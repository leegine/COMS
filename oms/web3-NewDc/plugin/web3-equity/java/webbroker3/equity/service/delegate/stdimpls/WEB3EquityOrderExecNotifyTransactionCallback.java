head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o���ʒm�ꌏTransactionCallback(WEB3EquityOrderExecNotifyTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/03 �򑺐m�m(SRA) �V�K�쐬
                   2005/03/09 ���iFLJ�j�L���[�e�[�u���ɂ�鉺�菈���̃g�����U�N�V��������ύX
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MarginOrderExecNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����o���ʒm�ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author �򑺐m�m
 * @@version 1.0
 */
public class WEB3EquityOrderExecNotifyTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyTransactionCallback.class);

    /**
      * �����P�ʃI�u�W�F�N�g�B<BR>
      */
    private EqTypeOrderUnit orderUnit;

    /**
      * �����o���ʒm�L���[Params�I�u�W�F�N�g�B
      */
    private HostEquityOrderExecNotifyParams orderExecNotifyParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_orderUnit - (�����P��)
     * @@params l_orderExecNotifyParams - (�����o���ʒm�L���[Params)
     */
    public WEB3EquityOrderExecNotifyTransactionCallback(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_orderExecNotifyParams)
    {
        orderUnit = l_orderUnit;
        orderExecNotifyParams = l_orderExecNotifyParams;
    }

    /**
     * �g�����U�N�V�������������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����o���ʒm���C���T�[�r�X�jprocess�v��<BR>
     * �����o���ʒm�ꌏTransactionCallback.process()�����Q�ƁB<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        try
        {
	        // 1.3.2. �擾���������P�ʁ����������̏ꍇ
	        if (OrderCategEnum.ASSET.equals(orderUnit.getOrderCateg()))
	        {
	            // �����o���ʒm�ꌏ�T�[�r�X�̎擾
	            WEB3EquityOrderExecNotifyUnitService l_unitService =
	                (WEB3EquityOrderExecNotifyUnitService)Services.getService(
	                    WEB3EquityOrderExecNotifyUnitService.class);
	            // 1.3.2.1. �����o���ʒm�L���[Params.�o���ʒm�敪���i"�S�����"or"�ꕔ���"�j�̏ꍇ
	            if (WEB3DealedTypeDef.EXECUTED.equals(orderExecNotifyParams.getDealedType()) ||
                    WEB3DealedTypeDef.FULLY_EXECUTED.equals(orderExecNotifyParams.getDealedType()) ||
	                WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(orderExecNotifyParams.getDealedType()))
	            {
	                l_unitService.notifyExecute(orderUnit, orderExecNotifyParams);
	            }
	            // 1.3.2.2. �����o���ʒm�L���[Params.�o���ʒm�敪���i"�����"�j�̏ꍇ
	            else if (WEB3DealedTypeDef.CANCEL.equals(orderExecNotifyParams.getDealedType()))
	            {
	                l_unitService.notifyExecuteCancel(orderUnit, orderExecNotifyParams);
	            }
	            // ��L�ȊO�̏ꍇ
	            else
	            {
	                String l_strMessage =
	                    "�����o���ʒm�L���[.�o���ʒm�敪���i\"�S�����\"or\"�ꕔ���\"or\"�����\"�j";
	                log.error(l_strMessage);
	                throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80025,
	                    this.getClass().getName() + "." + STR_METHOD_NAME,
	                    l_strMessage);
	            }
	        }
	        // 1.3.3. �擾���������P�ʁ��M�p����̏ꍇ
	        else if (OrderCategEnum.OPEN_MARGIN.equals(orderUnit.getOrderCateg()) ||
	                  OrderCategEnum.CLOSE_MARGIN.equals(orderUnit.getOrderCateg()) ||
	                  OrderCategEnum.SWAP_MARGIN.equals(orderUnit.getOrderCateg()))
	        {
	            // �M�p����o���ʒm�ꌏ�T�[�r�X�̎擾
	            WEB3MarginOrderExecNotifyUnitService l_unitService =
	                (WEB3MarginOrderExecNotifyUnitService)Services.getService(
	                    WEB3MarginOrderExecNotifyUnitService.class);
	            // 1.3.3.1. �����o���ʒm�L���[Params.�o���ʒm�敪���i"�S�����"or"�ꕔ���"�j�̏ꍇ
	            if (WEB3DealedTypeDef.EXECUTED.equals(orderExecNotifyParams.getDealedType()) ||
                    WEB3DealedTypeDef.FULLY_EXECUTED.equals(orderExecNotifyParams.getDealedType()) ||
	                WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(orderExecNotifyParams.getDealedType()))
	            {
	                l_unitService.notifyExecute(orderUnit, orderExecNotifyParams);
	            }
	            // 1.3.3.2. �����o���ʒm�L���[Params.�o���ʒm�敪���i"�����"�j�̏ꍇ
	            else if (WEB3DealedTypeDef.CANCEL.equals(orderExecNotifyParams.getDealedType()))
	            {
	                l_unitService.notifyExecuteCancel(orderUnit, orderExecNotifyParams);
	            }
	            // ��L�ȊO�̏ꍇ
	            else
	            {
	                String l_strMessage =
	                    "�����o���ʒm�L���[Params.�o���ʒm�敪���i\"�S�����\"or\"�ꕔ���\"or\"�����\"�j";
	                log.error(l_strMessage);
	                throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80025,
	                    this.getClass().getName() + "." + STR_METHOD_NAME,
	                    l_strMessage);
	            }
	        }
	        // ��L�ȊO�̏ꍇ
	        else
	        {
	            String l_strMessage =
	                "���������P��.�����J�e�S�����i\"��������\"or\"�V�K������\"or\"�ԍϒ���\"or\"�����E���n����\"�j";
	            log.error(l_strMessage);
	            throw new WEB3SystemLayerException(
	                WEB3ErrorCatalog.SYSTEM_ERROR_80025,
	                this.getClass().getName() + "." + STR_METHOD_NAME,
	                l_strMessage);
	        }
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
        catch (WEB3BaseRuntimeException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
        //�L���[�̍X�V�͊����o���ʒm�ꌏTransactionCallback�����ōs��
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
        orderExecNotifyParams.setStatus(WEB3StatusDef.DEALT);
        orderExecNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        l_QueryProcessor.doUpdateQuery(orderExecNotifyParams);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
