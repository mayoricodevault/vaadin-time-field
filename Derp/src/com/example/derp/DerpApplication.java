package com.example.derp;

import java.util.Locale;

import org.vaadin.thomas.timefield.TimeField;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DerpApplication extends UI {

	private static final long serialVersionUID = 547417272819355892L;

	@Override
	public void init() {
		Window mainWindow = new Window("");

		HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();
		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidth("100%");

		HorizontalLayout child = new HorizontalLayout();
		child.setSpacing(true);

		final TimeField f = new TimeField();
		f.setLocale(Locale.FRANCE);
		f.setWidth("200px");
		content.addComponent(f);

		hl.addComponent(child);
		hl.setComponentAlignment(child, Alignment.TOP_CENTER);

		content.addComponent(hl);

		mainWindow.addComponent(content);

		DateField df = new DateField();
		df.setTabIndex(34);
		content.addComponent(df);

		// f.setValue(null);

		Button b = new Button("adad");
		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				f.setValue(null);
			}
		});

		content.addComponent(b);
	}
}
