head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionOpenContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���jOP�V�K�������T�[�r�XImpl(WEB3ToSuccOptionOpenContractOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 ���n(���u) �V�K�쐬���f��281
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
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractOrderRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���jOP�V�K�������T�[�r�XImpl)<BR>
 * �i�A���j�I�v�V�����V�K�������T�[�r�X�����N���X<BR>
 *
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccOptionOpenContractOrderServiceImpl extends WEB3OptionOpenContractOrderServiceImpl
    implements WEB3ToSuccOptionOpenContractOrderService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractOrderServiceImpl.class);

    /**
     * @@roseuid 47FDBE40003E
     */
    public WEB3ToSuccOptionOpenContractOrderServiceImpl()
    {

    }

    /**
     * �i�A���j�I�v�V�����V�K���T�[�r�X���������{����B<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * �@@�@@[�i�A���j�����w���I�v�V�����V�K�������m�F���N�G�X�g�̏ꍇ] <BR>
     * �@@�@@�@@this.validate����()���R�[������B <BR>
     * �@@�@@[�i�A���j�����w���I�v�V�����V�K�������������N�G�X�g�̏ꍇ] <BR>
     * �@@�@@�@@this.submit����()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A918E701F1
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
        //[�i�A���j�����w���I�v�V�����V�K�������m�F���N�G�X�g�̏ꍇ]
        //this.validate����()���R�[������
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SuccOptionsOpenConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccOptionsOpenConfirmRequest)l_request);
        }
        //[�i�A���j�����w���I�v�V�����V�K�������������N�G�X�g�̏ꍇ]
        //this.submit����()���R�[������
        else if (l_request instanceof WEB3SuccOptionsOpenCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccOptionsOpenCompleteRequest)l_request);
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
     * �i�A���j�I�v�V�����V�K�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���jOP�V�K���T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�����w���I�v�V�����V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccOptionsOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A918E701D2
     */
    protected WEB3SuccOptionsOpenConfirmResponse validateOrder(WEB3SuccOptionsOpenConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsOpenConfirmRequest)";
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
        //�敨�^�I�v�V�����敪 �F "�I�v�V����"�i�Œ�j
        //�A����������敪 �F ���N�G�X�g.�A���������ʏ��.�A����������敪
        //�e�����̒����P�� �F �擾�����e�����̒����P�ʃI�u�W�F�N�g
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.OPTION,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //�A�������ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N���s��
        //[����]
        //�e�����̒����P�� �F �擾�����e�����̒����P�ʃI�u�W�F�N�g
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //OP�V�K���������m�F�������s��
        //[����]
        //���N�G�X�g�f�[�^ �F ���N�G�X�g�f�[�^
        WEB3SuccOptionsOpenConfirmResponse l_response =
            (WEB3SuccOptionsOpenConfirmResponse)super.validateOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���j�I�v�V�����V�K�������o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���jOP�V�K���T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�����w���I�v�V�����V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3SuccOptionsOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A918E701E1
     */
    protected WEB3SuccOptionsOpenCompleteResponse submitOrder(WEB3SuccOptionsOpenCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsOpenCompleteRequest)";
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
        //�敨�^�I�v�V�����敪 �F �I�v�V����"(�Œ�j
        //�A����������敪 �F ���N�G�X�g.�A���������ʏ��.�A����������敪
        //�e�����̒����P�� �F �擾�����e�����̒����P�ʃI�u�W�F�N�g
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.OPTION,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //�A�������ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N���s��
        //[����]
        //�e�����̒����P�� �F �擾�����e�����̒����P�ʃI�u�W�F�N�g
        this.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //OP�V�K�������o�^�������s��
        //[����]
        //���N�G�X�g�f�[�^ �F ���N�G�X�g�f�[�^
        WEB3SuccOptionsOpenCompleteResponse l_response =
            (WEB3SuccOptionsOpenCompleteResponse)super.submitOrder(l_request);

        //�\�񒍕��̓o�^�����[���G���W���T�[�o�ɒʒm����
        //[����]
        //�q�����̒���ID �F ���N�G�X�g.����ID
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����]��)<BR>
     * �؋����E�]�͂̃`�F�b�N���s���B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_orderSpecIntercepters - (�������e�C���^�Z�v�^)<BR>
     * �������e�C���^�Z�v�^�B<BR>
     * @@param l_orderSpecs - (�������e)<BR>
     * �������e�B<BR>
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * <BR>
     * true �F �]�͍Čv�Z���������{���� <BR>
     * false �F �]�͍Čv�Z���������{���Ȃ�<BR>
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 47BD39EA03AD
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        SubAccount l_subAccount,
        Object[] l_orderSpecIntercepters,
        Object[] l_orderSpecs,
        boolean l_blnUpdateFlg)
    {
        return null;
    }

    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������̍ő�ݒ萔�𒴉߂��Ȃ����`�F�b�N������B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}<BR>
     * �u�i�i�A���jOP�V�K���T�[�r�X�jvalidate�����^�i�i�A���jOP�V�K���T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BBCC2B02C7
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
     * �@@�@@�@@�@@�u���Ύ�����̖����w�肪�A�e�����ƕs�����v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@ ����.���N�G�X�g�f�[�^.�����R�[�h �� ����.�e�����̒����P��.����ID�ɊY����������R�[�h<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02250<BR>
     * <BR>
     * �@@�@@�@@ �������̃��N�G�X�g�f�[�^�́A�ȉ��̂����ꂩ�ɃL���X�g���邱�ƁB<BR>
     * �@@�@@�@@�@@ �E�i�A���j�����w���I�v�V�����V�K�������m�F���N�G�X�g<BR>
     * �@@�@@�@@�@@ �E�i�A���j�����w���I�v�V�����V�K�������������N�G�X�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BBEAD00026
     */
    protected void validateRequestDataAtReversingTrade(WEB3GenRequest l_request, IfoOrderUnit l_parentOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRequestDataAtReversingTrade(WEB3GenRequest, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccCommonInfo l_succCommonInfo = null;
        String l_strProductCode = null;
        if (l_request instanceof WEB3SuccOptionsOpenConfirmRequest)
        {
            WEB3SuccOptionsOpenConfirmRequest l_succOptionsOpenConfirmRequest =
                (WEB3SuccOptionsOpenConfirmRequest)l_request;
            l_succCommonInfo = l_succOptionsOpenConfirmRequest.succCommonInfo;
            l_strProductCode = l_succOptionsOpenConfirmRequest.opProductCode;
        }
        else if (l_request instanceof WEB3SuccOptionsOpenCompleteRequest)
        {
            WEB3SuccOptionsOpenCompleteRequest l_succOptionsOpenCompleteRequest =
                (WEB3SuccOptionsOpenCompleteRequest)l_request;
            l_succCommonInfo = l_succOptionsOpenCompleteRequest.succCommonInfo;
            l_strProductCode = l_succOptionsOpenCompleteRequest.opProductCode;
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
     * �@@�@@�⏕�����@@�F�@@����.�⏕����<BR>
     * �@@�@@�������e�@@�F�@@����.OP�V�K���������e<BR>
     * �@@�@@����ID �F�@@����.����ID<BR>
     * �@@�@@����p�X���[�h�@@�F�@@����.�p�X���[�h<BR>
     * �@@�@@�A����������敪 �F ����.���N�G�X�g�A�_�v�^.���N�G�X�g.�A���������ʏ��.�A����������敪<BR>
     * �@@�@@�P�������l �F ����.���N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l���.get�P�������l()<BR>
     * �@@�@@�������̃��N�G�X�g�A�_�v�^.���N�G�X�g.�P�������l��� == null�̏ꍇ�Anull���Z�b�g<BR>
     * �@@�@@�e�����̒����P�� �F ����.���N�G�X�g�A�_�v�^.�e�����̒����P��<BR>
     * �@@�@@�敨OP�T�Z��n����v�Z���� �F ����.�敨OP�T�Z��n����v�Z����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_openContractOrderSpec - (�V�K���������e)<BR>
     * �V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_requestAdaptor - (�V�K�����N�G�X�g�A�_�v�^)<BR>
     * OP�V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (�敨OP�T�Z��n���v�Z����)<BR>
     * �敨OP�T�Z��n����v�Z���ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C661B800E7
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_openContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdaptor,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder("
            + "WEB3GentradeSubAccount, "
            + "IfoOpenContractOrderSpec, "
            + "long, "
            + "String, "
            + "WEB3OptionOpenContractOrderRequestAdapter, "
            + "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionOpenContractOrderRequestAdapter l_adapter = null;
        IfoOrderUnit l_ifoOrderUnit = null;
        if (l_requestAdaptor instanceof WEB3ToSuccOptionOpenContractOrderRequestAdapter)
        {
            l_adapter =
                (WEB3ToSuccOptionOpenContractOrderRequestAdapter)l_requestAdaptor;
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
        if (l_requestAdaptor.request instanceof WEB3SuccOptionsOpenCompleteRequest)
        {
            WEB3SuccOptionsOpenCompleteRequest l_succOptionsOpenCompleteRequest =
                (WEB3SuccOptionsOpenCompleteRequest)l_requestAdaptor.request;
            l_succCommonInfo = l_succOptionsOpenCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_succOptionsOpenCompleteRequest.priceAdjustmentValueInfo;
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
     * �������̏ڍׂ̓V�[�P���X�}�u�i�i�A���jOP�V�K���T�[�r�X�jsubmit�����v�Q��<BR>
     * @@param l_lngChildOrderId - (�q�����̒���ID)<BR>
     * �q�����̒���ID�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BBD0080288
     */
    protected void notifyRsvOrderRegister(long l_lngChildOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);

        ////�\�񒍕��̓o�^�����[���G���W���T�[�o�֒ʒm����
        //�A�������}�l�[�W��.get�敨OP�\�񒍕��P��()�ɂāA�\�񒍕��P�ʂ��擾����
        //[����]
        //����ID�@@�F�@@����.�q�����̒���ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = null;
        l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(l_lngChildOrderId);

        //OP�����}�l�[�W��.notify���[���G���W���T�[�o()���R�[������
        //[����]
        //�����P�� �F get�敨OP�\�񒍕��P��()�̖߂�l
        //���� �F NEW_OPEN_CONTRACT_ORDER
        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        l_optionOrderManagerImpl.notifyRLS(
            l_ifoOrderUnit,
            OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �@@�i�A���jOP�V�K�����N�G�X�g�A�_�v�^.create()���R�[������B<BR>
     * <BR>
     * �@@[�����ݒ�d�l]<BR>
     * <BR>
     * �@@�@@���N�G�X�g �F ����.���N�G�X�g<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3OptionOpenContractOrderRequestAdapter
     * @@roseuid 47BD3B40004E
     */
    protected WEB3OptionOpenContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�i�A���jOP�V�K�����N�G�X�g�A�_�v�^.create()���R�[������
        //[�����ݒ�d�l]
        //���N�G�X�g �F ����.���N�G�X�g
        WEB3OptionOpenContractOrderRequestAdapter l_adapter =
            WEB3ToSuccOptionOpenContractOrderRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (set�P��)<BR>
     * �����̃��X�|���X�D������P���ɒP����ݒ肷��B<BR>
     * <BR>
     * �@@ ���N�G�X�g�f�[�^.�P�������l��� �� null�i�}�w�l�w��j�̏ꍇ<BR>
     * �@@�@@ ���X�|���X.������P���ɁA�����̃��N�G�X�g�A�_�v�^.get�P��()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �@@ ��L�ȊO�̏ꍇ<BR>
     * �@@�@@ return������B<BR>
     * @@param l_optionOpenContractOrderRequestAdapter - (OP�V�K���������N�G�X�g�A�_�v�^)<BR>
     * OP�V�K���������N�G�X�g�A�_�v�^�I�u�W�F�N�g�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�I�u�W�F�N�g�B<BR>
     * @@roseuid 47BD3B5901F4
     */
    protected void setPrice(
        WEB3OptionOpenContractOrderRequestAdapter l_optionOpenContractOrderRequestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setPrice(WEB3OptionOpenContractOrderRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^.�P�������l��� �� null�i�}�w�l�w��j�̏ꍇ
        //���X�|���X.������P���ɁA�����̃��N�G�X�g�A�_�v�^�Dget�P��()�̖߂�l���Z�b�g����
        WEB3ToSuccOptionOpenContractOrderRequestAdapter l_requestAdapter =
            (WEB3ToSuccOptionOpenContractOrderRequestAdapter)l_optionOpenContractOrderRequestAdapter;
        WEB3SuccOptionsOpenConfirmRequest l_request =
            (WEB3SuccOptionsOpenConfirmRequest)l_requestAdapter.request;

        //���N�G�X�g.�P�������l���null�i�}�w�l�w��j�̏ꍇ
        if (l_request.priceAdjustmentValueInfo != null)
        {
            WEB3SuccOptionsOpenConfirmResponse l_confirmResponse =
                (WEB3SuccOptionsOpenConfirmResponse)l_response;
            l_confirmResponse.afterAdjustmentPrice = WEB3StringTypeUtility.formatNumber(l_requestAdapter.getPrice());
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
    }
}
@
