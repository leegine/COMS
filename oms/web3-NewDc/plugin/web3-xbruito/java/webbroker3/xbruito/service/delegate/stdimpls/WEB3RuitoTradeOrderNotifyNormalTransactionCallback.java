head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������������ʒm���폈���ꌏTransactionCallback(WEB3RuitoTradeOrderNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 ���u��(���{���u) �V�K�쐬
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;

import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyUnitService;
import webbroker3.xbruito.data.HostRuitoOrderNotifyRow;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;

/**
 * �i�ݐϓ���������t���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3RuitoTradeOrderNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyNormalTransactionCallback.class);

    /**
      * �ݓ������ʒm�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostRuitoOrderNotifyParams hostRuitoOrderNotifyParams;

    /**
      * �ݓ����������ʒm�m��C���^�Z�v�^�I�u�W�F�N�g�B<BR>
      */
    private WEB3RuitoTradedOrderNotifyDecisionInterceptor ruitoInterceptor;


    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostRuitoOrderNotifyParams - (�ݓ������ʒm�L���[Params)
     * @@params l_ruitoInterceptor - (�ݓ����������ʒm�m��C���^�Z�v�^)
     */
    public WEB3RuitoTradeOrderNotifyNormalTransactionCallback(
        HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams,
        WEB3RuitoTradedOrderNotifyDecisionInterceptor l_ruitoInterceptor)
    {
        hostRuitoOrderNotifyParams = l_hostRuitoOrderNotifyParams;
        ruitoInterceptor = l_ruitoInterceptor;
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

        WEB3RuitoTradeOrderNotifyUnitService l_ruitoUnitService =
            (WEB3RuitoTradeOrderNotifyUnitService) Services.getService(
                WEB3RuitoTradeOrderNotifyUnitService.class);

        try
        {
            // 1.4.1 �����������s���B
            l_ruitoUnitService.notifyTradeOrderNotify(
                hostRuitoOrderNotifyParams,
                ruitoInterceptor);
            //��������������I�������ꍇ�A
            //�����Ώۂ̗ݓ������ʒm�L���[���R�[�h.�����敪�Ɂh1�F�����ρh���Z�b�g���X�V����B
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        HashMap l_map = new HashMap();
        l_map.put("status", WEB3StatusDef.DEALT);
        //1.4.2 �L���[�e�[�u���ɏ����ς��X�V
        String l_strUpdateWhere = " institution_code = ? "
                + " and branch_code = ? " + " and order_request_number = ? ";
        String[] l_strUpdateWhereValues = {
            hostRuitoOrderNotifyParams.getInstitutionCode(),
            hostRuitoOrderNotifyParams.getBranchCode(),
            hostRuitoOrderNotifyParams.getOrderRequestNumber() };

        QueryProcessor l_qp = Processors.getDefaultProcessor();
        l_qp.doUpdateAllQuery(
            HostRuitoOrderNotifyRow.TYPE,
            l_strUpdateWhere,
            l_strUpdateWhereValues,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}














@
