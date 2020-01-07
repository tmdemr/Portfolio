package GUI;

/* author : 이동현 
*  
*  2019-05-16 최종수정
*/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import command.Login;
import command.Users;
import utill.SHA256;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class SetPWDGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;

	public SetPWDGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("비밀번호 재설정");
		setBounds(100, 100, 600, 540);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("새로운 비밀번호 설정");
		lblNewLabel.setForeground(new Color(255, 165, 0));
		lblNewLabel.setFont(new Font("HY목각파임B", Font.PLAIN, 25));
		lblNewLabel.setBounds(171, 75, 252, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("변경할 비밀번호");
		lblNewLabel_1.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(116, 240, 150, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("비밀번호 확인");
		lblNewLabel_2.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(116, 297, 138, 21);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("궁서", Font.PLAIN, 18));
		passwordField.setBounds(287, 237, 189, 27);
		contentPane.add(passwordField);
		
		passwordField2 = new JPasswordField();
		passwordField2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		passwordField2.setBounds(287, 294, 189, 27);
		contentPane.add(passwordField2);
		
		JButton btnNewButton = new JButton("처음으로");
		btnNewButton.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		btnNewButton.addActionListener(e-> {
			dispose();
			}
		);
		btnNewButton.setBounds(297, 377, 183, 29);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("변경하기");
		button.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		button.addActionListener(e -> {
			
			if(passwordField.getText().isEmpty() == false && passwordField2.getText().isEmpty() == false) {
			
			if(passwordField.getText().contentEquals(passwordField2.getText())) {
				Login.SetPWD(SHA256.getHash(passwordField.getText()),FindPWDGUI.getid());
				dispose();
				
				
			}
			else {
				JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
			}
		
			}else {
				JOptionPane.showMessageDialog(null,"공백이 존재합니다.");
			}
			}
			
		
		);
		button.setBounds(116, 377, 183, 29);
		contentPane.add(button);
	}
}
