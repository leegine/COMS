head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\���T�[�r�XImpl(WEB3AioSLRepayApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �đo�g (���u) �d�l�ύX ���f��No.757,���f��No.775
Revision History : 2007/10/16 �đo�g (���u) �d�l�ύX ���f��No.806
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSLRepayUpdateInterceptor;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��S�ۃ��[���ԍϐ\���T�[�r�XImpl)<BR>
 * �،��S�ۃ��[���ԍϐ\���T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AioSLRepayApplyServiceImpl extends WEB3ClientRequestService implements WEB3AioSLRepayApplyService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayApplyServiceImpl.class);

    /**
     * @@roseuid 46E8908303C5
     */
    public WEB3AioSLRepayApplyServiceImpl()
    {

    }

    /**
     * �،��S�ۃ��[���ԍϐ\���T�[�r�X���������{����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��validate����()�A�܂���submit����()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SLRepayApplyConfirmRequest)
        {
            l_response =
                this.validateOrder(
                    (WEB3SLRepayApplyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SLRepayApplyCompleteRequest)
        {
            l_response =
                this.submitOrder(
                    (WEB3SLRepayApplyCompleteRequest)l_request);
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
     * (validate����)<BR>
     * �،��S�ۃ��[���ԍϐ\���̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��S�ۃ��[���ԍϐ\���jvalidate�����v�Q�ƁB<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jvalidate����<BR>
     * ��̈ʒu�Fis�،��S�ۃ��[�������J��( )<BR>
     * �@@�߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jvalidate����<BR>
     * ��̈ʒu�Fget���ߐU����(SubAccount, Date)<BR>
     * �@@get���ߐU����()�̖߂�l > ���N�G�X�g�f�[�^.�ԍϗ\��� �̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02915<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jvalidate����<BR>
     * ��̈ʒu�Fvalidate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],<BR>
     * �@@�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)<BR>
     * �@@�@@�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_01306<BR>
     * ========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayApplyConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3SLRepayApplyConfirmResponse validateOrder(WEB3SLRepayApplyConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SLRepayApplyConfirmRequest)";
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

        //validate( )
        l_request.validate();

        //get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        //[����]
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //validate����(SubAccount)
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.validateOrder(l_subAccount);

        //getMainAccount( )
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�،��S�ۃ��[�������J��( )
        boolean l_blnIsSecuredLoanAccountOpen = l_gentradeMainAccount.isSecuredLoanAccountOpen();
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            //�߂�l == false �̏ꍇ�A��O���X���[����B
            log.debug("�،��S�ۃ��[�����������J�݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + STR_METHOD_NAME,
                "�،��S�ۃ��[�����������J�݂ł��B");
        }

        //get������( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get���ߐU����(SubAccount, Date)
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        //�������F get������()�̖߂�l
        Date l_datClosestTransferDate =
            l_aioOrderManager.getClosestTransferDate(
                l_subAccount,
                l_datOrderBizDate);

        if (WEB3DateUtility.compare(l_datClosestTransferDate, l_request.repayScheduledDate) > 0)
        {
            //get���ߐU����()�̖߂�l > ���N�G�X�g�f�[�^.�ԍϗ\��� �̏ꍇ�A��O���X���[����B
            log.debug("���ߐU�����͕ԍϗ\������傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02915,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ߐU�����͕ԍϗ\������傫���ł��B");
        }

        //validateSL�ԍϏd������(SubAccount, Date)
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        //��n���F ���N�G�X�g�f�[�^.�ԍϗ\���
        l_aioOrderManager.validateSLRepayDuplicateOrder(l_subAccount, l_request.repayScheduledDate);

        //get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //get���iID(Institution)
        //[����]
        //�،���ЁF �⏕����.getInstitution()�̖߂�l
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());

        //���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, long, double, String, Date, String, Long)
        //[����]
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l
        //������ʁF 1020�i�U�֒����i�a���������I���b�N�X�N���W�b�g�j�j
        //�U�փ^�C�v�F 2�i�o���j
        //���iID�F get���iID()�̖߂�l
        //���z�F ���N�G�X�g�f�[�^.�ԍϊz
        //�L�q�F null
        //�U�֗\����F ���N�G�X�g�f�[�^.�ԍϗ\���
        //���ϋ@@��ID�F null
        //����ID�F null
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.TO_ORIX_CREDIT,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                Double.parseDouble(l_request.repayAmt),
                null,
                l_request.repayScheduledDate,
                null,
                null);

        //�،��S�ۃ��[���ԍύX�V�C���^�Z�v�^(���o���������e)
        //[����]
        //���o���������e�F ���o���������e
        WEB3AioSLRepayUpdateInterceptor l_slrepayUpdateInterceptor =
            new WEB3AioSLRepayUpdateInterceptor(l_aioNewOrderSpec);

        //�v���p�e�B�Z�b�g
        //�C���^�Z�v�^.������ = �i�ȉ��̂Ƃ���j
        //    get������()�̖߂�l = ���o���������e.�U�֗\��� or
        //    get������()�̖߂�l�̗��c�Ɠ� = ���o���������e.�U�֗\��� �̏ꍇ�Aget������()�̖߂�l
        //    ����ȊO�̏ꍇ�A���o���������e.�U�֗\����̑O�c�Ɠ�
        Date l_datEstimatedTransferDate = l_aioNewOrderSpec.getEstimatedTransferDate();
        WEB3GentradeBizDate l_gentradeOrderBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        if (WEB3DateUtility.compare(l_datOrderBizDate, l_datEstimatedTransferDate) == 0
            || WEB3DateUtility.compare(l_gentradeOrderBizDate.roll(1), l_datEstimatedTransferDate) == 0)
        {
            l_slrepayUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
        }
        else
        {
            WEB3GentradeBizDate l_gentradeTransferDate =
                new WEB3GentradeBizDate(new Timestamp(l_datEstimatedTransferDate.getTime()));
            l_slrepayUpdateInterceptor.setOrderBizDate(l_gentradeTransferDate.roll(-1));
        }

        //�C���^�Z�v�^.��n�� = ���o���������e.�U�֗\���
        l_slrepayUpdateInterceptor.setDeliveryDate(l_datEstimatedTransferDate);

        //validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],
        //   �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        //[����]
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g
        //�������e�C���^�Z�v�^�F �،��S�ۃ��[���ԍύX�V�C���^�Z�v�^�̔z��
        //�������e�F ���o���������e�̔z��
        //������ʁF 1020�i�U�֒����i�a���������I���b�N�X�N���W�b�g�j�j
        //�]�͍X�V�t���O�F false
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                new Object[]{l_slrepayUpdateInterceptor},
                new Object[]{l_aioNewOrderSpec},
                OrderTypeEnum.TO_ORIX_CREDIT,
                false);
        if (!l_tradingPowerResult.isResultFlg())
        {
            //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B
            log.debug("����]�̓`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[�B");
        }

        //createResponse( )
        WEB3SLRepayApplyConfirmResponse l_confirmResponse =
            (WEB3SLRepayApplyConfirmResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //���X�|���X.�ԍϊz = ���N�G�X�g�f�[�^.�ԍϊz
        l_confirmResponse.repayAmt = l_request.repayAmt;

        //���X�|���X.�ԍϗ\��� = ���N�G�X�g�f�[�^.�ԍϗ\���
        l_confirmResponse.repayScheduledDate = l_request.repayScheduledDate;

        log.exiting(STR_METHOD_NAME);
        return l_confirmResponse;
    }

    /**
     * (submit����)<BR>
     * �،��S�ۃ��[���ԍϐ\���̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��S�ۃ��[���ԍϐ\���jsubmit�����v�Q�ƁB<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jsubmit����<BR>
     * ��̈ʒu�Fis�،��S�ۃ��[�������J��( )<BR>
     * �@@�߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jsubmit����<BR>
     * ��̈ʒu�Fget���ߐU����(SubAccount, Date)<BR>
     * �@@get���ߐU����()�̖߂�l > ���N�G�X�g�f�[�^.�ԍϗ\��� �̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02915<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jsubmit����<BR>
     * ��̈ʒu�Fvalidate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],<BR>
     * �@@�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)<BR>
     * �@@�@@�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_01306<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\���jsubmit����<BR>
     * ��̈ʒu�FgetOrderUnits(�����h�c : long)<BR>
     * �@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3SystemLayerException<BR>
     * �@@�@@�@@�@@tag: SYSTEM_ERROR_80005<BR>
     * ========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayApplyCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3SLRepayApplyCompleteResponse submitOrder(WEB3SLRepayApplyCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SLRepayApplyCompleteRequest)";
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

        //validate( )
        l_request.validate();

        //get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        //[����]
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //validate����(SubAccount)
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.validateOrder(l_subAccount);

        //getMainAccount( )
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�،��S�ۃ��[�������J��( )
        boolean l_blnIsSecuredLoanAccountOpen = l_gentradeMainAccount.isSecuredLoanAccountOpen();
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            //�߂�l == false �̏ꍇ�A��O���X���[����B
            log.debug("�،��S�ۃ��[�����������J�݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + STR_METHOD_NAME,
                "�،��S�ۃ��[�����������J�݂ł��B");
        }

        //get������( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get���ߐU����(SubAccount, Date)
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        //�������F get������()�̖߂�l
        Date l_datClosestTransferDate =
            l_aioOrderManager.getClosestTransferDate(
                l_subAccount,
                l_datOrderBizDate);

        if (WEB3DateUtility.compare(l_datClosestTransferDate, l_request.repayScheduledDate) > 0)
        {
            //get���ߐU����()�̖߂�l > ���N�G�X�g�f�[�^.�ԍϗ\��� �̏ꍇ�A��O���X���[����B
            log.debug("���ߐU�����͕ԍϗ\������傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02915,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ߐU�����͕ԍϗ\������傫���ł��B");
        }

        //validateSL�ԍϏd������(SubAccount, Date)
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        //��n���F ���N�G�X�g�f�[�^.�ԍϗ\���
        l_aioOrderManager.validateSLRepayDuplicateOrder(l_subAccount, l_request.repayScheduledDate);

        //get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //get���iID(Institution)
        //[����]
        //�،���ЁF �⏕����.getInstitution()�̖߂�l
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());

        //���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, long, double, String, Date, String, Long)
        //[����]
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l
        //������ʁF 1020�i�U�֒����i�a���������I���b�N�X�N���W�b�g�j�j
        //�U�փ^�C�v�F 2�i�o���j
        //���iID�F get���iID()�̖߂�l
        //���z�F ���N�G�X�g�f�[�^.�ԍϊz
        //�L�q�F null
        //�U�֗\����F ���N�G�X�g�f�[�^.�ԍϗ\���
        //���ϋ@@��ID�F null
        //����ID�F null
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.TO_ORIX_CREDIT,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                Double.parseDouble(l_request.repayAmt),
                null,
                l_request.repayScheduledDate,
                null,
                null);

        //�،��S�ۃ��[���ԍύX�V�C���^�Z�v�^(���o���������e)
        //[����]
        //���o���������e�F ���o���������e
        WEB3AioSLRepayUpdateInterceptor l_slrepayUpdateInterceptor =
            new WEB3AioSLRepayUpdateInterceptor(l_aioNewOrderSpec);

        //�v���p�e�B�Z�b�g
        //�C���^�Z�v�^.������ = �i�ȉ��̂Ƃ���j
        //    get������()�̖߂�l = ���o���������e.�U�֗\��� or
        //    get������()�̖߂�l�̗��c�Ɠ� = ���o���������e.�U�֗\��� �̏ꍇ�Aget������()�̖߂�l
        //    ����ȊO�̏ꍇ�A���o���������e.�U�֗\����̑O�c�Ɠ�
        Date l_datEstimatedTransferDate = l_aioNewOrderSpec.getEstimatedTransferDate();
        WEB3GentradeBizDate l_gentradeOrderBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        if (WEB3DateUtility.compare(l_datOrderBizDate, l_datEstimatedTransferDate) == 0
            || WEB3DateUtility.compare(l_gentradeOrderBizDate.roll(1), l_datEstimatedTransferDate) == 0)
        {
            l_slrepayUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
        }
        else
        {
            WEB3GentradeBizDate l_gentradeTransferDate =
                new WEB3GentradeBizDate(new Timestamp(l_datEstimatedTransferDate.getTime()));
            l_slrepayUpdateInterceptor.setOrderBizDate(l_gentradeTransferDate.roll(-1));
        }

        //�C���^�Z�v�^.��n�� = ���o���������e.�U�֗\���
        l_slrepayUpdateInterceptor.setDeliveryDate(l_datEstimatedTransferDate);

        //validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],
        //  �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        //[����]
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g
        //�������e�C���^�Z�v�^�F �،��S�ۃ��[���ԍύX�V�C���^�Z�v�^�̔z��
        //�������e�F ���o���������e�̔z��
        //������ʁF 1020�i�U�֒����i�a���������I���b�N�X�N���W�b�g�j�j
        //�]�͍X�V�t���O�F true
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                new Object[]{l_slrepayUpdateInterceptor},
                new Object[]{l_aioNewOrderSpec},
                OrderTypeEnum.TO_ORIX_CREDIT,
                true);
        if (!l_tradingPowerResult.isResultFlg())
        {
            //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[����B
            log.debug("����]�̓`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[�B");
        }

        //createNewOrderId( )
        long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();

        //setThreadLocalPersistenceEventInterceptor(
        //�،��S�ۃ��[���ԍύX�V�C���^�Z�v�^ : AioOrderManagerPersistenceEventInterceptor)
        //[����]
        // �،��S�ۃ��[���ԍύX�V�C���^�Z�v�^�F�@@���������،��S�ۃ��[���ԍύX�V�C���^�Z�v�^
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_slrepayUpdateInterceptor);

        //submitNewOrder(�⏕���� : SubAccount, ���i�^�C�v : ProductTypeEnum,
        //���o���������e : NewOrderSpec, �����h�c : long, �p�X���[�h : String, isSkip�����R�� : boolean)
        //[����]
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g
        //���i�^�C�v�F�@@5�i�����j
        //���o���������e�F�@@���o���������e�I�u�W�F�N�g
        //�����h�c�F�@@createNewOrderId( )�̖߂�l
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        //isSkip�����R���F�@@true
        OrderSubmissionResult l_submissionResult =
            l_aioOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec,
                l_lngNewOrderId,
                l_request.password,
                true);

        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����o�^�������s�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "�����o�^�������s�ł���");
        }

        //getOrderUnits(�����h�c : long)
        //[����]
        //�����h�c�F�@@createNewOrderId( )�̖߂�l
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(l_lngNewOrderId);
        if (l_orderUnits.length <= 0)
        {
            //�擾�ł��Ȃ������ꍇ�A��O���X���[����B
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        AioOrderUnitRow l_aioOrderUnitRow =
            (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();

        //createResponse( )
        WEB3SLRepayApplyCompleteResponse l_completeResponse =
            (WEB3SLRepayApplyCompleteResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //���X�|���X.�ԍϊz = ���N�G�X�g�f�[�^.�ԍϊz
        l_completeResponse.repayAmt = l_request.repayAmt;

        //���X�|���X.�ԍϗ\��� = ���N�G�X�g�f�[�^.�ԍϗ\���
        l_completeResponse.repayScheduledDate = l_request.repayScheduledDate;

        //���X�|���X.�X�V���� = �����P��.�X�V���t
        l_completeResponse.lastUpdatedTimestamp =
            l_aioOrderUnitRow.getLastUpdatedTimestamp();

        //���X�|���X.����ID = createNewOrderId( )�̖߂�l �̖߂�l
        l_completeResponse.orderId = l_lngNewOrderId + "";

        log.exiting(STR_METHOD_NAME);
        return l_completeResponse;
    }
}
@
