head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FrontOrderSwitchManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImplTest;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.MarketNoticeManagementParams;
import webbroker3.equity.data.MarketNoticeManagementRow;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.VirtualServerChangeParams;
import webbroker3.gentrade.data.VirtualServerChangeRow;
import webbroker3.gentrade.data.VirtualServerInformationParams;
import webbroker3.gentrade.data.VirtualServerInformationRow;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (発注経路切替処理クラス)<BR>
 *
 * @@author 孟亜南(中訊)
 * @@version 1.0
 */
public class WEB3FrontOrderSwitchManagementTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoReferenceServiceImplTest.class);

    public WEB3FrontOrderSwitchManagementTest(String name)
    {
        super(name);
    }

    //getFinalNoticeNo
    public void test_getFinalNoticeNo_0001()
    {
        final String STR_METHOD_NAME = "test_getFinalNoticeNo_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecFrontOrderCommonService l_service =
                new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();

            WEB3AdminFrontRouteChangeCompleteRequest l_request =
                new WEB3AdminFrontRouteChangeCompleteRequest();

            l_request.institutionCode = "555";
            l_request.marketCode = "2";
            l_request.productType = "6";
            l_request.changeProcessDiv = "4";
            l_request.submitOrderRouteDiv = "5";
            l_request.convertMarketCode = "3";
            l_request.changeStartDiv = "7";
           
            WEB3FrontOrderSwitchManagement l_management =
                new WEB3FrontOrderSwitchManagement(l_service,l_request);
            
            Method l_method =
                WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "getFinalNoticeNo", 
                    new Class[]{String.class,String.class,String.class});
            l_method.setAccessible(true);
            String l_strFinalNoticeNo = (String)l_method.invoke(l_management, new Object[]{"", "", "1"});//東証  
            assertEquals("0", l_strFinalNoticeNo);
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_getFinalNoticeNo_0002()
    {
        final String STR_METHOD_NAME = "test_getFinalNoticeNo_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecFrontOrderCommonService l_service =
                new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();

            WEB3AdminFrontRouteChangeCompleteRequest l_request =
                new WEB3AdminFrontRouteChangeCompleteRequest();

            l_request.institutionCode = "555";
            l_request.marketCode = "2";
            l_request.productType = "6";
            l_request.changeProcessDiv = "4";
            l_request.submitOrderRouteDiv = "5";
            l_request.convertMarketCode = "3";
            l_request.changeStartDiv = "7";
           
            WEB3FrontOrderSwitchManagement l_management =
                new WEB3FrontOrderSwitchManagement(l_service,l_request);
            
            Method l_method =
                WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "getFinalNoticeNo", 
                    new Class[]{String.class,String.class,String.class});
            l_method.setAccessible(true);
            String l_strFinalNoticeNo = (String)l_method.invoke(l_management, new Object[]{"", "", "3"});//名証  
            assertEquals("0", l_strFinalNoticeNo);
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_getFinalNoticeNo_0003()
    {
        final String STR_METHOD_NAME = "test_getFinalNoticeNo_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecFrontOrderCommonService l_service =
                new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();

            WEB3AdminFrontRouteChangeCompleteRequest l_request =
                new WEB3AdminFrontRouteChangeCompleteRequest();

            l_request.institutionCode = "555";
            l_request.marketCode = "2";
            l_request.productType = "6";
            l_request.changeProcessDiv = "4";
            l_request.submitOrderRouteDiv = "5";
            l_request.convertMarketCode = "3";
            l_request.changeStartDiv = "7";
           
            WEB3FrontOrderSwitchManagement l_management =
                new WEB3FrontOrderSwitchManagement(l_service,l_request);
            
            Method l_method =
                WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "getFinalNoticeNo", 
                    new Class[]{String.class,String.class,String.class});
            l_method.setAccessible(true);
            String l_strFinalNoticeNo = (String)l_method.invoke(l_management, new Object[]{"", "", "2"});//大証  
            assertEquals("1", l_strFinalNoticeNo);
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_getVirtualServerInfo_0001()
    {
        try
        {
            TestDBUtility.deleteAll(VirtualServerInformationRow.TYPE);
            TestDBUtility.insertWithDel(this.getVirtualServerInformationRow());
            VirtualServerInformationParams l_virtualServerInformationParams = this.getVirtualServerInformationRow();
            //銘柄タイプ
            l_virtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
            //仮想サーバNo.（JSOES）
            l_virtualServerInformationParams.setVirtualServerNumberJsoes("654321");
            TestDBUtility.insertWithDel(l_virtualServerInformationParams);
        }
        catch (WEB3BaseException e)
        {
            e.printStackTrace();
            fail();
        }

        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();

        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();

        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        List l_lstVirtualServers = new ArrayList();
        try
        {
            l_lstVirtualServers = l_management.getVirtualServerInfo();
        } 
        catch (WEB3SystemLayerException e)
        {
            e.printStackTrace();
            fail();
        }
        
        assertEquals("1","" + l_lstVirtualServers.size());
        assertEquals("6:IFO","" + ((VirtualServerInformationRow)l_lstVirtualServers.get(0)).getProductType());
    }
    
    public void test_setVirtualServerChangeRecord_0001()
    {
        VirtualServerInformationParams l_virtualServerInformationParams = this.getVirtualServerInformationRow();
        //銘柄タイプ
        l_virtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
        //通知ファ@イルNo.
        l_virtualServerInformationParams.setNoticeFileNumber("999");

        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "1";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        
        //切替指示応答区分
        String l_priReqResDiv = "YY";
        //通知種別
        String l_priNoticeDiv = "ZZ";
        //最終通知No
        String l_priFinalNoticeNo ="12";
        
        VirtualServerChangeParams l_priVirtualServPrams = new VirtualServerChangeParams();
        
        Method method;
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("setVirtualServerChangeRecord", 
                new Class[]{VirtualServerInformationRow.class,String.class,String.class,String.class,String.class,String.class});
            method.setAccessible(true);
            l_priVirtualServPrams = (VirtualServerChangeParams) method.invoke(l_management, new Object[]{l_virtualServerInformationParams,l_priReqResDiv,l_priNoticeDiv,l_priFinalNoticeNo, null, null});
            
            assertEquals("6:IFO","" + l_priVirtualServPrams.product_type);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }

    }
    /*
     * 代行元受付系通知通番 !=null
     * 代行元約定系通知通番 !=null
     */
    public void test_setVirtualServerChangeRecord_0002()
    {
        VirtualServerInformationParams l_virtualServerInformationParams = this.getVirtualServerInformationRow();
        //銘柄タイプ
        l_virtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
        //通知ファ@イルNo.
        l_virtualServerInformationParams.setNoticeFileNumber("999");

        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "1";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        
        //切替指示応答区分
        String l_priReqResDiv = "YY";
        //通知種別
        String l_priNoticeDiv = "ZZ";
        //最終通知No
        String l_priFinalNoticeNo ="12";
        //代行元受付系通知通番
        String l_strAcceptNoticeNo = "1";
        //代行元約定系通知通番
        String l_strExecutedNoticeNo = "2";
        
        VirtualServerChangeParams l_priVirtualServPrams = new VirtualServerChangeParams();
        
        Method method;
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("setVirtualServerChangeRecord", 
                new Class[]{VirtualServerInformationRow.class,String.class,String.class,String.class,String.class,String.class});
            method.setAccessible(true);
            l_priVirtualServPrams = (VirtualServerChangeParams) method.invoke(l_management, new Object[]{
                l_virtualServerInformationParams,
                l_priReqResDiv,
                l_priNoticeDiv,
                l_priFinalNoticeNo,
                l_strAcceptNoticeNo,
                l_strExecutedNoticeNo});
            
            assertEquals("1", "" + l_priVirtualServPrams.agency_accept_notice_number);
            assertEquals("2", "" + l_priVirtualServPrams.agency_exec_notice_number);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }

    }
    
    public void test_getIfoMarketAcceptGrayOrder_0001()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(this.getHostFotypeOrderAllRow());
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("123");
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("getIfoMarketAcceptGrayOrder", 
                new Class[]{});
            method.setAccessible(true);
            List l_grayOrders = (List) method.invoke(l_management, new Object[]{});
            assertEquals("1","" + l_grayOrders.size());
            assertEquals("555","" + ((HostFotypeOrderAllRow)l_grayOrders.get(0)).getInstitutionCode());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    public void test_getIfoMarketAcceptGrayOrder_0002()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(this.getHostFotypeOrderAllRow());
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("getIfoMarketAcceptGrayOrder", 
                new Class[]{});
            method.setAccessible(true);
            List l_grayOrders = (List) method.invoke(l_management, new Object[]{});
            assertEquals("1","" + l_grayOrders.size());
            assertEquals("1","" + ((HostFotypeOrderAllRow)l_grayOrders.get(0)).getFrontOrderExchangeCode());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    public void test_getIfoMarketAcceptGrayOrder_0003()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(this.getHostFotypeOrderAllRow());
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("getIfoMarketAcceptGrayOrder", 
                new Class[]{});
            method.setAccessible(true);
            List l_grayOrders = (List) method.invoke(l_management, new Object[]{});
            assertEquals("1","" + l_grayOrders.size());
            assertEquals("1","" + ((HostFotypeOrderAllRow)l_grayOrders.get(0)).getFrontOrderSystemCode());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    public void test_getIfoMarketAcceptGrayOrder_0004()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(this.getHostFotypeOrderAllRow());
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("getIfoMarketAcceptGrayOrder", 
                new Class[]{});
            method.setAccessible(true);
            List l_grayOrders = (List) method.invoke(l_management, new Object[]{});
            assertEquals("1","" + l_grayOrders.size());
            assertEquals("1","" + ((HostFotypeOrderAllRow)l_grayOrders.get(0)).getFrontOrderTradeCode());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    public void test_getIfoMarketAcceptGrayOrder_0005()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(this.getHostFotypeOrderAllRow());
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("getIfoMarketAcceptGrayOrder", 
                new Class[]{});
            method.setAccessible(true);
            List l_grayOrders = (List) method.invoke(l_management, new Object[]{});
            assertEquals("1","" + l_grayOrders.size());
            assertEquals("2","" + ((HostFotypeOrderAllRow)l_grayOrders.get(0)).getSubmitOrderRouteDiv());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    public void test_getIfoMarketAcceptGrayOrder_0006()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(this.getHostFotypeOrderAllRow());
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("getIfoMarketAcceptGrayOrder", 
                new Class[]{});
            method.setAccessible(true);
            List l_grayOrders = (List) method.invoke(l_management, new Object[]{});
            assertEquals("1","" + l_grayOrders.size());
            assertEquals("1","" + ((HostFotypeOrderAllRow)l_grayOrders.get(0)).getStatus());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    public void test_getIfoMarketAcceptGrayOrder_0007()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(this.getHostFotypeOrderAllRow());
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("333");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("2");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "333";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("getIfoMarketAcceptGrayOrder", 
                new Class[]{});
            method.setAccessible(true);
            List l_grayOrders = (List) method.invoke(l_management, new Object[]{});
            assertEquals("1","" + l_grayOrders.size());
            assertEquals("2","" + ((HostFotypeOrderAllRow)l_grayOrders.get(0)).getStatus());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    public void test_validateIfoHostFotypeRepeat_0001()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = this.getHostFotypeOrderAllRow();
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "333";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        String l_corpCode = "123244444";
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("validateIfoHostFotypeRepeat", 
                new Class[]{String.class});
            method.setAccessible(true);
            Boolean l_bln = (Boolean)method.invoke(l_management, new Object[]{l_corpCode});
            assertTrue(l_bln.booleanValue());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_validateIfoHostFotypeRepeat_0002()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = this.getHostFotypeOrderAllRow();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "333";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        String l_corpCode = "123244444";
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("validateIfoHostFotypeRepeat", 
                new Class[]{String.class});
            method.setAccessible(true);
            Boolean l_bln = (Boolean)method.invoke(l_management, new Object[]{l_corpCode});
            assertFalse(l_bln.booleanValue());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_validateIfoHostFotypeRepeat_0003()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = this.getHostFotypeOrderAllRow();
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "333";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        String l_corpCode = "12324441";
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("validateIfoHostFotypeRepeat", 
                new Class[]{String.class});
            method.setAccessible(true);
            Boolean l_bln = (Boolean)method.invoke(l_management, new Object[]{l_corpCode});
            assertFalse(l_bln.booleanValue());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_validateIfoHostFotypeRepeat_0004()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = this.getHostFotypeOrderAllRow();
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "333";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        String l_corpCode = "12324441";
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("validateIfoHostFotypeRepeat", 
                new Class[]{String.class});
            method.setAccessible(true);
            Boolean l_bln = (Boolean)method.invoke(l_management, new Object[]{l_corpCode});
            assertFalse(l_bln.booleanValue());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_updateIfoGrayOrderStatus_0001()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = this.getHostFotypeOrderAllRow();
            //処理区分
            l_hostFotypeOrderAllParams1.setStatus("4");
            //取消区分
            l_hostFotypeOrderAllParams1.setCancelDiv("5");
            //社内処理項目
            l_hostFotypeOrderAllParams1.setCorpCode("123242");
            //全訂正処理区分
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("2");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("1");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("updateIfoGrayOrderStatus", 
                new Class[]{HostFotypeOrderAllRow.class});
            method.setAccessible(true);
            method.invoke(l_management, new Object[]{l_hostFotypeOrderAllParams});
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
        // ArrayListオブジェクトの生成
        List l_hostOrders = new ArrayList();
        
                // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_jsoes = ? ");
        
                // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                "654321"
            };
            
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_hostOrders = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);
           assertEquals("1","" + l_hostOrders.size());
           assertEquals("123244444",((HostFotypeOrderAllParams)l_hostOrders.get(0)).getCorpCode());
           assertEquals("9",((HostFotypeOrderAllParams)l_hostOrders.get(0)).getStatus());
           assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
               ((HostFotypeOrderAllParams)l_hostOrders.get(0)).getLastUpdatedTimestamp()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_updateIfoGrayOrderStatus_0002()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = this.getHostFotypeOrderAllRow();
            //処理区分
            l_hostFotypeOrderAllParams1.setStatus("4");
            //取消区分
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams1.setCorpCode("123242");
            //全訂正処理区分
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("2");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("2");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("updateIfoGrayOrderStatus", 
                new Class[]{HostFotypeOrderAllRow.class});
            method.setAccessible(true);
            method.invoke(l_management, new Object[]{l_hostFotypeOrderAllParams});
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
        // ArrayListオブジェクトの生成
        List l_hostOrders = new ArrayList();
        
                // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_jsoes = ? ");
        
                // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                "654321"
            };
            
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_hostOrders = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);
           assertEquals("1","" + l_hostOrders.size());
           assertEquals("123244444",((HostFotypeOrderAllParams)l_hostOrders.get(0)).getCorpCode());
           assertEquals("9",((HostFotypeOrderAllParams)l_hostOrders.get(0)).getStatus());
           assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
               ((HostFotypeOrderAllParams)l_hostOrders.get(0)).getLastUpdatedTimestamp()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_updateIfoGrayOrderStatus_0003()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = this.getHostFotypeOrderAllRow();
            //処理区分
            l_hostFotypeOrderAllParams1.setStatus("4");
            //取消区分
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams1.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("2");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("2");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("updateIfoGrayOrderStatus", 
                new Class[]{HostFotypeOrderAllRow.class});
            method.setAccessible(true);
            method.invoke(l_management, new Object[]{l_hostFotypeOrderAllParams});
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
        // ArrayListオブジェクトの生成
        List l_hostOrders = new ArrayList();
        
                // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_jsoes = ? ");
        
                // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                "654321"
            };
            
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_hostOrders = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);
           assertEquals("1","" + l_hostOrders.size());
           assertEquals("123244444",((HostFotypeOrderAllParams)l_hostOrders.get(0)).getCorpCode());
           assertEquals("9",((HostFotypeOrderAllParams)l_hostOrders.get(0)).getStatus());
           assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
               ((HostFotypeOrderAllParams)l_hostOrders.get(0)).getLastUpdatedTimestamp()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_updateIfoGrayOrderStatus_0004()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = this.getHostFotypeOrderAllRow();
            //処理区分
            l_hostFotypeOrderAllParams1.setStatus("4");
            //取消区分
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams1.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("8");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("2");
           
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("updateIfoGrayOrderStatus", 
                new Class[]{HostFotypeOrderAllRow.class});
            method.setAccessible(true);
            method.invoke(l_management, new Object[]{l_hostFotypeOrderAllParams});
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
        // ArrayListオブジェクトの生成
        List l_hostOrders = new ArrayList();
        
                // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_jsoes = ? ");
        
                // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                "654321"
            };
            
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_hostOrders = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);
           assertEquals("1","" + l_hostOrders.size());
           assertEquals("123244444",((HostFotypeOrderAllParams)l_hostOrders.get(0)).getCorpCode());
           assertEquals("9",((HostFotypeOrderAllParams)l_hostOrders.get(0)).getStatus());
           assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
               ((HostFotypeOrderAllParams)l_hostOrders.get(0)).getLastUpdatedTimestamp()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_WEB3FrontOrderSwitchManagement_0001()
    {
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        
        try {
            Field f = l_management.getClass().getDeclaredField("userData");
            f.setAccessible(true);
            String temp = (String)f.get(l_management);
            assertNull(temp);
        } 
        catch (SecurityException e)
        {
            e.printStackTrace();
            fail();
        } 
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
            fail();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        
    }
    
    public void test_executeOrderRouteSwitching_0001()
    {
        Services.overrideService(WEB3MQGatewayService.class, new WEB3MQGatewayServiceImplForTest1());
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagementForTest(l_service,l_request);
        
        try 
        {
            l_management.executeOrderRouteSwitching();
        }
        catch (WEB3SystemLayerException e) 
        {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_executeOrderRouteSwitching_0002()
    {
        try 
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(this.getHostEqtypeOrderAllRow());
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        Services.overrideService(WEB3MQGatewayService.class, new WEB3MQGatewayServiceImplForTest1());
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "1";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagementForTest1(l_service,l_request);
        
        try 
        {
            l_management.executeOrderRouteSwitching();
            fail();
        }
        catch (WEB3SystemLayerException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02208,e.getErrorInfo());
        }
    }
    
    public void test_executeOrderRouteSwitching_0003()
    {
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //口座ＩＤ
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("1");
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("1");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        Services.overrideService(WEB3MQGatewayService.class, new WEB3MQGatewayServiceImplForTest1());
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagementForTest1(l_service,l_request);
        
        try 
        {
            l_management.executeOrderRouteSwitching();
            fail();
        }
        catch (WEB3SystemLayerException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02208,e.getErrorInfo());
        }
    }
    
    public void test_executeOrderRouteSwitching_0004()
    {        
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //口座ＩＤ
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("1");
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("1");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
        Services.overrideService(WEB3MQGatewayService.class, new WEB3MQGatewayServiceImplForTest1());
        
        WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagementForTest1(l_service,l_request);
        
        try 
        {
            l_management.executeOrderRouteSwitching();
            fail();
        }
        catch (WEB3SystemLayerException e) 
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,e.getErrorInfo());
        }
        
        List l_ifOrderList = (List) new ArrayList();       
        try
        {
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            
            // 口座ID昇順指定
            String l_strSort = "submit_order_route_div asc";
                    
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
                {
                "45"
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            
            l_ifOrderList = qp.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        } 
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_ifOrderList.get(0);
        
        assertEquals("2",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        assertEquals("9",l_hostFotypeOrderAllRow.getStatus());
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow1 = (HostFotypeOrderAllRow)l_ifOrderList.get(1);
        
        assertEquals("3",l_hostFotypeOrderAllRow1.getSubmitOrderRouteDiv());
        assertEquals("0",l_hostFotypeOrderAllRow1.getStatus());
        
    }
    
    public void test_createIfoMarketAcceptModifyOrder_0001()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            l_hostFotypeOrderAllParams.setRequestCode("AA");
            //口座ＩＤ
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //部店コード
            l_hostFotypeOrderAllParams.setBranchCode("88");
            //識別コード
            l_hostFotypeOrderAllParams.setOrderRequestNumber("898989");
            //銘柄コード
            l_hostFotypeOrderAllParams.setProductCode("987987");
            //受注日時
            l_hostFotypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("3");
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("2");
            
            //売付数量
            l_hostFotypeOrderAllParams.setSellOrderQuantity(123D);
            //訂正数量
            l_hostFotypeOrderAllParams.setChangeQuantity(321D);
            //買付数量
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(313D);
            //指値
            l_hostFotypeOrderAllParams.setLimitPrice(412D);
            //訂正指値
            l_hostFotypeOrderAllParams.setChangeLimitPrice(567D);
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
       WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("createIfoMarketAcceptModifyOrder", 
                new Class[]{HostFotypeOrderAllRow.class});
            method.setAccessible(true);
            method.invoke(l_management, new Object[]{l_hostFotypeOrderAllParams});
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
        List l_ifOrderList = (List) new ArrayList();       
        try
        {
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            
            // 口座ID昇順指定
            String l_strSort = "submit_order_route_div asc";
                    
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
                {
                "EI802"
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            
            l_ifOrderList = qp.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        } 
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_ifOrderList.get(0);
        
        //口座ＩＤ
        assertEquals("45","" + l_hostFotypeOrderAllRow.getAccountId());
        //証券会社コード
        assertEquals("555",l_hostFotypeOrderAllRow.getInstitutionCode());
        //部店コード
        assertEquals("88",l_hostFotypeOrderAllRow.getBranchCode());
        //識別コード
        assertEquals("898989",l_hostFotypeOrderAllRow.getOrderRequestNumber());
        //銘柄コード
        assertEquals("987987",l_hostFotypeOrderAllRow.getProductCode());
        //受注日時
        assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
            l_hostFotypeOrderAllRow.getReceivedDateTime()));
        //発注経路区分
        assertEquals("3",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        //取消区分
        assertEquals("0",l_hostFotypeOrderAllRow.getCancelDiv());
        //フロント発注取引所区分コード
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());   
        //フロント発注システム区分
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());   
        //フロント発注取引区分コード
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());   
        //社内処理項目
        assertEquals("123244444",l_hostFotypeOrderAllRow.getCorpCode());   
        //（被訂正）社内処理項目
        assertEquals("123244444",l_hostFotypeOrderAllRow.getOrgCorpCode());   
        //全訂正処理区分
        assertEquals("1",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());   
        //処理区分
        assertEquals("0",l_hostFotypeOrderAllRow.getStatus()); 
        
        //売付数量
        assertEquals("" + new Double(321D),"" + l_hostFotypeOrderAllRow.getSellOrderQuantity()); 
        //訂正数量  
        assertEquals("" + new Double(321D),"" + l_hostFotypeOrderAllRow.getChangeQuantity()); 
        //買付数量
        assertEquals("" + new Double(321D),"" + l_hostFotypeOrderAllRow.getBuyOrderQuantity()); 
        //指値
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getLimitPrice()); 
        //訂正指値
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getChangeLimitPrice()); 
        
    }
    
    public void test_createIfoMarketAcceptModifyOrder_0002()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            l_hostFotypeOrderAllParams.setFutureOptionProductType("F");
            
            l_hostFotypeOrderAllParams.setRequestCode("AA");
            //口座ＩＤ
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //部店コード
            l_hostFotypeOrderAllParams.setBranchCode("88");
            //識別コード
            l_hostFotypeOrderAllParams.setOrderRequestNumber("898989");
            //銘柄コード
            l_hostFotypeOrderAllParams.setProductCode("987987");
            //受注日時
            l_hostFotypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("3");
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("2");
            
            //売付数量
            l_hostFotypeOrderAllParams.setSellOrderQuantity(0D);
            //訂正数量
            l_hostFotypeOrderAllParams.setChangeQuantity(321D);
            //買付数量
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(0D);
            //指値
            l_hostFotypeOrderAllParams.setLimitPrice(412D);
            //訂正指値
