head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֒ʒm�T�[�r�X�C���^�[�t�F�C�X(WEB3AioSecurityTransferNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �����(���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (�،��U�֒ʒm�T�[�r�X)<BR>
 * �،��U�֒ʒm�T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AioSecurityTransferNotifyService extends WEB3BackBusinessService 
{
    
    /**
     * �،��U�֒ʒm�������s���B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@roseuid 415781910128
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
