package lunchv1;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class KPlanKlasse extends Composite {

	public KPlanKlasse(Composite parent, int style) {
		super(parent, style);
		createKantinenLayer(this);
	}

	private Composite createKantinenLayer(Composite parent) {
		parent.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(parent);
		
		
		
		Label logo = new Label(parent, SWT.FLAT);
		Image image = new Image(parent.getDisplay(), "KW34.JPG");
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).applyTo(logo);
		logo.setImage(image);
		getShell().layout();
		
		return parent;
	}
}
