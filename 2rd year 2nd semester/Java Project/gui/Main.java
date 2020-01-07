package guigui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

public class Main extends JFrame implements ChangeListener, ItemListener {

	private JTextField textField = new JTextField(15); 
	private JButton button = new JButton("검색");

	private JLabel lb1 = new JLabel("               마커 선택 ");
	private JLabel lb2 = new JLabel(" 줌 크기 설정 : ");

	private JCheckBox chk1 = new JCheckBox("빨강색", false);
	private JCheckBox chk2 = new JCheckBox("노란색", false);

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private maps googleAPI = new maps();
	private JLabel googleMap = new JLabel();
	private JSlider slider = new JSlider(0, 20, 10);

	maps mpsize = new maps();
	boolean tf = true;
	public int xysize = 0;
	public int x = 0;
	public int y = 0;

	public void stateChanged(ChangeEvent arg1) { // 슬라이더 이벤트 처리
		JSlider source = (JSlider) arg1.getSource();

		if (!source.getValueIsAdjusting()) {
			int value = (int) source.getValue();
			maps.mpsize = value;
			setMap(textField.getText());
		}
	}

	public class EventMouse extends MouseAdapter implements MouseMotionListener {

		@Override
		public void mouseMoved(MouseEvent arg0) {}

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {
			x = arg0.getX();
			y = arg0.getY();
			googleMap.setLocation(x - 230, y - 325);
		}

		@Override
		public void mouseDragged(MouseEvent arg0) { // 드래그일시
			x = arg0.getX();
			y = arg0.getY();
			googleMap.setLocation(x - 225, y - 325);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}

	public class Event implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent arg0) { // 버튼 이벤트를 추가

			if (arg0.getSource() == button) {
				frame.setVisible(tf);
				setMap(textField.getText());
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg) {}

		@Override
		public void mouseDragged(MouseEvent arg) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}

		@Override
		public void mouseMoved(MouseEvent arg0) {}
	}

	public void setMap(String location) {
		googleAPI.downloadMap(location);
		googleMap.setIcon(googleAPI.getMap(location));
		googleAPI.fileDelete(location);
		frame.setLayout(new BorderLayout());
		googleMap.setBounds(0, 60, 750, 700);
		pack();
	}

	public Main() {

		setTitle("구글맵을 이용한 병원검색");
		frame.setLayout(new BorderLayout());
		frame.setSize(500, 500);
		frame.setLayout(null);

		panel.setSize(1000, 1000);
		slider.setBounds(100, 10, 250, 50);
		lb2.setBounds(20, 10, 100, 50);

		panel.setLayout(new GridLayout(8, 1, 5, 10));

		panel.add(BorderLayout.NORTH, textField);
		panel.add(button);
		panel.add(lb1);
		panel.add(chk1);
		panel.add(chk2);
		frame.add(lb2);
		frame.add(slider);
		frame.add(googleMap);

		add(BorderLayout.NORTH, panel);

		button.addMouseListener(new Event());
		frame.addMouseListener(new EventMouse());
		frame.addMouseMotionListener(new EventMouse());

		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);

		chk1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {// CheckBox 이벤트 처리
				if (e.getStateChange() == 1) {
					maps.maker1 = maps.makerR;
					maps.maker = maps.maker2 + maps.maker1;

				} else {
					maps.maker = maps.maker2 + "";
					maps.maker1 = "";
				}
				setMap(textField.getText());
			}
		});

		chk2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					maps.maker2 = maps.makerB;
					maps.maker = maps.maker1 + maps.maker2;
				} else {
					maps.maker = maps.maker1 + "";
					maps.maker2 = "";
				}
				setMap(textField.getText());
			}
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

	}

	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Main main = new Main();
	}
}