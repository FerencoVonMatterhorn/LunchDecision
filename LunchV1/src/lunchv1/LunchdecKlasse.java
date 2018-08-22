package lunchv1;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class LunchdecKlasse extends Composite {
	Browser browser;
	Composite searchbarlayer;
	Composite searchbarlayer1;

	public LunchdecKlasse(Composite parent, int style) {
		super(parent, style);
		searchbarlayer = createSearchBarLayer(this);
		searchbarlayer1 = createSearchBarLayer1(this);
		createLunchDecLayer(this);
	}

	private Composite createLunchDecLayer(Composite parent) {
		parent.setLayout(new GridLayout(1, true));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(parent);

		browser = new Browser(parent, SWT.BORDER); // Uses IE on MS
		browser.setUrl("https://wego.here.com/?map=48.47265,7.92901,15,satellite");

		GridDataFactory.fillDefaults().grab(true, true).applyTo(browser);

		return parent;
	}

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
}
