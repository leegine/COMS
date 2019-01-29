head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.30.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AccInfoCommissionCourseMasterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AccInfoCommissionCourseMasterTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/19 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommissionCourseMasterParams;
import webbroker3.accountinfo.data.CommissionCourseMasterRow;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoCommissionCourseMasterTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseMasterTest.class);
    public WEB3AccInfoCommissionCourseMasterTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //①@　@処理日時より計算する。
    //（変更申込締切指定日 == 00：毎日または01：毎週）の場合
    //②　@変更申込締切日時の計算
    //(計算結果 < 処理日時）の場合
    //（変更申込締切指定日 == 00：毎日）の場合、
    //処理日時(*1)の翌営業日（YYYYMMDD） + 変更申込締切時間（HHMMSS)
    public void testGetRegistEndTimestamp_C0001()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080819" + " 162245",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(l_date1.getTime()));
            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();

            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);
            
            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                "20080820 000000",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //①@　@処理日時より計算する。
    //（変更申込締切指定日 == 00：毎日または01：毎週）以外の場合
    //処理日時(*1)の年月（YYYYMM） + 変更申込締切指定日（DD） + 変更申込締切時間（HHMMSS）
    //②　@変更申込締切日時の計算
    //(計算結果 < 処理日時）の場合
    //（変更申込締切指定日 == 00：毎日 01：毎週）以外の場合、
    //処理日時(*1)の翌月（YYYYMM） + 変更申込締切指定日（DD） + 変更申込締切時間（HHMMSS）
    public void testGetRegistEndTimestamp_C0002()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080819" + " 162245",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp",
                        new Class[] {},
                        new Timestamp(l_date1.getTime()));

            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            l_commissionCourseMasterParams.setRegistEndDaySpec("3");

            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);

            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                "20080903  000000",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //②　@変更申込締切日時の計算
    //（①@の計算結果 >= 処理日時）の場合
    //①@の計算結果をDate型に変換した値を返却する。
    public void testGetRegistEndTimestamp_C0003()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080819" + " 162245",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp",
                        new Class[] {},
                        new Timestamp(l_date1.getTime()));

            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            l_commissionCourseMasterParams.setRegistEndDaySpec("00");
            l_commissionCourseMasterParams.setRegistEndTime("235959");

            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);
            
            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                "20080819 235959",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //①@　@処理日時より計算する。
    //（変更申込締切指定日 == 00：毎日または01：毎週）の場合
    //②　@変更申込締切日時の計算
    //(計算結果 < 処理日時）の場合
    //（変更申込締切指定日 == 01：毎週）の場合、
    //処理日時(*1)の翌週第一営業日（YYYYMMDD）+ 変更申込締切時間（HHMMSS）
    public void testGetRegistEndTimestamp_C0004()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080819" + " 162245",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp",
                        new Class[] {},
                        new Timestamp(l_date1.getTime()));

            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            l_commissionCourseMasterParams.setRegistEndDaySpec("01");
            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);

            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                "20080825 000000",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //処理日時(*1)
    //変更申込締切指定日 == 00：毎日）の場合かつ、TradingSystem.getSystemTimestamp()で
    //取得した日付が非営業日の場合は翌営業日を算出する。
    public void testGetRegistEndTimestamp_C0005()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080817" + " 162245",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp",
                        new Class[] {},
                        new Timestamp(l_date1.getTime()));

            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);

            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                 "20080818 000000",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //＜引数.信用口座開設フラグ == true の場合＞
    //以下の条件で、委託手数料コースマスタテーブルを検索する。
    //証券会社コード = 引数.証券会社コード
    //手数料商品コード = 引数.手数料商品コード
    //手数料区分 = 0 または 1（現物または信用
    public void testGetHandlingPossibleCommissionCourse_C0001()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleCommissionCourse_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            CommissionCourseMasterParams commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            commissionCourseMasterParams.setCommissionDiv("1");
            TestDBUtility.insertWithDel(commissionCourseMasterParams);

            WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse("0D", "10", true);
            CommissionCourseMasterParams L_params = (CommissionCourseMasterParams)l_commissionCourseMasters[0].getDataSourceObject();
            assertEquals(L_params.getCommissionDiv(), "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //＜引数.信用口座開設フラグ == false の場合＞
    //以下の条件で、委託手数料コースマスタテーブルを検索する。
    //証券会社コード = 引数.証券会社コード
    //手数料商品コード = 引数.手数料商品コード
    //手数料区分 = 0 （現物）
    public void testGetHandlingPossibleCommissionCourse_C0002()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleCommissionCourse_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            CommissionCourseMasterParams commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            TestDBUtility.insertWithDel(commissionCourseMasterParams);

            WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse("0D", "10", false);
            CommissionCourseMasterParams L_params = (CommissionCourseMasterParams)l_commissionCourseMasters[0].getDataSourceObject();
            assertEquals(L_params.getCommissionDiv(), "0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //委託手数料コースマスタテーブルを検索record is null
    public void testGetHandlingPossibleCommissionCourse_C0003()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleCommissionCourse_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            CommissionCourseMasterParams commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            TestDBUtility.insertWithDel(commissionCourseMasterParams);

            WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse("00", "10", false);
            assertNull(l_commissionCourseMasters);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

}
@
