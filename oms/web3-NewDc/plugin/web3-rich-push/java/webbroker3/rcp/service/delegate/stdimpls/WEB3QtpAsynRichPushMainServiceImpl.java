head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpAsynRichPushMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �񓯊��Ή����b�`�N���C�A���g�v�b�V�����C���T�[�r�X�����N���X(WEB3QtpAsynRichPushMainServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/10 ��(FLJ) �V�K�쐬
                  : 2009/06/03 ��(FTL) ���Ή�
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.rcp.WEB3QtpRichPushPlugin;
import webbroker3.rcp.WEB3RichPushObjectComparator;
import webbroker3.rcp.WEB3RichPushObjectCompareKey;
import webbroker3.rcp.WEB3RichPushObjectContext;
import webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityContRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushHistoryPK;
import webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoContRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptRow;
import webbroker3.rcp.define.WEB3RichPushDataStatusDef;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.message.WEB3QtpRichPushMainRequest;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushGateWayService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushUnitService;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3LogUtility;

/**
 * �񓯊��Ή����b�`�N���C�A���g�v�b�V�����C���T�[�r�X�����N���X
 * @@author  : ���iFLJ�j
 * @@version : 1.0
 */
public class WEB3QtpAsynRichPushMainServiceImpl
    implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpAsynRichPushMainServiceImpl.class);

    /**
     * �v�b�V���T�[�r�X
     */
    protected final static HashMap pushServices = new HashMap();

    /**
     * ���b�`�N���C�A���g�v�b�V�����C�����N�G�X�g
     */
    private WEB3QtpRichPushMainRequest request;

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3QtpAsynRichPushMainServiceImpl(
        WEB3QtpRichPushMainRequest
        l_request)
    {
        final String STR_METHOD_NAME =
            "WEB3QtpAsynRichPushMainServiceImpl()";
        log.entering(STR_METHOD_NAME);

        if(l_request == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02779,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        this.request = l_request;
        synchronized(pushServices)
        {
            if(pushServices.size() == 0)
            {
                // �����M�p������t�ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.EQUITY_ORDER_ACCEPT,
                    Services.getService(WEB3QtpRichPushEquityMarginOrderAcceptUnitService.class));
                // �M�p�������n������t�ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.SWAP_ORDER_ACCEPT,
                    Services.getService(WEB3QtpRichPushSwapOrderAcceptUnitService.class));
                // �����M�p��������ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.EQTYPE_CHANGE_CANCEL,
                    Services.getService(WEB3QtpRichPushEquityMarginChangeCancelUnitService.class));
                // �����M�p�o���ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.EQTYPE_CONT,
                    Services.getService(WEB3QtpRichPushEquityMarginContUnitService.class));
                // �����M�p�����ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.EQTYPE_LAPSE,
                    Services.getService(WEB3QtpRichPushEquityMarginLapseUnitService.class));
                // �敨�n�o������t�ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.IFO_ORDER_ACCEPT,
                    Services.getService(WEB3QtpRichPushFuOpOrderAcceptUnitService.class));
                // �敨�n�o��������ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.IFO_CHANGE_CANCEL,
                    Services.getService(WEB3QtpRichPushFuOpChangeCancelUnitService.class));
                // �敨�n�o�o���ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.IFO_CONT,
                    Services.getService(WEB3QtpRichPushFuOpContUnitService.class));
                // �敨�n�o�����ʒm�T�[�r�X
                pushServices.put(WEB3RichPushDataTypeDef.IFO_LAPSE,
                    Services.getService(WEB3QtpRichPushFuOpLapseUnitService.class));

                // �T�[�r�X�o���邱�Ƃ̃`�F�b�N
                Iterator l_iterator = pushServices.entrySet().iterator();
                while(l_iterator.hasNext())
                {
                    Entry l_entry = (Entry)l_iterator.next();
                    if(l_entry.getValue() == null)
                    {
                        throw new NullPointerException("Push service <" + l_entry.getKey() + "> is null!!");
                    }
                }
            }
        }
    }

    /**
     * ���s
     */
    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3QtpAsynRichPushMainServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);

        WEB3DescendRacCtxService l_sv = (WEB3DescendRacCtxService) Services.getService(
            WEB3DescendRacCtxService.class);
        l_sv.setAccountIdCtx(this.request.fromAccountId.longValue());

        try
        {
            // 1.1. WEB3QtpRichPushMainRequest.validate()
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

            // 1.3. ���b�`�N���C�A���g�v�b�V�����C��TransactionCallback()
            WEB3QtpRichPushMainTransactionCallback l_transactionCallBack =
                new WEB3QtpRichPushMainTransactionCallback();

            // 1.4.
            //seFrom�����h�c()
            l_transactionCallBack.setFromAccountId(this.request.fromAccountId.longValue());
            //seTo�����h�c()
            l_transactionCallBack.setToAccountId(this.request.toAccountId.longValue());
            //setThreadNo()
            l_transactionCallBack.setThreadNo(request.threadNo);
            //set�f�[�^�^�C�v�z��()
            l_transactionCallBack.setType(request.type);

            //�������R���e�N�X�g
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
                null);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
                new WEB3RichPushObjectContext());

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
            WEB3RichPushObjectContext l_context = (WEB3RichPushObjectContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME
                );
            try
            {
                this.saveQtpRichPushHistory(l_context.getPushObjects(),null);
            }
            catch (Throwable t)
            {
                log.error(t.getMessage(), t);
            }

            //�N���A�R���e�N�X�g
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
                null);
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
        l_sv.clearAccountIdCtx();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (���b�`�N���C�A���g�v�b�V�����C��TransactionCallback)<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
     */
    public class WEB3QtpRichPushMainTransactionCallback
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
         * �f�[�^�^�C�v�z��<BR>
         */
        protected String[] type;

        /**
         * �R���X�g���N�^�B<BR>
         */
        public WEB3QtpRichPushMainTransactionCallback()
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
         * (set�f�[�^�^�C�v�z��)
         * ����.�f�[�^�^�C�v�z���this.�f�[�^�^�C�v�z��ɃZ�b�g����B
         * @@param l_strType - (�f�[�^�^�C�v�z��)
         */
        public void setType(String[] l_strType)
        {
            if (l_strType != null)
            {
                this.type = new String[l_strType.length];
                for (int i = 0; i < l_strType.length; i++)
                {
                    this.type[i] = l_strType[i];
                }
            }
        }

        /**
         * Thread�����b�N����<BR>
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

            // 1.1. �����X���b�h��L
            if (lockThread(this.threadNo.longValue()) == false)
            {
                log.error("�����X���b�h��L���b�N�擾�ł��Ȃ����߁A�������~!");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            // 1.2�@@���b�`�N���C�A���g�փf�[�^�v�b�V��
            if (this.type == null)
            {
                // �f�[�^�^�C�v���w�肳��Ă��Ȃ���ΑS�@@�\�v�b�V��
                Iterator l_iterator = pushServices.values().iterator();
                while(l_iterator.hasNext())
                {
                    ((WEB3QtpRichPushUnitService)l_iterator.next()).push(this.fromAccountId, this.toAccountId);
                }
            }
            else
            {
                // �f�[�^�^�C�v�̎w�肪����ꍇ�A�w�肳�ꂽ�@@�\�̂݃v�b�V��
                for (int i = 0; i < this.type.length; i++)
                {
                    WEB3QtpRichPushUnitService l_pushService = (WEB3QtpRichPushUnitService)pushServices.get(this.type[i]);
                    if(l_pushService != null)
                    {
                        l_pushService.push(this.fromAccountId, this.toAccountId);
                    }
                    else
                    {
                        log.error("���m�f�[�^�^�C�v<" + this.type[i] + ">���m���ꂽ�A�����X�L�b�v!"  );
                    }
                }
            }

            WEB3RichPushObjectContext l_context = (WEB3RichPushObjectContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME
                );

            //�\�[�g
            List l_allList = l_context.getPushObjects();
            //��Ж��\�[�gMap���擾
            Map l_lstMap4SortedMap = sortQtpRichPushObjects(l_allList);

            Set set = l_lstMap4SortedMap.keySet();
            Iterator iterator = set.iterator();
            String key;
            while (iterator.hasNext())
            {
                key = (String) iterator.next();
                Map l_mapTree = (Map) l_lstMap4SortedMap.get(key);
                List l_lstsorted = getListFromTreeMap(l_mapTree);

                //�ő呗�M�����`�F�b�N
                int l_intPushMaxMessageSize = getPushMaxMessageSize();
                ArrayList l_lstMax = new ArrayList();
                for (int i = 0; i < l_lstsorted.size(); i++)
                {
                    if (l_lstMax.size() < l_intPushMaxMessageSize)
                    {
                        l_lstMax.add(l_lstsorted.get(i));
                    }
                }

                //���M
                boolean[] l_ret = forwardGateWay(key, l_lstMax);

                //�f�[�^�v�b�V�����ʂ𗚗��e�[�u���ɕۑ�
                saveQtpRichPushHistory(l_lstMax,
                        l_ret);

            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * ���ő�v�b�V�����b�Z�[�W�����擾
     *
     * @@return int
     */
    public int getPushMaxMessageSize()
    {

        final String STR_METHOD_NAME = "getPushMaxMessageSize()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return WEB3QtpRichPushPlugin.getPushMaxMessageSize();

    }

    /**
     * �Q�[�g�E�F�C�o�R���b�`�N���C�A���g�փf�[�^�v�b�V��
     *
     * @@param l_strInstitutionCode String
     * @@param l_lstPushObjects String
     * @@param l_mapHistoryRecords Map
     * @@return boolean
     */
    public boolean[] forwardGateWay(String l_strInstitutionCode, List l_lstPushObjects)
    {

        final String STR_METHOD_NAME = "forwardToGwy()";
        log.entering(STR_METHOD_NAME);

        WEB3QtpRichPushGateWayService l_pservice =
            (WEB3QtpRichPushGateWayService) Services.getService(
            WEB3QtpRichPushGateWayService.class);
        boolean[] l_ret = l_pservice.push(l_strInstitutionCode, l_lstPushObjects);

        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

    /**
     * �v�b�V�����ʂ𗚗��e�[�u���ɕۑ�
     *
     * @@param l_lstPushRecords List
     * @@param l_mapPushHistoryRecords Hashtable
     * @@param l_strStatus String
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public void saveQtpRichPushHistory(List l_lstPushRecords,
                                    boolean[] l_status) throws DataQueryException,
        DataNetworkException
    {

        final String STR_METHOD_NAME = "saveQtpRichPushHistory()";
        log.entering(STR_METHOD_NAME);
        
        if (l_lstPushRecords == null)
        {
            log.error(
                "l_lstPushRecords==null! save fail!");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        if(l_status == null)
        {
            log.error(
            "l_status==null! save all result as false!");            
            l_status = new boolean[l_lstPushRecords.size()];
            for (int i = 0; i < l_status.length; i++)
            {
                l_status[i] = false;
            }
        }
        if (l_lstPushRecords.size() != l_status.length )
        {
            log.error(
                "the size of l_lstPushRecords is not same as the result status! save fail! data size:"+l_lstPushRecords.size() + ",result size:"+l_status.length);
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        Timestamp l_tm = GtlUtils.getSystemTimestamp();
        if (l_lstPushRecords.size() > 0)
        {
            BatchedQuery[] l_bqs = new BatchedQuery[l_lstPushRecords.size()];
            for (int i = 0; i < l_lstPushRecords.size(); i++)
            {
                Row l_pushRow = (Row) l_lstPushRecords.get(i);
                //���M�����X�e�[�^�X
                String l_strStatus = WEB3RichPushDataStatusDef.DEAL;
                //���M����=���s�̏ꍇ
                if (l_status[i] == false)
                {
                    l_strStatus = WEB3RichPushDataStatusDef.DATA_ERROR;
                }
                
                BatchedQuery l_bq = doUpdateQtpPushHistory(l_pushRow.
                    getPrimaryKey().
                    toString(),
                    l_strStatus, l_tm);
                l_bqs[i] = l_bq;
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doQueries(l_bqs);
        }
        log.debug("saveQtpRichPushHistory ����="+l_lstPushRecords.size());
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * ���b�`�N���C�A���g�f�[�^�v�b�V�����ʂ𗚗��e�[�u���ɕۑ�����B
     *
     * @@param l_strSerlnum String
     * @@param l_strStatus String
     * @@throws DataNetworkException
     * @@throws DataQueryException
     */
    private BatchedQuery doUpdateQtpPushHistory(String l_strSerlnum,
                                             String l_strStatus, Timestamp l_tm) throws
        DataNetworkException, DataQueryException
    {

        final String STR_METHOD_NAME = "doUpdateQtpPushHistory()";
        log.entering(STR_METHOD_NAME);

        Map l_changes = new HashMap();
        l_changes.put("status", l_strStatus);
        l_changes.put("last_updated_timestamp", l_tm);

        QtpRichPushHistoryPK l_pk = new QtpRichPushHistoryPK();
        l_pk.serlnum = Long.valueOf(l_strSerlnum).longValue();
        log.exiting(STR_METHOD_NAME);

        return BatchedQuery.createUpdateQuery(l_pk, l_changes);
    }

    /**
     * ��ВP�ʂŃv�b�V���I�u�W�F�N�g���\�[�g����
     *
     * @@param l_lstPushObjects List
     * @@return Map
     */
    public Map sortQtpRichPushObjects(List l_lstPushObjects)
    {

        Map l_tblInstBaseMap4Map = new Hashtable();

        for (int i = 0; i < l_lstPushObjects.size(); i++)
        {

            Row l_row = (Row) l_lstPushObjects.get(i);

            // QTP�����M�p������t�ʒm���[
            if (l_row instanceof QtpRichPushEqOrderacceptRow)
            {
                QtpRichPushEqOrderacceptRow l_spRow = (QtpRichPushEqOrderacceptRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            // QTP�M�p�������n������t�ʒm���[
            else if (l_row instanceof QtpRichPushSwOrderacceptRow)
            {
                QtpRichPushSwOrderacceptRow l_spRow = (QtpRichPushSwOrderacceptRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);

            }
            // QTP�����M�p��������ʒm���[
            else if (l_row instanceof QtpRichPushEqChangecancelRow)
            {

                QtpRichPushEqChangecancelRow l_spRow = (QtpRichPushEqChangecancelRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);

            }
            // QTP�����M�p�o���ʒm���[
            else if (l_row instanceof QtpRichPushEquityContRow)
            {

                QtpRichPushEquityContRow l_spRow = (QtpRichPushEquityContRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);

            }
            // QTP�����M�p�����ʒm���[
            else if (l_row instanceof QtpRichPushEquityLapseRow)
            {
                QtpRichPushEquityLapseRow l_spRow = (QtpRichPushEquityLapseRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            // QTP�敨�n�o������t�ʒm���[
            else if (l_row instanceof QtpRichPushIfoOrderacceptRow)
            {
                QtpRichPushIfoOrderacceptRow l_spRow = (QtpRichPushIfoOrderacceptRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            // QTP�敨�n�o��������ʒm���[
            else if (l_row instanceof QtpRichPushIfoChangecancelRow)
            {
                QtpRichPushIfoChangecancelRow l_spRow = (QtpRichPushIfoChangecancelRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            // QTP�敨�n�o�o���ʒm���[
            else if (l_row instanceof QtpRichPushIfoContRow)
            {
                QtpRichPushIfoContRow l_spRow = (QtpRichPushIfoContRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            // QTP�敨�n�o�����ʒm���[
            else if (l_row instanceof QtpRichPushIfoLapseRow)
            {
                QtpRichPushIfoLapseRow l_spRow = (QtpRichPushIfoLapseRow)l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
        }

        return l_tblInstBaseMap4Map;
    }

    /**
     * getListFromTreeMap
     *
     * @@param l_treeMap Map
     * @@return List
     */
    public List getListFromTreeMap(Map l_treeMap)
    {
        ArrayList l_lstRet = new ArrayList();
        //�C�e���[�^�擾
        Set set = l_treeMap.keySet();
        Iterator iterator = set.iterator();
        Object key;
        while (iterator.hasNext())
        {
            key = iterator.next();
            l_lstRet.add(l_treeMap.get(key));
        }
        return l_lstRet;
    }
}
@
