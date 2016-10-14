/*******************************************************************************
 * Copyright 2016 Universidad Politécnica de Madrid UPM
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.universAAL.tools.role;

import javax.swing.event.TableModelListener;
import javax.swing.event.TreeModelListener;
import javax.swing.table.TableModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * @author amedrano
 *
 */
public class RoleTreeModel implements TreeModel, TableModel {

	/**
	 * 
	 */
	public RoleTreeModel() {
		// TODO Auto-generated constructor stub
	}

	/**{@inheritDoc} */
	public Object getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	/**{@inheritDoc} */
	public Object getChild(Object parent, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**{@inheritDoc} */
	public int getChildCount(Object parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**{@inheritDoc} */
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		return false;
	}

	/**{@inheritDoc} */
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}

	/**{@inheritDoc} */
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**{@inheritDoc} */
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}

	/**{@inheritDoc} */
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
