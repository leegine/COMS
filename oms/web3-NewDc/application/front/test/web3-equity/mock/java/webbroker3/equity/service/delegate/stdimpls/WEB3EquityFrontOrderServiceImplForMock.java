head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.26.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityFrontOrderServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3EquityFrontOrderServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/30 èôçGàÃ (íÜêu) êVãKçÏê¨
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author èôçGàÃ(íÜêu)
 * @@version 1.0
 */
public class WEB3EquityFrontOrderServiceImplForMock extends WEB3EquityFrontOrderServiceImpl
{
    /**
     * ÉçÉOèoóÕÉÜÅ[ÉeÉBÉäÉeÉBÅB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityFrontOrderServiceImplForMock.class);
    /**
     * (getî≠íçåoòHãÊï™(mock))<BR>
     */
    public String getSubmitOrderRouteDiv(
        EqTypeTradedProduct l_tradedProduct,
        String l_strInstitutionCode,
        String l_strSonarTradedCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubmitOrderRouteDiv(EqTypeTradedProduct, String, String)--forMock";
        log.entering(STR_METHOD_NAME);
        
        //1Åjô“ù…ÈÑÊö
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv",
            new Class[] {EqTypeTradedProduct.class, String.class,  String.class},
            new Object[]{l_tradedProduct, l_strInstitutionCode, l_strSonarTradedCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv",
            new Class[] {EqTypeTradedProduct.class, String.class,  String.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2ÅjMockFor --År asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {EqTypeTradedProduct.class, String.class,  String.class}).asWEB3BaseException();

            //3)MockFor --År asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {EqTypeTradedProduct.class, String.class,  String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getSubmitOrderRouteDiv (l_tradedProduct, l_strInstitutionCode, l_strSonarTradedCode);
    }
    
    public HostEqtypeOrderAllParams getHostEqtypeOrderAll(EqTypeOrderUnit l_orderUnit) 
    throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                 "getHostEqtypeOrderAll",
                 new Class[] {EqTypeOrderUnit.class},
                 new Object[]{l_orderUnit});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getHostEqtypeOrderAll",
                new Class[] {EqTypeOrderUnit.class}))
        {
            log.debug("webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImplForMock.getHostEqtypeOrderAll()");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class}).asWEB3BaseException();
            
            return (HostEqtypeOrderAllParams)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class}).asObject();
        }
                
        return super.getHostEqtypeOrderAll(l_orderUnit);
    }
    
    public void lockHostEqtypeOrderAll(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "lockHostEqtypeOrderAll",
                new Class[] {EqTypeOrderUnit.class},
                new Object[]{l_orderUnit});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "lockHostEqtypeOrderAll",
                new Class[] {EqTypeOrderUnit.class}))
        {
            log.debug("webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImplForMock.lockHostEqtypeOrderAll()");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "lockHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "lockHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class}).asVoid();
            return;
        }
        
        super.lockHostEqtypeOrderAll(l_orderUnit);
    }
    
    public String getFrontOrderSystemCode(String l_strMarketCode, String l_strOpenOtcDiv)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getFrontOrderSystemCode", new Class[]
                { String.class, String.class }, new Object[]
                { l_strMarketCode, l_strOpenOtcDiv });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getFrontOrderSystemCode", new Class[]
                { String.class, String.class }))
        {
            log
                    .debug("webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImplForMock.getFrontOrderSystemCode()");
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getFrontOrderSystemCode", new Class[]
                    { String.class, String.class }).asObject();
        }
        return super.getFrontOrderSystemCode(l_strMarketCode, l_strOpenOtcDiv);
    }
    
    public String getChangeSubmitOrderRouteDiv(
            EqTypeOrderUnit l_orderUnit)
            throws WEB3BaseException
        {
    	final String STR_METHOD_NAME = "getChangeSubmitOrderRouteDiv(EqTypeTradedProduct)--forMock";
        log.entering(STR_METHOD_NAME);
        
        //1Åjô“ù…ÈÑÊö
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
            "getChangeSubmitOrderRouteDiv",
            new Class[] {EqTypeOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
            "getChangeSubmitOrderRouteDiv",
            new Class[] {EqTypeOrderUnit.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2ÅjMockFor --År asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getChangeSubmitOrderRouteDiv",
                new Class[] {EqTypeOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --År asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getChangeSubmitOrderRouteDiv",
                new Class[] {EqTypeOrderUnit.class}).asObject();
        }
        log.exiting(STR_METHOD_NAME);
        return super.getChangeSubmitOrderRouteDiv (l_orderUnit);
        }
    public String getChangeOrderRev(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getChangeOrderRev(EqTypeOrderUnit)--forMock";
        log.entering(STR_METHOD_NAME);
        
        //1Åjô“ù…ÈÑÊö
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
            "getChangeOrderRev",
            new Class[] {EqTypeOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
            "getChangeOrderRev",
            new Class[] {EqTypeOrderUnit.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2ÅjMockFor --År asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getChangeOrderRev",
                new Class[] {EqTypeOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --År asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "getChangeOrderRev",
                new Class[] {EqTypeOrderUnit.class}).asObject();
        }
            log.exiting(STR_METHOD_NAME);
            return super.getChangeOrderRev (l_orderUnit);
    }
}
@
