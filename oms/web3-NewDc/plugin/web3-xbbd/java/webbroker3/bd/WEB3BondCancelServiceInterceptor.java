head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券取消サービスインタセプタ(WEB3BondCancelInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/22 趙林鵬 (中訊) 新規作成
Revesion History : 2007/07/25 謝旋 (中訊) 仕様変更・モデル221
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.bd.define.WEB3BondTypeListDef;
import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券取消サービスインタセプタ)<BR>
 * 債券取消サービスインタセプタ
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondCancelServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondCancelServiceInterceptor.class);   
    
    /**
     * @@roseuid 44FBFD3802CE
     */
    public WEB3BondCancelServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。 <BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@-リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストの <BR>
     * 　@プロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR> 
     * 　@取引カレンダコンテキスト.市場コード = "0：DEFAULT"<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 =<BR>
     * 　@　@　@　@　@リクエストデータ.注文IDに該当する債券注文単位オブジェクトを取得する。<BR>
     * 　@　@　@　@　@　@債券注文単位.債券タイプ == "外国債券"の場合、"25：債券"をセットする。<BR>
     * 　@　@　@　@　@　@債券注文単位.債券タイプ ≠ "外国債券"の場合、"36：国内債券"をセットする。<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = "28：債券" <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "06：取消"<BR>
     * <BR>
     * 　@-ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストを <BR>
     * 　@セットする。<BR>
     * 　@［setAttributeに渡すパラメタ］ <BR>
     * 　@　@設定キー: 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 　@　@設定値: 取引時間コンテキスト<BR>  
     * <BR>
     * ２）受付日時、日付ロールをセットする。 <BR>
     * 　@取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）リクエストデータの型が「債券取消完了リクエスト」の場合のみ、口座をロックする。<BR>  
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>  
     * 　@　@※引数はOpLoginSecurityServiceより編集。<BR> 
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 44B740450055
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        try
        {              
            //１）　@取引カレンダコンテキストに内容をセットする。
            //　@-リクエストデータの内容と、OpLoginSecurityServiceの内容より取引時間コンテキストの  
            //　@プロパティをセットする。  
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(l_lngAccountId);//NotFoundException

            //証券会社コードを取得する
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //部店コードを取得する
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();   
            
            //口座コードを取得する
            String l_strAccountCode = l_mainAccount.getAccountCode();
                
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
            
            //　@　@　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //　@　@　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集  
            l_context.setBranchCode(l_strBranchCode);
            
            //　@　@　@取引カレンダコンテキスト.市場コード = "0：DEFAULT"  
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //　@　@　@取引カレンダコンテキスト.受付時間区分 =
            //      リクエストデータ.注文IDに該当する債券注文単位オブジェクトを取得する。
            //       債券注文単位.債券タイプ == "外国債券"の場合、"25：債券"をセットする。
            //       債券注文単位.債券タイプ ≠ "外国債券"の場合、"36：国内債券"をセットする。
            WEB3BondOrderManager l_bondOrderManager =
                (WEB3BondOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getOrderManager();
            //債券取消確認リクエストの場合
            if (l_serviceParam[0] instanceof WEB3BondCancelConfirmRequest)
            {
                //リクエストデータ.注文IDに該当する債券注文単位オブジェクトを取得する
                WEB3BondCancelConfirmRequest l_confirmRequest =
                    (WEB3BondCancelConfirmRequest)l_serviceParam[0];
                WEB3BondOrderUnit l_bondOrderUnit =
                    l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_confirmRequest.orderId));
                if (l_bondOrderUnit.getBondType() == null)
                {
                    //債券注文単位.債券タイプ ≠ "外国債券"の場合、"36：国内債券"をセットする
                    l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
                }
                else
                {
                    String l_strBondType = String.valueOf(l_bondOrderUnit.getBondType().intValue());
                    if (WEB3BondTypeListDef.FOREIGN_BOND.equals(l_strBondType))
                    {
                        //債券注文単位.債券タイプ == "外国債券"の場合、"25：債券"をセットする。
                        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
                    }
                    else
                    {
                        //債券注文単位.債券タイプ ≠ "外国債券"の場合、"36：国内債券"をセットする
                        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
                    }
                }
            }
            else if (l_serviceParam[0] instanceof WEB3BondCancelCompleteRequest)
            {
                //債券取消完了リクエストの場合
                //リクエストデータ.注文IDに該当する債券注文単位オブジェクトを取得する
                WEB3BondCancelCompleteRequest l_confirmRequest =
                    (WEB3BondCancelCompleteRequest)l_serviceParam[0];
                WEB3BondOrderUnit l_bondOrderUnit =
                    l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_confirmRequest.orderId));
                if (l_bondOrderUnit.getBondType() == null)
                {
                    //債券注文単位.債券タイプ ≠ "外国債券"の場合、"36：国内債券"をセットする
                    l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
                }
                else
                {
                    String l_strBondType = String.valueOf(l_bondOrderUnit.getBondType().intValue());
                    if (WEB3BondTypeListDef.FOREIGN_BOND.equals(l_strBondType))
                    {
                        //債券注文単位.債券タイプ == "外国債券"の場合、"25：債券"をセットする。
                        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
                    }
                    else
                    {
                        //債券注文単位.債券タイプ ≠ "外国債券"の場合、"36：国内債券"をセットする
                        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
                    }
                }
            }

            //　@　@　@取引カレンダコンテキスト.注文受付商品 = "28：債券"  
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //　@　@　@取引カレンダコンテキスト.注文受付トランザクション = "06：取消" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
    
            //-ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストを  
            //セットする。    
            //設定キー: 取引時間管理.TRADING_CAL_CONTEXT_PATH   
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            //２）　@受付日時、日付ロールをセットする。  
            //　@取引時間管理.setTimestamp()をコールする。 
            WEB3GentradeTradingTimeManagement.setTimestamp(); //WEB3BaseException
            
            //３）　@リクエストデータの型が「債券取消完了リクエスト」の場合のみ、口座をロックする。  
            //－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。  
            //※引数はOpLoginSecurityServiceより編集。 
            if (l_serviceParam[0] instanceof WEB3BondCancelCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
            }   
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG <BR>
     * 　@取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 44B7405C01AD
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
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
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * １）ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG <BR>
     * 　@取引時間管理.OFFSET_TAG<BR> 
     * 　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 44B740660101
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
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
        
        log.exiting(STR_METHOD_NAME);  
    }

}
@
