package pro3.ex6;

/**
 * pro3.ex4.Task の一部を変更した Task
 * 今回は，パッケージ関係の問題を排除するために継承・インポートしていません．
 * @author tkobaya
 *
 */
public class Task {

	private long taskWeight = 0L;
	private long responseStartTime = 0L; // 作業依頼時のcurrent Time [ms]
	private long workStartTime = 0L; // 作業開始時のcurrent Time [ms]
	private long endTime = 0L; // 作業開始時のcurrent Time [ms]

	public Task(long l) {
		this.taskWeight = l;
	}

	public long getWeight() {
		return taskWeight;
	}

	public void setResponseStartTime(long l) {
		responseStartTime = l;
	}

	public void setWorkStartTime(long l) {
		workStartTime = l;
	}

	synchronized public void setEndTime(long l) {
		endTime = l;
		notifyAll();
	}

	synchronized public long getResponseTime() {
		try {
			while (endTime == 0L) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return (endTime - responseStartTime);
	}

	public long getWaitingTime() {
		return (workStartTime - responseStartTime);
	}
}
