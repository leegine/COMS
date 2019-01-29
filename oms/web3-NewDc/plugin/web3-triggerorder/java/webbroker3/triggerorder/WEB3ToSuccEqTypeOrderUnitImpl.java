head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEqTypeOrderUnitImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式予約注文単位Impl(WEB3ToSuccEqTypeOrderUnitImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 郭英 (中訊) 新規作成 
                 : 2006/08/30 張騰宇(中訊) モデル 165
*/
package webbroker3.triggerorder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderActionRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccMessageTradingTypeDef;
import webbroker3.triggerorder.define.WEB3ToSuccSignDivDef;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株式予約注文単位Impl)<BR>
 * 株式予約注文単位テーブルの1-Rowを表現するクラス。
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3ToSuccEqTypeOrderUnitImpl implements EqTypeOrderUnit 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccEqTypeOrderUnitImpl.class);
        
    /**
     * (株式予約注文単位Row)
     */
    private RsvEqOrderUnitRow rsvEqOrderUnitRow;
    
    /**
     * コンストラクタ<BR>
     */
    protected WEB3ToSuccEqTypeOrderUnitImpl(RsvEqOrderUnitRow l_rsvEqOrderUnitRow)
    {
        this.rsvEqOrderUnitRow = l_rsvEqOrderUnitRow;
    }
    
    /**
     * （getDataSourceObjectの実装）<BR> 
     * <BR>
     * this.株式予約注文単位Rowを返却する。
     * @@return Object
     * @@roseuid 431E765B01F0
     */
    public Object getDataSourceObject() 
    {        
        return this.rsvEqOrderUnitRow;
    }
    
    /**
     * (is指値注文)<BR>
     * （isLimitOrder）<BR>
     * <BR>
     * 指値注文であればtrueを、成行注文であればfalseを返す。<BR>
     * <BR>
     * （this.株式予約注文単位Row.指値 != 0、<BR>
     * 　@または this.株式予約注文単位Row.単価調整値 != null）<BR>
     * であればtrueを返却する。<BR>
     * 以外、falseを返却する。
     * @@return boolean
     * @@roseuid 4321814201CC
     */
    public boolean isLimitOrder() 
    {
        final String STR_METHOD_NAME = " isLimitOrder() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.rsvEqOrderUnitRow.getLimitPrice() != 0 ||
            this.rsvEqOrderUnitRow.getPriceAdjustValueIsNull() == false)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
                        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is成行注文)<BR>
     * （isMarketOrderの実装）<BR>
     * <BR>
     * 成行注文であればtrueを、指値注文であればfalseを返す。<BR>
     * <BR>
     * this.is指値注文()の戻り値を反転して返す。<BR>
     * （trueの場合はfalseを、falseの場合はtrueを返す）
     * @@return boolean
     * @@roseuid 432194D40380
     */
    public boolean isMarketOrder() 
    {
        final String STR_METHOD_NAME = " isMarketOrder() ";
        log.entering(STR_METHOD_NAME);
        
        //this.is指値注文()の戻り値を反転して返す。
        if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return true;
    }
    
    /**
     * (get注文単位ID)<BR>
     * （getOrderUnitIdの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.注文単位IDを返却する。
     * @@return long
     * @@roseuid 43218C5F01FB
     */
    public long getOrderUnitId() 
    {
        return this.rsvEqOrderUnitRow.getOrderUnitId();
    }
    
    /**
     * (get注文ID)<BR>
     * （getOrderIdの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.注文IDを返却する。
     * @@return long
     * @@roseuid 43218C5F021B
     */
    public long getOrderId() 
    {
        return this.rsvEqOrderUnitRow.getOrderId();
    }
    
    /**
     * (get口座ID)<BR>
     * （getAccountIdの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.口座IDを返却する。
     * @@return long
     * @@roseuid 43218C5F023A
     */
    public long getAccountId() 
    {
        return this.rsvEqOrderUnitRow.getAccountId();
    }
    
    /**
     * (get補助口座ID)<BR>
     * （getSubAccountIdの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.補助口座IDを返却する。
     * @@return long
     * @@roseuid 43218C5F0259
     */
    public long getSubAccountId() 
    {
        return this.rsvEqOrderUnitRow.getSubAccountId();
    }
    
    /**
     * (get部店ID)<BR>
     * （getBranchIdの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.部店IDを返却する。
     * @@return long
     * @@roseuid 43218C5F0278
     */
    public long getBranchId() 
    {
        return this.rsvEqOrderUnitRow.getBranchId();
    }
    
    /**
     * (get取引者ID)<BR>
     * （getTraderIdの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.取引者IDを返却する。
     * @@return long
     * @@roseuid 43218C5F0298
     */
    public long getTraderId() 
    {
        return this.rsvEqOrderUnitRow.getTraderId();
    }
    
    /**
     * (is終了済)<BR>
     * （isExpiredの実装）<BR>
     * <BR>
     * 注文が出来終了／失効により終了済かどうかを判定する。<BR>
     * <BR>
     * this.株式予約注文単位Row.失効区分 = OrderExpirationStatusEnum.OPENの場合は、<BR>
     * falseを返却する。<BR>
     * 以外、trueを返却する。
     * @@return boolean
     * @@roseuid 43218C5F02C6
     */
    public boolean isExpired() 
    {
        final String STR_METHOD_NAME = " isExpired() ";
        log.entering(STR_METHOD_NAME);
        
        //this.株式予約注文単位Row.失効区分 = OrderExpirationStatusEnum.OPENの場合は、
        if (OrderExpirationStatusEnum.OPEN.equals(
            this.rsvEqOrderUnitRow.getExpirationStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //falseを返却する。
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return true;
    }
    
    /**
     * （未実装）<BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 43218C5F02E6
     */
    public boolean isFullyExecuted() 
    {
        return false;
    }
    
    /**
     * （未実装）<BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 43218C5F0305
     */
    public boolean isPartiallyExecuted() 
    {
        return false;
    }
    
    /**
     * （未実装）<BR>
     * trueを返却する。
     * @@return boolean
     * @@roseuid 43218C5F0324
     */
    public boolean isUnexecuted() 
    {
        return true;
    }
    
    /**
     * （未実装）<BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 43218C5F0343
     */
    public double getConfirmedPrice() 
    {
        return 0;
    }
    
    /**
     * （未実装）<BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 43218C5F0363
     */
    public boolean isConfirmedPriceMarketOrder() 
    {
        return false;
    }
    
    /**
     * （未実装）<BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 43218C5F0382
     */
    public double getConfirmedQuantity() 
    {
        return 0;
    }
    
    /**
     * (get注文数量)<BR>
     * （getQuantityの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.注文数量を返却する。
     * @@return double
     * @@roseuid 43218C5F03A1
     */
    public double getQuantity() 
    {
        return this.rsvEqOrderUnitRow.getQuantity();
    }
    
    /**
     * （未実装）<BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 43218C5F03C0
     */
    public double getExecutedAmount() 
    {
        return 0;
    }
    
    /**
     * （未実装）<BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 43218C600007
     */
    public double getExecutedQuantity() 
    {
        return 0;
    }
    
    /**
     * (get指値)<BR>
     * （getLimitPriceの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.指値を返却する。
     * @@return double
     * @@roseuid 43218C600027
     */
    public double getLimitPrice() 
    {
        if (this.rsvEqOrderUnitRow.getLimitPriceIsNull())
        {
            return Double.NaN;
        }
        
        return this.rsvEqOrderUnitRow.getLimitPrice();
    }
    
    /**
     * （未実装）<BR>
     * nullを返却する。
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction[]
     * @@roseuid 43218C600046
     */
    public OrderAction[] getOrderActions() 
    {
        return null;
    }
    
    /**
     * (get株式予約注文履歴)<BR>
     * （getRsvEqOrderActions）<BR>
     * <BR>
     * 株式予約注文履歴テーブルより、以下の条件に合致するレコードを<BR>
     * 注文履歴番号 昇順で取得し返却する。<BR>
     * <BR>
     * [条件]<BR>
     * 注文ID = this.株式予約注文単位Row.注文ID
     * @@return webbroker3.triggerorder.data.RsvEqOrderActionRow[]
     * @@throws WEB3BaseException
     * @@roseuid 4337B448010C
     */
    public RsvEqOrderActionRow[] getRsvEqOrderActions() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRsvEqOrderActions() ";
        log.entering(STR_METHOD_NAME);
        
        //[条件]
        //注文ID = this.株式予約注文単位Row.注文ID
        String l_strWhere = " order_id = ? ";                
        Object[] l_objs = {new Long(this.rsvEqOrderUnitRow.getOrderId())};
        
        try
        {            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
              
            List l_lisRsvEqOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvEqOrderActionRow.TYPE, 
                    l_strWhere, 
                    "order_action_serial_no asc",
                    null,
                    l_objs);//DataNetworkException,DataQueryException
                
            RsvEqOrderActionRow[] l_rsvEqActionRows = null;
            
            if (l_lisRsvEqOrderActionRows != null && !l_lisRsvEqOrderActionRows.isEmpty())
            {
                l_rsvEqActionRows = new RsvEqOrderActionRow[l_lisRsvEqOrderActionRows.size()];
                
                l_lisRsvEqOrderActionRows.toArray(l_rsvEqActionRows);
            }
        
            log.exiting(STR_METHOD_NAME);
            
            return l_rsvEqActionRows;                    
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                  
    }
    
    /**
     * (get作成日時)<BR>
     * （getReceivedTimestampの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.created_timestampを返却する。
     * @@return Timestamp
     * @@roseuid 43218C600084
     */
    public Timestamp getReceivedTimestamp() 
    {
        return this.rsvEqOrderUnitRow.getCreatedTimestamp();
    }
    
    /**
     * (get注文失効日付)<BR>
     * （getExpirationTimestampの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.注文失効日付を返却する。
     * @@return Timestamp
     * @@roseuid 43218C6000A4
     */
    public Timestamp getExpirationTimestamp() 
    {
        return this.rsvEqOrderUnitRow.getExpirationDate();
    }
    
    /**
     * (get銘柄)<BR>
     * （getProductの実装）<BR>
     * <BR>
     * EQTYPEの拡張プロダクトマネージャ.getProduct(this.株式予約注文単位Row.銘柄ID)<BR>
     * により、this.株式予約注文単位Row.銘柄IDに該当する株式銘柄オブジェクト<BR>
     * を取得し返却する。
     * @@return Product
     * @@roseuid 43218C6000C3
     */
    public Product getProduct()
    {
        final String STR_METHOD_NAME = " getProduct() ";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                        
        try
        {
            Product l_product = 
                l_productManager.getProduct(this.rsvEqOrderUnitRow.getProductId());//NotFoundException
            
            log.exiting(STR_METHOD_NAME);            
            return l_product;
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);            
            return null;
        }            
    }
    
    /**
     * （未実装）<BR>
     * nullを返却する。
     * @@return Order
     * @@roseuid 43218C6000E2
     */
    public Order getOrder() 
    {
        return null;
    }
    
    /**
     * （未実装）<BR>
     * nullを返却する。
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution[]
     * @@roseuid 43218C600101
     */
    public OrderExecution[] getExecutions() 
    {
        return null;
    }
    
    /**
     * (get注文有効状態)<BR>
     * （getOrderOpenStatusの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.注文有効状態を返却する。
     * @@return OrderOpenStatusEnum
     * @@roseuid 43218C600140
     */
    public OrderOpenStatusEnum getOrderOpenStatus() 
    {
        return this.rsvEqOrderUnitRow.getOrderOpenStatus();
    }
    
    /**
     * (get注文種別)<BR>
     * （getOrderTypeの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.注文種別を返却する。
     * @@return OrderTypeEnum
     * @@roseuid 43218C60015F
     */
    public OrderTypeEnum getOrderType() 
    {
        return this.rsvEqOrderUnitRow.getOrderType();
    }
    
    /**
     * (get注文カテゴリ)<BR>
     * （getOrderCategの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.注文カテゴリを返却する。
     * @@return OrderCategEnum
     * @@roseuid 43218C60017E
     */
    public OrderCategEnum getOrderCateg() 
    {
        return this.rsvEqOrderUnitRow.getOrderCateg();
    }
    
    /**
     * (get税区分)<BR>
     * （getTaxTypeの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.税区分を返却する。
     * @@return TaxTypeEnum
     * @@roseuid 43218C60019E
     */
    public TaxTypeEnum getTaxType() 
    {
        return this.rsvEqOrderUnitRow.getTaxType();
    }
    
    /**
     * (get売買)<BR>
     * （getSideの実装）<BR>
     * <BR>
     * SideEnum.getSide(this.株式予約注文単位Row.注文種別)の戻り値を返却する。<BR>
     * @@return SideEnum
     * @@roseuid 43218C6001BD
     */
    public SideEnum getSide() 
    {
        return SideEnum.getSide(this.rsvEqOrderUnitRow.getOrderType());
    }
    
    /**
     * (get注文状態)<BR>
     * （getOrderStatusの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.注文状態を返却する。
     * @@return OrderStatusEnum
     * @@roseuid 43218C6001DC
     */
    public OrderStatusEnum getOrderStatus() 
    {
        return this.rsvEqOrderUnitRow.getOrderStatus();
    }
    
    /**
     * (get失効区分)<BR>
     * （getExpirationStatusの実装）<BR>
     * <BR>
     * this.株式予約注文単位Row.失効区分を返却する。
     * @@return OrderExpirationStatusEnum
     * @@roseuid 43218C6001FB
     */
    public OrderExpirationStatusEnum getExpirationStatus() 
    {
        return this.rsvEqOrderUnitRow.getExpirationStatus();
    }
    
    /**
     * （未実装）<BR>
     * nullを返却する。
     * @@return Date
     * @@roseuid 43218C60022A
     */
    public Date getDeliveryDate() 
    {
        return null;
    }
    
    /**
     * (get取引銘柄)<BR>
     * （getTradedProductの実装）<BR>
     * <BR>
     * EQTYPEの拡張プロダクトマネージャ.getTradedProduct(<BR>
     * this.株式予約注文単位Row.銘柄ID, this.株式予約注文単位Row.市場ID)により、<BR>
     * this.株式予約注文単位Row.銘柄ID、市場IDに該当する株式取引銘柄オブジェクト<BR>
     * を取得し返却する。
     * @@return TradedProduct
     * @@roseuid 43218C600249
     */
    public TradedProduct getTradedProduct() 
    {
        final String STR_METHOD_NAME = " getTradedProduct() ";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                        
        try
        {
            TradedProduct l_tradedProduct = 
                l_productManager.getTradedProduct(
                    this.rsvEqOrderUnitRow.getProductId(),
                    this.rsvEqOrderUnitRow.getMarketId());//NotFoundException
            
            log.exiting(STR_METHOD_NAME);            
            return l_tradedProduct;
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);            
            return null;
        }
    }
    
    /**
     * （未実装）<BR>
     * nullを返却する。
     * @@return EqTypeOrder
     * @@roseuid 43218C600278
     */
    public EqTypeOrder getEqTypeOrder() 
    {
        return null;
    }
    
    /**
     * (get予約注文執行単価)<BR>
     * 予約注文（子注文）の執行単価を取得し返却する。<BR>
     * －指値注文／成行注文の場合（±指値指定なしの場合）、指値または0を返却する。<BR>
     * －±指値指定注文の場合は、親注文の指値／時価に単価調整値を加味した単価を<BR>
     * 返却する。<BR>
     * <BR>
     * 連続注文マネージャImpl.get株式予約注文執行単価()にdelegateする。<BR>
     * 引数設定仕様は以下の通り。<BR>
     * <BR>
     * 親注文の注文単位：　@this.get親注文の注文単位()の戻り値<BR>
     * 指値：　@this.株式予約注文単位Rowの同項目<BR>
     * 単価調整値：　@this.株式予約注文単位Rowの同項目<BR>
     * 　@※nullの場合は、nullをそのままセット。<BR>
     * 株式取引銘柄：　@this.get取引銘柄()の戻り値
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4324EDFB02B5
     */
    public double getRsvOrderExecPrice() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getRsvOrderExecPrice() ";
        log.entering(STR_METHOD_NAME);
                
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        double l_dblRsvExecPrice = l_orderManager.getReserveEqtypeOrderExecPrice(
            this.getParentOrderUnit(),
            this.rsvEqOrderUnitRow.getLimitPrice(),
            this.rsvEqOrderUnitRow.getPriceAdjustValueIsNull() ? 
                null : new Double(this.rsvEqOrderUnitRow.getPriceAdjustValue()),
            (WEB3EquityTradedProduct)this.getTradedProduct());
                        
        log.exiting(STR_METHOD_NAME);        
        return l_dblRsvExecPrice;
    }
    
    /**
     * (is買注文)<BR>
     * （isBuyOrder）<BR>
     * <BR>
     * 買注文であればtrueを、売注文であればfalseを返却する。<BR>
     * <BR>
     * this.get売買()=="買"の場合はtrueを返却する。以外、falseを返却する。
     * @@return boolean
     * @@roseuid 43250C1300BD
     */
    public boolean isBuyOrder() 
    {
        final String STR_METHOD_NAME = " isBuyOrder() ";
        log.entering(STR_METHOD_NAME);
        
        //this.get売買()=="買"の場合はtrueを返却する。
        if (SideEnum.BUY.equals(this.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //以外、falseを返却する。
        return false;
    }
    
    /**
     * (is売注文)<BR>
     * （isSellOrder）<BR>
     * <BR>
     * 売注文であればtrueを、買注文であればfalseを返却する。<BR>
     * <BR>
     * this.get売買()=="売"の場合はtrueを返却する。以外、falseを返却する。
     * @@return boolean
     * @@roseuid 43250C76011B
     */
    public boolean isSellOrder() 
    {
        final String STR_METHOD_NAME = " isSellOrder() ";
        log.entering(STR_METHOD_NAME);
        
        //this.get売買()=="売"の場合はtrueを返却する。
        if (SideEnum.SELL.equals(this.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //以外、falseを返却する。
        return false;
    }
    
    /**
     * (is±指値指定)<BR>
     * ±指値が指定されているかどうかを判定する。<BR>
     * <BR>
     * this.株式予約注文単位Row.単価調整値≠null の場合、<BR>
     * trueを返却する。<BR>
     * 以外、falseを返却する。
     * @@return boolean
     * @@roseuid 43253BA10395
     */
    public boolean isExecPriceOrder() 
    {
        final String STR_METHOD_NAME = " isExecPriceOrder() ";
        log.entering(STR_METHOD_NAME);
        
        //this.株式予約注文単位Row.単価調整値≠null の場合、
        if (this.rsvEqOrderUnitRow.getPriceAdjustValueIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            
            //以外、falseを返却する。
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //trueを返却する。
        return true;
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * this.株式予約注文単位Row.銘柄タイプを返却する。
     * @@return ProductTypeEnum
     * @@roseuid 4326377F03D3
     */
    public ProductTypeEnum getProductType() 
    {
        return this.rsvEqOrderUnitRow.getProductType();
    }
    
    /**
     * (get市場)<BR>
     * （getMarket）<BR>
     * <BR>
     * this.株式予約注文単位Row.市場IDに該当する市場オブジェクトを取得し返却する。<BR>
     * （拡張金融オブジェクトマネージャ.getMarket(this.株式予約注文単位Row.市場ID)）
     * @@return WEB3GentradeMarket
     * @@return NotFoundException
     * @@roseuid 433B716B0354
     */
    public WEB3GentradeMarket getMarket() throws NotFoundException
    {
        final String STR_METHOD_NAME = " getMarket() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.rsvEqOrderUnitRow.getMarketIdIsNull())
        {
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market =
            (WEB3GentradeMarket)l_finObjManager.getMarket(
                this.rsvEqOrderUnitRow.getMarketId());
                
        log.exiting(STR_METHOD_NAME);            
        return l_market;               
    }
    
    /**
     * (get親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクトを返却する。<BR>
     * <BR>
     * 連続注文マネージャImpl.get株式親注文の注文単位<BR>
     * (this.株式予約注文単位Row.親注文の注文ID)にdelegateする。
     * @@return EqTypeOrderUnit
     * @@roseuid 43279DFC00A8
     */
    public EqTypeOrderUnit getParentOrderUnit() 
    {
        final String STR_METHOD_NAME = " getParentOrderUnit() ";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        EqTypeOrderUnit l_eqOrderUnit = l_orderManager.getEqtypeParentOrderUnit(
            this.rsvEqOrderUnitRow.getParentOrderId());
                        
        log.exiting(STR_METHOD_NAME);
        
        return l_eqOrderUnit;
    }
    
    /**
     * (is反対売買取引)<BR>
     * 反対売買取引かどうかを判定する。<BR>
     * <BR>
     * 連続注文マネージャImpl.is反対売買取引(<BR>
     * this.株式予約注文単位Row.連続注文取引区分, this.get親注文の注文単位)<BR>
     * にdelegateする。
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 43279F53003A
     */
    public boolean isReversingTrade() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " isReversingTrade() ";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        boolean l_blnIsOppoDealingTrade = l_orderManager.isReversingTrade(
            this.rsvEqOrderUnitRow.getReserveOrderTradingType(),
            this.getParentOrderUnit());
                        
        log.exiting(STR_METHOD_NAME);
        
        return l_blnIsOppoDealingTrade;
    }
    
    /**
     * (get株式予約建株返済指定情報一覧)<BR>
     * （getContractsToClose）<BR>
     * 予約注文（子注文）の、株式予約建株返済指定情報の行オブジェクトを返す。<BR>
     * －予約注文が、既存建に対する決済注文の場合、<BR>
     * 　@株式予約建株返済指定情報テーブルに登録されているレコードを取得し返す。<BR>
     * －予約注文が、親注文に対する反対取引の決済注文の場合、<BR>
     * 　@親注文が未約定／一部約定であれば、<BR>
     * 　@仮想の株式予約建株返済指定情報行オブジェクトを作成し返す。<BR>
     * 　@親注文が全部約定している場合は、<BR>
     * 　@親注文の約定により作成された建株を元にして、<BR>
     * 　@仮想の株式予約建株返済指定情報行オブジェクト[]を作成し返す。<BR>
     * <BR>
     * シーケンス図「（連続注文）get株式予約建株返済指定情報一覧」参照。<BR>
     *  ======================================================== <BR>
     * ・注文カテゴリチェック<BR>
     * 　@this.株式予約注文単位Row.注文カテゴリが <BR>
     * 　@下記以外の場合は、例外をthrowする。 <BR>
     *  <BR>
     * 　@　@　@OrderCategEnum."返済注文" <BR>
     * 　@　@　@OrderCategEnum."現引現渡" <BR>
     * 　@class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00653<BR>
     * <BR>
     * ・決済数量チェック
     * 　@親の約定建はあるが、決済割当可能数量不足の場合は、<BR>
     * 　@決済数量不足の例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00299<BR>
     *  ========================================================== <BR>
     * @@return webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow[]
     * @@throws WEB3BaseException
     * @@roseuid 433A4F820186
     */
    public RsvEqClosingContractSpecRow[] getContractsToClose() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContractsToClose() ";
        log.entering(STR_METHOD_NAME);
        
        //注文カテゴリチェック
        //this.株式予約注文単位Row.注文カテゴリが
        //下記以外の場合は、例外をthrowする。
        //OrderCategEnum."返済注文"
        //OrderCategEnum."現引現渡"
        if (!(OrderCategEnum.CLOSE_MARGIN.equals(this.rsvEqOrderUnitRow.getOrderCateg()) ||
            OrderCategEnum.SWAP_MARGIN.equals(this.rsvEqOrderUnitRow.getOrderCateg())))
        {
            log.debug("注文カテゴリが返済注文/現引現渡以外の場合です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文カテゴリが返済注文/現引現渡以外の場合です。");
        }
        
        //is反対売買取引( )
        boolean l_blnIsReserveTrade = this.isReversingTrade();
        
        //分岐フロー：　@親注文に対する反対取引でない（is反対売買取引()==false）場合）
        if (!l_blnIsReserveTrade)
        {
            //以下の条件にて株式予約建株返済指定情報テーブルを検索し、取得結果を返却する。
            //[条件]
            //注文ID = this.get注文ID()
            //[ソート条件]
            //返済連番 昇順
            String l_strWhere = " order_id = ? ";                
            Object[] l_objs = {
                new Long(this.getOrderId())};
        
            try
            {            
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRsvEqClosingContractSpecRows =
                    l_queryProcessor.doFindAllQuery(
                        RsvEqClosingContractSpecRow.TYPE, 
                        l_strWhere, 
                        " closing_serial_no asc ",
                        null,
                        l_objs);//DataNetworkException,DataQueryException
        
                RsvEqClosingContractSpecRow[] l_rsvRsvEqClosingContractSpecRows = null;
                if (l_lisRsvEqClosingContractSpecRows != null && 
                    !l_lisRsvEqClosingContractSpecRows.isEmpty())
                {
                    l_rsvRsvEqClosingContractSpecRows = 
                        new RsvEqClosingContractSpecRow[l_lisRsvEqClosingContractSpecRows.size()];
                    l_lisRsvEqClosingContractSpecRows.toArray(l_rsvRsvEqClosingContractSpecRows);

                    //（return 取得した予約建株返済指定情報行[];）
                    log.exiting(STR_METHOD_NAME);
                    return l_rsvRsvEqClosingContractSpecRows;   
                }
                //※検索結果が取得できなかった場合、nullを返却する。   
                log.exiting(STR_METHOD_NAME);    
                return null;    
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }                              
        }

        //（分岐フロー：　@親注文に対する反対取引の場合）
        //get親注文の注文単位( )
        EqTypeOrderUnit l_parentOrderUnit = this.getParentOrderUnit();
        
        //isFullyExecuted( )  
        boolean l_blnIsFullyExecuted = l_parentOrderUnit.isFullyExecuted();
        
        //未約定／一部約定の場合（isFullyExecuted()==false）
        if (!l_blnIsFullyExecuted)
        {
            //（インスタンス生成、プロパティセット）
            //[プロパティセット仕様]                                                           
            RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = 
                new RsvEqClosingContractSpecParams();
            
            //  口座ID：　@this.株式予約注文単位Row.口座ID
            l_rsvEqClosingContractSpecParams.setAccountId(
                this.rsvEqOrderUnitRow.getAccountId());
                                 
            //  補助口座ID：　@this.株式予約注文単位Row.補助口座ID
            l_rsvEqClosingContractSpecParams.setSubAccountId(
                this.rsvEqOrderUnitRow.getSubAccountId());
                         
            //  注文ID：　@this.株式予約注文単位Row.注文ID 
            l_rsvEqClosingContractSpecParams.setOrderId(
                this.rsvEqOrderUnitRow.getOrderId());
                                
            //  建株ID：　@0（固定）
            l_rsvEqClosingContractSpecParams.setContractId(0);
                                                       
            //  返済連番：　@1（固定） 
            l_rsvEqClosingContractSpecParams.setClosingSerialNo(1);
                                                    
            //  返済注文数量：　@this.株式予約注文単位Row.注文数量
            l_rsvEqClosingContractSpecParams.setQuantity(
                this.rsvEqOrderUnitRow.getQuantity());
                         
            //  作成日付：　@this.株式予約注文単位Row.作成日付    
            l_rsvEqClosingContractSpecParams.setCreatedTimestamp(
                this.rsvEqOrderUnitRow.getCreatedTimestamp());
                
            //  更新日付：　@this.株式予約注文単位Row.更新日付
            l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(
                this.rsvEqOrderUnitRow.getLastUpdatedTimestamp());
            
            //（return 作成した予約建株返済指定情報行;）
            return new RsvEqClosingContractSpecRow[]{l_rsvEqClosingContractSpecParams};
        }

        //全部約定の場合（isFullyExecuted()==true）
        //get建株ListBy注文単位(注文単位ID : long)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();    
            
        List l_lisContracts = l_positionManager.getContractListByOrderUnit(l_parentOrderUnit.getOrderUnitId());
        
        //（取得した建株のListをソートする）
        int l_intCnt = 0;
        if (l_lisContracts != null && !l_lisContracts.isEmpty())
        {
            l_intCnt = l_lisContracts.size();
        }
        
        if (l_intCnt > 1)
        {
            int l_intFlag = 0;
            //this.株式予約注文単位Row.決済順序区分==（"ランダム"or"単価益順"）の場合：
            if (WEB3ClosingOrderDef.RANDOM.equals(this.rsvEqOrderUnitRow.getClosingOrderType()) || 
                WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.rsvEqOrderUnitRow.getClosingOrderType()))
            {
                //    this.株式予約注文単位Row.get注文種別()=="買建返済（売返済）"or"現引注文"であれば、
                //    建単価の昇順（asc）でソート。
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(this.rsvEqOrderUnitRow.getOrderType()) ||
                    OrderTypeEnum.SWAP_MARGIN_LONG.equals(this.rsvEqOrderUnitRow.getOrderType()))                    
                {
                    l_intFlag = 1;
                }
                //    上記以外の場合は、建単価の降順（desc）でソート。  
                else
                {
                    l_intFlag = -1;
                }
            }
        
            //this.株式予約注文単位Row.決済順序区分=="単価損順"の場合：
            if (WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.rsvEqOrderUnitRow.getClosingOrderType()))
            {
                //    this.株式予約注文単位Row.get注文種別()=="買建返済（売返済）"or"現引注文"であれば、
                //    建単価の降順（desc）でソート。
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(this.rsvEqOrderUnitRow.getOrderType()) ||
                    OrderTypeEnum.SWAP_MARGIN_LONG.equals(this.rsvEqOrderUnitRow.getOrderType()))                    
                {
                    l_intFlag = -1;
                }
                //    上記以外の場合は、建単価の昇順（asc）でソート。
                else
                {
                    l_intFlag = 1;
                }
            }
        
            //※反対取引指定の場合、決済順序区分==（"建日順"or null）のケースは考慮不要。（ありえないため）
            if (this.rsvEqOrderUnitRow.getClosingOrderType() == null ||
                WEB3ClosingOrderDef.OPEN_DATE.equals(this.rsvEqOrderUnitRow.getClosingOrderType()))
            {
                l_intFlag = 0;
            }
        
            if (l_intFlag == 1)
            {
                for (int i = 0; i < l_intCnt; i++)
                {
                    EqtypeContractRow l_eqtypeContractRowI = (EqtypeContractRow)l_lisContracts.get(i);
                    for (int j = i + 1; j < l_intCnt; j++)
                    {
                        EqtypeContractRow l_eqtypeContractRowJ= (EqtypeContractRow)l_lisContracts.get(j);
                                                
                        if (l_eqtypeContractRowJ.getContractPrice() < l_eqtypeContractRowI.getContractPrice())
                        {
                            EqtypeContractRow l_eqtypeContractRowTemp = l_eqtypeContractRowI;
                        
                            l_lisContracts.set(i, l_eqtypeContractRowJ);
                            l_lisContracts.set(j, l_eqtypeContractRowTemp);
                            
                            l_eqtypeContractRowI = (EqtypeContractRow)l_lisContracts.get(i);
                        }
                    }
                }
            }
            else if (l_intFlag == -1)
            {
                for (int i = 0; i < l_intCnt; i++)
                {
                    EqtypeContractRow l_eqtypeContractRowI = (EqtypeContractRow)l_lisContracts.get(i);
                    for (int j = i + 1; j < l_intCnt; j++)
                    {
                        EqtypeContractRow l_eqtypeContractRowJ= (EqtypeContractRow)l_lisContracts.get(j);
                                                
                        if (l_eqtypeContractRowJ.getContractPrice() > l_eqtypeContractRowI.getContractPrice())
                        {
                            EqtypeContractRow l_eqtypeContractRowTemp = l_eqtypeContractRowI;
                        
                            l_lisContracts.set(i, l_eqtypeContractRowJ);
                            l_lisContracts.set(j, l_eqtypeContractRowTemp);
                            
                            l_eqtypeContractRowI = (EqtypeContractRow)l_lisContracts.get(i);
                        }
                    }
                }
            } 
        }
        
        //注文株数（残数）を、予約注文単位.注文数量 で初期化する。
        //（注文株数（残数） = 予約注文単位.注文数量;）
        double l_dblOrderQuantity = this.rsvEqOrderUnitRow.getQuantity();
        
        List l_lisArrayList = new ArrayList();
        //（LOOP：　@取得・ソートした建株の要素（index）数分、繰り返し）
        for (int i = 0; i < l_intCnt; i++)
        {
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_lisContracts.get(i);
            double l_dblSettleQuantity = 0.0D;
            
            //get株式顧客勘定明細ListBy注文単位Plus建株(注文単位ID : long, 建株ID : long)
            List l_lisEqtypeFinTransactions = l_positionManager.getFinTransactionListByOrderUnitPlusContract(
                l_parentOrderUnit.getOrderUnitId(),
                l_eqtypeContractRow.getContractId());
            
            int l_intCnt2 = 0;
            if (l_lisEqtypeFinTransactions != null && !l_lisEqtypeFinTransactions.isEmpty())
            {
                l_intCnt2 = l_lisEqtypeFinTransactions.size();
            }
            
            double l_dblExecQuantitySum = 0.0D;
            //（LOOP：　@取得した株式顧客勘定明細の要素（index2）数分、繰り返し）
            for (int j = 0; j < l_intCnt2; j++)
            {
                EqtypeFinTransactionRow l_eqtypeFinTransactionRow = 
                    (EqtypeFinTransactionRow) l_lisEqtypeFinTransactions.get(j);
                
                //株式顧客勘定明細[index2].約定数量 の集計値（数量①@）を求める。
                //約定数量のSUM値　@＝　@（約定数量のSUM値＋株式顧客勘定明細[index2].約定数量）
                l_dblExecQuantitySum = l_dblExecQuantitySum + l_eqtypeFinTransactionRow.getQuantity();                            
            }

            //当該建の決済可能数量（数量②）を求める
            WEB3EquityContract l_contract = new WEB3EquityContract(l_eqtypeContractRow);
            double l_dblCanSettleQuantity = l_contract.getQuantity() - l_contract.getLockedQuantity();
            
            //当該建への決済割当数量を決定する
            //（数量②のほうが小さい数量の場合、数量②を決済割当数量として使用する）
            l_dblSettleQuantity = l_dblExecQuantitySum;
            if (l_dblCanSettleQuantity < l_dblExecQuantitySum)
            {
                l_dblSettleQuantity = l_dblCanSettleQuantity;
            }
            
            //（当該建への決済割当数量 > 0の場合のみ、インスタンス生成、プロパティセット）
            if (l_dblSettleQuantity > 0)
            {
                RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = 
                    new RsvEqClosingContractSpecParams();
                    
                //[プロパティセット仕様]
                //口座ID：　@this.株式予約注文単位Row.口座ID
                l_rsvEqClosingContractSpecParams.setAccountId(this.rsvEqOrderUnitRow.getAccountId());
                
                //補助口座ID：　@this.株式予約注文単位Row.補助口ID
                l_rsvEqClosingContractSpecParams.setSubAccountId(this.rsvEqOrderUnitRow.getSubAccountId());
                
                //注文ID：　@this.株式予約注文単位Row.注文ID
                l_rsvEqClosingContractSpecParams.setOrderId(this.rsvEqOrderUnitRow.getOrderId());
                
                //建株ID：　@建株[index].建株ID
                l_rsvEqClosingContractSpecParams.setContractId(l_eqtypeContractRow.getContractId());
                               
                //返済連番：　@（index＋１）
                l_rsvEqClosingContractSpecParams.setClosingSerialNo(i + 1);
                                   
                //返済注文数量：
                //    （注文株数（残数） >= 当該建への決済割当数量）の場合は、当該建への決済割当数量。
                if (l_dblOrderQuantity >= l_dblSettleQuantity)
                {
                    l_rsvEqClosingContractSpecParams.setQuantity(l_dblSettleQuantity);
                }                        
                //    （注文株数（残数） < 当該建への決済割当数量）の場合は、注文株数（残数）。
                else
                {
                    l_rsvEqClosingContractSpecParams.setQuantity(l_dblOrderQuantity);
                }
                
                //作成日付：　@this.株式予約注文単位Row.作成日付
                l_rsvEqClosingContractSpecParams.setCreatedTimestamp(
                    this.rsvEqOrderUnitRow.getCreatedTimestamp());
                
                //更新日付：　@this.株式予約注文単位Row.更新日付
                l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(
                    this.rsvEqOrderUnitRow.getLastUpdatedTimestamp()); 
                    
                l_lisArrayList.add(l_rsvEqClosingContractSpecParams);                           
            }
            //注文株数（残数） -= 当該建への決済割当数量;
            l_dblOrderQuantity -= l_dblSettleQuantity;
            
            //減算の結果、
            //注文数量（残数） <= 0 となった場合は、
            //建株のLOOP（index）を抜ける。
            if (l_dblOrderQuantity <= 0)
            {
                break;
            }
        }

        //1.4.4.5:（return 作成した予約建株返済指定情報行[];）    
        RsvEqClosingContractSpecRow[] l_rsvRsvEqClosingContractSpecRows = null;

        if (l_lisArrayList != null && !l_lisArrayList.isEmpty())
        {
            if (l_dblOrderQuantity > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_rsvRsvEqClosingContractSpecRows = 
                new RsvEqClosingContractSpecRow[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_rsvRsvEqClosingContractSpecRows);
        }

        log.exiting(STR_METHOD_NAME);
        return l_rsvRsvEqClosingContractSpecRows;
    }
    
    /**
     * (is出来るまで注文単位)<BR>
     * 「出来るまで注文」の注文かどうかを判定する。<BR>
     * 「出来るまで注文」の場合はtrueを、「出来るまで注文」ではない場合はfalseを、<BR>
     * それぞれ返却する。<BR>
     * <BR>
     * １）　@this.株式予約注文単位Row.初回注文の注文単位ID≠nullの場合は、<BR>
     * trueを返す。<BR>
     * 　@　@　@以外、falseを返す。
     * @@return boolean
     * @@roseuid 433A673F0232
     */
    public boolean isCarriedOrderUnit() 
    {   
        final String STR_METHOD_NAME = " isCarriedOrderUnit() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@this.株式予約注文単位Row.初回注文の注文単位ID≠nullの場合は、     
        if (this.rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            
            //以外、falseを返す。
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        // trueを返す。
        return true;
    }
    
    /**
     * (is発注済)<BR>
     * （isOrdered）<BR>
     * 予約注文が発注済かどうかを判定する。<BR>
     * <BR>
     * this.株式予約注文単位Row.注文単位ID≠nullの場合、<BR>
     * true（＝発注済）を返却する。<BR>
     * 以外、falseを返却する。
     * @@return boolean
     * @@roseuid 4341EA9B00F0
     */
    public boolean isOrdered() 
    {
        final String STR_METHOD_NAME = " isOrdered() ";
        log.entering(STR_METHOD_NAME);
        
        //this.株式予約注文単位Row.注文単位ID≠nullの場合、
        if (this.rsvEqOrderUnitRow.getOrderUnitIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            
            //以外、falseを返却する。
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //true（＝発注済）を返却する。
        return true;
    }
    
    /**
     * (is発注実行済)<BR>
     * （isOrderExecuted）<BR>
     * 予約注文が発注処理を実行済かどうかを判定する。<BR>
     * <BR>
     * this.is発注済()==true（発注OK）、<BR>
     * または this.株式予約注文単位Row.発注エラーコード≠null（発注エラー）の場合、
     * true（＝発注実行済）を返却する。<BR>
     * 以外、falseを返却する。
     * @@return boolean
     * @@roseuid 4341EA9B00F0
     */
    public boolean isOrderExecuted() 
    {
        final String STR_METHOD_NAME = " isOrderExecuted() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.isOrdered() ||
            (this.rsvEqOrderUnitRow.getOrderErrorCode() != null))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (validate訂正可能状態)<BR>
     * （validateOrderForChangeability）<BR>
     * 予約注文が訂正可能な状態かどうかをチェックする。<BR>
     * 前提条件：取引カレンダコンテキストが、発注日取得な状態に設定されていること。<BR>
     * <BR>
     * １）　@注文有効状態チェック<BR>
     * <BR>
     * 　@　@this.株式予約注文単位Row.注文有効状態≠"オープン"の場合は、<BR>
     * 　@　@「指定の予約注文はクローズ済」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     *     tag: BUSINESS_ERROR_02287<BR>
     * <BR>
     * ２）　@発注日チェック<BR>
     * <BR>
     * 　@　@this.株式予約注文単位Row.発注日≠取引時間管理.get発注日(void)の場合は、<BR>
     * 　@　@「指定の予約注文は発注日を超過」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     *     tag: BUSINESS_ERROR_02288<BR>
     * <BR>
     * ３）　@親注文のチェック<BR>
     * <BR>
     * ３－１）　@親注文の取得<BR>
     * <BR>
     * 　@　@連続注文マネージャImpl.get株式親注文の注文単位<BR>
     * 　@　@(this.株式予約注文単位Row.親注文の注文ID)で取得する。<BR>
     * <BR>
     * ３－２）　@親注文のチェック<BR>
     * <BR>
     * 　@　@連続注文マネージャImpl.validateトリガー注文設定To親注文<BR>
     * 　@　@(取得した親注文の注文単位)にdelegateする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B4628039A
     */
    public void validateOrderForChangeability() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderForChangeability() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文有効状態チェック
        //this.株式予約注文単位Row.注文有効状態≠"オープン"の場合は、
        //「指定の予約注文はクローズ済」の例外をthrowする。
        if (!OrderOpenStatusEnum.OPEN.equals(this.rsvEqOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("注文有効状態≠オープン");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02287,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文有効状態≠オープン");
        }
        
        //２）　@発注日チェック
        //this.株式予約注文単位Row.発注日≠取引時間管理.get発注日(void)の場合は、
        //「指定の予約注文は発注日を超過」の例外をthrowする。
        Date l_datBizdate = WEB3DateUtility.getDate(this.rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        int l_intCompareValue = WEB3DateUtility.compareToDay(
            l_datBizdate, 
            WEB3GentradeTradingTimeManagement.getOrderBizDate());
        
        if (l_intCompareValue != 0)
        {
            log.debug("発注日≠取引時間管理");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02288,
                this.getClass().getName() + STR_METHOD_NAME,
                "発注日≠取引時間管理");
        }
        
        // ３）　@親注文のチェック
        //３－１）　@親注文の取得
        //連続注文マネージャImpl.get株式親注文の注文単位
        WEB3ToSuccOrderManagerImpl l_orderMgr = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        EqTypeOrderUnit l_eqOrderUnit = 
            l_orderMgr.getEqtypeParentOrderUnit(this.rsvEqOrderUnitRow.getParentOrderId());
        
        //３－２）　@親注文のチェック
        //連続注文マネージャImpl.validateトリガー注文設定To親注文
        l_orderMgr.validateTriggerOrderSettingToParentOrder(l_eqOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate取消可能状態)<BR>
     * （validateOrderForCancellation）<BR>
     * 予約注文が取消可能な状態かどうかをチェックする。<BR>
     * 前提条件：取引カレンダコンテキストが、発注日取得な状態に設定されていること。<BR>
     * <BR>
     * １）　@注文有効状態チェック<BR>
     * <BR>
     * 　@　@this.株式予約注文単位Row.注文有効状態≠"オープン"の場合は、<BR>
     * 　@　@「指定の予約注文はクローズ済」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     *     tag: BUSINESS_ERROR_02287<BR>
     * <BR>
     * ２）　@発注日チェック<BR>
     * <BR>
     * 　@　@this.株式予約注文単位Row.発注日≠取引時間管理.get発注日(void)の場合は、<BR>
     * 　@　@「指定の予約注文は発注日を超過」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     *     tag: BUSINESS_ERROR_02288<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B4A3F006E
     */
    public void validateOrderForCancellation() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderForCancellation() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文有効状態チェック                                                                                                                   
        //    this.株式予約注文単位Row.注文有効状態≠"オープン"の場合は、             
        //    「指定の予約注文はクローズ済」の例外をthrowする。                       
        if (!OrderOpenStatusEnum.OPEN.equals(this.rsvEqOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("注文有効状態≠オープン");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02287,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文有効状態≠オープン");
        }
                                                                  
        //２）　@発注日チェック                                                                                                                        
        //    this.株式予約注文単位Row.発注日≠取引時間管理.get発注日(void)の場合は、 
        //    「指定の予約注文は発注日を超過」の例外をthrowする。  
        Date l_datBizdate = WEB3DateUtility.getDate(this.rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        int l_intCompareValue = WEB3DateUtility.compareToDay(
            l_datBizdate, 
            WEB3GentradeTradingTimeManagement.getOrderBizDate());
        
        if (l_intCompareValue != 0)
        {
            log.debug("発注日≠取引時間管理");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02288,
                this.getClass().getName() + STR_METHOD_NAME,
                "発注日≠取引時間管理");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get概算簿価単価)<BR>
     * 概算簿価単価を返却する。<BR>
     * （レスポンス.概算簿価単価 設定に使用する）<BR>
     * <BR>
     * １）　@this.is売注文()==falseの場合は、nullを返却する。<BR>
     * 　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@親注文の反対売買取引かどうかを判定する。<BR>
     * <BR>
     * 　@　@連続注文マネージャImpl.is反対売買取引()==true（反対売買）の場合は、<BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * 　@　@-------------------------------------------------------<BR>
     * 　@　@＜is反対売買取引()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@連続注文取引区分：　@this.株式予約注文単位Row.連続注文取引区分<BR>
     * 　@　@親注文の注文単位：　@this.get親注文の注文単位()<BR>
     * 　@　@-------------------------------------------------------<BR>
     * <BR>
     * ３）　@反対売買でない場合、概算簿価単価を求める。<BR>
     * <BR>
     * 　@株式計算サービス.calc概算簿価単価(this.株式予約注文単位Row.銘柄ID,<BR>
     * 　@　@（this.株式予約注文単位Row.口座ID、補助口座ID）に該当する補助口座,<BR>
     * 　@　@this.株式予約注文単位Row.税区分)<BR>
     * 　@をコールし、戻り値を返却する。<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 434094D20321
     */
    public String getEstimatedBookPrice() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getEstimatedBookPrice() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@this.is売注文()==falseの場合は、nullを返却する。 
        if (!this.isSellOrder())
        {
            log.exiting(STR_METHOD_NAME);   
            return null;
        }
        //    以外、以下の処理を行う。 
        else
        {
            //２）　@親注文の反対売買取引かどうかを判定する。 
            //    連続注文マネージャImpl.is反対売買取引()==true（反対売買）の場合は、 
            //    nullを返却する。 
            WEB3ToSuccOrderManagerImpl l_orderMgr = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            boolean l_blnIsReverseTrade = l_orderMgr.isReversingTrade(
                this.rsvEqOrderUnitRow.getReserveOrderTradingType(),
                this.getParentOrderUnit());
            if (l_blnIsReverseTrade)
            {
                log.exiting(STR_METHOD_NAME);   
                return null;
            }
                
            //３）　@反対売買でない場合、概算簿価単価を求める。 
            //  株式計算サービス.calc概算簿価単価(this.株式予約注文単位Row.銘柄ID, 
            //    （this.株式予約注文単位Row.口座ID、補助口座ID）に該当する補助口座,  
            //    this.株式予約注文単位Row.税区分) 
            //  をコールし、戻り値を返却する。 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
               (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    l_finApp.getAccountManager().getSubAccount(
                        this.rsvEqOrderUnitRow.getAccountId(),
                        this.rsvEqOrderUnitRow.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("補助口座テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            double l_dblEstimatedBookPrice = l_eqBizLogicProvider.calcEstimatedBookPrice(
                this.rsvEqOrderUnitRow.getProductId(),
                l_subAccount,
                this.rsvEqOrderUnitRow.getTaxType());
            
            log.exiting(STR_METHOD_NAME);   
            return WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }
    }
    
    /**
     * (create連続注文共通情報)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、連続注文共通情報のインスタンスを<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@戻り値インスタンスを生成する。<BR>
     * <BR>
     * ２）　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@（親注文）注文ID：　@this.株式予約注文単位Row.親注文の注文ID<BR>
     * 　@　@連続注文取引区分：　@this.株式予約注文単位Row.連続注文取引区分<BR>
     * <BR>
     * ３）　@戻り値インスタンスを返却する。<BR>
     * @@return WEB3SuccCommonInfo
     * @@roseuid 433B5AA002E6
     */
    public WEB3SuccCommonInfo createSuccCommonInfo() 
    {
        final String STR_METHOD_NAME = " createSuccCommonInfo() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@戻り値インスタンスを生成する。
        WEB3SuccCommonInfo l_commonInfo = new WEB3SuccCommonInfo();
        
        //２）　@プロパティをセットする。
        //（親注文）注文ID：　@this.株式予約注文単位Row.親注文の注文ID
        l_commonInfo.parentOrderId = 
            WEB3StringTypeUtility.formatNumber(this.rsvEqOrderUnitRow.getParentOrderId());
        
        //連続注文取引区分：　@this.株式予約注文単位Row.連続注文取引区分
        l_commonInfo.succTradingType = this.rsvEqOrderUnitRow.getReserveOrderTradingType();
        
        //３）　@戻り値インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);      
        return l_commonInfo;
    }
    
    /**
     * (create単価調整値情報)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、連続注文単価調整値情報の<BR>
     * インスタンスを返却する。<BR>
     * <BR>
     * １）　@単価調整値の指定有無を判定する。<BR>
     * <BR>
     * 　@　@this.is±指値()==falseの場合は、<BR>
     * 　@　@単価調整指定なしと判定し、nullを返却する。<BR>
     * <BR>
     * 　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@戻り値インスタンスを生成する。<BR>
     * <BR>
     * ３）　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@符号：　@this.株式予約注文単位Row.単価調整値の符号部分<BR>
     * 　@　@　@　@　@　@　@※マイナス値の場合は、マイナスをセット。<BR>
     * 　@　@　@　@　@　@　@※0以上の場合は、プラスをセット。<BR>
     * 　@　@単価調整値：　@this.株式予約注文単位Row.単価調整値の単価部分<BR>
     * 　@　@　@　@　@　@　@※マイナス値の場合は、値を反転した（符号部を除いた）値をセット。<BR>
     * 　@　@　@　@　@　@　@※0以上の場合、そのままの値をセット。<BR>
     * <BR>
     * ４）　@戻り値インスタンスを返却する。<BR>
     * @@return WEB3SuccPriceAdjustmentValueInfo
     * @@roseuid 433B5B14020C
     */
    public WEB3SuccPriceAdjustmentValueInfo createSuccPriceAdjustmentValueInfo() 
    {
        final String STR_METHOD_NAME = " createSuccPriceAdjustmentValueInfo() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@単価調整値の指定有無を判定する。 
        //  this.is±指値()==falseの場合は、 
        //  単価調整指定なしと判定し、nullを返却する。 
        if (!this.isExecPriceOrder())
        {
            log.exiting(STR_METHOD_NAME);      
            return null;
        }
        
        //  以外、以下の処理を行う。 
        //２）　@戻り値インスタンスを生成する。 
        WEB3SuccPriceAdjustmentValueInfo l_piceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        
        //３）　@プロパティをセットする。 
        //  符号：　@this.株式予約注文単位Row.単価調整値の符号部分 
        //　@　@　@    ※マイナス値の場合は、マイナスをセット。 
        //　@　@　@    ※0以上の場合は、プラスをセット。 
        //  単価調整値：　@this.株式予約注文単位Row.単価調整値の単価部分 
        //　@　@　@    ※マイナス値の場合は、値を反転した（符号部を除いた）値をセット。 
        //　@　@　@    ※0以上の場合、そのままの値をセット。
        double l_dblPriceAdjustValue = this.rsvEqOrderUnitRow.getPriceAdjustValue(); 
        if (l_dblPriceAdjustValue < 0)
        {
            l_piceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.SUBTRACT;
            l_piceAdjustmentValueInfo.priceAdjustmentValue = WEB3StringTypeUtility.formatNumber(
                Math.abs(l_dblPriceAdjustValue));
        }
        else
        {
            l_piceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.ADD;
            l_piceAdjustmentValueInfo.priceAdjustmentValue = WEB3StringTypeUtility.formatNumber(l_dblPriceAdjustValue);
        }      
                         
        //４）　@戻り値インスタンスを返却する。         
        log.exiting(STR_METHOD_NAME);      
        return l_piceAdjustmentValueInfo;
    }
    
    /**
     * (getメッセージ用注文単価区分)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、注文単価区分を返却する。<BR>
     * <BR>
     * ○this.is±指値指定()==trueの場合<BR>
     * 　@　@"成行"を返却する。<BR>
     * <BR>
     * ○this.get注文カテゴリ()=="現引・現渡注文"の場合<BR>
     * 　@　@"指値"を返却する。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@　@this.is指値注文()==trueの場合は、"指値"を返却する。<BR>
     * 　@　@以外、"成行"を返却する。<BR>
     * @@return String
     * @@roseuid 433B6AA30325
     */
    public String getMsgOrderPriceDiv() 
    {
        final String STR_METHOD_NAME = " getMsgOrderPriceDiv() ";
        log.entering(STR_METHOD_NAME);
        
        // ○this.is±指値指定()==trueの場合
        if (this.isExecPriceOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //"成行"を返却する。
            return WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        //○this.get注文カテゴリ()=="現引・現渡注文"の場合
        else if (OrderCategEnum.SWAP_MARGIN.equals(this.getOrderCateg()))
        {
            log.exiting(STR_METHOD_NAME);
            //"指値"を返却する。
            return WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        //○上記以外の場合
        //this.is指値注文()==trueの場合
        else if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //"指値"を返却する。
            return WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        
        log.exiting(STR_METHOD_NAME);
        //以外、"成行"を返却する。
        return WEB3OrderPriceDivDef.MARKET_PRICE;
    }
    
    /**
     * (getメッセージ用注文単価)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、注文単価を返却する。<BR>
     * <BR>
     * ○this.is±指値指定()==trueの場合<BR>
     * 　@　@nullを返却する。<BR>
     * 　@　@※単価調整値指定時は、メッセージクラスには"成行"をセットするよう<BR>
     * 　@　@※AP⇔PR間のIFを規定したため。<BR>
     * <BR>
     * ○this.get注文カテゴリ()=="現引・現渡注文"の場合<BR>
     * 　@　@this.株式予約注文単位Row.指値を返却する。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@　@this.is指値注文()==trueの場合は、this.株式予約注文単位Row.指値 
     * を返却する。<BR>
     * 　@　@以外、nullを返却する。<BR>
     * @@return String
     * @@roseuid 433B6BFC0008
     */
    public String getMsgLimitPrice() 
    {
        final String STR_METHOD_NAME = " getMsgLimitPrice() ";
        log.entering(STR_METHOD_NAME);
        
        //○this.is±指値指定()==trueの場合
        if (this.isExecPriceOrder())
        {
            log.exiting(STR_METHOD_NAME);
            
            return null;
        }
        //this.get注文カテゴリ()=="現引・現渡注文"の場合
        else if (OrderCategEnum.SWAP_MARGIN.equals(this.getOrderCateg()))
        {
            log.exiting(STR_METHOD_NAME);
            //this.株式予約注文単位Row.指値を返却する。
            return WEB3StringTypeUtility.formatNumber(this.rsvEqOrderUnitRow.getLimitPrice());
        }
        //○上記以外の場合
        //this.is指値注文()==trueの場合 
        else if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //this.株式予約注文単位Row.指値を返却する。
            return WEB3StringTypeUtility.formatNumber(this.rsvEqOrderUnitRow.getLimitPrice());
        }
        
        log.exiting(STR_METHOD_NAME);
        //以外、nullを返却する。
        return null;
    }
    
    /**
     * (getメッセージ用注文期限区分)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、注文期限区分を返却する。<BR>
     * <BR>
     * ○this.is出来るまで注文単位()==trueの場合<BR>
     * 　@　@"出来るまで注文"を返却する。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@　@"当日限り"を返却する。
     * @@return String
     * @@roseuid 433B6E2B0150
     */
    public String getMsgExpirationDateType() 
    {
        final String STR_METHOD_NAME = " getMsgExpirationDateType() ";
        log.entering(STR_METHOD_NAME);
        
        //○this.is出来るまで注文単位()==trueの場合
        if (this.isCarriedOrderUnit())
        {
            log.exiting(STR_METHOD_NAME);
            //"出来るまで注文"を返却する。
            return WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        // ○上記以外の場合
        //"当日限り"を返却する。
        return  WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
    }
    
    /**
     * (getメッセージ用注文有効期限)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、注文有効期限を返却する。<BR>
     * <BR>
     * ○this.is出来るまで注文単位()==trueの場合<BR>
     * 　@　@this.株式予約注文単位Row.注文失効日 を返却する。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@　@nullを返却する。
     * @@return Date
     * @@roseuid 433B6E800279
     */
    public Date getMsgExpirationDate() 
    {
        final String STR_METHOD_NAME = " getMsgExpirationDate() ";
        log.entering(STR_METHOD_NAME);
        
        //○this.is出来るまで注文単位()==trueの場合
        if (this.isCarriedOrderUnit())
        {
            log.exiting(STR_METHOD_NAME);
            
            //this.株式予約注文単位Row.注文失効日 を返却する。
            return this.rsvEqOrderUnitRow.getExpirationDate();
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //○上記以外の場合
        //nullを返却する。
        return null;
    }
    
    /**
     * (getメッセージ用現引先現渡元口座区分)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、現引先現渡元口座区分を返却する。<BR>
     * <BR>
     * ○this.株式予約注文単位Row.税区分（現引現渡）=="その他"の場合<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * ○this.株式予約注文単位Row.税区分（現引現渡）=="一般"の場合<BR>
     * 　@　@"一般"を返却する。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@　@"特定"を返却する。
     * @@return String
     * @@roseuid 433CACD202AA
     */
    public String getMsgSwapTaxType() 
    {
        final String STR_METHOD_NAME = " getMsgSwapTaxType() ";
        log.entering(STR_METHOD_NAME);
        
        TaxTypeEnum l_swapTaxType = this.rsvEqOrderUnitRow.getSwapTaxType();
        
        //○this.株式予約注文単位Row.税区分（現引現渡）=="その他"の場合
        if (TaxTypeEnum.UNDEFINED.equals(l_swapTaxType))
        {
            log.exiting(STR_METHOD_NAME);
            
            //nullを返却する。
            return null;
        }
        //○this.株式予約注文単位Row.税区分（現引現渡）=="一般"の場合
        else if (TaxTypeEnum.NORMAL.equals(l_swapTaxType))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"一般"を返却する。
            return WEB3TaxTypeSpecialDef.NORMAL;
        }
        
        //○上記以外の場合
        //"特定"を返却する。
        return WEB3TaxTypeSpecialDef.SPECIAL;
    }
    
    /**
     * (getメッセージ用値段条件)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、値段条件を返却する。<BR>
     * <BR>
     * "条件なし"を返却する。
     * @@return String
     * @@roseuid 433B76E4018F
     */
    public String getMsgPriceCondType() 
    {
        return WEB3PriceConditionDef.DEFAULT;
    }
    
    /**
     * (getメッセージ用執行条件)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、執行条件を返却する。<BR>
     * <BR>
     * "無条件"を返却する。
     * @@return String
     * @@roseuid 433B77070306
     */
    public String getMsgExecCondType() 
    {
        return WEB3ExecutionConditionDef.NO_CONDITION;
    }
    
    /**
     * (getメッセージ用発注条件区分)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、発注条件区分を返却する。<BR>
     * <BR>
     * "指定なし"を返却する。
     * @@return String
     * @@roseuid 433B771C0363
     */
    public String getMsgOrderCondType() 
    {
        return WEB3OrderingConditionDef.DEFAULT;
    }
    
    /**
     * (getメッセージ用取引区分)<BR>
     * AP層⇔PR層間のメッセージクラスで使用する、取引区分を返却する。<BR>
     * <BR>
     * this.get注文種別()が、<BR>
     * ○OrderTypeEnum.現物買い注文"の場合<BR>
     * 　@"現物買注文"を返却する。<BR>
     * <BR>
     * ○OrderTypeEnum.現物売り注文"の場合<BR>
     * 　@"現物売注文"を返却する。<BR>
     * <BR>
     * ○OrderTypeEnum.新規買建注文の場合<BR>
     * 　@"新規買建注文"を返却する。<BR>
     * <BR>
     * ○OrderTypeEnum.新規売建注文）の場合<BR>
     * 　@"新規売建注文"を返却する。<BR>
     * <BR>
     * ○OrderTypeEnum.買建返済注文（売返済））の場合<BR>
     * 　@"買建返済注文（売返済）"を返却する。<BR>
     * <BR>
     * ○OrderTypeEnum.売建返済注文（買返済））の場合<BR>
     * 　@"売建返済注文（買返済）"を返却する。<BR>
     * <BR>
     * ○OrderTypeEnum.現引注文）の場合<BR>
     * 　@"現引注文"を返却する。<BR>
     * <BR>
     * ○OrderTypeEnum.現渡注文）の場合<BR>
     * 　@"現渡注文"を返却する。
     * @@return String
     * @@roseuid 433B7D160381
     */
    public String getMsgTradingType() 
    {
        final String STR_METHOD_NAME = " getMsgTradingType() ";
        log.entering(STR_METHOD_NAME);
        
        //this.get注文種別()が、
        //○OrderTypeEnum.現物買い注文"の場合
        if (OrderTypeEnum.EQUITY_BUY.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"現物買注文"を返却する。 
            return WEB3ToSuccMessageTradingTypeDef.BUY_ORDER;
        }
        //○OrderTypeEnum.現物売り注文"の場合
        else if (OrderTypeEnum.EQUITY_SELL.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"現物売注文"を返却する。
            return WEB3ToSuccMessageTradingTypeDef.SELL_ORDER;
        }
        //○OrderTypeEnum.新規買建注文の場合
        else if (OrderTypeEnum.MARGIN_LONG.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"新規買建注文"を返却する。
            return WEB3ToSuccMessageTradingTypeDef.OPEN_LONG_MARGIN;
        }
        //○OrderTypeEnum.新規売建注文）の場合
        else if (OrderTypeEnum.MARGIN_SHORT.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"新規売建注文"を返却する。
            return WEB3ToSuccMessageTradingTypeDef.OPEN_SHORT_MARGIN;
        }
        //○OrderTypeEnum.買建返済注文（売返済））の場合
        else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"買建返済注文（売返済）"を返却する。
            return WEB3ToSuccMessageTradingTypeDef.CLOSE_LONG_MARGIN;
        }
        //○OrderTypeEnum.売建返済注文（買返済））の場合
        else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"売建返済注文（買返済）"を返却する。
            return WEB3ToSuccMessageTradingTypeDef.CLOSE_SHORT_MARGIN;
        }
        //○OrderTypeEnum.現引注文）の場合
        else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"現引注文"を返却する。
            return WEB3ToSuccMessageTradingTypeDef.SWAP_MARGIN_LONG;
        }
        //○OrderTypeEnum.現渡注文）の場合
        else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"現渡注文"を返却する。
            return WEB3ToSuccMessageTradingTypeDef.SWAP_MARGIN_SHORT;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return null;
    }
}
@
