head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminIfoExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ґ敨OP�������Ɖ�T�[�r�X(WEB3AdminIfoExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��Ґ敨OP�������Ɖ�T�[�r�X)<BR>
 * <BR>
 * �Ǘ��Ґ敨OP�������Ɖ�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * WEB3AdminIfoExecuteReferenceService interface<BR>
 * <BR>
 * @@author Ambha
 * @@version 1.0
 */
public interface WEB3AdminIfoExecuteReferenceService extends WEB3BusinessService
{

    /**
     * �敨OP�������Ɖ�����s���B
     * <BR>
     * Execute WEB3AdminIfoExecuteReferenceService process<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41AE9A6302BA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;

}
@
