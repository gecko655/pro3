package pro3.ex6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Taskを生成して実行依頼するクラス．
 * 時間計測を簡単化するためにProducerが1つのProducer-Consumerパターンで実装
 * @author tkobaya
 */
public class TaskProducer {

	String id;
	// pro3.ex4.TaskTrayでなく BlockingQueueを利用
	private BlockingQueue<Task> tray;

	// 生成するTaskの情報が記録されているファイル
	public static final String DEFAULT_PATTERN_FILE = "pattern.txt";

	protected List<Task> tasks = new ArrayList<>();
	protected List<Integer> intervals = new ArrayList<>();

	public TaskProducer(String s, BlockingQueue<Task> t) {
		this(s, t, DEFAULT_PATTERN_FILE);
	}

	public TaskProducer(String s, BlockingQueue<Task> t, String patternFile) {
		id = s;
		tray = t;

		// パターン情報を読み込み，メモリ上にタスクを生成
		String line = "";
		String[] taskParams;
		int taskNum = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(patternFile))) {
			while ((line = br.readLine()) != null) {
				taskParams = line.split(",");
				intervals.add(Integer.parseInt(taskParams[0]));
				tasks.add(new Task(Integer.parseInt(taskParams[1])));
				taskNum++;
			}
			System.out.println("Read " + taskNum + " task(s) info from " + patternFile);
		} catch (FileNotFoundException e) {
			System.err.println("File " + patternFile + " was not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void run() {
		try {
			// タスクと到着間隔の数が等しいことを前提としている
			// assertionで念のため確認
			assert (tasks.size() == intervals.size());

			for (int i = 0; i < tasks.size(); i++) {
				Task t = tasks.get(i);
				// Taskの用意にかかる時間分だけ待機
				Thread.sleep(intervals.get(i));
				t.setResponseStartTime(System.currentTimeMillis());
				// Taskを追加
				// BlockingQueue#put() は capacity を超えたらblock
				tray.put(t);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
