head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-�T�[�r�X���p �v���O�C��(WEB3SrvRegiAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���� (���u) �V�K�쐬
Revesion History : 2008/02/18 ���g (���u) ���f��310
Revesion History : 2008/03/13 ���g (���u) ���f��335,336,337,338
Revesion History : 2008/05/22 �g�C�� (���u) ���f��371
*/

package webbroker3.srvregi;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.srvregi.data.WEB3SrvRegiMasterDatabaseExtensions;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountAppliStateListHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountChangeHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountChangeInputHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountDataDownloadHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountDataUlStateInquiryHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountDataUploadHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountListChangeInquiryHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountRegistHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdDownloadHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdListHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdUploadHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceActionInfoHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceBidPriceUpdateHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceChangeHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceChangeInputHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceDetailHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceRegiHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiSrvListHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiApplicationInputHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiCancelHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiServiceListInquiryHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiServiceStartHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiServiceUseApplicationHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiStreamHandler;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCommonRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputResponse;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse;
import webbroker3.srvregi.message.WEB3SrvRegiConsentRequest;
import webbroker3.srvregi.message.WEB3SrvRegiConsentResponse;
import webbroker3.srvregi.message.WEB3SrvRegiExecRequest;
import webbroker3.srvregi.message.WEB3SrvRegiExecResponse;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceRequest;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountAppliStateListService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeInputService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataDownloadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUlStateInquiryService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountListChangeInquiryService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountRegistService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdDownloadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdListService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceActionInfoService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateInputService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeInputService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceDetailService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceRegiService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiSrvListService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiApplicationInputService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiCancelService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiExecSendMailService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceListInquiryService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceStartService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceUseApplicationService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStreamService;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountAppliStateListServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountChangeInputServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountChangeServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataDownloadServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountRegistServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdListServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceActionInfoServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceChangeInputServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceChangeServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceDetailServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceRegiServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiSrvListServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiApplicationInputServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiCancelServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiExecSendMailServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiOtherOrgServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceListInquiryServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceStartServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceUseApplicationServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiStartInfoServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiStreamServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-�T�[�r�X���p �v���O�C���N���X�B
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public final class WEB3SrvRegiAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3SrvRegiAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3SrvRegiAppPlugin()";
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

        plug(WEB3SrvRegiAppPlugin.class);

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

        // DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3SrvRegiMasterDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------

        //�T�[�r�X���p�m�F���[�����M�T�[�r�X
        Services.registerService(WEB3SrvRegiExecSendMailService.class, new WEB3SrvRegiExecSendMailServiceImpl());

        //�T�[�r�X���p�O���A�g�T�[�r�X
        Services.registerService(WEB3SrvRegiOtherOrgService.class, new WEB3SrvRegiOtherOrgServiceImpl());

        //�T�[�r�X���p�\���o�^�T�[�r�X
        Services.registerService(WEB3SrvRegiRegistService.class, new WEB3SrvRegiRegistServiceImpl());
        
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X
        Services.registerService(WEB3AdminSrvRegiSrvListService.class, new WEB3AdminSrvRegiSrvListServiceImpl());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiServiceDetailService.class,
            new WEB3AdminSrvRegiServiceDetailServiceImpl());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiServiceRegiService.class,
            new WEB3AdminSrvRegiServiceRegiServiceImpl());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiServiceChangeService.class,
            new WEB3AdminSrvRegiServiceChangeServiceImpl());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���̓T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiServiceChangeInputService.class,
            new WEB3AdminSrvRegiServiceChangeInputServiceImpl());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�������T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiServiceActionInfoService.class,
            new WEB3AdminSrvRegiServiceActionInfoServiceImpl());

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiAccountDataUploadService.class,
            new WEB3AdminSrvRegiAccountDataUploadServiceImpl());

        //�T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitService
        Services.registerService(
            WEB3AdminSrvRegiAccountDataUploadUnitService.class,
            new WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl());

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiAccountDataUlStateInquiryService.class,
            new WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl());

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiAccountDataDownloadService.class,
            new WEB3AdminSrvRegiAccountDataDownloadServiceImpl());

        //�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiAccountListChangeInquiryService.class,
            new WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl());

        //�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiAccountAppliStateListService.class,
            new WEB3AdminSrvRegiAccountAppliStateListServiceImpl());

        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiAccountRegistService.class,
            new WEB3AdminSrvRegiAccountRegistServiceImpl());

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiAccountChangeService.class,
            new WEB3AdminSrvRegiAccountChangeServiceImpl());

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiAccountChangeInputService.class,
            new WEB3AdminSrvRegiAccountChangeInputServiceImpl());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiServiceBidPriceUpdateService.class,
            new WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓T�[�r�X
        Services.registerService(
            WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class,
            new WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl());

        //�T�[�r�X���p����T�[�r�X
        Services.registerService(WEB3SrvRegiCancelService.class, new WEB3SrvRegiCancelServiceImpl());

        //�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X
        Services.registerService(
            WEB3SrvRegiServiceListInquiryService.class,
            new WEB3SrvRegiServiceListInquiryServiceImpl());

        //�T�[�r�X���p�T�[�r�X�N���T�[�r�X
        Services.registerService(WEB3SrvRegiServiceStartService.class, new WEB3SrvRegiServiceStartServiceImpl());

        //�T�[�r�X���p�\���T�[�r�X
        Services.registerService(
            WEB3SrvRegiServiceUseApplicationService.class,
            new WEB3SrvRegiServiceUseApplicationServiceImpl());

        //�T�[�r�X���p�\�����̓T�[�r�X
        Services.registerService(
            WEB3SrvRegiApplicationInputService.class,
            new WEB3SrvRegiApplicationInputServiceImpl());
            
        //�T�[�r�X���p�N�����T�[�r�X
        Services.registerService(
            WEB3SrvRegiStartInfoService.class,
            new WEB3SrvRegiStartInfoServiceImpl());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖﻰ�޽
        Services.registerService(
            WEB3AdminSrvRegiOtherOrgIdListService.class,
            new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitService
        Services.registerService(
            WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class,
            new WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽
        Services.registerService(
            WEB3AdminSrvRegiOtherOrgIdUploadService.class,
            new WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽
        Services.registerService(
            WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
            new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl());

        //�T�[�r�X���p���A�g�T�[�r�X
        Services.registerService(
            WEB3SrvRegiStreamService.class,
            new WEB3SrvRegiStreamServiceImpl());

		//Service �� ServiceInterceptor ��ݒ肷�� ----------------------

		//�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X
		Services.addInterceptor(WEB3AdminSrvRegiSrvListService.class, new WEB3AdminSrvRegiSrvListServiceInterceptor());

		//�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceDetailService.class,
			new WEB3AdminSrvRegiServiceDetailServiceInterceptor());

		//�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceRegiService.class,
			new WEB3AdminSrvRegiServiceRegiServiceInterceptor());

		//�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceChangeService.class,
			new WEB3AdminSrvRegiServiceChangeServiceInterceptor());

		//�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���̓T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceChangeInputService.class,
			new WEB3AdminSrvRegiServiceChangeInputServiceInterceptor());

		//�T�[�r�X���p�Ǘ��҃T�[�r�X�������T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceActionInfoService.class,
			new WEB3AdminSrvRegiServiceActionInfoServiceInterceptor());

		//�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountDataUploadService.class,
			new WEB3AdminSrvRegiAccountDataUploadInterceptor());

		//�T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitService
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountDataUploadUnitService.class,
			new WEB3AdminSrvRegiAccountDataUploadInterceptor());

		//�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountDataDownloadService.class,
			new WEB3AdminSrvRegiAccountDataDownloadServiceInterceptor());

		//�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountDataUlStateInquiryService.class,
			new WEB3AdminSrvRegiAccountDataUlStateInquiryServiceInterceptor());

		//�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountListChangeInquiryService.class,
			new WEB3AdminSrvRegiAccountListChangeInquiryServiceInterceptor());

		//�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountAppliStateListService.class,
			new WEB3AdminSrvRegiAccountAppliStateListServiceInterceptor());

		//�T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountRegistService.class,
			new WEB3AdminSrvRegiAccountRegistServiceInterceptor());

		//�T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountChangeService.class,
			new WEB3AdminSrvRegiAccountChangeServiceInterceptor());

		//�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountChangeInputService.class,
			new WEB3AdminSrvRegiAccountChangeInputServiceInterceptor());

		//�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceBidPriceUpdateService.class,
			new WEB3AdminSrvRegiServiceBidPriceUpdateServiceInterceptor());

		//�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓T�[�r�X
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class,
			new WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceInterceptor());

		//�T�[�r�X���p����T�[�r�X
		Services.addInterceptor(WEB3SrvRegiCancelService.class, new WEB3SrvRegiCancelServiceInterceptor());

		//�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X
		Services.addInterceptor(
			WEB3SrvRegiServiceListInquiryService.class,
			new WEB3SrvRegiServiceListInquiryServiceInterceptor());

		//�T�[�r�X���p�T�[�r�X�N���T�[�r�X
		Services.addInterceptor(WEB3SrvRegiServiceStartService.class, new WEB3SrvRegiServiceStartServiceInterceptor());

		//�T�[�r�X���p�\���T�[�r�X
		Services.addInterceptor(
			WEB3SrvRegiServiceUseApplicationService.class,
			new WEB3SrvRegiServiceUseApplicationServiceInterceptor());

		//�T�[�r�X���p�\�����̓T�[�r�X
		Services.addInterceptor(
			WEB3SrvRegiApplicationInputService.class,
			new WEB3SrvRegiApplicationInputServiceInterceptor());    

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖﻰ�޽
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdListService.class,
            new WEB3AdminSrvRegiOtherOrgIdListServiceInterceptor());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadService.class,
            new WEB3AdminSrvRegiOtherOrgIdUploadServiceInterceptor());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitService
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class,
            new WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceInterceptor());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
            new WEB3AdminSrvRegiOtherOrgIdDownloadServiceInterceptor());

        //�T�[�r�X���p���A�g�T�[�r�X
        Services.addInterceptor(
            WEB3SrvRegiStreamService.class,
            new WEB3SrvRegiStreamServiceInterceptor());

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiSrvListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceDetailService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceRegiService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceChangeInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceActionInfoService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitService
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataUploadUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataUlStateInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountListChangeInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountAppliStateListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountChangeInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceBidPriceUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p����T�[�r�X
        Services.addInterceptor(
            WEB3SrvRegiCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3SrvRegiServiceListInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�T�[�r�X�N���T�[�r�X
        Services.addInterceptor(
            WEB3SrvRegiServiceStartService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�\���T�[�r�X
        Services.addInterceptor(
            WEB3SrvRegiServiceUseApplicationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�\�����̓T�[�r�X
        Services.addInterceptor(
            WEB3SrvRegiApplicationInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖﻰ�޽
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitService
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�T�[�r�X���p���A�g�T�[�r�X
        Services.addInterceptor(
            WEB3SrvRegiStreamService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

        //�T�[�r�X���p�m�F���[�����M�T�[�r�X
        Services.addInterceptor(WEB3SrvRegiExecSendMailService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�O���A�g�T�[�r�X
        Services.addInterceptor(WEB3SrvRegiOtherOrgService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�\���o�^�T�[�r�X
        Services.addInterceptor(WEB3SrvRegiRegistService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiSrvListService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiServiceDetailService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiServiceRegiService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiServiceChangeService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���̓T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiServiceChangeInputService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�������T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiServiceActionInfoService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiAccountDataUploadService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitService
        Services.addInterceptor(WEB3AdminSrvRegiAccountDataUploadUnitService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataUlStateInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiAccountDataDownloadService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiAccountListChangeInquiryService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiAccountAppliStateListService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiAccountRegistService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiAccountChangeService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiAccountChangeInputService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminSrvRegiServiceBidPriceUpdateService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p����T�[�r�X
        Services.addInterceptor(WEB3SrvRegiCancelService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X
        Services.addInterceptor(WEB3SrvRegiServiceListInquiryService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�T�[�r�X�N���T�[�r�X
        Services.addInterceptor(WEB3SrvRegiServiceStartService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�\���T�[�r�X
        Services.addInterceptor(WEB3SrvRegiServiceUseApplicationService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�\�����̓T�[�r�X
        Services.addInterceptor(WEB3SrvRegiApplicationInputService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖﻰ�޽
        Services.addInterceptor(WEB3AdminSrvRegiOtherOrgIdListService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitService
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�޻��޽
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdDownloadService.class, new WEB3LogSysTimeInterceptor());

        //�T�[�r�X���p���A�g�T�[�r�X
        Services.addInterceptor(
            WEB3SrvRegiStreamService.class, new WEB3LogSysTimeInterceptor());

        // Message �̓o�^���� ----------------------

        //�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�N�G�X�g
        regClass(WEB3SrvRegiReferenceRequest.class);
        //�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�X�|���X
        regClass(WEB3SrvRegiReferenceResponse.class);

        //�T�[�r�X���p�T�[�r�X�N�����N�G�X�g
        regClass(WEB3SrvRegiExecRequest.class);
        //�T�[�r�X���p�T�[�r�X�N�����X�|���X
        regClass(WEB3SrvRegiExecResponse.class);

        //�T�[�r�X���p����m�F���N�G�X�g
        regClass(WEB3SrvRegiCancelConfirmRequest.class);
        //�T�[�r�X���p����m�F���X�|���X
        regClass(WEB3SrvRegiCancelConfirmResponse.class);

        //�T�[�r�X���p����������N�G�X�g
        regClass(WEB3SrvRegiCancelCompleteRequest.class);
        //�T�[�r�X���p����������X�|���X
        regClass(WEB3SrvRegiCancelCompleteResponse.class);

        //�T�[�r�X���p�\���m�F���N�G�X�g
        regClass(WEB3SrvRegiApplyConfirmRequest.class);
        //�T�[�r�X���p�\���m�F���X�|���X
        regClass(WEB3SrvRegiApplyConfirmResponse.class);

        //�T�[�r�X���p�\���������N�G�X�g
        regClass(WEB3SrvRegiApplyCompleteRequest.class);
        //�T�[�r�X���p�\���������X�|���X
        regClass(WEB3SrvRegiApplyCompleteResponse.class);

        //�T�[�r�X���p�\�����ʃ��N�G�X�g
        regClass(WEB3SrvRegiApplyCommonRequest.class);

        //�T�[�r�X���p�\�����̓��N�G�X�g
        regClass(WEB3SrvRegiApplyInputRequest.class);
        //�T�[�r�X���p�\�����̓��X�|���X
        regClass(WEB3SrvRegiApplyInputResponse.class);

        //�T�[�r�X���p���ӏ����N�G�X�g
        regClass(WEB3SrvRegiConsentRequest.class);
        //�T�[�r�X���p���ӏ����X�|���X
        regClass(WEB3SrvRegiConsentResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ���N�G�X�g
        regClass(WEB3AdminSrvRegiServiceReferenceRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ���X�|���X
        regClass(WEB3AdminSrvRegiServiceReferenceResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��N�G�X�g
        regClass(WEB3AdminSrvRegiServiceDetailsRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��X�|���X
        regClass(WEB3AdminSrvRegiServiceDetailsResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�m�F���N�G�X�g
        regClass(WEB3AdminSrvRegiServiceRegistConfirmRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�m�F���X�|���X
        regClass(WEB3AdminSrvRegiServiceRegistConfirmResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�������N�G�X�g
        regClass(WEB3AdminSrvRegiServiceRegistCompleteRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�������X�|���X
        regClass(WEB3AdminSrvRegiServiceRegistCompleteResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^���ʃ��N�G�X�g
        regClass(WEB3AdminSrvRegiServiceRegistCommonRequest.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���N�G�X�g
        regClass(WEB3AdminSrvRegiServiceChangeConfirmRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���X�|���X
        regClass(WEB3AdminSrvRegiServiceChangeConfirmResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�������N�G�X�g
        regClass(WEB3AdminSrvRegiServiceChangeCompleteRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�������X�|���X
        regClass(WEB3AdminSrvRegiServiceChangeCompleteResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���ʃ��N�G�X�g
        regClass(WEB3AdminSrvRegiServiceChangeCommonRequest.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���̓��N�G�X�g
        regClass(WEB3AdminSrvRegiServiceChangeInputRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���̓��X�|���X
        regClass(WEB3AdminSrvRegiServiceChangeInputResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�m�F���N�G�X�g
        regClass(WEB3AdminSrvRegiSuccBidConfirmRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�m�F���X�|���X
        regClass(WEB3AdminSrvRegiSuccBidConfirmResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�������N�G�X�g
        regClass(WEB3AdminSrvRegiSuccBidCompleteRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�������X�|���X
        regClass(WEB3AdminSrvRegiSuccBidCompleteResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���ʃ��N�G�X�g
        regClass(WEB3AdminSrvRegiSuccBidCommonRequest.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓��N�G�X�g
        regClass(WEB3AdminSrvRegiSuccBidInputRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓��X�|���X
        regClass(WEB3AdminSrvRegiSuccBidInputResponse.class);

        //�T�[�r�X���p�Ǘ��҃T�[�r�X������񃊃N�G�X�g
        regClass(WEB3AdminSrvRegiServiceHistoryRequest.class);
        //�T�[�r�X���p�Ǘ��҃T�[�r�X������񃌃X�|���X
        regClass(WEB3AdminSrvRegiServiceHistoryResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�N�G�X�g
        regClass(WEB3AdminSrvRegiUploadStateRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�X�|���X
        regClass(WEB3AdminSrvRegiUploadStateResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�m�F���N�G�X�g
        regClass(WEB3AdminSrvRegiUploadConfirmRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�m�F���X�|���X
        regClass(WEB3AdminSrvRegiUploadConfirmResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�������N�G�X�g
        regClass(WEB3AdminSrvRegiUploadCompleteRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�������X�|���X
        regClass(WEB3AdminSrvRegiUploadCompleteResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���~���N�G�X�g
        regClass(WEB3AdminSrvRegiUploadCancelRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���~���X�|���X
        regClass(WEB3AdminSrvRegiUploadCancelResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���̓��N�G�X�g
        regClass(WEB3AdminSrvRegiUploadInputRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���̓��X�|���X
        regClass(WEB3AdminSrvRegiUploadInputResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h���N�G�X�g
        regClass(WEB3AdminSrvRegiDownloadRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h���X�|���X
        regClass(WEB3AdminSrvRegiDownloadResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�N�G�X�g
        regClass(WEB3AdminSrvRegiCustomerReferenceRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�X�|���X
        regClass(WEB3AdminSrvRegiCustomerReferenceResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ���N�G�X�g
        regClass(WEB3AdminSrvRegiStateRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ���X�|���X
        regClass(WEB3AdminSrvRegiStateResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^�m�F���N�G�X�g
        regClass(WEB3AdminSrvRegiCustomerRegistConfirmRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^�m�F���X�|���X
        regClass(WEB3AdminSrvRegiCustomerRegistConfirmResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^�������N�G�X�g
        regClass(WEB3AdminSrvRegiCustomerRegistCompleteRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^�������X�|���X
        regClass(WEB3AdminSrvRegiCustomerRegistCompleteResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^���ʃ��N�G�X�g
        regClass(WEB3AdminSrvRegiCustomerRegistCommonRequest.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX�m�F���N�G�X�g
        regClass(WEB3AdminSrvRegiCustomerChangeConfirmRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX�m�F���X�|���X
        regClass(WEB3AdminSrvRegiCustomerChangeConfirmResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX�������N�G�X�g
        regClass(WEB3AdminSrvRegiCustomerChangeCompleteRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX�������X�|���X
        regClass(WEB3AdminSrvRegiCustomerChangeCompleteResponse.class);

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��N�G�X�g
        regClass(WEB3AdminSrvRegiCustomerChangeInputRequest.class);
        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��X�|���X
        regClass(WEB3AdminSrvRegiCustomerChangeInputResponse.class);

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��ظ���
        regClass(WEB3AdminSrvRegiOtherOrgIdCommonRequest.class);

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ظ���
        regClass(WEB3AdminSrvRegiOtherOrgIdDownloadRequest.class);
        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ڽ��ݽ
        regClass(WEB3AdminSrvRegiOtherOrgIdDownloadResponse.class);

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ظ���
        regClass(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.class);
        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ڽ��ݽ
        regClass(WEB3AdminSrvRegiOtherOrgIdListReferenceResponse.class);

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ظ���
        regClass(WEB3AdminSrvRegiOtherOrgIdListSearchRequest.class);
        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ڽ��ݽ
        regClass(WEB3AdminSrvRegiOtherOrgIdListSearchResponse.class);

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~ظ���
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest.class);
        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޒ��~ڽ��ݽ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse.class);

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ���ظ���
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.class);
        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ���ڽ��ݽ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse.class);

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fظ���
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.class);
        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fڽ��ݽ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse.class);

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ���ظ���
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadInputRequest.class);
        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ޓ���ڽ��ݽ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadInputResponse.class);

        //�T�[�r�X���p���A�gظ���
        regClass(WEB3SrvRegiStreamRequest.class);
        //�T�[�r�X���p���A�gڽ��ݽ
        regClass(WEB3SrvRegiStreamResponse.class);

        //Handler �̓o�^���� ----------------------

        //�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiStateRequest.class,
            WEB3AdminSrvRegiAccountAppliStateListHandler.class,
            "searchAccountAppliState");

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerChangeConfirmRequest.class,
            WEB3AdminSrvRegiAccountChangeHandler.class,
            "confirmAccountChange");

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerChangeCompleteRequest.class,
            WEB3AdminSrvRegiAccountChangeHandler.class,
            "completeAccountChange");

        //�T�[�r�X���p�Ǘ��Ҍڋq�ύX���� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerChangeInputRequest.class,
            WEB3AdminSrvRegiAccountChangeInputHandler.class,
            "mainAccountChangeInputRequest");

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiDownloadRequest.class,
            WEB3AdminSrvRegiAccountDataDownloadHandler.class,
            "acountDownload");

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadStateRequest.class,
            WEB3AdminSrvRegiAccountDataUlStateInquiryHandler.class,
            "accountDataUploadStateInqueryRequest");

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadCancelRequest.class,
            WEB3AdminSrvRegiAccountDataUploadHandler.class,
            "accountUploadDiscontinuation");

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadConfirmRequest.class,
            WEB3AdminSrvRegiAccountDataUploadHandler.class,
            "accountUploadConfirm");

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadCompleteRequest.class,
            WEB3AdminSrvRegiAccountDataUploadHandler.class,
            "accountUpload");

        //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadInputRequest.class,
            WEB3AdminSrvRegiAccountDataUploadHandler.class,
            "accountUploadScreenIndication");

        //�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerReferenceRequest.class,
            WEB3AdminSrvRegiAccountListChangeInquiryHandler.class,
            "searchAccountAppliSrv");

        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerRegistConfirmRequest.class,
            WEB3AdminSrvRegiAccountRegistHandler.class,
            "confirmAccountRegist");

        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerRegistCompleteRequest.class,
            WEB3AdminSrvRegiAccountRegistHandler.class,
            "completeAccountRegist");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X������� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceHistoryRequest.class,
            WEB3AdminSrvRegiServiceActionInfoHandler.class,
            "searchSrvAction");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiSuccBidConfirmRequest.class,
            WEB3AdminSrvRegiServiceBidPriceUpdateHandler.class,
            "confirmBidPriceUpdate");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiSuccBidCompleteRequest.class,
            WEB3AdminSrvRegiServiceBidPriceUpdateHandler.class,
            "completeBidPriceUpdate");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiSuccBidInputRequest.class,
            WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler.class,
            "bidPriceUpdateInputRequest");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceChangeConfirmRequest.class,
            WEB3AdminSrvRegiServiceChangeHandler.class,
            "confirmSrvChange");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceChangeCompleteRequest.class,
            WEB3AdminSrvRegiServiceChangeHandler.class,
            "completeSrvChange");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceChangeInputRequest.class,
            WEB3AdminSrvRegiServiceChangeInputHandler.class,
            "srvChangeInputRequest");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceDetailsRequest.class,
            WEB3AdminSrvRegiServiceDetailHandler.class,
            "serviceDetailRequest");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceRegistCompleteRequest.class,
            WEB3AdminSrvRegiServiceRegiHandler.class,
            "completeServiceRegi");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^ �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceRegistConfirmRequest.class,
            WEB3AdminSrvRegiServiceRegiHandler.class,
            "confirmServiceRegi");

        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ �p�n���h���[�̓o�^
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3AdminSrvRegiServiceReferenceRequest.class,
            WEB3AdminSrvRegiSrvListHandler.class, "searchSrv");

        //�T�[�r�X���p�\������ �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiApplyInputRequest.class,
            WEB3SrvRegiApplicationInputHandler.class,
            "useAppliInputRequest");
        
        //�T�[�r�X���p�\������ �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiConsentRequest.class,
            WEB3SrvRegiApplicationInputHandler.class,
            "docScreenRequest");
        
        //�T�[�r�X���p��� �p�n���h���[�̓o�^
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3SrvRegiCancelCompleteRequest.class,
            WEB3SrvRegiCancelHandler.class, "completeCancel");

        //�T�[�r�X���p��� �p�n���h���[�̓o�^
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3SrvRegiCancelConfirmRequest.class,
            WEB3SrvRegiCancelHandler.class, "confirmCancel");

        //�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ� �p�n���h���[�̓o�^
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3SrvRegiReferenceRequest.class,
            WEB3SrvRegiServiceListInquiryHandler.class, "searchService");

        //�T�[�r�X���p�T�[�r�X�N�� �p�n���h���[�̓o�^
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3SrvRegiExecRequest.class,
            WEB3SrvRegiServiceStartHandler.class, "srvStartRequest");

        //�T�[�r�X���p�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiApplyCompleteRequest.class,
            WEB3SrvRegiServiceUseApplicationHandler.class,
            "completeUseAppli");

        //�T�[�r�X���p�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiApplyConfirmRequest.class,
            WEB3SrvRegiServiceUseApplicationHandler.class,
            "confirmUseAppli");

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ������
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.class,
            WEB3AdminSrvRegiOtherOrgIdListHandler.class,
            "otherOrgIdListReference");

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ������
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdListSearchRequest.class,
            WEB3AdminSrvRegiOtherOrgIdListHandler.class,
            "otherOrgIdListSearch");

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�������
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest.class,
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class,
            "otherOrgIdUploadScreenDisplay");

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�������
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.class,
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class,
            "otherOrgIdUploadConfirm");

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�������
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.class,
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class,
            "otherOrgIdUpload");

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�������
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest.class,
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class,
            "otherOrgIdUploadCancel");

        //�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�������
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdDownloadRequest.class,
            WEB3AdminSrvRegiOtherOrgIdDownloadHandler.class,
            "otherOrgIdDownload");

        //�T�[�r�X���p���A�g�n���h��
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiStreamRequest.class,
            WEB3SrvRegiStreamHandler.class,
            "srvRegiStreamRequest");

        log.exiting(STR_METHOD_NAME);
    }
}@
