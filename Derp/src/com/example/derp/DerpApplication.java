package com.example.derp;

import java.util.Locale;

import org.vaadin.thomas.timefield.TimeField;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DerpApplication extends UI {

	private static final long serialVersionUID = 547417272819355892L;

	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

		TimeField f = new TimeField();
		f.setLocale(Locale.FRANCE);
		f.setWidth("200px");
		content.addComponent(f);

		TimeField f2 = new TimeField();
		f2.setLocale(Locale.US);
		f2.setWidth("200px");
		content.addComponent(f2);

		f2.setPropertyDataSource(f);

		setContent(content);
	}
}
