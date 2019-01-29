head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.30.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AdminAccInfoCampaignCommonTest_isChangeInfo.java;


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

import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignCommonTest_isChangeInfo extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignCommon.class);

    WEB3AdminAccInfoCampaignCommon l_adminAccInfoCampaignCommon =  new WEB3AdminAccInfoCampaignCommonForTest();

    String methodId = null;

    public WEB3AdminAccInfoCampaignCommonTest_isChangeInfo(String arg0)
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

    public void testIsChangeInfo_C0001()
    {
        methodId = "testIsChangeInfo_C0001()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0002()
    {
        methodId = "testIsChangeInfo_C0002()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1d";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0003()
    {
        methodId = "testIsChangeInfo_C0003()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2d";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0004()
    {
        methodId = "testIsChangeInfo_C0004()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3d";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0005()
    {
        methodId = "testIsChangeInfo_C0005()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0005()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4d";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0006()
    {
        methodId = "testIsChangeInfo_C0006()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0006()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "5d";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0007()
    {
        methodId = "testIsChangeInfo_C0007()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0007()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6d";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0022()
    {
        methodId = "testIsChangeInfo_C0022()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0022()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = null;
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0008()
    {
        methodId = "testIsChangeInfo_C0008()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0008()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7d";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0009()
    {
        methodId = "testIsChangeInfo_C0009()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0009()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8d";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0010()
    {
        methodId = "testIsChangeInfo_C0010()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0010()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9d";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0011()
    {
        methodId = "testIsChangeInfo_C0011()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0011()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10d";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0012()
    {
        methodId = "testIsChangeInfo_C0012()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0012()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11d";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0013()
    {
        methodId = "testIsChangeInfo_C0013()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0013()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"2"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0014()
    {
        methodId = "testIsChangeInfo_C0014()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0014()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12d";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0015()
    {
        methodId = "testIsChangeInfo_C0015()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0015()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13d";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0016()
    {
        methodId = "testIsChangeInfo_C0016()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0016()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0017()
    {
        methodId = "testIsChangeInfo_C0017()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0017()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0018()
    {
        methodId = "testIsChangeInfo_C0018()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0018()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14d";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0019()
    {
        methodId = "testIsChangeInfo_C0019()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0019()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14d";
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsChangeInfo_C0020()
    {
        methodId = "testIsChangeInfo_C0020()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0020()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray = {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14d";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(l_accInfoCampaignInfo);
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testIsChangeInfo_C0021()
    {
        methodId = "testIsChangeInfo_C0021()";
        final String STR_METHOD_NAME = " testIsChangeInfo_C0021()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            //実際メソッドをコール
            boolean l_blnResult = l_adminAccInfoCampaignCommon.isChangeInfo(null);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }


    private class WEB3AdminAccInfoCampaignCommonForTest extends WEB3AdminAccInfoCampaignCommon
    {
        public WEB3AccInfoCampaignInfo getCampaignCondition(String l_strCampaignId)throws WEB3BaseException
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            Calendar l_canlendar = Calendar.getInstance();

            l_accInfoCampaignInfo.campaignId = "1";
            l_accInfoCampaignInfo.campaignName = "2";
            l_accInfoCampaignInfo.institutionCode = "3";
            l_accInfoCampaignInfo.branchCode = "4";
            l_accInfoCampaignInfo.accountCode = "123456";
            l_accInfoCampaignInfo.accountName = "6";
            l_accInfoCampaignInfo.traderCode = "7";
            l_accInfoCampaignInfo.accountOpenDiv = "8";
            l_accInfoCampaignInfo.registType = "9";
            l_accInfoCampaignInfo.deleteFlag = "10";
            l_accInfoCampaignInfo.transactionDiv = "11";
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "12";
            l_accInfoCampaignInfo.accopenPassPeriodDay = "13";

            String[] l_strArray =
            {"1"};
            l_accInfoCampaignInfo.itemCode = l_strArray;

            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 2);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            l_accInfoCampaignInfo.targetPeriodFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.targetPeriodTo = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.collectRate = "14";
            l_accInfoCampaignInfo.accountOpenDateFrom = new Timestamp(l_canlendar.getTimeInMillis());
            l_accInfoCampaignInfo.accountOpenDateTo = new Timestamp(l_canlendar.getTimeInMillis());

            return l_accInfoCampaignInfo;
        }
    }
}
@
