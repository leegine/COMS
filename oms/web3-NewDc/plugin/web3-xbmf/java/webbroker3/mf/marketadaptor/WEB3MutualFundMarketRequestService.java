head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundMarketRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�s�ꃊ�N�G�X�g���M�T�[�r�X�N���X(WEB3MutualFundMarketRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 �����(���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2004/12/09 ������ (���u) �c�Ή�
Revesion History : 2006/05/24 ��O�� (���u) �c�a�X�V�d�l No.075
Revesion History : 2006/10/12 ���� (���u) ���f�� 498
Revesion History : 2006/10/25  �����F (���u) ���f�� 511 �c�a�X�V�d�l 084
Revesion History : 2006/11/24 ���{ (SRA) �c�a�X�V�d�l 086
Revesion History : 2007/02/05  �����F (���u) ���f�� 533
Revesion History : 2007/04/10 ��іQ (���u) ���f�� 559 �c�a�X�V�d�l 091
Revesion History : 2007/06/19 �L���E�ĕ� (���u) ���f�� 568 �c�a�X�V�d�l 092
*/

package webbroker3.mf.marketadaptor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrder;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.market.messages.MutualFundNewOrderMarketRequestMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3ExecuteDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PrDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3SpecifyDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SwtDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.HostFrgnMmfOrderParams;
import webbroker3.mf.data.HostFrgnMmfOrderRow;
import webbroker3.mf.data.HostXbmfOrderCancelParams;
import webbroker3.mf.data.HostXbmfOrderParams;
import webbroker3.mf.data.HostXbmfOrderRow;
import webbroker3.mf.define.WEB3MFBuyDivDef;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MFSettlementDivDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�s�ꃊ�N�G�X�g���M�T�[�r�X�N���X�B<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3MutualFundMarketRequestService implements MutualFundMarketRequestSenderService
{

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundMarketRequestService.class);

    /**
     * (���M�s�ꃊ�N�G�X�g���M�T�[�r�X)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 40B5BBBA0196
     */
    public WEB3MutualFundMarketRequestService()
    {

    }

    /**
     * (��������s�ꃊ�N�G�X�g���b�Z�[�W���M)<BR>
     * �isend�̎����j<BR>
     * <BR>
     * �w��̎�������s�ꃊ�N�G�X�g���b�Z�[�W���s��֑��M����B<BR>
     * <BR>
     * �P�j�@@�����擾<BR>
     * �@@�|��������s�ꃊ�N�G�X�g���b�Z�[�W.getOrderId() �ɂĒ���ID���擾����B<BR>
     * �@@�|�g�����M�����}�l�[�W��.getOrder()���R�[�����āA���M�����I�u�W�F�N�g���擾���E
     *
     * �B<BR>
     * �@@�@@�mgetOrder�ɓn���p�����^�n<BR>
     * �@@�@@�@@����ID�F �擾��������ID<BR>
     * �@@�|�擾�������M�����I�u�W�F�N�g.getOrderUnits()���R�[�����A<BR>
     * ���M�����P�ʃI�u�W�F�N�g�̔z����擾����B<BR>
     * <BR>
     * �Q�j�@@��������s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     * �Q�|�P�j�@@is�s�ꖢ���M�̒l��true�̏ꍇ<BR>
     * �@@�|��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i�s�ꑗ�M�Ȃ��j()���R�[������B<BR>
     * �@@�m��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i�s�ꑗ�M�Ȃ��j�ɓn���p�����^�n<BR>
     * �@@�@@���M�����P�ʁF �擾�������M�����P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * �@@�@@�⏕�����F ��������s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()�̖߂�l<BR>
     * �@@�i�V�[�P���X�}�u�i���M�s�ꃊ�N�G�X�g�j����������M�i�s�ꑗ�M�Ȃ��j�v���Q�Ɓj<BR>
     * <BR>
     * �Q�|�Q�j�@@����.is�s�ꖢ���M�̒l��false�̏ꍇ<BR>
     * �@@�|��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i�s�ꑗ�M����j()���R�[������B<BR>
     * �@@�m��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i�s�ꑗ�M����j�ɓn���p�����^�n<BR>
     * �@@�@@���M�����P�ʁF �擾�������M�����P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * �@@�@@�⏕�����F ��������s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()�̖߂�l<BR>
     * �@@�i�V�[�P���X�}�u�i���M�s�ꃊ�N�G�X�g�j����������M�i�s�ꑗ�M����j�v���Q�Ɓj<BR>
     * <BR>
     * �Q�|�R�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h������I�������ꍇ�A<BR>
     * �@@DefaultMarketRequestSendResult.newSuccessResultInstance()�̖߂�l��Ԃ��B<BR>
     * �@@�mnewSuccessResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@���b�Z�[�W�g�[�N��ID�F 0<BR>
     * <BR>
     * �Q�|�S�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h����O���X���[�����ꍇ
     * <BR>
     * �@@�|ProcessingResult.newFailedResultInstance()���R�[������<BR>
     * ProcessingResult�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�mnewFailedResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@�@@�G���[���F ��O�I�u�W�F�N�g.getErrorInfo()�̖߂�l<BR>
     * �@@�|DefaultMarketRequestSendResult.newFailedResultInstance()�̖߂�l��Ԃ��B<BR>
     * �@@�@@�mnewFailedResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@�@@�I�y���[�V�������ʁF ��������ProcessingResult�I�u�W�F�N�g<BR>
     * @@param l_marketRequest - ��������s�ꃊ�N�G�X�g���b�Z�[�W<BR>
     * @@param l_blnIsMarketNotSendMessage - (is�s�ꖢ���M)<BR>
     * �������s��ɖ����M�ő��M�T�[�r�X�Ƀ��[�J�����������悤�ʒm����ꍇ��
     * true ���w�肷��B<BR>
     * false�Ɏw��̏ꍇ�͎s��֎�����b�Z�[�W�𑗐M����B<BR>
     *
     * @@return MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 40B5BBBA01B5
     */
    public MarketRequestSendResult send(
        CancelOrderMarketRequestMessage l_marketRequest,
        boolean l_blnIsMarketNotSendMessage)
    {
        final String l_strMethodName = "send("
            + "CancelOrderMarketRequestMessage l_marketRequest, "
            + "boolean l_blnIsMarketNotSendMessage)";
        log.entering(l_strMethodName);

        if (l_marketRequest == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // �P�j�@@�����擾
        // �|��������s�ꃊ�N�G�X�g���b�Z�[�W.getOrderId() �ɂĒ���ID���擾����B
        long l_lngOrderId = l_marketRequest.getOrderId();
        // �|�g�����M�����}�l�[�W��.getOrder()���R�[�����āA���M�����I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundOrderManager l_mfOrderManager =
            (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
        try
        {
            MutualFundOrder l_mfOrder =
                (MutualFundOrder)l_mfOrderManager.getOrder(l_lngOrderId);
            // �|�擾�������M�����I�u�W�F�N�g.getOrderUnits()���R�[�����A���M�����P�ʃI�u�W�F�N�g�̔z����擾����B
            OrderUnit[] l_orderUnits = l_mfOrder.getOrderUnits();
            MutualFundOrderUnit[] l_mfOrderUnits = new MutualFundOrderUnit[l_orderUnits.length];
            for (int i = 0; i < l_orderUnits.length; i ++)
            {
                l_mfOrderUnits[i] = (MutualFundOrderUnit)l_orderUnits[i];
            }
            // �Q�j�@@��������s�ꃊ�N�G�X�g���b�Z�[�W���M
            // �Q�|�P�j�@@is�s�ꖢ���M�̒l��true�̏ꍇ
            if (l_blnIsMarketNotSendMessage)
            {
                // �|��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i�s�ꑗ�M�Ȃ��j()���R�[������B
                this.cancelOrderMarketRequestSendMessageNotSubmit(
                    l_mfOrderUnits[0],
                    l_marketRequest.getSubAccount());
            }
            // �Q�|�Q�j�@@����.is�s�ꖢ���M�̒l��false�̏ꍇ
            else
            {
                // �|��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i�s�ꑗ�M����j()���R�[������B
                this.cancelOrderMarketRequestSendMessageSubmit(
                    l_mfOrderUnits[0],
                    l_marketRequest.getSubAccount());
            }
            // �Q�|�R�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h������I�������ꍇ�A
            // DefaultMarketRequestSendResult.newSuccessResultInstance()�̖߂�l��Ԃ��B
            MarketRequestSendResult l_sendResult = DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
            return l_sendResult;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y�����铊�M�����P�ʃI�u�W�F�N�g�܂��͓��M�����I�u�W�F�N�g������܂��� for OrderId = "
                + l_lngOrderId);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (WEB3BaseException l_ex)
        {
            // �Q�|�S�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h����O���X���[�����ꍇ
            // �|ProcessingResult.newFailedResultInstance()���R�[������ProcessingResult�I�u�W�F�N�g�𐶐�����B
            // �|DefaultMarketRequestSendResult.newFailedResultInstance()�̖߂�l��Ԃ��B
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            MarketRequestSendResult l_returnResult = DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }

    /**
     * (�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M)<BR>
     * �isend(MutualFundNewOrderMarketRequestMessage)�̎����j<BR>
     * <BR>
     * �w��̓��M�����}�[�P�b�g���N�G�X�g���b�Z�[�W���s��֑��M����B<BR>
     * <BR>
     * �P�j�@@�����擾<BR>
     * �@@�|���M�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W.getOrderUnitId()
     * �ɂĒ����P��ID���擾����B<BR>
     * �@@�|�g�����M�����}�l�[�W��.getOrderUnit()���R�[�����āA���M�����P�ʃI�u�W�F�N�g�E
     * �擾����B<BR>
     * �@@�@@�mgetOrderUnit�ɓn���p�����^�n<BR>
     * �@@�@@�@@�����P��ID�F �擾���������P��ID<BR>
     * <BR>
     * �Q�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     * �Q�|�P�j�@@�擾�������M�����P��.getDataSourceObject().get�����o�H�敪()�̖߂�l��
     * <BR>
     * �@@WEB3OrderRootDivDef.HOST�Ɠ������Ȃ��ꍇ�i��Web�V���͒����j<BR>
     * <BR>
     * �@@�|�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M()���R�[������B<BR>
     * �@@�m�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�E���E�抷�j�ɓn���p�����^�n<BR>
     * �@@�@@���M�����P�ʁF �擾�������M�����P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�⏕�����F ���M�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()�̖߂�l<BR>
     * �@@�@@�i�V�[�P���X�}�u�i���M�s�ꃊ�N�G�X�g�j�V�K�������M�i���t�E���E�抷�j�v���Q
     * �Ɓj<BR>
     * <BR>
     * �Q�|�Q�j�@@�擾�������M�����P��.getDataSourceObject().get�����o�H�敪()�̖߂�l��
     * <BR>
     * �@@WEB3OrderRootDivDef.HOST�Ɠ������ꍇ�i��SONAR���͒����j<BR>
     * <BR>
     * �@@�|�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���������ʒm�j()���R�[������B<BR>
     * �@@�@@�m�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���������ʒm�j�ɓn���p�����^�n<BR>
     * �@@�@@�@@���M�����P�ʁF �擾�������M�����P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�i�V�[�P���X�}�u�i���M�s�ꃊ�N�G�X�g�j�V�K�������M�i���������ʒm�j�v���Q�Ɓj
     * <BR>
     * �Q�|�R�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h������I�������ꍇ�A<BR>
     * �@@DefaultMarketRequestSendResult.newSuccessResultInstance()�̖߂�l��Ԃ��B<BR>
     * �@@�mnewSuccessResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@���b�Z�[�W�g�[�N��ID�F 0<BR>
     * <BR>
     * �Q�|�S�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h����O���X���[�����ꍇ
     * <BR>
     * �@@�|ProcessingResult.newFailedResultInstance()���R�[������<BR>
     * ProcessingResult�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�mnewFailedResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@�@@�G���[���F ��O�I�u�W�F�N�g.getErrorInfo()�̖߂�l<BR>
     * �@@�|DefaultMarketRequestSendResult.newFailedResultInstance()�̖߂�l��Ԃ��B<BR>
     * �@@�@@�mnewFailedResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@�@@�I�y���[�V�������ʁF ��������ProcessingResult�I�u�W�F�N�g<BR>
     * @@param l_marketRequest - ���M�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@roseuid 40B5BBBA01D4
     */
    public MarketRequestSendResult send(
        MutualFundNewOrderMarketRequestMessage l_marketRequest)
    {
        final String l_strMethodName = "send("
            + "MutualFundNewOrderMarketRequestMessage "
            + "l_mutualFundNewOrderMarketRequestMessage)";
        log.entering(l_strMethodName);

        if (l_marketRequest == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // �P�j�@@�����擾
        // �|���M�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W.getOrderUnitId() �ɂĒ����P��ID���擾����B
        long l_lngOrderUnitId = l_marketRequest.getOrderUnitId();
        // �|�g�����M�����}�l�[�W��.getOrderUnit()���R�[�����āA���M�����P�ʃI�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundOrderManager l_mfOrderManager =
            (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
        try
        {
            MutualFundOrderUnit l_mfOrderUnit = (MutualFundOrderUnit)l_mfOrderManager.getOrderUnit(l_lngOrderUnitId);
            // �Q�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M
            // �Q�|�P�j�@@�擾�������M�����P��.getDataSourceObject().get�����o�H�敪()�̖߂�l��
            //      WEB3OrderRootDivDef.HOST�Ɠ������Ȃ��ꍇ�i��Web�V���͒����j
            String l_strOrderRootDiv =
                ((MutualFundOrderUnitRow)l_mfOrderUnit.getDataSourceObject()).getOrderRootDiv();
            if (!WEB3OrderRootDivDef.HOST.equals(l_strOrderRootDiv))
            {
                // - �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�E���E�抷�j���R�[������B
                this.newOrderMarketRequestSendMessageAcquiredSellSwt(
                    l_mfOrderUnit,
                    l_marketRequest.getSubAccount()
                );
            }
            // �Q�|�Q�j�@@�擾�������M�����P��.getDataSourceObject().get�����o�H�敪()�̖߂�l��
            //      WEB3OrderRootDivDef.HOST�Ɠ������ꍇ�i��SONAR���͒����j
            else
            {
                // �|�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���������ʒm�j()���R�[������B
                this.newOrderMarketRequestSendMessageTradeOrderNotify(l_mfOrderUnit);
            }

            // �Q�|�R�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h������I�������ꍇ�A
            //  DefaultMarketRequestSendResult.newSuccessResultInstance()�̖߂�l��Ԃ��B
            MarketRequestSendResult l_sendResult = 
                DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
            return l_sendResult;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y�����铊�M�����P�ʃI�u�W�F�N�g����܂��� for OrderUnitId = "
                + l_lngOrderUnitId);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (WEB3BaseException l_ex)
        {
            // �Q�|�S�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h����O���X���[�����ꍇ
            // �|ProcessingResult.newFailedResultInstance()���R�[������ProcessingResult�I�u�W�F�N�g�𐶐�����B
            // �|DefaultMarketRequestSendResult.newFailedResultInstance()�̖߂�l��Ԃ��B
            ProcessingResult l_sendResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            MarketRequestSendResult l_returnResult = 
                DefaultMarketRequestSendResult.newFailedResultInstance(l_sendResult);
            return l_returnResult;
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }

    /**
     * (�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�E���E�抷�j)<BR>
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     * <BR>
     * ���t�����A���A�抷�̎s�ꃊ�N�G�X�g���b�Z�[�W�𑗐M����B<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�C���T�[�g<BR>
     * �@@����.���M�����P�ʂ̓��e�����ƂɁA���M�����L���[�e�[�u<BR>
     * �@@�ɒ����f�[�^��}������B<BR>
     * �@@(*) ����.���M�����P��.getOrderType()�̖߂�l��<BR>
     * �@@�@@OrderTypeEnum.�����M���������̏ꍇ�́ADB�X�V�d�l�u�����M�����t_���M����<BR>
     * �@@�@@�L���[�e�[�u��.xls�v�Q�ƁB<BR>
     * �@@(*) ����.���M�����P��.getOrderType()�̖߂�l��<BR>
     * �@@�@@OrderTypeEnum.�����M���������ł���<BR>
     * �@@�@@����.���M�����P��.getDataSourceObject().get�����R�[�h�i�抷��j()<BR>
     * �@@�@@�̖߂�l��null�̏ꍇ�́ADB�X�V�d�l�u�����M�����_���M�����L���[�e�[�u��.xls�E
     * �Q�ƁB<BR>
     * �@@(*) ����.���M�����P��.getOrderType()�̖߂�l��<BR>
     * �@@�@@OrderTypeEnum.�����M���������ł���<BR>
     * �@@�@@����.���M�����P��.getDataSourceObject().get�����R�[�h�i�抷��j()<BR>
     * �@@�@@�̖߂�l��null�łȂ��ꍇ�́ADB�X�V�d�l�u�����M���抷_���M�����L���[�e�[�u��.
     * xls�v�Q�ƁB<BR>
     *   (*) ����.���M�����P��.getOrderType()�̖߂�l�� <BR>
     * �@@�@@OrderTypeEnum.�����M����W�����̏ꍇ�́ADB�X�V�d�l <BR>
     *      �u�����M����W_���M�����L���[�e�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�g���K���s<BR>
     * �@@�|����.���M�����P��.get�󒍓���()�̖߂�l��YYYYMMDD�`���ɕϊ��������̂�<BR>
     * �@@�@@����.���M�����P��.getDataSourceObject().getBizDate()�̖߂�l���r���A<BR>
     * �@@�@@�l���قȂ�ꍇ�͈ȍ~�̏������s��Ȃ��B<BR>
     * <BR>
     * �@@�|�g���K�𔭍s���邩�̃`�F�b�N<BR>
     * �@@�@@���M������ԊǗ�.is�g���K���s()���R�[�����A�߂�l��true�ł���΃g���K��<BR>
     * �@@�@@���s����B�߂�l��false�ł���Έȍ~�̏������s��Ȃ��B<BR>
     * �@@�@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@���������F<BR>
     * �@@�@@�@@�@@�@@�h0 �F DEFAULT�h<BR>
     * <BR>
     * �@@�|WEB3MQMessageSpec�̐���<BR>
     * �@@�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F
     * ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F ���M�����L���[�e�[�u���̃f�[�^�R�[�h�̒l<BR>
     * <BR>
     * �@@�|�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayServiceImpl.send()���R�[�����A�g���K�𔭍s����B<BR>
     * �@@�@@�msend�ɓn���p�����^�n<BR>
     * �@@�@@�@@MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N�F
     * ��������WEB3MQMessageSpec�I�u�W�F�N�g<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B5BFAE03B8
     */
    public void newOrderMarketRequestSendMessageAcquiredSellSwt(
        MutualFundOrderUnit l_mutualFundOrderUnit, SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String l_strMethodName = "newOrderMarketRequestSendMessageAcquiredSellSwt("
            + "MutualFundOrderUnit l_mutualFundOrderUnit, "
            + "SubAccount l_subAccount)";
        log.entering(l_strMethodName);

        if (l_subAccount == null || l_mutualFundOrderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // �P�j�@@�L���[�f�[�^�C���T�[�g
            HostFrgnMmfOrderParams l_hostFrgnMmfOrderParams =
                new HostFrgnMmfOrderParams();
            HostXbmfOrderParams l_hostOrderParams = new HostXbmfOrderParams();

            MutualFundOrderUnitRow l_mfOderUnitRow =
                (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mfProductManager =
                (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            OrderTypeEnum l_orderType = l_mfOderUnitRow.getOrderType();

            //����.���M�����P�ʃI�u�W�F�N�g.���M�^�C�v = "�O��MMF"�̏ꍇ
            if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mfOderUnitRow.getFundType()))
            {
                // ����.���M�����P�ʃI�u�W�F�N�g.getOrderType()�̖߂�l��
                //  OrderTypeEnum.�����M���������̏ꍇ�́A
                //  DB�X�V�d�l�u�����M�����t_�O��MMF�����L���[�e�[�u��.xls�v�Q�ƁB
                if (OrderTypeEnum.MF_BUY.equals(l_orderType))
                {
                    WEB3MutualFundProduct l_mfProduct =
                        (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                            l_mfOderUnitRow.getProductId());

                    //���X���擾����
                    Branch l_banch =
                        l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                    //�،���ЃR�[�h
                    String l_strInstitutionCode =
                        l_banch.getInstitution().getInstitutionCode();
                    //�ڋq�R�[�h
                    String l_strAccountCode =
                        l_accountManager.getMainAccount(
                            l_mfOderUnitRow.getAccountId()).getAccountCode();
                    //DB�X�V�d�l�u�����M�����t_�O��MMF�����L���[�e�[�u��.xls�v�Q�ƁB
                    //�f�[�^�R�[�h  DI821
                    l_hostFrgnMmfOrderParams.setRequestCode(WEB3HostRequestCodeDef.FOREIGN_MMF);
                    //�،���ЃR�[�h ���M�����P��.���XID�ɊY������،���ЃR�[�h
                    l_hostFrgnMmfOrderParams.setInstitutionCode(l_strInstitutionCode);
                    //���X�R�[�h ���M�����P��.���XID�ɊY�����镔�X�R�[�h
                    l_hostFrgnMmfOrderParams.setBranchCode(l_banch.getBranchCode());
                    //�ڋq�R�[�h ���M�����P��.����ID�ɊY������ڋq�R�[�h
                    l_hostFrgnMmfOrderParams.setAccountCode(l_strAccountCode);
                    //���҃R�[�h ���M�����P��.�����ID�ɊY�����鈵�҃R�[�h
                    if (!l_mfOderUnitRow.getTraderIdIsNull())
                    {
                        String l_strTraderCode =
                            l_finObjMgr.getTrader(
                                l_mfOderUnitRow.getTraderId()).getTraderCode();
                        l_hostFrgnMmfOrderParams.setTraderCode(l_strTraderCode);
                    }
                    else
                    {
                        l_hostFrgnMmfOrderParams.setTraderCode(null);
                    }
                    //�����R�[�h ���M�����P��.����ID�ɊY����������R�[�h
                    l_hostFrgnMmfOrderParams.setProductCode(l_mfProduct.getProductCode());
                    //�w�� ���M�����P��.�������ʃ^�C�v�����ʂ̏ꍇ�h1�F�����h���z�Ȃ�΁h2�F���z�h
                    QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                    if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                    {
                        l_hostFrgnMmfOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                    }
                    if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                    {
                        l_hostFrgnMmfOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                    }
                    //������ 0
                    l_hostFrgnMmfOrderParams.setSellOrderQuantity(0);
                    //������ ���M�����P��.��������
                    l_hostFrgnMmfOrderParams.setBuyOrderQuantity((long)l_mfOderUnitRow.getQuantity());
                    //�`�[�� 9 + ���ʃR�[�h�̉�3��
                    l_hostFrgnMmfOrderParams.setTicketNumber(
                        Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));
                    //���t�敪 �u�����N
                    l_hostFrgnMmfOrderParams.setBuyDiv(WEB3MFBuyDivDef.HALF_SPACE);
                    //���� ���M�����P��.���ϋ敪���~�݂̏ꍇ"0:�~�݌���"�O�݂̏ꍇ"1:�O�݌���"
                    if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_mfOderUnitRow.getSettlementDiv()))
                    {
                        l_hostFrgnMmfOrderParams.setSettlementDiv(WEB3MFSettlementDivDef.EN_SETTLE);
                    }
                    else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_mfOderUnitRow.getSettlementDiv()))
                    {
                        l_hostFrgnMmfOrderParams.setSettlementDiv(WEB3MFSettlementDivDef.FOREIGN_SETTLE);
                    }
                    //���s 1�F���s 
                    l_hostFrgnMmfOrderParams.setExecuteDiv(WEB3ExecuteDivDef.EXECUTE);
                    //���ʃR�[�h ���M�����P��.���ʔԍ�
                    l_hostFrgnMmfOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                    //�󒍓� ���M�����P��.�󒍓���
                    l_hostFrgnMmfOrderParams.setCreateDate(l_mfOderUnitRow.getCreatedTimestamp());
                    //�󒍎��� ���M�����P��.�󒍓���
                    l_hostFrgnMmfOrderParams.setCreateTime(l_mfOderUnitRow.getCreatedTimestamp());
                    //������ ���M�����P��.������
                    l_hostFrgnMmfOrderParams.setOrderDate(
                        WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd"));
                    //�����敪 0�F����������
                    l_hostFrgnMmfOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                }
                // OrderTypeEnum.�����M���������̏ꍇ�́A
                // DB�X�V�d�l�u�����M�����_�O��MMF�����L���[�e�[�u��.xls�v�Q�ƁB
                if (OrderTypeEnum.MF_SELL.equals(l_orderType))
                {
                    WEB3MutualFundProduct l_mfProduct =
                        (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                            l_mfOderUnitRow.getProductId());

                    // ���X���擾����
                    Branch l_banch =
                        l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                    //�،���ЃR�[�h
                    String l_strInstitutionCode =
                        l_banch.getInstitution().getInstitutionCode();
                    //�ڋq�R�[�h
                    String l_strAccountCode =
                        l_accountManager.getMainAccount(
                            l_mfOderUnitRow.getAccountId()).getAccountCode();
                    //�����M�����_�O��MMF�����L���[�e�[�u��
                    //�f�[�^�R�[�h  DI821
                    l_hostFrgnMmfOrderParams.setRequestCode(WEB3HostRequestCodeDef.FOREIGN_MMF);
                    //�،���ЃR�[�h ���M�����P��.���XID�ɊY������،���ЃR�[�h
                    l_hostFrgnMmfOrderParams.setInstitutionCode(l_strInstitutionCode);
                    //���X�R�[�h ���M�����P��.���XID�ɊY�����镔�X�R�[�h
                    l_hostFrgnMmfOrderParams.setBranchCode(l_banch.getBranchCode());
                    //�ڋq�R�[�h ���M�����P��.����ID�ɊY������ڋq�R�[�h
                    l_hostFrgnMmfOrderParams.setAccountCode(l_strAccountCode);
                    //���҃R�[�h ���M�����P��.�����ID�ɊY�����鈵�҃R�[�h
                    if (!l_mfOderUnitRow.getTraderIdIsNull())
                    {
                        String l_strTraderCode =
                            l_finObjMgr.getTrader(
                                l_mfOderUnitRow.getTraderId()).getTraderCode();
                        l_hostFrgnMmfOrderParams.setTraderCode(l_strTraderCode);
                    }
                    else
                    {
                        l_hostFrgnMmfOrderParams.setTraderCode(null);
                    }
                    //�����R�[�h ���M�����P��.����ID�ɊY����������R�[�h
                    l_hostFrgnMmfOrderParams.setProductCode(l_mfProduct.getProductCode());
                    //�w�� ���M�����P��.���M���敪�������w��A�S���w��Ȃ�΁h1�F�����h
                    //���z�w��Ȃ�΁h2�F���z�h
                    String l_strSellDiv = l_mfOderUnitRow.getFundSellDiv();
                    if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strSellDiv)
                        || WEB3SellDivDef.ALL_DESIGNATE.equals(l_strSellDiv))
                    {
                        l_hostFrgnMmfOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                    }
                    if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strSellDiv))
                    {
                        l_hostFrgnMmfOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                    }
                    //������ ���M�����P��.��������
                    l_hostFrgnMmfOrderParams.setSellOrderQuantity((long)l_mfOderUnitRow.getQuantity());
                    //������ 0
                    l_hostFrgnMmfOrderParams.setBuyOrderQuantity(0);
                    //�`�[�� 9 + ���ʃR�[�h�̉�3��
                    l_hostFrgnMmfOrderParams.setTicketNumber(
                        Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));
                    //���t�敪 �u�����N
                    l_hostFrgnMmfOrderParams.setBuyDiv(WEB3MFBuyDivDef.HALF_SPACE);
                    //���� ���M�����P��.���ϋ敪���~�݂̏ꍇ"0:�~�݌���"�O�݂̏ꍇ"1:�O�݌���"
                    if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_mfOderUnitRow.getSettlementDiv()))
                    {
                        l_hostFrgnMmfOrderParams.setSettlementDiv(WEB3MFSettlementDivDef.EN_SETTLE);
                    }
                    else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_mfOderUnitRow.getSettlementDiv()))
                    {
                        l_hostFrgnMmfOrderParams.setSettlementDiv(WEB3MFSettlementDivDef.FOREIGN_SETTLE);
                    }
                    //���s 1�F���s
                    l_hostFrgnMmfOrderParams.setExecuteDiv(WEB3ExecuteDivDef.EXECUTE);
                    //���ʃR�[�h ���M�����P��.���ʔԍ�
                    l_hostFrgnMmfOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                    //�󒍓� ���M�����P��.�󒍓���
                    l_hostFrgnMmfOrderParams.setCreateDate(l_mfOderUnitRow.getCreatedTimestamp());
                    //�󒍎��� ���M�����P��.�󒍓���
                    l_hostFrgnMmfOrderParams.setCreateTime(l_mfOderUnitRow.getCreatedTimestamp());
                    //������ ���M�����P��.������
                    l_hostFrgnMmfOrderParams.setOrderDate(
                        WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd"));
                    //�����敪 0�F����������
                    l_hostFrgnMmfOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                }
                l_hostFrgnMmfOrderParams.markAllValuesAsSet();
                Processors.getDefaultProcessor().doInsertQuery(l_hostFrgnMmfOrderParams);
                log.debug("�L���[�f�[�^�C���T�[�g...... with HostFrgnMmfOrderParams = " + l_hostFrgnMmfOrderParams);

                log.exiting(l_strMethodName);
                return;
            }
            
            
            // (*) ����.���M�����P��.getOrderType()�̖߂�l��
            // �@@OrderTypeEnum.�����M���������̏ꍇ�́ADB�X�V�d�l�u�����M�����t_���M�����L���[�e�[�u��.xls�v�Q�ƁB

            if (OrderTypeEnum.MF_BUY.equals(l_orderType))
            {
                WEB3MutualFundProduct l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mfOderUnitRow.getProductId());
                
                //�E���M����.is�O�����M()==false or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h == �h�~�h�j �̏ꍇ�A
                //"CI801"���Z�b�g                
                boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
                boolean l_blnIsFWF = l_mfProduct.isFWF();
                String l_strCruuencyCode = l_mfProduct.getCurrencyCode();
                
                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_DOMESTIC_ORDER;
                    l_hostOrderParams.setRequestCode(l_strRequestCode);
                }
                //�E���M����.is�O�����M()==true or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h != �h�~�h�j �̏ꍇ�A
                //"CI803"���Z�b�g
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {                
                    String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER;
                    l_hostOrderParams.setRequestCode(l_strRequestCode); 
                }
                
                // ���X���擾����
                Branch l_banch =
                    l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                // ���X�R�[�h
                l_hostOrderParams.setBranchCode(l_banch.getBranchCode());
                // �،���ЃR�[�h
                String l_strInstitutionCode =
                    l_banch.getInstitution().getInstitutionCode();
                l_hostOrderParams.setInstitutionCode(l_strInstitutionCode);
                // �ڋq�R�[�h
                String l_strAccountCode =
                    l_accountManager.getMainAccount(
                        l_mfOderUnitRow.getAccountId()).getAccountCode();
                l_hostOrderParams.setAccountCode(l_strAccountCode);
                // ���҃R�[�h
                if (!l_mfOderUnitRow.getTraderIdIsNull())
                {
                    String l_strTraderCode =
                        l_finObjMgr.getTrader(
                            l_mfOderUnitRow.getTraderId()).getTraderCode();
                    l_hostOrderParams.setTraderCode(l_strTraderCode);
                }
                else
                {
                    l_hostOrderParams.setTraderCode(null);
                }
                // �����R�[�h
                l_hostOrderParams.setProductCode(l_mfProduct.getProductCode());
                // �w��
                QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                }
                if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                }
                // ����
                l_hostOrderParams.setSettlementDiv(l_mfOderUnitRow.getSettlementDiv());
                // ������
                l_hostOrderParams.setSellOrderQuantity(0);
                
                // ������                
                //���j���M�����́A���M�����P��.����ID�ɊY�����铊�M����                
                String l_strInputUnit = l_mfProduct.getInputUnit();
                BigDecimal l_bdBuyOrderQuantity = new BigDecimal("0");
                BigDecimal l_bdCalcNumber1 = new BigDecimal ("1000");
                BigDecimal l_bdCalcNumber2 = new BigDecimal ("10000");
                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_mfOderUnitRow.getQuantity()));
                
                //�E���M����.is�O�����M()==true or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h != �h�~�h�j �̏ꍇ�A
                
                if (l_blnIsForeignFund ||
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    //�E���M����.���͒P�ʂ��h1�F1�h�ł����
                    //    ���M�����P��.�������ʁ@@�~�@@1000
                    if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                    {
                        l_bdBuyOrderQuantity = l_bdQuantity.multiply(l_bdCalcNumber1);
                        
                        l_hostOrderParams.setBuyOrderQuantity(l_bdBuyOrderQuantity.longValue());
                    }
                    //�E���M����.���͒P�ʂ��h2�F1���h�ł����
                    //    ���M�����P��.�������� / 10000�@@�~�@@1000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                    {
                        l_bdBuyOrderQuantity =
                            l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP).multiply(
                                l_bdCalcNumber1);
                                
                        l_hostOrderParams.setBuyOrderQuantity(l_bdBuyOrderQuantity.longValue());
                    }
                }
                //�E����ȊO�̏ꍇ
                else
                {
                    //�E���M����.���͒P�ʂ��h1�F1�h�ł����
                    //�@@�@@���M�����P��.��������
                    if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                    {
                        l_hostOrderParams.setBuyOrderQuantity(l_bdQuantity.longValue());
                    }
                    //�E���M����.���͒P�ʂ��h2�F1���h�ł����
                    //�@@�@@���M�����P��.�������� / 10000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                    {
                        l_bdBuyOrderQuantity =
                            l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP);
                            
                        l_hostOrderParams.setBuyOrderQuantity(l_bdBuyOrderQuantity.longValue());
                    }               
                }
                
                // �`�[No.
                l_hostOrderParams.setTicketNumber(
                    Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));
                
                // �d�@@�敪
                //�E���M����.is�O�����M()==false or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h == �h�~�h�j �̏ꍇ�A
                // �h1�F�{���h���Z�b�g�B
                //�E���M����.is�O�����M()==true or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h != �h�~�h�j �̏ꍇ�A
                // "��"���Z�b�g�B
                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(WEB3PrDivDef.ISSUE_TICKET);
                }
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(" ");
                }
                // ���萔���敪
                l_hostOrderParams.setCommissionDiv(l_mfOderUnitRow.getNoContractCommissionDiv());

                // �a��`�F�b�N
                l_hostOrderParams.setDepositCheckDiv(" ");
                // ���ʃR�[�h
                l_hostOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                // �󒍓�/�󒍎���
                Date l_orderDate =
                    l_mfOderUnitRow.getReceivedDateTime();
                l_hostOrderParams.setCreateDate(l_orderDate);
                l_hostOrderParams.setCreateTime(l_orderDate);
                // ������
                Date l_bizDate = WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd");
                l_hostOrderParams.setOrderDate(l_bizDate);
                // �抷�敪
                l_hostOrderParams.setSwtDiv(null);
                // ���t�����R�[�h
                l_hostOrderParams.setSwtProductCode(null);
                // ��������敪
                TaxTypeEnum l_taxType = l_mfOderUnitRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                // ��������敪�i�抷��j
                l_hostOrderParams.setSwtTaxType(null);
                // �����敪
                l_hostOrderParams.setClaimDiv(" ");
                // �����`���l��
                l_hostOrderParams.setOrderChanel(l_mfOderUnitRow.getOrderChanel());
                // �����敪 = �h0�F���������� �h
                l_hostOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                // ������ = NULL
                l_hostOrderParams.setPaymentDate(null);
            }
            
            // (*) ����.���M�����P��.getOrderType()�̖߂�l��
            // �@@OrderTypeEnum.�����M���������̏ꍇ�́A
            //   DB�X�V�d�l�u�����M�����_���M�����L���[�e�[�u��.xls�v�Q�ƁB            
            if (OrderTypeEnum.MF_SELL.equals(l_orderType))
            {
                WEB3MutualFundProduct l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mfOderUnitRow.getProductId());
                
                //�f�[�^�R�[�h
                //�E���M����.is�O�����M()==false or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h == �h�~�h�j �̏ꍇ�A
                //"CI801"���Z�b�g

                boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
                boolean l_blnIsFWF = l_mfProduct.isFWF();
                String l_strCruuencyCode = l_mfProduct.getCurrencyCode();
                
                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_DOMESTIC_ORDER;
                    l_hostOrderParams.setRequestCode(l_strRequestCode);
                }
                //�E���M����.is�O�����M()==true or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h != �h�~�h�j �̏ꍇ�A
                //"CI803"���Z�b�g
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {                
					String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER;
					l_hostOrderParams.setRequestCode(l_strRequestCode);	
				}
                
                // ���X���擾����
                Branch l_banch =
                    l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                // ���X�R�[�h
                l_hostOrderParams.setBranchCode(l_banch.getBranchCode());
                // �،���ЃR�[�h
                String l_strInstitutionCode =
                    l_banch.getInstitution().getInstitutionCode();
                l_hostOrderParams.setInstitutionCode(l_strInstitutionCode);
                // �ڋq�R�[�h
                String l_strAccountCode =
                    l_accountManager.getMainAccount(
                        l_mfOderUnitRow.getAccountId()).getAccountCode();
                l_hostOrderParams.setAccountCode(l_strAccountCode);
                // ���҃R�[�h
                if (!l_mfOderUnitRow.getTraderIdIsNull())
                {
                    String l_strTraderCode =
                        l_finObjMgr.getTrader(
                            l_mfOderUnitRow.getTraderId()).getTraderCode();
                    l_hostOrderParams.setTraderCode(l_strTraderCode);
                }
                else
                {
                    l_hostOrderParams.setTraderCode(null);
                }
                // �����R�[�h
                l_hostOrderParams.setProductCode(l_mfProduct.getProductCode());
                // �w��
                if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_mfOderUnitRow.getFundSellDiv())
                    && l_mfOderUnitRow.getQuantity() == 0)
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.ALL);
                }
                else
                {
                    QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                    if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                    {
                        l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                    }
                    if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                    {
                        l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                    }
                }
                // ����
                l_hostOrderParams.setSettlementDiv(l_mfOderUnitRow.getSettlementDiv());
                
                // ������

                //���j���M�����́A���M�����P��.����ID�ɊY�����铊�M����                
                String l_strInputUnit = l_mfProduct.getInputUnit();
                BigDecimal l_bdSellOrderQuantity = new BigDecimal("0");
                BigDecimal l_bdCalcNumber1 = new BigDecimal ("1000");
                BigDecimal l_bdCalcNumber2 = new BigDecimal ("10000");
                BigDecimal l_bdQuantity = new BigDecimal (Double.toString(l_mfOderUnitRow.getQuantity()));
                
                //�E���M����.is�O�����M()==true or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h != �h�~�h�j �̏ꍇ�A
                
                if (l_blnIsForeignFund ||
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    //  �E���M����.���͒P�ʂ��h1�F1�h�ł����
                    //      ���M�����P��.�������ʁ@@�~�@@1000
                    if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                    {
                        l_bdSellOrderQuantity = l_bdQuantity.multiply(l_bdCalcNumber1);
                        
                        l_hostOrderParams.setSellOrderQuantity(l_bdSellOrderQuantity.longValue());
                    }
                    //  �E���M����.���͒P�ʂ��h2�F1���h�ł����
                    //      ���M�����P��.�������� / 10000�@@�~�@@1000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                    {
                        l_bdSellOrderQuantity =
                            l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP).multiply(
                                l_bdCalcNumber1);
                                
                        l_hostOrderParams.setSellOrderQuantity(l_bdSellOrderQuantity.longValue());
                    }
                }
                //�E����ȊO�̏ꍇ
                else
                {
                    //  �E���M����.���͒P�ʂ��h1�F1�h�ł����
                    //      ���M�����P��.��������
                    if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                    {
                        l_hostOrderParams.setSellOrderQuantity(l_bdQuantity.longValue());
                    }
                    //  �E���M����.���͒P�ʂ��h2�F1���h�ł����
                    //      ���M�����P��.�������� / 10000
                    else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                    {
                        l_bdSellOrderQuantity =
                            l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP);
                              
                        l_hostOrderParams.setSellOrderQuantity(l_bdSellOrderQuantity.longValue());
                    }
                }
                
                // ������
                l_hostOrderParams.setBuyOrderQuantity(0);

                // �`�[No.
                l_hostOrderParams.setTicketNumber(
                    Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));

                // �d�@@�敪
                //�E���M����.is�O�����M()==false or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h == �h�~�h�j �̏ꍇ�A
                // �h1�F�{���h���Z�b�g�B
                //�E���M����.is�O�����M()==true or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h != �h�~�h�j �̏ꍇ�A
                // "��"���Z�b�g�B
                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(WEB3PrDivDef.ISSUE_TICKET);
                }
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(" ");
                }
                // ���萔���敪
                l_hostOrderParams.setCommissionDiv(l_mfOderUnitRow.getNoContractCommissionDiv());

                // �a��`�F�b�N = ��
                l_hostOrderParams.setDepositCheckDiv(" ");
                
                // ���ʃR�[�h
                l_hostOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                // �󒍓�/�󒍎���
                Date l_orderDate =
                    l_mfOderUnitRow.getReceivedDateTime();
                l_hostOrderParams.setCreateDate(l_orderDate);
                l_hostOrderParams.setCreateTime(l_orderDate);
                // ������
                Date l_bizDate = WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd");
                l_hostOrderParams.setOrderDate(l_bizDate);
                // �抷�敪
                l_hostOrderParams.setSwtDiv(null);
                // ���t�����R�[�h
                l_hostOrderParams.setSwtProductCode(null);
                // ��������敪
                TaxTypeEnum l_taxType = l_mfOderUnitRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                // ��������敪�i�抷��j
                l_hostOrderParams.setSwtTaxType(null);
                
                // �����敪
                //�E���M����.isFWF() == true and ���M����.�ʉ݃R�[�h == �h�~�h�̏ꍇ
                //�h��񐿋��h
                if (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode))
                {
                    l_hostOrderParams.setClaimDiv(WEB3ClaimDivDef.SELL);
                }
                //�E����ȊO�̏ꍇ
                //    ���M�����P��.�����敪
                else
                {
                    l_hostOrderParams.setClaimDiv(l_mfOderUnitRow.getRequestDiv());
                }

                // �����`���l��
                l_hostOrderParams.setOrderChanel(l_mfOderUnitRow.getOrderChanel());
                // �����敪 = �h0�F���������� �h
                l_hostOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                //������ = NULL
                l_hostOrderParams.setPaymentDate(null);                    
            }
            
            // (*) ����.���M�����P��.getOrderType()�̖߂�l��
            // OrderTypeEnum.�����M���抷�����̏ꍇ�́A
            // DB�X�V�d�l�u�����M���抷_���M�����L���[�e�[�u��.xls�v�Q�ƁB
            if (OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
            {
                // �f�[�^�R�[�h
                String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_SWITCHING_ORDER;
                l_hostOrderParams.setRequestCode(l_strRequestCode);
                // ���X���擾����
                Branch l_banch =
                    l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                // ���X�R�[�h
                l_hostOrderParams.setBranchCode(l_banch.getBranchCode());
                // �،���ЃR�[�h
                String l_strInstitutionCode =
                    l_banch.getInstitution().getInstitutionCode();
                l_hostOrderParams.setInstitutionCode(l_strInstitutionCode);
                // �ڋq�R�[�h
                String l_strAccountCode =
                    l_accountManager.getMainAccount(
                        l_mfOderUnitRow.getAccountId()).getAccountCode();
                l_hostOrderParams.setAccountCode(l_strAccountCode);
                // ���҃R�[�h
                if (!l_mfOderUnitRow.getTraderIdIsNull())
                {
                    String l_strTraderCode =
                        l_finObjMgr.getTrader(
                            l_mfOderUnitRow.getTraderId()).getTraderCode();
                    l_hostOrderParams.setTraderCode(l_strTraderCode);
                }
                else
                {
                    l_hostOrderParams.setTraderCode(null);
                }
                // �����R�[�h
                WEB3MutualFundProduct l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mfOderUnitRow.getProductId());
                l_hostOrderParams.setProductCode(l_mfProduct.getProductCode());
                
                // �w��
                //�E���M�����P��.�������ʃ^�C�v==�h���ʁh �̏ꍇ�A�h�����h               
                QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                }
                //�E���M�����P��.�������ʃ^�C�v==�h���z�h �̏ꍇ�A�h���z�h
                else if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                }
                //�E���M�����P��.���M���敪==�h�S���w��h and
                //  ���M�����P��.��������==0 �̏ꍇ�A�h�S���h  
                String l_strSellDiv = l_mfOderUnitRow.getFundSellDiv();
                double l_dblQuantity = l_mfOderUnitRow.getQuantity();
                
                if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_strSellDiv) && l_dblQuantity == 0)
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.ALL);
                }
                
                // ����
                l_hostOrderParams.setSettlementDiv(l_mfOderUnitRow.getSettlementDiv());
                
                // ������
                
                String l_strInputUnit = l_mfProduct.getInputUnit();
                BigDecimal l_bdSellOrderQuantity = new BigDecimal("0");
                BigDecimal l_bdCalcNumber2 = new BigDecimal ("10000");
                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_mfOderUnitRow.getQuantity()));
                
                //�E���M����.���͒P�ʂ��h1�F1�h�ł����
                //�@@�@@���M�����P��.��������
                if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                {
                    l_hostOrderParams.setSellOrderQuantity(l_bdQuantity.longValue());
                }
                //�E���M����.���͒P�ʂ��h2�F1���h�ł����
                //�@@�@@���M�����P��.�������� / 10000
                else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                {
                    l_bdSellOrderQuantity =
                        l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP);
                    l_hostOrderParams.setSellOrderQuantity(l_bdSellOrderQuantity.longValue());
                }
               
                // ������
                l_hostOrderParams.setBuyOrderQuantity(0);

                // �`�[No.
                l_hostOrderParams.setTicketNumber(
                    Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));

                // �d�@@�敪 �h1�F�{���h
                l_hostOrderParams.setPrDiv(WEB3PrDivDef.ISSUE_TICKET);

                // ���萔���敪
                l_hostOrderParams.setCommissionDiv(l_mfOderUnitRow.getNoContractCommissionDiv());

                // �a��`�F�b�N = ��
                l_hostOrderParams.setDepositCheckDiv(" ");
                // ���ʃR�[�h
                l_hostOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                // �󒍓�/�󒍎���
                Date l_orderDate =
                    l_mfOderUnitRow.getReceivedDateTime();
                l_hostOrderParams.setCreateDate(l_orderDate);
                l_hostOrderParams.setCreateTime(l_orderDate);
                // ������
                Date l_bizDate = WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd");
                l_hostOrderParams.setOrderDate(l_bizDate);
                // �抷�敪
                l_hostOrderParams.setSwtDiv(WEB3SwtDivDef.SWITCHING);
                // ���t�����R�[�h
                l_hostOrderParams.setSwtProductCode(l_mfOderUnitRow.getSwtProductCode());
                // ��������敪
                TaxTypeEnum l_taxType = l_mfOderUnitRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                // ��������敪�i�抷��j
                TaxTypeEnum l_strSwtTaxType = l_mfOderUnitRow.getSwtTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_strSwtTaxType))
                {
                    l_hostOrderParams.setSwtTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_strSwtTaxType))
                {
                    l_hostOrderParams.setSwtTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                
                // �����敪
                //�E���M����.isFWF() == true and ���M����.�ʉ݃R�[�h == �h�~�h�̏ꍇ
                //�h��񐿋��h
                
                boolean l_blnIsFWF = l_mfProduct.isFWF();
                String l_strCruuencyCode = l_mfProduct.getCurrencyCode();
                
                if (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode))
                {
                    l_hostOrderParams.setClaimDiv(WEB3ClaimDivDef.SELL);
                }
                //�E����ȊO�̏ꍇ
                //    ���M�����P��.�����敪
                else
                {
                    l_hostOrderParams.setClaimDiv(l_mfOderUnitRow.getRequestDiv());
                }
                
                // �����`���l��
                l_hostOrderParams.setOrderChanel(l_mfOderUnitRow.getOrderChanel());
                // �����敪�h0�F���������� �h
                l_hostOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                //������ = NULL
                l_hostOrderParams.setPaymentDate(null);
                
            }
            
            //(*) ����.���M�����P��.getOrderType()�̖߂�l�� 
            // �@@�@@OrderTypeEnum.�����M����W�����̏ꍇ�́ADB�X�V�d�l 
            //      �u�����M����W_���M�����L���[�e�[�u��.xls�v�Q�ƁB
            if (OrderTypeEnum.MF_RECRUIT.equals(l_orderType))
            {
                //�f�[�^�R�[�h = CI807�F��W
                String l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_RECRUIT;
                l_hostOrderParams.setRequestCode(l_strRequestCode);
                
                // ���X���擾����
                Branch l_banch =
                    l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
                
                // ���X�R�[�h = ���M�����P��.���XID�ɊY�����镔�X�R�[�h
                l_hostOrderParams.setBranchCode(l_banch.getBranchCode());
                
                // �،���ЃR�[�h = ���M�����P��.���XID�ɊY������،���ЃR�[�h
                String l_strInstitutionCode =
                    l_banch.getInstitution().getInstitutionCode();
                l_hostOrderParams.setInstitutionCode(l_strInstitutionCode);
                
                // �ڋq�R�[�h = ���M�����P��.����ID�ɊY������ڋq�R�[�h
                String l_strAccountCode =
                    l_accountManager.getMainAccount(
                        l_mfOderUnitRow.getAccountId()).getAccountCode();
                l_hostOrderParams.setAccountCode(l_strAccountCode);
                
                // ���҃R�[�h = ���M�����P��.���҃R�[�h�iSONAR�j
                l_hostOrderParams.setTraderCode(l_mfOderUnitRow.getSonarTraderCode());
                
                // �����R�[�h = ���M�����P��.����ID�ɊY����������R�[�h                
                WEB3MutualFundProduct l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mfOderUnitRow.getProductId());
                l_hostOrderParams.setProductCode(l_mfProduct.getProductCode());    
                
                // �w�� = ���M�����P��.�������ʃ^�C�v�����ʂȂ�΁h1�F�����h�A���z�Ȃ�΁h2�F���z�h
                QuantityTypeEnum l_quantityTypeEnum = l_mfOderUnitRow.getQuantityType();
                
                if (QuantityTypeEnum.QUANTITY.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.COUNT);
                }
                if (QuantityTypeEnum.AMOUNT.equals(l_quantityTypeEnum))
                {
                    l_hostOrderParams.setSpecifyDiv(WEB3SpecifyDivDef.MONEY);
                }
                
                // ���� = ���M�����P��.���ϋ敪
                l_hostOrderParams.setSettlementDiv(l_mfOderUnitRow.getSettlementDiv());
                
                // ������ = 0
                l_hostOrderParams.setSellOrderQuantity(0);
                
                // ������ = 
                //�E���M����.���͒P�ʂ��h1�F1�h�ł���Γ��M�����P��.��������
                //�E���M����.���͒P�ʂ��h2�F1���h�ł���Γ��M�����P��.�������� / 10000
                //���j���M�����́A���M�����P��.����ID�ɊY�����铊�M����
                String l_strInputUnit = l_mfProduct.getInputUnit();
                BigDecimal l_bdBuyOrderQuantity = new BigDecimal("0");
                BigDecimal l_bdCalcNumber2 = new BigDecimal ("10000");
                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_mfOderUnitRow.getQuantity()));
                
                if(WEB3InputUnitDef.ONE.equals(l_strInputUnit))
                {
                    l_hostOrderParams.setBuyOrderQuantity(l_bdQuantity.longValue());
                }
                if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_strInputUnit))
                {
                    l_bdBuyOrderQuantity =
                        l_bdQuantity.divide(l_bdCalcNumber2,10,BigDecimal.ROUND_HALF_UP);
                    l_hostOrderParams.setBuyOrderQuantity(l_bdBuyOrderQuantity.longValue());
                }

                // �`�[No. = 9 + ����l�R�[�h�̉�3��
                l_hostOrderParams.setTicketNumber(
                    Integer.parseInt("9" + l_mfOderUnitRow.getOrderRequestNumber().substring(6)));

                // �d�@@�敪
                //�E���M����.is�O�����M()==false or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h == �h�~�h�j �̏ꍇ�A
                // �h1�F�{���h���Z�b�g�B
                //�E���M����.is�O�����M()==true or
                //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h != �h�~�h�j �̏ꍇ�A
                // "��"���Z�b�g�B
                boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
                boolean l_blnIsFWF = l_mfProduct.isFWF();
                String l_strCruuencyCode = l_mfProduct.getCurrencyCode();

                if (!l_blnIsForeignFund || 
                    (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(WEB3PrDivDef.ISSUE_TICKET);
                }
                if (l_blnIsForeignFund || 
                    (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
                {
                    l_hostOrderParams.setPrDiv(" ");
                }
                
                // ���萔���敪 = ���M�����P��.���萔���敪
                l_hostOrderParams.setCommissionDiv(l_mfOderUnitRow.getNoContractCommissionDiv());

                // �a��`�F�b�N = ��
                l_hostOrderParams.setDepositCheckDiv(" ");
                
                // ���ʃR�[�h = ���M�����P��.���ʔԍ�
                l_hostOrderParams.setOrderRequestNumber(l_mfOderUnitRow.getOrderRequestNumber());
                
                // �󒍓�/�󒍎��� = ���M�����P��.�󒍓���
                Date l_orderDate =
                    l_mfOderUnitRow.getReceivedDateTime();
                l_hostOrderParams.setCreateDate(l_orderDate);
                l_hostOrderParams.setCreateTime(l_orderDate);
                
                // ������ = ���M�����P��.������
                Date l_bizDate = WEB3DateUtility.getDate(l_mfOderUnitRow.getBizDate(), "yyyyMMdd");
                l_hostOrderParams.setOrderDate(l_bizDate);
                
                // �抷�敪 = NULL
                l_hostOrderParams.setSwtDiv(null);
                
                // ���t�����R�[�h
                // is�ē���������true�̏ꍇ�A���X�p�v���t�@@�����X�D�����i�ē����j
                // is�ē���������false�̏ꍇ�A���X�p�v���t�@@�����X�D�����i���z�j
                // ���R�[�h�ɑ��݂��Ȃ��ꍇ�A"00"
                l_hostOrderParams.setSwtProductCode("00");
                if (l_mfProduct.isPlowbackProduct())
                {
                    // ���X�p�v���t�@@�����X����u����(�ē���)�v���擾����B
                    BranchPreferencesRow l_preferencesRow = null;
                    try
                    {
                        l_preferencesRow = 
                            BranchPreferencesDao.findRowByPk(
                                l_mutualFundOrderUnit.getBranchId(), 
                                WEB3BranchPreferencesNameDef.PLOWBACK_SYOKUCHI,
                                1);
                        l_hostOrderParams.setSwtProductCode(l_preferencesRow.getValue());
                    }
                    catch(DataFindException l_ex)
                    {
                        l_preferencesRow = null;
                    }
                }
                else
                {
                    // ���X�p�v���t�@@�����X����u����(���z)�v���擾����B
                    BranchPreferencesRow l_preferencesRow = null;
                    try
                    {
                        l_preferencesRow = 
                            BranchPreferencesDao.findRowByPk(
                                l_mutualFundOrderUnit.getBranchId(), 
                                WEB3BranchPreferencesNameDef.NOPLOWBACK_SYOKUCHI,
                                1);
                        l_hostOrderParams.setSwtProductCode(l_preferencesRow.getValue());
                    }
                    catch(DataFindException l_ex)
                    {
                        l_preferencesRow = null;
                    }
                }
                
                // ��������敪 = ���M�����P��.�ŋ敪����ʂ̏ꍇ�h0�F��ʁh�A����̏ꍇ�h1�F����h
                TaxTypeEnum l_taxType = l_mfOderUnitRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.NORMAL);
                }
                if (TaxTypeEnum.SPECIAL.equals(l_taxType))
                {
                    l_hostOrderParams.setTaxType(WEB3TaxTypeSpecialDef.SPECIAL);
                }
                
                // ��������敪�i�抷��j= NULL                
                l_hostOrderParams.setSwtTaxType(null);               
                
                // �����敪 = ��
                l_hostOrderParams.setClaimDiv(" ");
                
                // �����`���l�� = ���M�����P��.���񒍕��̒����`���l��
                l_hostOrderParams.setOrderChanel(l_mfOderUnitRow.getOrderChanel());
                
                // �����敪 = 0�F������
                l_hostOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);
                
                //������ = ��W�̏ꍇ�͔������̗��c�Ɠ�
                //�i����ȊO�̏ꍇ��NULL�j
                if (OrderTypeEnum.MF_RECRUIT.equals(l_mfOderUnitRow.getOrderType()))
                {
                    Date l_datPaymentDate = 
                        new WEB3GentradeBizDate(new Timestamp(l_bizDate.getTime())).roll(1);
                    
                    l_hostOrderParams.setPaymentDate(l_datPaymentDate);
                }
                else
                {
                    l_hostOrderParams.setPaymentDate(null);
                }
            }
            
            l_hostOrderParams.markAllValuesAsSet();
            Processors.getDefaultProcessor().doInsertQuery(l_hostOrderParams);
            log.debug("�L���[�f�[�^�C���T�[�g...... with HostXbmfOrderParams = " + l_hostOrderParams);

            //1.2.<���򏈗�> (*2)
            if (OrderTypeEnum.MF_RECRUIT.equals(l_mutualFundOrderUnit.getOrderType()))
            {
                //1.2.1���X�p�v���t�@@�����X����u���M��W�����ꊇ���M�敪�v���擾����B
                BranchPreferencesRow l_preferencesRow = null;
                try
                {
                    l_preferencesRow = 
                        BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                            l_mutualFundOrderUnit.getBranchId(), 
                            WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                            1);
                }
                catch(DataFindException l_ex)
                {
                    l_preferencesRow = null;
                }
                
                //<���򏈗�>���X�p�v���t�@@�����X�D�v���t�@@�����X�l = �u�ꊇ���M����v�̏ꍇ
                if (l_preferencesRow == null || 
                    WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_preferencesRow.getValue()) )
                {
                    return;
                }
            }

            // 3�j�@@�g���K���s
            // �|����.���M�����P��.get�󒍓���()�̖߂�l��YYYYMMDD�`���ɕϊ��������̂�
            // ����.���M�����P��.getBizDate()�̖߂�l���r���A
            // �l���قȂ�ꍇ�͈ȍ~�̏������s��Ȃ��B
            String l_strOrderDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                    l_mfOderUnitRow.getReceivedDateTime());
            String l_strBizDate = l_mfOderUnitRow.getBizDate();
            log.debug(" ���M�����P��.��������(YYYYMMDD)�A���M�����P��.�������iYYYYMMDD�j�� " + l_strOrderDate + "�A" + l_strBizDate);
            if (!l_strOrderDate.equals(l_strBizDate))
            {
                return;
            }

            // �|�g���K�𔭍s���邩�̃`�F�b�N
			log.debug("�g���K���s���ԑт��̃`�F�b�N");
            if (WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT))
            {
                // �|WEB3MQMessageSpec�̐���
                WEB3MQMessageSpec l_mqMessageSpec =
                    new WEB3MQMessageSpec(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_hostOrderParams.getRequestCode() + "T");
                log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
                log.debug("DataCode =" + l_hostOrderParams.getRequestCode() + "T");
                // �|�g���K�𔭍s����B
                WEB3MQGatewayService l_mqGatewayService =
                    (WEB3MQGatewayService) Services.getService(
                        WEB3MQGatewayService.class);
                log.debug("�g���K�𔭍s����........");
                l_mqGatewayService.send(l_mqMessageSpec);
                log.debug("�g���K�𔭍s����........OK!");
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In ���X�A�ڋq�R�[�h�܂��͖����R�[�h���擾���� ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In ���M��������L���[�f�[�^�C���T�[�g ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In ���M��������L���[�f�[�^�C���T�[�g ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }

    /**
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���������ʒm�j<BR>
     * <BR>
     * �����P�ʂ̒�����Ԃ𔭒��ρi�V�K�����j�ɕύX����B<BR>
     * <BR>
     * �P�j�@@���M�s�ꉞ���R�[���o�b�N�T�[�r�X�I�u�W�F�N�g�̎擾<BR>
     * �@@MutualFundMarketResponseReceiverCallbackService( )<BR>
     * �@@���R�[�����A���M�s�ꉞ���R�[���o�b�N�T�[�r�X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@DefaultNewOrderAcceptedMarketResponseMessage<BR>
     * �@@�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@����ID�F ����.���M�����P��.getOrderId()�̖߂�l<BR>
     * <BR>
     * �R�j�@@���M��t�m��C���^�Z�v�^�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �S�j�@@���M��t�m��C���^�Z�v�^.set�����G���[���R�R�[�h()���R�[�����A�����G���[�R
     * �[�h��<BR>
     * �@@�ݒ���s���B<BR>
     * �@@[set�����G���[���R�R�[�h�ɓn���p�����^]<BR>
     * �@@�@@�����G���[���R�R�[�h�F null<BR>
     * <BR>
     * �T�j�@@�g�����M�����}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[��
     * ���A<BR>
     * �@@�C���^�Z�v�^�̐ݒ���s���B<BR>
     * �@@[setThreadLocalPersistenceEventInterceptor�ɓn���p�����^]<BR>
     * �@@�@@���M��t�m��C���^�Z�v�^�F �����������M��t�m��C���^�Z�v�^<BR>
     * <BR>
     * �U�j�@@MutualFundMarketResponseReceiverCallbackService.process()���R�[������B<BR>
     * �@@�mprocess�ɓn���p�����^�n<BR>
     * �@@�@@�V�K������t�ώs�ꉞ�����b�Z�[�W�F <BR>
     * �@@�@@�@@��������DefaultNewOrderAcceptedMarketResponseMessage�I�u�W�F�N�g<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B5BFAE03C8
     */
    public void newOrderMarketRequestSendMessageTradeOrderNotify(
        MutualFundOrderUnit l_mutualFundOrderUnit)
        throws WEB3BaseException
    {
        final String l_strMethodName = "newOrderMarketRequestSendMessageTradeOrderNotify("
            + "MutualFundOrderUnit l_mutualFundOrderUnit)";
        log.entering(l_strMethodName);
        // �P�j�@@���M�s�ꉞ���R�[���o�b�N�T�[�r�X�I�u�W�F�N�g�̎擾
//�@@        MutualFundMarketResponseReceiverCallbackService( )
//�@@        ���R�[�����A���M�s�ꉞ���R�[���o�b�N�T�[�r�X�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();
        MutualFundMarketResponseReceiverCallbackService l_service =
            (MutualFundMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();

//
        // �Q�j�@@DefaultNewOrderAcceptedMarketResponseMessage
        // �I�u�W�F�N�g�𐶐�����B
        // �m�R���X�g���N�^�ɓn���p�����^�n
//�@@�@@        ����ID�F ����.���M�����P��.getOrderId()�̖߂�l
        DefaultNewOrderAcceptedMarketResponseMessage
            l_defaultCancelOrderRejectedMarketResponseMessage =
                new DefaultNewOrderAcceptedMarketResponseMessage(
                    l_mutualFundOrderUnit.getOrderId());
//
        // �R�j�@@���M��t�m��C���^�Z�v�^�I�u�W�F�N�g�𐶐�����B
        WEB3MutualFundAcceptConfirmInterceptor l_mfAcceptconfirmInterceptor =
            new WEB3MutualFundAcceptConfirmInterceptor();

        // �S�j�@@���M��t�m��C���^�Z�v�^.set�����G���[���R�R�[�h()���R�[�����A�����G���[�R�[�h��
        // �ݒ���s���B
        // [set�����G���[���R�R�[�h�ɓn���p�����^]
        //  �����G���[���R�R�[�h�F null
        l_mfAcceptconfirmInterceptor.setOrderErrorReasonCode(null);
        // �T�j�@@�g�����M�����}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[�����A
        // �C���^�Z�v�^�̐ݒ���s���B
        // [setThreadLocalPersistenceEventInterceptor�ɓn���p�����^]
        //   ���M��t�m��C���^�Z�v�^�F �����������M��t�m��C���^�Z�v�^
        WEB3MutualFundOrderManager l_mfOrderManager =
            (WEB3MutualFundOrderManager)l_tm.getOrderManager();
        l_mfOrderManager.setThreadLocalPersistenceEventInterceptor(l_mfAcceptconfirmInterceptor);

        // �U�j�@@MutualFundMarketResponseReceiverCallbackService.process()���R�[������B
        // �mprocess�ɓn���p�����^�n
        //   �V�K������t�ώs�ꉞ�����b�Z�[�W�F
        // ��������DefaultNewOrderAcceptedMarketResponseMessage�I�u�W�F�N�g
        l_service.process(l_defaultCancelOrderRejectedMarketResponseMessage);

        log.exiting(l_strMethodName);
    }

    /**
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i�s�ꑗ�M����j<BR>
     * <BR>
     * ��������̎s�ꃊ�N�G�X�g���b�Z�[�W�𑗐M����B<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�C���T�[�g<BR>
     * �@@����.���M�����P�ʃI�u�W�F�N�g�̓��e�����ƂɁA���M��������L���[�e�[�u��<BR>
     * �@@�ɒ����f�[�^��}������B<BR>
     * �@@�iDB�X�V�d�l�u�����M�����_���M��������L���[�e�[�u��.xls�v�Q�Ɓj<BR>
     * <BR>
     * �Q�j�@@�g���K���s<BR>
     * �@@�|�g���K�𔭍s���邩�̃`�F�b�N<BR>
     * �@@�@@���M������ԊǗ�.is�g���K���s()���R�[�����A�߂�l��true�ł���΃g���K��<BR>
     * �@@�@@���s����B�߂�l��false�ł���Έȍ~�̏������s��Ȃ��B<BR>
     * �@@�@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@���������F<BR>
     * �@@�@@�@@�@@�@@�h0 �F DEFAULT�h<BR>
     * <BR>
     * �@@�|WEB3MQMessageSpec�̐���<BR>
     * �@@�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F
     * ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F ���M��������L���[�e�[�u���̃f�[�^�R�[�h�̒l<BR>
     * <BR>
     * �@@�|�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayServiceImpl.send()���R�[�����A�g���K�𔭍s����B<BR>
     * �@@�@@�msend�ɓn���p�����^�n<BR>
     * �@@�@@�@@MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N�F
     * ��������WEB3MQMessageSpec�I�u�W�F�N�g<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B5BFB80109
     */
    public void cancelOrderMarketRequestSendMessageSubmit(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String l_strMethodName = "cancelOrderMarketRequestSendMessageSubmit("
            + "MutualFundOrderUnit l_mutualFundOrderUnit, "
            + "SubAccount l_subAccount)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundOrderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // �P�j�@@�L���[�f�[�^�C���T�[�g
            HostXbmfOrderCancelParams l_hostOrderCancelParams =
                new HostXbmfOrderCancelParams();

            MutualFundOrderUnitRow l_mfOderUnitRow =
                (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mfProductManager =
                (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

            WEB3MutualFundProduct l_mfProduct =
                (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                    l_mfOderUnitRow.getProductId());
                    
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            
            // �f�[�^�R�[�h
            //�E���M����.is�O�����M()==false or
            //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h == �h�~�h�j �̏ꍇ�A
            //"CI802"���Z�b�g
            boolean l_blnIsForergnFund = l_mfProduct.isForeignFund();
            boolean l_blnIsFWF = l_mfProduct.isFWF();
            String l_strCruuencyCode = l_mfProduct.getCurrencyCode();
            String l_strRequestCode = null;
            
            if (!l_blnIsForergnFund || 
                (l_blnIsFWF && WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
            {
                l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_DOMESTIC_ORDER_CANCEL;
                l_hostOrderCancelParams.setRequestCode(l_strRequestCode);
            }
            //�E���M����.is�O�����M()==true or
            //�i���M����.isFWF()==true and ���M����.�ʉ݃R�[�h != �h�~�h�j �̏ꍇ�A
            //"CI804"���Z�b�g
            if (l_blnIsForergnFund || 
                (l_blnIsFWF && !WEB3MFOrderQuantityType.EN.equals(l_strCruuencyCode)))
            {                
                l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER_CANCEL;
                l_hostOrderCancelParams.setRequestCode(l_strRequestCode); 
            }
            //�E���M�����P��.������� == �h���M�抷�����h �̏ꍇ�A"CI806"���Z�b�g                       
			OrderTypeEnum l_orderType = l_mfOderUnitRow.getOrderType();
			if (OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
			{
                l_strRequestCode = WEB3HostRequestCodeDef.MUTUAL_FUND_SWITCHING_ORDER_CANCEL;
				l_hostOrderCancelParams.setRequestCode(l_strRequestCode);
			}
            
            // ���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_mfOderUnitRow.getBranchId());
            // ���X�R�[�h
            l_hostOrderCancelParams.setBranchCode(l_banch.getBranchCode());
            // �،���ЃR�[�h
            String l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            l_hostOrderCancelParams.setInstitutionCode(l_strInstitutionCode);
            // �ڋq�R�[�h
            String l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_mfOderUnitRow.getAccountId()).getAccountCode();
            l_hostOrderCancelParams.setAccountCode(l_strAccountCode);
            // ���҃R�[�h
            if (!l_mfOderUnitRow.getTraderIdIsNull())
            {
                String l_strTraderCode =
                    l_finObjMgr.getTrader(
                        l_mfOderUnitRow.getTraderId()).getTraderCode();
                l_hostOrderCancelParams.setTraderCode(l_strTraderCode);
            }
            else
            {
                l_hostOrderCancelParams.setTraderCode(null);
            }
            // �����R�[�h
            l_hostOrderCancelParams.setProductCode(l_mfProduct.getProductCode());
            // ����敪
            l_hostOrderCancelParams.setCancelDiv("-");
            // ���ʃR�[�h
            l_hostOrderCancelParams.setOrderRequestNumber(
                l_mfOderUnitRow.getOrderRequestNumber());
            // �󒍓�/�󒍎���
            Date l_strOrderDate =
                l_mfOderUnitRow.getReceivedDateTime();
            l_hostOrderCancelParams.setOrderDate(l_strOrderDate);
            l_hostOrderCancelParams.setOrderTime(l_strOrderDate);
            // �����敪
            String l_strStatus = WEB3StatusDef.NOT_DEAL;
            l_hostOrderCancelParams.setStatus(l_strStatus);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostOrderCancelParams);

            // �Q�j�@@�g���K���s
            // �|�g���K�𔭍s���邩�̃`�F�b�N
            if (WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT))
            {
                // �|WEB3MQMessageSpec�̐���
                WEB3MQMessageSpec l_web3MQMessageSpec =
                    new WEB3MQMessageSpec(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_strRequestCode + "T");
                log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
                log.debug("DataCode =" + l_strRequestCode + "T");
                
                // �|�g���K�𔭍s����B
                WEB3MQGatewayService l_web3MQGatewayService =
                    (WEB3MQGatewayService) Services.getService(
                        WEB3MQGatewayService.class);

                l_web3MQGatewayService.send(l_web3MQMessageSpec);
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In ���X�A�ڋq�R�[�h�܂��͖����R�[�h���擾���� ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In ���M��������L���[�f�[�^�C���T�[�g ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In ���M��������L���[�f�[�^�C���T�[�g ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        finally
        {
            log.exiting(l_strMethodName);
        }
    }

    /**
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i�s�ꑗ�M�Ȃ��j<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�폜<BR>
     * �@@����.���M�����P�ʃI�u�W�F�N�g�̎��ʃR�[�h�ɑΉ�����L���[�f�[�^�����݂���ꍇ�E
     * �A<BR>
     * �@@���M�����L���[�e�[�u���̃f�[�^���폜����B<BR>
     * �@@�m�폜�����n<BR>
     * �@@�@@�،���ЃR�[�h =
     * ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l AND<BR>
     * �@@�@@���X�R�[�h =
     * <BR>����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l AND<BR>
     * �@@�@@�ڋq�R�[�h = ����.�⏕����.getMainAccount().getAccountCode()�̖߂�l AND<BR>
     * �@@�@@���ʃR�[�h = ����.���M�����P��.getDataSourceObject().get���ʃR�[�h()<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B5BFC00157
     */
    public void cancelOrderMarketRequestSendMessageNotSubmit(
        MutualFundOrderUnit l_mutualFundOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String l_strMethodName = "cancelOrderMarketRequestSendMessageNotSubmit("
            + "MutualFundOrderUnit l_mutualFundOrderUnit, "
            + "SubAccount l_subAccount)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundOrderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // �P�j�@@�L���[�f�[�^�폜
        // ����.���M�����P�ʃI�u�W�F�N�g�̎��ʃR�[�h�ɑΉ�����L���[�f�[�^�����݂���ꍇ�A
        // ���M�����L���[�e�[�u���̃f�[�^���폜����B
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            String l_strWhere =
                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
            MutualFundOrderUnitRow l_mfOrderUnitRow =
                (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();
            //�m�폜�����n
            // �،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l
            // ���X�R�[�h = ����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l AND
            // �ڋq�R�[�h = ����.�⏕����.getMainAccount().getAccountCode()�̖߂�l AND
            // ���ʃR�[�h = ����.���M�����P��.getDataSourceObject().get���ʃR�[�h()
            Object[] l_objWhereValues = {
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_mfOrderUnitRow.getOrderRequestNumber()
            };
            
            //�@@���M�����P�ʁD���M�^�C�v = �O��MMF�̏ꍇ
            //�@@�@@�O��MMF�����L���[�e�[�u���ɊY������s������ꍇ�͍폜����
            if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mfOrderUnitRow.getFundType()))
            {
                processor.doDeleteAllQuery(
                    HostFrgnMmfOrderRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            }
            //�A���M�����P�ʁD���M�^�C�v  != �O��MMF�̏ꍇ
            //���M�����L���[�e�[�u���ɊY������s������ꍇ�͍폜����
            else
            {
                //�Ώۃf�[�^�폜
                log.debug("�L���[�f�[�^�폜HostXbmfOrderRow.............. with where = " + l_strWhere);
                log.debug("�L���[�f�[�^�폜HostXbmfOrderRow.............. with where Value0 = " + l_objWhereValues[0]);
                log.debug("�L���[�f�[�^�폜HostXbmfOrderRow.............. with where Value1 = " + l_objWhereValues[1]);
                log.debug("�L���[�f�[�^�폜HostXbmfOrderRow.............. with where Value2 = " + l_objWhereValues[2]);
                log.debug("�L���[�f�[�^�폜HostXbmfOrderRow.............. with where Value3 = " + l_objWhereValues[3]);
                processor.doDeleteAllQuery(
                    HostXbmfOrderRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            }
            log.debug("�L���[�f�[�^�폜.............. OK!");
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In ���M�����L���[�e�[�u���̃f�[�^���폜���� ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In ���M�����L���[�e�[�u���̃f�[�^���폜���� ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }

        log.exiting(l_strMethodName);
    }

    /* (�� Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSenderService#send(com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestMessage)
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
     return null;
    }
}
@
