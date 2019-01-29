head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToIfoOrderReferenceServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminToIfoOrderReferenceServiceImplForMock extends WEB3AdminToIfoOrderReferenceServiceImpl
{

    public WEB3AdminToIfoOrderReferenceServiceImplForMock()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminToIfoOrderReferenceServiceImplForMock.class);
    
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                "execute", 
                new Class[] {WEB3GenRequest.class}, 
                new Object[] {l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                "execute", new Class[] {WEB3GenRequest.class}))
        {
            log.debug("webbroker3.dirsec.WEB3AdminToIfoOrderReferenceServiceImpl.execute(WEB3GenRequest)");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                            "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                            "execute",
                            new Class[] {WEB3GenRequest.class}).asWEB3BaseException();

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class}).asWEB3BaseRuntimeException();

            return (WEB3GenResponse)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class}).asObject();
        }
        return super.execute(l_request);
    }
}
@
