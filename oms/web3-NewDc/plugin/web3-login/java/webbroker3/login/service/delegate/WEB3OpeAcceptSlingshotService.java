head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.22.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptSlingshotService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq���肷�܂�SS�J�ڃT�[�r�X�̃G���g���[�|�C���g(WEB3OpeAcceptSlingshotService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/09 �e�n(SRA) �V�K�쐬
*/

package webbroker3.login.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �ڋq���肷�܂�SS�J�ڃT�[�r�X�̃G���g���[�|�C���g<BR>
 * <BR>
 * @@author  �e�n(SRA)
 * @@version 1.0
 */
public interface WEB3OpeAcceptSlingshotService extends WEB3BusinessService
{

    /**
     * �ڋq���肷�܂�SS�J�ڃT�[�r�X���s<BR>
     * @@param    WEB3GenRequest
     * @@param l_request
     * @@return   WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406D24A20262
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
