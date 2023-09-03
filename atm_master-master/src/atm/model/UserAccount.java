package atm.model;

import atm.Color;
import atm.service.impl.AccountServiceImpl;

import java.awt.*;

public class UserAccount {
    private String name;
    private String lastName;
    private String cardNumber;
    private String pinCode;
    private int balance;

    public UserAccount() {
    }

    public UserAccount(String name, String lastName, String cardNumber, String pinCode, int balance) {
        this.name = name;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return Color.ANSI_CYAN + "[UserAccount : " + "  name : " + Color.ANSI_RESET + name +
                Color.ANSI_CYAN + "  lastName : " + Color.ANSI_RESET + lastName +
                Color.ANSI_CYAN + "  cardNumber : " + Color.ANSI_RESET + cardNumber +
                Color.ANSI_CYAN + "  pinCode : " + Color.ANSI_RESET + pinCode +
                Color.ANSI_CYAN + "  balance : " + Color.ANSI_RESET + balance + "]";
    }
}
