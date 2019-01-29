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
filename	WEB3RuitoCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投取消サービスインタセプタ  
                   (WEB3RuitoCancelServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李志強 (中訊) 新規作成
                   2004/12/06 韋念瓊 (中訊) 残対応
*/

package webbroker3.xbruito;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmRequest;

/**
 * 累積投資取消サービスインタセプタ<BR>
 */
public class WEB3RuitoCancelServiceInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoCancelServiceInterceptor.class);

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「累投取消完了リクエスト」の場合のみ、口座をロックする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * <BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
     * 　@　@※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－累投注文単位オブジェクトを取得する。<BR>
     * 　@　@拡張累投注文マネージャ.getOrder()をコールして、<BR>
     *           累投注文オブジェクトを取得する。<BR>
     * 　@　@［getOrderに渡すパラメタ］<BR>
     * 　@　@　@　@注文ID： リクエストデータオブジェクト.id<BR>
     * <BR>
     * 　@－取得した累投注文オブジェクト.getOrderUnits()をコールして累投注文単位<BR>
     *            オブジェクトの配列を取得する。<BR>
     * <BR>
     * 　@－取得した累投注文単位オブジェクトの配列[0] <BR>
     *            .getDataSourceObject()をコールし、<BR>
     *            累投注文単位Paramsを取得する。<BR>
     * <BR>
     * 　@－注文受付トランザクションを取得する。<BR>
     * 　@　@(*)累投注文単位Params.getOrderType == OrderTypeEnum.RUITO_BUYの場合、<BR>
     *         注文受付トランザクションの値は”01：買付（新規建買）”<BR>
     * 　@　@(*)累投注文単位Params.getOrderType == OrderTypeEnum.RUITO_SELLの場合、<BR>
     *         注文受付トランザクションの値は”02：売付（新規建売）”<BR>
     * <BR>
     * 　@－受付時間区分を取得する。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値がRuitoTypeEnum.中期国債ファ@ンドの<BR>
     *        場合、受付時間区分の値はWEB3TradingTimeTypeDef.中国F。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値がRuitoTypeEnum.MMFで<BR>
     *        累投注文単位Params.getOrderType == OrderTypeEnum.RUITO_BUYの場合、の場合、<BR>
     * 　@　@　@　@ 受付時間区分の値はWEB3TradingTimeTypeDef.MMF（設定）。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値がRuitoTypeEnum.MMFで<BR>
     *        累投注文単位Params.getOrderType == OrderTypeEnum.RUITO_SELLの場合の場合、<BR>
     * 　@　@　@　@ 受付時間区分の値はWEB3TradingTimeTypeDef.MMF（設定解約）。<BR>
     * <BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     *          取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード =  <BR>
     *                 OpLoginSecurityServiceより編集 <BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = 取得した受付時間区分<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”08：累積投資”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = ”06：取消” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間<BR>
     *           コンテキストをセットする。<BR>
     * 　@　@ ［setAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG<BR>
     * 　@　@　@設定値： 取引時間コンテキスト<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト <BR>
     * @@param l_serviceParam - サービスメソッド引数 <BR>
     * @@return Object
     * @@roseuid 40581D3B0041
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
                    this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("パラメータSizeは０できない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータSizeは０できない");
        }

        //サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        Object l_request = l_serviceParam[0];
        String l_strId = null;
        
        if (l_request instanceof WEB3RuitoCancelConfirmRequest)
        {
			l_strId = ((WEB3RuitoCancelConfirmRequest)l_request).id;
        }
		else if (l_request instanceof WEB3RuitoCancelCompleteRequest)
		{
			l_strId = ((WEB3RuitoCancelCompleteRequest)l_request).id;
		} 
        else
        {
            log.debug(
                "__request_error__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME));            
			log.exiting(STR_METHOD_NAME);
			return null;
        }
        
        log.debug("l_strId = " + l_strId);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {  
            long l_lngAccountId; // 口座コード
            String l_strInstitutionCode = null; // 証券会社コード
            String l_strBranchCode = null; // 部店コード

            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                    OpLoginSecurityService.class);
            l_lngAccountId = l_opLoginSec.getAccountId();
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount;
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            log.debug("証券会社コード = " + l_strInstitutionCode);
            log.debug("部店コード = " + l_strBranchCode);
            
            //１）リクエストデータの型が「累投取消完了リクエスト」の場合のみ、口座をロックする。
            if(l_request instanceof WEB3RuitoCancelCompleteRequest)
            {
                String l_strAccountCode = l_mainAccount.getAccountCode();
            
                //口座をロックする。 
                WEB3GentradeAccountManager l_gentradeAccMgr = 
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();       
                l_gentradeAccMgr.lockAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
            }
            
            //２）取引カレンダコンテキストに内容をセットする。
            // 累投注文単位オブジェクトを取得する。
            WEB3RuitoOrderManager l_ruitoOrderManager =
                 (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                     ProductTypeEnum.RUITO).getOrderManager();
            
            OrderUnit[] l_orderUnits = 
                l_ruitoOrderManager.getOrderUnits(Long.parseLong(l_strId));

			//累投注文単位Paramsを取得する。 
			RuitoOrderUnitParams l_ruitoOrderUnitParams = 
				(RuitoOrderUnitParams) l_orderUnits[0].getDataSourceObject();

			//注文受付トランザクションを取得する
			OrderTypeEnum l_orderTypeEnum = 
			    l_ruitoOrderUnitParams.getOrderType();
			String l_orderAcceptTransaction = null; //注文受付トランザクション
			WEB3GentradeTradingClendarContext l_context =
				new WEB3GentradeTradingClendarContext();
			
			log.debug("l_orderTypeEnum = " + l_orderTypeEnum);				

			//受付時間区分を取得する
			String l_strTradingTimeType = null; //受付時間区分
			RuitoTypeEnum l_ruitoTypeEnum = l_ruitoOrderUnitParams.getRuitoType();
			
			log.debug("累投注文単位Params.get累投タイプ() = " + l_ruitoTypeEnum.intValue());		
			
            //(*) 累投注文単位Params.get累投タイプ()の値がRuitoTypeEnum.中期国債ファ@ンドの場合、 
			if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum))
			{
				l_strTradingTimeType = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
			}
            //(*) 累投注文単位Params.get累投タイプ()の値がRuitoTypeEnum.MMFで、 
			//　@累投注文単位Params.getOrderType == OrderTypeEnum.RUITO_BUYの場合、
			else if ((RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum)) && 
			    (OrderTypeEnum.RUITO_BUY.equals(l_ruitoOrderUnitParams.getOrderType())))
			{
				l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET;
			}
            //(*) 累投注文単位Params.get累投タイプ()の値がRuitoTypeEnum.MMFで、 
			//累投注文単位Params.getOrderType == OrderTypeEnum.RUITO_SELLの場合、
			else if ((RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum)) && 
			    (OrderTypeEnum.RUITO_SELL.equals(l_ruitoOrderUnitParams.getOrderType())))
			{
				l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET_CANCEL;
			}
			
			log.debug("l_strTradingTimeType = " + l_strTradingTimeType);		

            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = 取得した受付時間区分
            l_context.setTradingTimeType(l_strTradingTimeType);
            //取引カレンダコンテキスト.注文受付商品 = ”08：累積投資” 
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            //取引カレンダコンテキスト.注文受付トランザクション = ”06：取消”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

            //－ThreadLocalSystemAttributesRegistry.setAttribute( )
            //にて取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //３）　@受付日時、日付ロールをセットする。 
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
           
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__");
            log.error(
                "__an unexpected error__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex));
			log.exiting(STR_METHOD_NAME);
			return null;
        }

        catch (WEB3BaseException l_ex)
        {
            log.debug(
                "__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
            log.error(
                    "__an unexpected error__",
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            //DBへのアクセスに失敗しました
            this.getClass().getName() + "onCall()", l_ex));
			log.exiting(STR_METHOD_NAME);
			return null;
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
     * @@param l_context - onCallリターン値 <BR>
     * @@param l_returnValue - サービスメソッドリターン値 <BR>
     * @@roseuid 40581D3B0060
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
     * @@param l_obj - onCallリターン値 <BR>
     * @@param l_throwable - 例外オブジェクト <BR>
     * @@roseuid 40581D3B006F
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
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
}
@
