head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���I���ʒmUnitServiceImpl(WEB3IfoExecuteEndNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/23 䈋� (���u) �V�K�쐬
              001: 2004/08/14 ���Ō� �Ή� �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)20040802 
              002 : 2006/07/28 �����F (���u) �d�l�ύX�@@���f��506
              003 : 2006/10/9 ������(���u)�@@�d�l�ύX�@@���f��No.556�@@
Revesion History : 2007/06/08 ��іQ (���u) ���f��No.694,704
Revesion History : 2007/11/19 �И��� �d�l�ύX���f��No.802
Revesion History : 2007/12/03 �И��� �d�l�ύX���f��No.822
Revesion History : 2008/03/14 �����F(���u) ���f��828 858
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoExecuteEndNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

/**
 * (�敨OP�o���I���ʒmUnitServiceImpl)<BR>
 * �敨OP�o���I���ʒm�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �����P�ʂP�����Ƃ̏o���I���ʒm���������{����B<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V��<BR>
 * TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 */
public class WEB3IfoExecuteEndNotifyUnitServiceImpl
    implements WEB3IfoExecuteEndNotifyUnitService
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoExecuteEndNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40C0753002BF
     */
    public WEB3IfoExecuteEndNotifyUnitServiceImpl()
    {
    }

    /**
     * (notify�o���I��)<BR>
     * �������̏o���I�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�o���I���ʒm�jnotify�o���I���v�Q�ƁB<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strOrderExecutionEndType - (�o���I���敪)<BR>
     * �o���I���敪<BR>
     * @@roseuid 408C94750364
     */
    public void notifyExecuteEnd(OrderUnit l_orderUnit, String l_strOrderExecutionEndType)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "notifyExecuteEnd(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error(STR_METHOD_NAME);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();

        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        IfoOrderUnitRow l_unitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3GentradeSubAccount l_gentradeSubAccount = null;
        try
        {
            l_gentradeSubAccount = new WEB3GentradeSubAccount(l_unitRow.getAccountId(),l_unitRow.getSubAccountId());
        }
        catch (DataFindException l_dataFindException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataFindException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataFindException.getMessage(),
                l_dataFindException);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }

        //���菈���̏������ɁA�O���v���Z�X����̃f�[�^�A�N�Z�X��h�~���邽�߂Ɍ��������b�N����B
        try
        {
            l_accountManager.lockAccount(
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode());
        }
        catch (WEB3BaseException l_wbe)
        {
            if (l_wbe.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80076))
            {
                log.error("�������b�N���������܂����B�������b�N�����ڋq ==>> " +
                            "�،���ЁF[ "+ l_gentradeSubAccount.getInstitution().getInstitutionCode() +
                            " ] ���X�F[ " + l_gentradeSubAccount.getWeb3GenBranch().getBranchCode() + " ]" +
                            " ] �ڋq�F[ " + l_gentradeSubAccount.getMainAccount().getAccountCode() + " ]");

            }

            throw l_wbe;
        }

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = null;
        l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        //getOrderUnit
        OrderUnit l_reOrderUnit = null;
        try
        {
             l_reOrderUnit = l_optionOrderManagerImpl.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3IfoExecuteEndNotifyUpdateInterceptor l_ifoExecuteEndUpdateIntercptor = null;
        l_ifoExecuteEndUpdateIntercptor = new WEB3IfoExecuteEndNotifyUpdateInterceptor();
        l_optionOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(
        l_ifoExecuteEndUpdateIntercptor);
        // 2.lock����(String, String, String)(�g���A�J�E���g�}�l�[�W��::lock����)
        long l_lngBranchId;
        long l_lngAccountId;
        //long l_orderUnitId;
        l_lngBranchId = l_orderUnit.getBranchId();
        l_lngAccountId = l_orderUnit.getAccountId();
        log.debug("BranchId =" + l_lngBranchId);
        log.debug("AccountId = " + l_lngAccountId);

        try
        {
            WEB3IfoExecutedMailSendService l_web3IfoExeMailSendService =
                 (WEB3IfoExecutedMailSendService)Services.getService(WEB3IfoExecutedMailSendService.class);
            l_web3IfoExeMailSendService.sendMailProcess(l_reOrderUnit, null);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.toString(),
                l_wbe);

        }

        //TradingSystem l_tradingSystem = l_finApp.getTradingSystem();

        WEB3GentradeSubAccount l_subAccount = null;

        long l_lngMainAccountId = l_orderUnit.getAccountId();
        try
        {
            log.debug("Get the MainAccount and the SubAccount object.");
            //�擾�⏕����
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().
                getSubAccount(l_lngMainAccountId, l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
       }
        //1.6is�o���I�������P��
        boolean l_blnExecuteEndIfoOrderUnit = this.isExecuteEndIfoOrderUnit(
            l_reOrderUnit, l_strOrderExecutionEndType);
        
        //getOrderId( )
        long l_lngOrderId = l_orderUnit.getOrderId();

        //1.7
        if (l_blnExecuteEndIfoOrderUnit)
        {
            //expireOrder(�����h�c : long)
            log.debug("expireOrder(" + l_lngOrderId + ")");
            ProcessingResult l_reusult = l_optionOrderManagerImpl.expireOrder(l_lngOrderId);
            if (l_reusult.isFailedResult())
            {
                throw new WEB3SystemLayerException(l_reusult.getErrorInfo(), STR_METHOD_NAME);
            }
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
            }
        }

        //is�\�񒍕��m�F�v
        //�����P�ʁF�@@�Ď擾���������P��
        boolean l_blnIsReserveOrderExist =
            l_optionOrderManagerImpl.isReserveOrderExist((IfoOrderUnit)l_reOrderUnit);

        //is�\�񒍕��m�F�v = false
        if (!l_blnIsReserveOrderExist)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //getBranch(arg0 : long)
        //���XID�F�@@�Ď擾���������P��.���XID
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_reOrderUnit.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //is�A�������J�z���{(�����^�C�v : ProductTypeEnum)
        boolean l_blnIsSuccOrderCarryoverEnforcemented =
            l_branch.isSuccOrderCarryoverEnforcemented(ProductTypeEnum.IFO);

        //is�o���I�������P�� = true �܂��� (�o���I���敪 = "�o���I��"�i�ŏI�j&& is�A�������J�z���{ = false)
        WEB3ToSuccReservationIfoOrderUpdateServiceImpl l_reservationIfoOrderUpdateServiceImpl =
            new WEB3ToSuccReservationIfoOrderUpdateServiceImpl();
        if (l_blnExecuteEndIfoOrderUnit
            || (WEB3OrderexecutionEndTypeDef.DEFAULT.equals(l_strOrderExecutionEndType)
                && !l_blnIsSuccOrderCarryoverEnforcemented))
        {
            //expireAll�\�񒍕��P��(�e�����̒���ID : long)
            //�e�����̒���ID�F�@@getOrderId()�̖߂�l
            l_reservationIfoOrderUpdateServiceImpl.expireAllOrderUnit(l_lngOrderId);
        }
        else
        {
            //get�L���\�񒍕��P�ʈꗗ(�e�����̒���ID : long)
            List l_lisOpenReserneIfoOrderUnits =
                l_reservationIfoOrderUpdateServiceImpl.getOpenReserveIfoOrderUnits(l_lngOrderId);
            int l_intSize = l_lisOpenReserneIfoOrderUnits.size();
            //get�L���\�񒍕��P�ʈꗗ()�Ŏ擾����List�̗v�f����Loop����
            for (int i = 0; i < l_intSize; i++)
            {
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                    (RsvIfoOrderUnitRow)l_lisOpenReserneIfoOrderUnits.get(i);
                //is�o���I���\�񒍕��P��(�敨OP�\�񒍕��P��Row, String)
                boolean l_blnIsExecuteEndReserveIfoOrderUnit =
                    this.isExecuteEndReserveIfoOrderUnit(l_rsvIfoOrderUnitRow, l_strOrderExecutionEndType);
                if (l_blnIsExecuteEndReserveIfoOrderUnit)
                {
                    //expire�\�񒍕��P��(�敨OP�\�񒍕��P�ʍs : �敨OP�\�񒍕��P��Row)
                    l_reservationIfoOrderUpdateServiceImpl.expireOrderUnit(l_rsvIfoOrderUnitRow);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�o���I�������P��)<BR>
     * �w��̒����P�ʃI�u�W�F�N�g���o���I���Ώۂ��ǂ����𔻒肷��B<BR>
     * �i�����\�b�h�͎s��ǌ�ɃR�[�������B�j<BR>
     * <BR>
     * <BR>
     * �P�j�����i�ؑցj�x���̒����͎����Ώ�<BR>
     * <BR>
     * �@@OP�����}�l�[�W��.is�������x������(����.�����P��) == true�̏ꍇ�A <BR>
     * �@@true��ԋp����B <BR>
     * <BR>
     * <BR>
     * �Q�j�X�g�b�v���������̒����͎����Ώ� <BR>
     * <BR>
     * �@@����.�����P��.���N�G�X�g�^�C�v == "����"�̏ꍇ�A <BR>
     * �@@true��ԋp����B <BR>
     * <BR>
     * �R�j �������������ŏI���̒����͎����Ώ�<BR>
     * <BR>
     * �@@�@@�@@�R�|�P�j ����.�����P�ʂ���敨OP��������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�Q�j ��L�Ŏ擾�����敨OP�������.getLastTradingDate()��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.�����P��.�������̔�r���ʂ������̏ꍇ�i*�j�Atrue��ԋp����B<BR>
     * <BR>
     * �S�j�ʏ�̎������� <BR>
     * <BR>
     * �@@�S�|�P�j�[��O�o���I���i����.�o���I���敪==�h�[��O�o���I���h�j�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�S�|�P�|�P�j�����o�^�̓������蒍���̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�i����.�����P��.���񒍕��̒����P��ID==NULL�@@���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����.�����P��.�[��O�J�z�Ώۃt���O==�h�[��O�J�z�Ȃ��h�@@���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����.�����P��.����敪==NULL�j <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * �@@�@@�@@�S�|�P�|�Q�j�@@�S�|�P�|�P�j�ȊO�̏ꍇ�A <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@false��ԋp����B <BR>
     * <BR>
     * <BR>
     * �@@�S�|�Q�j�@@�S�|�P�j�ȊO�̏ꍇ�i�o���I���i�ŏI�j�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�@@�S�|�Q�|�P�j���������̒����̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i����.�����P��.���������� == TradingSystem.getBizDate()�j <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * <BR>
     * �@@�@@�@@�@@�S�|�Q�|�Q�j�@@�S�|�Q�|�P�j�ȊO�̏ꍇ�A <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false��ԋp����B<BR>
     * <BR>
     * �i*�j�o���I���i�ŏI�j�̏ꍇ�A�������ԑт��u�����������ԑсv�ƂȂ邽�ߎ擾����<BR>
     * �@@�敨OP��������������c�Ɠ��̂��̂ƂȂ�B<BR>
     * �@@�h�����ŏI���h�ɂ��ẮA�����E�����̐敨OP��������Ƃ������̓��t��<BR>
     * �@@�ݒ肳��Ă��邽�ߏ�L�̎d�l�Ƃ���B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strOrderExecutionEndType - (�o���I���敪)<BR>
     * �o���I���敪<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    private boolean isExecuteEndIfoOrderUnit(
        OrderUnit l_orderUnit, String l_strOrderExecutionEndType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isExecuteEndIfoOrderUnit(OrderUnit, String)";
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

        //�����i�ؑցj�x���̒����͎����Ώ�
        //OP�����}�l�[�W��.is�������x������(����.�����P��) == true�̏ꍇ�A
        //true��ԋp����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        if (l_orderManager.isNotOrderedDelay((IfoOrderUnit)l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�X�g�b�v���������̒����͎����Ώ�
        //����.�����P��.���N�G�X�g�^�C�v == "����"�̏ꍇ�A
        //true��ԋp����B
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        if (WEB3RequestTypeDef.INVALIDATE.equals(l_ifoOrderUnitRow.getRequestType()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�������������ŏI���̒����͎����Ώ�
        WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();

        //����.�����P��.������
        Date l_datBizDate = WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(),
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        Date l_datLastTrading = l_tradedProduct.getLastTradingDate();

        //��r���ʂ������̏ꍇtrue
        if (WEB3DateUtility.compareToDay(l_datBizDate,l_datLastTrading) == 0)
        {
            return true;
        }

        //�[��O�o���I���i����.�o���I���敪==�h�[��O�o���I���h�j�̏ꍇ
        if (WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END.equals(
            l_strOrderExecutionEndType))
        {
            //�����o�^�̓������蒍���̏ꍇ�A
            //�i����.�����P��.���񒍕��̒����P��ID==NULL�@@����
            //����.�����P��.�[��O�J�z�Ώۃt���O==�h�[��O�J�z�Ȃ��h�@@����
            //����.�����P��.����敪==NULL�jtrue��ԋp����B
            if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
                && BooleanEnum.FALSE.equals(l_ifoOrderUnitRow.getEveningSessionCarryoverFlag())
                && l_ifoOrderUnitRow.getSessionType() == null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            //���������̒����̏ꍇ�A
            //�i����.�����P��.���������� == TradingSystem.getBizDate()�jtrue��ԋp����B
            int l_intCompareDate =
                WEB3DateUtility.compareToDay(
                    GtlUtils.getTradingSystem().getBizDate(), l_ifoOrderUnitRow.getExpirationDate());
            if (l_intCompareDate == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�o���I���\�񒍕��P��)<BR>
     * �w��̗\�񒍕��P�ʃI�u�W�F�N�g���o���I���Ώۂ��ǂ����𔻒肷��B <BR>
     * �i�����\�b�h�͎s��ǌ�ɃR�[�������B�j <BR>
     * <BR>
     * �P�j �������������ŏI���̒����͎����Ώ� <BR>
     * <BR>
     * �@@�@@�@@�P�|�P�j ����.�敨OP�\�񒍕��P�ʍs����敨OP��������I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@�@@�P�|�Q�j ��L�Ŏ擾�����敨OP�������.getLastTradingDate()��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.�敨OP�\�񒍕��P�ʍs.�������̔�r���ʂ������̏ꍇ�i*�j�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * �Q�j �ʏ�̎������� <BR>
     * <BR>
     * �@@�@@�@@�Q�|�P�j �[��O�o���I���i����.�o���I���敪==�h�[��O�o���I���h�j�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�Q�|�P�|�P�j �����o�^�̓������蒍���̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i����.�敨OP�\�񒍕��P�ʍs.���񒍕��̒����P��ID==NULL ���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����.�敨OP�\�񒍕��P�ʍs.�[��O�J�z�Ώۃt���O==<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�h�[��O�J�z�Ȃ��h�j�A <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�Q�|�P�|�Q�j �Q�|�P�|�P�j�ȊO�̏ꍇ�A <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false��ԋp����B <BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q�j �Q�|�P�j�ȊO�̏ꍇ�i�o���I���i�ŏI�j�̏ꍇ�j <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�Q�|�Q�|�P�j ���������̒����̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i����.�敨OP�\�񒍕��P�ʍs.���������� == <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@TradingSystem.getBizDate()�j�A <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�Q�|�Q�|�Q�j �Q�|�Q�|�P�j�ȊO�̏ꍇ�A <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false��ԋp����B <BR>
     * <BR>
     * <BR>
     * �i*�j�o���I���i�ŏI�j�̏ꍇ�A�������ԑт��u�����������ԑсv<BR>
     * �@@�@@�ƂȂ邽�ߎ擾����敨OP��������������c�Ɠ��̂��̂ƂȂ�B <BR>
     * �@@�@@�h�����ŏI���h�ɂ��ẮA<BR>
     * �@@�@@�����E�����̐敨OP��������Ƃ������̓��t���ݒ肳��Ă��邽�ߏ�L�̎d�l�Ƃ���B<BR>
     * @@param l_rsvIfoOrderUnitRow - (�敨OP�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * @@param l_strExecuteEndDiv - (�o���I���敪)<BR>
     * �o���I���敪<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isExecuteEndReserveIfoOrderUnit(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow, String l_strExecuteEndDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "isExecuteEndReserveIfoOrderUnit(RsvIfoOrderUnitRow, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j �������������ŏI���̒����͎����Ώ�
        //�P�|�P�j ����.�敨OP�\�񒍕��P�ʍs����敨OP��������I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManager =
            (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        WEB3IfoTradedProductImpl l_tradedProduct = null;
        try
        {
            l_tradedProduct = (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                l_rsvIfoOrderUnitRow.getProductId(), l_rsvIfoOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�P�|�Q�j ��L�Ŏ擾�����敨OP�������.getLastTradingDate()��
        //����.�敨OP�\�񒍕��P�ʍs.�������̔�r���ʂ������̏ꍇ�i*�j�Atrue��ԋp����B
        if (l_rsvIfoOrderUnitRow.getBizDate().equals(
            WEB3DateUtility.formatDate(l_tradedProduct.getLastTradingDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD)))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�Q�j �ʏ�̎�������
        //�Q�|�P�j �[��O�o���I���i����.�o���I���敪==�h�[��O�o���I���h�j�̏ꍇ
        if (WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END.equals(l_strExecuteEndDiv))
        {
            //�Q�|�P�|�P�j �����o�^�̓������蒍���̏ꍇ
            //�i����.�敨OP�\�񒍕��P�ʍs.���񒍕��̒����P��ID==NULL ����
            //����.�敨OP�\�񒍕��P�ʍs.�[��O�J�z�Ώۃt���O==�h�[��O�J�z�Ȃ��h�j�Atrue��ԋp����
            if (l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull()
                && BooleanEnum.FALSE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //�Q�|�Q�j �Q�|�P�j�ȊO�̏ꍇ�i�o���I���i�ŏI�j�̏ꍇ�j
        else
        {
            //�Q�|�Q�|�P�j ���������̒����̏ꍇ
            //�i����.�敨OP�\�񒍕��P�ʍs.���������� == TradingSystem.getBizDate()�j�Atrue��ԋp����B
            if (WEB3DateUtility.compareToDay(GtlUtils.getTradingSystem().getBizDate(),
                l_rsvIfoOrderUnitRow.getExpirationDate()) == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
