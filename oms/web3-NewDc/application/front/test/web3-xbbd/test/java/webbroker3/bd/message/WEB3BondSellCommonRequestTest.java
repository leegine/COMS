head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondSellCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3BondSellCommonRequestTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 武波(中訊) 新規作成
Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3BondSellCommonRequestTest extends TestBaseForMock
{

    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondSellCommonRequestTest.class);

    public WEB3BondSellCommonRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == null<br>
//     */
//    public  void test_validate_C0001()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = null;
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01919,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "a"<br>
//     */
//    public  void test_validate_C0002()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "a";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02641,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "-1"<br>
//     */
//    public  void test_validate_C0003()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "-1";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02636,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "1111111"<br>
//     *this.決済区分が以下定義値以外<br>
//     *this.settleDiv == "0"<br>
//     */
//    public  void test_validate_C0004()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "0";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02112,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "1111111"<br>
//     *this.決済区分が以下定義値以外<br>
//     *this.settleDiv == "1"<br>
//     *指値区分 ≠ nullの場合
//     *指値区分が以下の値でない場合
//     */
//    public  void test_validate_C0005()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "0";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02763,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "1111111"<br>
//     *this.決済区分が以下定義値以外<br>
//     *this.settleDiv == "1"<br>
//     *指値区分 ≠ nullの場合<br>
//     *指値区分が以下の値 == 指値<br>
//     *売却単価 == null<br>
//     */
//    public  void test_validate_C0006()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = null;
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02770,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "1111111"<br>
//     *this.決済区分が以下定義値以外<br>
//     *this.settleDiv == "1"<br>
//     *指値区分 ≠ nullの場合<br>
//     *指値区分が以下の値 == 指値<br>
//     *売却単価 == 1.11<br>
//     */
//    public  void test_validate_C0007()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0007()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = "11111.11";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02771,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "1111111"<br>
//     *this.決済区分が以下定義値以外<br>
//     *this.settleDiv == "1"<br>
//     *指値区分 ≠ nullの場合<br>
//     *指値区分が以下の値 == 指値<br>
//     *売却単価 == 11.11a<br>
//     */
//    public  void test_validate_C0008()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0008()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = "11.11a";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02772,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "1111111"<br>
//     *this.決済区分が以下定義値以外<br>
//     *this.settleDiv == "1"<br>
//     *指値区分 ≠ nullの場合<br>
//     *指値区分が以下の値 == 指値<br>
//     *売却単価 == -11111.11<br>
//     */
//    public  void test_validate_C0009()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0009()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = "-11.11";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02773,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondSellCommonRequest<br>
//     * 債券売却共通リクエスト<br>
//     *this.ＩＤ == 1001<br>
//     *this.額面金額が整数でない場合<br>
//     *this.額面金額が整数 == "1111111"<br>
//     *this.決済区分が以下定義値以外<br>
//     *this.settleDiv == "1"<br>
//     *指値区分 ≠ nullの場合<br>
//     *指値区分が以下の値 == 指値<br>
//     *売却単価 == 11111.11<br>
//     *推奨フラグ == 1の場合<br>
//     *為替リスクフラグ == 1の場合<br>
//     *備考 =< 500の場合<br>
//     */
//    public  void test_validate_C0010()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0010()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = "11.11";
//            l_bondSellCommonRequest.validate();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    public void testTest()
    {
        
    }
}
@
