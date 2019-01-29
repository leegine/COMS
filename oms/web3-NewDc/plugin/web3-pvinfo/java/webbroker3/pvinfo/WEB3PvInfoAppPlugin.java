head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-PvInfo �v���O�C��(WEB3PvInfoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 �����F (���u) �V�K�쐬
*/
package webbroker3.pvinfo;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.pvinfo.data.WEB3PvInfoAccountDatabaseExtensions;
import webbroker3.pvinfo.data.WEB3PvInfoMasterDatabaseExtensions;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionChangeHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionDelHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionDetailHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionListHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoConditionRegistHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoDirectChangeHandler;
import webbroker3.pvinfo.handler.WEB3AdminPvInfoDirectRegistHandler;
import webbroker3.pvinfo.handler.WEB3PvInfoPrivateInformationDetailHandler;
import webbroker3.pvinfo.handler.WEB3PvInfoPrivateInformationListHandler;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectRegistInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteRequest;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateRequest;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailRequest;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionChangeService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDelService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDetailService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionListService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionRegistService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectChangeService;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectRegistService;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationDetailService;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationListService;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionChangeServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionDelServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionDetailServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionListServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoConditionRegistServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoDirectChangeServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoDirectRegistServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3PvInfoPrivateInformationDetailServiceImpl;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3PvInfoPrivateInformationListServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-PvInfoInfo �v���O�C���N���X�B
 *                                                                
 * @@author �����F
 * @@version 1.0
 */
public final class WEB3PvInfoAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3PvInfoAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3PvInfoAppPlugin()";
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

        plug(WEB3PvInfoAppPlugin.class);

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
        
        //DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3PvInfoMasterDatabaseExtensions.plug();
        WEB3PvInfoAccountDatabaseExtensions.plug();
        
        //Service �̓o�^���� ----------------------
        
        //��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ
        Services.registerService(WEB3PvInfoDataManager.class, new WEB3PvInfoDataManagerImpl());
        
        //�Ǘ��ҕ\���ݒ�ύX�T�[�r�X
        Services.registerService(WEB3AdminPvInfoConditionChangeService.class, new WEB3AdminPvInfoConditionChangeServiceImpl());
        
        //�Ǘ��ҕ\���ݒ�폜�T�[�r�X
        Services.registerService(WEB3AdminPvInfoConditionDelService.class, new WEB3AdminPvInfoConditionDelServiceImpl());
        
        //�Ǘ��ҕ\���ݒ�ڍ׃T�[�r�X
        Services.registerService(WEB3AdminPvInfoConditionDetailService.class, new WEB3AdminPvInfoConditionDetailServiceImpl());
        
        //�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X
        Services.registerService(WEB3AdminPvInfoConditionListService.class, new WEB3AdminPvInfoConditionListServiceImpl());
        
        //�Ǘ��ҕ\���ݒ�o�^�T�[�r�X
        Services.registerService(WEB3AdminPvInfoConditionRegistService.class, new WEB3AdminPvInfoConditionRegistServiceImpl());
        
        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X
        Services.registerService(WEB3AdminPvInfoDirectChangeService.class, new WEB3AdminPvInfoDirectChangeServiceImpl());
        
        //�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X
        Services.registerService(WEB3AdminPvInfoDirectRegistService.class, new WEB3AdminPvInfoDirectRegistServiceImpl());
        
        //��ײ�ްĲ�̫Ұ��ݏڍ׃T�[�r�X
        Services.registerService(WEB3PvInfoPrivateInformationDetailService.class, new WEB3PvInfoPrivateInformationDetailServiceImpl());
        
        //��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X
        Services.registerService(WEB3PvInfoPrivateInformationListService.class, new WEB3PvInfoPrivateInformationListServiceImpl());
        

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�        
        
        //�Ǘ��ҕ\���ݒ�ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminPvInfoConditionChangeService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҕ\���ݒ�폜�T�[�r�X
        Services.addInterceptor(WEB3AdminPvInfoConditionDelService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionDelService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionDelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҕ\���ݒ�ڍ׃T�[�r�X
        Services.addInterceptor(WEB3AdminPvInfoConditionDetailService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionDetailService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionDetailService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminPvInfoConditionListService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��ҕ\���ݒ�o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminPvInfoConditionRegistService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoConditionRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminPvInfoDirectChangeService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoDirectChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoDirectChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҃_�C���N�g�w��o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminPvInfoDirectRegistService.class, new WEB3AdminPvInfoServiceInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoDirectRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminPvInfoDirectRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //��ײ�ްĲ�̫Ұ��ݏڍ׃T�[�r�X
        Services.addInterceptor(WEB3PvInfoPrivateInformationDetailService.class, new WEB3PvInfoPrivateInformationDetailServiceInterceptor());
        Services.addInterceptor(WEB3PvInfoPrivateInformationDetailService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3PvInfoPrivateInformationDetailService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //��ײ�ްĲ�̫Ұ��݈ꗗ�T�[�r�X
        Services.addInterceptor(WEB3PvInfoPrivateInformationListService.class, new WEB3PvInfoPrivateInformationListServiceInterceptor());
        Services.addInterceptor(WEB3PvInfoPrivateInformationListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3PvInfoPrivateInformationListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Message �̓o�^���� ----------------------
        //�_�C���N�g�w�胁�b�Z�[�W�폜���N�G�X�g 
        regClass(WEB3PvInfoDirectMessageDeleteRequest.class);
        //�_�C���N�g�w�胁�b�Z�[�W�폜���X�|���X 
        regClass(WEB3PvInfoDirectMessageDeleteResponse.class);
        
        //��ײ�ްĲ�̫Ұ��ݏڍ׃��N�G�X�g    
        regClass(WEB3PvInfoPrivateInformationDetailRequest.class);
        //��ײ�ްĲ�̫Ұ��ݏڍ׃��X�|���X    
        regClass(WEB3PvInfoPrivateInformationDetailResponse.class);

        //�ڋq�A�����N�G�X�g   
        regClass(WEB3PvInfoAccountConnectionRequest.class);
        //�ڋq�A�����X�|���X   
        regClass(WEB3PvInfoAccountConnectionResponse.class);

        //�،���ИA�����N�G�X�g 
        regClass(WEB3PvInfoInstitutionConnectionRequest.class);
        //�،���ИA�����X�|���X 
        regClass(WEB3PvInfoInstitutionConnectionResponse.class);

        //�������󋵃��N�G�X�g 
        regClass(WEB3PvInfoOrderExecStateRequest.class);
        //�������󋵃��X�|���X 
        regClass(WEB3PvInfoOrderExecStateResponse.class);

        //�Ǘ��ҁE���ݏ󋵍X�V���N�G�X�g 
        regClass(WEB3AdminPvInfoConditionUpdateRequest.class);
        //�Ǘ��ҁE���ݏ󋵍X�V���X�|���X 
        regClass(WEB3AdminPvInfoConditionUpdateResponse.class);
        
        //�Ǘ��ҁE�\���ݒ�ꗗ���N�G�X�g 
        regClass(WEB3AdminPvInfoConditionListRequest.class);
        //�Ǘ��ҁE�\���ݒ�ꗗ���X�|���X
        regClass(WEB3AdminPvInfoConditionListResponse.class);

        //�Ǘ��ҁE�\���ݒ�ڍ׃��N�G�X�g 
        regClass(WEB3AdminPvInfoConditionDetailRequest.class);
        //�Ǘ��ҁE�\���ݒ�ڍ׃��X�|���X 
        regClass(WEB3AdminPvInfoConditionDetailResponse.class);

        //�Ǘ��ҁE�\���ݒ�o�^���̓��N�G�X�g   
        regClass(WEB3AdminPvInfoConditionRegistInputRequest.class);
        //�Ǘ��ҁE�\���ݒ�o�^���̓��X�|���X   
        regClass(WEB3AdminPvInfoConditionRegistInputResponse.class);
        
        //�Ǘ��ҁE�\���ݒ�o�^�m�F���N�G�X�g   
        regClass(WEB3AdminPvInfoConditionRegistConfirmRequest.class);
        //�Ǘ��ҁE�\���ݒ�o�^�m�F���X�|���X   
        regClass(WEB3AdminPvInfoConditionRegistConfirmResponse.class);
        
        //�Ǘ��ҁE�\���ݒ�o�^�������N�G�X�g   
        regClass(WEB3AdminPvInfoConditionRegistCompleteRequest.class);
        //�Ǘ��ҁE�\���ݒ�o�^�������X�|���X   
        regClass(WEB3AdminPvInfoConditionRegistCompleteResponse.class);
        
        //�Ǘ��ҁE�\���ݒ�ύX���̓��N�G�X�g   
        regClass(WEB3AdminPvInfoConditionChangeInputRequest.class);
        //�Ǘ��ҁE�\���ݒ�ύX���̓��X�|���X   
        regClass(WEB3AdminPvInfoConditionChangeInputResponse.class);
        
        //�Ǘ��ҁE�\���ݒ�ύX�m�F���N�G�X�g   
        regClass(WEB3AdminPvInfoConditionChangeConfirmRequest.class);
        //�Ǘ��ҁE�\���ݒ�ύX�m�F���X�|���X   
        regClass(WEB3AdminPvInfoConditionChangeConfirmResponse.class);
        
        //�Ǘ��ҁE�\���ݒ�ύX�������N�G�X�g   
        regClass(WEB3AdminPvInfoConditionChangeCompleteRequest.class);
        //�Ǘ��ҁE�\���ݒ�ύX�������X�|���X   
        regClass(WEB3AdminPvInfoConditionChangeCompleteResponse.class);
        
        //�Ǘ��ҁE�\���ݒ�폜�m�F���N�G�X�g   
        regClass(WEB3AdminPvInfoConditionDelConfirmRequest.class);
        //�Ǘ��ҁE�\���ݒ�폜�m�F���X�|���X   
        regClass(WEB3AdminPvInfoConditionDelConfirmResponse.class);
        
        //�Ǘ��ҁE�\���ݒ�폜�������N�G�X�g   
        regClass(WEB3AdminPvInfoConditionDelCompleteRequest.class);
        //�Ǘ��ҁE�\���ݒ�폜�������X�|���X   
        regClass(WEB3AdminPvInfoConditionDelCompleteResponse.class);
        
        //�Ǘ��ҁE�_�C���N�g�w��o�^���̓��N�G�X�g    
        regClass(WEB3AdminPvInfoDirectRegistInputRequest.class);
        //�Ǘ��ҁE�_�C���N�g�w��o�^���̓��X�|���X    
        regClass(WEB3AdminPvInfoDirectRegistInputResponse.class);
        
        //�Ǘ��ҁE�_�C���N�g�w��o�^�m�F���N�G�X�g    
        regClass(WEB3AdminPvInfoDirectRegistConfirmRequest.class);
        //�Ǘ��ҁE�_�C���N�g�w��o�^�m�F���X�|���X    
        regClass(WEB3AdminPvInfoDirectRegistConfirmResponse.class);
        
        //�Ǘ��ҁE�_�C���N�g�w��o�^�������N�G�X�g    
        regClass(WEB3AdminPvInfoDirectRegistCompleteRequest.class);
        //�Ǘ��ҁE�_�C���N�g�w��o�^�������X�|���X    
        regClass(WEB3AdminPvInfoDirectRegistCompleteResponse.class);
        
        //�Ǘ��ҁE�_�C���N�g�w��o�^�A�b�v���[�h���~���N�G�X�g  
        regClass(WEB3AdminPvInfoDirectRegistCancelRequest.class);
        //�Ǘ��ҁE�_�C���N�g�w��o�^�A�b�v���[�h���~���X�|���X  
        regClass(WEB3AdminPvInfoDirectRegistCancelResponse.class);
        
        //�Ǘ��ҁE�_�C���N�g�w��ύX���̓��N�G�X�g    
        regClass(WEB3AdminPvInfoDirectChangeInputRequest.class);
        //�Ǘ��ҁE�_�C���N�g�w��ύX���̓��X�|���X    
        regClass(WEB3AdminPvInfoDirectChangeInputResponse.class);
        
        //�Ǘ��ҁE�_�C���N�g�w��ύX�m�F���N�G�X�g    
        regClass(WEB3AdminPvInfoDirectChangeConfirmRequest.class);
        //�Ǘ��ҁE�_�C���N�g�w��ύX�m�F���X�|���X    
        regClass(WEB3AdminPvInfoDirectChangeConfirmResponse.class);
        
        //�Ǘ��ҁE�_�C���N�g�w��ύX�������N�G�X�g    
        regClass(WEB3AdminPvInfoDirectChangeCompleteRequest.class);
        //�Ǘ��ҁE�_�C���N�g�w��ύX�������X�|���X    
        regClass(WEB3AdminPvInfoDirectChangeCompleteResponse.class);
        
        //�Ǘ��ҁE�_�C���N�g�w��ύX�A�b�v���[�h���~���N�G�X�g  
        regClass(WEB3AdminPvInfoDirectChangeCancelRequest.class);
        //�Ǘ��ҁE�_�C���N�g�w��ύX�A�b�v���[�h���~���X�|���X  
        regClass(WEB3AdminPvInfoDirectChangeCancelResponse.class);
        
        //Handler �̓o�^���� ----------------------
        
        //�_�C���N�g�w�胁�b�Z�[�W�폜 �p�n���h���[�̓o�^
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoDirectMessageDeleteRequest.class, WEB3PvInfoPrivateInformationDetailHandler.class, "deleteDirectMessage");
        
        //��ײ�ްĲ�̫Ұ��ݏڍ� �p�n���h���[�̓o�^    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoPrivateInformationDetailRequest.class, WEB3PvInfoPrivateInformationDetailHandler.class, "getDetailedScreen");
        
        //�ڋq�A�� �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoAccountConnectionRequest.class, WEB3PvInfoPrivateInformationListHandler.class, "getAccountConnectionScreen");
        
        //�،���ИA�� �p�n���h���[�̓o�^ 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoInstitutionConnectionRequest.class, WEB3PvInfoPrivateInformationListHandler.class, "getInstitutionConnectionScreen");
        
        //�������� �p�n���h���[�̓o�^ 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3PvInfoOrderExecStateRequest.class, WEB3PvInfoPrivateInformationListHandler.class, "getOrderExecStateScreen");
        
        //�Ǘ��ҁE���ݏ󋵍X�V �p�n���h���[�̓o�^ 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionUpdateRequest.class, WEB3AdminPvInfoConditionListHandler.class, "updateCondition");
        
        //�Ǘ��ҁE�\���ݒ�ꗗ �p�n���h���[�̓o�^ 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionListRequest.class, WEB3AdminPvInfoConditionListHandler.class, "getConditionListScreen");
        
        //�Ǘ��ҁE�\���ݒ�ڍ� �p�n���h���[�̓o�^ 
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionDetailRequest.class, WEB3AdminPvInfoConditionDetailHandler.class, "getConditionDetailScreen");
        
        //�Ǘ��ҁE�\���ݒ�o�^���� �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionRegistInputRequest.class, WEB3AdminPvInfoConditionRegistHandler.class, "getConditionRegistInputScreen"); 
        
        //�Ǘ��ҁE�\���ݒ�o�^�m�F �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionRegistConfirmRequest.class, WEB3AdminPvInfoConditionRegistHandler.class, "confirmConditionRegist"); 
        
        //�Ǘ��ҁE�\���ݒ�o�^���� �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionRegistCompleteRequest.class, WEB3AdminPvInfoConditionRegistHandler.class, "completeConditionRegist");
        
        //�Ǘ��ҁE�\���ݒ�ύX���� �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionChangeInputRequest.class, WEB3AdminPvInfoConditionChangeHandler.class, "getConditionChangeInputScreen"); 
        
        //�Ǘ��ҁE�\���ݒ�ύX�m�F �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionChangeConfirmRequest.class, WEB3AdminPvInfoConditionChangeHandler.class, "confirmConditionChange"); 
        
        //�Ǘ��ҁE�\���ݒ�ύX���� �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionChangeCompleteRequest.class, WEB3AdminPvInfoConditionChangeHandler.class, "completeConditionChange");
        
        //�Ǘ��ҁE�\���ݒ�폜�m�F �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionDelConfirmRequest.class, WEB3AdminPvInfoConditionDelHandler.class, "confirmConditionDel"); 
        
        //�Ǘ��ҁE�\���ݒ�폜���� �p�n���h���[�̓o�^   
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoConditionDelCompleteRequest.class, WEB3AdminPvInfoConditionDelHandler.class, "completeConditionDel");
        
        //�Ǘ��ҁE�_�C���N�g�w��o�^���� �p�n���h���[�̓o�^    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectRegistInputRequest.class, WEB3AdminPvInfoDirectRegistHandler.class, "getDirectRegistInputScreen"); 
        
        //�Ǘ��ҁE�_�C���N�g�w��o�^�m�F �p�n���h���[�̓o�^    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectRegistConfirmRequest.class, WEB3AdminPvInfoDirectRegistHandler.class, "confirmDirectRegist"); 
        
        //�Ǘ��ҁE�_�C���N�g�w��o�^���� �p�n���h���[�̓o�^    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectRegistCompleteRequest.class, WEB3AdminPvInfoDirectRegistHandler.class, "completeDirectRegist"); 
        
        //�Ǘ��ҁE�_�C���N�g�w��o�^�A�b�v���[�h���~ �p�n���h���[�̓o�^  
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectRegistCancelRequest.class, WEB3AdminPvInfoDirectRegistHandler.class, "undoDirectRegistUpload"); 
        
        //�Ǘ��ҁE�_�C���N�g�w��ύX���� �p�n���h���[�̓o�^    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectChangeInputRequest.class, WEB3AdminPvInfoDirectChangeHandler.class, "getDirectChangeInputScreen"); 
        
        //�Ǘ��ҁE�_�C���N�g�w��ύX�m�F �p�n���h���[�̓o�^    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectChangeConfirmRequest.class, WEB3AdminPvInfoDirectChangeHandler.class, "confirmDirectChange"); 
        
        //�Ǘ��ҁE�_�C���N�g�w��ύX���� �p�n���h���[�̓o�^    
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectChangeCompleteRequest.class, WEB3AdminPvInfoDirectChangeHandler.class, "completeDirectChange"); 
        
        //�Ǘ��ҁE�_�C���N�g�w��ύX�A�b�v���[�h���~ �p�n���h���[�̓o�^  
        regHandler(WEB3PvInfoAppPlugin.class, WEB3AdminPvInfoDirectChangeCancelRequest.class, WEB3AdminPvInfoDirectChangeHandler.class, "undoDirectChangeUpload"); 

        log.exiting(STR_METHOD_NAME);
    }  
}
@
