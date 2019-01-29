head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminPMEquityDataManagerTest_20070608.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;


import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleSortKeyUnit;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminPMEquityDataManagerTest_20070608 extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AdminPMEquityDataManagerTest_20070608.class);
    
    WEB3AdminPMEquityDataManager l_dataManager = null;
    
    public WEB3AdminPMEquityDataManagerTest_20070608(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_dataManager = new WEB3AdminPMEquityDataManager();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.checkMethodValue();
        super.tearDown();
    }

    public void testGetManualExpireOrderUnits_T01()
    {
        final String STR_METHOD_NAME = "testGetManualExpireOrderUnits_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderConditionType("0");
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
            l_eqtypeOrderUnitParams.setSonarTradedCode("1");
            l_eqtypeOrderUnitParams.setConfirmedQuantity(null);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            String l_strInstitutionCode = l_institutionParams.getInstitutionCode();
            WEB3GentradeInstitution l_instituion =
                new WEB3GentradeInstitution(l_strInstitutionCode);
            EqtypeOrderUnitRow[] l_returnRow = l_dataManager.getManualExpireOrderUnits(
                l_instituion,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
            assertNull(l_returnRow);
            log.debug(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetManualExpireOrderUnits_T02()
    {
        final String STR_METHOD_NAME = "testGetManualExpireOrderUnits_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderConditionType("0");
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
            l_eqtypeOrderUnitParams.setSonarTradedCode("1");
            l_eqtypeOrderUnitParams.setConfirmedQuantity(null);
            l_eqtypeOrderUnitParams.setApproveStatusType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            String l_strInstitutionCode = l_institutionParams.getInstitutionCode();
            WEB3GentradeInstitution l_instituion =
                new WEB3GentradeInstitution(l_strInstitutionCode);
            EqtypeOrderUnitRow[] l_returnRow = l_dataManager.getManualExpireOrderUnits(
                l_instituion,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
            assertNull(l_returnRow);
            log.debug(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetManualExpireOrderUnits_T03()
    {
        final String STR_METHOD_NAME = "testGetManualExpireOrderUnits_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderConditionType("0");
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
            l_eqtypeOrderUnitParams.setSonarTradedCode("1");
            l_eqtypeOrderUnitParams.setConfirmedQuantity(null);
            l_eqtypeOrderUnitParams.setApproveStatusType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            String l_strInstitutionCode = l_institutionParams.getInstitutionCode();
            WEB3GentradeInstitution l_instituion =
                new WEB3GentradeInstitution(l_strInstitutionCode);
            EqtypeOrderUnitRow[] l_returnRow = l_dataManager.getManualExpireOrderUnits(
                l_instituion,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
            assertNull(l_returnRow);
            log.debug(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateForcedSettleSortCondition_case01()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " margin_maintenance_rate ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "marginCollateralRate";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
