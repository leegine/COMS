head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������ʒm���폈���ꌏTransactionCallback(WEB3MutualTradeOrderNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 ���u��(���{���u) �V�K�쐬
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.data.HostXbmfOrderNotifyRow;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * �i�����M�����������ʒm���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3MutualTradeOrderNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualTradeOrderNotifyNormalTransactionCallback.class);

    /**
      * ���M�����ʒm�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostXbmfOrderNotifyParams orderNotifyParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_orderNotifyParams - (���M�����ʒm�L���[Params)
     */
    public WEB3MutualTradeOrderNotifyNormalTransactionCallback(
        HostXbmfOrderNotifyParams l_orderNotifyParams)
    {
        orderNotifyParams = l_orderNotifyParams;
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

        //1.2�j�@@���M���������ʒmUnitService���擾����B
        WEB3MutualTradeOrderNotifyUnitService
            l_mutualTradeOrderNotifyUnitService =
                (WEB3MutualTradeOrderNotifyUnitService) Services.getService(
                    WEB3MutualTradeOrderNotifyUnitService.class);

        HostXbmfOrderNotifyParams l_orderNotifyParams = orderNotifyParams;
        try
        {
            //1.4�j�����������s��
            //���M���������ʒmUnitService.notify���������ʒm()���R�[������B
            l_mutualTradeOrderNotifyUnitService.notifyTradeOrderNotify(
                l_orderNotifyParams);
        }
		catch (WEB3BaseException l_ex)
		{
			ErrorInfo l_errorInfo = l_ex.getErrorInfo();
			l_errorInfo.setErrorClass(l_ex.getClass().getName());
			throw new DataCallbackException(
				l_ex.getErrorMessage(),
				l_errorInfo);
		}

        HashMap l_map = new HashMap();

        //�،���ЃR�[�h�擾
        String l_strInstatutionCode =
            l_orderNotifyParams.getInstitutionCode();

        //���X�R�[�h�擾
        String l_strBranchCode =
            l_orderNotifyParams.getBranchCode();

        //���ʃR�[�h�擾
        String l_strOrderRequestNumber =
            l_orderNotifyParams.getOrderRequestNumber();

        String l_strUpdateWhere =
            " institution_code = ? "+          //�X�V�،���ЃR�[�h
            " and branch_code = ? "+           //�X�V���X�R�[�h
            " and order_request_number = ? ";  //�X�V���ʃR�[�h
        String[] l_updateParams = {
            l_strInstatutionCode,
            l_strBranchCode,
            l_strOrderRequestNumber };

        // 1.5�j�L���[�e�[�u���̏����敪���X�V
        //�|��������������I�������ꍇ�A
         //�����Ώۂ̓��M�����ʒm�L���[���R�[�h.�����敪�Ɂh
          //1�F�����ρh���Z�b�g���X�V����B

        l_map.put("status", WEB3StatusDef.DEALT);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        // do update
        l_queryProcessor.doUpdateAllQuery(
            HostXbmfOrderNotifyRow.TYPE,
            l_strUpdateWhere,
            l_updateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}

@
