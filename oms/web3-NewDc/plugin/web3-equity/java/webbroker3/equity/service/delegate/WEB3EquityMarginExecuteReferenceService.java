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
filename	WEB3EquityMarginExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E�M�p�������Ɖ�T�[�r�X(WEB3EquityMarginExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 �����Q(���u) �V�K�쐬
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�����E�M�p�������Ɖ�T�[�r�X)<BR>
 * �����E�M�p�������Ɖ�T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@ author �����Q <BR>
 * @@ version 1.0<BR>
 */
public interface WEB3EquityMarginExecuteReferenceService extends WEB3BusinessService
{
    /**
     * �����E�M�p�������Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 455D1AAE0318
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
