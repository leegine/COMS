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
filename	WEB3EquityOrderNotifyInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�C���^�Z�v�^(WEB3EquityOrderNotifyIntercepter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 �R�w��(���u) �쐬
Revesion History : 2006/04/26 �Ӑ� (���u) �c�a�X�V�d�lNo.198
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.define.WEB3EquityVoucherNoDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

/**
 * �i���������ʒm�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyInterceptor extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderNotifyInterceptor.class);


    /**
     * (�����������͒ʒm�L���[Params) <BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�̂P���R�[�h�B <BR>
     */
    private HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams;

    /**
     * @@roseuid 40B5A0FA0148
     */
    public WEB3EquityOrderNotifyInterceptor()
    {

    }

    /**
     * (���������ʒm�C���^�Z�v�^) <BR>
     * �R���X�g���N�^�B <BR>
     * �������A�N���X�̃v���p�e�B�ɃZ�b�g����B <BR>
     * @@param l_hostEqtypeOrderReceiptParams - (�����������͒ʒm�L���[Params) <BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�̂P���R�[�h�B <BR>
     * @@return WEB3EquityOrderNotifyIntercepter
     * @@roseuid 403EF16602DD
     */
    public WEB3EquityOrderNotifyInterceptor(HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceiptParams)
    {
        this.hostEqtypeOrderReceiptParams = l_hostEqtypeOrderReceiptParams;
    }

    /**
     * (�X�V�l�ݒ�) <BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �P�j �v���p�e�B�ݒ蔻�� <BR>
     * �@@this.�����������͒ʒm�L���[Params�v���p�e�B��null�̏ꍇ�� <BR>
     * �p�����[�^.�����P��Params��ԋp�� <BR>
     * �������I������B <BR>
     * <BR>
     * �Q�j �g�����ڃZ�b�g <BR>
     * �@@this.�����������͒ʒm�L���[Params�v���p�e�B����A <BR>
     * �p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * �X�V���e�́A <BR>
     * �u�������������ʒm_���������P�ʃe�[�u��.xls�v�Q�ƁB <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B <BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j <BR>
     * <BR>
     * @@param l_eqtypeOrderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 403EF16602D9
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (this.hostEqtypeOrderReceiptParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_eqtypeOrderUnitParams;
        }

        WEB3EquityNewCashBasedOrderSpec l_cashBaseOrderSpec = getEquityOrderSpec();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        AccountManager l_accMgr = l_finApp.getAccountManager();
       
        try
        {            
            // �l�i����            
            l_eqtypeOrderUnitParams.setPriceConditionType(
                l_orderManager.getPriceConditionType(
                    this.hostEqtypeOrderReceiptParams.getPriceConditionType()));
            
            //��������
            l_eqtypeOrderUnitParams.setOrderConditionType(l_cashBaseOrderSpec.getOrderCond());
            
            if (l_eqtypeOrderUnitParams.getOrderConditionType().equals(WEB3OrderingConditionDef.DEFAULT))
            {
                //�����������Z�q
                l_eqtypeOrderUnitParams.setOrderCondOperator(null);
                //�t�w�l��l
                l_eqtypeOrderUnitParams.setStopOrderPrice(null);
                //�iW�w�l�j�����w�l
                l_eqtypeOrderUnitParams.setWLimitPrice(null);
            }
            else
            {
                // �����������Z�q
                l_eqtypeOrderUnitParams.setOrderCondOperator(
                    l_cashBaseOrderSpec.getOrderCondOperator());   
                // �t�w�l��l  
                l_eqtypeOrderUnitParams.setStopOrderPrice(
                    l_cashBaseOrderSpec.getStopLimitPriceBasePrice());       
                //�iW�w�l�j�����w�l
                if (l_eqtypeOrderUnitParams.getOrderConditionType().equals(WEB3OrderingConditionDef.STOP_LIMIT_PRICE))
                {
                    l_eqtypeOrderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setWLimitPrice(
                        l_cashBaseOrderSpec.getWLimitPriceChange());     
                }
            }
            
            //��n��
            if (this.hostEqtypeOrderReceiptParams.getDeliveryDate() != null)
            {
				l_eqtypeOrderUnitParams.setDeliveryDate(
                    WEB3DateUtility.getDate(
                        this.hostEqtypeOrderReceiptParams.getDeliveryDate(), "yyyyMMdd"));
            }

            //�ŋ敪�i�������n�j
            l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
            
            //�ٍϋ敪
            l_eqtypeOrderUnitParams.setRepaymentType(null);
            
            //�ٍϊ����l
            l_eqtypeOrderUnitParams.setRepaymentNum(null);
            
            //�ٍϋ敪�iSONAR)
            l_eqtypeOrderUnitParams.setSonarRepaymentType(null);

            //������
            l_eqtypeOrderUnitParams.setBizDate(
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat()
                    .format(GtlUtils.getSystemTimestamp()));
        
            //���񒍕��̒����`���l��
            l_eqtypeOrderUnitParams.setOrderChanel(
                this.hostEqtypeOrderReceiptParams.getChannel());
        
            //�󒍓���
            l_eqtypeOrderUnitParams.setReceivedDateTime(
                this.hostEqtypeOrderReceiptParams.getCreateDatetime());
        
            //�`�[No
            String l_strOrderRequestNumber =
                this.hostEqtypeOrderReceiptParams.getOrderRequestNumber();
            String l_strOrderRequestNumber_3 = "";
            String l_strVoucherNo = "";
            if(l_strOrderRequestNumber.length() >= 3)
            {
                l_strOrderRequestNumber_3 = 
                l_strOrderRequestNumber.substring(l_strOrderRequestNumber.length() - 3,
                l_strOrderRequestNumber.length());   
                l_strVoucherNo = WEB3EquityVoucherNoDef.VoucherNo + l_strOrderRequestNumber_3;
            }
            l_eqtypeOrderUnitParams.setVoucherNo(l_strVoucherNo);

            //���񒍕��̎萔��No
            l_eqtypeOrderUnitParams.setCommTblNo(
                l_cashBaseOrderSpec.getCommission().getCommissionNo());
        
            //���񒍕��̎萔��No�}��
            l_eqtypeOrderUnitParams.setCommTblSubNo(
                l_cashBaseOrderSpec.getCommission().getCommissionRevNo());

            //���҃R�[�h�iSONAR�j
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount =
                    l_accMgr.getMainAccount(l_eqtypeOrderUnitParams.account_id);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }
            MainAccountRow l_accRow =
                (MainAccountRow) l_mainAccount.getDataSourceObject();
            l_eqtypeOrderUnitParams.setSonarTraderCode(
                l_accRow.getSonarTraderCode());

            //�����P��
            l_eqtypeOrderUnitParams.setPrice(l_cashBaseOrderSpec.getOrderUnitPrice()); 
        
            //���ʃR�[�h
            l_eqtypeOrderUnitParams.setOrderRequestNumber(
                this.hostEqtypeOrderReceiptParams.getOrderRequestNumber());
        
            //�T�Z��n���
            l_eqtypeOrderUnitParams.setEstimatedPrice(
                l_cashBaseOrderSpec.getEstimateDeliveryAmount());

            //���n�v���z
            l_eqtypeOrderUnitParams.setCapitalGain(0.0);
            
            //���n�v�Ŋz
            l_eqtypeOrderUnitParams.setCapitalGainTax(0.0);
        
            //����R�[�h�iSONAR�j
            l_eqtypeOrderUnitParams.setSonarTradedCode(
                this.hostEqtypeOrderReceiptParams.getSonarTradedCode());
        
            //�s��R�[�h�iSONAR�j
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqtypeOrderUnitParams);
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            Market l_market = null;
            try
            {
                l_market = l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
            String l_strMaketCode = l_marketRow.getSonarMarketCode();
            l_eqtypeOrderUnitParams.setSonarMarketCode(l_strMaketCode);
        
            //�萔�����i�R�[�h
            l_eqtypeOrderUnitParams.setCommProductCode(
                l_cashBaseOrderSpec.getCommissionProductCode());
            
            //�󔄃t���O
            l_eqtypeOrderUnitParams.setShortSellOrderFlag(
                WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
        
            //���������E����敪
            l_eqtypeOrderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
            //�����o�H�敪
            l_eqtypeOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            
            //�����o�H�敪
            l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv(
                hostEqtypeOrderReceiptParams.getSubmitOrderRouteDiv());
        
            //���Ϗ���
            l_eqtypeOrderUnitParams.setClosingOrderType(null);
        
            //�����G���[���R�R�[�h
            l_eqtypeOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
            //���N�G�X�g�^�C�v
            l_eqtypeOrderUnitParams.setRequestType(null);
        
            //����Rev.
            l_eqtypeOrderUnitParams.setOrderRev("00");
            
            //�s�ꂩ��m�F�ς݂̒���Rev.
            l_eqtypeOrderUnitParams.setConfirmedOrderRev("00");
            
            //���񒍕��̒����P�ʂh�c
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(null);
            
            // �������ϗ��R�敪
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(null);
            
            //���F��ԋ敪
            l_eqtypeOrderUnitParams.setApproveStatusType(null);
            
            //���F�҃R�[�h
            l_eqtypeOrderUnitParams.setApproverCode(null);
            
            //���F�^�񏳔F����
            l_eqtypeOrderUnitParams.setApproveTimestamp(null);
            
            //�ۏ؋��ێ���
            l_eqtypeOrderUnitParams.setMarginMaintenanceRate(null);
            
            //�Ǐؔ�����
            l_eqtypeOrderUnitParams.setAdditionalMarginDate(null);
            
            //�Ǐ،o�ߓ���
            l_eqtypeOrderUnitParams.setAdditionalMarginAccruedDays(null);

            //���������敪
            //0�F�I�[�v���i0�FDEFAULT�j
            l_eqtypeOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);
        }
        catch (WEB3BaseException l_wbe)
        {
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }
}
@
