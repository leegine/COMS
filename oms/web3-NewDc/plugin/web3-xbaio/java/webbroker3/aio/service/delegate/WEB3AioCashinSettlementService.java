head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinSettlementService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ψ˗��T�[�r�X�C���^�[�t�F�C�X(WEB3AioCashinSettlementService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ���z (���u) �V�K�쐬
                   2004/10/23 ������ (���u) ���r���[
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (���ψ˗��T�[�r�X)<BR>
 * ���ψ˗��T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public interface WEB3AioCashinSettlementService extends WEB3BusinessService 
{
    
    /**
     * ���ψ˗��T�[�r�X�����{����B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FE653202F5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
