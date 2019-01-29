head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashTransferListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AioCashTransferListServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/08 何文敏 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.DepositInformParams;
import webbroker3.aio.data.DepositInformRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.message.WEB3AioCashTransUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransferListServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferListServiceImplTest.class);
    
    forTestWEB3AioCashTransferListServiceImpl l_mpl = new forTestWEB3AioCashTransferListServiceImpl();
    WEB3AioCashTransferListServiceImpl l_mpl2 = new WEB3AioCashTransferListServiceImpl();

    public WEB3AioCashTransferListServiceImplTest(String arg0)
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

    public void testGetRemark_Case001()
    {
        final String STR_METHOD_NAME = "testGetRemark_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderRequestNumber("101");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            //TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_aioOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_mainAccountParams.institution_id);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.branch_id);
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            
            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode(l_mainAccountParams.institution_code);
            l_gftTransferStatusParams.setBranchCode(l_mainAccountParams.branch_code);
            l_gftTransferStatusParams.setOrderRequestNumber(l_aioOrderUnitParams.getOrderRequestNumber());
            l_gftTransferStatusParams.setFxSystemCode("10");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
            
            //InstitutionPreferencesRow
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = this.getInstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(l_mainAccountParams.institution_id);
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FX_AIOLISTCOURSEDIV);
            l_institutionPreferencesParams.setValue(l_gftTransferStatusParams.fx_system_code);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
           
            
            String l_strGetRemark = l_mpl2.getRemark(l_aioOrderUnitParams);
            
            assertEquals("0", l_strGetRemark);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }

    //getGFT振替状況()の戻り値　@!= nullの場合、GFT振替状況Params.コース区分を返却する。
    public void testGetRemark_C0002()
    {
        final String STR_METHOD_NAME = "testGetRemark_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderRequestNumber("101");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_aioOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_mainAccountParams.institution_id);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.branch_id);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode(l_mainAccountParams.institution_code);
            l_gftTransferStatusParams.setBranchCode(l_mainAccountParams.branch_code);
            l_gftTransferStatusParams.setOrderRequestNumber(l_aioOrderUnitParams.getOrderRequestNumber());
            l_gftTransferStatusParams.setFxSystemCode("10");
            l_gftTransferStatusParams.setCourseDiv("3");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            //InstitutionPreferencesRow
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = this.getInstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(l_mainAccountParams.institution_id);
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FX_AIOLISTCOURSEDIV);
            l_institutionPreferencesParams.setValue(l_gftTransferStatusParams.fx_system_code);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            String l_strGetRemark = l_mpl2.getRemark(l_aioOrderUnitParams);

            assertEquals("3", l_strGetRemark);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }

    //getGFT振替状況()の戻り値がnullの場合は、nullを返却する。 
    public void testGetRemark_C0003()
    {
        final String STR_METHOD_NAME = "testGetRemark_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderRequestNumber("101");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_aioOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_mainAccountParams.institution_id);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.branch_id);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode(l_mainAccountParams.institution_code);
            l_gftTransferStatusParams.setBranchCode(l_mainAccountParams.branch_code);
            l_gftTransferStatusParams.setOrderRequestNumber("102");
            l_gftTransferStatusParams.setFxSystemCode("15");
            l_gftTransferStatusParams.setCourseDiv("3");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            //InstitutionPreferencesRow
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = this.getInstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(l_mainAccountParams.institution_id);
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FX_AIOLISTCOURSEDIV);
            l_institutionPreferencesParams.setValue(l_gftTransferStatusParams.fx_system_code);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            String l_strGetRemark = l_mpl2.getRemark(l_aioOrderUnitParams);

            assertNull(l_strGetRemark);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }
    public void testGetCashTransferFromOrderUnit()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromOrderUnit()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512203L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_OUT);
            l_aioOrderUnitParams.setOrderRootDiv("9");
            l_aioOrderUnitParams.setPaymentApplicationDiv("33");
            l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setCurrencyCode("007");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromOrderUnit(l_subAccount);
            WEB3AioCashTransUnit l_unit = (WEB3AioCashTransUnit)l_lis.get(0);
            assertEquals("007", l_unit.currencyCode);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetCashTransferFromOrderUnit_Case001()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromOrderUnit_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512203L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_OUT);
            l_aioOrderUnitParams.setOrderRootDiv("9");
            l_aioOrderUnitParams.setPaymentApplicationDiv("33");
            l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setCurrencyCode("007");
            l_aioOrderUnitParams.setDescription("cashout_gross");
            l_aioOrderUnitParams.setRemarkCode("86");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromOrderUnit(l_subAccount);
            assertEquals(0, l_lis.size());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCashTransferFromOrderUnit_Case002()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromOrderUnit_Case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512203L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_OUT);
            l_aioOrderUnitParams.setOrderRootDiv("9");
            l_aioOrderUnitParams.setPaymentApplicationDiv("33");
            l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setCurrencyCode("007");
            l_aioOrderUnitParams.setDescription("cashout_gross1");
            l_aioOrderUnitParams.setRemarkCode("71");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromOrderUnit(l_subAccount);
            assertEquals(0, l_lis.size());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCashTransferFromOrderUnit_Case003()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromOrderUnit_Case003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512203L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_OUT);
            l_aioOrderUnitParams.setOrderRootDiv("9");
            l_aioOrderUnitParams.setPaymentApplicationDiv("33");
            l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setCurrencyCode("007");
            l_aioOrderUnitParams.setDescription("cashout_gross1");
            l_aioOrderUnitParams.setRemarkCode("72");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromOrderUnit(l_subAccount);
            assertEquals(0, l_lis.size());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCashTransferFromOrderUnit_Case004()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromOrderUnit_Case004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512203L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_OUT);
            l_aioOrderUnitParams.setOrderRootDiv("9");
            l_aioOrderUnitParams.setPaymentApplicationDiv("33");
            l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setCurrencyCode("007");
            l_aioOrderUnitParams.setDescription(null);
            l_aioOrderUnitParams.setRemarkCode(null);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromOrderUnit(l_subAccount);
            WEB3AioCashTransUnit l_unit = (WEB3AioCashTransUnit)l_lis.get(0);
            assertEquals("007", l_unit.currencyCode);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCashTransferFromOrderUnit_Case005()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromOrderUnit_Case005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512203L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_OUT);
            l_aioOrderUnitParams.setOrderRootDiv("9");
            l_aioOrderUnitParams.setPaymentApplicationDiv("33");
            l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_aioOrderUnitParams.setCurrencyCode("007");
            l_aioOrderUnitParams.setDescription("cashout_gross1");
            l_aioOrderUnitParams.setRemarkCode("86");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromOrderUnit(l_subAccount);
            WEB3AioCashTransUnit l_unit = (WEB3AioCashTransUnit)l_lis.get(0);
            assertEquals("007", l_unit.currencyCode);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCashTransferFromCashinNotice()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromCashinNotice()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DepositInformRow.TYPE);
            DepositInformParams l_depositInformParams = this.getDepositInformRow();
            l_depositInformParams.setInstitutionCode("0D");
            l_depositInformParams.setBranchCode("381");
            l_depositInformParams.setAccountCode("2512246");
            l_depositInformParams.setTransferDate(GtlUtils.getSystemTimestamp());
            l_depositInformParams.setWorkedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_depositInformParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromCashinNotice(l_subAccount);
            WEB3AioCashTransUnit l_unit = (WEB3AioCashTransUnit)l_lis.get(0);
            assertEquals(null, l_unit.currencyCode);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testGetCashTransferFromCashTransferStatus()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromCashTransferStatus()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BankCashTransferStatusRow.TYPE);
            BankCashTransferStatusParams l_bankCashTransferStatusParams = this.getBankCashTransferStatusRow();
            l_bankCashTransferStatusParams.setDeliveryScheduledDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bankCashTransferStatusParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromCashinNotice(l_subAccount);
            WEB3AioCashTransUnit l_unit = (WEB3AioCashTransUnit)l_lis.get(0);
            assertEquals(null, l_unit.currencyCode);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public class forTestWEB3AioCashTransferListServiceImpl extends WEB3AioCashTransferListServiceImpl
    {
        public String getTradingType(AioOrderUnitParams l_aioOrderUnitParams)
        throws WEB3BaseException 
        {
            return "1";
        }
        public String getRemark(AioOrderUnitParams l_aioOrderUnitParams)
        throws WEB3BaseException 
        {
            return "99";
        }
//        public AioOrderUnitRow getOrderUnitRow(
//                String l_strInstitutionCode, 
//                String l_strBranchCode, 
//                String l_strAccountCode, 
//                String l_strOrderRequestNumber, 
//                SubAccountTypeEnum l_subAccountType)        
//                throws WEB3BaseException, NotFoundException
//            {
//                AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
//                l_aioOrderUnitParams.setDescription("72");
//                l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
//                return l_aioOrderUnitParams;
//                  
//            }
        
        public String getTransferStatus(
                String l_strTransferStatusDiv, 
                String l_strSendRcvDiv, 
                String l_strResultCode)
            throws WEB3BaseException 
        {
            return "ok";
        }
    }
    
    /**
     * 入金連絡Row
     * @@return
     */
    public static DepositInformParams getDepositInformRow()
    {
        DepositInformParams l_depositInformParams = new DepositInformParams();
        
        l_depositInformParams.setInstitutionCode("0D");
        l_depositInformParams.setBranchCode("381");
        l_depositInformParams.setFinInstitutionCode("11");
        l_depositInformParams.setAccountCode("2512246");
        l_depositInformParams.setTransferDate(GtlUtils.getSystemTimestamp());
        l_depositInformParams.setWorkedTimestamp(GtlUtils.getSystemTimestamp());
        l_depositInformParams.setOrderRequestNumber("123456");
        
        return l_depositInformParams;
    }
    
    /**
     * 金融機@関連携入出金状況Row
     * @@return
     */
    public static BankCashTransferStatusParams getBankCashTransferStatusRow()
    {
        BankCashTransferStatusParams l_bankCashTransferStatusParams = new BankCashTransferStatusParams();
        
        l_bankCashTransferStatusParams.setInstitutionCode("0D");
        l_bankCashTransferStatusParams.setBranchCode("381");
        l_bankCashTransferStatusParams.setAccountCode("2512246");
        l_bankCashTransferStatusParams.setPaySchemeId("123456789");
        l_bankCashTransferStatusParams.setOrderStatusFlag("0");
        l_bankCashTransferStatusParams.setOrderRequestNumber("123456");
        l_bankCashTransferStatusParams.setStartStatusFlag("1");
        l_bankCashTransferStatusParams.setResultStatusFlag("1");
        l_bankCashTransferStatusParams.setTransactionStatus("1");
        return l_bankCashTransferStatusParams;
    }
    
    /**
     * GFT振替状況Row
     */
    public static GftTransferStatusParams getGftTransferStatusRow()
    {
        GftTransferStatusParams l_gftTransferStatusParams = new GftTransferStatusParams();
        
        l_gftTransferStatusParams.setInstitutionCode("0D");
        l_gftTransferStatusParams.setBranchCode("381");
        l_gftTransferStatusParams.setAccountCode("2512246");
        l_gftTransferStatusParams.setOrderRequestNumber("123456");
        l_gftTransferStatusParams.setOperationDiv("01");
        l_gftTransferStatusParams.setCourseDiv("1");
        l_gftTransferStatusParams.setFxAccountCode("1234");
        l_gftTransferStatusParams.setTransferStatusDiv("1");
        l_gftTransferStatusParams.setSendRcvDiv("1");
        l_gftTransferStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_gftTransferStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_gftTransferStatusParams;
    }

    
    
    
    
    
    public void testGetRemark_Case002()
    {
        final String STR_METHOD_NAME = "testGetRemark_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            String l_strGetRemark = l_mpl2.getRemark(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            
            assertNull(l_strGetRemark);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
       log.exiting(STR_METHOD_NAME);
    }

    public void testGetCashTransferFromGftTransferStatus_Case001()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromGftTransferStatus_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setTransferDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            l_gftTransferStatusParams.setOrderRequestNumber("101");
            l_gftTransferStatusParams.setFxSystemCode("10");
            l_gftTransferStatusParams.setOperationDiv("01");
            l_gftTransferStatusParams.setCourseDiv("3");
            l_gftTransferStatusParams.setIoListTradeDiv("33");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromGftTransferStatus(l_subAccount);
            WEB3AioCashTransUnit l_unit = (WEB3AioCashTransUnit)l_lis.get(0);
            
            assertEquals(l_gftTransferStatusParams.course_div, l_unit.ioRemark);
            assertEquals("33", l_unit.tradingType);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //入出金明細.取引 = GFT振替状況Params.入出金一覧取引区分
    //入出金明細.備考 = GFT振替状況Params.コース区分
    public void testGetCashTransferFromGftTransferStatus_C0002()
    {
        final String STR_METHOD_NAME = "testGetCashTransferFromGftTransferStatus_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setTransferDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            l_gftTransferStatusParams.setOrderRequestNumber("101");
            l_gftTransferStatusParams.setFxSystemCode("10");
            l_gftTransferStatusParams.setOperationDiv("01");
            l_gftTransferStatusParams.setCourseDiv("3");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            List l_lis = l_mpl.getCashTransferFromGftTransferStatus(l_subAccount);
            WEB3AioCashTransUnit l_unit = (WEB3AioCashTransUnit)l_lis.get(0);
            
            assertEquals("3", l_unit.ioRemark);
            assertEquals(null, l_unit.tradingType);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public InstitutionPreferencesParams getInstitutionPreferencesParams()
    {
        InstitutionPreferencesParams l_params = new InstitutionPreferencesParams();
        //証券会社ＩＤ    institution_id     NUMBER    18    NotNull
        l_params.setInstitutionId(33);
        //プリファ@レンス名    name     VARVHAR2    200    NotNull
        l_params.setName("jiddk");
        //プリファ@レンス名の連番    name_serial_no     NUMBER    6    NotNull
        l_params.setNameSerialNo(1);
        //プリファ@レンスの値    value     VARVHAR2    200    NotNull
        l_params.setValue("jidck");
        //作成日付    created_timestamp     DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp     DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_params;
    }

    //注文単位Params.注文種別 = 1011（振替注文（預り金から為替保証金））の場合
    //getGFT振替状況()の戻り値 != nullの場合
    //GFT振替状況Params.入出金一覧取引区分を返却する。 
    public void testGetTradingType_C0001()
    {
        final String STR_METHOD_NAME = "testGetTradingType_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AioCashTransferListServiceImpl l_impl =
            new WEB3AioCashTransferListServiceImpl();
        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setOrderRequestNumber("111111112");

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("111111112");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            String l_strReturnValue = l_impl.getTradingType(l_aioOrderUnitParams);

            assertEquals(null, l_strReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //注文単位Params.注文種別 = 1011（振替注文（預り金から為替保証金））の場合
    //getGFT振替状況()の戻り値がnullの場合、
    //9（FX保証金へ出金） を返却する。 
    public void testGetTradingType_C0002()
    {
        final String STR_METHOD_NAME = "testGetTradingType_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AioCashTransferListServiceImpl l_impl =
            new WEB3AioCashTransferListServiceImpl();
        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setOrderRequestNumber("111111112");

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("000000002");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            String l_strReturnValue = l_impl.getTradingType(l_aioOrderUnitParams);

            assertEquals("9", l_strReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //注文単位Params.注文種別 = 1021（CFD振替注文（預り金からCFD口座））の場合
    //getGFT振替状況()の戻り値がnullの場合、
    //9（FX保証金へ出金） を返却する。 
    public void testGetTradingType_C0003()
    {
        final String STR_METHOD_NAME = "testGetTradingType_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AioCashTransferListServiceImpl l_impl =
            new WEB3AioCashTransferListServiceImpl();
        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setOrderRequestNumber("111111112");

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("000000002");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            String l_strReturnValue = l_impl.getTradingType(l_aioOrderUnitParams);

            assertEquals("9", l_strReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //注文単位Params.注文種別 = 1012（振替注文（為替保証金から預り金））の場合
    //getGFT振替状況()の戻り値がnullの場合、
    //10（FX保証金から入金） を返却する。 
    public void testGetTradingType_C0004()
    {
        final String STR_METHOD_NAME = "testGetTradingType_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AioCashTransferListServiceImpl l_impl =
            new WEB3AioCashTransferListServiceImpl();
        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setOrderRequestNumber("111111112");

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("000000002");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            String l_strReturnValue = l_impl.getTradingType(l_aioOrderUnitParams);

            assertEquals("10", l_strReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //注文単位Params.注文種別 = 1022（CFD振替注文（CFD口座から預り金））の場合
    //getGFT振替状況()の戻り値がnullの場合、
    //10（FX保証金から入金） を返却する。 
    public void testGetTradingType_C0005()
    {
        final String STR_METHOD_NAME = "testGetTradingType_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AioCashTransferListServiceImpl l_impl =
            new WEB3AioCashTransferListServiceImpl();
        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
            l_aioOrderUnitParams.setAccountId(123);
            l_aioOrderUnitParams.setOrderRequestNumber("111111112");

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("000000002");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            String l_strReturnValue = l_impl.getTradingType(l_aioOrderUnitParams);

            assertEquals("10", l_strReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
