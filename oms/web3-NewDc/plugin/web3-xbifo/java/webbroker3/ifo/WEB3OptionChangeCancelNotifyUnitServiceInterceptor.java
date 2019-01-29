head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeCancelNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正取消通知１件サービスインタセプタクラス(WEB3OptionChangeCancelNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 盧法@旭 (中訊) 新規作成
Revesion History : 2004/7/21 盧法@旭 修正 notifyChange()  
Revesion History : 2007/04/24 孟亜南(中訊) 仕様変更モデルNo.637
*/
package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

/**
 * (株価指数オプション訂正取消通知１件サービスインタセプタ)<BR>
 * 株価指数オプション訂正取消通知１件サービスインタセプタクラス<BR>
 * Plugin時にOP訂正取消通知UnitServiceに対して設定する。<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyUnitServiceInterceptor implements Interceptor 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionChangeCancelNotifyUnitServiceInterceptor.class);
    /**
     * @@roseuid 40C0750800EA
     */
    public WEB3OptionChangeCancelNotifyUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－引数.サービスの引数[0]を注文単位オブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * 注文単位.証券会社部店ＩＤに該当する部店の証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * 注文単位.証券会社部店ＩＤに該当する部店の部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = <BR>
     * ”12：株価指数先物OP（取消訂正）”<BR>
     * 　@取引カレンダコンテキスト.商品コード = <BR>
     * （*1) 原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”06：オプション”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     * <BR>
     *  (*1) 原資産銘柄コードの取得方法@<BR>
     * 　@注文単位.getProductId()にて銘柄ＩＤを取得する。<BR>
     * 　@銘柄ＩＤに該当する先物OP銘柄オブジェクトを生成する。<BR>
     * 　@生成した先物OP銘柄.get原資産銘柄コード()。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * にて取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）日付ロールをセットする。 <BR>
     *　@－取引時間管理.setTimestamp()をコールする。 <BR>
     *<BR>
     * ３)　@受付日時として、注文単位.作成日時 を使用する為（訂正時の取引銘柄を取得するため）、<BR> 
     *　@　@取引時間管理.setTimestamp()にて設定された受付日時を上書きする。 <BR>
     *<BR>
     *　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて、注文単位.作成日時 をセットする。 <BR>
     *　@　@設定キー：　@取引時間管理.TIMESTAMP_TAG <BR>
     * 4）　@口座をロックする。<BR> 
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     * ※引数は注文単位より編集。<BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 4057D2F30179
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {  
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam) ";
        log.entering(STR_METHOD_NAME);
                     
        if(l_serviceParam == null)
        {
            log.error("onCall",new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "onCall" ));
            return null;            
        }
        if(l_serviceParam.length == 0)
        {         
            log.error("onCall", new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "onCall"));
            return null;    
        }
        
        OrderUnit l_orderUnit = (OrderUnit)l_serviceParam[0];
        
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        //証券会社部店ＩＤ
        long l_lngBranchId = l_orderUnit.getBranchId();
        log.debug("証券会社部店ＩＤ:" + l_lngBranchId);
        Branch l_branch = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_branch = l_accountManager.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe); 
        }
        //get product manager
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productMgr =
            (WEB3IfoProductManagerImpl) l_tradingMod.getProductManager();
        //部店証券会社コード
        String l_strBranchInstitutionCode = l_branch.getInstitution().getInstitutionCode();

        l_context.setInstitutionCode(l_strBranchInstitutionCode);
        
        //部店コード
        String l_strBranchCode = l_branch.getBranchCode();
        l_context.setBranchCode(l_strBranchCode);
        
        //市場コード "0：DEFAULT"
        l_context.setMarketCode
            (WEB3MarketCodeDef.DEFAULT);
        //受付時間区分 "12：株価指数先物OP（取消訂正)"
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL);
        
        //商品コード
        long l_lngProductId = l_orderUnit.getProduct().getProductId();
        Product l_product = null;
        try 
        {
            l_product = l_productMgr.getProduct(l_lngProductId);
        } 
        catch (NotFoundException l_nfe) 
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe); 
        }
       
        IfoProduct l_ifoProduct = (IfoProduct)l_product;
        String l_strUnderLyingProductCode = l_ifoProduct.getUnderlyingProductCode();
        log.debug("商品コード:" + l_strUnderLyingProductCode);
        l_context.setProductCode(l_strUnderLyingProductCode);
        //注文受付商品
        l_context.setOrderAcceptProduct(
            WEB3OrderAccProductDef.OPTION);
        log.debug("注文受付商品:" + l_context.getOrderAcceptProduct());
        //注文受付トランザクション
        l_context.setOrderAcceptTransaction(null);
        //
        ThreadLocalSystemAttributesRegistry.
            setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        String l_strAccountCode = null;
        try 
        {
            //２）　@日付ロールをセットする。 
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            ThreadLocalSystemAttributesRegistry.setAttribute
                (WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_orderUnitRow.getCreatedTimestamp());            
            
            long l_lngAccountId = l_orderUnit.getAccountId();
            l_strAccountCode = l_accountManager.getMainAccount(
                l_lngAccountId).getAccountCode();
        } 
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe);         
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);        
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_context;        
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
     * @@roseuid 4057D2F30189
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String l_strMethodName =
                    getClass().getName() + "onReturn(Object,Object)";
        log.entering(l_strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(l_strMethodName);          
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
     * @@roseuid 4057D2F30198
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String l_strMethodName =
            getClass().getName() + ".onThrowable(Object,Throwable)";
        log.entering(l_strMethodName);
        
          ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(l_strMethodName);     
    }
}
@
