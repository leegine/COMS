head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文取消サービスインタセプタ(WEB3EquityCancelOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 李雲峰 (中訊) 新規作成
                   2004/12/20 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文取消サービスインタセプタ）。<BR>
 * <BR>
 * 株式注文取消サービスインタセプタ
 * @@version 1.0
 */
public class WEB3EquityCancelOrderServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityCancelOrderServiceInterceptor.class);

    /**
     * @@roseuid 40AC7CAD00E0
     */
    public WEB3EquityCancelOrderServiceInterceptor() 
    {
    }
    
    /**
     * （onCall）。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「現物株式注文取消完了リクエスト」の場合のみ、口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクト（*1）にキャストする。<BR>
     * 　@－OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。<BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = （*2）<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = （*2）<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = （*2）<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”取消”<BR>
     * <BR>
     * 　@（*1）「現物株式注文取消確認リクエスト」または、<BR>
     * 　@　@　@　@「現物株式注文取消完了リクエスト」<BR>
     * <BR>
     * 　@（*2）リクエストデータオブジェクト.IDに該当する注文単位オブジェクトより編集。<BR>
     * 　@　@　@getOrderUnits(リクエストデータ.ID)で取得した<BR>
     * 　@　@　@注文単位オブジェクトの0番目の要素の注文単位より、<BR>
     * 　@　@　@取引カレンダコンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@○市場コード<BR>
     * 　@　@　@　@注文単位.市場IDに該当する市場オブジェクト.市場コード<BR>
     * <BR>
     * 　@　@　@○受付時間区分<BR>
     * 　@　@　@　@注文単位.取引コード（SONAR）≠"立会外分売"の場合：　@"株式・信用"<BR>
     * 　@　@　@　@注文単位.取引コード（SONAR）＝"立会外分売"の場合：　@"立会外分売"<BR>
     * <BR>
     * 　@　@　@○注文受付商品<BR>
     * 　@　@　@　@注文単位.取引コード（SONAR）≠"立会外分売"の場合：　@"株式"<BR>
     * 　@　@　@　@注文単位.取引コード（SONAR）＝"立会外分売"の場合：　@"立会外分売"<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
     * 設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ４）　@取引所が休憩時間帯の場合(*3)のみ、キューテーブルの共有ロックを行う。<BR>
     * <BR>
     * 　@　@株式発注サービス.lock株式注文取引キュー(取消対象注文単位(*4))をコールする。<BR>
     * <BR>
     * 　@　@(*3)取引所が休憩時間帯の場合<BR>
     * 　@　@　@　@取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。<BR>
     * <BR>
     * 　@　@(*4)取消対象注文単位<BR>
     * 　@　@　@　@　@リクエスト.IDに該当する注文IDを持つ注文単位オブジェクト<BR>
     * 　@　@　@　@　@※複数存在する場合は、getOrderUnits()の最初の要素を使用する。<BR>
     * @@param l_method （メソッド）
     * @@param l_serviceParam （サービスの引数）<BR>
     * <BR>
     * サービスのメソッドに渡される引数。<BR>
     * 株式注文取消サービスの場合、現物株式注文取消確認リクエストオブジェクト、<BR>
     * または現物株式注文取消完了リクエストオブジェクト。
     * @@return Object
     * @@roseuid 4031E93F034B
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeMainAccount l_account = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        
        long l_lngAccountId = l_opLoginSec.getAccountId();
        try
        {
            l_account =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
        l_strBranchCode = l_account.getBranch().getBranchCode();
        try
        {
            if (l_serviceParams[0] instanceof WEB3EquityCancelCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_account.getAccountCode());
            }
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
        
        Object l_request = l_serviceParams[0];
        String l_strOrderId = null;

        // １）　@取引カレンダコンテキストに内容をセットする。
        // 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        if (l_request instanceof WEB3EquityCancelCompleteRequest)
        {
            // 現物株式注文取消完了リクエスト
            WEB3EquityCancelCompleteRequest l_completeRequest =
                (WEB3EquityCancelCompleteRequest)l_request;
            l_strOrderId = l_completeRequest.id;
        }
        else if (l_request instanceof WEB3EquityCancelConfirmRequest)
        {
            // 現物株式注文取消確認リクエスト
            WEB3EquityCancelConfirmRequest l_confirmRequest =
                (WEB3EquityCancelConfirmRequest)l_request;
            l_strOrderId = l_confirmRequest.id;
        }
        else
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "request !=現物株式注文取消完了リクエスト and request !=現物株式注文取消確認リクエスト"));
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        if(l_strOrderId == null){
            throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00600,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より
        // 　@　@　@取引時間コンテキストのプロパティをセットする。    
        String l_strMarketCode = null;
        EqTypeOrderUnit l_orderUnit = null;
        try
        {
            WEB3EquityOrderManager l_orderManager =
                        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                                   
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
            l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            long l_lngMarketId = l_orderUnitRow.getMarketId();
            
            WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_strMarketCode = l_market.getMarketCode();
                               
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            // 取引カレンダコンテキスト.銘柄コード = 0 ： DEFAULT
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            // 取引カレンダコンテキスト.市場コード = (*2)
            l_context.setMarketCode(l_strMarketCode);
            
            if(!l_orderUnitRow.getSonarTradedCode().equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET))
            {
                //注文単位.取引コード（SONAR）≠"立会外分売"の場合
                ////取引カレンダコンテキスト.受付時間区分 = (*2)
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
                ////取引カレンダコンテキスト.注文受付商品 = (*2)
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
                
            }
            else
            {
                //注文単位.取引コード（SONAR）＝"立会外分売"の場合
                ////取引カレンダコンテキスト.受付時間区分 = (*2)
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
                ////取引カレンダコンテキスト.注文受付商品 = (*2)
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
                
            }
            // 取引カレンダコンテキスト.注文受付トランザクション = ”取消”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);           
            // 取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);         

        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        try
        {
            // ２）　@受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

            if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
            {
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                l_frontOrderService.lockHostEqtypeOrderAll(l_orderUnit);
            }
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
     * （onReturn）。<BR>
     * <BR>
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_context （onCallリターン値）
     * @@param l_returnValue （サービスメソッドリターン値）
     * @@roseuid 4031E944009C<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue) {
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
     * （onThrowable）。<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj （onCallリターン値）
     * @@param l_throwable （例外オブジェクト）
     * @@roseuid 40AC7CAD0221<BR>
     */
    public void onThrowable(Object arg0, Throwable arg1) {
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
