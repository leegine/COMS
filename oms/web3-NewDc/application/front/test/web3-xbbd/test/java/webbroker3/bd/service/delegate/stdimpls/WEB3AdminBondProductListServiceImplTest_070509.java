head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondProductListServiceImplTest_070509.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AdminBondProductListServiceImplTest_070509)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 武波(中訊) 新規作成
Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3AdminBondQueryContainer;
import webbroker3.bd.message.WEB3AdminBondProductConditionUnit;
import webbroker3.bd.message.WEB3AdminBondProductListConditionInfo;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3AdminBondProductListServiceImplTest_070509 extends TestBaseForMock
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductListServiceImplTest_070509.class);

    public WEB3AdminBondProductListServiceImplTest_070509(String name)
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
//     * 引数のHOST銘柄名1==nullの場合：
//     * 引数の利率==nullの場合：
//     * 引数のISINコード==nullの場合：
//     */
//    public  void test_createQueryContainer_C0001()
//    {
//        final String STR_METHOD_NAME = "test_createQueryContainer_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminBondProductListServiceImpl l_adminBondProductListServiceImpl = new WEB3AdminBondProductListServiceImpl();
//            WEB3AdminBondProductListConditionInfo l_bondProductListConditionInfo = new WEB3AdminBondProductListConditionInfo();
//            l_bondProductListConditionInfo.bondType = "1";
//            l_bondProductListConditionInfo.hostProductName1 = null;
//            l_bondProductListConditionInfo.coupon = null;
//            l_bondProductListConditionInfo.isinCode = null;
//
//            WEB3AdminBondQueryContainer l_adminBondQueryContainer = 
//                l_adminBondProductListServiceImpl.createQueryContainer(l_bondProductListConditionInfo);
//            assertEquals(" and bond_type = ? ", l_adminBondQueryContainer.getQueryString());
//            assertEquals("1",l_adminBondQueryContainer.getQueryData()[0]);
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
//     * 引数のHOST銘柄名1==1lの場合：
//     * 引数の利率==2の場合：
//     * 引数のISINコード==3の場合：
//     */
//    public  void test_createQueryContainer_C0002()
//    {
//        final String STR_METHOD_NAME = "test_createQueryContainer_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminBondProductListServiceImpl l_adminBondProductListServiceImpl = new WEB3AdminBondProductListServiceImpl();
//            WEB3AdminBondProductListConditionInfo l_bondProductListConditionInfo = new WEB3AdminBondProductListConditionInfo();
//            l_bondProductListConditionInfo.hostProductName1 = "1";
//            l_bondProductListConditionInfo.coupon = "2";
//            l_bondProductListConditionInfo.isinCode = "3";
//
//            WEB3AdminBondQueryContainer l_adminBondQueryContainer = 
//                l_adminBondProductListServiceImpl.createQueryContainer(l_bondProductListConditionInfo);
//            assertEquals("%1%",l_adminBondQueryContainer.getQueryData()[0]);
//            assertEquals("2",l_adminBondQueryContainer.getQueryData()[1]);
//            assertEquals("3",l_adminBondQueryContainer.getQueryData()[2]);
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
//     * 債券銘柄リスト == null
//     * 債券銘柄リスト .size == 1
//     * 債券銘柄リスト .size == 2
//     */
//    public  void test_createBondProductReferenceInfoList_C0003()
//    {
//        final String STR_METHOD_NAME = "test_createBondProductReferenceInfoList_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            List l_lisBondProductList = new ArrayList();
//            WEB3AdminBondProductListServiceImpl l_adminBondProductListServiceImpl = new WEB3AdminBondProductListServiceImpl();
//            WEB3AdminBondProductConditionUnit[] l_adminBondProductConditionUnits = 
//                l_adminBondProductListServiceImpl.createBondProductReferenceInfoList(null);
//
//            assertEquals(0,l_adminBondProductConditionUnits.length);
//
//            TestDBUtility.deleteAll(BondProductParams.TYPE);
//            BondProductParams l_bondProductParams1 = TestDBUtility.getBondProductRow();
//            l_bondProductParams1.setIsinCode("111");
//            l_lisBondProductList.add(l_bondProductParams1);
//
//            l_adminBondProductConditionUnits = 
//                l_adminBondProductListServiceImpl.createBondProductReferenceInfoList(l_lisBondProductList);
//            assertEquals("111",l_adminBondProductConditionUnits[0].isinCode);
//
//            BondProductParams l_bondProductParams2 = TestDBUtility.getBondProductRow();
//            l_bondProductParams2.setIsinCode("222");
//            l_lisBondProductList.add(l_bondProductParams2);
//            
//            l_adminBondProductConditionUnits = 
//                l_adminBondProductListServiceImpl.createBondProductReferenceInfoList(l_lisBondProductList);
//            assertEquals("111",l_adminBondProductConditionUnits[0].isinCode);
//            assertEquals("222",l_adminBondProductConditionUnits[1].isinCode);
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
