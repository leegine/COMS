head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�g�T�[�r�X(WEB3AioOnPaymentCooperationService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�o���A�g�T�[�r�X) <BR>
 * �o���A�g�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public interface WEB3AioOnPaymentCooperationService extends WEB3BackBusinessService
{
    /**
     * �o���A�g�������s���B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@roseuid 41BCF2CC0279
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}@
