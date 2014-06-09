package pro3.ex3;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Drawing3 - 綺麗な線が引ける Drawingツール.
 * 点の描画ではなく，短い直線を描画する．
 * @author tkobaya
 *
 */
public class Drawing3 extends JPanel implements
		MouseMotionListener, ActionListener {

	//Object Version Number as Serializable 
	private static final long serialVersionUID = 1L;

	volatile Point pnt = new Point(0, 0);
	volatile Point lastPnt;
	volatile private Boolean clear = true;

	/*
	 * 描画用のイメージ．この内容を毎回描画する
	 */
	volatile BufferedImage bufImg;
	/*
	 * 描画用イメージを描画するためのGraphicsオブジェクト
	 */
	volatile Graphics2D bufG;

	public Drawing3() {
		super();
		addMouseMotionListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = this.getWidth();
		int h = this.getHeight();

		if (clear) {
			if (bufImg == null) {
				bufImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
				bufG = bufImg.createGraphics();
			}
			bufG.setColor(Color.white); // 白で塗利つぶす
			bufG.fillRect(0, 0, w, h);
			bufG.setColor(Color.black); // 線の色を設定する
			clear = false;
		}
		g.drawImage(bufImg, 0, 0, this);
	}

	public void mouseDragged(MouseEvent e) {
		int lineWidth = 2;
		pnt = e.getPoint();
		// 直前の位置から現在の位置までの直線を引く
		if (lastPnt != null) {
			bufG.setStroke(new BasicStroke(lineWidth ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
			bufG.drawLine(lastPnt.x, lastPnt.y, pnt.x, pnt.y);
			repaint();
		}
		lastPnt = pnt;

	}

	public void mouseMoved(MouseEvent e) {
		lastPnt = null;
	}

	public void actionPerformed(ActionEvent e) {
	    String action = e.getActionCommand();
	    //System.err.println(action);
	    if(action.equals("clear")){
            clear = true;
	    }else if(action.equals("Blue")){
	        bufG.setColor(Color.blue);
	    }
		repaint();
	}

	public static void main(String[] args) {
		final JFrame jframe = new JFrame("Drawing Tool");

		// Drawing Area
		Drawing3 drawing = new Drawing3();
		drawing.setPreferredSize(new Dimension(600, 400));

		// Clear button
		JButton jbutton = new JButton("clear");
		jbutton.addActionListener(drawing);

		// Menubar
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("Color");
		JMenuItem menuBlack = new JMenuItem("Black");
		JMenuItem menuRed = new JMenuItem("Red");
		JMenuItem menuBlue = new JMenuItem("Blue");
		menuBlue.addActionListener(drawing);
		//
		menu1.add(menuBlack);
		menu1.add(menuRed);
		menu1.add(menuBlue);
		menubar.add(menu1);
		jframe.setJMenuBar(menubar);

		Container container = jframe.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(drawing, BorderLayout.NORTH);
		container.add(jbutton, BorderLayout.SOUTH);
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
