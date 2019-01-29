head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBranchListmarketDealtCondTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （部店市場上場区分別）取扱条件テスト(WEB3GentradeBranchTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 栄イ (中訊) 新規作成        
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.BranchListmarketDealtCondParams;
import webbroker3.gentrade.data.BranchListmarketDealtCondRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （（部店市場上場区分別）取扱条件テスト）<BR>
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3GentradeBranchListmarketDealtCondTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3GentradeBranchTest.class);

    public WEB3GentradeBranchListmarketDealtCondTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(BranchListmarketDealtCondRow.TYPE);
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(BranchListmarketDealtCondRow.TYPE);
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
    }

    /*
     * testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0001
     */
    public void testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0001()
    {
        final String STR_METHOD_NAME = " testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
                = new WEB3GentradeBranchListmarketDealtCond(
                101,
                10101,
                "1",
                "1",
                "1");
            fail();
       }
       catch (WEB3SystemLayerException l_sle)
       {
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_sle.getErrorInfo());
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0002
     */
    public void testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0002()
    {
        final String STR_METHOD_NAME = " testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchListmarketDealtCondParams l_branchListmarketDealtCondParams
                = new BranchListmarketDealtCondParams();
            l_branchListmarketDealtCondParams.setBranchId(101);
            l_branchListmarketDealtCondParams.setMarketId(10101);
            l_branchListmarketDealtCondParams.setListType("1");
            l_branchListmarketDealtCondParams.setNewListType("2");
            l_branchListmarketDealtCondParams.setOpenOtcDiv("3");
            l_branchListmarketDealtCondParams.setListmarketName("wulala");
            l_branchListmarketDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_branchListmarketDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_branchListmarketDealtCondParams.setMaxContUnitCorp(null);
            l_branchListmarketDealtCondParams.setMaxContUnitInd(new Double(1));
            l_branchListmarketDealtCondParams.setMaxContPriceCorp(null);
            l_branchListmarketDealtCondParams.setMaxContPriceInd(new Double(2));
            l_branchListmarketDealtCondParams.setMarginSecCheckRate(null);
            TestDBUtility.insertWithDel(l_branchListmarketDealtCondParams);
            MainAccountParams l_mainAccountParams = new MainAccountParams(TestDBUtility.getMainAccountRow());
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
                = new WEB3GentradeBranchListmarketDealtCond(
                101,
                10101,
                "1",
                "2",
                "3");
            Double l_dblMaxContUnit = l_branchListmarketDealtCond.getMaxContUnit(l_mainAccount);
            Double l_dblMaxContPrice = l_branchListmarketDealtCond.getMaxContPrice(l_mainAccount);
            Double l_dblMarginSecCheckRate = l_branchListmarketDealtCond.getMarginSecCheckRate();
            assertEquals(null, l_dblMaxContUnit);
            assertEquals(null, l_dblMaxContPrice);
            assertEquals(null, l_dblMarginSecCheckRate);
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0003
     */
    public void testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0003()
    {
        final String STR_METHOD_NAME = " testWEB3GentradeBranchListmarketDealtCondLongLongStringStringString_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchListmarketDealtCondParams l_branchListmarketDealtCondParams
                = new BranchListmarketDealtCondParams();
            l_branchListmarketDealtCondParams.setBranchId(101);
            l_branchListmarketDealtCondParams.setMarketId(10101);
            l_branchListmarketDealtCondParams.setListType("1");
            l_branchListmarketDealtCondParams.setNewListType("2");
            l_branchListmarketDealtCondParams.setOpenOtcDiv("3");
            l_branchListmarketDealtCondParams.setListmarketName("wulala");
            l_branchListmarketDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_branchListmarketDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_branchListmarketDealtCondParams.setMaxContUnitCorp(null);
            l_branchListmarketDealtCondParams.setMaxContUnitInd(new Double(1));
            l_branchListmarketDealtCondParams.setMaxContPriceCorp(null);
            l_branchListmarketDealtCondParams.setMaxContPriceInd(new Double(2));
            TestDBUtility.insertWithDel(l_branchListmarketDealtCondParams);
            MainAccountParams l_mainAccountParams = new MainAccountParams(TestDBUtility.getMainAccountRow());
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
                = new WEB3GentradeBranchListmarketDealtCond(
                101,
                10101,
                "1",
                "2",
                "3");
            Double l_dblMaxContUnit = l_branchListmarketDealtCond.getMaxContUnit(l_mainAccount);
            Double l_dblMaxContPrice = l_branchListmarketDealtCond.getMaxContPrice(l_mainAccount);
            assertEquals(new Double(1), l_dblMaxContUnit);
            assertEquals(new Double(2), l_dblMaxContPrice);
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0001
     */
    public void testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0001()
    {
        final String STR_METHOD_NAME = " testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
                = new WEB3GentradeBranchListmarketDealtCond(null);
            fail();
       }
       catch (WEB3SystemLayerException l_sle)
       {
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_sle.getErrorInfo());
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0002
     */
    public void testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0002()
    {
        final String STR_METHOD_NAME = " testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchListmarketDealtCondParams l_branchListmarketDealtCondParams
            = new BranchListmarketDealtCondParams();
        l_branchListmarketDealtCondParams.setBranchId(101);
        l_branchListmarketDealtCondParams.setMarketId(10101);
        l_branchListmarketDealtCondParams.setListType("1");
        l_branchListmarketDealtCondParams.setNewListType("2");
        l_branchListmarketDealtCondParams.setOpenOtcDiv("3");
        l_branchListmarketDealtCondParams.setListmarketName("wulala");
        l_branchListmarketDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_branchListmarketDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_branchListmarketDealtCondParams.setMaxContUnitCorp(new Double(1));
        l_branchListmarketDealtCondParams.setMaxContUnitInd(null);
        l_branchListmarketDealtCondParams.setMaxContPriceCorp(new Double(2));
        l_branchListmarketDealtCondParams.setMaxContPriceInd(null);
        l_branchListmarketDealtCondParams.setMarginSecCheckRate(new Double(3));
        TestDBUtility.insertWithDel(l_branchListmarketDealtCondParams);
        MainAccountParams l_mainAccountParams = new MainAccountParams(TestDBUtility.getMainAccountRow());
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
        TestDBUtility.insertWithDel(l_mainAccountParams);

        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
            = new WEB3GentradeBranchListmarketDealtCond(l_branchListmarketDealtCondParams);
        Double l_dblMaxContUnit = l_branchListmarketDealtCond.getMaxContUnit(l_mainAccount);
        Double l_dblMaxContPrice = l_branchListmarketDealtCond.getMaxContPrice(l_mainAccount);
        Double l_dblMarginSecCheckRate = l_branchListmarketDealtCond.getMarginSecCheckRate();
        assertEquals(new Double(1), l_dblMaxContUnit);
        assertEquals(new Double(2), l_dblMaxContPrice);
        assertEquals(new Double(3), l_dblMarginSecCheckRate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0003
     */
    public void testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0003()
    {
        final String STR_METHOD_NAME = " testWEB3GentradeBranchListmarketDealtCondBranchListmarketDealtCondRow_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchListmarketDealtCondParams l_branchListmarketDealtCondParams
            = new BranchListmarketDealtCondParams();
        l_branchListmarketDealtCondParams.setBranchId(101);
        l_branchListmarketDealtCondParams.setMarketId(10101);
        l_branchListmarketDealtCondParams.setListType("1");
        l_branchListmarketDealtCondParams.setNewListType("2");
        l_branchListmarketDealtCondParams.setOpenOtcDiv("3");
        l_branchListmarketDealtCondParams.setListmarketName("wulala");
        l_branchListmarketDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_branchListmarketDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_branchListmarketDealtCondParams.setMaxContUnitCorp(new Double(1));
        l_branchListmarketDealtCondParams.setMaxContUnitInd(null);
        l_branchListmarketDealtCondParams.setMaxContPriceCorp(new Double(2));
        l_branchListmarketDealtCondParams.setMaxContPriceInd(null);
        TestDBUtility.insertWithDel(l_branchListmarketDealtCondParams);
        MainAccountParams l_mainAccountParams = new MainAccountParams(TestDBUtility.getMainAccountRow());
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.JOINT_OWNERSHIP);
        TestDBUtility.insertWithDel(l_mainAccountParams);

        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
            = new WEB3GentradeBranchListmarketDealtCond(l_branchListmarketDealtCondParams);
        Double l_dblMaxContUnit = l_branchListmarketDealtCond.getMaxContUnit(l_mainAccount);
        Double l_dblMaxContPrice = l_branchListmarketDealtCond.getMaxContPrice(l_mainAccount);
        assertEquals(null, l_dblMaxContUnit);
        assertEquals(null, l_dblMaxContPrice);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetMaxContUnit_Case0001
     */
    public void testGetMaxContUnit_Case0001()
    {
        final String STR_METHOD_NAME = " testGetMaxContUnit_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchListmarketDealtCondParams l_branchListmarketDealtCondParams
            = new BranchListmarketDealtCondParams();
        l_branchListmarketDealtCondParams.setBranchId(101);
        l_branchListmarketDealtCondParams.setMarketId(10101);
        l_branchListmarketDealtCondParams.setListType("1");
        l_branchListmarketDealtCondParams.setNewListType("2");
        l_branchListmarketDealtCondParams.setOpenOtcDiv("3");
        l_branchListmarketDealtCondParams.setListmarketName("wulala");
        l_branchListmarketDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_branchListmarketDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        TestDBUtility.insertWithDel(l_branchListmarketDealtCondParams);

        WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
            = new WEB3GentradeBranchListmarketDealtCond(l_branchListmarketDealtCondParams);
        l_branchListmarketDealtCond.getMaxContUnit(null);
        fail();
        }
       catch (WEB3SystemLayerException l_sle)
       {
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_sle.getErrorInfo());
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetMaxContPrice_Case0001
     */
    public void testGetMaxContPrice_Case0001()
    {
        final String STR_METHOD_NAME = " testGetMaxContPrice_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchListmarketDealtCondParams l_branchListmarketDealtCondParams
            = new BranchListmarketDealtCondParams();
        l_branchListmarketDealtCondParams.setBranchId(101);
        l_branchListmarketDealtCondParams.setMarketId(10101);
        l_branchListmarketDealtCondParams.setListType("1");
        l_branchListmarketDealtCondParams.setNewListType("2");
        l_branchListmarketDealtCondParams.setOpenOtcDiv("3");
        l_branchListmarketDealtCondParams.setListmarketName("wulala");
        l_branchListmarketDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        l_branchListmarketDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
        TestDBUtility.insertWithDel(l_branchListmarketDealtCondParams);

        WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
            = new WEB3GentradeBranchListmarketDealtCond(l_branchListmarketDealtCondParams);
        l_branchListmarketDealtCond.getMaxContPrice(null);
        fail();
        }
       catch (WEB3SystemLayerException l_sle)
       {
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_sle.getErrorInfo());
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
