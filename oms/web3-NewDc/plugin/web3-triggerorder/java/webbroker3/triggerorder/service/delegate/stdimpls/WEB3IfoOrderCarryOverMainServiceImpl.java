head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����J�z���C���T�[�r�XImpl(WEB3IfoOrderCarryOverMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/22 ��іQ(���u) �V�K�쐬 ���f��No.669
Revision History : 2007/07/11 ��іQ(���u) ���f��No.774
Revision History : 2008/04/11 ��іQ (���u) ���f��No.277,278
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3CarryoverProcessTypeDef;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.message.WEB3IfoOrderCarryOverMainRequest;
import webbroker3.triggerorder.message.WEB3IfoOrderCarryOverMainResponse;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3IfoOrderCarryOverMainService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�����J�z���C���T�[�r�XImpl)<BR>
 * �i���ۃN���X�j<BR>
 * �敨OP�����J�z���C���T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public abstract class WEB3IfoOrderCarryOverMainServiceImpl
    implements WEB3IfoOrderCarryOverMainService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoOrderCarryOverMainServiceImpl.class);

    /**
     * �敨OP�����J�z���C���T�[�r�XImpl<BR>
     */
    public WEB3IfoOrderCarryOverMainServiceImpl()
    {

    }

    /**
     * �敨OP�����J�z���ʏ��������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^��敨OP�����J�z���C�����N�G�X�g�ɃL���X�g���A<BR>
     * this.exec�敨OP�����J�z()���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
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

        WEB3BackResponse l_response = null;

        //���N�G�X�g�f�[�^��敨OP�����J�z���C�����N�G�X�g�ɃL���X�g���A
        //this.exec�敨OP�����J�z()���R�[������B
        if (l_request instanceof WEB3IfoOrderCarryOverMainRequest)
        {
            l_response =
                this.execIfoOrderCarryOver(
                    (WEB3IfoOrderCarryOverMainRequest)l_request);
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
     * (create��������)<BR>
     * �����������쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP���ʃT�[�r�X�j�����J�z�v����<BR>
     * create��������()�������Q��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪<BR>
     * @@param l_strCarryoverProcessType - (�����J�z�����敪)<BR>
     * �����J�z�����敪<BR>
     * @@throws WEB3BaseException
     */
    public void createNextOrder(
        MainAccount l_mainAccount,
        String l_strFutureOptionDiv,
        String l_strCarryoverProcessType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createNextOrder(MainAccount, String, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //lock����
        //�،���ЃR�[�h�F�@@�ڋq[index].�،���ЃR�[�h
        //���X�R�[�h�F�@@�ڋq[index].���X�R�[�h
        //�����R�[�h�F�@@�ڋq[index].�����R�[�h
        l_accountManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode());

        //get�����L�������P��
        //�敨�^�I�v�V�����敪�F�@@�����́u�敨�^�I�v�V�����敪�v
        //�ڋq�F�@@�ڋq[index]
        //�����J�z�����敪�F�@@�����́u�����J�z�����敪�v
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        OrderUnit[] l_orderUnits = l_orderManager.getTodayOpenOrderUnits(
            l_strFutureOptionDiv,
            l_mainAccount,
            l_strCarryoverProcessType);

        // HashMap( )
        HashMap l_map = new HashMap();

        WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);
        //get�����L�������P��() != null�̏ꍇ�A�擾���������P�ʂ��Ƃ�Loop
        if (l_orderUnits != null && l_orderUnits.length != 0)
        {
            int l_intOrderUnitCnt = l_orderUnits.length;
            for (int i = 0; i < l_intOrderUnitCnt; i++)
            {
                //get�L���\�񒍕��P�ʈꗗ(long)
                //�e�����̒���ID�F�@@�����Ώۂ̒����P��.����ID
                List l_lisOpenReserveIfoOrderUnits =
                    l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(l_orderUnits[i].getOrderId());

                //put
                l_map.put(new Long(l_orderUnits[i].getOrderId()), l_lisOpenReserveIfoOrderUnits);

                // expire�J�z������(OrderUnit)
                //�����P�ʁF�@@�����Ώۂ̒����P��
                this.expireCarryOverOriginOrder(l_orderUnits[i]);
            }
        }

        //�]�͍Čv�Z(�ڋq)
        //�ڋq�F�@@�����Ώۂ̌ڋq
        this.reCalcTradingPower(l_mainAccount);

        //get�����L�������P��() != null�̏ꍇ�A�擾���������P�ʂ��Ƃ�Loop
        if (l_orderUnits != null && l_orderUnits.length != 0)
        {
            int l_intOrderUnitCnt = l_orderUnits.length;
            for (int i = 0; i < l_intOrderUnitCnt; i++)
            {
                //�����P�ʂ��Ď擾����B
                OrderUnit l_expiredOrderUnit = null;
                try
                {
                    l_expiredOrderUnit =
                        l_orderManager.getOrderUnit(l_orderUnits[i].getOrderUnitId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //get(arg0 : Object)
                List l_lisRsvIfoOrderUnits = (List)l_map.get(new Long(l_orderUnits[i].getOrderId()));

                //submit��������(IfoOrderUnit)
                try
                {
                    this.submitNextOrder((IfoOrderUnit)l_expiredOrderUnit, l_lisRsvIfoOrderUnits);
                }
                catch (WEB3BusinessLayerException l_web3ble)
                {
                    //update�J�z������(OrderUnit, String)
                    //[�����̐ݒ�]
                    //�����P�ʁF�@@�����P��[index]
                    //�����G���[���R�R�[�h�F���̑�
                    this.updateCarryOverOriginOrder(l_expiredOrderUnit,
                        WEB3ErrorReasonCodeDef.OTHRE_ERROR);
                }
                catch (WEB3SystemLayerException l_web3sle)
                {
                    ErrorInfo l_errInfo = l_web3sle.getErrorInfo();
                    if (WEB3ErrorCatalog.BUSINESS_ERROR_02216.equals(l_errInfo)
                        || WEB3ErrorCatalog.BUSINESS_ERROR_02187.equals(l_errInfo))
                    {
                        //update�J�z������(OrderUnit, String)
                        //[�����̐ݒ�]
                        //�����P�ʁF�@@�����P��[index]
                        //�����G���[���R�R�[�h�F���̑�
                        this.updateCarryOverOriginOrder(l_expiredOrderUnit,
                            WEB3ErrorReasonCodeDef.OTHRE_ERROR);
                    }
                    else
                    {
                        throw l_web3sle;
                    }
                }
                catch (WEB3BaseRuntimeException l_web3bre)
                {
                    ErrorInfo l_errInfo = l_web3bre.getErrorInfo();
                    if (WEB3ErrorCatalog.BUSINESS_ERROR_02216.equals(l_errInfo)
                        || WEB3ErrorCatalog.BUSINESS_ERROR_02187.equals(l_errInfo))
                    {
                        //update�J�z������(OrderUnit, String)
                        //[�����̐ݒ�]
                        //�����P�ʁF�@@�����P��[index]
                        //�����G���[���R�R�[�h�F���̑�
                        this.updateCarryOverOriginOrder(l_expiredOrderUnit,
                            WEB3ErrorReasonCodeDef.OTHRE_ERROR);
                    }
                    else
                    {
                        throw l_web3bre;
                    }
                }
            }
        }
    }

    /**
     * (exec�敨OP�����J�z)<BR>
     * �敨OP�����J�z���ʏ��������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP���ʃT�[�r�X�j�����J�z�v�Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �敨OP�����J�z���C�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3IfoOrderCarryOverMainResponse
     * @@throws WEB3BaseException
     */
    protected WEB3IfoOrderCarryOverMainResponse execIfoOrderCarryOver(
        WEB3IfoOrderCarryOverMainRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execIfoOrderCarryOver(WEB3IfoOrderCarryOverMainRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // get�敨�^�I�v�V�����敪( )
        String l_strFutureOptionDiv = this.getFutureOptionDiv();

        //get�����J�z�����敪( )
        String l_strCarryoverProcessType = this.getCarryoverProcessType();

        //get�I�����C���T�[�r�X�敪(String, String)
        //�،���ЃR�[�h�F�@@���N�G�X�g.�،���ЃR�[�h
        //�����J�z�����敪�F�@@get�����J�z�����敪()�̖߂�l
        String l_strOnlineServiceDiv = this.getOnlineServiceDiv(
            l_request.institutionCode,
            l_strCarryoverProcessType);

        //get�I�����C���T�[�r�X�敪()�̖߂�l��null�̏ꍇ
        if (l_strOnlineServiceDiv == null)
        {
            //���������ɂ��̂܂�return����B
            //���o���I���ʒm�������������Ă��Ȃ��ꍇ
            WEB3IfoOrderCarryOverMainResponse l_response =
                (WEB3IfoOrderCarryOverMainResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�敨OP�I�����C�����s����TransactionCallback(String, long, long, String, String)
        //�،���ЃR�[�h�F�@@���N�G�X�g.�،���ЃR�[�h
        //From����ID�F�@@���N�G�X�g.From����ID
        //To����ID�F�@@���N�G�X�g.To����ID
        //�敨�^�I�v�V�����敪�F�@@get�敨�^�I�v�V�����敪()�̖߂�l
        //�I�����C���T�[�r�X�敪�F�@@get�I�����C���T�[�r�X�敪()�̖߂�l
        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
        try
        {
            // getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�敨OP�I�����C�����s����TransactionCallback(String, long, long, String, String)
            //�،���ЃR�[�h�F�@@���N�G�X�g.�،���ЃR�[�h
            //From����ID�F�@@���N�G�X�g.From����ID
            //To����ID�F�@@���N�G�X�g.To����ID
            //�敨�^�I�v�V�����敪�F�@@get�敨�^�I�v�V�����敪()�̖߂�l
            //�I�����C���T�[�r�X�敪�F�@@get�I�����C���T�[�r�X�敪()�̖߂�l
            WEB3IfoOnlineRunStatusTransactionCallback l_ifoOnlineRunStatusTransactionCallback =
                new WEB3IfoOnlineRunStatusTransactionCallback(
                    l_request.institutionCode,
                    l_request.rangeFrom,
                    l_request.rangeTo,
                    l_strFutureOptionDiv,
                    l_strOnlineServiceDiv);

            //doTransaction(TX_CREATE_NEW : int, �I�����C�����s����TransactionCallback : TransactionCallback)
            l_onlineRunStatus = (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ifoOnlineRunStatusTransactionCallback);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            // set������()�����d�N���̗�O��throw���ꂽ�ꍇ
            Object l_exception = l_dataCallbackException.getDetails();
            if (l_exception instanceof WEB3BaseException)
            {
                WEB3BaseException l_baseException = (WEB3BaseException)l_exception;
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01992.equals(l_baseException.getErrorInfo()))
                {
                    log.debug("�w��AP�N�����i��d�N���G���[�j�B", l_baseException);

                    //���������ɂ��̂܂�return����B
                    WEB3IfoOrderCarryOverMainResponse l_response =
                        (WEB3IfoOrderCarryOverMainResponse)l_request.createResponse();

                    log.exiting(STR_METHOD_NAME);
                    return l_response;
                }
                throw l_baseException;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get�����L�������ڋq�ꗗ(String, �،����, long, long, String)
        //�敨�^�I�v�V�����敪�F�@@get�敨�^�I�v�V�����敪()�̖߂�l
        //�،���ЁF�@@���N�G�X�g�f�[�^.�،���ЃR�[�h�ɊY������،����
        //From����ID�F�@@���N�G�X�g�f�[�^.From����ID
        //To����ID�F�@@���N�G�X�g�f�[�^.To����ID
        //�����J�z�����敪�F�@@get�����J�z�����敪()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        Institution l_institution = null;
        try
        {
            l_institution =
                l_finApp.getAccountManager().getInstitution(l_request.institutionCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        MainAccount[] l_mainAccounts = l_orderManager.getTodayOpenOrderMainAcounts(
            l_strFutureOptionDiv,
            l_institution,
            l_request.rangeFrom,
            l_request.rangeTo,
            l_strCarryoverProcessType);

        boolean l_blnIsCarryOverAllAccountsSuccess = true;

        WEB3IfoOrderCarryOverMainService l_orderCarryOverMainService = null;

        if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            l_orderCarryOverMainService =
                (WEB3OptionOrderCarryOverService)Services.getService(WEB3OptionOrderCarryOverService.class);
        }
        else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
        {
            l_orderCarryOverMainService =
                (WEB3FuturesOrderCarryOverService)Services.getService(WEB3FuturesOrderCarryOverService.class); 
        }

        //get�����L�������ڋq�ꗗ()�̖߂�l != null�̏ꍇ�A�擾�����ڋq���Ƃ�Loop
        if (l_mainAccounts != null)
        {
            int l_intMainAccountCnt = l_mainAccounts.length;
            for (int i = 0; i < l_intMainAccountCnt; i++)
            {
                //create��������(MainAccount)
                //�ڋq�F�@@�����Ώۂ̌ڋq
                try
                {
                    l_orderCarryOverMainService.createNextOrder(
                        l_mainAccounts[i],
                        l_strFutureOptionDiv,
                        l_strCarryoverProcessType);
                }
                catch (Exception l_ex)
                {
                    log.debug("�ڋq�P�ʌJ�z�G���[���� :����ID[" + l_mainAccounts[i].getAccountId() + "]");
                    log.debug(STR_METHOD_NAME, l_ex);
                    l_blnIsCarryOverAllAccountsSuccess = false;
                }
            }
        }

        //update���s�X�e�[�^�X�敪
        //���s�X�e�[�^�X�敪�F
        //�J�z�������S�ڋq�Ő���I�������ꍇ��"������"
        //1�ڋq�ł��V�X�e���G���[���X���[���ꂽ�ꍇ��"�G���["���Z�b�g�B
        String l_strRunStatus = null;
        if (l_blnIsCarryOverAllAccountsSuccess)
        {
            l_strRunStatus = WEB3RunStatusDivDef.DEALED;
        }
        else
        {
            l_strRunStatus = WEB3RunStatusDivDef.ERROR;
        }
        l_onlineRunStatus.updateRunStatusDiv(l_strRunStatus);

        WEB3IfoOrderCarryOverMainResponse l_response =
            (WEB3IfoOrderCarryOverMainResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�敨�^�I�v�V�����敪)<BR>
     * �i���ۃ��\�b�h�j<BR>
     * �敨�^�I�v�V�����敪���擾����B<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected abstract String getFutureOptionDiv();

    /**
     * (expire�J�z������)<BR>
     * �i���ۃ��\�b�h�j<BR>
     * �J�z�������̎����������s���B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �i�J�z���j�����P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    protected abstract void expireCarryOverOriginOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException;

    /**
     * (�]�͍Čv�Z)<BR>
     * �i���ۃ��\�b�h�j<BR>
     * �]�͍Čv�Z���s���B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    protected abstract void reCalcTradingPower(MainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (submit��������)<BR>
     * �i���ۃ��\�b�h�j<BR>
     * ����������o�^����B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_lisRsvIfoOrderUnits - (�\�񒍕��P�ʈꗗ)<BR>
     * �\�񒍕��P�ʈꗗ<BR>
     * @@throws WEB3BaseException
     */
    protected abstract void submitNextOrder(IfoOrderUnit l_ifoOrderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException;

    /**
     * (update�J�z������)<BR>
     * �i���ۃ��\�b�h�j<BR>
     * �J�z�������̒����G���[���R�R�[�h���X�V����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �i�J�z���j�����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strOrderErrorReasonCode - (�����G���[���R�R�[�h)<BR>
     * (�����G���[���R�R�[�h)<BR>
     * <BR>
     * DB���C�A�E�g<BR>
     * �����P�ʃe�[�u���d�l.xls<BR>
     * �u�i�����P�ʃe�[�u���⑫�j�����G���[���R�R�[�h�v�V�[�g�Q�ƁB<BR>
     * @@throws WEB3BaseException
     */
    protected abstract void updateCarryOverOriginOrder(
        OrderUnit l_orderUnit, String l_strOrderErrorReasonCode)
            throws WEB3BaseException;

    /**
     * (get�I�����C���T�[�r�X�敪)<BR>
     * �o���I���ʒm�̌��ʂ��擾���A <BR>
     * �I�����C�����s���ʃe�[�u�����X�V���邽�߂� <BR>
     * �I�����C���T�[�r�X�敪�l��ԋp����B <BR>
     * <BR>
     * �P�j�@@�o���I���e�[�u���̌��� <BR>
     * �@@�ȉ��̏����ŏo���I���e�[�u������������B <BR>
     * <BR>
     * �@@�@@�@@������������ <BR>
     * �@@�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h <BR>
     * �@@�@@�����^�C�v�F�@@"�敨�I�v�V����" <BR>
     * �@@�@@�敨�^�I�v�V�����敪�F�@@this.get�敨�^�I�v�V�����敪()�̖߂�l <BR>
     * �@@�@@�o���I���敪�F <BR>
     * �@@�@@�@@[����.�����J�z�����敪��"�����J�z"�̏ꍇ] <BR>
     * �@@�@@�@@�@@"DEFAULT" <BR>
     * �@@�@@�@@[����.�����J�z�����敪��"�[��O�����J�z"�̏ꍇ] <BR>
     * �@@�@@�@@�@@"�[��O�o���I��" <BR>
     * <BR>
     * �Q�j�@@�ԋp�l�̔��� <BR>
     * �@@�Q�|�P�j�@@�P�j�Ŏ擾�������R�[�h����0���̏ꍇ <BR>
     * �@@�@@�o���I���ʒm���������Ɣ��肵�Anull��ԋp����B <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�P�j�Ŏ擾�������R�[�h�����P���̏ꍇ <BR>
     * �@@�@@����.�����J�z�����敪�ɊY������I�����C���T�[�r�X�敪��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strCarryoverProcessType - (�����J�z�����敪)<BR>
     * �����J�z�����敪<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getOnlineServiceDiv(String l_strInstitutionCode, String l_strCarryoverProcessType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOnlineServiceDiv(String, String)";
        log.entering(STR_METHOD_NAME);

        //�o���I���敪�F
        //[����.�����J�z�����敪��"�����J�z"�̏ꍇ]
        //"DEFAULT"
        //[����.�����J�z�����敪��"�[��O�����J�z"�̏ꍇ]
        //"�[��O�o���I��"
        String l_strOrderExecutionType =
            WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;

        if (WEB3CarryoverProcessTypeDef.ORDER_CARRY_OVER.equals(l_strCarryoverProcessType))
        {
            l_strOrderExecutionType = WEB3OrderexecutionEndTypeDef.DEFAULT;
        }

        //�@@������������
        // �،���ЃR�[�h�F�@@����.�،���ЃR�[�h
        // �����^�C�v�F�@@"�敨�I�v�V����"
        // �敨�^�I�v�V�����敪�F�@@this.get�敨�^�I�v�V�����敪()�̖߂�l
        // �o���I���敪�F
        StringBuffer l_sbQueryCond = new StringBuffer();
        l_sbQueryCond.append("institution_code = ? ");
        l_sbQueryCond.append(" and product_type = ? ");
        l_sbQueryCond.append(" and future_option_div = ? ");
        l_sbQueryCond.append(" and orderexecution_end_type = ?");

        Object[] l_objectWheres =
        {
            l_strInstitutionCode,
            ProductTypeEnum.IFO,
            this.getFutureOptionDiv(),
            l_strOrderExecutionType
        };

        List l_lisResultList = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisResultList = l_queryProcessor.doFindAllQuery(
                OrderexecutionEndRow.TYPE,
                l_sbQueryCond.toString(),
                l_objectWheres);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�|�P�j�@@�P�j�Ŏ擾�������R�[�h����0���̏ꍇ
        //�o���I���ʒm���������Ɣ��肵�Anull��ԋp����B
        if (l_lisResultList == null || l_lisResultList.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�擾�������R�[�h�����P���̏ꍇ
        //����.�����J�z�����敪�ɊY������I�����C���T�[�r�X�敪��ԋp����B
        //�����J�z�����敪��"�����J�z"�̏ꍇ�F�@@2�F�����J�z
        //�����J�z�����敪��"�[��O�����J�z"�̏ꍇ�F�@@8�F�[��O�����J�z
        if (WEB3CarryoverProcessTypeDef.ORDER_CARRY_OVER.equals(l_strCarryoverProcessType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3OnlineServiceDiv.ORDER_CARRY_OVER;
        }

        log.exiting(STR_METHOD_NAME);
        return WEB3OnlineServiceDiv.BEFORE_EVENING_SESSION_ORDER_CARRY_OVER;
    }

    /**
     * (get�����J�z�����敪)<BR>
     * �����J�z�����敪���擾����B<BR>
     * <BR>
     * �P�j�@@���ԑт̔���<BR>
     * �@@������ԊǗ�.is�[�ꎞ�ԑ�()��call����B<BR>
     * <BR>
     * �Q�j�@@�ԋp�l�̔���<BR>
     * �@@�P�j�̖߂�l��true�̏ꍇ�A"�[��O�����J�z"��ԋp����B<BR>
     * �@@�ȊO�̏ꍇ�A"�����J�z"��ԋp����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getCarryoverProcessType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCarryoverProcessType()";
        log.entering(STR_METHOD_NAME);

        //���ԑт̔���
        //������ԊǗ�.is�[�ꎞ�ԑ�()��call����B
        boolean l_blnIsEveningSessionTimeZone =
            WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();

        //�P�j�̖߂�l��true�̏ꍇ�A"�[��O�����J�z"��ԋp����B
        if (l_blnIsEveningSessionTimeZone)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3CarryoverProcessTypeDef.BEFORE_EVENING_SESSION_ORDER_CARRY_OVER;
        }

        //�ȊO�̏ꍇ�A"�����J�z"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3CarryoverProcessTypeDef.ORDER_CARRY_OVER;
    }

    /**
     * �敨OP�I�����C�����s����TransactionCallback<BR>
     * �敨OP�I�����C�����s����TransactionCallback�N���X<BR>
     */
    public class WEB3IfoOnlineRunStatusTransactionCallback implements TransactionCallback
    {
        /**
         * (�،���ЃR�[�h)<BR>
         * �J�z�Ώۂ̏،���ЃR�[�h�B<BR>
         */
        public String institutionCode;

        /**
         * (From����ID)<BR>
         * From����ID<BR>
         */
        public long rangeFrom;

        /**
         * (To����ID)<BR>
         * To����ID<BR>
         */
        public long rangeTo;

        /**
         * (�敨�^�I�v�V�����敪)<BR>
         * �敨�^�I�v�V�����敪<BR>
         */
        public String futureOptionDiv;

        /**
         * (�I�����C���T�[�r�X�敪)<BR>
         * �I�����C���T�[�r�X�敪<BR>
         */
        public String onlineServiceDiv;

        /**
         * (�敨OP�I�����C�����s����TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * @@param l_strInstitutionCode -(�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         * @@param l_lngRangeFrom - (From����ID)<BR>
         * From����ID<BR>
         * @@param l_lngRangeTo - (To����ID)<BR>
         * To����ID<BR>
         * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
         * �敨�^�I�v�V�����敪<BR>
         * @@param l_strOnlineServiceDiv - (�I�����C���T�[�r�X�敪)<BR>
         * �I�����C���T�[�r�X�敪<BR>
         */
        public WEB3IfoOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo,
            String l_strFutureOptionDiv,
            String l_strOnlineServiceDiv)
        {
            this.institutionCode = l_strInstitutionCode;
            this.rangeFrom = l_lngRangeFrom;
            this.rangeTo = l_lngRangeTo;
            this.futureOptionDiv = l_strFutureOptionDiv;
            this.onlineServiceDiv = l_strOnlineServiceDiv;
        }

        /**
         * �I�����C�����s���ʃe�[�u����"������"�ݒ���s���B<BR>
         * <BR>
         * �P�j�@@�I�����C�����s����.set������()���\�b�h���R�[������B<BR>
         * �@@[set������()�Ɏw�肷�����] <BR>
         * �@@�@@�،���ЃR�[�h�F�@@this.�،���ЃR�[�h<BR>
         * �@@�@@�����^�C�v�F�@@"�敨�I�v�V����" <BR>
         * �@@�@@�敨�^�I�v�V�����敪�F�@@this.�敨�^�I�v�V�����敪<BR>
         * �@@�@@�I�����C���T�[�r�X�敪�F�@@this.�I�����C���T�[�r�X�敪<BR>
         * �@@�@@From����ID�F�@@this.From����ID<BR>
         * �@@�@@To����ID�F�@@this.To����ID<BR>
         * <BR>
         * �Q�j�@@set������()�̖߂�l��ԋp����B<BR>
         * @@return Object
         * @@throws DataCallbackException�G
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //�P�j�@@�I�����C�����s����.set������()���\�b�h���R�[������B
            // �،���ЃR�[�h�F�@@this.�،���ЃR�[�h
            // �����^�C�v�F�@@"�敨�I�v�V����"
            // �敨�^�I�v�V�����敪�F�@@this.�敨�^�I�v�V�����敪
            // �I�����C���T�[�r�X�敪�F�@@this.�I�����C���T�[�r�X�敪
            // From����ID�F�@@this.From����ID
            // To����ID�F�@@this.To����ID
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode,
                    ProductTypeEnum.IFO,
                    this.futureOptionDiv,
                    this.onlineServiceDiv,
                    this.rangeFrom,
                    this.rangeTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }
            //�Q�j�@@set������()�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
}
@
