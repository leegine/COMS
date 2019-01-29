head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyCommonServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MutualFixedBuyCommonServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/10/26 金傑（中訊）新規作成
 */
package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyCommonServiceImplForMock extends WEB3MutualFixedBuyCommonServiceImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MutualFixedBuyCommonServiceImplForMock.class);

    public void validateFixedBuyAmount(SubAccount l_subAccount, String l_strMonthlyBuyAmount,
            String l_strIncreaseBuyAmount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "validateFixedBuyAmount", new Class[]
                {SubAccount.class, String.class, String.class}, new Object[]
                {l_subAccount, l_strMonthlyBuyAmount, l_strIncreaseBuyAmount});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "validateFixedBuyAmount", new Class[]
                {SubAccount.class, String.class, String.class}))
        {
            log.debug("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImplForMock.validateFixedBuyAmount()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", new Class[]
                    {SubAccount.class, String.class, String.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", new Class[]
                    {SubAccount.class, String.class, String.class}).asVoid();
            return;
        }
        super.validateFixedBuyAmount(l_subAccount, l_strMonthlyBuyAmount, l_strIncreaseBuyAmount);
    }
    
    public void validateDrawAccountRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "validateDrawAccountRegist", new Class[]
                {String.class, String.class, String.class}, new Object[]
                {l_strInstitutionCode, l_strBranchCode, l_strAccountCode});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "validateDrawAccountRegist", new Class[]
                {String.class, String.class, String.class}))
        {
            log.debug("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImplForMock.validateDrawAccountRegist()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", new Class[]
                    {String.class, String.class, String.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateDrawAccountRegist", new Class[]
                    {String.class, String.class, String.class}).asVoid();
            return;
        }
        super.validateDrawAccountRegist(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
    }
// <<<<<<< WEB3MutualFixedBuyCommonServiceImplForMock.java
    
 public Date calcValidStartDateOrderBizdate(String l_strInstitutionCode, String l_strBranchCode)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateOrderBizdate", new Class[]
                {String.class, String.class}, new Object[]
                {l_strInstitutionCode, l_strBranchCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateOrderBizdate", new Class[]
                {String.class, String.class}))
        {
            log.debug("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImplForMock.calcValidStartDateOrderBizdate()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateOrderBizdate", new Class[]
                    {String.class, String.class}).asWEB3BaseException();
            return (Date) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "calcValidStartDateOrderBizdate", new Class[]
                    {String.class, String.class}).asObject();
        }
        return super.calcValidStartDateOrderBizdate(l_strInstitutionCode, l_strBranchCode);
    }
    
    public Date calcValidStartDateCurrentDate(String l_strInstitutionCode, String l_strBranchCode)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateCurrentDate", new Class[]
                {String.class, String.class}, new Object[]
                {l_strInstitutionCode, l_strBranchCode});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "calcValidStartDateCurrentDate", new Class[]
                {String.class, String.class}))
        {
            log.debug("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImplForMock.calcValidStartDateCurrentDate()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "calcValidStartDateCurrentDate", new Class[]
                    {String.class, String.class}).asWEB3BaseException();
            return (Date) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "calcValidStartDateCurrentDate", new Class[]
                    {String.class, String.class}).asObject();
        }
        return super.calcValidStartDateCurrentDate(l_strInstitutionCode, l_strBranchCode);
    }
//=======

//    public Date calcValidStartDateCurrentDate(String l_strInstitutionCode, String l_strBranchCode)
//            throws WEB3BaseException
//    {
//        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "calcValidStartDateCurrentDate", new Class[]
//                {String.class, String.class}, new Object[]
//                {l_strInstitutionCode, l_strBranchCode});
//        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                "calcValidStartDateCurrentDate", new Class[]
//                {String.class, String.class}))
//        {
//            log.debug("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImplForMock.calcValidStartDateCurrentDate()");
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
//                    "calcValidStartDateCurrentDate", new Class[]
//                    {String.class, String.class}).asWEB3BaseException();
//            return (Date) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
//                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "calcValidStartDateCurrentDate", new Class[]
//                    {String.class, String.class}).asObject();
//        }
//        return super.calcValidStartDateCurrentDate(l_strInstitutionCode, l_strBranchCode);
//    }

    public boolean isPrizeAndMonth(String l_strInstitutionCode, Date l_datSelectMY) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "isPrizeAndMonth", new Class[]
                {String.class, Date.class}, new Object[]
                {l_strInstitutionCode, l_datSelectMY});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "isPrizeAndMonth", new Class[]
                {String.class, Date.class}))
        {
            log.debug("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImplForMock.isPrizeAndMonth()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "isPrizeAndMonth", new Class[]
                    {String.class, Date.class}).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "isPrizeAndMonth", new Class[]
                    {String.class, Date.class}).asBoolean();
        }
        return super.isPrizeAndMonth(l_strInstitutionCode, l_datSelectMY);
    }
    
    public WEB3MutualFixedBuyConditionUnit[] sortFixedBuyConditionList(
            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "sortFixedBuyConditionList", new Class[]
                {WEB3MutualFixedBuyConditionUnit[].class}, new Object[]
                {l_mutualFixedBuyConditionUnits});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                "sortFixedBuyConditionList", new Class[]
                {WEB3MutualFixedBuyConditionUnit[].class}))
        {
            log.debug("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImplForMock.sortFixedBuyConditionList()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "sortFixedBuyConditionList", new Class[]
                    {WEB3MutualFixedBuyConditionUnit[].class}).asWEB3BaseException();
            return (WEB3MutualFixedBuyConditionUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl", "sortFixedBuyConditionList", new Class[]
                    {WEB3MutualFixedBuyConditionUnit[].class}).asObject();
        }
        return super.sortFixedBuyConditionList(l_mutualFixedBuyConditionUnits);
    }
    
    
    
    
//>>>>>>> 1.3
}
@
