head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物取消注文サービスインタセプタ(WEB3FuturesCancelOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 盧法@旭 (中訊) 新規作成
Revesion History : 2007/01/29 何文敏 (中訊) 仕様変更　@モデルNO.625
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3FuturesCancelCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;


/**
 * (先物取消注文サービスインタセプタ)<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesCancelOrderServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCancelOrderServiceInterceptor.class);    
    /**
     * @@roseuid 40F7B0E00280
     */
    public WEB3FuturesCancelOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「株価指数オプション取消完了リクエスト」の場合のみ、口座をロックする。<BR> 
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
     * ※引数はOpLoginSecurityServiceより編集。<BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容<BR>より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”12：株価指数先物OP（取消訂正）”<BR>
     * 　@取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”05：先物”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”06：取消”<BR>
     * <BR>
     *  (*1) 原資産銘柄コードの取得方法@<BR>
     * 　@リクエストデータ.ＩＤに該当する注文オブジェクトを生成する。<BR>
     * 　@注文.getOrderUnits()[0].getProductId()にて銘柄ＩＤを取得する。<BR>
     * 　@銘柄ＩＤに該当する先物OP銘柄オブジェクトを生成する。<BR>
     * 　@生成した先物OP銘柄.get原資産銘柄コード()。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ４）　@取引所が休憩時間帯の場合(*2)のみ、キューテーブルの共有ロックを行う。<BR>
     * <BR>
     * 　@　@先物OP発注サービス.lock先物OP注文取引キュー(取消対象注文単位(*3))をコールする。<BR>
     * <BR>
     * 　@　@(*2)取引所が休憩時間帯の場合<BR>
     * 　@　@　@　@取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。<BR>
     * <BR>
     * 　@　@(*3)取消対象注文単位<BR>
     * 　@　@　@　@　@リクエスト.IDに該当する注文IDを持つ注文単位オブジェクト<BR>
     * 　@　@　@　@　@※複数存在する場合は、getOrderUnits()の最初の要素を使用する。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 40A81C72033E
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String METHOD_NAME = "onCall(Method,Object[])";
        log.entering(METHOD_NAME);

        try
        {    
            long l_lngOrderId   = 0;
            long l_lngProductId = 0;
            long l_lngAccountId = 0;
            
            //リクエストデータオブジェクトにキャストするを取得する
            if(l_serviceParam == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);                           
            }

            if(l_serviceParam.length ==0)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);                
            }

            Object l_request = l_serviceParam[0];
            if (l_request instanceof WEB3FuturesCancelConfirmRequest)
            {
                WEB3FuturesCancelConfirmRequest  l_cancelRequest =
                    (WEB3FuturesCancelConfirmRequest) l_request;
                l_lngOrderId = Long.parseLong(l_cancelRequest.id);
            }
            else if (l_request instanceof WEB3FuturesCancelCompleteRequest)
            {
                WEB3FuturesCancelCompleteRequest  l_cancelRequest =
                    (WEB3FuturesCancelCompleteRequest) l_request;
                l_lngOrderId = Long.parseLong(l_cancelRequest.id);
            }        
            else
            {
                log.error("パラメータタイプ不正。");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + METHOD_NAME);                       
            }
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                    OpLoginSecurityService.class);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            //口座マネージャを取得する
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();   
            l_lngAccountId = l_opLoginSec.getAccountId();

            //口座を取得する
            MainAccount l_mainAccount = l_accountMananger.getMainAccount(l_lngAccountId);

            //証券会社コード                
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();   
            log.debug("証券会社コード" + l_strInstitutionCode);                                           

            //部店コード
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            log.debug("部店コード" + l_strBranchCode);

            // １）　@リクエストデータの型が「株価指数オプション取消完了リクエスト」の場合のみ、口座をロックする。<BR> 
            // 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
            // ※引数はOpLoginSecurityServiceより編集。<BR>
            if (l_request instanceof WEB3FuturesCancelCompleteRequest)
            {
                    l_accountMananger.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_mainAccount.getAccountCode());
            }

            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);  
            
            //注文マネージャを取得する      
            WEB3FuturesOrderManagerImpl l_orderManager = 
                (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();                
                
            //注文ＩＤを指定し注文単位取得            
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);    
            OrderUnit l_orderUnit = null;
            if (l_orderUnits.length==0)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);   
            } 
            else 
            {
                l_orderUnit = l_orderUnits[0]; 
            }       
            
            IfoOrderUnitRow l_ifoOrderUnitRow = 
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject(); 
                                       
            //銘柄ＩＤを取得する        
            l_lngProductId = l_ifoOrderUnitRow.getProductId();

            //先物OP銘柄マネージャを取得する          
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager(); 
                                       
            //先物OP銘柄オブジェクトを生成する
            WEB3IfoProductImpl l_ifoProduct = 
                (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
                                        
            //原資産銘柄コードを取得する
            String l_strProductCode = l_ifoProduct.getUnderlyingProductCode();            
            log.debug("原資産銘柄コードを取得:" + l_strProductCode);

            //取引カレンダコンテキストを生成する
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();

            // ２）　@取引カレンダコンテキストに内容をセットする。            
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード =  ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”12：株価指数先物OP（取消訂正）”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL);            
            //取引カレンダコンテキスト.銘柄コード = 原資産銘柄コード
            l_context.setProductCode(l_strProductCode);
            //取引カレンダコンテキスト.注文受付商品 = ”05：先物”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
            //取引カレンダコンテキスト.注文受付トランザクション = ”06：取消”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            // ３）　@受付日時、日付ロールをセットする。
            try 
            {
                WEB3GentradeTradingTimeManagement.setTimestamp();
            } 
            catch (WEB3SystemLayerException l_ex) 
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + METHOD_NAME);           
            }
            
            // ４）　@取引所が休憩時間帯の場合(*2)のみ、キューテーブルの共有ロックを行う。
            // 取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。
            if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
            {
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnits[0];
                // 先物OP発注サービス.lock先物OP注文取引キュー(訂正対象注文単位(*3))をコールする。
                WEB3IfoFrontOrderService l_ifoOrderService =
                    (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                l_ifoOrderService.lockHostFotypeOrderAll(l_ifoOrderUnit);
            }

            return l_context;
        }
        catch (WEB3BaseException l_ex)
        {                      
            log.error("The Exception happened.");
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + METHOD_NAME); 
        }
        catch (NotFoundException l_nfe)
        {                      
            log.error("The Exception happened.");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME); 
        }  
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
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 40A81C72035D
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String METHOD_NAME =
             "onReturn(Object,Object)";
        log.entering(METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        log.exiting(METHOD_NAME);        
          
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
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 40A81C72036D
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String METHOD_NAME =
             "onThrowable(Object,Throwable)";
        log.entering(METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(METHOD_NAME);          
     
    }
}
@
