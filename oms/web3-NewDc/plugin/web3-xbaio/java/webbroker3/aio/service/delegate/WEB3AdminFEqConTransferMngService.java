head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFEqConTransferMngService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֊Ǘ��T�[�r�X(WEB3AdminFEqConTransferMngService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 ���E (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�O���U�֊Ǘ��T�[�r�X)<BR>
 * �O���U�֊Ǘ��T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public interface WEB3AdminFEqConTransferMngService extends WEB3BusinessService 
{
    
    /**
     * �O�������ւ̐U�֊Ǘ��������s���B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E398A2000A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
