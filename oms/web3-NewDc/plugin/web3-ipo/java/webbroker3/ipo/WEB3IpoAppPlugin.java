head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-IPO �v���O�C��(WEB3IpoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
Revesion History : 2005/01/13 ��� (SRA) >>>�Ǘ��Җ����V�K�o�^�A���������̃C���^�Z�v�^�[�o�^
Revesion History : 2005/12/20 ��� (���u) �d�l�ύX101~114
Revesion History : 2006/01/26 �s�p�i���u�j�d�l�ύX�E���f��118
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.ipo.data.WEB3IpoAccountDatabaseExtensions;
import webbroker3.ipo.data.WEB3IpoMasterDatabaseExtensions;
import webbroker3.ipo.handler.WEB3AdminIpoBookbuildingStateDownloadHandler;
import webbroker3.ipo.handler.WEB3AdminIpoCancelHandler;
import webbroker3.ipo.handler.WEB3AdminIpoLotHandler;
import webbroker3.ipo.handler.WEB3AdminIpoLotResultOfferDownloadHandler;
import webbroker3.ipo.handler.WEB3AdminIpoLotResultUploadHandler;
import webbroker3.ipo.handler.WEB3AdminIpoManagementDetailsHandler;
import webbroker3.ipo.handler.WEB3AdminIpoOfferStopResumeHandler;
import webbroker3.ipo.handler.WEB3AdminIpoProductChangeHandler;
import webbroker3.ipo.handler.WEB3AdminIpoProductDeleteHandler;
import webbroker3.ipo.handler.WEB3AdminIpoProductRegistrationHandler;
import webbroker3.ipo.handler.WEB3IpoBatoClientHandler;
import webbroker3.ipo.handler.WEB3IpoBookbuildingCancelHandler;
import webbroker3.ipo.handler.WEB3IpoBookbuildingChangeHandler;
import webbroker3.ipo.handler.WEB3IpoBookbuildingEnterHandler;
import webbroker3.ipo.handler.WEB3IpoBookbuildingOrderHandler;
import webbroker3.ipo.handler.WEB3IpoDeclineHandler;
import webbroker3.ipo.handler.WEB3IpoOfferHandler;
import webbroker3.ipo.handler.WEB3IpoOrderOfferListHandler;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOCancelCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOCancelCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOCancelConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOCancelConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotInputResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputResponse;
import webbroker3.ipo.message.WEB3AdminIPOManagementRequest;
import webbroker3.ipo.message.WEB3AdminIPOManagementResponse;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputResponse;
import webbroker3.ipo.message.WEB3IPOBatoUrlRequest;
import webbroker3.ipo.message.WEB3IPOBatoUrlResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3IPODeclineCompleteRequest;
import webbroker3.ipo.message.WEB3IPODeclineCompleteResponse;
import webbroker3.ipo.message.WEB3IPODeclineConfirmRequest;
import webbroker3.ipo.message.WEB3IPODeclineConfirmResponse;
import webbroker3.ipo.message.WEB3IPODemandCommonRequest;
import webbroker3.ipo.message.WEB3IPODemandCommonResponse;
import webbroker3.ipo.message.WEB3IPODemandOfferRequest;
import webbroker3.ipo.message.WEB3IPODemandOfferResponse;
import webbroker3.ipo.message.WEB3IPOLotCommonRequest;
import webbroker3.ipo.message.WEB3IPOLotCommonResponse;
import webbroker3.ipo.message.WEB3IPOOfferCommonResponse;
import webbroker3.ipo.message.WEB3IPOOfferCompleteRequest;
import webbroker3.ipo.message.WEB3IPOOfferCompleteResponse;
import webbroker3.ipo.message.WEB3IPOOfferConfirmRequest;
import webbroker3.ipo.message.WEB3IPOOfferConfirmResponse;
import webbroker3.ipo.message.WEB3IPOOfferInputRequest;
import webbroker3.ipo.message.WEB3IPOOfferInputResponse;
import webbroker3.ipo.message.WEB3IPOProductInfoRequest;
import webbroker3.ipo.message.WEB3IPOProductInfoResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoBookbuildingStateDownloadService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoCancelService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultOfferDownloadService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadUnitService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoManagementDetailsService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoOfferStopResumeService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductChangeService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductDeleteService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductRegistrationService;
import webbroker3.ipo.service.delegate.WEB3IpoBatoClientService;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingCancelService;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingChangeService;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingEnterService;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingOrderService;
import webbroker3.ipo.service.delegate.WEB3IpoDeclineService;
import webbroker3.ipo.service.delegate.WEB3IpoOfferService;
import webbroker3.ipo.service.delegate.WEB3IpoOrderActionUnitService;
import webbroker3.ipo.service.delegate.WEB3IpoOrderOfferListService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.ipo.service.delegate.WEB3IpoUploadActionUnitService;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoBookbuildingStateDownloadServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoCancelServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoLotResultOfferDownloadServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoLotResultUploadServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoLotResultUploadUnitServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoLotServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoManagementDetailsServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoOfferStopResumeServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoProductChangeServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoProductDeleteServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoProductRegistrationServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBatoClientServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBookbuildingCancelServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBookbuildingChangeServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBookbuildingEnterServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBookbuildingOrderServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoDeclineServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoOfferServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoOrderActionUnitServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoOrderOfferListServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoProductInfoServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoUploadActionUnitServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-IPO �v���O�C���N���X�B
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public final class WEB3IpoAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3IpoAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3IpoAppPlugin()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String STR_METHOD_NAME = " plug()";
        log.entering(STR_METHOD_NAME);

        plug(WEB3IpoAppPlugin.class);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String STR_METHOD_NAME = " onPlug()";
        log.entering(STR_METHOD_NAME);
        
        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        // �g���g�����U�N�V�����E�}�l�[�W���[��
        // �I�[�o�[���C�h���\�b�h���������ߊg��������W���[���N���X���쐬��
        // �g��������W���[���N���X���Őݒ�

        try
        {
            log.debug("Installing TradingModule : web3-ipo: ENTER");
            l_finApp.installTradingModule(new WEB3IpoTradingModule());
            log.debug("Installed TradingModule : web3-ipo: END");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }
        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);

        // �g���v���_�N�g�E�}�l�[�W���[
        l_tradingModule.overrideProductManager(new WEB3IpoProductManagerImpl());

        // �g�������}�l�[�W��
        l_tradingModule.overrideOrderManager(new WEB3IpoOrderManagerImpl());

        // DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3IpoMasterDatabaseExtensions.plug();
        WEB3IpoAccountDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------
        
        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�޻��޽
        Services.registerService(WEB3AdminIpoBookbuildingStateDownloadService.class, new WEB3AdminIpoBookbuildingStateDownloadServiceImpl());

        //�Ǘ���IPO���~�T�[�r�X
        Services.registerService(WEB3AdminIpoCancelService.class, new WEB3AdminIpoCancelServiceImpl());
        
        //�Ǘ���IPO���I���ʍw���\�����޳�۰�޻��޽
        Services.registerService(WEB3AdminIpoLotResultOfferDownloadService.class, new WEB3AdminIpoLotResultOfferDownloadServiceImpl());

        //�Ǘ���IPO���I���ʱ���۰�޻��޽
        Services.registerService(WEB3AdminIpoLotResultUploadService.class, new WEB3AdminIpoLotResultUploadServiceImpl());

        //�Ǘ���IPO���I���ʃA�b�v���[�h�P���T�[�r�X
        Services.registerService(WEB3AdminIpoLotResultUploadUnitService.class, new WEB3AdminIpoLotResultUploadUnitServiceImpl());

        //�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X
        Services.registerService(WEB3AdminIpoManagementDetailsService.class, new WEB3AdminIpoManagementDetailsServiceImpl());

        //�Ǘ���IPO��W��~�ĊJ�T�[�r�X
        Services.registerService(WEB3AdminIpoOfferStopResumeService.class, new WEB3AdminIpoOfferStopResumeServiceImpl());

        //�Ǘ���IPO���������T�[�r�X
        Services.registerService(WEB3AdminIpoProductChangeService.class, new WEB3AdminIpoProductChangeServiceImpl());

        //�Ǘ���IPO�����폜�T�[�r�X
        Services.registerService(WEB3AdminIpoProductDeleteService.class, new WEB3AdminIpoProductDeleteServiceImpl());

        //�Ǘ���IPO�����o�^�T�[�r�X
        Services.registerService(WEB3AdminIpoProductRegistrationService.class, new WEB3AdminIpoProductRegistrationServiceImpl());

        //IPO�u�b�N�r���f�B���O����T�[�r�X
        Services.registerService(WEB3IpoBookbuildingCancelService.class, new WEB3IpoBookbuildingCancelServiceImpl());

        //IPO�u�b�N�r���f�B���O�����T�[�r�X
        Services.registerService(WEB3IpoBookbuildingChangeService.class, new WEB3IpoBookbuildingChangeServiceImpl());

        //IPO�u�b�N�r���f�B���O�\���T�[�r�X
        Services.registerService(WEB3IpoBookbuildingOrderService.class, new WEB3IpoBookbuildingOrderServiceImpl());

        //IPO�u�b�N�r���f�B���O�Q���T�[�r�X
        Services.registerService(WEB3IpoBookbuildingEnterService.class, new WEB3IpoBookbuildingEnterServiceImpl());

        //IPO���ރT�[�r�X
        Services.registerService(WEB3IpoDeclineService.class, new WEB3IpoDeclineServiceImpl());

        //IPO�\���E�w���\���ꗗ
        Services.registerService(WEB3IpoOrderOfferListService.class, new WEB3IpoOrderOfferListServiceImpl());

        //IPO�\�����𖾍׍쐬�T�[�r�X
        Services.registerService(WEB3IpoOrderActionUnitService.class, new WEB3IpoOrderActionUnitServiceImpl());

        //IPO�w���\���T�[�r�X
        Services.registerService(WEB3IpoOfferService.class, new WEB3IpoOfferServiceImpl());

        //IPO�������쐬�T�[�r�X
        Services.registerService(WEB3IpoProductInfoService.class, new WEB3IpoProductInfoServiceImpl());

        //IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X
        Services.registerService(WEB3IpoUploadActionUnitService.class, new WEB3IpoUploadActionUnitServiceImpl());
        
        //�Ǘ���IPO���I�����T�[�r�X
        Services.registerService(WEB3AdminIpoLotService.class, new WEB3AdminIpoLotServiceImpl());
        
        //IPO�d�q���ڑ��T�[�r�X
        Services.registerService(WEB3IpoBatoClientService.class, new WEB3IpoBatoClientServiceImpl());
        
		//Service �� ServiceInterceptor ��ݒ肷�� ----------------------

		//�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�޻��޽
//		  Services.addInterceptor(WEB3AdminIpoBookbuildingStateDownloadService.class, new WEB3IpoServiceInterceptor());

		//�Ǘ���IPO���~�T�[�r�X
//		  Services.addInterceptor(WEB3AdminIpoCancelService.class, new WEB3IpoServiceInterceptor());

		//�Ǘ���IPO���I���ʍw���\�����޳�۰�޻��޽ 
		Services.addInterceptor(WEB3AdminIpoLotResultOfferDownloadService.class, new WEB3IpoServiceInterceptor());

		//�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X
//		  Services.addInterceptor(WEB3AdminIpoManagementDetailsService.class, new WEB3IpoServiceInterceptor());

		//�Ǘ���IPO��W��~�ĊJ�T�[�r�X
//		  Services.addInterceptor(WEB3AdminIpoOfferStopResumeService.class, new WEB3IpoServiceInterceptor());

		//�Ǘ���IPO���������T�[�r�X
		Services.addInterceptor(WEB3AdminIpoProductChangeService.class, new WEB3IpoServiceInterceptor());

		//�Ǘ���IPO�����폜�T�[�r�X
//		  Services.addInterceptor(WEB3AdminIpoProductDeleteService.class, new WEB3IpoServiceInterceptor());

		//�Ǘ���IPO�����o�^�T�[�r�X
		Services.addInterceptor(WEB3AdminIpoProductRegistrationService.class, new WEB3IpoServiceInterceptor());

		//IPO�u�b�N�r���f�B���O����T�[�r�X
		Services.addInterceptor(WEB3IpoBookbuildingCancelService.class, new WEB3IpoServiceInterceptor());

		//IPO�u�b�N�r���f�B���O�����T�[�r�X
		Services.addInterceptor(WEB3IpoBookbuildingChangeService.class, new WEB3IpoServiceInterceptor());

		//IPO�u�b�N�r���f�B���O�Q���T�[�r�X
		Services.addInterceptor(WEB3IpoBookbuildingEnterService.class, new WEB3IpoServiceInterceptor());

		//IPO�u�b�N�r���f�B���O�\���T�[�r�X
		Services.addInterceptor(WEB3IpoBookbuildingOrderService.class, new WEB3IpoServiceInterceptor());

		//IPO���ރT�[�r�X
		Services.addInterceptor(WEB3IpoDeclineService.class, new WEB3IpoServiceInterceptor());

		//IPO�w���\���T�[�r�X
		Services.addInterceptor(WEB3IpoOfferService.class, new WEB3IpoServiceInterceptor());

		//IPO�\���w���\���ꗗ�T�[�r�X
		Services.addInterceptor(WEB3IpoOrderOfferListService.class, new WEB3IpoServiceInterceptor());

		//IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X
//		  Services.addInterceptor(WEB3IpoUploadActionUnitService.class, new WEB3IpoServiceInterceptor());

		//IPO�\�����𖾍׍쐬�T�[�r�X
//		  Services.addInterceptor(WEB3IpoOrderActionUnitService.class, new WEB3IpoServiceInterceptor());

		//IPO�������쐬�T�[�r�X
//		  Services.addInterceptor(WEB3IpoProductInfoService.class, new WEB3IpoServiceInterceptor());

		//�Ǘ���IPO���I���ʱ���۰��UnitService
//		  Services.addInterceptor(WEB3AdminIpoLotResultUploadUnitService.class, new WEB3AdminIpoLotResultUploadInterceptor());

		//�Ǘ���IPO���I���ʱ���۰�޻��޽
		Services.addInterceptor(WEB3AdminIpoLotResultUploadService.class, new WEB3AdminIpoLotResultUploadInterceptor());
        
        //IPO�d�q���ڑ��T�[�r�X
        Services.addInterceptor(WEB3IpoBatoClientService.class, new WEB3IpoBatoClientServiceInterceptor());

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminIpoBookbuildingStateDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO���~�T�[�r�X
        Services.addInterceptor(WEB3AdminIpoCancelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO���I���ʍw���\�����޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminIpoLotResultOfferDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO���I���ʱ���۰�޻��޽
        Services.addInterceptor(WEB3AdminIpoLotResultUploadService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO���I���ʃA�b�v���[�h�P���T�[�r�X
        Services.addInterceptor(
            WEB3AdminIpoLotResultUploadUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X
        Services.addInterceptor(WEB3AdminIpoManagementDetailsService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO��W��~�ĊJ�T�[�r�X
        Services.addInterceptor(WEB3AdminIpoOfferStopResumeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO���������T�[�r�X
        Services.addInterceptor(WEB3AdminIpoProductChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO�����폜�T�[�r�X
        Services.addInterceptor(WEB3AdminIpoProductDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���IPO�����o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminIpoProductRegistrationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�u�b�N�r���f�B���O����T�[�r�X
        Services.addInterceptor(WEB3IpoBookbuildingCancelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�u�b�N�r���f�B���O�����T�[�r�X
        Services.addInterceptor(WEB3IpoBookbuildingChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�u�b�N�r���f�B���O�\���T�[�r�X
        Services.addInterceptor(WEB3IpoBookbuildingOrderService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�u�b�N�r���f�B���O�Q���T�[�r�X
        Services.addInterceptor(WEB3IpoBookbuildingEnterService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO���ރT�[�r�X
        Services.addInterceptor(WEB3IpoDeclineService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�\���E�w���\���ꗗ
        Services.addInterceptor(WEB3IpoOrderOfferListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�\�����𖾍׍쐬�T�[�r�X
//        Services.addInterceptor(WEB3IpoOrderActionUnitService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�w���\���T�[�r�X
        Services.addInterceptor(WEB3IpoOfferService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�������쐬�T�[�r�X
//        Services.addInterceptor(WEB3IpoProductInfoService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X
//        Services.addInterceptor(WEB3IpoUploadActionUnitService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO�d�q���ڑ��T�[�r�X
        Services.addInterceptor(WEB3IpoBatoClientService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�޻��޽
        Services.addInterceptor(WEB3AdminIpoBookbuildingStateDownloadService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO���~�T�[�r�X
        Services.addInterceptor(WEB3AdminIpoCancelService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO���I���ʍw���\�����޳�۰�޻��޽
        Services.addInterceptor(WEB3AdminIpoLotResultOfferDownloadService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO���I���ʱ���۰�޻��޽
        Services.addInterceptor(WEB3AdminIpoLotResultUploadService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO���I���ʃA�b�v���[�h�P���T�[�r�X
        Services.addInterceptor(WEB3AdminIpoLotResultUploadUnitService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X
        Services.addInterceptor(WEB3AdminIpoManagementDetailsService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO��W��~�ĊJ�T�[�r�X
        Services.addInterceptor(WEB3AdminIpoOfferStopResumeService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO���������T�[�r�X
        Services.addInterceptor(WEB3AdminIpoProductChangeService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO�����폜�T�[�r�X
        Services.addInterceptor(WEB3AdminIpoProductDeleteService.class, new WEB3LogSysTimeInterceptor());

        //�Ǘ���IPO�����o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminIpoProductRegistrationService.class, new WEB3LogSysTimeInterceptor());

        //IPO�u�b�N�r���f�B���O����T�[�r�X
        Services.addInterceptor(WEB3IpoBookbuildingCancelService.class, new WEB3LogSysTimeInterceptor());

        //IPO�u�b�N�r���f�B���O�����T�[�r�X
        Services.addInterceptor(WEB3IpoBookbuildingChangeService.class, new WEB3LogSysTimeInterceptor());

        //IPO�u�b�N�r���f�B���O�\���T�[�r�X
        Services.addInterceptor(WEB3IpoBookbuildingOrderService.class, new WEB3LogSysTimeInterceptor());

        //IPO�u�b�N�r���f�B���O�Q���T�[�r�X
        Services.addInterceptor(WEB3IpoBookbuildingEnterService.class, new WEB3LogSysTimeInterceptor());

        //IPO���ރT�[�r�X
        Services.addInterceptor(WEB3IpoDeclineService.class, new WEB3LogSysTimeInterceptor());

        //IPO�\���E�w���\���ꗗ
        Services.addInterceptor(WEB3IpoOrderOfferListService.class, new WEB3LogSysTimeInterceptor());

        //IPO�\�����𖾍׍쐬�T�[�r�X
        Services.addInterceptor(WEB3IpoOrderActionUnitService.class, new WEB3LogSysTimeInterceptor());

        //IPO�w���\���T�[�r�X
        Services.addInterceptor(WEB3IpoOfferService.class, new WEB3LogSysTimeInterceptor());

        //IPO�������쐬�T�[�r�X
        Services.addInterceptor(WEB3IpoProductInfoService.class, new WEB3LogSysTimeInterceptor());

        //IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X
        Services.addInterceptor(WEB3IpoUploadActionUnitService.class, new WEB3LogSysTimeInterceptor());
        
        //�Ǘ���IPO���I�����T�[�r�X
        Services.addInterceptor(WEB3AdminIpoLotService.class, new WEB3AdminIpoLotInterceptor());
        
        //IPO�d�q���ڑ��T�[�r�X
        Services.addInterceptor(WEB3IpoBatoClientService.class, new WEB3LogSysTimeInterceptor());

        // Message �̓o�^���� ----------------------

        //IPO�u�b�N�r���f�B���O�Q�����N�G�X�g
        regClass(WEB3IPOBookBuildingEnterRequest.class);
        //IPO�u�b�N�r���f�B���O�Q�����X�|���X
        regClass(WEB3IPOBookBuildingEnterResponse.class);

        //IPO�u�b�N�r���f�B���O����m�F���N�G�X�g
        regClass(WEB3IPOBookBuildingCancelConfirmRequest.class);
        //IPO�u�b�N�r���f�B���O����m�F���X�|���X
        regClass(WEB3IPOBookBuildingCancelConfirmResponse.class);

        //IPO�u�b�N�r���f�B���O����������N�G�X�g
        regClass(WEB3IPOBookBuildingCancelCompleteRequest.class);
        //IPO�u�b�N�r���f�B���O����������X�|���X
        regClass(WEB3IPOBookBuildingCancelCompleteResponse.class);

        //IPO�u�b�N�r���f�B���O�\���m�F���N�G�X�g
        regClass(WEB3IPOBookBuildingDemandConfirmRequest.class);
        //IPO�u�b�N�r���f�B���O�\���m�F���X�|���X
        regClass(WEB3IPOBookBuildingDemandConfirmResponse.class);

        //IPO�u�b�N�r���f�B���O�\���������N�G�X�g
        regClass(WEB3IPOBookBuildingDemandCompleteRequest.class);
        //IPO�u�b�N�r���f�B���O�\���������X�|���X
        regClass(WEB3IPOBookBuildingDemandCompleteResponse.class);

        //IPO�u�b�N�r���f�B���O�\�����̓��N�G�X�g
        regClass(WEB3IPOBookBuildingDemandInputRequest.class);
        //IPO�u�b�N�r���f�B���O�\�����̓��X�|���X
        regClass(WEB3IPOBookBuildingDemandInputResponse.class);

        //IPO�u�b�N�r���f�B���O�\���������N�G�X�g
        regClass(WEB3IPOBookBuildingHistoryRequest.class);
        //IPO�u�b�N�r���f�B���O�\���������X�|���X
        regClass(WEB3IPOBookBuildingHistoryResponse.class);

        //IPO�u�b�N�r���f�B���O�����m�F���N�G�X�g
        regClass(WEB3IPOBookBuildingChangeConfirmRequest.class);
        //IPO�u�b�N�r���f�B���O�����m�F���X�|���X
        regClass(WEB3IPOBookBuildingChangeConfirmResponse.class);

        //IPO�u�b�N�r���f�B���O�����������N�G�X�g
        regClass(WEB3IPOBookBuildingChangeCompleteRequest.class);
        //IPO�u�b�N�r���f�B���O�����������X�|���X
        regClass(WEB3IPOBookBuildingChangeCompleteResponse.class);

        //IPO�u�b�N�r���f�B���O�������̓��N�G�X�g
        regClass(WEB3IPOBookBuildingChangeInputRequest.class);
        //IPO�u�b�N�r���f�B���O�������̓��X�|���X
        regClass(WEB3IPOBookBuildingChangeInputResponse.class);

        //IPO�ʖ�����񃊃N�G�X�g
        regClass(WEB3IPOProductInfoRequest.class);
        //IPO�ʖ�����񃌃X�|���X
        regClass(WEB3IPOProductInfoResponse.class);

        //IPO�w���\���m�F���N�G�X�g
        regClass(WEB3IPOOfferConfirmRequest.class);
        //IPO�w���\���m�F���X�|���X
        regClass(WEB3IPOOfferConfirmResponse.class);

        //IPO�w���\���������N�G�X�g
        regClass(WEB3IPOOfferCompleteRequest.class);
        //IPO�w���\���������X�|���X
        regClass(WEB3IPOOfferCompleteResponse.class);

        //IPO�w���\�����ʃ��X�|���X
        regClass(WEB3IPOOfferCommonResponse.class);

        //IPO�w���\�����̓��N�G�X�g
        regClass(WEB3IPOOfferInputRequest.class);
        //IPO�w���\�����̓��X�|���X
        regClass(WEB3IPOOfferInputResponse.class);

        //IPO���ފm�F���N�G�X�g
        regClass(WEB3IPODeclineConfirmRequest.class);
        //IPO���ފm�F���X�|���X
        regClass(WEB3IPODeclineConfirmResponse.class);

        //IPO���ފ������X�|���X
        regClass(WEB3IPODeclineCompleteRequest.class);
        //IPO���ފ������N�G�X�g
        regClass(WEB3IPODeclineCompleteResponse.class);

        //IPO�\�����ʃ��X�|���X
        regClass(WEB3IPODemandCommonResponse.class);
        //IPO�\�����ʃ��N�G�X�g
        regClass(WEB3IPODemandCommonRequest.class);

        //IPO�\���w���\�����N�G�X�g
        regClass(WEB3IPODemandOfferRequest.class);
        //IPO�\���w���\�����X�|���X
        regClass(WEB3IPODemandOfferResponse.class);

        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ظ���
        regClass(WEB3AdminIPOBookBuildingStateDownloadRequest.class);
        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ڽ��ݽ
        regClass(WEB3AdminIPOBookBuildingStateDownloadResponse.class);

        //�Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ظ���
        regClass(WEB3AdminIPOBookBuildingStateFileDownloadRequest.class);
        //�Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ڽ��ݽ
        regClass(WEB3AdminIPOBookBuildingStateFileDownloadResponse.class);

        //�Ǘ���IPO�ޯ�����ިݸސ\�����z�}ظ���
        regClass(WEB3AdminIPOBookBuildingDemandMapRequest.class);
        //�Ǘ���IPO�ޯ�����ިݸސ\�����z�}ڽ��ݽ
        regClass(WEB3AdminIPOBookBuildingDemandMapResponse.class);

        //�Ǘ���IPO�ޯ�����ިݸސ\������ظ���
        regClass(WEB3AdminIPOBookBuildingHistoryRequest.class);
        //�Ǘ���IPO�ޯ�����ިݸސ\������ڽ��ݽ
        regClass(WEB3AdminIPOBookBuildingHistoryResponse.class);

        //�Ǘ���IPO���~�m�F���N�G�X�g
        regClass(WEB3AdminIPOCancelConfirmRequest.class);
        //�Ǘ���IPO���~�m�F���X�|���X
        regClass(WEB3AdminIPOCancelConfirmResponse.class);

        //�Ǘ���IPO���~�������N�G�X�g
        regClass(WEB3AdminIPOCancelCompleteRequest.class);
        //�Ǘ���IPO���~�������X�|���X
        regClass(WEB3AdminIPOCancelCompleteResponse.class);

        //�Ǘ���IPO���I���ʱ���۰�ފm�Fظ���
        regClass(WEB3AdminIPOLotResultUploadConfirmRequest.class);
        //�Ǘ���IPO���I���ʱ���۰�ފm�Fڽ��ݽ
        regClass(WEB3AdminIPOLotResultUploadConfirmResponse.class);

        //�Ǘ���IPO���I���ʱ���۰�ފ���ظ���
        regClass(WEB3AdminIPOLotResultUploadCompleteRequest.class);
        //�Ǘ���IPO���I���ʱ���۰�ފ���ڽ��ݽ
        regClass(WEB3AdminIPOLotResultUploadCompleteResponse.class);

        //�Ǘ���IPO���I���ʱ���۰�ޒ��~ظ���
        regClass(WEB3AdminIPOLotResultUploadCancelRequest.class);
        //�Ǘ���IPO���I���ʱ���۰�ޒ��~ڽ��ݽ
        regClass(WEB3AdminIPOLotResultUploadCancelResponse.class);

        //�Ǘ���IPO���I���ʱ���۰�ޓ���ظ���
        regClass(WEB3AdminIPOLotResultUploadInputRequest.class);
        //�Ǘ���IPO���I���ʱ���۰�ޓ���ڽ��ݽ
        regClass(WEB3AdminIPOLotResultUploadInputResponse.class);

        //�Ǘ���IPO���I���ʍw���\�����޳�۰��ظ���
        regClass(WEB3AdminIPOLotResultOfferDownloadRequest.class);
        //�Ǘ���IPO���I���ʍw���\�����޳�۰��ڽ��ݽ
        regClass(WEB3AdminIPOLotResultOfferDownloadResponse.class);

        //�Ǘ���IPO���I���ʍw���\����̧���޳�۰���ظ���
        regClass(WEB3AdminIPOLotResultOfferFileDownloadRequest.class);
        //�Ǘ���IPO���I���ʍw���\����̧���޳�۰��ڽ��ݽ
        regClass(WEB3AdminIPOLotResultOfferFileDownloadResponse.class);

        //�Ǘ���IPO���I���ʍw���\����ظ���
        regClass(WEB3AdminIPOLotResultOfferRequest.class);
        //�Ǘ���IPO���I���ʍw���\����ڽ��ݽ
        regClass(WEB3AdminIPOLotResultOfferResponse.class);

        //�Ǘ���IPO���I���ʍw���\���󋵈ꗗظ���
        regClass(WEB3AdminIPOLotResultOfferListRequest.class);
        //�Ǘ���IPO���I���ʍw���\���󋵈ꗗڽ��ݽ
        regClass(WEB3AdminIPOLotResultOfferListResponse.class);

        //�Ǘ���IPO��W��~�ĊJ�m�F���N�G�X�g
        regClass(WEB3AdminIPOOfferStopResumeConfirmRequest.class);
        //�Ǘ���IPO��W��~�ĊJ�m�F���X�|���X
        regClass(WEB3AdminIPOOfferStopResumeConfirmResponse.class);

        //�Ǘ���IPO��W��~�ĊJ�������N�G�X�g 
        regClass(WEB3AdminIPOOfferStopResumeCompleteRequest.class);
        //�Ǘ���IPO��W��~�ĊJ�������X�|���X
        regClass(WEB3AdminIPOOfferStopResumeCompleteResponse.class);

        //�Ǘ���IPO�����Ǘ����N�G�X�g
        regClass(WEB3AdminIPOManagementRequest.class);
        //�Ǘ���IPO�����Ǘ����X�|���X
        regClass(WEB3AdminIPOManagementResponse.class);

        //�Ǘ���IPO�����폜�m�F���N�G�X�g
        regClass(WEB3AdminIPOProductDeleteConfirmRequest.class);
        //�Ǘ���IPO�����폜�m�F���X�|���X
        regClass(WEB3AdminIPOProductDeleteConfirmResponse.class);

        //�Ǘ���IPO�����폜�������N�G�X�g
        regClass(WEB3AdminIPOProductDeleteCompleteRequest.class);
        //�Ǘ���IPO�����폜�������X�|���X
        regClass(WEB3AdminIPOProductDeleteCompleteResponse.class);

        //�Ǘ���IPO�����ڍ׃��N�G�X�g
        regClass(WEB3AdminIPOProductDetailsRequest.class);
        //�Ǘ���IPO�����ڍ׃��X�|���X
        regClass(WEB3AdminIPOProductDetailsResponse.class);

        //�Ǘ���IPO�����V�K�o�^�m�F���N�G�X�g
        regClass(WEB3AdminIPOProductRegistrationConfirmRequest.class);
        //�Ǘ���IPO�����V�K�o�^�m�F���X�|���X
        regClass(WEB3AdminIPOProductRegistrationConfirmResponse.class);

        //�Ǘ���IPO�����V�K�o�^�������N�G�X�g
        regClass(WEB3AdminIPOProductRegistrationCompleteRequest.class);
        //�Ǘ���IPO�����V�K�o�^�������X�|���X
        regClass(WEB3AdminIPOProductRegistrationCompleteResponse.class);

        //�Ǘ���IPO�����V�K�o�^���̓��N�G�X�g
        regClass(WEB3AdminIPOProductRegistrationInputRequest.class);
        //�Ǘ���IPO�����V�K�o�^���̓��X�|���X
        regClass(WEB3AdminIPOProductRegistrationInputResponse.class);

        //�Ǘ���IPO���������m�F���N�G�X�g
        regClass(WEB3AdminIPOProductChangeConfirmRequest.class);
        //�Ǘ���IPO���������m�F���X�|���X
        regClass(WEB3AdminIPOProductChangeConfirmResponse.class);

        //�Ǘ���IPO���������������N�G�X�g
        regClass(WEB3AdminIPOProductChangeCompleteRequest.class);
        //�Ǘ���IPO���������������X�|���X
        regClass(WEB3AdminIPOProductChangeCompleteResponse.class);

        //�Ǘ���IPO�����������̓��N�G�X�g
        regClass(WEB3AdminIPOProductChangeInputRequest.class);
        //�Ǘ���IPO�����������̓��X�|���X
        regClass(WEB3AdminIPOProductChangeInputResponse.class);

        //�Ǘ���IPO���I�������̓��N�G�X�g
        regClass(WEB3AdminIPOLotInputRequest.class);
        //�Ǘ���IPO���I�������̓��X�|���X
        regClass(WEB3AdminIPOLotInputResponse.class);
        //�Ǘ���IPO���I�����m�F���N�G�X�g
        regClass(WEB3AdminIPOLotConfirmRequest.class);
        //�Ǘ���IPO���I�����m�F���X�|���X
        regClass(WEB3AdminIPOLotConfirmResponse.class);
        //�Ǘ���IPO���I�����������N�G�X�g
        regClass(WEB3AdminIPOLotCompleteRequest.class);
        //�Ǘ���IPO���I�����������X�|���X
        regClass(WEB3AdminIPOLotCompleteResponse.class);
        //�Ǘ���IPO���I�������ʃ��N�G�X�g
        regClass(WEB3IPOLotCommonRequest.class);
        //�Ǘ���IPO���I�������ʃ��X�|���X
        regClass(WEB3IPOLotCommonResponse.class);
        
        //IPO�d�q��URL�擾���N�G�X�g
        regClass(WEB3IPOBatoUrlRequest.class);
        //IPO�d�q��URL�擾���X�|���X
        regClass(WEB3IPOBatoUrlResponse.class);

        //Handler �̓o�^���� ----------------------

        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AdminIPOBookBuildingHistoryRequest.class,
            WEB3AdminIpoBookbuildingStateDownloadHandler.class,
            "bookbuildingOrderActionIndication");

        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AdminIPOBookBuildingStateFileDownloadRequest.class,
            WEB3AdminIpoBookbuildingStateDownloadHandler.class,
            "bookbuildingStateDownload");

        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AdminIPOBookBuildingStateDownloadRequest.class,
            WEB3AdminIpoBookbuildingStateDownloadHandler.class,
            "bookbuildingStateDownloadScreenIndication");

        //�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AdminIPOBookBuildingDemandMapRequest.class,
            WEB3AdminIpoBookbuildingStateDownloadHandler.class,
            "orderDistributionChartScreenIndication");

        //�Ǘ���IPO���~ �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOCancelCompleteRequest.class, WEB3AdminIpoCancelHandler.class, "ipoCancelComplete");

        //�Ǘ���IPO���~ �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOCancelConfirmRequest.class, WEB3AdminIpoCancelHandler.class, "ipoCancelConfirm");

        //�Ǘ���IPO���I���ʍw���\�����޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AdminIPOLotResultOfferFileDownloadRequest.class,
            WEB3AdminIpoLotResultOfferDownloadHandler.class,
            "lotResultOfferStateDownload");

        //�Ǘ���IPO���I���ʍw���\�����޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AdminIPOLotResultOfferDownloadRequest.class,
            WEB3AdminIpoLotResultOfferDownloadHandler.class,
            "lotResultOfferStateDownloadScreenIndication");

        //�Ǘ���IPO���I���ʍw���\�����޳�۰�� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotResultOfferRequest.class, WEB3AdminIpoLotResultOfferDownloadHandler.class, "lotResultOfferStateIndication");

        //�Ǘ���IPO���I���ʍw���\�����޳�۰�� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotResultOfferListRequest.class, WEB3AdminIpoLotResultOfferDownloadHandler.class, "lotResultOfferStateListIndication");

        //�Ǘ���IPO���I���ʱ���۰�� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotResultUploadCompleteRequest.class, WEB3AdminIpoLotResultUploadHandler.class, "lotResultUpload");

        //�Ǘ���IPO���I���ʱ���۰�� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotResultUploadCancelRequest.class, WEB3AdminIpoLotResultUploadHandler.class, "lotResultUploadCancel");

        //�Ǘ���IPO���I���ʱ���۰�� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotResultUploadConfirmRequest.class, WEB3AdminIpoLotResultUploadHandler.class, "lotResultUploadFileConfirm");

        //�Ǘ���IPO���I���ʱ���۰�� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotResultUploadInputRequest.class, WEB3AdminIpoLotResultUploadHandler.class, "lotResultUploadScreenIndication");

        //�Ǘ���IPO�����Ǘ��E�ڍ� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductDetailsRequest.class, WEB3AdminIpoManagementDetailsHandler.class, "productDetailsIndication");

        //�Ǘ���IPO�����Ǘ��E�ڍ� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOManagementRequest.class, WEB3AdminIpoManagementDetailsHandler.class, "productManagement");

        //�Ǘ���IPO��W��~�ĊJ �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOOfferStopResumeCompleteRequest.class, WEB3AdminIpoOfferStopResumeHandler.class, "offerStopResumeComplete");

        //�Ǘ���IPO��W��~�ĊJ �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOOfferStopResumeConfirmRequest.class, WEB3AdminIpoOfferStopResumeHandler.class, "offerStopResumeConfirm");

        //�Ǘ���IPO�������� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductChangeInputRequest.class, WEB3AdminIpoProductChangeHandler.class, "inputScreenIndication");

        //�Ǘ���IPO�������� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductChangeCompleteRequest.class, WEB3AdminIpoProductChangeHandler.class, "productChangeComplete");

        //�Ǘ���IPO�������� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductChangeConfirmRequest.class, WEB3AdminIpoProductChangeHandler.class, "productChangeConfirm");

        //�Ǘ���IPO�����폜 �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductDeleteCompleteRequest.class, WEB3AdminIpoProductDeleteHandler.class, "productDeleteComplete");

        //�Ǘ���IPO�����폜 �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductDeleteConfirmRequest.class, WEB3AdminIpoProductDeleteHandler.class, "productDeleteConfirm");

        //�Ǘ���IPO�����o�^ �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductRegistrationInputRequest.class, WEB3AdminIpoProductRegistrationHandler.class, "inputScreenIndication");

        //�Ǘ���IPO�����o�^ �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductRegistrationCompleteRequest.class, WEB3AdminIpoProductRegistrationHandler.class, "productRegistrationComplete");

        //�Ǘ���IPO�����o�^ �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOProductRegistrationConfirmRequest.class, WEB3AdminIpoProductRegistrationHandler.class, "productRegistrationConfirm");

        //IPO�u�b�N�r���f�B���O��� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingCancelCompleteRequest.class, WEB3IpoBookbuildingCancelHandler.class, "bookbuildingCancelComplete");

        //IPO�u�b�N�r���f�B���O��� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingCancelConfirmRequest.class, WEB3IpoBookbuildingCancelHandler.class, "bookbuildingCancelConfirm");

        //IPO�u�b�N�r���f�B���O���� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingChangeCompleteRequest.class, WEB3IpoBookbuildingChangeHandler.class, "bookbuildingChangeComplete");

        //IPO�u�b�N�r���f�B���O���� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingChangeConfirmRequest.class, WEB3IpoBookbuildingChangeHandler.class, "bookbuildingChangeConfirm");

        //IPO�u�b�N�r���f�B���O���� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingChangeInputRequest.class, WEB3IpoBookbuildingChangeHandler.class, "inputScreenIndication");

        //IPO�u�b�N�r���f�B���O�\�� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingDemandCompleteRequest.class, WEB3IpoBookbuildingOrderHandler.class, "bookbuildingOrderComplete");

        //IPO�u�b�N�r���f�B���O�\�� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingDemandConfirmRequest.class, WEB3IpoBookbuildingOrderHandler.class, "bookbuildingOrderConfirm");

        //IPO�u�b�N�r���f�B���O�\�� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingDemandInputRequest.class, WEB3IpoBookbuildingOrderHandler.class, "inputScreenIndication");

        //IPO�u�b�N�r���f�B���O�Q�� �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingEnterRequest.class, WEB3IpoBookbuildingEnterHandler.class, "bookbuildingEnter");

        //IPO�u�b�N�r���f�B���O�Q�� �p�n���h���[�̓o�^
        regHandler(WEB3IPOProductInfoRequest.class, WEB3IpoBookbuildingEnterHandler.class, "individualProductInfoIndication");

        //IPO���� �p�n���h���[�̓o�^
        regHandler(WEB3IPODeclineCompleteRequest.class, WEB3IpoDeclineHandler.class, "declineComplete");

        //IPO���� �p�n���h���[�̓o�^
        regHandler(WEB3IPODeclineConfirmRequest.class, WEB3IpoDeclineHandler.class, "declineConfirm");

        //IPO�\���E�w���\���ꗗ �p�n���h���[�̓o�^
        regHandler(WEB3IPOBookBuildingHistoryRequest.class, WEB3IpoOrderOfferListHandler.class, "bookbuildingAction");

        //IPO�\���E�w���\���ꗗ �p�n���h���[�̓o�^
        regHandler(WEB3IPODemandOfferRequest.class, WEB3IpoOrderOfferListHandler.class, "orderOfferList");

        //IPO�w���\�� �p�n���h���[�̓o�^
        regHandler(WEB3IPOOfferCompleteRequest.class, WEB3IpoOfferHandler.class, "offerComplete");

        //IPO�w���\�� �p�n���h���[�̓o�^
        regHandler(WEB3IPOOfferConfirmRequest.class, WEB3IpoOfferHandler.class, "offerConfirm");

        //IPO�w���\�� �p�n���h���[�̓o�^
        regHandler(WEB3IPOOfferInputRequest.class, WEB3IpoOfferHandler.class, "offerInput");
        
        //�Ǘ���IPO���I���� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotInputRequest.class, WEB3AdminIpoLotHandler.class, "getLotInput");
        
        //�Ǘ���IPO���I���� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotConfirmRequest.class, WEB3AdminIpoLotHandler.class, "getLotConfirm");
        
        //�Ǘ���IPO���I���� �p�n���h���[�̓o�^
        regHandler(WEB3AdminIPOLotCompleteRequest.class, WEB3AdminIpoLotHandler.class, "getLotComplete");
        
        //IPO�d�q���ڑ��T�[�r�X
        regHandler(WEB3IPOBatoUrlRequest.class, WEB3IpoBatoClientHandler.class, "getBatoUrl");

        log.exiting(STR_METHOD_NAME);
    }
}@
