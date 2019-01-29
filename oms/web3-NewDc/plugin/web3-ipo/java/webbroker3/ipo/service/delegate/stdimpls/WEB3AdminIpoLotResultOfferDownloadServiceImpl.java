head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultOfferDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʍw���\�����޳�۰�޻��޽����(WEB3AdminIpoLotResultOfferDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 �ė� (���u) �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>043
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>064,072
Revesion History : 2005/12/20 杊��] (���u) �d�l�ύXNo.104,No.105�C��
                 : 2006/11/10 �����q (���u) �d�l�ύXNo.162
                 : 2006/11/10 �����q (���u) �d�l�ύXNo.164
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3EnableIpoQuantityChangeDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ipo.WEB3AdminIpoLotBuyListCsv;
import webbroker3.ipo.WEB3AdminIpoLotBuyListFewCsv;
import webbroker3.ipo.WEB3AdminIpoLotResultOfferCounter;
import webbroker3.ipo.WEB3AdminIpoLotResultUploadCsv;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3IpoCsvDivDef;
import webbroker3.ipo.define.WEB3IpoKeyItemDef;
import webbroker3.ipo.define.WEB3LotResultDivDef;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferResponse;
import webbroker3.ipo.message.WEB3IPOLotResultUnit;
import webbroker3.ipo.message.WEB3IPOOfferConditionUnit;
import webbroker3.ipo.message.WEB3IPOSortKey;
import webbroker3.ipo.message.WEB3IPOUploadHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultOfferDownloadService;
import webbroker3.ipo.service.delegate.WEB3IpoUploadActionUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �Ǘ���IPO���I���ʍw���\�����޳�۰�޻��޽�����N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultOfferDownloadServiceImpl implements WEB3AdminIpoLotResultOfferDownloadService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoLotResultOfferDownloadServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE30041
     */
    public WEB3AdminIpoLotResultOfferDownloadServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ���IPO���I���ʍw���\���󋵃_�E�����[�h���������{����B<BR>
     * <BR>
     * �P�j�@@�Ɩ������ݒ�<BR>
     * �@@������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A<BR>
     * �Ǘ���IPO���I���ʍw���\�����޳�۰��ظ��Ă̏ꍇ<BR>
     * �@@�|get�_�E�����[�h���()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A<BR>
     * �Ǘ���IPO���I���ʍw���\����̧���޳�۰��ظ��Ă̏ꍇ<BR>
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A<BR>
     * �Ǘ���IPO���I���ʍw���\����ظ��Ă̏ꍇ<BR>
     * �@@�|get���I���ʍw���\����()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40E140D400D3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_genResponse = null;
        
        // Timestamp���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        // Timestamp�ݒ�
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_processTime);
        
        if (l_request instanceof WEB3AdminIPOLotResultOfferDownloadRequest)  //�Ǘ���IPO���I���ʍw���\�����޳�۰��ظ��Ă̏ꍇ
        {
            l_genResponse =  this.getDownloadScreen((WEB3AdminIPOLotResultOfferDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOLotResultOfferFileDownloadRequest)  //�Ǘ���IPO���I���ʍw���\����̧���޳�۰��ظ��Ă̏ꍇ
        {
            l_genResponse =  this.getDownloadFile((WEB3AdminIPOLotResultOfferFileDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOLotResultOfferRequest)  //�Ǘ���IPO���I���ʍw���\����ظ��Ă̏ꍇ
        {
            l_genResponse =  this.getLotResultOfferState((WEB3AdminIPOLotResultOfferRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOLotResultOfferListRequest)  //�Ǘ���IPO���I���ʍw���\���󋵈ꗗظ���
        {
            l_genResponse =  this.getLotResultOfferStateList((WEB3AdminIPOLotResultOfferListRequest)l_request);
        }
        else
        {
            // Timestamp���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // Timestamp���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        log.exiting(STR_METHOD_NAME);
        return l_genResponse;
    }
    
    /**
     * (get�_�E�����[�h���)<BR>
     * IPO�Ǘ���IPO���I���ʍw���\���󋵉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʍw���\���󋵂c�k�jget�_�E�����[�h��ʁv�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jget�_�E�����[�h��ʁv�j: <BR>
     *         1.7(*1) �iis���~() == true Or is�폜() == true Or is�u�b�N�r���f�B���O�J�n() == 
     * false�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00587<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jget�_�E�����[�h��ʁv�j: <BR>
     *         1.5(*1) �폜�����iis�폜����() == true�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00588<BR>
     * ==========================================================<BR>
     * @@param l_request - �Ǘ���IPO���I���ʍw���\�����޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadResponse
     * @@roseuid 40E140CC0160
     */
    protected WEB3AdminIPOLotResultOfferDownloadResponse getDownloadScreen(WEB3AdminIPOLotResultOfferDownloadRequest l_request)
                throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminIPOLotResultOfferDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //�Ǘ���IPO���I���ʍw���\�����޳�۰��ڽ��ݽ
        WEB3AdminIPOLotResultOfferDownloadResponse l_response = (WEB3AdminIPOLotResultOfferDownloadResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //IPO����
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is�폜����()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is�폜����() = " + l_blnDeletionProduct);        
        
            //1.5.(is�폜����() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDeletionProduct)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            }
            
            //1.6.is���~()
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            log.debug("is���~() = " + l_blnDiscontinuation); 
            
            //1.7.(is���~() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDiscontinuation)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.8.is�u�b�N�r���f�B���O�I��()
            boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
            log.debug("is�u�b�N�r���f�B���O�I��() = " + l_blnBookBuildingEnd);
            
            //1.9.(is�u�b�N�r���f�B���O�I��() == false)�̏ꍇ�A��O���X���[����B
            if(!l_blnBookBuildingEnd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            
            //1.10.is�V�K���I�I��()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();           
            
            //1.11.(is�V�K���I�I��() == false)�̏ꍇ�A��O���X���[����B          
            if(!l_blnNewLotteryEnd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //�L��IPO�\��
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl[] l_ipoOrders = l_ipoOrderManagerImpl.getOpenOrderUnits(l_ipoProduct.getProductId(), null);
                       
			if (l_ipoOrders == null){
				 log.exiting(STR_METHOD_NAME);
				 throw new WEB3BusinessLayerException(
				     WEB3ErrorCatalog.BUSINESS_ERROR_01037,
				     this.getClass().getName() + STR_METHOD_NAME);
			}
        
            //���I���ʁE�w���\���󋵏W�v���ʂ𐶐�
            WEB3AdminIpoLotResultOfferCounter l_ipoLotResultOfferCounter = new WEB3AdminIpoLotResultOfferCounter(l_ipoProduct);
            
            int l_intCount = l_ipoOrders.length;
            for (int i = 0; i < l_intCount; i++)
            {
                WEB3IpoOrderImpl l_ipoOrder = l_ipoOrders[i];
                this.calcLotResultOfferState(l_ipoLotResultOfferCounter, l_ipoOrder);
            }
            
            //�i���I�ҁjIPO�w���\���T�����ׂ𐶐�
            WEB3IPOOfferConditionUnit l_iopOfferConditionUnit1 = new WEB3IPOOfferConditionUnit();
                
            l_iopOfferConditionUnit1.offerQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerApplicationQuantity());  //�w���\������
            l_iopOfferConditionUnit1.offerNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerOfferNumber());  //�w���\������
            l_iopOfferConditionUnit1.declineQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDeclineQuantity());  //���ސ���
            l_iopOfferConditionUnit1.declineNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDeclineNumber());  //���ތ���
            l_iopOfferConditionUnit1.undecideQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerUndecideQuantity()); //���萔��
            l_iopOfferConditionUnit1.undecideNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerUndecideNumber());  //���茏��
            l_iopOfferConditionUnit1.totalQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerTotalQuantity());  //���v����
            l_iopOfferConditionUnit1.totalNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerTotalNumber());   //���v����
            l_iopOfferConditionUnit1.decisionQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDecisionQuantity());  //�w���m�萔��
            l_iopOfferConditionUnit1.decisionNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDecisionNumber());  //�w���m�茏��
            l_iopOfferConditionUnit1.declineRejectedQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDeclineRejectedQuantity());  //���ޗ��I����
            l_iopOfferConditionUnit1.declineRejectedNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDeclineRejectedNumber());  //���ޗ��I����
                
            //�i���I�ҁjIPO�w���\���T�����ׂ𐶐�
            WEB3IPOOfferConditionUnit l_iopOfferConditionUnit2 = new WEB3IPOOfferConditionUnit();
                
            l_iopOfferConditionUnit2.offerQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingApplicationQuantity());  //�w���\������
            l_iopOfferConditionUnit2.offerNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingOfferNumber());  //�w���\������
            l_iopOfferConditionUnit2.declineQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDeclineQuantity());  //���ސ���
            l_iopOfferConditionUnit2.declineNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDeclineNumber());  //���ތ���
            l_iopOfferConditionUnit2.undecideQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingUndecideQuantity()); //���萔��
            l_iopOfferConditionUnit2.undecideNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingUndecideNumber());  //���茏��
            l_iopOfferConditionUnit2.totalQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingTotalQuantity());  //���v����
            l_iopOfferConditionUnit2.totalNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingTotalNumber());   //���v����
            l_iopOfferConditionUnit2.decisionQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDecisionQuantity());  //�w���m�萔��
            l_iopOfferConditionUnit2.decisionNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDecisionNumber());  //�w���m�茏��
            l_iopOfferConditionUnit2.declineRejectedNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDeclineRejectedNumber());  //���ޗ��I����                
                
            //�i���I�ҁjIPO�w���\���T�����ׂ𐶐�
            WEB3IPOOfferConditionUnit l_iopOfferConditionUnit3 = new WEB3IPOOfferConditionUnit();
                
            l_iopOfferConditionUnit3.totalNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getRejectedNumber());   //���I����
            l_iopOfferConditionUnit3.declineRejectedNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getRejectedNumber());  //���I����
                
            //���I���ʃA�b�v���[�h�f�[�^���f���𐶐�
            WEB3AdminIpoLotResultUploadCsv l_iopLotResultUploadcsv = new WEB3AdminIpoLotResultUploadCsv(l_ipoProduct);
                
            //�ŐV�̃A�b�v���[�h�������擾
            AdministratorUploadParams l_uploadParams = (AdministratorUploadParams)l_iopLotResultUploadcsv.getLatestUploadAction(l_ipoProductId);
                
            //�A�b�v���[�h���𖾍ׂ��쐬
            WEB3IpoUploadActionUnitService l_service = (WEB3IpoUploadActionUnitService)Services.getService(WEB3IpoUploadActionUnitService.class);
            WEB3IPOUploadHistoryUnit l_ipoUploadActionUnit = l_service.createUploadActionUnit(l_uploadParams);
                
            //���X�|���X�f�[�^�𐶐�
            IpoProductRow l_ipoProductRow = (IpoProductRow)(l_ipoProduct.getDataSourceObject());
            l_response.productCode = l_ipoProductRow.getProductCode();  //�����R�[�h
            l_response.productName = l_ipoProductRow.getStandardName(); //������
            l_response.uploadHistory = l_ipoUploadActionUnit;  //�A�b�v���[�h����
            if (!l_ipoProductRow.getDealingQuantityIsNull())
            {
                l_response.handlingQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getDealingQuantity()); //���Ў戵����
            }
            l_response.allotQuantity 
                    = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getAllotQuantity());  //�����m�萔��
            l_response.advanceQuantity 
                    = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getAdvanceQuantity());  //�J��\����
            l_response.advanceWaitQuantity
                    = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getAdvanceWaitQuantity());  //�J��҂�����
            l_response.prizedOfferCondition = l_iopOfferConditionUnit1;  //���I�ҍw���\���T��
            l_response.waitingOfferCondition = l_iopOfferConditionUnit2;  //�⌇�ҍw���\���T��
            l_response.rejectedOfferCondition = l_iopOfferConditionUnit3;  //���I�ҍw���\���T��
            l_response.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();  //�\���p�P�ʋ敪
            
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * IPO�Ǘ��ҁE���I���ʍw���\���󋵃_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʍw���\���󋵂c�k�jget�_�E�����[�h�t�@@�C���v�Q�ƁB<BR>
     * @@param l_request - �Ǘ���IPO���I���ʍw���\����̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadResponse
     * @@roseuid 40E140CC018F
     */
    protected WEB3AdminIPOLotResultOfferFileDownloadResponse getDownloadFile(WEB3AdminIPOLotResultOfferFileDownloadRequest l_request)
                throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminIPOLotResultOfferFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //1.17.�Ǘ���IPO���I���ʍw���\����̧���޳�۰��ڽ��ݽ
        WEB3AdminIPOLotResultOfferFileDownloadResponse l_response = (WEB3AdminIPOLotResultOfferFileDownloadResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //1.3.IPO����
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is�폜����()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is�폜����() = " + l_blnDeletionProduct);        
        
            //1.5.(is�폜����() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDeletionProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            
            }
            
            //1.6.is���~()
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            log.debug("is���~() = " + l_blnDiscontinuation); 
            
            //1.7.(is���~() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            
            //1.8.is�u�b�N�r���f�B���O�I��()
            boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
            log.debug("is�u�b�N�r���f�B���O�I��() = " + l_blnBookBuildingEnd);
            
            //1.9.(is�u�b�N�r���f�B���O�I��() == false)�̏ꍇ�A��O���X���[����B
            if(!l_blnBookBuildingEnd)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            } 
            
            //1.10.is�V�K���I�I��()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();           
            
            //1.11.(is�V�K���I�I��() == false)�̏ꍇ�A��O���X���[����B          
            if(!l_blnNewLotteryEnd)
            {

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }

            // getDataSourceObject()
            l_ipoProduct.getDataSourceObject();

            // get�_�E�����[�h�f�[�^(IPO����, IPO�\�[�g�L�[[])
            WEB3IpoOrderImpl[] l_ipoOrders = this.getDownloadData(l_ipoProduct, null);

            // ���N�G�X�g�f�[�^�FCSV�敪 = "1"�i�ǉ����ڂ���j �̏ꍇ�A�ȉ��̏��������s
            if (WEB3IpoCsvDivDef.ADD_ITEM_ON.equals(l_request.csvDiv))
            {
                // ���I���ʁE�w���\����CSV(IPO����)
                WEB3AdminIpoLotBuyListCsv l_ipoLotResultOfferCsv =
                    new WEB3AdminIpoLotBuyListCsv(l_ipoProduct);

                // create�w���\��CSV���
                String[] l_strIpoLotBuyListInfo =
                    this.creatIpoLotBuyListInfo(l_ipoOrders, l_ipoLotResultOfferCsv);

                // ���N�G�X�g�f�[�^�FCSV�敪 = "1"�i�ǉ����ڂ���j �̏ꍇ,create�w���\��CSV���()�̖߂�l
                l_response.downloadFile = l_strIpoLotBuyListInfo;
            }

            // ���N�G�X�g�f�[�^�FCSV�敪 = "0"�i�ǉ����ڂȂ��j �̏ꍇ�A�ȉ��̏��������s
            else if (WEB3IpoCsvDivDef.ADD_ITEM_OFF.equals(l_request.csvDiv))
            {
                // ���I���ʁE�w���\����FewCSV(IPO����)
                WEB3AdminIpoLotBuyListFewCsv l_ipoLotBuyListFewCsv =
                    new WEB3AdminIpoLotBuyListFewCsv(l_ipoProduct);

                // create�w���\��FewCSV���
                String[] l_strIpoLotBuyListFewInfo =
                    this.creatIpoLotBuyListFewInfo(l_ipoOrders, l_ipoLotBuyListFewCsv);

                // ���N�G�X�g�f�[�^�FCSV�敪 = "0"�i�ǉ����ڂȂ��j �̏ꍇ,create�w���\��FewCSV���()�̖߂�l
                l_response.downloadFile = l_strIpoLotBuyListFewInfo;
            }

            // ���X�|���X�f�[�^.���ݓ����F �iTradingSystem.getSystemTimestamp()�j�̖߂�l
            l_response.currentDate = l_finApp.getTradingSystem().getSystemTimestamp();
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���I���ʍw���\����)<BR>
     * IPO���I���ʁE�w���\���󋵃f�[�^�擾�������s���B<BR>
     * �i�Ǘ���IPO���I���ʁE�w���\���󋵉�ʂɕ\���j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʍw���\���󋵂c�k�jget���I���ʍw���\���󋵁v�Q�ƁB<BR>
     * @@param l_request - �Ǘ���IPO���I���ʍw���\����ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferResponse
     * @@roseuid 40E140CC017F
     */
    protected WEB3AdminIPOLotResultOfferResponse getLotResultOfferState(WEB3AdminIPOLotResultOfferRequest l_request)
                throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getLotResultOfferState(WEB3AdminIPOLotResultOfferRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);

        //1.20.�Ǘ���IPO���I���ʍw���\����ڽ��ݽ
        WEB3AdminIPOLotResultOfferResponse l_response = (WEB3AdminIPOLotResultOfferResponse)l_request.createResponse();
        
        try
        {
            //1.3.IPO����
            long l_ipoProductId = Long.parseLong(l_request.id);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IpoProductManagerImpl l_ipoProductManager 
                        = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct 
                        = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is�폜����()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is�폜����() = " + l_blnDeletionProduct);        
        
            //1.5.(is�폜����() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDeletionProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            
            }
            
            //1.6.is���~()
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            log.debug("is���~() = " + l_blnDiscontinuation); 
            
            //1.7.(is���~() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            
            //1.8.is�u�b�N�r���f�B���O�I��()
            boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
            log.debug("is�u�b�N�r���f�B���O�I��() = " + l_blnBookBuildingEnd);
            
            //1.9.(is�u�b�N�r���f�B���O�I��() == false)�̏ꍇ�A��O���X���[����B
            if(!l_blnBookBuildingEnd)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            } 
            
            //1.10.is�V�K���I�I��()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();           
            
            //1.11.(is�V�K���I�I��() == false)�̏ꍇ�A��O���X���[����B          
            if(!l_blnNewLotteryEnd)
            {

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }        
               
            //1.12.�،���ЃR�[�h���擾
            String l_strInstitutionCode = l_administartor.getInstitutionCode();
            
            //1.13.���X�R�[�h���擾
            String l_branchCode = l_request.branchCode;
