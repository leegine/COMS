head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������V�K���T�[�r�X(WEB3ToSuccMarginChangeOpenMarginService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17�@@���@@��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�i�A���j�M�p��������V�K���T�[�r�X)<BR>
 * �i�A���j�M�p��������V�K���T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public interface WEB3ToSuccMarginChangeOpenMarginService extends WEB3ToSuccMarginOpenMarginService 
{
    
    /**
     * �i�A���j�M�p��������V�K���T�[�r�X���������{����<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCCC401EA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
