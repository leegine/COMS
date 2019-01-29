head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投売買注文通知UnitServiceインタセプタクラス(WEB3RuitoTradeOrderNotifyUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 杜森 (中訊) 新規作成
                   2004/11/30 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.define.WEB3RuitoOrderDivTypeDef;

/**
 * 累投売買注文通知UnitServiceインタセプタクラス<BR>
 */
public class WEB3RuitoTradeOrderNotifyUnitServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyUnitServiceInterceptor.class);

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を累投注文通知キューParamsにキャストする。<BR>
     * <BR>
     * 　@－証券会社オブジェクトを取得する。<BR>
     * 　@　@アカウントマネージャ.getInstitution()をコールして、<BR>
     *             証券会社オブジェクトを取得する。<BR>
     * 　@　@[getInstitutionに渡すパラメタ] <BR>
     * 　@　@　@証券会社コード： 累投注文通知キューParams.get証券会社コード <BR>
     * <BR>
     * 　@－累投銘柄オブジェクトを取得する。<BR>
     * 　@　@拡張累投銘柄.get累投銘柄(withコースandプラン)をコールして、<BR>
     *            拡張累投銘柄オブジェクトを取得する。<BR>
     * 　@　@[get累投銘柄に渡すパラメタ] <BR>
     * 　@　@　@証券会社：　@取得した証券会社オブジェクト <BR>
     * 　@　@　@コース： 累投注文通知キューParams.getコース()の戻り値 <BR>
     * 　@　@　@プラン： 累投注文通知キューParams.getプラン()の戻り値<BR>
     * <BR>
     * 　@－注文受付トランザクションを取得する。<BR>
     * 　@　@(*)累投注文通知キューParams.get注文種別()の値が”2：買付”の場合、<BR>
     * 注文受付トランザクションの値は”01：買付（新規建買）”<BR>
     * 　@　@(*)累投注文通知キューParams.get注文種別()の値が”2：買付”でない場合、<BR>
     * 注文受付トランザクションの値は”02：売付（新規建売）” <BR>
     * <BR>
     * 　@－受付時間区分を取得する。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値が <BR>
     *               RuitoTypeEnum.中期国債ファ@ンドの場合、<BR>
     * 　@　@　@　@受付時間区分の値はWEB3TradingTimeTypeDef.中国F。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値がRuitoTypeEnum.MMF <BR>
     *                
     * で注文受付トランザクションの値が”01：買付（新規建買）”の場合、<BR>
     * 　@　@　@　@受付時間区分の値はWEB3TradingTimeTypeDef.MMF（設定）。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値が <BR>
     *          RuitoTypeEnum.MMFで注文受付トランザクションの値が <BR>
     *          ”02：売付（新規建売）”の場合、<BR>
     * 　@　@　@　@ 受付時間区分の値はWEB3TradingTimeTypeDef.MMF（設定解約）。<BR>
     * <BR>
     * 　@－取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード =  <BR>
     *                累投注文通知キューParams.get証券会社コード()の戻り値 <BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード =  <BR>
     *                累投注文通知キューParams.get部店コード()の戻り値 <BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = 取得した受付時間区分 <BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”08：累積投資”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *                取得した注文受付トランザクション <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( ) <BR>
     *              にて取引時間コンテキストをセットする。<BR>
     * 　@　@ ［setAttributeに渡すパラメタ］ <BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG <BR>
     * 　@　@　@設定値： 取引時間コンテキスト <BR>
     * <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@受付日時にSONAR側での発注日時(＝発注日時(biz_datetime)) をセットする。<BR> 
     * 　@　@受付日時として、SONAR側での発注日時(＝発注日時(biz_datetime))を <BR>
     * 　@　@使用する。（※注　@を参照） <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて、<BR>
     *      累投注文通知キューParams.発注日時 をセットする。 <BR>
     * 　@　@設定キー："xblocks.gtl.attributes.system_timestamp" <BR>
     * <BR>
     * ○ ※注 ○<BR>
     * SONAR側から受領した受注日時(create_datetime)は <BR>
     * 登録者による手入力項目（SONAR側入力チェックほとんど無し）であるため、<BR>
     * 取引銘柄取得のsystem_timestampとしては使用できない。<BR>
     * しかし、SONAR側から受領した発注日時(biz_datetime)は <BR>
     * SONARシステムが発注時に自動セットする項目であり、<BR>
     * YYYYMMDDには当日日付が、<BR>
     * HHMMSSには当日の開局前～開局時間帯の時間が入ってくるため、<BR>
     * このサービスにおいては発注日時(biz_datetime)を <BR>
     * 取引銘柄取得のsystem_timestampとして使用する。<BR>
     * ４）　@口座をロックする。 <BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
     * ※引数は累投注文通知キューParamsより編集。 <BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト <BR>
     * @@param l_serviceParam - サービスメソッド引数 <BR>
     * @@return Object
     * @@roseuid 405A4E4500BA
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParam)"; 
        log.entering(STR_METHOD_NAME);
        if (l_serviceParam == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("パラメータSizeは０できない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータSizeは０できない");
        }
        try
        {
            HostRuitoOrderNotifyParams l_hostOrderNotifyParams =
                (HostRuitoOrderNotifyParams) l_serviceParam[0];
    
            //証券会社オブジェクトを取得する
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            String l_strInstitutionCode = 
                l_hostOrderNotifyParams.getInstitutionCode();
            Institution l_institution =
                l_finApp.getAccountManager().getInstitution
                (l_strInstitutionCode);
            log.debug("証券会社オブジェクトを取得する = " + 
                l_institution.getInstitutionCode());    
           
            //累投銘柄オブジェクトを取得する
            WEB3RuitoProductManager l_ruitoProductManager =
                (WEB3RuitoProductManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getProductManager();
    
            String l_course = l_hostOrderNotifyParams.getCourse();
            String l_plan = l_hostOrderNotifyParams.getPlan();
            RuitoProduct l_ruitoProduct = null;
            l_ruitoProduct = 
                (RuitoProduct)l_ruitoProductManager.getRuitoProductWithCoursePlan(
                    l_institution, l_course, l_plan);

            RuitoProductParams l_RuitoProductParams = 
                (RuitoProductParams)l_ruitoProduct.getDataSourceObject();        
            log.debug("累投銘柄オブジェクトを取得する = " + 
                l_RuitoProductParams.getProductCode());        
    
            //－注文受付トランザクションを取得する。
            String l_strOrderAcceptTransaction = null;
            if (WEB3RuitoOrderDivTypeDef.BUY.equals(
                    l_hostOrderNotifyParams.getOrderDiv()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
            log.debug("l_orderNotifyParams.getOrderDiv() = " + 
                    l_hostOrderNotifyParams.getOrderDiv());
            log.debug("注文受付トランザクションを取得する = " + 
                l_strOrderAcceptTransaction);    
    
            //－受付時間区分を取得する。
    
            String l_strAcceptTimeDiv = null;
    
            if (l_RuitoProductParams.getRuitoType().equals( 
                RuitoTypeEnum.CHUUKOKU_FUND))
            {
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
            }
            else if ( l_RuitoProductParams.getRuitoType().equals( 
                    RuitoTypeEnum.MMF) && 
                    WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN.equals
                    (l_strOrderAcceptTransaction))
            {
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MMF_SET;
            }
            else if ( l_RuitoProductParams.getRuitoType().equals( 
                    RuitoTypeEnum.MMF) && 
                    WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN.equals
                    (l_strOrderAcceptTransaction))
            {
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MMF_SET_CANCEL;
            }
            log.debug("RuitoType = " + l_RuitoProductParams.getRuitoType());
            log.debug("l_strAcceptTimeDiv = " + l_strAcceptTimeDiv);
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            //取引カレンダコンテキスト.証券会社コード = 
            //累投注文通知キューParams.get証券会社コード()の戻り値 
            l_context.setInstitutionCode
                (l_hostOrderNotifyParams.getInstitutionCode());
    
            //取引カレンダコンテキスト.部店コード = 
            //累投注文通知キューParams.get部店コード()の戻り値
            l_context.setBranchCode(l_hostOrderNotifyParams.getBranchCode());
    
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            //取引カレンダコンテキスト.受付時間区分 = 取得した受付時間区分
            l_context.setTradingTimeType(l_strAcceptTimeDiv);
    
            //取引カレンダコンテキスト.注文受付商品 = ”08：累積投資”
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
    
            //取引カレンダコンテキスト.注文受付トランザクション = 
            //取得した注文受付トランザクション
            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);
            
            log.debug("証券会社コード = " + l_context.getInstitutionCode());
            log.debug("部店コード=" + l_context.getBranchCode());
            log.debug("市場コード=" + l_context.getMarketCode());
            log.debug("銘柄コード=" + l_context.getProductCode());
            log.debug("受付時間区分=" + l_context.getTradingTimeType());
            log.debug("注文受付商品=" + l_context.getOrderAcceptProduct());
            log.debug("注文受付トランザクション=" + 
                l_context.getOrderAcceptTransaction());
              
            //ThreadLocalSystemAttributesRegistry.setAttribute( )
            //にて取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //２）　@受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //３）　@受付日時にSONAR側での発注日時(＝発注日時(biz_datetime)) をセットする。
            // 取引時間コンテキストをセットする            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.system_timestamp",
                l_hostOrderNotifyParams.getOrderDate());
            
            //４）　@口座をロックする。 
            //－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //※引数は累投注文通知キューParamsより編集。 
            WEB3GentradeAccountManager l_gentradeAccMgr = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();       
            l_gentradeAccMgr.lockAccount(
                    l_hostOrderNotifyParams.getInstitutionCode(),
                    l_hostOrderNotifyParams.getBranchCode(),
                    l_hostOrderNotifyParams.getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("_NotFoundException_", l_ex);
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
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_context - onCallリターン値 <BR>
     * @@param l_returnValue - サービスメソッドリターン値 <BR>
     * @@roseuid 405A4E4500D9
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
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
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj - onCallリターン値 <BR>
     * @@param l_throwable - 例外オブジェクト <BR>
     * @@roseuid 405A4E4500E9
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
