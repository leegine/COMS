head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoQuoteDataImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revision History : 
*/

package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.quoteadaptor.AskPriceTitle;
import webbroker3.quoteadaptor.BidPriceTitle;
import webbroker3.quoteadaptor.CurrentPriceFlag;
import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.PutAndCall;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;

public class WEB3IfoQuoteDataImplForMock implements WEB3IfoQuoteData
{
    private double currentPrice = 0.0;
    private double change = 0.0;
    private Timestamp currentPriceTime = null;

    public double getCurrentPrice()
    {
        // TODO Auto-generated method stub
        return this.currentPrice;
    }

    public double getBidPrice()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public double getAskPrice()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public double getOpenPrice()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public double getLastClosingPrice()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Timestamp getQuoteTimestamp()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Date getQuoteDate()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public RealType getRealType()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public DataType getDataType()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String getMarketCode()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String getProductCode()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String getMonthOfDelivery()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public PutAndCall getPutAndCall()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public double getStrikePrice()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Timestamp getOpenPriceTime()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public double getHighPrice()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Timestamp getHighPriceTime()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public double getLowPrice()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Timestamp getLowPriceTime()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Timestamp getCurrentPriceTime()
    {
        // TODO Auto-generated method stub
        return this.currentPriceTime;
    }

    public CurrentPriceFlag getCurrentPriceFlag()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public double getChange()
    {
        // TODO Auto-generated method stub
        return this.change;
    }

    public double getVolume()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Timestamp getVolumeTime()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public AskPriceTitle getAskPriceTitle()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Timestamp getAskPriceTime()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public BidPriceTitle getBidPriceTitle()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Timestamp getBidPriceTime()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public double getBasePrice()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setCurrentPrice(double l_dblCurrentPrice)
    {
        this.currentPrice = l_dblCurrentPrice;
    }
    
    public void setChange(double l_dblChange)
    {
        this.change = l_dblChange;
    }
    
    public void setCurrentPriceTime(Timestamp l_Tp)
    {
        this.currentPriceTime = l_Tp;
    }
}
@
