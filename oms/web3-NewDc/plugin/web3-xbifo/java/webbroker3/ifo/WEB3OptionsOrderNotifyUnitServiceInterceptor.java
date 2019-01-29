head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文通知UnitServiceインタセプタ(WEB3OptionsOrderNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP注文通知UnitServiceインタセプタ)<BR>
 * 株価指数オプション注文通知１件サービスインタセプタクラス<BR>
 * Plugin時にOP注文通知UnitServiceに対して設定する。<BR>
 * @@author  : 凌建平
 * @@version : 1.0
 */
public class WEB3OptionsOrderNotifyUnitServiceInterceptor implements Interceptor 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsOrderNotifyUnitServiceInterceptor.class);
   
    /**
     * @@roseuid 41AAE84401C5
     */
    public WEB3OptionsOrderNotifyUnitServiceInterceptor() 
    {
        
    }
   
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより[OP注文通知Unitサービス]実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－引数.サービスの引数[0]を先物OP注文通知キューParamsにキャストする。<BR>
     * 　@－先物OP注文通知キューParamsの内容より、<BR>
     *     取引カレンダコンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード    = 
     * 先物OP注文通知キューParams.証券会社コード <BR>
     * 　@取引カレンダコンテキスト.部店コード    = 先物OP注文通知キューParams.部店コード 
     * <BR>
     * 　@取引カレンダコンテキスト.市場コード    = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分    = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード    = 
     * 先物OP注文通知キューParams.原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品    = ”06：オプション”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = (*)<BR>
     * <BR>
     * 　@(*)注文受付トランザクションの設定方法@<BR>
     * 　@・先物OP注文通知キューParams.取引コード(SONAR) == "先物OP建"の場合、<BR>
     * 　@　@－ 先物OP注文通知キューParams.売買区分 == "買建"ならば、”買付”。<BR>
     * 　@　@－ 先物OP注文通知キューParams.売買区分 == "売建"ならば、”売付”。<BR>
     * 　@・先物OP注文通知キューParams.取引コード(SONAR) == 
     * "先物OP埋"の場合、”返済”をセット。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( 
     * )にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@受付日時として、SONAR側での発注日時(=発注日時(biz_datetime))を使用する為、<BR>
     * 　@　@　@取引時間管理.setTimestamp()にて設定された受付日時を上書きする。※注参照）<BR> 
     *　@ －ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     *　@　@　@先物OP注文通知キューParams.発注日時をセットする。<BR> 
     * 　@　@ 設定キー：　@取引時間管理.TIMESTAMP_TAG<BR>
     *   ○ ※注 ○ <BR>
     *      SONAR側から受領した受注日時(create_datetime)は<BR>
     *      登録者による手入力項目（SONAR側入力チェックほとんど無し）であるため、<BR>
     *      取引銘柄取得のsystem_timestampとしては使用できない。<BR>
     *      しかし、SONAR側から受領した発注日時(biz_datetime)は <BR>
     *      SONARシステムが発注時に自動セットする項目であり、<BR>
     *      YYYYMMDDには当日日付が、<BR>
     *      HHMMSSには当日の開局前～開局時間帯の時間が入ってくるため、<BR>
     *      このサービスにおいては発注日時(biz_datetime)を<BR>
     *      取引銘柄取得のsystem_timestampとして使用する。<BR>
     * <BR>
     * ４）　@口座をロックする。<BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード,<BR> 
     *      口座コード)をコールする。<BR>
     *      ※引数は先物OP注文通知キューParamsより編集。<BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParams - サービスメソッド引数
     * @@return Object
     * @@roseuid 4163B1F4026E
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
       
        // 引数.サービスの引数[0]を先物OP注文通知キューParamsにキャストする。<BR>
        HostFotypeOrderReceiptParams l_receiptParams = null;
        if (l_serviceParams[0] instanceof HostFotypeOrderReceiptParams)
        {
            l_receiptParams = (HostFotypeOrderReceiptParams)l_serviceParams[0];
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                STR_METHOD_NAME);
        }
        
        //１）　@取引カレンダコンテキストに内容をセットする
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
            
        // 取引カレンダコンテキスト.証券会社コード = 先物OP注文通知キューParams.証券会社コード
        l_context.setInstitutionCode(l_receiptParams.getInstitutionCode());

        // 取引カレンダコンテキスト.部店コード = 先物OP注文通知キューParams.部店コード 
        l_context.setBranchCode(l_receiptParams.getBranchCode());

        // 取引カレンダコンテキスト.市場コード   = ”0：DEFAULT”<BR>
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

        // 取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        // 取引カレンダコンテキスト.商品コード = 先物OP注文通知キューParams.原資産銘柄コード
        l_context.setProductCode(l_receiptParams.getUnderlyingProductCode());

        // 取引カレンダコンテキスト.注文受付商品 = ”06：オプション”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);

        // 取引カレンダコンテキスト.注文受付トランザクション = (*)
        //  (*)注文受付トランザクションの設定方法@<BR>
        //  ・先物OP注文通知キューParams.取引コード(SONAR) == "先物OP建"の場合、<BR>
        //  － 先物OP注文通知キューParams.売買区分 == "買建"ならば、”買付”。<BR>
        //  － 先物OP注文通知キューParams.売買区分 == "売建"ならば、”売付”。<BR>
        //  ・先物OP注文通知キューParams.取引コード(SONAR) == "先物OP埋"の場合、”返済”をセット。<BR>
        String l_strTradeType = null;
        if (WEB3TransactionTypeSONARDef.OPEN_CONTRACT.equals(l_receiptParams.getSonarTradedCode()))
        {
            if (WEB3TradeTypeDef.OPEN_LONG_MARGIN.equals(l_receiptParams.getTradeType()))
            {
                l_strTradeType = WEB3BuySellTypeDef.BUY;
            }
            else if (WEB3TradeTypeDef.OPEN_SHORT_MARGIN.equals(l_receiptParams.getTradeType()))
            {
                l_strTradeType = WEB3BuySellTypeDef.SELL;
            }
        }
        else if (WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(l_receiptParams.getSonarTradedCode()))
        {
            l_strTradeType = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
        }
        l_context.setOrderAcceptTransaction(l_strTradeType);
        
        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);         

        try
        {
            // ２）　@受付日時、日付ロールをセットする                    
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
        }

        // ３）－ThreadLocalSystemAttributesRegistry.setAttribute(<BR> 
        //  )にて先物OP注文通知キューParams.発注日時をセットする。 <BR>
        //  　@　@設定キー：　@取引時間管理.TIMESTAMP_TAG <BR>
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, 
            l_receiptParams.getBizDatetime());

        // ４）　@口座をロックする。<BR>
        // －拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 
        // 口座コード)をコールする。<BR>
        // ※引数は先物OP注文通知キューParamsより編集。<BR>
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            l_accountManager.lockAccount(
                l_receiptParams.getInstitutionCode(),
                l_receiptParams.getBranchCode(),
                l_receiptParams.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
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
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 4163B1F4028D
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
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
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 4163B1F402AC
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
    }
}
@
