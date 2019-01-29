head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondProductRegisterServiceImplTest_070509.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
 */
package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.message.WEB3AdminBondProductBasicInfo;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondProductRegisterServiceImplTest_070509 extends TestBaseForMock
{

    public WEB3AdminBondProductRegisterServiceImplTest_070509(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegisterServiceImplTest_070509.class);

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * ISINコード　@=　@債券銘柄.getISINコード<br>
     * 債券銘柄.getISINコード == null<br>
     */
    public  void testInputProductRegisterC0001()
    {
        final String STR_METHOD_NAME = "test_createQueryContainer_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminBondProductRegisterServiceImpl l_adminBondProductRegisterServiceImpl = new WEB3AdminBondProductRegisterServiceImpl();
            WEB3AdminBondProductRegistInputRequest l_request = new WEB3AdminBondProductRegistInputRequest();
            l_request.productCode = "A01010763";

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams =TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator,"C1101",false,true);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setProductCode("A01010763");
            l_bondProductParams.setProductIssueCode("0");
            l_bondProductParams.setProductId(123456);
            l_bondProductParams.setRecruitAcceptDiv("01");
            l_bondProductParams.setRecruitInvitationDiv("02");
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2009/07/28", "yyyy/MM/dd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3AdminBondProductRegistInputResponse l_response =
                l_adminBondProductRegisterServiceImpl.inputProductRegister(l_request);
            WEB3AdminBondProductBasicInfo l_productBasicInfo = l_response.basicInfo;
            assertEquals("01", l_response.updateInfo.recruitAcceptDiv);
            assertEquals("02", l_response.updateInfo.recruitInvitationForm);
            assertEquals(WEB3DateUtility.getDate("2009/07/28", "yyyy/MM/dd"), l_response.updateInfo.deliveryDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * ISINコード　@=　@債券銘柄.getISINコード<br>
     * 債券銘柄.getISINコード == 12345<br>
     */
    public  void testInputProductRegisterC0002()
    {
        final String STR_METHOD_NAME = "test_createQueryContainer_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminBondProductRegisterServiceImpl l_adminBondProductRegisterServiceImpl = new WEB3AdminBondProductRegisterServiceImpl();
            WEB3AdminBondProductRegistInputRequest l_request = new WEB3AdminBondProductRegistInputRequest();
            l_request.productCode = "A01010763";

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams =TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator,"C1101",false,true);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setIsinCode("12345");
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setProductCode("A01010763");
            l_bondProductParams.setProductIssueCode("0");
            l_bondProductParams.setProductId(123456);
            l_bondProductParams.setRecruitAcceptDiv("01");
            l_bondProductParams.setRecruitInvitationDiv("02");
            l_bondProductParams.setDeliveryDate(null);
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3AdminBondProductRegistInputResponse l_response =
                l_adminBondProductRegisterServiceImpl.inputProductRegister(l_request);
            WEB3AdminBondProductBasicInfo l_productBasicInfo = l_response.basicInfo;
            assertEquals("01", l_response.updateInfo.recruitAcceptDiv);
            assertEquals("02", l_response.updateInfo.recruitInvitationForm);
            assertNull(l_response.updateInfo.deliveryDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testTest()
    {
        
    }
}
@
