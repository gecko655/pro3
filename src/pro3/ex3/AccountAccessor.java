package pro3.ex3;

public class AccountAccessor implements Runnable {

	static final int TRYTIME = 10000000;


	Account account;

	public AccountAccessor (Account account) {
		this.account = account;
	}

	public void run () {
		for (int i = 0; i < TRYTIME; i++) {
			if (i % 2 == 0) {
				account.deposit (200);
			} else {
				account.withdraw (200);
	        }
		}
	}

	/**
	 * Optimized version of run().
	 */
	public void run2 () {
		for (int i = 0; i < TRYTIME / 2; i++) {
				account.deposit (200);
				account.withdraw (200);
		}
	}


}



