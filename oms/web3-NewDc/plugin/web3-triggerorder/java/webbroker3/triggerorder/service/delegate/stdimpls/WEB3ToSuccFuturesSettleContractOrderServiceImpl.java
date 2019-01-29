head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�敨�ԍϒ����T�[�r�XImpl�iWEB3ToSuccFuturesSettleContractOrderServiceImpl.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/18 �И���(���u) �V�K�쐬���f��No.259
 Revision History : 2008/04/18 �И���(���u) �d�l�ύX���f��No.330 No.333
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3ContractCheckDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�敨�ԍϒ����T�[�r�XImpl)<BR>
 * �i�A���j�敨�ԍϒ����T�[�r�X�����N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractOrderServiceImpl
    extends WEB3FuturesSettleContractOrderServiceImpl
    implements WEB3ToSuccFuturesSettleContractOrderService
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractOrderServiceImpl.class);

    /**
     * @@roseuid 47D6593403C9
     */
    public WEB3ToSuccFuturesSettleContractOrderServiceImpl()
    {

    }

    /**
     * �i�A���j�敨�ԍσT�[�r�X���������{����B<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �@@�@@[�i�A���j�����w���敨�ԍϒ����m�F���N�G�X�g�̏ꍇ]<BR>
     * �@@�@@�@@this.validate����()���R�[������B<BR>
     * �@@�@@[�i�A���j�����w���敨�ԍϒ����������N�G�X�g�̏ꍇ]<BR>
     * �@@�@@�@@this.submit����()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94DBA028A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        //�i�A���j�����w���敨�ԍϒ����m�F���N�G�X�g
        if (l_request instanceof WEB3SuccFuturesCloseConfirmRequest)
        {
            //this.validate����()
            l_response = this.validateOrder((WEB3SuccFuturesCloseConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccFuturesCloseCompleteRequest)
        {
            //this.submit����()
            l_response = this.submitOrder((WEB3SuccFuturesCloseCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s��");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����)<BR>
     * �i�A���j�敨�ԍς̔����R�����s���B<BR>
     * <BR>
     * �u�i�i�A���j�敨�ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccFuturesCloseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94DBA02A9
     */
    protected WEB3SuccFuturesCloseConfirmResponse validateOrder(WEB3SuccFuturesCloseConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccFuturesCloseConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�e�����̒����P��(long)
        //�i�e�����j����ID�F�@@���N�G�X�g.�A���������ʏ��̓�����
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_request.succCommonInfo.parentOrderId));
        //get�⏕����
        SubAccount l_subAccount = this.getSubAccount();

        //�i�A���j�敨�ԍϒ������N�G�X�g�A�_�v�^
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)this.createRequestAdapter(l_request);

        //���N�G�X�g�A�_�v�^.is���Δ���()
        boolean l_blnReversingOrder = l_adapter.isReversingOrder();
        WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();

        String l_strProductCode = null;
        if (l_blnReversingOrder)
        {
            //get�敨OP�e�����̒����P��()�̖߂�l.����ID�ɊY������敨OP����.�����Y�����R�[�h
            l_strProductCode = l_product.getUnderlyingProductCode();
        }
        else
        {
            //���N�G�X�g�A�_�v�^.get����()�̖߂�l.����ID�ɊY������敨OP����.�����Y�����R�[�h
            WEB3IfoContractImpl l_ifoContractImpl = l_adapter.getContract();
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoContractImpl.getProduct();
            l_strProductCode = l_ifoProduct.getUnderlyingProductCode();
        }
        //reset�����R�[�h
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //validate�A������
        l_orderManager.validateSuccOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate�A�������ő�ݒ萔
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //validate����
        WEB3SuccFuturesCloseConfirmResponse l_response =
            (WEB3SuccFuturesCloseConfirmResponse)super.validateOrder(l_request);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���j�敨�ԍς̒�����o�^����B<BR>
     * <BR>
     * �u�i�i�A���j�敨�ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccFuturesCloseCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94DBA02B9
     */
    protected WEB3SuccFuturesCloseCompleteResponse submitOrder(WEB3SuccFuturesCloseCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccFuturesCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�e�����̒����P��(long)
        //�i�e�����j����ID�F�@@���N�G�X�g.�A���������ʏ��̓�����
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_request.succCommonInfo.parentOrderId));
        //get�⏕����
        SubAccount l_subAccount = this.getSubAccount();

        //�i�A���j�敨�ԍϒ������N�G�X�g�A�_�v�^
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)this.createRequestAdapter(l_request);

        //���N�G�X�g�A�_�v�^.is���Δ���()
        boolean l_blnReversingOrder = l_adapter.isReversingOrder();
        WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();

        String l_strProductCode = null;
        if (l_blnReversingOrder)
        {
            //get�敨OP�e�����̒����P��()�̖߂�l.����ID�ɊY������敨OP����.�����Y�����R�[�h
            l_strProductCode = l_product.getUnderlyingProductCode();
        }
        else
        {
            //���N�G�X�g�A�_�v�^.get����()�̖߂�l.����ID�ɊY������敨OP����.�����Y�����R�[�h
            WEB3IfoContractImpl l_ifoContractImpl = l_adapter.getContract();
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoContractImpl.getProduct();
            l_strProductCode = l_ifoProduct.getUnderlyingProductCode();
        }
        //reset�����R�[�h
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //validate�A������
        l_orderManager.validateSuccOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate�A�������ő�ݒ萔
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        WEB3SuccFuturesCloseCompleteResponse l_response =
            (WEB3SuccFuturesCloseCompleteResponse)super.submitOrder(l_request);

        //notify�\�񒍕��o�^
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ă��܂�Ȃ����ǂ������`�F�b�N����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�i�A���j�敨�ԍσT�[�r�X�jvalidate�����v<BR>
     * �u�i�i�A���j�敨�ԍσT�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BB83FE0222
     */
    protected void validateSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSuccOrderMaxQuantity(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        //�A�������}�l�[�W��.validate�A�������ő�ݒ萔
        l_orderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify�\�񒍕��o�^)<BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�i�A���j�敨�ԍσT�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_lngRsvOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BB8DD7008C
     */
    protected void notifyRsvOrderRegister(long l_lngRsvOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�\�񒍕��P��(long)
        //�i�e�����j����ID�F�@@���N�G�X�g.�A���������ʏ��̓�����
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit = l_orderManager.getReserveIfoOrderUnit(l_lngRsvOrderId);

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        l_optionOrderManagerImpl.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �i�A���j�敨�ԍϒ������N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3FuturesSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47BBD11302B9
     */
    protected WEB3FuturesSettleContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        return WEB3ToSuccFuturesSettleContractOrderRequestAdapter.create(l_request);
    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g�����쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�ԍό��ʁF�@@���������ԍό���(*1)<BR>
     * <BR>
     * �@@(*1)�ȉ��̃v���p�e�B���Z�b�g�����ԍό��ʃC���X�^���X<BR>
     * �@@�@@�݂̂�v�f�Ƃ���z��<BR>
     * <BR>
     * �@@�@@�ԍό���.�������ʁF�@@this.get��������()�̖߂�l��ݒ肷��B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���N�G�X�g�A�_�v�^�F�@@�p�����[�^.���N�G�X�g�A�_�v�^<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ�A<BR>
     * �@@super.create�ԍό��ʃG���g��()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_closeMarginContractUnits - (�ԍό���)<BR>
     * �ԍό��ʃI�u�W�F�N�g�̔z��<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 47BCD8C50210
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry("
            + "WEB3FuturesSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

        SettleContractEntry[] l_settleContractEntries = null;

        // ���Δ����̏ꍇ
        if (l_adapter.isReversingOrder())
        {
            // this.get��������()�̖߂�l��ݒ肷��B
            double l_dblOrderQuantity = this.getOrderQuantity(l_adapter);

            int l_intLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_closeMarginContractUnits[i].contractOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
            }

            // �A�������}�l�[�W��Impl���擾
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            // �A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()
            l_settleContractEntries =
                l_orderManager.createSettleContractEntry(l_closeMarginContractUnits);
        }
        // �ȊO�̏ꍇ
        else
        {
            l_settleContractEntries =
                super.createSettleContractEntry(l_requestAdapter, l_closeMarginContractUnits);
        }

        log.exiting(STR_METHOD_NAME);
        return l_settleContractEntries;

    }

    /**
     * (get��������)<BR>
     * �ԍό��ʂɐݒ肷�钍�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@�������ʂ����߂�B<BR>
     * �@@�@@�擾�������Ϗ�����"�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p�B<BR>
     * �@@�@@�擾�������Ϗ���=="�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�ԍό��ʂ̑S�v�f�̒������ʂ�SUM�l���g�p�B<BR>
     * <BR>
     * �Q�j�@@�P�j�ŋ��߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ�A<BR>
     * �@@�@@�u�������ʂ��e�����̒������ʂ𒴉߁v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_03065<BR>
     * <BR>
     * �R�j�@@�Q�j�̏����ɊY�����Ȃ��ꍇ�A�P�j�ŋ��߂��������ʂ�ԋp����B<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47BD406F02FD
     */
    private double getOrderQuantity(WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantity(WEB3ToSuccFuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        // �������ʂ����߂�B
        double l_dblOrderQuantity = 0.0D;

        if (l_requestAdapter.request instanceof WEB3SuccFuturesCloseConfirmRequest)
        {
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)l_requestAdapter.request;

            // �擾�������Ϗ�����"�����_��"�̏ꍇ
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_request.closingOrder))
            {
                // ���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p
                l_dblOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }
            // �擾�������Ϗ���=="�����_��"�̏ꍇ
            else
            {
                BigDecimal l_bdOrderQuantitySum = new BigDecimal("0");

                int l_intLength = l_request.closeMarginContractUnits.length;
                // ���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�ԍό��ʂ̑S�v�f�̒������ʂ�SUM�l���g�p�B
                for (int i = 0; i < l_intLength; i++)
                {
                    String l_strOrderQuantity = l_request.closeMarginContractUnits[i].contractOrderQuantity;
                    l_bdOrderQuantitySum = l_bdOrderQuantitySum.add(new BigDecimal(l_strOrderQuantity));
                }

                l_dblOrderQuantity = l_bdOrderQuantitySum.doubleValue();
            }
        }
        else if (l_requestAdapter.request instanceof WEB3SuccFuturesCloseCompleteRequest)
        {
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)l_requestAdapter.request;

            // �擾�������Ϗ�����"�����_��"�̏ꍇ
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_request.closingOrder))
            {
                // ���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p
                l_dblOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }
            // �擾�������Ϗ���=="�����_��"�̏ꍇ
            else
            {
                BigDecimal l_bdOrderQuantitySum = new BigDecimal("0");

                int l_intLength = l_request.closeMarginContractUnits.length;
                // ���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�ԍό��ʂ̑S�v�f�̒������ʂ�SUM�l���g�p�B
                for (int i = 0; i < l_intLength; i++)
                {
                    String l_strOrderQuantity = l_request.closeMarginContractUnits[i].contractOrderQuantity;
                    l_bdOrderQuantitySum = l_bdOrderQuantitySum.add(new BigDecimal(l_strOrderQuantity));
                }

                l_dblOrderQuantity = l_bdOrderQuantitySum.doubleValue();
            }
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s��");
        }

        // ���߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ
        if (l_dblOrderQuantity > l_requestAdapter.parentOrderUnit.getQuantity())
        {
            log.debug("�������ʂ��e�����̒������ʂ𒴉߂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03065,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ��e�����̒������ʂ𒴉߂��Ă��܂��B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblOrderQuantity;
    }

    /**
     * (validate�敨�ԍϒ���)<BR>
     * �敨�ԍϒ����̔����R�������{����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�敨�����}�l�[�W��.validate�敨�ԍϒ���()<BR>
     * �@@���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e <BR>
     * �@@�@@���ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.get����()<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ <BR>
     * �@@super.validate�敨�ԍϒ���()���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e <BR>
     * �@@�@@���N�G�X�g�A�_�v�^�F�@@�p�����[�^.���N�G�X�g�A�_�v�^<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_settleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 47BEA46A0104
     */
    protected NewOrderValidationResult validateFuturesSettleContractOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFuturesSettleContractOrder("
            + "SubAccount, WEB3IfoSettleContractOrderSpec, WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

        NewOrderValidationResult l_result = null;
        //���Δ����̏ꍇ
        if (l_adapter.isReversingOrder())
        {
            //�敨�����}�l�[�W��Impl���擾
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            //�敨�����}�l�[�W��.validate�敨�ԍϒ���()
            l_result =
                l_orderManager.validateFuturesSettleContractOrder(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_settleContractOrderSpec,
                    l_adapter.getContract());
        }
        else
        {
            //super.validate�敨�ԍϒ���()
            l_result =
                super.validateFuturesSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_requestAdapter);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * (get�T�Z���ϑ��v)<BR>
     * �T�Z���ϑ��v���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ�A<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�ȉ��̎菇�ɂČ��ʂ��擾����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�A�������}�l�[�W��Impl.create����()��<BR>
     * �@@�@@�R�[�����A���ʂ��쐬����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�敨�����}�l�[�W��.calc�T�Z���ϑ��v()���R�[�����A<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�萔���F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�w�l�F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�敨OP��������F�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�ԍό��ʃG���g���F �@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@���ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�@@�@@�����F <BR>
     * �@@�@@�@@�@@[�擾��������.isLong() == true�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@SideEnum.BUY<BR>
     * �@@�@@�@@�@@[�ȊO]<BR>
     * �@@�@@�@@�@@�@@SideEnum.SELL<BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F  false<BR>
     * �@@�@@�@@�敨OP���ʁF�@@�P�|�P�j�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.get�T�Z���ϑ��v()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_genCommission - (�萔��)<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * <BR>
     * 0���w�肳�ꂽ�ꍇ�́A�������v�Z�P���Ƃ��ė��p����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_tradedProduct - (�敨OP�������)<BR>
     * �敨OP�������<BR>
     * @@param l_settleContractEntrys - (�ԍό��ʃG���g��[])<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 47C21FF50205
     */
    protected WEB3IfoEstimateDeliveryAmountCalcResult getEstimateSettlementIncome(
        WEB3GentradeCommission l_genCommission,
        double l_dblLimitPrice,
        SubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_tradedProduct,
        SettleContractEntry[] l_settleContractEntrys,
        double l_dblQuantity,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEstimateSettlementIncome("
            + "WEB3GentradeCommission,"
            + "double,"
            + "SubAccount,"
            + "WEB3IfoTradedProductImpl,"
            + "SettleContractEntry[], "
            + "double,"
            + "WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

        WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult = null;
        //���Δ����̏ꍇ
        if (l_adapter.isReversingOrder())
        {
            // �A�������}�l�[�W��Impl���擾
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            // �A�������}�l�[�W��Impl.create����()
            WEB3IfoContractImpl l_ifoContractImpl =
                l_orderManager.createIfoContract(l_adapter.parentOrderUnit);

            SideEnum l_sideEnum = null;
            //�擾��������.isLong() == true�̏ꍇ
            if (l_ifoContractImpl.isLong())
            {
                l_sideEnum = SideEnum.BUY;
            }
            else
            {
                l_sideEnum = SideEnum.SELL;
            }

            //�敨�����}�l�[�W��Impl���擾
            WEB3FuturesOrderManagerImpl l_futuresOrderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            //�敨�����}�l�[�W��.calc�T�Z���ϑ��v()
            l_amountCalcResult =
                l_futuresOrderManager.calcEstimateSettlementIncome(
                l_genCommission,
                l_dblLimitPrice,
                (WEB3GentradeSubAccount)l_subAccount,
                l_tradedProduct,
                l_settleContractEntrys,
                l_dblQuantity,
                l_sideEnum,
                false,
                l_ifoContractImpl);
        }
        else
        {
            //super.get�T�Z���ϑ��v()
            l_amountCalcResult =
                super.getEstimateSettlementIncome(
                    l_genCommission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_tradedProduct,
                    l_settleContractEntrys,
                    l_dblQuantity,
                    l_requestAdapter);
        }

        log.exiting(STR_METHOD_NAME);
        return l_amountCalcResult;
    }

    /**
     * (create���ʖ���)<BR>
     * �ԍό��ʃG���g����茚�ʖ��ׂ̈ꗗ���쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ�A<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�ȉ��̎菇�ɂČ��ʖ��ׂ��쐬����B<BR>
     * �@@�P�|�P�j�@@�A�������}�l�[�W��Impl.create���ʖ���()��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�v���p�e�B�Z�b�g�����C���X�^���X�݂̂�v�f�Ƃ���<BR>
     * �@@�@@�z��𐶐����A�ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.create���ʖ���()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * @@param l_settleContractEntrys - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_tradedProduct - (�敨OP�������)<BR>
     * �敨OP�������<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47C24E7F02BD
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnit(
        SettleContractEntry[] l_settleContractEntrys,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoTradedProductImpl l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createContractUnit("
            + "SettleContractEntry[], "
            + "WEB3FuturesSettleContractOrderRequestAdapter "
            + "WEB3IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;

        WEB3FuturesOptionsContractUnit[] l_contractUnits = null;
        if (l_adapter.isReversingOrder())
        {
            // �A�������}�l�[�W��Impl���擾
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            //�A�������}�l�[�W��Impl.create���ʖ���()
            WEB3FuturesOptionsContractUnit l_contractUnit =
                l_orderManager.createContractUnit(l_adapter.parentOrderUnit);

            l_contractUnits = new WEB3FuturesOptionsContractUnit[]{l_contractUnit};
        }
        else
        {
            //super.create���ʖ���()
            l_contractUnits =
                super.createContractUnit(l_settleContractEntrys, l_requestAdapter, l_tradedProduct);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }

    /**
     * (submit�ԍϒ���)<BR>
     * �敨�ԍϒ�����o�^����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.submit�敨OP�ԍϐV�K�\�񒍕�()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * �⏕�����F�@@�����̕⏕����<BR>
     * �������e�F�@@�����̕ԍϒ������e<BR>
     * ����ID�F�@@�����̒���ID<BR>
     * ����p�X���[�h�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�Ïؔԍ�<BR>
     * �A����������敪�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�A���������ʏ��.�A����������敪<BR>
     * �P�������l�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()<BR>
     * �@@�@@�������̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���==null�̏ꍇ�́Anull���Z�b�g<BR>
     * �e�����̒����P�ʁF�@@�����̃��N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * �v�Z���ʁF�@@�����̊T�Z��n����v�Z����<BR>
     * ���ʁF�@@�����̃��N�G�X�g�A�_�v�^.get����()<BR>
     * ���Ϗ����F�@@�p�����[�^.���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ���<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_changeSettleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e<BR>
     * @@param l_lngOrderID - (����ID)<BR>
     * ����ID<BR>
     * @@param l_genCommission - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C274BE0035
     */
    protected void submitSettleContractOrder(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_changeSettleContractOrderSpec,
        long l_lngOrderID,
        WEB3GentradeCommission l_genCommission,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSettleContractOrder("
            + "WEB3FuturesSettleContractOrderRequestAdapter, "
            + "SubAccount"
            + "WEB3IfoSettleContractOrderSpec"
            + "long"
            + "WEB3GentradeCommission"
            + "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;
        WEB3SuccFuturesCloseCompleteRequest l_request =
            (WEB3SuccFuturesCloseCompleteRequest)l_adapter.request;

        //���N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()
        Double l_priceAdjustmentValue = null;
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_priceAdjustmentValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        //�A�������}�l�[�W��Impl.submit�敨OP�ԍϐV�K�\�񒍕�
        l_orderManager.submitIfoCloseContractNewOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_changeSettleContractOrderSpec,
            l_lngOrderID,
            l_request.password,
            l_request.succCommonInfo.succTradingType,
            l_priceAdjustmentValue,
            l_adapter.parentOrderUnit,
            l_estimateDeliveryAmountCalcResult,
            l_adapter.getContract(),
            l_request.closingOrder);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�P��)<BR>
     * �����̃��X�|���X�D������P���ɒP����ݒ肷��B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ<BR>
     * �@@�@@���X�|���X�D������P���ɁA�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �Q�j��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�����������^�[������B<BR>
     * @@param l_requestAdapter - (�敨�ԍϒ������N�G�X�g�A�_�v�^)<BR>
     * �敨�ԍϒ������N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C29A3E01B7
     */
    protected void setPrice(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setPrice(WEB3FuturesSettleContractOrderRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;
        WEB3SuccFuturesCloseConfirmRequest l_request =
            (WEB3SuccFuturesCloseConfirmRequest)l_adapter.request;

        //���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ
        if (l_request.priceAdjustmentValueInfo != null)
        {
            WEB3SuccFuturesCloseConfirmResponse l_confirmResponse =
                (WEB3SuccFuturesCloseConfirmResponse)l_response;
            l_confirmResponse.afterAdjustmentPrice = WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get���v��敪)<BR>
     * ���v��敪���擾����B<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ�A<BR>
     * �i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �u���v��v��ԋp����B(*1) <BR>
     * <BR>
     * �Q�j�@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ<BR>
     * �@@super.get���v��敪()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * <BR>
     * (*1) ���v��敪�́A�u�萔��`�C���^�t�F�C�X�FWEB3ContractCheckDef�v���Q��<BR>
     * @@param l_settleContractEntries - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getDayTradeType(SettleContractEntry[] l_settleContractEntries,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDayTradeType(SettleContractEntry[], WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccFuturesSettleContractOrderRequestAdapter)l_requestAdapter;
        String l_strDayTrade = null;

        //�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true
        if (l_adapter.isReversingOrder())
        {
            l_strDayTrade = WEB3ContractCheckDef.DAY_TRADE;
        }
        else
        {
            //super.get���v��敪()
            l_strDayTrade = super.getDayTradeType(l_settleContractEntries, l_requestAdapter);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strDayTrade;
    }
}
@
