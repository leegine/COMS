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
filename	WEB3AdminFeqExchangeRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ב֓o�^�T�[�r�X(WEB3AdminFeqExchangeRegistService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �Ջ`�g(���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[  
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�O�������ב֓o�^�T�[�r�X)<BR>
 * �O�������ב֓o�^�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author �Ջ`�g
 * @@version 1.0
 */
public interface WEB3AdminFeqExchangeRegistService extends WEB3BusinessService 
{
    
    /**
     * �O�������ב֓o�^�T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4210843E011D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
