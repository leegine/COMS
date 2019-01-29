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
filename	WEB3FeqChangeUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������X�V�C���^�Z�v�^(WEB3FeqChangeUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  ����(���u) �V�K�쐬
                 : 2005/07/28 ������(���u) ���r���[
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O�����������X�V�C���^�Z�v�^)<BR>
 * �O�����������X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@ author ���� 
 * @@ version 1.0 
 */
public class WEB3FeqChangeUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{
    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeUpdateInterceptor.class);
        
    /**
     * (�������e)<BR>
     * �O�����������������e�I�u�W�F�N�g<BR>
     */
    private WEB3FeqChangeOrderSpec orderSpec;
    
    /**
     * (���z�v�Z����)<BR>
     * �O���������z�v�Z���ʃI�u�W�F�N�g<BR>
     */
    private WEB3FeqAmountCalcResult amountRound;
    
    /**
     * (�v�Z�P��)<BR>
     * �v�Z�P��<BR>
     */
    private double roundPrice;
    
    /**
     * (�㗝���͎�)<BR>
     * �㗝���͎�<BR>
     */
    private Trader trader;
    
    
    /**
     * @@roseuid 42D0D26B034B
     */
    public WEB3FeqChangeUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�O�����������X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �p�����[�^�̍��ڂ��A�e�v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_orderSpec - (�������e)<BR>
     * �������e
     * 
     * @@param l_amountRound - (�v�Z����)
     * �v�Z����
     * 
     * @@param l_dblRoundPrice - (�v�Z�P��)
     * �v�Z�P��
     * 
     * @@param l_trader - (�㗝���͎�)
     * �㗝���͎�
     * @@roseuid 42975C180129
     */
    public WEB3FeqChangeUpdateInterceptor(
        WEB3FeqChangeOrderSpec l_orderSpec, 
        WEB3FeqAmountCalcResult l_amountRound, 
        double l_dblRoundPrice, 
        Trader l_trader) 
    {
        this.orderSpec = l_orderSpec;
        this.amountRound = l_amountRound;
        this.roundPrice = l_dblRoundPrice;
        this.trader = l_trader;
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@super.mutate(OrderManagerPersistenceType,<BR>
     * �@@ OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V<BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR> 
     * <BR>
     * ���ڐݒ���e�́A<BR>
     * DB�X�V�d�l�u����_�O�������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v
     * @@param l_context - (����)<BR>
     * ����
     * @@param l_feqOrderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j
     * @@return FeqOrderUnitParams
     * @@roseuid 42975C1401E4
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
        
        if (l_feqOrderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }    
        
        //����.�X�V�^�C�v != null�̏ꍇ�A�����iͯ�ށj�e�[�u���X�V���s��
        if (l_updateType != null)
        {
            //�P�j�@@�����iͯ�ށj�e�[�u���X�V
            l_feqOrderUnitParams = super.mutate(
                l_updateType,
                l_context,
                l_feqOrderUnitParams);
        }
                
        //�Q�j�@@�����P�ʃe�[�u���X�V                    
        //���s����
        //�������e.getExecConditionType( )
        l_feqOrderUnitParams.setExecutionConditionType(this.orderSpec.getChangeExecutionCondition());
        //�����������Z�q
        //�������e.get���������������Z�q()
        l_feqOrderUnitParams.setOrderCondOperator(this.orderSpec.getChangeOrderCondOperator());
        
        //�������e.get�������������P��()
        //�i* ����������0�FDEFAULT�i�����w��Ȃ��j�̏ꍇ�́Anull�j
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderSpec.getOrderConditionType()))
        {
            l_feqOrderUnitParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderUnitParams.setStopOrderPrice(this.orderSpec.getChangeOrderCondPrice());
        }

        //�������e.get�����iW�w�l�j�����w�l()
        //�i* �����������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull�j
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderSpec.getOrderConditionType())
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderSpec.getOrderConditionType()))
        {
            l_feqOrderUnitParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderUnitParams.setWLimitPrice(this.orderSpec.getChangeWLimitPrice());
        }

        //�����������t  
        //�������e.get���������L������( )
        if (this.orderSpec.getChangeOrderExpirationDate() != null)
        {        
            l_feqOrderUnitParams.setExpirationDate(this.orderSpec.getChangeOrderExpirationDate());
        }
        //�������   
        if (l_feqOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        }
        else
        {
            l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
        }
        //�����P��   
        //�C���^�Z�v�^.get�����P��( )�i* �T�Z��n����Z�o�Ɏg�p�����P���j               
        l_feqOrderUnitParams.setPrice(this.roundPrice);
        //�T�Z��n���    
        //���z�v�Z����.get��n���( )    
        l_feqOrderUnitParams.setEstimatedPrice(this.amountRound.getNetAmount());
        //�T�Z��n����i�O�݁j 
        //���z�v�Z����.get��n����i�O�݁j( )       
        l_feqOrderUnitParams.setFEstimatedPrice(this.amountRound.getNetAmountFc());
        //���������E����敪  
        if (l_feqOrderUnitParams.getConfirmedQuantityIsNull())
        {
            WEB3FeqOrderUnit l_orderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);
            if (l_orderUnit.isUnexecuted())
            {
                l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
            }
            else
            {
                l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
            }
        }
        else
        {
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGING);
        }
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);   

        //�����o�H�敪 
        //�Z�b�V�������擾���������ڂ̒l
      
        String l_orderRootDiv ="";
        String l_orderRootDivString = WEB3SessionAttributeDef.ORDER_ROOT_DIV;
        l_orderRootDiv = l_opLoginSec.getSessionProperty(l_orderRootDivString);
        l_feqOrderUnitParams.setOrderRootDiv(l_orderRootDiv);

        //�����G���[���R�R�[�h
        //0000�F����
        l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        // �X�V�҃R�[�h
        // �ڋq���͂̏ꍇ
        if (this.trader == null)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();

            // �ڋq�R�[�h���擾
            String l_strAccountCode;
            try
            {
                l_strAccountCode = l_accountMgr.getMainAccount(l_feqOrderUnitParams.getAccountId()).getAccountCode();
            }
            catch (NotFoundException l_ex)
            {
                log.error("�Y������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_feqOrderUnitParams.setLastUpdater(l_strAccountCode);
        }
        // �㗝���͂̏ꍇ
        else
        {
            l_feqOrderUnitParams.setLastUpdater(this.trader.getTraderCode());
        }  
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }
    
    /**
     * (�i���������j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̓����\�b�h���{�B<BR>
     * �@@-super.mutate()�����{����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̎葱�������{����B<BR>
     * �@@�E�X�e�C�^�X�ύX<BR>
     * �@@�@@-�p�����[�^.��������Params.�����C�x���g�^�C�v�Ɂh�ύX�����h���Z�b�g����B<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v
     * @@param l_context - (����)<BR>
     * ����
     * @@param l_feqOrderUnitParams - (���������s)<BR>
     * ���������s�i�F��������Params�j
     * @@return FeqOrderActionParams
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderActionParams l_feqOrderActionParams)
    {
        final String STR_METHOD_NAME =
              "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        l_feqOrderActionParams =
            super.mutate(
                l_updateType,
                l_context,
                l_feqOrderActionParams);
        
        l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }
    
    /**
     * (get�������e)<BR>
     * this.�������e��ԋp����B<BR>
     * @@return WEB3FeqChangeOrderSpec
     * @@roseuid 4299E919029F
     */
    public WEB3FeqChangeOrderSpec getOrderSpec() 
    {
        return this.orderSpec;
    }
    
    /**
     * (get���z�v�Z����)<BR>
     * this.���z�v�Z���ʂ�ԋp����B<BR>
     * @@return WEB3FeqAmountCalcResult
     * @@roseuid 4299E91902A0
     */
    public WEB3FeqAmountCalcResult getAmountRound() 
    {
        return this.amountRound;
    }
    
    /**
     * (get�v�Z�P��)<BR>
     * this.�v�Z�P����ԋp����B<BR>
     * @@return double
     * @@roseuid 4299E91902A1
     */
    public double getRoundPrice() 
    {
        return this.roundPrice;
    }
    
    /**
     * (get�㗝���͎�)<BR>
     * this.�㗝���͎҂�ԋp����B<BR>
     * @@return Trader
     * @@roseuid 4299ECAE02ED
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
}
@
