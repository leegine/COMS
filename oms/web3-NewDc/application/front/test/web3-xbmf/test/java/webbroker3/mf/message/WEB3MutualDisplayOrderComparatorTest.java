head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualDisplayOrderComparatorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3MutualDisplayOrderComparatorTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/01 武波 (中訊) 新規作成
*/
package webbroker3.mf.message;

import junit.framework.TestCase;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualDisplayOrderComparatorTest extends TestCase
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualDisplayOrderComparatorTest.class);
    public WEB3MutualDisplayOrderComparatorTest(String arg0)
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

    public void testCompare_C0001()
    {
        final String STR_METHOD_NAME = "testCompare_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.ASC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            assertEquals(0, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testCompare_C0002()
    {
        final String STR_METHOD_NAME = "testCompare_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.ASC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            l_unit2.displayOrder = "1";
            assertEquals(-1, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testCompare_C0003()
    {
        final String STR_METHOD_NAME = "testCompare_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.ASC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            l_unit1.displayOrder = "1";
            assertEquals(1, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testCompare_C0004()
    {
        final String STR_METHOD_NAME = "testCompare_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.DESC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            assertEquals(0, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testCompare_C0005()
    {
        final String STR_METHOD_NAME = "testCompare_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.DESC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            l_unit2.displayOrder = "1";
            assertEquals(1, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testCompare_C0006()
    {
        final String STR_METHOD_NAME = "testCompare_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.DESC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            l_unit1.displayOrder = "1";
            assertEquals(-1, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testCompare_C0007()
    {
        final String STR_METHOD_NAME = "testCompare_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.DESC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            l_unit1.displayOrder = "1";
            l_unit2.displayOrder = "0";
            assertEquals(-1, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testCompare_C0008()
    {
        final String STR_METHOD_NAME = "testCompare_C0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.ASC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            l_unit1.displayOrder = "0";
            l_unit2.displayOrder = "0";
            assertEquals(0, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testCompare_C0009()
    {
        final String STR_METHOD_NAME = "testCompare_C0009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3MutualDisplayOrderComparator l_comparator =
                new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.ASC);
            WEB3MutualFixedBuyConditionUnit l_unit1 =
                new WEB3MutualFixedBuyConditionUnit();
            WEB3MutualFixedBuyConditionUnit l_unit2 =
                new WEB3MutualFixedBuyConditionUnit();
            l_unit1.displayOrder = "0";
            l_unit2.displayOrder = "1";
            assertEquals(-1, l_comparator.compare(l_unit1, l_unit2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