//            String l_branchCode = l_administartor.getBranchCode();
            
            //1.14.get�ڋq()
            WEB3GentradeAccountManager l_genAccountMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            MainAccount l_mainAccount = 
                    l_genAccountMgr.getMainAccount(
                                        l_strInstitutionCode,
                                        l_branchCode,
                                        l_request.accountCode); 
                        
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
			//1.15.�ڋq�\�������擾
			// 2004/12/10 ��Q�Ǘ��[No.U00545 �ڋq���̒l���u�ڋq�̖��O(�c��)�{ "�@@" �{���O(���O)�v�ɂȂ�悤�ɏC���B���@@SRA START 
			String l_strName = l_mainAccountRow.getFamilyName();
//			  String l_strName = l_mainAccountRow.getFamilyName() + " " + l_mainAccountRow.getGivenName();
			// 2004/12/10 ��Q�Ǘ��[No.U00545 �ڋq���̒l���u�ڋq�̖��O(�c��)�{ "�@@" �{���O(���O)�v�ɂȂ�悤�ɏC���B���@@SRA END
            
            //1.16.�⏕�������擾
            SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //1.17.�w��ڋq�^�w��IPO������IPO�\���I�u�W�F�N�g
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl l_ipoOrder = l_ipoOrderManagerImpl.getOrderUnit(l_subAccount, l_ipoProductId);        
        
			if (l_ipoOrder == null){
				 
				log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME);
			}
        
            //1.18.(*5)�V�K�\���������ޯ�����ިݸޏI���O�łȂ��ꍇ�͗�O���X���[����
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            IpoProductRow l_ipoProductRow = (IpoProductRow) l_ipoProduct.getDataSourceObject();
            Timestamp l_ipoOrderDate = l_ipoOrderRow.getBookbuildingCreatedTimestamp();
            Timestamp l_ipoProductBBEnd = l_ipoProductRow.getBookbuildingEndDatetime();
            if(WEB3DateUtility.compareToMinute(l_ipoOrderDate,l_ipoProductBBEnd)>=0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01745,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            
            //1.19.(*6)����ς݂�IPO�\���̏ꍇ�͗�O���X���[����
            if(l_ipoOrder.getOrderStatus().equals(OrderStatusEnum.CANCELLED))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01746,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            
            //1.21.���X�|���X�f�[�^�𐶐�
            l_response.traderCode = l_mainAccountRow.getSonarTraderCode();  //���҃R�[�h
            l_response.accountName = l_strName;  //�ڋq��


			//���J���i 20060120�C��
			if (l_ipoProductRow.getPublicPriceIsNull())
			{
				l_response.publicOfferingPrice = null;
			}
			else
			{
				l_response.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());
			}
            //l_response.publicOfferingPrice = 
            //    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());  //���J���i
            
            // �M�p�敪�@@2006.04.27 SCS���� �ǉ�************************************************************
            WEB3GentradeMainAccount l_genMainAccount = 
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
                
            if(l_genMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)){
                
                l_response.marginDiv = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN;
            }
            else{
                l_response.marginDiv = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN;
            }
            // ���I�ԍ��@@2006.04.27 SCS����
            l_response.lotNumber = l_ipoOrderRow.getLotNumber();
            // *************************************** �C���@@end ******************************************

            String l_strLotResult = l_ipoOrderRow.getLotResult();  //���I����
            String l_strLotResultRetry = l_ipoOrderRow.getLotResultRetry();  //���I���ʁi�J��j
            
            //���I���ʋ敪
            if (WEB3LotResultDef.ELECTION.equalsIgnoreCase(l_strLotResult))
            {
                l_response.lotResultDiv = WEB3LotResultDef.ELECTION;  //���I
            }
            else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.ELECTION.equals(l_strLotResultRetry))
            {
                l_response.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT_ELECTION;  //�⌇���I
            }
            else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.DEFEAT.equals(l_strLotResultRetry))
            {
                l_response.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT_DEFEAT;  //�⌇���I
            }
            else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.DEFAULT.equals(l_strLotResultRetry))
            {
                l_response.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT;  //�⌇
            }
            else if (WEB3LotResultDef.DEFEAT.equalsIgnoreCase(l_strLotResult))
            {
                l_response.lotResultDiv = WEB3LotResultDivDef.DEFEAT;  //���I
            }
            
            if (!l_ipoOrderRow.getElectedQuantityIsNull())
            {
                l_response.prizeQuantity = WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getElectedQuantity());  //���I����
            }
            if (!l_ipoOrderRow.getApplicationQuantityIsNull())
            {
                l_response.offerQuantity = WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getApplicationQuantity());  //�w���\������
            }

            l_response.offerCancelDate = l_ipoOrderRow.getOfferingTimestamp();  //�w���\�����ޓ���
            
            String l_strOfferDiv = l_ipoOrderRow.getOfferingDiv();  //�w���\���敪
            if (WEB3OfferingDivDef.DEFAULT.equals(l_strOfferDiv))
            {
                l_response.offerStateDiv = null;
            }
            else
            {
                l_response.offerStateDiv = l_strOfferDiv;
            }
            
            l_response.receiveStateDiv = l_ipoOrderRow.getAcceptStatus();  //��t��ԋ敪
            if (!l_ipoOrderRow.getSubstitutePriorityIsNull())
            {
                l_response.priority = WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getSubstitutePriority());  //�D�揇��
            }
            
            //IPO�w���\�����ʕύX
            Institution l_institution = l_administartor.getInstitution();
            InstitutionRow l_institutionRow = (InstitutionRow)(l_institution.getDataSourceObject());
            String l_strEnableIpoQuantityChange = l_institutionRow.getEnableIpoQuantityChange();
            
            //�w���\�����ʕύX�\�t���O
            if (WEB3EnableIpoQuantityChangeDef.CAN_CHANGE.equals(l_strEnableIpoQuantityChange))
            {
                l_response.offerQuantityFlag = true;
            }
            else
            {
                l_response.offerQuantityFlag = false;
            }
            
            l_response.displayUnitDiv = ((IpoProductRow)(l_ipoOrder.getProduct().getDataSourceObject())).getIpoUnitDiv();  //�\���p�P�ʋ敪
            
            
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���I���ʍw���\���󋵈ꗗ)<BR>
     * IPO���I���ʁE�w���\���󋵈ꗗ�f�[�^�擾�������s���B<BR>
     * �i�Ǘ���IPO���I���ʁE�w���\���󋵈ꗗ��ʂɕ\���j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʍw���\���󋵂c�k�jget���I���ʍw���\���󋵈ꗗ�v�Q�ƁB<BR>
     * @@param l_request - �Ǘ���IPO���I���ʍw���\���󋵈ꗗظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListResponse
     * @@roseuid 40EE069802BF
     */
    protected WEB3AdminIPOLotResultOfferListResponse getLotResultOfferStateList(WEB3AdminIPOLotResultOfferListRequest l_request)
                throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getLotResultOfferStateList(WEB3AdminIPOLotResultOfferListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        //�،���ЃI�u�W�F�N�g���擾
        Institution l_institution = l_administartor.getInstitution();
        
        //�Ǘ���IPO���I���ʍw���\���󋵈ꗗڽ��ݽ
        WEB3AdminIPOLotResultOfferListResponse l_response = (WEB3AdminIPOLotResultOfferListResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //IPO����
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is�폜����()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is�폜����() = " + l_blnDeletionProduct);        
        
            //1.5.(is�폜����() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDeletionProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            
            }
            
            //1.6.is���~()
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            log.debug("is���~() = " + l_blnDiscontinuation); 
            
            //1.7.(is���~() == true)�̏ꍇ�A��O���X���[����B
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            
            //1.8.is�u�b�N�r���f�B���O�I��()
            boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
            log.debug("is�u�b�N�r���f�B���O�I��() = " + l_blnBookBuildingEnd);
            
            //1.9.(is�u�b�N�r���f�B���O�I��() == false)�̏ꍇ�A��O���X���[����B
            if(!l_blnBookBuildingEnd)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            } 
            
            //1.10.is�V�K���I�I��()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();           
            
            //1.11.(is�V�K���I�I��() == false)�̏ꍇ�A��O���X���[����B          
            if(!l_blnNewLotteryEnd)
            {

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }           
            
            //���I���ʁE�w���\���ꗗ�_�E�����[�h�t�@@�C���f�[�^���擾
            WEB3IpoOrderImpl[] l_ipoOrders = this.getDownloadData(l_ipoProduct, l_request.sortKeys);           
            if (!WEB3StringTypeUtility.isNumber(l_request.pageSize) || !WEB3StringTypeUtility.isNumber(l_request.pageIndex))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
			//ArrayList�𐶐�
			ArrayList l_lstIopLotResultUnit = new ArrayList();
			for (int i = 0; i < l_ipoOrders.length; i++)
			{
				WEB3IpoOrderImpl l_ipoOrder = l_ipoOrders[i];
                
				//IPO�\���s�I�u�W�F�N�g���擾
				IpoOrderRow l_ipoOrderRow = (IpoOrderRow)(l_ipoOrder.getDataSourceObject());
                
				//���I���ʖ��׃��b�Z�[�W�f�[�^�I�u�W�F�N�g�𐶐�
				WEB3IPOLotResultUnit l_iopLotResultUnit = new WEB3IPOLotResultUnit();
                
				long l_lnBranchId = l_ipoOrder.getBranchId();  //���X�h�c���擾
				long l_lnAccountId = l_ipoOrder.getAccountId();  //�����h�c���擾
                
				Branch l_branch = l_finApp.getAccountManager().getBranch(l_lnBranchId);  //throws NotFoundException
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_lnAccountId);  //throws NotFoundException
                
				MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
                
				//���I���ʖ��׃��b�Z�[�W�f�[�^�v���p�e�B�ɒl���Z�b�g
				l_iopLotResultUnit.branchCode = l_branch.getBranchCode();  //���X�R�[�h
                l_iopLotResultUnit.traderCode = l_mainAccountRow.getSonarTraderCode();  //���҃R�[�h
				l_iopLotResultUnit.accountCode = l_mainAccount.getDisplayAccountCode();  //�\���ڋq�R�[�h
//              2005/01/22 ��Q�Ǘ��[No.U00545 �ɂ��킹�ďC���B���@@SRA START
				l_iopLotResultUnit.accountName = l_mainAccountRow.getFamilyName() ;  //�ڋq��
//                l_iopLotResultUnit.accountName = l_mainAccountRow.getFamilyName() + " " + l_mainAccountRow.getGivenName();  //�ڋq��
                IpoProductRow l_ipoProductRow = (IpoProductRow) l_ipoProduct.getDataSourceObject();

				//���J���i 20060120�C��
				if (l_ipoProductRow.getPublicPriceIsNull())
				{
					l_iopLotResultUnit.publicOfferingPrice = null;
				}
				else
				{
					l_iopLotResultUnit.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());
				}
                //l_iopLotResultUnit.publicOfferingPrice = 
                //    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());  //���J���i

                // �M�p�敪�@@2006.04.27 SCS���� �ǉ�************************************************************
                if(l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)){
                
                    l_iopLotResultUnit.marginDiv = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN;
                }
                else{
                    l_iopLotResultUnit.marginDiv = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN;
                }
                // ���I�ԍ��@@2006.04.27 SCS����
                l_iopLotResultUnit.lotNumber = l_ipoOrderRow.getLotNumber();
                // *************************************** �C���@@end ******************************************

