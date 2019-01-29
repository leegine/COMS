head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.30.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AdminAccInfoCampaignCommonTest_cyp.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン共通サービスImpl(WEB3AdminAccInfoCampaignCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/07 崔遠鵬 (中訊) 新規作成
*/
package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import test.util.TestDBUtility;

import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignCommonTest_cyp extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignCommon.class);

    WEB3AdminAccInfoCampaignCommon l_adminAccInfoCampaignCommon =  new WEB3AdminAccInfoCampaignCommonForTest();

    String methodId = null;

    public WEB3AdminAccInfoCampaignCommonTest_cyp(String arg0)
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

    public void test_validateTargetPeriod_C0001()
    {
        methodId = "test_validateTargetPeriod_C0001";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2007, 3, 1));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);

            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals("過去期間のデータは変更・削除できません。", l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);   
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0002()
    {
        methodId = "test_validateTargetPeriod_C0002";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2007, 1, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 1, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);

            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            String l_strResult = l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "");
            assertEquals("1", l_strResult);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0003()
    {
        methodId = "test_validateTargetPeriod_C0003";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2007, 1, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 1, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);

            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            String l_strResult = l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "");
            assertEquals("1", l_strResult);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0004()
    {
        methodId = "test_validateTargetPeriod_C0004";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2007, 1, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 1, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);
            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            String l_strResult = l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "");
            assertEquals("1", l_strResult);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0005()
    {
        methodId = "test_validateTargetPeriod_C0005";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0005()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2007, 1, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 1, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);
            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            String l_strResult = l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "");
            assertEquals("1", l_strResult);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0006()
    {
        methodId = "test_validateTargetPeriod_C0006";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0006()";
        log.info(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2008,3, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2008, 3, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2008, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2008, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);
            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.MONTH, 4);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
            TradingSystem ts =finApp.getTradingSystem();
            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals("過去期間のデータは変更・削除できません。", l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);   
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0007()
    {
        methodId = "test_validateTargetPeriod_C0007";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0007()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            String l_strResult = l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "");
            assertEquals("1", l_strResult);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0008()
    {
        methodId = "test_validateTargetPeriod_C0008";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0008()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                this.getTimeStamp(2007, 1, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 1, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);

            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals("口座開設日Toが過去日付です。", l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0009()
    {
        methodId = "test_validateTargetPeriod_C0009";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0009()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                this.getTimeStamp(2007, 1, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 1, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);

            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals("口座開設日Fromが過去日付のデータは変更できません。", l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0010()
    {
        methodId = "test_validateTargetPeriod_C0010";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0010()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2007, 1, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 1, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);

            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.accountOpenDateTo = null;
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals("口座開設日From は現在より過去日付には設定できません。", l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0011()
    {
        methodId = "test_validateTargetPeriod_C0011";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0011()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2007, 1, 2));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 1, 2));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);

            //スタチィクメソッドの準備
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.accountOpenDateTo = null;
            l_accInfoCampaignInfo.accountOpenDateFrom = null;
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals("キャンペーン実施中の対象期間From 及び 口座開設日Fromの変更はできません。", l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateTargetPeriod_C0012()
    {
        methodId = "test_validateTargetPeriod_C0012";
        final String STR_METHOD_NAME = " test_validateTargetPeriod_C0012()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
        new Class[] {},
        this.getTimeStamp(2007, 3, 1));

        try
        {
            CalendarParams l_canlendarParams = new CalendarParams();
            TestDBUtility.deleteAllAndCommit(l_canlendarParams.getRowType());
            l_canlendarParams.setHoliday(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setBizDateType("0");
            l_canlendarParams.setCreatedTimestamp(this.getTimeStamp(2007, 3, 1));
            l_canlendarParams.setLastUpdatedTimestamp(this.getTimeStamp(2007, 3, 1));
            TestDBUtility.insertWithDelAndCommit(l_canlendarParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

            //実際のメソッドをコール
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo =  new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.accountOpenDateTo = null;
            l_accInfoCampaignInfo.accountOpenDateFrom = null;
            String l_strResult = l_adminAccInfoCampaignCommon.validateTargetPeriod(l_accInfoCampaignInfo, "1");
            assertEquals("0", l_strResult);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
       }
        log.exiting(STR_METHOD_NAME);
    }
    
    private class WEB3AdminAccInfoCampaignCommonForTest extends WEB3AdminAccInfoCampaignCommon
    {
        public WEB3AccInfoCampaignInfo getCampaignCondition(String l_strCampaignId) throws WEB3BaseException
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();

            Calendar l_canlendar = Calendar.getInstance();
            if ("test_validateTargetPeriod_C0001".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
                l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());                
            }
            if ("test_validateTargetPeriod_C0002".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
                l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 5);
                l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            }
            if ("test_validateTargetPeriod_C0003".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_accInfoCampaignInfo.targetPeriodFrom = null;

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 5);
                l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            }
            if ("test_validateTargetPeriod_C0004".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_accInfoCampaignInfo.targetPeriodTo = null;

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
                l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            }
            if ("test_validateTargetPeriod_C0005".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_accInfoCampaignInfo.targetPeriodTo = null;
                l_accInfoCampaignInfo.targetPeriodFrom = null;
            }

            if ("test_validateTargetPeriod_C0006".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = "1";
                l_accInfoCampaignInfo.accopenPassPeriodDay = "1";
                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 6);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
                l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());
            }
            if ("test_validateTargetPeriod_C0007".equals(methodId))
            {
                l_accInfoCampaignInfo.targetPeriodTo = null;
                l_accInfoCampaignInfo.targetPeriodFrom = null;

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 1);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
                l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 3);
                l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());
                l_accInfoCampaignInfo.accopenPassPeriodMonth = "1";
                l_accInfoCampaignInfo.accopenPassPeriodDay = "1";
            }
            if ("test_validateTargetPeriod_C0008".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_accInfoCampaignInfo.targetPeriodTo = null;
                l_accInfoCampaignInfo.targetPeriodFrom = null;

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
                l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            }
            if ("test_validateTargetPeriod_C0009".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_accInfoCampaignInfo.targetPeriodTo = null;
                l_accInfoCampaignInfo.targetPeriodFrom = null;

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
                l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            }
            if ("test_validateTargetPeriod_C0010".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_accInfoCampaignInfo.targetPeriodTo = null;
                l_accInfoCampaignInfo.targetPeriodFrom = null;

                l_canlendar.set(Calendar.YEAR, 2007);
                l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
                l_canlendar.set(Calendar.DAY_OF_YEAR, 3);
                l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            }

            if ("test_validateTargetPeriod_C0011".equals(methodId)) 
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = null;
                l_accInfoCampaignInfo.accopenPassPeriodDay = null;

                l_accInfoCampaignInfo.targetPeriodTo = null;
                l_accInfoCampaignInfo.targetPeriodFrom = null;
            }

            if ("test_validateTargetPeriod_C0012".equals(methodId))
            {
                l_accInfoCampaignInfo.accopenPassPeriodMonth = "a";
            }
            return l_accInfoCampaignInfo;
        }

        public void validateInputTargetPeriod(WEB3AccInfoCampaignInfo l_changeAfterInfo, String updateFlag)
        throws WEB3BaseException
        {
            return;
        }
    }
    /**Timestamp*/
    private Timestamp getTimeStamp(int year, int month, int day)
    {
        Calendar l_canlendar = Calendar.getInstance();
        l_canlendar.set(Calendar.YEAR, year);
        l_canlendar.set(Calendar.DAY_OF_MONTH, month);
        l_canlendar.set(Calendar.DAY_OF_YEAR, day);
        Timestamp l_tsTimeStamp = new Timestamp(l_canlendar.getTimeInMillis());
        
        return l_tsTimeStamp;
    }
}
@
