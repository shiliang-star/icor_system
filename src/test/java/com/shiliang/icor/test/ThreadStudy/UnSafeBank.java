package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/2 22:47
 * @Description 不安全银行取钱案例
 */
public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "基金");
        Bank me = new Bank(account, 5, "我");
        Bank girlFriend = new Bank(account, 10, "girlFriend");
        me.start();
        girlFriend.start();
    }

}

class Account {
     Integer money;
     String name;

    public Account(Integer money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Bank extends Thread {

    Account account;

    //取得钱
    int getMoney;

    //身上的钱
    int handMoney;

    public Bank(Account account, int getMoney, String name) {
        super(name);
        this.account = account;
        this.getMoney = getMoney;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (account.money - getMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够了，取不了");
                return;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money = account.money - getMoney;
            handMoney = handMoney + getMoney;
            System.out.println(account.name + "余额为：" + account.money);
            System.out.println(this.getName() + "手里的钱" + handMoney);
        }
    }
}
