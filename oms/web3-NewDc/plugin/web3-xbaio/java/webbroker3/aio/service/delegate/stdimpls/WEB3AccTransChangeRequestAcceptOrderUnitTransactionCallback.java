head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐�����t�����P�ʏ���TransactionCallback(WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/05 ���u��(���{���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�U�֐�����t���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback.class);

    /**
      * �U�֐�����t�L���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostTransferAcceptRow hostTransferAcceptRow;

    /**
      * �����P�ʃI�u�W�F�N�g�B<BR>
      */
    private AioOrderUnit aioOrderUnit;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostTransferReceiptParams - (�U�֐�����t�L���[Row)
     * @@params l_aioOrderUnit - (�����P��)
     */
    public WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback(
        HostTransferAcceptRow l_hostTransferAcceptRow,
        AioOrderUnit l_aioOrderUnit)
    {
        hostTransferAcceptRow = l_hostTransferAcceptRow;
        aioOrderUnit = l_aioOrderUnit;
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

        WEB3AccTransChangeAcceptUnitService l_AcceptService =
            (WEB3AccTransChangeAcceptUnitService) Services.getService(
                WEB3AccTransChangeAcceptUnitService.class);

        WEB3AccTransChangeCompleteUnitService l_CompleteService =
            (WEB3AccTransChangeCompleteUnitService) Services.getService(
                WEB3AccTransChangeCompleteUnitService.class);
        try
        {
            l_AcceptService.execute(
                aioOrderUnit,
                hostTransferAcceptRow.getErrorMessage(),
                hostTransferAcceptRow.getAcceptDiv());

            if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(
                hostTransferAcceptRow.getAcceptDiv()))
            {
                // 2 - 2 - 2)�U�֊��������ɔ��������f�[�^�̍X�V��
                // �g�����U�N�V�����f�[�^�̐������s���B
                // [����]
                // �����P�ʁF �����P�ʃI�u�W�F�N�g
                l_CompleteService.completeChange(
                aioOrderUnit);
            }
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
