package GUI;

/* author : 이동현 
*  회원가입 버튼을 눌렀을때 회원가입에 필요한 정보들을 입력하기 위한 GUI
*  2019-05-16 최종수정
*/

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import command.Login;
import command.Users;
import utill.SHA256;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class JoinGUI extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField passwordField;
	private JTextField phoneField;
	private JTextField phoneField2;
	private JTextField phoneField3;
	private JTextField answerField;
	private String answer = null;
	private String range = "^[0-9]+$";
	

	public JoinGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 540);
		setTitle("회원가입");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idlabel = new JLabel("ID");
		idlabel.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		idlabel.setBounds(55, 194, 78, 21);
		contentPane.add(idlabel);
		
		JLabel pwdlabel = new JLabel("PWD");
		pwdlabel.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		pwdlabel.setBounds(55, 230, 78, 21);
		contentPane.add(pwdlabel);
		
		JLabel phonelabel = new JLabel("PHONE");
		phonelabel.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		phonelabel.setBounds(55, 266, 78, 21);
		contentPane.add(phonelabel);
		
		JLabel questionlabel = new JLabel("Question");
		questionlabel.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		questionlabel.setBounds(55, 302, 122, 21);
		contentPane.add(questionlabel);
		
		JLabel answerlabel = new JLabel("Answer");
		answerlabel.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		answerlabel.setBounds(55, 338, 85, 21);
		contentPane.add(answerlabel);
		
		idField = new JTextField();
		idField.setBounds(172, 194, 243, 27);
		contentPane.add(idField);
		idField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 227, 243, 27);
		contentPane.add(passwordField);
		
		phoneField = new JTextField();
		phoneField.setBounds(172, 266, 63, 27);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		phoneField2 = new JTextField();
		phoneField2.setColumns(10);
		phoneField2.setBounds(264, 266, 63, 27);
		contentPane.add(phoneField2);
		
		phoneField3 = new JTextField();
		phoneField3.setColumns(10);
		phoneField3.setBounds(352, 266, 63, 27);
		contentPane.add(phoneField3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		comboBox.setBounds(172, 302, 243, 27);
		contentPane.add(comboBox);
		comboBox.addItem(new String("본인의 출생지는?"));
		comboBox.addItem(new String("가장 감명깊게 본 영화는?"));
		
		JButton joinButton = new JButton("회원가입");
		joinButton.setForeground(new Color(0, 0, 0));
		joinButton.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		joinButton.setBounds(293, 398, 233, 29);
		contentPane.add(joinButton);
		joinButton.setEnabled(false);
		joinButton.addActionListener(e->{
            	
			if(Login.CheckNullData(passwordField.getText(),phoneField.getText(),answerField.getText()) == true) {
				
				String a = (phoneField.getText()+phoneField2.getText()+phoneField3.getText());
				
				if(a.matches(range)) {
				
				if(comboBox.getSelectedItem().toString().equals("본인의 출생지는?")) {
            		Login.join(idField.getText().toUpperCase(),SHA256.getHash((passwordField.getText())),(phoneField.getText())+(phoneField2.getText())+(phoneField3.getText()),answerField.getText(),null);
            		dispose();
            	}
            	else {
            		Login.join(idField.getText().toUpperCase(),SHA256.getHash((passwordField.getText())),(phoneField.getText())+(phoneField2.getText())+(phoneField3.getText()),null,answerField.getText());
            		dispose();
            	}
				}else {
					JOptionPane.showMessageDialog(null,"휴대폰 번호에 잘못된 값이 입력되었습니다!");
				}
				
				
			}else {
				JOptionPane.showMessageDialog(null,"빈칸이 존재 합니다!");
			}
			
        });
		
		JLabel label_4 = new JLabel("-");
		label_4.setBounds(242, 269, 24, 21);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("-");
		label_5.setBounds(331, 269, 24, 21);
		contentPane.add(label_5);
		
		JButton checkbutton = new JButton("중복확인");
		checkbutton.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		checkbutton.setBounds(432, 194, 116, 29);
		contentPane.add(checkbutton);
		checkbutton.addActionListener(e->{
         
            	if(Login.DuplicateCheck(idField.getText().toUpperCase()) == true) {
            		          		
            		if((idField.getText().contains(" "))) {
            			JOptionPane.showMessageDialog(null,"아이디에 공백이 존재합니다!");
            		}
            		else if(idField.getText().isEmpty()) {
            			JOptionPane.showMessageDialog(null,"아이디를 입력해 주세요!");
            		}
            		else {
            		JOptionPane.showMessageDialog(null,"사용할 수 있는 아이디 입니다!");
            		joinButton.setEnabled(true);
            		}
            	}
            	
            });
		
	
		
		answerField = new JTextField();
		answerField.setBounds(172, 338, 246, 27);
		contentPane.add(answerField);
		answerField.setColumns(10);
		
		JButton backButton = new JButton("돌아가기");
		backButton.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		backButton.setBounds(55, 398, 236, 29);
		contentPane.add(backButton);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("HY목각파임B", Font.PLAIN, 25));
		lblNewLabel.setBounds(234, 62, 108, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("(대소문자 구분)");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(432, 230, 129, 21);
		contentPane.add(lblNewLabel_1);
		backButton.addActionListener(e-> {
            	dispose();
            
        });
		
		setVisible(true);
	}
}
