head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.27.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginSwapMarginAcceptUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MarginSwapMarginAcceptUnitServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/01 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginSwapMarginAcceptUnitServiceImplForMock extends WEB3MarginSwapMarginAcceptUnitServiceImpl
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptUnitServiceImplForMock.class);

    public WEB3MarginSwapMarginAcceptUnitServiceImplForMock()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void notifySwapMarginAccept(HostEqtypeSwapAcceptParams notifyExecuteCancel) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl",
                "notifySwapMarginAccept",
                new Class[] {HostEqtypeSwapAcceptParams.class},
                new Object[]{notifyExecuteCancel});

            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl",
                "notifySwapMarginAccept",
                new Class[] {HostEqtypeSwapAcceptParams.class})){
                log.debug(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImplForMock.notifySwapMarginAccept(EqTypeOrderUnit, HostEqtypeSwapAcceptParams)");
                
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl",
                        "notifySwapMarginAccept",
                        new Class[] {HostEqtypeSwapAcceptParams.class}).asWEB3BaseException();
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl",
                        "notifySwapMarginAccept",
                        new Class[] {HostEqtypeSwapAcceptParams.class}).asWEB3BaseRuntimeException();
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl",
                        "notifySwapMarginAccept",
                        new Class[] {HostEqtypeSwapAcceptParams.class}).asVoid();
                
                return;
            }
            super.notifySwapMarginAccept(notifyExecuteCancel);
    }
}
@
