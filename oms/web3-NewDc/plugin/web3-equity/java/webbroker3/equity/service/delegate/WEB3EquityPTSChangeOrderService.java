head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSChangeOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�����������������T�[�r�X(WEB3EquityPTSChangeOrderService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 �����F (���u) �V�K�쐬 ���f��1241
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * ((PTS)�����������������T�[�r�X)<BR>
 * (PTS)�����������������T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3EquityPTSChangeOrderService extends WEB3BusinessService
{

    /**
     * (PTS)�����������������T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 474A6091022A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
