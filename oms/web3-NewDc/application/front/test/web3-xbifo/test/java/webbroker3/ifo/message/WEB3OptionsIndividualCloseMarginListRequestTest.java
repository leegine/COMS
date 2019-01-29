head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsIndividualCloseMarginListRequestTest.java;


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
Revesion History : 2008/07/03 劉剣(中訊) 新規作成  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsIndividualCloseMarginListRequestTest extends TestBaseForMock
{
    /*
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsIndividualCloseMarginListRequestTest.class);

    private WEB3OptionsIndividualCloseMarginListRequest l_optionsIndividualCloseMarginListRequest = null;

    public WEB3OptionsIndividualCloseMarginListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_optionsIndividualCloseMarginListRequest = new WEB3OptionsIndividualCloseMarginListRequest();
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
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = null;
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
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
     *  　@１－２）this.株価指数先物オプションソートキーの要素数が <BR>
     *  　@　@　@　@０であれば例外をスローする。<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[0];
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *   １－３）this.株価指数先物オプションソートキーの要素数分<BR>
     * 　@　@　@　@繰り返してチェックを行う。 <BR>
     * 　@　@１－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = null;
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@　@１－３－２）ソートキー.キー項目に以下の項目名以外の値が<BR>
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@　@・建日<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "aaaa";
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = null;
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 　@　@１－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@・”A:昇順 D：降順”<BR>
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = "B";
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *   ２）　@ＩＤチェック<BR>
     * 　@２－１）this.ＩＤがnullの値であれば例外をスローする。<BR>
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsIndividualCloseMarginListRequest.id = null;
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *  　@２－２）this.ＩＤの要素数が０であれば例外をスローする。<BR>
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsIndividualCloseMarginListRequest.id = new String[0];
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00282, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case<BR>
     */
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            assertTrue(true);
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
