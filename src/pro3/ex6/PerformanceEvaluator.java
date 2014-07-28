package pro3.ex6;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 性能測定用クラス (一部未実装)
 * @author tkobaya
 */
public class PerformanceEvaluator {

	/**
	 * 読み込むタスクパターンファイル名
	 */
	private String filename;
	/**
	 * タスク生成器
	 */
	private TaskProducer p;
	/**
	 *  全タスクの処理時間
	 */
	private long executionTime = 0L;

	public PerformanceEvaluator(String s) {
		filename = s;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	/**
	 * 既定のcapacityで 実行し実行時間を測定.
	 * @param numOfWorker -- Wokerの数
	 */
	private void exec(int numOfWorker) {
		exec(100, numOfWorker);
	}

	/**
	 * 実行し実行時間を測定.
	 * @param capacity -- Queueのサイズ
	 * @param numOfWoker -- Wokerの数
	 */
	public void exec(int capacity, int numOfWoker) {
		long startTime;

		System.out.println("Run with " + numOfWoker + " worker(s), capacity of queue is " + capacity);
		// Queueの用意
		BlockingQueue<Task> tray = new ArrayBlockingQueue<Task>(capacity);
		// Producerを用意 (タスクパターンデータからタスクオブジェクトが生成される)
		p = new TaskProducer("Producer", tray, filename);

		// Wokerの用意
		for (int i = 0; i < numOfWoker; i++) {
			new TaskWorker("Worker-" + i, tray).start();
		}

		// メモリにパターンデータを読み込んでから計測開始
		startTime = System.currentTimeMillis();
		// タスク投入開始
		p.run();
		// タスク投入完了(≠全タスク完了)したら時間計測
		List<Task> tasks = p.getTasks();
		// 最後のTaskの終了をもって全タスクの終了を(疑似的に)判断
		tasks.get(tasks.size() - 1).getResponseTime();
		executionTime = (System.currentTimeMillis() - startTime);
		System.out.println("Put " + tasks.size() + " Tasks " + executionTime + " [ms]");
	}


	public double getAveResponseTime() {
		List<Task> tasks = p.getTasks();
		double art = 0D;

		double rtSum = 0;
		for (Task t : tasks) {
			rtSum += t.getResponseTime();
		}
		art = (double) (rtSum / tasks.size());

		return art;
	}

	private double getThroughput() {
		List<Task> tasks = p.getTasks();
		double th = 0D;

		th = (double) (tasks.size() * 1000 / (executionTime));

		return th;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = "";
		int numOfWorker = 1;
		if (args.length == 2) {
			filename = args[0];
			numOfWorker = Integer.decode(args[1]);
		} else {
			System.err
					.println("Usage: java -cp ... pro3.ex6.PerformanceEvaluator <path-to-pattern-file> <# of Worker>");
			System.exit(0);
		}
		PerformanceEvaluator pe = new PerformanceEvaluator(filename);
		pe.exec(numOfWorker);
		System.out.println("Average Response Time : " + pe.getAveResponseTime() + " [ms]");
		System.out.println("Throughput : " + pe.getThroughput() + " [task/sec]");
	}

}
