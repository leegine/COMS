head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3BaseSleRepliesCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3BaseSleRepliesCallback�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/18 ��(FLJ) �����쐬
                    2006/05/2  ��(FLJ) �V�K�쐬
 */
package webbroker3.slegateway.callback;
import com.fitechlabs.xbconnector.glbase.gldata.*;

import webbroker3.slegateway.define.WEB3SleCallbackAcceptTypeDef;
import webbroker3.slegateway.define.WEB3SleCallbackOrderExecRouteDivDef;
import webbroker3.slegateway.define.WEB3SleCallbackConstantsDef;
import webbroker3.slegateway.define.WEB3SleCallbackGLRejectTypeDef;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Category;

/**
 * SLE��M�R�[���o�b�N�����̊�{�@@�\API��񋟂���N���X
 * 
 * @@author      ���iFLJ�j
 * @@version     V1.0  
 */
public class WEB3BaseSleRepliesCallback{
    
    /**
     * ���O�o�̓I�u�W�F�N�g
     */
    private static final Category m_log =  Category.getInstance( WEB3BaseSleRepliesCallback.class);


    /**�O���������^�C�v*/
    public static Integer PRODUCT_TYPE = new Integer(4);
    
    /**�������e�F�f�t�H���g����*/
    public static final int OP_DEFAULT = 0;

    /**�������e�F������t�ςݏ���*/
    public static final int OP_ORDER_ACCEPTED = 1;

    /**�������e�F�����ςݏ���*/
    public static final int OP_CHANGE_ACCEPTED = 2;

    /**�������e�F�s���ԍX�V����*/
    public static final int OP_STATUS_CHANGE = 3;  
    
    public static final String ERROR_SENDQ_NOT_EXSIT = "�s�ꃌ�X�|���X�ɑΉ�������L���[�̒������b�Z�[�W�����݂��܂���ł����B";
    
    /**RCVD_Q'�����敪':�����҂���Ԃ�����킵�܂� */
    public static final Integer STATUS_TODO = new Integer(0);

    /**RCVD_Q'�����敪':�����ς�����킵�܂��B */
    public static final Integer STATUS_PROCESSED = new Integer(1);
    
    /**RCVD_Q'�����敪':�������������ꂽ���Ƃ�����킵�܂��B */
    public static final Integer STATUS_SKIP_PROCESSING_IGNORE = new Integer(8);
    
    /**RCVD_Q'�����敪':�G���[�̂��ߏ������ȗ����ꂽ���Ƃ�����킵�܂��B */
    public static final Integer STATUS_SKIP_PROCESSING_ERROR = new Integer(9);
    
    /**SEND_Q'��M�m�F�t���O':��M�m�F�t���O�X�V��\���܂��B��(�d�l�ύX�̂���2006/10/3�ǉ�) */
    public static final Integer SENDQ_CONFIRMED_BY_RCVD = new Integer(1);

    /**SEND_Q'��M�m�F�t���O':��M�m�F�t���O���X�V��\���܂��B��(�d�l�ύX�̂���2006/10/3�ǉ�) */
    public static final Integer SENDQ_NOCONFIRMED_BY_RCVD = new Integer(0);        
    
    /**�I�t���C����Ԃ�����킵�܂��B */
    public static final Integer MARKET_OFFLINE = new Integer(0);

    /**�I�����C����Ԃ�����킵�܂��B */
    public static final Integer MARKET_ONLINE = new Integer(1);
    
    /** �����N����������킵�܂��B */
    public static final Integer MARKET_LINK_RESTORED   = new Integer(2);

    /** �����N����������킵�܂��B */
    public static final Integer MARKET_LINK_LOST = new Integer(3);
    
    /**
     * ����ID���擾����B
     * @@param l_glData�@@�d���I�u�W�F�N�g
     * @@return ����ID�i�擾�ł��Ȃ��ꍇ�A-1��Ԃ��j
     */
    public static long getOrderId (GlData l_glData){
        if(l_glData.getBigDecimal("internal_reference") !=null)//�d�����ږ������ 2006/11/29
        {
            return l_glData.getBigDecimal("internal_reference").longValue();
        }
        
        return -1;
    }
    
