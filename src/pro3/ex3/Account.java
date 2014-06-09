package pro3.ex3;

abstract public class Account {

    protected int balance = 10000;

	abstract public void deposit  (int amount);
    abstract public void withdraw (int amount);
    public int getBalance() { return balance; }



	public static void runBySingleThread(Account acc) {
		String log = "[SigleThread Execution] ";
		long start = System.currentTimeMillis();
		AccountAccessor aa1 = new AccountAccessor(acc);
		AccountAccessor aa2 = new AccountAccessor(acc);
		log += "(Prepare AAs: " + (System.currentTimeMillis() - start) + "[ms] ";
		aa1.run(); aa2.run();
		log += "Calculation: " + (System.currentTimeMillis() - start) + "[ms]) " ;
		System.out.println(log);
		System.out.println("balance = " + acc.getBalance());
	}

    public static void runByTwoThreads(Account acc) {
		String log = "[TwoThreads Execution]";
		long start = System.currentTimeMillis();
		try {
			Thread th1 = new Thread(new AccountAccessor(acc));
			Thread th2 = new Thread(new AccountAccessor(acc));
			log += "(Prepare AAs: " + (System.currentTimeMillis() - start) + "[ms] ";
			start = System.currentTimeMillis();
			th1.start(); th2.start();
			th1.join();  th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log += "Calculation: " + (System.currentTimeMillis() - start) + "[ms]) " ;
		System.out.println(log);
		System.out.println("balance = " + acc.getBalance());
	}

    public static void runByThreeThreads(Account acc) {
		String log = "[ThreeThreads Execution]";
		long start = System.currentTimeMillis();
		try {
			Thread th1 = new Thread(new AccountAccessor(acc));
			Thread th2 = new Thread(new AccountAccessor(acc));
			Thread th3 = new Thread(new AccountAccessor(acc));
			log += "(Prepare AAs: " + (System.currentTimeMillis() - start) + "[ms] ";
			start = System.currentTimeMillis();
			th1.start(); th2.start(); th3.start();
			th1.join();  th2.join();  th3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log += "Calculation: " + (System.currentTimeMillis() - start) + "[ms]) " ;
		System.out.println(log);
		System.out.println("balance = " + acc.getBalance());
	}


}