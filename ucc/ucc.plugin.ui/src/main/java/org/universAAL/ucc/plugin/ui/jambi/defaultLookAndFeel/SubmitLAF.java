/*******************************************************************************
 * Copyright 2011 Universidad Politécnica de Madrid
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
package org.universAAL.ucc.plugin.ui.jambi.defaultLookAndFeel;


import org.universAAL.middleware.ui.rdf.Submit;
import org.universAAL.ucc.plugin.ui.jambi.model.Model;
import org.universAAL.ucc.plugin.ui.jambi.model.FormControl.SubmitModel;

import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QWidget;

/**
 * @author pabril
 * @author amedrano
 * 
 */
public class SubmitLAF extends SubmitModel {

	/**
	 * Constructor.
	 * 
	 * @param control
	 *            the {@link Submit} which to model.
	 */
	public SubmitLAF(Submit control) {
		super(control);
	}

	/**
	 * Set a color behabiour to a button
	 * 
	 * @param button
	 *            the button which to add the behaviour
	 * @param border
	 *            a {@link Border} for the button in normal status
	 * @param normalF
	 *            the foreground colour for normal state
	 * @param normalB
	 *            the background colour for normal state
	 * @param enterF
	 *            the foreground colour for pressed state
	 * @param enterB
	 *            the background colour for pressed state
	 * @param clickF
	 *            the foreground colour for clicked state
	 * @param clickB
	 *            the background colour for clicked state
	 */
	public static void setButtonBehaviour(QWidget button, QColor border,
			QColor normalF, QColor normalB, QColor enterF, QColor enterB,
			QColor clickF, QColor clickB) {
		
		// TODO
		//button.setBorder(new CompoundBorder(BorderFactory
		//		.createLineBorder(border), new EmptyBorder(10, 10, 10, 10)));
		
		// button.setForeground(normalF);
		// button.setBackground(normalB);
		
		// TODO
		// button.addMouseListener(new MyMouseAdatper(border, normalF, normalB,
		//		enterF, enterB, clickF, clickB));
	}

	/** {@inheritDoc} */
	public QWidget getNewComponent() {
		return buttonDecorate(this, super.getNewComponent());
	}

	protected static QWidget buttonDecorate(Model model, QWidget button) {
		QColor normalF = null;
		QColor normalB = null;
		QColor enterF = null;
		QColor enterB = null;
		QColor clickF = null;
		QColor clickB = null;
		QColor border = null;

		// TODO
		/*
		if (model.isInStandardGroup()) {
			/*
			 * System Buttons
			 */
		/*
			border = ColorLAF.getborderLineMM();
			normalF = ColorLAF.getBackMML();
			normalB = ColorLAF.getBackMM();
			enterF = ColorLAF.getBackLetter();
			enterB = ColorLAF.getOverSytem();
			clickF = ColorLAF.getBackLetter();
			clickB = ColorLAF.getOverSytem();
		} else {
			/*
			 * buttons inside IO
			 */
		/*
			border = ColorLAF.getborderLine();
			normalF = ColorLAF.getBackLetter();
			normalB = ColorLAF.getBackSystem();
			enterF = ColorLAF.getBackLetter();
			enterB = ColorLAF.getOverSytem();
			clickF = ColorLAF.getSelectedLetter();
			clickB = ColorLAF.getBackSystem();
		}
		*/
		setButtonBehaviour(button, border, normalF, normalB, enterF, enterB,
				clickF, clickB);
		return button;
	}

	// protected static class MyMouseAdatper extends MouseAdapter {
		/*
		private Color normalF;
		private Color normalB;
		private Color enterF;
		private Color enterB;
		private Color clickF;
		private Color clickB;
		private Color border;

		public MyMouseAdatper(Color border, Color normalF, Color normalB,
				Color enterF, Color enterB, Color clickF, Color clickB) {
			this.border = border;
			this.normalF = normalF;
			this.normalB = normalB;
			this.enterF = enterF;
			this.enterB = enterB;
			this.clickF = clickF;
			this.clickB = clickB;
		}

		public void mouseEntered(MouseEvent e) {
			JComponent src = (JComponent) e.getSource();
			src.setForeground(enterF);
			src.setBackground(enterB);
		}

		public void mouseExited(MouseEvent e) {
			JComponent src = (JComponent) e.getSource();
			src
					.setBorder(new CompoundBorder(BorderFactory
							.createLineBorder(border), new EmptyBorder(10, 10,
							10, 10)));
			src.setForeground(normalF);
			src.setBackground(normalB);
		}

		public void mouseClicked(MouseEvent e) {
			JComponent src = (JComponent) e.getSource();
			src.setForeground(clickF);
			src.setBackground(clickB);
		}*/
	// }
}