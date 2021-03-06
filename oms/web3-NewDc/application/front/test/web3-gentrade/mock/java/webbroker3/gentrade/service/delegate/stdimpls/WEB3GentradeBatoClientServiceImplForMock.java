head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBatoClientServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : dqตVXeฺฑT[rXภForMock(WEB3GentradeBatoClientServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 Gฬ (u) VK์ฌ
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeMultiCheckResults;
import webbroker3.gentrade.message.WEB3GentradeMultiDocCheckResultUnit;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * dqตVXeฺฑT[rXภForMock
 *
 * @@author Gฬ(u)
 * @@version 1.0
 */
public class WEB3GentradeBatoClientServiceImplForMock
    extends WEB3GentradeBatoClientServiceImpl
{
    /**
     * Ooอ[eBeB
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBatoClientServiceImplForMock.class);

    /**
     * ivalidateฺ_ฉ{(Mock)j<br />
     * @@param l_typeCode ํสR[h(oqwๆ่ๆพ)<br />
     * @@param l_productCode มฟR[h<br />
     * @@return webbroker3.gentrade.message.WEB3GentradeProspectusResult<br />
     */
    public WEB3GentradeProspectusResult validateProspectus(
        String l_typeCode, String l_productCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.validateProspectus(String,String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1jาษ้ๆ
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
            "validateProspectus",
            new Class[] {String.class, String.class},
            new Object[]{l_typeCode, l_productCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
            "validateProspectus",
            new Class[] {String.class, String.class}))
        {
            //2jMockFor --r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class}).asWEB3BaseException();

            //3)MockFor --r asObject
            return (WEB3GentradeProspectusResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateProspectus(l_typeCode, l_productCode);
    }
    
    
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "execute",
                new Class[]{WEB3GenRequest.class},
                new Object[]{l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl", 
                "execute",
                new Class[]{WEB3GenRequest.class}))
        {
            log.debug("webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl.execute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[]{WEB3GenRequest.class}).asWEB3BaseException();
            
            log.debug("webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl.execute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
            "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
            "execute",
            new Class[]{WEB3GenRequest.class}).asWEB3BaseRuntimeException();
            
            return (WEB3GenResponse) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "execute",
                    new Class[]{WEB3GenRequest.class}).asObject();
        }
        return super.execute(l_request);
    }
    
    public boolean isBatoStopping() throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl", "isBatoStopping",
                new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl", "isBatoStopping",
                new Class[]
                {}))
        {
            log
                    .debug("webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImplForMock.isBatoStopping()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "isBatoStopping", new Class[]
                    {}).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "isBatoStopping", new Class[]
                    {}).asBoolean();
        }
        return super.isBatoStopping();
    }
    
    public WEB3GentradeMultiCheckResults validateMultiProspectus(
            WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit, boolean l_blnIsCheckFlag)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateMultiProspectus", new Class[]
                {WEB3GentradeMultiDocCheckResultUnit[].class, boolean.class}, new Object[]
                {l_multiDocCheckResultUnit, new Boolean(l_blnIsCheckFlag)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateMultiProspectus", new Class[]
                {WEB3GentradeMultiDocCheckResultUnit[].class, boolean.class}))
        {
            log
                    .debug("webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImplForMock.validateMultiProspectus()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateMultiProspectus", new Class[]
                    {WEB3GentradeMultiDocCheckResultUnit[].class, boolean.class}).asWEB3BaseException();
            return (WEB3GentradeMultiCheckResults) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateMultiProspectus", new Class[]
                    {WEB3GentradeMultiDocCheckResultUnit[].class, boolean.class}).asObject();
        }
        return super.validateMultiProspectus(l_multiDocCheckResultUnit, l_blnIsCheckFlag);
    }
    
    
    
    
    
   
}
@
