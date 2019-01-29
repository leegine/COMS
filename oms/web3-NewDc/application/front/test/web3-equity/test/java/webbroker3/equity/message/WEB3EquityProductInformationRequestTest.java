head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityProductInformationRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3EquityProductInformationRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/30 ���n�i���u�j�V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityProductInformationRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3EquityProductInformationRequestTest.class);

    public WEB3EquityProductInformationRequestTest(String arg0)
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

    /*
     * ����d�l�ύX1237
     * 
     * ����ʉ�
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityProductInformationRequest l_productInformationRequest =
            new WEB3EquityProductInformationRequest();
        l_productInformationRequest.marketCode = WEB3MarketCodeDef.JNX_PTS;
        l_productInformationRequest.orderCommodityCode = null;
        
        try
        {
            l_productInformationRequest.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }
    
    /*
     * ����d�l�ύX1237
     * 
     * �e�o�ُ�M���FBUSINESS_ERROR_00608
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityProductInformationRequest l_productInformationRequest =
            new WEB3EquityProductInformationRequest();
        l_productInformationRequest.marketCode = "";
        try
        {
            l_productInformationRequest.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
}
@
