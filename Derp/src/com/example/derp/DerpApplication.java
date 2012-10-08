package com.example.derp;

import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Window;

public class DerpApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("");

		HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();
		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidth("100%");

		HorizontalLayout child = new HorizontalLayout();
		child.setSpacing(true);

		for (int i = 0; i < 3; i++) {
			Button button = new NativeButton();
			button.setIcon(new ThemeResource("../runo/favicon.ico"));
			button.setWidth("50px");
			button.setHeight("50px");
			child.addComponent(button);
		}

		hl.addComponent(child);
		hl.setComponentAlignment(child, Alignment.TOP_CENTER);

		content.addComponent(hl);

		mainWindow.addComponent(content);

		setMainWindow(mainWindow);
	}
}
