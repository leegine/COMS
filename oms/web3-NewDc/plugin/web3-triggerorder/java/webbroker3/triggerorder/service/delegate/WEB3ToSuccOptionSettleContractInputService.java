head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���jOP�ԍϓ��̓T�[�r�X(WEB3ToSuccOptionSettleContractInputService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 �k�v�u(���u) �V�K�쐬 ���f��297
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractInputService;

/**
 * (�i�A���jOP�ԍϓ��̓T�[�r�X)<BR>
 * �i�A���jOP�ԍϓ��̓T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public interface WEB3ToSuccOptionSettleContractInputService extends WEB3OptionSettleContractInputService
{

    /**
     * �i�A���j�����w���I�v�V�����ԍϓ��͉�ʕ\�����������{����B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A97A5E038B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
