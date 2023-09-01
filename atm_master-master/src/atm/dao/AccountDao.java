package atm.dao;

import atm.model.UserAccount;

import java.util.ArrayList;
import java.util.List;


public class AccountDao {
    List<UserAccount> userAccounts = new ArrayList<>();

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public UserAccount getUserByCar(String cardNumber) {
        return userAccounts.stream().filter(s -> s.getCardNumber().equals(cardNumber)).findFirst().orElse(null);
    }
}
