head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʱ���۰�޻��޽�����N���X(WEB3AdminIpoLotResultUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���C�g (���u) �V�K�쐬
Revesion History : 2004/12/27 ���(SRA) �c�Ή�>>>038
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>043
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>051,052
Revesion History : 2005/01/06 ���(SRA) �c�Ή�>>>058
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>066
Revesion History : 2007/04/19 �����q (���u) �d�l�ύXNO.171,NO.172
Revesion History : 2007/07/19 ��іQ (���u) �����̖��013,014
Revesion History : 2007/07/25 ��іQ (���u) �����̖��016
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Arrays;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.ipo.WEB3AdminIpoLotResultUploadCsv;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputResponse;
import webbroker3.ipo.message.WEB3IPOUploadHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadUnitService;
import webbroker3.ipo.service.delegate.WEB3IpoUploadActionUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * �Ǘ���IPO���I���ʱ���۰�޻��޽�����N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadServiceImpl implements WEB3AdminIpoLotResultUploadService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadUnitServiceImpl.class);
    
    /**
     * @@roseuid 4112F1900137
     */
    public WEB3AdminIpoLotResultUploadServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ���IPO���I���ʃA�b�v���[�h���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO���I���ʱ���۰�ޓ���ظ��Ă̏ꍇ<BR>
     * �@@�|get�A�b�v���[�h���()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO���I���ʱ���۰�ފm�Fظ��Ă̏ꍇ<BR>
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ���IPO���I���ʱ���۰�ފ���ظ��Ă̏ꍇ<BR>
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A<BR>
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j��<BR>
     * ���X�|���X�f�[�^.errorMessage�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40E145090150
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        if(l_request instanceof WEB3AdminIPOLotResultUploadInputRequest)
        {
			WEB3AdminIPOLotResultUploadInputResponse l_response = new WEB3AdminIPOLotResultUploadInputResponse();
            try
            {
                
                l_response = this.getUploadScreen((WEB3AdminIPOLotResultUploadInputRequest)l_request);
               
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                l_response.errorInfo = l_ex.getErrorInfo();
				l_response.errorMessage = l_ex.getErrorMessage();
                
            }
			log.exiting(STR_METHOD_NAME);
					return l_response; 
            
        }
        else if(l_request instanceof WEB3AdminIPOLotResultUploadConfirmRequest)
        {
			WEB3AdminIPOLotResultUploadConfirmResponse l_response = new WEB3AdminIPOLotResultUploadConfirmResponse();
            try
            {
            
                l_response = this.validateUploadFile((WEB3AdminIPOLotResultUploadConfirmRequest)l_request);
				          
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            
            }            
			log.exiting(STR_METHOD_NAME);
					return l_response; 
        }
        else if(l_request instanceof WEB3AdminIPOLotResultUploadCompleteRequest)
        {
			WEB3AdminIPOLotResultUploadCompleteResponse l_response = new WEB3AdminIPOLotResultUploadCompleteResponse();
            try
            {   
                
                l_response = this.submitUploadFile((WEB3AdminIPOLotResultUploadCompleteRequest)l_request);
				                
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            
            } 
			log.exiting(STR_METHOD_NAME);
					return l_response;            
            
        }
        else if(l_request instanceof WEB3AdminIPOLotResultUploadCancelRequest)
        {
			WEB3AdminIPOLotResultUploadCancelResponse l_response = new WEB3AdminIPOLotResultUploadCancelResponse();
            try
            {   
                
                l_response = this.undoUpload((WEB3AdminIPOLotResultUploadCancelRequest)l_request);
				
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            
            }            
			log.exiting(STR_METHOD_NAME);
					return l_response; 
        }
        else
        {
            
            log.error("�p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
           
        } 
    }
   
    /**
     * (get�A�b�v���[�h���)<BR>
     * IPO�Ǘ���IPO���I���ʃA�b�v���[�h��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʂt�k�jget�A�b�v���[�h��ʁv�Q�ƁB
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(IPO�i�Ǘ��ҁE���I���ʂt�k�jget�A�b�v���[�h���): <BR>
     *         8(*1) (is�폜����() == true) Or (is���~() == true) Or (is�u�b�N�r���f�B���O�I��() ==<BR> 
     * false)�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00527<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(IPO�i�Ǘ��ҁE���I���ʂt�k�jget�A�b�v���[�h���): <BR>
     *         10(*2) �iis�X�P�W���[������() == false�j�̏ꍇ�A��O���X���[����<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00528<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(IPO�i�Ǘ��ҁE���I���ʂt�k�jget�A�b�v���[�h���): <BR>
     *         12(*3) �iis���J���i����() == false�j�̏ꍇ�A��O���X���[����<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00529<BR>
     * ==========================================================<BR>
     * @@roseuid 40E145090122<BR>
     * @@param l_request - �Ǘ���IPO���I���ʱ���۰�ޓ���ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputResponse
     */
    protected WEB3AdminIPOLotResultUploadInputResponse getUploadScreen(WEB3AdminIPOLotResultUploadInputRequest l_request)
        throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminIpoLotResultUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4.IPO����(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        WEB3IpoProductImpl l_ipoProduct = null; 
              
        try
        {
            
            l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
        }
        catch(NotFoundException l_ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);

        }
        
        
        //1.5.���I���ʃA�b�v���[�hCSV(IPO����)
        WEB3AdminIpoLotResultUploadCsv l_ipoLotResultUploadCsv = 
            new WEB3AdminIpoLotResultUploadCsv(l_ipoProduct);
        
        long l_administratorId = l_ipoLotResultUploadCsv.getAdministratorUploadId();
            
        //1.6.validate�����A�b�v���[�h(long)
        l_ipoLotResultUploadCsv.validateSameTimeUpload(new Long(l_administratorId));
        
        
        //1.7.is�폜����()
        boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
        log.debug("is�폜����() = " + l_blnDeletionProduct);
        
        //1.8.is���~()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        log.debug("is���~() = " + l_blnDiscontinuation);
        
        //1.9.is�u�b�N�r���f�B���O�I��()
        boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
        log.debug("is�u�b�N�r���f�B���O�I��() = " + l_blnBookBuildingEnd);
        
        //1.10.(is�폜����() == true) Or (is���~() == true) Or (is�u�b�N�r���f�B���O�I��() == false)
        //1.10.(is�폜����() == true)�̏ꍇ�A��O���X���[����B
        if(l_blnDeletionProduct)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);            
            
        }
        //1.10.(is���~() == true)�̏ꍇ�A��O���X���[����B
        else if(l_blnDiscontinuation)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //1.10.(is�u�b�N�r���f�B���O�I��() == false)�̏ꍇ�A��O���X���[����B
        else if(!l_blnBookBuildingEnd)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }       
        
        //1.11.is�X�P�W���[������()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        
        //1.12.�iis�X�P�W���[������() == false�j�̏ꍇ�A��O���X���[����
        if(!l_blnScheduleDecision)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00528,
                this.getClass().getName() + STR_METHOD_NAME); 
            
        }
        
        //1.13.is���J���i����()
        boolean l_blnPublicPriceDecision = l_ipoProduct.isPublicPriceSettle();
        
        //1.14.�iis���J���i����() == false�j�̏ꍇ�A��O���X���[����
        if(!l_blnPublicPriceDecision)
        {
            
            log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00529,
                this.getClass().getName() + STR_METHOD_NAME); 
            
        }
      
        //1.15.get�A�b�v���[�h����()
        AdministratorUploadRow[] l_administratorRow = l_ipoLotResultUploadCsv.getUploadActions();
        
        //1.16.
        ArrayList l_list = new ArrayList();
        
        //1.17.
        int l_length = l_administratorRow.length;
        log.debug("l_length = " + l_length);
        for(int i =0; i < l_length; i++)
        {
            
            WEB3IpoUploadActionUnitService l_ipoService = 
                (WEB3IpoUploadActionUnitService)Services.getService(WEB3IpoUploadActionUnitService.class);
            //1.17.1.create�A�b�v���[�h���𖾍�(�i�Ǘ��ҋ��ʁj�A�b�v���[�hParams)
            WEB3IPOUploadHistoryUnit l_ipoUploadHistoryUnit = 
                l_ipoService.createUploadActionUnit(new AdministratorUploadParams(l_administratorRow[i]));
            //1.17.2.add(arg0 : Object)
            l_list.add(l_ipoUploadHistoryUnit);

        }
        
        //1.18.toArray()
        WEB3IPOUploadHistoryUnit[] l_ipoUploadHistoryUnit = new WEB3IPOUploadHistoryUnit[l_length];
        l_list.toArray(l_ipoUploadHistoryUnit);
        
        //1.19.�Ǘ���IPO���I���ʱ���۰�ޓ���ڽ��ݽ(WEB3GenRequest)
        WEB3AdminIPOLotResultUploadInputResponse l_response = 
            (WEB3AdminIPOLotResultUploadInputResponse)l_request.createResponse();
        
        //1.20.�v���p�e�B�Z�b�g
        IpoProductRow l_ipoProductRow =
            (IpoProductRow)l_ipoProduct.getDataSourceObject();
        
        l_response.productCode   = l_ipoProductRow.getProductCode();
        l_response.productName  = l_ipoProductRow.getStandardName();
        l_response.uploadHistoryList = l_ipoUploadHistoryUnit;
        
        log.debug("l_response.productCode = " + l_response.productCode);
        log.debug("l_response.productName = " + l_response.productName);
        log.debug("l_response.uploadHistoryList = " + l_response.uploadHistoryList);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * IPO�Ǘ��ҁE���I���ʃA�b�v���[�h�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * <BR>
     * ========================================================<BR>                        
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate�A�b�v���[�h�t�@@�C���v): <BR>                       
     *   10(*1) (is�폜����() == true) �̏ꍇ�A��O���X���[����B<BR>                      
     *   class: WEB3BusinessLayerException<BR>                       
     *   tag:   BUSINESS_ERROR_00588<BR>                     
     *   10(*1) (is���~() == true) �̏ꍇ�A��O���X���[����B<BR>                        
     *   class: WEB3BusinessLayerException<BR>                       
     *   tag:   BUSINESS_ERROR_00589<BR>                     
     *   10(*1) (is�u�b�N�r���f�B���O�I��() == false)�̏ꍇ�A��O���X���[����B<BR>                       
     *   class: WEB3BusinessLayerException<BR>                       
     *   tag:   BUSINESS_ERROR_00527<BR>                     
     * ==========================================================<BR>  
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate�A�b�v���[�h�t�@@�C���v): <BR>
     *         12(*2) �iis�X�P�W���[������() == false�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00528<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate�A�b�v���[�h�t�@@�C���v): <BR>
     *         14(*3) �iis���J���i����() == false�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00529<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate�A�b�v���[�h�t�@@�C���v): <BR>
     *         21.2.(*5.1) �iis�w���\���I���i���Аݒ�j() == false�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00529<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate�A�b�v���[�h�t�@@�C���v): <BR>
     *        21.4.(*5.2) �iis�V�K���I�I��() == true�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00530<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>                            
     * �V�[���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate�A�b�v���[�h�t�@@�C���v): <BR>                           
     *        22.3.(*6.1) �iis�w���\���J�n�i���Аݒ�j() == false�j�̏ꍇ�A��O���X���[����B<BR>                           
     *         class: WEB3BusinessLayerException<BR>                           
     *         tag:   BUSINESS_ERROR_00526<BR>                         
     *        22.3.(*6.1) �iis�w���\���I���i�ژ_�����L�ځj() == true�j�̏ꍇ�A��O���X���[����B<BR>                         
     *         class: WEB3BusinessLayerException<BR>                           
     *         tag:   BUSINESS_ERROR_01744<BR>                         
     * ==========================================================<BR>  
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate�A�b�v���[�h�t�@@�C���v): <BR>
     *         22.5(*6.2) �iis�V�K���I�I��() == false�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00597<BR>
     * ==========================================================<BR>
     * @@param l_request - �Ǘ���IPO���I���ʱ���۰�ފm�Fظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadConfirmResponse
     * @@roseuid 40E145090131
     */
    protected WEB3AdminIPOLotResultUploadConfirmResponse validateUploadFile(WEB3AdminIPOLotResultUploadConfirmRequest l_request)
        throws WEB3BaseException
    {
		
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminIpoLotResultUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();       
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.7.IPO����(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        WEB3IpoProductImpl l_ipoProduct = null;    
        try
        {    
            
            l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
        }
        catch(NotFoundException l_ex)
        {

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);

        }
        
        //1.4.���I���ʃA�b�v���[�hCSV(IPO����)
        WEB3AdminIpoLotResultUploadCsv l_ipoLotResultUploadCsv = new WEB3AdminIpoLotResultUploadCsv(l_ipoProduct);
        
        //1.5. validate�����A�b�v���[�h(long)
        long l_lngID = l_ipoLotResultUploadCsv.getAdministratorUploadId();                
        l_ipoLotResultUploadCsv.validateSameTimeUpload(null);  
        
        //1.6.save�A�b�v���[�h�J�n(long, String, String, String, String)
        long l_lngProductId = l_ipoProduct.getProductId();
        String l_strLotDiv = l_request.lotDiv;
        String l_administratorCode = l_administartor.getAdministratorCode();
        
        l_ipoLotResultUploadCsv.saveUpLoadStart(l_administratorCode,l_strLotDiv);          

        //1.8.is�폜����()
        boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
        log.debug("is�폜����() = " + l_blnDeletionProduct);        
        //1.9.is���~()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        log.debug("is���~() = " + l_blnDiscontinuation);
        //1.10.is�u�b�N�r���f�B���O�I��()
        boolean l_blnBookbuildingEnd = l_ipoProduct.isBookbuildingEnd();
        log.debug("is�u�b�N�r���f�B���O�I��() = " + l_blnBookbuildingEnd);
        //1.11.(*1)(is�폜����() == true) Or (is���~() == true) Or (is�u�b�N�r���f�B���O�I��() == false)
        //�̏ꍇ��O���X���[����B
        //1.11.(is�폜����() == true)�̏ꍇ�A��O���X���[����B
        if(l_blnDeletionProduct)
        {
            
            //1.11.1.save�A�b�v���[�h�G���[(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00588);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //1.11.(is���~() == true)��O���X���[����B
        else if(l_blnDiscontinuation)
        {
            
            //1.11.1.save�A�b�v���[�h�G���[(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00589);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //1.11.(is�u�b�N�r���f�B���O�I��() == false)�̏ꍇ�A��O���X���[����B
        else if(!l_blnBookbuildingEnd)
        {
            
            //1.11.1.save�A�b�v���[�h�G���[(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00527);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //1.12.is�X�P�W���[������()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        log.debug("is�X�P�W���[������() = " + l_blnScheduleDecision);

        //1.13.(*12)�iis�X�P�W���[������() == false�j�̏ꍇ�A��O���X���[����
        if(!l_blnScheduleDecision)
        {
            
            //1.13.1.save�A�b�v���[�h�G���[(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00528);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00528,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.14.is���J���i����()
        boolean l_blnPublicPriceDecision = l_ipoProduct.isPublicPriceSettle();
        
        //1.15.(*3)(is���J���i����() == false�j�̏ꍇ�A��O���X���[����
        log.debug("is���J���i����() = " + l_blnPublicPriceDecision);
        if(!l_blnPublicPriceDecision)
        {
            
            //1.15.1.save�A�b�v���[�h�G���[(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00529);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00529,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }

        //1.16.validate�L�[�w�b�_(String)
        String l_strUploadFile = l_request.uploadFile[0];
        try
        {
            
            l_ipoLotResultUploadCsv.validateKeyHeader(l_strUploadFile, l_request.lotDiv);
            
        }
        //1.17.(*4)validate�L�[�w�b�_()�ɂė�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����B
        catch(WEB3BaseException l_ex)
        {
            
            //1.17.1.save�A�b�v���[�h�G���[(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw l_ex;

        }
        
        //1.18.get�L��IPO�\��(long)
        long l_lngIdx = l_ipoProduct.getProductId();
        WEB3IpoOrderImpl[] l_ipoOrders = l_ipoOrderManagerImpl.getOpenOrderUnits(l_lngIdx, null);
        
        //1.19.ArrayList(java.util.Arrays.asList(IPO�\��[])
        if(l_ipoOrders == null)
        {
			//save�A�b�v���[�h�G���[(ErrorInfo)
			l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        ArrayList l_list = new ArrayList(Arrays.asList(l_ipoOrders));
        
        //1.20.is�V�K���I()
        boolean l_blnNewLot = l_ipoLotResultUploadCsv.isNewLot();
        
        //1.21.(*5)�V�K���I�̏ꍇ�iis�V�K���I() == true)
        if(l_blnNewLot)
        {
            
            //1.21.1.is�w���\���I���i���Аݒ�j()
            boolean l_blnPurchaseApplicationEndCompanySetting = l_ipoProduct.isOfferEnd();
            
            //1.21.2.(*5.1)is�w���\���I���i���Аݒ�j() == true�j�̏ꍇ�A��O���X���[����B
            if(l_blnPurchaseApplicationEndCompanySetting)
            {

				//1.21.2.1.save�A�b�v���[�h�G���[(ErrorInfo)
				l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00525);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00525,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }
            
            //1.21.3.is�V�K���I�I��()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();
            
            //1.21.4.(*5.2)�iis�V�K���I�I��() == true�j�̏ꍇ�A��O���X���[����
            if(l_blnNewLotteryEnd)
            {
                
				//1.21.4.1.save�A�b�v���[�h�G���[(ErrorInfo)
				l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00530);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00530,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }
            
            //1.21.5.validate���׍s�i�V�K���I�j(���I���ʃA�b�v���[�hCSV, ArrayList, String[])
            this.validateDetailsLineNewLot(l_ipoLotResultUploadCsv, l_list, l_request.uploadFile);
            
        }
        //1.22.(*6)�J�㒊�I�̏ꍇ�iis�V�K���I() == false)
        else
        {
            
            //1.22.1.is�w���\���J�n�i���Аݒ�j()
            boolean l_blnPurchaseApplicationStartCompanySetting  = l_ipoProduct.isOfferStart();
            
            //1.22.2.is�w���\���I���i�ژ_�����L�ځj()
            boolean l_blnOfferEndPros = l_ipoProduct.isOfferEndProspec();
            
            //1.22.3.(*6.1)�iis�w���\���J�n�i���Аݒ�j() == false Or is�w���\���I���i�ژ_�����L�ځj() == true�j
            //�̏ꍇ�A��O���X���[����B
            if(!l_blnPurchaseApplicationStartCompanySetting)
            {
                
				//1.22.3.1.save�A�b�v���[�h�G���[(ErrorInfo)
				l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00526);        
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00526,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }
            //(is�w���\���I���i�ژ_�����L�ځj() == true�j�̏ꍇ�A��O���X���[����
            else if(l_blnOfferEndPros)
            {
                
                //1.22.3.1.save�A�b�v���[�h�G���[
                l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01744);              
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01744,
                    this.getClass().getName() + STR_METHOD_NAME); 
                
            }
            
            //1.22.4.is�V�K���I�I��()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();
            
            //1.22.5.(*6.2)(is�V�K���I�I��() == false�j�̏ꍇ�A��O���X���[����
            log.debug("is�V�K���I�I��() = " + l_blnNewLotteryEnd);
            
            if(!l_blnNewLotteryEnd)
            {

				//1.22.5.1.save�A�b�v���[�h�G���[(ErrorInfo)
				l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00597);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }
            
            //1.22.6.validate���׍s(�J�㒊�I)
            this.validateDetailsLineAdvanceLot(l_ipoLotResultUploadCsv, l_list, l_request.uploadFile);
            
        }
        
        //1.23.get���׍s��()
        int l_intCount = l_ipoLotResultUploadCsv.getRowCount();
        
        //1.24.get�A�b�v���[�h�h�c()
        long l_lngUploadId = l_ipoLotResultUploadCsv.getAdministratorUploadId();
        
        //1.25.save�A�b�v���[�hTemp( )
        l_ipoLotResultUploadCsv.saveUploadTemp();
        
        //1.26.�Ǘ���IPO���I���ʱ���۰�ފm�Fڽ��ݽ(WEB3GenRequest)
        WEB3AdminIPOLotResultUploadConfirmResponse l_response = 
            (WEB3AdminIPOLotResultUploadConfirmResponse)l_request.createResponse();
         
        //1.27.(*6)�v���p�e�B�Z�b�g   
        l_response.uploadNumber = String.valueOf(l_intCount);
        l_response.uploadID = String.valueOf(l_lngUploadId);
        log.debug("l_response.uploadNumber = " + l_response.uploadNumber);
        log.debug("l_response.uploadID = " + l_response.uploadID);    
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�A�b�v���[�h�t�@@�C��)<BR>
     * IPO�Ǘ��ҁE���I���ʃA�b�v���[�h�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʂt�k�jsubmit�A�b�v���[�h�t�@@�C���v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jsubmit�A�b�v���[�h�t�@@�C���v): <BR>
     *        16.2.(*4.1)�iis�w���\���I���i���Аݒ�j() == true�j�̏ꍇ�A��O���X���[����B<BR>
     *        16.4.(*4.2) �V�K���I�ňȉ��̏����ɂ��Ă͂܂�ꍇ�͗�O���X���[����B<BR>
     *        �A�b�v���[�h���Ԃ̃`�F�b�N�i�V�K���I�j<BR>
     *       (*4) �V�K���I�ňȉ��̏����ɂ��Ă͂܂�ꍇ�͗�O���X���[����B<BR>  
     * <BR>
     * �@@        �|�w���\�����Ԃ��I�����Ă���iis�w���\���I���i���Аݒ�j() == true�j<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00525<BR>
     * �@@        �|�V�K���I���I�����Ă���iis�V�K���I() == true�j<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00530<BR> 
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jsubmit�A�b�v���[�h�t�@@�C���v): <BR>
     *        17.3.(*5.1) �iis�w���\���J�n�i���Аݒ�j() == false Or <BR>
     *                      is�w���\���I���i�ژ_�����L�ځj() == true�j�̏ꍇ�A��O���X���[����B<BR>
     *        17.5.(*5.2) �iis�V�K���I() == false�j�̏ꍇ�A��O���X���[����B<BR>
     *        �A�b�v���[�h���Ԃ̃`�F�b�N�i�J�㒊�I�j<BR>
     *       (*5) �J�㒊�I�ňȉ��̏����ɂ��Ă͂܂�ꍇ�͗�O���X���[����B<BR>
     * <BR>
     * �@@        �|�w���\�����Ԃ��J�n���Ă��Ȃ��iis�w���\���J�n�i���Аݒ�j() == false�j<BR>                          
     *              class: WEB3BusinessLayerException<BR>                           
     *              tag:   BUSINESS_ERROR_00526<BR>                         
�@@   *           �|�w���\���I���i�ژ_�����L�ځj���I�����Ă���iis�w���\���I���i�ژ_�����L�ځj() == true�j<BR>                            
     *              class: WEB3BusinessLayerException<BR>                          
     *              tag:   BUSINESS_ERROR_01744<BR>
     * �@@        �|�V�K���I���I�����Ă��Ȃ��iis�V�K���I() == false�j<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00597<BR>  
     * ==========================================================<BR>
     * @@param l_request - �Ǘ���IPO���I���ʱ���۰�ފ���ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadCompleteResponse
     * @@roseuid 40E146B300A5
     */
    protected WEB3AdminIPOLotResultUploadCompleteResponse submitUploadFile(WEB3AdminIPOLotResultUploadCompleteRequest l_request)
        throws WEB3BaseException 

    {
        final String STR_METHOD_NAME = " submitUploadFile(WEB3AdminIpoLotResultUploadCompleteRequest)";
            
        log.entering(STR_METHOD_NAME);    
        
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();       
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate����p�X���[�h(String)
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.4.validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.7.IPO����(Row)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();            
        WEB3IpoProductImpl l_ipoProduct = null;    
        try
        {    
            
            l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
        }
        catch(NotFoundException l_ex)
        {

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
   
        } 
               
        //1.5.���I���ʃA�b�v���[�hCSV(IPO����)
        WEB3AdminIpoLotResultUploadCsv l_ipoLotResultUploadCsv = new WEB3AdminIpoLotResultUploadCsv(l_ipoProduct);        
        
        //1.6.validate�����A�b�v���[�h(long)
        String l_strUploadID = l_request.uploadID;
        long l_lngUploadId = 0;
        if(l_strUploadID != null)
        {
            
            l_lngUploadId = Long.parseLong(l_strUploadID);
            
        }
        else
        {
            
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
            
        }
        l_ipoLotResultUploadCsv.validateSameTimeUpload(new Long(l_lngUploadId));
        
        //1.18.setDataFrom�A�b�v���[�hTemp(long) 
        //saveUpload���邽�߂�this.administratorUploadId�ɃA�b�v���[�hID���Z�b�g����K�v�����邽�߂����Ɉړ��B       
        l_ipoLotResultUploadCsv.setDataFromUploadTemp(l_lngUploadId);
               
        //1.8.is�폜����()
        boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
        log.debug("is�폜����() = " + l_blnDeletionProduct);
        
        //1.9.is���~()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        log.debug("is���~() = " + l_blnDiscontinuation);

        //1.10.is�u�b�N�r���f�B���O�I��()
        boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
        log.debug("is�u�b�N�r���f�B���O�I��() = " + l_blnBookBuildingEnd);
        
        //1.11.(is�폜����() == true) Or (is���~() == true) Or (is�u�b�N�r���f�B���O�I��() == false)�̏ꍇ�A��O���X���[����B
        if(l_blnDeletionProduct)
        {
            
            //1.11.1.save�A�b�v���[�h�G���[
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00588);
            //delete�A�b�v���[�hTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);                        
        
        }
        else if(l_blnDiscontinuation)
        {
            
            //1.11.1.save�A�b�v���[�h�G���[
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00589);
            //delete�A�b�v���[�hTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);             
        
        }
        else if(!(l_blnBookBuildingEnd))
        {
            
            //1.11.1.save�A�b�v���[�h�G���[
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00527);
            //delete�A�b�v���[�hTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                this.getClass().getName() + STR_METHOD_NAME);             
        
        }
        
        //1.12.is�X�P�W���[������()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        log.debug("is�X�P�W���[������() = " + l_blnScheduleDecision);

        //1.13.�iis�X�P�W���[������() == false�j�̏ꍇ�A��O���X���[����
        if(!l_blnScheduleDecision)
        {
            
            //1.13.1.save�A�b�v���[�h�G���[
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00528);
            //delete�A�b�v���[�hTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00528,
                this.getClass().getName() + STR_METHOD_NAME); 
            
        }
        
        //1.14.is���J���i����()
        boolean l_blnPublicPriceDecision = l_ipoProduct.isPublicPriceSettle();
        log.debug("is���J���i����() = " + l_blnPublicPriceDecision); 
        
        //1.15.�uis���J���i����()==false�v�̏ꍇ��O���X���[����        
        if(!l_blnPublicPriceDecision)
        {
            
            //1.15.1.save�A�b�v���[�h�G���[
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00529);            
            //delete�A�b�v���[�hTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00529,
                this.getClass().getName() + STR_METHOD_NAME); 
            
        }
        
         //1.19.is�V�K���I()
         boolean l_blnNewLot = l_ipoLotResultUploadCsv.isNewLot();
         
         //1.16.(*4)�V�K���I�̏ꍇ(is�V�K���I==true)
         if(l_blnNewLot)
         {
            
             ////1.16.1.is�w���\���I��(���Аݒ�)
             boolean l_blnSetting = l_ipoProduct.isOfferEnd();
             
             ////1.16.2.�uis�w���\���I��(���Аݒ�)==true�v�̏ꍇ��O���X���[����
             if(l_blnSetting)
             {
                 
                 //1.16.2.1.save�A�b�v���[�h�G���[
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00525);                 
                 //delete�A�b�v���[�hTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00525,
                     this.getClass().getName() + STR_METHOD_NAME);
                
             }
             
             ////1.16.3.is�V�K���I�I��()
             boolean l_blNewLotEnd = l_ipoProduct.isNewLotteryEnd();
             
             ////1.16.4.�uis�V�K���I�I��()==true�v�̏ꍇ��O���X���[����
             if(l_blNewLotEnd)
             {
                 
                 //1.16.4.1.save�A�b�v���[�h�G���[
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00530);                  
                 //delete�A�b�v���[�hTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                      WEB3ErrorCatalog.BUSINESS_ERROR_00530,
                      this.getClass().getName() + STR_METHOD_NAME);
             
             }    
         }
         //1.17.(*5)�J�グ���I�̏ꍇ(is�V�K���I==false)
         else
         {

             ////1.17.1.is�w���\���J�n(���Аݒ�)
             boolean l_blnStartCompanySetting = l_ipoProduct.isOfferStart();
             
             ////1.17.2.is�w���\���I��(�ژ_�����L��)
             boolean l_blnOfferEndPros = l_ipoProduct.isOfferEndProspec();
             
             ////1.17.3.�uis�w���\���J�n(���Аݒ�)==true Or is�w���\���I��(�ژ_�����L��)==true�v�̏ꍇ��O���X���[����
             if(!(l_blnStartCompanySetting))
             {
                 
                 //1.17.3.1.save�A�b�v���[�h�G���[
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00526);              
                 //delete�A�b�v���[�hTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00526,
                     this.getClass().getName() + STR_METHOD_NAME);
            
             }
             else if(l_blnOfferEndPros)
             {
                 
                 //1.17.3.1.save�A�b�v���[�h�G���[
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01744);              
                 //delete�A�b�v���[�hTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01744,
                     this.getClass().getName() + STR_METHOD_NAME); 
                                    
             }
             
             ////1.17.4.is�V�K���I�I��()
             boolean l_blNewLotEnd = l_ipoProduct.isNewLotteryEnd();
             
             ////1.17.5.�uis�V�K���I�I��()==false�v�̏ꍇ��O���X���[����
             if(!(l_blNewLotEnd))  
             {
                 
                 //1.17.5.1.save�A�b�v���[�h�G���[
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00597);                 
                 //delete�A�b�v���[�hTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                     this.getClass().getName() + STR_METHOD_NAME);                
                         
             }
                   
         }

//        //1.18.setDataFrom�A�b�v���[�hTemp(long)        
//        l_ipoLotResultUploadCsv.setDataFromUploadTemp(l_lngUploadId);
        
        //1.19.is�V�K���I()
        ////1.16�̑O�Ŕ��肪�K�v�̂��߈ړ��B
        
        //1.20.get���׍s��()
        int l_intRowCount = l_ipoLotResultUploadCsv.getRowCount();
        
        //1.21.get�L��IPO�\��For���I(Product_id,String,boolean)
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();        
        WEB3IpoOrderImpl[] l_ipoOrders = l_orderManagerImpl.getOpenOrderUnits(l_ipoProduct.getProductId(), null,l_blnNewLot);
		if(l_ipoOrders == null)
		{
        
            //save�A�b�v���[�h�G���[
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.SYSTEM_ERROR_80005);            
            //delete�A�b�v���[�hTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
		
        }

        //1.22.ArrayList(java.utilArrays.asList(IPO�\��[])
        ArrayList l_lisOrders = new ArrayList(Arrays.asList(l_ipoOrders));
        
        //1.23.(*6)���׍s�̐���LOOP����
        for(int i = 0; i < l_intRowCount; i++)
        {
            
            //1.23.1.get���X�R�[�h(int)
            String l_strBranchCode = l_ipoLotResultUploadCsv.getBranchCode(i);
            
            //1.23.2.get�ڋq�R�[�h(int)
            String l_strAccountCode = l_ipoLotResultUploadCsv.getAccountCode(i);
            
            //1.23.3.get�،����()
            Institution l_institution = l_ipoProduct.getInstitution();
            
            //1.23.4.getBranch(Institution, �_���r���[::java::lang::String)
            Branch l_branch = null;
            MainAccount l_mainAccount = null;
            try
            {
                
                l_branch = l_finApp.getAccountManager().getBranch(l_institution, l_strBranchCode);
                
            }
            catch(NotFoundException l_ex)
            {
                
                //save�A�b�v���[�h�G���[
                l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.SYSTEM_ERROR_80005);                
                //delete�A�b�v���[�hTemp()
                l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
                
            }
            
            //1.23.5.getMainAccount(long, long, �_���r���[::java::lang::String)
            try
            {
                
                l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_institution,l_branch,l_strAccountCode);
                
            }
            catch(NotFoundException l_ex)
            {
                
                //save�A�b�v���[�h�G���[
                l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.SYSTEM_ERROR_80005); 
                //delete�A�b�v���[�hTemp()
                l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
                
            }
            
            //1.23.6.get���I����(int)
            String l_strLotResult = l_ipoLotResultUploadCsv.getLotResult(i);
            
            //1.23.7.get���I����(int)
            double l_dblPrizeQuantity = l_ipoLotResultUploadCsv.getElectedQuantity(i);
            
            //1.23.8.get�D�揇��(int)
            Long l_lngSubstitutePriority = l_ipoLotResultUploadCsv.getSubstitutePriority(i);
            
            //1.23.9.update���I����(ArrayList, boolean, �Ǘ���, �ڋq, String, double, long)
            WEB3AdminIpoLotResultUploadUnitService l_ipoLotResultUploadUnit = 
                (WEB3AdminIpoLotResultUploadUnitService)Services.getService(WEB3AdminIpoLotResultUploadUnitService.class);
            l_ipoLotResultUploadUnit.updateLotResult(
                l_lisOrders,
                l_blnNewLot,
                l_administartor,
                l_mainAccount,
                l_strLotResult,
                l_dblPrizeQuantity,
                l_lngSubstitutePriority);
        }
        
        if(l_blnNewLot)
        {
            
            //1.24.�iis�V�K���I() == true�j�̏ꍇ�A���I�҂̃f�[�^���X�V����
            int l_intSize = l_lisOrders.size();
            WEB3IpoOrderImpl[] l_ipoOrderList = new WEB3IpoOrderImpl[l_intSize];
            
            //1.24.1.toArray()
            l_lisOrders.toArray(l_ipoOrderList);
            
            //1.24.2.IPO�\��[]�itoArray()�̖߂�l�j�e�v�f���Ƃ�LOOP����
            for(int i = 0; i < l_intSize; i++)
            {
                
                WEB3AdminIpoLotResultUploadUnitService l_ipoLotResultUploadUnit = 
                    (WEB3AdminIpoLotResultUploadUnitService)Services.getService(WEB3AdminIpoLotResultUploadUnitService.class);
                //1.24.2.1.update���I����(IPO�\��, boolean, �Ǘ���, String, double, long)                     
                l_ipoLotResultUploadUnit.updateLotResult(
                    l_ipoOrderList[i],
                    true,
                    l_administartor,
                    WEB3LotResultDef.DEFEAT,
                    0,
                    new Long(0));
            }
            
        }
        
        //1.25.�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
        l_ipoProduct.createForUpdateParams();
        
        //1.26.set���I�I��(boolean)(IPO����::set���I�I��)
        l_ipoProduct.setLotteryEnd(l_blnNewLot);
        
        //1.27.save����(IPO����)
        l_ipoProductManagerImpl.saveProduct(l_ipoProduct);
        
        //�A�b�v���[�h�����X�V������ǉ�
		l_ipoLotResultUploadCsv.saveUploadEnd(); 
        
        //1.28.delete�A�b�v���[�hTemp()
        l_ipoLotResultUploadCsv.deleteUploadTemp();   
                  
        //1.29.�Ǘ���IPO���I���ʱ���۰�ފ���ڽ��ݽ(WEB3GenRequest)
        WEB3AdminIPOLotResultUploadCompleteResponse l_response = 
            (WEB3AdminIPOLotResultUploadCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (undo�A�b�v���[�h)<BR>
     * IPO�Ǘ��ҁE���I���ʃA�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʂt�k�jundo�A�b�v���[�h�v�Q�ƁB
     * @@param l_request - �Ǘ���IPO���I���ʱ���۰�ޒ��~ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadCancelResponse
     * @@roseuid 40F77C280307
     */
    protected WEB3AdminIPOLotResultUploadCancelResponse undoUpload(WEB3AdminIPOLotResultUploadCancelRequest l_request) 
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " undoUpload(WEB3AdminIpoLotResultUploadCancelRequest)";
            
        log.entering(STR_METHOD_NAME);
        
        //(1.1)���I���ʃA�b�v���[�hCSV(long)
        long l_lngUploadId = Long.parseLong(l_request.uploadID);
        WEB3AdminIpoLotResultUploadCsv l_ipoLotResultUploadCsv = new WEB3AdminIpoLotResultUploadCsv(l_lngUploadId);
        //(1.2)delete�A�b�v���[�hTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp();
        //(1.3)save�A�b�v���[�h���~()
        l_ipoLotResultUploadCsv.saveUploadStop();        
        WEB3AdminIPOLotResultUploadCancelResponse l_response = 
            (WEB3AdminIPOLotResultUploadCancelResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;    
        
    }
    
    /**
     * (validate���׍s�i�V�K���I�j)<BR>
     * ���׍s�i�V�K���I�j�̃`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate���׍s�i�V�K���I�j�v�Q�ƁB
     * <BR>
     * "���X�R�[�h,�ڋq�R�[�h,�ڋq��,���I����,���I����,�D�揇��"
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate���׍s�i�V�K���I�j�v): <BR>
     *        1.6(*2) �i���I���ʍ��v�l > ���Ў戵���ʁj�̏ꍇ�A��O���X���[����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00598<BR>
     * ==========================================================<BR>
     * @@roseuid 40F4E15C018F
     * @@param l_lotResultCsv - ���I���ʃA�b�v���[�hCSV�f�[�^���f���I�u�W�F�N�g
     * 
     * @@param l_assessmentList - IPO�\����ArrayList
     * 
     * @@param l_strDetailsLines - ���׍s�̔z��<BR>
     */
    protected void validateDetailsLineNewLot(
        WEB3AdminIpoLotResultUploadCsv l_lotResultCsv, 
        ArrayList l_assessmentList, 
        String[] l_strDetailsLines) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateDetailsLineNewLot(WEB3AdminIPOLotResultUploadCsv, ArrayList, String[])";
            
        log.entering(STR_METHOD_NAME);
        
        //1.1.getIPO����()
        WEB3IpoProductImpl l_product = l_lotResultCsv.getIpoProduct();
        //1.2.getOrderValidator()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        l_orderManagerImpl.getOrderValidator();
                    
        //1.3.���׍s[1]�`�e�v�f����LOOP����
        int l_intLength = l_strDetailsLines.length;
        int l_intIndex = 0;
        
        for(int i = 1; i < l_intLength; i++)
        {
            String l_strBranchCode =null;
            String l_strAccountCode =null;
            
            try
            {
                
                //1.3.1.add���׍s(String)
                l_intIndex = l_lotResultCsv.addRow(l_strDetailsLines[i]);
                
            }
            catch(WEB3SystemLayerException l_ex)
            {
                //1.3.2.add���׍s()�ŗ�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
                //1.3.2.1.save�A�b�v���[�h�G���[(ErrorInfo)

                l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());
                throw new WEB3SystemLayerException(l_ex.getErrorInfo(), l_ex.getErrorMethod(),
                    "���׍s������i=" + l_strDetailsLines[i] + "�j", l_ex.getException());
                
            }
			//2004/12/7 U00525 ��O�����̃X�R�[�v���s�K�؁@@���@@SRA START
            String l_strBranchCodeErrDisp = null;
            String l_strAccountCodeErrDisp = null;
            try
            {
                //1.3.3.(*) ��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
                if (l_intIndex < 0)
                {
                    continue;
                }

                l_strBranchCodeErrDisp = l_lotResultCsv.getBranchCode(l_intIndex);
                l_strAccountCodeErrDisp = l_lotResultCsv.getAccountCode(l_intIndex);
                
                //1.3.4.validate���ڃ����O�X(�s�ԍ�:int)
                l_lotResultCsv.validateItemLength(l_intIndex);
                //1.3.5.get���I����(int)
                double l_dblPrizeQuantity = l_lotResultCsv.getElectedQuantity(l_intIndex);
                //1.3.6.get���I����(int)
                String l_strLotResult = l_lotResultCsv.getLotResult(l_intIndex);
                //1.3.7.���I���� == ���I�܂��́A�⌇�̏ꍇ
                WEB3IpoOrderValidator l_ipoOrderCheck = new WEB3IpoOrderValidator();
                if(WEB3LotResultDef.ELECTION.equals(l_strLotResult)
                   || WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult))
                {
                    
                    //1.3.7.1.validate����(IPO����, double)
                    l_ipoOrderCheck.validateQuantity(l_product,l_dblPrizeQuantity);//throw WEB3BaseException
                    
                }
                //1.3.8.validate�V�K���I���I����(String)
                l_lotResultCsv.validateNewLotLotResult(l_strLotResult);
                //1.3.9.get�D�揇��(int)
                Long l_lngSubstitutePriority = l_lotResultCsv.getSubstitutePriority(l_intIndex);
                //1.3.10.validate�D�揇��(String, long)
                l_lotResultCsv.validateSubstitutePriority(l_strLotResult, l_lngSubstitutePriority);
//                //1.3.11.validate�d���ڋq(int)
//                l_lotResultCsv.validateDuplicateAccount(l_intIndex);
                //1.3.12.get���X�R�[�h(�s�ԍ��i=add���׍s�̖߂�l�j : int)
                l_strBranchCode = l_lotResultCsv.getBranchCode(l_intIndex);
    //			String l_strBranchCode = l_lotResultCsv.getBranchCode(l_intIndex);
                //1.3.13.get�،����()
                Institution l_institution = l_product.getInstitution();
                //1.3.14.getBranch(Institution, �_���r���[::java::lang::String)
                Branch l_branch = null;
    //            try
    //            {
                    
                    l_branch = l_finApp.getAccountManager().getBranch(l_institution,l_strBranchCode);
                    
    //            }
    //            catch(NotFoundException l_ex)
    //            {
    //                
    //                log.exiting(STR_METHOD_NAME);
    //				//2004/12/06 U00520 �޼�Ƚ�װ�ɏC���@@���@@SRA START
    //				throw new WEB3BusinessLayerException(
    //				WEB3ErrorCatalog.BUSINESS_ERROR_00017,
    ////				throw new WEB3SystemLayerException(
    ////				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    //				//2004/12/06 U00520 �޼�Ƚ�װ�ɏC���@@���@@SRA END
    //                    STR_METHOD_NAME,
    //                    l_ex.getMessage(),
    //                    l_ex);
    //                
    //            }
                //1.3.15.get�ڋq�R�[�h(int)
                l_strAccountCode = l_lotResultCsv.getAccountCode(l_intIndex);
    //			String l_strAccountCode = l_lotResultCsv.getAccountCode(l_intIndex);
                //1.3.16.getMainAccount(long, �_���r���[::java::lang::String, �_���r���[::java::lang::String)(
    //            try
    //            {
                    
    //                l_finApp.getAccountManager().getMainAccount(l_institution,l_branch,l_strAccountCode);

                //1.3.16.get�ڋq
                WEB3GentradeAccountManager l_genAccountMgr = null;
                MainAccount l_maMainAccount = null;
                try
                {
                    l_genAccountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                    l_maMainAccount = l_genAccountMgr.getMainAccount(
                        l_institution.getInstitutionCode(),
                        l_strBranchCode,
                        l_strAccountCode);
                }
                catch(WEB3SystemLayerException l_ex)
                {
                    if (WEB3ErrorCatalog.SYSTEM_ERROR_80003.equals(l_ex.getErrorInfo())
                        || WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo())
                        || WEB3ErrorCatalog.SYSTEM_ERROR_80006.equals(l_ex.getErrorInfo()))
                    {
                        log.debug("�Y������f�[�^�����݂��܂���B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                            l_ex.getErrorMethod());
                    }
                    else if (WEB3ErrorCatalog.SYSTEM_ERROR_80017.equals(l_ex.getErrorInfo()))
                    {
                        log.debug("���̓p�����[�^�`�F�b�N�G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                            l_ex.getErrorMethod());
                    }
                    throw l_ex;
                }

                    //1.3.17.set�ڋq
                    l_lotResultCsv.setAccount(l_intIndex,l_maMainAccount.getAccountId());
                    
                 //1.3.10.validate�d���ڋq(int)��1.3.16�Ōڋq�R�[�h��6������7���ɃZ�b�g���Ȃ�����邽�߁A7�����m�̔�r���ł���悤�ړ�
                              l_lotResultCsv.validateDuplicateAccount(l_intIndex);
    //            }
    //            catch(NotFoundException l_ex)
    //            {
    //                
    //                log.exiting(STR_METHOD_NAME);
    //
    //				//2004/12/06 U00520 �޼�Ƚ�װ�ɏC���@@���@@SRA START
    //				throw new WEB3BusinessLayerException(
    //				WEB3ErrorCatalog.BUSINESS_ERROR_00017,
    ////				throw new WEB3SystemLayerException(
    ////				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    //				//2004/12/06 U00520 �޼�Ƚ�װ�ɏC���@@���@@SRA END
    //                    STR_METHOD_NAME,
    //                    l_ex.getMessage(),
    //                    l_ex);
    //                
    //            }
                //1.3.18.(*1.3)(���I����==���I)�̏ꍇ
                if(WEB3LotResultDef.ELECTION.equals(l_strLotResult))
                {
                    //1.3.18.1.add���I����(double)
                    l_lotResultCsv.addElectedQuantity(l_dblPrizeQuantity);
                }
                
                //1.3.19 ���I���� == ���I�܂��́A�⌇�̏ꍇ
                if (WEB3LotResultDef.ELECTION.equals(l_strLotResult)
                    || WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult))
                {
                    //1.3.19.1 validateIPO�\���i�V�K���I�j(ArrayList, String, String, double)
                    validateIpoOrderNewLot(l_assessmentList,
                        l_lotResultCsv.getAccountCode(l_intIndex),
                        l_lotResultCsv.getBranchCode(l_intIndex),
                        l_dblPrizeQuantity);
                }
    //            try
    //            {
                    
                    
                    
    //            }
    //            catch(WEB3BaseException l_ex)
    //            {
    //                
    //                //(1.3.17)get���I����()�`validateIPO�\��()�p�̎葱���ŗ�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
    //                l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());
    //                
    //                log.exiting(STR_METHOD_NAME);
    //                log.error(STR_METHOD_NAME, l_ex);
    //                throw new WEB3BaseException(l_ex.getErrorInfo(), STR_METHOD_NAME, l_strBranchCode + "." + l_strAccountCode);
    //                
    //            }
			}
			catch(WEB3BaseException l_ex)
			{           
				//1.3.20.1.get���I����()�`validateIPO�\��()�̎葱���ŗ�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
				l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());                
				log.exiting(STR_METHOD_NAME);
				log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),                
                    STR_METHOD_NAME,                
                    l_strBranchCodeErrDisp + "," + l_strAccountCodeErrDisp);                
			}
			catch(NotFoundException nf_ex)
			{
				l_lotResultCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01386);
				log.exiting(STR_METHOD_NAME);
				log.error(STR_METHOD_NAME, nf_ex);
                throw new WEB3BusinessLayerException(               
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386,              
                    STR_METHOD_NAME,                
                    l_strBranchCodeErrDisp + "," + l_strAccountCodeErrDisp);                
			}
			//2004/12/7 U00525 ��O�����̃X�R�[�v���s�K�؁@@���@@SRA END
        }
        
        //1.4.get���I���ʍ��v�l( )
        double l_dblPrizeQuantityTotal = l_lotResultCsv.getElectedQuantityTotal();
        
        //1.5.get���Ў戵����()
        double l_dblDealingQuantity = l_product.getDealingQuantity();
        //1.6.�i���I���ʍ��v�l > ���Ў戵���ʁj�̏ꍇ�A��O���X���[����
        
        if(l_dblPrizeQuantityTotal > l_dblDealingQuantity)
        {
            
            //1.6.1.save�A�b�v���[�h�G���[(ErrorInfo)
            l_lotResultCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00598);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
            //2004/12/7 U00526 �޼�Ƚ�װ���s�K�؂̂��ߏC���@@���@@SRA  START
                WEB3ErrorCatalog.BUSINESS_ERROR_00598,
