// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISO8601Format.java

package com.fitechlabs.xtrade.kernel.util;

import java.text.*;
import java.util.*;

public class ISO8601Format extends SimpleDateFormat
{

    private ISO8601Format()
    {
        hasFractionSecond = false;
        doTimeZone = true;
        format = "yyyy-MM-dd'T'HH:mm:ss";
        formatType = 0;
    }

    private ISO8601Format(String pattern)
    {
        hasFractionSecond = false;
        doTimeZone = true;
        format = "yyyy-MM-dd'T'HH:mm:ss";
        formatType = 0;
    }

    private ISO8601Format(String pattern, DateFormatSymbols formatSymbols)
    {
        hasFractionSecond = false;
        doTimeZone = true;
        format = "yyyy-MM-dd'T'HH:mm:ss";
        formatType = 0;
    }

    private ISO8601Format(String pattern, Locale loc)
    {
        hasFractionSecond = false;
        doTimeZone = true;
        format = "yyyy-MM-dd'T'HH:mm:ss";
        formatType = 0;
    }

    public ISO8601Format(int formatType, boolean utc)
    {
        hasFractionSecond = false;
        doTimeZone = true;
        format = "yyyy-MM-dd'T'HH:mm:ss";
        this.formatType = 0;
        setFormatType(formatType, utc);
    }

