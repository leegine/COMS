head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondBalanceReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.message.WEB3BondBalanceReferenceCommonRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceDetailUnit;
import webbroker3.bd.message.WEB3BondBalanceReferenceRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondBalanceReferenceServiceImplTest extends TestBaseForMock
{
    /**
     *Å@@ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceServiceImplTest.class);

    public WEB3BondBalanceReferenceServiceImplTest(String arg0)
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

    public void testCreateBalanceReferenceDetailList()
    {
        final String STR_METHOD_NAME = "testCreateBalanceReferenceDetailList()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3BondBalanceReferenceServiceImpl l_impl = new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccParams);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAssetId(1001);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setMiniStockDiv("0");
            l_assetParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_assetParams);

            l_assetParams.setAssetId(1002);
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setMiniStockDiv("0");
            l_assetParams.setProductId(3304140763001L);
            TestDBUtility.insertWithDel(l_assetParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_productParams = TestDBUtility.getBondProductRow();
            l_productParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            l_productParams.setBondType(BondTypeEnum.JGB);
            l_productParams.setProductId(3304140763001L);
            l_productParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_productParams);


            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_params = TestDBUtility.getProductRow();
            l_params.setProductType(ProductTypeEnum.BOND);
            l_params.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_params);

            l_params.setProductType(ProductTypeEnum.BOND);
            l_params.setProductId(3304140763001L);
            TestDBUtility.insertWithDel(l_params);

            WEB3GentradeSubAccount l_subAcc = new WEB3GentradeSubAccount(l_subAccParams);
            String l_strReferenceDiv = "2";
            WEB3BondBalanceReferenceDetailUnit[] l_units =
                l_impl.createBalanceReferenceDetailList(l_subAcc, l_strReferenceDiv);

            log.debug("l_units.length === " + l_units.length);
            assertEquals(1, l_units.length);
            log.exiting(STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("WEB3BaseException l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

//
//    /*
//     *
//     */
//    public void testCreateBalanceReferenceDetailList_case001()
//    {
//        final String STR_METHOD_NAME = "testCreateBalanceReferenceDetailList_case001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        WEB3BondBalanceReferenceServiceImpl l_impl = new WEB3BondBalanceReferenceServiceImpl();
//        try
//        {
//            for (int i = 0; i < 3; i++)
//            {
//                TestDBUtility.deleteAll(SubAccountRow.TYPE);
//                SubAccountParams l_subAccParams = TestDBUtility.getSubAccountRow();
//                TestDBUtility.insertWithDel(l_subAccParams);

//                TestDBUtility.deleteAll(AssetRow.TYPE);
//                AssetParams l_assetParams = TestDBUtility.getAssetRow();
//                l_assetParams.setAccountId(333812512203L);
//                l_assetParams.setSubAccountId(33381251220301L);
//                l_assetParams.setProductType(ProductTypeEnum.BOND);
//                l_assetParams.setMiniStockDiv("0");
//                l_assetParams.setProductId(3304140763000L);
//                TestDBUtility.insertWithDel(l_assetParams);

//                TestDBUtility.deleteAll(BondProductParams.TYPE);
//                BondProductParams l_productParams = TestDBUtility.getBondProductRow();
//                l_productParams.setBondType(BondTypeEnum.JGB);
//
//                if (i == 0)
//                {
//                    l_productParams.setSellPrice(0);
//                }
//                else if (i == 1)
//                {
//                    l_productParams.setSellPrice(100);
//                }
//                else if (i ==2)
//                {
//                    l_productParams.setSellPrice(null);
//                }
//
//                TestDBUtility.insertWithDel(l_productParams);

//                TestDBUtility.deleteAll(ProductParams.TYPE);
//                ProductParams l_params = TestDBUtility.getProductRow();
//                l_params.setProductType(ProductTypeEnum.BOND);
//                l_params.setProductId(3304140763000L);
//                TestDBUtility.insertWithDel(l_params);

//                WEB3GentradeSubAccount l_subAcc = new WEB3GentradeSubAccount(l_subAccParams);
//                String l_strReferenceDiv = "4";
//                WEB3BondBalanceReferenceDetailUnit[] l_units =
//                    l_impl.createBalanceReferenceDetailList(l_subAcc, l_strReferenceDiv);

//                log.debug("l_units.length === " + l_units.length);

//                if (i == 0)
//                {
//                    assertEquals(1, l_units.length);
//                    assertEquals(null, l_units[0].sellEvaluationPrice);
//                    assertEquals(null, l_units[0].yenEstimatedAsset);
//                    assertEquals(null, l_units[0].foreignEstimatedAsset);
//                }
//                else if (i == 1)
//                {
//                    assertEquals(1, l_units.length);
//                    assertEquals("100", l_units[0].sellEvaluationPrice);
//                    assertEquals("100000", l_units[0].yenEstimatedAsset);
//                    System.out.println("l_units[0].yenEstimatedAsset"+ l_units[0].yenEstimatedAsset);
//                    System.out.println("l_units[0].foreignEstimatedAsset" + l_units[0].foreignEstimatedAsset);
//                    assertEquals(null, l_units[0].foreignEstimatedAsset);
//                }
//                else if (i ==2)
//                {
//                    assertEquals(1, l_units.length);
//                    assertEquals(null, l_units[0].sellEvaluationPrice);
//                    assertEquals(null, l_units[0].yenEstimatedAsset);
//                    assertEquals(null, l_units[0].foreignEstimatedAsset);
//                }
//            }

//            log.exiting(STR_METHOD_NAME);
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error("WEB3BaseException l_ex == " + l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch(Exception l_ex)
//        {
//            log.error("Exception l_ex == " + l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//
//    }

    /**
     * è∆âÔãÊï™ == ÅhäOçëç¬åîñ¡ïøÇÃÇ›Åh Ç©Ç¬ ç¬åîñ¡ïø.ç¬åîÉ^ÉCÉv ÅÇ ÅhäOçëç¬åîÅhÇÃèÍçáÅA
     * falseÇï‘ãpÇ∑ÇÈÅB
     */
    public void testIsIndicationObjectDetails_0001()
    {
        final String STR_METHOD_NAME = "testIsIndicationObjectDetails_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3BondBalanceReferenceServiceImpl l_impl = new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_productParams = TestDBUtility.getBondProductRow();
            l_productParams.setBondType(BondTypeEnum.JGB);
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_productParams);
            boolean l_blnIndicationObjectDetails =
                l_impl.isIndicationObjectDetails("2", l_bondProduct);
            assertFalse(l_blnIndicationObjectDetails);
        }
        catch (Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * è∆âÔãÊï™ == Åhçëì‡ç¬åîÅiå¬êlå¸ÇØçëç¬Çä‹ÇﬁÅjÅh Ç©Ç¬ ç¬åîñ¡ïø.ç¬åîÉ^ÉCÉv == ÅhäOçëç¬åîÅhÇÃèÍçáÅA
     * falseÇï‘ãpÇ∑ÇÈÅB
     */
    public void testIsIndicationObjectDetails_0002()
    {
        final String STR_METHOD_NAME = "testIsIndicationObjectDetails_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3BondBalanceReferenceServiceImpl l_impl = new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_productParams = TestDBUtility.getBondProductRow();
            l_productParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_productParams);
            boolean l_blnIndicationObjectDetails =
                l_impl.isIndicationObjectDetails("3", l_bondProduct);
            assertFalse(l_blnIndicationObjectDetails);
        }
        catch (Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * è∆âÔãÊï™ == Åhçëì‡ç¬åîÅiå¬êlå¸ÇØçëç¬Çä‹Ç‹Ç»Ç¢ÅjÅh Ç©Ç¬ ÅiÅ@@ç¬åîñ¡ïø.ç¬åîÉ^ÉCÉv == ÅhäOçëç¬åîÅh
     * falseÇï‘ãpÇ∑ÇÈÅB
     */
    public void testIsIndicationObjectDetails_0003()
    {
        final String STR_METHOD_NAME = "testIsIndicationObjectDetails_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3BondBalanceReferenceServiceImpl l_impl = new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_productParams = TestDBUtility.getBondProductRow();
            l_productParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_productParams);
            boolean l_blnIndicationObjectDetails =
                l_impl.isIndicationObjectDetails("4", l_bondProduct);
            assertFalse(l_blnIndicationObjectDetails);
        }
        catch (Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * è∆âÔãÊï™ == Åhçëì‡ç¬åîÅiå¬êlå¸ÇØçëç¬Çä‹Ç‹Ç»Ç¢ÅjÅh Ç©Ç¬ ç¬åîñ¡ïø.ç¬åîÉ^ÉCÉv == Åhå¬êlå¸ÇØçëç¬Åh
     * falseÇï‘ãpÇ∑ÇÈÅB
     */
    public void testIsIndicationObjectDetails_0004()
    {
        final String STR_METHOD_NAME = "testIsIndicationObjectDetails_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3BondBalanceReferenceServiceImpl l_impl = new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_productParams = TestDBUtility.getBondProductRow();
            l_productParams.setBondType(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_productParams);
            boolean l_blnIndicationObjectDetails =
                l_impl.isIndicationObjectDetails("4", l_bondProduct);
            assertFalse(l_blnIndicationObjectDetails);
        }
        catch (Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * è∆âÔãÊï™ == Åhå¬êlå¸ÇØçëç¬ÇÃÇ›Åh Ç©Ç¬ ç¬åîñ¡ïø.ç¬åîÉ^ÉCÉv ÅÇ  Åhå¬êlå¸ÇØçëç¬ÅhÇÃèÍçáÅA
     * falseÇï‘ãpÇ∑ÇÈÅB
     */
    public void testIsIndicationObjectDetails_0005()
    {
        final String STR_METHOD_NAME = "testIsIndicationObjectDetails_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3BondBalanceReferenceServiceImpl l_impl = new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_productParams = TestDBUtility.getBondProductRow();
            l_productParams.setBondType(BondTypeEnum.JGB);
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_productParams);
            boolean l_blnIndicationObjectDetails =
                l_impl.isIndicationObjectDetails("5", l_bondProduct);
            assertFalse(l_blnIndicationObjectDetails);
        }
        catch (Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * è∆âÔãÊï™ == ÅhÇ∑Ç◊ÇƒÇÃñ¡ïøÅh Ç©Ç¬ ç¬åîñ¡ïø.ç¬åîÉ^ÉCÉv ÅÇ Åhå¬êlå¸ÇØçëç¬ÅhÇÃèÍçáÅA
     * trueÇï‘ãpÇ∑ÇÈÅB
     */
    public void testIsIndicationObjectDetails_0006()
    {
        final String STR_METHOD_NAME = "testIsIndicationObjectDetails_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3BondBalanceReferenceServiceImpl l_impl = new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_productParams = TestDBUtility.getBondProductRow();
            l_productParams.setBondType(BondTypeEnum.JGB);
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_productParams);
            boolean l_blnIndicationObjectDetails =
                l_impl.isIndicationObjectDetails("1", l_bondProduct);
            assertTrue(l_blnIndicationObjectDetails);
        }
        catch (Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceTotalRequest l_request = new WEB3BondBalanceReferenceTotalRequest();
        l_request.referenceType = "0";
        WEB3BondBalanceReferenceServiceImpl l_bondBalanceReferenceServiceImpl =
            new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            l_bondBalanceReferenceServiceImpl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceCommonRequest l_request = new WEB3BondBalanceReferenceCommonRequest();
        WEB3BondBalanceReferenceServiceImpl l_bondBalanceReferenceServiceImpl =
            new WEB3BondBalanceReferenceServiceImpl();
        try
        {
            l_bondBalanceReferenceServiceImpl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetBalanceTotal_0001()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceTotalRequest l_request = new WEB3BondBalanceReferenceTotalRequest();
        l_request.referenceType = "6";
        
        WEB3BondBalanceReferenceServiceImpl l_bondBalanceReferenceServiceImpl =
            new WEB3BondBalanceReferenceServiceImpl();
        
        try
        {
            l_bondBalanceReferenceServiceImpl.getBalanceTotal(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetBalanceTotal_0002()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceTotalRequest l_request = new WEB3BondBalanceReferenceTotalRequest();
        l_request.referenceType = "5";
        
        WEB3BondBalanceReferenceServiceImpl l_bondBalanceReferenceServiceImpl =
            new WEB3BondBalanceReferenceServiceImpl();
        
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context.setInstitutionCode("0D");
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
        try
        {
            l_bondBalanceReferenceServiceImpl.getBalanceTotal(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetBalanceTotal_0003()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3BondBalanceReferenceTotalRequest l_request = new WEB3BondBalanceReferenceTotalRequest();
        l_request.referenceType = "2";

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(l_subAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setMiniStockDiv("0");
            l_assetParams.setProductId(3304140763000L);
            TestDBUtility.deleteAll(l_assetParams.getRowType());
            TestDBUtility.insertWithDel(l_assetParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBondType(BondTypeEnum.CORPORATE_BOND);
            TestDBUtility.deleteAll(l_bondProductParams.getRowType());
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            WEB3BondBalanceReferenceServiceImpl l_bondBalanceReferenceServiceImpl =
                new WEB3BondBalanceReferenceServiceImpl();
            
            WEB3BondBalanceReferenceTotalResponse l_response =
                l_bondBalanceReferenceServiceImpl.getBalanceTotal(l_request);
            
            assertEquals("0", l_response.yenEstimatedTotalAsset);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetBalanceTotal_0004()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3BondBalanceReferenceTotalRequest l_request = new WEB3BondBalanceReferenceTotalRequest();
        l_request.referenceType = "5";

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512203L));
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(l_subAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_subAccountParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512203L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setMiniStockDiv("0");
            l_assetParams.setProductId(3304140763010L);
            l_assetParams.setQuantity(150.0);
            l_assetParams.setQuantityCannotSell(100);
            TestDBUtility.insertWithDel(l_assetParams);

            AssetParams l_assetParams1 = TestDBUtility.getAssetRow();
            l_assetParams1.setAssetId(1002);
            l_assetParams1.setAccountId(333812512203L);
            l_assetParams1.setSubAccountId(33381251220301L);
            l_assetParams1.setProductType(ProductTypeEnum.BOND);
            l_assetParams1.setMiniStockDiv("0");
            l_assetParams1.setProductId(3304140763000L);
            l_assetParams1.setQuantity(150.0);
            l_assetParams1.setQuantityCannotSell(100);
            TestDBUtility.insertWithDel(l_assetParams1);

            AssetParams l_assetParams2 = TestDBUtility.getAssetRow();
            l_assetParams2.setAssetId(1003);
            l_assetParams2.setAccountId(333812512203L);
            l_assetParams2.setSubAccountId(33381251220301L);
            l_assetParams2.setProductType(ProductTypeEnum.BOND);
            l_assetParams2.setMiniStockDiv("0");
            l_assetParams2.setProductId(3304140763010L);
            l_assetParams2.setQuantity(150.0);
            l_assetParams2.setQuantityCannotSell(100);
            TestDBUtility.insertWithDel(l_assetParams2);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(3304140763010L);
            TestDBUtility.insertWithDel(l_productParams1);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setBondType(BondTypeEnum.CORPORATE_BOND);
            l_bondProductParams.setSellPrice(2l);
            TestDBUtility.insertWithDel(l_bondProductParams);

            BondProductParams l_bondProductParams1 = TestDBUtility.getBondProductRow();
            l_bondProductParams1.setProductId(3304140763010L);
            l_bondProductParams1.setInstitutionCode("0C");
            l_bondProductParams1.setBondType(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
            l_bondProductParams1.setSellPrice(2l);
            TestDBUtility.insertWithDel(l_bondProductParams1);
            
            WEB3BondBalanceReferenceServiceImpl l_bondBalanceReferenceServiceImpl =
                new WEB3BondBalanceReferenceServiceImpl();
            
            WEB3BondBalanceReferenceTotalResponse l_response =
                l_bondBalanceReferenceServiceImpl.getBalanceTotal(l_request);
            
            assertEquals("6000", l_response.yenEstimatedTotalAsset);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceServiceImpl l_bondBalanceReferenceServiceImpl =
            new WEB3BondBalanceReferenceServiceImpl();

        try
        {
            l_bondBalanceReferenceServiceImpl.execute(null);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = "testExecute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceRequest l_request = new WEB3BondBalanceReferenceRequest();
        WEB3BondBalanceReferenceResponse l_response = null;
        
        WEB3BondBalanceReferenceServiceImplForTest l_bondBalanceReferenceServiceImpl =
            new WEB3BondBalanceReferenceServiceImplForTest();
        
        try
        {
            l_response = (WEB3BondBalanceReferenceResponse)l_bondBalanceReferenceServiceImpl.execute(l_request);
            
            assertEquals(WEB3BondBalanceReferenceResponse.class, l_response.getClass());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public class WEB3BondBalanceReferenceServiceImplForTest extends WEB3BondBalanceReferenceServiceImpl
    {
        protected WEB3BondBalanceReferenceResponse getBalanceReference(
            WEB3BondBalanceReferenceRequest l_request)throws WEB3BaseException
        {
            return new WEB3BondBalanceReferenceResponse();
        }
    }
}
@
