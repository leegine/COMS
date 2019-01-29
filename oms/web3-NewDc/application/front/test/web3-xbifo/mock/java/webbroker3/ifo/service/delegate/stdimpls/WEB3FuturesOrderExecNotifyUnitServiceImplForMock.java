head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.44.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOrderExecNotifyUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3FuturesOrderExecNotifyUnitServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/04/27 金傑 (中訊) 新規作成
 */
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3FuturesOrderExecNotifyUnitServiceImplForMock extends WEB3FuturesOrderExecNotifyUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3FuturesOrderExecNotifyUnitServiceImplForMock.class);

    public void notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity,
            double l_dblExecPrice, String l_strExecNotifyDiv) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                "notifyExecute",
                new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class,String.class},
                new Object[]{l_orderUnit,
                              l_tsExecDate,
                              new Double(l_dblExecQuantity),new Double(l_dblExecPrice),l_strExecNotifyDiv});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                "notifyExecute",
                new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class,String.class}))
        {
            log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImplForMock.notifyExecute");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                    "notifyExecute",
                    new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class,String.class}).asWEB3BaseException();
                    
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                    "notifyExecute",
                    new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class,String.class}).asVoid();
            return;
        }
        super.notifyExecute(l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecNotifyDiv);
    }
    
    public void notifyExecuteCancel(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity,
            double l_dblExecPrice) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                "notifyExecuteCancel",
                new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class},
                new Object[]{l_orderUnit,
                              l_tsExecDate,
                              new Double(l_dblExecQuantity),new Double(l_dblExecPrice)});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                "notifyExecuteCancel",
                new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class}))
        {
            log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImplForMock.notifyExecuteCancel");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                    "notifyExecuteCancel",
                    new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl",
                    "notifyExecuteCancel",
                    new Class[] {OrderUnit.class,Timestamp.class,double.class,double.class}).asVoid();
            return;
        }
        super.notifyExecuteCancel(l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice);
    }
    

}
@
