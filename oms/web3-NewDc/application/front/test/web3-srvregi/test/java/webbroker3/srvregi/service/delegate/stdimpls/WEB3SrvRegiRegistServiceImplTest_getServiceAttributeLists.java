head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiRegistServiceImplTest_getServiceAttributeLists.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/20 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiRegistServiceImplTest_getServiceAttributeLists extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3SrvRegiRegistServiceImplTest_getServiceAttributeLists.class);

    WEB3SrvRegiRegistServiceImpl impl = new WEB3SrvRegiRegistServiceImpl();

    public WEB3SrvRegiRegistServiceImplTest_getServiceAttributeLists(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetServiceAttributeLists01()
    {
        final String STR_METHOD_NAME = "testGetServiceAttributeLists01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            SrvAppliAttributeParams[] l_SrvAppliAttributeParamses = impl.getServiceAttributeLists(
                "338", null, "1234", null, "7", null, null);
            assertEquals(l_SrvAppliAttributeParamses.length, 1);
            assertEquals(l_SrvAppliAttributeParamses[0].getInstitutionCode(), "338");
            assertEquals(l_SrvAppliAttributeParamses[0].getSrvDiv(), "1234");
            assertEquals(l_SrvAppliAttributeParamses[0].getAppliAttribute(), "1");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetServiceAttributeLists02()
    {
        final String STR_METHOD_NAME = "testGetServiceAttributeLists02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setAppliAttribute("2");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            WEB3SrvRegiSortKey[] l_sortKeys = new WEB3SrvRegiSortKey[7];
            WEB3SrvRegiSortKey l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.keyItem = "branchcode";
            l_sortKey.ascDesc = "A";
            l_sortKeys[0] = l_sortKey;
            l_sortKey.keyItem = "accountCode";
            l_sortKeys[1] = l_sortKey;
            l_sortKey.keyItem = "appliAttribute";
            l_sortKeys[2] = l_sortKey;
            l_sortKey.keyItem = "trialStartDate";
            l_sortKeys[3] = l_sortKey;
            l_sortKey.keyItem = "trialEndDate";
            l_sortKeys[4] = l_sortKey;
            l_sortKey.keyItem = "lastUpdateTime";
            l_sortKeys[5] = l_sortKey;
            l_sortKey.keyItem = "lastUpdater";
            l_sortKeys[6] = l_sortKey;

            String[] l_branchCodes = new String[]{"A01"};

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppDate = new Timestamp(l_calendar.getTimeInMillis());

            SrvAppliAttributeParams[] l_SrvAppliAttributeParamses = impl.getServiceAttributeLists(
                "338", l_branchCodes, "1234", "1234567", "8", l_tsAppDate, l_sortKeys);
            assertEquals(l_SrvAppliAttributeParamses.length, 1);
            assertEquals(l_SrvAppliAttributeParamses[0].getInstitutionCode(), "338");
            assertEquals(l_SrvAppliAttributeParamses[0].getBranchCode(), "A01");
            assertEquals(l_SrvAppliAttributeParamses[0].getAccountCode(), "1234567");
            assertEquals(l_SrvAppliAttributeParamses[0].getSrvDiv(), "1234");
            assertEquals(l_SrvAppliAttributeParamses[0].getAppliAttribute(), "2");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }    

    public void testGetServiceAttributeLists03()
    {
        final String STR_METHOD_NAME = "testGetServiceAttributeLists03()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setAppliAttribute("2");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            WEB3SrvRegiSortKey[] l_sortKeys = new WEB3SrvRegiSortKey[7];
            WEB3SrvRegiSortKey l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.keyItem = "branchcode";
            l_sortKey.ascDesc = "D";
            l_sortKeys[0] = l_sortKey;
            l_sortKey.keyItem = "accountCode";
            l_sortKeys[1] = l_sortKey;
            l_sortKey.keyItem = "appliAttribute";
            l_sortKeys[2] = l_sortKey;
            l_sortKey.keyItem = "trialStartDate";
            l_sortKeys[3] = l_sortKey;
            l_sortKey.keyItem = "trialEndDate";
            l_sortKeys[4] = l_sortKey;
            l_sortKey.keyItem = "lastUpdateTime";
            l_sortKeys[5] = l_sortKey;
            l_sortKey.keyItem = "lastUpdater";
            l_sortKeys[6] = l_sortKey;

            String[] l_branchCodes = new String[]{"A01", "A02"};

            SrvAppliAttributeParams[] l_SrvAppliAttributeParamses = impl.getServiceAttributeLists(
                "338", l_branchCodes, "1234", null, "9", null, l_sortKeys);
            assertEquals(l_SrvAppliAttributeParamses.length, 1);
            assertEquals(l_SrvAppliAttributeParamses[0].getInstitutionCode(), "338");
            assertEquals(l_SrvAppliAttributeParamses[0].getBranchCode(), "A01");
            assertEquals(l_SrvAppliAttributeParamses[0].getSrvDiv(), "1234");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * proc_div asc
     *
     */
    public void testGetServiceAttributeLists04()
    {
        final String STR_METHOD_NAME = "testGetServiceAttributeLists04()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SrvAppliAttributeParams.TYPE);
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            
            SrvAppliAttributeParams l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            
            l_srvAppliAttributeParams.setAppliAttribute("2");
            l_srvAppliAttributeParams.setProcDiv("1");
            
            SrvAppliAttributeParams l_srvAppliAttributeParams1 = TestDBUtility.getSrvAppliAttributeRow();
            
            l_srvAppliAttributeParams1.setAppliAttribute("2");
            l_srvAppliAttributeParams1.setAccountCode("123456");
            l_srvAppliAttributeParams1.setProcDiv("2");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams1);

            WEB3SrvRegiSortKey[] l_sortKeys = new WEB3SrvRegiSortKey[1];
            WEB3SrvRegiSortKey l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.keyItem = "transactionDiv";
            l_sortKey.ascDesc = "A";
            l_sortKeys[0] = l_sortKey;
            

            String[] l_branchCodes = new String[]{"A01", "A02"};

            SrvAppliAttributeParams[] l_SrvAppliAttributeParamses = impl.getServiceAttributeLists(
                "338", l_branchCodes, "1234", null, "9", null, l_sortKeys);
            assertEquals(l_SrvAppliAttributeParamses.length, 2);
            assertEquals(l_SrvAppliAttributeParamses[0].getInstitutionCode(), "338");
            assertEquals(l_SrvAppliAttributeParamses[0].getBranchCode(), "A01");
            assertEquals(l_SrvAppliAttributeParamses[0].getSrvDiv(), "1234");
            assertEquals(l_SrvAppliAttributeParamses[0].getProcDiv(), "1");
            assertEquals(l_SrvAppliAttributeParamses[0].getAccountCode(), "1234567");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * proc_div des
     *
     */
    public void testGetServiceAttributeLists05()
    {
        final String STR_METHOD_NAME = "testGetServiceAttributeLists05()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SrvAppliAttributeParams.TYPE);
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            
            SrvAppliAttributeParams l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            
            l_srvAppliAttributeParams.setAppliAttribute("2");
            l_srvAppliAttributeParams.setProcDiv("1");
            
            SrvAppliAttributeParams l_srvAppliAttributeParams1 = TestDBUtility.getSrvAppliAttributeRow();
            
            l_srvAppliAttributeParams1.setAppliAttribute("2");
            l_srvAppliAttributeParams1.setAccountCode("123456");
            l_srvAppliAttributeParams1.setProcDiv("2");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams1);

            WEB3SrvRegiSortKey[] l_sortKeys = new WEB3SrvRegiSortKey[1];
            WEB3SrvRegiSortKey l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.keyItem = "transactionDiv";
            l_sortKey.ascDesc = "D";
            l_sortKeys[0] = l_sortKey;
            

            String[] l_branchCodes = new String[]{"A01", "A02"};

            SrvAppliAttributeParams[] l_SrvAppliAttributeParamses = impl.getServiceAttributeLists(
                "338", l_branchCodes, "1234", null, "9", null, l_sortKeys);
            assertEquals(l_SrvAppliAttributeParamses.length, 2);
            assertEquals(l_SrvAppliAttributeParamses[0].getInstitutionCode(), "338");
            assertEquals(l_SrvAppliAttributeParamses[0].getBranchCode(), "A01");
            assertEquals(l_SrvAppliAttributeParamses[0].getSrvDiv(), "1234");
            assertEquals(l_SrvAppliAttributeParamses[0].getProcDiv(), "2");
            assertEquals(l_SrvAppliAttributeParamses[0].getAccountCode(), "123456");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
