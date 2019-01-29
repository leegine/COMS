head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqExecutionNotifyUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FeqExecutionNotifyUnitServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/15 金傑（中訊）新規作成
*/
package webbroker3.feq.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqExecutionNotifyUnitServiceImplForMock extends WEB3FeqExecutionNotifyUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3FeqExecutionNotifyUnitServiceImplForMock.class);
    
    
    public void notifyOrder(WEB3FeqOrderUnit l_feqOrderUnit,
            HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams,
            SleRcvdQParams l_sleRcvdQParams)
            throws WEB3BaseException
   {
//        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
//                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImpl", "notifyOrder",
//                new Class[]
//                { WEB3FeqOrderUnit.class, HostFeqOrderExecNotifyParams.class, SleRcvdQParams.class }, new Object[]
//                { l_feqOrderUnit, l_hostFeqOrderExecNotifyParams, l_sleRcvdQParams });
//        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
//                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImpl", "notifyOrder",
//                new Class[]
//                { WEB3FeqOrderUnit.class, HostFeqOrderExecNotifyParams.class, SleRcvdQParams.class }))
//        {
//            log
//                    .debug("webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImplForMock.notifyOrder()");
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
//                    "webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImpl", "notifyOrder",
//                    new Class[]
//                    { WEB3FeqOrderUnit.class, HostFeqOrderExecNotifyParams.class, SleRcvdQParams.class }).asWEB3BaseException();
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
//                    "webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImpl", "notifyOrder",
//                    new Class[]
//                    { WEB3FeqOrderUnit.class, HostFeqOrderExecNotifyParams.class, SleRcvdQParams.class }).asVoid();
//            return;
//        }
//        super.notifyOrder(l_feqOrderUnit, l_hostFeqOrderExecNotifyParams, l_sleRcvdQParams);
    }
}
@
