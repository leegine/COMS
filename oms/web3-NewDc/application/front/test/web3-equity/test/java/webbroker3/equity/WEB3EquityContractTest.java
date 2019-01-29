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
     * ���O�o�̓��[�e�B���e�B�B<BR>
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
    //�Ǘ��� = �Ǘ���i�����ϕ��j
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

    //�Ǘ��� = �Ǘ���i�����ϕ��j+�Ǘ���i���ύϕ��j 1
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
    
    //�Ǘ��� = �Ǘ���i�����ϕ��j+�Ǘ���i���ύϕ��j 3
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
        //�g�����U�N�V�����h�c        fin_transaction_id          NOT NULL
        l_finTransactionParams.setFinTransactionId(111L);
        //�����h�c          account_id          NOT NULL
        l_finTransactionParams.setAccountId(333812512203L);
        //�⏕�����h�c            sub_account_id          NOT NULL
        l_finTransactionParams.setSubAccountId(33381251220301L);
        //�����h�c          product_id          NOT NULL
        l_finTransactionParams.setProductId(123456);
        //�g�����U�N�V�����^�C�v       fin_transaction_type            NOT NULL
        l_finTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
        //�g�����U�N�V�����J�e�S��  fin_transaction_categ       NOT NULL
        l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
        //�g�����U�N�V������������  fin_transaction_timestamp   NOT NULL
        l_finTransactionParams.setFinTransactionTimestamp(new Date("2007/05/21"));
        //�ŋ敪               tax_type            NOT NULL
        l_finTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
        //��n��               delivery_date           NOT NULL
        l_finTransactionParams.setDeliveryDate(new Date("2007/05/21"));
        //��n���          net_amount          NOT NULL
        l_finTransactionParams.setNetAmount(1.0);
        //�����^�C�v         product_type            NOT NULL
        l_finTransactionParams.setProductType(ProductTypeEnum.EQUITY);
        //�s��h�c          market_id           NULL
        //���P��          price               NULL
        //��萔��          quantity            NOT NULL
        l_finTransactionParams.setQuantity(1000);
        //�����h�c          order_id            NULL
        l_finTransactionParams.setOrderId(11);
        //�����P�ʂh�c            order_unit_id           NULL
        l_finTransactionParams.setOrderUnitId(1001);
        //���h�c          order_execution_id      NULL
        //�ϑ��萔��         commission_fee          NOT NULL
        l_finTransactionParams.setCommissionFee(1000);
        //�ϑ��萔�������      commission_fee_tax          NOT NULL
        l_finTransactionParams.setCommissionFeeTax(1000);
        //���Y�h�c          asset_id            NULL
        //�����̎�n���z           imported_paid_value     NOT NULL
        l_finTransactionParams.setImportedPaidValue(1000);
        //���萔��          imported_setup_fee      NOT NULL
        l_finTransactionParams.setImportedSetupFee(1000);
        //���萔�������           imported_setup_fee_tax      NOT NULL
        l_finTransactionParams.setImportedPayInterestFeeTax(1000);
        //���`������         imported_name_transfer_fee  NULL
        //���`�����������      imported_name_transfer_fee_tax  NULL
        //���n�v���z         capital_gain            NOT NULL
        l_finTransactionParams.setCapitalGain(1000);
        //���n�v�Ŋz         capital_gain_tax        NOT NULL
        l_finTransactionParams.setCapitalGainTax(1000);
        //�����h�c          contract_id         NULL
        l_finTransactionParams.setContractId(12347);
        //�Ǘ���               imported_management_fee     NOT NULL
        l_finTransactionParams.setImportedManagementFee(422);
        //�Ǘ�������            imported_management_fee_tax NOT NULL
        l_finTransactionParams.setImportedManagementFeeTax(533);
        //������               imported_interest_fee       NOT NULL
        l_finTransactionParams.setImportedInterestFee(1000);
        //�����������            imported_interest_fee_tax   NOT NULL
        l_finTransactionParams.setImportedInterestFeeTax(1000);
        //�t����               imported_pay_interest_fee   NULL
        //�t���������            imported_pay_interest_fee_tax   NULL
        //�݊���               imported_loan_equity_fee    NULL
        //���̑�               imported_other          NULL
        //���p�ۗL���Y�̊Ǘ���        transfered_asset_mng_fee    NULL
        //���p�ۗL���Y�̊Ǘ������� transfered_asset_mng_fee_tax    NULL
        //���p�ۗL���Y�̎萔��        transfered_asset_setup_fee  NULL
        //���p�ۗL���Y�̎萔������� transfered_asset_setup_fee_tax  NULL
        //���Y�̕뉿         transfered_asset_book_value NULL
        //�폜�t���O         delete_flag         NOT NULL
        l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        //�������o�ߗ��q      accrued_interest        NULL
        //�쐬���t          created_timestamp       NOT NULL
        l_finTransactionParams.setCreatedTimestamp(new Date("2007/05/21"));
        //�X�V���t          last_updated_timestamp      NOT NULL
        l_finTransactionParams.setLastUpdatedTimestamp(new Date("2007/05/21"));
        //���n�v�L�����           capital_gain_status     NOT NULL
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
        //�g�����U�N�V�����h�c        fin_transaction_id          NOT NULL
        l_finTransactionParams.setFinTransactionId(111L);
        //�����h�c          account_id          NOT NULL
        l_finTransactionParams.setAccountId(333812512203L);
        //�⏕�����h�c            sub_account_id          NOT NULL
        l_finTransactionParams.setSubAccountId(33381251220301L);
        //�����h�c          product_id          NOT NULL
        l_finTransactionParams.setProductId(123456);
        //�g�����U�N�V�����^�C�v       fin_transaction_type            NOT NULL
        l_finTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
        //�g�����U�N�V�����J�e�S��  fin_transaction_categ       NOT NULL
        l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
        //�g�����U�N�V������������  fin_transaction_timestamp   NOT NULL
        l_finTransactionParams.setFinTransactionTimestamp(new Date("2007/05/21"));
        //�ŋ敪               tax_type            NOT NULL
        l_finTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
        //��n��               delivery_date           NOT NULL
        l_finTransactionParams.setDeliveryDate(new Date("2007/05/21"));
        //��n���          net_amount          NOT NULL
        l_finTransactionParams.setNetAmount(1.0);
        //�����^�C�v         product_type            NOT NULL
        l_finTransactionParams.setProductType(ProductTypeEnum.EQUITY);
        //�s��h�c          market_id           NULL
        //���P��          price               NULL
        //��萔��          quantity            NOT NULL
        l_finTransactionParams.setQuantity(1000);
        //�����h�c          order_id            NULL
        l_finTransactionParams.setOrderId(11);
        //�����P�ʂh�c            order_unit_id           NULL
        l_finTransactionParams.setOrderUnitId(l_lgnOrderUnitId);
        //���h�c          order_execution_id      NULL
        //�ϑ��萔��         commission_fee          NOT NULL
        l_finTransactionParams.setCommissionFee(1000);
        //�ϑ��萔�������      commission_fee_tax          NOT NULL
        l_finTransactionParams.setCommissionFeeTax(1000);
        //���Y�h�c          asset_id            NULL
        //�����̎�n���z           imported_paid_value     NOT NULL
        l_finTransactionParams.setImportedPaidValue(1000);
        //���萔��          imported_setup_fee      NOT NULL
        l_finTransactionParams.setImportedSetupFee(1000);
        //���萔�������           imported_setup_fee_tax      NOT NULL
        l_finTransactionParams.setImportedPayInterestFeeTax(1000);
        //���`������         imported_name_transfer_fee  NULL
        //���`�����������      imported_name_transfer_fee_tax  NULL
        //���n�v���z         capital_gain            NOT NULL
        l_finTransactionParams.setCapitalGain(1000);
        //���n�v�Ŋz         capital_gain_tax        NOT NULL
        l_finTransactionParams.setCapitalGainTax(1000);
        //�����h�c          contract_id         NULL
        l_finTransactionParams.setContractId(l_lngContractId);
        //�Ǘ���               imported_management_fee     NOT NULL
        l_finTransactionParams.setImportedManagementFee(l_dblImportedManagementFee);
        //�Ǘ�������            imported_management_fee_tax NOT NULL
        l_finTransactionParams.setImportedManagementFeeTax(l_dblImportedManagementFeeTax);
        //������               imported_interest_fee       NOT NULL
        l_finTransactionParams.setImportedInterestFee(1000);
        //�����������            imported_interest_fee_tax   NOT NULL
        l_finTransactionParams.setImportedInterestFeeTax(1000);
        //�t����               imported_pay_interest_fee   NULL
        //�t���������            imported_pay_interest_fee_tax   NULL
        //�݊���               imported_loan_equity_fee    NULL
        //���̑�               imported_other          NULL
        //���p�ۗL���Y�̊Ǘ���        transfered_asset_mng_fee    NULL
        //���p�ۗL���Y�̊Ǘ������� transfered_asset_mng_fee_tax    NULL
        //���p�ۗL���Y�̎萔��        transfered_asset_setup_fee  NULL
        //���p�ۗL���Y�̎萔������� transfered_asset_setup_fee_tax  NULL
        //���Y�̕뉿         transfered_asset_book_value NULL
        //�폜�t���O         delete_flag         NOT NULL
        l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        //�������o�ߗ��q      accrued_interest        NULL
        //�쐬���t          created_timestamp       NOT NULL
        l_finTransactionParams.setCreatedTimestamp(new Date("2007/05/21"));
        //�X�V���t          last_updated_timestamp      NOT NULL
        l_finTransactionParams.setLastUpdatedTimestamp(new Date("2007/05/21"));
        //���n�v�L�����           capital_gain_status     NOT NULL
        l_finTransactionParams.setCapitalGainStatus("0");
        return l_finTransactionParams;
    }
}
@
