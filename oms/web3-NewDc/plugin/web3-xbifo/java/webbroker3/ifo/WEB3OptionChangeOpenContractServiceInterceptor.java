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
filename	WEB3OptionChangeOpenContractServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建サービスインタセプタ(WEB3OptionChangeOpenContractServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 李頴淵 (中訊) 新規作成
Revesion History : 2004/07/26 王暁傑 (中訊) onCallを修正 対応バッグ IFO_UT-000016
Revesion History : 2004/07/26 王暁傑 (中訊) onCallを修正 対応バッグ IFO_UT-000019
Revesion History : 2007/01/29 何文敏 (中訊) 仕様変更　@モデルNO.590
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;

/**
 * (OP訂正新規建サービスインタセプタ)<BR>
 * 株価指数オプション訂正新規建サービスインタセプタクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeOpenContractServiceInterceptor.class);
    
    /**
     * @@roseuid 40C07B410242
     */
    public WEB3OptionChangeOpenContractServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@リクエストデータの型が「株価指数オプション訂正新規建完了リクエスト」の場合のみ、口座をロックする。<BR> 
     *　@−拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     *※引数はOpLoginSecurityServiceより編集。 <BR> 
     * ２）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@−サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@−リクエストデータの内容と、OpLoginSecurityServiceの内容よ<BR>
     * り取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード =<BR>
     *  OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = <BR>
     * ”12：株価指数先物OP（取消訂正）”<BR>
     * 　@取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”06：オプション”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”05：訂正”<BR>
     * <BR>
     *  (*1) 原資産銘柄コードの取得方法@<BR>
     * 　@リクエストデータ.ＩＤに該当する注文オブジェクトを生成する。<BR>
     * 　@注文.getOrderUnits()[0].getProductId()にて銘柄ＩＤを取得する。<BR>
     * 　@銘柄ＩＤに該当する先物OP銘柄オブジェクトを生成する。<BR>
     * 　@生成した先物OP銘柄.get原資産銘柄コード()。<BR>
     * <BR>
     * 　@−ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@−取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ４）　@取引所が休憩時間帯の場合(*2)のみ、キューテーブルの共有ロックを行う。<BR>
     * <BR>
     * 　@　@先物OP発注サービス.lock先物OP注文取引キュー(訂正対象注文単位(*3))をコールする。<BR>
     * <BR>
     * 　@　@(*2)取引所が休憩時間帯の場合<BR>
     * 　@　@　@　@取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。<BR>
     * <BR>
     * 　@　@(*3)訂正対象注文単位<BR>
     * 　@　@　@　@　@リクエスト.IDに該当する注文IDを持つ注文単位オブジェクト<BR>
     * 　@　@　@　@　@※複数存在する場合は、getOrderUnits()の最初の要素を使用する。<BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 4056BF8B023F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);
        
        //１）　@取引カレンダコンテキストに内容をセットする。
        //サービスの引数[0]をリクエストデータオブジェクトにキャストする。
        String l_strRequestID = null;
        Object l_completeRequest = l_serviceParam[0];
        if (l_serviceParam[0] instanceof WEB3OptionsOpenMarginChangeConfirmRequest)
        {
            WEB3OptionsOpenMarginChangeConfirmRequest l_request = (WEB3OptionsOpenMarginChangeConfirmRequest)l_serviceParam[0];
            l_strRequestID = l_request.id;
        }
        else if (l_serviceParam[0] instanceof WEB3OptionsOpenMarginChangeCompleteRequest)
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request = (WEB3OptionsOpenMarginChangeCompleteRequest)l_serviceParam[0];
            l_strRequestID = l_request.id;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }
              
        
        String l_strInstitutionCode = null; //証券会社コード
        String l_strBranchCode      = null; //部店コード
        String l_strProductCode = null; //原資産銘柄コード
        
        try
        {
            long l_lngAccountId = 0;    // 口座コード
            FinApp l_finApp = null;         //FinancialApplication
            AccountManager l_accMgr = null; 
            MainAccount l_mainAccount = null;
            OrderUnit[] l_orderUnit;
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
                   
            //MainAccountを取得
            l_lngAccountId = l_opLoginSec.getAccountId();
            l_finApp = (FinApp) Services.getService(FinApp.class);
            l_accMgr = l_finApp.getAccountManager(); 
                       
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            
            //証券会社コードを取得
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //部店コードを取得
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

            // １）　@リクエストデータの型が「株価指数オプション訂正新規建完了リクエスト」の場合のみ、口座をロックする。            
            if (l_completeRequest instanceof WEB3OptionsOpenMarginChangeCompleteRequest)
            {
            ((WEB3GentradeAccountManager)l_accMgr).lockAccount(l_strInstitutionCode,
                l_strBranchCode,l_mainAccount.getAccountCode());
            }

            WEB3OptionOrderManagerImpl l_orderManager = 
               (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_orderUnit = l_orderManager.getOrder(Long.parseLong(l_strRequestID)).getOrderUnits();

            //原資産銘柄コードの取得
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_orderUnit[0].getProduct().getProductId());
             
            l_strProductCode = l_ifoProduct.getUnderlyingProductCode();

            WEB3GentradeTradingClendarContext l_context =
                             new WEB3GentradeTradingClendarContext();

            // ２）　@取引カレンダコンテキストに内容をセットする。
            //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
            l_context.setBranchCode(l_strBranchCode); 
            //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 =   ”12：株価指数先物OP（取消訂正）”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL); 
            //取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード  
            l_context.setProductCode(l_strProductCode);       
            //取引カレンダコンテキスト.注文受付商品 = ”06：オプション”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);         
            //取引カレンダコンテキスト.注文受付トランザクション = ”05：訂正”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
        
            //取引時間コンテキストをセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
                
            // ３）　@受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

            // ４）　@取引所が休憩時間帯の場合(*2)のみ、キューテーブルの共有ロックを行う。
            // 取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。
            if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
            {
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit[0];
                // 先物OP発注サービス.lock先物OP注文取引キュー(訂正対象注文単位(*3))をコールする。
                WEB3IfoFrontOrderService l_ifoOrderService =
                    (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                l_ifoOrderService.lockHostFotypeOrderAll(l_ifoOrderUnit);
            }

            log.exiting(STR_METHOD_NAME);
            return l_context;    
        } 
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
                                  
        }
        catch (DataException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
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
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 4056BF8B025F
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".onReturn(Object,Object)";
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
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 4056BF8B026E
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".onThrowable(Object,Throwable)";
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
