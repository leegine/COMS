head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替通知UnitServiceインタセプタクラス(WEB3AioSecurityTransferNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 王蘭芬(中訊) 新規作成
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
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;


/**
 * (証券振替通知UnitServiceインタセプタ)<BR>
 * 証券振替通知UnitServiceインタセプタクラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferNotifyUnitServiceInterceptor.class);
    
    /**
     * @@roseuid 41B031750167
     */
    public WEB3AioSecurityTransferNotifyUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]の配列の最初の要素を注文単位オブジェクトにキャストする。 <BR>
     * 　@－注文単位の内容より取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =<BR>
     *       注文単位.部店ＩＤに該当する部店の証券会社コード <BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     *       注文単位.部店ＩＤに該当する部店の部店コード <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”20：証券振替” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”11：証券振替” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”09：振替” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *          にて取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@口座をロックする。 <BR>
     * 　@－拡張アカウントマネージャ.lock口座<BR>
     *          (証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     *   ※引数は注文単位より編集。
     * @@param l_method
     * @@param l_obj
     * @@return Object
     * @@roseuid 41579471011B
     */
    public Object onCall(Method l_method, Object[] l_obj) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_obj)";
        log.entering(STR_METHOD_NAME);

        if (l_obj == null || l_obj.length == 0)
        {
            log.debug("l_obj is null or l_obj length is 0");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@取引カレンダコンテキストに内容をセットする。 
        //　@－引数.サービスの引数[0]を注文単位オブジェクトにキャストする。 
        //　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 
        AioOrderUnit[] l_aioOrderUnits = (AioOrderUnit[]) l_obj[0];
        
        //取引カレンダコンテキスト.証券会社コード = 注文単位.部店ＩＤに該当する部店の証券会社コード
        //取引カレンダコンテキスト.部店コード = 注文単位.部店ＩＤに該当する部店の部店コード
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        
        long l_lngAccountId = l_aioOrderUnits[0].getAccountId();  
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            
            //取引カレンダコンテキスト.証券会社コード = 注文単位.証券会社部店ＩＤに該当する部店の証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //　@取引カレンダコンテキスト.部店コード = 注文単位.証券会社部店ＩＤに該当する部店の部店コード
            l_context.setBranchCode(l_strBranchCode);
            
            //　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = ”20：証券振替”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SECURITY_TRANSFER);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.注文受付商品 = ”11：証券振替” 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.TRASUT_SUBSTITUTION_CHANGE);
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”09：振替”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);
            
            //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //２）　@受付日時、日付ロールをセットする。 
            //　@－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
			//３）　@口座をロックする。 
			//　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
			//※引数は注文単位より編集。 
			WEB3GentradeAccountManager l_accountManager = 
				(WEB3GentradeAccountManager)l_finApp.getAccountManager();
			String l_strAccountCode = l_mainAccount.getAccountCode();			
			try
			{
				l_accountManager.lockAccount(
					l_strInstitutionCode,
					l_strBranchCode,
					l_strAccountCode);
			}
			catch (WEB3BaseException l_ex)
			{
				log.error("__error in lockAccount__", l_ex);
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80002,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
        }    
        catch(NotFoundException l_ex)
        {
            log.error("no find AccountId", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
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
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_objOnCall
     * @@param l_obj
     * @@roseuid 41579471013A
     */
    public void onReturn(Object l_objOnCall, Object l_obj) 
    {
        final String STR_METHOD_NAME = "onReturn(Object l_context, Object l_obj)";
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
     * サービスメソッドが例外をスローした場合にコールされる。<BR> 
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_objOnCall
     * @@param l_throwable
     * @@roseuid 415794710159
     */
    public void onThrowable(Object l_objOnCall, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object l_obj, Throwable l_throwable)";
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
