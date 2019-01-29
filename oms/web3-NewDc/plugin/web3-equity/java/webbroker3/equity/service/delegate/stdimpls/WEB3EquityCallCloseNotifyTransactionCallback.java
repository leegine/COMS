head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCallCloseNotifyTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o���ʒmcall�����ʒmTransactionCallback(WEB3EquityCallCloseNotifyTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 �������F(SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyRow;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����o���ʒmcall�����ʒmTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j
 * @@author �������F
 * @@version 1.0
 */
public class WEB3EquityCallCloseNotifyTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCallCloseNotifyTransactionCallback.class);

    /**
      * �����P�ʃI�u�W�F�N�g�B<BR>
      */
    private EqTypeOrderUnit orderUnit;

    /**
      * �����o���ʒm�L���[Params�I�u�W�F�N�g�B
      */
    private HostEquityOrderExecNotifyParams orderExecNotifyParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_orderUnit - (�����P��)
     * @@params l_orderExecNotifyParams - (�����o���ʒm�L���[Params)
     */
    public WEB3EquityCallCloseNotifyTransactionCallback(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_orderExecNotifyParams)
    {
        orderUnit = l_orderUnit;
        orderExecNotifyParams = l_orderExecNotifyParams;
    }

    /**
     * �g�����U�N�V�������������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���ʒm�ꌏ�T�[�r�X�jcall�����ʒm�����v�Q�ƁB<BR>
     * @@return Object
     * @@throws DataNetworkException, DataQueryException, DataCallbackException
     */
    public Object process()
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        HostEqtypeCloseOrderNotifyParams l_notifyParams = null;
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        try
        {
            // 1.5.1.1. get�����������ʒm�L���[(�����o���ʒm�L���[Params)
            l_notifyParams = this.getCloseNotifyQueueParams(orderExecNotifyParams);

            if (l_notifyParams == null)
            {
                throw new DataCallbackException(
                    "�Y���f�[�^�Ȃ��F�o�������҂��̏����������ʒm�L���[�����݂��܂���B",
                    new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME));
            }
            
            // 1.5.1.2. exec����(���������ʒm�L���[Params, �����P��)
            WEB3EquityReceiveCloseOrderUnitService l_unitService =
                (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                    WEB3EquityReceiveCloseOrderUnitService.class);
            l_unitService.execCloseOrder(l_notifyParams, orderUnit);

            // 1.5.1.3. ���������ʒm�L���[.�����敪��update��commit����
            // �������敪�̍X�V�l�ݒ�
            //   exec����( )���G���[�ƂȂ����ꍇ�F"9�F�G���["
            //   exec����( )������ɏI�������ꍇ�F"1�F������"
            l_notifyParams.setStatus(WEB3StatusDef.DEALT);
            l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_processor.doUpdateQuery(l_notifyParams);
        }
        catch (WEB3BaseException l_bex)
        {
            ErrorInfo l_errorInfo = l_bex.getErrorInfo();
            if (l_errorInfo.getErrorTag().startsWith("BUSINESS_ERROR"))
            {
                throw new DataCallbackException(
                    l_bex.getMessage(),
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01961,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_bex.getMessage(),
                        l_bex));
            }
            else
            {
                throw new DataCallbackException(
                    l_bex.getMessage(),
                    new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80077,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_bex.getMessage(),
                        l_bex));
            }
        }
        catch (Exception l_ex)
        {
            throw new DataCallbackException(
                l_ex.getMessage(),
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80077,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex));
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �iget�����������ʒm�L���[�j<BR>
     * <BR>
     * �y���������ʒm�L���[�e�[�u���z���A�����ʒm�T�[�r�X�ɂ��o���҂��Ƃ��ꂽ<BR>
     * �u�������v���R�[�h���擾����B<BR>
     * <BR>
     * �P�j�@@�y���������ʒm�L���[�e�[�u���z���A�ȉ��̏����Ńf�[�^���擾����B<BR>
     * �@@�@@�@@��select for update�w��ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@�����o������<BR>
     * �@@�@@�f�[�^�R�[�h���hAI813�h<BR>
     * �@@�@@���@@�،���ЃR�[�h�������̏o���ʒm�L���[.�،���ЃR�[�h<BR>
     * �@@�@@���@@���X�R�[�h�������̏o���ʒm�L���[.���X�R�[�h<BR>
     * �@@�@@���@@�ڋq�R�[�h�������̏o���ʒm�L���[.�ڋq�R�[�h(*)<BR>
     * �@@�@@���@@���ʃR�[�h�������̏o���ʒm�L���[.���ʃR�[�h<BR>
     * �@@�@@���@@�����敪���h�������h<BR>
     * <BR>
     * �@@�@@(*)�_�C���N�g�����N�ɂ��o���f�[�^�̏ꍇ�A�ڋq�R�[�h�ɂ́i0 or null�j���ݒ肳��Ă���B<BR>
     * �@@�@@�@@�@@���̏ꍇ�́Athis.�����P��.����ID�ɊY������ڋq.�����R�[�h�����Ɏg�p����B<BR>
     * <BR>
     * �Q�j�@@�Y������f�[�^�����݂���ꍇ�́A�擾�������������ʒm�L���[Params��Ԃ��B<BR>
     * �@@�@@�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��Ԃ��B<BR>
     * <BR>
     * @@param l_execNotifyQueParams - (�o���ʒm�L���[)<BR>
     * �y�����o���ʒm�L���[�e�[�u���z�̂P���R�[�h�B
     * @@return HostEqtypeCloseOrderNotifyParams
     * @@throws WEB3BaseException
     */
    protected HostEqtypeCloseOrderNotifyParams getCloseNotifyQueueParams(
        HostEquityOrderExecNotifyParams l_execNotifyQueParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCloseNotifyQueueParams(HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�y���������ʒm�L���[�e�[�u���z���A�ȉ��̏����Ńf�[�^���擾����B
        // �@@�@@�����o������
        // �@@�@@�f�[�^�R�[�h���hAI813�h
        // �@@�@@���@@�،���ЃR�[�h�������̏o���ʒm�L���[.�،���ЃR�[�h
        // �@@�@@���@@���X�R�[�h�������̏o���ʒm�L���[.���X�R�[�h
        // �@@�@@���@@�ڋq�R�[�h�������̏o���ʒm�L���[.�ڋq�R�[�h
        // �@@�@@���@@���ʃR�[�h�������̏o���ʒm�L���[.���ʃR�[�h
        // �@@�@@���@@�����敪���h�������h
        String l_strWhere = " request_code = ? "
                          + " and institution_code = ? "
                          + " and branch_code = ? "
                          + " and account_code = ? "
                          + " and order_request_number = ? "
                          + " and status = ? ";
        String l_strAccountCode = l_execNotifyQueParams.getAccountCode();
        if (l_strAccountCode == null ||
            Integer.parseInt(l_strAccountCode.trim()) == 0)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            try
            {
                MainAccount l_account =
                    l_accountManager.getMainAccount(this.orderUnit.getAccountId());
                l_strAccountCode = l_account.getAccountCode();
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }
        Object[] l_objWhere =
          { WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE,
            l_execNotifyQueParams.getInstitutionCode(),
            l_execNotifyQueParams.getBranchCode(),
            l_strAccountCode,
            l_execNotifyQueParams.getOrderRequestNumber(),
            WEB3StatusDef.DEALING };

        log.debug("===================���������J�n=====================");
        log.debug("�،���ЃR�[�h: [" + l_execNotifyQueParams.getInstitutionCode() + "]");
        log.debug("    ���X�R�[�h: [" + l_execNotifyQueParams.getBranchCode() + "]");
        log.debug("    �ڋq�R�[�h: [" + l_strAccountCode + "]");
        log.debug("    ���ʃR�[�h: [" + l_execNotifyQueParams.getOrderRequestNumber() + "]");
        log.debug("===================���������I��=====================");

        // �Q�j�@@�Y������f�[�^�����݂���ꍇ�́A�擾�������������ʒm�L���[Params��Ԃ��B
        // �@@�@@�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��Ԃ��B
        List l_lisSearchResult = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisSearchResult =
                l_QueryProcessor.doFindAllQuery(
                    HostEqtypeCloseOrderNotifyRow.TYPE,
                    l_strWhere,
                    null,
                    "for update",
                    l_objWhere);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        int l_intNum = 0;
        if (l_lisSearchResult != null)
        {
            l_intNum = l_lisSearchResult.size();
        }
        HostEqtypeCloseOrderNotifyParams l_params = null;
        log.debug("���������ʒm�L���[�̌��� : [" + l_intNum + "]");
        if (l_intNum > 0)
        { 
            l_params = (HostEqtypeCloseOrderNotifyParams)l_lisSearchResult.get(0);
        }


        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@