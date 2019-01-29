head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSecurityTransferForceUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : (WEB3AioSecurityTransferForceUnitServiceImplTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/16 âΩï∂ïq (íÜêu) êVãKçÏê¨
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.aio.data.HostMrgsecTransNotifyRow;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq
 */

public class WEB3AioSecurityTransferForceUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceUnitServiceImplTest.class);

    WEB3AioSecurityTransferForceUnitServiceImpl l_mpl =
        new WEB3AioSecurityTransferForceUnitServiceImpl();
    HostMrgsecTransNotifyParams l_params = new HostMrgsecTransNotifyParams();
    public WEB3AioSecurityTransferForceUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testSubmitOrder1()
    {
        final String STR_METHOD_NAME = "testSubmitOrder1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostMrgsecTransNotifyRow.TYPE);
            HostMrgsecTransNotifyParams l_hostMrgsecTransNotifyParams =
                TestDBUtility.getHostMrgsecTransNotifyRow();
            l_hostMrgsecTransNotifyParams.setCommodityDiv("2");
            l_hostMrgsecTransNotifyParams.setDelivType("1");
            TestDBUtility.insertWithDel(l_hostMrgsecTransNotifyParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInputUnit("1");
            l_mutualFundProductParams.setProductCode("1");
            l_mutualFundProductParams.setInstitutionCode("07");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setInstitutionCode("07");
            l_traderParams.setTraderCode("00001");
            l_traderParams.setBranchCode("6");
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("6");
            l_mainAccountParams.setAccountCode("6");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setInstitutionId(33);
            l_mainAccountParams1.setBranchCode("6");
            l_mainAccountParams1.setAccountCode("6");
            l_mainAccountParams1.setAccountId(333812512247L);
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512247L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512247L);
            l_subAccountParams1.setSubAccountId(33381251220302L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            
            l_mpl.submitOrder(l_hostMrgsecTransNotifyParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder2()
    {
        final String STR_METHOD_NAME = "testSubmitOrder2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostMrgsecTransNotifyRow.TYPE);
            HostMrgsecTransNotifyParams l_hostMrgsecTransNotifyParams =
                TestDBUtility.getHostMrgsecTransNotifyRow();
            l_hostMrgsecTransNotifyParams.setCommodityDiv("2");
            l_hostMrgsecTransNotifyParams.setDelivType("1");
            TestDBUtility.insertWithDel(l_hostMrgsecTransNotifyParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInputUnit("2");
            l_mutualFundProductParams.setProductCode("1");
            l_mutualFundProductParams.setInstitutionCode("07");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setInstitutionCode("07");
            l_traderParams.setTraderCode("00001");
            l_traderParams.setBranchCode("6");
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("6");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(333812512247L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512247L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512247L);
            l_subAccountParams1.setSubAccountId(33381251220302L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            
            l_mpl.submitOrder(l_hostMrgsecTransNotifyParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrder3()
    {
        final String STR_METHOD_NAME = "testSubmitOrder3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostMrgsecTransNotifyRow.TYPE);
            HostMrgsecTransNotifyParams l_hostMrgsecTransNotifyParams =
                TestDBUtility.getHostMrgsecTransNotifyRow();
            l_hostMrgsecTransNotifyParams.setCommodityDiv("3");
            l_hostMrgsecTransNotifyParams.setDelivType("1");
            l_hostMrgsecTransNotifyParams.setProductCode("123456");
            TestDBUtility.insertWithDel(l_hostMrgsecTransNotifyParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setInstitutionCode("07");
            l_traderParams.setTraderCode("00001");
            l_traderParams.setBranchCode("6");
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("6");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(333812512247L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512247L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512247L);
            l_subAccountParams1.setSubAccountId(33381251220302L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(BondProductRow.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("07");
            l_bondProductParams.setHostProductIssueCode("12345");
            l_bondProductParams.setHostProductCode("6");
            l_bondProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            
            l_mpl.submitOrder(l_hostMrgsecTransNotifyParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
