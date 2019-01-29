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
filename	WEB3BondOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������X�V�C���^�Z�v�^(WEB3BondOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/08 ������ (���u) �V�K�쐬
                      : 2006/10/08 �����F �c�a�X�V�d�lNo.013
*/

package webbroker3.bd;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3HostReflectDivDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�������X�V�C���^�Z�v�^)
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondOrderUpdateInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderUpdateInterceptor.class);

    /**
     * (���������)<BR>
     * ���������
     */
    private WEB3BondExecuteDateInfo bondExecuteDateInfo;

    /**
     * (����n����v�Z����)<BR>
     * ����n����v�Z����
     */
    private WEB3BondEstimatedPriceCalcResult bondEstimatedPriceCalcResult;

    /**
     * (�g�����V�K�������e)<BR>
     * �g�����V�K�������e
     */
    private WEB3BondNewOrderSpec bondNewOrderSpec;

    /**
     * (�������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^
     * @@roseuid 44DFD2880148
     */
    public WEB3BondOrderUpdateInterceptor()
    {

    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �����P�ʃe�[�u���X�V <BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * ���ڐݒ���e�́ADB�X�V�d�l <BR>
     * �u����E���t_�������P�ʃe�[�u��DB�X�V�d�l.xls�v <BR>
     * �u���p_�������P�ʃe�[�u��DB�X�V�d�l.xls�v<BR>
     * <BR>
     * �Q�ƁB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44DFD27500FA
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

        //��� deal_type   (����E���t)
        if (OrderTypeEnum.BOND_BUY.equals(l_params.getOrderType()))
        {
            if (this.bondNewOrderSpec.getBondOrderTypeJudge().isBuyOrder())
            {
                //���V�K�������e.get��������ʔ���.is���t���� == true �̏ꍇ�A92:�����d�؎��
                l_params.setDealType(WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);
            }
            else if (this.bondNewOrderSpec.getBondOrderTypeJudge().isRecruitOrder())
            {
                //���V�K�������e.get��������ʔ���.is���咍�� == true �̏ꍇ�A����̏ꍇ35:��W���
                l_params.setDealType(WEB3DealTypeDef.RECRUIT_TRADING);
            }
        }

        //���   deal_type   (���p)
        //92:�����d�؎��
        else if (OrderTypeEnum.BOND_SELL.equals(l_params.getOrderType()))
        {
            l_params.setDealType(WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);
        }

        //��n��  delivery_date
        //���������.get��n���i�j
        l_params.setDeliveryDate(this.bondExecuteDateInfo.getDeliveryDate());

        //���n��n��  foreign_delivery_date
        //���������.get���n��n���i�j
        l_params.setForeignDeliveryDate(this.bondExecuteDateInfo.getForeignDeliveryDate());

        //�������b�N�敪  lock_status
        //1�F������
        l_params.setLockStatus(WEB3LockStatusDef.RELEASING);

        //�������敪  order_exec_status
        //0�F�����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);

        //������       biz_date
        //���������.get�������i�j
        l_params.setBizDate(
            WEB3DateUtility.formatDate(this.bondExecuteDateInfo.getBizDate(), "yyyyMMdd"));

        //���n������   foreign_biz_date
        //���������.get���n�������i�j
        l_params.setForeignBizDate(
            WEB3DateUtility.formatDate(this.bondExecuteDateInfo.getForeignBizDate(), "yyyyMMdd"));

        //���񒍕��̒����`���l��   order_chanel
        //�Z�b�V�������擾���������`���l��
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        l_params.setOrderChanel(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

        //�󒍓���   received_date_time
        //�T�[�o���ŃT�[�r�X���N�����ꂽ���ԁi�v�Z�����i���ʁj(*2) �������t�@@���Q�Ɓj
        //(*2) �������t
        //�v���Z�X�J�n���_�̓��t�E���Ԃ��X���b�h�ɕۑ����A�e�����ɂė��p����B
        //�iThreadLocalSystemAttributesRegistry#attributes("xblocks.gtl.attributes.system_timestamp")�j
        final String STR_ATTRIBUTE_NAME = "xblocks.gtl.attributes.systemtimestamp";

        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(STR_ATTRIBUTE_NAME);
        l_params.setReceivedDateTime(l_tsOrderAcceptDate);

        //���҃R�[�h�iSONAR�j   sonar_trader_code
        //�ڋq.���҃R�[�h�iSONAR�j
        // * SONAR�ŊǗ����Ă���ڋq�̈��ҁj
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;

        try
        {
            l_mainAccount =
                l_accountManager.getMainAccount(l_params.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        l_params.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

        //�����P��   price
        //����n����v�Z����.get�P���i�j
        if (this.bondEstimatedPriceCalcResult.getPrice() != null)
        {
            l_params.setPrice(this.bondEstimatedPriceCalcResult.getPrice().doubleValue());
        }

        //���ʃR�[�h    order_request_number
        //�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h( )
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        try
        {
            String l_strNewNumber =
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    ProductTypeEnum.BOND);
            l_params.setOrderRequestNumber(l_strNewNumber);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���^�C�v   bond_type
        //���V�K�������e.getProductCode()�ɊY�����������.get���^�C�v�i�j
        ProductManager l_productManager =
            l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)l_productManager.getProduct(l_params.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error  in  getProduct()__ ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        BondProductRow l_bondProductRow =
            (BondProductRow)l_bondProduct.getDataSourceObject();

        l_params.setBondType(l_bondProductRow.getBondType());

        //�ʉ݃R�[�h     currency_code
        //���V�K�������e.getProductCode()�ɊY�����������.get�ʉ݃R�[�h�i�j
        l_params.setCurrencyCode(l_bondProductRow.getCurrencyCode());

        //���ϋ敪      settlement_div
        //���V�K�������e.���ϋ敪
        l_params.setSettlementDiv(this.bondNewOrderSpec.getSettlementDiv());

        //�������敪        auto_exec_div
        //���V�K�������e.getProductCode()�ɊY�����������.get�������敪�i�j
        l_params.setAutoExecDiv(l_bondProductRow.getAutoExecDiv());

        //���P��      executed_price
        //null
        l_params.setExecutedPrice(null);

        //��בփ��[�g       base_fx_rate
        //����n����v�Z����.get�בփ��[�g�i�j
        if (this.bondEstimatedPriceCalcResult.getFxRate() != null)
        {
            l_params.setBaseFxRate(this.bondEstimatedPriceCalcResult.getFxRate().doubleValue());
        }

        //���בփ��[�g    exec_fx_rate
        //null
        l_params.setExecFxRate(null);

        //��������i�~�݁j    trading_price
        //����n����v�Z����.get��������i�~�݁j�i�j
        if (this.bondEstimatedPriceCalcResult.getTradingPrice() != null)
        {
            l_params.setTradingPrice(
                this.bondEstimatedPriceCalcResult.getTradingPrice().doubleValue());
        }

        //��������i�O�݁j    foreign_trading_price
        //����n����v�Z����.get��������i�O�݁j�i�j
        if (this.bondEstimatedPriceCalcResult.getForeignTradePrice() != null)
        {
            l_params.setForeignTradingPrice(
                this.bondEstimatedPriceCalcResult.getForeignTradePrice().doubleValue());
        }

        //�o�ߗ��q�i�~�݁j    accrued_interest
        //����n����v�Z����.get�o�ߗ��q�i�~�݁j�i�j
        if (this.bondEstimatedPriceCalcResult.getAccruedInterest() != null)
        {
            l_params.setAccruedInterest(
                this.bondEstimatedPriceCalcResult.getAccruedInterest().doubleValue());
        }

        //�o�ߗ��q�i�O�݁j    foreign_accrued_interest
        //����n����v�Z����.get�o�ߗ��q�i�O�݁j�i�j
        if (this.bondEstimatedPriceCalcResult.getForeignAccruedInterest() != null)
        {
            l_params.setForeignAccruedInterest(
                this.bondEstimatedPriceCalcResult.getForeignAccruedInterest().doubleValue());
        }

        //��n����i�~�݁j    estimated_price
        //����n����v�Z����.get��n����i�~�݁j�i�j
        if (this.bondEstimatedPriceCalcResult.getEstimatedPrice() != null)
        {
            l_params.setEstimatedPrice(
                this.bondEstimatedPriceCalcResult.getEstimatedPrice().doubleValue());
        }

        //��n����i�O�݁j    foreign_estimated_price
        //����n����v�Z����.get��n����i�O�݁j�i�j
        if (this.bondEstimatedPriceCalcResult.getForeignEstimatedPrice() != null)
        {
            l_params.setForeignEstimatedPrice(
                this.bondEstimatedPriceCalcResult.getForeignEstimatedPrice().doubleValue());
        }

        //���r���������z adjustment_before_maturity
        //null
        l_params.setAdjustmentBeforeMaturity(null);

        //�o�ߓ���      elapsed_days
        //����n����v�Z����.get�o�ߓ����i�j
        l_params.setElapsedDays(
            this.bondEstimatedPriceCalcResult.getElapsedDays());


        //�����      calc_base_days
        //����n����v�Z����.get������i�j
        l_params.setCalcBaseDays(
            this.bondEstimatedPriceCalcResult.getCalcBaseDays());


        //����        exec_date
        //���������.get�����i�j
        l_params.setExecDate(this.bondExecuteDateInfo.getExecuteDate());

        //���n����     foreign_exec_date
        //���������.get���n�����i�j
        l_params.setForeignExecDate(this.bondExecuteDateInfo.getForeignExecuteDate());

        //������ payment_date
        //���������.get�������i�j
        l_params.setPaymentDate(this.bondExecuteDateInfo.getPaymentDate());

        //�J�X�g�f�B�A���R�[�h    custodian_code
        //���V�K�������e.getProductCode()�ɊY�����������.get�J�X�g�f�B�A���R�[�h�i�j
        l_params.setCustodianCode(l_bondProductRow.getCustodianCode());

        //�����o�H�敪        order_root_div
        //�Z�b�V�������擾���������o�H�敪
        l_params.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //host���M�敪      host_send_div
        //0�F�����M
        l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);

        //�Ǘ��҃R�[�h        administrator_code
        //null
        l_params.setAdministratorCode(null);

        //�����G���[���R�R�[�h    error_reason_code
        //null
        l_params.setErrorReasonCode(null);

        //��蕪host���f�敪 exec_host_reflect_div
        //0�F�����f
        l_params.setExecHostReflectDiv(WEB3HostReflectDivDef.NOT_REFLECT);

        //�����host���f�敪 cancel_host_reflect_div
        //0�F�����f
        l_params.setCancelHostReflectDiv(WEB3HostReflectDivDef.NOT_REFLECT);

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
     * �����̒�������Params.����ID�A<BR>
     * �����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g <BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e�́A <BR>
     * �@@�u����E���t_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u���p_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderActionParams
     * @@roseuid 44DFD2A70251
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

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        OrderManager l_orderMgr = l_tradingMod.getOrderManager();
        BondOrderUnitRow l_orderUnitRow = null;

        try
        {
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_params.getOrderUnitId());
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
        // �������P�ʃe�[�u��.�����ID
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_params.setTraderId(null);
        }
        else
        {
            l_params.setTraderId(l_orderUnitRow.getTraderId());
        }

        //���    deal_type
        // �������P�ʃe�[�u��.���
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

        //�w�l     limit_price
        //�������P�ʃe�[�u��.�w�l
        if (l_orderUnitRow.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(null);
        }
        else
        {
            l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());
        }

        //�������敪 order_exec_status
        //�������P�ʃe�[�u��.�������敪
        l_params.setOrderExecStatus(l_orderUnitRow.getOrderExecStatus());

        //����     exec_date
        //�������P�ʃe�[�u��.����
        l_params.setExecDate(l_orderUnitRow.getExecDate());

        //���n����     foreign_exec_date
        //�������P�ʃe�[�u��.���n����
        l_params.setForeignExecDate(l_orderUnitRow.getForeignExecDate());

        //��n��     delivery_date
        // �������P�ʃe�[�u��.��n��
        l_params.setDeliveryDate(l_orderUnitRow.getDeliveryDate());

        //���n��n��     foreign_delivery_date
        //�������P�ʃe�[�u��.���n��n��
        l_params.setForeignDeliveryDate(l_orderUnitRow.getForeignDeliveryDate());

        //������ payment_date
        //�������P�ʃe�[�u��.������
        l_params.setPaymentDate(l_orderUnitRow.getPaymentDate());

        //��בփ��[�g      base_fx_rate
        //�������P�ʃe�[�u��.��בփ��[�g
        if (l_orderUnitRow.getBaseFxRateIsNull())
        {
            l_params.setBaseFxRate(null);
        }
        else
        {
            l_params.setBaseFxRate(l_orderUnitRow.getBaseFxRate());
        }

        //���בփ��[�g     exec_fx_rate
        //�������P�ʃe�[�u��.���בփ��[�g
        if (l_orderUnitRow.getExecFxRateIsNull())
        {
            l_params.setExecFxRate(null);
        }
        else
        {
            l_params.setExecFxRate(l_orderUnitRow.getExecFxRate());
        }

        //��������i�~�݁j      trading_price
        //�������P�ʃe�[�u��.��������i�~�݁j
        if (l_orderUnitRow.getTradingPriceIsNull())
        {
            l_params.setTradingPrice(null);
        }
        else
        {
            l_params.setTradingPrice(l_orderUnitRow.getTradingPrice());
        }

        //��������i�O�݁j      foreign_trading_price
        //�������P�ʃe�[�u��.��������i�O�݁j
        if (l_orderUnitRow.getForeignTradingPriceIsNull())
        {
            l_params.setForeignTradingPrice(null);
        }
        else
        {
            l_params.setForeignTradingPrice(l_orderUnitRow.getForeignTradingPrice());
        }

        //�o�ߗ��q�i�~�݁j      accrued_interest
        //�������P�ʃe�[�u��.�o�ߗ��q�i�~�݁j
        if (l_orderUnitRow.getAccruedInterestIsNull())
        {
            l_params.setAccruedInterest(null);
        }
        else
        {
            l_params.setAccruedInterest(l_orderUnitRow.getAccruedInterest());
        }

        //�o�ߗ��q�i�O�݁j      foreign_accrued_interest
        //�������P�ʃe�[�u��.�o�ߗ��q�i�O�݁j
        if (l_orderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_params.setForeignAccruedInterest(null);
        }
        else
        {
            l_params.setForeignAccruedInterest(l_orderUnitRow.getForeignAccruedInterest());
        }

        //��n����i�~�݁j      estimated_price
        //�������P�ʃe�[�u��.��n����i�~�݁j
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(null);
        }
        else
        {
            l_params.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
        }

        //��n����i�O�݁j      foreign_estimated_price
        // �������P�ʃe�[�u��.��n����i�O�݁j
        if (l_orderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_params.setForeignEstimatedPrice(null);
        }
        else
        {
            l_params.setForeignEstimatedPrice(l_orderUnitRow.getForeignEstimatedPrice());
        }

        //���r���������z adjustment_before_maturity
        //�������P�ʃe�[�u��.���r���������z
        if (l_orderUnitRow.getAdjustmentBeforeMaturityIsNull())
        {
            l_params.setAdjustmentBeforeMaturity(null);
        }
        else
        {
            l_params.setAdjustmentBeforeMaturity(l_orderUnitRow.getAdjustmentBeforeMaturity());
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

        //�J�X�g�f�B�A���R�[�h       custodian_code
        //�������P�ʃe�[�u��.�J�X�g�f�B�A���R�[�h
        l_params.setCustodianCode(l_orderUnitRow.getCustodianCode());

        //�����o�H�敪       order_root_div
        //�������P�ʃe�[�u��.�����o�H�敪
        l_params.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());

        //�Ǘ��҃R�[�h        administrator_code
        //�������P�ʃe�[�u��.�Ǘ��҃R�[�h
        l_params.setAdministratorCode(l_orderUnitRow.getAdministratorCode());

        //�����G���[���R�R�[�h      error_reason_code
        //�������P�ʃe�[�u��.�����G���[���R�R�[�h
        l_params.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());

        log.exiting(STR_METHOD_NAME);

        return l_params;
    }

    /**
     * (get���������)<BR>
     * this.����������ԋp����B
     * @@return WEB3BondExecuteDateInfo
     * @@roseuid 44DFD2CA01C5
     */
    public WEB3BondExecuteDateInfo getBondExecuteDateInfo()
    {
        return this.bondExecuteDateInfo;
    }

    /**
     * (set���������)<BR>
     * �����������Z�b�g����B
     * @@param l_bondExecuteDateInfo - (���������)<BR>
     * ���������
     * @@roseuid 44DFD2D9000F
     */
    public void setBondExecuteDateInfo(WEB3BondExecuteDateInfo l_bondExecuteDateInfo)
    {
        this.bondExecuteDateInfo = l_bondExecuteDateInfo;
    }

    /**
     * (get����n����v�Z����)<BR>
     * this.����n����v�Z���ʂ�ԋp����B
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@roseuid 44DFD2E60128
     */
    public WEB3BondEstimatedPriceCalcResult getBondEstimatedPriceCalcResult()
    {
        return this.bondEstimatedPriceCalcResult;
    }

    /**
     * (set����n����v�Z����)<BR>
     * ����n����v�Z���ʂ��Z�b�g����B
     * @@param l_bondEstimatedPriceCalcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����
     * @@roseuid 44DFD2FE0242
     */
    public void setBondEstimatedPriceCalcResult(WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult)
    {
        this.bondEstimatedPriceCalcResult = l_bondEstimatedPriceCalcResult;
    }

    /**
     * (get�g�����V�K�������e)<BR>
     * this.�g�����V�K�������e��ԋp����B
     * @@return WEB3BondNewOrderSpec
     * @@roseuid 44DFD31603A9
     */
    public WEB3BondNewOrderSpec getBondNewOrderSpec()
    {
        return this.bondNewOrderSpec;
    }

    /**
     * (set�g�����V�K�������e)<BR>
     * �g�����V�K�������e���Z�b�g����B
     * @@param l_bondNewOrderSpec - (�g�����V�K�������e)<BR>
     * �g�����V�K�������e
     * @@roseuid 44DFD32A0196
     */
    public void setBondNewOrderSpec(WEB3BondNewOrderSpec l_bondNewOrderSpec)
    {
        this.bondNewOrderSpec = l_bondNewOrderSpec;
    }

    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType arg0,
        OrderManagerPersistenceContext arg1,
        BondOrderExecutionParams arg2)
    {
        return null;
    }

    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType arg0,
        Class arg1)
    {
        return null;
    }
}
@
