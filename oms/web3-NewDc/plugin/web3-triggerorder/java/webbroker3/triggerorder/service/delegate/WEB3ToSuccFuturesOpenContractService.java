head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�V�K�������T�[�r�X(WEB3ToSuccFuturesOpenContractService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 ���n(���u) �V�K�쐬���f��257
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractService;


/**
 * (�i�A���j�敨�V�K�������T�[�r�X)<BR>
 * �i�A���j�敨�V�K�������T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public interface WEB3ToSuccFuturesOpenContractService extends WEB3FuturesOpenContractService
{

    /**
     * �i�A���j�敨�V�K�������T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A98197014C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
