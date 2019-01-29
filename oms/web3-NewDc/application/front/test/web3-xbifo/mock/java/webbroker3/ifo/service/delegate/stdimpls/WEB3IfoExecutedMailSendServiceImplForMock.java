head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.44.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoExecutedMailSendServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoExecutedMailSendServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/26 金傑（中訊）新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoExecutedMailSendServiceImplForMock extends WEB3IfoExecutedMailSendServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoExecutedMailSendServiceImplForMock.class);
    
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strCloseReasonCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", "sendMailProcess",
                new Class[]
                { OrderUnit.class, String.class }, new Object[]
                { l_orderUnit, l_strCloseReasonCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", "sendMailProcess",
                new Class[]
                { OrderUnit.class, String.class }))
        {
            log
                    .debug("webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImplForMock.sendMailProcess()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", "sendMailProcess",
                    new Class[]
                    { OrderUnit.class, String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl", "sendMailProcess",
                    new Class[]
                    { OrderUnit.class, String.class }).asVoid();
            return;
        }
        super.sendMailProcess(l_orderUnit, l_strCloseReasonCode);
    }
}
@
