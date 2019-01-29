head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostOptionOrderExecNotifyDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link HostOptionOrderExecNotifyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostOptionOrderExecNotifyRow}インスタンスへ関連付けることができます。 
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
 * @@see HostOptionOrderExecNotifyPK 
 * @@see HostOptionOrderExecNotifyRow 
 */
public class HostOptionOrderExecNotifyDao extends DataAccessObject {


  /** 
   * この{@@link HostOptionOrderExecNotifyDao}に関連する型指定のRowオブジェクト 
   */
    private HostOptionOrderExecNotifyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostOptionOrderExecNotifyRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostOptionOrderExecNotifyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostOptionOrderExecNotifyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostOptionOrderExecNotifyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostOptionOrderExecNotifyRow )
                return new HostOptionOrderExecNotifyDao( (HostOptionOrderExecNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostOptionOrderExecNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostOptionOrderExecNotifyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostOptionOrderExecNotifyRow}オブジェクト 
    */
    protected HostOptionOrderExecNotifyDao( HostOptionOrderExecNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostOptionOrderExecNotifyRow}オブジェクトを取得します。
   */
    public HostOptionOrderExecNotifyRow getHostOptionOrderExecNotifyRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostOptionOrderExecNotifyRow}オブジェクトから{@@link HostOptionOrderExecNotifyDao}オブジェクトを作成します。 
   * これは実際の{@@link HostOptionOrderExecNotifyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostOptionOrderExecNotifyDao}取得のために指定の{@@link HostOptionOrderExecNotifyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostOptionOrderExecNotifyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostOptionOrderExecNotifyDao forRow( HostOptionOrderExecNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (HostOptionOrderExecNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostOptionOrderExecNotifyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostOptionOrderExecNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostOptionOrderExecNotifyPK}やデータベースレコードとして挿入される{@@link HostOptionOrderExecNotifyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostOptionOrderExecNotifyRow.TYPE );
    }


  /** 
   * {@@link HostOptionOrderExecNotifyRow}を一意に特定する{@@link HostOptionOrderExecNotifyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostOptionOrderExecNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostOptionOrderExecNotifyParams}オブジェクトの主キーとして利用可能な{@@link HostOptionOrderExecNotifyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostOptionOrderExecNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostOptionOrderExecNotifyRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostOptionOrderExecNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostOptionOrderExecNotifyRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOptionOrderExecNotifyPK pk = new HostOptionOrderExecNotifyPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostOptionOrderExecNotifyPKオブジェクトから{@@link HostOptionOrderExecNotifyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostOptionOrderExecNotifyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostOptionOrderExecNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostOptionOrderExecNotifyRow findRowByPk( HostOptionOrderExecNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostOptionOrderExecNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostOptionOrderExecNotifyRow)}を使用してください。 
   */
    public static HostOptionOrderExecNotifyDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOptionOrderExecNotifyPK pk = new HostOptionOrderExecNotifyPK( p_rowid );
        HostOptionOrderExecNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostOptionOrderExecNotifyPK)}および{@@link #forRow(HostOptionOrderExecNotifyRow)}を使用してください。 
   */
    public static HostOptionOrderExecNotifyDao findDaoByPk( HostOptionOrderExecNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOptionOrderExecNotifyRow row = findRowByPk( pk );
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
   * p_orderRequestNumber, and にて指定の値に一致する{@@link HostOptionOrderExecNotifyRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link HostOptionOrderExecNotifyRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostOptionOrderExecNotifyRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(HostOptionOrderExecNotifyRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }


  /** 
   * p_lastUpdatedTimestamp, p_status, and にて指定の値に一致する{@@link HostOptionOrderExecNotifyRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_lastUpdatedTimestamp 検索対象であるp_lastUpdatedTimestampフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_lastUpdatedTimestamp, p_status, and の値と一致する{@@link HostOptionOrderExecNotifyRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByLastUpdatedTimestampStatus( java.sql.Timestamp p_lastUpdatedTimestamp, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostOptionOrderExecNotifyRow.TYPE,
            "last_updated_timestamp=? and status=?",
            null,
            new Object[] { p_lastUpdatedTimestamp, p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByLastUpdatedTimestampStatus(java.sql.Timestamp, String)}および{@@link #forRow(HostOptionOrderExecNotifyRow)}を使用してください。 
   */
    public static List findDaosByLastUpdatedTimestampStatus( java.sql.Timestamp p_lastUpdatedTimestamp, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByLastUpdatedTimestampStatus( p_lastUpdatedTimestamp, p_status ) );
    }


  /** 
   * p_status, and にて指定の値に一致する{@@link HostOptionOrderExecNotifyRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_status, and の値と一致する{@@link HostOptionOrderExecNotifyRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostOptionOrderExecNotifyRow.TYPE,
            "status=?",
            null,
            new Object[] { p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatus(String)}および{@@link #forRow(HostOptionOrderExecNotifyRow)}を使用してください。 
   */
    public static List findDaosByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatus( p_status ) );
    }

}
@
