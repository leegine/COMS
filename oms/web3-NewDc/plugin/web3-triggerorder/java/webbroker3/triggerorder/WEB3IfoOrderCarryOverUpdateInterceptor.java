head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����J�z�X�V�C���^�Z�v�^(WEB3IfoOrderCarryOverUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���� (���u) �V�K�쐬
              001: 2004/07/21 ���Ō�(���u) mutate()���C��
              001: 2004/07/21 ���Ō�(���u) mutate()���C�� �Ή��o�b�O WEB3_IFO_UT-000050 WEB3_IFO_UT-000051
                 : 2006/7/27 �юu�� (���u) DB�X�V�d�lNo.102,106
                 : 2006/10/16 �юu�� (���u) DB�X�V�d�lNo.116
Revesion History : 2007/01/25 �����F (���u) DB�X�V�d�lNo.144�A159
Revesion History : 2007/06/21 �Ј��� (���u) DB�X�V�d�lNo.187
Revesion History : 2007/06/21 �Ј��� (���u) ���f��No.744
Revesion History : 2007/06/21 �Ј��� (���u) ���f��No.765 DB�X�V�d�lNo.190
Revesion History : 2008/04/15 ��іQ (���u) �g���K�[�����c�a�X�V�d�l052,053
*/

package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoUpdateInterceptor;
import webbroker3.ifo.define.WEB3IfoVoucherNoFirstNumDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�����J�z�X�V�C���^�Z�v�^)<BR>
 * �敨OP�����J�z�X�V�C���^�Z�v�^�N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3IfoOrderCarryOverUpdateInterceptor extends WEB3IfoUpdateInterceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderCarryOverUpdateInterceptor.class);

    /**
     * (�i�J�z���j�����P��Params)<BR>
     * �J�z���̒����P�ʍs�I�u�W�F�N�g<BR>
     */
    protected IfoOrderUnitParams carryOverOrderUnitParams;

    /**
     * �G���[�R�[�h
     */
    protected String errorCode;

    /**
     * �萔���I�u�W�F�N�g
     */
    protected WEB3GentradeCommission commissionFee;

    /**
     * �T�Z��n����v�Z���ʃI�u�W�F�N�g
     */
    protected WEB3IfoEstimateDeliveryAmountCalcResult estimatedDeliveryAmountCalculationResult;

    /**
     * ����敪
     */
    protected String sessionType;

    /**
     * @@roseuid 40C09F9003D8
     */
    public WEB3IfoOrderCarryOverUpdateInterceptor()
    {

    }

    /**
     * (�敨OP�����J�z�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A���������g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_orderUnitParams - �i�J�z���j�����P�ʍs�I�u�W�F�N�g
     * 
     * @@param l_strOrderErrorReasonCode - �����G���[���R�R�[�h<BR>
     * <BR>
     * DB���C�A�E�g<BR>
     * �����P�ʃe�[�u���d�l.xls<BR>
     * �u�i�����P�ʃe�[�u���⑫�j�����G���[���R�R�[�h�v�V�[�g�Q�ƁB<BR>
     * 
     * @@return webbroker3.ifo.WEB3IfoOrderCarryOverUpdateInterceptor
     * @@roseuid 40A0A0050079
     */
    public WEB3IfoOrderCarryOverUpdateInterceptor(
        IfoOrderUnitParams l_orderUnitParams,
        String l_strOrderErrorReasonCode)
    {
        this.carryOverOrderUnitParams = l_orderUnitParams;
        this.errorCode = l_strOrderErrorReasonCode;
    }

    /**
     * (set�i�J�z���j�����P��Params)
     * @@param l_orderUnitParams - (�i�J�z���j�����P��Params)<BR>
     * �i�J�z���j�����P�ʍs�I�u�W�F�N�g<BR>
     * @@roseuid 40A09EC201FF
     */
    public void setOrderUnitParams(IfoOrderUnitParams l_orderUnitParams)
    {
        carryOverOrderUnitParams = l_orderUnitParams;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����̒����P��Params�Ɋg������(*)��ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �|�J�z�������̏ꍇ(*1)<BR>
     * �@@�Z�b�g���e�ɂ��ẮA�ȉ��̃t�@@�C�����Q�ƁB<BR>
     * <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V<BR>
     * �@@�uOP�����J�z_�����P�ʃe�[�u���d�l[�J�z��].xls�v<BR>
     * <BR>
     * �|���������̏ꍇ�i*2�j<BR>
     * �@@�Z�b�g���e�ɂ��ẮA�ȉ��̃t�@@�C�����Q�ƁB<BR>
     * <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V<BR>
     * �@@�uOP�����J�z_�����P�ʃe�[�u���d�l[���������i�[�꒍���j].xls�v<BR>
     * <BR>
     * <BR>
     * �@@(*1) �J�z�������̔���<BR>
     * �@@this.�i�J�z���j�����P��Params.�����P�ʂh�c == <BR>
     * ����.�����P��Params.�����P�ʂh�c<BR>
     * <BR>
     * �@@(*2) ���������̔���<BR>
     * �@@this.�i�J�z���j�����P��Params.�����P�ʂh�c != <BR>
     * ����.�����P��Params.�����P�ʂh�c<BR>
     * @@param l_updateType - (�X�V�^�C�v)
     * @@param l_context - (�R���e�L�X�g)
     * @@param l_orderUnitRow - �����P�ʍs�I�u�W�F�N�g
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40A09F1A01A2
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        IfoOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = getClass().getName() + ".mutate() ";

        log.debug(STR_METHOD_NAME + ": ENTER");
        try
        {
            if (this.carryOverOrderUnitParams.order_unit_id == l_orderUnitParams.order_unit_id)
            {
                //�J�z�������̏ꍇ
                
                //�����G���[���R�R�[�h
                l_orderUnitParams.setErrorReasonCode(this.errorCode);
            }
            else if (this.carryOverOrderUnitParams.order_unit_id != l_orderUnitParams.order_unit_id)
            {
                //���������̏ꍇ
                
                //�敨�^�I�v�V�����敪
                l_orderUnitParams.setFutureOptionDiv(this.carryOverOrderUnitParams.future_option_div);
                
                //��������
                l_orderUnitParams.setOrderConditionType(this.carryOverOrderUnitParams.order_condition_type);
                
                //�����������Z�q
                l_orderUnitParams.setOrderCondOperator(this.carryOverOrderUnitParams.getOrderCondOperator());

                //�t�w�l��l�^�C�v
                l_orderUnitParams.setStopPriceType(this.carryOverOrderUnitParams.getStopPriceType());

                //�t�w�l��l
                if (this.carryOverOrderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setStopOrderPrice(this.carryOverOrderUnitParams.getStopOrderPrice());
                }
                
                //�iW�w�l�j�����w�l
                if (this.carryOverOrderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitPrice(this.carryOverOrderUnitParams.getWLimitPrice());
                }
                
                //���N�G�X�g�^�C�v
                if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitParams.getOrderConditionType()))
                {
                    l_orderUnitParams.setRequestType(this.carryOverOrderUnitParams.getRequestType());        
                }
                else
                {
                    l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);                                                            
                }
                
                //���񒍕��̒����`���l��
                l_orderUnitParams.setOrderChanel(this.carryOverOrderUnitParams.order_chanel);

                //�`�[No
                //"9"(WebBroker)�{���ʃR�[�h�̉��R��
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                
                SubAccount l_subAccount = l_finApp.getAccountManager().getSubAccount(this.carryOverOrderUnitParams.getAccountId(), this.carryOverOrderUnitParams.getSubAccountId());
                
                WEB3HostReqOrderNumberManageService l_numberService =
                    (WEB3HostReqOrderNumberManageService) Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
                                
                String l_strOrderRequestNumber =
                    l_numberService.getNewNumber(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_subAccount.getMainAccount().getBranch().getBranchCode(),
                        ProductTypeEnum.IFO);
                        
                int l_intNumberOfChars = l_strOrderRequestNumber.length();
                               
                String l_strOrderRequestNumberLastThreeDigits =
                    l_strOrderRequestNumber.substring(l_intNumberOfChars - 3);
                    
                l_orderUnitParams.setVoucherNo(WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER + l_strOrderRequestNumberLastThreeDigits);
                

                //���񒍕��̎萔��No
                l_orderUnitParams.setCommTblNo(this.commissionFee.getCommissionNo());

                //���񒍕��̎萔��No�}��
                l_orderUnitParams.setCommTblSubNo(this.commissionFee.getCommissionRevNo());

                //���҃R�[�h�iSONAR�j
                l_orderUnitParams.setSonarTraderCode(this.carryOverOrderUnitParams.getSonarTraderCode());

                //�����P��
                l_orderUnitParams.setPrice(
                    this.estimatedDeliveryAmountCalculationResult.getCalcUnitPrice());

                //���ʃR�[�h

                //���ʃe�[�u�����A�،���ЃR�[�h�E���X�R�[�h�E�����^�C�v�ɂČ������A
                //�Y���s�̎��ʃR�[�h�iSEQ NO�j+1�̒l�B
                //�i* �Y���s�̎��ʃR�[�h���C���N�������g�����l�Ŏ��ʃR�[�h�e�[�u�����X�V����B���ʃR�[�h�e�[�u���ɍs�����݂��Ȃ��ꍇ��Insert����j
                l_orderUnitParams.setOrderRequestNumber(l_strOrderRequestNumber);

                //�T�Z��n���
                l_orderUnitParams.setEstimatedPrice(
                    this.estimatedDeliveryAmountCalculationResult.getEstimateDeliveryAmount());

                //����R�[�h�iSONAR�j
                l_orderUnitParams.setSonarTradedCode(this.carryOverOrderUnitParams.sonar_traded_code);

                //�s��R�[�h�iSONAR�j
                long l_lngMarketId = l_orderUnitParams.getMarketId();
                
                //NotFoundException
                String l_strMarketCode =
                    l_finApp.getFinObjectManager().getMarket(l_lngMarketId).getMarketCode();
                l_orderUnitParams.setSonarMarketCode(l_strMarketCode);

                //�萔�����i�R�[�h                 
                l_orderUnitParams.setCommProductCode(this.commissionFee.getCommissionProductCode());                

                //���������E����敪
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

                //�����o�H�敪
                l_orderUnitParams.setOrderRootDiv(this.carryOverOrderUnitParams.getOrderRootDiv());

                //�s�ꂩ��m�F�ς݂̒����P��
                l_orderUnitParams.setConfirmedOrderPrice(null);

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_orderUnitParams.setConfirmedEstimatedPrice(null);

                //�s�ꂩ��m�F�ς݂̎��s����
                l_orderUnitParams.setConfirmedExecConditionType(null);

                //���Ϗ���         
                l_orderUnitParams.setClosingOrder(this.carryOverOrderUnitParams.getClosingOrder());

                //�����G���[���R�R�[�h
                l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                // �J�z�������P��.���񒍕��̒����P�ʂh�c = 0 �܂��� null�̏ꍇ�A�J�z�������P��.�����P�ʂh�c
                // ����ȊO�̏ꍇ�A�J�z�������P��.���񒍕��̒����P�ʂh�c
                if (carryOverOrderUnitParams.getFirstOrderUnitIdIsNull() 
                    || carryOverOrderUnitParams.getFirstOrderUnitId() == 0)
                {
                    l_orderUnitParams.setFirstOrderUnitId(carryOverOrderUnitParams.getOrderUnitId());
                }
                else
                {
                    l_orderUnitParams.setFirstOrderUnitId(carryOverOrderUnitParams.getFirstOrderUnitId());
                }
                
                // �󒍓���
                // �J�z�������P�ʂ̓�����
                l_orderUnitParams.setReceivedDateTime(carryOverOrderUnitParams.getReceivedDateTime());
                
                //����������
                l_orderUnitParams.setOrgOrderConditionType(carryOverOrderUnitParams.getOrgOrderConditionType());
                
                //�������������Z�q
                l_orderUnitParams.setOrgOrderCondOperator(carryOverOrderUnitParams.getOrgOrderCondOperator());
                
                //���t�w�l��l�^�C�v
                l_orderUnitParams.setOrgStopPriceType(carryOverOrderUnitParams.getOrgStopPriceType());
                
                //���t�w�l��l
                if (carryOverOrderUnitParams.getOrgStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(carryOverOrderUnitParams.getOrgStopOrderPrice());
                }
                
                //���iW�w�l�j�����w�l
                if (carryOverOrderUnitParams.getOrgWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(carryOverOrderUnitParams.getOrgWLimitPrice());
                }
                
                //���iW�w�l�j���s����
                l_orderUnitParams.setOrgWLimitExecCondType(carryOverOrderUnitParams.getOrgWLimitExecCondType());
                
                //�iW�w�l�j���s����
                l_orderUnitParams.setWLimitExecCondType(carryOverOrderUnitParams.getWLimitExecCondType());
                
                //�iW�w�l�j�֑ؑO�w�l
                if (carryOverOrderUnitParams.getWLimitBeforeLimitPriceIsNull())
                {
                    l_orderUnitParams.setWLimitBeforeLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitBeforeLimitPrice(carryOverOrderUnitParams.getWLimitBeforeLimitPrice());
                }
                
                //�iW�w�l�j�֑ؑO���s����
                l_orderUnitParams.setWLimitBeforeExecCondType(carryOverOrderUnitParams.getWLimitBeforeExecCondType());

                //�����x���t���O
                l_orderUnitParams.setSubmitOrderDelayFlag(carryOverOrderUnitParams.getSubmitOrderDelayFlag());
                
                //�����o�H�敪
                WEB3IfoFrontOrderService l_ifoFrontOrderService =
                    (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    l_ifoFrontOrderService.getSubmitOrderRouteDiv(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_strMarketCode));

                //�[��O�J�z�Ώۃt���O
                l_orderUnitParams.setEveningSessionCarryoverFlag(
                    carryOverOrderUnitParams.getEveningSessionCarryoverFlag());

                //����敪
                l_orderUnitParams.setSessionType(this.sessionType);

                //���v��敪
                if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(carryOverOrderUnitParams.getOrderCateg())
                    || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(carryOverOrderUnitParams.getOrderCateg()))
                {
                    l_orderUnitParams.setDayTradeType(commissionFee.getDayTradeType());
                }
                else if (OrderCategEnum.IDX_FUTURES_OPEN.equals(carryOverOrderUnitParams.getOrderCateg())
                   || OrderCategEnum.IDX_OPTIONS_OPEN.equals(carryOverOrderUnitParams.getOrderCateg()))
                {
                    l_orderUnitParams.setDayTradeType(null);
                }

                //���������敪
                l_orderUnitParams.setExpirationDateType(
                    this.carryOverOrderUnitParams.getExpirationDateType());
            }

            log.debug(STR_METHOD_NAME + ": END");
            return l_orderUnitParams;
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "�f�[�^�s�����G���[�B",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex));

            log.debug(STR_METHOD_NAME + ": END");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName()+  "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }        
    }

    /**
     * (set�萔��)<BR>
     * �萔���I�u�W�F�N�g���Z�b�g����B<BR>
     * @@param l_commissionFee - �萔���I�u�W�F�N�g
     * @@roseuid 40A0C2420138
     */
    public void setCommissionFee(WEB3GentradeCommission l_commissionFee)
    {
        this.commissionFee = l_commissionFee;
    }

    /**
     * �T�Z��n����v�Z���ʂ��Z�b�g����B
     * @@param l_estimateDeliveryAmountCalcResult - �T�Z��n����v�Z���ʃI�u�W�F�N�g
     * @@roseuid 40A0C2420167
     */
    public void setEstimateDeliveryAmountCalcResult(WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
    {
        this.estimatedDeliveryAmountCalculationResult = l_estimateDeliveryAmountCalcResult;
    }

    /**
     * (set����敪)<BR>
     * @@param l_sessionType - ����敪
     */
    public void setSessionType(String l_strSessionType)
    {
        this.sessionType = l_strSessionType;
    }
}
@