    private void setFormatType(int formatType, boolean utc)
    {
        this.formatType = formatType;
        if(utc && formatType > 3)
            setTimeZone(TimeZone.getTimeZone("UTC"));
        else
            setTimeZone(TimeZone.getDefault());
        switch(formatType)
        {
        case 1: // '\001'
            format = "yyyy";
            hasFractionSecond = false;
            doTimeZone = false;
            break;

        case 2: // '\002'
            format = "yyyy-MM";
            hasFractionSecond = false;
            doTimeZone = false;
            break;

        case 3: // '\003'
            format = "yyyy-MM-dd";
            hasFractionSecond = false;
            doTimeZone = false;
            break;

        case 4: // '\004'
            hasFractionSecond = false;
            doTimeZone = true;
            format = "yyyy-MM-dd'T'HH:mm";
            break;

        case 5: // '\005'
            hasFractionSecond = false;
            doTimeZone = true;
            format = "yyyy-MM-dd'T'HH:mm:ss";
            break;

        case 6: // '\006'
        default:
            format = "yyyy-MM-dd'T'HH:mm:ss";
            doTimeZone = true;
            hasFractionSecond = true;
            break;
        }
    }

    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition)
    {
        Calendar cal = getCalendar();
        cal.setTime(date);
        applyPattern(format);
        super.format(date, toAppendTo, fieldPosition);
        NumberFormat nf = new DecimalFormat("00");
        if(hasFractionSecond)
        {
            int millis = cal.get(14);
            int frac = millis / 10;
            toAppendTo.append(".").append(nf.format(frac));
        }
        if(doTimeZone)
        {
            TimeZone tz2 = cal.getTimeZone();
            int offset = tz2.getOffset(cal.get(0), cal.get(1), cal.get(2), cal.get(5), cal.get(7), cal.get(14));
            if(offset == 0)
            {
                toAppendTo.append("Z");
            } else
            {
                offset /= 60000;
                int om = Math.abs(offset % 60);
                int oh = Math.abs(offset / 60);
                String os = "+";
                if(offset < 0)
                    os = "-";
                toAppendTo.append(os).append(nf.format(oh));
                if(om > 0)
                    toAppendTo.append(nf.format(om));
            }
        }
        return toAppendTo;
    }

    public Date parse(String text, ParsePosition pos)
    {
        Date retVal = null;
        try
        {
            Calendar cal = Calendar.getInstance();
            doParse(text, cal, pos);
            retVal = cal.getTime();
        }
        catch(Exception ex)
        {
            pos.setErrorIndex(pos.getIndex());
        }
        return retVal;
    }

    private String getNext(StringTokenizer tokens, ParsePosition pos)
    {
        if(tokens.hasMoreTokens())
        {
            String tok = tokens.nextToken();
            if(tok != null)
                pos.setIndex(tok.length());
            return tok;
        } else
        {
            return null;
        }
    }

    private void doParse(String text, Calendar cal, ParsePosition pos)
        throws Exception
    {
        cal.setTime(new Date(0L));
        StringTokenizer tokens = new StringTokenizer(text, "-T:+.Z", true);
        String current = getNext(tokens, pos);
        if(current != null)
            handleYear(current, tokens, cal, pos);
    }

    private void handleYear(String year, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        cal.set(1, Integer.parseInt(year));
        String current = getNext(tokens, pos);
        if(current != null)
        {
            if(current.equals("-"))
            {
                current = getNext(tokens, pos);
                if(current != null)
                    handleMonth(current, tokens, cal, pos);
                else
                    throw new Exception("Month missing!");
            } else
            {
                throw new Exception("Bad token: " + current);
            }
        } else
        {
            zeroOut(cal, true, true, true);
        }
    }

    private void handleMonth(String month, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        cal.set(2, Integer.parseInt(month) - 1);
        String current = getNext(tokens, pos);
        if(current != null)
        {
            if(current.equals("-"))
            {
                current = getNext(tokens, pos);
                if(current != null)
                    handleDate(current, tokens, cal, pos);
                else
                    throw new Exception("date missing!");
            } else
            {
                throw new Exception("Bad token: " + current);
            }
        } else
        {
            zeroOut(cal, false, true, true);
        }
    }

    private void handleDate(String date, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        cal.set(5, Integer.parseInt(date));
        String current = getNext(tokens, pos);
        if(current != null)
        {
            if(current.equals("T"))
            {
                current = getNext(tokens, pos);
                if(current != null)
                    handleHours(current, tokens, cal, pos);
                else
                    throw new Exception("Time missing!");
            } else
            {
                throw new Exception("Bad token: " + current);
            }
        } else
        {
            zeroOut(cal, false, false, true);
        }
    }

    private void zeroOut(Calendar cal, boolean month, boolean date, boolean time)
    {
        if(month)
            cal.set(2, 0);
        if(date)
            cal.set(5, 1);
        if(time)
        {
            cal.set(11, 0);
            cal.set(12, 0);
            cal.set(14, 0);
        }
    }

    private void handleHours(String hours, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        cal.set(11, Integer.parseInt(hours));
        String current = getNext(tokens, pos);
        if(current != null)
        {
            if(current.equals(":"))
            {
                current = getNext(tokens, pos);
                if(current != null)
                    handleMinutes(current, tokens, cal, pos);
                else
                    throw new Exception("Minutes missing!");
            } else
            {
                throw new Exception("Bad token: " + current);
            }
        } else
        {
            throw new Exception("Illegal date, has hours, but no minutes");
        }
    }

    private void handleMinutes(String minutes, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        cal.set(12, Integer.parseInt(minutes));
        String current = getNext(tokens, pos);
        if(current != null)
        {
            if(current.equals(":"))
            {
                current = getNext(tokens, pos);
                if(current != null)
                    handleSeconds(current, tokens, cal, pos);
                else
                    throw new Exception("Seconds missing!");
            } else
            if(current.equals("+") || current.equals("-") || current.equals("Z"))
                handleTZD(current, tokens, cal, pos);
            else
                throw new Exception("Bad token: " + current);
        } else
        {
            throw new Exception("Illegal date, has hours, but no minutes");
        }
    }

    private void handleSeconds(String seconds, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        cal.set(13, Integer.parseInt(seconds));
        String current = getNext(tokens, pos);
        if(current != null)
        {
            if(current.equals("."))
            {
                current = getNext(tokens, pos);
                if(current != null)
                    handlePercentSeconds(current, tokens, cal, pos);
                else
                    throw new Exception("Percent seconds missing!");
            } else
            if(current.equals("+") || current.equals("-") || current.equals("Z"))
                handleTZD(current, tokens, cal, pos);
            else
                throw new Exception("Bad token: " + current);
        } else
        {
            throw new Exception("Illegal date, has hours, but no minutes");
        }
    }

    private void handlePercentSeconds(String percentSeconds, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        cal.set(14, Integer.parseInt(percentSeconds) * 10);
        String current = getNext(tokens, pos);
        if(current != null)
        {
            if(current.equals("+") || current.equals("-") || current.equals("Z"))
                handleTZD(current, tokens, cal, pos);
            else
                throw new Exception("Bad token: " + current);
        } else
        {
            throw new Exception("Missing TZD after percent seconds");
        }
    }

    private void handleTZD(String tzdType, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        String current = tzdType;
        if(tzdType.equals("Z"))
            convertToLocalTime(cal);
        else
        if(tzdType.equals("+") || tzdType.equals("-"))
        {
            current = getNext(tokens, pos);
            if(current != null)
                handleHourOffset(current, tzdType, tokens, cal, pos);
            else
                throw new Exception("Hours  missing!");
        } else
        {
            throw new Exception("Bad token: " + current);
        }
    }

    private void convertToLocalTime(Calendar cal)
    {
        Date date = cal.getTime();
        long time = date.getTime();
        TimeZone tz = cal.getTimeZone();
        int offset = tz.getOffset(cal.get(0), cal.get(1), cal.get(2), cal.get(5), cal.get(7), cal.get(14));
        time += offset;
        date.setTime(time);
        cal.setTime(date);
    }

    private void handleHourOffset(String hourOffset, String tzdType, StringTokenizer tokens, Calendar cal, ParsePosition pos)
        throws Exception
    {
        int multiplier = tzdType.equals("+") ? 1 : -1;
        TimeZone tz = cal.getTimeZone();
        int hours = hoursVal(Integer.parseInt(hourOffset));
        String current = getNext(tokens, pos);
        if(current != null)
        {
            if(current.equals(":"))
            {
                current = getNext(tokens, pos);
                int minutes = minutesVal(Integer.parseInt(current));
                cal.set(15, multiplier * (hours + minutes));
                cal.set(16, 0);
            } else
            {
                throw new Exception("Bad Token: " + current);
            }
        } else
        {
            cal.set(15, multiplier * hours);
            cal.set(16, 0);
        }
    }

    private int hoursVal(int hours)
    {
        return hours * 0x36ee80;
    }

    private int minutesVal(int minutes)
    {
        return minutes * 60000;
    }

    public static final int ISO_YEAR = 1;
    public static final int ISO_MONTH = 2;
    public static final int ISO_DATE = 3;
    public static final int ISO_MINUTE = 4;
    public static final int ISO_SECOND = 5;
    public static final int ISO_FRACTION_SECOND = 6;
    private static final String year = "yyyy";
    private static final String month = "yyyy-MM";
    private static final String date = "yyyy-MM-dd";
    private static final String minute = "yyyy-MM-dd'T'HH:mm";
    private static final String second = "yyyy-MM-dd'T'HH:mm:ss";
    private boolean hasFractionSecond;
    private boolean doTimeZone;
    private String format;
    private int formatType;
}
