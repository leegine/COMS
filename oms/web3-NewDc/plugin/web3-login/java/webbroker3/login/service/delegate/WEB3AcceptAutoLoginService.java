head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.22.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptAutoLoginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�������O�C���T�[�r�X�̃G���g���[�|�C���g(WEB3AcceptAutoLoginService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/09 �e�n(SRA) �V�K�쐬
*/

package webbroker3.login.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �ڋq�������O�C���T�[�r�X�̃G���g���[�|�C���g<BR>
 * <BR>
 * @@author  �e�n(SRA)
 * @@version 1.0
 */
public interface WEB3AcceptAutoLoginService extends WEB3BusinessService
{

    /**
     * �ڋq�������O�C���T�[�r�X���s<BR>
     * @@param    WEB3GenRequest
     * @@param l_request
     * @@return   WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4069151B00FA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
