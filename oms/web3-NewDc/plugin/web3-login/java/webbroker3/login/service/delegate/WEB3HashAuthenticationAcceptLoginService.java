head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.22.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3HashAuthenticationAcceptLoginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �n�b�V���F�؃��O�C���T�[�r�X(WEB3HashAuthenticationAcceptLoginService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/20 �h�C(���u) �V�K�쐬
*/

package webbroker3.login.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �n�b�V���F�؃��O�C���T�[�r�X<BR>
 * <BR>
 * @@author  �h�C(���u)
 * @@version 1.0
 */
public interface WEB3HashAuthenticationAcceptLoginService extends WEB3BusinessService
{

    /**
     * �n�b�V���F�؃��O�C���T�[�r�X�����s����<BR>
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058167200FA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
