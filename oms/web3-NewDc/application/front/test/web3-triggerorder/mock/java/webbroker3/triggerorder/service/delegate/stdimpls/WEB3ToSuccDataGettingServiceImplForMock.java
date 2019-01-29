head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.05.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccDataGettingServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccDataGettingServiceImplForMock.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/07 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccDataGettingServiceImplForMock extends WEB3ToSuccDataGettingServiceImpl
{
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccDataGettingServiceImplForMock.class);
    
    public void createSuccOrderUnit(WEB3SuccOrderUnit l_succOrderUnit, OrderUnit l_orderUnit,
            boolean l_blnIsPossibleFlagSet) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImpl",
                "createSuccOrderUnit", new Class[]
                {WEB3SuccOrderUnit.class, OrderUnit.class, boolean.class}, new Object[]
                {l_succOrderUnit, l_orderUnit, new Boolean(l_blnIsPossibleFlagSet)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImpl",
                "createSuccOrderUnit", new Class[]
                {WEB3SuccOrderUnit.class, OrderUnit.class, boolean.class}))
        {
            log.debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImplForMock.createSuccOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImpl",
                    "createSuccOrderUnit", new Class[]
                    {WEB3SuccOrderUnit.class, OrderUnit.class, boolean.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImpl",
                    "createSuccOrderUnit", new Class[]
                    {WEB3SuccOrderUnit.class, OrderUnit.class, boolean.class}).asVoid();
            return;
        }
        super.createSuccOrderUnit(l_succOrderUnit, l_orderUnit, l_blnIsPossibleFlagSet);
    }
}
@
