head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.28.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeCompleteUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替完了UnitServiceインタセプタ(WEB3AccTransChangeCompleteUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/22 王蘭芬(中訊) レビュー     
                   2004/12/09 周勇 (中訊) 残対応                                  
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替完了UnitServiceインタセプタ)<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeCompleteUnitServiceInterceptor
    implements Interceptor
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeCompleteUnitServiceInterceptor.class);

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－引数.サービスの引数[0]を注文単位オブジェクトにキャストする。 <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * 注文単位.証券会社部店ＩＤに該当する部店の証券会社コード <BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * 注文単位.証券会社部店ＩＤに該当する部店の部店コード <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”13：証拠金振替” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = (*1)注文受付商品<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”<BR>
     * <BR>
     *   (*1)注文受付商品の取得方法@<BR>
     *     注文単位.注文種別 = 1005：振替注文（預り金から信用保証金） <BR>
     * の場合、”09：信用保証金への振替”<BR>
     *     注文単位.注文種別 = 1006：振替注文（信用保証金から預り金） <BR>
     * の場合、”10：信用保証金からの振替”<BR>
     *     注文単位.注文種別 = 1007：振替注文（預り金から株先証拠金） <BR>
     * の場合、”12：先OP証拠金への振替”<BR>
     *     注文単位.注文種別 = 1008：振替注文（株先証拠金から預り金） <BR>
     * の場合、”13：先OP証拠金からの振替”<BR>
     * 
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@口座をロックする。<BR> 
　@   * －拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)<BR>
     * をコールする。<BR> 
     * ※引数は注文単位より編集。<BR>
     * <BR>
     * @@param l_method - サービスメソッド
     * @@param l_serviceParams - サービスメソッド引数
     * @@return Object
     * @@roseuid 414166F80050
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        if (l_serviceParams.length == 0)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
               
        // １）　@取引カレンダコンテキストに内容をセットする。 
        // 　@－引数.サービスの引数[0]を注文単位オブジェクトにキャストする。 
        OrderUnit l_orderUnit = (OrderUnit) l_serviceParams[0];
        
        // リクエストデータの内容と、OpLoginSecurityServiceの内容より
        // 取引時間コンテキストのプロパティをセットする        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();  

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                
        long l_lngAccountId = l_orderUnit.getAccountId();
        AccountManager l_accMgr = l_finApp.getAccountManager();
        MainAccount l_acc = null;
        
        try
        {
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            String l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_acc.getBranch().getBranchCode(); 
            
            //取引カレンダコンテキスト.証券会社コード = 
            //  注文単位.証券会社部店ＩＤに該当する部店の証券会社コード 
            l_context.setInstitutionCode(l_strInstitutionCode); 
            
            //取引カレンダコンテキスト.部店コード = 
            //  注文単位.証券会社部店ＩＤに該当する部店の部店コード  
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //=========== remain wei-nianqiong No.203 start ================
            
            //取引カレンダコンテキスト.受付時間区分 =  (*1)受付時間区分
            //(*1)受付時間区分の取得方法@ 
            //注文種別が以下のいずれかの場合、”23：為替保証金” 
            //1011(為替保証金振替注文(預かり金から為替保証金)) 
            //1012(為替保証金振替注文(為替保証金から預かり金)) 
            //上記以外の場合、 ”13：証拠金振替” 
            String l_strTradingTimeType = null;
            
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
            log.debug("注文種別 = " + l_orderType);
            
            if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) || 
                OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EXCHANGE_GUARANTEE;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.MARGIN_TRANSFER;
            }
            
            l_context.setTradingTimeType(l_strTradingTimeType);
            
            //(*2)注文受付商品の取得方法@ 
            //注文種別 = 1005：振替注文（預り金から信用保証金） の場合、”09：信用保証金への振替” 
            //注文種別 = 1006：振替注文（信用保証金から預り金） の場合、”10：信用保証金からの振替” 
            //注文種別 = 1007：振替注文（預り金から株先証拠金） の場合、”12：先OP証拠金への振替” 
            //注文種別 = 1008：振替注文（株先証拠金から預り金） の場合、”13：先OP証拠金からの振替” 
            //　@注文種別が以下のいずれかの場合、”23：為替保証金” 
            //　@　@1011(為替保証金振替注文(預かり金から為替保証金)) 
            //　@　@1012(為替保証金振替注文(為替保証金から預かり金)) 
             String l_strOrderAcceptProduct = null;
            
            // 注文単位.注文種別 = 1005：振替注文（預り金から信用保証金） の場合、”
            //      09：信用保証金への振替” 
            if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderType))
            {
               l_strOrderAcceptProduct = 
                  WEB3OrderAccProductDef.MARGIN_GUARANTEE_MONEY_TRANSFER;
            }
            // 注文単位.注文種別 = 1006：振替注文（信用保証金から預り金） の場合、”
            //      10：信用保証金からの振替” 
            else if(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.MARGIN_GUARANTEE_MONEY_TRANSFER_FROM;
            }
            // 注文単位.注文種別 = 1007：振替注文（預り金から株先証拠金） の場合、”
            //      12：先OP証拠金への振替”
            else if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderType))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.FUTURE_OP_GUARANTEE_MONEY_CHANGE;
            }
            // 注文単位.注文種別 = 1008：振替注文（株先証拠金から預り金） の場合、”
            //      13：先OP証拠金からの振替”
            else if(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderType))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.FUTURE_OP_GUARANTEE_MONEY_CHANGE_FROM;
            }

            //　@注文種別が以下のいずれかの場合、”23：為替保証金” 
            //　@　@1011(為替保証金振替注文(預かり金から為替保証金)) 
            //　@　@1012(為替保証金振替注文(為替保証金から預かり金)) 
            else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) || 
                OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType))
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.EXCHANGE_GUARANTEE;
            }
            
            //取引カレンダコンテキスト.注文受付商品 = (*1)注文受付商品 
            l_context.setOrderAcceptProduct(l_strOrderAcceptProduct);
            
            //=========== remain wei-nianqiong No.203 end ================
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”09：振替” 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);
            
            //-ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセット
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                  l_context);
            
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //=========remain zhou-yong NO.1 begin ===========
            //３）　@口座をロックする。 
            //　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //※引数は注文単位より編集。
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_accMgr;
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            l_accountManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_mainAccount.getAccountCode());
            
            //=========remain zhou-yong NO.1 end ===========            
            
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        } 
        log.exiting(STR_METHOD_NAME);   
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 414166F8006F
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
            
        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 414166F8008F
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        log.exiting(STR_METHOD_NAME); 
    }
}
@
