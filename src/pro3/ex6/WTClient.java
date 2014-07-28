package pro3.ex6;

import java.util.concurrent.BlockingQueue;

public class WTClient extends TaskProducer {

	public WTClient(String id, BlockingQueue<Task> t, String filename) {
		super(id, t, filename);
	}

	public void run() {
		try {
			// タスクと到着間隔の数が等しいことを前提としている
			// assertionで念のため確認
			assert (tasks.size() == intervals.size());

			System.out.println("Start (" + tasks.size() + " tasks)");
			for (int i = 0; i < tasks.size(); i++) {
				Task t = tasks.get(i);
				// Taskの用意にかかる時間分だけ待機
				Thread.sleep(intervals.get(i));
				t.setResponseStartTime(System.currentTimeMillis());
				new SimpleTaskWoker(t).start();
			}
			tasks.get(tasks.size()-1).getResponseTime();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Queueは使わないので nullを渡す
		WTClient wtc = new WTClient("WTClient", null,  "pattern5.txt");
		long startTime = System.currentTimeMillis();
		wtc.run();
		System.out.println("Execution Time: " + (System.currentTimeMillis() - startTime) +" [ms]");
	}

}
