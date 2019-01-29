head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3CallBackDataAccessUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3CallBackDataAccessUtil�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/16 ��(FLJ) �V�K�쐬
*/
package webbroker3.slegateway.callback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.log4j.Category;

/**
 * ���̃N���X�̓f�[�^�x�[�X�̑�����Ȍ������邽�߂ɍ��ꂽ�c�[���ł��B
 * 
 * @@author      ���iFLJ�j
 * @@version     V1.0  
 */
public class WEB3CallBackDataAccessUtil
{

    /**
     * DB�ւ̐ڑ�
     */
    private Connection m_conn = null;

    /**
     * DB�����Statement
     */
    private Statement m_stmt = null;

    /**
     * DB�����PreparedStatement
     */
    private PreparedStatement m_para = null;

    
    /**
     * DB��������
     */
    private ResultSet m_rs = null;
    
    /**
     * DB�\�[�X
     */
    private final DataSource m_dataSource;

    /**
     * DB Driver
     */
    public final String m_driver;

    /**
     * DB url
     */
	public final String m_url;

    /**
     * DB ���[�U�[��
     */
	public final String m_user;

    /**
     * DB �p�X���[�h
     */
	public final String m_password;

    /**
     * ���O�o�̓I�u�W�F�N�g
     */
	private static final Category m_log =  Category.getInstance(WEB3CallBackDataAccessUtil.class);
	
    private static final boolean DBG   = m_log.isDebugEnabled();
    
    /**
     * DB�̃p�����[�^���w�肵�āADataAccessUtil�����
     * 
     * @@param l_strDriver      DB�h���C�o
     * @@param l_strUrl �@@�@@�@@�@@DB�ʒu
     * @@param l_strUser        DB���[�U�[
     * @@param l_strPassword    DB�p�X���[�h
     */
    public WEB3CallBackDataAccessUtil(String l_strDriver, String l_strUrl, String l_strUser,
            String l_strPassword) throws SQLException
    {
        this.m_driver = l_strDriver;
        this.m_url = l_strUrl;
        this.m_user = l_strUser;
        this.m_password = l_strPassword;
        this.m_dataSource = null;

        openConnection();

    }

    /**
     * DataSource�I�u�W�F�N�g����ADataAccessUtil�𐶐�
     * 
     * @@param l_dataSource  ����DataSource
     */
    public WEB3CallBackDataAccessUtil(DataSource l_dataSource) throws SQLException
    {
        this.m_dataSource = l_dataSource;
        this.m_driver = null;
        this.m_url = null;
        this.m_user = null;
        this.m_password = null;
        openConnection();

    }

    /**
     * @@throws java.sql.SQLException
     */
    public void commit() throws SQLException
    {
		if (m_conn != null){
			m_conn.commit();
		}//��Listen����Ȃǂɂ���ăR���l�N�V������������ 2006/10/27
    }

    /**
     * @@throws java.sql.SQLException
     */
    public void rollback() throws SQLException
    {	
    	if (m_conn != null){
    		m_conn.rollback();
    	}//��Listen����Ȃǂɂ���ăR���l�N�V������������ �ǉ�2006/10/27
    }

    /**
     * @@param l_isAutoCommit �����I�ɃR�~�b�g���邩�ǂ���
     * @@throws java.sql.SQLException
     */
    public void setAutoCommit(boolean l_isAutoCommit) throws SQLException
    {
		if (m_conn == null)//�ǉ� 2006/10/12
		{
			openConnection();
		}
        m_conn.setAutoCommit(l_isAutoCommit);
    }

