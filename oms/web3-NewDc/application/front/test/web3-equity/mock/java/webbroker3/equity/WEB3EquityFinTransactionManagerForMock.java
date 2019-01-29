head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityFinTransactionManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityFinTransactionManagerForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/01/16  金傑　@(中訊) 新規作成
 */
package webbroker3.equity;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityFinTransactionManagerForMock extends WEB3EquityFinTransactionManager
{
	/**
	 * ログ出力ユーティリティ。<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityFinTransactionManagerForMock.class);

	public String getEstimatedProfitLoss(List l_lisTransactions) throws WEB3BaseException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityFinTransactionManager",
            "getEstimatedProfitLoss",
            new Class[] {List.class},
            new Object[]{l_lisTransactions});

		if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityFinTransactionManager",
				"getEstimatedProfitLoss", new Class[] { List.class }))
		{
			log.debug("webbroker3.equity.WEB3EquityFinTransactionManagerForMock.getEstimatedProfitLoss(List)");
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityFinTransactionManager", "getEstimatedProfitLoss",
					new Class[] { List.class }).asWEB3BaseException();
			
			return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityFinTransactionManager", "getEstimatedProfitLoss",
					new Class[] { List.class }).asObject();
		}
		return super.getEstimatedProfitLoss(l_lisTransactions);
	}
	
    public List getTransactions(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityFinTransactionManager",
            "getTransactions",
            new Class[] {EqTypeOrderUnit.class},
            new Object[]{l_orderUnit});

		if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityFinTransactionManager",
				"getTransactions", new Class[] { EqTypeOrderUnit.class }))
		{
			log.debug("webbroker3.equity.WEB3EquityFinTransactionManagerForMock.getTransactions(EqTypeOrderUnit)");
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityFinTransactionManager", "getTransactions",
					new Class[] { EqTypeOrderUnit.class }).asWEB3BaseException();
			
			return (List) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.equity.WEB3EquityFinTransactionManager", "getTransactions",
					new Class[] { EqTypeOrderUnit.class }).asObject();
		}
    	return super.getTransactions(l_orderUnit);
    }
}
@
