head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeCompleteUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AccTransChangeCompleteUnitServiceImplForMock.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/09 武波 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeCompleteUnitServiceImplForMock extends WEB3AccTransChangeCompleteUnitServiceImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeCompleteUnitServiceImplForMock.class);

    public void completeChange(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "completeChange(AioOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImpl",
            "completeChange",  new Class[] {AioOrderUnit.class}))
        {

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImpl",
                "completeChange",
                new Class[] {AioOrderUnit.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.completeChange(l_orderUnit);
    }
}
@
