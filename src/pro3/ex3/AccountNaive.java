package pro3.ex3;

public class AccountNaive extends Account {

	public AccountNaive(){super();}

	@Override
	public void deposit  (int amount) { balance += amount; }
	@Override
	public void withdraw (int amount) { balance -= amount; }


	public static void main (String[] args)  {
		runBySingleThread(new AccountNaive());
		runByTwoThreads(new AccountNaive());
		runByThreeThreads(new AccountNaive());
	}

}
