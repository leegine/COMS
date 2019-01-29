head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�V�K���������N�G�X�g�A�_�v�^(WEB3ToSuccFuturesOpenContractRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 ���n(���u) �V�K�쐬���f��257
Revision History : 2008/03/19 ���n(���u) �d�l�ύX���f��294
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�敨�V�K���������N�G�X�g�A�_�v�^)<BR>
 * �i�A���j�敨�V�K���������N�G�X�g�A�_�v�^�N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccFuturesOpenContractRequestAdapter
    extends WEB3FuturesOpenContractRequestAdapter
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractRequestAdapter.class);

    /**
     * (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B<BR>
     */
    public IfoOrderUnit parentOrderUnit;

    /**
     * (�P��)<BR>
     * �P���B<BR>
     * <BR>
     * �i�w�l�^0�i�����s�j�^���s�P���i�}�w�l�j�j<BR>
     */
    public double price;

    /**
     * @@roseuid 47D6593202BF
     */
    public WEB3ToSuccFuturesOpenContractRequestAdapter()
    {

    }

    /**
     * �i�A���j�敨�V�K���������N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�P�j �{�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�j �A�������}�l�[�W��.get�敨OP�e�����̒����P��()���R�[�����āA<BR>
     * �@@�@@�@@ �e�����̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@ [�����ݒ�d�l]<BR>
     * <BR>
     * �@@�@@�@@�@@ �i�e�����j����ID : ���N�G�X�g.�A���������ʏ��.�i�e�����j����ID<BR>
     * <BR>
     * �@@�R�j ���������C���X�^���X�ɁA�ȉ��̍��ڂ�ݒ肷��B<BR>
     * <BR>
     * �@@�@@�@@ ���N�G�X�g : ����.���N�G�X�g<BR>
     * �@@�@@�@@ �e�����̒����P�� : �Q�j�Ŏ擾�����e�����̒����P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@ �P�� : 0�i�Œ�j<BR>
     * <BR>
     * �@@�S�j �C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3FuturesOpenContractRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47B102A80079
     */

    public static WEB3FuturesOpenContractRequestAdapter create(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�{�C���X�^���X�𐶐�����
        WEB3ToSuccFuturesOpenContractRequestAdapter l_toSuccFuturesOpenContractRequestAdapter =
            new WEB3ToSuccFuturesOpenContractRequestAdapter();

        //�A�������}�l�[�W��.get�敨OP�e�����̒����P��()���R�[�����āA
        //�e�����̒����P�ʃI�u�W�F�N�g���擾����B
        //[�����ݒ�d�l]
        //�i�e�����j����ID : ���N�G�X�g.�A���������ʏ��.�i�e�����j����ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccFuturesOpenConfirmRequest)
        {
            WEB3SuccFuturesOpenConfirmRequest l_succFuturesOpenConfirmRequest =
                (WEB3SuccFuturesOpenConfirmRequest)l_request;
            l_succCommonInfo = l_succFuturesOpenConfirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccFuturesOpenCompleteRequest)
        {
            WEB3SuccFuturesOpenCompleteRequest l_succFuturesOpenCompleteRequest =
                (WEB3SuccFuturesOpenCompleteRequest)l_request;
            l_succCommonInfo = l_succFuturesOpenCompleteRequest.succCommonInfo;
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccFuturesOpenContractRequestAdapter" + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(Long.parseLong(l_succCommonInfo.parentOrderId));

        //���������C���X�^���X�ɁA�ȉ��̍��ڂ�ݒ肷��
        //���N�G�X�g : ����.���N�G�X�g
        //�e�����̒����P�� : �Q�j�Ŏ擾�����e�����̒����P�ʃI�u�W�F�N�g
        //�P�� : 0�i�Œ�j
        l_toSuccFuturesOpenContractRequestAdapter.request = l_request;
        l_toSuccFuturesOpenContractRequestAdapter.parentOrderUnit = l_ifoOrderUnit;
        l_toSuccFuturesOpenContractRequestAdapter.price = 0;

        //�C���X�^���X��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_toSuccFuturesOpenContractRequestAdapter;
    }

    /**
     * (get�P��)<BR>
     * �P����ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �@@�P�j ���N�G�X�g�f�[�^ == �m�F���N�G�X�g�̏ꍇ<BR>
     * <BR>
     * �@@�@@�P�|�P�j this.�P�� == 0�̏ꍇ�́A<BR>
     * �@@�@@�@@�A�������}�l�[�W��.get�敨OP�\�񒍕����s�P��()���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�@@ �߂�l��this.�P���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@ ���e���������s�����̏ꍇ�A��������Ƃ������s�P���Ōv�Z���Ă��邽�߁A<BR>
     * �@@�@@�@@�@@�@@�@@�@@ �������̎����ϓ����l�������s�P�����L�����Ă����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@ [�����ݒ�d�l]<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@ �e�����̒����P�� �F this.�e�����̒����P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@ �w�l �F super.get�P��()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@ �P�������l �F ���N�G�X�g�f�[�^.�P�������l��� == null�̏ꍇ�Anull<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �ȊO�A���N�G�X�g�f�[�^.�P�������l���.get�P�������l()<BR>
     * �@@�@@�@@�@@�@@�@@�@@ �敨OP������� �F �e�����̒����P��.getTradedProduct()<BR>
     * <BR>
     * �@@�@@�P�|�Q�j this.�P����ԋp����B<BR>
     * <BR>
     * �@@�Q�j ���N�G�X�g�f�[�^ == �������N�G�X�g�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@ ���N�G�X�g�f�[�^.�P�������l���==null�i�w�l�^���s�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@super.get�P��()�̖߂�l��ԋp����B<BR>
     * �@@�@@�@@ ���N�G�X�g�f�[�^.�P�������l���null�i�}�w�l�w��j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.������P����ԋp����B<BR>
     * <BR>
     * �@@�@@�@@ ��������P����null�̏ꍇ�A�u���s�P����null�v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02707<BR>
     * �@@�@@�@@ ��������P����0�ȉ��̏ꍇ�A�u���s�P����0�ȉ��v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02298<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47B102A8007B
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPrice()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        //���N�G�X�g�f�[�^ == �m�F���N�G�X�g�̏ꍇ
        if (this.request instanceof WEB3SuccFuturesOpenConfirmRequest)
        {
            //this.�P�� == 0�̏ꍇ�́A�A�������}�l�[�W��.get�敨OP�\�񒍕����s�P��()���R�[�����A
            //�߂�l��this.�P���ɃZ�b�g����
            //���e���������s�����̏ꍇ�A��������Ƃ������s�P���Ōv�Z���Ă��邽�߁A
            //�������̎����ϓ����l�������s�P�����L�����Ă����B
            //[�����ݒ�d�l]
            //�e�����̒����P�� �F this.�e�����̒����P��
            //�w�l �F super.get�P��()�̖߂�l
            //�P�������l �F ���N�G�X�g�f�[�^.�P�������l��� == null�̏ꍇ�Anull
            //�ȊO�A���N�G�X�g�f�[�^.�P�������l���.get�P�������l()
            //�敨OP������� �F �e�����̒����P��.getTradedProduct()
            if (this.price == 0)
            {
                WEB3SuccFuturesOpenConfirmRequest l_succFuturesOpenConfirmRequest =
                    (WEB3SuccFuturesOpenConfirmRequest)this.request;

                if (l_succFuturesOpenConfirmRequest.priceAdjustmentValueInfo == null)
                {
                    this.price = l_orderManager.getReserveIfoOrderExecPrice(
                        this.parentOrderUnit,
                        super.getPrice(),
                        null,
                        (WEB3IfoTradedProductImpl)this.parentOrderUnit.getTradedProduct());
                }
                else
                {
                    this.price = l_orderManager.getReserveIfoOrderExecPrice(
                        this.parentOrderUnit,
                        super.getPrice(),
                        new Double(l_succFuturesOpenConfirmRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue()),
                        (WEB3IfoTradedProductImpl)this.parentOrderUnit.getTradedProduct());
                }
            }

            //this.�P����ԋp����
            log.exiting(STR_METHOD_NAME);
            return this.price;
        }
        //���N�G�X�g�f�[�^ == �������N�G�X�g�̏ꍇ
        else if (this.request instanceof WEB3SuccFuturesOpenCompleteRequest)
        {
            //���N�G�X�g�f�[�^.�P�������l���==null�i�w�l�^���s�j�̏ꍇ�Asuper.get�P��()�̖߂�l��ԋp����
            //���N�G�X�g�f�[�^.�P�������l���null�i�}�w�l�w��j�̏ꍇ�A���N�G�X�g�f�[�^.������P����ԋp����
            //��������P����null�̏ꍇ�A�u���s�P����null�v�̗�O��throw����B
            //��������P����0�ȉ��̏ꍇ�A�u���s�P����0�ȉ��v�̗�O��throw����B
            WEB3SuccFuturesOpenCompleteRequest l_succFuturesOpenCompleteRequest =
                (WEB3SuccFuturesOpenCompleteRequest)this.request;

            if (l_succFuturesOpenCompleteRequest.priceAdjustmentValueInfo == null)
            {
                log.exiting(STR_METHOD_NAME);
                return super.getPrice();
            }
            else
            {
                if (l_succFuturesOpenCompleteRequest.afterAdjustmentPrice == null)
                {
                    log.debug("���s�P����null�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02707,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���s�P����null�B");
                }

                double l_dblAfterAdjustmentPrice =
                    Double.parseDouble(l_succFuturesOpenCompleteRequest.afterAdjustmentPrice);

                if (l_dblAfterAdjustmentPrice <= 0)
                {
                    log.debug("���s�P����0�ȉ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���s�P����0�ȉ��B ");
                }

                log.exiting(STR_METHOD_NAME);
                //���N�G�X�g.������P����ԋp����
                return l_dblAfterAdjustmentPrice;
            }
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
    }
}
@
