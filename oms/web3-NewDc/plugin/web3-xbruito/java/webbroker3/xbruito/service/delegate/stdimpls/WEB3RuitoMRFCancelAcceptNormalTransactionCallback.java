head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFCancelAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���MRF�����t���폈���ꌏTransactionCallback(WEB3RuitoMRFCancelAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/25 ���u��(���{���u) �V�K�쐬
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CancelAcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;

import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.data.HostMrfCancelAcceptParams;
import webbroker3.xbruito.data.HostMrfCancelAcceptRow;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptUnitService;

/**
 * �i�ݐϓ���������t���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3RuitoMRFCancelAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFOrderAcceptNormalTransactionCallback.class);

    /**
      * �ݓ������P�ʃI�u�W�F�N�g�B<BR>
      */
    private RuitoOrderUnit rtOrderUnit;

    /**
      * �ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g�B<BR>
      */
    private WEB3RuitoAcceptedDecisionInterceptor decisionInterceptor;

    /**
      * �ݓ�MRF�����t�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostMrfCancelAcceptParams hostMrfCancelAcceptParams;


    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_rtOrderUnit - (�ݓ������P��)
     * @@params l_decisionInterceptor - (�ݓ���t�m��C���^�Z�v�^)
     * @@params l_hostMrfCancelAcceptParams - (�ݓ�MRF�����t�L���[Params)
     */
    public WEB3RuitoMRFCancelAcceptNormalTransactionCallback(
        RuitoOrderUnit l_rtOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_decisionInterceptor,
        HostMrfCancelAcceptParams l_hostMrfCancelAcceptParams)
    {
        rtOrderUnit = l_rtOrderUnit;
        decisionInterceptor = l_decisionInterceptor;
        hostMrfCancelAcceptParams = l_hostMrfCancelAcceptParams;
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

        String l_strAcceptStatus = hostMrfCancelAcceptParams.getAcceptStatus();

        //�Q�j�@@�ݓ�MRF�����tUnitService���擾����B
        WEB3RuitoMRFCancelAcceptUnitService l_unitService =
            (WEB3RuitoMRFCancelAcceptUnitService) Services.getService(
                WEB3RuitoMRFCancelAcceptUnitService.class);

        try
        {
            //�U�j�@@������t�������s���B
            l_strAcceptStatus = hostMrfCancelAcceptParams.getAcceptStatus();
            log.debug("��t�ʒm�敪:l_strAcceptStatus = " + l_strAcceptStatus);
            if (WEB3CancelAcceptStatusDef.COMPLETE.equals(
                l_strAcceptStatus))
            {
                log.debug("notify�����t����() begin.");
                //�ݓ�MRF�����tUnitService.notify�����t����()
                l_unitService.notifyCancelAcceptComplete(
                    rtOrderUnit,
                    decisionInterceptor);
                log.debug("notify�����t����() end.");
            }
            else
            {
                log.debug("notify�����t���s() begin.");
                //�ݓ�MRF�����tUnitService.notify�����t���s()
                l_unitService.notifyCancelAcceptFail(
                    rtOrderUnit,
                    decisionInterceptor);
                log.debug("notify�����t���s() end.");
            }
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

        String l_strUpdateWhere = "institution_code = ? "
                           + "and branch_code = ? "
                           + "and order_request_number = ?";
        String[] l_strArrayUpdateParams = {
            hostMrfCancelAcceptParams.getInstitutionCode(),
            hostMrfCancelAcceptParams.getBranchCode(),
            hostMrfCancelAcceptParams.getOrderRequestNumber()};
        // �V�j�@@�����Ώۂ�MRF�����t�L���[���R�[�h.�����敪��ݒ�h
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateAllQuery(
            HostMrfCancelAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);
        log.exiting(STR_METHOD_NAME);

        return null;
    }
}






@
