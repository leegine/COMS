head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockStatusUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X(WEB3AdminBondOrderLockStatusUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ����(���u) �V�K�쐬         
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X)<BR>
 * �Ǘ��ҍ��������b�N�敪�X�V�T�[�r�X�C���^�t�F�[�X
 * 
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminBondOrderLockStatusUpdateService extends WEB3BusinessService
{
    
    /**
     * ���Ǘ��Ғ������b�N�敪�X�V�������s��
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C42D9D001E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException;
}
@
