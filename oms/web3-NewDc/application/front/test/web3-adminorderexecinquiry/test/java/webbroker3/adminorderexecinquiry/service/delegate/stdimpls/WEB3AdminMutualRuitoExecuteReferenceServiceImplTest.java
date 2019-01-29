head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualRuitoExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * 
 * @@author tang-xingfeng
 *
 */
public class WEB3AdminMutualRuitoExecuteReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualRuitoExecuteReferenceServiceImplTest.class);
    WEB3AdminMutualRuitoExecuteReferenceServiceImpl impl =
        new WEB3AdminMutualRuitoExecuteReferenceServiceImpl();

    public WEB3AdminMutualRuitoExecuteReferenceServiceImplTest(String arg0)
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
     * Test method for 'webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminMutualRuitoExecuteReferenceServiceImpl.createQueryString(String, String, String, String, String)'
     */
    public void testCreateQueryString_case1()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strProductId = "3304148080000L";
            String l_strTaxType = WEB3TaxTypeDef.NORMAL;
            String l_strDeliveryDiv = "2";
            String l_strProductType = "5";
            String l_strMutualFrgnMmfDisplayDiv = "0";
            String l_strQuery = impl.createQueryString(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_strProductType,
                l_strMutualFrgnMmfDisplayDiv);
            assertEquals(l_strQuery, " and product_id = ?  and tax_type = ?  and payment_method = ?  and fund_type <> ? ");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_case2()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strProductId = "3304148080000L";
            String l_strTaxType = WEB3TaxTypeDef.NORMAL;
            String l_strDeliveryDiv = "2";
            String l_strProductType = "5";
            String l_strMutualFrgnMmfDisplayDiv = "1";
            String l_strQuery = impl.createQueryString(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_strProductType,
                l_strMutualFrgnMmfDisplayDiv);
            assertEquals(l_strQuery, " and product_id = ?  and tax_type = ?  and payment_method = ?  and fund_type = ? ");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_case3()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strProductId = "3304148080000L";
            String l_strTaxType = WEB3TaxTypeDef.NORMAL;
            String l_strDeliveryDiv = "2";
            String l_strProductType = "5";
            String l_strMutualFrgnMmfDisplayDiv = "2";
            String l_strQuery = impl.createQueryString(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_strProductType,
                l_strMutualFrgnMmfDisplayDiv);
            assertEquals(l_strQuery, " and product_id = ?  and tax_type = ?  and payment_method = ? ");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_case4()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_case4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strProductId = "3304148080000L";
            String l_strTaxType = WEB3TaxTypeDef.NORMAL;
            String l_strDeliveryDiv = "2";
            String l_strProductType = "4";
            String l_strMutualFrgnMmfDisplayDiv = "1";
            String l_strQuery = impl.createQueryString(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_strProductType,
                l_strMutualFrgnMmfDisplayDiv);
            assertEquals(l_strQuery, " and product_id = ?  and tax_type = ?  and payment_method = ? ");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminMutualRuitoExecuteReferenceServiceImpl.createQueryDataContainer(String, String, String, String, String)'
     */
    public void testCreateQueryDataContainer_case1()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strProductId = "3304148080000L";
            String l_strTaxType = WEB3TaxTypeDef.NORMAL;
            String l_strDeliveryDiv = "2";
            String l_strProductType = "4";
            String l_strMutualFrgnMmfDisplayDiv = "1";
            String[] l_strDataContainers = impl.createQueryDataContainer(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_strProductType,
                l_strMutualFrgnMmfDisplayDiv);
            assertEquals(l_strDataContainers[0], "3304148080000L");
            assertEquals(l_strDataContainers[1], "1");
            assertEquals(l_strDataContainers[2], "2");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainer_case2()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strProductId = "3304148080000L";
            String l_strTaxType = WEB3TaxTypeDef.NORMAL;
            String l_strDeliveryDiv = "2";
            String l_strProductType = "5";
            String l_strMutualFrgnMmfDisplayDiv = "1";
            String[] l_strDataContainers = impl.createQueryDataContainer(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_strProductType,
                l_strMutualFrgnMmfDisplayDiv);
            assertEquals(l_strDataContainers[0], "3304148080000L");
            assertEquals(l_strDataContainers[1], "1");
            assertEquals(l_strDataContainers[2], "2");
            assertEquals(l_strDataContainers[3], MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainer_case3()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strProductId = "3304148080000L";
            String l_strTaxType = WEB3TaxTypeDef.NORMAL;
            String l_strDeliveryDiv = "2";
            String l_strProductType = "5";
            String l_strMutualFrgnMmfDisplayDiv = "2";
            String[] l_strDataContainers = impl.createQueryDataContainer(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_strProductType,
                l_strMutualFrgnMmfDisplayDiv);
            assertEquals(l_strDataContainers[0], "3304148080000L");
            assertEquals(l_strDataContainers[1], "1");
            assertEquals(l_strDataContainers[2], "2");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateMutualRuitoExecutionRefReferenceUnitListCase1()
    {
        final String STR_METHOD_NAME = "testCreateMutualRuitoExecutionRefReferenceUnitListCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderQuantityDiv",
            new Class[]{MutualFundOrderUnit.class},
            "100");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getEstimateDeliveryAmountCurrencyCode",
            new Class[]{MutualFundOrderUnit.class},
            "200");

        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setBranchId(33381L);
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setProductId(4003000900000000L);

        try
        {
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3AdminMutualRuitoExecuteReferenceServiceImpl l_impl =
            new WEB3AdminMutualRuitoExecuteReferenceServiceImpl();
        OrderUnit[] l_orderUnits = null;
        String l_strProductDiv = null;
        String l_strQueryCond = "  order_unit_id = ?  ";
        String[] l_strQueryCondDataContainers = {"2050"};
        String l_strSortCond = null;

        ArrayList l_lisOrderUnits;
        try
        {
            l_lisOrderUnits = l_impl.getOrderUnits(
                l_strProductDiv,
                l_strQueryCond,
                l_strQueryCondDataContainers,
                l_strSortCond);
            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_lisOrderUnits, 1, 5);
            l_orderUnits = (OrderUnit[]) l_pageIndexInfo.getArrayReturned(OrderUnit.class);
            WEB3AdminORMutualRuitoOrderExecutionRefUnit[] l_unit = new WEB3AdminORMutualRuitoOrderExecutionRefUnit[1];
            l_unit = l_impl.createMutualRuitoExecutionRefReferenceUnitList(l_orderUnits);
            assertTrue(l_unit[0].frgnMmfFlag);
            assertEquals("2", l_unit[0].taxType);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (IllegalStateException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateMutualRuitoExecutionRefReferenceUnitListCase2()
    {
        final String STR_METHOD_NAME = "testCreateMutualRuitoExecutionRefReferenceUnitListCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setBranchId(33381L);
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setProductId(4003000900000000L);

        try
        {
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3AdminMutualRuitoExecuteReferenceServiceImpl l_impl =
            new WEB3AdminMutualRuitoExecuteReferenceServiceImpl();
        OrderUnit[] l_orderUnits = null;
        String l_strProductDiv = null;
        String l_strQueryCond = "  order_unit_id = ?  ";
        String[] l_strQueryCondDataContainers = {"2050"};
        String l_strSortCond = null;

        ArrayList l_lisOrderUnits;
        try
        {
            l_lisOrderUnits = l_impl.getOrderUnits(
                l_strProductDiv,
                l_strQueryCond,
                l_strQueryCondDataContainers,
                l_strSortCond);
            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_lisOrderUnits, 1, 5);
            l_orderUnits = (OrderUnit[]) l_pageIndexInfo.getArrayReturned(OrderUnit.class);
            WEB3AdminORMutualRuitoOrderExecutionRefUnit[] l_unit = new WEB3AdminORMutualRuitoOrderExecutionRefUnit[1];
            l_unit = l_impl.createMutualRuitoExecutionRefReferenceUnitList(l_orderUnits);
            assertFalse(l_unit[0].frgnMmfFlag);
            assertEquals("1", l_unit[0].taxType);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (IllegalStateException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
