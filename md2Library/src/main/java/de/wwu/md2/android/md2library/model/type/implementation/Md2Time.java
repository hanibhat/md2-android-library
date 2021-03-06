package de.wwu.md2.android.md2library.model.type.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.wwu.md2.android.md2library.model.type.interfaces.Md2NumericType;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;

/**
 * {@inheritDoc}
 * <p/>
 * Implementation of data type time in MD2-DSL.
 * Uses calendar as platform representation.
 * <p/>
 * Created on 09/07/2015
 *
 * @author Fabian Wrede
 * @version 1.0
 * @since 1.0
 */
public class Md2Time extends AbstractMd2TemporalType {
    /**
     * Instantiates a new Md2 time.
     */
    public Md2Time() {
        super();
    }

    /**
     * Instantiates a new Md2 time.
     *
     * @param platformValue the platform value
     */
    public Md2Time(Calendar platformValue) {
        super(platformValue);
    }

    /**
     * Instantiates a new Md2 time.
     *
     * @param platformValue the platform value
     */
    public Md2Time(Md2String platformValue) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            calendar.setTime(sdf.parse(platformValue.getPlatformValue()));
        } catch (ParseException e) {
            this.platformValue = null;
            this.isSet = false;
        }

        this.platformValue = calendar;
        this.isSet = true;
    }

    @Override
    public Calendar getPlatformValue() {
        return (Calendar) platformValue;
    }

    @Override
    public Md2Type clone() {
        return new Md2Date(this.getPlatformValue());
    }

    @Override
    public boolean gt(Md2NumericType value) {
        Calendar calendarValue;
        try {
            calendarValue = tryGetDate(value);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return (this.getPlatformValue().after(calendarValue));
    }

    @Override
    public boolean gte(Md2NumericType value) {
        Calendar calendarValue;
        try {
            calendarValue = tryGetDate(value);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return (this.getPlatformValue().after(calendarValue) || this.getPlatformValue().equals(calendarValue));
    }

    @Override
    public boolean lt(Md2NumericType value) {
        Calendar calendarValue;
        try {
            calendarValue = tryGetDate(value);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return (this.getPlatformValue().before(calendarValue));
    }

    @Override
    public boolean lte(Md2NumericType value) {
        Calendar calendarValue;
        try {
            calendarValue = tryGetDate(value);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return (this.getPlatformValue().before(calendarValue) || this.getPlatformValue().equals(calendarValue));
    }

    private Calendar tryGetDate(Md2NumericType value) {
        if (value == null || !value.isSet())
            throw new IllegalArgumentException();
        try {
            return ((Md2Time) value).getPlatformValue();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Md2Type value) {
        if (!super.equals(value))
            return false;

        if (!(value instanceof Md2Time))
            return false;

        Md2Time timeValue = (Md2Time) value;

        return (this.getPlatformValue().equals(timeValue.platformValue));
    }

    @Override
    public Md2String getString() {
        return new Md2String(this.toString());
    }

    @Override
    public String toString() {
        Calendar calendar = this.getPlatformValue();
        if(calendar == null) return "";

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(calendar.getTime());
    }
}
