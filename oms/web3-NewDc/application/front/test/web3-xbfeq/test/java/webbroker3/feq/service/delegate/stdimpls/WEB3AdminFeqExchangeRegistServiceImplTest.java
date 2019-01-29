head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqExchangeRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

import webbroker3.feq.message.WEB3AdminFeqExchangeRegistCompleteRequest;
import webbroker3.feq.message.WEB3FeqExchangeUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqExchangeRegistServiceImplTest extends JunitTestBase
{
     /**
     * （ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqExchangeRegistServiceImplTest.class);
    WEB3AdminFeqExchangeRegistServiceImpl l_impl;
    public WEB3AdminFeqExchangeRegistServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_impl = new WEB3AdminFeqExchangeRegistServiceImpl();
        WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testSaveProcessManagement_Case001()
    {
        final String STR_METHOD_NAME = "testSaveProcessManagement_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //ProcessManagementParams
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution =
                 new WEB3GentradeInstitution(l_institutionParams.getInstitutionCode());
            
            Method l_method =
                WEB3AdminFeqExchangeRegistServiceImpl.class.getDeclaredMethod(
                    "saveProcessManagement",
                    new Class[]{Institution.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_institution});
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(ProcessManagementRow.TYPE);
            assertEquals(l_lisResult.size(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSaveProcessManagement_Case002()
    {
        final String STR_METHOD_NAME = "testSaveProcessManagement_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //ProcessManagementParams
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution =
                 new WEB3GentradeInstitution(l_institutionParams.getInstitutionCode());
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionId(l_institution.getInstitutionId());
            l_branchParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_queryProcessor.doInsertQuery(l_branchParams);

            Method l_method =
                WEB3AdminFeqExchangeRegistServiceImpl.class.getDeclaredMethod(
                    "saveProcessManagement",
                    new Class[]{Institution.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_institution});

            List l_lisResult = l_queryProcessor.doFindAllQuery(ProcessManagementRow.TYPE);
            assertEquals(l_lisResult.size(), 1);
            ProcessManagementRow l_ProcessManagementRow =
                (ProcessManagementRow)l_lisResult.get(0);
            assertEquals("0006",l_ProcessManagementRow.getProcessId());
            assertEquals("0D",l_ProcessManagementRow.getInstitutionCode());
            assertEquals("624",l_ProcessManagementRow.getBranchCode());
            assertEquals("1",l_ProcessManagementRow.getStatus());
            assertEquals("retimed_by_ap",l_ProcessManagementRow.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_ProcessManagementRow.getLastUpdatedTimestamp(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSaveProcessManagement_Case003()
    {
        final String STR_METHOD_NAME = "testSaveProcessManagement_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //ProcessManagementParams
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution =
                 new WEB3GentradeInstitution(l_institutionParams.getInstitutionCode());
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionId(l_institution.getInstitutionId());
            l_branchParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_branchParams.setBranchId(3382);
            l_branchParams.setBranchCode("625");
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_branchParams.setBranchId(3383);
            l_branchParams.setBranchCode("626");
            TestDBUtility.insertWithDel(l_branchParams);
            
            Method l_method =
                WEB3AdminFeqExchangeRegistServiceImpl.class.getDeclaredMethod(
                    "saveProcessManagement",
                    new Class[]{Institution.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_institution});
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(
                ProcessManagementRow.TYPE,
                " process_id = ? and institution_code = ? ",
                " branch_code asc",
                null,
                new Object[]{"0006", "0D"});
            assertEquals(l_lisResult.size(), 3);
            //1111111111111111
            ProcessManagementRow l_ProcessManagementRow0 =
                (ProcessManagementRow)l_lisResult.get(0);
            assertEquals("0006",l_ProcessManagementRow0.getProcessId());
            assertEquals("0D",l_ProcessManagementRow0.getInstitutionCode());
            assertEquals("624",l_ProcessManagementRow0.getBranchCode());
            assertEquals("1",l_ProcessManagementRow0.getStatus());
            assertEquals("retimed_by_ap",l_ProcessManagementRow0.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_ProcessManagementRow0.getLastUpdatedTimestamp(), "yyyyMMdd"));
            
            //222222222222
            ProcessManagementRow l_ProcessManagementRow1 =
                (ProcessManagementRow)l_lisResult.get(1);
            assertEquals("0006",l_ProcessManagementRow1.getProcessId());
            assertEquals("0D",l_ProcessManagementRow1.getInstitutionCode());
            assertEquals("625",l_ProcessManagementRow1.getBranchCode());
            assertEquals("1",l_ProcessManagementRow1.getStatus());
            assertEquals("retimed_by_ap",l_ProcessManagementRow1.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_ProcessManagementRow1.getLastUpdatedTimestamp(), "yyyyMMdd"));

            //333333333
            ProcessManagementRow l_ProcessManagementRow2 =
                (ProcessManagementRow)l_lisResult.get(2);
            assertEquals("0006",l_ProcessManagementRow2.getProcessId());
            assertEquals("0D",l_ProcessManagementRow2.getInstitutionCode());
            assertEquals("626",l_ProcessManagementRow2.getBranchCode());
            assertEquals("1",l_ProcessManagementRow2.getStatus());
            assertEquals("retimed_by_ap",l_ProcessManagementRow2.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_ProcessManagementRow2.getLastUpdatedTimestamp(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSaveProcessManagement_Case004()
    {
        final String STR_METHOD_NAME = "testSaveProcessManagement_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //ProcessManagementParams
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution =
                 new WEB3GentradeInstitution(l_institutionParams.getInstitutionCode());
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_processManagementParams =
                TestDBUtility.getProcessManagementParams();
            l_processManagementParams.setProcessId("0006");
            l_processManagementParams.setInstitutionCode(l_institution.getInstitutionCode());
            l_processManagementParams.setStatus("0");
            l_processManagementParams.setLastUpdater("jiddk");
            TestDBUtility.insertWithDel(l_processManagementParams);
            
            Method l_method =
                WEB3AdminFeqExchangeRegistServiceImpl.class.getDeclaredMethod(
                    "saveProcessManagement",
                    new Class[]{Institution.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_institution});
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(ProcessManagementRow.TYPE);
            assertEquals(l_lisResult.size(), 1);
            ProcessManagementRow l_processManagementRow = (ProcessManagementRow)l_lisResult.get(0);
            assertEquals("retimed_by_ap", l_processManagementRow.getLastUpdater());
            assertEquals("0", l_processManagementRow.getStatus());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_processManagementRow.getLastUpdatedTimestamp(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSaveProcessManagement_Case005()
    {
        final String STR_METHOD_NAME = "testSaveProcessManagement_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution =
                 new WEB3GentradeInstitution(l_institutionParams.getInstitutionCode());
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_processManagementParams =
                TestDBUtility.getProcessManagementParams();
            l_processManagementParams.setProcessId("0006");
            l_processManagementParams.setInstitutionCode(l_institution.getInstitutionCode());
            l_processManagementParams.setStatus("0");
            l_processManagementParams.setLastUpdater("jiddk");
            l_processManagementParams.setBranchCode("624");
            l_queryProcessor.doInsertQuery(l_processManagementParams);
            
            l_processManagementParams.setBranchCode("625");
            l_queryProcessor.doInsertQuery(l_processManagementParams);
            
            l_processManagementParams.setBranchCode("626");
            l_queryProcessor.doInsertQuery(l_processManagementParams);
            
            Method l_method =
                WEB3AdminFeqExchangeRegistServiceImpl.class.getDeclaredMethod(
                    "saveProcessManagement",
                    new Class[]{Institution.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_institution});
            
            List l_lisResult = l_queryProcessor.doFindAllQuery(
                ProcessManagementRow.TYPE,
                " process_id = ? and institution_code = ? ",
                " branch_code asc",
                null,
                new Object[]{"0006", "0D"});
            assertEquals(l_lisResult.size(), 3);
            //1111111111111111
            ProcessManagementRow l_ProcessManagementRow0 =
                (ProcessManagementRow)l_lisResult.get(0);
            assertEquals("0006",l_ProcessManagementRow0.getProcessId());
            assertEquals("0D",l_ProcessManagementRow0.getInstitutionCode());
            assertEquals("624",l_ProcessManagementRow0.getBranchCode());
            assertEquals("0",l_ProcessManagementRow0.getStatus());
            assertEquals("retimed_by_ap",l_ProcessManagementRow0.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_ProcessManagementRow0.getLastUpdatedTimestamp(), "yyyyMMdd"));
            
            //222222222222
            ProcessManagementRow l_ProcessManagementRow1 =
                (ProcessManagementRow)l_lisResult.get(1);
            assertEquals("0006",l_ProcessManagementRow1.getProcessId());
            assertEquals("0D",l_ProcessManagementRow1.getInstitutionCode());
            assertEquals("625",l_ProcessManagementRow1.getBranchCode());
            assertEquals("0",l_ProcessManagementRow1.getStatus());
            assertEquals("retimed_by_ap",l_ProcessManagementRow1.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_ProcessManagementRow1.getLastUpdatedTimestamp(), "yyyyMMdd"));

            //333333333
            ProcessManagementRow l_ProcessManagementRow2 =
                (ProcessManagementRow)l_lisResult.get(2);
            assertEquals("0006",l_ProcessManagementRow2.getProcessId());
            assertEquals("0D",l_ProcessManagementRow2.getInstitutionCode());
            assertEquals("626",l_ProcessManagementRow2.getBranchCode());
            assertEquals("0",l_ProcessManagementRow2.getStatus());
            assertEquals("retimed_by_ap",l_ProcessManagementRow2.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_ProcessManagementRow2.getLastUpdatedTimestamp(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitRateRegist_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitRateRegist_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFeqExchangeRegistCompleteRequest l_request =
                new WEB3AdminFeqExchangeRegistCompleteRequest();
            WEB3FeqExchangeUnit[] exchangeList =
                new WEB3FeqExchangeUnit[1];
            exchangeList[0] = new WEB3FeqExchangeUnit();
            exchangeList[0].buyExchangeRate = "0.5";
            exchangeList[0].sellExchangeRate = "0.5";
            exchangeList[0].currencyCode = "101";
            exchangeList[0].rateDiv = "1";
            l_request.exchangeList = exchangeList;
            l_request.password = "123456";
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                "C0401",
                true,
                true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);
            //ProcessManagementParams
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            
            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_currencyParams.setCurrencyCode("101");
            TestDBUtility.insertWithDel(l_currencyParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            l_impl.submitRateRegist(l_request);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(ProcessManagementRow.TYPE);
            assertEquals(l_lisResult.size(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitRateRegist_Case002()
    {
        final String STR_METHOD_NAME = "testSubmitRateRegist_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFeqExchangeRegistCompleteRequest l_request =
                new WEB3AdminFeqExchangeRegistCompleteRequest();
            WEB3FeqExchangeUnit[] exchangeList =
                new WEB3FeqExchangeUnit[1];
            exchangeList[0] = new WEB3FeqExchangeUnit();
            exchangeList[0].buyExchangeRate = "0.5";
            exchangeList[0].sellExchangeRate = "0.5";
            exchangeList[0].currencyCode = "101";
            exchangeList[0].rateDiv = "1";
            l_request.exchangeList = exchangeList;
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                "C0401",
                true,
                true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //ProcessManagementParams
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3GentradeInstitution l_institution =
                 new WEB3GentradeInstitution(l_institutionParams.getInstitutionCode());
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionId(l_institution.getInstitutionId());
            l_branchParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_queryProcessor.doInsertQuery(l_branchParams);

            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_currencyParams.setCurrencyCode("101");
            TestDBUtility.insertWithDel(l_currencyParams);
            
            l_impl.submitRateRegist(l_request);
            List l_lisResult = l_queryProcessor.doFindAllQuery(ProcessManagementRow.TYPE);
            assertEquals(l_lisResult.size(), 1);
            ProcessManagementRow l_ProcessManagementRow =
                (ProcessManagementRow)l_lisResult.get(0);
            assertEquals("0006",l_ProcessManagementRow.getProcessId());
            assertEquals("0D",l_ProcessManagementRow.getInstitutionCode());
            assertEquals("624",l_ProcessManagementRow.getBranchCode());
            assertEquals("1",l_ProcessManagementRow.getStatus());
            assertEquals("retimed_by_ap",l_ProcessManagementRow.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_ProcessManagementRow.getLastUpdatedTimestamp(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
