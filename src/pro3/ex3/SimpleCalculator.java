package pro3.ex3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class SimpleCalculator extends JTextField
		implements ActionListener {

	//Object Version Number as Serializable 
	private static final long serialVersionUID = 1L;

	/**
	 * 表示欄の桁数
	 */
	static final int MAXLEN = 30;

	/**
	 * 計算結果の保管場所
	 */
	private int result = 0;

    private char lastOpe = '+';

	public SimpleCalculator() {
		super();
	}

	public SimpleCalculator(int i) {
		super(i);
	}

	/**
	 * 足し算を実行する
	 * それまでの計算結果に入力された文字列を
	 * 数字として評価した値を加え，表示する.
	 *
	 * 数字のみが入力せされていると仮定．
	 * 本当は入力のチェックが必要．
	 */
	protected void plus() {
		int inputNum = Integer.valueOf(this.getText());
		result += inputNum;
		this.setText(String.valueOf(result));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
        char input = arg0.getActionCommand().charAt(0);
		if(input=='+'||input=='-'){
		    doCalc();
		    lastOpe  = input;
		    this.setText("");
		} else if(input=='='){
		    doCalc();
		    this.setText(String.valueOf(result));
		} else{
            String newVal = this.getText()+ input;
            this.setText(newVal);
		}
	}

	private void doCalc() {
	    switch (lastOpe){
	    case '-':
	        this.setText('-'+this.getText());
	    case '+':
	        plus();
	        break;
	    }
        
    }

    public static void main(String[] args) {
		final JFrame jframe = new JFrame("Simple Calculator");
		JPanel resPanel = new JPanel();
		JTextField text1 = new SimpleCalculator(MAXLEN);
		resPanel.setLayout(new BorderLayout());
		resPanel.add(text1, BorderLayout.CENTER);

		Container container = jframe.getContentPane();
		container.setLayout(new BorderLayout());

		List<JButton> buttons = new ArrayList<JButton>();

		for (int i = 0; i < 10; i++) {
			buttons.add(new JButton(Integer.toString(i)));
		}
		buttons.add(new JButton("+"));
		buttons.add(new JButton("-"));
		buttons.add(new JButton("="));

		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(5, 3));

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
