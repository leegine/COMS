head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������X�V�C���^�Z�v�^(WEB3FeqOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19���U (���u) �V�K�쐬
                   2005/07/27 䈋��@@(���u) ���r���[
*/
package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;

import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������X�V�C���^�Z�v�^)<BR>
 * �O�����������X�V�C���^�Z�v�^�N���X<BR>
 * @@author ���U(���u)
 * @@version 1.0
*/
public class WEB3FeqOrderUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderUpdateInterceptor.class);

    /**
     * (�������e)<BR>
     * �O�������V�K�������e�I�u�W�F�N�g<BR>
     */
    private WEB3FeqNewOrderSpec orderSpec;
    
    /**
     * (���z�v�Z����)<BR>
     * �O���������z�v�Z���ʃI�u�W�F�N�g<BR>
     */
    private WEB3FeqAmountCalcResult amountRound;
    
    /**
     * (�v�Z�P��)<BR>
     * �v�Z�P��<BR>
     */
    private double roundPrice;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    private String orderConditionType;
    
    /**
     * (�����������Z�q)<BR>
     * �����������Z�q<BR>
     */
    private String orderCondOperator;
    
    /**
     * (���������P��)<BR>
     * ���������P��<BR>
     */
    private double orderConditionTypePrice;

    
    /**
     * @@roseuid 42D0DB2F0242
     */    
    public WEB3FeqOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�O�����������X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �p�����[�^�̍��ڂ��A�e�v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_orderSpec - (�������e)<BR>
     * �������e<BR>
     * 
     * @@param l_calcResult - (�v�Z����)<BR>
     * �v�Z����<BR>
     * 
     * @@param l_dblCalcPrice - (�v�Z�P��)<BR>
     * �v�Z�P��<BR>
     * 
     * @@param l_strOrderCond - (��������)<BR>
     * ��������<BR>
     * 
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * 
     * @@param l_dblOrderCondPrice - (���������P��)<BR>
     * ���������P��<BR>
     * @@roseuid 428C83A6010C
     */
    public WEB3FeqOrderUpdateInterceptor(
        WEB3FeqNewOrderSpec l_orderSpec, 
        WEB3FeqAmountCalcResult l_calcResult, 
        double l_dblCalcPrice, 
        String l_strOrderCond, 
        String l_strOrderCondOperator, 
        double l_dblOrderCondPrice) 
    {
        this.orderSpec = l_orderSpec;
        this.amountRound = l_calcResult;
        this.roundPrice = l_dblCalcPrice;
        this.orderConditionType = l_strOrderCond;
        this.orderCondOperator = l_strOrderCondOperator;
        this.orderConditionTypePrice = l_dblOrderCondPrice;         
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@super.mutate(OrderManagerPersistenceType,<BR>
     * �@@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V<BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * ���ڐݒ���e�́ADB�X�V�d�l<BR>
     * �u���t_�O�������P�ʃe�[�u��.xls�v<BR>
     * �u���t_�O�������P�ʃe�[�u��.xls�v<BR>
     * �Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_context - (����)<BR>
     * ����<BR>
     * @@param l_orderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j<BR>
     * @@return FeqOrderUnitParams
     * @@roseuid 428C83A600ED
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, " 
            + "FeqOrderUnitParams) ";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //����.�X�V�^�C�v != null�̏ꍇ�A�����iͯ�ށj�e�[�u���X�V���s��
        if (l_updateType != null)
        {
            //�P�j�@@�����iͯ�ށj�e�[�u���X�V
            l_orderUnitParams = super.mutate(
                l_updateType,
                l_context, 
                l_orderUnitParams);
        }
        
        //�Q�j�@@�����P�ʃe�[�u���X�V
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
         
        try
        {
            //�،���ЃR�[�h:�⏕����.getInstitution()�ɊY������،���ЃR�[�h
            AccountManager l_accountManager = l_finApp.getAccountManager();
                           
            l_orderUnitParams.setInstitutionCode(
                l_accountManager.getSubAccount(l_orderUnitParams.getAccountId(), 
                l_orderUnitParams.getSubAccountId()).getInstitution().getInstitutionCode());
            
            //��������:�C���^�Z�v�^.get��������()
            String l_strOrderConditionType = getOrderConditionType();
            l_orderUnitParams.setOrderConditionType(l_strOrderConditionType);
            
            //�����������Z�q:�C���^�Z�v�^.get�����������Z�q()
            //(* ����������0�FDEFAULT�i�����w��Ȃ��j�̏ꍇ�́Anull�j
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setOrderCondOperator(null);
            }
            else
            {    
                l_orderUnitParams.setOrderCondOperator(getOrderCondOperator());
            }
            
            //�t�w�l��l:�C���^�Z�v�^.get���������P��()
            //�i* ����������0�FDEFAULT�i�����w��Ȃ��j�̏ꍇ�́Anull�j
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setStopOrderPrice(null);
            }
            else
            {    
                l_orderUnitParams.setStopOrderPrice(getOrderConditionTypePrice());
            }
            
            //�iW�w�l�j�����w�l:�������e.get�iW�w�l�j�����w�l()
            //(* �����������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull�j
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setWLimitPrice(this.orderSpec.getWLimitPrice());
            }
            else
            {
                l_orderUnitParams.setWLimitPrice(null);    
            }
            
            //���ϋ敪:�������e.get���ϋ敪()
            l_orderUnitParams.setSettleDiv(this.orderSpec.getSettleDiv());
            
            //���񒍕��̒����`���l��:�Z�b�V�������擾���������ڂ̒l
            //�Z�b�V�������擾���������ڂ̒l
             OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);   

            l_orderUnitParams.setOrderChanel(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
            
            //�󒍓���:�T�[�o���ŃT�[�r�X���N�����ꂽ���ԁi�v�Z�����i���ʁj(*2) �������t�@@���Q�Ɓj
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            //�`�[No:"9"(WebBroker)�{���ʃR�[�h�̉�3��
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
                    
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(
                l_orderUnitParams.getBranchId());

            String l_strOrderRequestNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_orderUnitParams.getInstitutionCode(), 
                l_branch.getBranchCode(), 
                ProductTypeEnum.FOREIGN_EQUITY);
            if(l_strOrderRequestNumber == null || l_strOrderRequestNumber.length() < 3)
            {
                log.error("���ʃR�[�h�s�����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //���ʃR�[�h:�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()
            l_orderUnitParams.setOrderRequestNumber(l_strOrderRequestNumber);  
            
            l_orderUnitParams.setVoucherNo("9" 
                + l_orderUnitParams.getOrderRequestNumber().substring(l_strOrderRequestNumber.length() - 3));
            
            //���񒍕��̎萔��No:���z�v�Z����.get�萔��No()(* �萔���v�Z���Ɏg�p�����l�j
            l_orderUnitParams.setCommTblNo(this.amountRound.getCommissionNumber());
            
            //���񒍕��̎萔��No�}��:���z�v�Z����.get�萔��No�}��()�i* �萔���v�Z���Ɏg�p�����l�j
            l_orderUnitParams.setCommTblSubNo(this.amountRound.getCommissionBranchNumber());
            
            //���҃R�[�h�iSONAR�j:�ڋq.���҃R�[�h�iSONAR�j�i* SONAR�ŊǗ����Ă���ڋq�̈��ҁj                
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_accountManager.getMainAccount(l_orderUnitParams.getAccountId()).getDataSourceObject();
            l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
            
            //�����P��:�C���^�Z�v�^.�v�Z�P��()�i* �T�Z��n����Z�o�Ɏg�p�����P���j
            l_orderUnitParams.setPrice(this.roundPrice);            
                                                                      
            //�T�Z��n���:���z�v�Z����.get��n���()
            l_orderUnitParams.setEstimatedPrice(this.amountRound.getNetAmount());
            
            //�T�Z��n����i�O�݁j:���z�v�Z����.get��n����i�O�݁j()
            l_orderUnitParams.setFEstimatedPrice(this.amountRound.getNetAmountFc());
            
            //����R�[�h�iSONAR�j: 11�F���ʊ���(WEB3TransactionTypeSONARDef�ɂĒ�`)
            l_orderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
                            
            //�s��R�[�h�iSONAR�j:�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h(SONAR)
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market  = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                l_orderUnitParams.getMarketId());
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
            l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
           
            //�萔�����i�R�[�h:�S�O�F�O������(WEB3CommisionProductCodeDef�ɂĒ�`)
            l_orderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.FOREIGN_EQITY);
            
            //���n�v���z:0
            l_orderUnitParams.setCapitalGain(0);
            
            //���n�v�Ŋz:0
            l_orderUnitParams.setCapitalGainTax(0);
            
            //���������E����敪:0:�����l
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            //�����o�H�敪:�Z�b�V�������擾���������ڂ̒l
            l_orderUnitParams.setOrderRootDiv(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));          
            
            //�s�ꂩ��m�F�ς݂̒����P��:null                         
            l_orderUnitParams.setConfirmedOrderPrice(null);
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n���:null                         
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
            
            //�s�ꂩ��m�F�ς݂̎��s����:null                          
            l_orderUnitParams.setConfirmedExecConditionType(null);
            
            //�����G���[���R�R�[�h:0000�F����
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //�t�@@�N�^�[:�u�����N                
            l_orderUnitParams.setFactor(" ");

            //����.�X�V�^�C�v != null�̏ꍇ�A�^�p�R�[�h�̐ݒ���s��
            if (l_updateType != null)
            {
                //�^�p�R�[�h:�O�������^�p�R�[�h�̔ԃT�[�r�X.get�V�K�^�p�R�[�h()
                WEB3FeqOrderEmpCodeManageService l_service =
                     (WEB3FeqOrderEmpCodeManageService)Services.getService(
                        WEB3FeqOrderEmpCodeManageService.class);
                String l_strBizDate = l_orderUnitParams.getBizDate();
                Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");

                l_orderUnitParams.setOrderEmpCode(l_service.getNewEmpCode(l_market, l_datBizDate));
            }
            
            //�ڋq�敪:�h��ʁh
            l_orderUnitParams.setAccountDiv(WEB3AccountDivDef.NORMAL);
            
            //�o���I����������:null
            l_orderUnitParams.setExecEndTimestamp(null);  
            
            //���񒍕��̒����P�ʂh�c:�������e.get���񒍕��̒����P��ID()�̖߂�l�B(WEB3-XBFEQ-A-CD-0065)
            l_orderUnitParams.setFirstOrderUnitId(this.orderSpec.getFirstOrderUnitId());
            
            //�X�V�҃R�[�h:"�ڋq���͂̏ꍇ�F �ڋq�R�[�h;�㗝���͂̏ꍇ�F ���҃R�[�h" 
            if (this.orderSpec.getTrader() == null)
            {
                // �ڋq�R�[�h���擾
                String l_strAccountCode = l_accountManager.getMainAccount(
                    l_orderUnitParams.getAccountId()).getAccountCode();
                l_orderUnitParams.setLastUpdater(l_strAccountCode);
            }
            // �㗝���͂̏ꍇ
            else
            {
                l_orderUnitParams.setLastUpdater(this.orderSpec.getTrader().getTraderCode());
            }  
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
        catch (NotFoundException l_nfex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_nfex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfex.getMessage(),
                l_nfex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (get�������e)<BR>
     * this.�������e��ԋp����B<BR>
     * @@return webbroker3.feq.WEB3FeqNewOrderSpec
     * @@roseuid 4295DD2C0212
     */
    public WEB3FeqNewOrderSpec getOrderSpec() 
    {
        return this.orderSpec;
    }
    
    /**
     * (get���z�v�Z����)<BR>
     * this.���z�v�Z���ʂ�ԋp����B<BR>
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 4295DD510203
     */
    public WEB3FeqAmountCalcResult getAmountRound() 
    {
        return this.amountRound;
    }
    
    /**
     * (get�v�Z�P��)<BR>
     * this.�v�Z�P����ԋp����B<BR>
     * @@return double
     * @@roseuid 4295DD760195
     */
    public double getRoundPrice() 
    {
        return this.roundPrice;
    }
    
    /**
     * (get��������)<BR>
     * this.����������ԋp����B<BR>
     * @@return String
     * @@roseuid 4295DD9301D4
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }   
    
    /**
     * (get�����������Z�q)<BR>
     * this.�����������Z�q��ԋp����B<BR>
     * @@return String
     * @@roseuid 4295DDB002CE
     */
    public String getOrderCondOperator() 
    {
        return this.orderCondOperator;
    }
    
    /**
     * (get���������P��)<BR>
     * this.���������P����ԋp����B<BR>
     * @@return double
     * @@roseuid 4295DDB202FD
     */
    public double getOrderConditionTypePrice() 
    {
        return this.orderConditionTypePrice;
    }
}
@
