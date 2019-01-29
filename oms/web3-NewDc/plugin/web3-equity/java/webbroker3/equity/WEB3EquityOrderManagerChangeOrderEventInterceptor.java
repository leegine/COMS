head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderManagerChangeOrderEventInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������C���^�Z�v�^(WEB3EquityOrderManagerChangeOrderEventInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 ����� (���u) �V�K�쐬
Revesion History : 2004/12/07 ���� (SRA) �c�Č��Ή�
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/02 �đo�g (���u) �c�a�X�V�d�lNo.169
Revesion History : 2007/12/19 ��іQ (���u) �c�a�X�V�d�lNo.207
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderManagerChangeOrderEventInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderManagerChangeOrderEventInterceptor.class);
    
    /**
     * (�����o�H�敪)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (�㗝���͎�)<BR>
     */
    private WEB3GentradeTrader trader = null;
    
    /**
     * (�R���X�g���N�^)�B<BR>
     * @@param    l_orderRootdiv - �����o�H�敪<BR>
     * @@param    l_trader       - ����<BR>
     */
    public WEB3EquityOrderManagerChangeOrderEventInterceptor(
        String l_strOrderRootdiv, WEB3GentradeTrader l_trader)
    {
        this.orderRootDiv = l_strOrderRootdiv;
        this.trader = l_trader;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * <BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �g�����ڃZ�b�g<BR>
     * �@@this.�����������e�v���p�e�B����A�p�����[�^.�����P��Params��<BR>
     * �@@�g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * �X�V���e�́A<BR>
     * �u�i������������[�������s]�j���������P�ʃe�[�u���v�Q�ƁB<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_eqtypeOrderUnitParams - (�����P��Params)<BR>
     * <BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     * @@roseuid 4044170803A9
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams) ";

        log.entering(STR_METHOD_NAME);
        
        WEB3EquityNewCashBasedOrderSpec equityOrderSpec = this.getEquityOrderSpec();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingMod.getOrderManager();
        OrderUnit l_orderUnit = l_orderMgr.toOrderUnit(l_eqtypeOrderUnitParams);
        
        //���� = �C���^�Z�v�^�̃v���p�e�B.�㗝���͎�.�����ID
        //  ���C���^�Z�v�^�̃v���p�e�B.�㗝���͎�==null�̏ꍇ�́Anull���Z�b�g�B
        if (this.trader == null)
        {
            l_eqtypeOrderUnitParams.setTraderId(null);
        }
        else
        {
            l_eqtypeOrderUnitParams.setTraderId(this.trader.getTraderId());
        }

        //���s���� = �����������e.getExecConditionType( )
        l_eqtypeOrderUnitParams.setExecutionConditionType(equityOrderSpec.getExecConditionType());
            
        // �l�i����
        l_eqtypeOrderUnitParams.setPriceConditionType(equityOrderSpec.getPriceConditionType());
            
        //�����������t = �����������e.getOrderExpDate( )            
        l_eqtypeOrderUnitParams.setExpirationDate(equityOrderSpec.getOrderExpDate());
                        
        //�������� = �����������e.get��������( )
        l_eqtypeOrderUnitParams.setOrderConditionType(
            equityOrderSpec.getOrderCond());

        //�i����������"DEFAULT�i�����w��Ȃ��j"�̏ꍇ��null�Z�b�g
        if (equityOrderSpec.getOrderCond().equals(WEB3OrderingConditionDef.DEFAULT))
        {                
            l_eqtypeOrderUnitParams.setOrderCondOperator(null);     // �����������Z�q                
            l_eqtypeOrderUnitParams.setStopOrderPrice(null);        // �t�w�l��l                
            l_eqtypeOrderUnitParams.setWLimitPrice(null);           // �iW�w�l�j�����w�l
        }
        else
        {
            //�����������Z�q = �����������e.get�����������Z�q( )
            l_eqtypeOrderUnitParams.setOrderCondOperator(
                equityOrderSpec.getOrderCondOperator());

            //�t�w�l��l = �����������e.get�t�w�l��l( )
            l_eqtypeOrderUnitParams.setStopOrderPrice(
                equityOrderSpec.getStopLimitPriceBasePrice());

            // �iW�w�l�j�����w�l    �i�i0�FDEFAULT�A1�F�t�w�l�j�̏ꍇ��null�Z�b�g�j
            if (equityOrderSpec.getOrderCond().equals(WEB3OrderingConditionDef.STOP_LIMIT_PRICE))
            {
                l_eqtypeOrderUnitParams.setWLimitPrice(null);   // �iW�w�l�j�����w�l
            }
            else
            {                
                l_eqtypeOrderUnitParams.setWLimitPrice(
                    equityOrderSpec.getWLimitPriceChange());
            }                
        }
            

        // �s�ꂩ��m�F�ς݂̐���==Double.NaN�i���s�ꖢ���M�j            
        if (l_eqtypeOrderUnitParams.getConfirmedQuantityIsNull())
        {
            // �������
            // �p�����[�^.�����P��Params.������ԁiorderStatus�j�ɁhMODIFIED�h���Z�b�g
            l_eqtypeOrderUnitParams.setOrderStatus(
                OrderStatusEnum.MODIFIED);
                              
            //���������E����敪
            //    this.isUnexecuted( )==true�̏ꍇ�́A7�F�S�����������B
            //    this.isUnexecuted( )==false�̏ꍇ�́A6�F�ꕔ���������B
            if (l_orderUnit.isUnexecuted())
            {
                l_eqtypeOrderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CHANGED);
            }
            else
            {
                l_eqtypeOrderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
            }      

        }
        // �s�ꂩ��m�F�ς݂̐���!=Double.NaN�i���s�ꑗ�M�ρj
        else
        {
            // �������
            //�p�����[�^.�����P��Params.������ԁiorderStatus�j�ɁhMODIFY_ACCEPTED�h���Z�b�g
            l_eqtypeOrderUnitParams.setOrderStatus(
                OrderStatusEnum.MODIFY_ACCEPTED);
                
            // ���������E����敪
            l_eqtypeOrderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.CHANGING);    
                            
        }            

        //�����P�� = �����������e.get�����P��( )
        l_eqtypeOrderUnitParams.setPrice(
            equityOrderSpec.getOrderUnitPrice());

        //�T�Z��n��� = �����������e.get�T�Z��n���( )
        l_eqtypeOrderUnitParams.setEstimatedPrice(
            equityOrderSpec.getEstimateDeliveryAmount());

        //�����o�H�敪 = �C���^�Z�v�^�̃v���p�e�B.�����o�H�敪
        l_eqtypeOrderUnitParams.setOrderRootDiv(this.orderRootDiv);

        //�����o�H�敪
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        String l_strSubmitOrderRouteDiv = null;
        try
        {
            l_strSubmitOrderRouteDiv =
                l_frontOrderService.getChangeSubmitOrderRouteDiv(
                    (EqTypeOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_wbe.getMessage(), l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_wbe.getMessage(), l_wbe);
        }
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

        //�����G���[���R�R�[�h
        l_eqtypeOrderUnitParams.setErrorReasonCode(
            WEB3ErrorReasonCodeDef.NORMAL);
                        
        //����Rev.
        WEB3GentradeFinObjectManager l_finOjbectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_eqtypeOrderUnitParams.getMarketId());
        }
        catch(NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        String l_strChangeOrderRev = null;
        try
        {
            //PTS�s�꒍���̏ꍇ�F
            if (l_market.isPTSMarket())
            {
                //���������T�[�r�X.getPTS����������Rev()�̖߂�l
                l_strChangeOrderRev =
                    l_frontOrderService.getPTSChangeOrderRev((EqTypeOrderUnit)l_orderUnit);
            }
            else
            {
                //���������T�[�r�X.get����������Rev()�̖߂�l
                l_strChangeOrderRev =
                    l_frontOrderService.getChangeOrderRev((EqTypeOrderUnit)l_orderUnit);
            }
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_wbe.getMessage(), l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_wbe.getMessage(), l_wbe);
        }
        l_eqtypeOrderUnitParams.setOrderRev(l_strChangeOrderRev);

        // �iW�w�l�j���s����
        //�����������e.get(W�w�l)���s����( )
        //�������A�����������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull�B
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_eqtypeOrderUnitParams.getOrderConditionType())
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_eqtypeOrderUnitParams.getOrderConditionType()))
        {
            l_eqtypeOrderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            l_eqtypeOrderUnitParams.setWLimitExecCondType(equityOrderSpec.getWlimitExecCondType());
        }

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̓����\�b�h���{ <BR>
     * �@@-super.mutate()�����{����B <BR>
     * <BR>
     * �Q�j�@@�ȉ��̎葱�������{����B <BR>
     * <BR>
     * �E�X�e�C�^�X�ύX <BR>
     * �@@-�p�����[�^.��������Params.�����C�x���g�^�C�v�Ɂh�ύX�����h���Z�b�g����B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_eqtypeOrderActionParams - (������������Params)<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams
     * @@roseuid 404BCF6C0387
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_eqtypeOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams) ";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�X�[�p�[�N���X�̓����\�b�h���{
        //�|super.mutate()�����{����B
        EqtypeOrderActionParams l_orderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_eqtypeOrderActionParams);

        //�X�e�C�^�X�ύX
        // �����C�x���g�^�C�v���h2�F�ύX�����h�Z�b�g
        l_eqtypeOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }

}
@
