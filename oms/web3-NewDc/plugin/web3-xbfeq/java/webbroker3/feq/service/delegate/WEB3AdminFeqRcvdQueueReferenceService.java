head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�X(WEB3AdminFeqRcvdQueueReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ����� (���u) �V�K�쐬
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�X)<BR>
 * �Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * �i�g�����U�N�V���������F�ݒ�s�v�j<BR>
 * 
 * @@author �����
 * @@version 1.0
 */
public interface WEB3AdminFeqRcvdQueueReferenceService extends WEB3BusinessService 
{
    /**
     * �O�������i�ǁj�q�b�u�c�L���[�Ɖ�������{����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
