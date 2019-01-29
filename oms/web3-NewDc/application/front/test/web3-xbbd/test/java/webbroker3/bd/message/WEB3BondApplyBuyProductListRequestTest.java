head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondApplyBuyProductListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3BondApplyBuyProductListRequestTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 武波(中訊) 新規作成
Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
*/
package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondReferenceTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3BondApplyBuyProductListRequestTest extends TestBaseForMock
{

    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondApplyBuyProductListRequestTest.class);

    public WEB3BondApplyBuyProductListRequestTest(String name)
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
//     * WEB3BondApplyBuyProductListRequest<br>
//     * 債券応募/買付銘柄一覧リクエスト<br>
//     *債券応募/買付銘柄一覧検索条件.照会区分 == null<br>
//     */
//    public  void test_validate_C0001()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyProductListRequest l_bondApplyBuyProductListRequest = new WEB3BondApplyBuyProductListRequest();
//            l_bondApplyBuyProductListRequest.bondApplyBuyProductSearchCondition = new WEB3BondApplyBuyProductSearchConditionInfo();
//            l_bondApplyBuyProductListRequest.bondApplyBuyProductSearchCondition.referenceType = null;
//            l_bondApplyBuyProductListRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00081,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondApplyBuyProductListRequest<br>
//     * 債券応募/買付銘柄一覧リクエスト<br>
//     *債券応募/買付銘柄一覧検索条件.照会区分 == 0<br>
//     */
//    public  void test_validate_C0002()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyProductListRequest l_bondApplyBuyProductListRequest = new WEB3BondApplyBuyProductListRequest();
//            l_bondApplyBuyProductListRequest.bondApplyBuyProductSearchCondition = new WEB3BondApplyBuyProductSearchConditionInfo();
//            l_bondApplyBuyProductListRequest.bondApplyBuyProductSearchCondition.referenceType = WEB3BondReferenceTypeDef.EXECUTE_REFERENCE;
//            l_bondApplyBuyProductListRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondApplyBuyProductListRequest<br>
//     * 債券応募/買付銘柄一覧リクエスト<br>
//     *債券応募/買付銘柄一覧検索条件.照会区分 == 1<br>
//     *要求ページ番号 == "3"<br>
//     *ページ内表示行数 == "3"<br>
//     */
//    public  void test_validate_C0003()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyProductListRequest l_bondApplyBuyProductListRequest = new WEB3BondApplyBuyProductListRequest();
//            l_bondApplyBuyProductListRequest.bondApplyBuyProductSearchCondition = new WEB3BondApplyBuyProductSearchConditionInfo();
//            l_bondApplyBuyProductListRequest.bondApplyBuyProductSearchCondition.referenceType = WEB3BondReferenceTypeDef.RECRUIT_LIST;
//            l_bondApplyBuyProductListRequest.pageIndex = "3";
//            l_bondApplyBuyProductListRequest.pageSize = "3";
//            l_bondApplyBuyProductListRequest.validate();
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
