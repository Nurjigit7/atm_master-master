package atm.service.impl;

import atm.dao.AccountDao;
import atm.model.UserAccount;
import atm.service.AccountService;

import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao = new AccountDao();
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);


    @Override
    public void singUp(String name, String lastName) {
        UserAccount userAccount = new UserAccount();
        userAccount.setName(name);
        userAccount.setLastName(lastName);

        int cardNumber = random.nextInt(10_000_000, 99_999_999);
        int pinCode = random.nextInt(1_000, 9_999);

        userAccount.setCardNumber(String.valueOf(cardNumber));
        userAccount.setPinCode(String.valueOf(pinCode));

        accountDao.getUserAccounts().add(userAccount);
        System.out.println(userAccount);
    }

    @Override
    public void singIn(String name, String lastName) {
        /**бул методту да текшериш керек
         *
         */
        UserAccount user = accountDao.getUserAccounts()
                .stream().filter(n -> n.getName().equals(name) && n.getLastName()
                        .equals(lastName)).findFirst().orElse(null);
        System.out.println(user);

//                for (UserAccount account : accountDao.getUserAccounts()) {
//                    if (account.getName().equalsIgnoreCase(name) && account.getLastName().equalsIgnoreCase(lastName)) {
//
//                        System.out.println(account);
//                    }
//                }

    }

    @Override
    public void balance(String cardNumber, String pinCode) {
        accountDao.getUserAccounts().stream().filter(n -> n.getPinCode().
                        equals(pinCode) && n.getCardNumber().equals(cardNumber)).
                findFirst().ifPresentOrElse(user -> System.out.println("Твое баланс="
                        + user.getBalance()), () -> System.out.println("Пользователь не найден!!"));

    }

    @Override
    public void deposit(String cardNumber, String pinCode) {
        System.out.println("Введите сколько хотите денег внести !");
        int money = scanner.nextInt();
        accountDao.getUserAccounts().stream().filter(n -> n.getPinCode().equals(pinCode)
                && n.getCardNumber().equals(cardNumber)).findFirst().ifPresentOrElse(user -> user.
                setBalance(user.getBalance() + money), () -> System.out.println("Пользователь не найден!!"));


    }

    @Override
    public void sendToFriend(String friendCardNumber) {

        System.out.println("Введите сколько хотите денег перевести !");
        int money = scanner.nextInt();
        accountDao.getUserAccounts().stream()
                .filter(n -> n.getCardNumber().equals(friendCardNumber)).
                findFirst().ifPresentOrElse(user -> user.
                        setBalance(user.getBalance() + money), () -> System.out.println("Друг не найден!!"));

    }

    @Override
    public void withdrawMoney(int amount) {
        /**ушул методко маани бериш керек
         *
         */

//        System.out.println("Введите сколько хотите деньги пополнит");
        Boolean end = false;
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@  Welcome to the RMUTT BANK @@@@");
        while (end == false) {
            try {
                System.out.println("\n Type 1 - Create Account");
                System.out.println(" Type 2 - Login");
                System.out.println(" Type 3 - Set Manager");
                System.out.println(" Type 4 - Get Info Manager");
                System.out.println(" Type 5 - Exit");
                System.out.print("\nChoice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("\nCreate Account.");
                        end = true;
                        break;
                    case 2:
                        System.out.println("\nLogin.");
                        end = true;
                        break;
                    case 3:
                        System.out.println("\nSet Manager.");
                        end = true;
                        break;
                    case 4:
                        System.out.println("\nGet Manager.");
                        end = true;
                        break;
                    case 5:
                        System.out.println("\nExit.");
                        end = true;
                        System.exit(0);
                        break;

                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError" + e);
            }


        }
    }

    public void SingIn() {


    }
}
