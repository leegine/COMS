head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�������������������̓T�[�r�XImpl (WEB3ToSuccEquityChangeOrderInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A�C��(���u) �V�K�쐬
Revesion History : 2007/01/20 �юu��(���u) �d�l�ύX���f��No.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeInputResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�������������������̓T�[�r�XImpl )<BR>
 * �i�A���j�������������������̓T�[�r�X�����N���X�B<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderInputServiceImpl extends WEB3EquityChangeOrderInputServiceImpl 
    implements WEB3ToSuccEquityChangeOrderInputService 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderInputServiceImpl.class);
    
    /**
     * @@roseuid 436ACF7303B9
     */
    public WEB3ToSuccEquityChangeOrderInputServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�������������������̓T�[�r�X���������{����B<BR>
     * <BR>
     * get�������͉��()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4337C15B035E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccEquityChangeInputRequest)
        {
            //get�������͉��()
            l_response = 
                this.getChangeInputScreen((WEB3SuccEquityChangeInputRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������͉��)<BR>
     * �i�A���j�������������������͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�A���j�������������������́jget�������͉�ʁv�Q�ƁB<BR>
     * @@param  l_request - ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@return WEB3SuccEquityChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4337C1B600AE
     */
    protected WEB3SuccEquityChangeInputResponse getChangeInputScreen(
        WEB3SuccEquityChangeInputRequest  l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getChangeInputScreen(WEB3SuccEquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 validate( )
        l_request.validate();  //WEB3BusinessLayerException

        //1.2 get�����\�񒍕��P��(����ID : long)
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_toSuccOrderUnit = null;
        try
        {
            l_toSuccOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage =
                "�e�[�u���ɊY������f�[�^������܂���B�u����ID:" + l_lngOrderId + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow =
            (RsvEqOrderUnitRow)l_toSuccOrderUnit.getDataSourceObject();
        
        //1.3 reset�s��R�[�h(�s��R�[�h : String)
        Market l_market = null;
        String l_strMarketCode = null;
        try
        {
            l_market = l_toSuccOrderUnit.getMarket();
            if (l_market != null)
            {
                l_strMarketCode = l_market.getMarketCode();
            }
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage =
                "�e�[�u���ɊY������f�[�^������܂���B�u�s��ID:" + l_rsvEqOrderUnitRow.getMarketId() + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }        

        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        //1.4 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount(); //WEB3BaseException
        
        //1.5 get�e�����̒����P��( )
        EqTypeOrderUnit l_parentOrderUnit = l_toSuccOrderUnit.getParentOrderUnit();
        
        //1.6 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_rsvEqOrderUnitRow.getReserveOrderTradingType(),
            l_parentOrderUnit); //WEB3BaseException
            
        //1.7 get�������͉��(���N�G�X�g�f�[�^ : �������������������̓��N�G�X�g)
        WEB3SuccEquityChangeInputResponse l_response = 
            (WEB3SuccEquityChangeInputResponse)super.getChangeInputScreen(l_request);//WEB3BaseException
        
        //1.8 create�A���������ʏ��( )
        WEB3SuccCommonInfo l_succCommonInfo = l_toSuccOrderUnit.createSuccCommonInfo();
        
        //1.9 create�P�������l���( )
        WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo = 
            l_toSuccOrderUnit.createSuccPriceAdjustmentValueInfo();
        
        //���������敪�ꗗ�A�y�сi�o����܂Œ����J�n���A�ŏI���A�j���ꗗ�j�擾
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond =
            new WEB3GentradeHandlingOrderCond(l_subAccount.getInstitution().getInstitutionCode(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //�i(*) �v���p�e�B�Z�b�g
        //���u�i�A���j�������������������̓��X�|���X�v�ɂ̂ݑ��݂���v���p�e�B
        l_response.succCommonInfo = l_succCommonInfo;
        l_response.priceAdjustmentValueInfo = l_succPriceAdjustmentValueInfo;
        l_response.expirationDateTypeList =
            l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();
        
        //���قȂ�l���Z�b�g����v���p�e�B�i�ăZ�b�g�j
        l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
        //�v�w�l�p���s�����ꗗ�F�@@null���Z�b�g�B
        l_response.wlimitExecCondList = null;

        if (l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling() &&
            l_response.expirationStartDate == null)
        {
            l_response.expirationStartDate = l_gentradeHandingOrderCond.getOrderUntilDeadLineStartDay();
            l_response.expirationEndDate = l_gentradeHandingOrderCond.getOrderUntilDeadLineEndDay();
            l_response.holidayList = l_gentradeHandingOrderCond.getExpirationDateHoliday();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����Ώے����P��)<BR>
     * �����Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����\�񒍕��P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(���N�G�X�g�f�[�^.ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@���������P�ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�A�������}�l�[�W��Impl.create���������P��(�P�j�̖߂�l)��<BR>
     * �@@�R�[�����A�߂�l��ԋp����B<BR>
     * @@param  l_request - ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 433A1E150349
     */
    protected EqTypeOrderUnit getChangeOrderUnit(WEB3EquityChangeInputRequest  l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getChangeOrderUnit(WEB3EquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        // �P�j�@@�����\�񒍕��P�ʂ��擾����B
        // �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(���N�G�X�g�f�[�^.ID)��
        // �@@�R�[������B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        long l_lngOrderId = Long.parseLong(l_request.id);
        try
        {
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B�u����ID:" + l_lngOrderId + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        // �Q�j�@@���������P�ʃI�u�W�F�N�g�𐶐�����B
        // �@@�A�������}�l�[�W��Impl.create���������P��(�P�j�̖߂�l)��
        // �@@�R�[�����A�߂�l��ԋp����B
        EqTypeOrderUnit l_eqTypeOrderUnit = l_toOrderManager.createEqtypeOrderUnit(l_orderUnit);

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderUnit;
    }
    
    /**
     * (get���t�\�z)<BR>
     * ���t�\�z���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̊����\�񒍕��P��.getSide()==SideEnum.SELL�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@����]�̓T�[�r�X.get�������t�\�z�`�A�������`(�����̕⏕����, null, <BR>
     * �@@�@@�@@�����O�̊T�Z��n���(*1))��delegate����B<BR>
     * <BR>
     * (*1)�����O�̊T�Z��n���<BR>
     * �����̊����\�񒍕��P��.�T�Z��n������Z�b�g�B<BR>
     * @@param l_orderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@return Double
     * @@throws WEB3BaseException
     * @@roseuid 433A1E1503A7
     */
    protected Double getEquityTradingPower(EqTypeOrderUnit l_orderUnit, WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getEquityTradingPower(EqTypeOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����\�񒍕��P�ʂ����w��(null)�ł��B");
        }
        
        // �P�j�@@�����̊����\�񒍕��P��.getSide()==SideEnum.SELL�̏ꍇ�́Anull��ԋp����B
        // �@@�@@�@@�ȊO�A�ȉ��̏������s���B
        if (SideEnum.SELL.equals(l_orderUnit.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        

        // �Q�j�@@����]�̓T�[�r�X.get�������t�\�z�`�A�������`(�����̕⏕����, null, 
        // �@@�@@�@@�����O�̊T�Z��n���(*1))��delegate����B
        WEB3TPTradingPowerService l_trdingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        Double l_estimatedPrice = null;
        if (l_eqOrderUnitRow != null && !l_eqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_estimatedPrice = new Double(l_eqOrderUnitRow.getEstimatedPrice());
        }
        double l_dblEquityTradingPower = l_trdingPowerService.getSuccEquityTradingPower(
            l_subAccount,
            null,
            l_estimatedPrice);

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblEquityTradingPower);
    }
    
    /**
     * (get�o����܂Œ���from���t)<BR>
     * �����L�������擾�Ɏg�p����A�o����܂Œ���from���t��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����̊����\�񒍕��P��.��������ԋp����B<BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 433A1E1503AC
     */
    protected Date getCarriedOrderFromDate(EqTypeOrderUnit l_rsvEqOrderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCarriedOrderFromDate(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvEqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����\�񒍕��P�ʂ����w��(null)�ł��B");
        }
        Date l_datBizDate = null;
        if (l_rsvEqOrderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl)
        {
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
                (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            l_datBizDate = WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd");            
        }
        else
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            l_datBizDate = WEB3DateUtility.getDate(l_eqtypeOrderUnitRow.getBizDate(), "yyyyMMdd");
        }

        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }
    
    /**
     * (validate���������\)<BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����\�񒍕��P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(����.�����\�񒍕��P��.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l.validate�����\���()���R�[������B<BR>
     * @@param l_orderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A1E1503B6
     */
    protected void validateOrderForChangeability(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����\�񒍕��P�ʂ����w��(null)�ł��B");
        }

        // �P�j�@@�����\�񒍕��P�ʂ��擾����B
        // �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(����.�����\�񒍕��P��.����ID)��
        // �@@�R�[������B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = l_orderUnit.getOrderId();
        try
        {
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B�u����ID:" + l_lngOrderId + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }

        // �Q�j�@@�P�j�̖߂�l.validate�����\���()���R�[������B
        l_succEqTypeOrderUnitImpl.validateOrderForChangeability();    

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P����ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����\�񒍕��P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(����.�����\�񒍕��P��.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l.get�T�Z�뉿�P��()��delegate����B<BR>
     * @@param l_orderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BaseException 
     * @@roseuid 433A1E1503B8
     */
    protected String getEstimatedBookPrice(EqTypeOrderUnit l_orderUnit, WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getEstimatedBookPrice(EqTypeOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����\�񒍕��P�ʂ����w��(null)�ł��B");
        }

        // �P�j�@@�����\�񒍕��P�ʂ��擾����B
        // �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(����.�����\�񒍕��P��.����ID)��
        // �@@�R�[������B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = l_orderUnit.getOrderId();
        try
        {
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�e�[�u���ɊY������f�[�^������܂���B�u����ID:" + l_lngOrderId + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }

        // �Q�j�@@�P�j�̖߂�l.get�T�Z�뉿�P��()��delegate����B
        String l_strEstimatedBookPrice = l_succEqTypeOrderUnitImpl.getEstimatedBookPrice();

        log.exiting(STR_METHOD_NAME);
        return l_strEstimatedBookPrice;
    }
}
@
