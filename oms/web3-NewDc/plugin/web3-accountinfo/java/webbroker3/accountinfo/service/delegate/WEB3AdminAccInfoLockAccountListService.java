head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X (WEB3AdminAccInfoLockAccountListService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X )<BR>
 * �Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X <BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AdminAccInfoLockAccountListService extends WEB3BusinessService
{
    /**
     * �Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�����{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
