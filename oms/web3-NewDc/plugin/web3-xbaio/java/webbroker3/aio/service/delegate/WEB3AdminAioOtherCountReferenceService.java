head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioOtherCountReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���̑������Ɖ�T�[�r�X(WEB3AdminAioOtherCountReferenceService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/7/7 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (���̑������Ɖ�T�[�r�X)<BR>
 * ���̑������Ɖ�T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public interface WEB3AdminAioOtherCountReferenceService extends WEB3BusinessService 
{
    
    /**
     * ���̑������Ɖ�T�[�r�X�������s���B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E37D7E01CF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
