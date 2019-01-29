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
filename	WEB3AdminBondExecuteUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Җ��X�V�C���^�Z�v�^(WEB3AdminBondExecuteUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
                 : 2006/10/12 ꎉ�   (���u) WEB�V�J���W���̌������̑Ή��inewBigDecimal�����j
                 : 2006/10/16  �����F (���u) �c�a�X�V�d�lNo.019
*/

package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (���Ǘ��Җ��X�V�C���^�Z�v�^)<BR>
 * ���Ǘ��Җ��X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteUpdateInterceptor extends WEB3AdminBondExecuteCommonInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteUpdateInterceptor.class);
    
    /**
     * (���Ǘ��Җ��X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D969AD0157
     */
    public WEB3AdminBondExecuteUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * �P�j�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.�������P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e�́@@�u�����_�������P�ʃe�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�Q�ƁB <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D969AD0138
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
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        //���������.get��n��
        l_params.setDeliveryDate(this.getBondExecuteDateInfo().getDeliveryDate());
        
        //���������.get���n��n��
        l_params.setForeignDeliveryDate(this.getBondExecuteDateInfo().getForeignDeliveryDate());
        
        //�i�����l�j+ �v�Z�T�[�r�X.calc��������i����,�P��,�������j
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_provider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_params.getExecutedQuantity()));
        BigDecimal l_bdPrice = this.getBondEstimatedPriceCalcResult().getPrice();
        BigDecimal l_bdtemp = null;
        try
        {
            l_bdtemp = l_provider.calcTradingPrice(l_bdQuantity, l_bdPrice, this.getBondProduct());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        if (l_bdtemp != null)
        {
            l_params.setExecutedAmount(
                new BigDecimal(String.valueOf(l_params.getExecutedAmount())).add(l_bdtemp).doubleValue());
            
            //����n����v�Z����.get�P��
            l_params.setExecutedPrice(l_bdtemp.doubleValue());
        }
        
        //1�F����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.EXECUTED);
                
        //����n����v�Z����.get�בփ��[�g
        if (this.getBondEstimatedPriceCalcResult().getFxRate() != null)
        {
            BigDecimal l_bdFxRate = this.getBondEstimatedPriceCalcResult().getFxRate();
            l_params.setExecFxRate(l_bdFxRate.doubleValue());
        }
        
        //���P��
        if (this.getBondEstimatedPriceCalcResult().getPrice() != null)
        {
            BigDecimal l_bdExecutedPrice = this.getBondEstimatedPriceCalcResult().getPrice();
            l_params.setExecutedPrice(l_bdExecutedPrice.doubleValue());
        }
        
        //����n����v�Z����.get��������i�~�݁j
        if (this.getBondEstimatedPriceCalcResult().getTradingPrice() != null)
        {
            BigDecimal l_bdTradingPrice = this.getBondEstimatedPriceCalcResult().getTradingPrice();
            l_params.setTradingPrice(l_bdTradingPrice.doubleValue());
        }
        
        //����n����v�Z����.get��������i�O�݁j
        if (this.getBondEstimatedPriceCalcResult().getForeignTradePrice() != null)
        {
            BigDecimal l_bdForeignTradingPrice = 
                this.getBondEstimatedPriceCalcResult().getForeignTradePrice();
            l_params.setForeignTradingPrice(l_bdForeignTradingPrice.doubleValue());
        }
        
        //����n����v�Z����.get�o�ߗ��q�i�~�݁j
        if (this.getBondEstimatedPriceCalcResult().getAccruedInterest() != null)
        {
            BigDecimal l_bdAccruedInterest = 
                this.getBondEstimatedPriceCalcResult().getAccruedInterest();
            l_params.setAccruedInterest(l_bdAccruedInterest.doubleValue());
        }
        
        //����n����v�Z����.get�o�ߗ��q�i�O�݁j
        if (this.getBondEstimatedPriceCalcResult().getForeignAccruedInterest() != null)
        {
            BigDecimal l_bdForeignAccruedInterest = 
                this.getBondEstimatedPriceCalcResult().getForeignAccruedInterest();
            l_params.setForeignAccruedInterest(l_bdForeignAccruedInterest.doubleValue());
        }
        
        //����n����v�Z����.get��n����i�~�݁j
        if (this.getBondEstimatedPriceCalcResult().getEstimatedPrice() != null)
        {
            BigDecimal l_bdEstimatedPrice = 
                this.getBondEstimatedPriceCalcResult().getEstimatedPrice();
            l_params.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());
        }
        
        //����n����v�Z����.get��n����i�O�݁j
        if (this.getBondEstimatedPriceCalcResult().getForeignEstimatedPrice() != null)
        {
            BigDecimal l_bdForeignEstimatedPrice = 
                this.getBondEstimatedPriceCalcResult().getForeignEstimatedPrice();
            l_params.setForeignEstimatedPrice(l_bdForeignEstimatedPrice.doubleValue());
        }
        
        //����n����v�Z����.get�o�ߓ���
        if (this.getBondEstimatedPriceCalcResult().getElapsedDays() != null)
        {
            l_params.setElapsedDays(this.getBondEstimatedPriceCalcResult().getElapsedDays());
        }
        else
        {
            l_params.setElapsedDays(null);
        }
        
        //����n����v�Z����.get�����
        if (this.getBondEstimatedPriceCalcResult().getCalcBaseDays() != null)
        {
            l_params.setCalcBaseDays(this.getBondEstimatedPriceCalcResult().getCalcBaseDays());
        }
        else
        {
            l_params.setCalcBaseDays(null);
        }
        
        //���������.get����
        l_params.setExecDate(this.getBondExecuteDateInfo().getExecuteDate());
        
        //���������.get���n����
        l_params.setForeignExecDate(this.getBondExecuteDateInfo().getForeignExecuteDate());
        
        //���������.get������
        l_params.setPaymentDate(this.getBondExecuteDateInfo().getPaymentDate());
        
        //�C���^�Z�v�^.get�J�X�g�f�B�A���R�[�h
        l_params.setCustodianCode(this.getCustodianCode());
        
        //0�F�����M
        l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
        
        //�Ǘ���.get�Ǘ��҃R�[�h
        l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
