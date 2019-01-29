head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.04.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionChangeClosingContractServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî 
 File Name        : WEB3ToSuccOptionChangeClosingContractServiceImplForMock.java
 Author Name      : Daiwa Institute of Research  
 Revesion History : 2008/04/22 ókïvéu (íÜêu) êVãKçÏê¨  
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionChangeClosingContractServiceImplForMock extends
        WEB3ToSuccOptionChangeClosingContractServiceImpl
{
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3ToSuccOptionChangeClosingContractServiceImplForMock.class);

    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class}, new Object[]
                {l_request});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class}))
        {
            log
                    .debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                            "execute", new Class[]
                            {WEB3GenRequest.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                            "execute", new Class[]
                            {WEB3GenRequest.class}).asWEB3BaseRuntimeException();
            return (WEB3GenResponse) TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                            "execute", new Class[]
                            {WEB3GenRequest.class}).asObject();
        }
        return super.execute(l_request);
    }

    protected void validateRequestDataAtReversingTrade(WEB3GenRequest l_request,
            WEB3ToSuccIfoOrderUnitImpl l_rsvOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                "validateRequestDataAtReversingTrade", new Class[]
                {WEB3GenRequest.class, WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_request,l_rsvOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                "validateRequestDataAtReversingTrade", new Class[]
                {WEB3GenRequest.class, WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log
                    .debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                            "validateRequestDataAtReversingTrade", new Class[]
                            {WEB3GenRequest.class, WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                            "validateRequestDataAtReversingTrade", new Class[]
                            {WEB3GenRequest.class, WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseRuntimeException();
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                            "validateRequestDataAtReversingTrade", new Class[]
                            {WEB3GenRequest.class, WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        return;
    }
    protected void validateSettledContract(WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit)
    throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                "validateSettledContract", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_toSuccIfoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                "validateSettledContract", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log
                    .debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                            "validateSettledContract", new Class[]
                            {WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl",
                            "validateSettledContract", new Class[]
                            {WEB3ToSuccIfoOrderUnitImpl.class}).asWEB3BaseRuntimeException();
            return;
        }
        return;
    }
}
@
