head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteEventImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteCashEventの実装クラス(WEB3QuoteEventImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/19 山田　@卓司(FLJ) 新規作成
                   2009/04/20 許　@　@　@　@競(FLJ) 時価システム切替対応
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;

import webbroker3.quoteadaptor.*;
import webbroker3.util.WEB3LogUtility;

/**
 * WEB3QuoteCashEventの実装クラス<br>
 * <BR>
 * 時価情報通知ハンドラに通知される時価イベントであり、
 * 時価サーバから配信された1件の時価レコードを表す。
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3QuoteEventImpl
    extends AbstractQuoteData
    implements WEB3QuoteEvent
{

    private static final WEB3LogUtility _log =
        WEB3LogUtility.getInstance(WEB3QuoteEventImpl.class);

    private static final boolean DBG = _log.ison();

    /**
     * 時価サーバーから受信した時価情報のフォーマット定義。
     * 
     * int配列の各要素には、時価データの各項目のデータ長が設定される。
     * 配列のインデックスは時価データの項目のインデックスに一致する。
     * 
     * byte配列で受信した、時価データは、ここで定義されたフォーマットの
     * 2次元のbyte[]配列に変換される。
     */
    private static final int[] RECORD_FORMAT;
    static {
        RECORD_FORMAT = new int[28];
        RECORD_FORMAT[0] = WEB3QuoteRecordFormat.QUOTE_DATE_SIZE;
        RECORD_FORMAT[1] = WEB3QuoteRecordFormat.REAL_TYPE_SIZE;
        RECORD_FORMAT[2] = WEB3QuoteRecordFormat.DATA_TYPE_SIZE;
        RECORD_FORMAT[3] = WEB3QuoteRecordFormat.MARKET_CODE_SIZE;
        RECORD_FORMAT[4] = WEB3QuoteRecordFormat.PRODUCT_CODE_SIZE;
        RECORD_FORMAT[5] = WEB3QuoteRecordFormat.MONTH_OF_DELIVERY_SIZE;
        RECORD_FORMAT[6] = WEB3QuoteRecordFormat.PUT_AND_CALL_SIZE;
        RECORD_FORMAT[7] = WEB3QuoteRecordFormat.STRIKE_PRICE_SIZE;
        RECORD_FORMAT[8] = WEB3QuoteRecordFormat.OPEN_PRICE_SIZE;
        RECORD_FORMAT[9] = WEB3QuoteRecordFormat.OPEN_PRICE_TIME_SIZE;
        RECORD_FORMAT[10] = WEB3QuoteRecordFormat.HIGH_PRICE_SIZE;
        RECORD_FORMAT[11] = WEB3QuoteRecordFormat.HIGH_PRICE_TIME_SIZE;
        RECORD_FORMAT[12] = WEB3QuoteRecordFormat.LOW_PRICE_SIZE;
        RECORD_FORMAT[13] = WEB3QuoteRecordFormat.LOW_PRICE_TIME_SIZE;
        RECORD_FORMAT[14] = WEB3QuoteRecordFormat.CURRENT_PRICE_SIZE;
        RECORD_FORMAT[15] = WEB3QuoteRecordFormat.CURRENT_PRICE_TIME_SIZE;
        RECORD_FORMAT[16] = WEB3QuoteRecordFormat.CURRENT_PRICE_FLAG_SIZE;
        RECORD_FORMAT[17] = WEB3QuoteRecordFormat.CHANGE_SIZE;
        RECORD_FORMAT[18] = WEB3QuoteRecordFormat.VOLUME_SIZE;
        RECORD_FORMAT[19] = WEB3QuoteRecordFormat.VOLUME_TIME_SIZE;
        RECORD_FORMAT[20] = WEB3QuoteRecordFormat.ASK_PRICE_TITLE_SIZE;
        RECORD_FORMAT[21] = WEB3QuoteRecordFormat.ASK_PRICE_SIZE;
        RECORD_FORMAT[22] = WEB3QuoteRecordFormat.ASK_PRICE_TIME_SIZE;
        RECORD_FORMAT[23] = WEB3QuoteRecordFormat.BID_PRICE_TITLE_SIZE;
        RECORD_FORMAT[24] = WEB3QuoteRecordFormat.BID_PRICE_SIZE;
        RECORD_FORMAT[25] = WEB3QuoteRecordFormat.BID_PRICE_TIME_SIZE;
        RECORD_FORMAT[26] = WEB3QuoteRecordFormat.BASE_PRICE_SIZE;
    }

    /**
     * この時価情報の基準日
     */
    private byte[] baseDate;

    /**
     * コンストラクタ
     */
    WEB3QuoteEventImpl()
    {
        super();
        baseDate = new byte[12];
    }

    /**
     * byte配列からデータをセットする。
     * 
     * @@param data 元データを保持したbyte配列
     * @@param offset データ読み込み開始インデックス
     * @@param length 読み込みデータ長
     */
    boolean setData(byte[] data, int offset, int length)
    {

        try
        {

            // この時価情報の基準日をセット
            int index = offset;
            setBaseDate(data, index, RECORD_FORMAT[0]);

            // 時価情報を設定
            quoteDate = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[0]);
            index += RECORD_FORMAT[0];
            
            realType = toRealType(data, index, RECORD_FORMAT[1]);
            index += RECORD_FORMAT[1];
            
            dataType = toDataType(data, index, RECORD_FORMAT[2]);
            index += RECORD_FORMAT[2];
            
            marketCode = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[3]);
            index += RECORD_FORMAT[3];
            
            productCode = toProductCode(data, index, RECORD_FORMAT[4]);
            index += RECORD_FORMAT[4];
            
            monthOfDelivery = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[5]);
            index += RECORD_FORMAT[5];
            
            putAndCall = toPutAndCall(data, index, RECORD_FORMAT[6]);
            index += RECORD_FORMAT[6];
            
            strikePrice = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[7]);
            index += RECORD_FORMAT[7];
            
            openPrice = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[8]);
            index += RECORD_FORMAT[8];
            
            openPriceTime = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[9]);
            index += RECORD_FORMAT[9];
            
            highPrice = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[10]);
            index += RECORD_FORMAT[10];
            
            highPriceTime = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[11]);
            index += RECORD_FORMAT[11];
            
            lowPrice = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[12]);
            index += RECORD_FORMAT[12];
            
            lowPriceTime = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[13]);
            index += RECORD_FORMAT[13];
            
            currentPrice = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[14]);
            index += RECORD_FORMAT[14];
            
            currentPriceTime = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[15]);
            index += RECORD_FORMAT[15];
            
            currentPriceFlag = toCurrentPriceFlag(data, index, RECORD_FORMAT[16]);
            index += RECORD_FORMAT[16];
            
            change = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[17]);
            index += RECORD_FORMAT[17];
            
            volume = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[18]);
            index += RECORD_FORMAT[18];
            
            volumeTime = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[19]);
            index += RECORD_FORMAT[19];
            
            askPriceTitle = toAskPriceTitle(data, index, RECORD_FORMAT[20]);
            index += RECORD_FORMAT[20];
            
            askPrice = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[21]);
            index += RECORD_FORMAT[21];
            
            askPriceTime = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[22]);
            index += RECORD_FORMAT[22];
            
            bidPriceTitle = toBidPriceTitle(data, index, RECORD_FORMAT[23]);
            index += RECORD_FORMAT[23];
            
            bidPrice = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[24]);
            index += RECORD_FORMAT[24];
            
            bidPriceTime = WEB3QuoteUtil.toString(data, index, RECORD_FORMAT[25]);
            index += RECORD_FORMAT[25];
            
            basePrice = WEB3QuoteUtil.toDouble(data, index, RECORD_FORMAT[26]);
            index += RECORD_FORMAT[26];
            
            if (DBG)
            {
                _log.debug("received quote data=" + new String(data, offset, length -1));
                _log.debug("parsed quote data=" + toString());
            }
            
        
            return true;

        } catch (Throwable t)
        {
            String s = new String(data, offset, length);
            _log.warn("Unexpected error occured while parsing quote data. SOURCE:" + s, t);
            return false;
        }
    }

    // 基準日を設定
    private void setBaseDate(byte[] data, int offset, int length)
    {
        System.arraycopy(data, offset, baseDate, 0, length);
    }

    // Timestamp変換
    private Timestamp toTimestamp(byte[] data, int offset, int length)
    {
        // 基準日 + HHmmでTimestampを生成
        System.arraycopy(data, offset, baseDate, 8, length);
        return WEB3QuoteUtil.toTimestamp(baseDate, 0, 12);
    }

    // 銘柄コード変換
    private String toProductCode(byte[] data, int offset, int length)
    {
        if (DataType.EQUITY.equals(dataType))
        {
            return WEB3QuoteUtil.toString(data, offset, length - WEB3QuoteProductCodes.EQUITY_SUFFIX.length());
        } else {
            return WEB3QuoteUtil.toString(data, offset, length);
        }
    }

    // RealType変換
    static RealType toRealType(byte[] data, int offset, int length)
    {
        try {
            return (RealType) EnumeratedManager.getInstance().valueFromInt(
                RealType.class,
                WEB3QuoteUtil.toInteger(data, offset, length));
        } catch (IllegalArgumentException iae)
        {
            _log.warn(iae.getMessage(), iae);
            return RealType.UNDEFINED;
        }
    }

    // DataType変換
    static DataType toDataType(byte[] data, int offset, int length)
    {
        try {
            return (DataType) EnumeratedManager.getInstance().valueFromInt(
                DataType.class,
                WEB3QuoteUtil.toInteger(data, offset, length));
        } catch (IllegalArgumentException iae)
        {
            _log.warn(iae.getMessage(), iae);
            return DataType.UNDEFINED;
        }
    }

    // PutAndCall変換
    private PutAndCall toPutAndCall(byte[] data, int offset, int length)
    {
        if (DataType.INDEX_OPTION.equals(dataType))
        {
            return (PutAndCall) EnumeratedManager.getInstance().valueFromString(
                    PutAndCall.class, 
                    WEB3QuoteUtil.toString(data, offset, length));
        }
        return PutAndCall.UNDEFINED;
    }

    // CurrentPriceFlag変換
    static CurrentPriceFlag toCurrentPriceFlag(byte[] data, int offset, int length)
    {
        return (CurrentPriceFlag) EnumeratedManager.getInstance().valueFromInt(
            CurrentPriceFlag.class,
            WEB3QuoteUtil.toInteger(data, offset, length));
    }

    // AskPriceTitle変換
    static AskPriceTitle toAskPriceTitle(byte[] data, int offset, int length)
    {
        return (AskPriceTitle) EnumeratedManager.getInstance().valueFromInt(
            AskPriceTitle.class,
            WEB3QuoteUtil.toInteger(data, offset, length));
    }

    // BidPriceTitle変換
    static BidPriceTitle toBidPriceTitle(byte[] data, int offset, int length)
    {
        return (BidPriceTitle) EnumeratedManager.getInstance().valueFromInt(
            BidPriceTitle.class,
            WEB3QuoteUtil.toInteger(data, offset, length));
    }

}@
