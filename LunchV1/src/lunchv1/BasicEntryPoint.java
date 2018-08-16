package lunchv1;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
		GridDataFactory.fillDefaults().grab(false, false).align(SWT.CENTER, SWT.CENTER).applyTo(logolayer);

		Composite toolbarlayer = createToolBarLayer(parent);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(toolbarlayer);

		Composite anwendungslayerOben = createAnwendungsLayerOben(parent);
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.CENTER).applyTo(anwendungslayerOben);

		Composite anwendungslayerUnten = createAnwendungsLayerUnten(parent);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(anwendungslayerUnten);

		return parent;
	}


	private Composite createAnwendungsLayerOben(Composite parent) {
		Composite result = new Composite(parent, SWT.BORDER);
		result.setLayout(new GridLayout(1, true));
		Label nationalität = new Label(result, SWT.NONE);
		nationalität.setText("Nach welchem Essen möchten sie Suchen ?");
		radioButtonsErstellen(result);
		

		return parent;
	}

	private Composite createAnwendungsLayerUnten(Composite parent) {
		Composite result = new Composite(parent, SWT.BORDER);
		result.setLayout(new FillLayout());
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
//		Hier muss noch geschaut werden, was genau diese Zeile macht und ob sie zwingend notwendig ist

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

	private void radioButtonsErstellen(Composite result) {
		Button alleAnzeigen = new Button(result, SWT.RADIO);
		Button pizzaUndPastaAnzeigen = new Button(result, SWT.RADIO);
		Button asiatischAnzeigen = new Button(result, SWT.RADIO);
		Button indischAnzeigen = new Button(result, SWT.RADIO);
		Button dönerAnzeigen = new Button(result, SWT.RADIO);
		Button burgerUndCoAnzeigen = new Button(result, SWT.RADIO);
		Button andereAnzeigen = new Button(result, SWT.RADIO);
		Button randomeAnzeigen = new Button(result, SWT.RADIO);
		Button auswerten = new Button(result, SWT.BORDER);

		auswerten.setText("Auswerten");
		alleAnzeigen.setText("Alle");
		pizzaUndPastaAnzeigen.setText("Pizza & Pasta");
		asiatischAnzeigen.setText("Asiatisch");
		indischAnzeigen.setText("Indisch");
		dönerAnzeigen.setText("Döner");
		burgerUndCoAnzeigen.setText("Burger & Co");
		andereAnzeigen.setText("Andere");
		randomeAnzeigen.setText("Randooooooooooooomeee");
	}
}
