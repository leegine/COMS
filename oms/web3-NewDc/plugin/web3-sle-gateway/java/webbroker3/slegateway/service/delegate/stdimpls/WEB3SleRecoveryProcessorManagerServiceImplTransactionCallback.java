head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/26 ��(FLJ) �V�K�쐬
 */
package webbroker3.slegateway.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.slebase.data.SleConnStatusChangesParams;
import webbroker3.slebase.data.SleConnStatusChangesRow;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleConnectionStatusEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.slebase.utils.SleConnStatusChangesDbConstants;
import webbroker3.slebase.utils.SleConstants;
import webbroker3.slegateway.define.WEB3SleMarketAdapterErrorMessageDef;
import webbroker3.slegateway.WEB3SleOrderBookStatusChecker;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

/**
 * ���J�o���[��A�����̃g�����U�N�V�����ł��B
 * 
 * @@author      ���iFLJ�j
 * @@version     V1.0  
 */
public class WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback implements TransactionCallback
{
    
    private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback.class);

    private static final boolean  DBG   = m_log.ison();
    
    private WEB3SleProcessors m_wsp;

    /**
     * Thread No <BR>
     */
    private Long m_threadNo;

    /**
     * (From����ID)
     */
    private long m_fromAccountId;

    /**
     * (To����ID)
     */
    private long m_toAccountId;
    
    /**
     * ������ ORDER_BOOK ����ۑ����Ă���
     */
    private Map m_productsOrderBookMap = new HashMap();
    
    private PrimaryKey m_sleConn;
    
    /**
     * �����R�[�h�ɑΉ����鏈���t���O
     */
    private Map m_recoveryCheckDoneProductCodesMap = new HashMap(); 
   
    private static final String METHOD_PROCESS = "process()";
    private static final String METHOD_LOCK = "lockThread(final long)";
    private static final String METHOD_GET_ROWS = "getRows()";
    private static final String METHOD_IS_RECOVERYAVAILABLE = "isRecoveryAvailable (String marketCode)"; 
    private static final String METHOD_UPDATE_SLECONNSTATUS ="updateSleConnStatus(String marketCode)";
    private static final String METHOD_PROCESS_ROW = "processRow(final SleSendQRow sleSendqRow)";
    private static final String METHOD_RECOVERY_SLE_SENDQ_STATUS = "recoverySleSendqStatus (final SleSendQRow sleSendqRow)";

    public WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback(WEB3SleProcessors wsp)
    {
        m_wsp = wsp;
    }
    
    
    /**
     * From����ID Setter
     * @@param l_lngFromAccountId
     */
    public void setFromAccountId(long l_lngFromAccountId) {
      m_fromAccountId = l_lngFromAccountId;
    }

    /**
     * (To����ID) Setter
     * @@param l_lngToAccountId
     */
    public void setToAccountId(long l_lngToAccountId) {
      m_toAccountId = l_lngToAccountId;
    }

    /**
     * (ThreadNo) Setter
     * @@param l_lngThreadNo
     */
    public void setThreadNo(Long l_lngThreadNo) {
      m_threadNo = l_lngThreadNo;
    }    
   
    
    /**
     * (process) <BR>
     * <BR>
     * �g�����U�N�V�������������s����B <BR>
     * 
     * @@return Object
     * @@throws DataNetworkException,
     *             DataQueryException, DataCallbackException
     */
    public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
    {
        m_log.entering(METHOD_PROCESS);

        try
        {
            /*
             * ���J�o���[�����̃g�����U�N�V�������J�n����O�ŁA�������b�N���擾����B
             * �������b�N���擾�ł��Ȃ��ꍇ�A���L�̃G���[���b�Z�[�W�����O�ɏo�͂���B �eThread No = %d
             * �̃X���b�h�ɑ΂��A�����X���b�h��p���b�N�擾�ł��Ȃ����߁A�������~�B�h ��xTrade
             * ��AP�T�[�o���ɁA�����X���b�h�����ʂ��邽�߂̃X���b�hNo���w�肷��B
             */
            if (m_threadNo==null || !lockThread(m_threadNo.longValue()))
            {
                final String l_strMsgErr = WEB3SleMarketAdapterErrorMessageDef.SEND_THREAD_LOCKED_STATUS;
                m_log.error(l_strMsgErr);
                m_log.exiting(METHOD_PROCESS);
                return null;
            }

            /*
             * SLE�ڑ���ԊǗ��e�[�u������ASLE�ڑ��̍ŐV��Ԃ��擾���A���J�o���[�������\�ł��邩���f����B
             * �s�\�ł���΁A���J�o���[�����𒆎~����B
             */
            if (!isRecoveryAvailable(SleConstants.Markets.SEHK.MARKET_CODE))
            {
                final String l_strMsgErr = WEB3SleMarketAdapterErrorMessageDef.SLE_RECOVERY_NOT_AVAILABLE;
                m_log.error(l_strMsgErr);
                updateSleConnStatus();
                m_log.exiting(METHOD_PROCESS);
                return null;
            }

            //SEND_Q�ɑ��݂�����M���m�F�����M�ς݂̒������b�Z�[�W���擾����
            final List rows = getRows();
            final int size = rows.size();

            //�擾��������M�m�F�����M�ς݂̒������b�Z�[�W�ɑ΂��A�P���������b�Z�[�W���������J��Ԃ��B
            for (int i = 0; i < size; i++)
            {
                processRow((SleSendQRow) rows.get(i));
            }

            //���J�o���[�����̍Ō�ASLE�ڑ���ԏ��̏����敪���e�����ς�(1)�f�Ƃ���B
            updateSleConnStatus();

        }
        catch (DataException e)
        {
            final String msgErr = "Error while processing the rows.";
            m_log.error(msgErr + e);
            throw new DataCallbackException(msgErr+e);
        }

        m_log.exiting(METHOD_PROCESS);
        return null;
    }
    


    /**
     * �X���b�h���b�N���擾���� <BR>
     * <BR>
     * Thread�ԍ� <BR>
     * 
     * @@param l_lngThreadNo
     *            �擾�������X���b�h�ԍ�
     * @@return boolean ���b�N�擾�ł��邩�ǂ��� <BR>
     */
    public boolean lockThread(final long l_lngThreadNo)
    {

        m_log.entering(METHOD_LOCK);

        boolean l_blnResult = false;
        
        try
        {
            String l_strWhere = "thread_no = ? ";
            String l_strCondition = "for update nowait";
            Object[] l_aryBindVars = { new Long(l_lngThreadNo) };
            final QueryProcessor qp = m_wsp.getDefaultProcessor();
            List l_lisQueryResult = qp.doFindAllQuery(DaemonTriggerRow.TYPE, l_strWhere, l_strCondition,l_aryBindVars);
            if (l_lisQueryResult.size() > 0)
            {
                l_blnResult = true;
            }
        }
        catch (DataException e)
        {
            m_log.error("Error while locking the thread."+e, e);
        }

        m_log.exiting(METHOD_LOCK);
        return l_blnResult;
    }
    
    /**
     * SLE�ڑ���ԊǗ��e�[�u������ASLE�ڑ��̍ŐV��Ԃ��擾���A���J�o���[�������\�ł��邩���f����B
     * 
     * @@param l_strMarketCode �s��R�[�h
     * @@return �ڑ���Ԃ͐��킩�ǂ���
     */
    protected boolean isRecoveryAvailable(String l_strMarketCode)
    {
        m_log.entering(METHOD_IS_RECOVERYAVAILABLE);

        boolean l_blnResult = true;

        try
        {

            final QueryProcessor qp = m_wsp.getDefaultProcessor();
            /*
             * SLE�ڑ���ԊǗ��e�[�u���ɑ΂��A�����������s���A�e������(0)�f��SLE�ڑ���ԃ��R�[�h���擾����B ���i�����
             * �����敪(�������F0) ���\�[�e�B���O�� �s��R�[�h�A�L���[ID(�~��)
             */
            List l_lisQueryResult = qp.doFindAllQuery(SleConnStatusChangesParams.TYPE, "market_code = ?",
                    "market_code,que_id desc", null, new Object[] {l_strMarketCode });

            //�������ʂ��Ȃ��ꍇ�A���J�o���[�������~
            if (l_lisQueryResult.size() <= 0)
            {
                final String l_strMsgErr = "the status of SLE connection can not be found.";
                m_log.error(l_strMsgErr);
                l_blnResult = false;
            }
            else
            {

                //�������ʂ���A��ԎႢSLE�ڑ���ԃ��R�[�h�̂ݔ����o��
                SleConnStatusChangesRow l_sleConnStatusChangesRow = (SleConnStatusChangesRow) l_lisQueryResult.get(0);
                m_sleConn = l_sleConnStatusChangesRow.getPrimaryKey();
                //�擾����SLE�ڑ���ԃ��R�[�h����L���[ID����ԎႢSLE�ڑ���ԃ��R�[�h�̂ݔ����o���A
                //SLE�ڑ���Ԃ��e0�FSLE�G���W���֐ڑ��������f �A�e3�FSLE�R�l�N�^��~�f�ł���ꍇ�A���J�o���[������s�\�Ƃ��āA
                //���J�o���[�����𒆎~����B
                if ((l_sleConnStatusChangesRow.getNewStatus().equals(SleConnectionStatusEnum.CONNECTION_LOSE) || l_sleConnStatusChangesRow.getNewStatus().equals(
                        SleConnectionStatusEnum.CONNECTION_STOP)))
                {
                    final String l_strMsgErr = "SLE connection is losed or stopped.";
                    m_log.error(l_strMsgErr);
                    l_blnResult = false;
                }
            }
        }
        catch (DataException e)
        {
            m_log.error("Error while searching the status of SLE connection." + e, e);
            l_blnResult = false;
        }

        m_log.exiting(METHOD_IS_RECOVERYAVAILABLE);
        return l_blnResult;
    }

    
    /**
     * ���M�Ώۂ�SEND_Q���b�Z�[�W�𒊏o
     * 
     * @@return List ���M�Ώۂ�SEND_Q���b�Z�[�W���X�g
     * @@throws DataException
     */
    protected List getRows() throws DataException
    {

        m_log.entering(METHOD_GET_ROWS);
        List l_lisResult = new ArrayList();

        try
        {

            // ���M�Ώۂ𒊏o
            final String l_strWhere = "status = ? and confirmed_by_sle_rcvd_q = ? and  account_id >= ? and account_id <= ?";
            final String l_strOrderBy = "queue_id desc";
            final Object[] l_strBindVars = {  SleSendqProcStatusEnum.PROCESSED,
                                          BooleanEnum.FALSE,
                                          Long.valueOf(String.valueOf(m_fromAccountId)),
                                          Long.valueOf(String.valueOf(m_toAccountId))
                                       };

            final QueryProcessor qp = m_wsp.getDefaultProcessor();
            l_lisResult = qp.doFindAllQuery(SleSendQRow.TYPE, l_strWhere, l_strOrderBy, null, l_strBindVars);
        }
        catch (DataException e)
        {
            m_log.error("Error while getting the rows of the send_q."+e, e);
            throw e;
        }

        m_log.exiting(METHOD_GET_ROWS);
        return l_lisResult;
    }
    
    /**
     * ��s�ڂ̒������b�Z�[�W�̎s��R�[�h�A�����R�[�h�ɑΉ�����ORDER_BOOK�₢���킹�A�{���ɑ��M�������ǂ����𔻒f����
     * 
     * @@param l_sleSendqRow
     *            ��胁�b�Z�[�W
     * @@throws DataException
     */
    protected void processRow(final SleSendQRow l_sleSendqRow)
    {
        m_log.entering(METHOD_PROCESS_ROW);
        try
        {
            final QueryProcessor qp = m_wsp.getDefaultProcessor();

            qp.doTransaction(QueryProcessor.TX_CREATE_NEW, new TransactionCallback()
            {

                public Object process() throws DataNetworkException, DataCallbackException, DataQueryException
                {
                    SleSendQRow l_lockedSleSendQRow = (SleSendQRow)qp.doFindByPrimaryKeyQuery(l_sleSendqRow.getPrimaryKey(),"for update nowait");
                    
                    //�������b�Z�[�W�̖����R�[�h�ɑΉ����鏈���t���O���`�F�b�N���A�eON�f�ł���ꍇ�A�ȍ~�̏������X�L�b�v����B
                    if (m_recoveryCheckDoneProductCodesMap.get(l_lockedSleSendQRow.getProductCode()) != null)
                    {
                        return null;
                    }

                    //ORDER_BOOK�₢���킹���ʂ��擾����B
                    Map l_hmProduct = (Map) m_productsOrderBookMap.get(l_lockedSleSendQRow.getProductCode());//�L���b�V������擾
                    if (l_hmProduct == null)
                    {
                        l_hmProduct = WEB3SleOrderBookStatusChecker.getInstance().getOrderBook(l_sleSendqRow.getMarketCode(), l_lockedSleSendQRow
                                .getProductCode());//�L���b�V������擾�ł��Ȃ��ꍇ�AORDER_BOOK�₢���킹�֖₢���킹
                        m_productsOrderBookMap.put(l_lockedSleSendQRow.getProductCode(), l_hmProduct);//ORDER_BOOK�擾�������ʂ��L���b�V���ɕۑ�
                    }

                    //�������b�Z�[�W�ɑΉ�����d���𐳂���SLE�֑��M�����ۂ����f����B
                    final boolean l_blnIsSended = WEB3SleOrderBookStatusChecker.getInstance().isAlreadySent(l_lockedSleSendQRow, l_hmProduct);
                    if (l_blnIsSended == true)
                    {
                        m_recoveryCheckDoneProductCodesMap.put(l_lockedSleSendQRow.getProductCode(), "ON");//�Ή���������R�[�h�̏����t���O���eON�f�ɂ���B
                    }
                    else
                    {
                       recoverySleSendqStatus(l_lockedSleSendQRow);//���J�o���[����
                    }
                    
                    return null;

                }
            });
        }
        catch (Exception e)
        {
            m_log.error("Error while Processing the row." + l_sleSendqRow, e);
        }

        m_log.exiting(METHOD_PROCESS_ROW);
    }
    
    /**
     * SLE�֐��������M����Ȃ������������b�Z�[�W�ɑ΂������敪���e�����҂�(0)�f�ɕύX����B (�{���̃��J�o���[�v���Z�X)
     * 
     * @@param l_sleSendqRow
     *            ��������s
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataException
     */
    protected void recoverySleSendqStatus(final SleSendQRow l_sleSendqRow) throws DataQueryException,
            DataNetworkException
    {
        m_log.entering(METHOD_RECOVERY_SLE_SENDQ_STATUS);

        final QueryProcessor qp = m_wsp.getDefaultProcessor();

        //�V�����������w��
        Map l_hmParam = new HashMap();
        l_hmParam.put("status", SleSendqProcStatusEnum.TODO);
        l_hmParam.put("last_updated_timestamp", new Timestamp(System.currentTimeMillis()));

        //�X�V����
        qp.doUpdateQuery(l_sleSendqRow.getPrimaryKey(), l_hmParam);

        m_log.exiting(METHOD_RECOVERY_SLE_SENDQ_STATUS);
    }    
    
    
    /**
     * SLE�ڑ���ԏ��̏����敪���e�����ς�(1)�f�Ƃ���B isRecoveryAvailable���\�b�h�擾�������R�[�h�ɂ������āB�ł�����A
     * �K��isRecoveryAvailable���\�b�h�����s������A���̃��\�b�h���Ăяo���Ă�������
     * 
     * @@throws DataException
     */
    private void updateSleConnStatus() throws DataException //�{���̐錾
    {
        m_log.entering(METHOD_UPDATE_SLECONNSTATUS);

        try
        {
            if (m_sleConn != null)
            {
                final QueryProcessor qp = m_wsp.getDefaultProcessor();

                //�V�����������w��
                Map l_hmParam = new HashMap();
                l_hmParam.put("proc_status", new Integer(SleConnStatusChangesDbConstants.PROC_STATUS.PROCESSED));
                l_hmParam.put("last_updated_timestamp", new Timestamp(System.currentTimeMillis()));
                
                //�X�V����
                qp.doUpdateQuery(m_sleConn, l_hmParam);
            }
        }
        catch (DataException e)
        {
            m_log.error("Error while updating the status of SLE connection." + e, e);
            throw e;
        }

        m_log.exiting(METHOD_UPDATE_SLECONNSTATUS);

    }

}
@
