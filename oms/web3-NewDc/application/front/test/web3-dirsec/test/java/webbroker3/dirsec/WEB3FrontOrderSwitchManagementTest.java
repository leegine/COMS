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
 * (�����o�H�ؑ֏����N���X)<BR>
 *
 * @@author �Ј���(���u)
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
            String l_strFinalNoticeNo = (String)l_method.invoke(l_management, new Object[]{"", "", "1"});//����  
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
            String l_strFinalNoticeNo = (String)l_method.invoke(l_management, new Object[]{"", "", "3"});//����  
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
            String l_strFinalNoticeNo = (String)l_method.invoke(l_management, new Object[]{"", "", "2"});//���  
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
            //�����^�C�v
            l_virtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
            //���z�T�[�oNo.�iJSOES�j
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
        //�����^�C�v
        l_virtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
        //�ʒm�t�@@�C��No.
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
        
        //�ؑ֎w�������敪
        String l_priReqResDiv = "YY";
        //�ʒm���
        String l_priNoticeDiv = "ZZ";
        //�ŏI�ʒmNo
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
     * ��s����t�n�ʒm�ʔ� !=null
     * ��s�����n�ʒm�ʔ� !=null
     */
    public void test_setVirtualServerChangeRecord_0002()
    {
        VirtualServerInformationParams l_virtualServerInformationParams = this.getVirtualServerInformationRow();
        //�����^�C�v
        l_virtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
        //�ʒm�t�@@�C��No.
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
        
        //�ؑ֎w�������敪
        String l_priReqResDiv = "YY";
        //�ʒm���
        String l_priNoticeDiv = "ZZ";
        //�ŏI�ʒmNo
        String l_priFinalNoticeNo ="12";
        //��s����t�n�ʒm�ʔ�
        String l_strAcceptNoticeNo = "1";
        //��s�����n�ʒm�ʔ�
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
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
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
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
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
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
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
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
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
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
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
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����o�H�敪
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
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("333");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //�����敪
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
            //����敪
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
            //�S���������敪
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
            //�����敪
            l_hostFotypeOrderAllParams1.setStatus("4");
            //����敪
            l_hostFotypeOrderAllParams1.setCancelDiv("5");
            //�Г���������
            l_hostFotypeOrderAllParams1.setCorpCode("123242");
            //�S���������敪
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("2");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����敪
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
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_hostOrders = new ArrayList();
        
                // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_jsoes = ? ");
        
                // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                "654321"
            };
            
        try
        {
            // DB����
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
            //�����敪
            l_hostFotypeOrderAllParams1.setStatus("4");
            //����敪
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams1.setCorpCode("123242");
            //�S���������敪
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("2");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����敪
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
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_hostOrders = new ArrayList();
        
                // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_jsoes = ? ");
        
                // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                "654321"
            };
            
        try
        {
            // DB����
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
            //�����敪
            l_hostFotypeOrderAllParams1.setStatus("4");
            //����敪
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams1.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("2");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����敪
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
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_hostOrders = new ArrayList();
        
                // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_jsoes = ? ");
        
                // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                "654321"
            };
            
        try
        {
            // DB����
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
            //�����敪
            l_hostFotypeOrderAllParams1.setStatus("4");
            //����敪
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams1.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("8");
            
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            
            l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����敪
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
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_hostOrders = new ArrayList();
        
                // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_jsoes = ? ");
        
                // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                "654321"
            };
            
        try
        {
            // DB����
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
            //�����h�c
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("1");
            //�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //�����敪
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
            //�����h�c
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("1");
            //�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //�����敪
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
            // ���o����������̐���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            
            // ����ID�����w��
            String l_strSort = "submit_order_route_div asc";
                    
            // ���o�����R���e�i�̐���
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
            //�����h�c
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //���X�R�[�h
            l_hostFotypeOrderAllParams.setBranchCode("88");
            //���ʃR�[�h
            l_hostFotypeOrderAllParams.setOrderRequestNumber("898989");
            //�����R�[�h
            l_hostFotypeOrderAllParams.setProductCode("987987");
            //�󒍓���
            l_hostFotypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
            //�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("3");
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����敪
            l_hostFotypeOrderAllParams.setStatus("2");
            
            //���t����
            l_hostFotypeOrderAllParams.setSellOrderQuantity(123D);
            //��������
            l_hostFotypeOrderAllParams.setChangeQuantity(321D);
            //���t����
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(313D);
            //�w�l
            l_hostFotypeOrderAllParams.setLimitPrice(412D);
            //�����w�l
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
            // ���o����������̐���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            
            // ����ID�����w��
            String l_strSort = "submit_order_route_div asc";
                    
            // ���o�����R���e�i�̐���
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
        
        //�����h�c
        assertEquals("45","" + l_hostFotypeOrderAllRow.getAccountId());
        //�،���ЃR�[�h
        assertEquals("555",l_hostFotypeOrderAllRow.getInstitutionCode());
        //���X�R�[�h
        assertEquals("88",l_hostFotypeOrderAllRow.getBranchCode());
        //���ʃR�[�h
        assertEquals("898989",l_hostFotypeOrderAllRow.getOrderRequestNumber());
        //�����R�[�h
        assertEquals("987987",l_hostFotypeOrderAllRow.getProductCode());
        //�󒍓���
        assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
            l_hostFotypeOrderAllRow.getReceivedDateTime()));
        //�����o�H�敪
        assertEquals("3",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        //����敪
        assertEquals("0",l_hostFotypeOrderAllRow.getCancelDiv());
        //�t�����g����������敪�R�[�h
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());   
        //�t�����g�����V�X�e���敪
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());   
        //�t�����g��������敪�R�[�h
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());   
        //�Г���������
        assertEquals("123244444",l_hostFotypeOrderAllRow.getCorpCode());   
        //�i������j�Г���������
        assertEquals("123244444",l_hostFotypeOrderAllRow.getOrgCorpCode());   
        //�S���������敪
        assertEquals("1",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());   
        //�����敪
        assertEquals("0",l_hostFotypeOrderAllRow.getStatus()); 
        
        //���t����
        assertEquals("" + new Double(321D),"" + l_hostFotypeOrderAllRow.getSellOrderQuantity()); 
        //��������  
        assertEquals("" + new Double(321D),"" + l_hostFotypeOrderAllRow.getChangeQuantity()); 
        //���t����
        assertEquals("" + new Double(321D),"" + l_hostFotypeOrderAllRow.getBuyOrderQuantity()); 
        //�w�l
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getLimitPrice()); 
        //�����w�l
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
            //�����h�c
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //���X�R�[�h
            l_hostFotypeOrderAllParams.setBranchCode("88");
            //���ʃR�[�h
            l_hostFotypeOrderAllParams.setOrderRequestNumber("898989");
            //�����R�[�h
            l_hostFotypeOrderAllParams.setProductCode("987987");
            //�󒍓���
            l_hostFotypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
            //�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("3");
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����敪
            l_hostFotypeOrderAllParams.setStatus("2");
            
            //���t����
            l_hostFotypeOrderAllParams.setSellOrderQuantity(0D);
            //��������
            l_hostFotypeOrderAllParams.setChangeQuantity(321D);
            //���t����
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(0D);
            //�w�l
            l_hostFotypeOrderAllParams.setLimitPrice(412D);
            //�����w�l
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
            // ���o����������̐���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            
            // ����ID�����w��
            String l_strSort = "submit_order_route_div asc";
                    
            // ���o�����R���e�i�̐���
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
        
        //�����h�c
        assertEquals("45","" + l_hostFotypeOrderAllRow.getAccountId());
        //�،���ЃR�[�h
        assertEquals("555",l_hostFotypeOrderAllRow.getInstitutionCode());
        //���X�R�[�h
        assertEquals("88",l_hostFotypeOrderAllRow.getBranchCode());
        //���ʃR�[�h
        assertEquals("898989",l_hostFotypeOrderAllRow.getOrderRequestNumber());
        //�����R�[�h
        assertEquals("987987",l_hostFotypeOrderAllRow.getProductCode());
        //�󒍓���
        assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
            l_hostFotypeOrderAllRow.getReceivedDateTime()));
        //�����o�H�敪
        assertEquals("3",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        //����敪
        assertEquals("0",l_hostFotypeOrderAllRow.getCancelDiv());
        //�t�����g����������敪�R�[�h
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());   
        //�t�����g�����V�X�e���敪
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());   
        //�t�����g��������敪�R�[�h
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());   
        //�Г���������
        assertEquals("123244444",l_hostFotypeOrderAllRow.getCorpCode());   
        //�i������j�Г���������
        assertEquals("123244444",l_hostFotypeOrderAllRow.getOrgCorpCode());   
        //�S���������敪
        assertEquals("1",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());   
        //�����敪
        assertEquals("0",l_hostFotypeOrderAllRow.getStatus()); 
        
        //���t����
        assertEquals("" + new Double(0D),"" + l_hostFotypeOrderAllRow.getSellOrderQuantity()); 
        //��������  
        assertEquals("" + new Double(321D),"" + l_hostFotypeOrderAllRow.getChangeQuantity()); 
        //���t����
        assertEquals("" + new Double(0D),"" + l_hostFotypeOrderAllRow.getBuyOrderQuantity()); 
        //�w�l
        assertEquals("" + new Double(412D),"" + l_hostFotypeOrderAllRow.getLimitPrice()); 
        //�����w�l
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
            //�����h�c
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //���X�R�[�h
            l_hostFotypeOrderAllParams.setBranchCode("88");
            //���ʃR�[�h
            l_hostFotypeOrderAllParams.setOrderRequestNumber("898989");
            //�����R�[�h
            l_hostFotypeOrderAllParams.setProductCode("987987");
            //�󒍓���
            l_hostFotypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
            //�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("3");
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����敪
            l_hostFotypeOrderAllParams.setStatus("2");
            
            //���t����
            l_hostFotypeOrderAllParams.setSellOrderQuantity(123D);
            //��������
