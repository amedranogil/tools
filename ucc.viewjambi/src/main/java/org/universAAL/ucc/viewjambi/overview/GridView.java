/*
	Copyright 2007-2014 FZI, http://www.fzi.de
	Forschungszentrum Informatik - Information Process Engineering (IPE)

	See the NOTICE file distributed with this work for additional 
	information regarding copyright ownership
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	  http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */

package org.universAAL.ucc.viewjambi.overview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.universAAL.ucc.api.plugin.PluginGridViewItem;
import org.universAAL.ucc.viewjambi.common.SubWindow;
import org.universAAL.ucc.viewjambi.impl.MainWindow;
import org.universAAL.ucc.viewjambi.information.InformationView;
import org.universAAL.ucc.viewjambi.install.InstallView;
import org.universAAL.ucc.viewjambi.juic.Ui_GridView;
import org.universAAL.ucc.viewjambi.layouts.OverviewGridLayout;

import com.trolltech.qt.core.QObject;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QWidget;

/**
 * 
 * @author tzentek - <a href="mailto:zentek@fzi.de">Tom Zentek</a>
 * 
 */
public class GridView extends SubWindow {
	
	private static Ui_GridView install_base = new Ui_GridView();
	private OverviewGridLayout gridLayout;
	private static List<LabeledIcon> iconWidgets;
	static OverviewView overview;
	private HashMap<LabeledIcon, Runnable> items;


	public GridView() {
		super(GridView.install_base);
		gridLayout = install_base.currentLayout;
		
		this.setMinimumSize(new QSize(500,  200));
 
		items=new HashMap<LabeledIcon, Runnable>();
		Runnable temp=new Runnable(){
			public void run() {
				overview=new OverviewView();
			}
		};
		items.put(new LabeledIcon("Overview", "overview.png"), temp);
		temp=new Runnable(){
			public void run() {
				new InstallView();
			}
		};
		items.put(new LabeledIcon("Install App", "install.png"), temp);
		temp=new Runnable(){
			public void run() {
				MainWindow.getInstance().deinstallApp();
			}
		};
		items.put(new LabeledIcon("Uninstall App", "uninstall.png"), temp);
		/*temp=new Runnable(){
			public void run() {
				new StoreView();
			}
		};
		items.put(new LabeledIcon("Download App", "utorrent2.png"), temp);
		*/
		updateGridView();
		
		
		//setIconList();
  	}

	public void addItem(final PluginGridViewItem p){
		items.put(new LabeledIcon(p.getCaption(),p.getIcon()), p.getToBeRunAtClick());
		updateGridView();
	}
	private void updateGridView(){
		Iterator<QObject> old=gridLayout.children().iterator();
		while(old.hasNext())
			gridLayout.removeWidget((QWidget) old.next());

		Iterator<LabeledIcon> itr=items.keySet().iterator();
		while(itr.hasNext()){
			LabeledIcon icon=itr.next();
			icon.clicked.disconnect();
    		gridLayout.addWidget(icon, 3);
    		icon.clicked.connect(this,"gridItemClicked()");
    	} 
	}
	public void gridItemClicked(){
			Object object = QObject.signalSender();
			if (object == null || !(object instanceof LabeledIcon)) {
				System.err.println("Object have to be of type LabeledIcon!");
				return;
			}
			Runnable toExec = this.items.get(object);
			if (toExec == null) {
				System.err.println("Action not registered!");
				return;
			}
			toExec.run();
	}
	
	
	
    
/*	private void setIconList(){
		iconWidgets = new ArrayList<LabeledIcon>();
		iconWidgets.add(new LabeledIcon("Overview", "explorer.png"));
		iconWidgets.add(new LabeledIcon("Install App", "3dsmax.png"));
		iconWidgets.add(new LabeledIcon("Uninstall App", "aimp 4.png"));
		iconWidgets.add(new LabeledIcon("System\nInformation", "addressbook.png"));
		iconWidgets.add(new LabeledIcon("Download App", "utorrent2.png"));
		iconWidgets.add(new LabeledIcon("Foo", "rss.png"));
		iconWidgets.add(new LabeledIcon("Bar", "chat.png"));
		iconWidgets.add(new LabeledIcon("Foo2", "gmail.png"));
		iconWidgets.add(new LabeledIcon("Download App", "utorrent2.png"));
		iconWidgets.add(new LabeledIcon("Download App", "utorrent2.png"));
		iconWidgets.add(new LabeledIcon("Download App", "utorrent2.png"));
		iconWidgets.add(new LabeledIcon("Download App", "utorrent2.png"));
		iconWidgets.add(new LabeledIcon("Download App", "utorrent2.png"));
	
	}*/
	
}