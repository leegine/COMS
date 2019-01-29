head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.06.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccIfoOrderCarryOverServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccIfoOrderCarryOverServiceImplForMock.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/25 崔遠鵬 (中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccIfoOrderCarryOverServiceImplForMock
    extends WEB3ToSuccIfoOrderCarryOverServiceImpl
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoOrderCarryOverServiceImplForMock.class);
    
    public void execToSuccOrderCarryOver(
            IfoOrderUnit l_carryOverOriginalParentOrderUnit,
            IfoOrderUnit l_carryOverAfterParentOrderUnit,
            List l_lisRsvIfoOrderUnits) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImpl",
            "execToSuccOrderCarryOver", new Class[]
            {IfoOrderUnit.class, IfoOrderUnit.class, List.class}, new Object[]
            {l_carryOverOriginalParentOrderUnit, l_carryOverAfterParentOrderUnit, l_lisRsvIfoOrderUnits});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImpl",
            "execToSuccOrderCarryOver", new Class[]
            {IfoOrderUnit.class, IfoOrderUnit.class, List.class}))
        {
            log.debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImpl.execToSuccOrderCarryOver()");
            TestBaseForMock.MOCK_MANAGER
                .getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImpl",
                    "execToSuccOrderCarryOver", new Class[]
                    {IfoOrderUnit.class, IfoOrderUnit.class, List.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER
            .getMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImpl",
                "execToSuccOrderCarryOver", new Class[]
                {IfoOrderUnit.class, IfoOrderUnit.class, List.class}).asWEB3BaseRuntimeException();
            TestBaseForMock.MOCK_MANAGER
                .getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImpl",
                    "execToSuccOrderCarryOver", new Class[]
                    {IfoOrderUnit.class, IfoOrderUnit.class, List.class}).asVoid();
            return;
        }
        super.execToSuccOrderCarryOver(l_carryOverOriginalParentOrderUnit, l_carryOverAfterParentOrderUnit, l_lisRsvIfoOrderUnits);
    }
}
@
