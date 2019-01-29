head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecutedNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション出来通知サービスインタセプタ(WEB3OptionOrderExecutedNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

/**
 * (OP出来通知UnitServiceインタセプタ)<BR>
 * 株価指数オプション出来通知サービスインタセプタクラス<BR>
 * Plugin時にOP出来通知UnitServiceに対して設定する。<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionOrderExecutedNotifyUnitServiceInterceptor implements Interceptor
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOrderExecutedNotifyUnitServiceInterceptor.class);
    /**
     * @@roseuid 40C0750B00BB
     */
    public WEB3OptionOrderExecutedNotifyUnitServiceInterceptor()
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
     *      取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     *      注文単位.証券会社部店ＩＤに該当する部店の証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     *      注文単位.証券会社部店ＩＤに該当する部店の部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.商品コード = （*1) 原資産銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”06：オプション”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     * <BR>
     *  (*1) 原資産銘柄コードの取得方法@<BR>
     * 　@注文単位.getProductId()にて銘柄ＩＤを取得する。<BR>
     * 　@銘柄ＩＤに該当する先物OP銘柄オブジェクトを生成する。<BR>
     * 　@生成した先物OP銘柄.get原資産銘柄コード()。<BR>
     * <BR>
     * ２）　@日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３）　@実時間用タイムスタンプに実際の時間をセットする。 <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@TIMESTAMP_TAGに設定されているものと同じ時刻をセットする。<BR>
     * 　@　@※REAL_TIMESTAMPを使用する実装はまだ残っている為、<BR>
     * 　@　@　@デグレードを発生させないようセットする。<BR>
     * 　@設定キー： 設定キー定数定義インタフェイス.REAL_TIMESTAMP<BR>
     * <BR>
     * ４）　@当日場中の基準値取得のための属性をセットする。 <BR>
     * 　@－取引時間管理.set発注日計算用基準日時( )にて<BR>
     * 　@　@　@発注日計算の基準日時に、注文単位.発注日＋HHMMSSとして"000000"をセットする。 <BR>
     * <BR>
     * ５）　@口座をロックする。<BR>
     * 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR>
     *      ※引数は注文単位より編集。<BR>
     * @@param l_method - サービスメソッドオブジェクト
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 4057BE9903CB
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if ((l_serviceParam == null))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if ((!(l_serviceParam[0] instanceof OrderUnit)) 
            || (!(l_serviceParam[1] instanceof Timestamp)))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }

        OrderUnit l_orderUnit = (OrderUnit) l_serviceParam[0];

        WEB3GentradeTradingClendarContext l_cradingClendarContext = new WEB3GentradeTradingClendarContext();

        //証券会社部店ＩＤ
        long l_lngBranchId = l_orderUnit.getBranchId();
        log.debug("l_lngBranchId = " + l_lngBranchId);

        Branch l_branch = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;
        String l_strBranchInstitutionCode = null;
        String l_strBranchCode = null;
        try
        {
            // throw NotFoundException
            l_branch = l_accountManager.getBranch(l_lngBranchId);

            //get product manager
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingMod.getProductManager();

            //部店証券会社コード
            l_strBranchInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            l_cradingClendarContext.setInstitutionCode(l_strBranchInstitutionCode);

            //部店コード
            l_strBranchCode = l_branch.getBranchCode();
            l_cradingClendarContext.setBranchCode(l_strBranchCode);

            //市場コード "0：DEFAULT"
            l_cradingClendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //受付時間区分 "11：株価指数先物OP"
            l_cradingClendarContext.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            //商品コード
            long l_lngProductId = l_orderUnit.getProduct().getProductId();
            Product l_product = null;
            // throw NotFoundException
            l_product = l_productManager.getProduct(l_lngProductId);
            IfoProductRow l_productRow = (IfoProductRow) l_product.getDataSourceObject();
            String l_strUnderlyingProductCode = l_productRow.getUnderlyingProductCode();
            l_cradingClendarContext.setProductCode(l_strUnderlyingProductCode);

            //注文受付商品
            l_cradingClendarContext.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            //注文受付トランザクション
            l_cradingClendarContext.setOrderAcceptTransaction(null);
            //ThreadLocalSystemAttributesRegistry.setAttribute()            
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_cradingClendarContext);
            
            //日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //実時間をセットする。
            Timestamp l_tm = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3IfoAttributeNameDef.REAL_TIMESTAMP,
                l_tm);
                
            //注文単位から発注日を取得する。
            IfoOrderUnitRow l_ifoOrderUnitRow = 
                    (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_bizDate = 
                WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN, 
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
                
            //取引時間管理.set発注日計算用基準日時()のコール 
            WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
                new Timestamp(l_bizDate.getTime()));

            // ４）　@口座をロックする。<BR> 
            // 　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。<BR> 
            // ※引数は注文単位より編集。<BR>

            long l_lngAccountId = l_orderUnit.getAccountId();
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
 			 
        }
        catch (WEB3SystemLayerException l_systemLayerException)
        {
            log.error(l_systemLayerException.getMessage(),l_systemLayerException);
            throw new WEB3BaseRuntimeException(
                l_systemLayerException.getErrorInfo(),
                this.getClass().getName() + "onCall");
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        
        try
        {
            l_accountManager.lockAccount(
                l_strBranchInstitutionCode,
                l_strBranchCode,
                l_mainAccount.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {   
            log.debug(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex); 
        }
        
        return l_cradingClendarContext;
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
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<BR>
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 4057BE9903DA
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, 
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, 
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, 
            null);
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3IfoAttributeNameDef.REAL_TIMESTAMP, 
			null);
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
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
     * 取引時間管理.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * 　@※取引時間管理.clear発注日計算用基準日時( )をコール。<BR>
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 4057BE9A0002
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, 
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, 
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, 
            null);
		ThreadLocalSystemAttributesRegistry.setAttribute(
			WEB3IfoAttributeNameDef.REAL_TIMESTAMP, 
			null);
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
}
@
