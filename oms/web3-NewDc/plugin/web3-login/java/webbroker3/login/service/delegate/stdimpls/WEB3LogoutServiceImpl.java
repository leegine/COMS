head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LogoutServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���O�A�E�g�T�[�r�X�̃x�[�X�N���X(WEB3LogoutServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 �e�n(SRA) �V�K�쐬
 */
package webbroker3.login.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.login.message.WEB3LogoutRequest;
import webbroker3.login.service.delegate.WEB3LogoutService;
import webbroker3.util.WEB3LogUtility;

/**
 * (WEB3���O�A�E�g�T�[�r�X)<BR>
 * ���O�A�E�g�T�[�r�X�̃x�[�X�N���X<BR>
 *<BR>
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3LogoutServiceImpl
    implements WEB3BusinessService, WEB3LogoutService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3LogoutServiceImpl.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * @@roseuid 403EEFDA0035
     */
    public WEB3LogoutServiceImpl()
    {

    }

    /**
     * OpLoginSecurityService���擾����B<BR>
     * �@@Services.getService()<BR>
     * <BR>
     * ���O�A�E�g����B<BR>
     * �@@OpLoginSecurityService.logOut()<BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 403DAB8B028F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3LogoutRequest l_loginReq = (WEB3LogoutRequest) l_request;

        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);

        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        if (l_loginInfo == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                "������LoginInfo���擾�ł��Ȃ����͗L�蓾�Ȃ���.");
        }
        log.debug(
            STR_METHOD_NAME
                + " .... logout user = "
                + l_loginInfo.getUsername());

        if (!l_securityService.logOut())
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���O�A�E�g�ł��Ȃ��ꍇ�͗L�蓾�Ȃ���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_loginReq.createResponse();
    }
}
@
