head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeOrderAcceptDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link HostEqtypeOrderAcceptDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostEqtypeOrderAcceptRow}インスタンスへ関連付けることができます。 
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
 * @@see HostEqtypeOrderAcceptPK 
 * @@see HostEqtypeOrderAcceptRow 
 */
public class HostEqtypeOrderAcceptDao extends DataAccessObject {


  /** 
   * この{@@link HostEqtypeOrderAcceptDao}に関連する型指定のRowオブジェクト 
   */
    private HostEqtypeOrderAcceptRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostEqtypeOrderAcceptRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostEqtypeOrderAcceptDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostEqtypeOrderAcceptDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostEqtypeOrderAcceptRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEqtypeOrderAcceptRow )
                return new HostEqtypeOrderAcceptDao( (HostEqtypeOrderAcceptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEqtypeOrderAcceptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEqtypeOrderAcceptRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostEqtypeOrderAcceptRow}オブジェクト 
    */
    protected HostEqtypeOrderAcceptDao( HostEqtypeOrderAcceptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostEqtypeOrderAcceptRow}オブジェクトを取得します。
   */
    public HostEqtypeOrderAcceptRow getHostEqtypeOrderAcceptRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostEqtypeOrderAcceptRow}オブジェクトから{@@link HostEqtypeOrderAcceptDao}オブジェクトを作成します。 
   * これは実際の{@@link HostEqtypeOrderAcceptRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostEqtypeOrderAcceptDao}取得のために指定の{@@link HostEqtypeOrderAcceptRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostEqtypeOrderAcceptDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostEqtypeOrderAcceptDao forRow( HostEqtypeOrderAcceptRow row ) throws java.lang.IllegalArgumentException {
        return (HostEqtypeOrderAcceptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEqtypeOrderAcceptRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostEqtypeOrderAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostEqtypeOrderAcceptPK}やデータベースレコードとして挿入される{@@link HostEqtypeOrderAcceptParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEqtypeOrderAcceptRow.TYPE );
    }


  /** 
   * {@@link HostEqtypeOrderAcceptRow}を一意に特定する{@@link HostEqtypeOrderAcceptPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostEqtypeOrderAcceptRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostEqtypeOrderAcceptParams}オブジェクトの主キーとして利用可能な{@@link HostEqtypeOrderAcceptPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostEqtypeOrderAcceptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostEqtypeOrderAcceptRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeOrderAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeOrderAcceptRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAcceptPK pk = new HostEqtypeOrderAcceptPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostEqtypeOrderAcceptPKオブジェクトから{@@link HostEqtypeOrderAcceptRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostEqtypeOrderAcceptPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeOrderAcceptRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeOrderAcceptRow findRowByPk( HostEqtypeOrderAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEqtypeOrderAcceptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostEqtypeOrderAcceptRow)}を使用してください。 
   */
    public static HostEqtypeOrderAcceptDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAcceptPK pk = new HostEqtypeOrderAcceptPK( p_rowid );
        HostEqtypeOrderAcceptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostEqtypeOrderAcceptPK)}および{@@link #forRow(HostEqtypeOrderAcceptRow)}を使用してください。 
   */
    public static HostEqtypeOrderAcceptDao findDaoByPk( HostEqtypeOrderAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAcceptRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_requestCode, p_status, and にて指定の値に一致する{@@link HostEqtypeOrderAcceptRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_status, and の値と一致する{@@link HostEqtypeOrderAcceptRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAcceptRow.TYPE,
            "request_code=? and status=?",
            null,
            new Object[] { p_requestCode, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeStatus(String, String)}および{@@link #forRow(HostEqtypeOrderAcceptRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeStatus( String p_requestCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeStatus( p_requestCode, p_status ) );
    }


  /** 
   * p_orderRequestNumber, and にて指定の値に一致する{@@link HostEqtypeOrderAcceptRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link HostEqtypeOrderAcceptRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAcceptRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(HostEqtypeOrderAcceptRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }


  /** 
   * p_lastUpdatedTimestamp, p_status, and にて指定の値に一致する{@@link HostEqtypeOrderAcceptRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_lastUpdatedTimestamp 検索対象であるp_lastUpdatedTimestampフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_lastUpdatedTimestamp, p_status, and の値と一致する{@@link HostEqtypeOrderAcceptRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByLastUpdatedTimestampStatus( java.sql.Timestamp p_lastUpdatedTimestamp, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAcceptRow.TYPE,
            "last_updated_timestamp=? and status=?",
            null,
            new Object[] { p_lastUpdatedTimestamp, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByLastUpdatedTimestampStatus(java.sql.Timestamp, String)}および{@@link #forRow(HostEqtypeOrderAcceptRow)}を使用してください。 
   */
    public static List findDaosByLastUpdatedTimestampStatus( java.sql.Timestamp p_lastUpdatedTimestamp, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByLastUpdatedTimestampStatus( p_lastUpdatedTimestamp, p_status ) );
    }

}
@
