head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������敨OP�����ꌏ�T�[�r�XImpl(WEB3ToSuccIfoOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/06 ���n(���u) �V�K�쐬���f��311,339,344,347,348,349
Revision History : 2008/05/29 �g�E�N�|(���u) �d�l�ύX���f��No.354
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderUnitService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A�������敨OP�����ꌏ�T�[�r�XImpl)<BR>
 * �A�������敨OP�����ꌏ�T�[�r�X�����N���X�B<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderUnitServiceImpl implements WEB3ToSuccIfoOrderUnitService
{
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccIfoOrderUnitServiceImpl.class);

    /**
     * @@roseuid 481EA53F0017
     */
    public WEB3ToSuccIfoOrderUnitServiceImpl()
    {

    }

    /**
     * (submit�敨�V�K������)<BR>
     * �敨�V�K�������𔭒�����B<BR>
     * �V�[�P���X�}�u�i�A�������敨OP�����ꌏ�T�[�r�X�jsubmit�敨�V�K�������v���Q�ƁB<BR>
     * @@param l_rsvIfoOrderUnit - (�敨OP�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@roseuid 47DF5498018A
     */
    public void submitFuturesOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitFuturesOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�G���[�R�[�h
        String l_strErrorCode = null;

        //�敨OP�\�񒍕��X�V�T�[�r�X
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;
        WEB3GentradeSubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        try
        {
            //get������(�m�F�������� : Date, ����敪 : String)
            //�������^����ԑт̃`�F�b�N���s���B
            //�i�\�񒍕��o�^���̔������^����ԑтƁA
            //���ݓ������狁�߂��������^����ԑт��قȂ�ꍇ�͔����G���[�Ƃ���j
            //[����]
            //�@@�m�F�������� �F �����̐敨OP�\�񒍕��P��.������
            //�@@����敪 �F �����̐敨OP�\�񒍕��P��.����敪
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_rsvIfoOrderUnitRow.getSessionType());

            //����t���[�F�@@�\�񒍕��P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                //���ҁi�㗝���͎ҁj�I�u�W�F�N�g���擾����B
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B
                //�����ID�F�@@�\�񒍕��P��.�����ID
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvIfoOrderUnitRow.getTraderId());
            }

            //�⏕�������擾����
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_rsvIfoOrderUnit.getAccountId(),
                l_rsvIfoOrderUnitRow.getSubAccountId());

            //[create�V�K���������e()�Ɏw�肷�����]
            //�،���ЃR �[�h �F �\�񒍕��P��.���XID����擾�����،���ЃR�[�h
            //���� �F �\�񒍕��P��.�����ID==null�̏ꍇ�Anull
            //          �\�񒍕��P��.�����ID<>null�̏ꍇ�A�擾�������҃I�u�W�F�N�g
            //is���� �F �\�񒍕��P��.is������()
            //�s��R�[�h �F �\�񒍕��P��.get�s��().�s��R�[�h
            //���� �F �\�񒍕��P��.get����()
            //���� �F �\�񒍕��P��.��������
            //�w�l �F �\�񒍕��P��.get�\�񒍕����s�P��()
            //���s����   �F "�����Ȃ�"
            //�����������F�\�񒍕��P��.����������
            //��������   �F"�����Ȃ�"
            //�t�w�l��l       �F 0
            //�iW�w�l�j�����w�l �F 0
            //�iW�w�l�j���s���� �F null
            //���������敪             �F �\�񒍕��P��.���������敪
            //���񒍕��̒����P��ID �F �\�񒍕��P��.���񒍕��̒����P��ID
            Long l_firstOrderUnitId = null;
            if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            }
            //�[��O�J�z�Ώۃt���O   �F �\�񒍕��P��.�[��O�J�z�Ώۃt���O
            boolean l_blnEveningSessionCarryoverFlag = false;
            if (BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                l_blnEveningSessionCarryoverFlag = true;
            }

            String l_strInstitutionCode =
                l_accountManager.getBranch(l_rsvIfoOrderUnitRow.getBranchId()).getInstitution().getInstitutionCode();

            //create�V�K���������e
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_rsvIfoOrderUnit.isBuyOrder(),
                    l_rsvIfoOrderUnit.getMarket().getMarketCode(),
                    (WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct(),
                    l_rsvIfoOrderUnit.getQuantity(),
                    l_rsvIfoOrderUnit.getRsvOrderExecPrice(),
                    IfoOrderExecutionConditionType.NONE,
                    l_rsvIfoOrderUnit.getExpirationTimestamp(),
                    WEB3OrderingConditionDef.DEFAULT,
                    0,
                    0,
                    null,
                    l_rsvIfoOrderUnit.getExpirationDateType(),
                    l_firstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            //validate�敨�V�K������
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
            //�V�K���������e�F�@@�쐬�����V�K���������e�I�u�W�F�N�g
            //�����P�ʁFnull
            NewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateFuturesOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec,
                    null);

            //{validate�敨�V�K������()}�����s�̏ꍇ�B
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate�敨�V�K������()}�����s�̏ꍇ�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{validate�敨�V�K������()}�����s�̏ꍇ�B");
            }

            //�萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            //(*)�萔���I�u�W�F�N�g�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
            //�萔��.�����`���l�� = �\�񒍕��P��.���񒍕��̒����`���l��
            //�萔��.�،���ЃR�[�h = �\�񒍕��P��.���XID����擾�����،���ЃR�[�h
            //�萔��.���XID = �\�񒍕��P��.���XID
            //�萔��.������ = �\�񒍕��P��.������
            //�萔��.����R�[�h(SONAR) = �h51�F���h
            //�萔��.�萔�����i�R�[�h = �h50�F�敨�h
            //�萔��.�ٍϋ敪 = �h00�F���̑��h
            //�萔��.is�w�l=�V�K���������e.isLimitOrder()
            //�萔��.�����Y�����R�[�h = �\�񒍕��P��.get����().get�����Y�����R�[�h()
            //�萔��.����=�\�񒍕��P��.��������
            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            String l_strCommissionProductCode = WEB3CommisionProductCodeDef.INDEX_FUTURES;
            l_commission.setOrderChannel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_commission.setInstitutionCode(l_strInstitutionCode);
            l_commission.setBranchId(l_rsvIfoOrderUnit.getBranchId());
            l_commission.setOrderBizDate(l_tsOrderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strCommissionProductCode);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_openContractOrderSpec.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct()).getUnderlyingProductCode());
            l_commission.setQuantity(l_rsvIfoOrderUnit.getQuantity());

            //calc�T�Z�����
            //[calc�T�Z�����()�Ɏw�肷�����]
            //�萔��    �F �쐬�����萔���I�u�W�F�N�g
            //�v�Z�P�� �F �\�񒍕��P��.get�\�񒍕����s�P��()
            //�⏕���� �F �擾�����⏕�����I�u�W�F�N�g
            //�敨OP������� �F �\�񒍕��P��.get�������()
            //���� �F �\�񒍕��P��.��������
            //isSkip���z�`�F�b�N �F�@@false
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_orderManager.calcEstimatePrice(
                    l_commission,
                    l_rsvIfoOrderUnit.getRsvOrderExecPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_rsvIfoOrderUnit.getTradedProduct(),
                    l_rsvIfoOrderUnit.getQuantity(),
                    false);

            //�敨OP�V�K���X�V�C���^�Z�v�^���쐬����B
            //[����]
            //�敨OP�V�K���������e �F �쐬�����V�K���������e�I�u�W�F�N�g
            WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor =
                new WEB3IfoOpenContractUpdateInterceptor(l_openContractOrderSpec);

            //(*)�C���^�Z�v�^�I�u�W�F�N�g�̃v���p�e�B�Ɉȉ��̒l���Z�b�g����B
            //�C���^�Z�v�^.�萔�� = �쐬�����萔���I�u�W�F�N�g
            l_ifoOpenContractUpdateInterceptor.setCommision(l_commission);
            //�C���^�Z�v�^.�T�Z��n����v�Z���� = calc�T�Z�����()�̖߂�l
            l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
                l_ifoEstimateDeliveryAmountCalcResult);
            //�C���^�Z�v�^.�������� = "�����Ȃ�"
            l_ifoOpenContractUpdateInterceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            //�C���^�Z�v�^.����敪 = �\�񒍕��P��.����敪
            l_ifoOpenContractUpdateInterceptor.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());
            //�C���^�Z�v�^.���҃R�[�h�iSONAR�j =�@@�\�񒍕��P��.���҃R�[�h�iSONAR�j
            l_ifoOpenContractUpdateInterceptor.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());
            //�C���^�Z�v�^.���񒍕��̒����`���l�� = �\�񒍕��P��.���񒍕��̒����`���l��
            l_ifoOpenContractUpdateInterceptor.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());
            //�C���^�Z�v�^.�����o�H�敪 = �\�񒍕��P��.�����o�H�敪
            l_ifoOpenContractUpdateInterceptor.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //validate����]��
            //�؋����̃`�F�b�N���s���B
            //[����]
            //�⏕���� �F �擾�����⏕�����I�u�W�F�N�g
            //�������e�C���^�Z�v�^[] �F �敨OP�V�K���X�V�C���^�Z�v�^��v�f�Ƃ����z��
            //�������e[] �F �V�K���������e��v�f�Ƃ����z��
            //������� �F
            //�@@����.�������e[0].isBuyToOpenOrder() == true�̏ꍇ�A"�敨�V�K����"
            //�@@�ȊO�̏ꍇ�A"�敨�V�K����"
            //�]�͍X�V�t���O �F false
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);

            WEB3IfoOpenContractUpdateInterceptor[] l_ifoOpenContractUpdateInterceptors =
                {l_ifoOpenContractUpdateInterceptor};
            WEB3IfoOpenContractOrderSpec[] l_orderSpecs = {l_openContractOrderSpec};

            OrderTypeEnum l_orderTypeEnum = null;
            if (l_orderSpecs[0].isBuyToOpenOrder())
            {
                l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
            }
            WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_ifoOpenContractUpdateInterceptors,
                l_orderSpecs,
                l_orderTypeEnum,
                false);

            OrderSubmissionResult l_orderSubmissionResult = null;
            //����]�͌���.is����t���O( )==true�̏ꍇ
            if (l_tradingPowerResult.isResultFlg())
            {
                //setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
                //�����}�l�[�W����ThreadLocal�ɁA�敨OP�V�K���X�V�C���^�Z�v�^���Z�b�g����B
                //[����]
                //arg0�i�敨OP�V�K���X�V�C���^�Z�v�^�j�F�@@���������敨OP�V�K���X�V�C���^�Z�v�^
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_ifoOpenContractUpdateInterceptor);

                //submitOpenContractOrder
                //�V�K��������o�^����B
                String l_strTradingPasswood =
                    l_subAccount.getMainAccount().getTradingPassword();
                WEB3Crypt l_crypt = new WEB3Crypt();
                String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);

                l_orderSubmissionResult = l_orderManager.submitOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec,
                    l_rsvIfoOrderUnit.getOrderId(),
                    l_strDecryptPassword,
                    true);
            }
            //����]�͌���.is����t���O( )==false�̏ꍇ
            //�]�̓G���[�̏�񂩂�A�\�񒍕��P�ʂɋL�^����G���[�R�[�h�����肷��B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //����]�͌��ʁF�@@validate����]��()�̖߂�l
            //�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
            else
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_subAccount);
            }

            //���s���ʂɉ����A�\�񒍕��P�ʂ�update����B
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set������To�\�񒍕��P��(ProductTypeEnum, long)(�A�������}�l�[�W��Impl::set������To�\�񒍕��P��)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B
                //�����^�C�v �F �����̗\�񒍕��P��.�����^�C�v
                //����ID �F �����̗\�񒍕��P��.����ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvIfoOrderUnit.getProductType(),
                    l_rsvIfoOrderUnit.getOrderId());
            }
            //���s���ʃG���[�B
            else
            {
                log.debug("{submitFuturesOpenContractOrder()}���G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{submitFuturesOpenContractOrder()}���G���[�B");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate�\�񒍕��P��(�敨OP�\�񒍕��P��Row, String)
            //[����]
            //�@@�敨OP�\�񒍕��P�ʍs �F �����̗\�񒍕��P�ʂ̍s�I�u�W�F�N�g
            //�@@�����G���[�R�[�h �F ����������O�I�u�W�F�N�g��ErrorInfo.error_code
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvIfoOrderUnitRow,
                l_strErrorCode);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�敨�ԍϒ���)<BR>
     * �敨�ԍϒ����𔭒�����B<BR>
     * �V�[�P���X�}�u�i�A�������敨OP�����ꌏ�T�[�r�X�jsubmit�敨�ԍϒ����v���Q�ƁB<BR>
     * ==========================================================<BR>
     * �V�[�P���X�} �F(�i�A�������敨OP�����ꌏ�T�[�r�X�jsubmit�敨�ԍϒ���)<BR>
     * ��̈ʒu�F(get�敨OP�\�񌚋ʕԍώw����ꗗ)<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02339<BR>
     * ==========================================================<BR>
     * @@param l_rsvIfoOrderUnit - (�敨OP�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF5498019A
     */
    public void submitFuturesSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitFuturesSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�G���[�R�[�h
        String l_strErrorCode = null;

        //�敨�\�񒍕��P��
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;

        //�敨�\�񒍕��X�V�T�[�r�X
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeSubAccount l_subAccount = null;
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        try
        {
            //get������(�m�F�������� : Date, ����敪 : String)
            //�m�F�������� �F �����̐敨OP�\�񒍕��P��.������
            //����敪 �F �����̐敨OP�\�񒍕��P��.����敪
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_rsvIfoOrderUnitRow.getSessionType());

            //�i*�j�⏕�������擾����
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = (WEB3GentradeSubAccount)l_accountMananger.getSubAccount(
                l_rsvIfoOrderUnit.getAccountId(),
                l_rsvIfoOrderUnit.getSubAccountId());

            //get�敨OP�\�񌚋ʕԍώw����ꗗ( )
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecs = l_rsvIfoOrderUnit.getContractsToClose();
            if (l_rsvIfoClosingContractSpecs == null)
            {
                log.debug("�O�񒍕��ɂ���茚�̏��A�܂��͕ԍώw��f�[�^�����݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02339,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�񒍕��ɂ���茚�̏��A�܂��͕ԍώw��f�[�^�����݂��܂���B");
            }

            //create���ώw��G���g��(�敨OP�\�񌚋ʕԍώw����s[])
            //�\��ԍώw����ꗗ �F get�敨OP�\�񌚋ʕԍώw����ꗗ()�̖߂�l
            SettleContractEntry[] l_settleContractEntries =
                this.createSettleContractEntries(l_rsvIfoClosingContractSpecs);

            //get�\�񒍕����s�P��( )
            double l_dblRsvOrderExecPrice = l_rsvIfoOrderUnit.getRsvOrderExecPrice();

            //����t���[�F�@@�\�񒍕��P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(�����ID : long)
                //�����ID �F �\�񒍕��P��.�����ID
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvIfoOrderUnit.getTraderId());
            }

            //[create�ԍϒ������e( )�F�����ݒ�d�l]
            //�،���ЃR�[�h �F �\�񒍕��P��.���XID����擾�����،���ЃR�[�h
            //���� �F �\�񒍕��P��.�����ID == null�̏ꍇ�Anull
            //          �\�񒍕��P��.�����ID <> null�̏ꍇ�A�擾�������҃I�u�W�F�N�g
            //�w�l �F �\�񒍕��P��.get�\�񒍕����s�P��()�̖߂�l
            //���s���� �F "�����Ȃ�"
            //���������� �F �\�񒍕��P��.�����������t
            //�ԍό��ʃG���g�� �F create���ώw��G���g��()�̖߂�l
            //�������� �F "�����Ȃ�"
            //�t�w�l��l �F 0
            //�iW�w�l�j�����w�l �F 0
            //�iW�w�l�j���s���� �F null
            //���������敪 �F �\�񒍕��P��.���������敪
            //���񒍕��̒����P��ID �F �\�񒍕��P��.���񒍕��̒����P��ID
            //�[��O�J�z�Ώۃt���O �F �\�񒍕��P��.�[��O�J�z�Ώۃt���O
            Long l_firstOrderUnitId = null;
            if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            }

            //�،���ЃR�[�h
            String l_strInstitutionCode =
                l_accountMananger.getBranch(l_rsvIfoOrderUnitRow.getBranchId()).getInstitution().getInstitutionCode();

            //�[��O�J�z�Ώۃt���O
            boolean l_blnEveningSessionCarryoverFlag = false;
            if (BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                l_blnEveningSessionCarryoverFlag = true;
            }

            //create�ԍϒ������e
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_dblRsvOrderExecPrice,
                    IfoOrderExecutionConditionType.NONE,
                    l_rsvIfoOrderUnit.getExpirationTimestamp(),
                    l_settleContractEntries,
                    WEB3OrderingConditionDef.DEFAULT,
                    0,
                    0,
                    null,
                    l_rsvIfoOrderUnit.getExpirationDateType(),
                    l_firstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            //validate�ԍϒ���(�⏕���� : SubAccount, �ԍϒ������e : IfoSettleContractOrderSpec)
            //�⏕���� �F �擾�����⏕�����I�u�W�F�N�g
            //�敨OP�ԍϒ������e �F �쐬�����敨OP�ԍϒ������e�I�u�W�F�N�g
            NewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec);
            //{validate�ԍϒ���()}�����s�̏ꍇ�B
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate�ԍϒ���()}�����s�̏ꍇ�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{validate�ԍϒ���()}�����s�̏ꍇ�B");
            }

            //�萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            // (*)�萔���I�u�W�F�N�g�Ɉȉ��̃v���p�e�B��ݒ肷��B
            //�����`���l�� = �\�񒍕��P��.���񒍕��̒����`���l��
            //�،���ЃR�[�h = �\�񒍕��P��.���XID����擾�����،���ЃR�[�h
            //���XID = �\�񒍕��P��.���XID
            //������ = �\�񒍕��P��.������
            //����R�[�h(SONAR) = �h52�F�ԍρh
            //�萔�����i�R�[�h = �h50�F�敨�h
            //�ٍϋ敪 = �h00�F���̑��h
            //is�w�l = �ԍϒ������e.isLimitOrder()
            //�����Y�����R�[�h = �\�񒍕��P��.get����().get�����Y�����R�[�h()
            //���v��敪 = �敨�����}�l�[�W��.get���v��敪()(*1)
            //���� = �ԍϒ������e.getTotalQuantity()
            //(*1) [�����ݒ�d�l]
            //create���ώw��G���g��()�̖߂�l
            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            String l_strCommissionProductCode = WEB3CommisionProductCodeDef.INDEX_FUTURES;
            l_commission.setOrderChannel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_commission.setInstitutionCode(l_strInstitutionCode);
            l_commission.setBranchId(l_rsvIfoOrderUnit.getBranchId());
            l_commission.setOrderBizDate(l_tsOrderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strCommissionProductCode);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct()).getUnderlyingProductCode());
            l_commission.setDayTradeType(l_orderManager.getDayTradeType(l_settleContractEntries));
            l_commission.setQuantity(l_settleContractOrderSpec.getTotalQuantity());

            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            long l_lngContractId = l_settleContractEntries[0].getContractId();
            //���ʂ͕ԍό��ʃG���g��[0].����ID���琶���������ʃI�u�W�F�N�g
            WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);

            //[calc�T�Z���ϑ��v( )�F�����ݒ�d�l]
            //�萔�� �F �쐬�����萔���I�u�W�F�N�g
            //�w�l �F �ԍϒ������e.getLimitPrice()
            //�⏕���� �F �擾�����⏕�����I�u�W�F�N�g
            //�敨OP������� �F �\�񒍕��P��.get�������()
            //�ԍό��ʃG���g��[] �F create���ώw��G���g��()�̖߂�l
            //���� �F �ԍϒ������e.getTotalQuantity()
            //���� �F (*)
            // ����.isLong() == true�̏ꍇ�ASideEnum.BUY
            // ����.isLong() == false�̏ꍇ�ASideEnum.SELL
            //isSkip���z�`�F�b�N�F false�i�X�L�b�v���Ȃ��j
            //(*)���ʂ͕ԍό��ʃG���g��[0].����ID���琶���������ʃI�u�W�F�N�g
            SideEnum l_side = null;
            if (l_contract.isLong())
            {
            	l_side = SideEnum.BUY;
            }
            else
            {
            	l_side = SideEnum.SELL;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult =
                l_orderManager.calcEstimateSettlementIncome(
                    l_commission,
                    l_settleContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_rsvIfoOrderUnit.getTradedProduct(),
                    l_settleContractEntries,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_side,
                    false);

            //�敨OP�ԍύX�V�C���^�Z�v�^(�敨OP�ԍϒ������e : �ԍϒ������e)
            //�敨OP�ԍϒ������e �F �쐬�����敨OP�ԍϒ������e�I�u�W�F�N�g
            WEB3IfoSettleContractUpdateInterceptor l_settleContractUpdateInterceptor =
                new WEB3IfoSettleContractUpdateInterceptor(l_settleContractOrderSpec);

            //(*)�C���^�Z�v�^�Ƀv���p�e�B���Z�b�g����B
            //[�ݒ肷��e�v���p�e�B]
            //�萔�� �F �쐬�����萔���I�u�W�F�N�g
            l_settleContractUpdateInterceptor.setCommision(l_commission);
            //�T�Z��n����v�Z���� �F calc�T�Z���ϑ��v()�̖߂�l
            l_settleContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);
            //�������� �F "�����Ȃ�"
            l_settleContractUpdateInterceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            //���Ϗ��� �F �\�񒍕��P��.���Ϗ���
            l_settleContractUpdateInterceptor.setSettleSequence(l_rsvIfoOrderUnitRow.getClosingOrder());
            //����敪 �F �\�񒍕��P��.����敪
            l_settleContractUpdateInterceptor.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());
            //���҃R�[�h�iSONAR�j �F �\�񒍕��P��.���҃R�[�h�iSONAR�j
            l_settleContractUpdateInterceptor.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());
            //���񒍕��̒����`���l�� = �\�񒍕��P��.���񒍕��̒����`���l��
            l_settleContractUpdateInterceptor.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());
            //�����o�H�敪 = �\�񒍕��P��.�����o�H�敪
            l_settleContractUpdateInterceptor.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            //arg0�i�敨OP�ԍύX�V�C���^�Z�v�^�j �F ���������敨OP�ԍύX�V�C���^�Z�v�^
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_settleContractUpdateInterceptor);

            //[submitSettleContractOrder()�F�����ݒ�d�l]
            //arg0�i�⏕�����j �F �擾�����⏕�����I�u�W�F�N�g
            //arg1�i�ԍϒ������e�j �F create�ԍϒ������e()�̖߂�l
            //arg2�i�����h�c�j �F �\�񒍕��P��.����ID
            //arg3�i����p�X���[�h�j �F �ڋq.getTradingPassword()�̖߂�l��decrypt�����l
            //arg4�iisSkip�����R���j �F true
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();

            OrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec,
                l_rsvIfoOrderUnit.getOrderId(),
                l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                true);

            //���s���ʂɉ����A�\�񒍕��P�ʂ�update����B
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set������To�\�񒍕��P��(ProductTypeEnum, long)(�A�������}�l�[�W��Impl::set������To�\�񒍕��P��)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B
                //�����^�C�v �F �����̗\�񒍕��P��.�����^�C�v
                //����ID �F �����̗\�񒍕��P��.����ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvIfoOrderUnit.getProductType(),
                    l_rsvIfoOrderUnit.getOrderId());
            }
            //���s���ʃG���[�B
            else
            {
                log.debug("{submitFuturesSettleContractOrder()}���G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{submitFuturesSettleContractOrder()}���G���[�B");
            }

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate�\�񒍕��P��(�敨OP�\�񒍕��P��Row, String)
            //[����]
            //�@@�敨OP�\�񒍕��P�ʍs �F �����̗\�񒍕��P�ʂ̍s�I�u�W�F�N�g
            //�@@�����G���[�R�[�h �F ����������O�I�u�W�F�N�g��ErrorInfo.error_code
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvIfoOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP�V�K������)<BR>
     * �I�v�V�����V�K�������𔭒�����B<BR>
     * �V�[�P���X�}�u�i�A�������敨OP�����ꌏ�T�[�r�X�jsubmitOP�V�K�������v���Q�ƁB<BR>
     * @@param l_rsvIfoOrderUnit - (�敨OP�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF5498019C
     */
    public void submitOptionsOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOptionsOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�G���[�R�[�h
        String l_strErrorCode = null;

        //�敨OP�\�񒍕��X�V�T�[�r�X
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;
        WEB3GentradeSubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        try
        {
            //�������^����ԑт̃`�F�b�N���s���B
            //�i�\�񒍕��o�^���̔������^����ԑтƁA
            //���ݓ������狁�߂��������^����ԑт��قȂ�ꍇ�͔����G���[�Ƃ���j
            //[����]
            //�@@�m�F�������� �F �����̐敨OP�\�񒍕��P��.������
            //�@@����敪 �F �����̐敨OP�\�񒍕��P��.����敪
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_rsvIfoOrderUnitRow.getSessionType());

            //����t���[�F�@@�\�񒍕��P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                //���ҁi�㗝���͎ҁj�I�u�W�F�N�g���擾����B
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B
                //�����ID�F�@@�\�񒍕��P��.�����ID
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvIfoOrderUnitRow.getTraderId());
            }

            //�⏕�������擾����
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_rsvIfoOrderUnit.getAccountId(),
                l_rsvIfoOrderUnitRow.getSubAccountId());

            //[create�V�K���������e()�Ɏw�肷�����]
            //�،���ЃR �[�h �F �\�񒍕��P��.���XID����擾�����،���ЃR�[�h
            //���� �F �\�񒍕��P��.�����ID==null�̏ꍇ�Anull
            //          �\�񒍕��P��.�����ID<>null�̏ꍇ�A�擾�������҃I�u�W�F�N�g
            //is���� �F �\�񒍕��P��.is������()
            //�s��R�[�h �F �\�񒍕��P��.get�s��().�s��R�[�h
            //���� �F �\�񒍕��P��.get����()
            //���� �F �\�񒍕��P��.��������
            //�w�l �F �\�񒍕��P��.get�\�񒍕����s�P��()
            //���s����   �F "�����Ȃ�"
            //�����������F�\�񒍕��P��.����������
            //��������   �F"�����Ȃ�"
            //�t�w�l��l       �F 0
            //�iW�w�l�j�����w�l �F 0
            //�iW�w�l�j���s���� �F null
            //���������敪             �F �\�񒍕��P��.���������敪
            //���񒍕��̒����P��ID �F �\�񒍕��P��.���񒍕��̒����P��ID
            Long l_firstOrderUnitId = null;
            if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            }
            //�[��O�J�z�Ώۃt���O   �F �\�񒍕��P��.�[��O�J�z�Ώۃt���O
            boolean l_blnEveningSessionCarryoverFlag = false;
            if (BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                l_blnEveningSessionCarryoverFlag = true;
            }

            String l_strInstitutionCode =
                l_accountManager.getBranch(l_rsvIfoOrderUnitRow.getBranchId()).getInstitution().getInstitutionCode();

            //create�V�K���������e
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_rsvIfoOrderUnit.isBuyOrder(),
                    l_rsvIfoOrderUnit.getMarket().getMarketCode(),
                    (WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct(),
                    l_rsvIfoOrderUnit.getQuantity(),
                    l_rsvIfoOrderUnit.getRsvOrderExecPrice(),
                    IfoOrderExecutionConditionType.NONE,
                    l_rsvIfoOrderUnit.getExpirationTimestamp(),
                    WEB3OrderingConditionDef.DEFAULT,
                    0,
                    0,
                    null,
                    l_rsvIfoOrderUnit.getExpirationDateType(),
                    l_firstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            //validate�V�K������(�⏕���� : SubAccount, �敨OP�V�K���������e : IfoOpenContractOrderSpec, �����P�� : IfoOrderUnit)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
            //�V�K���������e�F�@@�쐬�����V�K���������e�I�u�W�F�N�g
            //�����P�ʁFnull
            NewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec,
                    null);

            //{validate�V�K������()}�����s�̏ꍇ�B
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate�V�K������()}�����s�̏ꍇ�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{validate�V�K������()}�����s�̏ꍇ�B");
            }

            //�萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            //(*)�萔���I�u�W�F�N�g�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
            //�萔��.�����`���l�� = �\�񒍕��P��.���񒍕��̒����`���l��
            //�萔��.�،���ЃR�[�h = �\�񒍕��P��.���XID����擾�����،���ЃR�[�h
            //�萔��.���XID = �\�񒍕��P��.���XID
            //�萔��.������ = �\�񒍕��P��.������
            //�萔��.����R�[�h(SONAR) = �h51�F���h
            //�萔��.�萔�����i�R�[�h = �h51�F�����w��OP�h
            //�萔��.�ٍϋ敪 = �h00�F���̑��h
            //�萔��.is�w�l=�V�K���������e.isLimitOrder()
            //�萔��.�����Y�����R�[�h = �\�񒍕��P��.get����().get�����Y�����R�[�h()
            //�萔��.����=�\�񒍕��P��.��������
            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            String l_strCommissionProductCode = WEB3CommisionProductCodeDef.INDEX_OP;
            l_commission.setOrderChannel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_commission.setInstitutionCode(l_strInstitutionCode);
            l_commission.setBranchId(l_rsvIfoOrderUnit.getBranchId());
            l_commission.setOrderBizDate(l_tsOrderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strCommissionProductCode);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_openContractOrderSpec.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct()).getUnderlyingProductCode());
            l_commission.setQuantity(l_rsvIfoOrderUnit.getQuantity());

            //calc�T�Z��n���
            //[calc�T�Z��n���()�Ɏw�肷�����]
            //�萔��    �F �쐬�����萔���I�u�W�F�N�g
            //�w�l �F �\�񒍕��P��.get�\�񒍕����s�P��()
            //�⏕���� �F �擾�����⏕�����I�u�W�F�N�g
            //�敨OP������� �F �\�񒍕��P��.get�������()
            //���� �F �\�񒍕��P��.��������
            //�����F
            //�@@  �E�\�񒍕��P��.is������() == true�̏ꍇ
            // �@@   �@@��SideEnum.BUY
            // �@@�E��L�ȊO�̏ꍇ
            //   �@@�@@ ��SideEnum.SELL
            //is�ԍϒ����F�@@false
            //isSkip���z�`�F�b�N�F�@@false
            SideEnum l_sideEnum = null;
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = null;
            if (l_rsvIfoOrderUnit.isBuyOrder())
            {
                l_sideEnum = SideEnum.BUY;
            }
            else
            {
                l_sideEnum = SideEnum.SELL;
            }
            l_ifoEstimateDeliveryAmountCalcResult = l_orderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_rsvIfoOrderUnit.getRsvOrderExecPrice(),
                l_subAccount,
                (WEB3IfoTradedProductImpl)l_rsvIfoOrderUnit.getTradedProduct(),
                l_rsvIfoOrderUnit.getQuantity(),
                l_sideEnum,
                false,
                false);

            //�敨OP�V�K���X�V�C���^�Z�v�^���쐬����B
            //[����]
            //�敨OP�V�K���������e �F �쐬�����V�K���������e�I�u�W�F�N�g
            WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor =
                new WEB3IfoOpenContractUpdateInterceptor(l_openContractOrderSpec);

            //(*)�C���^�Z�v�^�I�u�W�F�N�g�̃v���p�e�B�Ɉȉ��̒l���Z�b�g����B
            //�C���^�Z�v�^.�萔�� = �쐬�����萔���I�u�W�F�N�g
            l_ifoOpenContractUpdateInterceptor.setCommision(l_commission);
            //�C���^�Z�v�^.�T�Z��n����v�Z���� = calc�T�Z��n���()�̖߂�l
            l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
                l_ifoEstimateDeliveryAmountCalcResult);
            //�C���^�Z�v�^.�������� = "�����Ȃ�"
            l_ifoOpenContractUpdateInterceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            //�C���^�Z�v�^.����敪 = �\�񒍕��P��.����敪
            l_ifoOpenContractUpdateInterceptor.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());
            //�C���^�Z�v�^.���҃R�[�h�iSONAR�j =�@@�\�񒍕��P��.���҃R�[�h�iSONAR�j
            l_ifoOpenContractUpdateInterceptor.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());
            //�C���^�Z�v�^.���񒍕��̒����`���l�� = �\�񒍕��P��.���񒍕��̒����`���l��
            l_ifoOpenContractUpdateInterceptor.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());
            //�C���^�Z�v�^.�����o�H�敪 = �\�񒍕��P��.�����o�H�敪
            l_ifoOpenContractUpdateInterceptor.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //validate����]��
            //�؋����̃`�F�b�N���s���B
            //[����]
            //�⏕���� �F �擾�����⏕�����I�u�W�F�N�g
            //�������e�C���^�Z�v�^[] �F �敨OP�V�K���X�V�C���^�Z�v�^��v�f�Ƃ����z��
            //�������e[] �F �V�K���������e��v�f�Ƃ����z��
            //������� �F
            // ����.�������e[0].isBuyToOpenOrder() == true�̏ꍇ�A"OP�V�K����"
            // �ȊO�̏ꍇ�A"OP�V�K����"
            //�]�͍X�V�t���O �F true
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);

            WEB3IfoOpenContractUpdateInterceptor[] l_ifoOpenContractUpdateInterceptors =
                {l_ifoOpenContractUpdateInterceptor};
            WEB3IfoOpenContractOrderSpec[] l_orderSpecs = {l_openContractOrderSpec};
            OrderTypeEnum l_orderTypeEnum = null;
            if (l_orderSpecs[0].isBuyToOpenOrder())
            {
                l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;
            }
            WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_ifoOpenContractUpdateInterceptors,
                l_orderSpecs,
                l_orderTypeEnum,
                true);

            OrderSubmissionResult l_orderSubmissionResult = null;
            //����]�͌���.is����t���O( )==true�̏ꍇ
            if (l_tradingPowerResult.isResultFlg())
            {
                //setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
                //�����}�l�[�W����ThreadLocal�ɁA�敨OP�V�K���X�V�C���^�Z�v�^���Z�b�g����B
                //[����]
                //arg0�i�敨OP�V�K���X�V�C���^�Z�v�^�j�F�@@���������敨OP�V�K���X�V�C���^�Z�v�^
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_ifoOpenContractUpdateInterceptor);

                //submitOpenContractOrder
                //�V�K��������o�^����B
                String l_strTradingPasswood =
                    l_subAccount.getMainAccount().getTradingPassword();
                WEB3Crypt l_crypt = new WEB3Crypt();
                String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);

                l_orderSubmissionResult = l_orderManager.submitOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec,
                    l_rsvIfoOrderUnit.getOrderId(),
                    l_strDecryptPassword,
                    true);
            }
            //����]�͌���.is����t���O( )==false�̏ꍇ
            //�]�̓G���[�̏�񂩂�A�\�񒍕��P�ʂɋL�^����G���[�R�[�h�����肷��B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //����]�͌��ʁF�@@validate����]��()�̖߂�l
            //�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
            else
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_subAccount);
            }

            //���s���ʂɉ����A�\�񒍕��P�ʂ�update����B
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set������To�\�񒍕��P��(ProductTypeEnum, long)(�A�������}�l�[�W��Impl::set������To�\�񒍕��P��)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B
                //�����^�C�v �F �����̗\�񒍕��P��.�����^�C�v
                //����ID �F �����̗\�񒍕��P��.����ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvIfoOrderUnit.getProductType(),
                    l_rsvIfoOrderUnit.getOrderId());
            }
            //���s���ʃG���[�B
            else
            {
                log.debug("{submitOptionsOpenContractOrder()}���G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{submitOptionsOpenContractOrder()}���G���[�B");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate�\�񒍕��P��(�敨OP�\�񒍕��P��Row, String)
            //[����]
            //�@@�敨OP�\�񒍕��P�ʍs �F �����̗\�񒍕��P�ʂ̍s�I�u�W�F�N�g
            //�@@�����G���[�R�[�h �F ����������O�I�u�W�F�N�g��ErrorInfo.error_code
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvIfoOrderUnitRow,
                l_strErrorCode);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP�ԍϒ���)<BR>
     * �I�v�V�����ԍϒ����𔭒�����B<BR>
     * �V�[�P���X�}�u�i�A�������敨OP�����ꌏ�T�[�r�X�jsubmitOP�ԍϒ����v���Q�ƁB<BR>
     * ==========================================================<BR>
     * �V�[�P���X�} �F(�i�A�������敨OP�����ꌏ�T�[�r�X�jsubmit�敨�ԍϒ���)<BR>
     * ��̈ʒu�F(get�敨OP�\�񌚋ʕԍώw����ꗗ)<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02339<BR>
     * ==========================================================<BR>
     * @@param l_rsvIfoOrderUnit - (�敨OP�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF5498019E
     */
    public void submitOptionsSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOptionsSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�敨OP�\�񒍕��P��
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;

        //�G���[�R�[�h
        String l_strErrorCode = null;

        //�敨OP�\�񒍕��X�V�T�[�r�X
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeSubAccount l_subAccount = null;

        try
        {
            //get������(�m�F�������� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�m�F���������F�@@�����̐敨OP�\�񒍕��P��.������
            //����敪 �F �����̐敨OP�\�񒍕��P��.����敪
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_rsvIfoOrderUnitRow.getSessionType());

            //�⏕�������擾����
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_rsvIfoOrderUnit.getAccountId(),
                l_rsvIfoOrderUnitRow.getSubAccountId());

            //get�敨OP�\�񌚊��ԍώw����ꗗ( )(�敨OP�\�񒍕��P��Impl::get�敨OP�\�񌚊��ԍώw����ꗗ)
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows =
            	l_rsvIfoOrderUnit.getContractsToClose();

            if (l_rsvIfoClosingContractSpecRows == null)
            {
            	log.debug("�O�񒍕��ɂ���茚�̏��A�܂��͕ԍώw��f�[�^�����݂��܂���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02339,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�񒍕��ɂ���茚�̏��A�܂��͕ԍώw��f�[�^�����݂��܂���B");
            }

            //create���ώw��G���g��(�敨OP�\�񌚊��ԍώw����s[])(�A�������敨OP�����ꌏ�T�[�r�XImpl::create���ώw��G���g��)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�\��ԍώw����ꗗ �F get�敨OP�\�񌚋ʕԍώw����ꗗ()�̖߂�l
            SettleContractEntry[] l_settleContractOrderEntries =
                this.createSettleContractEntries(l_rsvIfoClosingContractSpecRows);

            //get�\�񒍕����s�P��( )(�敨OP�\�񒍕��P��Impl::get�\�񒍕����s�P��)
            double l_dblRsvOrderExecPrice = l_rsvIfoOrderUnit.getRsvOrderExecPrice();

            //����t���[�F�@@�\�񒍕��P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(�����ID : long)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����
                //  �����ID�F�@@�\�񒍕��P��.�����
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvIfoOrderUnit.getTraderId());
            }

            //[create�ԍϒ������e( )�F�����ݒ�d�l]
            //�،���ЃR�[�h �F �\�񒍕��P��.���XID����擾�����،���ЃR�[�h
            //���� �F �\�񒍕��P��.�����ID == null�̏ꍇ�Anull
            //        �\�񒍕��P��.�����ID <> null�̏ꍇ�A�擾�������҃I�u�W�F�N�g
            //�w�l �F �\�񒍕��P��.get�\�񒍕����s�P��()�̖߂�l
            //���s���� �F "�����Ȃ�"
            //���������� �F �\�񒍕��P��.�����������t
            //�ԍό��ʃG���g�� �F create���ώw��G���g��()�̖߂�l
            //�������� �F "�����Ȃ�"
            //�t�w�l��l �F 0
            //�iW�w�l�j�����w�l �F 0
            //�iW�w�l�j���s���� �F null
            //���������敪 �F �\�񒍕��P��.���������敪
            //���񒍕��̒����P��ID �F �\�񒍕��P��.���񒍕��̒����P��ID
            //�[��O�J�z�Ώۃt���O �F �\�񒍕��P��.�[��O�J�z�Ώۃt���O
            Long l_firstOrderUnitId = null;
            if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
            	l_firstOrderUnitId = new Long(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            }
            String l_strInstitutionCode =
                l_accountManager.getBranch(l_rsvIfoOrderUnitRow.getBranchId()).getInstitution().getInstitutionCode();

            boolean l_blnEveningSessionCarryoverFlag = false;
            if (BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                l_blnEveningSessionCarryoverFlag = true;
            }

            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            	WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
            		l_strInstitutionCode,
                    l_trader,
                    l_dblRsvOrderExecPrice,
                    IfoOrderExecutionConditionType.NONE,
                    l_rsvIfoOrderUnitRow.getExpirationDate(),
                    l_settleContractOrderEntries,
                    WEB3OrderingConditionDef.DEFAULT,
                    0,
                    0,
                    null,
                    l_rsvIfoOrderUnitRow.getExpirationDateType(),
                    l_firstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            //validate�ԍϒ���(�⏕���� : SubAccount, �M�p�ԍϒ������e : EqTypeSettleContractOrderSpec)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            //  �M�p�ԍϒ������e�F�@@�쐬�����M�p�ԍϒ������e�I�u�W�F�N�g
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();
            NewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec);

            //{validate�ԍϒ���()}�����s�̏ꍇ�B
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate�ԍϒ���()}�����s�̏ꍇ�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{validate�ԍϒ���()}�����s�̏ꍇ�B");
            }

            //�萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            //(*)�萔���I�u�W�F�N�g�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
            //�����`���l�� = �\�񒍕��P��.���񒍕��̒����`���l��
            //�،���ЃR�[�h = �\�񒍕��P��.���XID����擾�����،���ЃR�[�h
            //���XID = �\�񒍕��P��.���XID
            //������ = �\�񒍕��P��.������
            //����R�[�h(SONAR) =  �h52�F�ԍρh
            //�萔�����i�R�[�h =  �h51�F�����w���n�o�h
            //�ٍϋ敪 = �h00�F���̑��h
            //is�w�l=�V�K���������e.isLimitOrder()
            //�����Y�����R�[�h = �\�񒍕��P��.get����().get�����Y�����R�[�h()
            //���v��敪 = OP�����}�l�[�W��.get���v��敪()(*1)
            //     (*1) [�����ݒ�d�l]
            //     create���ώw��G���g��()�̖߂�l
            //���� = �ԍϒ������e.getTotalQuantity()
            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            String l_strCommisionProductCodeDef = WEB3CommisionProductCodeDef.INDEX_OP;
            l_commission.setOrderChannel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_commission.setInstitutionCode(l_strInstitutionCode);
            l_commission.setBranchId(l_rsvIfoOrderUnit.getBranchId());
            l_commission.setOrderBizDate(l_tsOrderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strCommisionProductCodeDef);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_rsvIfoOrderUnit.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct()).getUnderlyingProductCode());
            l_commission.setDayTradeType(l_orderManager.getDayTradeType(l_settleContractOrderEntries));
            l_commission.setQuantity(l_settleContractOrderSpec.getTotalQuantity());

            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            long l_lngContractId = l_settleContractOrderEntries[0].getContractId();
            //���ʂ͕ԍό��ʃG���g��[0].����ID���琶���������ʃI�u�W�F�N�g
            WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);

            //[calc�T�Z��n���()�F�����ݒ�d�l]
            //  �萔���F�@@�쐬�����萔���I�u�W�F�N�g
            //  �w�l�F�@@ �ԍϒ������e.getLimitPrice()
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            //  �敨OP������� �F �\�񒍕��P��.get�������()
            //  ���� �F �ԍϒ������e.getTotalQuantity()
            //  ���� �F (*)
            //    ����.isLong() == true�̏ꍇ�ASideEnum.BUY
            //    ����.isLong() == false�̏ꍇ�ASideEnum.SELL
            //    (*)���ʂ͕ԍό��ʃG���g��[0].����ID���琶���������ʃI�u�W�F�N�g
            //  is�ԍϒ��� �F true
            //  isSkip���z�`�F�b�N �F false�i�X�L�b�v���Ȃ��j
            SideEnum l_sideEnum = null;
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = null;
            if (l_contract.isLong())
            {
                l_sideEnum = SideEnum.BUY;
            }
            else
            {
                l_sideEnum = SideEnum.SELL;
            }
            l_ifoEstimateDeliveryAmountCalcResult =
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_settleContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_rsvIfoOrderUnit.getTradedProduct(),
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_sideEnum,
                    true,
                    false);
            //�敨OP�ԍύX�V�C���^�Z�v�^���쐬����B
            //�敨OP�ԍϒ������e �F �쐬�����敨OP�ԍϒ������e�I�u�W�F�N�g
            WEB3IfoSettleContractUpdateInterceptor l_closeIfoUpdateInterceptor =
                new WEB3IfoSettleContractUpdateInterceptor(
                    l_settleContractOrderSpec);

            //(*)�C���^�Z�v�^�Ƀv���p�e�B���Z�b�g����B
            //[�ݒ肷��e�v���p�e�B]
            //�萔�� �F �쐬�����萔���I�u�W�F�N�g
            //�T�Z��n����v�Z���� �F calc�T�Z��n���()�̖߂�l
            //�������� �F "�����Ȃ�"
            //���Ϗ��� �F �\�񒍕��P��.���Ϗ���
            //����敪 �F �\�񒍕��P��.����敪
            //���҃R�[�h�iSONAR�j �F �\�񒍕��P��.���҃R�[�h�iSONAR�j
            //���񒍕��̒����`���l�� = �\�񒍕��P��.���񒍕��̒����`���l��
            //�����o�H�敪 = �\�񒍕��P��.�����o�H�敪
            l_closeIfoUpdateInterceptor.setCommision(l_commission);
            l_closeIfoUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
                l_ifoEstimateDeliveryAmountCalcResult);
            l_closeIfoUpdateInterceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            l_closeIfoUpdateInterceptor.setSettleSequence(l_rsvIfoOrderUnitRow.getClosingOrder());
            l_closeIfoUpdateInterceptor.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());
            l_closeIfoUpdateInterceptor.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());
            l_closeIfoUpdateInterceptor.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_closeIfoUpdateInterceptor.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //�����}�l�[�W����ThreadLocal�ɁA�敨OP�ԍύX�V�C���^�Z�v�^���Z�b�g����B
            //[����]
            //arg0�i�敨OP�ԍύX�V�C���^�Z�v�^�j �F ���������敨OP�ԍύX�V�C���^�Z�v�^
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_closeIfoUpdateInterceptor);

            //[submitSettleContractOrder( )�F�����ݒ�d�l]
            //  arg0�i�⏕�����j�F�@@�擾�����⏕�����I�u�W�F�N�g
            //  arg1�i�ԍϒ������e�j�F�@@create�ԍϒ������e()�̖߂�l
            //  arg2�i�����h�c�j�F�@@�\�񒍕��P��.get����ID()
            //  arg3�i����p�X���[�h�j�F�@@�ڋq.getTradingPassword()�̖߂�l��decrypt�����l
            //  arg4�iisSkip�����R���j�F�@@true
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();

            OrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec,
                    l_rsvIfoOrderUnit.getOrderId(),
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);

            //�I�v�V�������������i�⏕����.�⏕�����^�C�v��"�����I�v�V������������i�敨�؋����j�j�̏ꍇ
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                //�]�͍Čv�Z(�⏕���� : �⏕����)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B
                //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerReCalcService)Services.getService(
                        WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
            }

            //���s���ʂɉ����A�\�񒍕��P�ʂ�update����
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set������To�\�񒍕��P��(ProductTypeEnum, long)(�A�������}�l�[�W��Impl::set������To�\�񒍕��P��)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B
                //  �����^�C�v�F�@@�����̗\�񒍕��P��.�����^�C�v
                //  ����ID�F�@@�����̗\�񒍕��P��.����ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvIfoOrderUnit.getProductType(),
                    l_rsvIfoOrderUnit.getOrderId());
            }
            else
            {
                log.debug("{submitOptionsSettleContractOrder()}���G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{submitOptionsSettleContractOrder()}���G���[�B");
            }

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate�\�񒍕��P��(�敨OP�\�񒍕��P�ʍs : �敨OP�\�񒍕��P��Row, �����G���[�R�[�h : String)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�敨OP�\�񒍕��P�ʍs �F �����̗\�񒍕��P�ʂ̍s�I�u�W�F�N�g
            //�����G���[�R�[�h �F ����������O�I�u�W�F�N�g��ErrorInfo.error_code
            //�����G���[�R�[�h�ɂ́A�G���[�����̓��肪�\��
            //BusinessError�^SystemError�̃G���[�R�[�h���Z�b�g����B
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvIfoOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���ώw��G���g��)<BR>
     * �����̐敨OP�\�񌚋ʕԍώw����ꗗ���A<BR>
     * �ԍϒ����ւ̌��ώw��G���g���i�z��j���쐬���ԋp����B<BR>
     * <BR>
     * �����̗\��ԍώw����ꗗ�̗v�f�iindex�j�����A�ȉ��̏������J��Ԃ��B<BR>
     * LOOP�I����A�쐬�����C���X�^���X�̔z���ԋp����B<BR>
     * <BR>
     * ���������@@START LOOP�@@��������<BR>
     * <BR>
     * �P�j�@@�ԍώw��Ώۂ̌��ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�敨OP���ʂ̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@[�����ݒ�d�l]<BR>
     * �@@�@@�@@�@@����ID �F �\��ԍώw����ꗗ[index].����ID<BR>
     * <BR>
     * �Q�j�@@�ԍϐ��ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�ԍϐ��� = �\��ԍώw����ꗗ[index].�ԍϒ�������<BR>
     * <BR>
     * �@@�@@�@@���ԍϐ��� == 0�̏ꍇ�́A���̗v�f�̏������s���icontinue�j<BR>
     * <BR>
     * �R�j�@@�ԍω\���ʎc�����v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�ԍω\���ʎc�� = ����.���ʐ� �| ����.getLockedQuantity()<BR>
     * <BR>
     * �@@�@@�@@�����ʂ͂P�j�Ŏ擾��������<BR>
     * <BR>
     * �S�j�@@�ԍϐ��ʂ��`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�@@�i�Q�j�Ŏ擾�����ԍϐ��� �� �R�j�Ōv�Z�����ԍω\���ʎc���j�̏ꍇ�A<BR>
     * �@@�@@�@@�u���ʎc���s���G���[�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_03082<BR>
     * <BR>
     * �T�j�@@SettleContractEntry�̃C���X�^���X�𐶐����A�߂�l��append����B<BR>
     * <BR>
     * �@@�@@�@@[�����ݒ�d�l]<BR>
     * �@@�@@�@@�@@����ID �F �\��ԍώw����ꗗ[index].����ID<BR>
     * �@@�@@�@@�@@�ԍϐ��� �F �Q�j�Ŏ擾�����ԍϐ���<BR>
     * <BR>
     * ���������@@END LOOP�@@��������<BR>
     * @@param l_rsvIfoClosingContractSpecs - (�\��ԍώw����ꗗ)<BR>
     * �敨OP�\�񌚋ʕԍώw����s�I�u�W�F�N�g�̔z��B<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 47DF549801A0
     */
    protected SettleContractEntry[] createSettleContractEntries(
        RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecs) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntries(RsvIfoClosingContractSpecRow[])";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoClosingContractSpecs == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���ώw��G���g�����X�g����`
        List l_lisContractOrderEntries = new ArrayList();

        try
        {
            //���ώw��G���g���z��̒��x
            int l_intContractSpecLength = l_rsvIfoClosingContractSpecs.length;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getPositionManager();

            //���������@@START LOOP�@@��������
            for (int i = 0; i < l_intContractSpecLength; i++)
            {
                //�P�j�@@�ԍώw��Ώۂ̌��ʃI�u�W�F�N�g���擾����B
                //�敨OP���ʂ̃C���X�^���X�𐶐�����B
                //[�����ݒ�d�l]
                //����ID �F �\��ԍώw����ꗗ[index].����ID
                long l_lngContractId = l_rsvIfoClosingContractSpecs[i].getContractId();
                WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);

                //�Q�j�@@�ԍϐ��ʂ��擾����B
                //�@@�@@�@@�ԍϐ��� = �\��ԍώw����ꗗ[index].�ԍϒ�������
                //�@@�@@�@@�ԍϐ��� == 0�̏ꍇ�́A���̗v�f�̏������s���icontinue�j
                double l_dblClosingQuantity = l_rsvIfoClosingContractSpecs[i].getQuantity();
                BigDecimal l_bdClosingQuantity = new BigDecimal(String.valueOf(l_dblClosingQuantity));
                if (GtlUtils.Double.isZero(l_dblClosingQuantity))
                {
                    continue;
                }

                double l_dblQuantity = l_contract.getQuantity();
                BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));

                double l_dblLockedQuantity = l_contract.getLockedQuantity();
                BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_dblLockedQuantity));

                //�R�j�@@�ԍω\���ʎc�����v�Z����B
                //�@@�@@�@@�ԍω\���ʎc�� = ����.���ʐ� �| ����.getLockedQuantity()
                //      �����ʂ͂P�j�Ŏ擾��������
                BigDecimal l_bdCloseContractQuantity = l_bdQuantity.subtract(l_bdLockedQuantity);
                //�S�j�@@�ԍϐ��ʂ��`�F�b�N����B
                // �@@�@@�i�Q�j�Ŏ擾�����ԍϐ��� �� �R�j�Ōv�Z�����ԍω\���ʎc���j�̏ꍇ�A
                // �@@�@@�u���ʎc���s���G���[�v�̗�O��throw����B
                if (l_bdClosingQuantity.compareTo(l_bdCloseContractQuantity) > 0)
                {
                    log.debug("���ʎc���s���G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03082,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʎc���s���G���[�B");
                }

                //�T�j�@@SettleContractEntry�̃C���X�^���X�𐶐����A�߂�l��append����B
                //�@@�@@�@@[�����ݒ�d�l]
                //�@@�@@�@@[�R���X�g���N�^�����ݒ�d�l]
                //�@@�@@�@@����ID �F �\��ԍώw����ꗗ[index].����ID
                //�@@�@@�@@�ԍϐ��� �F �Q�j�Ŏ擾�����ԍϐ���
                SettleContractEntry l_settlecontractEntry =
                    new SettleContractEntry(l_lngContractId, l_dblClosingQuantity);

                l_lisContractOrderEntries.add(l_settlecontractEntry);
            }
            //���������@@ENDLOOP�@@��������
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɏd������Y���f�[�^�����݂��܂��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���ώw��G���g�����X�g��null�ꍇ
        if (l_lisContractOrderEntries == null || l_lisContractOrderEntries.size() == 0)
        {
        	log.exiting(STR_METHOD_NAME);
            return null;
        }

        SettleContractEntry[] l_settleContractEntries =
            new SettleContractEntry[l_lisContractOrderEntries.size()];
        l_lisContractOrderEntries.toArray(l_settleContractEntries);

        log.exiting(STR_METHOD_NAME);
        return l_settleContractEntries;
    }
}
@
