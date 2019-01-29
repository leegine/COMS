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
filename	WEB3FuturesExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文約定照会サービスインタセプタ(WEB3FuturesExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李媛 (中訊) 新規作成
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrder;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP注文約定照会サービスインタセプタ)<BR>
 * 株価指数オプション注文約定照会サービスインタセプタクラス<BR>
 */
public class WEB3FuturesExecuteReferenceServiceInterceptor implements Interceptor 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesExecuteReferenceServiceInterceptor.class);
    
    /**
     * @@roseuid 40C07A3A008C
     */
    public WEB3FuturesExecuteReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>    
     *    アイテムの定義<BR>
     *    サービスメソッド開始時にコールされる。<BR> 
     * <BR>
     *    １）　@取引カレンダコンテキストに内容をセットする。<BR> 
     *　@    －サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR> 
     *　@　@　@    ※引数.サービスメソッド＝"get注文約定照会"の場合のみ。<BR> 
     *　@    －リクエストデータの内容と、OpLoginSecurityServiceの内容より <BR>
     *    取引時間コンテキストのプロパティをセットする。<BR> 
     * <BR>
     *　@    取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR> 
     *　@    取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     *　@    取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR> 
     *　@    取引カレンダコンテキスト.受付時間区分 =”11：株価指数先物OP”<BR> 
     *　@    取引カレンダコンテキスト.商品コード = null（*)<BR>
     *　@    取引カレンダコンテキスト.注文受付商品 = ”05：先物”<BR> 
     *　@    取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR> 
     * <BR>
     *    (*)商品コードはexecute( )からコールされるメソッド内で、 リセットする。<BR> 
     * <BR>
     *　@    ※OpLoginSecurityServiceより取得した口座ID == 0の場合、<BR>
     *　@    リクエスト.注文IDに該当する注文.口座IDを取引時間コンテキストにセットする。<BR>
     *　@    設定キー： ACCOUNT_ID<BR>
     * <BR>
     *　@    －ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     *　@　@　@取引時間コンテキストをセットする。 <BR>
     *    　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     *    ２）　@受付日時、日付ロールをセットする。<BR> 
     *　@    －取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 4057FBD200CD
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);
        
        Object l_request = l_serviceParam[0];
             
        if ((l_serviceParam == null) || (l_serviceParam[0] == null)) 
        {

            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + "onCall");
        } 
        
        if (!(l_serviceParam[0] instanceof WEB3GenRequest))
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);               
        }
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        try
        { 

            String l_strInstitutionCode = null; //証券会社コード
            String l_strBranchCode      = null; //部店コード

            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
                        
            //MainAccountを取得
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            //管理者セッションからコールされた場合
            if (l_lngAccountId == 0L)
            {
                long l_lngOrderId = 0L;
                //リクエスト == 先物注文約定詳細の場合
                if (l_request instanceof WEB3FuturesExecuteDetailsRequest)
                {
                    WEB3FuturesExecuteDetailsRequest l_detailRequest =
                        (WEB3FuturesExecuteDetailsRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_detailRequest.id);
                }
                //リクエスト == 先物注文約定履歴の場合
                else if (l_request instanceof WEB3FuturesOrderHistoryRequest)
                {
                    WEB3FuturesOrderHistoryRequest l_historyRequest =
                        (WEB3FuturesOrderHistoryRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                //リクエスト == 先物返済建玉一覧の場合
                else if (l_request instanceof WEB3FuturesCloseMarginContractListRequest)
                {
                    WEB3FuturesCloseMarginContractListRequest l_contractRequest =
                        (WEB3FuturesCloseMarginContractListRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_contractRequest.id);
                }

                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3FuturesOrderManagerImpl l_orderManager = 
                    (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
                
                IfoOrder l_ifoOrder =
                    (IfoOrder)l_orderManager.getOrder(l_lngOrderId);
                l_lngAccountId = l_ifoOrder.getAccountId();
                
                //ThreadLocalに取得した口座IDをセット。
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.ACCOUNT_ID, new Long(l_lngAccountId));
            }
            
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
                
            //証券会社コードを取得
            Institution l_institution = l_mainAccount.getInstitution();
            l_strInstitutionCode = l_institution.getInstitutionCode();
            //部店コードを取得
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();   
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集        
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.商品コード = null
            l_context.setProductCode(null); 
            //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            //取引カレンダコンテキスト.注文受付商品 = ”05：先物”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
            //取引カレンダコンテキスト.注文受付トランザクション =”07：照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);   
            
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context); 
                
            //受付日時、日付ロールをセットする。   
            WEB3GentradeTradingTimeManagement.setTimestamp();
              
            log.exiting(STR_METHOD_NAME);
      
        } 
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
        }   
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
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 409EFFA3014F
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
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
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3IfoAttributeNameDef.ACCOUNT_ID,
            null);
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 409EFFA3016F
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
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
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3IfoAttributeNameDef.ACCOUNT_ID,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
