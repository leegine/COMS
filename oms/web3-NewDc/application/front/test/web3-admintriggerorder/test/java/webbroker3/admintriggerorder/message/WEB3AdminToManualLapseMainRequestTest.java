head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToManualLapseMainRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminToManualLapseMainRequestTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToManualLapseMainRequestTest.class); // ///////////

    WEB3AdminToManualLapseMainRequest l_request = null; // ///////////////

    public WEB3AdminToManualLapseMainRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true); // //////////
        l_request = new WEB3AdminToManualLapseMainRequest(); // ////////////////
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // １） 証券会社コードチェック
    // １－１） this.証券会社コード == nullの場合、
    // 「証券会社コードが未指定です。」の例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    // ２） スレッドNoチェック
    // ２－１） this.スレッドNo == nullの場合、
    // 「スレッド番号の指定なし。」の例外をスローする。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01974, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    // ３） From口座IDチェック
    // ３－１） this.From口座ID == nullの場合、
    // 「From口座IDが未入力」の例外をスローする。
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02421, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    // ４） To口座IDチェック
    // ４－１） this.To口座ID == nullの場合、
    // 「To口座ID（至）が未入力」の例外をスローする。
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = new Long(2);
            l_request.rangeTo = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02422, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    // ５） 失効対象注文条件のチェック
    // this.失効対象注文条件 == nullの場合、
    // 「失効対象注文条件が未入力」の例外をスローする。
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = new Long(2);
            l_request.rangeTo = new Long(3);
            l_request.lapseTargetOrderCondition = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02420, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }    
    // ６） 部店コード == nullの場合、
    // 「部店コードがnull」の例外をスローする。
    // 部店コードｖａｌｉｄａｔｅ條件，抛出異常
    // BUSINESS_ERROR_02174
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = new Long(2);
            l_request.rangeTo = new Long(3);
            l_request.lapseTargetOrderCondition = new WEB3AdminToLapseTargetOrderCondition();
            l_request.lapseTargetOrderCondition.branchCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }



    // ７） this.失効対象注文条件.validate()をコールする。
    // ７－１）注文IDチェック
    // 注文ID != nullの場合、以下のチェックを行う。
    // ７－２）部店コード == nullの場合、
    // ・部店コード != 数字
    // ・部店コード.length != 3
    // ７－３） 条件注文種別一覧チェック
    // ７－４） 商品区分一覧チェック
    // ７－５） 商品区分一覧 == nullの場合、「商品区分一覧が未指定です。
    // ７－６） 銘柄コードチェック
    // 銘柄コード != nullの場合、以下のチェックを行う。
    // ７－７） 顧客コードチェック
    // 顧客コード != nullの場合、以下のチェックを行う。
    // normalcase
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = new Long(2);
            l_request.rangeTo = new Long(3);
            l_request.lapseTargetOrderCondition = new WEB3AdminToLapseTargetOrderCondition();
            l_request.lapseTargetOrderCondition.id = "11111";
            l_request.lapseTargetOrderCondition.branchCode = new String[]{"222"};
            l_request.lapseTargetOrderCondition.triggerOrderTypeList =new String[]{"1"};
            l_request.lapseTargetOrderCondition.productDivList = new String[]{"1"};
            l_request.lapseTargetOrderCondition.marketList = new String[]{"1"};
            l_request.lapseTargetOrderCondition.productCode = "11111";
            l_request.lapseTargetOrderCondition.accountCode = "111111";
            l_request.validate();
    }
    catch (Exception l_ex)
    {
        log.exiting(STR_METHOD_NAME);
        fail();
    }
    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
    log.exiting(STR_METHOD_NAME);
}
    
    
}
@
