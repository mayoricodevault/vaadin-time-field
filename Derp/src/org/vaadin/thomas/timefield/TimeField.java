package org.vaadin.thomas.timefield;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;

/**
 * A field for selecting time values.
 * <p>
 * Uses {@link Date} as internal data type.
 * 
 * @author Thomas Mattsson / Vaadin OY
 */
public class TimeField extends CustomField<Date> {

	private static final long serialVersionUID = -676425827861766118L;

	private static final String VALUE_AM = "am";
	private static final String VALUE_PM = "pm";

	private boolean use24HourClock = true;
	private Locale givenLocale = null;

	private final NativeSelect hourSelect;
	private final NativeSelect minuteSelect;
	private final NativeSelect secondSelect;
	private final NativeSelect ampmSelect;

	private Resolution resolution = Resolution.MINUTE;

	private boolean maskInternalValueChange = false;

	private HorizontalLayout root;

	public TimeField(String caption) {
		this();
		setCaption(caption);
	}

	public TimeField() {

		hourSelect = getSelect();
		minuteSelect = getSelect();
		secondSelect = getSelect();
		ampmSelect = getSelect();

		root = new HorizontalLayout();
		root.setHeight(null);
		root.setWidth(null);

		fill(hourSelect, use24HourClock ? 23 : 12, use24HourClock);
		root.addComponent(hourSelect);

		fill(minuteSelect, 59, true);
		root.addComponent(minuteSelect);

		fill(secondSelect, 59, true);
		root.addComponent(secondSelect);

		ampmSelect.addItem(VALUE_AM);
		ampmSelect.addItem(VALUE_PM);
		root.addComponent(ampmSelect);

		// when setValue() is called, update selects
		addValueChangeListener(new Property.ValueChangeListener() {

			private static final long serialVersionUID = 3383351188340627219L;

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				if (maskInternalValueChange) {
					return;
				}
				maskInternalValueChange = true;
				updateFields();
				maskInternalValueChange = false;
			}
		});

		addStyleName("timefield");
		setSizeUndefined();

