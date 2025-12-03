import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Account {
    private int balance;

    public Account(int initialBalance) {
        this.balance = initialBalance;
    }


    public synchronized void deposit(int amount, String threadName) {
        System.out.println(threadName + " is depositing: " + amount);
        int newBalance = balance + amount;
       
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        balance = newBalance;
        System.out.println(threadName + " completed deposit. New balance: " + balance);
    }


    public synchronized void withdraw(int amount, String threadName) {
        System.out.println(threadName + " is trying to withdraw: " + amount);

        if (balance >= amount) {
            int newBalance = balance - amount;

            // simulate some processing time
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            balance = newBalance;
            System.out.println(threadName + " completed withdrawal. New balance: " + balance);
        } else {
            System.out.println(threadName + " FAILED withdrawal. Insufficient funds. Current balance: " + balance);
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}

// -------------------- Transaction CLASS --------------------
class Transaction implements Runnable {
    private final Account account;
    private final String type;   // "deposit" or "withdraw"
    private final int amount;
    private final String name;   

    public Transaction(Account account, String type, int amount, String name) {
        this.account = account;
        this.type = type.toLowerCase();
        this.amount = amount;
        this.name = name;
    }

    @Override
    public void run() {
        if (type.equals("deposit")) {
            account.deposit(amount, name);
        } else if (type.equals("withdraw")) {
            account.withdraw(amount, name);
        } else {
            System.out.println(name + ": Unknown transaction type: " + type);
        }
    }
}

// -------------------- AccountTest CLASS (with main) --------------------
public class AccountTest {
    public static void main(String[] args) {
        
        Account sharedAccount = new Account(1000);

        
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(sharedAccount, "withdraw", 300, "Transaction-1"));
        transactions.add(new Transaction(sharedAccount, "deposit", 500, "Transaction-2"));
        transactions.add(new Transaction(sharedAccount, "withdraw", 800, "Transaction-3"));
        transactions.add(new Transaction(sharedAccount, "deposit", 200, "Transaction-4"));
        transactions.add(new Transaction(sharedAccount, "withdraw", 400, "Transaction-5"));

        
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("Initial balance: " + sharedAccount.getBalance());
        System.out.println("---- Starting transactions ----");

       
        for (Transaction t : transactions) {
            executor.execute(t);
        }

       
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("---- All transactions finished ----");
        System.out.println("Final balance: " + sharedAccount.getBalance());
    }
}
