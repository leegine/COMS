head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//�\�[�X �t�@@�C��: D:\\DD\\srcGen\\webbroker3\\ifo\\service\\delegate\\WEB3FuturesOpenMarginInputService.java

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�敨�V�K�����̓T�[�r�X)<BR>
 * �����w���敨�V�K�����̓T�[�r�X�C���^�t�F�C�X
 */
public interface WEB3FuturesOpenContractInputService extends WEB3BusinessService 
{
    
    /**
     * �����w���敨�V�K�����̓T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A99D6201CE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
