head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSendqProcessorServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleSendqProcessorServiceImpl�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 �� �V�K�쐬
 Revision History : 2006/05/31 ��(FLJ) �ʐM�G���[�n���h�������Ή�
 Revision History : 2006/06/98 ��(FLJ) �\�[�X����
                    2006/09/04 ��(FLJ) �s���ԂɊւ��鏈�����폜 
 */
package webbroker3.slegateway.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.slebase.data.SleSendQErrorsParams;
import webbroker3.slebase.data.SleSendQErrorsPK;
import webbroker3.slebase.enums.SleOpenStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.utils.SleConnStatusChangesDbConstants;
import webbroker3.slegateway.WEB3SleConnectorClientFactory;
import webbroker3.slegateway.define.WEB3SleMarketAdapterErrorMessageDef;
import webbroker3.slegateway.WEB3SleMarketAdapterUtils;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorService;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientException;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientInitialException;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientRequestSendStatusUnknownException;

/**
 * �w�肵��SEND_Q�������b�Z�[�W�𑗐M���邽�߂̃T�[�r�X�N���X
 */
public class WEB3SleSendqProcessorServiceImpl implements WEB3SleSendqProcessorService
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleSendqProcessorServiceImpl.class);//��2006/10/10 ����
    
    /**
     * �f�o�b�O���O�o�̓t���O
     */
    private static final boolean DBG = m_log.ison();
    
    /**
     * �R���X�g���N�^
     */
    public WEB3SleSendqProcessorServiceImpl(){}

    /**
     * �w�肵��SEND_Q ���b�Z�[�W��GlSleRequest�I�u�W�F�N�g�֕ϊ����A SLE�R�l�N�^�֓]������
     * @@param l_row
     *          SEND_Q ���b�Z�[�W
     * @@param l_wsp
     *          ���M�������Ŏg�p����DB�v���Z�b�T�C���X�^���X
     * @@param l_connectorfactory
     *          ���M�������Ŏg�p����SLE�R�l�N�^�C���X�^���X
     * @@throws DataException
     */
    public boolean processRow(Row l_row,final WEB3SleProcessors l_wsp, final WEB3SleConnectorClientFactory l_connectorfactory) throws DataException
    {

        m_log.entering("processRow(Row) ");
        final QueryProcessor l_qp = l_wsp.getDefaultProcessor();

        final SleSendQRow l_sleSendqRow = (SleSendQRow) l_row;
        
        //�����敪���`�F�b�N �@@0:�����҂� or 7:�����M �ȊO�̏����敪�Ȃ�A�������X�L�b�v���āAreturn����
        if ( (! SleSendqProcStatusEnum.TODO.equals(l_sleSendqRow.getStatus())) && (  ! SleSendqProcStatusEnum.NOT_PROCESSED.equals(l_sleSendqRow.getStatus()) )  )
        {
            final String warnMsg = "there been invalid status for sending sle_send_q message";
            m_log.warn(warnMsg);
            return false;
        }
        
        //�s���t�\�Ȏ��ԑу`�F�b�N
        if (WEB3SleMarketAdapterUtils.isMarketScheduleOnUsed()) //�ˎs�ꑗ�M�X�P�W���[���L���t���O���`�F�b�N
        {
            final boolean l_bIsMarketOnline = WEB3SleMarketAdapterUtils.isOk2SendOrdersOnAvailableMarketTimeZone();
            if ( !l_bIsMarketOnline  ) 
            {
                final String errMsg = WEB3SleMarketAdapterErrorMessageDef.MARKET_STATUS_NOVALID_PERIOD;
                m_log.error(errMsg);
                
                if ( l_sleSendqRow.getStatus().equals(SleSendqProcStatusEnum.TODO))
                {  
                    try
                    {
                        final QueryProcessor l_qp2 = l_wsp.getDefaultProcessor();
        
                
                        //�P�ƃg�����U�N�V������SEND_Q�̏����敪�������Őݒ肷��
                        l_qp2.doTransaction(
                                QueryProcessor.TX_CREATE_NEW,
                                new TransactionCallback()
                        {
                            final SleSendQRow ll_sleSendqRow = l_sleSendqRow;
                            public Object process()
                                throws
                                    DataNetworkException,
                                    DataQueryException,
                                    DataCallbackException {
                                
                                    l_qp2.doFindByPrimaryKeyQuery(ll_sleSendqRow.getPrimaryKey(),null,"FOR UPDATE NOWAIT",null);
                                    setToSleSendqUnprocess(l_sleSendqRow,SleSendqProcStatusEnum.NOT_PROCESSED,l_wsp);
                            
                                    return null;
        
                            }
                        });
                    }
                    catch (DataQueryException dqe)
                    {
                
                        m_log.warn("Could not acquire lock on sle_send_q row:" + l_sleSendqRow);
                        m_log.exiting("processRow(Row) ");
                        return false;
                    }
                }
                m_log.exiting("processRow(Row) ");
        
                return false;
            }      
            
        }
       

        /*
         SLE�R�l�N�^��SLE�����G���W���Ԃ̐ڑ���Ԃ����M�\�ȏ�Ԃł��邩�`�F�b�N����B
         ���uSLE�ڑ���ԊǗ��e�[�u���v�𒲂ׁA�eSLE�G���W���֐ڑ������f�܂��́eSLE�R�l�N�^��~�f�ł���ꍇ�A
         ���L�̃G���[���b�Z�[�W�����O�ɏo�͂��ASEND_Q�̒������b�Z�[�W�̏����敪�X�e�[�^�X�f���e���M�G���[�t���O�f�ɍX�V���邱�ƁB
         �gSLE�R�l�N�^����~�܂��͐ڑ������̂��߁A���M���s�ł����B�h
         */
        if (
            (!WEB3SleMarketAdapterUtils.isSleConnectorOK(l_sleSendqRow,SleConnStatusChangesDbConstants.CONN_DIV.ACTIVE_CONN))
            && (!WEB3SleMarketAdapterUtils.isSleConnectorOK(l_sleSendqRow,SleConnStatusChangesDbConstants.CONN_DIV.STANDBY_CONN))
        )   
        {
            final String errMsg =
                WEB3SleMarketAdapterErrorMessageDef.SLE_ADAPTER_STOP;
            m_log.error(errMsg);
            
            if ( l_sleSendqRow.getStatus().equals(SleSendqProcStatusEnum.NOT_PROCESSED)) 
            {
            
                try
                {
                    final QueryProcessor l_qp2 = l_wsp.getDefaultProcessor();
        
                    //�P�ƃg�����U�N�V������SEND_Q�̏����敪�������Őݒ肷��
                    l_qp2.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            new TransactionCallback()
                    {
                        final SleSendQRow ll_sleSendqRow = l_sleSendqRow;
                        public Object process()
                            throws
                                DataNetworkException,
                                DataQueryException,
                                DataCallbackException {
                                
                                l_qp2.doFindByPrimaryKeyQuery(ll_sleSendqRow.getPrimaryKey(),"status = ?","FOR UPDATE NOWAIT",new Object[]{SleSendqProcStatusEnum.NOT_PROCESSED});
                                setToSleSendqUnprocess(l_sleSendqRow,SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,l_wsp);
                            
                                return null;
        
                        }
                    });
            
                }
                catch (DataQueryException dqe)
                {
                
                    m_log.warn("Could not acquire lock on sle_send_q row:" + l_sleSendqRow);
                    m_log.exiting("processRow(Row) ");
                    return false;
                }
            }      
            else
            {
                setToSleSendqUnprocess(l_sleSendqRow,SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,l_wsp);
            }
            m_log.exiting("processRow(Row) ");
            return false;
        }

        try {
            Boolean procStatus =(
                Boolean) l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW,new TransactionCallback() 
                {
                    public Object process() throws
                        DataNetworkException,
                        DataCallbackException,
                        DataQueryException {

                    SleSendQRow l_rlockedRow;
                    
                    //�X�V�O��SLE_SEND_Q�̏����敪���擾
                    SleSendqProcStatusEnum l_preStatus = l_sleSendqRow.getStatus();

                    try {
                
                        final String where = "0 = (select count(*) from sle_send_q where product_code = ? and account_id = ? and market_code = ? and (status = ? or status = ?) and created_timestamp < ?)";
                        //���M���ő��M���b�Z�[�W�̔��������̏��Ԃ̂Ƃ��著�M���邱�Ƃ�ۏႷ�邽�߁A
                        //���M�Ώۂ�send_q���b�Z�[�W�̔����������ȑO�Ŕ������������M��send_q�L�����`�F�b�N����K�v������ 2006/10/31
                        //final String where = "(status = ? or status = ?)";//'�����M'�����敪�̍i�������ǉ� �� 2006/10/20
                        final Object[] bvs = { 
                                                l_sleSendqRow.getProductCode(),//add at 2006/10/31
                                                new Long(l_sleSendqRow.getAccountId()),//add at 2006/10/31
                                                l_sleSendqRow.getMarketCode(),//2007/10/23 �[�Z���s��Ή�
                                                SleSendqProcStatusEnum.TODO, 
                                                SleSendqProcStatusEnum.NOT_PROCESSED,//added at 2006/10/20
                                                l_sleSendqRow.getCreatedTimestamp(),//added at 2006/10/31
                                                };
                        l_rlockedRow =
                            (SleSendQRow) l_qp.doFindByPrimaryKeyQuery(
                                l_sleSendqRow.getPrimaryKey(),
                                where,
                                null,//lock�K�v���Ȃ� 2007/11/7
                                bvs);

                        if ( SleSendqProcStatusEnum.TODO.equals(l_rlockedRow.getStatus()) || SleSendqProcStatusEnum.NOT_PROCESSED.equals(l_rlockedRow.getStatus()) )//'�����M'�����敪�̍i�������ǉ� �� 2006/10/20
                        {
                            ;
                        }
                        else
                        {
                            throw new DataCallbackException("Already processed,hence skip.");
                        }
                    } catch (DataFindException dfe) {
                        if (DBG)
                        {
                            m_log.debug(
                                "Could not acquire lock on sle_send_q row:"
                                    + l_sleSendqRow,dfe);
                        }
                        throw new DataCallbackException("Could not lock the row");
                    } catch (DataQueryException dqe) {
                        if (DBG)
                        {
                            m_log.debug(
                                "Could not acquire lock on sle_send_q row:"
                                    + l_sleSendqRow,dqe);
                        }
                        throw new DataCallbackException("Could not lock the row.");
                    }

                    try 
                    {                        
                        //�X�e�[�^�X�𑗐M�OPrepare��ԂɍX�V�@@AP�_�E������Q��Ή�����ɂ͏����ǉ� 2007/11/7
                        //�R�[���o�b�N����Ȃ��悤�A�Ǝ��̃g�����U�N�V�����Ƃ����B
                        try{
                            setToSleSendqUnprocessByTransaction(
                                l_rlockedRow,
                                SleSendqProcStatusEnum.PREPARE_PROCESSED,
                                l_wsp,
                                true);
                        }
                        catch (DataQueryException dce) 
                        {
                             m_log.exiting("processRow(Row) ");                            
                             return Boolean.FALSE;                             
                        }
                           
                        // SLE�֑��M
                        WEB3SleRequestSender.send(
                            l_rlockedRow,
                        l_connectorfactory);

                        /*
                          SEND_Q�̒������b�Z�[�W�����������ł���ꍇ
                          �Ή����钍���ɑ΂��O���������P�ʃe�[�u���́f������ԁf���f�������f�ɍX�V����B
                          DataException�G��-�������鎞�ŁA�S�̏������~���邱��
                         */
                        setToSleOrderUnitOrderStatus(l_rlockedRow,l_wsp);

                        //�X�e�[�^�X�𑗐M�ς݂ɍX�V
                        setToSleSendqUnprocess(
                            l_rlockedRow,
                            SleSendqProcStatusEnum.PROCESSED,
                            l_wsp);

                        m_log.exiting("processRow(Row) ");

                        return Boolean.TRUE;
                    }  
                    catch (GlSleConnectorClientRequestSendStatusUnknownException sendStatusUnknownEx)
                    {
                        //SLE�R�l�N�^�T�[�o�֑��M���鎞�Ŗ����G���[������
                        final String errMsg =
                            "Unknown Error while sending to SLE. Will auto-check and retry later";
                        m_log.error(errMsg, sendStatusUnknownEx);
                        
                        
                        //SLE Unknown�G���[���A�����敪��0:TODO�֖߂�A�@@AP�_�E������Q��Ή�����ɂ͏����ǉ� 2007/11/7
                        setToSleSendqUnprocess(
                            l_rlockedRow,
                            l_preStatus,
                            l_wsp);
                        
                        /*
                         ���ORDER_BOOK�֖₢���킹�A���������M�����ۂ��m�F����K�v������̂�
                         �L���[ID����sle_send_q_errors�ɕێ����Ēu��
                        */
                        addToSleSendqErrors(
                            l_rlockedRow,
                            l_wsp);
                        
                        m_log.exiting("processRow(Row)");
                        
                        return Boolean.FALSE;
                    } 
                    catch (GlSleConnectorClientException sleConnEx)
                    {
                        
                        try{
                            //�đ�
                            retrySendQ(l_rlockedRow,l_connectorfactory,l_wsp);
                            m_log.exiting("processRow(Row) ");
                            return Boolean.TRUE;
                        }
                        catch(GlSleConnectorClientRequestSendStatusUnknownException unknownge)
                        {
                            //SLE�R�l�N�^�T�[�o�֑��M���鎞�Ŗ����G���[������
                            final String errMsg =
                                "Unknown Error while sending to SLE. Will auto-check and retry later";
                            m_log.error(errMsg, unknownge);
                        
                            //SLE Unknown�G���[���A�����敪��0:TODO�֖߂�A�@@AP�_�E������Q��Ή�����ɂ͏����ǉ� 2007/11/7
                            setToSleSendqUnprocess(
                                l_rlockedRow,
                                l_preStatus,
                                l_wsp);

                            addToSleSendqErrors(
                                l_rlockedRow,
                                l_wsp);
                        
                            m_log.exiting("processRow(Row)");
                        
                            return Boolean.FALSE;       
                        }
                        catch (GlSleConnectorClientInitialException initialConnectEx)
                        {
                            //SLE�R�l�N�^�֑��M���Ă��Ȃ����
                            final String errMsg =
                                "Initial connection with SLE Connector failed. ";
                            m_log.error(errMsg, initialConnectEx);
                            //�����敪�X�e�[�^�X'9'�ɂ��āA�Ǘ���ʂ���đ�����
                            setToSleSendqUnprocess(
                                l_rlockedRow,
                                SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,
                                l_wsp);
                            m_log.exiting("processRow(Row) ");
                        
                            return Boolean.FALSE;
                        }
                        catch(GlSleConnectorClientException ge)
                        {
                            //���M�O��SLE�R�l�N�^�N���C�A���g���ł̃G���[(ex:MFD�ϊ��G���[)
                            final String errMsg =
                                "Some kind of client side exception before attempting to send to the SLE Connector. ";
                            m_log.error(errMsg, sleConnEx);
                            //�����敪�X�e�[�^�X'9'�ɂ��āA�Ǘ���ʂ���đ�����
                            setToSleSendqUnprocess(
                                l_rlockedRow,
                                SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,
                                l_wsp);
                        
                            m_log.exiting("processRow(Row) ");
                            return Boolean.FALSE;
                        }
                    }
                    catch (Exception ex) {
                        /*
                         ���̃G���[(DB�G���[�Ȃ�)
                         SLE�R�l�N�^�ւ̑��M�����𒆎~��
                         (���������Ȃ̂ŊԈ���đ�ʂɔ������Ă��܂��̂͑�ϊ댯�Ȃ̂�)
                         SEND_Q�������b�Z�[�W�̃X�e�[�^�X��'���M�G���[�t���O(9)�f�ɂ�
                         ��Ŏ蓮�Œ������b�Z�[�W���m�F��
                         ���[���Ȃǂő��M����
                         set the circuit breaker (stop sendq threads)
                         Circuit Breaker �����s��
                         �S�Ă̎��s����SEND_Q�X���b�h���~����
                        */
                        enableSleSendqThreadsCircuitBreaker(l_rlockedRow.getMarketCode(), ex);
                        final String errMSg =
                            "Some kind of exception (for example:DB error) while processing sle_send_q row:we have to interrupte the whole send_q process: "
                                + l_rlockedRow;
                        m_log.error(errMSg, ex);
                        
                        setToSleSendqUnprocess(
                            l_rlockedRow,
                            SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,
                            l_wsp);
                            
                        m_log.exiting("processRow(Row) ");
                        
                        return Boolean.FALSE;
                    }
                }
            });

            if (Boolean.FALSE.equals(procStatus))
            {
                //SLE�R�l�N�^�ɂ́A�G���[�܂��ُ͈��Ԃ�
                //���M���~���A��Ԃɂ���Ă��̌ナ�g���C����B
                m_log.exiting("processRow(Row) ");
                return false;
            }

        }catch(DataCallbackException cde){
            ;
            m_log.error("this is a datacallbackexception happened!");
            
            return false;
        }catch (DataException de){
            /**
             �s��֑�ʖ��������𔭍s����̂�h�~���邽��
             ��ʏ����֗�O���X���[���A��ʏ������瑗�M�𒆎~����
             */
            m_log.error(de.getMessage(), de);
            throw new RuntimeException(de.getMessage(), de);
        }catch (Exception e){
            /**
             �s��֑�ʖ��������𔭍s����̂�h�~���邽��
             ��ʏ����֗�O���X���[���A��ʏ������瑗�M�𒆎~����
             */
            m_log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

        m_log.exiting("processRow(Row) ");
        return true;
    }

    /**
     * SEND_Q�������b�Z�[�W�̏����敪�X�e�[�^�X���X�V
     * 
     * @@param sleSendqRow
     *     SEND_Q�������b�Z�[�W
     * @@param status
     *     �w�肵�������敪�X�e�[�^�X
     * @@param l_wsp
     *     DB�v���Z�b�T
     * @@param b_forceFlag
     *     ����Update���s�t���O
     */
    private void setToSleSendqUnprocess(
        SleSendQRow l_sleSendqRow,
        SleSendqProcStatusEnum l_status,
        WEB3SleProcessors l_wsp)
    {

        final String STR_METHOD_NAME =
            "setToSleSendqUnprocess(SleSendQRow,SleSendqProcStatusEnum,WEB3SleProcessors)";
        m_log.entering(STR_METHOD_NAME);

        try {
            final QueryProcessor l_qp = l_wsp.getDefaultProcessor();
            final Map l_hmChange = new HashMap();
            l_hmChange.put("status", l_status);
            final Timestamp now = GtlUtils.getSystemTimestamp();
            l_hmChange.put("last_updated_timestamp", now);
            //�X�e�[�^�X�X�V
            l_qp.doUpdateQuery(l_sleSendqRow.getPrimaryKey(), l_hmChange);

        } catch (DataException de) {

            final String msg =
                "Exception while set a sle_send_q message as not processed message:"
                    + l_sleSendqRow.getQueueId();
            m_log.error(msg, de);
            /*
             �s��֑�ʖ��������𔭍s����̂�h�~���邽��
             ���M�����������~����
             */
            throw new RuntimeException(de.getMessage(), de);

        }
        m_log.exiting(STR_METHOD_NAME);

    }

    /**
     * SLE�֐��������M�����ۂ��m�F�Ώۂ�SEND_Q�������b�Z�[�W�̃L���[ID��sle_send_q_errors�ɓ����
     * @@param l_rSendqRow
     *         SLE�֐��������M�����ۂ��m�F�Ώۂ�SEND_Q�������b�Z�[�WRow�I�u�W�F�N�g
     */
    private void addToSleSendqErrors(SleSendQRow l_rSendqRow,
                                    final WEB3SleProcessors l_wsp)
    {

        final String STR_METHOD_NAME = "addToSleSendqErrors(SleSendQRow,WEB3SleProcessors)";
        m_log.entering(STR_METHOD_NAME);

        try {
            final QueryProcessor l_qp = l_wsp.getDefaultProcessor();

            final Map l_hmChanges = new HashMap();
            final Timestamp now = GtlUtils.getSystemTimestamp();
            l_hmChanges.put("open_status", SleOpenStatusEnum.OPEN);
            l_hmChanges.put(
                "last_updated_timestamp",
                GtlUtils.getSystemTimestamp());
            //DB�d�l�ύX�̂��߁A���G���[�L���[�ɒ���ID��ǉ�(2006/9/15 FTL��)
            l_hmChanges.put("order_id",new Long(l_rSendqRow.getOrderId()));
            //�A�b�v�f�[�^
            final int l_intUpdateCount =
                l_qp.doUpdateQuery(new SleSendQErrorsPK(l_rSendqRow.getQueueId()), l_hmChanges);

            //���ɑ��݂��Ȃ��ꍇ�A�C���T�[�g
            if (l_intUpdateCount == 0)
            {
                final SleSendQErrorsParams l_errParams =
                    new SleSendQErrorsParams();
                l_errParams.setQueueId(l_rSendqRow.getQueueId());
                //DB�d�l�ύX�̂��߁A���G���[�L���[�ɒ���ID��ǉ�(2006/9/15 FTL��)
                l_errParams.setOrderId(l_rSendqRow.getOrderId());
                l_errParams.setOpenStatus(SleOpenStatusEnum.OPEN);
                l_errParams.setCreatedTimestamp(now);
                l_errParams.setLastUpdatedTimestamp(now);
                // sle_send_q_errors�ɃC���T�[�g
                l_qp.doInsertQuery(l_errParams);
            }
        }catch (DataException de) {
            final String msg =
                "Exception while adding a row to sle_send_q_errors table with queue_id:"
                    + l_rSendqRow.getQueueId();
            m_log.error(msg, de);
            /*
             �s��֑�ʖ��������𔭍s����̂�h�~���邽��
             ���M�����������~����
             */
            throw new RuntimeException(de.getMessage(), de);
        }
        m_log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �g�����U�N�V������SEND_Q�������b�Z�[�W�̏����敪�X�e�[�^�X���X�V
     * 
     * @@param sleSendqRow
     *     SEND_Q�������b�Z�[�W
     * @@param status
     *     �w�肵�������敪�X�e�[�^�X
     * @@param l_wsp
     *     DB�v���Z�b�T�C���X�^���X
     * @@param l_isNewTransaction
     *     �g�����U�N�V������V�K�ō쐬���邩
     * 
     */
    private void setToSleSendqUnprocessByTransaction(
        SleSendQRow l_sleSendqRow,
        SleSendqProcStatusEnum l_status,
        WEB3SleProcessors l_wsp,
        boolean l_isNewTransaction) throws DataQueryException
    {

        final String STR_METHOD_NAME =
            "setToSleSendqUnprocessByTransaction(SleSendQRow,SleSendqProcStatusEnum,WEB3SleProcessors,boolean)";
        m_log.entering(STR_METHOD_NAME);
        
        
        final SleSendqProcStatusEnum  ll_status = l_status;
        final SleSendQRow ll_sleSendqRow = l_sleSendqRow;
        
        try
        {
            final QueryProcessor l_qp = l_wsp.getDefaultProcessor();
        
            if (l_isNewTransaction){    
            
                //�P�ƃg�����U�N�V������SEND_Q�̏����敪�������Őݒ肷��
                l_qp.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        new TransactionCallback()
                {
                    public Object process()
                        throws
                            DataNetworkException,
                            DataQueryException,
                            DataCallbackException {
                                
                            l_qp.doFindByPrimaryKeyQuery(
                                          ll_sleSendqRow.getPrimaryKey(),
                                          null,
                                          "FOR UPDATE NOWAIT",
                                          null);
                            
                            final Map l_hmChange = new HashMap();
                            l_hmChange.put("status", ll_status);
                            final Timestamp now = GtlUtils.getSystemTimestamp();
                            l_hmChange.put("last_updated_timestamp", now);
                            //�X�e�[�^�X�X�V
                            l_qp.doUpdateQuery(ll_sleSendqRow.getPrimaryKey(), l_hmChange);             
                            return null;
        
                    }
                });
            } 
            else
            {       
                final Map l_hmChange = new HashMap();
                l_hmChange.put("status", l_status);
                final Timestamp now = GtlUtils.getSystemTimestamp();
                l_hmChange.put("last_updated_timestamp", now);
                //�X�e�[�^�X�X�V
                l_qp.doUpdateQuery(l_sleSendqRow.getPrimaryKey(), l_hmChange);
        
            }
        
        }
        catch (DataQueryException dqe)
        {
            
            m_log.warn(
            "Could not acquire lock on sle_send_q row:"
                + ll_sleSendqRow);

            throw dqe;
        }        
        catch (DataException de)
        {

            final String msg =
                "Exception while set a sle_send_q message as not processed message:"
                    + l_sleSendqRow.getQueueId();
            m_log.error(msg, de);
            m_log.exiting(STR_METHOD_NAME);
            /*
                �s��֑�ʖ��������𔭍s����̂�h�~���邽��
                ���M�����������~����
             */
            throw new RuntimeException(de.getMessage(), de);
            
        }
        
        m_log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���M����SEND_Q�̒������b�Z�[�W�̒�����Ԃ�'������'�ɍX�V����
     * ���V�i���I�e�X�g���Ŕ���������Q��Ή����邽�ߍX�V 2006/12/1
     * @@param slesendqRow SEND_Q�������b�Z�[�W
     */
    private void setToSleOrderUnitOrderStatus(SleSendQRow l_slesendqRow,
                                            final WEB3SleProcessors l_wsp)
    {

        final String STR_METHOD_NAME = "setToSleOrderUnitOrderStatus(long,WEB3SleProcessors)";
        m_log.entering(STR_METHOD_NAME);

        //�����P��ID
        final long l_lngOrderUnitId = l_slesendqRow.getOrderUnitId();
        //�I�y���[�^�^�C�v
        final SleSendqOpTypeEnum l_optype = l_slesendqRow.getOpType();
        //�����敪
        final SleSendqProcStatusEnum l_proctype = l_slesendqRow.getStatus();
        //�����P��Row
        FeqOrderUnitRow l_row = null;

        try {
            final QueryProcessor l_qp = l_wsp.getDefaultProcessor();

            //�����P�ʏ����擾             
            l_row =
                (FeqOrderUnitRow) l_qp.doFindByPrimaryKeyQuery(
                    new FeqOrderUnitPK(l_lngOrderUnitId));
            OrderStatusEnum l_orderstatus = l_row.getOrderStatus();

            m_log.debug("*** �����P�� �I�u�W�F�N�g **** = " + l_row);
            m_log.debug("*** �����P�� *** ������� = " + l_orderstatus);
            m_log.debug("*** SEND_Q *** �����敪 = " + l_proctype);
            m_log.debug("*** SEND_Q *** �I�y���[�^�^�C�v = " + l_optype);


            final Map change = new HashMap();

            //�V�K�����̏ꍇ
            if (l_optype.equals(SleSendqOpTypeEnum.NEW_ORDER))
            {
                if (l_orderstatus.equals(OrderStatusEnum.ACCEPTED))
                {
                    change.put("order_status", OrderStatusEnum.ORDERING);
                    change.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_qp.doUpdateQuery(new FeqOrderUnitPK(l_lngOrderUnitId), change);
                    
                }
                //�ǎ��ԑт̒������� �ǎ��ԑт̒���������V�K�����Ƃ��ď��������悤
                //���C�� 2006/12/26
                else if (l_orderstatus.equals(OrderStatusEnum.MODIFIED))
                {
                    change.put("order_status", OrderStatusEnum.ORDERING);
                    change.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_qp.doUpdateQuery(
                    new FeqOrderUnitPK(l_lngOrderUnitId),
                    change);
                }
            }
            //�X�V�����̏ꍇ
            else if (l_optype.equals(SleSendqOpTypeEnum.CHANGE_ORDER))
            {
                //�J�ǎ��ԑт̍X�V����
                if (l_orderstatus.equals(OrderStatusEnum.MODIFY_ACCEPTED))
                {
                    change.put("order_status", OrderStatusEnum.MODIFYING);
                    change.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_qp.doUpdateQuery(
                        new FeqOrderUnitPK(l_lngOrderUnitId),
                        change);
                }
            }
            //���������
            else
            {
                if (l_orderstatus.equals(OrderStatusEnum.CANCEL_ACCEPTED))
                {
                    change.put("order_status", OrderStatusEnum.CANCELLING);
                    change.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_qp.doUpdateQuery(new FeqOrderUnitPK(l_lngOrderUnitId), change);
                }
            }
        }catch (DataException de){
            final String msg =
                "Exception while updatting a row to feq_order_unit table with order_unit_id:"
                    + l_lngOrderUnitId;
            m_log.error(msg, de);
            /*
             �s��֑�ʖ��������𔭍s����̂�h�~���邽��
             ���M�����������~����
            */
            throw new RuntimeException(de.getMessage(), de);
        }
        m_log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 1�x���M���s��SEND_Q���b�Z�[�W���đ�����
     * @@param l_slesendqRow �đ�����SEND_Q���b�Z�[�W
     * @@param l_connectorfactory �đ��pSLE�R�l�N�^�t�@@�N�g�� 
     * @@param l_wsp DB�v���Z�b�T
     */
    
    private void retrySendQ(SleSendQRow l_slesendqRow,final WEB3SleConnectorClientFactory l_connectorfactory,final WEB3SleProcessors l_wsp) throws GlSleConnectorClientException
    {
        final String STR_METHOD_NAME =
            "retrySendQ(SleSendQRow,WEB3SleConnectorClientFactory,WEB3SleProcessors)";
        m_log.entering(STR_METHOD_NAME);
        
        //SLE�֍đ��M
        WEB3SleRequestSender.send(l_slesendqRow,l_connectorfactory);
        
        //�����X�e�[�^�X�X�V
        setToSleOrderUnitOrderStatus(l_slesendqRow,l_wsp);

        //�X�e�[�^�X�𑗐M�ς݂ɍX�V
        setToSleSendqUnprocess(l_slesendqRow,SleSendqProcStatusEnum.PROCESSED,l_wsp);
        
        m_log.exiting(STR_METHOD_NAME);
        
        return;
    }
    
    /**
     * Circuit Breaker �����s���A�s���G���[�̔������鎞�̒������M���~����B
     * 
     * @@param Circuit Breaker�𔭐����錴����\����O
     */
    private void enableSleSendqThreadsCircuitBreaker(final String marketCode, final Throwable reason)
    {
        m_log.entering("enableSleSendqThreadsCircuitBreaker(Throwable)");

        m_log.error(
            "*********** Unexpected situation. Dont know how to handle. Will try to stop the sle_send_q threads as a precautionary measure.Reason:"
                + reason,
            reason);
        WEB3SleMarketAdapterUtils.setSystemPreferencesStatus(marketCode, false);
        
        m_log.exiting("enableSleSendqThreadsCircuitBreaker(Throwable)");
    }

    /* (�� Javadoc)
     * @@see webbroker3.common.service.delegate.WEB3BusinessService#execute(webbroker3.common.message.WEB3GenRequest)
     */
    public WEB3GenResponse execute(WEB3GenRequest arg0) throws WEB3BaseException
    {
        return null;
    }

}@
