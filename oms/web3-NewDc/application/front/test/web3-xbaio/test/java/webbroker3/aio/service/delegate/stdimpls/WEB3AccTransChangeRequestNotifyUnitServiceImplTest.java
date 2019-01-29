head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeRequestNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import test.util.TestDBUtility;
import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeRequestNotifyUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccTransChangeRequestNotifyUnitServiceImplTest.class);

    public WEB3AccTransChangeRequestNotifyUnitServiceImpl l_impl = null;

    public WEB3AccTransChangeRequestNotifyUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3AccTransChangeRequestNotifyUnitServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * ����.�U�֓��͒ʒm�L���[Params�I�u�W�F�N�g == null
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams = null;
        OrderTypeEnum l_orderType = OrderTypeEnum.BOND_BUY;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� == null
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType = null;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.�U�փ^�C�v == null
     */
    public void testSubmitOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0003()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = null;

        try
        {
            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �،���ЃI�u�W�F�N�g���擾���܂���
     */
    public void testSubmitOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0004()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("00");
            TestDBUtility.insertWithDel(l_institutionParams);

            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �㗝���͎҃I�u�W�F�N�g���擾���܂���
     */
    public void testSubmitOrder_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0005()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        l_hostTransferReceiptParams.setTraderCode("10000");
        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("10001");
            TestDBUtility.insertWithDel(l_traderParams);

            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �،���ЃI�u�W�F�N�g.���҃R�[�h = null 
     * �ڋq�I�u�W�F�N�g���擾���܂���
     */
    public void testSubmitOrder_C0006()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0006()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        l_hostTransferReceiptParams.setTraderCode(null);
        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("10001");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("0000000");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �،���ЃI�u�W�F�N�g.���҃R�[�h = null 
     * �⏕�����I�u�W�F�N�g���擾���܂���
     */
    public void testSubmitOrder_C0007()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0007()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        l_hostTransferReceiptParams.setTraderCode(null);
        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("10001");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.ALL_IN_ONE_ACCOUNT);
            l_subAccountParams.setAccountId(333812512247L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �،���ЃI�u�W�F�N�g.���҃R�[�h ="     "
     * �⏕�����I�u�W�F�N�g���擾���܂���
     */
    public void testSubmitOrder_C0008()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0008()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        l_hostTransferReceiptParams.setTraderCode("     ");
        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("10001");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.ALL_IN_ONE_ACCOUNT);
            l_subAccountParams.setAccountId(333812512247L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �����o�^�����̖߂�l..getProcessingResult().isFailedResult() = true
     */
    public void testSubmitOrder_C0009()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0009()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        l_hostTransferReceiptParams.setTraderCode("     ");
        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            ProcessingResult processingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {
                    SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                l_orderSubmissionResult);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("10001");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �����o�^�����̖߂�l..getProcessingResult().isFailedResult() = false
     * ����
     */
    public void testSubmitOrder_C0010()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0010()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams =
            TestDBUtility.getHostTransferReceiptRow();
        l_hostTransferReceiptParams.setTraderCode("     ");
        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_orderSubmissionResult = new OrderSubmissionResult(processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitNewOrder",
                new Class[] {
                    SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class},
                l_orderSubmissionResult);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("10001");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_impl.submitOrder(l_hostTransferReceiptParams, l_orderType, l_changeType);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� == null
     */
    public void testGetSubAccountType_C0001()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0001()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = null;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;

        try
        {
            l_impl.getSubAccountType(l_orderType, l_changeType);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.�U�փ^�C�v == null
     */
    public void testGetSubAccountType_C0002()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0002()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.UNDEFINED;
        AssetTransferTypeEnum l_changeType = null;

        try
        {
            l_impl.getSubAccountType(l_orderType, l_changeType);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j�̏ꍇ
     * ����.�U�փ^�C�v = 1�i�����j�̏ꍇ
     */
    public void testGetSubAccountType_C0003()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0003()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(2, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_MARGIN_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j�̏ꍇ
     * ����.�U�փ^�C�v = 2�i�o���j�̏ꍇ
     */
    public void testGetSubAccountType_C0004()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0004()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(1, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� = 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j�̏ꍇ
     * ����.�U�փ^�C�v = 1�i�����j�̏ꍇ
     */
    public void testGetSubAccountType_C0005()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0005()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(1, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� = 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j�̏ꍇ
     * ����.�U�փ^�C�v = 2�i�o���j�̏ꍇ
     */
    public void testGetSubAccountType_C0006()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0006()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(2, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_MARGIN_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� = 1007�i�U�֒����i�a������犔��؋����j�j�̏ꍇ
     * ����.�U�փ^�C�v = 1�i�����j�̏ꍇ
     */
    public void testGetSubAccountType_C0007()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0007()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(7, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_OPTIONS_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� = 1007�i�U�֒����i�a������犔��؋����j�j�̏ꍇ
     * ����.�U�փ^�C�v = 2�i�o���j�̏ꍇ
     */
    public void testGetSubAccountType_C0008()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0008()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(1, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� = 1008�i�U�֒����i����؋�������a����j�j�̏ꍇ
     * ����.�U�փ^�C�v = 1�i�����j�̏ꍇ
     */
    public void testGetSubAccountType_C0009()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0009()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(1, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������� = 1008�i�U�֒����i����؋�������a����j�j�̏ꍇ
     * ����.�U�փ^�C�v = 2�i�o���j�̏ꍇ
     */
    public void testGetSubAccountType_C0010()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0010()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(7, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_OPTIONS_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������ʂ��ȉ��̂����ꂩ�̏ꍇ
     * 1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j
     */
    public void testGetSubAccountType_C0011()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0011()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(1, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������ʂ��ȉ��̂����ꂩ�̏ꍇ
     * 1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j
     */
    public void testGetSubAccountType_C0012()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0012()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(1, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������ʂ��ȉ��̂����ꂩ�̏ꍇ
     * 1017�i���̑��U�֒����i�a�������X�j�j
     */
    public void testGetSubAccountType_C0013()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0013()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.TO_OTHER_TRANSFER;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_OUT;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(1, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����.������ʂ��ȉ��̂����ꂩ�̏ꍇ
     * 1018�i���̑��U�֒����iX����a����j�j
     */
    public void testGetSubAccountType_C0014()
    {
        final String STR_METHOD_NAME = "testGetSubAccountType_C0013()";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderType = OrderTypeEnum.FROM_OTHER_TRANSFER;
        AssetTransferTypeEnum l_changeType = AssetTransferTypeEnum.CASH_IN;

        try
        {
            SubAccountTypeEnum l_subAccountTypeEnum =
                l_impl.getSubAccountType(l_orderType, l_changeType);

            assertEquals(1, l_subAccountTypeEnum.intValue());
            assertEquals("EQUITY_SUB_ACCOUNT ", l_subAccountTypeEnum.stringValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
