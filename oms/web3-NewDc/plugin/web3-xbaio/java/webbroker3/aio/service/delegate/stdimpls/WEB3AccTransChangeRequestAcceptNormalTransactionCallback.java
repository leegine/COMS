head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐�����t���폈���ꌏTransactionCallback(WEB3AccTransChangeRequestAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/05 ���u��(���{���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�U�֐�����t���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeRequestAcceptNormalTransactionCallback.class);

    /**
      * �U�֐�����t�L���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostTransferAcceptRow hostTransferAcceptRow;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostTransferReceiptParams - (�U�֐�����t�L���[Row)
     */
    public WEB3AccTransChangeRequestAcceptNormalTransactionCallback(
        HostTransferAcceptRow l_hostTransferAcceptRow)
    {
        hostTransferAcceptRow = l_hostTransferAcceptRow;
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

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_orderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();

        AioOrderUnit[] l_aioOrderUnit = null;
        HostTransferAcceptRow l_hostTransferAcceptRow = hostTransferAcceptRow;
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        try
        {
            l_aioOrderUnit = l_orderManager.getTransferOrderUnit(
                l_hostTransferAcceptRow.getInstitutionCode(),
                l_hostTransferAcceptRow.getBranchCode(),
                l_hostTransferAcceptRow.getAccountCode(),
                l_hostTransferAcceptRow.getOrderRequestNumber());

            // 2 - 2) �擾���������P�ʖ���Loop����
            if(l_aioOrderUnit.length != 0)
            {
                for(int j = 0; j<l_aioOrderUnit.length; j++)
                {
                    WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback l_transactionCallback =
                        new WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback(
                        l_hostTransferAcceptRow,
                        l_aioOrderUnit[j]);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
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
        catch (NotFoundException l_ex)
        {
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            l_errorInfo.setErrorClass(l_ex.getClass().getName());
            throw new DataCallbackException(
                l_ex.getMessage(),
                l_errorInfo);
        }

        // �����Ώۂ̐U�֐�����t�L���[�e�[�u��.�����敪��ݒ�p
        String l_strUpdateWhere = " institution_code = ? "
                           + " and branch_code = ? "
                           + " and account_code = ? "
                           + "and order_request_number = ?";

        String[] l_strArrayUpdateParams = {
            l_hostTransferAcceptRow.getInstitutionCode(),
            l_hostTransferAcceptRow.getBranchCode(),
            l_hostTransferAcceptRow.getAccountCode(),
            l_hostTransferAcceptRow.getOrderRequestNumber()
        };

        Map l_map = new Hashtable();
        // �����Ώۂ̐U�֐�����t�L���[�e�[�u��.�����敪��ݒ�p
        l_map.put("status", WEB3StatusDef.DEALT);

        // 2 - 3�j�@@�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
        l_queryProcessor.doUpdateAllQuery(
            HostTransferAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);
        log.exiting(STR_METHOD_NAME);

        return null;
    }
}@
