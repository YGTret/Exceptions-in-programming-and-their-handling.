import java.util.HashMap;
import java.util.Map;

class BankAccount {
    private String accountNumber;
    private double balance;
    private double maxBalance;

    public BankAccount(String accountNumber, double maxBalance) {
        this.accountNumber = accountNumber;
        this.maxBalance = maxBalance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) throws MaxBalanceExceededException {
        if (balance + amount > maxBalance) {
            throw new MaxBalanceExceededException("Превышено максимальное значение баланса на счете");
        }
        balance += amount;
    }

    public synchronized void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Недостаточно средств на счете");
        }
        balance -= amount;
    }
}

class Bank {
    private Map<String, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public synchronized BankAccount createAccount(String accountNumber, double maxBalance) {
        if (accounts.containsKey(accountNumber)) {
            return null;
        }

        BankAccount account = new BankAccount(accountNumber, maxBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public void deposit(String accountNumber, double amount) throws MaxBalanceExceededException {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
        }
    }

    public void withdraw(String accountNumber, double amount) throws InsufficientFundsException {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        }
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class MaxBalanceExceededException extends Exception {
    public MaxBalanceExceededException(String message) {
        super(message);
    }
}
