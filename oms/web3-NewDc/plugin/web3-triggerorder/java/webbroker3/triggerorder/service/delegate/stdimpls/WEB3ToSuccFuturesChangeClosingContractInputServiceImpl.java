head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeClosingContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�敨�����ԍϓ��̓T�[�r�XImpl�iWEB3ToSuccFuturesChangeClosingContractInputServiceImpl.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/14 �И���(���u) �V�K�쐬���f��No.264 No.286 No.309
 Revision History : 2008/04/17 �И���(���u) �d�l�ύX���f��No.327
 */

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
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
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractInputServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�����ԍϓ��̓T�[�r�XImpl)<BR>
 * �i�A���j�����w���敨�����ԍϓ��̓T�[�r�X�����N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeClosingContractInputServiceImpl
    extends WEB3FuturesChangeClosingContractInputServiceImpl
    implements WEB3ToSuccFuturesChangeClosingContractInputService
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeClosingContractInputServiceImpl.class);

    /**
     * @@roseuid 47D6593402DE
     */
    public WEB3ToSuccFuturesChangeClosingContractInputServiceImpl()
    {

    }

    /**
     * �i�A���j�����w���敨�����ԍϓ��̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.get�����ԍϓ��͉��()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94F7A02B0
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

        if (!(l_request instanceof WEB3SuccFuturesCloseChangeInputRequest))
        {
            log.debug("�p�����[�^�^�C�v�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s��");
        }

        WEB3SuccFuturesCloseChangeInputRequest l_inputRequest =
            (WEB3SuccFuturesCloseChangeInputRequest)l_request;

        //this.get�����ԍϓ��͉��()
        WEB3SuccFuturesCloseChangeInputResponse l_response =
            this.getFuturesChangeClosingContractInputScreen(l_inputRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����ԍϓ��͉��)<BR>
     * �i�A���j�����w���敨�����ԍϓ��͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A���j�i�敨�����ԍϓ��́j���͉�ʕ\���f�[�^�擾�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccFuturesCloseChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C226C702D8
     */
    protected WEB3SuccFuturesCloseChangeInputResponse getFuturesChangeClosingContractInputScreen(
        WEB3SuccFuturesCloseChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFuturesChangeClosingContractInputScreen("
            + "WEB3SuccFuturesCloseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();

        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�\�񒍕��P��(long)
        //����ID�F�@@���N�G�X�g�f�[�^.ID
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));
        //get�e�����̒����P��
        IfoOrderUnit l_ifoOrderUnit = l_orderUnit.getParentOrderUnit();
        //get�⏕����
        SubAccount l_subAccount = this.getSubAccount();

        //validate�A������
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        l_orderManager.validateSuccOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_rsvIfoOrderUnitRow.getReserveOrderTradingType(),
            l_ifoOrderUnit);

        //get�����ԍϓ��͉��(���N�G�X�g�f�[�^ : �����w���敨�����ԍϓ��͉�ʃ��N�G�X�g)
        WEB3SuccFuturesCloseChangeInputResponse l_response =
            (WEB3SuccFuturesCloseChangeInputResponse)super.getCloseChangeInputScreen(l_request);

        //�戵�\��������
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                WEB3FuturesOptionDivDef.FUTURES,
                WEB3MarginTradingDivDef.DEFAULT);

        //�敨OP�������
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
            (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();
        //set����ŏI��
        l_gentradeHandlingOrderCond.setTradingEndDate(l_ifoTradedProductImpl.getLastTradingDate());

        //�A���������ʏ��
        l_response.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        //�P�������l���
        l_response.priceAdjustmentValueInfo = l_orderUnit.createSuccPriceAdjustmentValueInfo();
        //���s�����ꗗ
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
        //���������敪�ꗗ
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        //�v�w�l�p���s�����ꗗ
        l_response.wlimitExecCondList = null;

        //is�o����܂Œ����戵�\<����ŏI���l��>
        boolean l_blnDateConsidering =
            l_gentradeHandlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();
        if (l_blnDateConsidering)
        {
            //�L�������J�n�� = get�o����܂Œ����J�n��<����ŏI���l��>
            l_response.expirationStartDate =
                l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);
            //�L�������ŏI�� = get�o����܂Œ����ŏI��<����ŏI���l��>
            l_response.expirationEndDate =
                l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);
            //�L���������j���ꗗ = get�����������j���ꗗ
            l_response.holidayList = l_gentradeHandlingOrderCond.getExpirationDateHoliday();
        }
        else
        {
            //is�o����܂Œ����戵�\<����ŏI���l��>( ) == false�̏ꍇ��null���Z�b�g
            //�L�������J�n�� = null
            l_response.expirationStartDate = null;
            //�L�������ŏI�� = null
            l_response.expirationEndDate = null;
            //�L���������j���ꗗ = null
            l_response.holidayList = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����Ώے����P��)<BR>
     * �����Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�敨OP�\�񒍕��P��(���N�G�X�g�f�[�^.ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�敨OP�����P�ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�A�������}�l�[�W��Impl.create�敨OP�����P��(�P�j�̖߂�l)��<BR>
     * �@@�R�[�����A�߂�l��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 47BB74DC020D
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //�敨OP�\�񒍕��P�ʂ��擾����
        //����ID�F�@@���N�G�X�g�f�[�^.ID
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        //�敨OP�����P�ʃI�u�W�F�N�g�𐶐�����
        IfoOrderUnit l_ifoOrderUnit = l_orderManager.createIfoOrderUnit(l_orderUnit);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnit;
    }

    /**
     * (validate���������\)<BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�敨OP�\�񒍕��P��(�����̒����P��.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l.validate�����\���()���R�[������B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BB921C0054
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //�敨OP�\�񒍕��P�ʂ��擾����
        //����ID�F�@@���N�G�X�g�f�[�^.ID
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(l_orderUnit.getOrderId());

        //validate�����\���
        l_ifoOrderUnit.validateOrderForChangeability();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���ʖ���ByOrder)<BR>
     * �����̒����P�ʂɊ֘A���錚�ʖ��ׂ̈ꗗ���쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�敨OP�\�񒍕��P��(�����P��.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�A�������}�l�[�W��.create���ʖ���ByOrder(�P�j�̖߂�l)��<BR>
     * �@@�R�[�����A�߂�l��ԋp����B<BR>
     * �@@��null���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�@@�u�\�񌈍ϑΏی��ʂ͕ʒ����ɂ�茈�ύρv�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_03066<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47BBCA7F00B6
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnitByOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //�敨OP�\�񒍕��P�ʂ��擾����
        //����ID�F�@@���N�G�X�g�f�[�^.ID
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(l_orderUnit.getOrderId());

        //create���ʖ���ByOrder
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            l_orderManager.createIfoContractUnitByOrder(l_ifoOrderUnit);

        if (l_contractUnits == null)
        {
            log.debug("�\�񌈍ϑΏی��ʂ͕ʒ����ɂ�茈�ύςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03066,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�񌈍ϑΏی��ʂ͕ʒ����ɂ�茈�ύςł��B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
}
@
