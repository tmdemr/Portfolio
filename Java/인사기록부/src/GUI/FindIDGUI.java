package GUI;

/* author : 이동현 
*  아이디 찾기 버튼을 눌렀을때 생성되는 GUI
*  2019-05-16 최종수정
*/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import command.Login;
import command.Users;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class FindIDGUI extends JFrame {

	private JPanel contentPane;
	private JTextField phoneField;
	private JTextField phoneField2;
	private JTextField phoneField3;
	private JTextField answerField;
	private String range = "^[a-zA-Z0-9]*$";
	

	public FindIDGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 540);
		setTitle("아이디 찾기");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		phoneField.setBounds(253, 191, 63, 27);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		phoneField2 = new JTextField();
		phoneField2.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		phoneField2.setColumns(10);
		phoneField2.setBounds(344, 191, 63, 27);
		contentPane.add(phoneField2);
		
		phoneField3 = new JTextField();
		phoneField3.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		phoneField3.setColumns(10);
		phoneField3.setBounds(433, 191, 63, 27);
		contentPane.add(phoneField3);
		
		JLabel lblNewLabel = new JLabel("- 회원정보에 등록한 정보로 찾기 -");
		lblNewLabel.setFont(new Font("HY목각파임B", Font.PLAIN, 25));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(94, 88, 415, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("휴대폰 번호");
		lblNewLabel_1.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(107, 194, 108, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Question");
		lblNewLabel_2.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(107, 257, 129, 21);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		comboBox.setBounds(253, 254, 243, 27);
		contentPane.add(comboBox);
		comboBox.addItem(new String("본인의 출생지는?"));
		comboBox.addItem(new String("가장 감명깊게 본 영화는?"));
		
		JLabel label = new JLabel("-");
		label.setBounds(326, 194, 14, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("-");
		label_1.setBounds(415, 194, 14, 21);
		contentPane.add(label_1);
		
		JLabel lblNewLabel_3 = new JLabel("Answer");
		lblNewLabel_3.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(107, 313, 87, 21);
		contentPane.add(lblNewLabel_3);
		
		answerField = new JTextField();
		answerField.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		answerField.setBounds(251, 310, 245, 27);
		contentPane.add(answerField);
		answerField.setColumns(10);
		
		JButton btnNewButton = new JButton("검색하기");
		btnNewButton.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		btnNewButton.setBounds(315, 382, 181, 29);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("뒤로가기");
		button.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		button.addActionListener(e-> {
			dispose();
			}
		);
		
		button.setBounds(107, 382, 181, 29);
		contentPane.add(button);
		btnNewButton.addActionListener(e-> {
			
			if(comboBox.getSelectedItem().toString().equals("본인의 출생지는?") && answerField.getText().contains(" ") != true && answerField.getText().isEmpty() != true) {
				
				Login.FindID(phoneField.getText()+phoneField2.getText()+phoneField3.getText(),answerField.getText(),"is null");
				
			}
			
			else if(comboBox.getSelectedItem().toString().equals("가장 감명깊게 본 영화는?") && answerField.getText().contains(" ") != true && answerField.getText().isEmpty() != true){
				Login.FindID(phoneField.getText()+phoneField2.getText()+phoneField3.getText(),"is null",answerField.getText());
			}
			else {
				JOptionPane.showMessageDialog(null,"공백이 존재합니다");
			}
				
			
			}
    );
	}
}
