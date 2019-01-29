head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEquityOrderExecNotifyDao.java;


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
 * {@@link HostEquityOrderExecNotifyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostEquityOrderExecNotifyRow}インスタンスへ関連付けることができます。 
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
 * @@see HostEquityOrderExecNotifyPK 
 * @@see HostEquityOrderExecNotifyRow 
 */
public class HostEquityOrderExecNotifyDao extends DataAccessObject {


  /** 
   * この{@@link HostEquityOrderExecNotifyDao}に関連する型指定のRowオブジェクト 
   */
    private HostEquityOrderExecNotifyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostEquityOrderExecNotifyRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostEquityOrderExecNotifyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostEquityOrderExecNotifyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostEquityOrderExecNotifyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEquityOrderExecNotifyRow )
                return new HostEquityOrderExecNotifyDao( (HostEquityOrderExecNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEquityOrderExecNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEquityOrderExecNotifyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostEquityOrderExecNotifyRow}オブジェクト 
    */
    protected HostEquityOrderExecNotifyDao( HostEquityOrderExecNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostEquityOrderExecNotifyRow}オブジェクトを取得します。
   */
    public HostEquityOrderExecNotifyRow getHostEquityOrderExecNotifyRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostEquityOrderExecNotifyRow}オブジェクトから{@@link HostEquityOrderExecNotifyDao}オブジェクトを作成します。 
   * これは実際の{@@link HostEquityOrderExecNotifyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostEquityOrderExecNotifyDao}取得のために指定の{@@link HostEquityOrderExecNotifyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostEquityOrderExecNotifyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostEquityOrderExecNotifyDao forRow( HostEquityOrderExecNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (HostEquityOrderExecNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEquityOrderExecNotifyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostEquityOrderExecNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostEquityOrderExecNotifyPK}やデータベースレコードとして挿入される{@@link HostEquityOrderExecNotifyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEquityOrderExecNotifyRow.TYPE );
    }


  /** 
   * {@@link HostEquityOrderExecNotifyRow}を一意に特定する{@@link HostEquityOrderExecNotifyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostEquityOrderExecNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostEquityOrderExecNotifyParams}オブジェクトの主キーとして利用可能な{@@link HostEquityOrderExecNotifyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostEquityOrderExecNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostEquityOrderExecNotifyRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEquityOrderExecNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEquityOrderExecNotifyRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEquityOrderExecNotifyPK pk = new HostEquityOrderExecNotifyPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostEquityOrderExecNotifyPKオブジェクトから{@@link HostEquityOrderExecNotifyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostEquityOrderExecNotifyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEquityOrderExecNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEquityOrderExecNotifyRow findRowByPk( HostEquityOrderExecNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEquityOrderExecNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostEquityOrderExecNotifyRow)}を使用してください。 
   */
    public static HostEquityOrderExecNotifyDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEquityOrderExecNotifyPK pk = new HostEquityOrderExecNotifyPK( p_rowid );
        HostEquityOrderExecNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostEquityOrderExecNotifyPK)}および{@@link #forRow(HostEquityOrderExecNotifyRow)}を使用してください。 
   */
    public static HostEquityOrderExecNotifyDao findDaoByPk( HostEquityOrderExecNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEquityOrderExecNotifyRow row = findRowByPk( pk );
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
   * p_orderRequestNumber, and にて指定の値に一致する{@@link HostEquityOrderExecNotifyRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link HostEquityOrderExecNotifyRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEquityOrderExecNotifyRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(HostEquityOrderExecNotifyRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }


  /** 
   * p_lastUpdatedTimestamp, p_status, and にて指定の値に一致する{@@link HostEquityOrderExecNotifyRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_lastUpdatedTimestamp 検索対象であるp_lastUpdatedTimestampフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_lastUpdatedTimestamp, p_status, and の値と一致する{@@link HostEquityOrderExecNotifyRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByLastUpdatedTimestampStatus( java.sql.Timestamp p_lastUpdatedTimestamp, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEquityOrderExecNotifyRow.TYPE,
            "last_updated_timestamp=? and status=?",
            null,
            new Object[] { p_lastUpdatedTimestamp, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByLastUpdatedTimestampStatus(java.sql.Timestamp, String)}および{@@link #forRow(HostEquityOrderExecNotifyRow)}を使用してください。 
   */
    public static List findDaosByLastUpdatedTimestampStatus( java.sql.Timestamp p_lastUpdatedTimestamp, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByLastUpdatedTimestampStatus( p_lastUpdatedTimestamp, p_status ) );
    }


  /** 
   * p_status, and にて指定の値に一致する{@@link HostEquityOrderExecNotifyRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_status, and の値と一致する{@@link HostEquityOrderExecNotifyRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEquityOrderExecNotifyRow.TYPE,
            "status=?",
            null,
            new Object[] { p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatus(String)}および{@@link #forRow(HostEquityOrderExecNotifyRow)}を使用してください。 
   */
    public static List findDaosByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatus( p_status ) );
    }

}
@
