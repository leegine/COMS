head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文株式発注一件サービスインタセプタ(WEB3ToSuccEquityOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 凌建平(中訊) 新規作成
Revesion History : 2008/05/05 安陽(中訊) 仕様変更モデルNo.314
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;


/**
 * (連続注文株式発注一件サービスインタセプタ)<BR>
 * 連続注文株式発注一件サービスインタセプタ。
 * <BR>
 * @@author 凌建平 <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccEquityOrderUnitServiceInterceptor implements Interceptor 
{
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccEquityOrderUnitServiceInterceptor.class);

    /**
     * @@roseuid 4348DACE037A
     */
    public WEB3ToSuccEquityOrderUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]を株式予約注文単位オブジェクトにキャストする。 <BR>
     * 　@－株式予約注文単位オブジェクトより、<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =<BR>
     * 　@　@　@株式予約注文単位.部店IDに該当する 部店オブジェクト.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード =<BR>
     * 　@　@　@株式予約注文単位.部店IDに該当する 部店オブジェクト.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード =<BR>
     * 　@　@　@株式予約注文単位.市場IDに該当する市場オブジェクト.市場コード<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = <BR>
     *   注文単位.注文カテゴリより判定して編集(*1)<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = "DEFAULT"<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = <BR>
     *   注文単位.注文カテゴリより判定して編集(*2)<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *   注文単位.注文カテゴリ、注文種別より判定して編集(*3)<BR>
     * <BR>
     * 　@(*1)受付時間区分<BR>
     * 　@　@　@注文カテゴリ == "現引現渡"の場合は、"現引現渡"。<BR>
     * 　@　@　@上記以外の場合は、"株式・信用"<BR>
     * <BR>
     * 　@(*2)注文受付商品 <BR>
     * 　@　@　@注文カテゴリ == "現物注文"の場合は、"株式"。 <BR>
     * 　@　@　@上記以外の場合は、"信用取引"。 <BR>
     * <BR>
     * 　@(*3)注文受付トランザクション<BR>
     * 　@　@　@注文カテゴリ == "返済"の場合は、"返済"。<BR>
     * 　@　@　@注文カテゴリ == "現引現渡"の場合は、"現引現渡"。<BR>
     * 　@　@　@注文種別 == 
     * （"現物買注文"or"新規買建注文"）の場合は、"買付（新規建買）"。<BR>
     * 　@　@　@注文種別 == 
     * （"現物売注文"or"新規売建注文"）の場合は、"売付（新規建売）"。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *       にて取引時間コンテキストをセットする。<BR>
     * 　@　@設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 432183C903C7
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);  
        
        if (l_serviceParams[0] == null)
        {   
            log.error("パラメータ値不正。");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }

        //１）取引カレンダコンテキストに内容をセットする。 <BR>
        //－サービスの引数[0]を株式予約注文単位オブジェクトにキャストする。 <BR>
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        if (l_serviceParams[0] instanceof WEB3ToSuccEqTypeOrderUnitImpl)
        {
            l_orderUnit =
                (WEB3ToSuccEqTypeOrderUnitImpl) l_serviceParams[0];
            l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
        }
        else
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                STR_METHOD_NAME);
        }

        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //部店を取得
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_rsvEqOrderUnitRow.getBranchId());

            //市場を取得
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_rsvEqOrderUnitRow.getMarketId());
    
            //取引カレンダコンテキスト.証券会社コード =<BR>
            //  株式予約注文単位.部店IDに該当する 部店オブジェクト.証券会社コード<BR>
            l_context.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());

            //取引カレンダコンテキスト.部店コード =<BR>
            //  株式予約注文単位.部店IDに該当する 部店オブジェクト.部店コード<BR>
            l_context.setBranchCode(l_branch.getBranchCode());

            //取引カレンダコンテキスト.市場コード =<BR>
            //  株式予約注文単位.市場IDに該当する市場オブジェクト.市場コード<BR>
            l_context.setMarketCode(l_market.getMarketCode());
            
            //取引カレンダコンテキスト.受付時間区分 = <BR>
            //注文単位.注文カテゴリより判定して編集(*1)<BR>
            // 　@(*1)受付時間区分<BR>
            // 　@　@　@注文カテゴリ == "現引現渡"の場合は、"現引現渡"。<BR>
            // 　@　@　@上記以外の場合は、"株式・信用"<BR>
            String l_strTradingTimeType = null;
            if (OrderCategEnum.SWAP_MARGIN.equals(l_rsvEqOrderUnitRow.getOrderCateg()))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SWAP;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
            }

            l_context.setTradingTimeType(l_strTradingTimeType);
            
            //取引カレンダコンテキスト.銘柄コード = "DEFAULT"<BR>
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = <BR>
            //注文単位.注文カテゴリより判定して編集(*2)<BR>
            // 　@(*2)注文受付商品 <BR>
            // 　@　@　@注文カテゴリ == "現物注文"の場合は、"株式"。 <BR>
            // 　@　@　@上記以外の場合は、"信用取引"。 <BR>
            String l_strOrderAcceptProduct = null;
            if (OrderCategEnum.ASSET.equals(l_rsvEqOrderUnitRow.getOrderCateg()))
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.MARGIN;
            }

            l_context.setOrderAcceptProduct(l_strOrderAcceptProduct);
            
            //取引カレンダコンテキスト.注文受付トランザクション = <BR>
            //注文単位.注文カテゴリ、注文種別より判定して編集(*3)<BR>
            // 　@(*3)注文受付トランザクション<BR>
            // 　@　@　@注文カテゴリ == "返済"の場合は、"返済"。<BR>
            // 　@　@　@注文カテゴリ == "現引現渡"の場合は、"現引現渡"。<BR>
            // 　@　@　@注文種別 == 
            // （"現物買注文"or"新規買建注文"）の場合は、"買付（新規建買）"。<BR>
            // 　@　@　@注文種別 == 
            // （"現物売注文"or"新規売建注文"）の場合は、"売付（新規建売）"。<BR>
            String l_strOrderAcceptTransaction = null;
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_rsvEqOrderUnitRow.getOrderCateg()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.CLOSE_MARGIN;
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_rsvEqOrderUnitRow.getOrderCateg()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.SWAP_MARGIN;
            }
            else if (OrderTypeEnum.EQUITY_BUY.equals(l_rsvEqOrderUnitRow.getOrderType())
                || OrderTypeEnum.MARGIN_LONG.equals(l_rsvEqOrderUnitRow.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_rsvEqOrderUnitRow.getOrderType())
                || OrderTypeEnum.MARGIN_SHORT.equals(l_rsvEqOrderUnitRow.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }

            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);

            //ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //取引時間コンテキストをセットする 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //２）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする  
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in getAccountManager", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);    
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 432183C903CA
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);        

        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 432183C903D6
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);  

        log.exiting(STR_METHOD_NAME);    
    }
}
@
