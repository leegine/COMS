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
filename	WEB3EquityPTSExecEndNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�����o���I���ʒm�T�[�r�X(WEB3EquityPTSExecEndNotifyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ��іQ(���u) �V�K�쐬 ���f��No.1285
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * ((PTS)�����o���I���ʒm�T�[�r�X)<BR>
 * ((PTS)�����o���I���ʒm�T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public interface WEB3EquityPTSExecEndNotifyService extends WEB3BackBusinessService
{
    /**
     * (PTS)�����o���I���ʒm�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056EB8E03D5
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