//            l_hostFotypeOrderAllParams.setChangeQuantity(321D);
            //���t����
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(313D);
            //�w�l
            l_hostFotypeOrderAllParams.setLimitPrice(412D);
            //�����w�l
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
            // ���o����������̐���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            
            // ����ID�����w��
            String l_strSort = "submit_order_route_div asc";
                    
            // ���o�����R���e�i�̐���
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
        
        //�����h�c
        assertEquals("45","" + l_hostFotypeOrderAllRow.getAccountId());
        //�،���ЃR�[�h
        assertEquals("555",l_hostFotypeOrderAllRow.getInstitutionCode());
        //���X�R�[�h
        assertEquals("88",l_hostFotypeOrderAllRow.getBranchCode());
        //���ʃR�[�h
        assertEquals("898989",l_hostFotypeOrderAllRow.getOrderRequestNumber());
        //�����R�[�h
        assertEquals("987987",l_hostFotypeOrderAllRow.getProductCode());
        //�󒍓���
        assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
            l_hostFotypeOrderAllRow.getReceivedDateTime()));
        //�����o�H�敪
        assertEquals("3",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        //����敪
        assertEquals("0",l_hostFotypeOrderAllRow.getCancelDiv());
        //�t�����g����������敪�R�[�h
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());   
        //�t�����g�����V�X�e���敪
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());   
        //�t�����g��������敪�R�[�h
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());   
        //�Г���������
        assertEquals("123244444",l_hostFotypeOrderAllRow.getCorpCode());   
        //�i������j�Г���������
        assertEquals("123244444",l_hostFotypeOrderAllRow.getOrgCorpCode());   
        //�S���������敪
        assertEquals("1",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());   
        //�����敪
        assertEquals("0",l_hostFotypeOrderAllRow.getStatus()); 
        
        //���t����
        assertEquals("" + new Double(123D),"" + l_hostFotypeOrderAllRow.getSellOrderQuantity()); 
        //��������  
        assertEquals("" + new Double(123D),"" + l_hostFotypeOrderAllRow.getChangeQuantity()); 
        //���t����
        assertEquals("" + new Double(313D),"" + l_hostFotypeOrderAllRow.getBuyOrderQuantity()); 
        //�w�l
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getLimitPrice()); 
        //�����w�l
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
            //�����h�c
            l_hostFotypeOrderAllParams.setAccountId(45L);
            //���X�R�[�h
            l_hostFotypeOrderAllParams.setBranchCode("88");
            //���ʃR�[�h
            l_hostFotypeOrderAllParams.setOrderRequestNumber("898989");
            //�����R�[�h
            l_hostFotypeOrderAllParams.setProductCode("987987");
            //�󒍓���
            l_hostFotypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
            //�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("3");
            //����敪
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            //�Г���������
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //�S���������敪
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("8");
            //���z�T�[�oNo.�iJSOES�j
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode("555");
            //�t�����g����������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //�t�����g�����V�X�e���敪
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //�t�����g��������敪�R�[�h
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //�����敪
            l_hostFotypeOrderAllParams.setStatus("2");
            
            //���t����
            l_hostFotypeOrderAllParams.setSellOrderQuantity(0D);
            //��������
