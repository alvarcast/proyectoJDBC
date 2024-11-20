package view;

import model.*;

public class Printer {

    public static void printLevels(LevelList ll, BeatenLevelList bll){
        String beaten;

        System.out.println("<<< Levels and beaten levels >>>");
        System.out.println(" ");

        for (Level level : ll.getLevelList()){
            beaten = switch (level.getBeaten()){
                case 0 -> "No";
                case 1 -> "Yes";
                default -> "";
            };

            System.out.println("========================================");
            System.out.println(" ");

            System.out.println("Level game ID: " + level.getGame_id());
            System.out.println("Level name: " + level.getLevel_name());
            System.out.println("Level creator: " + level.getCreator());
            System.out.println("Level music: " + level.getMusic());
            System.out.println("Level difficulty: " + level.getDifficulty());
            System.out.println("Level GDDL difficulty: " + level.getDiff_num());

            if (beaten.equals("No")){
                System.out.println("Attempts: " + level.getAttempts());
                System.out.println("Start date: " + level.getStart_date());
            }

            System.out.println("Beaten: " + beaten);

            if (beaten.equals("No")){
                System.out.println(" ");
            } else {
                System.out.println(">>>");
            }

            if (beaten.equals("Yes")){
                //Esto sigue hasta que comprueba todos, no para
                for (BeatenLevel beatenLevel : bll.getBeatenLevelList()){
                    if (level.getLid() == beatenLevel.getLid()){
                        System.out.println("Music rate: " + beatenLevel.getMusic_rate());
                        System.out.println("Gameplay rate: " + beatenLevel.getGameplay_rate());
                        System.out.println("Decoration rate: " + beatenLevel.getDeco_rate());
                        System.out.println("Effects rate: " + beatenLevel.getFx_rate());
                        System.out.println("Enjoyment (AVG rate): " + beatenLevel.getEnjoyment());
                        System.out.println("Tottal attempts: " + beatenLevel.getTotal_attempts());
                        System.out.println("Start date: " + level.getStart_date());
                        System.out.println("End date: " + beatenLevel.getEnd_date());
                        System.out.println(" ");
                    }
                }
            }
        }
        Scan.waitForInput();
    }

    public static void printFavourites(LevelList ll) {
        System.out.println("<<< Favourite levels (formated) >>>");
        System.out.println(" ");

        for (Level level : ll.getLevelList()){
            System.out.println("========================================");
            System.out.println(" ");

            System.out.println("Level game ID: " + level.getGame_id());
            System.out.println("Level name: " + level.getLevel_name());
            System.out.println("Level creator: " + level.getCreator());
            System.out.println("Level music: " + level.getMusic());
            System.out.println("Level difficulty: " + level.getDifficulty());

            System.out.println(" ");
        }
        Scan.waitForInput();
    }

    public static void printAll(MegaClass mega){
        String beaten;

        System.out.println("=== Database ===");
        System.out.println(" ");

        System.out.println("<<< Users >>>");
        System.out.println(" ");

        for (User u : mega.getUserList().getUserList()){
            System.out.println("========================================");
            System.out.println(" ");

            System.out.println("ID: " + u.getUid());
            System.out.println("Username: " + u.getUsername());
            System.out.println("Password: " + u.getPassword());

            System.out.println(" ");
        }

        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("<<< Personal information table >>>");
        System.out.println(" ");

        for (PersonalInfo pi : mega.getPersonalInfoList().getPersonalInfoList()){
            System.out.println("========================================");
            System.out.println(" ");

            System.out.println("User: " + pi.getUsername());
            System.out.println("Real name: " + pi.getName());
            System.out.println("Surname: " + pi.getSurname());
            System.out.println("E-Mail: " + pi.getEmail());

            System.out.println(" ");
        }

        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("<<< Levels and beaten levels >>>");
        System.out.println(" ");

        for (Level level : mega.getLevelList().getLevelList()){
            beaten = switch (level.getBeaten()){
                case 0 -> "No";
                case 1 -> "Yes";
                default -> "";
            };

            System.out.println("========================================");
            System.out.println(" ");

            System.out.println("User: " + level.getUser());
            System.out.println("Level game ID: " + level.getGame_id());
            System.out.println("Level name: " + level.getLevel_name());
            System.out.println("Level creator: " + level.getCreator());
            System.out.println("Level music: " + level.getMusic());
            System.out.println("Level difficulty: " + level.getDifficulty());
            System.out.println("Level GDDL difficulty: " + level.getDiff_num());

            if (beaten.equals("No")){
                System.out.println("Attempts: " + level.getAttempts());
                System.out.println("Start date: " + level.getStart_date());
            }

            System.out.println("Beaten: " + beaten);

            if (beaten.equals("No")){
                System.out.println(" ");
            } else {
                System.out.println(">>> Ratings & Statistics");
            }

            if (beaten.equals("Yes")){
                //Esto sigue hasta que comprueba todos, no para
                for (BeatenLevel beatenLevel : mega.getBeatenLevelList().getBeatenLevelList()){
                    if (level.getLid() == beatenLevel.getLid()){
                        System.out.println("Music rate: " + beatenLevel.getMusic_rate());
                        System.out.println("Gameplay rate: " + beatenLevel.getGameplay_rate());
                        System.out.println("Decoration rate: " + beatenLevel.getDeco_rate());
                        System.out.println("Effects rate: " + beatenLevel.getFx_rate());
                        System.out.println("Enjoyment (AVG rate): " + beatenLevel.getEnjoyment());
                        System.out.println("Tottal attempts: " + beatenLevel.getTotal_attempts());
                        System.out.println("Start date: " + level.getStart_date());
                        System.out.println("End date: " + beatenLevel.getEnd_date());
                        System.out.println(" ");
                    }
                }
            }
        }

        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("<<< Favourite demmons >>>");
        System.out.println(" ");

        for (FavouriteLevel fl : mega.getFavouriteLevelList().getFavouriteLevelList()){
            System.out.println("========================================");
            System.out.println(" ");

            System.out.println("User: " + fl.getUsername());
            System.out.println("Level: " + fl.getLevel_name());

            System.out.println(" ");
        }

        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("<<< Demon difficulties >>>");
        System.out.println(" ");

        for (Difficulty d : mega.getDifficultyList().getDifficultieList()){
            System.out.println("========================================");
            System.out.println(" ");

            System.out.println("ID: " + d.getDid());
            System.out.println("Difficulty: " + d.getDiff_name());

            System.out.println(" ");
        }

        System.out.println("========================================");
        System.out.println(" ");
        Scan.waitForInput();
    }
}
