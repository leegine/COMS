head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.13.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϖ��׃T�[�r�X(WEB3HistorySettleDetailService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  �Ɍ��t(���u) �V�K�쐬
*/
package webbroker3.tradehistory.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (���ϖ��׃T�[�r�X)<BR>
 * ���ϖ��׃T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0
 */
public interface WEB3HistorySettleDetailService extends WEB3BusinessService 
{
    
    /**
     * ���ϖ��ו\���������s���B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413EBAA300FE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
