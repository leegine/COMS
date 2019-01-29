head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityMarginExecuteReferenceRequestTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17　@関博(中訊) 新規作成
Revesion History : 2007/12/30  于瀟(中訊) 仕様変更モデル1232
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 株式・信用注文約定照会リクエストクラスのテスト<BR>
 * @@author 関博(中訊)
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceRequestTest.class);

    public WEB3EquityMarginExecuteReferenceRequestTest(String arg0)
    {
        super(arg0);
    }
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    WEB3EquityMarginExecuteReferenceRequest l_request = null;
    
    //１）照会区分チェック
    //１－１）
    //    this.照会区分 ＝ null の場合、「照会区分がnull」の例外をスローする。
    //    class: WEB3BusinessLayerException
    //    tag:   BUSINESS_ERROR_00081
    public void testValidate1()
    {
        final String STR_METHOD_NAME = "testValidate1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = new String();
        l_request.referenceType = null;        

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00081,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //１－２）
    //this.照会区分が下記の値以外が設定されている場合、「照会区分が未定義の値」の例外をスローする。
    //・”注文約定照会”
    //・”訂正取消一覧”
    //class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00082
    public void testValidate2()
    {
        final String STR_METHOD_NAME = "testValidate2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = new String();
        l_request.referenceType = "2";        

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //２）商品区分チェック
    //２－１）
    //    this.商品区分 ＝ null の場合、「商品区分がnull」の例外をスローする。
    // 　@ class: WEB3BusinessLayerException
    // 　@ tag:   BUSINESS_ERROR_02182
    public void testValidate3()
    {
        final String STR_METHOD_NAME = "testValidate3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = new String();
        l_request.productDiv = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02182,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //２－２）
    //this.商品区分が下記の値以外が設定されている場合、「商品区分が未定義の値」の例外をスローする。
    //    ・”現物株式、信用取引 すべて”
    //    ・”現物株式”
    //    ・”信用取引”
    //     class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_01068
    public void testValidate4()
    {
        final String STR_METHOD_NAME = "testValidate4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = new String();
        l_request.productDiv = "3";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01068,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //３）約定状態区分チェック
    //this.約定状態区分 ≠ null and this.約定状態区分が下記の値以外が設定されている場合、
    //「約定状態区分が未定義の値」の例外をスローする。
    //    ・”未約定”
    //    ・”一部成立”
    //    ・”全部成立”
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00626
    public void testValidate5()
    {
        final String STR_METHOD_NAME = "testValidate5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = new String();
        l_request.execType = "3";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //３）約定状態区分チェック
    //this.約定状態区分 ≠ null and this.約定状態区分が下記の値以外が設定されている場合、
    //「約定状態区分が未定義の値」の例外をスローする。
    //    ・”未約定”
    //    ・”一部成立”
    //    ・”全部成立”
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00626
    public void testValidate6()
    {
        final String STR_METHOD_NAME = "testValidate6()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = new String();
        l_request.execType = "4";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //３）約定状態区分チェック
    //this.約定状態区分 ≠ null and this.約定状態区分が下記の値以外が設定されている場合、
    //「約定状態区分が未定義の値」の例外をスローする。
    //    ・”未約定”
    //    ・”一部成立”
    //    ・”全部成立”
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00626
    public void testValidate7()
    {
        final String STR_METHOD_NAME = "testValidate7()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = new String();
        l_request.execType = "5";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４）ソートキーチェック
    //４－１）
    //    this.ソートキー ＝ null の場合、「ソートキーがnull」の例外をスローする。
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00231
    public void testValidate8()
    {
        final String STR_METHOD_NAME = "testValidate8()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－２）
    //this.ソートキー.要素数 ＝ 0 の場合、「ソートキー.要素数が0」の例外をスローする。
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00232
    public void testValidate9()
    {
        final String STR_METHOD_NAME = "testValidate9()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[0];

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate10()
    {
        final String STR_METHOD_NAME = "testValidate10()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "openDate";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate11()
    {
        final String STR_METHOD_NAME = "testValidate11()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "appraisalProfitLoss";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate12()
    {
        final String STR_METHOD_NAME = "testValidate12()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "contractType";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate13()
    {
        final String STR_METHOD_NAME = "testValidate13()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "closeDate";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate14()
    {
        final String STR_METHOD_NAME = "testValidate14()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "dealingType";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate15()
    {
        final String STR_METHOD_NAME = "testValidate15()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "orderStartDatetime";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate16()
    {
        final String STR_METHOD_NAME = "testValidate16()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "orderEndDatetime";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate17()
    {
        final String STR_METHOD_NAME = "testValidate17()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "estimatedAssetBalanceQuantity";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate18()
    {
        final String STR_METHOD_NAME = "testValidate18()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "estimatedAppraisalProfitLossBalanceQuantity";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //４－３）
    //this.ソートキーの全要素に対して、下記のチェックを行う。
    //４－３－１）
    //ソートキー.validate()をコールする。
    public void testValidate19()
    {
        final String STR_METHOD_NAME = "testValidate19()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "contractPrice";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //５）要求ページ番号チェック
    //
    //５－１）
    //    this.要求ページ番号 ＝ null の場合、「要求ページ番号がnull」の例外をスローする。
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00089
    public void testValidate20()
    {
        final String STR_METHOD_NAME = "testValidate20()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //５－２）
    //this.要求ページ番号が数字以外の値の場合、「要求ページ番号が数字以外」の例外をスローする。
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00090
    public void testValidate21()
    {
        final String STR_METHOD_NAME = "testValidate21()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "A";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //５－３）
    //this.要求ページ番号 ≦ 0 の場合、「要求ページ番号が0以下」の例外をスローする。
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00616
    public void testValidate22()
    {
        final String STR_METHOD_NAME = "testValidate22()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "-1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //６）ページ内表示行数チェック
    //６－１）
    //    this.ページ内表示行数 ＝ null の場合、「ページ内表示行数がnull」の例外をスローする。
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00091
    public void testValidate23()
    {
        final String STR_METHOD_NAME = "testValidate23()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //６－２）
    //this.ページ内表示行数が数字以外の値の場合、「ページ内表示行数が数字以外」の例外をスローする。
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00092
    public void testValidate24()
    {
        final String STR_METHOD_NAME = "testValidate24()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "A";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //６－３）
    //this.ページ内表示行数 ≦ 0 の場合、「ページ内表示行数が0以下」の例外をスローする。
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00617
    public void testValidate25()
    {
        final String STR_METHOD_NAME = "testValidate25()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "-1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //７）市場コードチェック
    //this.市場コード ≠ null and 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。
    //    ・”東京”
    //    ・”大阪”
    //    ・”名古屋”
    //    ・”福岡”
    //    ・”札幌”
    //    ・”NNM”
    //    ・”JASDAQ”
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00608
    public void testValidate26()
    {
        final String STR_METHOD_NAME = "testValidate26()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //７）市場コードチェック
    //this.市場コード ≠ null and 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。
    //    ・”東京”
    //    ・”大阪”
    //    ・”名古屋”
    //    ・”福岡”
    //    ・”札幌”
    //    ・”NNM”
    //    ・”JASDAQ”
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00608
    public void testValidate27()
    {
        final String STR_METHOD_NAME = "testValidate27()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "4";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //７）市場コードチェック
    //this.市場コード ≠ null and 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。
    //    ・”東京”
    //    ・”大阪”
    //    ・”名古屋”
    //    ・”福岡”
    //    ・”札幌”
    //    ・”NNM”
    //    ・”JASDAQ”
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00608
    public void testValidate28()
    {
        final String STR_METHOD_NAME = "testValidate28()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "5";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //７）市場コードチェック
    //this.市場コード ≠ null and 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。
    //    ・”東京”
    //    ・”大阪”
    //    ・”名古屋”
    //    ・”福岡”
    //    ・”札幌”
    //    ・”NNM”
    //    ・”JASDAQ”
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00608
    public void testValidate29()
    {
        final String STR_METHOD_NAME = "testValidate29()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "N1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //７）市場コードチェック
    //this.市場コード ≠ null and 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。
    //    ・”東京”
    //    ・”大阪”
    //    ・”名古屋”
    //    ・”福岡”
    //    ・”札幌”
    //    ・”NNM”
    //    ・”JASDAQ”
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00608
    public void testValidate30()
    {
        final String STR_METHOD_NAME = "testValidate30()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "N2";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //７）市場コードチェック
    //this.市場コード ≠ null and 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。
    //    ・”東京”
    //    ・”大阪”
    //    ・”名古屋”
    //    ・”福岡”
    //    ・”札幌”
    //    ・”NNM”
    //    ・”JASDAQ”
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00608
    public void testValidate31()
    {
        final String STR_METHOD_NAME = "testValidate31()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "X1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //７）市場コードチェック
    //this.市場コード ≠ null and 下記の値以外の場合、「市場コードが未定義の値」の例外をスローする。
    //    ・”東京”
    //    ・”大阪”
    //    ・”名古屋”
    //    ・”福岡”
    //    ・”札幌”
    //    ・”NNM”
    //    ・”JASDAQ”
    //
    // 　@　@class: WEB3BusinessLayerException
    // 　@　@tag:   BUSINESS_ERROR_00608
    public void testValidate32()
    {
        final String STR_METHOD_NAME = "testValidate32()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "99";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 発注条件区分の値が存在しないコード値です
     * 
     * スロー:BUSINESS_ERROR_00212のメッセージ
     *
     */
    public void testValidate_C0033()
    {
        final String STR_METHOD_NAME = "testValidate_C0033()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
            
            
            WEB3EquityMarginSortKey[] sortKeys = new WEB3EquityMarginSortKey[1];
            sortKeys[0] = new WEB3EquityMarginSortKey();
            sortKeys[0].keyItem = "productCode";
            sortKeys[0].ascDesc = "A";
            this.l_request.sortKeys = sortKeys;
            // referenceType
            this.l_request.referenceType = "0";
            
            // productDiv
            this.l_request.productDiv = "1";
            //execType
            this.l_request.execType = "0";
            // pageIndex
            this.l_request.pageIndex = "1";
            this.l_request.pageSize ="1";
            this.l_request.marketCode = "1";
            
            // 発注条件区分
            this.l_request.orderCondType = "3";
            this.l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00212,l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 発注条件区分の値が存在しないコード値です
     * 
     * スロー:BUSINESS_ERROR_00212のメッセージ
     *
     */
    public void testValidate_C0034()
    {
        final String STR_METHOD_NAME = "testValidate_C0034()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
            
            
            WEB3EquityMarginSortKey[] sortKeys = new WEB3EquityMarginSortKey[1];
            sortKeys[0] = new WEB3EquityMarginSortKey();
            sortKeys[0].keyItem = "productCode";
            sortKeys[0].ascDesc = "A";
            this.l_request.sortKeys = sortKeys;
            // referenceType
            this.l_request.referenceType = "0";
            
            // productDiv
            this.l_request.productDiv = "1";
            //execType
            this.l_request.execType = "0";
            // pageIndex
            this.l_request.pageIndex = "1";
            this.l_request.pageSize ="1";
            this.l_request.marketCode = "1";
            
            // 発注条件区分
            this.l_request.orderCondType = "0";
            this.l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 對應仕様変更1232
     * 
     * 正常通過
     */
    public void testValidate_C0035()
    {
        final String STR_METHOD_NAME = "testValidate_C0035()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = "0";
            this.l_request.productDiv = "1";
            WEB3EquityMarginSortKey[] sortKeys = new WEB3EquityMarginSortKey[1];
            sortKeys[0] = new WEB3EquityMarginSortKey();
            sortKeys[0].keyItem = "productCode";
            sortKeys[0].ascDesc = "A";
            this.l_request.sortKeys = sortKeys;
            this.l_request.pageIndex = "1";
            this.l_request.pageSize ="1";
            this.l_request.marketCode = "11";
            this.l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 對應仕様変更1232
     * 
     * 抛出異常信息：BUSINESS_ERROR_00608
     */
    public void testValidate_C0036()
    {
        final String STR_METHOD_NAME = "testValidate_C0036()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = "0";
            this.l_request.productDiv = "1";
            WEB3EquityMarginSortKey[] sortKeys = new WEB3EquityMarginSortKey[1];
            sortKeys[0] = new WEB3EquityMarginSortKey();
            sortKeys[0].keyItem = "productCode";
            sortKeys[0].ascDesc = "A";
            this.l_request.sortKeys = sortKeys;
            this.l_request.pageIndex = "1";
            this.l_request.pageSize ="1";
            this.l_request.marketCode = "";
            this.l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
}
@
