head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����o���ʒm�T�[�r�X(WEB3OptionOrderExecNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (OP�o���ʒm�T�[�r�X)<BR>
 * <BR>
 * �����w���I�v�V�����o���ʒm�T�[�r�X�C���^�[�t�F�C�X<BR>
 *                                                                     
 * @@author 羉s
 * @@version 1.0
 */
public interface WEB3OptionOrderExecNotifyService extends WEB3BackBusinessService 
{
    
    /**
     * �����w���I�v�V�����o���ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�T�[�r�X�j�I�v�V�����o���ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057BDE903DA
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
