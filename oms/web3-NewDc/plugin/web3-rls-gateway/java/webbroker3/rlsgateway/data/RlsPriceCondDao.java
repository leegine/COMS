head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsPriceCondDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rlsgateway.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RlsPriceCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RlsPriceCondRow}インスタンスへ関連付けることができます。 
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
 * @@see RlsPriceCondPK 
 * @@see RlsPriceCondRow 
 */
public class RlsPriceCondDao extends DataAccessObject {


  /** 
   * この{@@link RlsPriceCondDao}に関連する型指定のRowオブジェクト 
   */
    private RlsPriceCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RlsPriceCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link RlsPriceCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RlsPriceCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RlsPriceCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsPriceCondRow )
                return new RlsPriceCondDao( (RlsPriceCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsPriceCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsPriceCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RlsPriceCondRow}オブジェクト 
    */
    protected RlsPriceCondDao( RlsPriceCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RlsPriceCondRow}オブジェクトを取得します。
   */
    public RlsPriceCondRow getRlsPriceCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link RlsPriceCondRow}オブジェクトから{@@link RlsPriceCondDao}オブジェクトを作成します。 
   * これは実際の{@@link RlsPriceCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RlsPriceCondDao}取得のために指定の{@@link RlsPriceCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RlsPriceCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RlsPriceCondDao forRow( RlsPriceCondRow row ) throws java.lang.IllegalArgumentException {
        return (RlsPriceCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsPriceCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RlsPriceCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RlsPriceCondPK}やデータベースレコードとして挿入される{@@link RlsPriceCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsPriceCondRow.TYPE );
    }


  /** 
   * {@@link RlsPriceCondRow}を一意に特定する{@@link RlsPriceCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RlsPriceCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RlsPriceCondParams}オブジェクトの主キーとして利用可能な{@@link RlsPriceCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RlsPriceCondPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RlsPriceCondPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RlsPriceCondRow}オブジェクトを検索します。 
   * 
   * @@param p_condOrdId 検索対象であるp_condOrdIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsPriceCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsPriceCondRow findRowByPk( long p_condOrdId ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsPriceCondPK pk = new RlsPriceCondPK( p_condOrdId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRlsPriceCondPKオブジェクトから{@@link RlsPriceCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRlsPriceCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsPriceCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsPriceCondRow findRowByPk( RlsPriceCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsPriceCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RlsPriceCondRow)}を使用してください。 
   */
    public static RlsPriceCondDao findDaoByPk( long p_condOrdId ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsPriceCondPK pk = new RlsPriceCondPK( p_condOrdId );
        RlsPriceCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RlsPriceCondPK)}および{@@link #forRow(RlsPriceCondRow)}を使用してください。 
   */
    public static RlsPriceCondDao findDaoByPk( RlsPriceCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsPriceCondRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link RlsPriceCondDao}に紐付く{@@link RlsPriceCondRow}内で外部キーの関係をもつ{@@link RlsCondOrderRow}を検索します。 
   * 
   * @@return {@@link RlsPriceCondDao}と外部キーの関係にある{@@link RlsCondOrderRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public RlsCondOrderRow fetchRlsCondOrderRowViaCondOrdId() throws DataNetworkException, DataFindException, DataQueryException  {
        RlsCondOrderPK pk = new RlsCondOrderPK( row.getCondOrdId() );
        Row row = RlsCondOrderDao.findRowByPk( pk );
        if ( row != null && !(row instanceof RlsCondOrderRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (RlsCondOrderRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRlsCondOrderRowViaCondOrdId()}および{@@link #forRow(RlsPriceCondRow)}を使用してください。 
   */
    public RlsCondOrderDao fetchRlsCondOrderDaoViaCondOrdId() throws DataNetworkException, DataFindException, DataQueryException  {
        RlsCondOrderPK pk = new RlsCondOrderPK( row.getCondOrdId() );
        DataAccessObject dao = RlsCondOrderDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof RlsCondOrderDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (RlsCondOrderDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for RlsCondOrder
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByCondOrdId(RlsCondOrderRow)}を使用してください。 
   */
    public static List findRowsByCondOrdId( RlsCondOrderDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByCondOrdId( dao.getRlsCondOrderRow() );
    }


  /** 
   * {@@link RlsCondOrderRow}と外部キーの関係にある{@@link RlsPriceCondRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RlsCondOrderRow}オブジェクト 
   * @@return 指定の{@@link RlsCondOrderRow}に外部キーを持つ{@@link RlsPriceCondRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCondOrdId( RlsCondOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByCondOrdId( row.getId() );
    }


  /** 
   * {@@link RlsCondOrderPK}と外部キーの関係にある{@@link RlsPriceCondRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RlsCondOrderPK}オブジェクト 
   * @@return {@@link RlsCondOrderPK}と外部キーが一致する値を持つ{@@link RlsPriceCondRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCondOrdId( RlsCondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByCondOrdId( pk.id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RlsPriceCondRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_id 検索対象であるp_idフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RlsPriceCondRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCondOrdId( long p_id  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsPriceCondRow.TYPE,
            "cond_ord_id=?",
            null,
            new Object[] { new Long(p_id) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RlsCondOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByCondOrdId(RlsCondOrderRow)}および{@@link #forRow(RlsPriceCondRow)}を使用してください。 
   */
    public static List findDaosByCondOrdId( RlsCondOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCondOrdId(RlsCondOrderRow)}および{@@link #forRow(RlsPriceCondRow)}を使用してください。 
   */
    public static List findDaosByCondOrdId( RlsCondOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCondOrdId(RlsCondOrderPK)}および{@@link #forRow(RlsPriceCondRow)}を使用してください。 
   */
    public static List findDaosByCondOrdId( RlsCondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( pk.id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCondOrdId(long)}および{@@link #forRow(RlsPriceCondRow)}を使用してください。 
   */
    public static List findDaosByCondOrdId( long p_id ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( p_id ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_condOrdId, and にて指定の値から一意の{@@link RlsPriceCondRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_condOrdId 検索対象であるp_condOrdIdフィールドの値
   * 
   * @@return 引数指定のp_condOrdId, and の値と一致する{@@link RlsPriceCondRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RlsPriceCondRow findRowByCondOrdId( long p_condOrdId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsPriceCondRow.TYPE,
            "cond_ord_id=?",
            null,
            new Object[] { new Long(p_condOrdId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsPriceCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsPriceCondDao.findRowsByCondOrdId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByCondOrdId(long)}および{@@link #forRow(RlsPriceCondRow)}を使用してください。 
   */
    public static RlsPriceCondDao findDaoByCondOrdId( long p_condOrdId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCondOrdId( p_condOrdId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
