head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : OP新規建入力サービスインタセプタ(WEB3OptionOpenContractInputServiceInterceptor.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/15 呉艶飛 (中訊) 新規作成
　@　@　@　@　@　@　@　@ 001: 2004/07/23  呉艶飛 (中訊) 新規修正
                 002: 2004/08/01  王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000102
Revesion History    : 2007/07/03 孟亜南 (中訊) 仕様変更モデルNo.771
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeAccountManager;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectRequest;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
/**
 * (OP新規建入力サービスインタセプタ)<BR>
 * 株価指数オプション新規建入力サービスインタセプタクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionOpenContractInputServiceInterceptor implements Interceptor
{

    /**
     * @@roseuid 40C07A750186
     */
    public WEB3OptionOpenContractInputServiceInterceptor()
    {

    }

    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOpenContractInputServiceInterceptor.class);

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
     * 
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”06：オプション”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     * 　@　@（リクエストデータ.建区分 == ”買建”の場合、”01：買付（新規建買）”<BR>
     * 　@　@　@リクエストデータ.建区分 == ”売建”の場合、”02：売付（新規建売）”）<BR>
     * <BR>
     *  (*1) 原資産銘柄コード<BR>
     * 　@−リクエストデータ.銘柄コードが入力されている場合は、<BR>
     * 　@　@　@銘柄コードに該当する先物OP銘柄オブジェクト,get原資産銘柄コード()。<BR>
     * 　@−以外、”0：DEFAULT”をセット。<BR>
     * <BR>
     * 　@−ThreadLocalSystemAttributesRegistry.setAttribute( 
     * )にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@−取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 407A51E402AF
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        Object l_request = l_serviceParam[0];
        String l_strProductCode = null; //原資産銘柄コード
        String l_orderAccept = null; //注文受付
        WEB3OptionsOpenMarginInputRequest l_inputRequest = null;
        if (l_request instanceof WEB3OptionsOpenMarginInputRequest)
        {
            l_inputRequest = (WEB3OptionsOpenMarginInputRequest) l_request;

            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_inputRequest.contractType))
            {

                l_orderAccept = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            if (WEB3IfoContractTypeDef.OPEN_SELL.equals(l_inputRequest.contractType))
            {
                l_orderAccept = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
        }
        else if (l_request instanceof WEB3OptionsProductSelectRequest)
        {
            WEB3OptionsProductSelectRequest l_selectRequest = (WEB3OptionsProductSelectRequest) l_request;
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_selectRequest.contractType))
            {
                l_orderAccept = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            if (WEB3IfoContractTypeDef.OPEN_SELL.equals(l_selectRequest.contractType))
            {
                l_orderAccept = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
        }
        else
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + "onCall");
        }

        try
        {
            long l_lngAccountId; //口座コード
            String l_institutionCode = null; // 証券会社コード
            String l_branchCode = null; //部店コード

            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);

            l_lngAccountId = l_opLoginSec.getAccountId();

            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager) GtlUtils.getAccountManager();

            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_institutionCode = l_acc.getInstitution().getInstitutionCode();                        
            log.debug("l_institutionCode =" + l_institutionCode);
            
            l_branchCode = l_acc.getBranch().getBranchCode();
            log.debug("l_branchCode =" + l_branchCode);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
                        
            if (l_inputRequest != null && l_inputRequest.opProductCode != null)
            {
                try
                {
                    WEB3IfoProductImpl l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_acc.getInstitution(),l_inputRequest.opProductCode);
                    l_strProductCode = l_ifoProductImpl.getUnderlyingProductCode();                    
                }
                catch (NotFoundException l_ex)
                {                    
                    log.error("データ不整合エラー。", new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex));
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME);
                }
            }
            else
            {
                l_strProductCode = WEB3ProductCodeDef.DEFAULT;
            }
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();            
            //注文単位.getProductId()にて銘柄ＩＤを取得する。
            //資産銘柄コードを取得
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_institutionCode);
            log.debug(">>>>>>>>>>l_context.setInstitutionCode(l_institutionCode);");
            
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_branchCode);
            
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            //取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード
            l_context.setProductCode(l_strProductCode);

            //取引カレンダコンテキスト.注文受付商品 = ”06：オプション”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);

            //取引カレンダコンテキスト.注文受付トランザクション 
            l_context.setOrderAcceptTransaction(l_orderAccept);
            
            //にて取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            //受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();
            

        }

        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex));
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, STR_METHOD_NAME);
        }

        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return null;
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
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 407A51E402B2
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = ".onReturn(Object,Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 407A51E402B5
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);

    }
}
@
