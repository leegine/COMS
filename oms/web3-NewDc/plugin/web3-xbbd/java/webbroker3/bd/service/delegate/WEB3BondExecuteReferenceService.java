head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ɖ�T�[�r�X (WEB3BondExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 ���� (���u) �V�K�쐬
*/
package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (���������Ɖ�T�[�r�X )<BR>
 * ���������Ɖ�T�[�r�X�C���^�t�F�C�X
 * 
 * @@author ����
 * @@version 1.0
 */
public interface WEB3BondExecuteReferenceService extends WEB3BusinessService
{
    /**
     * ���������Ɖ�����s�Ȃ��B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
