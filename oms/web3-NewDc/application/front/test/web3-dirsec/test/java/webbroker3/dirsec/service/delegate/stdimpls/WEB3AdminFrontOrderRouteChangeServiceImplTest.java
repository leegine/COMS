head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFrontOrderRouteChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmRequest;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.VirtualServerChangeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFrontOrderRouteChangeServiceImplTest extends TestBaseForMock
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontOrderRouteChangeServiceImplTest.class);

    public WEB3AdminFrontOrderRouteChangeServiceImplTest(String arg0)
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

    public void testValidateChange_0001()
    {
        String STR_METHOD_NAME = "testValidateChange_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminDirSecFrontOrderCommonService.class);
        // 管理者フロント発注管理共通サービス
        Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest());
        // 管理者フロント発注管理共通サービス
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)); 
        // 管理者フロント発注管理共通サービス
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());
        
        WEB3AdminFrontRouteChangeConfirmRequestForTest l_request = new WEB3AdminFrontRouteChangeConfirmRequestForTest();
        l_request.institutionCode = "1234";
        l_request.convertMarketCode = "111";
        l_request.changeStartDiv = "222";
        l_request.marketCode = "333";
        l_request.changeProcessDiv = "2";
        l_request.productType = "444";
        
        WEB3AdminFrontOrderRouteChangeServiceImplForTest l_adminFrontOrderRouteChangeServiceImplForTest = 
            new WEB3AdminFrontOrderRouteChangeServiceImplForTest();
        try
        {
            l_adminFrontOrderRouteChangeServiceImplForTest.validateChange(l_request);
            
            String l_expect = " 証券会社コード = 1234 フロント発注取引所区分コード = 111 フロント発注システム区分 = 111 銘柄タイプ = 444";
            assertEquals(l_expect , WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.toString());
            
            Services.overrideService(WEB3AdminDirSecFrontOrderCommonService.class,new WEB3AdminDirSecFrontOrderCommonServiceImplForMock());
        } catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubmitChange_0001()
    {
        String STR_METHOD_NAME = "testSubmitChange_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        Services.unregisterService(WEB3AdminDirSecFrontOrderCommonService.class);
        // 管理者フロント発注管理共通サービス
        Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest());
        // 管理者フロント発注管理共通サービス
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)); 
        // 管理者フロント発注管理共通サービス
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());
        
        WEB3AdminFrontRouteChangeCompleteRequestForTest l_request = 
            new WEB3AdminFrontRouteChangeCompleteRequestForTest();
        l_request.institutionCode = "1234";
        l_request.convertMarketCode = "111";
        l_request.changeStartDiv = "222";
        l_request.marketCode = "333";
        l_request.changeProcessDiv = "2";
        l_request.productType = "444";
        l_request.serviceStartDiv = "0";
        
        WEB3AdminFrontOrderRouteChangeServiceImplForTest l_adminFrontOrderRouteChangeServiceImplForTest = 
            new WEB3AdminFrontOrderRouteChangeServiceImplForTest();
        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setInstitutionId(123);
            l_administratorParams.setPermissionLevel("1");
            l_administratorParams.setAdministratorCode("123");
            l_administratorParams.setLoginId(123);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, 
                WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH, 
                true, 
                true);
            WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            l_adminFrontOrderRouteChangeServiceImplForTest.submitChange(l_request);
            
            String l_expect = " 証券会社コード = 1234 フロント発注取引所区分コード = 111 フロント発注システム区分 = 111 銘柄タイプ = 444";
            assertEquals(l_expect , WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.toString());
            
            Services.overrideService(WEB3AdminDirSecFrontOrderCommonService.class,new WEB3AdminDirSecFrontOrderCommonServiceImplForMock());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public class WEB3AdminFrontRouteChangeCompleteRequestForTest extends WEB3AdminFrontRouteChangeCompleteRequest
    {
        public void validate() throws WEB3BusinessLayerException
        {
            
        }
        
        public WEB3GenResponse createResponse()
        {
            return null;
        }
    }
    
    public class WEB3AdminFrontOrderRouteChangeServiceImplForTest extends WEB3AdminFrontOrderRouteChangeServiceImpl
    {
        protected boolean getSwitchPointDataCount(String l_institutionCode, String l_marketCode, 
            String l_frontSystemCode, String l_productType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit,
            String l_changeProcessDiv) throws WEB3SystemLayerException 
            {
                l_processInfoUnit.changeStartDiv = "222";
                return false;
            }
        
        protected void createSwitchInfo(String l_proChangeDiv, 
            WEB3AdminFrontProcessNumberInfoUnit l_proProcessInfoUnit, boolean l_boolProSwDiv) 
        {
                    
        }
    }
    
    public class WEB3AdminDirSecFrontOrderCommonServiceImplForTest extends WEB3AdminDirSecFrontOrderCommonServiceImpl
    {
        public String getFrontOrderExchangeCode(String l_strConvertMarketCode) 
        {
            return l_strConvertMarketCode;
        }
        
        public String getFrontSystemDiv(String l_strConvertMarketCode) 
        {
            return l_strConvertMarketCode;
        }
        
        public boolean isFrontRoute(String l_strInstitutionCode, String l_strMarketCode, String l_frontSysDiv, 
            String l_strProductType) throws WEB3SystemLayerException 
        {
            return true;
        }
        
        public void executeSwitchTransactionIssue(WEB3AdminFrontRouteChangeCompleteRequest l_request) throws WEB3SystemLayerException 
        {
            
        }
        
        public void validateSonarCheck(String l_institutionCode, String l_frontExCode, 
            String l_frontSystemCode, String l_strProductType) throws WEB3BusinessLayerException, WEB3SystemLayerException 
        {
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface = new StringBuffer();
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append(" 証券会社コード = " + l_institutionCode);
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append(" フロント発注取引所区分コード = " + l_frontExCode);
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append(" フロント発注システム区分 = " + l_frontSystemCode);
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append(" 銘柄タイプ = " + l_strProductType);
        }
    }
    
    public class WEB3AdminFrontRouteChangeConfirmRequestForTest extends WEB3AdminFrontRouteChangeConfirmRequest
    {
        public void validate() throws WEB3BusinessLayerException
        {
            
        }
        
        public WEB3GenResponse createResponse()
        {
            return null;
            
        }
    }
    
    public void test_submitChange_0001()
    {

        Services.overrideService(WEB3AdminDirSecFrontOrderCommonService.class, 
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest1());
        
        WEB3AdminFrontOrderRouteChangeServiceImpl l_changeServiceImpl = 
            new WEB3AdminFrontOrderRouteChangeServiceImpl();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.serviceStartDiv = "1";
        l_request.password = "12";
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
        
        try
        {
            l_changeServiceImpl.submitChange(l_request);
            fail();
        } 
        catch (DataNetworkException e) 
        {
            e.printStackTrace();
            fail();
        } 
        catch (DataQueryException e)
        {
            e.printStackTrace();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02216, l_ex.getErrorInfo());
            
            Services.overrideService(WEB3AdminDirSecFrontOrderCommonService.class,new WEB3AdminDirSecFrontOrderCommonServiceImplForMock());
        }
    }
    
    public class WEB3AdminDirSecFrontOrderCommonServiceImplForTest1 extends WEB3AdminDirSecFrontOrderCommonServiceImpl
    {
        public String getFrontOrderExchangeCode(String l_strConvertMarketCode)
        {
            return "1";
        }
        
        public String getFrontSystemDiv(String l_strConvertMarketCode)
        {
            return "1";
        }
        
        public String getDataCode(String l_strSwitchBootDiv,String l_changeStartDiv)
        {
            return null;
        }
        
        public String getUserData(String l_strConvertMarketCode, String l_strProductType, String l_strChangeStartDiv)
        {
            return null;
        }
        
        public boolean isFrontRoute(String l_strInstitutionCode, String l_strMarketCode, String l_frontSysDiv, 
            String l_strProductType) throws WEB3SystemLayerException
        {
            return false;
        }
    }
    
    //切替指示要求レコード.フロント発注取引所区分コード = "東証"の場合、
    //"通知再送要求（受付系）：未処理", "通知再送要求（約定系）：未処理"をカウントアップ。
    public void testSetSwitchPointReqCount_0001()
    {
        final String STR_METHOD_NAME = "testSetSwitchPointReqCount_0001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_serviceImpl = new WEB3AdminFrontOrderRouteChangeServiceImpl();
            VirtualServerChangeParams[] l_virtualServerChangeParams = new VirtualServerChangeParams[3];
            l_virtualServerChangeParams[0] = new VirtualServerChangeParams();
            l_virtualServerChangeParams[0].setChangeReqResDiv("05");
            l_virtualServerChangeParams[0].setFrontOrderExchangeCode("1");
            List l_priReqList = new ArrayList();
                l_priReqList.add(l_virtualServerChangeParams[0]);
            WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
            Method l_method =
                WEB3AdminFrontOrderRouteChangeServiceImpl.class.getDeclaredMethod("setSwitchPointReqCount", 
                    new Class[]{ List.class, WEB3AdminFrontProcessNumberInfoUnit.class });
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_priReqList, l_PriProcessInfoUnit});
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber3));
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber4));
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber5));            
        }
        catch(Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //切替指示要求レコード.フロント発注取引所区分コード != "東証" or "名証"の場合。
    public void testSetSwitchPointReqCount_0002()
    {
        final String STR_METHOD_NAME = "testSetSwitchPointReqCount_0002";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_serviceImpl = new WEB3AdminFrontOrderRouteChangeServiceImpl();
            VirtualServerChangeParams[] l_virtualServerChangeParams = new VirtualServerChangeParams[1];
            l_virtualServerChangeParams[0] = new VirtualServerChangeParams();
            l_virtualServerChangeParams[0].setChangeReqResDiv("05");
            l_virtualServerChangeParams[0].setFrontOrderExchangeCode("2");
            List l_priReqList = new ArrayList();
                l_priReqList.add(l_virtualServerChangeParams[0]);
            WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
            Method l_method =
                WEB3AdminFrontOrderRouteChangeServiceImpl.class.getDeclaredMethod("setSwitchPointReqCount", 
                    new Class[]{ List.class, WEB3AdminFrontProcessNumberInfoUnit.class });
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_priReqList, l_PriProcessInfoUnit});
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber3));
            assertEquals(0,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber4));
            assertEquals(0,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber5));            
        }
        catch(Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //切替指示要求レコード.フロント発注取引所区分コード = "名証"の場合、
    //"通知再送要求（受付系）：未処理", "通知再送要求（約定系）：未処理"をカウントアップ。
    public void testSetSwitchPointReqCount_0003()
    {
        final String STR_METHOD_NAME = "testSetSwitchPointReqCount_0003";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_serviceImpl = new WEB3AdminFrontOrderRouteChangeServiceImpl();
            VirtualServerChangeParams[] l_virtualServerChangeParams = new VirtualServerChangeParams[1];
            l_virtualServerChangeParams[0] = new VirtualServerChangeParams();
            l_virtualServerChangeParams[0].setChangeReqResDiv("05");
            l_virtualServerChangeParams[0].setFrontOrderExchangeCode("3");
            List l_priReqList = new ArrayList();
                l_priReqList.add(l_virtualServerChangeParams[0]);
            WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
            Method l_method =
                WEB3AdminFrontOrderRouteChangeServiceImpl.class.getDeclaredMethod("setSwitchPointReqCount", 
                    new Class[]{ List.class, WEB3AdminFrontProcessNumberInfoUnit.class });
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_priReqList, l_PriProcessInfoUnit});
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber3));
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber4));
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber5));            
        }
        catch(Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //切替指示応答レコード.フロント発注取引所区分コード = "東証"の場合、
    //"通知再送応答（受付系）：処理済", "通知再送応答（約定系）：処理済"をカウントアップ。
    public void testSetSwitchPointResCount_0001()
    {
        final String STR_METHOD_NAME = "testSetSwitchPointResCount_0001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_serviceImpl = new WEB3AdminFrontOrderRouteChangeServiceImpl();
            VirtualServerChangeParams[] l_virtualServerChangeParams = new VirtualServerChangeParams[1];
            l_virtualServerChangeParams[0] = new VirtualServerChangeParams();
            l_virtualServerChangeParams[0].setChangeReqResDiv("06");
            l_virtualServerChangeParams[0].setFrontOrderExchangeCode("1");
            l_virtualServerChangeParams[0].setStatus("1");
            String  l_PrichangeProcessDiv = "1";
            List l_priReqList = new ArrayList();
                l_priReqList.add(l_virtualServerChangeParams[0]);
            WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
            l_PriProcessInfoUnit.virtualServerQuantity = "1";
            Method l_method = WEB3AdminFrontOrderRouteChangeServiceImpl.class.
                getDeclaredMethod("setSwitchPointResCount", 
                                          new Class[]{List.class, WEB3AdminFrontProcessNumberInfoUnit.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_priReqList, l_PriProcessInfoUnit, l_PrichangeProcessDiv});
            
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber3));
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber4));
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber5));            
        }
        catch(Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //切替指示応答レコード.フロント発注取引所区分コード != "東証" or "名証"の場合。
    public void testSetSwitchPointResCount_0002()
    {
        final String STR_METHOD_NAME = "testSetSwitchPointResCount_0002";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_serviceImpl = new WEB3AdminFrontOrderRouteChangeServiceImpl();
            VirtualServerChangeParams[] l_virtualServerChangeParams = new VirtualServerChangeParams[1];
            l_virtualServerChangeParams[0] = new VirtualServerChangeParams();
            l_virtualServerChangeParams[0].setChangeReqResDiv("06");
            l_virtualServerChangeParams[0].setFrontOrderExchangeCode("2");
            l_virtualServerChangeParams[0].setStatus("1");
            String  l_PrichangeProcessDiv = "1";
            List l_priReqList = new ArrayList();
                l_priReqList.add(l_virtualServerChangeParams[0]);
            WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
            l_PriProcessInfoUnit.virtualServerQuantity = "1";
            Method l_method = WEB3AdminFrontOrderRouteChangeServiceImpl.class.
                getDeclaredMethod("setSwitchPointResCount", 
                                          new Class[]{List.class, WEB3AdminFrontProcessNumberInfoUnit.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_priReqList, l_PriProcessInfoUnit, l_PrichangeProcessDiv});
            
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber3));
            assertEquals(0,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber4));
            assertEquals(0,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber5));            
        }
        catch(Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //切替指示応答レコード.フロント発注取引所区分コード = "名証"の場合、
    //"通知再送応答（受付系）：処理済", "通知再送応答（約定系）：処理済"をカウントアップ。
    public void testSetSwitchPointResCount_0003()
    {
        final String STR_METHOD_NAME = "testSetSwitchPointResCount_0003";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_serviceImpl = new WEB3AdminFrontOrderRouteChangeServiceImpl();
            VirtualServerChangeParams[] l_virtualServerChangeParams = new VirtualServerChangeParams[1];
            l_virtualServerChangeParams[0] = new VirtualServerChangeParams();
            l_virtualServerChangeParams[0].setChangeReqResDiv("06");
            l_virtualServerChangeParams[0].setFrontOrderExchangeCode("3");
            l_virtualServerChangeParams[0].setStatus("1");
            String  l_PrichangeProcessDiv = "1";
            List l_priReqList = new ArrayList();
                l_priReqList.add(l_virtualServerChangeParams[0]);
            WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
            l_PriProcessInfoUnit.virtualServerQuantity = "1";
            Method l_method = WEB3AdminFrontOrderRouteChangeServiceImpl.class.
                getDeclaredMethod("setSwitchPointResCount", 
                                          new Class[]{List.class, WEB3AdminFrontProcessNumberInfoUnit.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_priReqList, l_PriProcessInfoUnit, l_PrichangeProcessDiv});
            
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber3));
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber4));
            assertEquals(1,Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber5));            
        }
        catch(Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
