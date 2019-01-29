head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FPTUploadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FPTUploadCsvTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTUploadCsvTest.class);

    public WEB3FPTUploadCsvTest(String arg0)
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

    public void testCheckDetailLines_0001()
    {
        final String STR_METHOD_NAME = "testCheckDetailLines_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminFPTUploadConfirmRequest l_request = new WEB3AdminFPTUploadConfirmRequest();
        l_request.uploadFile = new String[10];
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("UL_DOCADMIN_DEVMANAGE_COUNT");
            l_systemPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_csv.checkDetailLines(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02418, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCheckDetailLines_0002()
    {
        final String STR_METHOD_NAME = "testCheckDetailLines_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminFPTUploadConfirmRequest l_request = new WEB3AdminFPTUploadConfirmRequest();
        l_request.uploadFile = new String[10];
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("UL_DOCADMIN_DEVMANAGE_COUNT");
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_csv.checkDetailLines(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCheckDetailLines_0003()
    {
        final String STR_METHOD_NAME = "testCheckDetailLines_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminFPTUploadConfirmRequest l_request = new WEB3AdminFPTUploadConfirmRequest();
        l_request.uploadFile = new String[501];
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            l_csv.checkDetailLines(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02418, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCheckDetailLines_0004()
    {
        final String STR_METHOD_NAME = "testCheckDetailLines_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminFPTUploadConfirmRequest l_request = new WEB3AdminFPTUploadConfirmRequest();
        l_request.uploadFile = new String[499];
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            l_csv.checkDetailLines(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateColumnHeader()
    {
        final String STR_METHOD_NAME = "testCreateColumnHeader()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv(123L);
        try
        {
            l_csv.createColumnHeader();
            assertEquals(123L, l_csv.getAdministratorUploadId());

            WEB3GentradeCsvColumnModel l_columnModelAccountCode = l_csv.getColumnModel(l_csv.accountCodeLabel);
            assertEquals(1, l_columnModelAccountCode.getColumnNumber());
            WEB3GentradeCsvColumnModel l_columnModelBranchCode = l_csv.getColumnModel(l_csv.branchCodeLabel);
            assertEquals(0, l_columnModelBranchCode.getColumnNumber());
            WEB3GentradeCsvColumnModel l_columnModelBatoProductCode = l_csv.getColumnModel(l_csv.batoProductCodeLabel);
            assertEquals(2, l_columnModelBatoProductCode.getColumnNumber());
            WEB3GentradeCsvColumnModel l_columnModelDeliveryDate = l_csv.getColumnModel(l_csv.deliveryDateLabel);
            assertEquals(3, l_columnModelDeliveryDate.getColumnNumber());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUploadFileId()
    {
        final String STR_METHOD_NAME = "testGetUploadFileId()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv(123L);
        try
        {
            String l_strUploadFileId = l_csv.getUploadFileId();
            assertEquals("金商法@交付閲覧アップロード", l_strUploadFileId);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetAccountCode()
    {
        final String STR_METHOD_NAME = "testGetAccountCode()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "381,0000000,1,20071211";
        String l_strTest1 = "382,0000001,2,20071212";
        String l_strTest2 = "383,0000002,3,20071213";
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);
            l_csv.addRow(l_strTest1);
            l_csv.addRow(l_strTest2);
            String l_strAccountCode0 = l_csv.getAccountCode(0);
            assertEquals("0000000", l_strAccountCode0);
            String l_strBranchCode0 = l_csv.getBranchCode(0);
            assertEquals("381", l_strBranchCode0);
            String l_strDeliveryDate0 = l_csv.getDeliveryDate(0);
            assertEquals("20071211", l_strDeliveryDate0);
            String l_strAccountCode1 = l_csv.getAccountCode(1);
            assertEquals("0000001", l_strAccountCode1);
            String l_strBranchCode1 = l_csv.getBranchCode(1);
            assertEquals("382", l_strBranchCode1);
            String l_strDeliveryDate1 = l_csv.getDeliveryDate(1);
            assertEquals("20071212", l_strDeliveryDate1);
            String l_strAccountCode2 = l_csv.getAccountCode(2);
            assertEquals("0000002", l_strAccountCode2);
            String l_strBranchCode2 = l_csv.getBranchCode(2);
            assertEquals("383", l_strBranchCode2);
            String l_strDeliveryDate2 = l_csv.getDeliveryDate(2);
            assertEquals("20071213", l_strDeliveryDate2);
            String l_strBatoProductCode = l_csv.getBatoProductCode(0);
            assertEquals("1", l_strBatoProductCode);
            String l_strBatoProductCode1 = l_csv.getBatoProductCode(1);
            assertEquals("2", l_strBatoProductCode1);
            String l_strBatoProductCode2 = l_csv.getBatoProductCode(2);
            assertEquals("3", l_strBatoProductCode2);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetMainAccount()
    {
        final String STR_METHOD_NAME = "testGetMainAccount()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "381,0000000,1,20071211";
        String l_strTest1 = "382,0000001,2,20071212";
        String l_strTest2 = "383,0000002,3,20071213";
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);
            l_csv.addRow(l_strTest1);
            l_csv.addRow(l_strTest2);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("0000000");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = l_csv.getMainAccount(0, "0D");
            assertEquals(l_mainAccount.getAccountCode(), "0000000");
            assertEquals(l_csv.getProductType(), ProductTypeEnum.OTHER);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * updateUploadCancel
     */
    public void testUpdateUploadCancel_case0001()
    {
        String STR_METHOD_NAME = " testUpdateUploadCancel_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv(222);

        try
        {
            l_csv.updateUploadCancel();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
        }

        try
        {
            List l_lisActualResults = new ArrayList();
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisActualResults =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);

            assertEquals(0, l_lisActualResults.size());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);

                fail();
            }
            finally
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
        }
    }

    /**
     * getUploadNewHistory
     */
    public void testUpdateUploadCancel_case0002()
    {
        String STR_METHOD_NAME = " testUpdateUploadCancel_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        AdministratorUploadParams l_AdministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_AdministratorUploadParams.setAdministratorUploadId(222);
        l_AdministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
        l_AdministratorUploadParams.setUploadFileId("口座開設申込");
        l_AdministratorUploadParams.setNote1("申込");
        l_AdministratorUploadParams.setUploadStartTimestamp(
            WEB3DateUtility.getDate("20071020", "yyyyMMdd"));

        try
        {
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_AdministratorUploadParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv(222);

        try
        {
            l_csv.updateUploadCancel();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
        }

        try
        {
            List l_lisActualResults = new ArrayList();
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisActualResults =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);

            assertEquals(1, l_lisActualResults.size());

            AdministratorUploadParams l_administratorUploadParams =
                (AdministratorUploadParams) l_lisActualResults.get(0);

            assertEquals(222L, l_administratorUploadParams.getAdministratorUploadId());
            assertNotNull(l_administratorUploadParams.getUploadEndTimestamp());
            assertEquals(0, l_administratorUploadParams.getUploadCount());
            assertEquals("中止", l_administratorUploadParams.getNote1());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);

                fail();
            }
            finally
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
        }
    }

    public void testValidateDuplicateAccount()
    {
        final String STR_METHOD_NAME = "testValidateDuplicateAccount()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "381,000000,1111,20071211";
        String l_strTest1 = "382,0000001,2222,20071212";
        String l_strTest2 = "383,0000002,3333,20071213";
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);
            l_csv.addRow(l_strTest1);
            l_csv.addRow(l_strTest2);

            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = new DocDeliveryManagementParams();
            l_docDeliveryManagementParams.setBranchCode("381");
            l_docDeliveryManagementParams.setInstitutionCode("0D");
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));
            l_docDeliveryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));
            l_docDeliveryManagementParams.setAccountCode("0000001");
            l_docDeliveryManagementParams.setAccountId(123L);
            l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.TRUE);
            l_docDeliveryManagementParams.setProductCode("1111");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20071211", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDocumentCategory("111");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);

            l_csv.validateDuplicateAccount(0, "0D", "0");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02950, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDuplicateAccount1()
    {
        final String STR_METHOD_NAME = "testValidateDuplicateAccount1()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "381,000000,1111,20071211";
        String l_strTest1 = "382,0000001,2222,20071212";
        String l_strTest2 = "383,0000002,3333,20071213";
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);
            l_csv.addRow(l_strTest1);
            l_csv.addRow(l_strTest2);

            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            l_csv.validateDuplicateAccount(0, "0D", "1");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02952, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDuplicateAccount2()
    {
        final String STR_METHOD_NAME = "testValidateDuplicateAccount2()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "381,000000,1111,20071211";
        String l_strTest1 = "383,0000002,3333,20071213";
        String l_strTest2 = "383,0000002,3333,20071213";
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);
            l_csv.addRow(l_strTest1);
            l_csv.addRow(l_strTest2);

            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = new DocDeliveryManagementParams();
            l_docDeliveryManagementParams.setBranchCode("383");
            l_docDeliveryManagementParams.setInstitutionCode("0D");
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));
            l_docDeliveryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));
            l_docDeliveryManagementParams.setAccountCode("0000002");
            l_docDeliveryManagementParams.setAccountId(123L);
            l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.TRUE);
            l_docDeliveryManagementParams.setProductCode("3333");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20071213", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDocumentCategory("333");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);

            l_csv.validateDuplicateAccount(2, "0D", "1");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00517, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDuplicateAccount3()
    {
        final String STR_METHOD_NAME = "testValidateDuplicateAccount3()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "381,000000,1111,20071211";
        String l_strTest1 = "382,0000002,2222,20071212";
        String l_strTest2 = "383,0000002,3333,20071213";
        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);
            l_csv.addRow(l_strTest1);
            l_csv.addRow(l_strTest2);

            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = new DocDeliveryManagementParams();
            l_docDeliveryManagementParams.setBranchCode("383");
            l_docDeliveryManagementParams.setInstitutionCode("0D");
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));
            l_docDeliveryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521", "yyyyMMdd"));
            l_docDeliveryManagementParams.setAccountCode("0000002");
            l_docDeliveryManagementParams.setAccountId(123L);
            l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.TRUE);
            l_docDeliveryManagementParams.setProductCode("3333");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20071213", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDocumentCategory("333");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);

            l_csv.validateDuplicateAccount(2, "0D", "1");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine()";
        log.entering(STR_METHOD_NAME);

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.validateDetailLine(0, null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine1()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine1()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = ",000000,1111,20071211";

        WEB3FPTUploadCsvTest1 l_csv = new WEB3FPTUploadCsvTest1();
        
        try
        {
            l_csv.addRow(l_strTest0);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine2()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine2()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "２,000000,1111,20071211";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine2_1()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine2_1()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "2a,000000,1111,20071211";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine3()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine3()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "3333,000000,1111,20071211";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateDetailLine3_1()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine3()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "33,000000,1111,20071211";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine4()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine4()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "333,,1111,20071211";

        WEB3FPTUploadCsvTest2 l_csv = new WEB3FPTUploadCsvTest2();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine5()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine5()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "333,５2,1111,20071211";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine6()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine6()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "333,22,1111,20071211";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine7()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine7()";
        log.entering(STR_METHOD_NAME);


        WEB3FPTUploadCsvTest3 l_csv = new WEB3FPTUploadCsvTest3();
        String l_strTest0 = "333,222222,1111,200712100122";
        try
        {
            l_csv.addRow(l_strTest0);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("333");
            l_mainAccountParams.setAccountCode("0000000");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02961, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine8()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine8()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "333,222222,1111,200712100122";

        WEB3FPTUploadCsvTest4 l_csv = new WEB3FPTUploadCsvTest4();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("333");
            l_mainAccountParams.setAccountCode("0000001");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("333");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02995, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine9()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine9()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "333,222222,1111,20071212";

        WEB3FPTUploadCsvTest5 l_csv = new WEB3FPTUploadCsvTest5();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("333");
            l_mainAccountParams.setAccountCode("0000001");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("333");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02995, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine9_1()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine9_1()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "333,222222,1111,20071212";

        WEB3FPTUploadCsvTest6 l_csv = new WEB3FPTUploadCsvTest6();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("333");
            l_mainAccountParams.setAccountCode("0000001");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("333");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02995, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine10()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine10()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "333,222222,1111,20071212";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("333");
            l_mainAccountParams.setAccountCode("2222221");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("000");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine11()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine11()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "000,222222,1111111,20071212";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("000");
            l_mainAccountParams.setAccountCode("2222221");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("000");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02995, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine11_1()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine11_1()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "000,222222,1111111,20071212";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("000");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("000");
            l_mainAccountParams.setAccountCode("2222221");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("000");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02996, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine11_2()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine11_2()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "000,222222,1111111,20071212";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("000");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);

            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("000");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(0000000);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("000");
            l_mainAccountParams.setAccountCode("2222221");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("000");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine12()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine12()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "000,222222,1111,20071212";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("000");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDetailLine13()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine13()";
        log.entering(STR_METHOD_NAME);

        String l_strTest0 = "000,222222,1111,2007121b";

        WEB3FPTUploadCsv l_csv = new WEB3FPTUploadCsv();

        try
        {
            l_csv.addRow(l_strTest0);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("000");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            l_csv.validateDetailLine(0, l_administrator);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02414, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    public class WEB3FPTUploadCsvTest1 extends WEB3FPTUploadCsv
    {
        public String getBranchCode(int i)
        {
            return null;
        }
        public String getAccountCode(int l_intLineNo)
        {
            return null;
        }
        public String getDeliveryDate(int l_intLineNo)
        {
            return null;
        }

    }
    public class WEB3FPTUploadCsvTest2 extends WEB3FPTUploadCsv
    {
        public String getBranchCode(int i)
        {
            return "333";
        }
        public String getAccountCode(int l_intLineNo)
        {
            return null;
        }
        public String getDeliveryDate(int l_intLineNo)
        {
            return null;
        }
    }
    public class WEB3FPTUploadCsvTest3 extends WEB3FPTUploadCsv
    {
        public String getBranchCode(int i)
        {
            return "333";
        }
        public String getAccountCode(int l_intLineNo)
        {
            return "000000";
        }
        public String getDeliveryDate(int l_intLineNo)
        {
            return null;
        }
    }
    public class WEB3FPTUploadCsvTest4 extends WEB3FPTUploadCsv
    {
        public String getBranchCode(int i)
        {
            return "333";
        }
        public String getAccountCode(int l_intLineNo)
        {
            return "000000";
        }
        public String getDeliveryDate(int l_intLineNo)
        {
            return "20080101";
        }
        public String getBatoProductCode(int l_intLineNo)
        {
            return null;
        }
    }
    public class WEB3FPTUploadCsvTest5 extends WEB3FPTUploadCsv
    {
        public String getBranchCode(int i)
        {
            return "333";
        }
        public String getAccountCode(int l_intLineNo)
        {
            return "000000";
        }
        public String getDeliveryDate(int l_intLineNo)
        {
            return "20080101";
        }
        public String getBatoProductCode(int l_intLineNo)
        {
            return "a";
        }
    }
    public class WEB3FPTUploadCsvTest6 extends WEB3FPTUploadCsv
    {
        public String getBranchCode(int i)
        {
            return "333";
        }
        public String getAccountCode(int l_intLineNo)
        {
            return "000000";
        }
        public String getDeliveryDate(int l_intLineNo)
        {
            return "20080101";
        }
        public String getBatoProductCode(int l_intLineNo)
        {
            return "1";
        }
    }
}
@