    /**
     * DB���������s�BPreparedStatement�Ō����������ꍇ�̓p�����[�^��params�Ɏw�肵�Ă��������B
     * params��null�ł���΁AStatement�I�u�W�F�N�g��sql�������Ō������s���܂��B
     * 
     * @@param l_strSql ������SQL��
     * @@param l_lisParams null�ł���΁Asql�������Ō������s���܂��B
     * @@return �������ʍs�̏W���B��s�f�[�^�����map�I�u�W�F�N�g�ŕۑ����Ă���B
     *                            map����񖼂Ńf�[�^���擾�ł���B
     *                            �����ӁF�@@�擾����Ƃ��A�񖼂͕K���p��啶���Ŏw�肵�Ă��������B
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A�f�[�^�^NUMMBER�̍��ڂ�java�I�u�W�F�N�g��java.math.BigDecimal�ɕύX���Ă��������B�@@
     * @@throws SQLException
     */
    public List executeQuery(String l_strSql, List l_lisParams) throws SQLException
    {
        if(DBG)
        {
            m_log.debug("|||| Start Search : sql=" + l_strSql + ",params=" + l_lisParams);
        }
        
        List l_lisResult = new ArrayList();
        
        if (m_conn == null)
        {
            openConnection();
        }
		releaseResource(false);
        m_stmt = m_conn.createStatement();

        //params���e���Ȃ�����AStatement��sql�������s
        if (l_lisParams == null || l_lisParams.size() == 0)
        {
            m_rs = m_stmt.executeQuery(l_strSql);
        }
        //params������ꍇ�AprepareStatement�ɏ�����ݒ肵�A���������s
        else
        {
            m_para = m_conn.prepareStatement(l_strSql);
            setValue(m_para, l_lisParams);
            m_rs = m_para.executeQuery();
        }
        
        if(DBG)
        {
            m_log.debug("|||| End Search Success. " );
        }
        
        //ResultSet����f�[�^��map(��s�̃f�[�^)��list(���ׂẴf�[�^)�ɕۑ�
        ResultSetMetaData rsmd = m_rs.getMetaData();
        while (m_rs.next())
        {
            Map l_hmResult = new HashMap();

            for (int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                l_hmResult.put(rsmd.getColumnName(i), m_rs.getObject(i));
            }

            l_lisResult.add(l_hmResult);
        }
        if(DBG)
        {
            m_log.debug("|||| Search Result Setted. number of result:" + l_lisResult.size());
        }

        return l_lisResult;
    }

    /**
     * DB�X�V�����s�BPreparedStatement�ōX�V�������ꍇ�̓p�����[�^��params�Ɏw�肵�Ă��������B
     * params��null�ł���΁AStatement�I�u�W�F�N�g��sql�������ő�����s���܂��B
     * 
     * @@param l_strSql �X�V��SQL��
     * @@param l_lisParams null�ł���΁Asql�������ōX�V���s���܂��B
     * @@return �X�V���ꂽ�s���B
     * @@throws SQLException
     */
    public int executeUpdate(String l_strSql, List l_lisParams) throws SQLException
    {
        if(DBG)
        {
            m_log.debug("|||| Start Update : sql=" + l_strSql + ",params=" + l_lisParams);
        }
        
        int count = 0;

        if (m_conn == null)
        {
            openConnection();
        }
		releaseResource(false);
        m_stmt = m_conn.createStatement();
        //params���e���Ȃ�����AStatement��sql�������s
        if (l_lisParams == null || l_lisParams.size() == 0)
        {
            count = m_stmt.executeUpdate(l_strSql);
        }
        //params������ꍇ�AprepareStatement�ɏ�����ݒ肵�A�X�V�����s        
        else
        {
            m_para = m_conn.prepareStatement(l_strSql);
            setValue(m_para, l_lisParams);
            count = m_para.executeUpdate();
        }

        if(DBG)
        {
            m_log.debug("|||| End Update Success. " );          
        }
        return count;
    }
    
