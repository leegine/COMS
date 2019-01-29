head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.26.06.01.11;	author liu-lei;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3004db65fa72760;
filename	WEB3GentradeSystemSoapConnectServiceImplTest.java;

1.1
date	2011.04.07.01.59.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeSystemSoapConnectServiceImplTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3DocumentDeliverHistoryRegistHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/23 陸文靜（中訊）新規作成
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.axis2.jaxws.handler.SoapMessageContext;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.SoapConnectPrefMsgParams;
import webbroker3.gentrade.data.SoapConnectPrefMsgRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcRow;
import webbroker3.gentrade.service.delegate.WEB3GentradeSystemSoapConnectService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeSystemSoapConnectServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3GentradeSystemSoapConnectServiceImplTest.class);
    public WEB3GentradeSystemSoapConnectServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    protected void setUp() throws Exception
    {
        super.setUp();
    }
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public void testSendMessage_C0001()
    {
        final String STR_METHOD_NAME = "testSendMessage_C0001()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeSystemSoapConnectServiceImplForTest l_gentradeSystemSoapConnectServiceImpl =
            new WEB3GentradeSystemSoapConnectServiceImplForTest();
        long l_lngBranchId = 33381; 
        String l_strConnectDiv = new String("01"); 
        String[] l_strParameterlists = new String[]{"1"};
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SoapConnectPrefMsgRow.TYPE);
            SoapConnectPrefMsgParams l_soapConnectPrefMsgParams = 
                TestDBUtility.getSoapConnectPrefMsgRow();
            TestDBUtility.insertWithDel(l_soapConnectPrefMsgParams);

            l_gentradeSystemSoapConnectServiceImpl.sendMessage(l_lngBranchId,l_strConnectDiv,l_strParameterlists);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testSendMessage_C0002()
    {
        final String STR_METHOD_NAME = "testSendMessage_C0002()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeSystemSoapConnectServiceImplForTest l_gentradeSystemSoapConnectServiceImpl =
            new WEB3GentradeSystemSoapConnectServiceImplForTest();
        long l_lngBranchId = 33381; 
        String l_strConnectDiv = new String("01"); 
        String[] l_strParameterlists = new String[]{"1"};
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SoapConnectPrefMsgRow.TYPE);
            SoapConnectPrefMsgParams l_soapConnectPrefMsgParams = 
                TestDBUtility.getSoapConnectPrefMsgRow();
            l_soapConnectPrefMsgParams.setEndpointName("url;abc;sdhd;djjj");
            TestDBUtility.insertWithDel(l_soapConnectPrefMsgParams);

            l_gentradeSystemSoapConnectServiceImpl.sendMessage(l_lngBranchId,l_strConnectDiv,l_strParameterlists);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02398,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testSendMessage_C0003()
    {
        final String STR_METHOD_NAME = "testSendMessage_C0003()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeSystemSoapConnectServiceImplForTest l_gentradeSystemSoapConnectServiceImpl =
            new WEB3GentradeSystemSoapConnectServiceImplForTest();
        long l_lngBranchId = 33381; 
        String l_strConnectDiv = new String("01"); 
        String[] l_strParameterlists = new String[]{"1"};
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SoapConnectPrefMsgRow.TYPE);
            SoapConnectPrefMsgParams l_soapConnectPrefMsgParams = 
                TestDBUtility.getSoapConnectPrefMsgRow();
            l_soapConnectPrefMsgParams.setEndpointName("url;abc;sdhd");
            TestDBUtility.insertWithDel(l_soapConnectPrefMsgParams);

            l_gentradeSystemSoapConnectServiceImpl.sendMessage(l_lngBranchId,l_strConnectDiv,l_strParameterlists);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testSendMessage_C0004()
    {
        final String STR_METHOD_NAME = "testSendMessage_C0004()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeSystemSoapConnectServiceImplForTest l_gentradeSystemSoapConnectServiceImpl =
            new WEB3GentradeSystemSoapConnectServiceImplForTest();
        long l_lngBranchId = 33381; 
        String l_strConnectDiv = new String("01"); 
        String[] l_strParameterlists = new String[]{"1"};
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SoapConnectPrefMsgRow.TYPE);
            SoapConnectPrefMsgParams l_soapConnectPrefMsgParams = 
                TestDBUtility.getSoapConnectPrefMsgRow();
            l_soapConnectPrefMsgParams.setEndpointName("url;abc;https://sdhd");
            TestDBUtility.insertWithDel(l_soapConnectPrefMsgParams);

            l_gentradeSystemSoapConnectServiceImpl.sendMessage(l_lngBranchId,l_strConnectDiv,l_strParameterlists);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testSendMessage_C0005()
    {
        final String STR_METHOD_NAME = "testSendMessage_C0005()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeSystemSoapConnectServiceImplForTest l_gentradeSystemSoapConnectServiceImpl =
            new WEB3GentradeSystemSoapConnectServiceImplForTest();
        long l_lngBranchId = 33381; 
        String l_strConnectDiv = new String("01"); 
        String[] l_strParameterlists = new String[]{"1"};
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SoapConnectPrefMsgRow.TYPE);
            SoapConnectPrefMsgParams l_soapConnectPrefMsgParams = 
                TestDBUtility.getSoapConnectPrefMsgRow();
            l_soapConnectPrefMsgParams.setEndpointName("url;abc;https://s");
            TestDBUtility.insertWithDel(l_soapConnectPrefMsgParams);

            l_gentradeSystemSoapConnectServiceImpl.sendMessage(l_lngBranchId,l_strConnectDiv,l_strParameterlists);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02398,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testSendMessage_C0006()
    {
        final String STR_METHOD_NAME = "testSendMessage_C0006()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeSystemSoapConnectServiceImplForTest l_gentradeSystemSoapConnectServiceImpl =
            new WEB3GentradeSystemSoapConnectServiceImplForTest();
        long l_lngBranchId = 33381; 
        String l_strConnectDiv = new String("01"); 
        String[] l_strParameterlists = new String[]{"1"};
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SoapConnectPrefMsgRow.TYPE);
            SoapConnectPrefMsgParams l_soapConnectPrefMsgParams = 
                TestDBUtility.getSoapConnectPrefMsgRow();
            l_soapConnectPrefMsgParams.setEndpointName("url;abc;https://s");
            TestDBUtility.insertWithDel(l_soapConnectPrefMsgParams);

            l_gentradeSystemSoapConnectServiceImpl.sendMessage(l_lngBranchId,l_strConnectDiv,l_strParameterlists);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02398,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }

    public class WEB3GentradeSystemSoapConnectServiceImplForTest extends WEB3GentradeSystemSoapConnectServiceImpl 
    {
        protected void createMessage(
            SoapConnectPrefMsgParams l_prefMsgParams, 
            String[] l_strParameterlists, 
            SOAPMessage l_soapMessage) throws WEB3BaseException
            {
                return;
            }

        protected void insertSOAPMessage(
                long l_lngBranchId, 
                String l_strConnectDiv, 
                String l_strSendReceiveDiv,
                String l_strMessage) throws WEB3BaseException
                {
                    return;
                }
        
        protected SoapMessageContext sendMessage(
                String l_soapUrl,
                SoapConnectPrefMsgParams l_msgParams,
                SoapMessageContext l_soapMessageContextForSend)
                throws WEB3BaseException, ConnectException, SocketTimeoutException, MalformedURLException
            {
                final String STR_METHOD_NAME = "sendMessage(String, SoapConnectPrefMsgParams, SoapMessageContext)";
                log.entering(STR_METHOD_NAME);
                if("https://s".equals(l_soapUrl))
                {
                    ConnectException e = new ConnectException();
                    throw e;
                }
                else if("https://ss".equals(l_soapUrl))
                {
                    SocketTimeoutException e = new SocketTimeoutException();
                    throw e;
                }
                else 
                {
                    return l_soapMessageContextForSend;
                }       
            }
        protected Object[] getReturnValues(
                SoapConnectPrefMsgParams l_prefMsgParams, 
                SoapMessageContext l_defaultMessageContext) throws WEB3BaseException
                {
                    Object[] l_object= new Object[1];
                    return l_object;
                }
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d10 1
a12 1
import javax.xml.rpc.handler.soap.SOAPMessageContext;
d16 2
a37 3
import weblogic.webservice.binding.BindingInfo;
import weblogic.webservice.core.DefaultMessageContext;

d308 1
d333 1
a333 1
            l_soapConnectPrefMsgParams.setEndpointName("url;abc;https://ss");
d371 5
a375 4
        protected SOAPMessageContext sendMessage(
                BindingInfo l_bindingInfo, 
                DefaultMessageContext l_messageContext) 
                throws WEB3BaseException, ConnectException, SocketTimeoutException
d377 1
a377 1
                final String STR_METHOD_NAME = "sendMessage(BindingInfo, DefaultMessageContext)";
d379 1
a379 1
                if("https://s".equals(l_bindingInfo.getAddress()))
d384 1
a384 1
                else if("https://ss".equals(l_bindingInfo.getAddress()))
d391 1
a391 1
                    return l_messageContext;
d396 1
a396 1
                DefaultMessageContext l_defaultMessageContext) throws WEB3BaseException
@

