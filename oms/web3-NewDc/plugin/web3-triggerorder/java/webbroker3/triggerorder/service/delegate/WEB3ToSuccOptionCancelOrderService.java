head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionCancelOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���jOP��������T�[�r�iWEB3ToSuccOptionCancelOrderService.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 ���� (���u) �V�K�쐬 ���f��No.280
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 *  (�i�A���jOP��������T�[�r)<BR>
 * �i�A���jOP��������T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public interface WEB3ToSuccOptionCancelOrderService extends WEB3BusinessService
{

    /**
     * �i�A���jOP����T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A97840019F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
