head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�T�[�r�X(WEB3MarginSwapMarginService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i�M�p����������n�T�[�r�X�j�B<BR>
 * <BR>
 * �M�p����������n�T�[�r�X�C���^�t�F�[�X<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@author ������
 * @@version 1.0
 */
public interface WEB3MarginSwapMarginService extends WEB3BusinessService 
{
    
    /**
     * �M�p����������n�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056920702E8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
