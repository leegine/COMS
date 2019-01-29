head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション注文約定照会リクエスト(WEB3OptionsExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/04 孟亞南 (中訊) 新規作成
Revision History : 2007/10/18 張騰宇 (中訊)
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsExecuteReferenceRequestTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsCommonRequestTest.class);
    
    public WEB3OptionsExecuteReferenceRequestTest(String name)
    {
        super(name);
    }

    /**
     * this.照会区分がnullの値であれば例外をスローする
     * date:
     * this.照会区分 = null
     * テスト確認内容:
     * BUSINESS_ERROR_00081
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.referenceType = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00081, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.照会区分が以下の値以外の場合例外をスローする
     * ”0：注文約定照会” 
     * ”1：訂正取消一覧（訂正取消可能なもののみ表示）”
     * date:
     * this.照会区分 = "2"
     * テスト確認内容:
     * BUSINESS_ERROR_00082
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.referenceType = "2";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.照会区分 = "0：注文約定照会" pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = “0：未約定”
     * this.株価指数先物オプションソートキー.キー項目 = 銘柄コード 
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.照会区分 = "1：訂正取消一覧（訂正取消可能なもののみ表示）" pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = “0：未約定”
     * this.株価指数先物オプションソートキー.キー項目 = 銘柄コード 
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "1";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.約定状態区分≠null かつ、  
     * this.約定状態区分が以下の値以外の場合例外をスローする。  
     * ”0：未約定” 
     * ”1：一部成立”
     * ”2：全部成立” 
     * date:
     * this.照会区分 = “0：注文約定照会 ”
     * this.約定状態区分 = "3"
     * テスト確認内容:
     * BUSINESS_ERROR_00626
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "3";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.約定状態区分 =  ”0：未約定”  pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = “0：未約定”
     * this.株価指数先物オプションソートキー.キー項目 = 銘柄コード 
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.約定状態区分 =  ”1：一部成立”   pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = ”1：一部成立”
     * this.株価指数先物オプションソートキー.キー項目 = 銘柄コード 
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "1";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.約定状態区分 = ”2：全部成立” pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = ”2：全部成立” 
     * this.株価指数先物オプションソートキー.キー項目 = 銘柄コード 
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "2";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.株価指数先物オプションソートキーが 
     * 　@　@　@　@nullの値であれば例外をスローする。  
     * date:
     * 株価指数先物オプションソートキーチェック = null
     * テスト確認内容:
     * BUSINESS_ERROR_00231
     */
    public void test_validate_0010()
    {
        String STR_METHOD_NAME = "test_validate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "1";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.株価指数先物オプションソートキーの要素数が 
     * ０であれば例外をスローする。
     * date:
     * 株価指数先物オプションソートキーチェック.length = 0
     * テスト確認内容:
     * BUSINESS_ERROR_00232
     */
    public void test_validate_0011()
    {
        String STR_METHOD_NAME = "test_validate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "1";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目がnullの値であれば例外をスローする。
     * date:
     * 株価指数先物オプションソートキーチェック.キー項目 = null
     * テスト確認内容:
     * BUSINESS_ERROR_00085
     */
    public void test_validate_0012()
    {
        String STR_METHOD_NAME = "test_validate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();

        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "1";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目に以下の項目名以外の値が
     * 銘柄コード 
     * 取引市場
     * 取引区分
     * 注文時間 
     * 発注日
     * 注文有効期限 
     * date:
     * 株価指数先物オプションソートキーチェック.キー項目 = "注文"
     * テスト確認内容:
     * BUSINESS_ERROR_00086
     */
    public void test_validate_0013()
    {
        String STR_METHOD_NAME = "test_validate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();

        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "注文";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "1";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.昇順／降順がnullの値であれば例外をスローする。 
     * date:
     * 株価指数先物オプションソートキー.昇順／降順 = null
     * テスト確認内容:
     * BUSINESS_ERROR_00087
     */
    public void test_validate_0014()
    {
        String STR_METHOD_NAME = "test_validate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();

        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "1";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.昇順／降順が以下の値以外の場合例外をスローする。 
     * ”A：昇順” 
     * ”D：降順” 
     * date:
     * 株価指数先物オプションソートキー.昇順／降順 = "b"
     * テスト確認内容:
     * BUSINESS_ERROR_00088
     */
    public void test_validate_0015()
    {
        String STR_METHOD_NAME = "test_validate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();

        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "b";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "1";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目 = 銘柄コード  pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = "0：未約定" 
     * this.株価指数先物オプションソートキー.キー項目 = 銘柄コード 
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0016()
    {
        String STR_METHOD_NAME = "test_validate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目 = 取引市場   pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = "0：未約定" 
     * this.株価指数先物オプションソートキー.キー項目 = 取引市場 
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0017()
    {
        String STR_METHOD_NAME = "test_validate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "marketCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目 = 取引区分  pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = "0：未約定" 
     * this.株価指数先物オプションソートキー.キー項目 = 取引区分
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0018()
    {
        String STR_METHOD_NAME = "test_validate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "tradingType";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目 = 注文時間  pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = "0：未約定" 
     * 株価指数先物オプションソートキー.キー項目 = 注文時間
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0019()
    {
        String STR_METHOD_NAME = "test_validate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "orderDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目 = 発注日   pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = "0：未約定" 
     * 株価指数先物オプションソートキー.キー項目 = 発注日
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0020()
    {
        String STR_METHOD_NAME = "test_validate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "orderBizDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目 = 注文有効期限    pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = "0：未約定" 
     * 株価指数先物オプションソートキー.キー項目 = 注文有効期限
     * 株価指数先物オプションソートキー.昇順／降順 = “A：昇順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0021()
    {
        String STR_METHOD_NAME = "test_validate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数先物オプションソートキー.キー項目 = 注文有効期限    pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = "0：未約定" 
     * 株価指数先物オプションソートキー.キー項目 = 注文有効期限
     * 株価指数先物オプションソートキー.昇順／降順 = “D：降順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0022()
    {
        String STR_METHOD_NAME = "test_validate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.要求ページ番号がnullの値であれば例外をスローする。
     * date:
     * this.要求ページ番号 = null
     * テスト確認内容:
     * BUSINESS_ERROR_00089
     */
    public void test_validate_0023()
    {
        String STR_METHOD_NAME = "test_validate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.要求ページ番号が数字以外の値であれば例外をスローする。
     * date:
     * this.要求ページ番号 = avc
     * テスト確認内容:
     * BUSINESS_ERROR_00090
     */
    public void test_validate_0024()
    {
        String STR_METHOD_NAME = "test_validate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "avc";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.要求ページ番号 = 1  
     * this.ページ内表示行数 = 1  pass
     * date:
     * this.照会区分 = "0：注文約定照会"
     * this.約定状態区分 = "0：未約定" 
     * 株価指数先物オプションソートキー.キー項目 = 注文有効期限
     * 株価指数先物オプションソートキー.昇順／降順 = “D：降順”
     * this.要求ページ番号 = “1”
     * this.ページ内表示行数 = “1”
     * 銘柄コード  = null
     * テスト確認内容:
     * pass
     */
    public void test_validate_0025()
    {
        String STR_METHOD_NAME = "test_validate_0025()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "1";
        //銘柄コード
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.ページ内表示行数がnullの値であれば例外をスローする。
     * date:
     * this.ページ内表示行数 = null
     * テスト確認内容:
     * BUSINESS_ERROR_00091
     */
    public void test_validate_0026()
    {
        String STR_METHOD_NAME = "test_validate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数 
        l_request.pageSize = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * this.ページ内表示行数が数字以外の値であれば例外をスローする。
     * date:
     * this.ページ内表示行数 =abcx
     * テスト確認内容:
     * BUSINESS_ERROR_00092
     */
    public void test_validate_0027()
    {
        String STR_METHOD_NAME = "test_validate_0027()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //照会区分
        l_request.referenceType = "0";
        //約定状態区分
        l_request.execType = "0";
        //株価指数先物オプションソートキー
        l_request.futOpSortKeys = futOpSortKeys;
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数 =abcx
        l_request.pageSize = "abcx";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // 発注条件区分チェック
    //　@this.発注条件区分≠nullかつ、
    //　@this.発注条件区分が以下の値以外の場合例外をスローする。
    //　@　@　@・”0：指定なし”
    //　@　@　@・”1：逆指値”
    //　@　@　@・”2：W指値”
    //this.発注条件区分≠null ==3 異常
    public void testValidateCase1()
    {
        String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "3";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00212, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.発注条件区分≠null ==0 正常結束
    public void testValidateCase2()
    {
        String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "0";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.発注条件区分≠null ==1 正常結束
    public void testValidateCase3()
    {
        String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "1";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.発注条件区分≠null ==2 正常結束
    public void testValidateCase4()
    {
        String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "2";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.発注条件区分==null 正常結束
    public void testValidateCase5()
    {
        String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = null;
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
}
@
