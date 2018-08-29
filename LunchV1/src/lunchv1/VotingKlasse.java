package lunchv1;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class VotingKlasse extends Composite {
	public VotingKlasse(Composite parent , int style) {
		super(parent, style);
		createVotingLayer(this);
	}
	
	private Composite createVotingLayer(Composite parent) {
		parent.setLayout(new GridLayout());
		Label errmsg = new Label(parent, SWT.BORDER);
		errmsg.setText("Hier wird gerade noch gearbeitet, ebat");
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.CENTER).applyTo(errmsg);
		getShell().layout();
		return parent;
	}

}
