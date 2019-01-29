head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsContractReferenceRequestTest.java;


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

public class WEB3OptionsContractReferenceRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsContractReferenceRequestTest.class);

    private WEB3OptionsContractReferenceRequest l_optionsContractReferenceRequest = null;

    public WEB3OptionsContractReferenceRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_optionsContractReferenceRequest = new WEB3OptionsContractReferenceRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     *  １）　@決済状態区分のチェック<BR>
     *　@　@　@以下の状態以外が存在した場合、例外とする。<BR>
     *　@　@　@・null(指定なし) <BR>
     *　@　@　@・0(決済済)<BR>
     *      ・1(未決済)<BR>
     *      ・2(決済中)<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "3";
            l_optionsContractReferenceRequest.validate();
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
     *  ２）　@ソートキーのチェック<BR>
     * 　@　@ソートキーの配列の個数分、繰り返してチェックを行う。<BR>
     * 　@　@ソートキー.キー項目に以下の項目名以外が存在した場合、例外とする。<BR>
     * 　@　@　@　@・銘柄<BR>
     * 　@　@　@　@・建日<BR>
     * 　@　@　@　@・損益<BR>
     * 　@　@　@　@・建区分<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = null;
            l_optionsContractReferenceRequest.validate();
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
     *  ２）　@ソートキーのチェック<BR>
     * 　@　@ソートキーの配列の個数分、繰り返してチェックを行う。<BR>
     * 　@　@ソートキー.キー項目に以下の項目名以外が存在した場合、例外とする。<BR>
     * 　@　@　@　@・銘柄<BR>
     * 　@　@　@　@・建日<BR>
     * 　@　@　@　@・損益<BR>
     * 　@　@　@　@・建区分<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsContractReferenceRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsContractReferenceRequest.futOpSortKeys[0].keyItem = "aaaa";
            l_optionsContractReferenceRequest.validate();
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
     *   ３）　@要求ページ番号のチェック<BR>
     * リクエストデータ．要求ページ番号が未指定の場合、<BR>
     * リクエストデータ．要求ページ番号に「１」をセットする。<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsContractReferenceRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsContractReferenceRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsContractReferenceRequest.pageIndex = null;
            l_optionsContractReferenceRequest.pageSize = "8888";
            l_optionsContractReferenceRequest.validate();
            assertEquals(l_optionsContractReferenceRequest.pageIndex = "1", l_optionsContractReferenceRequest.pageIndex);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *   ４）　@ページ内表示行数のチェック<BR>
     * リクエストデータ．ページ内表示行数が０、または未指定の場合、<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsContractReferenceRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsContractReferenceRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsContractReferenceRequest.pageIndex = null;
            l_optionsContractReferenceRequest.pageSize = "0";
            l_optionsContractReferenceRequest.validate();
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
     *   ４）　@ページ内表示行数のチェック<BR>
     * リクエストデータ．ページ内表示行数が０、または未指定の場合、<BR>
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsContractReferenceRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsContractReferenceRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsContractReferenceRequest.pageIndex = null;
            l_optionsContractReferenceRequest.pageSize = "";
            l_optionsContractReferenceRequest.validate();
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
    
}
@
