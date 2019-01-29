head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondOrderTypeJudgeTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文種別判定(WEB3BondOrderTypeJudge.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/11 周墨洋 (中訊) 仕様変更・モデル No.196
Revision History : 2007/07/25 周墨洋 (中訊) 仕様変更・モデル No.241
*/

package webbroker3.bd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3BondOrderTypeJudgeTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderTypeJudgeTest.class);

    /**
     * 債券注文種別判定
     */
    private WEB3BondOrderTypeJudge l_bondOrderTypeJudge;

    /**
     *
     * @@param arg0
     */
    public WEB3BondOrderTypeJudgeTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testIsRecruitOrder_case0001()
    {
        final String STR_METHOD_NAME = " testIsRecruitOrder_case0001()";
        log.entering(TEST_END + STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.BOND_BUY;
        String l_strTrading = "35";

        try
        {
            this.l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(l_orderType, l_strTrading);

            Method l_method =
                WEB3BondOrderTypeJudge.class.getDeclaredMethod("isRecruitOrder", new Class[] {});
            l_method.setAccessible(true);

            Boolean l_blnExpected = Boolean.valueOf(true);
            Boolean l_blnActual = (Boolean)l_method.invoke(l_bondOrderTypeJudge, new Object[] {});
            assertEquals(l_blnExpected, l_blnActual);

        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.error(TEST_END + l_exNSME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(TEST_END + l_exITE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.error(TEST_END + l_exIAE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testIsRecruitOrder_case0002()
    {
        final String STR_METHOD_NAME = " testIsRecruitOrder_case0002()";
        log.entering(TEST_END + STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.DOMESTIC_BOND_RECRUIT;
        String l_strTrading = "92";

        try
        {
            this.l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(l_orderType, l_strTrading);

            Method l_method =
                WEB3BondOrderTypeJudge.class.getDeclaredMethod("isRecruitOrder", new Class[] {});
            l_method.setAccessible(true);

            Boolean l_blnExpected = Boolean.valueOf(true);
            Boolean l_blnActual = (Boolean)l_method.invoke(l_bondOrderTypeJudge, new Object[] {});
            assertEquals(l_blnExpected, l_blnActual);

        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.error(TEST_END + l_exNSME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(TEST_END + l_exITE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.error(TEST_END + l_exIAE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testIsRecruitOrder_case0003()
    {
        final String STR_METHOD_NAME = " testIsRecruitOrder_case0003()";
        log.entering(TEST_END + STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.BOND_BUY;
        String l_strTrading = "92";

        try
        {
            this.l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(l_orderType, l_strTrading);

            Method l_method =
                WEB3BondOrderTypeJudge.class.getDeclaredMethod("isRecruitOrder", new Class[] {});
            l_method.setAccessible(true);

            Boolean l_blnExpected = Boolean.valueOf(false);
            Boolean l_blnActual = (Boolean)l_method.invoke(l_bondOrderTypeJudge, new Object[] {});
            assertEquals(l_blnExpected, l_blnActual);

        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.error(TEST_END + l_exNSME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(TEST_END + l_exITE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.error(TEST_END + l_exIAE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     */
    public void testWEB3BondOrderTypeJudge_case0001()
    {
        final String STR_METHOD_NAME = " testWEB3BondOrderTypeJudge_case0001()";
        log.entering(TEST_END + STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.BOND_SELL;
        String l_strTrading = WEB3DealTypeDef.RECRUIT_TRADING;

        try
        {
            this.l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(l_orderType, l_strTrading);
            assertTrue(true);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     */
    public void testWEB3BondOrderTypeJudge_case0002()
    {
        final String STR_METHOD_NAME = " testWEB3BondOrderTypeJudge_case0002()";
        log.entering(TEST_END + STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.BOND_BUY;
        String l_strTrading = WEB3DealTypeDef.RECRUIT_TRADING;

        try
        {
            this.l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(l_orderType, l_strTrading);
            assertTrue(true);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

}
@
