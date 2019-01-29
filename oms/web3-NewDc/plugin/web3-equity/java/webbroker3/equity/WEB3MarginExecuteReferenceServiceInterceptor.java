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
filename	WEB3MarginExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引注文約定照会サービスインタセプタクラス(WEB3MarginExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/21 艾興 (中訊) 新規作成
                   2005/01/06 岡村和明 (SRA) JavaDoc修正
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListRequest;
import webbroker3.equity.message.WEB3MarginExecuteDetailsRequest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MarginOrderHistoryRequest;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引注文約定照会サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引注文約定照会サービスインタセプタクラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceServiceInterceptor
    implements Interceptor
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginExecuteReferenceServiceInterceptor.class);
    /**
     * @@roseuid 4142B32E02DB
     */
    public WEB3MarginExecuteReferenceServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@　@ ※引数.サービスメソッド＝"search注文約定照会"または<BR>
     * 　@　@　@　@　@　@　@　@    　@　@　@　@　@"search注文約定詳細"の場合のみ。<BR> 
     * <BR>
     * 　@－リクエストデータの内容と、<BR>
     *    OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     *    プロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *       OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = リクエスト.市場コード(*1)<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     * 　@(*1)リクエスト.市場コードにnullがセットされている場合は、<BR>
     *     　@nullをそのままセット。<BR>
     * 　@　@　@引数.サービスメソッド＝"search注文約定詳細"の場合は、<BR>
     * 　@　@　@　@○リクエスト.IDに該当する注文単位オブジェクトを取得する。<BR>
     * 　@　@　@　@○注文単位.市場IDに該当する市場.市場コードを取得しセットする。<BR>
     * <BR>
     * 　@※OpLoginSecurityServiceより取得した口座ID == 0の場合、<BR>
     * 　@　@リクエスト.注文IDに該当する注文.口座IDをLocalThreadにセットする。<BR>
     * 　@　@設定キー：　@ACCOUNT_ID<BR>
     *<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 405831EE018D
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {

        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        WEB3MarginExecuteReferenceRequest l_request = null;
        WEB3MarginExecuteDetailsRequest l_detailsRequest = null;
        if ((l_serviceParams == null) || (l_serviceParams[0] == null))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + "onCall");
        }

        if (l_serviceParams[0] instanceof WEB3MarginExecuteReferenceRequest)
        {
            l_request =
                    (WEB3MarginExecuteReferenceRequest)l_serviceParams[0];
        }
        else if(l_serviceParams[0] instanceof WEB3MarginExecuteDetailsRequest)
        {
            l_detailsRequest =
                    (WEB3MarginExecuteDetailsRequest)l_serviceParams[0];
        }
        else if(l_serviceParams[0] instanceof WEB3MarginOrderHistoryRequest)
        {
        }
        else if(l_serviceParams[0] instanceof WEB3MarginCloseMarginContractListRequest)
        {
        }
        else
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            String l_strInstitutionCode = null; //証券会社コード
            String l_strBranchCode = null; //部店コード

            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //MainAccountを取得
            long l_lngAccountId = l_opLoginSec.getAccountId();
            // 管理者セッションからコールされた場合
            if (l_lngAccountId == 0L)
            {
                long l_lngOrderId = 0L;
                // リクエスト == 信用取引注文約定詳細の場合
                if (l_serviceParams[0] instanceof WEB3MarginExecuteDetailsRequest)
                {
                    WEB3MarginExecuteDetailsRequest l_detailRequest =
                        (WEB3MarginExecuteDetailsRequest)l_serviceParams[0];
                    l_lngOrderId = Long.parseLong(l_detailRequest.id);
                }
                // リクエスト == 信用取引注文約定履歴照会の場合
                else if (l_serviceParams[0] instanceof WEB3MarginOrderHistoryRequest)
                {
                    WEB3MarginOrderHistoryRequest l_historyRequest =
                        (WEB3MarginOrderHistoryRequest)l_serviceParams[0];
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                // リクエスト == 信用取引決済建株一覧の場合
                else if (l_serviceParams[0] instanceof WEB3MarginCloseMarginContractListRequest)
                {
                    WEB3MarginCloseMarginContractListRequest l_historyRequest =
                        (WEB3MarginCloseMarginContractListRequest)l_serviceParams[0];
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                    
                EqTypeOrder l_eqtypeOrder =
                    (EqTypeOrder)l_orderManager.getOrder(l_lngOrderId);
                l_lngAccountId = l_eqtypeOrder.getAccountId();
                
                // ThreadLocalに取得した口座IDをセット。
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3MarginAttributeNameDef.ACCOUNT_ID, new Long(l_lngAccountId));
            }
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);

            //証券会社コードを取得
            Institution l_institution = l_mainAccount.getInstitution();
            l_strInstitutionCode = l_institution.getInstitutionCode();
            //部店コードを取得
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集        
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = リクエスト.市場コード(*1)
            //リクエスト.市場コードにnullがセットされている場合は、<BR>
            //nullをそのままセット
            if (l_serviceParams[0] instanceof WEB3MarginExecuteReferenceRequest)
            {
                l_context.setMarketCode(l_request.marketCode);
            }
            else if(l_serviceParams[0] instanceof WEB3MarginExecuteDetailsRequest)
            {
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
                String l_strMarketCode = null;
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_eqTypeOrderUnitRow.getMarketId()).getMarketCode();
                
                //取得した市場コードを取引カレンダコンテキスト.市場コードにセットする。
                l_context.setMarketCode(l_strMarketCode);
            }
            else
            {
                l_context.setMarketCode(null);
            }
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”01：株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            //取引カレンダコンテキスト.注文受付トランザクション =”07：照会”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);

            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //受付日時、日付ロールをセットする。   
            WEB3GentradeTradingTimeManagement.setTimestamp();

            log.exiting(STR_METHOD_NAME);
            return l_context;
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe);
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
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
     * <BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 405831EE019D
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
            
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.ACCOUNT_ID,
            null);
            
        log.exiting(STR_METHOD_NAME);
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
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 405831EE01A0
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
            
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.ACCOUNT_ID,
            null);
            
        log.exiting(STR_METHOD_NAME);

    }
}
@
