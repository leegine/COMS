head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�敨�ԍϒ����T�[�r�X�iWEB3ToSuccFuturesSettleContractOrderService.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/14 �И���(���u) �V�K�쐬���f��No.259
 */
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;


/**
 * (�i�A���j�敨�ԍϒ����T�[�r�X)<BR>
 * �i�A���j�敨�ԍϒ����T�[�r�X�C���^�t�F�[�X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public interface WEB3ToSuccFuturesSettleContractOrderService extends WEB3FuturesSettleContractOrderService
{

    /**
     * �i�A���j�敨�ԍσT�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A983B70276
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