    /**
     * �Ή����钍���P�ʃe�[�u���̒����P��ID���擾����B
     * 
     * @@param l_strKey �����L�[�iinternal_reference�j
     */
    public static Object getOrderUnitId(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, final String l_strKey) throws SQLException
    {
        Object l_result = null;
        final String l_strSql = "select ORDER_UNIT_ID from feq_order_unit where order_id = ?";

        List l_lisParams = new ArrayList(1);
        l_lisParams.add( new BigDecimal(l_strKey));
        //unix���ɔ��������^�C���A�E�g�G���[��Ή�����ɂ�BigDecimal�֕ϊ� 2006/11/8

        List l_lisQueryResult = new ArrayList(1);
        try
        {
            l_lisQueryResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute getOrderUnitFieldByInternalReference() :"
                            + e);
            throw e;
        }
        finally
        {
            try
            {
                l_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        if (l_lisQueryResult.size() > 0)
        {
            final Map map = (HashMap) l_lisQueryResult.get(0);
            l_result = map.get("ORDER_UNIT_ID");
        }

        return l_result;
    }
    
    /**
     * �Ή����钍���P�ʃe�[�u���̉^�p�R�[�h�A�،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h���擾����B
     * 
     * @@param l_strKey �����L�[�iinternal_reference�j
     * @@param l_hmUserData �擾�����l��ۑ�����I�u�W�F�N�g
     */
    public static void getOrderUnitFields(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, final String l_strKey, Map l_hmUserData) throws SQLException
    {
        final String l_strSql = "select a.ORDER_EMP_CODE," +
                                   "a.INSTITUTION_CODE," +
                                   "b.BRANCH_CODE, " +
                                   "a.ORDER_REQUEST_NUMBER " +
                                   "from feq_order_unit a,branch b" +
                                   " where a.order_id = ? and a.branch_id = b.branch_id(+) ";

        List l_lisParams = new ArrayList(1); 
        l_lisParams.add( new BigDecimal(l_strKey));
        //unix���ɔ��������^�C���A�E�g�G���[��Ή�����ɂ�BigDecimal�֕ϊ� 2006/11/8

        List l_lisQueryResult = new ArrayList(1);
        try
        {
            l_lisQueryResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute getOrderUnitFieldByInternalReference() :" + e);
            throw e;
        }
        finally
        {
            try
            {
                l_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        if (l_lisQueryResult.size() > 0)
        {
            final Map map = (HashMap) l_lisQueryResult.get(0);
            l_hmUserData.put("order_emp_code", map.get("ORDER_EMP_CODE"));
            l_hmUserData.put("institution_code", map.get("INSTITUTION_CODE"));
            l_hmUserData.put("branch_code", map.get("BRANCH_CODE"));
            l_hmUserData.put("order_request_number", map.get("ORDER_REQUEST_NUMBER"));
        }

    }    
    
    /**
     * �s�ꃌ�X�|���X����̓d�����ڂɂ���āA���X�|���X�R�[���o�b�N�̏������e���敪���A
     * �e��t�敪�f�A�e�����敪�f�A�e�o�H�敪�f��ݒ肷��B
     * @@param l_glData SLE���X�|���X�d��
     * @@param l_hmUserData �e��t�敪�f�A�e�����敪�f�A�e�o�H�敪�f�����̃p�����[�^�ɐݒ肷��B
     * ��l_hmUserData��'��M�X�V�t���O'�̃p�����[�^��ǉ�
     * @@return �������e
     */
    public static int getOrderStatusProcByGlData(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, GlData l_glData, Map l_hmUserData)
    {

        final String l_strReplyType = l_glData.getString("replies_type");
//        final String l_strSubStatus = l_glData.getString("sub_status");//����Ȃ��ϐ����폜�A2006/11/29
        final BigDecimal l_bdAckCommand = l_glData.getBigDecimal("type_of_acknowledged_command");
        final BigDecimal l_bdRejCommand = l_glData.getBigDecimal("type_of_rejected_command");      
        final BigDecimal l_bdActType = l_glData.getBigDecimal("acknowledgement_type");
        final String l_strEliminationMsg = l_glData.getString("elimination_message");//�d�l�ύX�̂��ߒǉ�(2006/10/9)
        final BigDecimal l_bdOpType = (BigDecimal)l_hmUserData.get("op_type");//�d�l�ύX�̂��ߒǉ�(2006/10/9)
        
        
        String l_strRouteDiv = null;
        String l_strAcceptDiv = null;
        Integer l_intStatus = STATUS_SKIP_PROCESSING_IGNORE;//'�����敪'�����l�ɂ���'�����ȗ�(8)'��ݒ肷��@@�o�O���C 2006/10/5
        Integer l_intRcvdConf = SENDQ_NOCONFIRMED_BY_RCVD;//�ˎd�l�ύX�̂��߁A2006/10/3�ǉ�
                                                          //�����l�Ɂ@@��M�X�V�t���O�𖢍X�V�ɐݒ肷��
        int l_intResult = 0;//�����l�Ƀf�t�H���g�l��ݒ�
        
        //////////////////////////////////////////////////////////
        // ACK���X�|���X��M
        //////////////////////////////////////////////////////////
        
        if(WEB3SleCallbackConstantsDef.RepliesType.ACK.equals(l_strReplyType))
        {

            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //    A           <brank>        0               -                2        a.�f�t�H���g����   31: �o����(����)    �O�F�����҂�    �s��ɒ��~
            
            if(l_bdActType!=null && WEB3SleCallbackConstantsDef.AckType.ORDER_ELIMINATED == l_bdActType.intValue())
            {
                if(l_strEliminationMsg != null){ 
                    //���������Ƃ��ď���                    
                    l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.NOT_EXECUTED;
                    l_intStatus = STATUS_SKIP_PROCESSING_IGNORE;
                    l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)    
                    l_intResult = OP_DEFAULT;
                }
                else{ 
                    //�s��reject�����Ƃ��ď����ǉ�(2006/10/9)
                    l_intStatus = STATUS_TODO;
                    l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                    l_intResult = OP_DEFAULT;
                    if ( WEB3SleCallbackConstantsDef.OrderOpType.NEW_ORDER_OP == l_bdOpType.intValue() ){//�I�y���[�^�^�C�v�𔻒f����ɂ�,
                                                                                                     //WEB3�ŊǗ�����I�y���[�^�^�C�v�ԍ��Ŕ��f����悤��
                                                                                                     //��2006/11/2���C
                        //�V�K�����s�ꋑ��
                        l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.ORDER_ACCEPT_ERROR;         
                    }
                    else if (WEB3SleCallbackConstantsDef.OrderOpType.MODIFY_ORDER_OP== l_bdOpType.intValue()){
                        //���������s�ꋑ��
                        l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CHANGE_ERROR;
                    }
                    else{
                        //��������s�ꋑ��
                        l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CANCEL_ERROR;
                    }
                    
                }
            }
            else if(l_bdAckCommand!=null && WEB3SleCallbackConstantsDef.AckdCommand.NEW_ORDER_ACK == l_bdAckCommand.intValue())
            {

                //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
                //                          Ack command    Rejected command    
                //    A            <brank>        0               -              4        b.������t�ςݏ���  01�F������t��      �O�F�����҂�  �s��ɏ��F 
                // �s��V�K������t�ς�                         
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.ORDER_ACCEPT_COMPLETE;
                l_intStatus = STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_ORDER_ACCEPTED;                        

            }
            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //    A          <brank>         1                 -               -         a.�f�t�H���g����   21�F�����         �O�F�����҂�  �s��ɏ��F                      
            // �s�꒍������ς�
            else if(l_bdAckCommand!=null && WEB3SleCallbackConstantsDef.AckdCommand.CANCEL_ACK == l_bdAckCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CANCEL;
                l_intStatus = STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_DEFAULT;                    
            }
            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //    A           <brank>         2                -              -          c.�����ςݏ���    11�F������          �O�F�����҂�  �s��ɏ��F                      
            // �s�꒍�������ς�
            else if(l_bdAckCommand!=null && WEB3SleCallbackConstantsDef.AckdCommand.MODIFY_ACK == l_bdAckCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CHANGED;
                l_intStatus = STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_CHANGE_ACCEPTED;                    
            }            
        }
        
        /////////////////////////////////////////////////////////////////////////
        // MT �܂���OMS REJECT(���n�،����OMS����)���X�|���X��M(FIX�d���̏ꍇ�ł��ύX������2006/10/5)
        /////////////////////////////////////////////////////////////////////////
        
        else if(WEB3SleCallbackConstantsDef.RepliesType.EXCHANGE_REJECT.equals(l_strReplyType))
        {
            
            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //  C           -           -                 0              -         a.�f�t�H���g����   02�F������t�G���[  �O�F�����҂�  OG�A�s��A�܂���SLE�ɋ���      
            // �V�K������MT�ɋ��ۂ���
            if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_NEW_ORDER == l_bdRejCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.ORDER_ACCEPT_ERROR;
                l_intStatus = STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_DEFAULT;                
            }
            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //    C         -           -                 1              -         a.�f�t�H���g����   22�F����G���[    �O�F�����҂�  OG�A�s��A�܂���SLE�ɋ���      
            // ���������MT�ɋ��ۂ���
            else if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_CANCEL== l_bdRejCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CANCEL_ERROR;
                l_intStatus =  STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_DEFAULT;           
            }
            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //    C         -           -                2              -          a.�f�t�H���g����   12�F�����G���[    �O�F�����҂�  OG�A�s��A�܂���SLE�ɋ���      
            // ����������MT�ɋ��ۂ���
            else if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_MODIFY == l_bdRejCommand.intValue())
            {
                
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CHANGE_ERROR;
                l_intStatus =  STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_DEFAULT;           
            }
            
            final String l_strRejectCode  = l_glData.getString("reject_code");
            //�L��GF-FIX���n��Q�ɂ�鋑�ۂ���
            //GF-FIX ���'No Channel is available for this message' �G���[��Ԃ�
            //GL reject_code = 'No Chann'
            if ( l_strRejectCode != null 
                 && (l_strRejectCode.indexOf(WEB3SleCallbackGLRejectTypeDef.GF_FIX_FAILOVER_FAIL) >= 0 )
               )
            {
                l_intStatus = STATUS_SKIP_PROCESSING_ERROR;
            }
            // �L���iOMS�j�ؑ֍Œ���Q�ɂ�鋑�ۂ���
            // GF-FIX ��� 'Communication fail with CTS Gateway' �G���[ ��Ԃ�
            //GL reject_code = ' Communi'
            if ( l_strRejectCode != null
                 && (l_strRejectCode.indexOf(WEB3SleCallbackGLRejectTypeDef.GF_OMS_FAILOVER_FAIL) >= 0 )
               )
            {
                l_intStatus = STATUS_SKIP_PROCESSING_ERROR;
            }            
            // ���׌N���iOMS�jNW��d���������ɂ�鋑�ۂ���
            // ���׌N��-OMS ��� '-150906090' �G���[�R�[�h ��Ԃ�
            if ( l_strRejectCode != null
                 && (l_strRejectCode.indexOf(WEB3SleCallbackGLRejectTypeDef.GJS_NW_CODE_DUPLI) >= 0 )
               )
            {
                l_intStatus = STATUS_SKIP_PROCESSING_ERROR;
            } 
        }
        
