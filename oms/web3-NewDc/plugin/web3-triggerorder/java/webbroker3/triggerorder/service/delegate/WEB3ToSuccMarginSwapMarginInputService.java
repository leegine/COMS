head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����������n���̓T�[�r�X(WEB3ToSuccMarginSwapMarginInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/8 杊��](���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginInputService;

/**
 * (�i�A���j�M�p����������n���̓T�[�r�X)<BR>
 * �i�A���j�M�p����������n���̓T�[�r�X�C���^�t�F�[�X�B<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public interface WEB3ToSuccMarginSwapMarginInputService extends WEB3MarginSwapMarginInputService 
{
    
    /**
     * �i�A���j�M�p����������n���͉�ʕ\�����������{����B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4340E2980082
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@