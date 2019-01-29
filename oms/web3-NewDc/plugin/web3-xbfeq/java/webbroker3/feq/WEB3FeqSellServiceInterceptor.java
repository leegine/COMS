head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式売付サービスインタセプタ(WEB3FeqSellServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.message.WEB3FeqSellCompleteRequest;
import webbroker3.feq.message.WEB3FeqSellConfirmRequest;
import webbroker3.feq.message.WEB3FeqSellInputRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式売付サービスインタセプタ)<BR>
 * 外国株式売付サービスインタセプタクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqSellServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqSellServiceInterceptor.class);
        
    /**
     * @@roseuid 42D0DEBC035B
     */
    public WEB3FeqSellServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）取引カレンダコンテキストに内容をセットする。<BR>
     *   －OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティを<BR>
     * セットする。<BR>
     * <BR>
     *   取引カレンダコンテキスト.証券会社コード =<BR> 
     * OpLoginSecurityServiceより編集<BR>
     *   取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     *   取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     *   取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     *   取引カレンダコンテキスト.受付時間区分 = ”外国株式”<BR>
     *   取引カレンダコンテキスト.注文受付商品 = ”外国株”<BR>
     *   取引カレンダコンテキスト.注文受付トランザクション = ”売付”<BR>
     * <BR>
     *   －ThreadLocalSystemAttributesRegistry.setAttribute( )に<BR>
     * て取引時間コンテキストをセットする。<BR>
     *      設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     *   (*)市場コードのセット方法@ <BR>
     * a) リクエストデータ.保有資産IDから保有資産オブジェクトを取得する。<BR> 
     * b) a)で取得した保有資産オブジェクトから銘柄オブジェクトを取得する。<BR> 
     * c) 取引カレンダコンテキスト.市場コード = 銘柄オブジェクト.get市場コード()の戻り値 <BR> 
     * ２）受付日時、日付ロールをセットする。<BR>
     *   －取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）口座をロックする。<BR>
     *    リクエストデータの型 == ”外国株式売付完了リクエスト” の場合、<BR>
     * 実施する。<BR>
     * <BR>
     *    拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)<BR>
     * をコールする。<BR>
     *    ※引数はOpLoginSecurityServiceより編集。<BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 428AFF91024A
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメーターが未指定(null)です。");
        }
        
        //１）取引カレンダコンテキストに内容をセットする。 
        //－OpLoginSecurityServiceの内容より取引時間コンテキストのプロパティをセットする。 
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();
        
        try
        {
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(l_lngAccountId);
            
            //証券会社コードを取得する
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //部店コードを取得する
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            //口座コード
            String l_accountCode = l_mainAccount.getAccountCode();
            
            //取引カレンダコンテキスト.set証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.set部店コード
            l_context.setBranchCode(l_strBranchCode);

            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”外国株式” 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            //取引カレンダコンテキスト.注文受付商品 = ”外国株” 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            //取引カレンダコンテキスト.注文受付トランザクション = ”売付”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //取引時間コンテキストをセットする 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //(*)市場コードのセット方法@ 
            // a) リクエストデータ.保有資産IDから保有資産オブジェクトを取得する。 
            // b) a)で取得した保有資産オブジェクトから銘柄オブジェクトを取得する。 
            // c) 取引カレンダコンテキスト.市場コード = 銘柄オブジェクト.get市場コード()の戻り値 
            
            String l_strMarketCode = null;
            //保有資産        
            Asset l_asset = null;
            long l_lngAsstId = 0;
            //外国株式ポジションマネージャ
            WEB3FeqPositionManager l_feqPositionManager =
                (WEB3FeqPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
            //外国株式プロダクトマネージャ        
            WEB3FeqProductManager l_feqProductManager =
                (WEB3FeqProductManager) GtlUtils.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
            if (l_serviceParams[0] instanceof WEB3FeqSellInputRequest)
            {
                WEB3FeqSellInputRequest l_request = 
                    (WEB3FeqSellInputRequest)l_serviceParams[0];
                if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
                {
                    l_lngAsstId = Long.parseLong(l_request.assetId);
                }
            }
            else if(l_serviceParams[0] instanceof WEB3FeqSellConfirmRequest)
            {
                WEB3FeqSellConfirmRequest l_request = 
                    (WEB3FeqSellConfirmRequest)l_serviceParams[0];
                if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
                {
                    l_lngAsstId = Long.parseLong(l_request.assetId);
                }
            }
            else if(l_serviceParams[0] instanceof WEB3FeqSellCompleteRequest)
            {
                WEB3FeqSellCompleteRequest l_request = 
                    (WEB3FeqSellCompleteRequest)l_serviceParams[0];
                if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
                {
                    l_lngAsstId = Long.parseLong(l_request.assetId);
                }
            }
            else
            {
                log.debug(
                    "リクエストデータが"
                        + " WEB3FeqSellInputRequest "
                        + "と WEB3FeqSellConfirmRequest "
                        + "と WEB3FeqSellCompleteRequest以外である, but is "
                        + this.getClass().getName());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            l_asset = l_feqPositionManager.getAsset(l_lngAsstId);
            //銘柄ID 
            long l_lngProductId = l_asset.getProduct().getProductId();
            WEB3FeqProduct l_feqProduct =
                (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
            l_strMarketCode = l_feqProduct.getMarketCode();
            
            //取引カレンダコンテキスト.市場コード = (*) 
            l_context.setMarketCode(l_strMarketCode);
        
            //２）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする  
            WEB3GentradeTradingTimeManagement.setTimestamp();   
            
            //３）口座をロックする。 
            //リクエストデータの型 == ”外国株式売付完了リクエスト” の場合、実施する。 
            //拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //※引数はOpLoginSecurityServiceより編集。 
            if (l_serviceParams[0] instanceof WEB3FeqSellCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
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
        
        log.exiting(STR_METHOD_NAME);
        
        return null;
    }
    
    /**
     * 取引カレンダコンテキストクリア処理。<BR>
     * サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 428AFF91026A
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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
     * 取引カレンダコンテキストクリア処理。<BR>
     * 例外発生時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 428AFF910279
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