//              2005/01/22 ��Q�Ǘ��[No.U00545 �ɂ��킹�ďC���B���@@SRA 
				String l_strLotResult = l_ipoOrderRow.getLotResult();  //���I����
				String l_strLotResultRetry = l_ipoOrderRow.getLotResultRetry();  //���I���ʁi�J��j
                
				//���I���ʋ敪
				if (WEB3LotResultDef.ELECTION.equalsIgnoreCase(l_strLotResult))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDef.ELECTION;  //���I
				}
				else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.ELECTION.equals(l_strLotResultRetry))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT_ELECTION;  //�⌇���I
				}
				else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.DEFEAT.equals(l_strLotResultRetry))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT_DEFEAT;  //�⌇���I
				}
				else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.DEFAULT.equals(l_strLotResultRetry))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT;  //�⌇
				}
				else if (WEB3LotResultDef.DEFEAT.equalsIgnoreCase(l_strLotResult))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDivDef.DEFEAT;  //���I
				}
				if (!l_ipoOrderRow.getElectedQuantityIsNull())
				{
					l_iopLotResultUnit.prizeQuantity 
							= WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getElectedQuantity());  //���I����
				}
				if (!l_ipoOrderRow.getApplicationQuantityIsNull())
				{
					l_iopLotResultUnit.offerQuantity 
							= WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getApplicationQuantity());  //�w���\������
				}

				l_iopLotResultUnit.offerCancelDate = l_ipoOrderRow.getOfferingTimestamp();  //�w���\�����ޓ���
                
				String l_strOfferDiv = l_ipoOrderRow.getOfferingDiv();  //�w���\���敪
				if (WEB3OfferingDivDef.DEFAULT.equals(l_strOfferDiv))
				{
					l_iopLotResultUnit.offerStateDiv = null;
				}
				else
				{
					l_iopLotResultUnit.offerStateDiv = l_strOfferDiv;
				}
                
				l_iopLotResultUnit.receiveStateDiv = l_ipoOrderRow.getAcceptStatus();  //��t��ԋ敪
				if (!l_ipoOrderRow.getSubstitutePriorityIsNull())
				{
					l_iopLotResultUnit.priority = WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getSubstitutePriority());  //�D�揇��
				}
                
				//ArrayList�ɒ��I���ʖ��ׂ�ǉ�
				l_lstIopLotResultUnit.add(l_iopLotResultUnit);
			}
            
			//���I���ʖ���List��z��ɕϊ�
			WEB3IPOLotResultUnit[] l_ipoLotResultUnits = new WEB3IPOLotResultUnit[l_lstIopLotResultUnit.size()];
			l_lstIopLotResultUnit.toArray(l_ipoLotResultUnits);
            
			//�y�[�W���߂��鏈���N���X���g�p
			int l_intpageIndex = Integer.parseInt(l_request.pageIndex);
			int l_intpageSize  = Integer.parseInt(l_request.pageSize);
			WEB3PageIndexInfo l_pageInfo = new WEB3PageIndexInfo(l_lstIopLotResultUnit,l_intpageIndex,l_intpageSize);
                
			//���X�|���X�f�[�^�𐶐�
			IpoProductRow l_ipoProductRow = (IpoProductRow)(l_ipoProduct.getDataSourceObject());
                
			l_response.productCode = l_ipoProductRow.getProductCode();  //�����R�[�h
			l_response.productName = l_ipoProductRow.getStandardName();  //������
			l_response.lotResultList = (WEB3IPOLotResultUnit[]) l_pageInfo.getArrayReturned(WEB3IPOLotResultUnit.class);
