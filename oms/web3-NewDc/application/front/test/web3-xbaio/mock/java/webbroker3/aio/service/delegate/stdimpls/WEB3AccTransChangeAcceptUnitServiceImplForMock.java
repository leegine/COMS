head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeAcceptUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AccTransChangeAcceptUnitServiceImplForMock.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/09 武波 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeAcceptUnitServiceImplForMock extends WEB3AccTransChangeAcceptUnitServiceImpl
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeAcceptUnitServiceImplForMock.class);

    /**
     * 
     */
    public void execute(
        AioOrderUnit l_orderUnit,
        String l_strErrorCode,
        String l_strAcceptDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(AioOrderUnit, String, String)";
        log.entering(STR_METHOD_NAME);

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
            "execute",  new Class[] {AioOrderUnit.class, String.class, String.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
                "execute",
                new Class[] {AioOrderUnit.class, String.class, String.class}).asWEB3BaseException();

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
                "execute",
                new Class[] {AioOrderUnit.class, String.class, String.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.execute(l_orderUnit, l_strErrorCode, l_strAcceptDiv);
    }
}
@
