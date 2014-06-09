package pro3.ex3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadExecutor extends JTextArea
		implements ActionListener {

	//Object Version Number as Serializable 
	private static final long serialVersionUID = 1L;

	static final int ROWNUM = 20;
	static final int COLNUM = 40;
	public static final int TASKWEIGHT = 10000000; // for Core i7-3667U

	private Map<String, TestThread> threadMap;

	public ThreadExecutor(int r, int c) {
		super(r, c);
		threadMap = new HashMap<String, TestThread>();
	}

	public void addString(String s) {
		String currentText = this.getText();
		this.setText(currentText + s + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();

		// 既に実行しているかどうかを確認し，
		// 実行していなかったらThreadを作成して実行．
		if (!threadMap.containsKey(command)) {
			TestThread tt = new TestThread(command);
			tt.start();
			addString("TestThread(" + command + ") is started");
			threadMap.put(command, tt);
		} else {
			TestThread th = threadMap.get(command);
			if (th.isSuspend()) {
				th.forceResume();
				addString("TestThread(" + command + ") is resumed");
			} else {
				th.forceSuspend();
				addString("TestThread(" + command + ") is suspened");
			}
		}
	}

	public static void main(String[] args) {
		final JFrame jframe = new JFrame("ThreadExecutor");
		JPanel resPanel = new JPanel();
		JTextArea text1 = new ThreadExecutor(ROWNUM, COLNUM);
		resPanel.setLayout(new BorderLayout());
		resPanel.add(text1, BorderLayout.CENTER);

		Container container = jframe.getContentPane();
		container.setLayout(new BorderLayout());

		List<JButton> buttons = new ArrayList<JButton>();

		for (int i = 0; i < 5; i++) {
			buttons.add(new JButton(Integer.toString(i)));
		}
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(1, 5));
		for (JButton bot : buttons) {
			bot.addActionListener((ActionListener) text1);
			keyPanel.add(bot);
		}

		jframe.add(resPanel, BorderLayout.NORTH);
		jframe.add(keyPanel, BorderLayout.CENTER);

		jframe.pack();
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				jframe.setVisible(true);
			}
		});

	}
}

class TestThread extends Thread {
	String com;
	boolean suspendFlag = false;

	public TestThread(String s) {
		super("TT-" + s);
		com = s;
	}

	public void forceResume() {
		suspendFlag = false;
	}

	public void forceSuspend() {
		suspendFlag = true;
	}

	public boolean isSuspend() {
		return suspendFlag;
	}

	public void run() {
		while (true) {
			try {
				// suspend状態の場合には1[s]毎に状態を確認．
				if (suspendFlag) {
					sleep(1000);
				} else {
					// 現在の時間を使って乱数を作成
					long sleepTime = Math.round((Math.random() * 10));
					long workTime = Math.round((Math.random() * 10));
					//work
					System.out.print(com + "(" + workTime + "," + sleepTime + ")");
					for (int i = 0; i < workTime * ThreadExecutor.TASKWEIGHT; i++) {
						Math.sin(workTime);
						Math.cos(workTime);
					}
					//sleep
					sleep(sleepTime * 1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
