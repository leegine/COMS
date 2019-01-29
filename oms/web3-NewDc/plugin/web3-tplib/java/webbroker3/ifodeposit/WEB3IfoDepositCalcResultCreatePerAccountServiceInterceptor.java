head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptorクラス(WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/24 孫(FLJ) 新規作成
*/

package webbroker3.ifodeposit;

import java.lang.reflect.Method;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;


/**
 * 顧客証拠金計算結果作成サービスインターセプタ
 * 
 * @@author 孫(FLJ)
 * @@version 1.0
 */
public class WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoDepositTransitionReferenceServiceInterceptor.class);

    /**
     * コンストラクタ
     */
    public WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor()
    {
    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 引数の補助口座より編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = 引数の補助口座より編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 =”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”31：証拠金推移”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     *   ※商品コード、注文受付商品については上記基準値を固定で設定することとする
     * 
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて 
     *     取引時間コンテキストをセットする。<BR>
     *       設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] arguments)
    {
        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            
            //サービスの引数[0]を補助口座オブジェクトにキャストする
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)arguments[0];
            
            //顧客オブジェクトを取得
            MainAccount l_mainAccount = l_subAccount.getMainAccount();

            // 取引カレンダコンテキストを生成
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.注文受付商品 = ”31：証拠金推移”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.DEPOSIT_TRANSITION);
            //取引カレンダコンテキスト.注文受付トランザクション =”07：照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        
            //受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

            log.debug("--------------------------------------------------");
            log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
            log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
            log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
            log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
            log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
            log.debug("TradingCalendarContext.orderAcceptProduct=" + l_context.getOrderAcceptProduct());
            log.debug("TradingCalendarContext.orderAcceptTransaction=" + l_context.getOrderAcceptTransaction());
            log.debug("--------------------------------------------------");

        }
        catch (WEB3SystemLayerException sle)
        {
            throw new WEB3BaseRuntimeException(
		        sle.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);		
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
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
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
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
            
        log.exiting(STR_METHOD_NAME);
    }
}
@
