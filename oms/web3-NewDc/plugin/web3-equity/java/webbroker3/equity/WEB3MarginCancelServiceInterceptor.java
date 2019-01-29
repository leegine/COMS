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
filename	WEB3MarginCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引取消サービスインタセプタ(WEB3MarginCancelServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 凌建平 (中訊) 新規作成
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
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
import webbroker3.equity.message.WEB3MarginCancelCompleteRequest;
import webbroker3.equity.message.WEB3MarginCancelConfirmRequest;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引取消サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引取消サービスインタセプタクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCancelServiceInterceptor implements Interceptor 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginCancelServiceInterceptor.class);
            
    /**
     * @@roseuid 4142B32C0351
     */
    public WEB3MarginCancelServiceInterceptor() 
    {
     
    }
    
    
    /**
     * (onCall)。<BR>
     * <BR>
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「信用取引注文取消完了リクエスト」の場合のみ、口座をロックする。<BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@　@　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = (*)<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = (*)<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”03：信用取引”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”06：取消”<BR>
     * <BR>
     * 　@(*)リクエストデータオブジェクト.IDに該当する注文単位オブジェクトより編集。<BR>
     * 　@　@　@getOrderUnits(リクエストデータ.ID)で取得した<BR>
     * 　@　@　@注文単位オブジェクトの0番目の要素の注文単位より、<BR>
     * 　@　@　@取引カレンダコンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@○市場コード<BR>
     * 　@　@　@　@注文単位.市場IDに該当する市場オブジェクト.市場コード<BR>
     * <BR>
     * 　@　@　@○受付時間区分<BR>
     * 　@　@　@　@注文単位.注文カテゴリ＝"現引・現渡"の場合：　@"現引・現渡"<BR>
     * 　@　@　@　@注文単位.注文カテゴリ≠"現引・現渡"の場合：　@"株式・信用"<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ４）　@取消対象注文が現引現渡以外(*2)、かつ　@取引所が休憩時間帯の場合(*3)のみ、<BR>
     * 　@　@　@キューテーブルの共有ロックを行う。<BR>
     * <BR>
     * 　@　@株式発注サービス.lock株式注文取引キュー(取消対象注文単位(*4))をコールする。<BR>
     * <BR>
     * 　@　@(*2)取消対象注文が現引現渡以外<BR>
     * 　@　@　@　@注文単位.注文カテゴリ≠"現引・現渡"の場合、現引現渡以外であると判定する。<BR>
     * <BR>
     * 　@　@(*3)取引所が休憩時間帯の場合<BR>
     * 　@　@　@　@取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。<BR>
     * <BR>
     * 　@　@(*4)取消対象注文単位<BR>
     * 　@　@　@　@　@リクエスト.IDに該当する注文IDを持つ注文単位オブジェクト<BR>
     * 　@　@　@　@　@※複数存在する場合は、getOrderUnits()の最初の要素を使用する。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 405808BF0300
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //--------------------
        // 変数・オブジェクトの初期化・設定
        //--------------------
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            
        Market l_market = null;
        WEB3GentradeMainAccount l_account = null;
        
        OrderUnit[] l_orderUnits = null;
        EqTypeOrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        
        String l_strMarketCode = null;
        String l_strTradingTimeType = null;
        String l_strOrderId = null;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;

        try
        {
            l_account = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(l_opLoginSec.getAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
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
            if (l_serviceParams[0] instanceof WEB3MarginCancelCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_account.getAccountCode());
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //--------------------
        // リクエストから注文IDを取得する
        //--------------------
        if (l_serviceParams[0] instanceof WEB3MarginCancelConfirmRequest)
        {
            WEB3MarginCancelConfirmRequest l_request = (WEB3MarginCancelConfirmRequest) l_serviceParams[0];
            l_strOrderId = l_request.id;
        }
        else if (l_serviceParams[0] instanceof WEB3MarginCancelCompleteRequest)
        {
            WEB3MarginCancelCompleteRequest l_request = (WEB3MarginCancelCompleteRequest) l_serviceParams[0];
            l_strOrderId = l_request.id;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." +STR_METHOD_NAME);
        }


        //--------------------
        // 注文単位を取得する
        //--------------------
        l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
        l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();


        //--------------------
        // 市場コードを取得する
        //--------------------
        try {
            l_market = l_finObjManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe){
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        l_strMarketCode = l_market.getMarketCode();


        //--------------------
        // 受付時間区分を取得する
        //--------------------
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
        {
            l_strTradingTimeType = WEB3TradingTimeTypeDef.SWAP;
        }
        else
        {
            l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
        }


        //--------------------
        // 取引カレンダコンテキストを設定
        //--------------------
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context.setInstitutionCode(l_strInstitutionCode);
        l_context.setBranchCode(l_strBranchCode);
        l_context.setMarketCode(l_strMarketCode);
        l_context.setTradingTimeType(l_strTradingTimeType);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);         


        try
        {
            
            //--------------------
            // 受付日時、日付ロールをセットする。
            //--------------------
            WEB3GentradeTradingTimeManagement.setTimestamp();

            if (!OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
            {
                if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                {
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    l_frontOrderService.lockHostEqtypeOrderAll(l_orderUnit);
                }
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
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
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
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
     * @@roseuid 405808BF031F
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
     * (onThrowable)<BR>
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
     * @@roseuid 405808BF032F
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
