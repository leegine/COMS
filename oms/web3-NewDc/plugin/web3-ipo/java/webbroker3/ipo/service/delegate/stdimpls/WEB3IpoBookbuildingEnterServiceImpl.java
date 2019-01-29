head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingEnterServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�Q���T�[�r�X�����N���X(WEB3IpoBookbuildingEnterServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.message.WEB3IPOProductInfoRequest;
import webbroker3.ipo.message.WEB3IPOProductInfoResponse;
import webbroker3.ipo.message.WEB3IPOPublicOfferingProductUnit;
import webbroker3.ipo.message.WEB3IPOTermUnit;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingEnterService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO�u�b�N�r���f�B���O�Q���T�[�r�X�����N���X
 * 
 * @@author �A�C��
 * @@version 1.0*
 */
public class WEB3IpoBookbuildingEnterServiceImpl 
    extends WEB3IpoClientRequestService implements WEB3IpoBookbuildingEnterService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingEnterServiceImpl.class);    
    /**
     * @@roseuid 4112F18F0149
     */
    public WEB3IpoBookbuildingEnterServiceImpl() 
    {
     
    }
    
    /**
     * IPO�u�b�N�r���f�B���O�Q�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�u�b�N�r���f�B���O�Q�����N�G�X�g�̏ꍇ<BR>
     * �@@�|get�����ꗗ()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�ʖ�����񃊃N�G�X�g�̏ꍇ<BR>
     * �@@�|get�����ڍ�()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D28008020A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPOBookBuildingEnterRequest)
        {
            //IPO�u�b�N�r���f�B���O�Q��
            l_response = getProductList(
                (WEB3IPOBookBuildingEnterRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOProductInfoRequest)
        {
            //IPO�ʖ������
            l_response = getDetailedProduct(
                (WEB3IPOProductInfoRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������WEB3IPOBookBuildingEnterRequest," + 
                "WEB3IPOProductInfoRequest�ތ^�B";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����ꗗ)<BR>
     * IPO�����ꗗ�擾�������s���B<BR>
     * �i�u�b�N�r���f�B���O�Q����ʂɕ\���j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸގQ���jget�����ꗗ�v�Q�ƁB
     * @@param l_request - IPO�u�b�N�r���f�B���O�Q�����N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingEnterResponse
     * @@roseuid 40D2801201DB
     */
    protected WEB3IPOBookBuildingEnterResponse getProductList(
        WEB3IPOBookBuildingEnterRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductList(WEB3IpoBookbuildingEnterRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3IPOBookBuildingEnterResponse l_response = null;

        //1.1 validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
        
        //1.2 get�⏕����
        SubAccount l_subAccount = this.getSubAccount();//WEB3BaseException
        
        //1.3 getInstitution
        Institution l_institution = l_subAccount.getInstitution();
        
        //1.4 get�戵���L������
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
        WEB3IpoProductManagerImpl l_ipoPorductManager = 
            (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
        WEB3IpoProductImpl[] l_ipoProduct = l_ipoPorductManager.getDealtInProcessOpenIpoProducts(
            l_institution.getInstitutionCode(), 
            WEB3IpoRegistDivDef.OPEN_LISTING);//WEB3BaseException

        //1.5 create��������
        WEB3IPOPublicOfferingProductUnit[] l_ipoPublicOfferingProductUnit1 = null;
        if(l_ipoProduct != null)
        {
            l_ipoPublicOfferingProductUnit1 = this.createProductUnits(l_ipoProduct);   
        }
                 
        
        //1.6 get�戵���L������
        l_ipoProduct = l_ipoPorductManager.getDealtInProcessOpenIpoProducts(
            l_institution.getInstitutionCode(), 
            WEB3IpoRegistDivDef.LISTED);//WEB3BaseException
        
        //1.7 create��������
        WEB3IPOPublicOfferingProductUnit[] l_ipoPublicOfferingProductUnit2 = null;
        if(l_ipoProduct != null)
        {
            l_ipoPublicOfferingProductUnit2 = this.createProductUnits(l_ipoProduct);
        }
        
        //1.7.1 ���b�Z�[�W IPO���J��������
        
        //1.8 IPO�u�b�N�r���f�B���O�Q�����X�|���X
        l_response = (WEB3IPOBookBuildingEnterResponse)l_request.createResponse();
        
        //1.9 �v���p�e�B�Z�b�g
        l_response.newListingList = l_ipoPublicOfferingProductUnit1;
        l_response.listingList = l_ipoPublicOfferingProductUnit2;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����ڍ�)<BR>
     * IPO�ʖ������擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸގQ���jget�����ڍׁv�Q�ƁB
     * @@param l_request - IPO�ʖ�����񃊃N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoProductInfoResponse
     * @@roseuid 40D280AA02C6
     */
    protected WEB3IPOProductInfoResponse getDetailedProduct(WEB3IPOProductInfoRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDetailedProduct(WEB3IpoProductInfoRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOProductInfoResponse l_response = null;
        try
        {
            //1.1 validate������t�\
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //1.2 createIPO�������
            WEB3IpoProductInfoService l_ipoProductInfoService = 
                (WEB3IpoProductInfoService) Services.getService(WEB3IpoProductInfoService.class);

            long l_lngProductID = Long.parseLong(l_request.id);
            WEB3IPOProductInfo l_ipoProductInfo = 
                l_ipoProductInfoService.createIpoProductInfo(l_lngProductID);

            //1.3 IPO����
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoProductManagerImpl l_productManager = 
                (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
            WEB3IpoProductImpl l_ipoProduct = 
                (WEB3IpoProductImpl)l_productManager.getProduct(l_lngProductID);

            //1.4 is�u�b�N�r���f�B���O�\���\
            boolean l_blnBookBindingOrderPossible = l_ipoProduct.isBookbuildingOrderPossible();
            
            //1.5 get�⏕����
            SubAccount l_subAccount = this.getSubAccount();
            
            //1.6 getIPO�\��
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
            WEB3IpoOrderImpl l_ipoOrder = 
                (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_subAccount, l_lngProductID);                     
            
            //1.7 IPO�ʖ�����񃌃X�|���X
            l_response = (WEB3IPOProductInfoResponse)l_request.createResponse();
            
            //1.8 �v���p�e�B�Z�b�g
            boolean l_blnUnDemand = false;
            //�\�����ς̔���
            if (l_ipoOrder == null) 
            {
                l_blnUnDemand = true;
            } else 
            {
                if (OrderStatusEnum.CANCELLED.equals(l_ipoOrder.getOrderStatus())) 
                {
                    l_blnUnDemand = true;
                }
            }
            
            l_response.demandFlag = l_blnBookBindingOrderPossible && l_blnUnDemand;
            l_response.ipoProductInfo = l_ipoProductInfo;

        }
        catch (NotFoundException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_e);
        }
   
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create��������)<BR>
     * ������IPO����[]�̓��e�ŁAIPO���J�������׃��b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�ޯ�����ިݸގQ���jcreate�������ׁv<BR>
     * �Q�ƁB
     * @@param l_products - (IPO�������X�g)<BR>
     * IPO�����I�u�W�F�N�g�̔z��
     * @@return webbroker3.ipo.message.WEB3IpoPublicOfferingProductUnit[]
     * @@roseuid 40D2A2F4014F
     */
    protected WEB3IPOPublicOfferingProductUnit[] createProductUnits(WEB3IpoProductImpl[] l_products) 
    {
        final String STR_METHOD_NAME = " createProductUnits(WEB3IpoProductImpl[])";
        log.entering(STR_METHOD_NAME);
        
        if(l_products == null)
        {
            log.debug("create��������,parameter WEB3IpoProductImpl[] l_products == null.");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        int l_productCount = l_products.length ;
        //1.1 ArrayList
        ArrayList l_arrList = new ArrayList(l_productCount);
        
        //1.2 IPO���� �e�v�f����LOOP����
        IpoProductRow l_ipoProductRow = null;
        WEB3IpoProductImpl l_ipoProductImpl = null;
        for(int i = 0; i < l_productCount; i++)
        {
            l_ipoProductImpl = l_products[i];
            l_ipoProductRow = 
                (IpoProductRow)l_ipoProductImpl.getDataSourceObject();
            
            //1.2.1 is�X�P�W���[������
            boolean l_scheduleDecision = l_ipoProductImpl.isScheduleDecision();
            
            //1.2.2 getIPO�X�P�W���[��
            String l_ipoSchedule = l_ipoProductImpl.getIpoSchedule();            
            
            //1.2.3 IPO���Ԏw��
            WEB3IPOTermUnit l_ipoTermUnit = new WEB3IPOTermUnit();
            
            //1.2.4 ���J���C���X�^���X �v���p�e�B�Z�b�g
            l_ipoTermUnit.startDate = l_ipoProductRow.getPublicOfferingDate(); 
            if(!l_ipoProductRow.getPublicOfferingDateCountIsNull())
            {
                l_ipoTermUnit.bizDateUpper = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicOfferingDateCount());
            }
                
            //1.2.5 IPO���J��������
            WEB3IPOPublicOfferingProductUnit l_ipoPublicOfferintProductUnit =
                new WEB3IPOPublicOfferingProductUnit();
                
            //1.2.6 �v���p�e�B�Z�b�g
            
            //IPO�����h�c
            l_ipoPublicOfferintProductUnit.id = WEB3StringTypeUtility.formatNumber(l_ipoProductImpl.getProductId());
            
            //�����R�[�h
            l_ipoPublicOfferintProductUnit.productCode = l_ipoProductRow.getProductCode();
            
            //������
            l_ipoPublicOfferintProductUnit.productName = l_ipoProductImpl.getStandardName();
            
            //���J�s��R�[�h
            l_ipoPublicOfferintProductUnit.publicOfferingMarketCode = l_ipoProductImpl.getPublicMarket() ;
            
            //�u�b�N�r���f�B���O�J�n����
            l_ipoPublicOfferintProductUnit.bookBuildingStartDate = l_ipoProductRow.getBookbuildingStartDatetime();
            
            //�u�b�N�r���f�B���O�I������
            l_ipoPublicOfferintProductUnit.bookBuildingEndDate = l_ipoProductRow.getBookbuildingEndDatetime();
            
            //�������敪
            l_ipoPublicOfferintProductUnit.temporaryConditionDiv = l_ipoProductRow.getProvisionalValueDiv();
            
            //�����������l
            if(!l_ipoProductRow.getProvisionalMinValueIsNull())
            {
                l_ipoPublicOfferintProductUnit.temporaryConditionLower = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getProvisionalMinValue());
            }
            
            //����������l
            if(!l_ipoProductRow.getProvisionalMaxValueIsNull())
            {
                l_ipoPublicOfferintProductUnit.temporaryConditionUpper = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getProvisionalMaxValue());
            }
            
            //�������񎦓�
            l_ipoPublicOfferintProductUnit.temporaryConditionPresentationDate = l_ipoProductRow.getProvisionalValueOpenDate();
            
            //���J���i
            if(!l_ipoProductRow.getPublicPriceIsNull())
            {
                l_ipoPublicOfferintProductUnit.publicOfferingPrice = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());
            }
            
            //���J���i�f�B�X�J�E���g��
            if(!l_ipoProductRow.getPublicPriceDiscountRateIsNull())
            {
                l_ipoPublicOfferintProductUnit.publicOfferingDiscountRate = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPriceDiscountRate());
            }
            
            //���J���i�����
            l_ipoPublicOfferintProductUnit.publicOfferingPriceDetermDate = l_ipoProductRow.getPublicPriceSettleDate();
            
            //���茈��敪
            if(l_scheduleDecision)
            {
                l_ipoPublicOfferintProductUnit.undecideDecideDiv = 
                    WEB3UndecideDecideDivDef.SCHEDULE_DECIDED;
            }
            else
            {
                l_ipoPublicOfferintProductUnit.undecideDecideDiv = 
                    WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED;
            }
            
            //���J��
            l_ipoPublicOfferintProductUnit.publicOfferingDate = 
                l_ipoTermUnit;
            
            //ipo�X�P�W���[��
            l_ipoPublicOfferintProductUnit.ipoScheduleDiv = l_ipoSchedule;
            
            //IPO��~
            l_ipoPublicOfferintProductUnit.ipoStopDiv = l_ipoProductRow.getIpoStop();
            
            //1.2.7 add
            l_arrList.add(l_ipoPublicOfferintProductUnit);
        }
        
        //1.3 toArray
        WEB3IPOPublicOfferingProductUnit[] l_offeringProducts =
            new WEB3IPOPublicOfferingProductUnit[l_arrList.size()];
            
        l_arrList.toArray(l_offeringProducts);
        
        log.exiting(STR_METHOD_NAME);
        return l_offeringProducts;
    }
}
@
