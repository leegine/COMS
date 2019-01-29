head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoSettleContractOrderNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�ԍϒ����ʒm�X�V�C���^�Z�v�^�N���X(WEB3IfoSettleContractOrderNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ������ (���u) �V�K�쐬
Revesion History : 2007/01/25 �����F (���u) DB�X�V�d�lNo.133�A139
Revesion History : 2007/06/11 �Ј��� (���u) �d�l�ύX���f��No.665 DB�X�V�d�lNo.170
Revesion History : 2007/06/21 �Ј��� (���u) DB�X�V�d�lNo.180
Revesion History : 2007/07/02 �Ј��� (���u) �d�l�ύX���f��No.770 DB�X�V�d�lNo.192
Revesion History : 2008/04/25 �����F (���u) DB�X�V�d�lNo.197
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
 * (�敨OP�ԍϒ����ʒm�X�V�C���^�Z�v�^)<BR>
 * �敨OP�ԍϒ����ʒm�X�V�C���^�Z�v�^�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoSettleContractOrderNotifyUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
   
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3IfoSettleContractOrderNotifyUpdateInterceptor.class);
    
   /**
    * (�敨OP�ԍϒ������e)<BR>
    */
   private WEB3IfoSettleContractOrderSpec ifoSettleContractOrderSpec;
   
   /**
    * (���Ϗ���)<BR>
    * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
    * �ꊇ�ԍώ��̏ꍇ�ݒ�<BR>
    */
   private String closingOrder;
   
   /**
    * ���ʃR�[�h<BR>
    */
   private String orderRequestNumber;
   
   /**
    * �󒍓���<BR>
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
    * @@roseuid 41AD749600CB
    */
   public WEB3IfoSettleContractOrderNotifyUpdateInterceptor() 
   {
        
   }
   
   /**
    * (�敨OP�ԍϒ����ʒm�X�V�C���^�Z�v�^)<BR>
    * �R���X�g���N�^<BR>
    * <BR>
    * �C���X�^���X�𐶐����A<BR>
    * �����̐敨OP�ԍϒ������e���v���p�e�B�ɃZ�b�g����B<BR>
    * @@param l_ifoSettleContractOrderSpec - �敨OP�ԍϒ������e
    * @@roseuid 4165191202EA
    */
   public WEB3IfoSettleContractOrderNotifyUpdateInterceptor(WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec) 
   {
       ifoSettleContractOrderSpec = l_ifoSettleContractOrderSpec;
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
    * �uOP�����ʒm[�ԍ�]�����P�ʃe�[�u��_DB�X�V�d�l�v�V�[�g<BR>
    * <BR>
    * �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ<BR>
    * �u�敨�����ʒm_�����P�ʃe�[�u���d�l.xls�v��<BR>
    * �u�敨�����ʒm[�ԍ�]�����P�ʃe�[�u��_DB�X�V�d�l�v�V�[�g<BR>
    * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
    * INSERT�܂��́AUPDATE�B<BR>
    * <BR>
    * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
    * 
    * @@param l_orderManagerPersistenceContext - (����)<BR>
    * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
    * @@param l_ifoOrderUnitParams - (�����P��Params)<BR>
    * �����P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
    * @@return com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams
    * @@roseuid 4165191202CB
    */
   public IfoOrderUnitParams mutate(
    OrderManagerPersistenceType l_orderManagerPersistenceType, 
    OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
    IfoOrderUnitParams l_ifoOrderUnitParams)
   {
       final String STR_METHOD_NAME = "mutate";

       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
       WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
       long l_productId = l_ifoOrderUnitParams.getProductId();
       WEB3IfoProductImpl l_productImpl = null;
       MainAccount l_mainAccount = null;
       Market l_market = null;
       long l_lngMarketId = l_ifoOrderUnitParams.getMarketId();
       try
       {
           l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);

           l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_ifoOrderUnitParams.getAccountId());

           l_productImpl = (WEB3IfoProductImpl) l_productManagerImpl.getProduct(l_productId);
       }
       catch (NotFoundException l_nfe)
       {
           log.error(STR_METHOD_NAME, l_nfe);
           throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
       IfoProductRow l_productRow = (IfoProductRow) l_productImpl.getDataSourceObject();

       //�敨OP�����e�[�u��.�敨�^�I�v�V�����敪
       l_ifoOrderUnitParams.setFutureOptionDiv(l_productRow.getFutureOptionDiv());
       if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
       {
           l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
           //�i* null�Œ�j
           l_ifoOrderUnitParams.setOrderCondOperator(null);
           //�i* null�Œ�j
           l_ifoOrderUnitParams.setStopPriceType(null);
           //�i* null�Œ�j
           l_ifoOrderUnitParams.setStopOrderPrice(null);
           //�i* null�Œ�j
           l_ifoOrderUnitParams.setWLimitPrice(null);
       }
       else
       {
           //�C���^�Z�v�^.��������
           l_ifoOrderUnitParams.setOrderConditionType(this.orderCond);
           //�C���^�Z�v�^.�����������Z�q
           l_ifoOrderUnitParams.setOrderCondOperator(this.orderCondOperator);
           //�C���^�Z�v�^.�t�w�l��l�^�C�v
           l_ifoOrderUnitParams.setStopPriceType(this.stopOrderBasePriceType);
           //�C���^�Z�v�^.�t�w�l��l
           l_ifoOrderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);
           //�C���^�Z�v�^.�iW�w�l�j�����w�l
           l_ifoOrderUnitParams.setWLimitPrice(this.wLimitPriceChange);
       }

       //�C���^�Z�v�^.��n��
       l_ifoOrderUnitParams.setDeliveryDate(this.deliveryDate);
        
       //�C���^�Z�v�^.�������̓��t�����܂�(yyyymmdd)��ݒ�
       //�i* ���ۂɔ�������c�Ɠ��BOP�����ʒm�L���[�e�[�u��.���������Ɠ����l�B�j
       l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));

       l_ifoOrderUnitParams.setOrderChanel(this.commRevMstId.getOrderChannel());
       l_ifoOrderUnitParams.setReceivedDateTime(this.receivedDateTime);
       final String l_strBaseNumber = WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER;
       //�`�[No��ݒ肷��
       int l_intRequestNumberLength = this.getOrderRequestNumber().length() - 3;
       String l_strVoucherNo = l_strBaseNumber + this.getOrderRequestNumber().substring(l_intRequestNumberLength);
       l_ifoOrderUnitParams.setVoucherNo(l_strVoucherNo);

       //�C���^�Z�v�^.�萔��.�萔��No
       l_ifoOrderUnitParams.setCommTblNo(this.commRevMstId.getCommissionNo());

       //�C���^�Z�v�^.�萔��.�萔��No�}��
       l_ifoOrderUnitParams.setCommTblSubNo(this.commRevMstId.getCommissionRevNo());

       //�ڋq.���҃R�[�h�iSONAR�j
       l_ifoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
       //�C���^�Z�v�^.�T�Z��n����v�Z����.�v�Z�P��
       l_ifoOrderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

       //�C���^�Z�v�^.���ʃR�[�h
       l_ifoOrderUnitParams.setOrderRequestNumber(this.getOrderRequestNumber());

       //�C���^�Z�v�^.�T�Z��n����v�Z����.�T�Z��n���
       l_ifoOrderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());

       //51�F�V�K��
       l_ifoOrderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

       if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitParams.getFutureOptionDiv()))
       {
           //51�F�����w��OP
           l_ifoOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
       }
       else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitParams.getFutureOptionDiv()))
       {
           //50�F�敨
           l_ifoOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
       }
       //�s��R�[�h�iSONAR�j
       MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
       l_ifoOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());

       //���������E����敪
       l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

       //�����o�H�敪
       l_ifoOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);

       //�s�ꂩ��m�F�ς݂̒����P��
       if (l_ifoOrderUnitParams.getPriceIsNull())
       {
           l_ifoOrderUnitParams.setConfirmedOrderPrice(null);
       }
       else
       {
           l_ifoOrderUnitParams.setConfirmedOrderPrice(l_ifoOrderUnitParams.getPrice());
       }
       
       //�s�ꂩ��m�F�ς݂̊T�Z��n�����ݒ肷��
       if (l_ifoOrderUnitParams.getEstimatedPriceIsNull())
       {
           l_ifoOrderUnitParams.setConfirmedEstimatedPrice(null);
       }
       else
       {
           l_ifoOrderUnitParams.setConfirmedEstimatedPrice(l_ifoOrderUnitParams.getEstimatedPrice());
       }
       
       //�s�ꂩ��m�F�ς݂̎��s����
       l_ifoOrderUnitParams.setConfirmedExecConditionType(l_ifoOrderUnitParams.getExecutionConditionType()); 

       //���Ϗ��� 
       l_ifoOrderUnitParams.setClosingOrder(this.getClosingOrder());

       //�����G���[���R�R�[�h
       l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

       //���N�G�X�g�^�C�v 
       l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);

       //�J�z�������P�ʂh�c
       l_ifoOrderUnitParams.setFirstOrderUnitId(null);

        //�����o�H�敪
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION);
        
        //�[��O�J�z�Ώۃt���O
        boolean l_blnEveningSessionCarryoverFlag = this.ifoSettleContractOrderSpec.getEveningSessionCarryoverFlag();
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
        l_ifoOrderUnitParams.setDayTradeType(this.commRevMstId.getDayTradeType());

        //���������敪
        l_ifoOrderUnitParams.setExpirationDateType(this.ifoSettleContractOrderSpec.getExpirationDateType());

       log.exiting(STR_METHOD_NAME);
       return l_ifoOrderUnitParams;

   }
   
   /**
    * (set���Ϗ���)<BR>
    * ���Ϗ������Z�b�g����B<BR>
    * @@param l_strSettletSequence - ���Ϗ���<BR>
    * <BR>
    * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
    * �ꊇ�ԍώ��̏ꍇ�ݒ�<BR>
    * @@roseuid 416518F000E7
    */
   public void setClosingOrder(String l_strClosingOrder) 
   {
        this.closingOrder = l_strClosingOrder;
   }
   
   /**
    * (set���ʃR�[�h)<BR>
    * ���ʃR�[�h���Z�b�g����B<BR>
    * @@param l_strRequestCode - ���ʃR�[�h
    * @@roseuid 416519920125
    */
   public void setOrderRequestNumber(String l_strOrderRequestNumber) 
   {
        this.orderRequestNumber = l_strOrderRequestNumber;
   }
   
   /**
    * (set�󒍓���)<BR>
    * �󒍓������Z�b�g����B<BR>
    * @@param l_strReceivedDateTime - �󒍓���
    * @@roseuid 416519920135
    */
   public void setReceivedDateTime(Date l_strReceivedDateTime) 
   {
        this.receivedDateTime = l_strReceivedDateTime;
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
    * (get���Ϗ���)<BR>
    * ���Ϗ������擾����B<BR>
    * @@return String
    * @@roseuid 416518F000F6
    */
   public String getClosingOrder() 
   {
        return this.closingOrder;
   }
   
   /**
    * (get���ʃR�[�h)<BR>
    * ���ʃR�[�h���擾����B<BR>
    * @@return String
    * @@roseuid 416519920144
    */
   public String getOrderRequestNumber() 
   {
        return this.orderRequestNumber;
   }
   
   /**
    * (get�󒍓���)<BR>
    * �󒍓������擾����B<BR>
    * @@return java.util.Date
    * @@roseuid 416519920154
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
