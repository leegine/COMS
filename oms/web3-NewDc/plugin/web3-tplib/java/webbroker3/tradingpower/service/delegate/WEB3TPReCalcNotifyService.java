head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�ʒm�T�[�r�X(WEB3TPReCalcNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�]�͌v�Z�ʒm�T�[�r�X)
 */
public interface WEB3TPReCalcNotifyService extends WEB3BackBusinessService
{
    /**
     * �]�͍Čv�Z�ʒm���������{����<BR>
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
