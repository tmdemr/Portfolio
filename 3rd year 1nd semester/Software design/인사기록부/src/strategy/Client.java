package strategy;
/**
 * author 최시창
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import command.DeletableCommand;
import command.Delete;
import command.DeleteDisableCommand;
import command.Insert;
import command.InsertDisableCommand;
import command.InsertableCommand;
import command.Invoker;
import command.Login;
import command.Search;
import command.SearchAllDataCommand;
import command.SearchRestrictDataCommand;
import command.SearchRestrictDataCommand2;
import command.UpdatableCommand;
import command.Update;
import command.UpdateDisableCommand;
import command.Users;
import memento.User;

/**
 * Launch the application.
 */

public abstract class Client extends JFrame {
	
	private JPanel contentPane;
	private static JTable table;
	private JComboBox<String> comboBoxName;
	private JList<String> listName;
	private JComboBox comboBoxSelect;
	private JLabel lblClock;
	

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	private JLabel lblNewLabel;
	private JLabel lblID;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblAge;
	private static JTextField textFieldEID;
	private static JTextField textFieldName;
	private static JTextField textFieldSurname;
	private static JTextField textFieldAge;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextField textFieldSearch;
	private JButton btnSearch;
	private Connection conn =null;
	
	Invoker invoker = new Invoker();
	Search search = new Search();
	Delete delete = new Delete();
	Insert insert = new Insert();
	Update update = new Update();
	
	SearchAllDataCommand searchalldatacommand = new SearchAllDataCommand(search);
	SearchRestrictDataCommand searchrestrictdatacommand = new SearchRestrictDataCommand(search);
	SearchRestrictDataCommand2 searchrestrictdatacommand2 = new SearchRestrictDataCommand2(search);
	DeletableCommand deletablecommand = new DeletableCommand(delete);
	DeleteDisableCommand deletedisablecommand = new DeleteDisableCommand(delete);
	InsertableCommand insertablecommand = new InsertableCommand(insert);
	InsertDisableCommand insertdisablecommand = new InsertDisableCommand(insert);
	UpdatableCommand updatablecommand = new UpdatableCommand(update);
	UpdateDisableCommand updatedisablecommand = new UpdateDisableCommand(update);
	
	
	Function func = new Function();
	ChangeFont chfont;
	ChangeBackground chback;
	
