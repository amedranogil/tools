package org.universAAL.tools.subprofiles;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.utils.LogUtils;
import org.universAAL.middleware.owl.ManagedIndividual;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.ontology.profile.SubProfile;
import org.universAAL.ontology.profile.User;
import org.universAAL.ontology.security.SecuritySubprofile;
import org.universAAL.tools.CHeQuerrier;
import org.universAAL.tools.ProjectActivator;

public class UserEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private User user;
	private JTabbedPane spTabbedPane;
	
	private static final String NAMESPACE = "http://tools.universAAL.org/UserManager.owl#";
	private static final String AUX_BAG_OBJECT = NAMESPACE + "auxilaryBagObject";
	private static final String AUX_BAG_PROP =  NAMESPACE + "auxilaryBagProperty";


	/**
	 * Create the frame.
	 */
	public UserEditor(User u) {
		user = u;
		setTitle("User Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel generalUserProperties = new JPanel();
		contentPane.add(generalUserProperties, BorderLayout.NORTH);
		
		JLabel lblUserUri = new JLabel("User URI: ");
		
		JLabel lblNewLabel = new JLabel("User type: ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		
		JButton btnReload = new JButton("reload");
		
		JButton btnSave = new JButton("save");
		GroupLayout gl_generalUserProperties = new GroupLayout(generalUserProperties);
		gl_generalUserProperties.setHorizontalGroup(
			gl_generalUserProperties.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_generalUserProperties.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_generalUserProperties.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUserUri)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_generalUserProperties.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, Alignment.TRAILING, 0, 277, Short.MAX_VALUE)
						.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_generalUserProperties.createParallelGroup(Alignment.LEADING)
						.addComponent(btnReload, Alignment.TRAILING)
						.addComponent(btnSave, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_generalUserProperties.setVerticalGroup(
			gl_generalUserProperties.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_generalUserProperties.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_generalUserProperties.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserUri)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReload))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_generalUserProperties.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		generalUserProperties.setLayout(gl_generalUserProperties);
		
		spTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(spTabbedPane, BorderLayout.CENTER);
		
		updateSubprofiles();
	}
	
	
	
	private void updateSubprofiles() {
		spTabbedPane.removeAll();
		List<SubProfile> sps = getSubProfile(ProjectActivator.context, user);
		for (SubProfile sp : sps) {
			SubProfileEditor spe = SubProfileEditorFactory.getSubProfileEditor(sp.getClassURI());
			JPanel spep = spe.getSubprofileEditorPanel(sp);
			String title = OntologyManagement.getInstance().getOntClassInfo(sp.getClassURI()).getResourceLabel();
			spTabbedPane.addTab(title, spep);
		}
		//TODO add + thing.
	}



	protected List<SubProfile> getSubProfile(ModuleContext mc, User usr){
		CHeQuerrier querier = new CHeQuerrier(mc);
		Object o = querier.query(CHeQuerrier.getQuery(CHeQuerrier.getResource("getSubProfileForUser.sparql"), new String[]{AUX_BAG_OBJECT,AUX_BAG_PROP,usr.getURI()}));

		if (o instanceof SubProfile){
			ArrayList<SubProfile> r = new ArrayList<SubProfile>();
			r.add((SubProfile) o);
			return r;
		} else if (o instanceof List){
			return (List<SubProfile>) o; 
		} else {
			return new ArrayList<SubProfile>();
		}
	}
}
