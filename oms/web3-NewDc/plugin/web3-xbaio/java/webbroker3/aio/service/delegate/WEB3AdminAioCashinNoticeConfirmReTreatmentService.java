head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioCashinNoticeConfirmReTreatmentService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�m�F�ď����T�[�r�X�C���^�[�t�F�C�X(WEB3AdminAioCashinNoticeConfirmReTreatmentService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/20 ���r (���u) �V�K�쐬                             
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�����ʒm�m�F�ď����T�[�r�X)<BR>
 * �����ʒm�m�F�ď����T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ���r(���u)
 * @@version 1.0
 */

public interface WEB3AdminAioCashinNoticeConfirmReTreatmentService extends WEB3BusinessService 
{
    
    /**
     * �����ʒm�m�F�ď������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410755400349
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
