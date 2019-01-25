// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqtypeSampleQuoteDataRow.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import java.sql.Timestamp;

public interface EqtypeSampleQuoteDataRow
    extends Row
{

    public abstract String getProductCode();

    public abstract boolean getProductCodeIsSet();

    public abstract String getMarketCode();

    public abstract boolean getMarketCodeIsSet();

    public abstract double getOpenPrice();

    public abstract boolean getOpenPriceIsNull();

    public abstract double getCurrentPrice();

    public abstract boolean getCurrentPriceIsNull();

    public abstract double getBidPrice();

    public abstract boolean getBidPriceIsNull();

    public abstract double getAskPrice();

    public abstract boolean getAskPriceIsNull();

    public abstract double getVolume();

    public abstract boolean getVolumeIsNull();

    public abstract double getBidSize();

    public abstract boolean getBidSizeIsNull();

    public abstract double getAskSize();

    public abstract boolean getAskSizeIsNull();

    public abstract double getYtdLowPrice();

    public abstract boolean getYtdLowPriceIsNull();

    public abstract double getYtdHighPrice();

    public abstract boolean getYtdHighPriceIsNull();

    public abstract double getLastClosingPrice();

    public abstract boolean getLastClosingPriceIsNull();

    public abstract double getPrevTradingDayClosingPrice();

    public abstract boolean getPrevTradingDayClosingPriceIsNull();

    public abstract double getDiff();

    public abstract boolean getDiffIsNull();

    public abstract Timestamp getCreatedTimestamp();

    public abstract boolean getCreatedTimestampIsSet();

    public abstract Timestamp getLastUpdatedTimestamp();

    public abstract boolean getLastUpdatedTimestampIsSet();

    public static final RowType TYPE = new RowType("eqtype_sample_quote_data", "master");

}
