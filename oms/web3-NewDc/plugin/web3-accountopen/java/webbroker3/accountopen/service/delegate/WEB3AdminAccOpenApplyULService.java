head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyULService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X(WEB3AdminAccOpenApplyULService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 ���g(���u) �V�K�쐬 ���f�� No.148,No.152
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X)<BR>
 * �Ǘ��Ҍ����J�ݐ\��UL�T�[�r�X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public interface WEB3AdminAccOpenApplyULService extends WEB3BusinessService
{

    /**
     * �����J�ݐ\���A�b�v���[�h�������s���B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299A18023F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
