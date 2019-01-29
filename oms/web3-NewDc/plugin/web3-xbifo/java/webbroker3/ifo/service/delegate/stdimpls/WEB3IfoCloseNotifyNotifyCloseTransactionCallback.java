head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCloseNotifyNotifyCloseTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����ʒm���������ꌏTransactionCallback(WEB3IfoCloseNotifyNotifyCloseTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/11 ���u��(���{���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate.stdimpls;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�敨OP�����ʒm���������ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyNotifyCloseTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoCloseNotifyNotifyCloseTransactionCallback.class);

    /**
      * �����P�ʃI�u�W�F�N�g�B<BR>
      */
    private OrderUnit orderUnit;

    /**
      * ��萔�ʃI�u�W�F�N�g�B<BR>
      */
    private double dblExecutionQuantity;

    /**
      * �������R�R�[�h�I�u�W�F�N�g�B<BR>
      */
    private String strCloseReasonCode;

    /**
      * �����ʒm�敪�I�u�W�F�N�g�B<BR>
      */
    private String strCloseNotifyType;

    /**
      * �����ʒm�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostFotypeCloseOrderNotifyParams hostFotypeCloseOrderNotifyParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_orderUnit - (�����P��)
     * @@params l_dblExecutionQuantity - (��萔��)
     * @@params l_strCloseReasonCode - (�������R�R�[�h)
     * @@params l_strCloseNotifyType - (�����ʒm�敪)
     * @@params l_hostFotypeCloseOrderNotifyParams - (�����ʒm�L���[Params)
     */
    public WEB3IfoCloseNotifyNotifyCloseTransactionCallback(
        OrderUnit l_orderUnit,
        double l_dblExecutionQuantity,
        String l_strCloseReasonCode,
        String l_strCloseNotifyType,
        HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams)
    {
        orderUnit = l_orderUnit;
        dblExecutionQuantity = l_dblExecutionQuantity;
        strCloseReasonCode = l_strCloseReasonCode;
        strCloseNotifyType = l_strCloseNotifyType;
        hostFotypeCloseOrderNotifyParams = l_hostFotypeCloseOrderNotifyParams;
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

        WEB3IfoCloseNotifyUnitService l_closeNotifyUnitServiceImpl =
            (WEB3IfoCloseNotifyUnitService)Services.getService(WEB3IfoCloseNotifyUnitService.class);
		OrderUnit l_reOrderUnit = null;

        String l_strStatus = null;

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            // lock����
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            l_accountManager.lockAccount(
                hostFotypeCloseOrderNotifyParams.getInstitutionCode(),
                hostFotypeCloseOrderNotifyParams.getBranchCode(),
                hostFotypeCloseOrderNotifyParams.getAccountCode());

            // ������Ԃ��m�F���邽�߂ɒ����P�ʂ��Ď擾����
            orderUnit = l_orderManager.getOrderUnit(
                hostFotypeCloseOrderNotifyParams.getInstitutionCode(),
                hostFotypeCloseOrderNotifyParams.getBranchCode(),
                ProductTypeEnum.IFO,
                hostFotypeCloseOrderNotifyParams.getOrderRequestNumber());

            this.validateOrderStatus((IfoOrderUnit)orderUnit);

            l_strStatus = l_closeNotifyUnitServiceImpl.notifyClose(
                orderUnit,
                dblExecutionQuantity,
                strCloseReasonCode,
                strCloseNotifyType
                );

			l_reOrderUnit = l_orderManager.getOrderUnit(orderUnit.getOrderUnitId());                    
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        } 		
        catch (NotFoundException l_exp)
		{
			ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
			l_errorInfo.setErrorClass(l_exp.getClass().getName());
			throw new DataCallbackException(
				l_exp.getMessage(),
				l_errorInfo);
		}

        //8:�L���[�e�[�u���X�V
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        log.debug("ExecutedQuantity:" + l_reOrderUnit.getExecutedQuantity());
        log.debug("HostFortypeQuantity:" + dblExecutionQuantity);

        log.debug("Enter the path that �L���[�e�[�u���X�V ");
        hostFotypeCloseOrderNotifyParams.setStatus(l_strStatus);
        hostFotypeCloseOrderNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        l_queryProcessor.doUpdateQuery(hostFotypeCloseOrderNotifyParams);
        log.debug("Exit the path that �L���[�e�[�u���X�V");

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (validate�������)<BR>
     * �Ώے������A�����^��������ɂ��X�V���s���ėǂ���Ԃł��邩�`�F�b�N����B<BR>
     * <BR>
     * �i�`�F�b�N���e�j<BR>
     * �P�j�@@�����œn���ꂽ�����P��.�s�ꂩ��m�F�ς̐��� == NaN�̏ꍇ�A<BR>
     * �@@�@@�u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B<BR>
     * <BR>
     * �Q�j�@@�����œn���ꂽ�����P��.������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@�u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B<BR>
     * �@@�@@�@@�@@ACCEPTED�F��t�ρi�V�K�����j<BR>
     * �@@�@@�@@�@@ORDERING�F�������i�V�K�����j<BR>
     * �@@�@@�@@�@@MODIFY_ACCEPTED�F��t�ρi�ύX�����j<BR>
     * �@@�@@�@@�@@MODIFYING�F�������i�ύX�����j<BR>
     * �@@�@@�@@�@@CANCEL_ACCEPTED�F��t�ρi��������j<BR>
     * �@@�@@�@@�@@CANCELLING�F�������i��������j<BR>
     * <BR>
     * �ȊO�A���̂܂�return����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderStatus(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateOrderStatus(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_orderUnitRow.getConfirmedQuantity() == 0.0D)
        {
            log.debug("�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.ORDERING.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFYING.equals(l_orderStatus) ||
            OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            log.debug("�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
