head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeClosingContractService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�����ԍσT�[�r�X�iWEB3ToSuccFuturesChangeClosingContractService.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 �g�E�N�| (���u) �V�K�쐬 ���f��No.270
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�i�A���j�敨�����ԍσT�[�r�X)<BR>
 * �i�A���j�敨�����ԍσT�[�r�X�C���^�t�F�[�X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public interface WEB3ToSuccFuturesChangeClosingContractService
    extends WEB3ToSuccFuturesSettleContractOrderService
{

    /**
     * �i�A���j�敨�����ԍσT�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9842D006C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
