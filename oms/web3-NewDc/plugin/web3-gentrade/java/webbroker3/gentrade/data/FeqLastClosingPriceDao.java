head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.26.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FeqLastClosingPriceDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FeqLastClosingPriceDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqLastClosingPriceRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqLastClosingPricePK 
 * @@see FeqLastClosingPriceRow 
 */
public class FeqLastClosingPriceDao extends DataAccessObject {


  /** 
   * この{@@link FeqLastClosingPriceDao}に関連する型指定のRowオブジェクト 
   */
    private FeqLastClosingPriceRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqLastClosingPriceRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqLastClosingPriceDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqLastClosingPriceDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqLastClosingPriceRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqLastClosingPriceRow )
                return new FeqLastClosingPriceDao( (FeqLastClosingPriceRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqLastClosingPriceRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqLastClosingPriceRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqLastClosingPriceRow}オブジェクト 
    */
    protected FeqLastClosingPriceDao( FeqLastClosingPriceRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqLastClosingPriceRow}オブジェクトを取得します。
   */
    public FeqLastClosingPriceRow getFeqLastClosingPriceRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqLastClosingPriceRow}オブジェクトから{@@link FeqLastClosingPriceDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqLastClosingPriceRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqLastClosingPriceDao}取得のために指定の{@@link FeqLastClosingPriceRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqLastClosingPriceDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqLastClosingPriceDao forRow( FeqLastClosingPriceRow row ) throws java.lang.IllegalArgumentException {
        return (FeqLastClosingPriceDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqLastClosingPriceRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqLastClosingPriceRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqLastClosingPricePK}やデータベースレコードとして挿入される{@@link FeqLastClosingPriceParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqLastClosingPriceRow.TYPE );
    }


  /** 
   * {@@link FeqLastClosingPriceRow}を一意に特定する{@@link FeqLastClosingPricePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqLastClosingPriceRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqLastClosingPriceParams}オブジェクトの主キーとして利用可能な{@@link FeqLastClosingPricePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqLastClosingPricePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqLastClosingPriceRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_baseDate 検索対象であるp_baseDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqLastClosingPriceRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqLastClosingPriceRow findRowByPk( long p_productId, java.sql.Timestamp p_baseDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLastClosingPricePK pk = new FeqLastClosingPricePK( p_productId, p_baseDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqLastClosingPricePKオブジェクトから{@@link FeqLastClosingPriceRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqLastClosingPricePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqLastClosingPriceRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqLastClosingPriceRow findRowByPk( FeqLastClosingPricePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqLastClosingPriceRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,java.sql.Timestamp)}および{@@link #forRow(FeqLastClosingPriceRow)}を使用してください。 
   */
    public static FeqLastClosingPriceDao findDaoByPk( long p_productId, java.sql.Timestamp p_baseDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLastClosingPricePK pk = new FeqLastClosingPricePK( p_productId, p_baseDate );
        FeqLastClosingPriceRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqLastClosingPricePK)}および{@@link #forRow(FeqLastClosingPriceRow)}を使用してください。 
   */
    public static FeqLastClosingPriceDao findDaoByPk( FeqLastClosingPricePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLastClosingPriceRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link FeqLastClosingPriceDao}に紐付く{@@link FeqLastClosingPriceRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link FeqLastClosingPriceDao}と外部キーの関係にある{@@link ProductRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public ProductRow fetchProductRowViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        ProductPK pk = new ProductPK( row.getProductId() );
        Row row = ProductDao.findRowByPk( pk );
        if ( row != null && !(row instanceof ProductRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (ProductRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(FeqLastClosingPriceRow)}を使用してください。 
   */
    public ProductDao fetchProductDaoViaProductId() throws DataNetworkException, DataFindException, DataQueryException  {
        ProductPK pk = new ProductPK( row.getProductId() );
        DataAccessObject dao = ProductDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof ProductDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (ProductDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Product
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByProductId(ProductRow)}を使用してください。 
   */
    public static List findRowsByProductId( ProductDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( dao.getProductRow() );
    }


  /** 
   * {@@link ProductRow}と外部キーの関係にある{@@link FeqLastClosingPriceRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link FeqLastClosingPriceRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link FeqLastClosingPriceRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link FeqLastClosingPriceRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link FeqLastClosingPriceRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link FeqLastClosingPriceRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            FeqLastClosingPriceRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(FeqLastClosingPriceRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(FeqLastClosingPriceRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(FeqLastClosingPriceRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(FeqLastClosingPriceRow)}を使用してください。 
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
   * p_productId, p_baseDate, and にて指定の値から一意の{@@link FeqLastClosingPriceRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_baseDate 検索対象であるp_baseDateフィールドの値
   * 
   * @@return 引数指定のp_productId, p_baseDate, and の値と一致する{@@link FeqLastClosingPriceRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqLastClosingPriceRow findRowByProductIdBaseDate( long p_productId, java.sql.Timestamp p_baseDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqLastClosingPriceRow.TYPE,
            "product_id=? and base_date=?",
            null,
            new Object[] { new Long(p_productId), p_baseDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqLastClosingPriceRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqLastClosingPriceDao.findRowsByProductIdBaseDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdBaseDate(long, java.sql.Timestamp)}および{@@link #forRow(FeqLastClosingPriceRow)}を使用してください。 
   */
    public static FeqLastClosingPriceDao findDaoByProductIdBaseDate( long p_productId, java.sql.Timestamp p_baseDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdBaseDate( p_productId, p_baseDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
