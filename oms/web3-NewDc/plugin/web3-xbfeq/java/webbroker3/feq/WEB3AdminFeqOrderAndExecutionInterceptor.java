head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAndExecutionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式約定入力サービスインタセプタ(WEB3AdminFeqOrderAndExecutionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 郭英 (中訊) 新規作成
                 : 2005/08/02 韋念瓊(中訊) レビュー   
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者外国株式約定入力サービスインタセプタ)<BR>
 * 管理者外国株式約定入力サービスインタセプタ<BR>
 *
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionInterceptor.class);
    
    /**
     * @@roseuid 42D0CED300AB
     */
    public WEB3AdminFeqOrderAndExecutionInterceptor() 
    {
     
    }
    
    /**
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR> 
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR> 
     * 　@－ログインセッションよりログインＩＤを取得，ログインＩＤに該当する管理者の<BR>
     * 情報より、<BR>
     * 　@　@以下の通り取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 管理者.証券会社コード<BR> 
     * 　@取引カレンダコンテキスト.部店コード = 管理者.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = null<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”10：外国株式”<BR> 
     * 　@取引カレンダコンテキスト.商品コード = 0：DEFAULT<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”04：外国株”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<BR> 
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 取引時間コンテキストをセットする。<BR> 
     * 　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR> 
     * <BR>
     * ２）　@日付ロールをセットする。<BR> 
     * 　@－取引時間管理.setTimestamp()をコールする。<BR> 
     * <BR>
     * ※以下、サービスメソッドが「管理者外国株式約定入力完了リクエスト」<BR>
     * の場合のみ実施。<BR>
     * ３）　@口座をロックする。<BR> 
     * <BR>
     * 　@３－１）　@口座ロック<BR>
     * 　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード,<BR>
     *  口座コード)をコールする。<BR> 
     *       リクエストデータ.約定入力情報より編集。<BR> 
     *       （リクエストデータ.約定入力情報.顧客コードから顧客を取得し、口座コードを取得する）<BR> 
     *        (証券会社コードは、管理者.証券会社コードを使用)<BR> 
     * <BR>
     * 　@３－２）　@約定経路区分をセットする。<BR>
     * 　@　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて、<BR>
     * 約定経路区分.9：約定入力をセットする。<BR> 
     * 　@　@　@設定キー： 外国株式約定経路区分<BR>
     * <BR>
     * 　@(*) 約定経路区分<BR>
     * 　@0：出来通知（Default）　@1：出来入力　@2：約定結果一括入力　@9：約定入力<BR>
     *<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * 
     * @@param l_serviceParams - (サービスの引数)<BR>
     * サービスの引数配列<BR>
     * @@return Object
     * @@roseuid 42B6944200AB
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメーターが未指定(null)です。");
        }

        try
        {
            //管理者
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            if (l_administrator == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //証券会社コード
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            //部店コード
            String l_strBranchCode = l_administrator.getBranchCode();

            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
           
            //１）　@取引カレンダコンテキストに内容をセットする。        
            //取引カレンダコンテキスト.証券会社コード = 管理者.証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
    
            //取引カレンダコンテキスト.部店コード = 管理者.部店コード
            l_context.setBranchCode(l_strBranchCode);
    
            //取引カレンダコンテキスト.市場コード = null”
            l_context.setMarketCode(null);
    
            //取引カレンダコンテキスト.受付時間区分 = ”10：外国株式”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
    
            //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            //取引カレンダコンテキスト.注文受付商品 = ”04：外国株”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
    
            //取引カレンダコンテキスト.注文受付トランザクション = null
            l_context.setOrderAcceptTransaction(null);
    
            // 取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //取引時間管理
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
 
            //※以下、サービスメソッドが「管理者外国株式約定入力完了リクエスト」の場合のみ実施。       
            if (l_serviceParams[0] instanceof WEB3AdminFeqOrderAndExecutionCompleteRequest)
            {
                //３）　@口座をロックする。 
                WEB3AdminFeqOrderAndExecutionCompleteRequest l_request = 
                    (WEB3AdminFeqOrderAndExecutionCompleteRequest)l_serviceParams[0];
                    
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    log.debug("FinAppが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "FinAppが存在しない。");
                }
                
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);                
                if (l_tradingModule == null)
                {
                    log.debug("TradingModuleが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "TradingModuleが存在しない。");
                }
                
                WEB3FeqOrderManager l_orderMgr = 
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
                if (l_orderMgr == null)
                {
                    log.debug("外国株式注文マネージャが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "外国株式注文マネージャが存在しない。");
                }                
                
                if (l_request.orderAndExecutionUnit == null)
                {
                    log.debug("約定入力情報が存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "約定入力情報が存在しない。");
                }

                //３－１）　@口座ロック
                //拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。
                WEB3GentradeAccountManager l_accountMgr = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accountMgr == null)
                {
                    log.debug("拡張アカウントマネージャが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "拡張アカウントマネージャが存在しない。");
                }
                
                String l_strAccountCodeForLock = null;
                String l_strBranchCodeForLock = null;
                String l_strInstitutionCodeForLock = null;

                //リクエストデータ.約定入力情報.顧客コードから顧客を取得し、口座コードを取得する
                String l_6bytesAccCode = l_request.orderAndExecutionUnit.accountCode;
                MainAccount l_MainAcc = 
                    l_accountMgr.getMainAccount(
                        l_strInstitutionCode,
                        l_request.orderAndExecutionUnit.branchCode,
                        l_6bytesAccCode);
                    
                if (l_MainAcc == null)
                {
                    log.debug("顧客が存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "顧客が存在しない。");
                }
                    
                //顧客から顧客コードを取得する
                l_strAccountCodeForLock = l_MainAcc.getAccountCode();
                l_strBranchCodeForLock = l_request.orderAndExecutionUnit.branchCode;
                    
                //証券会社コードは管理者.証券会社コードを使用
                l_strInstitutionCodeForLock = l_strInstitutionCode;
                
                l_accountMgr.lockAccount(
                    l_strInstitutionCodeForLock,
                    l_strBranchCodeForLock,
                    l_strAccountCodeForLock);//WEB3BaseException
                 
                //３－２）　@約定経路区分をセットする。 
                //  －ThreadLocalSystemAttributesRegistry.setAttribute( )にて、約定経路区分.9：約定入力をセットする。  
                //    設定キー： 外国株式約定経路区分
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV, 
                    WEB3FeqOrderExecRouteDivDef.ORDER_AND_EXEC_INPUT);
            }
            
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
     * サービスメソッド終了時にコールされる。<BR> 
     * 取引カレンダコンテキストクリア処理。<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR> 
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR> 
     * 取引時間管理.OFFSET_TAG<BR> 
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR> 
     * 外国株式約定経路区分<BR>
     * @@param l_context - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_returnValue - (サービスメソッド返却値)<BR>
     * サービスメソッド返却値<BR>
     * @@roseuid 42B6944200AE
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

        //外国株式約定経路区分
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
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
     * 外国株式約定経路区分<BR>
     * @@param l_obj - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_throwable - (例外)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 42B6944200BB
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

        //外国株式約定経路区分
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
