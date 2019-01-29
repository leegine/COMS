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
filename	WEB3RuitoBuyOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資買付注文サービスインタセプタクラス(WEB3RuitoBuyOrderServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito;
import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmRequest;
import webbroker3.util.WEB3LogUtility;
/**
 * 累積投資買付注文サービスインタセプタクラス<BR>
 */
public class WEB3RuitoBuyOrderServiceInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderServiceInterceptor.class);
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * <BR>
     * 　@－証券会社オブジェクトを取得する。<BR>
     * 　@　@アカウントマネージャ.getInstitution()をコールして、<BR>
     *     証券会社オブジェクトを取得する。<BR>
     * 　@　@[getInstitutionに渡すパラメタ]<BR>
     * 　@　@　@証券会社コード： OpLoginSecurityServiceより編集<BR>
     * <BR>
     * 　@－累投銘柄オブジェクトを取得する。<BR>
     * 　@　@拡張累投銘柄マネージャ.get累投銘柄()をコールして、<BR>
     *      拡張累投銘柄オブジェクトを取得する。<BR>
     * 　@　@[get累投銘柄に渡すパラメタ]<BR>
     * 　@　@　@証券会社：　@取得した証券会社オブジェクト<BR>
     * 　@　@　@銘柄コード： リクエストデータ.銘柄コード<BR>
     * <BR>
     * 　@－受付時間区分を取得する。<BR>
     * 　@　@(*) 拡張累投銘柄.get累投タイプ()の<BR>
     *          値がRuitoTypeEnum.中期国債ファ@ンドの場合、<BR>
     * 　@　@　@　@受付時間区分はWEB3TradingTimeTypeDef.中国F。<BR>
     * 　@　@(*) 拡張累投銘柄.get累投タイプ()の値が<BR>
     *          RuitoTypeEnum.MMFの場合、<BR>
     * 　@　@　@　@受付時間区分はWEB3TradingTimeTypeDef.MMF（設定）。<BR>
     * <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容<BR>
     *      より取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード = <BR>
     *                                             OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = <BR>
     *                                             OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = 取得した受付時間区分<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”08：累積投資”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *                                                    ”01：買付（新規建買）”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *      にて取引時間コンテキストをセットする。<BR>
     * 　@　@［setAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG<BR>
     * 　@　@　@設定値： 取引時間コンテキスト<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@リクエストデータの型が「累投買付注文完了リクエスト」の場合のみ、口座をロックする。<BR> 
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     * 　@　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 40582919015A
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug(" パラメータ値がNULL ");
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
        
        Object l_request = l_serviceParam[0];
        String l_strRuitoProductCode = null; //銘柄コード

        WEB3RuitoBuyConfirmRequest l_web3RuitoBuyConfirmRequest = null;
        WEB3RuitoBuyCompleteRequest l_web3RuitoBuyCompleteRequest = null;
        if(l_request instanceof WEB3RuitoBuyConfirmRequest)
        {
            l_web3RuitoBuyConfirmRequest = (WEB3RuitoBuyConfirmRequest) l_request;        
            l_strRuitoProductCode = l_web3RuitoBuyConfirmRequest.ruitoProductCode;   
        }
        else if(l_request instanceof WEB3RuitoBuyCompleteRequest)
        {
            l_web3RuitoBuyCompleteRequest = (WEB3RuitoBuyCompleteRequest) l_request;        
            l_strRuitoProductCode = l_web3RuitoBuyCompleteRequest.ruitoProductCode;   
        }
        else
        {
            log.debug(
                "__an NullPoint error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME));      
        }
        
        //クエストデータ.銘柄コード
        log.debug("クエストデータ.銘柄コード = " + l_strRuitoProductCode);
        //証券会社オブジェクトを取得する 
        long l_lngAccountId; // 口座コード
        String l_strInstitutionCode = null; // 証券会社コード
        Institution l_institution = null; //証券会社 
        String l_strBranchCode = null; // 部店コード
        
        //セキュリティサービスを取得
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        
        //AccountIdを取得
        l_lngAccountId = l_opLoginSec.getAccountId();
        log.debug("AccountId = " + l_lngAccountId);
        
        //FinAppサービスを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        //累投銘柄オブジェクトを取得する
        RuitoProduct l_ruitoProduct = null; //拡張累投銘柄
        WEB3RuitoProductManager l_ruitoProductManager = null; //拡張累投銘柄マネージャクラス
        RuitoTypeEnum l_ruitoTypeEnum = null; //累投タイプ
        String l_midmmfOrdHostOrdRcvDiv = null; //受付時間区分  
        
        l_ruitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();

        try
        {
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
            
            l_strBranchCode = l_acc.getBranch().getBranchCode();
            l_ruitoProduct =
                l_ruitoProductManager.getRuitoProduct(
                    l_institution,
                    l_strRuitoProductCode);
			log.debug("l_ruitoProduct = " + l_ruitoProduct );
            
            l_ruitoTypeEnum = l_ruitoProduct.getRuitoType();
            log.debug("l_ruitoTypeEnum = " + l_ruitoTypeEnum);
            if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum))
            {
                log.debug(
                    " entry if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum))");
                l_midmmfOrdHostOrdRcvDiv =
                    WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
            }
            else if (RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum))
            {
                log.debug(
                    " entry else if (RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum))");
                l_midmmfOrdHostOrdRcvDiv = WEB3TradingTimeTypeDef.MMF_SET;
            }
            //リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();

            // 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            log.debug(
                "l_context.getInstitutionCode() = "
                    + l_context.getInstitutionCode());
            
            // 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode);
            log.debug(
                "l_context.getBranchCode() = " + l_context.getBranchCode());
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR> 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            log.debug(
                "l_context.getMarketCode() = " + l_context.getMarketCode());
            
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            log.debug(
                "l_context.getProductCode() = " + l_context.getProductCode());
            
            //取引カレンダコンテキスト.受付時間区分 = 取得した受付時間区分 
            l_context.setTradingTimeType(l_midmmfOrdHostOrdRcvDiv);
            log.debug(
                "l_context.getTradingTimeType() = "
                    + l_context.getTradingTimeType());
            
            //取引カレンダコンテキスト.注文受付商品 = ”08：累積投資”                                                                                        
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            log.debug(
                "l_context.getOrderAcceptProduct() = "
                    + l_context.getOrderAcceptProduct());
            
            //取引カレンダコンテキスト.注文受付トランザクション = ”01：買付（新規建買）”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            log.debug(
                "l_context.getOrderAcceptTransaction() = "
                    + l_context.getOrderAcceptTransaction());
            
            //ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            //受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
            
          //３）リクエストデータの型が「累投買付注文完了リクエスト」の場合のみ、口座をロックする。 
          if(l_request instanceof WEB3RuitoBuyCompleteRequest)
          {
              WEB3GentradeAccountManager l_gentradeAccMgr = 
                  (WEB3GentradeAccountManager) l_finApp.getAccountManager();       
              l_gentradeAccMgr.lockAccount(              
                      l_strInstitutionCode,
                      l_strBranchCode,
                      l_acc.getAccountCode());
          }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
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
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 40582919015D
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
     * @@roseuid 405829190160
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
