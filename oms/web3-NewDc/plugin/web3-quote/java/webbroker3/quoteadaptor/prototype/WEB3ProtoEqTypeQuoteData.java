head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3ProtoEqTypeQuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : WebBroker3プロトタイプ用株式時価情報クラス(WEB3ProtoEqtypeQuoteData.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/02 山田　@卓司(FLJ) 新規作成
 *                  : 2005/03/30 山田　@卓司(FLJ) 値が取得できない場合は「0」を返すように変更（前日比以外）
 */
package webbroker3.quoteadaptor.prototype;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.quoteadaptor.*;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoRow;

/**
 * WebBroker3プロトタイプ用株式時価情報クラス。
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3ProtoEqTypeQuoteData
    implements WEB3EqTypeQuoteData, WEB3IfoQuoteData, WEB3IndexQuoteData
{

    /**
     * 時価情報のデータベースモデル
     */
    Web3QuoteProtoRow row;
    
    IndexType indexType;

    public WEB3ProtoEqTypeQuoteData(Web3QuoteProtoRow row)
    {
        if (row == null)
        {
            throw new IllegalArgumentException("row is required.");
        }
        this.row = row;
        this.indexType = IndexType.UNDEFINED;
    }

    public Date getQuoteDate()
    {
        return toDate(row.getQuoteDate());
    }

    public RealType getRealType()
    {
        RealType realType = row.getRealType();
        if (realType != null)
        {
            return realType;
        } else
        {
            return RealType.UNDEFINED;
        }
    }

    public DataType getDataType()
    {
        DataType dataType = row.getDataType();
        if (dataType != null)
        {
            return dataType;
        } else
        {
            return DataType.UNDEFINED;
        }
    }

    public String getMarketCode()
    {
        return row.getMarketCode();
    }

    public String getProductCode()
    {
        return row.getProductCode();
    }

    public String getMonthOfDelivery()
    {

        return row.getContractMonth();
    }

    public PutAndCall getPutAndCall()
    {
        PutAndCall putAndCall = PutAndCall.UNDEFINED;
        try
        {
            int intValue = Integer.parseInt(row.getPutAndCall());
            switch (intValue)
            {
                case IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS :
                    putAndCall = PutAndCall.CALL;
                    break;
                case IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS :
                    putAndCall = PutAndCall.PUT;
                    break;
                default :
                    break;
            }
        } catch (NumberFormatException ne)
        {
        }
        return putAndCall;
    }

    public double getStrikePrice()
    {
        return getColumn("strike_price", 0.0d);
    }

    public double getCurrentPrice()
    {
        return getColumn("current_price", 0.0d);
    }

    public double getBidPrice()
    {
        return getColumn("bid_price", 0.0d);
    }

    public double getAskPrice()
    {
        return getColumn("ask_price", 0.0d);
    }

    public double getOpenPrice()
    {
        return getColumn("open_price", 0.0d);
    }

    public Timestamp getOpenPriceTime()
    {
        return toTimestamp(row.getOpenPriceTime());
    }

    public double getHighPrice()
    {
        return getColumn("high_price", 0.0d);
    }

    public Timestamp getHighPriceTime()
    {
        return toTimestamp(row.getHighPriceTime());
    }

    public double getLowPrice()
    {
        return getColumn("low_price", 0.0d);
    }

    public Timestamp getLowPriceTime()
    {
        return toTimestamp(row.getLowPriceTime());
    }

    public Timestamp getCurrentPriceTime()
    {
        return getQuoteTimestamp();
    }

    public CurrentPriceFlag getCurrentPriceFlag()
    {
        CurrentPriceFlag flag = row.getCurrentPriceFlag();
        if (flag != null)
        {
            return flag;
        } else
        {
            return CurrentPriceFlag.NORMAL;
        }
    }

    public double getChange()
    {
        return getColumn("change", Double.NaN);
    }

    public double getVolume()
    {
        return getColumn("volume", 0.0d);
    }

    public Timestamp getVolumeTime()
    {
        return toTimestamp(row.getVolumeTime());
    }

    public AskPriceTitle getAskPriceTitle()
    {
        AskPriceTitle title = row.getAskPriceTitle();
        if (title != null)
        {
            return title;
        } else
        {
            return AskPriceTitle.UNDEFINED;
        }
    }

    public Timestamp getAskPriceTime()
    {
        return toTimestamp(row.getAskPriceTime());
    }

    public BidPriceTitle getBidPriceTitle()
    {
        BidPriceTitle title = row.getBidPriceTitle();
        if (title != null)
        {
            return title;
        } else
        {
            return BidPriceTitle.UNDEFINED;
        }
    }

    public Timestamp getBidPriceTime()
    {
        return toTimestamp(row.getBidPriceTime());
    }

    public double getBasePrice()
    {
        return getColumn("base_price", 0.0d);
    }

    public Timestamp getQuoteTimestamp()
    {
        return toTimestamp(row.getCurrentPriceTime());
    }

    public double getLastClosingPrice()
    {
        return getBasePrice();
    }
    
    public IndexType getIndexType()
    {
        return indexType;
    }

    private Date toDate(String source)
    {
        if (source != null)
        {
            DateFormat dateFormat =
                GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            try
            {
                return dateFormat.parse(source);
            } catch (ParseException pe)
            {
            }
        }
        return null;
    }

    private Timestamp toTimestamp(String source)
    {
        if (source != null)
        {
            DateFormat dateFormat =
                GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd HHmm");
            StringBuffer buf = new StringBuffer();
            buf.append(row.getQuoteDate());
            buf.append(" ");
            buf.append(source);
            try
            {
                return new Timestamp(
                    dateFormat.parse(buf.toString()).getTime());
            } catch (ParseException pe)
            {
            }
        }
        return null;
    }
    
    protected double getColumn(String columnName, double defaultValue)
    {
        Double value = (Double) row.getColumn(columnName);
        return (value != null) ? value.doubleValue() : defaultValue;
    }
    
}
@
