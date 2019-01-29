head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductRegistrationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �Ǘ���IPO�����o�^�T�[�r�XImpl(WEB3AdminIpoProductRegistrationServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/08/16 ���o�� �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>043
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>060
Revesion History : 2010/09/21 ��V�� (���u) �����̖�� No.017
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PublicMarketDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductRegistrationService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

/**
 * (�Ǘ���IPO�����o�^�T�[�r�XImpl)<BR>
 * �Ǘ���IPO�����o�^�T�[�r�X�����N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIpoProductRegistrationServiceImpl implements WEB3AdminIpoProductRegistrationService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoProductRegistrationServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE401E7
     */
    public WEB3AdminIpoProductRegistrationServiceImpl() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �Ǘ���IPO�����V�K�o�^���͉�ʕ\���f�[�^�쐬�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�����o�^�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����V�K�o�^���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoProductRegistrationInputResponse
     * @@roseuid 40C3D1A6018E
     */
    protected WEB3AdminIPOProductRegistrationInputResponse getInputScreen(WEB3AdminIPOProductRegistrationInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminIpoProductRegistrationInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductRegistrationInputResponse l_ipoProductRegistrationInputResponse;
        //1.getInstanceFrom���O�C�����
        log.debug("getInstanceFrom���O�C����� before");
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C�����");
            
        //2.validate����
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_REG,true);
        log.debug("validate���� end");
            
        //3. createResponse
        l_ipoProductRegistrationInputResponse = (WEB3AdminIPOProductRegistrationInputResponse)l_request.createResponse();
            
        //4.getSystemTimestamp
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        log.debug("getSystemTimestamp end");
            
        //5.�v���p�e�B�Z�b�g
        String[] l_strPublicMarketCodesTemp;
        l_strPublicMarketCodesTemp = new String[17];
        l_strPublicMarketCodesTemp[0] = WEB3PublicMarketDef.TOKYO_STOCK_EXCHANGE;      //����
        l_strPublicMarketCodesTemp[1] = WEB3PublicMarketDef.TSE_NO_ONE_DEPT;           //���؈ꕔ
        l_strPublicMarketCodesTemp[2] = WEB3PublicMarketDef.TSE_NO_TWO_DEPT;           //���ؓ�
        l_strPublicMarketCodesTemp[3] = WEB3PublicMarketDef.MOTHERS;                   //�}�U�[�Y�@@
        l_strPublicMarketCodesTemp[4] = WEB3PublicMarketDef.OSAKA_SECURITIES_EXCHANGE; //���
        l_strPublicMarketCodesTemp[5] = WEB3PublicMarketDef.OSE_NO_ONE_DEPT;           //��؈ꕔ
        l_strPublicMarketCodesTemp[6] = WEB3PublicMarketDef.OSE_NO_TWO_DEPT;           //��ؓ�
        l_strPublicMarketCodesTemp[7] = WEB3PublicMarketDef.NAGOYA_STOCK_EXCHANGE;     //����
        l_strPublicMarketCodesTemp[8] = WEB3PublicMarketDef.NSE_NO_ONE_DEPT;           //���؈ꕔ
        l_strPublicMarketCodesTemp[9] = WEB3PublicMarketDef.NSE_NO_TWO_DEPT;           //���ؓ�
        l_strPublicMarketCodesTemp[10] = WEB3PublicMarketDef.CENTREX;                  //�Z���g���b�N�X
        l_strPublicMarketCodesTemp[11] = WEB3PublicMarketDef.FUKUOKA_STOCK_EXCHANGE;   //����
        l_strPublicMarketCodesTemp[12] = WEB3PublicMarketDef.Q_BOARD;                  //Q-Board
        l_strPublicMarketCodesTemp[13] = WEB3PublicMarketDef.SAPPORO_STOCK_EXCHANGE;   //�D��
        l_strPublicMarketCodesTemp[14] = WEB3PublicMarketDef.AMBITIOUS;                //�A���r�V���X
        l_strPublicMarketCodesTemp[15] = WEB3PublicMarketDef.JASDAQ_STANDARD;          //JASDAQ�i�X�^���_�[�h�j
        l_strPublicMarketCodesTemp[16] = WEB3PublicMarketDef.JASDAQ_CLOSE;             //JASDAQ�i�O���[�X�j
        
        l_ipoProductRegistrationInputResponse.publicOfferingMarketList = l_strPublicMarketCodesTemp;
                    
        l_ipoProductRegistrationInputResponse.currentDate = l_tsCurrentTime;
            
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductRegistrationInputResponse; 

    }
    
    /**
     * (validate�����o�^)<BR>
     * �Ǘ���IPO�����V�K�o�^�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�����o�^�jvalidate�����o�^�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����V�K�o�^�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIpoProductRegistrationConfirmResponse
     * @@roseuid 40C3CF8603DF
     */
    protected WEB3AdminIPOProductRegistrationConfirmResponse validateProductRegistration(WEB3AdminIPOProductRegistrationConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateProductRegistration(WEB3AdminIpoProductRegistrationConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.validate
        l_request.validate();
        
        //2.getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C�����");
        
        //3.validate����
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_REG,true);
        log.debug("validate����");
        
        //4.createNewIPO����(IPO�������, �Ǘ���)
        WEB3IpoProductImpl l_ipoProductImpl = this.createNewIpoProduct(l_request.ipoProductInfo,l_administrator);
        log.debug("l_ipoProductImpl.getDataSourceObject() = " + l_ipoProductImpl.getDataSourceObject());
        
        //5.validate�X�P�W���[��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        log.debug("getSystemTimestamp");
        
        l_ipoProductImpl.validateSchedule(l_tsCurrentTime);
        log.debug("validate�X�P�W���[��");
                
        //6.validate��������()
        l_ipoProductImpl.validateProduct();
        log.debug("validate��������");
                
        //7.get�،���ЃR�[�h
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        log.debug("get�،���ЃR�[�h");
        
        //8.validate���ԏd���o�^
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            
        log.debug("l_request.ipoProductInfo.productCode = " + l_request.ipoProductInfo.productCode);
        log.debug("l_request.ipoProductInfo.bookbuildingStartDatetime = " + l_request.ipoProductInfo.bookBuildingStartDate);
        log.debug("l_request.ipoProductInfo.publicOfferingDate.startDatetime = " + l_request.ipoProductInfo.publicOfferingDate.startDate);
        l_ipoProductManagerImpl.validateDuplicateTerm
            (l_strInstitutionCode, l_request.ipoProductInfo.productCode, 
            l_request.ipoProductInfo.bookBuildingStartDate, 
            l_request.ipoProductInfo.publicOfferingDate.startDate, 
            0);    
        log.debug("validate���ԏd���o�^");
        
        //9.set������
        l_ipoProductImpl.setStandardName();
        
        //10.createResponse
        WEB3AdminIPOProductRegistrationConfirmResponse l_ipoProductRegistrationConfirmResponse = (WEB3AdminIPOProductRegistrationConfirmResponse)l_request.createResponse();
        
        //11.get������
        String l_strProductName = l_ipoProductImpl.getStandardName();
        
        //12.�v���p�e�B�Z�b�g
        l_ipoProductRegistrationConfirmResponse.productName = l_strProductName;
        
        log.debug("l_strProductName = " + l_strProductName);
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductRegistrationConfirmResponse;
    }
    
    /**
     * (submit�����o�^)<BR>
     * �Ǘ���IPO�����V�K�o�^�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�����o�^�jsubmit�����o�^�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����V�K�o�^�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoProductRegistrationCompleteResponse
     * @@roseuid 40C3CF8D0324
     */
    protected WEB3AdminIPOProductRegistrationCompleteResponse submitProductRegistration(WEB3AdminIPOProductRegistrationCompleteRequest l_request)
        throws WEB3BaseException   
    {
        final String STR_METHOD_NAME = " submitProductRegistration(WEB3AdminIpoProductRegistrationCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.validate
        l_request.validate();
        
        //2.getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3.validate����
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_REG,true);
        
        //4.validate����p�X���[�h
        l_administrator.validateTradingPassword(l_request.password);
        
        //5.createNewIPO����
        WEB3IpoProductImpl l_ipoProductImpl = this.createNewIpoProduct(l_request.ipoProductInfo,l_administrator);
        log.debug("createNewIPO����");
        
        //6.validate�X�P�W���[��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
        l_ipoProductImpl.validateSchedule(l_tsCurrentTime);
        log.debug("validate�X�P�W���[��");
        
        //7.validate��������()
        l_ipoProductImpl.validateProduct();
        log.debug("validate��������");
        
        //8.get�،���ЃR�[�h
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //9.validate���ԏd���o�^
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        l_ipoProductManagerImpl.validateDuplicateTerm
            (l_strInstitutionCode,l_request.ipoProductInfo.productCode,l_request.ipoProductInfo.bookBuildingStartDate,l_request.ipoProductInfo.publicOfferingDate.startDate,0);    
        log.debug("validate���ԏd���o�^");
        
        //10.set������
        l_ipoProductImpl.setStandardName();
        
        //11.saveNew����
        log.debug("l_ipoProductImpl.getDataSourceObject" + l_ipoProductImpl.getDataSourceObject());
        l_ipoProductManagerImpl.saveNewProduct(l_ipoProductImpl);
        log.debug("saveNewProduct");
        
        //12.createResponse
        WEB3AdminIPOProductRegistrationCompleteResponse l_ipoProductRegistrationCompleteResponse = null;
        l_ipoProductRegistrationCompleteResponse = (WEB3AdminIPOProductRegistrationCompleteResponse)l_request.createResponse();
                
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductRegistrationCompleteResponse;
    }
    
    /**
     * �Ǘ���IPO�����o�^���������{����B<BR>
     * <BR>
     * �P�j�@@�Ɩ������ݒ�<BR>
     * �@@������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO�����V�K�o�^���̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get���͉��()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO�����V�K�o�^�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�����o�^()���R�[������B<BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO�����V�K�o�^�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�����o�^()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40C3CF9402B7
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
        
        if (l_request instanceof WEB3AdminIPOProductRegistrationInputRequest)
        {
            log.debug("WEB3AdminIpoProductRegistrationInputRequest");
            WEB3GenResponse l_response = getInputScreen(
                (WEB3AdminIPOProductRegistrationInputRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminIPOProductRegistrationConfirmRequest)
        {
            log.debug("WEB3AdminIpoProductRegistrationConfirmRequest");
            WEB3AdminIPOProductRegistrationConfirmResponse l_ipoProductRegistrationConfirmResponse = validateProductRegistration(
                (WEB3AdminIPOProductRegistrationConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductRegistrationConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminIPOProductRegistrationCompleteRequest)
        {
            log.debug("WEB3AdminIpoProductRegistrationCompleteRequest");
            WEB3AdminIPOProductRegistrationCompleteResponse l_ipoProductRegistrationCompleteResponse = submitProductRegistration(
                (WEB3AdminIPOProductRegistrationCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductRegistrationCompleteResponse;
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
     * (createNewIPO����)<BR>
     * IPO���������AIPO�����I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * ����������IPO�����s�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�@@IPO�����s�𐶐�����B<BR>
     * �@@IPO����Params�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@��IPO����Params�N���X��DDL��莩�������B<BR>
     * <BR>
     * �Q�j�@@IPO�����s�̊e���ڂɏ����l���Z�b�g����B<BR>
     * �@@�|���t�^�iDate�j�A������^�iString�j�̏����l �F null<BR>
     * �@@�|���l�^�iDouble,double,Long,long,Integer,int�j�̏����l �F 0<BR>
     * <BR>
     * �R�j�@@���͓��e���IPO���������쐬����B<BR>
     * �@@IPO�������쐬�T�[�r�X.createIPO����()���R�[������B<BR>
     * <BR>
     * �@@[createIPO����()�Ɏw�肷�����]<BR>
     * �@@IPO�����s�F�@@�i�P�j�A�Q�j�Ő��������I�u�W�F�N�g�j<BR>
     * �@@IPO���������̓��b�Z�[�W�F�@@����.IPO���������̓��b�Z�[�W<BR>
     * �@@�Ǘ��ҁF�@@����.�Ǘ��҃I�u�W�F�N�g<BR>
     * @@param l_ipoProductInfoInputMessage - IPO���������̓��b�Z�[�W
     * @@param l_manager - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40C5377E0211
     */
    protected WEB3IpoProductImpl createNewIpoProduct(WEB3IPOProductInfo l_ipoProductInfoInputMessage, WEB3Administrator l_manager) 
    {
        final String STR_METHOD_NAME = " createNewIpoProduct(WEB3IpoProductInfo,WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        //IPO�����s�𐶐�����        
        IpoProductParams l_ipoProductParams = new IpoProductParams();
        
        //IPO�����s�̊e���ڂɏ����l���Z�b�g����
        l_ipoProductParams.setIpoProductId(0);          //IPO�����h�c
        l_ipoProductParams.setInstitutionCode(null);    //�،���ЃR�[�h
        l_ipoProductParams.setProductCode(null);        //�����R�[�h
        l_ipoProductParams.setStandardName(null);       //������
        l_ipoProductParams.setProductType(null);        //�����^�C�v
        l_ipoProductParams.setIpoRegistDiv(null);      //IPO�o�^�敪
        l_ipoProductParams.setIpoRegistDetailDiv(null); //IPO�o�^�敪�ڍ�
        l_ipoProductParams.setPublicOfferingDate(null);                          //���J��
        l_ipoProductParams.setPublicOfferingDateCount(0);                       //���J������  
        l_ipoProductParams.setPublicMarket(null);                                //���J�s��
        l_ipoProductParams.setProvisionalValueDiv(null);                        //�������敪
        l_ipoProductParams.setProvisionalMinValue(null);                        //�����������l
        l_ipoProductParams.setProvisionalMaxValue(null);                        //����������l
        l_ipoProductParams.setProvisionalValueOpenDate(null);                 //�������񎦓�
        l_ipoProductParams.setPublicOfferingQuantity(0);                          //���吔��  
        l_ipoProductParams.setPublicSalesQuantity(0);                          //���o����
        l_ipoProductParams.setDealingQuantity(0);                             //���Ў戵���� 
        l_ipoProductParams.setLeadManagingUnderwriter(null);                   //�劲���،���Ж�
        l_ipoProductParams.setLotSize(0);                                  //�w���\���P��
        l_ipoProductParams.setTickValue(null);                                 //����
        l_ipoProductParams.setIpoUnitDiv(null);                               //�\���p�P�ʋ敪
        l_ipoProductParams.setEnableMarketOrder(null);                        //���s�\
        l_ipoProductParams.setBookbuildingStartDatetime(null);                //�u�b�N�r���f�B���O�J�n����
        l_ipoProductParams.setBookbuildingEndDatetime(null);                  //�u�b�N�r���f�B���O�I������   
        l_ipoProductParams.setPublicPriceSettleDate(null);                   //���J���i�����
        l_ipoProductParams.setPublicPrice(0);                      //���J���i
        l_ipoProductParams.setPublicPriceDiscountRate(0);        //���J���i�i�f�B�X�J�E���g���j
        l_ipoProductParams.setLotDate(null);                                   //���I��
        l_ipoProductParams.setLotDateCount(0);                   //���I������
        l_ipoProductParams.setLotStatus(null);                                 //���I���  
        l_ipoProductParams.setOfferStartDatetime(null);                       //�w���\���J�n����(���Аݒ�)
        l_ipoProductParams.setOfferStartDateCount(0);           //�w���\���J�n������(���Аݒ�j
        l_ipoProductParams.setOfferEndDatetime(null);                         //�w���\���I������(���Аݒ�) 
        l_ipoProductParams.setOfferEndDateCount(0);             //�w���\���I��������(���Аݒ�j
        l_ipoProductParams.setOfferStartDateProspec(null);                   //�w���\���J�n��(�ژ_�����L��)
        l_ipoProductParams.setOfferStartDateCountProspec(0);   //�w���\���J�n������(�ژ_�����L��)
        l_ipoProductParams.setOfferEndDateCountProspec(null);                     //�w���\���I����(�ژ_�����L��)
        l_ipoProductParams.setOfferEndDateCountProspec(0);     //�w���\���I��������(�ژ_�����L��)
        l_ipoProductParams.setCompanyLogoUrl(null);                           //���s��Ѓ��S�t�@@�C��URL
        l_ipoProductParams.setCompanyUrl(null);                                //���s��ЃE�F�u�T�C�gURL
        l_ipoProductParams.setCompanyOutline(null);                            //���s��ЊT�v
        l_ipoProductParams.setNote(null);                                       //���l
        l_ipoProductParams.setIpoStop(null);                                   //IPO��~
        l_ipoProductParams.setDeleteFlag(null);                                //�폜�t���O
        l_ipoProductParams.setLastUpdater(null);                               //�X�V�҃R�[�h
        l_ipoProductParams.setCreatedTimestamp(null);                          //�쐬����
        l_ipoProductParams.setLastUpdatedTimestamp(null);                     //�X�V����
        
        //���͓��e���IPO���������쐬����
        WEB3IpoProductInfoService l_service =
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
        log.debug("l_ipoProductParams = " + l_ipoProductParams);
        log.debug("l_ipoProductInfoInputMessage = " + l_ipoProductInfoInputMessage);        
        log.debug("l_manager = " + l_manager);
        WEB3IpoProductImpl l_ipoProductImpl = l_service.createIpoProduct(l_ipoProductParams,l_ipoProductInfoInputMessage,l_manager);              
                
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductImpl;
        
    }
}
@
