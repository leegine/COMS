head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�������������������̓T�[�r�X(WEB3ToSuccEquityChangeOrderInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderInputService;


/**
 * (�i�A���j�������������������̓T�[�r�X)<BR>
 * �i�A���j�������������������̓T�[�r�X�C���^�t�F�[�X�B<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public interface WEB3ToSuccEquityChangeOrderInputService extends WEB3EquityChangeOrderInputService 
{
    
    /**
     * �i�A���j�������������������̓T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4337C11600FC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
