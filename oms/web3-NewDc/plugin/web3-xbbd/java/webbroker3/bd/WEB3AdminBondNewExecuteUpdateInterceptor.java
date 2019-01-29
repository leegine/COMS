head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondNewExecuteUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��ҐV�K���X�V�C���^�Z�v�^(WEB3AdminBondNewExecuteUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
                   2006/10/12 ꎉ�   (���u) WEB�V�J���W���̌������̑Ή��inewBigDecimal�����j
*/

package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (���Ǘ��ҐV�K���X�V�C���^�Z�v�^)<BR>
 * ���Ǘ��ҐV�K���X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondNewExecuteUpdateInterceptor extends WEB3AdminBondExecuteCommonInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondNewExecuteUpdateInterceptor.class);
    
    /**
     * (���Ǘ��ҐV�K���X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44CCA6E502CD
     */
    public WEB3AdminBondNewExecuteUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     *  <BR>
     * �P�j�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.�������P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e�́@@�u���V�K���_�������P�ʃe�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�Q�ƁB <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44CCA2770203
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_params == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�g�����ڃZ�b�g     
        //�i�����l�j+ �v�Z�T�[�r�X.get��������i����,�P��,�������j
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_provider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();
        BigDecimal l_bdQuantity = this.getBondEstimatedPriceCalcResult().getQuantity();
        BigDecimal l_bdPrice = this.getBondEstimatedPriceCalcResult().getPrice();
        BigDecimal l_bdtemp;
        try
        {
            l_bdtemp = 
                l_provider.calcTradingPrice(l_bdQuantity, l_bdPrice, this.getBondProduct());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        l_params.setExecutedAmount(
            new BigDecimal(String.valueOf(l_params.getExecutedAmount())).add(l_bdtemp).doubleValue());
        
        //1�F����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.EXECUTED);
        
        //��בփ��[�g
        if (this.getBondEstimatedPriceCalcResult().getFxRate() != null)
        {
            l_params.setExecFxRate(
                this.getBondEstimatedPriceCalcResult().getFxRate().doubleValue());
        }
        else 
        {
            l_params.setExecFxRate(null);
        }
        
        //�s�ꂩ��m�F�ς݂̎w�l
        if (this.getBondEstimatedPriceCalcResult().getPrice() != null)
        {
            l_params.setExecutedPrice(
                this.getBondEstimatedPriceCalcResult().getPrice().doubleValue());
        }
        else 
        {
            l_params.setExecutedPrice(null);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
