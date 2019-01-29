head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������X�V�C�x���g�C���^�Z�v�^(WEB3FeqExecuteUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  ����(���u) �V�K�쐬
                 : 2005/07/28 ������(���u) ���r���[
Revesion History : 2007/08/20 �Ӑ� (���u) �c�a�X�V�d�l087
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
*/

package webbroker3.feq;


import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.feq.util.WEB3FeqOrderUtility;

/**
 * (�O���������X�V�C�x���g�C���^�Z�v�^)<BR>
 * �O���������X�V�C�x���g�C���^�Z�v�^<BR>
 * 
 * @@ author ���� 
 * @@ version 1.0 
 */
public class WEB3FeqExecuteUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteUpdateInterceptor.class);
    
    /**
     * (�O���o���ʒm�L���[)<BR>
     * �O���o���ʒm�L���[�s�I�u�W�F�N�g<BR>
     * <BR>
     * ���O���o���ʒm�L���[Params��DDL��莩����������B<BR>
     */
    private HostFeqOrderExecNotifyParams feqOrderExecNotify;
    
    /**
     * (�����P��)<BR>
     * �X�V�O�̒����P�ʃI�u�W�F�N�g�B
     */
    private FeqOrderUnit feqOrderUnit;
    
    /**
     * (��萔��)<BR>
     * ��萔�ʁB
     */
    private double execQuantity;
    
    /**
     * (���P��)<BR>
     * ���P���B
     */
    private double execPrice;
    
    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g�B
     */
    private double fxRate;
    
    /**
     * @@roseuid 42D0D2D6000F
     */
    public WEB3FeqExecuteUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�O���������X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �o���ʒm�L���[���v���p�e�B�ɃZ�b�g���C���X�^���X��������B<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[)<BR>
     * �O���o���ʒm�L���[�s�I�u�W�F�N�g
     * @@roseuid 428B055D0031
     */
    public WEB3FeqExecuteUpdateInterceptor(HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams) 
    {
        this.feqOrderExecNotify = l_hostFeqOrderExecNotifyParams;
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@super.mutate(OrderManagerPersistenceType,<BR>
     * �@@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V<BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR> 
     * �@@���ڐݒ���e�́A<BR>
     * �@@�yxTrade�z�⑫����.DB�X�V\\11.�Ǘ��ҁE�o������<BR>
     * �@@�u�O���o��_�O�������P�ʎd�l.xls�v�Q�ƁB<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v
     * @@param l_context - (����)<BR>
     * ����
     * @@param l_feqOrderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j
     * @@return FeqOrderUnitParams
     * @@roseuid 428B026B0060
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderUnitParams l_feqOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, " 
            + "FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����iͯ�ށj�e�[�u���X�V
        if (l_feqOrderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }

        l_feqOrderUnitParams = super.mutate(
            l_updateType,
            l_context,
            l_feqOrderUnitParams);
            
        //�Q�j�@@�����P�ʃe�[�u���X�V        
        //���ŏI�ʔ�
        //���͂�����ꍇ�i�O���o���ʒm�L���[Params.���ʔ� != null�j�A
        // ���͒l�i�O���o���ʒm�L���[Params.���ʔԁj���Z�b�g
        if (this.feqOrderExecNotify.getExecSerialNo() != null)
        {
            l_feqOrderUnitParams.setLastExecutionSerialNo(Integer.parseInt(this.feqOrderExecNotify.getExecSerialNo()));
        } 
        
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
            double l_dblFillAmount = this.execPrice * this.execQuantity;
            BigDecimal l_bdFillAmount = new BigDecimal(l_dblFillAmount);
            l_bdFillAmount = l_bdFillAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
            l_dblFillAmount = l_bdFillAmount.doubleValue();
            double l_dblFillAmountYen =
                l_bizLogicProvider.calcJPYAmount(
                    l_dblFillAmount,
                    this.fxRate,
                    l_strFCCYRoundDiv);
            FeqOrderUnitRow l_orderUnitRow =
                (FeqOrderUnitRow)this.feqOrderUnit.getDataSourceObject();
            BigDecimal l_bdExecutedAmount =
                new BigDecimal(String.valueOf(l_orderUnitRow.getExecutedAmount()));
            l_feqOrderUnitParams.setExecutedAmount(l_bdExecutedAmount.add(l_bdFillAmount).doubleValue());
            l_feqOrderUnitParams.setExecutedAmountYen(l_orderUnitRow.getExecutedAmountYen() + l_dblFillAmountYen);
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
        
        //���������E����敪
        //0�F�����l�iWEB3ModifyCancelTypeDef�ɂĒ�`�j
        l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        //����.�X�V�҃R�[�h
        FeqOrderRow l_feqOrderRow = null;
        try
        {            
            l_feqOrderRow = (FeqOrderRow) FeqOrderDao.findRowByOrderId(l_feqOrderUnitParams.getOrderId());

            //�X�V�҃R�[�h = ����.�X�V�҃R�[�h
            l_feqOrderUnitParams.setLastUpdater(l_feqOrderRow.getLastUpdater());
            if (this.feqOrderExecNotify.getExecutionSeqNoIsNull())
            {
                l_feqOrderUnitParams.setExecutionSeqNo(null);
            }
            else
            {
                l_feqOrderUnitParams.setExecutionSeqNo(this.feqOrderExecNotify.getExecutionSeqNo());
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("�f�[�^���d�����Ă��܂�: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * �@@���ڐݒ���e�́A<BR>
     * �@@�yxTrade�z�⑫����.DB�X�V\11.�Ǘ��ҁE�o������<BR>
     * �@@�u�O���o��_�O�����������d�l.xls�v<BR>
     * @@param l_updateType
     * @@param l_context
     * @@param l_feqOrderActionParams
     * @@return FeqOrderActionParams
     * @@roseuid 42AD54F5031B
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderActionParams l_feqOrderActionParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderActionParams) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderActionParams == null)
        {
            log.debug("��������Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        long l_lngOrderUnitId = l_feqOrderActionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingMod.getOrderManager();
        FeqOrderUnit l_feqOrderUnit = null;

        try 
        {
            // �����P�ʃI�u�W�F�N�g�擾
            l_feqOrderUnit = (FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
                
        if (l_feqOrderUnit == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();

        //�����P��.��������   
        l_feqOrderActionParams.setOrderConditionType(l_feqOrderUnitRow.getOrderConditionType());

        //�����P��.�����������Z�q
        l_feqOrderActionParams.setOrderCondOperator(l_feqOrderUnitRow.getOrderCondOperator());

        //�����P��.�t�w�l��l
        if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setStopOrderPrice(l_feqOrderUnitRow.getStopOrderPrice());
        }        
        
        //�����P��.�iW�w�l�j�����w�l
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_feqOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setWLimitPrice(l_feqOrderUnitRow.getWLimitPrice());
        }        
        
        //�����P��.�����������t    
        l_feqOrderActionParams.setExpirationDate(l_feqOrderUnitRow.getExpirationDate());
        
        //������        
        Timestamp l_tsNowDate = GtlUtils.getTradingSystem( ).getSystemTimestamp();
        
        //null�̏ꍇ�A���ݓ������Z�b�g
        if (this.feqOrderExecNotify.getExecTimestamp() == null)
        {
            l_feqOrderActionParams.setExecTimestamp(l_tsNowDate); 
        }
        //�O���o���ʒm�L���[Params.������
        else
        {
            l_feqOrderActionParams.setExecTimestamp(this.feqOrderExecNotify.getExecTimestamp());
        }
        
        //�����P��.�T�Z��n���
        if (l_feqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setEstimatedPrice(l_feqOrderUnitRow.getEstimatedPrice());
        }        
        
        //�����P��.�T�Z��n����i�O�݁j
        if (l_feqOrderUnitRow.getFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setFEstimatedPrice(l_feqOrderUnitRow.getFEstimatedPrice());
        }        
        
        //�����P��.���������E����敪
        l_feqOrderActionParams.setModifyCancelType(l_feqOrderUnitRow.getModifyCancelType());
        
        //�����P��.�����o�H�敪
        l_feqOrderActionParams.setOrderRootDiv(l_feqOrderUnitRow.getOrderRootDiv());
        
        //�s�ꂩ��m�F�ς݂̒����P�� = �����P��.�s�ꂩ��m�F�ς݂̒����P��
        if (l_feqOrderUnitRow.getConfirmedOrderPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(l_feqOrderUnitRow.getConfirmedOrderPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̊T�Z��n��� = �����P��.�s�ꂩ��m�F�ς݂̊T�Z��n���
        if (l_feqOrderUnitRow.getConfirmedEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(l_feqOrderUnitRow.getConfirmedEstimatedPrice());
        }

        //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j= �����P��.�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
        if (l_feqOrderUnitRow.getConfirmedFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(l_feqOrderUnitRow.getConfirmedFEstimatedPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̎��s���� = �����P��.�s�ꂩ��m�F�ς݂̎��s����
        l_feqOrderActionParams.setConfirmedExecConditionType(l_feqOrderUnitRow.getConfirmedExecConditionType());
        
        //�����P��.�����G���[���R�R�[�h
        l_feqOrderActionParams.setErrorReasonCode(l_feqOrderUnitRow.getErrorReasonCode());
        
        //�X�V�҃R�[�h = �����P��.�X�V�҃R�[�h
        l_feqOrderActionParams.setLastUpdater(l_feqOrderUnitRow.getLastUpdater());   
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }
    
    /**
     * (�i���j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * ���Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * ���ڐݒ���e�́A<BR>
     * �yxTrade�z�⑫����.DB�X�V\\<BR>
     * 11.�Ǘ��ҁE�o�����́u�O���o��_�O�����d�l.xls�v�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v
     * @@param l_context - (����)<BR>
     * ����
     * @@param l_feqOrderExecutionParams - (���s)<BR>
     * ���s�i�F���Params�j
     * @@return FeqOrderExecutionParams
     * @@roseuid 428B026B007F
     */
    public FeqOrderExecutionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderExecutionParams l_feqOrderExecutionParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderExecutionParams) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderExecutionParams == null)
        {
            log.debug("���Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        long l_lngOrderUnitId = l_feqOrderExecutionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingMod.getOrderManager();
        FeqOrderUnit l_feqOrderUnit = null;

        try 
        {
            // �����P�ʃI�u�W�F�N�g�擾
            l_feqOrderUnit = (FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }

        if (l_feqOrderUnit == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        
        //������
        Timestamp l_tsNowDate = GtlUtils.getTradingSystem( ).getSystemTimestamp();
        
        //null�̏ꍇ�A���ݓ������Z�b�g
        if (this.feqOrderExecNotify.getExecTimestamp() == null)
        {
            l_feqOrderExecutionParams.setExecTimestamp(l_tsNowDate); 
        }
        //�O���o���ʒm�L���[Params.������
        else
        {
            l_feqOrderExecutionParams.setExecTimestamp(this.feqOrderExecNotify.getExecTimestamp());
        }
                
        Timestamp l_tsExecTimestamp = l_feqOrderExecutionParams.getExecTimestamp();        
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_tsExecTimestamp.getTime()));
                                        
        //��n�� = �������i���ԕ�����00:00:00�j�̂R�c�Ɠ���
        try
        {
            String l_strMarketCode = l_finApp.getFinObjectManager().getMarket(l_feqOrderExecutionParams.getMarketId()).getMarketCode();        
            String l_strInstitutionCode = l_finApp.getAccountManager().getMainAccount(l_feqOrderExecutionParams.getAccountId()).getInstitution().getInstitutionCode();  
            l_feqOrderExecutionParams.setDeliveryDate(WEB3DateUtility.toDay(l_gentradeBizDate.roll(3)));
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        catch (WEB3SystemLayerException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);      
        }

        //���n��n��
        //null�̏ꍇ�A��n�����Z�b�g
        if(this.feqOrderExecNotify.getFDeliveryDate() == null)
        {
            l_feqOrderExecutionParams.setFDeliveryDate(l_feqOrderExecutionParams.getDeliveryDate());
        }
        //�O���o���ʒm�L���[Params.���n��n��
        else
        {
            l_feqOrderExecutionParams.setFDeliveryDate(this.feqOrderExecNotify.getFDeliveryDate());
        }

        //�����P��.������
        l_feqOrderExecutionParams.setBizDate(l_feqOrderUnitRow.getBizDate());

        //�����P��.���ϋ敪
        l_feqOrderExecutionParams.setSettleDiv(l_feqOrderUnitRow.getSettleDiv());

        //�����P��.���ʃR�[�h
        l_feqOrderExecutionParams.setOrderRequestNumber(l_feqOrderUnitRow.getOrderRequestNumber());

        //�����P��.�^�p�R�[�h
        l_feqOrderExecutionParams.setOrderEmpCode(l_feqOrderUnitRow.getOrderEmpCode());

        //ThreadLocalSystemAttributesRegistry.getAttribute(�h���o�H�敪�h)
        //�i���e�T�[�r�X�C���^�Z�v�^�ɂăZ�b�g�����l�j        
        l_feqOrderExecutionParams.setOrderExecRouteDiv(
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV).toString());

        //�����P��.�X�V�҃R�[�h
        l_feqOrderExecutionParams.setLastUpdater(l_feqOrderUnitRow.getLastUpdater());
        
        //��������@@�����_��O�ʂ��l�̌ܓ��isonar�̎d�l�j
        double l_dblForeignTradePricewk =
            new BigDecimal(String.valueOf(l_feqOrderExecutionParams.getExecPrice()))
            .multiply(new BigDecimal(String.valueOf(l_feqOrderExecutionParams.getExecQuantity())))
            .doubleValue();
        double l_dblForeignTradePrice = 
            WEB3FeqOrderUtility.getCutOutValue(
                l_dblForeignTradePricewk,
                2,
                WEB3FeqOrderUtility.ROUND);  
        l_feqOrderExecutionParams.setForeignTradePrice(l_dblForeignTradePrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderExecutionParams;
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
    
    /**
     * (set��萔��)<BR>
     * ��萔�ʂ�ݒ肷��B
     * @@param l_dblExecQuantity - (��萔��)<BR>
     * ��萔�ʁB
     */
    public void setExecQuantity(double l_dblExecQuantity)
    {
        this.execQuantity = l_dblExecQuantity;
    }
    
    /**
     * (get��萔��)<BR>
     * ��萔�ʂ�Ԃ��B
     * @@return double
     */
    public double getExecQuantity()
    {
        return this.execQuantity;
    }
    
    /**
     * (set���P��)<BR>
     * ���P����ݒ肷��B
     * @@param l_dblExecPrice - (���P��)<BR>
     * ���P���B
     */
    public void setExecPrice(double l_dblExecPrice)
    {
        this.execPrice = l_dblExecPrice;
    }
    
    /**
     * (get���P��)<BR>
     * ���P����Ԃ��B
     * @@return double
     */
    public double getExecPrice()
    {
        return this.execPrice;
    }
    
    /**
     * (set�בփ��[�g)<BR>
     * �בփ��[�g��ݒ肷��B
     * @@param l_dblFxRate - (�בփ��[�g)<BR>
     * �בփ��[�g�B
     */
    public void setFxRate(double l_dblFxRate)
    {
        this.fxRate = l_dblFxRate;
    }
    
    /**
     * (get�בփ��[�g)<BR>
     * �בփ��[�g��Ԃ��B
     * @@return double
     */
    public double getFxRate()
    {
        return this.fxRate;
    }
}
@
