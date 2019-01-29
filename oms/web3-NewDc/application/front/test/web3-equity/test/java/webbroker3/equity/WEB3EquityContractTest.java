head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityContractTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3EquityContractTest extends TestBaseForMock
{
    /**
     * ƒƒOo—Íƒ†[ƒeƒBƒŠƒeƒBB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityContractTest.class);

    public WEB3EquityContractTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityContract.getManagementFee(long)'
     */
    //ŠÇ—”ï = ŠÇ—”ïi–¢ŒˆÏ•ªj
    public void testGetManagementFeeLongC1()
    {
        String STR_METHOD_NAME = "testGetManagementFeeLongC1()";
        log.entering(STR_METHOD_NAME);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(111);
        l_eqtypeContractParams.setManagementFee(50322.43212);
        l_eqtypeContractParams.setManagementFeeTax(60322.4312);
        l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
        
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderId(11);
        l_eqtypeOrderUnitParams.setOrderUnitId(123L);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3EquityContract l_contract = new WEB3EquityContract(111);
            double l_dblManagementFee = l_contract.getManagementFee(123L);
            
            assertEquals(l_dblManagementFee + "", "50322.43212");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //ŠÇ—”ï = ŠÇ—”ïi–¢ŒˆÏ•ªj+ŠÇ—”ïiŒˆÏÏ•ªj 1
    public void testGetManagementFeeLongC2()
    {
        String STR_METHOD_NAME = "testGetManagementFeeLongC2()";
        log.entering(STR_METHOD_NAME);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(111);
        l_eqtypeContractParams.setManagementFee(50322.43212);
        l_eqtypeContractParams.setManagementFeeTax(60322.4312);
        l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
        
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderId(11);
        l_eqtypeOrderUnitParams.setOrderUnitId(123L);
        
        EqtypeFinTransactionParams l_finTransactionParams =
            this.getEqtypeFinTransactionParams(10000, 20000, 111L, 123L);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_finTransactionParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3EquityContract l_contract = new WEB3EquityContract(111);
            double l_dblManagementFee = l_contract.getManagementFee(123L);
            
            assertEquals(l_dblManagementFee + "", "60322.43212");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //ŠÇ—”ï = ŠÇ—”ïi–¢ŒˆÏ•ªj+ŠÇ—”ïiŒˆÏÏ•ªj 3
    public void testGetManagementFeeLongC3()
    {
        String STR_METHOD_NAME = "testGetManagementFeeLongC3()";
        log.entering(STR_METHOD_NAME);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(111);
        l_eqtypeContractParams.setManagementFee(50322.43212);
        l_eqtypeContractParams.setManagementFeeTax(60322.4312);
        l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
        
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderId(11);
        l_eqtypeOrderUnitParams.setOrderUnitId(123L);
        
        EqtypeFinTransactionParams l_finTransactionParams1 =
            this.getEqtypeFinTransactionParams(10000, 20000, 111L, 123L);
        l_finTransactionParams1.setFinTransactionId(111);
        EqtypeFinTransactionParams l_finTransactionParams2 =
            this.getEqtypeFinTransactionParams(10000, 20000, 111L, 123L);
        l_finTransactionParams2.setFinTransactionId(222);
        EqtypeFinTransactionParams l_finTransactionParams3 =
            this.getEqtypeFinTransactionParams(10000, 20000, 111L, 123L);
        l_finTransactionParams3.setFinTransactionId(333);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_finTransactionParams1);
            TestDBUtility.insertWithDel(l_finTransactionParams2);
            TestDBUtility.insertWithDel(l_finTransactionParams3);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3EquityContract l_contract = new WEB3EquityContract(111);
            double l_dblManagementFee = l_contract.getManagementFee(123L);
            
            assertEquals(l_dblManagementFee + "", "80322.43212");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetManagementFeeTaxCase0001()
    {
        final String STR_METHOD_NAME = " testGetManagementFeeTaxCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
        
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(12345);
            l_eqtypeContractParams.setManagementFeeTax(100);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            
            WEB3EquityContract l_contract = new WEB3EquityContract(l_eqtypeContractParams.getContractId());
            
            double l_dblManagementFeeTax = l_contract.getManagementFeeTax(1001);
            
            assertEquals("100", WEB3StringTypeUtility.formatNumber(l_dblManagementFeeTax));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    public void testGetManagementFeeTaxCase0002()
    {
        final String STR_METHOD_NAME = " testGetManagementFeeTaxCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(12345);
            l_eqtypeContractParams.setManagementFeeTax(100);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            EqtypeFinTransactionParams l_eqtypeFinTransactionParams = this.getEqtypeFinTransactionParams();
            l_eqtypeFinTransactionParams.setContractId(l_eqtypeContractParams.getContractId());
            l_eqtypeFinTransactionParams.setOrderUnitId(1001);
            l_eqtypeFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_eqtypeFinTransactionParams.setImportedManagementFeeTax(200);
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeFinTransactionParams);
            
            WEB3EquityContract l_contract = new WEB3EquityContract(l_eqtypeContractParams.getContractId());
            
            double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_eqtypeFinTransactionParams.getOrderUnitId());
            
            assertEquals("300", WEB3StringTypeUtility.formatNumber(l_dblManagementFeeTax));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    public void testGetManagementFeeTaxCase0003()
    {
        final String STR_METHOD_NAME = " testGetManagementFeeTaxCase0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(12345);
            l_eqtypeContractParams.setManagementFeeTax(100);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            EqtypeFinTransactionParams l_eqtypeFinTransactionParams = this.getEqtypeFinTransactionParams();
            l_eqtypeFinTransactionParams.setFinTransactionId(101);
            l_eqtypeFinTransactionParams.setContractId(l_eqtypeContractParams.getContractId());
            l_eqtypeFinTransactionParams.setOrderUnitId(1001);
            l_eqtypeFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_eqtypeFinTransactionParams.setImportedManagementFeeTax(200);
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeFinTransactionParams);
            
            EqtypeFinTransactionParams l_eqtypeFinTransactionParams1 = this.getEqtypeFinTransactionParams();
            l_eqtypeFinTransactionParams1.setFinTransactionId(102);
            l_eqtypeFinTransactionParams1.setContractId(l_eqtypeContractParams.getContractId());
            l_eqtypeFinTransactionParams1.setOrderUnitId(1001);
            l_eqtypeFinTransactionParams1.setDeleteFlag(BooleanEnum.FALSE);
            l_eqtypeFinTransactionParams1.setImportedManagementFeeTax(300);
            TestDBUtility.insertWithDel(l_eqtypeFinTransactionParams1);
            
            EqtypeFinTransactionParams l_eqtypeFinTransactionParams2 = this.getEqtypeFinTransactionParams();
            l_eqtypeFinTransactionParams2.setFinTransactionId(103);
            l_eqtypeFinTransactionParams2.setContractId(l_eqtypeContractParams.getContractId());
            l_eqtypeFinTransactionParams2.setOrderUnitId(1001);
            l_eqtypeFinTransactionParams2.setDeleteFlag(BooleanEnum.FALSE);
            l_eqtypeFinTransactionParams2.setImportedManagementFeeTax(400);
            TestDBUtility.insertWithDel(l_eqtypeFinTransactionParams2);

            WEB3EquityContract l_contract = new WEB3EquityContract(l_eqtypeContractParams.getContractId());
            
            double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_eqtypeFinTransactionParams.getOrderUnitId());
            
            assertEquals("1000", WEB3StringTypeUtility.formatNumber(l_dblManagementFeeTax));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    public EqtypeFinTransactionParams getEqtypeFinTransactionParams()
    {
        
        EqtypeFinTransactionParams l_finTransactionParams = new EqtypeFinTransactionParams();
        //ƒgƒ‰ƒ“ƒUƒNƒVƒ‡ƒ“‚h‚c        fin_transaction_id          NOT NULL
        l_finTransactionParams.setFinTransactionId(111L);
        //ŒûÀ‚h‚c          account_id          NOT NULL
        l_finTransactionParams.setAccountId(333812512203L);
        //•â•ŒûÀ‚h‚c            sub_account_id          NOT NULL
        l_finTransactionParams.setSubAccountId(33381251220301L);
        //–Á•¿‚h‚c          product_id          NOT NULL
        l_finTransactionParams.setProductId(123456);
        //ƒgƒ‰ƒ“ƒUƒNƒVƒ‡ƒ“ƒ^ƒCƒv       fin_transaction_type            NOT NULL
        l_finTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
        //ƒgƒ‰ƒ“ƒUƒNƒVƒ‡ƒ“ƒJƒeƒSƒŠ  fin_transaction_categ       NOT NULL
        l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
        //ƒgƒ‰ƒ“ƒUƒNƒVƒ‡ƒ“”­¶“ú  fin_transaction_timestamp   NOT NULL
        l_finTransactionParams.setFinTransactionTimestamp(new Date("2007/05/21"));
        //Å‹æ•ª               tax_type            NOT NULL
        l_finTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
        //ó“n“ú               delivery_date           NOT NULL
        l_finTransactionParams.setDeliveryDate(new Date("2007/05/21"));
        //ó“n‘ã‹à          net_amount          NOT NULL
        l_finTransactionParams.setNetAmount(1.0);
        //–Á•¿ƒ^ƒCƒv         product_type            NOT NULL
        l_finTransactionParams.setProductType(ProductTypeEnum.EQUITY);
        //sê‚h‚c          market_id           NULL
        //–ñ’è’P‰¿          price               NULL
        //–ñ’è”—Ê          quantity            NOT NULL
        l_finTransactionParams.setQuantity(1000);
        //’•¶‚h‚c          order_id            NULL
        l_finTransactionParams.setOrderId(11);
        //’•¶’PˆÊ‚h‚c            order_unit_id           NULL
        l_finTransactionParams.setOrderUnitId(1001);
        //–ñ’è‚h‚c          order_execution_id      NULL
        //ˆÏ‘õè”—¿         commission_fee          NOT NULL
        l_finTransactionParams.setCommissionFee(1000);
        //ˆÏ‘õè”—¿Á”ïÅ      commission_fee_tax          NOT NULL
        l_finTransactionParams.setCommissionFeeTax(1000);
        //‘Y‚h‚c          asset_id            NULL
        //ŒšŠ”‚Ìó“n‹àŠz           imported_paid_value     NOT NULL
        l_finTransactionParams.setImportedPaidValue(1000);
        //Œšè”—¿          imported_setup_fee      NOT NULL
        l_finTransactionParams.setImportedSetupFee(1000);
        //Œšè”—¿Á”ïÅ           imported_setup_fee_tax      NOT NULL
        l_finTransactionParams.setImportedPayInterestFeeTax(1000);
        //–¼‹`‘Š·—¿         imported_name_transfer_fee  NULL
        //–¼‹`‘Š·—¿Á”ïÅ      imported_name_transfer_fee_tax  NULL
        //÷“n‰v‹àŠz         capital_gain            NOT NULL
        l_finTransactionParams.setCapitalGain(1000);
        //÷“n‰vÅŠz         capital_gain_tax        NOT NULL
        l_finTransactionParams.setCapitalGainTax(1000);
        //ŒšŠ”‚h‚c          contract_id         NULL
        l_finTransactionParams.setContractId(12347);
        //ŠÇ—”ï               imported_management_fee     NOT NULL
        l_finTransactionParams.setImportedManagementFee(422);
        //ŠÇ—”ïÁ”ïÅ            imported_management_fee_tax NOT NULL
        l_finTransactionParams.setImportedManagementFeeTax(533);
        //‡“ú•à               imported_interest_fee       NOT NULL
        l_finTransactionParams.setImportedInterestFee(1000);
        //‡“ú•àÁ”ïÅ            imported_interest_fee_tax   NOT NULL
        l_finTransactionParams.setImportedInterestFeeTax(1000);
        //‹t“ú•à               imported_pay_interest_fee   NULL
        //‹t“ú•àÁ”ïÅ            imported_pay_interest_fee_tax   NULL
        //‘İŠ”—¿               imported_loan_equity_fee    NULL
        //‚»‚Ì‘¼               imported_other          NULL
        //”„‹p•Û—L‘Y‚ÌŠÇ—”ï        transfered_asset_mng_fee    NULL
        //”„‹p•Û—L‘Y‚ÌŠÇ—”ïÁ”ïÅ transfered_asset_mng_fee_tax    NULL
        //”„‹p•Û—L‘Y‚Ìè”—¿        transfered_asset_setup_fee  NULL
        //”„‹p•Û—L‘Y‚Ìè”—¿Á”ïÅ transfered_asset_setup_fee_tax  NULL
        //‘Y‚Ì•ë‰¿         transfered_asset_book_value NULL
        //íœƒtƒ‰ƒO         delete_flag         NOT NULL
        l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        //ÂŒ”Š“¾Œo‰ß—˜q      accrued_interest        NULL
        //ì¬“ú•t          created_timestamp       NOT NULL
        l_finTransactionParams.setCreatedTimestamp(new Date("2007/05/21"));
        //XV“ú•t          last_updated_timestamp      NOT NULL
        l_finTransactionParams.setLastUpdatedTimestamp(new Date("2007/05/21"));
        //÷“n‰v—LŒøó‘Ô           capital_gain_status     NOT NULL
        l_finTransactionParams.setCapitalGainStatus("0");
        return l_finTransactionParams;
    }
    
    public EqtypeFinTransactionParams getEqtypeFinTransactionParams(
            double l_dblImportedManagementFee,
            double l_dblImportedManagementFeeTax,
            long l_lngContractId,
            long l_lgnOrderUnitId)
    {
        
        EqtypeFinTransactionParams l_finTransactionParams = new EqtypeFinTransactionParams();
        //ƒgƒ‰ƒ“ƒUƒNƒVƒ‡ƒ“‚h‚c        fin_transaction_id          NOT NULL
        l_finTransactionParams.setFinTransactionId(111L);
        //ŒûÀ‚h‚c          account_id          NOT NULL
        l_finTransactionParams.setAccountId(333812512203L);
        //•â•ŒûÀ‚h‚c            sub_account_id          NOT NULL
        l_finTransactionParams.setSubAccountId(33381251220301L);
        //–Á•¿‚h‚c          product_id          NOT NULL
        l_finTransactionParams.setProductId(123456);
        //ƒgƒ‰ƒ“ƒUƒNƒVƒ‡ƒ“ƒ^ƒCƒv       fin_transaction_type            NOT NULL
        l_finTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
        //ƒgƒ‰ƒ“ƒUƒNƒVƒ‡ƒ“ƒJƒeƒSƒŠ  fin_transaction_categ       NOT NULL
        l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
        //ƒgƒ‰ƒ“ƒUƒNƒVƒ‡ƒ“”­¶“ú  fin_transaction_timestamp   NOT NULL
        l_finTransactionParams.setFinTransactionTimestamp(new Date("2007/05/21"));
        //Å‹æ•ª               tax_type            NOT NULL
        l_finTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
        //ó“n“ú               delivery_date           NOT NULL
        l_finTransactionParams.setDeliveryDate(new Date("2007/05/21"));
        //ó“n‘ã‹à          net_amount          NOT NULL
        l_finTransactionParams.setNetAmount(1.0);
        //–Á•¿ƒ^ƒCƒv         product_type            NOT NULL
        l_finTransactionParams.setProductType(ProductTypeEnum.EQUITY);
        //sê‚h‚c          market_id           NULL
        //–ñ’è’P‰¿          price               NULL
        //–ñ’è”—Ê          quantity            NOT NULL
        l_finTransactionParams.setQuantity(1000);
        //’•¶‚h‚c          order_id            NULL
        l_finTransactionParams.setOrderId(11);
        //’•¶’PˆÊ‚h‚c            order_unit_id           NULL
        l_finTransactionParams.setOrderUnitId(l_lgnOrderUnitId);
        //–ñ’è‚h‚c          order_execution_id      NULL
        //ˆÏ‘õè”—¿         commission_fee          NOT NULL
        l_finTransactionParams.setCommissionFee(1000);
        //ˆÏ‘õè”—¿Á”ïÅ      commission_fee_tax          NOT NULL
        l_finTransactionParams.setCommissionFeeTax(1000);
        //‘Y‚h‚c          asset_id            NULL
        //ŒšŠ”‚Ìó“n‹àŠz           imported_paid_value     NOT NULL
        l_finTransactionParams.setImportedPaidValue(1000);
        //Œšè”—¿          imported_setup_fee      NOT NULL
        l_finTransactionParams.setImportedSetupFee(1000);
        //Œšè”—¿Á”ïÅ           imported_setup_fee_tax      NOT NULL
        l_finTransactionParams.setImportedPayInterestFeeTax(1000);
        //–¼‹`‘Š·—¿         imported_name_transfer_fee  NULL
        //–¼‹`‘Š·—¿Á”ïÅ      imported_name_transfer_fee_tax  NULL
        //÷“n‰v‹àŠz         capital_gain            NOT NULL
        l_finTransactionParams.setCapitalGain(1000);
        //÷“n‰vÅŠz         capital_gain_tax        NOT NULL
        l_finTransactionParams.setCapitalGainTax(1000);
        //ŒšŠ”‚h‚c          contract_id         NULL
        l_finTransactionParams.setContractId(l_lngContractId);
        //ŠÇ—”ï               imported_management_fee     NOT NULL
        l_finTransactionParams.setImportedManagementFee(l_dblImportedManagementFee);
        //ŠÇ—”ïÁ”ïÅ            imported_management_fee_tax NOT NULL
        l_finTransactionParams.setImportedManagementFeeTax(l_dblImportedManagementFeeTax);
        //‡“ú•à               imported_interest_fee       NOT NULL
        l_finTransactionParams.setImportedInterestFee(1000);
        //‡“ú•àÁ”ïÅ            imported_interest_fee_tax   NOT NULL
        l_finTransactionParams.setImportedInterestFeeTax(1000);
        //‹t“ú•à               imported_pay_interest_fee   NULL
        //‹t“ú•àÁ”ïÅ            imported_pay_interest_fee_tax   NULL
        //‘İŠ”—¿               imported_loan_equity_fee    NULL
        //‚»‚Ì‘¼               imported_other          NULL
        //”„‹p•Û—L‘Y‚ÌŠÇ—”ï        transfered_asset_mng_fee    NULL
        //”„‹p•Û—L‘Y‚ÌŠÇ—”ïÁ”ïÅ transfered_asset_mng_fee_tax    NULL
        //”„‹p•Û—L‘Y‚Ìè”—¿        transfered_asset_setup_fee  NULL
        //”„‹p•Û—L‘Y‚Ìè”—¿Á”ïÅ transfered_asset_setup_fee_tax  NULL
        //‘Y‚Ì•ë‰¿         transfered_asset_book_value NULL
        //íœƒtƒ‰ƒO         delete_flag         NOT NULL
        l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        //ÂŒ”Š“¾Œo‰ß—˜q      accrued_interest        NULL
        //ì¬“ú•t          created_timestamp       NOT NULL
        l_finTransactionParams.setCreatedTimestamp(new Date("2007/05/21"));
        //XV“ú•t          last_updated_timestamp      NOT NULL
        l_finTransactionParams.setLastUpdatedTimestamp(new Date("2007/05/21"));
        //÷“n‰v—LŒøó‘Ô           capital_gain_status     NOT NULL
        l_finTransactionParams.setCapitalGainStatus("0");
        return l_finTransactionParams;
    }
}
@
