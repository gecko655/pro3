package pro3.ex3;

public class AccountWithVolatile extends AccountNaive {

	protected volatile int vbalance = 10000;

	@Override
	public void deposit  (int amount) { vbalance += amount; }

	@Override
    public void withdraw (int amount) { vbalance -= amount; }

	@Override
    public int getBalance() { return vbalance; }

	public static void main (String[] args)  {
		
		runBySingleThread(new AccountWithVolatile());
		runByTwoThreads(new AccountWithVolatile());
		runByThreeThreads(new AccountWithVolatile());
	}


}
