head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccAdditionalContentsSelectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ǉ����e�I���T�[�r�X(WEB3ToSuccAdditionalContentsSelectService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�ǉ����e�I���T�[�r�X)<BR>
 * �ǉ����e�I���T�[�r�X�C���^�t�F�C�X<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3ToSuccAdditionalContentsSelectService extends WEB3BusinessService 
{
    
    /**
     * �ǉ����e�I���T�[�r�X�������s���B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D173F0068
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
