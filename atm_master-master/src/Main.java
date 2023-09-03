import atm.service.impl.AccountServiceImpl;

public class Main {
    public static void main(String[] args) {
        AccountServiceImpl accountService=new AccountServiceImpl();
        accountService.singUp("Nurjigit","Umarov");
        accountService.singUp("Rysbek","Sharapov");


       while (true){
           accountService.singIn("Nurjigit","Umarov");
       }



    }
}