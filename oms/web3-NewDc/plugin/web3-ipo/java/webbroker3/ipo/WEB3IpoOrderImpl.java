head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\���N���X(WEB3IpoOrderImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���C�g (���u) �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>044
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>054
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3IpoOrderAcceptStatusDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.ipo.data.IpoBookbuildingOrderActionDao;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoOrderDao;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3OrderAppStatusDef;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�\���N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3IpoOrderImpl implements OrderUnit 
{
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderImpl.class);
    
    /**
     * IPO�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * �� IPO�\��Params�N���X��DDL��莩����������B
     */
    private IpoOrderParams ipoOrderParams;
    
    public WEB3IpoOrderImpl()
    {
        
            
    }
    
    /**
     * (IPO�\��)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �w�肵��IPO�\���h�c�ɊY������s��IPO�\���e�[�u����茟������B<BR>
     * �������ʂ�IPO�\���s�I�u�W�F�N�g�������Ɏw�肵�āA�R���X�g���N�^���R�[������B<BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B
     * @@param l_lngIpoOrderId - IPO�\��ID
     * @@roseuid 40BFFB280000
     */
    public WEB3IpoOrderImpl(long l_lngIpoOrderId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        
        this(IpoOrderDao.findRowByPk(l_lngIpoOrderId));

    }
    
    /**
     * (IPO�\��)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �s�w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B
     * @@param l_ipoOrderParams - (�u�b�N�r���f�B���O�\��Params)<BR>
     * �u�b�N�r���f�B���O�\���s�I�u�W�F�N�g<BR>
     * �� �u�b�N�r���f�B���O�\��Params�N���X��DDL��莩����������B
     * @@roseuid 40BFFB2703D9
     */
    public WEB3IpoOrderImpl(Row l_ipoOrderParams) 
    {

        ipoOrderParams = new IpoOrderParams((IpoOrderRow)l_ipoOrderParams);
        
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.IPO�\���s��ԋp����B
     * @@return Object
     * @@roseuid 40BFF9FE009C
     */
    public Object getDataSourceObject() 
    {
        
        final String STR_METHOD_NAME =
            " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams;
        

    }
    
    /**
     * �igetOrderUnitId�̎����j<BR>
     * <BR>
     * this.IPO�\���s.IPO�\���h�c ��ԋp����B
     * @@return long
     * @@roseuid 40BFF9FE00AC
     */
    public long getOrderUnitId() 
    {
        
        final String STR_METHOD_NAME =
            " getOrderUnitId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getIpoOrderId();

    }
    
    /**
     * (getIPO�\���h�c)<BR>
     * �igetOrderId�̎����j<BR>
     * <BR>
     * this.IPO�\���s.IPO�\���h�c ��ԋp����B
     * @@return long
     * @@roseuid 40BFF9FE00BC
     */
    public long getOrderId() 
    {
        
        final String STR_METHOD_NAME =
            " getOrderId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getIpoOrderId();
        
    }
    
    /**
     * (get����ID)<BR>
     * �igetAccountId�̎����j<BR>
     * <BR>
     * this.IPO�\���s.�����h�c ��ԋp����B
     * @@return long
     * @@roseuid 40BFF9FE00CB
     */
    public long getAccountId() 
    {
        
        final String STR_METHOD_NAME =
            " getAccountId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getAccountId();
        
    }
    
    /**
     * (get�⏕����ID)<BR>
     * �igetSubAccountId�̎����j<BR>
     * <BR>
     * this.IPO�\���s.�⏕�����h�c ��ԋp����B
     * @@return long
     * @@roseuid 40BFF9FE00DB
     */
    public long getSubAccountId() 
    {
        
        final String STR_METHOD_NAME =
            " getSubAccountId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getSubAccountId();
        
    }
    
    /**
     * (get���XID)<BR>
     * �igetBranchId�̎����j<BR>
     * <BR>
     * this.IPO�\���s.���X�h�c ��ԋp����B
     * @@return long
     * @@roseuid 40BFF9FE00EB
     */
    public long getBranchId() 
    {
        
        final String STR_METHOD_NAME =
            " getBranchId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getBranchId();
        
    }
    
    /**
     * (get�����ID)<BR>
     * �igetTraderId�̎����j<BR>
     * <BR>
     * this.IPO�\���s.����҂h�c ��ԋp����B
     * @@return long
     * @@roseuid 40BFF9FE00FA
     */
    public long getTraderId() 
    {

        return ipoOrderParams.getTraderId();
        
    }
    
    /**
     * (get�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * this.get�����h�c�ɊY������ڋq�I�u�W�F�N�g���擾���ԋp����B
     * @@return MainAccount
     * @@roseuid 40F65238006F
     */
    public MainAccount getMainAccount() 
    {
        
        final String STR_METHOD_NAME =
            " getMainAccount()";
        log.entering(STR_METHOD_NAME);
        
        MainAccount l_mainAccount = null;
        
        if(l_mainAccount == null)
        {
            
            try
            {
                
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                l_mainAccount = l_finApp.getAccountManager().getMainAccount(getAccountId());
                
            }
            catch (NotFoundException l_ex)
            {
                
                String l_strMsg = "MainAccount not found accountId : " + getAccountId();
                log.error(l_strMsg, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new RuntimeSystemException(l_strMsg, l_ex);                
                
            }
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;

    }
    
    /**
     * (get�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * this.get�⏕�����h�c�ɊY������⏕�����I�u�W�F�N�g���擾���ԋp����B
     * @@return SubAccount
     */
    public SubAccount getSubAccount() 
    {
        
        final String STR_METHOD_NAME =
            " getSubAccount()";
        log.entering(STR_METHOD_NAME);
        
        SubAccount l_subAccount = null;
        
        if(l_subAccount == null)
        {
            
            try
            {
                
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                l_subAccount = l_finApp.getAccountManager().getSubAccount(getAccountId(),getSubAccountId());
                
            }
            catch (NotFoundException l_ex)
            {
                
                String l_strMsg = "SubAccount not found subaccountId : " + getSubAccountId();
                log.error(l_strMsg, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new RuntimeSystemException(l_strMsg, l_ex);                
                
            }
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;

    }
    
    
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 40BFF9FE010A
     */
    public boolean isExpired() 
    {
        
        return false;
        
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 40BFF9FE0119
     */
    public boolean isFullyExecuted() 
    {
        
        return false;
        
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 40BFF9FE0129
     */
    public boolean isPartiallyExecuted() 
    {
        
        return false;
        
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 40BFF9FE0139
     */
    public boolean isUnexecuted() 
    {
        
        return false;
        
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 40BFF9FE0158
     */
    public double getConfirmedPrice() 
    {
        
        return 0;
     
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 40BFF9FE0168
     */
    public boolean isConfirmedPriceMarketOrder() 
    {
        
        return false;
     
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 40BFF9FE0177
     */
    public double getConfirmedQuantity() 
    {
        
        return 0;
     
    }
    
    /**
     * (get����)<BR>
     * �igetQuantity�̎����j<BR>
     * <BR>
     * this.IPO�\���s.���� ��ԋp����B
     * @@return double
     * @@roseuid 40BFF9FE0187
     */
    public double getQuantity() 
    {

        return ipoOrderParams.getQuantity();
         
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 40BFF9FE0196
     */
    public double getExecutedAmount() 
    {
        
        return 0;
     
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 40BFF9FE01A6
     */
    public double getExecutedQuantity() 
    {
        
        return 0;
     
    }
    
    /**
     * (get�w�l)<BR>
     * �igetLimitPrice�̎����j<BR>
     * <BR>
     * this.IPO�\���s.�w�l ��ԋp����B
     * @@return double
     * @@roseuid 40BFF9FE01B6
     */
    public double getLimitPrice() 
    {

        return ipoOrderParams.getLimitPrice();
     
    }
    
    /**
     * (is���s)<BR>
     * �iisMarketOrder�̎����j<BR>
     * <BR>
     * �|�ithis.IPO�\���s.�w�l == 0�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �|�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 40BFF9FE01C5
     */
    public boolean isMarketOrder() 
    {
        
        final String STR_METHOD_NAME = " isMarketOrder()";
        log.entering(STR_METHOD_NAME);
        
        double l_dblPrice = getLimitPrice();
        
        if(l_dblPrice == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            return true;
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
        
    }
    
    /**
     * (get�쐬����)<BR>
     * �igetReceivedTimestamp�̎����j<BR>
     * <BR>
     * this.IPO�\���s.�쐬���� ��ԋp����B
     * @@return Timestamp
     * @@roseuid 40BFF9FE01F4
     */
    public Timestamp getReceivedTimestamp() 
    {
        
        return ipoOrderParams.getCreatedTimestamp();
        
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return Timestamp
     * @@roseuid 40BFF9FE0204
     */
    public Timestamp getExpirationTimestamp() 
    {
        
        return null;
     
    }
    
    /**
     * (getIPO����)<BR>
     * �igetProduct�̎����j<BR>
     * <BR>
     * this.IPO�\���s.IPO����ID�ɊY������IPO�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO����ID���w�肵�AIPO�����𐶐�����B<BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@IPO����ID�F�@@this.IPO�\���s.IPO����ID<BR>
     * <BR>
     * �Q�j�@@���������I�u�W�F�N�g��ԋp����B<BR>
     * @@return Product
     * @@roseuid 40BFF9FE0213
     */
    public Product getProduct() 
    {
        
        final String STR_METHOD_NAME =
            " getProduct()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoProductImpl l_product = null;

        try
        {
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
                (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            
            l_product = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(ipoOrderParams.getIpoProductId());                
                
        }
        catch (NotFoundException l_ex)
        {
            
            String l_strMsg = "Order not found in ifo_order with productId : " + ipoOrderParams.getIpoProductId();
            log.error(l_strMsg, l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new RuntimeSystemException(l_strMsg, l_ex);                
            
        }

        
        log.exiting(STR_METHOD_NAME);
        return l_product;

    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return Order
     * @@roseuid 40BFF9FE0233
     */
    public Order getOrder() 
    {
        
        return null;
     
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution[]
     * @@roseuid 40BFF9FE0242
     */
    public OrderExecution[] getExecutions() 
    {
        
        return null;
     
    }
    
    /**
     * (getIPO�\���L�����)<BR>
     * �igetOrderOpenStatus�̎����j<BR>
     * <BR>
     * this.IPO�\���s.IPO�\���L����� ��ԋp����B
     * @@return OrderOpenStatusEnum
     * @@roseuid 40BFF9FE0262
     */
    public OrderOpenStatusEnum getOrderOpenStatus() 
    {
        
        return ipoOrderParams.getOrderOpenStatus();
     
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return OrderTypeEnum
     * @@roseuid 40BFF9FE0271
     */
    public OrderTypeEnum getOrderType() 
    {
        
        return null;
     
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return OrderCategEnum
     * @@roseuid 40BFF9FE0281
     */
    public OrderCategEnum getOrderCateg() 
    {
        
        return null;
     
    }
    
    /**
     * (get�ŋ敪)<BR>
     * �igetTaxType�̎����j<BR>
     * <BR>
     * this.IPO�\���s.�ŋ敪 ��ԋp����B
     * @@return TaxTypeEnum
     * @@roseuid 40BFF9FE0290
     */
    public TaxTypeEnum getTaxType() 
    {
        
        return ipoOrderParams.getTaxType();
     
    }
    
    /**
     * SideEnum.BUY��ԋp����B
     * @@return SideEnum
     * @@roseuid 40BFF9FE02A0
     */
    public SideEnum getSide() 
    {
        
        return SideEnum.BUY;

    }
    
    /**
     * (get�u�b�N�r���f�B���O�\�����)<BR>
     * �igetOrderStatus�̎����j<BR>
     * <BR>
     * this.IPO�\���s.�u�b�N�r���f�B���O�\����� ��ԋp����B
     * @@return OrderStatusEnum
     * @@roseuid 40BFF9FE02B0
     */
    public OrderStatusEnum getOrderStatus() 
    {

        return ipoOrderParams.getOrderStatus();

    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return OrderExpirationStatusEnum
     * @@roseuid 40BFF9FE02BF
     */
    public OrderExpirationStatusEnum getExpirationStatus() 
    {
        
        return null;
        
    }
    
    /**
     * (get���J��)<BR>
     * �igetDeliveryDate�̎����j<BR>
     * <BR>
     * this.IPO�\���s.IPO����ID�ɊY������IPO�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO����ID���w�肵�AIPO�����𐶐�����B<BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@IPO����ID�F�@@this.IPO�\���s.IPO����ID<BR>
     * <BR>
     * �Q�j�@@��������IPO����.���J����ԋp����B<BR>
     * @@return Date
     * @@roseuid 40BFF9FE02CF
     */
    public Date getDeliveryDate() 
    {
        
        final String STR_METHOD_NAME = " getDeliveryDate()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoProductImpl l_product = null;
        IpoProductRow l_productRow = null;
        try
        {
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
                (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            
            l_product = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(ipoOrderParams.getIpoProductId());                
            l_productRow = (IpoProductRow)(l_product.getDataSourceObject());
                
        }
        catch (NotFoundException l_ex)
        {
            
            String l_strMsg = "Order not found in ifo_order with productId : " + ipoOrderParams.getIpoProductId();
            log.error(l_strMsg, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(l_strMsg, l_ex);                
            
        }
 
        log.exiting(STR_METHOD_NAME);
        return l_productRow.getPublicOfferingDate();

    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return TradedProduct
     * @@roseuid 40BFF9FE02DF
     */
    public TradedProduct getTradedProduct() 
    {
        
         return null;
     
    }
    
    /**
     * (get�w���\�����)<BR>
     * this.IPO�\���s.�w���\����� ��ԋp����B
     * @@return double
     * @@roseuid 40DBDD870295
     */
    public double getPayAmount() 
    {
        
        if(ipoOrderParams.getPayAmountIsNull())
        {
            
            return 0.0D/0.0D;
            
        }
        return ipoOrderParams.getPayAmount();

    }
    
    /**
     * (get�\���\����)<BR>
     * IPO�\�����e�𔻒f���A�Y������\���\���󋵋敪��ԋp����B<BR>
     * <BR>
     * �@@[�\���\���󋵋敪]<BR>
     * �@@01�F�@@�u�b�N�r���f�B���O�\����<BR>
     * �@@02�F�@@�u�b�N�r���f�B���O�L�����Z��<BR>
     * �@@03�F�@@���I<BR>
     * �@@04�F�@@���I�L�����Z��<BR>
     * �@@05�F�@@�\����<BR>
     * �@@06�F�@@����<BR>
     * �@@07�F�@@�⌇<BR>
     * �@@08�F�@@�⌇�L�����Z��<BR>
     * �@@09�F�@@�⌇�\����<BR>
     * �@@10�F�@@�⌇����<BR>
     * �@@11�F�@@�⌇���I<BR>
     * �@@12�F�@@���I<BR>
     * <BR>
     * 
     * �P�j�@@���I�҂̔���<BR>
     * �@@this.IPO�\���s.���I���� == �h���I�h�̏ꍇ<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h0�F�@@DEFAULT�i�����l�j�h�j &&<BR>
     * �@@�@@�@@�ithis.getIPO����().is�w���\���I���i���Аݒ�j() == true�j �̏ꍇ�A�h<BR>���I�L�����Z���h��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h0�F�@@DEFAULT�i�����l�j�h�j &&<BR>
     * �@@�@@�@@�ithis.getIPO����().is�w���\���I���i���Аݒ�j() == false�j �̏ꍇ�A�h<BR>���I�h��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h1�F�@@�w���\���h�j &&<BR>
     * �@@�@@�@@�ithis.IPO�\���s.��t��� == �h0�F�@@DEFAULT�i�����l�j�h�j �̏ꍇ�A�h<BR>�\���ρh��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h1�F�@@�w���\���h�j &&<BR>
     * �@@�@@�@@�ithis.IPO�\���s.��t��� != �h0�F�@@DEFAULT�i�����l�j�h�j �̏ꍇ�A<BR>�h���ρh��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h2�F�@@���ށh�j �̏ꍇ�A<BR>
     * �h���I�L�����Z���h��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�⌇�҂̔���<BR>
     * �@@this.IPO�\���s.���I���� == �h�⌇�h�̏ꍇ<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h0�F�@@DEFAULT�i�����l�j�h�j &&<BR>
     * �@@�@@�@@�ithis.getIPO����().is�w���\���I���i���Аݒ�j() == true <BR>
     *           Or   �ithis.IPO�\���s.���I���ʁi�J��j == �h���I�h�j�j�̏ꍇ�A�h<BR>�⌇�L�����Z���h��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h0�F�@@DEFAULT�i�����l�j�h�j &&<BR>
     * �@@�@@�@@�ithis.getIPO����().is�w���\���I���i���Аݒ�j() == false�j �̏ꍇ�A�h<BR>�⌇�h��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h1�F�@@�w���\���h�j &&<BR>
     * �@@�@@�@@�ithis.IPO�\���s.���I���ʁi�J��j == �h0�FDEFAULT�i�����I�j�h�j ��<BR>�ꍇ�A�h�⌇�\���ρh��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h1�F�@@�w���\���h�j &&<BR>
     * �@@�@@�@@�ithis.IPO�\���s.���I���ʁi�J��j == �h���I�h�j �̏ꍇ�A�h�⌇���ρh<BR>��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h1�F�@@�w���\���h�j &&<BR>
     * �@@�@@�@@�ithis.IPO�\���s.���I���ʁi�J��j == �h���I�h�j �̏ꍇ�A�h�⌇���I�h��<BR>�ԋp����B<BR>
     * <BR>
     * �@@�@@�|�ithis.IPO�\���s.�w���\���敪 == �h2�F�@@���ށh�j �̏ꍇ�A<BR>
     * �h�⌇�L�����Z���h��ԋp����B<BR>
     * <BR>
     * �R�j�@@���I�҂̔���<BR>
     * �@@this.IPO�\���s.���I���� == �h���I�h�̏ꍇ�A�h���I�h��ԋp����B<BR>
     * <BR>
     * �S�j�@@���I�O�̔���<BR>
     * �@@this.IPO�\���s.���I���� == �h0�FDEFAULT�i�����I�j�h�̏ꍇ<BR>
     * <BR>
     * �@@�|�ithis.getIPO�\���L�����() == OrderOpenStatusEnum.OPEN�j�̏ꍇ�A<BR>
     * �@@�@@�h�u�b�N�r���f�B���O�\���ρh��ԋp����B<BR>
     * <BR>
     * �@@�|�ithis.getIPO�\���L�����() == OrderOpenStatusEnum.CLOSE�j�̏ꍇ�A<BR>
     * �@@�@@�h�u�b�N�r���f�B���O�L�����Z���h��ԋp����B<BR>
     * <BR>
     * @@return String
     * @@roseuid 40DFA29000F9
     */
    public String getOrderOfferState() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME =
            " getOrderOfferState()";
        log.entering(STR_METHOD_NAME);
        
        //this.IPO�\���s.���I���� == �h���I�h�̏ꍇ
        if((WEB3LotResultDef.ELECTION).equals(ipoOrderParams.getLotResult()))
        {
            
            //���I�L�����Z���h��ԋp����
            boolean l_bln = ((WEB3IpoProductImpl)getProduct()).isOfferEnd();
            if((WEB3OfferingDivDef.DEFAULT).equals(ipoOrderParams.getOfferingDiv()) && (l_bln))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.ELECTION_DEL;
                
            }
            //���I�h��ԋp����
            if((WEB3OfferingDivDef.DEFAULT).equals(ipoOrderParams.getOfferingDiv()) && (!l_bln))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.ELECTION;
                
            }
            //�\���ρh��ԋp����
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                ((WEB3IpoOrderAcceptStatusDef.DEFAULT).equals(ipoOrderParams.getAcceptStatus())))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.APPLICATION;
                
            }
            //�h���ρh��ԋp����
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                !((WEB3IpoOrderAcceptStatusDef.DEFAULT.equals(ipoOrderParams.getAcceptStatus()))))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.EXECUTED;
                
            }
            //���I�L�����Z���h��ԋp����B
            if((WEB3OfferingDivDef.REFUSAL).equals(ipoOrderParams.getOfferingDiv()))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.ELECTION_DEL;
                 
            }
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get�\���\����");
            
        }
        else if((WEB3LotResultDef.SUPPLEMENT).equals(ipoOrderParams.getLotResult()))//�⌇�҂̔���
        {
            
            //�⌇�L�����Z���h��ԋp����
            boolean l_bln = ((WEB3IpoProductImpl)getProduct()).isOfferEnd();
            if((WEB3OfferingDivDef.DEFAULT).equals(ipoOrderParams.getOfferingDiv()) 
                && (l_bln || WEB3LotResultRetryDef.DEFEAT.equals(ipoOrderParams.getLotResultRetry())))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_DEL;
                
            }
            //�⌇�h��ԋp����B
            if((WEB3OfferingDivDef.DEFAULT).equals(ipoOrderParams.getOfferingDiv()) && (!l_bln))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT;
                
            }
            //�⌇�\���ρh��ԋp����
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                ((WEB3LotResultRetryDef.DEFAULT).equals(ipoOrderParams.getLotResultRetry())))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_APPLICATION;
                
            }
            //�h�⌇���ρh��ԋp����
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                (((WEB3LotResultRetryDef.ELECTION).equals(ipoOrderParams.getLotResultRetry()))))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_EXECUTED;
                
            }
            //�h�⌇���I�h��ԋp����
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                ((WEB3LotResultRetryDef.DEFEAT).equals(ipoOrderParams.getLotResultRetry())))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_DEFEAT;
                
            }            
            //�⌇�L�����Z���h��ԋp����B
            if((WEB3OfferingDivDef.REFUSAL).equals(ipoOrderParams.getOfferingDiv()))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_DEL;
                 
            }
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get�\���\����");            
            
        }
        else if((WEB3LotResultDef.DEFEAT).equals(ipoOrderParams.getLotResult()))//���I�҂̔���
        {
            
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderAppStatusDef.DEFEAT;
            
        }
        else if((WEB3LotResultDef.DEFAULT).equals(ipoOrderParams.getLotResult()))                     //���I�O�̔���
        {
            
            if(OrderOpenStatusEnum.OPEN.equals(getOrderOpenStatus()))//�h�u�b�N�r���f�B���O�\���ρh��ԋp����
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.BOOK_BUILDING_DEMAND_COMPLETE;
                
            }
            
            if(OrderOpenStatusEnum.CLOSED.equals(getOrderOpenStatus()))//�h�u�b�N�r���f�B���O�L�����Z���h��ԋp����
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.BOOK_BUILDING_DEL;
                
            }
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get�\���\����");
            
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get�\���\����"); 
            
        }

    }
    
    /**
     * (get�\�����i)<BR>
     * �\�����i���擾����B<BR>
     * <BR>
     * this.IPO�\���s.�v�Z�P�� ��ԋp����B<BR> 
     * @@return double
     * @@roseuid 40E511ED00AF
     */
    public double getOrderPrice() 
    {
        
        final String STR_METHOD_NAME =
            " getOrderPrice()";
        log.entering(STR_METHOD_NAME);
        
        double l_dblPrice = this.ipoOrderParams.getPrice();
        
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;

    }
    
    /**
     * (get�X�V�҃R�[�h)<BR>
     * this.IPO�\���s.�X�V�҃R�[�h ��ԋp����B
     * @@return String
     * @@roseuid 40D912890249
     */
    public String getUpdaterCode() 
    {

        return ipoOrderParams.getLastUpdater();

    }
    
    /**
     * (get�ŐV�����h�c)<BR>
     * �{�I�u�W�F�N�g���ێ�����u�b�N�r���f�B���O�\�������ŁA�ŐV�̗�����<BR>
     * �u�b�N�r���f�B���O�\�������h�c���擾����B<BR>
     * <BR>
     * �P�j�@@this.get�u�b�N�r���f�B���O�\������()�ɂė������擾����B<BR>
     * �Q�j�@@�擾���������̂����A�ŐV�̗���(*1)�̃u�b�N�r���f�B���O�\�������h�c��<BR>�ԋp����B<BR>
     * <BR>
     * (*1) �ŐV�̗����̔���<BR>
     * this.IPO�\���s.�\�������ŏI�ԍ� == <BR>
     * �u�b�N�r���f�B���O�\������.get�\������ԍ�<BR>
     * <BR>
     * @@return long
     * @@roseuid 40D7C797028B
     */
    public long getLastOrderActionId() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getLastOrderActionId()";
        log.entering(STR_METHOD_NAME);
        
        OrderAction[] l_orderAction = this.getOrderActions();
        long l_lngLength = l_orderAction.length;
        
        for(int i = 0; i < l_lngLength; i++ )
        {
            
            int l_intNo = l_orderAction[i].getOrderActionSerialNo();
            long l_lngId  = l_orderAction[i].getOrderActionId();
            
            if(this.ipoOrderParams.getLastOrderActionSerialNo() == l_intNo)
            {
                
                log.exiting(STR_METHOD_NAME);
                return l_lngId;
                
            }

        }
        log.exiting(STR_METHOD_NAME);
        throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get�ŐV�����h�c");
        
    }
    
    /**
     * (get�u�b�N�r���f�B���O�\������)<BR>
     * �igetOrderActions�j<BR>
     * <BR>
     * ���Y�I�u�W�F�N�g�Ɋ֘A����u�b�N�r���\���������擾����B<BR>
     * <BR>
     * �P�j�@@�u�b�N�r���f�B���O�\�������擾Dao.findRowByIpoOrderId()�ɂ�<BR>
     * �֘A����u�b�N�r���\�������s�̃��X�g���擾����B<BR>
     * <BR>
     * [findRowsByOrderId()�Ɏw�肷�����]<BR>
     * this.getIPO�\���h�c()<BR>
     * <BR>
     * ���u�b�N�r���\������Dao��DDL�ɂĎ�����������B<BR>
     * <BR>
     * �Q�j�@@�擾�����e�v�f�������Ɏw�肵�A�u�b�N�r���f�B���O�\�������I�u�W�F�N�g��<BR>��������B<BR>
     * <BR>
     * [�R���X�g���N�^�̈���]<BR>
     * �u�b�N�r���f�B���O�\������Params�F�@@�u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g<BR>
     * <BR>
     * �R�j�@@�����������ׂĂ̗v�f��z��ϊ�����B<BR>
     * <BR>
     * �S�j�@@sort<BR>
     * �@@�R�j�Ŏ擾�����u�b�N�r���f�B���O�\�������̔z����AWEB3ArraysUtility.sort()<BR>
     * �ɂ�sort���ĕԋp����B<BR>
     * <BR>
     * �@@[sort�Ɏw�肷�����]<BR>
     * �@@Object[]�F�@@�Q�j�Ŏ擾�����u�b�N�r���f�B���O�\�������̔z��<BR>
     * �@@Comparator[]�F�@@<BR>
     * �@@�@@com[0] = new �u�b�N�r���f�B���O�\������.�쐬����<BR>
     * Comparator(�����i�Fasc�j)<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction[]
     * @@roseuid 40E3F8C001AA
     */
    public OrderAction[] getOrderActions() 
    {
        
        final String STR_METHOD_NAME =
            " getOrderActions()";
        log.entering(STR_METHOD_NAME);
        
        List l_list;
        
        try
        {
            //�P�j�@@�u�b�N�r���f�B���O�\�������擾Dao.findRowsByIpoOrderId()�ɂ�
            l_list = IpoBookbuildingOrderActionDao.findRowsByIpoOrderId(this.getOrderId());
            
        }
        catch(DataException de)
        {
            
            String msg = "Exception while getting IpoOrderActionsRows from iPo_order_action table for order_unit_id:"
                 + getOrderUnitId();
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(msg, de);
            
        }

        int l_intSize = l_list.size();
        OrderAction[] l_orderActions = new OrderAction[l_intSize];
        ArrayList l_arrayList = new ArrayList();
        for(int i = 0; i < l_intSize; i++)
        {

            WEB3IpoBookbuildingOrderActionImpl l_ipoBookbuildingOrderAction = 
                new WEB3IpoBookbuildingOrderActionImpl((IpoBookbuildingOrderActionRow)l_list.get(i));   
            l_arrayList.add(l_ipoBookbuildingOrderAction);
            
        }
        
        l_arrayList.toArray(l_orderActions);
        Comparator[] l_comparators = new Comparator[1]; 
        l_comparators[0] = new WEB3IpoBookbuildingOrderActionCreatedTimestampComparator(WEB3AscDescDef.ASC);
        WEB3ArraysUtility.sort(l_orderActions,l_comparators);

        log.exiting(STR_METHOD_NAME);
        return l_orderActions;

    }
    
    /**
     * (is�w���\��)<BR>
     * �w���\���ς��𔻒肷��B<BR>
     * <BR>
     * �|�ithis.IPO�\���s.�w���\���敪 == �h�w���\���h�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 40DB9C6E03A6
     */
    public boolean isOffered() 
    {

        return (WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv());
                
    }
    
    /**
     * (is����)<BR>
     * ���ލς��𔻒肷��B<BR>
     * <BR>
     * �|�ithis.IPO�\���s.�w���\���敪 == �h���ށh�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 40DB9D1801E6
     */
    public boolean isDecline() 
    {
        
        return (WEB3OfferingDivDef.REFUSAL).equals(ipoOrderParams.getOfferingDiv());
          
    }
    
    /**
     * (is���I��)<BR>
     * ���I���Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �|�ithis.IPO�\���s.���I���� == �h���I�h�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40DB9D610210
     */
    public boolean isElected() 
    {
        
        final String STR_METHOD_NAME =
            " isElected()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return (WEB3LotResultDef.ELECTION).equals(ipoOrderParams.getLotResult());

    }
    
    /**
     * (is�⌇��)<BR>
     * �⌇�҂��𔻒肷��B<BR>
     * <BR>
     * �|�ithis.IPO�\���s.���I���� == �h�⌇�h�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 40DB9D8A0114
     */
    public boolean isWaiting() 
    {
        
        return (WEB3LotResultDef.SUPPLEMENT).equals(ipoOrderParams.getLotResult());

    }
    
    /**
     * (is���I��)<BR>
     * ���I���𔻒肷��B<BR>
     * <BR>
     * �|�ithis.IPO�\���s.���I���� == �h���I�h�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 40DB9DAA0392
     */
    public boolean isRejected() 
    {

        return (WEB3LotResultDef.DEFEAT).equals(ipoOrderParams.getLotResult());

    }
 
    /**
     * (is�J�㗎�I��)<BR>
     * �J�㗎�I���𔻒肷��B <BR>
     * <BR>
     *   �|�ithis.IPO�\���s.���I���ʁi�J��j == �h���I�h�j�̏ꍇ�Atrue��ԋp����B<BR>
     *   �|�ȊO�Afalse��ԋp����B
     * @@return boolean
     */
    public boolean isAdvanceRejected() 
    {

        return (WEB3LotResultRetryDef.DEFEAT).equals(ipoOrderParams.getLotResultRetry());

    } 
    
    /**
     * IPO�\���h�c��V�K�̔�(*1)���Athis.IPO�\���s.IPO�\���h�c�ɃZ�b�g����B<BR>
     * <BR>
     * (*1) IPO�\���h�c�̐V�K�̔�<BR>
     * �@@IPO�\��DAO.newPkValue()���\�b�h�ɂĎ擾����B<BR>
     * �@@�� IPO�\��DAO�N���X��DDL��莩����������B
     * @@roseuid 40C03E2B0356
     */
    public void setNewId() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " setNewId()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            long l_lngId = IpoOrderDao.newPkValue();
            this.ipoOrderParams.setIpoOrderId(l_lngId);            
            
        }
        catch (DataNetworkException ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"IPO�\��DAO.newPkValue()");
            
        }
        catch(DataQueryException ex1)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"IPO�\��DAO.newPkValue()");
            
        }

    }
    
    /**
     * (set�\�������z�v�Z����)<BR>
     * �\�������z�v�Z���ʂ̓��e���I�u�W�F�N�g�ɃZ�b�g����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g�擾<BR>
     * �@@����.IPO�\��.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g<BR>
     * �@@�ȉ��̒ʂ�A�s�I�u�W�F�N�g�ɃZ�b�g���s���B<BR>
     * <BR>
     * �@@IPO�\���s.�v�Z�P�� = �\�������z�v�Z����.�v�Z�P��<BR>
     * �@@IPO�\���s.��l�i�����j = �\�������z�v�Z����.��l�i�����j<BR>
     * �@@IPO�\���s.�\�������z = �\�������z�v�Z����.�\�������z
     * @@param l_bookbuildingPriceCalcResult - �\�������z�v�Z���ʃI�u�W�F�N�g
     * @@roseuid 40D9052D0372
     */
    public void setBookbuildingPriceCalcResult(WEB3IpoBookbuildingPriceCalcResult l_bookbuildingPriceCalcResult) 
    {
        
        final String STR_METHOD_NAME =
            " setBookbuildingPriceCalcResult(WEB3IpoBookbuildingPriceCalcResult)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblCalcUnitPrice = l_bookbuildingPriceCalcResult.getPrice();
        double l_dblBasePrice     = l_bookbuildingPriceCalcResult.getCurrentPrice();
        double l_EquivalentPrice  = l_bookbuildingPriceCalcResult.getBookbuildingPrice();
        
        this.ipoOrderParams.setPrice(l_dblCalcUnitPrice);
        if(!Double.isNaN(l_dblBasePrice))
        {
            this.ipoOrderParams.setCurrentPrice(l_dblBasePrice);
        }
        else
        {
            this.ipoOrderParams.setCurrentPrice(null);
        }
        this.ipoOrderParams.setBookbuildingPrice(l_EquivalentPrice);
        
        log.exiting(STR_METHOD_NAME);
         
    }
    
    /**
     * (�w���\��)<BR>
     * IPO�\���I�u�W�F�N�g�ɍw���\�������Z�b�g����B<BR>
     * <BR>
     * �P�j�@@IPO�����I�u�W�F�N�g�擾<BR>
     * �@@this.getIPO����()�ɂ�IPO�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Z�b�g<BR>
     * �ȉ��̒ʂ�Athis.IPO�����s�̃v���p�e�B�ɒl���Z�b�g����B<BR>
     * <BR>
     * �@@this.IPO�\���s.�w���\������ == �w���\������<BR>
     * �@@this.IPO�\���s.�w���\����� == �i�w���\������ * IPO����.���J���i�j<BR>
     * �@@this.IPO�\���s.�w���\���敪 == �h�w���\���h<BR>
     * �@@this.IPO�\���s.�ŋ敪 == �ŋ敪<BR>
     * �@@this.IPO�\���s.�w���\���^���ޓ��� == ���ݓ���(*1)<BR>
     * �@@this.IPO�\���s.�X�V�҃R�[�h == (*2)<BR>
     * <BR>
     * �@@(*1)�@@���ݓ���<BR>
     * �@@TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@(*2)�@@�X�V�҃R�[�h<BR>
     * �@@�@@�i����� == null�j�̏ꍇ<BR>
     * �@@�@@�@@�|�����h�c�ɊY������ڋq.getAccountCode()<BR>
     * �@@�@@�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�|�����.getTraderCode()�@@
     * @@param l_trader - (�����)<BR>
     * ����ҁi���ҁj�I�u�W�F�N�g
     * 
     * @@param l_dblOfferQuantity - �w���\������
     * @@param l_taxType - �ŋ敪
     * @@roseuid 40DBDA1F00DB
     */
    public void offer(Trader l_trader, double l_dblOfferQuantity, TaxTypeEnum l_taxType) 
    {
        
        final String STR_METHOD_NAME =
            " offer(Trader, double, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoProductImpl l_product = null;
        
        l_product = (WEB3IpoProductImpl)(this.getProduct());
        IpoProductRow l_productRow = (IpoProductRow)l_product.getDataSourceObject();
        
        this.ipoOrderParams.setApplicationQuantity(l_dblOfferQuantity);
        this.ipoOrderParams.setPayAmount(l_dblOfferQuantity*(l_productRow.getPublicPrice()));
        this.ipoOrderParams.setOfferingDiv(WEB3OfferingDivDef.PURCHASE_APPLICATION);
        this.ipoOrderParams.setTaxType(l_taxType);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        
        this.ipoOrderParams.setOfferingTimestamp(l_processTime);
        
        String l_LastUpdater = null;
        
        if(l_trader == null)
        {
            
            l_LastUpdater = this.getMainAccount().getAccountCode();
            
        }
        else
        {
            
            l_LastUpdater = l_trader.getTraderCode();
            
        }

        this.ipoOrderParams.setLastUpdater(l_LastUpdater);
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (����)<BR>
     * IPO�\���I�u�W�F�N�g�Ɏ��ޏ����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�v���p�e�B�Z�b�g<BR>
     * �ȉ��̒ʂ�Athis.IPO�\���s�̃v���p�e�B�ɒl���Z�b�g����B<BR>
     * <BR>
     * �@@this.IPO�\���s.�w���\������ == 0<BR>
     * �@@this.IPO�\���s.�w���\����� == 0<BR>
     * �@@this.IPO�\���s.�w���\���敪 == �h���ށh<BR>
     * �@@this.IPO�\���s.�ŋ敪 == null<BR>
     * �@@this.IPO�\���s.�w���\���^���ޓ��� == ���ݓ���(*1)<BR>
     * �@@this.IPO�\���s.�X�V�҃R�[�h == (*2)<BR>
     * <BR>
     * �@@(*1)�@@���ݓ���<BR>
     * �@@TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@(*2)�@@�X�V�҃R�[�h<BR>
     * �@@�@@�i����� == null�j�̏ꍇ<BR>
     * �@@�@@�@@�|�����h�c�ɊY������ڋq.getAccountCode()<BR>
     * �@@�@@�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�|�����.getTraderCode()�@@<BR>
     * @@param l_trader - (�����)<BR>
     * ����ҁi���ҁj�I�u�W�F�N�g
     * @@roseuid 40DBE3C70186
     */
    public void decline(Trader l_trader) 
    {
        
        final String STR_METHOD_NAME =
            " ��ecline(Trader)";
        log.entering(STR_METHOD_NAME);
        
        this.ipoOrderParams.setApplicationQuantity(0);
        this.ipoOrderParams.setPayAmount(0);
        this.ipoOrderParams.setOfferingDiv(WEB3OfferingDivDef.REFUSAL);
        this.ipoOrderParams.setTaxType(null);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        
        this.ipoOrderParams.setOfferingTimestamp(l_processTime);
        
        String l_LastUpdater = null;
        
        if(l_trader == null)
        {
            
            l_LastUpdater = this.getMainAccount().getAccountCode();
            
        }
        else
        {
            
            l_LastUpdater = l_trader.getTraderCode();
            
        }

        
        this.ipoOrderParams.setLastUpdater(l_LastUpdater);
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (is���I���ʍX�V��)<BR>
     * ���I���ʍ��ڂ��X�V�ς��𔻒肷��B<BR>
     * <BR>
     * �|�ithis.IPO�\���s.���I���� == 0�FDEFAULT�i�����I�j�j�̏ꍇ�Afalse��<BR>�ԋp����B<BR>
     * �|�ȊO�Atrue��ԋp����B
     * @@return boolean
     * @@roseuid 40F51CB200D4
     */
    public boolean isLotResultUpdated() 
    {

        return !(WEB3LotResultDef.DEFAULT).equals(ipoOrderParams.getLotResult());

    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h���擾����B<BR>
     * <BR>
     * AccountManager.getMainAccount()�ɂāA<BR>
     * this.get�����h�c()�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * �擾���Čڋq�I�u�W�F�N�g.getAccountCode()��ԋp����B
     * @@roseuid 40F5225C0122
     */
    public String getAccountCode() 
    {
        
        final String STR_METHOD_NAME =
            " getAccountCode()";
        log.entering(STR_METHOD_NAME);
        
        MainAccount l_mainAccount = null;
        
        try
        {
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            l_mainAccount = l_finApp.getAccountManager().getMainAccount(getAccountId());
            
            log.exiting(STR_METHOD_NAME);
            return l_mainAccount.getAccountCode();
            
        }
        catch (NotFoundException l_ex)
        {
                
            String l_strMsg = "MainAccount not found accountId : " + getAccountId();
            log.error(l_strMsg, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(l_strMsg, l_ex); 
             
        }

    }
    
    /**
     * (createForUpdateParams ())<BR>
     * this.IPO�\���s���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬����iclone�j�B<BR>
     * <BR>
     * �쐬�����R�s�[�����g��this.IPO�\���s�ɃZ�b�g����B<BR>
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        
        IpoOrderParams l_ipoOrderParams = new IpoOrderParams(this.ipoOrderParams);
        this.ipoOrderParams = l_ipoOrderParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is�w���\���E���މ\)<BR>
     * �w���\���E���ނ��\���𔻒肷��B<BR>
     * �ȉ��̏����@@�܂��́A�A�ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * [�����@@]�@@���V�K���I�ŗ��I���Ă���ꍇ<BR>
     * �ithis.is���I��() == false�j && �ithis.is�⌇��() == false�j<BR>
     * <BR>
     * [�����A]�@@���J�㒊�I�ŗ��I���Ă���ꍇ<BR>
     * IPO�\��.is�J�㗎�I��() == true<BR>
     * <BR>
     * <BR>
     * �ǂ���ɂ����Ă͂܂�Ȃ��ꍇ�Atrue��ԋp����B<BR>
     * @@return boolean<BR>
     */
    public boolean isOfferDeclinePossible()
    {
        final String STR_METHOD_NAME = " isOfferDeclinePossible()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnOfferDeclinePossible = true;
        if (!this.isElected() && !this.isWaiting())
        {
            log.debug("�V�K���I�ŗ��I���Ă���ꍇ");
            l_blnOfferDeclinePossible = false;
        }
        if (this.isAdvanceRejected())
        {
            log.debug("�J�㒊�I�ŗ��I���Ă���ꍇ");
            l_blnOfferDeclinePossible = false;
        }        
        
        log.exiting(STR_METHOD_NAME);
        return l_blnOfferDeclinePossible;
    }

}
@
