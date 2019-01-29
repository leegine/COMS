head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@QTPリッチクライアントデータプッシュ機@能単位サービス共通機@能実装(WEB3QtpRichPushUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 孫(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.text.DecimalFormat;
import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.rcp.*;
import webbroker3.rcp.define.WEB3QtpRichPushMarketCodeDef;
import webbroker3.rcp.define.WEB3QtpRichPushMarketNameDef;
import webbroker3.rcp.define.WEB3QtpRichPushOrderTypeNameDef;
import webbroker3.rcp.define.WEB3QtpRichPushUrlPatternDef;
import webbroker3.rcp.service.delegate.*;
import webbroker3.util.*;

/**
 * QTPリッチクライアントデータプッシュ機@能単位サービス共通機@能実装
 * 
 * @@author 孫
 * @@version 1.0
 */
public abstract class WEB3QtpRichPushUnitServiceImpl implements WEB3QtpRichPushUnitService
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3QtpRichPushUnitServiceImpl.class);

    /**
     * 数字の表示フォーマット
     */
    protected static final String DOUBLE_FORMAT = "###,###,###,###.######";
    
    /**
     * 時間フォーマット
     */
    protected static final String TIME_FORMAT = "yyyyMMddHHmmss";
    
    /**
     * スレッドごと数字表示フォーマットオブジェクト
     */
    private static final ThreadLocal decimalFormatTL = new ThreadLocal()
    {
        protected Object initialValue()
        {
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.applyPattern(DOUBLE_FORMAT);
            decimalFormat.setMaximumFractionDigits(300);
            decimalFormat.setMinimumIntegerDigits(1);
            return decimalFormat;
        }
    };

    /**
     * リッチクライアントデータプッシュ
     * 
     * @@param l_lngFromAccountId
     *            long
     * @@param l_lngToAccountId
     *            long
     */
    public abstract void push(long l_lngFromAccountId, long l_lngToAccountId) throws DataQueryException, DataNetworkException;

    /**
     * リッチクライアントプッシュオブジェクトソート用コンテクストへ保存
     * 
     * @@param l_lstPushObjects
     *            List
     * @@param l_mapPushHistoryObjects
     *            Map
     */
    protected void saveToContext(List l_lstPushObjects)
    {

        final String STR_METHOD_NAME = "saveToContext()";
        log.entering(STR_METHOD_NAME);

        if (l_lstPushObjects == null)
        {
            log.error("l_lstPushRecords==nulll! save fail!");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        WEB3RichPushObjectContext l_context = (WEB3RichPushObjectContext) ThreadLocalSystemAttributesRegistry
                .getAttribute(WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME);
        //プッシュオブジェクト一覧追加
        List l_lstAllObjects = l_context.getPushObjects();
        l_lstAllObjects.addAll(l_lstPushObjects);

        log.exiting(STR_METHOD_NAME);

    }

    protected String getUrlFromOrderType(OrderTypeEnum l_orderType)
    {
        final String STR_METHOD_NAME = "getUrlFromOrderType(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        String l_result = "";

        switch (l_orderType.intValue())
        {
        case OrderTypeEnum.IntValues.EQUITY_BUY:
        case OrderTypeEnum.IntValues.EQUITY_SELL:
            l_result = WEB3QtpRichPushUrlPatternDef.URL_EQUITY;
            break;
        case OrderTypeEnum.IntValues.MARGIN_LONG:
        case OrderTypeEnum.IntValues.MARGIN_SHORT:
        case OrderTypeEnum.IntValues.CLOSE_MARGIN_LONG:
        case OrderTypeEnum.IntValues.CLOSE_MARGIN_SHORT:
        case OrderTypeEnum.IntValues.SWAP_MARGIN_LONG:
        case OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT:
            l_result = WEB3QtpRichPushUrlPatternDef.URL_MARGIN;
            break;
        case OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_OPEN:
        case OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_OPEN:
        case OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_CLOSE:
        case OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_CLOSE:
            l_result = WEB3QtpRichPushUrlPatternDef.URL_FUTURES;
            break;
        case OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_OPEN:
        case OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_OPEN:
        case OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_CLOSE:
        case OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_CLOSE:
            l_result = WEB3QtpRichPushUrlPatternDef.URL_OPTIONS;
            break;
        }

        if (l_result.length() < 1)
        {
            log.warn("No Matched QTP url pattern for the order type." + l_orderType);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    protected String getOrderTypeNameFromOrderType(OrderTypeEnum l_orderType)
    {
        final String STR_METHOD_NAME = "getOrderTypeNameFromOrderType(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        String l_result = "";

        switch (l_orderType.intValue())
        {
        case OrderTypeEnum.IntValues.EQUITY_BUY:
            l_result = WEB3QtpRichPushOrderTypeNameDef.EQUITY_BUY;
            break;
        case OrderTypeEnum.IntValues.EQUITY_SELL:
            l_result = WEB3QtpRichPushOrderTypeNameDef.EQUITY_SELL;
            break;
        case OrderTypeEnum.IntValues.MARGIN_LONG:
            l_result = WEB3QtpRichPushOrderTypeNameDef.MARGIN_LONG;
            break;
        case OrderTypeEnum.IntValues.MARGIN_SHORT:
            l_result = WEB3QtpRichPushOrderTypeNameDef.MARGIN_SHORT;
            break;
        case OrderTypeEnum.IntValues.CLOSE_MARGIN_LONG:
            l_result = WEB3QtpRichPushOrderTypeNameDef.CLOSE_MARGIN_LONG;
            break;
        case OrderTypeEnum.IntValues.CLOSE_MARGIN_SHORT:
            l_result = WEB3QtpRichPushOrderTypeNameDef.CLOSE_MARGIN_SHORT;
            break;
        case OrderTypeEnum.IntValues.SWAP_MARGIN_LONG:
            l_result = WEB3QtpRichPushOrderTypeNameDef.SWAP_MARGIN_LONG;
            break;
        case OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT:
            l_result = WEB3QtpRichPushOrderTypeNameDef.SWAP_MARGIN_SHORT;
            break;
        case OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_OPEN:
            l_result = WEB3QtpRichPushOrderTypeNameDef.IDX_FUTURES_BUY_TO_OPEN;
            break;
        case OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_OPEN:
            l_result = WEB3QtpRichPushOrderTypeNameDef.IDX_FUTURES_SELL_TO_OPEN;
            break;
        case OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_CLOSE:
            l_result = WEB3QtpRichPushOrderTypeNameDef.IDX_FUTURES_BUY_TO_CLOSE;
            break;
        case OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_CLOSE:
            l_result = WEB3QtpRichPushOrderTypeNameDef.IDX_FUTURES_SELL_TO_CLOSE;
            break;
        case OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_OPEN:
            l_result = WEB3QtpRichPushOrderTypeNameDef.IDX_OPTIONS_BUY_TO_OPEN;
            break;
        case OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_OPEN:
            l_result = WEB3QtpRichPushOrderTypeNameDef.IDX_OPTIONS_SELL_TO_OPEN;
            break;
        case OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_CLOSE:
            l_result = WEB3QtpRichPushOrderTypeNameDef.IDX_OPTIONS_BUY_TO_CLOSE;
            break;
        case OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_CLOSE:
            l_result = WEB3QtpRichPushOrderTypeNameDef.IDX_OPTIONS_SELL_TO_CLOSE;
            break;
        }

        if (l_result.length() < 1)
        {
            log.warn("Can't get QTP order type name for the order type." + l_orderType);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    protected String getQtpMarketCodeFromMarket(String l_marketCode)
    {
        final String STR_METHOD_NAME = "getQtpMarketCodeFromMarket(String)";
        log.entering(STR_METHOD_NAME);
        String l_result = "";
        if (WEB3MarketCodeDef.TOKYO.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketCodeDef.TOKYO;
        }
        else if (WEB3MarketCodeDef.OSAKA.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketCodeDef.OSAKA;
        }
        else if (WEB3MarketCodeDef.JASDAQ.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketCodeDef.JASDAQ;
        }
        else if (WEB3MarketCodeDef.NAGOYA.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketCodeDef.NAGOYA;
        }
        else if (WEB3MarketCodeDef.NNM.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketCodeDef.NNM;
        }
        else if (WEB3MarketCodeDef.FUKUOKA.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketCodeDef.FUKUOKA;
        }
        else if (WEB3MarketCodeDef.SAPPORO.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketCodeDef.SAPPORO;
        }
        if (l_result.length() < 1)
        {
            log.warn("Can't get QTP Market Code from local market code." + l_marketCode);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    protected String getQtpMarketNameFromMarket(String l_marketCode)
    {
        final String STR_METHOD_NAME = "getQtpMarketNameFromMarket(String)";
        log.entering(STR_METHOD_NAME);
        String l_result = "";
        if (WEB3MarketCodeDef.TOKYO.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketNameDef.TOKYO;
        }
        else if (WEB3MarketCodeDef.OSAKA.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketNameDef.OSAKA;
        }
        else if (WEB3MarketCodeDef.JASDAQ.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketNameDef.JASDAQ;
        }
        else if (WEB3MarketCodeDef.NAGOYA.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketNameDef.NAGOYA;
        }
        else if (WEB3MarketCodeDef.NNM.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketNameDef.NNM;
        }
        else if (WEB3MarketCodeDef.FUKUOKA.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketNameDef.FUKUOKA;
        }
        else if (WEB3MarketCodeDef.SAPPORO.equals(l_marketCode))
        {
            l_result = WEB3QtpRichPushMarketNameDef.SAPPORO;
        }
        if (l_result.length() < 1)
        {
            log.warn("Can't get QTP Market Name from local market code." + l_marketCode);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * 整数からStringフォーマットに変換
     * @@param l_num
     * @@return
     */
    protected String formatNumber(int l_num)
    {
        DecimalFormat l_df = (DecimalFormat)decimalFormatTL.get();
        return l_df.format(l_num);
    }
    
    /**
     * doubleからStringフォーマットに変換
     * @@param l_num
     * @@return
     */
    protected String formatNumber(double l_num)
    {
        DecimalFormat l_df = (DecimalFormat)decimalFormatTL.get();
        return l_df.format(l_num);
    }

    /**
     * データ整合性チェック（共通）
     * @@param l_pushObj
     * @@return
     */
    protected boolean isCommonValid(WEB3QtpExcutionInformUnit l_pushObj)
    {
        final String STR_METHOD_NAME = "isCommonValid(WEB3QtpExcutionInformUnit l_pushObj)";
        log.entering(STR_METHOD_NAME);
        boolean l_isValid = false;
        if(null != l_pushObj)
        {
            if((null != l_pushObj.getSrlnum())
                    && (null != l_pushObj.getSid())
                    && (null != l_pushObj.getTm())
                    && (null != l_pushObj.getTlgNtcNmsgFtagFatt()))
            {
                l_isValid = true;
            }
        }
        if(!l_isValid)
        {
            if(null == l_pushObj)
            {
                log.warn("The push object is null.");
            }
            else
            {
                log.warn("Data is not valid:[" + l_pushObj.toString() + "]");
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_isValid;
    }

    /**
     * データ整合性チェック（全てフィールド）
     * @@param l_pushObj
     * @@return
     */
    protected boolean isAllValid(WEB3QtpExcutionInformUnit l_pushObj)
    {
        final String STR_METHOD_NAME = "isAllValid(WEB3QtpExcutionInformUnit l_pushObj)";
        log.entering(STR_METHOD_NAME);
        boolean l_isValid = isCommonValid(l_pushObj);
        if(l_isValid)
        {
            l_isValid = false;
            if((null != l_pushObj.getUrl())
                    && (null != l_pushObj.getTitle())
                    && (null != l_pushObj.getUrlParams())
                    && (null != l_pushObj.getQcodeParams())
                    && (null != l_pushObj.getQcodeName())
                    && (null != l_pushObj.getOrderTypeName())
                    && (null != l_pushObj.getPrc())
                    && (null != l_pushObj.getQuantityText())
                    && (null != l_pushObj.getVol())
                    && (null != l_pushObj.getLnkText()))
            {
                l_isValid = true;
            }
            else
            {
                if(null == l_pushObj)
                {
                    log.warn("The push object is null.");
                }
                else
                {
                    log.warn("Data is not valid:[" + l_pushObj.toString() + "]");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_isValid;
    }
}@
