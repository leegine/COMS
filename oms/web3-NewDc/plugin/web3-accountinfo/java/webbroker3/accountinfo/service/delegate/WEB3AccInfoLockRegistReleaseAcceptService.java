head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���b�N�o�^������t�T�[�r�X(WEB3AccInfoLockRegistReleaseAcceptService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (���b�N�o�^������t�T�[�r�X)<BR>
 * ���b�N�o�^������t�T�[�r<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AccInfoLockRegistReleaseAcceptService extends WEB3BackBusinessService 
{
    /**
     * ���b�N�o�^������t�T�[�r�X�C���^�t�F�C�X�����{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
