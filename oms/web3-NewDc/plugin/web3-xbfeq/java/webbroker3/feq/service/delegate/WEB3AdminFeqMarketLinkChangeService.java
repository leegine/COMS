head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqMarketLinkChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X (WEB3AdminFeqMarketRequestDivChangeService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/12 �����q (���u) �V�K�쐬 
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X )<BR>
 * �Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X�C���^�t�F�[�X <BR>
 * <BR>
 *�i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
 *   
 * @@author �����q
 * @@version 1.0
 */
public interface WEB3AdminFeqMarketLinkChangeService extends WEB3BusinessService
{
    /**
     * �Ǘ��ҊO�������s��A���敪�ύX���������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FECAF006D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;	
}
@