//			  l_response.lotResultList = l_ipoLotResultUnits;  //���I���ʈꗗ
			l_response.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();  //�\���p�P�ʋ敪
            
			//IPO�w���\�����ʕύX
			InstitutionRow l_institutionRow = (InstitutionRow)(l_institution.getDataSourceObject());
			String l_strEnableIpoQuantityChange = l_institutionRow.getEnableIpoQuantityChange();
            
			//�w���\�����ʕύX�\�t���O
			if (WEB3EnableIpoQuantityChangeDef.CAN_CHANGE.equals(l_strEnableIpoQuantityChange))
			{
				l_response.offerQuantityFlag = true;
			}
			else
			{
				l_response.offerQuantityFlag = false;
			}
			l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_pageInfo.getTotalPages());
			l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_pageInfo.getTotalRecords());
			l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pageInfo.getPageIndex());
            
		}
		catch (NotFoundException l_nfex)
		{
			log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
		}
        
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
    
    /**
     * (get�_�E�����[�h�f�[�^)<BR>
     * �����Ώۂ̃f�[�^���擾���Asort���ĕԋp����B<BR>
     * <BR>
     * �P�j�@@�f�[�^�擾<BR>
     * �@@������IPO�����ɊY������IPO�\���I�u�W�F�N�g�ŗL���Ȃ��̂��擾����B<BR>
     * <BR>
     * �@@[get�L��IPO�\��()�Ɏw�肷�����]<BR>
     * �@@IPO�����h�c�F�@@IPO����.getIPO�����h�c()<BR>
     * <BR>
     * �Q�j�@@sort<BR>
     * �@@�P�j�Ŏ擾����IPO�\���̔z����A<BR>
     * WEB3ArraysUtility.sort()�ɂ�sort���ԋp����B<BR>
     * <BR>
     * �@@�� �\�[�g�L�[�w��Ȃ��i����.IPO�\�[�g�L�[ == null�j�̏ꍇ<BR>
     * <BR>
     * �@@[sort�Ɏw�肷�����]<BR>
     * �@@Object[]�F�@@�P�j�Ŏ擾����IPO�\���̔z��<BR>
     * �@@Comparator[]�F�@@<BR>
     * �@@�@@com[0] = new IPO�\��.���I����Comparator(�����i�Fasc�j)<BR>
     * �@@�@@com[1] = new IPO�\��.���I���ʁi�J��jComparator(�����i�Fasc�j)<BR>
     * �@@�@@com[2] = new IPO�\��.�w���\���敪Comparator(�����i�Fasc�j)<BR>
     * �@@�@@com[3] = new IPO�\��.�D�揇��Comparator(�����i�Fasc�j)<BR>
     * �@@�@@com[4] = new IPO�\��.�ڋq�R�[�hComparator(�����i�Fasc�j)<BR>
     * <BR>
     * �@@�� �\�[�g�L�[�w��i����.IPO�\�[�g�L�[ != null�j�̏ꍇ<BR>
     * <BR>
     * �@@[sort�Ɏw�肷�����]<BR>
     * �@@Object[]�F�@@�P�j�Ŏ擾����IPO�\���̔z��<BR>
     * �@@Comparator[]�F�@@<BR>
     * �@@�@@���@@����.IPO�\�[�g�L�[[]�̔z�񏇏��ŁA<BR>
     * �w��̒ʂ�Comparator�𐶐����Ďw�肷��B<BR>
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.���X�R�[�h�j�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.���X�R�[�hComparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�ڋq�R�[�h�j�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.�ڋq�R�[�hComparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.���I���ʋ敪�j�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.���I����Comparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * �@@�@@�@@new IPO�\��.���I���ʁi�J��jComparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.���I���ʁj�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.���I����Comparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�w���\�����ʁj�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.�w���\������Comparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�w���\���^���ޓ����j�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.�w���\���^���ޓ���Comparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�w���\���󋵋敪�j�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.�w���\���敪Comparator(IPO�\�[�g�L�[.�����^�~��)
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.��t��ԋ敪�j�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.��t���Comparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * <BR>
     * �@@�@@�|�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�D�揇�ʁj�̏ꍇ<BR>
     * �@@�@@�@@new IPO�\��.�D�揇��Comparator(IPO�\�[�g�L�[.�����^�~��)<BR>
     * <BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@param l_ipoSortKeys - IPO�\�[�g�L�[
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40ECD557008E
     */
    protected WEB3IpoOrderImpl[] getDownloadData(WEB3IpoProductImpl l_ipoProduct, WEB3IPOSortKey[] l_ipoSortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDownloadData(WEB3IpoProductImpl, WEB3IPOSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        String sortCond = "";
        
        if (l_ipoSortKeys == null)
        {
//            Comparator[] l_comparator = new Comparator[5];
//            //IPO�\��.���I����Comparator(�����i�Fasc�j)
//            l_comparator[0] = new WEB3IpoOrderLotResultComparator(WEB3AscDescDef.ASC);  
//            //IPO�\��.���I���ʁi�J��jComparator(�����i�Fasc�j)
//            l_comparator[1] = new WEB3IpoOrderLotResultRetryComparator(WEB3AscDescDef.ASC);  
//            //IPO�\��.�w���\���敪Comparator(�����i�Fasc�j)
//            l_comparator[2] = new WEB3IpoOrderOfferingDivComparator(WEB3AscDescDef.ASC);  
//            //IPO�\��.�D�揇��Comparator(�����i�Fasc�j)
//            l_comparator[3] = new WEB3IpoOrderSubstitutePriorityComparator(WEB3AscDescDef.ASC);
//            //IPO�\��.�ڋq�R�[�hComparator(�����i�Fasc�j)
//            l_comparator[4] = new WEB3IpoOrderAccountCodeComparator(WEB3AscDescDef.ASC);
//            WEB3ArraysUtility.sort(l_ipoOrders, l_comparator);
            
            sortCond = " lot_result, lot_result_retry, offering_div, substitute_priority asc ";
        }
        else if (l_ipoSortKeys != null)
        {
            int l_intCount = l_ipoSortKeys.length;
            int l_lastIndex = l_intCount - 1;
            //ArrayList l_comparators = new ArrayList();
            
            for (int i = 0; i < l_intCount; i++)
            {
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.���X�R�[�h�j�̏ꍇ
                if (WEB3IpoKeyItemDef.BRANCH_CODE.equals(l_ipoSortKeys[i].keyItem))
                {
                    //l_comparators.add(new WEB3IpoOrderBranchCodeComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "BRANCH_ID ASC";
                    } else 
                    {
                        sortCond = sortCond + "BRANCH_ID DESC";
                    }
                }
                
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�ڋq�R�[�h�j�̏ꍇ
                if (WEB3IpoKeyItemDef.ACCOUNT_CODE.equals(l_ipoSortKeys[i].keyItem))
                {
                    //l_comparators.add(new WEB3IpoOrderAccountCodeComparator(l_ipoSortKeys[i].ascDesc));
                    continue;
                }
                
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.���I���ʋ敪�j�̏ꍇ
                if (WEB3IpoKeyItemDef.LOT_RESULT_DIV.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderLotResultComparator(l_ipoSortKeys[i].ascDesc));
                    //comparators.add(new WEB3IpoOrderLotResultRetryComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "LOT_RESULT ASC, LOT_RESULT_RETRY ASC";
                    } else 
                    {
                        sortCond = sortCond + "LOT_RESULT DESC, LOT_RESULT_RETRY DESC";
                    }
                }
                
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.���I���ʁj�̏ꍇ
                if (WEB3IpoKeyItemDef.PRIZE_QUANTITY.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderElectedQuantityComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "ELECTED_QUANTITY ASC";
                    } else 
                    {
                        sortCond = sortCond + "ELECTED_QUANTITY DESC";
                    }
                }
                
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�w���\�����ʁj�̏ꍇ
                if (WEB3IpoKeyItemDef.OFFER_QUANTITY.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderApplicationQuantityComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "APPLICATION_QUANTITY ASC";
                    } else 
                    {
                        sortCond = sortCond + "APPLICATION_QUANTITY DESC";
                    }
                }
                
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�w���\���^���ޓ����j�̏ꍇ
                if (WEB3IpoKeyItemDef.OFFER_CANCEL_DATE.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderOfferingTimestampComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "OFFERING_TIMESTAMP ASC";
                    } else 
                    {
                        sortCond = sortCond + "OFFERING_TIMESTAMP DESC";
                    }
                }
                
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�w���\���󋵋敪�j�̏ꍇ
                if (WEB3IpoKeyItemDef.OFFER_STATE_DIV.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderOfferingDivComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "OFFERING_DIV ASC";
                    } else 
                    {
                        sortCond = sortCond + "OFFERING_DIV DESC";
                    }
                }
                
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.��t��ԋ敪�j�̏ꍇ
                if (WEB3IpoKeyItemDef.RECEIVE_STATE_DIV.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderAcceptStatusComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "ACCEPT_STATUS ASC";
                    } else 
                    {
                        sortCond = sortCond + "ACCEPT_STATUS DESC";
                    }
                }
                
                //�iIPO�\�[�g�L�[.�L�[���� == IPO���I���ʖ���.�D�揇�ʁj�̏ꍇ
                if (WEB3IpoKeyItemDef.PRIORITY.equals(l_ipoSortKeys[i].keyItem))
                {
                    //l_comparators.add(new WEB3IpoOrderSubstitutePriorityComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "SUBSTITUTE_PRIORITY ASC";
                    } else 
                    {
                        sortCond = sortCond + "SUBSTITUTE_PRIORITY DESC";
                    }
                }
            }
        }
        //�f�[�^�擾
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoOrderImpl[] l_ipoOrders = l_ipoOrderManagerImpl.getOpenOrderUnits(
            l_ipoProduct.getProductId(), sortCond
            );
        
		if (l_ipoOrders == null){
			 log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
		}
        
