head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.28.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3UpdateAccessTimeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Z�b�V�����A�N�Z�X�����X�V�ׂ̈̃��N�G�X�g�E�n���h��(WEB3UpdateAccessTimeHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26�@@�e�n(SRA) �V�K�쐬
 */
package webbroker3.login.handler;

import webbroker3.login.message.WEB3UpdateAccessTimeRequest;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * �Z�b�V�����A�N�Z�X�����X�V�ׂ̈̃��N�G�X�g�E�n���h���B<BR>
 * @@author      �e�n(SRA)
 * @@version     0.01
 */
public final class WEB3UpdateAccessTimeHandler implements MessageHandler
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3UpdateAccessTimeHandler.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * @@roseuid 403EF3C9020A
     */
    public WEB3UpdateAccessTimeHandler()
    {

    }

    /**
     * (�Z�b�V�����E�A�N�Z�X�����X�V)<BR>
     * �Z�b�V�����A�N�Z�X�����X�V�ׂ̈̃��N�G�X�g���󂯕t����B<BR>
     * �������Ȃ��B���׌y���ׁ̈A���O���f�o�b�O�̂ݏo�͂���B<BR>
     * @@param l_request
     * @@return Response
     * @@roseuid 403DBE5E0222
     */
    public Response updateAccessTime(WEB3UpdateAccessTimeRequest l_request)
    {
        final String STR_METHOD_NAME =
            "updateAccessTime(WEB3UpdateAccessTimeRequest)";
        log.debug(STR_METHOD_NAME + " .... �Z�b�V�����E�A�N�Z�X�����X�V");
        return l_request.createResponse();
    }
};
@
