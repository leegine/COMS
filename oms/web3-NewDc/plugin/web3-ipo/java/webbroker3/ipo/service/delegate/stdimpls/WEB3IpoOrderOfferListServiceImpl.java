head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderOfferListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\���E�w���\���ꗗ�T�[�r�X�����N���X(WEB3IpoOrderOfferListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���]��(���u) �V�K�쐬
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>048
*/
package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnableIpoQuantityChangeDef;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3ControlPossibleCodeListDef;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3IPODemandHistoryUnit;
import webbroker3.ipo.message.WEB3IPODemandOfferProductUnit;
import webbroker3.ipo.message.WEB3IPODemandOfferRequest;
import webbroker3.ipo.message.WEB3IPODemandOfferResponse;
import webbroker3.ipo.message.WEB3IPOTermUnit;
import webbroker3.ipo.service.delegate.WEB3IpoOrderActionUnitService;
import webbroker3.ipo.service.delegate.WEB3IpoOrderOfferListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

/**
 * IPO�\���E�w���\���ꗗ�T�[�r�X�����N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IpoOrderOfferListServiceImpl extends WEB3IpoClientRequestService implements WEB3IpoOrderOfferListService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoOrderOfferListServiceImpl.class); 
    /**
     * @@roseuid 4112F1920126
     */
    public WEB3IpoOrderOfferListServiceImpl() 
    {
     
    }
    
    /**
     * IPO�\���E�w���\���ꗗ���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�\���w���\�����N�G�X�g�̏ꍇ<BR>
     * �@@�|get�\���w���\���ꗗ()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���AIPO�\���������N�G�X�g�̏ꍇ<BR>
     * �@@�|get�\�������ꗗ()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40DA5E500311
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPODemandOfferRequest)
        {
            l_response = (WEB3GenResponse)this.getOrderOfferList((WEB3IPODemandOfferRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingHistoryRequest)
        {           
            l_response = (WEB3GenResponse)this.getOrderActionList((WEB3IPOBookBuildingHistoryRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������WEB3IPOBookBuildingEnterRequest," + 
                "WEB3IPOProductInfoRequest�ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get�\���w���\���ꗗ)<BR>
     * IPO�\���E�w���\���ꗗ�ꗗ�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�\���E�w���\���ꗗ�jget�\���w���\���ꗗ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO�\���w���\�����N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoOrderOfferResponse
     * @@roseuid 40DA5E500340
     */
    protected WEB3IPODemandOfferResponse getOrderOfferList(WEB3IPODemandOfferRequest l_request)
        throws WEB3BaseException 
    {

       
        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //get�⏕����()
        SubAccount l_subAccount = this.getSubAccount();
        
        //(�V�K���) getIPO�\��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
                             (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
                             
        WEB3IpoOrderImpl[] l_newOrders = l_orderManagerImpl.getOrderUnits(l_subAccount,WEB3IpoRegistDivDef.OPEN_LISTING);
        WEB3IPODemandOfferProductUnit[] l_newOrderOfferProductUnits = this.createOrderOfferUnits(l_newOrders);
        
        //�����ꗗ
        WEB3IpoOrderImpl[] l_oldOrders = l_orderManagerImpl.getOrderUnits(l_subAccount,WEB3IpoRegistDivDef.LISTED);
        WEB3IPODemandOfferProductUnit[] l_oldOrderOfferProductUnits = this.createOrderOfferUnits(l_oldOrders);
        //IPO�w���\�����̓��X�|���X(WEB3GenRequest)              
        WEB3IPODemandOfferResponse l_orderOfferResponse = (WEB3IPODemandOfferResponse) l_request.createResponse();
        
        l_orderOfferResponse.newListingList = l_newOrderOfferProductUnits;
        l_orderOfferResponse.listingList =l_oldOrderOfferProductUnits;
               
        return l_orderOfferResponse;
    }
    
    /**
     * (get�\�������ꗗ)<BR>
     * IPO�\�������ꗗ�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�\���E�w���\���ꗗ�jget�\�������ꗗ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO�u�b�N�r���f�B���O�\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderActionResponse
     * @@roseuid 40DA5E50036F
     */
    protected WEB3IPOBookBuildingHistoryResponse getOrderActionList(WEB3IPOBookBuildingHistoryRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderHistoryList(WEB3IpoBookbuildingOrderActionRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //IPO�\��::IPO�\��
        long l_lngOrderId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
 
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngOrderId);
                    
            //IPO�\��::getIPO����
            WEB3IpoProductImpl l_product;
            l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();
        
            //IPO�\��::get�⏕����ID
            long l_lngSubAccountId = l_ipoOrder.getSubAccountId();
        
            //IPO�\�����𖾍׍쐬�T�[�r�XImpl::createIPO�\�����𖾍�
            
            WEB3IpoOrderActionUnitService l_service = (WEB3IpoOrderActionUnitService)Services.getService(WEB3IpoOrderActionUnitService.class);
                    
            WEB3IPODemandHistoryUnit[] l_orderActionUnits = l_service.createOrderActionUnit(l_ipoOrder);        
            //IPO�u�b�N�r���f�B���O�\���������X�|���X::IPO�u�b�N�r���f�B���O�\���������X�|���X
            WEB3IPOBookBuildingHistoryResponse l_bookbuildingHistoryResponse = (WEB3IPOBookBuildingHistoryResponse) l_request.createResponse();
        
            //���b�Z�[�W (*2) �v���p�e�B�Z�b�g
            l_bookbuildingHistoryResponse.productCode = ((IpoProductRow)l_product.getDataSourceObject()).getProductCode();
            l_bookbuildingHistoryResponse.productName = l_product.getStandardName();
            l_bookbuildingHistoryResponse.bookBuildingHistoryList =l_orderActionUnits ; 
            
            log.exiting(STR_METHOD_NAME);  
            return l_bookbuildingHistoryResponse;
                    
        } 
        catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        
    }
    
    /**
     * (create�\���w���\������)<BR>
     * ������IPO�\��[]�̓��e�ŁAIPO�\���w���\���������׃��b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�\���E�w���\���ꗗ�jcreate�\���w���\�����ׁv<BR>
     * �Q�ƁB<BR>
     * @@param l_ipoOrders - IPO�\���I�u�W�F�N�g�̔z��
     * @@return webbroker3.ipo.message.WEB3IpoOrderOfferProductUnit[]
     * @@roseuid 40DC00C10379
     */
    protected WEB3IPODemandOfferProductUnit[] createOrderOfferUnits(WEB3IpoOrderImpl[] l_ipoOrders) throws WEB3BaseException 
    {
        
        //���b�Z�[�W ArrayList( )
        ArrayList l_orderOfferProductList = new ArrayList();
        
        //���b�Z�[�W (*1) IPO�\���@@�e�v�f����LOOP
        
        int l_intLength = l_ipoOrders.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //IPO�\��::getIPO����
            WEB3IpoProductImpl l_product;
            l_product = (WEB3IpoProductImpl) l_ipoOrders[i].getProduct();
        
            //���b�Z�[�W (*2) �w���\�����ԁi���Ўw��j�C���X�^���X �v���p�e�B�Z�b�g
            WEB3IPOTermUnit l_termUnit = new WEB3IPOTermUnit();
            
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();
            
            l_termUnit.startDate =l_ipoProductRow.getOfferStartDatetime();
            l_termUnit.endDate = l_ipoProductRow.getOfferEndDatetime();

            if(!l_ipoProductRow.getOfferStartDateCountIsNull())
            {
                l_termUnit.bizDateUpper = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getOfferStartDateCount());
            } 
            
            if(!l_ipoProductRow.getOfferEndDateCountIsNull())
            {
                l_termUnit.bizDateLower = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getOfferEndDateCount());
            } 
        
            //IPO����::getIPO�X�P�W���[��
            String l_getIpoSchedule = l_product.getIpoSchedule();
        
            //IPO�\��::get�\���\����
            String l_strOrderOfferState = l_ipoOrders[i].getOrderOfferState();
        
            //���b�Z�[�W ArrayList( )
            ArrayList l_arrayList = new ArrayList();
            
            //IPO����::is�u�b�N�r���f�B���O�J�n
            boolean l_blnStart = l_product.isBookbuildingStart();
        
            //IPO����::is�u�b�N�r���f�B���O�I��
            boolean l_blnEnd = l_product.isBookbuildingEnd();
            
            IpoOrderRow l_IpoOrderRow = (IpoOrderRow)l_ipoOrders[i].getDataSourceObject();
            
            if(l_blnStart && !l_blnEnd)
            {
                if( OrderStatusEnum.CANCELLED.equals(l_IpoOrderRow.getOrderStatus()) )
                {
                    l_arrayList.add(WEB3ControlPossibleCodeListDef.DAMAND);
                }
                else
                {
                    l_arrayList.add(WEB3ControlPossibleCodeListDef.CHANGE_CANCEL);
                }
            }
            
            //IPO����::is�V�K���I�I��
            boolean l_blnLotteryEnd = l_product.isNewLotteryEnd();            
            
            //IPO�\��::is�w���\��
            boolean l_blnOffered = l_ipoOrders[i].isOffered();
            
            //IPO�\��::is����
            boolean l_blnDecline = l_ipoOrders[i].isDecline();
            
            //IPO�\��::is�w���\���E���މ\
            boolean l_blnOfferDeclinePossible = l_ipoOrders[i].isOfferDeclinePossible();
            
            if (!l_blnOffered && !l_blnDecline && l_blnOfferDeclinePossible)
            {
                if(l_blnLotteryEnd && !l_product.isOfferEnd())
                {
                    l_arrayList.add(WEB3ControlPossibleCodeListDef.DECLINE);
                }
            
                //(*5) �V�K���I�I����ŁA�w���\�����Ԓ��̏ꍇ
                if(l_product.isNewLotteryEnd() && l_product.isOfferStart() && !l_product.isOfferEnd()) 
                {
                    l_arrayList.add(WEB3ControlPossibleCodeListDef.PURCHASE_APPLICATION);
                }
            }

            //is�戵��( )
            boolean l_blnDealtInProcess = l_product.isDealtInProcess();
            
            //(*) IPO�����������̏ꍇ�iis�戵�� == false�j 
            if (!l_blnDealtInProcess)
            {
                l_arrayList.clear();                     
            }
            
            //toArray( )
            Object[] l_controlPossibleCodeList = l_arrayList.toArray();
            
            //IPO�\���w���\����������::IPO�\���w���\����������
            WEB3IPODemandOfferProductUnit l_orderOfferProductUnit = new WEB3IPODemandOfferProductUnit();
           
            //�h�c
            l_orderOfferProductUnit.id =Long.toString(l_ipoOrders[i].getOrderId());
            
            //IPO����ID
            l_orderOfferProductUnit.ipoProductId = Long.toString(l_ipoProductRow.getIpoProductId());
            
            //�����R�[�h
            l_orderOfferProductUnit.productCode = l_ipoProductRow.getProductCode();
            
            //������
            l_orderOfferProductUnit.productName = l_product.getStandardName();
            
            //�\������
            double l_dblQuantity = l_ipoOrders[i].getQuantity();
            l_orderOfferProductUnit.demandQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            
            //�\�����i�敪            
            if (l_ipoOrders[i].getLimitPrice() == 0)
            {
                l_orderOfferProductUnit.demandPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_orderOfferProductUnit.demandPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            
            //�\�����i
            double l_dblLimitPriceQuantity = l_ipoOrders[i].getLimitPrice();
            l_orderOfferProductUnit.demandPrice = WEB3StringTypeUtility.formatNumber(l_dblLimitPriceQuantity);
            
            //���J���i
            if(l_ipoProductRow.getPublicPriceIsNull())
            {
                l_orderOfferProductUnit.publicOfferingPrice = null;
            }
            else
            {
                l_orderOfferProductUnit.publicOfferingPrice = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());
            }
            
            //���J���i�f�B�X�J�E���g��
            if(l_ipoProductRow.getPublicPriceDiscountRateIsNull())
            {
                l_orderOfferProductUnit.publicOfferingDiscountRate = null;
            }
            else
            {
                l_orderOfferProductUnit.publicOfferingDiscountRate = 
                    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPriceDiscountRate());
            }
            
            //���J���i�����
            l_orderOfferProductUnit.publicOfferingPriceDetermDate = new WEB3IPOTermUnit();
            l_orderOfferProductUnit.publicOfferingPriceDetermDate.startDate = l_ipoProductRow.getPublicPriceSettleDate();
            
            //���I����
            if(l_IpoOrderRow.getElectedQuantityIsNull())
            {
                l_orderOfferProductUnit.prizeQuantity  = null;
            }
            else
            {
                l_orderOfferProductUnit.prizeQuantity  = WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getElectedQuantity()); 
            }
            
            //�w���\������
            if(l_IpoOrderRow.getApplicationQuantityIsNull())
            {
                l_orderOfferProductUnit.offerQuantity = null;
            }
            else
            {
                l_orderOfferProductUnit.offerQuantity =
                    WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getApplicationQuantity());
            }
            
            //�\�����ޓ���
            l_orderOfferProductUnit.offerCancelDate = l_IpoOrderRow.getOfferingTimestamp();
            
            //�\�������z 
            if(l_IpoOrderRow.getBookbuildingPriceIsNull())
            {
                l_orderOfferProductUnit.demandEquivalentPrice = null;
            }
            else
            {
                l_orderOfferProductUnit.demandEquivalentPrice = 
                    WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getBookbuildingPrice());
            }
            
            //�w���\�����
            if(l_IpoOrderRow.getPayAmountIsNull())
            {
                l_orderOfferProductUnit.offerPrice = null;
            }
            else
            {
                l_orderOfferProductUnit.offerPrice =
                    WEB3StringTypeUtility.formatNumber(l_IpoOrderRow.getPayAmount());
            }
            
            //�u�b�N�r���f�B���O�J�n����
            l_orderOfferProductUnit.bookBuildingStartDate = l_ipoProductRow.getBookbuildingStartDatetime();
            
            //�u�b�N�r���f�B���O�I������
            l_orderOfferProductUnit.bookBuildingEndDate = l_ipoProductRow.getBookbuildingEndDatetime();
            
            //���茈��敪
            if(l_product.isScheduleDecision())
            {
                l_orderOfferProductUnit.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_DECIDED;
            }
            else if( !(l_product.isScheduleDecision()) )
            {
                l_orderOfferProductUnit.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED;
            }

            //�w���\�����ԁi���Ўw��j
            l_orderOfferProductUnit.appointOfferTerm = l_termUnit;
            
            //IPO�X�P�W���[��
            l_orderOfferProductUnit.ipoScheduleDiv = l_getIpoSchedule;
            
            //�\���\���󋵋敪
            l_orderOfferProductUnit.demandOfferStateDiv = l_strOrderOfferState;
            
            //�����敪
            //TaxTypeEnum
            if( TaxTypeEnum.NORMAL.equals(l_ipoOrders[i].getTaxType()))
            {
                l_orderOfferProductUnit.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if( TaxTypeEnum.SPECIAL.equals(l_ipoOrders[i].getTaxType()) 
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_ipoOrders[i].getTaxType()))
            {
                l_orderOfferProductUnit.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            
            //����\�R�[�h�ꗗ
            int l_intSize = l_controlPossibleCodeList.length;
            l_orderOfferProductUnit.controlPossibleCodeList = new String[l_intSize];
            for(int j = 0; j < l_intSize; j++)
            {
                l_orderOfferProductUnit.controlPossibleCodeList[j] = (String)l_controlPossibleCodeList[j];
            }
            
            //�\���p�P�ʋ敪
            l_orderOfferProductUnit.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();  
            
            //1.12.18.get�،����()
            Institution l_instInstitution = l_product.getInstitution(); 
            InstitutionRow l_InstitutionRow = (InstitutionRow)l_instInstitution.getDataSourceObject(); 
            //�w���\�����ʕύX�\�t���O
            if(l_InstitutionRow.getEnableIpoQuantityChange().equals(WEB3EnableIpoQuantityChangeDef.CAN_CHANGE))
            {
                l_orderOfferProductUnit.offerQuantityFlag = true ; 
            }
            else
            {
                l_orderOfferProductUnit.offerQuantityFlag = false ; 
            }
                           
            //�������敪
            l_orderOfferProductUnit.temporaryConditionDiv = l_ipoProductRow.getProvisionalValueDiv();
            
            //IPO��~
            l_orderOfferProductUnit.ipoStopDiv = l_ipoProductRow.getIpoStop();
            
            //add(IPO�\���w���\���������� : Object)
            l_orderOfferProductList.add(l_orderOfferProductUnit);            
        }
        
        WEB3IPODemandOfferProductUnit[] l_orderOfferProductUnit = new WEB3IPODemandOfferProductUnit[l_orderOfferProductList.size()];
        l_orderOfferProductList.toArray(l_orderOfferProductUnit);
        
        return l_orderOfferProductUnit;
    }
}
@
