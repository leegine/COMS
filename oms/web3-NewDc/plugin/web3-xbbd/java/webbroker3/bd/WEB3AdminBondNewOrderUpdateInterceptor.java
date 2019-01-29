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
filename	WEB3AdminBondNewOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��ҐV�K�����X�V�C���^�Z�v�^(WEB3AdminBondNewOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
                 : 2006/10/16 ��іQ (���u) �c�a�X�V�d�lNo.017
*/

package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3HostReflectDivDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (���Ǘ��ҐV�K�����X�V�C���^�Z�v�^)<BR>
 * ���Ǘ��ҐV�K�����X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0  
 */
public class WEB3AdminBondNewOrderUpdateInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondNewOrderUpdateInterceptor.class);
    
    /**
     * (�g�����V�K�������e)<BR>
     * �g�����V�K�������e<BR>
     */
    private WEB3BondNewOrderSpec bondNewOrderSpec;
    
    /**
     * (���Ǘ��ҐV�K�����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 44C73A7E0220
     */
    public WEB3AdminBondNewOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * �P�j�g�����ڃZ�b�g<BR>
     * �@@�P�|�P�j�p�����[�^.�������P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e�� �u���V�K����_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44C73A7E023F
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
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //���t�A���p�̏ꍇ�A92(�����d�؎��)
        //����̏ꍇ�A35(��W���)
        if (this.getBondNewOrderSpec().getBondOrderTypeJudge().isRecruitOrder())
        {
            l_params.setDealType(WEB3DealTypeDef.RECRUIT_TRADING);
        }
        else if (
            this.getBondNewOrderSpec().getBondOrderTypeJudge().isBuyOrder() || 
            this.getBondNewOrderSpec().getBondOrderTypeJudge().isSellOrder())
        {
            l_params.setDealType(WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);
        }
        
        //���������.get��n��(* ��ʓ��́j
        l_params.setDeliveryDate(this.getBondExecuteDateInfo().getDeliveryDate());
        
        //���������.get���n��n��(* ��ʓ��́j
        l_params.setForeignDeliveryDate(this.getBondExecuteDateInfo().getForeignDeliveryDate());
        
        //1�F������
        l_params.setLockStatus(WEB3LockStatusDef.RELEASING);
        
        //0�F�����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);
        
        //���������.get���n������
        
        l_params.setForeignBizDate(WEB3DateUtility.formatDate(
            this.getBondExecuteDateInfo().getForeignBizDate(), "yyyyMMdd"));
        
        //WEB3ChannelDef.�c�ƓX
        l_params.setOrderChanel(WEB3ChannelDef.BRANCH);
        
        //�T�[�o���ŃT�[�r�X���N�����ꂽ���ԁi�v�Z�����i���ʁj(*2) �������t�@@���Q�Ɓj
        l_params.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //�ڋq.���҃R�[�h�iSONAR�j�i* SONAR�ŊǗ����Ă���ڋq�̈��ҁj
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        MainAccountRow l_mainAccountRow;
        try
        {
            l_mainAccountRow = 
                (MainAccountRow)l_accountManager.getMainAccount(l_params.getAccountId()).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_params.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
        
        //���V�K�������e.getLimitPrice()(* ��ʓ��́j
        l_params.setPrice(this.getBondNewOrderSpec().getLimitPrice());
        
        //�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        try
        {
            String l_strNewNumber = 
                l_hostReqOrderNumberManageService.getNewNumber(
                    this.getAdministrator().getInstitutionCode(),
                    this.getAdministrator().getBranchCode(),
                    ProductTypeEnum.BOND);
            l_params.setOrderRequestNumber(l_strNewNumber);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }      
        
        //������.get���^�C�v
        l_params.setBondType(this.getBondProduct().getBondType());
        
        //������.get�ʉ݃R�[�h
        l_params.setCurrencyCode(this.getBondProduct().getCurrencyCode());
        
        //���V�K�������e.get���ϋ敪() (* ��ʓ��́j
        l_params.setSettlementDiv(this.getBondNewOrderSpec().getSettlementDiv());
        
        //������.get�������敪
        l_params.setAutoExecDiv(this.getBondProduct().getAutoExecDiv());
        
        //null
        l_params.setExecutedPrice(null);
        
        //����n����v�Z����.get�בփ��[�g
        if (this.getBondEstimatedPriceCalcResult().getFxRate() != null)
        {
            double l_dblFxRate = this.getBondEstimatedPriceCalcResult().getFxRate().doubleValue();
            l_params.setBaseFxRate(l_dblFxRate);
        }
        
        //null
        l_params.setExecFxRate(null);
        
        //����n����v�Z����.get��������i�~�݁j
        if (this.getBondEstimatedPriceCalcResult().getTradingPrice() != null)
        {
            BigDecimal l_bdTradingPrice = this.getBondEstimatedPriceCalcResult().getTradingPrice();
            l_params.setTradingPrice(l_bdTradingPrice.doubleValue());
        }
        
        //����n����v�Z����.get��������i�O�݁j
        if (this.getBondEstimatedPriceCalcResult().getForeignTradePrice() != null)
        {
            BigDecimal l_bdForeignTradingPrice = this.getBondEstimatedPriceCalcResult().getForeignTradePrice();
            l_params.setForeignTradingPrice(l_bdForeignTradingPrice.doubleValue());
        }
        
        //����n����v�Z����.get�o�ߗ��q�i�~�݁j
        if (this.getBondEstimatedPriceCalcResult().getAccruedInterest() != null)
        {
            BigDecimal l_bdAccruedInterest = this.getBondEstimatedPriceCalcResult().getAccruedInterest();
            l_params.setAccruedInterest(l_bdAccruedInterest.doubleValue());
        }
        
        //����n����v�Z����.get�o�ߗ��q�i�O�݁j
        if (this.getBondEstimatedPriceCalcResult().getForeignAccruedInterest() != null)
        {
            BigDecimal l_bdForeignAccruedInterest = this.getBondEstimatedPriceCalcResult().getForeignAccruedInterest();
            l_params.setForeignAccruedInterest(l_bdForeignAccruedInterest.doubleValue());
        }
        
        //����n����v�Z����.get��n����i�~�݁j
        if (this.getBondEstimatedPriceCalcResult().getEstimatedPrice() != null)
        {
            BigDecimal l_bdEstimatedPrice = this.getBondEstimatedPriceCalcResult().getEstimatedPrice();
            l_params.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());
        }
        
        //����n����v�Z����.get��n����i�O�݁j
        if (this.getBondEstimatedPriceCalcResult().getForeignEstimatedPrice() != null)
        {
            BigDecimal l_bdForeignEstimatedPrice = this.getBondEstimatedPriceCalcResult().getForeignEstimatedPrice();
            l_params.setForeignEstimatedPrice(l_bdForeignEstimatedPrice.doubleValue());
        }
        
        //����n����v�Z����.get�o�ߓ���
        if (this.getBondEstimatedPriceCalcResult().getElapsedDays() != null)
        {
            l_params.setElapsedDays(this.getBondEstimatedPriceCalcResult().getElapsedDays());
        }
        
        //����n����v�Z����.get�����
        if (this.getBondEstimatedPriceCalcResult().getCalcBaseDays() != null)
        {
            l_params.setCalcBaseDays(this.getBondEstimatedPriceCalcResult().getCalcBaseDays());
        }
        
        //���������.get����
        l_params.setExecDate(this.getBondExecuteDateInfo().getExecuteDate());
        
        //���������.get���n����
        l_params.setForeignExecDate(this.getBondExecuteDateInfo().getForeignExecuteDate());
        
        //�C���^�Z�v�^.get�J�X�g�f�B�A���R�[�h(* ��ʓ��́j
        l_params.setCustodianCode(this.getCustodianCode());
        
        //WEB3OrderRootDivDef.�Ǘ���    
        l_params.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);
        
        //0�F�����M
        l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
        
        //�Ǘ���.get�Ǘ��҃R�[�h    
        l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());
        
        //null
        l_params.setErrorReasonCode(null);
        
        //���r���������znull
        l_params.setAdjustmentBeforeMaturity(null);
        
        //���������.get������
        l_params.setPaymentDate(this.getBondExecuteDateInfo().getPaymentDate());
        
        //0�F�����f
        l_params.setExecHostReflectDiv(WEB3HostReflectDivDef.NOT_REFLECT);
        //0�F�����f
        l_params.setCancelHostReflectDiv(WEB3HostReflectDivDef.NOT_REFLECT);
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (get�g�����V�K�������e)<BR>
     * �g�����V�K�������e��ԋp����<BR>
     * @@return webbroker3.bd.WEB3BondNewOrderSpec
     * @@roseuid 44D966D30000
     */
    public WEB3BondNewOrderSpec getBondNewOrderSpec() 
    {
        return this.bondNewOrderSpec;
    }
    
    /**
     * (set�g�����V�K�������e)<BR>
     * �g�����V�K�������e��ݒ肷��<BR>
     * @@param l_bondNewOrderSpec - (�g�����V�K�������e)<BR>
     * �g�����V�K�������e<BR>
     * @@roseuid 44D966D3002E
     */
    public void setBondNewOrderSpec(WEB3BondNewOrderSpec l_bondNewOrderSpec) 
    {
        this.bondNewOrderSpec = l_bondNewOrderSpec;
    }
}
@
