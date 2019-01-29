head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsGatewayPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RlsGatewayPlugin�v���O�C���̃v���O�C���N���X(WEB3RlsGatewayPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ���@@�V�K�쐬
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import webbroker3.rlsgateway.connector.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.rlsgateway.service.*;
import webbroker3.rlsgateway.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3RlsGatewayPlugin�v���O�C���̃v���O�C���N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3RlsGatewayPlugin
    extends Plugin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsGatewayPlugin.class);

    private static boolean isPlugged = false;

    public static final String MESSAGE_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.rlsgateway.messagecontext";

    /**
     * �����t�������̂̔������[�h�̐ݒ�L�[
     */
    public final static String STR_RLS_SERVER_ORDER_SUBMIT_MODE_KEY =
        "rls.server.order.submit.mode";

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3RlsGatewayPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3RlsGatewayPlugin.class);
    }

    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        // WEB3RlsRequestSenderService�T�[�r�X��o�^
        Services.registerService(
            WEB3RlsRequestSenderService.class,
            new WEB3RlsRequestSenderServiceImpl());

        // WEB3RlsRealTxSenderService�T�[�r�X��o�^
        Services.registerService(
            WEB3RlsRealTxSenderService.class,
            new WEB3RlsRealTxSenderServiceImpl());

        // �����g�����U�N�V�����ݒ�
        Services.addInterceptor(
            WEB3RlsRealTxSenderService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        WEB3RlsGatewaySessionDatabaseExtensions.plug();

        //���[���G���W���ڑ��p�T�[�r�X
        WEB3RlsConnectorPlugin.plug();

        isPlugged = true;

        log.info("WEB3RlsGatewayPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }
}
@
