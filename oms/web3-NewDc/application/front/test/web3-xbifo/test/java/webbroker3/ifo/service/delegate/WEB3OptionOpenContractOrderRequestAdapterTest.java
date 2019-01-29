head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOpenContractOrderRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionOpenContractOrderRequestAdapterTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/24 劉剣（中訊）新規作成
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOpenContractOrderRequestAdapterTest extends TestBaseForMock
{
    private WEB3OptionOpenContractOrderRequestAdapter l_adapter = null;

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3OptionOpenContractOrderRequestAdapterTest.class);
    
    public WEB3OptionOpenContractOrderRequestAdapterTest(String name)
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

    public void testGetPrice_C0001()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_adapter = WEB3OptionOpenContractOrderRequestAdapter.create(new WEB3GenRequestForTest());
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0.0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetPrice_C0002()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //WEB3OptionsOpenMarginConfirmRequest
            WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "5000";
            this.l_adapter = WEB3OptionOpenContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(5000,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetPrice_C0003()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //WEB3OptionsOpenMarginConfirmRequest
            WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3OptionOpenContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetPrice_C0004()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //WEB3OptionsOpenMarginConfirmRequest
            WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            this.l_adapter = WEB3OptionOpenContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetPrice_C0005()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //WEB3OptionsOpenMarginCompleteRequest
            WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "5000";
            this.l_adapter = WEB3OptionOpenContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(5000,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetPrice_C0006()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //WEB3OptionsOpenMarginCompleteRequest
            WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3OptionOpenContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetPrice_C0007()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0007";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //WEB3OptionsOpenMarginCompleteRequest
            WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            this.l_adapter = WEB3OptionOpenContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3GenRequestForTest extends WEB3GenRequest
    {

        public WEB3GenResponse createResponse()
        {
            return null;
        }
        
    }
}
@
