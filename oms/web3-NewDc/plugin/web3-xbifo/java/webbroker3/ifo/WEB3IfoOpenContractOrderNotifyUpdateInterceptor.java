head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOpenContractOrderNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�V�K�������ʒm�X�V�C���^�Z�v�^�N���X(WEB3IfoOpenContractOrderNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ������ (���u) �V�K�쐬
Revesion History : 2007/01/25 �����F (���u) DB�X�V�d�lNo.133�A139
Revesion History : 2007/06/11 �Ј��� (���u) �d�l�ύX���f��No.665 DB�X�V�d�lNo.170
Revesion History : 2007/06/21 �Ј��� (���u) DB�X�V�d�lNo.180
Revesion History : 2007/07/02 �Ј��� (���u) �d�l�ύX���f��No.770 DB�X�V�d�lNo.192
Revesion History : 2008/03/17 �����F (���u) DB�X�V�d�lNo.197
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.ifo.define.WEB3IfoVoucherNoFirstNumDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�V�K�������ʒm�X�V�C���^�Z�v�^)<BR>
 * �敨OP�V�K�������ʒm�X�V�C���^�Z�v�^�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoOpenContractOrderNotifyUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3IfoOpenContractOrderNotifyUpdateInterceptor.class);

    /**
     * (�敨OP�V�K���������e)<BR>
     */
    private WEB3IfoOpenContractOrderSpec ifoOpenContractOrderSpec;

    /**
     * ���ʃR�[�h
     */
    private String orderRequestNumber;

    /**
     * �󒍓���
     */
    private Date receivedDateTime;

    /**
     * ��n��
     */
    private Date deliveryDate;

    /**
     * ������
     */
    private Date bizDate;

    /**
     * ����敪
     */
    private String sessionType;

    /**
     * @@roseuid 41AD74950119
     */
    public WEB3IfoOpenContractOrderNotifyUpdateInterceptor()
    {

    }

    /**
     * (�敨OP�V�K�������ʒm�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A<BR>
     * �����̐敨OP�V�K���������e���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_ifoOpenContractOrderSpec - �敨OP�V�K���������e
     * @@roseuid 416513CB025E
     */
    public WEB3IfoOpenContractOrderNotifyUpdateInterceptor(WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec)
    {
        ifoOpenContractOrderSpec = l_ifoOpenContractOrderSpec;
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
     * �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ<BR>
     * �uOP�����ʒm_�����P�ʃe�[�u���d�l.xls�v��<BR>
     * �uOP�����ʒm[�V�K��]�����P�ʃe�[�u��_DB�X�V�d�l�v�V�[�g<BR>
     * <BR>
     * �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ<BR>
     * �u�敨�����ʒm_�����P�ʃe�[�u���d�l.xls�v��<BR>
     * �u�敨�����ʒm[�V�K��]�����P�ʃe�[�u��_DB�X�V�d�l�v�V�[�g<BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams
     * @@roseuid 416513CB023E
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        IfoOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate";

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        long l_productId = l_orderUnitParams.getProductId();
        WEB3IfoProductImpl l_productImpl = null;
        MainAccount l_mainAccount = null;
        Market l_market = null;
        long l_lngMarketId = l_orderUnitParams.getMarketId();
        try
        {
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);

            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_orderUnitParams.getAccountId());

            l_productImpl = (WEB3IfoProductImpl) l_productManagerImpl.getProduct(l_productId);
        }
        catch (NotFoundException l_nfe)
        {
            log.debug("Failed to get the market object.");
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
        IfoProductRow l_productRow = (IfoProductRow) l_productImpl.getDataSourceObject();

        //�敨OP�����e�[�u��.�敨�^�I�v�V�����敪
        l_orderUnitParams.setFutureOptionDiv(l_productRow.getFutureOptionDiv());
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
            //�i* null�Œ�j
            l_orderUnitParams.setOrderCondOperator(null);
            //�i* null�Œ�j
            l_orderUnitParams.setStopPriceType(null);
            //�i* null�Œ�j
            l_orderUnitParams.setStopOrderPrice(null);
            //�i* null�Œ�j
            l_orderUnitParams.setWLimitPrice(null);
        }
        else
        {
            //�C���^�Z�v�^.��������
            l_orderUnitParams.setOrderConditionType(this.orderCond);
            //�C���^�Z�v�^.�����������Z�q
            l_orderUnitParams.setOrderCondOperator(this.orderCondOperator);
            //�C���^�Z�v�^.�t�w�l��l�^�C�v
            l_orderUnitParams.setStopPriceType(this.stopOrderBasePriceType);
            //�C���^�Z�v�^.�t�w�l��l
            l_orderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);
            //�C���^�Z�v�^.�iW�w�l�j�����w�l
            l_orderUnitParams.setWLimitPrice(this.wLimitPriceChange);
        }
        
        //�C���^�Z�v�^.��n��
        l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //�C���^�Z�v�^.�������̓��t�����܂�(yyyymmdd)��ݒ�
        //�i* ���ۂɔ�������c�Ɠ��BOP�����ʒm�L���[�e�[�u��.���������Ɠ����l�B�j
        l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));
        
        l_orderUnitParams.setOrderChanel(this.commRevMstId.getOrderChannel());
        l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);
        final String l_strBaseNumber = WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER;
        //�`�[No��ݒ肷��
        int l_intRequestNumberLength = this.getOrderRequestNumber().length() - 3;
        String l_strVoucherNo = l_strBaseNumber + this.getOrderRequestNumber().substring(l_intRequestNumberLength);
        l_orderUnitParams.setVoucherNo(l_strVoucherNo);

        //�C���^�Z�v�^.�萔��.�萔��No
        l_orderUnitParams.setCommTblNo(this.commRevMstId.getCommissionNo());

        //�C���^�Z�v�^.�萔��.�萔��No�}��
        l_orderUnitParams.setCommTblSubNo(this.commRevMstId.getCommissionRevNo());

        //�ڋq.���҃R�[�h�iSONAR�j
        l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
        //�C���^�Z�v�^.�T�Z��n����v�Z����.�v�Z�P��
        l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

        //�C���^�Z�v�^.���ʃR�[�h
        l_orderUnitParams.setOrderRequestNumber(this.getOrderRequestNumber());

        //�C���^�Z�v�^.�T�Z��n����v�Z����.�T�Z��n���
        l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());

        //51�F�V�K��
        l_orderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);

        if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitParams.getFutureOptionDiv()))
        {
            //51�F�����w��OP
            l_orderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
        }
        else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_orderUnitParams.getFutureOptionDiv()))
        {
            //50�F�敨
            l_orderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
        }
        //�s��R�[�h�iSONAR�j
        MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
        l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());

        //���������E����敪
        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

        //�����o�H�敪
        l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);

        //�s�ꂩ��m�F�ς݂̒����P��
        if (l_orderUnitParams.getPriceIsNull())
        {
            l_orderUnitParams.setConfirmedOrderPrice(null);
        }
        else
        {
            l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̊T�Z��n�����ݒ肷��
        if (l_orderUnitParams.getEstimatedPriceIsNull())
        {
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
        }
        else
        {
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̎��s����
        l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType()); 

        //���Ϗ��� 
        l_orderUnitParams.setClosingOrder(null);

        //�����G���[���R�R�[�h
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //���N�G�X�g�^�C�v 
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);

        //�J�z�������P�ʂh�c
        l_orderUnitParams.setFirstOrderUnitId(null);

        //�����o�H�敪
        l_orderUnitParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION);

        //�[��O�J�z�Ώۃt���O
        boolean l_blnEveningSessionCarryoverFlag = this.ifoOpenContractOrderSpec.getEveningSessionCarryoverFlag();
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

        //���������敪
        l_orderUnitParams.setExpirationDateType(this.ifoOpenContractOrderSpec.getExpirationDateType());

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }

    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B<BR>
     * @@param l_strRequestCode - ���ʃR�[�h
     * @@roseuid 41651822028D
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber)
    {
        orderRequestNumber = l_strOrderRequestNumber;
    }

    /**
     * (set�󒍓���)<BR>
     * �󒍓������Z�b�g����B<BR>
     * @@param l_datReceivedDateTime - �󒍓���
     * @@roseuid 4165184603D5
     */
    public void setReceivedDateTime(Date l_datReceivedDateTime)
    {
        receivedDateTime = l_datReceivedDateTime;
    }

    /**
     * (set��n��)<BR>
     * �󒍓����Z�b�g����B<BR>
     * @@param l_datDeliveryDate - �󒍓�
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (set������)<BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datBizDate - ������
     * @@roseuid 4165184603D5
     */
    public void setBizDate(Date l_datBizDate)
    {
        bizDate = l_datBizDate;
    }

    /**
     * (get���ʃR�[�h)<BR>
     * ���ʃR�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 416519460387
     */
    public String getOrderRequestNumber()
    {
        return this.orderRequestNumber;
    }

    /**
     * (get�󒍓���)<BR>
     * �󒍓������擾����B<BR>
     * @@return Date
     * @@roseuid 4165195E028D
     */
    public Date getReceivedDateTime()
    {
        return this.receivedDateTime;
    }

    /**
     * (get��n��)<BR>
     * �󒍓����擾����B<BR>
     * @@return Date
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * @@return Date
     * @@roseuid 4165195E028D
     */
    public Date getBizDate()
    {
        return this.bizDate;
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
     * ����敪���Z�b�g����<BR>
     * @@param l_strSessionType - (����敪)<BR>
     * ����敪
     */
    public void setSessionType(String l_strSessionType)
    {
        this.sessionType = l_strSessionType;
    }
}
@
