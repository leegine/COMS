head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMarketRequestSubmitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�N���X(WEB3RuitoMarketRequestSubmitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15  ���u�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.marketadaptor;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrder;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.market.messages.RuitoNewOrderMarketRequestMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderDivTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SettleDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransferRouteDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.data.HostMrfOrderCancelParams;
import webbroker3.xbruito.data.HostMrfOrderParams;
import webbroker3.xbruito.data.HostRuitoOrderCancelParams;
import webbroker3.xbruito.data.HostRuitoOrderParams;
import webbroker3.xbruito.data.HostRuitoSellParams;
import webbroker3.xbruito.data.HostSellCancelParams;

/**
 * �ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�N���X�B<BR>
 */
public class WEB3RuitoMarketRequestSubmitServiceImpl
    implements RuitoMarketRequestSenderService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3RuitoMarketRequestSubmitServiceImpl.class);

    /**
     * �s�ꑗ�M���s���ꍇ�� true ���A�����łȂ��ꍇ�� false ��ݒ肷��B<BR>
     */
    private boolean isMarketSubmit;

    /**
     * �isend�̎����j<BR>
     * <BR>
     * �w��̎�������s�ꃊ�N�G�X�g���b�Z�[�W���s��֑��M����B<BR>
     * <BR>
     * �P�j�@@�����擾<BR>
     * �@@�|��������s�ꃊ�N�G�X�g���b�Z�[�W.getOrderId() �ɂĒ���ID��<BR>
     *          �擾����B<BR>
     * �@@�|�g���ݓ������}�l�[�W��.getOrder()���R�[�����āA<BR>
     *   �ݓ������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetOrder�ɓn���p�����^�n<BR>
     * �@@�@@�@@����ID�F �擾��������ID<BR>
     * �@@�|�擾�����ݓ������I�u�W�F�N�g.getOrderUnits()���R�[�����A<BR>
     *         �ݓ������P�ʃI�u�W�F�N�g�̔z����擾����B<BR>
     *     <BR>
     * �Q�j�@@��������s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     * �Q�|�P�j�@@�ݓ������P��.getDataSourceObject().getFundType()��<BR>
     *            �߂�l��<BR>
     * RuitoTypeEnum.MRF�ŁA����.is�s�ꖢ���M�̒l��false�̏ꍇ<BR>
     * �@@��������s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     * �iMRF���F�s�ꑗ�M����j()�� �R�[������B<BR>
     * �@@�m��������s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     * �iMRF���F�s�ꑗ�M����j�ɓn���p�����^�n<BR>
     * �@@�@@�ݓ������P�ʁF <BR>
     *     �擾�����ݓ������P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * �@@�@@�⏕�����F <BR>
     *    ��������s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()�̖߂�l<BR>
     * �@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j����������M<BR>
     *        �iMRF���F�s�ꑗ�M����j�v���Q�Ɓj<BR>
     * <BR>
     * �Q�|�Q�j�@@�ݓ������P��.getDataSourceObject().getFundType()��<BR>
     *           �߂�l��<BR>
     * RuitoTypeEnum.�������t�@@���h�܂���RuitoTypeEnum.MMF�ŁA<BR>
     * ���ݓ������P��.getOrderType()�̖߂�l��<BR>
     * OrderTypeEnum.�ݓ���������<BR>
     * �A������.is�s�ꖢ���M�̒l��false�̏ꍇ<BR>
     * �@@��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�F�s�ꑗ�M����j()��<BR>
     *         �R�[������B<BR>
     * �@@�m��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�F�s�ꑗ�M����j��<BR>
     *          �n���p�����^�n<BR>
     * �@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * �@@�@@�⏕�����F ��������s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()��<BR>
     *          �߂�l<BR>
     * �@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j����������M<BR>
     *    �i���t�F�s�ꑗ�M����j�v���Q�Ɓj<BR>
     * <BR>
     * �Q�|�R�j�@@�ݓ������P��.getDataSourceObject().getFundType()<BR>
     *            �̖߂�l��<BR>
     * RuitoTypeEnum.�������t�@@���h�܂���RuitoTypeEnum.MMF�ŁA<BR>
     * ���ݓ������P��.getOrderType()�̖߂�l��<BR>
     * OrderTypeEnum.�ݓ��������ŁA<BR>
     * ������.is�s�ꖢ���M�̒l��false�̏ꍇ<BR>
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���F�s�ꑗ�M����j()��<BR>
     * �R�[������B<BR>
     * �m��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���F�s�ꑗ�M����j��<BR>
     * �n���p�����^�n<BR>
     * �@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * �@@�@@�⏕�����F <BR>
     *    ��������s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()�̖߂�l<BR>
     * �@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j����������M<BR>
     *    �i���F�s�ꑗ�M����j�v ���Q�Ɓj<BR>
     * <BR>
     * �Q�|�S�j�@@�ݓ������P��.getDataSourceObject().getFundType()<BR>
     *            �̖߂�l��<BR>
     * RuitoTypeEnum.MRF�ŁA����.is�s�ꖢ���M�̒l��true�̏ꍇ<BR>
     * �@@��������s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     *  �iMRF���F�s�ꑗ�M�Ȃ��j()���R�[������B<BR>
     * �@@�m��������s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     *    �iMRF���F�s�ꑗ�M�Ȃ��j�ɓn���p�����^�n<BR>
     * �@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * �@@�@@�⏕�����F <BR>
     *    ��������s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()�̖߂�l<BR>
     * �@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j����������M<BR>
     *    �iMRF���F�s�ꑗ�M�Ȃ��j�v���Q�Ɓj<BR>
     * <BR>
     * �Q�|�T�j�@@�ݓ������P��.getDataSourceObject().getFundType()<BR>
     *            �̖߂�l��<BR>
     * RuitoTypeEnum.�������t�@@���h�܂���RuitoTypeEnum.MMF�ŁA<BR>
     * ���ݓ������P��.getOrderType()�̖߂�l��<BR>
     * OrderTypeEnum.�ݓ��������ŁA<BR>
     * ������.is�s�ꖢ���M�̒l��true�̏ꍇ<BR>
     * �@@��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�F�s�ꑗ�M�Ȃ��j()<BR>
     *   ���R�[������B<BR>
     * �@@�m��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�F�s�ꑗ�M�Ȃ��j��<BR>
     *     �n���p�����^�n<BR>
     * �@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * �@@�@@�⏕�����F <BR>
     *     ��������s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()�̖߂�l<BR>
     * �@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j����������M<BR>
     *    �i���t�F�s�ꑗ�M�Ȃ��j�v���Q�Ɓj<BR>
     * <BR>
     * �Q�|�U�j�@@�ݓ������P��.getDataSourceObject().getFundType()<BR>
     *            �̖߂�l��<BR>
     * RuitoTypeEnum.�������t�@@���h�܂���RuitoTypeEnum.MMF�ŁA<BR>
     * ���ݓ������P��.getOrderType()�̖߂�l��<BR>
     *                     OrderTypeEnum.�ݓ��������ŁA<BR>
     * ������.is�s�ꖢ���M�̒l��true�̏ꍇ<BR>
     * �@@��������s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     *   �i���F�s�ꑗ�M�Ȃ��j()���R�[������B<BR>
     * �@@�m��������s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     *    �i���F�s�ꑗ�M�Ȃ��j�ɓn���p�����^�n<BR>
     * �@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * �@@�@@�⏕�����F <BR>
     *     ��������s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()�̖߂�l<BR>
     * �@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j����������M<BR>
     *    �i���F�s�ꑗ�M�Ȃ��j�v ���Q�Ɓj<BR>
     * <BR>
     * �Q�|�V�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M��<BR>
     *                         �e���\�b�h������I�������ꍇ�A<BR>
     * �@@DefaultMarketRequestSendResult.newSuccessResultInstance()<BR>
     *          �̖߂�l��Ԃ��B<BR>
     * �@@�mnewSuccessResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@���b�Z�[�W�g�[�N��ID�F 0<BR>
     * <BR>
     * �Q�|�W�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h��<BR>
     *              ��O���X���[�����ꍇ<BR>
     * �@@�|ProcessingResult.newFailedResultInstance()���R�[������<BR>
     *         ProcessingResult�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�mnewFailedResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@�@@�G���[���F ��O�I�u�W�F�N�g.getErrorInfo()�̖߂�l<BR>
     * �@@�|DefaultMarketRequestSendResult.newFailedResultInstance()<BR>
     *            �̖߂�l��Ԃ��B<BR>
     * �@@�@@�mnewFailedResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@�@@�I�y���[�V�������ʁF ��������ProcessingResult�I�u�W�F�N�g<BR>
     * @@param l_request - ��������s�ꃊ�N�G�X�g���b�Z�[�W<BR>
     * @@param isMarketNotSubmit -
     * �������s��ɖ����M�ő��M�T�[�r�X�Ƀ��[�J�����������悤�ʒm����ꍇ��<BR>
     * true ���w�肷��B<BR>
     * false�Ɏw��̏ꍇ�͎s��֎�����b�Z�[�W�𑗐M����B<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 406D22920166
     */
    public MarketRequestSendResult send(
        CancelOrderMarketRequestMessage l_request,
        boolean isMarketNotSubmit)
        throws TooLateException
    {
        final String STR_METHOD_NAME =
            "send(CancelOrderMarketRequestMessage l_request,"
                + "boolean isMarketNotSubmit)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("__ParameterError__");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //��������s�ꃊ�N�G�X�g���b�Z�[�W.getOrderId() �ɂĒ���ID���擾����
            long l_lngOrderId = l_request.getOrderId();
            log.debug(
                "[cancelOrderMarketRequestMessageSubmit] l_lngOrderId = "
                    + l_lngOrderId);

            WEB3RuitoOrderManager l_ruitoOrderManager = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            l_ruitoOrderManager =
                (WEB3RuitoOrderManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getOrderManager();
            RuitoOrder l_ruitoOrder = null;
            try
            {
                //�ݓ������I�u�W�F�N�g
                l_ruitoOrder =
                    (RuitoOrder) l_ruitoOrderManager.getOrder(l_lngOrderId);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�ݓ������P�ʃI�u�W�F�N�g�̔z��
            OrderUnit[] l_orderUnits = l_ruitoOrder.getOrderUnits();
            //��������s�ꃊ�N�G�X�g���b�Z�[�W���M
            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_orderUnits[0].getDataSourceObject();

            RuitoOrderUnit l_ruitoOrderUnit = (RuitoOrderUnit) l_orderUnits[0];
            int l_intRuitoType = l_ruitoOrderUnitRow.getRuitoType().intValue();

            log.debug("l_intRuitoType = " + l_intRuitoType);
            log.debug("l_intOrderType = " + l_ruitoOrderUnit.getOrderType());

            try
            {
                if (l_intRuitoType == RuitoTypeEnum.MRF.intValue()
                    && !isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_BUY.equals(
                            l_ruitoOrderUnit.getOrderType())
                        && !isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitBuyHasMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_SELL.equals(
                            l_ruitoOrderUnit.getOrderType())
                        && !isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitSellHasMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    l_intRuitoType == RuitoTypeEnum.MRF.intValue()
                        && isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitMRFSellNotMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_BUY.equals(
                            l_ruitoOrderUnit.getOrderType())
                        && isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitBuyNotMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_SELL.equals(
                            l_ruitoOrderUnit.getOrderType())
                        && isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitSellNotMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
            }
            catch (WEB3BaseException l_ex)
            {
                ProcessingResult l_processingResult =
                    ProcessingResult.newFailedResultInstance(
                        l_ex.getErrorInfo());

                log.exiting(STR_METHOD_NAME);
                return DefaultMarketRequestSendResult.newFailedResultInstance(
                    l_processingResult);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new TooLateException(l_ex.getMessage());
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
    }

    /**
     * �isend(RuitoNewOrderMarketRequestMessage)�̎����j<BR>
     * <BR>
     * �w��̗ݓ������}�[�P�b�g���N�G�X�g���b�Z�[�W���s��֑��M����B<BR>
     * <BR>
     * �P�j�@@this.is�s�ꑗ�M()�̒l�� false �̏ꍇ�A<BR>
     * DefaultMarketRequestSendResult.newSuccessResultInstance(0)<BR>
     * �̖߂�l��Ԃ��B<BR>
     * <BR>
     * �Q�j�@@�����擾<BR>
     * �@@�|�ݓ��V�K�����s�ꃊ�N�G�X�g���b�Z�[�W.getOrderUnitId() �ɂ�<BR>
     *          �����P��ID���擾����B<BR>
     * �@@�|�g���ݓ������}�l�[�W��.getOrderUnit()���R�[�����āA<BR>
     *       �ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetOrderUnit�ɓn���p�����^�n<BR>
     * �@@�@@�@@�����P��ID�F �擾���������P��ID<BR>
     * <BR>
     * �R�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     * �R�|�P�j�@@�擾�����ݓ������P��.getDataSourceObject().<BR>
     *            get�����o�H�敪()�̖߂�l��<BR>
     * �@@WEB3OrderRootDivDef.HOST�Ɠ������Ȃ��ꍇ<BR>
     *   �i��Web�V���͒����j<BR>
     * <BR>
     * �@@�|�ݓ������P��.getDataSourceObject().getFundType()<BR>
     *      �̖߂�l��<BR>
     * �@@�@@RuitoTypeEnum.MRF�̏ꍇ<BR>
     * �@@�@@�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�iMRF���j()<BR>
     *       ���R�[������B<BR>
     * �@@�@@�@@�m�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     *         �iMRF���j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�⏕�����F <BR>
     *          �ݓ��V�K�����s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()<BR>
     *           �̖߂�l<BR>
     * �@@�@@�@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j�V�K�������M<BR>
     *         �iMRF���j�v���Q�Ɓj<BR>
     * <BR>
     * �@@�|�ݓ������P��..getDataSourceObject().getFundType()<BR>
     *      �̖߂�l��<BR>
     *         RuitoTypeEnum.�������t�@@���h<BR>
     *     �܂���RuitoTypeEnum.MMF�ŁA<BR>
     *    ���ݓ������P��.getOrderType()�̖߂�l��<BR>
     * OrderTypeEnum.�ݓ��������̏ꍇ<BR>
     * �@@�@@�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�j()���R�[������B<BR>
     * �@@�@@�@@�m�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�⏕�����F <BR>
     *         �ݓ��V�K�����s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()<BR>
     *         �̖߂�l<BR>
     * �@@�@@�@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j�V�K�������M<BR>
     *         �i���t�j�v���Q�Ɓj<BR>
     * <BR>
     * �@@�|�ݓ������P��..getDataSourceObject().getFundType()<BR>
     *       �̖߂�l��RuitoTypeEnum.�������t�@@���h<BR>
     *      �܂���RuitoTypeEnum.MMF�ŁA<BR>
     *      ���ݓ������P��.getOrderType()�̖߂�l��<BR>
     *          OrderTypeEnum.�ݓ��������̏ꍇ<BR>
     * �@@�@@�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���j()���R�[������B<BR>
     * �@@�@@�@@�m�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�⏕�����F <BR>
     *         �ݓ��V�K�����s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()<BR>
     *         �̖߂�l<BR>
     * �@@�@@�@@�i�V�[�P���X�}�u�i�ݓ��s�ꃊ�N�G�X�g�j�V�K�������M<BR>
     *        �i���j�v���Q�Ɓj<BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����ݓ������P��.getDataSourceObject().<BR>
     *             get�����o�H�敪()�̖߂�l��<BR>
     * �@@WEB3OrderRootDivDef.HOST�Ɠ������ꍇ�i��SONAR���͒����j<BR>
     * �@@�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���������ʒm�j()<BR>
     *      ���R�[������B<BR>
     * �@@�@@�m�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M<BR>
     *       �i���������ʒm�j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�⏕�����F <BR>
     *        �ݓ��V�K�����s�ꃊ�N�G�X�g���b�Z�[�W.getSubAccount()<BR>
     *        �̖߂�l<BR>
     * �@@�@@�i�V�[�P���X�}�u�i�ݓ��s��ظ��āj�V�K�������M<BR>
     *      �i���������ʒm�j�v���Q�Ɓj<BR>
     * <BR>
     * �R�|�R�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h��<BR>
     *           ����I�������ꍇ�A<BR>
     * �@@DefaultMarketRequestSendResult.newSuccessResultInstance()��<BR>
     *              �߂�l��Ԃ��B<BR>
     * �@@�mnewSuccessResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@���b�Z�[�W�g�[�N��ID�F 0<BR>
     * <BR>
     * �R�|�S�j�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�̊e���\�b�h��<BR>
     *          ��O���X���[�����ꍇ<BR>
     * �@@�|ProcessingResult.newFailedResultInstance()���R�[������<BR>
     *          ProcessingResult�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�mnewFailedResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@�@@�G���[���F ��O�I�u�W�F�N�g.getErrorInfo()�̖߂�l<BR>
     * �@@�|DefaultMarketRequestSendResult.newFailedResultInstance()<BR>
     *               �̖߂�l��Ԃ��B<BR>
     * �@@�@@�mnewFailedResultInstance�ɓn���p�����^�n<BR>
     * �@@�@@�@@�I�y���[�V�������ʁF ��������ProcessingResult�I�u�W�F�N�g<BR>
     * @@param l_request - �ݓ��V�K�����s�ꃊ�N�G�X�g���b�Z�[�W<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@roseuid 406D22920176
     */
    public MarketRequestSendResult send(RuitoNewOrderMarketRequestMessage l_request)
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSumbit(RuitoNewOrderMarketRequestMessage l_request)";

        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("__ParameterError__");

            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            log.exiting(STR_METHOD_NAME);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        if (!isMarketSubmit())
        {
            log.debug("isMarketSubmit == false");
            log.exiting(STR_METHOD_NAME);
            return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp
                .getTradingModule(ProductTypeEnum.RUITO)
                .getOrderManager();

        RuitoOrderUnit l_ruitoOrderUnit = null;
        try
        {
            l_ruitoOrderUnit =
                (RuitoOrderUnit) l_web3RuitoOrderManager.getOrderUnit(
                    l_request.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006);

            log.exiting(STR_METHOD_NAME);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        log.debug("orderRootDiv = " + l_ruitoOrderUnitRow.getOrderRootDiv());

        try
        {
            if (!WEB3OrderRootDivDef
                .HOST
                .equals(l_ruitoOrderUnitRow.getOrderRootDiv()))
            {
                int l_intRuitoType = l_ruitoOrderUnitRow.getRuitoType().intValue();

                log.debug("ruitoType = " + l_intRuitoType);
                log.debug("orderType = " + l_ruitoOrderUnitRow.getOrderType());

                if (l_intRuitoType == RuitoTypeEnum.MRF.intValue())
                {
                    newOrderMarketRequestMessageSubmitMRFSell(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_BUY.equals(
                            l_ruitoOrderUnitRow.getOrderType()))
                {
                    newOrderMarketRequestMessageSubmitBuy(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_SELL.equals(
                            l_ruitoOrderUnitRow.getOrderType()))
                {
                    newOrderMarketRequestMessageSubmitSell(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
            }
            else
            {
                newOrderMarketRequestMessageSubmitTradeOrderNotify(l_ruitoOrderUnit);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0);

    }

    /**
     * �L���[�e�[�u���ւ̏������݁A�s��ւ̑��M���s�����s��Ȃ�����<BR>
     * �w�肷��B<BR>
     * �s���ꍇ�� true ���A�s��Ȃ��ꍇ�� false ���w�肷��B<BR>
     * @@param isMarketSubmit - �L���[�e�[�u���ւ̏������݁A�s��ւ̑��M���s���ꍇ��
     * true ���A<BR>
     * �����łȂ��ꍇ�� false ���w�肷��B<BR>
     *
     * @@return boolean
     * @@roseuid 406D320100E9
     */
    public boolean setMarketSubmit(boolean l_isMarketSubmit)
    {
        isMarketSubmit = l_isMarketSubmit;
        log.debug("l_isMarketSubmit = " + l_isMarketSubmit);
        return isMarketSubmit;
    }

    /**
     * �L���[�e�[�u���ւ̏������݂Ǝs��ւ̑��M���s���ꍇ�� true ���A<BR>
     * �����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 406D33E1008B
     */
    public boolean isMarketSubmit()
    {
        log.debug("isMarketSubmit = " + isMarketSubmit);
        return isMarketSubmit;
    }
    /**
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�iMRF���j<BR>
     * <BR>
     * MRF��񒍕��̎s�ꃊ�N�G�X�g���b�Z�[�W�𑗐M����B<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�C���T�[�g<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̓��e�����ƂɁA<BR>
     *   MRF�����L���[�e�[�u���ɒ����f�[�^��}������B<BR>
     * �@@�iDB�X�V�d�l�uMRF���_MRF�����L���[�e�[�u��.xls�v�Q�Ɓj<BR>
     * <BR>
     * �Q�j�@@�g���K���s<BR>
     * �@@�|�g���K�𔭍s���邩�̃`�F�b�N<BR>
     * �@@�@@������ԊǗ�.is�g���K���s()���R�[�����A<BR>
     *      �߂�l��true�ł���΃g���K�𔭍s����B<BR>
     *      �߂�l��false�ł���Έȍ~�̏������s��Ȃ��B<BR>
     * �@@�@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@���������F<BR>
     * �@@�@@�@@�@@�@@�h0 �F DEFAULT�h<BR>
     * <BR>
     * �@@�|WEB3MQMessageSpec�̐���<BR>
     * �@@�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR><BR>
     * �@@�@@ �،���ЃR�[�h�F ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     *        �̖߂�l<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F �hGI831�h<BR>
     * <BR>
     * �@@�|�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayServiceImpl.send()���R�[�����A<BR>
     *      �g���K�𔭍s����B<BR>
     * �@@�@@�msend�ɓn���p�����^�n<BR>
     * �@@�@@�@@MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N�F ��������<BR>
     *           WEB3MQMessageSpec�I�u�W�F�N�g<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407C98E1022B
     */
    public void newOrderMarketRequestMessageSubmitMRFSell(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSubmitMRFSell(RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostMrfOrderParams l_hostMrfOrderParams = new HostMrfOrderParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strPoductCode = null;
        String l_strReturnMethod = null;        
        long l_lngSellOrderAmount = 0;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        WEB3RuitoProductManager l_ruitoProductManager =
            (WEB3RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();

            log.debug("l_strBranchCode = " + l_strBranchCode);

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();

            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();

            log.debug("l_strAccountCode = " + l_strAccountCode);

            //���҃R�[�h���擾����
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();

            log.debug("l_strTraderCode = " + l_strTraderCode);

            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();

            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //�����R�[�h���擾����
            long productId = l_ruitoOderUnitRow.getProductId();
            log.debug("productId = " + productId);
            RuitoProduct l_product =
                (RuitoProduct) l_ruitoProductManager.getProduct(productId);

            RuitoProductRow l_ruitoProductRow =
                (RuitoProductRow) l_product.getDataSourceObject();
            l_strPoductCode = l_ruitoProductRow.getMrfFundCode();
            log.debug("l_strPoductCode = " + l_strPoductCode);

            //���t���z
            l_lngSellOrderAmount = (long)l_ruitoOderUnitRow.getQuantity();
            log.debug("l_lngSellOrderAmount = " + l_lngSellOrderAmount);

            //�Ԋҕ��@@���擾����
            l_strReturnMethod = l_ruitoOderUnitRow.getReturnMethod();
            log.debug("l_strReturnMethod = " + l_strReturnMethod);

            //�󒍓���
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //�f�[�^�R�[�h
            l_hostMrfOrderParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_SELL);

            //�،���ЃR�[�h
            l_hostMrfOrderParams.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h
            l_hostMrfOrderParams.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h
            l_hostMrfOrderParams.setAccountCode(l_strAccountCode);
            //���҃R�[�h
            l_hostMrfOrderParams.setTraderCode(l_strTraderCode);
            //���ʃR�[�h
            l_hostMrfOrderParams.setOrderRequestNumber(l_strOrderRequestNumber);
            //����
            l_hostMrfOrderParams.setMrfFundCode(l_strPoductCode);
            //�Ԋҋ敪
            l_hostMrfOrderParams.setReturnDiv(" ");
            //�ېŋ敪
            l_hostMrfOrderParams.setTaxDiv(" ");
            //���t���z
            l_hostMrfOrderParams.setSellOrderAmount(l_lngSellOrderAmount);
            //�Ԋҕ��@@
            l_hostMrfOrderParams.setReturnMethod(l_strReturnMethod);
            //�󒍓���
            l_hostMrfOrderParams.setOrderDate(l_tsReceivedDateTime);
            //�����敪
            l_hostMrfOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostMrfOrderParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_SELL + "T");
            log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
            log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_SELL + "T");
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�j<BR>
     * <BR>
     * �������t�@@���h�EMMF���t�����̎s�ꃊ�N�G�X�g���b�Z�[�W��<BR>
     * ���M����B<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�C���T�[�g<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̓��e�����ƂɁA<BR>
     *    �ݓ������L���[�e�[�u��<BR>
     * �@@�ɒ����f�[�^��}������B<BR>
     * �@@�iDB�X�V�d�l�u�ݐϓ������t_�ݓ������L���[�e�[�u��.xls�v�Q�Ɓj<BR>
     * <BR>
     * �Q�j�@@�g���K���s<BR>
     * �@@�|�g���K�𔭍s���邩�̃`�F�b�N<BR>
     * �@@�@@������ԊǗ�.is�g���K���s()���R�[�����A<BR>
     *     �߂�l��true�ł���΃g���K��<BR>
     * �@@�@@���s����B�߂�l��false�ł���Έȍ~�̏������s��Ȃ��B<BR>
     * �@@�@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@���������F<BR>
     * �@@�@@�@@�@@�@@�h0 �F DEFAULT�h<BR>
     * <BR>
     * �@@�|WEB3MQMessageSpec�̐���<BR>
     * �@@�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@ �،���ЃR�[�h�F ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     *           �̖߂�l<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F �hDI801�h<BR>
     * <BR>
     * �@@�|�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayServiceImpl.send()���R�[�����A<BR>
     *     �g���K�𔭍s����B<BR>
     * �@@�@@�msend�ɓn���p�����^�n<BR>
     * �@@�@@�@@MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N�F <BR>
     *        ��������WEB3MQMessageSpec�I�u�W�F�N�g<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407C98F502E4
     */
    public void newOrderMarketRequestMessageSubmitBuy(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSubmitBuy(RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";

        log.entering(STR_METHOD_NAME);
        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostRuitoOrderParams l_hostRuitoOrderParams =
            new HostRuitoOrderParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strReturnMethod = null;
        String l_strTaxType = null;
        String l_strCourse = null;
        String l_strPlan = null;
        //int l_intPayAmount = 0;
        long l_lngPayAmount = 0;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //���҃R�[�h���擾����
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();
            log.debug("l_strTraderCode = " + l_strTraderCode);

            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //�R�[�X
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getProduct(
                    l_ruitoOderUnitRow.getProductId());
            l_strCourse = l_ruitoProduct.getCourse();
            log.debug("l_strCourse = " + l_strCourse);

            //�v����
            l_strPlan = l_ruitoProduct.getPlan();
            log.debug("l_strPlan = " + l_strPlan);

            //�����z
            l_lngPayAmount = (long) l_ruitoOderUnitRow.getQuantity();
            log.debug("l_intPayAmount = " + l_lngPayAmount);

            //�Ԋҕ��@@���擾����
            l_strReturnMethod = l_ruitoOderUnitRow.getReturnMethod();
            log.debug("l_strReturnMethod = " + l_strReturnMethod);

            //�󒍓���
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //�f�[�^�R�[�h
            l_hostRuitoOrderParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_BUY);
            //�،���ЃR�[�h
            l_hostRuitoOrderParams.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h
            l_hostRuitoOrderParams.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h
            l_hostRuitoOrderParams.setAccountCode(l_strAccountCode);
            //���҃R�[�h
            l_hostRuitoOrderParams.setTraderCode(l_strTraderCode);
            //���ʃR�[�h
            l_hostRuitoOrderParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //�R�[�X
            l_hostRuitoOrderParams.setCourse(l_strCourse);
            //�v����
            l_hostRuitoOrderParams.setPlan(l_strPlan);
            //�����z
            l_hostRuitoOrderParams.setPayAmount(l_lngPayAmount);
            //���ϋ敪
            l_hostRuitoOrderParams.setSettleDiv(WEB3SettleDivDef.TRANSFER);
            //�����敪
            l_hostRuitoOrderParams.setPayDiv(" ");
            //���t����
            l_hostRuitoOrderParams.setProduct("  ");
            //��n�o�H
            l_hostRuitoOrderParams.setTransferRoute(
                WEB3TransferRouteDef.CUSTOM_ACCOUNT);
            //�ېŋ敪
            l_hostRuitoOrderParams.setTaxDiv(" ");
            //�抷�敪
            l_hostRuitoOrderParams.setConvDiv(" ");
            //����
            l_hostRuitoOrderParams.setPartner("  ");
            //��������
            l_hostRuitoOrderParams.setPayDate(null);
            //�󒍓���
            l_hostRuitoOrderParams.setOrderDate(l_tsReceivedDateTime);
            //�����敪
            l_hostRuitoOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostRuitoOrderParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_BUY + "T",
					l_strCourse);
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_BUY + "T");
			log.debug("UserData =" + l_strCourse);
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���j<BR>
     * <BR>
     * �������t�@@���h�EMMF��񒍕��̎s�ꃊ�N�G�X�g���b�Z�[�W��<BR>
     * ���M����B<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�C���T�[�g<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̓��e�����ƂɁA<BR>
     *   �ݓ����L���[�e�[�u<BR>
     * �@@�ɒ����f�[�^��}������B<BR>
     * �@@�iDB�X�V�d�l�u�ݐϓ������_�ݓ����L���[�e�[�u��.xls�v�Q�Ɓj<BR>
     * <BR>
     * �Q�j�@@�g���K���s<BR>
     * �@@�|�g���K�𔭍s���邩�̃`�F�b�N<BR>
     * �@@�@@������ԊǗ�.is�g���K���s()���R�[�����A<BR>
     *      �߂�l��true�ł���΃g���K��<BR>
     * �@@�@@���s����B�߂�l��false�ł���Έȍ~�̏������s��Ȃ��B<BR>
     * �@@�@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@���������F<BR>
     * �@@�@@�@@�@@�@@�h0 �F DEFAULT�h<BR>
     * <BR>
     * �@@�|WEB3MQMessageSpec�̐���<BR>
     * �@@�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@ �،���ЃR�[�h�F ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     *            �̖߂�l<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F �hDI803�h<BR>
     * <BR>
     * �@@�|�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayServiceImpl.send()���R�[�����A<BR>
     *     �g���K�𔭍s����B<BR>
     * �@@�@@�msend�ɓn���p�����^�n<BR>
     * �@@�@@�@@MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N�F <BR>
     *        ��������WEB3MQMessageSpec�I�u�W�F�N�g<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407C99080042
     */
    public void newOrderMarketRequestMessageSubmitSell(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSubmitSell(RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostRuitoSellParams l_hostRuitoSellParams = new HostRuitoSellParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strReturnMethod = null;
        String l_strTaxType = null;
        String l_strCourse = null;
        String l_strPlan = null;
        String l_strReturnDiv = null;
        long l_lngAmount = 0;
        long l_lngQuantity = 0;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_ruitoOderUnitRow.getOrderId() = " + l_ruitoOderUnitRow.getOrderId());
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //���҃R�[�h���擾����
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();
            log.debug("l_strTraderCode = " + l_strTraderCode);

            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //�R�[�X���擾����
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getProduct(
                    l_ruitoOderUnitRow.getProductId());
            l_strCourse = l_ruitoProduct.getCourse();
            log.debug("l_strCourse = " + l_strCourse);

            //�v�������擾����
            l_strPlan = l_ruitoProduct.getPlan();
            log.debug("l_strPlan = " + l_strPlan);

            //�Ԋҋ敪���擾����
            //�ݓ������P��.�ݓ����敪==�h2�F�S���w��h�̏ꍇ�� 1�F�S�����(MAXAS�ɂ�'2'�ɕϊ�)
            //����ȊO�̏ꍇ�� 3�F���z�w����(�ꕔ���)

            if (WEB3SellDivDef
                .ALL_DESIGNATE
                .equals(l_ruitoOderUnitRow.getGpSellDiv()))
            {
                l_strReturnDiv = WEB3OrderDivTypeDef.ALL_CANCEL;
            }
            else
            {
                l_strReturnDiv = WEB3OrderDivTypeDef.PARTIALLY_CANCEL;
            }
            log.debug("l_strReturnDiv = " + l_strReturnDiv);

            //�����z���擾����
            //�ݓ������P��.�ݓ����敪��"4:�����w��"�̏ꍇ�ݓ������P��.��������
            //����ȊO�̏ꍇ��0
            if (WEB3SellDivDef
                .MONEY_DESIGNATE
                .equals(l_ruitoOderUnitRow.getGpSellDiv()))
            {
                l_lngAmount = (long) l_ruitoOderUnitRow.getQuantity();
            }
            else
            {
                l_lngAmount = 0;
            }
            log.debug("l_lngAmount = " + l_lngAmount);

            //���ʂ��擾����
            //�ݓ������P��.�ݓ����敪��"3:���z�w��"�̏ꍇ�ݓ������P��.��������
            //����ȊO�̏ꍇ��0
            if (WEB3SellDivDef.COUNT_DESIGNATE.equals(
                l_ruitoOderUnitRow.getGpSellDiv()))
            {
                l_lngQuantity = (long) l_ruitoOderUnitRow.getQuantity();
            }
            else
            {
                l_lngQuantity = 0;
            }
            log.debug("l_lngQuantity = " + l_lngQuantity);

            //�󒍓������擾����
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //�f�[�^�R�[�h
            l_hostRuitoSellParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL);
            //�،���ЃR�[�h
            l_hostRuitoSellParams.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h
            l_hostRuitoSellParams.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h
            l_hostRuitoSellParams.setAccountCode(l_strAccountCode);
            //���҃R�[�h
            l_hostRuitoSellParams.setTraderCode(l_strTraderCode);
            //���ʃR�[�h
            l_hostRuitoSellParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //�R�[�X
            l_hostRuitoSellParams.setCourse(l_strCourse);
            //�v����
            l_hostRuitoSellParams.setPlan(l_strPlan);
            //�Z���敪
            l_hostRuitoSellParams.setLoanDiv(" ");
            //�����E��
            l_hostRuitoSellParams.setProductIssueCode("         ");
            //�ېŋ敪
            l_hostRuitoSellParams.setTaxDiv(" ");
            //�Ԋҋ敪
            l_hostRuitoSellParams.setReturnDiv(l_strReturnDiv);
            //�����z
            l_hostRuitoSellParams.setAmount(l_lngAmount);
            //����
            l_hostRuitoSellParams.setQuantity(l_lngQuantity);
            //�S�۔��p
            l_hostRuitoSellParams.setMortgageSell(" ");
            //��n��
            l_hostRuitoSellParams.setDeliveryDate(null);
            //�󒍓���
            l_hostRuitoSellParams.setOrderDate(l_tsReceivedDateTime);
            //�����敪
            l_hostRuitoSellParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostRuitoSellParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL + "T",
					l_strCourse);
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL + "T");
			log.debug("UserData =" + l_strCourse);
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���������ʒm�j<BR>
     * <BR>
     * MRF��񒍕��̎s�ꃊ�N�G�X�g���b�Z�[�W�𑗐M����B<BR>
     * <BR>
     * �P�j�@@�ݓ��s�ꉞ���R�[���o�b�N�T�[�r�X�I�u�W�F�N�g�̎擾<BR>
     * �@@RuitoMarketResponseReceiverCallbackService( )<BR>
     * �@@���R�[�����A�ݓ��s�ꉞ���R�[���o�b�N�T�[�r�X�I�u�W�F�N�g<BR>
     *   ���擾����B<BR>
     * <BR>
     * �Q�j�@@DefaultNewOrderAcceptedMarketResponseMessage<BR>
     * �@@�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@����ID�F ����.�ݓ��������e.getOrderId()�̖߂�l<BR>
     * <BR>
     * �R�j�@@�ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �S�j�@@�ݓ���t�m��C���^�Z�v�^.set�����G���[���R�R�[�h()���R�[�����A<BR>
     * �����G���[�R�[�h��<BR>
     * �@@�ݒ���s���B<BR>
     * �@@[set�����G���[���R�R�[�h�ɓn���p�����^]<BR>
     * �@@�@@�����G���[���R�R�[�h�F null<BR>
     * <BR>
     * �T�j�@@�g���ݓ������}�l�[�W��<BR>
     * .setThreadLocalPersistenceEventInterceptor()<BR>
     * ���R�[�����A�C���^�Z�v�^�̐ݒ���s���B<BR>
     * �@@[setThreadLocalPersistenceEventInterceptor�ɓn���p�����^]<BR>
     * �@@�@@�ݓ���t�m��C���^�Z�v�^�F ���������ݓ���t�m��C���^�Z�v�^<BR>
     * <BR>
     * �U�j�@@RuitoMarketResponseReceiverCallbackService.process()<BR>
     *       ���R�[������B<BR>
     * �@@�mprocess�ɓn���p�����^�n<BR>
     * �@@�@@�V�K������t�ώs�ꉞ�����b�Z�[�W�F <BR>
     * �@@�@@�@@��������<BR>
     * DefaultNewOrderAcceptedMarketResponseMessage<BR>
     *            �I�u�W�F�N�g<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407C991A015A
     */
    public void newOrderMarketRequestMessageSubmitTradeOrderNotify(RuitoOrderUnit l_ruitoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSubmitTradeOrderNotify("
                + "RuitoOrderUnit l_ruitoOrderUnit)";

        log.entering(STR_METHOD_NAME);
        if (l_ruitoOrderUnit == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        MarketAdapter l_marketAdaptor =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO).getMarketAdapter();
        RuitoMarketResponseReceiverCallbackService l_callBackService =
            (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();

        DefaultNewOrderAcceptedMarketResponseMessage l_defaultNewOrderAcceptedMarketResponseMessage =
            new DefaultNewOrderAcceptedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

        WEB3RuitoAcceptedDecisionInterceptor l_web3RuitoAcceptedDecisionInterceptor =
            new WEB3RuitoAcceptedDecisionInterceptor();
        l_web3RuitoAcceptedDecisionInterceptor.setOrderErrorReasonCode(null);

        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp
                .getTradingModule(ProductTypeEnum.RUITO)
                .getOrderManager();

        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_web3RuitoAcceptedDecisionInterceptor);
        l_callBackService.process(
            l_defaultNewOrderAcceptedMarketResponseMessage);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�iMRF���F�s�ꑗ�M����j<BR>
     * <BR>
     * MRF��������̎s�ꃊ�N�G�X�g���b�Z�[�W�𑗐M����B<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�C���T�[�g<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̓��e�����ƂɁA<BR>
     *          MRF��������L���[�e�[�u���ɒ����f�[�^��}������B<BR>
     * �@@�iDB�X�V�d�l�uMRF���_MRF��������L���[�e�[�u��.xls�v�Q�Ɓj<BR>
     * <BR>
     * �Q�j�@@�g���K���s<BR>
     * �@@�|�g���K�𔭍s���邩�̃`�F�b�N<BR>
     * �@@�@@������ԊǗ�.is�g���K���s()���R�[�����A<BR>
     *     �߂�l��true�ł���΃g���K��<BR>
     * �@@�@@���s����B�߂�l��false�ł���Έȍ~�̏������s��Ȃ��B<BR>
     * �@@�@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@���������F<BR>
     * �@@�@@�@@�@@�@@�h0 �F DEFAULT�h<BR>
     * <BR>
     * �@@�|WEB3MQMessageSpec�̐���<BR>
     * �@@�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@ �،���ЃR�[�h�F ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     *           �̖߂�l<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F �hGI831�h<BR>
     * <BR>
     * �@@�|�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayServiceImpl.send()���R�[�����A�g���K�𔭍s����B<BR>
     * �@@�@@�msend�ɓn���p�����^�n<BR>
     * �@@�@@�@@MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N�F <BR>
     *      ��������WEB3MQMessageSpec�I�u�W�F�N�g<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A7FC01C8
     */
    public void cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostMrfOrderCancelParams l_hostMrfOrderCancelParams =
            new HostMrfOrderCancelParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strPoductCode = null;
        String l_strReturnMethod = null;
        String l_strTaxType = null;
        int l_intSellOrderAmount = 0;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //�����R�[�h���擾����
            long productId = l_ruitoOderUnitRow.getProductId();
            log.debug("productId = " + productId);
            RuitoProduct l_product =
                (RuitoProduct) l_ruitoProductManager.getProduct(productId);

            RuitoProductRow l_ruitoProductRow =
                (RuitoProductRow) l_product.getDataSourceObject();
            l_strPoductCode = l_ruitoProductRow.getMrfFundCode();
            log.debug("l_strPoductCode = " + l_strPoductCode);

            //�󒍓������擾����
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //�f�[�^�R�[�h
            l_hostMrfOrderCancelParams.setRequestCode(
                WEB3HostRequestCodeDef
                    .RUITO_REQUEST_MESSAGE_SEND_MRF_ORDER_CANCEL);
            //�،���ЃR�[�h
            l_hostMrfOrderCancelParams.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h
            l_hostMrfOrderCancelParams.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h
            l_hostMrfOrderCancelParams.setAccountCode(l_strAccountCode);
            //���҃R�[�h
            l_hostMrfOrderCancelParams.setTraderCode(l_ruitoOderUnitRow.getSonarTraderCode());
            //���ʃR�[�h
            l_hostMrfOrderCancelParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //����
            l_hostMrfOrderCancelParams.setMrfFundCode(l_strPoductCode);
            //����敪
            l_hostMrfOrderCancelParams.setCancelDiv("-");
            //�󒍓���
            l_hostMrfOrderCancelParams.setOrderDate(l_tsReceivedDateTime);
            //�����敪
            l_hostMrfOrderCancelParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostMrfOrderCancelParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_ORDER_CANCEL + "T");
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_ORDER_CANCEL + "T");
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�F�s�ꑗ�M����j<BR>
     * <BR>
     * �������t�@@���h�EMMF���t��������̎s�ꃊ�N�G�X�g���b�Z�[�W<BR>
     * �𑗐M����B<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�C���T�[�g<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̓��e�����ƂɁA<BR>
     *   �ݓ���������L���[�e�[�u��<BR>
     * �@@�ɒ����f�[�^��}������B<BR>
     * �@@�iDB�X�V�d�l<BR>
     *     �u�ݐϓ������_�ݓ���������L���[�e�[�u��.xls�v�Q�Ɓj<BR>
     * <BR>
     * �Q�j�@@�g���K���s<BR>
     * �@@�|�g���K�𔭍s���邩�̃`�F�b�N<BR>
     * �@@�@@������ԊǗ�.is�g���K���s()���R�[�����A<BR>
     *      �߂�l��true�ł���΃g���K��<BR>
     * �@@�@@���s����B�߂�l��false�ł���Έȍ~�̏������s��Ȃ��B<BR>
     * �@@�@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@���������F<BR>
     * �@@�@@�@@�@@�@@�h0 �F DEFAULT�h<BR>
     * <BR>
     * �@@�|WEB3MQMessageSpec�̐���<BR>
     * �@@�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F <BR>
     *       ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     *         �̖߂�l<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F �hDI801�h<BR>
     * <BR>
     * �@@�|�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayServiceImpl.send()���R�[�����A<BR>
     *     �g���K�𔭍s����B<BR>
     * �@@�@@�msend�ɓn���p�����^�n<BR>
     * �@@�@@�@@MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N�F <BR>
     *        ��������WEB3MQMessageSpec�I�u�W�F�N�g<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A84401A9
     */
    public void cancelOrderMarketRequestMessageSubmitBuyHasMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostRuitoOrderCancelParams l_hostRuitoOrderCancelParams =
            new HostRuitoOrderCancelParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strCourse = null;
        String l_strPlan = null;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //���҃R�[�h���擾����
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();
            log.debug("l_strTraderCode = " + l_strTraderCode);

            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //�R�[�X���擾����
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getProduct(
                    l_ruitoOderUnitRow.getProductId());
            l_strCourse = l_ruitoProduct.getCourse();
            log.debug("l_strCourse = " + l_strCourse);

            //�v�������擾����
            l_strPlan = l_ruitoProduct.getPlan();
            log.debug("l_strPlan = " + l_strPlan);

            //�󒍓������擾����
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //�f�[�^�R�[�h
            l_hostRuitoOrderCancelParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_ORDER_CANCEL);
            //�،���ЃR�[�h
            l_hostRuitoOrderCancelParams.setInstitutionCode(
                l_strInstitutionCode);
            //���X�R�[�h
            l_hostRuitoOrderCancelParams.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h
            l_hostRuitoOrderCancelParams.setAccountCode(l_strAccountCode);
            //���҃R�[�h
            l_hostRuitoOrderCancelParams.setTraderCode(l_strTraderCode);
            //���ʃR�[�h
            l_hostRuitoOrderCancelParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //�R�[�X
            l_hostRuitoOrderCancelParams.setCourse(l_strCourse);
            //�v����
            l_hostRuitoOrderCancelParams.setPlan(l_strPlan);
            //����敪
            l_hostRuitoOrderCancelParams.setCancelDiv("-");
            //�󒍓���
            l_hostRuitoOrderCancelParams.setOrderDate(l_tsReceivedDateTime);
            //�����敪
            l_hostRuitoOrderCancelParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostRuitoOrderCancelParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_ORDER_CANCEL + "T",
					l_strCourse);
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_ORDER_CANCEL + "T");
			log.debug("UserData =" + l_strCourse);
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���F�s�ꑗ�M����j<BR>
     * <BR>
     * �������t�@@���h�EMMF��񒍕��̎s�ꃊ�N�G�X�g���b�Z�[�W�𑗐M����B<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�C���T�[�g<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̓��e�����ƂɁA<BR>
     *   �ݓ�������L���[�e�[�u<BR>
     * �@@�ɒ����f�[�^��}������B<BR>
     * �@@�iDB�X�V�d�l<BR>
     *     �u�ݐϓ������_�ݓ�������L���[�e�[�u��.xls�v�Q�Ɓj<BR>
     * <BR>
     * �Q�j�@@�g���K���s<BR>
     * �@@�|�g���K�𔭍s���邩�̃`�F�b�N<BR>
     * �@@�@@������ԊǗ�.is�g���K���s()���R�[�����A<BR>
     *     �߂�l��true�ł���΃g���K��<BR>
     * �@@�@@���s����B�߂�l��false�ł���Έȍ~�̏������s��Ȃ��B<BR>
     * �@@�@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@���������F<BR>
     * �@@�@@�@@�@@�@@�h0 �F DEFAULT�h<BR>
     * <BR>
     * �@@�|WEB3MQMessageSpec�̐���<BR>
     * �@@�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F <BR>
     *      ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     *           �̖߂�l<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F �hDI803�h<BR>
     * <BR>
     * �@@�|�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayServiceImpl.send()���R�[�����A<BR>
     *      �g���K�𔭍s����B<BR>
     * �@@�@@�msend�ɓn���p�����^�n<BR>
     * �@@�@@�@@MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N�F <BR>
     *        ��������WEB3MQMessageSpec�I�u�W�F�N�g<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A87A00CE
     */
    public void cancelOrderMarketRequestMessageSubmitSellHasMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostSellCancelParams l_hostSellCancelParams =
            new HostSellCancelParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strCourse = null;
        String l_strPlan = null;
        String l_strReturnMethod = null;
        String l_strTaxType = null;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //���҃R�[�h���擾����
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();
            log.debug("l_strTraderCode = " + l_strTraderCode);

            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //�R�[�X���擾����
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getProduct(
                    l_ruitoOderUnitRow.getProductId());
            l_strCourse = l_ruitoProduct.getCourse();
            log.debug("l_strCourse = " + l_strCourse);

            //�v�������擾����
            l_strPlan = l_ruitoProduct.getPlan();
            log.debug("l_strPlan = " + l_strPlan);

            //�󒍓������擾����
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //�f�[�^�R�[�h
            l_hostSellCancelParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL_CANCEL);
            //�،���ЃR�[�h
            l_hostSellCancelParams.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h
            l_hostSellCancelParams.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h
            l_hostSellCancelParams.setAccountCode(l_strAccountCode);
            //���҃R�[�h
            l_hostSellCancelParams.setTraderCode(l_strTraderCode);
            //���ʃR�[�h
            l_hostSellCancelParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //�R�[�X
            l_hostSellCancelParams.setCourse(l_strCourse);
            //�v����
            l_hostSellCancelParams.setPlan(l_strPlan);
            //�Z���敪
            l_hostSellCancelParams.setLoanDiv(null);
            //�����E��
            l_hostSellCancelParams.setProductIssueCode(null);
            //����敪
            l_hostSellCancelParams.setCancelDiv("-");
            //�󒍓���
            l_hostSellCancelParams.setOrderDate(l_tsReceivedDateTime);
            //�����敪
            l_hostSellCancelParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostSellCancelParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL_CANCEL + "T",
					l_strCourse);
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL_CANCEL + "T");
			log.debug("UserData =" + l_strCourse);
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�iMRF���F�s�ꑗ�M�Ȃ��j<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�폜<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̎��ʃR�[�h�ɑΉ�����<BR>
     *               �L���[�f�[�^�����݂���ꍇ�A<BR>
     * �@@MRF�����L���[�e�[�u���̃f�[�^���폜����B<BR>
     * �@@�m�폜�����n<BR>
     * �@@�@@�،���ЃR�[�h = <BR>
     * ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l AND<BR>
     * �@@�@@���X�R�[�h = <BR>
     * ����.�⏕����.getMainAccount().getBranch().getBranchCode()<BR>
     *          �̖߂�l AND<BR>
     * �@@�@@�ڋq�R�[�h = ����.�⏕����.getMainAccount().getAccountCode()<BR>
     *          �̖߂�l AND<BR>
     * �@@�@@���ʃR�[�h = ����.�ݓ������P��.getDataSourceObject().get���ʃR�[�h()<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A89D0217
     */
    public void cancelOrderMarketRequestMessageSubmitMRFSellNotMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitMRFSellNotMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit, SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //����.�ݓ������P�ʃI�u�W�F�N�g�̎��ʃR�[�h�ɑΉ�����L���[�f�[�^�����݂���ꍇ��
        //MRF�����L���[�e�[�u���̃f�[�^���폜����
        try
        {
            // MRF�����L���[�e�[�u�����A�ȉ��̏����Ɉ�v����s���폜����B
            // �،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l
            // ���X�R�[�h = ����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
            // �ڋq�R�[�h = ����.�⏕����.getMainAccount().getAccountCode()�̖߂�l
            // ���ʃR�[�h = ����.�ݓ������P��.getDataSourceObject().get���ʃR�[�h()
            QueryProcessor processor = Processors.getDefaultProcessor();
            String l_strWhere =
                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
            String[] l_strBindValues = new String[4];
            l_strBindValues[0] =
                l_subAccount.getInstitution().getInstitutionCode();
            log.debug("InstitutionCode = " + l_strBindValues[0]);
            l_strBindValues[1] =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            log.debug("BranchCode = " + l_strBindValues[1]);
            l_strBindValues[2] = l_subAccount.getMainAccount().getAccountCode();
            log.debug("AccountCode = " + l_strBindValues[2]);
            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
            l_strBindValues[3] = l_ruitoOrderUnitRow.getOrderRequestNumber();
            log.debug("OrderRequestNumber = " + l_strBindValues[3]);

            //�Ώۃf�[�^�폜
            processor.doDeleteAllQuery(
                HostMrfOrderParams.TYPE,
                l_strWhere,
                l_strBindValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�F�s�ꑗ�M�Ȃ��j<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�폜<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̎��ʃR�[�h��<BR>
     * �Ή�����L���[�f�[�^�����݂���ꍇ�́A<BR>
     * �@@�ݓ������L���[�e�[�u���̃f�[�^���폜����B<BR>
     * �@@�m�폜�����n<BR>
     * �@@�@@�،���ЃR�[�h = <BR>
     *      ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l AND<BR>
     * �@@�@@���X�R�[�h = <BR>
     *     ����.�⏕����.getMainAccount().getBranch().getBranchCode()<BR>
     *     �̖߂�l AND<BR>
     * �@@�@@�ڋq�R�[�h = <BR>
     *    ����.�⏕����.getMainAccount().getAccountCode()�̖߂�l AND<BR>
     * �@@�@@���ʃR�[�h = <BR>
     *    ����.�ݓ������P��.getDataSourceObject().get���ʃR�[�h()<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A89D0226
     */
    public void cancelOrderMarketRequestMessageSubmitBuyNotMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitBuyNotMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit, SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //����.�ݓ������P�ʃI�u�W�F�N�g�̎��ʃR�[�h�ɑΉ�����L���[�f�[�^�����݂���ꍇ��
        //MRF�����L���[�e�[�u���̃f�[�^���폜����
        try
        {
            // MRF�����L���[�e�[�u�����A�ȉ��̏����Ɉ�v����s���폜����B
            // �،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l
            // ���X�R�[�h = ����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
            // �ڋq�R�[�h = ����.�⏕����.getMainAccount().getAccountCode()�̖߂�l
            // ���ʃR�[�h = ����.�ݓ������P��.getDataSourceObject().get���ʃR�[�h()
            QueryProcessor processor = Processors.getDefaultProcessor();
            String l_strWhere =
                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
            String[] l_strBindValues = new String[4];
            l_strBindValues[0] =
                l_subAccount.getInstitution().getInstitutionCode();
            log.debug("InstitutionCode = " + l_strBindValues[0]);
            l_strBindValues[1] =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            log.debug("BranchCode = " + l_strBindValues[1]);
            l_strBindValues[2] = l_subAccount.getMainAccount().getAccountCode();
            log.debug("AccountCode = " + l_strBindValues[2]);
            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
            l_strBindValues[3] = l_ruitoOrderUnitRow.getOrderRequestNumber();
            log.debug("OrderRequestNumber = " + l_strBindValues[3]);

            //�Ώۃf�[�^�폜
            processor.doDeleteAllQuery(
                HostRuitoOrderParams.TYPE,
                l_strWhere,
                l_strBindValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��������s�ꃊ�N�G�X�g���b�Z�[�W���M�i���F�s�ꑗ�M�Ȃ��j<BR>
     * <BR>
     * �P�j�@@�L���[�f�[�^�폜<BR>
     * �@@����.�ݓ������P�ʃI�u�W�F�N�g�̎��ʃR�[�h��<BR>
     *            �Ή�����L���[�f�[�^�����݂���ꍇ�́A<BR>
     * �@@�ݓ����L���[�e�[�u���̃f�[�^���폜����B<BR>
     * �@@�m�폜�����n<BR>
     * �@@�@@�،���ЃR�[�h = <BR>
     *     ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     *          �̖߂�l AND<BR>
     * �@@�@@���X�R�[�h = <BR>
     *    ����.�⏕����.getMainAccount().getBranch().getBranchCode()<BR>
     *           �̖߂�l AND<BR>
     * �@@�@@�ڋq�R�[�h = <BR>
     *    ����.�⏕����.getMainAccount().getAccountCode()<BR>
     *           �̖߂�l AND<BR>
     * �@@�@@���ʃR�[�h = <BR>
     *    ����.�ݓ������P��.getDataSourceObject().get���ʃR�[�h()<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A89D0236
     */
    public void cancelOrderMarketRequestMessageSubmitSellNotMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitSellNotMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit, SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //����.�ݓ������P�ʃI�u�W�F�N�g�̎��ʃR�[�h�ɑΉ�����L���[�f�[�^�����݂���ꍇ��
        //MRF�����L���[�e�[�u���̃f�[�^���폜����
        try
        {
            // MRF�����L���[�e�[�u�����A�ȉ��̏����Ɉ�v����s���폜����B
            // �،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l
            // ���X�R�[�h = ����.�⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
            // �ڋq�R�[�h = ����.�⏕����.getMainAccount().getAccountCode()�̖߂�l
            // ���ʃR�[�h = ����.�ݓ������P��.getDataSourceObject().get���ʃR�[�h()
            QueryProcessor processor = Processors.getDefaultProcessor();
            String l_strWhere =
                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
            String[] l_strBindValues = new String[4];
            l_strBindValues[0] =
                l_subAccount.getInstitution().getInstitutionCode();
            log.debug("InstitutionCode = " + l_strBindValues[0]);
            l_strBindValues[1] =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            log.debug("BranchCode = " + l_strBindValues[1]);
            l_strBindValues[2] = l_subAccount.getMainAccount().getAccountCode();
            log.debug("AccountCode = " + l_strBindValues[2]);
            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
            l_strBindValues[3] = l_ruitoOrderUnitRow.getOrderRequestNumber();
            log.debug("OrderRequestNumber = " + l_strBindValues[3]);

            //�Ώۃf�[�^�폜
            processor.doDeleteAllQuery(
                HostRuitoSellParams.TYPE,
                l_strWhere,
                l_strBindValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
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
