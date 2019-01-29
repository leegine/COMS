head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdUploadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSVテスト(WEB3AdminSrvRegiOtherOrgIdUploadCsvTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 武波 (中訊) 新規作成 モデル337,349,351,352
*/
package webbroker3.srvregi;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSVテスト)<BR>
 * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSVテスト<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadCsvTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadCsvTest.class);
    /**
     * WEB3AdminSrvRegiOtherOrgIdUploadCsvTest
     * @@param l_arg0
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCsvTest(String l_arg0)
    {
        super(l_arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    WEB3AdminSrvRegiOtherOrgIdUploadCsv l_csv =
        new WEB3AdminSrvRegiOtherOrgIdUploadCsv(new Long(1));
    /**
     *
     */
    public void testGetUploadFileId()
    {
        final String STR_METHOD_NAME = "testGetUploadFileId()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCsv l_csv =
                new WEB3AdminSrvRegiOtherOrgIdUploadCsv();
            WEB3AdminSrvRegiOtherOrgIdUploadCsv.UPLOAD_FILE_ID = "4";
            assertEquals("4", l_csv.getUploadFileId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    /**
     *
     */
    public void testGetProductType()
    {
        final String STR_METHOD_NAME = "testGetProductType()";
        log.entering(STR_METHOD_NAME);
        try
        {
            assertEquals(ProductTypeEnum.OTHER, l_csv.getProductType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testIsUploadNewRegist_case0001()
    {
        final String STR_METHOD_NAME = "testIsUploadNewRegist_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            assertTrue(l_csv.isUploadNewRegist());
            l_csv.uploadDiv = "1";
            assertTrue(!l_csv.isUploadNewRegist());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    /**
     *
     */
    public void testcreateColumnHeader_case0001()
    {
        final String STR_METHOD_NAME = "testcreateColumnHeader_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            WEB3GentradeCsvColumnModel l_columnModelSequenceNumberLabel =
                l_csv.getColumnModel(WEB3AdminSrvRegiOtherOrgIdUploadCsv.SEQUENCE_NUMBER_LABEL);
            assertEquals(0, l_columnModelSequenceNumberLabel.getColumnNumber());

            WEB3GentradeCsvColumnModel l_columnModelIdLabel =
                l_csv.getColumnModel(WEB3AdminSrvRegiOtherOrgIdUploadCsv.ID_LABEL);
            assertEquals(1, l_columnModelIdLabel.getColumnNumber());

            WEB3GentradeCsvColumnModel l_columnModelPasswordLabel =
                l_csv.getColumnModel(WEB3AdminSrvRegiOtherOrgIdUploadCsv.PASSWORD_LABEL);
            assertEquals(2, l_columnModelPasswordLabel.getColumnNumber());

            WEB3GentradeCsvColumnModel l_columnModelStatusLabel =
                l_csv.getColumnModel(WEB3AdminSrvRegiOtherOrgIdUploadCsv.STATUS_LABEL);
            assertEquals(3, l_columnModelStatusLabel.getColumnNumber());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testcreateColumnHeader_case0002()
    {
        final String STR_METHOD_NAME = "testcreateColumnHeader_case0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();
            WEB3GentradeCsvColumnModel l_columnModelSequenceNumberLabel =
                l_csv.getColumnModel(WEB3AdminSrvRegiOtherOrgIdUploadCsv.SEQUENCE_NUMBER_LABEL);
            assertEquals(0, l_columnModelSequenceNumberLabel.getColumnNumber());

            WEB3GentradeCsvColumnModel l_columnModelIdLabel =
                l_csv.getColumnModel(WEB3AdminSrvRegiOtherOrgIdUploadCsv.ID_LABEL);
            assertEquals(1, l_columnModelIdLabel.getColumnNumber());

            WEB3GentradeCsvColumnModel l_columnModelStatusLabel =
                l_csv.getColumnModel(WEB3AdminSrvRegiOtherOrgIdUploadCsv.STATUS_LABEL);
            assertEquals(2, l_columnModelStatusLabel.getColumnNumber());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testSetUploadDiv_case0001()
    {
        final String STR_METHOD_NAME = "testSetUploadDiv_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.setUploadDiv(null, "1,2,3,4");
            assertEquals("0", l_csv.getUploadDiv());
            l_csv.setUploadDiv(null, "1,2,3");
            assertEquals("1", l_csv.getUploadDiv());
            l_csv.setUploadDiv("2", null);
            assertEquals("2", l_csv.getUploadDiv());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }


    public void testGetSequenceNumber_case0001()
    {
        final String STR_METHOD_NAME = "testSetUploadDiv_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,12,1";
            l_csv.addRow(l_str);
            assertEquals(111111111111111111L, l_csv.getSequenceNumber(0));
            assertEquals("123", l_csv.getId(0));
            assertEquals("12", l_csv.getPassword(0));
            assertEquals("1", l_csv.getStatus(0));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSequenceNumber_case0002()
    {
        final String STR_METHOD_NAME = "testSetUploadDiv_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);
            assertEquals(111111111111111111L, l_csv.getSequenceNumber(0));
            assertEquals("123", l_csv.getId(0));
            assertEquals("1", l_csv.getStatus(0));
            assertEquals("0D", l_csv.getInstitutionCode(0));
            assertEquals("381", l_csv.getBranchCode(0));
            assertEquals("111111", l_csv.getAccountCode(0));
            assertEquals("20080317", WEB3DateUtility.formatDate(l_csv.getAppliStartDate(0), "yyyyMMdd"));
            assertEquals("20080318", WEB3DateUtility.formatDate(l_csv.getAppliEndDate(0), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateRecodeMatch_case0001()
    {
        final String STR_METHOD_NAME = "testValidateRecodeMatch_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,12,1";
            l_csv.addRow(l_str);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(111111111111111111L);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_csv.validateRecodeMatch(0, "1");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03027, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateRecodeMatch_case0002()
    {
        final String STR_METHOD_NAME = "testValidateRecodeMatch_case0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,12,1";
            l_csv.addRow(l_str);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            l_csv.validateRecodeMatch(0, "1");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateRecodeMatch_case0003()
    {
        final String STR_METHOD_NAME = "testValidateRecodeMatch_case0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            l_csv.validateRecodeMatch(0, "1");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateRecodeMatch_case0004()
    {
        final String STR_METHOD_NAME = "testValidateRecodeMatch_case0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(111111111111111111L);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_csv.validateRecodeMatch(0, "1");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateUploadFileInnerRepeat_case0001()
    {
        final String STR_METHOD_NAME = "testValidateUploadFileInnerRepeat_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);
            l_csv.addRow(l_str);

            l_csv.validateUploadFileInnerRepeat(1);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03051, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateUploadFileInnerRepeat_case0002()
    {
        final String STR_METHOD_NAME = "testValidateUploadFileInnerRepeat_case0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);
            l_str = "111111111111111112,124,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateUploadFileInnerRepeat(1);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateUploadFileInnerRepeat_case0003()
    {
        final String STR_METHOD_NAME = "testValidateUploadFileInnerRepeat_case0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);
            l_str = "111111111111111112,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateUploadFileInnerRepeat(1);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03052, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateUploadFileInnerRepeat_case0004()
    {
        final String STR_METHOD_NAME = "testValidateUploadFileInnerRepeat_case0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);
            l_str = "111111111111111112,124,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateUploadFileInnerRepeat(1);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0001()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "-1,123,12,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03059, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0002()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "1111111111111111111,123,12,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03054, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0003()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCsvTest1 l_csv = new WEB3AdminSrvRegiOtherOrgIdUploadCsvTest1();
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,12,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02776, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0004()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,@@,12,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03057, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0005()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,1,12,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00954, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0006()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCsvTest2 l_csv = new WEB3AdminSrvRegiOtherOrgIdUploadCsvTest2();
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,11111111,12,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03058, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateDetailLine_case0007()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,11111111,１,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01916, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0008()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,11111111,1111111,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01915, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
   
    public void testValidateDetailLine_case0009()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCsvTest3 l_csv = new WEB3AdminSrvRegiOtherOrgIdUploadCsvTest3();
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,11111111,11111111,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00889, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0010()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0010()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,11111111,11111111,2";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00890, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0011()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0011()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "0";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,11111111,11111111,1";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0012()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0012()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "-1,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03059, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0013()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0013()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "1111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03054, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0014()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0014()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCsvTest1 l_csv = new WEB3AdminSrvRegiOtherOrgIdUploadCsvTest1();
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02776, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0015()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0015()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,@@,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03057, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0016()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0016()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,123,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00954, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0017()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0017()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCsvTest3 l_csv = new WEB3AdminSrvRegiOtherOrgIdUploadCsvTest3();
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,12345678,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00889, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0018()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0018()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,12345678,2,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00890, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0019()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0019()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,12345678,1,0D,!,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0020()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0020()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,12345678,1,0D,3,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDetailLine_case0021()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_case0021()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv.uploadDiv = "1";
            l_csv.createColumnHeader();

            String l_str = "111111111111111111,12345678,1,0D,381,111111,20080317,20080318";
            l_csv.addRow(l_str);

            l_csv.validateDetailLine(0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    private class WEB3AdminSrvRegiOtherOrgIdUploadCsvTest1 extends WEB3AdminSrvRegiOtherOrgIdUploadCsv
    {
        public String getId(int l_int)
        {
            return null;
        }
    }

    private class WEB3AdminSrvRegiOtherOrgIdUploadCsvTest2 extends WEB3AdminSrvRegiOtherOrgIdUploadCsv
    {
        public String getPassword(int l_int)
        {
            return null;
        }
    }

    private class WEB3AdminSrvRegiOtherOrgIdUploadCsvTest3 extends WEB3AdminSrvRegiOtherOrgIdUploadCsv
    {
        public String getStatus(int l_int)
        {
            return null;
        }
    }
}
@