    /**
     * DB�����̌��ʂ��W�v�BPreparedStatement�Ō����������ꍇ�̓p�����[�^��params�Ɏw�肵�Ă��������B
     * params��null�ł���΁AStatement�I�u�W�F�N�g��sql�������Ō������s���܂��B
     * 
     * @@param l_strSql ������SQL��
     * @@param l_lisParams null�ł���΁Asql�������Ō������s���܂��B
     * @@return �������ʍs�̏W���B��s�f�[�^�����map�I�u�W�F�N�g�ŕۑ����Ă���Bmap����񖼂Ńf�[�^���擾�ł���B
     * @@throws SQLException
     */
    public int executeCount(String l_strSql, List l_lisParams) throws SQLException
    {
        if(DBG)
        {
            m_log.debug("|||| Start Count : sql=" + l_strSql +",params=" + l_lisParams);
        }
        
        int l_intResult = 0;
        
        if (m_conn == null)
        {
            openConnection();
        }
		releaseResource(false);
        m_stmt = m_conn.createStatement();

        //params���e���Ȃ�����AStatement��sql�������s
        if (l_lisParams == null || l_lisParams.size() == 0)
        {
            m_rs = m_stmt.executeQuery(l_strSql);
        }
        //params������ꍇ�AprepareStatement�ɏ�����ݒ肵�A���������s
        else
        {
            m_para = m_conn.prepareStatement(l_strSql);
            setValue(m_para, l_lisParams);
            m_rs = m_para.executeQuery();
        }
        
        if(DBG)
        {
            m_log.debug("|||| End Search Success. " );
        }
        
        while (m_rs.next())
        {
            l_intResult++;
        }
        if(DBG)
        {
            m_log.debug("|||| Count Setted. number of result:" + l_intResult);
        }

        return l_intResult;
    }    

    /**
     * ���\�[�X���N���[�Y�B
     * @@param l_isConnectionClosed Connection���ꏏ�ɃN���[�Y�������Ƃ��Atrue���w�肵�Ă�������
     */
    public void releaseResource(boolean l_isConnectionClosed) throws SQLException
    {

        if (m_rs != null)
        {
            m_rs.close();
            m_rs = null;
        }
        if (m_stmt != null)
        {
            m_stmt.close();
            m_stmt = null;
        }
        if (m_para != null)
        {
            m_para.close();
            m_para = null;
        }
        if (m_conn != null && l_isConnectionClosed)
        {
            m_conn.close();
            m_conn = null;
        }
    }
    
    /**
     * �f�[�^�x�[�X�ւ̐ڑ����J����
     *
     */
    private void openConnection() throws SQLException
    {
        if (m_dataSource == null)
        {
            // load the driver
            try
            {
                Class.forName(m_driver).newInstance();
            }
            catch (Throwable t)
            {

                final String msg = "Failed to load the JDBC driver. Perhaps not in the classpath";
                m_log.error(msg, t);
                throw new RuntimeException(msg, t);
            }

            m_log.info("|||| Connecting to DB ");

            try
            {
                Properties params = new Properties();
                params.setProperty("user", m_user);
                params.setProperty("password", m_password);
                m_conn = DriverManager.getConnection(m_url, params);

            }
            catch (SQLException e)
            {
                final String msg = "Error while obtaining JDBC connection.";
                m_log.error(msg, e);
                throw e;
            }
        }
        else
        {
            try
            {
                m_conn = m_dataSource.getConnection();
            }
            catch (SQLException e)
            {
                final String msg = "Error while obtaining JDBC connection from data source.";
                m_log.error(msg, e);
                throw e;
            }
        }

    }    

    /**
     * PreparedStatement�Ƀp�����[�^��ݒ�
     * 
     * @@param l_preparedStatement
     *            PreparedStatement
     * @@param l_lisParams
     *            ����
     * @@throws SQLException
     */
    private void setValue(PreparedStatement l_preparedStatement, List l_lisParams)
            throws SQLException
    {
        int l_intCnt = l_lisParams.size();
        //�����̏W����՗�
        for (int i = 0; i < l_intCnt; i++)
        {
            //�������^�C�v�Őݒ肷��
            Object l_object = l_lisParams.get(i);
            if(l_object instanceof Object[])
            {
                Object[] vals = (Object[])l_object;
//                System.out.println(i+"::::::::::"+vals[0]+","+vals[1]);
                if(vals[0]==null)
                {
                    l_preparedStatement.setNull(i+1, ((Integer)vals[1]).intValue());
                }
                else
                {
                    l_preparedStatement.setObject(i+1, vals[0]);
                }
            
            }else{
                l_preparedStatement.setObject(i + 1, l_object);
//                System.out.println(i+"::::::::::"+o);            
            }

        }
    }
}@
