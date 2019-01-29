head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文繰越UnitServiceインタセプタ(WEB3FuturesOrderCarryOverUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 艾興 (中訊) 新規作成
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物注文繰越UnitServiceインタセプタ)<BR>
 * <BR>
 * Plugin時先物注文繰越UnitServiceに対して設定する。<BR>
 * @@author  王暁傑
 * @@version 1.0
 */
public class WEB3FuturesOrderCarryOverUnitServiceInterceptor
    implements Interceptor
{
    /**
       * ログ出力ユーティリティ。<BR>
       */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOrderCarryOverUnitServiceInterceptor.class);
    /**
     * @@roseuid 40F7A0370399
     */
    public WEB3FuturesOrderCarryOverUnitServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－引数.サービスの引数[0]を注文単位オブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容<BR>より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>注文単位.証券会社部店ＩＤに該当する部店の証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>注文単位.証券会社部店ＩＤに該当する部店の部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”05：先物” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会” <BR>
     * <BR>
     *  (*1) 原資産銘柄コードの取得方法@<BR>
     * 　@注文単位.getProductId()にて銘柄ＩＤを取得する。<BR>
     * 　@銘柄ＩＤに該当する先物OP銘柄オブジェクトを生成する。<BR>
     * 　@生成した先物OP銘柄.get原資産銘柄コード()。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * ※引数は注文単位より編集。<BR> 
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 40A88BFB032F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall() ";
        log.entering(STR_METHOD_NAME);

        OrderUnit l_orderUnit = null;
        if (l_serviceParam[0] instanceof OrderUnit)
        {
            l_orderUnit = (OrderUnit)l_serviceParam[0];
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,      
                 this.getClass().getName());
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        long l_lngBranchId = l_orderUnit.getBranchId();
        try
        {
            l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_nfe)
        {          
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME);
        }

        //部店証券会社コード
        String l_strBranchInstitutionCode = l_branch.getInstitution().getInstitutionCode();

        //部店コード
        String l_strBranchCode = l_branch.getBranchCode();

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // 取引カレンダコンテキスト.証券会社コード = 注文単位.証券会社部店ＩＤに該当する部店の証券会社コード<BR>
        l_context.setInstitutionCode(l_strBranchInstitutionCode);
        //取引カレンダコンテキスト.部店コード = 注文単位.証券会社部店ＩＤに該当する部店の部店コード<BR>
        l_context.setBranchCode(l_strBranchCode);
        // 取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        // 取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
        l_context.setTradingTimeType(
            WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        //商品コード
        IfoProduct l_ifoProduct = (IfoProduct)l_orderUnit.getProduct();
        
        String l_strUnderLyingProductCode =
            l_ifoProduct.getUnderlyingProductCode();

        // 取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード
        l_context.setProductCode(l_strUnderLyingProductCode);
        // 取引カレンダコンテキスト.注文受付商品 = ”05：先物”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
        //取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        try
        {
            //日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 40A88BFB033E
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + "'onReturn(Object l_context, Object l_returnValue)";
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
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 40A88BFB035D
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".onThrowable(Object l_obj, Throwable l_throwable) ";
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
