head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���ʒm�X�V�C���^�Z�v�^(WEB3IfoExecuteNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 Ḗ@@�� (���u) �V�K�쐬
				 : 2006/7/19 ���@@�r (���u) �d�l�ύX�@@���f��525
                 : 2006/8/10 �юu�� (���u) �d�l�ύX  ���f��543
                 : 2006/11/29 ���� (���u) DB�X�V�d�lNo.129�A130
Revesion History : 2007/07/02 �Ј��� (���u) DB�X�V�d�lNo.193
*/

package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�敨OP�o���ʒm�X�V�C���^�Z�v�^)<BR>
 * �敨OP�o���ʒm�X�V�C���^�Z�v�^�N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoExecuteNotifyUpdateInterceptor extends WEB3IfoUpdateInterceptor
{
    /**
      * ���O�o�̓��[�e�B���e�B�B
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoExecuteNotifyUpdateInterceptor.class);

    /**
     * �o���ʒm�敪<BR>
     * <BR>
     * DB���C�A�E�g<BR>
     * �u�敨OP�o���ʒm�L���[�e�[�u���v�Q�ƁB<BR>
     */
    private String dealedType;

    /**
     * (set�o���ʒm�敪)<BR>
     * �o���ʒm�敪���Z�b�g����B<BR>
     * @@param l_strDealedType - �o���ʒm�敪
     */
    public void setDealedType(String l_strDealedType)
    {
        this.dealedType = l_strDealedType;
    }

    /**
     * (get�o���ʒm�敪)<BR>
     * �o���ʒm�敪���擾����B<BR>
     * @@return String
     * @@roseuid 4084E05500B8
     */
    public String getDealedType()
    {
        return this.dealedType;
    }

	/**
	 * ������<BR>
	 * <BR>
	 * DB���C�A�E�g<BR>
	 * �u�敨OP�o���ʒm�L���[�e�[�u���v�Q�ƁB<BR>
	 */
	private Date execTimestamp;

	/**
	 * (set������)<BR>
	 * ���������Z�b�g����B<BR>
	 * @@param l_datExecTimestamp - ������
	 */
	public void setExecTimestamp(Date l_datExecTimestamp)
	{
		this.execTimestamp = l_datExecTimestamp;
	}

	/**
	 * (get������)<BR>
	 * ���������擾����B<BR>
	 * @@return String
	 * @@roseuid 4084E05500B8
	 */
	public Date getExecTimestamp()
	{
		return this.execTimestamp;
	}

     /**
     * (�X�V�l�ݒ�)<BR>
     *�imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     *  (OrderManagerPersistenceType�ɂĒ萔��`) <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderUnitParams - (�����P��Params)<BR>
     * �@@�����P�ʃI�u�W�F�N�g<BR>
     * @@return IfoOrderUnitParams
     * @@roseuid 40875C9F0270
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        String STR_METHOD_NAME = "mutate()";
        log.entering(STR_METHOD_NAME);

        if((WEB3DealedTypeDef.FULLY_EXECUTED.equals(this.dealedType))||
          (WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(this.dealedType)))
        {
	        l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
	        l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        }
            
        //ThreadLocal�ɐݒ肳��Ă���V�X�e���������Ԃ��擾����			
        Timestamp l_realTimestamp = 
                 (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
                 
        //�X�V���t��ݒ肷��
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(l_realTimestamp);
		
        //�����P�ʂɂЂ��Â��������擾����
        long l_orderID = l_ifoOrderUnitParams.getOrderId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Order l_ifoOrder = null;
        IfoOrderParams l_ifoOrderParams = null;

        try
        {
            l_ifoOrder = l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager().getOrder(l_orderID);
            l_ifoOrderParams = (IfoOrderParams)l_ifoOrder.getDataSourceObject();

            WEB3OptionOrderManagerImpl l_opOrderMgr = (WEB3OptionOrderManagerImpl)
                l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit =
                (IfoOrderUnit)l_opOrderMgr.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());

            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_beforeOrderUnitParams = new IfoOrderUnitParams(l_row);

            String l_strWLimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

            //���@@(*1)
            //  �E�����̏��� == "UNDO_EXECUTION"�̏ꍇ�A������Ɣ��肷��B
            //  �ExTrade�X�V�O�̒����P�ʁiIfoOrderUnitParams.�����P��ID�ɊY�����钍���P�ʂ�DB����
            //  �Ď擾�������́j�̒����L����� == "�N���[�Y"�ł���Ζ������ăI�[�v���Ɣ��肷��B
            //���@@(*2)�敨OP�f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()��"���~�b�g�����L��"�̏ꍇ�A���~�b�g�����L���B
            //  �����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
            //  �iIfoOrderUnitParams��OP�����}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
            if (OrderManagerPersistenceContext.UNDO_EXECUTION.equals(l_orderManagerPersistenceContext)
                && OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
                //��������
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�A0�FDEFAULT�i�����w��Ȃ��j
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //�����������Z�q
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrderCondOperator(null);

                //�t�w�l��l�^�C�v
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setStopPriceType(null);

                //�t�w�l��l
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setStopOrderPrice(null);

                //�iW�w�l�j�����w�l
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setWLimitPrice(null);

                //����������
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�A�X�V�O�̔�������
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrgOrderConditionType(
                    l_beforeOrderUnitParams.getOrderConditionType());

                //�������������Z�q
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�A�X�V�O�̔����������Z�q
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrgOrderCondOperator(
                    l_beforeOrderUnitParams.getOrderCondOperator());

                //���t�w�l��l�^�C�v
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�A�X�V�O�̋t�w�l��l�^�C�v
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrgStopPriceType(
                    l_beforeOrderUnitParams.getStopPriceType());

                //���t�w�l��l
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�A�X�V�O�̋t�w�l��l
                //�ȊO�A�i�����l�j
                if (l_beforeOrderUnitParams.getStopOrderPriceIsNull())
                {
                    l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_ifoOrderUnitParams.setOrgStopOrderPrice(
                        l_beforeOrderUnitParams.getStopOrderPrice());
                }

                //���iW�w�l�j�����w�l
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�A�X�V�O�́iW�w�l�j�����w�l
                //�ȊO�A�i�����l�j
                if (l_beforeOrderUnitParams.getWLimitPriceIsNull())
                {
                    l_ifoOrderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_ifoOrderUnitParams.setOrgWLimitPrice(
                        l_beforeOrderUnitParams.getWLimitPrice());
                }

                //���iW�w�l�j���s����
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�A�X�V�O�́iW�w�l�j���s����
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrgWLimitExecCondType(
                    l_beforeOrderUnitParams.getWLimitExecCondType());

                //�iW�w�l�j���s����
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setWLimitExecCondType(null);

                //���N�G�X�g�^�C�v
                //�������ăI�[�v��(*1) ���� ���~�b�g�����L��(*2)�̏ꍇ�A5�F����
                l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }

            //����.�X�V���t��ݒ肷��
            l_ifoOrderParams.setLastUpdatedTimestamp(l_realTimestamp);
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_queryProcessor.doUpdateQuery(l_ifoOrderParams);
		}
		catch (DataException l_dqe)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�������擾�ł��܂���ł����B" + "����ID=" + l_orderID, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }
    
	/**
	 * �imutate���\�b�h�̎����j<BR>
	 * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
	 * <BR>
	 * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
	 * <BR>
	 * �P�j�@@super.mutate(IfoOrderActionParams)���R�[������B<BR>
	 * <BR>
	 * �Q�j�@@�����ԗp�^�C���X�^���v��������Ԃ��擾���� <BR>
	 * �@@�|ThreadLocalSystemAttributesRegistry.getAttribute( )<BR> 
	 *   �ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�C�X.REAL_TIMESTAMP <BR>
	 * <BR>
     * �R�j�@@�쐬���t�E�X�V���t�̐ݒ�<BR> 
     * �@@�p�����[�^.��������Params.�쐬���t���Q�j�Ŏ擾�������ݎ���<BR> 
     * �@@�p�����[�^.��������Params.�X�V���t���Q�j�Ŏ擾�������ݎ���<BR> 
	 * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
	 * <BR>
	 * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * @@param l_context - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
	 * @@param l_ifoOrderActionParams - (��������Params)<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B
	 * @@return webbroker3.ifo.data.IfoOrderActionParams
	 */
	public IfoOrderActionParams mutate(
		OrderManagerPersistenceType l_updateType,
		OrderManagerPersistenceContext l_context,
		IfoOrderActionParams l_ifoOrderActionParams)
	{
		final String STR_METHOD_NAME =
			"mutate(OrderManagerPersistenceType l_updateType, "
				+ "OrderManagerPersistenceContext l_context, "
				+ "IfoOrderActionParams l_ifoOrderActionParams)";
		log.entering(STR_METHOD_NAME);

		if (l_ifoOrderActionParams == null)
		{
			log.error(
				STR_METHOD_NAME,
				new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80017,
					this.getClass().getName() + STR_METHOD_NAME));
			return l_ifoOrderActionParams;
        }

        //�X�[�p�[�N���X�̏������R�[������
        super.mutate(l_updateType, l_context, l_ifoOrderActionParams);
        
        //ThreadLocal�ɐݒ肳��Ă���V�X�e���������Ԃ��擾����			
        Timestamp l_realTimestamp = 
				 (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
                     
		//�쐬�����A�X�V������ݒ肷��
		l_ifoOrderActionParams.setCreatedTimestamp(l_realTimestamp);
		l_ifoOrderActionParams.setLastUpdatedTimestamp(l_realTimestamp);
        
		log.exiting(STR_METHOD_NAME);
		return l_ifoOrderActionParams;
	}

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * ���Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �����̒����P��Params.����ID�A<BR>
     * �����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.���Params�̎��ʃR�[�h�ɁA�����P�ʃI�u�W�F�N�g�̓������ڂ���<BR>
     * �l���Z�b�g���A�ԋp����B<BR>
     * �@@�p�����[�^.���Params�̖������ɁA�v���p�e�B�̓������ڂ���<BR>
     * �l���Z�b�g���A�ԋp����B<BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     *
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderExecutionParams - (���Params)<BR>
     * ���s�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.data.IfoOrderExecutionParams
     * @@roseuid 40875C9F0270
     */
    public IfoOrderExecutionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderExecutionParams l_ifoOrderExecutionParams)
    {
        final String STR_METHOD_NAME = ".mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderExecutionParams == null)
        {
            log.error(STR_METHOD_NAME, new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�擾�����P�ʃI�u�W�F�N�g
            long l_lngOrderUnitId = l_ifoOrderExecutionParams.getOrderUnitId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            // get orderUnitRow object
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //��n��
            l_ifoOrderExecutionParams.setDeliveryDate(l_ifoOrderUnitRow.getDeliveryDate());

			if((WEB3DealedTypeDef.FULLY_EXECUTED.equals(this.dealedType))||
			  (WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(this.dealedType)))
			{
            //���ʃR�[�h��ݒ肷��
            l_ifoOrderExecutionParams.setOrderRequestNumber(l_ifoOrderUnitRow.getOrderRequestNumber());
			}

			//��������ݒ肷��
			l_ifoOrderExecutionParams.setExecTimestamp(this.execTimestamp);

            //ThreadLocal�ɐݒ肳��Ă���V�X�e���������Ԃ��擾����			
			Timestamp l_realTimestamp = 
                     (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
                     
            //�X�V���t��ݒ肷��
			l_ifoOrderExecutionParams.setLastUpdatedTimestamp(l_realTimestamp);
			
			if(l_orderManagerPersistenceType.equals(OrderManagerPersistenceType.INSERT))
			{
	            //�쐬���t��ݒ肷��
				l_ifoOrderExecutionParams.setCreatedTimestamp(l_realTimestamp);
			}
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderExecutionParams;
    }

    /**
     * (�敨OP�o���ʒm�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.ifo.WEB3IfoExecuteNotifyUpdateInterceptor
     * @@roseuid 40875F10035B
     */
    public WEB3IfoExecuteNotifyUpdateInterceptor()
    {

    }
}
@
