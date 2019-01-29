head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.27.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSChangeOrderInputServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityPTSChangeOrderInputServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/26 金傑（中訊）新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSChangeOrderInputServiceImplForMock extends WEB3EquityPTSChangeOrderInputServiceImpl
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityPTSChangeOrderInputServiceImplForMock.class);
    
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class }, new Object[]
                { l_request });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class }))
        {
            log
                    .debug("webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class }).asWEB3BaseException();
            return (WEB3GenResponse) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class }).asObject();
        }
        return super.execute(l_request);
    }
}
@
