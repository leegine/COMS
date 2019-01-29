head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3RuitoCancelAcceptedUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.xbruito.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl;

public class WEB3RuitoCancelAcceptedUnitServiceImplForMock extends
        WEB3RuitoCancelAcceptedUnitServiceImpl
{
    public void notifyCancelAcceptedComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl",
            "notifyCancelAcceptedComplete",
            new Class[]
                {RuitoOrderUnit.class, WEB3RuitoAcceptedDecisionInterceptor.class},
            new Object[]
                {l_ruitoOrderUnit, l_ruitoAcceptedDecisionInterceptor});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl",
            "notifyCancelAcceptedComplete",
            new Class[]
                {RuitoOrderUnit.class, WEB3RuitoAcceptedDecisionInterceptor.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl",
                "notifyCancelAcceptedComplete",
                new Class[]{
                    RuitoOrderUnit.class,
                    WEB3RuitoAcceptedDecisionInterceptor.class}).asWEB3BaseException();

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl",
                "notifyCancelAcceptedComplete",
                new Class[]{
                    RuitoOrderUnit.class,
                    WEB3RuitoAcceptedDecisionInterceptor.class}).asVoid();

            return;
        }

        super.notifyCancelAcceptedComplete(l_ruitoOrderUnit, l_ruitoAcceptedDecisionInterceptor);
    }
}
@
