head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���jOP�ԍϒ����T�[�r�X(WEB3ToSuccOptionSettleContractOrderService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 �k�v�u(���u) �V�K�쐬 ���f��283
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderService;

/**
 * (�i�A���jOP�ԍϒ����T�[�r�X)<BR>
 * �i�A���j�I�v�V�����ԍϒ����T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public interface WEB3ToSuccOptionSettleContractOrderService extends WEB3OptionSettleContractOrderService
{

    /**
     * �i�A���j�I�v�V�����ԍσT�[�r�X���������{����B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A97A100291
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