	public Client() {
		
		Users test = Login.returnUser();
		
		
		if(test.getLEV() == 1) {
			invoker.SetCommander(0,searchrestrictdatacommand2);
			invoker.SetCommander(1,deletedisablecommand);
			invoker.SetCommander(2, insertdisablecommand);
			invoker.SetCommander(3, updatedisablecommand);
		}
		else if(test.getLEV() == 2) {
			invoker.SetCommander(0,searchrestrictdatacommand);
			invoker.SetCommander(1,deletedisablecommand);
			invoker.SetCommander(2, insertdisablecommand);
			invoker.SetCommander(3, updatablecommand);
			
		}
		else if(test.getLEV() == 3) {
			invoker.SetCommander(0,searchalldatacommand);
			invoker.SetCommander(1,deletablecommand);
			invoker.SetCommander(2, insertablecommand);
			invoker.SetCommander(3, updatablecommand);
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadTable = new JButton("Load Data");
		btnLoadTable.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLoadTable.setForeground(new Color(30, 144, 255));
		btnLoadTable.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				invoker.ButtonWasPressed(0);
				
				
			}
		});
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//Search();
			}
		});
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				func.connect();
				func.Search(comboBoxSelect, textFieldSearch, table);
			}
		});
		
		JButton btnRollback = new JButton("RollBack");
		btnRollback.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnRollback.setBounds(699, 82, 105, 27);
		contentPane.add(btnRollback);
		
		btnRollback.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					try {
						User.poped();
						Function.refreshTable(table);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
		lblClock = new JLabel("");
		lblClock.setBounds(10, 367, 220, 44);
		contentPane.add(lblClock);
		
		comboBoxSelect = new JComboBox();
		comboBoxSelect.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"EID", "Name", "Surname", "Age"}));
		comboBoxSelect.setBounds(354, 85, 96, 23);
		contentPane.add(comboBoxSelect);
		btnSearch.setBounds(592, 85, 84, 23);
		contentPane.add(btnSearch);
		textFieldSearch.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldSearch.setBackground(Color.GREEN);
		textFieldSearch.setForeground(Color.BLACK);
		textFieldSearch.setBounds(465, 86, 117, 22);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 282, 220, 69);
		contentPane.add(scrollPane_1);
		
		listName = new JList<String>();
		listName.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				func.connect();
				func.refreshTable(table);
			}
		});
		
		listName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane_1.setViewportView(listName);

		btnLoadTable.setBounds(240, 84, 104, 24);
		contentPane.add(btnLoadTable);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 117, 436, 192);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
			func.connect();
			func.MouseCliked(textFieldEID, textFieldName, textFieldSurname, textFieldAge, table);
			}
		});
	
		lblNewLabel = new JLabel("Employee Information System");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(239, 25, 383, 38);
		contentPane.add(lblNewLabel);
				
		lblID = new JLabel("EID");
		lblID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblID.setBounds(10, 149, 65, 31);
		contentPane.add(lblID);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblName.setBounds(10, 179, 65, 31);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSurname.setBounds(10, 208, 65, 31);
		contentPane.add(lblSurname);
		
		lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAge.setBounds(10, 236, 65, 31);
		contentPane.add(lblAge);
		
		textFieldEID = new JTextField();
		textFieldEID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldEID.setBounds(75, 154, 157, 22);
		contentPane.add(textFieldEID);
		textFieldEID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldName.setBounds(76, 184, 156, 22);
		contentPane.add(textFieldName); 
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldSurname.setBounds(76, 213, 156, 22);
		contentPane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textFieldAge.setBounds(76, 240, 156, 22);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setBackground(Color.YELLOW);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				invoker.ButtonWasPressed(2);
				
			}
		});
		
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSave.setBounds(354, 320, 96, 31);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				invoker.ButtonWasPressed(3);
				
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnUpdate.setBounds(586, 320, 91, 31);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.RED);
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			User.pushed(textFieldEID);
			invoker.ButtonWasPressed(1);
			}
		});
		
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelete.setBounds(470, 320, 96, 31);
		contentPane.add(btnDelete);
		
		comboBoxName = new JComboBox<String>();
		comboBoxName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			func.connect();
			func.GetComboBoxData(comboBoxName, textFieldEID, textFieldName, textFieldSurname, textFieldAge, table);
			}
		});
		
		comboBoxName.setBounds(10, 121, 220, 21);
		contentPane.add(comboBoxName);
		
		JButton btnReset = new JButton("New");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(Color.BLUE);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				func.New(textFieldEID, textFieldName, textFieldSurname, textFieldAge);
			}
		});
		
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnReset.setBounds(240, 320, 96, 31);
		contentPane.add(btnReset);
		
		
		String filePath = new File("").getAbsolutePath();
		String imagepath =filePath.concat("\\img\\Sky.jpg");
		JLabel lblNewLabel_1 = new JLabel("");
		Image img = new ImageIcon((imagepath)).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(0, 0, 836, 411);
		contentPane.add(lblNewLabel_1);
		
		func.connect();
		
		func.fillComboBox(comboBoxName);;
		func.LoadList(listName);
	
		func.Clock(lblClock);
		
		JButton btnOption = new JButton("Option");
		btnOption.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnOption.setBounds(699, 34, 105, 27);
		contentPane.add(btnOption);
		
		JButton btnGrant = new JButton("Grant");
		btnGrant.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnGrant.setBounds(699, 132, 105, 27);
		contentPane.add(btnGrant);
		

		btnOption.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					try {
						Option frame = new Option();
						frame.setVisible(true);
						dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		

	}
	
	
