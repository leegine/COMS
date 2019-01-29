head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoDepOrderExistFlagComparatorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ｘｘｘｘ(WEB3AdminIfoDepOrderExistFlagComparatorTest.java.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/09 張騰宇 (中訊) 新規作成 モデルNo.000
*/
package webbroker3.ifoadmin.message;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoDepOrderExistFlagComparatorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepOrderExistFlagComparatorTest.class);

    public WEB3AdminIfoDepOrderExistFlagComparatorTest(String arg0)
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

    /*
     * Test method for 'webbroker3.ifoadmin.message.WEB3AdminIfoDepOrderExistFlagComparator.compare(Object, Object)'
     */
    //昇順指定の場合、 
    //　@　@明細1の注文有無フラグがfalse、明細2の注文有無フラグがtrue場合、負の整数（-1）を返却する。
    public void testCompareCase1()
    {
        String STR_METHOD_NAME = "testCompareCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepOrderExistFlagComparator l_comparator = new WEB3AdminIfoDepOrderExistFlagComparator("A");
        
        try
        {
            WEB3IfoDepShortageInfo l_info1 = new WEB3IfoDepShortageInfo();

            WEB3IfoDepShortageInfo l_info2 = new WEB3IfoDepShortageInfo();
            l_info1.orderExistFlag = false;
            l_info2.orderExistFlag = true;
            
            int l_intResult = l_comparator.compare(l_info1, l_info2);
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //昇順指定の場合、 
    //　@　@両方が等しい場合は0を返却する。
    public void testCompareCase2()
    {
        String STR_METHOD_NAME = "testCompareCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepOrderExistFlagComparator l_comparator = new WEB3AdminIfoDepOrderExistFlagComparator("A");
        
        try
        {
            WEB3IfoDepShortageInfo l_info1 = new WEB3IfoDepShortageInfo();

            WEB3IfoDepShortageInfo l_info2 = new WEB3IfoDepShortageInfo();
            l_info1.orderExistFlag = false;
            l_info2.orderExistFlag = false;
            
            int l_intResult = l_comparator.compare(l_info1, l_info2);
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //昇順指定の場合、 
    //　@　@明細1の注文有無フラグがtrue、明細2の注文有無フラグがfalse場合、正の整数（1）を返却する。
    public void testCompareCase3()
    {
        String STR_METHOD_NAME = "testCompareCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepOrderExistFlagComparator l_comparator = new WEB3AdminIfoDepOrderExistFlagComparator("A");
        
        try
        {
            WEB3IfoDepShortageInfo l_info1 = new WEB3IfoDepShortageInfo();

            WEB3IfoDepShortageInfo l_info2 = new WEB3IfoDepShortageInfo();
            l_info1.orderExistFlag = true;
            l_info2.orderExistFlag = false;
            
            int l_intResult = l_comparator.compare(l_info1, l_info2);
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //降順指定の場合、 
    //　@　@明細1の注文有無フラグがfalse、明細2の注文有無フラグがtrue場合、正の整数（1）を返却する。
    public void testCompareCase4()
    {
        String STR_METHOD_NAME = "testCompareCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepOrderExistFlagComparator l_comparator = new WEB3AdminIfoDepOrderExistFlagComparator("D");
        
        try
        {
            WEB3IfoDepShortageInfo l_info1 = new WEB3IfoDepShortageInfo();

            WEB3IfoDepShortageInfo l_info2 = new WEB3IfoDepShortageInfo();
            l_info1.orderExistFlag = false;
            l_info2.orderExistFlag = true;
            
            int l_intResult = l_comparator.compare(l_info1, l_info2);
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //降順指定の場合、 
    //　@　@両方が等しい場合は0を返却する。
    public void testCompareCase5()
    {
        String STR_METHOD_NAME = "testCompareCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepOrderExistFlagComparator l_comparator = new WEB3AdminIfoDepOrderExistFlagComparator("D");
        
        try
        {
            WEB3IfoDepShortageInfo l_info1 = new WEB3IfoDepShortageInfo();

            WEB3IfoDepShortageInfo l_info2 = new WEB3IfoDepShortageInfo();
            l_info1.orderExistFlag = true;
            l_info2.orderExistFlag = true;
            
            int l_intResult = l_comparator.compare(l_info1, l_info2);
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //降順指定の場合、 
    //　@　@明細1の注文有無フラグがtrue、明細2の注文有無フラグがfalse場合、負の整数（-1）を返却する。
    public void testCompareCase6()
    {
        String STR_METHOD_NAME = "testCompareCase6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepOrderExistFlagComparator l_comparator = new WEB3AdminIfoDepOrderExistFlagComparator("D");
        
        try
        {
            WEB3IfoDepShortageInfo l_info1 = new WEB3IfoDepShortageInfo();

            WEB3IfoDepShortageInfo l_info2 = new WEB3IfoDepShortageInfo();
            l_info1.orderExistFlag = true;
            l_info2.orderExistFlag = false;
            
            int l_intResult = l_comparator.compare(l_info1, l_info2);
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
