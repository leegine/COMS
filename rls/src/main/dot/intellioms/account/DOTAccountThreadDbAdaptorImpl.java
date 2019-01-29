/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���ƃA�J�E���g�����W�f�[�^�x�[�X�A�_�v�^�[����(DOTAccountThreadDbAdaptorImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.fitechlabs.fin.intellioms.account.AccountIDRange;
import com.fitechlabs.fin.intellioms.persist.PersistException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.PurposeType;





/**
 * �X���b�h���ƃA�J�E���g�����W�f�[�^�x�[�X�A�_�v�^�[����
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTAccountThreadDbAdaptorImpl implements DOTAccountThreadDbAdaptor
{

    /**
     * ���K�[
     */
    private static final Log log = Log.getLogger(DOTAccountThreadDbAdaptorImpl.class);

    private static final String SQL_GET_ACCOUNT =
        "SELECT MIN(id), MAX(id) FROM rls_account ";

    private static final String SQL_FOR_UPDATE_ACCOUNT_RANGE_PER_THREAD =
        "SELECT * FROM rls_account_range_per_thread WHERE " +
        "? <= account_id_end AND " +
        "account_id_start <= ? AND " +
        "purpose_type = ? " +
        "FOR UPDATE NOWAIT";

    private static final String SQL_DEL_ACCOUNT_RANGE_PER_THREAD =
        "DELETE FROM rls_account_range_per_thread WHERE " +
        "? <= account_id_end AND " +
        "account_id_start <= ? AND " +
        "purpose_type = ? ";

    private static final String SQL_INSERT_ACCOUNT_RANGE_PER_THREAD =
        "INSERT INTO rls_account_range_per_thread " +
        "(purpose_type, account_id_start, account_id_end, thread_no, created_timestamp, last_updated_timestamp) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    /**
     *
     */
    public DOTAccountThreadDbAdaptorImpl()
    {
        super();
    }

    /**
     * @see DOTAccountThreadDbAdaptor#getAccountIDRange(DOTAccountThreadDbParams)
     */
    public AccountIDRange getAccountIDRange(DOTAccountThreadDbParams l_context) throws PersistException
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conn = l_context.getConnection();

            ps = conn.prepareStatement(SQL_GET_ACCOUNT);

            rs = ps.executeQuery();

            rs.next();

            AccountIDRange l_range = new AccountIDRange(rs.getLong("MIN(id)"), rs.getLong("MAX(id)"));

            return l_range;
        }
        catch (SQLException e)
        {
            log.error(e.getMessage(), e);
            throw new PersistException(e.getMessage(), e);
        }
        finally
        {
            close(rs);
            close(ps);
        }

    }
    /**
     *
     * @see DOTAccountThreadDbAdaptorImpl#getThreadAccountRange(DOTAccountThreadDbParams)
     */
    public List getAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException
    {
        List l_list = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            AccountRangePerThread l_accountRange = l_context.getAccountRange();
            conn = l_context.getConnection();

            ps = conn.prepareStatement(SQL_FOR_UPDATE_ACCOUNT_RANGE_PER_THREAD);

            ps.setLong(1, l_accountRange.getAccountStart());
            ps.setLong(2, l_accountRange.getAccountEnd());
            ps.setInt(3, l_accountRange.getPurposeType().toValue());

            for (rs = ps.executeQuery(); rs.next();)
            {
                AccountRangePerThread l_accountRangePerThread = new AccountRangePerThread();
                l_accountRangePerThread.setPurposeType(PurposeType.toPurposeType(rs.getInt("purpose_type")));
                l_accountRangePerThread.setAccountStart(rs.getLong("account_id_start"));
                l_accountRangePerThread.setAccountEnd(rs.getLong("account_id_end"));
                l_accountRangePerThread.setThreadNo(rs.getLong("thread_no"));
                l_accountRangePerThread.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
                l_accountRangePerThread.setLastUpdatedTimestamp(rs.getTimestamp("last_updated_timestamp"));
                l_list.add(l_accountRangePerThread);
            }
        }
        catch (SQLException e)
        {
            log.error(e.getMessage(), e);
            throw new PersistException(e.getMessage(), e);
        }
        finally
        {
            close(rs);
            close(ps);
        }

        return l_list;
    }

    /**
     *
     * @see DOTAccountThreadDbAdaptorImpl#deleteAccountRangePerThread(DOTAccountThreadDbParams)
     */
    public void deleteAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException
    {
        Connection conn = null;
        PreparedStatement ps = null;

        try
        {
            AccountRangePerThread l_accountRange = l_context.getAccountRange();
            conn = l_context.getConnection();

            ps = conn.prepareStatement(SQL_DEL_ACCOUNT_RANGE_PER_THREAD);

            ps.setLong(1, l_accountRange.getAccountStart());
            ps.setLong(2, l_accountRange.getAccountEnd());
            ps.setInt(3, l_accountRange.getPurposeType().toValue());

            ps.executeUpdate();

        }
        catch (SQLException e)
        {
            log.error(e.getMessage(), e);
            throw new PersistException(e.getMessage(), e);
        }
        finally
        {
            close(ps);
        }
    }

    /**
     *
     * @see DOTAccountThreadDbAdaptorImpl#insertAccountRangePerThread(DOTAccountThreadDbParams)
     */
    public void insertAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException
    {
        Connection conn = null;
        PreparedStatement ps = null;

        try
        {
            AccountRangePerThread l_accountRange = l_context.getAccountRange();
            conn = l_context.getConnection();

            ps = conn.prepareStatement(SQL_INSERT_ACCOUNT_RANGE_PER_THREAD);

            ps.setLong(1, l_accountRange.getPurposeType().toValue());
            ps.setLong(2, l_accountRange.getAccountStart());
            ps.setLong(3, l_accountRange.getAccountEnd());
            ps.setLong(4, l_accountRange.getThreadNo());
            ps.setTimestamp(5, l_accountRange.getCreatedTimestamp());
            ps.setTimestamp(6, l_accountRange.getLastUpdatedTimestamp());

            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            log.error(e.getMessage(), e);
            throw new PersistException(e.getMessage(), e);
        }
        finally
        {
            close(ps);
        }
    }

    protected void close(ResultSet rs)
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch(SQLException e)
            {
                log.warn("Unable to close database result set.", e);
            }
        }
    }

    protected void close(Statement st)
    {
        if(st != null)
        {
            try
            {
                st.close();
            }
            catch(SQLException e)
            {
                log.warn("Unable to close database result set.", e);
            }
        }
    }

}
