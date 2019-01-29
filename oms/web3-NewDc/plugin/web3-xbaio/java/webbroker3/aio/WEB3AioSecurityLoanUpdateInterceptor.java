head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityLoanUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン更新インタセプタ (WEB3AioSecurityLoanUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 唐性峰 (中訊) 新規作成                                     
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (証券担保ローン更新インタセプタ)
 * 
 * @@author 唐性峰(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityLoanUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityLoanUpdateInterceptor.class); 
    
    /**
     * (入出金注文内容)<BR>
     * 入出金注文内容 <BR>
     */
    private WEB3AioNewOrderSpec cashTransOrderSpec;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    private Date bizDate;
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    private Date deliveryDate;
    
    /**
     * (注文経路区分)<BR>
     * 注文経路区分 <BR>
     *<BR>
     *1：call <BR>
     *2：PC <BR>
     *3：スリングショット <BR>
     *4：i-mode <BR>
     *5：vodafone <BR>
     *6：au <BR>
     *7：スリングショット（無料<BR>
     *9：HOST <BR>
     *A：管理者 <BR>
     *B：保証金自動振替バッチ<BR>
     */
    protected String orderRootDiv;
    
    /**
     * (証券担保ローン更新インタセプタ)<BR>
     * コンストラクタ <BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。<BR>
     * @@param cashTransOrderSpec - (入出金注文内容オブジェクト)
     * @@roseuid 4508EC8D012C
     */
    public WEB3AioSecurityLoanUpdateInterceptor(WEB3AioNewOrderSpec cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = 
            "WEB3AioSecurityLoanUpdateInterceptor(WEB3AioNewOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        this.cashTransOrderSpec = cashTransOrderSpec;
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR> 
     * 注文単位Paramsに拡張項目(*)を設定し返却する。<BR> 
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR> 
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR> 
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR> 
     *  「証券担保ローン_注文単位テーブル仕様.xls」参照。 <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE <BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。） 
     * <BR>
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * <BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス 
     * @@return AioOrderUnitParams
     * @@roseuid 4508ECD202B2
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process,
        AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate()";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        
        //注文種別,   order_type,  
        // 1019：振替注文(預り金から大証金), 
        l_orderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK);
           
        //注文カテゴリ,   order_categ,   
        //"9：入出金    （OrderCategEnum.CASH_IN_OUT）",
        l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
               
        // 銘柄タイプ,   product_type,  
        //  5：現金,   
        l_orderUnitParams.setProductType(ProductTypeEnum.CASH);
               
        // 注文数量,   quantity,   
        // 入出金注文内容.getQuantity()の戻り値,
        l_orderUnitParams.setQuantity(this.cashTransOrderSpec.getQuantity());
               
        // 発注日,   biz_date,   
        // 証券担保ローン更新インタセプタ.発注日, 
        l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));
               
        // 受渡日,   delivery_date,   
        // 証券担保ローン更新インタセプタ.受渡日,
        l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //ミニ株区分    mini_stock_div
        //0：FALSE（ミニ株でない）
        l_orderUnitParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        
        // 受注日時,   received_date_time,   
        // 処理日時,   
        l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
              
        // 扱者コード（SONAR）,   sonar_trader_code,   
        // null,   
        l_orderUnitParams.setSonarTraderCode(null);
              
        // 識別コード,   order_request_number,   
        // null,   
        l_orderUnitParams.setOrderRequestNumber(null);
              
        // .comデビット決済取引番号,   com_debit_number,   
        // null,   
        l_orderUnitParams.setComDebitNumber(null);
               
        // 保証金区分,   guarantee_div,   
        // null,   
        l_orderUnitParams.setGuaranteeDiv(null);
               
        // 出金申込区分,   payment_application_div,  
        // null,   
        l_orderUnitParams.setPaymentApplicationDiv(null);
               
        // 注文取消区分,   cancel_type,   
        //0：初期値, 
        l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        // 注文経路区分,   order_root_div,  
        // E:振替注文(預り金から大証金),   
        l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.FROM_DEPOSIT_AMOUNT_DSK);
               
        // 注文エラー理由コード,   error_reason_code,  
        //  0000：正常,   
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
               
        // MQステータス,   mq_status,  
        //  null,   
        l_orderUnitParams.setMqStatus(null);
        
        //通貨コード    currency_code
        //null
        l_orderUnitParams.setCurrencyCode(null);
        
        //入出金金額(円換算額)    convert_amount
        //null
        l_orderUnitParams.setConvertAmount(null);
        
        return l_orderUnitParams;
    }
    
    /**
     * (set発注日)<BR>
     * 発注日をセットする。<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@roseuid 4508ED1203CB
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        this.bizDate = l_datOrderBizDate;
    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@roseuid 4508ED1203DB
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        this.deliveryDate = l_datDeliveryDate;      
    }
    
    /**
     * (set注文経路区分)<BR>
     * 注文経路区分をセットする。<BR>
     * @@param l_strOrderRootDiv - (注文経路区分)
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {        
        this.orderRootDiv = l_strOrderRootDiv; 
    }
}
@
