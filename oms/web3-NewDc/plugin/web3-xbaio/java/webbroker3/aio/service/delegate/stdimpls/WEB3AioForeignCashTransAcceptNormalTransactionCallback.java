head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�ݓ��o����t���폈���ꌏTransactionCallback(WEB3AioForeignCashTransAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 ���G�� (���u) �V�K�쐬
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
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostFCashTransOrderAcceptParams;
import webbroker3.aio.data.HostFCashTransOrderAcceptRow;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�O�ݓ��o����t���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioForeignCashTransAcceptNormalTransactionCallback.class);

    /**
      * �����P�ʃI�u�W�F�N�g�B<BR>
      */
    private AioOrderUnit aioOrderUnit;

    /**
      * �O�ݓ��o���`�[��t�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostFCashTransOrderAcceptParams hostFCashTransOrderAcceptParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_aioOrderUnit - (�����P��)
     * @@params l_hostFCashTransOrderAcceptParams - (�O�ݓ��o���`�[��t�L���[Params)
     */
    public WEB3AioForeignCashTransAcceptNormalTransactionCallback(
        AioOrderUnit l_aioOrderUnit,
        HostFCashTransOrderAcceptParams l_hostFCashTransOrderAcceptParams)
    {
        aioOrderUnit = l_aioOrderUnit;
        hostFCashTransOrderAcceptParams = l_hostFCashTransOrderAcceptParams;
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

        //���o����tUnitService�C���^�[�t�F�C�X
        WEB3AioCashTransferAcceptUnitService l_aioCashTransferAcceptUnitService =
            (WEB3AioCashTransferAcceptUnitService) Services.getService(
                WEB3AioCashTransferAcceptUnitService.class);

        //���o������UnitService�C���^�[�t�F�C�X
        WEB3AioCashTransferCompleteUnitService l_aioCashTransferCompleteUnitService =
            (WEB3AioCashTransferCompleteUnitService) Services.getService(
                WEB3AioCashTransferCompleteUnitService.class);

        AioOrderUnit l_aioOrderUnit = aioOrderUnit;
        HostFCashTransOrderAcceptParams l_hostFCashTransOrderAcceptParams = 
            hostFCashTransOrderAcceptParams;

        try
        {
            // 1.2.2)���o����tDB�X�V�������s���B  
            //[����]  
            //�����P�ʁF �擾���������P�ʃI�u�W�F�N�g  
            //�G���[�R�[�h�F �O�ݓ��o���`�[��t�L���[�e�[�u��.�G���[���b�Z�[�W  
            //��t�ʒm�敪�F �O�ݓ��o���`�[��t�L���[�e�[�u��.��t�ʒm�敪 
            l_aioCashTransferAcceptUnitService.execute(
                l_aioOrderUnit,
                l_hostFCashTransOrderAcceptParams.getErrorMessage(),
                l_hostFCashTransOrderAcceptParams.getAcceptDiv());

            // �O�ݓ��o���`�[��t�L���[�e�[�u��.��t�ʒm�敪 = "1"�i��t�����j�̏ꍇ
            if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(
                l_hostFCashTransOrderAcceptParams.getAcceptDiv()))
            {
                //1.2.3)��t�����̏ꍇ
                // 1.2.3.1)���o�����������ɔ��������f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B
                //[����]
                //�����P�ʁF get�����P��()�Ŏ擾���������P�ʃI�u�W�F�N�g
                l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
            }
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        //1.2.4)�O�ݓ��o���`�[��t�L���[�e�[�u���̏����敪�̍X�V
        //�O�ݓ��o���`�[��t�L���[�e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
        //"1"�i�����ρj�F 
        HashMap l_hashMap = new HashMap();
        l_hashMap.put("status", WEB3StatusDef.DEALT);

        String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
            "and branch_code = ? and account_code = ? and order_request_number = ? ";

        String l_strRequestCode = 
            l_hostFCashTransOrderAcceptParams.getRequestCode();
        String l_strInstitutionCode = 
            l_hostFCashTransOrderAcceptParams.getInstitutionCode();
        String l_strBranchCode = 
            l_hostFCashTransOrderAcceptParams.getBranchCode();
        String l_strAccountCode = 
            l_hostFCashTransOrderAcceptParams.getAccountCode();
        String l_strOrderRequestNumber = 
            l_hostFCashTransOrderAcceptParams.getOrderRequestNumber();

        Object[] l_objUpdaeWereValues = {
            l_strRequestCode, 
            l_strInstitutionCode,
            l_strBranchCode, 
            l_strAccountCode, 
            l_strOrderRequestNumber};

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateAllQuery(
            HostFCashTransOrderAcceptRow.TYPE,
            l_strUpdateWhere,
            l_objUpdaeWereValues,
            l_hashMap);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}

@
