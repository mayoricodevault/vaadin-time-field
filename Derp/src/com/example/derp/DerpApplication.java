package com.example.derp;

import java.util.Locale;

import org.vaadin.thomas.timefield.TimeField;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DerpApplication extends UI {

	private static final long serialVersionUID = 547417272819355892L;

	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		setContent(content);

		final TimeField f = new TimeField();
		f.setLocale(Locale.FRANCE);
		f.setWidth("200px");
		f.setImmediate(true);
		f.setHours(0);
		content.addComponent(f);

		TimeField f2 = new TimeField();
		f2.setResolution(Resolution.SECOND);
		f2.setLocale(Locale.US);
		f2.setWidth("200px");
		// f2.setHourMin(1);
		f2.setHourMax(14);
		content.addComponent(f2);
		f2.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				Notification.show(event.getProperty().getValue() + "");
			}
		});

		f2.setPropertyDataSource(f);

		f2 = new TimeField();
		f2.setWidth("200px");
		f2.setResolution(Resolution.MINUTE);
		f2.setMinutes(40);
		f2.setMinuteInterval(30);
		f2.setHourMin(-12);
		f2.setHourMax(3);
		content.addComponent(f2);

		f2 = new TimeField();
		f2.setWidth("200px");
		f2.setEnabled(false);
		content.addComponent(f2);

		f2 = new TimeField();
		f2.setWidth("200px");
		f2.setReadOnly(true);
		content.addComponent(f2);

	}
}