//            l_hostFotypeOrderAllParams.setChangeQuantity(321D);
            //���t����
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(313D);
            //�w�l
            l_hostFotypeOrderAllParams.setLimitPrice(412D);
            //�����w�l
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
            // ���o����������̐���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            
            // ����ID�����w��
            String l_strSort = "submit_order_route_div asc";
                    
            // ���o�����R���e�i�̐���
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
        
        //�����h�c
        assertEquals("45","" + l_hostFotypeOrderAllRow.getAccountId());
        //�،���ЃR�[�h
        assertEquals("555",l_hostFotypeOrderAllRow.getInstitutionCode());
        //���X�R�[�h
        assertEquals("88",l_hostFotypeOrderAllRow.getBranchCode());
        //���ʃR�[�h
        assertEquals("898989",l_hostFotypeOrderAllRow.getOrderRequestNumber());
        //�����R�[�h
        assertEquals("987987",l_hostFotypeOrderAllRow.getProductCode());
        //�󒍓���
        assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
            l_hostFotypeOrderAllRow.getReceivedDateTime()));
        //�����o�H�敪
        assertEquals("3",l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
        //����敪
        assertEquals("0",l_hostFotypeOrderAllRow.getCancelDiv());
        //�t�����g����������敪�R�[�h
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());   
        //�t�����g�����V�X�e���敪
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());   
        //�t�����g��������敪�R�[�h
        assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());   
        //�Г���������
        assertEquals("123244444",l_hostFotypeOrderAllRow.getCorpCode());   
        //�i������j�Г���������
        assertEquals("123244444",l_hostFotypeOrderAllRow.getOrgCorpCode());   
        //�S���������敪
        assertEquals("1",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());   
        //�����敪
        assertEquals("0",l_hostFotypeOrderAllRow.getStatus()); 
        
        //���t����
        assertEquals("" + new Double(0D),"" + l_hostFotypeOrderAllRow.getSellOrderQuantity()); 
        //��������  
        assertEquals("" + new Double(313D),"" + l_hostFotypeOrderAllRow.getChangeQuantity()); 
        //���t����
        assertEquals("" + new Double(313D),"" + l_hostFotypeOrderAllRow.getBuyOrderQuantity()); 
        //�w�l
        assertEquals("" + new Double(567D),"" + l_hostFotypeOrderAllRow.getLimitPrice()); 
        //�����w�l
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
            
            // ��������������̐���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and market_code = ? ");        
            l_sbWhere.append(" and front_order_system_code = ? ");
            l_sbWhere.append(" and product_type = ? ");
            l_sbWhere.append(" and submit_order_route_div = ? ");
            
            // ���������R���e�i�̐���
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
     * if�@@�ؑ֎w�������敪 = "�ʒm��s�v��" ����
     * ���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h = "����"�̏ꍇ�A
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
     * if�@@�ؑ֎w�������敪 = "�ʒm��s�v��" ����
     * ���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h = "����"�̏ꍇ�A
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
     * �ؑ֎w�������敪���ʒm�đ��v���̏ꍇ�A�ŏI�ʒmNo���擾
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
     * �ؑ֏����������ʔԏƉ���̏ꍇ�A�ʔԏƉ�v�����R�[�h��o�^
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
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
     * �ؑ֏����������ʔԏƉ���̏ꍇ�A�ʔԏƉ�v�����R�[�h��o�^
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h���s���݂���ꍇ
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
     * �ʒm��s�����v�����R�[�h��o�^
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
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
     * �ʒm��s�����v�����R�[�h��o�^
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h���s���݂���ꍇ
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
     * �ʒm��s�v�����R�[�h��o�^
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
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
     * �ʒm��s�v�����R�[�h��o�^
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h���s���݂���ꍇ
     * ���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h = "����"�̏ꍇ�A
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
     * �ʒm�đ��v���i��t�n�j���R�[�h��o�^
     * ���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h != "����" or "����"�̏ꍇ�A
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
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
     * �ʒm�đ��v���i��t�n�j���R�[�h��o�^
     * ���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h != "����" or "����"�̏ꍇ�A
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h���s���݂���ꍇ
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
     * �ʒm�đ��v���i���n�j���R�[�h��o�^
     * ���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h != "����" or "����"�̏ꍇ�A
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
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
     * �ʒm�đ��v���i���n�j���R�[�h��o�^
     * ���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h != "����" or "����"�̏ꍇ�A
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h���s���݂���ꍇ
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
     * �ʒm��s�v�����R�[�h��o�^
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h���s���݂���ꍇ
     * ���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h = "����"�̏ꍇ�A
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
            //���z�T�[�oNo.�iJSOES�j
            l_virtual.setVirtualServerNumberJsoes("124");
            //�،���ЃR�[�h
            l_virtual.setInstitutionCode("554");
            //�t�����g����������敪�R�[�h
            l_virtual.setFrontOrderExchangeCode("9");
            //�t�����g�����V�X�e���敪
            l_virtual.setFrontOrderSystemCode("2");
            //�t�����g��������敪�R�[�h
            l_virtual.setFrontOrderTradeCode("3");
            //���z�T�[�oNo.�i�s��j
            l_virtual.setVirtualServerNumberMarket("45");
            //�����^�C�v
            l_virtual.setProductType(ProductTypeEnum.BOND);
            //�쐬����
            l_virtual.setCreatedTimestamp(Calendar.getInstance().getTime());
            //�X�V����
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
        //���z�T�[�oNo.�iJSOES�j
        l_virtualServerInformationParams.setVirtualServerNumberJsoes("123456");
        //�،���ЃR�[�h
        l_virtualServerInformationParams.setInstitutionCode("555");
        //�t�����g����������敪�R�[�h
        l_virtualServerInformationParams.setFrontOrderExchangeCode("1");
        //�t�����g�����V�X�e���敪
        l_virtualServerInformationParams.setFrontOrderSystemCode("1");
        //�t�����g��������敪�R�[�h
        l_virtualServerInformationParams.setFrontOrderTradeCode("1");
        //���z�T�[�oNo.�i�s��j
        l_virtualServerInformationParams.setVirtualServerNumberMarket("456");
        //�����^�C�v
        l_virtualServerInformationParams.setProductType(ProductTypeEnum.EQUITY);
        //�쐬����
        l_virtualServerInformationParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //�X�V����
        l_virtualServerInformationParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_virtualServerInformationParams;
    }
    
    public HostFotypeOrderAllParams getHostFotypeOrderAllRow()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        //�،���ЃR�[�h
        l_hostFotypeOrderAllParams.setInstitutionCode("555");
        //�t�����g����������敪�R�[�h
        l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
        //�t�����g�����V�X�e���敪
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
        //�t�����g��������敪�R�[�h
        l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
        //����敪
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        //�Г���������
        l_hostFotypeOrderAllParams.setCorpCode("123244444");
        //�S���������敪
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("1");
        //�����o�H�敪
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
        //�����敪
        l_hostFotypeOrderAllParams.setStatus("1");
        
        return l_hostFotypeOrderAllParams;
    }
      
    public HostEqtypeOrderAllParams getHostEqtypeOrderAllRow()
    {
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
        //�،���ЃR�[�h
        l_hostEqtypeOrderAllParams.setInstitutionCode("555");
        //�t�����g����������敪�R�[�h
        l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
        //�t�����g�����V�X�e���敪
        l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
        //�t�����g��������敪�R�[�h
        l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
        //����敪
        l_hostEqtypeOrderAllParams.setCancelDiv("0");
        //�Г���������
        l_hostEqtypeOrderAllParams.setCorpCode("123244444");
        //�S���������敪
        l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("1");
        //�����敪
        l_hostEqtypeOrderAllParams.setStatus("1");
        l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("2");
        
        return l_hostEqtypeOrderAllParams;
    }

//  �s���t�s������.�t�����g����������敪�R�[�h == "���"
//  �s���t�s������.�f�[�^�R�[�h == "AI802"
//  ����敪 == "0"
//  �@@�E�s���t�s������.�Г���������
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
          //�����h�c
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //���X�R�[�h
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //���ʃR�[�h
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //�����R�[�h
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //�󒍓���
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //�����o�H�敪
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //����敪
          l_hostEqtypeOrderAllParams.setCancelDiv("0");
          //�Г���������
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //�S���������敪
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //���z�T�[�oNo.�iJSOES�j
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //�،���ЃR�[�h
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //�t�����g����������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("2");
          //�t�����g�����V�X�e���敪
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //�t�����g��������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //�����敪
          l_hostEqtypeOrderAllParams.setStatus("2");

          //�s��R�[�h�iSONAR�j
          l_hostEqtypeOrderAllParams.setSonarMarketCode("1");
          //�i������j�Г���������
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          /*//���t����
          l_hostEqtypeOrderAllParams.setSellOrderQuantity(111D);
          //��������
          l_hostEqtypeOrderAllParams.setChangeQuantity(321D);
          //���t����
          l_hostEqtypeOrderAllParams.setBuyOrderQuantity(313D);
          //�w�l
          l_hostEqtypeOrderAllParams.setLimitPrice(412D);
          //�����w�l
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
          // ���o����������̐���
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");

          // ���o�����R���e�i�̐���
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

      //�����h�c
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //�،���ЃR�[�h
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //���X�R�[�h
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //���ʃR�[�h
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //�����R�[�h
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //�󒍓���
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //�����o�H�敪
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //�t�����g����������敪�R�[�h
      assertEquals("2",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //�t�����g�����V�X�e���敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //�t�����g��������敪�R�[�h
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //�Г���������
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //�i������j�Г���������
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //�S���������敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //�����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }

  //  �s���t�s������.�t�����g����������敪�R�[�h == "����"
  //  �����i�s���t�s������.�f�[�^�R�[�h == "AI802"
  //  ����敪 == "1"�j�̏ꍇ
  //  �@@�E�s���t�s������.�Г���������
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
          //�����h�c
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //���X�R�[�h
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //���ʃR�[�h
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //�����R�[�h
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //�󒍓���
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //�����o�H�敪
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //����敪
          l_hostEqtypeOrderAllParams.setCancelDiv("1");
          //�Г���������
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //�S���������敪
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //���z�T�[�oNo.�iJSOES�j
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //�،���ЃR�[�h
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //�t�����g����������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
          //�t�����g�����V�X�e���敪
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //�t�����g��������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //�����敪
          l_hostEqtypeOrderAllParams.setStatus("2");

          //�s��R�[�h�iSONAR�j
          l_hostEqtypeOrderAllParams.setSonarMarketCode("1");
          //�i������j�Г���������
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          /*//���t����
          l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
          //��������
          l_hostEqtypeOrderAllParams.setChangeQuantity(321D);
          //���t����
          l_hostEqtypeOrderAllParams.setBuyOrderQuantity(313D);
          //�w�l
          l_hostEqtypeOrderAllParams.setLimitPrice(412D);
          //�����w�l
          l_hostEqtypeOrderAllParams.setChangeLimitPrice(567D);
          //�l�i�����iSONAR�j
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
          
          // ���o����������̐���
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");
                   
          // ���o�����R���e�i�̐���
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

      //�����h�c
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //�،���ЃR�[�h
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //���X�R�[�h
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //���ʃR�[�h
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //�����R�[�h
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //�󒍓���
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //�����o�H�敪
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //�t�����g����������敪�R�[�h
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //�t�����g�����V�X�e���敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //�t�����g��������敪�R�[�h
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //�Г���������
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //�i������j�Г���������
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //�S���������敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //�����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }

  //  �s���t�s������.�t�����g����������敪�R�[�h == "����"
  //  �s���t�s������.�f�[�^�R�[�h == "AA"
  //  ����敪 == "0"
  //  �@@�E�s���t�s������.�Г���������
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
          //�����h�c
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //���X�R�[�h
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //���ʃR�[�h
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //�����R�[�h
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //�󒍓���
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //�����o�H�敪
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //����敪
          l_hostEqtypeOrderAllParams.setCancelDiv("1");
          //�Г���������
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //�S���������敪
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //���z�T�[�oNo.�iJSOES�j
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //�،���ЃR�[�h
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //�t�����g����������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("3");
          //�t�����g�����V�X�e���敪
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //�t�����g��������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //�����敪
          l_hostEqtypeOrderAllParams.setStatus("2");

          //�s��R�[�h�iSONAR�j
          l_hostEqtypeOrderAllParams.setSonarMarketCode("2");
          //�i������j�Г���������
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          /*//���t����
          l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
          //��������
          //l_hostEqtypeOrderAllParams.setChangeQuantity(321D);
          //���t����
          l_hostEqtypeOrderAllParams.setBuyOrderQuantity(313D);
          //�w�l
          l_hostEqtypeOrderAllParams.setLimitPrice(412D);
          //�����w�l
          //l_hostEqtypeOrderAllParams.setChangeLimitPrice(567D);
          //�l�i�����iSONAR�j
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
          // ���o����������̐���
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");

          // ���o�����R���e�i�̐���
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

      //�����h�c
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //�،���ЃR�[�h
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //���X�R�[�h
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //���ʃR�[�h
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //�����R�[�h
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //�󒍓���
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //�����o�H�敪
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //�t�����g����������敪�R�[�h
      assertEquals("3",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //�t�����g�����V�X�e���敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //�t�����g��������敪�R�[�h
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //�Г���������
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //�i������j�Г���������
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //�S���������敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //�����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }

  //  �s���t�s������.�t�����g����������敪�R�[�h == "����"
  //  �s���t�s������.�f�[�^�R�[�h == "AI802"
  //  ����敪 == "0"
  //  �@@�E�s���t�s������.�i������j�Г���������
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
          //�����h�c
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //���X�R�[�h
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //���ʃR�[�h
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //�����R�[�h
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //�󒍓���
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //�����o�H�敪
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //����敪
          l_hostEqtypeOrderAllParams.setCancelDiv("0");
          //�Г���������
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //�S���������敪
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //���z�T�[�oNo.�iJSOES�j
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //�،���ЃR�[�h
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //�t�����g����������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("3");
          //�t�����g�����V�X�e���敪
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //�t�����g��������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //�����敪
          l_hostEqtypeOrderAllParams.setStatus("2");

          //�s��R�[�h�iSONAR�j
          l_hostEqtypeOrderAllParams.setSonarMarketCode("2");
          //�i������j�Г���������
          l_hostEqtypeOrderAllParams.setOrgCorpCode("11111");

          /*//���t����
          l_hostEqtypeOrderAllParams.setSellOrderQuantity(111D);
          //��������
          //l_hostEqtypeOrderAllParams.setChangeQuantity(321D);
          //���t����
          l_hostEqtypeOrderAllParams.setBuyOrderQuantity(313D);
          //�w�l
          l_hostEqtypeOrderAllParams.setLimitPrice(412D);
          //�����w�l
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
          // ���o����������̐���
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");
                  
          // ���o�����R���e�i�̐���
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

      //�����h�c
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //�،���ЃR�[�h
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //���X�R�[�h
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //���ʃR�[�h
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //�����R�[�h
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //�󒍓���
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //�����o�H�敪
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //�t�����g����������敪�R�[�h
      assertEquals("3",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //�t�����g�����V�X�e���敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //�t�����g��������敪�R�[�h
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //�Г���������
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //�i������j�Г���������
      assertEquals("11111",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //�S���������敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //�����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }

  //  �s���t�s������.�t�����g����������敪�R�[�h == "����"
  //  �s���t�s������.�f�[�^�R�[�h == "AI802"
  //  ����敪 == "0"
  //  �@@�E�s���t�s������.�i������j�Г���������
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
          //�����h�c
          l_hostEqtypeOrderAllParams.setAccountId(45L);
          //���X�R�[�h
          l_hostEqtypeOrderAllParams.setBranchCode("88");
          //���ʃR�[�h
          l_hostEqtypeOrderAllParams.setOrderRequestNumber("898989");
          //�����R�[�h
          l_hostEqtypeOrderAllParams.setProductCode("987987");
          //�󒍓���
          l_hostEqtypeOrderAllParams.setReceivedDateTime(Calendar.getInstance().getTime());
          //�����o�H�敪
          l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("3");
          //����敪
          l_hostEqtypeOrderAllParams.setCancelDiv("0");
          //�Г���������
          l_hostEqtypeOrderAllParams.setCorpCode("123244444");
          //�S���������敪
          l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("8");
          //���z�T�[�oNo.�iJSOES�j
          l_hostEqtypeOrderAllParams.setVirtualServerNumberJsoes("654321");
          //�،���ЃR�[�h
          l_hostEqtypeOrderAllParams.setInstitutionCode("555");
          //�t�����g����������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
          //�t�����g�����V�X�e���敪
          l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
          //�t�����g��������敪�R�[�h
          l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
          //�����敪
          l_hostEqtypeOrderAllParams.setStatus("2");

          //�s��R�[�h�iSONAR�j
          l_hostEqtypeOrderAllParams.setSonarMarketCode("2");
          //�i������j�Г���������
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
          // ���o����������̐���
          StringBuffer l_sbWhere = new StringBuffer();
          l_sbWhere.append(" request_code = ? ");

          // ���o�����R���e�i�̐���
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

      //�����h�c
      assertEquals("45","" + l_hostEqtypeOrderAllRow.getAccountId());
      //�،���ЃR�[�h
      assertEquals("555",l_hostEqtypeOrderAllRow.getInstitutionCode());
      //���X�R�[�h
      assertEquals("88",l_hostEqtypeOrderAllRow.getBranchCode());
      //���ʃR�[�h
      assertEquals("898989",l_hostEqtypeOrderAllRow.getOrderRequestNumber());
      //�����R�[�h
      assertEquals("987987",l_hostEqtypeOrderAllRow.getProductCode());
      //�󒍓���
      assertEquals("0","" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
          l_hostEqtypeOrderAllRow.getReceivedDateTime()));
      //�����o�H�敪
      assertEquals("3",l_hostEqtypeOrderAllRow.getSubmitOrderRouteDiv());
      //����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getCancelDiv());
      //�t�����g����������敪�R�[�h
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderExchangeCode());   
      //�t�����g�����V�X�e���敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderSystemCode());   
      //�t�����g��������敪�R�[�h
      assertEquals("1",l_hostEqtypeOrderAllRow.getFrontOrderTradeCode());   
      //�Г���������
      assertEquals("123244444",l_hostEqtypeOrderAllRow.getCorpCode());   
      //�i������j�Г���������
      assertEquals("11111",l_hostEqtypeOrderAllRow.getOrgCorpCode());   
      //�S���������敪
      assertEquals("1",l_hostEqtypeOrderAllRow.getAllOrderChangeDiv());   
      //�����敪
      assertEquals("0",l_hostEqtypeOrderAllRow.getStatus()); 

      log.exiting(STR_METHOD_NAME);
  }
}

@
