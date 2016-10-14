package org.universAAL.tools.user;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.ontology.profile.User;
import org.universAAL.tools.CHeQuerrier;

//TODO: add modular system for subprofiles to add information to table.
class UserListTableModel implements TableModel, WindowListener {

	private static final String NAMESPACE = "http://tools.universAAL.org/UserManager.owl#";
	private static final String AUX_BAG_OBJECT = NAMESPACE + "auxilaryBagObject";
	private static final String AUX_BAG_PROP =  NAMESPACE + "auxilaryBagProperty";
	
	private ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();
	
	private CHeQuerrier querier;
	
	List<User> usr= new ArrayList<User>();
	private static final String[] COL_NAMES = new String[] { "User Type", "User URI"};

	public UserListTableModel(ModuleContext md) {
		// helper = new ProfilingServerHelper(md);
		querier = new CHeQuerrier(md);
		updated();
	}

	void updated() {
		
		Object o = querier.query( CHeQuerrier.getQuery(CHeQuerrier.getResource("getObjectType.sparql"), new String[]{AUX_BAG_OBJECT,AUX_BAG_PROP, User.MY_URI}));
		o = ((Resource)o).getProperty(AUX_BAG_PROP);
		
		if (o instanceof List){
			List<Resource> lr = (List<Resource>) o;
			usr.retainAll(lr);
			for (Resource r : lr) {
				if (!usr.contains(r)) {
					usr.add((User) querier.getFullResourceGraph(r.getURI()));
				}
			}
		}
		 else if (o instanceof Resource){
				usr.add((User) querier.getFullResourceGraph(((Resource) o).getURI()));
		}
		for (TableModelListener l : listeners) {
			l.tableChanged(new TableModelEvent(this));
		}
	}

	/** {@ inheritDoc} */
	public void windowOpened(WindowEvent e) {
	}

	/** {@ inheritDoc} */
	public void windowClosing(WindowEvent e) {
	}

	/** {@ inheritDoc} */
	public void windowClosed(WindowEvent e) {
		updated();
	}

	/** {@ inheritDoc} */
	public void windowIconified(WindowEvent e) {
	}

	/** {@ inheritDoc} */
	public void windowDeiconified(WindowEvent e) {
	}

	/** {@ inheritDoc} */
	public void windowActivated(WindowEvent e) {
	}

	/** {@ inheritDoc} */
	public void windowDeactivated(WindowEvent e) {
	}

	/** {@ inheritDoc} */
	public int getRowCount() {
		if (usr == null)
			return 0;
		return usr.size();
	}

	/** {@ inheritDoc} */
	public int getColumnCount() {
		return 2;
	}

	/** {@ inheritDoc} */
	public String getColumnName(int columnIndex) {
		return COL_NAMES[columnIndex];
	}

	/** {@ inheritDoc} */
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	/** {@ inheritDoc} */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/** {@ inheritDoc} */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			return usr.get(rowIndex).getURI();
		}
		if (columnIndex == 0) {
			String label = Resource.getResource(getUser(rowIndex).getClassURI(), null).getOrConstructLabel(
				    null);
			return label;
		}
		return null;
	}

	User getUser(int rowIndex){
		return usr.get(rowIndex);
	}
	
	/** {@ inheritDoc} */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	/** {@ inheritDoc} */
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	/** {@ inheritDoc} */
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

}