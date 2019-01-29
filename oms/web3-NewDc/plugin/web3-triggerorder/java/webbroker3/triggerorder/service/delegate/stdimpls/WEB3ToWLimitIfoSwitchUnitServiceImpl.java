head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitIfoSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�����敨OP�ؑֈꌏ�T�[�r�XImpl(WEB3ToWLimitIfoSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/23�@@�юu��(���u) �V�K�쐬
Revesion History : 2006/11/10  ������(���u)�@@���f��No.186
Revesion History : 2006/11/29  �����(���u)�@@���f��No.200,DB�X�V�d�l No.034
Revesion History : 2007/01/31  �g��i(���u)�@@�d�l�ύX�@@���f��No.213
Revesion History : 2007/06/30  �Ј���(���u)�@@�d�l�ύX�@@���f��No.240
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitIfoSwitchUpdateInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitIfoSwitchUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (W�w�l�����敨OP�ؑֈꌏ�T�[�r�XImpl)<BR>
 * W�w�l�����敨OP�ؑֈꌏ�T�[�r�X�����N���X<BR>
 *
 * @@author �юu��
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUnitServiceImpl implements WEB3ToWLimitIfoSwitchUnitService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitIfoSwitchUnitServiceImpl.class);

    /**
     * @@roseuid 44E9077B0203
     */
    public WEB3ToWLimitIfoSwitchUnitServiceImpl()
    {

    }

    /**
     * (submit�敨�V�K��W�w�l����)<BR>
     * �敨�V�K��W�w�l�����ؑ֏������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iW�w�l�����敨OP�ؑֈꌏ�T�[�r�X�jsubmit�敨�V�K��W�w�l�����v�Q�ƁB<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨�I�v�V���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923E370100
     */
    public void submitFuturesOpenContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitFuturesOpenContractWLimitOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        //�����œn���ꂽ�����P�ʂ͍X�V����Ă���\��������̂ŁA�Ď擾���s�����ƁB
        try
        {
            l_orderUnit =
                (IfoOrderUnit)l_futuresOrderManagerImpl.getOrderUnit(
                    l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1.1)getSubAccount()
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
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

        //1.2)is�����Ώ�()
        boolean l_blnIsProcessObject = this.isProcessObject(l_orderUnit);

        //1.3)�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
        if (!l_blnIsProcessObject)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.4)validate�ؑ֏����\()
        this.validateSwitchPossible(l_orderUnit);

        //1.5) (*)validate�ؑ֏����\()�ɂė�O���X���[���ꂽ�ꍇ
        try
        {
            //1.6) get������(�m�F�������� : Date, ����敪 : String)
            Date l_datBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate, l_ifoOrderUnitRow.getSessionType());

            //1.7)�i����t���[�F�����P��.�����ID��null�̏ꍇ�̂݁j
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_ifoProductManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            Trader l_trader = null;
            WEB3GentradeMarket l_market = null;
            WEB3IfoProductImpl l_productImpl = null;

            try
            {
                if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                {
                    l_trader =
                        l_finObjectManager.getTrader(l_ifoOrderUnitRow.getTraderId());
                }
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
                l_productImpl =
                    (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_ifoOrderUnitRow.getProductId());

            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

            //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            //1.8)create�V�K���������e
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            double l_dblWLimitPrice = l_ifoOrderUnitRow.getWLimitPrice();
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                l_ifoOrderUnitRow.getWLimitExecCondType();
            Timestamp l_expirationDate = l_ifoOrderUnitRow.getExpirationDate();

            //������
            Date l_datBiz = l_datBizDate;

            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblQuantity,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_expirationDate,
                    l_datBiz,
                    l_ifoOrderUnitRow.getOrderConditionType(),
                    l_ifoOrderUnitRow.getOrderCondOperator(),
                    l_ifoOrderUnitRow.getStopPriceType(),
                    l_ifoOrderUnitRow.getStopOrderPrice(),
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryoverFlag);

            //isSkip�x���󋵃`�F�b�N
            boolean l_blnIsSkipDelayStatusCheck = true;
            //1.9) validate�敨�V�K����������()
            OrderValidationResult l_orderValidationResult =
                l_futuresOrderManagerImpl.validateFuturesChangeOrder(
                    l_subAccount,
                    l_ifoOpenContractChangeSpec,
                    l_blnIsSkipDelayStatusCheck);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.10)create�萔��()
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_ifoOrderUnitRow.getOrderUnitId());

            //1.11)is�w�l�i�ؑ֌�j()
            l_commission.setIsLimitPrice(this.isLimitPriceAfterSwitch(l_orderUnit));

            //1.11)get�������()
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct =
                    l_ifoProductManager.getIfoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_productImpl.getProductCode(),
                        l_market.getMarketCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.13)calc�������T�Z�����()
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_futuresOrderManagerImpl.calcChangeEstimatePrice(
                    l_commission,
                    l_ifoOpenContractChangeSpec.getAfterChangePrice(),
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity(),
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getExecutedAmount(),
                    false);
            //1.14)�敨OP�V�K�������X�V�C���^�Z�v�^()
            WEB3IfoOpenContractChangeUpdateInterceptor l_interceptor =
                new WEB3IfoOpenContractChangeUpdateInterceptor(l_ifoOpenContractChangeSpec);

            //1.15)(*)�v���p�e�B�Z�b�g
            //�萔��           �� �쐬�����萔���I�u�W�F�N�g
            l_interceptor.setCommision(l_commission);

            //�T�Z��n����v�Z����    �� calc�������T�Z��n���()�̖߂�l
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);

            //��������          �� �����P�ʂ̓�������
            l_interceptor.setOrderCond(l_ifoOrderUnitRow.getOrderConditionType());

            //�����������Z�q       �� �����P�ʂ̓�������
            l_interceptor.setOrderCondOperator(l_ifoOrderUnitRow.getOrderCondOperator());

            //�t�w�l��l�^�C�v     �� �����P�ʂ̓�������
            l_interceptor.setStopOrderBasePriceType(l_ifoOrderUnitRow.getStopPriceType());

            //�t�w�l��l        �� �����P�ʂ̓�������
            l_interceptor.setStopOrderBasePrice(l_ifoOrderUnitRow.getStopOrderPrice());

            //�iW�w�l�j�����w�l     �� �����P�ʂ̓�������
            l_interceptor.setWLimitPriceChange(l_ifoOrderUnitRow.getWLimitPrice());

            //�����ID         �� �����P��.�����ID��null�̏ꍇ�A0�i�Œ�j�B�ȊO�A�����P��.�����ID
            if (l_ifoOrderUnitRow.getTraderIdIsNull())
            {
                l_interceptor.setTraderId(0);
            }
            else
            {
                l_interceptor.setTraderId(l_ifoOrderUnitRow.getTraderId());
            }

            //�����o�H�敪        �� �����P�ʂ̓�������
            l_interceptor.setOrderRootDiv(l_ifoOrderUnitRow.getOrderRootDiv());

            //1.16)validate����]��()
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            WEB3TPTradingPowerResult l_tpResult =
                l_tpTradingPowerService.validateTradingPower(
                    l_subAccount,
                    new Object[]{l_interceptor},
                    new Object[]{l_ifoOpenContractChangeSpec},
                    l_ifoOrderUnitRow.getOrderType(),
                    true);

            //1.17)throw�]�̓G���[���()
            if (!l_tpResult.isResultFlg())
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                l_futuresOrderManagerImpl.throwTpErrorInfo(l_tpResult, l_subAccount);
            }

            //1.18) (���s���ʂɉ����Ē����n�f�[�^��UPDATE����)
            //1.18.1(*)����I�������ꍇ
            //1.18.1.1)W�w�l�����敨OP�ؑ֍X�V�C���^�Z�v�^
            WEB3GentradeTrader l_gentradeTrader = null;
            if (l_trader != null)
            {
                l_gentradeTrader = (WEB3GentradeTrader)l_trader;
            }
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_wLimitIfoSwitchUpdateInterceptor =
                new WEB3ToWLimitIfoSwitchUpdateInterceptor(
                    l_gentradeTrader,
                    l_ifoEstimateDeliveryAmountCalcResult,
                    l_ifoOpenContractChangeSpec);

            //1.18.2)setThreadLocalPersistenceEventInterceptor()
            l_futuresOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(
                l_wLimitIfoSwitchUpdateInterceptor);

            //1.18.3)submitChangeOrder()
            String l_strTradingPasswood =
                l_subAccount.getMainAccount().getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);
            OrderSubmissionResult l_orderSubmissionResult =
                l_futuresOrderManagerImpl.submitChangeOrder(
                    l_subAccount,
                    l_ifoOpenContractChangeSpec,
                    l_strDecryptPassword,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.18.2)(*)�������ɗ�O���X���[���ꂽ�ꍇ
        catch (Exception l_ex)
        {
            //1.18.2.1)get�����G���[���R�R�[�h(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_strErrorReasonCode = null;
            if (l_errorInfo != null)
            {
                l_strErrorReasonCode =
                    l_futuresOrderManagerImpl.getErrorReasonCode(l_errorInfo.getErrorCode());
            }   

            //1.18.2.2) update�ؑ֎��s()
            this.updateSwitchFail(l_orderUnit, l_strErrorReasonCode);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�敨�ԍ�W�w�l����)<BR>
     * �敨�ԍ�W�w�l�����ؑ֏������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iW�w�l�����敨OP�ؑֈꌏ�T�[�r�X�jsubmit�敨�ԍ�W�w�l�����v�Q�ƁB<BR>
     *  ========================================================== <BR>
     * 1.8.1)(*)�ԍω\���ʃ`�F�b�N�@@<BR>
     * �R�j�ԍϐ���(*1) �� �ԍω\���ʎc��(*2)�@@�̏ꍇ�A<BR>
     * �@@�u�ԍω\�c�����ʒ��߃G���[�v�̗�O��throw����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00299<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨�I�v�V���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923E370110
     */
    public void submitFuturesSettleContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitFuturesSettleContractWLimitOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        //�����œn���ꂽ�����P�ʂ͍X�V����Ă���\��������̂ŁA�Ď擾���s�����ƁB
        try
        {
            l_orderUnit =
                (IfoOrderUnit)l_futuresOrderManagerImpl.getOrderUnit(
                    l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1.1)getSubAccount()
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
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

        //1.2)is�����Ώ�()
        boolean l_blnIsProcessObject = this.isProcessObject(l_orderUnit);

        //1.2.1)�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
        if (!l_blnIsProcessObject)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.3)validate�ؑ֏����\()
        this.validateSwitchPossible(l_orderUnit);

        //1.4) (*)validate�ؑ֏����\()�ɂė�O���X���[���ꂽ�ꍇ
        try
        {
            //1.5) get������(�m�F�������� : Date, ����敪 : String)
            Date l_datBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate, l_ifoOrderUnitRow.getSessionType());

            IfoContractSettleOrderUnit l_contractSettleOrderUnit =
                (IfoContractSettleOrderUnit)l_orderUnit;

            //1.6)getContractsToClose()
            IfoClosingContractSpec[] l_ifoClosingContractSpecList =
                l_contractSettleOrderUnit.getContractsToClose();

            //1.7)ArrayList()
            List l_lisSettleContractEntry = new ArrayList();

            //1.8)�igetContractsToClose( )�̖߂�l�i�����ʕԍώw����j�v�f��(index)���ALoop�j
            int l_intLength = 0;
            if (l_ifoClosingContractSpecList != null)
            {
                l_intLength = l_ifoClosingContractSpecList.length;
            }
            for (int i = 0; i < l_intLength; i++)
            {
                //1.8.1)(*)�ԍω\���ʃ`�F�b�N
                //���ʕԍώw����[index].�ԍϒ�������
                double l_dblContractsQuantity = l_ifoClosingContractSpecList[i].getQuantity();
                BigDecimal l_bdContractsQuantity = new BigDecimal(l_dblContractsQuantity);

                //���ʕԍώw����[index].�ԍϖ�萔��
                double l_dblExecutedQuantity = l_ifoClosingContractSpecList[i].getExecutedQuantity();
                BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity);

                //�@@�ԍϐ��� �� ���ʕԍώw����[index].�ԍϒ������� �| ���ʕԍώw����[index].�ԍϖ�萔��
                BigDecimal l_bdCloseQuantity = l_bdContractsQuantity.subtract(l_bdExecutedQuantity);

                //1.8.1.1)�敨OP����()
                WEB3IfoContractImpl l_ifoContractImpl = null;
                try
                {
                    l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoClosingContractSpecList[i].getContractId());
                }
                catch (DataNetworkException l_dne)
                {
                    log.error(STR_METHOD_NAME, l_dne);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                catch (DataQueryException l_dqe)
                {
                    log.error(STR_METHOD_NAME, l_dqe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //1.8.1.2)getQuantity( )
                double l_dblQuantity = l_ifoContractImpl.getQuantity();
                BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);

                //1.8.1.3)getLockedQuantity( )
                double l_dblLockedQuantity = l_ifoContractImpl.getLockedQuantity();
                BigDecimal l_bdLockedQuantity = new BigDecimal(l_dblLockedQuantity);

                //1.8.1.4)get���b�N������()
                double l_dblLockedQuantityReal = l_ifoContractImpl.getLockedQuantity(l_orderUnit.getOrderUnitId());
                BigDecimal l_bdLockedQuantityReal = new BigDecimal(l_dblLockedQuantityReal);

                //�ԍω\���ʎc�� �� ����.getQuantity()�i�����ʐ��ʁj �| ����.getLockedQuantity()�i�����b�N�����ʁj
                //�{ ����.get���b�N������(�����P��ID)�i�����Y�������b�N�����ʁj
                BigDecimal l_bdContractBalance = l_bdQuantity.subtract(l_bdLockedQuantity).add(l_bdLockedQuantityReal);

                //�ԍϐ���(*1) �� �ԍω\���ʎc��(*2)�@@�̏ꍇ�A�u�ԍω\�c�����ʒ��߃G���[�v�̗�O��throw����B
                if (l_bdCloseQuantity.compareTo(l_bdContractBalance) > 0)
                {
                    log.debug("�ԍω\�c�����ʒ��߃G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //1.8.1.5)SettleContractEntry()
                SettleContractEntry l_settleContractEntry =
                    new SettleContractEntry(
                        l_ifoClosingContractSpecList[i].getContractId(),
                        l_dblContractsQuantity);

                //1.8.1.6)add()
                l_lisSettleContractEntry.add(l_settleContractEntry);
            }

            SettleContractEntry[] l_settleContractEntryList =
                new SettleContractEntry[l_lisSettleContractEntry.size()];
            l_lisSettleContractEntry.toArray(l_settleContractEntryList);

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_ifoProductManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            Trader l_trader = null;
            WEB3GentradeMarket l_market = null;
            WEB3IfoProductImpl l_productImpl = null;

            try
            {
                //1.9)�i����t���[�F�����P��.�����ID��null�̏ꍇ�̂݁j
                if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                {
                    //1.9.1)getTrader()
                    l_trader =
                        l_finObjectManager.getTrader(l_ifoOrderUnitRow.getTraderId());
                }
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
                l_productImpl =
                    (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_ifoOrderUnitRow.getProductId());

            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

            //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            //1.10)create�ԍϒ������e
            double l_dblWLimitPrice = l_ifoOrderUnitRow.getWLimitPrice();
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                l_ifoOrderUnitRow.getWLimitExecCondType();
            Timestamp l_expirationDate = l_ifoOrderUnitRow.getExpirationDate();

            //������
            Date l_datBiz = l_datBizDate;

            WEB3IfoChangeSettleContractOrderSpec l_ifoChangeSettleContractOrderSpec =
                WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblWLimitPrice,
                    l_settleContractEntryList,
                    l_wLimitExecCondType,
                    l_expirationDate,
                    l_datBiz,
                    l_ifoOrderUnitRow.getOrderConditionType(),
                    l_ifoOrderUnitRow.getOrderCondOperator(),
                    l_ifoOrderUnitRow.getStopPriceType(),
                    l_ifoOrderUnitRow.getStopOrderPrice(),
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryoverFlag);

            //isSkip�x���󋵃`�F�b�N
            boolean l_blnIsSkipDelayStatusCheck = true;
            //1.11)validate�敨�ԍϒ�������()
            OrderValidationResult l_orderValidationResult =
                l_futuresOrderManagerImpl.validateFuturesChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoChangeSettleContractOrderSpec,
                    l_blnIsSkipDelayStatusCheck);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //1.12)create�萔��()
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_ifoOrderUnitRow.getOrderUnitId());

            //1.13)is�w�l�i�ؑ֌�j()
            l_commission.setIsLimitPrice(this.isLimitPriceAfterSwitch(l_orderUnit));

            //1.13)get�������()
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct =
                    l_ifoProductManager.getIfoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_productImpl.getProductCode(),
                        l_market.getMarketCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //����:        
            //�����P��.getSide()��SideEnum.BUY(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B
            //�����P��.getSide()��SideEnum.SELL(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B
            SideEnum l_side = null;        
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }

            //1.15) calc�������T�Z���ϑ��v()
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_futuresOrderManagerImpl.calcChangeEstimateSettlementIncome(
                    l_commission,
                    l_ifoChangeSettleContractOrderSpec.getAfterChangePrice(),
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_settleContractEntryList,
                    l_ifoChangeSettleContractOrderSpec.getAfterChangeTotalQuantity(),
                    l_side,
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    false);

            //1.16.1.1)W�w�l�����敨OP�ؑ֍X�V�C���^�Z�v�^()
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_interceptor =
                new WEB3ToWLimitIfoSwitchUpdateInterceptor(
                    (WEB3GentradeTrader)l_trader,
                    l_ifoEstimateDeliveryAmountCalcResult,
                    l_ifoChangeSettleContractOrderSpec);

            //1.15.1.3)setThreadLocalPersistenceEventInterceptor()
            l_futuresOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(l_interceptor);

            //1.15.1.4)submitChangeSettleContractOrder()
            String l_strTradingPasswood =
                l_subAccount.getMainAccount().getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);
            OrderSubmissionResult l_orderSubmissionResult =
                l_futuresOrderManagerImpl.submitChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoChangeSettleContractOrderSpec,
                    l_strDecryptPassword,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = "
                    + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            //1.15.2.1)get�����G���[���R�R�[�h(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_strErrorReasonCode = null;
            if (l_errorInfo != null)
            {
                l_strErrorReasonCode =
                    l_futuresOrderManagerImpl.getErrorReasonCode(l_errorInfo.getErrorCode());
            } 

            //1.15.3) update�ؑ֎��s()
            this.updateSwitchFail(l_orderUnit, l_strErrorReasonCode);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP�V�K��W�w�l����)<BR>
     * �I�v�V�����V�K��W�w�l�����ؑ֏������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iW�w�l�����敨OP�ؑֈꌏ�T�[�r�X�jsubmitOP�V�K��W�w�l�����v�Q�ƁB<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨�I�v�V���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923E37012F
     */
    public void submitOptionOpenContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOptionOpenContractWLimitOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //�����œn���ꂽ�����P�ʂ͍X�V����Ă���\��������̂ŁA�Ď擾���s�����ƁB
        try
        {
            l_orderUnit =
                (IfoOrderUnit)l_optionOrderManagerImpl.getOrderUnit(
                    l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1.1)getSubAccount()
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
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

        //1.2)is�����Ώ�()
        boolean l_blnIsProcessObject = this.isProcessObject(l_orderUnit);

        //1.3)�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
        if (!l_blnIsProcessObject)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.4)validate�ؑ֏����\()
        this.validateSwitchPossible(l_orderUnit);

        //1.5) (*)validate�ؑ֏����\()�ɂė�O���X���[���ꂽ�ꍇ
        try
        {
            //1.6) get������(�m�F�������� : Date, ����敪 : String)
            Date l_datBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate, l_ifoOrderUnitRow.getSessionType());

            //1.7)�i����t���[�F�����P��.�����ID��null�̏ꍇ�̂݁j
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_ifoProductManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            Trader l_trader = null;
            WEB3GentradeMarket l_market = null;
            WEB3IfoProductImpl l_productImpl = null;

            try
            {
                if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                {
                    l_trader =
                        l_finObjectManager.getTrader(l_ifoOrderUnitRow.getTraderId());
                }
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
                l_productImpl =
                    (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_ifoOrderUnitRow.getProductId());

            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

            //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            //1.8)create�V�K���������e()
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            double l_dblWLimitPrice = l_ifoOrderUnitRow.getWLimitPrice();
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                l_ifoOrderUnitRow.getWLimitExecCondType();
            Timestamp l_expirationDate = l_ifoOrderUnitRow.getExpirationDate();

            //������
            Date l_datBiz = l_datBizDate;

            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblQuantity,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_expirationDate,
                    l_datBiz,
                    l_ifoOrderUnitRow.getOrderConditionType(),
                    l_ifoOrderUnitRow.getOrderCondOperator(),
                    l_ifoOrderUnitRow.getStopPriceType(),
                    l_ifoOrderUnitRow.getStopOrderPrice(),
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryoverFlag);

            //isSkip�x���󋵃`�F�b�N
            boolean l_blnIsSkipDelayStatusCheck = true;
            //1.9) validate�V�K����������()
            OrderValidationResult l_orderValidationResult =
                l_optionOrderManagerImpl.validateChangeOrder(
                    l_subAccount,
                    l_ifoOpenContractChangeSpec,
                    l_blnIsSkipDelayStatusCheck);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.10)create�萔��()
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_ifoOrderUnitRow.getOrderUnitId());

            //1.11)is�w�l()
            l_commission.setIsLimitPrice(this.isLimitPriceAfterSwitch(l_orderUnit));

            //1.11)get�������()
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct =
                    l_ifoProductManager.getIfoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_productImpl.getProductCode(),
                        l_market.getMarketCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.13)calc�������T�Z��n���()
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_optionOrderManagerImpl.calcChangeEstimateDeliveryAmount(
                    l_commission,
                    l_ifoOpenContractChangeSpec.getAfterChangePrice(),
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity(),
                    l_orderUnit.getSide(),
                    false,
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getExecutedAmount(),
                    false);

            //1.14)�敨OP�V�K�������X�V�C���^�Z�v�^()
            WEB3IfoOpenContractChangeUpdateInterceptor l_interceptor =
                new WEB3IfoOpenContractChangeUpdateInterceptor(l_ifoOpenContractChangeSpec);

            //1.15)(*)�v���p�e�B�Z�b�g
            //�萔��           �� �쐬�����萔���I�u�W�F�N�g
            l_interceptor.setCommision(l_commission);

            //�T�Z��n����v�Z����    �� calc�������T�Z��n���()�̖߂�l
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);

            //��������          �� �����P�ʂ̓�������
            l_interceptor.setOrderCond(l_ifoOrderUnitRow.getOrderConditionType());

            //�����������Z�q       �� �����P�ʂ̓�������
            l_interceptor.setOrderCondOperator(l_ifoOrderUnitRow.getOrderCondOperator());

            //�t�w�l��l�^�C�v     �� �����P�ʂ̓�������
            l_interceptor.setStopOrderBasePriceType(l_ifoOrderUnitRow.getStopPriceType());

            //�t�w�l��l        �� �����P�ʂ̓�������
            l_interceptor.setStopOrderBasePrice(l_ifoOrderUnitRow.getStopOrderPrice());

            //�iW�w�l�j�����w�l     �� �����P�ʂ̓�������
            l_interceptor.setWLimitPriceChange(l_ifoOrderUnitRow.getWLimitPrice());

            //�����ID         �� �����P��.�����ID��null�̏ꍇ�A0�i�Œ�j�B�ȊO�A�����P��.�����ID
            if (l_ifoOrderUnitRow.getTraderIdIsNull())
            {
                l_interceptor.setTraderId(0);
            }
            else
            {
                l_interceptor.setTraderId(l_ifoOrderUnitRow.getTraderId());
            }

            //�����o�H�敪        �� �����P�ʂ̓�������
            l_interceptor.setOrderRootDiv(l_ifoOrderUnitRow.getOrderRootDiv());

            //1.16)validate����]��()
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            WEB3TPTradingPowerResult l_tpResult =
                l_tpTradingPowerService.validateTradingPower(
                    l_subAccount,
                    new Object[]{l_interceptor},
                    new Object[]{l_ifoOpenContractChangeSpec},
                    l_ifoOrderUnitRow.getOrderType(),
                    true);

            //1.17)throw�]�̓G���[���()
            if (!l_tpResult.isResultFlg())
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                l_optionOrderManagerImpl.throwTpErrorInfo(l_tpResult, l_subAccount);
            }

            //1.18) (���s���ʂɉ����Ē����n�f�[�^��UPDATE����)
            //1.18.1(*)����I�������ꍇ
            //1.18.1.1)W�w�l�����敨OP�ؑ֍X�V�C���^�Z�v�^
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_wLimitIfoSwitchUpdateInterceptor =
                new WEB3ToWLimitIfoSwitchUpdateInterceptor(
                    (WEB3GentradeTrader)l_trader,
                    l_ifoEstimateDeliveryAmountCalcResult,
                    l_ifoOpenContractChangeSpec);

            //1.18.2)setThreadLocalPersistenceEventInterceptor()
            l_optionOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(
                l_wLimitIfoSwitchUpdateInterceptor);

            //1.18.3)submitChangeOrder()
            String l_strTradingPasswood =
                l_subAccount.getMainAccount().getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);
            OrderSubmissionResult l_orderSubmissionResult =
                l_optionOrderManagerImpl.submitChangeOrder(
                    l_subAccount,
                    l_ifoOpenContractChangeSpec,
                    l_strDecryptPassword,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            //1.18.2.1)get�����G���[���R�R�[�h(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_strErrorReasonCode = null;
            if (l_errorInfo != null)
            {
                l_strErrorReasonCode =
                    l_optionOrderManagerImpl.getErrorReasonCode(l_errorInfo.getErrorCode());
            }

            //1.18.2.2) update�ؑ֎��s()
            this.updateSwitchFail(l_orderUnit, l_strErrorReasonCode);

            //1.18.2.3) (*)�I�v�V�������������i�⏕����.�⏕�����^�C�v��"�����I�v�V������������i�敨�؋����j�j�̏ꍇ
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                //1.18.2.3.1)�]�͍Čv�Z(�⏕���� : �⏕����)(����]�̓T�[�r�X::�]�͍Čv�Z)
                WEB3TPTradingPowerService l_tpTradingPowerService =
                    (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP�ԍ�W�w�l����)<BR>
     * �I�v�V�����ԍ�W�w�l�����ؑ֏������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iW�w�l�����敨OP�ؑֈꌏ�T�[�r�X�jsubmitOP�ԍ�W�w�l�����v�Q�ƁB<BR>
     *  ========================================================== <BR>
     * 1.8.1)(*)�ԍω\���ʃ`�F�b�N�@@<BR>
     * �R�j�ԍϐ���(*1) �� �ԍω\���ʎc��(*2)�@@�̏ꍇ�A<BR>
     * �@@�u�ԍω\�c�����ʒ��߃G���[�v�̗�O��throw����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00299<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨�I�v�V���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923E37014F
     */
    public void submitOptionSettleContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOptionSettleContractWLimitOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //�����œn���ꂽ�����P�ʂ͍X�V����Ă���\��������̂ŁA�Ď擾���s�����ƁB
        try
        {
            l_orderUnit =
                (IfoOrderUnit)l_optionOrderManagerImpl.getOrderUnit(
                    l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1.1)getSubAccount()
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
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

        //1.2)is�����Ώ�()
        boolean l_blnIsProcessObject = this.isProcessObject(l_orderUnit);

        //1.2.1)�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
        if (!l_blnIsProcessObject)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.3)validate�ؑ֏����\()
        this.validateSwitchPossible(l_orderUnit);

        //1.4) (*)validate�ؑ֏����\()�ɂė�O���X���[���ꂽ�ꍇ
        try
        {
            //1.5) get������(�m�F�������� : Date, ����敪 : String)
            Date l_datBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate, l_ifoOrderUnitRow.getSessionType());

            IfoContractSettleOrderUnit l_contractSettleOrderUnit =
                (IfoContractSettleOrderUnit)l_orderUnit;

            //1.6)getContractsToClose()
            IfoClosingContractSpec[] l_ifoClosingContractSpecList =
                l_contractSettleOrderUnit.getContractsToClose();

            //1.7)ArrayList()
            List l_lisSettleContractEntry = new ArrayList();

            //1.8)�igetContractsToClose( )�̖߂�l�i�����ʕԍώw����j�v�f��(index)���ALoop�j
            int l_intLength = l_ifoClosingContractSpecList.length;
            for (int i = 0; i < l_intLength; i++)
            {
                //1.8.1)(*)�ԍω\���ʃ`�F�b�N
                //���ʕԍώw����[index].�ԍϒ�������
                double l_dblContractsQuantity = l_ifoClosingContractSpecList[i].getQuantity();
                BigDecimal l_bdContractsQuantity = new BigDecimal(l_dblContractsQuantity);

                //���ʕԍώw����[index].�ԍϖ�萔��
                double l_dblExecutedQuantity = l_ifoClosingContractSpecList[i].getExecutedQuantity();
                BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity);

                //�@@�ԍϐ��� �� ���ʕԍώw����[index].�ԍϒ������� �| ���ʕԍώw����[index].�ԍϖ�萔��
                BigDecimal l_bdCloseQuantity = l_bdContractsQuantity.subtract(l_bdExecutedQuantity);

                //1.8.1.1)�敨OP����()
                WEB3IfoContractImpl l_ifoContractImpl = null;
                try
                {
                    l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoClosingContractSpecList[i].getContractId());
                }
                catch (DataNetworkException l_dne)
                {
                    log.error(STR_METHOD_NAME, l_dne);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                catch (DataQueryException l_dqe)
                {
                    log.error(STR_METHOD_NAME, l_dqe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //1.8.1.2)getQuantity( )
                double l_dblQuantity = l_ifoContractImpl.getQuantity();
                BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);

                //1.8.1.3)getLockedQuantity( )
                double l_dblLockedQuantity = l_ifoContractImpl.getLockedQuantity();
                BigDecimal l_bdLockedQuantity = new BigDecimal(l_dblLockedQuantity);

                //1.8.1.4)get���b�N������()
                double l_dblLockedQuantityReal = l_ifoContractImpl.getLockedQuantity(l_orderUnit.getOrderUnitId());
                BigDecimal l_bdLockedQuantityReal = new BigDecimal(l_dblLockedQuantityReal);

                //�ԍω\���ʎc�� �� ����.getQuantity()�i�����ʐ��ʁj �| ����.getLockedQuantity()�i�����b�N�����ʁj
                //�{ ����.get���b�N������(�����P��ID)�i�����Y�������b�N������
                BigDecimal l_bdContractBalance = l_bdQuantity.subtract(l_bdLockedQuantity).add(l_bdLockedQuantityReal);

                //�ԍϐ���(*1) �� �ԍω\���ʎc��(*2)�@@�̏ꍇ�A�u�ԍω\�c�����ʒ��߃G���[�v�̗�O��throw����B
                if (l_bdCloseQuantity.compareTo(l_bdContractBalance) > 0)
                {
                    log.debug("�ԍω\�c�����ʒ��߃G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //1.8.1.5)SettleContractEntry()
                SettleContractEntry l_settleContractEntry =
                    new SettleContractEntry(
                        l_ifoClosingContractSpecList[i].getContractId(),
                        l_dblContractsQuantity);

                //1.8.1.6)add()
                l_lisSettleContractEntry.add(l_settleContractEntry);
            }

            SettleContractEntry[] l_settleContractEntryList =
                new SettleContractEntry[l_lisSettleContractEntry.size()];
            l_lisSettleContractEntry.toArray(l_settleContractEntryList);

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_ifoProductManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            Trader l_trader = null;
            WEB3GentradeMarket l_market = null;
            WEB3IfoProductImpl l_productImpl = null;

            try
            {
                //1.9)�i����t���[�F�����P��.�����ID��null�̏ꍇ�̂݁j
                if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                {
                    //1.9.1)getTrader()
                    l_trader =
                        l_finObjectManager.getTrader(l_ifoOrderUnitRow.getTraderId());
                }
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
                l_productImpl =
                    (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_ifoOrderUnitRow.getProductId());

            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //���������敪 = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

            //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            //1.10)create�ԍϒ������e()
            double l_dblWLimitPrice = l_ifoOrderUnitRow.getWLimitPrice();
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                l_ifoOrderUnitRow.getWLimitExecCondType();
            Timestamp l_expirationDate = l_ifoOrderUnitRow.getExpirationDate();

            //������
            Date l_datBiz = l_datBizDate;

            WEB3IfoChangeSettleContractOrderSpec l_ifoChangeSettleContractOrderSpec =
                WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblWLimitPrice,
                    l_settleContractEntryList,
                    l_wLimitExecCondType,
                    l_expirationDate,
                    l_datBiz,
                    l_ifoOrderUnitRow.getOrderConditionType(),
                    l_ifoOrderUnitRow.getOrderCondOperator(),
                    l_ifoOrderUnitRow.getStopPriceType(),
                    l_ifoOrderUnitRow.getStopOrderPrice(),
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryoverFlag);

            //isSkip�x���󋵃`�F�b�N
            boolean l_blnIsSkipDelayStatusCheck = true;
            //1.11)validate�ԍϒ�������()
            OrderValidationResult l_orderValidationResult =
                l_optionOrderManagerImpl.validateChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoChangeSettleContractOrderSpec,
                    l_blnIsSkipDelayStatusCheck);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.12)create�萔��()
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_ifoOrderUnitRow.getOrderUnitId());

            //1.13)is�w�l()
            l_commission.setIsLimitPrice(this.isLimitPriceAfterSwitch(l_orderUnit));

            //1.13)get�������()
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct =
                    l_ifoProductManager.getIfoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_productImpl.getProductCode(),
                        l_market.getMarketCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.15)calc�������T�Z��n���()
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_optionOrderManagerImpl.calcChangeEstimateDeliveryAmount(
                    l_commission,
                    l_ifoChangeSettleContractOrderSpec.getAfterChangePrice(),
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_ifoChangeSettleContractOrderSpec.getAfterChangeTotalQuantity(),
                    l_orderUnit.getSide(),
                    true,
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getExecutedAmount(),
                    false);

            //1.16.1.1)W�w�l�����敨OP�ؑ֍X�V�C���^�Z�v�^()
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_interceptor =
                new WEB3ToWLimitIfoSwitchUpdateInterceptor(
                    (WEB3GentradeTrader)l_trader,
                    l_ifoEstimateDeliveryAmountCalcResult,
                    l_ifoChangeSettleContractOrderSpec);

            //1.15.1.3)setThreadLocalPersistenceEventInterceptor()
            l_optionOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(l_interceptor);

            //1.18.3)submitChangeSettleContractOrder()
            String l_strTradingPasswood =
                l_subAccount.getMainAccount().getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);
            OrderSubmissionResult l_orderSubmissionResult =
                l_optionOrderManagerImpl.submitChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoChangeSettleContractOrderSpec,
                    l_strDecryptPassword,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            //1.18.2.1)get�����G���[���R�R�[�h(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_strErrorReasonCode = null;
            if (l_errorInfo != null)
            {
                l_strErrorReasonCode =
                    l_optionOrderManagerImpl.getErrorReasonCode(l_errorInfo.getErrorCode());
            }

            //1.18.2.2) update�ؑ֎��s()
            this.updateSwitchFail(l_orderUnit, l_strErrorReasonCode);
        }

        //1.16) (*)�I�v�V�������������i�⏕����.�⏕�����^�C�v��"�����I�v�V������������i�敨�؋����j�j�̏ꍇ
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            //1.16.1)�]�͍Čv�Z(�⏕���� : �⏕����)(����]�̓T�[�r�X::�]�͍Čv�Z)
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (is�����Ώ�)<BR>
     * �w��̒�����W�w�l�ؑւ̏����Ώۂł��邩�𔻒肷��B<BR>
     * �����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �����ΏۊO�Ƃ��Afalse��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �E�p�����[�^.�����P��.�����L����� != "�I�[�v��"<BR>
     * �E�p�����[�^.�����P��.�������� != "W�w�l"<BR>
     * �E�p�����[�^.�����P��.���N�G�X�g�^�C�v == "�����T�[�o"<BR>
     * �@@ or "�ؑ֊���" or "����"<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 44924963027C
     */
    protected boolean isProcessObject(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isProcessObject(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (!OrderOpenStatusEnum.OPEN.equals(l_ifoOrderUnitRow.getOrderOpenStatus())
            || !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType())
            || WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType())
            || WEB3RequestTypeDef.TRANSFERED.equals(l_ifoOrderUnitRow.getRequestType())
            || WEB3RequestTypeDef.INVALIDATE.equals(l_ifoOrderUnitRow.getRequestType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate�ؑ֏����\)<BR>
     * �w��̒�����W�w�l�ؑւ̏����\�ł��邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@������ԃ`�F�b�N<BR>
     * �@@�������s�ꖢ���M�i*�j�̏ꍇ�A�܂��́A<BR>
     * �@@�p�����[�^.�����P��.������Ԃ��ȉ��̂����ꂩ��<BR>
     * �@@�Y������ꍇ�A�u��t���^�������^������̒����͐ؑ֏����s�v��<BR>
     * �@@��O���X���[����B<BR>
     * �@@�@@�E"��t�ρi�ύX�����j"<BR>
     * �@@�@@�E"�������i�ύX�����j"<BR>
     * �@@�@@�E"��t�ρi��������j"<BR>
     * �@@�@@�E"�������i��������j"<BR>
     * <BR>
     * �i*�j�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�NaN�̏ꍇ�A<BR>
     * �@@�s�ꖢ���M�̒����Ɣ��肷��B<BR>
     * class:WEB3BusinessLayerException<BR>
     * tag  :BUSINESS_ERROR_02521<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     * @@roseuid 449249EE00E5
     */
    protected void validateSwitchPossible(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSwitchPossible(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_ifoOrderUnitRow.getConfirmedQuantityIsNull()
            || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatus)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            log.debug("��t���^�������^������̒����͐ؑ֏����s�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02521,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�ؑ֎��s)<BR>
     * �����̒����P�ʂ�ؑ֎��s��ԂɍX�V����B<BR>
     * <BR>
     * OP�����}�l�[�W��.update�����f�[�^()���R�[������B<BR>
     * <BR>
     * [update�����f�[�^()�Ɏw�肷�����] <BR>
     * �@@�����P�ʁF�@@�X�V�l���Z�b�g���������P�� <BR>
     * �@@is�����쐬�F�@@true�i�쐬����j<BR>
     * <BR>
     * ���X�V�l�̐ݒ�d�l�ɂ��ẮA<BR>
     * DB�X�V�d�l <BR>
     * �uW�w�l�����ؑցiNG�j_�����P�ʃe�[�u���d�l�v�Q�ƁB<BR>
     * <BR>
     * ���p�����[�^.�����P��.������� == "OP�V�K��������"�̏ꍇ�A<BR>
     * �@@�ȉ��̎菇�ɂĊT�Z��n����̍Čv�Z���s���B <BR>
     * �@@�i�X�g�b�v�����̒����P���ŗ]�͂��S������Ă���\��������ׁA<BR>
     * �@@�@@���~�b�g�����̒����P���ōČv�Z���s���B�j <BR>
     * <BR>
     * �@@OP�����}�l�[�W��.get�X�g�b�v�����������T�Z����v�Z����()���R�[������B<BR>
     * <BR>
     * �@@[get�X�g�b�v�����������T�Z����v�Z����()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@����.�����P��<BR>
     * �@@�@@�⏕�����F�@@����.�����P��.�⏕����ID�ɊY������⏕����<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strErrorReasonCode - (�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h<BR>
     * <BR>
     * DB���C�A�E�g<BR>
     * �����P�ʃe�[�u���d�l.xls<BR>
     * �u�i�����P�ʃe�[�u���⑫�j�����G���[���R�R�[�h�v�V�[�g�Q�ƁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 449634E80241
     */
    protected void updateSwitchFail(IfoOrderUnit l_orderUnit, String l_strErrorReasonCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSwitchFail(IfoOrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams(l_ifoOrderUnitRow);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //���������ŏI�ʔ�
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(
            l_ifoOrderUnitParams.getLastOrderActionSerialNo() + 1);

        //����������
        l_ifoOrderUnitParams.setOrgOrderConditionType(
            l_ifoOrderUnitParams.getOrderConditionType());

        //�������������Z�q
        l_ifoOrderUnitParams.setOrgOrderCondOperator(
            l_ifoOrderUnitParams.getOrderCondOperator());

        //���t�w�l��l�^�C�v
        l_ifoOrderUnitParams.setOrgStopPriceType(
            l_ifoOrderUnitParams.getStopPriceType());

        //���t�w�l��l
        if (l_ifoOrderUnitParams.getStopOrderPriceIsNull())
        {
            l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_ifoOrderUnitParams.setOrgStopOrderPrice(
                l_ifoOrderUnitParams.getStopOrderPrice());
        }

        //���iW�w�l�j�����w�l
        if (l_ifoOrderUnitParams.getWLimitPriceIsNull())
        {
            l_ifoOrderUnitParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_ifoOrderUnitParams.setOrgWLimitPrice(
                l_ifoOrderUnitParams.getWLimitPrice());
        }

        //���iW�w�l�j���s����
        l_ifoOrderUnitParams.setOrgWLimitExecCondType(
            l_ifoOrderUnitParams.getWLimitExecCondType());

        //��������
        l_ifoOrderUnitParams.setOrderConditionType(
            WEB3OrderingConditionDef.DEFAULT);

        //������� 11�F�������s�i�ύX�����j
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);

        //�����������Z�q
        l_ifoOrderUnitParams.setOrderCondOperator(null);

        //�t�w�l��l�^�C�v
        l_ifoOrderUnitParams.setStopPriceType(null);

        //�t�w�l��l
        l_ifoOrderUnitParams.setStopOrderPrice(null);

        //�iW�w�l�j�����w�l
        l_ifoOrderUnitParams.setWLimitPrice(null);

        //this.������� == "OP�V�K��������"�̏ꍇ
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnitParams.getOrderType()))
        {
            //OP�����}�l�[�W��.get�X�g�b�v�����������T�Z����v�Z����()���R�[������B
            //[get�X�g�b�v�����������T�Z����v�Z����()�Ɏw�肷�����]
            //�����P�ʁF�@@����.�����P��
            //�⏕�����F�@@����.�����P��.�⏕����ID�ɊY������⏕����
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
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
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_optionOrderManagerImpl.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);

            if (l_ifoOrderUnitParams.getLimitPriceIsNull())
            {
                //�����P��
                l_ifoOrderUnitParams.setPrice(null);

                //�s�ꂩ��m�F�ς݂̒����P��
                l_ifoOrderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                //�����P��
                l_ifoOrderUnitParams.setPrice(l_ifoOrderUnitParams.getLimitPrice());

                //�s�ꂩ��m�F�ς݂̒����P��
                l_ifoOrderUnitParams.setConfirmedOrderPrice(
                    l_ifoOrderUnitParams.getLimitPrice());
            }

            //�T�Z��n���
            double l_dblEstimateDeliveryAmount =
                l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
            l_ifoOrderUnitParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);

            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_ifoOrderUnitParams.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);
        }

        //���������E����敪
        l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);

        //�����G���[���R�R�[�h
        l_ifoOrderUnitParams.setErrorReasonCode(l_strErrorReasonCode);

        //���N�G�X�g�^�C�v
        l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

        //�iW�w�l�j���s����
        l_ifoOrderUnitParams.setWLimitExecCondType(null);
        //�X�V���t
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //OP�����}�l�[�W��.update�����f�[�^()���R�[������B
        IfoOrderUnit l_orderUnitUpdate =
            (IfoOrderUnit)l_optionOrderManagerImpl.toOrderUnit(l_ifoOrderUnitParams);
        l_optionOrderManagerImpl.updateOrderData(l_orderUnitUpdate, true);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�w�l(�ؑ֌�))<BR>
     * �X�g�b�v�����֐ؑ֌�̂v�w�l�������w�l�������ǂ������ʂ���B<BR>
     * <BR>
     * �p�����[�^.�����P��.(�v�w�l)�����w�l != 0�̏ꍇ�A<BR>
     * true(�w�l)��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isLimitPriceAfterSwitch(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isLimitPriceAfterSwitch(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�敨OP�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�敨OP�����P�� = null�B");
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        if (l_ifoOrderUnitRow.getWLimitPrice() > 0D)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
