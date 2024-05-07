package Projektmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;

public class PMWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JLabel lblTopTitle;
	private JLabel lblFirstname;
	private JLabel lblPerson;
	private JLabel lblLastname;
	private JLabel lblEmail;
	private JTextField tfEmail;
	private JLabel lblPhone;
	private JTextField tfPhone;
	private JLabel lblFax;
	private JComboBox<Object> cbSex;
	private JTextField tfUsername;
	private JLabel lblPassword;
	private JTextField tfPassword;
	private JLabel lblProjectName;
	private JTextArea taProjectNames;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PMWindow frame = new PMWindow();
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
	public PMWindow() {
		
		initWindow();
	}
	
	private void initWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		splitPane.setResizeWeight(0.5);
		splitPane.setPreferredSize(new Dimension(800, 800));
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane);
		
		topPanel = new JPanel();
		topPanel.setMaximumSize(new Dimension(800, 250));
		splitPane.setLeftComponent(topPanel);
		
		lblTopTitle = new JLabel("Form");
		lblTopTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		lblFirstname = new JLabel("Firstname:");
		lblFirstname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JTextField tfFirstname = new JTextField();
		tfFirstname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfFirstname.setColumns(10);
		
		lblPerson = new JLabel("Person");
		lblPerson.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JTextField tfLastname = new JTextField();
		tfLastname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfLastname.setColumns(10);
		
		lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfEmail.setColumns(10);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfPhone = new JTextField();
		tfPhone.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPhone.setColumns(10);
		
		lblFax = new JLabel("Fax:");
		lblFax.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JTextField tfFax = new JTextField();
		tfFax.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfFax.setColumns(10);
		
		JLabel lblSex = new JLabel("Gender:");
		lblSex.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		String[] gender = {" ", "Male", "Female", "Divers" };
		DialogModel dialogModel = new DialogModel(gender);
		
		cbSex = new JComboBox<Object>(dialogModel);
		cbSex.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfUsername.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPassword.setColumns(10);
		
		lblProjectName = new JLabel("Projects: ");
		lblProjectName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		taProjectNames = new JTextArea();
		taProjectNames.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(373)
					.addComponent(lblTopTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(379))
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(171)
					.addComponent(lblPerson, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
					.addGap(581))
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblFirstname, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastname, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFax, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSex)
						.addComponent(lblUsername)
						.addComponent(lblPassword)
						.addComponent(lblProjectName))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(taProjectNames, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(tfUsername, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(cbSex, 0, 247, Short.MAX_VALUE)
						.addComponent(tfFax, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(tfPhone, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(tfLastname, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(tfFirstname, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
					.addGap(430))
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_topPanel.createSequentialGroup()
					.addComponent(lblTopTitle)
					.addGap(11)
					.addComponent(lblPerson, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfFirstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblFirstname, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastname)
						.addComponent(tfLastname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone)
						.addComponent(tfPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFax)
						.addComponent(tfFax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSex)
						.addComponent(cbSex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(tfUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProjectName)
						.addComponent(taProjectNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(452))
		);
		topPanel.setLayout(gl_topPanel);
		
		bottomPanel = new JPanel();
		splitPane.setRightComponent(bottomPanel);
		
		btnNewButton = new JButton("New button");
		
		btnNewButton_1 = new JButton("New button");
		
		table = new JTable();
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_bottomPanel.createSequentialGroup()
							.addGap(289)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1))
						.addGroup(gl_bottomPanel.createSequentialGroup()
							.addGap(166)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		bottomPanel.setLayout(gl_bottomPanel);
	}
}

class DialogModel extends DefaultComboBoxModel<Object> 
{
	
	public DialogModel(Object[] obj) 
	{
		super(obj);
	}
	
	public void sort() 
	{
		ArrayList<Object> list = new ArrayList<Object>();
		int i = 0;
		while(super.getElementAt(i) != null) 
		{
			list.add(super.getElementAt(i));
			i++;
		}
		Object[] objs = (Object[]) list.toArray(new Object[0]);
		Arrays.sort(objs);
		super.removeAllElements();
		for(int j = 0; j < objs.length; j++) 
		{
			super.addElement(objs[j]);
			
		}
	}
}
