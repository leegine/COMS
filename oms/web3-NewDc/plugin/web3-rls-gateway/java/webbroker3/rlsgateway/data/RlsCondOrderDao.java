head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsCondOrderDao.java;


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
 * {@@link RlsCondOrderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RlsCondOrderRow}インスタンスへ関連付けることができます。 
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
 * @@see RlsCondOrderPK 
 * @@see RlsCondOrderRow 
 */
public class RlsCondOrderDao extends DataAccessObject {


  /** 
   * この{@@link RlsCondOrderDao}に関連する型指定のRowオブジェクト 
   */
    private RlsCondOrderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RlsCondOrderRow}と仮定される{@@link DataAccessObject}から新たに{@@link RlsCondOrderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RlsCondOrderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RlsCondOrderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsCondOrderRow )
                return new RlsCondOrderDao( (RlsCondOrderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsCondOrderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsCondOrderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RlsCondOrderRow}オブジェクト 
    */
    protected RlsCondOrderDao( RlsCondOrderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RlsCondOrderRow}オブジェクトを取得します。
   */
    public RlsCondOrderRow getRlsCondOrderRow() {
        return row;
    }


  /** 
   * 指定の{@@link RlsCondOrderRow}オブジェクトから{@@link RlsCondOrderDao}オブジェクトを作成します。 
   * これは実際の{@@link RlsCondOrderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RlsCondOrderDao}取得のために指定の{@@link RlsCondOrderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RlsCondOrderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RlsCondOrderDao forRow( RlsCondOrderRow row ) throws java.lang.IllegalArgumentException {
        return (RlsCondOrderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsCondOrderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RlsCondOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RlsCondOrderPK}やデータベースレコードとして挿入される{@@link RlsCondOrderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsCondOrderRow.TYPE );
    }


  /** 
   * {@@link RlsCondOrderRow}を一意に特定する{@@link RlsCondOrderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RlsCondOrderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RlsCondOrderParams}オブジェクトの主キーとして利用可能な{@@link RlsCondOrderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RlsCondOrderPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RlsCondOrderPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RlsCondOrderRow}オブジェクトを検索します。 
   * 
   * @@param p_id 検索対象であるp_idフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsCondOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsCondOrderRow findRowByPk( long p_id ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsCondOrderPK pk = new RlsCondOrderPK( p_id );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRlsCondOrderPKオブジェクトから{@@link RlsCondOrderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRlsCondOrderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsCondOrderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsCondOrderRow findRowByPk( RlsCondOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsCondOrderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public static RlsCondOrderDao findDaoByPk( long p_id ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsCondOrderPK pk = new RlsCondOrderPK( p_id );
        RlsCondOrderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RlsCondOrderPK)}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public static RlsCondOrderDao findDaoByPk( RlsCondOrderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsCondOrderRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link RlsCondOrderDao}に関連する{@@link RlsCondOrderRow}の外部キーがある{@@link RlsOmsOrderRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RlsCondOrderDao}に関連する{@@link RlsCondOrderRow}の外部キーがある{@@link RlsOmsOrderRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRlsOmsOrderRowsByCondOrdId() throws DataNetworkException, DataQueryException  {
        return RlsOmsOrderDao.findRowsByCondOrdId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRlsOmsOrderRowsByCondOrdId()}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public List fetchRlsOmsOrderDaosByCondOrdId() throws DataNetworkException, DataQueryException  {
        return RlsOmsOrderDao.findDaosByCondOrdId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRlsOmsOrderRowsByCondOrdId()}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public List fetchRlsOmsOrderDaosCondOrdId() throws DataNetworkException, DataQueryException  {
        return fetchRlsOmsOrderDaosByCondOrdId();
    }


  /** 
   * この{@@link RlsCondOrderDao}に関連する{@@link RlsCondOrderRow}の外部キーがある{@@link RlsPriceCondRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RlsCondOrderDao}に関連する{@@link RlsCondOrderRow}の外部キーがある{@@link RlsPriceCondRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRlsPriceCondRowsByCondOrdId() throws DataNetworkException, DataQueryException  {
        return RlsPriceCondDao.findRowsByCondOrdId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRlsPriceCondRowsByCondOrdId()}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public List fetchRlsPriceCondDaosByCondOrdId() throws DataNetworkException, DataQueryException  {
        return RlsPriceCondDao.findDaosByCondOrdId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRlsPriceCondRowsByCondOrdId()}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public List fetchRlsPriceCondDaosCondOrdId() throws DataNetworkException, DataQueryException  {
        return fetchRlsPriceCondDaosByCondOrdId();
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
   * p_id, and にて指定の値から一意の{@@link RlsCondOrderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_id 検索対象であるp_idフィールドの値
   * 
   * @@return 引数指定のp_id, and の値と一致する{@@link RlsCondOrderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RlsCondOrderRow findRowById( long p_id ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsCondOrderRow.TYPE,
            "id=?",
            null,
            new Object[] { new Long(p_id) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsCondOrderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsCondOrderDao.findRowsById(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowById(long)}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public static RlsCondOrderDao findDaoById( long p_id ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowById( p_id ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, and にて指定の値に一致する{@@link RlsCondOrderRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, and の値と一致する{@@link RlsCondOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsCondOrderRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountId(long)}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountId( p_accountId ) );
    }


  /** 
   * p_parentId, and にて指定の値に一致する{@@link RlsCondOrderRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_parentId 検索対象であるp_parentIdフィールドの値
   * 
   * @@return 引数指定のp_parentId, and の値と一致する{@@link RlsCondOrderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByParentId( Long p_parentId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RlsCondOrderRow.TYPE,
            "parent_id=?",
            null,
            new Object[] { p_parentId } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByParentId(Long)}および{@@link #forRow(RlsCondOrderRow)}を使用してください。 
   */
    public static List findDaosByParentId( Long p_parentId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByParentId( p_parentId ) );
    }

}
@
