head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.30.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���O�C���T�[�r�X�v���O�C��(WEB3LoginAppPlugin.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 �e�n(SRA) �V�K�쐬
Revesion History    : 2006/06/23 ������(���u)�w�n�b�V���F�؃��O�C���x���v���O�C��
Revesion History    : 2007/07/27 �����q(���u)�@@�d�l�ύX�E���f��No.039
 */

package webbroker3.login;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginPlugin;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin;
import com.fitechlabs.xtrade.plugin.util.queries.QueryRequestPlugin;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.login.message.*;
import webbroker3.login.handler.*;
import webbroker3.login.service.delegate.*;
import webbroker3.login.service.delegate.stdimpls.*;
import webbroker3.util.WEB3LogUtility;

/**
 * ���O�C���T�[�r�X�v���O�C���B<BR>
 * @@author      �e�n(SRA)
 * @@version     0.01
 */
public final class WEB3LoginAppPlugin extends Plugin
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3LoginAppPlugin.class);

    private WEB3LoginAppPlugin()
    {
    }

    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3LoginAppPlugin.class);
        log.exiting(METHOD_NAME);
    }

    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // �����ł́AxTrade �J�[�l�����u�[�g����B
        KernelPlugin.plug();
        log.info("KernelPlugin bootstrap succeeded !!!");
        QueryRequestPlugin.plug();
        log.info("QueryRequestPlugin bootstrap succeeded !!!");
        OpLoginPlugin.plug();
        log.info("OpLoginPlugin bootstrap succeeded !!!");

        // install the eqtype plugins
        GenericTradingPlugin.plug();
        log.info("GenericTradingPlugin bootstrap succeeded !!!");

        // Service �̓o�^���� ----------------------
        Services.registerService(
            WEB3AcceptLoginService.class,
            new WEB3AcceptLoginServiceImpl());
        Services.registerService(
            WEB3AcceptSlingshotService.class,
            new WEB3AcceptSlingshotServiceImpl());
        Services.registerService(
            WEB3AcceptAutoLoginService.class,
            new WEB3AcceptAutoLoginServiceImpl());
        Services.registerService(
            WEB3CCOperatorLoginService.class,
            new WEB3CCOperatorLoginServiceImpl());
        Services.registerService(
            WEB3SetAccountService.class,
            new WEB3SetAccountServiceImpl());
        Services.registerService(
            WEB3OpeAcceptSlingshotService.class,
            new WEB3OpeAcceptSlingshotServiceImpl());
        Services.registerService(
            WEB3OpeAcceptAutoLoginService.class,
            new WEB3OpeAcceptAutoLoginServiceImpl());
        Services.registerService(
            WEB3AdministratorLoginService.class,
            new WEB3AdministratorLoginServiceImpl());
        Services.registerService(
            WEB3LoginPwdChangeService.class,
            new WEB3LoginPwdChangeServiceImpl());
        Services.registerService(
            WEB3LogoutService.class,
            new WEB3LogoutServiceImpl());
        Services.registerService(
            WEB3AcceptPasswordCheckService.class,
            new WEB3AcceptPasswordCheckServiceImpl());
        Services.registerService(
            WEB3CCOperatorAccountListService.class,
            new WEB3CCOperatorAccountListServiceImpl());

        Services.registerService(
            WEB3DigestService.class,
            new WEB3DigestServiceImpl());
        // �n�b�V���F�؃��O�C��
        Services.registerService(
            WEB3HashAuthenticationAcceptLoginService.class,
            new WEB3HashAuthenticationAcceptLoginServiceImpl());

        // �T�[�r�X�Ăяo���O���
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        Services.addInterceptor(
            WEB3AcceptLoginService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AcceptSlingshotService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AcceptAutoLoginService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3CCOperatorLoginService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3SetAccountService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3OpeAcceptSlingshotService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3OpeAcceptAutoLoginService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AdministratorLoginService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3LoginPwdChangeService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3LogoutService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3AcceptPasswordCheckService.class,
            new WEB3LogSysTimeInterceptor());
        // �n�b�V���F�؃��O�C��
        Services.addInterceptor(
            WEB3HashAuthenticationAcceptLoginService.class,
            new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(
            WEB3CCOperatorAccountListService.class,
            new WEB3LogSysTimeInterceptor());
        
        // Message �̓o�^���� ----------------------
        // �ڋq���O�C�����N�G�X�g�E���X�|���X��o�^
        regClass(WEB3AcceptLoginRequest.class);
        regClass(WEB3AcceptLoginResponse.class);
        // �ڋqSS�J�ڃ��N�G�X�g�E���X�|���X��o�^
        regClass(WEB3AcceptSlingshotRequest.class);
        regClass(WEB3AcceptSlingshotResponse.class);
        // �ڋq�������O�C�����N�G�X�g�E���X�|���X��o�^
        regClass(WEB3AcceptAutoLoginRequest.class);
        regClass(WEB3AcceptAutoLoginResponse.class);
        // CC�I�y���[�^���O�C�����N�G�X�g�E���X�|���X��o�^
        regClass(WEB3CCOperatorLoginRequest.class);
        regClass(WEB3CCOperatorLoginResponse.class);
        // �ڋq���肷�܂����N�G�X�g�E���X�|���X��o�^
        regClass(WEB3SetAccountRequest.class);
        regClass(WEB3SetAccountResponse.class);
        // �ڋq���肷�܂�SS�J�ڃ��N�G�X�g�E���X�|���X��o�^
        regClass(WEB3OpeAcceptSlingshotRequest.class);
        regClass(WEB3OpeAcceptSlingshotResponse.class);
        // �ڋq���肷�܂��������O�C�����N�G�X�g�E���X�|���X��o�^
        regClass(WEB3OpeAcceptAutoLoginRequest.class);
        regClass(WEB3OpeAcceptAutoLoginResponse.class);
        // �Ǘ��҃��O�C�����N�G�X�g�E���X�|���X��o�^
        regClass(WEB3AdministratorLoginRequest.class);
        regClass(WEB3AdministratorLoginResponse.class);
        // ���O�C�����p�X���[�h�ύX���N�G�X�g�E���X�|���X��o�^
        regClass(WEB3LoginPwdChangeRequest.class);
        regClass(WEB3LoginPwdChangeResponse.class);
        // �Z�b�V���������ݒ胊�N�G�X�g�E���X�|���X��o�^
        regClass(WEB3SetSessionRequest.class);
        regClass(WEB3SetSessionResponse.class);
        // �Z�b�V�����A�N�Z�X�����X�V���N�G�X�g�E���X�|���X��o�^
        regClass(WEB3UpdateAccessTimeRequest.class);
        regClass(WEB3UpdateAccessTimeResponse.class);
        // ���O�A�E�g���N�G�X�g�E���X�|���X��o�^
        regClass(WEB3LogoutRequest.class);
        regClass(WEB3LogoutResponse.class);
        // �ڋq�p�X���[�h�`�F�b�N���N�G�X�g�E���X�|���X��o�^
        regClass(WEB3AcceptPasswordCheckRequest.class);
        regClass(WEB3AcceptPasswordCheckResponse.class);
        // �n�b�V���F�؃��O�C��
        regClass(WEB3HashAuthenticationAcceptLoginRequest.class);
        regClass(WEB3HashAuthenticationAcceptLoginResponse.class);
        // CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g�E���X�|���X��o�^
        regClass(WEB3CCOperatorAccountListRequest.class);
        regClass(WEB3CCOperatorAccountListResponse.class);

        // �n���h���[�̓o�^
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3AcceptLoginRequest.class,
            WEB3AcceptLoginHandler.class,
            "acceptLoginRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3AcceptSlingshotRequest.class,
            WEB3AcceptLoginHandler.class,
            "acceptSlingshotRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3AcceptAutoLoginRequest.class,
            WEB3AcceptLoginHandler.class,
            "acceptAutoLoginRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3CCOperatorLoginRequest.class,
            WEB3CCOperatorLoginHandler.class,
            "ccOperatorLoginRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3SetAccountRequest.class,
            WEB3SetAccountHandler.class,
            "setAccountRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3OpeAcceptSlingshotRequest.class,
            WEB3SetAccountHandler.class,
            "opeAcceptSlingshotRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3OpeAcceptAutoLoginRequest.class,
            WEB3SetAccountHandler.class,
            "opeAcceptAutoLoginRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3AdministratorLoginRequest.class,
            WEB3AdministratorLoginHandler.class,
            "administratorLoginRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3LoginPwdChangeRequest.class,
            WEB3LoginPwdChangeHandler.class,
            "loginPwdChangeRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3SetSessionRequest.class,
            WEB3SetSessionHandler.class,
            "setSessionRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3UpdateAccessTimeRequest.class,
            WEB3UpdateAccessTimeHandler.class,
            "updateAccessTime");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3LogoutRequest.class,
            WEB3LogoutHandler.class,
            "logoutRequest");
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3AcceptPasswordCheckRequest.class,
            WEB3AcceptPasswordCheckHandler.class,
            "acceptPasswordCheckRequest");
        // �n�b�V���F�؃��O�C��
        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3HashAuthenticationAcceptLoginRequest.class,
            WEB3HashAuthenticationAcceptLoginHandler.class,
            "hashAuthenticationAcceptLoginRequest");

        // �g���}�l�[�W���̐؂�ւ����� --------------------
        // �g���A�J�E���g�E�}�l�[�W���[
        //        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //		l_finApp.overrideAccountManager(new WEB3GentradeAccountManager());

        regHandler(
            WEB3LoginAppPlugin.class,
            WEB3CCOperatorAccountListRequest.class,
            WEB3CCOperatorAccountListHandler.class,
            "ccOperatorAccountListRequest");

        log.exiting(METHOD_NAME);
    }
}
@