//			    WEB3ErrorCatalog.BUSINESS_ERROR_00599,
			//2004/12/7 U00526 �޼�Ƚ�װ���s�K�؂̂��ߏC���@@���@@SRA  END
                this.getClass().getName() + STR_METHOD_NAME);
            
        }

    }
    
    /**
     * ���׍s�i�J�㒊�I�j�̃`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate���׍s�i�J�㒊�I�j�v�Q�ƁB
     * "���X�R�[�h,�ڋq�R�[�h,�ڋq��,���I����,���I����,�D�揇��"
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE���I���ʂt�k�jvalidate���׍s�i�J�㒊�I�j�v): <BR>
     *        1.9(*3) { ���I���ʍ��v�l > �i���Ў戵���� - �����m�萔�ʁj }�̏ꍇ�A��O���X���[����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00599<BR>
     * ==========================================================<BR>
     * @@param l_lotResultCsv - ���I���ʃA�b�v���[�hCsv�f�[�^���f���I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_assessmentList - IPO�\����ArrayList<BR>
     * <BR>
     * @@param l_strDetailsLines - ���׍s�̔z��<BR>
     * @@roseuid 40F4E45D0141
     */
    protected void validateDetailsLineAdvanceLot(
        WEB3AdminIpoLotResultUploadCsv l_lotResultCsv, 
        ArrayList l_assessmentList, 
        String[] l_strDetailsLines) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateDetailsLineAdvanceLot(WEB3AdminIPOLotResultUploadCsv, ArrayList, String[])";
            
        log.entering(STR_METHOD_NAME);
        
        //1.1.toArray()
        int l_intLength = l_assessmentList.size();
        WEB3IpoOrderImpl[] l_ipoOrders = new WEB3IpoOrderImpl[l_intLength];
        l_assessmentList.toArray(l_ipoOrders);
        //(1.3) getIPO����()
        WEB3IpoProductImpl l_product = l_lotResultCsv.getIpoProduct();
        //1.2.IPO�\��[]�iIPO�\��List.toArray()�j�e�v�f����LOOP����
        
        for(int i = 0; i < l_intLength; i++)
        {
            //1.2.1.add�����m�萔��(IPO�\��, IPO����)
            l_lotResultCsv.addAllotQuantity(l_ipoOrders[i], l_product);
            //1.2.2.is�⌇��()
            boolean l_blnWaiting = l_ipoOrders[i].isWaiting();
            //1.2.3.�⌇�҂łȂ��ꍇ�iis�⌇��() == false�j�A�Ώۗv�f��IPO�\��List���폜����
            if(!l_blnWaiting)
            {
                
                //1.2.3.1.indexOf((arg0�i=IPO�\��[index]) : Object)
                int l_intIndex = l_assessmentList.indexOf(l_ipoOrders[i]);
                //1.2.3.2.remove(arg0 : int)
                l_assessmentList.remove(l_intIndex);
                
            }
 
        }
        //1.4.getOrderValidator( )            
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        l_orderManagerImpl.getOrderValidator();
                
        //1.5.���׍s[1]�`�̊e�v�f����LOOP����
        int l_intLgn = l_strDetailsLines.length;
        int l_intRowIndex = 0;
        for(int i = 1; i < l_intLgn; i++)
        {
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            
            //1.5.1.add���׍s(���׍s������i=���׍s[index]�j : String)
            try
            {
                
                l_intRowIndex = l_lotResultCsv.addRow(l_strDetailsLines[i]);
                
            }
            catch(WEB3SystemLayerException l_ex)
            {
                
                //1.5.2.add���׍s()�ŗ�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
                //1.5.2.1.save�A�b�v���[�h�G���[(ErrorInfo)
                l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());
                    
                throw new WEB3SystemLayerException(l_ex.getErrorInfo(), l_ex.getErrorMethod(),
                    "���׍s������i=" + l_strDetailsLines[i] + "�j", l_ex.getException());

            }
            
            //2004/12/7 U00525 ��O�����̃X�R�[�v���s�K�؁@@���@@SRA START
            String l_strBranchCodeErrDisp = null;
            String l_strAccountCodeErrDisp = null;
            try
            {
                //1.5.3.(*) ��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
                if (l_intRowIndex < 0)
                {
                    continue;
                }
                
                l_strBranchCodeErrDisp = l_lotResultCsv.getBranchCode(l_intRowIndex);
                l_strAccountCodeErrDisp = l_lotResultCsv.getAccountCode(l_intRowIndex);

                //1.5.4.validate���ڃ����O�X(�s�ԍ�:int)
                l_lotResultCsv.validateItemLength(l_intRowIndex);            	
                //1.5.5.get���I����(int)
                double l_dblPrizeQuantity = l_lotResultCsv.getElectedQuantity(l_intRowIndex);
                //1.5.6.get���I����(int)
                String l_strLotResult = l_lotResultCsv.getLotResult(l_intRowIndex);
                //1.5.7.���I���� == ���I�̏ꍇ
                if(WEB3LotResultDef.ELECTION.equals(l_strLotResult))
                {
                    
                    //1.5.7.1.validate����(IPO����, double)
                    WEB3IpoOrderValidator l_ipoOrderCheck = new WEB3IpoOrderValidator();
                    l_ipoOrderCheck.validateQuantity(l_product, l_dblPrizeQuantity);
                    
                }
                //1.5.8.validate�J�㒊�I���I����(String, double)
                l_lotResultCsv.validateAdvanceLotLotResult(l_strLotResult, l_dblPrizeQuantity);
                //1.5.9.get�D�揇��(int)
                Long l_lngSubstitutePriority = l_lotResultCsv.getSubstitutePriority(l_intRowIndex);
                //1.5.10.validate�D�揇��(String, long)
                l_lotResultCsv.validateSubstitutePriority(l_strLotResult, l_lngSubstitutePriority);
//                //1.5.11.validate�d���ڋq(int)
//                l_lotResultCsv.validateDuplicateAccount(l_intRowIndex);
                //1.5.12.get���X�R�[�h(int)
                l_strBranchCode = l_lotResultCsv.getBranchCode(l_intRowIndex);
    //			String l_strBranchCode = l_lotResultCsv.getBranchCode(l_intRowIndex);
                //1.5.13.get�،����()
                Institution l_institution = l_product.getInstitution();
                //1.5.14.getBranch(long)
                Branch l_branch = null;
                
    //            try
    //            {
                    
                    l_branch = l_finApp.getAccountManager().getBranch(l_institution, l_strBranchCode);
                    
    //            }
    //            catch(NotFoundException l_ex)
    //            {
    //                
    //                log.exiting(STR_METHOD_NAME);
    //				//2004/12/06 U00520 �޼�Ƚ�װ�ɏC���@@���@@SRA START
    //				throw new WEB3BusinessLayerException(
    //				WEB3ErrorCatalog.BUSINESS_ERROR_00017,
    ////				throw new WEB3SystemLayerException(
    ////				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    //				//2004/12/06 U00520 �޼�Ƚ�װ�ɏC���@@���@@SRA END
    //                    STR_METHOD_NAME,
    //                    l_ex.getMessage(),
    //                    l_ex);
    //                
    //            }
                //1.5.15.get�ڋq�R�[�h(int)
                l_strAccountCode = l_lotResultCsv.getAccountCode(l_intRowIndex);
    //			String l_strAccountCode = l_lotResultCsv.getAccountCode(l_intRowIndex);
    //            try
    //            {
                    
    //                //(1.5.15)getMainAccount(long)
    //                l_finApp.getAccountManager().getMainAccount(l_institution, l_branch, l_strAccountCode);

                //1.5.16.get�ڋq
                WEB3GentradeAccountManager l_genAccountMgr = null;
                MainAccount l_maMainAccount = null;
                try
                {
                    l_genAccountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                    l_maMainAccount = l_genAccountMgr.getMainAccount(
                        l_institution.getInstitutionCode(),
                        l_strBranchCode,
                        l_strAccountCode);
                }
                catch(WEB3SystemLayerException l_ex)
                {
                    if (WEB3ErrorCatalog.SYSTEM_ERROR_80003.equals(l_ex.getErrorInfo())
                        || WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo())
                        || WEB3ErrorCatalog.SYSTEM_ERROR_80006.equals(l_ex.getErrorInfo()))
                    {
                        log.debug("�Y������f�[�^�����݂��܂���B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                            l_ex.getErrorMethod());
                    }
                    else if (WEB3ErrorCatalog.SYSTEM_ERROR_80017.equals(l_ex.getErrorInfo()))
                    {
                        log.debug("���̓p�����[�^�`�F�b�N�G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                            l_ex.getErrorMethod());
                    }
                    throw l_ex;
                }

                //1.5.17.set�ڋq
                    l_lotResultCsv.setAccount(l_intRowIndex,l_maMainAccount.getAccountId());
    
                //1.3.10.validate�d���ڋq(int)��1.5.16�Ōڋq�R�[�h��6������7���ɃZ�b�g���Ȃ�����邽�߁A7�����m�̔�r���ł���悤�ړ�
                l_lotResultCsv.validateDuplicateAccount(l_intRowIndex);                    
    //            }
    //            catch(NotFoundException l_ex)
    //            {
    //                
    //                log.exiting(STR_METHOD_NAME);
    //				//2004/12/06 U00520 �޼�Ƚ�װ�ɏC���@@���@@SRA START
    //				throw new WEB3BusinessLayerException(
    //				WEB3ErrorCatalog.BUSINESS_ERROR_00017,
    ////				throw new WEB3SystemLayerException(
    ////				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    //				//2004/12/06 U00520 �޼�Ƚ�װ�ɏC���@@���@@SRA END
    //                    STR_METHOD_NAME,
    //                    l_ex.getMessage(),
    //                    l_ex);
    //                
    //            }            
                //1.5.18.add���I����(double)
                l_lotResultCsv.addElectedQuantity(l_dblPrizeQuantity);
                //1.5.19.validateIPO�\���i�J�㒊�I�j(ArrayList, String, String, double)
    //            try
    //            {
                    
                    this.validateIpoOrderAdvanceLot(l_assessmentList,
                        l_lotResultCsv.getAccountCode(l_intRowIndex),
                        l_lotResultCsv.getBranchCode(l_intRowIndex),
                        l_dblPrizeQuantity);
                    
    //            }
    //            catch(WEB3BaseException l_ex)
    //            {
    //                
    //                //(1.5.17)get���I����()�`validateIPO�\��()�p�̎葱���ŗ�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
    //                l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());
    //                
    //                log.exiting(STR_METHOD_NAME);
    //                log.error(STR_METHOD_NAME, l_ex);
    //                throw new WEB3BaseException(l_ex.getErrorInfo(), STR_METHOD_NAME, l_strBranchCode + "." + l_strAccountCode);
    //                
    //            }
			  }
			  catch(WEB3BaseException l_ex)
			  {
                
				  //1.5.19.get���I����()�`validateIPO�\��()�̎葱���ŗ�O�����������ꍇ�A�A�b�v���[�h�G���[���X�V����
				  l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());                
				  log.exiting(STR_METHOD_NAME);
				  log.error(STR_METHOD_NAME, l_ex);
				  throw new WEB3BaseException(
                      l_ex.getErrorInfo(), 
                      STR_METHOD_NAME,
                      l_strBranchCodeErrDisp + "," + l_strAccountCodeErrDisp);                
			  }
			  catch(NotFoundException nf_ex)
			  {
				l_lotResultCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01386);
				log.exiting(STR_METHOD_NAME);
				log.error(STR_METHOD_NAME, nf_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                    STR_METHOD_NAME,
                    l_strBranchCodeErrDisp + "," + l_strAccountCodeErrDisp);
			  }
			//2004/12/7 U00525 ��O�����̃X�R�[�v���s�K�؁@@���@@SRA END       
        }
        //1.6.get�����m�萔�ʍ��v�l()
        double l_dblQuantityTotal = l_lotResultCsv.getAllotQuantityTotal();
        //1.7.get���I���ʍ��v�l()
        double l_dblPrizeQuantityTotal = l_lotResultCsv.getElectedQuantityTotal();
        //1.8.get���Ў戵����()
        double l_dblDealingQuantity = l_product.getDealingQuantity();
        //1.9.{ ���I���ʍ��v�l > �i���Ў戵���� - �����m�萔�ʁj }�̏ꍇ�A��O���X���[����
        double l_dblRemoteQuantity = l_dblDealingQuantity - l_dblQuantityTotal;
        if(l_dblPrizeQuantityTotal > l_dblRemoteQuantity)
        {
            
            //1.9.1.save�A�b�v���[�h�G���[(ErrorInfo)
            l_lotResultCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00599);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00599,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
    }
    
    /**
     * (validateIPO�\���i�V�K���I�j)<BR>
     * �V�K���I�A�b�v���[�h�s�ɂ��āAIPO�\���f�[�^�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�z��擾<BR>
     * �@@IPO�\��List.toArray()�ɂāAIPO�\���̔z����擾����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�ɊY������IPO�\�����擾����B<BR>
     * �@@�P�j�Ŏ擾�����z����A�ȉ��̏����Ɉ�v����v�f���擾����B<BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A�\���ς݂̌ڋq�ł͂Ȃ��Ɣ��肵�A��O���X���[����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�ڋq�R�[�h == IPO�\��[index].get�ڋq�R�[�h()<BR>
     * �@@���X�R�[�h == IPO�\��[index].getMainAccount().getBranch().getBranchCode()<BR>  
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00510<BR>
     * <BR>
     * �R�j�@@�ڋq�ɊY������IPO�\���̃`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@����̃`�F�b�N<BR>
     * �@@�@@IPO�\�����L���łȂ��ꍇ�iIPO�\��[index].getIPOI�\���L�����() == 
     * <BR>OrderOpenStatusEnum.CLOSE�j�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00511<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���I���ʂ̃`�F�b�N<BR>
     * �@@�@@IPO�\�������I���ʍX�V�ς݂̏ꍇ�iIPO�\��[index].is���I���ʍX�V��()<BR> == true�j�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00512<BR>
     * <BR>
     * �@@�R�|�R�j�@@���ʂ̃`�F�b�N<BR>
     * �@@�@@���I���ʂ��\�����ʂ𒴂��Ă���ꍇ�iIPO�\��[index].get����() < <BR>���I���ʁj�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00513<BR>
     * <BR>
     * �S�j�@@�`�F�b�N��̗v�f��List���폜<BR>
     * �@@IPO�\��List.indexOf(), remove()�ɂāA�Y���v�f��List���폜����B<BR>
     * <BR>
     * �@@[indexOf()�Ɏw�肷�����]<BR>
     * �@@arg0�F�@@IPO�\��[index]<BR>
     * <BR>
     * �@@[remove()�Ɏw�肷�����]<BR>
     * �@@arg0�F�@@�iindexOf()�̖߂�l�j<BR>
     * <BR>
     * @@param l_orderList - IPO�\����ArrayList
     * 
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_dblElectedQuantity - ���I����
     * @@roseuid 40F5292B019F
     */
    protected void validateIpoOrderNewLot(ArrayList l_orderList,
        String l_strAccountCode, 
        String l_strBranchCode,
        double l_dblElectedQuantity)
        throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateIpoAssessmentNewLot(ArrayList, String, String, double)";
            
        log.entering(STR_METHOD_NAME);
        
        if (l_strAccountCode == null || l_strBranchCode == null)
        {
            log.debug("���̓p�����[�^�`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                this.getClass().getName() + STR_METHOD_NAME,
                "���̓p�����[�^�`�F�b�N�G���[�B");
        }

        //�P�j�z��擾
        int l_intLength = l_orderList.size();
        WEB3IpoOrderImpl[] l_ipoOrders = new WEB3IpoOrderImpl[l_intLength];
        l_orderList.toArray(l_ipoOrders);
        
        int l_intCnt = 0;
        for(int i = 0; i < l_intLength; i++)
        {
            
            //�Q�j�@@�ڋq�ɊY������IPO�\�����擾����B
            String l_strCode = l_ipoOrders[i].getAccountCode();
            String l_strBranchCodes = l_ipoOrders[i].getMainAccount().getBranch().getBranchCode();
            if(l_strAccountCode.equals(l_strCode) &&
                l_strBranchCode.equals(l_strBranchCodes))
            {
                
                l_intCnt++;
                //�R�j�@@�ڋq�ɊY������IPO�\���̃`�F�b�N
                OrderOpenStatusEnum l_orderOpenStatus = l_ipoOrders[i].getOrderOpenStatus();
                //�R�|�P�j�@@����̃`�F�b�N
                if(OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00511,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //�R�|�Q�j�@@���I���ʂ̃`�F�b�N
                if(l_ipoOrders[i].isLotResultUpdated())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00512,
                        this.getClass().getName() + STR_METHOD_NAME);                
                }
                //�R�|�R�j�@@���ʂ̃`�F�b�N
                if(l_ipoOrders[i].getQuantity() < l_dblElectedQuantity)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00513,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //�S�j�@@�`�F�b�N��̗v�f��List���폜
                int l_intIndex = l_orderList.indexOf(l_ipoOrders[i]);
                l_orderList.remove(l_intIndex);
                log.exiting(STR_METHOD_NAME);
                break;
            }
        }
        if(l_intCnt == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00510,
                this.getClass().getName() + STR_METHOD_NAME);  
            
        }

    }
    
    /**
     * (validateIPO�\���i�J�㒊�I�j)<BR>
     * �J�㒊�I�A�b�v���[�h�s�ɂ��āAIPO�\���f�[�^�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�z��擾<BR>
     * �@@IPO�\��List.toArray()�ɂāAIPO�\���̔z����擾����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�ɊY������IPO�\�����擾����B<BR>
     * �@@�P�j�Ŏ擾�����z����A�ȉ��̏����Ɉ�v����v�f���擾����B<BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A�⌇�҂̃f�[�^�����݂��Ȃ��Ɣ��肵�A��O���X���[����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�ڋq�R�[�h == IPO�\��[index].get�ڋq�R�[�h()<BR>
     * �@@���X�R�[�h == IPO�\��[index].getMainAccount().getBranch().getBranchCode()<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01995<BR>
     * <BR>
     * �R�j�@@�ڋq�ɊY������IPO�\���̃`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@����̃`�F�b�N<BR>
     * �@@�@@IPO�\�����L���łȂ��ꍇ�iIPO�\��[index].getIPOI�\���L�����() == 
     * <BR>OrderOpenStatusEnum.CLOSE�j�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00511<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�i�⌇���I�̏ꍇ�j�w���\���̃`�F�b�N<BR>
     * �@@�@@�⌇���I��IPO�\�����w���\���ς݂łȂ��ꍇ�i���I���� != 0 && <BR>IPO�\��[index].is�w���\��() == 
     * false�j�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00514<BR>
     * <BR>
     * �@@�R�|�R�j�@@���ʂ̃`�F�b�N<BR>
     * �@@�@@���I���ʂ��w���\�����ʂ𒴂��Ă���ꍇ<BR>
     * �iIPO�\��[index].IPO�\���s.�w���\������ < ���I���ʁj�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01991<BR>
     * <BR>
     * �@@�R�|�S�j�@@�J�㒊�I�ς݂̃`�F�b�N<BR>
     * �@@�@@���ɌJ�㒊�I���ʂ��X�V����Ă���ꍇ<BR>
     * �iIPO�\��[index].IPO�\���s.���I���ʁi�J��j != 0�FDEFAULT�i�����I�j�j�A<BR>��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00515<BR>
     * <BR>
     *   �R�|�T�j�@@�i�⌇���I�̏ꍇ�j���ނ̃`�F�b�N<BR>
     *   �⌇���I��IPO�\�������ލς̏ꍇ<BR>
     * �i���I���� == 0 && IPO�\��[index].is����() == true�j�A<BR>
     *  ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01741<BR>
     * <BR>
     * �S�j�@@�`�F�b�N��̗v�f��List���폜<BR>
     * �@@IPO�\��List.indexOf(), remove()�ɂāA�Y���v�f��List���폜����B<BR>
     * <BR>
     * �@@[indexOf()�Ɏw�肷�����]<BR>
     * �@@arg0�F�@@IPO�\��[index]<BR>
     * <BR>
     * �@@[remove()�Ɏw�肷�����]<BR>
     * �@@arg0�F�@@�iindexOf()�̖߂�l�j<BR>
     * @@param l_orderList - IPO�\����ArrayList
     * 
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_dblElectedQuantity - ���I����
     * @@roseuid 40F606170273
     */
    protected void validateIpoOrderAdvanceLot(
        ArrayList l_orderList,
        String l_strAccountCode,
        String l_strBranchCode,
        double l_dblElectedQuantity) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateIpoOrderAdvanceLot(ArrayList, String, String, double)";
            
        log.entering(STR_METHOD_NAME);
        
        if (l_strAccountCode == null || l_strBranchCode == null)
        {
            log.debug("���̓p�����[�^�`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                this.getClass().getName() + STR_METHOD_NAME,
                "���̓p�����[�^�`�F�b�N�G���[�B");
        }
        //�P�j�z��擾
        int l_intLength = l_orderList.size();
        WEB3IpoOrderImpl[] l_ipoOrders = new WEB3IpoOrderImpl[l_intLength];
        l_orderList.toArray(l_ipoOrders);
        int l_intFlag = 0;
        for(int i = 0; i < l_intLength; i++)
        {
            
            //�Q�j�@@�ڋq�ɊY������IPO�\�����擾����B
            String l_strCode = l_ipoOrders[i].getAccountCode();
            log.debug("l_strCode = " + l_strCode);
            String l_strBranchCodes = l_ipoOrders[i].getMainAccount().getBranch().getBranchCode();
            if(l_strAccountCode.equals(l_strCode) &&
                l_strBranchCode.equals(l_strBranchCodes))
            {
                
                l_intFlag++;
                //�R�j�@@�ڋq�ɊY������IPO�\���̃`�F�b�N
                OrderOpenStatusEnum l_orderOpenStatus = l_ipoOrders[i].getOrderOpenStatus();
                //�R�|�P�j�@@����̃`�F�b�N
                if(OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00511,
                    this.getClass().getName() + STR_METHOD_NAME);
                }
                //�R�|�Q�j�@@�i�⌇���I�̏ꍇ�j�w���\���̃`�F�b�N
                if(l_dblElectedQuantity != 0 && !(l_ipoOrders[i].isOffered()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00514,
                        this.getClass().getName() + STR_METHOD_NAME);                
                }
                //�R�|�R�j�@@���ʂ̃`�F�b�N
                IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrders[i].getDataSourceObject();
				if(l_ipoOrderParams.getApplicationQuantity() < l_dblElectedQuantity)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01991,
                        this.getClass().getName() + STR_METHOD_NAME);         
                }
                //�R�|�S�j�@@�J�㒊�I�ς݂̃`�F�b�N
                if(!WEB3LotResultRetryDef.DEFAULT.equals(l_ipoOrderParams.getLotResultRetry()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00515,
                        this.getClass().getName() + STR_METHOD_NAME);                  
                }
                //�R�|�T�j�@@�i�⌇���I�̏ꍇ�j���ނ̃`�F�b�N
                if(l_dblElectedQuantity == 0 && (l_ipoOrders[i].isDecline()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01741,
                        this.getClass().getName() + STR_METHOD_NAME);                
                }                
                //�S�j�@@�`�F�b�N��̗v�f��List���폜
                int l_intIndex = l_orderList.indexOf(l_ipoOrders[i]);
                l_orderList.remove(l_intIndex);
            
                log.exiting(STR_METHOD_NAME);
                break;            
            }
        } 
        if(l_intFlag == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01995,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
    }
}
@
