package guigui;

import parser.HospitalDB;
import parser.HospitalParsing;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

import chatting.MultiChatServer;
import chatting.ClientFrame;
import chatting.MultiChatClient;
import guigui.Main;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.border.LineBorder;

import static guigui.LocationData.*;

public class GUI extends JFrame {
	JList townList;
	JList cityList;

	public static List<String[]> tableData = new ArrayList<>();

	public static String selectedCity;
	public static String selectedTown;

	private Label text1;
	JPanel panel = new JPanel();
	JPanel panelA = new JPanel();
	JPanel panelB = new JPanel();
	JPanel panelD = new JPanel();

	public GUI() {
		HospitalParsing.run();
		setSize(600, 600); // 프레임 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼을 누르게 될 경우 프로그램까지 종료되게 함
		setTitle("병원 검색 앱");

		panelA.setBounds(0, 0, 586, 48);
		panelB.setBounds(0, 46, 586, 484);
		/* 안내 문구 */
		text1 = new Label("City                               Town");
		panelD.add(text1);

		/* 패널 색상 변경 */
		panel.setBackground(Color.WHITE);
		panelA.setBackground(Color.PINK);
		panelB.setBackground(Color.WHITE);
		panelD.setBackground(Color.WHITE);

		/* 시 / 도 선택 리스트 */

		cityList = new JList(cities); // JList 객체 생성
		cityList.setBorder(new LineBorder(new Color(0, 0, 0)));
		cityList.setBounds(58, 42, 95, 305);
		cityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cityList.addListSelectionListener(new ListSelectionListener() {
		
			public void valueChanged(ListSelectionEvent e) {

				if (cityList.getSelectedIndex() == 0) {
					setSelectedPlace(seoul);
				}
				if (cityList.getSelectedIndex() == 1) {
					setSelectedPlace(busan);
				}
				if (cityList.getSelectedIndex() == 2) {
					setSelectedPlace(daegu);
				}
				if (cityList.getSelectedIndex() == 3) {
					setSelectedPlace(incheon);
				}
				if (cityList.getSelectedIndex() == 4) {
					setSelectedPlace(gwangjoo);
				}
				if (cityList.getSelectedIndex() == 5) {
					setSelectedPlace(daejeon);
				}
				if (cityList.getSelectedIndex() == 6) {
					setSelectedPlace(ulsan);
				}
				if (cityList.getSelectedIndex() == 7) {
					setSelectedPlace(kyunggi);
				}
				if (cityList.getSelectedIndex() == 8) {
					setSelectedPlace(kangwon);
				}
				if (cityList.getSelectedIndex() == 9) {
					setSelectedPlace(choongchungnam);
				}
				if (cityList.getSelectedIndex() == 10) {
					setSelectedPlace(choongchungbuk);
				}
				if (cityList.getSelectedIndex() == 11) {
					setSelectedPlace(jeonranam);
				}
				if (cityList.getSelectedIndex() == 12) {
					setSelectedPlace(jeonrabuk);
				}
				if (cityList.getSelectedIndex() == 13) {
					setSelectedPlace(kyungsangnam);
				}
				if (cityList.getSelectedIndex() == 14) {
					setSelectedPlace(kyungsangbuk);
				}
				if (cityList.getSelectedIndex() == 15) {
					setSelectedPlace(jeju);
				}
			}
		});
		panelB.setLayout(null);
		panelB.add(cityList);
		panelA.setLayout(null);
		JLabel label = new JLabel("병원 검색 앱");
		label.setFont(new Font("문체부 쓰기 정체", Font.PLAIN, 19));
		label.setForeground(Color.DARK_GRAY);
		label.setBackground(Color.PINK);
		label.setBounds(228, 10, 193, 19);
		panelA.add(label);

		/* panel에 panelA, panelB 추가 */
		panel.setLayout(null);
		panel.add(panelA);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(303, 110, 17, 48);
		panelA.add(scrollBar);
		panel.add(panelB);

		Label label_1 = new Label("Select City");
		label_1.setFont(new Font("문체부 궁체 흘림체", Font.PLAIN, 13));
		label_1.setBackground(UIManager.getColor("Button.light"));
		label_1.setBounds(58, 12, 87, 22);
		panelB.add(label_1);
		Label label_2 = new Label("Select Town");
		label_2.setFont(new Font("문체부 쓰기 흘림체", Font.PLAIN, 13));
		label_2.setBounds(231, 12, 87, 22);
		panelB.add(label_2);
		label_2.setBackground(UIManager.getColor("Button.light"));

		townList = new JList();

		townList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		townList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

			}
		});

		townList.setBorder(new LineBorder(new Color(0, 0, 0)));
		townList.setBounds(221, 42, 97, 432);
		panelB.add(townList);
		JButton btnNewButton = new JButton("결과");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(402, 66, 110, 104);
		panelB.add(btnNewButton);
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnNewButton.setBackground(Color.DARK_GRAY);

		JButton button = new JButton("채팅방");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(396, 293, 116, 104);
		panelB.add(button);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cityList.getSelectedValue() != null)
					selectedCity = cityList.getSelectedValue().toString();

				if (townList.getSelectedValue() != null)
					selectedTown = townList.getSelectedValue().toString();
				new newWindow();
			}
		});

		//채팅방 클릭하면 채팅방 클라이언트접속
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket socket=null;
				ClientFrame cf;
				try{
					socket=new Socket("127.0.0.1",3000);
					System.out.println("연결성공");
					cf = new ClientFrame(socket);
					new ReadThread(socket, cf).start();
				}catch(IOException ie){
					System.out.println(ie.getMessage());
				}
			}
		});
		
		getContentPane().add(panel);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("지도 검색");
		JMenuItem frame = new JMenuItem("Open Map");
		mnNewMenu.add(frame);

		frame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new newmap();
			}
		});

		menuBar.add(mnNewMenu);
		setVisible(true);

	}

	void setSelectedPlace(String[] cityName) {
		Vector vec = new Vector();
		for (int i = 0; i < cityName.length; i++)
			vec.addElement(cityName[i]);
		townList.setListData(vec);
	}

	private static void setTableData(String citiesName, String townsName) { // 테이블에 들어갈 데이터 Set
		for (HospitalDB temp_data : HospitalParsing.hosData) { // hosData의 객체를 하나씩 읽으며
			if (temp_data.getDutyAddr().contains(citiesName)) {
				if (townsName == null) {
					tableData.add(new String[] { temp_data.getDutyName(), temp_data.getDutyEmclsName(), // 배열 내에 필요한
																										// 데이터들을 할당
							temp_data.getDutyAddr(), temp_data.getDutyTel1() });
					System.out.println(townsName);

				} else {
					if (temp_data.getDutyAddr().contains(String.valueOf(townsName))) {
						tableData.add(new String[] { temp_data.getDutyName(), temp_data.getDutyEmclsName(), // 배열 내에 필요한
																											// 데이터들을 할당
								temp_data.getDutyAddr(), temp_data.getDutyTel1() });
					}
				}
			}
		}
	}

	
	class newWindow extends JFrame {
		newWindow() {
			setTableData(selectedCity, selectedTown);
			new CreatJTable(tableData);
		}
	}

	class newmap extends JFrame {
		newmap() {
			new Main();
		}
	}
	
	class EchoThread extends Thread{
		Socket socket;
		Vector<Socket> vec;
		public EchoThread(Socket socket, Vector<Socket> vec){
			this.socket = socket;
			this.vec = vec;
		}
	}
	class ReadThread extends Thread{
		Socket socket;
		ClientFrame cf;
		public ReadThread(Socket socket, ClientFrame cf) {
			this.cf = cf;
			this.socket=socket;
	}
}	
}
