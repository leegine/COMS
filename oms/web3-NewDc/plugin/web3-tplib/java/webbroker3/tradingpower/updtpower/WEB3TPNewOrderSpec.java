head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 現注文内容(WEB3TPNewOrderSpec.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/02 山田　@卓司(FLJ) 新規作成
                    2006/09/25 徐宏偉 (中訊) モデルNo.51、52、53
                    2006/09/29 徐宏偉 (中訊) モデルNo.67、69
 Revision History : 2007/09/24 孟亜南 (中訊) モデルNo.175
 */
package webbroker3.tradingpower.updtpower;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypePositionManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSwapContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSwapChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewMiniStockOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSwapContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.TradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.Utils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PositionManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CalendarUtils;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.ordersubmitter.io.AioNewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioServerConfigConstants;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondNewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondTradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqNewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqTradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.ordersubmitter.io.MutualFundNewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundTradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.ordersubmitter.io.RuitoNewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoTradingModuleImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageService;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (現注文内容) <BR>
 * 余力計算時使用する現注文内容を表現する。
 */
public class WEB3TPNewOrderSpec
{
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPNewOrderSpec.class);

    public final static long DEFAULT_NEW_ID = -1;

    /**
     * 補助口座ID　@<BR>
     */
    private long subAccountId;

    /**
     * 補助口座タイプ　@<BR>
     */
    private SubAccountTypeEnum subAccountType;

    /**
     * 注文ID　@<BR>
     */
    private long orderId;

    /**
     * 注文単位ID　@<BR>
     */
    private long orderUnitId;

    /**
     * 銘柄ID　@<BR>
     */
    private long productId;

    /**
     * 銘柄タイプ　@<BR>
     */
    private ProductTypeEnum productType;

    /**
     * 市場ID　@<BR>
     */
    private long marketId;

    /**
     * 注文カテゴリ　@<BR>
     */
    private OrderCategEnum orderCategory;

    /**
     * 注文タイプ　@<BR>
     */
    private OrderTypeEnum orderType;

    /**
     * 数量　@<BR>
     */
    private double quantity;

    /**
     * 単価　@<BR>
     */
    private double price;

    /**
     * 指値　@<BR>
     */
    private double limitPrice;
    
    /**
     * 概算代金　@<BR>
     */
    private double estimatedPrice;

    /**
     * 発注日　@<BR>
     */
    private Date orderBizDate;

    /**
     * 受渡日　@<BR>
     */
    private Date deliveryDate;

    /**
     * 返済指定情報　@<BR>
     */
    private webbroker3
        .tradingpower
        .updtpower
        .WEB3TPContractSettleSpecify[] contractSettleSpecify;

    /**
     * 譲渡損益　@<BR>
     */
    private double capitaGain;

    /**
     * 税区分　@<BR>
     */
    private TaxTypeEnum taxType;

    /**
     * 出金申込区分<BR>
     */
    private String paymentApplicationDiv;
    
    /**
     * 受付日時<BR>
     */   
    private Date receivedDateTime;
    
    /**
     * 源泉徴収拘束金　@<BR>
     */
    private double withholdingTaxRestriction;

    /**
     * 入金日　@<BR>
     */
    private Date paymentDate;
    
    /**
     * (約定数量) <BR>
     * <BR>
     * ※債券で使用。債券注文単位テーブルの同項目をセット <BR>
     */
    private double executedQuantity;
    
    /**
     * (注文約定区分)<BR> 
     * <BR>
     * ※債券で使用。債券注文単位テーブルの同項目をセット<BR>
     */
    private String orderExecStatus;
    
    /**
     * (取引)<BR> 
     * <BR>
     * ※債券で使用。債券注文単位テーブルの同項目をセット<BR>
     */
    private String dealType;
 
    /**
     * @@roseuid 4136BF9E0045
     */
    public WEB3TPNewOrderSpec()
    {

    }

    /**
     * (get補助口座ID)<BR>
     * <BR>
     * this.補助口座IDを取得する。<BR>
     * @@return long
     */
    public long getSubAccountId()
    {
        return this.subAccountId;
    }

    /**
     * (set補助口座ID)<BR>
     * <BR>
     * 引数.補助口座IDをthis.補助口座IDにセットする<BR>
     * @@param l_subAccountId  - 補助口座ID
     */
    public void setSubAccountId(long l_subAccountId)
    {
        this.subAccountId = l_subAccountId;
    }

    /**
     * (get補助口座タイプ)<BR>
     * <BR>
     * this.補助口座タイプを取得する。<BR>
     * @@return SubAccountTypeEnum
     */
    public SubAccountTypeEnum getSubAccountType()
    {
        return this.subAccountType;
    }

    /**
     * (set補助口座タイプ)<BR>
     * <BR>
     * 引数.補助口座タイプをthis.補助口座タイプにセットする<BR>
     * @@param l_subAccountType  - 補助口座タイプ
     */
    public void setSubAccountType(SubAccountTypeEnum l_subAccountType)
    {
        this.subAccountType = l_subAccountType;
    }

    /**
     * (get注文ID)<BR>
     * <BR>
     * 注文IDを取得する。<BR>
     * @@return long
     * @@roseuid 41046AA203D8
     */
    public long getOrderId()
    {
        return orderId;
    }

    /**
     * (set注文ID)<BR>
     * <BR>
     * 注文IDをセットする。<BR>
     * @@param l_lngOrderId  - 注文ID
     * @@roseuid 41046AAC00AC
     */
    public void setOrderId(long l_lngOrderId)
    {
        orderId = l_lngOrderId;
    }

    /**
     * (get単位注文ID)<BR>
     * <BR>
     * 注文単位IDを取得する。<BR>
     * @@return long
     * @@roseuid 41046AB50177
     */
    public long getOrderUnitId()
    {
        return orderUnitId;
    }

    /**
     * (set単位注文ID)<BR>
     * <BR>
     * 注文単位IDをセットする。<BR>
     * @@param l_lngOrderUnitId - 注文単位ID
     * @@roseuid 41046ABD01D5
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        orderUnitId = l_lngOrderUnitId;
    }

    /**
     * (get銘柄ID)<BR>
     * <BR>
     * 銘柄IDを取得する。<BR>
     * @@return long
     * @@roseuid 41046AC300DB
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * (set銘柄ID)<BR>
     * <BR>
     * 銘柄IDをセットする。<BR>
     * @@param l_lngProductId  - 銘柄ID
     * @@roseuid 41046AC900FA
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }

    /**
     * (get銘柄タイプ)<BR>
     * <BR>
     * 銘柄タイプを取得する。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 41046AF901CD
     */
    public ProductTypeEnum getProductType()
    {
        return productType;
    }

    /**
     * (set銘柄タイプ)<BR>
     * <BR>
     * 銘柄タイプをセットする。<BR>
     * @@param l_productType  - 銘柄タイプ
     * @@roseuid 41046B040094
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        productType = l_productType;
    }

    /**
     * (get市場ID)<BR>
     * <BR>
     * 市場IDを取得する。<BR>
     * @@return long
     * @@roseuid 41046B0C00B3
     */
    public long getMarketId()
    {
        return marketId;
    }

    /**
     * (set市場ID)<BR>
     * <BR>
     * 市場IDをセットする。<BR>
     * @@param l_lngMarketId - 市場ID
     * @@roseuid 41046B150121
     */
    public void setMarketId(long l_lngMarketId)
    {
        marketId = l_lngMarketId;
    }

    /**
     * (get注文カテゴリ)<BR>
     * <BR>
     * 注文カテゴリを取得する。<BR>
     * @@return OrderCategEnum
     * @@roseuid 41046B2403B1
     */
    public OrderCategEnum getOrderCategory()
    {
        return orderCategory;
    }

    /**
     * (set注文カテゴリ)<BR>
     * <BR>
     * 注文カテゴリをセットする。<BR>
     * @@param l_orderCateg  - 注文カテゴリ
     * @@roseuid 41046B2B023A
     */
    public void setOrderCategory(OrderCategEnum l_orderCateg)
    {
        orderCategory = l_orderCateg;
    }

    /**
     * (get注文タイプ)<BR>
     * <BR>
     * 注文タイプを取得する。<BR>
     * @@return OrderTypeEnum
     * @@roseuid 41046B3601EC
     */
    public OrderTypeEnum getOrderType()
    {
        return orderType;
    }

    /**
     * (set注文タイプ)<BR>
     * <BR>
     * 注文タイプをセットする。<BR>
     * @@param l_orderType  - 注文タイプ
     * @@roseuid 41046B3C022A
     */
    public void setOrderType(OrderTypeEnum l_orderType)
    {
        orderType = l_orderType;
    }

    /**
     * (get注文数量)<BR>
     * <BR>
     * 注文数量を取得する。<BR>
     * @@return double
     * @@roseuid 41046C850056
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * (set注文数量)<BR>
     * <BR>
     * 注文数量をセットする。<BR>
     * @@param l_dblQuantity  - 注文数量
     * @@roseuid 41046C8B0150
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * (get注文単価)<BR>
     * <BR>
     * 注文単価を取得する。<BR>
     * @@return double
     * @@roseuid 41046C98017F
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * (set注文単価)<BR>
     * <BR>
     * 注文単価をセットする。<BR>
     * @@param l_dblPrice  - 注文単価
     * @@roseuid 41046C9D03C1
     */
    public void setPrice(double l_dblPrice)
    {
        price = l_dblPrice;
    }

    /**
     * (get指値)<BR>
     * <BR>
     * 指値を取得する。<BR>
     * @@return double
     */
    public double getLimitPrice()
    {
        return this.limitPrice;
    }

    /**
     * (set指値)<BR>
     * <BR>
     * 指値をセットする。<BR>
     * @@param l_dblLimitPrice  - 指値
     */
    public void setLimitPrice(double l_dblLimitPrice)
    {
        this.limitPrice = l_dblLimitPrice;
    }

    /**
     * (get概算受渡代金)<BR>
     * <BR>
     * 概算受渡代金を取得する。<BR>
     * @@return double
     * @@roseuid 41046CA703D0
     */
    public double getEstimatedPrice()
    {
        return estimatedPrice;
    }

    /**
     * (set概算受渡代金)<BR>
     * <BR>
     * 概算受渡代金をセットする。<BR>
     * @@param l_dblEstimatedPrice  - 概算受渡代金
     * @@roseuid 41046CAD02A7
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice)
    {
        estimatedPrice = l_dblEstimatedPrice;
    }

    /**
     * (get注文日)<BR>
     * <BR>
     * 注文日を取得する。<BR>
     * @@return java.util.Date
     * @@roseuid 41046CB90269
     */
    public Date getOrderBizDate()
    {
        return orderBizDate;
    }

    /**
     * (set注文日)<BR>
     * <BR>
     * 注文日をセットする。<BR>
     * @@param l_datOrderBizDate  - 発注日
     * @@roseuid 41046CC201FC
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        orderBizDate = l_datOrderBizDate;
    }

    /**
     * (get受渡日)<BR>
     * <BR>
     * 受渡日を取得する。<BR>
     * @@return java.util.Date
     * @@roseuid 41046CCF022A
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (set受渡日)<BR>
     * <BR>
     * 受渡日をセットする。<BR>
     * @@param l_datDeliveryDate  - 受渡日
     * @@roseuid 41046CD60102
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (get返済指定情報)<BR>
     * <BR>
     * 返済指定情報を取得する。<BR>
     * @@return WEB3TPContractSettleSpecify[]
     * @@roseuid 41046CE8018E
     */
    public WEB3TPContractSettleSpecify[] getContractSettleSpecify()
    {
        return contractSettleSpecify;
    }

    /**
     * (set返済指定情報)<BR>
     * <BR>
     * 返済指定情報をセットする。<BR>
     * @@param l_contractSettleSpecify -　@返済指定情報
     * @@roseuid 41046CEE03D0
     */
    public void setContractSettleSpecify(WEB3TPContractSettleSpecify[]
                                         l_contractSettleSpecify)
    {
        contractSettleSpecify = l_contractSettleSpecify;
    }

    /**
     * (get譲渡損益)<BR>
     * <BR>
     * 譲渡損益を取得する。<BR>
     * @@return double
     * @@roseuid 41046CFB0046
     */
    public double getCapitaGain()
    {
        return capitaGain;
    }

    /**
     * (set譲渡損益)<BR>
     * <BR>
     * 譲渡損益をセットする。<BR>
     * @@param l_dblCapitaGain  - 譲渡損益
     * @@roseuid 41046D030065
     */
    public void setCapitaGain(double l_dblCapitaGain)
    {
        capitaGain = l_dblCapitaGain;
    }

    /**
     * (get税区分)<BR>
     * <BR>
     * 税区分を取得する。<BR>
     * @@return TaxTypeEnum
     * @@roseuid 41046D0C00D3
     */
    public TaxTypeEnum getTaxType()
    {
        return taxType;
    }

    /**
     * (set税区分)<BR>
     * <BR>
     * 税区分をセットする。<BR>
     * @@param l_TaxType  - 税区分
     * @@roseuid 41046D110373
     */
    public void setTaxType(TaxTypeEnum l_TaxType)
    {
        taxType = l_TaxType;
    }

    /**
     * (get出金申込区分)<BR>
     * <BR>
     * this.出金申込区分を取得する。<BR>
     * @@return String
     */
    public String getPaymentApplicationDiv()
    {
        return paymentApplicationDiv;
    }

    /**
     * (set出金申込区分)<BR>
     * <BR>
     * 引数.出金申込区分をthis.出金申込区分にセットする<BR>
     * @@param l_subAccountId  - 出金申込区分
     */
    public void setPaymentApplicationDiv(String l_paymentApplicationDiv)
    {
        this.paymentApplicationDiv = l_paymentApplicationDiv;
    }

    /**
     * (get受付日時)<BR>
     * <BR>
     * this.受付日時を取得する。<BR>
     * @@return Date
     */
    public Date getReceivedDateTime()
    {
        return this.receivedDateTime;
    }

    /**
     * (set受付日時)<BR>
     * <BR>
     * 引数.受付日時をthis.受付日時にセットする<BR>
     * @@param l_receivedDateTime  - 受付日時
     */
    public void setReceivedDateTime(Date l_receivedDateTime)
    {
        this.receivedDateTime = l_receivedDateTime;
    }

    /**
     * (get源泉徴収拘束金)<BR>
     * <BR>
     * 源泉徴収拘束金を取得する。<BR>
     * @@return double
     */
    public double getWithholdingTaxRestriction()
    {
        return withholdingTaxRestriction;
    }

    /**
     * (set源泉徴収拘束金)<BR>
     * <BR>
     * 源泉徴収拘束金をセットする。<BR>
     * @@param l_dblWithholdingTaxRestriction  - 源泉徴収拘束金
     */
    public void setWithholdingTaxRestriction(double l_dblWithholdingTaxRestriction)
    {
        withholdingTaxRestriction = l_dblWithholdingTaxRestriction;
    }

    /**
     * (get入金日)<BR>
     * <BR>
     * 入金日を取得する。<BR>
     * @@return java.util.Date
     */
    public Date getPaymentDate()
    {
        return paymentDate;
    }

    /**
     * (set入金日)<BR>
     * <BR>
     * 入金日をセットする。<BR>
     * @@param l_datPaymentDate  - 入金日
     */
    public void setPaymentDate(Date l_datPaymentDate)
    {
        paymentDate = l_datPaymentDate;
    }
    
    /**
     * (get約定数量) <BR>
     * <BR>
     * this.約定数量を返却する。<BR>
     * @@return double
     */
    public double getExecutedQuantity()
    {
        return this.executedQuantity;
    }
    
    /**
     * (set約定数量) <BR>
     * <BR>
     * 引数.約定数量を、this.約定数量にセットする。<BR>
     * @@param  l_strExecutedQuantity - (約定数量)
     */
    public void setExecutedQuantity(double l_dblExecutedQuantity)
    {
        this.executedQuantity = l_dblExecutedQuantity;
    }
    
    /**
     * (get注文約定区分)<BR> 
     * <BR>
     * this.注文約定区分を返却する。<BR>
     * @@return String
     */
    public String getOrderExecStatus()
    {
        return this.orderExecStatus;
    }
    
    /**
     * (set注文約定区分) <BR>
     * <BR>
     * 引数.注文約定区分を、this.注文約定区分にセットする。<BR>
     * @@param l_strOrderExecStatus - (注文約定区分)
     */
    public void setOrderExecStatus(String l_strOrderExecStatus)
    {
        this.orderExecStatus = l_strOrderExecStatus; 
    }
    
    /**
     * (get取引)<BR> 
     * <BR>
     * this.取引を返却する。<BR>
     * @@return String 
     */
    public String getDealType()
    {
        return this.dealType;
    }
    
    /**
     * (set取引)<BR> 
     * <BR>
     * 引数.取引を、this.取引にセットする。 <BR>
     * @@param l_strDealType - (取引)
     */
    public void setDealType(String l_strDealType)
    {
        this.dealType = l_strDealType;
    }

    /**
     * (create現注文内容)<BR>
     * <BR>
     * 現注文内容を作成する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_interceptor  注文インターセプト
     * @@param l_orderspec   注文内容
     * @@return WEB3TPNewOrderSpec  - 現注文内容
     */
    public static WEB3TPNewOrderSpec create(SubAccount l_subAccount, Object l_interceptor,
                                            Object l_orderspec)
    {
        try
        {
            //ThreadLocalSystemAttributesRegistryに、採番させないフラグを追加
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG, Boolean.TRUE);
            
            //現注文内容オブジェクトを生成
            WEB3TPNewOrderSpec l_newOrderSpec = toWEB3TPNewOrderSpec(l_subAccount, l_interceptor, l_orderspec);
    
            return l_newOrderSpec;
        }
        finally
        {
            //ThreadLocalSystemAttributesRegistryに、採番させないフラグを削除
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG, null);
        }
    }

    /**
     * (to株式現注文内容())<BR>
     * <BR>
     * 株式現注文内容に作成する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_interceptor  注文インターセプト
     * @@param l_orderspec   注文内容
     * @@return WEB3TPNewOrderSpec  - 現注文内容
     */
    private static WEB3TPNewOrderSpec toWEB3EqtypeTPNewOrderSpec(SubAccount l_subAccount,
        Object l_interceptor,
        Object l_orderspec)
    {
        final String STR_METHOD_NAME = "WEB3TPNewOrderSpec.toWEB3EqtypeTPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";

        try
        {
            WEB3TPNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3TPNewOrderSpec();
            EqtypeOrderUnitParams l_orderUnitParams = new EqtypeOrderUnitParams();

            //現物新規注文の場合
            if (l_orderspec instanceof EqTypeNewCashBasedOrderSpec)
            {
                EqTypeNewCashBasedOrderSpec l_eqOrderspec = (EqTypeNewCashBasedOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                if (l_eqOrderspec.isBuyOrder())
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
                }
                else
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
                }
                l_orderUnitParams.setQuantity(l_eqOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_eqOrderspec.getTaxType());
                //Y00125：割増拘束率対応
                l_orderUnitParams.setLimitPrice(l_eqOrderspec.getLimitPrice());
                
                //銘柄属性作成
                TradedProduct l_tp = getEqtypeTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //劉修正 add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            //ミニ株新規注文の場合
            else if (l_orderspec instanceof EqTypeNewMiniStockOrderSpec)
            {
                EqTypeNewMiniStockOrderSpec l_eqOrderspec = (EqTypeNewMiniStockOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                if (l_eqOrderspec.isBuyOrder())
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.MINI_STOCK_BUY);
                }
                else
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.MINI_STOCK_SELL);
                }
                l_orderUnitParams.setQuantity(l_eqOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_eqOrderspec.getTaxType());

                //銘柄属性作成
                TradedProduct l_tp = getEqtypeTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());

                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            //信用新規建注文の場合
            else if (l_orderspec instanceof EqTypeOpenContractOrderSpec)
            {
                EqTypeOpenContractOrderSpec l_eqOrderspec = (EqTypeOpenContractOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
                if (l_eqOrderspec.isLongOrder())
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
                }
                else
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.MARGIN_SHORT);
                }
                l_orderUnitParams.setQuantity(l_eqOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_eqOrderspec.getTaxType());
                //Y00125：割増拘束率対応
                l_orderUnitParams.setLimitPrice(l_eqOrderspec.getLimitPrice());

                //銘柄属性作成
                TradedProduct l_tp = getEqtypeTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //劉修正 add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER,
                    l_orderUnitParams);

            }
            //信用新規返済注文の場合
            else if (l_orderspec instanceof EqTypeSettleContractOrderSpec)
            {
                EqTypeSettleContractOrderSpec l_eqOrderspec = (
                    EqTypeSettleContractOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
                String l_tmName = TradingModuleImpl.TRADING_MODULE_NAME;
                PositionManager l_eqposMgr = (PositionManager) (GtlUtils.
                    getTradingModule(
                    l_tmName).getPositionManager());
                Contract l_contract = l_eqposMgr.getContract(l_eqOrderspec.
                    getSettleContractOrderEntries()[0].getContractId());
                OrderTypeEnum orderType = l_contract.isLong() ?
                    OrderTypeEnum.CLOSE_MARGIN_LONG : OrderTypeEnum.CLOSE_MARGIN_SHORT;
                l_orderUnitParams.setOrderType(orderType);
                l_orderUnitParams.setQuantity(l_eqOrderspec.getTotalQuantity());
                l_orderUnitParams.setTaxType(l_eqOrderspec.getTaxType());

                //銘柄属性作成
                TradedProduct l_tp = l_contract.getTradedProduct();
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());

                //劉修正 add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER,
                    l_orderUnitParams);

                //返済指定情報
                EqTypeSettleContractOrderEntry l_entries[] = l_eqOrderspec.
                    getSettleContractOrderEntries();
                WEB3TPContractSettleSpecify[] l_contractSettleSpecify = new
                    WEB3TPContractSettleSpecify[l_entries.length];

                for (int i = 0; i < l_entries.length; i++)
                {
                    l_contractSettleSpecify[i] = new WEB3TPContractSettleSpecify();
                    l_contractSettleSpecify[i].setContractId(l_entries[i].getContractId());
                    l_contractSettleSpecify[i].setQuantity(l_entries[i].getQuantity());
                }
                l_newOrderSpec.setContractSettleSpecify(l_contractSettleSpecify);

            }
            //信用新規現引現渡注文の場合
            else if (l_orderspec instanceof EqTypeSwapContractOrderSpec)
            {
                EqTypeSwapContractOrderSpec l_eqOrderspec = (
                    EqTypeSwapContractOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
                EqTypePositionManager l_eqposMgr = Utils.getPositionManager();
                Contract l_contract = l_eqposMgr.getContract(l_eqOrderspec.
                    getSettleContractOrderEntries()[0].getContractId());
                OrderTypeEnum orderType = l_contract.isLong() ?
                    OrderTypeEnum.SWAP_MARGIN_LONG : OrderTypeEnum.SWAP_MARGIN_SHORT;
                l_orderUnitParams.setOrderType(orderType);
                l_orderUnitParams.setQuantity(l_eqOrderspec.getTotalQuantity());
                l_orderUnitParams.setTaxType(l_eqOrderspec.getTaxType());

                //銘柄属性作成
                TradedProduct l_tp = l_contract.getTradedProduct();
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());

                //劉修正 add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_SWAP_CONTRACT_ORDER,
                    l_orderUnitParams);

                EqTypeSettleContractOrderEntry l_entries[] = l_eqOrderspec.
                    getSettleContractOrderEntries();
                WEB3TPContractSettleSpecify[] l_contractSettleSpecify = new
                    WEB3TPContractSettleSpecify[l_entries.length];

                for (int i = 0; i < l_entries.length; i++)
                {
                    l_contractSettleSpecify[i] = new WEB3TPContractSettleSpecify();
                    l_contractSettleSpecify[i].setContractId(l_entries[i].getContractId());
                    l_contractSettleSpecify[i].setQuantity(l_entries[i].getQuantity());
                }
                l_newOrderSpec.setContractSettleSpecify(l_contractSettleSpecify);

            }
            //現物注文訂正の場合
            else if (l_orderspec instanceof EqTypeChangeOrderSpec)
            {
                EqTypeChangeOrderSpec l_eqChangeOrderspec = (EqTypeChangeOrderSpec)
                    l_orderspec;
                EqtypeOrderRow orderRow = EqtypeOrderDao.findRowByPk(l_eqChangeOrderspec.
                    getOrderId());
                List orderUnits = EqtypeOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new EqtypeOrderUnitParams( (
                    EqtypeOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(l_eqChangeOrderspec.
                                              getChangeOrderUnitEntries()[0].
                                              getAfterChangeOriginalQuantity());
                l_orderUnitParams.setPrice(l_eqChangeOrderspec.getChangeOrderUnitEntries()[
                                           0].getAfterChangePrice());

                //Y00125：割増拘束率対応
                l_orderUnitParams.setLimitPrice(l_eqChangeOrderspec
                    .getChangeOrderUnitEntries()[0].getAfterChangePrice());

                
                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            //信用返済注文訂正の場合
            else if (l_orderspec instanceof EqTypeChangeSettleContractOrderSpec)
            {
                EqTypeChangeSettleContractOrderSpec l_eqChangeOrderspec = (
                    EqTypeChangeSettleContractOrderSpec)
                    l_orderspec;
                EqtypeOrderRow orderRow = EqtypeOrderDao.findRowByPk(l_eqChangeOrderspec.
                    getOrderId());
                List orderUnits = EqtypeOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new EqtypeOrderUnitParams( (
                    EqtypeOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(l_eqChangeOrderspec.
                                              getChangeOrderUnitEntries()[0].
                                              getAfterChangeTotalQuantity());
                l_orderUnitParams.setPrice(l_eqChangeOrderspec.getChangeOrderUnitEntries()[
                                           0].getAfterChangePrice());

                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

                //返済指定情報
                EqTypeContractSettleChangeOrderUnitEntry l_orderUnitEntry =
                    l_eqChangeOrderspec.getChangeOrderUnitEntries()[0];
                EqTypeSettleContractOrderEntry[] l_entries = l_orderUnitEntry.
                    getAfterChangeSettleContractOrderEntries();
                WEB3TPContractSettleSpecify[] l_contractSettleSpecify = new
                    WEB3TPContractSettleSpecify[l_entries.length];
                for (int i = 0; i < l_entries.length; i++)
                {
                    l_contractSettleSpecify[i] = new WEB3TPContractSettleSpecify();
                    l_contractSettleSpecify[i].setContractId(l_entries[i].getContractId());
                    l_contractSettleSpecify[i].setQuantity(l_entries[i].getQuantity());
                }
                l_newOrderSpec.setContractSettleSpecify(l_contractSettleSpecify);

            }
            //信用現引現渡注文訂正の場合
            else if (l_orderspec instanceof EqTypeChangeSwapContractOrderSpec)
            {
                EqTypeChangeSwapContractOrderSpec l_eqChangeOrderspec = (
                    EqTypeChangeSwapContractOrderSpec)
                    l_orderspec;
                EqtypeOrderRow orderRow = EqtypeOrderDao.findRowByPk(l_eqChangeOrderspec.
                    getOrderId());
                List orderUnits = EqtypeOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new EqtypeOrderUnitParams( (
                    EqtypeOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(l_eqChangeOrderspec.
                                              getChangeOrderUnitEntries()[0].
                                              getAfterChangeTotalQuantity());

                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

                //返済指定情報
                EqTypeContractSwapChangeOrderUnitEntry l_orderUnitEntry =
                    l_eqChangeOrderspec.getChangeOrderUnitEntries()[0];
                EqTypeSettleContractOrderEntry[] l_entries = l_orderUnitEntry.
                    getAfterChangeSettleContractOrderEntries();
                WEB3TPContractSettleSpecify[] l_contractSettleSpecify = new
                    WEB3TPContractSettleSpecify[l_entries.length];
                for (int i = 0; i < l_entries.length; i++)
                {
                    l_contractSettleSpecify[i] = new WEB3TPContractSettleSpecify();
                    l_contractSettleSpecify[i].setContractId(l_entries[i].getContractId());
                    l_contractSettleSpecify[i].setQuantity(l_entries[i].getQuantity());
                }
                l_newOrderSpec.setContractSettleSpecify(l_contractSettleSpecify);

            }
            //注文取消の場合
            else if (l_orderspec instanceof EqTypeCancelOrderSpec)
            {
                EqTypeCancelOrderSpec l_eqCancelOrderspec = (EqTypeCancelOrderSpec)
                    l_orderspec;
                EqtypeOrderRow orderRow = EqtypeOrderDao.findRowByPk(l_eqCancelOrderspec.
                    getOrderId());
                List orderUnits = EqtypeOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new EqtypeOrderUnitParams( (
                    EqtypeOrderUnitRow) orderUnits.
                    get(0));


                //概算代金などカスタマイズ属性作成
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);
                    
                //注文属性作成
                l_orderUnitParams.setQuantity(0);
                l_orderUnitParams.setEstimatedPrice(0.0);
                l_orderUnitParams.setCapitalGain(0.0);
                l_orderUnitParams.setPrice(0.0);

            }
            //そのたの場合、未対応ため異常発生
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //現注文属性を設定する
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());

            l_newOrderSpec.setOrderId(l_orderUnitParams.getOrderId());
            l_newOrderSpec.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            l_newOrderSpec.setOrderCategory(l_orderUnitParams.getOrderCateg());
            l_newOrderSpec.setOrderType(l_orderUnitParams.getOrderType());
            l_newOrderSpec.setQuantity(l_orderUnitParams.getQuantity());
            l_newOrderSpec.setTaxType(l_orderUnitParams.getTaxType());
            l_newOrderSpec.setProductId(l_orderUnitParams.getProductId());
            l_newOrderSpec.setProductType(l_orderUnitParams.getProductType());
            l_newOrderSpec.setMarketId(l_orderUnitParams.getMarketId());
            l_newOrderSpec.setOrderBizDate(WEB3DateUtility.getDate(l_orderUnitParams.
                getBizDate(),
                "yyyyMMdd"));
            l_newOrderSpec.setDeliveryDate(l_orderUnitParams.getDeliveryDate());
            l_newOrderSpec.setPrice(l_orderUnitParams.getPrice());
            //Y00125：割増拘束率対応
            l_newOrderSpec.setLimitPrice(l_orderUnitParams.getLimitPrice());
            l_newOrderSpec.setEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            l_newOrderSpec.setCapitaGain(l_orderUnitParams.getCapitalGain());
            return l_newOrderSpec;
        }
        catch (NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME, de.getMessage(), de);
        }
        catch (DataException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, de.getMessage(), de);
        }

    }

    /**
     * (to累積投資現注文内容())<BR>
     * <BR>
     * 累積投資現注文内容に作成する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_interceptor  注文インターセプト
     * @@param l_orderspec   注文内容
     * @@return WEB3TPNewOrderSpec  - 現注文内容
     */
    private static WEB3TPNewOrderSpec toWEB3RuitoTPNewOrderSpec(SubAccount
        l_subAccount,
        Object l_interceptor,
        Object l_orderspec)
    {
        final String STR_METHOD_NAME = "WEB3TPNewOrderSpec.toWEB3RuitoTPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";
        try
        {
            WEB3TPNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3TPNewOrderSpec();
            RuitoOrderUnitParams l_orderUnitParams = new RuitoOrderUnitParams();

            //累積投資新規注文の場合
            if (l_orderspec instanceof RuitoNewOrderSpec)
            {
                RuitoNewOrderSpec l_eqOrderspec = (RuitoNewOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                if (l_eqOrderspec.isBuyOrder())
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.RUITO_BUY);
                }
                else
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.RUITO_SELL);
                }
                l_orderUnitParams.setQuantity(l_eqOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_eqOrderspec.getTaxType());

                //銘柄属性作成
                TradedProduct l_tp = getRuitoTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode(),
                    l_eqOrderspec.getIssueCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //劉修正 add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //概算代金などカスタマイズ属性作成
                RuitoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (RuitoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            //累積投資注文取消の場合
            else if (l_orderspec instanceof CancelOrderSpec &&
                     l_interceptor instanceof
                     RuitoOrderManagerPersistenceEventInterceptor)
            {
                CancelOrderSpec l_eqCancelOrderspec = (CancelOrderSpec)
                    l_orderspec;
                RuitoOrderRow orderRow = RuitoOrderDao.findRowByPk(l_eqCancelOrderspec.
                    getOrderId());
                List orderUnits = RuitoOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new RuitoOrderUnitParams( (
                    RuitoOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(0);

                //概算代金などカスタマイズ属性作成
                RuitoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (RuitoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            //そのたの場合、未対応ため異常発生
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //現注文属性を設定する
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());

            l_newOrderSpec.setOrderId(l_orderUnitParams.getOrderId());
            l_newOrderSpec.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            l_newOrderSpec.setOrderCategory(l_orderUnitParams.getOrderCateg());
            l_newOrderSpec.setOrderType(l_orderUnitParams.getOrderType());
            l_newOrderSpec.setQuantity(l_orderUnitParams.getQuantity());
            l_newOrderSpec.setTaxType(l_orderUnitParams.getTaxType());
            l_newOrderSpec.setProductId(l_orderUnitParams.getProductId());
            l_newOrderSpec.setProductType(l_orderUnitParams.getProductType());
            l_newOrderSpec.setMarketId(l_orderUnitParams.getMarketId());
            l_newOrderSpec.setOrderBizDate(WEB3DateUtility.getDate(l_orderUnitParams.
                getBizDate(),
                "yyyyMMdd"));
            l_newOrderSpec.setDeliveryDate(l_orderUnitParams.getDeliveryDate());
            l_newOrderSpec.setPrice(1.0);
            l_newOrderSpec.setEstimatedPrice(l_orderUnitParams.getQuantity());
            l_newOrderSpec.setCapitaGain(0.0);
            return l_newOrderSpec;
        }
        catch (NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME, de.getMessage(), de);
        }
        catch (DataException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, de.getMessage(), de);
        }
    }

    /**
     * (to投資信託現注文内容())<BR>
     * <BR>
     * 投資信託現注文内容に作成する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_interceptor  注文インターセプト
     * @@param l_orderspec   注文内容
     * @@return WEB3TPNewOrderSpec  - 現注文内容
     */
    private static WEB3TPNewOrderSpec toWEB3MutualFundTPNewOrderSpec(SubAccount
        l_subAccount,
        Object l_interceptor,
        Object l_orderspec)
    {
        final String STR_METHOD_NAME = "WEB3TPNewOrderSpec.toWEB3MutualFundTPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";
        try
        {
            WEB3TPNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3TPNewOrderSpec();
            MutualFundOrderUnitParams l_orderUnitParams = new MutualFundOrderUnitParams();

            //投資信託新規注文の場合
            if (l_orderspec instanceof MutualFundNewOrderSpec)
            {
                MutualFundNewOrderSpec l_eqOrderspec = (MutualFundNewOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                if (l_eqOrderspec.isBuyOrder())
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.MF_BUY);
                }
                else
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
                }
                l_orderUnitParams.setQuantity(l_eqOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_eqOrderspec.getTaxType());

                //銘柄属性作成
                TradedProduct l_tp = getMutualFundTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode(),
                    l_eqOrderspec.getIssueCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //劉修正 add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //概算代金などカスタマイズ属性作成
                MutualFundOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (MutualFundOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            //投資信託注文取消の場合
            else if (l_orderspec instanceof CancelOrderSpec &&
                     l_interceptor instanceof
                     MutualFundOrderManagerPersistenceEventInterceptor)
            {
                CancelOrderSpec l_eqCancelOrderspec = (CancelOrderSpec)
                    l_orderspec;
                MutualFundOrderRow orderRow = MutualFundOrderDao.findRowByPk(
                    l_eqCancelOrderspec.
                    getOrderId());
                List orderUnits = MutualFundOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new MutualFundOrderUnitParams( (
                    MutualFundOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(0);
                l_orderUnitParams.setEstimatedPrice(0.0);

                //概算代金などカスタマイズ属性作成
                MutualFundOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (MutualFundOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            //そのたの場合、未対応ため異常発生
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //現注文属性を設定する
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());

            l_newOrderSpec.setOrderId(l_orderUnitParams.getOrderId());
            l_newOrderSpec.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            l_newOrderSpec.setOrderCategory(l_orderUnitParams.getOrderCateg());
            l_newOrderSpec.setOrderType(l_orderUnitParams.getOrderType());
            l_newOrderSpec.setQuantity(l_orderUnitParams.getQuantity());
            l_newOrderSpec.setTaxType(l_orderUnitParams.getTaxType());
            l_newOrderSpec.setProductId(l_orderUnitParams.getProductId());
            l_newOrderSpec.setProductType(l_orderUnitParams.getProductType());
            l_newOrderSpec.setMarketId(l_orderUnitParams.getMarketId());
            l_newOrderSpec.setOrderBizDate(WEB3DateUtility.getDate(l_orderUnitParams.
                getBizDate(),
                "yyyyMMdd"));
            l_newOrderSpec.setDeliveryDate(l_orderUnitParams.getDeliveryDate());
            l_newOrderSpec.setPrice(1.0);
            l_newOrderSpec.setEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            l_newOrderSpec.setCapitaGain(0.0);
            l_newOrderSpec.setWithholdingTaxRestriction(l_orderUnitParams.getWithholdingTaxRestriction());
            l_newOrderSpec.setPaymentDate(l_orderUnitParams.getPaymentDate());
            return l_newOrderSpec;
        }
        catch (NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME, de.getMessage(), de);
        }
        catch (DataException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, de.getMessage(), de);
        }

    }

    /**
     * (to振替現注文内容())<BR>
     * <BR>
     * 振替現注文内容に作成する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_interceptor  注文インターセプト
     * @@param l_orderspec   注文内容
     * @@return WEB3TPNewOrderSpec  - 現注文内容
     */
    private static WEB3TPNewOrderSpec toWEB3AioTPNewOrderSpec(SubAccount
        l_subAccount,
        Object l_interceptor,
        Object l_orderspec)
    {
        final String STR_METHOD_NAME = "WEB3TPNewOrderSpec.toWEB3AioTPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";
        try
        {
            WEB3TPNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3TPNewOrderSpec();
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams();

            SubAccount l_tmpSubAccount = l_subAccount;

            //振替新規注文の場合
            if (l_orderspec instanceof AioNewOrderSpec)
            {
                AioNewOrderSpec l_aioOrderspec = (AioNewOrderSpec)
                    l_orderspec;
                
                //注文種別
                OrderTypeEnum l_orderType = l_aioOrderspec.getOrderTypeEnum();
                //振替タイプ
                AssetTransferTypeEnum l_assetType = l_aioOrderspec.getAssetTransferTypeEnum();

                //注文種別＝1007：振替注文（預り金から株先証拠金）の場合
                if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderType))
                {
                    //振替タイプ=1：入金の場合
                    if (AssetTransferTypeEnum.CASH_IN.equals(l_assetType))
                    {
                        //補助口座（７：株式オプション取引口座（先物証拠金）を取得
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                    }
                    //以外（振替タイプ=2：出金）の場合
                    else
                    {
                        //補助口座（1：株式取引口座（預り金）を取得
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }
                }
                //注文種別＝1009：証券振替注文（保護預りから代用有価証券）の場合
                else if (OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.equals(l_orderType))
                {
                    //振替タイプ=1：入金の場合
                    if (AssetTransferTypeEnum.CASH_IN.equals(l_assetType))
                    {
                        //補助口座（2：株式信用取引口座（保証金）を取得
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    }
                    //以外（振替タイプ=2：出金）の場合
                    else
                    {
                        //補助口座（1：株式取引口座（預り金）を取得
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }
                }
                //注文種別＝1020：振替注文（預り金からオリックスクレジット）の場合
                else if (OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderType))
                {
                    //振替タイプ=2：出金の場合
                    if (AssetTransferTypeEnum.CASH_OUT.equals(l_assetType))
                    {
                        //補助口座（1：株式取引口座（預り金）を取得
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }
                }

                //顧客属性
                l_orderUnitParams.setAccountId(l_tmpSubAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_tmpSubAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_tmpSubAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.ASSET_TRANSFER);
                l_orderUnitParams.setOrderType(l_aioOrderspec.getOrderTypeEnum());
                l_orderUnitParams.setQuantity(l_aioOrderspec.getQuantity());

                //銘柄属性作成
                l_orderUnitParams.setProductId(l_aioOrderspec.getProductId());
                ProductRow l_productRow = ProductDao.findRowByProductId(l_aioOrderspec.
                    getProductId());
                l_orderUnitParams.setProductType(l_productRow.getProductType());
                Date l_bizDate = GtlUtils.getTradingSystem().getBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.
                                             getThreadSafeYYYYMMDDSimpleDateFormat().
                                             format(l_bizDate));
                l_orderUnitParams.setDeliveryDate(getAioDeliveryDate(l_subAccount,
                    l_bizDate));

                //概算代金などカスタマイズ属性作成
                AioOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (AioOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_ASSET_TRANSFER_ORDER,
                    l_orderUnitParams);

            }
            //振替注文(入出金（１レコード）のみ、振替（２レコード）は取消処理がない)取消の場合
            else if (l_orderspec instanceof CancelOrderSpec &&
                     l_interceptor instanceof
                     AioOrderManagerPersistenceEventInterceptor)
            {
                CancelOrderSpec l_eqCancelOrderspec = (CancelOrderSpec)
                    l_orderspec;
                AioOrderRow orderRow = AioOrderDao.findRowByPk(
                    l_eqCancelOrderspec.
                    getOrderId());
                List orderUnits = AioOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new AioOrderUnitParams( (
                    AioOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(0);

                //概算代金などカスタマイズ属性作成
                AioOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (AioOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            //そのたの場合、未対応ため異常発生
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //現注文属性を設定する
            l_newOrderSpec.setSubAccountId(l_tmpSubAccount.getSubAccountId());
            l_newOrderSpec.setSubAccountType(l_tmpSubAccount.getSubAccountType());

            l_newOrderSpec.setOrderId(l_orderUnitParams.getOrderId());
            l_newOrderSpec.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            l_newOrderSpec.setOrderCategory(l_orderUnitParams.getOrderCateg());
            l_newOrderSpec.setOrderType(l_orderUnitParams.getOrderType());
            l_newOrderSpec.setQuantity(l_orderUnitParams.getQuantity());
            l_newOrderSpec.setEstimatedPrice(l_orderUnitParams.getQuantity());
            l_newOrderSpec.setProductId(l_orderUnitParams.getProductId());
            l_newOrderSpec.setProductType(l_orderUnitParams.getProductType());
            l_newOrderSpec.setOrderBizDate(WEB3DateUtility.getDate(l_orderUnitParams.
                getBizDate(),
                "yyyyMMdd"));
            l_newOrderSpec.setDeliveryDate(l_orderUnitParams.getDeliveryDate());
            l_newOrderSpec.setPrice(1.0);
            l_newOrderSpec.setCapitaGain(0.0);
            l_newOrderSpec.setPaymentApplicationDiv(l_orderUnitParams.getPaymentApplicationDiv());
            l_newOrderSpec.setReceivedDateTime(l_orderUnitParams.getReceivedDateTime());

            return l_newOrderSpec;
        }
        catch (DataException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, de.getMessage(), de);
        } 
        catch (NotFoundException nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME, nfe.getMessage(), nfe);
        }
    }

    /**
     * (to先物オプション現注文内容())<BR>
     * <BR>
     * 先物オプション現注文内容に作成する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_interceptor  注文インターセプト
     * @@param l_orderspec   注文内容
     * @@return WEB3TPNewOrderSpec  - 現注文内容
     */
    private static WEB3TPNewOrderSpec toWEB3IFOTPNewOrderSpec(SubAccount
        l_subAccount,
        Object l_interceptor,
        Object l_orderspec)
    {
        final String STR_METHOD_NAME = "WEB3TPNewOrderSpec.toWEB3IFOTPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";
        try
        {
            WEB3TPNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3TPNewOrderSpec();
            IfoOrderUnitParams l_orderUnitParams = new IfoOrderUnitParams();

            //先物オプション新規建注文の場合
            if (l_orderspec instanceof IfoOpenContractOrderSpec)
            {
                IfoOpenContractOrderSpec l_ifoOrderspec = (IfoOpenContractOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                OrderCategEnum orderCateg = IfoDerivativeTypeEnum.FUTURES.equals(
                    l_ifoOrderspec.
                    getDerivativeType()) ? OrderCategEnum.IDX_FUTURES_OPEN :
                    OrderCategEnum.IDX_OPTIONS_OPEN;
                l_orderUnitParams.setOrderCateg(orderCateg);
                l_orderUnitParams.setOrderType(getIFOOrderTypeEnum(l_ifoOrderspec));
                l_orderUnitParams.setQuantity(l_ifoOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_ifoOrderspec.getTaxType());

                //銘柄属性作成
                TradedProduct l_tp = getIFOTradedProduct(
                    l_subAccount,
                    l_ifoOrderspec.getUnderlyingProductCode(),
                    l_ifoOrderspec.getDerivativeType(),
                    l_ifoOrderspec.getMonthOfDelivery(),
                    l_ifoOrderspec.getStrikePrice(),
                    l_ifoOrderspec.getMarketCode());

                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //劉修正 add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //概算代金などカスタマイズ属性作成
                IfoOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER,
                    l_orderUnitParams);

            }
            //先物オプション新規返済注文の場合
            else if (l_orderspec instanceof IfoSettleContractOrderSpec)
            {
                IfoSettleContractOrderSpec l_ifoOrderspec = (IfoSettleContractOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                String l_tmName = IfoTradingModuleImpl.TRADING_MODULE_NAME;
                PositionManager l_ifoPosMgr = (PositionManager) (GtlUtils.
                    getTradingModule(
                    l_tmName).getPositionManager());
                Contract l_firstContract = l_ifoPosMgr.getContract(l_ifoOrderspec.
                    getSettleContractEntries()[0].getContractId());
                IfoTradedProduct l_tp = (IfoTradedProduct) l_firstContract.
                    getTradedProduct();

                boolean isFuturesOrder = IfoDerivativeTypeEnum.FUTURES.equals( ( (
                    IfoProduct) l_tp.getProduct()).getDerivativeType());

                OrderTypeEnum l_orderType;
                OrderCategEnum l_orderCateg;
                if (isFuturesOrder)
                {
                    if (l_firstContract.isLong())
                    {
                        l_orderType = OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE;
                    }
                    else
                    {
                        l_orderType = OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE;
                    }
                    l_orderCateg = OrderCategEnum.IDX_FUTURES_CLOSE;
                }
                else
                {
                    if (l_firstContract.isLong())
                    {
                        l_orderType = OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE;
                    }
                    else
                    {
                        l_orderType = OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE;
                    }
                    l_orderCateg = OrderCategEnum.IDX_OPTIONS_CLOSE;
                }
                l_orderUnitParams.setOrderCateg(l_orderCateg);
                l_orderUnitParams.setOrderType(l_orderType);

                //銘柄属性作成
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());

                l_orderUnitParams.setQuantity(l_ifoOrderspec.getTotalQuantity());
                //概算代金などカスタマイズ属性作成
                IfoOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER,
                    l_orderUnitParams);

                //返済指定情報
                SettleContractEntry l_entries[] = l_ifoOrderspec.
                    getSettleContractEntries();
                WEB3TPContractSettleSpecify[] l_contractSettleSpecify = new
                    WEB3TPContractSettleSpecify[l_entries.length];

                for (int i = 0; i < l_entries.length; i++)
                {
                    l_contractSettleSpecify[i] = new WEB3TPContractSettleSpecify();
                    l_contractSettleSpecify[i].setContractId(l_entries[i].getContractId());
                    l_contractSettleSpecify[i].setQuantity(l_entries[i].getQuantity());
                }
                l_newOrderSpec.setContractSettleSpecify(l_contractSettleSpecify);

            }
            //先物オプション新規建注文訂正の場合
            else if (l_orderspec instanceof IfoChangeOpenContractOrderSpec)
            {
                IfoChangeOpenContractOrderSpec l_eqChangeOrderspec = (
                    IfoChangeOpenContractOrderSpec)
                    l_orderspec;
                IfoOrderRow orderRow = IfoOrderDao.findRowByPk(l_eqChangeOrderspec.
                    getOrderId());
                List orderUnits = IfoOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new IfoOrderUnitParams( (
                    IfoOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(l_eqChangeOrderspec.
                                              getAfterChangeOriginalQuantity());
                l_orderUnitParams.setPrice(l_eqChangeOrderspec.getAfterChangePrice());

                //概算代金などカスタマイズ属性作成
                IfoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);
            }
            //先物オプション返済注文訂正の場合
            else if (l_orderspec instanceof IfoChangeSettleContractOrderSpec)
            {
                IfoChangeSettleContractOrderSpec l_changeOrderspec = (
                    IfoChangeSettleContractOrderSpec)
                    l_orderspec;
                IfoOrderRow orderRow = IfoOrderDao.findRowByPk(l_changeOrderspec.
                    getOrderId());
                List orderUnits = IfoOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new IfoOrderUnitParams( (
                    IfoOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(l_changeOrderspec.
                                              getAfterChangeTotalQuantity());

                //概算代金などカスタマイズ属性作成
                IfoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

                //返済指定情報
                SettleContractEntry l_entries[] = l_changeOrderspec.
                    getAfterChangeSettleContractEntries();
                WEB3TPContractSettleSpecify[] l_contractSettleSpecify = new
                    WEB3TPContractSettleSpecify[l_entries.length];

                for (int i = 0; i < l_entries.length; i++)
                {
                    l_contractSettleSpecify[i] = new WEB3TPContractSettleSpecify();
                    l_contractSettleSpecify[i].setContractId(l_entries[i].getContractId());
                    l_contractSettleSpecify[i].setQuantity(l_entries[i].getQuantity());
                }
                l_newOrderSpec.setContractSettleSpecify(l_contractSettleSpecify);

            }
            //先物オプション注文取消の場合
            else if (l_orderspec instanceof CancelOrderSpec &&
                     l_interceptor instanceof
                     IfoOrderManagerPersistenceEventInterceptor)
            {
                CancelOrderSpec l_eqCancelOrderspec = (CancelOrderSpec)
                    l_orderspec;
                IfoOrderRow orderRow = IfoOrderDao.findRowByPk(
                    l_eqCancelOrderspec.
                    getOrderId());
                List orderUnits = IfoOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new IfoOrderUnitParams( (
                    IfoOrderUnitRow) orderUnits.
                    get(0));

                //概算代金などカスタマイズ属性作成
                IfoOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);

                //注文属性作成
                l_orderUnitParams.setQuantity(0);
                l_orderUnitParams.setPrice(0.0);
                l_orderUnitParams.setEstimatedPrice(0.0);

            }
            //そのたの場合、未対応ため異常発生
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //現注文属性を設定する
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());

            l_newOrderSpec.setOrderId(l_orderUnitParams.getOrderId());
            l_newOrderSpec.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            l_newOrderSpec.setOrderCategory(l_orderUnitParams.getOrderCateg());
            l_newOrderSpec.setOrderType(l_orderUnitParams.getOrderType());
            l_newOrderSpec.setQuantity(l_orderUnitParams.getQuantity());
            l_newOrderSpec.setTaxType(l_orderUnitParams.getTaxType());
            l_newOrderSpec.setProductId(l_orderUnitParams.getProductId());
            l_newOrderSpec.setProductType(l_orderUnitParams.getProductType());
            l_newOrderSpec.setMarketId(l_orderUnitParams.getMarketId());
            l_newOrderSpec.setOrderBizDate(WEB3DateUtility.getDate(l_orderUnitParams.
                getBizDate(),
                "yyyyMMdd"));
            l_newOrderSpec.setDeliveryDate(l_orderUnitParams.getDeliveryDate());
            l_newOrderSpec.setPrice(l_orderUnitParams.getPrice());
            l_newOrderSpec.setEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            l_newOrderSpec.setCapitaGain(0.0);
            return l_newOrderSpec;
        }
        catch (NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME, de.getMessage(), de);
        }
        catch (DataException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, de.getMessage(), de);
        }

    }
    /**
     * (to外株現注文内容())<BR>
     * <BR>
     * 外株注文内容に作成する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_interceptor  注文インターセプト
     * @@param l_orderspec   注文内容
     * @@return WEB3TPNewOrderSpec  - 現注文内容
     */
    private static WEB3TPNewOrderSpec toWEB3FeqTPNewOrderSpec(SubAccount l_subAccount, Object l_interceptor, Object l_orderspec) {

        final String STR_METHOD_NAME = "WEB3TPNewOrderSpec.toWEB3FeqTPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";
        
        log.entering(STR_METHOD_NAME);
                
        try
        {
            WEB3TPNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3TPNewOrderSpec();
            FeqOrderUnitParams l_orderUnitParams = new FeqOrderUnitParams();

            //外株買の場合
            if (l_orderspec instanceof FeqNewOrderSpec)
            {
                FeqNewOrderSpec l_feqOrderspec = (FeqNewOrderSpec)
                    l_orderspec;

                //顧客属性
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //注文属性作成
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                if (l_feqOrderspec.isBuyOrder())
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
                }
                else
                {
                    l_orderUnitParams.setOrderType(OrderTypeEnum.FEQ_SELL);
                }
                l_orderUnitParams.setQuantity(l_feqOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_feqOrderspec.getTaxType());
                l_orderUnitParams.setLimitPrice(l_feqOrderspec.getLimitPrice());
                
                
                //銘柄属性作成
                TradedProduct l_tp = getFeqTradedProduct(l_subAccount,
                        l_feqOrderspec.getProductCode(),
                		l_feqOrderspec.getMarketCode());
                
                Product l_p = l_tp.getProduct();
                                
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                
                //概算代金などカスタマイズ属性作成
                FeqOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (FeqOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    null,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            
            //外株　@訂正の場合
            else if (l_orderspec instanceof FeqChangeOrderSpec)
            {
                FeqChangeOrderSpec l_feqChangeOrderspec = (FeqChangeOrderSpec)
                    l_orderspec;
                FeqOrderRow orderRow = FeqOrderDao.findRowByPk(l_feqChangeOrderspec.
                    getOrderId());
                List orderUnits = FeqOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new FeqOrderUnitParams( (
                    FeqOrderUnitRow) orderUnits.
                    get(0));

                //注文属性作成
                l_orderUnitParams.setQuantity(l_feqChangeOrderspec.getAfterChangeOriginalQuantity());
                l_orderUnitParams.setPrice(l_feqChangeOrderspec.getAfterChangePrice());
                
                //Y00125：割増拘束率対応
                l_orderUnitParams.setLimitPrice(l_feqChangeOrderspec.getAfterChangePrice());
                                
                //概算代金などカスタマイズ属性作成
                FeqOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (FeqOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    null,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            
            //外株　@注文取消の場合
            else if (l_orderspec instanceof CancelOrderSpec)
            {
                CancelOrderSpec l_feqCancelOrderspec = (CancelOrderSpec)
                    l_orderspec;
                FeqOrderRow orderRow = FeqOrderDao.findRowByPk(l_feqCancelOrderspec.
                    getOrderId());
                List orderUnits = FeqOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new FeqOrderUnitParams( (
                    FeqOrderUnitRow) orderUnits.
                    get(0));


                //概算代金などカスタマイズ属性作成
                FeqOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (FeqOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    null,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);
                                    
                //注文属性作成
                l_orderUnitParams.setQuantity(0);
                l_orderUnitParams.setEstimatedPrice(0.0);
                l_orderUnitParams.setCapitalGain(0.0);
                l_orderUnitParams.setPrice(0.0);

            }
            //そのたの場合、未対応ため異常発生
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }
            
            //現注文属性を設定する
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());

            l_newOrderSpec.setOrderId(l_orderUnitParams.getOrderId());
            l_newOrderSpec.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            l_newOrderSpec.setOrderCategory(l_orderUnitParams.getOrderCateg());
            l_newOrderSpec.setOrderType(l_orderUnitParams.getOrderType());
            l_newOrderSpec.setQuantity(l_orderUnitParams.getQuantity());
            l_newOrderSpec.setTaxType(l_orderUnitParams.getTaxType());
            l_newOrderSpec.setProductId(l_orderUnitParams.getProductId());
            l_newOrderSpec.setProductType(l_orderUnitParams.getProductType());
            l_newOrderSpec.setMarketId(l_orderUnitParams.getMarketId());
            l_newOrderSpec.setOrderBizDate(WEB3DateUtility.getDate(l_orderUnitParams.
                getBizDate(),
                "yyyyMMdd"));
            l_newOrderSpec.setDeliveryDate(l_orderUnitParams.getDeliveryDate());
            l_newOrderSpec.setPrice(l_orderUnitParams.getPrice());
            //Y00125：割増拘束率対応
            l_newOrderSpec.setLimitPrice(l_orderUnitParams.getLimitPrice());
            l_newOrderSpec.setEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            l_newOrderSpec.setCapitaGain(l_orderUnitParams.getCapitalGain());
            
            log.debug("mutate後の外株注文単位=" + l_orderUnitParams);
            log.debug("作成された外株新規注文内容=" + l_newOrderSpec);

            return l_newOrderSpec;
        }
        catch (DataException de)
        {
            log.error(de.getMessage());
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, de.getMessage(), de);

        } catch (NotFoundException nfe) 
        {
            log.error(nfe.getMessage());
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, nfe.getMessage(), nfe);
        }
    }
    /**
     * @@param account
     * @@param marketCode
     * @@param productCode
     * @@return
     */
    private static TradedProduct getFeqTradedProduct(
            SubAccount l_subAccount, 
            String l_strProductCode, 
            String l_strMarketCode) throws NotFoundException
    {
        log.debug("" );
        
        log.debug("getInstitutionCode="+l_subAccount.getInstitution().getInstitutionCode());
        log.debug("getInstitutionId=" + l_subAccount.getInstitution().getInstitutionId());
        log.debug("l_strProductCode=" + l_strProductCode);
        log.debug("l_strMarketCode="+l_strMarketCode);
        
        String l_tmName = FeqTradingModuleImpl.TRADING_MODULE_NAME;
        FeqProductManager l_pm = (FeqProductManager) (GtlUtils.getTradingModule(
            l_tmName).getProductManager());
        TradedProduct tradedProduct = l_pm.getFeqTradedProduct(
            l_subAccount.getInstitution(), l_strProductCode, l_strMarketCode);
        return tradedProduct;
    }

    /**
     * (staticメソッド)(to現注文内容)<BR> 
     * 現注文内容インスタンスを作成する<BR>
     * <BR>
     * ※シーケンス図「(現注文内容)create現注文内容」参照<BR>
     * <BR>
     * @@param l_subAccount   補助口座
     * @@param l_interceptor  注文インターセプト
     * @@param l_orderspec   注文内容
     * @@return WEB3TPNewOrderSpec  - 現注文内容
     */
    private static WEB3TPNewOrderSpec toWEB3TPNewOrderSpec(SubAccount
        l_subAccount,
        Object l_interceptor,
        Object l_orderspec)
    {
        final String STR_METHOD_NAME = 
            "WEB3TPNewOrderSpec.toWEB3TPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";
        log.entering(STR_METHOD_NAME);
        
        //株式の場合
        if (l_interceptor instanceof
            EqTypeOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3EqtypeTPNewOrderSpec(l_subAccount, l_interceptor, l_orderspec);
        }
        //累積投資の場合
        else if (l_interceptor instanceof
                 RuitoOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3RuitoTPNewOrderSpec(l_subAccount, l_interceptor,
                                             l_orderspec);
        }
        //投資信託の場合
        else if (l_interceptor instanceof
                 MutualFundOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3MutualFundTPNewOrderSpec(l_subAccount, l_interceptor,
                                                  l_orderspec);
        }
        //先物オプションの場合
        else if (l_interceptor instanceof
                 IfoOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3IFOTPNewOrderSpec(l_subAccount, l_interceptor,
                                           l_orderspec);
        }
        //振替の場合
        else if (l_interceptor instanceof
                 AioOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3AioTPNewOrderSpec(l_subAccount, l_interceptor,
                                           l_orderspec);
        }
        else if (l_interceptor instanceof
            FeqOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3FeqTPNewOrderSpec(l_subAccount, l_interceptor, l_orderspec);
        }
        //債券の場合
        else if (l_interceptor instanceof
            BondOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3BondTPNewOrderSpec(l_subAccount, l_interceptor, l_orderspec);
        }
        //そのた未対応のため、異常発生
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
    }
    
    /**
     * (staticメソッド)(to債券現注文内容)<BR> 
     * 現注文内容インスタンスを作成する <BR>
     * <BR>
     * ※シーケンス図「(現注文内容)to債券現注文内容」参照 <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_interceptor - (注文内容インタセプタ)
     * @@param l_orderSpec - (注文内容)
     * @@return WEB3TPNewOrderSpec
     */
    private static WEB3TPNewOrderSpec toWEB3BondTPNewOrderSpec(
        SubAccount l_subAccount,
        Object l_interceptor,
        Object l_orderSpec)
    {
        final String STR_METHOD_NAME = 
            "WEB3TPNewOrderSpec.toWEB3BondTPNewOrderSpec(SubAccount, Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1.1.BondOrderUnitParams( )
            //債券注文単位Paramsオブジェクトを生成する。
            WEB3TPNewOrderSpec l_newOrderSpec = new WEB3TPNewOrderSpec();
            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams();
            
            //1.2.(*)分岐フロー
            //債券新規注文の場合
            //（引数.注文内容 instanceof BondNewOrderSpec）
            if (l_orderSpec instanceof BondNewOrderSpec)
            {
                //1.2.1.(*)引数.注文内容を、クラス（：BondNewOrderSpec）にキャストする。
                BondNewOrderSpec l_bondNewOrderSpec = (BondNewOrderSpec)l_orderSpec;
                
                //1.2.2.(*)引数.注文内容インタセプタを、クラス
                //（：BondOrderManagerPersistenceEventInterceptor）にキャストする
                BondOrderManagerPersistenceEventInterceptor l_bondInterceptor = 
                    (BondOrderManagerPersistenceEventInterceptor)l_interceptor;
                
                //1.2.3.get債券取引銘柄(SubAccount, String, String, String)
                //取引銘柄オブジェクトを取得する。
                //[引数]
                //SubAccount = 引数.補助口座
                //String =  引数.注文内容.getMarketCode()
                //String =  引数.注文内容.getProductCode()
                //String =  引数.注文内容.getIssueCode()
                TradedProduct l_tradedProduct = getBondTradedProduct(
                    l_subAccount, 
                    l_bondNewOrderSpec.getMarketCode(),
                    l_bondNewOrderSpec.getProductCode(),
                    l_bondNewOrderSpec.getIssueCode());
                
                //1.2.4.getProduct( )
                //取得した取引銘柄オブジェクトより
                //銘柄オブジェクトを取得する。
                Product l_product = l_tradedProduct.getProduct();
                
                //1.2.5.getMarket( )
                //取得した取引銘柄オブジェクトより
                //市場オブジェクトを取得する。
                Market l_market = l_tradedProduct.getMarket();
                
                //1.2.6.getTradingCalendar(long)
                //取引カレンダオブジェクトを取得する。
                //[引数]
                //long = 取引銘柄オブジェクト.getTradedProductId()
                TradingCalendar l_tradingCalendar = 
                    GtlUtils.getFinObjectManager().getTradingCalendar(
                        l_tradedProduct.getTradedProductId());
                
                //1.2.7.getCurrentBizDate( )
                //取引カレンダオブジェクトより
                //営業日(当日)を取得する。
                Date l_datCurrentBizDate = l_tradingCalendar.getCurrentBizDate();
                
                //1.2.8.(*)債券注文単位Paramsの各値に値をセットする。
                //[設定値]
                //　@債券注文単位Params.口座ＩＤ = 引数.補助口座.getAccountId()
                l_bondOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                
                //　@債券注文単位Params.補助口座ＩＤ = 引数.補助口座.getSubAccountId()
                l_bondOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                
                //　@債券注文単位Params.部店ＩＤ = 引数.補助口座.getMainAccount().getBranch().getBranchId()
                l_bondOrderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
                
                //　@債券注文単位Params.注文ＩＤ = -1
                l_bondOrderUnitParams.setOrderId(DEFAULT_NEW_ID);
                
                //　@債券注文単位Params.注文単位ＩＤ = -1
                l_bondOrderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                
                //　@債券注文単位Params.注文カテゴリ = 1：現物注文
                l_bondOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                
                //　@債券注文単位Params.注文種別 = (*1)
                //　@(*1)引数.注文内容.isBuyOrder() == trueの場合、401：債券買い注文をセット
                //　@　@　@以外の場合、402：債券売り注文をセット
                if (l_bondNewOrderSpec.isBuyOrder())
                {
                    l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_BUY);
                }
                else
                {
                    l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_SELL);
                }

                //　@債券注文単位Params.注文数量 = 引数.注文内容.getQuantity()
                l_bondOrderUnitParams.setQuantity(l_bondNewOrderSpec.getQuantity());
                
                //　@債券注文単位Params.税区分 = 引数.注文内容.getTaxType()
                l_bondOrderUnitParams.setTaxType(l_bondNewOrderSpec.getTaxType());
                
                //　@債券注文単位Params.銘柄ＩＤ = getProduct()の戻り値.getProductId()
                l_bondOrderUnitParams.setProductId(l_product.getProductId());
                
                //　@債券注文単位Params.銘柄タイプ = getProduct()の戻り値.getProductType()
                l_bondOrderUnitParams.setProductType(l_product.getProductType());
                
                //　@債券注文単位Params.受渡日 = get債券取引銘柄()の戻り値.getDailyDeliveryDate()
                l_bondOrderUnitParams.setDeliveryDate(l_tradedProduct.getDailyDeliveryDate());
                
                //　@債券注文単位Params.市場ＩＤ = getMarket()の戻り値.getMarketId()
                l_bondOrderUnitParams.setMarketId(l_market.getMarketId());
                
                //　@債券注文単位Params.発注日 = getCurrentBizDateの戻り値(*2)
                //　@(*2)日付フォーマット「YYYYMMDD」に変換した値をセット
                l_bondOrderUnitParams.setBizDate(
                    WEB3DateUtility.formatDate(l_datCurrentBizDate, "yyyyMMdd"));
                
                //1.2.9.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)
                //債券注文単位Paramsの拡張項目に対して値をセットする。
                //[引数]
                //OrderManegerPersistenecType = OrderManagerPersistenceType.INSERT
                //OrderManegerPersistenecContext = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER
                //BondOrderUnitParams = 生成した債券注文単位Paramsオブジェクト
                l_bondOrderUnitParams = l_bondInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT, 
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER, 
                    l_bondOrderUnitParams);
            }

            //1.3.(*)分岐フロー
            //債券訂正注文の場合
            //（引数.注文内容 instanceof BondChangeOrderSpec
            if (l_orderSpec instanceof BondChangeOrderSpec)
            {
                //1.3.1.(*)引数.注文内容を、クラス（：BondChangeOrderSpec）にキャストする。
                BondChangeOrderSpec l_bondChangeOrderSpec = (BondChangeOrderSpec)l_orderSpec;
                
                //1.3.2.(*)引数.注文内容インタセプタを、クラス（
                //：BondOrderManagerPersistenceEventInterceptor）にキャストする
                BondOrderManagerPersistenceEventInterceptor l_bondInterceptor = 
                    (BondOrderManagerPersistenceEventInterceptor)l_interceptor;
                
                //1.3.3.findRowByPk(long)
                //債券注文単位オブジェクトを取得する。
                //[引数]
                //long = 引数.注文内容.getOrderId()               
                BondOrderRow l_orderRow = BondOrderDao.findRowByPk(
                    l_bondChangeOrderSpec.getOrderId());
                
                List l_lisOrderUnits = BondOrderUnitDao.findRowsByOrderId(l_orderRow);
                l_bondOrderUnitParams = new BondOrderUnitParams( (
                    BondOrderUnitRow)l_lisOrderUnits.get(0));
                
                //1.3.4.(*)取得した債券注文単位Paramsに注文内容の値をセットする。
                //[設定値]
                //　@債券注文単位Params.約定数量 = 
                //    引数.注文内容.getChangeOrderUnitEntries()[0].getAfterChangeOriginalQuantity()
                //　@債券注文単位Params.約定単価 = 
                //    引数.注文内容.getChangeOrderUnitEntries()[0].getAfterChangePrice()
                l_bondOrderUnitParams.setExecutedQuantity(
                    l_bondChangeOrderSpec.getChangeOrderUnitEntries()[0].getAfterChangeOriginalQuantity());

                l_bondOrderUnitParams.setExecutedPrice(
                    l_bondChangeOrderSpec.getChangeOrderUnitEntries()[0].getAfterChangePrice());
                
                //1.3.5.mutate(OrderManegerPersistenecType, OrderManegerPersistenecContext, BondOrderUnitParams)
                //債券注文単位Paramsの拡張項目に対して値をセットする。
                //[引数]
                //OrderManegerPersistenecType = OrderManagerPersistenceType.UPDATE
                //OrderManegerPersistenecContext = OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED
                //BondOrderUnitParams = 取得した債券注文単位Paramsオブジェクト
                l_bondOrderUnitParams = l_bondInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE, 
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED, 
                    l_bondOrderUnitParams);
            }
            
            //1.4.債券注文単位Params
            //[設定値]
            //　@現注文内容.補助口座ID = 引数.補助口座.getSubAccountId()
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            
            //　@現注文内容.補助口座タイプ = 引数.補助口座.getSubAccountType()
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());
            
            //　@現注文内容.注文ＩＤ = 生成した債券注文単位Params.注文ＩＤ
            l_newOrderSpec.setOrderId(l_bondOrderUnitParams.getOrderId());
            
            //　@現注文内容.注文単位ＩＤ = 生成した債券注文単位Params.注文単位ＩＤ 
            l_newOrderSpec.setOrderUnitId(l_bondOrderUnitParams.getOrderUnitId());
            
            //　@現注文内容.銘柄ID = 生成した債券注文単位Params.銘柄ＩＤ
            l_newOrderSpec.setProductId(l_bondOrderUnitParams.getProductId());
            
            //　@現注文内容.銘柄タイプ = 生成した債券注文単位Params.銘柄タイプ
            l_newOrderSpec.setProductType(l_bondOrderUnitParams.getProductType());
            
            //　@現注文内容.市場ID = 生成した債券注文単位Params.市場ＩＤ
            l_newOrderSpec.setMarketId(l_bondOrderUnitParams.getMarketId());
            
            //　@現注文内容.注文カテゴリ = 生成した債券注文単位Params.注文カテゴリ
            l_newOrderSpec.setOrderCategory(l_bondOrderUnitParams.getOrderCateg());
            
            //　@現注文内容.注文タイプ = 生成した債券注文単位Params.注文タイプ
            l_newOrderSpec.setOrderType(l_bondOrderUnitParams.getOrderType());
            
            //　@現注文内容.数量 = 生成した債券注文単位Params.注文数量
            l_newOrderSpec.setQuantity(l_bondOrderUnitParams.getQuantity());
            
            //　@現注文内容.単価 = 生成した債券注文単位Params.注文単価
            l_newOrderSpec.setPrice(l_bondOrderUnitParams.getPrice());
            
            //　@現注文内容.指値 = 生成した債券注文単位Params.指値
            l_newOrderSpec.setLimitPrice(l_bondOrderUnitParams.getLimitPrice());
            
            //　@現注文内容.概算代金 = 生成した債券注文単位Params.受渡代金(円貨)
            l_newOrderSpec.setEstimatedPrice(l_bondOrderUnitParams.getEstimatedPrice());
            
            //　@現注文内容.発注日 = 生成した債券注文単位Params.発注日
            l_newOrderSpec.setOrderBizDate(WEB3DateUtility.getDate(
                l_bondOrderUnitParams.getBizDate(), "yyyyMMdd"));
            
            //　@現注文内容.受渡日 = 生成した債券注文単位Params.受渡日
            l_newOrderSpec.setDeliveryDate(l_bondOrderUnitParams.getDeliveryDate());
            
            //　@現注文内容.税区分 = 生成した債券注文単位Params.税区分
            l_newOrderSpec.setTaxType(l_bondOrderUnitParams.getTaxType());
            
            //　@現注文内容.受注日時 = 生成した債券注文単位Params.受注日時
            l_newOrderSpec.setReceivedDateTime(l_bondOrderUnitParams.getReceivedDateTime());
            
            //　@現注文内容.入金日 = 生成した債券注文単位Params.入金日
            l_newOrderSpec.setPaymentDate(l_bondOrderUnitParams.getPaymentDate());
            
            //　@現注文内容.約定数量 = 生成した債券注文単位Params.約定数量
            l_newOrderSpec.setExecutedQuantity(l_bondOrderUnitParams.getExecutedQuantity());
            
            //　@現注文内容.注文約定区分 = 生成した債券注文単位Params.注文約定区分
            l_newOrderSpec.setOrderExecStatus(l_bondOrderUnitParams.getOrderExecStatus());
            
            //　@現注文内容.取引 = 生成した債券注文単位Params.取引
            l_newOrderSpec.setDealType(l_bondOrderUnitParams.getDealType());
            
            //1.5.(*)生成した現注文内容を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_newOrderSpec;
            
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (get株式取引銘柄())<BR>
     * <BR>
     * 株式取引銘柄を取得する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_sMarketCode  Marketコード
     * @@param l_sProductCode   銘柄コード
     * @@return TradedProduct  - 取引銘柄
     */
    private static TradedProduct getEqtypeTradedProduct(SubAccount l_subAccount,
        String l_sMarketCode,
        String l_sProductCode) throws NotFoundException
    {
        String l_tmName = TradingModuleImpl.TRADING_MODULE_NAME;
        EqTypeProductManager l_pm = (EqTypeProductManager) (GtlUtils.getTradingModule(
            l_tmName).getProductManager());
        TradedProduct tradedProduct = l_pm.getTradedProduct(
            l_subAccount.getInstitution(), l_sProductCode, l_sMarketCode);
        return tradedProduct;
    }

    /**
     * (get累積投資取引銘柄())<BR>
     * <BR>
     * 累積投資取引銘柄を取得する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_sMarketCode  Marketコード
     * @@param l_sProductCode   銘柄コード
     * @@param l_sIssueCode   回号コード
     * @@return TradedProduct  - 取引銘柄
     */
    private static TradedProduct getRuitoTradedProduct(SubAccount l_subAccount,
        String l_sMarketCode,
        String l_sProductCode,
        String l_sIssueCode) throws NotFoundException
    {
        String l_tmName = RuitoTradingModuleImpl.TRADING_MODULE_NAME;
        RuitoProductManager l_pm = (RuitoProductManager) (GtlUtils.getTradingModule(
            l_tmName).getProductManager());
        TradedProduct tradedProduct = l_pm.getRuitoTradedProduct(
            l_subAccount.getInstitution(), l_sProductCode, l_sIssueCode, l_sMarketCode);
        return tradedProduct;
    }

    /**
     * (get投資信託取引銘柄())<BR>
     * <BR>
     * 投資信託取引銘柄を取得する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_sMarketCode  Marketコード
     * @@param l_sProductCode   銘柄コード
     * @@param l_sIssueCode   回号コード
     * @@return TradedProduct  - 取引銘柄
     */
    private static TradedProduct getMutualFundTradedProduct(SubAccount l_subAccount,
        String l_sMarketCode,
        String l_sProductCode,
        String l_sIssueCode) throws NotFoundException
    {
        String l_tmName = MutualFundTradingModuleImpl.TRADING_MODULE_NAME;
        MutualFundProductManager l_pm = (MutualFundProductManager) (GtlUtils.
            getTradingModule(
            l_tmName).getProductManager());
        TradedProduct tradedProduct = l_pm.getMutualFundTradedProduct(
            l_subAccount.getInstitution(), l_sProductCode, l_sIssueCode, l_sMarketCode);
        return tradedProduct;
    }

    /**
     * (get先物オプション取引銘柄())<BR>
     * <BR>
     * 先物オプション取引銘柄を取得する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_sUnderlyingProductCode   銘柄コード
     * @@para　@l_derivativeType　@受渡タイプ
     * @@param l_sMonthOfDelivery   限月
     * @@param l_dnlStrikePrice   行使価格
     * @@param l_sMarketCode  Marketコード
     * @@return TradedProduct  - 取引銘柄
     */
    private static TradedProduct getIFOTradedProduct(SubAccount l_subAccount,
        String l_sUnderlyingProductCode,
        IfoDerivativeTypeEnum l_derivativeType,
        String l_sMonthOfDelivery,
        double l_dnlStrikePrice,
        String l_sMarketCode) throws NotFoundException
    {
        String l_tmName = IfoTradingModuleImpl.TRADING_MODULE_NAME;
        IfoProductManager l_pm = (IfoProductManager) (GtlUtils.
            getTradingModule(
            l_tmName).getProductManager());
        TradedProduct tradedProduct = l_pm.getIfoTradedProduct(l_subAccount.
            getInstitution(), l_sUnderlyingProductCode, l_derivativeType,
            l_sMonthOfDelivery, l_dnlStrikePrice, l_sMarketCode);
        return tradedProduct;
    }

    /**
     * (get先物オプション取引タイプ())<BR>
     * <BR>
     * 先物オプション取引タイプを取得する<BR>
     * @@param l_subAccount   補助口座
     * @@param l_baseDate   基準日
     * @@return Date  - 受渡日
     */
    private static OrderTypeEnum getIFOOrderTypeEnum(IfoOpenContractOrderSpec ｌ_spec)
    {
        boolean isFuturesOrder = IfoDerivativeTypeEnum.FUTURES.equals(ｌ_spec.
            getDerivativeType());
        OrderTypeEnum l_orderType;
        if (isFuturesOrder)
        {
            if (ｌ_spec.isBuyToOpenOrder())
            {
                l_orderType = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            }
            else
            {
                l_orderType = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
            }
        }
        else
        {
            if (ｌ_spec.isBuyToOpenOrder())
            {
                l_orderType = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
            }
            else
            {
                l_orderType = OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;
            }
        }
        return l_orderType;
    }

    /**
     * (getデフォルト振替受渡日())<BR>
     * <BR>
     * デフォルト振替受渡日を取得する。<BR>
     * @@param l_subAccount   補助口座
     * @@param l_baseDate   基準日
     * @@return Date  - 受渡日
     */
    private static Date getAioDeliveryDate(SubAccount l_subAcct, Date l_baseDate)
    {
        Market m = GtlUtils.getTradingSystem().getMarketForSystemCalendar(l_subAcct.
            getInstitution());
        int shiftDays = AioServerConfigConstants.getDeliverDateShiftDays();
        if (m != null)
        {
            MarketCalendar mc = m.getMarketCalendar();
            return mc.roll(l_baseDate, shiftDays);
        }
        else
        {
            return CalendarUtils.roll(l_baseDate, shiftDays);
        }
    }
    
    /**
     * (staticメソッド)(get債券取引銘柄) <BR>
     * <BR>
     * 引数（補助口座、市場コード、銘柄コード、回号コード）より <BR>
     * 取引銘柄オブジェクトを検索し返却する。 <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_strMarketCode - (市場コード)
     * @@param l_strProductCode - (銘柄コード)
     * @@param l_strIssueCode - (回号コード)
     * @@throws NotFoundException
     * @@return TradedProduct
     */
    private static TradedProduct getBondTradedProduct(
        SubAccount l_subAccount,
        String l_strMarketCode,
        String l_strProductCode,
        String l_strIssueCode) throws NotFoundException
    {
        final String STR_METHOD_NAME = 
            "TradedProduct.getBondTradedProduct(SubAccount, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strBondName = BondTradingModuleImpl.TRADING_MODULE_NAME;
        BondProductManager l_productManager = (BondProductManager) 
            (GtlUtils.getTradingModule(l_strBondName).getProductManager());
        
        TradedProduct l_tradedProduct = l_productManager.getBondTradedProduct(
            l_subAccount.getInstitution(), 
            l_strProductCode, 
            l_strIssueCode, 
            l_strMarketCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_tradedProduct;

    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("subAccountId", subAccountId);
        l_builder.append("subAccountType", subAccountType);
        l_builder.append("orderId", orderId);
        l_builder.append("orderUnitId", orderUnitId);
        l_builder.append("productId", productId);
        l_builder.append("productType", productType);
        l_builder.append("marketId", marketId);
        l_builder.append("orderCategory", orderCategory);
        l_builder.append("orderType", orderType);
        l_builder.append("quantity", quantity);
        l_builder.append("price", price);
        l_builder.append("limitPrice", limitPrice);
        l_builder.append("estimatedPrice", estimatedPrice);
        l_builder.append("orderBizDate", orderBizDate);
        l_builder.append("deliveryDate", deliveryDate);
        l_builder.append("withholdingTaxRestriction", withholdingTaxRestriction);
        l_builder.append("paymentDate", paymentDate);
        l_builder.append("executedQuantity", executedQuantity);
        l_builder.append("orderExecStatus", orderExecStatus);
        l_builder.append("dealType", dealType);

        if(contractSettleSpecify != null)
        {
            for(int i=0; i < contractSettleSpecify.length; i++)
            {
                l_builder.append("contractSettleSpecify[" + i + "]",contractSettleSpecify[i].toString());
            }
        }
        else
        {
            l_builder.append("contractSettleSpecify",contractSettleSpecify);
        }
        
        return l_builder.toString();        
    }
}
@
