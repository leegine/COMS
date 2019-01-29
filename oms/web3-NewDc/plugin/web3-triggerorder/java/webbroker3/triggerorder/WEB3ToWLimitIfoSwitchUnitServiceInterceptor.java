head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitIfoSwitchUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文先物OP切替一件サービスインタセプタ(WEB3ToWLimitIfoSwitchUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/24 唐性峰(中訊) 新規作成
Revesion History : 2007/1/31 崔遠鵬(中訊) モデルNo.217 對應
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.util.WEB3LogUtility;


/**
 * (W指値注文先物OP切替一件サービスインタセプタ)<BR>
 * W指値注文先物OP切替一件サービスインタセプタ<BR>
 * 
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUnitServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitIfoSwitchUnitServiceInterceptor.class);
    
    /**
     * @@roseuid 44E90CEB0138
     */
    public WEB3ToWLimitIfoSwitchUnitServiceInterceptor() 
    {
             
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]を先物OP注文単位オブジェクトにキャストする。 <BR>
     * 　@－先物OP注文単位.口座IDに該当する、顧客オブジェクトを取得する。<BR>
     * 　@－口座をロックする。 <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(顧客.証券会社コード, 顧客.部店コード, 顧客.口座コード)を<BR>
     * 　@　@  コールする。 <BR>
     * 　@－先物OP注文単位オブジェクトより、<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =<BR>
     * 　@　@　@注文単位.部店IDに該当する 部店オブジェクト.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード =<BR>
     * 　@　@　@注文単位.部店IDに該当する 部店オブジェクト.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "株価指数先物OP"<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = <BR>
     * 　@　@　@注文単位.銘柄IDに該当する先物OP銘柄オブジェクト.原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = <BR>
     * 　@　@　@注文単位.注文カテゴリより判定して編集(*1)<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "訂正"<BR>
     * <BR>
     * 　@(*1)注文受付商品 <BR>
     * 　@　@　@注文カテゴリ == （"先物新規建注文"or"先物返済注文"）の場合は、"先物"。 <BR>
     * 　@　@　@上記以外の場合は、"オプション"。 <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 　@　@設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@スキップvalidate特殊執行条件取扱停止をセットする。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.setAttribute()にて<BR>
     * 　@スキップvalidate特殊執行条件取扱停止に"TRUE"をセットする。<BR>
     * 　@　@-ThreadLocalSystemAttributesRegistry.setAttribute(<BR>
     * 　@　@　@　@WEB3LocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,<BR>
     * 　@　@　@　@BooleanEnum.TRUE)<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 44924515035A
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.error("パラメータ値不正。");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }
        
        if (! (l_serviceParam[0] instanceof IfoOrderUnit))
        {
            log.error("パラメータの型不正。");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        
        try
        {
            //１）　@取引カレンダコンテキストに内容をセットする。  
            //  －サービスの引数[0]を先物OP注文単位オブジェクトにキャストする。
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_serviceParam[0];
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();       

            long l_lngBranchId = l_row.getBranchId(); 
            long l_lngProductId = l_row.getProductId();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //口座マネージャを取得する
            WEB3GentradeAccountManager l_accountMananger = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //  －先物OP注文単位.口座IDに該当する、顧客オブジェクトを取得する。
            MainAccount l_mainAcc = l_accountMananger.getMainAccount(l_orderUnit.getAccountId());
            
            //  －口座をロックする。  
            //    拡張アカウントマネージャ.lock口座(顧客.証券会社コード, 顧客.部店コード, 顧客.口座コード)を 
            //    コールする。  
            l_accountMananger.lockAccount(
                l_mainAcc.getInstitution().getInstitutionCode(),
                l_mainAcc.getBranch().getBranchCode(),
                l_mainAcc.getAccountCode());
            
            //  －先物OP注文単位オブジェクトより、
            //       取引時間コンテキストのプロパティをセットする。
            WEB3GentradeBranch l_branch = 
                (WEB3GentradeBranch)l_accountMananger.getBranch(l_lngBranchId);
            
            WEB3IfoProductManagerImpl l_ifoProductManagetImpl = 
                (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getProductManager();
            
            WEB3IfoProductImpl l_product = 
                (WEB3IfoProductImpl)l_ifoProductManagetImpl.getProduct(l_lngProductId);
            //証券会社コード
            String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            //部店コード
            String l_strBranchCode = l_branch.getBranchCode();

            //  取引カレンダコンテキスト.証券会社コード = 
            //      注文単位.部店IDに該当する 部店オブジェクト.証券会社コード 
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //  取引カレンダコンテキスト.部店コード = 
            //      注文単位.部店IDに該当する 部店オブジェクト.部店コード 
            l_context.setBranchCode(l_strBranchCode);
            
            //  取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //  取引カレンダコンテキスト.受付時間区分 = "株価指数先物OP"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            
            //  取引カレンダコンテキスト.銘柄コード = 注文単位.銘柄IDに該当する先物OP銘柄オブジェクト.原資産銘柄コード 
            l_context.setProductCode(l_product.getUnderlyingProductCode());
            
            //  取引カレンダコンテキスト.注文受付商品 = 注文単位.注文カテゴリより判定して編集(*1) 
            //  (*1)注文受付商品  
            //      注文カテゴリ == （"先物新規建注文"or"先物返済注文"）の場合は、"先物"。  
            //      上記以外の場合は、"オプション"。 
            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())
               || OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
            }
            else
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            }
            
            //  取引カレンダコンテキスト.注文受付トランザクション = "訂正" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            
            //
            //  －ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 
            //    設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            //２）　@スキップvalidate特殊執行条件取扱停止をセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
                BooleanEnum.TRUE);
            
            //３）　@受付日時、日付ロールをセットする。  
            //  －取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." +STR_METHOD_NAME,
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
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 449245150379
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NANE = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NANE);
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NANE);              
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
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 449245150399
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
        
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
