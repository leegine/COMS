head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceNormalTransactionCallBack.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋������폈���ꌏTransactionCallback(WEB3AioSpsecTransferForceNormalTransactionCallBack.java)
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

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.aio.data.HostSpsecTransNotifyRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyUnitService;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�،��U�֒ʒm���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceNormalTransactionCallBack implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceNormalTransactionCallBack.class);

    /**
      * ������������U�փL���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostSpsecTransNotifyRow hostSpsecTransNotifyRow;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostMrgsecTransAcceptRow - (��p�U�֎�t�L���[Row)
     */
    public WEB3AioSpsecTransferForceNormalTransactionCallBack(
        HostSpsecTransNotifyRow l_hostSpsecTransNotifyRow)
    {
        hostSpsecTransNotifyRow = l_hostSpsecTransNotifyRow;
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

        HostSpsecTransNotifyRow l_hostSpsecTransNotifyRow = hostSpsecTransNotifyRow;

        //get the params
        HostSpsecTransNotifyParams l_hostSpsecTransNotifyParams =
            new HostSpsecTransNotifyParams(l_hostSpsecTransNotifyRow);

        log.debug("������������U�փL���[Row" + l_hostSpsecTransNotifyRow);

        WEB3AioSpsecTransferForceUnitService l_aioSpsecTransferForceUnitService =
            (WEB3AioSpsecTransferForceUnitService)Services.getService(
                WEB3AioSpsecTransferForceUnitService.class);

        try
        {
            //1.2.1 ������������U�փL���[.���� > 0 �̏ꍇ
            if (l_hostSpsecTransNotifyRow.getQuantity() > 0)
            {
                log.debug("������������U�փL���[.���� > 0 �̏ꍇ");
                //1.2.1.1 �V�K�����i�U�֌��{�U�֐�j�̓o�^���s���B
                //[����]
                //������������U�փL���[Params�F ������������U�փL���[Params�I�u�W�F�N�g

                //throw WEB3BaseException
                AioOrderUnit[] l_aioOrderUnits =
                    l_aioSpsecTransferForceUnitService.submitOrder(
                            l_hostSpsecTransNotifyParams);

                if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
                {
                    log.debug("�G���[�F�Y���̐U�֒����P�ʂ��Ȃ�");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                //1.2.1.2 �،��U�֒ʒm�������s���B
                //[����]
                //�����P�ʁF submit����()�̖߂�l
                //�G���[�R�[�h�F "0000"�i����j
                //��t�ʒm�敪�F "1"�i��t�����j

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
            //1.2.2 ������������U�փL���[.���� < 0 �̏ꍇ
            else if (l_hostSpsecTransNotifyRow.getQuantity() < 0)
            {
                log.debug("������������U�փL���[.���� < 0 �̏ꍇ");
                //1.2.2.1 �Y�������̎�����s���B
                //[����]
                //������������U�փL���[Params�F ������������U�փL���[Params�I�u�W�F�N�g
                l_aioSpsecTransferForceUnitService.submitCancel(
                        l_hostSpsecTransNotifyParams);
            }

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.2.3 ������������U�փL���[.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
        //"9"�i�G���[�j�F ��L�����iLoop���̏����j�ŗ�O�����������ꍇ
        //"1"�i�����ρj�F ����ȊO�̏ꍇ

        //a> the map to be updated
        HashMap l_map = new HashMap();

        //b> where
        String l_strWhereUpdate =
            "institution_code = ? and branch_code = ? and " +
            "account_code = ? and order_request_number = ? ";

        //c> data
        Object[] l_bindVarsUpdate =
            {l_hostSpsecTransNotifyRow.getInstitutionCode(),
                l_hostSpsecTransNotifyRow.getBranchCode(),
                l_hostSpsecTransNotifyRow.getAccountCode(),
                l_hostSpsecTransNotifyRow.getOrderRequestNumber()};

        l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        //update
        l_queryProcessor.doUpdateAllQuery(
            HostSpsecTransNotifyRow.TYPE,
            l_strWhereUpdate,
            l_bindVarsUpdate,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}

@
