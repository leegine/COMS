head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃J�e�S���[�o�^�T�[�r�X(WEB3AdminMutualCategoryRegistService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��҃J�e�S���[�o�^�T�[�r�X)<BR>
 * �Ǘ��҃J�e�S���[�o�^�T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public interface WEB3AdminMutualCategoryRegistService extends WEB3BusinessService 
{
    /**
     * (execute)<BR>
     * �����M�������J�e�S���[�o�^�����{����B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153D8E00046
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
