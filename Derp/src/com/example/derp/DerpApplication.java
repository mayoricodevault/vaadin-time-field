package com.example.derp;

import java.util.Date;
import java.util.Locale;

import org.vaadin.thomas.timefield.TimeField;

import com.vaadin.Application;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class DerpApplication extends Application {

	private static final long serialVersionUID = 547417272819355892L;

	@Override
	public void init() {
		Window mainWindow = new Window("");
		setMainWindow(mainWindow);

		HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();
		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidth("100%");

		HorizontalLayout child = new HorizontalLayout();
		child.setSpacing(true);

		final TimeField f = new TimeField();
		f.setLocale(Locale.FRANCE);
		f.setWidth("200px");
		f.setTabIndex(3);
		content.addComponent(f);

		hl.addComponent(child);
		hl.setComponentAlignment(child, Alignment.TOP_CENTER);

		content.addComponent(hl);

		mainWindow.addComponent(content);

		DateField df = new DateField();
		df.setTabIndex(34);
		content.addComponent(df);

		f.setValue(new Date());
		f.setValue(null);

		TextField tf = new TextField();
		tf.setTabIndex(2);
		mainWindow.addComponent(tf);

	}
}
