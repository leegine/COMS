head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������ԍσT�[�r�X(WEB3ToSuccMarginChangeCloseMarginService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 杊��](���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�i�A���j�M�p��������ԍσT�[�r�X)<BR>
 * �i�A���j�M�p��������ԍσT�[�r�X�C���^�[�t�F�C�X
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public interface WEB3ToSuccMarginChangeCloseMarginService extends WEB3ToSuccMarginCloseMarginService 
{
    
    /**
     * �i�A���j�M�p��������ԍσT�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433CFCDD022C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
