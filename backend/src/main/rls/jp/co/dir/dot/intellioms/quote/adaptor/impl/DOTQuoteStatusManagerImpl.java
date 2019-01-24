/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteStatusManagerクラス(DOTQuoteStatusManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 山田　卓司(FLJ) 新規作成
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
 * (接続状態マネージャ)<BR>
 * <BR>
 * 時価サーバとの接続状態をQUOTE_STATUSテーブルで管理するクラス。<BR>
 * <BR>
 * QUOTE_STATUSテーブルでは、ルールエンジンのホスト名をキーとして、
 * ルールエンジンのノード毎に時価サーバとの接続状態を管理する。<BR>
 * 
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteStatusManagerImpl extends AbstractDbStore implements DOTQuoteStatusManager
{
    
    /** ロガー */
    private final Log log = Log.getLogger(getClass());
    
    /** ホスト名 */
    private String hostName;
    
    /**
     * コンストラクタ<BR>
     */
    public DOTQuoteStatusManagerImpl()
    {
        hostName = getHostName();
    }
    
    /**
     * (getステータス)<BR>
     * <BR>
     * 現在の接続状態を取得する。<BR>
     * <BR>
     * @return 現在の接続状態
     */
    public QuoteStatus getCurrentStatus()
    {
        
        // デフォルトの接続状態
        int l_intStatus = QuoteStatus.IntValues.CLOSED;
        
        Connection l_con = null;
        PreparedStatement l_st = null;
        ResultSet l_rs = null;
        
        try
        {
            try
            {
                
                TxManager.begin();
                
                // ホスト名でQUOTE_STATUSテーブルを検索
                l_con = TxManager.getConnection();
                l_st = l_con.prepareStatement(
                    "select * from quote_status where host_name=?");
                l_st.setString(1, hostName);
                l_rs = l_st.executeQuery();
                
                // 対象データが存在する場合、STATUS列の値を取得
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
        
        // 取得した値をQuoteStatusに変換
        return QuoteStatus.toQuoteStatus(l_intStatus);
        
    }
    
    /**
     * (modify接続状態)<BR>
     * <BR>
     * 現在の接続状態を指定したステータスに変更をする。<BR>
     * 
     * @param l_newStatus 接続状態
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
                
                // ホスト名でQUOTE_STATUSテーブルを検索
                l_con = TxManager.getConnection();
                l_st1 = l_con.prepareStatement(
                    "select * from quote_status where host_name=?");
                l_st1.setString(1, hostName);
                l_rs = l_st1.executeQuery();
                
                if (l_rs.next())
                {
                    
                    // 対象データが存在する場合は、STATUS列の値をUPDATE
                    l_st2 = l_con.prepareStatement(
                        "update quote_status set status=?, last_updated_timestamp=sysdate where host_name=?");
                    l_st2.setInt(1, l_newStatus.toValue());
                    l_st2.setString(2, hostName);
                    
                    l_st2.executeUpdate();
                    
                } else
                {
                    
                    // 対象データが存在しない場合は、INSERT
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
     * (getホスト名)<BR>
     * <BR>
     * ローカルホストのホスト名を取得する
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
