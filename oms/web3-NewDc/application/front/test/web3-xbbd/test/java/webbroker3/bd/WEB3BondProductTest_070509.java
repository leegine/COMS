head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondProductTest_070509.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3BondProductTest_070509)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/08 武波(中訊) 新規作成
Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
*/
package webbroker3.bd;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3BondProductTest_070509 extends TestBaseForMock
{

    public WEB3BondProductTest_070509(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondProductTest_070509.class);
    
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    /*
//     * 注文付加情報テーブル.注文単位ID　@== 1001<br>
//     *注文付加情報テーブル.銘柄タイプ　@== 1<br>
//     *数据庫中不存在改条数据<br>
//     *ISINコードを返す。<br>
//     */
//    public  void test_getIsinCode_C0001()
//    {
//        final String STR_METHOD_NAME = "test_getIsinCode_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//
//            TestDBUtility.deleteAll(BondProductParams.TYPE);
//            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//            l_bondProductParams.setProductId(123L);
//            l_bondProductParams.setIsinCode("123");
//            TestDBUtility.insertWithDel(l_bondProductParams);
//
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(123l);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
//            assertEquals("123",l_bondProduct.getIsinCode());
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
