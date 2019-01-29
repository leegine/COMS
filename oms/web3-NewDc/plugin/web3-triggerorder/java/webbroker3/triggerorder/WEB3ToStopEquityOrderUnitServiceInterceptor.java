head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文株式発注一件サービスインタセプタ(WEB3ToStopEquityOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (逆指値注文株式発注一件サービスインタセプタ)<BR>
 * 逆指値注文株式発注一件サービスインタセプタ<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToStopEquityOrderUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityOrderUnitServiceInterceptor.class);
    
    /**
     * @@roseuid 436ACB8D000F
     */
    public WEB3ToStopEquityOrderUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]を株式注文単位オブジェクトにキャストする。 <BR>
     *   －株式注文単位.口座IDに該当する、顧客オブジェクトを取得する。<BR>
     *   －口座をロックする。<BR>
     *      拡張アカウントマネージャ.lock口座(顧客.証券会社コード, <BR>
     *      顧客.部店コード, 顧客.口座コード)をコールする。<BR>
     * 　@－株式注文単位オブジェクトより<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =<BR>
     * 　@　@　@注文単位.部店IDに該当する 部店オブジェクト.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード =<BR>
     * 　@　@　@注文単位.部店IDに該当する 部店オブジェクト.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード =<BR>
     * 　@　@　@注文単位.市場IDに該当する市場オブジェクト.市場コード<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "株式・信用"<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = "DEFAULT"<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = 注文単位.注文カテゴリより判定して編集(*1)<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = 注文単位.注文カテゴリ、注文種別より<BR>
     *   判定して編集(*2)<BR>
     * <BR>
     * 　@(*1)注文受付商品 <BR>
     * 　@　@　@注文カテゴリ == "現物注文"の場合は、"株式"。 <BR>
     * 　@　@　@上記以外の場合は、"信用取引"。 <BR>
     * <BR>
     * 　@(*2)注文受付トランザクション<BR>
     * 　@　@　@注文カテゴリ == "返済"の場合は、"返済"。<BR>
     * 　@　@　@注文種別 == （"現物買注文"or"新規買建注文"）の場合は、"買付（新規建買）"。<BR>
     * 　@　@　@注文種別 == （"現物売注文"or"新規売建注文"）の場合は、"売付（新規建売）"。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 　@　@設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 434C7BF602F4
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam) ";
        log.entering(STR_METHOD_NAME);
        if(l_serviceParams[0] == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        try
        {
            //１）　@取引カレンダコンテキストに内容をセットする。 <BR>
            //－サービスの引数[0]を株式注文単位オブジェクトにキャストする。 
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_serviceParams[0];
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                
            String l_strInstitutionCode = null; //証券会社コード
            String l_strBranchCode  = null; //部店コード

            long l_lngBronchId = l_row.getBranchId(); 
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //口座マネージャを取得する
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            //株式注文単位.口座IDに該当する、顧客オブジェクトを取得する。
            MainAccount l_mainAcc = l_accountMananger.getMainAccount(l_orderUnit.getAccountId());
            
            //口座をロックする。
            l_accountMananger.lockAccount(
                l_mainAcc.getInstitution().getInstitutionCode(),
                l_mainAcc.getBranch().getBranchCode(),
                l_mainAcc.getAccountCode());
            
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_lngBronchId);
            
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());
            
            l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            l_strBranchCode = l_branch.getBranchCode();
                        
            //取引カレンダコンテキスト.証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
                
            //取引カレンダコンテキスト.部店コード 
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード 
            l_context.setMarketCode(l_market.getMarketCode());        
            
            // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = 注文単位.注文カテゴリより判定して編集(*1)
            //(*1)注文受付商品 
            //注文カテゴリ == "現物注文"の場合は、"株式"。 
            //上記以外の場合は、"信用取引"。 
            if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            }
            else
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            }
            
            //取引カレンダコンテキスト.注文受付トランザクション = 
            //注文単位.注文カテゴリ、注文種別より<判定して編集(*2)
            //(*2)注文受付トランザクション
            //注文カテゴリ == "返済"の場合は、"返済"。
            //注文種別 == （"現物買注文"or"新規買建注文"）の場合は、"買付（新規建買）"。
            //注文種別 == （"現物売注文"or"新規売建注文"）の場合は、"売付（新規建売）"。
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            }
            else if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType())
                || OrderTypeEnum.MARGIN_LONG.equals(l_orderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            else if(OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType())
                || OrderTypeEnum.MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            }
                
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (WEB3BaseException l_ex)
        {                    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        //--------------------
        //スキップvalidate特殊失効条件取扱停止をセットする。
        //--------------------
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            BooleanEnum.TRUE);

        //--------------------
        //受付日時をセットする。
        //--------------------
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_wse)
        {
            throw new WEB3BaseRuntimeException(
                l_wse.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wse.getMessage(), l_wse);                  
        }
        log.exiting(STR_METHOD_NAME);
        return l_context;
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
     * @@roseuid 434C7BF60362
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            null);

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
     * @@roseuid 434C7BF60391
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            null);

    }
}
@
