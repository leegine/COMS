head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadServiceImplTest_validateDetailsLine.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountDataUploadServiceImplTest_validateDetailsLine.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/08 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3AdminSrvRegiAccountDataUploadCsv;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadServiceImplTest_validateDetailsLine extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadServiceImplTest_validateDetailsLine.class);

    WEB3AdminSrvRegiAccountDataUploadServiceImpl csv = new WEB3AdminSrvRegiAccountDataUploadServiceImpl();

    WEB3AdminSrvRegiAccountDataUploadCsv accountDataUploadCsv =new WEB3AdminSrvRegiAccountDataUploadCsvForTest();

    String[] detailsLines = {"1", "2"};

    private String methosName;
    WEB3Administrator admin;

    public WEB3AdminSrvRegiAccountDataUploadServiceImplTest_validateDetailsLine(String arg0)
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

    public void testValidateDetailsLine01()
    {
        final String STR_METHOD_NAME = "testValidateDetailsLine01()";
        methosName = STR_METHOD_NAME;
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            admin = new WEB3Administrator(33381330001L);
            csv.validateDetailsLine(accountDataUploadCsv, detailsLines, admin);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01028, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateDetailsLine02()
    {
        final String STR_METHOD_NAME = "testValidateDetailsLine02()";
        methosName = STR_METHOD_NAME;
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_administratorParams.getRowType());
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(l_administratorTypeParams.getRowType());
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(l_srvRegiMasterParams.getRowType());
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(l_srvRegiSetupParams.getRowType());
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            admin = new WEB3Administrator(33381330001L);
            csv.validateDetailsLine(accountDataUploadCsv, detailsLines, admin);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01029, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateDetailsLine03()
    {
        final String STR_METHOD_NAME = "testValidateDetailsLine03()";
        methosName = STR_METHOD_NAME;
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getServiceRegist",
            new Class[]{String.class, String.class, String.class, String.class, long.class, boolean.class},
            new WEB3GentradeSrvRegiApplication(new SrvRegiApplicationParams()));
        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_administratorParams.getRowType());
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(l_administratorTypeParams.getRowType());
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(l_srvRegiMasterParams.getRowType());
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(l_srvRegiSetupParams.getRowType());
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            admin = new WEB3Administrator(33381330001L);
            String l_strResult = csv.validateDetailsLine(accountDataUploadCsv, detailsLines, admin);
            assertEquals(l_strResult, null);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateDetailsLine04()
    {
        final String STR_METHOD_NAME = "testValidateDetailsLine04()";
        methosName = STR_METHOD_NAME;
        log.entering(TEST_START + STR_METHOD_NAME);


        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceRegist",
                new Class[]{String.class, String.class, String.class, String.class, long.class, boolean.class},
                new WEB3GentradeSrvRegiApplication(new SrvRegiApplicationParams()));

            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_administratorParams.getRowType());
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(l_administratorTypeParams.getRowType());
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(l_srvRegiMasterParams.getRowType());
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(l_srvRegiSetupParams.getRowType());
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            admin = new WEB3Administrator(33381330001L);
            csv.validateDetailsLine(accountDataUploadCsv, detailsLines, admin);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03019);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateDetailsLine05()
    {
        final String STR_METHOD_NAME = "testValidateDetailsLine05()";
        methosName = STR_METHOD_NAME;
        log.entering(TEST_START + STR_METHOD_NAME);


        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceRegist",
                new Class[]{String.class, String.class, String.class, String.class, long.class, boolean.class},
                new WEB3GentradeSrvRegiApplication(new SrvRegiApplicationParams()));

            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_administratorParams.getRowType());
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(l_administratorTypeParams.getRowType());
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(l_srvRegiMasterParams.getRowType());
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(l_srvRegiSetupParams.getRowType());
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1234");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("1111111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            admin = new WEB3Administrator(33381330001L);
            csv.validateDetailsLine(accountDataUploadCsv, detailsLines, admin);

            csv.validateDetailsLine(accountDataUploadCsv, new String[]{"1", "2", "3"}, admin);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03029);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateDetailsLine06()
    {
        final String STR_METHOD_NAME = "testValidateDetailsLine06()";
        methosName = STR_METHOD_NAME;
        log.entering(TEST_START + STR_METHOD_NAME);


        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceRegist",
                new Class[]{String.class, String.class, String.class, String.class, long.class, boolean.class},
                new WEB3GentradeSrvRegiApplication(new SrvRegiApplicationParams()));

            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_administratorParams.getRowType());
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(l_administratorTypeParams.getRowType());
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(l_srvRegiMasterParams.getRowType());
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(l_srvRegiSetupParams.getRowType());
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            admin = new WEB3Administrator(33381330001L);
            csv.validateDetailsLine(accountDataUploadCsv, detailsLines, admin);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03019);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateDetailsLine07()
    {
        final String STR_METHOD_NAME = "testValidateDetailsLine07()";
        methosName = STR_METHOD_NAME;
        log.entering(TEST_START + STR_METHOD_NAME);


        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceRegist",
                new Class[]{String.class, String.class, String.class, String.class, long.class, boolean.class},
                new WEB3GentradeSrvRegiApplication(new SrvRegiApplicationParams()));

            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_administratorParams.getRowType());
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(l_administratorTypeParams.getRowType());
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(l_mainAccountParams.getRowType());
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(l_srvRegiMasterParams.getRowType());
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(l_srvRegiSetupParams.getRowType());
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1234");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("2512246");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            admin = new WEB3Administrator(33381330001L);
            csv.validateDetailsLine(accountDataUploadCsv, detailsLines, admin);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    class WEB3AdminSrvRegiAccountDataUploadCsvForTest extends WEB3AdminSrvRegiAccountDataUploadCsv
    {
        public int addRow(String l_rowString)
        {
            return 0;
        }

        public void validateDispensableItem(int l_intLineNumber)
        {
            return;
        }

        public String getBranchCode(int l_intLineNumber)
        {
            return "381";
        }

        public String getUploadDiv(int l_intLineNumber)
        {
            if ("testValidateDetailsLine01()".equals(methosName)
                || "testValidateDetailsLine06()".equals(methosName)
                || "testValidateDetailsLine07()".equals(methosName))
            {
                return "1";
            }
            else if ("testValidateDetailsLine02()".equals(methosName))
            {
                return "3";
            }
            else if ("testValidateDetailsLine03()".equals(methosName))
            {
                return "2";
            }
            else if ("testValidateDetailsLine04()".equals(methosName)
                || "testValidateDetailsLine05()".equals(methosName))
            {
                return "0";
            }
            else
            {
                return "";
            }
        }

        public void validateUploadDiv(String l_strUploadDiv)
        {
            return;
        }

        public String getRegistId(int l_intLineNumber)
        {
            return "0";
        }

        public String getInstitutionCode(int l_intLineNumber)
        {
            return "0D";
        }

        public String getAccountCode(int l_intLineNumber)
        {
            return "2512246";
        }

        public void validateAccount(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode)
        {
            return;
        }

        public String getSrvDiv(int l_intLineNumber)
        {
            return "1234";
        }

        public void validateSrvDiv(String l_strInstitutionCode, String l_strSrvDiv)
        {
            return;
        }

        public Date getAppliStartDate(int l_intLineNumber)
        {
            return null;
        }

        public Date getAppliEndDate(int l_intLineNumber)
        {
            return null;
        }

        public String getPaymentDiv(int l_intLineNumber)
        {
            return "";
        }

        public void validatePaymentDiv(String l_strPaymentDiv)
        {
            return;
        }

        public Date getAppliDate(int l_intLineNumber)
        {
            return null;
        }

        public void validateTimestampSetup(
            String l_strInstitutionCode,
            String l_strSrvDiv,
            String l_strLotDiv,
            Date l_datAppliDate,
            Date l_datAppliStartDate,
            Date l_datAppliEndDate) throws WEB3BaseException
        {
            if ("testValidateDetailsLine04()".equals(methosName)
                || "testValidateDetailsLine05()".equals(methosName)
                || "testValidateDetailsLine06()".equals(methosName)
                || "testValidateDetailsLine07()".equals(methosName))
            {
            }
            else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01028,
                    this.getClass().getName());
            }
        }

        public void saveUploadError(ErrorInfo l_errorInfo)
        {
            return;
        }

        public void validatePaymentFreeRegiDate(Date l_datAppliyStartDate, Date l_datAppliyEndDate)
            throws WEB3BaseException
        {
            if ("testValidateDetailsLine04()".equals(methosName)
                || "testValidateDetailsLine05()".equals(methosName)
                || "testValidateDetailsLine06()".equals(methosName)
                || "testValidateDetailsLine07()".equals(methosName))
            {
            }
            else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01029,
                    this.getClass().getName());
            }
        }

        public Double getUseAmt(int l_intLineNumber)
        {
            return null;
        }
        public String getAppliLotDiv(int l_intLineNumber)
        {
            return "";
        }

        public void validateAppliLotDiv(String l_strUploadDiv, String l_strLotDiv, String l_strAppliLotDiv)
        {
            return;
        }
    }
}
@
