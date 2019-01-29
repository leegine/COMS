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
filename	WEB3MarginOpenMarginUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�V�K���X�V�C���^�Z�v�^(WEB3MarginOpenMarginUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ���� (SRA) �V�K�쐬
Revesion History : 2006/08/01 ���r (���u) �c�a�X�V�d�l163
Revesion History : 2006/11/02 �đo�g (���u) �c�a�X�V�d�lNo.170,174
Revesion History : 2007/04/26 �Ӑ� (���u) �c�a�X�V�d�lNo.199
*/

package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractOpenOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.define.WEB3MarginBaseNumber;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�V�K���X�V�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �V�K�������o�^���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * �iEqTypeOrderManagerPersistenceEventInterceptor�̎����j<BR>
 * <BR>
 * �ȉ��̃T�[�r�X���痘�p�����B<BR>
 * �E�u�M�p����V�K���T�[�r�X�v<BR>
 * �E�u�M�p��������ʒm�T�[�r�X�v<BR>
 * �E�u�����J�z�T�[�r�X�v<BR>
 * �E�u�A�����������T�[�r�X�v
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOpenMarginUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginUpdateInterceptor.class);
    
    /**
     * (�M�p�V�K���������e)<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�B<BR>
     */
    private WEB3MarginOpenContractOrderSpec openContractOrderSpec;
    
    /**
     * (�萔��)<BR>
     * �萔���I�u�W�F�N�g�B<BR>
     * �i�萔��No���̐ݒ�Ɏg�p�j<BR>
     */
    private WEB3GentradeCommission commission;
    
    /**
     * (���񒍕��̒����`���l���B)<BR>
     * ���񒍕��̒����`���l���B<BR>
     */
    private String orderChanel;
    
    /**
     * (�����o�H�敪�B)<BR>
     * �����o�H�敪�B<BR>
     */
    private String orderRootDiv;
    
    /**
     * (���ʃR�[�h�B)<BR>
     * ���ʃR�[�h�B <BR>
     * �M�p�����ʒm�T�[�r�X���痘�p����ꍇ�̂݃Z�b�g���Ďg�p�B�ȊO�Anull���Z�b�g�B<BR>
     */
    private String orderRequestNumber = null;
    
    /**
     * (�󒍓����B)<BR>
     * �󒍓����B <BR>
     * �M�p�����ʒm�T�[�r�X�A�A�����������T�[�r�X���痘�p����ꍇ�̂݃Z�b�g���Ďg�p�B<BR>
     * �ȊO�Anull���Z�b�g�B<BR>
     */
    private Date receivedDateTime = null;
    
    /**
     * (��n���B)<BR>
     * ��n���B <BR>
     * �M�p�����ʒm�T�[�r�X���痘�p����ꍇ�̂݃Z�b�g���Ďg�p�B�ȊO�Anull���Z�b�g�B<BR>
     */
    private Date deliveryDate = null;
    
    /**
     * (�󔄂�K���Ώۃt���O�B)<BR>
     * �󔄂�K���Ώۃt���O�B<BR>
     */
    private boolean shortSellingRestraint;
    
    /**
     * (�J�z���̒����P�ʃI�u�W�F�N�g�B)<BR>
     * �J�z���̒����P�ʃI�u�W�F�N�g�B <BR>
     * �����J�z�T�[�r�X���痘�p����ꍇ�̂݃Z�b�g���Ďg�p�B�ȊO�Anull���Z�b�g�B<BR>
     */
    private EqTypeContractOpenOrderUnit carryoverOrderUnit = null;
    
    /**
     * (�����o�H�敪�B)<BR>
     * �M�p�����ʒm�T�[�r�X���痘�p����ꍇ�̂݃Z�b�g���Ďg�p�B�ȊO�Anull���Z�b�g�B<BR>
     */
    private String submitOrderRouteDiv;
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �K�{�v���p�e�B�ݒ�`�F�b�N<BR>
     * <BR>
     * �@@���I�u�W�F�N�g�̃v���p�e�B�̂����A<BR>
     * �@@�M�p�V�K���������e�A�萔���A���񒍕��̒����`���l���A�����o�H�敪��<BR>
     * �@@�����ꂩ�P�ł�null�̏ꍇ�́A<BR>
     * �@@�p�����[�^.�����P��Params��ԋp���A�������I������B<BR>
     * <BR>
     * �Q�j �g�����ڃZ�b�g<BR>
     * <BR>
     * �@@�e�v���p�e�B����A<BR>
     * �@@�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �@@�X�V���e�́A�ȉ���DB�ݒ�_�����Q�ƁB<BR>
     * �@@�E�u�M�p�V�K��_���������P�ʃe�[�u��.xls�v<BR>
     * �@@�E�u�M�p��������ʒm_���������P�ʃe�[�u��.xls�v��<BR>
     * �@@�@@�u�i�M�p��������ʒm�j�m�V�K���n���������P�ʃe�[�u���v�V�[�g<BR>
     * �@@�E�u�M�p�����J�z_���������P�ʃe�[�u��.xls�v��<BR>
     * �@@�@@�u�i�M�p�����J�z[�J�z(�V�K��)]�j�����P�ʃe�[�u���v�V�[�g<BR>
     * <BR>
     * �@@�u�������v�u��n���v�u�󒍓����v�u���ʃR�[�h�v�u���N�G�X�g�^�C�v�v�u�����o�H�敪�v�̐ݒ�d�l�́A<BR>
     * �@@�ȉ��̒ʂ�ɕ��򂷂�B<BR>
     * �@@-----------------------------------------------------------------------<BR>
     * �@@������       �Fthis.get�����o�H�敪( )��"HOST"�̏ꍇ�̂݁A���ݓ�����YYYYMMDD���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��this.get�����o�H�敪( )��"HOST"�̏ꍇ�́AxTrade�W�������̂܂܂Ƃ���B<BR>
     * �@@��n��       �Fthis.get��n��( )��null�̏ꍇ�̂݁Athis.��n���v���p�e�B���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��this.get��n��( )��null�̏ꍇ�́AxTrade�W�������̂܂܂Ƃ���B<BR>
     * �@@�󒍓����@@�@@�Fthis.�J�z�������P�ʁ�null�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Ethis.get�󒍓���( )��null�̏ꍇ�́AGtlUtils.getSystemTimestamp( )�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Ethis.get�󒍓���( )��null�̏ꍇ�́Athis.�󒍓����v���p�e�B���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.�J�z�������P�ʁ�null�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Ethis.�J�z�������P�ʂ̓����ڂ��Z�b�g�B<BR>
     * �@@���ʃR�[�h�@@�@@�Fthis.get���ʃR�[�h( )��null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h( )�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.get���ʃR�[�h( )��null�̏ꍇ�́Athis.���ʃR�[�h�v���p�e�B���Z�b�g�B<BR>
     * �@@���N�G�X�g�^�C�v�Fthis.�J�z�������P�ʁ�null�̏ꍇ�́A�M�p�V�K���������e.get��������( )�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.�J�z�������P�ʁ�null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.�J�z�������P�ʂ̓��e��蔻�肵�ăZ�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i* ������@@�͏�LDB�ݒ�_���u�M�p�����J�z_���������P�ʃe�[�u��.xls�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�Ƃ̂��Ɓj<BR>
     * �@@�����o�H�敪�Fthis.�����o�H�敪��null�̏ꍇ�́A���������T�[�r�X.get�����o�H�敪( )�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.�����o�H�敪��null�̏ꍇ�́Athis.�����o�H�敪�v���p�e�B���Z�b�g�B<BR>
     * �@@-----------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_process - ����<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 40B18F0B01C4
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "WEB3MarginOpenMarginUpdateInterceptor:mutate()";
        log.entering(STR_METHOD_NAME);

        //�P�j �K�{�v���p�e�B�ݒ�`�F�b�N
        if (this.openContractOrderSpec == null
                || this.commission == null
                || this.orderChanel == null
                || this.orderRootDiv == null)
        {
            return l_orderUnitParams;
        }
        
        //�Q�j �g�����ڃZ�b�g
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        if (carryoverOrderUnit != null)
        {
            log.debug("this.�J�z�������P�ʃv���p�e�B��null�̏ꍇ���J�n");
            // this.�J�z�������P�ʃv���p�e�B��null�̏ꍇ
            //  �u�M�p�����J�z_���������P�ʃe�[�u��.xls�v��<BR>
            //  �u�i�M�p�����J�z[�J�z(�V�K��)]�j�����P�ʃe�[�u���v�V�[�g���Q��
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)carryoverOrderUnit.getDataSourceObject();

            //�l�i����
            l_orderUnitParams.setPriceConditionType(
                openContractOrderSpec.getPriceConditionType());
            
            //��������
            l_orderUnitParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
            
            //�����������Z�q
            l_orderUnitParams.setOrderCondOperator(
                l_orderUnitRow.getOrderCondOperator());
            //�t�w�l��l
            if (l_orderUnitRow.getStopOrderPriceIsNull())
            {
                l_orderUnitParams.setStopOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setStopOrderPrice(
                    l_orderUnitRow.getStopOrderPrice());
            }
            //�iW�w�l�j�����w�l
            if (l_orderUnitRow.getWLimitPriceIsNull())
            {
                l_orderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setWLimitPrice(
                    l_orderUnitRow.getWLimitPrice());
            }

            //�ŋ敪�i�������n�j
            l_orderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);

            //�ٍϋ敪
            l_orderUnitParams.setRepaymentType(
                openContractOrderSpec.getRepaymentType());

            //�ٍϊ����l
            l_orderUnitParams.setRepaymentNum(
                (int)openContractOrderSpec.getRepaymentNum());
            
            //�ٍϋ敪�iSONAR�j
            l_orderUnitParams.setSonarRepaymentType(commission.getPayType());

            //���񒍕��̒����`���l��
            l_orderUnitParams.setOrderChanel(orderChanel);
            
            //�󒍓���
            l_orderUnitParams.setReceivedDateTime(
                l_orderUnitRow.getReceivedDateTime());

            //���ʃR�[�h
            WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService =
               (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);            
            MainAccount l_mainAccount = null;
            String l_orderRequestNumber= null;
            try 
            {
                l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_orderUnitRow.getAccountId());
                l_orderRequestNumber = l_WEB3HostReqOrderNumberManageService.getNewNumber(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    ProductTypeEnum.EQUITY);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(),
                    l_nfe);
            }
            l_orderUnitParams.setOrderRequestNumber(l_orderRequestNumber);

            //�`�[No
            int l_intLenth = l_orderRequestNumber.length();
            l_orderUnitParams.setVoucherNo(WEB3MarginBaseNumber.BaseNumber + 
                l_orderRequestNumber.substring(l_intLenth - 3,l_intLenth));
            
            //���񒍕��̎萔��No
            l_orderUnitParams.setCommTblNo(commission.getCommissionNo());
            
            //���񒍕��̎萔��No�}��
            l_orderUnitParams.setCommTblSubNo(commission.getCommissionRevNo());

            //���҃R�[�h�iSONAR�j
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
                  
            //�����P��
            l_orderUnitParams.setPrice(openContractOrderSpec.getCalcUnitPrice());
            
            //�T�Z��n���
            l_orderUnitParams.setEstimatedPrice(
                openContractOrderSpec.getContractAmount());
            
            //���n�v���z
            l_orderUnitParams.setCapitalGain(0D);
            
            //���n�v�Ŋz
            l_orderUnitParams.setCapitalGainTax(0D);

            //����R�[�h�iSONAR�j
            l_orderUnitParams.setSonarTradedCode(commission.getSonarTradedCode());

            //�s��R�[�h�iSONAR�j
            try
            {
                WEB3GentradeFinObjectManager l_objectManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                Market l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            //�萔�����i�R�[�h
            l_orderUnitParams.setCommProductCode(commission.getCommissionProductCode());
            
            //�󔄂�K���Ώۃt���O
            if (this.shortSellingRestraint)
            {
                l_orderUnitParams.setShortSellOrderFlag(
                    WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);
            }
            else
            {
                l_orderUnitParams.setShortSellOrderFlag(
                    WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);                
            }

            //���������E����敪
            l_orderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.INITIAL_VALUE);

            //�����o�H�敪
            l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);

            //�����o�H�敪
            if (this.submitOrderRouteDiv == null)
            {
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                String l_strSubmitOrderRouteDiv = null;
                try
                {
                    Branch l_branch = l_accountManager.getBranch(l_orderUnitParams.getBranchId());
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_productManager.getTradedProduct(
                            l_orderUnitParams.getProductId(),
                            l_orderUnitParams.getMarketId());
                    l_strSubmitOrderRouteDiv =
                        l_frontOrderService.getSubmitOrderRouteDiv(
                            l_tradedProduct,
                            l_branch.getInstitution().getInstitutionCode(),
                            l_orderUnitParams.getSonarTradedCode());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(), l_nfe);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
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
            }
            else
            {
                l_orderUnitParams.setSubmitOrderRouteDiv(this.submitOrderRouteDiv);
            }
            
            //�s�ꂩ��m�F�ς݂̒����P��
            l_orderUnitParams.setConfirmedOrderPrice(null);                
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
            
            //�s�ꂩ��m�F�ς݂̎��s����
            l_orderUnitParams.setConfirmedExecConditionType(null);
            
            //�s�ꂩ��m�F�ς݂̒l�i����
            l_orderUnitParams.setConfirmedPriceConditionType(null);
            
            //���Ϗ����敪
            l_orderUnitParams.setClosingOrderType(null);
            
            //�����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //���N�G�X�g�^�C�v
            String l_strOrderConditionType = l_orderUnitParams.getOrderConditionType();
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
            {
				l_orderUnitParams.setRequestType(l_orderUnitRow.getRequestType());
            }
            else
            {
				l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
            }

            //����Rev.
            l_orderUnitParams.setOrderRev("00");
                
            //�s�ꂩ��m�F�ς݂̒���Rev.
            l_orderUnitParams.setConfirmedOrderRev("00");
            
            //���񒍕��̒����P�ʂh�c
            l_orderUnitParams.setFirstOrderUnitId(
                openContractOrderSpec.getFirstOrderUnitId());
                
            // ����������
            l_orderUnitParams.setOriginalQuantity(l_orderUnitParams.getQuantity());
            
            //����������
            l_orderUnitParams.setOrgOrderConditionType(
                l_orderUnitRow.getOrgOrderConditionType());
            
            //�������������Z�q
            l_orderUnitParams.setOrgOrderCondOperator(
                l_orderUnitRow.getOrgOrderCondOperator());
            
            //���t�w�l��l
            if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_orderUnitParams.setOrgStopOrderPrice(null);
            }
            else 
            {
                l_orderUnitParams.setOrgStopOrderPrice(
                    l_orderUnitRow.getOrgStopOrderPrice());
            }
            //���iW�w�l�j�����w�l
            if (l_orderUnitRow.getOrgWLimitPriceIsNull())
            {
                l_orderUnitParams.setOrgWLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setOrgWLimitPrice(
                    l_orderUnitRow.getOrgWLimitPrice());
            }
            //���iW�w�l�j���s����
            l_orderUnitParams.setOrgWLimitExecCondType(
                l_orderUnitRow.getOrgWLimitExecCondType());
            //�iW�w�l�j���s����
            l_orderUnitParams.setWLimitExecCondType(
                l_orderUnitRow.getWLimitExecCondType());
            //�iW�w�l�j�֑ؑO�w�l
            if (l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_orderUnitParams.setWLimitBeforeLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setWLimitBeforeLimitPrice(
                    l_orderUnitRow.getWLimitBeforeLimitPrice());
            }
            //�iW�w�l�j�֑ؑO���s����
            l_orderUnitParams.setWLimitBeforeExecCondType(
                    l_orderUnitRow.getWLimitBeforeExecCondType());

            //�����x���t���O
            l_orderUnitParams.setSubmitOrderDelayFlag(
                l_orderUnitRow.getSubmitOrderDelayFlag());

            log.debug("this.�J�z�������P�ʃv���p�e�B��null�̏ꍇ���I��");
        }
        else
        {
            log.debug("this.�J�z�������P�ʃv���p�e�B��null�̏ꍇ���J�n");
            // this.�J�z�������P�ʃv���p�e�B��null�̏ꍇ
            //  �u�M�p�V�K��_���������P�ʃe�[�u��.xls�v<BR>
            //  �u�M�p��������ʒm_���������P�ʃe�[�u��.xls�v��<BR>
            //  �u�i�M�p��������ʒm�j�m�V�K���n���������P�ʃe�[�u���v�V�[�g<BR>            
            
            //�l�i����
            l_orderUnitParams.setPriceConditionType(openContractOrderSpec.getPriceConditionType());

            //��������
            l_orderUnitParams.setOrderConditionType(openContractOrderSpec.getOrderConditionType());

            //�����������Z�q
            String l_strOrderConditionType = l_orderUnitParams.getOrderConditionType();
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setOrderCondOperator(null);
                l_orderUnitParams.setStopOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setOrderCondOperator(openContractOrderSpec.getOrderCondOperator());
                l_orderUnitParams.setStopOrderPrice(openContractOrderSpec.getStopOrderPrice());    
            }
            
            //�iW�w�l�j�����w�l
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType)
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setWLimitPrice(openContractOrderSpec.getWLimitPrice());
            }

            //��n��
            if (this.getDeliveryDate() != null)
            {
                l_orderUnitParams.setDeliveryDate(this.getDeliveryDate());
            }
            
            //�ŋ敪�i�������n�j
            l_orderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);

            //�ٍϋ敪
            l_orderUnitParams.setRepaymentType(openContractOrderSpec.getRepaymentType());

            //�ٍϊ����l
            l_orderUnitParams.setRepaymentNum((int)openContractOrderSpec.getRepaymentNum());
            
            //�ٍϋ敪�iSONAR�j
            l_orderUnitParams.setSonarRepaymentType(commission.getPayType());

            //������    
            if (this.orderRootDiv.equals(WEB3OrderRootDivDef.HOST))
            {
                l_orderUnitParams.setBizDate(
                    GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat()
                        .format(GtlUtils.getSystemTimestamp()));
            }

            //���񒍕��̒����`���l��
            l_orderUnitParams.setOrderChanel(this.orderChanel);
            
            //�󒍓���
            if (this.getReceivedDateTime() == null)
            {
                Timestamp l_timeStamp = GtlUtils.getTradingSystem().getSystemTimestamp();
                l_orderUnitParams.setReceivedDateTime(l_timeStamp);
            }
            else
            {
                l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);
            }
            
            //���ʃR�[�h
            WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);            
            MainAccount l_mainAccount = null;
            String l_orderRequestNumber= null;
            try 
            {
                l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_orderUnitParams.getAccountId());
                l_orderRequestNumber = l_WEB3HostReqOrderNumberManageService.getNewNumber(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    ProductTypeEnum.EQUITY);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), l_ex);
            }
            catch(NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }           
            if (this.getOrderRequestNumber() == null)
            {             
                l_orderUnitParams.setOrderRequestNumber(l_orderRequestNumber);
            }
            else
            {
                l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);
            }

            //�`�[No
            l_orderRequestNumber = l_orderUnitParams.getOrderRequestNumber();
            int l_intLenth = l_orderRequestNumber.length();
            l_orderUnitParams.setVoucherNo(WEB3MarginBaseNumber.BaseNumber + 
                l_orderRequestNumber.substring(l_intLenth - 3,l_intLenth));
            
            //���񒍕��̎萔��No
            l_orderUnitParams.setCommTblNo(commission.getCommissionNo());
            
            //���񒍕��̎萔��No�}��
            l_orderUnitParams.setCommTblSubNo(commission.getCommissionRevNo());

            //���҃R�[�h�iSONAR�j
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
            
            //�����P��
            l_orderUnitParams.setPrice(openContractOrderSpec.getCalcUnitPrice());
            
            //�T�Z��n���
            l_orderUnitParams.setEstimatedPrice(openContractOrderSpec.getContractAmount());
            
            //���n�v���z
            double l_dblCapitalGain = 0D;
            l_orderUnitParams.setCapitalGain(l_dblCapitalGain);
            
            //���n�v�Ŋz
            double l_dblCapitalGainTax = 0D;
            l_orderUnitParams.setCapitalGainTax(l_dblCapitalGainTax);

            //����R�[�h�iSONAR�j
            l_orderUnitParams.setSonarTradedCode(commission.getSonarTradedCode());

            //�s��R�[�h�iSONAR�j
            try
            {
                WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                Market l_market = l_objectManager.getMarket(l_orderUnitParams.getMarketId());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }
            
            //�萔�����i�R�[�h
            l_orderUnitParams.setCommProductCode(commission.getCommissionProductCode());

            //�󔄋K���Ώۃt���O
            if (this.shortSellingRestraint)
            {
                l_orderUnitParams.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);
            }
            else
            {
                l_orderUnitParams.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);                
            }
            
            //���������E����敪
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

            //�����o�H�敪
            l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);

            //�����o�H�敪
            if (this.submitOrderRouteDiv == null)
            {
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                String l_strSubmitOrderRouteDiv = null;
                try
                {
                    Branch l_branch = l_accountManager.getBranch(l_orderUnitParams.getBranchId());
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_productManager.getTradedProduct(
                            l_orderUnitParams.getProductId(),
                            l_orderUnitParams.getMarketId());
                    l_strSubmitOrderRouteDiv =
                        l_frontOrderService.getSubmitOrderRouteDiv(
                            l_tradedProduct,
                            l_branch.getInstitution().getInstitutionCode(),
                            l_orderUnitParams.getSonarTradedCode());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(), l_nfe);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
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
            }
            else
            {
                l_orderUnitParams.setSubmitOrderRouteDiv(this.submitOrderRouteDiv);
            }
            
            //�s�ꂩ��m�F�ς݂̒����P��
            l_orderUnitParams.setConfirmedOrderPrice(null);
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
            
            //�s�ꂩ��m�F�ς݂̎��s����
            l_orderUnitParams.setConfirmedExecConditionType(null);
            
            //�s�ꂩ��m�F�ς݂̒l�i����
            l_orderUnitParams.setConfirmedPriceConditionType(null);
            
            //���Ϗ����敪
            l_orderUnitParams.setClosingOrderType(null);
            
            //�����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //���N�G�X�g�^�C�v
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(openContractOrderSpec.getOrderConditionType())
                    || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(openContractOrderSpec.getOrderConditionType()))
            {
                l_orderUnitParams.setRequestType(WEB3OrderingConditionDef.DEFAULT); 
            }
            else
            {
                l_orderUnitParams.setRequestType(null); 
            }

            //����Rev.
            l_orderUnitParams.setOrderRev("00");
                
            //�s�ꂩ��m�F�ς݂̒���Rev.
            l_orderUnitParams.setConfirmedOrderRev("00");
            
            //���񒍕��̒����P�ʂh�c
            l_orderUnitParams.setFirstOrderUnitId(openContractOrderSpec.getFirstOrderUnitId() );
            
            // ����������
            l_orderUnitParams.setOriginalQuantity(l_orderUnitParams.getQuantity());

            //�iW�w�l�j���s����
            //�M�p�V�K���������e.get�iW�w�l�j���s����
            //�������A�����������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull�B
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitParams.getOrderConditionType())
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitParams.getOrderConditionType()))
            {
                l_orderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                l_orderUnitParams.setWLimitExecCondType(this.openContractOrderSpec.getWlimitExecCondType());
            }

            //�����x���t���O
            //0�F�x���Ȃ��i0�FDEFAULT�j
            l_orderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.FALSE);
            
            //�������ϗ��R�敪
            l_orderUnitParams.setForcedSettleReasonType(null);
            
            //���F��ԋ敪
            l_orderUnitParams.setApproveStatusType(null);
            
            //���F�҃R�[�h
            l_orderUnitParams.setApproverCode(null);
            
            //���F�^�񏳔F����
            l_orderUnitParams.setApproveTimestamp(null);
            
            //�ۏ؋��ێ���
            l_orderUnitParams.setMarginMaintenanceRate(null);
            
            //�Ǐؔ�����
            l_orderUnitParams.setAdditionalMarginDate(null);
            
            //�Ǐ،o�ߓ���
            l_orderUnitParams.setAdditionalMarginAccruedDays(null);

            //���������敪
            //0�F�I�[�v���i0�FDEFAULT�j
            l_orderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);

            log.debug("this.�J�z�������P�ʃv���p�e�B��null�̏ꍇ���I��");
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (�M�p�V�K���X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * �����Ɏw�肳�ꂽ�I�u�W�F�N�g���A�����̃v���p�e�B�ɐݒ肷��B<BR>
     * <BR>
     * �v���p�e�B�u���ʃR�[�h�v�u�󒍓����v�u��n���v�u�J�z�������P�ʁv�ɂ́Anull���Z�b�g����B<BR>
     * @@param l_openContractOrderSpec - �M�p�V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_commission - �萔���I�u�W�F�N�g�B<BR>
     * @@param l_strOrderChanel - ���񒍕��̒����`���l���B<BR>
     * @@param l_strOrderRootDiv - �����o�H�敪�B<BR>
     * @@param l_strShortSellOrderFlag - �󔄂�K���Ώۃt���O�B<BR>
     * @@return EB3MarginOpenMarginUpdateInterceptor
     * @@roseuid 40B18F0B01E3
     */
    public WEB3MarginOpenMarginUpdateInterceptor(
        WEB3MarginOpenContractOrderSpec l_openContractOrderSpec, 
        WEB3GentradeCommission l_commission, 
        String l_strOrderChanel, 
        String l_strOrderRootDiv,
        boolean l_shortSellOrderFlag) 
    {
        final String STR_METHOD_NAME = "WEB3MarginOpenMarginUpdateInterceptor:WEB3MarginOpenMarginUpdateInterceptor()";
        log.entering(STR_METHOD_NAME);
        
        //�����Ɏw�肳�ꂽ�I�u�W�F�N�g���A�����̃v���p�e�B�ɐݒ肷��B
        this.openContractOrderSpec = l_openContractOrderSpec;
        this.commission = l_commission;
        this.orderChanel = l_strOrderChanel;
        this.orderRootDiv = l_strOrderRootDiv;
        this.shortSellingRestraint = l_shortSellOrderFlag;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get���ʃR�[�h)<BR>
     * ���ʃR�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 40F37CBF0388
     */
    public String getOrderRequestNumber() 
    {
        return this.orderRequestNumber;
    }
    
    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B<BR>
     * @@param l_strOrderRequestNumber - ���ʃR�[�h<BR>
     * @@roseuid 40F37CD402AD
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        this.orderRequestNumber = l_strOrderRequestNumber;
    }
    
    /**
     * (get�󒍓���)<BR>
     * �󒍓������擾����B<BR>
     * @@return Date
     * @@roseuid 40F50B9300EC
     */
    public Date getReceivedDateTime() 
    {
        return this.receivedDateTime;
    }
    
    /**
     * (set�󒍓���)<BR>
     * �󒍓������Z�b�g����B<BR>
     * @@param �󒍓��� - �󒍓���
     * @@roseuid 40F50BAB03CA
     */
    public void setReceivedDateTime(Date l_datReceivedDateTime) 
    {
        this.receivedDateTime = l_datReceivedDateTime;
    }
    
    /**
     * (get��n��)<BR>
     * ��n�����擾����B<BR>
     * @@return Date
     * @@roseuid XXXXXXXXXXX
     */
    public Date getDeliveryDate() 
    {
        return this.deliveryDate;
    }
    
    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B<BR>
     * @@param ��n�� - ��n��
     * @@roseuid XXXXXXXXXXX
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        this.deliveryDate = l_datDeliveryDate;
    }
    
    /**
     * (get�J�z�������P��)<BR>
     * �v���p�e�B�u�J�z�������P�ʁv���擾����B<BR>
     * @@return EqTypeContractOpenOrderUnit
     * @@roseuid 4112F34D0381
     */
    public EqTypeContractOpenOrderUnit getCarryoverOrderUnit() 
    {
        return this.carryoverOrderUnit;
    }
    
    /**
     * (set�J�z�������P��)<BR>
     * �������A�v���p�e�B�u�J�z�������P�ʁv�ɃZ�b�g����B<BR>
     * @@param l_carryoverOrderUnit - �J�z�������P�ʁB<BR>
     * @@roseuid 41088F2E026A
     */
    public void setCarryoverOrderUnit(EqTypeContractOpenOrderUnit l_carryoverOrderUnit) 
    {
        this.carryoverOrderUnit = l_carryoverOrderUnit;
    }
    
    /**
     * (get�����o�H�敪)<BR>
     * �����o�H�敪���擾����B
     * @@return �����o�H�敪
     */
    public String getSubmitOrderRouteDiv()
    {
        return this.submitOrderRouteDiv;
    }
    
    /**
     * (set�����o�H�敪)<BR>
     * �����o�H�敪���Z�b�g����B
     * @@param l_submitOrderRouteDiv - (�����o�H�敪)<BR>
     * �����o�H�敪
     */
    public void setSubmitOrderRouteDiv(String l_submitOrderRouteDiv)
    {
        this.submitOrderRouteDiv = l_submitOrderRouteDiv;
    }
}
@
