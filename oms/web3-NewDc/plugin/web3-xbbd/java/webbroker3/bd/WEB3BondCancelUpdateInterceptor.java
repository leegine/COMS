head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������X�V�C���^�Z�v�^(WEB3BondCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 ������ (���u) �V�K�쐬
                   2006/10/08 ��іQ(���u) �c�a�X�V�d�lNo.013
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;


/**
 * (������X�V�C���^�Z�v�^)
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondCancelUpdateInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondCancelUpdateInterceptor.class);

    /**
     * (�㗝���͎�)<BR>
     * �㗝���͎�<BR>
     * �i���㗝���͂̏ꍇ�̂ݐݒ肳���j
     */
    private WEB3GentradeTrader trader;

     /**
     * (������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^
     * @@roseuid 44DFD390032C
     */
    public WEB3BondCancelUpdateInterceptor()
    {

    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �����P�ʃe�[�u���X�V <BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * ���ڐݒ���e�́ADB�X�V�d�l <BR>
     * �u���_�������P�ʃe�[�u��DB�X�V�d�l.xls�v <BR>
     * <BR>
     * �Q�ƁB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        //�������敪    order_exec_status
        //2�F�����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.CANCELED);

        //�����o�H�敪     order_root_div
        //�Z�b�V��������擾���������o�H�敪
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        l_params.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //host���M�敪    host_send_div
        //2:���M�s�v
        l_params.setHostSendDiv(WEB3HostSendDivDef.NOT_SEND);

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (�i���������j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾 <BR>
     * <BR>
     * �����̒�������Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g <BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e�́A <BR>
     * �@@�u���_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderActionParams
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderActionParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        OrderManager l_orderManager = l_tradingModule.getOrderManager();

        BondOrderUnitRow l_orderUnitRow = null;
        try
        {
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_params.getOrderUnitId());
            l_orderUnitRow = (BondOrderUnitRow) l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //����҂h�c    trader_id
        //�������P�ʃe�[�u��.�����ID
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_params.setTraderId(null);
        }
        else
        {
            l_params.setTraderId(l_orderUnitRow.getTraderId());
        }

        //���    deal_type
        //�������P�ʃe�[�u��.���
        l_params.setDealType(l_orderUnitRow.getDealType());

        //�����P��    price
        //�������P�ʃe�[�u��.�����P��
        if (l_orderUnitRow.getPriceIsNull())
        {
            l_params.setPrice(null);
        }
        else
        {
            l_params.setPrice(l_orderUnitRow.getPrice());
        }

        //�w�l    limit_price
        //�������P�ʃe�[�u��.�w�l
        if (l_orderUnitRow.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(null);
        }
        else
        {
            l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());
        }

        //�������敪  order_exec_status
        //�������P�ʃe�[�u��.�������敪
        l_params.setOrderExecStatus(l_orderUnitRow.getOrderExecStatus());

        //����        exec_date
        //�������P�ʃe�[�u��.����
        l_params.setExecDate(l_orderUnitRow.getExecDate());

        //���n����        foreign_exec_date
        //�������P�ʃe�[�u��.���n����
        l_params.setForeignExecDate(l_orderUnitRow.getForeignExecDate());

        //��n��        delivery_date
        //�������P�ʃe�[�u��.��n��
        l_params.setDeliveryDate(l_orderUnitRow.getDeliveryDate());

        //���n��n��        foreign_delivery_date
        //�������P�ʃe�[�u��.���n��n��
        l_params.setForeignDeliveryDate(l_orderUnitRow.getForeignDeliveryDate());

        //������  payment_date
        //�������P�ʃe�[�u��.������
        l_params.setPaymentDate(l_orderUnitRow.getPaymentDate());

        //��בփ��[�g        base_fx_rate
        //�������P�ʃe�[�u��.��בփ��[�g
        if (l_orderUnitRow.getBaseFxRateIsNull())
        {
            l_params.setBaseFxRate(null);
        }
        else
        {
            l_params.setBaseFxRate(l_orderUnitRow.getBaseFxRate());
        }

        //���בփ��[�g        exec_fx_rate
        //�������P�ʃe�[�u��.���בփ��[�g
        if (l_orderUnitRow.getExecFxRateIsNull())
        {
            l_params.setExecFxRate(null);
        }
        else
        {
            l_params.setExecFxRate(l_orderUnitRow.getExecFxRate());
        }

        //��������i�~�݁j        trading_price
        //�������P�ʃe�[�u��.��������i�~�݁j
        if (l_orderUnitRow.getTradingPriceIsNull())
        {
            l_params.setTradingPrice(null);
        }
        else
        {
            l_params.setTradingPrice(l_orderUnitRow.getTradingPrice());
        }

        //��������i�O�݁j        foreign_trading_price
        //�������P�ʃe�[�u��.��������i�O�݁j
        if (l_orderUnitRow.getForeignTradingPriceIsNull())
        {
            l_params.setForeignTradingPrice(null);
        }
        else
        {
            l_params.setForeignTradingPrice(l_orderUnitRow.getForeignTradingPrice());
        }

        //�o�ߗ��q�i�~�݁j        accrued_interest
        //�������P�ʃe�[�u��.�o�ߗ��q�i�~�݁j
        if (l_orderUnitRow.getAccruedInterestIsNull())
        {
            l_params.setAccruedInterest(null);
        }
        else
        {
            l_params.setAccruedInterest(l_orderUnitRow.getAccruedInterest());
        }

        //�o�ߗ��q�i�O�݁j        foreign_accrued_interest
        //�������P�ʃe�[�u��.�o�ߗ��q�i�O�݁j
        if (l_orderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_params.setForeignAccruedInterest(null);
        }
        else
        {
            l_params.setForeignAccruedInterest(l_orderUnitRow.getForeignAccruedInterest());
        }

        //��n����i�~�݁j        estimated_price
        //�������P�ʃe�[�u��.��n����i�~�݁j
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(null);
        }
        else
        {
            l_params.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
        }

        //��n����i�O�݁j        foreign_estimated_price
        //�������P�ʃe�[�u��.��n����i�O�݁j
        if (l_orderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_params.setForeignEstimatedPrice(null);
        }
        else
        {
            l_params.setForeignEstimatedPrice(l_orderUnitRow.getForeignEstimatedPrice());
        }

        //���r���������z   adjustment_before_maturity
        //�������P�ʃe�[�u��.���r���������z
        if (l_orderUnitRow.getAdjustmentBeforeMaturityIsNull())
        {
            l_params.setAdjustmentBeforeMaturity(null);
        }
        else
        {
            l_params.setAdjustmentBeforeMaturity(
                l_orderUnitRow.getAdjustmentBeforeMaturity());
        }

        //�o�ߓ���        elapsed_days
        //�������P�ʃe�[�u��.�o�ߓ���
        if (l_orderUnitRow.getElapsedDaysIsNull())
        {
            l_params.setElapsedDays(null);
        }
        else
        {
            l_params.setElapsedDays(l_orderUnitRow.getElapsedDays());
        }

        //�����        calc_base_days
        //�������P�ʃe�[�u��.�����
        if (l_orderUnitRow.getCalcBaseDaysIsNull())
        {
            l_params.setCalcBaseDays(null);
        }
        else
        {
            l_params.setCalcBaseDays(l_orderUnitRow.getCalcBaseDays());
        }

        //�J�X�g�f�B�A���R�[�h        custodian_code
        //�������P�ʃe�[�u��.�J�X�g�f�B�A���R�[�h
        l_params.setCustodianCode(l_orderUnitRow.getCustodianCode());

        //�����o�H�敪        order_root_div
        //�������P�ʃe�[�u��.�����o�H�敪
        l_params.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());

        //�Ǘ��҃R�[�h        administrator_code
        //�������P�ʃe�[�u��.�Ǘ��҃R�[�h
        l_params.setAdministratorCode(l_orderUnitRow.getAdministratorCode());

        //�����G���[���R�R�[�h        error_reason_code
        //�������P�ʃe�[�u��.�����G���[���R�R�[�h
        l_params.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (get�㗝���͎�)<BR>
     * �㗝���͎҂��擾����B
     * @@return WEB3GentradeTrader
     * @@roseuid 44E441C60077
     */
    public WEB3GentradeTrader getTrader()
    {
        return trader;
    }

    /**
     * (set�㗝���͎�)<BR>
     * �㗝���͎҂��Z�b�g����B
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎�
     * @@roseuid 44E442020362
     */
    public void setTrader(WEB3GentradeTrader l_trader)
    {
        this.trader = l_trader;
    }

    public BondOrderExecutionParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, BondOrderExecutionParams arg2)
    {
        return null;
    }

    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1)
    {
        return null;
    }
}
@
