head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginCloseMarginConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
 File Name        : (WEB3MarginCloseMarginConfirmRequestTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/06/10  âΩï∂ïq(íÜêu)Å@@êVãKçÏê¨
 */
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidationsTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3MarginCloseMarginConfirmRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityTypeOrderManagerReusableValidationsTest.class);
    WEB3MarginCloseMarginConfirmRequest l_request =
        new WEB3MarginCloseMarginConfirmRequest();

    public WEB3MarginCloseMarginConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testValidate_Case0001()
    {
        final String STR_METHOD_NAME = "testValidate_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = true;

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.closingOrder = "0";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02812, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case0002()
    {
        final String STR_METHOD_NAME = "testValidate_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            l_unit.settlePriority = "123";
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = false;

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.closingOrder = "0";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case0003()
    {
        final String STR_METHOD_NAME = "testValidate_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = false;
            l_request.orderQuantity = "100";

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case0004()
    {
        final String STR_METHOD_NAME = "testValidate_Case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = true;
            l_request.orderQuantity = "100";
            l_request.expirationDateType = "2";
            l_request.expirationDate = WEB3DateUtility.getDate("20040206", "yyyyMMdd");

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02813, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case0005()
    {
        final String STR_METHOD_NAME = "testValidate_Case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = false;
            l_request.orderQuantity = "100";
            l_request.expirationDateType = "2";
            l_request.expirationDate = WEB3DateUtility.getDate("20040206", "yyyyMMdd");

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case0006()
    {
        final String STR_METHOD_NAME = "testValidate_Case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = true;
            l_request.orderQuantity = "100";
            l_request.expirationDateType = "1";

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02814, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case0007()
    {
        final String STR_METHOD_NAME = "testValidate_Case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginCloseMarginContractUnit l_unit =
                new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "123456";
            l_unit.orderQuantity = "100";
            l_request.orderPriceDiv = "0";
            
            l_request.orderCondType = "0";
            l_request.priceCondType = "0";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "1";
            l_request.execCondType = "1";
            l_request.stopOrderCondPrice = "15";
            l_request.stopOrderCondOperator = "1";
            l_request.manualForcedSettleFlag = false;
            l_request.orderQuantity = "100";
            l_request.expirationDateType = "1";

            l_request.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{l_unit};
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
