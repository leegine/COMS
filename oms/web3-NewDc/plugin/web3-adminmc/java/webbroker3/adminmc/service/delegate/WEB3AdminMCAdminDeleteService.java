head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminDeleteService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�X(WEB3AdminMCAdminDeleteService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 Tongwei(���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;


/**
 * (�Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�X)<BR>
 * �Ǘ��҃��j���[����Ǘ��ҍ폜�T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author Tongwei
 * @@version 1.0 
 */
public interface WEB3AdminMCAdminDeleteService extends WEB3BusinessService 
{
    
    /**
     * �Ǘ��ҍ폜���������{����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DAF0D037A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
