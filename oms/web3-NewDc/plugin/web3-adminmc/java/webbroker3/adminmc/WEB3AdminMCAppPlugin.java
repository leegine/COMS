head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Adminmc �v���O�C��(WEB3AdminMCAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/04 �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.adminmc.handler.WEB3AdminMCAdminChangeHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminDeleteHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminListHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminMenuSubMenuDisplayHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPermGrpChangeHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPermGrpDeleteHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPermGrpListHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPermGrpRegistHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPwdLockCancelHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminRegistHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorChangeHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorDeleteHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorListHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorPwdLockCancelHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorRegistHandler;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputResponse;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminChangeService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminDeleteService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminMenuSubMenuDisplayService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpChangeService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpDeleteService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpRegistService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPwdLockCancelService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistUnitCreateService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorChangeService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorDeleteService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorPwdLockCancelService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistUnitCreateService;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminChangeServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminDeleteServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminListServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermGrpChangeServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermGrpDeleteServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermGrpListServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermGrpRegistServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermUnitCreateServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPwdLockCancelServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminRegistServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminRegistUnitCreateServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorChangeServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorDeleteServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorListServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorPwdLockCancelServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorRegistServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AdminMC �v���O�C���N���X�B
 *                                                                
 * @@author �� �� �@@
 * @@version 1.0
 */
public final class WEB3AdminMCAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AdminMCAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3AdminmcAppPlugin()";
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

        plug(WEB3AdminMCAppPlugin.class);

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
        //WEB3AdminMCMasterDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------

        //CC�I�y���[�^�p�X���[�h���b�N����
        Services.registerService(WEB3AdminMCCCOperatorPwdLockCancelService.class, new WEB3AdminMCCCOperatorPwdLockCancelServiceImpl());
        
        //CC�I�y���[�^�ꗗ
        Services.registerService(WEB3AdminMCCCOperatorListService.class, new WEB3AdminMCCCOperatorListServiceImpl());

        //CC�I�y���[�^�폜
        Services.registerService(WEB3AdminMCCCOperatorDeleteService.class, new WEB3AdminMCCCOperatorDeleteServiceImpl());

        //CC�I�y���[�^�o�^
        Services.registerService(WEB3AdminMCCCOperatorRegistService.class, new WEB3AdminMCCCOperatorRegistServiceImpl());

        //CC�I�y���[�^�ύX
        Services.registerService(WEB3AdminMCCCOperatorChangeService.class, new WEB3AdminMCCCOperatorChangeServiceImpl());
        
        //���j���[���䋤�ʃT�[�r�X
        Services.registerService(WEB3AdminMCCCOperatorRegistUnitCreateService.class, new WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl());
        Services.registerService(WEB3AdminMCAdminPermUnitCreateService.class, new WEB3AdminMCAdminPermUnitCreateServiceImpl());
        Services.registerService(WEB3AdminMCAdminRegistUnitCreateService.class, new WEB3AdminMCAdminRegistUnitCreateServiceImpl());
        
        //�Ǘ��҃T�u���j���[�\��
        Services.registerService(WEB3AdminMCAdminMenuSubMenuDisplayService.class, new WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl());
        
        //�Ǘ��҃p�X���[�h���b�N����
        Services.registerService(WEB3AdminMCAdminPwdLockCancelService.class, new WEB3AdminMCAdminPwdLockCancelServiceImpl());

        //�Ǘ��҈ꗗ
        Services.registerService(WEB3AdminMCAdminListService.class, new WEB3AdminMCAdminListServiceImpl());

        //�Ǘ��Ҍ����O���[�v�ꗗ
        Services.registerService(WEB3AdminMCAdminPermGrpListService.class, new WEB3AdminMCAdminPermGrpListServiceImpl());

        //�Ǘ��Ҍ����O���[�v�폜
        Services.registerService(WEB3AdminMCAdminPermGrpDeleteService.class, new WEB3AdminMCAdminPermGrpDeleteServiceImpl());

        //�Ǘ��Ҍ����O���[�v�o�^
        Services.registerService(WEB3AdminMCAdminPermGrpRegistService.class, new WEB3AdminMCAdminPermGrpRegistServiceImpl());

        //�Ǘ��Ҍ����O���[�v�ύX
        Services.registerService(WEB3AdminMCAdminPermGrpChangeService.class, new WEB3AdminMCAdminPermGrpChangeServiceImpl());

        //�Ǘ��ҍ폜
        Services.registerService(WEB3AdminMCAdminDeleteService.class, new WEB3AdminMCAdminDeleteServiceImpl());

        //�Ǘ��ғo�^
        Services.registerService(WEB3AdminMCAdminRegistService.class, new WEB3AdminMCAdminRegistServiceImpl());

        //�Ǘ��ҕύX
        Services.registerService(WEB3AdminMCAdminChangeService.class, new WEB3AdminMCAdminChangeServiceImpl());



        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        //CC�I�y���[�^�p�X���[�h���b�N����
        Services.addInterceptor(WEB3AdminMCCCOperatorPwdLockCancelService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorPwdLockCancelService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorPwdLockCancelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //CC�I�y���[�^�ꗗ
        Services.addInterceptor(WEB3AdminMCCCOperatorListService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //CC�I�y���[�^�폜
        Services.addInterceptor(WEB3AdminMCCCOperatorDeleteService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorDeleteService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //CC�I�y���[�^�o�^
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //CC�I�y���[�^�ύX
        Services.addInterceptor(WEB3AdminMCCCOperatorChangeService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���j���[���䋤�ʃT�[�r�X
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistUnitCreateService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistUnitCreateService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistUnitCreateService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        Services.addInterceptor(WEB3AdminMCAdminPermUnitCreateService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermUnitCreateService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermUnitCreateService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        Services.addInterceptor(WEB3AdminMCAdminRegistUnitCreateService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminRegistUnitCreateService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminRegistUnitCreateService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��҃T�u���j���[�\��
        Services.addInterceptor(WEB3AdminMCAdminMenuSubMenuDisplayService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminMenuSubMenuDisplayService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminMenuSubMenuDisplayService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҃p�X���[�h���b�N����
        Services.addInterceptor(WEB3AdminMCAdminPwdLockCancelService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPwdLockCancelService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPwdLockCancelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҈ꗗ
        Services.addInterceptor(WEB3AdminMCAdminListService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ҍ����O���[�v�ꗗ
        Services.addInterceptor(WEB3AdminMCAdminPermGrpListService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ҍ����O���[�v�폜
        Services.addInterceptor(WEB3AdminMCAdminPermGrpDeleteService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpDeleteService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ҍ����O���[�v�o�^
        Services.addInterceptor(WEB3AdminMCAdminPermGrpRegistService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ҍ����O���[�v�ύX
        Services.addInterceptor(WEB3AdminMCAdminPermGrpChangeService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҍ폜
        Services.addInterceptor(WEB3AdminMCAdminDeleteService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminDeleteService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ғo�^
        Services.addInterceptor(WEB3AdminMCAdminRegistService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҕύX
        Services.addInterceptor(WEB3AdminMCAdminChangeService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));


        // Message �̓o�^���� ----------------------

        //�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ������m�Fظ���	
        regClass(WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ������m�Fڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ���������ظ���	
        regClass(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ���������ڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��ꗗظ���	
        regClass(WEB3AdminMCCCOperatorListRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��ꗗڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorListResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��ꗗ����ظ���	
        regClass(WEB3AdminMCCCOperatorListInputRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��ꗗ����ڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorListInputResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��폜�m�Fظ���	
        regClass(WEB3AdminMCCCOperatorDeleteConfirmRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��폜�m�Fڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorDeleteConfirmResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��폜����ظ���	
        regClass(WEB3AdminMCCCOperatorDeleteCompleteRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��폜����ڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorDeleteCompleteResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��o�^�m�Fظ���	
        regClass(WEB3AdminMCCCOperatorRegistConfirmRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��o�^�m�Fڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorRegistConfirmResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��o�^����ظ���	
        regClass(WEB3AdminMCCCOperatorRegistCompleteRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��o�^����ڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorRegistCompleteResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��o�^����ظ���	
        regClass(WEB3AdminMCCCOperatorRegistInputRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��o�^����ڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorRegistInputResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��ύX�m�Fظ���	
        regClass(WEB3AdminMCCCOperatorChangeConfirmRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��ύX�m�Fڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorChangeConfirmResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��ύX����ظ���	
        regClass(WEB3AdminMCCCOperatorChangeCompleteRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��ύX����ڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorChangeCompleteResponse.class);
        
        //�Ǘ��҃��j���[����CC���ڰ��ύX����ظ���	
        regClass(WEB3AdminMCCCOperatorChangeInputRequest.class);
        //�Ǘ��҃��j���[����CC���ڰ��ύX����ڽ��ݽ	
        regClass(WEB3AdminMCCCOperatorChangeInputResponse.class);
        
        //�Ǘ��҃��j���[����T�u���j���[�\�����N�G�X�g	
        regClass(WEB3AdminMCAdminMenuSubMenuDisplayRequest.class);
        //�Ǘ��҃��j���[����T�u���j���[�\�����X�|���X	
        regClass(WEB3AdminMCAdminMenuSubMenuDisplayResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ������m�Fظ���	
        regClass(WEB3AdminMCAdminPwdLockCancelConfirmRequest.class);
        //�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ������m�Fڽ��ݽ	
        regClass(WEB3AdminMCAdminPwdLockCancelConfirmResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ���������ظ���	
        regClass(WEB3AdminMCAdminPwdLockCancelCompleteRequest.class);
        //�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ���������ڽ��ݽ	
        regClass(WEB3AdminMCAdminPwdLockCancelCompleteResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g	
        regClass(WEB3AdminMCAdminListRequest.class);
        //�Ǘ��҃��j���[����Ǘ��҈ꗗ���X�|���X	
        regClass(WEB3AdminMCAdminListResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��N�G�X�g	
        regClass(WEB3AdminMCAdminListInputRequest.class);
        //�Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��X�|���X	
        regClass(WEB3AdminMCAdminListInputResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpListRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���X�|���X	
        regClass(WEB3AdminMCAdminPermGrpListResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�m�F���N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpDeleteConfirmRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�m�F���X�|���X	
        regClass(WEB3AdminMCAdminPermGrpDeleteConfirmResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�������N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpDeleteCompleteRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�폜�������X�|���X	
        regClass(WEB3AdminMCAdminPermGrpDeleteCompleteResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpDetailsRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��X�|���X	
        regClass(WEB3AdminMCAdminPermGrpDetailsResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�m�F���N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpRegistConfirmRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�m�F���X�|���X	
        regClass(WEB3AdminMCAdminPermGrpRegistConfirmResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�������N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpRegistCompleteRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^�������X�|���X	
        regClass(WEB3AdminMCAdminPermGrpRegistCompleteResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^���̓��N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpRegistInputRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�o�^���̓��X�|���X	
        regClass(WEB3AdminMCAdminPermGrpRegistInputResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�m�F���N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpChangeConfirmRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�m�F���X�|���X	
        regClass(WEB3AdminMCAdminPermGrpChangeConfirmResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�������N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpChangeCompleteRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX�������X�|���X	
        regClass(WEB3AdminMCAdminPermGrpChangeCompleteResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��N�G�X�g	
        regClass(WEB3AdminMCAdminPermGrpChangeInputRequest.class);
        //�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ύX���̓��X�|���X	
        regClass(WEB3AdminMCAdminPermGrpChangeInputResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��ҍ폜�m�F���N�G�X�g	
        regClass(WEB3AdminMCAdminDeleteConfirmRequest.class);
        //�Ǘ��҃��j���[����Ǘ��ҍ폜�m�F���X�|���X	
        regClass(WEB3AdminMCAdminDeleteConfirmResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��ҍ폜�������N�G�X�g	
        regClass(WEB3AdminMCAdminDeleteCompleteRequest.class);
        //�Ǘ��҃��j���[����Ǘ��ҍ폜�������X�|���X	
        regClass(WEB3AdminMCAdminDeleteCompleteResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��ғo�^�m�F���N�G�X�g	
        regClass(WEB3AdminMCAdminRegistConfirmRequest.class);
        //�Ǘ��҃��j���[����Ǘ��ғo�^�m�F���X�|���X	
        regClass(WEB3AdminMCAdminRegistConfirmResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��ғo�^�������N�G�X�g	
        regClass(WEB3AdminMCAdminRegistCompleteRequest.class);
        //�Ǘ��҃��j���[����Ǘ��ғo�^�������X�|���X	
        regClass(WEB3AdminMCAdminRegistCompleteResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��ғo�^���̓��N�G�X�g	
        regClass(WEB3AdminMCAdminRegistInputRequest.class);
        //�Ǘ��҃��j���[����Ǘ��ғo�^���̓��X�|���X	
        regClass(WEB3AdminMCAdminRegistInputResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��ҕύX�m�F���N�G�X�g	
        regClass(WEB3AdminMCAdminChangeConfirmRequest.class);
        //�Ǘ��҃��j���[����Ǘ��ҕύX�m�F���X�|���X	
        regClass(WEB3AdminMCAdminChangeConfirmResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��ҕύX�������N�G�X�g	
        regClass(WEB3AdminMCAdminChangeCompleteRequest.class);
        //�Ǘ��҃��j���[����Ǘ��ҕύX�������X�|���X	
        regClass(WEB3AdminMCAdminChangeCompleteResponse.class);
        
        //�Ǘ��҃��j���[����Ǘ��ҕύX���̓��N�G�X�g	
        regClass(WEB3AdminMCAdminChangeInputRequest.class);
        //�Ǘ��҃��j���[����Ǘ��ҕύX���̓��X�|���X	
        regClass(WEB3AdminMCAdminChangeInputResponse.class);
        


        //Handler �̓o�^���� ----------------------

        //CC�I�y���[�^�p�X���[�h���b�N�����m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest.class, WEB3AdminMCCCOperatorPwdLockCancelHandler.class, "cancelConfirm");

        //CC�I�y���[�^�p�X���[�h���b�N��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest.class, WEB3AdminMCCCOperatorPwdLockCancelHandler.class, "cancelComplete");

        //CC�I�y���[�^�ꗗ�������͉�ʕ\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorListInputRequest.class, WEB3AdminMCCCOperatorListHandler.class, "inputScreenDisplay");

        //CC�I�y���[�^�ꗗ�\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorListRequest.class, WEB3AdminMCCCOperatorListHandler.class, "CCOperatorListDisplay");

        //CC�I�y���[�^�폜�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorDeleteConfirmRequest.class, WEB3AdminMCCCOperatorDeleteHandler.class, "traderDeleteConfirm");
        //CC�I�y���[�^�폜��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorDeleteCompleteRequest.class, WEB3AdminMCCCOperatorDeleteHandler.class, "traderDeleteComplete");

        //���͉�ʕ\��
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorRegistInputRequest.class, WEB3AdminMCCCOperatorRegistHandler.class, "inputScreenDisplay");
        //CC�I�y���[�^�o�^�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorRegistConfirmRequest.class, WEB3AdminMCCCOperatorRegistHandler.class, "traderRegistConfirm");
        //CC�I�y���[�^�o�^��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorRegistCompleteRequest.class, WEB3AdminMCCCOperatorRegistHandler.class, "traderRegistComplete");

        //���͉�ʕ\��
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorChangeInputRequest.class, WEB3AdminMCCCOperatorChangeHandler.class, "inputScreenDisplay");
        //CC�I�y���[�^�ύX�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorChangeConfirmRequest.class, WEB3AdminMCCCOperatorChangeHandler.class, "traderChangeConfirm");
        //CC�I�y���[�^�ύX����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorChangeCompleteRequest.class, WEB3AdminMCCCOperatorChangeHandler.class, "traderChangeComplete");

        //�Ǘ��҃p�X���[�h���b�N�����m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPwdLockCancelConfirmRequest.class, WEB3AdminMCAdminPwdLockCancelHandler.class, "cancelConfirm");
        //�Ǘ��҃p�X���[�h���b�N������������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPwdLockCancelCompleteRequest.class, WEB3AdminMCAdminPwdLockCancelHandler.class, "cancelComplete");

        //�Ǘ��҈ꗗ�������͉�ʕ\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminListInputRequest.class, WEB3AdminMCAdminListHandler.class, "inputScreenDisplay");
        //�Ǘ��҈ꗗ�\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminListRequest.class, WEB3AdminMCAdminListHandler.class, "adminListDisplay");

        //�����O���[�v�ꗗ�\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpListRequest.class, WEB3AdminMCAdminPermGrpListHandler.class, "permGrpListDisplay");
        //�����O���[�v�ڍו\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpDetailsRequest.class, WEB3AdminMCAdminPermGrpListHandler.class, "permGrpDetailDisplay");

        //�����O���[�v�폜�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpDeleteConfirmRequest.class, WEB3AdminMCAdminPermGrpDeleteHandler.class, "permGrpDeleteConfirm");
        //�����O���[�v�폜��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpDeleteCompleteRequest.class, WEB3AdminMCAdminPermGrpDeleteHandler.class, "permGrpDeleteComplete");

        //�Ǘ��Ҍ����O���[�v���͉�ʕ\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpRegistInputRequest.class, WEB3AdminMCAdminPermGrpRegistHandler.class, "inputScreenDisplay");
        //�����O���[�v�o�^�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpRegistConfirmRequest.class, WEB3AdminMCAdminPermGrpRegistHandler.class, "permGrpRegistConfirm");
        //�����O���[�v�o�^��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpRegistCompleteRequest.class, WEB3AdminMCAdminPermGrpRegistHandler.class, "permGrpRegistComplete");

        //�Ǘ��Ҍ����O���[�v�ύX���͉�ʕ\��
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpChangeInputRequest.class, WEB3AdminMCAdminPermGrpChangeHandler.class, "inputScreenDisplay");
        //�����O���[�v�ύX�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpChangeConfirmRequest.class, WEB3AdminMCAdminPermGrpChangeHandler.class, "permGrpChangeConfirm");
        //�����O���[�v�ύX��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpChangeCompleteRequest.class, WEB3AdminMCAdminPermGrpChangeHandler.class, "permGrpChangeComplete");

        //�Ǘ��ҍ폜�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminDeleteConfirmRequest.class, WEB3AdminMCAdminDeleteHandler.class, "adminDeleteConfirm");
        //�Ǘ��ҍ폜��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminDeleteCompleteRequest.class, WEB3AdminMCAdminDeleteHandler.class, "adminDeleteComplete");

        //�Ǘ��ғo�^���͉�ʕ\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminRegistInputRequest.class, WEB3AdminMCAdminRegistHandler.class, "inputScreenDisplay");
        //�Ǘ��ғo�^�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminRegistConfirmRequest.class, WEB3AdminMCAdminRegistHandler.class, "adminRegistConfirm");
        //�Ǘ��ғo�^��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminRegistCompleteRequest.class, WEB3AdminMCAdminRegistHandler.class, "adminRegistComplete");

        //�Ǘ��ҕύX���͉�ʕ\������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminChangeInputRequest.class, WEB3AdminMCAdminChangeHandler.class, "inputScreenDisplay");
        //�Ǘ��ҕύX�m�F����
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminChangeConfirmRequest.class, WEB3AdminMCAdminChangeHandler.class, "adminChangeConfirm");
        //�Ǘ��ҕύX��������
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminChangeCompleteRequest.class, WEB3AdminMCAdminChangeHandler.class, "adminChangeComplete");

        //�Ǘ��҃T�u���j���[�\��
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminMenuSubMenuDisplayRequest.class, WEB3AdminMCAdminMenuSubMenuDisplayHandler.class, "subMenuDisplay");


        log.exiting(STR_METHOD_NAME);
    }
}@
