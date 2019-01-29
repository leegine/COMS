head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFrontNoticeHistoryServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontSortKeyTest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFrontNoticeHistoryServiceImplTest extends TestBaseForMock
{
    public WEB3AdminFrontNoticeHistoryServiceImplTest(String name)
    {
        super(name);
    }

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontSortKeyTest.class);
    
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    WEB3AdminFrontNoticeHistoryServiceImpl l_adminFrontNoticeHistoryServiceImpl =
        new WEB3AdminFrontNoticeHistoryServiceImpl();
    public void test_createSearchConditionCharacterString_C0001()
    {
        String STR_METHOD_NAME = "test_createSearchConditionCharacterString_C0001()";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = null;
        String l_strFrontOrderTradeCode = null;
        String l_strFrontOrderSystemCode = null;
        WEB3AdminFrontNoticeHistoryReferenceRequest l_requestdata = 
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        l_requestdata.productType = "1";
        
        String l_strResult = 
            l_adminFrontNoticeHistoryServiceImpl.createSearchConditionCharacterString(l_strInstitutionCode, 
                l_strFrontOrderTradeCode, l_strFrontOrderSystemCode, l_requestdata);
        
        assertEquals("institution_code = ? and product_type = ? ",l_strResult);
    }
    
    public void test_createSearchConditionCharacterString_C0002()
    {
        String STR_METHOD_NAME = "test_createSearchConditionCharacterString_C0002()";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = null;
        String l_strFrontOrderTradeCode = null;
        String l_strFrontOrderSystemCode = null;
        WEB3AdminFrontNoticeHistoryReferenceRequest l_requestdata = 
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        String l_strResult = 
            l_adminFrontNoticeHistoryServiceImpl.createSearchConditionCharacterString(l_strInstitutionCode, 
                l_strFrontOrderTradeCode, l_strFrontOrderSystemCode, l_requestdata);
        
        assertEquals("institution_code = ? ",l_strResult);
    }
    
    public void test_getInputScreen_C0001()
    {
        String STR_METHOD_NAME = "test_getInputScreen_C0001()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFrontNoticeHistoryInputRequest l_requestdata = null;
        try
        {
      
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setTransactionCategory("C1002");
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            Services.overrideService(WEB3AdminFrontOrderCommonService.class,
                new WEB3AdminFrontOrderCommonServiceImpl());
            WEB3Administrator l_administrator = new WEB3AdministratorForMock(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            l_adminFrontNoticeHistoryServiceImpl.getInputScreen(l_requestdata);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
        } 
        catch (WEB3BaseException e)
        {
            fail();
        }
        
    }
    
    public void test_getInputScreen_C0002()
    {
        String STR_METHOD_NAME = "test_getInputScreen_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFrontNoticeHistoryInputRequest l_requestdata =
            new WEB3AdminFrontNoticeHistoryInputRequest();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C1001");
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3AdministratorForMock(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            
            OrderSwitchingParams l_orderSwitchingParams1 = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams1.setProductType(ProductTypeEnum.IFO);
            l_orderSwitchingParams1.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams1);
            
            Services.overrideService(WEB3AdminFrontOrderCommonService.class, new WEB3AdminFrontOrderCommonServiceImpl());
            WEB3AdminFrontNoticeHistoryInputResponse l_response = l_adminFrontNoticeHistoryServiceImpl
                .getInputScreen(l_requestdata);
            assertEquals("1",l_response.productTypeList[0]);
            assertEquals("6",l_response.productTypeList[1]);
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        } 
        catch (WEB3BaseException e)
        {
            fail();
        }
        
    }
    
    public void test_getProductTypeList_C0001() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getProductTypeList_C0001()";
        log.entering(STR_METHOD_NAME); 
        
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
        String l_strInstitutionCode = "OD";
        try
        {
            String[] l_strResult = l_adminFrontNoticeHistoryServiceImpl
                .getProductTypeList(l_strInstitutionCode);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01279,l_ex.getErrorInfo());
        } 
    }
    
    public void test_getProductTypeList_C0002() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getProductTypeList_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            String l_strInstitutionCode = "0D";
            String[] l_strResult = 
                l_adminFrontNoticeHistoryServiceImpl.getProductTypeList(l_strInstitutionCode);
            assertEquals("1",l_strResult[0]);
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        } 
        
    }
    
    public void test_getProductTypeList_C0003() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getProductTypeList_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            
            OrderSwitchingParams l_orderSwitchingParams1 = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams1.setProductType(ProductTypeEnum.IFO);
            l_orderSwitchingParams1.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams1);
            
            OrderSwitchingParams l_orderSwitchingParams2 = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams2.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams2.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams2);
            
            String l_strInstitutionCode = "0D";
            String[] l_strResult = l_adminFrontNoticeHistoryServiceImpl
                .getProductTypeList(l_strInstitutionCode);
            
            assertEquals("1",l_strResult[0]);
            assertEquals("6",l_strResult[1]);
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        } 
        
    }
}
@
