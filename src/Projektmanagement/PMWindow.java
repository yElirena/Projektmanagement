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
import javax.swing.JOptionPane;

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
import java.awt.Component;
import java.awt.event.ActionListener;
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
	private JLabel lblProjectName;
	private JButton btnAmend;
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
	private JTable tablePerson_Projects;

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
		btnActions();
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
		topPanel.setPreferredSize(new Dimension(32767, 16383));
		topPanel.setMinimumSize(new Dimension(32767, 16383));
		topPanel.setMaximumSize(new Dimension(32767, 16383));
		splitPane.setLeftComponent(topPanel);
		
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
		
		lblProjectName = new JLabel("Projects: ");
		lblProjectName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
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
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnSave = new JButton("Save");
		btnSave.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JScrollPane scpProjects = new JScrollPane();
		
		JScrollPane scpDescription = new JScrollPane();
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(373)
					.addComponent(lblTopTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(379))
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLastname, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFax, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSex)
						.addComponent(lblUsername)
						.addComponent(lblPassword)
						.addComponent(lblProjectName)
						.addComponent(lblFirstname, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(tfFirstname, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
								.addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
								.addComponent(tfUsername, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
								.addComponent(cbSex, 0, 254, Short.MAX_VALUE)
								.addComponent(tfFax, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
								.addComponent(tfPhone, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
								.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
								.addComponent(tfLastname, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
							.addGap(60)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_topPanel.createSequentialGroup()
									.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAcronym)
										.addComponent(lblStartdate)
										.addComponent(lblTitle)
										.addComponent(lblEnddate))
									.addGap(18)
									.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(tfEnddate)
										.addComponent(tfStartdate)
										.addComponent(tfTitle)
										.addComponent(tfAcronym, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
								.addGroup(gl_topPanel.createSequentialGroup()
									.addComponent(lblDescription)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(scpDescription, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))))
						.addComponent(scpProjects, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(223)
					.addComponent(lblPerson, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addGap(339)
					.addComponent(lblProject)
					.addGap(133))
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap(290, Short.MAX_VALUE)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(337))
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
						.addComponent(lblEmail)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStartdate)
						.addComponent(tfStartdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(scpProjects, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCancel)
								.addComponent(btnSave)))
						.addComponent(scpDescription, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(14))
		);
		
		JTextArea taDescription = new JTextArea();
		taDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scpDescription.setViewportView(taDescription);
		
		JTextArea taProjects = new JTextArea();
		taProjects.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scpProjects.setViewportView(taProjects);
		topPanel.setLayout(gl_topPanel);
		
		bottomPanel = new JPanel();
		bottomPanel.setMinimumSize(new Dimension(32767, 16383));
		bottomPanel.setMaximumSize(new Dimension(32767, 16383));
		splitPane.setRightComponent(bottomPanel);
		
		btnAmend = new JButton("Amend");
		btnAmend.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JScrollPane scpTable = new JScrollPane();
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_bottomPanel.createSequentialGroup()
							.addGap(289)
							.addComponent(btnAmend, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_bottomPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scpTable, GroupLayout.PREFERRED_SIZE, 779, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_bottomPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDelete)
						.addComponent(btnAmend))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scpTable, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tablePerson_Projects = new JTable();
		scpTable.setViewportView(tablePerson_Projects);
		bottomPanel.setLayout(gl_bottomPanel);
	}
	private void btnActions() 
	{
		
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		btnAmend.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?");
			}
		});
						
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