//            l_hostFotypeOrderAllParams.setChangeLimitPrice(567D);
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
       WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("createIfoMarketAcceptModifyOrder", 
                new Class[]{HostFotypeOrderAllRow.class});
            method.setAccessible(true);
            method.invoke(l_management, new Object[]{l_hostFotypeOrderAllParams});
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
        List l_ifOrderList = (List) new ArrayList();       
        try
        {
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            
            // 口座ID昇順指定
            String l_strSort = "submit_order_route_div asc";
                    
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
                {
                "EI804"
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            
            l_ifOrderList = qp.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        } 
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_ifOrderList.get(0);
        
        //口座ＩＤ
        assertEquals("45","" + l_hostFotypeOrderAllRow.getAccountId());
        //証券会社コード
        assertEquals("555",l_hostFotypeOrderAllRow.getInstitutionCode());
        //部店コード
        assertEquals("88",l_hostFotypeOrderAllRow.getBranchCode());
        //識別コード
        assertEquals("898989",l_hostFotypeOrderAllRow.getOrderRequestNumber());
        //銘柄コード
        assertEquals("987987",l_hostFotypeOrderAllRow.getProductCode());
        //受注日時
        assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
            l_hostFotypeOrderAllRow.getReceivedDateTime()));
        //発注経路区分
        assertEquals("3",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        //取消区分
        assertEquals("0",l_hostFotypeOrderAllRow.getCancelDiv());
        //フロント発注取引所区分コード
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());   
        //フロント発注システム区分
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());   
        //フロント発注取引区分コード
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());   
        //社内処理項目
        assertEquals("123244444",l_hostFotypeOrderAllRow.getCorpCode());   
        //（被訂正）社内処理項目
        assertEquals("123244444",l_hostFotypeOrderAllRow.getOrgCorpCode());   
        //全訂正処理区分
        assertEquals("1",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());   
        //処理区分
        assertEquals("0",l_hostFotypeOrderAllRow.getStatus()); 
        
        //売付数量
        assertEquals("" + new Double(0D),"" + l_hostFotypeOrderAllRow.getSellOrderQuantity()); 
        //訂正数量  
        assertEquals("" + new Double(321D),"" + l_hostFotypeOrderAllRow.getChangeQuantity()); 
        //買付数量
        assertEquals("" + new Double(0D),"" + l_hostFotypeOrderAllRow.getBuyOrderQuantity()); 
        //指値
        assertEquals("" + new Double(412D),"" + l_hostFotypeOrderAllRow.getLimitPrice()); 
        //訂正指値
        assertEquals("" + new Double(412D),"" + l_hostFotypeOrderAllRow.getChangeLimitPrice()); 
        
    }
    
    public void test_createIfoMarketAcceptModifyOrder_0003()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            l_hostFotypeOrderAllParams.setRequestCode("AA");
            //口座ＩＤ
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //部店コード
            l_hostFotypeOrderAllParams.setBranchCode("88");
            //識別コード
            l_hostFotypeOrderAllParams.setOrderRequestNumber("898989");
            //銘柄コード
            l_hostFotypeOrderAllParams.setProductCode("987987");
            //受注日時
            l_hostFotypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("3");
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("2");
            
            //売付数量
            l_hostFotypeOrderAllParams.setSellOrderQuantity(123D);
            //訂正数量
