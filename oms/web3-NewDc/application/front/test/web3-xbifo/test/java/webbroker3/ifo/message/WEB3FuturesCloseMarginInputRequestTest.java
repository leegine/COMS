head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3FuturesCloseMarginInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/03 安陽(中訊) 新規作成
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginInputRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCloseMarginInputRequestTest.class);
    
    WEB3FuturesCloseMarginInputRequest l_request = null;

    public WEB3FuturesCloseMarginInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_request = new WEB3FuturesCloseMarginInputRequest();
    }

    protected void tearDown() throws Exception
    {
        l_request = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest.validate()'
     */ 
    
    //１）　@ＩＤチェック<BR>
    //　@this.ＩＤがnullの値であれば例外をスローする。<BR> 
    public void testValidate_C0001()
    {
        String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }        
    }
    
    //２）　@ソートキーチェック<BR>
    //　@２－１）this.ソートキーがnullの値であれば例外をスローする。<BR>
    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.id = new String[0];
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }        
    }
    
    //２－２）this.ソートキーの要素数が０であれば例外をスローする。
    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.id = new String[0];
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[0];
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }        
    }
    
    //２－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。
    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.id = new String[0];
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }        
    }
    
    //２－３－２）ソートキー.キー項目に以下の項目名以外の値が存在したら例外をスローする。
    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.id = new String[0];
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "other";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }        
    }
    
    //２－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.id = new String[0];
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }        
    }
    
    //２－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。
    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.id = new String[0];
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractPrice";
            l_request.futOpSortKeys[0].ascDesc = "other";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }        
    }
    
    //normal case
    public void testValidate_C0008()
    {
        String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.id = new String[0];
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[2];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.futOpSortKeys[0].ascDesc = "A";
            l_request.futOpSortKeys[1] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[1].keyItem = "contractPrice";
            l_request.futOpSortKeys[1].ascDesc = "D";
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
