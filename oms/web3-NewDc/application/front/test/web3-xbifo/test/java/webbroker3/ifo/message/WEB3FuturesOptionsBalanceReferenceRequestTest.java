head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOptionsBalanceReferenceRequestTest.java;


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

public class WEB3FuturesOptionsBalanceReferenceRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOptionsBalanceReferenceRequestTest.class);

    private WEB3FuturesOptionsBalanceReferenceRequest l_futuresOptionsBalanceReferenceRequest = null;

    public WEB3FuturesOptionsBalanceReferenceRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_futuresOptionsBalanceReferenceRequest = new WEB3FuturesOptionsBalanceReferenceRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * １）　@先物／オプション区分のチェック<BR>
     * 　@１－１）nullの場合、例外とする。<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01736, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@１－２）以下の項目以外が存在した場合、例外とする。<BR>
     * 　@　@　@・1(先物)<BR>
     * 　@　@　@・2(オプション)<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "0";
            l_futuresOptionsBalanceReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01737, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ２）　@決済状態区分のチェック<BR>
     * 　@　@　@以下の状態以外が存在した場合、例外とする。<BR>
     * 　@　@　@・null(指定なし) <BR>
     *       ・1(未決済)<BR>
     *       ・2(決済中)<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = "0";
            l_futuresOptionsBalanceReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00233, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ３）　@株価指数先物オプションソートキーチェック <BR>
     * 　@３－１）this.株価指数先物オプションソートキーが <BR>
     * 　@　@　@　@nullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * 　@３－２）this.株価指数先物オプションソートキーの要素数が<BR> 
     * 　@　@　@　@０であれば例外をスローする。<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[0];
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * 　@３－３）this.株価指数先物オプションソートキーの要素数分<BR> 
     * 　@　@　@　@繰り返してチェックを行う。 <BR>
     * 　@　@３－３－１）株価指数先物オプションソートキー.キー項目がnullの値であれば例外をスローする。<BR> 
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * 　@　@３－３－２）株価指数先物オプションソートキー.キー項目に以下の項目名以外の値が<BR> 
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@　@・先物OP残高照会明細.銘柄コード<BR>
     * 　@　@　@・先物OP残高照会明細.建区分<BR>
     * 　@　@　@・先物OP残高照会明細.建日<BR>
     * 　@　@　@・先物OP残高照会明細.損益<BR>
     * 　@　@　@・先物OP残高照会明細.損益(諸経費込)<BR>
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "abc";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * 　@　@３－３－３）株価指数先物オプションソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * 　@　@３－３－４）株価指数先物オプションソートキー.昇順／降順が以下の値以外であれば例外をスローする。 <BR>
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順”<BR>
     */
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "B";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * ４）　@要求ページ番号のチェック<BR>
     * リクエストデータ．要求ページ番号が未指定の場合、<BR>
     * リクエストデータ．要求ページ番号に「１」をセットする。<BR>
     */
    public void testValidate_C00010()
    {
        final String STR_METHOD_NAME = "testValidate_C00010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.validate();
            assertEquals(l_futuresOptionsBalanceReferenceRequest.pageIndex = "1",l_futuresOptionsBalanceReferenceRequest.pageIndex);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ５）　@ページ内表示行数のチェック<BR>
     * リクエストデータ．ページ内表示行数が０、または未指定の場合、<BR>
     */
    public void testValidate_C00011()
    {
        final String STR_METHOD_NAME = "testValidate_C00011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * ５）　@ページ内表示行数のチェック<BR>
     * リクエストデータ．ページ内表示行数が０、または未指定の場合、<BR>
     */
    public void testValidate_C00012()
    {
        final String STR_METHOD_NAME = "testValidate_C00012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "0";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * ６－１）銘柄コードと銘柄特定項目がどちらも設定されている場合は例外をスローする。<BR>
     *      先物の場合(先物/オプション区分＝先物)<BR>
     */
    public void testValidate_C00013()
    {
        final String STR_METHOD_NAME = "testValidate_C00013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = "1";
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = "0005";
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = "200807";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     *  　@６－２）銘柄特定項目による銘柄指定の場合<BR>
     *            取引市場、指数種別、限月の全てが設定されていなければ、例外をスローする。<BR>
     *         先物の場合(先物/オプション区分＝先物)<BR>
     */
    public void testValidate_C00014()
    {
        final String STR_METHOD_NAME = "testValidate_C00014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = null;
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = null;
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     *  　@６－２）銘柄特定項目による銘柄指定の場合<BR>
     *            取引市場、指数種別、限月の全てが設定されていなければ、例外をスローする。<BR>
     *         先物の場合(先物/オプション区分＝先物)<BR>
     */
    public void testValidate_C00015()
    {
        final String STR_METHOD_NAME = "testValidate_C00015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = null;
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = "0005";
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = "200807";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * ６－１）銘柄コードと銘柄特定項目がどちらも設定されている場合は例外をスローする。<BR>
     *      オプションの場合(先物/オプション区分＝オプション)<BR>
     */
    public void testValidate_C00016()
    {
        final String STR_METHOD_NAME = "testValidate_C00016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "2";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = "1";
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = "0005";
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = "200807";
            l_futuresOptionsBalanceReferenceRequest.opProductType = "P";
            l_futuresOptionsBalanceReferenceRequest.strikePrice = "1234";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     *  　@６－２）銘柄特定項目による銘柄指定の場合<BR>
     *            取引市場、指数種別、限月の全てが設定されていなければ、例外をスローする。<BR>
     *         オプションの場合(先物/オプション区分＝オプション)<BR>
     */
    public void testValidate_C00017()
    {
        final String STR_METHOD_NAME = "testValidate_C00017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = null;
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = null;
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = null;
            l_futuresOptionsBalanceReferenceRequest.opProductType = null;
            l_futuresOptionsBalanceReferenceRequest.strikePrice = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     *  　@６－２）銘柄特定項目による銘柄指定の場合<BR>
     *            取引市場、指数種別、限月の全てが設定されていなければ、例外をスローする。<BR>
     *         オプションの場合(先物/オプション区分＝オプション)<BR>
     */
    public void testValidate_C00018()
    {
        final String STR_METHOD_NAME = "testValidate_C00018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = null;
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = "0005";
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = "200807";
            l_futuresOptionsBalanceReferenceRequest.opProductType = "P";
            l_futuresOptionsBalanceReferenceRequest.strikePrice = "1234";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
