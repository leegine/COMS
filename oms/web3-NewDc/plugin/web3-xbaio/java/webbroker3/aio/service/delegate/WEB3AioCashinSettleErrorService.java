head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinSettleErrorService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���σG���[�T�[�r�X�C���^�[�t�F�C�X(WEB3AioCashinSettleErrorService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �����(���u) �V�K�쐬
                   2004/10/22 ���E(���u) ���r���[
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (���σG���[�T�[�r�X)<BR>
 * ���σG���[�T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AioCashinSettleErrorService extends WEB3BusinessService 
{
    
    /**
     * ���σG���[�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411B30240012
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)throws WEB3BaseException;
}
@
