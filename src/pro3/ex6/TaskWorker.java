package pro3.ex6;

import java.util.concurrent.BlockingQueue;

/**
 * pro3.ex4.Task の一部を変更した TaskWoker
 * 今回は，パッケージ関係の問題を排除するために継承・インポートしていません．
 * 同名のクラスを作っても別パッケージであれば名前衝突しないことに注意
 * @author tkobaya
 */
public class TaskWorker extends Thread {

	String id;
	BlockingQueue<Task> tray;

	public TaskWorker(String s, BlockingQueue<Task> tt) {
		id = s;
		tray = tt;
	}

	public void run() {
		try {
			while (true) {
				// BlockingQueue#take() は
				// size=0 だった場合はblock
				consume(tray.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void consume(Task t) throws InterruptedException {
		t.setWorkStartTime(System.currentTimeMillis());
		sleep(t.getWeight());
		t.setEndTime(System.currentTimeMillis());
		//		System.out.println("Task done: response " + t.getResponseTime()
		//				+ " [ms], wait " + t.getWaitingTime() + " [ms], work " + t.getWeight() + " [ms]");
		//		System.out.print(".");
	}
}
