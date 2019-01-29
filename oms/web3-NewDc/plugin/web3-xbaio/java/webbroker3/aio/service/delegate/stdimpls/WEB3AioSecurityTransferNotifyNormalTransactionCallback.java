head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֒ʒm���폈���ꌏTransactionCallback(WEB3AioSecurityTransferNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/28 ���u��(���{���u) �V�K�쐬
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

import webbroker3.aio.data.HostMrgsecTransAcceptRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;




/**
 * �i�،��U�֒ʒm���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferNotifyNormalTransactionCallback.class);

    /**
      * ��p�U�֎�t�L���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostMrgsecTransAcceptRow hostMrgsecTransAcceptRow;

    /**
      * �����P�ʃI�u�W�F�N�g�B<BR>
      */
    private AioOrderUnit[] aioOrderUnits;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostMrgsecTransAcceptRow - (��p�U�֎�t�L���[Row)
     * @@params l_aioOrderUnits - (�����P��)
     */
    public WEB3AioSecurityTransferNotifyNormalTransactionCallback(
        HostMrgsecTransAcceptRow l_hostMrgsecTransAcceptRow,
        AioOrderUnit[] l_aioOrderUnits)
    {
        hostMrgsecTransAcceptRow = l_hostMrgsecTransAcceptRow;
        aioOrderUnits = l_aioOrderUnits;
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

        HostMrgsecTransAcceptRow l_hostMrgsecTransAcceptRow = hostMrgsecTransAcceptRow;
        AioOrderUnit[] l_aioOrderUnits = aioOrderUnits;

        try
        {
            WEB3AioSecurityTransferNotifyUnitService l_unitService =
                (WEB3AioSecurityTransferNotifyUnitService)
                    Services.getService(WEB3AioSecurityTransferNotifyUnitService.class);

            //1.2.2 execute(AioOrderUnit[], String, String)
            //[����]
            // �����P�ʁF get�U�֒����P��()�̖߂�l
            // �G���[�R�[�h�F ��p�U�֎�t�L���[.�G���[���b�Z�[�W
            // ��t�ʒm�敪�F ��p�U�֎�t�L���[.��t�ʒm�敪
            l_unitService.execute(
                l_aioOrderUnits,
                l_hostMrgsecTransAcceptRow.getErrorMessage(),
                l_hostMrgsecTransAcceptRow.getAcceptDiv());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        HashMap l_map = new HashMap();
        String l_strUpdateWhere =
            " institution_code = ? and branch_code = ? and account_code = ? and order_request_number = ? ";
        String[] l_strUpdateWhereValues = {
                l_hostMrgsecTransAcceptRow.getInstitutionCode(),
                l_hostMrgsecTransAcceptRow.getBranchCode(),
                l_hostMrgsecTransAcceptRow.getAccountCode(),
                l_hostMrgsecTransAcceptRow.getOrderRequestNumber() };

        l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        //  1.2.3 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
        l_queryProcessor.doUpdateAllQuery(
            HostMrgsecTransAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strUpdateWhereValues,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}




@
