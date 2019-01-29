head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Faq �v���O�C���N���X(WEB3FaqAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ (���u) �V�K�쐬
*/

package webbroker3.faq;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.faq.data.WEB3FaqMasterDatabaseExtensions;
import webbroker3.faq.data.WEB3FaqSessionDatabaseExtensions;
import webbroker3.faq.handler.WEB3AdminFaqDetailsHandler;
import webbroker3.faq.handler.WEB3AdminFaqListHandler;
import webbroker3.faq.handler.WEB3FaqInputHandler;
import webbroker3.faq.message.WEB3AdminFaqDetailsRequest;
import webbroker3.faq.message.WEB3AdminFaqDetailsResponse;
import webbroker3.faq.message.WEB3AdminFaqListInputRequest;
import webbroker3.faq.message.WEB3AdminFaqListInputResponse;
import webbroker3.faq.message.WEB3AdminFaqListRequest;
import webbroker3.faq.message.WEB3AdminFaqListResponse;
import webbroker3.faq.message.WEB3FaqCompleteRequest;
import webbroker3.faq.message.WEB3FaqCompleteResponse;
import webbroker3.faq.message.WEB3FaqConfirmRequest;
import webbroker3.faq.message.WEB3FaqConfirmResponse;
import webbroker3.faq.message.WEB3FaqInputRequest;
import webbroker3.faq.message.WEB3FaqInputResponse;
import webbroker3.faq.service.delegate.WEB3AdminFaqDetailsService;
import webbroker3.faq.service.delegate.WEB3AdminFaqListService;
import webbroker3.faq.service.delegate.WEB3FaqInputService;
import webbroker3.faq.service.delegate.WEB3FaqNumberService;
import webbroker3.faq.service.delegate.stdimpls.WEB3AdminFaqDetailsServiceImpl;
import webbroker3.faq.service.delegate.stdimpls.WEB3AdminFaqListServiceImpl;
import webbroker3.faq.service.delegate.stdimpls.WEB3FaqInputServiceImpl;
import webbroker3.faq.service.delegate.stdimpls.WEB3FaqNumberServiceImpl;
import webbroker3.util.WEB3LogUtility;


/**
 * Webbroker3-Faq �v���O�C���N���X�B
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public final class WEB3FaqAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FaqAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3FaqAppPlugin()
    {

    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3FaqAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);
        
        WEB3FaqMasterDatabaseExtensions.plug();
        // ���u�� Codegen�̑Ή� start
        WEB3FaqSessionDatabaseExtensions.plug();
        // ���u�� Codegen�̑Ή� end

        KernelPlugin.plug();

        // ---------------------- 1 Service �̓o�^���� ----------------------
        //���⍇�����̓T�[�r�X
        Services.registerService(
            WEB3FaqInputService.class,
            new WEB3FaqInputServiceImpl());

        //���⍇���ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminFaqListService.class,
            new WEB3AdminFaqListServiceImpl());

        //�킹�Ǘ����⍇���ڍ׃T�[�r�X
        Services.registerService(
            WEB3AdminFaqDetailsService.class,
            new WEB3AdminFaqDetailsServiceImpl());

        //�⍇���Ǘ��⍇���R�[�h�̔ԃT�[�r�X
        Services.registerService(
            WEB3FaqNumberService.class,
            new WEB3FaqNumberServiceImpl());

        // ---------------------- 2 Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        //���⍇�����̓T�[�r�X
        Services.addInterceptor(
            WEB3FaqInputService.class,
            new WEB3LogSysTimeInterceptor());

        //���⍇���ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFaqListService.class,
            new WEB3LogSysTimeInterceptor());

        //�킹�Ǘ����⍇���ڍ׃T�[�r�X
        Services.addInterceptor(
            WEB3AdminFaqDetailsService.class,
            new WEB3LogSysTimeInterceptor());

        // ---------------------- 3 Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        //���⍇�����̓T�[�r�X
        Services.addInterceptor(
            WEB3FaqInputService.class,
            new WEB3FaqInputServiceInterceptor());

        //���⍇���ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFaqListService.class,
            new WEB3FaqServiceInterceptor());
        
        //�킹�Ǘ����⍇���ڍ׃T�[�r�X
        Services.addInterceptor(
            WEB3AdminFaqDetailsService.class,
            new WEB3FaqServiceInterceptor());

        // ---------------------- 4 Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        //���⍇�����̓T�[�r�X
        Services.addInterceptor(
            WEB3FaqInputService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //���⍇���ꗗ�T�[�r�X
        Services.addInterceptor(
        WEB3AdminFaqListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�킹�Ǘ����⍇���ڍ׃T�[�r�X
        Services.addInterceptor(
        WEB3AdminFaqDetailsService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�⍇���Ǘ��⍇���R�[�h�̔ԃT�[�r�X
        Services.addInterceptor(
        WEB3FaqNumberService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // ---------------------- 5 Message �̓o�^���� ----------------------
        //�⍇���Ǘ����⍇�����̓��N�G�X�g
        regClass(WEB3FaqInputRequest.class);
        //�⍇���Ǘ����⍇�����̓��X�|���X
        regClass(WEB3FaqInputResponse.class);

        //�⍇���Ǘ����⍇���m�F���N�G�X�g
        regClass(WEB3FaqConfirmRequest.class);
        //�⍇���Ǘ����⍇���m�F���X�|���X
        regClass(WEB3FaqConfirmResponse.class);

        //�⍇���Ǘ����⍇���������N�G�X�g
        regClass(WEB3FaqCompleteRequest.class);
        //�⍇���Ǘ����⍇���������X�|���X
        regClass(WEB3FaqCompleteResponse.class);

        //�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���̓��N�G�X�g
        regClass(WEB3AdminFaqListInputRequest.class);
        //�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���̓��X�|���X
        regClass(WEB3AdminFaqListInputResponse.class);

        //�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g
        regClass(WEB3AdminFaqListRequest.class);
        //�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���X�|���X
        regClass(WEB3AdminFaqListResponse.class);

        //�Ǘ��Җ⍇���Ǘ����⍇���ڍ׃��N�G�X�g
        regClass(WEB3AdminFaqDetailsRequest.class);
        //�Ǘ��Җ⍇���Ǘ����⍇���ڍ׃��X�|���X
        regClass(WEB3AdminFaqDetailsResponse.class);

        // ---------------------- 6 Handler �̓o�^���� ----------------------
        //�⍇���Ǘ����⍇������
        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3FaqInputRequest.class,
            WEB3FaqInputHandler.class,
            "inputScreenDisplay");

        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3FaqConfirmRequest.class,
            WEB3FaqInputHandler.class,
            "faqInputConfirm");

        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3FaqCompleteRequest.class,
            WEB3FaqInputHandler.class,
            "faqInputComplete");

        //�Ǘ��Җ⍇���Ǘ����⍇���ꗗ����
        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3AdminFaqListInputRequest.class,
            WEB3AdminFaqListHandler.class,
            "inputScreenDisplay");

        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3AdminFaqListRequest.class,
            WEB3AdminFaqListHandler.class,
            "faqListDisplay");

        //�Ǘ��Җ⍇���Ǘ����⍇���ڍ�
        regHandler(
            WEB3FaqAppPlugin.class,
            WEB3AdminFaqDetailsRequest.class,
            WEB3AdminFaqDetailsHandler.class,
            "faqDetailsDisplay");

        log.exiting(METHOD_NAME);
    }
}
@
