head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消受付UnitServiceインタセプタクラス(WEB3MutualCancelAcceptUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/23 韋念瓊 (中訊) レビュー    
*/

package webbroker3.mf;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託取消受付UnitServiceインタセプタクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptUnitServiceInterceptor
    implements Interceptor
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualCancelAcceptUnitServiceInterceptor.class);

    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－引数.サービスの引数[0]を投信注文単位オブジェクトにキャストする。 <BR>
     * <BR>
     * 　@－部店オブジェクトの取得 <BR>
     * 　@　@アカウントマネージャ.getBranch()をコールして部店オブジェクトを取得する。 <BR>
     * 　@　@[getBranchに渡すパラメタ] <BR>
     * 　@　@　@部店ID： 取得した投信注文単位.getBranchId()の戻り値 <BR>
     * <BR>
     * 　@－証券会社オブジェクトの取得 <BR>
     * 　@　@取得した部店オブジェクト.getInstitution()をコールして<BR>
     * 証券会社オブジェクトを取得する。 <BR>
     * <BR>
     * 　@－取得した投信注文単位オブジェクト.getDataSourceObject()をコールし、<BR>
     * 投信注文単位Paramsを取得する。 <BR>
     * <BR>
     * 　@－拡張投信銘柄オブジェクトを取得する。<BR>
     * 　@　@拡張投信銘柄マネージャ.getProduct()をコールして、<BR>
     * Productオブジェクトを取得する。<BR>
     * 　@　@［getProductに渡すパラメタ］<BR>
     * 　@　@　@銘柄ID： 取得した投信注文単位Params.getProductId()の戻り値<BR>
     * 　@　@拡張投信銘柄マネージャ.to銘柄()をコールして、<BR>
     * 拡張投信銘柄オブジェクトを取得する。<BR>
     * 　@　@［to銘柄に渡すパラメタ]<BR>
     * 　@　@　@Rowオブジェクト： <BR>
     * 取得したProductオブジェクト.getDataSourceObject()の戻り値<BR>
     * <BR>
     * 　@－取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード = <BR>
     * 取得した証券会社オブジェクト.getInstitutionCode()の戻り値 <BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = 取得した部店オブジェクト<BR>
     * .getBranchCode()の戻り値 <BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = <BR>
     * 取得した拡張投信銘柄オブジェクト.getProductCode()の戻り値<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = <BR>
     * WEB3TradingTimeTypeDef.投資信託<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     * 取得した注文受付トランザクション <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 取引時間コンテキストをセットする。 <BR>
     * 　@　@［setAttributeに渡すパラメタ］ <BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG <BR>
     * 　@　@　@設定値： 取引時間コンテキスト <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@口座をロックする。 <BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
     * 　@　@※引数は投信取消受付キューから編集。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 405660100198
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_serviceParam.length == 0)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １）　@取引カレンダコンテキストに内容をセットする
        //－引数.サービスの引数[0]を投信注文単位オブジェクトにキャストする
        MutualFundOrderUnit l_mutualOrderUnit =
            (MutualFundOrderUnit) l_serviceParam[0];

        //－部店オブジェクトの取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //アカウントマネージャ.getBranch()をコールして部店オブジェクトを取得する
            Branch l_branch =
                l_accMgr.getBranch(l_mutualOrderUnit.getBranchId());

            //－証券会社オブジェクトの取得
            // 取得した部店オブジェクト.getInstitution()をコール
            Institution l_institution = l_branch.getInstitution();

            //－取得した投信注文単位オブジェクト.getDataSourceObject()をコール
            MutualFundOrderUnitParams l_mutualOrderUnitParams =
                new MutualFundOrderUnitParams(
                      (MutualFundOrderUnitRow) l_mutualOrderUnit.getDataSourceObject());

            //－拡張投信銘柄オブジェクトを取得する 
            //拡張投信銘柄マネージャを取得する
            WEB3MutualFundProductManager l_mutualManager =
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();

            //拡張投信銘柄マネージャ.getProduct()をコール
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualManager.getProduct(
                    l_mutualOrderUnitParams.getProductId());

            //－取引時間コンテキストのプロパティをセットする
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //取引カレンダコンテキスト.証券会社コード = 証券会社オブジェクト.getInstitutionCode()
            l_context.setInstitutionCode(l_institution.getInstitutionCode());
            
            //取引カレンダコンテキスト.部店コード = 部店オブジェクト.getBranchCode()
            l_context.setBranchCode(l_branch.getBranchCode());
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.銘柄コード = 拡張投信銘柄オブジェクト.getProductCode()
            l_context.setProductCode(l_mutualFundProduct.getProductCode());
            
            //取引カレンダコンテキスト.受付時間区分 =  WEB3TradingTimeTypeDef.投資信託
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            
            //取引カレンダコンテキスト.注文受付商品 = ”07：投資信託”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
            
            //取引カレンダコンテキスト.注文受付トランザクション = 取得した注文受付トランザクション
            l_context.setOrderAcceptTransaction(null);
            
            //-ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセット
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3GentradeTradingTimeManagement.setTimestamp();              
                         
            //３）　@口座をロックする。 
            String l_strAccountCode = l_accMgr.getMainAccount(
                    l_mutualOrderUnitParams.getAccountId()).getAccountCode();      
            l_accMgr.lockAccount(
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_strAccountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__an unexpected error__ when "
                    + " l_manager.getBranch(BranchId)"
                    + " with BranchId = "
                    + l_mutualOrderUnit.getBranchId(),l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",l_ex);
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
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 4056601001A7
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
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
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 4056601001B7
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