        /////////////////////////////////////////////////////////////////////////
        // SLE REJECT���X�|���X��M(FIX�d���̏ꍇ�ł��ύX������2006/10/5)
        /////////////////////////////////////////////////////////////////////////
        
        else if(WEB3SleCallbackConstantsDef.RepliesType.GL_REJECT.equals(l_strReplyType))
        {
            String l_strRej = l_glData.getString("reject_code");
            
            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //  G           -           -                 0              -         a.�f�t�H���g����   02�F������t�G���[  �O�F�����҂�  OG�A�s��A�܂���SLE�ɋ���      
            // �V�K�������@@SLE���ۂ���
            if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_NEW_ORDER == l_bdRejCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.ORDER_ACCEPT_ERROR;
                l_intStatus = STATUS_SKIP_PROCESSING_ERROR;                
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_DEFAULT;                
            }
            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //    G         -           -                 1              -         a.�f�t�H���g����   22�F����G���[    �O�F�����҂�  OG�A�s��A�܂���SLE�ɋ���      
            // ����������@@SLE�ɋ��ۂ���
            else if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_CANCEL== l_bdRejCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CANCEL_ERROR;
                l_intStatus =  STATUS_SKIP_PROCESSING_ERROR;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_DEFAULT;           
            }
            //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
            //                          Ack command    Rejected command    
            //    G         -           -                2              -          a.�f�t�H���g����   12�F�����G���[    �O�F�����҂�  OG�A�s��A�܂���SLE�ɋ���      
            // �����������@@SLE�ɋ��ۂ���
            else if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_MODIFY == l_bdRejCommand.intValue())
            {
                
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CHANGE_ERROR;
                if( ( l_strRej.equals(WEB3SleCallbackGLRejectTypeDef.CHANGE_REJECT_AF_ALL_EXEC)) || (l_strRej.equals(WEB3SleCallbackGLRejectTypeDef.CHANGE_REJECT_AF_PART_EXEC)))
                {
                    /**SLE AP�F
                       �߂�̒����A����d���̂���GL(SLE)����߂��Ă���G���[�d����SLE AP���ŃG���[
                       �X�e�[�^�X�Ƃ��������҂��Ƃ����� 2006/12/14
                     **/
                    l_intStatus = STATUS_TODO;
                }
                else
                {
                    l_intStatus = STATUS_SKIP_PROCESSING_ERROR;
                }
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
                l_intResult = OP_DEFAULT;           
            }
        }
        
        //reply_type    Sub_status    Type of          Type�@@of       Ack_type        �������e           ��t�敪           �����敪       ����
        //                          Ack command    Rejected command    
        //    R               -           -                -              -         a.�f�t�H���g����       NULL           �O�F�����҂�  �s��ɖ�� 
        //�@@���
        /////////////////////////////////////////////////////////////////////////
        // ��背�X�|���X��M(FIX�d���̏ꍇ�ł��ύX������2006/10/5)
        /////////////////////////////////////////////////////////////////////////     
        else if(WEB3SleCallbackConstantsDef.RepliesType.TRADE_EXEC.equals(l_strReplyType))
        {
            l_strAcceptDiv = null;
            l_intStatus = STATUS_TODO;
            l_intRcvdConf = SENDQ_NOCONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
            l_intResult = OP_DEFAULT;
        }

        
        //�e�o�H�敪�f�ɂ��āA�e�󂯋敪�f���ݒ肳�ꂽ�ꍇ�A�h3:������t�h���ݒ肳��G�g�s��ɖ��h�̏ꍇ�A�h0: �o���ʒm�g���ݒ肳��G���̏ꍇ�A�ݒ肳��Ȃ��B
        if(l_strAcceptDiv != null)
        {
            l_strRouteDiv = WEB3SleCallbackOrderExecRouteDivDef.ORDER_ACCEPT;
        }
        else if(WEB3SleCallbackConstantsDef.RepliesType.TRADE_EXEC.equals(l_strReplyType)) 
        {
            l_strRouteDiv = WEB3SleCallbackOrderExecRouteDivDef.EXEC_NOTIFY;
        }
        
        //�e�����敪�f�ɂ��āA�Y�������̎s��E�O�������s��A���敪= �e0�f(MAIL)�̏ꍇ�A�S�Ă̎s�ꃌ�X�|���X�ɑ΂��A�e�W�F(�����ȗ�)�f�̏����敪���ݒ肳���B
