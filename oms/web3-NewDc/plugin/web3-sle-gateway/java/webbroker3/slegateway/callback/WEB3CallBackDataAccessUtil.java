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
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3CallBackDataAccessUtilクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/16 孫(FLJ) 新規作成
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
 * このクラスはデータベースの操作を簡潔化するために作られたツールです。
 * 
 * @@author      孫（FLJ）
 * @@version     V1.0  
 */
public class WEB3CallBackDataAccessUtil
{

    /**
     * DBへの接続
     */
    private Connection m_conn = null;

    /**
     * DB操作のStatement
     */
    private Statement m_stmt = null;

    /**
     * DB操作のPreparedStatement
     */
    private PreparedStatement m_para = null;

    
    /**
     * DB検索結果
     */
    private ResultSet m_rs = null;
    
    /**
     * DBソース
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
     * DB ユーザー名
     */
	public final String m_user;

    /**
     * DB パスワード
     */
	public final String m_password;

    /**
     * ログ出力オブジェクト
     */
	private static final Category m_log =  Category.getInstance(WEB3CallBackDataAccessUtil.class);
	
    private static final boolean DBG   = m_log.isDebugEnabled();
    
    /**
     * DBのパラメータを指定して、DataAccessUtilを作る
     * 
     * @@param l_strDriver      DBドライバ
     * @@param l_strUrl 　@　@　@　@DB位置
     * @@param l_strUser        DBユーザー
     * @@param l_strPassword    DBパスワード
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
     * DataSourceオブジェクトから、DataAccessUtilを生成
     * 
     * @@param l_dataSource  既存DataSource
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
		}//←Listen閉じるなどによってコンネクションが失い時 2006/10/27
    }

    /**
     * @@throws java.sql.SQLException
     */
    public void rollback() throws SQLException
    {	
    	if (m_conn != null){
    		m_conn.rollback();
    	}//←Listen閉じるなどによってコンネクションが失い時 追加2006/10/27
    }

    /**
     * @@param l_isAutoCommit 自動的にコミットするかどうか
     * @@throws java.sql.SQLException
     */
    public void setAutoCommit(boolean l_isAutoCommit) throws SQLException
    {
		if (m_conn == null)//追加 2006/10/12
		{
			openConnection();
		}
        m_conn.setAutoCommit(l_isAutoCommit);
    }

    /**
     * DB検索を実行。PreparedStatementで検索したい場合はパレメータをparamsに指定してください。
     * paramsがnullであれば、Statementオブジェクトはsql文だけで検索を行います。
     * 
     * @@param l_strSql 検索のSQL文
     * @@param l_lisParams nullであれば、sql文だけで検索を行います。
     * @@return 検索結果行の集合。一行データずつ一つmapオブジェクトで保存している。
     *                            mapから列名でデータが取得できる。
     *                            ご注意：①@取得するとき、列名は必ず英語大文字で指定してください。
     *  　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@②データ型NUMMBERの項目はjavaオブジェクトのjava.math.BigDecimalに変更してください。　@
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

        //params内容がなったら、Statementでsql文を実行
        if (l_lisParams == null || l_lisParams.size() == 0)
        {
            m_rs = m_stmt.executeQuery(l_strSql);
        }
        //paramsがある場合、prepareStatementに条件を設定し、検索を実行
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
        
        //ResultSetからデータをmap(一行のデータ)とlist(すべてのデータ)に保存
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
     * DB更新を実行。PreparedStatementで更新したい場合はパレメータをparamsに指定してください。
     * paramsがnullであれば、Statementオブジェクトはsql文だけで操作を行います。
     * 
     * @@param l_strSql 更新のSQL文
     * @@param l_lisParams nullであれば、sql文だけで更新を行います。
     * @@return 更新された行数。
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
        //params内容がなったら、Statementでsql文を実行
        if (l_lisParams == null || l_lisParams.size() == 0)
        {
            count = m_stmt.executeUpdate(l_strSql);
        }
        //paramsがある場合、prepareStatementに条件を設定し、更新を実行        
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
     * DB検索の結果を集計。PreparedStatementで検索したい場合はパレメータをparamsに指定してください。
     * paramsがnullであれば、Statementオブジェクトはsql文だけで検索を行います。
     * 
     * @@param l_strSql 検索のSQL文
     * @@param l_lisParams nullであれば、sql文だけで検索を行います。
     * @@return 検索結果行の集合。一行データずつ一つmapオブジェクトで保存している。mapから列名でデータが取得できる。
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

        //params内容がなったら、Statementでsql文を実行
        if (l_lisParams == null || l_lisParams.size() == 0)
        {
            m_rs = m_stmt.executeQuery(l_strSql);
        }
        //paramsがある場合、prepareStatementに条件を設定し、検索を実行
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
     * リソースをクローズ。
     * @@param l_isConnectionClosed Connectionも一緒にクローズしたいとき、trueを指定してください
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
     * データベースへの接続を開ける
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
     * PreparedStatementにパラメータを設定
     * 
     * @@param l_preparedStatement
     *            PreparedStatement
     * @@param l_lisParams
     *            条件
     * @@throws SQLException
     */
    private void setValue(PreparedStatement l_preparedStatement, List l_lisParams)
            throws SQLException
    {
        int l_intCnt = l_lisParams.size();
        //条件の集合を遍歴
        for (int i = 0; i < l_intCnt; i++)
        {
            //正しいタイプで設定する
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