		initValue();
	}

	@SuppressWarnings("deprecation")
	private void initValue() {

		Date newDate = new Date(0);
		newDate.setSeconds(0);
		newDate.setMinutes(0);
		newDate.setHours(0);
		setValue(newDate);
	}

	/**
	 * Returns hour value in 24-hour format (0-23)
	 */
	@SuppressWarnings("deprecation")
	public int getHours() {
		Date d = getValue();
		return d.getHours();
	}

	/**
	 * Set hour in 24-hour format (0-23)
	 * 
	 * @param hours
	 */
	public void setHours(int hours) {

		if (!use24HourClock) {

			if (hours > 12) {
				ampmSelect.select(VALUE_PM);
				hours %= 12;
			} else {
				ampmSelect.select(VALUE_AM);
			}

			if (hours == 0) {
				hours = 12;
			}
		}
		hourSelect.setValue(hours);
	}

	@SuppressWarnings("deprecation")
	public int getMinutes() {
		Date d = getValue();
		return d.getMinutes();
	}

	public void setMinutes(int minutes) {
		minuteSelect.setValue(minutes);
	}

	@SuppressWarnings("deprecation")
	public int getSeconds() {
		Date d = getValue();
		return d.getSeconds();
	}

	public void setSeconds(int seconds) {
		secondSelect.setValue(seconds);
	}

	private void fill(NativeSelect s, int max, boolean includeZero) {

		s.removeAllItems();
		int startVal;
		if (includeZero) {
			startVal = 0;
		} else {
			startVal = 1;
		}
		for (int i = startVal; i <= max; i++) {
			s.addItem(i);
		}
	}

	private NativeSelect getSelect() {
		NativeSelect select = new NativeSelect() {
			private static final long serialVersionUID = 7956455947702461208L;

			@Override
			public String getItemCaption(Object itemId) {

				if (itemId instanceof Integer) {
					int val = (Integer) itemId;

					if (val < 10) {
						return "0" + val;
					}
					return val + "";
				}
				return super.getItemCaption(itemId);
			}
		};
		select.setImmediate(true);
		select.setNullSelectionAllowed(false);
		select.addValueChangeListener(new Property.ValueChangeListener() {

			private static final long serialVersionUID = 3383351188340627219L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				if (maskInternalValueChange) {
					return;
				}
				maskInternalValueChange = true;
				updateValue();
				TimeField.this.fireValueChange(true);
				maskInternalValueChange = false;

			}
		});
		return select;
	}

	@SuppressWarnings("deprecation")
	private void updateValue() {

		Date newDate = new Date(0);
		newDate.setSeconds(0);
		newDate.setMinutes(0);
		newDate.setHours(0);

		int val;
		if (resolution.ordinal() <= Resolution.HOUR.ordinal()) {
			val = (Integer) hourSelect.getValue();

			if (use24HourClock) {
				newDate.setHours(val);
			} else {
				if (VALUE_PM.equals(ampmSelect.getValue()) && val != 12) {
					val += 12;
					val %= 24;
				} else if (VALUE_AM.equals(ampmSelect.getValue()) && val == 12) {
					val = 0;
				}
				newDate.setHours(val);
			}
		}
		if (resolution.ordinal() < Resolution.HOUR.ordinal()) {
			val = (Integer) minuteSelect.getValue();
			newDate.setMinutes(val);
		}
		if (resolution.ordinal() < Resolution.MINUTE.ordinal()) {
			val = (Integer) secondSelect.getValue();
			newDate.setSeconds(val);
		}

		setValue(newDate);
	}

	@SuppressWarnings("deprecation")
	private void updateFields() {

		Date val = getValue();
		if (val == null) {
			hourSelect.setValue(null);
			minuteSelect.setValue(null);
			secondSelect.setValue(null);
			ampmSelect.setValue(null);
		}

		fill(hourSelect, use24HourClock ? 23 : 12, use24HourClock);
		if (use24HourClock) {
			hourSelect.setValue(val.getHours());
		} else {
			int h = val.getHours();
			if (h == 0) {
				h = 12;
			} else {
				h %= 12;
			}
			hourSelect.setValue(h);
		}

		minuteSelect.setVisible(resolution.ordinal() < Resolution.HOUR
				.ordinal());
		minuteSelect.setValue(val.getMinutes());

		secondSelect.setVisible(resolution.ordinal() < Resolution.MINUTE
				.ordinal());
		secondSelect.setValue(val.getSeconds());

		ampmSelect.setVisible(!use24HourClock);
		if (val.getHours() < 12) {
			ampmSelect.setValue(VALUE_AM);
		} else {
			ampmSelect.setValue(VALUE_PM);
		}

		// fix 12:xx am
		if (!use24HourClock && val.getHours() == 0) {
			hourSelect.setValue(12);
		}
	}

	/**
	 * Sets locale that is used for time format detection. Defaults to browser
	 * locale.
	 */
	@Override
	public void setLocale(Locale l) {
		givenLocale = l;

		DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT,
				givenLocale);
		String time = df.format(new Date());

		if (time.contains("am") || time.contains("AM") || time.contains("pm")
				|| time.contains("PM")) {
			use24HourClock = false;
		} else {
			use24HourClock = true;
		}
		maskInternalValueChange = true;
		updateFields();
		maskInternalValueChange = false;
	}

	/**
	 * Only resolutions of HOUR, MIN, SECOND are supported. Default is
	 * DateField.RESOLUTION_MIN.
	 * 
	 * @see DateField#setResolution(int)
	 * @param resolution
	 */
	public void setResolution(Resolution resolution) {

		if (this.resolution.ordinal() < resolution.ordinal()) {
			if (resolution.ordinal() > Resolution.SECOND.ordinal()) {
				secondSelect.setValue(0);
			}
			if (resolution.ordinal() > Resolution.MINUTE.ordinal()) {
				minuteSelect.setValue(0);
			}
		}
		this.resolution = resolution;
		maskInternalValueChange = true;
		updateFields();
		maskInternalValueChange = false;
	}

	/**
	 * @see DateField#getResolution()
	 * @return
	 */
	public Resolution getResolution() {
		return resolution;
	}

	@Override
	public void attach() {
		super.attach();

		// if there isn't a given locale at this point, use the one from the
		// browser
		if (givenLocale == null) {
			// we have access to application after attachment
			@SuppressWarnings("deprecation")
			Locale locale = getUI().getSession().getBrowser().getLocale();
			givenLocale = locale;
		}
	}

	public void set24HourClock(boolean use24hourclock) {
		use24HourClock = use24hourclock;
		maskInternalValueChange = true;
		updateFields();
		maskInternalValueChange = false;
	}

	public boolean is24HourClock() {
		return use24HourClock;
	}

	@Override
	public Class<? extends Date> getType() {
		return Date.class;
	}

	@Override
	protected Component initContent() {

		return root;
	}
}
