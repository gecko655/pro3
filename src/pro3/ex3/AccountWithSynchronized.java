package pro3.ex3;

public class AccountWithSynchronized extends AccountNaive {


	@Override
	synchronized public void deposit  (int amount) { balance += amount; }

	@Override
	synchronized public void withdraw (int amount) { balance -= amount; }


	public static void main (String[] args)  {
		runBySingleThread(new AccountWithSynchronized());
		runByTwoThreads(new AccountWithSynchronized());
		runByThreeThreads(new AccountWithSynchronized());
	}

}
