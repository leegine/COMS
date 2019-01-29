head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoBalanceReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP残高照会サービスインタセプタ(WEB3IfoBalanceReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
*/
package webbroker3.ifo;

import java.lang.reflect.Method;
import java.util.Hashtable;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalRequest;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (先物OP残高照会サービスインタセプタ)<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoBalanceReferenceServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoBalanceReferenceServiceInterceptor.class);
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@時価の変数をセットする。<BR>
     * ThreadLocalSystemAttributesRegistry.setAttribute()<BR>
     * にてThreadLocalに時価の変数をセットする<BR>
     * 　@　@　@　@　@　@　@設定キー：　@CURRENT_PRICE_INFO<BR>
     * 　@　@　@　@　@　@　@値：　@Hashtable（新規に生成したHashtable）<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの<BR>
     * 内容より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = null<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 =<BR>
     * 　@　@[リクエストデータ.先物／オプション区分 == "先物"の場合]<BR>
     * 　@　@　@”05：先物”をセット。<BR>
     * 　@　@[リクエストデータ.先物／オプション区分 == "オプション"の場合]<BR>
     * 　@　@　@”06：オプション”をセット。<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *     にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParams - サービスメソッド引数
     * @@return Object
     * @@roseuid 41AACA560276
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".onCall(Method,Object[])";
  
        log.entering(STR_METHOD_NAME);
  
        //Hashtable（新規に生成したHashtable）
        Hashtable l_currentPriceVariable = new Hashtable();
  
        //１）　@時価の変数をセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO, l_currentPriceVariable);
  
        //２）　@取引カレンダコンテキストに内容をセットする。
        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode = null; //部店コード
  
        try
        {
            long l_lngAccountId; // 口座コード
            FinApp l_finApp; //FinancialApplication
            AccountManager l_accMgr;
            MainAccount l_mainAccount;
  
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
  
            //MainAccountを取得
            l_lngAccountId = l_opLoginSec.getAccountId();           
            l_finApp = (FinApp)Services.getService(FinApp.class);
            l_accMgr = l_finApp.getAccountManager();
            //NotFoundException
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
  
            //証券会社コードを取得
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //部店コードを取得
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
  
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
  
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            //取引カレンダコンテキスト.商品コード = null
            l_context.setProductCode(null);
            if (l_serviceParams[0]instanceof WEB3FuturesOptionsBalanceReferenceTotalRequest)
            {
                WEB3FuturesOptionsBalanceReferenceTotalRequest l_request = (WEB3FuturesOptionsBalanceReferenceTotalRequest)l_serviceParams[0];
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_request.fuOpDiv))
                {
                    //取引カレンダコンテキスト.注文受付商品 = ”06：オプション”
                    l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
                }
                else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_request.fuOpDiv))
                {
                    //取引カレンダコンテキスト.注文受付商品 = ”05：先物”をセット。”
                    l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
                }
            }
            else if (l_serviceParams[0]instanceof WEB3FuturesOptionsBalanceReferenceRequest)
            {
                WEB3FuturesOptionsBalanceReferenceRequest l_request = (WEB3FuturesOptionsBalanceReferenceRequest)l_serviceParams[0];
                  if (WEB3FuturesOptionDivDef.OPTION.equals(l_request.fuOpDiv))
                  {
                      //取引カレンダコンテキスト.注文受付商品 = ”06：オプション”
                      l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
                  }
                  else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_request.fuOpDiv))
                  {
                      //取引カレンダコンテキスト.注文受付商品 = ”05：先物”をセット。”
                      l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
                  }
            }
  
  
            //取引カレンダコンテキスト.注文受付トランザクション =”07：照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
  
            //取引時間コンテキストをセットする。
  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
  
            //３）　@受付日時、日付ロールをセットする。
            //WEB3SystemLayerException
  
            WEB3GentradeTradingTimeManagement.setTimestamp();
  
            log.exiting(STR_METHOD_NAME);
  
            return l_context;
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "テーブルに該当するデータがありません。",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex));
  
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(
                "業務アプリケーションで使用するシステムエラーの例外",
                    l_ex);
  
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキスト、および時価のクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE_INFO<BR>
     * 
     * @@param l_context
     *  - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 41AACA560295
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".onReturn(Object,Object)";
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
            WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO,
            null);
  
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキスト、および時価のクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE_INFO<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 41AACA5602A5
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".onThrowable(Object,Throwable)";
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
            WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO,
            null);
  
        log.exiting(STR_METHOD_NAME);
    }
}
@
