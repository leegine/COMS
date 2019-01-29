head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知UnitServiceインタセプタクラス(WEB3IfoExecuteEndNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/21 艾興 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP出来終了通知UnitServiceインタセプタ)<BR>
 * 先物OP出来終了通知UnitServiceインタセプタクラス<BR>
 * Plugin時先物OP出来終了通知UnitServiceに対して設定する。<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoExecuteEndNotifyUnitServiceInterceptor
    implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoExecuteEndNotifyUnitServiceInterceptor.class);

    /**
     * @@roseuid 40C0750E009C
     */
    public WEB3IfoExecuteEndNotifyUnitServiceInterceptor()
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
     * 　@－リクエストデータの内容と、<BR>
     * OpLoginSecurityServiceの内容より<BR>
     * 取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =<BR>
     *  注文単位.証券会社部店ＩＤに該当する部店の証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * 注文単位.証券会社部店ＩＤに該当する部店の部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = null<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     * <BR>
     *  (*1) 原資産銘柄コードの取得方法@<BR>
     * 　@注文単位.getProductId()にて銘柄ＩＤを取得する。<BR>
     * 　@銘柄ＩＤに該当する先物OP銘柄オブジェクトを生成する。<BR>
     * 　@生成した先物OP銘柄.get原資産銘柄コード()。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * ※引数は注文単位より編集。 <BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 4057BB0600AE
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        // パラメータNull出来ない。  
        if (l_serviceParam == null)
        {
            log.error("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //CollectionタイプのパラメータSizeは０できない
        //パラメータタイプ不正

        if (!(l_serviceParam[0] instanceof  IfoOrderUnit))
        {
            log.error("パラメータタイプ不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GentradeTradingClendarContext l_web3GentradeTradingClendarContext =
            new WEB3GentradeTradingClendarContext();

        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_serviceParam[0];

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
        Branch l_branch = null;
        WEB3IfoProductImpl l_ifoProduct = null;

        try
        {
            //throws NotFoundException             
            l_branch = l_accountManager.getBranch(l_orderUnit.getBranchId());
            //先物OP銘柄オブジェクトを生成する
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();
            log.debug("l_lngProductId =" + l_lngProductId);
            l_ifoProduct =
                (WEB3IfoProductImpl)l_productManager.getProduct(l_lngProductId);
            log.debug("l_ifoProduct =" + l_ifoProduct);

        }
        catch (NotFoundException l_nfe)
        {                    
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_branch.getBranchCode();

        l_web3GentradeTradingClendarContext.setBranchCode(l_strBranchCode);
        l_web3GentradeTradingClendarContext.setInstitutionCode(l_strInstitutionCode);
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_web3GentradeTradingClendarContext.setMarketCode(
            WEB3MarketCodeDef.DEFAULT);
        //取引カレンダコンテキスト.受付時間区分 = ”12：株価指数先物OP”<BR>
        l_web3GentradeTradingClendarContext.setTradingTimeType(
            WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        //取引カレンダコンテキスト.注文受付商品 = null
        l_web3GentradeTradingClendarContext.setOrderAcceptProduct(null);
        //生成した先物OP銘柄.get原資産銘柄コード()。

        //      先物OP銘柄マネージャを取得する          
        String l_underlyingProductCode =
            l_ifoProduct.getUnderlyingProductCode();
        l_web3GentradeTradingClendarContext.setProductCode(
            l_underlyingProductCode);
        //注文受付商品
        l_web3GentradeTradingClendarContext.setOrderAcceptProduct(null);
        //取引カレンダコンテキスト.注文受付トランザクション = null
        l_web3GentradeTradingClendarContext.setOrderAcceptTransaction(null);
        //にて取引時間コンテキストをセットする。<BR>
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_web3GentradeTradingClendarContext);

        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_wsle)
        { 
            log.error(STR_METHOD_NAME,l_wsle);                   
            throw new WEB3BaseRuntimeException(
                l_wsle.getErrorInfo(), STR_METHOD_NAME);
        }

        return l_web3GentradeTradingClendarContext;
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
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 4057BB0600BE
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".onReturn(Object l_context, Object l_returnValue)";
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
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 4057BB0600DD
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
