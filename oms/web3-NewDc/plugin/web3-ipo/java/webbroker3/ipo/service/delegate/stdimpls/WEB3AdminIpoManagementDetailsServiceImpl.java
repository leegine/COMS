head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoManagementDetailsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�XImpl(WEB3AdminIpoManagementDetailsServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ���o�� �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>043
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>065
Revesion History : 2005/12/20 杊��] (���u) �d�l�ύXNo.101�C��
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3ControlCodeListDef;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.ipo.message.WEB3AdminIPOManagementRequest;
import webbroker3.ipo.message.WEB3AdminIPOManagementResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsResponse;
import webbroker3.ipo.message.WEB3AdminIPOPublicOfferingProductUnit;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.message.WEB3IPOTermUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoManagementDetailsService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�XImpl)<BR>
 * �Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X�����N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIpoManagementDetailsServiceImpl implements WEB3AdminIpoManagementDetailsService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoManagementDetailsServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE30395
     */
    public WEB3AdminIpoManagementDetailsServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ���IPO�����Ǘ��E�ڍ׏��������{����B<BR>
     * <BR>
     * �P�j�@@�Ɩ������ݒ�<BR>
     * �@@������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO�����Ǘ����N�G�X�g�̏ꍇ<BR>
     * �@@�|get�����ꗗ()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO�����ڍ׃��N�G�X�g�̏ꍇ<BR>
     * �@@�|get�����ڍ�()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C666B003B6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        // Timestamp���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        // Timestamp�ݒ�
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_processTime);
        
        if (l_request instanceof WEB3AdminIPOManagementRequest)
        {
            log.debug("WEB3AdminIpoManagementRequest");
            WEB3AdminIPOManagementResponse l_ipoManagementResponse = getProductList(
                (WEB3AdminIPOManagementRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoManagementResponse;
        }
        else if (l_request instanceof WEB3AdminIPOProductDetailsRequest)
        {
            log.debug("WEB3AdminIpoProductDetailsRequest");
            WEB3AdminIPOProductDetailsResponse l_ipoProductDetailsResponse = getProductDetails(
                (WEB3AdminIPOProductDetailsRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductDetailsResponse;
        }
        else
        {
            // Timestamp���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get�����ꗗ)<BR>
     * IPO�����ꗗ�擾�������s���B<BR>
     * �i�Ǘ���IPO�����Ǘ���ʂɕ\���j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�����Ǘ��jget�����ꗗ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����Ǘ����N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOManagementResponse
     * @@roseuid 40C6677200B8
     */
    protected WEB3AdminIPOManagementResponse getProductList(WEB3AdminIPOManagementRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductList(WEB3AdminIPOManagementRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //2.validate����
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //3. get�،���ЃR�[�h
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //4.get�L������
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        WEB3IpoProductImpl[] l_ipoProductImpl = null; 
        l_ipoProductImpl = l_ipoProductManagerImpl.getOpenIpoProducts(l_strInstitutionCode,WEB3IpoRegistDivDef.OPEN_LISTING);
        
        //5.create��������
        WEB3AdminIPOPublicOfferingProductUnit[] l_ipoPublicOfferingProductUnit = null;
        if(l_ipoProductImpl != null)
        {
            l_ipoPublicOfferingProductUnit = this.createProductUnits(l_ipoProductImpl);
        
        }
        
        //6.get�L������
        WEB3IpoProductImpl[] l_ipoProductImpl2 = null; 
        l_ipoProductImpl2 = l_ipoProductManagerImpl.getOpenIpoProducts(l_strInstitutionCode,WEB3IpoRegistDivDef.LISTED);
        
        //7.create��������
        WEB3AdminIPOPublicOfferingProductUnit[] l_ipoPublicOfferingProductUnit2 = null;
        if(l_ipoProductImpl2 != null)
        {
            l_ipoPublicOfferingProductUnit2 = this.createProductUnits(l_ipoProductImpl2);
         
        }
        
        //8.�Ǘ���IPO�����Ǘ����X�|���X
        WEB3AdminIPOManagementResponse l_ipoManagementResponse = null;
        l_ipoManagementResponse = (WEB3AdminIPOManagementResponse)l_request.createResponse();
        
        //9.�v���p�e�B�Z�b�g
        l_ipoManagementResponse.newListingList = l_ipoPublicOfferingProductUnit;
        l_ipoManagementResponse.listingList = l_ipoPublicOfferingProductUnit2;
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoManagementResponse;
    }
    
    /**
     * (get�����ڍ�)<BR>
     * IPO�����ڍ׎擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�����ڍׁjget�����ڍׁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductDetailsResponse
     * @@roseuid 40C6677C01F0
     */
    protected WEB3AdminIPOProductDetailsResponse getProductDetails(WEB3AdminIPOProductDetailsRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductDetails(WEB3AdminIPOProductDetailsRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //2.validate����
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        long l_ipoProductId = Long.parseLong(l_request.id);
            
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        try
        {
            //3.IPO����
            WEB3IpoProductManagerImpl l_ipoProductManager 
                = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct 
                = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //4.is�폜����()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is�폜����() = " + l_blnDeletionProduct);        
        
            //5.(is�폜����() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDeletionProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            
            }       
        }
        catch(NotFoundException nf_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        
        //6.ArrayList
        List l_lisArrayList = new ArrayList();
        
        //7.add(����R�[�h.�h�����h : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.CHANGE);
        
        //8.add(����R�[�h.�h�폜�h : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.CANCEL);
        
        //9.add(����R�[�h.�h�u�b�N�r���f�B���O�󋵃_�E�����[�h�h : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.BOOK_BUILDING_SITUATION_DOWNLOAD);
        
        //10.add(����R�[�h.�h���I���ʃA�b�v���[�h�h : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.LOT_RESULT_UPLOAD);
        
        //11.add(����R�[�h.�h���I���ʁ^�w���\���󋵃_�E�����[�h�h : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.LOT_RESULT_OFFER_STATE_DOWNLOAD);
        
        //12.add(����R�[�h.�hIPO��W��~�^�ĊJ�h : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_RECRUIT_STOP_RESUMPTION);
        
        //13.add(����R�[�h.�hIPO���~�h : Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_DISCONTINUATION);
        
        //14.add(int, Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_LOT_INPUT);

        //15.add(int, Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_LOT_RESULT_CONFIRM);

        //16.add(int, Object)
        l_lisArrayList.add(WEB3ControlCodeListDef.IPO_LOT_RESULT_COMPLETE);
        
        //17.toArray( )
        String[] l_strControlCodes = null;
        l_strControlCodes = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strControlCodes);
        
        //18. createIPO�������(long)
        WEB3IpoProductInfoService l_service =
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
        WEB3IPOProductInfo l_ipoProductInfo = l_service.createIpoProductInfo(Long.parseLong(l_request.id));        
        
        //19.�Ǘ���IPO�����ڍ׃��X�|���X
        WEB3AdminIPOProductDetailsResponse l_ipoProductDetailsResponse = (WEB3AdminIPOProductDetailsResponse)l_request.createResponse();
               
        //20. �v���p�e�B�Z�b�g
        l_ipoProductDetailsResponse.controlCodeList = l_strControlCodes;
        l_ipoProductDetailsResponse.ipoProductInfo = l_ipoProductInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductDetailsResponse;
    }
    
    /**
     * (create��������)<BR>
     * ������IPO����[]�̓��e�ŁA�Ǘ���IPO���J�������׃��b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�����Ǘ��jcreate�������ׁv<BR>
     * �Q�ƁB<BR>
     * @@param l_ipoProducts - (IPO�������X�g)<BR>
     * IPO�����I�u�W�F�N�g�̔z��
     * @@return WEB3AdminIpoPublicOfferingProductUnit[]
     * @@roseuid 40C68F290015
     */
    protected WEB3AdminIPOPublicOfferingProductUnit[] createProductUnits(WEB3IpoProductImpl[] l_ipoProducts) 
    {
        final String STR_METHOD_NAME = " createProductUnits(WEB3IpoProductImpl[])";
        log.entering(STR_METHOD_NAME);
        
        //1.ArrayList
        List l_arrayList = new ArrayList();
        
        //2.IPO���� �e�v�f����LOOP����
        int l_intLength = l_ipoProducts.length;
        log.debug("l_intLength = " + l_intLength);
        for (int i = 0; i < l_intLength; i++)
        {
            IpoProductRow l_ipoProductRow =(IpoProductRow)l_ipoProducts[i].getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);
            
            //2.1is�X�P�W���[������
            boolean l_blnScheduleDecision = l_ipoProducts[i].isScheduleDecision();
        
            //2.2getIPO�X�P�W���[��
            String l_strIpoSchedule = l_ipoProducts[i].getIpoSchedule();
        
            //2.3IPO���Ԏw��
            WEB3IPOTermUnit l_ipoTermUnit = new WEB3IPOTermUnit(); 
            
            //2.4���I���C���X�^���X �v���p�e�B�Z�b�g
            
            l_ipoTermUnit.startDate = l_ipoProductParams.getLotDate();
            if (l_ipoProductParams.getLotDateCountIsNull())
            {
                l_ipoTermUnit.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnit.bizDateUpper = Integer.toString(l_ipoProductParams.getLotDateCount());
            }           
            
            //2.5 IPO���Ԏw��
            WEB3IPOTermUnit l_ipoTermUnitOfferDate = new WEB3IPOTermUnit();
            
            //2.6�w���\�����ԁi���Ўw��j�C���X�^���X �v���p�e�B�Z�b�g
            l_ipoTermUnitOfferDate.startDate = l_ipoProductParams.getOfferStartDatetime();
            l_ipoTermUnitOfferDate.endDate = l_ipoProductParams.getOfferEndDatetime();
            // 2004/11/26 ��Q�Ǘ��[No.U00480 �c�Ɠ�����l�Ɖc�Ɠ������l�ɋt�̒l���Z�b�g����� ����@@SRA START
//            if (l_ipoProductParams.getOfferStartDateCountIsNull())
//            {
//                l_ipoTermUnitOfferDate.bizDateUpper = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferDate.bizDateUpper = Integer.toString(l_ipoProductParams.getOfferStartDateCount());
//            }
//            if (l_ipoProductParams.getOfferEndDateCountIsNull())
//            {
//                l_ipoTermUnitOfferDate.bizDateLower = null;
//            }
//            else
//            {
//                l_ipoTermUnitOfferDate.bizDateLower = Integer.toString(l_ipoProductParams.getOfferEndDateCount());
//            }          
            if (l_ipoProductParams.getOfferStartDateCountIsNull())
            {
                l_ipoTermUnitOfferDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitOfferDate.bizDateUpper = Integer.toString(l_ipoProductParams.getOfferStartDateCount());
            }
            if (l_ipoProductParams.getOfferEndDateCountIsNull())
            {
                l_ipoTermUnitOfferDate.bizDateLower = null;
            }
            else
            {
                l_ipoTermUnitOfferDate.bizDateLower = Integer.toString(l_ipoProductParams.getOfferEndDateCount());
            }          
            // 2004/11/26 ��Q�Ǘ��[No.U00480 �c�Ɠ�����l�Ɖc�Ɠ������l�ɋt�̒l���Z�b�g����� ����@@SRA END
            
            //2.7 IPO���Ԏw��
            WEB3IPOTermUnit l_ipoTermUnitPublicDate = new WEB3IPOTermUnit();
            
            //2.8���J���C���X�^���X �v���p�e�B�Z�b�g
            l_ipoTermUnitPublicDate.startDate = l_ipoProductParams.getPublicOfferingDate();
            if (l_ipoProductParams.getPublicOfferingDateCountIsNull())
            {
                l_ipoTermUnitPublicDate.bizDateUpper = null;
            }
            else
            {
                l_ipoTermUnitPublicDate.bizDateUpper = Integer.toString(l_ipoProductParams.getPublicOfferingDateCount());
            }
                      
            //2.9 �Ǘ���IPO���J��������
            WEB3AdminIPOPublicOfferingProductUnit l_ipoPublicOfferingProductUnit = new WEB3AdminIPOPublicOfferingProductUnit();
            
            //2.10�v���p�e�B�Z�b�g
            //�h�c�F�@@IPO����.IPO�����h�c
            l_ipoPublicOfferingProductUnit.id = Long.toString(l_ipoProductParams.getIpoProductId());
            
            //�����R�[�h�F�@@IPO����.�����R�[�h
            l_ipoPublicOfferingProductUnit.productCode = l_ipoProductParams.getProductCode();   
                     
            //�������F�@@IPO����.������
            l_ipoPublicOfferingProductUnit.productName = l_ipoProductParams.getStandardName();
            
            //���J�s��R�[�h�F�@@IPO����.���J�s��
            l_ipoPublicOfferingProductUnit.publicOfferingMarketCode = l_ipoProductParams.getPublicMarket();
            
            //�u�b�N�r���f�B���O�J�n�����F�@@IPO����.�u�b�N�r���f�B���O�J�n����
            l_ipoPublicOfferingProductUnit.bookBuildingStartDate = l_ipoProductParams.getBookbuildingStartDatetime();
            
            //�u�b�N�r���f�B���O�I�������F�@@IPO����.�u�b�N�r���f�B���O�I������
            l_ipoPublicOfferingProductUnit.bookBuildingEndDate = l_ipoProductParams.getBookbuildingEndDatetime();
            
            //���J���i�F�@@IPO����.���J���i
            if (l_ipoProductParams.getPublicPriceIsNull())
            {
                l_ipoPublicOfferingProductUnit.publicOfferingPrice = null;
            }
            else
            {
                l_ipoPublicOfferingProductUnit.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicPrice());
            }
            
            //���J���i�f�B�X�J�E���g���F�@@IPO����.���J���i�i�f�B�X�J�E���g���j
            if (l_ipoProductParams.getPublicPriceDiscountRateIsNull())
            {
                l_ipoPublicOfferingProductUnit.publicOfferingDiscountRate = null;
            }
            else
            {
                l_ipoPublicOfferingProductUnit.publicOfferingDiscountRate = WEB3StringTypeUtility.formatNumber(l_ipoProductParams.getPublicPriceDiscountRate());
            }            
            
            //���J���i������F�@@IPO����.���J���i�����
            l_ipoPublicOfferingProductUnit.publicOfferingPriceDetermDate = l_ipoProductParams.getPublicPriceSettleDate();
            
            //���茈��敪�F�@@
            //�iIPO����.is�X�P�W���[������() == true�j�̏ꍇ�A�h����h
            //�iIPO����.is�X�P�W���[������() == false�j�̏ꍇ�A�h����h
            if (l_blnScheduleDecision)
            {
                log.debug("is�X�P�W���[������() == true");
                l_ipoPublicOfferingProductUnit.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_DECIDED;
            }
            else if (!l_blnScheduleDecision)
            {
                log.debug("is�X�P�W���[������() == false");
                l_ipoPublicOfferingProductUnit.undecideDecideDiv = WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED;
            }
            
            //���I���F�@@���I���i�FIPO���Ԏw��j
            l_ipoPublicOfferingProductUnit.lotDate = l_ipoTermUnit;
            
            //�w���\�����ԁi���Ўw��j�F�@@�w���\�����ԁi���Ўw��j�i�FIPO���Ԏw��j
            l_ipoPublicOfferingProductUnit.appointOfferTerm = l_ipoTermUnitOfferDate;
            
            //���J���F�@@���J���i�FIPO���Ԏw��j
            l_ipoPublicOfferingProductUnit.publicOfferingDate = l_ipoTermUnitPublicDate;
            
            //IPO�X�P�W���[���F�@@IPO����.getIPO�X�P�W���[��()�̖߂�l
            l_ipoPublicOfferingProductUnit.ipoScheduleDiv = l_strIpoSchedule;
            
            //IPO��~�F�@@IPO����.IPO��~
            l_ipoPublicOfferingProductUnit.ipoStopDiv = l_ipoProductParams.getIpoStop();
            
            //2.11add(�Ǘ���IPO���J�������� : Object)
            l_arrayList.add(l_ipoPublicOfferingProductUnit);           
        }
        //3.toArray
        WEB3AdminIPOPublicOfferingProductUnit[] l_WEB3AdminIpoPublicOfferingProductUnit;
        l_WEB3AdminIpoPublicOfferingProductUnit = new WEB3AdminIPOPublicOfferingProductUnit[l_arrayList.size()];
        l_arrayList.toArray(l_WEB3AdminIpoPublicOfferingProductUnit);
        
        log.exiting(STR_METHOD_NAME);
        return l_WEB3AdminIpoPublicOfferingProductUnit;
    }
}
@
