package project.window;

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
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import project.PMDatabase;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;

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
	private JLabel lblCollaborators;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblProject;
	private JLabel lblAcronym;
	private JTextField tfAcronym;
	private JLabel lblTitle;
	private JTextField tfTitle;
	private JLabel lblStartdate;
	private JTextField tfStartdate;
	private JLabel lblEnddate;
	private JTextField tfEnddate;
	private JButton btnCancel;
	private JTextField tfFirstname;
	private JTextField tfFax;
	private JTextField tfLastname;
	private JButton btnSave;
	private List<JTextField> tfList = new ArrayList<JTextField>();
	private JScrollPane scpDescription;
	private JScrollPane scpCollab;
	private JTextArea taCollabs;
	private JTextArea taDescription;
	private JTable tablePerson;
	private String[] personHeaders;
	private String[] projectHeaders;
	private int numPersonRows;
	private int numProjectRows;
	private DefaultTableModel modelPerson;
	private DefaultTableModel modelProject;
	private JButton btnChangeTable;
	private boolean isFirstTable = true;
	private String firstname;
	private String lastname;
	private Object sex;
	private String email;
	private String phone;
	private String fax;
	private String username;
	private String password;
	private String acronym;
	private String title;
	private String startdate;
	private String enddate;
	private String description;
	private String collaborators;
	private int projectID;
	private int personID;
	private JButton btnAddCollab;
	private boolean isMouseEventEnabled = true;


	/**
	 * The PMWindow class represents the main window of the project management application.
	 * It provides functionality for managing projects and collaborators.
	 */
	public PMWindow() {
		
		initWindow();
		changeBtn();
		try 
		{
			btnActions();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	 /**
     * Initializes all Swing components used in the PMWindow.
     */
	private void initWindow() {
		
		//Set the size of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		//setup for the splitpane
		splitPane = new JSplitPane();
		splitPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		splitPane.setResizeWeight(0.5);
		splitPane.setPreferredSize(new Dimension(800, 800));
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane);
		
		//set up for the top panel
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(32767, 16383));
		topPanel.setMinimumSize(new Dimension(32767, 16383));
		topPanel.setMaximumSize(new Dimension(32767, 16383));
		splitPane.setLeftComponent(topPanel);
		
		//Labels, Textfields, Combobox, Buttons for the top panel
		lblTopTitle = new JLabel("Form");
		lblTopTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		lblFirstname = new JLabel("Firstname:");
		lblFirstname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfFirstname = new JTextField();
		tfFirstname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfFirstname.setColumns(10);
		
		lblPerson = new JLabel("Person");
		lblPerson.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfLastname = new JTextField();
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
		
		tfFax = new JTextField();
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
		
		lblCollaborators = new JLabel("Collaborators: ");
		lblCollaborators.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		lblProject = new JLabel("Project");
		lblProject.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblAcronym = new JLabel("Acronym:");
		lblAcronym.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfAcronym = new JTextField();
		tfAcronym.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfAcronym.setColumns(10);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfTitle = new JTextField();
		tfTitle.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTitle.setColumns(10);
		
		lblStartdate = new JLabel("Starting Date:");
		lblStartdate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfStartdate = new JTextField();
		tfStartdate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfStartdate.setColumns(10);
		
		lblEnddate = new JLabel("End Date:");
		lblEnddate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		tfEnddate = new JTextField();
		tfEnddate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfEnddate.setColumns(10);
		
		//adding the textfields to a list to make iterating through them easier later on
		tfList.add(tfFirstname);
		tfList.add(tfLastname);
		tfList.add(tfEmail);
		tfList.add(tfPhone);
		tfList.add(tfFax);
		tfList.add(tfUsername);
		tfList.add(tfPassword);
		tfList.add(tfAcronym);
		tfList.add(tfTitle);
		tfList.add(tfStartdate);
		tfList.add(tfEnddate);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancel.setEnabled(false);
		
		btnSave = new JButton("Save");
		btnSave.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSave.setEnabled(false);
		
		scpCollab = new JScrollPane();
		
		scpDescription = new JScrollPane();
		
		btnAddCollab = new JButton("Add Collaborators");
		btnAddCollab.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnAddCollab.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(373)
					.addComponent(lblTopTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(379))
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(223)
					.addComponent(lblPerson, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addGap(339)
					.addComponent(lblProject)
					.addGap(133))
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLastname, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSex)
								.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFax, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername)
								.addComponent(lblPassword)
								.addComponent(lblFirstname, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(cbSex, 0, 221, Short.MAX_VALUE)
								.addComponent(tfFirstname, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(tfUsername, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(tfFax, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(tfPhone, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(tfLastname, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGap(60)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_topPanel.createSequentialGroup()
										.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblAcronym)
											.addComponent(lblStartdate)
											.addComponent(lblTitle)
											.addComponent(lblEnddate))
										.addGap(18))
									.addGroup(gl_topPanel.createSequentialGroup()
										.addComponent(lblDescription)
										.addGap(29)))
								.addComponent(lblCollaborators))
							.addGap(33)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfEnddate)
								.addComponent(tfStartdate)
								.addComponent(tfTitle)
								.addComponent(tfAcronym, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
								.addComponent(scpDescription)
								.addComponent(scpCollab))
							.addContainerGap())
						.addGroup(gl_topPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAddCollab)
							.addGap(78))))
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addComponent(lblTopTitle)
					.addGap(11)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPerson, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
						.addComponent(lblProject))
					.addGap(18)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfAcronym, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAcronym)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFirstname, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfFirstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastname)
						.addComponent(tfLastname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartdate)
						.addComponent(tfStartdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbSex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSex))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone)
						.addComponent(tfPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnddate)
						.addComponent(tfEnddate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFax)
								.addComponent(tfFax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescription))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername)
								.addComponent(tfUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scpDescription, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCollaborators)
						.addComponent(scpCollab, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnAddCollab)
						.addComponent(btnSave))
					.addGap(23))
		);
		
		taDescription = new JTextArea();
		taDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scpDescription.setViewportView(taDescription);
		
		taCollabs = new JTextArea();
		taCollabs.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scpCollab.setViewportView(taCollabs);
		topPanel.setLayout(gl_topPanel);
		
		//setup for the bottom panel with the table
		bottomPanel = new JPanel();
		bottomPanel.setMinimumSize(new Dimension(32767, 16383));
		bottomPanel.setMaximumSize(new Dimension(32767, 16383));
		splitPane.setRightComponent(bottomPanel);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		btnDelete = new JButton("Delete");
		btnDelete.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JScrollPane scpTable = new JScrollPane();
		
		btnChangeTable = new JButton("Change Table");
		btnChangeTable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnChangeTable.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_bottomPanel.createSequentialGroup()
							.addGap(289)
							.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnChangeTable))
						.addGroup(gl_bottomPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scpTable, GroupLayout.PREFERRED_SIZE, 779, GroupLayout.PREFERRED_SIZE)))
					.addGap(9))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_bottomPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDelete)
						.addComponent(btnUpdate)
						.addComponent(btnChangeTable))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scpTable, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		//setup for the two tables
		personHeaders = new String[]{"ID", "Firstname", "Lastname", "Gender", "E-Mail", "Phone", "Fax", "Username"};
		numPersonRows = 0;
		modelPerson = new DefaultTableModel(numPersonRows, personHeaders.length) 
		{
			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		modelPerson.setColumnIdentifiers(personHeaders);
		
		
		projectHeaders = new String[] {"ID", "Acronym", "Title", "Starting Date", "End Date", "Description", "Collaborators"};
		numProjectRows = 0;
		modelProject = new DefaultTableModel(numProjectRows, projectHeaders.length) 
		{
			@Override
			public boolean isCellEditable(int row, int column) 
			{
			    return false;
			}
		};
		modelProject.setColumnIdentifiers(projectHeaders);
		
		//initializing the tables from the database
		PMDatabase.fetchFromPerson(modelPerson);
		PMDatabase.fetchFromProjects(modelProject);
		
		tablePerson = new JTable(modelPerson);
		tablePerson.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scpTable.setViewportView(tablePerson);
		bottomPanel.setLayout(gl_bottomPanel);
		
		//MouseEvent for the table to fill the textfields when a row is selected
		tablePerson.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(isMouseEventEnabled) 
				{
					
					int row = tablePerson.rowAtPoint(e.getPoint());
					int col = tablePerson.columnAtPoint(e.getPoint());
					if(isFirstTable) 
					{
						if(row >= 0 && col >= 0) 
						{
							personID = (int) tablePerson.getValueAt(row, 0);
							firstname = tablePerson.getValueAt(row, 1).toString();
							lastname = tablePerson.getValueAt(row, 2).toString();
							sex = tablePerson.getValueAt(row, 3);
							email = tablePerson.getValueAt(row, 4).toString();
							phone = tablePerson.getValueAt(row, 5).toString();
							fax = tablePerson.getValueAt(row, 6).toString();
							username = tablePerson.getValueAt(row, 7).toString();						
							
							tfFirstname.setText(firstname);
							tfLastname.setText(lastname);
							cbSex.setSelectedItem(sex);
							tfEmail.setText(email);
							tfPhone.setText(phone);
							tfFax.setText(fax);
							tfUsername.setText(username);
						}
					}
					else if(!isFirstTable) 
					{
						if(row >= 0 && col >= 0) 
						{
							projectID = (int) tablePerson.getValueAt(row, 0);
							acronym = tablePerson.getValueAt(row, 1).toString();
							title = tablePerson.getValueAt(row, 2).toString();
							startdate = tablePerson.getValueAt(row, 3).toString();
							enddate = tablePerson.getValueAt(row, 4).toString();
							description = tablePerson.getValueAt(row, 5).toString();
							collaborators = tablePerson.getValueAt(row, 6).toString();
							
							tfAcronym.setText(acronym);
							tfTitle.setText(title);
							tfStartdate.setText(startdate);
							tfEnddate.setText(enddate);
							taDescription.setText(description);
							taCollabs.setText(collaborators);
						}
					}
				}
			}
		});
	}
	
	
	//Button actions
	private void btnActions() throws SQLException 
	{
		Connection conn = PMDatabase.connect();
		
		//saving the textfields to the database
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{					
				try {  
					
					PreparedStatement pstmt;
				
					if(!tfFirstname.getText().isEmpty() && !tfLastname.getText().isEmpty() && !tfEmail.getText().isEmpty() && !tfPhone.getText().isEmpty() && !tfUsername.getText().isEmpty() && !tfPassword.getText().isEmpty()) 
					{
						
							
						String firstname = tfFirstname.getText();
						String lastname = tfLastname.getText();
						String gender = (String) cbSex.getSelectedItem();
						String email = tfEmail.getText();
						String phone = tfPhone.getText();
						String fax = tfFax.getText();
						String username = tfUsername.getText();
						String password = tfPassword.getText();
						pstmt = conn.prepareStatement("INSERT INTO Person (firstname, lastname, sex, email, phone, fax, username, password) "
										+ "VALUES (?,?,?,?,?,?,?,?)");
						pstmt.setString(1, firstname);
						pstmt.setString(2, lastname);
						pstmt.setString(3, gender);
						pstmt.setString(4, email);
						pstmt.setString(5,  phone);
						pstmt.setString(6, fax);
						pstmt.setString(7, username);
						pstmt.setString(8, password);
						pstmt.execute();
						pstmt.close();
						
						PMDatabase.fetchFromPerson(modelPerson);
					} 
					else if(!tfAcronym.getText().isEmpty() && !tfTitle.getText().isEmpty() && !tfStartdate.getText().isEmpty() && !taDescription.getText().isEmpty()) 
					{
						
						String acronym = tfAcronym.getText();
						String title = tfTitle.getText();
						String startdate = tfStartdate.getText();
						String endDate = tfEnddate.getText();
						String description = taDescription.getText();
						pstmt = conn.prepareStatement("INSERT INTO Project (acronym, title, startdate, enddate, description) VALUES (?,?,?,?,?)");
						pstmt.setString(1, acronym);
						pstmt.setString(2, title);
						pstmt.setString(3, startdate);
						pstmt.setString(4,  endDate);
						pstmt.setString(5, description);
						pstmt.execute();
						pstmt.close();
						
						PMDatabase.fetchFromProjects(modelProject);

					}
					tablePerson.repaint();
					conn.close();
					resetFields();
					} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		
		//resetting the textfields if the user confirms they are sure they want the information erased
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int returnValue = 0;
				 returnValue = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the fields?", "Cancel", JOptionPane.YES_NO_OPTION);
				 if(returnValue == JOptionPane.YES_OPTION) 
				 {
					 resetFields();	 
					 
				 }			
			}
		});
		
		//updating the information in case the information was changed
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if(!tfFirstname.getText().isEmpty() && !tfLastname.getText().isEmpty() && !tfEmail.getText().isEmpty() && !tfPhone.getText().isEmpty() && !tfUsername.getText().isEmpty() && !tfPassword.getText().isEmpty()) 
					{
						String firstname = tfFirstname.getText();
						String lastname = tfLastname.getText();
						String gender = (String) cbSex.getSelectedItem();
						String email = tfEmail.getText();
						String phone = tfPhone.getText();
						String fax = tfFax.getText();
						String username = tfUsername.getText();
						String password = tfPassword.getText();
						
						PreparedStatement pstmtUpdate = conn.prepareStatement("UPDATE Person SET firstname = ?, lastname = ?, sex = ?, email = ?, phone = ?, fax = ?, username = ?, password = ? WHERE personID = ?");
						
						pstmtUpdate.setString(1, firstname);
						pstmtUpdate.setString(2, lastname);
						pstmtUpdate.setString(3, gender);
						pstmtUpdate.setString(4, email);
						pstmtUpdate.setString(5, phone);
						pstmtUpdate.setString(6, fax);
						pstmtUpdate.setString(7, username);
						pstmtUpdate.setString(8, password);
						pstmtUpdate.setInt(9, personID);
						pstmtUpdate.executeUpdate();
						pstmtUpdate.close();
						
						PMDatabase.fetchFromPerson(modelPerson);						
					}
					else if(!tfAcronym.getText().isEmpty() && !tfTitle.getText().isEmpty() && !tfStartdate.getText().isEmpty() && !taDescription.getText().isEmpty())
					{
						String acronym = tfAcronym.getText();
						String title = tfTitle.getText();
						String startdate = tfStartdate.getText();
						String endDate = tfEnddate.getText();
						String description = taDescription.getText();
						PreparedStatement pstmt = conn.prepareStatement("UPDATE Project SET acronym = ?, title = ?, startdate = ?, enddate = ?, description = ? WHERE projectID = ?");
						pstmt.setString(1, acronym);
						pstmt.setString(2, title);
						pstmt.setString(3, startdate);
						pstmt.setString(4,  endDate);
						pstmt.setString(5, description);
						pstmt.setInt(6, projectID);
						pstmt.executeUpdate();
						pstmt.close();
						
						PMDatabase.fetchFromProjects(modelProject);						
					}
					
					conn.close();
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		
		//adding collaborators to a project
		btnAddCollab.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String[] lastNames = taCollabs.getText().split("[,\\s\\r?\\n]+");
					PreparedStatement pstmtInsert = conn.prepareStatement("INSERT INTO Person_Project (personID, projectID) VALUES (?,?)");
					PreparedStatement pstmtPersonId = conn.prepareStatement("SELECT personID FROM Person WHERE lastname = ?");
					for (String lastName : lastNames) 
					{
						lastName.toString();
						pstmtPersonId.setString(1, lastName);
						ResultSet rsPersonId = pstmtPersonId.executeQuery();
						if(rsPersonId.next()) 
						{
							int personID = rsPersonId.getInt("personID");
							pstmtInsert.setInt(1, personID);
							pstmtInsert.setInt(2,  projectID);
							pstmtInsert.executeUpdate();
						}
						else 
						{
							JOptionPane.showMessageDialog(null, lastname + " does not exist in the database.");
						}
						rsPersonId.close();
					}
					pstmtPersonId.close();
					pstmtInsert.close();
					PMDatabase.fetchFromProjects(modelProject);
					conn.close();
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		
		//deleting a row out of the table with a message asking if the user is sure
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				int returnValue = 0;
				 returnValue = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?", "Are you sure?", JOptionPane.YES_NO_OPTION);
				 if(returnValue == JOptionPane.YES_OPTION) 
				 {
					 try 
					 {
						if(isFirstTable) {
							PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Person WHERE personID = ?");
							pstmt.setInt(1, personID);
							pstmt.executeUpdate();
							pstmt.close();
							PMDatabase.fetchFromPerson(modelPerson);
						}
						else if(!isFirstTable) 
						{
							PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Project WHERE projectID = ?");
							pstmt.setInt(1, projectID);
							pstmt.executeUpdate();
							pstmt.close();
							PMDatabase.fetchFromPerson(modelProject);
						}
						tablePerson.repaint();
						resetFields();
						conn.close();
					 } 
					 catch (SQLException e1) 
					 {
						e1.printStackTrace();
					 }
					 
				 }
			}
		});
		
		//switching the view between the table containing the employees and the table containing the projects
		btnChangeTable.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(isFirstTable) 
				{
					tablePerson.setModel(modelProject);
					isFirstTable = false;					
				}
				else 
				{
					tablePerson.setModel(modelPerson);
					isFirstTable = true;
				}
			}
		});
	}
	
		
	public void changeBtn() 
	{
		tfFirstname.getDocument().addDocumentListener(new DocumentListener() 
		{

			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				tablePerson.setRowSelectionAllowed(true);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				tablePerson.setRowSelectionAllowed(true);
				isMouseEventEnabled = true;
				
			}
		});
		tfLastname.getDocument().addDocumentListener(new DocumentListener()

		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfEmail.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfPhone.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfFax.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);	
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfUsername.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfPassword.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfAcronym.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfTitle.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);	
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfStartdate.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);	
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		tfEnddate.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		taDescription.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
		taCollabs.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);	
				isMouseEventEnabled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				isMouseEventEnabled = true;
				
			}
		});
	}
	
	//empties the form fields
	public void resetFields() 
	{
		for(JTextField tf : tfList)
		 {
			 tf.setText("");
		 }
		 cbSex.setSelectedIndex(0);
		 taCollabs.setText("");
		 taDescription.setText("");
	}
}

//class for the functionality of the combobox
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
