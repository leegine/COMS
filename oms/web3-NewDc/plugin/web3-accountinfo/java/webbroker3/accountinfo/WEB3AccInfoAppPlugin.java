head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.08.51.11;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoAppPlugin.java;

1.1
date	2011.03.10.02.24.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAppPlugin.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-AccInfo �v���O�C��(WEB3AccInfoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ���C�g (���u) �V�K�쐬
Revesion History : 2010/11/15 �����F (���u) ���f��278
*/

package webbroker3.accountinfo;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.accountinfo.data.WEB3AccInfoAccountDatabaseExtensions;
import webbroker3.accountinfo.data.WEB3AccInfoMasterDatabaseExtensions;
import webbroker3.accountinfo.data.WEB3AccInfoSessionDatabaseExtensions;
import webbroker3.accountinfo.handler.WEB3AccInfoBaseInfoReferenceHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoCommonInputHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoElecDeliveryRegisterChangeHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoEquityCommissionCourseRegistHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoExecMailDistributionChangeHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoLockRegistReleaseAcceptHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoMailAddressChangeHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoMailDistributionChangeHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoMobileOfficeRegistHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoPasswordChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoAccEstablishSearchHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoAccountBaseInfoInquiryHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignAccOpenChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignAccOpenListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignIndiviChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignIndiviListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignRegistAccListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCommissionRegistAccountDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCommissionRegistAccountInquiryHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoExclusiveTransferAccountChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoExclusiveTransferAccountUploadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoInsiderInfoChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoInsiderInfoListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoLockAccountListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoLoginErrorResetHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoLoginPasswordResetHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailAddressChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailAddressDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailAddressUploadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailDistributionHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMobileOfficeChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoPasswordChangeAccountDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoPasswordResetHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoStopStateChangeHandler;
import webbroker3.accountinfo.message.*;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoBaseInfoReferenceService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommonInputService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoElecDeliveryRegisterChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoEquityCommissionCourseRegistService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoExecMailDistributionChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptUnitService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailAddressChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailDistributionChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMobileOfficeRegistService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoPasswordChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccEstablishSearchService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccountBaseInfoInquiryService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignRegistAccListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountInquiryService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountListInquiryService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountUploadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLockAccountListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginErrorResetService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordResetService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeAccountDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressUploadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailDistributionService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordChangeAccountDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordResetService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoStopStateChangeService;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoAccountBaseInfoCreatedServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoBaseInfoReferenceServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoCommonInputServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoEquityCommissionCourseRegistServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoExecMailDistributionChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoLockRegistReleaseAcceptServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoMailAddressChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoMailDistributionChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoMobileOfficeRegistServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoPasswordChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoAccEstablishSearchServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoInsiderInfoChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoInsiderInfoListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoLockAccountListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoLoginErrorResetServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoLoginPasswordResetServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressUploadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailDistributionServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMobileOfficeChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoPasswordResetServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoStopStateChangeServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AccInfo �v���O�C���N���X�B
 *                                                                
 * @@author ���C�g
 * @@version 1.0
 */
public final class WEB3AccInfoAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccInfoAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AccInfoAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3AccInfoAppPlugin()";
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

        plug(WEB3AccInfoAppPlugin.class);

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
        WEB3AccInfoMasterDatabaseExtensions.plug();
        WEB3AccInfoAccountDatabaseExtensions.plug();
        WEB3AccInfoSessionDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------
        
        //�Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�X
        //�Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoMailDistributionService.class, 
            new WEB3AdminAccInfoMailDistributionServiceImpl());
        
        //�Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoInsiderInfoListService.class, 
            new WEB3AdminAccInfoInsiderInfoListServiceImpl());
            
        //���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�X
        Services.registerService(
            WEB3AccInfoCommissionCourseRegistInfoCreatedService.class, 
            new WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl());

        //���q�l��񋤒ʓ��̓T�[�r�X
        Services.registerService(
            WEB3AccInfoCommonInputService.class, 
            new WEB3AccInfoCommonInputServiceImpl());
        
        //���q�l���ڋq��{���쐬�T�[�r�X
        Services.registerService(
            WEB3AccInfoAccountBaseInfoCreatedService.class, 
            new WEB3AccInfoAccountBaseInfoCreatedServiceImpl());

        //���q�l��񃁁[���A�h���X�ύX�T�[�r�X
        Services.registerService(
            WEB3AccInfoMailAddressChangeService.class, 
            new WEB3AccInfoMailAddressChangeServiceImpl());

        //���q�l���ē����[���z�M�ݒ�ύX�T�[�r�X
        Services.registerService(
            WEB3AccInfoMailDistributionChangeService.class, 
            new WEB3AccInfoMailDistributionChangeServiceImpl());

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X
        Services.registerService(
            WEB3AccInfoEquityCommissionCourseRegistService.class, 
            new WEB3AccInfoEquityCommissionCourseRegistServiceImpl());

        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽
        Services.registerService(
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class, 
            new WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl());

        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽
        Services.registerService(
            WEB3AdminAccInfoMailAddressDownloadService.class, 
            new WEB3AdminAccInfoMailAddressDownloadServiceImpl());

        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoMailAddressChangeService.class, 
            new WEB3AdminAccInfoMailAddressChangeServiceImpl());

        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽
        Services.registerService(
            WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class, 
            new WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl());

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoMobileOfficeChangeService.class, 
            new WEB3AdminAccInfoMobileOfficeChangeServiceImpl());

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class, 
            new WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl());

        //�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoAccountBaseInfoInquiryService.class, 
            new WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl());

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽
        Services.registerService(
            WEB3AdminAccInfoCommissionRegistAccountDownloadService.class, 
            new WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl());

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽
        Services.registerService(
            WEB3AdminAccInfoCommissionRegistAccountListInquiryService.class, 
            new WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl());

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽
        Services.registerService(
            WEB3AdminAccInfoCommissionRegistAccountInquiryService.class, 
            new WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl());

        //�Ǘ��҂��q�l����p�U�����������۰�޻��޽
        Services.registerService(
            WEB3AdminAccInfoExclusiveTransferAccountUploadService.class, 
            new WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl());

        //�Ǘ��҂��q�l����p�U��������ύX�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoExclusiveTransferAccountChangeService.class, 
            new WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl());

        //�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoStopStateChangeService.class, 
            new WEB3AdminAccInfoStopStateChangeServiceImpl());

        //�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoInsiderInfoChangeService.class, 
            new WEB3AdminAccInfoInsiderInfoChangeServiceImpl());
            
        //���q�l����{���Ɖ�T�[�r�X
        Services.registerService(
            WEB3AccInfoBaseInfoReferenceService.class, 
            new WEB3AccInfoBaseInfoReferenceServiceImpl());
            
        //���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X
        Services.registerService(
            WEB3AccInfoMobileOfficeRegistService.class, 
            new WEB3AccInfoMobileOfficeRegistServiceImpl());
            
        //���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X
        Services.registerService(
            WEB3AccInfoExecMailDistributionChangeService.class, 
            new WEB3AccInfoExecMailDistributionChangeServiceImpl());
            
        //���q�l���Ïؔԍ��ύX�T�[�r�X
        Services.registerService(
            WEB3AccInfoPasswordChangeService.class, 
            new WEB3AccInfoPasswordChangeServiceImpl());
            
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoLoginPasswordResetService.class, 
            new WEB3AdminAccInfoLoginPasswordResetServiceImpl());
            
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽
        Services.registerService(
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class, 
            new WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl());
            
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoLoginErrorResetService.class, 
            new WEB3AdminAccInfoLoginErrorResetServiceImpl());
            
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoPasswordResetService.class, 
            new WEB3AdminAccInfoPasswordResetServiceImpl());
            
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽
        Services.registerService(
            WEB3AdminAccInfoPasswordChangeAccountDownloadService.class, 
            new WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl());

        //�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X
        Services.registerService(
                WEB3AdminAccInfoLockAccountListService.class, 
                new WEB3AdminAccInfoLockAccountListServiceImpl());
        
        //���b�N�o�^������t�T�[�r�X
        Services.registerService(
                WEB3AccInfoLockRegistReleaseAcceptService.class, 
                new WEB3AccInfoLockRegistReleaseAcceptServiceImpl());
        
        //���b�N�o�^������t�P���T�[�r�X
        Services.registerService(
                WEB3AccInfoLockRegistReleaseAcceptUnitService.class, 
                new WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl());
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoMailAddressUploadService.class, 
            new WEB3AdminAccInfoMailAddressUploadServiceImpl());
        
        //�V�K�����J�݌����T�[�r�X
        Services.registerService(
            WEB3AdminAccInfoAccEstablishSearchService.class, 
            new WEB3AdminAccInfoAccEstablishSearchServiceImpl());
        
        //�ʌڋq�w��ꗗ�T�[�r�X
        Services.registerService(
        		WEB3AdminAccInfoCampaignIndiviListService.class, 
            new WEB3AdminAccInfoCampaignIndiviListServiceImpl());
        
        //�ʌڋq�w��ύX�T�[�r�X
        Services.registerService(
        		WEB3AdminAccInfoCampaignIndiviChangeService.class, 
            new WEB3AdminAccInfoCampaignIndiviChangeServiceImpl());
        
        //�����J�ݏ����w��ꗗ�T�[�r�X
        Services.registerService(
        		WEB3AdminAccInfoCampaignAccOpenListService.class, 
            new WEB3AdminAccInfoCampaignAccOpenListServiceImpl());
        
        //�����J�ݏ����w��ύX�T�[�r�X
        Services.registerService(
        		WEB3AdminAccInfoCampaignAccOpenChangeService.class, 
            new WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl());
        
        //�����J�ݏ����w��ύX�T�[�r�X
        Services.registerService(
        		WEB3AdminAccInfoCampaignRegistAccListService.class, 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl());
        
        //�d�q��t�T�[�r�X�o�^�E�ύX�T�[�r�X
        Services.registerService(
            WEB3AccInfoElecDeliveryRegisterChangeService.class, 
            new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl());
        
        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        //���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoCommissionCourseRegistInfoCreatedService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMailDistributionService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoInsiderInfoListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���q�l��񋤒ʓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoCommonInputService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

