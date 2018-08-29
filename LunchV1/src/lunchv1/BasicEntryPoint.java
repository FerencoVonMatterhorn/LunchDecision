package lunchv1;

import java.util.stream.Stream;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.ScrolledComposite;
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
	Text mandant;
	Browser browser;
	ToolItem kantinenPlanItem;
	ToolItem lunchDecItem;
	ToolItem votingItem;
	ToolItem mandantenauswahlItem;

	LunchdecKlasse lunchDeclayer;
	KPlanKlasse kantinenLayer;
	MandantAuswahlKlasse mandantenAuswahlLayer;
	VotingKlasse votingLayer;
	Shell shell;
	Display display;
	private ScrolledComposite scrolledComposite;
	private Composite content;

	public int createUI() {
		display = Display.getDefault();
		shell = new Shell(display, SWT.NONE);
		shell.setBounds(display.getBounds());
		FillLayout layout = new FillLayout();
		shell.setLayout(layout);

		scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		content = new Composite(scrolledComposite, SWT.NONE);
		content.setLayout(new GridLayout(1, true));
		createLayer(content);

		scrolledComposite.setContent(content);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinSize(content.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		shell.open();
		shell.layout();

		display.addListener(SWT.Resize, (event) -> shell.setBounds(display.getBounds()));
		return 0;
	}

	private Composite createLayer(Composite parent) {

		Composite headerComp = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(headerComp);

		headerComp.setLayout(new GridLayout(1, false));

		mandant = new Text(headerComp, SWT.NONE);
		mandant.setText("Mandant: ");
		mandant.setEditable(false);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(mandant);

		Composite logolayer = createLogoLayer(headerComp);
		GridDataFactory.fillDefaults().grab(false, false).align(SWT.CENTER, SWT.CENTER).applyTo(logolayer);

		Composite toolbarlayer = createToolBarLayer(headerComp);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(toolbarlayer);

		kantinenPlanItem.addListener(SWT.Selection, (event) -> activate(kantinenLayer));
		lunchDecItem.addListener(SWT.Selection, (event) -> activate(lunchDeclayer));
		votingItem.addListener(SWT.Selection, (event) -> activate(votingLayer));
		mandantenauswahlItem.addListener(SWT.Selection, (event) -> activate(mandantenAuswahlLayer));
		
		Composite contentPane = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		contentPane.setLayout(gridLayout);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(contentPane);
		lunchDeclayer = createLunchDecLayer(contentPane);
		kantinenLayer = createKantinenPlanLayer(contentPane);
		mandantenAuswahlLayer = createMandantAuswahlLayer(contentPane);
		votingLayer = createVotinLayer(contentPane);
		activate(mandantenAuswahlLayer);

		mandantenAuswahlLayer.registerOnButtonClick(
				(combo) -> mandant.setText("Mandant: " + combo.getItem(combo.getSelectionIndex())));

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
		Image image = new Image(result.getDisplay(), "res\\Test.png");
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
		ToolBar dieToolbar = new ToolBar(result, SWT.HORIZONTAL | SWT.BORDER);
		lunchDecItem = new ToolItem(dieToolbar, SWT.PUSH);
		lunchDecItem.setText("LunchFinder");
		new ToolItem(dieToolbar, SWT.SEPARATOR);
		votingItem = new ToolItem(dieToolbar, SWT.PUSH);
		votingItem.setText("Voting");
		new ToolItem(dieToolbar, SWT.SEPARATOR);
		kantinenPlanItem = new ToolItem(dieToolbar, SWT.PUSH);
		kantinenPlanItem.setText("Wochenplan Kantine");
		new ToolItem(dieToolbar, SWT.SEPARATOR);
		mandantenauswahlItem = new ToolItem(dieToolbar, SWT.PUSH);
		mandantenauswahlItem.setData(RWT.CUSTOM_VARIANT, "mandantToolItem");
		mandantenauswahlItem.setText("Mandantenauswahl");

		dieToolbar.addListener(SWT.Dispose, evt -> lunchDecItem.dispose());
	}

	private LunchdecKlasse createLunchDecLayer(Composite parent) {
		return new LunchdecKlasse(parent, SWT.NONE);

	}

	private KPlanKlasse createKantinenPlanLayer(Composite parent) {
		return new KPlanKlasse(parent, SWT.NONE);
	}

	private MandantAuswahlKlasse createMandantAuswahlLayer(Composite parent) {
		return new MandantAuswahlKlasse(parent, SWT.NONE);
	}

	private VotingKlasse createVotinLayer(Composite parent) {
		return new VotingKlasse(parent, SWT.NONE);
	}

	private void activate(Composite layer) {
		Stream.of(layer.getParent().getChildren()).forEach(child -> {
			child.setVisible(child == layer);
			GridData layoutData = (GridData) child.getLayoutData();
			if( layoutData != null ) {
				layoutData.exclude = child != layer;
			}
			
		});
		scrolledComposite.setMinSize(layer.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		layer.setBounds(scrolledComposite.getClientArea());
	}

}
