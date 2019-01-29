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
filename	WEB3MiniStockOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j���������X�V�C���^�Z�v�^(WEB3MiniStockOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 �J�N���V (���u) �V�K�쐬
                   2004/12/10 ���� (SRA) �c�Č��Ή�
                   2004/12/29 ���� (SRA) JavaDoc�C��
                   2006/07/19 ���r (���u) �c�a�X�V�d�l158
                   2006/11/14 ������ (���u)�@@���f��No.1026
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.equity.define.WEB3MarginBaseNumber;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j���������X�V�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �����~�j���������X�V�C���^�Z�v�^�N���X
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3MiniStockOrderUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    /**
     * �i���O���[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MiniStockOrderUpdateInterceptor.class);
    
    /**
     * �i�~�j���������e�j�B<BR>
     * <BR>
     * �~�j���������e�I�u�W�F�N�g
     */
    private WEB3NewMiniStockOrderSpec mstkOrderSpec;
    
    /**
     * �i�萔���j�B<BR>
     * <BR>
     * �萔���I�u�W�F�N�g
     */
    private WEB3GentradeCommission commission;
    
    /**
     * �i�T�Z��n����v�Z���ʁj�B<BR>
     * <BR>
     * �T�Z��n����v�Z���ʃI�u�W�F�N�g
     */
    private WEB3EquityEstimatedDeliveryPrice estimatedDeliveryPrice;
    
    /**
     * �i�����~�j���������X�V�C���^�Z�v�^�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
    public WEB3MiniStockOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * �i�����~�j���������X�V�C���^�Z�v�^�j�B<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * �����Ɏw�肳�ꂽ�I�u�W�F�N�g�^�l���A�����̃v���p�e�B�ɐݒ肷��B
     * @@param l_mstkOrderSpec �i�~�j���������e�j<BR>
     * �~�j���������e�I�u�W�F�N�g
     * @@param l_commission �i�萔���j<BR>
     * �萔���I�u�W�F�N�g
     * @@param l_estimatedDeliveryPrice �i�T�Z��n����v�Z���ʁj<BR>
     * �T�Z��n����v�Z����
     */
    public WEB3MiniStockOrderUpdateInterceptor(
        WEB3NewMiniStockOrderSpec l_mstkOrderSpec, 
        WEB3GentradeCommission l_commission, 
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice) 
    {
        
        //�����Ɏw�肳�ꂽ�I�u�W�F�N�g�^�l���A�����̃v���p�e�B�ɐݒ肷��
        this.mstkOrderSpec = l_mstkOrderSpec;
        
        //�~�j���������e�I�u�W�F�N�g
        this.commission = l_commission;
        
        //�萔���I�u�W�F�N�g
        this.estimatedDeliveryPrice = l_estimatedDeliveryPrice;
     
    }
    
    /**
     * �i�X�V�l�ݒ�j�B<BR>
     * <BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �@@�v���p�e�B����A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �X�V���e��DB�ݒ�_���u�~�j������_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_mutate - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_context �i�����j<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_eqtypeOrderUnitParams �i�����P��Params�j<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_mutate, 
        OrderManagerPersistenceContext l_context, 
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams) 
    {
        
        final String STR_METHOD_NAME = 
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, qtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //�l�i����
        l_eqtypeOrderUnitParams.setPriceConditionType(WEB3PriceConditionDef.DEFAULT);
        //��������
        l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
        //�����������Z�q
        l_eqtypeOrderUnitParams.setOrderCondOperator(null);
        //�t�w�l��l
        l_eqtypeOrderUnitParams.setStopOrderPrice(null);
        //�iW�w�l�j�����w�l
        l_eqtypeOrderUnitParams.setWLimitPrice(null);
        //�ŋ敪�i�������n�j
        l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
        //�ٍϋ敪
        l_eqtypeOrderUnitParams.setRepaymentType(null);
        //�ٍϊ����l
        l_eqtypeOrderUnitParams.setRepaymentNum(null);
        //�ٍϋ敪�iSONAR�j
        l_eqtypeOrderUnitParams.setSonarRepaymentType(null);
        //���񒍕��̒����`���l��
        l_eqtypeOrderUnitParams.setOrderChanel(commission.getOrderChannel());
        //�󒍓���
        l_eqtypeOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        //���ʃR�[�h
        WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService=
        (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);            
        MainAccount l_mainAccount ;
        String l_orderRequestNumber;
        try 
        {
            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_eqtypeOrderUnitParams.getAccountId());
            l_orderRequestNumber = l_WEB3HostReqOrderNumberManageService.getNewNumber(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                ProductTypeEnum.EQUITY);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(),this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        catch(NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }           
        l_eqtypeOrderUnitParams.setVoucherNo(WEB3MarginBaseNumber.BaseNumber + l_orderRequestNumber.substring(l_orderRequestNumber.length() - 3, l_orderRequestNumber.length()));
        log.debug("�`�[No:" + l_eqtypeOrderUnitParams.getVoucherNo());
        //���񒍕��̎萔��No  
        l_eqtypeOrderUnitParams.setCommTblNo(commission.getCommissionNo());
        //���񒍕��̎萔��No�}��
        l_eqtypeOrderUnitParams.setCommTblSubNo(commission.getCommissionRevNo());
        //���҃R�[�h�iSONAR�j
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        l_eqtypeOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
        //�����P��
        l_eqtypeOrderUnitParams.setPrice(estimatedDeliveryPrice.getCalcUnitPrice());
        //���ʃR�[�h
        l_eqtypeOrderUnitParams.setOrderRequestNumber(l_orderRequestNumber);
        //�T�Z��n���
        l_eqtypeOrderUnitParams.setEstimatedPrice(estimatedDeliveryPrice.getEstimateDeliveryAmount());
        //���n�v���z
        l_eqtypeOrderUnitParams.setCapitalGain(null);
        //���n�v�Ŋz
        l_eqtypeOrderUnitParams.setCapitalGainTax(null);
        //����R�[�h�iSONAR�j
        String l_strSonarTradedCode = commission.getSonarTradedCode();
        l_eqtypeOrderUnitParams.setSonarTradedCode(l_strSonarTradedCode);
        //�s��R�[�h�iSONAR�j
        try
        {
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_eqtypeOrderUnitParams.getMarketId());
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
            l_eqtypeOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
        }
        catch (NotFoundException l_notFoundExp)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        //�萔�����i�R�[�h
        l_eqtypeOrderUnitParams.setCommProductCode(commission.getCommissionProductCode());
        //���������E����敪
        l_eqtypeOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        //�����o�H�敪
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        l_eqtypeOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
        //�s�ꂩ��m�F�ς݂̒����P��
        l_eqtypeOrderUnitParams.setConfirmedOrderPrice(null);
        //�s�ꂩ��m�F�ς݂̊T�Z��n���
        l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(null);
        //�s�ꂩ��m�F�ς݂̎��s����
        l_eqtypeOrderUnitParams.setConfirmedExecConditionType(null);
        //�s�ꂩ��m�F�ς݂̒l�i����
        l_eqtypeOrderUnitParams.setConfirmedPriceConditionType(null);
        //���Ϗ����敪
        l_eqtypeOrderUnitParams.setClosingOrderType(null);
        //�����G���[���R�R�[�h
        l_eqtypeOrderUnitParams.setErrorReasonCode("0000");
        //���N�G�X�g�^�C�v
        l_eqtypeOrderUnitParams.setRequestType(null);
        //���񒍕��̒����P�ʂh�c
        l_eqtypeOrderUnitParams.setFirstOrderUnitId(null);
        
        //����������
        l_eqtypeOrderUnitParams.setOrgOrderConditionType(null);
        //�������������Z�q
        l_eqtypeOrderUnitParams.setOrgOrderCondOperator(null);
        //���t�w�l��l
        l_eqtypeOrderUnitParams.setOrgStopOrderPrice(null);
        //���iW�w�l�j�����w�l
        l_eqtypeOrderUnitParams.setOrgWLimitPrice(null);
        //���iW�w�l�j���s����
        l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(null);
        //�iW�w�l�j���s����
        l_eqtypeOrderUnitParams.setWLimitExecCondType(null);
        //�iW�w�l�j�֑ؑO�w�l
        l_eqtypeOrderUnitParams.setWLimitBeforeLimitPrice(null);
        //�iW�w�l�j�֑ؑO���s����
        l_eqtypeOrderUnitParams.setWLimitBeforeExecCondType(null);
        
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
        
    }
}
@
