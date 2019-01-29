head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3RecoveryOrdersAtConnectionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RecoveryOrdersAtConnectionCallback( WEB3RecoveryOrdersAtConnectionCallback.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/1/30 ���@@�j(FLJ) �V�K�쐬
 Revesion History : 2009/6/10 ���@@�j(FLJ) �X�e�[�^�X = 9:���M�G���[��SEND_Q���b�Z�[�W�̕��������̌��������ɂ́ABizDate�̍i�������ǉ�
 */

package webbroker3.slegateway.callback;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.sql.SQLException;
import org.apache.log4j.Category;
import com.fitechlabs.xbconnector.glbase.gldata.GlData;
import com.fitechlabs.xbconnector.glbase.admin.objs.ResultIterator;
import com.fitechlabs.xbconnector.sleconnector.impl.WEB3GlConnectorServer;
import webbroker3.slegateway.define.WEB3SleCallbackConstantsDef;


/**
 * SLE�R�l�N�V�������ă����N���ł�SEND_Q�������b�Z�[�W���J�o���[����
 * @@author FTL.Lee Han
 */
public class WEB3RecoveryOrdersAtConnectionCallback 
{
    
    /**
     * ���O�o�̓I�u�W�F�N�g
     */
    private static final Category m_log =  Category.getInstance( WEB3RecoveryOrdersAtConnectionCallback.class);


    
    /**
     * ������ ORDER_BOOK ����ۑ����Ă���
     */
    private Map m_productsOrderBookMap = new HashMap();
    
    /**
     * �����R�[�h�ɑΉ����鏈���t���O
     */
    private Map m_recoveryCheckDoneProductCodesMap = new HashMap(); 
    
    /**
     * BizDateProvider
     */
    private WEB3CallbackBizDateProviderUtils m_DateProvider;
   
    /**
     * DB�A�N�Z�T
     */
    private WEB3CallBackDataAccessUtil m_web3CallBackDataAccessUtil;
    
    /**
     * SYSTEM_PREFERENCES�ݒ��ێ�����Adaptor
     */
    private WEB3CallbackSystemPreferencesAdaptorUtils m_adaptor;
    
    /**
     * �V���O���g���C���X�^���X 
     */
    private static WEB3RecoveryOrdersAtConnectionCallback m_recoverier = null;
    
    /**
     * �R���X�g���N�^
     */
    private WEB3RecoveryOrdersAtConnectionCallback()
    {    
    }
    
    /**
     * �R���X�g���N�^
     * @@param l_web3CallBackDataAccessUtil DB�A�N�Z�T
     * @@param l_DateProvider BizDataProvider
     */
    
    private WEB3RecoveryOrdersAtConnectionCallback(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil,WEB3CallbackSystemPreferencesAdaptorUtils l_adaptor,WEB3CallbackBizDateProviderUtils l_DateProvider)
    {
        m_web3CallBackDataAccessUtil = l_web3CallBackDataAccessUtil;
        m_DateProvider = l_DateProvider;
        m_adaptor= l_adaptor;
    }
    
    /**
     * �V���O���g���C���X�^���X���擾
     * @@param l_web3CallBackDataAccessUtil DB�A�N�Z�T
     * @@param l_DateProvider BizDataProvider
     * @@return �V���O���g���C���X�^���X��Ԃ�
     */
    public static WEB3RecoveryOrdersAtConnectionCallback getInstance(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil,WEB3CallbackSystemPreferencesAdaptorUtils l_adaptor,WEB3CallbackBizDateProviderUtils l_DateProvider)
    {
        if (m_recoverier == null)
        { 
            m_recoverier = new WEB3RecoveryOrdersAtConnectionCallback(l_web3CallBackDataAccessUtil,l_adaptor,l_DateProvider);
        }
        return m_recoverier;
    }
   
    
    /**
     * SLE�R�l�N�V�������ă����N����SEND_Q�������b�Z�[�W���J�o���[���C������
     * @@param l_sleconnQId SLE�ڑ���ԊǗ��e�[�u��
     * @@param s_marketCode SLE�R�l�N�^�Ή��s��R�[�h
     * @@return void
     * @@throws RuntimeException SQL��O�������ŁARuntimeException��O���X���[�����
     */
    public void doRecoveryOrdersAtConnectorCallback(final String s_marketCode,final long l_queid)
    {
        m_log.info("||||| Starting orders message recovery processing while SLE connect status changed with ID: " + s_marketCode );
        
        try
        {
//            // ������ORDER_BOOK����ێ����郁�����̈���N�[���A����
//            if ( m_productsOrderBookMap != null)m_productsOrderBookMap.clear();
//            // ������ORDER_BOOK�����t���O��ێ����郁�����̈���N�[���A����
//            if (m_recoveryCheckDoneProductCodesMap != null)m_recoveryCheckDoneProductCodesMap.clear();
//            Hash���������N���A�[���鏈�����Ăь����Ɉړ]
            
            m_web3CallBackDataAccessUtil.setAutoCommit(false);
            
            //SEND_Q�ɑ��݂�����M���m�F�����M�ς݂̒������b�Z�[�W���擾����
            final List l_rows = getRows(s_marketCode);
            final int i_size = l_rows.size();
            if ( i_size == 0)
            {
                //���J�o���[�ΏۊO��9:�����G���[��SEND_Q���b�Z�[�W�̏����敪�� 9��0�ɍX�V
                int i_updCount = updateSleSendqStatus(s_marketCode);
                m_log.info(i_updCount + "records of send_q messages have been updated status without recovery while reconnection or re-start.");
                m_web3CallBackDataAccessUtil.commit();
                return;
            }

            //�擾��������M�m�F�����M�ς݂̒������b�Z�[�W�ɑ΂��A�P���������b�Z�[�W���������J��Ԃ��B
            for (int i = 0; i < i_size; i++)
            {
                Map map = (HashMap) l_rows.get(i);
                long queue_id = ((BigDecimal)(map.get("QUEUE_ID"))).longValue();
                
                processRow(queue_id);
            }

            //���J�o���[�����̍Ō�ASLE�ڑ���ԏ��̏����敪���e�����ς�(1)�f�Ƃ���B
            updateSleConnStatus(l_queid,s_marketCode);
            
            //���J�o���[�����̍Ō�ASEND_Q�ɑ��݂����S��'9:�����G���['�̒������b�Z�[�W�����敪���@@9��0 �ɕύX
            int i_updCount = updateSleSendqStatus(s_marketCode);
            m_log.info(i_updCount + "records of send_q messages have been updated status after the recovery while reconnection or re-start.");
            
            m_web3CallBackDataAccessUtil.commit();
        }
        catch(SQLException e)
        {
            m_log.error("Error while processing recovery of orders at connection callback:" + e);
            
            try
            {
                m_web3CallBackDataAccessUtil.rollback();
            }
            catch (SQLException ex)
            {
                m_log.error("DB Error while doing rollback at method  WEB3RecoveryOrdersAtConnectionCallback#doRecoveryOrdersAtConnectorCallback()");
            }
            
            final String eMsg = "DB Error while processing recovery of orders at connection callback";
            
            throw new RuntimeException(eMsg, e);
        }
        finally{
            try
            {
                m_web3CallBackDataAccessUtil.setAutoCommit(true);
                m_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        

    }
    
    /**
     * ��s�ڂ̒������b�Z�[�W�̎s��R�[�h�A�����R�[�h�ɑΉ�����ORDER_BOOK�₢���킹�A�{���ɑ��M�������ǂ����𔻒f����
     * 
     * @@param l_sleSendqId SEND_Q�������b�Z�[�W�̃L���[ID
     * @@throws SQLException
     */
    private void processRow(final long l_sleSendqId ) throws SQLException
    {
        m_log.info("processing recovery for order message data" + l_sleSendqId);
        
        String s_Productcode = null;
        String s_Marketcode = null;
        
        final String l_strSql = "select market_code,product_code from sle_send_q where queue_id = ? for update nowait";
    
        List l_lisParams = new ArrayList(1);
        l_lisParams.add( new Long(l_sleSendqId));

        List l_lisQueryResult = new ArrayList(1);
            
        try
        {
            m_web3CallBackDataAccessUtil.setAutoCommit(false);
            
            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
            Map map = (HashMap) l_lisQueryResult.get(0);
            s_Productcode = (String)map.get("PRODUCT_CODE");
            s_Marketcode = (String)map.get("MARKET_CODE"); 
            
            //�������b�Z�[�W�̖����R�[�h�ɑΉ����鏈���t���O���`�F�b�N���A�eON�f�ł���ꍇ�A�ȍ~�̏������X�L�b�v����B
            Boolean b_recoveryCheckDone = (Boolean)m_recoveryCheckDoneProductCodesMap.get(s_Productcode);
            if ((b_recoveryCheckDone != null) && ( b_recoveryCheckDone.equals(Boolean.TRUE) ))
            {
                m_web3CallBackDataAccessUtil.commit();
                m_log.info("the order book flag is closed about product_code :" + s_Productcode);
                return;
            }
            //ORDER_BOOK�₢���킹���ʂ��擾����B
            Map l_hmProduct = (Map) m_productsOrderBookMap.get(s_Productcode);//�L���b�V������擾
            if (l_hmProduct == null) 
            {
                l_hmProduct = getOrderBook(s_Marketcode, s_Productcode);//�L���b�V������擾�ł��Ȃ��ꍇ�AORDER_BOOK�₢���킹�֖₢���킹
                m_productsOrderBookMap.put(s_Productcode, l_hmProduct);//ORDER_BOOK�擾�������ʂ��L���b�V���ɕۑ�
            }
            
            //�������b�Z�[�W�ɑΉ�����d���𐳂���SLE�֑��M�����ۂ����f����B
            final boolean l_blnIsSended = isAlreadySent(l_sleSendqId, l_hmProduct);
            if (l_blnIsSended == true)
            {
                m_recoveryCheckDoneProductCodesMap.put(s_Productcode, Boolean.TRUE);//�Ή���������R�[�h�̏����t���O���eON�f�ɂ���B
            }
            else
            {
               recoverySleSendqStatus(l_sleSendqId);//���J�o���[����
            }
            
            m_web3CallBackDataAccessUtil.commit();
//            m_web3CallBackDataAccessUtil.setAutoCommit(true);
            return;
            
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute processRow() :"
                             + e);
            
            try
            {
                m_web3CallBackDataAccessUtil.rollback();
            }
            catch (SQLException ex)
            {
                m_log.error("DB Error while doing rollback at method  WEB3RecoveryOrdersAtConnectionCallback#processRow()");
            }
            throw e;
        }
        finally
        {
            try
            {
                m_web3CallBackDataAccessUtil.setAutoCommit(true);
                m_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
    }
    
    /**
     * ���J�o���[�Ώۂ�SEND_Q���b�Z�[�W�𒊏o(SEND_Q���b�Z�[�W�������Ԃ̋t��)
     * @@param s_marketCode �s��R�[�h
     * @@return List ���M�Ώۂ�SEND_Q���b�Z�[�W���X�g
     * @@throws SQLException
     */
    private List getRows(final String s_marketCode) throws SQLException
    {

        List l_lisResult = null;
        
    
        final String l_strSql = "select queue_id ,created_timestamp from sle_send_q where status = ? and confirmed_by_sle_rcvd_q = ? and market_code = ? and biz_date = ? order by created_timestamp desc";
        
        List l_lisParams = new ArrayList(1);
        l_lisParams.add(new Integer(1));
        l_lisParams.add(new Integer(0));
        l_lisParams.add(s_marketCode);
        l_lisParams.add(m_DateProvider.getBizDate());

        List l_lisQueryResult = new ArrayList(1);
            
        try
        {
            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
            return l_lisQueryResult;
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute getRows() :"
                             + e);
            throw e;
        }
        finally
        {
            try
            {
                m_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
    
    }
    
    /**
     * SLE�֐��������M����Ȃ������������b�Z�[�W�ɑ΂������敪���e�����҂�(0)�f�ɕύX����B (�{���̃��J�o���[�v���Z�X)
     * @@param l_sleSendqId SEND_Q�L���[ID
     * @@throws SQLException
     */
    protected void recoverySleSendqStatus(final long l_sleSendqId) throws SQLException
    {
        
        final String l_strSql = "update sle_send_q set status = ?,last_updated_timestamp = ?  where queue_id =?";
        List l_lisParams = new ArrayList(3);
        l_lisParams.add(new Integer(0));
        l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));
        l_lisParams.add(new Long(l_sleSendqId));            
            
        m_web3CallBackDataAccessUtil.executeUpdate(l_strSql, l_lisParams);
    
    }

    /**
     * SLE�ڑ���ԏ��̏����敪���e�����ς�(1)�f�Ƃ���
     * @@param l_sleconnQId SLE�ڑ���ԃL���[ID,s_marketCode ���J�o���[�S�����̎s��R�[�h
     * 
     * @@throws SQLException
     */
    private void updateSleConnStatus(final long l_sleconnQId,final String s_marketCode) throws SQLException
    {
        
        final String l_strSql = "update sle_conn_status_changes set proc_status = ?,last_updated_timestamp = sysdate  where que_id = ?";
        
        List l_lisParams = new ArrayList(3);
        l_lisParams.add(new Integer(1));
//        l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));
        l_lisParams.add(new Long(l_sleconnQId));
//        l_lisParams.add(s_marketCode);            
//        l_lisParams.add(new Integer(0));//��������SLE�ڑ���ԃ��R�[�h�̂�
            
        m_web3CallBackDataAccessUtil.executeUpdate(l_strSql, l_lisParams);
    }
    
    /**
     * SEND_Q�ɑS�ď����敪=9:�����G���[�̒������b�Z�[�W�̏����敪�� 0:�������Ƃ���
     * @@param s_marketCode SLE�R�l�N�^�ɑΉ�����s��R�[�h
     * @@return �X�V���R�[�h����
     * @@throws SQLException
     */
    private int updateSleSendqStatus(final String s_marketCode) throws SQLException
    {
        
        final String l_strSql = "update sle_send_q set status = ?,last_updated_timestamp = ? where status = ? and market_code = ? and biz_date = ?";
        
        List l_lisParams = new ArrayList(5);

        l_lisParams.add(new Integer(0));
        l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));
        l_lisParams.add(new Integer(9));
        l_lisParams.add(s_marketCode);
        //���s��Ή�����ɂ́AUpdate SQL�̍i������Ɏs��R�[�h��ǉ�
        l_lisParams.add(m_DateProvider.getBizDate());
        //������蔭������~���邽�߁ABizDate����������ǉ�            
        return m_web3CallBackDataAccessUtil.executeUpdate(l_strSql, l_lisParams);
    }
    
    /**
     * SLE�R�l�N�^���s��R�[�h�A�����R�[�h�ɑΉ�����ORDER BOOK�₢���킹���ʎ擾(�V���A�����₢���킹���s��Ȃ�����)
     * @@param marketCode �s��R�[�h
     * @@param productCode�@@�����R�[�h
     * @@return�@@ORDER BOOK �̖₢���킹���ʂ�InternalRef (����ID)�̃}�b�s���O�Ή��֌W��ێ�����Map
     * @@throws RuntimeException ORDER BOOK�₢���킹���s���ARuntimeException��O���X���[�����
     */
    private Map getOrderBook(String l_strMarketCode, String l_strProductCode)
        throws RuntimeException
    {


        final long startTime = System.currentTimeMillis();
        m_log.info(
            ":::: start of getOrderBook for marketCode,productCode :"
                + l_strMarketCode
                + ","
                + l_strProductCode
            );
        
        //2004�₢���킹���ʂ�ێ�����Iterator
        
        ResultIterator itr_2004QueryResults = null;    
        //2004 Order Reference�₢���킹���s��
        try
        {
            
            // SLE2004�d���𐶐�
            final GlData l_orderConsReqData = new GlData("2004.ORDER");

            m_log.info(
                "Will send 2004 request for marketCode,productCode:"
                    + l_strMarketCode
                    + ","
                    + l_strProductCode);

            l_orderConsReqData.putString("question_type", "2");
            l_orderConsReqData.putString("order_category", "O");
            
            /**�����R�[�h���w��*/
            l_orderConsReqData.putString(
                "stock_code",
                l_strProductCode == null ? "" : l_strProductCode);
                
            l_orderConsReqData.putBigDecimal(
                "index",
                new BigDecimal("000000"));
            l_orderConsReqData.putBigDecimal(
                "number_of_requested_replies",
                new BigDecimal("00000"));
            
            m_log.info("Sending Order Book Request to SLE:" + l_orderConsReqData);    
            try {
                itr_2004QueryResults =
                    WEB3GlConnectorServer
                        .getGlConnectorInstance()
                        .sendOrderConsultationRequest(
                        l_orderConsReqData);
            }
            catch(Exception e){
                e.printStackTrace();
                m_log.error(e.getMessage(),e);    
            }

            /** ��������Order References �₢���킹���ʂ�ێ�����Map*/
            final Map l_hmOrderbookEntriesTable = new HashMap();
            
            while (itr_2004QueryResults.hasNext())
            {
                final Object l_result = itr_2004QueryResults.next();
                final GlData l_respData = (GlData) l_result;
            
                m_log.info("Received 2004 Response data from Order Book: " + l_respData );
                    
                final String l_strInternalRef =
                            l_respData.getString("internal_reference");
                if (l_strInternalRef != null)
                {
                    l_hmOrderbookEntriesTable.put(l_strInternalRef, l_respData);
                }
                
            }
            return Collections.unmodifiableMap(l_hmOrderbookEntriesTable);        
        }
//        catch ( Exception ge)
//        {
//            final String errMsg =
//                        "the query request for sle order book failed";
//            m_log.error(errMsg);
//            throw new RuntimeException(errMsg,ge);    
//        }
        catch (Exception e)
        {
            final String errMsg =
                        "the fatal error happened while Querying Order Book";
            m_log.error(errMsg);
            throw new RuntimeException(errMsg,e);    
                
        }

    }
    
    /**
     * SEND_Q�������b�Z�[�W������SLE�ɐ��������M����邩�`�F�b�N
     * @@param l_sleSendqId SEND_Q�������b�Z�[�W�L���[ID
     * @@param l_mOrderBook �擾��������ORDER_BOOK
     * @@return ���M�ς�:true��Ԃ��@@��:false��Ԃ��B
     */
    private boolean isAlreadySent(long l_sleSendqId, Map l_mOrderBook )
        throws SQLException
    {

        BigDecimal i_OpType = null;
        BigDecimal l_OrderId = null;
        boolean b_result = true;
//        String s_orderstatus_0 = null;
//        String s_orderstatus_1 = null;
        
        final String l_strSql = "select order_id,op_type,created_timestamp from sle_send_q where queue_id = ?";

        List l_lisParams = new ArrayList(1);
        l_lisParams.add( new BigDecimal(l_sleSendqId));

        List l_lisQueryResult = new ArrayList(1);
        
        try
        {
            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
            Map map = (HashMap) l_lisQueryResult.get(0);
//            Object obj_t=map.get("CREATED_TIMESTAMP");
//            m_log.debug(obj_t.getClass());
            i_OpType = (BigDecimal)map.get("OP_TYPE");
            l_OrderId = (BigDecimal)map.get("ORDER_ID");
//            s_orderstatus_0 = (String)map.get("order_status0");
//            s_orderstatus_1 = (String)map.get("order_status1");
            
            final boolean isNewOrder = new BigDecimal(0).equals(i_OpType);

            final String orderIdStr = "" + l_OrderId.toString();

            m_log.info(
                "Checking whether sle_send_row is already sent or not. Row:"
                    + l_sleSendqId);

            final GlData l_gldata = (GlData) l_mOrderBook.get(orderIdStr);

            Boolean l_bRetResult = null;

//            if (isNewOrder)
//            {

            // ���M�ς�
            l_bRetResult = Boolean.valueOf(
                                 (l_gldata != null) 
                                 && ( (Long.toString(l_sleSendqId)).equals(l_gldata.getString("memo")))
                                 );
//            }
//            else if (l_gldata != null)
//            {
//
//                final String memo = l_gldata.getString("memo");
//
//                final Timestamp createTimestampOfOrderMessageInTheOrderBook = getCreatedTimeByMemo(l_gldata.getString("memo")); 
//                l_bRetResult = 
//                    Boolean.valueOf(createTimestampOfOrderMessageInTheOrderBook.after(t_Created)
//                            || createTimestampOfOrderMessageInTheOrderBook.equals(t_Created));
//            }

//            if (l_gldata != null)
//            {
//                //order book ���X�|���X����̏ꍇ�Aorder book status table�Ƃ̃}�b�`�������ʂ��A���f
//                l_bRetResult = Boolean.valueOf(isMatchedWithOrderbookStatusTable(i_OpType.intValue(),s_orderstatus_0,s_orderstatus_1));
//            }
//            else
//            {
//                //Order book ���X�|���X�f�[�^���Ȃ��̏ꍇ
//                l_bRetResult = Boolean.valueOf(false);
//            }

            final boolean l_bFinalReturnValue = Boolean.TRUE.equals(l_bRetResult);
            m_log.info(
                "sle_send_q  already sent:"
                    + l_bFinalReturnValue
                    + " - for row:"
                    + l_sleSendqId);

            return l_bFinalReturnValue;
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute isAlreadySent() :"
                             + e);
            m_log.error("SQL Excution while processing isAlreadySent  for queue_id:" + l_sleSendqId);
                
            throw e;
            
        }
        finally
        {
            try
            {
                m_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
    }
    
//    /**
//     * gl_data.memo�d�����ڂɑΉ�����send_q�������b�Z�[�W��created_timestamp��Ԃ�
//     * @@return 'memo'�Ή�����send_q�������b�Z�[�W��created_timestamp��Ԃ�
//     * @@throws SQLException
//     */
//    private java.sql.Date getCreatedTimeByMemo(String s_Memo) throws SQLException
//    {
//
//
//        
//        final String l_strSql = "select created_timestamp from sle_send_q where queue_id = ?";
//        
//        List l_lisParams = new ArrayList(1);
//        l_lisParams.add( s_Memo);
//
//        List l_lisQueryResult = new ArrayList(1);
//            
//        try
//        {
//            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
//            Map map = (HashMap) l_lisQueryResult.get(0);
//            return (java.sql.Date)map.get("CREATED_TIMESTAMP");
//        }
//        catch (SQLException e)
//        {
//            m_log.debug("SQLException Catched! when excute getCreatedTimeByMemo() :"
//                             + e);
//            throw e;
//        }
//        finally
//        {
//            try
//            {
//                m_web3CallBackDataAccessUtil.releaseResource(false);
//            }
//            catch(SQLException sqle)
//            {
//                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
//            }
//        }
//    }
    
//    /**
//     * gl_data.memo�d�����ڂɑΉ�����send_q�������b�Z�[�W�̃I�y���^�[�^�C�v(op_type)��Ԃ�
//     * @@return 'memo'�Ή�����send_q�������b�Z�[�W��op_type��Ԃ�
//     */
//    private int getOptypeByMemo(String s_Memo) throws SQLException
//    {
//
//
//        
//        final String l_strSql = "select op_type from sle_send_q where queue_id = ?";
//        
//        List l_lisParams = new ArrayList(1);
//        l_lisParams.add( s_Memo);
//
//        List l_lisQueryResult = new ArrayList(1);
//            
//        try
//        {
//            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
//            Map map = (HashMap) l_lisQueryResult.get(0);
//            return ((BigDecimal)(map.get("op_type"))).intValue();
//        }
//        catch (SQLException e)
//        {
//            m_log.debug("SQLException Catched! when excute getOpTypeTimeByMemo() :"
//                             + e);
//            throw e;
//        }
//        finally
//        {
//            try
//            {
//                m_web3CallBackDataAccessUtil.releaseResource(false);
//            }
//            catch(SQLException sqle)
//            {
//                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
//            }
//        }
//
//    }
    
    /**
     * order book�₢���킹���ʂ�status ��order book status table �ƃ}�b�`���A�}�b�`�����ꍇ�̂�true��Ԃ��悤��
     * @@param l_optype �����̃I�y���[�^�^�C�v 0:�V�K 1:���� 2:�����
     *         s_1st_orderstatus order book�₢���킹���ʂ�order_status0(30)
     *         s_1st_orderstatus order book�₢���킹���ʂ�order_status1(41)
     * @@return order book status table�ƃ}�b�`�����ꍇ: true �Ԃ� �}�b�`���Ȃ��ꍇ�Afalse
     */
    private boolean isMatchedWithOrderbookStatusTable(final int l_optype,final String s_1st_orderstatus,final String s_2nd_orderstatus)
    {

        //�V�K�����̏ꍇ
        if ( l_optype == WEB3SleCallbackConstantsDef.OrderOpType.NEW_ORDER_OP)
        {
            if ( (s_1st_orderstatus != null) && s_1st_orderstatus.equals("E") && (s_2nd_orderstatus != null ) && s_2nd_orderstatus.equals("000"))return true;
        }
        //���������̏ꍇ
        else if( l_optype == WEB3SleCallbackConstantsDef.OrderOpType.MODIFY_ORDER_OP)
        {
            if (  (s_1st_orderstatus != null) && s_1st_orderstatus.equals("N") && (s_2nd_orderstatus != null ) && s_2nd_orderstatus.startsWith("5"))return true;
        }
        //����������̏ꍇ
        else if ( l_optype == WEB3SleCallbackConstantsDef.OrderOpType.CANCEL_ORDER_OP)
        {
            if (( s_1st_orderstatus.equals(" ") || (s_1st_orderstatus.length() == 0) || s_1st_orderstatus == null) && (s_2nd_orderstatus != null ) && (s_2nd_orderstatus.startsWith("6")))return true;
        }
            
        return false;
    }
    
    
    /**
     * ������ ORDER_BOOK �����擾����
     */
    public Map getProductsOrderBookMap()
    {
            
        return m_productsOrderBookMap;
    }

    /**
     * ������ ORDER_BOOK ����Set
     */
    public void setProductsOrderBookMap(String s_productCode,Map m_orderbook)
    {        
        m_productsOrderBookMap.put(s_productCode, m_orderbook);
    }

    /**
     * ������ ORDER_BOOK ����reset
     */
    public void reSetProductsOrderBookMap()
    {        
        m_productsOrderBookMap.clear();
    }
      
    /**
     * �����R�[�h�ɑΉ����鏈���t���O���擾
     */
    public Map getRecoveryCheckDoneProductCodesMap()
    { 
        return m_recoveryCheckDoneProductCodesMap;
    }
    
    /**
     * �����R�[�h�ɑΉ����鏈���t���O��Set
     */
    public void setRecoveryCheckDoneProductCodesMap(String s_productCode, Boolean b_on)
    { 
        m_recoveryCheckDoneProductCodesMap.put(s_productCode, b_on);
    }
    
    /**
     * �����R�[�h�ɑΉ����鏈���t���O��reSet
     */
    public void reSetRecoveryCheckDoneProductCodesMap()
    { 
        m_recoveryCheckDoneProductCodesMap.clear();
    }    
    
}

@