//            Comparator[] l_comparator = new Comparator[l_comparators.size()];
//            l_comparators.toArray(l_comparator);
//            
//            WEB3ArraysUtility.sort(l_ipoOrders, l_comparator);
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoOrders;
    }
    
    /**
     * (calc���I���ʍw���\����)<BR>
     * �Ώ�IPO�\���I�u�W�F�N�g�ɂ��āA���I���ʁ^�w���\�����ʁA���������Z����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʍw���\���󋵂c�k�jcalc���I���ʍw���\���󋵁v�Q�ƁB<BR>
     * @@param l_lotResultOfferCounter - ���I���ʁE�w���\���󋵏W�v���ʃI�u�W�F�N�g
     * @@param l_ipoOrder - IPO�\���I�u�W�F�N�g
     * @@roseuid 40EBA9010118
     */
    protected void calcLotResultOfferState(WEB3AdminIpoLotResultOfferCounter l_lotResultOfferCounter, WEB3IpoOrderImpl l_ipoOrder) 
    {
        final String STR_METHOD_NAME = " calcLotResultOfferState(WEB3AdminIpoLotResultOfferCounter, WEB3IpoOrderImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lotResultOfferCounter == null || l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�s�I�u�W�F�N�g���擾
        IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
        
        if (l_ipoOrder.isElected())
        {
            //���I�ҍw���\�����ʂ����Z
            if (l_ipoOrder.isOffered())
            {
                l_lotResultOfferCounter.addPrizerApplicationQuantity(l_ipoOrderRow.getApplicationQuantity());
            }
            
            //���I�Ҏ��ސ��ʂ����Z
            if (l_ipoOrder.isDecline())
            {
                l_lotResultOfferCounter.addPrizerDeclineQuantity(l_ipoOrderRow.getElectedQuantity());
            }
            
            //���I�҂ŁA�w���\���^���ރI�y���[�V�������ς̐��ʂ����Z
            if (!l_ipoOrder.isOffered() && !l_ipoOrder.isDecline())
            {
                l_lotResultOfferCounter.addPrizerUndecideQuantity(l_ipoOrderRow.getElectedQuantity());
            }
        }
        
        if (l_ipoOrder.isWaiting())
        {
            //�⌇�ҍw���\�����ʂ����Z
            if (l_ipoOrder.isOffered())
            {
                l_lotResultOfferCounter.addWaitingApplicationQuantity(l_ipoOrderRow.getApplicationQuantity());
            }
            
            //�⌇�Ҏ��ސ��ʂ����Z
            if (l_ipoOrder.isDecline())
            {
                l_lotResultOfferCounter.addWaitingDeclineQuantity(l_ipoOrderRow.getElectedQuantity());
            }
            
            //�⌇�҂ŁA�w���\���^���ރI�y���[�V�������ς̐��ʂ����Z
            if (!l_ipoOrder.isOffered() && !l_ipoOrder.isDecline())
            {
                l_lotResultOfferCounter.addWaitingUndecideQuantity(l_ipoOrderRow.getElectedQuantity());
            }
            
            //�⌇���I���ʂ����Z
            if (WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderRow.getLotResultRetry()) && l_ipoOrder.isOffered())
            {
                l_lotResultOfferCounter.addWaitingPrizeQuantity(l_ipoOrderRow.getElectedQuantity());
            }
            
            //�⌇���I�������C���N�������g
            if (WEB3LotResultRetryDef.DEFEAT.equals(l_ipoOrderRow.getLotResultRetry()))
            {
                l_lotResultOfferCounter.addWaitingRejectedNumber();
            }
            
            //�⌇���I�������C���N�������g
            if (WEB3LotResultRetryDef.DEFAULT.equals(l_ipoOrderRow.getLotResultRetry()))
            {
                if (!l_ipoOrder.isDecline() && !l_ipoOrder.isOffered() 
                        && ((WEB3IpoProductImpl)l_ipoOrder.getProduct()).isOfferEnd())
                {
                    l_lotResultOfferCounter.addWaitingRejectedNumber();
                }
            }
        }
        
        //���I�������C���N�������g
        if (l_ipoOrder.isRejected())
        {
            l_lotResultOfferCounter.addRejectedNumber();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�w���\��CSV���)<BR>
     * ���I���ʁE�w���\����CSV���𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}�F�uIPO�i�Ǘ��ҁE���I���ʍw���\����DL�jcreate�w���\��CSV���v�Q��<BR>
     * <BR>
     * @@param l_ipoOrder - (IPO�\��[])<BR>
     * IPO�\���s[]
     * @@param l_csv - (���I���ʁE�w���\����CSV)<BR>
     * ���I���ʁE�w���\����CSV�I�u�W�F�N�g
     * @@return String[]
     * @@throws WEB3BaseException 
     */
    protected String[] creatIpoLotBuyListInfo(
        WEB3IpoOrderImpl[] l_ipoOrder,
        WEB3AdminIpoLotBuyListCsv l_csv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "creatIpoLotBuyListInfo(WEB3IpoOrderImpl[], WEB3AdminIpoLotBuyListCsv)";
        log.entering(STR_METHOD_NAME);

        if (l_ipoOrder == null || l_csv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �����GIPO�\��[]�e�v�f����LOOP����
        int l_intIpoOrder = l_ipoOrder.length;
        for (int i = 0; i < l_intIpoOrder; i++)
        {
            // add���׍s( )
            int l_intAddRow = l_csv.addRow();

            // get���XID( )
            long l_lngBranchId = l_ipoOrder[i].getBranchId();

            // set���X�R�[�h(int, long)
            l_csv.setBranchCode(i, l_lngBranchId);
            try
            {
                // get����ID
                long l_lngAccountId = l_ipoOrder[i].getAccountId();

                // set�M�p�敪
                // [set�M�p�敪()�Ɏw�肷�����]
                // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
                // �����h�c�F�@@IPO�\��[index].get�����h�c()
                l_csv.setMarginDiv(l_intAddRow, l_lngAccountId);

                // set���҃R�[�h
                // [set���҃R�[�h()�Ɏw�肷�����]
                // �s�ԍ��F�@@�iadd���׍s()�̖߂�l)
                // �����h�c�F�@@IPO�\��[index].get�����h�c()
                l_csv.setTradeCode(l_intAddRow, l_lngAccountId);

                // set�ڋq
                // [set�ڋq()�Ɏw�肷�����]
                // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
                // �����h�c�F�@@IPO�\��[index].get�����h�c()
                l_csv.setAccount(i, l_lngAccountId);
            }
            // �ڋq�I�u�W�F�N�g�擾�Ɏ��s�����ꍇ
            // �ڋq�I�u�W�F�N�g�擾�Ɏ��s�����ꍇ(set�ڋq�ɂė�O������)
            // �A�Y�c�ڋq�̖��׍s�͍쐬���Ȃ��B
            catch (WEB3BaseException l_ex)
            {
                // delete���׍s(�s�ԍ� : int) 
                l_csv.deleteRow(l_intAddRow);
                continue;
            }

            // set���J���i        
            IpoProductRow l_ipoProductRow =
                (IpoProductRow)l_ipoOrder[i].getProduct().getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);
            if (!l_ipoProductParams.getPublicPriceIsNull())
            {
                double l_dbPublicPrice = l_ipoProductRow.getPublicPrice();
                Double l_dbPublicPrice1 = new Double(l_dbPublicPrice);
                l_csv.setPublicOfferingPrice(l_intAddRow, l_dbPublicPrice1);
            }           

            // getDataSourceObject( )
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder[i].getDataSourceObject();

            // set���I����
            // [set���I����()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // ���I���ʁF�@@IPO�\��[index].IPO�\���s.���I����
            // ���I���ʁi�J��j�F�@@IPO�\��[index].IPO�\���s.���I���ʁi�J��j
            l_csv.setLotResult(
                i,
                l_ipoOrderRow.getLotResult(),
                l_ipoOrderRow.getLotResultRetry());

            // set���I����
            l_csv.setElectedQuantity(i, l_ipoOrderRow.getElectedQuantity());

            // set�w���\������
            if (l_ipoOrderRow.getApplicationQuantityIsNull())
            {
                l_csv.setApplicationQuantity(i, 0.0D/0.0D);
            }
            else
            {
                l_csv.setApplicationQuantity(i, l_ipoOrderRow.getApplicationQuantity());
            }

            // set�w���\���^���ޓ���
            // [set�w���\���^���ޓ���()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // �w���\���^���ޓ����F�@@IPO�\��[index].IPO�\���s.�w���\���^���ޓ���
            l_csv.setOfferingTimestamp(i, l_ipoOrderRow.getOfferingTimestamp());

            // set�w���\�����(int, String)
            // [set�w���\�����()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // �w���\����ԁF�@@IPO�\��[index].IPO�\���s.�w���\���敪
            l_csv.setOfferStatus(i, l_ipoOrderRow.getOfferingDiv());

            // ��t���(int, String)
            // [set��t���()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // ��t��ԁF�@@IPO�\��[index].IPO�\���s.��t���
            l_csv.setAcceptStatus(i, l_ipoOrderRow.getAcceptStatus());

            // set�D�揇��(int, long)
            // [set�D�揇��()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // �D�揇�ʁF�@@IPO�\��[index].IPO�\���s.�D�揇��
            if (!l_ipoOrderRow.getSubstitutePriorityIsNull())
            {
                Long l_lngSubstitutePriority =
                    new Long(l_ipoOrderRow.getSubstitutePriority());
                l_csv.setSubstitutePriority(i, l_lngSubstitutePriority);
            }
            // set���I�ԍ�(int, String)
            // [set���I�ԍ�()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // �D�揇�ʁF�@@IPO�\��[index].IPO�\���s.���I�ԍ�
            l_csv.setLotNumber(i, l_ipoOrderRow.getLotNumber());
        }

        // getCSV�t�@@�C���s( )
        String[] l_strCsvFileLines = l_csv.getCsvFileLines();

        log.exiting(STR_METHOD_NAME);
        return l_strCsvFileLines;
    }

    /**
     * (create�w���\��FewCSV���)<BR>
     * ���I���ʁE�w���\����CSV���𐶐�����B<BR>
     * �i���҃R�[�h�A�M�p�敪�A���J���i�A���I�ԍ��Ȃ��j<BR>
     * <BR>
     * �V�[�P���X�}�F�uIPO�i�Ǘ��ҁE���I���ʍw���\����DL�jcreate�w���\��FewCSV���v�Q��<BR>
     * <BR>
     * @@param l_ipoOrder - (IPO�\��[])<BR>
     * IPO�\���s[]
     * @@param l_csv - (���I���ʁE�w���\��FewCSV���)<BR>
     * ���I���ʁE�w���\��FewCSV���I�u�W�F�N�g
     * @@return String[]
     * @@throws WEB3BaseException
     */
    protected String[] creatIpoLotBuyListFewInfo(
        WEB3IpoOrderImpl[] l_ipoOrder,
        WEB3AdminIpoLotBuyListFewCsv l_csv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "creatIpoLotBuyListFewInfo(WEB3IpoOrderImpl[], WEB3AdminIpoLotBuyListFewCsv)";
        log.entering(STR_METHOD_NAME);

        if (l_ipoOrder == null || l_csv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �����GIPO�\��[]�e�v�f����LOOP����
        int l_intIpoOrder = l_ipoOrder.length;
        for (int i = 0; i < l_intIpoOrder; i++)
        {
            // add���׍s( )
            int l_intAddRow = l_csv.addRow();

            // get���XID( )
            long l_lngBranchId = l_ipoOrder[i].getBranchId();

            // set���X�R�[�h(int, long)
            l_csv.setBranchCode(i, l_lngBranchId);
            try
            {
                // get����ID
                long l_lngAccountId = l_ipoOrder[i].getAccountId();

                // set�ڋq
                // [set�ڋq()�Ɏw�肷�����]
                // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
                // �����h�c�F�@@IPO�\��[index].get�����h�c()
                l_csv.setAccount(i, l_lngAccountId);
            }
            // �ڋq�I�u�W�F�N�g�擾�Ɏ��s�����ꍇ
            // �ڋq�I�u�W�F�N�g�擾�Ɏ��s�����ꍇ(set�ڋq�ɂė�O������)
            // �A�Y�c�ڋq�̖��׍s�͍쐬���Ȃ��B
            catch (WEB3BaseException l_ex)
            {
                // delete���׍s(�s�ԍ� : int)
                l_csv.deleteRow(l_intAddRow);
                continue;
            }

            // getDataSourceObject( )
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder[i].getDataSourceObject();

            // set���I����
            // [set���I����()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // ���I���ʁF�@@IPO�\��[index].IPO�\���s.���I����
            // ���I���ʁi�J��j�F�@@IPO�\��[index].IPO�\���s.���I���ʁi�J��j  
            l_csv.setLotResult(
                i,
                l_ipoOrderRow.getLotResult(),
                l_ipoOrderRow.getLotResultRetry());

            // set���I����
            l_csv.setElectedQuantity(i, l_ipoOrderRow.getElectedQuantity());

            // set�w���\������
            if (l_ipoOrderRow.getApplicationQuantityIsNull())
            {
                l_csv.setApplicationQuantity(i, 0.0D/0.0D);
            }
            else
            {
                l_csv.setApplicationQuantity(i, l_ipoOrderRow.getApplicationQuantity());
            }

            // set�w���\���^���ޓ���
            // [set�w���\���^���ޓ���()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex)
            // �w���\���^���ޓ����F�@@IPO�\��[index].IPO�\���s.�w���\���^���ޓ���
            Date l_datOfferingTimestamp = l_ipoOrderRow.getOfferingTimestamp();
            if (l_datOfferingTimestamp != null)
            {
                l_csv.setOfferingTimestamp(i, l_datOfferingTimestamp);
            }

            // set�w���\�����(int, String)
            // [set�w���\�����()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // �w���\����ԁF�@@IPO�\��[index].IPO�\���s.�w���\���敪
            l_csv.setOfferStatus(i, l_ipoOrderRow.getOfferingDiv());

            // ��t���(int, String)
            // [set��t���()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // ��t��ԁF�@@IPO�\��[index].IPO�\���s.��t���
            l_csv.setAcceptStatus(i, l_ipoOrderRow.getAcceptStatus());

            // set�D�揇��(int, long)
            // [set�D�揇��()�Ɏw�肷�����]
            // �s�ԍ��F�@@IPO�\���̗v�f�ԍ��iindex�j
            // �D�揇�ʁF�@@IPO�\��[index].IPO�\���s.�D�揇��
            if (!l_ipoOrderRow.getSubstitutePriorityIsNull())
            {
                l_csv.setSubstitutePriority(i, l_ipoOrderRow.getSubstitutePriority());
            }
        }
        
        // getCSV�t�@@�C���s( )
        String[] l_strCsvFileLines = l_csv.getCsvFileLines();

        // return
        log.exiting(STR_METHOD_NAME);
        return l_strCsvFileLines;
    }
}
@
