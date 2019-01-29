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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������e(WEB3TPNewOrderSpec.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/02 �R�c�@@��i(FLJ) �V�K�쐬
                    2006/09/25 ���G�� (���u) ���f��No.51�A52�A53
                    2006/09/29 ���G�� (���u) ���f��No.67�A69
 Revision History : 2007/09/24 �Ј��� (���u) ���f��No.175
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
 * (���������e) <BR>
 * �]�͌v�Z���g�p���錻�������e��\������B
 */
public class WEB3TPNewOrderSpec
{
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPNewOrderSpec.class);

    public final static long DEFAULT_NEW_ID = -1;

    /**
     * �⏕����ID�@@<BR>
     */
    private long subAccountId;

    /**
     * �⏕�����^�C�v�@@<BR>
     */
    private SubAccountTypeEnum subAccountType;

    /**
     * ����ID�@@<BR>
     */
    private long orderId;

    /**
     * �����P��ID�@@<BR>
     */
    private long orderUnitId;

    /**
     * ����ID�@@<BR>
     */
    private long productId;

    /**
     * �����^�C�v�@@<BR>
     */
    private ProductTypeEnum productType;

    /**
     * �s��ID�@@<BR>
     */
    private long marketId;

    /**
     * �����J�e�S���@@<BR>
     */
    private OrderCategEnum orderCategory;

    /**
     * �����^�C�v�@@<BR>
     */
    private OrderTypeEnum orderType;

    /**
     * ���ʁ@@<BR>
     */
    private double quantity;

    /**
     * �P���@@<BR>
     */
    private double price;

    /**
     * �w�l�@@<BR>
     */
    private double limitPrice;
    
    /**
     * �T�Z����@@<BR>
     */
    private double estimatedPrice;

    /**
     * �������@@<BR>
     */
    private Date orderBizDate;

    /**
     * ��n���@@<BR>
     */
    private Date deliveryDate;

    /**
     * �ԍώw����@@<BR>
     */
    private webbroker3
        .tradingpower
        .updtpower
        .WEB3TPContractSettleSpecify[] contractSettleSpecify;

    /**
     * ���n���v�@@<BR>
     */
    private double capitaGain;

    /**
     * �ŋ敪�@@<BR>
     */
    private TaxTypeEnum taxType;

    /**
     * �o���\���敪<BR>
     */
    private String paymentApplicationDiv;
    
    /**
     * ��t����<BR>
     */   
    private Date receivedDateTime;
    
    /**
     * ���򒥎��S�����@@<BR>
     */
    private double withholdingTaxRestriction;

    /**
     * �������@@<BR>
     */
    private Date paymentDate;
    
    /**
     * (��萔��) <BR>
     * <BR>
     * �����Ŏg�p�B�������P�ʃe�[�u���̓����ڂ��Z�b�g <BR>
     */
    private double executedQuantity;
    
    /**
     * (�������敪)<BR> 
     * <BR>
     * �����Ŏg�p�B�������P�ʃe�[�u���̓����ڂ��Z�b�g<BR>
     */
    private String orderExecStatus;
    
    /**
     * (���)<BR> 
     * <BR>
     * �����Ŏg�p�B�������P�ʃe�[�u���̓����ڂ��Z�b�g<BR>
     */
    private String dealType;
 
    /**
     * @@roseuid 4136BF9E0045
     */
    public WEB3TPNewOrderSpec()
    {

    }

    /**
     * (get�⏕����ID)<BR>
     * <BR>
     * this.�⏕����ID���擾����B<BR>
     * @@return long
     */
    public long getSubAccountId()
    {
        return this.subAccountId;
    }

    /**
     * (set�⏕����ID)<BR>
     * <BR>
     * ����.�⏕����ID��this.�⏕����ID�ɃZ�b�g����<BR>
     * @@param l_subAccountId  - �⏕����ID
     */
    public void setSubAccountId(long l_subAccountId)
    {
        this.subAccountId = l_subAccountId;
    }

    /**
     * (get�⏕�����^�C�v)<BR>
     * <BR>
     * this.�⏕�����^�C�v���擾����B<BR>
     * @@return SubAccountTypeEnum
     */
    public SubAccountTypeEnum getSubAccountType()
    {
        return this.subAccountType;
    }

    /**
     * (set�⏕�����^�C�v)<BR>
     * <BR>
     * ����.�⏕�����^�C�v��this.�⏕�����^�C�v�ɃZ�b�g����<BR>
     * @@param l_subAccountType  - �⏕�����^�C�v
     */
    public void setSubAccountType(SubAccountTypeEnum l_subAccountType)
    {
        this.subAccountType = l_subAccountType;
    }

    /**
     * (get����ID)<BR>
     * <BR>
     * ����ID���擾����B<BR>
     * @@return long
     * @@roseuid 41046AA203D8
     */
    public long getOrderId()
    {
        return orderId;
    }

    /**
     * (set����ID)<BR>
     * <BR>
     * ����ID���Z�b�g����B<BR>
     * @@param l_lngOrderId  - ����ID
     * @@roseuid 41046AAC00AC
     */
    public void setOrderId(long l_lngOrderId)
    {
        orderId = l_lngOrderId;
    }

    /**
     * (get�P�ʒ���ID)<BR>
     * <BR>
     * �����P��ID���擾����B<BR>
     * @@return long
     * @@roseuid 41046AB50177
     */
    public long getOrderUnitId()
    {
        return orderUnitId;
    }

    /**
     * (set�P�ʒ���ID)<BR>
     * <BR>
     * �����P��ID���Z�b�g����B<BR>
     * @@param l_lngOrderUnitId - �����P��ID
     * @@roseuid 41046ABD01D5
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        orderUnitId = l_lngOrderUnitId;
    }

    /**
     * (get����ID)<BR>
     * <BR>
     * ����ID���擾����B<BR>
     * @@return long
     * @@roseuid 41046AC300DB
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * (set����ID)<BR>
     * <BR>
     * ����ID���Z�b�g����B<BR>
     * @@param l_lngProductId  - ����ID
     * @@roseuid 41046AC900FA
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }

    /**
     * (get�����^�C�v)<BR>
     * <BR>
     * �����^�C�v���擾����B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 41046AF901CD
     */
    public ProductTypeEnum getProductType()
    {
        return productType;
    }

    /**
     * (set�����^�C�v)<BR>
     * <BR>
     * �����^�C�v���Z�b�g����B<BR>
     * @@param l_productType  - �����^�C�v
     * @@roseuid 41046B040094
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        productType = l_productType;
    }

    /**
     * (get�s��ID)<BR>
     * <BR>
     * �s��ID���擾����B<BR>
     * @@return long
     * @@roseuid 41046B0C00B3
     */
    public long getMarketId()
    {
        return marketId;
    }

    /**
     * (set�s��ID)<BR>
     * <BR>
     * �s��ID���Z�b�g����B<BR>
     * @@param l_lngMarketId - �s��ID
     * @@roseuid 41046B150121
     */
    public void setMarketId(long l_lngMarketId)
    {
        marketId = l_lngMarketId;
    }

    /**
     * (get�����J�e�S��)<BR>
     * <BR>
     * �����J�e�S�����擾����B<BR>
     * @@return OrderCategEnum
     * @@roseuid 41046B2403B1
     */
    public OrderCategEnum getOrderCategory()
    {
        return orderCategory;
    }

    /**
     * (set�����J�e�S��)<BR>
     * <BR>
     * �����J�e�S�����Z�b�g����B<BR>
     * @@param l_orderCateg  - �����J�e�S��
     * @@roseuid 41046B2B023A
     */
    public void setOrderCategory(OrderCategEnum l_orderCateg)
    {
        orderCategory = l_orderCateg;
    }

    /**
     * (get�����^�C�v)<BR>
     * <BR>
     * �����^�C�v���擾����B<BR>
     * @@return OrderTypeEnum
     * @@roseuid 41046B3601EC
     */
    public OrderTypeEnum getOrderType()
    {
        return orderType;
    }

    /**
     * (set�����^�C�v)<BR>
     * <BR>
     * �����^�C�v���Z�b�g����B<BR>
     * @@param l_orderType  - �����^�C�v
     * @@roseuid 41046B3C022A
     */
    public void setOrderType(OrderTypeEnum l_orderType)
    {
        orderType = l_orderType;
    }

    /**
     * (get��������)<BR>
     * <BR>
     * �������ʂ��擾����B<BR>
     * @@return double
     * @@roseuid 41046C850056
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * (set��������)<BR>
     * <BR>
     * �������ʂ��Z�b�g����B<BR>
     * @@param l_dblQuantity  - ��������
     * @@roseuid 41046C8B0150
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * (get�����P��)<BR>
     * <BR>
     * �����P�����擾����B<BR>
     * @@return double
     * @@roseuid 41046C98017F
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * (set�����P��)<BR>
     * <BR>
     * �����P�����Z�b�g����B<BR>
     * @@param l_dblPrice  - �����P��
     * @@roseuid 41046C9D03C1
     */
    public void setPrice(double l_dblPrice)
    {
        price = l_dblPrice;
    }

    /**
     * (get�w�l)<BR>
     * <BR>
     * �w�l���擾����B<BR>
     * @@return double
     */
    public double getLimitPrice()
    {
        return this.limitPrice;
    }

    /**
     * (set�w�l)<BR>
     * <BR>
     * �w�l���Z�b�g����B<BR>
     * @@param l_dblLimitPrice  - �w�l
     */
    public void setLimitPrice(double l_dblLimitPrice)
    {
        this.limitPrice = l_dblLimitPrice;
    }

    /**
     * (get�T�Z��n���)<BR>
     * <BR>
     * �T�Z��n������擾����B<BR>
     * @@return double
     * @@roseuid 41046CA703D0
     */
    public double getEstimatedPrice()
    {
        return estimatedPrice;
    }

    /**
     * (set�T�Z��n���)<BR>
     * <BR>
     * �T�Z��n������Z�b�g����B<BR>
     * @@param l_dblEstimatedPrice  - �T�Z��n���
     * @@roseuid 41046CAD02A7
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice)
    {
        estimatedPrice = l_dblEstimatedPrice;
    }

    /**
     * (get������)<BR>
     * <BR>
     * ���������擾����B<BR>
     * @@return java.util.Date
     * @@roseuid 41046CB90269
     */
    public Date getOrderBizDate()
    {
        return orderBizDate;
    }

    /**
     * (set������)<BR>
     * <BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datOrderBizDate  - ������
     * @@roseuid 41046CC201FC
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        orderBizDate = l_datOrderBizDate;
    }

    /**
     * (get��n��)<BR>
     * <BR>
     * ��n�����擾����B<BR>
     * @@return java.util.Date
     * @@roseuid 41046CCF022A
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (set��n��)<BR>
     * <BR>
     * ��n�����Z�b�g����B<BR>
     * @@param l_datDeliveryDate  - ��n��
     * @@roseuid 41046CD60102
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (get�ԍώw����)<BR>
     * <BR>
     * �ԍώw������擾����B<BR>
     * @@return WEB3TPContractSettleSpecify[]
     * @@roseuid 41046CE8018E
     */
    public WEB3TPContractSettleSpecify[] getContractSettleSpecify()
    {
        return contractSettleSpecify;
    }

    /**
     * (set�ԍώw����)<BR>
     * <BR>
     * �ԍώw������Z�b�g����B<BR>
     * @@param l_contractSettleSpecify -�@@�ԍώw����
     * @@roseuid 41046CEE03D0
     */
    public void setContractSettleSpecify(WEB3TPContractSettleSpecify[]
                                         l_contractSettleSpecify)
    {
        contractSettleSpecify = l_contractSettleSpecify;
    }

    /**
     * (get���n���v)<BR>
     * <BR>
     * ���n���v���擾����B<BR>
     * @@return double
     * @@roseuid 41046CFB0046
     */
    public double getCapitaGain()
    {
        return capitaGain;
    }

    /**
     * (set���n���v)<BR>
     * <BR>
     * ���n���v���Z�b�g����B<BR>
     * @@param l_dblCapitaGain  - ���n���v
     * @@roseuid 41046D030065
     */
    public void setCapitaGain(double l_dblCapitaGain)
    {
        capitaGain = l_dblCapitaGain;
    }

    /**
     * (get�ŋ敪)<BR>
     * <BR>
     * �ŋ敪���擾����B<BR>
     * @@return TaxTypeEnum
     * @@roseuid 41046D0C00D3
     */
    public TaxTypeEnum getTaxType()
    {
        return taxType;
    }

    /**
     * (set�ŋ敪)<BR>
     * <BR>
     * �ŋ敪���Z�b�g����B<BR>
     * @@param l_TaxType  - �ŋ敪
     * @@roseuid 41046D110373
     */
    public void setTaxType(TaxTypeEnum l_TaxType)
    {
        taxType = l_TaxType;
    }

    /**
     * (get�o���\���敪)<BR>
     * <BR>
     * this.�o���\���敪���擾����B<BR>
     * @@return String
     */
    public String getPaymentApplicationDiv()
    {
        return paymentApplicationDiv;
    }

    /**
     * (set�o���\���敪)<BR>
     * <BR>
     * ����.�o���\���敪��this.�o���\���敪�ɃZ�b�g����<BR>
     * @@param l_subAccountId  - �o���\���敪
     */
    public void setPaymentApplicationDiv(String l_paymentApplicationDiv)
    {
        this.paymentApplicationDiv = l_paymentApplicationDiv;
    }

    /**
     * (get��t����)<BR>
     * <BR>
     * this.��t�������擾����B<BR>
     * @@return Date
     */
    public Date getReceivedDateTime()
    {
        return this.receivedDateTime;
    }

    /**
     * (set��t����)<BR>
     * <BR>
     * ����.��t������this.��t�����ɃZ�b�g����<BR>
     * @@param l_receivedDateTime  - ��t����
     */
    public void setReceivedDateTime(Date l_receivedDateTime)
    {
        this.receivedDateTime = l_receivedDateTime;
    }

    /**
     * (get���򒥎��S����)<BR>
     * <BR>
     * ���򒥎��S�������擾����B<BR>
     * @@return double
     */
    public double getWithholdingTaxRestriction()
    {
        return withholdingTaxRestriction;
    }

    /**
     * (set���򒥎��S����)<BR>
     * <BR>
     * ���򒥎��S�������Z�b�g����B<BR>
     * @@param l_dblWithholdingTaxRestriction  - ���򒥎��S����
     */
    public void setWithholdingTaxRestriction(double l_dblWithholdingTaxRestriction)
    {
        withholdingTaxRestriction = l_dblWithholdingTaxRestriction;
    }

    /**
     * (get������)<BR>
     * <BR>
     * ���������擾����B<BR>
     * @@return java.util.Date
     */
    public Date getPaymentDate()
    {
        return paymentDate;
    }

    /**
     * (set������)<BR>
     * <BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datPaymentDate  - ������
     */
    public void setPaymentDate(Date l_datPaymentDate)
    {
        paymentDate = l_datPaymentDate;
    }
    
    /**
     * (get��萔��) <BR>
     * <BR>
     * this.��萔�ʂ�ԋp����B<BR>
     * @@return double
     */
    public double getExecutedQuantity()
    {
        return this.executedQuantity;
    }
    
    /**
     * (set��萔��) <BR>
     * <BR>
     * ����.��萔�ʂ��Athis.��萔�ʂɃZ�b�g����B<BR>
     * @@param  l_strExecutedQuantity - (��萔��)
     */
    public void setExecutedQuantity(double l_dblExecutedQuantity)
    {
        this.executedQuantity = l_dblExecutedQuantity;
    }
    
    /**
     * (get�������敪)<BR> 
     * <BR>
     * this.�������敪��ԋp����B<BR>
     * @@return String
     */
    public String getOrderExecStatus()
    {
        return this.orderExecStatus;
    }
    
    /**
     * (set�������敪) <BR>
     * <BR>
     * ����.�������敪���Athis.�������敪�ɃZ�b�g����B<BR>
     * @@param l_strOrderExecStatus - (�������敪)
     */
    public void setOrderExecStatus(String l_strOrderExecStatus)
    {
        this.orderExecStatus = l_strOrderExecStatus; 
    }
    
    /**
     * (get���)<BR> 
     * <BR>
     * this.�����ԋp����B<BR>
     * @@return String 
     */
    public String getDealType()
    {
        return this.dealType;
    }
    
    /**
     * (set���)<BR> 
     * <BR>
     * ����.������Athis.����ɃZ�b�g����B <BR>
     * @@param l_strDealType - (���)
     */
    public void setDealType(String l_strDealType)
    {
        this.dealType = l_strDealType;
    }

    /**
     * (create���������e)<BR>
     * <BR>
     * ���������e���쐬����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_interceptor  �����C���^�[�Z�v�g
     * @@param l_orderspec   �������e
     * @@return WEB3TPNewOrderSpec  - ���������e
     */
    public static WEB3TPNewOrderSpec create(SubAccount l_subAccount, Object l_interceptor,
                                            Object l_orderspec)
    {
        try
        {
            //ThreadLocalSystemAttributesRegistry�ɁA�̔Ԃ����Ȃ��t���O��ǉ�
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG, Boolean.TRUE);
            
            //���������e�I�u�W�F�N�g�𐶐�
            WEB3TPNewOrderSpec l_newOrderSpec = toWEB3TPNewOrderSpec(l_subAccount, l_interceptor, l_orderspec);
    
            return l_newOrderSpec;
        }
        finally
        {
            //ThreadLocalSystemAttributesRegistry�ɁA�̔Ԃ����Ȃ��t���O���폜
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG, null);
        }
    }

    /**
     * (to�������������e())<BR>
     * <BR>
     * �������������e�ɍ쐬����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_interceptor  �����C���^�[�Z�v�g
     * @@param l_orderspec   �������e
     * @@return WEB3TPNewOrderSpec  - ���������e
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

            //�����V�K�����̏ꍇ
            if (l_orderspec instanceof EqTypeNewCashBasedOrderSpec)
            {
                EqTypeNewCashBasedOrderSpec l_eqOrderspec = (EqTypeNewCashBasedOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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
                //Y00125�F�����S�����Ή�
                l_orderUnitParams.setLimitPrice(l_eqOrderspec.getLimitPrice());
                
                //���������쐬
                TradedProduct l_tp = getEqtypeTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //���C�� add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            //�~�j���V�K�����̏ꍇ
            else if (l_orderspec instanceof EqTypeNewMiniStockOrderSpec)
            {
                EqTypeNewMiniStockOrderSpec l_eqOrderspec = (EqTypeNewMiniStockOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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

                //���������쐬
                TradedProduct l_tp = getEqtypeTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            //�M�p�V�K�������̏ꍇ
            else if (l_orderspec instanceof EqTypeOpenContractOrderSpec)
            {
                EqTypeOpenContractOrderSpec l_eqOrderspec = (EqTypeOpenContractOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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
                //Y00125�F�����S�����Ή�
                l_orderUnitParams.setLimitPrice(l_eqOrderspec.getLimitPrice());

                //���������쐬
                TradedProduct l_tp = getEqtypeTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //���C�� add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER,
                    l_orderUnitParams);

            }
            //�M�p�V�K�ԍϒ����̏ꍇ
            else if (l_orderspec instanceof EqTypeSettleContractOrderSpec)
            {
                EqTypeSettleContractOrderSpec l_eqOrderspec = (
                    EqTypeSettleContractOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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

                //���������쐬
                TradedProduct l_tp = l_contract.getTradedProduct();
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());

                //���C�� add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER,
                    l_orderUnitParams);

                //�ԍώw����
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
            //�M�p�V�K�������n�����̏ꍇ
            else if (l_orderspec instanceof EqTypeSwapContractOrderSpec)
            {
                EqTypeSwapContractOrderSpec l_eqOrderspec = (
                    EqTypeSwapContractOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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

                //���������쐬
                TradedProduct l_tp = l_contract.getTradedProduct();
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());

                //���C�� add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
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
            //�������������̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(l_eqChangeOrderspec.
                                              getChangeOrderUnitEntries()[0].
                                              getAfterChangeOriginalQuantity());
                l_orderUnitParams.setPrice(l_eqChangeOrderspec.getChangeOrderUnitEntries()[
                                           0].getAfterChangePrice());

                //Y00125�F�����S�����Ή�
                l_orderUnitParams.setLimitPrice(l_eqChangeOrderspec
                    .getChangeOrderUnitEntries()[0].getAfterChangePrice());

                
                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            //�M�p�ԍϒ��������̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(l_eqChangeOrderspec.
                                              getChangeOrderUnitEntries()[0].
                                              getAfterChangeTotalQuantity());
                l_orderUnitParams.setPrice(l_eqChangeOrderspec.getChangeOrderUnitEntries()[
                                           0].getAfterChangePrice());

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

                //�ԍώw����
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
            //�M�p�������n���������̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(l_eqChangeOrderspec.
                                              getChangeOrderUnitEntries()[0].
                                              getAfterChangeTotalQuantity());

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

                //�ԍώw����
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
            //��������̏ꍇ
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


                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                EqTypeOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (EqTypeOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);
                    
                //���������쐬
                l_orderUnitParams.setQuantity(0);
                l_orderUnitParams.setEstimatedPrice(0.0);
                l_orderUnitParams.setCapitalGain(0.0);
                l_orderUnitParams.setPrice(0.0);

            }
            //���̂��̏ꍇ�A���Ή����߈ُ픭��
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //������������ݒ肷��
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
            //Y00125�F�����S�����Ή�
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
     * (to�ݐϓ������������e())<BR>
     * <BR>
     * �ݐϓ������������e�ɍ쐬����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_interceptor  �����C���^�[�Z�v�g
     * @@param l_orderspec   �������e
     * @@return WEB3TPNewOrderSpec  - ���������e
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

            //�ݐϓ����V�K�����̏ꍇ
            if (l_orderspec instanceof RuitoNewOrderSpec)
            {
                RuitoNewOrderSpec l_eqOrderspec = (RuitoNewOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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

                //���������쐬
                TradedProduct l_tp = getRuitoTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode(),
                    l_eqOrderspec.getIssueCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //���C�� add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                RuitoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (RuitoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            //�ݐϓ�����������̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(0);

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                RuitoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (RuitoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            //���̂��̏ꍇ�A���Ή����߈ُ픭��
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //������������ݒ肷��
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
     * (to�����M�����������e())<BR>
     * <BR>
     * �����M�����������e�ɍ쐬����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_interceptor  �����C���^�[�Z�v�g
     * @@param l_orderspec   �������e
     * @@return WEB3TPNewOrderSpec  - ���������e
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

            //�����M���V�K�����̏ꍇ
            if (l_orderspec instanceof MutualFundNewOrderSpec)
            {
                MutualFundNewOrderSpec l_eqOrderspec = (MutualFundNewOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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

                //���������쐬
                TradedProduct l_tp = getMutualFundTradedProduct(l_subAccount,
                    l_eqOrderspec.getMarketCode(),
                    l_eqOrderspec.getProductCode(),
                    l_eqOrderspec.getIssueCode());
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                //���C�� add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                MutualFundOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (MutualFundOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            //�����M����������̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(0);
                l_orderUnitParams.setEstimatedPrice(0.0);

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                MutualFundOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (MutualFundOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            //���̂��̏ꍇ�A���Ή����߈ُ픭��
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //������������ݒ肷��
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
     * (to�U�֌��������e())<BR>
     * <BR>
     * �U�֌��������e�ɍ쐬����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_interceptor  �����C���^�[�Z�v�g
     * @@param l_orderspec   �������e
     * @@return WEB3TPNewOrderSpec  - ���������e
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

            //�U�֐V�K�����̏ꍇ
            if (l_orderspec instanceof AioNewOrderSpec)
            {
                AioNewOrderSpec l_aioOrderspec = (AioNewOrderSpec)
                    l_orderspec;
                
                //�������
                OrderTypeEnum l_orderType = l_aioOrderspec.getOrderTypeEnum();
                //�U�փ^�C�v
                AssetTransferTypeEnum l_assetType = l_aioOrderspec.getAssetTransferTypeEnum();

                //������ʁ�1007�F�U�֒����i�a������犔��؋����j�̏ꍇ
                if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderType))
                {
                    //�U�փ^�C�v=1�F�����̏ꍇ
                    if (AssetTransferTypeEnum.CASH_IN.equals(l_assetType))
                    {
                        //�⏕�����i�V�F�����I�v�V������������i�敨�؋����j���擾
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                    }
                    //�ȊO�i�U�փ^�C�v=2�F�o���j�̏ꍇ
                    else
                    {
                        //�⏕�����i1�F������������i�a����j���擾
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }
                }
                //������ʁ�1009�F�،��U�֒����i�ی�a�肩���p�L���،��j�̏ꍇ
                else if (OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.equals(l_orderType))
                {
                    //�U�փ^�C�v=1�F�����̏ꍇ
                    if (AssetTransferTypeEnum.CASH_IN.equals(l_assetType))
                    {
                        //�⏕�����i2�F�����M�p��������i�ۏ؋��j���擾
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    }
                    //�ȊO�i�U�փ^�C�v=2�F�o���j�̏ꍇ
                    else
                    {
                        //�⏕�����i1�F������������i�a����j���擾
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }
                }
                //������ʁ�1020�F�U�֒����i�a�������I���b�N�X�N���W�b�g�j�̏ꍇ
                else if (OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderType))
                {
                    //�U�փ^�C�v=2�F�o���̏ꍇ
                    if (AssetTransferTypeEnum.CASH_OUT.equals(l_assetType))
                    {
                        //�⏕�����i1�F������������i�a����j���擾
                        l_tmpSubAccount =
                            l_subAccount.getMainAccount().getSubAccount(
                                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }
                }

                //�ڋq����
                l_orderUnitParams.setAccountId(l_tmpSubAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_tmpSubAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_tmpSubAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderCateg(OrderCategEnum.ASSET_TRANSFER);
                l_orderUnitParams.setOrderType(l_aioOrderspec.getOrderTypeEnum());
                l_orderUnitParams.setQuantity(l_aioOrderspec.getQuantity());

                //���������쐬
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

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                AioOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (AioOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_ASSET_TRANSFER_ORDER,
                    l_orderUnitParams);

            }
            //�U�֒���(���o���i�P���R�[�h�j�̂݁A�U�ցi�Q���R�[�h�j�͎���������Ȃ�)����̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(0);

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                AioOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (AioOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            //���̂��̏ꍇ�A���Ή����߈ُ픭��
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //������������ݒ肷��
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
     * (to�敨�I�v�V�������������e())<BR>
     * <BR>
     * �敨�I�v�V�������������e�ɍ쐬����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_interceptor  �����C���^�[�Z�v�g
     * @@param l_orderspec   �������e
     * @@return WEB3TPNewOrderSpec  - ���������e
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

            //�敨�I�v�V�����V�K�������̏ꍇ
            if (l_orderspec instanceof IfoOpenContractOrderSpec)
            {
                IfoOpenContractOrderSpec l_ifoOrderspec = (IfoOpenContractOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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

                //���������쐬
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
                //���C�� add
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
                //end

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                IfoOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER,
                    l_orderUnitParams);

            }
            //�敨�I�v�V�����V�K�ԍϒ����̏ꍇ
            else if (l_orderspec instanceof IfoSettleContractOrderSpec)
            {
                IfoSettleContractOrderSpec l_ifoOrderspec = (IfoSettleContractOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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

                //���������쐬
                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());

                l_orderUnitParams.setQuantity(l_ifoOrderspec.getTotalQuantity());
                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                IfoOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT,
                    OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER,
                    l_orderUnitParams);

                //�ԍώw����
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
            //�敨�I�v�V�����V�K�����������̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(l_eqChangeOrderspec.
                                              getAfterChangeOriginalQuantity());
                l_orderUnitParams.setPrice(l_eqChangeOrderspec.getAfterChangePrice());

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                IfoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);
            }
            //�敨�I�v�V�����ԍϒ��������̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(l_changeOrderspec.
                                              getAfterChangeTotalQuantity());

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                IfoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

                //�ԍώw����
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
            //�敨�I�v�V������������̏ꍇ
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

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                IfoOrderManagerPersistenceEventInterceptor
                    l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);

                //���������쐬
                l_orderUnitParams.setQuantity(0);
                l_orderUnitParams.setPrice(0.0);
                l_orderUnitParams.setEstimatedPrice(0.0);

            }
            //���̂��̏ꍇ�A���Ή����߈ُ픭��
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //������������ݒ肷��
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
     * (to�O�����������e())<BR>
     * <BR>
     * �O���������e�ɍ쐬����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_interceptor  �����C���^�[�Z�v�g
     * @@param l_orderspec   �������e
     * @@return WEB3TPNewOrderSpec  - ���������e
     */
    private static WEB3TPNewOrderSpec toWEB3FeqTPNewOrderSpec(SubAccount l_subAccount, Object l_interceptor, Object l_orderspec) {

        final String STR_METHOD_NAME = "WEB3TPNewOrderSpec.toWEB3FeqTPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";
        
        log.entering(STR_METHOD_NAME);
                
        try
        {
            WEB3TPNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3TPNewOrderSpec();
            FeqOrderUnitParams l_orderUnitParams = new FeqOrderUnitParams();

            //�O�����̏ꍇ
            if (l_orderspec instanceof FeqNewOrderSpec)
            {
                FeqNewOrderSpec l_feqOrderspec = (FeqNewOrderSpec)
                    l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().
                                              getBranchId());

                //���������쐬
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
                
                
                //���������쐬
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
                
                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                FeqOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (FeqOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    null,
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER,
                    l_orderUnitParams);

            }
            
            //�O���@@�����̏ꍇ
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

                //���������쐬
                l_orderUnitParams.setQuantity(l_feqChangeOrderspec.getAfterChangeOriginalQuantity());
                l_orderUnitParams.setPrice(l_feqChangeOrderspec.getAfterChangePrice());
                
                //Y00125�F�����S�����Ή�
                l_orderUnitParams.setLimitPrice(l_feqChangeOrderspec.getAfterChangePrice());
                                
                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                FeqOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (FeqOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    null,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                    l_orderUnitParams);

            }
            
            //�O���@@��������̏ꍇ
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


                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                FeqOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (FeqOrderManagerPersistenceEventInterceptor) l_interceptor;
                l_orderUnitParams = l_persistenceInterceptor.mutate(
                    null,
                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED,
                    l_orderUnitParams);
                                    
                //���������쐬
                l_orderUnitParams.setQuantity(0);
                l_orderUnitParams.setEstimatedPrice(0.0);
                l_orderUnitParams.setCapitalGain(0.0);
                l_orderUnitParams.setPrice(0.0);

            }
            //���̂��̏ꍇ�A���Ή����߈ُ픭��
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }
            
            //������������ݒ肷��
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
            //Y00125�F�����S�����Ή�
            l_newOrderSpec.setLimitPrice(l_orderUnitParams.getLimitPrice());
            l_newOrderSpec.setEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            l_newOrderSpec.setCapitaGain(l_orderUnitParams.getCapitalGain());
            
            log.debug("mutate��̊O�������P��=" + l_orderUnitParams);
            log.debug("�쐬���ꂽ�O���V�K�������e=" + l_newOrderSpec);

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
     * (static���\�b�h)(to���������e)<BR> 
     * ���������e�C���X�^���X���쐬����<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������e)create���������e�v�Q��<BR>
     * <BR>
     * @@param l_subAccount   �⏕����
     * @@param l_interceptor  �����C���^�[�Z�v�g
     * @@param l_orderspec   �������e
     * @@return WEB3TPNewOrderSpec  - ���������e
     */
    private static WEB3TPNewOrderSpec toWEB3TPNewOrderSpec(SubAccount
        l_subAccount,
        Object l_interceptor,
        Object l_orderspec)
    {
        final String STR_METHOD_NAME = 
            "WEB3TPNewOrderSpec.toWEB3TPNewOrderSpec(SubAccount l_subAccount,Object l_interceptor,Object l_orderspec)";
        log.entering(STR_METHOD_NAME);
        
        //�����̏ꍇ
        if (l_interceptor instanceof
            EqTypeOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3EqtypeTPNewOrderSpec(l_subAccount, l_interceptor, l_orderspec);
        }
        //�ݐϓ����̏ꍇ
        else if (l_interceptor instanceof
                 RuitoOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3RuitoTPNewOrderSpec(l_subAccount, l_interceptor,
                                             l_orderspec);
        }
        //�����M���̏ꍇ
        else if (l_interceptor instanceof
                 MutualFundOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3MutualFundTPNewOrderSpec(l_subAccount, l_interceptor,
                                                  l_orderspec);
        }
        //�敨�I�v�V�����̏ꍇ
        else if (l_interceptor instanceof
                 IfoOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3IFOTPNewOrderSpec(l_subAccount, l_interceptor,
                                           l_orderspec);
        }
        //�U�ւ̏ꍇ
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
        //���̏ꍇ
        else if (l_interceptor instanceof
            BondOrderManagerPersistenceEventInterceptor)
        {
            log.exiting(STR_METHOD_NAME);
            return toWEB3BondTPNewOrderSpec(l_subAccount, l_interceptor, l_orderspec);
        }
        //���̂����Ή��̂��߁A�ُ픭��
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
    }
    
    /**
     * (static���\�b�h)(to�����������e)<BR> 
     * ���������e�C���X�^���X���쐬���� <BR>
     * <BR>
     * ���V�[�P���X�}�u(���������e)to�����������e�v�Q�� <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_interceptor - (�������e�C���^�Z�v�^)
     * @@param l_orderSpec - (�������e)
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
            //�������P��Params�I�u�W�F�N�g�𐶐�����B
            WEB3TPNewOrderSpec l_newOrderSpec = new WEB3TPNewOrderSpec();
            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams();
            
            //1.2.(*)����t���[
            //���V�K�����̏ꍇ
            //�i����.�������e instanceof BondNewOrderSpec�j
            if (l_orderSpec instanceof BondNewOrderSpec)
            {
                //1.2.1.(*)����.�������e���A�N���X�i�FBondNewOrderSpec�j�ɃL���X�g����B
                BondNewOrderSpec l_bondNewOrderSpec = (BondNewOrderSpec)l_orderSpec;
                
                //1.2.2.(*)����.�������e�C���^�Z�v�^���A�N���X
                //�i�FBondOrderManagerPersistenceEventInterceptor�j�ɃL���X�g����
                BondOrderManagerPersistenceEventInterceptor l_bondInterceptor = 
                    (BondOrderManagerPersistenceEventInterceptor)l_interceptor;
                
                //1.2.3.get���������(SubAccount, String, String, String)
                //��������I�u�W�F�N�g���擾����B
                //[����]
                //SubAccount = ����.�⏕����
                //String =  ����.�������e.getMarketCode()
                //String =  ����.�������e.getProductCode()
                //String =  ����.�������e.getIssueCode()
                TradedProduct l_tradedProduct = getBondTradedProduct(
                    l_subAccount, 
                    l_bondNewOrderSpec.getMarketCode(),
                    l_bondNewOrderSpec.getProductCode(),
                    l_bondNewOrderSpec.getIssueCode());
                
                //1.2.4.getProduct( )
                //�擾������������I�u�W�F�N�g���
                //�����I�u�W�F�N�g���擾����B
                Product l_product = l_tradedProduct.getProduct();
                
                //1.2.5.getMarket( )
                //�擾������������I�u�W�F�N�g���
                //�s��I�u�W�F�N�g���擾����B
                Market l_market = l_tradedProduct.getMarket();
                
                //1.2.6.getTradingCalendar(long)
                //����J�����_�I�u�W�F�N�g���擾����B
                //[����]
                //long = ��������I�u�W�F�N�g.getTradedProductId()
                TradingCalendar l_tradingCalendar = 
                    GtlUtils.getFinObjectManager().getTradingCalendar(
                        l_tradedProduct.getTradedProductId());
                
                //1.2.7.getCurrentBizDate( )
                //����J�����_�I�u�W�F�N�g���
                //�c�Ɠ�(����)���擾����B
                Date l_datCurrentBizDate = l_tradingCalendar.getCurrentBizDate();
                
                //1.2.8.(*)�������P��Params�̊e�l�ɒl���Z�b�g����B
                //[�ݒ�l]
                //�@@�������P��Params.�����h�c = ����.�⏕����.getAccountId()
                l_bondOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                
                //�@@�������P��Params.�⏕�����h�c = ����.�⏕����.getSubAccountId()
                l_bondOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                
                //�@@�������P��Params.���X�h�c = ����.�⏕����.getMainAccount().getBranch().getBranchId()
                l_bondOrderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
                
                //�@@�������P��Params.�����h�c = -1
                l_bondOrderUnitParams.setOrderId(DEFAULT_NEW_ID);
                
                //�@@�������P��Params.�����P�ʂh�c = -1
                l_bondOrderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                
                //�@@�������P��Params.�����J�e�S�� = 1�F��������
                l_bondOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                
                //�@@�������P��Params.������� = (*1)
                //�@@(*1)����.�������e.isBuyOrder() == true�̏ꍇ�A401�F�������������Z�b�g
                //�@@�@@�@@�ȊO�̏ꍇ�A402�F�����蒍�����Z�b�g
                if (l_bondNewOrderSpec.isBuyOrder())
                {
                    l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_BUY);
                }
                else
                {
                    l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_SELL);
                }

                //�@@�������P��Params.�������� = ����.�������e.getQuantity()
                l_bondOrderUnitParams.setQuantity(l_bondNewOrderSpec.getQuantity());
                
                //�@@�������P��Params.�ŋ敪 = ����.�������e.getTaxType()
                l_bondOrderUnitParams.setTaxType(l_bondNewOrderSpec.getTaxType());
                
                //�@@�������P��Params.�����h�c = getProduct()�̖߂�l.getProductId()
                l_bondOrderUnitParams.setProductId(l_product.getProductId());
                
                //�@@�������P��Params.�����^�C�v = getProduct()�̖߂�l.getProductType()
                l_bondOrderUnitParams.setProductType(l_product.getProductType());
                
                //�@@�������P��Params.��n�� = get���������()�̖߂�l.getDailyDeliveryDate()
                l_bondOrderUnitParams.setDeliveryDate(l_tradedProduct.getDailyDeliveryDate());
                
                //�@@�������P��Params.�s��h�c = getMarket()�̖߂�l.getMarketId()
                l_bondOrderUnitParams.setMarketId(l_market.getMarketId());
                
                //�@@�������P��Params.������ = getCurrentBizDate�̖߂�l(*2)
                //�@@(*2)���t�t�H�[�}�b�g�uYYYYMMDD�v�ɕϊ������l���Z�b�g
                l_bondOrderUnitParams.setBizDate(
                    WEB3DateUtility.formatDate(l_datCurrentBizDate, "yyyyMMdd"));
                
                //1.2.9.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)
                //�������P��Params�̊g�����ڂɑ΂��Ēl���Z�b�g����B
                //[����]
                //OrderManegerPersistenecType = OrderManagerPersistenceType.INSERT
                //OrderManegerPersistenecContext = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER
                //BondOrderUnitParams = ���������������P��Params�I�u�W�F�N�g
                l_bondOrderUnitParams = l_bondInterceptor.mutate(
                    OrderManagerPersistenceType.INSERT, 
                    OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER, 
                    l_bondOrderUnitParams);
            }

            //1.3.(*)����t���[
            //�����������̏ꍇ
            //�i����.�������e instanceof BondChangeOrderSpec
            if (l_orderSpec instanceof BondChangeOrderSpec)
            {
                //1.3.1.(*)����.�������e���A�N���X�i�FBondChangeOrderSpec�j�ɃL���X�g����B
                BondChangeOrderSpec l_bondChangeOrderSpec = (BondChangeOrderSpec)l_orderSpec;
                
                //1.3.2.(*)����.�������e�C���^�Z�v�^���A�N���X�i
                //�FBondOrderManagerPersistenceEventInterceptor�j�ɃL���X�g����
                BondOrderManagerPersistenceEventInterceptor l_bondInterceptor = 
                    (BondOrderManagerPersistenceEventInterceptor)l_interceptor;
                
                //1.3.3.findRowByPk(long)
                //�������P�ʃI�u�W�F�N�g���擾����B
                //[����]
                //long = ����.�������e.getOrderId()               
                BondOrderRow l_orderRow = BondOrderDao.findRowByPk(
                    l_bondChangeOrderSpec.getOrderId());
                
                List l_lisOrderUnits = BondOrderUnitDao.findRowsByOrderId(l_orderRow);
                l_bondOrderUnitParams = new BondOrderUnitParams( (
                    BondOrderUnitRow)l_lisOrderUnits.get(0));
                
                //1.3.4.(*)�擾�����������P��Params�ɒ������e�̒l���Z�b�g����B
                //[�ݒ�l]
                //�@@�������P��Params.��萔�� = 
                //    ����.�������e.getChangeOrderUnitEntries()[0].getAfterChangeOriginalQuantity()
                //�@@�������P��Params.���P�� = 
                //    ����.�������e.getChangeOrderUnitEntries()[0].getAfterChangePrice()
                l_bondOrderUnitParams.setExecutedQuantity(
                    l_bondChangeOrderSpec.getChangeOrderUnitEntries()[0].getAfterChangeOriginalQuantity());

                l_bondOrderUnitParams.setExecutedPrice(
                    l_bondChangeOrderSpec.getChangeOrderUnitEntries()[0].getAfterChangePrice());
                
                //1.3.5.mutate(OrderManegerPersistenecType, OrderManegerPersistenecContext, BondOrderUnitParams)
                //�������P��Params�̊g�����ڂɑ΂��Ēl���Z�b�g����B
                //[����]
                //OrderManegerPersistenecType = OrderManagerPersistenceType.UPDATE
                //OrderManegerPersistenecContext = OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED
                //BondOrderUnitParams = �擾�����������P��Params�I�u�W�F�N�g
                l_bondOrderUnitParams = l_bondInterceptor.mutate(
                    OrderManagerPersistenceType.UPDATE, 
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED, 
                    l_bondOrderUnitParams);
            }
            
            //1.4.�������P��Params
            //[�ݒ�l]
            //�@@���������e.�⏕����ID = ����.�⏕����.getSubAccountId()
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            
            //�@@���������e.�⏕�����^�C�v = ����.�⏕����.getSubAccountType()
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());
            
            //�@@���������e.�����h�c = ���������������P��Params.�����h�c
            l_newOrderSpec.setOrderId(l_bondOrderUnitParams.getOrderId());
            
            //�@@���������e.�����P�ʂh�c = ���������������P��Params.�����P�ʂh�c 
            l_newOrderSpec.setOrderUnitId(l_bondOrderUnitParams.getOrderUnitId());
            
            //�@@���������e.����ID = ���������������P��Params.�����h�c
            l_newOrderSpec.setProductId(l_bondOrderUnitParams.getProductId());
            
            //�@@���������e.�����^�C�v = ���������������P��Params.�����^�C�v
            l_newOrderSpec.setProductType(l_bondOrderUnitParams.getProductType());
            
            //�@@���������e.�s��ID = ���������������P��Params.�s��h�c
            l_newOrderSpec.setMarketId(l_bondOrderUnitParams.getMarketId());
            
            //�@@���������e.�����J�e�S�� = ���������������P��Params.�����J�e�S��
            l_newOrderSpec.setOrderCategory(l_bondOrderUnitParams.getOrderCateg());
            
            //�@@���������e.�����^�C�v = ���������������P��Params.�����^�C�v
            l_newOrderSpec.setOrderType(l_bondOrderUnitParams.getOrderType());
            
            //�@@���������e.���� = ���������������P��Params.��������
            l_newOrderSpec.setQuantity(l_bondOrderUnitParams.getQuantity());
            
            //�@@���������e.�P�� = ���������������P��Params.�����P��
            l_newOrderSpec.setPrice(l_bondOrderUnitParams.getPrice());
            
            //�@@���������e.�w�l = ���������������P��Params.�w�l
            l_newOrderSpec.setLimitPrice(l_bondOrderUnitParams.getLimitPrice());
            
            //�@@���������e.�T�Z��� = ���������������P��Params.��n���(�~��)
            l_newOrderSpec.setEstimatedPrice(l_bondOrderUnitParams.getEstimatedPrice());
            
            //�@@���������e.������ = ���������������P��Params.������
            l_newOrderSpec.setOrderBizDate(WEB3DateUtility.getDate(
                l_bondOrderUnitParams.getBizDate(), "yyyyMMdd"));
            
            //�@@���������e.��n�� = ���������������P��Params.��n��
            l_newOrderSpec.setDeliveryDate(l_bondOrderUnitParams.getDeliveryDate());
            
            //�@@���������e.�ŋ敪 = ���������������P��Params.�ŋ敪
            l_newOrderSpec.setTaxType(l_bondOrderUnitParams.getTaxType());
            
            //�@@���������e.�󒍓��� = ���������������P��Params.�󒍓���
            l_newOrderSpec.setReceivedDateTime(l_bondOrderUnitParams.getReceivedDateTime());
            
            //�@@���������e.������ = ���������������P��Params.������
            l_newOrderSpec.setPaymentDate(l_bondOrderUnitParams.getPaymentDate());
            
            //�@@���������e.��萔�� = ���������������P��Params.��萔��
            l_newOrderSpec.setExecutedQuantity(l_bondOrderUnitParams.getExecutedQuantity());
            
            //�@@���������e.�������敪 = ���������������P��Params.�������敪
            l_newOrderSpec.setOrderExecStatus(l_bondOrderUnitParams.getOrderExecStatus());
            
            //�@@���������e.��� = ���������������P��Params.���
            l_newOrderSpec.setDealType(l_bondOrderUnitParams.getDealType());
            
            //1.5.(*)�����������������e��ԋp����B
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
     * (get�����������())<BR>
     * <BR>
     * ��������������擾����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_sMarketCode  Market�R�[�h
     * @@param l_sProductCode   �����R�[�h
     * @@return TradedProduct  - �������
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
     * (get�ݐϓ����������())<BR>
     * <BR>
     * �ݐϓ�������������擾����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_sMarketCode  Market�R�[�h
     * @@param l_sProductCode   �����R�[�h
     * @@param l_sIssueCode   �񍆃R�[�h
     * @@return TradedProduct  - �������
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
     * (get�����M���������())<BR>
     * <BR>
     * �����M������������擾����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_sMarketCode  Market�R�[�h
     * @@param l_sProductCode   �����R�[�h
     * @@param l_sIssueCode   �񍆃R�[�h
     * @@return TradedProduct  - �������
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
     * (get�敨�I�v�V�����������())<BR>
     * <BR>
     * �敨�I�v�V��������������擾����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_sUnderlyingProductCode   �����R�[�h
     * @@para�@@l_derivativeType�@@��n�^�C�v
     * @@param l_sMonthOfDelivery   ����
     * @@param l_dnlStrikePrice   �s�g���i
     * @@param l_sMarketCode  Market�R�[�h
     * @@return TradedProduct  - �������
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
     * (get�敨�I�v�V��������^�C�v())<BR>
     * <BR>
     * �敨�I�v�V��������^�C�v���擾����<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_baseDate   ���
     * @@return Date  - ��n��
     */
    private static OrderTypeEnum getIFOOrderTypeEnum(IfoOpenContractOrderSpec ��_spec)
    {
        boolean isFuturesOrder = IfoDerivativeTypeEnum.FUTURES.equals(��_spec.
            getDerivativeType());
        OrderTypeEnum l_orderType;
        if (isFuturesOrder)
        {
            if (��_spec.isBuyToOpenOrder())
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
            if (��_spec.isBuyToOpenOrder())
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
     * (get�f�t�H���g�U�֎�n��())<BR>
     * <BR>
     * �f�t�H���g�U�֎�n�����擾����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_baseDate   ���
     * @@return Date  - ��n��
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
     * (static���\�b�h)(get���������) <BR>
     * <BR>
     * �����i�⏕�����A�s��R�[�h�A�����R�[�h�A�񍆃R�[�h�j��� <BR>
     * ��������I�u�W�F�N�g���������ԋp����B <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_strMarketCode - (�s��R�[�h)
     * @@param l_strProductCode - (�����R�[�h)
     * @@param l_strIssueCode - (�񍆃R�[�h)
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
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
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
