head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文約定照会サービスインタセプタ(WEB3FeqExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成 
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.message.WEB3FeqExecuteDetailsRequest;
import webbroker3.feq.message.WEB3FeqExecuteReferenceRequest;
import webbroker3.feq.message.WEB3FeqOrderHistoryRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式注文約定照会サービスインタセプタ)<BR>
 * 外国株式注文約定照会サービスインタセプタ
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqExecuteReferenceServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceServiceInterceptor.class);
    
    /**
     * @@roseuid 42D0D2D402FD
     */
    public WEB3FeqExecuteReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）取引カレンダコンテキストに内容をセットする。<BR>
     *   －OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * 　@　@プロパティをセットする。<BR>
     * <BR>
     *   取引カレンダコンテキスト.証券会社コード = (*1)<BR>
     *   取引カレンダコンテキスト.部店コード = (*1)<BR>
     *   取引カレンダコンテキスト.市場コード = (*2)<BR>
     *   取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     *   取引カレンダコンテキスト.受付時間区分 = ”外国株式”<BR>
     *   取引カレンダコンテキスト.注文受付商品 = ”外国株”<BR>
     *   取引カレンダコンテキスト.注文受付トランザクション = ”照会”<BR>
     * <BR>
     *   －ThreadLocalSystemAttributesRegistry.setAttribute( )に<BR>
     * て取引時間コンテキストをセットする。<BR>
     *      設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）受付日時、日付ロールをセットする。<BR>
     *   －取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * (*1)①@OpLoginSecurityServiceより口座IDを取得する。<BR>
     *     ②管理者セッション(OpLoginSecurityServiceより取得した口座ID == <BR>
     * 0)の場合、<BR>
     *       instanceofにてリクエストデータの型を判別し、以下の型にキャストする。<BR>
     * 　@　@　@　@・外国株式注文約定詳細リクエスト<BR>
     * 　@　@　@　@・外国株式注文約定履歴リクエスト<BR>
     * <BR>
     *       キャストしたリクエスト.注文IDに該当する注文.口座IDを<BR>
     * 取引時間コンテキストにセットする。 <BR>
     *         設定キー：　@ACCOUNT_ID <BR>
     *         ※設定キー：ACCOUNT_IDは必ず定数クラスを作成し、<BR>
     * その定数を参照すること。<BR>
     * <BR>
     *     ③取得した口座ID(②の場合は、<BR>
     * 注文.口座ID)に該当する顧客オブジェクトより<BR>
     *       証券会社コード、部店コードをセットする。<BR>
     * <BR>
     * (*2)[パラメータ.サービスメソッド＝"get注文約定照会()"の場合]<BR>
     *       ①@ パラメータ.サービスメソッド引数[0]を<BR>
     * 外国株式注文約定照会リクエスト型にキャストする。<BR>
     *       ② キャストしたリクエスト.市場コードをセットする。<BR>
     *          ※キャストしたリクエスト.市場コード == nullの場合、<BR>
     * ”0：DEFAULT”をセットする。<BR>
     *     [上記以外]<BR>
     *       ”0：DEFAULT”をセットする。<BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 429EB2EC0115
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("該当パラメータにNull値は設定できません。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);         
        }
        
        //１）取引カレンダコンテキストに内容をセットする。 
        //－OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();
        
        try
        {
            //(*1)(1)OpLoginSecurityServiceより口座IDを取得する。 
            //(2)管理者セッション(OpLoginSecurityServiceより取得した口座ID == 0)の場合、 
            
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
            long l_lngOrderId = 0L;
            
            //(2)管理者セッション(OpLoginSecurityServiceより取得した口座ID == 0)の場合、 
            if (l_lngAccountId == 0L)
            {
                //instanceofにてリクエストデータの型を判別し、以下の型にキャストする。 
                //・外国株式注文約定詳細リクエスト 
                //・外国株式注文約定履歴リクエスト 
                if (l_serviceParams[0] instanceof WEB3FeqExecuteDetailsRequest)
                {
                    WEB3FeqExecuteDetailsRequest l_detailsRequest = 
                        (WEB3FeqExecuteDetailsRequest)l_serviceParams[0];
                    
                    l_lngOrderId = Long.parseLong(l_detailsRequest.orderId);
                }        
                else if (l_serviceParams[0] instanceof WEB3FeqOrderHistoryRequest)
                {
                    
                    WEB3FeqOrderHistoryRequest l_historyRequest = 
                        (WEB3FeqOrderHistoryRequest)l_serviceParams[0];
                    
                    l_lngOrderId = Long.parseLong(l_historyRequest.orderId);                   
                }
                
                //キャストしたリクエスト.注文IDに該当する注文.口座IDを取引時間コンテキストにセットする。  
                //  設定キー：　@ACCOUNT_ID  
                //  ※設定キー：ACCOUNT_IDは必ず定数クラスを作成し、その定数を参照すること。
                FeqOrderUnit l_feqOrderUnit = 
                    l_feqOrderManager.getOrderUnitByOrderId(l_lngOrderId);

                FeqOrderUnitParams l_feqOrderUnitParams = 
                    new FeqOrderUnitParams(
                        (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
                
                long l_lngOrderAccountId = l_feqOrderUnitParams.getAccountId();
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID, 
                    new Long(l_lngOrderAccountId));
                
                l_lngAccountId = l_lngOrderAccountId;
            }            

            // (3)取得した口座ID((2)の場合は、注文.口座ID)に該当する顧客オブジェクトより 
            //証券会社コード、部店コードをセットする。 
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(l_lngAccountId);            
            
            //(*2)[パラメータ.サービスメソッド＝"get注文約定照会()"の場合] 
            String l_strMarketCode = null;
            final String METHOD_GETORDEREXCUTEREFERENCE = "getOrderExecuteReference";
            
            if (METHOD_GETORDEREXCUTEREFERENCE.equals(l_method.getName()))
            {
                //(1) パラメータ.サービスメソッド引数[0]を外国株式注文約定照会リクエスト型にキャストする。 
                if (l_serviceParams[0] instanceof WEB3FeqExecuteReferenceRequest)
                {
                    WEB3FeqExecuteReferenceRequest l_detailsRequest = 
                        (WEB3FeqExecuteReferenceRequest)l_serviceParams[0];
                    
                    //(2) キャストしたリクエスト.市場コードをセットする。 

                    if (l_detailsRequest.marketCode != null)
                    {
                        l_strMarketCode = l_detailsRequest.marketCode;
                    }
                    //   ※キャストしたリクエスト.市場コード == nullの場合、
                    //    ”0：DEFAULT”をセットする。 
                    else
                    {
                        l_strMarketCode = WEB3MarketCodeDef.DEFAULT;
                    }
                }               
            }
            //[上記以外] 
            //”0：DEFAULT”をセットする。 
            else
            {
                l_strMarketCode = WEB3MarketCodeDef.DEFAULT;
            }
            
            //証券会社コードを取得する
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //部店コードを取得する
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            //取引カレンダコンテキスト.証券会社コード = (*1) 
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //取引カレンダコンテキスト.部店コード = (*1)
            l_context.setBranchCode(l_strBranchCode);
            
            //取引カレンダコンテキスト.市場コード = (*2) 
            l_context.setMarketCode(l_strMarketCode);
            
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = ”外国株式” 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            //取引カレンダコンテキスト.注文受付商品 = ”外国株” 
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.FOREIGN_STOCK);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”照会”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);
    
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
            log.error("error in l_accountManager.getMainAccount", l_ex);
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
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return null;
    }
    
    /**
     * 取引カレンダコンテキストクリア処理。<BR>
     * サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * ACCOUNT_ID<BR>
     * <BR>
     * ※設定キー：ACCOUNT_IDは必ず定数クラスを作成し、<BR>
     * その定数を参照すること。<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 429EB2EC0134
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

        //ACCOUNT_ID
        //※設定キー：ACCOUNT_IDは必ず定数クラスを作成し、その定数を参照すること。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID, null);
        
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * 取引カレンダコンテキストクリア処理。<BR>
     * 例外発生時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * ACCOUNT_ID<BR>
     * <BR>
     * ※設定キー：ACCOUNT_IDは必ず定数クラスを作成し、<BR>
     * その定数を参照すること。<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 429EB2EC0144
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
        
        //ACCOUNT_ID
        //※設定キー：ACCOUNT_IDは必ず定数クラスを作成し、その定数を参照すること。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID, null);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
