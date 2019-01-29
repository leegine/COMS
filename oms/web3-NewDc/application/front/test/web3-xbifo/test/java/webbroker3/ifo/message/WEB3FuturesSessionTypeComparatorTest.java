head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesSessionTypeComparatorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデル
*/
package webbroker3.ifo.message;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesSessionTypeComparatorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSessionTypeComparatorTest.class);
    public WEB3FuturesSessionTypeComparatorTest(String arg0)
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
     * Test method for 'webbroker3.ifo.message.WEB3FuturesSessionTypeComparator.compare(Object, Object)'
     */
    public void testCompareCase1()
    {
        final String STR_METHOD_NAME = "testCompareCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesContractReferenceUnit l_unit1 = new WEB3FuturesContractReferenceUnit();
            l_unit1.sessionType = null;
            WEB3FuturesContractReferenceUnit l_unit2 = new WEB3FuturesContractReferenceUnit();
            l_unit2.sessionType = null;
            WEB3FuturesSessionTypeComparator l_comparator =
                new WEB3FuturesSessionTypeComparator(WEB3AscDescDef.ASC);
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
            WEB3FuturesContractReferenceUnit l_unit1 = new WEB3FuturesContractReferenceUnit();
            l_unit1.sessionType = null;
            WEB3FuturesContractReferenceUnit l_unit2 = new WEB3FuturesContractReferenceUnit();
            l_unit2.sessionType = "1";
            WEB3FuturesSessionTypeComparator l_comparator =
                new WEB3FuturesSessionTypeComparator(WEB3AscDescDef.ASC);
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
            WEB3FuturesContractReferenceUnit l_unit1 = new WEB3FuturesContractReferenceUnit();
            l_unit1.sessionType = "1";
            WEB3FuturesContractReferenceUnit l_unit2 = new WEB3FuturesContractReferenceUnit();
            l_unit2.sessionType = null;
            WEB3FuturesSessionTypeComparator l_comparator =
                new WEB3FuturesSessionTypeComparator(WEB3AscDescDef.ASC);
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
            WEB3FuturesContractReferenceUnit l_unit1 = new WEB3FuturesContractReferenceUnit();
            l_unit1.sessionType = "1";
            WEB3FuturesContractReferenceUnit l_unit2 = new WEB3FuturesContractReferenceUnit();
            l_unit2.sessionType = "1";
            WEB3FuturesSessionTypeComparator l_comparator =
                new WEB3FuturesSessionTypeComparator(WEB3AscDescDef.ASC);
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
            WEB3FuturesOptionsContractUnit l_unit1 = new WEB3FuturesOptionsContractUnit();
            l_unit1.sessionType = null;
            WEB3FuturesOptionsContractUnit l_unit2 = new WEB3FuturesOptionsContractUnit();
            l_unit2.sessionType = null;
            WEB3FuturesSessionTypeComparator l_comparator =
                new WEB3FuturesSessionTypeComparator(WEB3AscDescDef.DESC);
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
            WEB3FuturesOptionsContractUnit l_unit1 = new WEB3FuturesOptionsContractUnit();
            l_unit1.sessionType = null;
            WEB3FuturesOptionsContractUnit l_unit2 = new WEB3FuturesOptionsContractUnit();
            l_unit2.sessionType = "1";
            WEB3FuturesSessionTypeComparator l_comparator =
                new WEB3FuturesSessionTypeComparator(WEB3AscDescDef.DESC);
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
            WEB3FuturesOptionsContractUnit l_unit1 = new WEB3FuturesOptionsContractUnit();
            l_unit1.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_unit2 = new WEB3FuturesOptionsContractUnit();
            l_unit2.sessionType = null;
            WEB3FuturesSessionTypeComparator l_comparator =
                new WEB3FuturesSessionTypeComparator(WEB3AscDescDef.DESC);
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
            WEB3FuturesOptionsContractUnit l_unit1 = new WEB3FuturesOptionsContractUnit();
            l_unit1.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_unit2 = new WEB3FuturesOptionsContractUnit();
            l_unit2.sessionType = "1";
            WEB3FuturesSessionTypeComparator l_comparator =
                new WEB3FuturesSessionTypeComparator(WEB3AscDescDef.DESC);
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
