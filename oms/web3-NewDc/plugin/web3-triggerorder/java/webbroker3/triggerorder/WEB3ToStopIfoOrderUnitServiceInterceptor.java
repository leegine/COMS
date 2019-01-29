head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopIfoOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文先物OP発注一件サービスインタセプタ(WEB3ToStopIfoOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/23 呉艶飛(中訊) 新規作成
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
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
 * (逆指値注文先物OP発注一件サービスインタセプタ)<BR>
 * 逆指値注文先物OP発注一件サービスインタセプタ<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToStopIfoOrderUnitServiceInterceptor implements Interceptor 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopIfoOrderUnitServiceInterceptor.class);
    
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
     * 　@　@コールする。 <BR>
     * 　@－株式注文単位オブジェクトより、<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =<BR>
     * 　@　@　@注文単位.部店IDに該当する 部店オブジェクト.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード =<BR>
     * 　@　@　@注文単位.部店IDに該当する 部店オブジェクト.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = "株価指数先物OP"<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = 注文単位.銘柄IDに該当する先物OP銘柄オブジェクト.原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = 注文単位.注文カテゴリより判定して編集(*1)<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = 注文単位.注文種別より判定して編集(*2)<BR>
     * <BR>
     * 　@(*1)注文受付商品 <BR>
     * 　@　@　@注文カテゴリ == （"先物新規建注文"or"先物返済注文"）の場合は、"先物"。 <BR>
     * 　@　@　@上記以外の場合は、"オプション"。 <BR>
     * <BR>
     * 　@(*2)注文受付トランザクション<BR>
     * 　@　@　@注文種別 == （"先物新規買建注文"or"OP新規買建注文"）の場合は、"買付（新規建買）"。<BR>
     * 　@　@　@注文種別 == （"先物新規売建注文"or"OP新規売建注文"）の場合は、"売付（新規建売）"。<BR>
     * 　@　@　@上記以外の場合は、"返済"。 <BR>
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
            //サービスの引数[0]を先物OP注文単位オブジェクトにキャストする。 
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_serviceParams[0];
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                
            String l_strInstitutionCode = null; //証券会社コード
            String l_strBranchCode  = null; //部店コード

            long l_lngBronchId = l_row.getBranchId(); 
            long l_lngProductId = l_row.getProductId();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //口座マネージャを取得する
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            //先物OP注文単位.口座IDに該当する、顧客オブジェクトを取得する。
            MainAccount l_mainAcc = l_accountMananger.getMainAccount(l_orderUnit.getAccountId());
            
            //口座をロックする。
            l_accountMananger.lockAccount(
                l_mainAcc.getInstitution().getInstitutionCode(),
                l_mainAcc.getBranch().getBranchCode(),
                l_mainAcc.getAccountCode());
            
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_lngBronchId);            
            
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
                (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getProductManager();
            
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoProductManagerImpl.getProduct(l_lngProductId);
            l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            l_strBranchCode = l_branch.getBranchCode();
                        
            //取引カレンダコンテキスト.証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
                
            //取引カレンダコンテキスト.部店コード 
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);        
            
            // 取引カレンダコンテキスト.受付時間区分 = "株価指数先物OP"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            
            //取引カレンダコンテキスト.銘柄コード = 注文単位.銘柄IDに該当する先物OP銘柄オブジェクト.原資産銘柄コード
            l_context.setProductCode(l_ifoProduct.getUnderlyingProductCode());
            
            //取引カレンダコンテキスト.注文受付商品 = 注文単位.注文カテゴリより判定して編集(*1)
            //(*1)注文受付商品 
            //注文カテゴリ == （"先物新規建注文"or"先物返済注文"）の場合は、"先物"。
            //上記以外の場合は、"オプション"。
            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())
                || OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
            }
            else
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            }
            
            //取引カレンダコンテキスト.注文受付トランザクション = 
            //注文単位.注文カテゴリ、注文種別より<判定して編集(*2)
            //(*2)注文受付トランザクション
            //注文種別 == （"先物新規買建注文"or"OP新規買建注文"）の場合は、"買付（新規建買）"。
            //注文種別 == （"先物新規売建注文"or"OP新規売建注文"）の場合は、"売付（新規建売）"。
            //上記以外の場合は、"返済"。
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderUnit.getOrderType())
                || OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderUnit.getOrderType())
                || OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            }
            else
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
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
