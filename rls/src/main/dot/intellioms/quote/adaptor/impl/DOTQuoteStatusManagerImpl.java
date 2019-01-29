/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteStatusManager�N���X(DOTQuoteStatusManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.fitechlabs.fin.intellioms.persist.PersistException;
import com.fitechlabs.fin.intellioms.persist.db.AbstractDbStore;
import com.fitechlabs.fin.intellioms.tx.TxManager;
import com.fitechlabs.fin.intellioms.tx.TxManagerException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteStatusManager;


/**
 * (�ڑ���ԃ}�l�[�W��)<BR>
 * <BR>
 * �����T�[�o�Ƃ̐ڑ���Ԃ�QUOTE_STATUS�e�[�u���ŊǗ�����N���X�B<BR>
 * <BR>
 * QUOTE_STATUS�e�[�u���ł́A���[���G���W���̃z�X�g�����L�[�Ƃ��āA
 * ���[���G���W���̃m�[�h���Ɏ����T�[�o�Ƃ̐ڑ���Ԃ��Ǘ�����B<BR>
 * 
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteStatusManagerImpl extends AbstractDbStore implements DOTQuoteStatusManager
{
    
    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());
    
    /** �z�X�g�� */
    private String hostName;
    
    /**
     * �R���X�g���N�^<BR>
     */
    public DOTQuoteStatusManagerImpl()
    {
        hostName = getHostName();
    }
    
    /**
     * (get�X�e�[�^�X)<BR>
     * <BR>
     * ���݂̐ڑ���Ԃ��擾����B<BR>
     * <BR>
     * @return ���݂̐ڑ����
     */
    public QuoteStatus getCurrentStatus()
    {
        
        // �f�t�H���g�̐ڑ����
        int l_intStatus = QuoteStatus.IntValues.CLOSED;
        
        Connection l_con = null;
        PreparedStatement l_st = null;
        ResultSet l_rs = null;
        
        try
        {
            try
            {
                
                TxManager.begin();
                
                // �z�X�g����QUOTE_STATUS�e�[�u��������
                l_con = TxManager.getConnection();
                l_st = l_con.prepareStatement(
                    "select * from quote_status where host_name=?");
                l_st.setString(1, hostName);
                l_rs = l_st.executeQuery();
                
                // �Ώۃf�[�^�����݂���ꍇ�ASTATUS��̒l���擾
                if (l_rs.next())
                {
                    l_intStatus = l_rs.getInt("status");
                }
                
                TxManager.commit();
                
            } catch (SQLException l_sqle)
            {
                log.error(l_sqle.getMessage(), l_sqle);
                rollback();
                throw new RuntimeException(l_sqle);
            } catch (TxManagerException l_txe)
            {
                log.error(l_txe.getMessage(), l_txe);
                rollback();
                throw new RuntimeException(l_txe);
            } finally
            {
                close(l_rs);
                close(l_st);
            }
            
        } catch (PersistException l_pe)
        {
            throw new RuntimeException(l_pe);
        }
        
        // �擾�����l��QuoteStatus�ɕϊ�
        return QuoteStatus.toQuoteStatus(l_intStatus);
        
    }
    
    /**
     * (modify�ڑ����)<BR>
     * <BR>
     * ���݂̐ڑ���Ԃ��w�肵���X�e�[�^�X�ɕύX������B<BR>
     * 
     * @param l_newStatus �ڑ����
     */
    public void modifyStatus(QuoteStatus l_newStatus)
    {
        
        Connection l_con = null;
        PreparedStatement l_st1 = null;
        PreparedStatement l_st2 = null;
        ResultSet l_rs = null;
        
        try
        {
            try
            {
                
                TxManager.begin();
                
                // �z�X�g����QUOTE_STATUS�e�[�u��������
                l_con = TxManager.getConnection();
                l_st1 = l_con.prepareStatement(
                    "select * from quote_status where host_name=?");
                l_st1.setString(1, hostName);
                l_rs = l_st1.executeQuery();
                
                if (l_rs.next())
                {
                    
                    // �Ώۃf�[�^�����݂���ꍇ�́ASTATUS��̒l��UPDATE
                    l_st2 = l_con.prepareStatement(
                        "update quote_status set status=?, last_updated_timestamp=sysdate where host_name=?");
                    l_st2.setInt(1, l_newStatus.toValue());
                    l_st2.setString(2, hostName);
                    
                    l_st2.executeUpdate();
                    
                } else
                {
                    
                    // �Ώۃf�[�^�����݂��Ȃ��ꍇ�́AINSERT
                    l_st2 = l_con.prepareStatement(
                        "insert into quote_status(host_name, status) values(?, ?)");
                    l_st2.setString(1, hostName);
                    l_st2.setInt(2, l_newStatus.toValue());
                    
                    l_st2.executeUpdate();
                    
                }
                
                TxManager.commit();
                
                if (log.isDebug())
                {
                    log.debug("QuoteStatus modified to " + l_newStatus + ".");
                }
                
            } catch (SQLException l_sqle)
            {
                log.error(l_sqle.getMessage(), l_sqle);
                rollback();
                throw new RuntimeException(l_sqle);
            } catch (TxManagerException l_txe)
            {
                log.error(l_txe.getMessage(), l_txe);
                rollback();
                throw new RuntimeException(l_txe);
            } finally
            {
                close(l_rs);
                close(l_st1);
                close(l_st2);
            }
            
        } catch (PersistException l_pe)
        {
            throw new RuntimeException(l_pe);
        }
        
        return;
        
    }
    
    /**
     * (get�z�X�g��)<BR>
     * <BR>
     * ���[�J���z�X�g�̃z�X�g�����擾����
     */
    private String getHostName()
    {
        try
        {
            InetAddress l_localHost = InetAddress.getLocalHost();
            return l_localHost.getHostName();
        } catch (UnknownHostException l_uhe)
        {
            throw new RuntimeException(l_uhe);
        }
    }
    
}
