head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設データ移管UnitServiceインタセプタ(WEB3AdminAccOpenDataTransferUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/26 趙林鵬 (中訊) 新規作成 仕様変更管理台帳モデルNo.190
Revision History : 2009/08/31 武波(中訊) モデル 198
*/

package webbroker3.accountopen;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.accountopen.define.WEB3AdminAccountOpenAccTransferDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設データ移管UnitServiceインタセプタ)<BR>
 * 管理者口座開設データ移管UnitServiceインタセプタ<BR>
 *   
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminAccOpenDataTransferUnitServiceInterceptor implements Interceptor {

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferUnitServiceInterceptor.class);

    /**
     * @@roseuid 41B5AB7E0148
     */
    public WEB3AdminAccOpenDataTransferUnitServiceInterceptor()
    {

    }

    /**
     * 管理者口座開設データ移管Unitサービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR> 
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR> 
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * 　@　@引数.arg1[0]から口座開設見込客の証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * 　@　@引数.arg1[0]から口座開設見込客の部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”22：口座開設”<BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”<BR> 
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR> 
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR> 
　@   * －取引時間管理.setTimestamp()をコールする。<BR> 
     * <BR>
     * ３）　@ThreadLocalSystemAttributesRegistry.setAttribute( )にて口座開設データ移管の環境変数をセットする。<BR> 
     * [引数]<BR>
     * 　@arg0：　@"web3.adminAccountOpenAccTransfer"<BR> 
     * 　@arg1：　@Boolean(true)<BR>
     * <BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 41871ABA0235
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //証券会社コード
            String l_strInstitutionCode = null;     
            
            // 部店コード
            String l_strBranchCode = null;   

            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();

            if (l_serviceParam != null && l_serviceParam.length > 0
                && l_serviceParam[0] instanceof WEB3AccOpenExpAccountOpen)
            {
                //取引カレンダコンテキスト.証券会社コード = 引数.arg1[0]から口座開設見込客の証券会社コード
                l_strInstitutionCode =
                    ((WEB3AccOpenExpAccountOpen)l_serviceParam[0]).getInstitutionCode();

                //取引カレンダコンテキスト.部店コード = 引数.arg1[0]から口座開設見込客の部店コード
                l_strBranchCode =
                    ((WEB3AccOpenExpAccountOpen)l_serviceParam[0]).getBranchCode();
            }
            else
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }

            l_context.setInstitutionCode(l_strInstitutionCode);

            l_context.setBranchCode(l_strBranchCode);
    
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            // 取引カレンダコンテキスト.受付時間区分 = ”22：口座開設”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.ACCOUNT_OPEN);
            
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”  
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            //取引カレンダコンテキスト.注文受付商品 = ”22:　@顧客サービス”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
    
            //取引カレンダコンテキスト.注文受付トランザクション = ”07:照会”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            
            // 取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //取引時間管理
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

            //サービスの引数[0]の型が「管理者口座開設データ移管完了リクエスト」の場合のみ、
            //口座開設データ移管の環境変数をセットする。
            //[引数]
            //　@arg0：　@"web3.adminAccountOpenAccTransfer"
            //　@arg1：　@Boolean(true)

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3AdminAccountOpenAccTransferDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER,
                new Boolean(true));

            log.exiting(STR_METHOD_NAME);
            return l_context; 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
    }

    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CAL_CONTEXT_PATH<BR>
     * 　@"web3.adminAccountOpenAccTransfer"<BR>
     * <BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 41871ABA0238
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        //arg0：　@"web3.adminAccountOpenAccTransfer"
        //arg1：　@null
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3AdminAccountOpenAccTransferDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER,
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
     * 　@取引カレンダコンテキスト.TRADING_CAL_CONTEXT_PATH<BR>
     * 　@"web3.adminAccountOpenAccTransfer"<BR>
     * <BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 41871ABA0245
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        //口座開設データ移管の環境変数をセットする。
        //arg0：　@"web3.adminAccountOpenAccTransfer"
        //arg1：　@null
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3AdminAccountOpenAccTransferDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER,
            null);

        log.exiting(STR_METHOD_NAME);   
    }
}
@
