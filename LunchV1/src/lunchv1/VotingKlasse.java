package lunchv1;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class VotingKlasse extends Composite {
	public VotingKlasse(Composite parent , int style) {
		super(parent, style);
		createVotingLayer(this);
	}
	
	private Composite createVotingLayer(Composite parent) {
		parent.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(parent);
		return parent;
	}

}