//        //���q�l���ڋq��{���쐬�T�[�r�X
//        Services.addInterceptor(
//            WEB3AccInfoAccountBaseInfoCreatedService.class,
//            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���q�l��񃁁[���A�h���X�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoMailAddressChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���q�l���ē����[���z�M�ݒ�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoMailDistributionChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoEquityCommissionCourseRegistService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMobileOfficeChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoAccountBaseInfoInquiryService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountListInquiryService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountInquiryService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l����p�U�����������۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoExclusiveTransferAccountUploadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l����p�U��������ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoExclusiveTransferAccountChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoStopStateChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoInsiderInfoChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //���q�l����{���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoBaseInfoReferenceService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoMobileOfficeRegistService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoExecMailDistributionChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���q�l���Ïؔԍ��ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoPasswordChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoLoginPasswordResetService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoLoginErrorResetService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoPasswordResetService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoPasswordChangeAccountDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X 
        Services.addInterceptor(
            WEB3AdminAccInfoLockAccountListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //���b�N�o�^������t�P���T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoLockRegistReleaseAcceptUnitService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressUploadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //�V�K�����J�݌����T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoAccEstablishSearchService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //�ʌڋq�w��ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignIndiviListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //�ʌڋq�w��ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignIndiviChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //�����J�ݏ����w��ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignAccOpenListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //�����J�ݏ����w��ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignAccOpenChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //�o�^�ڋq�Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignRegistAccListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //�d�q��t�T�[�r�X�o�^�E�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoElecDeliveryRegisterChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

        //���q�l��񃁁[���A�h���X�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoMailAddressChangeService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        Services.addInterceptor(
            WEB3AccInfoCommonInputService.class,
            new WEB3AccInfoServiceInterceptor());
        
        //�Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMailDistributionService.class,
            new WEB3AccInfoServiceInterceptor());
        
        //�Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoInsiderInfoListService.class,
            new WEB3AccInfoServiceInterceptor());
        
        //���q�l���ē����[���z�M�ݒ�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoMailDistributionChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoEquityCommissionCourseRegistService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X 
        Services.addInterceptor(
            WEB3AdminAccInfoMobileOfficeChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoAccountBaseInfoInquiryService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountListInquiryService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountInquiryService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l����p�U�����������۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoExclusiveTransferAccountUploadService.class, 
            new WEB3AdminAccInfoExclusiveTransferAccountUploadServiceInterceptor());

        //�Ǘ��҂��q�l����p�U��������ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoExclusiveTransferAccountChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoStopStateChangeService.class, 
            new WEB3AdminAccInfoStopStateChangeServiceInterceptor());

        //�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoInsiderInfoChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //���q�l����{���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoBaseInfoReferenceService.class, 
            new WEB3AccInfoServiceInterceptor());

        //���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoMobileOfficeRegistService.class, 
            new WEB3AccInfoServiceInterceptor());

        //���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoExecMailDistributionChangeService.class, 
            new WEB3AccInfoServiceInterceptor());
        
        //���q�l���Ïؔԍ��ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoPasswordChangeService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoLoginPasswordResetService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoLoginErrorResetService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoPasswordResetService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�޻��޽
        Services.addInterceptor(
            WEB3AdminAccInfoPasswordChangeAccountDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());
        
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X 
        Services.addInterceptor(
            WEB3AdminAccInfoLockAccountListService.class, 
            new WEB3AccInfoServiceInterceptor());
                
        //���b�N�o�^������t�P���T�[�r�X 
        Services.addInterceptor(
            WEB3AccInfoLockRegistReleaseAcceptUnitService.class, 
            new WEB3AccInfoLockRegistReleaseAcceptUnitInterceptor());
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressUploadService.class, 
            new WEB3AdminAccInfoMailAddressUploadServiceInterceptor());       
        
        //�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X
        Services.addInterceptor(
                WEB3AdminAccInfoStopStateChangeService.class, 
                new WEB3MQGatewayInterceptor());   
        
        //�V�K�����J�݌������޽ 
        Services.addInterceptor(
            WEB3AdminAccInfoAccEstablishSearchService.class, 
            new WEB3AccInfoServiceInterceptor());

        //�d�q��t�T�[�r�X�o�^�E�ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AccInfoElecDeliveryRegisterChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        // Message �̓o�^���� ----------------------
        
        //�Ǘ��҂��q�l���ē����[���z�M�w�����N�G�X�g
        regClass(WEB3AdminAccInfoMailDistributionRequest.class);
        //�Ǘ��҂��q�l���ē����[���z�M�w�����X�|���X
        regClass(WEB3AdminAccInfoMailDistributionResponse.class);
        //�Ǘ��҂��q�l���ē����[���z�M�w���m�F���N�G�X�g
        regClass(WEB3AdminAccInfoMailDistributionConfirmRequest.class);
        //�Ǘ��҂��q�l���ē����[���z�M�w���m�F���X�|���X
        regClass(WEB3AdminAccInfoMailDistributionConfirmResponse.class);
        //�Ǘ��҂��q�l���ē����[���z�M�w���������N�G�X�g
        regClass(WEB3AdminAccInfoMailDistributionCompleteRequest.class);
        //�Ǘ��҂��q�l���ē����[���z�M�w���������X�|���X
        regClass(WEB3AdminAccInfoMailDistributionCompleteResponse.class);
        //�Ǘ��҂��q�l���ē����[���z�M����m�F���N�G�X�g
        regClass(WEB3AdminAccInfoMailDistributionCancelConfirmRequest.class);
        //�Ǘ��҂��q�l���ē����[���z�M����m�F���X�|���X
        regClass(WEB3AdminAccInfoMailDistributionCancelConfirmResponse.class);
        //�Ǘ��҂��q�l���ē����[���z�M����������N�G�X�g
        regClass(WEB3AdminAccInfoMailDistributionCancelCompleteRequest.class);
        //�Ǘ��҂��q�l���ē����[���z�M����������X�|���X
        regClass(WEB3AdminAccInfoMailDistributionCancelCompleteResponse.class);      
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇�����N�G�X�g
        regClass(WEB3AdminAccInfoMailAddressInquiryRequest.class);
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇�����X�|���X
        regClass(WEB3AdminAccInfoMailAddressInquiryResponse.class);
        
        //���q�l��񃁁[���A�h���X�ύX�m�F���N�G�X�g
        regClass(WEB3AccInfoMailAddressChangeConfirmRequest.class);
        //���q�l��񃁁[���A�h���X�ύX�m�F���X�|���X
        regClass(WEB3AccInfoMailAddressChangeConfirmResponse.class);

        //���q�l��񃁁[���A�h���X�ύX�������N�G�X�g
        regClass(WEB3AccInfoMailAddressChangeCompleteRequest.class);
        //���q�l��񃁁[���A�h���X�ύX�������X�|���X
        regClass(WEB3AccInfoMailAddressChangeCompleteResponse.class);
        
        //���q�l���ē����[���z�M�ݒ�ύX�������N�G�X�g
        regClass(WEB3AccInfoMailDistributionChangeCompleteRequest.class);
        //���q�l���ē����[���z�M�ݒ�ύX�������X�|���X
        regClass(WEB3AccInfoMailDistributionChangeCompleteResponse.class);

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���N�G�X�g
        regClass(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.class);
        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���X�|���X
        regClass(WEB3AccInfoEquityCommissionCourseChangeConfirmResponse.class);

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���������N�G�X�g
        regClass(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest.class);
        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���������X�|���X
        regClass(WEB3AccInfoEquityCommissionCourseChangeCompleteResponse.class);

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���N�G�X�g
        regClass(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest.class);
        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���X�|���X
        regClass(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse.class);

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������N�G�X�g
        regClass(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest.class);
        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������X�|���X
        regClass(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse.class);

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��N�G�X�g
        regClass(WEB3AccInfoEquityCommissionCourseChangeInputRequest.class);
        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��X�|���X
        regClass(WEB3AccInfoEquityCommissionCourseChangeInputResponse.class);

        //���q�l����{���Ɖ�N�G�X�g
        regClass(WEB3AccInfoAccountBaseInfoReferenceRequest.class);
        //���q�l����{���Ɖ�X�|���X
        regClass(WEB3AccInfoAccountBaseInfoReferenceResponse.class);

        //���q�l��񋤒ʓ��̓��N�G�X�g
        regClass(WEB3AccInfoCommonInputRequest.class);
        //���q�l��񋤒ʓ��̓��X�|���X
        regClass(WEB3AccInfoCommonInputResponse.class);

        //���q�l���g�єԍ��E�Ζ�����ύX�\���m�F���N�G�X�g
        regClass(WEB3AccInfoMobileOfficeRegistConfirmRequest.class);
        //���q�l���g�єԍ��E�Ζ�����ύX�\���m�F���X�|���X
        regClass(WEB3AccInfoMobileOfficeRegistConfirmResponse.class);

        //���q�l���g�єԍ��E�Ζ�����ύX�\���������N�G�X�g
        regClass(WEB3AccInfoMobileOfficeRegistCompleteRequest.class);
        //���q�l���g�єԍ��E�Ζ�����ύX�\���������X�|���X
        regClass(WEB3AccInfoMobileOfficeRegistCompleteResponse.class);

        //���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��N�G�X�g
        regClass(WEB3AccInfoMobileOfficeRegistInputRequest.class);
        //���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��X�|���X
        regClass(WEB3AccInfoMobileOfficeRegistInputResponse.class);

        //���q�l�����^����胁�[���z�M�ݒ�ύX�������N�G�X�g
        regClass(WEB3AccInfoExecMailDistributionChangeCompleteRequest.class);
        //���q�l�����^����胁�[���z�M�ݒ�ύX�������X�|���X
        regClass(WEB3AccInfoExecMailDistributionChangeCompleteResponse.class);
        //�g�єԍ��E�Ζ����� 
        //regClass(WEB3AccInfoMobileOfficeInfo.class);
        //�ڋq��{��� 
        //regClass(WEB3AccInfoAccountBaseInfo.class);
        //�萔���R�[�X�ύX�\����� 
        //regClass(WEB3AccInfoCommissionCourseChangeInfo.class);
        //��~��� 
        //regClass(WEB3AccInfoStopInfo.class);
        //�d�q����� 
        //regClass(WEB3AccInfoBatoInfo.class);
        //�����ҏ�� 
        //regClass(WEB3AccInfoInsiderInfo.class);
        //���q�l���A�b�v���[�h���𖾍� 
        //regClass(WEB3AccInfoUploadHistoryUnit.class);
        //���q�l���\�[�g�L�[ 
        //regClass(WEB3AccInfoSortKey.class);
        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�ޖ⍇��ظ��� 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest.class);
        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�ޖ⍇��ڽ��ݽ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse.class);
        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest.class);
        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse.class);
        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest.class);
        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse.class);
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoMailAddressDownloadRequest.class);
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoMailAddressDownloadResponse.class);
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S��̧���޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoMailAddressFileDownloadRequest.class);
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S��̧���޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoMailAddressFileDownloadResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�m�F���N�G�X�g 
        regClass(WEB3AdminAccInfoMailAddressChangeConfirmRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�m�F���X�|���X 
        regClass(WEB3AdminAccInfoMailAddressChangeConfirmResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������N�G�X�g 
        regClass(WEB3AdminAccInfoMailAddressChangeCompleteRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������X�|���X 
        regClass(WEB3AdminAccInfoMailAddressChangeCompleteResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq̧���޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq̧���޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ظ��� 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ڽ��ݽ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX���̓��N�G�X�g 
        regClass(WEB3AdminAccInfoMailAddressChangeInputRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX���̓��X�|���X 
        regClass(WEB3AdminAccInfoMailAddressChangeInputResponse.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�m�F���N�G�X�g 
        regClass(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�m�F���X�|���X 
        regClass(WEB3AdminAccInfoMobileOfficeRegistConfirmResponse.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�������N�G�X�g 
        regClass(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�������X�|���X 
        regClass(WEB3AdminAccInfoMobileOfficeRegistCompleteResponse.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqظ��� 
        regClass(WEB3AdminAccInfoMobileOfficeRegistAccountRequest.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqڽ��ݽ 
        regClass(WEB3AdminAccInfoMobileOfficeRegistAccountResponse.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ظ��� 
        regClass(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ڽ��ݽ 
        regClass(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX���̓��N�G�X�g 
        regClass(WEB3AdminAccInfoMobileOfficeRegistInputRequest.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX���̓��X�|���X 
        regClass(WEB3AdminAccInfoMobileOfficeRegistInputResponse.class);
        //�Ǘ��҂��q�l���ڋq��{���⍇�����N�G�X�g 
        regClass(WEB3AdminAccInfoAccountBaseInfoResultRequest.class);
        //�Ǘ��҂��q�l���ڋq��{���⍇�����X�|���X 
        regClass(WEB3AdminAccInfoAccountBaseInfoResultResponse.class);
        //�Ǘ��҂��q�l���ڋq��{���⍇�����̓��N�G�X�g 
        regClass(WEB3AdminAccInfoAccountBaseInfoInquiryRequest.class);
        //�Ǘ��҂��q�l���ڋq��{���⍇�����̓��X�|���X 
        regClass(WEB3AdminAccInfoAccountBaseInfoInquiryResponse.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountDownloadResponse.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ظ��� 
        regClass(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ڽ��ݽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq̧���޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq̧���޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇��ظ��� 
        regClass(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇��ڽ��ݽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ظ��� 
        regClass(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ڽ��ݽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ظ��� 
        regClass(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ڽ��ݽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountInquiryResponse.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ظ��� 
        regClass(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest.class);
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ڽ��ݽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse.class);
        //�Ǘ��҂��q�l����p�U�����������۰�ފm�Fظ��� 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest.class);
        //�Ǘ��҂��q�l����p�U�����������۰�ފm�Fڽ��ݽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse.class);
        //�Ǘ��҂��q�l����p�U�����������۰�ފ���ظ��� 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest.class);
        //�Ǘ��҂��q�l����p�U�����������۰�ފ���ڽ��ݽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse.class);
        //�Ǘ��҂��q�l����p�U�����������۰�ޒ��~ظ��� 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest.class);
        //�Ǘ��҂��q�l����p�U�����������۰�ޒ��~ڽ��ݽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse.class);
        //�Ǘ��҂��q�l����p�U�����������۰�ޓ���ظ��� 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest.class);
        //�Ǘ��҂��q�l����p�U�����������۰�ޓ���ڽ��ݽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse.class);
        //�Ǘ��҂��q�l����p�U��������ύX�m�Fظ��� 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest.class);
        //�Ǘ��҂��q�l����p�U��������ύX�m�Fڽ��ݽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse.class);
        //�Ǘ��҂��q�l����p�U��������ύX����ظ��� 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.class);
        //�Ǘ��҂��q�l����p�U��������ύX����ڽ��ݽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse.class);
        //�Ǘ��҂��q�l����p�U��������ύX����ظ��� 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest.class);
        //�Ǘ��҂��q�l����p�U��������ύX����ڽ��ݽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse.class);
        //�Ǘ��҂��q�l����~�󋵕ύX�m�F���N�G�X�g 
        regClass(WEB3AdminAccInfoStopStateChangeConfirmRequest.class);
        //�Ǘ��҂��q�l����~�󋵕ύX�m�F���X�|���X 
        regClass(WEB3AdminAccInfoStopStateChangeConfirmResponse.class);
        //�Ǘ��҂��q�l����~�󋵕ύX�������N�G�X�g 
        regClass(WEB3AdminAccInfoStopStateChangeCompleteRequest.class);
        //�Ǘ��҂��q�l����~�󋵕ύX�������X�|���X 
        regClass(WEB3AdminAccInfoStopStateChangeCompleteResponse.class);
        //�Ǘ��҂��q�l����~�󋵕ύX���̓��N�G�X�g 
        regClass(WEB3AdminAccInfoStopStateChangeInputRequest.class);
        //�Ǘ��҂��q�l����~�󋵕ύX���̓��X�|���X 
        regClass(WEB3AdminAccInfoStopStateChangeInputResponse.class);
        //�Ǘ��҂��q�l�������ҏ��ύX�m�F���N�G�X�g 
        regClass(WEB3AdminAccInfoInsiderInfoChangeConfirmRequest.class);
        //�Ǘ��҂��q�l�������ҏ��ύX�m�F���X�|���X 
        regClass(WEB3AdminAccInfoInsiderInfoChangeConfirmResponse.class);
        //�Ǘ��҂��q�l�������ҏ��ύX�������N�G�X�g 
        regClass(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.class);
        //�Ǘ��҂��q�l�������ҏ��ύX�������X�|���X 
        regClass(WEB3AdminAccInfoInsiderInfoChangeCompleteResponse.class);
        //�Ǘ��҂��q�l�������ҏ��ύX���̓��N�G�X�g 
        regClass(WEB3AdminAccInfoInsiderInfoChangeInputRequest.class);
        //�Ǘ��҂��q�l�������ҏ��ύX���̓��X�|���X 
        regClass(WEB3AdminAccInfoInsiderInfoChangeInputResponse.class);
        //�Ǘ��҂��q�l���������ꗗ���̓��N�G�X�g
        regClass(WEB3AdminAccInfoInsiderInfoInputRequest.class);
        //�Ǘ��҂��q�l���������ꗗ���̓��X�|���X
        regClass(WEB3AdminAccInfoInsiderInfoInputResponse.class);
        //�Ǘ��҂��q�l���������ꗗ���N�G�X�g
        regClass(WEB3AdminAccInfoInsiderInfoListRequest.class);
        //�Ǘ��҂��q�l���������ꗗ���X�|���X
        regClass(WEB3AdminAccInfoInsiderInfoListResponse.class);
        //�g�єԍ��E�Ζ�����ύX�\���ڋq 
        //regClass(WEB3AccInfoMobileOfficeChangeAccount.class);
        //�ڋq���[���A�h���X��� 
        //regClass(WEB3AccInfoAccountMailAddressInfo.class);
        //�萔���ύX�ڋq��� 
        //regClass(WEB3AccInfoCommissionChangeAccountInfo.class);
        
        //���q�l���Ïؔԍ��ύX���N�G�X�g 
        regClass(WEB3AccInfoPasswordChangeCompleteRequest.class);
        //���q�l���Ïؔԍ��ύX���X�|���X 
        regClass(WEB3AccInfoPasswordChangeCompleteResponse.class);
        //���q�l���Ïؔԍ��ύX���̓��N�G�X�g 
        regClass(WEB3AccInfoPasswordChangeInputRequest.class);
        //���q�l���Ïؔԍ��ύX���̓��X�|���X 
        regClass(WEB3AccInfoPasswordChangeInputResponse.class);
        //�p�X���[�h�ύX�ڋq��� 
        //regClass(WEB3AccInfoLoginPasswordChangeAccountInfo.class);
        //�Ïؔԍ��ύX�ڋq��� 
        //regClass(WEB3AccInfoPasswordChangeAccountInfo.class);
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g���N�G�X�g 
        regClass(WEB3AdminAccInfoLoginPasswordResetRequest.class);
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g���X�|���X 
        regClass(WEB3AdminAccInfoLoginPasswordResetResponse.class);
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g���̓��N�G�X�g 
        regClass(WEB3AdminAccInfoLoginPasswordResetInputRequest.class);
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g���̓��X�|���X 
        regClass(WEB3AdminAccInfoLoginPasswordResetInputResponse.class);
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.class);
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse.class);
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq̧���޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest.class);
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq̧���޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse.class);
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�⍇��ظ��� 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest.class);
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�⍇��ڽ��ݽ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse.class);
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g���N�G�X�g 
        regClass(WEB3AdminAccInfoLoginErrorResetRequest.class);
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g���X�|���X 
        regClass(WEB3AdminAccInfoLoginErrorResetResponse.class);
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g���̓��N�G�X�g 
        regClass(WEB3AdminAccInfoLoginErrorResetInputRequest.class);
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g���̓��X�|���X 
        regClass(WEB3AdminAccInfoLoginErrorResetInputResponse.class);
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g���N�G�X�g 
        regClass(WEB3AdminAccInfoPasswordResetRequest.class);
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g���X�|���X 
        regClass(WEB3AdminAccInfoPasswordResetResponse.class);
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g���̓��N�G�X�g 
        regClass(WEB3AdminAccInfoPasswordResetInputRequest.class);
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g���̓��X�|���X 
        regClass(WEB3AdminAccInfoPasswordResetInputResponse.class);
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoPasswordChangeAccountDownloadRequest.class);
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountDownloadResponse.class);
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq̧���޳�۰��ظ��� 
        regClass(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest.class);
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq̧���޳�۰��ڽ��ݽ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse.class);
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�⍇��ظ��� 
        regClass(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest.class);
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�⍇��ڽ��ݽ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountInquiryResponse.class);        
        
        //���b�N�o�^������t���N�G�X�g
        regClass(WEB3AccInfoLockRegistReleaseAcceptRequest.class);  
        //���b�N�o�^������t���X�|���X
        regClass(WEB3AccInfoLockRegistReleaseAcceptResponse.class); 
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��N�G�X�g
        regClass(WEB3AdminAccInfoLockAccountSearchInputRequest.class);
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��X�|���X
        regClass(WEB3AdminAccInfoLockAccountSearchInputResponse.class);
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���N�G�X�g
        regClass(WEB3AdminAccInfoLockAccountSearchListRequest.class);
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���X�|���X
        regClass(WEB3AdminAccInfoLockAccountSearchListResponse.class);
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���̓��N�G�X�g
        regClass(WEB3AdminAccInfoMailAddressUploadInputRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���N�G�X�g
        regClass(WEB3AdminAccInfoMailAddressUploadConfirmRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�������N�G�X�g
        regClass(WEB3AdminAccInfoMailAddressUploadCompleteRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���~���N�G�X�g
        regClass(WEB3AdminAccInfoMailAddressUploadCancelRequest.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���̓��X�|���X
        regClass(WEB3AdminAccInfoMailAddressUploadInputResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���X�|���X
        regClass(WEB3AdminAccInfoMailAddressUploadConfirmResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�������X�|���X
        regClass(WEB3AdminAccInfoMailAddressUploadCompleteResponse.class);
        //�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���~���X�|���X
        regClass(WEB3AdminAccInfoMailAddressUploadCancelResponse.class);
                
        //���O�C�����b�N�ڋq��������ظ���
        regClass(WEB3AdminAccInfoAccEstablishSearchInputRequest.class);
        //���O�C�����b�N�ڋq��������ڽ��ݽ
        regClass(WEB3AdminAccInfoAccEstablishSearchInputResponse.class);
        //���O�C�����b�N�ڋq�����ꗗظ���
        regClass(WEB3AdminAccInfoAccEstablishSearchListRequest.class);
        //���O�C�����b�N�ڋq�����ꗗڽ��ݽ
        regClass(WEB3AdminAccInfoAccEstablishSearchListResponse.class);
        //���O�C�����b�N�ڋq�����޳�۰��ظ���
        regClass(WEB3AdminAccInfoAccEstablishSearchDLRequest.class);
        //���O�C�����b�N�ڋq�����޳�۰��ڽ��ݽ
        regClass(WEB3AdminAccInfoAccEstablishSearchDLResponse.class);

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F���N�G�X�g
        regClass(WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�Fڽ��ݽ
        regClass(WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮�����N�G�X�g
        regClass(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.class);
        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮��ڽ��ݽ
        regClass(WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse.class);
        
        //�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w��ꗗ�v��
        regClass(WEB3AdminAccInfoCampaignIndiviListRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w��ꗗ����
        regClass(WEB3AdminAccInfoCampaignIndiviListResponse.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���o�^�ڋq�Ɖ�v��
        regClass(WEB3AdminAccInfoCampaignRegistAccListRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���o�^�ڋq�Ɖ��
        regClass(WEB3AdminAccInfoCampaignRegistAccListResponse.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����ꗗ�v��
        regClass(WEB3AdminAccInfoCampaignAccOpenListRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����ꗗ����
        regClass(WEB3AdminAccInfoCampaignAccOpenListResponse.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ������͗v��
        regClass(WEB3AdminAccInfoCampaignAccOpenInputRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ������͉���
        regClass(WEB3AdminAccInfoCampaignAccOpenInputResponse.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����m�F�v��
        regClass(WEB3AdminAccInfoCampaignAccOpenConfirmRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����m�F����
        regClass(WEB3AdminAccInfoCampaignAccOpenConfirmResponse.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ��������v��
        regClass(WEB3AdminAccInfoCampaignAccOpenCompleteRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ�����������
        regClass(WEB3AdminAccInfoCampaignAccOpenCompleteResponse.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w����͗v��
        regClass(WEB3AdminAccInfoCampaignIndiviInputRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w����͉���
        regClass(WEB3AdminAccInfoCampaignIndiviInputResponse.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w��m�F�v��
        regClass(WEB3AdminAccInfoCampaignIndiviConfirmRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w��m�F����
        regClass(WEB3AdminAccInfoCampaignIndiviConfirmResponse.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w�芮���v��
        regClass(WEB3AdminAccInfoCampaignIndiviCompleteRequest.class);
        //�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w�芮������
        regClass(WEB3AdminAccInfoCampaignIndiviCompleteResponse.class);

        //�d�q��t�T�[�r�X�o�^�E�ύX���̓��N�G�X�g
        regClass(WEB3AccInfoElecDeliveryRegisterChangeInputRequest.class);
        //�d�q��t�T�[�r�X�o�^�E�ύX���̓��X�|���X
        regClass(WEB3AccInfoElecDeliveryRegisterChangeInputResponse.class);

        //�d�q��t�T�[�r�X�o�^�E�ύX�������N�G�X�g
        regClass(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.class);
        //�d�q��t�T�[�r�X�o�^�E�ύX�������X�|���X
        regClass(WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse.class);

        //�d�q��t���ꗗ���N�G�X�g
        regClass(WEB3AccInfoElecDeliveryApyReferenceRequest.class);
        //�d�q��t���ꗗ���X�|���X
        regClass(WEB3AccInfoElecDeliveryApyReferenceResponse.class);

        //Handler �̓o�^���� ----------------------

        //���q�l��񋤒ʓ��� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoCommonInputRequest.class,
            WEB3AccInfoCommonInputHandler.class,
            "inputScreenDisplay");

        //���q�l��񃁁[���A�h���X�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMailAddressChangeConfirmRequest.class,
            WEB3AccInfoMailAddressChangeHandler.class,
            "mailAddressChangeConfirm");

        //���q�l��񃁁[���A�h���X�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMailAddressChangeCompleteRequest.class,
            WEB3AccInfoMailAddressChangeHandler.class,
            "mailAddressChangeComplete");

        //���q�l���ē����[���z�M�ݒ�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMailDistributionChangeCompleteRequest.class,
            WEB3AccInfoMailDistributionChangeHandler.class,
            "accountInfoMailDistributionChangeComplete");

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeInputRequest.class, 
            WEB3AccInfoEquityCommissionCourseRegistHandler.class, 
            "inputScreenDisplay");

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.class, 
            WEB3AccInfoEquityCommissionCourseRegistHandler.class, 
            "registConfirm");

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeCompleteRequest.class,
            WEB3AccInfoEquityCommissionCourseRegistHandler.class,
            "registComplete");

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest.class,
            WEB3AccInfoEquityCommissionCourseRegistHandler.class,
            "registCancelConfirm");

        //���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest.class, 
            WEB3AccInfoEquityCommissionCourseRegistHandler.class, 
            "registConcelComplete");

        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest.class, 
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest.class, 
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.class, 
            "downloadScreenDisplay");

        //�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest.class, 
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.class, 
            "hyperBoxCommissionChangeDownload");

        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,        
            WEB3AdminAccInfoMailAddressInquiryRequest.class, 
            WEB3AdminAccInfoMailAddressDownloadHandler.class, 
            "inputScreenDisplay");
                        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressDownloadRequest.class, 
            WEB3AdminAccInfoMailAddressDownloadHandler.class, 
            "downloadScreenDisplay");

        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressFileDownloadRequest.class, 
            WEB3AdminAccInfoMailAddressDownloadHandler.class, 
            "mailAddressDownload");

        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeInputRequest.class, 
            WEB3AdminAccInfoMailAddressChangeHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeConfirmRequest.class, 
            WEB3AdminAccInfoMailAddressChangeHandler.class, 
            "mailAddressChangeConfirm");

        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeCompleteRequest.class, 
            WEB3AdminAccInfoMailAddressChangeHandler.class, 
            "mailAddressChangeComplete");

        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest.class, 
            WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest.class, 
            WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.class, 
            "downloadScreenDisplay");

        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest.class, 
            WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.class, 
            "mailAddressChangeAccountDownload");

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistInputRequest.class, 
            WEB3AdminAccInfoMobileOfficeChangeHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistConfirmRequest.class, 
            WEB3AdminAccInfoMobileOfficeChangeHandler.class, 
            "changeConfirm");

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.class, 
            WEB3AdminAccInfoMobileOfficeChangeHandler.class, 
            "changeComplete");

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest.class, 
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistAccountRequest.class, 
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class, 
            "registAccountListDisplay");

        //�Ǘ��҂��q�l���ڋq��{���⍇�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccountBaseInfoResultRequest.class, 
            WEB3AdminAccInfoAccountBaseInfoInquiryHandler.class, 
            "accountBaseInfoInquiry");

        //�Ǘ��҂��q�l���ڋq��{���⍇�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccountBaseInfoInquiryRequest.class, 
            WEB3AdminAccInfoAccountBaseInfoInquiryHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.class, 
            "downloadScreenDisplay");

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.class, 
            "commissionRegistAccountDownload");

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler.class, 
            "commissionRegistAccountListDisplay");

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.class, 
            "registListDisplay");

        //�Ǘ��҂��q�l����p�U�����������۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class, 
            "uploadScreenDisplay");

        //�Ǘ��҂��q�l����p�U�����������۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class, 
            "uploadFileConfirm");

        //�Ǘ��҂��q�l����p�U�����������۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class, 
            "exclusiveTransferAccountUpload");

        //�Ǘ��҂��q�l����p�U�����������۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class, 
            "uploadCancel");

        //�Ǘ��҂��q�l����p�U��������ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l����p�U��������ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.class, 
            "changeConfirm");

        //�Ǘ��҂��q�l����p�U��������ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.class, 
            "changeComplete");

        //�Ǘ��҂��q�l����~�󋵕ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoStopStateChangeInputRequest.class, 
            WEB3AdminAccInfoStopStateChangeHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l����~�󋵕ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoStopStateChangeConfirmRequest.class, 
            WEB3AdminAccInfoStopStateChangeHandler.class, 
            "changeConfirm");
        
        //�Ǘ��҂��q�l����~�󋵕ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoStopStateChangeCompleteRequest.class, 
            WEB3AdminAccInfoStopStateChangeHandler.class, 
            "changeComplete");
            
        //�Ǘ��҂��q�l�������ҏ��ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoChangeInputRequest.class, 
            WEB3AdminAccInfoInsiderInfoChangeHandler.class, 
            "inputScreenDisplay");

        //�Ǘ��҂��q�l�������ҏ��ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoChangeConfirmRequest.class, 
            WEB3AdminAccInfoInsiderInfoChangeHandler.class, 
            "changeConfirm");
        
        //�Ǘ��҂��q�l�������ҏ��ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.class, 
            WEB3AdminAccInfoInsiderInfoChangeHandler.class, 
            "changeComplete");
            
        //���q�l����{���Ɖ� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoAccountBaseInfoReferenceRequest.class, 
            WEB3AccInfoBaseInfoReferenceHandler.class, 
            "baseInfoReference");
            
        //���q�l���g�єԍ��E�Ζ�����ύX�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMobileOfficeRegistInputRequest.class, 
            WEB3AccInfoMobileOfficeRegistHandler.class, 
            "inputScreenDisplay");
        
        //���q�l���g�єԍ��E�Ζ�����ύX�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMobileOfficeRegistConfirmRequest.class, 
            WEB3AccInfoMobileOfficeRegistHandler.class, 
            "registConfirm");
            
        //���q�l���g�єԍ��E�Ζ�����ύX�\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMobileOfficeRegistCompleteRequest.class, 
            WEB3AccInfoMobileOfficeRegistHandler.class, 
            "registComplete");
            
        //���q�l�����^����胁�[���z�M�ݒ�ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoExecMailDistributionChangeCompleteRequest.class, 
            WEB3AccInfoExecMailDistributionChangeHandler.class, 
            "execMailDistributionChangeComplete");
            
        //���q�l���Ïؔԍ��ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoPasswordChangeInputRequest.class, 
            WEB3AccInfoPasswordChangeHandler.class, 
            "inputScreenDisplay");
            
        //���q�l���Ïؔԍ��ύX �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoPasswordChangeCompleteRequest.class, 
            WEB3AccInfoPasswordChangeHandler.class, 
            "changePassword");
            
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordResetInputRequest.class, 
            WEB3AdminAccInfoLoginPasswordResetHandler.class, 
            "inputScreenDisplay");
            
        //�Ǘ��҂��q�l���p�X���[�h���Z�b�g �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordResetRequest.class, 
            WEB3AdminAccInfoLoginPasswordResetHandler.class, 
            "loginPasswordReset");
            
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest.class, 
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.class, 
            "inputScreenDisplay");
            
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.class, 
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.class, 
            "downloadScreenDisplay");
            
        //�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest.class, 
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.class, 
            "loginPasswordChangeAccountDownload");
            
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginErrorResetInputRequest.class, 
            WEB3AdminAccInfoLoginErrorResetHandler.class, 
            "inputScreenDisplay");
            
        //�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginErrorResetRequest.class, 
            WEB3AdminAccInfoLoginErrorResetHandler.class, 
            "loginErrorReset");
            
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordResetInputRequest.class, 
            WEB3AdminAccInfoPasswordResetHandler.class, 
            "getInputScreen");
            
        //�Ǘ��҂��q�l���Ïؔԍ����Z�b�g �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordResetRequest.class, 
            WEB3AdminAccInfoPasswordResetHandler.class, 
            "passwordReset");
            
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordChangeAccountInquiryRequest.class, 
            WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.class, 
            "inputScreenDisplay");
            
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordChangeAccountDownloadRequest.class, 
            WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.class, 
            "downloadScreenDisplay");
            
        //�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest.class, 
            WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.class, 
            "passwordChangeAccountDownload");
        
        //�Ǘ��҂��q�l�������ҏ��ꗗ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoInputRequest.class, 
            WEB3AdminAccInfoInsiderInfoListHandler.class, 
            "inputScreenDisplay");
            
        //�Ǘ��҂��q�l�������ҏ��ꗗ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoListRequest.class, 
            WEB3AdminAccInfoInsiderInfoListHandler.class, 
            "listScreenDisplay");
            
        //�Ǘ��҂��q�܂����ē����[���z�M�w�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "getMailDistributionScreen");
            
        //�Ǘ��҂��q�܂����ē����[���z�M�w�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionConfirmRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "validateMailDistribution");
            
        //�Ǘ��҂��q�܂����ē����[���z�M�w�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionCompleteRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "submintMailDistribution");
            
        //�Ǘ��҂��q�܂����ē����[���z�M�w�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionCancelConfirmRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "validateMailDistributionCancle");
            
        //�Ǘ��҂��q�܂����ē����[���z�M�w�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionCancelCompleteRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "submitMailDistributionCancle");
        
        //���b�N�o�^������t�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoLockRegistReleaseAcceptRequest.class, 
            WEB3AccInfoLockRegistReleaseAcceptHandler.class, 
            "lockRegistReleaseAccept");
        
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓n���h���[�̓o�^
        regHandler(
                WEB3AccInfoAppPlugin.class,
                WEB3AdminAccInfoLockAccountSearchInputRequest.class, 
                WEB3AdminAccInfoLockAccountListHandler.class, 
                "inputScreenDisplay");
        
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ�n���h���[�̓o�^
        regHandler(
                WEB3AccInfoAppPlugin.class,
                WEB3AdminAccInfoLockAccountSearchListRequest.class, 
                WEB3AdminAccInfoLockAccountListHandler.class, 
                "getLockAccountRegistList");
        
        //���[���A�h���X�A�b�v���[�h��ʕ\�������n���h���[�̓o�^
        regHandler(
                WEB3AccInfoAppPlugin.class,
                WEB3AdminAccInfoMailAddressUploadInputRequest.class, 
                WEB3AdminAccInfoMailAddressUploadHandler.class, 
                "uploadScreenDisplay");
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X����۰�ފm�F�����n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressUploadConfirmRequest.class, 
            WEB3AdminAccInfoMailAddressUploadHandler.class, 
            "uploadFileConfirm");
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X����۰�ފ��������n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressUploadCompleteRequest.class, 
            WEB3AdminAccInfoMailAddressUploadHandler.class, 
            "mailAddressUpload");
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X����۰�ޏ����n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressUploadCancelRequest.class, 
            WEB3AdminAccInfoMailAddressUploadHandler.class, 
            "uploadCancel");
        
        //���O�C�����b�N�ڋq��������ׁ[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccEstablishSearchInputRequest.class, 
            WEB3AdminAccInfoAccEstablishSearchHandler.class, 
            "getInputScreen");
        
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccEstablishSearchListRequest.class, 
            WEB3AdminAccInfoAccEstablishSearchHandler.class, 
            "getListScreen");
        
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccEstablishSearchDLRequest.class, 
            WEB3AdminAccInfoAccEstablishSearchHandler.class, 
            "getDownLoadFile");

        //�ꊇ����m�F����
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest.class,
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class,
            "judgementConfirmScreenDisplay");

        //�ꊇ���芮������
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.class,
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class,
            "judgementComplete");
        
        //�ʌڋq�w��ꗗ�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignIndiviListRequest.class,
            WEB3AdminAccInfoCampaignIndiviListHandler.class,
            "getListScreen");
        
        //�ʌڋq�w��ύX�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignIndiviInputRequest.class,
            WEB3AdminAccInfoCampaignIndiviChangeHandler.class,
            "inputScreenDisplay");
        
        //�ʌڋq�w��ύX�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignIndiviConfirmRequest.class,
            WEB3AdminAccInfoCampaignIndiviChangeHandler.class,
            "IndiviChangeConfirm");
        
        //�ʌڋq�w��ύX�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignIndiviCompleteRequest.class,
            WEB3AdminAccInfoCampaignIndiviChangeHandler.class,
            "IndiviChangeComplete");
        
        //�����J�ݏ����w��ꗗ�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignAccOpenListRequest.class,
            WEB3AdminAccInfoCampaignAccOpenListHandler.class,
            "getListScreen");
        
        //�����J�ݏ����w��ύX�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignAccOpenInputRequest.class,
            WEB3AdminAccInfoCampaignAccOpenChangeHandler.class,
            "inputScreenProcess");
        
        //�����J�ݏ����w��ύX�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignAccOpenConfirmRequest.class,
            WEB3AdminAccInfoCampaignAccOpenChangeHandler.class,
            "changeConfirmScreenProcess");
        
        //�����J�ݏ����w��ύX�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignAccOpenCompleteRequest.class,
            WEB3AdminAccInfoCampaignAccOpenChangeHandler.class,
            "changeCompleteScreenProcess");
        
        //�o�^�ڋq�Ɖ�n���h���[�̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignRegistAccListRequest.class,
            WEB3AdminAccInfoCampaignRegistAccListHandler.class,
            "listScreenShow");
        
        //�d�q��t�T�[�r�X�o�^�E�ύX�n���h���̓o�^
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoElecDeliveryRegisterChangeInputRequest.class,
            WEB3AccInfoElecDeliveryRegisterChangeHandler.class,
            "inputScreenDisplay");
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.class,
            WEB3AccInfoElecDeliveryRegisterChangeHandler.class,
            "elecDeliveryRegisterChange");
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoElecDeliveryApyReferenceRequest.class,
            WEB3AccInfoElecDeliveryRegisterChangeHandler.class,
            "elecDeliveryApyReference");
        log.exiting(STR_METHOD_NAME);
    }
}@


1.1
log
@*** empty log message ***
@
text
@d6 1
d21 1
d63 1
d105 1
d408 5
d615 5
d793 5
d1228 15
d1908 16
@

