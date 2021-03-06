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
filename	WEB3EquityProductInformationServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式銘柄情報表示サービスインタセプタ(WEB3EquityProductInformationServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 坂上(SRA) 新規作成
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3EquityProductInformationRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式銘柄情報表示サービスインタセプタ）。<BR>
 * <BR>
 * 株式銘柄情報表示サービスインタセプタクラス
 * @@version 1.0
 */
public class WEB3EquityProductInformationServiceInterceptor implements Interceptor
{

	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3EquityProductInformationServiceInterceptor.class);

	/**
	 * @@roseuid XXXXXXXXXX
	 */
	public WEB3EquityProductInformationServiceInterceptor()
	{

	}

	/**
	 * サービスメソッド開始時にコールされる。<BR>
	 * <BR>
	 * 取引カレンダが利用するコンテキストを生成する。<BR>
	 * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
	 * <BR>
	 * １）　@取引カレンダコンテキストに内容をセットする。<BR>
	 * 　@−サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
	 * 　@−リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
	 * 取引時間コンテキストのプロパティをセットする。<BR>
	 * <BR>
	 * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
	 * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
	 * 　@取引カレンダコンテキスト.市場コード = リクエスト.市場コードを編集。リクエストデータ.市場コード設定なし時はnullを編集<BR>
	 * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
	 * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
	 * 　@取引カレンダコンテキスト.注文受付商品 = リクエストデータ.注文受付商品を編集。<BR>
	 * 　@取引カレンダコンテキスト.注文受付トランザクション = ”照会”
	 * <BR>
	 * 　@−ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
	 * にて取引時間コンテキストをセットする。<BR>
	 * 設定キー："web3.tradingcalendarcontext"<BR>
	 * <BR>
	 * ２）　@受付日時、日付ロールをセットする。<BR>
	 * 　@−取引時間管理.setTimestamp()をコールする。<BR>
	 * @@param l_method - サービスメソッド<BR>
	 * サービスメソッドオブジェクト<BR>
	 * @@param l_serviceParam - サービスメソッド引数<Br>
	 * @@return Object
	 * @@roseuid XXXXXXXXXXX
	 */
	public Object onCall(Method l_method, Object[] l_serviceParam)
	{
		final String STR_METHOD_NAME = "onCall(Method, Object[])";
		log.entering(STR_METHOD_NAME);

		Object l_request = l_serviceParam[0];
		String l_strMarketCode = null; //市場コード
		String l_strOrderCommodityCode = null; //注文受付商品
		if (l_request instanceof WEB3EquityProductInformationRequest)
		{
			WEB3EquityProductInformationRequest l_inputRequest =
				(WEB3EquityProductInformationRequest) l_request;
			l_strMarketCode = l_inputRequest.marketCode;
			l_strOrderCommodityCode = l_inputRequest.orderCommodityCode;
		}
		else
		{
			log.error(
				STR_METHOD_NAME,
				new WEB3BaseException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80018,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"NOT 現物株式株式銘柄情報表示リクエスト"));
			return null;
		}
		long l_lngAccountId; // 口座コード
		String l_strInstitutionCode = null; // 証券会社コード
		String l_strBranchCode = null; // 部店コード
		WEB3GentradeTradingClendarContext l_context =
			new WEB3GentradeTradingClendarContext();

		//セキュリティサービスを取得
		try
		{
			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);

			//AccountIdを取得
			l_lngAccountId = l_opLoginSec.getAccountId();
			//FinAppサービスを取得
			FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
			AccountManager l_accMgr = l_finApp.getAccountManager();
			MainAccount l_acc;
			l_acc = l_accMgr.getMainAccount(l_lngAccountId);
			l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
			l_strBranchCode = l_acc.getBranch().getBranchCode();
		}
		catch (NotFoundException e)
		{
			log.error(STR_METHOD_NAME,e);
		}

		// 取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
		l_context.setInstitutionCode(l_strInstitutionCode);
		// 取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
		l_context.setBranchCode(l_strBranchCode);
		// 取引カレンダコンテキスト.市場コード = リクエストデータより編集
		l_context.setMarketCode(l_strMarketCode);
		//取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
		l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
		// 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
		l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
		// 取引カレンダコンテキスト.注文受付商品 = リクエストデータ.注文受付商品を編集
		l_context.setOrderAcceptProduct(l_strOrderCommodityCode);
		// 取引カレンダコンテキスト.注文受付トランザクション =”照会”
		l_context.setOrderAcceptTransaction(
			WEB3OrderAccTransactionDef.REFERENCE);
		// 取引時間コンテキストをセットする
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
			l_context);
		try
		{
			WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
		}
		catch (WEB3BaseException e)
		{
			log.error(STR_METHOD_NAME,e);
		}
		log.exiting(STR_METHOD_NAME);
		return l_context;

	}

	/**
	 * サービスメソッド終了時にコールされる。<BR>
	 * <BR>
	 * 取引カレンダコンテキストクリア処理。<BR>
	 * <BR>
	 * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
	 * <BR>
	 * "system_timestamp"<BR>
	 * "xblocks.gtl.attributes.bizdate.offset"<BR>
	 * "web3.tradingcalendarcontext"<BR>
	 * @@param l_context - onCallリターン値
	 * @@param l_returnValue - サービスメソッドリターン値
	 * @@roseuid 406030FF02B8
	 */
	public void onReturn(Object l_context, Object l_returnValue)
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
	 * サービスメソッドが例外をスローした場合にコールされる。<BR>
	 * <BR>
	 * 取引カレンダコンテキストクリア処理。<BR>
	 * <BR>
	 * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
	 * <BR>
	 * "system_timestamp"<BR>
	 * "xblocks.gtl.attributes.bizdate.offset"<BR>
	 * "web3.tradingcalendarcontext"<BR>
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
