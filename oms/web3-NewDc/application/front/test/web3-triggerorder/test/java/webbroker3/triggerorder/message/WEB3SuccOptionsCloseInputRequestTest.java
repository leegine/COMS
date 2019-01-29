head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsCloseInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3SuccOptionsCloseInputRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 楊夫志 (中訊) 新規作成  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsCloseInputRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsCloseInputRequestTest.class);

    private WEB3SuccOptionsCloseInputRequest succOptionsCloseInputRequest= null;

    public WEB3SuccOptionsCloseInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsCloseInputRequest = new WEB3SuccOptionsCloseInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //１）　@連続注文共通情報チェック
    // 　@１－１）　@this.連続注文共通情報==nullの場合、
    // 　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succOptionsCloseInputRequest.succCommonInfo = null;
            succOptionsCloseInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02251, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
    // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
    // 　@　@　@　@　@"OP返済（前提注文）"
    // 　@　@　@　@　@"OP返済（既存残）"
    // 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succOptionsCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succOptionsCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseInputRequest.succCommonInfo.succTradingType = "16";
            succOptionsCloseInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02252, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    // ２）　@this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"の場合のみ、
    // 　@　@　@super.validate()をコールする。
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succOptionsCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succOptionsCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseInputRequest.succCommonInfo.succTradingType = "18";
            succOptionsCloseInputRequest.id = null;
            succOptionsCloseInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case this.連続注文共通情報.連続注文取引区分=="OP返済（前提注文）"
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succOptionsCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succOptionsCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseInputRequest.succCommonInfo.succTradingType = "17";
            succOptionsCloseInputRequest.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succOptionsCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succOptionsCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseInputRequest.succCommonInfo.succTradingType = "18";
            //super.validate()をコールする。
            succOptionsCloseInputRequest.id = new String[]{"0001","0002"};
            succOptionsCloseInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[]
                          { new WEB3FuturesOptionsSortKey(), new WEB3FuturesOptionsSortKey()};
            int l_intObjectItemLength = succOptionsCloseInputRequest.futOpSortKeys.length;
            for (int i = 0; i < l_intObjectItemLength; i++)
            {
                succOptionsCloseInputRequest.futOpSortKeys[i].keyItem = "openDate";
                succOptionsCloseInputRequest.futOpSortKeys[i].ascDesc = "A";
            }
            succOptionsCloseInputRequest.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //１－２）　@this.連続注文共通情報.validate()をコールする。
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.連続注文共通情報!＝nullの場合
            succOptionsCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02258, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
