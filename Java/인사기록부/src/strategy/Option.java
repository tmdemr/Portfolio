package strategy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Option extends JFrame {

	private JPanel contentPane;
	static String isfont;
	static String isback;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Option frame = new Option();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Option() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Option");
		lblNewLabel.setFont(new Font("LG PC", Font.PLAIN, 23));
		lblNewLabel.setBounds(14, 0, 101, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblFontSeting = new JLabel("font");
		lblFontSeting.setBounds(14, 66, 89, 26);
		contentPane.add(lblFontSeting);
		
		JRadioButton rdbtnBatang = new JRadioButton("Batang");
		rdbtnBatang.setBounds(58, 66, 80, 27);
		contentPane.add(rdbtnBatang);
		
		JRadioButton rdbtnDotum = new JRadioButton("Dotum");
		rdbtnDotum.setBounds(58, 97, 139, 27);
		contentPane.add(rdbtnDotum);
		
		JRadioButton rdbtnGungsuh = new JRadioButton("Gungsuh");
		rdbtnGungsuh.setBounds(58, 128, 139, 27);
		contentPane.add(rdbtnGungsuh);
		
		
		ButtonGroup buttonGrp = new ButtonGroup();
		
		buttonGrp.add(rdbtnBatang);
		buttonGrp.add(rdbtnGungsuh);
		buttonGrp.add(rdbtnDotum);
		
		JLabel lblBackground = new JLabel("background");
		lblBackground.setBounds(148, 70, 107, 18);
		contentPane.add(lblBackground);
		
		JRadioButton rdbtnSky = new JRadioButton("Sky");
		rdbtnSky.setBounds(238, 66, 139, 27);
		contentPane.add(rdbtnSky);
		
		JRadioButton rdbtnComputer = new JRadioButton("Computer");
		rdbtnComputer.setBounds(238, 97, 139, 27);
		contentPane.add(rdbtnComputer);
		
		JRadioButton rdbtnIce = new JRadioButton("Ice");
		rdbtnIce.setBounds(238, 128, 139, 27);
		contentPane.add(rdbtnIce);
		
		ButtonGroup buttongorundGrp = new ButtonGroup();
		
		buttongorundGrp.add(rdbtnIce);
		buttongorundGrp.add(rdbtnComputer);
		buttongorundGrp.add(rdbtnSky);
		

		JButton btnApply = new JButton("Apply");
		
		
		btnApply.setBounds(150, 189, 105, 27);
		contentPane.add(btnApply);
		
		
			rdbtnBatang.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					try {
						ChangeFont font= new BatangFont();
						isfont= font.changefont();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			rdbtnDotum.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
						try {
							ChangeFont font= new DotumFont();
							isfont= font.changefont();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			rdbtnGungsuh.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
						try {
							ChangeFont font= new GungsuhFont();
							isfont= font.changefont();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			
			
			rdbtnSky.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
						try {
							ChangeBackground back= new Skyback();
							isback= back.changeBackground();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
							
			rdbtnComputer.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
						try {
							ChangeBackground back= new Computerback();
							isback= back.changeBackground();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			rdbtnIce.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
						try {
							ChangeBackground back= new Iceback();
							isback= back.changeBackground();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			
			btnApply.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//버튼기능
					Client frame = new UserClient(isfont,isback);
					frame.setVisible(true);
					dispose();
					
				}
			});
			
			
			
	}
}
