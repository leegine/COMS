head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3OptionsOrderHistoryRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/07/07 劉剣(中訊) 新規作成  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginInputRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsCloseMarginInputRequestTest.class);

    private WEB3OptionsCloseMarginInputRequest l_optionsCloseMarginInputRequest = null;

    public WEB3OptionsCloseMarginInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_optionsCloseMarginInputRequest = new WEB3OptionsCloseMarginInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = null;
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *２）　@ソートキーチェック<BR>
     * 　@２－１）this.ソートキーがnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = null;
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@２－２）this.ソートキーの要素数が０であれば例外をスローする。<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[0];
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@２－３）this.ソートキーの要素数分繰り返してチェックを行う。<BR>
     * 　@　@２－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = null;
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@２－３－２）ソートキー.キー項目に以下の項目名以外の値が<BR>
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@　@・建日<BR>
     * 　@　@　@・建単価<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = "abc";
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@　@２－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].ascDesc = null;
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@　@２－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].ascDesc = "B";
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *  normal case
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginInputRequest.validate();
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
