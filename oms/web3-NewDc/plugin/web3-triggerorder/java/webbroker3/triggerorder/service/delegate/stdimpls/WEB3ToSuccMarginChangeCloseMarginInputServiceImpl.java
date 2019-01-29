head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������ԍϓ��̓T�[�r�XImpl(WEB3ToSuccMarginChangeCloseMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 杊��](���u) �V�K�쐬
Revesion History : 2007/01/20 �юu��(���u) �d�l�ύX���f��No.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCloseMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�i�A���j�M�p��������ԍϓ��̓T�[�r�XImpl)<BR>
 * �i�A���j�M�p��������ԍϓ��̓T�[�r�X�����N���X<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginInputServiceImpl 
    extends WEB3MarginChangeCloseMarginInputServiceImpl 
    implements WEB3ToSuccMarginChangeCloseMarginInputService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginInputServiceImpl.class);

    /**
     * @@roseuid 436ACF7D0222
     */
    public WEB3ToSuccMarginChangeCloseMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p��������ԍϓ��̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.get�����ԍϓ��͉��()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BBB0800A2
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
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginCloseChangeInputRequest)
        {
            l_response = this.getCloseMarginChangeInputScreen((WEB3SuccMarginCloseChangeInputRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
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
     * (get�����ԍϓ��͉��)<BR>
     * �M�p��������ԍς̓��͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p��������ԍϓ��̓T�[�r�X�jget�����ԍϓ��͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccMarginCloseChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BBB9402D5
     */
    protected WEB3SuccMarginCloseChangeInputResponse getCloseMarginChangeInputScreen(
        WEB3SuccMarginCloseChangeInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCloseMarginChangeInputScreen(WEB3SuccMarginCloseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();
        
        //get�����\�񒍕��P��(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnit = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_eqTypeOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����\�񒍕��P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //reset�s��R�[�h(�s��R�[�h : String)
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode = l_eqTypeOrderUnit.getMarket().getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�s��e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //get�e�����̒����P��( )
        EqTypeOrderUnit l_orderUnit = l_eqTypeOrderUnit.getParentOrderUnit();
        
        //get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //validate�A������(�⏕����, ProductTypeEnum, String, String, OrderUnit)
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_eqTypeOrderUnit.getDataSourceObject();
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_rsvEqOrderUnitRow.getReserveOrderTradingType(),
            l_orderUnit);

        //get�����ԍϓ��͉��(���N�G�X�g�f�[�^ : �M�p��������ԍϓ��̓��N�G�X�g)
        WEB3SuccMarginCloseChangeInputResponse l_response = 
            (WEB3SuccMarginCloseChangeInputResponse) super.getCloseMarginChangeInputScreen(l_request);

        //���������敪�ꗗ�A�y�сi�o����܂Œ����J�n���A�ŏI���A�j���ꗗ�j�擾
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond =
            new WEB3GentradeHandlingOrderCond(l_subAccount.getInstitution().getInstitutionCode(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //(*)�v���p�e�B�Z�b�g
        //���i�A���j�M�p��������ԍϓ��̓��X�|���X�ŗL�̃v���p�e�B
        l_response.succCommonInfo = l_eqTypeOrderUnit.createSuccCommonInfo();
        l_response.priceAdjustmentValueInfo = l_eqTypeOrderUnit.createSuccPriceAdjustmentValueInfo();
        l_response.expirationDateTypeList = l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();

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
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * <BR>
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 433BC53D03B0
     */
    protected EqTypeOrderUnit getChangeOrderUnit(WEB3MarginCloseMarginChangeInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getChangeOrderUnit(WEB3MarginCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����\�񒍕��P�ʂ��擾����B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnit = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_eqTypeOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@���������P�ʃI�u�W�F�N�g�𐶐�����B
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.createEqtypeOrderUnit(l_eqTypeOrderUnit);
        
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
     * @@roseuid 433BC53D03CF
     */
    protected void validateOrderForChangeability(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����\�񒍕��P�ʂ��擾����B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnit = null;
        try
        {
            long l_lngOrderId = l_orderUnit.getOrderId();
            l_eqTypeOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�P�j�̖߂�l.validate�����\���()���R�[������B
        l_eqTypeOrderUnit.validateOrderForChangeability();

        log.exiting(STR_METHOD_NAME);
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
     * @@roseuid 433BC53E0006
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
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        log.exiting(STR_METHOD_NAME);
        return WEB3DateUtility.getDate(l_eqOrderUnitRow.getBizDate(), "yyyyMMdd");
    }
    
    /**
     * (create��������ByOrder)<BR>
     * �����̒����P�ʂɊ֘A����M�p����������ׂ�<BR>
     * �ꗗ���쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����\�񒍕��P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��(�����P��.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�A�������}�l�[�W��.create��������ByOrder(�P�j�̖߂�l)��<BR>
     * �@@�R�[�����A�߂�l��ԋp����B<BR>
     * �@@��null���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�@@�u�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύρv�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_02289<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 433BC53E0025
     */
    protected WEB3MarginContractUnit[] createContractUnitByOrder(EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createContractUnitByOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����\�񒍕��P�ʂ��擾����B
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnit = null;
        try
        {
            long l_lngOrderId = l_orderUnit.getOrderId();
            l_eqTypeOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�A�������}�l�[�W��.create��������ByOrder(�P�j�̖߂�l)���R�[������B
        WEB3MarginContractUnit[] l_contractUnits = l_toOrderManager.createContractUnitByOrder(l_eqTypeOrderUnit);
        if (l_contractUnits == null)
        {
            log.debug("�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02289,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύςł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
}
@
