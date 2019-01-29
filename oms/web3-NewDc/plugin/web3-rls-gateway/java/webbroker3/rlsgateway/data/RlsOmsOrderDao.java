head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOmsOrderDao.java;


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
 * {@@link RlsOmsOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RlsOmsOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see RlsOmsOrderPK 
 * @@see RlsOmsOrderRow 
 */
public class RlsOmsOrderDao extends DataAccessObject {


  /** 
   * この{@@link RlsOmsOrderDao}に関連する型指定のRowオブジェクト 
   */
    private RlsOmsOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RlsOmsOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link RlsOmsOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RlsOmsOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RlsOmsOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsOmsOrderRow )
                return new RlsOmsOrderDao( (RlsOmsOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsOmsOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsOmsOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RlsOmsOrderRow}オブジェクト 
    */
    protected RlsOmsOrderDao( RlsOmsOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RlsOmsOrderRow}オブジェクトを取得します。
   */
    public RlsOmsOrderRow getRlsOmsOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link RlsOmsOrderRow}オブジェクトから{@@link RlsOmsOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link RlsOmsOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RlsOmsOrderDao}取得のために指定の{@@link RlsOmsOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RlsOmsOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RlsOmsOrderDao forRow( RlsOmsOrderRow row ) throws java.lang.IllegalArgumentException {
        return (RlsOmsOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsOmsOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RlsOmsOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RlsOmsOrderPK}やデータベースレコードとして挿入される{@@link RlsOmsOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsOmsOrderRow.TYPE );
    }


  /** 
   * {@@link RlsOmsOrderRow}を一意に特定する{@@link RlsOmsOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RlsOmsOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RlsOmsOrderParams}オブジェクトの主キーとして利用可能な{@@link RlsOmsOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RlsOmsOrderPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RlsOmsOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_ordId 検索対象であるp_ordIdフィールドの値
   * @@param p_ordType 検索対象であるp_ordTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsOmsOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsOmsOrderRow findRowByPk( long p_ordId, long p_ordType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOmsOrderPK pk = new RlsOmsOrderPK( p_ordId, p_ordType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRlsOmsOrderPKオブジェクトから{@@link RlsOmsOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRlsOmsOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsOmsOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsOmsOrderRow findRowByPk( RlsOmsOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsOmsOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,long)}および{@@link #forRow(RlsOmsOrderRow)}を使用してください。 
   */
    public static RlsOmsOrderDao findDaoByPk( long p_ordId, long p_ordType ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOmsOrderPK pk = new RlsOmsOrderPK( p_ordId, p_ordType );
        RlsOmsOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RlsOmsOrderPK)}および{@@link #forRow(RlsOmsOrderRow)}を使用してください。 
   */
    public static RlsOmsOrderDao findDaoByPk( RlsOmsOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsOmsOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link RlsOmsOrderDao}に紐付く{@@link RlsOmsOrderRow}内で外部キーの関係をもつ{@@link RlsCondOrderRow}を検索します。 
   * 
   * @@return {@@link RlsOmsOrderDao}と外部キーの関係にある{@@link RlsCondOrderRow} 
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
   * @@deprecated 代わりに{@@link #fetchRlsCondOrderRowViaCondOrdId()}および{@@link #forRow(RlsOmsOrderRow)}を使用してください。 
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
   * {@@link RlsCondOrderRow}と外部キーの関係にある{@@link RlsOmsOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link RlsCondOrderRow}オブジェクト 
   * @@return 指定の{@@link RlsCondOrderRow}に外部キーを持つ{@@link RlsOmsOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCondOrdId( RlsCondOrderRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByCondOrdId( row.getId() );
    }


  /** 
   * {@@link RlsCondOrderPK}と外部キーの関係にある{@@link RlsOmsOrderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link RlsCondOrderPK}オブジェクト 
   * @@return {@@link RlsCondOrderPK}と外部キーが一致する値を持つ{@@link RlsOmsOrderRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCondOrdId( RlsCondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByCondOrdId( pk.id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RlsOmsOrderRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_id 検索対象であるp_idフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RlsOmsOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCondOrdId( long p_id  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsOmsOrderRow.TYPE,
            "cond_ord_id=?",
            null,
            new Object[] { new Long(p_id) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for RlsCondOrder
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByCondOrdId(RlsCondOrderRow)}および{@@link #forRow(RlsOmsOrderRow)}を使用してください。 
   */
    public static List findDaosByCondOrdId( RlsCondOrderDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCondOrdId(RlsCondOrderRow)}および{@@link #forRow(RlsOmsOrderRow)}を使用してください。 
   */
    public static List findDaosByCondOrdId( RlsCondOrderRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCondOrdId(RlsCondOrderPK)}および{@@link #forRow(RlsOmsOrderRow)}を使用してください。 
   */
    public static List findDaosByCondOrdId( RlsCondOrderPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCondOrdId( pk.id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCondOrdId(long)}および{@@link #forRow(RlsOmsOrderRow)}を使用してください。 
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
   * p_ordId, p_ordType, and にて指定の値から一意の{@@link RlsOmsOrderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_ordId 検索対象であるp_ordIdフィールドの値
   * @@param p_ordType 検索対象であるp_ordTypeフィールドの値
   * 
   * @@return 引数指定のp_ordId, p_ordType, and の値と一致する{@@link RlsOmsOrderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RlsOmsOrderRow findRowByOrdIdOrdType( long p_ordId, long p_ordType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsOmsOrderRow.TYPE,
            "ord_id=? and ord_type=?",
            null,
            new Object[] { new Long(p_ordId), new Long(p_ordType) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsOmsOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsOmsOrderDao.findRowsByOrdIdOrdType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByOrdIdOrdType(long, long)}および{@@link #forRow(RlsOmsOrderRow)}を使用してください。 
   */
    public static RlsOmsOrderDao findDaoByOrdIdOrdType( long p_ordId, long p_ordType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByOrdIdOrdType( p_ordId, p_ordType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
