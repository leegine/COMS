head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyULCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込ULCSVのテストクラス(WEB3AdminAccOpenApplyULCsvTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/27 周墨洋 (中訊) 新規作成 モデル No.148
*/

package webbroker3.accountopen;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設申込ULCSV)<BR>
 * 口座開設申込ULCSVのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyULCsvTest extends TestBaseForMock
{
    String l_strtest0 = "00001,2004071690001,384,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,82,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
    String l_strtest1 = "00001,2004071690001,384,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,82,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,06";
    String l_strtest2 = "00001,2004071690001,384,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,82,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,07";

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULCsvTest.class);

    /**
     * 口座開設申込ULCSV
     */
    WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv =
        new WEB3AdminAccOpenApplyULCsv();

    /**
     * @@param arg0
     */
    public WEB3AdminAccOpenApplyULCsvTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        
        //1
        l_adminAccOpenApplyULCsv.addRow(l_strtest0);
        //2
       l_adminAccOpenApplyULCsv.addRow(l_strtest1);
        //3
        l_adminAccOpenApplyULCsv.addRow(l_strtest2);

    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * WEB3AdminAccOpenApplyULCsv
     */
    public void testWEB3AdminAccOpenApplyULCsv_case0001()
    {
        String STR_METHOD_NAME = " testWEB3AdminAccOpenApplyULCsv_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv(1945L);
        assertEquals(1945L,
            this.l_adminAccOpenApplyULCsv.getAdministratorUploadId());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getProductType
     */
    public void testGetProductType_case0001()
    {
        String STR_METHOD_NAME = " testGetProductType_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
        assertEquals(ProductTypeEnum.OTHER,
            this.l_adminAccOpenApplyULCsv.getProductType());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getUploadFileId
     */
    public void testGetUploadFileId_case0001()
    {
        String STR_METHOD_NAME = " testGetUploadFileId_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
        this.l_adminAccOpenApplyULCsv.uploadFileId = "4508";
        assertEquals("4508", this.l_adminAccOpenApplyULCsv.getUploadFileId());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getUploadNewHistory
     */
    public void testGetUploadNewHistory_case0001()
    {
        String STR_METHOD_NAME = " testGetUploadNewHistory_case0001()";
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

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
        this.l_adminAccOpenApplyULCsv.uploadFileId = "UploadFileId";
        long l_lngUploadKey = 1945L;
        try
        {
            AdministratorUploadRow l_administratorUploadRow =
                (AdministratorUploadRow)
                this.l_adminAccOpenApplyULCsv.getUploadNewHistory(l_lngUploadKey);

            assertNull(l_administratorUploadRow);

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
    public void testGetUploadNewHistory_case0002()
    {
        String STR_METHOD_NAME = " testGetUploadNewHistory_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        AdministratorUploadParams l_AdministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_AdministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
        l_AdministratorUploadParams.setUploadFileId("口座開設申込");

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

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
        long l_lngUploadKey = 12345;

        try
        {
            AdministratorUploadRow l_administratorUploadRow =
                (AdministratorUploadRow)
                this.l_adminAccOpenApplyULCsv.getUploadNewHistory(l_lngUploadKey);

            assertEquals("0D", l_administratorUploadRow.getInstitutionCode());
            assertEquals("口座開設申込", l_administratorUploadRow.getUploadFileId());
            assertEquals(ProductTypeEnum.OTHER, l_administratorUploadRow.getProductType());
            assertEquals(12345L, l_administratorUploadRow.getUploadKey());
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
    public void testGetUploadNewHistory_case0003()
    {
        String STR_METHOD_NAME = " testGetUploadNewHistory_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        AdministratorUploadParams l_AdministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_AdministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
        l_AdministratorUploadParams.setUploadFileId("口座開設申込");
        l_AdministratorUploadParams.setNote1("中止");

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

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
        long l_lngUploadKey = 12345;

        try
        {
            AdministratorUploadRow l_administratorUploadRow =
                (AdministratorUploadRow)
                this.l_adminAccOpenApplyULCsv.getUploadNewHistory(l_lngUploadKey);

            assertNull(l_administratorUploadRow);
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
    public void testGetUploadNewHistory_case0004()
    {
        String STR_METHOD_NAME = " testGetUploadNewHistory_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            AdministratorUploadParams l_AdministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_AdministratorUploadParams.setAdministratorUploadId(222);
            l_AdministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_AdministratorUploadParams.setUploadFileId("口座開設申込");
            l_AdministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20071020", "yyyyMMdd"));

            TestDBUtility.insertWithDel(l_AdministratorUploadParams);

            l_AdministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_AdministratorUploadParams.setAdministratorUploadId(333);
            l_AdministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_AdministratorUploadParams.setUploadFileId("口座開設申込");
            l_AdministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20071120", "yyyyMMdd"));

            TestDBUtility.insertWithDel(l_AdministratorUploadParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
        long l_lngUploadKey = 12345;

        try
        {
            AdministratorUploadRow l_administratorUploadRow =
                (AdministratorUploadRow)
                this.l_adminAccOpenApplyULCsv.getUploadNewHistory(l_lngUploadKey);

            assertEquals(333, l_administratorUploadRow.getAdministratorUploadId());
            assertEquals("0D", l_administratorUploadRow.getInstitutionCode());
            assertEquals("口座開設申込", l_administratorUploadRow.getUploadFileId());
            assertEquals(ProductTypeEnum.OTHER, l_administratorUploadRow.getProductType());
            assertEquals(12345L, l_administratorUploadRow.getUploadKey());
            assertEquals(WEB3DateUtility.getDate("20071120", "yyyyMMdd"),
                l_administratorUploadRow.getUploadStartTimestamp());
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
     * validateDuplicateAccount
     */
    public void testValidateDuplicateAccount_case0001()
    {
        String STR_METHOD_NAME = " testValidateDuplicateAccount_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("0001,,");
            this.l_adminAccOpenApplyULCsv.addRow("0001,,");

            int l_intLineNo = 1;

            this.l_adminAccOpenApplyULCsv.validateDuplicateAccount(l_intLineNo);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02959, l_ex.getErrorInfo());
            assertEquals("0001", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     * validateDuplicateAccount
     */
    public void testValidateDuplicateAccount_case0002()
    {
        String STR_METHOD_NAME = " testValidateDuplicateAccount_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("0001,0002,0003");
            this.l_adminAccOpenApplyULCsv.addRow("0002,0002,0003");

            int l_intLineNo = 1;

            this.l_adminAccOpenApplyULCsv.validateDuplicateAccount(l_intLineNo);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02959, l_ex.getErrorInfo());
            assertEquals("0002　@0003", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     * validateDuplicateAccount
     */
    public void testValidateDuplicateAccount_case0003()
    {
        String STR_METHOD_NAME = " testValidateDuplicateAccount_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("0001,0002,0003");
            this.l_adminAccOpenApplyULCsv.addRow("0002,0002,0004");

            int l_intLineNo = 1;

            this.l_adminAccOpenApplyULCsv.validateDuplicateAccount(l_intLineNo);

            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     * validateDuplicateAccount
     */
    public void testValidateDuplicateAccount_case0004()
    {
        String STR_METHOD_NAME = " testValidateDuplicateAccount_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("0001,0002,0003");
            this.l_adminAccOpenApplyULCsv.addRow("0002,0004,0003");

            int l_intLineNo = 1;

            this.l_adminAccOpenApplyULCsv.validateDuplicateAccount(l_intLineNo);

            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     * validateDuplicateAccount
     */
    public void testValidateDuplicateAccount_case0005()
    {
        String STR_METHOD_NAME = " testValidateDuplicateAccount_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("0001,0002,0003");
            this.l_adminAccOpenApplyULCsv.addRow("0002,0004,0005");
        
            int l_intLineNo = 1;

            this.l_adminAccOpenApplyULCsv.validateDuplicateAccount(l_intLineNo);

            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     * validateDuplicateAccount
     */
    public void testValidateDuplicateAccount_case0006()
    {
        String STR_METHOD_NAME = " testValidateDuplicateAccount_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("0001,0002,");
            this.l_adminAccOpenApplyULCsv.addRow("0002,0003,");

            int l_intLineNo = 1;

            this.l_adminAccOpenApplyULCsv.validateDuplicateAccount(l_intLineNo);

            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     * validateExpAccountOpenLogin
     */
    public void testValidateExpAccountOpenLogin_case0001()
    {
        String STR_METHOD_NAME = " testValidateExpAccountOpenLogin_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ExpAccountOpenParams l_expAccountOpenParams =
                TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccOpenRequestNumber("9967");

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);

            TestDBUtility.insertWithDel(l_expAccountOpenParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("9967,0002,");

            int l_intLineNo = 0;
            String l_strInstitutionCode = "0D";

            l_adminAccOpenApplyULCsv.validateExpAccountOpenLogin(
                l_intLineNo,
                l_strInstitutionCode);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02960, l_ex.getErrorInfo());
            assertEquals("9967", l_ex.getErrorMessage());
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
                TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
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
     * validateExpAccountOpenLogin
     */
    public void testValidateExpAccountOpenLogin_case0002()
    {
        String STR_METHOD_NAME = " testValidateExpAccountOpenLogin_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("9967,,");

            int l_intLineNo = 0;
            String l_strInstitutionCode = "0D";

            l_adminAccOpenApplyULCsv.validateExpAccountOpenLogin(
                l_intLineNo,
                l_strInstitutionCode);

            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     * validateExpAccountOpenLogin
     */
    public void testValidateExpAccountOpenLogin_case0003()
    {
        String STR_METHOD_NAME = " testValidateExpAccountOpenLogin_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ExpAccountOpenParams l_expAccountOpenParams =
                TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccOpenRequestNumber("9967");
            l_expAccountOpenParams.setAccountCode("7654321");

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);

            TestDBUtility.insertWithDel(l_expAccountOpenParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("3345,381,765432");

            int l_intLineNo = 0;
            String l_strInstitutionCode = "0D";

            l_adminAccOpenApplyULCsv.validateExpAccountOpenLogin(
                l_intLineNo,
                l_strInstitutionCode);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02960, l_ex.getErrorInfo());
            assertEquals("381　@765432", l_ex.getErrorMessage());
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
                TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
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
     * validateExpAccountOpenLogin
     */
    public void testValidateExpAccountOpenLogin_case0004()
    {
        String STR_METHOD_NAME = " testValidateExpAccountOpenLogin_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ExpAccountOpenParams l_expAccountOpenParams =
                TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccOpenRequestNumber("9967");
            l_expAccountOpenParams.setAccountCode("1234567");

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);

            TestDBUtility.insertWithDel(l_expAccountOpenParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[3];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("3345,381,765432");

            int l_intLineNo = 0;
            String l_strInstitutionCode = "0D";

            l_adminAccOpenApplyULCsv.validateExpAccountOpenLogin(
                l_intLineNo,
                l_strInstitutionCode);

            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
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
                TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
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
     * validateRecordNumber
     */
    public void testValidateRecordNumber_case0001()
    {
        String STR_METHOD_NAME = " testValidateRecordNumber_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[4];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "レコード番号";
        l_intColumnNumber = 3;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[3] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("3345,381,765432,");

            int l_intLineNo = 0;

            this.l_adminAccOpenApplyULCsv.validateRecordNumber(l_intLineNo);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());
            assertEquals("レコード番号", l_ex.getErrorMessage());
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

    /**
     * validateRecordNumber
     */
    public void testValidateRecordNumber_case0002()
    {
        String STR_METHOD_NAME = " testValidateRecordNumber_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[4];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "レコード番号";
        l_intColumnNumber = 3;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[3] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("3345,381,765432,0002");

            int l_intLineNo = 0;

            this.l_adminAccOpenApplyULCsv.validateRecordNumber(l_intLineNo);

            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
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

    /**
     * validateRecordNumber
     */
    public void testValidateRecordNumber_case0003()
    {
        String STR_METHOD_NAME = " testValidateRecordNumber_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[4];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "レコード番号";
        l_intColumnNumber = 3;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[3] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("3345,381,765432,abc");

            int l_intLineNo = 0;

            this.l_adminAccOpenApplyULCsv.validateRecordNumber(l_intLineNo);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01738, l_ex.getErrorInfo());
            assertEquals("レコード番号", l_ex.getErrorMessage());
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

    /**
     * validateRecordNumber
     */
    public void testValidateRecordNumber_case0004()
    {
        String STR_METHOD_NAME = " testValidateRecordNumber_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[4];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "レコード番号";
        l_intColumnNumber = 3;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[3] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("3345,381,765432,１２３");

            int l_intLineNo = 0;

            this.l_adminAccOpenApplyULCsv.validateRecordNumber(l_intLineNo);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01738, l_ex.getErrorInfo());
            assertEquals("レコード番号", l_ex.getErrorMessage());
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

    /**
     * validateRecordNumber
     */
    public void testValidateRecordNumber_case0005()
    {
        String STR_METHOD_NAME = " testValidateRecordNumber_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();

        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModels =
            new WEB3GentradeCsvColumnModel[4];

        String l_strColumnLabel = "識別コード";
        int l_intColumnNumber = 0;
        int l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        DateFormat l_dateFormat = null;

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[0] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "部店コード";
        l_intColumnNumber = 1;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[1] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "顧客コード";
        l_intColumnNumber = 2;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[2] = l_gentradeCsvColumnModel;

        l_strColumnLabel = "レコード番号";
        l_intColumnNumber = 3;
        l_intColumnType = WEB3GentradeCsvColumnModel.STRINGTYPE;
        l_dateFormat = null;

        l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel(
                l_strColumnLabel,
                l_intColumnNumber,
                l_intColumnType,
                l_dateFormat);

        l_gentradeCsvColumnModels[3] = l_gentradeCsvColumnModel;

        this.l_adminAccOpenApplyULCsv.setColumnHeader(l_gentradeCsvColumnModels);

        try
        {
            this.l_adminAccOpenApplyULCsv.addRow("3345,381,765432,123z");

            int l_intLineNo = 0;

            this.l_adminAccOpenApplyULCsv.validateRecordNumber(l_intLineNo);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01738, l_ex.getErrorInfo());
            assertEquals("レコード番号", l_ex.getErrorMessage());
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

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv(222L);

        try
        {
            this.l_adminAccOpenApplyULCsv.updateUploadCancel();
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

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv(222L);

        try
        {
            this.l_adminAccOpenApplyULCsv.updateUploadCancel();
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

    /**
     * updateUploadCancel
     */
    public void testToExpAccountOpen_case0001()
    {
        String STR_METHOD_NAME = " testToExpAccountOpen_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv(222L);

        try
        {
            this.l_adminAccOpenApplyULCsv.createColumnHeader();

            this.l_adminAccOpenApplyULCsv.addRow(
                ",1111,381,2246357,55555,0,3,20071128172830,山田,太郎," +
                "やまだ,たろう,1,4,110211,ahou@@daiwa.com,748-5274,日本,東京,八王子," +
                "にほん,とうきょう,はちおうじ,010,8874,5174,132,4444,9914,94," +
                "山口組,123-4567,奈落,543,5814,8814,取引所,取締役,2,泉," +
                "湯,XXX,5,77,三井,13,上野,2,8765432,123," +
                "456,0,1,2,3,4,5,4,3,2," +
                "1,1,1,1,1,1,7,99,2,1," +
                "666,5,4,3,2,1,0");

            int l_intLineNo = 0;
            String l_strInstitutionCode = "0D";

            WEB3AccOpenExpAccountOpen l_cccOpenExpAccountOpen =
                this.l_adminAccOpenApplyULCsv.toExpAccountOpen(
                    l_intLineNo,
                    l_strInstitutionCode);

            assertNotNull(l_cccOpenExpAccountOpen);

            assertEquals("0D", l_cccOpenExpAccountOpen.getInstitutionCode());
            assertEquals("1111", l_cccOpenExpAccountOpen.getRequestNumber());
            assertEquals("381", l_cccOpenExpAccountOpen.getBranchCode());
            assertEquals("2246357", l_cccOpenExpAccountOpen.getAccountCode());
            assertEquals("0", l_cccOpenExpAccountOpen.getAccountDiv());

            Field field = WEB3AccOpenExpAccountOpen.class.getDeclaredField("expAccountOpenParams");
            field.setAccessible(true);
            ExpAccountOpenParams l_resultExpAccountOpenParams =
                (ExpAccountOpenParams)field.get(l_cccOpenExpAccountOpen);

            assertEquals("1111", l_resultExpAccountOpenParams.getAccOpenRequestNumber());
            assertEquals("381", l_resultExpAccountOpenParams.getBranchCode());
            assertEquals("2246357", l_resultExpAccountOpenParams.getAccountCode());
            assertEquals("55555", l_resultExpAccountOpenParams.getSonarTraderCode());
            assertEquals("0", l_resultExpAccountOpenParams.getAccountDiv());
            assertEquals("3", l_resultExpAccountOpenParams.getOrderDiv());
            assertEquals(WEB3DateUtility.getDate("20071128172830", "yyyyMMddHHmmss"),
                l_resultExpAccountOpenParams.getInfomationClaimDatetime());
            assertEquals("山田", l_resultExpAccountOpenParams.getFamilyName());
            assertEquals("太郎", l_resultExpAccountOpenParams.getGivenName());
            assertEquals("やまだ", l_resultExpAccountOpenParams.getFamilyNameAlt1());
            assertEquals("たろう", l_resultExpAccountOpenParams.getGivenNameAlt1());
            assertEquals("1", l_resultExpAccountOpenParams.getSex());
            assertEquals("4", l_resultExpAccountOpenParams.getEraBorn());
            assertEquals("110211", l_resultExpAccountOpenParams.getBornDate());
            assertEquals("ahou@@daiwa.com", l_resultExpAccountOpenParams.getEmailAddress());
            assertEquals("748-5274", l_resultExpAccountOpenParams.getZipCode());
            assertEquals("日本", l_resultExpAccountOpenParams.getAddressLine1());
            assertEquals("東京", l_resultExpAccountOpenParams.getAddressLine2());
            assertEquals("八王子", l_resultExpAccountOpenParams.getAddressLine3());
            assertEquals("にほん", l_resultExpAccountOpenParams.getAddressLine1Kana());
            assertEquals("とうきょう", l_resultExpAccountOpenParams.getAddressLine2Kana());
            assertEquals("はちおうじ", l_resultExpAccountOpenParams.getAddressLine3Kana());
            assertEquals("010-8874-5174", l_resultExpAccountOpenParams.getTelephone());
            assertEquals("132-4444-9914", l_resultExpAccountOpenParams.getMobile());
            assertEquals("94", l_resultExpAccountOpenParams.getOccupationDiv());
            assertEquals("山口組", l_resultExpAccountOpenParams.getOffice());
            assertEquals("123-4567", l_resultExpAccountOpenParams.getOfficeZipCode());
            assertEquals("奈落", l_resultExpAccountOpenParams.getOfficeAddress());
            assertEquals("543-5814-8814", l_resultExpAccountOpenParams.getOfficeTelephone());
            assertEquals("取引所", l_resultExpAccountOpenParams.getDepartment());
            assertEquals("取締役", l_resultExpAccountOpenParams.getPost());
            assertEquals("2", l_resultExpAccountOpenParams.getFamilyRelationship());
            assertEquals("泉", l_resultExpAccountOpenParams.getHouseholder());
            assertEquals("湯", l_resultExpAccountOpenParams.getHouseholderOffice());
            assertEquals("XXX", l_resultExpAccountOpenParams.getHouseholderPost());
            assertEquals("5", l_resultExpAccountOpenParams.getTransferDiv());
            assertEquals("77", l_resultExpAccountOpenParams.getFinInstitutionCode());
            assertEquals("三井", l_resultExpAccountOpenParams.getFinInstitutionName());
            assertEquals("13", l_resultExpAccountOpenParams.getFinBranchCode());
            assertEquals("上野", l_resultExpAccountOpenParams.getFinBranchName());
            assertEquals("2", l_resultExpAccountOpenParams.getFinSaveDiv());
            assertEquals("8765432", l_resultExpAccountOpenParams.getFinAccountNo());
            assertEquals("123", l_resultExpAccountOpenParams.getPostalSaveCode());
            assertEquals("456", l_resultExpAccountOpenParams.getPostalSaveNo());
            assertEquals("0", l_resultExpAccountOpenParams.getExperienceDivEquity());
            assertEquals("1", l_resultExpAccountOpenParams.getExperienceDivMargin());
            assertEquals("2", l_resultExpAccountOpenParams.getExperienceDivBond());
            assertEquals("3", l_resultExpAccountOpenParams.getExperienceDivWt());
            assertEquals("4", l_resultExpAccountOpenParams.getExperienceDivFundSk());
            assertEquals("5", l_resultExpAccountOpenParams.getExperienceDivFundBd());
            assertEquals("4", l_resultExpAccountOpenParams.getExperienceDivFo());
            assertEquals("3", l_resultExpAccountOpenParams.getExperienceDivFEquity());
            assertEquals("2", l_resultExpAccountOpenParams.getExperienceDivEtc());
            assertEquals(BooleanEnum.TRUE, l_resultExpAccountOpenParams.getInterestFlagEquity());
            assertEquals(BooleanEnum.TRUE, l_resultExpAccountOpenParams.getInterestFlagMargin());
            assertEquals(BooleanEnum.TRUE, l_resultExpAccountOpenParams.getInterestFlagBond());
            assertEquals(BooleanEnum.TRUE, l_resultExpAccountOpenParams.getInterestFlagFund());
            assertEquals(BooleanEnum.TRUE, l_resultExpAccountOpenParams.getInterestFlagFo());
            assertEquals(BooleanEnum.TRUE, l_resultExpAccountOpenParams.getInterestFlagFEquity());
            assertEquals("7", l_resultExpAccountOpenParams.getAnnualIncomeDiv());
            assertEquals("99", l_resultExpAccountOpenParams.getAssetValueDiv());
            assertEquals("2", l_resultExpAccountOpenParams.getSpecialAcc());
            assertEquals(BooleanEnum.TRUE, l_resultExpAccountOpenParams.getInsiderFlag());
            assertEquals("666", l_resultExpAccountOpenParams.getProductName());
            assertEquals("5", l_resultExpAccountOpenParams.getExtItemDiv1());
            assertEquals("4", l_resultExpAccountOpenParams.getExtItemDiv2());
            assertEquals("3", l_resultExpAccountOpenParams.getExtItemDiv3());
            assertEquals("2", l_resultExpAccountOpenParams.getExtItemDiv4());
            assertEquals("1", l_resultExpAccountOpenParams.getExtItemDiv5());
            assertEquals("0", l_resultExpAccountOpenParams.getExtItemDiv6());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
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

    /**
     * updateUploadCancel
     */
    public void testToExpAccountOpen_case0002()
    {
        String STR_METHOD_NAME = " testToExpAccountOpen_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv(222L);

        try
        {
            this.l_adminAccOpenApplyULCsv.createColumnHeader();

            this.l_adminAccOpenApplyULCsv.addRow(
                ",1111,381,2246357,55555,0,3,20071128172830,山田,太郎," +
                "やまだ,たろう,1,4,110211,ahou@@daiwa.com,748-5274,日本,東京,八王子," +
                "にほん,とうきょう,はちおうじ,010,8874,5174,132,4444,9914,94," +
                "山口組,123-4567,奈落,543,5814,8814,取引所,取締役,2,泉," +
                "湯,XXX,5,77,三井,13,上野,2,8765432,123," +
                "456,0,1,2,3,4,5,4,3,2," +
                "0,0,0,0,0,0,7,99,2,0," +
                "666,5,4,3,2,1,0");

            int l_intLineNo = 0;
            String l_strInstitutionCode = "0D";

            WEB3AccOpenExpAccountOpen l_cccOpenExpAccountOpen =
                this.l_adminAccOpenApplyULCsv.toExpAccountOpen(
                    l_intLineNo,
                    l_strInstitutionCode);

            assertNotNull(l_cccOpenExpAccountOpen);

            assertEquals("0D", l_cccOpenExpAccountOpen.getInstitutionCode());
            assertEquals("1111", l_cccOpenExpAccountOpen.getRequestNumber());
            assertEquals("381", l_cccOpenExpAccountOpen.getBranchCode());
            assertEquals("2246357", l_cccOpenExpAccountOpen.getAccountCode());
            assertEquals("0", l_cccOpenExpAccountOpen.getAccountDiv());

            Field field = WEB3AccOpenExpAccountOpen.class.getDeclaredField("expAccountOpenParams");
            field.setAccessible(true);
            ExpAccountOpenParams l_resultExpAccountOpenParams =
                (ExpAccountOpenParams)field.get(l_cccOpenExpAccountOpen);

            assertEquals("1111", l_resultExpAccountOpenParams.getAccOpenRequestNumber());
            assertEquals("381", l_resultExpAccountOpenParams.getBranchCode());
            assertEquals("2246357", l_resultExpAccountOpenParams.getAccountCode());
            assertEquals("55555", l_resultExpAccountOpenParams.getSonarTraderCode());
            assertEquals("0", l_resultExpAccountOpenParams.getAccountDiv());
            assertEquals("3", l_resultExpAccountOpenParams.getOrderDiv());
            assertEquals(WEB3DateUtility.getDate("20071128172830", "yyyyMMddHHmmss"),
                l_resultExpAccountOpenParams.getInfomationClaimDatetime());
            assertEquals("山田", l_resultExpAccountOpenParams.getFamilyName());
            assertEquals("太郎", l_resultExpAccountOpenParams.getGivenName());
            assertEquals("やまだ", l_resultExpAccountOpenParams.getFamilyNameAlt1());
            assertEquals("たろう", l_resultExpAccountOpenParams.getGivenNameAlt1());
            assertEquals("1", l_resultExpAccountOpenParams.getSex());
            assertEquals("4", l_resultExpAccountOpenParams.getEraBorn());
            assertEquals("110211", l_resultExpAccountOpenParams.getBornDate());
            assertEquals("ahou@@daiwa.com", l_resultExpAccountOpenParams.getEmailAddress());
            assertEquals("748-5274", l_resultExpAccountOpenParams.getZipCode());
            assertEquals("日本", l_resultExpAccountOpenParams.getAddressLine1());
            assertEquals("東京", l_resultExpAccountOpenParams.getAddressLine2());
            assertEquals("八王子", l_resultExpAccountOpenParams.getAddressLine3());
            assertEquals("にほん", l_resultExpAccountOpenParams.getAddressLine1Kana());
            assertEquals("とうきょう", l_resultExpAccountOpenParams.getAddressLine2Kana());
            assertEquals("はちおうじ", l_resultExpAccountOpenParams.getAddressLine3Kana());
            assertEquals("010-8874-5174", l_resultExpAccountOpenParams.getTelephone());
            assertEquals("132-4444-9914", l_resultExpAccountOpenParams.getMobile());
            assertEquals("94", l_resultExpAccountOpenParams.getOccupationDiv());
            assertEquals("山口組", l_resultExpAccountOpenParams.getOffice());
            assertEquals("123-4567", l_resultExpAccountOpenParams.getOfficeZipCode());
            assertEquals("奈落", l_resultExpAccountOpenParams.getOfficeAddress());
            assertEquals("543-5814-8814", l_resultExpAccountOpenParams.getOfficeTelephone());
            assertEquals("取引所", l_resultExpAccountOpenParams.getDepartment());
            assertEquals("取締役", l_resultExpAccountOpenParams.getPost());
            assertEquals("2", l_resultExpAccountOpenParams.getFamilyRelationship());
            assertEquals("泉", l_resultExpAccountOpenParams.getHouseholder());
            assertEquals("湯", l_resultExpAccountOpenParams.getHouseholderOffice());
            assertEquals("XXX", l_resultExpAccountOpenParams.getHouseholderPost());
            assertEquals("5", l_resultExpAccountOpenParams.getTransferDiv());
            assertEquals("77", l_resultExpAccountOpenParams.getFinInstitutionCode());
            assertEquals("三井", l_resultExpAccountOpenParams.getFinInstitutionName());
            assertEquals("13", l_resultExpAccountOpenParams.getFinBranchCode());
            assertEquals("上野", l_resultExpAccountOpenParams.getFinBranchName());
            assertEquals("2", l_resultExpAccountOpenParams.getFinSaveDiv());
            assertEquals("8765432", l_resultExpAccountOpenParams.getFinAccountNo());
            assertEquals("123", l_resultExpAccountOpenParams.getPostalSaveCode());
            assertEquals("456", l_resultExpAccountOpenParams.getPostalSaveNo());
            assertEquals("0", l_resultExpAccountOpenParams.getExperienceDivEquity());
            assertEquals("1", l_resultExpAccountOpenParams.getExperienceDivMargin());
            assertEquals("2", l_resultExpAccountOpenParams.getExperienceDivBond());
            assertEquals("3", l_resultExpAccountOpenParams.getExperienceDivWt());
            assertEquals("4", l_resultExpAccountOpenParams.getExperienceDivFundSk());
            assertEquals("5", l_resultExpAccountOpenParams.getExperienceDivFundBd());
            assertEquals("4", l_resultExpAccountOpenParams.getExperienceDivFo());
            assertEquals("3", l_resultExpAccountOpenParams.getExperienceDivFEquity());
            assertEquals("2", l_resultExpAccountOpenParams.getExperienceDivEtc());
            assertEquals(BooleanEnum.FALSE, l_resultExpAccountOpenParams.getInterestFlagEquity());
            assertEquals(BooleanEnum.FALSE, l_resultExpAccountOpenParams.getInterestFlagMargin());
            assertEquals(BooleanEnum.FALSE, l_resultExpAccountOpenParams.getInterestFlagBond());
            assertEquals(BooleanEnum.FALSE, l_resultExpAccountOpenParams.getInterestFlagFund());
            assertEquals(BooleanEnum.FALSE, l_resultExpAccountOpenParams.getInterestFlagFo());
            assertEquals(BooleanEnum.FALSE, l_resultExpAccountOpenParams.getInterestFlagFEquity());
            assertEquals("7", l_resultExpAccountOpenParams.getAnnualIncomeDiv());
            assertEquals("99", l_resultExpAccountOpenParams.getAssetValueDiv());
            assertEquals("2", l_resultExpAccountOpenParams.getSpecialAcc());
            assertEquals(BooleanEnum.FALSE, l_resultExpAccountOpenParams.getInsiderFlag());
            assertEquals("666", l_resultExpAccountOpenParams.getProductName());
            assertEquals("5", l_resultExpAccountOpenParams.getExtItemDiv1());
            assertEquals("4", l_resultExpAccountOpenParams.getExtItemDiv2());
            assertEquals("3", l_resultExpAccountOpenParams.getExtItemDiv3());
            assertEquals("2", l_resultExpAccountOpenParams.getExtItemDiv4());
            assertEquals("1", l_resultExpAccountOpenParams.getExtItemDiv5());
            assertEquals("0", l_resultExpAccountOpenParams.getExtItemDiv6());
            assertEquals(0, l_resultExpAccountOpenParams.getExAccountFlag().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagEquity().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagMargin().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagBond().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagWt().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagFundSk().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagFundBd().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagFo().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagFEquity().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getExperienceFlagEtc().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getInterestFlagMinistock().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getInterestFlagEtc().intValue());
            assertEquals(0, l_resultExpAccountOpenParams.getIdConfirmFlag().intValue());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
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

    /*
     * Test method for 'webbroker3.accountopen.WEB3AdminAccOpenApplyULCsv.getIDConfirmDocDiv(int)'
     */
   
    public void testGetRecordNumber() {
        final String STR_METHOD_NAME ="testGetRecordNumber()";
         log.entering(STR_METHOD_NAME);
            assertEquals("00001",l_adminAccOpenApplyULCsv.getRecordNumber(0));
            assertEquals("00001",l_adminAccOpenApplyULCsv.getRecordNumber(1));
            assertEquals("00001",l_adminAccOpenApplyULCsv.getRecordNumber(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetRequestNumber() {
        final String STR_METHOD_NAME ="testGetRequestNumber()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2004071690001",l_adminAccOpenApplyULCsv.getRequestNumber(0));
            assertEquals("2004071690001",l_adminAccOpenApplyULCsv.getRequestNumber(1));
            assertEquals("2004071690001",l_adminAccOpenApplyULCsv.getRequestNumber(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetBranchCode() {
        final String STR_METHOD_NAME ="testGetBranchCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("384",l_adminAccOpenApplyULCsv.getBranchCode(0));
            assertEquals("384",l_adminAccOpenApplyULCsv.getBranchCode(1));
            assertEquals("384",l_adminAccOpenApplyULCsv.getBranchCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAccountCode() {
        final String STR_METHOD_NAME ="testGetAccountCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("600005",l_adminAccOpenApplyULCsv.getAccountCode(0));
            assertEquals("600005",l_adminAccOpenApplyULCsv.getAccountCode(1));
            assertEquals("600005",l_adminAccOpenApplyULCsv.getAccountCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetSonarTraderCode() {
        final String STR_METHOD_NAME ="testGetSonarTraderCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("11123",l_adminAccOpenApplyULCsv.getSonarTraderCode(0));
            assertEquals("11123",l_adminAccOpenApplyULCsv.getSonarTraderCode(1));
            assertEquals("11123",l_adminAccOpenApplyULCsv.getSonarTraderCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAccountDiv() {
        final String STR_METHOD_NAME ="testGetAccountDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("0",l_adminAccOpenApplyULCsv.getAccountDiv(0));
            assertEquals("0",l_adminAccOpenApplyULCsv.getAccountDiv(1));
            assertEquals("0",l_adminAccOpenApplyULCsv.getAccountDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetOrderDiv() {
        final String STR_METHOD_NAME ="testGetOrderDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("3",l_adminAccOpenApplyULCsv.getOrderDiv(0));
            assertEquals("3",l_adminAccOpenApplyULCsv.getOrderDiv(1));
            assertEquals("3",l_adminAccOpenApplyULCsv.getOrderDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInfoClaimDate() {
        final String STR_METHOD_NAME ="testGetInfoClaimDate()";
         log.entering(STR_METHOD_NAME);
            assertEquals("20040716100000",l_adminAccOpenApplyULCsv.getInfoClaimDate(0));
            assertEquals("20040716100000",l_adminAccOpenApplyULCsv.getInfoClaimDate(1));
            assertEquals("20040716100000",l_adminAccOpenApplyULCsv.getInfoClaimDate(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAccountFamilyName() {
        final String STR_METHOD_NAME ="testGetAccountFamilyName()";
         log.entering(STR_METHOD_NAME);
            assertEquals("福永",l_adminAccOpenApplyULCsv.getAccountFamilyName(0));
            assertEquals("福永",l_adminAccOpenApplyULCsv.getAccountFamilyName(1));
            assertEquals("福永",l_adminAccOpenApplyULCsv.getAccountFamilyName(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAccountName() {
        final String STR_METHOD_NAME ="testGetAccountName()";
         log.entering(STR_METHOD_NAME);
            assertEquals("祐一",l_adminAccOpenApplyULCsv.getAccountName(0));
            assertEquals("祐一",l_adminAccOpenApplyULCsv.getAccountName(1));
            assertEquals("祐一",l_adminAccOpenApplyULCsv.getAccountName(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAccountFamilyNameKana() {
        final String STR_METHOD_NAME ="testGetAccountFamilyNameKana()";
         log.entering(STR_METHOD_NAME);
            assertEquals("フクナガ",l_adminAccOpenApplyULCsv.getAccountFamilyNameKana(0));
            assertEquals("フクナガ",l_adminAccOpenApplyULCsv.getAccountFamilyNameKana(1));
            assertEquals("フクナガ",l_adminAccOpenApplyULCsv.getAccountFamilyNameKana(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAccountNameKana() {
        final String STR_METHOD_NAME ="testGetAccountNameKana()";
         log.entering(STR_METHOD_NAME);
            assertEquals("ユウイチ",l_adminAccOpenApplyULCsv.getAccountNameKana(0));
            assertEquals("ユウイチ",l_adminAccOpenApplyULCsv.getAccountNameKana(1));
            assertEquals("ユウイチ",l_adminAccOpenApplyULCsv.getAccountNameKana(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetSex() {
        final String STR_METHOD_NAME ="testGetSex()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getSex(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getSex(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getSex(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetEraBorn() {
        final String STR_METHOD_NAME ="testGetEraBorn()";
         log.entering(STR_METHOD_NAME);
            assertEquals("3",l_adminAccOpenApplyULCsv.getEraBorn(0));
            assertEquals("3",l_adminAccOpenApplyULCsv.getEraBorn(1));
            assertEquals("3",l_adminAccOpenApplyULCsv.getEraBorn(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetBornDate() {
        final String STR_METHOD_NAME ="testGetBornDate()";
         log.entering(STR_METHOD_NAME);
            assertEquals("101010",l_adminAccOpenApplyULCsv.getBornDate(0));
            assertEquals("101010",l_adminAccOpenApplyULCsv.getBornDate(1));
            assertEquals("101010",l_adminAccOpenApplyULCsv.getBornDate(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetMailAddress() {
        final String STR_METHOD_NAME ="testGetMailAddress()";
         log.entering(STR_METHOD_NAME);
            assertEquals("12355@@163.com",l_adminAccOpenApplyULCsv.getMailAddress(0));
            assertEquals("12355@@163.com",l_adminAccOpenApplyULCsv.getMailAddress(1));
            assertEquals("12355@@163.com",l_adminAccOpenApplyULCsv.getMailAddress(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetZipCode() {
        final String STR_METHOD_NAME ="testGetZipCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1001235",l_adminAccOpenApplyULCsv.getZipCode(0));
            assertEquals("1001235",l_adminAccOpenApplyULCsv.getZipCode(1));
            assertEquals("1001235",l_adminAccOpenApplyULCsv.getZipCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAddress1() {
        final String STR_METHOD_NAME ="testGetAddress1()";
         log.entering(STR_METHOD_NAME);
            assertEquals("滋賀県栗東市",l_adminAccOpenApplyULCsv.getAddress1(0));
            assertEquals("滋賀県栗東市",l_adminAccOpenApplyULCsv.getAddress1(1));
            assertEquals("滋賀県栗東市",l_adminAccOpenApplyULCsv.getAddress1(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAddress2() {
        final String STR_METHOD_NAME ="testGetAddress2()";
         log.entering(STR_METHOD_NAME);
            assertEquals("御園",l_adminAccOpenApplyULCsv.getAddress2(0));
            assertEquals("御園",l_adminAccOpenApplyULCsv.getAddress2(1));
            assertEquals("御園",l_adminAccOpenApplyULCsv.getAddress2(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAddress3() {
        final String STR_METHOD_NAME ="testGetAddress3()";
         log.entering(STR_METHOD_NAME);
            assertEquals("栗東トレーニングセンター",l_adminAccOpenApplyULCsv.getAddress3(0));
            assertEquals("栗東トレーニングセンター",l_adminAccOpenApplyULCsv.getAddress3(1));
            assertEquals("栗東トレーニングセンター",l_adminAccOpenApplyULCsv.getAddress3(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAddressKana1() {
        final String STR_METHOD_NAME ="testGetAddressKana1()";
         log.entering(STR_METHOD_NAME);
            assertEquals("シガケンリットウシ",l_adminAccOpenApplyULCsv.getAddressKana1(0));
            assertEquals("シガケンリットウシ",l_adminAccOpenApplyULCsv.getAddressKana1(1));
            assertEquals("シガケンリットウシ",l_adminAccOpenApplyULCsv.getAddressKana1(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAddressKana2() {
        final String STR_METHOD_NAME ="testGetAddressKana2()";
         log.entering(STR_METHOD_NAME);
            assertEquals("ミソノ１０２８",l_adminAccOpenApplyULCsv.getAddressKana2(0));
            assertEquals("ミソノ１０２８",l_adminAccOpenApplyULCsv.getAddressKana2(1));
            assertEquals("ミソノ１０２８",l_adminAccOpenApplyULCsv.getAddressKana2(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAddressKana3() {
        final String STR_METHOD_NAME ="testGetAddressKana3()";
         log.entering(STR_METHOD_NAME);
            assertEquals("リットウトレーニングセンター",l_adminAccOpenApplyULCsv.getAddressKana3(0));
            assertEquals("リットウトレーニングセンター",l_adminAccOpenApplyULCsv.getAddressKana3(1));
            assertEquals("リットウトレーニングセンター",l_adminAccOpenApplyULCsv.getAddressKana3(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetTelephoneAreaCode() {
        final String STR_METHOD_NAME ="testGetTelephoneAreaCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("646433",l_adminAccOpenApplyULCsv.getTelephoneAreaCode(0));
            assertEquals("646433",l_adminAccOpenApplyULCsv.getTelephoneAreaCode(1));
            assertEquals("646433",l_adminAccOpenApplyULCsv.getTelephoneAreaCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetTelephoneExchangeNumber() {
        final String STR_METHOD_NAME ="testGetTelephoneExchangeNumber()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2222",l_adminAccOpenApplyULCsv.getTelephoneExchangeNumber(0));
            assertEquals("2222",l_adminAccOpenApplyULCsv.getTelephoneExchangeNumber(1));
            assertEquals("2222",l_adminAccOpenApplyULCsv.getTelephoneExchangeNumber(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetTelephoneNumber() {
        final String STR_METHOD_NAME ="testGetTelephoneNumber()";
         log.entering(STR_METHOD_NAME);
            assertEquals("4343",l_adminAccOpenApplyULCsv.getTelephoneNumber(0));
            assertEquals("4343",l_adminAccOpenApplyULCsv.getTelephoneNumber(1));
            assertEquals("4343",l_adminAccOpenApplyULCsv.getTelephoneNumber(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetMobileAreaCode() {
        final String STR_METHOD_NAME ="testGetMobileAreaCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("132344",l_adminAccOpenApplyULCsv.getMobileAreaCode(0));
            assertEquals("132344",l_adminAccOpenApplyULCsv.getMobileAreaCode(1));
            assertEquals("132344",l_adminAccOpenApplyULCsv.getMobileAreaCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetMobileExchangeNumber() {
        final String STR_METHOD_NAME ="testGetMobileExchangeNumber()";
         log.entering(STR_METHOD_NAME);
            assertEquals("5566",l_adminAccOpenApplyULCsv.getMobileExchangeNumber(0));
            assertEquals("5566",l_adminAccOpenApplyULCsv.getMobileExchangeNumber(1));
            assertEquals("5566",l_adminAccOpenApplyULCsv.getMobileExchangeNumber(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetMobileNumber() {
        final String STR_METHOD_NAME ="testGetMobileNumber()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2231",l_adminAccOpenApplyULCsv.getMobileNumber(0));
            assertEquals("2231",l_adminAccOpenApplyULCsv.getMobileNumber(1));
            assertEquals("2231",l_adminAccOpenApplyULCsv.getMobileNumber(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetOccupationDiv() {
        final String STR_METHOD_NAME ="testGetOccupationDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("82",l_adminAccOpenApplyULCsv.getOccupationDiv(0));
            assertEquals("82",l_adminAccOpenApplyULCsv.getOccupationDiv(1));
            assertEquals("82",l_adminAccOpenApplyULCsv.getOccupationDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetOffice() {
        final String STR_METHOD_NAME ="testGetOffice()";
         log.entering(STR_METHOD_NAME);
            assertEquals("日本中央競馬会（ＪＲＡ）",l_adminAccOpenApplyULCsv.getOffice(0));
            assertEquals("日本中央競馬会（ＪＲＡ）",l_adminAccOpenApplyULCsv.getOffice(1));
            assertEquals("日本中央競馬会（ＪＲＡ）",l_adminAccOpenApplyULCsv.getOffice(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetOfficeZipCode() {
        final String STR_METHOD_NAME ="testGetOfficeZipCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2222222",l_adminAccOpenApplyULCsv.getOfficeZipCode(0));
            assertEquals("2222222",l_adminAccOpenApplyULCsv.getOfficeZipCode(1));
            assertEquals("2222222",l_adminAccOpenApplyULCsv.getOfficeZipCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetOfficeAddress() {
        final String STR_METHOD_NAME ="testGetOfficeAddress()";
         log.entering(STR_METHOD_NAME);
            assertEquals("東京都港区西新橋１丁目１番１９号",l_adminAccOpenApplyULCsv.getOfficeAddress(0));
            assertEquals("東京都港区西新橋１丁目１番１９号",l_adminAccOpenApplyULCsv.getOfficeAddress(1));
            assertEquals("東京都港区西新橋１丁目１番１９号",l_adminAccOpenApplyULCsv.getOfficeAddress(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetOfficeTelephone1() {
        final String STR_METHOD_NAME ="testGetOfficeTelephone1()";
         log.entering(STR_METHOD_NAME);
            assertEquals("222333",l_adminAccOpenApplyULCsv.getOfficeTelephone1(0));
            assertEquals("222333",l_adminAccOpenApplyULCsv.getOfficeTelephone1(1));
            assertEquals("222333",l_adminAccOpenApplyULCsv.getOfficeTelephone1(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetOfficeTelephone2() {
        final String STR_METHOD_NAME ="testGetOfficeTelephone2()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2323",l_adminAccOpenApplyULCsv.getOfficeTelephone2(0));
            assertEquals("2323",l_adminAccOpenApplyULCsv.getOfficeTelephone2(1));
            assertEquals("2323",l_adminAccOpenApplyULCsv.getOfficeTelephone2(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetOfficeTelephone3() {
        final String STR_METHOD_NAME ="testGetOfficeTelephone3()";
         log.entering(STR_METHOD_NAME);
            assertEquals("3232",l_adminAccOpenApplyULCsv.getOfficeTelephone3(0));
            assertEquals("3232",l_adminAccOpenApplyULCsv.getOfficeTelephone3(1));
            assertEquals("3232",l_adminAccOpenApplyULCsv.getOfficeTelephone3(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetDepartment() {
        final String STR_METHOD_NAME ="testGetDepartment()";
         log.entering(STR_METHOD_NAME);
            assertEquals("栗東",l_adminAccOpenApplyULCsv.getDepartment(0));
            assertEquals("栗東",l_adminAccOpenApplyULCsv.getDepartment(1));
            assertEquals("栗東",l_adminAccOpenApplyULCsv.getDepartment(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetPost() {
        final String STR_METHOD_NAME ="testGetPost()";
         log.entering(STR_METHOD_NAME);
            assertEquals("役職名",l_adminAccOpenApplyULCsv.getPost(0));
            assertEquals("役職名",l_adminAccOpenApplyULCsv.getPost(1));
            assertEquals("役職名",l_adminAccOpenApplyULCsv.getPost(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetHouseHolderFamilyRelation() {
        final String STR_METHOD_NAME ="testGetHouseHolderFamilyRelation()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2",l_adminAccOpenApplyULCsv.getHouseHolderFamilyRelation(0));
            assertEquals("2",l_adminAccOpenApplyULCsv.getHouseHolderFamilyRelation(1));
            assertEquals("2",l_adminAccOpenApplyULCsv.getHouseHolderFamilyRelation(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetHouseHolder() {
        final String STR_METHOD_NAME ="testGetHouseHolder()";
         log.entering(STR_METHOD_NAME);
            assertEquals("坂本　@龍一",l_adminAccOpenApplyULCsv.getHouseHolder(0));
            assertEquals("坂本　@龍一",l_adminAccOpenApplyULCsv.getHouseHolder(1));
            assertEquals("坂本　@龍一",l_adminAccOpenApplyULCsv.getHouseHolder(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetHouseHolderOffice() {
        final String STR_METHOD_NAME ="testGetHouseHolderOffice()";
         log.entering(STR_METHOD_NAME);
            assertEquals("世帯主勤務先名称",l_adminAccOpenApplyULCsv.getHouseHolderOffice(0));
            assertEquals("世帯主勤務先名称",l_adminAccOpenApplyULCsv.getHouseHolderOffice(1));
            assertEquals("世帯主勤務先名称",l_adminAccOpenApplyULCsv.getHouseHolderOffice(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetHouseHolderPost() {
        final String STR_METHOD_NAME ="testGetHouseHolderPost()";
         log.entering(STR_METHOD_NAME);
            assertEquals("世帯主役職名",l_adminAccOpenApplyULCsv.getHouseHolderPost(0));
            assertEquals("世帯主役職名",l_adminAccOpenApplyULCsv.getHouseHolderPost(1));
            assertEquals("世帯主役職名",l_adminAccOpenApplyULCsv.getHouseHolderPost(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetTransfer() {
        final String STR_METHOD_NAME ="testGetTransfer()";
         log.entering(STR_METHOD_NAME);
            assertEquals("5",l_adminAccOpenApplyULCsv.getTransfer(0));
            assertEquals("5",l_adminAccOpenApplyULCsv.getTransfer(1));
            assertEquals("5",l_adminAccOpenApplyULCsv.getTransfer(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetFinInstitutionCode() {
        final String STR_METHOD_NAME ="testGetFinInstitutionCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2121",l_adminAccOpenApplyULCsv.getFinInstitutionCode(0));
            assertEquals("2121",l_adminAccOpenApplyULCsv.getFinInstitutionCode(1));
            assertEquals("2121",l_adminAccOpenApplyULCsv.getFinInstitutionCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetFinInstitutionName() {
        final String STR_METHOD_NAME ="testGetFinInstitutionName()";
         log.entering(STR_METHOD_NAME);
            assertEquals("りそな銀行",l_adminAccOpenApplyULCsv.getFinInstitutionName(0));
            assertEquals("りそな銀行",l_adminAccOpenApplyULCsv.getFinInstitutionName(1));
            assertEquals("りそな銀行",l_adminAccOpenApplyULCsv.getFinInstitutionName(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetFinBranchCode() {
        final String STR_METHOD_NAME ="testGetFinBranchCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("051",l_adminAccOpenApplyULCsv.getFinBranchCode(0));
            assertEquals("051",l_adminAccOpenApplyULCsv.getFinBranchCode(1));
            assertEquals("051",l_adminAccOpenApplyULCsv.getFinBranchCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetFinBranchName() {
        final String STR_METHOD_NAME ="testGetFinBranchName()";
         log.entering(STR_METHOD_NAME);
            assertEquals("大阪営業部",l_adminAccOpenApplyULCsv.getFinBranchName(0));
            assertEquals("大阪営業部",l_adminAccOpenApplyULCsv.getFinBranchName(1));
            assertEquals("大阪営業部",l_adminAccOpenApplyULCsv.getFinBranchName(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetFinSaveDiv() {
        final String STR_METHOD_NAME ="testGetFinSaveDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2",l_adminAccOpenApplyULCsv.getFinSaveDiv(0));
            assertEquals("2",l_adminAccOpenApplyULCsv.getFinSaveDiv(1));
            assertEquals("2",l_adminAccOpenApplyULCsv.getFinSaveDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetFinAccountNo() {
        final String STR_METHOD_NAME ="testGetFinAccountNo()";
         log.entering(STR_METHOD_NAME);
            assertEquals("0190544",l_adminAccOpenApplyULCsv.getFinAccountNo(0));
            assertEquals("0190544",l_adminAccOpenApplyULCsv.getFinAccountNo(1));
            assertEquals("0190544",l_adminAccOpenApplyULCsv.getFinAccountNo(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetPostalSaveCode() {
        final String STR_METHOD_NAME ="testGetPostalSaveCode()";
         log.entering(STR_METHOD_NAME);
            assertEquals("12345",l_adminAccOpenApplyULCsv.getPostalSaveCode(0));
            assertEquals("12345",l_adminAccOpenApplyULCsv.getPostalSaveCode(1));
            assertEquals("12345",l_adminAccOpenApplyULCsv.getPostalSaveCode(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetPostalSaveNo() {
        final String STR_METHOD_NAME ="testGetPostalSaveNo()";
         log.entering(STR_METHOD_NAME);
            assertEquals("12345678",l_adminAccOpenApplyULCsv.getPostalSaveNo(0));
            assertEquals("12345678",l_adminAccOpenApplyULCsv.getPostalSaveNo(1));
            assertEquals("12345678",l_adminAccOpenApplyULCsv.getPostalSaveNo(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceEquityDiv() {
        final String STR_METHOD_NAME ="testGetExperienceEquityDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceEquityDiv(0));
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceEquityDiv(1));
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceEquityDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceMarginDiv() {
        final String STR_METHOD_NAME ="testGetExperienceMarginDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceMarginDiv(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceMarginDiv(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceMarginDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceBondDiv() {
        final String STR_METHOD_NAME ="testGetExperienceBondDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceBondDiv(0));
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceBondDiv(1));
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceBondDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceOptionsDiv() {
        final String STR_METHOD_NAME ="testGetExperienceOptionsDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceOptionsDiv(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceOptionsDiv(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceOptionsDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceFundSkDiv() {
        final String STR_METHOD_NAME ="testGetExperienceFundSkDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceFundSkDiv(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceFundSkDiv(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceFundSkDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceFundBdDiv() {
        final String STR_METHOD_NAME ="testGetExperienceFundBdDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("3",l_adminAccOpenApplyULCsv.getExperienceFundBdDiv(0));
            assertEquals("3",l_adminAccOpenApplyULCsv.getExperienceFundBdDiv(1));
            assertEquals("3",l_adminAccOpenApplyULCsv.getExperienceFundBdDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceFuturesDiv() {
        final String STR_METHOD_NAME ="testGetExperienceFuturesDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceFuturesDiv(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceFuturesDiv(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getExperienceFuturesDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceFEquityDiv() {
        final String STR_METHOD_NAME ="testGetExperienceFEquityDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceFEquityDiv(0));
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceFEquityDiv(1));
            assertEquals("2",l_adminAccOpenApplyULCsv.getExperienceFEquityDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetExperienceEtcDiv() {
        final String STR_METHOD_NAME ="testGetExperienceEtcDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("0",l_adminAccOpenApplyULCsv.getExperienceEtcDiv(0));
            assertEquals("0",l_adminAccOpenApplyULCsv.getExperienceEtcDiv(1));
            assertEquals("0",l_adminAccOpenApplyULCsv.getExperienceEtcDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInterestEquityFlag() {
        final String STR_METHOD_NAME ="testGetInterestEquityFlag()";
         log.entering(STR_METHOD_NAME);
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestEquityFlag(0));
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestEquityFlag(1));
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestEquityFlag(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInterestMarginFlag() {
        final String STR_METHOD_NAME ="testGetInterestMarginFlag()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestMarginFlag(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestMarginFlag(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestMarginFlag(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInterestBondFlag() {
        final String STR_METHOD_NAME ="testGetInterestBondFlag()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestBondFlag(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestBondFlag(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestBondFlag(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInterestFundFlag() {
        final String STR_METHOD_NAME ="testGetInterestFundFlag()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestFundFlag(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestFundFlag(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getInterestFundFlag(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInterestFuturesFlag() {
        final String STR_METHOD_NAME ="testGetInterestFuturesFlag()";
         log.entering(STR_METHOD_NAME);
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestFuturesFlag(0));
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestFuturesFlag(1));
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestFuturesFlag(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInterestOptionsFlag() {
        final String STR_METHOD_NAME ="testGetInterestOptionsFlag()";
         log.entering(STR_METHOD_NAME);
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestOptionsFlag(0));
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestOptionsFlag(1));
            assertEquals("0",l_adminAccOpenApplyULCsv.getInterestOptionsFlag(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAnnualIncomeDiv() {
        final String STR_METHOD_NAME ="testGetAnnualIncomeDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("6",l_adminAccOpenApplyULCsv.getAnnualIncomeDiv(0));
            assertEquals("6",l_adminAccOpenApplyULCsv.getAnnualIncomeDiv(1));
            assertEquals("6",l_adminAccOpenApplyULCsv.getAnnualIncomeDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAssetValueDiv() {
        final String STR_METHOD_NAME ="testGetAssetValueDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("6",l_adminAccOpenApplyULCsv.getAssetValueDiv(0));
            assertEquals("6",l_adminAccOpenApplyULCsv.getAssetValueDiv(1));
            assertEquals("6",l_adminAccOpenApplyULCsv.getAssetValueDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetSpecialAccEquity() {
        final String STR_METHOD_NAME ="testGetSpecialAccEquity()";
         log.entering(STR_METHOD_NAME);
            assertEquals("0",l_adminAccOpenApplyULCsv.getSpecialAccEquity(0));
            assertEquals("0",l_adminAccOpenApplyULCsv.getSpecialAccEquity(1));
            assertEquals("0",l_adminAccOpenApplyULCsv.getSpecialAccEquity(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInsiderFlag() {
        final String STR_METHOD_NAME ="testGetInsiderFlag()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getInsiderFlag(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getInsiderFlag(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getInsiderFlag(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetProductName() {
        final String STR_METHOD_NAME ="testGetProductName()";
         log.entering(STR_METHOD_NAME);
            assertEquals("ＪＲ東日本",l_adminAccOpenApplyULCsv.getProductName(0));
            assertEquals("ＪＲ東日本",l_adminAccOpenApplyULCsv.getProductName(1));
            assertEquals("ＪＲ東日本",l_adminAccOpenApplyULCsv.getProductName(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetTypeDiv() {
        final String STR_METHOD_NAME ="testGetTypeDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2",l_adminAccOpenApplyULCsv.getTypeDiv(0));
            assertEquals("2",l_adminAccOpenApplyULCsv.getTypeDiv(1));
            assertEquals("2",l_adminAccOpenApplyULCsv.getTypeDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetIncomeDormDiv() {
        final String STR_METHOD_NAME ="testGetIncomeDormDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("1",l_adminAccOpenApplyULCsv.getIncomeDormDiv(0));
            assertEquals("1",l_adminAccOpenApplyULCsv.getIncomeDormDiv(1));
            assertEquals("1",l_adminAccOpenApplyULCsv.getIncomeDormDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInsiderRelationDiv() {
        final String STR_METHOD_NAME ="testGetInsiderRelationDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("3",l_adminAccOpenApplyULCsv.getInsiderRelationDiv(0));
            assertEquals("3",l_adminAccOpenApplyULCsv.getInsiderRelationDiv(1));
            assertEquals("3",l_adminAccOpenApplyULCsv.getInsiderRelationDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetInvestPurposeDiv() {
        final String STR_METHOD_NAME ="testGetInvestPurposeDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("2",l_adminAccOpenApplyULCsv.getInvestPurposeDiv(0));
            assertEquals("2",l_adminAccOpenApplyULCsv.getInvestPurposeDiv(1));
            assertEquals("2",l_adminAccOpenApplyULCsv.getInvestPurposeDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetAppliMotivatDiv() {
        final String STR_METHOD_NAME ="testGetAppliMotivatDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("4",l_adminAccOpenApplyULCsv.getAppliMotivatDiv(0));
            assertEquals("4",l_adminAccOpenApplyULCsv.getAppliMotivatDiv(1));
            assertEquals("4",l_adminAccOpenApplyULCsv.getAppliMotivatDiv(2));
              log.exiting(STR_METHOD_NAME);
          }
        public void testGetIDConfirmDocDiv() {
        final String STR_METHOD_NAME ="testGetIDConfirmDocDiv()";
         log.entering(STR_METHOD_NAME);
            assertEquals("05",l_adminAccOpenApplyULCsv.getIDConfirmDocDiv(0));
            assertEquals("06",l_adminAccOpenApplyULCsv.getIDConfirmDocDiv(1));
            assertEquals("07",l_adminAccOpenApplyULCsv.getIDConfirmDocDiv(2));
              log.exiting(STR_METHOD_NAME);
          }

}
@