public Client(String font, String back) {
	
	Users test = Login.returnUser();
	
	
	if(test.getLEV() == 1) {
	invoker.SetCommander(0,searchrestrictdatacommand2);
	invoker.SetCommander(1,deletedisablecommand);
	invoker.SetCommander(2, insertdisablecommand);
	invoker.SetCommander(3, updatedisablecommand);
	}
	else if(test.getLEV() == 2) {
	invoker.SetCommander(0,searchrestrictdatacommand);
	invoker.SetCommander(1,deletedisablecommand);
	invoker.SetCommander(2, insertdisablecommand);
	invoker.SetCommander(3, updatablecommand);
	
	}
	else if(test.getLEV() == 3) {
	invoker.SetCommander(0,searchalldatacommand);
	invoker.SetCommander(1,deletablecommand);
	invoker.SetCommander(2, insertablecommand);
	invoker.SetCommander(3, updatablecommand);
	}
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadTable = new JButton("Load Data");
		btnLoadTable.setFont(new Font(font, Font.BOLD, 12));
		btnLoadTable.setForeground(new Color(30, 144, 255));
		btnLoadTable.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				invoker.ButtonWasPressed(0);
			}
		});
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//Search();
			}
		});
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font(font, Font.PLAIN, 12));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				func.connect();
				func.Search(comboBoxSelect, textFieldSearch, table);
			}
		});
		
		lblClock = new JLabel("");
		lblClock.setBounds(10, 367, 220, 44);
		contentPane.add(lblClock);
		
		comboBoxSelect = new JComboBox();
		comboBoxSelect.setFont(new Font(font, Font.PLAIN, 12));
		comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"EID", "Name", "Surname", "Age"}));
		comboBoxSelect.setBounds(354, 85, 96, 23);
		contentPane.add(comboBoxSelect);
		btnSearch.setBounds(592, 85, 84, 23);
		contentPane.add(btnSearch);
		textFieldSearch.setFont(new Font(font, Font.PLAIN, 12));
		textFieldSearch.setBackground(Color.GREEN);
		textFieldSearch.setForeground(Color.BLACK);
		textFieldSearch.setBounds(465, 86, 117, 22);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 282, 220, 69);
		contentPane.add(scrollPane_1);
		
		listName = new JList<String>();
		listName.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				func.connect();
				func.refreshTable(table);
			}
		});
		
		listName.setFont(new Font(font, Font.PLAIN, 12));
		scrollPane_1.setViewportView(listName);

		btnLoadTable.setBounds(240, 84, 104, 24);
		contentPane.add(btnLoadTable);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 117, 436, 192);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
			func.connect();
			func.MouseCliked(textFieldEID, textFieldName, textFieldSurname, textFieldAge, table);
			}
		});
	
		lblNewLabel = new JLabel("Employee Information System");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font(font, Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(239, 25, 383, 38);
		contentPane.add(lblNewLabel);
				
		lblID = new JLabel("EID");
		lblID.setFont(new Font(font, Font.BOLD, 14));
		lblID.setBounds(10, 149, 65, 31);
		contentPane.add(lblID);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font(font, Font.BOLD, 14));
		lblName.setBounds(10, 179, 65, 31);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font(font, Font.BOLD, 14));
		lblSurname.setBounds(10, 208, 65, 31);
		contentPane.add(lblSurname);
		
		lblAge = new JLabel("Age");
		lblAge.setFont(new Font(font, Font.BOLD, 14));
		lblAge.setBounds(10, 236, 65, 31);
		contentPane.add(lblAge);
		
		textFieldEID = new JTextField();
		textFieldEID.setFont(new Font(font, Font.PLAIN, 12));
		textFieldEID.setBounds(75, 154, 157, 22);
		contentPane.add(textFieldEID);
		textFieldEID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font(font, Font.PLAIN, 12));
		textFieldName.setBounds(76, 184, 156, 22);
		contentPane.add(textFieldName); 
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font(font, Font.PLAIN, 12));
		textFieldSurname.setBounds(76, 213, 156, 22);
		contentPane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font(font, Font.PLAIN, 12));
		textFieldAge.setBounds(76, 240, 156, 22);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setBackground(Color.YELLOW);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				invoker.ButtonWasPressed(2);
			}
		});
		
		btnSave.setFont(new Font(font, Font.BOLD, 18));
		btnSave.setBounds(354, 320, 96, 31);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				invoker.ButtonWasPressed(3);
			}
		});
		btnUpdate.setFont(new Font(font, Font.BOLD, 18));
		btnUpdate.setBounds(586, 320, 91, 31);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.RED);
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			invoker.ButtonWasPressed(1);
			}
		});
		
		btnDelete.setFont(new Font(font, Font.BOLD, 18));
		btnDelete.setBounds(470, 320, 96, 31);
		contentPane.add(btnDelete);
		
		comboBoxName = new JComboBox<String>();
		comboBoxName.setFont(new Font(font, Font.PLAIN, 12));
		comboBoxName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			func.connect();
			func.GetComboBoxData(comboBoxName, textFieldEID, textFieldName, textFieldSurname, textFieldAge, table);
			}
		});
		
		comboBoxName.setBounds(10, 121, 220, 21);
		contentPane.add(comboBoxName);
		
		JButton btnReset = new JButton("New");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(Color.BLUE);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				func.New(textFieldEID, textFieldName, textFieldSurname, textFieldAge);
			}
		});
		
		btnReset.setFont(new Font(font, Font.BOLD, 18));
		btnReset.setBounds(240, 320, 96, 31);
		contentPane.add(btnReset);
		
		String filePath = new File("").getAbsolutePath();
		String imagepath =filePath.concat("\\img\\" + back +".jpg");
		System.out.println(imagepath);
		JLabel lblNewLabel_1 = new JLabel("");
		Image img = new ImageIcon((imagepath)).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(0, 0, 836, 411);
		contentPane.add(lblNewLabel_1);
		
		func.connect();
		
		func.fillComboBox(comboBoxName);;
		func.LoadList(listName);
	
		func.Clock(lblClock);
		
		JButton btnOption = new JButton("Option");
		btnOption.setFont(new Font(font, Font.PLAIN, 15));
		btnOption.setBounds(699, 34, 105, 27);
		contentPane.add(btnOption);
		
		btnOption.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					try {
						Option frame = new Option();
						frame.setVisible(true);
						dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
		JButton btnRollback = new JButton("RollBack");
		btnRollback.setFont(new Font(font, Font.PLAIN, 15));
		btnRollback.setBounds(699, 82, 105, 27);
		contentPane.add(btnRollback);
		
		btnRollback.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					try {
						User.poped();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		

	}
	
	public static JTable getTable() {
	return table;
		
	}

	public static JTextField getTextField() {
	return textFieldEID;
		
	}
	
	public static JTextField getTextField2() {
		return textFieldName;
		
	}
	
	public static JTextField getTextField3() {
		return textFieldSurname;
		
	}
	
	public static JTextField getTextField4() {
		return textFieldAge;
		
	}
}

