head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferForceNormalTransactionCallBack.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֋������폈���ꌏTransactionCallback(WEB3AioSecurityTransferForceNormalTransactionCallBack.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/29 ���u��(���{���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.aio.data.HostMrgsecTransNotifyRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceUnitService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;



/**
 * �i�،��U�֋������폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceNormalTransactionCallBack implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceNormalTransactionCallBack.class);

    /**
      * ��p�U�֋����L���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostMrgsecTransNotifyRow hostMrgsecTransNotifyRow;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostMrgsecTransNotifyRow - (��p�U�֋����L���[Row)
     */
    public WEB3AioSecurityTransferForceNormalTransactionCallBack(
    HostMrgsecTransNotifyRow l_hostMrgsecTransNotifyRow)
    {
        hostMrgsecTransNotifyRow = l_hostMrgsecTransNotifyRow;
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

        //�،��U�֋���UnitService
        WEB3AioSecurityTransferForceUnitService l_aioSecurityTransferForceUnitService =
            (WEB3AioSecurityTransferForceUnitService)Services.getService(
                WEB3AioSecurityTransferForceUnitService.class);

        HostMrgsecTransNotifyRow l_hostMrgsecTransNotifyRow = hostMrgsecTransNotifyRow;

        //get the params
        HostMrgsecTransNotifyParams l_hostMrgsecTransNotifyParams =
            new HostMrgsecTransNotifyParams(l_hostMrgsecTransNotifyRow);

        try
        {
            //throw WEB3BaseException
            AioOrderUnit[] l_aioOrderUnits =
                l_aioSecurityTransferForceUnitService.submitOrder(l_hostMrgsecTransNotifyParams);

            if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
            {
                log.debug("�G���[�F�Y���̐U�֒����P�ʂ��Ȃ�");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.2.2 �،��U�֒ʒm�������s���B
            //[����]
            //�����P�ʁF submit����()�̖߂�l --l_aioOrderUnit
            //�G���[�R�[�h�F "0000"�i����j--WEB3ErrorReasonCodeDef.NORMAL
            //��t�ʒm�敪�F "1"�i��t�����j--WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE

            //�،��U�֒ʒmUnitService
            WEB3AioSecurityTransferNotifyUnitService l_aioSecurityTransferNotifyUnitService =
                (WEB3AioSecurityTransferNotifyUnitService)Services.getService(
                    WEB3AioSecurityTransferNotifyUnitService.class);

            //throw WEB3BaseException
            l_aioSecurityTransferNotifyUnitService.execute(
                l_aioOrderUnits,
                WEB3ErrorReasonCodeDef.NORMAL,
                WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.2.3 ��p�U�֋����L���[.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
        //"9"�i�G���[�j�F ��L�����iLoop���̏����j�ŗ�O�����������ꍇ
        //"1"�i�����ρj�F ����ȊO�̏ꍇ
        HashMap l_map = new HashMap();

        //b> where
        String l_strWhereUpdate =
            " institution_code = ? and branch_code = ? and account_code = ? and order_request_number = ? ";

        //c> data
        Object[] l_bindVarsUpdate =
            {l_hostMrgsecTransNotifyRow.getInstitutionCode(),
                l_hostMrgsecTransNotifyRow.getBranchCode(),
                l_hostMrgsecTransNotifyRow.getAccountCode(),
                l_hostMrgsecTransNotifyRow.getOrderRequestNumber()};

        l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        //update
        l_queryProcessor.doUpdateAllQuery(
            HostMrgsecTransNotifyRow.TYPE,
            l_strWhereUpdate,
            l_bindVarsUpdate,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}@
