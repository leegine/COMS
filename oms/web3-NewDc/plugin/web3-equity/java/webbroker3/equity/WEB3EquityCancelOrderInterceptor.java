head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCancelOrderInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������C���^�Z�v�^(WEB3EquityCancelOrderInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 ���_�� (���u) �V�K�쐬
                   2004/12/07 �����iSRA�j �c�Č��Ή�
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * �i������������C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityCancelOrderInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCancelOrderInterceptor.class);
    
    /**
     * (�����o�H�敪)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (�㗝���͎�)<BR>
     */
    private WEB3GentradeTrader trader = null;
    
    /**
     * �i��n����j�B
     */
    private double deliveryPrice = 0.0;

    /**
     * (�R���X�g���N�^)�B<BR>
     * @@param    l_dblDeliveryPrice - ��n���
     * @@param    l_orderRootdiv     - �����o�H�敪<BR>
     * @@param    l_trader           - ����<BR>
     */
    public WEB3EquityCancelOrderInterceptor(
        double l_dblDeliveryPrice,
        String l_strOrderRootdiv,
        WEB3GentradeTrader l_trader)
    {
        this.deliveryPrice = l_dblDeliveryPrice;
        this.orderRootDiv = l_strOrderRootdiv;
        this.trader = l_trader;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �i������Ԃ��h��t�ς݁i��������j�F<BR>
     * CANCEL_ACCEPTED�h�܂��́h�����ς݁i�������)<BR>
     * �FCANCELLED�h�̏ꍇ�̂ݏ��������{����B<BR>
     * �@@�ȊO�́A���������̂܂ܕԋp���������I������j<BR>
     * �@@�����P��Params�̒l���u��������v�̏�Ԃɐݒ肵�ԋp����B<BR>
     * <BR>
     * �X�V���e�́A�u�������_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     *<BR>
     * @@param l_manage - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     * @@roseuid 403EEAE5029A
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_manage,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderUnitParams)";

        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // ������ԁ��h12�F��t�ς݁i��������j�h���́h14�F�����ς݁i��������j�h�̏ꍇ�̂�
        // �u��������v��Ԃɐݒ肷��B
        if (l_orderUnitParams.getOrderStatus().equals(OrderStatusEnum.CANCEL_ACCEPTED)
            || l_orderUnitParams.getOrderStatus().equals(OrderStatusEnum.CANCELLED))
        {
            //���� = �C���^�Z�v�^�̃v���p�e�B.�㗝���͎�.�����ID
            //  ���C���^�Z�v�^�̃v���p�e�B.�㗝���͎�==null�̏ꍇ�́Anull���Z�b�g�B
            if (this.trader == null)
            {
                l_orderUnitParams.setTraderId(null);
            }
            else
            {
                l_orderUnitParams.setTraderId(this.trader.getTraderId());
            }
            
            //�T�Z��n���z�ݒ�
            l_orderUnitParams.setEstimatedPrice(this.deliveryPrice);
            
            // �s�ꂩ��m�F�ς݂̐���==Double.NaN�i���s�ꖢ���M�j            
            if (l_orderUnitParams.getConfirmedQuantityIsNull())
            {                    
                // ���������E����敪�i3�F�S����������j
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCELED);
            }
            // �s�ꂩ��m�F�ς݂̐���!=Double.NaN�i���s�ꑗ�M�ρj
            else
            {
                // ���������E����敪�i1�F������j
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCELING);     
            }

            //�����o�H�敪 = �C���^�Z�v�^�̃v���p�e�B.�����o�H�敪
            l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
            
            //�����o�H�敪
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
            String l_strSubmitOrderRouteDiv = null;
            try
            {
                l_strSubmitOrderRouteDiv =
                    l_frontOrderService.getChangeSubmitOrderRouteDiv(l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.error(l_wbe.getMessage(), l_wbe);
                throw new WEB3BaseRuntimeException(
                    l_wbe.getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_wbe.getMessage(), l_wbe);
            }
            l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
            
            //�����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
