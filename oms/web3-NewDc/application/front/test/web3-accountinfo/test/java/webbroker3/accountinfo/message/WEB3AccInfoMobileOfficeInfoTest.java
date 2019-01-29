head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoMobileOfficeInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.EraParams;
import webbroker3.util.WEB3LogUtility;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

public class WEB3AccInfoMobileOfficeInfoTest extends JunitTestBase
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeInfoTest.class);

    WEB3AccInfoMobileOfficeInfo l_info = null;

    public WEB3AccInfoMobileOfficeInfoTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_info = new WEB3AccInfoMobileOfficeInfo();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidate_Case001()
    {
        final String STR_METHOD_NAME = "testValidate_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            TestDBUtility.insertWithDel(l_eraParams);
            
            
            l_info.directorEraBorn = "1";
            l_info.directorBornDate = "010107";
            l_info.validate();
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case002()
    {
        final String STR_METHOD_NAME = "testValidate_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            TestDBUtility.insertWithDel(l_eraParams);
            
            
            l_info.directorEraBorn = "1";
            l_info.directorBornDate = "01w107";
            l_info.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02687, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case003()
    {
        final String STR_METHOD_NAME = "testValidate_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            TestDBUtility.insertWithDel(l_eraParams);
            
            l_info.directorEraBorn = "1";
            l_info.directorBornDate = "010137";
            l_info.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02687, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
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
