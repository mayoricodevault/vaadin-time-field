package com.example.derp;

import java.util.Locale;

import org.vaadin.thomas.timefield.TimeField;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DerpApplication extends UI {

	private static final long serialVersionUID = 547417272819355892L;

	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		int i = 1;
		TextField tf = new TextField();
		tf.setTabIndex(i++);
		content.addComponent(tf);
		tf = new TextField();
		tf.setTabIndex(i++);
		content.addComponent(tf);

		final TimeField f = new TimeField();
		f.setLocale(Locale.FRANCE);
		f.setWidth("200px");
		f.setTabIndex(i++);
		content.addComponent(f);

		tf = new TextField();
		tf.setTabIndex(i++);
		content.addComponent(tf);

		TimeField f2 = new TimeField();
		f2.setResolution(Resolution.SECOND);
		f2.setLocale(Locale.US);
		f2.setWidth("200px");
		content.addComponent(f2);

		f2.setPropertyDataSource(f);

		tf = new TextField();
		tf.setTabIndex(i++);
		content.addComponent(tf);
		tf = new TextField();
		tf.setTabIndex(i++);
		content.addComponent(tf);

		setContent(content);

		f2 = new TimeField();
		f2.setWidth("200px");
		f2.setEnabled(false);
		content.addComponent(f2);

		f2 = new TimeField();
		f2.setWidth("200px");
		f2.setReadOnly(true);
		content.addComponent(f2);

		f2 = new TimeField();
		f2.setWidth("200px");
		f2.setResolution(Resolution.MINUTE);
		f2.setMinuteInterval(1230);
		content.addComponent(f2);

	}
}
