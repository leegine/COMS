head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3FuturesCloseMarginListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/04 安陽(中訊) 新規作成
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginListRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCloseMarginListRequestTest.class);
    
    WEB3FuturesCloseMarginListRequest l_request = null;
    
    public WEB3FuturesCloseMarginListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_request = new WEB3FuturesCloseMarginListRequest();
    }

    protected void tearDown() throws Exception
    {
        l_request = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifo.message.WEB3FuturesCloseMarginListRequest.validate()'
     */
    
    //１）　@ソートキーチェック
    //１－１）株価指数先物オプションソートキーがnullの値であれば例外をスローする
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //１－２）this.株価指数先物オプションソートキーの要素数が０であれば例外をスローする。
    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
    
    //１－３）this.株価指数先物オプションソートキーの要素数分繰り返してチェックを行う。
    //１－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。
    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
    
    //１－３－２）ソートキー.キー項目に以下の項目名以外の値が存在したら例外をスローする。
    //銘柄コード
    //建区分
    //損益
    //損益(諸経費込)
    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
    
    //１－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "futProductCode";
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
    
    //１－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。
    //”A：昇順”
    //”D：降順”
    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "futProductCode";
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

    //２）　@要求ページ番号チェック
    //２－１）this.要求ページ番号がnullの値であれば例外をスローする。
    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "income";
            l_request.futOpSortKeys[0].ascDesc = "A";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }    
    
    //２－２）this.要求ページ番号が数字以外の値であれば例外をスローする。
    public void testValidate_C0008()
    {
        String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "incomeCost";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "a";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //２－２）this.要求ページ番号が数字以外の値であれば例外をスローする。
    public void testValidate_C0009()
    {
        String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "incomeCost";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = ",";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //３）　@ページ内表示行数チェック
    //３－１）this.ページ内表示行数がnullの値であれば例外をスローする。
    public void testValidate_C0010()
    {
        String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //３－２）this.ページ内表示行数が数字以外の値であれば例外をスローする。
    public void testValidate_C0011()
    {
        String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "0";
            l_request.pageSize = "a";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //３－２）this.ページ内表示行数が数字以外の値であれば例外をスローする。
    public void testValidate_C0012()
    {
        String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "0";
            l_request.pageSize = ",";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
   
    //４） 銘柄設定チェック
    //４－１）以下の全てのリクエスト項目を設定している場合、例外をスローする。
    //    　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)
    public void testValidate_C0013()
    {
        String STR_METHOD_NAME = "testValidate_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = "b1";
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = "b3";
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
    
    // ４－２）銘柄選択時に取引市場,指数種別,限月のいずれかの項目が              
    // 設定されていない場合、例外をスローする。
    public void testValidate_C0014()
    {
        String STR_METHOD_NAME = "testValidate_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = null;
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = "b3";
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
    
    // ４－２）銘柄選択時に取引市場,指数種別,限月のいずれかの項目が              
    // 設定されていない場合、例外をスローする。    
    public void testValidate_C0015()
    {
        String STR_METHOD_NAME = "testValidate_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = "b1";
            l_request.targetProductCode = null;
            l_request.delivaryMonth = "b3";
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
    
    // ４－２）銘柄選択時に取引市場,指数種別,限月のいずれかの項目が              
    // 設定されていない場合、例外をスローする。    
    public void testValidate_C0016()
    {
        String STR_METHOD_NAME = "testValidate_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = "b1";
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = null;
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
    
    // ４－２）銘柄選択時に取引市場,指数種別,限月のいずれかの項目が              
    // 設定されていない場合、例外をスローする。    
    public void testValidate_C0017()
    {
        String STR_METHOD_NAME = "testValidate_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = null;
            l_request.marketCode = "b1";
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = null;
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
    
    //normal case
    public void testValidate_C0018()
    {
        String STR_METHOD_NAME = "testValidate_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[2];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.futOpSortKeys[1] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[1].keyItem = "income";
            l_request.futOpSortKeys[1].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = null;
            l_request.targetProductCode = null;
            l_request.delivaryMonth = null;
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
    
    //normal case
    public void testValidate_C0019()
    {
        String STR_METHOD_NAME = "testValidate_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = null;
            l_request.marketCode = null;
            l_request.targetProductCode = null;
            l_request.delivaryMonth = null;
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
    
    //normal case
    public void testValidate_C0020()
    {
        String STR_METHOD_NAME = "testValidate_C0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = null;
            l_request.marketCode = "b1";
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = "b3";
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