//            l_hostFotypeOrderAllParams.setChangeQuantity(321D);
            //買付数量
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(313D);
            //指値
            l_hostFotypeOrderAllParams.setLimitPrice(412D);
            //訂正指値
            l_hostFotypeOrderAllParams.setChangeLimitPrice(567D);
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
       WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("createIfoMarketAcceptModifyOrder", 
                new Class[]{HostFotypeOrderAllRow.class});
            method.setAccessible(true);
            method.invoke(l_management, new Object[]{l_hostFotypeOrderAllParams});
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
        List l_ifOrderList = (List) new ArrayList();       
        try
        {
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            
            // 口座ID昇順指定
            String l_strSort = "submit_order_route_div asc";
                    
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
                {
                "EI802"
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            
            l_ifOrderList = qp.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        } 
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_ifOrderList.get(0);
        
        //口座ＩＤ
        assertEquals("45","" + l_hostFotypeOrderAllRow.getAccountId());
        //証券会社コード
        assertEquals("555",l_hostFotypeOrderAllRow.getInstitutionCode());
        //部店コード
        assertEquals("88",l_hostFotypeOrderAllRow.getBranchCode());
        //識別コード
        assertEquals("898989",l_hostFotypeOrderAllRow.getOrderRequestNumber());
        //銘柄コード
        assertEquals("987987",l_hostFotypeOrderAllRow.getProductCode());
        //受注日時
        assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
            l_hostFotypeOrderAllRow.getReceivedDateTime()));
        //発注経路区分
        assertEquals("3",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        //取消区分
        assertEquals("0",l_hostFotypeOrderAllRow.getCancelDiv());
        //フロント発注取引所区分コード
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());   
        //フロント発注システム区分
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());   
        //フロント発注取引区分コード
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());   
        //社内処理項目
        assertEquals("123244444",l_hostFotypeOrderAllRow.getCorpCode());   
        //（被訂正）社内処理項目
        assertEquals("123244444",l_hostFotypeOrderAllRow.getOrgCorpCode());   
        //全訂正処理区分
        assertEquals("1",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());   
        //処理区分
        assertEquals("0",l_hostFotypeOrderAllRow.getStatus()); 
        
        //売付数量
        assertEquals("" + new Double(123D),"" + l_hostFotypeOrderAllRow.getSellOrderQuantity()); 
        //訂正数量  
        assertEquals("" + new Double(123D),"" + l_hostFotypeOrderAllRow.getChangeQuantity()); 
        //買付数量
        assertEquals("" + new Double(313D),"" + l_hostFotypeOrderAllRow.getBuyOrderQuantity()); 
        //指値
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getLimitPrice()); 
        //訂正指値
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getChangeLimitPrice()); 
        
    }
    
    public void test_createIfoMarketAcceptModifyOrder_0004()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
        try 
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            l_hostFotypeOrderAllParams.setRequestCode("AA");
            //口座ＩＤ
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //部店コード
            l_hostFotypeOrderAllParams.setBranchCode("88");
            //識別コード
            l_hostFotypeOrderAllParams.setOrderRequestNumber("898989");
            //銘柄コード
            l_hostFotypeOrderAllParams.setProductCode("987987");
            //受注日時
            l_hostFotypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("3");
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("2");
            
            //売付数量
            l_hostFotypeOrderAllParams.setSellOrderQuantity(0D);
            //訂正数量
