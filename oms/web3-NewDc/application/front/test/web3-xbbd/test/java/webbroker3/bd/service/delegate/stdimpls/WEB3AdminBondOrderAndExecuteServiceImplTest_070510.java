head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondOrderAndExecuteServiceImplTest_070510.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AdminBondOrderAndExecuteServiceImplTest_070510)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 ïêîg(íÜêu) êVãKçÏê¨
Revesion History : 2007/07/05  ÅyWEB3ÅzÅyCITIÉtÉçÉìÉgì±ì¸Åiç¬åîÅjÅzàƒåèéÊè¡ÅCíçù{ë„·˘
*/
package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.message.WEB3AdminBondExecInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecInputResponse;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author ïêîg(íÜêu)
 * @@version 1.0
 */
public class WEB3AdminBondOrderAndExecuteServiceImplTest_070510 extends TestBaseForMock
{

    public WEB3AdminBondOrderAndExecuteServiceImplTest_070510(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderAndExecuteServiceImplTest_070510.class);

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    /*
//     *ç¬åîñ¡ïø.îÉïtíPâø == Åh0Åh
//     */
//    public  void test_createQueryContainer_C0001()
//    {
//        final String STR_METHOD_NAME = "test_createQueryContainer_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminBondOrderAndExecuteServiceImpl l_adminBondOrderAndExecuteServiceImpl = new WEB3AdminBondOrderAndExecuteServiceImpl();
//            WEB3AdminBondExecInputRequest l_request = new WEB3AdminBondExecInputRequest();
//            l_request.productCode = "A01010763";
//
//            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
//            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
//            l_bondBranchConditionParams.setBranchId(33381);
//            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
//
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setInstitutionCode("0D");
//            l_branchParams.setBranchCode("123");
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
//            l_administratorParams.setInstitutionCode("0D");
//            l_administratorParams.setBranchCode("123");
//            TestDBUtility.insertWithDel(l_administratorParams);
//
//            TestDBUtility.deleteAll(InstitutionParams.TYPE);
//            InstitutionParams l_InstitutionParams =TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_InstitutionParams);
//
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator,"C1102",true,true);
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("2007/05/10", "yyyy/MM/dd"));
//
//            TestDBUtility.deleteAll(BondProductParams.TYPE);
//            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//            l_bondProductParams.setInstitutionCode("0D");
//            l_bondProductParams.setProductCode("A01010763");
//            l_bondProductParams.setProductIssueCode("0");
//            l_bondProductParams.setProductId(123456);
//            l_bondProductParams.setBuyPrice(0D);
//            TestDBUtility.insertWithDel(l_bondProductParams);
//
//            TestDBUtility.deleteAll(MarketParams.TYPE);
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(123456);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            WEB3AdminBondExecInputResponse l_response =
//                l_adminBondOrderAndExecuteServiceImpl.getExecuteInputScreen(l_request);
//            WEB3AdminBondOrderInfo l_bondOrderInfo = l_response.orderInfo;
//            assertNull(l_bondOrderInfo.price);
//            assertNull(l_bondOrderInfo.orderPriceDiv);
////            assertNull(l_bondOrderInfo.orderAddInfoList);
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
//    *ç¬åîñ¡ïø.îÉïtíPâø != Åh0Åh
//    */
//   public  void test_createQueryContainer_C0002()
//   {
//       final String STR_METHOD_NAME = "test_createQueryContainer_C0002()";
//       log.entering(TEST_START + STR_METHOD_NAME);
//       try
//       {
//           WEB3AdminBondOrderAndExecuteServiceImpl l_adminBondOrderAndExecuteServiceImpl = new WEB3AdminBondOrderAndExecuteServiceImpl();
//           WEB3AdminBondExecInputRequest l_request = new WEB3AdminBondExecInputRequest();
//           l_request.productCode = "A01010763";
//
//           TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
//           BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
//           l_bondBranchConditionParams.setBranchId(33381);
//           TestDBUtility.insertWithDel(l_bondBranchConditionParams);
//
//           TestDBUtility.deleteAll(BranchParams.TYPE);
//           BranchParams l_branchParams = TestDBUtility.getBranchRow();
//           l_branchParams.setInstitutionCode("0D");
//           l_branchParams.setBranchCode("123");
//           TestDBUtility.insertWithDel(l_branchParams);
//
//           TestDBUtility.deleteAll(AdministratorParams.TYPE);
//           AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
//           l_administratorParams.setInstitutionCode("1D");
//           l_administratorParams.setBranchCode("123");
//           TestDBUtility.insertWithDel(l_administratorParams);
//
//           TestDBUtility.deleteAll(InstitutionParams.TYPE);
//           InstitutionParams l_InstitutionParams =TestDBUtility.getInstitutionRow();
//           TestDBUtility.insertWithDel(l_InstitutionParams);
//
//           WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//           WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//           WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator,"C1102",true,true);
//           WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("2007/05/10", "yyyy/MM/dd"));
//
//           TestDBUtility.deleteAll(BondProductParams.TYPE);
//           BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//           l_bondProductParams.setInstitutionCode("0D");
//           l_bondProductParams.setProductCode("A01010763");
//           l_bondProductParams.setProductIssueCode("0");
//           l_bondProductParams.setProductId(123456);
//           l_bondProductParams.setBuyPrice(1D);
//           TestDBUtility.insertWithDel(l_bondProductParams);
//
//           TestDBUtility.deleteAll(MarketParams.TYPE);
//           MarketParams l_marketParams = TestDBUtility.getMarketRow();
//           l_marketParams.setInstitutionCode("0D");
//           TestDBUtility.insertWithDel(l_marketParams);
//           
//           TestDBUtility.deleteAll(ProductParams.TYPE);
//           ProductParams l_productParams = TestDBUtility.getProductRow();
//           l_productParams.setProductId(123456);
//           TestDBUtility.insertWithDel(l_productParams);
//
//           WEB3AdminBondExecInputResponse l_response =
//               l_adminBondOrderAndExecuteServiceImpl.getExecuteInputScreen(l_request);
//           WEB3AdminBondOrderInfo l_bondOrderInfo = l_response.orderInfo;
//           assertEquals("1",l_bondOrderInfo.price);
//           assertNull(l_bondOrderInfo.orderPriceDiv);
////           assertNull(l_bondOrderInfo.orderAddInfoList);
//       }
//       catch (Exception l_ex)
//       {
//           log.error(STR_METHOD_NAME, l_ex);
//           fail();
//       }
//       log.exiting(TEST_END + STR_METHOD_NAME);
//   }
    
    public void testTest()
    {
        
    }
}
@
