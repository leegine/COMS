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
filename	WEB3OptionOrderExecutedInquiryServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文約定照会サービスインタセプタ(WEB3OptionOrderExecutedInquiryServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 馬振田 (中訊) 新規作成
                   2004/07/26 李　@媛 (中訊)　@修正（UTBUG（WEB3_IFO_UT-000028）を対応）
                   2004/07/30 李　@媛 (中訊)　@修正（UTBUG（WEB3_IFO_UT-000076）を対応）
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
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryRequest;
/**
 * (OP注文約定照会サービスインタセプタ)<BR>
 * 株価指数オプション注文約定照会サービスインタセプタクラス<BR>
 * @@author  馬振田
 * @@version 1.0
 */
public class WEB3OptionOrderExecutedInquiryServiceInterceptor
    implements Interceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderExecutedInquiryServiceInterceptor.class);
    /**
     * @@roseuid 40C07A3A008C
     */
    public WEB3OptionOrderExecutedInquiryServiceInterceptor()
    {
    }
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@　@　@※引数.サービスメソッド＝"search注文約定照会"の場合のみ。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 =”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = null（*)<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”06：オプション”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     *  (*)商品コードはexecute( )からコールされるメソッド内で、 リセットする。<BR>
     * <BR>
     * 　@※OpLoginSecurityServiceより取得した口座ID == 0の場合、<BR>
     * 　@リクエスト.注文IDに該当する注文.口座IDを取引時間コンテキストにセットする。<BR>
     * 　@設定キー： ACCOUNT_ID<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて
     * 取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
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
        
        if ((l_serviceParam == null)
            || (l_serviceParam[0] == null)
            || !(l_serviceParam[0] instanceof WEB3GenRequest))
        {
            log.error(
                "__照会は未対応です__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "onCall"));
        }
        
        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode = null; //部店コード
        
        FinApp l_finApp =  (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        MainAccount l_mainAccount;
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
               
        try
        {
            //口座IDを取得する。
            long l_lngAccountId = l_opLoginSec.getAccountId();
            //管理者セッションからコールされた場合
            if (l_lngAccountId == 0L)
            {
                long l_lngOrderId = 0L;
                //リクエスト == オプション注文約定詳細の場合
                if (l_request instanceof WEB3OptionsExecuteDetailsRequest)
                {
                    WEB3OptionsExecuteDetailsRequest l_detailRequest =
                        (WEB3OptionsExecuteDetailsRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_detailRequest.id);
                }
                //リクエスト == オプション注文約定履歴の場合
                else if (l_request instanceof WEB3OptionsOrderHistoryRequest)
                {
                    WEB3OptionsOrderHistoryRequest l_historyRequest =
                        (WEB3OptionsOrderHistoryRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                //リクエスト == オプション返済建玉一覧の場合
                else if (l_request instanceof WEB3OptionsCloseMarginContractListRequest)
                {
                    WEB3OptionsCloseMarginContractListRequest l_contractRequest =
                        (WEB3OptionsCloseMarginContractListRequest)l_request;
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
            
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            //証券会社コードを取得
            l_strInstitutionCode =
                l_mainAccount.getInstitution().getInstitutionCode();
            //部店コードを取得
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集        
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.商品コード = null
                l_context.setProductCode(null);
            //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            //取引カレンダコンテキスト.注文受付商品 = ”06：オプション”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            //取引カレンダコンテキスト.注文受付トランザクション =”07：照会”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(
                "__an unexpected error__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + ".onCall",
                    l_nfe));
        }
        //受付日時、日付ロールをセットする。
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
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
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 409EFFA3014F
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String l_strMethodName = "onReturn(Object,Object)";
        log.entering(l_strMethodName);
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
            WEB3IfoAttributeNameDef.ACCOUNT_ID,
            null);
        log.exiting(l_strMethodName);
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
        final String l_strMethodName = "onThrowable(Object,Throwable)";
        log.entering(l_strMethodName);
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
            WEB3IfoAttributeNameDef.ACCOUNT_ID,
            null);
        log.exiting(l_strMethodName);
    }
}
@
