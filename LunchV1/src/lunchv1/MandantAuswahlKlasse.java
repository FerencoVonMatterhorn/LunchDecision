package lunchv1;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class MandantAuswahlKlasse extends Composite {
	private Button derausw�hlenButton;
	private Set<Consumer<Combo>> actionOnClicks;

	public MandantAuswahlKlasse(Composite parent, int style) {
		super(parent, style);
		actionOnClicks = new HashSet<>();
		createMandantAuswahlLayer(this);
	}

	private Composite createMandantAuswahlLayer(Composite parent) {
		parent.setLayout(new GridLayout());

		Text mandant = new Text(parent, SWT.NONE);
		mandant.setText("Bitte w�hlen sie ihren Mandanten aus:");
		mandant.setEditable(false);

		Combo mandantauswahl = new Combo(parent, SWT.DROP_DOWN | SWT.BORDER);
		mandantauswahl.add("Horvay, Ferenc");
		mandantauswahl.add("Deister, Andreas");
		mandantauswahl.add("Struck, Rainer");
		mandantauswahl.add("Schaefer, Christian");
		mandantauswahl.add("Gutbrod, Luca");
		mandantauswahl.add("Skaley, Eduard");
		mandantauswahl.add("Appel, Frank");
		mandantauswahl.add("Hauck, Michael");
		mandantauswahl.add("Obrecht, Michael");
		mandantauswahl.addListener(SWT.Modify,
				event -> actionEnableButton(mandantauswahl));
		derausw�hlenButton = new Button(parent, SWT.BORDER);
		derausw�hlenButton.setText("Ausw�hlen");

		derausw�hlenButton.addListener(SWT.Selection, (event) -> {
			actionOnClicks.forEach(action -> action.accept(mandantauswahl));
		});
		derausw�hlenButton.setEnabled(false);
		return parent;
	}

	private void actionEnableButton(Combo mandantauswahl) {
		derausw�hlenButton.setEnabled(!(mandantauswahl.getSelectionIndex() == -1));
	}

	public void registerOnButtonClick(Consumer<Combo> actionOnClick) {
		actionOnClicks.add(actionOnClick);
	}
}
