package lunchv1;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class BasicEntryPoint implements EntryPoint {

	public int createUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.NONE);
		shell.setBounds(0, 0, 1920, 1080);
		shell.setLayout(new GridLayout(1, true));
		createLayer(shell);
		shell.open();
		shell.layout();

		return 0;
	}

	private Composite createLayer(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Composite logolayer = createLogoLayer(parent);
		GridDataFactory.fillDefaults().grab(false,false).align(SWT.CENTER, SWT.CENTER).applyTo(logolayer);
		
		
		Composite toolbarlayer = createToolBarLayer(parent);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(toolbarlayer);
		
		Composite anwendungslayer = new Composite(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(anwendungslayer);
		
		
		
		return parent;
	}
	
	private Composite createToolBarLayer(Composite parent) {
		Composite result = new Composite(parent, SWT.NONE);
		result.setLayout(new FillLayout());
		ToolBar dieToolbar = new ToolBar(result, SWT.HORIZONTAL);
		ToolItem lunchitem = new ToolItem(dieToolbar, SWT.PUSH);
		lunchitem.setText("Lunch Application");
		new ToolItem(dieToolbar, SWT.SEPARATOR);
		ToolItem calcitem = new ToolItem(dieToolbar, SWT.PUSH);
		calcitem.setText("Calculator");
//		dieToolbar.addListener(SWT.Dispose, evt -> lunchitem.dispose());
		
		
		return result;
	}

	private Composite createLogoLayer(Composite parent) {
		Composite result = new Composite(parent, SWT.NONE);
		result.setLayout(new FillLayout());
		Label logo = new Label(result, SWT.NONE);
		Image image = new Image(result.getDisplay(), "C:\\Users\\horvayF\\eclipse-workspace\\LunchV1\\Test.png");
		logo.setImage(image);
		logo.addListener(SWT.Dispose, evt -> image.dispose());
		return result;
	}

}
