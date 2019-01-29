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
filename	WEB3BondDomesticOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������X�V�C���^�Z�v�^�N���X(WEB3BondDomesticOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.231,���f��No.243
Revision History : 2007/08/28 �đo�g (���u) �c�a�X�V�d�lNo.039
*/
package webbroker3.bd;

import java.sql.Timestamp;

import webbroker3.bd.define.WEB3BondAutoExecDivListDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3HostReflectDivDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

/**
 * (�����������X�V�C���^�Z�v�^�N���X)<BR>
 * �����������X�V�C���^�Z�v�^�N���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticOrderUpdateInterceptor extends WEB3BondOrderUpdateInterceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticOrderUpdateInterceptor.class);

    /**
     * (�����������X�V�C���^�Z�v�^�N���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D84FAD015E
     */
    public WEB3BondDomesticOrderUpdateInterceptor()
    {

    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �����P�ʃe�[�u���X�V<BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * ���ڐݒ���e�́ADB�X�V�d�l<BR>
     * �u����������_�������P�ʃe�[�u��DB�X�V�d�l.xls�v<BR>
     * <BR>
     * �Q�ƁB<BR>
     * @@param l_persistenceType - (OrderManagerPersistenceType)<BR>
     * OrderManagerPersistenceType<BR>
     * @@param l_context - (OrderManagerPersistenceContext)<BR>
     * OrderManagerPersistenceContext<BR>
     * @@param l_params - (BondOrderUnitParams)<BR>
     * BondOrderUnitParams<BR>
     * @@return BondOrderUnitParams
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME = " mutate(OrderManagerPersistenceType,"
            + " OrderManagerPersistenceContext"
            + " BondOrderUnitParams";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }

        //�������: 403:���������咍��
        l_params.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);

        //���,35:��W���
        l_params.setDealType(WEB3DealTypeDef.RECRUIT_TRADING);

        //��n��,���������.get��n���i�j
        l_params.setDeliveryDate(this.getBondExecuteDateInfo().getDeliveryDate());

        //���n��n��,null
        l_params.setForeignDeliveryDate(null);

        //�������b�N�敪  lock_status
        //1�F������
        l_params.setLockStatus(WEB3LockStatusDef.RELEASING);

        //�������敪  order_exec_status
        //0�F�����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);

        //������       biz_date
        //���������.get�������i�j
        l_params.setBizDate(
            WEB3DateUtility.formatDate(
                this.getBondExecuteDateInfo().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //���n������   foreign_biz_date
        //���������.get���n�������i�j
        l_params.setForeignBizDate(null);

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

        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
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
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        l_params.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

        //�����P��   price
        //����n����v�Z����.get�P���i�j
        if (this.getBondEstimatedPriceCalcResult().getPrice() != null)
        {
            l_params.setPrice(this.getBondEstimatedPriceCalcResult().getPrice().doubleValue());
        }
        else
        {
            l_params.setPrice(null);
        }

        //���ʃR�[�h    order_request_number
        //�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h( )
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
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
            log.error("���ʃR�[�h���擾����: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
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
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
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
        l_params.setSettlementDiv(this.getBondNewOrderSpec().getSettlementDiv());

        //�������敪        auto_exec_div
        //0�F�񎩓����
        l_params.setAutoExecDiv(WEB3BondAutoExecDivListDef.ZERO);

        //���P��      executed_price
        //null
        l_params.setExecutedPrice(null);

        //��בփ��[�g       base_fx_rate
        l_params.setBaseFxRate(null);

        //���בփ��[�g    exec_fx_rate
        //null
        l_params.setExecFxRate(null);

        //��������i�~�݁j    trading_price
        //����n����v�Z����.get��������i�~�݁j�i�j
        if (this.getBondEstimatedPriceCalcResult().getTradingPrice() != null)
        {
            l_params.setTradingPrice(
                this.getBondEstimatedPriceCalcResult().getTradingPrice().doubleValue());
        }
        else
        {
            l_params.setTradingPrice(null);
        }

        //��������i�O�݁j    foreign_trading_price null
        l_params.setForeignTradingPrice(null);

        //�o�ߗ��q�i�~�݁j    accrued_interest
        //����n����v�Z����.get�o�ߗ��q�i�~�݁j�i�j
        if (this.getBondEstimatedPriceCalcResult().getAccruedInterest() != null)
        {
            l_params.setAccruedInterest(
                this.getBondEstimatedPriceCalcResult().getAccruedInterest().doubleValue());
        }
        else
        {
            l_params.setAccruedInterest(null);
        }

        //�o�ߗ��q�i�O�݁j    foreign_accrued_interest null
        l_params.setForeignAccruedInterest(null);

        //��n����i�~�݁j    estimated_price
        //����n����v�Z����.get��n����i�~�݁j�i�j
        if (this.getBondEstimatedPriceCalcResult().getEstimatedPrice() != null)
        {
            l_params.setEstimatedPrice(
                this.getBondEstimatedPriceCalcResult().getEstimatedPrice().doubleValue());
        }
        else
        {
            l_params.setEstimatedPrice(null);
        }

        //��n����i�O�݁j    foreign_estimated_price null
        l_params.setForeignEstimatedPrice(null);

        //���r���������z adjustment_before_maturity
        //null
        l_params.setAdjustmentBeforeMaturity(null);

        //�o�ߓ���      elapsed_days null
        l_params.setElapsedDays(null);


        //�����      calc_base_days null
        l_params.setCalcBaseDays(null);

        //����        exec_date
        //���������.get�����i�j
        l_params.setExecDate(this.getBondExecuteDateInfo().getExecuteDate());

        //���n����     foreign_exec_date null
        l_params.setForeignExecDate(null);

        //������ payment_date
        //���������.get�������i�j
        l_params.setPaymentDate(this.getBondExecuteDateInfo().getPaymentDate());

        //�J�X�g�f�B�A���R�[�h    custodian_code null
        l_params.setCustodianCode(null);

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
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �����̒�������Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * �@@���ڐݒ���e�́A<BR>
     * �@@�u����������_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * @@param l_persistenceType - (OrderManagerPersistenceType)<BR>
     * OrderManagerPersistenceType<BR>
     * @@param l_context - (OrderManagerPersistenceContext)<BR>
     * OrderManagerPersistenceContext<BR>
     * @@param l_params - (BondOrderActionParams)<BR>
     * BondOrderActionParams<BR>
     * @@return BondOrderActionParams
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderActionParams l_params)
    {
        final String STR_METHOD_NAME = " mutate(OrderManagerPersistenceType,"
            + " OrderManagerPersistenceContext"
            + " BondOrderActionParams";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }

        //�g�����ڃZ�b�g
        long l_lngOrderUnitId = l_params.getOrderUnitId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_orderManager = (WEB3BondOrderManager)l_tradingMod.getOrderManager();
        BondOrderUnit l_bondOrderUnit = null;

        //�����P�ʃI�u�W�F�N�g�擾
        l_bondOrderUnit = (BondOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);

        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();

        //�������P�ʃe�[�u��.�����ID
        if (!l_bondOrderUnitRow.getTraderIdIsNull())
        {
            l_params.setTraderId(l_bondOrderUnitRow.getTraderId());
        }
        else
        {
            l_params.setTraderId(null);
        }

        //�������P�ʃe�[�u��.���
        l_params.setDealType(l_bondOrderUnitRow.getDealType());

        //�������P�ʃe�[�u��.�����P��
        if (!l_bondOrderUnitRow.getPriceIsNull())
        {
            l_params.setPrice(l_bondOrderUnitRow.getPrice());
        }
        else
        {
            l_params.setPrice(null);
        }

        //�������P�ʃe�[�u��.�w�l
        if (!l_bondOrderUnitRow.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(l_bondOrderUnitRow.getLimitPrice());
        }
        else
        {
            l_params.setLimitPrice(null);
        }

        //�������P�ʃe�[�u��.�������敪
        l_params.setOrderExecStatus(l_bondOrderUnitRow.getOrderExecStatus());

        //�������P�ʃe�[�u��.����
        l_params.setExecDate(l_bondOrderUnitRow.getExecDate());

        //�������P�ʃe�[�u��.���n����
        l_params.setForeignExecDate(l_bondOrderUnitRow.getForeignExecDate());

        //�������P�ʃe�[�u��.��n��
        l_params.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());

        //�������P�ʃe�[�u��.���n��n��
        l_params.setForeignDeliveryDate(l_bondOrderUnitRow.getForeignDeliveryDate());

        //�������P�ʃe�[�u��.������
        l_params.setPaymentDate(l_bondOrderUnitRow.getPaymentDate());

        //�������P�ʃe�[�u��.��בփ��[�g
        if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
        {
            l_params.setBaseFxRate(l_bondOrderUnitRow.getBaseFxRate());
        }
        else
        {
            l_params.setBaseFxRate(null);
        }

        //�������P�ʃe�[�u��.���בփ��[�g
        if (!l_bondOrderUnitRow.getExecFxRateIsNull())
        {
            l_params.setExecFxRate(l_bondOrderUnitRow.getExecFxRate());
        }
        else
        {
            l_params.setExecFxRate(null);
        }

        //�������P�ʃe�[�u��.��������i�~�݁j
        if (!l_bondOrderUnitRow.getTradingPriceIsNull())
        {
            l_params.setTradingPrice(l_bondOrderUnitRow.getTradingPrice());
        }
        else
        {
            l_params.setTradingPrice(null);
        }

        //�������P�ʃe�[�u��.��������i�O�݁j
        if (!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
        {
            l_params.setForeignTradingPrice(l_bondOrderUnitRow.getForeignTradingPrice());
        }
        else
        {
            l_params.setForeignTradingPrice(null);
        }

        //�������P�ʃe�[�u��.�o�ߗ��q�i�~�݁j
        if (!l_bondOrderUnitRow.getAccruedInterestIsNull())
        {
            l_params.setAccruedInterest(l_bondOrderUnitRow.getAccruedInterest());
        }
        else
        {
            l_params.setAccruedInterest(null);
        }

        //�������P�ʃe�[�u��.�o�ߗ��q�i�O�݁j
        if (!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_params.setForeignAccruedInterest(l_bondOrderUnitRow.getForeignAccruedInterest());
        }
        else
        {
            l_params.setForeignAccruedInterest(null);
        }

        //�������P�ʃe�[�u��.��n����i�~�݁j
        if (!l_bondOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(l_bondOrderUnitRow.getEstimatedPrice());
        }
        else
        {
            l_params.setEstimatedPrice(null);
        }
        //�������P�ʃe�[�u��.��n����i�O�݁j
        if (!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_params.setForeignEstimatedPrice(l_bondOrderUnitRow.getForeignEstimatedPrice());
        }
        else
        {
            l_params.setForeignEstimatedPrice(null);
        }

        //�������P�ʃe�[�u��.���r���������z
        if (!l_bondOrderUnitRow.getAdjustmentBeforeMaturityIsNull())
        {
            l_params.setAdjustmentBeforeMaturity(l_bondOrderUnitRow.getAdjustmentBeforeMaturity());
        }
        else
        {
            l_params.setAdjustmentBeforeMaturity(null);
        }

        //�������P�ʃe�[�u��.�o�ߓ���
        if (!l_bondOrderUnitRow.getElapsedDaysIsNull())
        {
            l_params.setElapsedDays(l_bondOrderUnitRow.getElapsedDays());
        }
        else
        {
            l_params.setElapsedDays(null);
        }

        //�������P�ʃe�[�u��.�����
        if (!l_bondOrderUnitRow.getCalcBaseDaysIsNull())
        {
            l_params.setCalcBaseDays(l_bondOrderUnitRow.getCalcBaseDays());
        }
        else
        {
            l_params.setCalcBaseDays(null);
        }

        //�������P�ʃe�[�u��.�J�X�g�f�B�A���R�[�h
        l_params.setCustodianCode(l_bondOrderUnitRow.getCustodianCode());

        //�������P�ʃe�[�u��.�����o�H�敪
        l_params.setOrderRootDiv(l_bondOrderUnitRow.getOrderRootDiv());

        //�������P�ʃe�[�u��.�Ǘ��҃R�[�h
        l_params.setAdministratorCode(l_bondOrderUnitRow.getAdministratorCode());

        //�������P�ʃe�[�u��.�����G���[���R�R�[�h
        l_params.setErrorReasonCode(l_bondOrderUnitRow.getErrorReasonCode());

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
