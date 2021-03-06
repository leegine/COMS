head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPCalcConditionForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : WEB3TPCalcConditionForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/15 ΰiujVKμ¬
*/
package webbroker3.tradingpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


/**
 * i]ΝvZπForMockj
 * 
 */
public class WEB3TPCalcConditionForMock extends WEB3TPCalcCondition
{

    /**
     * OoΝ[eBeBB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3TPCalcConditionForMock.class);

    /**
     * 
     * @@param l_blnIsMarginAccountEstablished true:MpΪq false:»¨Ϊq
     * @@param l_lngAccountId:ϋΐhc
     * @@param l_lngSubAccountId:βϋΐhc
     * @@param l_strPreferencesName vt@@XΌ
     * @@return
     */
    public static void mockCreateCalcConditionStandard(boolean l_blnIsMarginAccountEstablished,
        long l_lngAccountId,long l_lngSubAccountId,String l_strPreferencesKey,String l_strPreferencesValue)
    {
        final String STR_METHOD_NAME = "createCalcConditionStandard(boolean,l_lngAccountId,l_lngSubAccountId)-->ForMock";
        log.entering(STR_METHOD_NAME);
        try
        {

            deleteParams();
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            
            if(l_blnIsMarginAccountEstablished)
            {
                l_mainAccountParams.setMarginGenAccOpenDiv("1");
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            
            l_subAccountParams.setAccountId(l_lngAccountId);
            l_subAccountParams.setSubAccountId(l_lngSubAccountId);


            l_TradingpowerCalcConditionParams.setAccountId(l_lngAccountId);
            
            l_branchPreferencesParams.setName(l_strPreferencesKey);
            l_branchPreferencesParams.setValue(l_strPreferencesValue);
            l_branchPreferencesParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
           
            insertParams(
            l_mainAccountParams,
            l_subAccountParams,
            l_institutionParams,
            l_branchParams,
            l_TradingpowerCalcConditionParams,
            l_branchPreferencesParams);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 
     * @@param l_mainAccountParams:Ϊq}X^[Row
     * @@param l_subAccountParams:βϋΐiΪq¨θcjRow
     * @@param l_institutionParams:ΨοΠRow
     * @@param l_branchParams:XRow
     * @@param l_tradingpowerCalcConditionParams:]ΝvZπRow
     * @@param l_branchPreferencesParams: Xpvt@@XRow
     */
    public static void mockCreateCalcConditionStandard(
        MainAccountParams l_mainAccountParams,
        SubAccountParams l_subAccountParams,
        InstitutionParams l_institutionParams,
        BranchParams l_branchParams,
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams,
        BranchPreferencesParams l_branchPreferencesParams)
    {
        final String STR_METHOD_NAME = "createCalcConditionStandard(MainAccountParams," +
                "SubAccountParams,InstitutionParams,BranchParams,TradingpowerCalcConditionParams,BranchPreferencesParams)-->ForMock";
        log.entering(STR_METHOD_NAME);
        try
        {
            deleteParams();
            insertParams(
            l_mainAccountParams,
            l_subAccountParams,
            l_institutionParams,
            l_branchParams,
            l_tradingpowerCalcConditionParams,
            l_branchPreferencesParams);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private static void deleteParams()
    {
        final String STR_METHOD_NAME = "deleteParams()-->ForMock";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private static void insertParams(
        MainAccountParams l_mainAccountParams,
        SubAccountParams l_subAccountParams,
        InstitutionParams l_institutionParams,
        BranchParams l_branchParams,
        TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams,
        BranchPreferencesParams l_branchPreferencesParams
        )
    {
        final String STR_METHOD_NAME = "insertParams()-->ForMock";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    protected void calcBasePoint(WEB3GentradeSubAccount l_subAccount)
    {

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPCalcCondition",
                "calcBasePoint", new Class[]
                {WEB3GentradeSubAccount.class},
                new Object[]
                {l_subAccount});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPCalcCondition",
                "calcBasePoint", new Class[]
                {WEB3GentradeSubAccount.class}))
        {
            log.debug("webbroker3.tradingpower.WEB3TPCalcConditionForMock.calcBasePoint()");

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.tradingpower.WEB3TPCalcCondition",
                    "calcBasePoint", new Class[]
                    {WEB3GentradeSubAccount.class}).asVoid();
            return;
        }
        super.calcBasePoint(l_subAccount);

    }
}
@
