head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFotypeOrderAllDao.java;


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
 * {@@link HostFotypeOrderAllDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostFotypeOrderAllRow}インスタンスへ関連付けることができます。 
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
 * @@see HostFotypeOrderAllPK 
 * @@see HostFotypeOrderAllRow 
 */
public class HostFotypeOrderAllDao extends DataAccessObject {


  /** 
   * この{@@link HostFotypeOrderAllDao}に関連する型指定のRowオブジェクト 
   */
    private HostFotypeOrderAllRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostFotypeOrderAllRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostFotypeOrderAllDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostFotypeOrderAllDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostFotypeOrderAllRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFotypeOrderAllRow )
                return new HostFotypeOrderAllDao( (HostFotypeOrderAllRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFotypeOrderAllRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFotypeOrderAllRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostFotypeOrderAllRow}オブジェクト 
    */
    protected HostFotypeOrderAllDao( HostFotypeOrderAllRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostFotypeOrderAllRow}オブジェクトを取得します。
   */
    public HostFotypeOrderAllRow getHostFotypeOrderAllRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostFotypeOrderAllRow}オブジェクトから{@@link HostFotypeOrderAllDao}オブジェクトを作成します。 
   * これは実際の{@@link HostFotypeOrderAllRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostFotypeOrderAllDao}取得のために指定の{@@link HostFotypeOrderAllRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostFotypeOrderAllDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostFotypeOrderAllDao forRow( HostFotypeOrderAllRow row ) throws java.lang.IllegalArgumentException {
        return (HostFotypeOrderAllDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFotypeOrderAllRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostFotypeOrderAllRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostFotypeOrderAllPK}やデータベースレコードとして挿入される{@@link HostFotypeOrderAllParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFotypeOrderAllRow.TYPE );
    }


  /** 
   * {@@link HostFotypeOrderAllRow}を一意に特定する{@@link HostFotypeOrderAllPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostFotypeOrderAllRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostFotypeOrderAllParams}オブジェクトの主キーとして利用可能な{@@link HostFotypeOrderAllPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostFotypeOrderAllPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostFotypeOrderAllRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFotypeOrderAllRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFotypeOrderAllRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderAllPK pk = new HostFotypeOrderAllPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostFotypeOrderAllPKオブジェクトから{@@link HostFotypeOrderAllRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostFotypeOrderAllPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostFotypeOrderAllRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostFotypeOrderAllRow findRowByPk( HostFotypeOrderAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFotypeOrderAllRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostFotypeOrderAllRow)}を使用してください。 
   */
    public static HostFotypeOrderAllDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderAllPK pk = new HostFotypeOrderAllPK( p_rowid );
        HostFotypeOrderAllRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostFotypeOrderAllPK)}および{@@link #forRow(HostFotypeOrderAllRow)}を使用してください。 
   */
    public static HostFotypeOrderAllDao findDaoByPk( HostFotypeOrderAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderAllRow row = findRowByPk( pk );
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
   * p_status, p_submitOrderRouteDiv, and にて指定の値に一致する{@@link HostFotypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_submitOrderRouteDiv 検索対象であるp_submitOrderRouteDivフィールドの値
   * 
   * @@return 引数指定のp_status, p_submitOrderRouteDiv, and の値と一致する{@@link HostFotypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatusSubmitOrderRouteDiv( String p_status, String p_submitOrderRouteDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "status=? and submit_order_route_div=?",
            null,
            new Object[] { p_status, p_submitOrderRouteDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatusSubmitOrderRouteDiv(String, String)}および{@@link #forRow(HostFotypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByStatusSubmitOrderRouteDiv( String p_status, String p_submitOrderRouteDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusSubmitOrderRouteDiv( p_status, p_submitOrderRouteDiv ) );
    }


  /** 
   * p_corpCode, and にて指定の値に一致する{@@link HostFotypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_corpCode 検索対象であるp_corpCodeフィールドの値
   * 
   * @@return 引数指定のp_corpCode, and の値と一致する{@@link HostFotypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "corp_code=?",
            null,
            new Object[] { p_corpCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCorpCode(String)}および{@@link #forRow(HostFotypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCorpCode( p_corpCode ) );
    }


  /** 
   * p_accountId, p_orderRequestNumber, and にて指定の値に一致する{@@link HostFotypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_orderRequestNumber, and の値と一致する{@@link HostFotypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdOrderRequestNumber( Long p_accountId, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "account_id=? and order_request_number=?",
            null,
            new Object[] { p_accountId, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdOrderRequestNumber(Long, String)}および{@@link #forRow(HostFotypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByAccountIdOrderRequestNumber( Long p_accountId, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdOrderRequestNumber( p_accountId, p_orderRequestNumber ) );
    }


  /** 
   * p_orderRequestNumber, and にて指定の値に一致する{@@link HostFotypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderRequestNumber, and の値と一致する{@@link HostFotypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "order_request_number=?",
            null,
            new Object[] { p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderRequestNumber(String)}および{@@link #forRow(HostFotypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByOrderRequestNumber( String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderRequestNumber( p_orderRequestNumber ) );
    }


  /** 
   * p_orgCorpCode, and にて指定の値に一致する{@@link HostFotypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orgCorpCode 検索対象であるp_orgCorpCodeフィールドの値
   * 
   * @@return 引数指定のp_orgCorpCode, and の値と一致する{@@link HostFotypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrgCorpCode( String p_orgCorpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            "org_corp_code=?",
            null,
            new Object[] { p_orgCorpCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrgCorpCode(String)}および{@@link #forRow(HostFotypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByOrgCorpCode( String p_orgCorpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrgCorpCode( p_orgCorpCode ) );
    }

}
@
