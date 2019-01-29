head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3AsynRlsCondOrderNotifyMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@�񓯊��������[���G���W���ʒm���C���T�[�r�X����(WEB3AsynRlsCondOrderNotifyMainServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 ��(FLJ) �V�K�쐬
�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ 2008/08/31�@@��(FLJ)�@@�C���@@�����ُ�Ή������Ƃ��āA�������g���C�������s��
 */

package webbroker3.omsadaptor.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.common.define.*;
import webbroker3.gentrade.*;
import webbroker3.gentrade.data.*;
import webbroker3.omsadaptor.message.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.rlsgateway.define.*;
import webbroker3.util.*;

/**
 * �i�񓯊��Ή����[���G���W���ʒm���C���T�[�r�X�����N���X�j�B
 * @@author  : ���iFLJ�j
 * @@version : 1.0
 */
public class WEB3AsynRlsCondOrderNotifyMainServiceImpl
    implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynRlsCondOrderNotifyMainServiceImpl.class);

    /**
     * ���[���G���W���ʒm���C�����N�G�X�g
     */
    private WEB3RlsCondOrderNotifyMainRequest request;

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3AsynRlsCondOrderNotifyMainServiceImpl(
        WEB3RlsCondOrderNotifyMainRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynRlsCondOrderNotifyMainServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);

        try
        {

            // 1.1. WEB3RlsCondOrderNotifyMainRequest.validate()
            request.validate();

            // 1.2. getDefaultProcessor()
            try
            {
                Processors.getDefaultProcessor();
            }
            catch (Exception l_e)
            {
                log.error(l_e.getMessage(), l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_e.getMessage(), l_e);
            }

            // 1.3. ���[���G���W���ʒm���C��TransactionCallback()
            WEB3RlsCondOrderNotifyMainTransactionCallback l_transactionCallBack =
                new WEB3RlsCondOrderNotifyMainTransactionCallback();

            // 1.4.
            //seFrom�����h�c()
            l_transactionCallBack.setFromAccountId(this.request.fromAccountId.longValue());
            //se�s�������h�c()
            l_transactionCallBack.setToAccountId(this.request.toAccountId.longValue());
            //setThreadNo()
            l_transactionCallBack.setThreadNo(request.threadNo);
            //setRlsNotifyOrderType()
            l_transactionCallBack.setRlsNotifyOrderType(request.rlsNotifyOrderType);

            // 1.5. doTransaction()
            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_queryProcesser.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_transactionCallBack);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(), l_dqe);
            }

        }
        catch (Throwable e)
        {

            log.error(e.getMessage(), e);

        }
        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
                longValue());
        }
        catch (Throwable t)
        {
            log.error(t.getMessage(), t);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (���[���G���W���ʒm���C��TransactionCallback)<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
     */
    public class WEB3RlsCondOrderNotifyMainTransactionCallback
        implements TransactionCallback
    {
        /**
         * Thread No<BR>
         */
        private Long threadNo;

        /**
         * (From����ID)
         */
        protected long fromAccountId;

        /**
         * (To����ID)
         */
        protected long toAccountId;

        /**
         * �����^�C�v<BR>
         */
        protected int rlsNotifyOrderTypes[];

        /**
         * �R���X�g���N�^�B<BR>
         */
        public WEB3RlsCondOrderNotifyMainTransactionCallback()
        {
        }

        /**
         * (set������ThreadNo)<BR>
         * <BR>
         * ������ThreadNo���v���p�e�B�ɃZ�b�g����B<BR>
         * @@params Long - ThreadNo<BR>
         */
        public void setThreadNo(Long l_threadNo)
        {
            this.threadNo = l_threadNo;
        }

        /**
         * (setFrom����ID)
         * ����.From����ID��this.From����ID�ɃZ�b�g����B
         * @@param l_lngFromAccountId - (From����ID)
         */
        public void setFromAccountId(long l_lngFromAccountId)
        {
            this.fromAccountId = l_lngFromAccountId;
        }

        /**
         * (setTo����ID)
         * ����.To����ID��this.To����ID�ɃZ�b�g����B
         * @@param l_lngToAccountId - (To����ID)
         */
        public void setToAccountId(long l_lngToAccountId)
        {
            this.toAccountId = l_lngToAccountId;
        }

        /**
         * (set�����^�C�v)
         * ����.�����^�C�v��this.�����^�C�v�ɃZ�b�g����B
         * @@param l_intTypes - (�����^�C�v)
         */
        public void setRlsNotifyOrderType(int l_intTypes[])
        {
            this.rlsNotifyOrderTypes = l_intTypes;
        }

        /**
         * ��菈��Thread�����b�N����<BR>
         * <BR>
         * Thread�ԍ�<BR>
         * @@return boolean<BR>
         */
        private boolean lockThread(long l_lngThreadNo)
        {
            final String STR_METHOD_NAME = "lockThread(long)";
            log.entering(STR_METHOD_NAME);
            boolean l_blnResult = false;
            try
            {
                String l_strWhere =
                    "thread_no = ? ";
                String l_strCondition = "for update nowait";

                Object l_bindVars[] =
                    {
                    new Long(l_lngThreadNo)
                };
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                List l_lisRows = l_queryProcesser.doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere, l_strCondition, l_bindVars);
                if (l_lisRows.size() > 0)
                {
                    l_blnResult = true;
                }
                else
                {
                    l_blnResult = false;
                }

            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
                l_blnResult = false;
            }

            log.exiting(STR_METHOD_NAME);
            return l_blnResult;
        }

        /**
         * (process)<BR>
         * <BR>
         * �g�����U�N�V�������������{����B<BR>
         * @@return Object
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         */
        public Object process() throws DataNetworkException, DataQueryException,
            DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            // 1.�P. �����X���b�h��L
            if (lockThread(this.threadNo.longValue()) == false)
            {
                log.error("�����X���b�h��L���b�N�擾�ł��Ȃ����߁A�������~!");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            // 1.2. ���[���G���W���ʒm�L���[�e�[�u���ǂݍ���
            StringBuffer l_sbWhere = new StringBuffer();
            String l_strWhere = "account_id >= ? and account_id <= ? and status = ?";
            l_sbWhere.append(l_strWhere);
            int l_intLength = 0;
            if (rlsNotifyOrderTypes != null)
            {
                l_intLength = rlsNotifyOrderTypes.length;
            }
            if (l_intLength > 0)
            {
                l_sbWhere.append(" and (");
                for (int i = 0; i < l_intLength; i++)
                {
                    if (i > 0)
                    {
                        l_sbWhere.append(" or ");
                    }
                    l_sbWhere.append("order_type = ?");
                }
                l_sbWhere.append(")");
            }

            Object[] l_bindVars = new Object[l_intLength + 3];
            l_bindVars[0] = new Long(this.fromAccountId);
            l_bindVars[1] = new Long(this.toAccountId);
            l_bindVars[2] = WEB3RlsNotifyStatusDef.NOT_DEAL;

            log.debug("WHERE=" + l_sbWhere.toString());
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);

            for (int i = 0; i < l_intLength; i++)
            {
                l_bindVars[i + 3] = new Integer(rlsNotifyOrderTypes[i]);
                log.debug("BindVars[" + (i + 3) + "]=" + l_bindVars[i + 3]);
            }

            String l_strOrderBy =
                " account_id,order_type,parent_order_id,serial_no_in_parent asc ";

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                RlsConOrderHitNotifyParams.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_bindVars);

            // 1.3. �擾�����L���[�e�[�u���̃��R�[�h�����ALoop
            RlsConOrderHitNotifyParams l_params = null;
            int l_intNum = l_lisSearchResult.size();

            if (l_intNum == 0)
            {
                log.debug("���[���G���W���ʒm�f�[�^���݂��܂���.");
            }

            for (int i = 0; i < l_intNum; i++)
            {
                try
                {
                    log.debug("���[���G���W���ʒm���C���FLoop���� i = " + i);

                    l_params = (RlsConOrderHitNotifyParams)
                        l_lisSearchResult.get(i);
                    // �ꌏ����
                    // �ꌏTransactionCallback����
                    WEB3AsynRlsCondOrderNotifyTransactionCallback
                        l_transactionCallback =
                        new WEB3AsynRlsCondOrderNotifyTransactionCallback(l_params);
                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch (Exception l_exp)
                {
                    log.error(l_exp.getMessage(), l_exp);
                    //l_params.setStatus(WEB3StatusDef.PROGRAM_ERROR);
                    //2008/08/31�@@���s�㏈���Ƃ��āA�������g���C�����֕ύX
                    l_params.setStatus(WEB3StatusDef.NOT_DEAL);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
