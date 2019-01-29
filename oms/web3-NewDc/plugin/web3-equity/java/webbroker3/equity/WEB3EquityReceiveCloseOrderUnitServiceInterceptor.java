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
filename	WEB3EquityReceiveCloseOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式失効通知一件サービスインタセプタ(WEB3EquityReceiveCloseOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 鄒政 (中訊) 新規作成
Revesion History : 2004/12/15 水落 (SRA) 残案件対応のため修正
Revesion History : 2005/01/05 岡村 (SRA) JavaDoc修正
Revesion History : 2006/02/20 大澤喜宗@ (SRA) 仕様変更管理台帳（モデル）№863の対応
*/
package webbroker3.equity;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

/**
 * （株式失効通知一件サービスインタセプタ）。<BR>
 * <BR>
 * 株式失効通知一件サービスImplに対して設定する。
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderUnitServiceInterceptor
    implements Interceptor
{
    /**
     * onCall()の呼出元メソッド名
     */
    private String methodName = null;
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityReceiveCloseOrderUnitServiceInterceptor.class);

    /**
     * @@roseuid 4142B67C02E2
     */
    public WEB3EquityReceiveCloseOrderUnitServiceInterceptor()
    {

    }

    /**
     * （onCall）。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる）<BR>
     * ※他サービス（出来通知）からコールされている場合は、何もしない。<BR>
     * <BR>
     * １）　@メソッド名=="exec失効"の場合は、何もせずにreturnする。<BR>
     * <BR>
     * 以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]を株式失効通知キューParamsオブジェクトにキャストする。<BR>
     * 　@－株式失効通知キューParamsオブジェクトの内容より取引時間コンテキストの<BR>
     * プロパティを<BR>
     * 　@　@　@セットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード ＝　@株式失効通知キューParamsの同項目<BR>
     *   取引カレンダコンテキスト.部店コード ＝　@株式失効通知キューParamsの同項目<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 ＝　@”01：株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード　@ ＝　@”0：DEFAULT”<BR> 
     * <BR>
     * 　@※市場コードは注文単位ごとにサービス内にて設定<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * 　@にて取引時間コンテキストをセットする。<BR>
     * 　@設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ４）　@当日場中の基準値取得のための属性をセットする。<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@「下り処理で必ず場中扱い」をセットする。<BR>
     * 　@　@設定キー： 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * 　@－取引時間管理.set発注日計算用基準日時( )にて<BR>
     * 　@　@発注日計算の基準日時に、注文単位.発注日＋HHMMSSとして"000000"をセットする。<BR>
     * 　@　@※注文単位オブジェクトは、メソッドの第二引数をcastして使用。<BR>
     * <BR>
     * ５）　@口座をロックする。<BR>
     * <BR>
     * 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
     * ※引数は株式失効通知キューParamsより編集。
     * @@param l_method サービスメソッドオブジェクト
     * @@param l_serviceParams サービスメソッド引数
     * @@return Object
     * @@roseuid 4105DA920318
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        
        //１）　@メソッド名=="exec失効"の場合は、何もせずにreturnする。
        methodName = l_method.getName();
        if ("execCloseOrder".equals(methodName))
        {
        	return null;
        }
        
        //以外、以下の処理を行う。
        //２）　@取引カレンダコンテキストに内容をセットする       
        
        //サービスの引数[0]を株式失効通知キューParamsオブジェクトにキャストする。
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams = 
            (HostEqtypeCloseOrderNotifyParams)l_serviceParams[0];
        
        String l_strAccountCode = l_hostEqtypeCloseOrderNotifyParams.getAccountCode();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        
        //取引カレンダコンテキスト.証券会社コード ＝ 株式失効通知キューParamsの同項目
        l_context.setInstitutionCode(l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode());
        
        //取引カレンダコンテキスト.部店コード ＝　@株式失効通知キューParamsの同項目
        l_context.setBranchCode(l_hostEqtypeCloseOrderNotifyParams.getBranchCode());
        
        //取引カレンダコンテキスト.受付時間区分  ＝　@”01：株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //取引カレンダコンテキスト.銘柄コード  ＝　@”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);        
        
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();        
        try
        {
            //３）　@受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();
            //４）　@当日場中の基準値取得のための属性をセットする。
			EqTypeOrderUnit l_eqTypeOrderUnit = 
            	(EqTypeOrderUnit)l_serviceParams[1];
            EqtypeOrderUnitRow l_orderUnitRow = 
            	(EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, 
				WEB3EquityBackServiceOnlineDef.ONLINE);
			Date l_bizDate = 
				WEB3DateUtility.getDate(
				l_orderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN, 
				WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
			WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
				new Timestamp(l_bizDate.getTime()));
            //５）　@口座をロックする。 
            l_accountManager.lockAccount(
                l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode(),
                l_hostEqtypeCloseOrderNotifyParams.getBranchCode(),
                l_strAccountCode);
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
     * ※他サービス（出来通知）からコールされている場合は、何もしない。<BR>
     * <BR>
     * メソッド名=="exec失効"の場合は、何もせずにreturnする。<BR> 
     * 以外、取引カレンダコンテキストクリア処理を行う。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * @@param l_context onCallリターン値
     * @@param l_returnValue サービスメソッドリターン値
     * @@roseuid 4105DA920337
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
		if ("execCloseOrder".equals(methodName))
		{
			return;
		}

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
			WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
			null);
		
		WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }

    /**
     * （onThrowable）。<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * ※他サービス（出来通知）からコールされている場合は、何もしない。<BR>
     * <BR>
     * メソッド名=="exec失効"の場合は、何もせずにreturnする。<BR>
     * 以外、取引カレンダコンテキストクリア処理を行う。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<BR>
     * 設定キー定数定義インタフェース.BACK_SERVICE_IN_ONLINE<BR>
     * @@param l_obj onCallリターン値
     * @@param l_throwable 例外オブジェクト
     * @@roseuid 4105DA920347
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
		if ("execCloseOrder".equals(methodName))
		{
			return;
		}

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
			WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
			null);
		
		WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
}
@
