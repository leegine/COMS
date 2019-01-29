head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocAdminAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-DocAdmin �v���O�C���N���X(WEB3DocAdminAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/10  �����q(���u) �V�K�쐬
Revision History : 2007/11/06 �Ӑ� (���u) �d�l�ύX�E���f�� No.010,No.011
Revision History : 2007/12/11 ���g (���u) �d�l�ύX�E���f�� No.012,No.013
Revision History : 2008/03/03 �g�C�� (���u) �d�l�ύX�E ���f��No.037
*/

package webbroker3.docadmin;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.docadmin.data.WEB3DocadminSessionDatabaseExtensions;
import webbroker3.docadmin.handler.WEB3AdminFPTDeleteHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTDocumentListReferenceHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTDocumentUpdateHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTForceLogoutHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTForceLogoutMainHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTListReferenceHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTRegistHandler;
import webbroker3.docadmin.handler.WEB3AdminFPTUploadHandler;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUpdateCommonRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDeleteService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentListReferenceService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentUpdateService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutMainService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutUnitService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTListReferenceService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTRegistService;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTUploadService;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDeleteServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentListReferenceServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentUpdateServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTForceLogoutServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTListReferenceServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTRegistServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTUploadServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTForceLogoutMainServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTForceLogoutUnitServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-DocAdmin �v���O�C���N���X�B
 *
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3DocAdminAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3DocAdminAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3DocAdminAppPlugin()
    {

    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3DocAdminAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ---------------------- 1 Service �̓o�^���� ----------------------
        // �Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�X
        Services.registerService(
            WEB3AdminFPTListReferenceService.class,
            new WEB3AdminFPTListReferenceServiceImpl());
        // �Ǘ��ҋ����@@��t�{���o�^�T�[�r�X
        Services.registerService(
            WEB3AdminFPTRegistService.class,
            new WEB3AdminFPTRegistServiceImpl());
        //�Ǘ��ҋ����@@��t�{���폜�T�[�r�X
        Services.registerService(
            WEB3AdminFPTDeleteService.class,
            new WEB3AdminFPTDeleteServiceImpl());

        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X
        Services.registerService(
            WEB3AdminFPTUploadService.class,
            new WEB3AdminFPTUploadServiceImpl());
        
        //�Ǘ��ҋ����@@��t���ʏƉ�T�[�r�X
        Services.registerService(
            WEB3AdminFPTDocumentListReferenceService.class,
            new WEB3AdminFPTDocumentListReferenceServiceImpl());
        
        //�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X
        Services.registerService(
            WEB3AdminFPTDocumentUpdateService.class,
            new WEB3AdminFPTDocumentUpdateServiceImpl());

        //�Ǘ��ҏ��ʖ����� �������O�A�E�g�T�[�r�X
        Services.registerService(
            WEB3AdminFPTForceLogoutService.class,
            new WEB3AdminFPTForceLogoutServiceImpl());
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g���C���T�[�r�X
        Services.registerService(
            WEB3AdminFPTForceLogoutMainService.class,
            new WEB3AdminFPTForceLogoutMainServiceImpl());
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�X
        Services.registerService(
                WEB3AdminFPTForceLogoutUnitService.class,
                new WEB3AdminFPTForceLogoutUnitServiceImpl());        
        
        // ---------------------- 2 Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        // �Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTListReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        // �Ǘ��ҋ����@@��t�{���o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTRegistService.class,
            new WEB3LogSysTimeInterceptor());
        //�Ǘ��ҋ����@@��t�{���폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTDeleteService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTUploadService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ� ���ʖ����� �������O�A�E�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g���C���T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutMainService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�X
        Services.addInterceptor(
                WEB3AdminFPTForceLogoutUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // ---------------------- 3 Service �� ServiceInterceptor ��ݒ肷�� ----------------------

        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X
        Services.addInterceptor(WEB3AdminFPTUploadService.class,
            new WEB3AdminFPTUploadInterceptor());
        
        //�Ǘ� ���ʖ����� �������O�A�E�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutService.class,
            new WEB3AdminFPTForceLogoutInterceptor());

        // ---------------------- 4 Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        //�Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTListReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        // �Ǘ��ҋ����@@��t�{���o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTRegistService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        //�Ǘ��ҋ����@@��t�{���폜�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTDeleteService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTUploadService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ� ���ʖ����� �������O�A�E�g�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutService.class,
            new TransactionalInterceptor(
                    TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g���C���T�[�r�X
        Services.addInterceptor(
            WEB3AdminFPTForceLogoutMainService.class,
            new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�X
        Services.addInterceptor(
                WEB3AdminFPTForceLogoutUnitService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // ---------------------- 5 Message �̓o�^���� ----------------------
        //�Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g
        regClass(WEB3AdminFPTListReferenceRequest.class);
        //�Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�X�|���X
        regClass(WEB3AdminFPTListReferenceResponse.class);
        
        //�Ǘ��ҋ����@@��t�{���o�^�������N�G�X�g
        regClass(WEB3AdminFPTRegistCompleteRequest.class);
        //�Ǘ��ҋ����@@��t�{���o�^�������X�|���X
        regClass(WEB3AdminFPTRegistCompleteResponse.class);
        
        //�Ǘ��ҋ����@@��t�{���o�^�m�F���N�G�X�g
        regClass(WEB3AdminFPTRegistConfirmRequest.class);
        //�Ǘ��ҋ����@@��t�{���o�^�m�F���X�|���X
        regClass(WEB3AdminFPTRegistConfirmResponse.class);
        
        //�Ǘ��ҋ����@@��t�{���o�^���̓��N�G�X�g
        regClass(WEB3AdminFPTRegistInputRequest.class);
        //�Ǘ��ҋ����@@��t�{���o�^���̓��X�|���X
        regClass(WEB3AdminFPTRegistInputResponse.class);
        
        //�Ǘ��ҋ����@@��t�{���������̓��N�G�X�g
        regClass(WEB3AdminFPTSearchInputRequest.class);
        //�Ǘ��ҋ����@@��t�{���������̓��X�|���X
        regClass(WEB3AdminFPTSearchInputResponse.class);

        //�Ǘ��ҋ����@@��t�{���폜�m�F���N�G�X�g
        regClass(WEB3AdminFPTDeleteConfirmRequest.class);
        //�Ǘ��ҋ����@@��t�{���폜�m�F���X�|���X
        regClass(WEB3AdminFPTDeleteConfirmResponse.class);

        //�Ǘ��ҋ����@@��t�{���폜�������N�G�X�g
        regClass(WEB3AdminFPTDeleteCompleteRequest.class);
        //�Ǘ��ҋ����@@��t�{���폜�������X�|���X
        regClass(WEB3AdminFPTDeleteCompleteResponse.class);

        //�Ǘ��ҋ����@@��t�X�V���ʃ��N�G�X�g
        regClass(WEB3AdminFPTUpdateCommonRequest.class);

        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h���~���N�G�X�g
        regClass(WEB3AdminFPTUploadCancelRequest.class);
        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h���~���X�|���X
        regClass(WEB3AdminFPTUploadCancelResponse.class);

        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h�������N�G�X�g
        regClass(WEB3AdminFPTUploadCompleteRequest.class);
        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h�������X�|���X
        regClass(WEB3AdminFPTUploadCompleteResponse.class);

        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F���N�G�X�g
        regClass(WEB3AdminFPTUploadConfirmRequest.class);
        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h�m�F���X�|���X
        regClass(WEB3AdminFPTUploadConfirmResponse.class);

        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h���̓��N�G�X�g
        regClass(WEB3AdminFPTUploadInputRequest.class);
        //�Ǘ��ҋ����@@��t�{���A�b�v���[�h���̓��X�|���X
        regClass(WEB3AdminFPTUploadInputResponse.class);
        
        //�Ǘ��ҋ����@@��t���ʍX�V���̓��X�|���X�N���X
        regClass(WEB3AdminFPTDocumentUpdateInputResponse.class);
        //�Ǘ��ҋ����@@��t���ʍX�V���̓��N�G�X�g�N���X
        regClass(WEB3AdminFPTDocumentUpdateInputRequest.class);
        //�Ǘ��ҋ����@@��t���ʍX�V�m�F���X�|���X�N���X
        regClass(WEB3AdminFPTDocumentUpdateConfirmResponse.class);
        //�Ǘ��ҋ����@@��t���ʍX�V�m�F���N�G�X�g�N���X
        regClass(WEB3AdminFPTDocumentUpdateConfirmRequest.class);
        //�Ǘ��ҋ����@@��t���ʍX�V�������X�|���X�N���X
        regClass(WEB3AdminFPTDocumentUpdateCompleteResponse.class);
        //�Ǘ��ҋ����@@��t���ʍX�V�������N�G�X�g�N���X
        regClass(WEB3AdminFPTDocumentUpdateCompleteRequest.class);
        //�Ǘ��ҋ����@@��t���ʏƉ�����̓��X�|���X�N���X
        regClass(WEB3AdminFPTDocumentListSearchInputResponse.class);
        //�Ǘ��ҋ����@@��t���ʏƉ�����̓��N�G�X�g�N���X
        regClass(WEB3AdminFPTDocumentListSearchInputRequest.class);
        //�Ǘ��ҋ����@@��t���ʏƉ�ꗗ���X�|���X�N���X
        regClass(WEB3AdminFPTDocumentListReferenceResponse.class);
        //�Ǘ��ҋ����@@��t���ʏƉ�ꗗ���N�G�X�g�N���X
        regClass(WEB3AdminFPTDocumentListReferenceRequest.class);
       
        //�Ǘ��ҏ��ʖ������������O�A�E�g���̓��N�G�X�g
        regClass(WEB3AdminFPTForceLogoutInputRequest.class);
        //�Ǘ��ҏ��ʖ������������O�A�E�g���̓��X�|���X
        regClass(WEB3AdminFPTForceLogoutInputResponse.class);
        
        //�Ǘ��ҏ��ʖ������������O�A�E�g�m�F���N�G�X�g
        regClass(WEB3AdminFPTForceLogoutConfirmRequest.class);
        //�Ǘ��ҏ��ʖ������������O�A�E�g�m�F���X�|���X
        regClass(WEB3AdminFPTForceLogoutConfirmResponse.class);
        
        //�Ǘ��ҏ��ʖ������������O�A�E�g�������N�G�X�g
        regClass(WEB3AdminFPTForceLogoutCompleteRequest.class);
        //�Ǘ��ҏ��ʖ������������O�A�E�g�������X�|���X
        regClass(WEB3AdminFPTForceLogoutCompleteResponse.class);
        
        //�Ǘ��ҏ��ʖ������������O�A�E�g���ʏƉ�N�G�X�g
        regClass(WEB3AdminFPTForceLogoutReferenceRequest.class);
        //�Ǘ��ҏ��ʖ������������O�A�E�g���ʏƉ�X�|���X
        regClass(WEB3AdminFPTForceLogoutReferenceResponse.class);
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g���C�����N�G�X�g
        regClass(WEB3AdminFPTForceLogoutMainRequest.class);
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g���C�����X�|���X
        regClass(WEB3AdminFPTForceLogoutMainResponse.class);

        // ---------------------- 6 Handler �̓o�^���� ----------------------
        //�Ǘ��ҋ����@@��t�{���Ɖ�n���h��
        //get��t�{���ꗗ�������
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTSearchInputRequest.class,
            WEB3AdminFPTListReferenceHandler.class,
            "getListSearchScreen");

        //get��t�{���ꗗ�Ɖ���
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTListReferenceRequest.class,
            WEB3AdminFPTListReferenceHandler.class,
            "getListReferenceScreen");

        //�Ǘ��ҋ����@@��t�{���o�^�n���h��
        //get�����@@��t�{���o�^����
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTRegistInputRequest.class,
            WEB3AdminFPTRegistHandler.class,
            "getRegistInput");

        //get�����@@��t�{���o�^�m�F
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTRegistConfirmRequest.class,
            WEB3AdminFPTRegistHandler.class,
            "getRegistConfirm");

        //get�����@@��t�{���o�^����
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTRegistCompleteRequest.class,
            WEB3AdminFPTRegistHandler.class,
            "getRegistComplete");

        //get�����@@��t�{���폜�m�F
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDeleteConfirmRequest.class,
            WEB3AdminFPTDeleteHandler.class,
            "getDeleteConfirm");

        //get�����@@��t�{���폜����
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDeleteCompleteRequest.class,
            WEB3AdminFPTDeleteHandler.class,
            "getDeleteComplete");

        //�A�b�v���[�h��ʕ\��
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTUploadInputRequest.class,
            WEB3AdminFPTUploadHandler.class,
            "uploadScreenDisplay");

        //�A�b�v���[�h�t�@@�C���m�F
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTUploadConfirmRequest.class,
            WEB3AdminFPTUploadHandler.class,
            "uploadFileConfirm");

        //�����@@��t�{���A�b�v���[�h
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTUploadCompleteRequest.class,
            WEB3AdminFPTUploadHandler.class,
            "adminFPTUpload");

        //�A�b�v���[�h���~
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTUploadCancelRequest.class,
            WEB3AdminFPTUploadHandler.class,
            "uploadCancel");
        
        //�Ǘ��ҋ����@@��t���ʏƉ�n���h��
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentListSearchInputRequest.class,
            WEB3AdminFPTDocumentListReferenceHandler.class,    
            "getDocumentReferenceSearchInput");
        //�Ǘ��ҋ����@@��t���ʏƉ�n���h��
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentListReferenceRequest.class,
            WEB3AdminFPTDocumentListReferenceHandler.class,    
            "getDocumentReferenceList");
    
        //�Ǘ��ҋ����@@��t���ʍX�V�n���h��
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentUpdateInputRequest.class,
            WEB3AdminFPTDocumentUpdateHandler.class,    
            "getDocumentUpdateInput");
        
        //�Ǘ��ҋ����@@��t���ʍX�V�n���h��
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentUpdateConfirmRequest.class,
            WEB3AdminFPTDocumentUpdateHandler.class,    
            "getDocumentUpdateConfirm");
        
        //�Ǘ��ҋ����@@��t���ʍX�V�n���h��
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTDocumentUpdateCompleteRequest.class,
            WEB3AdminFPTDocumentUpdateHandler.class,    
            "getDocumentUpdateComplete");

        //�Ǘ� ���ʖ����� �������O�A�E�g�n���h�� get���͉��
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutInputRequest.class,
            WEB3AdminFPTForceLogoutHandler.class,
            "getInputPage");
        
        //�Ǘ� ���ʖ����� �������O�A�E�g�n���h�� validate�������O�A�E�g
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutConfirmRequest.class,
            WEB3AdminFPTForceLogoutHandler.class,
            "validateForceLogout");
        
        //�Ǘ� ���ʖ����� �������O�A�E�g�n���h�� submit�������O�A�E�g
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutCompleteRequest.class,
            WEB3AdminFPTForceLogoutHandler.class,
            "submitForceLogout");
        
        //�Ǘ� ���ʖ����� �������O�A�E�g�n���h�� ���ʏƉ��ʕ\������
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutReferenceRequest.class,
            WEB3AdminFPTForceLogoutHandler.class,
            "getResultRefrence");
        
        //�Ǘ��� ���ʖ����� �������O�A�E�g���C���n���h��
        regHandler(
            WEB3DocAdminAppPlugin.class,
            WEB3AdminFPTForceLogoutMainRequest.class,
            WEB3AdminFPTForceLogoutMainHandler.class,
            "execForceLogout");
        
        // ---------------------- 7 DB �̓o�^���� ----------------------
        WEB3DocadminSessionDatabaseExtensions.plug();
        
        log.exiting(METHOD_NAME);
    }
}
@
