head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBalanceReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����c���Ɖ�T�[�r�X(WEB3MstkBalanceReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �i�����~�j�����c���Ɖ�T�[�r�X�j�B<BR>
 * <BR>
 * �����~�j�����c���Ɖ�T�[�r�X�C���^�t�F�C�X<BR>
 */
public interface WEB3MstkBalanceReferenceService extends WEB3BusinessService
{
    
    /**
     * �����~�j�����c���Ɖ�����s���B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41C2D23A017E<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@