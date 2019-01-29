head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越スキップ銘柄通知繰越対象チェックサービスインタセプタ(WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 中尾寿彦(SRA) 新規作成
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文繰越スキップ銘柄通知繰越対象チェックサービスインタセプタ）。<BR>
 * <BR>
 * interface「株式注文繰越スキップ銘柄通知繰越対象チェックサービス」に対する<BR>
 * インタセプタ。<BR>
 * 取引時間時間管理クラスを使用するためのコンテキスト設定を行う。
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor
    implements Interceptor
{
    /**
       * ログ出力ユーティリティ。
       */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor.class);

    /**
     * @@roseuid 40B2E7BD02D7
     */
    public WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor()
    {

    }

    /**
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を注文単位オブジェクトにキャストする。<BR>
     * 　@－注文単位オブジェクトの内容より取引時間コンテキストのプロパティをセットする。
     * <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 注文単位.部店ID の部店<BR>
     * オブジェクトの同項目<BR>
     * 　@取引カレンダコンテキスト.部店コード = 注文単位.部店ID の部店<BR>
     * オブジェクトの同項目<BR>
     * 　@取引カレンダコンテキスト.市場コード = 注文単位.市場ID の市場<BR>
     * オブジェクトの同項目<BR>
     *   取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = null（使用しないため）<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null（使用しないため）<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。<BR>
     * 設定キー："web3.tradingcalendarcontext"<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 406A4D45028B
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParam == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //サービスの引数[0]を注文単位オブジェクトにキャストする
        OrderUnit l_orderUnit = (OrderUnit) l_serviceParam[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        if (l_orderUnitRow == null)
        {
            return null;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Branch l_branch;
        Market l_market;
        try
        {
            //get部店オブジェクト
            l_branch =
                l_finApp.getAccountManager().getBranch(
                    l_orderUnit.getBranchId());
            //get市場オブジェクト
            l_market =
                l_finApp.getFinObjectManager().getMarket(
                    l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_nfe.getMessage(),
            l_nfe);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
            
        //取引カレンダコンテキスト.証券会社コード = 注文単位.部店ID の
        //部店オブジェクトの同項目
        l_context.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());
            
            
        //取引カレンダコンテキスト.部店コード = 注文単位.部店ID の部店
        // オブジェクトの同項目
        l_context.setBranchCode(l_branch.getBranchCode());
        
        //取引カレンダコンテキスト.市場コード = 注文単位.市場ID の市場
        //オブジェクトの同項目
        l_context.setMarketCode(l_market.getMarketCode());
        
        //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //取引カレンダコンテキスト.注文受付商品 = null
        l_context.setOrderAcceptProduct(null);
        
        //取引カレンダコンテキスト.注文受付トランザクション = null
        // 注文単位.注文種別より判定して編集
        l_context.setOrderAcceptTransaction(null);
            
        //取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //受付日時、日付ロールをセットする
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
                l_ex.getMessage(),
                l_ex);                  
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }

    /**
     * 取引カレンダコンテキストクリア処理。<BR>
     * サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_returnValue - onCallリターン値
     * @@param l_context - サービスメソッドリターン値
     * @@roseuid 406A4D45029B
     */
    public void onReturn(Object l_returnValue, Object l_context)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 406030FF02C8
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

    }
}
@
