head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBranchForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeBranchForMock extends WEB3GentradeBranch
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3GentradeMainAccountForMock.class);
    
    public WEB3GentradeBranchForMock(long l_lngBranchID) throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_lngBranchID);
    }

    public boolean isMarginTradeEnforcement(String l_strRepaymentDiv)
    {
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeBranch",
				"isMarginTradeEnforcement", 
				new Class[] { String.class }, 
				new Object[] { l_strRepaymentDiv});
		
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeBranch",
            "isMarginTradeEnforcement",
            new Class[] {String.class}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBranch.isMarginTradeEnforcement(String l_strRepaymentDiv)");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBranch",
                "isMarginTradeEnforcement",
                new Class[] {String.class}).asBoolean();
        }
        return super.isMarginTradeEnforcement(l_strRepaymentDiv);
    }

    public long getMarketMessageSuspension(ProductTypeEnum l_productTypeEnum,
        String l_strMarginTradeDiv,
        String l_strFutureOptionDiv)
    {
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeBranch",
				"getMarketMessageSuspension", 
				new Class[] {ProductTypeEnum.class, String.class,String.class }, 
				new Object[] {l_productTypeEnum,l_strMarginTradeDiv,l_strFutureOptionDiv});
		
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeBranch",
            "getMarketMessageSuspension",
            new Class[] {ProductTypeEnum.class,
                String.class,
                String.class}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBranch.getMarketMessageSuspension(ProductTypeEnum l_productTypeEnum, String l_strMarginTradeDiv, String l_strFutureOptionDiv)");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBranch",
                "getMarketMessageSuspension",
                new Class[] {ProductTypeEnum.class,
                    String.class,
                    String.class}).asLong();
        }
        return super.getMarketMessageSuspension(l_productTypeEnum,l_strMarginTradeDiv,l_strFutureOptionDiv);
    }
    
    public boolean isEveningSessionEnforcemented(ProductTypeEnum l_productType) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeBranch",
                "isEveningSessionEnforcemented", new Class[]
                { ProductTypeEnum.class }, new Object[]
                { l_productType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeBranch",
                "isEveningSessionEnforcemented", new Class[]
                { ProductTypeEnum.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBranchForMock.isEveningSessionEnforcemented()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "isEveningSessionEnforcemented", new Class[]
                    { ProductTypeEnum.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "isEveningSessionEnforcemented", new Class[]
                    { ProductTypeEnum.class }).asBoolean();
        }
        return super.isEveningSessionEnforcemented(l_productType);
    }
    
    public Institution getInstitution()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeBranch", "getInstitution",
                new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeBranch", "getInstitution",
                new Class[]
                {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBranchForMock.getInstitution()");
            return (Institution) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch", "getInstitution", new Class[]
                    {}).asObject();
        }
        return super.getInstitution();
    }
    
    public void validateAgeLowLimit(WEB3GentradeSubAccount l_subAccount, String l_strName, long l_lngNameSerialNo)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeBranch",
                "validateAgeLowLimit", new Class[]
                { WEB3GentradeSubAccount.class, String.class, long.class }, new Object[]
                { l_subAccount, l_strName, new Long(l_lngNameSerialNo) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeBranch", "validateAgeLowLimit",
                new Class[]
                { WEB3GentradeSubAccount.class, String.class, long.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBranchForMock.validateAgeLowLimit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "validateAgeLowLimit", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, long.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "validateAgeLowLimit", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, long.class }).asVoid();
            return;
        }
        super.validateAgeLowLimit(l_subAccount, l_strName, l_lngNameSerialNo);
    }
    
    public void validateAgeHighLimit(WEB3GentradeSubAccount l_subAccount, String l_strName, long l_lngNameSerialNo)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeBranch",
                "validateAgeHighLimit", new Class[]
                { WEB3GentradeSubAccount.class, String.class, long.class }, new Object[]
                { l_subAccount, l_strName, new Long(l_lngNameSerialNo) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeBranch", "validateAgeHighLimit",
                new Class[]
                { WEB3GentradeSubAccount.class, String.class, long.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBranchForMock.validateAgeHighLimit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "validateAgeHighLimit", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, long.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "validateAgeHighLimit", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, long.class }).asVoid();
            return;
        }
        super.validateAgeHighLimit(l_subAccount, l_strName, l_lngNameSerialNo);
    }

    public boolean isIfodepositLackchargeNonManagement() throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeBranch",
                "isIfodepositLackchargeNonManagement", new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeBranch",
                "isIfodepositLackchargeNonManagement", new Class[]
                {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBranchForMock.isIfodepositLackchargeNonManagement()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "isIfodepositLackchargeNonManagement", new Class[]
                    {}).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBranch",
                    "isIfodepositLackchargeNonManagement", new Class[]
                    {}).asBoolean();
        }
        return super.isIfodepositLackchargeNonManagement();
    }
}
@
