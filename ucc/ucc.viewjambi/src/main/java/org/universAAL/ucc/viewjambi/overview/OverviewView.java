package org.universAAL.ucc.viewjambi.overview;

import org.universAAL.ucc.api.model.IModel;
import org.universAAL.ucc.viewjambi.Activator;
import org.universAAL.ucc.viewjambi.MainWindow;
import org.universAAL.ucc.viewjambi.SubWindow;
import org.universAAL.ucc.viewjambi.juic.Ui_Overview;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QTreeModel;

public class OverviewView extends SubWindow {
	
	private static Ui_Overview install_base = new Ui_Overview();
	private static IModel model;
	
	public OverviewView(MainWindow parent) {
		super(parent, OverviewView.install_base);
		model = Activator.getModel();
		init();
	}
	
	public void init(){
		
		
		QTreeModel treeModel = new QTreeModel() {
			
			@Override
			public String text(Object arg0) {
				return (String) arg0;
			}
			
			@Override
			public int childCount(Object arg0) {
				if(!(arg0 == null)){
				if(model.getApplicationManagment().containsApplication((String) arg0)){
					return model.getApplicationManagment().getInstalledBundles((String) arg0).size();
				}else{
					return 0;
				}
				}else{
					return model.getApplicationManagment().getInstalledApplications().size();
				}
			}
			
			@Override
			public Object child(Object arg0, int arg1) {
				if(arg0 == null){
					return model.getApplicationManagment().getInstalledApplications().get(arg1);
				}else{
					return model.getApplicationManagment().getInstalledBundles((String) arg0).get(arg1);
				}
			}
		};
		

		install_base.treeView.setHeaderHidden(true);
		install_base.treeView.setModel(treeModel);
		install_base.treeView.doubleClicked.connect(this, "onDoubleClick(QModelIndex)");

	}
	private void onDoubleClick(QModelIndex index){
		if(Activator.getModel().getApplicationManagment().containsApplication((String) index.data()))
			new Overview_ConfigView(this.parent, (String) index.data());
	}
	
	protected void cancel() {
		this.parent.closeSubWindow(this);
	}
	

}