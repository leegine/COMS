head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocDeliveryManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3FPTHistoryInfoUnit;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDeleteServiceImpl;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

public class WEB3AdminFPTDocDeliveryManagementTest extends TestBaseForMock
{

    public WEB3AdminFPTDocDeliveryManagementTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDivManagementTest.class);

    public void testInsertDocDeliveryManagementParams()
    {
        final String STR_METHOD_NAME = "testInsertDocDeliveryManagementParams()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = new DocDeliveryManagementParams();
            l_docDeliveryManagementParams.setBranchCode("123");
            l_docDeliveryManagementParams.setInstitutionCode("0D");
            l_docDeliveryManagementParams.setDocumentDiv("1");
            l_docDeliveryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));
            l_docDeliveryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));
            l_docDeliveryManagementParams.setAccountCode("1");
            l_docDeliveryManagementParams.setAccountId(123L);
            l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.TRUE);
            l_docDeliveryManagementParams.setProductCode("1");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));

            WEB3AdminFPTDocDeliveryManagement l_adminDocAdminFPTDocDeliveryManagement =
                new WEB3AdminFPTDocDeliveryManagement();
            l_adminDocAdminFPTDocDeliveryManagement.insertDocDeliveryManagementParams(l_docDeliveryManagementParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetDocDivManagementParams_0001()
    {
        final String STR_METHOD_NAME = "testGetDocDivManagementParams_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_accountId = 123;
        String l_documentDiv = "1";
        String l_productCode = "1234";
        Date l_docuDeliDate = new Date();
        try
        {
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            WEB3AdminFPTDocDeliveryManagement management = new WEB3AdminFPTDocDeliveryManagement();
            DocDeliveryManagementRow l_docDeliveryManagementRow =
                management.getDocDivManagementParams(
                    l_accountId, l_documentDiv, l_productCode, l_docuDeliDate, "1");

            assertEquals(null, l_docDeliveryManagementRow);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDocDivManagementParams_0002()
    {
        final String STR_METHOD_NAME = "testGetDocDivManagementParams_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_accountId = 12345678;
        String l_documentDiv = "1";
        String l_productCode = "N8080";
        Date l_docuDeliDate = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
        Timestamp l_docuDeliDate2 = new Timestamp(l_docuDeliDate.getTime());
        try
        {
            DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
            l_params.setAccountId(12345678L);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setDocumentDiv("1");
            l_params.setAccountCode("2512246");
            l_params.setProductCode("N8080");
            l_params.setDeliveryDate(l_docuDeliDate2);
            l_params.setDeleteFlag(BooleanEnum.FALSE);
            l_params.setLastUpdater("123456");
            l_params.setCreatedTimestamp(l_docuDeliDate2);
            l_params.setLastUpdatedTimestamp(l_docuDeliDate2);
            l_params.setDocumentCategory("1");

            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_params);

            WEB3AdminFPTDocDeliveryManagement management = new WEB3AdminFPTDocDeliveryManagement();
            DocDeliveryManagementRow l_docDeliveryManagementRow =
                management.getDocDivManagementParams(
                    l_accountId, l_documentDiv, l_productCode, l_docuDeliDate, "1");

            assertEquals(l_accountId, l_docDeliveryManagementRow.getAccountId(), 0);
            assertEquals(l_documentDiv, l_docDeliveryManagementRow.getDocumentDiv());
            assertEquals(l_productCode, l_docDeliveryManagementRow.getProductCode());
            assertEquals(l_docuDeliDate2, l_docDeliveryManagementRow.getDeliveryDate());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDocDivManagement()
    {
        final String STR_METHOD_NAME = " testGetDocDivManagement()";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            WEB3AdminFPTDeleteServiceImpl l_impl = new WEB3AdminFPTDeleteServiceImpl();

            method = WEB3AdminFPTDeleteServiceImpl.class.getDeclaredMethod(
                    "createQueryString",
                    new Class[]{});

            method.setAccessible(true);

            Object[] l_obj = {};

            String l_strQueryString = (String)method.invoke(l_impl, l_obj);

            assertEquals(l_strQueryString,
                " institution_code = ? "
                + " and branch_code = ? "
                + " and account_code like ? || '%' "
                + " and document_div = ? "
                + " and product_code = ? "
                + " and delivery_date　@=　@? "
                + " and document_category　@=　@? ");

            Method method1 = WEB3AdminFPTDeleteServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3GenRequest.class});
            method1.setAccessible(true);

            WEB3AdminFPTDeleteConfirmRequest l_request = new WEB3AdminFPTDeleteConfirmRequest();
            WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
            l_unit.branchCode = "123";
            l_unit.acceptCode = "321";
            l_unit.documentDiv = "1";
            l_unit.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.financialProductTradeDeleteRow = l_unit;
            Object[] l_obj1 = {"0D", l_request};

            Object[] l_queryDataContainers = (Object[])method1.invoke(l_impl, l_obj1);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            WEB3AdminFPTDocDeliveryManagement l_docDeliveryManagement = new WEB3AdminFPTDocDeliveryManagement();
            List l_lisDocDivManagements =
                l_docDeliveryManagement.getDocDivManagement(l_strQueryString, l_queryDataContainers);
            assertEquals(0, l_lisDocDivManagements.size());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }


    public void testGetDocDivManagement2()
    {
        final String STR_METHOD_NAME = " testGetDocDivManagement2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            Date l_docuDeliDate = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
            Timestamp l_docuDeliDate2 = new Timestamp(l_docuDeliDate.getTime());
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
            l_params.setAccountId(12345678L);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("123");
            l_params.setDocumentDiv("1");
            l_params.setAccountCode("321012");
            l_params.setProductCode("231");
            l_params.setDeliveryDate(l_docuDeliDate2);
            l_params.setDeleteFlag(BooleanEnum.FALSE);
            l_params.setLastUpdater("123456");
            l_params.setCreatedTimestamp(l_docuDeliDate2);
            l_params.setLastUpdatedTimestamp(l_docuDeliDate2);
            l_params.setDocumentCategory("1");


            TestDBUtility.insertWithDel(l_params);

            WEB3AdminFPTDeleteServiceImpl l_impl = new WEB3AdminFPTDeleteServiceImpl();

            method = WEB3AdminFPTDeleteServiceImpl.class.getDeclaredMethod(
                    "createQueryString",
                    new Class[]{});

            method.setAccessible(true);

            Object[] l_obj = {};

            String l_strQueryString = (String)method.invoke(l_impl, l_obj);

            assertEquals(l_strQueryString,
                    " institution_code = ? "
                    + " and branch_code = ? "
                    + " and account_code like ? || '%' "
                    + " and document_div = ? "
                    + " and product_code = ? "
                    + " and delivery_date　@=　@? "
                    + " and document_category　@=　@? ");

            Method method1 = WEB3AdminFPTDeleteServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3GenRequest.class});
            method1.setAccessible(true);

            WEB3AdminFPTDeleteCompleteRequest l_request = new WEB3AdminFPTDeleteCompleteRequest();
            WEB3FPTHistoryInfoUnit l_unit = new WEB3FPTHistoryInfoUnit();
            l_unit.branchCode = "123";
            l_unit.acceptCode = "321";
            l_unit.documentDiv = "1";
            l_unit.docuDeliDate = l_docuDeliDate2;
            l_unit.batoProductCode = "231";
            l_unit.documentCategory = "1";
            l_request.financialProductTradeDeleteRow = l_unit;
            Object[] l_obj1 = {"0D", l_request};

            Object[] l_queryDataContainers = (Object[])method1.invoke(l_impl, l_obj1);
            assertEquals(l_queryDataContainers[0], "0D");
            assertEquals(l_queryDataContainers[1], "123");
            assertEquals(l_queryDataContainers[2], "321");
            assertEquals(l_queryDataContainers[3], "1");
            assertEquals(l_queryDataContainers[4], "231");
            assertEquals(l_queryDataContainers[5], l_docuDeliDate2);
            assertEquals(l_queryDataContainers[6], "1");
            WEB3AdminFPTDocDeliveryManagement l_docDeliveryManagement = new WEB3AdminFPTDocDeliveryManagement();
            List l_lisDocDivManagements =
                l_docDeliveryManagement.getDocDivManagement(l_strQueryString, l_queryDataContainers);
            assertEquals(1, l_lisDocDivManagements.size());
            DocDeliveryManagementRow l_docDeliveryManagementRow =
                (DocDeliveryManagementRow)l_lisDocDivManagements.get(0);
            assertEquals(0, l_docDeliveryManagementRow.getDeleteFlag().intValue());
            assertEquals("123456", l_docDeliveryManagementRow.getLastUpdater());

            l_docDeliveryManagement.deleteDocDivManagement(12345678L, "1", "231", l_docuDeliDate2, "1");
            l_lisDocDivManagements =
                l_docDeliveryManagement.getDocDivManagement(l_strQueryString, l_queryDataContainers);
            assertEquals(0, l_lisDocDivManagements.size());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
