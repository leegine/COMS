head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityAttentionInfoNotifyServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityAttentionInfoNotifyServiceImplForMock
    extends WEB3AdminEquityAttentionInfoNotifyServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminEquityAttentionInfoNotifyServiceImplForMock.class);

    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl",
                "execute",
                new Class[] {WEB3BackRequest.class},
                new Object[] {l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl",
                "execute", new Class[] {WEB3BackRequest.class}))
        {
            log.debug("webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl.execute(WEB3BackRequest)");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                            "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl",
                            "execute",
                            new Class[] {WEB3BackRequest.class}).asWEB3BaseException();

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl",
                    "execute",
                    new Class[] {WEB3BackRequest.class}).asWEB3BaseRuntimeException();

            return (WEB3BackResponse)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl",
                    "execute",
                    new Class[] {WEB3BackRequest.class}).asObject();
        }
        return super.execute(l_request);
    }

}
@
