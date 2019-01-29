head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticProductRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputResponse;
import webbroker3.bd.message.WEB3BondDomesticProductBasicInfo;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfo;
import webbroker3.bond.data.BondProductRow;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondDomesticProductRegistServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductRegistServiceImplTest.class);

    WEB3AdminBondDomesticProductRegistServiceImpl l_impl;
    public WEB3AdminBondDomesticProductRegistServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_impl = new WEB3AdminBondDomesticProductRegistServiceImpl();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecutet_01()
    {
        final String STR_METHOD_NAME = "testExecutet_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.execute(null);
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecutet_02()
    {
        final String STR_METHOD_NAME = "testExecutet_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistInputRequest l_request =
                new WEB3AdminBondDomesticProductRegistInputRequest();

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            WEB3AdminBondDomesticProductRegistInputResponse l_response =
                (WEB3AdminBondDomesticProductRegistInputResponse)l_impl.execute(l_request);
            
            WEB3BondDomesticProductBasicInfo l_baseInfo = l_response.productBasicInfo;
            assertEquals("0763", l_baseInfo.productCode);
            assertEquals("101", l_baseInfo.productIssueCode);
            assertEquals("jiddk", l_baseInfo.productNameHost);
            assertEquals("123", l_baseInfo.bondCategCode);
            assertEquals(0, l_baseInfo.issueCouponType.length);
            assertEquals(WEB3DateUtility.getDate("2004/01/01", "yyyy/MM/dd"), l_baseInfo.issueDate);
            assertEquals(null, l_baseInfo.applyPrice);
            assertEquals("0.08", l_baseInfo.coupon);
            assertEquals("2", l_baseInfo.yearlyInterestPayments);
            assertNull(l_baseInfo.couponPaymentDate1);
            assertEquals(WEB3DateUtility.getDate("2009/07/01", "yyyy/MM/dd"), l_baseInfo.maturityDate);
            assertEquals(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"), l_baseInfo.recruitStartDateSONAR);
            assertEquals(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"),l_baseInfo.recruitEndDateSONAR);
            
            WEB3BondDomesticProductUpdateInfo l_updateInfo = l_response.productUpdateInfo;
            assertEquals("2", l_updateInfo.tradeHandleDiv);
            assertEquals("6", l_updateInfo.dealingType);
            assertEquals(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"), l_updateInfo.recruitStartDateWEB3);
            assertEquals(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"), l_updateInfo.recruitEndDateWEB3);
            assertEquals(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"), l_updateInfo.recruitStartDateInterNet);
            assertEquals(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"), l_updateInfo.recruitEndDateInterNet);
            assertEquals(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"), l_updateInfo.deliveryDate);
            assertEquals("chai024", l_updateInfo.productNameWEB3);
            assertEquals("100", l_updateInfo.applyUnit);
            assertEquals("100", l_updateInfo.minFaceAmount);
            assertEquals(null, l_updateInfo.maxFaceAmount);
            assertEquals("1", l_updateInfo.prospectusCheckDiv);

        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecutet_03()
    {
        final String STR_METHOD_NAME = "testExecutet_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistConfirmRequest l_request =
                new WEB3AdminBondDomesticProductRegistConfirmRequest();

            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            //WEB3BondProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "validateProductSpec", 
                new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class}, 
                null);

            WEB3AdminBondDomesticProductRegistConfirmResponse l_respose =
                (WEB3AdminBondDomesticProductRegistConfirmResponse)l_impl.execute(l_request);
            
            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "validateProductSpec", 
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class});
           assertEquals(l_request.productId, l_paramsValue.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue.getFirstCalled()[1]);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecutet_04()
    {
        final String STR_METHOD_NAME = "testExecutet_04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistCompleteRequest l_request =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();
            l_request.password = "123456";
            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            //WEB3BondProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "validateProductSpec", 
                new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class}, 
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.bd.WEB3BondProductManager",
                    "updateBondProductContent",
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class, String.class},
                    null);

            WEB3AdminBondDomesticProductRegistCompleteResponse l_response =
                (WEB3AdminBondDomesticProductRegistCompleteResponse)l_impl.execute(l_request);

            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue1 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "validateProductSpec", 
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class});

           assertEquals(l_request.productId, l_paramsValue1.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue1.getFirstCalled()[1]);
           
           WEB3MockObjectParamsValue l_paramsValue2 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.bd.WEB3BondProductManager",
                   "updateBondProductContent",
                   new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class, String.class});

           assertEquals(l_request.productId, l_paramsValue2.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue2.getFirstCalled()[1]);
           assertEquals(l_administratorParams.getAdministratorCode(), l_paramsValue2.getFirstCalled()[2]);

           //返回?驗證
           assertEquals(l_administratorParams.getAdministratorCode(), l_response.updaterCode);
           String l_strDate1 = WEB3DateUtility.formatDate(l_response.updateTimeStamp, "yyyy/MM/dd");
           String l_strDate2 = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd");
           assertEquals(l_strDate1, l_strDate2);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecutet_05()
    {
        final String STR_METHOD_NAME = "testExecutet_05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductListSearchDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListSearchDisplayRequest();
            l_impl.execute(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputProductRegistT_01()
    {
        final String STR_METHOD_NAME = "testInputProductRegistT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.inputProductRegist(null);
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputProductRegistT_02()
    {
        final String STR_METHOD_NAME = "testInputProductRegistT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistInputRequest l_request =
                new WEB3AdminBondDomesticProductRegistInputRequest();
            l_request.productId = null;
            l_impl.inputProductRegist(l_request);
            fail();
            
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testInputProductRegistT_03()
    {
        final String STR_METHOD_NAME = "testInputProductRegistT_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistInputRequest l_request =
                new WEB3AdminBondDomesticProductRegistInputRequest();
            l_request.productId = "123";

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_impl.inputProductRegist(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testInputProductRegistT_04()
    {
        final String STR_METHOD_NAME = "testInputProductRegistT_04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistInputRequest l_request =
                new WEB3AdminBondDomesticProductRegistInputRequest();
            l_request.productId = "123";

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_impl.inputProductRegist(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testInputProductRegistT_05()
    {
        final String STR_METHOD_NAME = "testInputProductRegistT_05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistInputRequest l_request =
                new WEB3AdminBondDomesticProductRegistInputRequest();

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            WEB3AdminBondDomesticProductRegistInputResponse l_response =
                l_impl.inputProductRegist(l_request);
            WEB3BondDomesticProductBasicInfo l_baseInfo = l_response.productBasicInfo;
            assertEquals("0763", l_baseInfo.productCode);
            assertEquals("101", l_baseInfo.productIssueCode);
            assertEquals("jiddk", l_baseInfo.productNameHost);
            assertEquals("123", l_baseInfo.bondCategCode);
            assertEquals(0, l_baseInfo.issueCouponType.length);
            assertEquals(WEB3DateUtility.getDate("2004/01/01", "yyyy/MM/dd"), l_baseInfo.issueDate);
            assertEquals(null, l_baseInfo.applyPrice);
            assertEquals("0.08", l_baseInfo.coupon);
            assertEquals("2", l_baseInfo.yearlyInterestPayments);
            assertNull(l_baseInfo.couponPaymentDate1);
            assertEquals(WEB3DateUtility.getDate("2009/07/01", "yyyy/MM/dd"), l_baseInfo.maturityDate);
            assertEquals(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"), l_baseInfo.recruitStartDateSONAR);
            assertEquals(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"),l_baseInfo.recruitEndDateSONAR);
            
            WEB3BondDomesticProductUpdateInfo l_updateInfo = l_response.productUpdateInfo;
            assertEquals("2", l_updateInfo.tradeHandleDiv);
            assertEquals("6", l_updateInfo.dealingType);
            assertEquals(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"), l_updateInfo.recruitStartDateWEB3);
            assertEquals(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"), l_updateInfo.recruitEndDateWEB3);
            assertEquals(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"), l_updateInfo.recruitStartDateInterNet);
            assertEquals(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"), l_updateInfo.recruitEndDateInterNet);
            assertEquals(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"), l_updateInfo.deliveryDate);
            assertEquals("chai024", l_updateInfo.productNameWEB3);
            assertEquals("100", l_updateInfo.applyUnit);
            assertEquals("100", l_updateInfo.minFaceAmount);
            assertEquals(null, l_updateInfo.maxFaceAmount);
            assertEquals("1", l_updateInfo.prospectusCheckDiv);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testValidateProductRegistT_01()
    {
        final String STR_METHOD_NAME = "testValidateProductRegistT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.validateProductRegist(null);
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateProductRegistT_02()
    {
        final String STR_METHOD_NAME = "testValidateProductRegistT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistConfirmRequest l_request =
                new WEB3AdminBondDomesticProductRegistConfirmRequest();
            l_request.productId = null;
            l_impl.validateProductRegist(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateProductRegistT_03()
    {
        final String STR_METHOD_NAME = "testValidateProductRegistT_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistConfirmRequest l_request =
                new WEB3AdminBondDomesticProductRegistConfirmRequest();
            l_request.productId = "123";
            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            l_impl.validateProductRegist(l_request);
            
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductRegistT_04()
    {
        final String STR_METHOD_NAME = "testValidateProductRegistT_04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistConfirmRequest l_request =
                new WEB3AdminBondDomesticProductRegistConfirmRequest();
            l_request.productId = "123";
            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            l_impl.validateProductRegist(l_request);
            
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductRegistT_05()
    {
        final String STR_METHOD_NAME = "testValidateProductRegistT_05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3AdminBondDomesticProductRegistConfirmRequest l_request =
                new WEB3AdminBondDomesticProductRegistConfirmRequest();

            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            //WEB3BondProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "validateProductSpec", 
                new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class}, 
                null);

            l_impl.validateProductRegist(l_request);
            
            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "validateProductSpec", 
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class});
           assertEquals(l_request.productId, l_paramsValue.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue.getFirstCalled()[1]);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testSubmitProductRegistT_01()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.submitProductRegist(null);
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitProductRegistT_02()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistCompleteRequest l_request =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();
            l_request.productId = null;
            l_impl.submitProductRegist(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitProductRegistT_03()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistCompleteRequest l_request =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();
            l_request.productId = "123";
            l_request.password = "123456";
            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            l_impl.submitProductRegist(l_request);
            
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitProductRegistT_04()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistCompleteRequest l_request =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();
            l_request.productId = "123";
            l_request.password = "123456";
            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", false);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_impl.submitProductRegist(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testSubmitProductRegistT_05()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistCompleteRequest l_request =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_request.productId = "123";
            l_request.password = "123456";
            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            
            l_impl.submitProductRegist(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitProductRegistT_06()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistCompleteRequest l_request =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();
            l_request.password = "123456";
            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            //WEB3BondProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "validateProductSpec", 
                new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class}, 
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.bd.WEB3BondProductManager",
                    "updateBondProductContent",
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class, String.class},
                    null);

            WEB3AdminBondDomesticProductRegistCompleteResponse l_response =
                l_impl.submitProductRegist(l_request);

            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue1 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "validateProductSpec", 
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class});

           assertEquals(l_request.productId, l_paramsValue1.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue1.getFirstCalled()[1]);
           
           WEB3MockObjectParamsValue l_paramsValue2 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.bd.WEB3BondProductManager",
                   "updateBondProductContent",
                   new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class, String.class});

           assertEquals(l_request.productId, l_paramsValue2.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue2.getFirstCalled()[1]);
           assertEquals(l_administratorParams.getAdministratorCode(), l_paramsValue2.getFirstCalled()[2]);

           //返回?驗證
           assertEquals(l_administratorParams.getAdministratorCode(), l_response.updaterCode);
           String l_strDate1 = WEB3DateUtility.formatDate(l_response.updateTimeStamp, "yyyy/MM/dd");
           String l_strDate2 = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd");
           assertEquals(l_strDate1, l_strDate2);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSetRecruitEndDate()
    {
        final String STR_METHOD_NAME = " testSetRecruitEndDate()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("020701");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3AdminBondDomesticProductRegistServiceImpl l_impl =
                new WEB3AdminBondDomesticProductRegistServiceImpl();
            WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo = new WEB3BondDomesticProductUpdateInfo();
            l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
                WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_impl.setRecruitEndDate(l_bondDomesticProductUpdateInfo);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSetRecruitEndDate1()
    {
        final String STR_METHOD_NAME = " testSetRecruitEndDate()";
        log.entering(STR_METHOD_NAME);
        try
        {

            WEB3AdminBondDomesticProductRegistServiceImpl l_impl =
                new WEB3AdminBondDomesticProductRegistServiceImpl();
            l_impl.setRecruitEndDate(null);
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
