head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoBalanceReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�c���Ɖ�T�[�r�X�C���^�t�F�C�X(WEB3IfoBalanceReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * (�敨OP�c���Ɖ�T�[�r�X)<BR>
 * �敨OP�c���Ɖ�T�[�r�X�C���^�t�F�C�X<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3IfoBalanceReferenceService extends WEB3BusinessService 
{
   
    /**
     * �����w���敨/�I�v�V�����c���Ɖ�����s���B
     * @@param l_request - ���N�G�X�g
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41AAC9CD011E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