//            l_hostFotypeOrderAllParams.setChangeQuantity(321D);
            //買付数量
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(313D);
            //指値
            l_hostFotypeOrderAllParams.setLimitPrice(412D);
            //訂正指値
            l_hostFotypeOrderAllParams.setChangeLimitPrice(567D);
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        } 
        catch (WEB3BaseException e) 
        {
            e.printStackTrace();
            fail();
        }
        
       WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
        
        WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
        
        l_request.institutionCode = "555";
        l_request.marketCode = "2";
        l_request.productType = "6";
        l_request.changeProcessDiv = "4";
        l_request.submitOrderRouteDiv = "5";
        l_request.convertMarketCode = "3";
        l_request.changeStartDiv = "7";
       
        WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
        Method method;
        
        try 
        {
            method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod("createIfoMarketAcceptModifyOrder", 
                new Class[]{HostFotypeOrderAllRow.class});
            method.setAccessible(true);
            method.invoke(l_management, new Object[]{l_hostFotypeOrderAllParams});
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
        
        List l_ifOrderList = (List) new ArrayList();       
        try
        {
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            
            // 口座ID昇順指定
            String l_strSort = "submit_order_route_div asc";
                    
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
                {
                "EI802"
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            
            l_ifOrderList = qp.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        } 
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_ifOrderList.get(0);
        
        //口座ＩＤ
        assertEquals("45","" + l_hostFotypeOrderAllRow.getAccountId());
        //証券会社コード
        assertEquals("555",l_hostFotypeOrderAllRow.getInstitutionCode());
        //部店コード
        assertEquals("88",l_hostFotypeOrderAllRow.getBranchCode());
        //識別コード
        assertEquals("898989",l_hostFotypeOrderAllRow.getOrderRequestNumber());
        //銘柄コード
        assertEquals("987987",l_hostFotypeOrderAllRow.getProductCode());
        //受注日時
        assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
            l_hostFotypeOrderAllRow.getReceivedDateTime()));
        //発注経路区分
        assertEquals("3",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        //取消区分
        assertEquals("0",l_hostFotypeOrderAllRow.getCancelDiv());
        //フロント発注取引所区分コード
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());   
        //フロント発注システム区分
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());   
        //フロント発注取引区分コード
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());   
        //社内処理項目
        assertEquals("123244444",l_hostFotypeOrderAllRow.getCorpCode());   
        //（被訂正）社内処理項目
        assertEquals("123244444",l_hostFotypeOrderAllRow.getOrgCorpCode());   
        //全訂正処理区分
        assertEquals("1",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());   
        //処理区分
        assertEquals("0",l_hostFotypeOrderAllRow.getStatus()); 
        
        //売付数量
        assertEquals("" + new Double(0D),"" + l_hostFotypeOrderAllRow.getSellOrderQuantity()); 
        //訂正数量  
        assertEquals("" + new Double(313D),"" + l_hostFotypeOrderAllRow.getChangeQuantity()); 
        //買付数量
        assertEquals("" + new Double(313D),"" + l_hostFotypeOrderAllRow.getBuyOrderQuantity()); 
        //指値
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getLimitPrice()); 
        //訂正指値
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getChangeLimitPrice()); 
        
    }

    public void testUpdateOrderRouteInvalidity_0001()
    {
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            TestDBUtility.deleteAllAndCommit(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams);
            
            WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            l_request.institutionCode = "0D";
            l_request.marketCode = "N1";
            l_request.productType = "6";
            l_request.changeProcessDiv = "4";
            l_request.submitOrderRouteDiv = "0";
            l_request.convertMarketCode = "1";
            l_request.changeStartDiv = "7";
           
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);
            
            l_management.updateOrderRouteInvalidity();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // 検索条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and market_code = ? ");        
            l_sbWhere.append(" and front_order_system_code = ? ");
            l_sbWhere.append(" and product_type = ? ");
            l_sbWhere.append(" and submit_order_route_div = ? ");
            
            // 検索条件コンテナの生成
            Object[] l_objWhere =
                {
                    "0D",
                    "N1",
                    "1",
                    "6",
                    "0"
                };
            
            List l_list =
                l_queryProcessor.doFindAllQuery(OrderSwitchingRow.TYPE,l_sbWhere.toString(),l_objWhere);
            
            OrderSwitchingRow l_orderSwitchingRow = (OrderSwitchingRow)l_list.get(0);
            
            assertEquals("0", l_orderSwitchingRow.getValidFlag());
            assertEquals("6:IFO", l_orderSwitchingRow.getProductType().toString());
        }
        catch (Exception l_ex)
        {
            
        }
    }

    /*
     * if　@切替指示応答区分 = "通知代行要求" かつ
     * 仮想サーバ情報テーブルRow.フロント発注取引所区分コード = "東証"の場合、
     */
    public void testInsertVirtualServerChange_0001()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            String l_changeReqResDiv = "05";
            String l_noticeType = "1";
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("1");
            
            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams1 = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams1.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams1.setNoticeType("01");
            l_MarketNoticeManagementParams1.setNoticeNumber(2002);
            l_MarketNoticeManagementParams1.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams1.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams1.setBizDateCount(0);
            l_MarketNoticeManagementParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            MarketNoticeManagementParams l_MarketNoticeManagementParams2 = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams2.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams2.setNoticeType("01");
            l_MarketNoticeManagementParams2.setNoticeNumber(2002);
            l_MarketNoticeManagementParams2.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams2.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams2.setBizDateCount(0);
            l_MarketNoticeManagementParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams1);
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams2);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            l_management.insertVirtualServerChange(l_changeReqResDiv, l_noticeType, l_virtualInfoParams);
            
            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "05", "1", "1"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
            
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * if　@切替指示応答区分 = "通知代行要求" かつ
     * 仮想サーバ情報テーブルRow.フロント発注取引所区分コード = "名証"の場合、
     */
    public void testInsertVirtualServerChange_0002()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            String l_changeReqResDiv = "05";
            String l_noticeType = "1";
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("3");
            
            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams1 = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams1.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams1.setNoticeType("01");
            l_MarketNoticeManagementParams1.setNoticeNumber(2002);
            l_MarketNoticeManagementParams1.setFrontOrderExchangeCode("3");
            l_MarketNoticeManagementParams1.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams1.setBizDateCount(0);
            l_MarketNoticeManagementParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            MarketNoticeManagementParams l_MarketNoticeManagementParams2 = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams2.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams2.setNoticeType("01");
            l_MarketNoticeManagementParams2.setNoticeNumber(2002);
            l_MarketNoticeManagementParams2.setFrontOrderExchangeCode("3");
            l_MarketNoticeManagementParams2.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams2.setBizDateCount(0);
            l_MarketNoticeManagementParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams1);
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams2);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            l_management.insertVirtualServerChange(l_changeReqResDiv, l_noticeType, l_virtualInfoParams);
            
            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "05", "1", "3"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
            
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 切替指示応答区分が通知再送要求の場合、最終通知Noを取得
     */
    public void testInsertVirtualServerChange_0003()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            String l_changeReqResDiv = "07";
            String l_noticeType = "1";
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("1");
            
            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams.setNoticeType("01");
            l_MarketNoticeManagementParams.setNoticeNumber(2002);
            l_MarketNoticeManagementParams.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams.setBizDateCount(0);
            l_MarketNoticeManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            l_management.insertVirtualServerChange(l_changeReqResDiv, l_noticeType, l_virtualInfoParams);
            
            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "07", "1", "1"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
            
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    
    /*
     * 切替処理方式が通番照会処理の場合、通番照会要求レコードを登録
     * 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
     */
    public void testExecuteVirtualServChange_C0001()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("1");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            VirtualServerChangeParams l_virtualServerChangeParams = TestDBUtility.getVirtualServerChangeRow();
            l_virtualServerChangeParams.setVirtualServerNumberMarket("1002");
            l_virtualServerChangeParams.setChangeReqResDiv("01");
            l_virtualServerChangeParams.setNoticeType("00");
            l_virtualServerChangeParams.setFrontOrderExchangeCode("1");
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            assertEquals(WEB3SystemLayerException.class,
                l_exITE.getTargetException().getClass());
            WEB3SystemLayerException l_targetException =
                (WEB3SystemLayerException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_exIAE)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 切替処理方式が通番照会処理の場合、通番照会要求レコードを登録
     * 障害仮想サーバ切替管理テーブルにレコードが不存在する場合
     */
    public void testExecuteVirtualServChange_C0002()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("1");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams.setNoticeType("01");
            l_MarketNoticeManagementParams.setNoticeNumber(2002);
            l_MarketNoticeManagementParams.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams.setBizDateCount(0);
            l_MarketNoticeManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "01", "00", "1"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知代行解除要求レコードを登録
     * 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
     */
    public void testExecuteVirtualServChange_C0003()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("1");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            VirtualServerChangeParams l_virtualServerChangeParams = TestDBUtility.getVirtualServerChangeRow();
            l_virtualServerChangeParams.setVirtualServerNumberMarket("1002");
            l_virtualServerChangeParams.setChangeReqResDiv("03");
            l_virtualServerChangeParams.setNoticeType("00");
            l_virtualServerChangeParams.setFrontOrderExchangeCode("1");
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            assertEquals(WEB3SystemLayerException.class,
                l_exITE.getTargetException().getClass());
            WEB3SystemLayerException l_targetException =
                (WEB3SystemLayerException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_exIAE)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知代行解除要求レコードを登録
     * 障害仮想サーバ切替管理テーブルにレコードが不存在する場合
     */
    public void testExecuteVirtualServChange_C0004()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("1");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams.setNoticeType("01");
            l_MarketNoticeManagementParams.setNoticeNumber(2002);
            l_MarketNoticeManagementParams.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams.setBizDateCount(0);
            l_MarketNoticeManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "03", "00", "1"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知代行要求レコードを登録
     * 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
     */
    public void testExecuteVirtualServChange_C0005()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("1");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            VirtualServerChangeParams l_virtualServerChangeParams = TestDBUtility.getVirtualServerChangeRow();
            l_virtualServerChangeParams.setVirtualServerNumberMarket("1002");
            l_virtualServerChangeParams.setChangeReqResDiv("05");
            l_virtualServerChangeParams.setNoticeType("00");
            l_virtualServerChangeParams.setFrontOrderExchangeCode("1");
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            assertEquals(WEB3SystemLayerException.class,
                l_exITE.getTargetException().getClass());
            WEB3SystemLayerException l_targetException =
                (WEB3SystemLayerException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_exIAE)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知代行要求レコードを登録
     * 障害仮想サーバ切替管理テーブルにレコードが不存在する場合
     * 仮想サーバ情報テーブルRow.フロント発注取引所区分コード = "東証"の場合、
     */
    public void testExecuteVirtualServChange_C0006()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("1");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams1 = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams1.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams1.setNoticeType("01");
            l_MarketNoticeManagementParams1.setNoticeNumber(2002);
            l_MarketNoticeManagementParams1.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams1.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams1.setBizDateCount(0);
            l_MarketNoticeManagementParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            MarketNoticeManagementParams l_MarketNoticeManagementParams2 = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams2.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams2.setNoticeType("01");
            l_MarketNoticeManagementParams2.setNoticeNumber(2002);
            l_MarketNoticeManagementParams2.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams2.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams2.setBizDateCount(0);
            l_MarketNoticeManagementParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams1);
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams2);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "05", "00", "1"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知再送要求（受付系）レコードを登録
     * 仮想サーバ情報テーブルRow.フロント発注取引所区分コード != "東証" or "名証"の場合、
     * 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
     */
    public void testExecuteVirtualServChange_C0007()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("2");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            VirtualServerChangeParams l_virtualServerChangeParams = TestDBUtility.getVirtualServerChangeRow();
            l_virtualServerChangeParams.setVirtualServerNumberMarket("1002");
            l_virtualServerChangeParams.setChangeReqResDiv("07");
            l_virtualServerChangeParams.setNoticeType("01");
            l_virtualServerChangeParams.setFrontOrderExchangeCode("2");
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            assertEquals(WEB3SystemLayerException.class,
                l_exITE.getTargetException().getClass());
            WEB3SystemLayerException l_targetException =
                (WEB3SystemLayerException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_exIAE)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知再送要求（受付系）レコードを登録
     * 仮想サーバ情報テーブルRow.フロント発注取引所区分コード != "東証" or "名証"の場合、
     * 障害仮想サーバ切替管理テーブルにレコードが不存在する場合
     */
    public void testExecuteVirtualServChange_C0008()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("2");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams.setNoticeType("01");
            l_MarketNoticeManagementParams.setNoticeNumber(2002);
            l_MarketNoticeManagementParams.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams.setBizDateCount(0);
            l_MarketNoticeManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));

            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "07", "01", "2"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知再送要求（約定系）レコードを登録
     * 仮想サーバ情報テーブルRow.フロント発注取引所区分コード != "東証" or "名証"の場合、
     * 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
     */
    public void testExecuteVirtualServChange_C0009()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("2");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            VirtualServerChangeParams l_virtualServerChangeParams = TestDBUtility.getVirtualServerChangeRow();
            l_virtualServerChangeParams.setVirtualServerNumberMarket("1002");
            l_virtualServerChangeParams.setChangeReqResDiv("07");
            l_virtualServerChangeParams.setNoticeType("02");
            l_virtualServerChangeParams.setFrontOrderExchangeCode("2");
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            assertEquals(WEB3SystemLayerException.class,
                l_exITE.getTargetException().getClass());
            WEB3SystemLayerException l_targetException =
                (WEB3SystemLayerException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_exIAE)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知再送要求（約定系）レコードを登録
     * 仮想サーバ情報テーブルRow.フロント発注取引所区分コード != "東証" or "名証"の場合、
     * 障害仮想サーバ切替管理テーブルにレコードが不存在する場合
     */
    public void testExecuteVirtualServChange_C0010()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("2");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams.setNoticeType("01");
            l_MarketNoticeManagementParams.setNoticeNumber(2002);
            l_MarketNoticeManagementParams.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams.setBizDateCount(0);
            l_MarketNoticeManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));

            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "07", "02", "2"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    /*
     * 通知代行要求レコードを登録
     * 障害仮想サーバ切替管理テーブルにレコードが不存在する場合
     * 仮想サーバ情報テーブルRow.フロント発注取引所区分コード = "名証"の場合、
     */
    public void testExecuteVirtualServChange_C0011()
    {
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_commonService = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
            WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();
            
            WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_commonService, l_request);
            
            VirtualServerInformationParams l_virtualInfoParams = new VirtualServerInformationParams();
            l_virtualInfoParams.setVirtualServerNumberJsoes("1001");
            l_virtualInfoParams.setVirtualServerNumberMarket("1002");
            l_virtualInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_virtualInfoParams.setFrontOrderExchangeCode("3");

            Field l_field = WEB3FrontOrderSwitchManagement.class.getDeclaredField("changeProcessDiv");
            l_field.setAccessible(true);
            l_field.set(l_management, "0");

            TestDBUtility.deleteAll(MarketNoticeManagementRow.TYPE);
            MarketNoticeManagementParams l_MarketNoticeManagementParams1 = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams1.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams1.setNoticeType("01");
            l_MarketNoticeManagementParams1.setNoticeNumber(2002);
            l_MarketNoticeManagementParams1.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams1.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams1.setBizDateCount(0);
            l_MarketNoticeManagementParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            MarketNoticeManagementParams l_MarketNoticeManagementParams2 = new MarketNoticeManagementParams();
            l_MarketNoticeManagementParams2.setVirtualServerNumberMarket("2001");
            l_MarketNoticeManagementParams2.setNoticeType("01");
            l_MarketNoticeManagementParams2.setNoticeNumber(2002);
            l_MarketNoticeManagementParams2.setFrontOrderExchangeCode("1");
            l_MarketNoticeManagementParams2.setFrontOrderSystemCode("1");
            l_MarketNoticeManagementParams2.setBizDateCount(0);
            l_MarketNoticeManagementParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_MarketNoticeManagementParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams1);
            TestDBUtility.insertWithDel(l_MarketNoticeManagementParams2);
            
            TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            
            Method l_method = WEB3FrontOrderSwitchManagement.class.getDeclaredMethod(
                    "executeVirtualServChange",
                    new Class[]{VirtualServerInformationRow.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_virtualInfoParams};
            l_method.invoke(l_management, l_obj);

            List l_lisvirtualServerChangeRows = null;
            
            String l_strWhere =
                " virtual_server_number_market = ? " +
                "and change_req_res_div = ? " +
                "and notice_type = ? " +
                "and front_order_exchange_code = ? ";
            Object[] l_values = {"1002", "05", "00", "3"};
            l_lisvirtualServerChangeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        VirtualServerChangeRow.TYPE,
                        l_strWhere,
                        l_values);
            
            assertEquals(1, l_lisvirtualServerChangeRows.size());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    
    public class WEB3FrontOrderSwitchManagementForTest extends WEB3FrontOrderSwitchManagement
    {

        public WEB3FrontOrderSwitchManagementForTest(WEB3AdminDirSecFrontOrderCommonService l_commonService, WEB3AdminFrontRouteChangeCompleteRequest l_request)
        {
            super(l_commonService, l_request);
        }
        
        protected List getVirtualServerInfo() throws WEB3SystemLayerException
        {
            List l_virtualServerLists = new ArrayList(); 
            return  l_virtualServerLists;
        }
        
        protected void updateOrderRouteInvalidity()throws WEB3SystemLayerException 
        {
            
        }
    }
    
    public class WEB3FrontOrderSwitchManagementForTest1 extends WEB3FrontOrderSwitchManagement
    {

        public WEB3FrontOrderSwitchManagementForTest1(WEB3AdminDirSecFrontOrderCommonService l_commonService, WEB3AdminFrontRouteChangeCompleteRequest l_request)
        {
            super(l_commonService, l_request);
        }
        
        protected List getVirtualServerInfo() throws WEB3SystemLayerException
        {
            try 
            {
                TestDBUtility.deleteAll(VirtualServerChangeRow.TYPE);
            } 
            catch (WEB3BaseException e) 
            {
                e.printStackTrace();
                fail();
            }
            
            List l_virtualServerLists = new ArrayList(); 
            VirtualServerInformationParams l_virtual = new VirtualServerInformationParams();
            //仮想サーバNo.（JSOES）
            l_virtual.setVirtualServerNumberJsoes("124");
            //証券会社コード
            l_virtual.setInstitutionCode("554");
            //フロント発注取引所区分コード
            l_virtual.setFrontOrderExchangeCode("9");
            //フロント発注システム区分
            l_virtual.setFrontOrderSystemCode("2");
            //フロント発注取引区分コード
            l_virtual.setFrontOrderTradeCode("3");
            //仮想サーバNo.（市場）
            l_virtual.setVirtualServerNumberMarket("45");
            //銘柄タイプ
            l_virtual.setProductType(ProductTypeEnum.BOND);
            //作成日時
            l_virtual.setCreatedTimestamp(Calendar.getInstance().getTime());
            //更新日時
            l_virtual.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            l_virtualServerLists.add(l_virtual);
            
            return  l_virtualServerLists;
        }
        
        protected void updateOrderRouteInvalidity()throws WEB3SystemLayerException 
        {
            
        }
        
        protected boolean validateHostEqtypeRepeat(String l_corpCode) throws WEB3SystemLayerException 
        {
            return true;
        }
        
    }
    
    public class WEB3MQGatewayServiceImplForTest1 extends WEB3MQGatewayServiceImpl
    {
        public WEB3MQSendResult send(WEB3MQMessageSpec spec)
        {
            WEB3MQSendResult l_web3MQSendResult = new WEB3DefaultMQSendResultForTest1();
            return l_web3MQSendResult;
        }
    }
    
    public class WEB3DefaultMQSendResultForTest1 implements WEB3MQSendResult
    {
        public boolean isSuccessResult()
        {
            return false;
        }

        public ErrorInfo getErrorInfo() {
            return null;
        }

        public boolean isFailedResult() {
            return false;
        }
    }
    
    
    public class WEB3AdminDirSecFrontOrderCommonServiceImplForTest extends WEB3AdminDirSecFrontOrderCommonServiceImpl
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
    }
    
    public VirtualServerInformationParams getVirtualServerInformationRow()
    {
        VirtualServerInformationParams l_virtualServerInformationParams= new VirtualServerInformationParams();
        //仮想サーバNo.（JSOES）
        l_virtualServerInformationParams.setVirtualServerNumberJsoes("123456");
        //証券会社コード
        l_virtualServerInformationParams.setInstitutionCode("555");
        //フロント発注取引所区分コード
        l_virtualServerInformationParams.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_virtualServerInformationParams.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_virtualServerInformationParams.setFrontOrderTradeCode("1");
        //仮想サーバNo.（市場）
        l_virtualServerInformationParams.setVirtualServerNumberMarket("456");
        //銘柄タイプ
        l_virtualServerInformationParams.setProductType(ProductTypeEnum.EQUITY);
        //作成日時
        l_virtualServerInformationParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日時
        l_virtualServerInformationParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_virtualServerInformationParams;
    }
    
    public HostFotypeOrderAllParams getHostFotypeOrderAllRow()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        //証券会社コード
        l_hostFotypeOrderAllParams.setInstitutionCode("555");
        //フロント発注取引所区分コード
        l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
        //取消区分
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        //社内処理項目
        l_hostFotypeOrderAllParams.setCorpCode("123244444");
        //全訂正処理区分
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("1");
        //発注経路区分
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
        //処理区分
        l_hostFotypeOrderAllParams.setStatus("1");
        
        return l_hostFotypeOrderAllParams;
    }
      
    public HostEqtypeOrderAllParams getHostEqtypeOrderAllRow()
    {
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
        //証券会社コード
        l_hostEqtypeOrderAllParams.setInstitutionCode("555");
        //フロント発注取引所区分コード
        l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
        //取消区分
        l_hostEqtypeOrderAllParams.setCancelDiv("0");
        //社内処理項目
        l_hostEqtypeOrderAllParams.setCorpCode("123244444");
        //全訂正処理区分
        l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("1");
        //処理区分
        l_hostEqtypeOrderAllParams.setStatus("1");
        l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("2");
        
        return l_hostEqtypeOrderAllParams;
    }

