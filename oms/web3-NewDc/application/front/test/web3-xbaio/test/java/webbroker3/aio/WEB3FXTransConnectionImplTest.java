head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransConnectionImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransConnectionImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransConnectionImplTest.class);
    public WEB3FXTransConnectionImplTest(String name)
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
    public void test_doTransfer_0001()
    {
        final String STR_METHOD_NAME = "test_doTransfer_0001()";
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
        WEB3FXTransConnectionImpForTest l_fxTransConnectionImpForTest = new WEB3FXTransConnectionImpForTest();
        try
        {
            WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
                l_fxTransConnectionImpForTest.doTransfer(l_compFxConditionParams, l_fXGftAskingTelegramUnit);
            assertEquals("0D", l_fXGftResultNoticeTelegramUnit.institutionCode);
        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_updateGFTTransferStatus_0001()
    {
        final String STR_METHOD_NAME = "test_updateGFTTransferStatus_0001()";
        log.entering(STR_METHOD_NAME);
        Services.unregisterService(WEB3FXDataControlService.class);
        Services.registerService(WEB3FXDataControlService.class, new WEB3FXDataControlServiceImplForTest());
        WEB3FXTransConnectionImpl l_fxTransConnectionImpl = new WEB3FXTransConnectionImpl();
        try
        {
            l_fxTransConnectionImpl.updateGFTTransferStatus("", "3", "", "");
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
    
    public void test_updateGFTTransferStatus_0002()
    {
        final String STR_METHOD_NAME = "test_updateGFTTransferStatus_0001()";
        log.entering(STR_METHOD_NAME);
        Services.unregisterService(WEB3FXDataControlService.class);
        Services.registerService(WEB3FXDataControlService.class, new WEB3FXDataControlServiceImplForTest());
        WEB3FXTransConnectionImpl l_fxTransConnectionImpl = new WEB3FXTransConnectionImpl();
        try
        {
            l_fxTransConnectionImpl.updateGFTTransferStatus("", "", "", "");
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
    public class WEB3FXTransConnectionImpForTest extends WEB3FXTransConnectionImpl
    {
        public WEB3ExtConnection sendExtConnAskingMessage(
                CompFxConditionParams l_compFxConditionParams,
                WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit) throws WEB3BaseException
        {
            return new WEB3GFTConnectionSystemForMork();
        }
        
        public void updateGFTTransferStatus(
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

            log.exiting(STR_METHOD_NAME);
            return l_result;

        }
    }
    
    public class WEB3FXDataControlServiceImplForTest extends WEB3FXDataControlServiceImpl
    {
        public GftTransferStatusParams getGFTTransferStatus(
                String l_strInstitutionCode, String l_strBranchCode,
                String l_strRequestNumber) throws WEB3BaseException
        {
            GftTransferStatusParams l_gftTransferStatusParams = new GftTransferStatusParams();
            if (l_strBranchCode == "3")
            {
                l_gftTransferStatusParams.setTransferStatusDiv("3");
                return l_gftTransferStatusParams;
            }
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            return l_gftTransferStatusParams;
        }
        
        public void updateGFTTransferStatus(
                String l_strInstitutionCode, String l_strBranchCode,
                String l_strRequestCode, String l_strResultCode) throws WEB3BaseException
        {
        
        }
    }
}
@
