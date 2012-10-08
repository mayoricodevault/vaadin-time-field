package com.example.derp;

import java.util.Date;

import org.vaadin.thomas.timefield.TimeField;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class DerpApplication extends UI {

	private static final long serialVersionUID = 547417272819355892L;

	@Override
	protected void init(VaadinRequest request) {

		HorizontalLayout content = new HorizontalLayout();
		content.setSpacing(true);

		TimeField f = new TimeField();
		f.setWidth("200px");
		content.addComponent(f);

		TimeField f2 = new TimeField();
		f2.setWidth("200px");
		content.addComponent(f2);

		f2.setPropertyDataSource(f);
		f.setValue(new Date());

		Button b = new Button();
		content.addComponent(b);
		b = new Button();
		content.addComponent(b);
		b = new Button();
		content.addComponent(b);

		setContent(content);
	}
}
