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
filename	WEB3AdminFeqSendQueueReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X(WEB3AdminFeqSendQueueReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/12 ꎉ� (���u) �V�K�쐬  
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X)<BR>
 * �Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X�C���^�t�F�[�X <BR>
 * <BR>
 * �i�g�����U�N�V���������F�ݒ�s�v�j<BR>
 *   
 * @@author ꎉ�
 * @@version 1.0
 */
public interface WEB3AdminFeqSendQueueReferenceService extends WEB3BusinessService 
{
    /**
     * �Ǘ��ҊO������SEND�L���[�Ɖ�������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FECAF006D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
