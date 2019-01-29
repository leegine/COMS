head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoBookbuildingStateDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X����(WEB3AdminIpoBookbuildingStateDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/23 �ė� (���u) �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>043,044
Revesion History : 2005/01/06 ���(SRA) �c�Ή�>>>056
Revesion History : 2005/01/11 ���(SRA) �c�Ή�>>>044(�F�G�]�͏C���ǉ���)
Revision History : 2005/08/19 ��c(SRA) ���捞�Č�IPO-No.76�i�p�t�H�[�}���X���P�j
Revesion History : 2005/12/20 杊��] (���u) �d�l�ύXNo.104�C��
Revision History : 2006/11/09 ꎉ� (���u) �d�l�ύXNo.161
                   2006/11/09 ꎉ� (���u) �d�l�ύXNo.163
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3AdminIpoBookbuildingCounter;
import webbroker3.ipo.WEB3AdminIpoDemandListCsv;
import webbroker3.ipo.WEB3AdminIpoDemandListFewCsv;
import webbroker3.ipo.WEB3AdminIpoInvalidOperationCsv;
import webbroker3.ipo.WEB3AdminIpoInvalidOperationFewCsv;
import webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoBookbuildingOrderActionParams;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3IpoBBStateFileTypeDef;
import webbroker3.ipo.define.WEB3IpoCsvDivDef;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadResponse;
import webbroker3.ipo.message.WEB3IPODemandDistributionUnit;
import webbroker3.ipo.message.WEB3IPODemandHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoBookbuildingStateDownloadService;
import webbroker3.ipo.service.delegate.WEB3IpoOrderActionUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X�����N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIpoBookbuildingStateDownloadServiceImpl implements WEB3AdminIpoBookbuildingStateDownloadService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoBookbuildingStateDownloadServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE20221
     */
    public WEB3AdminIpoBookbuildingStateDownloadServiceImpl() 
    {
     
    }
    
    /**
     * (get�_�E�����[�h���)<BR>
     * IPO�u�b�N�r���f�B���O�󋵎擾�������s���B<BR>
     * �i�Ǘ���IPO�u�b�N�r���f�B���O�󋵃_�E�����[�h��ʂɕ\���j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jget�_�E�����[�h��ʁv�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>                            
     * �V�[�P���X�}(�uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jget�_�E�����[�h��ʁv�j: <BR>                          
     *   1.7(*1) �iis���~() == true�j�̏ꍇ�A��O���X���[����B<BR>                           
     *   class: WEB3BusinessLayerException<BR>                           
     *   tag:   BUSINESS_ERROR_00589<BR>                         
     *   1.7(*1) �iis�폜() == true�j�̏ꍇ�A��O���X���[����B<BR>                           
     *   class: WEB3BusinessLayerException<BR>                           
     *   tag:   BUSINESS_ERROR_00588<BR>                         
     *   1.7(*1) �iis�u�b�N�r���f�B���O�J�n() == false�j�̏ꍇ�A��O���X���[����B<BR>                         
     *   class: WEB3BusinessLayerException<BR>                           
     *   tag:   BUSINESS_ERROR_00587<BR>                         
     * ==========================================================<BR>  
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadResponse
     * @@roseuid 40E136780373
     */
    protected WEB3AdminIPOBookBuildingStateDownloadResponse getDownloadScreen(WEB3AdminIPOBookBuildingStateDownloadRequest l_request)
               throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminIPOBookBuildingStateDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //1.10.�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ڽ��ݽ
        WEB3AdminIPOBookBuildingStateDownloadResponse l_response = (WEB3AdminIPOBookBuildingStateDownloadResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //1.3.IPO����
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is���~()
            boolean l_blisDiscont = l_ipoProduct.isDiscontinuation();
            
            //1.5.is�폜����()
            boolean l_blisDeletedProd = l_ipoProduct.isDeletedProduct();
            
            //1.6.is�u�b�N�r���f�B���O�J�n()
            boolean l_blisBookbuildingStart = l_ipoProduct.isBookbuildingStart();
            
            //1.7.(*1)(is���~()==true Or is�폜����()==true Or is�u�b�N�r���f�B���O�J�n()==false)
            if (l_blisDiscont)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (l_blisDeletedProd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (!l_blisBookbuildingStart)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00587, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }    
            
            //�u�b�N�r���f�B���O�󋵏W�v����
            WEB3AdminIpoBookbuildingCounter l_ipoBookbuildingCounter = new WEB3AdminIpoBookbuildingCounter();
            
            //IPO�\��
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl[] l_ipoOrders = l_ipoOrderManagerImpl.getOrderUnits(l_ipoProduct);       
        
			if (l_ipoOrders == null)
            {
			     log.exiting(STR_METHOD_NAME);
                //U01801           
				 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01980,
                     STR_METHOD_NAME);
			}
            
            //calc�u�b�N�r���f�B���O��
            this.calcBookbuildingSituation(l_ipoBookbuildingCounter, l_ipoOrders);
            
            //�v���p�e�B�Z�b�g
            l_response.productCode = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();     //�����R�[�h
            l_response.productName = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getStandardName();    //������
            l_response.lotTargetNumber 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getLotTargetOrderNumber());     //���I�Ώې\������
            l_response.cancelNumber 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getCancelNumber());        //�������
            l_response.allDemandNumber 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getAllOrderNumber());     //�S�\������
            l_response.averageDemandPrice 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getAverageOrderPrice());  //���ϐ\�����i
            l_response.lotTargetQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getLotTargetOrderQuantity());   //���I�Ώې\������                              
            l_response.cancelQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getCancelQuantity());      //�������
            l_response.allDemandQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getAllOrderQuantity());   //�S�\������
            l_response.paymentPowerHolderTotalNumber 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getTradingPowerHolderTotalNumber());     //�o���]�͕ێ��ҍ��v�l��
            l_response.paymentPowerHolderTotalQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getTradingPowerHolderTotalQuantity());   //�o���]�͕ێ��ҍ��v����
            l_response.displayUnitDiv = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getIpoUnitDiv();   //�\���p�P�ʋ敪
            l_response.temporaryConditionDiv 
                        = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProvisionalValueDiv();        //�������敪
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_nfex.getMessage(), l_nfex);

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�\�����z�})<BR>
     * IPO�u�b�N�r���f�B���O�\�����z�}�f�[�^�擾�������s���B<BR>
     * �i�Ǘ���IPO�u�b�N�r���f�B���O�󋵐\�����z�}��ʂɕ\���j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jget�\�����z�}�v�Q�ƁB<BR>
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸސ\�����z�}ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse
     * @@roseuid 40E137310066
     */
    protected WEB3AdminIPOBookBuildingDemandMapResponse getOrderDistributionChart(WEB3AdminIPOBookBuildingDemandMapRequest l_request) 
    {
        //xx
     return null;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * IPO�u�b�N�r���f�B���O�󋵃_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jget�_�E�����[�h�t�@@�C���v�Q�ƁB<BR>
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadResponse
     * @@roseuid 40E137AD0066
     */
    protected WEB3AdminIPOBookBuildingStateFileDownloadResponse getDownloadFile(WEB3AdminIPOBookBuildingStateFileDownloadRequest l_request)
               throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminIPOBookBuildingStateFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //1.10.�Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ڽ��ݽ
        WEB3AdminIPOBookBuildingStateFileDownloadResponse l_response = (WEB3AdminIPOBookBuildingStateFileDownloadResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //1.3.IPO����
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException

            //1.4.is���~()
            boolean l_blisDiscont = l_ipoProduct.isDiscontinuation();
            
            //1.5.is�폜����()
            boolean l_blisDeletedProd = l_ipoProduct.isDeletedProduct();
            
            //1.6.is�u�b�N�r���f�B���O�J�n()
            boolean l_blisBookbuildingStart = l_ipoProduct.isBookbuildingStart();
            
            //1.7.(*1)(is���~()==true Or is�폜����()==true Or is�u�b�N�r���f�B���O�J�n()==false)
            if (l_blisDiscont)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (l_blisDeletedProd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (!l_blisBookbuildingStart)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00587, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            
            // ����t���[�i���N�G�X�g�f�[�^.�t�@@�C�����==�����f�[�^
            // ���� ���N�G�X�g�f�[�^.CSV�敪 = "0"(�ǉ����ڂȂ�)�j�̏ꍇ
            if (WEB3IpoBBStateFileTypeDef.INVALID_OP.equals(l_request.fileTypeCode)
                && WEB3IpoCsvDivDef.ADD_ITEM_OFF.equals(l_request.csvDiv))
            {
                // createFew�����I�y���[�V���������t�@@�C��(IPO����)
                l_response.downloadFile =
                    this.createFewInvalidOperationFiles(l_ipoProduct);
            }

            // ����t���[�i���N�G�X�g�f�[�^.�t�@@�C�����==�����f�[�^
            // ���� ���N�G�X�g�f�[�^.CSV�敪 = "1"(�ǉ����ڂ���)�j�̏ꍇ
            if (WEB3IpoBBStateFileTypeDef.INVALID_OP.equals(l_request.fileTypeCode)
                && WEB3IpoCsvDivDef.ADD_ITEM_ON.equals(l_request.csvDiv))
            {
                // create�����I�y���[�V���������t�@@�C��(IPO����)
                l_response.downloadFile =
                    this.createInvalidOperationActionFiles(l_ipoProduct);
            }

            // ����t���[�i���N�G�X�g�f�[�^.�t�@@�C�����==BB�󋵃f�[�^�i�]�͂���j
            // or BB�󋵃f�[�^�i�]�͂Ȃ��j ���� ���N�G�X�g�f�[�^.CSV�敪 = "0"�i�ǉ����ڂȂ��j�j�̏ꍇ
            if ((WEB3IpoBBStateFileTypeDef.BB_STATE_TP_ON.equals(l_request.fileTypeCode)
                || WEB3IpoBBStateFileTypeDef.BB_STATE_TP_OFF.equals(l_request.fileTypeCode))
                && WEB3IpoCsvDivDef.ADD_ITEM_OFF.equals(l_request.csvDiv))
            {
                // createFew�u�b�N�r���f�B���O�󋵃t�@@�C��
                l_response.downloadFile =
                    this.createFewBookbuildingStateFiles(l_ipoProduct, l_request);
            }

            // ����t���[�i���N�G�X�g�f�[�^.�t�@@�C�����==BB�󋵃f�[�^�i�]�͂���j or
            // BB�󋵃f�[�^�i�]�͂Ȃ��j ���� ���N�G�X�g�f�[�^.CSV�敪 = "1"�i�ǉ����ڂ���j�j�̏ꍇ
            if ((WEB3IpoBBStateFileTypeDef.BB_STATE_TP_ON.equals(l_request.fileTypeCode)
                || WEB3IpoBBStateFileTypeDef.BB_STATE_TP_OFF.equals(l_request.fileTypeCode))
                && WEB3IpoCsvDivDef.ADD_ITEM_ON.equals(l_request.csvDiv))
            {
                // create�u�b�N�r���f�B���O�󋵃t�@@�C��
                l_response.downloadFile =
                    this.createBookbuildingStateFiles(l_ipoProduct, l_request);
            }

            // �v���p�e�B�Z�b�g
            ////���ݓ���
            l_response.currentDate = l_finApp.getTradingSystem().getSystemTimestamp();
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 l_nfex.getMessage(), l_nfex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�\�������ꗗ)<BR>
     * IPO�u�b�N�r���f�B���O�\�������ꗗ�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jget�\�������ꗗ�v�Q�ƁB<BR>
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸސ\������ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse
     * @@roseuid 40E138520131
     */
    protected WEB3AdminIPOBookBuildingHistoryResponse getOrderActionList(WEB3AdminIPOBookBuildingHistoryRequest l_request)
               throws WEB3BaseException   
    {
        final String STR_METHOD_NAME = " getOrderActionList(WEB3AdminIPOBookBuildingHistoryRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFrom���O�C�����()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate����()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //1.15.�Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ڽ��ݽ
        WEB3AdminIPOBookBuildingHistoryResponse l_response = (WEB3AdminIPOBookBuildingHistoryResponse)l_request.createResponse();
        
        try
        {
            
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //1.3.IPO����
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException

            //1.4.is���~()
            boolean l_blisDiscont = l_ipoProduct.isDiscontinuation();
            
            //1.5.is�폜����()
            boolean l_blisDeletedProd = l_ipoProduct.isDeletedProduct();
            
            //1.6.is�u�b�N�r���f�B���O�J�n()
            boolean l_blisBookbuildingStart = l_ipoProduct.isBookbuildingStart();
            
            //1.7.(*1)(is���~()==true Or is�폜����()==true Or is�u�b�N�r���f�B���O�J�n()==false)
            if (l_blisDiscont)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (l_blisDeletedProd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (!l_blisBookbuildingStart)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00587, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }                   

            //1.8.get�،���ЃR�[�h()
            String l_strInstCode = l_administartor.getInstitutionCode();
            
            //1.9.get�ڋq(�،���ЃR�[�h�A���X�R�[�h�A�����R�[�h) 
            WEB3GentradeAccountManager l_genAccountMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            String l_strBranchCode  = l_request.branchCode;
            String l_strAccountCode = l_request.accountCode;
            MainAccount l_maMainAccount = 
                l_genAccountMgr.getMainAccount(
                                        l_strInstCode,
                                        l_strBranchCode,
                                        l_strAccountCode);                
            
            //1.10.getSubAccount(����ID�A�⏕�����^�C�v)
            long l_lngAccountID = l_maMainAccount.getAccountId() ;
            SubAccount l_saSubAccount = 
                l_finApp.getAccountManager().getSubAccount(
                                        l_lngAccountID,
                                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                       
            //1.11.getIPO�\��(�⏕�����AIPO����ID)
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl 
                    = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManagerImpl.getOrderUnit(l_saSubAccount,l_ipoProductId);
            
            //1.12.getIPO����
            //WEB3IpoProductImpl l_ipoProductfromOrder = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
                        
            //1.13.createIPO�\�����𖾍�(IPO�\��)
            WEB3IpoOrderActionUnitService l_service = (WEB3IpoOrderActionUnitService)Services.getService(WEB3IpoOrderActionUnitService.class);
            WEB3IPODemandHistoryUnit[] l_ipoOrderActions = l_service.createOrderActionUnit(l_ipoOrder);
            
            //1.14.get���̑����i���t�\�z(�⏕����,��n��)
            TradingSystem l_trdSys = l_finApp.getTradingSystem();  
            WEB3TPTradingPowerService l_tpTPS  = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            double l_trdPow = l_tpTPS.getOtherTradingPower((WEB3GentradeSubAccount)l_saSubAccount,l_trdSys.getBizDate() );
                       
            //1.16.(*2)�v���p�e�B�Z�b�g
            l_response.paymentPower = WEB3StringTypeUtility.formatNumber(l_trdPow);//�o���]��
            l_response.demandHistoryList = l_ipoOrderActions;  //�\�������ꗗ
            l_response.displayUnitDiv = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getIpoUnitDiv();  //�\���p�P�ʋ敪
            
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_nfex.getMessage(), l_nfex);

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �Ǘ���IPO�u�b�N�r���f�B���O�󋵃_�E�����[�h���������{����B<BR>
     * <BR>
     * �P�j�@@�Ɩ������ݒ�<BR>
     * �@@������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A<BR>�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ظ��Ă̏ꍇ<BR>
     * �@@�|get�_�E�����[�h���()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A<BR>�Ǘ���IPO�ޯ�����ިݸސ\�����z�}ظ��Ă̏ꍇ<BR>
     * �@@�|get�\�����z�}()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A<BR>�Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ظ��Ă̏ꍇ<BR>
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A<BR>�Ǘ���IPO�ޯ�����ިݸސ\������ظ��Ă̏ꍇ<BR>
     * �@@�|get�\�������ꗗ()���R�[������B<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E138D50095
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        
        // Timestamp���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        // Timestamp�ݒ�
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_processTime);
        
        if (l_request instanceof WEB3AdminIPOBookBuildingStateDownloadRequest)  //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ظ��Ă̏ꍇ
        {
            l_response =  this.getDownloadScreen((WEB3AdminIPOBookBuildingStateDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOBookBuildingStateFileDownloadRequest)  //�Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ظ��Ă̏ꍇ
        {
            l_response =  this.getDownloadFile((WEB3AdminIPOBookBuildingStateFileDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOBookBuildingHistoryRequest)  //�Ǘ���IPO�ޯ�����ިݸސ\������ظ��Ă̏ꍇ
        {
            l_response =  this.getOrderActionList((WEB3AdminIPOBookBuildingHistoryRequest)l_request);
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
        return l_response;
    }
    
    /**
     * (calc�u�b�N�r���f�B���O��)<BR>
     * �u�b�N�r���f�B���O�󋵏W�v�������s���B<BR>
     * <BR>
     * ����.IPO�\��[]�̊e�v�f���Ɉȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * --- LOOP START ---<BR>
     * <BR>
     * �P�j�@@����̏ꍇ<BR>
     * �@@�@@�iIPO�\��[index].get�u�b�N�r���f�B���O�\����� == <BR>OrderStatusEnum.CANCELLED�i�F����j�j<BR>
     * <BR>
     *   �P�|�P�j�@@����̏W�v�i�� ���Y�ڋq�ɗL���Ȑ\�����Ȃ��ꍇ�j<BR>
     * �@@�@@�u�b�N�r���f�B���O�󋵏W�v����.add�������()���R�[������B<BR>
     * <BR>
     * �@@�@@[add�������()�Ɏw�肷�����]<BR>
     * �@@�@@���ʁF�@@�Ώۗv�f�i�FIPO�\���j.get����()<BR>
     * <BR>
     * �Q�j�@@����ȊO�̏ꍇ<BR>
     * �@@�@@�iIPO�\��[index].get�u�b�N�r���f�B���O�\����� != <BR>OrderStatusEnum.CANCELLED�i�F����j�j
     * <BR>
     * �@@�Q�|�P�j�@@���I�Ώۂ̏W�v<BR>
     * �@@�@@�u�b�N�r���f�B���O�󋵏W�v����.add���I�Ώې\������()���R�[������B<BR>
     * <BR>
     * �@@�@@[add���I�Ώې\������()�Ɏw�肷�����]<BR>
     * �@@�@@���ʁF�@@�Ώۗv�f�i�FIPO�\���j.get����()<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�\�����i�̏W�v<BR>
     * �@@�@@�u�b�N�r���f�B���O�󋵏W�v����.add�\�����i()���R�[������B<BR>
     * <BR>
     * �@@�@@[add�\�����i()�Ɏw�肷�����]<BR>
     * �@@�@@�\�����i�F�@@�Ώۗv�f�i�FIPO�\���j.get�\�����i()<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�o���]�͕ێ��҂̏W�v<BR>
     * �@@�@@�ȉ��̏����ɓ��Ă͂܂�ꍇ�A<BR>
     * �@@�@@�u�b�N�r���f�B���O�󋵏W�v����.add�o���]�͕ێ��ҍ��v����()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�Ώۗv�f�i�FIPO�\���j.�\�������z <= �o���]�́�<BR>
     * <BR>
     * �@@�@@[add�o���]�͕ێ��ҍ��v����()�Ɏw�肷�����]<BR>
     * �@@�@@���ʁF�@@�Ώۗv�f�i�FIPO�\���j.get����()<BR>
     * <BR>
     * �@@�@@���@@�o���]��<BR>
     * �@@�@@����]�̓T�[�r�XImpl.get���̑����i���t�\�z()�̖߂�l <BR>
     * <BR>
     *     [���̑����i���t�\�z()�Ɏw�肷�����] <BR>
     *   �@@�⏕�����F�@@�Ώۗv�f�i�FIPO�\���j.get�⏕����()<BR>
     * �@@�@@��n���F�@@TradingSystem.getBizDate() <BR>
     * <BR>
     * --- LOOP END ---<BR>
     * <BR>
     * @@param l_bookbuildingCounter - �u�b�N�r���f�B���O�󋵏W�v���ʃI�u�W�F�N�g
     * 
     * @@param l_ipoOrders - (IPO�\�����X�g)<BR>
     * IPO�\���̔z��<BR>
     * @@roseuid 40E3A04A0246
     */
    protected void calcBookbuildingSituation(WEB3AdminIpoBookbuildingCounter l_bookbuildingCounter, WEB3IpoOrderImpl[] l_ipoOrders) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcBookbuildingSituation(WEB3AdminIpoBookbuildingCounter, WEB3IpoOrderImpl[])";
        log.entering(STR_METHOD_NAME );
                
        if (l_bookbuildingCounter == null || l_ipoOrders == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        int l_intCount = l_ipoOrders.length;
        log.debug("l_intCount = " + l_intCount);

        for (int i = 0; i < l_intCount; i++)
        {
            WEB3IpoOrderImpl l_ipoOrder =  l_ipoOrders[i];
        
            if (l_ipoOrder.getOrderStatus() == OrderStatusEnum.CANCELLED)
            {
                double l_dbQuantity = l_ipoOrder.getQuantity();  //���ʂ��擾
                l_bookbuildingCounter.addCancelQuantity(l_dbQuantity);  //����\�����W�v
            }
            else
            {
                double l_dblQuantity = l_ipoOrder.getQuantity();  //���ʂ��擾
                l_bookbuildingCounter.addLotTargetOrderQuantity(l_dblQuantity);  //���I�Ώې\�����W�v
            
                double l_dblPrice = l_ipoOrder.getOrderPrice();  //�\�����i���擾
                l_bookbuildingCounter.addOrderPrice(l_dblPrice);  //�\�����i���W�v
            }
        }                    
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�\�����z����)<BR>
     * IPO�\�����z���ׂ̔z��𐶐����A<BR>
     * �����W�͈́i�\�����i�����^�\�����i����j���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�\�����i���z�����W�v�Z<BR>
     * �@@�����W�͈́A�����W�����v�Z����B<BR>
     * <BR>
     * �@@�����W�͈͍͂��݂ɂ���Č��肷��B<BR>
     * �����W���̏����10�Ƃ���B<BR>
     * �����W����10���傫���Ȃ�ꍇ�́A<BR>
     * �����W�͈͂����݂�n�{�ɍL���A10�ȓ��ɂ���B<BR>
     * <BR>
     * �@@�ȉ��̌v�Z�����{����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i�����������l(*1) - ����������l(*2)�j �� ����(*3) + 1<BR>
     * <BR>
     * �@@(*1) IPO����.IPO�����s.�����������l<BR>
     * �@@(*2) IPO����.IPO�����s.����������l<BR>
     * �@@(*3) IPO����.IPO�����s.����<BR>
     * <BR>
     * �@@�|�i��L�̌v�Z���� <= 10�j�̏ꍇ�A<BR>
     * �@@�@@�@@�E�v�Z���ʂ̏����_�ȉ���؂�̂Ă������l�������W���Ƃ���B<BR>
     * �@@�@@�@@�E���݂������W�͈͂Ƃ���B<BR>
     * <BR>
     * �@@�|�i��L�̌v�Z���� > 10�j�̏ꍇ�A<BR>
     * �@@�@@�@@�v�Z�����̍��݂̒l���A�i���݁~2�j�ɕύX���čČv�Z����B<BR>
     * �@@�@@�@@�i�Čv�Z���� > 10�j�̏ꍇ�́A�i���݁~3�j�ōČv�Z���A<BR>
     * �@@�@@�@@�i�Čv�Z���� <= 10�j�ɂȂ�܂ŁA�i���݁~n�j�̍Čv�Z���J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�i�Čv�Z���� <= 10�j�ɂȂ����ꍇ�A<BR>
     * �@@�@@�@@�E�Čv�Z���ʂ̏����_�ȉ���؂�̂Ă������l�������W���Ƃ���B<BR>
     * �@@�@@�@@�E�i���݁~n�j�������W�͈͂Ƃ���B<BR>
     * <BR>
     * �Q�j�@@�\�����z���ׂ̔z�񐶐�<BR>
     * <BR>
     * �@@�P�j�ŋ��߂������W���ŁAIPO�\�����z���ׂ̔z��𐶐�����B<BR>
     * <BR>
     * �@@IPO�\�����z���׃I�u�W�F�N�g�̔z��𐶐�����B<BR>
     * �@@�z��̗v�f���́A�P�j�ŋ��߂������W���Ƃ���B<BR>
     * <BR>
     * �R�j�@@�\�����z���׃����W�͈́i�\�����i�����^�\�����i����j�Z�b�g<BR>
     * <BR>
     * �@@�Q�j�Ő��������z��̗v�f�����ȉ����J��Ԃ��B<BR>
     * �@@// for (int index = 0; index < IPO�\�����z���ׂ̔z��.length; index ++) 
     * <BR>
     * �@@--- LOOP START ---<BR>
     * <BR>
     * �@@�R�|�P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �@@IPO�\�����z���׃I�u�W�F�N�g���C���X�^���X������B<BR>
     * �inew IPO�\�����z����()�j<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�\�����i�����^����Z�b�g<BR>
     * <BR>
     * �@@�|�����i�iIPO����.is�f�B�X�J�E���g����() == false�j�̏ꍇ<BR>
     * �@@�@@IPO�\�����z����.�\�����i���� = ����������l(*2) - (index �~ �����W�͈́j<BR>
     * �@@�@@IPO�\�����z����.�\�����i��� = ����������l(*2) - ((index + 1) �~ �����W�͈́j<BR>
     * <BR>
     * �@@�@@���@@�A���A�ŏI�v�f�̏ꍇ�iindex + 1 = �z��.length�j�A<BR>
     * �@@�@@�@@�@@�\�����i�����A�\�����i����̗����ɁA<BR>
     * {����������l(*2) �| (index �~ �����W�͈́j}<BR>
     * �@@�@@�@@�@@���Z�b�g����B<BR>
     * <BR>
     * �@@�|�f�B�X�J�E���g���iIPO����.is�f�B�X�J�E���g����() == true�j�̏ꍇ<BR>
     * �@@�@@IPO�\�����z����.�\�����i���� = �����������l(*1) + (index �~ �����W�͈́j<BR>
     * �@@�@@IPO�\�����z����.�\�����i��� = �����������l(*1) + ((index + 1) �~ �����W�͈́j<BR>
     * <BR>
     * �@@�@@���@@�A���A�ŏI�v�f�̏ꍇ�iindex + 1 = �z��.length�j�A<BR>
     * �@@�@@�@@�@@�\�����i�����A�\�����i����̗����ɁA<BR>{�����������l(*1) �{ (index �~ �����W�͈́j}���Z�b�g����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�z��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�Q�j�Ő��������z��[index] = IPO�\�����z����<BR>
     * <BR>
     * �@@--- LOOP END ---<BR>
     * <BR>
     * �@@(*4) IPO����.IPO�����s.�������敪<BR>
     * <BR>
     * �S�j�@@�z��ԋp<BR>
     * <BR>
     * �@@IPO�\�����z���׃I�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.message.WEB3IpoOrderDistributionUnit
     * @@roseuid 40E4DE57009F
     */
    protected WEB3IPODemandDistributionUnit createOrderDistributionDetails(WEB3IpoProductImpl l_ipoProduct) 
    {
        //xx
     return null;
    }
    
    /**
     * (calc�\�����z�l)<BR>
     * IPO�\�����z���ׂɐ\�����z�l���Z�b�g���A���ϐ\�����i��ԋp����B<BR>
     * <BR>
     * �����^���i�̏W�v<BR>
     * <BR>
     * IPO�\��[]�̊e�v�f���Ɉȉ��̏������J��Ԃ��B<BR>
     * // for (int index = 0; index < IPO�\��.length; index ++)<BR>
     * <BR>
     * --- LOOP START ---<BR>
     * <BR>
     * �P�j�@@�\�����i�擾<BR>
     * �@@�\�����i = �Ώۗv�f�i�FIPO�\���j.get�\�����i()<BR>
     * <BR>
     * �Q�j�@@�����J�E���g<BR>
     * <BR>
     * �@@�|�f�B�X�J�E���g���iIPO����.is�f�B�X�J�E���g����() == true�j�̏ꍇ<BR>
     * �@@�@@[����]<BR>
     * �@@�@@IPO�\�����z����[n].�\�����i���� > �\�����i >= <BR>
     * IPO�\�����z����[n].�\�����i���<BR>
     *  �@@�@@<BR>
     * �@@�@@[�\�������̃C���N�������g]<BR>
     * �@@�@@IPO�\�����z����[n].�\������ = IPO�\�����z����[n].�\������ + 1
     * <BR>
     * �@@�|�����i�iIPO����.is�f�B�X�J�E���g����() == false�j�̏ꍇ<BR>
     * �@@�@@�����̐\�����z���׃��X�g�i�FIPO�\�����z����[]�j�̗v�f�̂����A<BR>
     * �@@�@@�ȉ��̏����ɓ��Ă͂܂�v�f�̐\���������C���N�������g����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@IPO�\�����z����[n].�\�����i���� < <BR>
     * �\�����i <= IPO�\�����z����[n].�\�����i���<BR>
     *  �@@�@@<BR>
     * �@@�@@[�\�������̃C���N�������g]<BR>
     * �@@�@@IPO�\�����z����[n].�\������ = IPO�\�����z����[n].�\������ + 1<BR>
     * <BR>
     * �R�j�@@�\�����i�W�v<BR>
     * �@@�u�b�N�r���f�B���O�󋵏W�v����.add���I�Ώې\������()���R�[������B<BR>
     * �@@�u�b�N�r���f�B���O�󋵏W�v����.add�\�����i()���R�[������B<BR>
     * <BR>
     * �@@[add�\�����i()�Ɏw�肷�����]<BR>
     * �@@�\�����i�F�@@�\�����i<BR>
     * <BR>
     * --- LOOP END ---<BR>
     * <BR>
     * <BR>
     * @@param l_orderDistributionUnit - (�\�����z���׃��X�g)<BR>
     * IPO�\�����z���ׂ̔z��B<BR>
     * <BR>
     * ���@@�w��O�Ɋe�v�f�̃C���X�^���X���A�����W�̐ݒ肪�K�v�B<BR>
     * 
     * @@param l_bookbuildingCounter - �u�b�N�r���f�B���O�󋵏W�v���ʃI�u�W�F�N�g
     * 
     * @@param l_ipoOrder - IPO�\���̔z��
     * 
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@roseuid 40E5065C03DB
     */
    protected void calcOrderDistributionPrice(WEB3IPODemandDistributionUnit l_orderDistributionUnit, WEB3AdminIpoBookbuildingCounter l_bookbuildingCounter, WEB3IpoOrderImpl l_ipoOrder, WEB3IpoProductImpl l_ipoProduct) 
    {
        //xx
     
    }
    
    /**
     * (create�����I�y���[�V���������t�@@�C��)<BR>
     * �_�E�����[�h�����Ώۂ̃f�[�^���擾���Asort���ĕԋp����B<BR>
     * ���@@�����I�y���[�V��������<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jcreate�����I�y���[�V���������t�@@�C���v�Q�ƁB<BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 40EE7EBF0234
     */
    protected String[] createInvalidOperationActionFiles(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createInvalidOperationActionFiles(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME );
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String[] l_invalidOperationActionFiles = null;
        
        //�����I�y���[�V��������CSV(IPO����)
        WEB3AdminIpoInvalidOperationCsv l_ipoInvalidOperationCsv = new WEB3AdminIpoInvalidOperationCsv(l_ipoProduct);
        
        //get�����u�b�N�r���f�B���O�\������
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoBookbuildingOrderActionImpl[] l_ipoBookBuildingOrderActions = l_ipoOrderManagerImpl.getInvalidOrderActions(l_ipoProduct.getProductId());
                 
        if (l_ipoBookBuildingOrderActions == null)
        {
            log.exiting(STR_METHOD_NAME);
            //U01801                       
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01981,
                STR_METHOD_NAME);
        }
                
        //sort
//        Comparator[] l_comparator = new Comparator[3];
//        l_comparator[0] = new WEB3IpoBookbuildingOrderActionBranchCodeComparator(WEB3AscDescDef.ASC);
//        l_comparator[1] = new WEB3IpoBookbuildingOrderActionAccountCodeComparator(WEB3AscDescDef.ASC);
//        l_comparator[2] = new WEB3IpoBookbuildingOrderActionCreatedTimestampComparator(WEB3AscDescDef.ASC);
//        WEB3ArraysUtility.sort(l_ipoBookBuildingOrderActions, l_comparator);
        
        //�u�b�N�r���f�B���O�\������[]�e�v�f����LOOP����
        int l_intCount = l_ipoBookBuildingOrderActions.length;
        for (int i = 0; i < l_intCount; i ++)
        {
            WEB3IpoBookbuildingOrderActionImpl l_ipoBookBuildingOrderAction = l_ipoBookBuildingOrderActions[i];
            
            //���׍s��ǉ�
            int l_intRow = l_ipoInvalidOperationCsv.addRow();
            
            //�u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g���擾
            IpoBookbuildingOrderActionRow l_ipoBookBuildingOrderActionRow = (IpoBookbuildingOrderActionRow)l_ipoBookBuildingOrderAction.getDataSourceObject();
            IpoBookbuildingOrderActionParams l_params = new IpoBookbuildingOrderActionParams(l_ipoBookBuildingOrderActionRow);
            
            //���X�R�[�h���Z�b�g
            long l_lngBranchCode = l_params.branch_id;
            l_ipoInvalidOperationCsv.setBranchCode(l_intRow, l_lngBranchCode);
            
            //�ڋq�R�[�h�A�ڋq�����Z�b�g
            try
            {
                //�����h�c���擾
                long l_lngAccountId = l_ipoBookBuildingOrderAction.getAccountId();
                //1.3.5.set���҃R�[�h(int, long)
                l_ipoInvalidOperationCsv.setTradeCode(l_intRow, l_lngAccountId);
                l_ipoInvalidOperationCsv.setAccount(l_intRow, l_lngAccountId);
            }
            catch (WEB3BaseException l_ex)
            {
                l_ipoInvalidOperationCsv.deleteRow(l_intRow);
                continue;
            }
            
            //�쐬�������擾
            Timestamp l_timestamp = l_ipoBookBuildingOrderAction.getOrderActionTimestamp();
            
            //��t�������Z�b�g
            l_ipoInvalidOperationCsv.setReceptionDate(l_intRow, l_timestamp);
            
            //���ʂ��擾
            double l_dblQuantity = l_ipoBookBuildingOrderAction.getQuantity();
            
            //�\�����ʂ��Z�b�g�A�\�����i���Z�b�g
            l_ipoInvalidOperationCsv.setOrderQuantity(l_intRow, l_dblQuantity);   //�\�����ʂ��Z�b�g
            if (l_params.getPriceIsNull())
            {
                l_ipoInvalidOperationCsv.setOrderPrice(l_intRow, (0.0D / 0.0D));  //�\�����i���Z�b�g
            }
            else
            {
                l_ipoInvalidOperationCsv.setOrderPrice(l_intRow, l_params.getPrice());  //�\�����i���Z�b�g
            }
            
            //�u�b�N�r���f�B���O�\����Ԃ��擾
            OrderStatusEnum l_orderStatusEnum = l_ipoBookBuildingOrderAction.getOrderStatus();
            
            //�������e���Z�b�g
            l_ipoInvalidOperationCsv.setProcessing(l_intRow, l_orderStatusEnum);
            
            //�w�l���擾
            double l_dblPrice = l_ipoBookBuildingOrderAction.getPrice();
            
            //�w�l�^���s���Z�b�g
            l_ipoInvalidOperationCsv.setLimitPriceMarketOrder(l_intRow, l_dblPrice);
        }
        
        //CSV�t�@@�C���s���擾
        l_invalidOperationActionFiles = l_ipoInvalidOperationCsv.getCsvFileLines();
        
        log.exiting(STR_METHOD_NAME);
        return l_invalidOperationActionFiles;
    }
    
    /**
     * (create�u�b�N�r���f�B���O�󋵃t�@@�C��)<BR>
     * �_�E�����[�h�����Ώۂ̃f�[�^���擾���Asort���ĕԋp����B<BR>
     * ���@@�u�b�N�r���f�B���O��<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jcreate�u�b�N�r���f�B���O�󋵃t�@@�C���v�Q�ƁB<BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 40EE7EEF00CC
     */
    protected String[] createBookbuildingStateFiles(
        WEB3IpoProductImpl l_ipoProduct,
        WEB3AdminIPOBookBuildingStateFileDownloadRequest l_request
    ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createBookbuildingStateFiles(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME );
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String[] l_bookbuildingStateFiles = null;
        
        //1.1.�u�b�N�r���f�B���O��CSV�f�[�^���f���𐶐�
        WEB3AdminIpoDemandListCsv l_ipoDemandListCsv = new WEB3AdminIpoDemandListCsv(l_ipoProduct);
        
        //1.2.�����ɊY������IPO�\�����擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoOrderImpl[] l_ipoOrders =
            l_ipoOrderManagerImpl.getOrderUnits(
                l_ipoProduct,
                l_request.branchCode,
                l_request.accountCodeFrom,
                l_request.accountCodeTo,
                l_request.bbCreatedTimestampFrom,
                l_request.bbCreatedTimestampTo
            );
        if (l_ipoOrders == null)
        {
            log.exiting(STR_METHOD_NAME);
            //U01801                       
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01980,
                STR_METHOD_NAME);
        }
        
        
        //1.3.sort
//        Comparator[] l_comparator = new Comparator[1];
//        l_comparator[0] = new WEB3IpoOrderBookbuildingCreatedTimestampComparator(WEB3AscDescDef.ASC);
//        WEB3ArraysUtility.sort(l_ipoOrders, l_comparator);
        
        //1.4.IPO�\��[]�e�v�f����LOOP����
        int l_intCount = l_ipoOrders.length;
        for (int i = 0; i < l_intCount; i ++)
        {
            WEB3IpoOrderImpl l_ipoOrder = l_ipoOrders[i];
            
            //1.4.1.���׍s��ǉ�
            int l_intRow = l_ipoDemandListCsv.addRow();
            
            //1.4.2.���X�h�c���擾
            long l_lngBranchCode = l_ipoOrder.getBranchId();
            
            //1.4.3.���X�R�[�h���Z�b�g            
            l_ipoDemandListCsv.setBranchCode(l_intRow, l_lngBranchCode);
            
            
            try
            {
                //1.4.4.�����h�c���擾
                long l_lngAccountId = l_ipoOrder.getAccountId();
                //1.3.5.set���҃R�[�h(int, long)
                l_ipoDemandListCsv.setTradeCode(l_intRow, l_lngAccountId);
                //1.4.5.�ڋq�����Z�b�g
                l_ipoDemandListCsv.setAccount(l_intRow, l_lngAccountId);
            }
            //1.4.6.�ڋq�I�u�W�F�N�g�擾���s�����ꍇ
            catch (WEB3BaseException l_ex)
            {
                //1.4.6.1.delete���׍s
                l_ipoDemandListCsv.deleteRow(l_intRow);
                continue;
            }
            
            //1.4.7.�V�K�\���������擾
            Timestamp l_timestamp = ((IpoOrderRow)l_ipoOrder.getDataSourceObject()).getBookbuildingCreatedTimestamp();
            
            //1.4.8.�V�K�\���������Z�b�g
            l_ipoDemandListCsv.setBookbuildingCreatedTimestamp(l_intRow, l_timestamp);
            
            //1.4.9.���ʂ��擾
            double l_dblQuantity = l_ipoOrder.getQuantity();
            
            //1.4.10.�\�����ʂ��Z�b�g
            l_ipoDemandListCsv.setOrderQuantity(l_intRow, l_dblQuantity);
            
            //1.4.11.�w�l���擾
            double l_dblLimitPrice = l_ipoOrder.getLimitPrice();
            
            //1.4.12.�w�l�^���s���Z�b�g
            l_ipoDemandListCsv.setLimitPriceMarketOrderPrice(l_intRow, l_dblLimitPrice);
            
            //IPO�\���s�I�u�W�F�N�g���擾
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            //1.4.13.�\�����i���Z�b�g
            if (!l_ipoOrderRow.getPriceIsNull())
            {
                double l_dblPrice = l_ipoOrderRow.getPrice();
                l_ipoDemandListCsv.setOrderPrice(l_intRow, l_dblPrice);
            }
            else
            {
                l_ipoDemandListCsv.setOrderPrice(l_intRow, (0.0D / 0.0D));
            }
            
            if (!l_ipoOrderRow.getBookbuildingPriceIsNull())
            {
                //1.4.14.�\�������z���Z�b�g
                double l_dblBookBuildingPrice = l_ipoOrderRow.getBookbuildingPrice();
                l_ipoDemandListCsv.setBookbuildingPrice(l_intRow, l_dblBookBuildingPrice);
            }
            else
            {
                l_ipoDemandListCsv.setBookbuildingPrice(l_intRow, (0.0D / 0.0D));
            }

            //1.4.15.��l���Z�b�g
            if (!l_ipoOrderRow.getCurrentPriceIsNull())
            {
                double l_dblCurrentPrice = l_ipoOrderRow.getCurrentPrice();
                l_ipoDemandListCsv.setBasePrice(l_intRow, l_dblCurrentPrice);
            }
            else
            {
                l_ipoDemandListCsv.setBasePrice(l_intRow, (0.0D / 0.0D));
            }
            
            //BB�󋵃f�[�^�i�]�͂���j
            if (l_request.fileTypeCode.equals(WEB3IpoBBStateFileTypeDef.BB_STATE_TP_ON))
            {
                //get�⏕����()
                WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) l_ipoOrder.getSubAccount();
            
                //get���̑����i���t�\�z(�⏕����,��n��)
                TradingSystem l_trdSys = l_finApp.getTradingSystem();         
                WEB3TPTradingPowerService l_tpTPS  
                    = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                double l_trdPow = l_tpTPS.getOtherTradingPower(l_subAccount, l_trdSys.getBizDate() );

                //�o���]�͂��Z�b�g
                l_ipoDemandListCsv.setTradingPower(l_intRow, l_trdPow);
            
                //�\���z�ێ��҂��Z�b�g
                if (!l_ipoOrderRow.getBookbuildingPriceIsNull())
                {
                    double l_dblBookBuildingPrice = l_ipoOrderRow.getBookbuildingPrice();
                
                    l_ipoDemandListCsv.setTradingPowerCheck(l_intRow, l_dblBookBuildingPrice, l_trdPow);
                }
                else
                {
                    l_ipoDemandListCsv.setTradingPowerCheck(l_intRow, (0.0D / 0.0D), l_trdPow);
                }
            }
            //BB�󋵃f�[�^�i�]�͂Ȃ��j
            else
            {
                //�o���]�͂��Z�b�g
                l_ipoDemandListCsv.setTradingPowerWithoutIndicate(l_intRow);
                //�\���z�ێ��҂��Z�b�g
                l_ipoDemandListCsv.setTradingPowerCheckWithoutIndicate(l_intRow);
            }
            
            //1.4.20.�u�b�N�r���f�B���O�\����Ԃ��擾
            OrderStatusEnum l_orderStatusEnum = l_ipoOrder.getOrderStatus();
            
            //1.4.21.�\����Ԃ��Z�b�g
            l_ipoDemandListCsv.setOrderStatus(l_intRow, l_orderStatusEnum);
        }
        
        //1.5.CSV�t�@@�C���s���擾
        l_bookbuildingStateFiles = l_ipoDemandListCsv.getCsvFileLines();
        
        log.exiting(STR_METHOD_NAME);
        return l_bookbuildingStateFiles;
    }
    
    /**
     * (createFew�����I�y���[�V���������t�@@�C��)<BR>
     * �_�E�����[�h�����Ώۂ̃f�[�^���擾���Asort���ĕԋp����B<BR>
     * ���@@�����I�y���[�V��������<BR>
     * �i���҃R�[�h�Ȃ��j <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jcreateFew�����I�y���[�V���������t�@@�C���v�Q�ƁB<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 40EE7EBF0234
     */
    protected String[] createFewInvalidOperationFiles(
        WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createFewInvalidOperationFiles(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME );

        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        String[] l_invalidOperationActionFewFiles = null;

        //�����I�y���[�V��������FewCSV�f�[�^���f���𐶐�����B
        //[�R���X�g���N�^�̈���]
        //IPO�����F�@@IPO�����I�u�W�F�N�g
        WEB3AdminIpoInvalidOperationFewCsv l_invalidOperationFewCsv =
            new WEB3AdminIpoInvalidOperationFewCsv(l_ipoProduct);

        //get�����u�b�N�r���f�B���O�\������
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl =
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoBookbuildingOrderActionImpl[] l_ipoBookBuildingOrderActions =
            l_ipoOrderManagerImpl.getInvalidOrderActions(l_ipoProduct.getProductId());

        if (l_ipoBookBuildingOrderActions == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01981,
                STR_METHOD_NAME);
        }

        //�擾�����a�a�\������[]�e�v�f����LOOP����
        int l_intCountActions = l_ipoBookBuildingOrderActions.length;
        for (int i = 0; i < l_intCountActions; i ++)
        {
            WEB3IpoBookbuildingOrderActionImpl l_ipoBookBuildingOrderAction =
                l_ipoBookBuildingOrderActions[i];

            //���׍s��ǉ�
            int l_intRow = l_invalidOperationFewCsv.addRow();

            //�u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g���擾
            IpoBookbuildingOrderActionRow l_ipoBookBuildingOrderActionRow =
                (IpoBookbuildingOrderActionRow)l_ipoBookBuildingOrderAction.getDataSourceObject();

            //���X�R�[�h���Z�b�g
            long l_lngBranchCode = l_ipoBookBuildingOrderActionRow.getBranchId();
            l_invalidOperationFewCsv.setBranchCode(l_intRow, l_lngBranchCode);

            //�ڋq�R�[�h�A�ڋq�����Z�b�g
            try
            {
                //�����h�c���擾
                long l_lngAccountId = l_ipoBookBuildingOrderAction.getAccountId();

                // set�ڋq(int, long)
                l_invalidOperationFewCsv.setAccount(l_intRow, l_lngAccountId);
            }
            // �ڋq�I�u�W�F�N�g�擾�Ɏ��s�����ꍇ
            catch (WEB3BaseException l_ex)
            {
                //delete���׍s(�s�ԍ� : int)
                l_invalidOperationFewCsv.deleteRow(l_intRow);

                //continue
                continue;
            }

            // get�쐬����
            Timestamp l_timestamp = l_ipoBookBuildingOrderAction.getOrderActionTimestamp();

            // set��t����
            l_invalidOperationFewCsv.setReceptionDate(l_intRow, l_timestamp);

            // get����
            double l_dblQuantity = l_ipoBookBuildingOrderAction.getQuantity();

            // set�\������
            l_invalidOperationFewCsv.setOrderQuantity(l_intRow, l_dblQuantity);

            // set�\�����i
            if (l_ipoBookBuildingOrderActionRow.getPriceIsNull())
            {
                l_invalidOperationFewCsv.setOrderPrice(l_intRow, (0.0D / 0.0D));
            }
            else
            {
                l_invalidOperationFewCsv.setOrderPrice(l_intRow, l_ipoBookBuildingOrderActionRow.getPrice());
            }

            // get�u�b�N�r���f�B���O�\�����
            OrderStatusEnum l_orderStatusEnum = l_ipoBookBuildingOrderAction.getOrderStatus();

            // set�������e
            l_invalidOperationFewCsv.setProcessing(l_intRow, l_orderStatusEnum);

            // get�w�l
            double l_dblPrice = l_ipoBookBuildingOrderAction.getPrice();

            // set�w�l�^���s�l
            l_invalidOperationFewCsv.setLimitPriceMarketOrder(l_intRow, l_dblPrice);
        }

        // getCSV�t�@@�C���s
        l_invalidOperationActionFewFiles = l_invalidOperationFewCsv.getCsvFileLines();

        log.exiting(STR_METHOD_NAME);
        return l_invalidOperationActionFewFiles;
    }   
    
    /**
     * (createFew�u�b�N�r���f�B���O�󋵃t�@@�C��)<BR>
     * �_�E�����[�h�����Ώۂ̃f�[�^���擾���Asort���ĕԋp����B<BR>
     * ���@@�u�b�N�r���f�B���O��<BR>
     * �i���҃R�[�h�Ȃ��j <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE�a�a�󋵂c�k�jcreateFew�u�b�N�r���f�B���O�󋵃t�@@�C���v�Q�ƁB<BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�u�b�N�r���f�B���O�󋵃t�@@�C���_�E�����[�h���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 40EE7EBF0234
     */
    protected String[] createFewBookbuildingStateFiles(
        WEB3IpoProductImpl l_ipoProduct,
        WEB3AdminIPOBookBuildingStateFileDownloadRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createFewBookbuildingStateFiles(WEB3IpoProductImpl, " +
            "WEB3AdminIPOBookBuildingStateFileDownloadRequest)";
        log.entering(STR_METHOD_NAME );

        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �u�b�N�r���f�B���O��fewCSV
        WEB3AdminIpoDemandListFewCsv l_csv =
            new WEB3AdminIpoDemandListFewCsv(l_ipoProduct);

        // getIPO�\��(IPO����, String[], String, String, Date, Date)
        // [getIPO�\��()�Ɏw�肷�����]
        // IPO�����F�@@IPO����
        // ����.���N�G�X�g�f�[�^.���X�R�[�h�̔z��
        // ����.���N�G�X�g�f�[�^.�ڋq�R�[�hfrom
        // ����.���N�G�X�g�f�[�^.�ڋq�R�[�hto
        // ����.���N�G�X�g�f�[�^.�V�K�\������from
        // ����.���N�G�X�g�f�[�^.�V�K�\������to
        String[] l_strBranchCodes = l_request.branchCode;
        String l_strAccountCodeFrom = l_request.accountCodeFrom;
        String l_strAccountCodeTo = l_request.accountCodeTo;
        Date l_datBbCreatedTimestampFrom = l_request.bbCreatedTimestampFrom;
        Date l_datBbCreatedTimestampTo = l_request.bbCreatedTimestampTo;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl =
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoOrderImpl[] l_ipoOrder =
            (WEB3IpoOrderImpl[])l_ipoOrderManagerImpl.getOrderUnits(
                l_ipoProduct,
                l_strBranchCodes,
                l_strAccountCodeFrom,
                l_strAccountCodeTo,
                l_datBbCreatedTimestampFrom,
                l_datBbCreatedTimestampTo);

        if (l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01980,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �擾����IPO�\��[]�e�v�f����LOOP����
        int l_intIpoOrder = l_ipoOrder.length;
        for (int i = 0; i < l_intIpoOrder; i++)
        {
            // add���׍s( )
            int l_intAddRow = l_csv.addRow();

            // get���XID( )
            long l_lngBranchId = l_ipoOrder[i].getBranchId();

            // set���X�R�[�h(int, long)
            // [set���X�R�[�h()�Ɏw�肷�����]
            // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            // ���X�h�c�F�@@IPO�\��[index].get���X�h�c()
            l_csv.setBranchCode(l_intAddRow, l_lngBranchId);

            try
            {
                // get����ID( )
                long l_lngAccountId = l_ipoOrder[i].getAccountId();

                // set�ڋq(int, long)
                l_csv.setAccount(l_intAddRow, l_lngAccountId);
            }
            // �ڋq�I�u�W�F�N�g�擾�Ɏ��s�����ꍇ
            catch(WEB3BaseException l_ex)
            {
                // delete���׍s(�s�ԍ� : int)
                l_csv.deleteRow(l_intAddRow);

                // continue
                continue;
            }

            // getDataSourceObject( )
            IpoOrderRow l_ipoOrderRow =
                (IpoOrderRow)l_ipoOrder[i].getDataSourceObject();

            // set�V�K�\������(int, Timestamp)
            // [set�V�K�\������()�Ɏw�肷�����]
            // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            // �V�K�\�������F�@@IPO�\��[index].IPO�\���s.�V�K�\������
            Timestamp l_tsBookbuildingCreate =
                l_ipoOrderRow.getBookbuildingCreatedTimestamp();
            l_csv.setBookbuildingCreatedTimestamp(
                l_intAddRow,
                l_tsBookbuildingCreate);

            // get����( )
            double l_dbQuantity = l_ipoOrder[i].getQuantity();

            // set�\������(int, double)
            // [set�\������()�Ɏw�肷�����]
            // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            // �\�����ʁF�@@IPO�\��[index].get����()
            l_csv.setOrderQuantity(l_intAddRow, l_dbQuantity);

            // get�w�l( )
            double l_dbLimitPrice = l_ipoOrder[i].getLimitPrice();

            // set�w�l�^���s�l(int, double)
            // [set�w�l�^���s()�Ɏw�肷�����]
            // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            // �w�l�F�@@IPO�\��[index].get�w�l()
            l_csv.setLimitPriceMarketOrderPrice(l_intAddRow, l_dbLimitPrice);

            // set�\�����i(int, double)
            // [set�\�����i()�Ɏw�肷�����]
            // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            // �\�����i�F�@@IPO�\��[index].IPO�\���s.�v�Z�P��
            if (l_ipoOrderRow.getPriceIsNull())
            {
                l_csv.setOrderPrice(l_intAddRow, (0.0D / 0.0D));
            }
            else
            {
                l_csv.setOrderPrice(l_intAddRow, l_ipoOrderRow.getPrice());
            }

            // set�\�������z(int, double)
            // [set�\�������z()�Ɏw�肷�����]
            // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            // �\�����i�F�@@IPO�\��[index].IPO�\���s.�\�������z
            if (l_ipoOrderRow.getBookbuildingPriceIsNull())
            {
                l_csv.setBookbuildingPrice(l_intAddRow, (0.0D / 0.0D));
            }
            else
            {
                l_csv.setBookbuildingPrice(l_intAddRow, l_ipoOrderRow.getBookbuildingPrice());
            }

            // set��l(int, double)
            // [set��l()�Ɏw�肷�����]
            // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            // �\�����i�F�@@IPO�\��[index].IPO�\���s.��l�i�����j
            if (l_ipoOrderRow.getCurrentPriceIsNull())
            {
                l_csv.setBasePrice(l_intAddRow, (0.0D / 0.0D));
            }
            else
            {
                l_csv.setBasePrice(l_intAddRow, l_ipoOrderRow.getCurrentPrice());
            }

            // BB�󋵃f�[�^�i�]�͂���j�̂Ƃ�
            if (WEB3IpoBBStateFileTypeDef.BB_STATE_TP_ON.equals(
                l_request.fileTypeCode))
            {
                // get�⏕����( )
                SubAccount l_dbSubAccount = l_ipoOrder[i].getSubAccount();

                // get���̑����i���t�\�z(�⏕���� : �⏕����, ��n�� : Date)
                TradingSystem l_trdSys = l_finApp.getTradingSystem();
                WEB3TPTradingPowerService l_tpTPS =
                    (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                double l_trdPow = l_tpTPS.getOtherTradingPower(
                    (WEB3GentradeSubAccount)l_dbSubAccount,
                    l_trdSys.getBizDate());

                // set�o���]��(int, double)
                // [set�o���]��()�Ɏw�肷�����]
                // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
                // �o���]�́F�@@�iget���̑����i���t�\�z()�̖߂�l)
                l_csv.setTradingPower(l_intAddRow, l_trdPow);

                // set�\���z�ێ���(int, double, double)
                l_csv.setTradingPowerCheck(
                    l_intAddRow, l_ipoOrderRow.getBookbuildingPrice(), l_trdPow);
            }

            // BB�󋵃f�[�^�i�]�͂Ȃ��j�̂Ƃ�
            if (WEB3IpoBBStateFileTypeDef.BB_STATE_TP_OFF.equals(
                    l_request.fileTypeCode))
            {
                // set�o���]�́|�]�͕\���Ȃ�(int)
                l_csv.setTradingPowerWithoutIndicate(l_intAddRow);

                // set�\���z�ێ��ҁ|�]�͕\���Ȃ�(int)
                l_csv.setTradingPowerCheckWithoutIndicate(l_intAddRow);
            }

            // get�u�b�N�r���f�B���O�\�����( )
            OrderStatusEnum l_orderStatus = l_ipoOrder[i].getOrderStatus();

            // set�\�����(int, OrderStatusEnum)
            // [set�\�����()�Ɏw�肷�����]
            // �s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            // IPO�\���L����ԁF�@@IPO�\��[index].get�u�b�N�r���f�B���O�\�����()
            l_csv.setOrderStatus(l_intAddRow, l_orderStatus);
        }
        // getCSV�t�@@�C���s( )
        String[] l_strCsvFileLines = l_csv.getCsvFileLines();

        log.exiting(STR_METHOD_NAME);
        return l_strCsvFileLines;
    }
}
@
