head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityExecutedMailSenderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.EqtypeOrderExecSendMailParams;
import webbroker3.equity.data.EqtypeOrderExecSendMailRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author tang-xingfeng
 *
 */
public class WEB3EquityExecutedMailSenderServiceImplTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecutedMailSenderServiceImplTest.class);
    WEB3EquityExecutedMailSenderServiceImpl impl =
        new WEB3EquityExecutedMailSenderServiceImpl();

    public WEB3EquityExecutedMailSenderServiceImplTest(String arg0)
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
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecutedMailSenderServiceImpl.undoSendMail(OrderUnit)'
     */
    public void testUndoSendMail()
    {
        final String STR_METHOD_NAME = "testUndoSendMail()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);

            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(63101);
            l_branchParams.setInstitutionCode("6D");
            l_branchParams.setInstitutionId(63);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            EqtypeOrderExecSendMailParams l_eqtypeOrderExecSendMailParams =
                this.getEqtypeOrderExecSendMailRow();

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderExecSendMailRow.TYPE);

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderExecSendMailParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(3304148080001L);
            impl.undoSendMail(l_orderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public static EqtypeOrderExecSendMailParams getEqtypeOrderExecSendMailRow()
    {
        EqtypeOrderExecSendMailParams l_eqtypeOrderExecSendMailParams =
            new EqtypeOrderExecSendMailParams();
        //�،���ЃR�[�h,  institution_code,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setInstitutionCode("6D");
        //���X�R�[�h,  branch_code,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setBranchCode("381");
        //�����R�[�h,  account_code,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setAccountCode("2512246");
        //���ʃR�[�h,  order_request_number,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setOrderRequestNumber("000022001");
        //�����R�[�h,  product_code,  NULL,
        l_eqtypeOrderExecSendMailParams.setProductCode("1");
        //�s��R�[�h,  market_code,  NULL,
        l_eqtypeOrderExecSendMailParams.setMarketCode("1");
        //���������h�c,  order_action_id,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setOrderActionId(15051L);
        //�ŋ敪,  tax_type,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setTaxType(TaxTypeEnum.NORMAL);
        //�󒍓���,  received_date_time,  NULL,
//        l_eqtypeOrderExecSendMailParams.setReceivedDateTime(Calendar.getInstance().getTime());
        //���敪,  order_exec_status,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setOrderExecStatus("1");
        //��n���,  delivaly_amount,  NULL,
//        l_eqtypeOrderExecSendMailParams.setDelivalyAmount(200D);
        //��萔��,  executed_quantity,  NULL,
//        l_eqtypeOrderExecSendMailParams.setExecutedQuantity(300D);
        //�������,  order_type,  NULL,
        //����R�[�h�iSONAR�j,  sonar_traded_code,  NULL,
        //�d�q���[�����M�X�e�C�^�X,  status,  NULL,
        //�d�q���[�����M����,  send_process_date_time,  NULL,
        //�d�q���[�����M�G���[�R�[�h,  error_code,  NULL,
        //�đ��敪,  resend_status,  NULL,
        //�d�q���[���đ�����,  resend_process_date_time,  NULL,
        //�������R�R�[�h,  reason_code,  NULL,
        //�w�l,  limit_price,  NULL,
        //email�A�h���X,  email_address,  NULL,
        //��������,  order_quantity,  NULL,
        //�폜�t���O,  delete_flag,  NULL,
        //�쐬����,  created_timestamp,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //�X�V����,  last_updated_timestamp,  NOT NULL,
        l_eqtypeOrderExecSendMailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_eqtypeOrderExecSendMailParams;
    }

}
@
