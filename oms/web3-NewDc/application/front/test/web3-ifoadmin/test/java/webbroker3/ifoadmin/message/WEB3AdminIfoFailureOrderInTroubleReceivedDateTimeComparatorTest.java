head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest.java
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/02 劉レイ(北京中訊) 新規作成
 */
package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest extends TestBaseForMock
{

    public WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest(String arg0)
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

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest.class);
    
    public void testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0001()
    {
        final String STR_METHOD_NAME =
            "testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0001()";
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0002()
    {
        final String STR_METHOD_NAME =
            "testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0002()";
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0003()
    {
        final String STR_METHOD_NAME =
            "testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0003()";
        
        String strAscDesc = "B";
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
        }
        catch (IllegalArgumentException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals("パラメータの値が'A：昇順'、'D：降順'以外です。",
                l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0001()
    {
        final String STR_METHOD_NAME = "testCompareCase0001()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0002()
    {
        final String STR_METHOD_NAME = "testCompareCase0002()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0003()
    {
        final String STR_METHOD_NAME = "testCompareCase0003()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0004()
    {
        final String STR_METHOD_NAME = "testCompareCase0004()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0005()
    {
        final String STR_METHOD_NAME = "testCompareCase0005()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0006()
    {
        final String STR_METHOD_NAME = "testCompareCase0006()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0007()
    {
        final String STR_METHOD_NAME = "testCompareCase0007()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(null);
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0008()
    {
        final String STR_METHOD_NAME = "testCompareCase0008()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(null);
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0009()
    {
        final String STR_METHOD_NAME = "testCompareCase0009()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0010()
    {
        final String STR_METHOD_NAME = "testCompareCase0010()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(null);
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0011()
    {
        final String STR_METHOD_NAME = "testCompareCase0011()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(null);
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0012()
    {
        final String STR_METHOD_NAME = "testCompareCase0012()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受信日時
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0013()
    {
        final String STR_METHOD_NAME = "testCompareCase0013()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        IfoProductParams l_params1 = new IfoProductParams();
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //明細2の受注日時
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
        }
        catch (IllegalArgumentException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals("パラメータの類型が不正、該当する'HostFotypeOrderAllRow'類型。",
                l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0014()
    {
        final String STR_METHOD_NAME = "testCompareCase0014()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //明細1の受注日時
        l_params1.setReceivedDateTime(null);
        
        IfoProductParams l_params2 = new IfoProductParams();
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
        }
        catch (IllegalArgumentException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals("パラメータの類型が不正、該当する'HostFotypeOrderAllRow'類型。",
                l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
