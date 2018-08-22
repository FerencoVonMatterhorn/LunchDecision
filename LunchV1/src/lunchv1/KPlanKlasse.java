package lunchv1;

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
		Composite result = null;
		result = new Composite(parent, SWT.NONE);
		result.setLayout(new GridLayout());
	
		Label logo = new Label(result, SWT.BORDER);
		Image image = new Image(result.getDisplay(), "KW34.JPG");
		logo.setImage(image);
		
		return result;
	}
}
