head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���jOP�ԍϒ������N�G�X�g�A�_�v�^(WEB3ToSuccOptionSettleContractOrderRequestAdapter.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 �k�v�u(���u) �V�K�쐬 ���f��283
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�ԍϒ������N�G�X�g�A�_�v�^)<BR>
 * �i�A���j�I�v�V�����ԍϒ������N�G�X�g�A�_�v�^�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3ToSuccOptionSettleContractOrderRequestAdapter extends WEB3OptionSettleContractOrderRequestAdapter
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractOrderRequestAdapter.class);

    /**
     * (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g<BR>
     */
    public IfoOrderUnit parentOrderUnit;

    /**
     * (�P��)<BR>
     * �P���B<BR>
     * �i�w�l�^0�i�����s�j�^���s�P���i�}�w�l�j�j<BR>
     * �����s�����̏ꍇ�̊T�Z��n����v�Z�Ɏg�p���������́A<BR>
     * ���T�Z��n����v�Z����.get�v�Z�P��()�Ŏ擾����B<BR>
     */
    public double price;

    /**
     * @@roseuid 47FDBE3F01F4
     */
    public WEB3ToSuccOptionSettleContractOrderRequestAdapter()
    {

    }

    /**
     * �i�A���jOP�ԍϒ������N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�����B<BR>
     * �Q�j�@@�e�����̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�A�������}�l�[�W��.get�敨OP�e�����̒����P��(<BR>
     * �@@�@@���N�G�X�g.�A���������ʏ��.�i�e�����j����ID)���R�[������B<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X�ɁA�����̃��N�G�X�g�A<BR>
     * �@@�@@�y�ю擾�����e�����̒����P�ʃI�u�W�F�N�g���Z�b�g����B<BR>
     * �@@�@@�v���p�e�B�̒P���ɂ�0���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^�I�u�W�F�N�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3OptionCloseContractOrderRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47CE5D960146
     */
    public static WEB3OptionSettleContractOrderRequestAdapter create(
        WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�{�C���X�^���X�𐶐�����B
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_settleContractOrderResquestAdapter =
            new WEB3ToSuccOptionSettleContractOrderRequestAdapter();

        //���N�G�X�g.�A���������ʏ��.�i�e�����j����ID
        String l_strID = null;
        if (l_request instanceof WEB3SuccOptionsCloseConfirmRequest)
        {
            l_strID = ((WEB3SuccOptionsCloseConfirmRequest)l_request).succCommonInfo.parentOrderId;
        }
        else if (l_request instanceof WEB3SuccOptionsCloseCompleteRequest)
        {
            l_strID = ((WEB3SuccOptionsCloseCompleteRequest)l_request).succCommonInfo.parentOrderId;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccOptionSettleContractOrderRequestAdapter." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        //�e�����̒����P�ʃI�u�W�F�N�g���擾����
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�e�����̒����P��(long)
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_strID));

        l_settleContractOrderResquestAdapter.request = l_request;
        l_settleContractOrderResquestAdapter.parentOrderUnit = l_ifoOrderUnit;
        l_settleContractOrderResquestAdapter.price = 0;

        //�C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderResquestAdapter;
    }

    /**
     * (get����)<BR>
     * ���ʃI�u�W�F�N�g��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@this.is���Δ���() == true�̏ꍇ�A<BR>
     * �@@�A�������}�l�[�W��Impl.create����()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�����P�ʁF�@@this.�e�����̒����P��<BR>
     * <BR>
     * �Q�j�@@�����c�ɑ΂���ԍρi�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.get����()���R�[�����A�߂�l��ԋp����B<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     * @@roseuid 47CE5D9601F1
     */
    public WEB3IfoContractImpl getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract()";
        log.entering(STR_METHOD_NAME);

        //this.is���Δ���() == true
        boolean l_blnReversingOrder = this.isReversingOrder();
        if (l_blnReversingOrder)
        {
            //�A�������}�l�[�W�����擾����
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3IfoContractImpl l_ifoContractImpl = l_orderManager.createIfoContract(this.parentOrderUnit);

            log.exiting(STR_METHOD_NAME);
            return l_ifoContractImpl;
        }

        //super.get����()
        log.exiting(STR_METHOD_NAME);
        return super.getContract();
    }

    /**
     * (is���Δ���)<BR>
     * ���Δ������ǂ������ʂ���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�A���������ʏ��.�A����������敪=="OP�ԍρi�O�񒍕��j"�̏ꍇ�́A<BR>
     * true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 47CE5D96024F
     */
    public boolean isReversingOrder()
    {
        final String STR_METHOD_NAME = "isReversingOrder()";
        log.entering(STR_METHOD_NAME);

        if (super.request instanceof WEB3SuccOptionsCloseConfirmRequest)
        {
            WEB3SuccOptionsCloseConfirmRequest l_request =
                (WEB3SuccOptionsCloseConfirmRequest)super.request;
            //���N�G�X�g�f�[�^.�A���������ʏ��.�A����������敪=="OP�ԍρi�O�񒍕��j"�̏ꍇ
            if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
                l_request.succCommonInfo.succTradingType))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else if (super.request instanceof WEB3SuccOptionsCloseCompleteRequest)
        {
            WEB3SuccOptionsCloseCompleteRequest l_request =
                (WEB3SuccOptionsCloseCompleteRequest)super.request;
            //���N�G�X�g�f�[�^.�A���������ʏ��.�A����������敪=="OP�ԍρi�O�񒍕��j"�̏ꍇ
            if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
                l_request.succCommonInfo.succTradingType))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�P��)<BR>
     * �P�������N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�m�F���N�G�X�g�̏ꍇ<BR>
     * <BR>
     * �@@this.�P�� == 0�̏ꍇ�́A<BR>
     * �@@�A�������}�l�[�W��.get�敨OP�\�񒍕����s�P��()���R�[�����A<BR>
     * �@@�߂�l��this.�P���ɃZ�b�g����B<BR>
     * �@@���e���������s�����̏ꍇ�A��������Ƃ������s�P�����v�Z���Ă��邽�߁A<BR>
     * �@@���������̎����̕ϓ����l�����A���s�P�����L�����Ă����B<BR>
     * <BR>
     * �@@---------------------------------------------------------<BR>
     * �@@�������ݒ�d�l��<BR>
     * <BR>
     * �@@�e�����̒����P�ʁF�@@�e�����̒����P��<BR>
     * �@@�w�l�F�@@super.get�P��()�̖߂�l<BR>
     * �@@�P�������l�F�@@���N�G�X�g�f�[�^.�P�������l���==null�̏ꍇ�́Anull�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A���N�G�X�g�f�[�^.�P�������l���.get�P�������l()�B<BR>
     * �@@�敨OP��������F�@@�e�����̒����P��.getTradedProduct()<BR>
     * �@@---------------------------------------------------------<BR>
     * <BR>
     * �@@this.�P����ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������N�G�X�g�̏ꍇ<BR>
     * <BR>
     * �@@���N�G�X�g.�P�������l���==null�i�w�l�^���s�j�̏ꍇ�́Asuper.get�P��()�̖߂�l��ԋp����B<BR>
     * �@@���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ�́A���N�G�X�g.������P����ԋp����B<BR>
     * �@@��������P����null�̏ꍇ�́A�u���s�P����null�v�̗�O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02707<BR>
     * �@@��������P����0�ȉ��̏ꍇ�́A�u���s�P����0�ȉ��v�̗�O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02298<BR>
     * <BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47CE5D9602AD
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);

        //�m�F���N�G�X�g�̏ꍇ
        if (super.request instanceof WEB3SuccOptionsCloseConfirmRequest)
        {
            WEB3SuccOptionsCloseConfirmRequest l_request =
                (WEB3SuccOptionsCloseConfirmRequest)super.request;

            //this.�P�� == 0�̏ꍇ
            if (GtlUtils.Double.isZero(this.price))
            {
                //�A�������}�l�[�W�����擾����
                WEB3ToSuccOrderManagerImpl l_orderManager =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                Double l_valueInfo = null;
                if (l_request.priceAdjustmentValueInfo != null)
                {
                    l_valueInfo = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
                }

                //�A�������}�l�[�W��.get�敨OP�\�񒍕����s�P��
                this.price =
                    l_orderManager.getReserveIfoOrderExecPrice(
                        this.parentOrderUnit,
                        super.getPrice(),
                        l_valueInfo,
                        (WEB3IfoTradedProductImpl)this.parentOrderUnit.getTradedProduct());
            }
            log.exiting(STR_METHOD_NAME);
            return this.price;
        }
        //�������N�G�X�g�̏ꍇ
        else if (super.request instanceof WEB3SuccOptionsCloseCompleteRequest)
        {
            WEB3SuccOptionsCloseCompleteRequest l_request =
                (WEB3SuccOptionsCloseCompleteRequest)super.request;
            //���N�G�X�g.�P�������l���==null�i�w�l�^���s�j�̏ꍇ�́Asuper.get�P��()�̖߂�l��ԋp����
            if (l_request.priceAdjustmentValueInfo == null)
            {
                return super.getPrice();
            }
            //���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ�́A���N�G�X�g.������P����ԋp����B
            else
            {
                if (l_request.afterAdjustmentPrice == null)
                {
                    log.debug("���s�P����null�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02707,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���s�P����null�B");
                }
                else if (GtlUtils.Double.isZero(Double.parseDouble(l_request.afterAdjustmentPrice))
                    || Double.parseDouble(l_request.afterAdjustmentPrice) < 0)
                {
                    log.debug("���s�P����0�ȉ��ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���s�P����0�ȉ��ł��B");
                }
                log.exiting(STR_METHOD_NAME);
                return Double.parseDouble(l_request.afterAdjustmentPrice);
            }
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�^�C�v�s���B");
        }

    }
}
@
