head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondAutoExecLimitActionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link BondAutoExecLimitActionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BondAutoExecLimitActionRow}インスタンスへ関連付けることができます。 
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
 * @@see BondAutoExecLimitActionPK 
 * @@see BondAutoExecLimitActionRow 
 */
public class BondAutoExecLimitActionDao extends DataAccessObject {


  /** 
   * この{@@link BondAutoExecLimitActionDao}に関連する型指定のRowオブジェクト 
   */
    private BondAutoExecLimitActionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BondAutoExecLimitActionRow}と仮定される{@@link DataAccessObject}から新たに{@@link BondAutoExecLimitActionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BondAutoExecLimitActionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BondAutoExecLimitActionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondAutoExecLimitActionRow )
                return new BondAutoExecLimitActionDao( (BondAutoExecLimitActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondAutoExecLimitActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondAutoExecLimitActionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BondAutoExecLimitActionRow}オブジェクト 
    */
    protected BondAutoExecLimitActionDao( BondAutoExecLimitActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BondAutoExecLimitActionRow}オブジェクトを取得します。
   */
    public BondAutoExecLimitActionRow getBondAutoExecLimitActionRow() {
        return row;
    }


  /** 
   * 指定の{@@link BondAutoExecLimitActionRow}オブジェクトから{@@link BondAutoExecLimitActionDao}オブジェクトを作成します。 
   * これは実際の{@@link BondAutoExecLimitActionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BondAutoExecLimitActionDao}取得のために指定の{@@link BondAutoExecLimitActionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BondAutoExecLimitActionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BondAutoExecLimitActionDao forRow( BondAutoExecLimitActionRow row ) throws java.lang.IllegalArgumentException {
        return (BondAutoExecLimitActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondAutoExecLimitActionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BondAutoExecLimitActionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BondAutoExecLimitActionPK}やデータベースレコードとして挿入される{@@link BondAutoExecLimitActionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondAutoExecLimitActionRow.TYPE );
    }


  /** 
   * {@@link BondAutoExecLimitActionRow}を一意に特定する{@@link BondAutoExecLimitActionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BondAutoExecLimitActionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BondAutoExecLimitActionParams}オブジェクトの主キーとして利用可能な{@@link BondAutoExecLimitActionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BondAutoExecLimitActionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BondAutoExecLimitActionRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_executionUpdateDate 検索対象であるp_executionUpdateDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondAutoExecLimitActionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondAutoExecLimitActionRow findRowByPk( long p_productId, java.sql.Timestamp p_executionUpdateDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondAutoExecLimitActionPK pk = new BondAutoExecLimitActionPK( p_productId, p_executionUpdateDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBondAutoExecLimitActionPKオブジェクトから{@@link BondAutoExecLimitActionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBondAutoExecLimitActionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondAutoExecLimitActionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondAutoExecLimitActionRow findRowByPk( BondAutoExecLimitActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondAutoExecLimitActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,java.sql.Timestamp)}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public static BondAutoExecLimitActionDao findDaoByPk( long p_productId, java.sql.Timestamp p_executionUpdateDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondAutoExecLimitActionPK pk = new BondAutoExecLimitActionPK( p_productId, p_executionUpdateDate );
        BondAutoExecLimitActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BondAutoExecLimitActionPK)}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public static BondAutoExecLimitActionDao findDaoByPk( BondAutoExecLimitActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondAutoExecLimitActionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link BondAutoExecLimitActionDao}に紐付く{@@link BondAutoExecLimitActionRow}内で外部キーの関係をもつ{@@link BondProductRow}を検索します。 
   * 
   * @@return {@@link BondAutoExecLimitActionDao}と外部キーの関係にある{@@link BondProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public BondProductRow fetchBondProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        Row row = BondProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BondProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BondProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBondProductRowViaProductId()}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public BondProductDao fetchBondProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        BondProductPK pk = new BondProductPK( row.getProductId() );
        DataAccessObject dao = BondProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BondProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BondProductDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for BondProduct
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(BondProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( BondProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getBondProductRow() );
    }


  /** 
   * {@@link BondProductRow}と外部キーの関係にある{@@link BondAutoExecLimitActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BondProductRow}オブジェクト 
   * @@return 指定の{@@link BondProductRow}に外部キーを持つ{@@link BondAutoExecLimitActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link BondProductPK}と外部キーの関係にある{@@link BondAutoExecLimitActionRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BondProductPK}オブジェクト 
   * @@return {@@link BondProductPK}と外部キーが一致する値を持つ{@@link BondAutoExecLimitActionRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BondAutoExecLimitActionRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BondAutoExecLimitActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondAutoExecLimitActionRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for BondProduct
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(BondProductRow)}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(BondProductRow)}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(BondProductPK)}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public static List findDaosByProductId( BondProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( p_productId ) );
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
   * p_productId, p_executionUpdateDate, and にて指定の値から一意の{@@link BondAutoExecLimitActionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_executionUpdateDate 検索対象であるp_executionUpdateDateフィールドの値
   * 
   * @@return 引数指定のp_productId, p_executionUpdateDate, and の値と一致する{@@link BondAutoExecLimitActionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondAutoExecLimitActionRow findRowByProductIdExecutionUpdateDate( long p_productId, java.sql.Timestamp p_executionUpdateDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondAutoExecLimitActionRow.TYPE,
            "product_id=? and execution_update_date=?",
            null,
            new Object[] { new Long(p_productId), p_executionUpdateDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondAutoExecLimitActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondAutoExecLimitActionDao.findRowsByProductIdExecutionUpdateDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdExecutionUpdateDate(long, java.sql.Timestamp)}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public static BondAutoExecLimitActionDao findDaoByProductIdExecutionUpdateDate( long p_productId, java.sql.Timestamp p_executionUpdateDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdExecutionUpdateDate( p_productId, p_executionUpdateDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_productId, p_onlineDispDiv, and にて指定の値に一致する{@@link BondAutoExecLimitActionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_onlineDispDiv 検索対象であるp_onlineDispDivフィールドの値
   * 
   * @@return 引数指定のp_productId, p_onlineDispDiv, and の値と一致する{@@link BondAutoExecLimitActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductIdOnlineDispDiv( long p_productId, String p_onlineDispDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondAutoExecLimitActionRow.TYPE,
            "product_id=? and online_disp_div=?",
            null,
            new Object[] { new Long(p_productId), p_onlineDispDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductIdOnlineDispDiv(long, String)}および{@@link #forRow(BondAutoExecLimitActionRow)}を使用してください。 
   */
    public static List findDaosByProductIdOnlineDispDiv( long p_productId, String p_onlineDispDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductIdOnlineDispDiv( p_productId, p_onlineDispDiv ) );
    }

}
@
