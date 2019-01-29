head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.05.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccIfoOrderUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : (WEB3ToSuccIfoOrderUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/07 à¿óz(íÜêu) êVãKçÏê¨
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccIfoOrderUnitServiceImplForMock extends WEB3ToSuccIfoOrderUnitServiceImpl
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoOrderUnitServiceImplForMock.class);
    
    public void submitFuturesOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                "submitFuturesOpenContractOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_rsvIfoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                "submitFuturesOpenContractOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log.debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImplForMock.submitFuturesOpenContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitFuturesOpenContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        super.submitFuturesOpenContractOrder(l_rsvIfoOrderUnit);
    }
    
    public void submitFuturesSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                "submitFuturesSettleContractOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_rsvIfoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                "submitFuturesSettleContractOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log.debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImplForMock.submitFuturesSettleContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitFuturesSettleContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        super.submitFuturesSettleContractOrder(l_rsvIfoOrderUnit);
    }
    
    public void submitOptionsOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                "submitOptionsOpenContractOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_rsvIfoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                "submitOptionsOpenContractOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log.debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImplForMock.submitOptionsOpenContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitOptionsOpenContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        super.submitOptionsOpenContractOrder(l_rsvIfoOrderUnit);
    }
    
    public void submitOptionsSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                "submitOptionsSettleContractOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}, new Object[]
                {l_rsvIfoOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                "submitOptionsSettleContractOrder", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class}))
        {
            log.debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImplForMock.submitOptionsSettleContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitOptionsSettleContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}).asVoid();
            return;
        }
        super.submitOptionsSettleContractOrder(l_rsvIfoOrderUnit);
    }    
}
@
