package view;

import model.*;

import java.util.ArrayList;

public class Printer {

    //Metodo para imprimir niveles con sus listas
    public static void printLevels(LevelList ll, BeatenLevelList bll, boolean asResult){
        String beaten;
        String inex;
        String enex;

        if (asResult){
            inex = "<<< Search result >>>";
            enex = "No results were found";
        } else {
            inex = "<<< Levels and beaten levels >>>";
            enex = "You haven't added any levels yet";
        }

        if (!ll.getLevelList().isEmpty()){
            System.out.println(inex);
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
            System.out.println("========================================");
            Scan.waitForInput();
        } else {
            System.out.println(enex);
        }
    }

    //Metodo para imprimir niveles favoritos con su lista
    public static void printFavourites(LevelList ll) {
        if (!ll.getLevelList().isEmpty()){
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
        } else {
            System.out.println("You haven't added any levels to your favourites yet");
        }
    }

    //Metodo para imprimir la base de datos entera, la salida cambia si se estan buscando nulos o simplemente imprimiendo la BD
    public static void printAll(MegaClass mega, boolean checkingNulls){
        String beaten;

        if(checkingNulls && mega.isEmpty()){
            System.out.println("No broken entries were found in the database");
            System.out.println(" ");
        } else {
            if (checkingNulls){
                System.out.println("=== Broken entries ===");
            } else {
                System.out.println("=== Database ===");
            }
            System.out.println(" ");

            if (checkingNulls && mega.getUserList().getUserList().isEmpty()){
                System.out.println("No broken entries were found in the user table");
                System.out.println(" ");
            } else {
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
            }

            if (checkingNulls && mega.getPersonalInfoList().getPersonalInfoList().isEmpty()){
                System.out.println("No broken entries were found in the personal_info table");
                System.out.println(" ");
            } else {
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
            }

            if(checkingNulls && mega.getLevelList().getLevelList().isEmpty() && mega.getBeatenLevelList().getBeatenLevelList().isEmpty()){
                System.out.println("No broken entries were found in the level / beaten_level table");
                System.out.println(" ");
            } else {
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
            }

            if (checkingNulls && mega.getFavouriteLevelList().getFavouriteLevelList().isEmpty()){
                System.out.println("No broken entries were found in the fav_demons table");
                System.out.println(" ");
            } else {
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
            }

            if (checkingNulls && mega.getDifficultyList().getDifficultieList().isEmpty()){
                System.out.println("No broken entries were found in the demon_difficulties table");
                System.out.println(" ");
            } else {
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
            }

            if(!mega.isEmpty() && !checkingNulls){
                System.out.println("========================================");
                System.out.println(" ");
            }
            Scan.waitForInput();
        }
    }

    //Metodo para imprimir resultados de la busqueda tipo google
    public static void printSearchResult(ArrayList<String> searchResult){
        if (!searchResult.isEmpty()){
            System.out.println("<<< Search result >>>");
            System.out.println(" ");

            for (String string : searchResult){
                System.out.println(string);
            }

            Scan.waitForInput();
        } else {
            System.err.println("No results were found");
        }
    }
}
