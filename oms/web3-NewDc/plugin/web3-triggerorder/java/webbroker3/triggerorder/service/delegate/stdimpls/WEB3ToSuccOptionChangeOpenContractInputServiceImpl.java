head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���jOP�����V�K�����̓T�[�r�XImpl�iWEB3ToSuccOptionChangeOpenContractInputServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/11 �g�E�N�| (���u) �V�K�쐬 ���f��267
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputRequest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractInputServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�����V�K�����̓T�[�r�XImpl)<BR>
 * �i�A���jOP�����V�K�����̓T�[�r�X�����N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeOpenContractInputServiceImpl
    extends WEB3OptionChangeOpenContractInputServiceImpl
    implements WEB3ToSuccOptionChangeOpenContractInputService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 47FDBE400242
     */
    public WEB3ToSuccOptionChangeOpenContractInputServiceImpl()
    {

    }

    /**
     * �i�A���j�����w���I�v�V���������V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.create���͉��()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A92193029D
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
        if (l_request instanceof WEB3SuccOptionsOpenChangeInputRequest)
        {
            l_response = this.createInputScreen((WEB3SuccOptionsOpenChangeInputRequest)l_request);
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
     * (get�����Ώے����P��)<BR>
     * �����Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B  <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j  <BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʂ��擾����B  <BR>
     * �@@�A�������}�l�[�W��Impl.get�敨OP�\�񒍕��P��(����ID)���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@����ID �F ���N�G�X�g.ID <BR>
     * <BR>
     * �Q�j�@@�敨OP�����P�ʃI�u�W�F�N�g�𐶐�����B  <BR>
     * �@@�A�������}�l�[�W��Impl.create�敨OP�����P��(�\�񒍕��P��)���R�[�����A�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�\�񒍕��P�� �F �P�j�̖߂�l<BR>
     * @@param l_request - (�����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g)<BR>
     * �����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 47D71DEC00BB
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3OptionsOpenMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3OptionsOpenMarginChangeInputRequest)";
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

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // �敨OP�\�񒍕��P�ʂ��擾����B
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // �敨OP�����P�ʃI�u�W�F�N�g�𐶐�����B
        IfoOrderUnit l_ifoOrderUnit = l_orderManager.createIfoOrderUnit(l_ifoOrderUnitImpl);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnit;
    }

    /**
     * (validate���������\)<BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B  <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j  <BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʂ��擾����B  <BR>
     * �@@�A�������}�l�[�W��Impl.get�敨OP�\�񒍕��P��(����ID)���R�[������B  <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@����ID �F ������IfoOrderUnit.����ID <BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l.validate�����\���()���R�[������B<BR>
     * @@param l_orderUnit - IfoOrderUnit<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D71DFB025D
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // �P�j�敨OP�\�񒍕��P�ʂ��擾����B
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
            l_orderManager.getReserveIfoOrderUnit(l_orderUnit.getOrderId());

        //�Q�j �P�j�̖߂�l.validate�����\���()���R�[������B
        l_ifoOrderUnitImpl.validateOrderForChangeability();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���͉��)<BR>
     * ���͉�ʕ\������ <BR>
     * <BR>
     * �u(�i�A���jOP�����V�K������)���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccOptionsOpenChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47D71E12011E
     */
    protected WEB3SuccOptionsOpenChangeInputResponse createInputScreen(
        WEB3SuccOptionsOpenChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInputScreen(WEB3SuccOptionsOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get�敨OP�\�񒍕��P��(long)
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        // get�e�����̒����P��( )
        IfoOrderUnit l_parentOrderUnit = l_ifoOrderUnit.getParentOrderUnit();

        // get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        // validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.OPTION,
            l_rsvIfoOrderUnitRow.getReserveOrderTradingType(),
            l_parentOrderUnit);

        // create���͉��(���N�G�X�g�f�[�^ : �����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g)
        WEB3SuccOptionsOpenChangeInputResponse l_response =
            (WEB3SuccOptionsOpenChangeInputResponse)super.createInputScreen(l_request);

        // �戵�\���������I�u�W�F�N�g�𐶐�����B
        WEB3GentradeHandlingOrderCond l_handlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                WEB3FuturesOptionDivDef.OPTION,
                WEB3MarginTradingDivDef.DEFAULT);

        IfoTradedProduct l_tradedProduct = (IfoTradedProduct)l_ifoOrderUnit.getTradedProduct();

        // set����ŏI��(����ŏI�� : Date)
        l_handlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

        // is�o����܂Œ����戵�\<����ŏI���l��>( )
        boolean l_blnIsOrderUntilDeadLinePossibleHandling =
            l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();

        Date l_datExpirationStartDate = null;
        Date l_datExpirationEndDate = null;
        Date[] l_datHolidayLists = null;
        // ������is�o����܂Œ����戵�\<����ŏI���l��>( )�@@==�@@true�@@�̏ꍇ
        if (l_blnIsOrderUntilDeadLinePossibleHandling)
        {
            // get�o����܂Œ����J�n��<����ŏI���l��>(�o����܂Œ����J�n�� : Date)
            l_datExpirationStartDate =
                l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);

            // get�o����܂Œ����ŏI��<����ŏI���l��>(������������ : Date)
            l_datExpirationEndDate =
                l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);

            // get�����������j���ꗗ( )
            l_datHolidayLists = l_handlingOrderCond.getExpirationDateHoliday();
        }

        // ���i�A���j�����w���敨�����V�K�����͉�ʃ��X�|���X�ŗL�̃v���p�e�B
        // �A���������ʏ��F�@@�敨OP�\�񒍕��P��.create�A���������ʏ��()���Z�b�g�B
        l_response.succCommonInfo = l_ifoOrderUnit.createSuccCommonInfo();

        // �P�������l���F�@@�@@�敨OP�\�񒍕��P��.create�P�������l���()���Z�b�g�B
        l_response.priceAdjustmentValueInfo = l_ifoOrderUnit.createSuccPriceAdjustmentValueInfo();

        // ���قȂ�l���Z�b�g����v���p�e�B�i�ăZ�b�g�j
        // ���s�����ꗗ�F�@@"������"�݂̂��Z�b�g�B
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};

        // ���������敪�ꗗ�F�@@"�w��Ȃ�"�݂̂��Z�b�g�B
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};

        // �v�w�l�p���s�����ꗗ�F�@@null���Z�b�g�B
        l_response.wlimitExecCondList = null;

        // �L�������J�n���F�@@(*1)�戵�\��������.get�o����܂Œ����J�n��<����ŏI���l��>()�̖߂�l���Z�b�g�B
        l_response.expirationStartDate = l_datExpirationStartDate;

        // �L�������ŏI���F�@@(*1)�戵�\��������.get�o����܂Œ����ŏI��<����ŏI���l��>()�̖߂�l���Z�b�g�B
        l_response.expirationEndDate = l_datExpirationEndDate;

        // �L���������j���ꗗ�F�@@(*1)�戵�\��������.get�����������j���ꗗ()�̖߂�l���Z�b�g�B
        l_response.holidayList = l_datHolidayLists;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
