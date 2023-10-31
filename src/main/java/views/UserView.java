package views;
public class UserView {
    private String email;
    UserView(String emial){
        this.email = emial ;
    }
    public void home(){
            System.out.println("wellcome " + this.email);
           System.out.println("this is our OOPM Project");
    }
}
