head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����]�̓T�[�r�XImpl(WEB3TPTradingPowerServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 nakazato(ACT) �V�K�쐬
                   2006/09/11 ���G��  (���u) ���f��No.006
                   2006/09/11 ���G��  (���u) ���f��No.007
                   2006/09/11 ���G��  (���u) ���f��No.009
                   2006/09/25 �Ԑi�@@  (���u) ���f��No.047
                   2006/09/25 �Ԑi�@@  (���u) ���f��No.048
                   2006/09/25 �Ԑi�@@  (���u) ���f��No.049
                   2006/11/13 �����  (���u) ���f��No.071-072
                   2006/11/27 �Ӑ�  (���u) ���f��No.089�ANo.090�ANo.091
                   2007/03/19 �{�{ �ē� (SCS) ���f��No.100
Revesion History : 2007/07/12 ���� (���u) ���f��No.107
Revesion History : 2007/07/19 �Ј��� (���u) ���f��No.109-111�A���f��No.118
Revesion History : 2007/08/06 ���� (���u) ���f��No.123
Revesion History : 2007/08/17 ���� (���u) ���f��No.166
Revesion History : 2007/09/20 �Ј��� (���u) ���f��No.169
Revesion History : 2007/09/29 �g�E�N�| (���u) ���f��No.190
Revesion History : 2007/10/10 �g�E�N�| (���u) ���f��No.206
Revesion History : 2007/10/12 ���� (���u) ���f��No.194
Revesion History : 2007/10/12 ���� (���u) ���f��No.209
Revesion History : 2007/10/12 ���� (���u) �v�Z����No.013
Revesion History : 2007/10/16 �И���i���u�j���f��No.210
Revesion History : 2007/10/15 �И��� (���u) ���f��No.211
Revesion History : 2007/10/18 �����Q�i���u�j���f��No.212
Revesion History : 2007/10/22 �И��� (���u) ���f��No.213 �����̖��No.004
                   2007/11/08 inomata (SCS) ���f��No.229
Revesion History : 2007/12/19 ���G�� (���u) ���f��No.243
Revesion History : 2008/01/18 �g�E�N�| (���u) ���f��No.247
Revesion History : 2008/03/14 �����Q (���u) ���f��No.261�ANo.262
Revesion History : 2008/09/10 ������ (���u) ���f��No.291-295
Revesion History : 2008/10/20 ���z (���u) ���f��No.319�A320�A323�A333�A334�A335�A343
Revesion History : 2008/10/31 �И��� (���u) ���f��No.355
Revesion History : 2008/11/21 �O���~��Y (SCS) ���f��No.372
Revesion History : 2008/12/02 ���L���E ���f��No.377
Revesion History : 2008/12/10 ���� (���u) ���f��No.378�A���f��No.380�A���f��No.381
Revesion History : 2008/12/16 ���� (���u) ���f��No.383�A���f��No.384
                   2008/12/24 �O���~��Y (SCS) ���f��No.385-387
Revesion History : 2009/01/07 ���� (���u) ���f��No.388
Revesion History : 2009/01/19 ���r (���u) ���f��No.389
Revesion History : 2009/12/11 �����F (���u) ���f��No.404 405 407 433 435 436 437 440 �v�Z����No.022 No.023
Revesion History : 2010/01/28 ���g�@@ (���u)���f��No.448,449,450,451,452,454
Revesion History : 2010/01/11 ���g�@@ (���u)���f��No.424,No.425,No.428,No.438,No.439
*/
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.TradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqNewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CashoutTodayDepositTransferDivDef;
import webbroker3.common.define.WEB3CashoutTradingpowerCheckDef;
import webbroker3.common.define.WEB3DbCurrentPriceCheckDivDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3SecondDepositMarginOpenTpStopDef;
import webbroker3.gentrade.WEB3GentradeBranchListmarketDealtCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.ifodeposit.WEB3IfoNewOrderSpec;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPCashoutTodayDepositTransferDivDef;
import webbroker3.tradingpower.define.WEB3TPDepositRealTransferEnforceDivDef;
import webbroker3.tradingpower.define.WEB3TPDoublepositionCheckDef;
import webbroker3.tradingpower.define.WEB3TPEquityBuyTradingPowerCheckTypeDef;
import webbroker3.tradingpower.define.WEB3TPExcludeExceptSettlementBuyAmountCheckDef;
import webbroker3.tradingpower.define.WEB3TPForcedSettleReasonDef;
import webbroker3.tradingpower.define.WEB3TPMarginSwapLongTradingPowerCheckTypeDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPMutualFundBuyApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPOrixSecuredLoanLockDef;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (����]�̓T�[�r�XImpl)<BR>
 * ����]�̓T�[�r�X�C���^�[�t�F�C�X�̎����N���X<BR>
 */
public class WEB3TPTradingPowerServiceImpl implements WEB3TPTradingPowerService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceImpl.class);

    /**
     * (�R���X�g���N�^)<BR>
     * @@roseuid 418F3DD101B4
     */
    public WEB3TPTradingPowerServiceImpl()
    {

    }

    /**
     * (validate����]��)<BR>
     * <BR>
     * ����.������ʂɂ����Ďw�肳�ꂽ�����̎���]�̓`�F�b�N�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]�́v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_orderSpecIntercepters - (�������e�C���^�Z�v�^)
     * @@param l_orderSpecs - (�������e)
     * @@param l_orderTypeEnum - (�������)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * true�̎��A�]�͍Čv�Z���������{����<BR>
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�<BR>
     * @@throws WEB3SystemLayerException
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 4158CBB901C5
     */
    public WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Object[] l_orderSpecIntercepters,
        Object[] l_orderSpecs,
        OrderTypeEnum l_orderTypeEnum,
        boolean l_blnUpdateFlg)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "validateTradingPower(WEB3GentradeSubAccount, Object[], Object[], OrderTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕�����A����.�������e�C���^�Z�v�^�A����.�������e�A����.������ʂ̉��ꂩ��null�̏ꍇ
        if (l_subAccount == null
            || l_orderSpecIntercepters == null
            || l_orderSpecs == null
            || l_orderTypeEnum == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //����]�͌���
            WEB3TPTradingPowerResult l_tradingPowerResult = null;

            /*
             * ����.�������e�C���Z�v�^�A�������e���A���������e�𐶐�
             */
            //����.�������e�̗v�f�����擾����B
            int l_intSize = l_orderSpecs.length;

            //���������e[]
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[l_intSize];
            //�敨OP���������e
            WEB3IfoNewOrderSpec l_ifoNewOrderSpec = null;

            //�⏕�����^�C�v���擾
            SubAccountTypeEnum l_subAccountType = l_subAccount.getSubAccountType();

            //����.�⏕����.�⏕�����^�C�v == �f�����I�v�V�����������(�敨�؋���)�̏ꍇ
            if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
            {
                //�敨OP���������e�𐶐�����
                l_ifoNewOrderSpec =
                    WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                        l_subAccount,
                        l_orderSpecIntercepters[0],
                        l_orderSpecs[0],
                        l_orderTypeEnum);
            }
            //�ȊO(����.�⏕����.�⏕�����^�C�v==1�F�����������(�a���)�܂��́A2�F�����M�p�������(�ۏ؋�)�̏ꍇ)
            else
            {
                //����.�������e�̗v�f����LOOP����
                for (int index = 0; index < l_intSize; index++)
                {
                    //���������e�𐶐�����B
                    WEB3TPNewOrderSpec l_spec =
                        WEB3TPNewOrderSpec.create(
                            l_subAccount,
                            l_orderSpecIntercepters[index],
                            l_orderSpecs[index]);

                    log.debug(l_spec.toString());
                    l_newOrderSpecs[index] = l_spec;
                }
            }

            //����.������ʁ��������t�����̏ꍇ
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum))
            {
                l_tradingPowerResult =
                    this.validateTradingPowerEquityBuy(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_blnUpdateFlg);
            }
            //����.������ʁ��������t�����̏ꍇ
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderTypeEnum))
            {
                //�V�K�����̏ꍇ
                if(l_orderSpecs[0] instanceof EqTypeNewCashBasedOrderSpec)
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerEquitySell(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_blnUpdateFlg);
                    
                }
                //�ȊO�i���������j�̏ꍇ
                else
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerReCalc(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_orderTypeEnum,
                            l_blnUpdateFlg);
                }
            }
            //����.������ʁ��M�p���������̏ꍇ
            else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderTypeEnum))
            {
                l_tradingPowerResult =
                    this.validateTradingPowerActualReceipt(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_blnUpdateFlg);
            }
            //����.������ʁ��M�p���n�����̏ꍇ
            else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderTypeEnum))
            {
                //�M�p���n��������̏ꍇ
                if (l_orderSpecs[0] instanceof EqTypeCancelOrderSpec)
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerActualDeliveryCancel(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_blnUpdateFlg);
                }
                //�ȊO�i�V�K�����j�̏ꍇ
                else
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerActualDelivery(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_blnUpdateFlg);
                }
            }
            //����.������ʁ��M�p�V�K�������̏ꍇ
            else if (
                OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum)
                    || OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum))
            {
                l_tradingPowerResult =
                    this.validateTradingPowerMargin(l_subAccount, l_newOrderSpecs, l_blnUpdateFlg);
            }
            //�I�v�V���������̏ꍇ
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderTypeEnum))
            {
                //�؋��������J�ݍς݂̏ꍇ
                if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerOptionBuy(l_subAccount, l_ifoNewOrderSpec);
                }
                //�ȊO(�؋��������J�ݖ���)�̏ꍇ
                else
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerOther(
                                l_subAccount,
                                l_newOrderSpecs,
                                l_orderTypeEnum,
                                l_blnUpdateFlg);
                }
            }
            //�敨����A�I�v�V���������̏ꍇ
            else if (
                l_orderTypeEnum.intValue() == OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()
                    || l_orderTypeEnum.intValue()
                        == OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue())
            {
                l_tradingPowerResult =
                    this.validateTradingPowerFuturesOption(l_subAccount, l_ifoNewOrderSpec);
            }
            //���M���t�A���M��W�A���M�抷�A�ݓ����t�A�����������A�~�j�����t�A�o���̏ꍇ
            else if (
                l_orderTypeEnum.intValue() == OrderTypeEnum.MF_BUY.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.MF_RECRUIT.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.MF_SWITCHING.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.RUITO_BUY.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.BOND_BUY.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.MINI_STOCK_BUY.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.CASH_OUT.intValue()
                    ) 
            {
                l_tradingPowerResult =
                    this.validateTradingPowerOther(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            //(�a����ˏ؋���)�U�֒����A(�a����ˈב֕ۏ؋�)�U�֒����A(�a����ˊO����������)�U�֒����A
            //(�a����˃I���b�N�X�N���W�b�g)�U�֒����A(�a�����CFD����)�U�֒����̏ꍇ
            else if (
                l_orderTypeEnum.intValue() == OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.intValue()
                	|| l_orderTypeEnum.intValue() == OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.intValue()
                	|| l_orderTypeEnum.intValue() == OrderTypeEnum.TRANSFER_TO_FEQ.intValue()
                	|| l_orderTypeEnum.intValue() == OrderTypeEnum.TO_ORIX_CREDIT.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.intValue())
            {
                /*
                 * ���������e[]�̂����A�⏕�����^�C�v��1�F������������i�a����j�̌��������e�̂ݗ]�͍X�V�Ɉ����n��
                 */
                WEB3TPNewOrderSpec[] l_specs = null;

                for (int index = 0; index < l_newOrderSpecs.length; index++)
                {
                    WEB3TPNewOrderSpec l_spec = l_newOrderSpecs[index];

                    //���������e.�⏕�����^�C�v��1�F������������i�a����j�̏ꍇ
                    if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_spec.getSubAccountType()))
                    {
                        l_specs = new WEB3TPNewOrderSpec[1];
                        l_specs[0] = l_spec;
                        break;
                    }
                }

                l_tradingPowerResult =
                    this.validateTradingPowerOther(
                        l_subAccount,
                        l_specs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            //�ݓ����@@��������̏ꍇ
            else if (
                l_orderTypeEnum.intValue() == OrderTypeEnum.RUITO_SELL.intValue()
                    && l_orderSpecs[0] instanceof CancelOrderSpec)
            {
                l_tradingPowerResult =
                    this.validateTradingPowerOther(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            //�،��U��(�ی�a��ˑ�p)�̏ꍇ
            else if (
                l_orderTypeEnum.intValue()
                    == OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.intValue())
            {
                l_tradingPowerResult =
                    this.validateTradingPowerReCalc(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            //�����t�A���ύX�̏ꍇ
            else if(l_orderTypeEnum.intValue() == OrderTypeEnum.BOND_SELL.intValue()
                    && l_orderSpecs[0] instanceof BondChangeOrderSpec)
            {
                l_tradingPowerResult =
                    this.validateTradingPowerOther(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            else if(l_orderTypeEnum.intValue() == OrderTypeEnum.FEQ_BUY.intValue())
            {
                //validate����]��<�O���������t>(�⏕����, ���������e[]
                //(�_���r���[::�]�́^�؋���(web3-tradingpower)::�]��::�]�͍X�V::�]�͍X�V�G���e�B�e�B::���������e), boolean)
                l_tradingPowerResult =
                    this.validateTradingPowerFeqBuy(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_blnUpdateFlg);
            }
            else if(l_orderTypeEnum.intValue() == OrderTypeEnum.FEQ_SELL.intValue())
            {
                if (l_orderSpecs[0] instanceof FeqNewOrderSpec)
                {
                    //validate����]��<�O���������t>(�⏕����, ���������e[]
                    //(�_���r���[::�]�́^�؋���(web3-tradingpower)::�]��::�]�͍X�V::�]�͍X�V�G���e�B�e�B::���������e), boolean)
                    l_tradingPowerResult =
                        this.validateTradingPowerFeqSell(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_blnUpdateFlg);
                }
                else
                {
                    //validate����]��<�]�͍Čv�Z>
                    l_tradingPowerResult =
                        this.validateTradingPowerReCalc(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_orderTypeEnum,
                            l_blnUpdateFlg);
                }
            }
            //�ȊO�̏ꍇ
            else
            {
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            log.debug(l_tradingPowerResult.toString());
            return l_tradingPowerResult;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�������t�\�z)<BR>
     * �����������t�\�z���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�������t�\�z�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return double
     */
    public double getEquityTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getEquityTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

                //���Y�]�͏��
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);
                //�]�͌v�Z���ʂ��擾
                WEB3TPCalcResult l_result = l_calcEquity.calcAppliedEquityTradingPower();
                log.debug(l_result.toString());

                //0�␳
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("����\�z = " + Double.toString(l_dblTradingPower));

                //�\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //���Y�]�͏��
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
                //�]�͌v�Z���ʂ��擾
                WEB3TPCalcResult l_result = l_calcMargin.calcAppliedEquityTradingPower();
                log.debug(l_result.toString());

                //0�␳
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("����\�z = " + Double.toString(l_dblTradingPower));

                //�\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�M�p�����\�z)<BR>
     * �M�p�����\�z���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�M�p�����\�z�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return double
     */
    public double getActualReceiptTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getActualReceiptTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
            //�]�͌v�Z���ʂ��擾
            WEB3TPCalcResult l_result = l_calcMargin.calcAppliedActualReceiptTradingPower();
            log.debug(l_result.toString());

            //0�␳
            double l_dblTradingPower = Math.max(0, l_result.tradingPower);
            log.debug("����\�z = " + Double.toString(l_dblTradingPower));

            //�\�z��ԋp
            log.exiting(STR_METHOD_NAME);
            return l_dblTradingPower;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�M�p�V�K���\�z)<BR>
     * �M�p�V�K���\�z���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�M�p�V�K���\�z�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return double
     * @@roseuid 415A0BAB03AA
     */
    public double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);


        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
            //�]�͌v�Z���ʂ��擾
            WEB3TPCalcResult l_result = l_calcMargin.calcAppliedMarginTradingPower();
            log.debug(l_result.toString());

            //0�␳
            double l_dblTradingPower = Math.max(0, l_result.tradingPower);
            log.debug("����\�z = " + Double.toString(l_dblTradingPower));

            //�\�z��ԋp
            log.exiting(STR_METHOD_NAME);
            return l_dblTradingPower;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�I�v�V�����V�K�����\�z)<BR>
     * �I�v�V�����̐V�K�����\�z��ԋp����B<BR>
     * <BR>
     * �E�؋��������J�ݍς̏ꍇ <BR>
     * �@@- �������w�肳��Ă��Ȃ��ꍇ�Anull��ԋp����B <BR>
     * �@@(�ʃT�[�r�X�ŐV�K���\�z��\�����邽��) <BR>
     * �@@- �������w�肳��Ă���ꍇ�A�؋����v�Z�̏؋����]�͊z��ԋp����B<BR>
     * <BR>
     * �E�؋����������J�݂̏ꍇ�A�]�͌v�Z�̃I�v�V�����V�K�����\�z��ԋp����B <BR>
     * <BR>
     * ���؋��������J�ݍς̏ꍇ <BR>
     * (����.�⏕����.�⏕�����^�C�v == �h�����I�v�V�����������(�敨�؋���)�h) <BR>
     * <BR>
     * �P�j�@@�������w�肳��Ă��Ȃ��ꍇ(����.���� == null) <BR>
     * <BR>
     * �@@null��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�������w�肳��Ă���ꍇ(����.���� != null) <BR>
     * �@@�Q�|�P�j�؋����v�Z�̎擾 <BR>
     * �@@�@@�؋����v�Z�T�[�r�X.get�؋����v�Z(�⏕����)���R�[������B <BR>
     * �@@�@@<BR>
     * �@@�@@[�����̐ݒ�]  <BR>
     * �@@�@@�@@�⏕�����F�@@����.�⏕����  <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�V�K���]�͉\�`�F�b�N<BR>
     *   �@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A0��ԋp����i�V�K���]�͕s��)<BR>
     *   �@@�E�؋����v�Z.get�؋����v�Z����().is�V�K���]�͉\() == false<BR>
     *   �@@�E�؋����v�Z.calc����؋����c��() < �؋����v�Z.get�؋����v�Z����().get�K�v�Œ�؋���()<BR>
     *   �@@�E�؋����v�Z.calc�������z() > 0<BR>
     * <BR>
     * �@@�Q�|�R�j�I�v�V�����V�K���\�z�̎擾  <BR>
     * �@@�؋����v�Z.calc�؋����]�͊z( )���R�[�����A���ʂ�ԋp����B  <BR>
     * <BR>
     * ���؋����������J�݂̏ꍇ<BR>
     * (����.�⏕����.�⏕�����^�C�v != �h�����I�v�V�����������(�敨�؋���)�h)<BR>
     * �@@�P�j�M�p�����J�݋敪���擾<BR>
     * �@@�@@�E����.�⏕����.getMainAccount()���R�[��<BR>
     * �@@�@@�E�ڋq.is�M�p�����J��()���R�[��<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�ٍϋ敪�F�h0�FDEFAULT�h<BR>
     * <BR>
     * �@@�Q�j�]�͌v�Z�����I�u�W�F�N�g�𐶐�<BR>
     * �@@�@@�E�]�͌v�Z����.create�]�͌v�Z����()���R�[��<BR>
     * �@@�@@�@@�m�����n<BR>
     * �@@�@@�@@�@@�⏕�����F����.�⏕����<BR>
     * <BR>
     * �@@�Ra)�����ڋq�̏ꍇ(*�ڋq.is�M�p�����J��()==false)<BR>
     * �@@�@@�Ra�|�P�j�]�͌v�Z����Params<�����ڋq>���擾<BR>
     * �@@�@@�@@�E���Y�]�͏��<�����ڋq>.find�]�͌v�Z����<�����ڋq>Params()���R�[��<BR>
     * �@@�@@�@@�@@�m�����n<BR>
     * �@@�@@�@@�@@�@@long�F����.�⏕����.getAccountId()<BR>
     * �@@�@@�Ra�|�Q)���Y�]�͏��<�����ڋq>�𐶐�<BR>
     * �@@�@@�@@�E�R���X�g���N�^���R�[��<BR>
     * �@@�@@�@@�@@�m�����n<BR>
     * �@@�@@�@@�@@�@@�]�͌v�Z����Params<�����ڋq>�F�擾�����]�͌v�Z����Params<�����ڋq><BR>
     * �@@�@@�@@�@@�@@�]�͌v�Z�����F���������]�͌v�Z�����I�u�W�F�N�g<BR>
     * <BR>
     * �@@�@@�Ra�|�R�j�I�v�V���������\�z���擾����B<BR>
     * �@@�@@�@@�E���Y�]�͏��<�����ڋq>.calc�I�v�V���������\�z()���R�[��<BR>
     * <BR>
     * �@@�@@�Ra�|�S�j����\�z��ԋp����@@<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@�@@�]�͌v�Z����.����\�z<BR>
     *<BR>
     * �@@�Rb)�M�p�ڋq�̏ꍇ(*�ڋq.is�M�p�����J��()==true)<BR>
     * �@@�@@�Rb�|�P�j�]�͌v�Z����Params<�M�p�ڋq>���擾<BR>
     * �@@�@@�@@�E���Y�]�͏��<�M�p�ڋq>.find�]�͌v�Z����<�M�p�ڋq>Params()���R�[��<BR>
     * �@@�@@�@@�@@�m�����n<BR>
     * �@@�@@�@@�@@�@@long�F����.�⏕����.getAccountId()<BR>
     * <BR>
     * �@@�@@�Rb�|�Q)���Y�]�͏��<�M�p�ڋq>�𐶐�<BR>
     * �@@�@@�@@�E�R���X�g���N�^���R�[��<BR>
     *�@@�@@�@@�@@ �m�����n<BR>
     * �@@�@@�@@�@@�@@�]�͌v�Z����Params<�M�p�ڋq>�F�擾�����]�͌v�Z����Params<�M�p�ڋq><BR>
     *�@@ �@@�@@�@@�@@�]�͌v�Z�����F���������]�͌v�Z�����I�u�W�F�N�g<BR>
     * <BR>
     * �@@�@@�Rb�|�R�j�I�v�V���������\�z���擾����B<BR>
     * �@@�@@�@@�E���Y�]�͏��<�M�p�ڋq>.calc�I�v�V���������\�z()���R�[��<BR>
     * <BR>
     * �@@�@@�Rb�|�S�j����\�z��ԋp����<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@�@@�]�͌v�Z����.����\�z<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return Double
     * @@roseuid 416F5BC901E9
     */
    public Double getOptionBuyTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        IfoProduct l_ifoProduct)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOptionBuyTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�⏕�����^�C�v���擾
            SubAccountTypeEnum l_subAccountType = l_subAccount.getSubAccountType();

            //����.�⏕����.�⏕�����^�C�v == �f�����I�v�V�����������(�敨�؋���)�̏ꍇ
            if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
            {
                //�������w�肳��Ă��Ȃ��ꍇ(����.���� == null)
                if(l_ifoProduct == null)
                {
                    //null��ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                //�؋����v�Z�T�[�r�X���擾
                WEB3IfoDepositCalcService l_service =
                    (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);
                //�؋����v�Z�I�u�W�F�N�g���擾
                WEB3IfoDepositCalc l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount);
                //�؋����v�Z�������擾
                WEB3IfoDepositCalcCondition l_ifoCalcCond = l_ifoDepCalc.getIfoDepositCalcCondition();
            
                //�V�K���]�͉\�t���O
                boolean l_newOpenFlg = l_ifoCalcCond.isNewOpenTradingPowerAvailable();
                //�K�v�Œ�ۏ؋�
                double l_dblMinIfoDep = l_ifoCalcCond.getMinIfoDeposit();
                //����؋����c��
                double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
                //�������z
                double l_dblNonPayAmt = l_ifoDepCalc.calcNonPayAmount();

                //�V�K���]�͕s�̏ꍇ
                if (l_newOpenFlg == false
                    || l_dblRecIfoDepBal < l_dblMinIfoDep
                    || l_dblNonPayAmt > 0)
                {
                    return new Double(0);
                }

                //�I�v�V�����V�K���\�z���擾
                double l_dblTradingPower = l_ifoDepCalc.calcIfoDepositTradingPowerAmount();

                //�I�v�V�����V�K���\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return new Double(l_dblTradingPower);
            }
            //�ȊO(����.�⏕����.�⏕�����^�C�v==1�F�����������(�a���)�܂��́A2�F�����M�p�������(�ۏ؋�)�̏ꍇ)
            else
            {
                //����ID���擾
                long l_lngAccountId = l_subAccount.getAccountId();

                //�ڋq�I�u�W�F�N�g���擾
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                //�M�p�����J�݋敪���擾
                boolean l_blnMargin =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

                //�]�͌v�Z����
                WEB3TPCalcCondition l_calcCond =
                    WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

                //�����ڋq�̏ꍇ
                if (l_blnMargin == false)
                {
                    //�]�͌v�Z����(List)���擾
                    List l_lisCalcResult =
                        WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

                    //���Y�]�͏��
                    WEB3TPTradingPowerCalcEquity l_calcEquity =
                        new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);
                    //�]�͌v�Z���ʂ��擾
                    WEB3TPCalcResult l_result = l_calcEquity.calcOptionTradingPower();
                    log.debug(l_result.toString());

                    //0�␳
                    double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                    log.debug("����\�z = " + Double.toString(l_dblTradingPower));

                    //�\�z��ԋp
                    log.exiting(STR_METHOD_NAME);
                    return new Double(l_dblTradingPower);
                }
                //�M�p�ڋq�̏ꍇ
                else
                {
                    //�]�͌v�Z����(List)���擾
                    List l_lisCalcResult =
                        WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                    //���Y�]�͏��
                    WEB3TPTradingPowerCalcMargin l_calcMargin =
                        new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
                    //�]�͌v�Z���ʂ��擾
                    WEB3TPCalcResult l_result = l_calcMargin.calcOptionTradingPower();
                    log.debug(l_result.toString());

                    //0�␳
                    double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                    log.debug("����\�z = " + Double.toString(l_dblTradingPower));

                    //�\�z��ԋp
                    log.exiting(STR_METHOD_NAME);
                    return new Double(l_dblTradingPower);
                }
            }
        }
        catch(WEB3SystemLayerException ex)
        {
            throw ex;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�敨�I�v�V�����V�K���\�z)
     * �敨�I�v�V�����̐V�K���\�z(����)��ԋp����B<BR>
     * ���I�v�V�����́A�����̂݁B�����̏ꍇ�́Athis.getOP�V�K�����\�z()���g�p�B<BR>
     * <BR>
     * �E�������w�肳��Ă��Ȃ��ꍇ�Anull��ԋp����B<BR>
     * �@@(�ʃT�[�r�X�ŐV�K���\�z��\�����邽��)<BR>
     * <BR>
     * �E�������w�肳��Ă���ꍇ�A<BR>
     * �@@�@@SPAN�̗p��ЂȂ�΁A�؋����v�Z�̏؋����]�͊z��ԋp����B<BR>
     * �@@�@@SPAN��̗p���(�܂���SPNA�g���u����)�Ȃ�΁A�؋����v�Z�̔�/���|�W�V�����\�ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�������w�肳��Ă��Ȃ��ꍇ(����.���� == null)<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �Q�j�������w�肳��Ă���ꍇ(����.���� != null)<BR>
     * �Q�|�P�j�؋����v�Z�̎擾<BR>
     * �@@�؋����v�Z�T�[�r�X.get�؋����v�Z(�⏕����)���R�[������B<BR>
     * �@@[�����̐ݒ�] <BR>
     * �@@�@@�⏕�����F�@@����.�⏕���� <BR>
     * <BR>
     * �Q�|�Q�j�@@�V�K���]�͉\�`�F�b�N<BR>
     *   �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A0��ԋp����i�V�K���]�͕s��)<BR>
     *   �E�؋����v�Z.get�؋����v�Z����().is�V�K���]�͉\() == false<BR>
     *   �E�؋����v�Z.calc����؋����c��() < �؋����v�Z.get�؋����v�Z����().get�K�v�Œ�؋���()<BR>
     *   �E�؋����v�Z.calc�������z() > 0<BR>
     * <BR>
     * �Q�|�R�j�V�K���\�z/���ʂ̎擾<BR>
     * �@@��SPAN�̗p��Ђ̏ꍇ<BR>
     * �@@(�؋����v�Z.get�؋����v�Z����( ).isSPAN�g�p�\( ) == true)<BR>
     * �@@�@@�؋����v�Z.calc�؋����]�͊z( )���R�[�����A���ʂ�ԋp����B<BR>
     * <BR>
     * �@@��SPAN��̗p��Ђ̏ꍇ<BR>
     * �@@(�؋����v�Z.get�؋����v�Z����( ).isSPAN�g�p�\( ) == false)<BR>
     * �@@�@@�E���|�W�V�����̏ꍇ<BR>
     * �@@�@@(����.����.�敨�I�v�V�������i == �h�敨�h && ����.is���� == true�A�܂��́A<BR>
     * �@@�@@ ����.����.�敨�I�v�V�������i == �h�v�b�g�I�v�V�����h)<BR>
     * <BR>
     * �@@�@@�@@�@@�؋����v�Z.calc���|�W�V�����\����(����.����.�����Y�����R�[�h)���R�[�����A���ʂ�ԋp����B<BR>
     * <BR>
     * �@@�E���|�W�V�����̏ꍇ<BR>
     * �@@�@@(����.����.�敨�I�v�V�������i == �h�敨�h && ����.is���� == false�A�܂��́A<BR>
     * �@@�@@ ����.����.�敨�I�v�V�������i == �h�R�[���I�v�V�����h)<BR>
     * <BR>
     * �@@�@@�@@�@@�؋����v�Z.calc���|�W�V�����\����(����.����.�����Y�����R�[�h)���R�[�����A���ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_blnLongFlg - (is����)
     * true�F�����@@false�F����
     * @@param l_ifoProduct - (����)
     * �����w�莞�̂ݐݒ�B�ȊO�Anull�B
     * @@return Double
     * @@roseuid 416F5BC90227
     */
    public Double getFuturesOptionTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        boolean l_blnLongFlg,
        IfoProduct l_ifoProduct)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getFuturesOptionTradingPower(WEB3GentradeSubAccount, boolean, IfoProduct)";
        log.entering(STR_METHOD_NAME);

        //�������w�肳��Ă��Ȃ��ꍇ
        if (l_ifoProduct == null)
        {
            //-1��ԋp����
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�������w�肳��Ă���ꍇ
        else
        {
            //�؋����v�Z�T�[�r�X���擾
            WEB3IfoDepositCalcService l_service =
                (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);
            //�؋����v�Z�I�u�W�F�N�g���擾
            WEB3IfoDepositCalc l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount);
            //�؋����v�Z�������擾
            WEB3IfoDepositCalcCondition l_ifoCalcCond = l_ifoDepCalc.getIfoDepositCalcCondition();
            
            //�V�K���]�͉\�t���O
            boolean l_newOpenFlg = l_ifoCalcCond.isNewOpenTradingPowerAvailable();
            //�K�v�Œ�ۏ؋�
            double l_dblMinIfoDep = l_ifoCalcCond.getMinIfoDeposit();
            //����؋����c��
            double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
            //�������z
            double l_dblNonPayAmt = l_ifoDepCalc.calcNonPayAmount();
            
            //�V�K���]�͕s�̏ꍇ
            if (l_newOpenFlg == false
                || l_dblRecIfoDepBal < l_dblMinIfoDep
                || l_dblNonPayAmt > 0)
            {
                return new Double(0);
            }

            //SPAN�̗p��Ђ̏ꍇ
            if (l_ifoDepCalc.getIfoDepositCalcCondition().isSPANUsable() == true)
            {
                //�؋����]�͊z���擾����B
                double l_dblTradingPower = l_ifoDepCalc.calcIfoDepositTradingPowerAmount();

                //�؋����]�͊z��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return new Double(l_dblTradingPower);
            }
            //SPAN��̗p��Ђ̏ꍇ
            else
            {
                //�敨OP���i�敪
                IfoDerivativeTypeEnum l_deriEnum = l_ifoProduct.getDerivativeType();
                //�����Y�����R�[�h
                String l_strProductCode = l_ifoProduct.getUnderlyingProductCode();

                //���|�W�V�����̏ꍇ
                if ((l_deriEnum.intValue() == IfoDerivativeTypeEnum.FUTURES.intValue()
                    && l_blnLongFlg == true)
                    || l_deriEnum.intValue() == IfoDerivativeTypeEnum.PUT_OPTIONS.intValue())
                {
                    //���|�W�V�����\���ʂ��擾
                    double l_dblPossQty = l_ifoDepCalc.calcPossibleBuyQty(l_strProductCode);

                    //���|�W�V�����\���ʂ�ԋp
                    log.exiting(STR_METHOD_NAME);
                    return new Double(l_dblPossQty);
                }
                //���|�W�V�����̏ꍇ
                else if (
                    (l_deriEnum.intValue() == IfoDerivativeTypeEnum.FUTURES.intValue()
                        && l_blnLongFlg == false)
                        || l_deriEnum.intValue() == IfoDerivativeTypeEnum.CALL_OPTIONS.intValue())
                {
                    //���|�W�V�����\���ʂ��擾
                    double l_dblPossQty = l_ifoDepCalc.calcPossibleSellQty(l_strProductCode);

                    //���|�W�V�����\���ʂ�ԋp
                    log.exiting(STR_METHOD_NAME);
                    return new Double(l_dblPossQty);
                }
                //�ȊO
                else
                {
                    //�G���[���X���[
                    log.error("illegal Argument");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
    }

    /**
     * (get���̑����i���t�\�z)<BR>
     * ���̑����i���t�\�z�i0�␳�L��j���擾����B<BR>
     * <BR>
     * �P�jget���̑����i���t�\�z�`0�␳�����`()���R�[�����A<BR>
     * ���̑����i���t�\�z�i0�␳�����j���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�⏕�����F����.�⏕����<BR>
     * �@@��n���F����.��n��<BR>
     * <BR>
     * �Q�j�P�j�̖߂�l���A0�␳���ĕԋp����B<BR>
     * <BR>
     * �@@�ԋp�l = Math.max(0, �P�j�̖߂�l)<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@roseuid 4158CBE10251
     */
    public double getOtherTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOtherTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //get���̑����i���t�\�z�`0�␳�����`()���R�[�����A���̑����i���t�\�z�i0�␳�����j���擾����B
        double l_dblTradingPower = this.getOtherTradingPowerForCheck(l_subAccount, l_datDeliveryDate);

        //�ԋp�l = Math.max(0, �P�j�̖߂�l)
        double l_dblPayment = Math.max(0, l_dblTradingPower);

        log.exiting(STR_METHOD_NAME);
        return l_dblPayment;
    }

    /**
     * (get�o���\�z�`0�␳�L��`)<BR>
     * �o���\�z���擾����B<BR>
     * <BR><
     * �P�j�o���\�z���擾����B<BR>
     * �@@����]�̓T�[�r�XImpl.get�o���\�z�`0�␳����()���R�[��<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�⏕�����F����.�⏕����<BR>
     * �@@�@@��n���F����.��n��<BR>
     *     �Ăяo�����敪�F1<BR>
     * <BR>
     * �Q�j�o���\�z��ԋp����B<BR>
     * �@@���P�j�̖߂�l < 0�̏ꍇ<BR>
     * �@@�@@�ԋp�l�F0<BR>
     * <BR>
     * �@@���ȊO�i �P�j�̖߂�l >= 0�j<BR>
     * �@@�@@�ԋp�l�F"�P�j�̖߂�l"<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    public double getPaymentTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPaymentTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //get�o���\�z�`0�␳�����`���R�[��
        double l_tradingPower = this.getPaymentTradingPowerForCheck(
                l_subAccount, l_datDeliveryDate, 1);
        //0�␳���s��
        l_tradingPower = Math.max(0, l_tradingPower);

        log.debug("����\�z = " + Double.toString(l_tradingPower));
        log.exiting(STR_METHOD_NAME);
        return l_tradingPower;
    }

    /**
     * (get�o���\�z�`0�␳�����`) �i���j�o���]�̓`�F�b�N���Ɏg�p<BR>
     * �o���\�z���擾����B<BR>
     * <BR>
     * �P�j�o���\�z���擾����B<BR>
     * �@@����]�̓T�[�r�XImpl.get�o���\�z�`0�␳����()���R�[��<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�⏕�����F����.�⏕����<BR>
     * �@@�@@��n���F����.��n��<BR>
     *     �Ăяo�����敪�F2<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    public double getPaymentTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        //get�o���\�z�`0�␳�����`���R�[��
        return this.getPaymentTradingPowerForCheck(
                l_subAccount, l_datDeliveryDate, 2);
    }

    /**
     * (get�����M�����t�\�z)<BR>
     * ���M���t�\�z���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�����M�����t�\�z�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_orderTypeEnum - (�������)
     * @@return double
     */
    public double getMutualFundBuyTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate, OrderTypeEnum l_orderTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMutualFundBuyTradingPower(WEB3GentradeSubAccount, Date, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕�����A����.��n����null�̏ꍇ
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //������ʁ�null�̏ꍇ
        if(l_orderTypeEnum == null)
        {
            //���M���t���f�t�H���g�Ƃ��ăZ�b�g����
            l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            /*
             * �]�͌v�Z�����I�u�W�F�N�g�𐶐�����
             */
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�����M�����t�\�z�K�p���̐ݒ�l���擾
            String l_strMFBuyApplyDate = l_calcCond.getInstBranCalcCondition(WEB3TPCalcCondition.MFBUY_APPLY_DATE);

            //���
            int l_intFundBasePoint = 0;
            
            //�K�p�����������ȍ~�̏ꍇ
            if(WEB3TPMutualFundBuyApplyDateDef.FROM_BIZ_DATE_UNTIL_T5.equals(l_strMFBuyApplyDate))
            {
                //������v�Z�����̊��<���M>
                l_intFundBasePoint = l_calcCond.getFundBasePoint();
            }
            else
            {
                //��n��������(T+0)�ȑO�������ꍇ
                if (WEB3DateUtility
                    .compareToDay(l_datDeliveryDate, l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0))
                    < 0)
                {
                    //�G���[���X���[
                    log.error("illegal Argument");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                //��n����T+5�ȍ~�������ꍇ
                else if (
                    WEB3DateUtility.compareToDay(
                            l_datDeliveryDate,
                        l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5))
                        > 0)
                {
                    //������c�Ɠ�(T+5)
                    l_intFundBasePoint = WEB3TPSpecifiedPointDef.T_5;
                }
                //�ȊO(��n�����AT+0�`T+5�̊�)�̏ꍇ
                else
                {
                    //����������̎�n��
                    l_intFundBasePoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
                }
            }

            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

                //���Y�]�͏��
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

                //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                WEB3TPCalcResult l_result = l_calcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intFundBasePoint);

                log.debug(l_result.toString());

                //0�␳
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("����\�z = " + Double.toString(l_dblTradingPower));

                //�\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //���Y�]�͏��
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

                //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                WEB3TPCalcResult l_result = l_calcMargin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intFundBasePoint);

                log.debug(l_result.toString());

                //0�␳
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("����\�z = " + Double.toString(l_dblTradingPower));

                //�\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�a����ւ̐U�֊z)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�a����ւ̐U�֊z�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_dblNecessaryCash - (�����K�v����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@roseuid 416E4E9002A6
     */
    public double getTransferAmountToEquitySubAcount(
        WEB3GentradeSubAccount l_subAccount,
        double l_dblNecessaryCash,
        Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getTransferAmountToEquitySubAcount(WEB3GentradeSubAccount, double, Date)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕�����A����.��n����null�̏ꍇ
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                //���Y�]�͏��<�����ڋq>���擾
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    this.getTradingPowerCalcEquity(l_subAccount);

                //�����ڋq����c�����擾
                double l_dblRealBalance =
                    this.calcRealBalanceEquity(l_subAccount, l_calcEquity, l_datDeliveryDate);

                //�a����ւ̐U��ւ��z���v�Z
                double l_dblTranAmt = l_dblNecessaryCash - l_dblRealBalance;
                //0�␳
                l_dblTranAmt = Math.max(0, l_dblTranAmt);
                log.debug("�a����ւ̐U�֊z = " + Double.toString(l_dblTranAmt));

                //�a����ւ̐U��ւ��z��ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_dblTranAmt;
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                //���Y�]�͏��<�M�p�ڋq>���擾
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    this.getTradingPowerCalcMargin(l_subAccount);

                //�����ڋq����c�����擾
                double l_dblRealBalance =
                    this.calcRealBalanceMargin(l_subAccount, l_calcMargin, l_datDeliveryDate);

                //�a����ւ̐U��ւ��z���v�Z
                double l_dblTranAmt = l_dblNecessaryCash - l_dblRealBalance;
                //0�␳
                l_dblTranAmt = Math.max(0, l_dblTranAmt);
                log.debug("�a����ւ̐U�֊z = " + Double.toString(l_dblTranAmt));

                //�a����ւ̐U��ւ��z��ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_dblTranAmt;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (�]�͍Čv�Z)<BR>
     * �]�͍Čv�Z�����{���A�����Ŏw�肳�ꂽ�ڋq�̗]�͏�Ԃ��ŐV�ɂ���B<BR>
     * <BR>
     * ���V�[�P���X�}�u(����]�̓T�[�r�X)�]�͍Čv�Z�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@roseuid 41774399003E
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "reCalcTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            //�]�͍X�V
            WEB3TPTradingPowerUpd l_tpUpd =
                new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                //�]�͍X�V���e���擾
                List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();
                //�]�͍X�V���e���e�[�u���ɑ}��
                l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                //�]�͍X�V���e���擾
                List l_updResults =
                    l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);
                //�]�͍X�V���e���e�[�u���ɑ}��
                l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���Y�]�͏��<�����ڋq>)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���Y�]�͏��<�����ڋq>�v�Q��
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcEquity(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�M�p�ڋq�̏ꍇ
        if (l_blnMargin == true)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //���Y�]�͏���ԋp
            log.exiting(STR_METHOD_NAME);
            return l_calcEquity;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get���Y�]�͏��<�M�p�ڋq>)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���Y�]�͏��<�M�p�ڋq>�v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcMargin(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //���Y�]�͏���ԋp
            log.exiting(STR_METHOD_NAME);
            return l_calcMargin;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get���Y�]�͏��<�����ڋq>�`�����]���`)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���Y�]�͏��<�����ڋq>�`�����]���`�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquityQuote(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcEquityQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�M�p�ڋq�̏ꍇ
        if (l_blnMargin == true)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionQuote(l_subAccount);

            //�]�͍X�V�I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);
        
            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult = l_tpUpd.calcTradingpowerUpdResultEquity();

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //���Y�]�͏���ԋp
            log.exiting(STR_METHOD_NAME);
            return l_calcEquity;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get���Y�]�͏��<�M�p�ڋq>�`�����]���`)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���Y�]�͏��<�M�p�ڋq>�`�����]���`�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMarginQuote(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcMarginQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionQuote(l_subAccount);

            //�]�͍X�V�I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);
        
            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult = l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //���Y�]�͏���ԋp
            log.exiting(STR_METHOD_NAME);
            return l_calcMargin;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get���Y�]�͏��<�����ڋq>)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���Y�]�͏��<�����ڋq>�`�]�͌v�Z���ʂh�c�w��`�v�Q��<BR>
     * <BR>
     * @@param l_lngCalcResultId - (�]�͌v�Z����ID)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(long l_lngCalcResultId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcEquity(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParamsSpecifiedCalcResultId(
                    l_lngCalcResultId);

            /*
             * List���̗]�͌v�Z����<�����ڋq>Params�I�u�W�F�N�g������ID���擾����B
             */
            //����ID
            long l_lngAccountId;
            //�]�͌v�Z����<�����ڋq>Params
            TpCalcResultEquityParams l_params = null;

            for (Iterator l_iter = l_lisCalcResult.iterator(); l_iter.hasNext();)
            {
                Object l_element = (Object)l_iter.next();

                if (l_element instanceof TpCalcResultEquityParams)
                {
                    l_params = (TpCalcResultEquityParams)l_element;
                }
            }
            l_lngAccountId = l_params.getAccountId();

            //�⏕�����I�u�W�F�N�g���擾
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount(l_lngAccountId);

            //�ڋq�I�u�W�F�N�g���擾
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            //�M�p�����J�݋敪���擾
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //�M�p�ڋq�̏ꍇ
            if (l_blnMargin == true)
            {
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //���Y�]�͏���ԋp
            log.exiting(STR_METHOD_NAME);
            return l_calcEquity;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get���Y�]�͏��<�M�p�ڋq>)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���Y�]�͏��<�M�p�ڋq>�`�]�͌v�Z���ʂh�c�w��`�v�Q��<BR>
     * <BR>
     * @@param l_lngCalcResultId - (�]�͌v�Z����ID)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(long l_lngCalcResultId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcMargin(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParamsSpecifiedCalcResultId(
                    l_lngCalcResultId);

            /*
             * List���̗]�͌v�Z����<�����ڋq>Params�I�u�W�F�N�g������ID���擾����B
             */
            //����ID
            long l_lngAccountId;
            //�]�͌v�Z����<�M�p�ڋq>Params
            TpCalcResultMarginParams l_params = null;

            for (Iterator l_iter = l_lisCalcResult.iterator(); l_iter.hasNext();)
            {
                Object l_element = (Object)l_iter.next();

                if (l_element instanceof TpCalcResultMarginParams)
                {
                    l_params = (TpCalcResultMarginParams)l_element;
                }
            }
            l_lngAccountId = l_params.getAccountId();

            //�⏕�����I�u�W�F�N�g���擾
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount(l_lngAccountId);

            //�ڋq�I�u�W�F�N�g���擾
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            //�M�p�����J�݋敪���擾
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //���Y�]�͏���ԋp
            log.exiting(STR_METHOD_NAME);
            return l_calcMargin;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get���Y�]�͏��<�����ڋq>)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���Y�]�͏��<�����ڋq>�`�v�Z���ʎ��O�擾�ρ`�v�Q��
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_resultRow - (�]�͌v�Z����<�����ڋq>)
     * @@param l_resultDetailRow - (�]�͌v�Z���ʏڍ�<�����ڋq>)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultEquityRow l_resultRow,
        TpCalcResultEquityDetailRow l_resultDetailRow)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcEquity(WEB3GentradeSubAccount, TpCalcResultEquityRow, TpCalcResultEquityDetailRow)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�M�p�ڋq�̏ꍇ
        if (l_blnMargin == true)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͌v�Z����(List)���쐬
            List l_lisCalcResult = new ArrayList();
            l_lisCalcResult.add(l_resultRow);
            l_lisCalcResult.add(l_resultDetailRow);

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //���Y�]�͏���ԋp
            log.exiting(STR_METHOD_NAME);
            return l_calcEquity;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get���Y�]�͏��<�M�p�ڋq>)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���Y�]�͏��<�M�p�ڋq>�`�v�Z���ʎ��O�擾�ρ`�v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_resultRow - (�]�͌v�Z����<�M�p�ڋq>)
     * @@param l_resultDetailRow - (�]�͌v�Z���ʏڍ�<�M�p�ڋq>)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultMarginRow l_resultRow,
        TpCalcResultMarginDetailRow l_resultDetailRow)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcMargin(WEB3GentradeSubAccount, TpCalcResultMarginRow, TpCalcResultMarginDetailRow)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͌v�Z����(List)���쐬
            List l_lisCalcResult = new ArrayList();
            l_lisCalcResult.add(l_resultRow);
            l_lisCalcResult.add(l_resultDetailRow);

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //���Y�]�͏���ԋp
            log.exiting(STR_METHOD_NAME);
            return l_calcMargin;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get��K�������ꗗ)<BR>
     * <BR>
     * ���V�[�P���X�}�u(����]�̓T�[�r�X)get��K���������ꗗ�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPMarginSecurityInfo[]
     */
    public WEB3TPMarginSecurityInfo[] getMarginSecurityInfo(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarginSecurityInfo(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͍X�V�I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerUpd l_calcUpd =
                new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

            //�]�͍X�V���e(List)���擾
            List l_lisCalcResult =
                l_calcUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //���Y�]�͏��<�M�p>�I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //��K���������ꗗ���擾
            WEB3TPMarginSecurityInfo[] l_marginSecInfo =
                this.calcMarginSecurity(l_subAccount, -1, l_calcMargin, l_calcUpd, null, -1, null);

            log.exiting(STR_METHOD_NAME);
            return l_marginSecInfo;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get���̑����i���t�\�z�`�]�͌v�Z����ROW�w��`)
     * <BR>
     * �]�͌v�Z����Row�Ȃ�тɗ]�͌v�Z���ʏڍ�Row���w�肵��<BR>
     * ���̑����i���t�\�z���擾����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_tpCalcResultEquityRow - (�]�͌v�Z����<�����ڋq>Row)
     * @@param l_tpCalcResultEquityDetailRow - (�]�͌v�Z���ʏڍ�<�����ڋq>Row)
     * @@param l_tpCalcResultMarginRow - (�]�͌v�Z����<�M�p�ڋq>Row)
     * @@param l_tpCalcResultMarginDetailRow - (�]�͌v�Z���ʏڍ�<�M�p�ڋq>Row)l_subAccount - (�⏕����)
     * @@return double
     */
    public double getOtherTradingPower(
            WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate,
            TpCalcResultEquityRow l_tpCalcResultEquityRow,
            TpCalcResultEquityDetailRow l_tpCalcResultEquityDetailRow,
            TpCalcResultMarginRow l_tpCalcResultMarginRow,
            TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow)
            throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOtherTradingPower(WEB3GentradeSubAccount, Date, TpCalcResultEquityRow, TpCalcResultEquityDetailRow, TpCalcResultMarginRow, TpCalcResultMarginDetailRow)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕�����A����.��n����null�̏ꍇ
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            /*
             * �]�͌v�Z�����I�u�W�F�N�g�𐶐�����
             */
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //��n��������(T+0)�ȑO�������ꍇ
            if (WEB3DateUtility
                .compareToDay(l_datDeliveryDate, l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0))
                < 0)
            {
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //��n����T+5�ȍ~�������ꍇ
            else if (
                WEB3DateUtility.compareToDay(
                    l_datDeliveryDate,
                    l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5))
                    > 0)
            {
                //�]�͌v�Z���<���̑����t>=T+5���Z�b�g
                l_calcCond.setOtherBasePoint(WEB3TPSpecifiedPointDef.T_5);
            }
            //�ȊO(��n�����AT+0�`T+5�̊�)�̏ꍇ
            else
            {
                //��n���ɑΉ�����w������擾
                int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

                //�]�͌v�Z���<���̑����t>=�w������Z�b�g
                l_calcCond.setOtherBasePoint(l_intDeliDate);
            }

            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                List l_lisCalcResult = null;

                if(l_tpCalcResultEquityRow != null)
                {
                    //�]�͌v�Z����(List)���擾
                    l_lisCalcResult = new ArrayList();
                    l_lisCalcResult.add(l_tpCalcResultEquityRow);
                    l_lisCalcResult.add(l_tpCalcResultEquityDetailRow);
                }
                else
                {
                    //�]�͌v�Z����(List)���擾
                    l_lisCalcResult = WEB3TPTradingPowerCalcEquity
                        .findCalcResultEquityParams(l_lngAccountId);
                }

                //���Y�]�͏��
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

                //�]�͌v�Z���ʂ��擾
                WEB3TPCalcResult l_result = l_calcEquity.calcAppliedOtherTradingPower(null);
                log.debug(l_result.toString());

                //0�␳
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("����\�z = " + Double.toString(l_dblTradingPower));

                //�\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                List l_lisCalcResult = null;

                if(l_tpCalcResultMarginRow != null)
                {
                    //�]�͌v�Z����(List)���擾
                    l_lisCalcResult = new ArrayList();
                    l_lisCalcResult.add(l_tpCalcResultMarginRow);
                    l_lisCalcResult.add(l_tpCalcResultMarginDetailRow);
                }
                else
                {
                    //�]�͌v�Z����(List)���擾
                    l_lisCalcResult = WEB3TPTradingPowerCalcMargin
                        .findCalcResultMarginParams(l_lngAccountId);
                }

                //���Y�]�͏��
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

                //�]�͌v�Z���ʂ��擾
                WEB3TPCalcResult l_result = l_calcMargin.calcAppliedOtherTradingPower(null);
                log.debug(l_result.toString());

                //0�␳
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("����\�z = " + Double.toString(l_dblTradingPower));

                //�\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�������t�\�z�`�A�������`) <BR>
     * <BR>
     * �A�������p�̊������t�\�z���v�Z���ԋp����B <BR>
     * <BR>
     * ���V�[�P���X�}�u(����]�̓T�[�r�X)get�������t�\�z�`�A�������`�v�Q�� <BR>
     * <BR>
     * �u�ԋp�l >= �T�Z��n����v�ˎ��OK <BR>
     * �u�ԋp�l <  �T�Z��n����v�ˎ��NG <BR>
     * <BR>
     * ���j�ԋp�l >= 0 �Ƃ���B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_estimatedPrice - (�����O�T�Z��n���)
     * @@return double
     */
    public double getSuccEquityTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate,
            Double l_estimatedPrice) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSuccEquityTradingPower(WEB3GentradeSubAccount, Date, Double)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕�������Anull�̏ꍇ
        if(l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            /*
             * ������擾
             */
            int l_intBasePoint;
            //����.��n�� == null�̏ꍇ
            if(l_datDeliveryDate == null)
            {
                //�]�͌v�Z���<�������t/�M�p����>���擾
                l_intBasePoint = l_calcCond.getEquityBasePoint();
            }
            //�ȊO�̏ꍇ
            else 
            {
                //����.��n���ɑΉ�����w������擾
                l_intBasePoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
            }
            
            //�c�Ɠ�(T+0)
            Date l_datT0 = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);

            //���������\�񒍕��P�ʈꗗ
            List l_lisTodaysRsvEqOrderUnits = this.getTodaysRsvEqOrderUnits(l_lngAccountId, l_datT0);

            //�������t�\�z�`�A�������`
            double l_result;

            //�����ڋq�̏ꍇ
            if(l_blnMargin == false)
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult = WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

                //���Y�]�͏��
                WEB3TPTradingPowerCalcEquity l_calcEquity = new WEB3TPTradingPowerCalcEquity(
                        l_lisCalcResult,
                        l_calcCond);

                //���������\�񒍕��P�ʈꗗ���Z�b�g
                l_calcEquity.setTodaysRsvEqOrderUnits(l_lisTodaysRsvEqOrderUnits);

                //�������t�\�z�`�A�������`���擾
                l_result = l_calcEquity.calcSuccEquityTradingPower(l_intBasePoint);
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult = WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //���Y�]�͏��
                WEB3TPTradingPowerCalcMargin l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                        l_lisCalcResult,
                        l_calcCond);

                //���������\�񒍕��P�ʈꗗ���Z�b�g
                l_calcMargin.setTodaysRsvEqOrderUnits(l_lisTodaysRsvEqOrderUnits);

                //�������t�\�z�`�A�������`���擾
                l_result = l_calcMargin.calcSuccEquityTradingPower(l_intBasePoint);
            }

            //���������̏ꍇ
            if(l_estimatedPrice != null)
            {
                //�������t�\�z�`�A�������` = �������t�\�z�`�A�������` - �����O�T�Z��n���
                l_result = l_result + l_estimatedPrice.doubleValue();
            }

            //0�␳
            l_result = Math.max(0, l_result);
            log.debug("����\�z = " + Double.toString(l_result));

            //�\�z��ԋp
            log.exiting(STR_METHOD_NAME);
            return l_result;

        }
        catch(WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                    baseRunEx.getErrorInfo(),
                    baseRunEx.getErrorMethod(),
                    baseRunEx.getErrorMessage(),
                    baseRunEx.getException());
        }
        catch(Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
    }

    /**
     * (get�M�p�V�K���\�z�`�A�������`) <BR>
     * <BR>
     * �A�������p�̐M�p�V�K���\�z���v�Z���ԋp����B <BR>
     * <BR>
     * ���V�[�P���X�}�u(����]�̓T�[�r�X)get�M�p�V�K���\�z�`�A�������`�v�Q�� <BR>
     * <BR>
     * �u�ԋp�l >= �T�Z��n����v�ˎ��OK 
     * �u�ԋp�l <  �T�Z��n����v�ˎ��NG <BR><BR>
     * <BR>
     * ���j�ԋp�l >= 0 �Ƃ���B <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_estimatedPrice - (�����O�T�Z��n���)
     * @@return double
     */
    public double getSuccMarginTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Double l_estimatedPrice) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSuccMarginTradingPower(WEB3GentradeSubAccount, Double)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if(l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�c�Ɠ�(T+0)
            Date l_datT0 = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);

            //���������\�񒍕��P�ʈꗗ
            List l_lisTodaysRsvEqOrderUnits = this.getTodaysRsvEqOrderUnits(l_lngAccountId, l_datT0);

            //�M�p�V�K���\�z�`�A�������`
            double l_result;

            //�����ڋq�̏ꍇ
            if(l_blnMargin == false)
            {
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult = WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //���Y�]�͏��
                WEB3TPTradingPowerCalcMargin l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                        l_lisCalcResult,
                        l_calcCond);

                //���������\�񒍕��P�ʈꗗ���Z�b�g
                l_calcMargin.setTodaysRsvEqOrderUnits(l_lisTodaysRsvEqOrderUnits);

                //�M�p�V�K���\�z�`�A�������`���擾
                l_result = l_calcMargin.calcSuccMarginTradingPower();
            }

            //���������̏ꍇ
            if(l_estimatedPrice != null)
            {
                //�M�p�V�K���\�z�`�A�������` = �M�p�V�K���\�z�`�A�������` - �����O�T�Z��n���
                l_result = l_result + l_estimatedPrice.doubleValue();
            }

            //0�␳
            l_result = Math.max(0, l_result);
            log.debug("����\�z = " + Double.toString(l_result));

            //�\�z��ԋp
            log.exiting(STR_METHOD_NAME);
            return l_result;

        }
        catch(WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                    baseRunEx.getErrorInfo(),
                    baseRunEx.getErrorMethod(),
                    baseRunEx.getErrorMessage(),
                    baseRunEx.getException());
        }
        catch(Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
    }

    /**
     * (get�M�p�����\�z�`�A�������`) <BR>
     * <BR>
     * �A�������p�̐M�p�����\�z���v�Z���ԋp����B <BR>
     * <BR>
     * ���V�[�P���X�}�u(����]�̓T�[�r�X)get�M�p�����\�z�`�A�������`�v�Q�� <BR>
     * <BR>
     * �u�ԋp�l >= �T�Z��n����v�ˎ��OK <BR>
     * �u�ԋp�l <  �T�Z��n����v�ˎ��NG <BR>
     * <BR>
     * ���j�ԋp�l >= 0 �Ƃ���B <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    public double getSuccActualReceiptTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSuccActualReceiptTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null �̏ꍇ
        if(l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            /*
             * ������擾
             */
            int l_intBasePoint;
            //����.��n�� == null�̏ꍇ
            if(l_datDeliveryDate == null)
            {
                //�]�͌v�Z���<�������t/�M�p����>���擾
                l_intBasePoint = l_calcCond.getEquityBasePoint();
            }
            //�ȊO�̏ꍇ
            else 
            {
                //����.��n���ɑΉ�����w������擾
                l_intBasePoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
            }

            //�c�Ɠ�(T+0)
            Date l_datT0 = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);

            //���������\�񒍕��P�ʈꗗ
            List l_lisTodaysRsvEqOrderUnits = this.getTodaysRsvEqOrderUnits(l_lngAccountId, l_datT0);

            //�M�p�����\�z�`�A�������`
            double l_result;

            //�����ڋq�̏ꍇ
            if(l_blnMargin == false)
            {
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult = WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //���Y�]�͏��
                WEB3TPTradingPowerCalcMargin l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                        l_lisCalcResult,
                        l_calcCond);

                //���������\�񒍕��P�ʈꗗ���Z�b�g
                l_calcMargin.setTodaysRsvEqOrderUnits(l_lisTodaysRsvEqOrderUnits);

                //�M�p�����\�z�`�A�������`���擾
                l_result = l_calcMargin.calcSuccActualReceiptTradingPower(l_intBasePoint);
            }

            //0�␳
            l_result = Math.max(0, l_result);
            log.debug("����\�z = " + Double.toString(l_result));

            //�\�z��ԋp
            log.exiting(STR_METHOD_NAME);
            return l_result;

        }
        catch(WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                    baseRunEx.getErrorInfo(),
                    baseRunEx.getErrorMethod(),
                    baseRunEx.getErrorMessage(),
                    baseRunEx.getException());
        }
        catch(Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
    }

    /**
     * (validate����]��<�����������t>) <BR>
     * <BR>
     * �����������t�ɂ����āA���񒍕�����������������]�̓`�F�b�N�����{����B <BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�����������t>�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)
     * true�̎��A�]�͍Čv�Z���������{����
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerEquityBuy(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerEquityBuy(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z����
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        /*
         * �����~�敪�`�F�b�N
         */
        //�����~���̏ꍇ
        if (l_calcCond.isTradingStop() == true)
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //����t���O���Z�b�g
            l_tpResult.setResultFlg(false);
            //����\�z���Z�b�g
            l_tpResult.setTradingPower(0);

            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //���̑����i���t�]�͒�~���̏ꍇ
        if (l_calcCond.isOtherTradingStop() == true)
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //����t���O���Z�b�g
            l_tpResult.setResultFlg(false);
            //����\�z���Z�b�g
            l_tpResult.setTradingPower(0);

            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv =
                WEB3TPTradingPowerErrorDivDef.OTHER_TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //�]�͍X�V�I�u�W�F�N�g�𐶐�
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);

        /*
         * �������������t���O���擾
         */
        //����ID
        long l_lngProductId = l_newOrderSpecs[0].getProductId();
        //�s��ID
        long l_lngMarketId = l_newOrderSpecs[0].getMarketId();
        //�������������t���O
        boolean l_isTodayDepFundReg = this.isTodayDepFundReg(l_lngProductId, l_lngMarketId);
        
        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�]�͌v�Z����(List)�`����蔄�t�����l���`���擾
            List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquityIncUnexecSellOrder();

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_updResults, l_calcCond);

            /*
             * �a����s���z�A�����s���z���v�Z����B
             * 
             * [�a��،��]�����ڋq�̏ꍇ]
             * �@@�a����s���z = MIN(���o�\����(T+0..������), �������t�\�z<���v��S�����l��>(T+��n��..5))
             * �@@�����s���z = MIN(���o�\����(T+0..������), ���o�\����(T+��n��..5))
             * 
             * �@@���������������������Ώۖ����̏ꍇ�A�ȉ��̌v�Z����B
             * �@@�@@ �a����s���z = MIN(�a����s���z, �g�p�\����(T+0..��n��))�@@�@@
             * 
             * [�O�����ڋq�̏ꍇ] ����a��،��]�����ڋq
             * �@@�a����s���z = MIN( �������t�\�z<���v��S�����l>(T+0..5) ))
             *   �����s���z = 0
             */
            //�a����s���z
            double l_dblLackAmt = 0;
            //�����s���z
            double l_dblLackCashAmt = 0;

            //[�a��،��]�����ڋq�̏ꍇ]
            if(l_calcCond.isAssetEvalDiv() == true)
            {
                //������(�Fint�^)
                int l_intBizDate = l_calcCond
                    .calcSpecifiedPoint(l_newOrderSpecs[0].getOrderBizDate());
                //��n��(�Fint�^)
                int l_intDeliDate = l_calcCond
                    .calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());
                
                /*
                 * �a����s���z�@@=�@@MIN(���o�\����(T+0..������), �������t�\�z<���v��S�����l��>(T+��n��..5))
                 * �����s���z�@@=�@@MIN(���o�\����(T+0..������), ���o�\����(T+��n��..5))
                 */

                //LOOP����(��n���`T+5�̊�)
                for (int index = l_intDeliDate; index <= WEB3TPSpecifiedPointDef.T_5; index++)
                {
                    l_dblLackAmt =
                        Math.min(l_calcEquity.calcEquityTradingPowerIncDayTrade(index), l_dblLackAmt);
                    l_dblLackCashAmt =
                        Math.min(l_calcEquity.calcActualPaymentBalance(index), l_dblLackCashAmt);
                }

                //LOOP����(T+0�`�������̊�)
                for (int index = WEB3TPSpecifiedPointDef.T_0; index <= l_intBizDate; index++)
                {
                    l_dblLackAmt =
                        Math.min(l_calcEquity.calcActualPaymentBalance(index), l_dblLackAmt);
                    l_dblLackCashAmt =
                        Math.min(l_calcEquity.calcActualPaymentBalance(index), l_dblLackCashAmt);
                }

                //���������Ώۖ����t���O==true�̏ꍇ
                if(l_isTodayDepFundReg == true)
                {
                    /*
                     * �a����s���z = MIN(�a����s���z, �g�p�\����(T+������..��n��-1))
                     */

                    //LOOP����(T+�������`��n��-1�̊�)
                    for (int index = l_intBizDate; index < l_intDeliDate; index++)
                    {
                        l_dblLackAmt =
                            Math.min(l_calcEquity.calcActualAccountBalance(index), l_dblLackAmt);
                    }
                }
            }
            //[�O�����ڋq�̏ꍇ] ����a��،��]�����ڋq
            else
            {
                //�a����s���z = MIN( �������t�\�z<���v��S�����l>(T+0..5) ))
                for (int index = 0; index <= WEB3TPSpecifiedPointDef.T_5; index++)
                {
                    l_dblLackAmt =
                        Math.min(l_calcEquity.calcEquityTradingPowerIncDayTrade(index), l_dblLackAmt);
                }
            }
            
            //���X�^�C�v���擾����B
            BranchTypeEnum l_branchType = l_calcCond.getBranchType();
            
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * ����]�̓`�F�b�NOK�i�a����s���G���[�`�F�b�NOK �܂��́A�c�ƕ��X�j�̏ꍇ
             * (0 ���� �a����s���z || get���X�^�C�v()�̖߂�l == 4:��ʍ������X�j
             */
            if(l_dblLackAmt >= 0
                    || BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true)
            {
                //�]�͌v�Z����(List)���擾
                l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();

                //���Y�]�͏��I�u�W�F�N�g�𐶐�
                l_calcEquity = new WEB3TPTradingPowerCalcEquity(
                        l_updResults, l_calcCond);

                //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                WEB3TPCalcResult l_calcResult = l_calcEquity.calcAppliedEquityTradingPower();

                //true���Z�b�g
                l_tpResult.setResultFlg(true);
                //(���񒍕���)����\�z���Z�b�g
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(null);
                
                //�a��،��]�����ڋq�������s���̏ꍇ
                if(l_calcCond.isAssetEvalDiv() == true && l_dblLackCashAmt < 0)
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult
                        .setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION);

                    //set�a����s���z(double)
                    //�a����s���z���Z�b�g����B
                    //[����]
                    //double = ABS(�����s���z)
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackCashAmt));
                }
                /*
                 * �c�ƕ��X�@@���@@�a����s���̏ꍇ
                 * �iget���X�^�C�v()�̖߂�l == 4:��ʍ������X && �a����s���z  < 0�j
                 */
                else if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblLackAmt < 0)
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //�a����s���z���Z�b�g
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackAmt));
                }
                //�ȊO�̏ꍇ
                else
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(null);
                }
                
                //�]�͍X�V�t���O==true�̏ꍇ
                if (l_blnUpdateFlg == true)
                {
                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
                }
            }
            /*
             * �ȊO�i����]�̓`�F�b�NNG�j�̏ꍇ
             */
            else
            {
                //false���Z�b�g
                l_tpResult.setResultFlg(false);
                //(���񒍕���)����\�z(=0)���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                l_tpErrorInfo.buyOrderPossibleAmount =
                    this.getBuyOrderPossibleAmount(l_subAccount, l_newOrderSpecs[0]);
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
        //�M�p�ڋq�̏ꍇ
        else
        {
            /*
             * �Ǐؗ]�̓`�F�b�N
             */
            //�����O�]�͌v�Z���ʂ��擾
            List l_curUpdResults = WEB3TPTradingPowerCalcMargin
                .findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcMargin l_curCalcMargin = new WEB3TPTradingPowerCalcMargin(
                    l_curUpdResults, l_calcCond);
            
            //������
            Date l_datBizDate = l_newOrderSpecs[0].getOrderBizDate();
            int l_intBizDate = l_calcCond.calcSpecifiedPoint(l_datBizDate);
            
            //��n��
            Date l_datDeliDate = l_newOrderSpecs[0].getDeliveryDate();
            int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliDate);

            //�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>���擾
            WEB3TPCalcResult l_marginCallPower = l_curCalcMargin
                .calcMarginCallPowerForCheck(
                        OrderTypeEnum.EQUITY_BUY, l_intBizDate, l_intDeliDate);
            
            //�Ǐ؃`�F�b�NNG�̏ꍇ(�]�͌v�Z����.����\�z < 0)
            if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
            {
                //����]�͌��ʃI�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = l_marginCallPower.tradingPower;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }
            
            /*
             * �g����]�̓`�F�b�N���@@-�����������t�������h���擾����B
             */
            //����]�̓`�F�b�N���@@-�����������t������
            String l_strInstBranCalcCond = l_calcCond.getInstBranCalcCondition(WEB3TPCalcCondition.EQUITYBUY_TRADINGPOWER_CHECK);
            
            /*
             * �]�͌v�Z����(List)�`����蔄�t�����l���`���擾
             */
            //�]�͌v�Z����(List)�`����蔄�t�����l���`
            List l_updResults = null;
            
            //�g����]�̓`�F�b�N���@@-�����������t�������h== 1:BRANCH_SUBRATE �̏ꍇ
            if(WEB3TPEquityBuyTradingPowerCheckTypeDef.BRANCH_SUBRATE.equals(l_strInstBranCalcCond) == true)
            {
                //���t��]�͍X�V�I�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerUpdAfterBuy l_tpUpdAfterBuy =
                    new WEB3TPTradingPowerUpdAfterBuy(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);
                
                //�]�͌v�Z����(List)�`����蔄�t�����l���`���擾
                l_updResults =
                    l_tpUpdAfterBuy.calcTradingpowerUpdResultMarginIncUnexecSellOrder();
            }
            //�ȊO(�g����]�̓`�F�b�N���@@-�����������t�������h== 0:DEFAULT or null)�̏ꍇ
            else
            {
                //�]�͌v�Z����(List)�`����蔄�t�����l���`���擾
                l_updResults =
                    l_tpUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();
            }

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            /*
             * �a����s���z���v�Z����
             * 
             *  �@@�a����s���z = MIN( �a��������]��(T+0..T+��n��-1), 
             *                       ���o�\����(T+��n��..5), 
             *                       �ۏ؋����o�]��(T+��n��..5) )
             */
            double l_dblLackAmt = 0;

            //T+0..T+��n��-1
            for(int index = 0; index < l_intDeliDate; index++)
            {
                //�a��������]��(T+0..T+��n��-1)
	            l_dblLackAmt = Math.min(l_calcMargin
	                .calcAccountBalanceDemandPower(index), l_dblLackAmt);
            }
            //T+��n��..T+5
            for(int index = l_intDeliDate; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //���o�\����(T+��n��..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcActualPaymentBalance(index), l_dblLackAmt);

                //�ۏ؋����o�]��(T+��n��..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcMarginDrawPower(index), l_dblLackAmt);
            }

            //���������Ώۖ����t���O==true�̏ꍇ
            if(l_isTodayDepFundReg == true)
            {
                /*
                 * �a����s���z = MIN(�a����s���z, 
                 *                   ���o�\����(T+������..��n��-1),
                 *                   �ۏ؋����o�]��(T+������..��n��-1))
                 */
                //LOOP����(T+�������`��n��-1�̊�)
                for (int index = l_intBizDate; index < l_intDeliDate; index++)
                {
                    //���o�\����(T+������..��n��-1)
                    l_dblLackAmt =
                        Math.min(l_calcMargin.calcActualPaymentBalance(index), l_dblLackAmt);

                    //�ۏ؋����o�]��(T+������..��n��-1)
                    l_dblLackAmt =
                        Math.min(l_calcMargin.calcMarginDrawPower(index), l_dblLackAmt);
                }
            }
            
            /*
             * ���������̓�K���`�F�b�N�����{
             */
            //�]�͌v�Z����(List)�`����蔄�t�����l���`���擾
            l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                    l_updResults, l_calcCond);

            //��K���`�F�b�N�G���[�������̔z����擾
            WEB3TPMarginSecurityInfo[] l_marginSecs = this.calcMarginSecurity(
                    l_subAccount, l_lngProductId, l_calcMargin, l_tpUpd,
                    OrderTypeEnum.EQUITY_BUY, l_lngMarketId, l_datBizDate);
            
            //���X�^�C�v���擾����B
            BranchTypeEnum l_branchType = l_calcCond.getBranchType();
            
            /*
             * ����]�͌��ʃI�u�W�F�N�g�𐶐�
             */
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * ����]�̓`�F�b�NOK�i��K���`�F�b�NOK�@@���@@�i�a����s���G���[�`�F�b�NOK�܂��́A�c�ƕ��X�j�j�̏ꍇ
             * (��K���������ꗗ[] == null && (0 ���� �a����s���z�@@�܂��� get���X�^�C�v()�̖߂�l == 4:��ʍ������X)
             * 
             * ����K���������ꗗ[] = �]�͌v�Z.calc��K��()�̖߂�l
             */
            if(l_marginSecs == null
                    && (l_dblLackAmt >= 0 || BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true))
            {
                //�]�͌v�Z����(List)���擾
                l_updResults = l_tpUpd
                    .calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

                //���Y�]�͏��I�u�W�F�N�g�𐶐�
                l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                        l_updResults, l_calcCond);

                //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                WEB3TPCalcResult l_calcResult = l_calcMargin.calcAppliedEquityTradingPower();

                //true���Z�b�g
                l_tpResult.setResultFlg(true);
                //(���񒍕���)����\�z���Z�b�g
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(null);

                /*
                 * �c�ƕ��X�@@���@@�a����s���̏ꍇ
                 * �iget���X�^�C�v()�̖߂�l == 4:��ʍ������X && �a����s���z  < 0�j
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblLackAmt < 0)
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //�a����s���z���Z�b�g
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackAmt));
                }
                //�ȊO�̏ꍇ
                else
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(null);
                }
                
                /*
                 * �]�͍X�V�����{
                 */
                //�]�͍X�V�t���O==true�̏ꍇ
                if (l_blnUpdateFlg == true)
                {
                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            /*
             * �ȊO�i����]�̓`�F�b�NNG�j�̏ꍇ
             */
            else
            {
                //false���Z�b�g
                l_tpResult.setResultFlg(false);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();

                //0 �� �a����s���z ���� get���X�^�C�v()�̖߂�l �� 4:��ʍ������X�̏ꍇ
                if(l_dblLackAmt < 0
                        && BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == false)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv =
                        WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                }
                //����ȊO(��K���G���[�̏ꍇ)
                else
                {
                    l_tpErrorInfo.tradinPowerErrorDiv =
                        WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                    l_tpErrorInfo.lackAccountBalance = 0;
                }

                l_tpErrorInfo.buyOrderPossibleAmount =
                    this.getBuyOrderPossibleAmount(l_subAccount, l_newOrderSpecs[0]);
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecs;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
    }

    /**
     * (validate����]��<�����������t>)<BR>
     * <BR>
     * �����������t�ɂ����āA���񒍕�����������������]�̓`�F�b�N�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�����������t>�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)
     * true�̎��A�]�͍Čv�Z���������{����
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerEquitySell(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME = "validateTradingPowerEquitySell(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        //�M�p�����J�݋敪���擾
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z����
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //����ID
        long l_lngProductId = l_newOrderSpecs[0].getProductId();

        //(������)���t���]�͍X�V
        WEB3TPTradingPowerUpdAfterSell l_tpUpdAfter = new WEB3TPTradingPowerUpdAfterSell(
            l_lngAccountId,
            l_blnMargin,
            l_calcCond,
            l_newOrderSpecs,
            l_lngProductId);

        //����]�͌���
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        //��n��(:int)
        int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());
        //�������t���O
        boolean l_blnTradingPower = false;
        //���X�^�C�v
        BranchTypeEnum l_branchType = l_calcCond.getBranchType();

        //�����ڋq�̏ꍇ
        if(l_blnMargin == false)
        {
            //(������)���o�\����(��n��)
            WEB3TPTradingPowerCalcEquity l_calcEquityAfter = new WEB3TPTradingPowerCalcEquity(
                l_tpUpdAfter.calcTradingpowerUpdResultEquityIncUnexecSellOrder(),
                l_calcCond);
            double l_dblActualPayBalAfter = l_calcEquityAfter.calcActualPaymentBalance(l_intDeliDate);

            //(�����O)���t���]�͍X�V
            WEB3TPTradingPowerUpdAfterSell l_tpUpdBefore = new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnMargin,
                l_calcCond,
                null,
                l_lngProductId);

            //(�����O)���o�\����(��n��)
            WEB3TPTradingPowerCalcEquity l_calcEquityBefore = new WEB3TPTradingPowerCalcEquity(
                l_tpUpdBefore.calcTradingpowerUpdResultEquityIncUnexecSellOrder(),
                l_calcCond);
            double l_dblActualPayBalBefore = l_calcEquityBefore.calcActualPaymentBalance(l_intDeliDate);

            //�������ϑ����O���t�����l����(������)���o�\����(T+��n��)
            double l_dblActualPaymentBalance = 0;

            // (*)����t���[
            // (������)���o�\����(T+��n��) < 0�A���A
            // (�����O)���o�\����(T+��n��) <= (������)���o�\����(T+��n��)�̏ꍇ
            if (l_dblActualPayBalAfter < 0 && l_dblActualPayBalBefore <= l_dblActualPayBalAfter)
            {
                //get��Е��X�ʗ]�͌v�Z����(String)
                String l_strExcludeExceptSettlementBuyAmountCheck =
                    l_calcCond.getInstBranCalcCondition(
                        WEB3TPCalcCondition.EXCLUDE_EXCEPT_SETTLEMENT_BUY_AMOUNT_CHECK);
 
                //is�a��،��]����( )
                boolean l_blnIsAssetEvalDiv = l_calcCond.isAssetEvalDiv();

                //get���v��S����(int)
                //�i�����O�j���v��S�������擾����B
                //[����]
                //��n��:�]�͌v�Z����.calc�w���()�̖߂�l
                double l_dblDayTradeRestraintBefore = l_calcEquityBefore.getDayTradeRestraint(l_intDeliDate);

                //get���v��S����(int)
                //�i������j���v��S�������擾����B
                //[����]
                //��n��:�]�͌v�Z����.calc�w���()�̖߂�l
                double l_dblDayTradeRestraintAfter = l_calcEquityAfter.getDayTradeRestraint(l_intDeliDate);

                // (*)����t���[
                // �������ϑ����O���t�����l���̍������σ`�F�b�N����(
                // get��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F���{����)���A
                // is�a��،��]����()�̖߂�l == true ����
                // (�����O)get���v��S����(T+��n��) < (������)get���v��S����(T+��n��)�̏ꍇ
                if (WEB3TPExcludeExceptSettlementBuyAmountCheckDef.EXECUTE.equals(
                    l_strExcludeExceptSettlementBuyAmountCheck)
                    && l_blnIsAssetEvalDiv && l_dblDayTradeRestraintBefore < l_dblDayTradeRestraintAfter)
                {
                    //calc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`(boolean)
                    List l_lisOrders = l_tpUpdAfter.calcTradingpowerUpdResultEquityIncUnexecSellOrder(true);

                    //���Y�]�͏��<�����ڋq>(List, �]�͌v�Z����)
                    WEB3TPTradingPowerCalcEquity l_tradingPowerCalcEquity =
                        new WEB3TPTradingPowerCalcEquity(l_lisOrders, l_calcCond);

                    //calc���o�\����(int)
                    l_dblActualPaymentBalance =
                        l_tradingPowerCalcEquity.calcActualPaymentBalance(l_intDeliDate);
                }
            }

            //�u�c�ƕ��X�v�̏ꍇ
            if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true)
            {
                l_blnTradingPower = true;
            }
            //�ȊO�̏ꍇ
            else
            {
                // �������ϑ����O���t�����l����(������)���o�\����(T+��n��) >= 0 ���A
                // (�u�i������j���o�\����(T+��n��) >= 0�v �܂���
                //�u�i������j���o�\����(T+��n��) >= �i�����O�j���o�\����(T+��n��)�v)�̏ꍇ
                if(l_dblActualPaymentBalance >= 0
                    && (l_dblActualPayBalAfter >= 0
                    || l_dblActualPayBalAfter >= l_dblActualPayBalBefore))
                {
                    l_blnTradingPower = true;
                }
                //�ȊO�̏ꍇ
                else
                {
                    l_blnTradingPower = false;
                }
            }

            //����t���[�F���OK�̏ꍇ
            if(l_blnTradingPower == true)
            {
                //����t���O
                l_tpResult.setResultFlg(true);
                //����\�z
                l_tpResult.setTradingPower(0);
                //����G���[���
                l_tpResult.setTpErrorInfo(null);

                /*
                 * [�u�c�ƕ��X�v ���� �u(������)���o�\����(T+��n��) < 0�v ���� 
                 *  �u�i������j���o�\����(T+��n��) < �i�����O�j���o�\����(T+��n��)�v�̏ꍇ]
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblActualPayBalAfter < 0
                        && l_dblActualPayBalAfter < l_dblActualPayBalBefore)
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //�a����s���z���Z�b�g
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblActualPayBalAfter));
                }
                //�ȊO�̏ꍇ
                else
                {
                    //���ӕ����\���敪
                    l_tpResult.setAttentionObjectionType(null);
                }

                //submit�����̏ꍇ
                if(l_blnUpdateFlg == true)
                {
                    //�]�͍X�V
                    WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId,
                        l_blnMargin,
                        l_calcCond,
                        l_newOrderSpecs);

                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultEquity(l_tpUpd.calcTradingpowerUpdResultEquity());
                }
            }
            //����t���[�F���NG�̏ꍇ
            else
            {
                //����t���O
                l_tpResult.setResultFlg(false);
                //����\�z
                l_tpResult.setTradingPower(0);
                //���ӕ����\���敪
                l_tpResult.setAttentionObjectionType(null);

                /*
                 * ����]�̓G���[���
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                //�������ϑ����O���t�����l����(������)���o�\����(T+��n��) < 0 �̏ꍇ
                if (l_dblActualPaymentBalance < 0)
                {
                    //�a����s���z = ABS(�������ϑ����O���t�����l����(������)���o�\����(T+��n��))
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblActualPaymentBalance);
                }
                //����ȊO
                else
                {
                    //�a����s���z = ABS((������)���o�\����(T+��n��))
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblActualPayBalAfter);
                }

                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = this.getSellOrderPossibleQuantity(
                    l_subAccount,
                    l_newOrderSpecs[0]);
                l_tpErrorInfo.marginSecInfo = null;

                //����G���[���
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }
        //�M�p�ڋq�̏ꍇ
        else
        {
            //(������)���o�\����(��n��)
            WEB3TPTradingPowerCalcMargin l_calcMarginAfter = new WEB3TPTradingPowerCalcMargin(
                l_tpUpdAfter.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
                l_calcCond);
            double l_dblActualPayBalAfter = l_calcMarginAfter.calcActualPaymentBalance(l_intDeliDate);

            //(�����O)���t���]�͍X�V
            WEB3TPTradingPowerUpdAfterSell l_tpUpdBefore = new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnMargin,
                l_calcCond,
                null,
                l_lngProductId);

            //(�����O)���o�\����(��n��)
            WEB3TPTradingPowerCalcMargin l_calcMarginBefore = new WEB3TPTradingPowerCalcMargin(
                l_tpUpdBefore.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
                l_calcCond);
            double l_dblActualPayBalBefore = l_calcMarginBefore.calcActualPaymentBalance(l_intDeliDate);

            //�u�c�ƕ��X�v�܂��́u�i������j���o�\����(T+��n��) >= 0�v �̏ꍇ
            if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                    || l_dblActualPayBalAfter >= 0)
            {
                l_blnTradingPower = true;
            }
            //�ȊO�̏ꍇ
            else
            {
                //�u�i������j���o�\����(T+��n��) >= �i�����O�j���o�\����(T+��n��)�v�@@�̏ꍇ
                if(l_dblActualPayBalAfter >= l_dblActualPayBalBefore)
                {
                    l_blnTradingPower = true;
                }
                //�ȊO�̏ꍇ
                else
                {
                    l_blnTradingPower = false;
                }
            }

            //����t���[�F���OK�̏ꍇ
            if(l_blnTradingPower == true)
            {
                //����t���O
                l_tpResult.setResultFlg(true);
                //����\�z
                l_tpResult.setTradingPower(0);
                //����G���[���
                l_tpResult.setTpErrorInfo(null);
                //���ӕ����\���敪
                l_tpResult.setAttentionObjectionType(null);

                /*
                 * [�u�c�ƕ��X�v ���� �u(������)���o�\����(T+��n��) < 0�v ���� 
                 *  �u�i������j���o�\����(T+��n��) < �i�����O�j���o�\����(T+��n��)�v�̏ꍇ]
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblActualPayBalAfter < 0
                        && l_dblActualPayBalAfter < l_dblActualPayBalBefore)
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //�a����s���z���Z�b�g
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblActualPayBalAfter));
                }
                //�ȊO�̏ꍇ
                else
                {
                    //���ӕ����\���敪
                    l_tpResult.setAttentionObjectionType(null);
                }

                //submit�����̏ꍇ
                if(l_blnUpdateFlg == true)
                {
                    //�]�͍X�V
                    WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId,
                        l_blnMargin,
                        l_calcCond,
                        l_newOrderSpecs);

                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL));
                }
            }
            //����t���[�F���NG�̏ꍇ
            else
            {
                //����t���O
                l_tpResult.setResultFlg(false);
                //����\�z
                l_tpResult.setTradingPower(0);
                //���ӕ����\���敪
                l_tpResult.setAttentionObjectionType(null);

                /*
                 * ����]�̓G���[���
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblActualPayBalAfter);
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = this.getSellOrderPossibleQuantity(
                    l_subAccount,
                    l_newOrderSpecs[0]);
                l_tpErrorInfo.marginSecInfo = null;

                //����G���[���
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }

        //����]�͌��ʂ�ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (validate����]��<�M�p�������>) <BR>
     * <BR>
     * �M�p��������ɂ����āA���񒍕�����������������]�̓`�F�b�N�����{����B <BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�M�p�������>�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)
     * true�̎��A�]�͍Čv�Z���������{����
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerActualReceipt(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerActualReceipt(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z����
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        /*
         * �����~�敪�`�F�b�N
         */
        //�����~���̏ꍇ
        if (l_calcCond.isTradingStop() == true)
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //����t���O���Z�b�g
            l_tpResult.setResultFlg(false);
            //����\�z���Z�b�g
            l_tpResult.setTradingPower(0);

            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //���̑����i���t�]�͒�~���̏ꍇ
        if (l_calcCond.isOtherTradingStop() == true)
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //����t���O���Z�b�g
            l_tpResult.setResultFlg(false);
            //����\�z���Z�b�g
            l_tpResult.setTradingPower(0);

            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv =
                WEB3TPTradingPowerErrorDivDef.OTHER_TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //�]�͍X�V�I�u�W�F�N�g�𐶐�
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�M�p�ڋq�̏ꍇ
        else
        {
            /*
             * �Ǐؗ]�̓`�F�b�N
             */
            //�����O�]�͌v�Z���ʂ��擾
            List l_curUpdResults = WEB3TPTradingPowerCalcMargin
                .findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcMargin l_curCalcMargin = new WEB3TPTradingPowerCalcMargin(
                    l_curUpdResults, l_calcCond);

            //������
            Date l_datBizDate = l_newOrderSpecs[0].getOrderBizDate();
            int l_intBizDate = l_calcCond.calcSpecifiedPoint(l_datBizDate);

            //��n��
            Date l_datDeliDate = l_newOrderSpecs[0].getDeliveryDate();
            int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliDate);

            //�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>���擾
            WEB3TPCalcResult l_marginCallPower = l_curCalcMargin
                .calcMarginCallPowerForCheck(
                        OrderTypeEnum.SWAP_MARGIN_LONG, l_intBizDate,
                        l_intDeliDate);

            //�Ǐ؃`�F�b�NNG�̏ꍇ(�]�͌v�Z����.����\�z < 0)
            if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
            {
                //����]�͌��ʃI�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = l_marginCallPower.tradingPower;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            /*
             * �]�͌v�Z����(List)�`����蔄�t�����l���`���擾
             */
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            /*
             * ���������̓�K���`�F�b�N�����{
             */
            //���������e��蒍������ID���擾
            long l_lngProductId = l_newOrderSpecs[0].getProductId();
            //���������e���s��ID���擾
            long l_lngMarketId = l_newOrderSpecs[0].getMarketId();

            //��K���`�F�b�N�G���[�������̔z����擾
            WEB3TPMarginSecurityInfo[] l_marginSecs = this.calcMarginSecurity(l_subAccount,
                    l_lngProductId, l_calcMargin, l_tpUpd, OrderTypeEnum.SWAP_MARGIN_LONG,
                    l_lngMarketId, l_datBizDate);

            /*
             * �a����s���z���擾
             */
            //��Е��X�ʗ]�͌v�Z����."����]�̓`�F�b�N���@@-�M�p����������" ���擾
            String l_strTradingPowerCheck = l_calcCond
                .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINSWAPLONG_TRADINGPOWER_CHECK);

            //���񒍕��̎�n���ɑΉ�����w������擾
            int l_intSpecifiedPoint = l_calcCond
                .calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());

            //�a����s���z
            double l_dblLackAmt = 0;

            //T+0..T+��n��-1
            for(int index = 0; index < l_intSpecifiedPoint; index++)
            {
                //�a��������]��(T+0..T+��n��-1)
	            l_dblLackAmt = Math.min(l_calcMargin
	                .calcAccountBalanceDemandPower(index), l_dblLackAmt);
            }
            //T+��n��..T+5
            for(int index = l_intSpecifiedPoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //���o�\����(T+��n��..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcActualPaymentBalance(index), l_dblLackAmt);

                //"����]�̓`�F�b�N���@@-�M�p����������" = 1:ADDDEPOSIT �̏ꍇ
                if(WEB3TPMarginSwapLongTradingPowerCheckTypeDef.ADDDEPOSIT
                    .equals(l_strTradingPowerCheck) == true)
                {
                    //�Ǐؗ]��(index)���擾
                    l_dblLackAmt = Math.min(l_calcMargin
                        .calcMarginCallPower(index), l_dblLackAmt);
                }
                //�ȊO �̏ꍇ
                else
                {
                    //�ۏ؋��]��(index)���擾
                    l_dblLackAmt = Math.min(l_calcMargin
                        .calcMarginPower(index), l_dblLackAmt);
                }
            }

            //���X�^�C�v���擾����B
            BranchTypeEnum l_branchType = l_calcCond.getBranchType();
            
            /*
             * ����]�͌��ʃI�u�W�F�N�g�𐶐�
             */
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * ����]�̓`�F�b�NOK�i��K���`�F�b�NOK�@@���@@�i�a����s���G���[�`�F�b�NOK�܂��́A�c�ƕ��X�j�j�̏ꍇ
             * (��K���������ꗗ[] == null && (0 ���� �a����s���z�@@�܂��� get���X�^�C�v()�̖߂�l == 4:��ʍ������X)
             */
            if(l_marginSecs == null
                    && (l_dblLackAmt >= 0 || BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true))
            {
                /*
                 * �]�͌v�Z����(List)���擾
                 */
                l_updResults =
                    l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

                //���Y�]�͏��I�u�W�F�N�g�𐶐�
                l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

                //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                WEB3TPCalcResult l_calcResult = l_calcMargin.calcAppliedActualReceiptTradingPower();

                //true���Z�b�g
                l_tpResult.setResultFlg(true);
                //(���񒍕���)����\�z���Z�b�g
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(null);

                /*
                 * �c�ƕ��X�@@���@@�a����s���̏ꍇ
                 * �iget���X�^�C�v()�̖߂�l == 4:��ʍ������X && �a����s���z  < 0�j
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblLackAmt < 0)
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //�a����s���z���Z�b�g
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackAmt));
                }
                //�ȊO�̏ꍇ
                else
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(null);
                }
                
                /*
                 * �]�͍X�V�����{
                 */
                //�]�͍X�V�t���O==true�̏ꍇ
                if (l_blnUpdateFlg == true)
                {
                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            //����]�̓`�F�b�NNG�i�ȊO�j�̏ꍇ
            else
            {
                //false���Z�b�g
                l_tpResult.setResultFlg(false);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();

                //0 �� �a����s���z ���� get���X�^�C�v()�̖߂�l �� 4:��ʍ������X�̏ꍇ
                if(l_dblLackAmt < 0
                        && BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == false)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv =
                        WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                }
                //����ȊO(��K���G���[�̏ꍇ)
                else
                {
                    l_tpErrorInfo.tradinPowerErrorDiv =
                        WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                    l_tpErrorInfo.lackAccountBalance = 0;
                }

                l_tpErrorInfo.buyOrderPossibleAmount =
                    this.getBuyOrderPossibleAmount(l_subAccount, l_newOrderSpecs[0]);
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecs;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
    }

    /**
     * (validate����]��<�M�p������n>)<BR>
     * �M�p������n�ɂ����āA���񒍕�����������������]�̓`�F�b�N�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�M�p������n>�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)
     * true�̎��A�]�͍Čv�Z���������{����
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@roseuid 41593E170359
     */
    protected WEB3TPTradingPowerResult validateTradingPowerActualDelivery(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME = "validateTradingPowerActualDelivery(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        //�M�p�����J�݋敪���擾
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq�̏ꍇ
        if(l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass()
                .getName()
                + "."
                + STR_METHOD_NAME);
        }

        //�]�͌v�Z����
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //����ID
        long l_lngProductId = l_newOrderSpecs[0].getProductId();

        //(������)���t���]�͍X�V
        WEB3TPTradingPowerUpdAfterSell l_tpUpdAfter = new WEB3TPTradingPowerUpdAfterSell(
            l_lngAccountId,
            l_blnMargin,
            l_calcCond,
            l_newOrderSpecs,
            l_lngProductId);

        //����]�͌���
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        //��n��(:int)
        int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());
        //�������t���O
        boolean l_blnTradingPower = false;
        //���X�^�C�v
        BranchTypeEnum l_branchType = l_calcCond.getBranchType();
        
        //(������)���o�\����(��n��)
        WEB3TPTradingPowerCalcMargin l_calcMarginAfter = new WEB3TPTradingPowerCalcMargin(
            l_tpUpdAfter.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
            l_calcCond);
        double l_dblActualPayBalAfter = l_calcMarginAfter.calcActualPaymentBalance(l_intDeliDate);

        //(�����O)���t���]�͍X�V
        WEB3TPTradingPowerUpdAfterSell l_tpUpdBefore = new WEB3TPTradingPowerUpdAfterSell(
            l_lngAccountId,
            l_blnMargin,
            l_calcCond,
            null,
            l_lngProductId);

        //(�����O)���o�\����(��n��)
        WEB3TPTradingPowerCalcMargin l_calcMarginBefore = new WEB3TPTradingPowerCalcMargin(
            l_tpUpdBefore.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
            l_calcCond);
        double l_dblActualPayBalBefore = l_calcMarginBefore.calcActualPaymentBalance(l_intDeliDate);

        //�u�c�ƕ��X�v�܂��́u�i������j���o�\����(T+��n��) >= 0�v �̏ꍇ
        if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                || l_dblActualPayBalAfter >= 0)
        {
            l_blnTradingPower = true;
        }
        //�ȊO�̏ꍇ
        else
        {
            //�u�i������j���o�\����(T+��n��) >= �i�����O�j���o�\����(T+��n��)�v�@@�̏ꍇ
            if(l_dblActualPayBalAfter >= l_dblActualPayBalBefore)
            {
                l_blnTradingPower = true;
            }
            //�ȊO�̏ꍇ
            else
            {
                l_blnTradingPower = false;
            }
        }

        //����t���[�F���OK�̏ꍇ
        if(l_blnTradingPower == true)
        {
            //����t���O
            l_tpResult.setResultFlg(true);
            //����\�z
            l_tpResult.setTradingPower(0);
            //����G���[���
            l_tpResult.setTpErrorInfo(null);
            //���ӕ����\���敪
            l_tpResult.setAttentionObjectionType(null);

            /*
             * [�u�c�ƕ��X�v ���� �u(������)���o�\����(T+��n��) < 0�v ���� 
             *  �u�i������j���o�\����(T+��n��) < �i�����O�j���o�\����(T+��n��)�v�̏ꍇ]
             */
            if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                    && l_dblActualPayBalAfter < 0
                    && l_dblActualPayBalAfter < l_dblActualPayBalBefore)
            {
                //���ӕ����\���敪���Z�b�g
                l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                //�a����s���z���Z�b�g
                l_tpResult.setLackAccountBalance(Math.abs(l_dblActualPayBalAfter));
            }
            //�ȊO�̏ꍇ
            else
            {
                //���ӕ����\���敪
                l_tpResult.setAttentionObjectionType(null);
            }

            //submit�����̏ꍇ
            if(l_blnUpdateFlg == true)
            {
                //�]�͍X�V
                WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                    l_lngAccountId,
                    l_blnMargin,
                    l_calcCond,
                    l_newOrderSpecs);

                //�]�͍X�V�����{
                l_tpUpd.saveTradingpowerUpdResultMargin(l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL));
            }
        }
        //����t���[�F���NG�̏ꍇ
        else
        {
            //����t���O
            l_tpResult.setResultFlg(false);
            //����\�z
            l_tpResult.setTradingPower(0);
            //���ӕ����\���敪
            l_tpResult.setAttentionObjectionType(null);

            /*
             * ����]�̓G���[���
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
            l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblActualPayBalAfter);
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = this.getSellOrderPossibleQuantity(
                l_subAccount,
                l_newOrderSpecs[0]);
            l_tpErrorInfo.marginSecInfo = null;

            //����G���[���
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);
        }

        //����]�͌��ʂ�ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (validate����]��<�M�p������n���>)<BR>
     * �M�p������n��������ɂ����āA���񒍕�����������������]�̓`�F�b�N�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�M�p������n���>�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)
     * true�̎��A�]�͍Čv�Z���������{����
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerActualDeliveryCancel(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerActualDeliveryCancel(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z����
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //�]�͍X�V�I�u�W�F�N�g�𐶐�
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�M�p�ڋq�̏ꍇ
        else
        {
            /*
             * �]�͌v�Z����(List)�`����蔄�t�����l���`���擾
             */
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            /*
             * ����]�͌��ʃI�u�W�F�N�g�𐶐�
             */
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * �a����s���z���v�Z����
             * 
             *  �@@�a����s���z = MIN( �a��������]��(T+0..T+��n��-1), 
             *                       ���o�\����(T+��n��..5), 
             *                       �ۏ؋����o�]��(T+��n��..5) )
             */
            //���񒍕��̎�n���ɑΉ�����w������擾
            int l_intSpecifiedPoint = l_calcCond
                .calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());

            double l_dblLackAmt = 0;

            //T+0..T+��n��-1
            for(int index = 0; index < l_intSpecifiedPoint; index++)
            {
                //�a��������]��(T+0..T+��n��-1)
	            l_dblLackAmt = Math.min(l_calcMargin
	                .calcAccountBalanceDemandPower(index), l_dblLackAmt);
            }
            //T+��n��..T+5
            for(int index = l_intSpecifiedPoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //���o�\����(T+��n��..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcActualPaymentBalance(index), l_dblLackAmt);

                //�ۏ؋����o�]��(T+��n��..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcMarginDrawPower(index), l_dblLackAmt);
            }
            
            //���X�^�C�v���擾
            BranchTypeEnum l_branchType = l_calcCond.getBranchType();
            
            /*
             * ����]�̓`�F�b�NOK�i�a����s���G���[�`�F�b�NOK�܂��́A�c�ƕ��X�j�̏ꍇ
             * ((0 ���� �a����s���z�@@�܂��� get���X�^�C�v()�̖߂�l == 4:��ʍ������X)
             */
            if(l_dblLackAmt >= 0
                    || BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true)
            {
                //true���Z�b�g
                l_tpResult.setResultFlg(true);
                //(���񒍕���)����\�z���Z�b�g
                l_tpResult.setTradingPower(0);
                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(null);

                /*
                 * �c�ƕ��X�@@���@@�a����s���̏ꍇ
                 * �iget���X�^�C�v()�̖߂�l == 4:��ʍ������X && �a����s���z  < 0�j
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblLackAmt < 0)
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //�a����s���z���Z�b�g
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackAmt));
                }
                //�ȊO�̏ꍇ
                else
                {
                    //���ӕ����\���敪���Z�b�g
                    l_tpResult.setAttentionObjectionType(null);
                }

                /*
                 * �]�͍X�V�����{
                 */
                //�]�͍X�V�t���O==true�̏ꍇ
                if (l_blnUpdateFlg == true)
                {
                    /*
                     * �]�͌v�Z����(List)���擾
                     */
                    l_updResults = l_tpUpd
                        .calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);
                    
                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            //����]�̓`�F�b�NNG�i�ȊO�j�̏ꍇ
            else
            {
                //false���Z�b�g
                l_tpResult.setResultFlg(false);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();

                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;

                l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
    }

    /**
     * (validate����]��<�M�p����V�K��>)<BR>
     * �M�p����V�K���ɂ����āA���񒍕�����������������]�̓`�F�b�N�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�M�p����V�K��>�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)
     * true�̎��A�]�͍Čv�Z���������{����
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPowerMargin(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerMargin(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z����
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        /*
         * �����~�敪�`�F�b�N
         */
        //�����~���̏ꍇ
        if (l_calcCond.isTradingStop() == true)
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //����t���O���Z�b�g
            l_tpResult.setResultFlg(false);
            //����\�z���Z�b�g
            l_tpResult.setTradingPower(0);

            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //�M�p�V�K���]�͒�~���̏ꍇ
        if (l_calcCond.isMarginOpenPositionStop() == true)
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //����t���O���Z�b�g
            l_tpResult.setResultFlg(false);
            //����\�z���Z�b�g
            l_tpResult.setTradingPower(0);

            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv =
                WEB3TPTradingPowerErrorDivDef.MARGIN_OPEN_POSITION_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //get��Е��X�ʗ]�͌v�Z����(String)
        //���X�p�v���t�@@�����X�e�[�u�����A����.�v���t�@@�����X���ɑΉ�����
        //�v���t�@@�����X�̒l���擾����B
        String  l_strSecondDepositMarginopenTpstop =
            l_calcCond.getInstBranCalcCondition(WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_MARGINOPEN_TPSTOP);

        //(*)����t���[
        //get��Е��X�ʗ]�͌v�Z����()�̖߂�l�� 1�F���{����ꍇ�A
        //�ȉ��̏��������{����B
        if (WEB3SecondDepositMarginOpenTpStopDef.EXECUTE.equals(
                l_strSecondDepositMarginopenTpstop))
        {
            //create���������Ǘ�(�ڋq)
            //���������Ǘ��𐶐�����B
            //[����]
            //�ڋq�FgetMainAccount()�̖߂�l
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

            //is��񐅏��Ǐؔ���( )
            //��񐅏��Ǐ؂��������Ă��邩���肷��B
            boolean l_blnIsSecondAdddeposit = l_paymentRequisitionManagement.isSecondAdddeposit();

            if (l_blnIsSecondAdddeposit)
            {
                //����]�͌��ʃI�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.MARGIN_OPEN_POSITION_STOP_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }
        }
        //�]�͍X�V�I�u�W�F�N�g�𐶐�
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);
        
        /*
         * (������)�]�͌v�Z����(List)���擾
         */
        List l_updResults =
            l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);
        
        //(������)���Y�]�͏��𐶐�
        WEB3TPTradingPowerCalcMargin l_calcMargin =
            new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);
        //(������)�]�͌v�Z���ʂ��擾
        WEB3TPCalcResult l_calcResult = l_calcMargin.calcAppliedMarginTradingPower();
        
        /*
         * ���������̕ۏ؋���/�����ۏ؋������擾����B
         */
        //���������e��蒍����ʂ��擾
        OrderTypeEnum l_orderType = l_newOrderSpecs[0].getOrderType();
        //���������e��蒍������ID���擾
        long l_lngProductId = l_newOrderSpecs[0].getProductId();
        //���������e���s��ID���擾
        long l_lngMarketId = l_newOrderSpecs[0].getMarketId();
        //���������e��蔭�������擾
        Date l_datBizDate = l_newOrderSpecs[0].getOrderBizDate();

        //(��������)�ۏ؋���
        int l_intOrderMarginDepRate = 0;
        //(��������)�����ۏ؋���
        int l_intOrderCashMarginDepRate = 0;

        try
        {
            //�����������Row���擾�B
            EqtypeTradedProductRow l_row = WEB3TPTradingPowerServiceImpl.getEqtypeTradedProductRow(
                l_lngProductId,
                l_lngMarketId);

            //�V�K�����̏ꍇ
            if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType) == true)
            {
                l_intOrderMarginDepRate = (int) l_row.getLongMarginDepositRate();
                l_intOrderCashMarginDepRate = (int) l_row.getLongCashMarginDepositRate();
            }
            //�V�K�����̏ꍇ
            else if(OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) == true)
            {
                l_intOrderMarginDepRate = (int) l_row.getShortMarginDepositRate();
                l_intOrderCashMarginDepRate = (int) l_row.getShortCashMarginDepositRate();
            }
        }
        catch(NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        /*
         * �a����s���z���v�Z����B
         */
        //�a����s���z
        double l_dblLackAmt = 0;

        //(���X)�ۏ؋���
        int l_intMarginDepRate = l_calcCond.getMarginDepositRate();
        //(���X)�����ۏ؋���
        int l_intCashMarginDepRate = l_calcCond.getCashMarginDepositRate();

        //���S�ۋK�������̏ꍇ
        //[(���X)�����ۏ؋��� < (��������)�����ۏ؋���]
        if(l_intCashMarginDepRate < l_intOrderCashMarginDepRate)
        {
            /*
             * [�v�Z��]
             * �@@�a����s���z�@@=�@@MIN(�a��������]��(T+�M�p�V�K�����),�E�E�E,�a��������]��(T+5))
             * 
             * ���v�Z���ڂ̎擾���@@
             * �@@�E���o�\��(T+n) = 
             * �@@�@@�@@(������)���Y�]�͏��<�M�p�ڋq>.calc�a��������]��(T+n)
             * �@@�E�M�p�V�K����� = �]�͌v�Z����.get�]�͌v�Z���<�M�p�V�K��>()
             */
            //LOOP����(��n���`T+5�̊�)
            for(int index = l_calcCond.getMarginBasePoint(); index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //�a��������]��(T+n)
                double l_dblActPayBal = l_calcMargin.calcAccountBalanceDemandPower(index);

                l_dblLackAmt = Math.min(l_dblActPayBal, l_dblLackAmt);
            }
        }
        
        /*
         * ���������̓�K���`�F�b�N�����{
         */
        //��K���������[]
        WEB3TPMarginSecurityInfo[] l_marginSecs = null;
        
        //������� == �M�p�V�K�����̏ꍇ
        if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType) == true)
        {
            //��K���������[]���擾
            l_marginSecs = this.calcMarginSecurity(l_subAccount, l_lngProductId, l_calcMargin,
                    l_tpUpd, l_orderType, l_lngMarketId, l_datBizDate);
        }

        //is����ۏ؋���L������(long, �]�͍X�V, ���Y�]�͏��<�M�p�ڋq>)
        boolean l_blnIsReceiptDepositRateOver = this.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_calcMargin);

        /*
         * ����]�͌��ʃI�u�W�F�N�g�𐶐�
         */        
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        
        //����]�̓`�F�b�NOK�̏ꍇ
        //(0 <= (������)�V�K���\�z ���� 0<= �a����s���z ���� ��K���������[] == null
        //���� is����ۏ؋���L������()�̖߂�l == false)
        if (l_calcResult.tradingPower >= 0 && l_dblLackAmt >= 0 && l_marginSecs == null
            && !l_blnIsReceiptDepositRateOver)
        {
            //true���Z�b�g
            l_tpResult.setResultFlg(true);
            //(���񒍕���)����\�z���Z�b�g
            l_tpResult.setTradingPower(l_calcResult.tradingPower);
            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(null);
            
            /*
             * ���S�ۋK�������̏ꍇ
             */
            if(l_intMarginDepRate < l_intOrderMarginDepRate
                || l_intCashMarginDepRate < l_intOrderCashMarginDepRate)
            {
                //���ӕ����\���敪���Z�b�g
                l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.INC_DEPOSIT_REG_ATTENTION);
            }
            
            /*
             * �]�͍X�V�����{
             */
            //�]�͍X�V�t���O==true�̏ꍇ
            if (l_blnUpdateFlg == true)
            {
                //�]�͍X�V�����{
                l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
            }
        }
        //�ȊO�i����]�̓`�F�b�NNG�j�̏ꍇ
        else
        {
            //false���Z�b�g
            l_tpResult.setResultFlg(false);
            
            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            
            //���S�ۋK�������̏ꍇ
            if(l_intMarginDepRate < l_intOrderMarginDepRate
                || l_intCashMarginDepRate < l_intOrderCashMarginDepRate)
            {
                /*
                 * ���S�ۋK�������V�K���\�z���擾
                 */
                //(�����O)�]�͌v�Z����(List)���擾
                List l_updResultsBeFore = WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //(�����O)���Y�]�͏��𐶐�
                WEB3TPTradingPowerCalcMargin l_calcMarginBefore = new WEB3TPTradingPowerCalcMargin(
                    l_updResultsBeFore,
                    l_calcCond);

                //(�����O)�]�͌v�Z���ʂ��擾
                WEB3TPCalcResult l_calcResultBefore = l_calcMarginBefore.calcAppliedMarginTradingPowerIncDeposit(
                    Math.max(l_intMarginDepRate, l_intOrderMarginDepRate),
                    Math.max(l_intCashMarginDepRate, l_intOrderCashMarginDepRate));

                //(���S��)�a����s���G���[�̏ꍇ
                //(�a����s���z < 0)
                if(l_dblLackAmt < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                    l_tpErrorInfo.marginTradingPowerIncDeposit = Math.max(
                        l_calcResultBefore.tradingPower,
                        0);
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //(���S��)�ۏ؋��s���G���[�̏ꍇ
                //(������)�V�K���\�z < 0)
                else if(l_calcResult.tradingPower < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_MARGIN_POWER;
                    l_tpErrorInfo.marginTradingPowerIncDeposit = Math.max(
                        l_calcResultBefore.tradingPower,
                        0);
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //��K���G���[�̏ꍇ
                else if (l_marginSecs != null)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //����ۏ؋���L�����߃G���[�̏ꍇ
                else if (l_blnIsReceiptDepositRateOver)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.RECEIPT_DEPOSIT_RATE_OVER_ERROR;
                }
            }
            //�ȊO�i�ʏ�����j�̏ꍇ
            else
            {
                //�ۏ؋��s���G���[�̏ꍇ
                //(������)�V�K���\�z < 0)
                if(l_calcResult.tradingPower < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_MARGIN_POWER;
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //��K���G���[�̏ꍇ
                else if (l_marginSecs != null)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //����ۏ؋���L�����߃G���[�̏ꍇ
                else if (l_blnIsReceiptDepositRateOver)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.RECEIPT_DEPOSIT_RATE_OVER_ERROR;
                }
            }
            
            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);
        }
        
        //����]�͌��ʂ�ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (validate����]��<�I�v�V�����V�K����>)<BR>
     * �I�v�V�����V�K�����ɂ����āA����]�̓`�F�b�N�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�I�v�V�����V�K����>�`�敨�؋����`�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpec - (�敨OP���������e)
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@roseuid 416FA7EE0285
     */
    protected WEB3TPTradingPowerResult validateTradingPowerOptionBuy(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoNewOrderSpec l_newOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerOptionBuy(WEB3GentradeSubAccount, WEB3IfoNewOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);

        //�؋����v�Z�T�[�r�X���擾
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);

        try
        {
            //����]�͌��ʂ𐶐�        
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();

            //�؋����v�Z�I�u�W�F�N�g���擾
            WEB3IfoDepositCalc l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount, l_newOrderSpec);
            
            //�V�K���]�͉\�t���O
            boolean l_newOpenFlg = l_ifoDepCalc.getIfoDepositCalcCondition().isNewOpenTradingPowerAvailable();
            //�K�v�Œ�ۏ؋�
            double l_dblMinIfoDep = l_ifoDepCalc.getIfoDepositCalcCondition().getMinIfoDeposit();
            //����؋����c��
            double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
            //�������z
            double l_dblNonPayAmt = l_ifoDepCalc.calcNonPayAmount();

            //�V�K���]�͕s�̏ꍇ
            if (l_newOpenFlg == false)
            {
                //����t���O��false����
                l_tradingPowerResult.setResultFlg(false);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //�V�K���]�͉\�Ŏ���؋����c�����K�v�Œ�ۏ؋��̏ꍇ
            if (l_dblRecIfoDepBal < l_dblMinIfoDep)
            {
                //����t���O��false����
                l_tradingPowerResult.setResultFlg(false);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //�V�K���]�͉\�Ŗ������z��0�̏ꍇ
            if (l_dblNonPayAmt > 0)
            {
                //����t���O��false����
                l_tradingPowerResult.setResultFlg(false);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }

            //�I�v�V�����V�K���\�z
            double l_dblTradingPower = l_ifoDepCalc.calcIfoDepositTradingPowerAmount();

            //�؋����v�Z.calc�؋����]�͊z() >= 0�̏ꍇ
            if (l_dblTradingPower >= 0)
            {
                //����t���O��true����
                l_tradingPowerResult.setResultFlg(true);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //�ȊO�̎�
            else
            {
                //����t���O��false����
                l_tradingPowerResult.setResultFlg(false);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
        }
        catch (WEB3SystemLayerException e)
        {
            throw new WEB3BaseRuntimeException(
                e.getErrorInfo(),
                e.getErrorMethod(),
                e.getErrorMessage(),
                e.getException());
        }
    }

    /**
     * (validate����]��<�敨�I�v�V�����V�K��>)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�敨�I�v�V�����V�K��>�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpec - (�敨OP���������e)
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@roseuid 416FA7EE0295
     */
    protected WEB3TPTradingPowerResult validateTradingPowerFuturesOption(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoNewOrderSpec l_newOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerFuturesOption(WEB3GentradeSubAccount, WEB3IfoNewOrderSpec)";
        log.entering(STR_METHOD_NAME);

        //����]�͌��ʂ𐶐�        
        WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();

        //�؋����v�Z�T�[�r�X���擾
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);

        //�؋����v�Z�I�u�W�F�N�g���擾
        WEB3IfoDepositCalc l_ifoDepCalc = null;
        try
        {
            l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount, l_newOrderSpec);
            //�؋����v�Z�������擾
            WEB3IfoDepositCalcCondition l_ifoCalcCond =
                l_service.createIfoDepositCalcCondition(l_subAccount);
        }
        catch (WEB3SystemLayerException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                e.getErrorInfo(),
                e.getErrorMethod(),
                e.getErrorMessage(),
                e.getException());
        }

        //�؋����v�Z�������擾
        WEB3IfoDepositCalcCondition l_ifoCalcCond = l_ifoDepCalc.getIfoDepositCalcCondition();

        //SPAN�̗p���{��Ђ̏ꍇ
        if (l_ifoCalcCond.isSPANUsable() == true)
        {
            //�V�K���]�͉\�̏ꍇ
            if (l_ifoCalcCond.isNewOpenTradingPowerAvailable() == true)
            {
                //����؋����c��
                double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
                //�؋������v�z
                double l_dblIfoDepReqAmt = l_ifoDepCalc.calcIfoDepositRequiredAmount();
                //�K�v�Œ�؋���
                double l_dblMinIfoDep = l_ifoCalcCond.getMinIfoDeposit();

                //����؋����c�����K�v�Œ�؋����̏ꍇ
                if (l_dblRecIfoDepBal < l_dblMinIfoDep)
                {
                    //����t���O��false����
                    l_tradingPowerResult.setResultFlg(false);
                    //����]�͌��ʂ�ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
                //����؋����c�����؋������v�z�̏ꍇ
                else if (l_dblRecIfoDepBal < l_dblIfoDepReqAmt)
                {
                    //����t���O��false����
                    l_tradingPowerResult.setResultFlg(false);
                    //����]�͌��ʂ�ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
                //�ȊO�̏ꍇ
                else
                {
                    //����t���O��true����
                    l_tradingPowerResult.setResultFlg(true);
                    //����]�͌��ʂ�ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
            }
            //�V�K���]�͕s�\�̏ꍇ
            else
            {
                //����t���O��false����
                l_tradingPowerResult.setResultFlg(false);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
        }
        //SPAN��̗p��Ђ̏ꍇ
        else
        {
            //�V�K���]�͊z
            boolean l_newOpenFlg = l_ifoCalcCond.isNewOpenTradingPowerAvailable();
            //�K�v�Œ�ۏ؋�
            double l_dblMinIfoDep = l_ifoCalcCond.getMinIfoDeposit();
            //����؋����c��
            double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
            //�������z
            double l_dblNonPayAmt = l_ifoDepCalc.calcNonPayAmount();

            //�V�K���]�͕s�̏ꍇ
            if (l_newOpenFlg == false)
            {
                //����t���O��false����
                l_tradingPowerResult.setResultFlg(false);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //�V�K���]�͉\�Ŏ���؋����c�����K�v�Œ�ۏ؋��̏ꍇ
            if (l_dblRecIfoDepBal < l_dblMinIfoDep)
            {
                //����t���O��false����
                l_tradingPowerResult.setResultFlg(false);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //�V�K���]�͉\�Ŗ������z��0�̏ꍇ
            if (l_dblNonPayAmt > 0)
            {
                //����t���O��false����
                l_tradingPowerResult.setResultFlg(false);
                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }

            //�敨OP���i�敪
            IfoDerivativeTypeEnum l_deriEnum = l_newOrderSpec.ifoDerivativeType;
            //���敪
            ContractTypeEnum l_contEnum = l_newOrderSpec.contractType;

            //���|�W�V�����̏ꍇ
            if ((l_deriEnum.intValue() == IfoDerivativeTypeEnum.FUTURES.intValue()
                && l_contEnum.intValue() == ContractTypeEnum.LONG.intValue())
                || l_deriEnum.intValue() == IfoDerivativeTypeEnum.PUT_OPTIONS.intValue())
            {
                //���|�W�V�����\����
                double l_dblPossBuyQty =
                    l_ifoDepCalc.calcPossibleBuyQty(l_newOrderSpec.underlyingProductCode);

                //�V�K���\���� >= 0�̏ꍇ
                if (l_dblPossBuyQty >= 0)
                {
                    //����t���O��true����
                    l_tradingPowerResult.setResultFlg(true);
                    //����]�͌��ʂ�ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
                //�ȊO�̏ꍇ
                else
                {
                    //����t���O��false����
                    l_tradingPowerResult.setResultFlg(false);
                    //����]�͌��ʂ�ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
            }
            //���|�W�V�����̏ꍇ
            else
            {
                //���|�W�V�����\����
                double l_dblPossSellQty =
                    l_ifoDepCalc.calcPossibleSellQty(l_newOrderSpec.underlyingProductCode);

                //�V�K���\���� >= 0�̏ꍇ
                if (l_dblPossSellQty >= 0)
                {
                    //����t���O��true����
                    l_tradingPowerResult.setResultFlg(true);
                    //����]�͌��ʂ�ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
                //�ȊO�̏ꍇ
                else
                {
                    //����t���O��false����
                    l_tradingPowerResult.setResultFlg(false);
                    //����]�͌��ʂ�ԋp����
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
            }
        }
    }

    /**
     * (validate����]��<�]�͍Čv�Z>)<BR>
     * ���������e���܂񂾗]�͍Čv�Z���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�]�͍Čv�Z>�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_orderTypeEnum - (�������)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)
     * true�̎��A�]�͍Čv�Z���������{����
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�
     * @@return webbroker3.tradingpower.WEB3TPCalcResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerReCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        OrderTypeEnum l_orderTypeEnum,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerReCalc(WEB3GentradeSubAccount, WEB3TPNewOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z����
        WEB3TPCalcCondition l_calcCond =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        /*
         * ����]�͌��ʃI�u�W�F�N�g�𐶐�
         */
        WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            /*
             * ����]�͌��ʃI�u�W�F�N�g�ɒl���Z�b�g
             */
            //����t���O
            l_tradingPowerResult.setResultFlg(true);
            //����\�z
            l_tradingPowerResult.setTradingPower(0);
            //����]�̓G���[���
            l_tradingPowerResult.setTpErrorInfo(null);

            //�]�͍X�V�t���O==true�̏ꍇ
            if(l_blnUpdateFlg == true)
            {
                /*
                 * �]�͍X�V�I�u�W�F�N�g�𐶐�
                 */
                WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId, l_blnMargin, l_calcCond,
                        l_newOrderSpecs);

                /*
                 * �]�͌v�Z����(List)���擾
                 */
                List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();

                //�]�͍X�V�����{
                l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
            }
        }
        //�M�p�ڋq�̏ꍇ
        else
        {
            /*
             * �]�͍X�V�I�u�W�F�N�g�𐶐�
             */
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                    l_lngAccountId, l_blnMargin, l_calcCond,
                    l_newOrderSpecs);

            /*
             * �]�͌v�Z����(List)���擾
             */
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //���Y�]�͏��𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            /*
             * ���������̓�K���`�F�b�N�����{
             */
            //���������e��蒍����ʂ��擾
            OrderTypeEnum l_orderType = l_newOrderSpecs[0].getOrderType();
            //���������e��蒍������ID���擾
            long l_lngProductId = l_newOrderSpecs[0].getProductId();

            //��K���`�F�b�N�G���[�������̔z��
            WEB3TPMarginSecurityInfo[] l_marginSecs = null;

            //������� == (�ی�ˑ�p)�،��U�ւ̏ꍇ
            if(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES
                .equals(l_orderType) == true)
            {
                //��K���`�F�b�N�G���[�������̔z����擾
                l_marginSecs = this.calcMarginSecurity(l_subAccount, l_lngProductId, l_calcMargin,
                        l_tpUpd, l_orderType, -1, null);
            }

            //��K���`�F�b�NOK�ꍇ
            if(l_marginSecs == null)
            {
                /*
                 * ����]�͌��ʃI�u�W�F�N�g�ɒl���Z�b�g
                 */
                //����t���O
                l_tradingPowerResult.setResultFlg(true);
                //����\�z
                l_tradingPowerResult.setTradingPower(0);
                //����]�̓G���[���
                l_tradingPowerResult.setTpErrorInfo(null);

                /*
                 * �]�͍X�V�����{
                 */
                //�]�͍X�V�t���O==true�̏ꍇ
                if (l_blnUpdateFlg == true)
                {
                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            //��K���`�F�b�NNG�ꍇ
            else
            {
                /*
                 * ����]�͌��ʃI�u�W�F�N�g�ɒl���Z�b�g
                 */
                //����t���O
                l_tradingPowerResult.setResultFlg(false);
                //����\�z
                l_tradingPowerResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecs;

                //����]�̓G���[���
                l_tradingPowerResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_tradingPowerResult;
    }

    /**
     * (validate����]��<���̑����i>)<BR>
     * <BR>
     * ���̑����i(���������A�M�p����ȊO)�ɂ����āA <BR>
     * ���������e����荞�ݗa����`�F�b�N�����{����B <BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<���̑����i>�v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_orderTypeEnum - (�������)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * true�̎��A�]�͍Čv�Z���������{����<BR>
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�<BR>
     * @@return WEB3TPCalcResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPowerOther(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        OrderTypeEnum l_orderTypeEnum,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerOther(WEB3GentradeSubAccount, WEB3TPNewOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);

        //�]�͌v�Z����
        WEB3TPCalcCondition l_calcCond =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //����.������� �� �ݓ���� ���@@����.������� �� �����t�@@�̏ꍇ
        if(OrderTypeEnum.RUITO_SELL.equals(l_orderTypeEnum) == false
                && OrderTypeEnum.BOND_SELL.equals(l_orderTypeEnum) == false)
        {
            /*
             * �����~�敪�`�F�b�N
             */
            //�����~���̏ꍇ
            if (l_calcCond.isTradingStop() == true)
            {
                //����]�͌��ʃI�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            //���̑����i���t�]�͒�~�敪 == true ���� ����.������� not in (�o���A�؋����U�ցA�ב֕ۏ؋��U�ցA���������U�ցA
            //�د���ڼޯāACFD�����ւ̐U��)�̏ꍇ
            if (l_calcCond.isOtherTradingStop() == true
                && !(OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
            {
                //����]�͌��ʃI�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.OTHER_TRADING_STOP_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }
            
            //1.5.3.(*3)����t���[
            //�o�������A�؋����ւ̐U�ցA�ב֕ۏ؋��ւ̐U�ցA���������ւ̐U�ց@@�د���ڼޯĂւ̐U�ցA
            //CFD�����ւ̐U�� ���� �a����S�ۏo���]�͒�~���̏ꍇ
            //�i
            //�@@����.������� IN(
            //�@@�@@1001�F�o������,
            //�@@�@@1007�F�U�֒���(�a������犔��؋���),
            //�@@�@@1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j,
            //�@@�@@1013�F�O�������U�֒����i�a�������O�����������j
            //    1020�F�U�֒����i�a�������I���b�N�X�N���W�b�g�j
            //    1021�FCFD�U�֒����i�a�������CFD�����j
            //�@@&& 
            //�@@�]�͌v�Z����.is�a����S�ۏo����~�敪==true
            //�j
            if (l_calcCond.isCashDepositStopDiv() == true                
                && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                ||OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                ||OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
            {
                //����]�͌��ʃI�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
                
                //[����]�͌��ʂ̐ݒ�l]
                //����t���O�Ffalse
                l_tpResult.setResultFlg(false);

                //����\�z�F0
                l_tpResult.setTradingPower(0);
                
                // ����]�̓G���[���𐶐�
                 
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                
                //����G���[���F�i���j�Q��
                //�i���j[����]�̓G���[���̐ݒ�l]
                //�E����]�̓G���[�敪�F����]�̓G���[�敪Def.�a����S�ۏo���]�͒�~�G���[
                l_tpErrorInfo.tradinPowerErrorDiv = 
                    WEB3TPTradingPowerErrorDivDef.CASH_DEPOSIT_PAYMENT_STOP_ERROR;
               
                //�E�a����s���z�F0
                l_tpErrorInfo.lackAccountBalance = 0;   
                
                //�E�������ϔ��t�\�z�F0
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                
                //�E�������ϔ��t�\���ʁF0
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                
                //�E��K���������ꗗ[]�Fnull
                l_tpErrorInfo.marginSecInfo = null;
            
                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //1.5.3.1.(*)����]�͌��ʂ�ԋp����B 
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            //�o���]�͒�~�� ���� ����.�������in (�o���A�؋����U�ցA�ב֕ۏ؋��U�ցA���������U�ցA
            //�د���ڼޯ�ACFD�����ւ̐U���)�̏ꍇ
            if (l_calcCond.isPaymentStop() == true
                && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                ||OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                ||OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
            {
                //����]�͌��ʃI�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.PAYMENT_STOP_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //����]�͌��ʂ�ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            //(*5)����t���[
            //�،��S�ۃ��[�������J�ݍόڋq�i�o���S�������R�[�h�L�j ����
            //�o�������A�؋����ւ̐U�ցA�ב֕ۏ؋��ւ̐U�ցA���������ւ̐U�ցA
            //CFD�����ւ̐U�� ���� �،��S�ۃ��[�����z���b�N���̏ꍇ
            //
            //�i
            //  �]�͌v�Z����.is�I���b�N�X_�S�ۃ��[�������J�݋敪==true
            //  &&
            //�@@����.������� IN(
            //�@@�@@1001�F�o������,
            //�@@�@@1007�F�U�֒���(�a������犔��؋���),
            //�@@�@@1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j,
            //�@@�@@1013�F�O�������U�֒����i�a�������O�����������j
            //    1021�FCFD�U�֒����i�a�������CFD�����j
            //�@@&&
            //�@@�]�͌v�Z����.get�I���b�N�X_�S�ۃ��[�����z���b�N==1
            //�j
            if (l_calcCond.isOrixSecuredLoanAccOpenDiv() == true
            	&& (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum))
                && WEB3TPOrixSecuredLoanLockDef.ORIX_SECURED_LOAN.equals(l_calcCond.getOrixSecuredLoanLockFlag()))
            {
                //����]�͌��ʃI�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //[����]�͌��ʂ̐ݒ�l]
                //����t���O�Ffalse
                l_tpResult.setResultFlg(false);

                //����\�z�F0
                l_tpResult.setTradingPower(0);

                // ����]�̓G���[���𐶐�

                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();

                //����G���[���F�i���j�Q��
                //�i���j[����]�̓G���[���̐ݒ�l]
                //�E����]�̓G���[�敪�F����]�̓G���[�敪Def.�،��S�ۃ��[�����z���b�N�G���[
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.SECURITY_DEPOSIT_LOAN_LOCK_ERROR;

                //�E�a����s���z�F0
                l_tpErrorInfo.lackAccountBalance = 0;

                //�E�������ϔ��t�\�z�F0
                l_tpErrorInfo.buyOrderPossibleAmount = 0;

                //�E�������ϔ��t�\���ʁF0
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;

                //�E��K���������ꗗ[]�Fnull
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //����]�͌��ʂ�ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }
        }

        // ������擾
        String l_strDealType = l_newOrderSpecs[0].getDealType();
        
        /*
         * �]�͌v�Z�����I�u�W�F�N�g�ɁA���������e[0].��n���ɑΉ�����]�͌v�Z���<���̑�>���Z�b�g
         */
        //��n��
        Date l_datDeliDate = l_newOrderSpecs[0].getDeliveryDate();
        
        //����W����̏ꍇ
        //����.������� =401�F���������� && get���() ==  35�F��W���
        if (OrderTypeEnum.BOND_BUY.equals(l_orderTypeEnum) && 
            WEB3DealTypeDef.RECRUIT_TRADING.equals(l_strDealType))
        {
            // "��n��" = get������()
            l_datDeliDate = l_newOrderSpecs[0].getPaymentDate(); 
        }
        
        //��n��<int>
        int l_intDeliDate = 0;
        
        //���M���t�A���M��W�A���M�抷�̏ꍇ
        if(OrderTypeEnum.MF_BUY.equals(l_orderTypeEnum)
           || OrderTypeEnum.MF_RECRUIT.equals(l_orderTypeEnum)
           || OrderTypeEnum.MF_SWITCHING.equals(l_orderTypeEnum))
        {
            //���M��W�̏ꍇ�A��n���ł͂Ȃ����������̗p
            if(OrderTypeEnum.MF_RECRUIT.equals(l_orderTypeEnum))
            {
                //��n�����������ɂ���
                l_datDeliDate = l_newOrderSpecs[0].getPaymentDate();                
            }
            
            //�����M�����t�\�z�K�p���̐ݒ�l���擾
            String l_strMFBuyApplyDate = l_calcCond.getInstBranCalcCondition(WEB3TPCalcCondition.MFBUY_APPLY_DATE);
            
            //�K�p�����������ȍ~�̏ꍇ
            if(WEB3TPMutualFundBuyApplyDateDef.FROM_BIZ_DATE_UNTIL_T5.equals(l_strMFBuyApplyDate))
            {
                //��n���������ɂ���
                l_datDeliDate = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);
            }
        }
        
        //��n��������(T+0)�ȑO�������ꍇ
        if (WEB3DateUtility
            .compareToDay(l_datDeliDate, l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0))
            < 0)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //��n����T+5�ȍ~�������ꍇ
        else if (
            WEB3DateUtility.compareToDay(
                l_datDeliDate,
                l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5))
                > 0)
        {
            //��n��<int> = T+5
            l_intDeliDate = WEB3TPSpecifiedPointDef.T_5;
        }
        //�ȊO(��n�����AT+0�`T+5�̊�)�̏ꍇ
        else
        {
            //��n��<int> = ��n���ɑΉ�����w������擾
            l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliDate);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͍X�V�I�u�W�F�N�g�𐶐�
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);

        //�c�ƓX����]�̓`�F�b�N���{�敪���A�擾����B
        //OrderTypeEnum = ����.�������
        boolean l_blnBrachTradingPowerCheckDiv = l_calcCond.isSalesOfficeTPCheckDiv(l_orderTypeEnum);
        
        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * �]�͌v�Z����(List)���擾
             */
            List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_updResults, l_calcCond, l_newOrderSpecs[0].getEstimatedPrice());

            //�]�͌v�Z���ʃI�u�W�F�N�g���擾
            WEB3TPCalcResult l_calcResult = l_calcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intDeliDate);

            //�]�͌v�Z����.����\�z >= 0 || is�c�ƓX����]�̓`�F�b�N���{�敪() == true�̏ꍇ
            if (l_calcResult.tradingPower >= 0 || l_blnBrachTradingPowerCheckDiv)
            {
                //true���Z�b�g
                l_tpResult.setResultFlg(true);
                //(���񒍕���)����\�z���Z�b�g
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(null);

                //�]�͍X�V�t���O==true�̏ꍇ
                if (l_blnUpdateFlg == true)
                {
                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
                }
            }
            //�]�͌v�Z����.����\�z < 0�̏ꍇ
            else
            {
                //false���Z�b�g
                l_tpResult.setResultFlg(false);
                //(���񒍕���)����\�z(=0)���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
        //�M�p�ڋq�̏ꍇ
        else
        {
            /*
             * �]�͌v�Z����(List)���擾
             */
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //���Y�]�͏��I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            //�]�͌v�Z���ʃI�u�W�F�N�g���擾
            WEB3TPCalcResult l_calcResult = l_calcMargin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intDeliDate);

            /*
             * ���������̓�K���`�F�b�N�����{
             */
            //���������e��蒍����ʂ��擾
            OrderTypeEnum l_orderType = l_newOrderSpecs[0].getOrderType();

            //��K���`�F�b�N�G���[�������̔z��
            WEB3TPMarginSecurityInfo[] l_marginSecs = null;

            //����.������� �� �ݓ���� ���� ����.������� �� �����t �̏ꍇ
            if(OrderTypeEnum.RUITO_SELL.equals(l_orderTypeEnum) == false
                    && OrderTypeEnum.BOND_SELL.equals(l_orderTypeEnum) == false)
            {
                //��K���`�F�b�N�G���[�������̔z����擾
                l_marginSecs = this.calcMarginSecurity(l_subAccount, -1, l_calcMargin, l_tpUpd,
                        l_orderType, -1, null);
            }

            //(*)����t���[
            //���L�����𖞂����ꍇ�A�ȉ��̏��������{
            //�@@�o���ɔ��������ۏ؋��U�֎��{�敪�@@=�@@�P�FEXECUTE
            //�A����.������� = 1001:�o��
            //�B����.���������e.get������() = T+0
            //�C����.���������e.get��n��() = T+1
            //�D����.���������e.get�o���\���敪() = null
            //�E���������Ώۖ����S����( T + 0 ) > 0
            //���o���ɔ��������ۏ؋��U�֎��{�敪 =
            //���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z����(
            //     :String = "cashout.today.deposit.transfer.div")
            String l_strInstBranCalcCondition = l_calcMargin.getCalcCondition().getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV);
            Date l_datBizDate0 = l_calcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
            Date l_datBizDate1 = l_calcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_1);
            double l_dblTodayDepFundRestraint0 = l_calcMargin.getCalcResultDetailMargin().getTodayDepFundRestraint0();           

            double l_dblTradingPower = 0;
            if (WEB3TPCashoutTodayDepositTransferDivDef.EXECUTE.equals(l_strInstBranCalcCondition)
                && OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                && WEB3DateUtility.compareToDay(l_newOrderSpecs[0].getOrderBizDate(), l_datBizDate0) == 0
                && WEB3DateUtility.compareToDay(l_newOrderSpecs[0].getDeliveryDate(), l_datBizDate1) == 0
                && l_newOrderSpecs[0].getPaymentApplicationDiv() == null
                && l_dblTodayDepFundRestraint0 > 0)
            {
                //�o���\�z���v�Z����B
                //�o���\�z�@@=�@@�ۏ؋����o�]��(�s�{�O)
                //���ۏ؋����o�]��(�s�{�O) = calc�ۏ؋����o�]��(int)�̖߂�l
                l_dblTradingPower =
                    l_calcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_0);
            }

            /*
             * ����]�͌��ʃI�u�W�F�N�g�𐶐�
             */        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //��K���`�F�b�N�G���[�����݂��Ȃ��@@���@@ (����\�z >=0 || is�c�ƓX����]�̓`�F�b�N���{�敪() == true�ꍇ)
            //&& �o���\�z >= 0
            if (l_marginSecs == null && (l_calcResult.tradingPower >= 0 || l_blnBrachTradingPowerCheckDiv)
                && l_dblTradingPower >= 0)
            {
                //true���Z�b�g
                l_tpResult.setResultFlg(true);
                //(���񒍕���)����\�z���Z�b�g
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(null);

                /*
                 * �]�͍X�V�����{
                 */
                //�]�͍X�V�t���O==true�̏ꍇ
                if (l_blnUpdateFlg == true)
                {
                    //�]�͍X�V�����{
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            //�ȊO
            else
            {
                //false���Z�b�g
                l_tpResult.setResultFlg(false);
                //(���񒍕���)����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                
                //�a������s�����Ă���ꍇ
                if(l_calcResult.tradingPower < 0 || l_dblTradingPower < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                }
                //����ȊO(��K���G���[�̏ꍇ)
                else
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                }
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecs;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
            
            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
    }

    /**
     * �icalc��K���j<BR>
     * ��K���`�F�b�N�����{����B <BR>
     * ��K���������Ƃɓ�K�������Z�o��������K�����𒴂��Ă��邩�ǂ����`�F�b�N����B <BR>
     * ������K�����𒴂��Ă�����K���`�F�b�N�G���[�������̔z����쐬���ԋp����B <BR>
     * 
     * �V�[�P���X�}�u(����]�̓T�[�r�X)calc��K���v�Q��<BR>
     * 
     * @@param l_subAccount -(�⏕����)
     * @@param l_lngProductId -(����ID)
     * @@param l_tpCalcMargin -(���Y�]�͏��<�M�p�ڋq>)
     * @@param l_tpUpd -(�]�͍X�V)
     * @@param l_orderTypeEnum - (�������)
     * @@param l_lngMarketId - (�s��ID)
     * @@param l_datDeliDate - (������)
     * @@return WEB3TPMarginSecurityInfo[]
     */
    protected WEB3TPMarginSecurityInfo[] calcMarginSecurity(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngProductId,
        WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
        WEB3TPTradingPowerUpd l_tpUpd,
        OrderTypeEnum l_orderTypeEnum,
        long l_lngMarketId,
        Date l_datBizDate)
    {
        final String STR_METHOD_NAME =
            "calcMarginSecurity(WEB3GentradeSubAccount, long, WEB3TPTradingPowerCalcMargin, WEB3TPTradingPowerUpd, OrderTypeEnum, long, Date)";
        log.entering(STR_METHOD_NAME);

        //����ID
        long l_lngCheckProductId;
        //��Е��X�ʗ]�͌v�Z����."��K���`�F�b�N���@@"
        String l_strDoublepositionCheck = null;
        
        //����.������� == null �̏ꍇ
        if(l_orderTypeEnum == null)
        {
            //����ID = ����.����ID
            l_lngCheckProductId = l_lngProductId;
        }
        //�ȊO(����.������� != null)�̏ꍇ
        else
        {
            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond = l_tpCalcMargin.getCalcCondition();

            /*
             * ��Е��X�ʗ]�͌v�Z�������擾
             */
            //����.������� == �����������̏ꍇ
            if(OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum) == true)
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.EQUITYBUY_DOUBLEPOSITION_CHECK);
            }
            //����.������� == �M�p�V�K���������̏ꍇ
            else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum) == true)
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINOPENLONG_DOUBLEPOSITION_CHECK);
            }
            //����.������� == �M�p���������̏ꍇ
            else if(OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderTypeEnum) == true)
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINSWAPLONG_DOUBLEPOSITION_CHECK);
            }
            //����.������� == �،��U�֒����̏ꍇ
            else if(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES
                .equals(l_orderTypeEnum) == true)
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINSUBSECURITY_DOUBLEPOSITION_CHECK);
            }
            //����.������� == �ȊO�̏ꍇ
            else
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.OTHERBUY_DOUBLEPOSITION_CHECK);
            }

        
            /*
             * ����ID��ݒ肷��B
             */
            //"��K���`�F�b�N���@@" = 1:ORDER_PRODUCT 
            //�܂��́@@
            //"��K���`�F�b�N���@@" = 3:LISTMARKET_ORDER_PRODUCT�̏ꍇ
            if(WEB3TPDoublepositionCheckDef.ORDER_PRODUCT.equals(l_strDoublepositionCheck) == true
                    || WEB3TPDoublepositionCheckDef.LISTMARKET_ORDER_PRODUCT
                            .equals(l_strDoublepositionCheck) == true)
            {
                // ����ID = ����.����ID
                l_lngCheckProductId = l_lngProductId;
            }
            // "��K���`�F�b�N���@@" = 2:ALL_PRODUCT �̏ꍇ
            else if(WEB3TPDoublepositionCheckDef.ALL_PRODUCT
                .equals(l_strDoublepositionCheck) == true)
            {
                //����ID = -1
                l_lngCheckProductId = -1;
            }
            //�ȊO �̏ꍇ
            else
            {
                //null��ԋp
                return null;
            }
        }
        
        /*
         * ��K�������擾����B
         */
        //�⏕����������X���擾����
        BranchRow l_branchRow = (BranchRow) l_subAccount.getWeb3GenBranch().getDataSourceObject();

        //������K����(�S�̐ݒ�)
        double l_dbLimitRate = l_branchRow.getMarginSecCheckRate();
        //���XID
        long l_lngBranchId = l_branchRow.getBranchId();
        
       
        //��K���ă`�F�b�N���@@==3:LISTMARKET_ORDER_PRODUCT����
        //����ID �� -1 ���� ����.�s��ID �� -1 ���� ����.������ !=null
        //�̏ꍇ
        if(WEB3TPDoublepositionCheckDef.LISTMARKET_ORDER_PRODUCT.equals(l_strDoublepositionCheck) == true
                && l_lngCheckProductId != -1 && l_lngMarketId != -1 && l_datBizDate != null)
        {
            //��ЃR�[�h
            String l_strInstCode = l_subAccount.getInstitution().getInstitutionCode();
            //������
            String l_strBizdate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                    l_datBizDate);

            //���敪
            String l_strListType = null;
            //�V���敪
            String l_strNewListType = null;
            //�X�����J�敪
            String l_strOpenOtcDiv = null;

            //������������}�X�^Row���擾����B
            EqtypeTradedProductRow l_eqTradedProductRow = WEB3TPPersistentDataManager.getInstance()
                    .getEqtypeTradedProduct(l_strInstCode, l_lngProductId, l_lngMarketId,
                            l_strBizdate);

            //������������}�X�^Row���擾�ł����ꍇ
            if(l_eqTradedProductRow != null)
            {
                l_strListType = l_eqTradedProductRow.getListType();
                l_strNewListType = l_eqTradedProductRow.getNewListType();
                l_strOpenOtcDiv = l_eqTradedProductRow.getOpenOtcDiv();
            }
            //������������}�X�^Row���擾�ł��Ȃ������ꍇ
            else
            {
                //�����������UpdqRow���擾
                EqtypeTradedProductUpdqRow l_eqTradedProductUpdqRow = WEB3TPPersistentDataManager
                        .getInstance().getEqtypeTradedProductUpdq(l_lngProductId, l_lngMarketId,
                                l_strBizdate);

                //�����������UpdqRow���擾�ł����ꍇ
                if(l_eqTradedProductUpdqRow != null)
                {
                    l_strListType = l_eqTradedProductUpdqRow.getListType();
                    l_strNewListType = l_eqTradedProductUpdqRow.getNewListType();
                    l_strOpenOtcDiv = l_eqTradedProductUpdqRow.getOpenOtcDiv();
                }
                //�����������UpdqRow���擾�ł��Ȃ������ꍇ
                else
                {
                    StringBuffer l_strErrorBuf = new StringBuffer("������������}�X�^�[�Y�����R�[�h����:");
                    l_strErrorBuf.append("product_id=");
                    l_strErrorBuf.append(l_lngProductId);
                    l_strErrorBuf.append(" market_id=");
                    l_strErrorBuf.append(l_lngMarketId);
                    l_strErrorBuf.append(" valid_until_biz_date=");
                    l_strErrorBuf.append(l_strBizdate);
                    log.error(l_strErrorBuf.toString());
                }
            }

            try
            {
                //(���X�s����敪��)�戵�����N���X�̃C���X�^���X�𐶐�����B
                WEB3GentradeBranchListmarketDealtCond l_dealtCond = new WEB3GentradeBranchListmarketDealtCond(
                                                                                                              l_lngBranchId,
                                                                                                              l_lngMarketId,
                                                                                                              l_strListType,
                                                                                                              l_strNewListType,
                                                                                                              l_strOpenOtcDiv);

                //���X�E�s��E���敪�ʂ̐�����K�������擾����B
                Double l_MarginSecCheckRate = l_dealtCond.getMarginSecCheckRate();

                if(l_MarginSecCheckRate != null)
                {
                    l_dbLimitRate = l_MarginSecCheckRate.doubleValue();
                }
            }
            catch(WEB3SystemLayerException e)
            {
                StringBuffer l_strErrorBuf = new StringBuffer("(���X�s����敪��)�戵�����e�[�u���@@�Y�����R�[�h����:");
                l_strErrorBuf.append("branch_id=");
                l_strErrorBuf.append(l_lngBranchId);
                l_strErrorBuf.append(" market_id=");
                l_strErrorBuf.append(l_lngMarketId);
                l_strErrorBuf.append(" list_type=");
                l_strErrorBuf.append(l_strListType);
                l_strErrorBuf.append(" new_list_type=");
                l_strErrorBuf.append(l_strNewListType);
                l_strErrorBuf.append(" open_otc_div=");
                l_strErrorBuf.append(l_strOpenOtcDiv);
                log.error(l_strErrorBuf.toString());
            }
        }

        
        // �����ۏ؋�(T+5)���擾
        double l_dbPaidMarginDeposit =
            l_tpCalcMargin.calcPaidMarginDeposit(WEB3TPSpecifiedPointDef.T_5);
        BigDecimal l_bdPaidMarginDeposit = new BigDecimal(Double.toString(l_dbPaidMarginDeposit));

        //�����ۏ؋�(T+5) <= 0 �ꍇ
        if(l_dbPaidMarginDeposit <= 0) 
        {
            //null��ԋp
            return null;
        }

        //�]�͍X�V�T�[�r�X��茚�ʖ����ꗗ���擾����
        long[] l_lngContractProductIds = l_tpUpd.getContractProducts(
                WEB3TPSpecifiedPointDef.T_5, ContractTypeEnum.LONG);
        if(l_lngContractProductIds == null)
        {
            //null��ԋp
            return null;
        }
        
        //����ID��-1�łȂ��ꍇ
        if (l_lngCheckProductId != -1)
        {
            /*
             * ���ʖ����ꗗ������.����ID������
             */
            boolean l_flg = false;

            //���ʖ����ꗗ�̗v�f����A���������s����
            int l_intCnt = l_lngContractProductIds.length;
            for (int index = 0; index < l_intCnt; index++)
            {
                if(l_lngContractProductIds[index] == l_lngCheckProductId)
                {
                    l_flg = true;
                }
            }

            //���ʖ����ꗗ������.����ID�������ł����ꍇ
            if(l_flg == true)
            {
                //�w�肳�ꂽ����ID�݂̂��K������ID�̔z��ɃZ�b�g����
                l_lngContractProductIds = new long[1];
                l_lngContractProductIds[0] = l_lngCheckProductId;
            }
            //�ȊO�̏ꍇ
            else
            {
                //null��ԋp����B
                return null;                
            }
        }
        
        /*
         * ��K���`�F�b�N�����{
         */
        //��K���`�F�b�N�G���[�������ꗗ
        ArrayList l_errorMargins = new ArrayList();

        //��K�����̏���l
        BigDecimal l_bdLimitRate = new BigDecimal(Double.toString(l_dbLimitRate));
        //��K����L��
        BigDecimal l_bdMarginRate = null;
        //�������Ƒ�p�،��]���z
        BigDecimal l_bdValuationPrice = null;

        //��K������ID�̔z��̗v�f����A���������s����
        int l_marginProductIdsCnt = l_lngContractProductIds.length;
        for (int i = 0; i < l_marginProductIdsCnt; i++)
        {
            //�������Ƒ�p�،��]���z���擾����
            double l_dbValuationPrice =
                l_tpUpd.getSubstituteValuationPriceParProduct(
                        l_lngContractProductIds[i],
                    WEB3TPSpecifiedPointDef.T_5);
            l_bdValuationPrice = new BigDecimal(Double.toString(l_dbValuationPrice));

            /*
             * ��K����L�������v�Z����
             * 
             * ��K����L�� = (������)��p�،��]���z(T+5) / �����ۏ؋�(T+5) * 100
             * ��������Q�ʂ܂ŋ��߂�B
             */
            l_bdMarginRate =
                l_bdValuationPrice.multiply(new BigDecimal("100")).divide(
                    l_bdPaidMarginDeposit,
                    2,
                    BigDecimal.ROUND_HALF_UP);

            //�i��K�����̏���l�@@���@@��K����L���j�̏ꍇ
            if (l_bdLimitRate.compareTo(l_bdMarginRate) < 0)
            {
                /*
                 * ��K���`�F�b�N�G���[�������I�u�W�F�N�g�𐶐�����
                 */
                WEB3TPMarginSecurityInfo l_marginSecInfo = new WEB3TPMarginSecurityInfo();
                l_marginSecInfo.marginSecProductId = l_lngContractProductIds[i];
                l_marginSecInfo.marginSecRate = l_bdMarginRate.doubleValue();

                //��K���`�F�b�N�G���[�������ꗗ�ɒǉ�����
                l_errorMargins.add(l_marginSecInfo);
            }
        }

        //��K���`�F�b�N�G���[���������݂��Ȃ���
        if (l_errorMargins.isEmpty() == true)
        {
            //null��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            //���X�g��z��ɕϊ�����
            WEB3TPMarginSecurityInfo[] l_errorMarginSec =
                new WEB3TPMarginSecurityInfo[l_errorMargins.size()];
            l_errorMarginSec =
                (WEB3TPMarginSecurityInfo[])l_errorMargins.toArray(l_errorMarginSec);

            //��K���`�F�b�N�G���[�������̔z���ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_errorMarginSec;
        }
    }

    /**
     * �icalc�����ڋq����c��<�����ڋq><BR>
     * ����.�⏕�����́A�����ڋq����c��(*)�v��ԋp����B<BR>
     * (*)�����_�̊m��ڋq����c���ɁA��������������������l<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount -(�⏕����)
     * @@param l_tpCalcEquity -(���Y�]�͏��<�����ڋq>)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    protected double calcRealBalanceEquity(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPTradingPowerCalcEquity l_tpCalcEquity,
        Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcRealBalanceEquity()";

        /*
         * �w������擾����B
         */
        //�w������擾����B
        int l_intSpecifiedPoint;

        //��n��������(T+0)�ȑO�������ꍇ
        if (WEB3DateUtility
            .compareToDay(l_datDeliveryDate, l_tpCalcEquity.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0))
            < 0)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //��n����T+5�ȍ~�������ꍇ
        else if (
            WEB3DateUtility.compareToDay(
                l_datDeliveryDate,
        l_tpCalcEquity.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5))
                > 0)
        {
            //�w���=T+5���Z�b�g
            l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_5;
        }
        //�ȊO(��n�����AT+0�`T+5�̊�)�̏ꍇ
        else
        {
            //��n���ɑΉ�����w������擾
            l_intSpecifiedPoint = l_tpCalcEquity.getCalcCondition().calcSpecifiedPoint(l_datDeliveryDate);
        }

        //�����a����c��
        double l_dblRealAccBal =
            l_tpCalcEquity.getAccountBalance(l_intSpecifiedPoint)
                - l_tpCalcEquity.getTodayExecutedAmount(l_intSpecifiedPoint)
                - l_tpCalcEquity.getTodayUnexecutedAmount(l_intSpecifiedPoint)
                - l_tpCalcEquity.getOtherRestraint(l_intSpecifiedPoint);

        /*
         * �����_�̊m��MRF�c���A�ڋq����c������MRF�c���ւ̐U�֊z���擾
         */
        //����ID
        long l_lngAccountId = l_subAccount.getAccountId();
        //�⏕����ID(�a���)
        long l_lngSubAccountId;
        try
        {
            l_lngSubAccountId =
                l_subAccount
                    .getMainAccount()
                    .getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT)
                    .getSubAccountId();
        }
        catch (NotFoundException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����(T+0)
        Date l_datCurDate =
            l_tpCalcEquity.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        String l_strCurDate = WEB3DateUtility.formatDate(l_datCurDate, "yyyyMMdd");

        //�ڋq����c��(�}�X�^���)�e�[�u����������
        String l_strTpWhere = "account_id=? and sub_account_id=?";
        Object[] l_objTpBindVars = { new Long(l_lngAccountId), new Long(l_lngSubAccountId)};

        log.debug(
            "Finding TpCashBalanceParams where="
                + l_strTpWhere
                + ", bindVars="
                + objectsToString(l_objTpBindVars));

        //�ݓ������P�ʃe�[�u���̌�������
        String l_strRuitoWhere =
            "account_id=? and sub_account_id=? and order_type=? and biz_date>=? and delivery_date<=? and order_status not in (?,?)";
        Object[] l_objRuitoBindVars =
            {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderTypeEnum.RUITO_SELL,
                l_strCurDate,
                l_datDeliveryDate,
                OrderStatusEnum.NOT_ORDERED,
                OrderStatusEnum.CANCELLED };

        log.debug(
            "Finding RuitoOrderUnitParams where="
                + l_strRuitoWhere
                + ", bindVars="
                + objectsToString(l_objRuitoBindVars));

        BatchedQuery[] l_queries =
            new BatchedQuery[] {
                BatchedQuery.createFindAllQuery(
                    TpCashBalanceParams.TYPE,
                    l_strTpWhere,
                    l_objTpBindVars),
                BatchedQuery.createFindAllQuery(
                    RuitoOrderUnitParams.TYPE,
                    l_strRuitoWhere,
                    l_objRuitoBindVars)};

        //MRF�c��
        double l_dblMrfBal = 0;
        //�U�֊z
        double l_dblQuantity = 0;

        try
        {
            Object[] l_results = Processors.getDefaultProcessor().doQueries(l_queries);
            List l_tpList = (List)l_results[0];
            List l_ruitoList = (List)l_results[1];

            TpCashBalanceParams l_tpParmas = null;
            RuitoOrderUnitParams l_ruitoParams = null;

            /*
             * �ڋq����c��(�}�X�^)���e�[�u���̌������ʂ��MRF�c�����擾����
             */
            if (l_tpList != null && l_tpList.size() == 1)
            {
                l_tpParmas = (TpCashBalanceParams)l_tpList.get(0);
                l_dblMrfBal = l_tpParmas.getMrfBalance();
            }
            else
            {
                //Y00127:�a������R�[�h�����݂��Ȃ������ꍇ�̑Ή�
                l_dblMrfBal = 0;
            }

            /*
             * �ݓ������P�ʃe�[�u���̌������ʂ��U�֊z���W�v
             */
            if (l_ruitoList != null && l_ruitoList.size() > 0)
            {
                for (int index = 0; index < l_ruitoList.size(); index++)
                {
                    l_ruitoParams = (RuitoOrderUnitParams)l_ruitoList.get(index);
                    l_dblQuantity = l_dblQuantity + l_ruitoParams.getQuantity();
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                de.getMessage(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de);
        }

        //�����ڋq����c�� = �����a����c���@@�| (MRF�c�� �| �U�֊z)
        double l_dblRealBal = l_dblRealAccBal - l_dblMrfBal + l_dblQuantity;

        //�����ڋq����c����ԋp����B
        return l_dblRealBal;
    }

    /**
     * �icalc�����ڋq����c��<�M�p�ڋq>�j<BR>
     * ����.�⏕�����́A�����ڋq����c��(*)�v��ԋp����B<BR>
     * (*)�����_�̊m��ڋq����c���ɁA��������������������l<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB <BR>
     * <BR>
     * @@param l_subAccount -(�⏕����)
     * @@param l_tpCalcMargin -(���Y�]�͏��<�M�p�ڋq>)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    protected double calcRealBalanceMargin(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
        Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcRealBalanceMargin()";

        /*
         * �w������擾����B
         */
        //�w������擾����B
        int l_intSpecifiedPoint;

        //��n��������(T+0)�ȑO�������ꍇ
        if (WEB3DateUtility
            .compareToDay(l_datDeliveryDate, l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0))
            < 0)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //��n����T+5�ȍ~�������ꍇ
        else if (
            WEB3DateUtility.compareToDay(
                l_datDeliveryDate,
        l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5))
                > 0)
        {
            //�w���=T+5���Z�b�g
            l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_5;
        }
        //�ȊO(��n�����AT+0�`T+5�̊�)�̏ꍇ
        else
        {
            //��n���ɑΉ�����w������擾
            l_intSpecifiedPoint = l_tpCalcMargin.getCalcCondition().calcSpecifiedPoint(l_datDeliveryDate);
        }

        //�����a����c��
        double l_dblRealAccBal =
            l_tpCalcMargin.getAccountBalance(l_intSpecifiedPoint)
                - l_tpCalcMargin.getTodayExecutedAmount(l_intSpecifiedPoint)
                - l_tpCalcMargin.getTodayUnexecutedAmount(l_intSpecifiedPoint)
                - l_tpCalcMargin.getOtherRestraint(l_intSpecifiedPoint);

        /*
         * �����_�̊m��M�p�ۏ؋��c���A�ڋq����c������M�p�ۏ؋��c���ւ̐U�֊z���擾
         */
        //����ID
        long l_lngAccountId = l_subAccount.getAccountId();
        //�⏕����ID(�M�p�ۏ؋�)
        long l_lngSubAccountId;
        try
        {
            l_lngSubAccountId =
                l_subAccount
                    .getMainAccount()
                    .getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT)
                    .getSubAccountId();
        }
        catch (NotFoundException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        //����(T+0)
        Date l_datCurDate =
            l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        String l_strCurDate = WEB3DateUtility.formatDate(l_datCurDate, "yyyyMMdd");

        //�ڋq����c��(�}�X�^���)�e�[�u����������
        String l_strTpWhere = "account_id=? and sub_account_id=?";
        Object[] l_objTpBindVars = { new Long(l_lngAccountId), new Long(l_lngSubAccountId)};

        log.debug(
            "Finding TpCashBalanceParams where="
                + l_strTpWhere
                + ", bindVars="
                + objectsToString(l_objTpBindVars));

        //���o�������P�ʃe�[�u���̌�������
        String l_strAioWhere =
            "account_id=? and sub_account_id=? and order_type in (?,?) and biz_date>=? and delivery_date<=? and order_status not in (?,?)";
        Object[] l_objAioBindVars =
            {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_strCurDate,
                l_datDeliveryDate,
                OrderStatusEnum.NOT_ORDERED,
                OrderStatusEnum.CANCELLED };

        log.debug(
            "Finding AioOrderUnitParams where="
                + l_strAioWhere
                + ", bindVars="
                + objectsToString(l_objAioBindVars));

        BatchedQuery[] l_queries =
            new BatchedQuery[] {
                BatchedQuery.createFindAllQuery(
                    TpCashBalanceParams.TYPE,
                    l_strTpWhere,
                    l_objTpBindVars),
                BatchedQuery.createFindAllQuery(
                    AioOrderUnitParams.TYPE,
                    l_strAioWhere,
                    l_objAioBindVars)};

        //�ۏ؋��c��
        double l_dblCashBal = 0;
        //�U�֊z
        double l_dblQuantity = 0;

        try
        {
            Object[] l_results = Processors.getDefaultProcessor().doQueries(l_queries);
            List l_tpList = (List)l_results[0];
            List l_aioList = (List)l_results[1];

            TpCashBalanceParams l_tpParmas = null;
            AioOrderUnitParams l_aioParams = null;

            /*
             * �ڋq����c��(�}�X�^)���e�[�u���̌������ʂ��ۏ؋��c�����擾����
             */
            if (l_tpList != null && l_tpList.size() == 1)
            {
                l_tpParmas = (TpCashBalanceParams)l_tpList.get(0);

                //��n����T+0�̏ꍇ
                if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance0();
                }
                //��n����T+1�̏ꍇ
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance1();
                }
                //��n����T+2�̏ꍇ
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_2)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance2();
                }
                //��n����T+3�̏ꍇ
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_3)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance3();
                }
                //��n����T+4�̏ꍇ
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_4)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance4();
                }
                //��n����T+5�̏ꍇ
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_5)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance5();
                }
            }
            else
            {
                //Y00127:�ۏ؋��c�����R�[�h�����݂��Ȃ������ꍇ�̑Ή�
                l_dblCashBal = 0;
            }

            /*
             * ���o�������P�ʃe�[�u���̌������ʂ�蓖���U�֊z���W�v
             */
            if (l_aioList != null && l_aioList.size() > 0)
            {
                for (int index = 0; index < l_aioList.size(); index++)
                {
                    l_aioParams = (AioOrderUnitParams)l_aioList.get(index);

                    //�����P�ʃe�[�u��.������� = �g1005:�a�������M�p�ۏ؋��h�̏ꍇ
                    if (l_aioParams.getOrderType().intValue()
                        == OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.intValue())
                    {
                        //�U�֊z = �U�֊z + �����P�ʃe�[�u��.��������
                        l_dblQuantity = l_dblQuantity + l_aioParams.getQuantity();
                    }
                    //�����P�ʃe�[�u��.������� = �g1006:�M�p�ۏ؋�����a����h�̏ꍇ
                    else if (
                        l_aioParams.getOrderType().intValue()
                            == OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.intValue())
                    {
                        //�U�֊z = �U�֊z - �����P�ʃe�[�u��.��������
                        l_dblQuantity = l_dblQuantity - l_aioParams.getQuantity();
                    }
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //�����ڋq����c�� = �����a����c�� �| (�m��M�p�ۏ؋��c�� + �U�֊z)
        double l_dblRealBal = l_dblRealAccBal - l_dblCashBal - l_dblQuantity;

        //�����ڋq����c����ԋp����B
        return l_dblRealBal;
    }

    /**
     * �iget�⏕�����j<BR>
     * <BR>
     * �P�j�ڋq�I�u�W�F�N�g�𐶐�����B <BR>
     * �@@[����]<BR>
     * �@@�@@����ID�F����.����ID<BR>
     * <BR>
     * �Q�j�ڋq.is�M�p�����J��()�̔���<BR>
     * �@@[����]<BR>
     *   �@@�ٍϋ敪�F�h�w��Ȃ��h<BR>
     * <BR>
     * �@@�����J�݂̏ꍇ(�ڋq.is�M�p�����J��()==false)<BR>
     * �@@�@@�@@�⏕����<�����������(�a���)>�I�u�W�F�N�g���擾���A���^�[������B<BR>
     * <BR>
     * �@@���J�݂̏ꍇ(�ڋq.is�M�p�����J��()==true)<BR>
     * �@@�@@�@@�⏕����<�����M�p�������(�ۏ؋�)>�I�u�W�F�N�g���擾���A���^�[������B<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)
     * @@return webbroker3.gentrade.WEB3GentradeSubAccount
     */
    protected WEB3GentradeSubAccount getSubAccount(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "getSubAccount(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�ڋq
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngAccountId);
            //�⏕����
            SubAccount l_subAccount = null;

            //�M�p�����J�݂̔���
            if (!l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                //�����ڋq�@@�����^�C�v�F�����������(�a���)
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            else
            {
                //�M�p�ڋq�@@�����^�C�v�F�����M�p�������(�ۏ؋�)>
                l_subAccount =
                    l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }

            //�⏕������ԋp
            log.exiting(STR_METHOD_NAME);
            return new WEB3GentradeSubAccount((SubAccountRow)l_subAccount.getDataSourceObject());

        }
        catch (NotFoundException nfe)
        {
            log.error(nfe.getMessage(), nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }
    }

    /**
     * (get�������ϔ��t�\�z)<BR>
     * <BR>
     * (get�������ϔ��t�\�z)<BR>
     * <BR>
     * �P�j����.���������e���A����ID�A�s��ID�A��n�����擾����B<BR>
     * �@@�@@����ID=����.���������e.get����ID()<BR>
     * �@@�@@�s��ID=����.���������e.get�s��ID()<BR>
     * �@@�@@��n��=����.���������e.get��n��ID()<BR>
     * <BR>
     * �Q�j���������Ώۖ����t���O���擾<BR>
     * �@@�|����ID�A�s��ID�ɑΉ����銔���������Row�I�u�W�F�N�g���擾<BR>
     * �@@�|�����������Row�I�u�W�F�N�g.���������Ώۖ����t���O���擾<BR>
     * <BR>
     * �R�j�������ϔ��t�\�z���擾<BR>
     * �@@�|�������ώ���]�̓T�[�r�X���擾<BR>
     * �@@�|�������ώ���]�̓T�[�r�X.get�������ϔ��t�\�z()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@�⏕�����F����.�⏕����<BR>
     * �@@�@@�w����F�P�j�Ŏ擾�����A��n��<BR>
     * �@@�@@��������ID�F�P�j�Ŏ擾�����A����ID<BR>
     * �@@�@@���������Ώۖ����t���O�F�Q�j�Ŏ擾�������������Ώۖ����t���O<BR>
     * <BR>
     * �S�j�擾�����������ϔ��t�\�z��ԋp<BR>
     * <BR>
     * @@param l_subAccount�@@�⏕����
     * @@param l_newOrderSpec  ���������e
     * @@return double  
     */
    private double getBuyOrderPossibleAmount(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec l_newOrderSpec)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerServiceImpl.getBuyOrderPossibleAmount(WEB3GentradeSubAccount, WEB3TPNewOrderSpec)";

        try
        {
            /*
             * �������ϔ��t�\�z���擾
             */
            //����ID
            long l_lngProductId = l_newOrderSpec.getProductId();
            //�s��ID
            long l_lngMarketId = l_newOrderSpec.getMarketId();
            //��n��
            Date l_datDeliveryDate = l_newOrderSpec.getDeliveryDate();
            //�������������t���O
            boolean l_isTodayDepFundReg = this.isTodayDepFundReg(
                    l_lngProductId, l_lngMarketId);

            log.debug("CALL get�������ϔ��t�\�z");
            log.debug("�@@��n�� = " + l_datDeliveryDate);
            log.debug("�@@����ID = " + l_lngProductId);
            log.debug("�@@�������������t���O = " + l_isTodayDepFundReg);

            //�������ώ���]�̓T�[�r�X���擾
            WEB3TPTradingPowerSettlementService l_service =
                (WEB3TPTradingPowerSettlementService)Services.getService(
                    WEB3TPTradingPowerSettlementService.class);

            //�������ϔ��t�\�z���擾
            double l_dblDayTradeEquityTradingPower =
                l_service.getBuyOrderPossibleAmount(
                    l_subAccount,
                    l_datDeliveryDate,
                    l_lngProductId,
                    l_isTodayDepFundReg);

            return l_dblDayTradeEquityTradingPower;

        }
        catch (WEB3SystemLayerException e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�������ϔ��t�\����)<BR>
     * <BR>
     * �P�j����.���������e���A����ID�A�s��ID�A��n�����擾����B
     * �@@����ID = ����.���������e.get����ID()
     * �@@�s��ID = ����.���������e.get�s��ID()
     * �@@��n�� = ����.���������e.get��n��()
     * 
     * �Q�j�����P�ʂ��擾
     * �@@�|����ID�A�s��ID�ɑΉ����銔���������Row�I�u�W�F�N�g���擾
     * �@@�|�����������Row�I�u�W�F�N�g.�����P�ʂ��擾
     * 
     * �R�j�w�l���擾����B
     * �@@[a.�M�p���n�����̏ꍇ]
     * �@@�i����.���������e.get�����^�C�v == 8:���n�����j
     * 
     * �@@�@@�w�l = ����.���������e.get�T�Z��n���() / ����.���������e.get����()(*)
     * �@@�@@(*)�����_�ȉ��؎̂�
     * 
     * �@@[a.�ȊO�i�������t�����j�̏ꍇ]
     * 
     * �@@�@@�w�l = ����.���������e.get�P��()
     * 
     * �S�j�������ϔ��t�\���ʂ��擾
     * �@@�|�������ώ���]�̓T�[�r�X���擾
     * �@@�|�������ώ���]�̓T�[�r�X.get�������ϔ��t�\����()���R�[��
     * 
     * �@@�@@�m�����n
     * �@@�@@�@@�⏕�����F����.�⏕����
     * �@@�@@�@@�w����F�P�j�Ŏ擾�����A��n��
     * �@@�@@�@@��������ID�F�P�j�Ŏ擾�����A����ID
     * �@@�@@�@@�s��ID�F�P�j�Ŏ擾�����s��ID
     * �@@�@@�@@�w�l�F�R�j�Ŏ擾�����A�w�l
     * �@@�@@�@@�����P�ʁF�Q�j�Ŏ擾�����A�����P��
     * 
     * �S�j�擾�����������ϔ��t�\���ʂ�ԋp
     * 
     * @@param l_subAccount�@@�⏕����
     * @@param l_newOrderSpec  ���������e
     * @@return double
     */
    private double getSellOrderPossibleQuantity(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec l_newOrderSpec)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerServiceImpl.getSellOrderPossibleQuantity(WEB3GentradeSubAccount, WEB3TPNewOrderSpec)";
        log.entering(STR_METHOD_NAME);

        try
        {
            /*
             * ����.���������e���A����ID�A�s��ID�A��n�����擾����B
             * 
             */
            //����ID
            long l_lngProductId = l_newOrderSpec.getProductId();
            //�s��ID
            long l_lngMarketId = l_newOrderSpec.getMarketId();
            //��n��
            Date l_datDeliveryDate = l_newOrderSpec.getDeliveryDate();

            /*
             * �����P�ʂ��擾
             */
            //�����������Row���擾
            EqtypeTradedProductRow l_row = WEB3TPTradingPowerServiceImpl.getEqtypeTradedProductRow(
                l_lngProductId,
                l_lngMarketId);

            //�����P��
            double l_dblLotSize = l_row.getLotSize();

            /*
             * �w�l���擾����
             */
            //�w�l
            double l_dblLimitPrice = 0;

            //[a.�M�p���n�����̏ꍇ]
            if(OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_newOrderSpec.getOrderType()) == true)
            {
                //�w�l = ����.���������e.get�T�Z��n���() / ����.���������e.get����()(*)
                //(*)�����_�ȉ��؎̂�
                l_dblLimitPrice = Math.floor(l_newOrderSpec.getEstimatedPrice()
                    / l_newOrderSpec.getQuantity());

            }
            //[a.�ȊO�i�������t�����j�̏ꍇ]
            else
            {
                //�w�l = ����.���������e.get�P��()
                l_dblLimitPrice = l_newOrderSpec.getPrice();
            }

            log.debug("CALL get�������ϔ��t�\����");
            log.debug("�@@�w��� = " + l_datDeliveryDate);
            log.debug("�@@����ID = " + l_lngProductId);
            log.debug("�@@�s��ID = " + l_lngMarketId);
            log.debug("�@@�w�l   = " + l_dblLimitPrice);
            log.debug("�@@�����P�� = " + l_dblLotSize);

            //�������ώ���]�̓T�[�r�X���擾
            WEB3TPTradingPowerSettlementService l_service = (WEB3TPTradingPowerSettlementService) Services.getService(WEB3TPTradingPowerSettlementService.class);

            //�������ϔ��t�\���ʂ��擾
            double l_dblsellOrderPossQty = l_service.getSellOrderPossibleQuantity(
                l_subAccount,
                l_datDeliveryDate,
                l_lngProductId,
                l_lngMarketId,
                l_dblLimitPrice,
                l_dblLotSize);

            return l_dblsellOrderPossQty;
        }
        catch (WEB3SystemLayerException e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (is���������Ώۖ����t���O)<BR>
     * 
     * ����.����ID�^����.�s��ID��葦�����������t���O���擾����B
     * 
     * <BR>
     * �P�j���������Ώۖ����t���O���擾<BR>
     * �@@�|����ID�A�s��ID�ɑΉ����銔���������Row�I�u�W�F�N�g���擾<BR>
     * �@@�|�����������Row�I�u�W�F�N�g.���������Ώۖ����t���O���擾<BR>
     * 
     * �Q�j�擾�������������Ώۖ����t���O��ԋp<BR>
     * <BR>
     * @@param l_lngProductId  ����ID
     * @@param l_lngMarketId  �s��ID
     * @@return boolean  
     */
    private boolean isTodayDepFundReg(long l_lngProductId, long l_lngMarketId)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerServiceImpl.isTodayDepFundReg(WEB3TPNewOrderSpec)";

        try
        {
            /*
             * ���������Ώۖ����t���O���擾
             */
            //�������������t���O
            boolean l_isTodayDepFundReg = false;

            //�����������Row���擾�B
            EqtypeTradedProductRow l_row =
                WEB3TPTradingPowerServiceImpl.getEqtypeTradedProductRow(
                    l_lngProductId,
                    l_lngMarketId);

            // �������������t���O���Z�b�g
            if (BooleanEnum.TRUE.equals(l_row.getTodayDepFundReg()))
            {
                l_isTodayDepFundReg = true;
            }
            else
            {
                l_isTodayDepFundReg = false;
            }

            return l_isTodayDepFundReg;

        }
        catch (NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (get���������\�񒍕��P�ʈꗗ) <BR>
     * �����ȍ~�̊��������\�񒍕��P�ʂ̃��X�g���擾����B <BR>
     * <BR>
     * 1)�����\�񒍕��P�ʃe�[�u�����ȉ��̏����Ō�������B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@����ID = ����.����ID <BR>
     * �@@�@@������ >= ����.����(T+0) <BR>
     * <BR>
     * 2)�������ꂽRow�I�u�W�F�N�g�̃��X�g��ԋp����B 
     * @@param l_lngAccountId - (����ID)<BR>
     * @@param l_datT0 - (����(T+0))
     * @@return List  
     */
    private List getTodaysRsvEqOrderUnits(long l_lngAccountId, Date l_datT0)
    {
        final String STR_METHOD_NAME = "getTodaysRsvEqOrderUnits(long, Date)";
        log.entering(STR_METHOD_NAME);

        try
        {
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id = ? and ");
            l_strWhereBuf.append("TO_DATE(biz_Date,'YYYYMMDD') >= ?");
            String l_strWhere = l_strWhereBuf.toString();

            Object[] l_bindVars =
            {new Long(l_lngAccountId), l_datT0};

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);

            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get�o���\�z�`0�␳�����`) �i���j�o���]�̓`�F�b�N���Ɏg�p<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�o���\�z�`0�␳�����`�v�Q��<BR>
     * ==================================================================== <BR>
     * �V�[�P���X�} �F((����]�̓T�[�r�X)get�o���\�z�`0�␳�����`) <BR>
     * ��̈ʒu�F(��n�� < �]�͌v�Z����.get�c�Ɠ��i0�j�̏ꍇ)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02336 <BR>
     * ==================================================================== <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_intSummonsDiv - (�Ăяo�����敪)
     * @@return double
     */
    private double getPaymentTradingPowerForCheck(
            WEB3GentradeSubAccount l_subAccount, 
            Date l_datDeliveryDate, 
            int l_intSummonsDiv
            )
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPaymentTradingPowerForCheck(WEB3GentradeSubAccount, Date, boolean)";
        log.entering(STR_METHOD_NAME);

        //�w���
        int l_intDeliDate = 0;

        //����.�⏕�����A����.��n����null�̏ꍇ
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();
    
        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        /*
         * �]�͌v�Z�����I�u�W�F�N�g�𐶐�����
         */
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //��n�� < �]�͌v�Z����.get�c�Ɠ��i0�j�̏ꍇ
        if (WEB3DateUtility.compareToDay(l_datDeliveryDate,
            l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0)) < 0)
        {
            //��O���X���[����
            log.debug("��n������c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��n������c�Ɠ��ł��B");
        }
        //��n�� > �]�͌v�Z����.get�c�Ɠ��i5�j�̏ꍇ
        else if (WEB3DateUtility.compareToDay(l_datDeliveryDate,
            l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5)) > 0)
        {
            //set�]�͌v�Z���<�o��>(int)
            //�]�͌v�Z������Z�b�g����B
            //[����] 
            //�]�͌v�Z��� = 5
            l_calcCond.setPaymentBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //��n������L�ȊO�̏�
        else
        {
            //calc�w���(Date)
            //����.��n���ɑΉ�����w������擾����B
            //[����]
            //Date�F�@@����.��n��
            l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

            //set�]�͌v�Z���<�o��>(int)
            //�]�͌v�Z������Z�b�g����B
            //[����]
            //�]�͌v�Z��� = calc�w����̖߂�l
            l_calcCond.setPaymentBasePoint(l_intDeliDate);
        }

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

            //���Y�]�͏��
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //calc�K�p���̑����i���t�\�z(OrderTypeEnum)
            //�ŏ��̂��̑����i���t�\�z���v�Z����B
            //[����]
            //OrderTypeEnum�F�@@1001:�o��
            WEB3TPCalcResult l_result = l_calcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT);
            log.debug(l_result.toString());

            log.debug("����\�z = " + Double.toString(l_result.tradingPower));
            log.exiting(STR_METHOD_NAME);
            return l_result.tradingPower;
        }
        //�M�p�ڋq�̏ꍇ
        else
        {
            //�]�͌v�Z����(List)���擾
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //get��Е��X�ʗ]�͌v�Z����("cashout.tradingpower.check") == 1:ON_BIZ_DATE�̖߂�l�̏ꍇ
            //(�o�����̏o���\�z���擾����B�ꍇ)
            //���A����.�Ăяo�����敪���A2�i�Fget�o���\�z�`0�␳�����`()���A�Ăяo�����j�̏ꍇ
            if (WEB3CashoutTradingpowerCheckDef.ON_BIZ_DATE.equals(
                l_calcCond.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.CASHOUT_TRADINGPOWER_CHECK))
                    && l_intSummonsDiv == 2)
            {
                //is�����~�敪( )
                //�����~�敪���擾����B
                boolean l_blnIsTradingStop = l_calcCond.isTradingStop();

                //is�o���]�͋敪( )
                //�o���]�͋敪���擾����B
                boolean l_blnIsPaymentStop = l_calcCond.isPaymentStop();

                //is�����~�敪()�Ais�o���]�͋敪()�̖߂�l�̂����ꂩ��true�̏ꍇ
                if (l_blnIsTradingStop || l_blnIsPaymentStop)
                {
                    //-1��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return -1D;
                }

                // calc���̑����i���t�\�z(int)
                //���̑����i���t�\�z���v�Z����B 
                //[����] 
                //int�F �]�͌v�Z����.calc�w���()�̖߂�l
                double l_dblOtherTradingPower =
                    l_calcMargin.calcOtherTradingPower(l_intDeliDate);

                //calc���̑����i���t�\�z()�̖߂�l < 0 �̏ꍇ
                if (l_dblOtherTradingPower < 0)
                {
                    //-1��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return -1D;
                }
                else
                {
                    //�ԋp�l=calc���̑����i���t�\�z()�̖߂�l
                    log.exiting(STR_METHOD_NAME);
                    return l_dblOtherTradingPower;
                }
            }

            //calc�K�p���̑����i���t�\�z(OrderTypeEnum)
            //�ŏ��̂��̑����i���t�\�z���v�Z����B
            //[����]
            //OrderTypeEnum�F�@@1001:�o��
            WEB3TPCalcResult l_result = l_calcMargin.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT);
            log.debug(l_result.toString());   

            if (WEB3CashoutTodayDepositTransferDivDef.EXECUTE.equals(
                l_calcCond.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV))
                && l_intSummonsDiv == 3
                && WEB3DateUtility.compareToDay(l_datDeliveryDate,
                    l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_1)) == 0)
            {
                //calc�a��������]��(�w��� : int)
                //int�F 0
                double l_dblCalcAccountBalanceDemandPower =
                    l_calcMargin.calcAccountBalanceDemandPower(WEB3TPSpecifiedPointDef.T_0);

                //calc�ۏ؋����o�]��(�w��� : int)
                //int�F 0
                double l_dblCalcMarginDrawPower =
                    l_calcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_0);

                //���Y�]�͏��<�M�p�ڋq>Wrapper
                //�]�͌v�Z����Params�Ffind�]�͌v�Z����<�M�p�ڋq>Params()�̖߂�l
                //�]�͌v�Z�����F�@@create�]�͌v�Z����()�̖߂�l
                WEB3TPTradingPowerCalcMarginWrapper l_tradingPowerCalcMarginWrapper =
                    new WEB3TPTradingPowerCalcMarginWrapper(l_lisCalcResult, l_calcCond);

                //calc�����ڋq����c��<�M�p�ڋq>
                //�⏕�����F����.�⏕����
                //���Y�]�͏��<�M�p�ڋq>�F���Y�]�͏��<�M�p�ڋq>Wrapper�I�u�W�F�N�g
                //Date�F����.��n��
                double l_dblCalcRealBalanceMargin = this.calcRealBalanceMargin(
                    l_subAccount, l_tradingPowerCalcMarginWrapper, l_datDeliveryDate);

                //Max( Min(�a��������]��(T+0), �ۏ؋����o�]��(T+0)), 0 ) �{ Max( �����ڋq����c��(T+1), 0 )
                double l_dblAmount = Math.max(
                        Math.min(
                            l_dblCalcAccountBalanceDemandPower,
                            l_dblCalcMarginDrawPower), 0);
                l_dblAmount =
                    new BigDecimal(Double.toString(l_dblAmount)).add(
                        new BigDecimal(
                            Double.toString(Math.max(
                                l_dblCalcRealBalanceMargin, 0)))).doubleValue();
                log.exiting(STR_METHOD_NAME);
                return Math.min(l_result.tradingPower, l_dblAmount);
            }
            log.debug("����\�z = " + Double.toString(l_result.tradingPower));
            log.exiting(STR_METHOD_NAME);
            return l_result.tradingPower;
        }
    }
    
    /**
     * (get�����������())<BR>
     * <BR>
     * ��������������擾����B<BR>
     * @@param l_lngProductId�@@����ID  
     * @@param l_lngMarketId  �s��ID
     * @@return EqtypeTradedProductRow  - �����������Row
     */
    private static EqtypeTradedProductRow getEqtypeTradedProductRow(
        long l_lngProductId,
        long l_lngMarketId)
        throws NotFoundException
    {
        String l_tmName = TradingModuleImpl.TRADING_MODULE_NAME;
        EqTypeProductManager l_pm =
            (EqTypeProductManager) (GtlUtils.getTradingModule(l_tmName).getProductManager());
        TradedProduct tradedProduct = l_pm.getTradedProduct(l_lngProductId, l_lngMarketId);
        EqtypeTradedProductRow l_row = (EqtypeTradedProductRow)tradedProduct.getDataSourceObject();
        return l_row;
    }

    private static String objectsToString(Object[] l_objBindVars)
    {

        StringBuffer l_sb = new StringBuffer();
        if (l_objBindVars != null)
        {
            for (int i = 0; i < l_objBindVars.length; i++)
            {
                if (i > 0)
                {
                    l_sb.append(",");
                }
                l_sb.append("[").append(i).append("]=");
                l_sb.append(String.valueOf(l_objBindVars[i]));
            }
        }
        return l_sb.toString();
    }
   
    /**
     * (get��؋��ւ̐U�։\�z) <BR>
     * <BR>
     * �،��S�ۃ��[�����{�ڋq�ɂ��āA�a����ԍϊz��ԋp����B <BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get��؋��ւ̐U�։\�z�v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getOsakaTransferableTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOsakaTransferableTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);
        
        //����.�⏕������null �̏ꍇ
        if(l_subAccount == null)
        {
            //�G���[���X���[
            log.error("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1.getMainAccount()
        //����.�⏕�������A�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //1.2. is�M�p�����J��(String)
        //�M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B 
        //true�F�@@�J�ݍ� 
        //false�F�@@���J�� 
        //[����] 
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //1.3.(*)����t���[
        //(*)����t���[
        //is�M�p�����J��()�̖߂�l = true(�M�p�ڋq)
        //�̏ꍇ�͈ȉ��̏��������{����B
        if (l_blnMarginAccountEstablished)
        {
            //1.3.1.(*)��O���X���[����B
            //�G���[���X���[
            log.error("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.4.create�]�͌v�Z����<�W��>(�⏕����)
        //�]�͌v�Z�����𐶐�����B 
        //[����] 
        //�⏕�����F����.�⏕����
        //�]�͌v�Z�����I�u�W�F�N�g�𐶐�����B
        WEB3TPCalcCondition l_calcCond =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //1.5.calc�w���(Date)
        //����.��n���ɑΉ�����w������擾����B 
        //[����] 
        //Date�F�@@����.��n��
        int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
        
        //1.6.set�]�͌v�Z���<���̑����t>(int)
        //�]�͌v�Z���<���̑����t>�̒l������.��n���̊���ɃZ�b�g�������B 
        //[����] 
        //int�F�@@calc�w���()�̖߂�l
        l_calcCond.setOtherBasePoint(l_intSpecifiedPoint);
        
        //1.7.find�]�͌v�Z����<�����ڋq>�`�����h�c�w��`(long)
        //�]�͌v�Z����<�����ڋq>Params���擾����B 
        //[����] 
        //long:����.�⏕����.getMainAccountId()
        List l_lisCalcResult = WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(
            l_subAccount.getMainAccount().getAccountId());
        
        //1.8.���Y�]�͏��<�����ڋq>(List, �]�͌v�Z����)
        //���Y�]�͏��<�����ڋq>�I�u�W�F�N�g�𐶐�����B 
        //[����] 
        //�]�͌v�Z����Params�Ffind�]�͌v�Z����<�����ڋq>Params()�̖߂�l 
        //�]�͌v�Z�����F�@@create�]�͌v�Z����()�̖߂�l
        WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity = 
            new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

        //1.9.calc�K�p���̑����i���t�\�z(OrderTypeEnum)
        //�ŏ��̂��̑����i���t�\�z���v�Z����B 
        //[����] 
        //OrderTypeEnum = 1019�F�U�֒����i�a��������؋�)
        WEB3TPCalcResult l_tpCalcResult = 
            l_tpTradingPowerCalcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK);
        
        //1.10.�]�͌v�Z����.����\�z��0�␳���ĕԋp����B
        //�ԋp�l=MAX(�]�͌v�Z����.����\�z, 0)
        //���]�͌v�Z���� = calc�K�p���̑����i���t�\�z()�̖߂�l
        double l_dblMax = Math.max(l_tpCalcResult.tradingPower, 0);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblMax;
    }
    
    /**
     * (get�o���\�z&lt;DB����&gt;�`0�␳�����`)<BR>
     * (get�o���\�z&lt;DB����&gt;�`0�␳�����`)�i���j�o���]�̓`�F�b�N���Ɏg�p<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�o���\�z&lt;DB����&gt;�`0�␳�����`�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getPaymentTradingPowerDBQuote(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getPaymentTradingPowerDBQuote(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕�����A����.��n����null�̏ꍇ
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //�G���[���X���[
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        double l_dblReturn = 0;

        //getMainAccount( )
        //����.�⏕�������A�ڋq�I�u�W�F�N�g���擾����B 
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�M�p�����J��(String)
        //�M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B
        //true�F�@@�J�ݍ�
        //false�F�@@���J��
        //[����]
        //�ٍϋ敪�F�@@0�i�w��Ȃ��j
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //����t���[
        //is�M�p�����J��(String) == false
        if (!l_blnMargin)
        {
            //create�]�͌v�Z����<�W��>(�⏕����)
            //�]�͌v�Z�����𐶐�����B
            //[����]
            //�⏕�����F�@@����.�⏕����
            WEB3TPCalcCondition l_calcCond = 
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //calc�w���(Date)
            //����.��n���ɑΉ�����w������擾����B 
            //[����]
            //Date�F�@@����.��n��
            int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

            //find�]�͌v�Z����<�����ڋq>�`�����h�c�w��`(long)
            //�]�͌v�Z����<�����ڋq>Params���擾����B
            //[����]
            //long�F�@@����.�⏕����.getMainAccountId()
            List l_lisCalcResult = WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(
                l_mainAccount.getAccountId());

            //���Y�]�͏��<�����ڋq>(List, �g�p�\�������, �]�͌v�Z����)
            //���Y�]�͏��<�����ڋq>�I�u�W�F�N�g�𐶐�����B  
            //[����]  
            //�]�͌v�Z����Params�F�@@find�]�͌v�Z����<�����ڋq>()�̖߂�l  
            //�]�͌v�Z�����F�@@create�]�͌v�Z����<�W��>()�̖߂�l 
            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity = 
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //calc�K�p���̑����i���t�\�z(OrderTypeEnum, int)
            //�ŏ��̂��̑����i���t�\�z���v�Z����B
            //[����]
            //OrderTypeEnum�F�@@1001:�o��
            //int�F�@@calc�w���()�̖߂�l
            WEB3TPCalcResult l_tpCalcResult =
                l_tpTradingPowerCalcEquity.calcAppliedOtherTradingPower(
                    OrderTypeEnum.CASH_OUT,
                    l_intSpecifiedPoint);

            //�ԋp�l = �]�͌v�Z����.����\�z
            l_dblReturn = l_tpCalcResult.tradingPower;
        }
        //����t���[
        //is�M�p�����J��(String) == true
        else
        {
            //create�]�͌v�Z����<DB����>(�⏕����)
            //�]�͌v�Z�����𐶐�����B
            //[����]
            //�⏕�����F�@@����.�⏕����
            WEB3TPCalcCondition l_calcCond = 
                WEB3TPCalcCondition.createCalcConditionDBQuote(l_subAccount);

            //calc�w���(Date)
            //����.��n���ɑΉ�����w������擾����B 
            //[����]
            //Date�F�@@����.��n��
            int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

            //�]�͍X�V(long, boolean, �]�͌v�Z����, ���������e[])
            //�]�͍X�V�T�[�r�X�I�u�W�F�N�g�𐶐�����B
            //[����]
            //long�F�@@����.�⏕����.getAccountId()
            //boolean�F�@@is�M�p�����J��()�̕Ԃ�l
            //�]�͌v�Z�����F�@@create�]�͌v�Z����<DB����>()�̖߂�l
            //���������e[]�F�@@null
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                l_mainAccount.getAccountId(),
                l_blnMargin,
                l_calcCond,
                null);

            //calc�]�͍X�V���e<�M�p�ڋq>(String)
            //�]�͌v�Z���ʂ�List���擾
            //[����]
            //String�F�@@2�FDB�����l��
            List l_lisCalcResult =
                l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.DB_QUOTE);

            //���Y�]�͏��<�M�p�ڋq>(List, �]�͌v�Z����)
            //���Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g�𐶐�
            //[����]
            //�]�͌v�Z����Params<�M�p�ڋq>�F�@@calc�]�͍X�V���e<�M�p�ڋq>()�̖߂�l
            //�]�͌v�Z�����F�@@create�]�͌v�Z����<DB����>()�̖߂�l
            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin = 
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //calc�K�p���̑����i���t�\�z(OrderTypeEnum, int)
            //�ŏ��̂��̑����i���t�\�z���v�Z����B
            //[����]
            //OrderTypeEnum�F�@@1001:�o��
            //int�F�@@calc�w���()�̖߂�l
            WEB3TPCalcResult l_tpCalcResult =
                l_tpTradingPowerCalcMargin.calcAppliedOtherTradingPower(
                    OrderTypeEnum.CASH_OUT,
                    l_intSpecifiedPoint);

            //save�]�͍X�V���e<�M�p�ڋq>(List)
            //�]�͍X�V���������s����B
            //[����]
            //List�F�@@calc�]�͍X�V���e<�M�p�ڋq>()�̖߂�l
            l_tpUpd.saveTradingpowerUpdResultMargin(l_lisCalcResult);

            //�ԋp�l = �]�͌v�Z����.����\�z
            l_dblReturn = l_tpCalcResult.tradingPower;
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblReturn;
    }

    /**
     * (get�ۏ؋��ւ̐U�֊z) <BR>
     * <BR>
     * �ۏ؋��ւ̐U�֊z���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�ۏ؋��ւ̐U�֊z�v�Q�� <BR>
     * <BR>
     * ���w�肳�ꂽ�ڋq���M�p�������J�݂̎��A�ۏ؋��ւ̐U�֊z(=0)��ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_dblPayAmount - (�����z)
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getTransferAmountToDeposit(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate,
        double l_dblPayAmount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransferAmountToDeposit(WEB3GentradeSubAccount, Date, double)";
        log.entering(STR_METHOD_NAME);
        // ����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        BigDecimal l_bdPayAmount = new BigDecimal(l_dblPayAmount + "");

        double l_dblTransferAmount = 0;

        BigDecimal l_bdAdddepositNotClearAmt = new BigDecimal("0");

        double l_dblRealBalanceMargin = 0;

        // �ڋq�I�u�W�F�N�g���擾����
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //  is�M�p�����J��
        // �M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B
        // true�F�@@�J�ݍ�
        // false�F�@@���J��
        // [����]
        // �ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        // �M�p�ڋq�̏ꍇ
        if (l_blnIsMarginAccountEstablished)
        {
            // create�]�͌v�Z����<�W��>
            // �]�͌v�Z�����𐶐�����
            // [����]
            // �⏕�����F����.�⏕����
            // �]�͌v�Z�����I�u�W�F�N�g�𐶐�����B
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            // find�]�͌v�Z����<�M�p�ڋq>�`�����h�c�w��`
            List l_lisCalcResults =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_subAccount.getAccountId());

            // ���Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g�𐶐�����
            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResults, l_calcCond);

            //get��Е��X�ʗ]�͌v�Z����(String)
            String l_strDepositRealTransferEnforceDiv =
                l_calcCond.getInstBranCalcCondition(
                    WEB3TPCalcCondition.DEPOSIT_REAL_TRANSFER_ENFORCE_DIV);

            // get��Е��X�ʗ]�͌v�Z����()�̖߂�l  =  null or
            // (get��Е��X�ʗ]�͌v�Z����()�̖߂�l  !=  "1" and
            // get��Е��X�ʗ]�͌v�Z����()�̖߂�l  !=  "2")
            // �̏ꍇ�͈ȉ��̌v�Z�����{���āA�ۏ؋��ւ̐U�֊z��ԋp����
            if (l_strDepositRealTransferEnforceDiv == null
                || (!WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T0.equals(l_strDepositRealTransferEnforceDiv)
                    && !WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T1.equals(l_strDepositRealTransferEnforceDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return l_dblTransferAmount;
            }

            // ����v���[
            // get��Е��X�ʗ]�͌v�Z����()�̖߂�l = "2" �̏ꍇ�͈ȉ��̏��������{����
            if (WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T1.equals(l_strDepositRealTransferEnforceDiv))
            {
            	// calc�w���(Date)
            	int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

            	// get�c�Ɠ�(int)
            	Date l_datBizDate = l_calcCond.getBizDate(l_intSpecifiedPoint + 1);

            	// calc�����ڋq����c��<�M�p�ڋq>(�⏕����, ���Y�]�͏��<�M�p�ڋq>, Date)
            	l_dblRealBalanceMargin = this.calcRealBalanceMargin(
            	    l_subAccount, l_tpTradingPowerCalcMargin, l_datBizDate);

                //create���������Ǘ�(�ڋq)
                WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                    WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

                // create��񐅏��Ǐؖ��������( )
                //��񐅏��Ǐؖ��������𐶐�����B
                WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
                    l_paymentRequisitionManagement.createSecondAdddepositNotClearInfo();

                BigDecimal l_bdSecondDepositNonPay = new BigDecimal(
                    l_secondAdddepositNotClearInfo.secondDepositNonPay + "");
                BigDecimal l_bdSecondDeposit2 = new BigDecimal(
                    l_secondAdddepositNotClearInfo.secondDeposit2 + "");
                BigDecimal l_bdSecondDeposit1 = new BigDecimal(
                    l_secondAdddepositNotClearInfo.secondDeposit1 + "");
                BigDecimal l_bdSecondDepositExpect = new BigDecimal(
                    l_secondAdddepositNotClearInfo.secondDepositExpect + "");

                //��񐅏��Ǐؖ����������v�Z����
                //(*1)��n���̎w��� = calc�w���()�̖߂�l
                //(*1)��n���̎w��� = 0�̏ꍇ
                if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
                {
                    // �Ǐؖ��������z�@@���@@��񐅏��Ǐؖ��������.�Ǐ؋��z(������) 
                    //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+��񐅏��Ǐؖ��������.�Ǐ؋��z(����2) 
                    l_bdAdddepositNotClearAmt = l_bdSecondDepositNonPay.add(l_bdSecondDeposit2);
                }
                //(*1)��n���̎w��� = 1�̏ꍇ
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1)
                {
                    //�Ǐؖ��������z�@@���@@��񐅏��Ǐؖ��������.�Ǐ؋��z(������) 
                    //                �@@+��񐅏��Ǐؖ��������.�Ǐ؋��z(����2) 
                    //                �@@+��񐅏��Ǐؖ��������.�Ǐ؋��z(����1)
                    l_bdAdddepositNotClearAmt =
                        l_bdSecondDepositNonPay.add(l_bdSecondDeposit2).add(l_bdSecondDeposit1);
                }
                //(*1)��n���̎w��� = ��L�ȊO�̏ꍇ
                else
                {
                    //�Ǐؖ��������z�@@���@@��񐅏��Ǐؖ��������.�Ǐ؋��z(������) 
                    //                �@@+��񐅏��Ǐؖ��������.�Ǐ؋��z(����2) 
                    //                �@@+��񐅏��Ǐؖ��������.�Ǐ؋��z(����1)
                    //                �@@+��񐅏��Ǐؖ��������.�Ǐ؋��z(�������z)
                    l_bdAdddepositNotClearAmt = l_bdSecondDepositNonPay.add(
                        l_bdSecondDeposit2).add(l_bdSecondDeposit1).add(l_bdSecondDepositExpect);
                }

                //�Ǐؖ��������z == 0 �̏ꍇ�ȉ��̏��������{����B
                if (GtlUtils.Double.isZero(l_bdAdddepositNotClearAmt.doubleValue()))
                {
                    //get��ꐅ���Ǐؖ��������z( )
                    //��ꐅ���Ǐؖ��������z���擾����B
                    double l_dblFirstAdddepositUncancelAmt =
                        l_paymentRequisitionManagement.getFirstAdddepositUncancelAmt();

                    //get��ꐅ���Ǐ،o�ߓ���( )
                    //��ꐅ���Ǐ،o�ߓ������擾����B
                    int l_intFirstAdddepositPassDay =
                        l_paymentRequisitionManagement.getFirstAdddepositPassDay();

                    //get��ꐅ���ǏؗL���o�ߓ���( )
                    //��ꐅ���ǏؗL���o�ߓ������擾����B
                    int l_intFirstAdddepositPassDayValid =
                        l_paymentRequisitionManagement.getFirstAdddepositPassDayValid();

                    //��ꐅ���Ǐؖ����������v�Z����
                    //(*1)�L���o�ߓ��� - (*2)�o�ߓ��� <= (*3)��n���̎w����̏ꍇ
                    //(*1)get��ꐅ���ǏؗL���o�ߓ���()�̖߂�l
                    //(*2)get��ꐅ���Ǐ،o�ߓ���()�̖߂�l
                    //(*3)calc�w���()�̖߂�l
                    if (l_intFirstAdddepositPassDayValid - l_intFirstAdddepositPassDay <= l_intSpecifiedPoint)
                    {
                        //�Ǐؖ��������z = get��ꐅ���Ǐؖ��������z()�̖߂�l
                        l_bdAdddepositNotClearAmt = new BigDecimal(l_dblFirstAdddepositUncancelAmt + "");
                    }
                }
            }

            // calc�����ڋq����c��<�M�p�ڋq>
            double l_dblRealBal = this.calcRealBalanceMargin(l_subAccount, l_tpTradingPowerCalcMargin, l_datDeliveryDate);

            BigDecimal l_bdRealBal = new BigDecimal(String.valueOf(l_dblRealBal));

            //�@@�Eget��Е��X�ʗ]�͌v�Z����()�̖߂�l = "1"�@@�̏ꍇ
            if (WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T0.equals(l_strDepositRealTransferEnforceDiv))
            {
                //�@@�ۏ؋��ւ̐U�֊z
                //�@@= 
                //�@@MIN(MAX((*1)�����ڋq����c��+����.�ۏ؋��U�֊z, 0), ����.�ۏ؋��U�֊z))
                //(*1) 1.3.8. calc�����ڋq����c��<�M�p�ڋq>�̖߂�l
                double l_dblAmount = l_bdRealBal.add(l_bdPayAmount).doubleValue();
                l_dblAmount = Math.max(l_dblAmount, 0);
                l_dblTransferAmount = Math.min(l_dblAmount, l_dblPayAmount);
            }
            //�@@�Eget��Е��X�ʗ]�͌v�Z����()�̖߂�l = "2"�@@�̏ꍇ
            else if (WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T1.equals(l_strDepositRealTransferEnforceDiv))
            {
                //�s��(T+0) = MIN((*1)�����ڋq����c��, 0)
                double l_dblNon0 = Math.min(l_dblRealBal, 0);
                BigDecimal l_bdNon0 = new BigDecimal(l_dblNon0 + "");

                //�s��(T+1) = MIN((*2)�����ڋq����c��, 0)
                double l_dblNon1 = Math.min(l_dblRealBalanceMargin, 0);
                BigDecimal l_bdNon1 = new BigDecimal(l_dblNon1 + "");

                //�P�A�s��(T+0) <= �s��(T+1)�̏ꍇ
                if ((l_dblNon0 < l_dblNon1) || GtlUtils.Double.isEqual(l_dblNon0, l_dblNon1))
                {
                    //�E�ۏ؋��U�֊z = MAX(����.�ۏ؋��U�֊z + �s��(T+0), 0)
                    double l_dblAmount = l_bdPayAmount.add(l_bdNon0).doubleValue();
                    l_dblTransferAmount = Math.max(l_dblAmount, 0);
                }
                //�Q�A�s��(T+0) > �s��(T+1)�̏ꍇ
                else
                {
                    //a�j����.�ۏ؋��U�֊z + �s��(T+0) - �Ǐؖ��������z <= 0
                    if (l_bdPayAmount.add(l_bdNon0).subtract(l_bdAdddepositNotClearAmt).doubleValue() < 0
                        || GtlUtils.Double.isZero(
                        l_bdPayAmount.add(l_bdNon0).subtract(l_bdAdddepositNotClearAmt).doubleValue()))
                    {
                        //�E�ۏ؋��U�֊z = MAX(����.�ۏ؋��U�֊z + �s��(T+0), 0)
                        double l_dblAmount = l_bdPayAmount.add(l_bdNon0).doubleValue();
                        l_dblTransferAmount = Math.max(l_dblAmount, 0);
                    }
                    //b�j����.�ۏ؋��U�֊z + �s��(T+0) - �Ǐؖ��������z > 0
                    else
                    {
                        //�E�ۏ؋��U�֊z = MAX(����.�ۏ؋��U�֊z + �s��(T+1), �Ǐؖ��������z, 0)
                        double l_dblAmount = l_bdPayAmount.add(l_bdNon1).doubleValue();
                        l_dblAmount = Math.max(l_dblAmount, l_bdAdddepositNotClearAmt.doubleValue());
                        l_dblTransferAmount = Math.max(l_dblAmount, 0);
                    }
                }
            }

            log.exiting(STR_METHOD_NAME);
            return l_dblTransferAmount;
        }
        // �����ڋq�̏ꍇ
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblTransferAmount;
        }
    }

    /**
     * (get���̑����i���t�\�z�`0�␳�����`)<BR>
     * (get���̑����i���t�\�z�`0�␳�����`)<BR>
     * <BR>
     * ���̑����i���t�\�z�i0�␳�����j���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get���̑����i���t�\�z�`0�␳�����`�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getOtherTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOtherTradingPowerForCheck(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);
    
        //����.�⏕�����A����.��n����null�̏ꍇ
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();
    
        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
    
        try
        {
            /*
             * �]�͌v�Z�����I�u�W�F�N�g�𐶐�����
             */
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
    
            //��n��������(T+0)�ȑO�������ꍇ
            if (WEB3DateUtility
                .compareToDay(l_datDeliveryDate, l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0))
                < 0)
            {
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //��n����T+5�ȍ~�������ꍇ
            else if (
                WEB3DateUtility.compareToDay(
                    l_datDeliveryDate,
                    l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5))
                    > 0)
            {
                //�]�͌v�Z���<���̑����t>=T+5���Z�b�g
                l_calcCond.setOtherBasePoint(WEB3TPSpecifiedPointDef.T_5);
            }
            //�ȊO(��n�����AT+0�`T+5�̊�)�̏ꍇ
            else
            {
                //��n���ɑΉ�����w������擾
                int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
    
                //�]�͌v�Z���<���̑����t>=�w������Z�b�g
                l_calcCond.setOtherBasePoint(l_intDeliDate);
            }
    
            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);
    
                //���Y�]�͏��
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);
                //�]�͌v�Z���ʂ��擾
                WEB3TPCalcResult l_result = l_calcEquity.calcAppliedOtherTradingPower(null);
                log.debug(l_result.toString());
    
                //�\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_result.tradingPower;
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                //�]�͌v�Z����(List)���擾
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);
    
                //���Y�]�͏��
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
                //�]�͌v�Z���ʂ��擾
                WEB3TPCalcResult l_result = l_calcMargin.calcAppliedOtherTradingPower(null);
                log.debug(l_result.toString());
    
                //�\�z��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_result.tradingPower;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�O�ݎc�����) <BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�O�ݎc�����v�Q�� <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * @@return WEB3ForeignPositionContract
     * @@throws WEB3SystemLayerException
     */
    public WEB3ForeignPositionContract getForeignPositionContract(
        WEB3GentradeSubAccount l_subAccount, String l_strCurrencyCode)
            throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getForeignPositionContract(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �O�ݎc�������擾����
        // [����]
        //  ����ID�F����.�⏕����.getMainAccountId()
        //  �ʉ݃R�[�h�F����.�ʉ݃R�[�h

        WEB3ForeignPositionContract l_foreignPositionContract =
            WEB3ForeignPositionContract.createForeignPositionContract(
            l_subAccount.getMainAccount().getAccountId(),
            l_strCurrencyCode);

        log.exiting(STR_METHOD_NAME);
        return l_foreignPositionContract;
    }
    
    /**
     * (get�S�ۃ��[���U�։\�z)<BR>
     * <BR>
     * �،��S�ۃ��[�����{�ڋq�ɂ��āA�a����ԍϊz�i�I���b�N�X�N���W�b�g�j��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�S�ۃ��[���U�։\�z�v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getSLChangePossAmt(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSLChangePossAmt(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null �̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //getMainAccount()
        //����.�⏕�������A�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�M�p�����J��(String)
        //�M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B
        //true�F�@@�J�ݍ�
        //false�F�@@���J��
        //[����]
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //(*)����t���[
        //is�M�p�����J��()�̖߂�l = true(�M�p�ڋq)
        //�̏ꍇ�͈ȉ��̏��������{����B
        if (l_blnIsMarginAccountEstablished)
        {
            //�G���[���X���[
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //create�]�͌v�Z����<�W��>(�⏕����)
        //�]�͌v�Z�����𐶐�����B
        //[����]
        //�⏕�����F����.�⏕����
        //�]�͌v�Z�����I�u�W�F�N�g�𐶐�����B
        WEB3TPCalcCondition l_calcCond =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //calc�w���(Date)
        //����.��n���ɑΉ�����w������擾����B
        //[����]
        //Date�F�@@����.��n��
        int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

        //set�]�͌v�Z���<���̑����t>(int)
        //�]�͌v�Z���<���̑����t>�̒l������.��n���̊���ɃZ�b�g�������B
        //[����]
        //int�F�@@calc�w���()�̖߂�l
        l_calcCond.setOtherBasePoint(l_intSpecifiedPoint);

        //find�]�͌v�Z����<�����ڋq>�`�����h�c�w��`(long)
        //�]�͌v�Z����<�����ڋq>Params���擾����B
        //[����]
        //long:����.�⏕����.getMainAccountId()
        List l_lisCalcResults = WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(
            l_subAccount.getMainAccount().getAccountId());

        //���Y�]�͏��<�����ڋq>(List, �]�͌v�Z����)
        //���Y�]�͏��<�����ڋq>�I�u�W�F�N�g�𐶐�����B
        //[����]
        //�]�͌v�Z����Params�Ffind�]�͌v�Z����<�����ڋq>Params()�̖߂�l
        //�]�͌v�Z�����F�@@create�]�͌v�Z����()�̖߂�l
        WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity =
            new WEB3TPTradingPowerCalcEquity(l_lisCalcResults, l_calcCond);

        //calc�K�p���̑����i���t�\�z(OrderTypeEnum)
        //�ŏ��̂��̑����i���t�\�z���v�Z����B
        //[����]
        //OrderTypeEnum = 1020�F�U�֒����i�a�������I���b�N�X�N���W�b�g�j
        WEB3TPCalcResult l_tpCalcResult =
            l_tpTradingPowerCalcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.TO_ORIX_CREDIT);

        //�]�͌v�Z����.����\�z��0�␳���ĕԋp����B
        //�ԋp�l=MAX(�]�͌v�Z����.����\�z, 0)
        //���]�͌v�Z���� = calc�K�p���̑����i���t�\�z()�̖߂�l
        double l_dblMax = Math.max(l_tpCalcResult.tradingPower, 0);

        log.exiting(STR_METHOD_NAME);
        return l_dblMax;
    }

    /**
     * (validate���ʋ������ρ`�I�����C���J�n�O�`) <BR>
     * <BR>
     * �������ς̑Ώیڋq��������s���A<BR>
     * ���ʂ��u���ʋ������ό��ʁv�I�u�W�F�N�g�ɐݒ肵�A�ԋp����B <BR>
     * <BR>
     * ���V�[�P���X�}�u(����]�̓T�[�r�X)validate���ʋ������ρ`�I�����C���J�n�O�`�v�Q��<BR>
     * ==================================================================== <BR>
     * �V�[�P���X�} �F((����]�̓T�[�r�X)validate���ʋ������ρ`�I�����C���J�n�O�`) <BR>
     * ��̈ʒu�F(is�M�p�����J��()�̖߂�l�@@=�@@false(�����ڋq)�̏ꍇ)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02887 <BR>
     * ==================================================================== <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettleBefOnline(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateContractForcedSettleBefOnline(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //getMainAccount( )
        //����.�⏕�������A�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�M�p�����J��(�ٍϋ敪 : String)
        //�M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B
        //true�F�@@�J�ݍ�
        //false�F�@@���J��
        //[����]
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //is�M�p�����J��()�̖߂�l�@@=�@@false(�����ڋq)�̏ꍇ
        if (l_blnMargin == false)
        {
            //��O���X���|
            log.debug("�M�p�����J�݂Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02887,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�M�p�����J�݂Ȃ��B");
        }

        //create���������Ǘ�(�ڋq)
        WEB3TPPaymentRequisitionManagement l_management =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

        //���ʋ������ό���
        WEB3TPContractForcedSettleResult l_settleResult = new WEB3TPContractForcedSettleResult();

        //get��ꐅ���Ǐ،o�ߓ���( )
        //��ꐅ���Ǐ،o�ߓ������擾����B
        int l_intFirstAdddepositPassDay = l_management.getFirstAdddepositPassDay();

        //get��ꐅ���ǏؗL���o�ߓ���( )
        //��ꐅ���ǏؗL���o�ߓ������擾����B
        int l_intFirstAdddepositPassDayValid = l_management.getFirstAdddepositPassDayValid();

        //(*1)��ꐅ���Ǐ،o�ߓ����@@>�@@(*2)��ꐅ���ǏؗL���o�ߓ���
        //(*1)get��ꐅ���Ǐ،o�ߓ����̖߂�l
        //(*2)get��ꐅ���ǏؗL���o�ߓ����̖߂�l
        if (l_intFirstAdddepositPassDay > l_intFirstAdddepositPassDayValid)
        {
            //���ʋ������ό��ʂ�ԋp
            //���ʋ������ό��ʃI�u�W�F�N�g�̑����ɃZ�b�g����B
            //�E����t���O�@@=�@@true
            l_settleResult.resultFlg = true;
            //�E�������ϗ��R�@@=�@@"2"�F�I�����C���J�n�O(�y�x)
            l_settleResult.forcedSettleReason = WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_SLIGHTNESS;
            //�E�Ǐؔ������@@=�@@���������Ǘ�.get��ꐅ���Ǐؔ�����
            l_settleResult.additionalMarginDate = l_management.getFirstAdddepositOccurredDate();
            //�E�Ǐؔ���������̌o�ߓ����@@=�@@���������Ǘ�.get��ꐅ���Ǐ،o�ߓ���
            l_settleResult.additionalMarginAccruedDays = new Integer(l_management.getFirstAdddepositPassDay());
            //�E�ۏ؋��a�����@@=�@@���������Ǘ�.get��ꐅ���Ǐؕۏ؋���
            l_settleResult.marginMaintenanceRate =
                new Double(l_management.getFirstAdddepositMarginDepositRate());

            log.exiting(STR_METHOD_NAME);
            //���ʋ������ό��ʂ�ԋp
            return l_settleResult;
        }

        //get��񐅏��Ǐ؋��z�i�������j( )
        //��񐅏��Ǐ؋��z�i�������j���擾����B
        double l_dblSecondAdddepositDepositNonPay = l_management.getSecondAdddepositDepositNonPay();

        //(*1)��񐅏��Ǐ؋��z(������)�@@>�@@0�@@�̏ꍇ
        //(*1)get��񐅏��Ǐ؋��z(������)�̖߂�l
        if (l_dblSecondAdddepositDepositNonPay > 0)
        {
            //���ʋ������ό��ʂ�ԋp
            //���ʋ������ό��ʃI�u�W�F�N�g�̑����ɃZ�b�g����B 
            //�E����t���O�@@=�@@true
            l_settleResult.resultFlg = true;
            //�E�������ϗ��R�@@=�@@"4"�F�I�����C���J�n�O(�@@��)
            l_settleResult.forcedSettleReason = WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_LEGAL;
            //�E�Ǐؔ������@@=�@@null
            l_settleResult.additionalMarginDate = null;
            //�E�Ǐؔ���������̌o�ߓ����@@=�@@null
            l_settleResult.additionalMarginAccruedDays = null;
            //�E�ۏ؋��a�����@@=�@@null
            l_settleResult.marginMaintenanceRate = null;

            log.exiting(STR_METHOD_NAME);
            //���ʋ������ό��ʂ�ԋp
            return l_settleResult;
        }

        //���ʋ������ό��ʂ�ԋp
        //���ʋ������ό��ʃI�u�W�F�N�g�̑����ɃZ�b�g����B
        //�E����t���O�@@=�@@false
        l_settleResult.resultFlg = false;
        //�E�������ϗ��R�@@=�@@null
        l_settleResult.forcedSettleReason = null;
        //�E�Ǐؔ������@@=�@@null
        l_settleResult.additionalMarginDate = null;
        //�E�Ǐؔ���������̌o�ߓ����@@=�@@null
        l_settleResult.additionalMarginAccruedDays = null;
        //�E�ۏ؋��a�����@@=�@@null
        l_settleResult.marginMaintenanceRate = null;

        log.exiting(STR_METHOD_NAME);
        //���ʋ������ό��ʂ�ԋp
        return l_settleResult;
    }

    /**
     * (validate���ʋ������ρ`��ԁ`) <BR>
     * validate���ʋ������ρ`��ԁ`<BR>
     * <BR>
     * �������ς̑Ώیڋq��������s���A���ʂ��u���ʋ������ό��ʁv�I�u�W�F�N�g�ɐݒ肵�A<BR>
     * �@@�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(����]�̓T�[�r�X)validate���ʋ������ρ`��ԁ`�v�Q��<BR>
     * ================================================================= <BR>
     * �V�[�P���X�} �F((����]�̓T�[�r�X)validate���ʋ������ρ`��ԁ`) <BR>
     * ��̈ʒu�F(is�M�p�����J��()�̖߂�l�@@=�@@false(�����ڋq)�̏ꍇ)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02887 <BR>
     * ================================================================= <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettleIntermission(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateContractForcedSettleIntermission(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //getMainAccount( )
        //����.�⏕�������A�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�M�p�����J��(�ٍϋ敪 : String)
        //�M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B
        //true�F�@@�J�ݍ�
        //false�F�@@���J��
        //[����]
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //is�M�p�����J��()�̖߂�l�@@=�@@false(�����ڋq)�̏ꍇ
        if (l_blnMargin == false)
        {
            //��O���X���|
            log.debug("�M�p�����J�݂Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02887,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�M�p�����J�݂Ȃ��B");
        }

        //create���������Ǘ�(�ڋq)
        //���������Ǘ��I�u�W�F�N�g�𐶐�����B
        //[����]
        //�ڋq�F�@@getMainAccount()�̖߂�l
        WEB3TPPaymentRequisitionManagement l_management =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

        //���ʋ������ό���()
        //���ʋ������ό��ʃI�u�W�F�N�g�𐶐�����B
        WEB3TPContractForcedSettleResult l_settleResult = new WEB3TPContractForcedSettleResult();

        //create��񐅏��Ǐؖ��������( )
        //��񐅏��Ǐؖ��������𐶐�����B
        WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
            l_management.createSecondAdddepositNotClearInfo();

        //(*1)��񐅏��Ǐ؋��z(����2)�@@>�@@0�̏ꍇ
        //(*1)��񐅏��Ǐؖ��������.�Ǐ؋��z(����2)
        if (l_secondAdddepositNotClearInfo.secondDeposit2 > 0)
        {
            //���ʋ������ό��ʃI�u�W�F�N�g�̑����ɃZ�b�g����B
            //�E����t���O�@@=�@@true
            l_settleResult.resultFlg = true;
            //�E�������ϗ��R�@@=�@@"3"�F���
            l_settleResult.forcedSettleReason = WEB3TPForcedSettleReasonDef.TRADING_TIME_ZONE;
            //�E�Ǐؔ������@@=�@@���������Ǘ�.get��񐅏��Ǐؔ�����(����2)
            l_settleResult.additionalMarginDate = l_management.getSecondAdddepositDepositOccurredDate2();
            //�E�Ǐؔ���������̌o�ߓ����@@=�@@"3"�F�Œ�l
            l_settleResult.additionalMarginAccruedDays = new Integer(3);
            //�E�ۏ؋��a�����@@=�@@���������Ǘ�.get��񐅏��Ǐؕۏ؋���(����2)
            l_settleResult.marginMaintenanceRate =
                new Double(l_management.getSecondAdddepositMarginDepositRate2());

            log.exiting(STR_METHOD_NAME);
            //���ʋ������ό��ʃI�u�W�F�N�g��ԋp����B
            return l_settleResult;
        }

        //���ʋ������ό��ʃI�u�W�F�N�g�̑����ɃZ�b�g����B
        //����t���O�@@=�@@false
        l_settleResult.resultFlg = false;
        //�������ϗ��R�@@=�@@null
        l_settleResult.forcedSettleReason = null;
        //�Ǐؔ������@@=�@@null
        l_settleResult.additionalMarginDate = null;
        //�Ǐؔ���������̌o�ߓ����@@=�@@null
        l_settleResult.additionalMarginAccruedDays = null;
        //�ۏ؋��a�����@@=�@@null
        l_settleResult.marginMaintenanceRate = null;

        log.exiting(STR_METHOD_NAME);
        //���ʋ������ό��ʃI�u�W�F�N�g��ԋp����B
        return l_settleResult;
    }

    /**
     * (is����ۏ؋���L������)<BR>
     * <BR>
     * is����ۏ؋���L�����߂����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)is����ۏ؋���L�����߁v�Q��<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_tpUpd - (�]�͍X�V)<BR>
     * �]�͍X�V<BR>
     * @@param l_tpCalcMargin - (���Y�]�͏��<�M�p�ڋq>)<BR>
     * ���Y�]�͏��<�M�p�ڋq><BR>
     * @@return boolean
     */
    protected boolean isReceiptDepositRateOver(
        long l_lngProductId,
        WEB3TPTradingPowerUpd l_tpUpd,
        WEB3TPTradingPowerCalcMargin l_tpCalcMargin)
    {
        final String STR_METHOD_NAME =
            "isReceiptDepositRateOver(long, WEB3TPTradingPowerUpd, WEB3TPTradingPowerCalcMargin)";
        log.entering(STR_METHOD_NAME);

        //get�]�͌v�Z����()
        WEB3TPCalcCondition l_tpCalcCondition = l_tpCalcMargin.getCalcCondition();

        //get��Е��X�ʗ]�͌v�Z����(String)
        String l_strReceiptMarginDepositHighlimitRate =
            l_tpCalcCondition.getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.RECEIPT_MARGIN_DEPOSIT_HIGHLIMIT_RATE);

        //(*)����t���[
        if (l_strReceiptMarginDepositHighlimitRate == null)
        {
            log.debug("get��Е��X�ʗ]�͌v�Z����()�̖߂�l = null");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //����ۏ؋���L���̏���l
        double l_dblReceiptDepositRateMax = Double.parseDouble(l_strReceiptMarginDepositHighlimitRate);
        log.debug("����ۏ؋���L���̏���l = " + l_dblReceiptDepositRateMax);

        if (GtlUtils.Double.isZero(l_dblReceiptDepositRateMax))
        {
            log.debug("get��Е��X�ʗ]�͌v�Z����()�̖߂�l = 0");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //(*)����t���[
        if (l_dblReceiptDepositRateMax > 0)
        {
            //get���ʏ��()
            WEB3TPContractInfo l_tpContractInfo = l_tpUpd.getContractInfo();

            //get�c�Ɠ�(int)
            Date l_datBizDate = l_tpCalcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);

            //calc�������ƕK�v�ۏ؋�(Date, long)
            double l_dblProductMarginDeposit = l_tpContractInfo.calcProductMarginDeposit(l_datBizDate, l_lngProductId);
            BigDecimal l_bdProductMarginDeposit = new BigDecimal(l_dblProductMarginDeposit + "");

            //calc����ۏ؋�(int)
            double l_dblReceiptMarginDeposit = l_tpCalcMargin.calcReceiptMarginDeposit(WEB3TPSpecifiedPointDef.T_5);
            BigDecimal l_bdReceiptMarginDeposit = new BigDecimal(l_dblReceiptMarginDeposit + "");
            log.debug("����ۏ؋�(T+5) = " + l_dblReceiptMarginDeposit);

            //(*)����t���[
            //calc����ۏ؋�() <= 0 �̏ꍇ
            if (l_dblReceiptMarginDeposit < 0 || GtlUtils.Double.isZero(l_dblReceiptMarginDeposit))
            {
                //calc�������ƕK�v�ۏ؋�() > 0 �̏ꍇ
                if (l_dblProductMarginDeposit > 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
                //calc�������ƕK�v�ۏ؋�() <= 0 �̏ꍇ
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }

            //�i*�j����ۏ؋���L���Ǝ���ۏ؋���L���̏���l���v�Z����B
            //����ۏ؋���L��
            BigDecimal l_bdReceiptDepositRate = l_bdProductMarginDeposit.multiply(
                new BigDecimal("100")).divide(
                    l_bdReceiptMarginDeposit,
                    2,
                    BigDecimal.ROUND_UP);
            double l_dblReceiptDepositRate = l_bdReceiptDepositRate.doubleValue();
            log.debug(l_lngProductId + "�̎���ۏ؋���L�� = " + l_dblReceiptDepositRate);

            //(*)����t���[
            if (l_dblReceiptDepositRate < l_dblReceiptDepositRateMax
                || GtlUtils.Double.isEqual(l_dblReceiptDepositRate, l_dblReceiptDepositRateMax))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�����s����)<BR>
     * (get�����s����) <BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�����s�����v�Q�� <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getNextBizDateShortfall(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNextBizDateShortfall(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        double l_dblNextBizDateShortfall = 0;
        //getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�M�p�����J��(String)
        //[����]
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountOpen =
            l_mainAccount.isMarginAccountEstablished( WEB3GentradeRepaymentDivDef.DEFAULT);

        if (l_blnIsMarginAccountOpen)
        {
            //create�]�͌v�Z����<�W��>(�⏕���� : �⏕����)
            WEB3TPCalcCondition l_tpCalcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            //find�]�͌v�Z����<�M�p�ڋq>�`�����h�c�w��`(����ID : long)
            List l_lisTpCalcResultMarginParams =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_subAccount.getAccountId());
            //���Y�]�͏��<�M�p�ڋq>(�]�͌v�Z����<�M�p�ڋq> : List, �]�͌v�Z���� : �]�͌v�Z����)
            //[����]
            //�]�͌v�Z����Params�Ffind�]�͌v�Z����<�����ڋq>Params()�̖߂�l
            //�]�͌v�Z�����F�@@create�]�͌v�Z����()�̖߂�l
            WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisTpCalcResultMarginParams, l_tpCalcCondition);
            //calc�����s����(�⏕���� : �⏕����, ���Y�]�͏��<�M�p�ڋq>)
            l_dblNextBizDateShortfall = this.calcNextBizDateShortfall(l_subAccount, l_tradingPowerCalcMargin);
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblNextBizDateShortfall;
    }

    /**
     * �icalc�����s�����j<BR>
     * �icalc�����s�����j<BR>
     * <BR>
     * �ڋq�́u�����s�����v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_tradingPowerCalcMargin - �i���Y�]�͏��<�M�p�ڋq>�j<BR>
     * ���Y�]�͏��<�M�p�ڋq><BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcNextBizDateShortfall(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcNextBizDateShortfall(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //�V�X�e�����t���擾����B
        //�V�X�e�����t�i�����j
        //�V�X�e�����t�i���c�Ɠ��j
        Date l_datSystemDay = l_tradingPowerCalcMargin.getCalcCondition().getBizDate(0);
        Date l_datNextBizDate = l_tradingPowerCalcMargin.getCalcCondition().getBizDate(1);

        //�ڋq�}�X�^���擾����B
        //�q�}�X�^ = ����.�⏕����.getMainAccount()
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //�]�͌v�Z���ʂɊԂ��Ă���v�Z���ڂ��擾����B
        //�]�͌v�Z���ʂ��擾����B
        //�]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j  = ���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>
        TpCalcResultMarginParams l_tpCalcResultMarginParams = l_tradingPowerCalcMargin.getCalcResultMargin();

        //�]�͌v�Z���ʏڍ׃e�[�u���i�M�p�ڋq�j    = ���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>
        TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = l_tradingPowerCalcMargin.getCalcResultDetailMargin();
        
        //�]�͌v�Z���ʂɊԂ��Ă���v�Z����
        //�a����iT+0�j      = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.�a����c���iT+0�j
        double l_dblAccountBalance0 = l_tpCalcResultMarginParams.getAccountBalance0();

        //�a����iT+1�j      = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.�a����c���iT+1�j
        double l_dblAccountBalance1 = l_tpCalcResultMarginParams.getAccountBalance1();

        //�������ϑ���iT+0�j  = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.�������ϑ���iT+0�j
        double l_dblTodayExecutedAmount0 = l_tpCalcResultMarginParams.getTodayExecutedAmount0();

        //�������ϑ���iT+1�j  = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.�������ϑ���iT+1�j
        double l_dblTodayExecutedAmount1 = l_tpCalcResultMarginParams.getTodayExecutedAmount1();

        //������������iT+0�j  = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.������������iT+0�j
        double l_dblTodayUnexecutedAmount0 = l_tpCalcResultMarginParams.getTodayUnexecutedAmount0();

        //������������iT+1�j    = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.������������iT+1�j
        double l_dblTodayUnexecutedAmount1 = l_tpCalcResultMarginParams.getTodayUnexecutedAmount1();
        
        //���v��S�����iT+1�j       = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.���v��S�����iT+1�j
        double l_dblDayTradeRestraint1 = l_tpCalcResultMarginParams.getDayTradeRestraint1();

        //��p�،��]���z�iT+1�j  = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.��p�،��]���z�iT+1�j
        double l_dblSubstituteSecurityAsset1= l_tpCalcResultMarginParams.getSubstituteSecurityAsset1();

        //�����ό��ʑ���iT+1�j  = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.�����ό��ʑ���iT+1�j
        double l_dblContractAmount1= l_tpCalcResultMarginParams.getContractAmount1();

        //����n���ʑ���iT+1�j  = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.����n���ʑ���iT+1�j
        double l_dblUndeliContractAmount1 = l_tpCalcResultMarginParams.getUndeliContractAmount1();

        //�����K�v�ۏ؋��iT+1�j  = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.�����K�v�ۏ؋��iT+1�j
        double l_dblCashMarginDeposit1 = l_tpCalcResultMarginParams.getCashMarginDeposit1();

        //�����ό��ʕ]�����v�iT+1�j    = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.�����ό��ʕ]�����v�iT+1�j
        double l_dblContractAssetProfitLoss1 = l_tpCalcResultMarginParams.getContractAssetProfitLoss1();

        //���ʏ��o��iT+1�j        = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.���ʏ��o��iT+1�j
        double l_dblContractTotalCost1 = l_tpCalcResultMarginParams.getContractTotalCost1();

        //����n���ʌ��ϑ��iT+1�j = �]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j.����n���ʌ��ϑ��iT+1�j
        double l_dblUndeliContractLoss1 = l_tpCalcResultMarginParams.getUndeliContractLoss1();

        //���������Ώۖ����S����(T+0)�@@= �]�͌v�Z���ʏڍ׃e�[�u���i�M�p�ڋq�j.���������Ώۖ����S����(T+0)
        double l_dbTodayDepFundRestraint0 = l_tpCalcResultMarginDetailParams.getTodayDepFundRestraint0();
        
        //���������Ώۖ����S����(T+1)�@@= �]�͌v�Z���ʏڍ׃e�[�u���i�M�p�ڋq�j.���������Ώۖ����S����(T+1)
        double l_dbTodayDepFundRestraint1 = l_tpCalcResultMarginDetailParams.getTodayDepFundRestraint1();

        SubAccount l_eqtypeMarSubAccount = null;
        try
        {
            //�ڋq����c���ɊԂ��Ă���v�Z���ڂ��擾����B
            //�⏕���� = �ڋq�}�X�^.getSubAccount(�g2:�����M�p��������i�ۏ؋��j�h)
            l_eqtypeMarSubAccount =
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�ڋq����c��(�}�X�^���)=�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�ڋq����c��(�}�X�^���) (����ID, �⏕����ID)
        TpCashBalanceRow l_tpCashBalanceRow =
            WEB3TPPersistentDataManager.getInstance().getTpCashBalanceRow(
                l_mainAccount.getAccountId(), l_eqtypeMarSubAccount.getSubAccountId());

        //�ڋq����c��(�}�X�^���)�ɊԂ��Ă���v�Z����
        //[a. �ڋq����c���i�}�X�^���j.�c���i����+�@@�O���j == null�̏ꍇ]
        //�c���iT+0�j = 0
        // �ȊO�@@�̏ꍇ
        //�c���iT+0�j = �ڋq����c���i�}�X�^���j.�i����+�@@�O���j
        double l_dblTpCashBalance0 = 0.0;
        if (l_tpCashBalanceRow != null)
        {
            l_dblTpCashBalance0 = l_tpCashBalanceRow.getCashBalance0();
        }

        //�ڋq����c���i�}�X�^���j.�c���i����+�@@�P���j == null�̏ꍇ
        //�c���iT+1�j = 0
        //�ȊO�̏ꍇ:�c���iT+1�j = �ڋq����c���i�}�X�^���j.�c���i����+�@@�P���j
        double l_dblTpCashBalance1 = 0.0;
        if (l_tpCashBalanceRow != null)
        {
            l_dblTpCashBalance1 = l_tpCashBalanceRow.getCashBalance1();
        }

        double l_dblMarginMaintenanceRate =
            l_tradingPowerCalcMargin.getCalcCondition().getMarginMentenanceRate() / 100.0;

        //�Œ�K�v�ۏ؋�   = ���X.�Œ�K�v�ۏ؋�
        double l_dblMinMarginDeposit =
            l_tradingPowerCalcMargin.getCalcCondition().getMinMarginDeposit();

        //�ۏ؋��U�ւɊԂ��Ă���v�Z���ڂ��擾����B
        //���o�������P�ʃe�[�u�����ȉ��̏����Ō�������B
        //����ID = �⏕����.getAccountId()
        //�⏕����ID = �⏕����.getSubAccountId()
        //������� IN(�g1005:�a�������M�p�ۏ؋��h,�g1006:�M�p�ۏ؋�����a����h)
        //������ >= �V�X�e�����t�i�����j
        //������� NOT IN(�g6:�������s(�V�K����)�h,�g14:������(�������)�h)
        List l_lisAioOrderUnitDeposits = null;
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbWhereDeposit = new StringBuffer();
            l_sbWhereDeposit.append(" account_id = ? ");
            l_sbWhereDeposit.append(" and sub_account_id = ? ");
            l_sbWhereDeposit.append(" and order_type in (?, ?) ");
            l_sbWhereDeposit.append(" and biz_date >= ? ");
            l_sbWhereDeposit.append(" and order_status not in(?, ?) ");
            Object[] l_aioWhereDeposits =
                {new Long(l_mainAccount.getAccountId()),
                new Long(l_eqtypeMarSubAccount.getSubAccountId()),
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                WEB3DateUtility.formatDate(l_datSystemDay,"yyyyMMdd"),
                OrderStatusEnum.NOT_ORDERED,
                OrderStatusEnum.CANCELLED};

            l_lisAioOrderUnitDeposits = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_sbWhereDeposit.toString(),
                l_aioWhereDeposits);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�U�֋�(T+0�ȑO)
        double l_dblTransferAmountT0 = 0.0;
        BigDecimal l_bdTransferAmountT0 = new BigDecimal(Double.toString(0.0));
        //�U�֋�(T+1)
        double l_dblTransferAmountT1 = 0.0;
        BigDecimal l_bdTransferAmountT1 = new BigDecimal(Double.toString(0.0));

        //�������ꂽ���o�������P�ʃe�[�u���̍s���U�֋����W�v����
        //���� (=i)�͈̔�(0�`�g�������ꂽ�s���g-1)�ňȉ��̏������J��Ԃ��B
        int l_intCount = l_lisAioOrderUnitDeposits.size();
        for (int i = 0 ; i < l_intCount; i++)
        {
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitDeposits.get(i);
            //���o�������P�ʃe�[�u��.��n�� <= �V�X�e�����t�i�����j �̏ꍇ
            if (WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(), l_datSystemDay) <= 0)
            {
                //�����P�ʃe�[�u��.������� = �g1005:�a�������M�p�ۏ؋��h�̏ꍇ
                if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                    l_aioOrderUnitRow.getOrderType()))
                {
                    //�U�֋�(T+0�ȑO) = �U�֋�(T+0�ȑO) + �����P�ʃe�[�u��.��������
                    l_bdTransferAmountT0 = l_bdTransferAmountT0.add(new BigDecimal(
                        Double.toString(l_aioOrderUnitRow.getQuantity())));
                }
                //�����P�ʃe�[�u��.������� = �g1006:�M�p�ۏ؋�����a����h�̏ꍇ
                else
                {
                    //�U�֋�(T+0�ȑO) = �U�֋�(T+0�ȑO) - �����P�ʃe�[�u��.��������
                    l_bdTransferAmountT0 = l_bdTransferAmountT0.subtract(new BigDecimal(
                        Double.toString(l_aioOrderUnitRow.getQuantity())));
                }
            }

            //���o�������P�ʃe�[�u��.��n�� = �V�X�e�����t�i���c�Ɠ��j �̏ꍇ
            if (WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(), l_datNextBizDate) == 0)
            {
                //�����P�ʃe�[�u��.������� = �g1005:�a�������M�p�ۏ؋��h�̏ꍇ
                if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                    l_aioOrderUnitRow.getOrderType()))
                {
                    //�U�֋�(T+1) = �U�֋�(T+1) + �����P�ʃe�[�u��.��������
                    l_bdTransferAmountT1 = l_bdTransferAmountT1.add(new BigDecimal(
                        Double.toString(l_aioOrderUnitRow.getQuantity())));
                }
                //�����P�ʃe�[�u��.������� = �g1006:�M�p�ۏ؋�����a����h�̏ꍇ
                else
                {
                    //�U�֋�(T+1) = �U�֋�(T+1) - �����P�ʃe�[�u��.��������
                    l_bdTransferAmountT1 = l_bdTransferAmountT1.subtract(new BigDecimal(
                        Double.toString(l_aioOrderUnitRow.getQuantity())));
                }
            }
        }
        l_dblTransferAmountT0 = l_bdTransferAmountT0.doubleValue();
        l_dblTransferAmountT1 = l_bdTransferAmountT1.doubleValue();

        //SONAR���ŁA�a����̕s������z���v�Z����B(T+0)
        //SONAR�a������v�Z����B
        //WEB�����ۏ؋��iT+0�j   = �a����iT+0�j - �������ϑ���iT+0�j 
        //- ������������iT+0�j - ���������Ώۖ����S����(T+0)
        double l_dblWebMarginAccountBalance = 
            new BigDecimal(Double.toString(l_dblAccountBalance0)).subtract(
                new BigDecimal(Double.toString(l_dblTodayExecutedAmount0))).subtract(
                    new BigDecimal(Double.toString(l_dblTodayUnexecutedAmount0))).subtract(
                        new BigDecimal(Double.toString(l_dbTodayDepFundRestraint0))).doubleValue();

        //SONAR�����ۏ؋��iT+0�j   = �c���iT+0�j + �U�֋�(T+0�ȑO)
        double l_dblSonaMarginAccountBalance0 = new BigDecimal(
            Double.toString(l_dblTpCashBalance0)).add(
                new BigDecimal(Double.toString(l_dblTransferAmountT0))).doubleValue();

        //SONAR�a����iT+0�j     = WEB�����ۏ؋��iT+0�j - SONAR�����ۏ؋��iT+0�j
        double l_dblSonaAccountBalance0 = 
            new BigDecimal(Double.toString(l_dblWebMarginAccountBalance)).subtract(
                new BigDecimal(Double.toString(l_dblSonaMarginAccountBalance0))).doubleValue();

        //SONAR�a����s���z���v�Z����B
        double l_dblUnlessSonaAccountBalance0 = Math.abs(Math.min(l_dblSonaAccountBalance0, 0));

        //�ۏ؋����a����ւ̐U�֊z���v�Z����B(T+0)
        //SONAR�a����s���z(T+0) > 0 ���A���������Ώۖ����S����(T+0) > 0 �̏ꍇ
        //�a����ւ̐U�֊z(T+0) = SONAR�a����s���z(T+0)
        //��L�ȊO �̏ꍇ
        //�a����ւ̐U�֊z(T+0) = 0
        double l_dblToAccBalTraAmount0 = 0.0;
        if (l_dblUnlessSonaAccountBalance0 > 0 && l_dbTodayDepFundRestraint0 >0)
        {
            l_dblToAccBalTraAmount0 = l_dblUnlessSonaAccountBalance0;
        }

        //�ۏ؋��ێ��]�͂��v�Z����B(T+1)
        //SONAR�����ۏ؋�(T+1)���v�Z����B
        //SONAR�����ۏ؋�(T+1) = ( �c��(T+1) + �U�֋�(T+1) + �U�֋�(T+0�ȑO) ) 
        //+ ��p�،��]���z(T+1) - �a����ւ̐U�֊z(T+0)
        double l_dblPaidMarginDeposit1 = new BigDecimal(
            Double.toString(l_dblTpCashBalance1)).add(
                new BigDecimal(Double.toString(l_dblTransferAmountT1))).add(
                    new BigDecimal(Double.toString( l_dblTransferAmountT0))).add(
                        new BigDecimal(Double.toString( l_dblSubstituteSecurityAsset1))).subtract(
                            new BigDecimal(Double.toString( l_dblToAccBalTraAmount0))).doubleValue();

        //SONAR����ۏ؋�(T+1)���v�Z����B
        //SONAR����ۏ؋�(T+1)   = SONAR�����ۏ؋�(T+1) + MIN(�����ό��ʕ]�����v(T+1), 0) 
        //- ���ʏ��o��(T+1) - ����n���ʌ��ϑ�(T+1)
        double l_dblSonaReceiptMarginDeposit1 = new BigDecimal(Double.toString(l_dblPaidMarginDeposit1)).add(
            new BigDecimal(Double.toString(Math.min(l_dblContractAssetProfitLoss1, 0)))).subtract(
                new BigDecimal(Double.toString(l_dblContractTotalCost1))).subtract(
                    new BigDecimal(Double.toString(l_dblUndeliContractLoss1))).doubleValue();

        //SONAR�ۏ؋��ێ��]��(T+1)���v�Z����B
        //�����ό��ʑ��(T+1) + ����n���ʑ��(T+1) = 0�̏ꍇ
        //SONAR�ۏ؋��ێ��]��(T+1) = SONAR����ۏ؋�(T+1)
        double l_dblMarginMaintenancePower1 = 0.0;
        if (GtlUtils.Double.isZero(l_dblContractAmount1 + l_dblUndeliContractAmount1))
        {
            l_dblMarginMaintenancePower1 = l_dblSonaReceiptMarginDeposit1;
        }
        //�����ό��ʑ��(T+1) + ����n���ʑ��(T+1) > 0�̏ꍇ
        else if (l_dblContractAmount1 + l_dblUndeliContractAmount1 > 0)
        {
            //SONAR�ۏ؋��ێ��]��(T+1) = Min(SONAR�����ۏ؋�(T+1) - �Œ�K�v�ۏ؋�,
            //SONAR����ۏ؋�(T+1) - MAX(�����ό��ʑ��(T+1) 
            //       * �ۏ؋��ێ���, �@@��Œ�K�v�ۏ؋�))
            l_dblMarginMaintenancePower1 = Math.min(
                new BigDecimal(Double.toString(l_dblPaidMarginDeposit1)).subtract(
                    new BigDecimal(Double.toString(l_dblMinMarginDeposit))).doubleValue() ,
                new BigDecimal(Double.toString(l_dblSonaReceiptMarginDeposit1)).subtract(
                    new BigDecimal(Double.toString(Math.max(
                        new BigDecimal(Double.toString(l_dblContractAmount1)).multiply(
                            new BigDecimal(Double.toString(l_dblMarginMaintenanceRate))).doubleValue(), 
                        l_tradingPowerCalcMargin.getCalcCondition().getLegalMinMarginDeposit())))).doubleValue());
        }

        //SONAR���ŁA�a����̕s������z���v�Z����B(T+1)
        //SONAR�a������v�Z����B
        //WEB�����ۏ؋��iT+1�j    = �a����iT+1�j - �������ϑ���iT+1�j 
        //- ������������iT+1�j - ���������Ώۖ����S����(T+1)
        double l_dblWebMarginAccountBalance1 = 
            new BigDecimal(Double.toString(l_dblAccountBalance1)).subtract(
                new BigDecimal(Double.toString(l_dblTodayExecutedAmount1))).subtract(
                    new BigDecimal(Double.toString(l_dblTodayUnexecutedAmount1))).subtract(
                        new BigDecimal(Double.toString(l_dbTodayDepFundRestraint1))).doubleValue();

        //SONAR�����ۏ؋��iT+1�j  = �c���iT+1�j + �U�֋�(T+1) + �U�֋�(T+0�ȑO) 
        //- �a����ւ̐U�֊z(T+0)
        double l_dblSonaMarginAccountBalance1 = 
            new BigDecimal(Double.toString(l_dblTpCashBalance1)).add(
                new BigDecimal(Double.toString(l_dblTransferAmountT1))).add(
                    new BigDecimal(Double.toString(l_dblTransferAmountT0))).subtract(
                        new BigDecimal(Double.toString(l_dblToAccBalTraAmount0))).doubleValue();

        //SONAR�a����iT+1�j= WEB�����ۏ؋��iT+1�j - SONAR�����ۏ؋��iT+1�j
        double l_dblSonaAccountBalance1 = 
            new BigDecimal(Double.toString(l_dblWebMarginAccountBalance1)).subtract(
                new BigDecimal(Double.toString(l_dblSonaMarginAccountBalance1))).doubleValue();

        //SONAR�a����s���z���v�Z����B
        //SONAR�a����s���z�iT+1�j= ABS( MIN(SONAR�a����iT+1�j - ���v��S�����iT+1�j, 0) )
        double l_dblUnlessSonaAccountBalance1 = Math.abs(Math.min(
            new BigDecimal(Double.toString(l_dblSonaAccountBalance1)).subtract(
                new BigDecimal(Double.toString(l_dblDayTradeRestraint1))).doubleValue(), 0));

        //�ۏ؋����a����ւ̐U�֊z���v�Z����B(T+1)
        //SONAR�a����s���z(T+1) <= 0�̏ꍇ
        double l_dblToAccBalTraAmount1 = 0.0;
        if (l_dblUnlessSonaAccountBalance1 <= 0)
        {
            //�iSONAR�a����ɏ\������������A�U��ւ��Ȃ��Ă����֋��E���ʗ��֋��͔������Ȃ��j
            //0��ԋp����
            log.exiting(STR_METHOD_NAME);
            return 0D;
        }
        //SONAR�a����s���z(T+1) > 0�̏ꍇ
        else
        {
            //SONAR�����ۏ؋�(T+1) - SONAR�a����s���z(T+1) - �����K�v�ۏ؋�(T+1) >= 0 
            //and SONAR�ۏ؋��ێ��]��(T+1) + SONAR �a���(T+1) >= 0 �̏ꍇ
            double l_dblSubAmount =  
                new BigDecimal(Double.toString(l_dblSonaMarginAccountBalance1)).subtract(
                    new BigDecimal(Double.toString(l_dblUnlessSonaAccountBalance1))).subtract(
                        new BigDecimal(Double.toString(l_dblCashMarginDeposit1))).doubleValue();

            double l_dblAddAmount = 
                new BigDecimal(Double.toString(l_dblMarginMaintenancePower1)).add(
                    new BigDecimal(Double.toString(l_dblSonaAccountBalance1))).doubleValue();
            if (l_dblSubAmount >= 0 && l_dblAddAmount >= 0)
            {
                //0��ԋp����
                log.exiting(STR_METHOD_NAME);
                return 0D;
            }
            //��L�ȊO�̏ꍇ
            else
            {
                //�a����ւ̐U�֊z(T+1) = MIN(ABS(MIN(SONAR �a���(T+1), 0)),
                //MAX(MIN(SONAR�ۏ؋��ێ��]��(T+1), SONAR�����ۏ؋�(T+1) 
                //        - �����K�v�ۏ؋�(T+1)), 0))
                l_dblToAccBalTraAmount1 = Math.min(Math.abs(Math.min(l_dblSonaAccountBalance1, 0)),
                    Math.max(Math.min(l_dblMarginMaintenancePower1,
                        new BigDecimal(Double.toString(l_dblSonaMarginAccountBalance1)).subtract(
                            new BigDecimal(Double.toString(l_dblCashMarginDeposit1))).doubleValue()), 0));
            }
        }

        //�����s�������v�Z����B
        //�����s���� = ABS(MIN(SONAR �a���(T+1) �{ �a����ւ̐U�֊z(T+1), 0)) 
        double l_dblNextBizDateShortfall = Math.abs(Math.min(
            new BigDecimal(Double.toString(l_dblSonaAccountBalance1)).add(
                new BigDecimal(Double.toString(l_dblToAccBalTraAmount1))).doubleValue(), 0D));

        BigDecimal l_bdNextBizDateShortfall = new BigDecimal(Double.toString(l_dblNextBizDateShortfall));

        l_dblNextBizDateShortfall =
            l_bdNextBizDateShortfall.setScale(0, BigDecimal.ROUND_DOWN).doubleValue();

        log.exiting(STR_METHOD_NAME);
        return l_dblNextBizDateShortfall;
    }

    /**
     * (get�����ۏ؋����o�]��)<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�����ۏ؋����o�]�́v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strDbCurrentPriceCheckDiv - (DB�����]�̓`�F�b�N�敪)<BR>
     * �⏕����<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getTodayDepositWithdrawTradingPower(
        WEB3GentradeSubAccount l_subAccount, String l_strDbCurrentPriceCheckDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTodayDepositWithdrawTradingPower("
            + "WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        //getMainAccount
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�M�p�����J��(�ٍϋ敪 : String)
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountOpen =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //is�M�p�����J�݂̖߂�l��FALSE�i�����ڋq�j�̏ꍇ
        if (!l_blnIsMarginAccountOpen)
        {
            log.exiting(STR_METHOD_NAME);
            return 0D;
        }
        //is�M�p�����J�݂̖߂�l��TRUE�i�M�p�ڋq�j�̏ꍇ
        else
        {
            WEB3TPCalcCondition l_tpCalcCondition = null;
            //DB�����]�̓`�F�b�N�敪=0�i�����{�j�̏ꍇ
            if (WEB3DbCurrentPriceCheckDivDef.NOT_ENFORCEMENT.equals(l_strDbCurrentPriceCheckDiv))
            {
                //create�]�͌v�Z����<�W��>(�⏕����)
                l_tpCalcCondition =
                    WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            }
            //DB�����]�̓`�F�b�N�敪=1�i���{�j�̏ꍇ
            else if (WEB3DbCurrentPriceCheckDivDef.ENFORCEMENT.equals(l_strDbCurrentPriceCheckDiv))
            {
                //create�]�͌v�Z����<DB����>(�⏕����)
                l_tpCalcCondition =
                    WEB3TPCalcCondition.createCalcConditionDBQuote(l_subAccount);
            }

            //find�]�͌v�Z����<�M�p�ڋq>�`�����h�c�w��`
            List l_lisFindCalcResultMarginParams =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(
                    l_subAccount.getAccountId());

            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(
                    l_lisFindCalcResultMarginParams, l_tpCalcCondition);


            double l_dblCalcMarginDrawPower = 0;

            //���������Ώۖ����S����( T + 0 ) > 0 �̏ꍇ
            if (l_tpTradingPowerCalcMargin.getCalcResultDetailMargin().getTodayDepFundRestraint0() > 0)
            {
                l_dblCalcMarginDrawPower =
                    l_tpTradingPowerCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_0);
            }         

            log.exiting(STR_METHOD_NAME);
            return l_dblCalcMarginDrawPower;
        }
    }

    /**
     * (validate����]��<�O���������t>)<BR>
     * <BR>
     * �O���������t�ɂ����āA���������e����荞�ݗa����`�F�b�N�����{����B<BR> 
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�O���������t>�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_newOrderSpecs - (���������e)<BR>
     * ���������e<BR>
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O<BR>
     * true�̎��A�]�͍Čv�Z���������{����<BR>
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPowerFeqBuy(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPowerFeqBuy("
            + "WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null
            || l_newOrderSpecs == null
            || l_newOrderSpecs.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //getAccountId
        long l_lngAccountId = l_subAccount.getAccountId();

        //getMainAccount
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�M�p�����J��(�ٍϋ敪 : String)
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountOpen =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //create�]�͌v�Z����<�W��>(�⏕����)
        WEB3TPCalcCondition l_tpCalcCondition =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //����t���[
        if (l_tpCalcCondition.isTradingStop())
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //����t���O���Z�b�g
            l_tpResult.setResultFlg(false);
            //����\�z���Z�b�g
            l_tpResult.setTradingPower(0);

            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //����t���[
        if (l_tpCalcCondition.isOtherTradingStop())
        {
            //����]�͌��ʃI�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //����t���O���Z�b�g
            l_tpResult.setResultFlg(false);
            //����\�z���Z�b�g
            l_tpResult.setTradingPower(0);

            /*
             * ����]�̓G���[���𐶐�
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.OTHER_TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //����]�̓G���[�����Z�b�g
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //����]�͌��ʂ�ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //get��n��
        Date l_datDeliveryDate = l_newOrderSpecs[0].getDeliveryDate();

        //calc�w���(Date)
        //Date = ''��n��"
        int l_intSpecifiedPoint = 0;

        if (WEB3DateUtility.compareToDay(
            l_datDeliveryDate,
            l_tpCalcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5))
            > 0)
        {
            //[a."��n��" > T+5 �̏ꍇ]
            //calc�w���()�̖߂�l���AT+5(=5)�Ƃ���B
            l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_5;
        }
        else if (WEB3DateUtility.compareToDay(
            l_tpCalcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0),
            l_datDeliveryDate)
            > 0)
        {
            //[a."��n��" < T+0 �̏ꍇ]
            //��O�����X���[����B
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        else
        {
            l_intSpecifiedPoint = l_tpCalcCondition.calcSpecifiedPoint(l_datDeliveryDate);
        }

        //�]�͍X�V(long, boolean, �]�͌v�Z����, ���������e[])
        //long = �⏕����.getAccountId()
        //boolean = �ڋq.is�M�p�����J��()
        //�]�͌v�Z���� = create�]�͌v�Z����<�W��>()
        //���������e[0] = ����.���������e
        WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpd(
            l_lngAccountId,
            l_blnIsMarginAccountOpen,
            l_tpCalcCondition,
            l_newOrderSpecs);

        //����]�͌���
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        //����t���[
        if (!l_blnIsMarginAccountOpen)
        {
            //calc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`
            //�����ڋq�̗]�͍X�V���e���v�Z����
            //����蔄�t�������l���������v��S�������擾����B
            //�i�o���𔺂������̎���]�̓`�F�b�N���ɃR�[�������B�j
            //�P�jthis.calc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`()���R�[������B
            //[����]
            //�������ϑ����O���t�����l���t���O�Ffalse
            List l_lisTradingpowerUpdResultEquityIncUnexecSellOrders =
                l_tradingPowerUpd.calcTradingpowerUpdResultEquityIncUnexecSellOrder();

            //���Y�]�͏��<�����ڋq>(List, �]�͌v�Z����, double)
            // calc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`()�̖߂�l
            //�]�͌v�Z���� = create�]�͌v�Z����<�W��>()�̖߂�l
            //���񒍕��o���z = ����.���������e[0].get�T�Z���
            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity =
                new WEB3TPTradingPowerCalcEquity(
                    l_lisTradingpowerUpdResultEquityIncUnexecSellOrders,
                    l_tpCalcCondition,
                    l_newOrderSpecs[0].getEstimatedPrice());

            //�a����s���z���v�Z����B
            double l_dblActualPaymentBalance = 0.0D;
            for(int index = 0; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //���o�\����(T+0..5)
                l_dblActualPaymentBalance = Math.min(
                    l_tpTradingPowerCalcEquity.calcActualPaymentBalance(index),
                    l_dblActualPaymentBalance);
            }

            //����t���[
            if (l_dblActualPaymentBalance > 0
                || GtlUtils.Double.isZero(l_dblActualPaymentBalance))
            {
                //calc�]�͍X�V���e<�����ڋq>( )
                List l_lisTradingpowerUpdResultEquitys =
                    l_tradingPowerUpd.calcTradingpowerUpdResultEquity();

                //���Y�]�͏��<�����ڋq>(List, �]�͌v�Z����)
                //�]�͌v�Z����Params<�����ڋq>�Fcalc�]�͍X�V���e<�����ڋq>()�̖߂�l
                //�]�͌v�Z�����Fcreate�]�͌v�Z����<�W��>()�̖߂�l
                WEB3TPTradingPowerCalcEquity l_tradingPowerCalcEquity =
                    new WEB3TPTradingPowerCalcEquity(
                        l_lisTradingpowerUpdResultEquitys,
                        l_tpCalcCondition);

                //calc�K�p���̑����i���t�\�z(OrderTypeEnum, int)
                //������ʁ@@=�@@701�F�O������
                //��� = calc�w���()�̖߂�l
                WEB3TPCalcResult l_calcResult =
                    l_tradingPowerCalcEquity.calcAppliedOtherTradingPower(
                        OrderTypeEnum.FEQ_BUY, l_intSpecifiedPoint);

                //set����t���O(boolean)
                //boolean�F true
                l_tpResult.setResultFlg(true);

                //set�������\�z(double)
                //����\�z�F �]�͌v�Z����.����\�z
                //���@@�]�͌v�Z���ʁFcalc�K�p���̑����i���t�\�z()�̖߂�l
                l_tpResult.setTradingPower(l_calcResult.tradingPower);

                //set����]�̓G���[���(����]�̓G���[���)
                //����]�̓G���[���Fnull
                l_tpResult.setTpErrorInfo(null);

                //����t���[
                if (l_blnUpdateFlg)
                {
                    //save�]�͍X�V���e<�����ڋq>
                    //List�F�]�͍X�V.calc�]�͍X�V���e<�����ڋq>()
                    l_tradingPowerUpd.saveTradingpowerUpdResultEquity(l_lisTradingpowerUpdResultEquitys);
                }
            }
            else
            {
                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }
        else
        {
            //find�]�͌v�Z����<�M�p�ڋq>�`�����h�c�w��`(����ID : long)
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��<�M�p�ڋq>(List, �g�p�\�������, �]�͌v�Z����)
            //�]�͌v�Z����Params�Ffind�]�͌v�Z����<�M�p�ڋq>()�̖߂�l
            //�]�͌v�Z���� = create�]�͌v�Z����<�W��>()�̖߂�l
            WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_tpCalcCondition);

            //get������
            Date l_datOrderBizDate = l_newOrderSpecs[0].getOrderBizDate();
            int l_intOrderBizDate = l_tpCalcCondition.calcSpecifiedPoint(l_datOrderBizDate);

            //get��n��
            int l_intDeliveryDate = l_tpCalcCondition.calcSpecifiedPoint(l_datDeliveryDate);

            //calc�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>(OrderCategEnum, int, int)
            //������� = 701�F�O������
            //������ = get�������̖߂�l
            //��n�� = get��n���̖߂�l
            WEB3TPCalcResult l_tpCalcResult =
                l_tradingPowerCalcMargin.calcMarginCallPowerForCheck(
                    OrderTypeEnum.FEQ_BUY, l_intOrderBizDate, l_intDeliveryDate);

            //����t���[
            if (l_tpCalcResult !=null && l_tpCalcResult.tradingPower < 0)
            {
                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            //calc�]�͍X�V���e<�M�p�ڋq>�`����蔄�t�����l���`
            List l_lisTradingpowerUpdResultMarginIncUnexecSellOrders =
                l_tradingPowerUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            //���Y�]�͏��<�M�p�ڋq>(List, �]�͌v�Z����)
            //�]�͌v�Z����Params<�M�p�ڋq>�Fcalc�]�͍X�V���e<�M�p�ڋq>�`����蔄�t�����l���`()
            //�]�͌v�Z�����Fcreate�]�͌v�Z����<�W��>()
            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(
                    l_lisTradingpowerUpdResultMarginIncUnexecSellOrders, l_tpCalcCondition);

            //�a����s���z���v�Z����B
            double l_dblActualPaymentBalance = 0.0D;
            /*
             * �a����s���z���v�Z����
             * 
             *  �@@�a����s���z = MIN( �a��������]��(T+0..T+��n��-1), 
             *                       ���o�\����(T+��n��..5), 
             *                       �ۏ؋����o�]��(T+��n��..5) )
             */
            //T+0..T+��n��-1
            for(int index = 0; index < l_intDeliveryDate; index++)
            {
                //�a��������]��(T+0..T+��n��-1)
                l_dblActualPaymentBalance = Math.min(
                    l_tpTradingPowerCalcMargin.calcAccountBalanceDemandPower(index),
                    l_dblActualPaymentBalance);
            }
            //T+��n��..T+5
            for(int index = l_intDeliveryDate; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //���o�\����(T+��n��..5)
                l_dblActualPaymentBalance = Math.min(
                    l_tpTradingPowerCalcMargin.calcActualPaymentBalance(index),
                    l_dblActualPaymentBalance);

                //�ۏ؋����o�]��(T+��n��..5)
                l_dblActualPaymentBalance = Math.min(
                    l_tpTradingPowerCalcMargin.calcMarginDrawPower(index),
                    l_dblActualPaymentBalance);
            }

            //calc��K��(�⏕����, long, ���Y�]�͏��<�M�p�ڋq>, �]�͍X�V, OrderTypeEnum, long, Date)
            //�⏕�����F����.�⏕����
            //long�F-1
            //���Y�]�͏��<�M�p�ڋq>�F�����������Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g
            //�]�͍X�V�F���������]�͍X�V�I�u�W�F�N�g
            //OrderTypeEnum�F�O������
            //long�F-1
            //Date�Fnull
            WEB3TPMarginSecurityInfo[] l_marginSecurityInfos =
                this.calcMarginSecurity(
                    l_subAccount,
                    -1,
                    l_tpTradingPowerCalcMargin,
                    l_tradingPowerUpd,
                    OrderTypeEnum.FEQ_BUY,
                    -1,
                    null);

            //����t���[
            if (l_marginSecurityInfos == null
                && (l_dblActualPaymentBalance > 0
                    || GtlUtils.Double.isZero(l_dblActualPaymentBalance)))
            {
                //calc�]�͍X�V���e<�M�p�ڋq>
                List l_lisTradingpowerUpdResultMargin =
                    l_tradingPowerUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

                //���Y�]�͏��<�M�p�ڋq>(List, �]�͌v�Z����)
                //�]�͌v�Z����Params<�M�p�ڋq>�Fcalc�]�͍X�V���e<�M�p�ڋq>()
                //�]�͌v�Z�����Fcreate�]�͌v�Z����<�W��>()
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(
                        l_lisTradingpowerUpdResultMargin, l_tpCalcCondition);

                //calc�K�p���̑����i���t�\�z(OrderTypeEnum, int)
                //������ʁ@@=�@@701�F�O������
                //��� = calc�w���()�̖߂�l
                WEB3TPCalcResult l_calcResult = l_calcResult =
                    l_calcMargin.calcAppliedOtherTradingPower(
                        OrderTypeEnum.FEQ_BUY, l_intSpecifiedPoint);

                //set����t���O(boolean)
                //boolean�F true
                l_tpResult.setResultFlg(true);

                //set�������\�z(double)
                //����\�z�F �]�͌v�Z����.����\�z
                //���@@�]�͌v�Z���ʁFcalc�K�p���̑����i���t�\�z()�̖߂�l
                l_tpResult.setTradingPower(l_calcResult.tradingPower);

                //set����]�̓G���[���(����]�̓G���[���)
                //����]�̓G���[���Fnull
                l_tpResult.setTpErrorInfo(null);

                //����t���[
                if (l_blnUpdateFlg)
                {
                    //save�]�͍X�V���e<�M�p�ڋq>
                    //List�F�]�͍X�V.calc�]�͍X�V���e<�M�p�ڋq>()
                    l_tradingPowerUpd.saveTradingpowerUpdResultMargin(l_lisTradingpowerUpdResultMargin);
                }
            }
            else
            {
                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);

                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                if (l_dblActualPaymentBalance < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;                    
                }
                else
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                }
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecurityInfos;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (validate����]��<�O���������t>)<BR>
     * <BR>
     * �O���������t�ɂ����āA���������e����荞�ݗa����`�F�b�N�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)validate����]��<�O���������t>�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_newOrderSpecs - (���������e)<BR>
     * ���������e<BR>
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O<BR>
     * true�̎��A�]�͍Čv�Z���������{����<BR>
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPowerFeqSell(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPowerFeqSell("
            + "WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null
            || l_newOrderSpecs == null
            || l_newOrderSpecs.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //getAccountId
        long l_lngAccountId = l_subAccount.getAccountId();

        //getMainAccount
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�M�p�����J��(�ٍϋ敪 : String)
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountOpen =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //create�]�͌v�Z����<�W��>(�⏕����)
        WEB3TPCalcCondition l_tpCalcCondition =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //get����ID
        long l_lngProductId = l_newOrderSpecs[0].getProductId();

        //���t��]�͍X�V(long, boolean, �]�͌v�Z����, ���������e[], long)
        //long�F�⏕����.getAccountId() �̖߂�l
        //boolean�F�ڋq.is�M�p�����J��() �̖߂�l
        //�]�͌v�Z�����Fcreate�]�͌v�Z����<�W��>()�̖߂�l
        //���������e[]�Fnull
        //����ID�F���������e.get����ID()�̖߂�l
        WEB3TPTradingPowerUpdAfterSell l_tradingPowerUpdAfterSellOrderFront =
            new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnIsMarginAccountOpen,
                l_tpCalcCondition,
                null,
                l_lngProductId);

        //���t��]�͍X�V(long, boolean, �]�͌v�Z����, ���������e[], long)
        //long�F�⏕����.getAccountId() �̖߂�l
        //boolean�F�ڋq.is�M�p�����J��() �̖߂�l
        //�]�͌v�Z�����Fcreate�]�͌v�Z����<�W��>()�̖߂�l
        //���������e[]�F����.���������e
        //����ID�F���������e.get����ID()�̖߂�l
        WEB3TPTradingPowerUpdAfterSell l_tradingPowerUpdAfterSellOrderBack =
            new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnIsMarginAccountOpen,
                l_tpCalcCondition,
                l_newOrderSpecs,
                l_lngProductId);

        //����]�͌���
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        //(*����t���[�F�����ڋq�i�ڋq.is�M�p�����J��() == false�j�̏ꍇ
        if (!l_blnIsMarginAccountOpen)
        {
            //get��n��
            Date l_datDeliveryDate = l_newOrderSpecs[0].getDeliveryDate();

            //calc�w���(Date)
            //��n���F����.���������e.get��n��()�̖߂�l
            int l_intSpecifiedPoint = l_tpCalcCondition.calcSpecifiedPoint(l_datDeliveryDate);

            //calc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`
            List l_lisTradingpowerUpdResultEquityIncUnexecSellOrderFronts =
                l_tradingPowerUpdAfterSellOrderFront.calcTradingpowerUpdResultEquityIncUnexecSellOrder();

            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquityFront =
                new WEB3TPTradingPowerCalcEquity(
                    l_lisTradingpowerUpdResultEquityIncUnexecSellOrderFronts,
                    l_tpCalcCondition);
            //calc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`
            List l_lisTradingpowerUpdResultEquityIncUnexecSellOrderBacks =
                l_tradingPowerUpdAfterSellOrderBack.calcTradingpowerUpdResultEquityIncUnexecSellOrder();

            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquityBack =
                new WEB3TPTradingPowerCalcEquity(
                    l_lisTradingpowerUpdResultEquityIncUnexecSellOrderBacks,
                    l_tpCalcCondition);
            //����t���[�F���OK�̏ꍇ
            // (�u�i������j���o�\����(T+��n��) >= 0�v �܂���
            //�u�i������j���o�\����(T+��n��) >= �i�����O�j���o�\����(T+��n��)�v)�̏ꍇ
            double l_dblActualPayBalFront =
                l_tpTradingPowerCalcEquityFront.calcActualPaymentBalance(l_intSpecifiedPoint);
            double l_dblActualPayBalBack =
                l_tpTradingPowerCalcEquityBack.calcActualPaymentBalance(l_intSpecifiedPoint);
            if (l_dblActualPayBalBack > 0
                || GtlUtils.Double.isZero(l_dblActualPayBalBack)
                || l_dblActualPayBalBack > l_dblActualPayBalFront
                || GtlUtils.Double.isEqual(l_dblActualPayBalBack, l_dblActualPayBalFront))
            {
                //����t���O
                l_tpResult.setResultFlg(true);
                //����\�z
                l_tpResult.setTradingPower(0);
                //����G���[���
                l_tpResult.setTpErrorInfo(null);
                //���ӕ����\���敪
                l_tpResult.setAttentionObjectionType(null);

                //����t���[�Fsubmit�����i����.�X�V�t���O == true�j�̏ꍇ
                if (l_blnUpdateFlg)
                {
                    //�]�͍X�V(long, boolean, �]�͌v�Z����, ���������e[])
                    //long�F�⏕����.getAccountId() �̖߂�l
                    //boolean�F�ڋq.is�M�p�����J��() �̖߂�l
                    //�]�͌v�Z�����Fcreate�]�͌v�Z����<�W��>()�̖߂�l
                    //���������e[]�F����.���������e
                    WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId,
                        l_blnIsMarginAccountOpen,
                        l_tpCalcCondition,
                        l_newOrderSpecs);

                    //calc�]�͍X�V���e<�����ڋq>
                    List l_lisTradingpowerUpdResultEquitys =
                        l_tradingPowerUpd.calcTradingpowerUpdResultEquity();

                    // save�]�͍X�V���e<�����ڋq>(List)
                    //�]�͍X�V���e�F�]�͍X�V.calc�]�͍X�V���e<�����ڋq>()�̖߂�l
                    l_tradingPowerUpd.saveTradingpowerUpdResultEquity(
                        l_lisTradingpowerUpdResultEquitys);
                }
            }
            else
            {
                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);
                //���ӕ����\���敪
                l_tpResult.setAttentionObjectionType(null);
                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }
        else
        {
            //get��n��
            Date l_datDeliveryDate = l_newOrderSpecs[0].getDeliveryDate();

            //calc�w���(Date)
            //��n���F����.���������e.get��n��()�̖߂�l
            int l_intSpecifiedPoint = l_tpCalcCondition.calcSpecifiedPoint(l_datDeliveryDate);

            //calc�]�͍X�V���e<�M�p�ڋq>�`����蔄�t�����l���`
            List l_lisTradingpowerUpdResultMarginIncUnexecSellOrderFronts =
                l_tradingPowerUpdAfterSellOrderFront.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMarginFront =
                new WEB3TPTradingPowerCalcMargin(
                    l_lisTradingpowerUpdResultMarginIncUnexecSellOrderFronts,
                    l_tpCalcCondition);
            //calc�]�͍X�V���e<�M�p�ڋq>�`����蔄�t�����l���`
            List l_lisTradingpowerUpdResultMarginIncUnexecSellOrderBacks =
                l_tradingPowerUpdAfterSellOrderBack.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMarginBack =
                new WEB3TPTradingPowerCalcMargin(
                    l_lisTradingpowerUpdResultMarginIncUnexecSellOrderBacks,
                    l_tpCalcCondition);

            //����t���[�F���OK�̏ꍇ
            // (�u�i������j���o�\����(T+��n��) >= 0�v �܂���
            //�u�i������j���o�\����(T+��n��) >= �i�����O�j���o�\����(T+��n��)�v)�̏ꍇ
            double l_dblActualPayBalFront =
                l_tpTradingPowerCalcMarginFront.calcActualPaymentBalance(l_intSpecifiedPoint);
            double l_dblActualPayBalBack =
                l_tpTradingPowerCalcMarginBack.calcActualPaymentBalance(l_intSpecifiedPoint);
            if (l_dblActualPayBalBack > 0
                || GtlUtils.Double.isZero(l_dblActualPayBalBack)
                || l_dblActualPayBalBack > l_dblActualPayBalFront
                || GtlUtils.Double.isEqual(l_dblActualPayBalBack, l_dblActualPayBalFront))
            {
                //����t���O
                l_tpResult.setResultFlg(true);
                //����\�z
                l_tpResult.setTradingPower(0);
                //����G���[���
                l_tpResult.setTpErrorInfo(null);
                //���ӕ����\���敪
                l_tpResult.setAttentionObjectionType(null);

                //����t���[�Fsubmit�����i����.�X�V�t���O == true�j�̏ꍇ
                if (l_blnUpdateFlg)
                {
                    //�]�͍X�V(long, boolean, �]�͌v�Z����, ���������e[])
                    //long�F�⏕����.getAccountId() �̖߂�l
                    //boolean�F�ڋq.is�M�p�����J��() �̖߂�l
                    //�]�͌v�Z�����Fcreate�]�͌v�Z����<�W��>()�̖߂�l
                    //���������e[]�F����.���������e
                    WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId,
                        l_blnIsMarginAccountOpen,
                        l_tpCalcCondition,
                        l_newOrderSpecs);

                    //calc�]�͍X�V���e<�M�p�ڋq>(String)
                    //String�F0:�ʏ�
                    List l_lisTradingpowerUpdResultMargins =
                        l_tradingPowerUpd.calcTradingpowerUpdResultMargin(
                            WEB3TPMarkToMarketDivDef.NORMAL);

                    // save�]�͍X�V���e<�M�p�ڋq>(List)
                    //�]�͍X�V���e�F�]�͍X�V.calc�]�͍X�V���e<�M�p�ڋq>()�̖߂�l
                    l_tradingPowerUpd.saveTradingpowerUpdResultMargin(
                        l_lisTradingpowerUpdResultMargins);
                }
            }
            else
            {
                //����t���O���Z�b�g
                l_tpResult.setResultFlg(false);
                //����\�z���Z�b�g
                l_tpResult.setTradingPower(0);
                //���ӕ����\���敪
                l_tpResult.setAttentionObjectionType(null);
                /*
                 * ����]�̓G���[���𐶐�
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //����]�̓G���[�����Z�b�g
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (get�o���\�z�`�o�����͉�ʕ\���p�`)<BR>
     * <BR>
     * �o���\�z���擾����B<BR>
     * <BR>
     * �P�j�o���\�z���擾����B<BR>
     * �@@����]�̓T�[�r�XImpl.get�o���\�z�`0�␳�����`()���R�[��<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�⏕�����F����.�⏕����<BR>
     * �@@�@@��n���F����.��n��<BR>
     * �@@�@@�Ăяo�����敪�F3<BR>
     * <BR>
     * �Q�j�o���\�z��ԋp����B<BR>
     * �@@���P�j�̖߂�l < 0�̏ꍇ<BR>
     * �@@�@@�ԋp�l�F0<BR>
     * <BR>
     * �@@���ȊO�i �P�j�̖߂�l >= 0�j<BR>
     * �@@�@@�ԋp�l�F"�P�j�̖߂�l"<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getPaymentTradingPowerAioCashoutInput(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPaymentTradingPowerAioCashoutInput("
            + "WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�o���\�z���擾����B
        double l_tradingPower = this.getPaymentTradingPowerForCheck(
            l_subAccount, l_datDeliveryDate, 3);

        if (l_tradingPower < 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_tradingPower;
        }
    }
}
@
