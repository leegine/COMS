head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IfoAdminAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Eqtypeadmin AppPlugin(WEB3EqtypeadminAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/17  �Ӑ� (���u)�V�K�쐬
Revision History : 2009/03/03  �����F (���u) �؋����s���󋵏Ɖ�
*/
package webbroker3.ifoadmin;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.ifoadmin.handler.WEB3AdminIfoDepShortageDownloadHandler;
import webbroker3.ifoadmin.handler.WEB3AdminIfoDepShortageReferenceHandler;
import webbroker3.ifoadmin.handler.WEB3AdminIfoManualExpireHandler;
import webbroker3.ifoadmin.handler.WEB3AdminIfoManualExpireMainHandler;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageDownloadService;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageReferenceService;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireMainService;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireService;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoDepShortageDownloadServiceImpl;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoDepShortageReferenceServiceImpl;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireMainServiceImpl;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Ifoadmin<BR>
 * WEB3IfoAdminAppPlugin
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3IfoAdminAppPlugin extends Plugin
{

    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoAdminAppPlugin.class);

    public WEB3IfoAdminAppPlugin()
    {
    }

    /**
     * plug method
     * @@throws Exception exception
     */
    public static void plug() throws Exception
    {
        final String STR_METHOD_NAME = "plug()";
        log.entering(STR_METHOD_NAME);
        plug(WEB3IfoAdminAppPlugin.class);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * onPlug method
     * @@throws Exception exception
     */
    public static void onPlug() throws Exception
    {
        final String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();

        // Service �̓o�^����
        //�Ǘ��ҁE�敨OP�蓮�������C���T�[�r�X
        Services.registerService(WEB3AdminIfoManualExpireMainService.class,
            new WEB3AdminIfoManualExpireMainServiceImpl());
        //�Ǘ��ҁE�敨OP�蓮�����T�[�r�X
        Services.registerService(WEB3AdminIfoManualExpireService.class,
            new WEB3AdminIfoManualExpireServiceImpl());

        //�Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�X
        Services.registerService(WEB3AdminIfoDepShortageReferenceService.class,
            new WEB3AdminIfoDepShortageReferenceServiceImpl());

        //�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X
        Services.registerService(WEB3AdminIfoDepShortageDownloadService.class,
            new WEB3AdminIfoDepShortageDownloadServiceImpl());

        // �T�[�r�X�C���^�Z�v�^�̐ݒ�
        //�Ǘ��ҁE�敨OP�蓮�����T�[�r�X
        Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
            new WEB3AdminIfoManualExpireServiceInterceptor());

        //�Ǘ��ҁE�敨OP�蓮�������C���T�[�r�X
        Services.addInterceptor(WEB3AdminIfoManualExpireMainService.class,
            new WEB3LogSysTimeInterceptor());
        //�Ǘ��ҁE�敨OP�蓮�����T�[�r�X
        Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminIfoDepShortageReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X
        Services.addInterceptor(WEB3AdminIfoDepShortageDownloadService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�敨OP�蓮�������C���T�[�r�X
        Services.addInterceptor(WEB3AdminIfoManualExpireMainService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        //�Ǘ��ҁE�敨OP�蓮�����T�[�r�X
        Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminIfoDepShortageReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X
        Services.addInterceptor(WEB3AdminIfoDepShortageDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //Message �̓o�^
        //�Ǘ��ҁE�敨OP�蓮�������̓��N�G�X�g*���X�|���X
        regClass(WEB3AdminIfoManualLapseInputRequest.class);
        regClass(WEB3AdminIfoManualLapseInputResponse.class);

        //�Ǘ��ҁE�敨OP�蓮�����m�F���N�G�X�g*���X�|���X
        regClass(WEB3AdminIfoManualLapseConfirmRequest.class);
        regClass(WEB3AdminIfoManualLapseConfirmResponse.class);

        //�Ǘ��ҁE�敨OP�蓮���������N�����N�G�X�g*���X�|���X
        regClass(WEB3AdminIfoManualLapseRunRequest.class);
        regClass(WEB3AdminIfoManualLapseRunResponse.class);

        //�Ǘ��ҁE�敨OP�蓮���������X�e�[�^�X�m�F���N�G�X�g*���X�|���X
        regClass(WEB3AdminIfoManualLapseStatusRequest.class);
        regClass(WEB3AdminIfoManualLapseStatusResponse.class);

        //�Ǘ��ҁE�敨OP�蓮�������C�����N�G�X�g*���X�|���X
        regClass(WEB3AdminIfoManualLapseMainRequest.class);
        regClass(WEB3AdminIfoManualLapseMainResponse.class);

        //�Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g*���X�|���X
        regClass(WEB3AdminIfoDepShortageReferenceRequest.class);
        regClass(WEB3AdminIfoDepShortageReferenceResponse.class);

        //�Ǘ��ҁE�؋����s���󋵏Ɖ���̓��N�G�X�g*���X�|���X
        regClass(WEB3AdminIfoDepShortageRefInputRequest.class);
        regClass(WEB3AdminIfoDepShortageRefInputResponse.class);

       //�Ǘ��ҁE�؋����s���󋵃_�E�����[�h���N�G�X�g*���X�|���X
        regClass(WEB3AdminIfoDepShortageDownloadRequest.class);
        regClass(WEB3AdminIfoDepShortageDownloadResponse.class);

        //�n���h���[�̓o�^
        //�Ǘ��ҁE�敨OP�蓮�����n���h��
        regHandler(
            WEB3IfoAdminAppPlugin.class,
            WEB3AdminIfoManualLapseInputRequest.class,
            WEB3AdminIfoManualExpireHandler.class,
            "getInputScreen");

        regHandler(
            WEB3IfoAdminAppPlugin.class,
            WEB3AdminIfoManualLapseConfirmRequest.class,
            WEB3AdminIfoManualExpireHandler.class,
            "confirmManualExpire");

        regHandler(
            WEB3IfoAdminAppPlugin.class,
            WEB3AdminIfoManualLapseRunRequest.class,
            WEB3AdminIfoManualExpireHandler.class,
            "runManualExpire");

        regHandler(
            WEB3IfoAdminAppPlugin.class,
            WEB3AdminIfoManualLapseStatusRequest.class,
            WEB3AdminIfoManualExpireHandler.class,
            "confirmStatus");

        //�Ǘ��ҁE�敨OP�蓮�������C���n���h��
        regHandler(
            WEB3IfoAdminAppPlugin.class,
            WEB3AdminIfoManualLapseMainRequest.class,
            WEB3AdminIfoManualExpireMainHandler.class,
            "manualExpireRequest");

        //�Ǘ��ҁE�؋����s���󋵏Ɖ�n���h��
        regHandler(
            WEB3IfoAdminAppPlugin.class,
            WEB3AdminIfoDepShortageRefInputRequest.class,
            WEB3AdminIfoDepShortageReferenceHandler.class,
            "getInputScreen");

        regHandler(
            WEB3IfoAdminAppPlugin.class,
            WEB3AdminIfoDepShortageReferenceRequest.class,
            WEB3AdminIfoDepShortageReferenceHandler.class,
            "getReferenceScreen");

        //�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�n���h��
        regHandler(
            WEB3IfoAdminAppPlugin.class,
            WEB3AdminIfoDepShortageDownloadRequest.class,
            WEB3AdminIfoDepShortageDownloadHandler.class,
            "getDownloadFile");

        log.exiting(METHOD_NAME);
    }

}
@
