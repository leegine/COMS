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
filename	WEB3FeqOrderCarryOverUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������J�z�X�V�C�x���g�C���^�Z�v�^(WEB3FeqOrderCarryOverUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  鰊](���u) �V�K�쐬
                   2005/07/27 䈋��@@(���u) ���r���[
*/

package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccRegAccountDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������J�z�X�V�C�x���g�C���^�Z�v�^)<BR>
 * �O�����������J�z�X�V�C�x���g�C���^�Z�v�^<BR>
 * 
 * @@ author 鰊]<BR> 
 * @@ version 1.0 <BR>
 */
public class WEB3FeqOrderCarryOverUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{
   
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverUpdateInterceptor.class);
 
    /**
     * (�J�z�������P�ʍs)<BR>
     * �J�z���̒����P�ʍs�I�u�W�F�N�g<BR>
     */
    private FeqOrderUnitParams carryOverOriginUnitParams;
    
    /**
     * (���z�v�Z����)<BR>
     * �O���������z�v�Z���ʃI�u�W�F�N�g
     */
    private WEB3FeqAmountCalcResult amountCalcResult;
    
    /**
     * (�v�Z�P��)<BR>
     * �v�Z�P��<BR>
     */
    private double roundPrice;
    
    /**
     * @@roseuid 42D0D8310148
     */
    public WEB3FeqOrderCarryOverUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�O�����������J�z�X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * ���������g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_carryOverOrderUnitParams - (�J�z�������P�ʍs)<BR>
     * �J�z���̒����P�ʍs�I�u�W�F�N�g<BR>
     * @@param l_amountCalcResult - (���z�v�Z����)<BR>
     * �O���������z�v�Z���ʃI�u�W�F�N�g<BR>
     * @@param l_dblPrice - (�v�Z�P��)<BR>
     * �v�Z�P��<BR>
     * @@roseuid 42B8C0A40133
     */
    public WEB3FeqOrderCarryOverUpdateInterceptor(FeqOrderUnitParams l_carryOverOrderUnitParams,
        WEB3FeqAmountCalcResult l_amountCalcResult,
        double l_dblPrice) 
    {
        this.carryOverOriginUnitParams = l_carryOverOrderUnitParams;
        this.amountCalcResult = l_amountCalcResult;
        this.roundPrice = l_dblPrice;
    }
    
    /**
     * (�i�����j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̃I�[�o�[���[�h�j <BR>
     * �����iͯ�ށj�e�[�u���X�V<BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �����h�c�ɊY�����钍���s�i�F����Params�j���擾����B<BR>
     * �擾���������s�i�F����Params�j���X�V�iDbUpdate�j����B <BR>
     * <BR>
     * �@@���ڐݒ���e�́A<BR>
     * �@@�yxTrade�z�⑫����.DB�X�V\05.�����J�z\05.2.�J�z�㒍��<BR>
     * �@@�@@�u�O�������J�z(�J�z��)_�O�������i�w�b�_�j�d�l.xls�v�Q�ƁB<BR>
     * @@param l_lngOrderId - (�����h�c)<BR>
     * �����h�c<BR>
     * @@roseuid 42C0E787008A
     */
    protected void mutate(long l_lngOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "mutate(long)";
        log.entering(STR_METHOD_NAME);
        
        QueryProcessor l_qp = null;
        FeqOrderParams l_feqOrderParams = null;
        try 
        {
            l_qp = Processors.getDefaultProcessor();             
            FeqOrderRow l_feqOrderRow = FeqOrderDao.findRowByOrderId(l_lngOrderId);
            l_feqOrderParams = new FeqOrderParams(l_feqOrderRow);
            //�X�V�҃R�[�h = �J�z���̒����̓�����
            l_feqOrderParams.setLastUpdater(this.carryOverOriginUnitParams.getLastUpdater());
            //�����iͯ�ށj�e�[�u���X�V
            l_qp.doUpdateQuery(l_feqOrderParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);

        }        
        log.exiting(STR_METHOD_NAME);   
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@[�p�����[�^.���� == ORDER_EXPIRED�̏ꍇ]<BR>
     * �@@�@@super.mutate(OrderManagerPersistenceType, 
     * <BR>�@@�@@�@@�@@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * �@@�@@���R�[������B<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@this.mutate()���R�[������B<BR>
     * <BR>
     * �@@�@@[mutate()�Ɏw�肷�����]<BR>
     * �@@�@@�@@����ID�F�@@�p�����[�^.�����P�ʍs.����ID<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V<BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * �@@���ڐݒ���e�́A<BR>
     * �@@[�p�����[�^.���� == ORDER_EXPIRED�̏ꍇ]<BR>
     * �@@�@@�yxTrade�z�⑫����.DB�X�V\05.�����J�z\05.1.�J�z������<BR>
     * �@@�@@�@@�u�O�������J�z(�J�z��)_�O�������P�ʎd�l.xls�v�Q�ƁB<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�yxTrade�z�⑫����.DB�X�V\05.�����J�z\05.2.�J�z�㒍��<BR>
     * �@@�@@�@@�u�O�������J�z(�J�z��)_�O�������P�ʎd�l.xls�v�Q�ƁB<BR>
     * �@@�@@<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_context - (����)<BR>
     * ����<BR>
     * @@param l_feqOrderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 42B8C13701EF
     */
    public FeqOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
     
        if (l_feqOrderUnitParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
                
        long l_lngOrderId = l_feqOrderUnitParams.getOrderId();       
        
        //�p�����[�^.���� == ORDER_EXPIRED�̏ꍇ
        if(OrderManagerPersistenceContext.ORDER_EXPIRED.equals(l_context))
        {
            //����.�X�V�^�C�v != null�̏ꍇ�A�����iͯ�ށj�e�[�u���X�V���s��
            if (l_updateType != null)
            {
                //�P�j�@@�����iͯ�ށj�e�[�u���X�V
                super.mutate(l_updateType, l_context, l_feqOrderUnitParams);
                l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            }
        } 
        //��L�ȊO�̏ꍇ
        else
        {
            //����.�X�V�^�C�v != null�̏ꍇ�A�����iͯ�ށj�e�[�u���X�V���s��
            if (l_updateType != null)
            {
                try
                {
                    //�P�j�@@�����iͯ�ށj�e�[�u���X�V
                    this.mutate(l_lngOrderId);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(), 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            
            //�،���ЃR�[�h = �J�z�������P��.�،���ЃR�[�h
            l_feqOrderUnitParams.setInstitutionCode(this.carryOverOriginUnitParams.getInstitutionCode());
            //�������� = �J�z�������P�ʂ̓�����
            l_feqOrderUnitParams.setOrderConditionType(this.carryOverOriginUnitParams.getOrderConditionType());
            //�����������Z�q = �J�z�������P�ʂ̓�����
            l_feqOrderUnitParams.setOrderCondOperator(this.carryOverOriginUnitParams.getOrderCondOperator());
            
            //�t�w�l��l = �J�z�������P�ʂ̓�����
            if (this.carryOverOriginUnitParams.getStopOrderPriceIsNull())
            {
                l_feqOrderUnitParams.setStopOrderPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setStopOrderPrice(this.carryOverOriginUnitParams.getStopOrderPrice());
            }
            
            //�iW�w�l�j�����w�l = �J�z�������P�ʂ̓�����
            if (this.carryOverOriginUnitParams.getWLimitPriceIsNull())
            {
                l_feqOrderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setWLimitPrice(this.carryOverOriginUnitParams.getWLimitPrice());
            }
            
            //���ϋ敪 = �J�z�������P�ʂ̓�����            
            l_feqOrderUnitParams.setSettleDiv(this.carryOverOriginUnitParams.getSettleDiv());
            //���񒍕��̒����`���l�� = �J�z�������P�ʂ̓�����
            l_feqOrderUnitParams.setOrderChanel(this.carryOverOriginUnitParams.getOrderChanel());
            //�󒍓��� = �J�z�������P�ʂ̓�����
            l_feqOrderUnitParams.setReceivedDateTime(this.carryOverOriginUnitParams.getReceivedDateTime());
            //�������ʃR�[�h�̔ԃT�[�r�X
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
                    (WEB3HostReqOrderNumberManageService)Services.getService(
                        WEB3HostReqOrderNumberManageService.class);
                        
            String l_strInstCode = this.carryOverOriginUnitParams.getInstitutionCode();
            
            long l_lngBranchId = this.carryOverOriginUnitParams.getBranchId();
            
            WEB3GentradeBranch l_branch = null;
            String l_strBranchCode = null;       
            String l_strOrderRequestNumber = null;                
            try
            {
                l_branch = new WEB3GentradeBranch(l_lngBranchId);
                l_strBranchCode = l_branch.getBranchCode();
                  
                l_strOrderRequestNumber = l_hostReqOrderNumberManageService.
                    getNewNumber(l_strInstCode, l_strBranchCode, ProductTypeEnum.FOREIGN_EQUITY);
                
            }
            catch (DataFindException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);
            }
            
            if(l_strOrderRequestNumber == null || l_strOrderRequestNumber.length() < 3)
            {
                log.error("���ʃR�[�h�s�����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //���ʃR�[�h = �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()
            l_feqOrderUnitParams.setOrderRequestNumber(l_strOrderRequestNumber);                                                                
            
            //�`�[No = "9"(WebBroker)�{���ʃR�[�h�̉�3��
            int l_intLength = l_strOrderRequestNumber.length();
            l_feqOrderUnitParams.setVoucherNo("9" + l_strOrderRequestNumber.substring(l_intLength - 3, l_intLength));
            //���񒍕��̎萔��No = ���z�v�Z����.get�萔��No()�i* �萔���v�Z���Ɏg�p�����l�j
            l_feqOrderUnitParams.setCommTblNo(this.amountCalcResult.getCommissionNumber());
            //���񒍕��̎萔��No�}�� = ���z�v�Z����.get�萔��No�}��()�i* �萔���v�Z���Ɏg�p�����l�j            
            l_feqOrderUnitParams.setCommTblSubNo(this.amountCalcResult.getCommissionBranchNumber());
            //���҃R�[�h�iSONAR�j = �J�z�������P�ʂ̓����� 
            l_feqOrderUnitParams.setSonarTraderCode(this.carryOverOriginUnitParams.getSonarTraderCode());            
            //�����P�� = �C���^�Z�v�^.get�v�Z�P��()�i* �T�Z��n����Z�o�Ɏg�p�����P���j            
            l_feqOrderUnitParams.setPrice(this.getRoundPrice());                                        
            //�T�Z��n��� = ���z�v�Z����.get��n���()
            l_feqOrderUnitParams.setEstimatedPrice(this.amountCalcResult.getNetAmount());
            //�T�Z��n����i�O�݁j
            l_feqOrderUnitParams.setFEstimatedPrice(this.amountCalcResult.getNetAmountFc());
            //����R�[�h�iSONAR�j=  11�F���ʊ���
            l_feqOrderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
            //�s��R�[�h�iSONAR�j= �J�z�������P�ʂ̓�����
            l_feqOrderUnitParams.setSonarMarketCode(this.carryOverOriginUnitParams.getSonarMarketCode());
            //�萔�����i�R�[�h = �J�z�������P�ʂ̓�����
            l_feqOrderUnitParams.setCommProductCode(this.carryOverOriginUnitParams.getCommProductCode());
            //���n�v���z = 0
            l_feqOrderUnitParams.setCapitalGain(0);
            //���n�v�Ŋz = 0
            l_feqOrderUnitParams.setCapitalGainTax(0);
            //���������E����敪 = 0:�����l
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            //�����o�H�敪 = �J�z�������P�ʂ̓�����
            l_feqOrderUnitParams.setOrderRootDiv(this.carryOverOriginUnitParams.getOrderRootDiv());
            //�s�ꂩ��m�F�ς݂̒����P�� = null
            l_feqOrderUnitParams.setConfirmedOrderPrice(null);
            //�s�ꂩ��m�F�ς݂̊T�Z��n��� = null
            l_feqOrderUnitParams.setConfirmedEstimatedPrice(null);
            //�s�ꂩ��m�F�ς݂̎��s���� = null
            l_feqOrderUnitParams.setConfirmedExecConditionType(null);
            //�����G���[���R�R�[�h = 0000�F����
            l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            //�t�@@�N�^�[
            l_feqOrderUnitParams.setFactor(" ");
            
            //�^�p�R�[�h = �O�������^�p�R�[�h�̔ԃT�[�r�X.get�V�K�^�p�R�[�h()
            WEB3FeqOrderEmpCodeManageService l_service =
                     (WEB3FeqOrderEmpCodeManageService)Services.getService(
                         WEB3FeqOrderEmpCodeManageService.class);
            
            Date l_datBizDate = null;
            try
            {
                l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);            
            }  
            WEB3GentradeMarket l_market = null;
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(this.carryOverOriginUnitParams.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_nfe.getMessage(),
                   l_nfe);
            }                                                         
            try
            {
                l_feqOrderUnitParams.setOrderEmpCode(l_service.getNewEmpCode(l_market, l_datBizDate));
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);            
            }            
            
            //�ڋq�敪 = �h��ʁh
            l_feqOrderUnitParams.setAccountDiv(WEB3AccRegAccountDivDef.GENERAL);
            //�o���I���������� = null
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            //���񒍕��̒����P�ʂh�c
            //�J�z�������P��.���񒍕��̒����P�ʂh�c = 0 �̏ꍇ�A�J�z�������P��.�����P�ʂh�c
            //����ȊO�̏ꍇ�A�J�z�������P��.���񒍕��̒����P�ʂh�c
            if (this.carryOverOriginUnitParams.getFirstOrderUnitId() == 0)
            {
                l_feqOrderUnitParams.setFirstOrderUnitId(this.carryOverOriginUnitParams.getOrderUnitId());
            }
            else
            {
                l_feqOrderUnitParams.setFirstOrderUnitId(this.carryOverOriginUnitParams.getFirstOrderUnitId());
            }
            //�X�V�҃R�[�h = �J�z�������P�ʂ̓�����
            l_feqOrderUnitParams.setLastUpdater(this.carryOverOriginUnitParams.getLastUpdater());
            
        }        
        log.exiting(STR_METHOD_NAME);      
        return l_feqOrderUnitParams;
    }
    
    /**
     * (get�J�z�������P�ʍs)<BR>
     * this.�J�z�������P�ʍs��ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 42BA691B02B8
     */
    public FeqOrderUnitParams getCarryOverOriginOrderUnitParams() 
    {
        return this.carryOverOriginUnitParams;
    }
    
    /**
     * (set�J�z�������P�ʍs)<BR>
     * �����̌J�z�������P�ʍs�����g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_feqOrderUnitParams - (�J�z�������P�ʍs)<BR>
     * �J�z���̒����P�ʍs�I�u�W�F�N�g<BR>
     * @@roseuid 42BA68C40335
     */
    public void setCarryOverOriginOrderUnitParams(FeqOrderUnitParams l_feqOrderUnitParams) 
    {
        this.carryOverOriginUnitParams = l_feqOrderUnitParams;
    }
    
    /**
     * (get���z�v�Z����)<BR>
     * this.���z�v�Z���ʂ�ԋp����B<BR>
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42BA6C990180
     */
    public WEB3FeqAmountCalcResult getAmountCalcResult() 
    {
        return this.amountCalcResult;
    }
    
    /**
     * (set���z�v�Z����)<BR>
     * ���z�v�Z���ʂ����g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_amountCalcResult - (���z�v�Z����)<BR>
     * �O���������z�v�Z���ʃI�u�W�F�N�g<BR>
     * @@roseuid 42BA6CCB03D1
     */
    public void setAmountCalcResult(WEB3FeqAmountCalcResult l_amountCalcResult) 
    {
        this.amountCalcResult = l_amountCalcResult;
    }
    
    /**
     * (get�v�Z�P��)<BR>
     * this.�v�Z�P����ԋp����B<BR>
     * @@return double
     * @@roseuid 42BA6DCC02A8
     */
    public double getRoundPrice() 
    {
        return this.roundPrice;
    }
    
    /**
     * (set�v�Z�P��)<BR>
     * �����̌v�Z�P�������g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_dblRoundPrice - (�v�Z�P��)<BR>
     * �v�Z�P��<BR>
     * @@roseuid 42BA6E0802B8
     */
    public void setRoundPrice(double l_dblRoundPrice) 
    {
        this.roundPrice = l_dblRoundPrice;
    }
}
@
