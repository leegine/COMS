head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���jOP�ԍϒ����T�[�r�XImpl(WEB3ToSuccOptionSettleContractOrderServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 �k�v�u(���u) �V�K�쐬 ���f��283,321
 Revision History : 2008/04/18 �И���(���u) �d�l�ύX���f��No.330 No.333
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
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
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���jOP�ԍϒ����T�[�r�XImpl)<BR>
 * �i�A���j�I�v�V�����ԍϒ����T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3ToSuccOptionSettleContractOrderServiceImpl
    extends WEB3OptionSettleContractOrderServiceImpl
    implements WEB3ToSuccOptionSettleContractOrderService
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractOrderServiceImpl.class);

    /**
     * @@roseuid 47FDBE4100CB
     */
    public WEB3ToSuccOptionSettleContractOrderServiceImpl()
    {

    }

    /**
     * �i�A���j�����w���I�v�V�����ԍϒ����T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́Asubmit����()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A923070071
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
        //�i�A���j�����w���I�v�V�����ԍϒ����m�F���N�G�X�g
        if (l_request instanceof WEB3SuccOptionsCloseConfirmRequest)
        {
            //this.validate����()
            l_response = this.validateOrder((WEB3SuccOptionsCloseConfirmRequest)l_request);
        }
        //�i�A���j�����w���I�v�V���������ԍϊ������N�G�X�g
        else if (l_request instanceof WEB3SuccOptionsCloseCompleteRequest)
        {
            //this.submit����()
            l_response = this.submitOrder((WEB3SuccOptionsCloseCompleteRequest)l_request);
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
     * �i�A���j�����w���I�v�V�����̕ԍϔ����R�����s���B<BR>
     * <BR>
     * �u�i�i�A���jOP�ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�B<BR>
     * @@return WEB3SuccOptionsCloseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A923070081
     */
    protected WEB3SuccOptionsCloseConfirmResponse validateOrder(
        WEB3SuccOptionsCloseConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsCloseConfirmRequest)";
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

        //�i�A���jOP�ԍϒ������N�G�X�g�A�_�v�^
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)this.createRequestAdapter(l_request);

        //���N�G�X�g�A�_�v�^.is���Δ���()
        boolean l_blnIsReversingOrder = l_adapter.isReversingOrder();
        WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();

        String l_strProductCode = null;
        if (l_blnIsReversingOrder)
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
            WEB3FuturesOptionDivDef.OPTION,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate�A�������ő�ݒ萔
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //validate����
        WEB3SuccOptionsCloseConfirmResponse l_response =
            (WEB3SuccOptionsCloseConfirmResponse)super.validateOrder(l_request);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���j�����w���I�v�V�����̕ԍϒ�����o�^����B<BR>
     * <BR>
     * �u�i�i�A���jOP�ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�B<BR>
     * @@return WEB3SuccOptionsCloseCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A923070090
     */
    protected WEB3SuccOptionsCloseCompleteResponse submitOrder(
        WEB3SuccOptionsCloseCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsCloseCompleteRequest)";
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

        //�i�A���jOP�ԍϒ������N�G�X�g�A�_�v�^
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)this.createRequestAdapter(l_request);

        //���N�G�X�g�A�_�v�^.is���Δ���()
        boolean l_blnIsReversingOrder = l_adapter.isReversingOrder();
        WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();

        String l_strProductCode = null;
        if (l_blnIsReversingOrder)
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
            WEB3FuturesOptionDivDef.OPTION,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate�A�������ő�ݒ萔
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        WEB3SuccOptionsCloseCompleteResponse l_response =
            (WEB3SuccOptionsCloseCompleteResponse)super.submitOrder(l_request);

        //notify�\�񒍕��o�^
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ă��܂�Ȃ����ǂ������`�F�b�N����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�i�A���jOP�ԍσT�[�r�X�jvalidate�����v<BR>
     * �@@�u�i�i�A���jOP�ԍσT�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E0700C0
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
     * �������̏ڍׂ̓V�[�P���X�}�u�i�i�A���jOP�ԍσT�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_lngChildOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E07010F
     */
    protected void notifyRsvOrderRegister(long l_lngChildOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�\�񒍕��P��(long)
        //����ID�F�@@�p�����[�^.�q�����̒���ID
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit = l_orderManager.getReserveIfoOrderUnit(l_lngChildOrderId);

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        l_optionOrderManagerImpl.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �i�A���jOP�ԍϒ������N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)���R�[������B <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3OptionSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E07019B
     */
    protected WEB3OptionSettleContractOrderRequestAdapter createRequestAdapter(
        WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        return WEB3ToSuccOptionSettleContractOrderRequestAdapter.create(l_request);
    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g�����쐬����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ <BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@�A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()���R�[�����A <BR>
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[����] <BR>
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
     * �Q�j�@@�P�j�ȊO�̏ꍇ�A <BR>
     * �@@super.create�ԍό��ʃG���g��()���R�[�����A <BR>
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B <BR>
     * <BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�B<BR>
     * @@param l_closeMarginContractUnits - (�ԍό��ʃI�u�W�F�N�g�̔z��)<BR>
     * �ԍό��ʃI�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E070228
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry("
            + "WEB3OptionSettleContractOrderRequestAdapter,"
            + "WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;

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
     * �P�j�@@�������ʂ����߂�B�@@<BR>
     * �@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ�����"�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p�B<BR>
     * �@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���Ϗ���=="�����_��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�ԍό��ʂ̑S�v�f�̒������ʂ�SUM�l���g�p�B<BR>
     * <BR>
     * �Q�j�@@�P�j�ŋ��߂��������� > ���N�G�X�g�A�_�v�^.�e�����̒����P��.�������ʂ̏ꍇ�A<BR>
     * �@@�@@�u�������ʂ��e�����̒������ʂ𒴉߁v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_03065<BR>
     * <BR>
     * �R�j�@@�Q�j�̏����ɊY�����Ȃ��ꍇ�A�P�j�ŋ��߂��������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�B<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E0702F3
     */
    private double getOrderQuantity(WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantity(WEB3ToSuccOptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        // �������ʂ����߂�B
        double l_dblOrderQuantity = 0.0D;

        if (l_requestAdapter.request instanceof WEB3SuccOptionsCloseConfirmRequest)
        {
            WEB3SuccOptionsCloseConfirmRequest l_request =
                (WEB3SuccOptionsCloseConfirmRequest)l_requestAdapter.request;

            // �擾�������Ϗ�����"�����_��"�̏ꍇ
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_request.closingOrder))
            {
                // ���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p
                l_dblOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
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
        else if (l_requestAdapter.request instanceof WEB3SuccOptionsCloseCompleteRequest)
        {
            WEB3SuccOptionsCloseCompleteRequest l_request =
                (WEB3SuccOptionsCloseCompleteRequest)l_requestAdapter.request;

            // �擾�������Ϗ�����"�����_��"�̏ꍇ
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_request.closingOrder))
            {
                // ���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�������� ���g�p
                l_dblOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
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
     * (validateOP�ԍϒ���)<BR>
     * OP�ԍϒ����̔����R�������{����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@���Δ����̏ꍇ<BR>
     * �@@�i�p�����[�^.���N�G�X�g�A�_�v�^.is���Δ���() == true�j<BR>
     * �@@OP�����}�l�[�W��.validate�ԍϒ���()<BR>
     * �@@���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e <BR>
     * �@@�@@���ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.get����()<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ <BR>
     * �@@super.validateOP�ԍϒ���()���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e <BR>
     * �@@�@@���N�G�X�g�A�_�v�^�F�@@�p�����[�^.���N�G�X�g�A�_�v�^<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����B<BR>
     * @@param l_ifoOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g�B<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�B<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E07038F
     */
    protected NewOrderValidationResult validateOptionsSettleContractOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_ifoOrderSpec,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOptionsSettleContractOrder("
            + "SubAccount,"
            + "IfoSettleContractOrderSpec,"
            + "WEB3OptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;

        NewOrderValidationResult l_result = null;
        //���Δ����̏ꍇ
        if (l_adapter.isReversingOrder())
        {
            //OP�����}�l�[�W��Impl���擾
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            //OP�����}�l�[�W��.validate�ԍϒ���()
            l_result =
                l_orderManager.validateSettleContractOrder(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_ifoOrderSpec,
                    l_adapter.getContract());
        }
        else
        {
            //super.validateOP�ԍϒ���()
            l_result =
                super.validateOptionsSettleContractOrder(l_subAccount, l_ifoOrderSpec, l_requestAdapter);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
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
     * <BR>
     * @@param l_settleContractEntries - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g���B<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�B<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)<BR>
     * �敨OP��������B<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E08037F
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnit(
        SettleContractEntry[] l_settleContractEntries,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoTradedProductImpl l_ifoTradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createContractUnit("
            + "SettleContractEntry[], "
            + "WEB3OptionSettleContractOrderRequestAdapter,"
            + "WEB3IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;

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
                super.createContractUnit(l_settleContractEntries, l_requestAdapter, l_ifoTradedProduct);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }

    /**
     * (submit�ԍϒ���)<BR>
     * OP�ԍϒ�����o�^����B<BR>
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
     * <BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����B<BR>
     * @@param l_orderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔���B<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z���ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CE5E090062
     */
    protected void submitSettleContractOrder(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        WEB3GentradeCommission l_commission,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSettleContractOrder("
            + "WEB3OptionSettleContractOrderRequestAdapter, "
            + "SubAccount,"
            + "IfoSettleContractOrderSpec,"
            + "long,"
            + "WEB3GentradeCommission,"
            + "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;
        WEB3SuccOptionsCloseCompleteRequest l_request =
            (WEB3SuccOptionsCloseCompleteRequest)l_adapter.request;

        //���N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()
        Double l_priceAdjustmentValue = null;
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_priceAdjustmentValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        //�A�������}�l�[�W��Impl.submit�敨OP�ԍϐV�K�\�񒍕�
        l_orderManager.submitIfoCloseContractNewOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_orderSpec,
            l_lngOrderId,
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
     * <BR>
     * @@param l_requestAdapter - (OP�ԍϒ������N�G�X�g�A�_�v�^)<BR>
     * OP�ԍϒ������N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�B<BR>
     * @@roseuid 47CE5E090208
     */
    protected void setPrice(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setPrice("
            + "WEB3OptionSettleContractOrderRequestAdapter,"
            + "WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;
        WEB3SuccOptionsCloseConfirmRequest l_request =
            (WEB3SuccOptionsCloseConfirmRequest)l_adapter.request;

        //���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ
        if (l_request.priceAdjustmentValueInfo != null)
        {
            WEB3SuccOptionsCloseConfirmResponse l_confirmResponse =
                (WEB3SuccOptionsCloseConfirmResponse)l_response;
            l_confirmResponse.afterAdjustmentPrice = WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
    }

    /**
     * (exec�]�͍Čv�Z)<BR>
     * �]�͍Čv�Z���s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@roseuid 47CF38FD0220
     */
    public void execReCalcTradingPower(SubAccount l_subAccount)
    {
        return;
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
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDayTradeType(SettleContractEntry[], WEB3OptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_requestAdapter;
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
