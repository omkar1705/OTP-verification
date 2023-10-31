package views;

import com.mysql.cj.conf.IntegerProperty;
import dao.UserDAO;
import model.User;
import service.GenarateOTP;
import service.SendOTPServices;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;


public class Welcome {
    public void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wellcome to the app");
        System.out.println("press 1 to login");
        System.out.println("press 2 to sign in");
        System.out.println("press 0 to exit");
        int ch=0;
        try{
            ch= Integer.parseInt(br.readLine());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        switch (ch){
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email:");
        String email = sc.nextLine();

        try {
            if (UserDAO.isExists(email)){
                String genOTP = GenarateOTP.getOTP() ;
                SendOTPServices.sendOTp(email, genOTP);
                System.out.println("enter the otp");
                String otp = sc.nextLine();
                if (otp.equals(genOTP)){
                    new UserView(email).home();

                    System.out.println("wellcome");
                }
                else {
                    System.out.println("wrong OTP");
                }

            }else {
                System.out.println("user not found");
            }
        }
        catch (SQLException ex ){
            ex.printStackTrace();
        }



    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the name ");
        String name = sc.nextLine();
        System.out.println("enter the e mail");
        String email = sc.nextLine();
        String genOTP = GenarateOTP.getOTP() ;
        SendOTPServices.sendOTp(email, genOTP);
        System.out.println("enter the otp");
        String otp = sc.nextLine();
        if (otp.equals(genOTP)){
            User user = new User(name , email);
            int responce = UserService.saveUser(user);
            switch (responce){
                case 0-> System.out.println("user is resgistered");
                case 1-> System.out.println("user already esists");
            }
            System.out.println("wellcome");
        }
        else {
            System.out.println("wrong OTP");
        }
    }
}


