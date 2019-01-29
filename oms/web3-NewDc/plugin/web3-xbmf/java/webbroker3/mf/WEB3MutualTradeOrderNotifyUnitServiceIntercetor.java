head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyUnitServiceIntercetor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託売買注文通知UnitServiceインタセプタクラス(WEB3MutualTradeOrderNotifyUnitServiceIntercetor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
                   2004/08/24 王蘭芬 (中訊) レビュー
                   2004/12/10 黄建 (中訊) 残対応              
*/

package webbroker3.mf;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.define.WEB3MFTradeTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託売買注文通知UnitServiceインタセプタクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualTradeOrderNotifyUnitServiceIntercetor 
    implements Interceptor 
{

    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualTradeOrderNotifyUnitServiceIntercetor.class);    

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を投信注文通知キューParamsにキャストする。<BR>
     * <BR>
     * 　@－証券会社オブジェクトを取得する。<BR>
     * 　@　@拡張アカウントマネージャ.getInstitution()をコールして、証券会社オブジェクトを取得する。<BR>
     * 　@　@[getInstitutionに渡すパラメタ]<BR>
     * 　@　@　@証券会社コード： 投信注文通知キューParams.get会社コード()の戻り値<BR>
     * <BR>
     * 　@－拡張投信銘柄オブジェクトを取得する。<BR>
     * 　@　@拡張投信銘柄マネージャ.get投信銘柄()をコールして、<BR>
     * 拡張投信銘柄オブジェクトを取得する。<BR>
     * 　@　@[get投信銘柄に渡すパラメタ]<BR>
     * 　@　@　@証券会社：　@取得した証券会社オブジェクト<BR>
     * 　@　@　@銘柄コード： 投信注文通知キューParams.get銘柄コード()の戻り値<BR>
     * <BR>
     * 　@－注文受付トランザクションを取得する。<BR>
     *     (*) 引数.投信注文通知キューParams.getデータコード()の戻り値が”CI817：募集”の場合、<BR>
     * 注文受付トランザクションの値は”11：募集” <BR>
     * 　@　@(*) 投信注文通知キューParams.get売買区分()の値が”1：売付”の場合、<BR>
     * 注文受付トランザクションの値は”02：売付（新規建売）”<BR>
     * 　@　@(*) 投信注文通知キューParams.get売買区分()の値が”2：買付”の場合、<BR>
     * 注文受付トランザクションの値は”01：買付（新規建買）”<BR>
     * <BR>
     * 　@－取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード<BR>
     *  = 投信注文通知キューParams.get会社コード()の戻り値<BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = 投信注文通知キューParams.get部店コード()の戻り値<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード<BR>
     *  = 取得した拡張投信銘柄オブジェクト.getProductCode()の戻り値<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = WEB3TradingTimeTypeDef.投資信託<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = 取得した注文受付トランザクション<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセットする。<BR>
     * 　@　@［setAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG<BR>
     * 　@　@　@設定値： 取引時間コンテキスト<BR>
     * <BR>
     * ２）受付日時、日付ロールをセットする。 <BR>
     * 　@　@－取引時間管理.setTimestamp()をコールする。<BR>
     * 　@<BR>
     * ３）　@受付日時にSONAR側での発注日時(＝発注日時(biz_datetime)) をセットする。<BR> 　@
     *       受付日時として、SONAR側での発注日時(＝発注日時(biz_datetime))を 
     *       使用する。（※注　@を参照） 
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute()にて、<BR>
     * 投信注文通知キューParams.get発注日時()の戻り値をセットする。<BR>
     * 
     * 　@　@設定キー："xblocks.gtl.attributes.system_timestamp"<BR>
     * <BR>
     * ○ ※注 ○<BR>
     * SONAR側から受領した受注日時(create_datetime)は<BR>
     * 登録者による手入力項目（SONAR側入力チェックほとんど無し）であるため、<BR>
     * 取引銘柄取得のsystem_timestampとしては使用できない。<BR>
     * しかし、SONAR側から受領した発注日時(biz_datetime)は<BR>
     * SONARシステムが発注時に自動セットする項目であり、<BR>
     * YYYYMMDDには当日日付が、<BR>
     * HHMMSSには当日の開局前～開局時間帯の時間が入ってくるため、<BR>
     * このサービスにおいては発注日時(biz_datetime)を<BR>
     * 取引銘柄取得のsystem_timestampとして使用する。<BR>
     * ４）　@口座をロックする。 <BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
     * 　@　@※引数は投信注文通知キューから編集。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 40567DF90009
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
  
        if (l_serviceParam == null)
        {
            log.debug("パラメータNull出来ない。");        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータはNullである");       
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("CollectionタイプのパラメータSizeは０できない。");        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "CollectionタイプのパラメータSizeは０できない。");      
        }
        
        //１）　@取引カレンダコンテキストに内容をセットする。
        
        //証券会社
        Institution l_institution = null;     
        
        // －サービスの引数[0]を投信注文通知キューParamsにキャストする。
        HostXbmfOrderNotifyParams l_orderNotifyParams = 
            (HostXbmfOrderNotifyParams) l_serviceParam[0];
   
        //拡張アカウントマネージャ取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManaer = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        
        //証券会社コード取得 
        String l_strInstitutionCode = 
            l_orderNotifyParams.getInstitutionCode();     
        
        try
        {
            //拡張アカウントマネージャ.getInstitution()をコールして、
            //－証券会社オブジェクトを取得する。
            l_institution = 
                l_gentradeAccountManaer.getInstitution(l_strInstitutionCode); 
           
            //拡張投信銘柄マネージャ.get投信銘柄()をコールして、
            
            //拡張投信銘柄マネージャ取得する
            WEB3MutualFundProductManager l_mutualFundProductManager =
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            
            //－拡張投信銘柄オブジェクトを取得する
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_orderNotifyParams.getProductCode());
            
            //－注文受付トランザクションを取得する。
            
            //注文受付トランザクション
            String l_strOrderAcceptTransaction = null;   
            //(*) 引数.投信注文通知キューParams.getデータコード()の戻り値が”CI817：募集”の場合、
            //注文受付トランザクションの値は”11：募集” 
            if(WEB3HostRequestCodeDef.MUTUAL_FUND_RECRUIT_ORDER_NOTIFY.equals(l_orderNotifyParams.getRequestCode()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.RECRUIT;
            }
            
            //(*) 投信注文通知キューParams.get売買区分()の値が”
             //2：買付”の場合、注文受付トランザクションの値は”01：買付（新規建買”
            else if ((WEB3MFTradeTypeDef.BUY).equals(
                    l_orderNotifyParams.getTradeType()))
            {
                l_strOrderAcceptTransaction = 
                    WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }  
            
            //(*) 投信注文通知キューParams.get売買区分()の値が”
            //1：売付”の場合、注文受付トランザクションの値は”02：売付（新規建売）”
            else if ((WEB3MFTradeTypeDef.SELL).equals(
                l_orderNotifyParams.getTradeType()))
            {
                l_strOrderAcceptTransaction =
                    WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }

            //－取引時間コンテキストのプロパティをセットする。
            
            //取引時間コンテキスト取得
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();     
           
            //取引カレンダコンテキスト.証券会社コード = 
             // 投信注文通知キューParams.get会社コード()の戻り値
            l_context.setInstitutionCode(
                l_orderNotifyParams.getInstitutionCode());
        
            //取引カレンダコンテキスト.部店コード = 
            //  投信注文通知キューParams.get部店コード()の戻り値
            l_context.setBranchCode(l_orderNotifyParams.getBranchCode());
       
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
       
            //取引カレンダコンテキスト.銘柄コード = 
            //  取得した拡張投信銘柄オブジェクト.getProductCode()の戻り値
            l_context.setProductCode(l_mutualFundProduct.getProductCode());
            
            //取引カレンダコンテキスト.受付時間区分 = 
            //  WEB3TradingTimeTypeDef.投資信託
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            
            //取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
            
            //取引カレンダコンテキスト.注文受付トランザクション = 
            //  取得した注文受付トランザクション
            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);
            
            //－ThreadLocalSystemAttributesRegistry.setAttribute()
            //  にて取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
                
            //２）受付日時、日付ロールをセットする。 
            // －取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //３）　@受付日時にSONAR側での発注日時(＝発注日時(biz_datetime)) をセットする。 
            //  受付日時として、SONAR側での発注日時(＝発注日時(biz_datetime))を 
            //  使用する。（※注　@を参照） 
            //－ThreadLocalSystemAttributesRegistry.setAttribute()にて、
            //投信注文通知キューParams.get発注日時()の戻り値をセットする。 
            //  設定キー："xblocks.gtl.attributes.system_timestamp"  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_orderNotifyParams.getBizDatetime());
                        
            //４）　@口座をロックする。      
            l_gentradeAccountManaer.lockAccount(
                    l_orderNotifyParams.getInstitutionCode(),
                    l_orderNotifyParams.getBranchCode(),
                    l_orderNotifyParams.getAccountCode());                
            
            log.exiting(STR_METHOD_NAME);
            return l_serviceParam;            
        }
        catch (NotFoundException l_ex) 
        {
              log.error("該当証券会社/銘柄コード不存在  with" +
                " (証券会社)l_institution =  " + 
                    l_orderNotifyParams.getInstitutionCode() + 
                " and (銘柄コード)l_strProductCode = " + 
                    l_orderNotifyParams.getProductCode());
              throw new WEB3BaseRuntimeException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex); 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__WEB3GentradeTradingTimeManagement.setTimestamp Error__", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        

    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 40567DF900C5
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        //１）ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //取引時間管理.OFFSET_TAG 
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //取引カレンダコンテキスト.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
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
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 40567DF90161
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME); 
        
        //１）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);        
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //取引カレンダコンテキスト.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@
