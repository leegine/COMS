head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminEquityExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �Ǘ��Ҋ����������Ɖ�T�[�r�X�C���^�t�F�C�X(WEB3AdminEquityExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��Ҋ����������Ɖ�T�[�r�X)<BR>
 * <BR>
 * �Ǘ��Ҋ����������Ɖ�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * WEB3AdminEquityExecuteReferenceService interface<BR>
 * <BR>
 * @@author wanishree
 * @@version 1.0
 */
public interface WEB3AdminEquityExecuteReferenceService extends WEB3BusinessService
{

    /**
     * �����������Ɖ�����s���B<BR>
     * <BR>
     * Execute WEB3AdminEquityExecuteReferenceService process<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41A5C06B026A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
