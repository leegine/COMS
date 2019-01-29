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
filename	HostEqtypeOrderAllDao.java;


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
 * {@@link HostEqtypeOrderAllDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostEqtypeOrderAllRow}インスタンスへ関連付けることができます。 
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
 * @@see HostEqtypeOrderAllPK 
 * @@see HostEqtypeOrderAllRow 
 */
public class HostEqtypeOrderAllDao extends DataAccessObject {


  /** 
   * この{@@link HostEqtypeOrderAllDao}に関連する型指定のRowオブジェクト 
   */
    private HostEqtypeOrderAllRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostEqtypeOrderAllRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostEqtypeOrderAllDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostEqtypeOrderAllDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostEqtypeOrderAllRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEqtypeOrderAllRow )
                return new HostEqtypeOrderAllDao( (HostEqtypeOrderAllRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEqtypeOrderAllRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEqtypeOrderAllRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostEqtypeOrderAllRow}オブジェクト 
    */
    protected HostEqtypeOrderAllDao( HostEqtypeOrderAllRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostEqtypeOrderAllRow}オブジェクトを取得します。
   */
    public HostEqtypeOrderAllRow getHostEqtypeOrderAllRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostEqtypeOrderAllRow}オブジェクトから{@@link HostEqtypeOrderAllDao}オブジェクトを作成します。 
   * これは実際の{@@link HostEqtypeOrderAllRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostEqtypeOrderAllDao}取得のために指定の{@@link HostEqtypeOrderAllRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostEqtypeOrderAllDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostEqtypeOrderAllDao forRow( HostEqtypeOrderAllRow row ) throws java.lang.IllegalArgumentException {
        return (HostEqtypeOrderAllDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEqtypeOrderAllRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostEqtypeOrderAllRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostEqtypeOrderAllPK}やデータベースレコードとして挿入される{@@link HostEqtypeOrderAllParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEqtypeOrderAllRow.TYPE );
    }


  /** 
   * {@@link HostEqtypeOrderAllRow}を一意に特定する{@@link HostEqtypeOrderAllPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostEqtypeOrderAllRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostEqtypeOrderAllParams}オブジェクトの主キーとして利用可能な{@@link HostEqtypeOrderAllPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostEqtypeOrderAllPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostEqtypeOrderAllRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeOrderAllRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeOrderAllRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAllPK pk = new HostEqtypeOrderAllPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostEqtypeOrderAllPKオブジェクトから{@@link HostEqtypeOrderAllRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostEqtypeOrderAllPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeOrderAllRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeOrderAllRow findRowByPk( HostEqtypeOrderAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEqtypeOrderAllRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostEqtypeOrderAllRow)}を使用してください。 
   */
    public static HostEqtypeOrderAllDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAllPK pk = new HostEqtypeOrderAllPK( p_rowid );
        HostEqtypeOrderAllRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostEqtypeOrderAllPK)}および{@@link #forRow(HostEqtypeOrderAllRow)}を使用してください。 
   */
    public static HostEqtypeOrderAllDao findDaoByPk( HostEqtypeOrderAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderAllRow row = findRowByPk( pk );
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
   * p_orderActionSerialNo, p_orderRequestNumber, and にて指定の値に一致する{@@link HostEqtypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orderActionSerialNo 検索対象であるp_orderActionSerialNoフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_orderActionSerialNo, p_orderRequestNumber, and の値と一致する{@@link HostEqtypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrderActionSerialNoOrderRequestNumber( Integer p_orderActionSerialNo, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "order_action_serial_no=? and order_request_number=?",
            null,
            new Object[] { p_orderActionSerialNo, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrderActionSerialNoOrderRequestNumber(Integer, String)}および{@@link #forRow(HostEqtypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByOrderActionSerialNoOrderRequestNumber( Integer p_orderActionSerialNo, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrderActionSerialNoOrderRequestNumber( p_orderActionSerialNo, p_orderRequestNumber ) );
    }


  /** 
   * p_status, p_submitOrderRouteDiv, and にて指定の値に一致する{@@link HostEqtypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_submitOrderRouteDiv 検索対象であるp_submitOrderRouteDivフィールドの値
   * 
   * @@return 引数指定のp_status, p_submitOrderRouteDiv, and の値と一致する{@@link HostEqtypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatusSubmitOrderRouteDiv( String p_status, String p_submitOrderRouteDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "status=? and submit_order_route_div=?",
            null,
            new Object[] { p_status, p_submitOrderRouteDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatusSubmitOrderRouteDiv(String, String)}および{@@link #forRow(HostEqtypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByStatusSubmitOrderRouteDiv( String p_status, String p_submitOrderRouteDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusSubmitOrderRouteDiv( p_status, p_submitOrderRouteDiv ) );
    }


  /** 
   * p_corpCode, and にて指定の値に一致する{@@link HostEqtypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_corpCode 検索対象であるp_corpCodeフィールドの値
   * 
   * @@return 引数指定のp_corpCode, and の値と一致する{@@link HostEqtypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "corp_code=?",
            null,
            new Object[] { p_corpCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCorpCode(String)}および{@@link #forRow(HostEqtypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCorpCode( p_corpCode ) );
    }


  /** 
   * p_frontOrderExchangeCode, and にて指定の値に一致する{@@link HostEqtypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_frontOrderExchangeCode 検索対象であるp_frontOrderExchangeCodeフィールドの値
   * 
   * @@return 引数指定のp_frontOrderExchangeCode, and の値と一致する{@@link HostEqtypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByFrontOrderExchangeCode( String p_frontOrderExchangeCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "front_order_exchange_code=?",
            null,
            new Object[] { p_frontOrderExchangeCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByFrontOrderExchangeCode(String)}および{@@link #forRow(HostEqtypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByFrontOrderExchangeCode( String p_frontOrderExchangeCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByFrontOrderExchangeCode( p_frontOrderExchangeCode ) );
    }


  /** 
   * p_orgCorpCode, and にて指定の値に一致する{@@link HostEqtypeOrderAllRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_orgCorpCode 検索対象であるp_orgCorpCodeフィールドの値
   * 
   * @@return 引数指定のp_orgCorpCode, and の値と一致する{@@link HostEqtypeOrderAllRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByOrgCorpCode( String p_orgCorpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            "org_corp_code=?",
            null,
            new Object[] { p_orgCorpCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByOrgCorpCode(String)}および{@@link #forRow(HostEqtypeOrderAllRow)}を使用してください。 
   */
    public static List findDaosByOrgCorpCode( String p_orgCorpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByOrgCorpCode( p_orgCorpCode ) );
    }

}
@
