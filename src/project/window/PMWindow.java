package project.window;

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
import java.util.HashMap;
import java.util.List;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import project.database.PMDatabase;
import project.database.PasswordHash;

import java.awt.Color;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private JButton btnSave;
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
	private JButton btnNew;
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
	private boolean isMouseEventEnabled = true;
	private boolean isUserInsert;
	
	HashMap<JComponent, MyDocumentListener> listeners = new HashMap<>();


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
		
		btnNew = new JButton("New");
		btnNew.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnNew.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNew.setEnabled(false);
		
		scpCollab = new JScrollPane();
		
		scpDescription = new JScrollPane();
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
						.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
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
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))))
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
						.addComponent(btnNew))
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
		
		btnSave = new JButton("Save");
		btnSave.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
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
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(btnSave)
						.addComponent(btnChangeTable))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scpTable, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		//setup for the two tables
		personHeaders = new String[]{"ID", "Firstname", "Lastname", "Gender", "E-Mail", "Phone", "Fax", "Username", "Password"};
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
					resetInputs();
					//setUserInput(false);
					isUserInsert = false;
					
					//changeButtonState(isUserInsert);
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
							password = (String) tablePerson.getValueAt(row, 8);
							
							tfFirstname.setText(firstname);
							tfLastname.setText(lastname);
							cbSex.setSelectedItem(sex);
							tfEmail.setText(email);
							tfPhone.setText(phone);
							tfFax.setText(fax);
							tfUsername.setText(username);
							
							if(password==null) {
								tfPassword.setText("...");
								MyDocumentListener tmp = listeners.get(tfPassword);
								 if(tmp != null) {
									 tmp.firstRun = true;
								 }
								 tfPassword.setText("");
								 tmp.firstRun = false;
							}
							
							else {
								
								tfPassword.setText(password);
							}
							
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
		btnNew.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{					
				String acronym = tfAcronym.getText();
				String title = tfTitle.getText();
				String startdate = tfStartdate.getText();
				String endDate = tfEnddate.getText();
				String description = taDescription.getText();
				String collabs = taCollabs.getText();
				String firstname = tfFirstname.getText();
				String lastname = tfLastname.getText();
				String gender = (String) cbSex.getSelectedItem();
				String email = tfEmail.getText();
				String phone = tfPhone.getText();
				String fax = tfFax.getText();
				String username = tfUsername.getText();
				String password = PasswordHash.generatePassword(tfPassword.getText());
				
				
				try {  
				
					if(!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !username.isEmpty() && !password.isEmpty() && !acronym.isEmpty() && !title.isEmpty() && !startdate.isEmpty() && !description.isEmpty()) 
					{
						PMDatabase.insertAll(firstname, lastname, gender, email, phone, fax, username, password, acronym, title, startdate, endDate, description);
					}
					else if (!collabs.isEmpty()) 
					{
						PMDatabase.insertIntoCollab(projectID, collabs);
					}
					else if(!acronym.isEmpty() && !title.isEmpty() && !startdate.isEmpty() && !description.isEmpty()) 
					{
						PMDatabase.insertIntoProject(acronym, title, startdate, endDate, description);
					}
					else if(!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !username.isEmpty() && !password.isEmpty()) 
					{
						PMDatabase.insertIntoPerson(firstname, lastname, gender, email, phone, fax, username, password);			
						
					}
					PMDatabase.fetchFromPerson(modelPerson);
					PMDatabase.fetchFromProjects(modelProject);
					tablePerson.repaint();
					resetFields();
					} 
				catch (NullPointerException e1) 
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
					 btnDelete.setEnabled(true);
					 btnNew.setEnabled(false);
					 btnCancel.setEnabled(false);
					 isMouseEventEnabled = true;
					 isUserInsert = false;
				 }
			}
		});
		
		//updating the information in case the information was changed
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String firstname = tfFirstname.getText();
					String lastname = tfLastname.getText();
					String gender = (String) cbSex.getSelectedItem();
					String email = tfEmail.getText();
					String phone = tfPhone.getText();
					String fax = tfFax.getText();
					String username = tfUsername.getText();
					String password = PasswordHash.generatePassword(tfPassword.getText());
					
					String acronym = tfAcronym.getText();
					String title = tfTitle.getText();
					String startdate = tfStartdate.getText();
					String endDate = tfEnddate.getText();
					String description = taDescription.getText();
					String collabs = taCollabs.getText();
					
					if(arePersonFieldsFilled()) 
					{
						
						PMDatabase.updatePerson(firstname, lastname, gender, email, phone, fax, username, password, personID);						
						PMDatabase.fetchFromPerson(modelPerson);						
					}
					else if(areProjectFieldsFilled())
					{
						PMDatabase.updateProject(acronym, title, startdate, endDate, description, collabs, projectID);
						PMDatabase.fetchFromProjects(modelProject);						
					}
				} 
				catch (NullPointerException e1) 
				{
					e1.printStackTrace();
				}
				resetFields();
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
	
	//courtesy of Sven	
	public void changeBtn() 
	{
		listeners.putIfAbsent(tfFirstname, new MyDocumentListener(this));
		listeners.putIfAbsent(tfLastname, new MyDocumentListener(this));		
		listeners.putIfAbsent(tfEmail, new MyDocumentListener(this));
		listeners.putIfAbsent(tfPhone, new MyDocumentListener(this));
		listeners.putIfAbsent(tfFax, new MyDocumentListener(this));
		listeners.putIfAbsent(tfUsername, new MyDocumentListener(this));
		listeners.putIfAbsent(tfPassword, new MyDocumentListener(this));
		listeners.putIfAbsent(tfAcronym, new MyDocumentListener(this));
		listeners.putIfAbsent(tfTitle, new MyDocumentListener(this));
		listeners.putIfAbsent(tfStartdate, new MyDocumentListener(this));
		listeners.putIfAbsent(tfEnddate, new MyDocumentListener(this));
		listeners.putIfAbsent(taDescription, new MyDocumentListener(this));
		listeners.putIfAbsent(taCollabs, new MyDocumentListener(this));
		
		tfFirstname.getDocument().addDocumentListener(listeners.get(tfFirstname));
		tfLastname.getDocument().addDocumentListener(listeners.get(tfLastname));
		tfEmail.getDocument().addDocumentListener(listeners.get(tfEmail));
		tfPhone.getDocument().addDocumentListener(listeners.get(tfPhone));
		tfFax.getDocument().addDocumentListener(listeners.get(tfFax));
		tfUsername.getDocument().addDocumentListener(listeners.get(tfUsername));	
		
		tfPassword.getDocument().addDocumentListener(listeners.get(tfPassword));		
		
		tfAcronym.getDocument().addDocumentListener(listeners.get(tfAcronym));
		tfTitle.getDocument().addDocumentListener(listeners.get(tfTitle));
		tfStartdate.getDocument().addDocumentListener(listeners.get(tfStartdate));
		tfEnddate.getDocument().addDocumentListener(listeners.get(tfEnddate));
		taDescription.getDocument().addDocumentListener(listeners.get(taDescription));
		taCollabs.getDocument().addDocumentListener(listeners.get(taCollabs));
	}
	//courtesy of Sven	
	public void resetInputs() {
		for(JTextField tf : tfList)
		 {
			
			 MyDocumentListener tmp = listeners.get(tf);
			 if(tmp != null) {
				 tmp.firstRun = true;
			 }
			 
		 }		
	}
	
	//empties the form fields
	public void resetFields() 
	{
		
		
		for(JTextField tf : tfList)
		 {
			
			 tf.setText("");
			 
			 resetInputs();
			 
		 }
		// listeners.clear();
		 cbSex.setSelectedIndex(0);
		 taCollabs.setText("");
		 taDescription.setText("");
		 
		isMouseEventEnabled=true;

	}
	
	//courtesy of Sven	
	public void changeButtonState( boolean isUserInput) {
		btnNew.setEnabled(isUserInput);
		btnCancel.setEnabled(isUserInput);
	}
	
	//courtesy of Sven	
	public void setUserInput( boolean active) {
		this.isUserInsert = active;
		//changeButtonState(active);
	}
	
	public boolean arePersonFieldsFilled() 
	{
		return !tfFirstname.getText().isEmpty() && 
				!tfLastname.getText().isEmpty() && 
				!tfEmail.getText().isEmpty() && 
				!tfPhone.getText().isEmpty() && 
				!tfUsername.getText().isEmpty() && 
				!tfPassword.getText().isEmpty();
		
	}
	
	public boolean areProjectFieldsFilled() 
	{
		return !tfAcronym.getText().isEmpty() && 
				!tfTitle.getText().isEmpty() && 
				!tfStartdate.getText().isEmpty() && 
				!taDescription.getText().isEmpty();
	}
	
//courtesy of Sven	
class MyDocumentListener implements DocumentListener {
		
		boolean firstRun =true;
		PMWindow pmWindow;
	
		
		public MyDocumentListener(PMWindow pmWindow) {
			this.pmWindow = pmWindow;
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) 
		{
			int dlength =  e.getDocument().getLength();
			if(firstRun ) {
				pmWindow.btnDelete.setEnabled(true);
				pmWindow.btnNew.setEnabled(false);
				pmWindow.btnCancel.setEnabled(false);
				
				firstRun = false;
			}
			else {
				pmWindow.isMouseEventEnabled = false;
				pmWindow.isUserInsert = true;
				pmWindow.btnNew.setEnabled(true);
				pmWindow.btnCancel.setEnabled(true);
				pmWindow.btnDelete.setEnabled(false);
			}
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) 
		{
			if(!firstRun) {
				insertUpdate(e);	
			}
		}

		@Override
		public void changedUpdate(DocumentEvent e) 
		{
		
		}
		
		public void setFirstRun( boolean firstrun) {
			this.firstRun = firstrun;
		}
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



