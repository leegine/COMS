head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^�Ώیڋq�ꗗ�T�[�r�X(WEB3CCOperatorAccountListService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 ���n�m (���u) �V�K�쐬 ���f��No.039
*/

package webbroker3.login.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (CC�I�y���[�^�Ώیڋq�ꗗ�T�[�r�X)<BR>
 * CC�I�y���[�^�Ώیڋq�ꗗ�T�[�r�X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public interface WEB3CCOperatorAccountListService extends WEB3BusinessService
{

    /**
     * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A�������ʂ����X�|���X�ɐݒ肵�Ă�
     * �Ԃ��B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46A0291701C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;

}
@
