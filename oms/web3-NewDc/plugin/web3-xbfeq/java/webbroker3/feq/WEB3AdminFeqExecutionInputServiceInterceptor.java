head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者外国株式出来入力サービスインタセプタ(WEB3AdminFeqExecutionInputServiceInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/18 戴義波(中訊) 新規作成
                    2005/08/01 韋念瓊(中訊) レビュー
 */

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
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
import webbroker3.feq.message.WEB3AdminFeqExecutionCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式出来入力サービスインタセプタ) <BR>
 * 管理者外国株式出来入力サービスインタセプタ <BR>
 * 
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionInputServiceInterceptor implements Interceptor
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminFeqExecutionInputServiceInterceptor.class);

    /**
     * @@roseuid 42D0CED2030D
     */
    public WEB3AdminFeqExecutionInputServiceInterceptor()
    {

    }

    /**
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １） 取引カレンダコンテキストに内容をセットする。 <BR>
     * －ログインセッションよりログインＩＤを取得，ログインＩＤに該当する管理者の情報 より、 <BR>
     * 以下の通り取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 取引カレンダコンテキスト.証券会社コード = 管理者.証券会社コード <BR>
     * 取引カレンダコンテキスト.部店コード = 管理者.部店コード <BR>
     * 取引カレンダコンテキスト.市場コード = null <BR>
     * 取引カレンダコンテキスト.受付時間区分 = ”10：外国株式” <BR>
     * 取引カレンダコンテキスト.商品コード = 0：DEFAULT <BR>
     * 取引カレンダコンテキスト.注文受付商品 = ”04：外国株” <BR>
     * 取引カレンダコンテキスト.注文受付トランザクション = null <BR>
     * <BR>
     * －ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２） 日付ロールをセットする。 <BR>
     * －取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ※以下、サービスメソッドが「管理者外国株式出来入力完了リクエスト」 <BR>
     * の場合のみ実施。 <BR>
     * ３） 口座をロックする。 <BR>
     * ３－１） サービスの引数[0]（：注文ＩＤ）にて、該当する注文オブジェクトを <BR>
     * 取得する。（外国株式注文マネージャ.getOrder()を使用） <BR>
     * <BR>
     * ３－２） 口座ロック <BR>
     * 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード) <BR>
     * をコールする。 <BR>※ 引数は注文単位より編集。 <BR>
     * <BR>
     * ３－３） 約定経路区分をセットする。 <BR>
     * －ThreadLocalSystemAttributesRegistry.setAttribute( )にて、約定経路区分. <BR>
     * 1：出来入力をセットする。 <BR>
     * 設定キー： 外国株式約定経路区分 <BR>
     * <BR>
     * (*) 約定経路区分 <BR>
     * 0：出来通知（Default） 1：出来入力 2：約定結果一括入力 9：約定入力 <BR>
     * 
     * @@param l_method -
     *            (サービスメソッド) <BR>
     *            サービスメソッドオブジェクト <BR>
     * @@param l_serviceParams -
     *            (サービスの引数) <BR>
     *            サービスの引数配列 <BR>
     * @@return Object
     * @@roseuid 428C4267005C
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //管理者
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            if (l_administrator == null)
            {
                log.debug("管理者が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者が存在しない。");
            }

            //証券会社コード
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            // 部店コード
            String l_strBranchCode = l_administrator.getBranchCode();

            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //１） 取引カレンダコンテキストに内容をセットする。
            //取引カレンダコンテキスト.証券会社コード = 管理者.証券会社コード 
            l_context.setInstitutionCode(l_strInstitutionCode);

            //取引カレンダコンテキスト.部店コード = 管理者.部店コード 
            l_context.setBranchCode(l_strBranchCode);

            //取引カレンダコンテキスト.市場コード = null
            l_context.setMarketCode(null);

            //取引カレンダコンテキスト.受付時間区分 = ”10：外国株式” 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);

            //取引カレンダコンテキスト.商品コード = 0：DEFAULT
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //取引カレンダコンテキスト.注文受付商品 = ”04：外国株”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);

            //取引カレンダコンテキスト.注文受付トランザクション = null 
            l_context.setOrderAcceptTransaction(null);

            //ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //取引時間コンテキストをセットする
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //２）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //以下、サービスメソッドが「管理者外国株式出来入力完了リクエスト」
            if (l_serviceParams != null && l_serviceParams[0] instanceof WEB3AdminFeqExecutionCompleteRequest)
            {
                //３） 口座をロックする。 
                // 　@３－１） サービスの引数[0]（：注文ＩＤ）にて、該当する注文オブジェクトを
                // 取得する。（外国株式注文マネージャ.getOrder()を使用）
                WEB3AdminFeqExecutionCompleteRequest l_request = 
                    (WEB3AdminFeqExecutionCompleteRequest)l_serviceParams[0];
                
                long l_lngOrderId = 0;     
                if (WEB3StringTypeUtility.isNumber(l_request.orderId))
                {
                    l_lngOrderId = Long.parseLong(l_request.orderId); 
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + STR_METHOD_NAME, 
                        "パラメータタイプ不正。");
                }
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    String l_strMessage = "FinAppが存在しない。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }

                TradingModule l_tradingModule = 
                    l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
                if (l_tradingModule == null)
                {
                    String l_strMessage = "TradingModuleが存在しない。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
                WEB3FeqOrderManager l_orderManager = 
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
                if (l_orderManager == null)
                {
                    String l_strMessage = "外国株式注文マネージャが存在しない。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
            
                //get注文単位オブジェクト
                OrderUnit l_orderUnit = 
                    l_orderManager.getOrderUnitByOrderId(l_lngOrderId);//NotFoundException
                if (l_orderUnit == null)
                {
                    String l_strMessage = "注文単位オブジェクトが存在しない。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
    
                // ３－２） 口座ロック
                // 拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード,
                // 口座コード)
                // をコールする。 
                // 　@　@※ 引数は注文単位より編集。 
                long l_lngBranchId = l_orderUnit.getBranchId();
                long l_lngAccountId = l_orderUnit.getAccountId();
                WEB3GentradeAccountManager l_accountManager = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accountManager == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "拡張アカウントマネージャが存在しない。");
                }
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);//NotFoundException
                Branch l_branch = l_accountManager.getBranch(l_lngBranchId);//NotFoundException
            
                l_accountManager.lockAccount(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_mainAccount.getAccountCode());//WEB3BaseException
    
                // ３－３） 約定経路区分をセットする。
                // －ThreadLocalSystemAttributesRegistry.setAttribute(
                // )にて、約定経路区分.
                // 1：出来入力をセットする。 
                // 設定キー： 外国株式約定経路区分
                // (*) 約定経路区分
                // 0：出来通知（Default） 1：出来入力 2：約定結果一括入力 9：約定入力
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
                    WEB3FeqOrderExecRouteDivDef.EXEC_INPUT);
            }
            log.exiting(STR_METHOD_NAME);
            
            return  l_context;
        } 
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
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
     * 外国株式約定経路区分 <BR>
     * 
     * @@param l_context -
     *            (onCall返却値) <BR>
     *            onCall返却値 <BR>
     * @@param l_returnValue -
     *            (サービスメソッド返却値) <BR>
     *            サービスメソッド返却値 <BR>
     * @@roseuid 428C42670136
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        //外国株式約定経路区分
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 外国株式約定経路区分 <BR>
     * 
     * @@param l_obj -
     *            (onCall返却値) <BR>
     *            onCall返却値 <BR>
     * @@param l_throwable -
     *            (例外) <BR>
     *            例外オブジェクト <BR>
     * @@roseuid 428C426701F2
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
            
        //外国株式約定経路区分
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV, null);

        log.exiting(STR_METHOD_NAME);
    }
}@
