head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3AsynRlsCondOrderNotifyTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���[���G���W���ʒm�ꌏTransactionCallback(WEB3AsynRlsCondOrderNotifyTransactionCallback.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 ��(FLJ) �V�K�쐬
 */

package webbroker3.omsadaptor.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.rlsgateway.define.*;
import webbroker3.util.*;

/**
 * �i���[���G���W���ʒm�ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ��
 * @@version 1.0
 */
public class WEB3AsynRlsCondOrderNotifyTransactionCallback
    implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynRlsCondOrderNotifyTransactionCallback.class);

    /**
     * ���[���G���W���ʒmParams�I�u�W�F�N�g�B
     */
    private RlsConOrderHitNotifyParams notifyParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_orderUnit - (�����P��)
     * @@params l_orderExecNotifyParams - (�����o���ʒm�L���[Params)
     */
    public WEB3AsynRlsCondOrderNotifyTransactionCallback(
        RlsConOrderHitNotifyParams l_notifyParams)
    {
        notifyParams = l_notifyParams;
    }

    /**
     * �g�����U�N�V�������������{����B<BR>
     * ���[���G���W���ʒm�ꌏTransactionCallback.process()�����Q�ƁB<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process() throws DataQueryException, DataNetworkException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        WEB3RlsCondOrderSubmitService service = (
            WEB3RlsCondOrderSubmitService) Services.getService(
            WEB3RlsCondOrderSubmitService.class);

        String l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.SUCCEED;

        try
        {

            //����
            l_strSubmitResult = service.submitRlsCondOrder(notifyParams);
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
        //�L���[�̍X�V�̓��[���G���W���ʒm�ꌏTransactionCallback�����ōs��
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
        java.sql.Timestamp l_lastTime=GtlUtils.getSystemTimestamp();
        if (WEB3RlsOrderSubmitErrorCodeDef.DELAY.equals(l_strSubmitResult))
        {
            notifyParams.setStatus(WEB3RlsNotifyStatusDef.PROGRAM_ERROR);
        }
        else
        {
            notifyParams.setStatus(WEB3RlsNotifyStatusDef.DEAL);
            notifyParams.setOrderSubmitTimestamp(l_lastTime);
        }

        notifyParams.setOrderSubmitErrorCode(l_strSubmitResult);
        notifyParams.setLastUpdatedTimestamp(l_lastTime);
        l_QueryProcessor.doUpdateQuery(notifyParams);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
