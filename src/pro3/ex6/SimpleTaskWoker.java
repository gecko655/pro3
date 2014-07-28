package pro3.ex6;

public class SimpleTaskWoker extends Thread {

	volatile static int num =0; // Class field 覚えてますか？
	private int id = num;
	private Task task;

	public SimpleTaskWoker(Task t) {
		task = t;
		num++;
	}

	public void run() {
		try {
			task.setWorkStartTime(System.currentTimeMillis());
			sleep(task.getWeight());
			task.setEndTime(System.currentTimeMillis());
					System.out.println("Thread-"+id+" Task done: response " + task.getResponseTime()
							+ " [ms], wait " + task.getWaitingTime() + " [ms], work " + task.getWeight() + " [ms]");
			//System.out.print(".");
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
