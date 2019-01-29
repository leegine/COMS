head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.27.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderExecNotifyUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
 File Name        : WEB3EquityOrderExecNotifyUnitServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/29 ókïvéu(íÜêu) êVãKçÏê¨ ÉÇÉfÉã 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderExecNotifyUnitServiceImplForMock extends WEB3EquityOrderExecNotifyUnitServiceImpl
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyUnitServiceImplForMock.class);
    
    public WEB3EquityOrderExecNotifyUnitServiceImplForMock()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public void notifyExecute(
            EqTypeOrderUnit l_orderUnit,
            HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
            throws WEB3BaseException 
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                "notifyExecute",
                new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class},
                new Object[]{l_orderUnit, l_equityExecNotifyQueueParams});

            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                "notifyExecute",
                new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class})){
                log.debug(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImplForMock.notifyExecute(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)");
                
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                        "notifyExecute",
                        new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class}).asWEB3BaseException();
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                        "notifyExecute",
                        new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class}).asVoid();
                
                return;
            }
            super.notifyExecute(l_orderUnit, l_equityExecNotifyQueueParams);
    }

    public void notifyExecuteCancel(
            EqTypeOrderUnit l_orderUnit,
            HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                "notifyExecuteCancel",
                new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class},
                new Object[]{l_orderUnit, l_equityExecNotifyQueueParams});

            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                "notifyExecuteCancel",
                new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class})){
                log.debug(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImplForMock.notifyExecuteCancel(EqTypeOrderUnit, HostEquityOrderExecNotifyParams)");
                
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                        "notifyExecuteCancel",
                        new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class}).asWEB3BaseException();
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                        "notifyExecuteCancel",
                        new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class}).asVoid();
                
                return;
            }
            super.notifyExecute(l_orderUnit, l_equityExecNotifyQueueParams);
    }
}
@
