head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinNoticeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���T�[�r�X(WEB3AioCashinNoticeService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬  
                   2004/10/22 ���� (���u) ���r���[    
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�����A���T�[�r�X)<BR>
 * �����A���T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public interface WEB3AioCashinNoticeService extends WEB3BusinessService 
{
    
    /**
     * �����A���T�[�r�X���������{����B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@roseuid 40FE5E4F0268
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
