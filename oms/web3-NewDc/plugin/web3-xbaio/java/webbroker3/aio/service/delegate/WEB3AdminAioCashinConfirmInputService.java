head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.12.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioCashinConfirmInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F���̓T�[�r�X�C���^�[�t�F�C�X(WEB3AdminAioCashinConfirmInputService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬      
                   2004/10/22 ���z (���u) ���r���[              
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�����A���m�F���̓T�[�r�X)<BR>
 * �����A���m�F���̓T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public interface WEB3AdminAioCashinConfirmInputService extends WEB3BusinessService 
{
    
    /**
     * �����A���m�F���̓T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410749540164
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
