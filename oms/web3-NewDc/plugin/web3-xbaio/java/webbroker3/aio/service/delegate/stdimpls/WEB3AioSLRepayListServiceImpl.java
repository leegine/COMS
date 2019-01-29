head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍψꗗ�T�[�r�XImpl(WEB3AioSLRepayListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �d�l�ύX�E���f��No.757,775,791,794
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.define.WEB3AioCancelEnableFlagDef;
import webbroker3.aio.message.WEB3SLRepayCancelListRequest;
import webbroker3.aio.message.WEB3SLRepayCancelListResponse;
import webbroker3.aio.message.WEB3SLRepayUnit;
import webbroker3.aio.service.delegate.WEB3AioSLRepayListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�،��S�ۃ��[���ԍψꗗ�T�[�r�XImpl)<BR>
 * �،��S�ۃ��[���ԍψꗗ�T�[�r�X�����N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSLRepayListServiceImpl extends WEB3ClientRequestService
    implements WEB3AioSLRepayListService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayListServiceImpl.class);

    /**
     * @@roseuid 46E8908400D7
     */
    public WEB3AioSLRepayListServiceImpl()
    {

    }

    /**
     * �،��S�ۃ��[���ԍψꗗ�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��S�ۃ��[���ԍψꗗ�j�ꗗ��ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍψꗗ�T�[�r�X�jexecute<BR>
     * ��̈ʒu�Fis�،��S�ۃ��[�������J��( )<BR>
     * �@@�@@�߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@roseuid 46DE36D90377
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

        WEB3SLRepayCancelListRequest l_slRepayCancelListRequest =
            (WEB3SLRepayCancelListRequest)l_request;
        // �،��S�ۃ��[���ԍψꗗ�T�[�r�X���������{����B
        // �u�i�،��S�ۃ��[���ԍψꗗ�j�ꗗ��ʕ\���f�[�^�擾�v�Q�ƁB
        // get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum
        // �⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //  validate����(SubAccount)
        // �⏕�����F get�⏕����()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.validateOrder(l_subAccount);

        // getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        // is�،��S�ۃ��[���������J�݂��Ă��邩�`�F�b�N����B
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

        // get������( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        // �⏕����.getSubAccountId()�̖߂�l
        // 1020�i�U�֒����i�a���������I���b�N�X�N���W�b�g�j�j
        Long l_subAccountId = new Long(l_subAccount.getSubAccountId());
        Object[] l_objValues = new Object[]{l_subAccountId, OrderTypeEnum.TO_ORIX_CREDIT};

        // doFindAllQuery(Row�^�C�v : String, Where : String, orderBy : String, condition : String, ���X�g : Object[])
        // Row�^�C�v�F AioOrderUnitRow.TYPE
        // Where�F "sub_account_id=? and order_type=?"
        // orderBy�F "received_date_time"
        // condition�F null
        // ���X�g�F �ȉ��̍��ڂ̃��X�g
        List l_lisAioOrderUnitRows = null;
        try
        {
            // getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisAioOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    "sub_account_id=? and order_type=?",
                    "received_date_time",
                    null,
                    l_objValues);
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

        // ArrayList
        List l_lisResults = new ArrayList();

        // �����P��Params�̗v�f����Loop
        int l_intLength = l_lisAioOrderUnitRows.size();
        for (int i = 0; i < l_intLength; i++)
        {
            // �،��S�ۃ��[���ԍϖ���( )
            // �،��S�ۃ��[���ԍϖ��׃C���X�^���X�𐶐�����B
            WEB3SLRepayUnit l_slRepayUnit = new WEB3SLRepayUnit();

            // get����\�t���O(�����P��Params, Date)
            // �����P��Params�F �����P��Params
            // �������F get������( )�̖߂�l
            AioOrderUnitParams l_aioOrderUnitParams =
                (AioOrderUnitParams)l_lisAioOrderUnitRows.get(i);
            String l_strCancelPossibleFlag =
                this.getCancelEnableFlag(l_aioOrderUnitParams, l_datOrderBizDate);

            // �v���p�e�B�Z�b�g
            // �،��S�ۃ��[���ԍϖ���.����ID = �����P��Params.����ID
            l_slRepayUnit.orderId =
                WEB3StringTypeUtility.formatNumber(l_aioOrderUnitParams.getOrderId());
            // �،��S�ۃ��[���ԍϖ���.��t���� = �����P��Params.�󒍓���
            l_slRepayUnit.receptionDate = l_aioOrderUnitParams.getReceivedDateTime();
            // �،��S�ۃ��[���ԍϖ���.�ԍϗ\��� = .�����P��Params.�U�֗\���
            l_slRepayUnit.repayScheduledDate = l_aioOrderUnitParams.getEstTransferDate();
            // �،��S�ۃ��[���ԍϖ���.�ԍϊz = �����P��Params.����
            l_slRepayUnit.repayAmt =
                WEB3StringTypeUtility.formatNumber(l_aioOrderUnitParams.getQuantity());
            // �،��S�ۃ��[������\�t���O = get����\�t���O()�̖߂�l
            l_slRepayUnit.cancelFlag = l_strCancelPossibleFlag;

            // add(arg0 : Object)
            l_lisResults.add(l_slRepayUnit);
        }

        // toArray( )
        WEB3SLRepayUnit[] l_slRepayUnits = new WEB3SLRepayUnit[l_lisResults.size()];
        l_lisResults.toArray(l_slRepayUnits);

        // createResponse( )
        WEB3SLRepayCancelListResponse l_slRepayCancelListResponse =
            (WEB3SLRepayCancelListResponse)l_slRepayCancelListRequest.createResponse();

        // �v���p�e�B�Z�b�g
        // ���X�|���X.�،��S�ۃ��[���ԍϖ��� = �،��S�ۃ��[���ԍϖ��׃��X�g
        l_slRepayCancelListResponse.stockLoanRepayUnits = l_slRepayUnits;

        log.exiting(STR_METHOD_NAME);
        return l_slRepayCancelListResponse;
    }

    /**
     * (get����\�t���O)<BR>
     * ����\���ǂ����̏�Ԃ��擾����B<BR>
     * <BR>
     * �P�j����.�����P��Params.MQ�X�e�[�^�X �� 0:�����M�̏ꍇ�A<BR>
     * �@@�@@�P�|�P�j����.�����P��Params.������� = 14:�����ρi��������j and<BR>
     * �@@�@@�@@�@@�@@�@@����.�����P��Params.�����L����� = 2:�N���[�Y �̏ꍇ�A<BR>
     *    �@@�@@�@@ �@@�@@3�F����ς݂�ԋp����B<BR>
     * �@@�@@�P�|�Q�j�P�|�P�j�ȊO�ŁA����.������ �� ����.�����P��Params.�������̏ꍇ�A<BR>
     *     �@@�@@�@@�@@�@@0�F����s��ԋp����B<BR>
     * �@@�@@�P�|�R�j�P�|�P�j�A�P�|�Q�j�ȊO�̏ꍇ�A<BR>
     *     �@@�@@�@@�@@�@@1�F����\��ԋp����B<BR>
     * <BR>
     * �Q�j����.�����P��Params.MQ�X�e�[�^�X �� 1:���M�ς݂̏ꍇ�A<BR>
     *     �@@�@@�@@�@@�@@2�F���M�ς݂�ԋp����B<BR>
     * <BR>
     * �R�j�P�j�A�Q�j�ȊO�̏ꍇ�A<BR>
     *     �@@�@@�@@�@@�@@4�F���M�G���[��ԋp����B<BR>
     * <BR>
     * @@param l_aioOrderUnitParams - (�����P��Params)<BR>
     * �����P��Params<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * ���ߐU����<BR>
     * @@return String
     * @@roseuid 46E48EEF022F
     */
    private String getCancelEnableFlag(AioOrderUnitParams l_aioOrderUnitParams, Date l_datOrderBizDate)
    {
        final String STR_METHOD_NAME = "getCancelPossibleFlag(AioOrderUnitParams, Date)";
        log.entering(STR_METHOD_NAME);

        String l_strCancelPossibleFlag = null;
        // ����.�����P��Params.������
        Date l_datBizDate = WEB3DateUtility.getDate(
            l_aioOrderUnitParams.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        // ����\���ǂ����̏�Ԃ��擾����B
        // �P�j����.�����P��Params.MQ�X�e�[�^�X �� 0:�����M�̏ꍇ�A
        if (WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_aioOrderUnitParams.getMqStatus()))
        {
            // ����.�����P��Params.������� = 14:�����ρi��������j and
            // ����.�����P��Params.�����L����� = 2:�N���[�Y �̏ꍇ�A
            if (OrderStatusEnum.CANCELLED.equals(l_aioOrderUnitParams.getOrderStatus()) &&
                OrderOpenStatusEnum.CLOSED.equals(l_aioOrderUnitParams.getOrderOpenStatus()))
            {
                // 3�F����ς݂�ԋp����B
                l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.CANCElED;
                log.exiting(STR_METHOD_NAME);
                return l_strCancelPossibleFlag;
            }
            // �P�|�Q�j�P�|�P�j�ȊO�ŁA����.������ �� ����.�����P��Params.������ �̏ꍇ�A
            // 0�F����s��ԋp����B
            else if (WEB3DateUtility.compare(l_datOrderBizDate, l_datBizDate) > 0)
            {
                // 0�F����s��ԋp����B
                l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.CANCEL_NOT;
                log.exiting(STR_METHOD_NAME);
                return l_strCancelPossibleFlag;
            }
            // �P�|�R�j�P�|�P�j�A�P�|�Q�j�ȊO�̏ꍇ�A
            else
            {
                // 1�F����\��ԋp����B
                l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.CANCEL_POSSIBLE;
                log.exiting(STR_METHOD_NAME);
                return l_strCancelPossibleFlag;
            }
        }
        // �Q�j����.�����P��Params.MQ�X�e�[�^�X �� 1:���M�ς݂̏ꍇ�A
        else if (WEB3MqStatusDef.MAIL_SENDED.equals(l_aioOrderUnitParams.getMqStatus()))
        {
            // 2�F���M�ς݂�ԋp����B
            l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.SENDED;
            log.exiting(STR_METHOD_NAME);
            return l_strCancelPossibleFlag;
        }
        // �R�j�P�j�A�Q�j�ȊO�̏ꍇ�A
        else
        {
            // 4�F���M�G���[��ԋp����B
            l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.ERROR;
            log.exiting(STR_METHOD_NAME);
            return l_strCancelPossibleFlag;
        }
    }
}
@
