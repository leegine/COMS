head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SLEGatewayInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3BaseSleRepliesCallbackクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/09/20  李(FLJ) 新規作成
 */

package webbroker3.slegateway;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor; 
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.slebase.data.SleSendQRow;


/**
 * SLE送信サービスのインタセプタ
 */
public class WEB3SLEGatewayInterceptor implements Interceptor{
	
	/**
	  * ログ出力ユーティリティ。
	  */
	 private static WEB3LogUtility log =
			 WEB3LogUtility.getInstance(WEB3SLEGatewayInterceptor.class);

	 /**
	  * コンストラクタ
	  */
	 public WEB3SLEGatewayInterceptor() {}
	 
	 
	/**
	 * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
	 サービスメソッド開始時にコールされる。 
	 取引カレンダが利用するコンテキストを生成する。 
	（xTradeカーネルよりサービス実行前に呼び出される） 

	１）　@取引カレンダコンテキストに内容をセットする。 
　@		  取引カレンダコンテキスト.証券会社コード = 引数.send_qの行オブジェクト.get証券会社コード() 
　@		  取引カレンダコンテキスト.部店コード = 引数.send_qの行オブジェクト.get部店コード() 
　@		  取引カレンダコンテキスト.市場コード = 引数.send_qの行オブジェクト.get市場コード()
  		  取引カレンダコンテキスト.受付時間区分 = ”34：外国株式SLE送信” 
　@		  取引カレンダコンテキスト.商品コード = 0：DEFAULT 
　@		  取引カレンダコンテキスト.注文受付商品 = ”04：外国株” 
　@		  取引カレンダコンテキスト.注文受付トランザクション = null 
          ※受付時間区分はサービス内にてセットする。 
		  －ThreadLocalSystemAttributesRegistry.setAttribute( )にて 
　@　@　@		取引時間コンテキストをセットする。 
			設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
	２）　@受付日時、日付ロールをセットする。 
　@       －取引時間管理.setTimestamp()をコールする。

	 * @@param l_method - (サービスメソッド)<BR>
	 * サービスメソッドオブジェクト<BR>
	 * 
	 * @@param l_serviceParams - (サービスの引数)<BR>
	 * サービスの引数配列<BR>
	 * @@return Object
	 * @@roseuid 4295FFDD01AC
	 */
	public Object onCall(Method l_method, Object[] l_serviceParams) 
	{
		final String STR_METHOD_NAME = " onCall(Method, Object[])";
		log.entering(STR_METHOD_NAME);

		long l_lngExecId = 0;
		
		SleSendQRow l_sendqRow = (SleSendQRow) l_serviceParams[0];

		try
		{
	
			//証券会社コード
			String l_strInstitutionCode = l_sendqRow.getInstitutionCode();
			//部店コード
			String l_strBranchCode = l_sendqRow.getBranchCode();
			//市場コード　@
            String l_strMarketCode = l_sendqRow.getMarketCode();
            
			WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
                   
			//１）　@取引カレンダコンテキストに内容をセットする。        
			//取引カレンダコンテキスト.証券会社コード = 管理者.証券会社コード
			l_context.setInstitutionCode(l_strInstitutionCode);
            
			//取引カレンダコンテキスト.部店コード = 管理者.部店コード
			l_context.setBranchCode(l_strBranchCode);
            
			//取引カレンダコンテキスト.市場コード = null”
//			l_context.setMarketCode(null);
            l_context.setMarketCode(l_strMarketCode);
            
			//取引カレンダコンテキスト.受付時間区分 = ”34：外国株式SLE送信”??WEB3TradingTimeTypeDefクラスに未定義
//			l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            l_context.setTradingTimeType("34");
            
			//取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
			l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
			//取引カレンダコンテキスト.注文受付商品 = ”04：外国株”
			l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
				 
			l_context.setOrderAcceptTransaction(null);
            
        
			// 取引時間コンテキストをセットする
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
				l_context);
       		
            //取引時間管理
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

			log.exiting(STR_METHOD_NAME);
            
			return l_context;        
		}
		catch (WEB3BaseException l_ex)
		{
			log.error("予期しないシステムエラーが発生しました。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				l_ex.getErrorInfo(),
				this.getClass().getName() + STR_METHOD_NAME);                
		}
		
	}
    
	/**
	 * サービスメソッド終了時にコールされる。 <BR>
	 * 取引カレンダコンテキストクリア処理。 <BR>
	 * <BR>
	 * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
	 * <BR>
	 * 取引時間管理.TIMESTAMP_TAG <BR>
	 * 取引時間管理.OFFSET_TAG <BR>
	 * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
	 * @@param l_context - (onCall返却値)<BR>
	 * onCall返却値<BR>
	 * @@param l_returnValue - (サービスメソッド返却値)<BR>
	 * サービスメソッド返却値<BR>
	 * @@roseuid 4295FFDD01AF
	 */
	public void onReturn(Object l_context, Object l_returnValue) 
	{
		final String STR_METHOD_NAME = " onReturn(Object, Object)";
		log.entering(STR_METHOD_NAME);

		//取引時間管理.TIMESTAMP_TAG
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
			null);
        
		//取引時間管理.OFFSET_TAG
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3GentradeTradingTimeManagement.OFFSET_TAG,
			null);
            
		//取引時間管理.TRADING_CAL_CONTEXT_PATH
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
			null);

		log.exiting(STR_METHOD_NAME);
	}
    
	/**
	 * サービスメソッドが例外をスローした場合にコールされる。 <BR>
	 * 取引カレンダコンテキストクリア処理。 <BR>
	 * <BR>
	 * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
	 * <BR>
	 * 取引時間管理.TIMESTAMP_TAG <BR>
	 * 取引時間管理.OFFSET_TAG <BR>
	 * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
	 * @@param l_obj - (onCall返却値)<BR>
	 * onCall返却値<BR>
	 * @@param l_throwable - (例外)<BR>
	 * 例外オブジェクト<BR>
	 * @@roseuid 4295FFDD01BB
	 */
	public void onThrowable(Object l_obj, Throwable l_throwable) 
	{
		final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
		log.entering(STR_METHOD_NAME);

		//取引時間管理.TIMESTAMP_TAG
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
			null);
            
		//取引時間管理.OFFSET_TAG
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3GentradeTradingTimeManagement.OFFSET_TAG,
			null);

		//取引時間管理.TRADING_CAL_CONTEXT_PATH
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
			null);

		log.exiting(STR_METHOD_NAME);
     
	}
}
@
