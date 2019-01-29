head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.16.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-AdminMailInfo �v���O�C��(WEB3AdminMailInfoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �����F (���u) �V�K�쐬
*/

package webbroker3.mailinfo;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoChangeHandler;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoDeleteHandler;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoDetailsHandler;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoReferenceHandler;
import webbroker3.mailinfo.handler.WEB3AdminMailInfoRegistHandler;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeInputRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeInputResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoCommonRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoCommonResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCommonRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoChangeService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDeleteService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDetailsService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoReferenceService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoRegistService;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoChangeServiceImpl;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoDeleteServiceImpl;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoDetailsServiceImpl;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoReferenceServiceImpl;
import webbroker3.mailinfo.service.delegate.stdimpls.WEB3AdminMailInfoRegistServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AdminMailInfo �v���O�C���N���X�B
 *                                                                
 * @@author �����F
 * @@version 1.0
 */
public final class WEB3AdminMailInfoAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AdminMailInfoAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3AdminMailInfoAppPlugin()";
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

        plug(WEB3AdminMailInfoAppPlugin.class);

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
        

        // Service �̓o�^���� ----------------------
        
        //���[�����ύX�T�[�r�X
        Services.registerService(WEB3AdminMailInfoChangeService.class, new WEB3AdminMailInfoChangeServiceImpl());

        //���[�����폜�T�[�r�X
        Services.registerService(WEB3AdminMailInfoDeleteService.class, new WEB3AdminMailInfoDeleteServiceImpl());
        
        //���[�����ڍ׃T�[�r�X
        Services.registerService(WEB3AdminMailInfoDetailsService.class, new WEB3AdminMailInfoDetailsServiceImpl());

        //���[�����ꗗ�T�[�r�X
        Services.registerService(WEB3AdminMailInfoReferenceService.class, new WEB3AdminMailInfoReferenceServiceImpl());

        //���[�����o�^�T�[�r�X
        Services.registerService(WEB3AdminMailInfoRegistService.class, new WEB3AdminMailInfoRegistServiceImpl());


        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        //���[�����ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminMailInfoChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���[�����폜�T�[�r�X
        Services.addInterceptor(WEB3AdminMailInfoDeleteService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���[�����ڍ׃T�[�r�X
        Services.addInterceptor(WEB3AdminMailInfoDetailsService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoDetailsService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���[�����ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminMailInfoReferenceService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoReferenceService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���[�����o�^�T�[�r�X        
        Services.addInterceptor(WEB3AdminMailInfoRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMailInfoRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));


        // Message �̓o�^���� ----------------------

        //���[����񋤒ʃ��N�G�X�g
        regClass(WEB3AdminMailInfoCommonRequest.class);
        //���[����񋤒ʃ��X�|���X
        regClass(WEB3AdminMailInfoCommonResponse.class);

        //���[�����폜���ʃ��N�G�X�g
        regClass(WEB3AdminMailInfoDeleteCommonRequest.class);

        //���[�����ꗗ���N�G�X�g
        regClass(WEB3AdminMailInfoReferenceRequest.class);
        //���[�����ꗗ���X�|���X
        regClass(WEB3AdminMailInfoReferenceResponse.class);

        //���[�����폜�m�F���N�G�X�g
        regClass(WEB3AdminMailInfoDeleteConfirmRequest.class);
        //���[�����폜�m�F���X�|���X
        regClass(WEB3AdminMailInfoDeleteConfirmResponse.class);

        //���[�����폜�������N�G�X�g
        regClass(WEB3AdminMailInfoDeleteCompleteRequest.class);
        //���[�����폜�������X�|���X
        regClass(WEB3AdminMailInfoDeleteCompleteResponse.class);

        //���[�����ڍ׃��N�G�X�g
        regClass(WEB3AdminMailInfoDetailsRequest.class);
        //���[�����ڍ׃��X�|���X
        regClass(WEB3AdminMailInfoDetailsResponse.class);

        //���[�����o�^�m�F���N�G�X�g
        regClass(WEB3AdminMailInfoRegistConfirmRequest.class);
        //���[�����o�^�m�F���X�|���X
        regClass(WEB3AdminMailInfoRegistConfirmResponse.class);

        //���[�����o�^�������N�G�X�g
        regClass(WEB3AdminMailInfoRegistCompleteRequest.class);
        //���[�����o�^�������X�|���X
        regClass(WEB3AdminMailInfoRegistCompleteResponse.class);

        //���[�����ύX���͉�ʃ��N�G�X�g
        regClass(WEB3AdminMailInfoChangeInputRequest.class);
        //���[�����ύX���͉�ʃ��X�|���X
        regClass(WEB3AdminMailInfoChangeInputResponse.class);

        //���[�����ύX�m�F���N�G�X�g
        regClass(WEB3AdminMailInfoChangeConfirmRequest.class);
        //���[�����ύX�m�F���X�|���X
        regClass(WEB3AdminMailInfoChangeConfirmResponse.class);

        //���[�����ύX�������N�G�X�g
        regClass(WEB3AdminMailInfoChangeCompleteRequest.class);
        //���[�����ύX�������X�|���X
        regClass(WEB3AdminMailInfoChangeCompleteResponse.class);


        //Handler �̓o�^���� ----------------------

        //���[�����ύX �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoChangeInputRequest.class, WEB3AdminMailInfoChangeHandler.class, "mailInfoChangeInputScreenRequest");

        //���[�����ύX �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoChangeConfirmRequest.class, WEB3AdminMailInfoChangeHandler.class, "confirmMailInfoChange");

        //���[�����ύX �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoChangeCompleteRequest.class, WEB3AdminMailInfoChangeHandler.class, "completeMailInfoChange");

        //���[�����폜 �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoDeleteConfirmRequest.class, WEB3AdminMailInfoDeleteHandler.class, "confirmMailInfoDelete");

        //���[�����폜 �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoDeleteCompleteRequest.class, WEB3AdminMailInfoDeleteHandler.class, "completeMailInfoDelete");

        //���[�����ڍ� �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoDetailsRequest.class, WEB3AdminMailInfoDetailsHandler.class, "mailInfoDetailsRequest");

        //���[�����ꗗ �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoReferenceRequest.class, WEB3AdminMailInfoReferenceHandler.class, "mailInfoReferenceRequest");

        //���[�����o�^ �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoRegistConfirmRequest.class, WEB3AdminMailInfoRegistHandler.class, "confirmMailInfoRegist");

        //���[�����o�^ �p�n���h���[�̓o�^
        regHandler(WEB3AdminMailInfoRegistCompleteRequest.class, WEB3AdminMailInfoRegistHandler.class, "completeMailInfoRegist");

        log.exiting(STR_METHOD_NAME);
    }
}@
