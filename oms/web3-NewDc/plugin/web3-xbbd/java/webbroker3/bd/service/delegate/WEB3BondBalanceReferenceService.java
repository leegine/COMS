head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�T�[�r�X (WEB3BondBalanceReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (���c���Ɖ�T�[�r�X)<BR>
 * ���c���Ɖ�T�[�r�X�C���^�t�F�C�X
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public interface WEB3BondBalanceReferenceService extends WEB3BusinessService
{
    /**
     * ���c���Ɖ�����s��
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44D7227703CC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)throws WEB3BaseException;
}
    
@
