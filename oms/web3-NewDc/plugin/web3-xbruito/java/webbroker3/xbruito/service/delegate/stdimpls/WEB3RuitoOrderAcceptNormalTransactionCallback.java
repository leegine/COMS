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
filename	WEB3RuitoOrderAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���������t���폈���ꌏTransactionCallback(WEB3RuitoOrderAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 ���u��(���{���u) �V�K�쐬
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
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.data.HostRuitoOrderAcceptParams;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptUnitService;

/**
 * �i�ݐϓ���������t���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3RuitoOrderAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderAcceptNormalTransactionCallback.class);

    /**
      * �ݓ������P�ʃI�u�W�F�N�g�B<BR>
      */
    private RuitoOrderUnit rtOrderUnit;

    /**
      * �ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g�B<BR>
      */
    private WEB3RuitoAcceptedDecisionInterceptor decisionInterceptor;

    /**
      * �ݓ�������t�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostRuitoOrderAcceptParams ruitoOrderAcceptParams;


    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_rtOrderUnit - (�ݓ������P��)
     * @@params l_decisionInterceptor - (�ݓ���t�m��C���^�Z�v�^)
     * @@params l_ruitoOrderAcceptParams - (�ݓ�������t�L���[Params)
     */
    public WEB3RuitoOrderAcceptNormalTransactionCallback(
        RuitoOrderUnit l_rtOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_decisionInterceptor,
        HostRuitoOrderAcceptParams l_ruitoOrderAcceptParams)
    {
        rtOrderUnit = l_rtOrderUnit;
        decisionInterceptor = l_decisionInterceptor;
        ruitoOrderAcceptParams = l_ruitoOrderAcceptParams;
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


        //��t�ʒm�敪�I�擾
        String l_strAcceptStatus = ruitoOrderAcceptParams.getAcceptStatus();

        //1.2�@@�ݓ�������tUnitService���擾����B
        WEB3RuitoOrderAcceptUnitService l_ruitoOrderAcceptUnitService =
            (WEB3RuitoOrderAcceptUnitService) Services.getService(
                WEB3RuitoOrderAcceptUnitService.class);

        try
        {
            //�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�p
            if ((WEB3AcceptStatusDef.OVER).equals(
                l_strAcceptStatus))
            {
                //�ݓ�������tUnitService.notify������t����()
                l_ruitoOrderAcceptUnitService.notifyOrderAcceptComplete(
                    rtOrderUnit,
                    decisionInterceptor);
            }
            else
            {
                //�ݓ�������tUnitService.notify������t���s()
                l_ruitoOrderAcceptUnitService.notifyOrderAcceptFail(
                    rtOrderUnit,
                    decisionInterceptor);
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
        QueryProcessor l_processorObject =
            Processors.getDefaultProcessor();
        // 1.4.4�@@�L���[�e�[�u���̏����敪���X�V
        //�،���ЃR�[�h�I�擾
        String l_strInstatutionCode =
            ruitoOrderAcceptParams.getInstitutionCode();

        //���X�R�[�h�I�擾
        String l_strBranchCode =
            ruitoOrderAcceptParams.getBranchCode();

        //���ʃR�[�h�I�擾
        String l_strOrderRequestNumber =
            ruitoOrderAcceptParams.getOrderRequestNumber();

        String l_strUpdateWhere =
            " institution_code = ?" +
            " and branch_code = ?" +
            " and order_request_number = ?";
        String[] l_strArrayUpdateParams =
            {
                l_strInstatutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber};
        // do update
        l_processorObject.doUpdateAllQuery(
            HostRuitoOrderAcceptParams.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}

@
