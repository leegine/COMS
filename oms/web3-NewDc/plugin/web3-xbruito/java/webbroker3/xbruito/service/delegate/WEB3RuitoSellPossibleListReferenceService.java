head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellPossibleListReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ����\�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X(WEB3RuitoSellPossibleListReferenceService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���u�� (���u) �V�K�쐬
*/

package webbroker3.xbruito.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * �ݓ����\�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X<BR>
 */
public interface WEB3RuitoSellPossibleListReferenceService
    extends WEB3BusinessService
{

    /**
     * ���͉�ʕ\������<BR>
     * �ݐϓ����̉��\�ꗗ�Ɖ��ʕ\�����������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4092031902CE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
