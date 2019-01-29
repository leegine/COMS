head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderUnitImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP予約注文単位Impl(WEB3ToSuccIfoOrderUnitImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/11 趙林鵬(中訊) 新規作成 モデルNo.250,252
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccMessageTradingTypeDef;
import webbroker3.triggerorder.define.WEB3ToSuccSignDivDef;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物OP予約注文単位Impl)<BR>
 * 先物OP予約注文単位テーブルの1-Rowを表現するクラス。<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderUnitImpl implements IfoOrderUnit
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoOrderUnitImpl.class);

    /**
     * 先物OP予約注文単位Row<BR>
     */
    private RsvIfoOrderUnitRow rsvIfoOrderUnitRow;

    /**
     * (先物OP予約注文単位Impl)<BR>
     * コンストラクタ<BR>
     */
    public WEB3ToSuccIfoOrderUnitImpl(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow)
    {
        this.rsvIfoOrderUnitRow = l_rsvIfoOrderUnitRow;
    }

    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Rowを返却する。<BR>
     * @@return Object
     * @@roseuid 431E765B01F0
     */
    public Object getDataSourceObject()
    {
        return this.rsvIfoOrderUnitRow;
    }

    /**
     * (is指値注文)<BR>
     * （isLimitOrder）<BR>
     * <BR>
     * 指値注文であればtrueを、成行注文であればfalseを返す。<BR>
     * <BR>
     * （this.先物OP予約注文単位Row.指値 != 0、または<BR>
     * this.先物OP予約注文単位Row.単価調整値 != null）<BR>
     * であればtrueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4321814201CC
     */
    public boolean isLimitOrder()
    {
        final String STR_METHOD_NAME = "isLimitOrder()";
        log.entering(STR_METHOD_NAME);

        //this.先物OP予約注文単位Row.指値 != 0、または
        //this.先物OP予約注文単位Row.単価調整値 != null
        if (this.rsvIfoOrderUnitRow.getLimitPrice() != 0
            || !this.rsvIfoOrderUnitRow.getPriceAdjustValueIsNull())
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
     * （trueの場合はfalseを、falseの場合はtrueを返す）<BR>
     * @@return boolean
     * @@roseuid 432194D40380
     */
    public boolean isMarketOrder()
    {
        final String STR_METHOD_NAME = "isMarketOrder()";
        log.entering(STR_METHOD_NAME);

        //this.is指値注文()の戻り値を反転して返す。
        //trueの場合はfalseを、falseの場合はtrueを返す
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
     * this.先物OP予約注文単位Row.注文単位IDを返却する。<BR>
     * @@return long
     * @@roseuid 43218C5F01FB
     */
    public long getOrderUnitId()
    {
        return this.rsvIfoOrderUnitRow.getOrderUnitId();
    }

    /**
     * (get注文ID)<BR>
     * （getOrderIdの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.注文IDを返却する。<BR>
     * @@return long
     * @@roseuid 43218C5F021B
     */
    public long getOrderId()
    {
        return this.rsvIfoOrderUnitRow.getOrderId();
    }

    /**
     * (get口座ID)<BR>
     * （getAccountIdの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.口座IDを返却する。<BR>
     * @@return long
     * @@roseuid 43218C5F023A
     */
    public long getAccountId()
    {
        return this.rsvIfoOrderUnitRow.getAccountId();
    }

    /**
     * (get補助口座ID)<BR>
     * （getSubAccountIdの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.補助口座IDを返却する。<BR>
     * @@return long
     * @@roseuid 43218C5F0259
     */
    public long getSubAccountId()
    {
        return this.rsvIfoOrderUnitRow.getSubAccountId();
    }

    /**
     * (get部店ID)<BR>
     * （getBranchIdの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.部店IDを返却する。<BR>
     * @@return long
     * @@roseuid 43218C5F0278
     */
    public long getBranchId()
    {
        return this.rsvIfoOrderUnitRow.getBranchId();
    }

    /**
     * (get取引者ID)<BR>
     * （getTraderIdの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.取引者IDを返却する。<BR>
     * @@return long
     * @@roseuid 43218C5F0298
     */
    public long getTraderId()
    {
        return this.rsvIfoOrderUnitRow.getTraderId();
    }

    /**
     * (is終了済)<BR>
     * （isExpiredの実装）<BR>
     * <BR>
     * 注文が出来終了／失効により終了済かどうかを判定する。<BR>
     * <BR>
     * this.先物OP予約注文単位Row.失効区分 = "オープン"の場合は、<BR>
     * falseを返却する。<BR>
     * <BR>
     * 以外、trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C5F02C6
     */
    public boolean isExpired()
    {
        final String STR_METHOD_NAME = "isExpired()";
        log.entering(STR_METHOD_NAME);

        //this.先物OP予約注文単位Row.失効区分 = "オープン"の場合は
        if (OrderExpirationStatusEnum.OPEN.equals(
            this.rsvIfoOrderUnitRow.getExpirationStatus()))
        {
            //falseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * （未実装）<BR>
     * falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C5F02E6
     */
    public boolean isFullyExecuted()
    {
        return false;
    }

    /**
     * （未実装）<BR>
     * falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C5F0305
     */
    public boolean isPartiallyExecuted()
    {
        return false;
    }

    /**
     * （未実装）<BR>
     * trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C5F0324
     */
    public boolean isUnexecuted()
    {
        return true;
    }

    /**
     * （未実装）<BR>
     * 0を返却する。<BR>
     * @@return double
     * @@roseuid 43218C5F0343
     */
    public double getConfirmedPrice()
    {
        return 0;
    }

    /**
     * （未実装）<BR>
     * falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C5F0363
     */
    public boolean isConfirmedPriceMarketOrder()
    {
        return false;
    }

    /**
     * （未実装）<BR>
     * 0を返却する。<BR>
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
     * this.先物OP予約注文単位Row.注文数量を返却する。<BR>
     * @@return double
     * @@roseuid 43218C5F03A1
     */
    public double getQuantity()
    {
        return this.rsvIfoOrderUnitRow.getQuantity();
    }

    /**
     * （未実装）<BR>
     * 0を返却する。<BR>
     * @@return double
     * @@roseuid 43218C5F03C0
     */
    public double getExecutedAmount()
    {
        return 0;
    }

    /**
     * （未実装）<BR>
     * 0を返却する。<BR>
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
     * this.先物OP予約注文単位Row.指値を返却する。<BR>
     * @@return double
     * @@roseuid 43218C600027
     */
    public double getLimitPrice()
    {
        if (this.rsvIfoOrderUnitRow.getLimitPriceIsNull())
        {
            return Double.NaN;
        }

        return this.rsvIfoOrderUnitRow.getLimitPrice();
    }

    /**
     * （未実装）<BR>
     * nullを返却する。<BR>
     * @@return OrderAction[]
     * @@roseuid 43218C600046
     */
    public OrderAction[] getOrderActions()
    {
        return null;
    }

    /**
     * (get先物OP予約注文履歴)<BR>
     * （getRsvIfoOrderActions）<BR>
     * <BR>
     * 先物OP予約注文履歴テーブルより、以下の条件に合致するレコードを<BR>
     * 注文履歴番号 昇順で取得し返却する。<BR>
     * <BR>
     * [条件]<BR>
     * 注文ID = this.先物OP予約注文単位Row.注文ID<BR>
     * @@return RsvIfoOrderActionRow[]
     * @@throws WEB3BaseException
     * @@roseuid 4337B448010C
     */
    public RsvIfoOrderActionRow[] getRsvIfoOrderActions() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRsvIfoOrderActions()";
        log.entering(STR_METHOD_NAME);

        //[条件]
        //注文ID = this.先物OP予約注文単位Row.注文ID
        String l_strWhere = " order_id = ? ";
        Object[] l_objs = {new Long(this.rsvIfoOrderUnitRow.getOrderId())};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    l_strWhere,
                    "order_action_serial_no asc",
                    null,
                    l_objs);

            RsvIfoOrderActionRow[] l_rsvIfoActionRows = null;

            if (l_lisRsvIfoOrderActionRows != null && !l_lisRsvIfoOrderActionRows.isEmpty())
            {
                l_rsvIfoActionRows = new RsvIfoOrderActionRow[l_lisRsvIfoOrderActionRows.size()];

                l_lisRsvIfoOrderActionRows.toArray(l_rsvIfoActionRows);
            }

            log.exiting(STR_METHOD_NAME);
            return l_rsvIfoActionRows;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get作成日時)<BR>
     * （getReceivedTimestampの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.作成日付を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 43218C600084
     */
    public Timestamp getReceivedTimestamp()
    {
        return this.rsvIfoOrderUnitRow.getCreatedTimestamp();
    }

    /**
     * (get注文失効日付)<BR>
     * （getExpirationTimestampの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.注文失効日付を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 43218C6000A4
     */
    public Timestamp getExpirationTimestamp()
    {
        return this.rsvIfoOrderUnitRow.getExpirationDate();
    }

    /**
     * (get銘柄)<BR>
     * （getProductの実装）<BR>
     * <BR>
     * 先物OPプロダクトマネージャ.getProduct()により、<BR>
     * this.先物OP予約注文単位Row.銘柄IDに該当する<BR>
     * 先物OP銘柄オブジェクトを取得し返却する。<BR>
     * <BR>
     * [引数設定]<BR>
     * 銘柄ID　@：this.先物OP予約注文単位Row.銘柄ID<BR>
     * @@return Product
     * @@roseuid 43218C6000C3
     */
    public Product getProduct()
    {
        final String STR_METHOD_NAME = "getProduct()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3IfoProductManagerImpl l_productManager =
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        try
        {
            //先物OPプロダクトマネージャ.getProduct()により、this.先物OP予約注文単位Row.銘柄IDに該当する
            Product l_product =
                l_productManager.getProduct(this.rsvIfoOrderUnitRow.getProductId());

            //先物OP銘柄オブジェクトを取得し返却する。
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
     * nullを返却する。<BR>
     * @@return Order
     * @@roseuid 43218C6000E2
     */
    public Order getOrder()
    {
        return null;
    }

    /**
     * （未実装）<BR>
     * nullを返却する。<BR>
     * @@return OrderExecution[]
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
     * this.先物OP予約注文単位Row.注文有効状態を返却する。<BR>
     * @@return OrderOpenStatusEnum
     * @@roseuid 43218C600140
     */
    public OrderOpenStatusEnum getOrderOpenStatus()
    {
        return this.rsvIfoOrderUnitRow.getOrderOpenStatus();
    }

    /**
     * (get注文種別)<BR>
     * （getOrderTypeの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.注文種別を返却する。<BR>
     * @@return OrderTypeEnum
     * @@roseuid 43218C60015F
     */
    public OrderTypeEnum getOrderType()
    {
        return this.rsvIfoOrderUnitRow.getOrderType();
    }

    /**
     * (get注文カテゴリ)<BR>
     * （getOrderCategの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.注文カテゴリを返却する。<BR>
     * @@return OrderCategEnum
     * @@roseuid 43218C60017E
     */
    public OrderCategEnum getOrderCateg()
    {
        return this.rsvIfoOrderUnitRow.getOrderCateg();
    }

    /**
     * (get税区分)<BR>
     * （getTaxTypeの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.税区分を返却する。<BR>
     * @@return TaxTypeEnum
     * @@roseuid 43218C60019E
     */
    public TaxTypeEnum getTaxType()
    {
        return this.rsvIfoOrderUnitRow.getTaxType();
    }

    /**
     * (get売買)<BR>
     * （getSideの実装）<BR>
     * <BR>
     * SideEnum.getSide()の戻り値を返却する。<BR>
     * <BR>
     * [引数設定]<BR>
     * 注文種別　@：this.先物OP予約注文単位Row.注文種別<BR>
     * @@return SideEnum
     * @@roseuid 43218C6001BD
     */
    public SideEnum getSide()
    {
        return SideEnum.getSide(this.rsvIfoOrderUnitRow.getOrderType());
    }

    /**
     * (get注文状態)<BR>
     * （getOrderStatusの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.注文状態を返却する。<BR>
     * @@return OrderStatusEnum
     * @@roseuid 43218C6001DC
     */
    public OrderStatusEnum getOrderStatus()
    {
        return this.rsvIfoOrderUnitRow.getOrderStatus();
    }

    /**
     * (get失効区分)<BR>
     * （getExpirationStatusの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.失効区分を返却する。<BR>
     * @@return OrderExpirationStatusEnum
     * @@roseuid 43218C6001FB
     */
    public OrderExpirationStatusEnum getExpirationStatus()
    {
        return this.rsvIfoOrderUnitRow.getExpirationStatus();
    }

    /**
     * （未実装）<BR>
     * nullを返却する。<BR>
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
     * 先物OPプロダクトマネージャ.getTradedProduct()により、<BR>
     * this.先物OP予約注文単位Row.銘柄ID,市場IDに該当する<BR>
     * 先物取引銘柄オブジェクトを取得し返却する。<BR>
     * <BR>
     * [引数設定]<BR>
     * 銘柄ID　@：this.先物OP予約注文単位Row.銘柄ID<BR>
     * 市場ID　@：this.先物OP予約注文単位Row.市場ID<BR>
     * @@return TradedProduct
     * @@roseuid 43218C600249
     */
    public TradedProduct getTradedProduct()
    {
        final String STR_METHOD_NAME = "getTradedProduct() ";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3IfoProductManagerImpl l_productManager =
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        try
        {
            //先物OPプロダクトマネージャ.getTradedProduct()により、
            //this.先物OP予約注文単位Row.銘柄ID,市場IDに該当する
            TradedProduct l_tradedProduct =
                l_productManager.getTradedProduct(
                    this.rsvIfoOrderUnitRow.getProductId(),
                    this.rsvIfoOrderUnitRow.getMarketId());

            //先物取引銘柄オブジェクトを取得し返却する。
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
     * (is先物注文)<BR>
     * （isFuturesOrderの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.先物／オプション区分が"先物"の場合、<BR>
     * であればtrueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C600257
     */
    public boolean isFuturesOrder()
    {
        final String STR_METHOD_NAME = "isFuturesOrder()";
        log.entering(STR_METHOD_NAME);
        //this.先物OP予約注文単位Row.先物／オプション区分が"先物"の場合、
        if (WEB3FuturesOptionDivDef.FUTURES.equals(this.rsvIfoOrderUnitRow.getFutureOptionDiv()))
        {
            //であればtrueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isOP注文)<BR>
     * （isOptionsOrderの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.先物／オプション区分が"オプション"の場合、<BR>
     * であればtrueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C6055468
     */
    public boolean isOptionsOrder()
    {
        final String STR_METHOD_NAME = "isOptionsOrder()";
        log.entering(STR_METHOD_NAME);
        //this.先物OP予約注文単位Row.先物／オプション区分が"オプション"の場合
        if (WEB3FuturesOptionDivDef.OPTION.equals(this.rsvIfoOrderUnitRow.getFutureOptionDiv()))
        {
            //であればtrueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isコールOP注文)<BR>
     * (isCallOptionsOrder)<BR>
     * <BR>
     * オプションのコール注文であればtrueを、<BR>
     * それ以外であればfalseを返す。<BR>
     * <BR>
     * 　@○this.isOP注文()がtrueの場合<BR>
     * 　@　@this.get銘柄()の戻り値オブジェクト.先物オプション商品=="コールオプション"<BR>
     * 　@　@である場合、trueを返却する。<BR>
     * <BR>
     * 　@○上記以外の場合<BR>
     * 　@　@falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C6055425
     */
    public boolean isCallOptionsOrder()
    {
        final String STR_METHOD_NAME = "isCallOptionsOrder()";
        log.entering(STR_METHOD_NAME);

        //this.isOP注文()がtrueの場合
        if (this.isOptionsOrder())
        {
            //this.get銘柄()の戻り値オブジェクト.先物オプション商品=="コールオプション"
            IfoProductRow l_ifoProductRow = (IfoProductRow)this.getProduct().getDataSourceObject();

            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
            {
                //trueを返却する。
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //上記以外の場合falseを返却する
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isプットOP注文)<BR>
     * （isPutOptionsOrder）<BR>
     * <BR>
     * オプションのプット注文であればtrueを、<BR>
     * それ以外であればfalseを返す。<BR>
     * <BR>
     * 　@○this.isOP注文()がtrueの場合<BR>
     * 　@　@this.get銘柄()の戻り値オブジェクト.先物オプション商品=="プットオプション" <BR>
     * 　@　@である場合、trueを返却する。<BR>
     * <BR>
     * 　@○上記以外の場合<BR>
     * 　@　@falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C6025468
     */
    public boolean isPutOptionsOrder()
    {
        final String STR_METHOD_NAME = "isPutOptionsOrder()";
        log.entering(STR_METHOD_NAME);

        //this.isOP注文()がtrueの場合
        if (this.isOptionsOrder())
        {
            //this.get銘柄()の戻り値オブジェクト.先物オプション商品=="プットオプション"
            IfoProductRow l_ifoProductRow = (IfoProductRow)this.getProduct().getDataSourceObject();

            if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
            {
                //trueを返却する。
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //上記以外の場合falseを返却する
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is新規建注文)<BR>
     * （isOpenContractOrderの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.注文カテゴリが"先物新規建注文"の場合、<BR>
     * または this.先物OP予約注文単位Row.注文カテゴリが"OP新規建注文" <BR>
     * であればtrueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C6025125
     */
    public boolean isOpenContractOrder()
    {
        final String STR_METHOD_NAME = "isOpenContractOrder()";
        log.entering(STR_METHOD_NAME);

        //this.先物OP予約注文単位Row.注文カテゴリが"先物新規建注文"の場合
        //または this.先物OP予約注文単位Row.注文カテゴリが"OP新規建注文"
        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(this.rsvIfoOrderUnitRow.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_OPEN.equals(this.rsvIfoOrderUnitRow.getOrderCateg()))
        {
            //trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is返済注文)<BR>
     * （isSettleContractOrderの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.注文カテゴリが"先物返済注文"の場合、<BR>
     * または this.先物OP予約注文単位Row.注文カテゴリが"OP返済注文"<BR>
     * であればtrueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43218C6021455
     */
    public boolean isSettleContractOrder()
    {
        final String STR_METHOD_NAME = "isSettleContractOrder()";
        log.entering(STR_METHOD_NAME);

        //this.先物OP予約注文単位Row.注文カテゴリが"先物返済注文"の場合
        //または this.先物OP予約注文単位Row.注文カテゴリが"OP返済注文"
        if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderCateg()))
        {
            //trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * （未実装）<BR>
     * "1:条件なし"を返却する。<BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 43218C6021457
     */
    public IfoOrderExecutionConditionType getExecutionConditionType()
    {
        return IfoOrderExecutionConditionType.NONE;
    }

    /**
     * (get予約注文執行単価)<BR>
     * （getRsvOrderExecPrice）<BR>
     * <BR>
     * 予約注文（子注文）の執行単価を取得し返却する。<BR>
     * 　@　@・指値注文／成行注文の場合（±指値指定なしの場合）、指値または0を返却する。<BR>
     * 　@　@・±指値指定注文の場合は、<BR>
     * 　@　@　@親注文の指値／時価に単価調整値を加味した単価を返却する。<BR>
     * <BR>
     * 連続注文マネージャImpl.get先物OP予約注文執行単価()にdelegateする。<BR>
     * 引数設定仕様は以下の通り。<BR>
     * <BR>
     * [引数設定]<BR>
     * 注文単位　@　@　@　@：　@this.get親注文の注文単位()<BR>
     * 指値　@　@　@　@　@　@　@：　@this.先物OP予約注文単位Row.指値<BR>
     * 単価調整値　@　@　@：　@this.先物OP予約注文単位Row.単価調整値（※）<BR>
     * 先物OP取引銘柄：　@this.get取引銘柄()<BR>
     * <BR>
     * （※）nullの場合は、nullをそのままセット。<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4324EDFB02B5
     */
    public double getRsvOrderExecPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRsvOrderExecPrice() ";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        Double l_priceAdjustValue = null;
        if (!this.rsvIfoOrderUnitRow.getPriceAdjustValueIsNull())
        {
            l_priceAdjustValue = new Double(this.rsvIfoOrderUnitRow.getPriceAdjustValue());
        }

        //連続注文マネージャImpl.get先物OP予約注文執行単価()にdelegateする。
        //[引数設定]
        // 注文単位　@　@　@　@：　@this.get親注文の注文単位()
        // 指値　@　@　@　@　@　@　@：　@this.先物OP予約注文単位Row.指値
        // 単価調整値　@　@　@：　@this.先物OP予約注文単位Row.単価調整値（※）
        // 先物OP取引銘柄：　@this.get取引銘柄()
        double l_dblRsvExecPrice = l_orderManager.getReserveIfoOrderExecPrice(
            this.getParentOrderUnit(),
            this.rsvIfoOrderUnitRow.getLimitPrice(),
            l_priceAdjustValue,
            (WEB3IfoTradedProductImpl)this.getTradedProduct());

        log.exiting(STR_METHOD_NAME);
        return l_dblRsvExecPrice;
    }

    /**
     * (is買注文)<BR>
     * （isBuyOrder）<BR>
     * <BR>
     * 買注文であれば"true"を、<BR>
     * 売注文であれば"false"を返却する。<BR>
     * <BR>
     * this.get売買()=="買"の場合はtrueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43250C1300BD
     */
    public boolean isBuyOrder()
    {
        final String STR_METHOD_NAME = "isBuyOrder()";
        log.entering(STR_METHOD_NAME);

        //this.get売買()=="買"の場合はtrueを返却する。
        if (SideEnum.BUY.equals(this.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is売注文)<BR>
     * （isSellOrder）<BR>
     * <BR>
     * 売注文であれば"true"を、<BR>
     * 買注文であれば"false"を返却する。<BR>
     * <BR>
     * this.get売買()=="売"の場合はtrueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43250C76011B
     */
    public boolean isSellOrder()
    {
        final String STR_METHOD_NAME = "isSellOrder()";
        log.entering(STR_METHOD_NAME);

        //this.get売買()=="売"の場合はtrueを返却する。
        if (SideEnum.SELL.equals(this.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is±指値指定)<BR>
     * (isExecPriceOrder)<BR>
     * <BR>
     * ±指値が指定されているかどうかを判定する。<BR>
     * <BR>
     * this.先物OP予約注文単位Row.単価調整値≠null の場合、<BR>
     * trueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 43253BA10395
     */
    public boolean isExecPriceOrder()
    {
        final String STR_METHOD_NAME = "isExecPriceOrder()";
        log.entering(STR_METHOD_NAME);

        //this.先物OP予約注文単位Row.単価調整値≠null の場合、
        if (!this.rsvIfoOrderUnitRow.getPriceAdjustValueIsNull())
        {
            //trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get銘柄タイプ)<BR>
     * （getProductTypeの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.銘柄タイプを返却する。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 4326377F03D3
     */
    public ProductTypeEnum getProductType()
    {
        return this.rsvIfoOrderUnitRow.getProductType();
    }

    /**
     * (get市場)<BR>
     * （getMarketの実装）<BR>
     * <BR>
     * this.先物OP予約注文単位Row.市場IDに該当する市場オブジェクトを取得し返却する。<BR>
     * <BR>
     * 拡張金融オブジェクトマネージャ.getMarket()<BR>
     * <BR>
     * [引数設定]<BR>
     * 市場ID　@：this.先物OP予約注文単位Row.市場ID<BR>
     * @@return WEB3GentradeMarket
     * @@throws WEB3BaseException
     * @@roseuid 433B716B0354
     */
    public WEB3GentradeMarket getMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);

        if (this.rsvIfoOrderUnitRow.getMarketIdIsNull())
        {
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //this.先物OP予約注文単位Row.市場IDに該当する市場オブジェクトを取得し返却する。
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjManager.getMarket(
                this.rsvIfoOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * (get親注文の注文単位)<BR>
     * (getParentOrderUnit)<BR>
     * <BR>
     * 親注文の注文単位オブジェクトを返却する。<BR>
     * <BR>
     * 連続注文マネージャImpl.get親注文の注文単位()にdelegateする。<BR>
     * <BR>
     * [引数設定]<BR>
     * 銘柄タイプ　@："先物オプション" <BR>
     * 注文ID　@　@　@：this.先物OP予約注文単位Row.親注文の注文ID<BR>
     * @@return IfoOrderUnit
     * @@roseuid 43279DFC00A8
     */
    public IfoOrderUnit getParentOrderUnit()
    {
        final String STR_METHOD_NAME = "getParentOrderUnit()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //連続注文マネージャImpl.get親注文の注文単位()にdelegateする。
        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getParentOrderUnit(
            ProductTypeEnum.IFO,
            this.rsvIfoOrderUnitRow.getParentOrderId());

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnit;
    }

    /**
     * (is反対売買取引)<BR>
     * （isReversingTrade）<BR>
     * <BR>
     * 反対売買取引かどうかを判定する。<BR>
     * <BR>
     * 連続注文マネージャImpl.is反対売買取引()にdelegateする。<BR>
     * <BR>
     * [引数設定]<BR>
     * 連続注文取引区分：this.先物OP予約注文単位Row.連続注文取引区分<BR>
     * 注文単位　@　@　@　@：this.get親注文の注文単位<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 43279F53003A
     */
    public boolean isReversingTrade() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isReversingTrade()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //連続注文マネージャImpl.is反対売買取引()にdelegateする。
        //[引数設定]
        // 連続注文取引区分：this.先物OP予約注文単位Row.連続注文取引区分
        // 注文単位             ：this.get親注文の注文単位
        boolean l_blnIsOppoDealingTrade = l_orderManager.isReversingTrade(
            this.rsvIfoOrderUnitRow.getReserveOrderTradingType(),
            this.getParentOrderUnit());

        log.exiting(STR_METHOD_NAME);
        return l_blnIsOppoDealingTrade;
    }

    /**
     * (get先物OP予約建玉返済指定情報一覧)<BR>
     * （getContractsToClose）<BR>
     * <BR>
     * 予約注文（子注文）の、先物OP予約建玉返済指定情報の行オブジェクトを返す。<BR>
     * <BR>
     * 　@・予約注文が、既存建に対する決済注文の場合、<BR>
     * 　@　@先物OP予約建玉返済指定情報テーブルに登録されているレコードを取得し返す。<BR>
     * <BR>
     * 　@・予約注文が、親注文に対する反対取引の決済注文の場合、<BR>
     * 　@　@親注文が未約定／一部約定であれば、<BR>
     * 　@　@仮想の先物OP予約建玉返済指定情報行オブジェクト[]を作成し返す。<BR>
     * <BR>
     * 　@　@親注文が全部約定している場合は、<BR>
     * 　@　@親注文の約定により作成された建玉を元にして、<BR>
     * 　@　@仮想の先物OP予約建玉返済指定情報行オブジェクト[]を作成し返す。<BR>
     * <BR>
     * シーケンス図「（連続注文）get先物OP予約建玉返済指定情報一覧」参照。<BR>
     * ======================================================== <BR>
     * シーケンス図 ：(（連続注文）get先物OP予約建玉返済指定情報一覧) <BR>
     * 具体位置：this.先物OP予約注文単位Row.注文カテゴリが<BR>
     * 　@　@　@　@　@下記以外の場合は、例外をthrowする。<BR>
     * 　@　@　@　@　@　@　@OrderCategEnum."先物返済注文"<BR>
     * 　@　@　@　@　@　@　@OrderCategEnum."OP返済注文"<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00653 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(（連続注文）get先物OP予約建玉返済指定情報一覧) <BR>
     * 具体位置：親の約定建はあるが、返済割当可能数量不足（注文数量（残数）がある）場合は、<BR>
     * 　@　@　@　@　@返済数量不足の例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00299<BR>
     * ========================================================== <BR>
     * <BR>
     * @@return RsvIfoClosingContractSpecRow[]
     * @@throws WEB3BaseException
     * @@roseuid 433A4F820186
     */
    public RsvIfoClosingContractSpecRow[] getContractsToClose() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractsToClose()";
        log.entering(STR_METHOD_NAME);

        //注文カテゴリチェック
        //this.先物OP予約注文単位Row.注文カテゴリが
        //下記以外の場合は、例外をthrowする。
        //OrderCategEnum."先物返済注文"
        //OrderCategEnum."OP返済注文"
        if (!(OrderCategEnum.IDX_FUTURES_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderCateg())))
        {
            log.debug("注文カテゴリの値が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文カテゴリの値が不正です。");
        }

        //is反対売買取引( )
        boolean l_blnIsReserveTrade = this.isReversingTrade();

        //分岐フロー：　@親注文に対する反対取引でない（is反対売買取引（）==false）の場合）
        if (!l_blnIsReserveTrade)
        {
            //以下の条件にて先物OP予約建玉返済指定情報テーブルを検索し
            //[条件]
            //注文ID = this.get注文ID()
            //[ソート条件]
            //返済連番 昇順
            String l_strWhere = " order_id = ? ";
            Object[] l_objs = {new Long(this.getOrderId())};

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRsvIfoClosingContractSpecRows =
                    l_queryProcessor.doFindAllQuery(
                        RsvIfoClosingContractSpecRow.TYPE,
                        l_strWhere,
                        " closing_serial_no asc ",
                        null,
                        l_objs);

                if (l_lisRsvIfoClosingContractSpecRows != null
                    && !l_lisRsvIfoClosingContractSpecRows.isEmpty())
                {
                    RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows =
                        new RsvIfoClosingContractSpecRow[l_lisRsvIfoClosingContractSpecRows.size()];
                    l_lisRsvIfoClosingContractSpecRows.toArray(l_rsvIfoClosingContractSpecRows);

                    //取得結果を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return l_rsvIfoClosingContractSpecRows;
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
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //（分岐フロー：　@親注文に対する反対取引の場合
        //get親注文の注文単位()
        IfoOrderUnit l_parentOrderUnit = this.getParentOrderUnit();

        //isFullyExecuted( )
        boolean l_blnIsFullyExecuted = l_parentOrderUnit.isFullyExecuted();

        //未約定／一部約定の場合（isFullyExecuted()==false）
        if (!l_blnIsFullyExecuted)
        {
            //（インスタンス生成、プロパティセット）
            //[プロパティセット仕様]
            RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                new RsvIfoClosingContractSpecParams();

            //口座ID：　@this.先物OP予約注文単位Row.口座ID
            l_rsvIfoClosingContractSpecParams.setAccountId(
                this.rsvIfoOrderUnitRow.getAccountId());

            //　@補助口座ID：　@this.先物OP予約注文単位Row.補助口座ID
            l_rsvIfoClosingContractSpecParams.setSubAccountId(
                this.rsvIfoOrderUnitRow.getSubAccountId());

            //注文ID：　@this.先物OP予約注文単位Row.注文ID
            l_rsvIfoClosingContractSpecParams.setOrderId(
                this.rsvIfoOrderUnitRow.getOrderId());

            //　@建玉ID：　@0（固定）
            l_rsvIfoClosingContractSpecParams.setContractId(0);

            //　@返済連番：　@1（固定）
            l_rsvIfoClosingContractSpecParams.setClosingSerialNo(1);

            //返済注文数量：　@this.先物OP予約注文単位Row.注文数量
            l_rsvIfoClosingContractSpecParams.setQuantity(
                this.rsvIfoOrderUnitRow.getQuantity());

            // 　@作成日付：　@this.先物OP予約注文単位Row.作成日付
            l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(
                this.rsvIfoOrderUnitRow.getCreatedTimestamp());

            //更新日付：　@this.先物OP予約注文単位Row.更新日付
            l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(
                this.rsvIfoOrderUnitRow.getLastUpdatedTimestamp());

            //return 作成した予約建玉返済指定情報行[]
            return new RsvIfoClosingContractSpecParams[]{l_rsvIfoClosingContractSpecParams};
        }

        //全部約定の場合（isFullyExecuted()==true）
        //get建玉ListBy注文単位(注文ID : long)
        //注文ID：　@取得した親注文の注文単位.注文ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

        List l_lisContracts = l_positionManager.getContractListByOrderUnit(l_parentOrderUnit.getOrderId());

        //（取得した建玉のListをソートする）
        int l_intCnt = 0;
        if (l_lisContracts != null && !l_lisContracts.isEmpty())
        {
            l_intCnt = l_lisContracts.size();
        }

        if (l_intCnt > 1)
        {
            int l_intFlag = 0;
            //this.先物OP予約注文単位Row.決済順序==（"ランダム"or"単価益順"）の場合：
            if (WEB3ClosingOrderDef.RANDOM.equals(this.rsvIfoOrderUnitRow.getClosingOrder())
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.rsvIfoOrderUnitRow.getClosingOrder()))
            {
                //this.先物OP予約注文単位Row.get注文種別()=="先物買建返済（売返済）"or"OP買建返済（売返済）"であれば、
                //建単価の昇順（asc）でソート。
                if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderType())
                    || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderType()))
                {
                    l_intFlag = 1;
                }
                //　@上記以外の場合は、建単価の降順（desc）でソート。
                else
                {
                    l_intFlag = -1;
                }
            }

            //this.先物OP予約注文単位Row.決済順序=="単価損順"の場合：
            if (WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.rsvIfoOrderUnitRow.getClosingOrder()))
            {
                //this.先物OP予約注文単位Row.get注文種別()=="先物買建返済（売返済）"or"OP買建返済（売返済）"であれば、
                //建単価の降順（desc）でソート。
                if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderType())
                    || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderType()))
                {
                    l_intFlag = -1;
                }
                //上記以外の場合は、建単価の昇順（asc）でソート。
                else
                {
                    l_intFlag = 1;
                }
            }

            //建単価の昇順（asc）でソート。
            if (l_intFlag == 1)
            {
                for (int i = 0; i < l_intCnt; i++)
                {
                    IfoContractRow l_ifoContractRowI = (IfoContractRow)l_lisContracts.get(i);
                    for (int j = i + 1; j < l_intCnt; j++)
                    {
                        IfoContractRow l_ifoContractRowJ = (IfoContractRow)l_lisContracts.get(j);

                        if (l_ifoContractRowJ.getContractPrice() < l_ifoContractRowI.getContractPrice())
                        {
                            l_lisContracts.set(i, l_ifoContractRowJ);
                            l_lisContracts.set(j, l_ifoContractRowI);

                            l_ifoContractRowI = (IfoContractRow)l_lisContracts.get(i);
                        }
                    }
                }
            }
            //建単価の降順（desc）でソート。
            else if (l_intFlag == -1)
            {
                for (int i = 0; i < l_intCnt; i++)
                {
                    IfoContractRow l_ifoContractRowI = (IfoContractRow)l_lisContracts.get(i);
                    for (int j = i + 1; j < l_intCnt; j++)
                    {
                        IfoContractRow l_ifoContractRowJ = (IfoContractRow)l_lisContracts.get(j);

                        if (l_ifoContractRowJ.getContractPrice() > l_ifoContractRowI.getContractPrice())
                        {
                            l_lisContracts.set(i, l_ifoContractRowJ);
                            l_lisContracts.set(j, l_ifoContractRowI);

                            l_ifoContractRowI = (IfoContractRow)l_lisContracts.get(i);
                        }
                    }
                }
            }
        }

        //注文数量（残数）を、予約注文単位.注文数量 で初期化する。
        //（注文数量（残数） = 予約注文単位.注文数量;）
        double l_dblOrderQuantity = this.rsvIfoOrderUnitRow.getQuantity();

        List l_lisArrayList = new ArrayList();
        //LOOP：　@取得・ソートした建玉の要素（index）数分、繰り返し
        for (int i = 0; i < l_intCnt; i++)
        {
            IfoContractRow l_ifoContractRow = (IfoContractRow)l_lisContracts.get(i);

            //get取引勘定明細ListBy注文単位Plus建玉(注文単位ID : long, 建玉ID : long)
            //注文単位ID：　@取得した親注文の注文単位.注文単位ID
            //建玉ID：　@建玉（index）.建玉ID
            List l_lisTransactions = l_positionManager.getTransactionsListByOrderUnitPlusContract(
                l_parentOrderUnit.getOrderUnitId(),
                l_ifoContractRow.getContractId());

            int l_intTransactionsCnt = 0;
            if (l_lisTransactions != null && !l_lisTransactions.isEmpty())
            {
                l_intTransactionsCnt = l_lisTransactions.size();
            }

            double l_dblExecQuantitySum = 0.0D;
            //LOOP：　@取得した取引勘定明細の要素（index2）数分、繰り返し
            for (int j = 0; j < l_intTransactionsCnt; j++)
            {
                IfoFinTransactionRow l_ifoFinTransactionRow =
                    (IfoFinTransactionRow)l_lisTransactions.get(j);

                //取引勘定明細[index2].約定数量 の集計値（数量①@）を求める。
                //約定数量のSUM値　@＝　@（約定数量のSUM値＋取引勘定明細[index2].約定数量）
                l_dblExecQuantitySum = l_dblExecQuantitySum + l_ifoFinTransactionRow.getQuantity();
            }

            //当該建の決済可能数量（数量②）を求める

            //当該建の返済可能数量　@＝　@建玉.建玉数量（getQuantity()） － 建玉.ロック中の数量（getLockedQuantity()）
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImpl(l_ifoContractRow);
            double l_dblCanSettleQuantity = l_ifoContract.getQuantity() - l_ifoContract.getLockedQuantity();

            //当該建への決済割当数量を決定する
            //（数量②のほうが小さい数量の場合、数量②を決済割当数量として使用する）
            double l_dblSettleQuantity = l_dblExecQuantitySum;
            if (l_dblCanSettleQuantity < l_dblExecQuantitySum)
            {
                l_dblSettleQuantity = l_dblCanSettleQuantity;
            }

            //（当該建への決済割当数量 > 0の場合のみ、インスタンス生成、プロパティセット）
            if (l_dblSettleQuantity > 0)
            {
                RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                    new RsvIfoClosingContractSpecParams();

                //[プロパティセット仕様]
                //口座ID：　@this.先物OP予約注文単位Row.口座ID
                l_rsvIfoClosingContractSpecParams.setAccountId(this.rsvIfoOrderUnitRow.getAccountId());

                //補助口座ID：　@this.先物OP予約注文単位Row.補助口座ID
                l_rsvIfoClosingContractSpecParams.setSubAccountId(this.rsvIfoOrderUnitRow.getSubAccountId());

                //注文ID：　@this.先物OP予約注文単位Row.注文ID
                l_rsvIfoClosingContractSpecParams.setOrderId(this.rsvIfoOrderUnitRow.getOrderId());

                //建玉ID：　@建玉[index].建玉ID
                l_rsvIfoClosingContractSpecParams.setContractId(l_ifoContractRow.getContractId());

                //　@返済連番：　@（index＋１）
                l_rsvIfoClosingContractSpecParams.setClosingSerialNo(i + 1);

                //返済注文数量：
                //（注文数量（残数） >= 当該建への返済割当数量）の場合は、当該建への返済割当数量。
                if (l_dblOrderQuantity >= l_dblSettleQuantity)
                {
                    l_rsvIfoClosingContractSpecParams.setQuantity(l_dblSettleQuantity);
                }
                //（注文数量（残数） < 当該建への返済割当数量）の場合は、注文数量（残数）。
                else
                {
                    l_rsvIfoClosingContractSpecParams.setQuantity(l_dblOrderQuantity);
                }

                //　@作成日付：　@this.先物OP予約注文単位Row.作成日付
                l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(
                    this.rsvIfoOrderUnitRow.getCreatedTimestamp());

                //更新日付：　@this.先物OP予約注文単位Row.更新日付
                l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(
                    this.rsvIfoOrderUnitRow.getLastUpdatedTimestamp());

                l_lisArrayList.add(l_rsvIfoClosingContractSpecParams);
            }
            // 注文数量（残数） -= 当該建への返済割当数量;
            l_dblOrderQuantity -= l_dblSettleQuantity;

            //減算の結果、
            //注文数量（残数） <= 0 となった場合は、
            //建玉のLOOP（index）を抜ける。
            if (l_dblOrderQuantity <= 0)
            {
                break;
            }
        }

        //return 作成した予約建玉返済指定情報行[]
        RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows = null;

        if (l_lisArrayList != null && !l_lisArrayList.isEmpty())
        {
            //親の約定建はあるが、返済割当可能数量不足（注文数量（残数）がある）場合は、
            //返済数量不足の例外をthrowする。
            if (l_dblOrderQuantity > 0)
            {
                log.debug("注文数量が返済可能残高数量を超えています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文数量が返済可能残高数量を超えています。");
            }
            l_rsvIfoClosingContractSpecRows =
                new RsvIfoClosingContractSpecRow[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_rsvIfoClosingContractSpecRows);
        }

        log.exiting(STR_METHOD_NAME);
        return l_rsvIfoClosingContractSpecRows;
    }

    /**
     * (get注文期限区分)<BR>
     * （getExpirationDateType）<BR>
     * <BR>
     * ○this.先物OP予約注文単位Row.注文期限区分()を返却する。<BR>
     * @@return String
     * @@roseuid 433A4F820158
     */
    public String getExpirationDateType()
    {
        return this.rsvIfoOrderUnitRow.getExpirationDateType();
    }

    /**
     * (is出来るまで注文)<BR>
     * （isCarriedOrder）<BR>
     * <BR>
     * ○this.get注文期限区分()を取得する。<BR>
     * <BR>
     * 　@"出来るまで注文"の場合、<BR>
     * 　@trueを返却する。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 433A4F820569
     */
    public boolean isCarriedOrder()
    {
        final String STR_METHOD_NAME = "isCarriedOrder()";
        log.entering(STR_METHOD_NAME);

        //this.get注文期限区分()を取得する。
        //"出来るまで注文"の場合、
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.getExpirationDateType()))
        {
            //trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //上記以外の場合
        //falseを返却する。
        return false;
    }

    /**
     * (is夕場まで注文)<BR>
     * （isEveningSessionOrder）<BR>
     * <BR>
     * ○this.get注文期限区分()を取得する。<BR>
     * <BR>
     * 　@"夕場まで注文"の場合、<BR>
     * 　@trueを返却する。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 433A4F820354
     */
    public boolean isEveningSessionOrder()
    {
        final String STR_METHOD_NAME = "isEveningSessionOrder()";
        log.entering(STR_METHOD_NAME);

        //this.get注文期限区分()を取得する。
        //"夕場まで注文"の場合、
        if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.getExpirationDateType()))
        {
            //trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //上記以外の場合
        //falseを返却する。
        return false;
    }

    /**
     * (is発注済)<BR>
     * （isOrdered）<BR>
     * 予約注文が発注済かどうかを判定する。<BR>
     * <BR>
     * this.先物OP予約注文単位Row.注文単位ID≠nullの場合、<BR>
     * true（＝発注済）を返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4341EA9B00F0
     */
    public boolean isOrdered()
    {
        final String STR_METHOD_NAME = "isOrdered()";
        log.entering(STR_METHOD_NAME);

        //this.先物OP予約注文単位Row.注文単位ID≠nullの場合、
        if (!this.rsvIfoOrderUnitRow.getOrderUnitIdIsNull())
        {
            //true（＝発注済）を返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is発注実行済)<BR>
     * （isOrderExecuted）<BR>
     * 予約注文が発注処理を実行済かどうかを判定する。<BR>
     * <BR>
     * this.is発注済（）==true（発注OK）、または<BR>
     * this.先物OP予約注文単位Row.発注エラーコード≠null（発注エラー）の場合、<BR>
     * true（＝発注実行済）を返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4341EA9B00F0
     */
    public boolean isOrderExecuted()
    {
        final String STR_METHOD_NAME = "isOrderExecuted()";
        log.entering(STR_METHOD_NAME);

        //this.is発注済()==true（発注OK）または
        //this.先物OP予約注文単位Row.発注エラーコード≠null（発注エラー）の場合
        if (this.isOrdered() || this.rsvIfoOrderUnitRow.getOrderErrorCode() != null)
        {
            //true（＝発注実行済）を返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is繰越予約注文単位)<BR>
     * （isCarryoverRsvOrderUnit）<BR>
     * <BR>
     * 先物OP予約注文単位Rowを判定して、<BR>
     * 繰越していなければ"false"を返却し、<BR>
     * 繰越していれば"true"を返却する。<BR>
     * <BR>
     * １）　@this.先物OP予約注文単位Row.初回注文の注文ID==（null または 0）の場合<BR>
     * <BR>
     * 　@　@"false"（繰越していない）を返却する。<BR>
     * <BR>
     * ２）　@上記以外の場合、<BR>
     * <BR>
     * 　@　@"true"（繰越している）を返却する。<BR>
     * @@return boolean
     * @@roseuid 4341EA9B0010
     */
    public boolean isCarryoverRsvOrderUnit()
    {
        final String STR_METHOD_NAME = "isCarryoverRsvOrderUnit()";
        log.entering(STR_METHOD_NAME);

        //１）　@this.先物OP予約注文単位Row.初回注文の注文ID==（null または 0）の場合
        if (this.rsvIfoOrderUnitRow.getFirstOrderIdIsNull()
            || this.rsvIfoOrderUnitRow.getFirstOrderId() == 0)
        {
            //false"（繰越していない）を返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２）　@上記以外の場合、
        //"true"（繰越している）を返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate訂正可能状態)<BR>
     * （validateOrderForChangeability）<BR>
     * <BR>
     * 予約注文が訂正可能な状態かどうかをチェックする。<BR>
     * 前提条件：取引カレンダコンテキストが、発注日取得な状態に設定されていること。<BR>
     * <BR>
     * 　@１）　@注文有効状態チェック<BR>
     * 　@　@　@　@this.先物OP予約注文単位Row.注文有効状態≠"オープン"の場合は、<BR>
     * 　@　@　@　@「指定の予約注文はクローズ済」の例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_02287<BR>
     * <BR>
     * 　@２）　@訂正時間帯チェック<BR>
     * 　@　@　@　@取引時間管理.validate閉局後訂正取消受付可能()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[引数設定]<BR>
     * 　@　@　@　@銘柄タイプ　@　@　@　@　@：this.先物OP予約注文単位Row.銘柄タイプ<BR>
     * 　@　@　@　@先物／オプション区分：this.先物OP予約注文単位Row.先物／オプション区分<BR>
     * 　@　@　@　@部店　@　@　@　@：this.予約注文単位Row.部店IDを元に生成した部店オブジェクト<BR>
     * 　@　@　@　@立会区分　@　@　@　@　@　@　@：this.先物OP予約注文単位Row.立会区分<BR>
     * 　@　@　@　@発注日　@　@　@　@　@　@　@　@　@：this.先物OP予約注文単位Row.発注日<BR>
     * <BR>
     * 　@３）　@親注文のチェック<BR>
     * 　@　@３－１）　@親注文の取得<BR>
     * 　@　@　@　@　@　@連続注文マネージャImpl.get先物OP親注文の注文単位()で取得する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[引数設定]<BR>
     * 　@　@　@　@　@　@this.先物OP予約注文単位Row.親注文の注文ID<BR>
     * <BR>
     * 　@　@３－２）　@親注文のチェック<BR>
     * 　@　@　@　@　@　@　@連続注文マネージャImpl.validateトリガー注文設定To親注文（）<BR>
     * 　@　@　@　@　@　@　@にdelegateする。<BR>
     * <BR>
     * 　@　@　@　@　@　@[引数設定]<BR>
     * 　@　@　@　@　@　@注文単位　@：取得した親注文の注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B4628039A
     */
    public void validateOrderForChangeability() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability()";
        log.entering(STR_METHOD_NAME);

        //１）　@注文有効状態チェック
        //this.先物OP予約注文単位Row.注文有効状態≠"オープン"の場合は、
        //「指定の予約注文はクローズ済」の例外をthrowする。
        if (!OrderOpenStatusEnum.OPEN.equals(this.rsvIfoOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("指定の予約注文はクローズ済");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02287,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定の予約注文はクローズ済です。");
        }

        //this.予約注文単位Row.部店IDを元に生成した部店オブジェクト
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(this.rsvIfoOrderUnitRow.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）訂正時間帯チェック
        //取引時間管理.validate閉局後訂正取消受付可能()メソッドをコールする。
        //[引数設定]
        //銘柄タイプ　@　@　@　@　@　@　@：this.先物OP予約注文単位Row.銘柄タイプ
        //先物／オプション区分：this.先物OP予約注文単位Row.先物／オプション区分
        //部店　@　@　@　@　@　@　@　@　@　@：this.予約注文単位Row.部店IDを元に生成した部店オブジェクト
        //立会区分　@　@　@　@　@　@　@：this.先物OP予約注文単位Row.立会区分
        //発注日　@　@　@　@　@　@　@　@　@：this.先物OP予約注文単位Row.発注日
        WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
            this.rsvIfoOrderUnitRow.getProductType(),
            this.rsvIfoOrderUnitRow.getFutureOptionDiv(),
            l_branch,
            this.rsvIfoOrderUnitRow.getSessionType(),
            WEB3DateUtility.getDate(this.rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        // ３）　@親注文のチェック
        //３－１）　@親注文の取得
        //連続注文マネージャImpl.get先物OP親注文の注文単位()で取得する。
        WEB3ToSuccOrderManagerImpl l_orderMgr =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        IfoOrderUnit l_ifoOrderUnit =
            l_orderMgr.getIfoParentOrderUnit(this.rsvIfoOrderUnitRow.getParentOrderId());

        //３－２）　@親注文のチェック
        //連続注文マネージャImpl.validateトリガー注文設定To親注文
        l_orderMgr.validateTriggerOrderSettingToParentOrder(l_ifoOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取消可能状態)<BR>
     * （validateOrderForCancellation）<BR>
     * <BR>
     * 予約注文が取消可能な状態かどうかをチェックする。<BR>
     * 前提条件：取引カレンダコンテキストが、発注日取得な状態に設定されていること。<BR>
     * <BR>
     * 　@１）　@注文有効状態チェック<BR>
     * 　@　@　@　@this.先物OP予約注文単位Row.注文有効状態≠"オープン"の場合は、<BR>
     * 　@　@　@　@「指定の予約注文はクローズ済」の例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_02287<BR>
     * <BR>
     * 　@２）　@取消時間帯チェック<BR>
     * 　@　@　@　@取引時間管理.validate閉局後訂正取消受付可能()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[引数設定]<BR>
     * 　@　@　@　@銘柄タイプ　@　@　@　@：this.先物OP予約注文単位Row.銘柄タイプ<BR>
     * 　@　@　@　@先物／オプション区分：this.先物OP予約注文単位Row.先物／オプション区分<BR>
     * 　@　@　@　@部店　@　@　@　@　@：this.予約注文単位Row.部店IDを元に生成した部店オブジェクト<BR>
     * 　@　@　@　@立会区分　@　@　@　@：this.先物OP予約注文単位Row.立会区分<BR>
     * 　@　@　@　@発注日　@　@　@　@　@：this.先物OP予約注文単位Row.発注日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B4A3F006E
     */
    public void validateOrderForCancellation() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation()";
        log.entering(STR_METHOD_NAME);

        //　@１）　@注文有効状態チェック
        //this.先物OP予約注文単位Row.注文有効状態≠"オープン"の場合は、
        //「指定の予約注文はクローズ済」の例外をthrowする。
        if (!OrderOpenStatusEnum.OPEN.equals(this.rsvIfoOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("指定の予約注文はクローズ済");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02287,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定の予約注文はクローズ済です。");
        }

        //this.予約注文単位Row.部店IDを元に生成した部店オブジェクト
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(this.rsvIfoOrderUnitRow.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //　@２）　@取消時間帯チェック
        //取引時間管理.validate閉局後訂正取消受付可能()メソッドをコールする
        //[引数設定]
        //銘柄タイプ　@　@　@　@　@　@　@：this.先物OP予約注文単位Row.銘柄タイプ
        //先物／オプション区分：this.先物OP予約注文単位Row.先物／オプション区分
        //部店　@　@　@　@　@　@　@　@　@　@：this.予約注文単位Row.部店IDを元に生成した部店オブジェクト
        //立会区分　@　@　@　@　@　@　@：this.先物OP予約注文単位Row.立会区分
        //発注日　@　@　@　@　@　@　@　@　@：this.先物OP予約注文単位Row.発注日
        WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
            this.rsvIfoOrderUnitRow.getProductType(),
            this.rsvIfoOrderUnitRow.getFutureOptionDiv(),
            l_branch,
            this.rsvIfoOrderUnitRow.getSessionType(),
            WEB3DateUtility.getDate(this.rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create連続注文共通情報)<BR>
     * （createSuccCommonInfo）<BR>
     * <BR>
     * AP層⇔PR層間のメッセージクラスで使用する、<BR>
     * 連続注文共通情報のインスタンスを返却する。<BR>
     * <BR>
     * 　@１）　@戻り値インスタンスを生成する。<BR>
     * <BR>
     * 　@２）　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@（親注文）注文ID　@　@　@：this.先物OP予約注文単位Row.親注文の注文ID<BR>
     * 　@　@連続注文取引区分　@：this.先物OP予約注文単位Row.連続注文取引区分<BR>
     * <BR>
     * 　@３）　@戻り値インスタンスを返却する。<BR>
     * @@return WEB3SuccCommonInfo
     * @@roseuid 433B5AA002E6
     */
    public WEB3SuccCommonInfo createSuccCommonInfo()
    {
        final String STR_METHOD_NAME = "createSuccCommonInfo()";
        log.entering(STR_METHOD_NAME);

        //１）　@戻り値インスタンスを生成する。
        WEB3SuccCommonInfo l_commonInfo = new WEB3SuccCommonInfo();

        //２）　@プロパティをセットする。
        //（親注文）注文ID　@　@　@：this.先物OP予約注文単位Row.親注文の注文ID
        l_commonInfo.parentOrderId = this.rsvIfoOrderUnitRow.getParentOrderId() + "";

        //連続注文取引区分　@：this.先物OP予約注文単位Row.連続注文取引区分
        l_commonInfo.succTradingType = this.rsvIfoOrderUnitRow.getReserveOrderTradingType();

        //３）　@戻り値インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_commonInfo;
    }

    /**
     * (create単価調整値情報)<BR>
     * （createSuccPriceAdjustmentValueInfo）<BR>
     * <BR>
     * AP層⇔PR層間のメッセージクラスで使用する、<BR>
     * 連続注文単価調整値情報のインスタンスを返却する。<BR>
     * <BR>
     * 　@１）　@単価調整値の指定有無を判定する。<BR>
     * <BR>
     * 　@　@　@this.is±指値指定()==falseの場合は、<BR>
     * 　@　@　@単価調整指定なしと判定し、nullを返却する。<BR>
     * <BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * 　@２）　@戻り値インスタンスを生成する。<BR>
     * <BR>
     * 　@３）　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@　@符号：　@this.先物OP予約注文単位Row.単価調整値の符号部分<BR>
     * 　@　@　@　@　@　@　@　@※マイナス値の場合は、マイナスをセット。<BR>
     * 　@　@　@　@　@　@　@　@※0以上の場合は、プラスをセット。<BR>
     * <BR>
     * 　@　@　@単価調整値：　@this.先物OP予約注文単位Row.単価調整値の単価部分<BR>
     * 　@　@　@　@　@　@　@　@※マイナス値の場合は、値を反転した（符号部を除いた）値をセット。<BR>
     * 　@　@　@　@　@　@　@　@※0以上の場合、そのままの値をセット。<BR>
     * <BR>
     * 　@４）　@戻り値インスタンスを返却する。<BR>
     * @@return WEB3SuccPriceAdjustmentValueInfo
     * @@roseuid 433B5B14020C
     */
    public WEB3SuccPriceAdjustmentValueInfo createSuccPriceAdjustmentValueInfo()
    {
        final String STR_METHOD_NAME = "createSuccPriceAdjustmentValueInfo()";
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
        //  符号：　@this.先物OP予約注文単位Row.単価調整値の符号部分
        //　@　@　@    ※マイナス値の場合は、マイナスをセット。
        //　@　@　@    ※0以上の場合は、プラスをセット。
        //単価調整値：　@this.先物OP予約注文単位Row.単価調整値の単価部分
        //　@　@　@    ※マイナス値の場合は、値を反転した（符号部を除いた）値をセット。
        //　@　@　@    ※0以上の場合、そのままの値をセット。
        double l_dblPriceAdjustValue = this.rsvIfoOrderUnitRow.getPriceAdjustValue();
        if (l_dblPriceAdjustValue < 0)
        {
            l_piceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.SUBTRACT;
            l_piceAdjustmentValueInfo.priceAdjustmentValue = WEB3StringTypeUtility.formatNumber(
                Math.abs(l_dblPriceAdjustValue));
        }
        else
        {
            l_piceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.ADD;
            l_piceAdjustmentValueInfo.priceAdjustmentValue =
                WEB3StringTypeUtility.formatNumber(l_dblPriceAdjustValue);
        }

        //４）　@戻り値インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_piceAdjustmentValueInfo;
    }

    /**
     * (getメッセージ用注文単価区分)<BR>
     * （getMsgOrderPriceDiv）<BR>
     * <BR>
     * AP層⇔PR層間のメッセージクラスで使用する、注文単価区分を返却する。<BR>
     * <BR>
     * 　@○this.is±指値指定()==trueの場合<BR>
     * 　@　@"成行"を返却する。<BR>
     * <BR>
     * 　@○上記以外の場合<BR>
     * 　@　@　@this.is指値注文()==trueの場合は、<BR>
     * 　@　@"指値"を返却する。<BR>
     * <BR>
     * 　@　@以外、"成行"を返却する。<BR>
     * @@return String
     * @@roseuid 433B6AA30325
     */
    public String getMsgOrderPriceDiv()
    {
        final String STR_METHOD_NAME = "getMsgOrderPriceDiv()";
        log.entering(STR_METHOD_NAME);

        // this.is±指値指定()==trueの場合
        if (this.isExecPriceOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //"成行"を返却する。
            return WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        //○上記以外の場合
        //this.is指値注文()==trueの場合
        else if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //"指値"を返却する。
            return WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        //以外、"成行"を返却する。
        log.exiting(STR_METHOD_NAME);
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
     * ○上記以外の場合<BR>
     * 　@　@this.is指値注文()==trueの場合は、<BR>
     * 　@　@this.先物OP予約注文単位Row.指値を返却する。<BR>
     * 　@　@以外、nullを返却する。<BR>
     * @@return String
     * @@roseuid 433B6BFC0008
     */
    public String getMsgLimitPrice()
    {
        final String STR_METHOD_NAME = "getMsgLimitPrice()";
        log.entering(STR_METHOD_NAME);

        //○this.is±指値指定()==trueの場合
        if (this.isExecPriceOrder())
        {
            //nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //○上記以外の場合
        //this.is指値注文()==trueの場合
        else if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //this.先物OP予約注文単位Row.指値を返却する。
            return WEB3StringTypeUtility.formatNumber(this.rsvIfoOrderUnitRow.getLimitPrice());
        }

        log.exiting(STR_METHOD_NAME);
        //以外、nullを返却する。
        return null;
    }

    /**
     * (getメッセージ用注文有効期限)<BR>
     * （getMsgExpirationDate）<BR>
     * <BR>
     * AP層⇔PR層間のメッセージクラスで使用する、注文有効期限を返却する。<BR>
     * <BR>
     * 　@○this.is出来るまで注文()==trueの場合<BR>
     * 　@　@this.先物OP予約注文単位Row.注文失効日 を返却する。<BR>
     * <BR>
     * 　@○上記以外の場合<BR>
     * 　@　@　@nullを返却する。<BR>
     * @@return Date
     * @@roseuid 433B6E800279
     */
    public Date getMsgExpirationDate()
    {
        final String STR_METHOD_NAME = "getMsgExpirationDate()";
        log.entering(STR_METHOD_NAME);

        //○this.is出来るまで注文()==trueの場合
        if (this.isCarriedOrder())
        {
            //this.先物OP予約注文単位Row.注文失効日 を返却する。
            log.exiting(STR_METHOD_NAME);
            return this.rsvIfoOrderUnitRow.getExpirationDate();
        }

        // ○上記以外の場合
        //nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (getメッセージ用執行条件)<BR>
     * （getMsgExecCondType）<BR>
     * <BR>
     * AP層⇔PR層間のメッセージクラスで使用する、執行条件を返却する。<BR>
     * <BR>
     * "無条件"を返却する。<BR>
     * @@return String
     * @@roseuid 433B77070306
     */
    public String getMsgExecCondType()
    {
        return WEB3ExecutionConditionDef.NO_CONDITION;
    }

    /**
     * (getメッセージ用発注条件区分)<BR>
     * （getMsgOrderCondType）<BR>
     * <BR>
     * AP層⇔PR層間のメッセージクラスで使用する、発注条件区分を返却する。<BR>
     * <BR>
     * "指定なし"を返却する。<BR>
     * @@return String
     * @@roseuid 433B771C0363
     */
    public String getMsgOrderCondType()
    {
        return WEB3OrderingConditionDef.DEFAULT;
    }

    /**
     * (getメッセージ用取引区分)<BR>
     * （getMsgTradingType）<BR>
     * <BR>
     * AP層⇔PR層間のメッセージクラスで使用する、取引区分を返却する。<BR>
     * <BR>
     * this.get注文種別()が、<BR>
     * 　@○OrderTypeEnum.先物新規買建注文、<BR>
     * 　@　@または、<BR>
     * 　@　@OrderTypeEnum.OP新規買建注文の場合<BR>
     * <BR>
     * 　@　@"新規買建注文"を返却する。<BR>
     * <BR>
     * 　@○OrderTypeEnum.先物新規売建注文、<BR>
     * 　@　@または、<BR>
     * 　@　@OrderTypeEnum.OP新規売建注文の場合<BR>
     * <BR>
     * 　@　@"新規売建注文"を返却する。<BR>
     * <BR>
     * 　@○OrderTypeEnum.先物買建売返済注文（売返済）の場合、<BR>
     * 　@　@または、 <BR>
     * 　@　@OrderTypeEnum.OP買建売返済注文（売返済）の場合<BR>
     * <BR>
     * 　@　@"買建返済注文（売返済）"を返却する。<BR>
     * <BR>
     * 　@○OrderTypeEnum.先物売建買返済注文（買返済）の場合、<BR>
     * 　@　@または、<BR>
     * 　@　@OrderTypeEnum.OP売建買返済注文（買返済）の場合<BR>
     * <BR>
     * 　@　@"売建返済注文（買返済）"を返却する。<BR>
     * @@return String
     * @@roseuid 433B7D160381
     */
    public String getMsgTradingType()
    {
        final String STR_METHOD_NAME = "getMsgTradingType";
        log.entering(STR_METHOD_NAME);

        //this.get注文種別()が、OrderTypeEnum.先物新規買建注文、
        //または、OrderTypeEnum.OP新規買建注文の場合
        if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(this.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(this.getOrderType()))
        {
            //"新規買建注文"を返却する。
            log.exiting(STR_METHOD_NAME);
            return WEB3ToSuccMessageTradingTypeDef.OPEN_LONG_MARGIN;
        }

        //　@○OrderTypeEnum.先物新規売建注文
        //　@　@または、 　@　@OrderTypeEnum.OP新規売建注文の場合
        if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(this.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(this.getOrderType()))
        {
            //"新規売建注文"を返却する。
            log.exiting(STR_METHOD_NAME);
            return WEB3ToSuccMessageTradingTypeDef.OPEN_SHORT_MARGIN;
        }

        //　@○OrderTypeEnum.先物買建売返済注文（売返済）の場合、
        //　@　@または、　@　@OrderTypeEnum.OP買建売返済注文（売返済）の場合
        if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(this.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(this.getOrderType()))
        {
            //"買建返済注文（売返済）"を返却する。
            log.exiting(STR_METHOD_NAME);
            return WEB3ToSuccMessageTradingTypeDef.CLOSE_LONG_MARGIN;
        }

        //　@○OrderTypeEnum.先物売建買返済注文（買返済）の場合、
        //　@　@または、　@　@　@　@OrderTypeEnum.OP売建買返済注文（買返済）の場合
        if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(this.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(this.getOrderType()))
        {
            //"売建返済注文（買返済）"を返却する。
            log.exiting(STR_METHOD_NAME);
            return WEB3ToSuccMessageTradingTypeDef.CLOSE_SHORT_MARGIN;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
