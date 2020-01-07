package GUI;

/* author : 이동현 
*  비밀번호 찾기 버튼을 눌렀을때 생성되는 GUI
*  2019-05-16 최종수정
*/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import command.Login;
import command.Users;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FindPWDGUI extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField phoneField;
	private JTextField phoneField2;
	private JTextField phoneField3;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblNewLabel_2;
	private JLabel lblAnswer;
	private JTextField answerField;
	private JButton button;
	public static String id=null;

	public static String getid() {
		return id;
		
	}
	
	public FindPWDGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 540);
		contentPane = new JPanel();
		setTitle("비밀번호 찾기");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		phoneField.setBounds(244, 228, 63, 27);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		phoneField2 = new JTextField();
		phoneField2.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		phoneField2.setColumns(10);
		phoneField2.setBounds(340, 228, 63, 27);
		contentPane.add(phoneField2);
		
		phoneField3 = new JTextField();
		phoneField3.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		phoneField3.setColumns(10);
		phoneField3.setBounds(432, 228, 63, 27);
		contentPane.add(phoneField3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		comboBox.setBounds(244, 281, 251, 27);
		contentPane.add(comboBox);
		comboBox.addItem(new String("본인의 출생지는?"));
		comboBox.addItem(new String("가장 감명깊게 본 영화는?"));
		
		JLabel lblNewLabel = new JLabel("등록한 회원정보로 비밀번호 재설정");
		lblNewLabel.setForeground(new Color(0, 206, 209));
		lblNewLabel.setFont(new Font("HY목각파임B", Font.PLAIN, 25));
		lblNewLabel.setBounds(106, 38, 419, 61);
		contentPane.add(lblNewLabel);
		
		idField = new JTextField();
		idField.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		idField.setBounds(244, 166, 251, 27);
		contentPane.add(idField);
		idField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(106, 169, 78, 21);
		contentPane.add(lblNewLabel_1);
		
		label = new JLabel("-");
		label.setBounds(317, 231, 15, 21);
		contentPane.add(label);
		
		label_1 = new JLabel("-");
		label_1.setBounds(410, 231, 15, 21);
		contentPane.add(label_1);
		
		label_2 = new JLabel("휴대폰 번호");
		label_2.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		label_2.setBounds(106, 231, 102, 21);
		contentPane.add(label_2);
		
		lblNewLabel_2 = new JLabel("Question");
		lblNewLabel_2.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(106, 284, 103, 21);
		contentPane.add(lblNewLabel_2);
		
		lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblAnswer.setBounds(106, 339, 90, 21);
		contentPane.add(lblAnswer);
		
		answerField = new JTextField();
		answerField.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		answerField.setBounds(244, 336, 251, 27);
		contentPane.add(answerField);
		answerField.setColumns(10);
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		btnNewButton.addActionListener(e-> {
			
			
			if(comboBox.getSelectedItem().toString().equals("본인의 출생지는?")&& answerField.getText().contains(" ") != true && answerField.getText().isEmpty() != true) {
			Login.FindPWD(idField.getText().toUpperCase(),phoneField.getText() + phoneField2.getText() + phoneField3.getText(),answerField.getText(),"is null");
			id = idField.getText().toUpperCase();
			
			if(Login.FindPWD(idField.getText().toUpperCase(),phoneField.getText() + phoneField2.getText() + phoneField3.getText(),answerField.getText(),"is null") ==true ) {
				JOptionPane.showMessageDialog(null,"본인확인에 성공하였습니다.");
				SetPWDGUI a = new SetPWDGUI();
				a.setVisible(true);
				dispose();
			}else {
				JOptionPane.showMessageDialog(null,"일치하는 정보가 없습니다.");
			}
			
			
			
			}else if(comboBox.getSelectedItem().toString().equals("가장 감명깊게 본 영화는?")&& answerField.getText().contains(" ") != true && answerField.getText().isEmpty() != true) {
				Login.FindPWD(idField.getText().toUpperCase(),phoneField.getText() + phoneField2.getText() + phoneField3.getText(),"is null",answerField.getText());	
				id = idField.getText().toUpperCase();
				
			 if(Login.FindPWD(idField.getText().toUpperCase(),phoneField.getText() + phoneField2.getText() + phoneField3.getText(),"is null",answerField.getText())== true){
				JOptionPane.showMessageDialog(null,"본인확인에 성공하였습니다.");
				SetPWDGUI a = new SetPWDGUI();
				a.setVisible(true);
				 dispose();
			 }else {
				 JOptionPane.showMessageDialog(null,"일치하는 정보가 없습니다.");
			 }
				
				
			}
			
			else {
				JOptionPane.showMessageDialog(null,"공백이 존재합니다");
			}
			
			
		
		}
		);
		btnNewButton.setBounds(301, 396, 194, 29);
		contentPane.add(btnNewButton);
		
		button = new JButton("뒤로가기");
		button.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		button.addActionListener(e-> {
			dispose();
			}
		);
		button.setBounds(106, 396, 194, 29);
		contentPane.add(button);
	}
}
