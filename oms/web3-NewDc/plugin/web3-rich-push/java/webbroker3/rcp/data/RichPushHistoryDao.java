head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rcp.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RichPushHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RichPushHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see RichPushHistoryPK 
 * @@see RichPushHistoryRow 
 */
public class RichPushHistoryDao extends DataAccessObject {


  /** 
   * この{@@link RichPushHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private RichPushHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RichPushHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link RichPushHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RichPushHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RichPushHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RichPushHistoryRow )
                return new RichPushHistoryDao( (RichPushHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RichPushHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RichPushHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RichPushHistoryRow}オブジェクト 
    */
    protected RichPushHistoryDao( RichPushHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RichPushHistoryRow}オブジェクトを取得します。
   */
    public RichPushHistoryRow getRichPushHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link RichPushHistoryRow}オブジェクトから{@@link RichPushHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link RichPushHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RichPushHistoryDao}取得のために指定の{@@link RichPushHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RichPushHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RichPushHistoryDao forRow( RichPushHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (RichPushHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RichPushHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RichPushHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RichPushHistoryPK}やデータベースレコードとして挿入される{@@link RichPushHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RichPushHistoryRow.TYPE );
    }


  /** 
   * {@@link RichPushHistoryRow}を一意に特定する{@@link RichPushHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RichPushHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RichPushHistoryParams}オブジェクトの主キーとして利用可能な{@@link RichPushHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RichPushHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RichPushHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RichPushHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RichPushHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RichPushHistoryRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryPK pk = new RichPushHistoryPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRichPushHistoryPKオブジェクトから{@@link RichPushHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRichPushHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RichPushHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RichPushHistoryRow findRowByPk( RichPushHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RichPushHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RichPushHistoryRow)}を使用してください。 
   */
    public static RichPushHistoryDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryPK pk = new RichPushHistoryPK( p_serlnum );
        RichPushHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RichPushHistoryPK)}および{@@link #forRow(RichPushHistoryRow)}を使用してください。 
   */
    public static RichPushHistoryDao findDaoByPk( RichPushHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryRow row = findRowByPk( pk );
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
   * p_serlnum, and にて指定の値から一意の{@@link RichPushHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のp_serlnum, and の値と一致する{@@link RichPushHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RichPushHistoryRow findRowBySerlnum( long p_serlnum ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "serlnum=?",
            null,
            new Object[] { new Long(p_serlnum) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RichPushHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RichPushHistoryDao.findRowsBySerlnum(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowBySerlnum(long)}および{@@link #forRow(RichPushHistoryRow)}を使用してください。 
   */
    public static RichPushHistoryDao findDaoBySerlnum( long p_serlnum ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowBySerlnum( p_serlnum ) );
    }


  /** 
   * p_tpk, p_type, and にて指定の値から一意の{@@link RichPushHistoryRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_tpk 検索対象であるp_tpkフィールドの値
   * @@param p_type 検索対象であるp_typeフィールドの値
   * 
   * @@return 引数指定のp_tpk, p_type, and の値と一致する{@@link RichPushHistoryRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RichPushHistoryRow findRowByTpkType( String p_tpk, String p_type ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "tpk=? and type=?",
            null,
            new Object[] { p_tpk, p_type } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RichPushHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RichPushHistoryDao.findRowsByTpkType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTpkType(String, String)}および{@@link #forRow(RichPushHistoryRow)}を使用してください。 
   */
    public static RichPushHistoryDao findDaoByTpkType( String p_tpk, String p_type ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTpkType( p_tpk, p_type ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_status, and にて指定の値に一致する{@@link RichPushHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_status, and の値と一致する{@@link RichPushHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "account_id=? and status=?",
            null,
            new Object[] { new Long(p_accountId), p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdStatus(long, String)}および{@@link #forRow(RichPushHistoryRow)}を使用してください。 
   */
    public static List findDaosByAccountIdStatus( long p_accountId, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdStatus( p_accountId, p_status ) );
    }


  /** 
   * p_status, p_procTimestamp, p_type, and にて指定の値に一致する{@@link RichPushHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_procTimestamp 検索対象であるp_procTimestampフィールドの値
   * @@param p_type 検索対象であるp_typeフィールドの値
   * 
   * @@return 引数指定のp_status, p_procTimestamp, p_type, and の値と一致する{@@link RichPushHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatusProcTimestampType( String p_status, java.sql.Timestamp p_procTimestamp, String p_type ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "status=? and proc_timestamp=? and type=?",
            null,
            new Object[] { p_status, p_procTimestamp, p_type } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatusProcTimestampType(String, java.sql.Timestamp, String)}および{@@link #forRow(RichPushHistoryRow)}を使用してください。 
   */
    public static List findDaosByStatusProcTimestampType( String p_status, java.sql.Timestamp p_procTimestamp, String p_type ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusProcTimestampType( p_status, p_procTimestamp, p_type ) );
    }


  /** 
   * p_type, p_createdTimestamp, and にて指定の値に一致する{@@link RichPushHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_type 検索対象であるp_typeフィールドの値
   * @@param p_createdTimestamp 検索対象であるp_createdTimestampフィールドの値
   * 
   * @@return 引数指定のp_type, p_createdTimestamp, and の値と一致する{@@link RichPushHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTypeCreatedTimestamp( String p_type, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RichPushHistoryRow.TYPE,
            "type=? and created_timestamp=?",
            null,
            new Object[] { p_type, p_createdTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTypeCreatedTimestamp(String, java.sql.Timestamp)}および{@@link #forRow(RichPushHistoryRow)}を使用してください。 
   */
    public static List findDaosByTypeCreatedTimestamp( String p_type, java.sql.Timestamp p_createdTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTypeCreatedTimestamp( p_type, p_createdTimestamp ) );
    }

}
@
