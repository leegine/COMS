head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutTradingPowerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�N�T�[�r�X(WEB3AioCashoutTradingPowerService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�o���]�̓`�F�b�N�T�[�r�X)<BR>
 * �o���]�̓`�F�b�N�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public interface WEB3AioCashoutTradingPowerService 
    extends WEB3BackBusinessService 
{
    
    /**
     * �o���]�̓`�F�b�N�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41294D6400EF
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException;
}
@
