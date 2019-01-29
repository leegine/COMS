head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOpenContractUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�V�K���X�V�C���^�Z�v�^�N���X(WEB3IfoOpenContractUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 䈋� (���u) �V�K�쐬
Revesion History : 2006/07/13 �s�p (���u) DB�X�V�d�lNo.087
Revesion History : 2007/01/30 �đo�g (���u) DB�X�V�d�lNo.131,138
Revesion History : 2007/06/08 �Ј��� (���u) �d�l�ύX���f��No.667 DB�X�V�d�lNo.169
Revesion History : 2008/03/17 �����F (���u) DB�X�V�d�lNo.197
Revesion History : 2008/04/28 �����F (���u) ���f��868 DB�X�V�d�lNo.207
Revesion History : 2008/05/07 �����F (���u) ���f��884,885 DB�X�V�d�lNo.209
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
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
 * (�敨OP�V�K���X�V�C���^�Z�v�^)<BR>
 * �敨OP�V�K���X�V�C���^�Z�v�^�N���X<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoOpenContractUpdateInterceptor
    extends WEB3IfoOrderUpdateInterceptor
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOpenContractUpdateInterceptor.class);

    /**
     * (�敨OP�V�K���������e)<BR>
     */
    private WEB3IfoOpenContractOrderSpec ifoOpenContractOrderSpec;

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
     * @@roseuid 40C07C02038A
     */
    public WEB3IfoOpenContractUpdateInterceptor()
    {

    }

    /**
     * (�敨OP�V�K���X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̐敨OP�V�K���������e���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_openContractOrderSpec - �敨OP�V�K���������e
     * @@return webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor
     * @@roseuid 405E8A1302BA
     */
    public WEB3IfoOpenContractUpdateInterceptor(WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        ifoOpenContractOrderSpec = l_openContractOrderSpec;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>�E
     * �p����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     *   �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ 
     *      �u�敨�V�K��_�����P�ʃe�[�u���d�l.xls�v�Q�� 
     *   �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ
     *      �uOP�V�K��_�����P�ʃe�[�u���d�l.xls�v�Q�� 
     * 
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 405E898A03A4
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType "
                + "l_orderManagerPersistenceType, "
                + "OrderManagerPersistenceContext "
                + "l_orderManagerPersistenceContext, "
                + "IfoOrderUnitParams l_ifoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnitParams == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_futureDiv;
        String l_orderChannel;
        Date l_receivedDateTime;
        long l_accountId;
        String l_voucherNo;
        String l_sonarMarketCode;
        int l_orderRequestNumLen;
        //(�敨�^�I�v�V�����敪)<BR>
        IfoProductRow l_IfoProductRow = null;

        try
        {
            l_IfoProductRow =
                IfoProductDao.findRowByPk(l_ifoOrderUnitParams.product_id);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }

        l_futureDiv = l_IfoProductRow.getFutureOptionDiv();
        l_ifoOrderUnitParams.setFutureOptionDiv(l_futureDiv);

        // (��������)<BR>
        l_ifoOrderUnitParams.setOrderConditionType(this.orderCond);
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            // (�����������Z�q)<BR>       
            l_ifoOrderUnitParams.setOrderCondOperator(null);

            // (�t�w�l��l�^�C�v)<BR>      
            l_ifoOrderUnitParams.setStopPriceType(null);

            // (�t�w�l��l)<BR>
            l_ifoOrderUnitParams.setStopOrderPrice(null);

            // �iW�w�l�j�����w�l)<BR>        
            l_ifoOrderUnitParams.setWLimitPrice(null);
            // ���N�G�X�g�^�C�v
            l_ifoOrderUnitParams.setRequestType(null);
        }
        else
        {
            // (�����������Z�q)<BR>       
            l_ifoOrderUnitParams.setOrderCondOperator(this.orderCondOperator);

            // (�t�w�l��l�^�C�v)<BR>      
            l_ifoOrderUnitParams.setStopPriceType(this.stopOrderBasePriceType);

            // (�t�w�l��l)<BR>
            l_ifoOrderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);
            
            // �iW�w�l�j�����w�l)<BR>            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
            {
                l_ifoOrderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_ifoOrderUnitParams.setWLimitPrice(this.wLimitPriceChange);
            }
            // ���N�G�X�g�^�C�v
            l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
        }

        // ���񒍕��̒����`���l��
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);

        if (this.orderChanel == null)
        {
            l_orderChannel =
                l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_CHANNEL);
            l_ifoOrderUnitParams.setOrderChanel(l_orderChannel);
        }
        else
        {
            l_ifoOrderUnitParams.setOrderChanel(this.orderChanel);
        }

        // �󒍓���<BR>
        l_receivedDateTime =
            (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_ifoOrderUnitParams.setReceivedDateTime(l_receivedDateTime);

        // ���񒍕��̎萔��No<BR>
        if (this.commRevMstId == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        l_ifoOrderUnitParams.setCommTblNo(this.commRevMstId.getCommissionNo());

        // ���񒍕��̎萔��No�}��<BR>
        l_ifoOrderUnitParams.setCommTblSubNo(
            this.commRevMstId.getCommissionRevNo());

        //���҃R�[�h�iSONAR�j<BR>
        l_accountId = l_ifoOrderUnitParams.account_id;
        MainAccount l_mainAccount = null;

        // �s��R�[�h�iSONAR�j<br>
        MarketRow l_MarketRow = null;

        try
        {
            l_mainAccount =
                l_finApp.getAccountManager().getMainAccount(l_accountId);
            l_MarketRow =
                (MarketRow)l_finApp
                    .getFinObjectManager()
                    .getMarket(l_ifoOrderUnitParams.getMarketId())
                    .getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        MainAccountRow l_MainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�C���^�Z�v�^.���҃R�[�h�iSONAR�j=null�̏ꍇ �ڋq.���҃R�[�h�iSONAR�j
        if (this.sonarTraderCode == null)
        {
            //�ڋq.���҃R�[�h�iSONAR�j
            l_ifoOrderUnitParams.setSonarTraderCode(l_MainAccountRow.getSonarTraderCode());
        }
        else
        {
            //�C���^�Z�v�^.���҃R�[�h�iSONAR�j
            l_ifoOrderUnitParams.setSonarTraderCode(this.sonarTraderCode);
        }

        // �����P��<BR>            
        if (this.estimateDeliveryAmounCalcResult == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        l_ifoOrderUnitParams.setPrice(
            this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());
        
        WEB3HostReqOrderNumberManageService l_numberService =
                            (WEB3HostReqOrderNumberManageService) Services.getService(
                                WEB3HostReqOrderNumberManageService.class);
        String l_newOrderRequestNumber = null;
        try
        {
            l_newOrderRequestNumber =
                l_numberService.getNewNumber(
                    ifoOpenContractOrderSpec.getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    ProductTypeEnum.IFO);            
        }
        catch (WEB3BaseException l_wbex)
        {
            log.error(STR_METHOD_NAME, l_wbex);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }

        l_ifoOrderUnitParams.order_request_number = l_newOrderRequestNumber;
        
        //�T�Z��n���<br> 
        l_ifoOrderUnitParams.setEstimatedPrice(
            this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());

        // �s��R�[�h�iSONAR�j<br>
        l_sonarMarketCode = l_MarketRow.getSonarMarketCode();
        l_ifoOrderUnitParams.setSonarMarketCode(l_sonarMarketCode);

        //�萔�����i�R�[�h<br>
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitParams.getFutureOptionDiv()))
        {
            l_ifoOrderUnitParams.setCommProductCode(
                WEB3CommisionProductCodeDef.INDEX_OP);            
        }
        else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitParams.getFutureOptionDiv()))
        {
            l_ifoOrderUnitParams.setCommProductCode(
                WEB3CommisionProductCodeDef.INDEX_FUTURES);
        }

        // ���������E����敪<br>      
        l_ifoOrderUnitParams.setModifyCancelType(
            WEB3ModifyCancelTypeDef.INITIAL_VALUE);

        //�s�ꂩ��m�F�ς݂̒����P��
        l_ifoOrderUnitParams.setConfirmedOrderPrice(null);

        //�s�ꂩ��m�F�ς݂̊T�Z��n���
        l_ifoOrderUnitParams.setConfirmedEstimatedPrice(null);

        //�s�ꂩ��m�F�ς݂̎��s����
        l_ifoOrderUnitParams.setConfirmedExecConditionType(null);

        //���Ϗ���
        l_ifoOrderUnitParams.setClosingOrder(null);

        
		//���񒍕��̒����P�ʂh�c
        l_ifoOrderUnitParams.setFirstOrderUnitId(ifoOpenContractOrderSpec.getFirstOrderUnitId());

        // �����G���[���R�R�[�h<br>  
        l_ifoOrderUnitParams.setErrorReasonCode(
            WEB3ErrorReasonCodeDef.NORMAL);

        l_orderRequestNumLen = l_newOrderRequestNumber.length();
        l_voucherNo = l_newOrderRequestNumber.substring(l_orderRequestNumLen - 3);
        l_voucherNo = WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER + l_voucherNo;
        l_ifoOrderUnitParams.setVoucherNo(l_voucherNo);

        //�����o�H�敪
        if (this.orderRootDiv == null)
        {
            String l_strOrderRootDiv =
                l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            l_ifoOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
        }
        else
        {
            l_ifoOrderUnitParams.setOrderRootDiv(this.orderRootDiv);
        }

        //����R�[�h�iSONAR�j
        l_ifoOrderUnitParams.setSonarTradedCode(
            WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
        
        //����������
        l_ifoOrderUnitParams.setOrgOrderConditionType(null);
        
        //�������������Z�q
        l_ifoOrderUnitParams.setOrgOrderCondOperator(null);
        
        //���t�w�l��l�^�C�v
        l_ifoOrderUnitParams.setOrgStopPriceType(null);
        
        //���t�w�l��l
        l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
        
        //���iW�w�l�j�����w�l
        l_ifoOrderUnitParams.setOrgWLimitPrice(null);
        
        //���iW�w�l�j���s����
        l_ifoOrderUnitParams.setOrgWLimitExecCondType(null);
        
        //�iW�w�l�j���s����
        //�V�K���������e.get�iW�w�l�j���s����
        //�������������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            l_ifoOrderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
            {
                l_ifoOrderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                l_ifoOrderUnitParams.setWLimitExecCondType(
                    this.ifoOpenContractOrderSpec.getWLimitExecCondType());
            }
        }
        
        //�iW�w�l�j�֑ؑO�w�l
        l_ifoOrderUnitParams.setWLimitBeforeLimitPrice(null);
        
        //�iW�w�l�j�֑ؑO���s����
        l_ifoOrderUnitParams.setWLimitBeforeExecCondType(null);

        try
        {
            //�����o�H�敪:�敨OP�����T�[�r�X.get�����o�H�敪()�̖߂�l
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            String l_strSubmitOrderRouteDiv =
                l_ifoOrderService.getSubmitOrderRouteDiv(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_MarketRow.getMarketCode());
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        //�[��O�J�z�Ώۃt���O
        boolean l_blnEveningSessionCarryoverFlag = this.ifoOpenContractOrderSpec.getEveningSessionCarryoverFlag();
        if (l_blnEveningSessionCarryoverFlag)
        {
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(
                BooleanEnum.TRUE);
        }
        else
        {
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(
                BooleanEnum.FALSE);
        }

        //����敪
        l_ifoOrderUnitParams.setSessionType(this.sessionType);

        //���v��敪
        l_ifoOrderUnitParams.setDayTradeType(null);

        //���������敪
        l_ifoOrderUnitParams.setExpirationDateType(this.ifoOpenContractOrderSpec.getExpirationDateType());

        log.exiting(STR_METHOD_NAME);

        return l_ifoOrderUnitParams;
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
     * ����敪��ݒ肷��B<BR>
     * @@param l_strSessionType - (����敪)<BR>
     * ����敪
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
