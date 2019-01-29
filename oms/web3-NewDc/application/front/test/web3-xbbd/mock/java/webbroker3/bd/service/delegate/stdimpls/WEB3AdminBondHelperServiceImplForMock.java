head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondHelperServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AdminBondHelperServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/09 金傑 (中訊) 新規作成
 */
package webbroker3.bd.service.delegate.stdimpls;

import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondHelperServiceImplForMock extends WEB3AdminBondHelperServiceImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        		WEB3AdminBondHelperServiceImplForMock.class);
    
	public String getOrderLockButtonDiv(WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
                "getOrderLockButtonDiv",
                new Class[] {WEB3BondOrderUnit.class},
                new Object[]{l_orderUnit});
		if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
                "getOrderLockButtonDiv",
                new Class[] {WEB3BondOrderUnit.class})){
			
			log.debug("webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl.getOrderLockButtonDiv(WEB3BondOrderUnit)");

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
	                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
	                "getOrderLockButtonDiv",
	                new Class[] {WEB3BondOrderUnit.class}).asWEB3BaseException();
			
			return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
	                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
	                "getOrderLockButtonDiv",
	                new Class[] {WEB3BondOrderUnit.class}).asObject();
			
		}
		return super.getOrderLockButtonDiv(l_orderUnit);
	}
	
    public String getExecuteChangButtonDiv(WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
                "getExecuteChangButtonDiv",
                new Class[] {WEB3BondOrderUnit.class},
                new Object[]{l_orderUnit});
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl", "getOrderLockButtonDiv",
				new Class[] { WEB3BondOrderUnit.class }))
		{
			log.debug("webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl.getExecuteChangButtonDiv(WEB3BondOrderUnit)");
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
	                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
	                "getExecuteChangButtonDiv",
	                new Class[] {WEB3BondOrderUnit.class}).asWEB3BaseException();
			
			return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
	                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
	                "getExecuteChangButtonDiv",
	                new Class[] {WEB3BondOrderUnit.class}).asObject();
		}
		
    	return super.getExecuteChangButtonDiv(l_orderUnit);
	}
    
    public String getCancelButtonDiv(WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
                "getCancelButtonDiv",
                new Class[] {WEB3BondOrderUnit.class},
                new Object[]{l_orderUnit});
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl", "getCancelButtonDiv",
				new Class[] { WEB3BondOrderUnit.class }))
		{
			log.debug("webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl.getCancelButtonDiv(WEB3BondOrderUnit)");
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
	                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
	                "getCancelButtonDiv",
	                new Class[] {WEB3BondOrderUnit.class}).asWEB3BaseException();
			
			return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
	                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl",
	                "getCancelButtonDiv",
	                new Class[] {WEB3BondOrderUnit.class}).asObject();
		}
    	return super.getCancelButtonDiv(l_orderUnit);
	}
}
@
