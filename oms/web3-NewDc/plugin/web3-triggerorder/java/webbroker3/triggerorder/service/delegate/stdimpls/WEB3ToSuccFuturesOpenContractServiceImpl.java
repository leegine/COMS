head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�V�K�������T�[�r�X����Impl(WEB3ToSuccFuturesOpenContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 ���n(���u) �V�K�쐬���f��257
Revision History : 2008/03/18 ���n(���u) �d�l�ύX���f��290
Revision History : 2008/03/19 ���n(���u) �d�l�ύX���f��294
Revision History : 2008/04/01 ���n(���u) �d�l�ύX���f��310
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractRequestAdapter;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractServiceImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�敨�V�K�������T�[�r�XImpl)<BR>
 * �i�A���j�敨�V�K�������T�[�r�X�����N���X<BR>
 *
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccFuturesOpenContractServiceImpl
    extends WEB3FuturesOpenContractServiceImpl
    implements WEB3ToSuccFuturesOpenContractService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractServiceImpl.class);

    /**
     * @@roseuid 47D882AE00A9
     */
    public WEB3ToSuccFuturesOpenContractServiceImpl()
    {

    }

    /**
     * �i�A���j�敨�V�K�������T�[�r�X���������{����B<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * �@@�@@[�i�A���j�敨�V�K�������m�F���N�G�X�g�̏ꍇ] <BR>
     * �@@�@@�@@this.validate����()���R�[������B <BR>
     * �@@�@@[�i�A���j�敨�V�K�������������N�G�X�g�̏ꍇ] <BR>
     * �@@�@@�@@this.submit����()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A93E3E0063
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

        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����
        //[�i�A���j�敨�V�K�������m�F���N�G�X�g�̏ꍇ]
        //�@@this.validate����()���R�[������B
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SuccFuturesOpenConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccFuturesOpenConfirmRequest)l_request);
        }
        //[�i�A���j�敨�V�K�������������N�G�X�g�̏ꍇ]
        //�@@this.submit����()���R�[������B
        else if (l_request instanceof WEB3SuccFuturesOpenCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccFuturesOpenCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����)<BR>
     * �i�A���j�敨�V�K�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�i�A���j�敨�V�K���T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�����w���敨�V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccFuturesOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A93E3E0034
     */
    protected WEB3SuccFuturesOpenConfirmResponse validateOrder(WEB3SuccFuturesOpenConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccFuturesOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //�⏕�������擾����
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)getSubAccount();

        //�e�����̒����P�ʂ��擾����
        //[����]
        //�i�e�����j����ID �F ���N�G�X�g.�A���������ʏ��.�i�e�����j����ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(Long.parseLong(l_request.succCommonInfo.parentOrderId));

        //���Ύ���w�莞�̃��N�G�X�g�f�[�^�ɂ��ăv���p�e�B�`�F�b�N���s��
        //[����]
        //���N�G�X�g�f�[�^ �F ����.���N�G�X�g�f�[�^
        //�e�����̒����P�� �F �A�������}�l�[�W��.get�敨OP�e�����̒����P��()�̖߂�l
        this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

        //�A���������ʂ̃`�F�b�N���s��
        //[����]
        //�⏕���� �F �擾�����⏕�����I�u�W�F�N�g
        //�����^�C�v �F "�敨OP"�i�Œ�j
        //�敨�^�I�v�V�����敪 �F "�敨"�i�Œ�j
        //�A����������敪 �F ���N�G�X�g.�A���������ʏ��.�A����������敪
        //�e�����̒����P�� �F �擾�����e�����̒����P�ʃI�u�W�F�N�g
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //�A�������ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N���s��
        //[����]
        //�e�����̒����P�� �F �擾�����e�����̒����P�ʃI�u�W�F�N�g
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //�敨�V�K���������m�F�������s��
        //[����]
        //���N�G�X�g�f�[�^ �F ���N�G�X�g�f�[�^
        WEB3SuccFuturesOpenConfirmResponse l_response =
            (WEB3SuccFuturesOpenConfirmResponse)super.validateOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���j�敨�V�K�������o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�i�A���j�敨�V�K���T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�����w���敨�V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccFuturesOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A93E3E0053
     */
    protected WEB3SuccFuturesOpenCompleteResponse submitOrder(WEB3SuccFuturesOpenCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccFuturesOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //�⏕�������擾����
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)getSubAccount();

        //�e�����̒����P�ʂ��擾����
        //[����]
        //�i�e�����j����ID �F ���N�G�X�g.�A���������ʏ��.�i�e�����j����ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(Long.parseLong(l_request.succCommonInfo.parentOrderId));

        //���Ύ���w�莞�̃��N�G�X�g�f�[�^�ɂ��ăv���p�e�B�`�F�b�N���s��
        //�@@[����]
        //���N�G�X�g�f�[�^ �F ����.���N�G�X�g�f�[�^
        //�e�����̒����P�� �F �A�������}�l�[�W��.get�敨OP�e�����̒����P��()�̖߂�l
        this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

        //�A���������ʂ̃`�F�b�N���s��
        //[����]
        //�⏕���� �F �擾�����⏕�����I�u�W�F�N�g
        //�����^�C�v �F "�敨OP"�i�Œ�j
        //�敨�^�I�v�V�����敪 �F "�敨"�i�Œ�j
        //�A����������敪 �F ���N�G�X�g.�A���������ʏ��.�A����������敪
        //�e�����̒����P�� �F �擾�����e�����̒����P�ʃI�u�W�F�N�g
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //�A�������ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N���s��
        //[����]
        //�e�����̒����P�� �F �擾�����e�����̒����P�ʃI�u�W�F�N�g
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //�敨�V�K�������o�^�������s��
        //[����]
        //���N�G�X�g�f�[�^ �F ���N�G�X�g�f�[�^
        WEB3SuccFuturesOpenCompleteResponse l_response =
            (WEB3SuccFuturesOpenCompleteResponse)super.submitOrder(l_request);

        //�\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����
        //[����]
        //�q�����̒���ID �F ���N�G�X�g.����ID
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����]��)<BR>
     * �؋����̃`�F�b�N���s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpecIntercepter - (�������e�C���^�Z�v�^)<BR>
     * �������e�C���^�Z�v�^�B<BR>
     * @@param l_orderSpec - (�������e)<BR>
     * �������e�B<BR>
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * <BR>
     * true �F �]�͍Čv�Z���������{���� <BR>
     * false �F �]�͍Čv�Z���������{���Ȃ�<BR>
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 47ABD8B30056
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        SubAccount l_subAccount,
        Object[] l_orderSpecIntercepter,
        Object[] l_orderSpec,
        boolean l_blnUpdateFlg)
    {
        return null;
    }

    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N������B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}<BR>
     * �u�i�i�A���j�敨�V�K���T�[�r�X�jvalidate�����^�i�i�A���j�敨�V�K���T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABB71D0202
     */
    protected void validateSuccOrderMaxQuantity(IfoOrderUnit l_parentOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSuccOrderMaxQuantity(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�A�������ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N������
        //[����]
        //�e�����̒����P�ʁ@@�F�@@����.�e�����̒����P�ʃI�u�W�F�N�g
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        l_orderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���N�G�X�g�f�[�^at���Ύ��)<BR>
     * ���Ύ���w�莞�ɁA�ŗL�́i���N�G�X�g�f�[�^�j�v���p�e�B�`�F�b�N���s���B <BR>
     * <BR>
     * �@@�P�j �A�������}�l�[�W��Impl.is���Δ������() == false�i�����Ύ���łȂ��j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@return����B<BR>
     * <BR>
     * �@@�@@�@@ [�����ݒ�d�l]<BR>
     * <BR>
     * �@@�@@�@@�@@ �A����������敪 �F ����.���N�G�X�g.�A���������ʏ��.�A����������敪<BR>
     * �@@�@@�@@�@@ �e�����̒����P�� �F ����.�e�����̒����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * �@@�Q�j �ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@ �u���Ύ�����̖����w�肪�A�e�����ƕs�����v�̗�O��throw����B<BR>
     * �@@�@@�@@ ����.���N�G�X�g�f�[�^.�����R�[�h �� <BR>
     * �@@�@@�@@�@@�@@����.�e�����̒����P��.����ID�ɊY����������R�[�h<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02250<BR>
     * <BR>
     * �@@�@@�@@ �������̃��N�G�X�g�f�[�^�́A�ȉ��̂����ꂩ�ɃL���X�g���邱�ƁB<BR>
     * �@@�@@�@@�@@ �E�i�A���j�敨�V�K�������m�F���N�G�X�g<BR>
     * �@@�@@�@@�@@ �E�i�A���j�敨�V�K�������������N�G�X�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABB71D01FF
     */
    protected void validateRequestDataAtReversingTrade(
        WEB3GenRequest l_request,
        IfoOrderUnit l_parentOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRequestDataAtReversingTrade(WEB3GenRequest, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccCommonInfo l_succCommonInfo = null;
        String l_strProductCode = null;
        if (l_request instanceof WEB3SuccFuturesOpenConfirmRequest)
        {
            WEB3SuccFuturesOpenConfirmRequest l_succFuturesOpenConfirmRequest =
                (WEB3SuccFuturesOpenConfirmRequest)l_request;
            l_succCommonInfo = l_succFuturesOpenConfirmRequest.succCommonInfo;
            l_strProductCode = l_succFuturesOpenConfirmRequest.futProductCode;
        }
        else if (l_request instanceof WEB3SuccFuturesOpenCompleteRequest)
        {
            WEB3SuccFuturesOpenCompleteRequest l_succFuturesOpenCompleteRequest =
                (WEB3SuccFuturesOpenCompleteRequest)l_request;
            l_succCommonInfo = l_succFuturesOpenCompleteRequest.succCommonInfo;
            l_strProductCode = l_succFuturesOpenCompleteRequest.futProductCode;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        //�A�������}�l�[�W��Impl.is���Δ������() == false�i�����Ύ���łȂ��j�̏ꍇ�Areturn����
        //[�����ݒ�d�l]
        //�A����������敪 �F ����.���N�G�X�g.�A���������ʏ��.�A����������敪
        //�e�����̒����P�� �F ����.�e�����̒����P�ʃI�u�W�F�N�g
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        if (!l_orderManager.isReversingTrade(
            l_succCommonInfo.succTradingType,
            l_parentOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A�u���Ύ�����̖����w�肪�A�e�����ƕs�����v�̗�O��throw����
        //����.���N�G�X�g�f�[�^.�����R�[�h �� ����.�e�����̒����P��.����ID�ɊY����������R�[�h
        IfoProductImpl l_product = (IfoProductImpl)l_parentOrderUnit.getProduct();
        if ((l_strProductCode == null) || !l_strProductCode.equals(l_product.getProductCode()))
        {
           log.debug("���Ύ�����̖����w�肪�A�e�����ƕs�����ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02250,
               getClass().getName() + "." + STR_METHOD_NAME,
               "���Ύ�����̖����w�肪�A�e�����ƕs�����ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�V�K������)<BR>
     * �\�񒍕��̒����o�^���s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �@@�A�������}�l�[�W��.submit�敨OP�V�K���V�K�\�񒍕�()���R�[������B<BR>
     * <BR>
     * �@@[�����ݒ�d�l]<BR>
     * <BR>
     * �@@�@@�⏕���� �F ����.�⏕����<BR>
     * �@@�@@�������e �F ����.�敨�V�K���������e<BR>
     * �@@�@@����ID �F ����.����ID<BR>
     * �@@�@@����p�X���[�h �F ����.����p�X���[�h<BR>
     * �@@�@@�A����������敪 �F <BR>
     * �@@�@@�@@����.���N�G�X�g�A�_�v�^.���N�G�X�g.�A���������ʏ��.�A����������敪<BR>
     * �@@�@@�P�������l �F <BR>
     * �@@�@@�@@����.���N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()<BR>
     * �@@�@@�������̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l��� == null�̏ꍇ�A<BR>
     * �@@�@@�@@null���Z�b�g<BR>
     * �@@�@@�e�����̒����P�� �F ����.���N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * �@@�@@�敨OP�T�Z��n����v�Z���� �F ����.�敨OP�T�Z��n����v�Z����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_openContractOrderSpec - (�V�K���������e)<BR>
     * �V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_requestAdaptor - (�V�K�����N�G�X�g�A�_�v�^)<BR>
     * �敨�V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�敨OP�T�Z��n����v�Z����)<BR>
     * �敨OP�T�Z��n����v�Z���ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABB71D01F0
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_openContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3FuturesOpenContractRequestAdapter l_requestAdaptor,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder("
            + "WEB3GentradeSubAccount, "
            + "IfoOpenContractOrderSpec, "
            + "long, "
            + "String, "
            + "WEB3FuturesOpenContractRequestAdapter, "
            + "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccFuturesOpenContractRequestAdapter l_adapter = null;
        IfoOrderUnit l_ifoOrderUnit = null;
        if (l_requestAdaptor instanceof WEB3ToSuccFuturesOpenContractRequestAdapter)
        {
            l_adapter =
                (WEB3ToSuccFuturesOpenContractRequestAdapter)l_requestAdaptor;
            l_ifoOrderUnit = l_adapter.parentOrderUnit;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        //�A�������}�l�[�W��.submit�敨OP�V�K���V�K�\�񒍕�()���R�[������
        //[�����ݒ�d�l]
        //�⏕���� �F ����.�⏕����
        //�������e �F ����.�敨�V�K���������e
        //����ID �F ����.����ID
        //����p�X���[�h �F ����.����p�X���[�h
        //�A����������敪 �F ����.���N�G�X�g�A�_�v�^.���N�G�X�g.�A���������ʏ��.�A����������敪
        //�P�������l �F ����.���N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()
        //�������̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l��� == null�̏ꍇ�Anull���Z�b�g
        //�e�����̒����P�� �F ����.���N�G�X�g�A�_�v�^.�e�����̒����P��
        //�敨OP�T�Z��n����v�Z���� �F ����.�敨OP�T�Z��n����v�Z����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3SuccCommonInfo l_succCommonInfo = null;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccFuturesOpenCompleteRequest)
        {
            WEB3SuccFuturesOpenCompleteRequest l_succFuturesOpenCompleteRequest =
                (WEB3SuccFuturesOpenCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_succFuturesOpenCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_succFuturesOpenCompleteRequest.priceAdjustmentValueInfo;
        }

        Double l_price = null;
        if (l_priceAdjustmentValueInfo == null)
        {
            l_price = null;
        }
        else
        {
            l_price = new Double(l_priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        l_orderManager.submitIfoOpenContractNewOrder(
            l_subAccount,
            (WEB3IfoOpenContractOrderSpec)l_openContractOrderSpec,
            l_lngOrderId,
            l_strTradingPassword,
            l_succCommonInfo.succTradingType,
            l_price,
            l_ifoOrderUnit,
            l_estimateDeliveryAmountCalcResult);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify�\�񒍕��o�^)<BR>
     * �\�񒍕��̓o�^�����[���G���W���T�[�o�֒ʒm����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�i�A���j�敨�V�K���T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_lngRsvOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABB71D0204
     */
    protected void notifyRsvOrderRegister(long l_lngRsvOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        //�\�񒍕��̓o�^�����[���G���W���T�[�o�֒ʒm����
        //�A�������}�l�[�W��.get�敨OP�\�񒍕��P��()�ɂāA�\�񒍕��P�ʂ��擾����
        //[����]
        //����ID�@@�F�@@����.�q�����̒���ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = null;
        l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(l_lngRsvOrderId);

        //�敨�����}�l�[�W��.notify���[���G���W���T�[�o()���R�[������
        //[����]
        //�����P�� �F get�敨OP�\�񒍕��P��()�̖߂�l
        //���� �F NEW_OPEN_CONTRACT_ORDER
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        l_orderMgr.notifyRLS(
            l_ifoOrderUnit,
            OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �@@�i�A���j�敨�V�K�����N�G�X�g�A�_�v�^.create()���R�[������B<BR>
     * <BR>
     * �@@[�����ݒ�d�l]<BR>
     * <BR>
     * �@@�@@���N�G�X�g �F ����.���N�G�X�g<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3FuturesOpenContractRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47B1207F0191
     */
    protected WEB3FuturesOpenContractRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�i�A���j�敨�V�K�����N�G�X�g�A�_�v�^.create()���R�[������
        //[�����ݒ�d�l]
        //���N�G�X�g �F ����.���N�G�X�g
        WEB3FuturesOpenContractRequestAdapter l_adapter =
            WEB3ToSuccFuturesOpenContractRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (set�P��)<BR>
     * �����̃��X�|���X�D������P���ɒP����ݒ肷��B<BR>
     * <BR>
     * �@@ ���N�G�X�g�f�[�^.�P�������l��� �� null�i�}�w�l�w��j�̏ꍇ<BR>
     * �@@�@@�@@���X�|���X.������P���ɁA<BR>
     * �@@�@@�@@�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �@@ ��L�ȊO�̏ꍇ<BR>
     * �@@�@@ return������B<BR>
     * @@param l_adapter - (�敨�V�K���������N�G�X�g�A�_�v�^)<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�I�u�W�F�N�g�B<BR>
     * @@roseuid 47B104C702DA
     */
    protected void setPrice(WEB3FuturesOpenContractRequestAdapter l_adapter, WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setPrice(WEB3FuturesOpenContractRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^.�P�������l��� �� null�i�}�w�l�w��j�̏ꍇ
        //���X�|���X.������P���ɁA�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����
        WEB3ToSuccFuturesOpenContractRequestAdapter l_requestAdapter =
            (WEB3ToSuccFuturesOpenContractRequestAdapter)l_adapter;
        WEB3SuccFuturesOpenConfirmRequest l_request =
            (WEB3SuccFuturesOpenConfirmRequest)l_requestAdapter.request;

        //���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ
        if (l_request.priceAdjustmentValueInfo != null)
        {
            WEB3SuccFuturesOpenConfirmResponse l_confirmResponse =
                (WEB3SuccFuturesOpenConfirmResponse)l_response;
            l_confirmResponse.afterAdjustmentPrice = WEB3StringTypeUtility.formatNumber(l_requestAdapter.getPrice());
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
