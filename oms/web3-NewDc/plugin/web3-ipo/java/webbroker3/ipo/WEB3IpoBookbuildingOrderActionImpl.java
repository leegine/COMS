head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderActionImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �u�b�N�r���f�B���O�\������(WEB3IpoBookbuildingOrderActionImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo;

import java.sql.Timestamp;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.data.IpoBookbuildingOrderActionDao;
import webbroker3.ipo.data.IpoBookbuildingOrderActionParams;
import webbroker3.util.WEB3LogUtility;

/**
 * �u�b�N�r���f�B���O�\������
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderActionImpl implements OrderAction 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingOrderActionImpl.class);           
    /**
     * (�u�b�N�r���f�B���O�\�������s)<BR>
     * �u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g<BR>
     * �� �u�b�N�r���f�B���O�\������Params�N���X��DDL��莩����������B
     */
    private IpoBookbuildingOrderActionParams ipoBookbuildingOrderActionParams;
    
    /**
     * @@roseuid 411308340380
     */
    public WEB3IpoBookbuildingOrderActionImpl() 
    {
     
    }
    
    /**
     * (�u�b�N�r���f�B���O�\������)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �w�肵���u�b�N�r���f�B���O�\�������h�c�ɊY������s���u�b�N�r���f�B���O<BR>
     * �\�������e�[�u����茟������B<BR>
     * �������ʂ̃u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �R���X�g���N�^���R�[������B<BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B
     * @@param l_lngBookbuildingOrderActionId - �u�b�N�r���f�B���O�\������ID
     * @@return webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl
     * @@roseuid 40BFFBC3039A
     */
    public WEB3IpoBookbuildingOrderActionImpl(long l_lngBookbuildingOrderActionId) 
    {
        final String STR_METHOD_NAME = " WEB3IpoBookbuildingOrderActionImpl(long)";
        try
        {
            this.ipoBookbuildingOrderActionParams = (IpoBookbuildingOrderActionParams)
                IpoBookbuildingOrderActionDao.findRowByPk(l_lngBookbuildingOrderActionId);//DataFindException, DataQueryException, DataNetworkException
        }
        catch (DataFindException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataQueryException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
    }
    
    /**
     * (�u�b�N�r���f�B���O�\������)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �s�w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B
     * @@param l_bookbuildingOrderActionParams - (�u�b�N�r���f�B���O�\������Params)<BR>
     * �u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g<BR>
     * �� �u�b�N�r���f�B���O�\������Params�N���X��DDL��莩����������B
     * 
     * @@return webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl
     * @@roseuid 40BFFBC3037B
     */
    public WEB3IpoBookbuildingOrderActionImpl(Row l_bookbuildingOrderActionParams) 
    {
        final String STR_METHOD_NAME = " WEB3IpoBookbuildingOrderActionImpl(Row)";
        log.entering(STR_METHOD_NAME);
        
        if(!(l_bookbuildingOrderActionParams instanceof IpoBookbuildingOrderActionParams))
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������'IpoBookbuildingOrderActionParams' �ތ^�B";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        this.ipoBookbuildingOrderActionParams = (IpoBookbuildingOrderActionParams)l_bookbuildingOrderActionParams;
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s��ԋp����B
     * @@return Object
     * @@roseuid 40BFFAAB0223
     */
    public Object getDataSourceObject() 
    {
        return this.ipoBookbuildingOrderActionParams;
    }
    
    /**
     * (get�u�b�N�r���f�B���O�\�������h�c)<BR>
     * �igetOrderActionId�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.�u�b�N�r���f�B���O�\�������h�c ��ԋp����B
     * @@return long
     * @@roseuid 40BFFAAB0242
     */
    public long getOrderActionId() 
    {
        return this.ipoBookbuildingOrderActionParams.bookbuilding_order_action_id ;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return long
     * @@roseuid 40BFFAAB0262
     */
    public long getOrderId() 
    {
         return 0;
    }
    
    /**
     * (get�\������ԍ�)<BR>
     * �igetOrderActionSerialNo�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.�\������ԍ� ��ԋp����B
     * @@return int
     * @@roseuid 40BFFAAB0290
     */
    public int getOrderActionSerialNo() 
    {
        return this.ipoBookbuildingOrderActionParams.order_action_serial_no;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 40BFFAAB02B0
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
     * @@roseuid 40BFFAAB02BF
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
     * @@roseuid 40BFFAAB02CF
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
     * @@roseuid 40BFFAAB02DF
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
     * @@roseuid 40BFFAAB02EE
     */
    public double getExecutionQuantity() 
    {
        return 0;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 40BFFAAB02FE
     */
    public double getExecutionPrice() 
    {
        return 0;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 40BFFAAB032D
     */
    public double getConfirmedQuantity() 
    {
        return 0;
    }
    
    /**
     * (get�w�l)<BR>
     * �igetPrice�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.�w�l ��ԋp����B
     * @@return double
     * @@roseuid 40BFFAAB033C
     */
    public double getPrice() 
    {
        if(this.ipoBookbuildingOrderActionParams.getLimitPriceIsSet())
        {
            return this.ipoBookbuildingOrderActionParams.getLimitPrice();
        }
        else
        {
            return 0.0D/0.0D; 
        }
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 40BFFAAB035C
     */
    public double getConfirmedPrice() 
    {
        return 0;
    }
    
    /**
     * (get�쐬����)<BR>
     * �igetOrderActionTimestamp�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.�쐬���� ��ԋp����B
     * @@return Timestamp
     * @@roseuid 40BFFAAB037B
     */
    public Timestamp getOrderActionTimestamp() 
    {
        return this.ipoBookbuildingOrderActionParams.created_timestamp;
    }
    
    /**
     * SideEnum.BUY��ԋp����B
     * @@return SideEnum
     * @@roseuid 40BFFAAB038A
     */
    public SideEnum getSide() 
    {
        return SideEnum.BUY;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return OrderExpirationStatusEnum
     * @@roseuid 40BFFAAB03AA
     */
    public OrderExpirationStatusEnum getExpirationStatus() 
    {
        return null;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return OrderTypeEnum
     * @@roseuid 40BFFAAB03B9
     */
    public OrderTypeEnum getOrderType() 
    {
        return null;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return OrderEventTypeEnum
     * @@roseuid 40BFFAAB03C9
     */
    public OrderEventTypeEnum getOrderEventType() 
    {
        return null;
    }
    
    /**
     * (get�u�b�N�r���f�B���O�\�����)<BR>
     * �igetOrderStatus�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.�u�b�N�r���f�B���O�\����� ��ԋp����B
     * @@return OrderStatusEnum
     * @@roseuid 40BFFAAB03D9
     */
    public OrderStatusEnum getOrderStatus() 
    {
        return this.ipoBookbuildingOrderActionParams.order_status;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@return TradedProduct
     * @@roseuid 40BFFAAC0000
     */
    public TradedProduct getTradedProduct() 
    {
        return null;
    }
    
    /**
     * �u�b�N�r���f�B���O�\�������h�c��V�K�̔�(*1)���Athis.�u�b�N�r���f�B���O<BR>
     * �\�������s.�u�b�N�r���f�B���O�\�������h�c�ɃZ�b�g����B<BR>
     * <BR>
     * (*1) �u�b�N�r���f�B���O�\�������h�c�̐V�K�̔�<BR>
     * �@@�u�b�N�r���f�B���O�\������DAO.newPkValue()���\�b�h�ɂĎ擾����B<BR>
     * �@@�� �u�b�N�r���f�B���O�\������DAO�N���X��DDL��莩����������B
     * @@roseuid 40C03E6301EF
     */
    public void setNewId() 
    {
        final String STR_METHOD_NAME = " setNewId()"; 
        
        try
        {
            this.ipoBookbuildingOrderActionParams.bookbuilding_order_action_id =
                IpoBookbuildingOrderActionDao.newPkValue();//DataQueryException, DataNetworkException
        }
        catch (DataNetworkException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataQueryException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
    }
    
    /**
     * (get�u�b�N�r���f�B���O�\���h�c)<BR>
     * �igetOrderUnitId�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.�u�b�N�r���f�B���O�\���h�c ��ԋp����B
     * @@return long
     * @@roseuid 40D15E330342
     */
    public long getOrderUnitId() 
    {
        return this.ipoBookbuildingOrderActionParams.bookbuilding_order_action_id ;
    }
    
    /**
     * (get����ID)<BR>
     * �igetAccountId�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.�����h�c ��ԋp����B
     * @@return long
     * @@roseuid 40D15E4A014E
     */
    public long getAccountId() 
    {
        return this.ipoBookbuildingOrderActionParams.account_id;
    }
    
    /**
     * (get�⏕����ID)<BR>
     * �igetSubAccountId�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.�⏕�����h�c ��ԋp����B
     * @@return long
     * @@roseuid 40D15E56015E
     */
    public long getSubAccountId() 
    {
         return this.ipoBookbuildingOrderActionParams.sub_account_id;
    }
    
    /**
     * (get����)<BR>
     * �igetQuantity�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.���� ��ԋp����B
     * @@return double
     * @@roseuid 40D15E9A0229
     */
    public double getQuantity() 
    {
        if(this.ipoBookbuildingOrderActionParams.getQuantityIsSet())
        {
            return this.ipoBookbuildingOrderActionParams.getQuantity();
        }
        else
        {
            return 0.0D/0.0D;
        }
             
    }
    
    /**
     * (is���s)<BR>
     * �iisMarketOrder�̎����j<BR>
     * <BR>
     * �|�ithis.�u�b�N�r���f�B���O�\�������s.�w�l == 0�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �|�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 40D15F2A02C5
     */
    public boolean isMarketOrder() 
    {
        return this.ipoBookbuildingOrderActionParams.getLimitPrice() == 0;
    }
    
    /**
     * (getIPO����)<BR>
     * �igetProduct�̎����j<BR>
     * <BR>
     * this.�u�b�N�r���f�B���O�\�������s.IPO����ID�ɊY������IPO�����I�u�W�F�N�g��<BR>�ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO����ID���w�肵�AIPO�����𐶐�����B<BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@IPO����ID�F�@@this.�u�b�N�r���f�B���O�\���s.IPO����ID<BR>
     * <BR>
     * �Q�j�@@���������I�u�W�F�N�g��ԋp����B
     * @@return Product
     * @@roseuid 40D15F490286
     */
    public Product getProduct()
    {
        final String STR_METHOD_NAME = " getProduct()";
        log.entering(STR_METHOD_NAME);
        
        long l_lngIpoProductID = this.ipoBookbuildingOrderActionParams.getIpoProductId();
        Product l_ipoProduct = null;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoProductManagerImpl l_productManager = 
                (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
                
            l_productManager.getProduct(l_lngIpoProductID);
        }
        catch (NotFoundException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }

        
        log.exiting(STR_METHOD_NAME);
        return l_ipoProduct ;
    }
    
    /**
     * (createForUpdateParams ())<BR>
     * this.�u�b�N�r���f�B���O�\�������s���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬����iclone�j�B<BR>
     * <BR>
     * �쐬�����R�s�[�����g��this.�u�b�N�r���f�B���O�\�������s�ɃZ�b�g����B <BR>
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        IpoBookbuildingOrderActionParams l_ipoProductParams = new IpoBookbuildingOrderActionParams(this.ipoBookbuildingOrderActionParams);
        this.ipoBookbuildingOrderActionParams = l_ipoProductParams;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
