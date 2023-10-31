package views;

import dao.DataDao;
import model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;
    UserView(String emial){
        this.email = emial ;
    }
    public void home(){
        do {
            System.out.println("wellcome " + this.email);
            System.out.println("1 to show hidden files");
            System.out.println("2 to hide new files");
            System.out.println("3 to unhide the file");
            System.out.println("0 to exit");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch)
            {
                case 1-> {
                    try {
                        List<Data> files = DataDao.getAllfiles(this.email);
                        System.out.println("ID - File Name");
                        for(Data file : files)
                        {
                            System.out.println(file.getId()+ "-" + file.getFileName());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("entre the file path");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0, f.getName(),path , this.email);
                    try {
                        DataDao.hideFile(file);
                    } catch (SQLException e) {

                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                }
                case 3 -> {
                    List<Data> files = null;
                    try {
                        files = DataDao.getAllfiles(this.email);

                    System.out.println("ID - File Name");
                    for(Data file : files)
                    {
                        System.out.println(file.getId()+ "-" + file.getFileName());
                    }
                    System.out.println("enter the id of the file to unhide");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean isVAlidID = false;
                    for (Data file : files)
                    {
                        if(file.getId()== id){
                            isVAlidID = true;
                            break;                        }
                    }
                    if (isVAlidID){
                        DataDao.unhide(id);
                    }
                    else {
                        System.out.println("entered the wrong ID plz try again");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e ){
                        e.printStackTrace();
                    }
                }
                case 0 -> {
                    System.exit(0);
                }
            }
        }while (true);
    }
}
