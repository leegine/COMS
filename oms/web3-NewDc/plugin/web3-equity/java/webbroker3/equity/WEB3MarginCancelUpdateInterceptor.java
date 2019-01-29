head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����X�V�C���^�Z�v�^(WEB3MarginCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
                   2004/12/09 �����iSRA�j�c�Č��Ή�
                   2005/01/06 ���� (SRA) JavaDoc�C��
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����X�V�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ��������o�^���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * �iEqTypeOrderManagerPersistenceEventInterceptor�̎����j
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCancelUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCancelUpdateInterceptor.class);

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
     * @@param    l_trader           - �㗝���͎�<BR>
     */
    public WEB3MarginCancelUpdateInterceptor(
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
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �X�V���e�́ADB�ݒ�_���u�M�p���_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * 
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 40D2975C0185
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = "WEB3MarginCancelUpdateInterceptor:mutate()";
        log.entering(STR_METHOD_NAME);

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
        
        //���������E����敪���Z�b�g
        if (l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            //�S���������
            log.debug("�s��ɖ����M�̒����̎���̏ꍇ�F�@@3:�S���������");             
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        }
        else
        {
            //�����
            log.debug("�s��ɑ��M�ς̒����̎���̏ꍇ�F�@@1:�����");
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELING);
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
        
        //�����G���[���R�R�[�h���Z�b�g
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        // ����������
        // this.�s�ꂩ��m�F�ς̐��� == Double.NaN�i=�s�ꖢ���M�j�̏ꍇ
        if (l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            l_orderUnitParams.setOriginalQuantity(0);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (�M�p����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return WEB3MarginCancelUpdateInterceptor
     * @@roseuid 40D28AEB01B4
     */
    public WEB3MarginCancelUpdateInterceptor WEB3EquityCancelUpdateInterceptor() 
    {
        return null;
    }
}
@
