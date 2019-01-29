head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.26.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data.qtp;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rcp.data.qtp.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link QtpRichPushHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QtpRichPushHistoryRow}インスタンスへ関連付けることができます。 
 * クライアントコードにおいて必要とされる共通のデータオペレーションを実装しています。 
 * <p> 
 *     <li> 新しいレコードに対し一意の主キー値またはオブジェクトを作成 </li> 
 *     <li> 外部キーからレコードを検索 </li> 
 *     <li> 外部キーの関係にあるオブジェクトを検索 </li> 
 *     <li> インデックスを持つ既存のデータベーススキーマにクエリを実行 </li> 
 * <p> 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QtpRichPushHistoryPK 
 * @@see QtpRichPushHistoryRow 
 */
public class QtpRichPushHistoryDao extends DataAccessObject {


  /** 
   * この{@@link QtpRichPushHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private QtpRichPushHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QtpRichPushHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link QtpRichPushHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QtpRichPushHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QtpRichPushHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QtpRichPushHistoryRow )
                return new QtpRichPushHistoryDao( (QtpRichPushHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QtpRichPushHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QtpRichPushHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QtpRichPushHistoryRow}オブジェクト 
    */
    protected QtpRichPushHistoryDao( QtpRichPushHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QtpRichPushHistoryRow}オブジェクトを取得します。
   */
    public QtpRichPushHistoryRow getQtpRichPushHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link QtpRichPushHistoryRow}オブジェクトから{@@link QtpRichPushHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link QtpRichPushHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QtpRichPushHistoryDao}取得のために指定の{@@link QtpRichPushHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QtpRichPushHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QtpRichPushHistoryDao forRow( QtpRichPushHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (QtpRichPushHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QtpRichPushHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QtpRichPushHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QtpRichPushHistoryPK}やデータベースレコードとして挿入される{@@link QtpRichPushHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QtpRichPushHistoryRow.TYPE );
    }


  /** 
   * {@@link QtpRichPushHistoryRow}を一意に特定する{@@link QtpRichPushHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QtpRichPushHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QtpRichPushHistoryParams}オブジェクトの主キーとして利用可能な{@@link QtpRichPushHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QtpRichPushHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new QtpRichPushHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QtpRichPushHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushHistoryRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryPK pk = new QtpRichPushHistoryPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQtpRichPushHistoryPKオブジェクトから{@@link QtpRichPushHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQtpRichPushHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushHistoryRow findRowByPk( QtpRichPushHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QtpRichPushHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(QtpRichPushHistoryRow)}を使用してください。 
   */
    public static QtpRichPushHistoryDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryPK pk = new QtpRichPushHistoryPK( p_serlnum );
        QtpRichPushHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QtpRichPushHistoryPK)}および{@@link #forRow(QtpRichPushHistoryRow)}を使用してください。 
   */
    public static QtpRichPushHistoryDao findDaoByPk( QtpRichPushHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_serlnum, and にて指定の値から一意の{@@link QtpRichPushHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のp_serlnum, and の値と一致する{@@link QtpRichPushHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static QtpRichPushHistoryRow findRowBySerlnum( long p_serlnum ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "serlnum=?",
            null,
            new Object[] { new Long(p_serlnum) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QtpRichPushHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QtpRichPushHistoryDao.findRowsBySerlnum(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowBySerlnum(long)}および{@@link #forRow(QtpRichPushHistoryRow)}を使用してください。 
   */
    public static QtpRichPushHistoryDao findDaoBySerlnum( long p_serlnum ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySerlnum( p_serlnum ) );
    }


  /** 
   * p_tpk, p_type, and にて指定の値から一意の{@@link QtpRichPushHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tpk 検索対象であるp_tpkフィールドの値
   * @@param p_type 検索対象であるp_typeフィールドの値
   * 
   * @@return 引数指定のp_tpk, p_type, and の値と一致する{@@link QtpRichPushHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static QtpRichPushHistoryRow findRowByTpkType( String p_tpk, String p_type ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "tpk=? and type=?",
            null,
            new Object[] { p_tpk, p_type } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QtpRichPushHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QtpRichPushHistoryDao.findRowsByTpkType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTpkType(String, String)}および{@@link #forRow(QtpRichPushHistoryRow)}を使用してください。 
   */
    public static QtpRichPushHistoryDao findDaoByTpkType( String p_tpk, String p_type ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTpkType( p_tpk, p_type ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and にて指定の値に一致する{@@link QtpRichPushHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_status, and の値と一致する{@@link QtpRichPushHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdStatus(long, String)}および{@@link #forRow(QtpRichPushHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }


  /** 
   * p_status, p_procTimestamp, p_type, and にて指定の値に一致する{@@link QtpRichPushHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_procTimestamp 検索対象であるp_procTimestampフィールドの値
   * @@param p_type 検索対象であるp_typeフィールドの値
   * 
   * @@return 引数指定のp_status, p_procTimestamp, p_type, and の値と一致する{@@link QtpRichPushHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatusProcTimestampType( String p_status, java.sql.Timestamp p_procTimestamp, String p_type ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "status=? and proc_timestamp=? and type=?",
            null,
            new Object[] { p_status, p_procTimestamp, p_type } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatusProcTimestampType(String, java.sql.Timestamp, String)}および{@@link #forRow(QtpRichPushHistoryRow)}を使用してください。 
   */
    public static List findDaosByStatusProcTimestampType( String p_status, java.sql.Timestamp p_procTimestamp, String p_type ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusProcTimestampType( p_status, p_procTimestamp, p_type ) );
    }


  /** 
   * p_type, p_createdTimestamp, and にて指定の値に一致する{@@link QtpRichPushHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_type 検索対象であるp_typeフィールドの値
   * @@param p_createdTimestamp 検索対象であるp_createdTimestampフィールドの値
   * 
   * @@return 引数指定のp_type, p_createdTimestamp, and の値と一致する{@@link QtpRichPushHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTypeCreatedTimestamp( String p_type, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QtpRichPushHistoryRow.TYPE,
            "type=? and created_timestamp=?",
            null,
            new Object[] { p_type, p_createdTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTypeCreatedTimestamp(String, java.sql.Timestamp)}および{@@link #forRow(QtpRichPushHistoryRow)}を使用してください。 
   */
    public static List findDaosByTypeCreatedTimestamp( String p_type, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTypeCreatedTimestamp( p_type, p_createdTimestamp ) );
    }

}
@
