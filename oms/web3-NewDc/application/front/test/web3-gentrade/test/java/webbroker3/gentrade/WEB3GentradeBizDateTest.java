head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.21.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBizDateTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3GentradeBizDateTest extends TestBaseForMock
{
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBizDateTest.class);

    public WEB3GentradeBizDateTest(String arg0)
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

    public void testCalcPTSBizDate_0001()
    {
        String STR_METHOD_NAME = "testCalcPTSBizDate_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            CalendarParams l_calendarParams = this.getCalendarRow();
            TestDBUtility.deleteAll(l_calendarParams.getRowType());
            TestDBUtility.insertWithDel(l_calendarParams);
            TestDBUtility.commit();
            Date l_date = new SimpleDateFormat("yyyyMMdd").parse("20071217");
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp((new Date()).getTime()));
            l_gentradeBizDate.calcPTSBizDate(new Timestamp(l_date.getTime()), 0);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
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
    
    public void testCalcPTSBizDate_0002()
    {
        String STR_METHOD_NAME = "testCalPTSBizDate_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            CalendarParams l_calendarParams = this.getCalendarRow();
            TestDBUtility.deleteAll(l_calendarParams.getRowType());
            TestDBUtility.insertWithDel(l_calendarParams);

            
            Date l_date = new SimpleDateFormat("yyyyMMdd").parse("20071213");
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp((new Date()).getTime()));
            Date l_runDate = l_gentradeBizDate.calcPTSBizDate(new Timestamp(l_date.getTime()), 3);
            
            Date l_expdate = new SimpleDateFormat("yyyyMMdd").parse("20071218");
            assertEquals(l_expdate.getTime(), l_runDate.getTime());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcPTSBizDate_0003()
    {
        String STR_METHOD_NAME = "testCalPTSBizDate_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            CalendarParams l_calendarParams = this.getCalendarRow();
            l_calendarParams.setHoliday(new SimpleDateFormat("yyyyMMdd").parse("20071214"));
            TestDBUtility.deleteAll(l_calendarParams.getRowType());
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_date = new SimpleDateFormat("yyyyMMdd").parse("20071213");
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp((new Date()).getTime()));
            Date l_runDate = l_gentradeBizDate.calcPTSBizDate(new Timestamp(l_date.getTime()), 3);
            
            Date l_expdate = new SimpleDateFormat("yyyyMMdd").parse("20071218");
            assertEquals(l_expdate.getTime(), l_runDate.getTime());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcPTSBizDate_0004()
    {
        String STR_METHOD_NAME = "testCalPTSBizDate_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            CalendarParams l_calendarParams = this.getCalendarRow();
            l_calendarParams.setHoliday(new SimpleDateFormat("yyyyMMdd").parse("20071220"));
            TestDBUtility.deleteAll(l_calendarParams.getRowType());
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_date = new SimpleDateFormat("yyyyMMdd").parse("20071213");
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp((new Date()).getTime()));
            Date l_runDate = l_gentradeBizDate.calcPTSBizDate(new Timestamp(l_date.getTime()), 3);
            
            Date l_expdate = new SimpleDateFormat("yyyyMMdd").parse("20071217");
            assertEquals(l_expdate.getTime(), l_runDate.getTime());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcPTSBizDate_0005()
    {
        String STR_METHOD_NAME = "testCalPTSBizDate_0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            CalendarParams l_calendarParams = this.getCalendarRow();
            TestDBUtility.deleteAll(l_calendarParams.getRowType());
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_date = new SimpleDateFormat("yyyyMMdd").parse("20071213");
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp((new Date()).getTime()));
            Date l_runDate = l_gentradeBizDate.calcPTSBizDate(new Timestamp(l_date.getTime()), -3);
            
            Date l_expdate = new SimpleDateFormat("yyyyMMdd").parse("20071210");
            assertEquals(l_expdate.getTime(), l_runDate.getTime());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcPTSBizDate_0006()
    {
        String STR_METHOD_NAME = "testCalPTSBizDate_0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            CalendarParams l_calendarParams = this.getCalendarRow();
            l_calendarParams.setHoliday(new SimpleDateFormat("yyyyMMdd").parse("20071211"));
            TestDBUtility.deleteAll(l_calendarParams.getRowType());
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_date = new SimpleDateFormat("yyyyMMdd").parse("20071213");
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp((new Date()).getTime()));
            Date l_runDate = l_gentradeBizDate.calcPTSBizDate(new Timestamp(l_date.getTime()), -5);
            
            Date l_expdate = new SimpleDateFormat("yyyyMMdd").parse("20071207");
            assertEquals(l_expdate.getTime(), l_runDate.getTime());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcPTSBizDate_0007()
    {
        String STR_METHOD_NAME = "testCalPTSBizDate_0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            CalendarParams l_calendarParams = this.getCalendarRow();
            l_calendarParams.setHoliday(new SimpleDateFormat("yyyyMMdd").parse("20071207"));
            TestDBUtility.deleteAll(l_calendarParams.getRowType());
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_date = new SimpleDateFormat("yyyyMMdd").parse("20071213");
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp((new Date()).getTime()));
            Date l_runDate = l_gentradeBizDate.calcPTSBizDate(new Timestamp(l_date.getTime()), -5);
            
            Date l_expdate = new SimpleDateFormat("yyyyMMdd").parse("20071206");
            assertEquals(l_expdate.getTime(), l_runDate.getTime());
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
     * getèTèââcã∆ì˙
     * åvéZäÓèÄì˙à◊é¸1ÅB
     * 
     */
    public void testGetWeekStartBizDateCase0001()
    {
        final String STR_METHOD_NAME = "testGetWeekStartBizDateCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datBiz = WEB3DateUtility.getDate("20080818", "yyyyMMdd");
            
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp(l_datBiz.getTime()));
            
            Date l_datWeekStartBizDate = l_gentradeBizDate.getWeekStartBizDate();
            
            assertEquals("20080818", WEB3DateUtility.formatDate(l_datWeekStartBizDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * getèTèââcã∆ì˙
     * åvéZäÓèÄì˙à◊é¸2ÅB
     * 
     */
    public void testGetWeekStartBizDateCase0002()
    {
        final String STR_METHOD_NAME = "testGetWeekStartBizDateCase0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datBiz = WEB3DateUtility.getDate("20080819", "yyyyMMdd");
            
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp(l_datBiz.getTime()));
            
            Date l_datWeekStartBizDate = l_gentradeBizDate.getWeekStartBizDate();
            
            assertEquals("20080818", WEB3DateUtility.formatDate(l_datWeekStartBizDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * getèTèââcã∆ì˙
     * åvéZäÓèÄì˙à◊é¸3ÅB
     * 
     */
    public void testGetWeekStartBizDateCase0003()
    {
        final String STR_METHOD_NAME = "testGetWeekStartBizDateCase0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datBiz = WEB3DateUtility.getDate("20080820", "yyyyMMdd");
            
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp(l_datBiz.getTime()));
            
            Date l_datWeekStartBizDate = l_gentradeBizDate.getWeekStartBizDate();
            
            assertEquals("20080818", WEB3DateUtility.formatDate(l_datWeekStartBizDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * getèTèââcã∆ì˙
     * åvéZäÓèÄì˙à◊é¸4ÅB
     * 
     */
    public void testGetWeekStartBizDateCase0004()
    {
        final String STR_METHOD_NAME = "testGetWeekStartBizDateCase0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datBiz = WEB3DateUtility.getDate("20080821", "yyyyMMdd");
            
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp(l_datBiz.getTime()));
            
            Date l_datWeekStartBizDate = l_gentradeBizDate.getWeekStartBizDate();
            
            assertEquals("20080818", WEB3DateUtility.formatDate(l_datWeekStartBizDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * getèTèââcã∆ì˙
     * åvéZäÓèÄì˙à◊é¸5ÅB
     * 
     */
    public void testGetWeekStartBizDateCase0005()
    {
        final String STR_METHOD_NAME = "testGetWeekStartBizDateCase0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datBiz = WEB3DateUtility.getDate("20080822", "yyyyMMdd");
            
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp(l_datBiz.getTime()));
            
            Date l_datWeekStartBizDate = l_gentradeBizDate.getWeekStartBizDate();
            
            assertEquals("20080818", WEB3DateUtility.formatDate(l_datWeekStartBizDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * getèTèââcã∆ì˙
     * åvéZäÓèÄì˙à◊é¸6ÅB
     * 
     */
    public void testGetWeekStartBizDateCase0006()
    {
        final String STR_METHOD_NAME = "testGetWeekStartBizDateCase0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datBiz = WEB3DateUtility.getDate("20080823", "yyyyMMdd");
            
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(new Timestamp(l_datBiz.getTime()));
            
            Date l_datWeekStartBizDate = l_gentradeBizDate.getWeekStartBizDate();
            
            assertEquals("20080818", WEB3DateUtility.formatDate(l_datWeekStartBizDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    private CalendarParams getCalendarRow()
    {
        CalendarParams l_calendarParams = null;
        try
        {
            l_calendarParams = new CalendarParams();
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setHoliday(new SimpleDateFormat("yyyyMMdd").parse("20071217"));
        }
        catch (Exception l_ex)
        {
            l_calendarParams.setHoliday(new Date());
        }
        
        return l_calendarParams;
    }
}
@
