head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticProductRegistCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondDomesticProductRegistCommonRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductRegistCommonRequestTest.class);

    WEB3AdminBondDomesticProductRegistCommonRequest l_request =
        new WEB3AdminBondDomesticProductRegistCommonRequest();
    public WEB3AdminBondDomesticProductRegistCommonRequestTest(String arg0)
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

    public void testValidate_case0001()
    {
        final String STR_METHOD_NAME = "testValidate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("ñ¡ïøIDÇ™ñ¢éwíËÇ≈Ç∑ÅB", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0002()
    {
        final String STR_METHOD_NAME = "testValidate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.productId = "123";
            l_request.bondDomesticProductUpdateInfo = new WEB3BondDomesticProductUpdateInfo();
            l_request.bondDomesticProductUpdateInfo.tradeHandleDiv = "2";
            l_request.bondDomesticProductUpdateInfo.dealingType = "3";
            l_request.bondDomesticProductUpdateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070604", "yyyyMMdd");
            l_request.bondDomesticProductUpdateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_request.bondDomesticProductUpdateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("20070610", "yyyyMMdd");
            l_request.bondDomesticProductUpdateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_request.bondDomesticProductUpdateInfo.deliveryDate = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_request.bondDomesticProductUpdateInfo.productNameWEB3 = "1234567890123456789012890";
            l_request.bondDomesticProductUpdateInfo.applyUnit = "1234567";
            l_request.bondDomesticProductUpdateInfo.minFaceAmount = "123456";
            l_request.bondDomesticProductUpdateInfo.maxFaceAmount = "1234567";
            l_request.bondDomesticProductUpdateInfo.prospectusCheckDiv = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateResponse()
    {
        final String STR_METHOD_NAME = "testValidate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GenResponse l_response =
                l_request.createResponse();
            assertEquals(null, l_response);
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
