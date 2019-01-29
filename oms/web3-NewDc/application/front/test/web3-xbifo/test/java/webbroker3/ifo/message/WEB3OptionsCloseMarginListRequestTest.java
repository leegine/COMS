head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginListRequestTest.java;


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
Revesion History : 2008/07/04 劉剣(中訊) 新規作成  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginListRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsCloseMarginListRequestTest.class);

    private WEB3OptionsCloseMarginListRequest l_optionsCloseMarginListRequest = null;

    public WEB3OptionsCloseMarginListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_optionsCloseMarginListRequest = new WEB3OptionsCloseMarginListRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * １）　@ソートキーチェック<BR>
     * 　@１－１）this.株価指数先物オプションソートキーが<BR>
     * 　@　@　@　@nullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = null;
            l_optionsCloseMarginListRequest.validate();
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
     * 　@１－２）this.株価指数先物オプションソートキーの要素数が<BR>
     * 　@　@　@　@０であれば例外をスローする。<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[0];
            l_optionsCloseMarginListRequest.validate();
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
     * 　@１－３）this.株価指数先物オプションソートキーの要素数分<BR>
     * 　@　@　@　@繰り返してチェックを行う。<BR>
     * 　@　@１－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = null;
            l_optionsCloseMarginListRequest.validate();
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
     * 　@　@１－３－２）ソートキー.キー項目に以下の項目名以外の値が<BR>
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@　@・銘柄コード<BR>
     * 　@　@　@・建区分<BR>
     * 　@　@　@・損益<BR>
     * 　@　@　@・損益(諸経費込)<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "aaaa";
            l_optionsCloseMarginListRequest.validate();
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
     * 　@　@１－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = null;
            l_optionsCloseMarginListRequest.validate();
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
     * 　@　@１－３－４）ソートキー.昇順／降順以下の値以外の場合例外をスローする。<BR>
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "B";
            l_optionsCloseMarginListRequest.validate();
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
     * ２）　@要求ページ番号チェック<BR>
     * 　@２－１）this.要求ページ番号がnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = null;
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@２－２）this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "abc";
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ３）　@ページ内表示行数チェック<BR>
     * 　@３－１）this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = null;
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@３－２）this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     */
    public void testValidate_C00010()
    {
        final String STR_METHOD_NAME = "testValidate_C00010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = "abc";
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ４）　@銘柄設定チェック<BR>
     *   ４－１）以下の全てのリクエスト項目を設定している場合、例外をスローする。<BR>
     *        　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)<BR>
     *      ・銘柄コード<BR>
     *      ・取引市場<BR>
     *      ・指数種別<BR>
     *      ・限月<BR>
     *      ・オプション商品区分<BR>
     *      ・行使価格<BR>
     */
    public void testValidate_C00011()
    {
        final String STR_METHOD_NAME = "testValidate_C00011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = "02";
            l_optionsCloseMarginListRequest.opProductCode = "1001";
            l_optionsCloseMarginListRequest.marketCode = "1";
            l_optionsCloseMarginListRequest.targetProductCode = "0005";
            l_optionsCloseMarginListRequest.delivaryMonth = "200807";
            l_optionsCloseMarginListRequest.opProductType = "P";
            l_optionsCloseMarginListRequest.strikePrice = "2000";
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@４－２）銘柄特定項目による銘柄指定の場合<BR>
     *      取引市場、指数種別、限月、オプション商品区分、行使価格の全てが設定されていなければ、例外をスローする。<BR>
     */
    public void testValidate_C00012()
    {
        final String STR_METHOD_NAME = "testValidate_C00012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = "02";
            l_optionsCloseMarginListRequest.marketCode = null;
            l_optionsCloseMarginListRequest.targetProductCode = null;
            l_optionsCloseMarginListRequest.delivaryMonth = null;
            l_optionsCloseMarginListRequest.opProductType = null;
            l_optionsCloseMarginListRequest.strikePrice = null;
            l_optionsCloseMarginListRequest.validate();
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@４－２）銘柄特定項目による銘柄指定の場合<BR>
     *      取引市場、指数種別、限月、オプション商品区分、行使価格の全てが設定されていなければ、例外をスローする。<BR>
     */
    public void testValidate_C00013()
    {
        final String STR_METHOD_NAME = "testValidate_C00013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = "02";
            l_optionsCloseMarginListRequest.marketCode = null;
            l_optionsCloseMarginListRequest.targetProductCode = "0005";
            l_optionsCloseMarginListRequest.delivaryMonth = "200807";
            l_optionsCloseMarginListRequest.opProductType = "P";
            l_optionsCloseMarginListRequest.strikePrice = "2000";
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
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