//  市場受付不明注文.フロント発注取引所区分コード == "大証"
//  市場受付不明注文.データコード == "AI802"
//  取消区分 == "0"
//  　@・市場受付不明注文.社内処理項目
  public void test_createMarketAcceptModifyOrder_0001()
  {
      String STR_METHOD_NAME = "test_createMarketAcceptModifyOrder_0001()";
      log.entering(STR_METHOD_NAME);

      HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
      try 
      {
          TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);

          l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
          l_hostEqtypeOrderAllParams.setRequestCode("AI802");
          //口座ＩＤ
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //部店コード
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //識別コード
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //銘柄コード
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //受注日時
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //発注経路区分
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //取消区分
          l_hostEqtypeOrderAllParams.setCancelDiv("0");
          //社内処理項目
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //全訂正処理区分
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //仮想サーバNo.（JSOES）
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //証券会社コード
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //フロント発注取引所区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("2");
          //フロント発注システム区分
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //フロント発注取引区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //処理区分
          l_hostEqtypeOrderAllParams.setStatus("2");

          //市場コード（SONAR）
          l_hostEqtypeOrderAllParams.setSonarMarketCode("1");
          //（被訂正）社内処理項目
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          /*//売付数量
          l_hostEqtypeOrderAllParams.setSellOrderQuantity(111D);
          //訂正数量
          l_hostEqtypeOrderAllParams.setChangeQuantity(321D);
          //買付数量
          l_hostEqtypeOrderAllParams.setBuyOrderQuantity(313D);
          //指値
          l_hostEqtypeOrderAllParams.setLimitPrice(412D);
          //訂正指値
          l_hostEqtypeOrderAllParams.setChangeLimitPrice(567D);*/

          //TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
      } 
      catch (WEB3BaseException e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }

      WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
      WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();

      l_request.institutionCode = "555";
      l_request.marketCode = "2";
      l_request.productType = "6";
      l_request.changeProcessDiv = "4";
      l_request.submitOrderRouteDiv = "5";
      l_request.convertMarketCode = "3";
      l_request.changeStartDiv = "7";

      WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);

      List l_ifOrderList = (List) new ArrayList();       
      try
      {
          HostEqtypeOrderAllRow l_hostEqtypeOrderAllRows = (HostEqtypeOrderAllRow)l_hostEqtypeOrderAllParams;
          l_management.createMarketAcceptModifyOrder(l_hostEqtypeOrderAllRows);
          // 抽出条件文字列の生成
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");

          // 抽出条件コンテナの生成
          Object[] l_objWhere = {"AI802"};
          QueryProcessor qp = Processors.getDefaultProcessor();
          
          l_ifOrderList = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                  l_sbWhere.toString(),
                                  null,
                                  null,
                                  l_objWhere);
      }
      catch (Exception e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      } 

      HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_ifOrderList.get(0);

      //口座ＩＤ
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //証券会社コード
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //部店コード
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //識別コード
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //銘柄コード
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //受注日時
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //発注経路区分
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //取消区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //フロント発注取引所区分コード
      assertEquals("2",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //フロント発注システム区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //フロント発注取引区分コード
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //社内処理項目
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //（被訂正）社内処理項目
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //全訂正処理区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //処理区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }

  //  市場受付不明注文.フロント発注取引所区分コード == "東証"
  //  訂正（市場受付不明注文.データコード == "AI802"
  //  取消区分 == "1"）の場合
  //  　@・市場受付不明注文.社内処理項目
  public void test_createMarketAcceptModifyOrder_0002()
  {
      String STR_METHOD_NAME = "test_createMarketAcceptModifyOrder_0002()";
      log.entering(STR_METHOD_NAME);

      HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
      try 
      {
          TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);

          l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
          l_hostEqtypeOrderAllParams.setRequestCode("AI802");
          //口座ＩＤ
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //部店コード
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //識別コード
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //銘柄コード
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //受注日時
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //発注経路区分
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //取消区分
          l_hostEqtypeOrderAllParams.setCancelDiv("1");
          //社内処理項目
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //全訂正処理区分
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //仮想サーバNo.（JSOES）
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //証券会社コード
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //フロント発注取引所区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
          //フロント発注システム区分
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //フロント発注取引区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //処理区分
          l_hostEqtypeOrderAllParams.setStatus("2");

          //市場コード（SONAR）
          l_hostEqtypeOrderAllParams.setSonarMarketCode("1");
          //（被訂正）社内処理項目
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          /*//売付数量
          l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
          //訂正数量
          l_hostEqtypeOrderAllParams.setChangeQuantity(321D);
          //買付数量
          l_hostEqtypeOrderAllParams.setBuyOrderQuantity(313D);
          //指値
          l_hostEqtypeOrderAllParams.setLimitPrice(412D);
          //訂正指値
          l_hostEqtypeOrderAllParams.setChangeLimitPrice(567D);
          //値段条件（SONAR）
          l_hostEqtypeOrderAllParams.setPriceConditionType("1");*/

          //TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
      } 
      catch (WEB3BaseException e) 
      {
          //e.printStackTrace();
          log.exiting(STR_METHOD_NAME);
          fail();
      }

      WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest(); 
      WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();

      l_request.institutionCode = "555";
      l_request.marketCode = "2";
      l_request.productType = "6";
      l_request.changeProcessDiv = "4";
      l_request.submitOrderRouteDiv = "5";
      l_request.convertMarketCode = "3";
      l_request.changeStartDiv = "7";

      WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);

      List l_ifOrderList = (List) new ArrayList();       
      try
      {
          HostEqtypeOrderAllRow l_hostEqtypeOrderAllRows = (HostEqtypeOrderAllRow)l_hostEqtypeOrderAllParams;
          l_management.createMarketAcceptModifyOrder(l_hostEqtypeOrderAllRows);
          
          // 抽出条件文字列の生成
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");
                   
          // 抽出条件コンテナの生成
          Object[] l_objWhere = {"AI802"};
          QueryProcessor qp = Processors.getDefaultProcessor();
          
          l_ifOrderList = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                  l_sbWhere.toString(),
                                  null,
                                  null,
                                  l_objWhere);
      } 
      catch (Exception e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      } 

      HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_ifOrderList.get(0);

      //口座ＩＤ
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //証券会社コード
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //部店コード
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //識別コード
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //銘柄コード
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //受注日時
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //発注経路区分
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //取消区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //フロント発注取引所区分コード
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //フロント発注システム区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //フロント発注取引区分コード
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //社内処理項目
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //（被訂正）社内処理項目
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //全訂正処理区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //処理区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }

  //  市場受付不明注文.フロント発注取引所区分コード == "名証"
  //  市場受付不明注文.データコード == "AA"
  //  取消区分 == "0"
  //  　@・市場受付不明注文.社内処理項目
  public void test_createMarketAcceptModifyOrder_0003()
  {
      String STR_METHOD_NAME = "test_createMarketAcceptModifyOrder_0003()";
      log.entering(STR_METHOD_NAME);

      HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
      try 
      {
          TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);

          l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
          l_hostEqtypeOrderAllParams.setRequestCode("AA");
          //口座ＩＤ
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //部店コード
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //識別コード
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //銘柄コード
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //受注日時
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //発注経路区分
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //取消区分
          l_hostEqtypeOrderAllParams.setCancelDiv("1");
          //社内処理項目
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //全訂正処理区分
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //仮想サーバNo.（JSOES）
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //証券会社コード
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //フロント発注取引所区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("3");
          //フロント発注システム区分
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //フロント発注取引区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //処理区分
          l_hostEqtypeOrderAllParams.setStatus("2");

          //市場コード（SONAR）
          l_hostEqtypeOrderAllParams.setSonarMarketCode("2");
          //（被訂正）社内処理項目
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          /*//売付数量
          l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
          //訂正数量
          //l_hostEqtypeOrderAllParams.setChangeQuantity(321D);
          //買付数量
          l_hostEqtypeOrderAllParams.setBuyOrderQuantity(313D);
          //指値
          l_hostEqtypeOrderAllParams.setLimitPrice(412D);
          //訂正指値
          //l_hostEqtypeOrderAllParams.setChangeLimitPrice(567D);
          //値段条件（SONAR）
          //l_hostEqtypeOrderAllParams.setPriceConditionType("1");*/

          //TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
      }
      catch (WEB3BaseException e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }

      WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
      WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();

      l_request.institutionCode = "555";
      l_request.marketCode = "2";
      l_request.productType = "6";
      l_request.changeProcessDiv = "4";
      l_request.submitOrderRouteDiv = "5";
      l_request.convertMarketCode = "3";
      l_request.changeStartDiv = "7";

      WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);

      List l_ifOrderList = (List) new ArrayList();       
      try
      {
          HostEqtypeOrderAllRow l_hostEqtypeOrderAllRows = (HostEqtypeOrderAllRow)l_hostEqtypeOrderAllParams;
          l_management.createMarketAcceptModifyOrder(l_hostEqtypeOrderAllRows);
          // 抽出条件文字列の生成
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");

          // 抽出条件コンテナの生成
          Object[] l_objWhere = {"AI802"};
          QueryProcessor qp = Processors.getDefaultProcessor();
          
          l_ifOrderList = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                  l_sbWhere.toString(),
                                  null,
                                  null,
                                  l_objWhere);
      } 
      catch (Exception e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }

      HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_ifOrderList.get(0);

      //口座ＩＤ
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //証券会社コード
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //部店コード
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //識別コード
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //銘柄コード
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //受注日時
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //発注経路区分
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //取消区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //フロント発注取引所区分コード
      assertEquals("3",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //フロント発注システム区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //フロント発注取引区分コード
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //社内処理項目
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //（被訂正）社内処理項目
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //全訂正処理区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //処理区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }

  //  市場受付不明注文.フロント発注取引所区分コード == "名証"
  //  市場受付不明注文.データコード == "AI802"
  //  取消区分 == "0"
  //  　@・市場受付不明注文.（被訂正）社内処理項目
  public void test_createMarketAcceptModifyOrder_0004()
  {
      String STR_METHOD_NAME = "test_createMarketAcceptModifyOrder_0004()";
      log.entering(STR_METHOD_NAME);

      HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
      try 
      {
          TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);

          l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
          l_hostEqtypeOrderAllParams.setRequestCode("AI802");
          //口座ＩＤ
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //部店コード
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //識別コード
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //銘柄コード
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //受注日時
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //発注経路区分
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //取消区分
          l_hostEqtypeOrderAllParams.setCancelDiv("0");
          //社内処理項目
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //全訂正処理区分
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //仮想サーバNo.（JSOES）
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //証券会社コード
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //フロント発注取引所区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("3");
          //フロント発注システム区分
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //フロント発注取引区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //処理区分
          l_hostEqtypeOrderAllParams.setStatus("2");

          //市場コード（SONAR）
          l_hostEqtypeOrderAllParams.setSonarMarketCode("2");
          //（被訂正）社内処理項目
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          /*//売付数量
          l_hostEqtypeOrderAllParams.setSellOrderQuantity(111D);
          //訂正数量
          //l_hostEqtypeOrderAllParams.setChangeQuantity(321D);
          //買付数量
          l_hostEqtypeOrderAllParams.setBuyOrderQuantity(313D);
          //指値
          l_hostEqtypeOrderAllParams.setLimitPrice(412D);
          //訂正指値
          l_hostEqtypeOrderAllParams.setChangeLimitPrice(567D);*/

          //TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
      }
      catch (WEB3BaseException e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }

      WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
      WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();

      l_request.institutionCode = "555";
      l_request.marketCode = "2";
      l_request.productType = "6";
      l_request.changeProcessDiv = "4";
      l_request.submitOrderRouteDiv = "5";
      l_request.convertMarketCode = "3";
      l_request.changeStartDiv = "7";

      WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);

      List l_ifOrderList = (List) new ArrayList();       
      try
      {
          HostEqtypeOrderAllRow l_hostEqtypeOrderAllRows = (HostEqtypeOrderAllRow)l_hostEqtypeOrderAllParams;
          l_management.createMarketAcceptModifyOrder(l_hostEqtypeOrderAllRows);
          // 抽出条件文字列の生成
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");
                  
          // 抽出条件コンテナの生成
          Object[] l_objWhere = {"AI802"};
          QueryProcessor qp = Processors.getDefaultProcessor();
          
          l_ifOrderList = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                  l_sbWhere.toString(),
                                  null,
                                  null,
                                  l_objWhere);
      }
      catch (Exception e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      } 

      HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_ifOrderList.get(0);

      //口座ＩＤ
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //証券会社コード
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //部店コード
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //識別コード
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //銘柄コード
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //受注日時
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //発注経路区分
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //取消区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //フロント発注取引所区分コード
      assertEquals("3",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //フロント発注システム区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //フロント発注取引区分コード
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //社内処理項目
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //（被訂正）社内処理項目
      assertEquals("11111",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //全訂正処理区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //処理区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }

  //  市場受付不明注文.フロント発注取引所区分コード == "東京"
  //  市場受付不明注文.データコード == "AI802"
  //  取消区分 == "0"
  //  　@・市場受付不明注文.（被訂正）社内処理項目
  public void test_createMarketAcceptModifyOrder_0005()
  {
      String STR_METHOD_NAME = "test_createMarketAcceptModifyOrder_0005()";
      log.entering(STR_METHOD_NAME);

      HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
      try 
      {
          TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);

          l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
          l_hostEqtypeOrderAllParams.setRequestCode("AI802");
          //口座ＩＤ
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //部店コード
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //識別コード
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //銘柄コード
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //受注日時
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //発注経路区分
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //取消区分
          l_hostEqtypeOrderAllParams.setCancelDiv("0");
          //社内処理項目
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //全訂正処理区分
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //仮想サーバNo.（JSOES）
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //証券会社コード
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //フロント発注取引所区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
          //フロント発注システム区分
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //フロント発注取引区分コード
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //処理区分
          l_hostEqtypeOrderAllParams.setStatus("2");

          //市場コード（SONAR）
          l_hostEqtypeOrderAllParams.setSonarMarketCode("2");
          //（被訂正）社内処理項目
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          //TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
      }
      catch (WEB3BaseException e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }

      WEB3AdminDirSecFrontOrderCommonService l_service = new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();
      WEB3AdminFrontRouteChangeCompleteRequest l_request = new WEB3AdminFrontRouteChangeCompleteRequest();

      l_request.institutionCode = "555";
      l_request.marketCode = "2";
      l_request.productType = "6";
      l_request.changeProcessDiv = "4";
      l_request.submitOrderRouteDiv = "5";
      l_request.convertMarketCode = "3";
      l_request.changeStartDiv = "7";

      WEB3FrontOrderSwitchManagement l_management = new WEB3FrontOrderSwitchManagement(l_service,l_request);

      List l_ifOrderList = (List) new ArrayList();       
      try
      {
          HostEqtypeOrderAllRow l_hostEqtypeOrderAllRows = (HostEqtypeOrderAllRow)l_hostEqtypeOrderAllParams;
          l_management.createMarketAcceptModifyOrder(l_hostEqtypeOrderAllRows);
          // 抽出条件文字列の生成
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");

          // 抽出条件コンテナの生成
          Object[] l_objWhere = {"AI802"};
          QueryProcessor qp = Processors.getDefaultProcessor();

          l_ifOrderList = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                  l_sbWhere.toString(),
                                  null,
                                  null,
                                  l_objWhere);
      }
      catch (Exception e) 
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      } 

      HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_ifOrderList.get(0);

      //口座ＩＤ
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //証券会社コード
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //部店コード
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //識別コード
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //銘柄コード
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //受注日時
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //発注経路区分
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //取消区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //フロント発注取引所区分コード
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //フロント発注システム区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //フロント発注取引区分コード
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //社内処理項目
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //（被訂正）社内処理項目
      assertEquals("11111",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //全訂正処理区分
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //処理区分
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }
}

@
