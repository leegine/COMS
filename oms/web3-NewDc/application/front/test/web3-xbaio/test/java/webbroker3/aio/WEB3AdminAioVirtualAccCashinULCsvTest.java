head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioVirtualAccCashinULCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmRequest;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.EraParams;
import webbroker3.util.WEB3LogUtility;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

public class WEB3AdminAioVirtualAccCashinULCsvTest extends JunitTestBase
{

    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioVirtualAccCashinULCsvTest.class);

    WEB3AdminAioVirtualAccCashinULCsv l_cashinULCsv = null;

    public WEB3AdminAioVirtualAccCashinULCsvTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_cashinULCsv = new WEB3AdminAioVirtualAccCashinULCsv();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateDataRecord_Case001()
    {
        final String STR_METHOD_NAME = "testValidateDataRecord_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AdminAioVirtualAccCashinULConfirmRequest l_request =
                new WEB3AdminAioVirtualAccCashinULConfirmRequest();
            l_request.uploadFile = new String[]{
             "12345670w01070101071" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789"};
            l_cashinULCsv.validateDataRecord(0, l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02437, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateDataRecord_Case002()
    {
        final String STR_METHOD_NAME = "testValidateDataRecord_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AdminAioVirtualAccCashinULConfirmRequest l_request =
                new WEB3AdminAioVirtualAccCashinULConfirmRequest();
            l_request.uploadFile = new String[]{
             "12345670101070w01071" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789"};
            l_cashinULCsv.validateDataRecord(0, l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02437, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateDataRecord_Case003()
    {
        final String STR_METHOD_NAME = "testValidateDataRecord_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AdminAioVirtualAccCashinULConfirmRequest l_request =
                new WEB3AdminAioVirtualAccCashinULConfirmRequest();
            l_request.uploadFile = new String[]{
             "12345670101070101071" +
             "w1234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789"};
            l_request.financialInstitutionCode = "0001";
            
            l_cashinULCsv.validateDataRecord(0, l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02437, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateDataRecord_Case004()
    {
        final String STR_METHOD_NAME = "testValidateDataRecord_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AdminAioVirtualAccCashinULConfirmRequest l_request =
                new WEB3AdminAioVirtualAccCashinULConfirmRequest();
            l_request.uploadFile = new String[]{
             "12345670101370101071" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789"};
            l_cashinULCsv.validateDataRecord(0, l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02437, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateDataRecord_Case005()
    {
        final String STR_METHOD_NAME = "testValidateDataRecord_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AdminAioVirtualAccCashinULConfirmRequest l_request =
                new WEB3AdminAioVirtualAccCashinULConfirmRequest();
            l_request.uploadFile = new String[]{
             "12345670101070101371" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789" +
             "01234567890123456789"};
            l_cashinULCsv.validateDataRecord(0, l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02437, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public EraParams getEraParams()
    {
        EraParams l_eraParams = new EraParams();
        //年号区分    japanese_era_div    NUMBER    1    NotNull    1：明治、2：大正、3：昭和、4：平成、9：不明
        l_eraParams.setJapaneseEraDiv(1);
        //年号    japanese_era    VARCHAR2    20    NotNull    1：明治、2：大正、3：昭和、4：平成、9：不明
        l_eraParams.setJapaneseEra("1");
        //開始年(和暦)    start_year_jap    VARCHAR2    2    NotNull    和暦(YY)
        l_eraParams.setStartYearJap("01");
        //開始年(西暦)    start_year    VARCHAR2    4    NotNull    西暦(YYYY)
        l_eraParams.setStartYear("2008");
        //開始月日    start_date    VARCHAR2    4    NotNull    MMDD
        l_eraParams.setStartDate("0002");
        //終了年(和暦)    end_year_jap    VARCHAR2    2    NotNull    和暦(YY)
        l_eraParams.setEndYearJap("02");
        //終了年(西暦)    end_year    VARCHAR2    4    NotNull    西暦(YYYY)
        l_eraParams.setEndYear("2009");
        //終了月日    end_date    VARCHAR2    4    NotNull    MMDD
        l_eraParams.setEndDate("0102");
        //作成日時    created_timestamp    DATE        NotNull    DEFAULT sysdate
        l_eraParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日時    last_updated_timestamp    DATE        NotNull    DEFAULT sysdate
        l_eraParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_eraParams;
    }
}
@
