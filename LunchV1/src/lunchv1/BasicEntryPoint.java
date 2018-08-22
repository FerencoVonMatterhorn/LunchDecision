package lunchv1;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class BasicEntryPoint implements EntryPoint {
	Browser browser;
	ToolItem kanPlan;
	ToolItem lunchItem;
	LunchdecKlasse lunchDeclayer;
	Composite kantinenLayer;
	Shell shell;
	Display display;

	public int createUI() {
		display = Display.getDefault();
		shell = new Shell(display, SWT.NONE);
		shell.setBounds(display.getBounds());
		shell.setLayout(new GridLayout(1, true));
		
		createLayer(shell);
		shell.open();
		shell.layout();

		display.addListener(SWT.Resize, (event) -> shell.setBounds(display.getBounds()));
		return 0;
	}

	private Composite createLayer(Composite parent) {
		Composite headerComp = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(headerComp);

		headerComp.setLayout(new GridLayout(1, false));

		Composite toolbarlayer = createToolBarLayer(headerComp);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(toolbarlayer);

		Composite logolayer = createLogoLayer(headerComp);
		GridDataFactory.fillDefaults().grab(false, false).align(SWT.CENTER, SWT.CENTER).applyTo(logolayer);

		kanPlan.addListener(SWT.Selection, (event) -> {
			render(lunchDeclayer, false);
			render(kantinenLayer, true);

		});

		lunchItem.addListener(SWT.Selection, (event) -> {
			render(kantinenLayer, false);
			render(lunchDeclayer, true);
		});
		Composite contentPane = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		contentPane.setLayout(gridLayout);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(contentPane);
		lunchDeclayer = createLunchDecLayer(contentPane);
		kantinenLayer = createKantinenPlan(contentPane);
		render(kantinenLayer, false);
		return parent;
	}

//--------------------------------Toolbarlayer-------------------------------------------------------------------------------------------------------------

	private Composite createToolBarLayer(Composite parent) {
		Composite result = new Composite(parent, SWT.NONE);
		result.setLayout(new FillLayout());

		addToolBarItems(result);

		return result;
	}

//----------------------------------LogoLayer---------------------------------------------------------------------------------------------------------------

	private Composite createLogoLayer(Composite parent) {
		Composite result = new Composite(parent, SWT.NONE);
		result.setLayout(new FillLayout());

		Label logo = new Label(result, SWT.NONE);
		Image image = new Image(result.getDisplay(), "Test.png");
		logo.setImage(image);
		logo.addListener(SWT.Dispose, evt -> image.dispose());
		return result;
	}

//--------------------------------------Searchbar-----------------------------------------------------------------------------------------------------------

	private Composite createSearchBarLayer(Composite parent) {
		Composite result = new Composite(parent, SWT.BORDER);
		result.setLayout(new FillLayout());
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.CENTER, SWT.CENTER).applyTo(result);

		Text dieSearchbar = new Text(result, SWT.BORDER);
		Button derGoButton = new Button(result, SWT.BORDER);
		derGoButton.setText("GO!");
		derGoButton.addListener(SWT.Selection, (event) -> {
			String searchtext = dieSearchbar.getText();
			browser.setUrl("https://wego.here.com/search/" + searchtext + "?map=48.47265,7.92901,15,satellite");
		});
		dieSearchbar.addListener(SWT.DefaultSelection, (event) -> {
			String searchtext = dieSearchbar.getText();
			browser.setUrl("https://wego.here.com/search/" + searchtext + "?map=48.47265,7.92901,15,satellite");
		});
		return result;
	}

	private Composite createSearchBarLayer1(Composite parent) {
		Composite result = new Composite(parent, SWT.BORDER);
		result.setLayout(new FillLayout());
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.CENTER, SWT.CENTER).applyTo(result);

		Button deraufnehmenButton = new Button(result, SWT.BORDER);
		deraufnehmenButton.setText("Ins Voting aufnehmen");
		return result;
	}

// ------------------------extracted-Methods------------------------------------------------------------------------------------

	private void addToolBarItems(Composite result) {
		ToolBar dieToolbar = new ToolBar(result, SWT.HORIZONTAL);
		lunchItem = new ToolItem(dieToolbar, SWT.PUSH);
		lunchItem.setText("LunchFinder");
		new ToolItem(dieToolbar, SWT.SEPARATOR);
		ToolItem votingItem = new ToolItem(dieToolbar, SWT.PUSH);
		votingItem.setText("Voting");
		new ToolItem(dieToolbar, SWT.SEPARATOR);
		kanPlan = new ToolItem(dieToolbar, SWT.PUSH);
		kanPlan.setText("Wochenplan Kantine");
		dieToolbar.addListener(SWT.Dispose, evt -> lunchItem.dispose());
	}

	private LunchdecKlasse createLunchDecLayer(Composite parent) {
		return new LunchdecKlasse(parent, SWT.NONE);

	}

	private KPlanKlasse createKantinenPlan(Composite parent) {
		return new KPlanKlasse(parent, SWT.NONE);
	}

	private void render(Composite parent, boolean isVisible) {
		GridData layoutData = (GridData) parent.getLayoutData();
		layoutData.exclude = !isVisible;
		parent.setVisible(isVisible);
		parent.layout();
	}
}
