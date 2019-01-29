head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券約定取消サービスインタセプタ(WEB3AdminBondExecuteCancelServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 徐大方(中訊) 新規作成         
Revesion History : 2007/7/25 武波 (中訊) 仕様変更・モデルNo.236
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;

import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者債券約定取消サービスインタセプタ)<BR>
 * 管理者約定取消サービスインタセプタクラス
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminBondExecuteCancelServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelServiceInterceptor.class);
    
    /**
     * @@roseuid 44E3362502BF
     */
    public WEB3AdminBondExecuteCancelServiceInterceptor() 
    {
     
    }
    
    /**
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）債券注文単位オブジェクトの取得 <BR>
     * 　@　@拡張債券注文マネージャ.get債券注文単位By注文ID()を<BR>
     * 　@　@コールして債券注文単位オブジェクトを取得する。 <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@注文ID： 引数.注文ID<BR>
     * 　@　@　@※事前に引数.注文IDをチェックする。<BR>
     * 　@　@　@注文ID==nullの場合、例外をスローする。<BR>   
     * 　@　@　@注文IDが数値でない場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BaseRuntimeException<BR>
     * 　@　@　@tag: 　@BUSINESS_ERROR_00600<BR>
     * 　@　@　@class: WEB3BaseRuntimeException<BR>
     * 　@　@　@tag: 　@BUSINESS_ERROR_01476<BR>
     * <BR>
     * ２）取引カレンダコンテキストに内容をセットする。<BR>
     *     －ログインセッションよりログインＩＤを取得，ログインＩＤに該当する管理者の情報より、 <BR>
     *     以下の通り取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     *　@　@取引カレンダコンテキスト.証券会社コード =  債券注文単位.部店IDに該当する部店オブジェクト.証券会社コード  <BR>
     *　@　@取引カレンダコンテキスト.部店コード =  債券注文単位.部店IDに該当する部店オブジェクト.部店コード <BR>
     *　@　@取引カレンダコンテキスト.市場コード = "0:DEFAULT"<BR>
     *　@　@取引カレンダコンテキスト.商品コード = "0:DEFAULT"  <BR>
     *　@　@取引カレンダコンテキスト.受付時間区分 =<BR>
     *　@　@　@　@　@　@債券注文単位.債券タイプ == "外国債券"の場合、"25：債券"をセットする。<BR>
     *　@　@　@　@　@　@債券注文単位.債券タイプ ≠ "外国債券"の場合、"36：国内債券"をセットする。<BR>
     *　@　@取引カレンダコンテキスト.注文受付商品 = ”28:債券” <BR>
     *　@　@取引カレンダコンテキスト.注文受付トランザクション = "00:DEFAULT" <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。 <BR>
     * 　@設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ３）　@受付日時、日付ロールをセットする。<BR>
     * 　@　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ※以下、サービスメソッドが「管理者約定取消完了リクエスト」の場合<BR>
     * のみ実施。 <BR>
     * ４）　@口座をロックする。 <BR>
     * 　@　@４－１）拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得 <BR>
     * 　@　@   [引数]<BR>
     * 　@ 　@  顧客ID：取得した債券注文単位.get顧客ID()<BR>
     * <BR>
     *     ４－２）拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     * 　@     [引数] <BR>
     * 　@　@  ・証券会社コード：顧客オブジェクトから取得<BR>
     * 　@  　@・部店コード：顧客オブジェクトから取得<BR>
     * 　@  　@・口座コード：顧客オブジェクトから取得<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数
     * @@return Object
     * @@roseuid 44B6FE2100B0
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("CollectionタイプのパラメータSizeは０できない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        String l_strId = null;
        if (l_serviceParam[0] instanceof WEB3AdminBondExecCancelConfirmRequest)
        {
            WEB3AdminBondExecCancelConfirmRequest l_confirmRequest = 
                (WEB3AdminBondExecCancelConfirmRequest) l_serviceParam[0];
            l_strId = l_confirmRequest.id;
        }
        if (l_serviceParam[0] instanceof WEB3AdminBondExecCancelCompleteRequest)
        {
            WEB3AdminBondExecCancelCompleteRequest l_completeRequest = 
                (WEB3AdminBondExecCancelCompleteRequest) l_serviceParam[0];
            l_strId = l_completeRequest.id;
        }
        
        //注文ID==nullの場合、例外をスローする。 
        if (l_strId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }
        
        //注文IDが数値でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(l_strId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが数字以外です。");
        }
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3BondOrderManager l_bondOrderManager =
                (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();  
            WEB3BondOrderUnit l_bondOrderUnit =
                l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_strId));
            
             //  ２）取引カレンダコンテキストに内容をセットする。
             //  －ログインセッションよりログインＩＤを取得，ログインＩＤに該当する管理者の情報より、 
             //     以下の通り取引時間コンテキストのプロパティをセットする。
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_bondOrderUnit.getBranchId()); 
            
            String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_branch.getBranchCode();
            
            //　@取引カレンダコンテキスト.証券会社コード =  債券注文単位.部店IDに該当する部店オブジェクト.証券会社コード  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //　@取引カレンダコンテキスト.部店コード =  債券注文単位.部店IDに該当する部店オブジェクト.部店コード  
            l_context.setBranchCode(l_strBranchCode);
            
            //  取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //  取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //　@取引カレンダコンテキスト.受付時間区分 =
            if (BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
            {
                //債券注文単位.債券タイプ == "外国債券"の場合、"25：債券"をセットする。
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            }
            else
            {
                //債券注文単位.債券タイプ ≠ "外国債券"の場合、"36：国内債券"をセットする。
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
            }

            //　@取引カレンダコンテキスト.注文受付商品 = "28:債券" 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //　@取引カレンダコンテキスト.注文受付トランザクション = "00:DEFAULT" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            
            //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。  
            //設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //３）　@受付日時、日付ロールをセットする。
            //－取引時間管理.setTimestamp()をコールする。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //※以下、サービスメソッドが「管理者約定取消完了リクエスト」の場合のみ実施。  
            if (l_serviceParam[0] instanceof WEB3AdminBondExecCancelCompleteRequest)
            {         
                // ４－１）拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得 
                //          [引数] 
                //          顧客ID：取得した債券注文単位.get顧客ID() 
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                
                
                // ４－２）拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。  
                //          [引数] 
                //          ・証券会社コード：顧客オブジェクトから取得 
                //          ・部店コード：顧客オブジェクトから取得 
                //          ・口座コード：顧客オブジェクトから取得 
                WEB3GentradeMainAccount l_mainAccount = 
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_bondOrderUnit.getAccountId());
                l_accountManager.lockAccount(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_mainAccount.getAccountCode());
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ ",  l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。",  l_ex);
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
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_returnValue - (onCallリターン値)<BR>
     * onCallリターン値
     * @@param l_context - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値
     * @@roseuid 44B6FE240350
     */
    public void onReturn(Object l_returnValue, Object l_context) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
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
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値
     * @@param l_throwable - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値
     * @@roseuid 44B6FE280043
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