//        if(getFeqOrderRequestDiv(l_web3CallBackDataAccessUtil, l_glData.getString("internal_reference"))==0)
        if(getFeqOrderRequestDiv(l_web3CallBackDataAccessUtil,l_hmUserData.get("internal_ref").toString() )==0)//�d�l���C�Ή��R�� 2006/11/10
        {
            l_intStatus = STATUS_SKIP_PROCESSING_IGNORE;
            l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//��(2006/10/3�ǉ�)
        }
        
        //�e��敪�l���L�[�Ŏ擾���AGldata�ɐݒ�
        l_hmUserData.put("route_div", l_strRouteDiv);
        l_hmUserData.put("accept_div", l_strAcceptDiv);
        l_hmUserData.put("rcvd_confirmed",l_intRcvdConf);//�d�l�ύX�̂���(�ǉ� 2006/10/3)
        l_hmUserData.put("status", l_intStatus);

        return l_intResult;
    }
    
    
    /**
     * �Ή�������L���[SEND_Q�̒������b�Z�[�W����A�J�E���gID�A�I�y���[�^�^�C�v���擾����B userData�ɕۑ�����B
     * 
     * @@param l_glData �d���I�u�W�F�N�g
     * @@param l_hmUserData �A�J�E���gID�A�I�y���[�^�^�C�v�����̃p�����[�^�[�ɕۑ�
     *         ���d�l�ύX�̂��߁A����ID���p�����[�^���X�g�ɒǉ�(2006/10/3)
     * @@return �Ή����Ă���SEND_Q���b�Z�[�W�����邩�ǂ����A����ꍇtrue
     */
    public static boolean getSendQMessage(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, GlData l_glData, Map l_hmUserData) throws SQLException,RuntimeException    {
        boolean l_blnResult = true;

        final String l_strSql = "select ACCOUNT_ID,OP_TYPE,ORDER_ID from SLE_SEND_Q where queue_id = ?";//�d�l�ύX����(2006/10/3)�ύX

        List l_lisParams = new ArrayList(1);
        if ( l_glData.getString("memo") == null){
            throw new RuntimeException("the 'memo' field of response data is null");
        }
        //l_lisParams.add(l_glData.getString("memo"));
        l_lisParams.add(new BigDecimal(l_glData.getString("memo")));
        //unix���ɔ��������^�C���A�E�g�G���[�Ή����邽�߁ABigDecimal�^�֕ϊ�����
        List l_lisQueryResult = new ArrayList(0);
        
        try
        {
            m_log.debug(" l_lisParams = " + l_lisParams);
            l_lisQueryResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute setSendqByQueueId() :" + e);
            throw e;
        }
        finally
        {
            try
            {
                l_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        
        //�����L���[ID�ɑΉ�������L���[��SEND_Q�������b�Z�[�W�����݂��Ȃ��ꍇ�A�G���[���b�Z�[�W�����O�ɏo��
        if (l_lisQueryResult.size() == 0)
        {
            m_log.error(ERROR_SENDQ_NOT_EXSIT);
            l_blnResult = false;
        }
        else
        {
            final Map map = (HashMap) l_lisQueryResult.get(0);
            l_hmUserData.put("account_id", map.get("ACCOUNT_ID"));
            l_hmUserData.put("op_type", map.get("OP_TYPE"));
            l_hmUserData.put("internal_ref",map.get("ORDER_ID"));//�d�l�ύX���߁A�ǉ�(2006/10/3)
        }

        return l_blnResult;

    }
    
    /**
     * ���钍��ID�ɑΉ����Ă���s��̊O�������s��A���敪���擾����B
     * @@param l_web3CallBackDataAccessUtil �f�[�^�x�[�X�̐ڑ�
     * @@param l_strKey ����ID
     * @@return �O�������s��A���敪�l
     */
    private static int getFeqOrderRequestDiv(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, final String l_strKey)
    {
        int l_intResult = -1;
        final String l_strSql = "select b.FEQ_ORDER_REQUEST_DIV from feq_order_unit a,market b where a.order_id = ? and a.market_id = b.market_id";

        List l_lisParams = new ArrayList(1);
        //l_lisParams.add(l_strKey);
        l_lisParams.add(new BigDecimal(l_strKey));
        //unix���ɔ��������^�C���A�E�g�G���[��Ή�����ɂ́ABigDecimal�֕ϊ�(2006/11/8)

        List l_lisResult = new ArrayList(1);
        try
        {
            l_lisResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute getFeqOrderRequestDiv() :" + e);
        }
        finally
        {
            try
            {
                l_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch (SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        if (l_lisResult.size() > 0)
        {
            final Map map = (HashMap) l_lisResult.get(0);
            String div = (String)map.get("FEQ_ORDER_REQUEST_DIV");
            if( div != null && !"".equals(div))
            {
                l_intResult = Integer.parseInt(div);                
            }
        }
        return l_intResult;
    }    
    
}
@
