head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.26.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsSessionTypeComparatorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会区分ComparatorTest(WEB3OptionsSessionTypeComparatorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/18 張騰宇 (中訊) 新規作成
Revision History : 2007/07/11 張騰宇 (中訊) 733
*/
package webbroker3.ifo.message;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsSessionTypeComparatorTest extends TestBaseForMock
{
   /**
    * ログユーティリティ
    */
   private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsSessionTypeComparatorTest.class);
    public WEB3OptionsSessionTypeComparatorTest(String arg0)
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
     * Test method for 'webbroker3.ifo.message.WEB3OptionsSessionTypeComparator.compare(Object, Object)'
     */
    public void testCompareCase1()
    {
        final String STR_METHOD_NAME = "testCompareCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.sessionType = null;
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.sessionType = null;
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.ASC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(0,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase2()
    {
        final String STR_METHOD_NAME = "testCompareCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.sessionType = null;
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.sessionType = "1";
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.ASC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(-1,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCompareCase3()
    {
        final String STR_METHOD_NAME = "testCompareCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.sessionType = "1";
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.sessionType = null;
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.ASC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(1,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase4()
    {
        final String STR_METHOD_NAME = "testCompareCase4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.sessionType = "1";
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.sessionType = "1";
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.ASC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(0,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase5()
    {
        final String STR_METHOD_NAME = "testCompareCase5()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.sessionType = null;
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.sessionType = null;
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.DESC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(0,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase6()
    {
        final String STR_METHOD_NAME = "testCompareCase6()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.sessionType = null;
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.sessionType = "1";
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.DESC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(1,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase7()
    {
        final String STR_METHOD_NAME = "testCompareCase7()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.sessionType = "1";
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.sessionType = null;
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.DESC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(-1,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase8()
    {
        final String STR_METHOD_NAME = "testCompareCase8()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.sessionType = "1";
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.sessionType = "1";
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.DESC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(0,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCompareCase9()
    {
        final String STR_METHOD_NAME = "testCompareCase9()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsDetailUnit l_unit1 = new WEB3FuturesOptionsDetailUnit();
            l_unit1.sessionType = null;
            WEB3FuturesOptionsDetailUnit l_unit2 = new WEB3FuturesOptionsDetailUnit();
            l_unit2.sessionType = null;
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.DESC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(0,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase10()
    {
        final String STR_METHOD_NAME = "testCompareCase10()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsDetailUnit l_unit1 = new WEB3FuturesOptionsDetailUnit();
            l_unit1.sessionType = null;
            WEB3FuturesOptionsDetailUnit l_unit2 = new WEB3FuturesOptionsDetailUnit();
            l_unit2.sessionType = "1";
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.DESC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(1,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase11()
    {
        final String STR_METHOD_NAME = "testCompareCase11()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsDetailUnit l_unit1 = new WEB3FuturesOptionsDetailUnit();
            l_unit1.sessionType = "1";
            WEB3FuturesOptionsDetailUnit l_unit2 = new WEB3FuturesOptionsDetailUnit();
            l_unit2.sessionType = null;
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.DESC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(-1,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase12()
    {
        final String STR_METHOD_NAME = "testCompareCase12()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsDetailUnit l_unit1 = new WEB3FuturesOptionsDetailUnit();
            l_unit1.sessionType = "1";
            WEB3FuturesOptionsDetailUnit l_unit2 = new WEB3FuturesOptionsDetailUnit();
            l_unit2.sessionType = "1";
            WEB3OptionsSessionTypeComparator l_comparator =
                new WEB3OptionsSessionTypeComparator(WEB3AscDescDef.DESC);
            int l_int= l_comparator.compare(l_unit1,l_unit2);
            
            assertEquals(0,l_int);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
