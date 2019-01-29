head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������t���폈���ꌏTransactionCallback(WEB3MutualCancelAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 ���u��(���{���u) �V�K�쐬
*/


package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CancelAcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.data.HostXbmfCancelAcceptParams;
import webbroker3.mf.data.HostXbmfCancelAcceptRow;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�����M�������t���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptNormalTransactionCallback.class);

    /**
      * ���M�����P�ʃI�u�W�F�N�g�B<BR>
      */
    private MutualFundOrderUnit mfOrderUnit;

    /**
      * ���M��t�m��C���^�Z�v�^�I�u�W�F�N�g�B<BR>
      */
    private WEB3MutualFundAcceptConfirmInterceptor confirmInterceptor;

    /**
      * ���M�����t�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostXbmfCancelAcceptParams cancelAcceptParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_mfOrderUnit - (���M�����P��)
     * @@params l_confirmInterceptor - (���M��t�m��C���^�Z�v�^)
     * @@params l_cancelAcceptParams - (���M�����t�L���[Params)
     */
    public WEB3MutualCancelAcceptNormalTransactionCallback(
        MutualFundOrderUnit l_mfOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor,
        HostXbmfCancelAcceptParams l_cancelAcceptParams)
    {
        mfOrderUnit = l_mfOrderUnit;
        confirmInterceptor = l_confirmInterceptor;
        cancelAcceptParams = l_cancelAcceptParams;
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

        // �Q�j�@@���M�����tUnitService���擾����
        WEB3MutualCancelAcceptUnitService l_cancelAcceptUnitService =
            (WEB3MutualCancelAcceptUnitService) Services.getService(
                WEB3MutualCancelAcceptUnitService.class);

        MutualFundOrderUnit l_mfOrderUnit = mfOrderUnit;
        WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor = confirmInterceptor;
        HostXbmfCancelAcceptParams l_cancelAcceptParams = cancelAcceptParams;

        String l_strAcceptStatus = l_cancelAcceptParams.getAcceptStatus();

        try
        {
            //�|���M�����t�L���[Params.get����ʒm�敪()�̖߂�l���h1�F��������h�̏ꍇ
            if (WEB3CancelAcceptStatusDef.COMPLETE.equals(l_strAcceptStatus))
            {
                //���M�����tUnitService.notify�����t����()���R�[������
                l_cancelAcceptUnitService.notifyCancelAcceptComplete(
                    l_mfOrderUnit, l_confirmInterceptor);
            }
            else
            {
                //���M�����t�L���[Params.get����ʒm�敪()�̖߂�l�� �h2�F������s�h�̏ꍇ
                if (WEB3CancelAcceptStatusDef.FAIL.equals(l_strAcceptStatus))
                {
                    //���M�����tUnitService.notify�����t���s()���R�[������
                    l_cancelAcceptUnitService.notifyCancelAcceptFail(
                        l_mfOrderUnit, l_confirmInterceptor);
                }
                else
                {
                    log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                }
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
        // �����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�p
        String l_strUpdateWhere = " institution_code = ? "
                           + " and branch_code = ? "
                           + " and order_request_number = ?";
        String[] l_strArrayUpdateParams = {
            l_cancelAcceptParams.getInstitutionCode(),
            l_cancelAcceptParams.getBranchCode(),
            l_cancelAcceptParams.getOrderRequestNumber()
        };

        l_map.put("status", WEB3StatusDef.DEALT);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        // �W�j�@@�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
        l_queryProcessor.doUpdateAllQuery(
            HostXbmfCancelAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
