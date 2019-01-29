head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ManualCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3ManualCommonRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/03 安陽(中訊) 新規作成
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ManualCommonRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3ManualCommonRequestTest.class);
    
    public WEB3ManualCommonRequestTest(String arg0)
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
    
    //１－１）this.銘柄タイプ == nullの場合、「銘柄タイプがnull」の例外をスローする。
    public void testValidate_C0001()
    {
        String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02394, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //１－２）this.銘柄タイプが以下の値以外の場合、「銘柄タイプが未定義の値」の例外をスローする。
    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = "2";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02395, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //２－１）this.条件注文種別 == nulの場合、「条件注文種別がnull」の例外をスローする。
    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = "1";
        l_request.triggerOrderType = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02396, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //２－２）this.条件注文種別が以下の値以外の場合、「条件注文種別が未定義の値」の例外をスローする。
    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = "6";
        l_request.triggerOrderType = "0";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02397, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //３－１）this.注文IDの要素数 == nullの場合、
    //　@　@　@　@「注文IDがnull」の例外をスローする。
    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = "1";
        l_request.triggerOrderType = "1";
        l_request.orderId = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
 
    //３－１）this.注文IDの要素数 == nullの場合、
    //　@　@　@　@「注文IDがnull」の例外をスローする。    
    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = "1";
        l_request.triggerOrderType = "2";
        l_request.orderId = new String[0];

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //３－２）this.注文IDの要素数分繰り返してチェックを行う。
    //　@　@・注文ID[ｎ番目の要素] == nullの場合、
    //　@　@　@　@「注文IDがnull」の例外をスローする。
    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = "1";
        l_request.triggerOrderType = "3";
        l_request.orderId = new String[1];
        l_request.orderId[0] = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //３－２）this.注文IDの要素数分繰り返してチェックを行う。
    //　@　@・注文ID[ｎ番目の要素] == nullの場合、
    //　@　@　@　@「注文IDがnull」の例外をスローする。
    public void testValidate_C0008()
    {
        String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = "1";
        l_request.triggerOrderType = "4";
        l_request.orderId = new String[]{""};

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //normal case
    public void testValidate_C0009()
    {
        String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3ManualCommonRequest l_request = new WEB3ManualCommonRequest();
        l_request.productType = "1";
        l_request.triggerOrderType = "5";
        l_request.orderId = new String[]{"9", "9", "5"};

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
}
@
