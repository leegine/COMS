head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//�\�[�X �t�@@�C��: C:\\web3ModelDetalRelease\\srcGen\\webbroker3\\ifo\\service\\delegate\\WEB3IfoChangeCancelOrderAcceptService.java

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�敨OP���������t�T�[�r�X)<BR>
 * �敨OP���������t�T�[�r�X�C���^�[�t�F�C�X<BR>
 */
public interface WEB3IfoChangeCancelOrderAcceptService extends WEB3BackBusinessService
{

    /**
     * �敨OP���������t�T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3IfoChangeCancelAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 408383220309
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
