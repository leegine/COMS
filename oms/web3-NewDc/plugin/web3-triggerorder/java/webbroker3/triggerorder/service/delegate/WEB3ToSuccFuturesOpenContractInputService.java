head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�V�K�����̓T�[�r�X(WEB3ToSuccFuturesOpenContractInputService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 ���n(���u) �V�K�쐬���f��256
Revision History : 2008/04/01 ���n(���u) �d�l�ύX���f��310
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractInputService;


/**
 * (�i�A���j�敨�V�K�����̓T�[�r�X)<BR>
 * �i�A���j�����w���敨�V�K�����̓T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public interface WEB3ToSuccFuturesOpenContractInputService extends WEB3FuturesOpenContractInputService
{

    /**
     * �i�A���j�����w���敨�V�K�����̓T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9810203CF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
