head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐����ʒm���폈���ꌏTransactionCallback(WEB3AccTransChangeRequestNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/05 ���u��(���{���u) �V�K�쐬
*/


package webbroker3.aio.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.aio.data.HostTransferReceiptRow;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�U�֐����ʒm���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeRequestNotifyNormalTransactionCallback.class);

    /**
      * �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostTransferReceiptParams hostTransferReceiptParams;

    /**
      * ������ʃI�u�W�F�N�g�B<BR>
      */
    private OrderTypeEnum orderType;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostTransferReceiptParams - (�U�֓��͒ʒm�L���[Params)
     * @@params l_orderType - (�������)
     */
    public WEB3AccTransChangeRequestNotifyNormalTransactionCallback(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType)
    {
        hostTransferReceiptParams = l_hostTransferReceiptParams;
        orderType = l_orderType;
    }

    /**
     * �g�����U�N�V�������������{����B<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        HostTransferReceiptParams l_hostTransferReceiptParams = hostTransferReceiptParams;

        OrderTypeEnum l_orderType = orderType;
        try
        {
            //1.2.2)
            if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
                OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
                OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType) ||
                OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
            {
                AssetTransferTypeEnum l_assetTransferType = null;
                //�@@�E������ʂ��ȉ��̂����ꂩ�̏ꍇ�A2�i�o���j
                //1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�))
                //1017(���̑��U�֒���(�a���������X))
                if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
                    OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType))
                {
                    l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;
                }
                //�E������ʂ��ȉ��̂����ꂩ�̏ꍇ�A1�i�����j
                //1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����))
                //1018(���̑��U�֒���(X����a�����))
                else if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
                        OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
                {
                    l_assetTransferType = AssetTransferTypeEnum.CASH_IN;
                }
                // 1.2.2.1) �V�K�ב֕ۏ؋��U�֒����̓o�^���s���B
                // [����]
                // �U�֓��͒ʒm�L���[Params�F �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g
                // ������ʁF get�������()�̖߂�l
                // �U�փ^�C�v�F
                //  �E������ʂ��ȉ��̂����ꂩ�̏ꍇ�A2�i�o���j
                //      1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�))
                //      1017(���̑��U�֒���(�a���������X))
                //  �E������ʂ��ȉ��̂����ꂩ�̏ꍇ�A1�i�����j
                //      1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����))
                //      1018(���̑��U�֒���(X����a�����))
                this.createOrder(
                    l_hostTransferReceiptParams,
                    l_orderType,
                    l_assetTransferType);
            }
            //1.2.3
            else
            {
                //1.2.3.1 �V�K���������̓o�^���s���B
                //�U�֓��͒ʒm�L���[Params�F �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g
                //������ʁF get�������()�̖߂�l
                //�U�փ^�C�v�F 1�i�����j
                this.createOrder(
                    l_hostTransferReceiptParams,
                    l_orderType,
                    AssetTransferTypeEnum.CASH_IN);

                //1.2.3.2 �V�K�o�������̓o�^���s���B
                //[����]
                //�U�֓��͒ʒm�L���[Params�F �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g
                //������ʁF get�������()�̖߂�l
                //�U�փ^�C�v�F 2�i�o���j
                this.createOrder(
                    l_hostTransferReceiptParams,
                    l_orderType,
                    AssetTransferTypeEnum.CASH_OUT);
            }
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }


        // �����Ώۂ̐U�֓��͒ʒm�e�[�u��.�����敪��ݒ�p
        String l_strUpdateWhere = " rowid = ? ";

        String[] l_strArrayUpdateParams = {
            l_hostTransferReceiptParams.getRowid()
        };

        Map l_map = new Hashtable();

        // �����Ώۂ̐U�֓��͒ʒm�e�[�u��.�����敪��ݒ�p
        l_map.put("status", WEB3StatusDef.DEALT);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        // 1.2.4�@@�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
        l_queryProcessor.doUpdateAllQuery(
            HostTransferReceiptRow.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }

    /**
     * (create����)<BR>
     * �U�֒����𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�U�֐����ʒm�jcreate�����v �Q��<BR>
     * @@param l_hostTransferReceiptParams - (�U�֓��͒ʒm�L���[Params�I�u�W�F�N�g)<BR>
     * @@param l_orderType - (�������)<BR>
     * @@param l_changeType - (�U�փ^�C�v)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D3C370036
     */
    protected void createOrder(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createOrder(" +
            "HostTransferReceiptParams l_hostTransferReceiptParams," +
            "OrderTypeEnum l_orderType," +
            "AssetTransferTypeEnum l_changeType)";

        log.entering(STR_METHOD_NAME);

        // 1) �V�K�����̓o�^���s���B
        // [����]
        // �U�֓��͒ʒm�L���[Params�F �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g
        // ������ʁF ����.�������
        // �U�փ^�C�v�F ����.�U�փ^�C�v
        WEB3AccTransChangeRequestNotifyUnitService l_notifyUnitService =
            (WEB3AccTransChangeRequestNotifyUnitService)Services.getService(
                WEB3AccTransChangeRequestNotifyUnitService.class);
        long l_lngOrderID = l_notifyUnitService.submitOrder(
                l_hostTransferReceiptParams,
                l_orderType,
                l_changeType);
        log.debug("l_lngOrderID = " + l_lngOrderID);
        // 2) �����P�ʂ��擾����B
        // (*�z���1�Ԗڂ̗v�f���擾����j
        // [����]
        // ����ID�F submit����()�̖߂�l
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_orderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderID);
        AioOrderUnit l_orderUnit = (AioOrderUnit) l_orderUnits[0];

        // 3�j�U�֎�tDB�X�V�������s���B
        // [����]
        // �����P�ʁF �����P�ʃI�u�W�F�N�g
        // �G���[�R�[�h�F "0000"�i����j
        // ��t�ʒm�敪�F "1"�i��t�����j
        WEB3AccTransChangeAcceptUnitService l_acceptUnitService =
            (WEB3AccTransChangeAcceptUnitService) Services.getService(
                WEB3AccTransChangeAcceptUnitService.class);

        l_acceptUnitService.execute(
            l_orderUnit,
            WEB3ErrorReasonCodeDef.NORMAL,
            WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);

        // 4) �U�֊��������ɔ��������f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B
        // [����]
        // �����P�ʁF �����P�ʃI�u�W�F�N�g
        WEB3AccTransChangeCompleteUnitService l_completeUnitService =
            (WEB3AccTransChangeCompleteUnitService) Services.getService(
            WEB3AccTransChangeCompleteUnitService.class);
        l_completeUnitService.completeChange(l_orderUnit);

        // 5) �⏕�����I�u�W�F�N�g���擾����B
        // �m�����n
        // ����ID�F �����P��.����ID
        // �⏕����ID�F �����P��.�⏕����ID
        WEB3GentradeAccountManager l_accManage =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accManage.getSubAccount(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�����I�u�W�F�N�g���擾����:",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        if(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
            l_subAccount.getSubAccountType()))
        {
            //=========remain zhou-yong NO.1 begin ===========

            // 6) �]�͍Čv�Z(�⏕���� : �⏕����)
            //�A�C�e���̒�`
            //�]�͂̍X�V���s���B
            //[����]
            //�⏕�����F get�⏕����()�̖߂�l
			WEB3TPTradingPowerReCalcService l_service =
                (WEB3TPTradingPowerReCalcService) Services.getService(
			WEB3TPTradingPowerReCalcService.class);

            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;

            l_service.reCalcTradingPower(l_gentradeSubAccount);

            //=========remain zhou-yong NO.1 end ===========

        }
        log.exiting(STR_METHOD_NAME);
    }
}

@
