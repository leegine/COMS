head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ�T�[�r�X(WEB3AdminBondExecuteReferenceService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬    
*/

package webbroker3.adminorderexecinquiry.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (���Ǘ��Ғ������Ɖ�T�[�r�X)<BR>
 * �Ǘ��ҍ��������Ɖ�T�[�r�X�C���^�t�F�[�X<BR>
 *  
 * @@author �����q(���u)
 * @@version 1.0
 */
public interface WEB3AdminBondExecuteReferenceService extends WEB3BusinessService
{
    
    /**
     * ���Ǘ��Ғ������Ɖ���s��
     * @@param l_request - ���N�G�X�g<BR>
     * ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B7510202CC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
