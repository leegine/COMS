head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOサービスインタセプタ基礎(WEB3IpoServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>068
*/

package webbroker3.ipo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.message.WEB3IPODeclineCompleteRequest;
import webbroker3.ipo.message.WEB3IPOOfferCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * IPOサービスインタセプタ基礎クラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IpoServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoServiceInterceptor.class);
        
    /**
     * @@roseuid 41130179033C
     */
    public WEB3IpoServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR> 
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの<BR>
     * 内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR> 
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”02：IPO” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”20：IPO” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コン<BR>テキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR> 
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_method - サービスメソッド
     * @@param l_serviceParams - サービスメソッド引数
     * @@return Object
     * @@roseuid 40D27570021A
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method,Object[])";

        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダコンテキストに内容をセットする。
        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode = null; //部店コード

        try
        {            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

            //MainAccountを取得
            long l_lngAccountId = l_opLoginSec.getAccountId(); 
            log.debug("Account Id: " + l_lngAccountId);
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
			MainAccount l_mainAccount = null;
            
			//管理者の場合は管理者オブジェクトから取得する
            if(l_lngAccountId != 0){
            	log.debug("顧客用");
            	AccountManager l_accMgr = l_finApp.getAccountManager();
				l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
				// 証券会社コードを取得
				l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
				// 部店コードを取得
				l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            }else{
            	log.debug("管理者用");
            	WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            	// 証券会社コードを取得
            	l_strInstitutionCode = l_admin.getInstitutionCode();
            	// 部店コードを取得	
            	l_strBranchCode = l_admin.getBranchCode();
            	
            }
            log.debug("Institution Code: " + l_strInstitutionCode);
            log.debug("Branch Code: " + l_strBranchCode);

            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

            log.debug("取引時間コンテキストのプロパティをセット: ENTER");
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”02：IPO”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.IPO);
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”20：IPO” 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.IPO);
            //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            log.debug("取引時間コンテキストのプロパティをセット: END");

            //取引時間コンテキストをセットする。
            log.debug("取引時間コンテキストをセット: ENTER");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            log.debug("取引時間コンテキストをセット: END");

            //２）　@受付日時、日付ロールをセットする。
            //WEB3SystemLayerException
            log.debug("受付日時、日付ロールをセット: ENTER");
            WEB3GentradeTradingTimeManagement.setTimestamp();
            log.debug("受付日時、日付ロールをセット: END");

			if (l_serviceParams[0] instanceof WEB3IPOOfferCompleteRequest
						|| l_serviceParams[0] instanceof WEB3IPODeclineCompleteRequest)
			{
				log.debug("口座をロックする。");
				// 拡張アカウントマネージャ取得
				WEB3GentradeAccountManager l_gentradeAccountManaer = 
					(WEB3GentradeAccountManager) l_finApp.getAccountManager();
				
				//1.2.口座をロックする。
				l_gentradeAccountManaer.lockAccount(
					l_strInstitutionCode,
					l_strBranchCode,
					l_mainAccount.getAccountCode());				
			}

            log.exiting(STR_METHOD_NAME);

            return l_context;
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "データ不整合エラー。",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex)
                );
        }
        catch (WEB3BaseException l_ex)
        {   
            log.error("予期しないシステムエラーが発生しました。", l_ex);
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
     * @@roseuid 40D2757002C6
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object,Object)";
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
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 40D2757002D5
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object,Throwable)";
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
