head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍώ���T�[�r�XImpl(WEB3AioSLRepayCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �d�l�ύX�E���f��No.757,774,775,792,793
Revision History : 2007/12/14 �đo�g (���u) �d�l�ύX�E���f��No.828
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSLRepayCancelUpdateInterceptor;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�،��S�ۃ��[���ԍώ���T�[�r�XImpl)<BR>
 * �،��S�ۃ��[���ԍώ���T�[�r�X�����N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSLRepayCancelServiceImpl extends WEB3ClientRequestService
    implements WEB3AioSLRepayCancelService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayCancelServiceImpl.class);

    /**
     * @@roseuid 46E89084006A
     */
    public WEB3AioSLRepayCancelServiceImpl()
    {

    }

    /**
     * �،��S�ۃ��[���ԍώ���T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��validate����()�A�܂���submit����()���\�b�h��<BR>
     * �R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE4D0B03C7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
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

        WEB3GenResponse l_response;
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̏������R�[������B
        //get���͉��()
        //get�����Ɖ���()
        if (l_request instanceof WEB3SLRepayCancelConfirmRequest)
        {
            l_response =
                validateOrder((WEB3SLRepayCancelConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SLRepayCancelCompleteRequest)
        {
            l_response =
                submitOrder((WEB3SLRepayCancelCompleteRequest)l_request);
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
     * (validate����)<BR>
     * �،��S�ۃ��[���ԍώ���̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��S�ۃ��[���ԍώ���jvalidate�����v�Q�ƁB<BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jvalidate����<BR>
     * ��̈ʒu�Fis�،��S�ۃ��[�������J��( )<BR>
     * �@@�@@�߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE4D0B03D7
     */
    protected WEB3SLRepayCancelConfirmResponse validateOrder(WEB3SLRepayCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SLRepayCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        // �⏕�����^�C�v�F 1�i�a��������j
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        // is�،��S�ۃ��[�������J��( )
        boolean l_blnIsSeuredLoanAccountOpen = l_mainAccount.isSecuredLoanAccountOpen();
        if (!l_blnIsSeuredLoanAccountOpen)
        {
            log.debug("�،��S�ۃ��[�����������J�݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،��S�ۃ��[�����������J�݂ł��B");
        }

        // CancelOrderSpec(����ID : long)
        // ����ID�F ���N�G�X�g�f�[�^.����ID
        CancelOrderSpec l_cancelOrderSpec =
            new CancelOrderSpec(Long.parseLong(l_request.orderId));

        // validate�������(SubAccount, CancelOrderSpec)
        // �⏕�����F get�⏕����()�̖߂�l
        // ����������e�F ����������e�I�u�W�F�N�g
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        OrderValidationResult l_validationResult =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in validateCancelOrder" +
                l_validationResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate����\(CancelOrderSpec)
        //[����]
        //  ����������e�F ����������e�I�u�W�F�N�g
        this.validateCancelAccept(l_cancelOrderSpec);

        // getOrderUnits(����ID : long)
        OrderUnit[] l_orderUnits =
            l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));

        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        // getQuantity( )
        double l_dblQuantity = l_aioOrderUnit.getQuantity();

        // getEstimatedTransferDate( )
        Date l_datEstimatedTransgerDate = l_aioOrderUnit.getEstimatedTransferDate();

        // get�S�ۃ��[���U�։\�z(�⏕���� : �⏕����, ��n�� : Date)
        // �⏕�����F get�⏕����()�̖߂�l
        // ��n���F getEstimatedTransferDate()�̖߂�l
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblPaymentTradingPower =
        	l_tpTradingPowerService.getSLChangePossAmt(l_subAccount, l_datEstimatedTransgerDate);

        // createResponse( )
        WEB3SLRepayCancelConfirmResponse l_response =
            (WEB3SLRepayCancelConfirmResponse)l_request.createResponse();

        // �v���p�e�B�Z�b�g
        // ���X�|���X.����ID = ���N�G�X�g�f�[�^.����ID
        l_response.orderId = l_request.orderId;
        // ���X�|���X.�ԍω\�z = ����]�̓T�[�r�X.get�S�ۃ��[���U�։\�z()�̖߂�l
        l_response.repayableAmt = WEB3StringTypeUtility.formatNumber(l_dblPaymentTradingPower);
        // ���X�|���X.�ԍϊz = �����P��.getQuantity()�̖߂�l
        l_response.repayAmt = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
        // ���X�|���X.�ԍϗ\��� = �����P��.getEstimatedTransferDate()�̖߂�l
        l_response.repayScheduledDate = l_datEstimatedTransgerDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �،��S�ۃ��[���ԍώ���̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��S�ۃ��[���ԍώ���jsubmit�����v�Q�ƁB<BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jsubmit����<BR>
     * ��̈ʒu�Fsubmit����( )<BR>
     * �@@�@@�߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE4D0B03E6
     */
    protected WEB3SLRepayCancelCompleteResponse submitOrder(WEB3SLRepayCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SLRepayCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        // �⏕�����^�C�v�F 1�i�a��������j
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        // is�،��S�ۃ��[�������J��( )
        boolean l_blnIsSecuredLoanAccountOpen = l_mainAccount.isSecuredLoanAccountOpen();
        // �߂�l == false �̏ꍇ�A��O���X���[����B
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            log.debug("�،��S�ۃ��[�����������J�݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،��S�ۃ��[�����������J�݂ł��B");
        }

        // CancelOrderSpec(����ID : long)
        // ����ID�F ���N�G�X�g�f�[�^.����ID
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(Long.parseLong(l_request.orderId));

        // validate�������(SubAccount, CancelOrderSpec)
        // �⏕�����F get�⏕����()�̖߂�l
        // ����������e�F ����������e�I�u�W�F�N�g
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        OrderValidationResult l_validationResult =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in validateCancelOrder" +
                l_validationResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate����\(CancelOrderSpec)
        //[����]
        // ����������e�F ����������e�I�u�W�F�N�g
        this.validateCancelAccept(l_cancelOrderSpec);

        // �،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^( )
        WEB3AioSLRepayCancelUpdateInterceptor l_slRepayCancelUpdateInterceptor =
            new WEB3AioSLRepayCancelUpdateInterceptor();

        // getOrderUnits(����ID : long)
        OrderUnit[] l_orderUnits =
            l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        if (l_orderUnits.length <= 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];

        // setThreadLocalPersistenceEventInterceptor(�،��S�ۃ��[���ԍώ���X�V
        // �C���^�Z�v�^ : AioOrderManagerPersistenceEventInterceptor)
        // �،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^�F�@@���������،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_slRepayCancelUpdateInterceptor);

        // submitCancelOrder(�⏕���� : SubAccount, ����������e : CancelOrderSpec,
        // �p�X���[�h : String, isSkip�����R�� : boolean)
        // �⏕�����F�@@get�⏕����()�̖߂�l
        // ����������e�F�@@����������e�I�u�W�F�N�g
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        // isSkip�����R���F�@@true
        OrderSubmissionResult l_submitCancelOrderResult = 
            l_orderManager.submitCancelOrder(l_subAccount, l_cancelOrderSpec, l_request.password, true);

        if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitCancelOrder" +
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �]�͍Čv�Z(�⏕���� : �⏕����)
        // �⏕�����F�@@�⏕�����I�u�W�F�N�g
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        l_service.reCalcTradingPower(l_subAccount);

        // getOrderUnit
        AioOrderUnit l_aioOrderUnitCanceled = null;
        try
        {
            l_aioOrderUnitCanceled =
                (AioOrderUnit)l_orderManager.getOrderUnit(l_aioOrderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in �����P�ʃI�u�W�F�N�g���擾����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // getQuantity( )
        double l_dblQuantity = l_aioOrderUnitCanceled.getQuantity();

        // getEstimatedTransferDate( )
        Date l_datEstimatedTransferDate = l_aioOrderUnitCanceled.getEstimatedTransferDate();

        // getDataSourceObject( )
        AioOrderUnitRow l_aioOrderUnitRow =
            (AioOrderUnitRow)l_aioOrderUnitCanceled.getDataSourceObject();

        // getLastUpdatedTimestamp( )
        Timestamp l_tsLastUpdatedTimestamp = l_aioOrderUnitRow.getLastUpdatedTimestamp();

        // createResponse( )
        WEB3SLRepayCancelCompleteResponse l_slRepayCancelCompleteResponse =
            (WEB3SLRepayCancelCompleteResponse)l_request.createResponse();

        // �v���p�e�B�Z�b�g
        // ���X�|���X.�ԍϊz = �����P��.getQuantity()�̖߂�l
        l_slRepayCancelCompleteResponse.repayAmt = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
        // ���X�|���X.�ԍϗ\��� = �����P��.getEstimatedTransferDate()�̖߂�l
        l_slRepayCancelCompleteResponse.repayScheduledDate = l_datEstimatedTransferDate;
        // ���X�|���X.�X�V���� = �����P��Params.getLastUpdateedTimestamp()�̖߂�l
        l_slRepayCancelCompleteResponse.lastUpdatedTimestamp = l_tsLastUpdatedTimestamp;
        // ���X�|���X.����ID = ���N�G�X�g�f�[�^.����ID
        l_slRepayCancelCompleteResponse.orderId = l_request.orderId;

        log.exiting(STR_METHOD_NAME);
        return l_slRepayCancelCompleteResponse;
    }

    /**
     * (validate����\)<BR>
     * ����\�`�F�b�N�����{����B<BR>
     * <BR>
     * �P�j���������擾����B<BR>
     * ������ԊǗ�.get������( )���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j����������e�Ó����`�F�b�N<BR>
     * �Y������������\�����`�F�b�N����B<BR>
     * <BR>
     * �Q�|�P�j����������e�������Ώۂ̒����I�u�W�F�N�g���擾����B<BR>
     * ����������e.getOrderID()<BR>
     * <BR>
     * �Q�|�Q�j�����I�u�W�F�N�g����A�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * ����.getOrderUnits()��1�Ԗڂ̗v�f<BR>
     * <BR>
     * �Q�|�R�j������Ԃ̃`�F�b�N<BR>
     * <BR>
     * get������( )�̖߂�l �� �����P�ʃI�u�W�F�N�g.������<BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02965<BR>
     * <BR>
     * @@param l_cancelOrderSpec - (����������e)<BR>
     * ����������e�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    private void validateCancelAccept(CancelOrderSpec l_cancelOrderSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancelAccept(CancelOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_cancelOrderSpec == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���������擾����B
        //������ԊǗ�.get������( )���\�b�h���R�[������B
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //����������e�������Ώۂ̒����I�u�W�F�N�g���擾����B
        //����������e.getOrderID()
        long l_lngOrderId = l_cancelOrderSpec.getOrderId();

        //�����I�u�W�F�N�g����A�����P�ʃI�u�W�F�N�g���擾����B
        //����.getOrderUnits()��1�Ԗڂ̗v�f
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        OrderManager l_orderManager = l_tradingModule.getOrderManager();
        Order l_order;
        try
        {
            l_order = l_orderManager.getOrder(l_lngOrderId);
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

        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        if (l_orderUnits.length > 0)
        {
            OrderUnit l_orderUnit = l_orderUnits[0];
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
    
            //get������( )�̖߂�l �� �����P�ʃI�u�W�F�N�g.������
            //�̏ꍇ�A��O���X���[����B
            String l_strBizDate = l_aioOrderUnitRow.getBizDate();
            Date l_datOrderUnitBizDate =
                WEB3DateUtility.getDate(l_strBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            if (WEB3DateUtility.compare(l_datOrderBizDate, l_datOrderUnitBizDate) > 0)
            {
                log.debug("get������( )�̖߂�l �� �����P�ʃI�u�W�F�N�g.������");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02965,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����P�ʂ̔����������݂̔�������菬�����l�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
