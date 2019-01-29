head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoSettleContractUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�ԍύX�V�C���^�Z�v�^�N���X(WEB3IfoSettleContractUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/11 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 2004/07/22 ���Ō� (���u) WEB3TransactionTypeSONARDef�� WEB3IfoSonarTradeCodeDef�������ւ���
Revesion History : 2004/07/29  Ḗ@@�� (Sinocom) �Ή��o�b�O WEB3_IFO_UT-000107
Revesion History : 2006/07/12 �s�p (���u) DB�X�V�d�lNo.086,092
Revesion History : 2007/01/30 �đo�g (���u) DB�X�V�d�lNo.135,151
Revesion History : 2007/06/11 �Ј��� (���u) �d�l�ύX���f��No.650 DB�X�V�d�lNo.168
Revesion History : 2007/06/21 �Ј��� (���u) DB�X�V�d�lNo.186
Revesion History : 2008/03/17 ���� (���u) DB�X�V�d�lNo.197
Revesion History : 2008/04/28 �����F (���u) ���f��868 DB�X�V�d�lNo.207
Revesion History : 2008/05/07 �����F (���u) ���f��884 DB�X�V�d�lNo.209
*/

package webbroker3.ifo;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.ifo.define.WEB3IfoVoucherNoFirstNumDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�ԍύX�V�C���^�Z�v�^)<BR>
 * �敨OP�ԍύX�V�C���^�Z�v�^�N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoSettleContractUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoSettleContractUpdateInterceptor.class);

    /**
     * (�敨OP�ԍϒ������e)
     */
    private WEB3IfoSettleContractOrderSpec futuresOptionSettleContractOrderSpec;
    
    /**
     * ���Ϗ���<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * �ꊇ�ԍώ��̏ꍇ�ݒ�<BR>
     */
    private String settletSequence;

    /**
     * (����敪)<BR>
     * 1 �F �[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j<BR>
     * null �F ��L�ȊO<BR>
     */
    private String sessionType;

    /**
     * (���҃R�[�h�iSONAR�j)<BR>
     * ���҃R�[�h�iSONAR�j <BR>
     * <BR>
     * ���敨OP�A���������������Ŏg�p <BR>
     * <BR>
     * �A���������������ł́A�敨OP�����P��.���҃R�[�h�iSONAR�j�� <BR>
     * �\�񒍕��P��.���҃R�[�h�iSONAR�j��ݒ肷�邽�߁B <BR>
     * <BR>
     * �A���������������ȊO�̓Z�b�g����Ȃ��̂�null�ł���B<BR>
     */
    private String sonarTraderCode;

    /**
     * (���񒍕��̒����`���l��)<BR>
     * ���񒍕��̒����`���l�� <BR>
     * <BR>
     * ���敨OP�A���������������Ŏg�p<BR>
     */
    private String orderChanel;

    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * <BR>
     * ���敨OP�A���������������Ŏg�p<BR>
     */
    private String orderRootDiv;

    /**
     * @@roseuid 40C07C0200CB
     */
    public WEB3IfoSettleContractUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�敨OP�ԍύX�V�C���^�Z�v�^)
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̐敨OP�ԍϒ������e���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_settleContractOrderSpec - �敨OP�ԍϒ������e
     * @@return webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor
     * @@roseuid 405E8A6503C4
     */
    public WEB3IfoSettleContractUpdateInterceptor(WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec) 
    {
        this.futuresOptionSettleContractOrderSpec = l_settleContractOrderSpec;  
    }
    
    /**
     * (�X�V�l�ݒ�)
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>
     * �ԋp����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     *   �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ <BR>
     *      �u�敨�ԍ�_�����P�ʃe�[�u���d�l.xls�v�Q�� <BR>
     *   �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ<BR>
     *      �uOP�ԍ�_�����P�ʃe�[�u���d�l.xls�v�Q�� <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_orderUnitParams - �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 405E899001B0
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process, 
        IfoOrderUnitParams l_orderUnitParams) 
    {
        //return null;
        final String STR_METHOD_NAME =
             "mutate(OrderManagerPersistenceType l_updateType," +
             "OrderManagerPersistenceContext l_process, " +
             "IfoOrderUnitParams l_orderUnitParams)";
        final String l_strBaseNumber = WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER; 
        
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnitParams == null)
        {
            log.debug("Enter the if path l_orderUnitParams is null.");
            log.error(STR_METHOD_NAME,new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ STR_METHOD_NAME ));
            log.debug("Exit the if path l_orderUnitParams is null.");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        //get product manager
        WEB3IfoProductManagerImpl l_productMgr =
            (WEB3IfoProductManagerImpl) l_tradingMod.getProductManager();
           
        //get WEB3Session Object
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
            OpLoginSecurityService.class);

        //get MainAccountRow
        MainAccount l_mainAccount = null;
        try
        {    
            log.debug("Enter the try path and get the account object.");
            l_mainAccount =
                l_finApp.getAccountManager().getMainAccount(l_orderUnitParams.getAccountId());
            log.debug("Succeeded in get the Account object.");
        }
        catch (NotFoundException l_nfe)
        {   log.debug("Failed to get the Account Object.");        
            log.error(STR_METHOD_NAME,l_nfe);   
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
     
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
        //get Market Object
        Market l_market = null;
        long l_lngMarketId = l_orderUnitParams.getMarketId();
        try
        {
            log.debug("Enter the try and get the market object"); 
            l_market =
                l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
            log.debug("Exit the try and succeed get the market object.");
        }
        catch (NotFoundException l_nfe)
        {
            log.debug("Failed to get the market object.");
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);   
         }

        try
        {   
            log.debug("Enter the try path that l_orderUnitParams is not null.");
            //�敨OP�����e�[�u��.�敨�^�I�v�V�����敪��ݒ肷��
            Product l_product = l_productMgr.getProduct(l_orderUnitParams.getProductId());
            IfoProductRow l_productRow =(IfoProductRow)l_product.getDataSourceObject();
            l_orderUnitParams.setFutureOptionDiv(l_productRow.getFutureOptionDiv());
            log.debug("FutureOptionDiv is:" + l_productRow.getFutureOptionDiv());
            //�C���^�Z�v�^.����������ݒ肷��
            l_orderUnitParams.setOrderConditionType(this.orderCond);
            log.debug("����������ݒ�:"+ this.orderCond);
            
            if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
            {                
                //�C���^�Z�v�^.�����������Z�q��ݒ肷��
                l_orderUnitParams.setOrderCondOperator(null);   
                
                //�C���^�Z�v�^.�t�w�l��l�^�C�v��ݒ肷��
                l_orderUnitParams.setStopPriceType(null);
                
                //�C���^�Z�v�^.�t�w�l��l��ݒ肷��
                l_orderUnitParams.setStopOrderPrice(null);
                            
                //�iW�w�l�j�����w�l)
                l_orderUnitParams.setWLimitPrice(null);
                // ���N�G�X�g�^�C�v
                l_orderUnitParams.setRequestType(null);
            }
            else
            {               
                //�C���^�Z�v�^.�����������Z�q��ݒ肷��
                l_orderUnitParams.setOrderCondOperator(this.orderCondOperator);                
                log.debug("�����������Z�q:"+ this.orderCondOperator);
                
                //�C���^�Z�v�^.�t�w�l��l�^�C�v��ݒ肷��
                l_orderUnitParams.setStopPriceType(this.stopOrderBasePriceType);
                
                //�C���^�Z�v�^.�t�w�l��l��ݒ肷��
                l_orderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);   
                
                // �iW�w�l�j�����w�l)<BR>            
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
                {
                    l_orderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitPrice(this.wLimitPriceChange);
                }
                // ���N�G�X�g�^�C�v
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
            }
            //���񒍕��̒����`���l��
            if (this.orderChanel == null)
            {
                //get ���񒍕��̒����`���l��
                String l_strOrderChannel =
                     l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
                l_orderUnitParams.setOrderChanel(l_strOrderChannel);
            }
            else
            {
                l_orderUnitParams.setOrderChanel(this.orderChanel);
            }
            //�󒍓���          
            Timestamp l_timeStamp = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            l_orderUnitParams.setReceivedDateTime(l_timeStamp);
            log.debug("�󒍓���:" + l_timeStamp);
            //���񒍕��̎萔��No     
            l_orderUnitParams.setCommTblNo(this.commRevMstId.getCommissionNo());
            log.debug("���񒍕��̎萔��No:" + this.commRevMstId.getCommissionNo());   
            //���񒍕��̎萔��No�}��
            l_orderUnitParams.setCommTblSubNo(this.commRevMstId.getCommissionRevNo());
            log.debug("���񒍕��̎萔��No�}��:" + this.commRevMstId.getCommissionRevNo());
            //���҃R�[�h�iSONAR�j
            if (this.sonarTraderCode == null)
            {
                //�ڋq.���҃R�[�h�iSONAR�j
                l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
            }
            else
            {
                //�C���^�Z�v�^.���҃R�[�h�iSONAR�j
                l_orderUnitParams.setSonarTraderCode(this.sonarTraderCode);
            }
            log.debug("���҃R�[�h�iSONAR�j" + l_mainAccountRow.getSonarTraderCode());
            //�����P��
            l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());   
            
            //���ʃR�[�h                    
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            WEB3HostReqOrderNumberManageService l_numberService =
                (WEB3HostReqOrderNumberManageService) Services.getService(
                WEB3HostReqOrderNumberManageService.class);
            String l_strOrderRequestNumber =
            l_numberService.getNewNumber(
                futuresOptionSettleContractOrderSpec.getInstitutionCode(),
                l_strBranchCode,
                ProductTypeEnum.IFO);
            
            l_orderUnitParams.order_request_number = l_strOrderRequestNumber;
            
            //�`�[No��ݒ肷��
            int l_intRequestNumberLength = l_strOrderRequestNumber.length() - 3;
            String l_strVoucherNo = l_strBaseNumber + l_strOrderRequestNumber.substring(l_intRequestNumberLength);
            l_orderUnitParams.setVoucherNo(l_strVoucherNo);
            log.debug("�`�[No��ݒ肷��:" + l_strVoucherNo);
            //�T�Z��n���
            l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
            log.debug("�T�Z��n���:" + this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
            //����R�[�h�iSONAR�j
            l_orderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            log.debug("����R�[�h:" + WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            //�s��R�[�h�iSONAR�j
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
            l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            log.debug("�s��R�[�h�iSONAR�j:" + l_marketRow.getSonarMarketCode());
            
            //�萔�����i�R�[�h 
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitParams.getFutureOptionDiv()))
            {
                l_orderUnitParams.setCommProductCode(
                    WEB3CommisionProductCodeDef.INDEX_OP);            
            }
            else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_orderUnitParams.getFutureOptionDiv()))
            {
                l_orderUnitParams.setCommProductCode(
                    WEB3CommisionProductCodeDef.INDEX_FUTURES);
            }            
            log.debug("�萔�����i�R�[�h :" + l_orderUnitParams.getCommProductCode());
            
            //���������E����敪
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            log.debug("���������E����敪:" + WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            //�����o�H�敪
            if (this.orderRootDiv == null)
            {
                //get �����o�H�敪
                String l_strOrderRootDiv =
                     l_opLoginSec.getSessionProperty(
                     WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                l_orderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
            }
            else
            {
                l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
            }
            //�s�ꂩ��m�F�ς݂̒����P��
            l_orderUnitParams.setConfirmedOrderPrice(null);
            log.debug("�s�ꂩ��m�F�ς݂̒����P��:" +  l_orderUnitParams.getConfirmedOrderPrice());
            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
            log.debug("�s�ꂩ��m�F�ς݂̊T�Z��n���:"+ l_orderUnitParams.getConfirmedEstimatedPrice());
            //�s�ꂩ��m�F�ς݂̎��s����
            l_orderUnitParams.setConfirmedExecConditionType(null);
            log.debug("�s�ꂩ��m�F�ς݂̎��s����:"+ l_orderUnitParams.getConfirmedExecConditionType());
            //���Ϗ��� 
            l_orderUnitParams.setClosingOrder(this.settletSequence);
            log.debug("���Ϗ���:" + l_orderUnitParams.getClosingOrder());
            //�����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            log.debug("�����G���[���R�R�[�h:" + l_orderUnitParams.getErrorReasonCode());
            log.debug("request type:" + WEB3RequestTypeDef.DEFAULT);

            //���񒍕��̒����P�ʂh�c
            l_orderUnitParams.setFirstOrderUnitId(this.futuresOptionSettleContractOrderSpec.getFirstOrderUnitId());

            //����������
            l_orderUnitParams.setOrgOrderConditionType(null);
            
            //�������������Z�q
            l_orderUnitParams.setOrgOrderCondOperator(null);
            
            //���t�w�l��l�^�C�v
            l_orderUnitParams.setOrgStopPriceType(null);
            
            //���t�w�l��l
            l_orderUnitParams.setOrgStopOrderPrice(null);
            
            //���iW�w�l�j�����w�l
            l_orderUnitParams.setOrgWLimitPrice(null);
            
            //���iW�w�l�j���s����
            l_orderUnitParams.setOrgWLimitExecCondType(null);
                        
            //�iW�w�l�j���s����
            //�ԍϒ������e.get�iW�w�l�j���s����
            //�������������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull
            if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
            {
                l_orderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
                {
                    l_orderUnitParams.setWLimitExecCondType(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitExecCondType(
                        this.futuresOptionSettleContractOrderSpec.getWLimitExecCondType());
                }
            }
            
            //�iW�w�l�j�֑ؑO�w�l
            l_orderUnitParams.setWLimitBeforeLimitPrice(null);
            
            //�iW�w�l�j�֑ؑO���s����
            l_orderUnitParams.setWLimitBeforeExecCondType(null);

            //�����o�H�敪:�敨OP�����T�[�r�X.get�����o�H�敪()�̖߂�l
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            String l_strSubmitOrderRouteDiv =
                l_ifoOrderService.getSubmitOrderRouteDiv(
                    l_mainAccountRow.getInstitutionCode(),
                    l_marketRow.getMarketCode());
            l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

            //�[��O�J�z�Ώۃt���O
            boolean l_blnEveningSessionCarryoverFlag =
                this.futuresOptionSettleContractOrderSpec.getEveningSessionCarryoverFlag();
            if (l_blnEveningSessionCarryoverFlag)
            {
                l_orderUnitParams.setEveningSessionCarryoverFlag(
                    BooleanEnum.TRUE);
            }
            else
            {
                l_orderUnitParams.setEveningSessionCarryoverFlag(
                    BooleanEnum.FALSE);
            }

            //����敪
            l_orderUnitParams.setSessionType(this.sessionType);

            //���v��敪
            l_orderUnitParams.setDayTradeType(this.commRevMstId.getDayTradeType());

            // ���������敪 expiration_date_type �ԍϒ������e.get���������敪()�̖߂�l
            l_orderUnitParams.setExpirationDateType(
                this.futuresOptionSettleContractOrderSpec.getExpirationDateType());
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
            log.debug("Enter the catch path and report the exception.");   
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,this.getClass().getName()+  "." + STR_METHOD_NAME);
           
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (set���Ϗ���)<BR>
     * ���Ϗ������Z�b�g����B<BR>
     * @@param l_strSettleSequence - ���Ϗ���
     * 
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������
     * �ꊇ�ԍώ��̏ꍇ�ݒ�
     * @@roseuid 4068E03801FF
     */
    public void setSettleSequence(String l_strSettleSequence) 
    {
        this.settletSequence = l_strSettleSequence;
    }
    
    /**
     * (get���Ϗ���)<BR>
     * ���Ϗ������擾����B<BR>
     * @@return String
     * @@roseuid 4068E04503C4
     */
    public String getSettleSequence() 
    {
        return this.settletSequence;
    }
    
    /**
     * (get����敪)<BR>
     * ����敪���擾����B<BR>
     * <BR>
     * @@return String
     */
    public String getSessionType()
    {
        return this.sessionType;
    }

    /**
     * (set����敪)<BR>
     * ����敪���Z�b�g����B<BR>
     * @@param l_strSessionType - (����敪)<BR>
     * ����敪<BR>
     * 1 �F �[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j<BR>
     * ��L�ȊO �F null<BR>
     */
    public void setSessionType(String l_strSessionType)
    {
        this.sessionType = l_strSessionType;
    }

    /**
     * (set���҃R�[�h�iSONAR�j)<BR>
     * ���҃R�[�h�iSONAR�j���Z�b�g����B<BR>
     * @@param l_strSonarTraderCode - (���҃R�[�h�iSONAR�j)<BR>
     * ���҃R�[�h�iSONAR�j<BR>
     */
    public void setSonarTraderCode(String l_strSonarTraderCode)
    {
        this.sonarTraderCode = l_strSonarTraderCode;
    }

    /**
     * (set���񒍕��̒����`���l��)<BR>
     * ���񒍕��̒����`���l�����Z�b�g����B<BR>
     * @@param l_strOrderChanel - (���񒍕��̒����`���l��)<BR>
     * ���񒍕��̒����`���l��<BR>
     */
    public void setOrderChanel(String l_strOrderChanel)
    {
        this.orderChanel = l_strOrderChanel;
    }

    /**
     * (set�����o�H�敪)<BR>
     * �����o�H�敪���Z�b�g����B<BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        this.orderRootDiv = l_strOrderRootDiv;
    }
}
@
