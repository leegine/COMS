head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������V�K�����̓T�[�r�XImpl(WEB3ToSuccMarginChangeOpenMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 ���@@��(���u) �V�K�쐬
Revesion History : 2007/01/20 �юu��(���u) �d�l�ύX���f��No.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeOpenMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p��������V�K�����̓T�[�r�XImpl)<BR>
 * �i�A���j�M�p��������V�K�����̓T�[�r�X�����N���X<BR>
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeOpenMarginInputServiceImpl extends 
    WEB3MarginChangeOpenMarginInputServiceImpl implements WEB3ToSuccMarginChangeOpenMarginInputService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeOpenMarginInputServiceImpl.class);
    
    /**
     * @@roseuid 436ACF7B0213
     */
    public WEB3ToSuccMarginChangeOpenMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p��������V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.get�����V�K�����͉��()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433B83F30219
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SuccMarginOpenChangeInputRequest)
        {
            l_response = this.getOpenMarginChangeInputScreen((WEB3SuccMarginOpenChangeInputRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����V�K�����͉��)<BR>
     * �i�A���j�M�p��������V�K���̓��͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p��������V�K�����̓T�[�r�X�j<BR>
     * get�����V�K�����͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccMarginOpenChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 433B845A0006
     */
    protected WEB3SuccMarginOpenChangeInputResponse getOpenMarginChangeInputScreen(
        WEB3SuccMarginOpenChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getOpenMarginChangeInputScreen(WEB3SuccMarginOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�����\�񒍕��P��(����ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try 
        {
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�����\�񒍕��P�ʃe�[�u���ɊY������f�[�^������܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.3 reset�s��R�[�h(�s��R�[�h : String)
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode = l_orderUnitImpl.getMarket().getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�s��e�[�u���ɊY������f�[�^������܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.4 get�e�����̒����P��( )
        EqTypeOrderUnit l_orderUnit = l_orderUnitImpl.getParentOrderUnit();
        
        //1.5 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //1.6 validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        RsvEqOrderUnitRow l_orderUnitRow = (RsvEqOrderUnitRow) l_orderUnitImpl.getDataSourceObject();
        
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_orderUnitRow.getReserveOrderTradingType(),
            l_orderUnit);
        
        //1.7 get�����V�K�����͉��(���N�G�X�g�f�[�^ : �M�p��������V�K�����̓��N�G�X�g)
        WEB3SuccMarginOpenChangeInputResponse l_response = (
            WEB3SuccMarginOpenChangeInputResponse) super.getOpenMarginChangeInputScreen(l_request);

        //���������敪�ꗗ�A�y�сi�o����܂Œ����J�n���A�ŏI���A�j���ꗗ�j�擾
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond =
            new WEB3GentradeHandlingOrderCond(l_subAccount.getInstitution().getInstitutionCode(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);
            
        //(*)�v���p�e�B�Z�b�g
        //���i�A���j�M�p��������ԍϓ��̓��X�|���X�ŗL�̃v���p�e�B
        l_response.succCommonInfo = l_orderUnitImpl.createSuccCommonInfo();
        l_response.priceAdjustmentValueInfo = l_orderUnitImpl.createSuccPriceAdjustmentValueInfo();
        l_response.expirationDateTypeList =
            l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();
        
        //���قȂ�l���Z�b�g����v���p�e�B�i�ăZ�b�g�j
        l_response.priceCondList = new String[] {WEB3PriceConditionDef.DEFAULT};
        l_response.execCondList = new String[] {WEB3ExecutionConditionDef.NO_CONDITION};
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
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * 
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 433BA7FD0035
     */
    protected EqTypeOrderUnit getChangeOrderUnit(
        WEB3MarginOpenMarginChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getChangeOrderUnit(WEB3MarginOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
     
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try 
        {
            //�P�j�@@�����\�񒍕��P�ʂ��擾����B
            // �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(���N�G�X�g�f�[�^.ID)��
            // �@@�R�[������B
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //2) ���������P�ʃI�u�W�F�N�g�𐶐�����B
        // �@@�A�������}�l�[�W��Impl.create���������P��(�P�j�̖߂�l)��
        // �@@�R�[�����A�߂�l��ԋp����B
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.createEqtypeOrderUnit(l_orderUnitImpl);

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }
    
    /**
     * (validate���������\)<BR>
     * �����Ώے������A�����\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����\�񒍕��P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(�����P��.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l.validate�����\���()���R�[������B<BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433BA7FD0054
     */
    protected void validateOrderForChangeability(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            //�P�j�@@�����\�񒍕��P�ʂ��擾����B
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_orderUnit.getOrderId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
         
        //�Q�j�P�j�̖߂�l.validate�����\���()���R�[������B
        l_orderUnitImpl.validateOrderForChangeability();

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�V�K���\�z)<BR>
     * �V�K���\�z���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����]�̓T�[�r�X.get�M�p�V�K���\�z�`�A�������`<BR>
     * (�⏕����, null)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 433BA7FD0073
     */
    protected double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //����]�̓T�[�r�X.get�M�p�V�K���\�z�`�A�������`
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        
        double l_dblMarginTradingPower = l_tradingPowerService.getSuccMarginTradingPower(l_subAccount, null);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblMarginTradingPower;
    }
    
    /**
     * (get�o����܂Œ���from���t)<BR>
     * �����L�������擾�Ɏg�p����A�o����܂Œ���from���t��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����̒����P��.��������ԋp����B<BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 433BA7FD00A2
     */
    protected Date getCarriedOrderFromDate(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCarriedOrderFromDate(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderUnitRow l_firstOrderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        Date l_datBizDate = null;
        try
        {
            //�����L�������擾�Ɏg�p����A�o����܂Œ���from���t��ԋp����
            l_datBizDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(l_firstOrderUnitRow.getBizDate());
        }
        catch (ParseException l_pex)
        {
            log.error("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_pex.getMessage(),
                l_pex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }
}@
