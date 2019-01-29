head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-�����J�� �v���O�C��(WEB3AccOpenAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �s�p (���u) �V�K�쐬
Revesion History : 2006/06/15 ���� (���u) �d�l�ύX ���f��052
Revesion History : 2006/08/18 ���r (���u) �d�l�ύX ���f��090
Revesion History : 2006/11/28 �����q (���u) �d�l�ύX ���f��113
Revesion History : 2007/05/29 �đo�g (���u) �d�l�ύX ���f��No.123
Revesion History : 2007/11/27 �Ӑ� (���u) �d�l�ύX ���f��No.147,148
Revesion History : 2008/12/16 ���m�a (���u) �d�l�ύX ���f��No.158,159,160,161
Revesion History : 2009/08/10 �И��� (���u) �d�l�ύX ���f�� No.166
Revesion History : 2009/08/13 ���g (���u) �d�l�ύX ���f�� No.164,No175
Revesion History : 2009/08/19 ��іQ (���u) �d�l�ύX ���f�� No.180,No181
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.accountopen.data.WEB3AccOpenMasterDatabaseExtensions;
import webbroker3.accountopen.data.WEB3AccOpenSessionDatabaseExtensions;
import webbroker3.accountopen.handler.WEB3AccOpenFinInstitutionSearchHandler;
import webbroker3.accountopen.handler.WEB3AccOpenMailAddressRegistHandler;
import webbroker3.accountopen.handler.WEB3AccOpenRegistHandler;
import webbroker3.accountopen.handler.WEB3AccOpenVoucherRegAcceptHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenApplyDataDelHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenApplyULHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenCompleteMailSendHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenDataTransferHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenJudgeHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenRegistSearchHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenStateInquiryHandler;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse;
import webbroker3.accountopen.message.WEB3AccOpenChangeRequest;
import webbroker3.accountopen.message.WEB3AccOpenChangeResponse;
import webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchRequest;
import webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchResponse;
import webbroker3.accountopen.message.WEB3AccOpenFinancialSearchRequest;
import webbroker3.accountopen.message.WEB3AccOpenFinancialSearchResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputResponse;
import webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptRequest;
import webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenAccountCodeService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenFinInstitutionSearchService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInformAcceptUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenMailAddressRegistService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRealUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRegistService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRequestNumberService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptUnitService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyDataDelService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyULService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendUnitService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferUnitService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenJudgeService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenRegistSearchService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenStateInquiryService;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenAccountCodeServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenFinInstitutionSearchServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInfoCreatedServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenMailAddressRegistServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenRealUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenRegistServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenRequestNumberServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherCreatedServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenApplyDataDelServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenApplyULServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenCompleteMailSendServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenCompleteMailSendUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenDataTransferServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenDataTransferUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenJudgeServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenRegistSearchServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenStateInquiryServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-�����J�� �v���O�C���N���X�B
 *
 * @@author �s�p
 * @@version 1.0
 */
public final class WEB3AccOpenAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AccOpenAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3AccOpenAppPlugin()";
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

        plug(WEB3AccOpenAppPlugin.class);

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
        WEB3AccOpenMasterDatabaseExtensions.plug();
        WEB3AccOpenSessionDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------
        //�����J�ݓ`�[�쐬�T�[�r�X
        Services.registerService(WEB3AccOpenVoucherCreatedService.class,
            new WEB3AccOpenVoucherCreatedServiceImpl());

        //�Ǘ��Ҍ����J�݊������[�����M�T�[�r�X
        Services.registerService(
            WEB3AdminAccOpenCompleteMailSendService.class,
            new WEB3AdminAccOpenCompleteMailSendServiceImpl());

        //�����J�݊������[�����MUnitService
        Services.registerService(
            WEB3AdminAccOpenCompleteMailSendUnitService.class,
            new WEB3AdminAccOpenCompleteMailSendUnitServiceImpl());

        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X
        Services.registerService(
            WEB3AdminAccOpenStateInquiryService.class,
            new WEB3AdminAccOpenStateInquiryServiceImpl());

        //�Ǘ��Ҍ����J�ݐ\�������T�[�r�X
        Services.registerService(
            WEB3AdminAccOpenRegistSearchService.class,
            new WEB3AdminAccOpenRegistSearchServiceImpl());
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X 
        Services.registerService(
            WEB3AdminAccOpenJudgeService.class,
            new WEB3AdminAccOpenJudgeServiceImpl());        

        //�����J�݋��Z�@@�֌����T�[�r�X
        Services.registerService(
            WEB3AccOpenFinInstitutionSearchService.class,
            new WEB3AccOpenFinInstitutionSearchServiceImpl());

        //�����J�ݎ��ʃR�[�h�̔ԃT�[�r�X
        Services.registerService(WEB3AccOpenRequestNumberService.class,
            new WEB3AccOpenRequestNumberServiceImpl());

        //�����J�ݏ��쐬�T�[�r�X
        Services.registerService(WEB3AccOpenInfoCreatedService.class,
            new WEB3AccOpenInfoCreatedServiceImpl());

        //�����J�ݐ\���T�[�r�X
        Services.registerService(WEB3AccOpenRegistService.class,
            new WEB3AccOpenRegistServiceImpl());

        //�����J�ݓ`�[�o�^��tUnitService
        Services.registerService(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new WEB3AccOpenVoucherRegAcceptUnitServiceImpl());

        //�����J�ݓ`�[�o�^��t�T�[�r�X
        Services.registerService(
            WEB3AccOpenVoucherRegAcceptService.class,
            new WEB3AccOpenVoucherRegAcceptServiceImpl());
        
        //�����J�݌ڋq�R�[�h�̔ԃT�[�r�X
        Services.registerService(
            WEB3AccOpenAccountCodeService.class,
            new WEB3AccOpenAccountCodeServiceImpl());
        
        // ���������J��UnitService
        Services.registerService(
            WEB3AccOpenRealUnitService.class,
            new WEB3AccOpenRealUnitServiceImpl());

        //�e��A����tUnitService
        Services.registerService(
            WEB3AccOpenInformAcceptUnitService.class,
            new WEB3AccOpenInformAcceptUnitServiceImpl());

        //�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X
        Services.registerService(
            WEB3AdminAccOpenApplyULService.class,
            new WEB3AdminAccOpenApplyULServiceImpl());

        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X
        Services.registerService(
            WEB3AdminAccOpenApplyDataDelService.class,
            new WEB3AdminAccOpenApplyDataDelServiceImpl());

        //�����J�݃��[���A�h���X�o�^�T�[�r�X
        Services.registerService(
            WEB3AccOpenMailAddressRegistService.class,
            new WEB3AccOpenMailAddressRegistServiceImpl());

        //�Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�X
        Services.registerService(
            WEB3AdminAccOpenDataTransferService.class,
            new WEB3AdminAccOpenDataTransferServiceImpl());

        //�Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService
        Services.registerService(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new WEB3AdminAccOpenDataTransferUnitServiceImpl());

        //Service �� Interceptor �ݒ菈�� ----------------------
        //�����g�����U�N�V�����ݒ�
        //�����J�ݓ`�[�쐬�T�[�r�X
        Services.addInterceptor(WEB3AccOpenVoucherCreatedService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ҍ����J�݊������[�����M�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����J�݊������[�����MUnitService
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenStateInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ҍ����J�ݐ\�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenRegistSearchService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X 
        Services.addInterceptor(
            WEB3AdminAccOpenJudgeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����J�݋��Z�@@�֌����T�[�r�X
        Services.addInterceptor(
            WEB3AccOpenFinInstitutionSearchService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����J�ݎ��ʃR�[�h�̔ԃT�[�r�X
        Services.addInterceptor(WEB3AccOpenRequestNumberService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�����J�ݏ��쐬�T�[�r�X
        Services.addInterceptor(WEB3AccOpenInfoCreatedService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����J�ݐ\���T�[�r�X
        Services.addInterceptor(WEB3AccOpenRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����J�ݓ`�[�o�^��tUnitService
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�����J�ݓ`�[�o�^��t�T�[�r�X
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�����J�݌ڋq�R�[�h�̔ԃT�[�r�X
        Services.addInterceptor(
            WEB3AccOpenAccountCodeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        // ���������J��UnitService
        Services.addInterceptor(
            WEB3AccOpenRealUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�e��A����tUnitService
        Services.addInterceptor(
            WEB3AccOpenInformAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenApplyULService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenApplyDataDelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�����J�݃��[���A�h���X�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AccOpenMailAddressRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        //�����J�ݓ`�[�쐬�T�[�r�X
        Services.addInterceptor(WEB3AccOpenVoucherCreatedService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ҍ����J�݊������[�����M�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendService.class,
            new WEB3LogSysTimeInterceptor());

        //�����J�݊������[�����MUnitService
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenStateInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ҍ����J�ݐ\�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenRegistSearchService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X 
        Services.addInterceptor(
            WEB3AdminAccOpenJudgeService.class,
            new WEB3LogSysTimeInterceptor());

        //�����J�݋��Z�@@�֌����T�[�r�X
        Services.addInterceptor(
            WEB3AccOpenFinInstitutionSearchService.class,
            new WEB3LogSysTimeInterceptor());

        //�����J�ݎ��ʃR�[�h�̔ԃT�[�r�X
        Services.addInterceptor(WEB3AccOpenRequestNumberService.class,
            new WEB3LogSysTimeInterceptor());

        //�����J�ݏ��쐬�T�[�r�X
        Services.addInterceptor(WEB3AccOpenInfoCreatedService.class,
            new WEB3LogSysTimeInterceptor());

        //�����J�ݐ\���T�[�r�X
        Services.addInterceptor(WEB3AccOpenRegistService.class,
            new WEB3LogSysTimeInterceptor());

        //�����J�ݓ`�[�o�^��tUnitService
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�����J�ݓ`�[�o�^��t�T�[�r�X
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����J�݌ڋq�R�[�h�̔ԃT�[�r�X
        Services.addInterceptor(
            WEB3AccOpenAccountCodeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenApplyULService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenApplyDataDelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����J�݃��[���A�h���X�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AccOpenMailAddressRegistService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        //�Ǘ��Ҍ����J�݊������[�����M�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendService.class,
            new WEB3AccOpenServiceInterceptor());

        //�����J�݊������[�����MUnitService
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendUnitService.class,
            new WEB3AccOpenServiceInterceptor());
        
        //�Ǘ��Ҍ����J�ݐR���T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenJudgeService.class,
            new WEB3AccOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AdminAccOpenJudgeService.class,
            new WEB3MQGatewayInterceptor());

        //�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X
        //�i�T�[�r�X�C���^�Z�v�^�CMQGateway�C���^�Z�v�^�j
        Services.addInterceptor(
            WEB3AdminAccOpenStateInquiryService.class,
            new WEB3AccOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AdminAccOpenStateInquiryService.class,
            new WEB3MQGatewayInterceptor());

        //�Ǘ��Ҍ����J�ݐ\�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenRegistSearchService.class,
            new WEB3AccOpenServiceInterceptor());

        //�����J�݋��Z�@@�֌����T�[�r�X
        //�����J�ݐ\���T�[�r�X
        Services.addInterceptor(WEB3AccOpenRegistService.class,
            new WEB3AccOpenInfoCreatedServiceInterceptor());

        //�����J�ݓ`�[�o�^��tUnitService
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new WEB3AccOpenVoucherRegAcceptUnitServiceInterceptor());
        
        // ���������J��UnitService
        Services.addInterceptor(
            WEB3AccOpenRealUnitService.class,
            new WEB3AccOpenRealUnitServiceInterceptor());

        //�e��A����tUnitService
        Services.addInterceptor(
            WEB3AccOpenInformAcceptUnitService.class,
            new WEB3AccOpenInformAcceptUnitServiceInterceptor());

        //�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAccOpenApplyULService.class,
            new WEB3AdminAccOpenApplyULServiceInterceptor());
        
        //�����J�݃��[���A�h���X�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AccOpenMailAddressRegistService.class,
            new WEB3AccOpenInfoCreatedServiceInterceptor());

        //�Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new WEB3AdminAccOpenDataTransferUnitServiceInterceptor());

        //�Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new WEB3MQGatewayInterceptor());

        // Message �̓o�^���� ----------------------
        //�Ǘ��Ҍ����J�݊������[�����M���N�G�X�g
        regClass(WEB3AdminAccOpenCompleteMailSendRequest.class);
        //�Ǘ��Ҍ����J�݊������[�����M���X�|���X
        regClass(WEB3AdminAccOpenCompleteMailSendResponse.class);

        //�Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g
        regClass(WEB3AdminAccOpenCompleteMailSendListRequest.class);
        //�Ǘ��Ҍ����J�݊������[�����M�ꗗ���X�|���X
        regClass(WEB3AdminAccOpenCompleteMailSendListResponse.class);

        //�Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g
        regClass(WEB3AdminAccOpenStateInquiryListRequest.class);
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���X�|���X
        regClass(WEB3AdminAccOpenStateInquiryListResponse.class);

        //�Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��N�G�X�g
        regClass(WEB3AdminAccOpenStateInquiryDetailRequest.class);
        //�Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��X�|���X
        regClass(WEB3AdminAccOpenStateInquiryDetailResponse.class);

        //�Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��N�G�X�g
        regClass(WEB3AdminAccOpenStateInquiryInputRequest.class);
        //�Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��X�|���X
        regClass(WEB3AdminAccOpenStateInquiryInputResponse.class);

        //�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g
        regClass(WEB3AdminAccOpenApplyDownloadRequest.class);
        //�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���X�|���X
        regClass(WEB3AdminAccOpenApplyDownloadResponse.class);

        //�Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g
        regClass(WEB3AdminAccOpenApplySearchInputRequest.class);
        //�Ǘ��Ҍ����J�ݐ\���������̓��X�|���X
        regClass(WEB3AdminAccOpenApplySearchInputResponse.class);

        //�Ǘ��Ҍ����J�ݐ\���X�V�m�F���N�G�X�g
        regClass(WEB3AdminAccOpenApplyUpdateConfirmRequest.class);
        //�Ǘ��Ҍ����J�ݐ\���X�V�m�F���X�|���X
        regClass(WEB3AdminAccOpenApplyUpdateConfirmResponse.class);

        //�Ǘ��Ҍ����J�ݐ\���X�V�������N�G�X�g
        regClass(WEB3AdminAccOpenApplyUpdateCompleteRequest.class);
        //�Ǘ��Ҍ����J�ݐ\���X�V�������X�|���X
        regClass(WEB3AdminAccOpenApplyUpdateCompleteResponse.class);

        //�Ǘ��Ҍ����J�ݓ`�[�쐬�m�F���N�G�X�g
        regClass(WEB3AdminAccOpenVoucherMakeConfirmRequest.class);
        //�Ǘ��Ҍ����J�ݓ`�[�쐬�m�F���X�|���X
        regClass(WEB3AdminAccOpenVoucherMakeConfirmResponse.class);

        //�Ǘ��Ҍ����J�ݓ`�[�쐬�������N�G�X�g
        regClass(WEB3AdminAccOpenVoucherMakeCompleteRequest.class);
        //�Ǘ��Ҍ����J�ݓ`�[�쐬�������X�|���X
        regClass(WEB3AdminAccOpenVoucherMakeCompleteResponse.class);

        //�Ǘ��Ҍ����J�ݓ`�[����m�F���N�G�X�g
        regClass(WEB3AdminAccOpenVoucherCancelConfirmRequest.class);
        //�Ǘ��Ҍ����J�ݓ`�[����m�F���X�|���X
        regClass(WEB3AdminAccOpenVoucherCancelConfirmResponse.class);

        //�Ǘ��Ҍ����J�ݓ`�[����������N�G�X�g
        regClass(WEB3AdminAccOpenVoucherCancelCompleteRequest.class);
        //�Ǘ��Ҍ����J�ݓ`�[����������X�|���X
        regClass(WEB3AdminAccOpenVoucherCancelCompleteResponse.class);
        
        //�Ǘ��Ҍ����J�ݐR���ꗗ���N�G�X�g
        regClass(WEB3AdminAccOpenInspectListRequest.class);
        //�Ǘ��Ҍ����J�ݐR���ꗗ���X�|���X
        regClass(WEB3AdminAccOpenInspectListResponse.class);
        //�Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������N�G�X�g
        regClass(WEB3AdminAccOpenInspectListSearchRequest.class);
        //�Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������X�|���X
        regClass(WEB3AdminAccOpenInspectListSearchResponse.class);
		//�Ǘ��Ҍ����J�ݐR�����F�m�F���N�G�X�g
        regClass(WEB3AdminAccOpenInspectConsentConfirmRequest.class);
		//�Ǘ��Ҍ����J�ݐR�����F�m�F���X�|���X
        regClass(WEB3AdminAccOpenInspectConsentConfirmResponse.class);
		//�Ǘ��Ҍ����J�ݐR�����F�������N�G�X�g
        regClass(WEB3AdminAccOpenInspectConsentCompleteRequest.class);
		//�Ǘ��Ҍ����J�ݐR�����F�������X�|���X
        regClass(WEB3AdminAccOpenInspectConsentCompleteResponse.class);
		//�Ǘ��Ҍ����J�ݐR���۔F�m�F���N�G�X�g
        regClass(WEB3AdminAccOpenInspectDenyConfirmRequest.class);
		//�Ǘ��Ҍ����J�ݐR���۔F�m�F���X�|���X
        regClass(WEB3AdminAccOpenInspectDenyConfirmResponse.class);
		//�Ǘ��Ҍ����J�ݐR���۔F�������N�G�X�g
        regClass(WEB3AdminAccOpenInspectDenyCompleteRequest.class);
		//�Ǘ��Ҍ����J�ݐR���۔F�������X�|���X
        regClass(WEB3AdminAccOpenInspectDenyCompleteResponse.class);

        //�����J�݋��Z�@@�֌������N�G�X�g
        regClass(WEB3AccOpenFinancialSearchRequest.class);
        //�����J�݋��Z�@@�֌������X�|���X
        regClass(WEB3AccOpenFinancialSearchResponse.class);

        //�����J�ݎx�X�������N�G�X�g
        regClass(WEB3AccOpenFinancialBranchSearchRequest.class);
        //�����J�ݎx�X�������X�|���X
        regClass(WEB3AccOpenFinancialBranchSearchResponse.class);

        //�����J�ݐ\���m�F���N�G�X�g
        regClass(WEB3AccOpenApplyConfirmRequest.class);
        //�����J�ݐ\���m�F���X�|���X
        regClass(WEB3AccOpenApplyConfirmResponse.class);

        //�����J�ݐ\���������N�G�X�g
        regClass(WEB3AccOpenApplyCompleteRequest.class);
        //�����J�ݐ\���������X�|���X
        regClass(WEB3AccOpenApplyCompleteResponse.class);

        //�����J�ݐ\�����̓��N�G�X�g
        regClass(WEB3AccOpenApplyInputRequest.class);
        //�����J�ݐ\�����̓��X�|���X
        regClass(WEB3AccOpenApplyInputResponse.class);

        //�����J�ݓ`�[�o�^��t���N�G�X�g
        regClass(WEB3AccOpenVoucherRegAcceptRequest.class);
        //�����J�ݓ`�[�o�^��t���X�|���X
        regClass(WEB3AccOpenVoucherRegAcceptResponse.class);

        //�Ǘ��Ҍ����J�ݐ\��UL���̓��N�G�X�g
        regClass(WEB3AdminAccOpenApplyUploadInputRequest.class);
        //�Ǘ��Ҍ����J�ݐ\��UL���̓��X�|���X
        regClass(WEB3AdminAccOpenApplyUploadInputResponse.class);

        //�Ǘ��Ҍ����J�ݐ\��UL�m�F���N�G�X�g
        regClass(WEB3AdminAccOpenApplyUploadConfirmRequest.class);
        //�Ǘ��Ҍ����J�ݐ\��UL�m�F���X�|���X
        regClass(WEB3AdminAccOpenApplyUploadConfirmResponse.class);

        //�Ǘ��Ҍ����J�ݐ\��UL�������N�G�X�g
        regClass(WEB3AdminAccOpenApplyUploadCompleteRequest.class);
        //�Ǘ��Ҍ����J�ݐ\��UL�������X�|���X
        regClass(WEB3AdminAccOpenApplyUploadCompleteResponse.class);

        //�Ǘ��Ҍ����J�ݐ\��UL���~���N�G�X�g
        regClass(WEB3AdminAccOpenApplyUploadCancelRequest.class);
        //�Ǘ��Ҍ����J�ݐ\��UL���~���X�|���X
        regClass(WEB3AdminAccOpenApplyUploadCancelResponse.class);

        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g
        regClass(WEB3AdminAccOpenApplyDataDelSearchRequest.class);
        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������X�|���X
        regClass(WEB3AdminAccOpenApplyDataDelSearchResponse.class);

        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���N�G�X�g
        regClass(WEB3AdminAccOpenApplyDataDelCnfRequest.class);
        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���X�|���X
        regClass(WEB3AdminAccOpenApplyDataDelCnfResponse.class);

        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g
        regClass(WEB3AdminAccOpenApplyDataDelCmpRequest.class);
        //�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������X�|���X
        regClass(WEB3AdminAccOpenApplyDataDelCmpResponse.class);
        
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        regClass(WEB3AccOpenMailAddrRegCompleteRequest.class);
        //�����J�݃��[���A�h���X�o�^�������X�|���X
        regClass(WEB3AccOpenMailAddrRegCompleteResponse.class);
        
        //�����J�݃��[���A�h���X�o�^���̓��N�G�X�g
        regClass(WEB3AccOpenMailAddrRegInputRequest.class);
        //�����J�݃��[���A�h���X�o�^���̓��X�|���X
        regClass(WEB3AccOpenMailAddrRegInputResponse.class);

        //�Ǘ��Ҍ����J�ݐؑփ��N�G�X�g
        regClass(WEB3AccOpenChangeRequest.class);
        //�Ǘ��Ҍ����J�ݐؑփ��X�|���X
        regClass(WEB3AccOpenChangeResponse.class);

        //�Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��N�G�X�g
        regClass(WEB3AdminAccOpenDataTransferInputRequest.class);

        //�Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��X�|���X
        regClass(WEB3AdminAccOpenDataTransferInputResponse.class);

        //�Ǘ��Ҍ����J�݃f�[�^�ڊǊ������N�G�X�g
        regClass(WEB3AdminAccOpenDataTransferCompleteRequest.class);

        //�Ǘ��Ҍ����J�݃f�[�^�ڊǊ������X�|���X
        regClass(WEB3AdminAccOpenDataTransferCompleteResponse.class);

        //Handler �̓o�^���� ----------------------
        //�Ǘ��Ҍ����J�݊������[�����M �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenCompleteMailSendListRequest.class,
            WEB3AdminAccOpenCompleteMailSendHandler.class,
            "mailSendListDisplay");

        //�Ǘ��Ҍ����J�݊������[�����M �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenCompleteMailSendRequest.class,
            WEB3AdminAccOpenCompleteMailSendHandler.class,
            "mailSend");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenStateInquiryInputRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "inputScreenDisplay");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenStateInquiryListRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "accOpenStatusListDisplay");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenStateInquiryDetailRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "accOpenStatusDetailDisplay");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUpdateConfirmRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "registUpdatedConfirm");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUpdateCompleteRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "registUpdatedComplete");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenVoucherMakeConfirmRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "voucherCreatedConfirm");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenVoucherMakeCompleteRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "voucherCreatedComplete");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenVoucherCancelConfirmRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "voucherCanceledConfirm");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenVoucherCancelCompleteRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "voucherCanceledComplete");

        //�Ǘ��Ҍ����J�ݏ󋵖⍇ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenChangeRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "change");

        //�Ǘ��Ҍ����J�ݐ\������ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplySearchInputRequest.class,
            WEB3AdminAccOpenRegistSearchHandler.class,
            "inputScreenDisplay");

        //�Ǘ��Ҍ����J�ݐ\������ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyDownloadRequest.class,
            WEB3AdminAccOpenRegistSearchHandler.class,
            "accOpenRegistDownload");

        //�����J�݋��Z�@@�֌��� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenFinancialSearchRequest.class,
            WEB3AccOpenFinInstitutionSearchHandler.class,
            "finInstitutionListDisplay");
        
        //�Ǘ��Ҍ����J�ݐR���n���h��
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectListSearchRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "inspectObjectListSearchScreen");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectListRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "screenList");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectConsentConfirmRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "consentConfirm");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectConsentCompleteRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "consentComplete");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectDenyConfirmRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "denyConfirm");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectDenyCompleteRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "denyComplete");

        //�����J�݋��Z�@@�֌��� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenFinancialBranchSearchRequest.class,
            WEB3AccOpenFinInstitutionSearchHandler.class,
            "finBranchListDisplay");

        //�����J�ݐ\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenApplyInputRequest.class,
            WEB3AccOpenRegistHandler.class,
            "inputScreenDisplay");

        //�����J�ݐ\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenApplyConfirmRequest.class,
            WEB3AccOpenRegistHandler.class,
            "registConfirm");

        //�����J�ݐ\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenApplyCompleteRequest.class,
            WEB3AccOpenRegistHandler.class,
            "registComplete");

        //�����J�ݓ`�[�o�^��t �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenVoucherRegAcceptRequest.class,
            WEB3AccOpenVoucherRegAcceptHandler.class,
            "accOpenVoucherRegAccept");

        //�Ǘ��Ҍ����J�ݐ\��UL���� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUploadInputRequest.class,
            WEB3AdminAccOpenApplyULHandler.class,
            "uploadScreenDisplay");

        //�Ǘ��Ҍ����J�ݐ\��UL�m�F �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUploadConfirmRequest.class,
            WEB3AdminAccOpenApplyULHandler.class,
            "uploadFileConfirm");

        //�Ǘ��Ҍ����J�ݐ\��UL���� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUploadCompleteRequest.class,
            WEB3AdminAccOpenApplyULHandler.class,
            "accOpenApplyUpload");

        //�Ǘ��Ҍ����J�ݐ\��UL���~ �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUploadCancelRequest.class,
            WEB3AdminAccOpenApplyULHandler.class,
            "uploadCancel");

        //�����J�ݎ��������f�[�^�폜������ʕ\�� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyDataDelSearchRequest.class,
            WEB3AdminAccOpenApplyDataDelHandler.class,
            "displaySearchScreen");

        //�����J�ݎ��������f�[�^�폜�m�F���� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyDataDelCnfRequest.class,
            WEB3AdminAccOpenApplyDataDelHandler.class,
            "deleteConfirm");

        //�����J�ݎ��������f�[�^�폜�������� �p�n���h���[�̓o�^
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyDataDelCmpRequest.class,
            WEB3AdminAccOpenApplyDataDelHandler.class,
            "deleteComplete");
        
        //�����J�݃��[���A�h���X�o�^�n���h��
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenMailAddrRegInputRequest.class,
            WEB3AccOpenMailAddressRegistHandler.class,
            "inputScreenDisplay");
        
        //�����J�݃��[���A�h���X�o�^�n���h��
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenMailAddrRegCompleteRequest.class,
            WEB3AccOpenMailAddressRegistHandler.class,
            "registComplete");

        //�Ǘ��Ҍ����J�݃f�[�^�ڊǃn���h��
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenDataTransferInputRequest.class,
            WEB3AdminAccOpenDataTransferHandler.class,
            "getInputScreen");

        //�Ǘ��Ҍ����J�݃f�[�^�ڊǃn���h��
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenDataTransferCompleteRequest.class,
            WEB3AdminAccOpenDataTransferHandler.class,
            "submitDataTransfer");

        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new WEB3AccOpenDescendRacCtxInterceptor());

        log.exiting(STR_METHOD_NAME);
    }
}
@
