head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionOpenContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���jOP�V�K�����̓T�[�r�X(WEB3ToSuccOptionOpenContractInputService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 ���n(���u) �V�K�쐬���f��266
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;


/**
 * (�i�A���jOP�V�K�����̓T�[�r�X)<BR>
 * �i�A���jOP�V�K�����̓T�[�r�X�C���^�t�F�[�X<BR>
 *
 * @@author ���n
 * @@version 1.0
 */
public interface WEB3ToSuccOptionOpenContractInputService extends WEB3OptionOpenContractInputService
{

    /**
     * �i�A���jOP�V�K�����̓T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9791C00D4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
