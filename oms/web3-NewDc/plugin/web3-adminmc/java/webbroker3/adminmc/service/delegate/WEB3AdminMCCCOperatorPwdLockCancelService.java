head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.33.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorPwdLockCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽(WEB3AdminMCCCOperatorPwdLockCancelService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 TongWei(���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;


/**
 * (�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽�C���^�t�F�C�X<BR>
 * 
 * @@author TongWei
 * @@version 1.0
 */
public interface WEB3AdminMCCCOperatorPwdLockCancelService extends WEB3BusinessService 
{
    
    /**
     * CC�I�y���[�^�p�X���[�h���b�N�������������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F24600114
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
