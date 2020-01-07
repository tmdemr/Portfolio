package GUI;

import java.awt.Color;
import java.awt.Font;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import command.Login;
import command.Users;
import utill.SHA256;
import java.awt.SystemColor;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public LoginGUI() {
		setForeground(new Color(0, 0, 0));
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 540);
		setTitle("인사데이터 관리 프로그램");
		contentPane = new JPanel();
		//ImageIcon img = new ImageIcon("C:\\Users\\이동현\\Desktop\\TeamProject\\img\\background.jpg");
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		textField.setBounds(129, 219, 390, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		passwordField.setBounds(129, 272, 390, 27);
		contentPane.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("아이디 찾기");
		btnNewButton_1.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		btnNewButton_1.setBounds(73, 377, 147, 29);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               FindIDGUI a = new FindIDGUI();
               a.setVisible(true);
            }
        });
		
		JLabel label = new JLabel("- 인사 데이터 관리 프로그램 ver 1.0 -");
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setFont(new Font("HY목각파임B", Font.PLAIN, 23));
		label.setBounds(94, 38, 433, 82);
		contentPane.add(label);
		
		JButton btnNewButton_2 = new JButton("비밀번호 찾기");
		btnNewButton_2.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		btnNewButton_2.setBounds(220, 377, 152, 29);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(e-> {
               FindPWDGUI a = new FindPWDGUI();
               a.setVisible(true);
            }
        );
		
		JLabel lblId = new JLabel("ID");
		lblId.setBackground(new Color(0, 0, 0));
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		lblId.setBounds(73, 222, 39, 21);
		contentPane.add(lblId);
		
		JLabel label_1 = new JLabel("PWD");
		label_1.setBackground(new Color(0, 0, 0));
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		label_1.setBounds(73, 275, 45, 21);
		contentPane.add(label_1);
		
		JButton button = new JButton("회원가입");
		button.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		button.setBounds(372, 377, 147, 29);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("HY목각파임B", Font.PLAIN, 18));
		btnNewButton.setBounds(73, 333, 446, 29);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(e-> {
               Login.login(textField.getText().toUpperCase(), SHA256.getHash(passwordField.getText()));
               
		}
        );
		
		/*JLabel jlabel = new JLabel();
		jlabel.setForeground(Color.WHITE);
		jlabel.setIcon(img);
		jlabel.setBounds(0, 0, 578, 484);
		contentPane.add(jlabel);*/
		
		
		
		
		
		button.addActionListener(e-> {
            	JoinGUI test = new JoinGUI();
            }
        );
		
		setVisible(true);
	}
}
