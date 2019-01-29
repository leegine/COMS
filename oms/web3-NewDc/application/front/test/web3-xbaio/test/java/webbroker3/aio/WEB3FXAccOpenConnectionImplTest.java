head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXAccOpenConnectionImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXAccOpenConnectionImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenConnectionImplTest.class);
    public WEB3FXAccOpenConnectionImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_doAccountOpen_0001()
    {
        final String STR_METHOD_NAME = "test_doAccountOpen_0001()";
        log.entering(STR_METHOD_NAME);
        CompFxConditionParams l_compFxConditionParams = new CompFxConditionParams();
        l_compFxConditionParams.setExtConnectSystemCode("01");
        l_compFxConditionParams.setFxSystemCode("01");
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
        l_fXGftAskingTelegramUnit.institutionCode = "0D";
        l_fXGftAskingTelegramUnit.branchCode = "381";
        l_fXGftAskingTelegramUnit.accountCode = "2512246";
        l_fXGftAskingTelegramUnit.gftOperationDiv = "07";
        l_fXGftAskingTelegramUnit.requestNumber = "11";
        WEB3FXAccOpenConnectionImplForMork l_fxAccOpenConnectionImpl = new WEB3FXAccOpenConnectionImplForMork();
        try
        {
//            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
//            
//            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDelAndCommit(l_branchParams);
//            
//            TestDBUtility.deleteAllAndCommit(SoapConnectPrefRpcParams.TYPE);
//            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
//            l_soapConnectPrefRpcParams.setBranchId(33381);
//            l_soapConnectPrefRpcParams.setConnectDiv("01");
//            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
//            l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
//            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
//            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
//            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
//            l_soapConnectPrefRpcParams.setResponseParamType("#");
//            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            
//            l_soapConnectPrefRpcParams.setResponseTimeout("50");
//            TestDBUtility.insertWithDelAndCommit(l_soapConnectPrefRpcParams);
//            
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDelAndCommit(l_institutionParams); 
//            
//            TestDBUtility.deleteAllAndCommit(CompFxConditionParams.TYPE);
//            CompFxConditionParams l_compFxConditionParams1 = TestDBUtility.getCompFxConditionRow();
//            l_compFxConditionParams1.setInstitutionCode("0D");
//            l_compFxConditionParams1.setBranchCode("381");
//            l_compFxConditionParams1.setFxSystemCode("01");
//            l_compFxConditionParams1.setGetTransferableAmtDiv("1");
//            TestDBUtility.insertWithDelAndCommit(l_compFxConditionParams1);
//            
//            TestDBUtility.deleteAllAndCommit(GftAccountOpenStatusParams.TYPE);
//            GftAccountOpenStatusParams l_gftAccountOpenStatusParams = TestDBUtility.getGftAccountOpenStatusRow();
//            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
//            l_gftAccountOpenStatusParams.setBranchCode("381");
//            l_gftAccountOpenStatusParams.setOrderRequestNumber("11");
//            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
//            TestDBUtility.insertWithDelAndCommit(l_gftAccountOpenStatusParams);
//            
            WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
                l_fxAccOpenConnectionImpl.doAccountOpen(l_compFxConditionParams, l_fXGftAskingTelegramUnit);
            
            WEB3FXAccInformationUnit[] l_units = l_fXGftResultNoticeTelegramUnit.fxAccInformationList;
            assertEquals("10001", l_units[0].fxAccountCode);
            assertEquals("1", l_units[0].fxCourseDiv);
            assertEquals("10002", l_units[1].fxAccountCode);
            assertEquals("2", l_units[1].fxCourseDiv);
            assertEquals("10003", l_units[2].fxAccountCode);
            assertEquals("3", l_units[2].fxCourseDiv);
            assertEquals("10004", l_units[3].fxAccountCode);
            assertEquals("4", l_units[3].fxCourseDiv);
            assertEquals("10005", l_units[4].fxAccountCode);
            assertEquals("5", l_units[4].fxCourseDiv);
            assertEquals("10006", l_units[5].fxAccountCode);
            assertEquals("6", l_units[5].fxCourseDiv);
            assertEquals("10007", l_units[6].fxAccountCode);
            assertEquals("7", l_units[6].fxCourseDiv);
            assertEquals("10008", l_units[7].fxAccountCode);
            assertEquals("8", l_units[7].fxCourseDiv);
            assertEquals("10009", l_units[8].fxAccountCode);
            assertEquals("9", l_units[8].fxCourseDiv);
            assertEquals("10010", l_units[9].fxAccountCode);
            assertEquals("10", l_units[9].fxCourseDiv);
        }
        catch (Exception e)
        {
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_updateGFTAccountOpenStatus_0001()
    {
        final String STR_METHOD_NAME = "test_updateGFTAccountOpenStatus_0001()";
        log.entering(STR_METHOD_NAME);
        Services.unregisterService(WEB3FXDataControlService.class);
        Services.registerService(WEB3FXDataControlService.class, new WEB3FXDataControlServiceImplForTest());
        WEB3FXAccOpenConnectionImpl l_fxAccOpenConnectionImpl = new WEB3FXAccOpenConnectionImpl();
        try
        {
            l_fxAccOpenConnectionImpl.updateGFTAccountOpenStatus("", "", "", "");
            fail();
        } catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01800, e.getErrorInfo());
            Services.unregisterService(WEB3FXDataControlService.class);
            Services.registerService(WEB3FXDataControlService.class, new WEB3FXDataControlServiceImpl());
        }
        catch (Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_updateGFTAccountOpenStatus_0002()
    {
        final String STR_METHOD_NAME = "test_updateGFTAccountOpenStatus_0002()";
        log.entering(STR_METHOD_NAME);
        Services.unregisterService(WEB3FXDataControlService.class);
        Services.registerService(WEB3FXDataControlService.class, new WEB3FXDataControlServiceImplForTest());
        WEB3FXAccOpenConnectionImpl l_fxAccOpenConnectionImpl = new WEB3FXAccOpenConnectionImpl();
        try
        {
            l_fxAccOpenConnectionImpl.updateGFTAccountOpenStatus("", "0", "", "");
            Services.unregisterService(WEB3FXDataControlService.class);
            Services.registerService(WEB3FXDataControlService.class, new WEB3FXDataControlServiceImpl());
        } catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3FXAccOpenConnectionImplForMork extends WEB3FXAccOpenConnectionImpl
    {
        public WEB3ExtConnection sendExtConnAskingMessage(
                CompFxConditionParams l_compFxConditionParams,
                WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit) throws WEB3BaseException
        {
            return new WEB3GFTConnectionSystemForMork();
        }
        
        public void updateGFTAccountOpenStatus(
                String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strResultCode)
                throws WEB3BaseException
        {
            
        }
    }
    
    public class WEB3GFTConnectionSystemForMork extends WEB3GFTConnectionSystem
    {
        public Object getResult(String l_strName)
        {
            final String STR_METHOD_NAME = "getResult(String)";
            log.entering(STR_METHOD_NAME);
            
            Object l_result = "M";
            
            HashMap l_map = new HashMap();
            l_map.put(WEB3ExtConnection.CONNECT_RESULT, "M");
            l_map.put(WEB3ExtConnection.FX_ACC_01, new Long(10001));
            l_map.put(WEB3ExtConnection.FX_ACC_10, new Long(10002));
            l_map.put(WEB3ExtConnection.CFD_ACC, new Long(10003));
            l_map.put(WEB3ExtConnection.CFD_ACC2, new Long(10004));
            l_map.put(WEB3ExtConnection.CFD_ACC3, new Long(10005));
            l_map.put(WEB3ExtConnection.CFD_ACC4, new Long(10006));
            l_map.put(WEB3ExtConnection.CFD_ACC5, new Long(10007));
            l_map.put(WEB3ExtConnection.CFD_ACC6, new Long(10008));
            l_map.put(WEB3ExtConnection.CFD_ACC7, new Long(10009));
            l_map.put(WEB3ExtConnection.CFD_ACC8, new Long(10010));
            
            if (l_map.get(l_strName) != null)
            {
                l_result = l_map.get(l_strName);
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_result;

        }
    }
    
    public class WEB3FXDataControlServiceImplForTest extends WEB3FXDataControlServiceImpl
    {
        public GftAccountOpenStatusParams getGFTAccountOpenStatus(
                String l_strInstitutionCode, String l_strBranchCode,
                String l_strRequestNumber)
        {
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams = new GftAccountOpenStatusParams();
            if (l_strBranchCode == "0")
            {
                l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
                return l_gftAccountOpenStatusParams;
            }
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("1");
            return l_gftAccountOpenStatusParams;
        }
    }
}
@
