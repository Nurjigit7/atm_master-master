package atm.service.impl;

import atm.dao.AccountDao;
import atm.model.UserAccount;
import atm.service.AccountService;
import enums.Services;
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
        try {
            UserAccount user = accountDao.getUserAccounts()
                    .stream().filter(n -> n.getName().equalsIgnoreCase(name) && n.getLastName()
                            .equalsIgnoreCase(lastName)).findFirst().orElseThrow(() -> new RuntimeException("Not found User"));
            int choice = 0;
//            for (int i = 0; i < accountDao.getUserAccounts().size(); i++) {
//                UserAccount user = accountDao.getUserAccounts().get(i);
//                if (user.getName().equalsIgnoreCase(name) && user.getLastName().equalsIgnoreCase(lastName)) {
            System.out.println("select services !!");
            String word;
            System.out.print(Services.BALANCE + " Для пополнения баланса нажмите на [1] "
                    + "\n" + Services.DEPOSIT + " Для получение депозита нажните на   [2] "
                    + "\n" + Services.SEND_MONEY_TO_FRIEND + " Для отправки денег другу нажните на  [3] "
                    + "\n" + Services.WITHDRAW_MONEY + " Для снятия наличных денег нажните на [4]");

            while (true) {
                word = scanner.nextLine();
                if ("1234".contains(word) && !word.equals("")) {
                    choice = Integer.parseInt(word);
                    break;
                } else if (!word.equals(" ")) {
                    System.out.println("select services !!!");
                }
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Введите номер карта ->");
                    String cardNumber = scanner.nextLine();
                    System.out.println("Введите пароль ->");
                    String pinCode = scanner.nextLine();
                    balance(cardNumber, pinCode);
                }
                case 2 -> {
                    System.out.println("Введите номер карта ->");
                    String cardNumber = scanner.nextLine();
                    System.out.println("Введите пароль ->");
                    String pinCode = scanner.nextLine();
                    deposit(cardNumber, pinCode);
                }
                case 3 -> {
                    System.out.println("Введите друга карта номер ->");
                    String friendCardNumber = scanner.nextLine();
                    sendToFriend(friendCardNumber);
                }
                case 4 -> {
                    System.out.println("Сколько дениг хотите снять ->");
                    int amount = scanner.nextInt();
                    withdrawMoney(amount);

                }

            }

        } catch (NumberFormatException e) {
            System.out.println("\n Error:" + e);

        }
    }

    @Override
    public void balance(String cardNumber, String pinCode) {
        accountDao.getUserAccounts().stream().filter(n -> n.getPinCode().
                        equals(pinCode) && n.getCardNumber().equals(cardNumber)).
                findFirst().ifPresentOrElse(user -> System.out.println("Твое баланс :"
                        + user.getBalance()), () -> System.out.println("Пользователь не найден!!"));
    }

    @Override
    public void deposit(String cardNumber, String pinCode) {
//        System.out.println("Введите сколько хотите денег внести !");
//        int money = scanner.nextInt();
//        accountDao.getUserAccounts().stream().filter(n -> n.getPinCode().equals(pinCode)
//                && n.getCardNumber().equals(cardNumber)).findFirst().ifPresentOrElse(user -> user.
//                setBalance(user.getBalance() + money), () -> System.out.println("Пользователь не найден!!"));

        UserAccount user = accountDao.getUserByCar(cardNumber);
        if (user != null && user.getPinCode().equals(pinCode)) {
            System.out.println("Введите суума ");
            int sum = scanner.nextInt();
            user.setBalance(sum);
            accountDao.getUserAccounts().add(user);
            System.out.println("Deposit amount" + user);
        } else {
            System.out.println("Не правилный данный !!!");
        }
    }

    @Override
    public void sendToFriend(String friendCardNumber) {
        System.out.println("Введите номер карты :");
        String cardNumber = scanner.nextLine();
        System.out.println("Введите cвои пин код :");
        String pinCode = scanner.nextLine();
        System.out.println("Сколько хотите денег перевести ??");
        int money = scanner.nextInt();
        for (int i = 0; i < accountDao.getUserAccounts().size(); i++) {
            UserAccount user = accountDao.getUserAccounts().get(i);
            if (user.getCardNumber().equals(friendCardNumber)) {
                friendCardNumber = String.valueOf(user);
            }
        }
        for (int j = 0; j < accountDao.getUserAccounts().size(); j++) {
            UserAccount user = accountDao.getUserAccounts().get(j);
            if (user.getCardNumber().equals(cardNumber) && user.getPinCode().equals(pinCode)) {
                user.setBalance(user.getBalance() - money);
                accountDao.getUserAccounts().add(user);
                user.setBalance(user.getBalance() + money);
                accountDao.getUserAccounts().add(user);
                System.out.println("Ваше данных : " + user);
                System.out.println("Данный друга : " + user);
            } else {
                System.out.println("Логин или пароль неверный");
    }
//        System.out.println("Введите номер карты :");
//        String cardNumber = scanner.nextLine();
//        System.out.println("Введите свой  пин код :");
//        String pinCode = scanner.nextLine();
//        System.out.println("Сколько денег хотите перевести?");
//        int amount = scanner.nextInt();
//        for (int i = 0; i < accountDao.getUserAccounts().size(); i++) {
//            UserAccount user = accountDao.getUserAccounts().get(i);
//            if (user.getCardNumber().equals(cardNumber) && user.getPinCode().equals(pinCode)) {
//                user.setBalance(user.getBalance()-amount);
//                user.setBalance(user.getBalance()+amount);
//                accountDao.getUserAccounts().add(user);
//                System.out.println(" Ваши данные :  " + user);
//                System.out.println("Данные друга  : " + user);
//            } else {
//                System.out.println("Логин или пароль неверный");
//            }
        }
    }

    @Override
    public void withdrawMoney(int amount) {
        System.out.println("");
        int[] money = {1000, 500, 200, 100, 50};
        int choice = amount;
        for (int moneys : money) {
            int arifmet = amount / moneys;
            if (arifmet > 0) {
                System.out.println(moneys + "-> штук :" + arifmet);
                choice %= moneys;
            }
        }
        if (choice > 0) {
            System.out.println("монетками -> 10p" + choice * 100 + " -> штук");
        }
    }
}