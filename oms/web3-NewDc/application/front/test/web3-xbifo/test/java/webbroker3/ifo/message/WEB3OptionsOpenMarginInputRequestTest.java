head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション新規建注文入力画面リクエスト(WEB3OptionsOpenMarginInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/27 孫洪江(中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsOpenMarginInputRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsOpenMarginInputRequestTest.class);

    public WEB3OptionsOpenMarginInputRequestTest(String arg0)
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
     * １）　@建区分チェック
     * １－１）this.建区分がnullの値であれば例外をスローする。
     */
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        l_request.contractType = null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00263, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * １）　@建区分チェック
     * １－１）this.建区分がnullの値であれば例外をスローする。
     */
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        l_request.contractType = "1";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * １－２）this.建区分が以下の値以外の場合例外をスローする。　@　@
     * ・”1：買建”
     * ・”2：売建”
     */
    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        l_request.contractType = "3";
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00264, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * １－２）this.建区分が以下の値以外の場合例外をスローする。　@　@
     * ・”1：買建”
     * ・”2：売建”
     */
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        l_request.contractType = "2";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * ２）銘柄設定チェック
     * 　@２－１）全てのリクエスト項目を設定している場合、例外をスローする。
     * 　@　@　@・銘柄コード
     * 　@　@　@・取引市場
     * 　@　@　@・指数種別
     * 　@　@　@・限月
     * 　@　@　@・オプション商品区分
     * 　@　@　@・行使価格
     */
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        
        l_request.opProductCode = "1";               
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * ２）銘柄設定チェック
     * 　@２－１）全てのリクエスト項目を設定している場合、例外をスローする。
     * 　@　@　@・銘柄コード
     * 　@　@　@・取引市場
     * 　@　@　@・指数種別
     * 　@　@　@・限月
     * 　@　@　@・オプション商品区分
     * 　@　@　@・行使価格
     */
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        
        l_request.opProductCode = null;               
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * ２－２）銘柄選択時に
     * 取引市場,指数種別,限月,オプション商品区分,行使価格の 
     * いずれかの項目が設定されていない場合、例外をスローする。
     */
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= null;               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * ２－２）銘柄選択時に
     * 取引市場,指数種別,限月,オプション商品区分,行使価格の 
     * いずれかの項目が設定されていない場合、例外をスローする。
     */
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= "1";               
        l_request.targetProductCode= null;                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * ２－２）銘柄選択時に
     * 取引市場,指数種別,限月,オプション商品区分,行使価格の 
     * いずれかの項目が設定されていない場合、例外をスローする。
     */
    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= null;                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * ２－２）銘柄選択時に
     * 取引市場,指数種別,限月,オプション商品区分,行使価格の 
     * いずれかの項目が設定されていない場合、例外をスローする。
     */
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= null;                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * ２－２）銘柄選択時に
     * 取引市場,指数種別,限月,オプション商品区分,行使価格の 
     * いずれかの項目が設定されていない場合、例外をスローする。
     */
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= null;
        l_request.contractType ="1";
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * ２－２）銘柄選択時に
     * 取引市場,指数種別,限月,オプション商品区分,行使価格の 
     * いずれかの項目が設定されていない場合、例外をスローする。
     */
    public void testValidate_0012()
    {
        String STR_METHOD_NAME = ".testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= null;               
        l_request.targetProductCode= null;                
        l_request.delivaryMonth= null;                
        l_request.opProductType= null;                
        l_request.strikePrice= null;
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
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
