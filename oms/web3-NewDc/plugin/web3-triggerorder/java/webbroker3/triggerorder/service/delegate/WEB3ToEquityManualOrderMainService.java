head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.06.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToEquityManualOrderMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮�������C���T�[�r�X(WEB3ToEquityManualOrderMainService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/3/6 ��O��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�����蓮�������C���T�[�r�X)<BR>
 * 
 * @@author ��O�� <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToEquityManualOrderMainService extends WEB3BusinessService 
{

    /**
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E9AD7C02BF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
    
}
@
