head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定照会サービスインタセプタ(WEB3EquityExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 孟 東 (中訊) 新規作成
                   2004/12/24 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.equity.message.WEB3EquityExecuteDetailsRequest;
import webbroker3.equity.message.WEB3EquityExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityOrderHistoryRequest;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式注文約定照会サービスインタセプタ）。<BR>
 * <BR>
 * 現物株式注文約定照会サービスインタセプタ
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceServiceInterceptor
    implements Interceptor
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityExecuteReferenceServiceInterceptor.class);

    /**
     * @@roseuid 40A288C501B5
     */
    public WEB3EquityExecuteReferenceServiceInterceptor()
    {
        super();
    }

    /**
     * (onCall)<BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@　@　@※引数.サービスメソッド＝"search注文約定照会"または<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"search注文約定詳細"の場合のみ。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード =
     * 　@　@　@引数.サービスメソッド＝"search注文約定照会"の場合、
     *　@　@　@　@　@○リクエストデータより編集
     * 　@　@　@引数.サービスメソッド＝"search注文約定詳細"の場合、
     *　@　@　@　@　@○リクエスト.IDに該当する注文単位オブジェクトを取得する。
     *　@　@　@　@　@○注文単位.市場IDに該当する市場.市場コードを取得しセットする。
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”株式”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     * 　@※OpLoginSecurityServiceより取得した口座ID == 0の場合、<BR>
     * 　@　@リクエスト.注文IDに該当する注文.口座IDをLocalThreadにセットする。<BR>
     * 　@　@設定キー：　@ACCOUNT_ID<BR>
     *<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 取引時間コンテキストをセットする。<BR>
     * 設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 4060DF0D017B
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        Object l_request = l_serviceParam[0];

        // １）　@取引カレンダコンテキストに内容をセットする。
        // 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        // 　@　@　@※引数.サービスメソッド＝"search注文約定照会"の場合のみ
        String l_strMarketCode = null;
        if (l_request instanceof WEB3EquityExecuteReferenceRequest)
        {
            WEB3EquityExecuteReferenceRequest l_referenceRequest =
                (WEB3EquityExecuteReferenceRequest)l_request;
            l_strMarketCode = l_referenceRequest.marketCode;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        try
        {
            if (l_request instanceof WEB3EquityExecuteDetailsRequest)
            {
                WEB3EquityExecuteDetailsRequest l_detailsRequest =
                    (WEB3EquityExecuteDetailsRequest)l_request;
                //リクエスト.IDに該当する注文単位オブジェクトを取得する。
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                long l_lngOrderId = Long.parseLong(l_detailsRequest.id);
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
                EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
                EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
                    (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        
                //注文単位.市場IDに該当する市場.市場コードを取得する。
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_eqTypeOrderUnitRow.getMarketId()).getMarketCode();
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        // 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より
        // 　@　@取引時間コンテキストのプロパティをセットする。
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);

        try
        {
            long l_lngAccountId = l_opLoginSec.getAccountId();
            
            // 管理者セッションからコールされた場合
            if (l_lngAccountId == 0L)
            {
                long l_lngOrderId = 0L;
                // リクエスト == 株式注文約定詳細の場合
                if (l_request instanceof WEB3EquityExecuteDetailsRequest)
                {
                    WEB3EquityExecuteDetailsRequest l_detailRequest =
                        (WEB3EquityExecuteDetailsRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_detailRequest.id);
                }
                // リクエスト == 株式注文約定履歴照会の場合
                else if (l_request instanceof WEB3EquityOrderHistoryRequest)
                {
                    WEB3EquityOrderHistoryRequest l_historyRequest =
                        (WEB3EquityOrderHistoryRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                    
                EqTypeOrder l_eqtypeOrder =
                    l_eqtypeOrder = (EqTypeOrder)l_orderManager.getOrder(l_lngOrderId);
                l_lngAccountId = l_eqtypeOrder.getAccountId();
                
                // ThreadLocalに取得した口座IDをセット。
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3MarginAttributeNameDef.ACCOUNT_ID, new Long(l_lngAccountId));
            }

            MainAccount l_account = l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
            l_strBranchCode = l_account.getBranch().getBranchCode();
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode(l_strInstitutionCode);
        // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode(l_strBranchCode);
        // 取引カレンダコンテキスト.市場コード = 引数.サービスメソッド＝"search注文約定照会"の
        // 場合のみ、リクエストデータより編集
        l_context.setMarketCode(l_strMarketCode);
        // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        // 取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // 取引カレンダコンテキスト.注文受付商品 = ”株式”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        // 取引カレンダコンテキスト.注文受付トランザクション =”07：照会”
        l_context.setOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.REFERENCE);

        //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
        // 　@　@取引時間コンテキストをセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        // ２）　@受付日時、日付ロールをセットする。
        // 　@－取引時間管理.setTimestamp()をコールする。
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
        return null;
    }

    /**
     * (onReturn)<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_objOnCallReturnValue - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_objServiceMethodReturnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値
     * @@roseuid 4060DF0D019A
     */
    public void onReturn(
        Object l_objOnCallReturnValue,
        Object l_objServiceMethodReturnValue)
    {
        String STR_METHOD_NAME = "onReturn(Object, Object)";
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.ACCOUNT_ID,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable)<BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_objOnCallReturnValue - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 406411F702F4
     */
    public void onThrowable(
        Object l_objOnCallReturnValue,
        Throwable l_throwable)
    {
        String STR_METHOD_NAME = "onReturn(Object, Throwable)";
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.ACCOUNT_ID,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
