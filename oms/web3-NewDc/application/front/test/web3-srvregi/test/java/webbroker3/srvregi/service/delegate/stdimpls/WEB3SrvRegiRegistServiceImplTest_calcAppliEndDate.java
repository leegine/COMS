head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiRegistServiceImplTest_calcAppliEndDate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3SrvRegiRegistServiceImplTest_calcAppliEndDate.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/14 崔遠鵬(中訊) 新規作成 仕様変更モデルNo.XXX
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiChargeParams;
import webbroker3.srvregi.data.SrvRegiChargeRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiRegistServiceImplTest_calcAppliEndDate extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3SrvRegiRegistServiceImplTest_calcAppliEndDate.class);

    WEB3SrvRegiRegistServiceImpl impl = new WEB3SrvRegiRegistServiceImpl();

    public WEB3SrvRegiRegistServiceImplTest_calcAppliEndDate(String arg0)
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

    public void testCalcAppliEndDate01()
    {
        final String STR_METHOD_NAME = "testCalcAppliEndDate01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);
            l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationRow();
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("A01");
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate("20070614","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsResult = impl.calcAppliEndDate("0D", "A01", "1234", "1234567",
                new Timestamp(l_calendar.getTimeInMillis()), 0L, "","");
            assertEquals(WEB3DateUtility.compareToDay(l_tsResult, WEB3DateUtility.getDate("20070714", "yyyyMMdd")),0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcAppliEndDate02()
    {
        final String STR_METHOD_NAME = "testCalcAppliEndDate02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);
            l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationRow();
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("A01");
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate("20070614","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);
            l_srvRegiChargeParams = TestDBUtility.getSrvRegiChargeRow();
            l_srvRegiChargeParams.setInstitutionCode("0D");
            l_srvRegiChargeParams.setConsecutiveNumbers(0L);
            TestDBUtility.insertWithDel(l_srvRegiChargeParams);
            
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            Timestamp l_tsResult = impl.calcAppliEndDate("0D", "A01", "1234", "1234567",
                new Timestamp(l_calendar.getTimeInMillis()), 0L, "", null);
            assertEquals(WEB3DateUtility.compareToDay(l_tsResult, WEB3DateUtility.getDate("20070615", "yyyyMMdd")),0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcAppliEndDate03()
    {
        final String STR_METHOD_NAME = "testCalcAppliEndDate03()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getInitializeAppliDiv",
            new Class[] {String.class, String.class, String.class, String.class},
            "1");
        try
        {
            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);
            l_srvRegiChargeParams = TestDBUtility.getSrvRegiChargeRow();
            l_srvRegiChargeParams.setInstitutionCode("0D");
            l_srvRegiChargeParams.setConsecutiveNumbers(0L);
            TestDBUtility.insertWithDel(l_srvRegiChargeParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            Timestamp l_tsResult = impl.calcAppliEndDate("0D", "A01", "1234", "1234567",
                new Timestamp(l_calendar.getTimeInMillis()), 0L, "", null);
            assertEquals(WEB3DateUtility.compareToDay(l_tsResult, WEB3DateUtility.getDate("20070613", "yyyyMMdd")),0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcAppliEndDate04()
    {
        final String STR_METHOD_NAME = "testCalcAppliEndDate04()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getInitializeAppliDiv",
            new Class[] {String.class, String.class, String.class, String.class},
            "0");
        try
        {
            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            l_srvRegiSetupParams.setTrialPeriod(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);
            l_srvRegiChargeParams = TestDBUtility.getSrvRegiChargeRow();
            l_srvRegiChargeParams.setInstitutionCode("0D");
            l_srvRegiChargeParams.setConsecutiveNumbers(0L);
            TestDBUtility.insertWithDel(l_srvRegiChargeParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            Timestamp l_tsResult = impl.calcAppliEndDate("0D", "A01", "1234", "1234567",
                new Timestamp(l_calendar.getTimeInMillis()), 0L, "", null);
            assertEquals(WEB3DateUtility.compareToDay(l_tsResult, WEB3DateUtility.getDate("20080613", "yyyyMMdd")),0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

//    public void testCalcAppliEndDate05()
//    {
//        final String STR_METHOD_NAME = "testCalcAppliEndDate05()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
//            "getInitializeAppliDiv",
//            new Class[] {String.class, String.class, String.class, String.class},
//            "1");
//        try
//        {
//            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
//            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
//            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
//
//            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);
//
//            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
//            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
//            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
//            l_srvRegiSetupParams.setFreeCoverageLength(1);
//            l_srvRegiSetupParams.setTrialPeriod(1);
//            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
//
//            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
//            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
//            l_srvAppliAttributeParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
//
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 6-1, 14);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);
//
//            l_calendar.set(2007, 6-1, 14);
//            Timestamp l_tsResult = impl.calcAppliEndDate("0D", "A01", "1234", "1234567",
//                new Timestamp(l_calendar.getTimeInMillis()), 0L, "", "");
//            assertEquals(WEB3DateUtility.compareToDay(l_tsResult, WEB3DateUtility.getDate("20080206", "yyyyMMdd")),0);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    
    public void testCalcAppliEndDateC0001()
    {
        final String STR_METHOD_NAME = "testCalcAppliEndDateC0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);
            l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationRow();
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("A01");
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate("20070614","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            l_srvRegiSetupParams.setTrialPeriodDiv("1");
            l_srvRegiSetupParams.setTrialPeriod(10);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);
            l_srvRegiChargeParams = TestDBUtility.getSrvRegiChargeRow();
            l_srvRegiChargeParams.setInstitutionCode("0D");
            l_srvRegiChargeParams.setConsecutiveNumbers(0L);
            TestDBUtility.insertWithDel(l_srvRegiChargeParams);
            
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            Timestamp l_tsResult = impl.calcAppliEndDate("0D", "A01", "1234", "1234567",
                new Timestamp(l_calendar.getTimeInMillis()), 0L, "", null);
            assertEquals(WEB3DateUtility.compareToDay(l_tsResult, WEB3DateUtility.getDate("20170614", "yyyyMMdd")),0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliEndDateC0002()
    {
        final String STR_METHOD_NAME = "testCalcAppliEndDateC0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);
            l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationRow();
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("A01");
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate("20070614","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            l_srvRegiSetupParams.setTrialPeriodDiv("2");
            l_srvRegiSetupParams.setTrialPeriod(10);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);
            l_srvRegiChargeParams = TestDBUtility.getSrvRegiChargeRow();
            l_srvRegiChargeParams.setInstitutionCode("0D");
            l_srvRegiChargeParams.setConsecutiveNumbers(0L);
            TestDBUtility.insertWithDel(l_srvRegiChargeParams);
            
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            Timestamp l_tsResult = impl.calcAppliEndDate("0D", "A01", "1234", "1234567",
                new Timestamp(l_calendar.getTimeInMillis()), 0L, "", null);
            assertEquals(WEB3DateUtility.compareToDay(l_tsResult, WEB3DateUtility.getDate("20080414", "yyyyMMdd")),0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcAppliEndDateC0003()
    {
        final String STR_METHOD_NAME = "testCalcAppliEndDateC0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);
            l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationRow();
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("A01");
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate("20070614","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setFreeCoverageLength(10);
            l_srvRegiSetupParams.setTrialPeriodDiv("3");
            l_srvRegiSetupParams.setTrialPeriod(10);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);
            l_srvRegiChargeParams = TestDBUtility.getSrvRegiChargeRow();
            l_srvRegiChargeParams.setInstitutionCode("0D");
            l_srvRegiChargeParams.setConsecutiveNumbers(0L);
            TestDBUtility.insertWithDel(l_srvRegiChargeParams);
            
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            Timestamp l_tsResult = impl.calcAppliEndDate("0D", "A01", "1234", "1234567",
                new Timestamp(l_calendar.getTimeInMillis()), 0L, "", null);
            assertEquals(WEB3DateUtility.compareToDay(l_tsResult, WEB3DateUtility.getDate("20070624", "yyyyMMdd")),0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
}
@
