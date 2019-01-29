head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.44.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderExecNotifyUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3OptionOrderExecNotifyUnitServiceImplForMock.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/29 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOrderExecNotifyUnitServiceImplForMock extends WEB3OptionOrderExecNotifyUnitServiceImpl
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOrderExecNotifyUnitServiceImplForMock.class);
    
    public void notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity,
            double l_dblExecPrice, String l_strExecutedNotifyDivision) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl", "notifyExecute",
                new Class[]
                {OrderUnit.class, Timestamp.class, double.class, double.class, String.class}, new Object[]
                {l_orderUnit, l_tsExecDate, new Double(l_dblExecQuantity), new Double(l_dblExecPrice),
                        l_strExecutedNotifyDivision});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl", "notifyExecute",
                new Class[]
                {OrderUnit.class, Timestamp.class, double.class, double.class, String.class}))
        {
            log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImplForMock.notifyExecute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class}).asVoid();
            return;
        }
        super.notifyExecute(l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecutedNotifyDivision);
    }
}
@
