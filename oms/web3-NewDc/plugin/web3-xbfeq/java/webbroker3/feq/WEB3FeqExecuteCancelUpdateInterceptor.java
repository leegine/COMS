head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������X�V�C�x���g�C���^�Z�v�^(WEB3FeqExecuteCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research           
Revesion History : 2005/07/14  ����(���u) �V�K�쐬
                 : 2005/07/28 ������(���u) ���r���[
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
*/

package webbroker3.feq;

import java.math.BigDecimal;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqPositionManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������X�V�C�x���g�C���^�Z�v�^) <BR>
 * �O������������X�V�C�x���g�C���^�Z�v�^ <BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3FeqExecuteCancelUpdateInterceptor extends WEB3FeqUpdateInterceptor implements
    FeqPositionManagerPersistenceEventInterceptor
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteCancelUpdateInterceptor.class);

    /**
     * (�O�����) <BR>
     * ������Ώۂ̊O���������I�u�W�F�N�g <BR>
     */
    private WEB3FeqOrderExecution feqExecution;

    /**
     * (�����P��)<BR>
     * �X�V�O�̒����P�ʃI�u�W�F�N�g�B
     */
    private FeqOrderUnit feqOrderUnit;
    
    /**
     * @@roseuid 42D0D2AD038A
     */
    public WEB3FeqExecuteCancelUpdateInterceptor()
    {

    }

    /**
     * (�O������������X�V�C�x���g�C���^�Z�v�^) <BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * ���I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���C���X�^���X��������B <BR>
     *
     * @@param l_feqExecute -
     *            (�O�����) <BR>
     *            �O���������I�u�W�F�N�g
     * @@roseuid 42AD57B40230
     */
    public WEB3FeqExecuteCancelUpdateInterceptor(WEB3FeqOrderExecution l_feqExecute)
    {
        this.feqExecution = l_feqExecute;
    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�) <BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j �����iͯ�ށj�e�[�u���X�V <BR>
     * super.mutate(OrderManagerPersistenceType, <BR>
     * OrderManagerPersistenceContext, FeqOrderUnitParams) <BR>
     * ���R�[������B <BR>
     * <BR>
     * �Q�j �����P�ʃe�[�u���X�V <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * ���ڐݒ���e�́A�yxTrade�z�⑫����.DB�X�V\\ <BR>
     * 13.�Ǘ��ҁE�o��������u�O���o�����_�O�������P�ʎd�l.xls�v�Q�ƁB <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     *
     * @@param l_updateType -
     *            (�X�V�^�C�v) <BR>
     *            �X�V�^�C�v
     * @@param l_context -
     *            (����) <BR>
     *            ����
     * @@param l_feqOrderUnitParams -
     *            (�����P�ʍs) <BR>
     *            �����P�ʍs�i�F�����P��Params�j
     * @@return FeqOrderUnitParams
     * @@roseuid 42AD57B401F2
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnitParams == null)
        {
            log.debug(" �����P��Params�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j �����iͯ�ށj�e�[�u���X�V
        l_feqOrderUnitParams = super.mutate(l_updateType, l_context, l_feqOrderUnitParams);
        
        // �Q�j �����P�ʃe�[�u���X�V
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqBizLogicProvider l_bizLogicProvider =
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            WEB3FeqProduct l_product = (WEB3FeqProduct)this.feqOrderUnit.getProduct();
            WEB3GentradeCurrency l_genCurrency = l_product.getCurrency();
            int l_intScale = l_genCurrency.getScale();
            String l_strFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
            double l_dblUndoAmount =
                feqExecution.getExecutionPrice() * feqExecution.getExecutionQuantity();
            BigDecimal l_bdUndoAmount = new BigDecimal(l_dblUndoAmount);
            l_bdUndoAmount = l_bdUndoAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
            l_dblUndoAmount = l_bdUndoAmount.doubleValue();
            double l_dblUndoAmountYen =
                l_bizLogicProvider.calcJPYAmount(
                    l_dblUndoAmount,
                    feqExecution.getFxRate(),
                    l_strFCCYRoundDiv);
            FeqOrderUnitRow l_orderUnitRow =
                (FeqOrderUnitRow)this.feqOrderUnit.getDataSourceObject();
            BigDecimal l_bdExecutedAmount =
                new BigDecimal(String.valueOf(l_orderUnitRow.getExecutedAmount()));
            l_feqOrderUnitParams.setExecutedAmount(l_bdExecutedAmount.subtract(l_bdUndoAmount).doubleValue());
            l_feqOrderUnitParams.setExecutedAmountYen(l_orderUnitRow.getExecutedAmountYen() - l_dblUndoAmountYen);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
        }
        
        // �X�V�҃R�[�h
        try
        {
            FeqOrderRow l_orderRow = FeqOrderDao.findRowByPk(l_feqOrderUnitParams.getOrderId());
            if (l_orderRow == null)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            String l_strLastUpdater = l_orderRow.getLastUpdater();
            l_feqOrderUnitParams.setLastUpdater(l_strLastUpdater);
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }

    /**
     * (�i���������j�X�V�l�ݒ�) <BR>
     * �imutate���\�b�h�̎����j <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * �P�j �����P�ʃI�u�W�F�N�g�擾 <BR>
     * <BR>
     * �����̒�������Params.����ID�A <BR>
     * �����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j �g�����ڃZ�b�g <BR>
     * �p�����[�^.��������Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * ���ڐݒ���e�́A <BR>
     * �yxTrade�z�⑫����.DB�X�V\\ <BR>
     * 13.�Ǘ��ҁE�o��������u�O���o�����_�O�����������d�l.xls�v�Q�ƁB <BR>
     *
     * @@param l_updateType
     * @@param l_context
     * @@param l_feqOrderActionParams
     * @@return FeqOrderActionParams
     * @@roseuid 42AD57B40211
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderActionParams l_feqOrderActionParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderActionParams == null)
        {
            log.debug(" ��������Params�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j �����P�ʃI�u�W�F�N�g�擾
        FeqOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnitRow = FeqOrderUnitDao.findRowByPk(
                l_feqOrderActionParams.getOrderUnitId());
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_orderUnitRow == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �Q�j �g�����ڃZ�b�g
        // ��������
        l_feqOrderActionParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());

        // �����������Z�q
        l_feqOrderActionParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());

        // �t�w�l��l
        if (l_orderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setStopOrderPrice(l_orderUnitRow.getStopOrderPrice());
        }

        // �iW�w�l�j�����w�l
        if (l_orderUnitRow.getWLimitPriceIsNull())
        {
            l_feqOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setWLimitPrice(l_orderUnitRow.getWLimitPrice());
        }

        // �����������t
        l_feqOrderActionParams.setExpirationDate(l_orderUnitRow.getExpirationDate());

        // ������
        l_feqOrderActionParams.setExecTimestamp(feqExecution.getExecutionTimestamp());

        // �T�Z��n���
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
        }

        // �T�Z��n����i�O�݁j
        if (l_orderUnitRow.getFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setFEstimatedPrice(l_orderUnitRow.getFEstimatedPrice());
        }

        // ���������E����敪
        l_feqOrderActionParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());

        // �����o�H�敪
        l_feqOrderActionParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());

        // �s�ꂩ��m�F�ς݂̒����P��
        if (l_orderUnitRow.getConfirmedOrderPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(l_orderUnitRow.getConfirmedOrderPrice());
        }

        // �s�ꂩ��m�F�ς݂̊T�Z��n���
        if (l_orderUnitRow.getConfirmedEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(l_orderUnitRow.getConfirmedEstimatedPrice());
        }

        // �s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
        if (l_orderUnitRow.getConfirmedFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(l_orderUnitRow.getConfirmedFEstimatedPrice());
        }

        // �s�ꂩ��m�F�ς݂̎��s����
        l_feqOrderActionParams.setConfirmedExecConditionType(l_orderUnitRow.getConfirmedExecConditionType());

        // �����G���[���R�R�[�h
        l_feqOrderActionParams.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());

        // �X�V�҃R�[�h
        l_feqOrderActionParams.setLastUpdater(l_orderUnitRow.getLastUpdater());

        //����Ώۂ̊O�����.���P��
        if (Double.isNaN(this.feqExecution.getExecutionPrice()))
        {
            l_feqOrderActionParams.setExecutedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setExecutedPrice(this.feqExecution.getExecutionPrice()); 
        }
        //����Ώۂ̊O�����.��萔��
        l_feqOrderActionParams.setExecutedQuantity(this.feqExecution.getExecutionQuantity());
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }

    /**
     * (�i���j�X�V�l�ݒ�) <BR>
     * �imutate���\�b�h�̎����j <BR>
     * ���Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * ���ڐݒ���e�́A <BR>
     * �yxTrade�z�⑫����.DB�X�V\\ <BR>
     * 13.�Ǘ��ҁE�o��������u�O���o�����_�O�����d�l.xls�v�Q�ƁB <BR>
     * <BR>
     *
     * @@param l_updateType -
     *            (�X�V�^�C�v) <BR>
     *            �X�V�^�C�v
     * @@param l_context -
     *            (����) <BR>
     *            ����
     * @@param l_feqOrderExecutionParams -
     *            (���s) <BR>
     *            ���s�i�F���Params�j
     * @@return FeqOrderExecutionParams
     * @@roseuid 42AE6BD7024E
     */
    public FeqOrderExecutionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderExecutionParams l_feqOrderExecutionParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderExecutionParams == null)
        {
            log.debug(" ���Params�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �����P�ʃI�u�W�F�N�g�擾
        FeqOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnitRow = FeqOrderUnitDao.findRowByPk(
                l_feqOrderExecutionParams.getOrderUnitId());
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_orderUnitRow == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB
        // �X�V�҃R�[�h
        l_feqOrderExecutionParams.setLastUpdater(l_orderUnitRow.getLastUpdater());

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderExecutionParams;
    }

    /**
     * �imutateBeforeInsert�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * ������AssetParams��ԋp����B <BR>
     *
     * @@param arg0
     * @@return AssetParams
     * @@roseuid 42AEA8210069
     */
    public AssetParams mutateBeforeInsert(AssetParams arg0)
    {
        return arg0;
    }

    /**
     * �imutateBeforeInsert�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * ������FeqFinTransactionParams��ԋp����B <BR>
     *
     * @@param arg0
     * @@return FeqFinTransactionParams
     * @@roseuid 42AEA8210088
     */
    public FeqFinTransactionParams mutateBeforeInsert(FeqFinTransactionParams arg0)
    {
        return arg0;
    }

    /**
     * �imutateBeforeInsert�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * ������AssetUnitParams��ԋp����B <BR>
     *
     * @@param arg0
     * @@return AssetUnitParams
     * @@roseuid 42AEA82100A8
     */
    public AssetUnitParams mutateBeforeInsert(AssetUnitParams arg0)
    {
        return arg0;
    }

    /**
     * �imutateBeforeInsert�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * ������AssetUnitSalesParams��ԋp����B <BR>
     *
     * @@param arg0
     * @@return AssetUnitSalesParams
     * @@roseuid 42AEA82100C7
     */
    public AssetUnitSalesParams mutateBeforeInsert(AssetUnitSalesParams arg0)
    {
        return arg0;
    }

    /**
     * �imutateBeforeUpdate�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * ������Map��ԋp����B <BR>
     *
     * @@param arg0
     * @@param arg1
     * @@return Map
     * @@roseuid 42AEA82100D7
     */
    public Map mutateBeforeUpdate(AssetParams arg0, Map arg1)
    {
        return arg1;
    }

    /**
     * (�i�g�����U�N�V�����i������薾�ׁj�j�X�V�O�X�V�l�ݒ�) <BR>
     * �imutateBeforeUpdate�̎����j <BR>
     * ������Map�Ɋg������(*)��ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * ���ڐݒ���e�́A <BR>
     * �yxTrade�z�⑫����.DB�X�V\\ <BR>
     * 13.�Ǘ��ҁE�o��������u�O���o�����_�g�����U�N�V���� <BR>
     * �i������薾�ׁj�d�l.xls�v�Q�ƁB <BR>
     * <BR>
     * ��Map�ւ́Akey�F �X�V�񕨗����Avalue�F �X�V�l�Ƃ��� <BR>
     * �ݒ肷�邱�ƁB <BR>
     *
     * @@param l_feqFinTransactionParams
     * @@param l_map
     * @@return Map
     * @@roseuid 42AEA82100F6
     */
    public Map mutateBeforeUpdate(FeqFinTransactionParams l_feqFinTransactionParams, Map l_map)
    {
        final String STR_METHOD_NAME = "mutateBeforeUpdate(FeqFinTransactionParams, Map)";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }    
             
        // this.���ID�ɊY��������擾
        FeqOrderExecutionRow l_orderExecRow = null;
        try
        {
            l_orderExecRow = FeqOrderExecutionDao.findRowByPk(
                l_feqFinTransactionParams.getOrderExecutionId());
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_orderExecRow == null)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB
        l_map.put("last_updater", l_orderExecRow.getLastUpdater());

        log.exiting(STR_METHOD_NAME);
        return l_map;
    }

    /**
     * �imutateBeforeUpdate�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * ������Map��ԋp����B <BR>
     *
     * @@param arg0
     * @@param arg1
     * @@return Map
     * @@roseuid 42AEA8210115
     */
    public Map mutateBeforeUpdate(AssetUnitParams arg0, Map arg1)
    {
        return arg1;
    }

    /**
     * �imutateBeforeUpdate�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * ������Map��ԋp����B <BR>
     *
     * @@param arg0
     * @@param arg1
     * @@return Map
     * @@roseuid 42AEA8210134
     */
    public Map mutateBeforeUpdate(AssetUnitSalesParams arg0, Map arg1)
    {
        return arg1;
    }
    
    /**
     * (set�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g��ݒ肷��B
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     */
    public void setFeqOrderUnit(FeqOrderUnit l_feqOrderUnit)
    {
        this.feqOrderUnit = l_feqOrderUnit;
    }
    
    /**
     * (get�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g��Ԃ��B
     * @@return FeqOrderUnit
     */
    public FeqOrderUnit getFeqOrderUnit()
    {
        return this.feqOrderUnit;
    }
}@
