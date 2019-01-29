head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoFinTransactionManagerImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OPトランザクションマネージャクラスForMock(WEB3IfoFinTransactionManagerImplForMock.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/02 徐宏偉 (中訊) 新規作成
 */
package webbroker3.ifo;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OPトランザクションマネージャクラスForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoFinTransactionManagerImplForMock extends WEB3IfoFinTransactionManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoFinTransactionManagerImplForMock.class);

    public List getTransactions(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
                "getTransactions", new Class[]
                { OrderUnit.class }, new Object[]
                { l_orderUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
                "getTransactions", new Class[]
                { OrderUnit.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoFinTransactionManagerImplForMock.getTransactions()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
                    "getTransactions", new Class[]
                    { OrderUnit.class }).asWEB3BaseException();
            return (List) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl", "getTransactions", new Class[]
                    { OrderUnit.class }).asObject();
        }
        return super.getTransactions(l_orderUnit);
    }
    
    public List getTransactions(
        long l_lngContractID,
        FinTransactionCateg l_transactionCategory,
        Date l_datOccurDate)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
                "getTransactions", new Class[]
                {long.class, FinTransactionCateg.class, Date.class}, new Object[]
                {new Long(l_lngContractID), l_transactionCategory, l_datOccurDate});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
                "getTransactions", new Class[]
                {long.class, FinTransactionCateg.class, Date.class}))
        {
            log.debug("webbroker3.ifo.WEB3IfoFinTransactionManagerImplForMock.getTransactions()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
                    "getTransactions", new Class[]
                    {long.class, FinTransactionCateg.class, Date.class}).asWEB3BaseException();
            return (List) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl", "getTransactions", new Class[]
                    {long.class, FinTransactionCateg.class, Date.class}).asObject();
        }
        return super.getTransactions(l_lngContractID, l_transactionCategory, l_datOccurDate);
    }
    
    public double getNetAmount(OrderUnit l_orderUnit)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
            "getNetAmount",
            new Class[]{OrderUnit.class},
            new Object[]{l_orderUnit});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
            "getNetAmount",
            new Class[]{OrderUnit.class}))
        {
            log.debug("webbroker3.ifo.WEB3IfoFinTransactionManagerImplForMock.getNetAmount()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
                "getNetAmount",
                new Class[]{OrderUnit.class}).asDouble();
        }
        return super.getNetAmount(l_orderUnit);
    }

}
@
