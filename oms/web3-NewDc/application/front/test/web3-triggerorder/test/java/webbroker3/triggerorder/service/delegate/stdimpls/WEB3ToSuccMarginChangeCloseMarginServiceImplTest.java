head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.20.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccMarginChangeCloseMarginServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccMarginChangeSettleContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderActionParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmResponse;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

/**
 *
 */
public class WEB3ToSuccMarginChangeCloseMarginServiceImplTest extends TestBaseForMock
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginServiceImplTest.class);

    /**
     *
     */
    public WEB3ToSuccMarginChangeCloseMarginServiceImplTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    private WEB3ToSuccMarginChangeCloseMarginServiceImplInner l_toSuccMarginChangeCloseMarginServiceImpl =
        new WEB3ToSuccMarginChangeCloseMarginServiceImplInner();

    public class WEB3ToSuccMarginCloseMarginServiceImplInner extends WEB3ToSuccMarginCloseMarginServiceImpl
    {
        protected WEB3SuccMarginCloseConfirmResponse validateOrder(
                WEB3SuccMarginCloseConfirmRequest l_request) throws WEB3BaseException 
        {
            WEB3SuccMarginCloseConfirmResponse l_response = new WEB3SuccMarginCloseConfirmResponse();
            l_response.expirationDate = WEB3DateUtility.getDate("20040130","yyyyMMdd");
            return l_response;
        }

        protected WEB3SuccMarginCloseCompleteResponse submitOrder(
                WEB3SuccMarginCloseCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3SuccMarginCloseCompleteResponse l_response = new WEB3SuccMarginCloseCompleteResponse();
            l_response.expirationDate = WEB3DateUtility.getDate("20040230","yyyyMMdd");
            return l_response;
        }
    }
    public class WEB3ToSuccMarginChangeCloseMarginServiceImplInner
        extends WEB3ToSuccMarginCloseMarginServiceImplInner
    {

        /**
         * (validate����)<BR>
         * �M�p����̒����ԍϔ����R�����s���B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�i�A���j�M�p��������ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
         * @@param l_request - (���N�G�X�g�f�[�^)<BR>
         * �i�A���j�M�p������������ԍϊm�F���N�G�X�g�I�u�W�F�N�g�B<BR>
         * @@return WEB3SuccMarginCloseChangeConfirmResponse
         * @@throws WEB3BaseException
         * @@roseuid 433D001803C2
         */
        protected WEB3SuccMarginCloseChangeConfirmResponse validateOrder(
            WEB3SuccMarginCloseChangeConfirmRequest l_request)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginCloseChangeConfirmRequest)";
            log.entering(STR_METHOD_NAME);

            //1.1 validate( )
            l_request.validate();

            //1.2 get�����\�񒍕��P��(long)
            WEB3ToSuccOrderManagerImpl l_toOrderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
            try
            {
                long l_lngOrderId = Long.parseLong(l_request.id);
                l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //1.3 validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �����\�񒍕��P��Impl)
            this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);

            //1.4 validate�����\���( )
            l_orderUnit.validateOrderForChangeability();

            //1.5 validate���ύό���(�����\�񒍕��P��Impl)
            this.validateClosedMarginContract(l_orderUnit);

            //1.6 create�m�F���N�G�X�g(�i�A���j�M�p������������ԍϊm�F���N�G�X�g, �����\�񒍕��P��Impl)
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = this.createConfirmRequest(l_request, l_orderUnit);

            //1.7 validate����(�i�A���j�M�p����ԍϒ����m�F���N�G�X�g)
            WEB3SuccMarginCloseConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

            //1.8 createResponse( )
            WEB3SuccMarginCloseChangeConfirmResponse l_response =
                (WEB3SuccMarginCloseChangeConfirmResponse)l_request.createResponse();

            //1.9 (*)�v���p�e�B�Z�b�g
            //�i*�j���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
            // �m�F���������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.checkDate = l_confirmResponse.checkDate;

            //�T�Z��n����F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.estimatedPrice = l_confirmResponse.estimatedPrice;

            //����I���x���s��R�[�h�ꗗ�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.messageSuspension = l_confirmResponse.messageSuspension;

            //�������׈ꗗ�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.contractUnits = l_confirmResponse.contractUnits;

            //�萔�����F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.commissionInfo = l_confirmResponse.commissionInfo;

            //�m�F���P���F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.checkPrice = l_confirmResponse.checkPrice;

            //�C���T�C�_�[�x���\���t���O�F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.insiderWarningFlag = l_confirmResponse.insiderWarningFlag;

            //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.expirationDate = l_confirmResponse.expirationDate;

            //������P��
            if (l_confirmRequest.priceAdjustmentValueInfo != null)
            {
                l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        /**
         * (submit����)<BR>
         * �M�p����̒����ԍϒ�����o�^����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�i�A���j�M�p��������ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
         * @@param l_request - (���N�G�X�g�f�[�^)<BR>
         * �i�A���j�M�p������������ԍϊ������N�G�X�g�I�u�W�F�N�g
         * @@return WEB3SuccMarginCloseChangeCompleteResponse
         * @@throws WEB3BaseException
         * @@roseuid 433D001803E1
         */
        protected WEB3SuccMarginCloseChangeCompleteResponse submitOrder(
            WEB3SuccMarginCloseChangeCompleteRequest l_request)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginCloseChangeCompleteRequest)";
            log.entering(STR_METHOD_NAME);

            //1.1 validate( )
            l_request.validate();

            //1.2 get�����\�񒍕��P��(long)
            WEB3ToSuccOrderManagerImpl l_toOrderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
            try
            {
                long l_lngOrderId = Long.parseLong(l_request.id);
                l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //1.3 validate���N�G�X�g�f�[�^at���Ύ��(WEB3GenRequest, �����\�񒍕��P��Impl)
            this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);

            //1.4 validate�����\���( )
            l_orderUnit.validateOrderForChangeability();

            //1.5 validate���ύό���(�����\�񒍕��P��Impl)
            this.validateClosedMarginContract(l_orderUnit);

            //1.6 create�������N�G�X�g(�i�A���j�M�p������������ԍϊ������N�G�X�g, �����\�񒍕��P��Impl)
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = this.createCompleteRequest(
                l_request, l_orderUnit);

            //1.7 submit����(�i�A���j�M�p����ԍϒ����������N�G�X�g)
            WEB3SuccMarginCloseCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

            //1.8 create���N�G�X�g�A�_�v�^(WEB3GenRequest)
            WEB3MarginCloseMarginRequestAdapter l_adapter = this.createRequestAdapter(l_completeRequest);

            //1.9 create���ό����G���g��(�M�p������ό�������[], �M�p����ԍσ��N�G�X�g�A�_�v�^)
            EqTypeSettleContractOrderEntry[] l_contractOrderEntrys =
                this.createClosingContractEntry(l_request.closeMarginContractUnits, l_adapter);

            //1.10 create�����\��ԍϒ����������e(����ID : long, ���ό����G���g�� : EqTypeSettleContractOrderEntry[],
            //     �����㊔�� : double, ������w�l : double, ������T�Z��n��� : double, ������v�Z�P�� : double,
            //     �����㒍�������� : Date, ������is�o����܂Œ��� : boolean, �㗝���͎� : ����, ������P�������l : Double)
            double l_dbModifiedOrderQuantity = 0.0D;
            double l_dblModifiedCalcUnitPrice = 0.0D;
            Date l_datModifiedExpirationDate = l_request.checkDate;
            boolean l_blnModifiedIsCarriedOrder = false;
            Double l_dblModifiedPriceAdjustValue = null;

            if (l_request.orderQuantity != null)
            {
                //���N�G�X�g�f�[�^�D�������� != null�̏ꍇ�A�����㊔���F���N�G�X�g�f�[�^�D��������
                l_dbModifiedOrderQuantity = Double.parseDouble(l_request.orderQuantity);
            }
            else
            {
                //�ȊO�A�����㊔���Fcreate���ό����G���g��()�̖߂�l�D������SUM�l
                for (int i = 0; i < l_contractOrderEntrys.length; i++)
                {
                    l_dbModifiedOrderQuantity += l_contractOrderEntrys[i].getQuantity();
                }
            }

            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                l_dblModifiedCalcUnitPrice = 0;
            }
            else
            {
                l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.limitPrice);
            }

            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_datModifiedExpirationDate = l_request.expirationDate;
                l_blnModifiedIsCarriedOrder = true;
            }

            if (null != l_request.priceAdjustmentValueInfo)
            {
                l_dblModifiedPriceAdjustValue = new Double(
                    l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
            }

            WEB3ToSuccMarginChangeSettleContractOrderSpec l_orderSpec =
                WEB3ToSuccMarginChangeSettleContractOrderSpec.createMarginChangeSettleContractOrderSpec(
                    l_orderUnit.getOrderId(),
                    l_contractOrderEntrys,
                    l_dbModifiedOrderQuantity,
                    l_dblModifiedCalcUnitPrice,
                    Double.parseDouble(l_request.estimatedPrice),
                    Double.parseDouble(l_request.checkPrice),
                    l_datModifiedExpirationDate,
                    l_blnModifiedIsCarriedOrder,
                    (WEB3GentradeTrader) this.getTrader(),
                    l_dblModifiedPriceAdjustValue);

            //1.11 submit���������\��ԍϒ���(�⏕���� : SubAccount, �\��ԍϒ����������e : �����\��ԍϒ����������e,
            //     ����p�X���[�h : String, �����O�\�񒍕��P�� : �����\�񒍕��P��Impl)
            l_toOrderManager.submitEqtypeChangeSettleContractOrder(
                this.getSubAccount(),
                l_orderSpec,
                l_request.password,
                l_orderUnit);

            //1.12 createResponse( )
            WEB3SuccMarginCloseChangeCompleteResponse l_response =
                (WEB3SuccMarginCloseChangeCompleteResponse)l_request.createResponse();

            //1.13 (*)�v���p�e�B�Z�b�g
            //�i*�j���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //  �X�V���ԁF�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;

            // ���ʔԍ��F�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.orderActionId = l_completeResponse.orderActionId;

            // �C���T�C�_�[�x���\���t���O�F�@@super�N���X��submit����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.insiderWarningFlag = l_completeResponse.insiderWarningFlag;

            // �A�������ݒ�t���O�F�@@false�i�Œ�j
            l_response.succSettingFlag = false;

            //�����L�������F�@@super�N���X��validate����()�̃��X�|���X�̓����v���p�e�B���Z�b�g
            l_response.expirationDate = l_completeResponse.expirationDate;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        protected WEB3SuccMarginCloseCompleteRequest createCompleteRequest(
                WEB3SuccMarginCloseChangeCompleteRequest l_request,
                WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit)
        {
            WEB3SuccMarginCloseCompleteRequest l_succMarginCloseCompleteRequest =
                new WEB3SuccMarginCloseCompleteRequest();
            l_succMarginCloseCompleteRequest.orderPriceDiv = "0";
            l_succMarginCloseCompleteRequest.limitPrice = null;
            l_succMarginCloseCompleteRequest.priceCondType = "0";
            l_succMarginCloseCompleteRequest.execCondType = "1";
            l_succMarginCloseCompleteRequest.expirationDateType = "1";
            l_succMarginCloseCompleteRequest.expirationDate = null;
            l_succMarginCloseCompleteRequest.orderCondType = "0";
            l_succMarginCloseCompleteRequest.stopOrderCondPrice = null;
            l_succMarginCloseCompleteRequest.stopOrderCondOperator = null;
            l_succMarginCloseCompleteRequest.wlimitOrderCondPrice = null;
            l_succMarginCloseCompleteRequest.wlimitOrderCondOperator = null;
            l_succMarginCloseCompleteRequest.wLimitOrderPriceDiv = null;
            l_succMarginCloseCompleteRequest.wLimitPrice = null;
            l_succMarginCloseCompleteRequest.wlimitExecCondType = null;
            l_succMarginCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            l_succMarginCloseCompleteRequest.succCommonInfo.parentOrderId = "123";
            l_succMarginCloseCompleteRequest.succCommonInfo.succTradingType = "08";
            WEB3MarginCloseMarginContractUnit[] l_WEB3MarginCloseMarginContractUnits =
                new WEB3MarginCloseMarginContractUnit[1];
            l_WEB3MarginCloseMarginContractUnits[0] = new WEB3MarginCloseMarginContractUnit();
            l_WEB3MarginCloseMarginContractUnits[0].id = "123";
            l_WEB3MarginCloseMarginContractUnits[0].settlePriority = "123";
            l_WEB3MarginCloseMarginContractUnits[0].orderQuantity = "123";
            l_succMarginCloseCompleteRequest.closeMarginContractUnits = l_WEB3MarginCloseMarginContractUnits;
            l_succMarginCloseCompleteRequest.closingOrder = "0";
            l_succMarginCloseCompleteRequest.orderQuantity = "1";
            return l_succMarginCloseCompleteRequest;
        }

        protected void validateRequestDataAtReversingTrade(
            WEB3GenRequest l_request, 
            WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) 
            throws WEB3BaseException
        {

        }
        protected void validateClosedMarginContract(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) 
            throws WEB3BaseException
        {

        }

        protected WEB3SuccMarginCloseConfirmRequest createConfirmRequest(
            WEB3SuccMarginCloseChangeConfirmRequest l_request,
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit)
        {
            WEB3SuccMarginCloseConfirmRequest l_succMarginCloseConfirmRequest =
                new WEB3SuccMarginCloseConfirmRequest();
            l_succMarginCloseConfirmRequest.orderPriceDiv = "0";
            l_succMarginCloseConfirmRequest.limitPrice = null;
            l_succMarginCloseConfirmRequest.priceCondType = "0";
            l_succMarginCloseConfirmRequest.execCondType = "1";
            l_succMarginCloseConfirmRequest.expirationDateType = "1";
            l_succMarginCloseConfirmRequest.expirationDate = null;
            l_succMarginCloseConfirmRequest.orderCondType = "0";
            l_succMarginCloseConfirmRequest.stopOrderCondPrice = null;
            l_succMarginCloseConfirmRequest.stopOrderCondOperator = null;
            l_succMarginCloseConfirmRequest.wlimitOrderCondPrice = null;
            l_succMarginCloseConfirmRequest.wlimitOrderCondOperator = null;
            l_succMarginCloseConfirmRequest.wLimitOrderPriceDiv = null;
            l_succMarginCloseConfirmRequest.wLimitPrice = null;
            l_succMarginCloseConfirmRequest.wlimitExecCondType = null;
            l_succMarginCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            l_succMarginCloseConfirmRequest.succCommonInfo.parentOrderId = "123";
            l_succMarginCloseConfirmRequest.succCommonInfo.succTradingType = "08";
            WEB3MarginCloseMarginContractUnit[] l_WEB3MarginCloseMarginContractUnits =
                new WEB3MarginCloseMarginContractUnit[1];
            l_WEB3MarginCloseMarginContractUnits[0] = new WEB3MarginCloseMarginContractUnit();
            l_WEB3MarginCloseMarginContractUnits[0].id = "123";
            l_WEB3MarginCloseMarginContractUnits[0].settlePriority = "123";
            l_WEB3MarginCloseMarginContractUnits[0].orderQuantity = "123";
            l_succMarginCloseConfirmRequest.closeMarginContractUnits = l_WEB3MarginCloseMarginContractUnits;
            l_succMarginCloseConfirmRequest.closingOrder = "0";
            l_succMarginCloseConfirmRequest.orderQuantity = "1";
            return l_succMarginCloseConfirmRequest;
        }
    }

    /**
     * <BR>
     */
    public void test_validateOrder_0001()
    {
        final String STR_METHOD_NAME =
            " test_validateOrder_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            WEB3SuccMarginCloseChangeConfirmRequest l_request = new WEB3SuccMarginCloseChangeConfirmRequest();
            l_request.priceAdjustmentValueInfo = null;
            l_request.id = "123";

            TestDBUtility.deleteAll(RsvEqOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);

            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("0");
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            l_subAccountRow.setAccountId(1001L);
            l_subAccountRow.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountRow);

            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(123L);
            l_rsvEqOrderUnitParams.setParentOrderId(123L);
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams.setBizDate("20070101");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            EqtypeOrderUnitParams l_eqtypeOrderUnitRow = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitRow.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitRow.setForcedSettleReasonType(null);
            l_eqtypeOrderUnitRow.setOrderId(123L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitRow);

            WEB3SuccMarginCloseChangeConfirmResponse l_response =
                l_toSuccMarginChangeCloseMarginServiceImpl.validateOrder(l_request);
            assertEquals(WEB3DateUtility.getDate("20040130", "yyyyMMdd"), l_response.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * <BR>
     */
    public void test_submitOrder_0001()
    {
        final String STR_METHOD_NAME =
            " test_submitOrder_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070101","yyyyMMdd"));

            TestDBUtility.deleteAll(RsvEqOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(RsvEqOrderActionParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);

            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123L);
            l_eqtypeContractParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            l_subAccountRow.setAccountId(1001L);
            l_subAccountRow.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountRow);

            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(123L);
            l_rsvEqOrderUnitParams.setParentOrderId(123L);
            l_rsvEqOrderUnitParams.setAccountId(1001L);
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams.setBizDate("20070101");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            EqtypeOrderUnitParams l_eqtypeOrderUnitRow = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitRow.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitRow.setForcedSettleReasonType(null);
            l_eqtypeOrderUnitRow.setOrderId(123L);
            l_eqtypeOrderUnitRow.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitRow);

            WEB3SuccMarginCloseChangeCompleteRequest l_request = new WEB3SuccMarginCloseChangeCompleteRequest();
            l_request.estimatedPrice = "123";
            l_request.checkPrice = "123";
            l_request.id = "123";
            l_request.priceAdjustmentValueInfo = null;
            WEB3MarginCloseMarginContractUnit[] l_marginCloseMarginContractUnits =
                new WEB3MarginCloseMarginContractUnit[1];
            l_marginCloseMarginContractUnits[0] = new WEB3MarginCloseMarginContractUnit();
            l_marginCloseMarginContractUnits[0].id = "123";
            l_marginCloseMarginContractUnits[0].settlePriority = "123";
            l_marginCloseMarginContractUnits[0].orderQuantity = "123";
            l_request.limitPrice = "1.1";
            l_request.closeMarginContractUnits = l_marginCloseMarginContractUnits;
            WEB3SuccMarginCloseChangeCompleteResponse l_response =
                l_toSuccMarginChangeCloseMarginServiceImpl.submitOrder(l_request);
            assertEquals(WEB3DateUtility.getDate("20040230", "yyyyMMdd"), l_response.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@
