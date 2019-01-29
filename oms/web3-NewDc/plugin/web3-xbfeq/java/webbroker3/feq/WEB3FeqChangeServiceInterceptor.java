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
filename	WEB3FeqChangeServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式訂正サービスインタセプタ(WEB3FeqChangeServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成 
                 : 2005/08/03 鄭海良(中訊) レビュー       
Revesion History : 2008/04/24 武波 (中訊) 仕様変更 モデル412
                 : 2008/07/18 大辻(SRA) モデルNo：454 対応
                 : 2008/07/23 黒釜(SRA) モデルNo：456 実装No:016,018 対応
                 : 2008/07/29 黒釜(SRA) モデルNo：458,461 実装No:021,023 対応
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.message.WEB3FeqChangeCompleteRequest;
import webbroker3.feq.message.WEB3FeqChangeConfirmRequest;
import webbroker3.feq.message.WEB3FeqChangeInputRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式訂正サービスインタセプタ)<BR>
 * 外国株式訂正サービスインタセプタクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqChangeServiceInterceptor implements Interceptor 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeServiceInterceptor.class);
    
    /**
     * @@roseuid 42D0D224038A
     */
    public WEB3FeqChangeServiceInterceptor() 
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
     *   取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     *   取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     *   取引カレンダコンテキスト.市場コード = (*) <BR>
     *   取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     *   取引カレンダコンテキスト.受付時間区分 = ”外国株式（訂正）”<BR>
     *   取引カレンダコンテキスト.注文受付商品 = ”外国株”<BR>
     *   取引カレンダコンテキスト.注文受付トランザクション = ”訂正”<BR>
     * <BR>
     *   －ThreadLocalSystemAttributesRegistry.setAttribute( )に<BR>
     * て取引時間コンテキストをセットする。<BR>
     *      設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * (*)市場コードのセット方法@ <BR>
     *     a) リクエストデータ.注文IDから注文単位オブジェクトを取得する。 <BR>
     *     b) 取引カレンダコンテキスト.市場コード = 注文単位.市場IDから取得した市場コード <BR>
     * ２）受付日時、日付ロールをセットする。<BR>
     *   －取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）口座をロックする。<BR>
     *    リクエストデータの型 == ”外国株式訂正完了リクエスト” の場合、実施する。<BR>
     * <BR>
     *    拡張アカウントマネージャ.lock口座<BR>
     * (証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     *    ※引数はOpLoginSecurityServiceより編集。<BR>
     * <BR>
     * ４）注文キューをロックする。<BR>
     * 　@リクエストデータの型==”外国株式訂正完了リクエスト”　@かつ<BR>
     * 　@注文単位.get市場.isシステム連動　@== true　@かつ<BR>
     * 　@Double.isNaN(注文単位.getConfirmedQuantity()) == true　@の場合のみ以下処理を実施する。<BR>
     * <BR>
     *　@４－１）外国株式注文キューTransactionCallbackを生成する<BR>
     *　@　@　@　@[コンストラクタに指定する引数]<BR>
     *　@　@　@　@　@注文単位オブジェクト<BR>
     * <BR>
     *　@４－２）トランザクションを実行する<BR>
     *　@　@　@　@　@（トランザクション属性： TX_JOIN_EXISTING）<BR>
     *<BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 4295F0950176
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("該当パラメータにNull値は設定できません。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);         
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
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();            
        
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
            
            //(*)市場コードのセット方法@ 
            //a) リクエストデータ.注文IDから注文単位オブジェクトを取得する。 
            //b) 取引カレンダコンテキスト.市場コード = 注文単位.市場IDから取得した市場コード 

            WEB3GentradeMarket l_market = null;   
            long l_lngOrderId = 0;
            
            if (l_serviceParams[0] instanceof WEB3FeqChangeInputRequest)
            {
                WEB3FeqChangeInputRequest l_inputRequest = 
                    (WEB3FeqChangeInputRequest)l_serviceParams[0];
                
                l_lngOrderId = Long.parseLong(l_inputRequest.orderId);
                
            }
            else if (l_serviceParams[0] instanceof WEB3FeqChangeConfirmRequest)
            {
                WEB3FeqChangeConfirmRequest l_confirmRequest = 
                    (WEB3FeqChangeConfirmRequest)l_serviceParams[0];
                
                l_lngOrderId = Long.parseLong(l_confirmRequest.orderId);
            }
            else if (l_serviceParams[0] instanceof WEB3FeqChangeCompleteRequest)
            {
                WEB3FeqChangeCompleteRequest l_completeRequest = 
                    (WEB3FeqChangeCompleteRequest)l_serviceParams[0];
                
                l_lngOrderId = Long.parseLong(l_completeRequest.orderId);                
            }            
            
            FeqOrderUnit l_feqOrderUnit = 
                l_feqOrderManager.getOrderUnitByOrderId(l_lngOrderId);

            FeqOrderUnitRow l_feqOrderUnitRow = 
                    (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
            
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_feqOrderUnitRow.getMarketId());            
            
            //取引カレンダコンテキスト.set証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCode);
            //取引カレンダコンテキスト.set部店コード
            l_context.setBranchCode(l_strBranchCode);
            //取引カレンダコンテキスト.市場コード = (*)  
            l_context.setMarketCode(l_market.getMarketCode());
            //取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”外国株式（訂正）” 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FEQ_CHANGE);
            //取引カレンダコンテキスト.注文受付商品 = ”外国株” 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            //取引カレンダコンテキスト.注文受付トランザクション = ”訂正”
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()にて
            //取引時間コンテキストをセットする 
            //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //２）受付日時、日付ロールをセットする
            //取引時間管理.setTimestamp()をコールする  
            WEB3GentradeTradingTimeManagement.setTimestamp();   
            
            //３）口座をロックする。 
            //リクエストデータの型 == ”外国株式訂正完了リクエスト” の場合、実施する。 
            //拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //※引数はOpLoginSecurityServiceより編集。 
            if (l_serviceParams[0] instanceof WEB3FeqChangeCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);

                //４）注文キューをロックする。
                //注文単位.get市場.isシステム連動　@== true　@かつ
                //Double.isNaN(注文単位.getConfirmedQuantity()) == true　@の場合のみ実施する。
                //４－１）外国株式注文キューTransactionCallbackを生成する
                if (l_market.isSystemInterLock() == true &&
                    Double.isNaN(l_feqOrderUnit.getConfirmedQuantity()) == true)
                {
                    WEB3FeqOrderQueueTransactionCallback l_transactionCallback =
                        new WEB3FeqOrderQueueTransactionCallback(l_feqOrderUnit);

                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                        l_processor.doTransaction(
                            QueryProcessor.TX_JOIN_EXISTING,
                            l_transactionCallback);
                }
            }
        }
        catch (DataException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
            log.error("error in setTimestamp or lockAccount", l_ex);
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
     * @@roseuid 4295F0950179
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
     * @@roseuid 4295F095017C
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
