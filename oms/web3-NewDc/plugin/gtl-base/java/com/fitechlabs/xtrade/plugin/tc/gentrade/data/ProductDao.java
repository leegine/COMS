head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.34.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ProductDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link ProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ProductRow}インスタンスへ関連付けることができます。 
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
 * @@see ProductPK 
 * @@see ProductRow 
 */
public class ProductDao extends DataAccessObject {


  /** 
   * この{@@link ProductDao}に関連する型指定のRowオブジェクト 
   */
    private ProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link ProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ProductRow )
                return new ProductDao( (ProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ProductRow}オブジェクト 
    */
    protected ProductDao( ProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ProductRow}オブジェクトを取得します。
   */
    public ProductRow getProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link ProductRow}オブジェクトから{@@link ProductDao}オブジェクトを作成します。 
   * これは実際の{@@link ProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ProductDao}取得のために指定の{@@link ProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ProductDao forRow( ProductRow row ) throws java.lang.IllegalArgumentException {
        return (ProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ProductPK}やデータベースレコードとして挿入される{@@link ProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ProductRow.TYPE );
    }


  /** 
   * {@@link ProductRow}を一意に特定する{@@link ProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ProductParams}オブジェクトの主キーとして利用可能な{@@link ProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new ProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ProductRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductPK pk = new ProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のProductPKオブジェクトから{@@link ProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ProductRow findRowByPk( ProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public static ProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductPK pk = new ProductPK( p_productId );
        ProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ProductPK)}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public static ProductDao findDaoByPk( ProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductRow row = findRowByPk( pk );
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
   * この{@@link ProductDao}に関連する{@@link ProductRow}の外部キーがある{@@link TradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link ProductDao}に関連する{@@link ProductRow}の外部キーがある{@@link TradedProductUpdqRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductUpdqRowsByProductId()}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public List fetchTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductUpdqRowsByProductId()}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public List fetchTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductUpdqDaosByProductId();
    }


  /** 
   * この{@@link ProductDao}に関連する{@@link ProductRow}の外部キーがある{@@link TradedProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link ProductDao}に関連する{@@link ProductRow}の外部キーがある{@@link TradedProductRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductRowsByProductId()}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public List fetchTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductRowsByProductId()}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public List fetchTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductDaosByProductId();
    }


  /** 
   * この{@@link ProductDao}に関連する{@@link ProductRow}の外部キーがある{@@link TradedProductCalendarRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link ProductDao}に関連する{@@link ProductRow}の外部キーがある{@@link TradedProductCalendarRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTradedProductCalendarRowsByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductCalendarRowsByProductId()}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public List fetchTradedProductCalendarDaosByProductId() throws DataNetworkException, DataQueryException  {
        return TradedProductCalendarDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTradedProductCalendarRowsByProductId()}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public List fetchTradedProductCalendarDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchTradedProductCalendarDaosByProductId();
    }


  /** 
   * この{@@link ProductDao}に関連する{@@link ProductRow}の外部キーがある{@@link TickValuesDefsRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link ProductDao}に関連する{@@link ProductRow}の外部キーがある{@@link TickValuesDefsRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTickValuesDefsRowsByProductId() throws DataNetworkException, DataQueryException  {
        return TickValuesDefsDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTickValuesDefsRowsByProductId()}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public List fetchTickValuesDefsDaosByProductId() throws DataNetworkException, DataQueryException  {
        return TickValuesDefsDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTickValuesDefsRowsByProductId()}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public List fetchTickValuesDefsDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchTickValuesDefsDaosByProductId();
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
   * p_productId, and にて指定の値から一意の{@@link ProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のp_productId, and の値と一致する{@@link ProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductId(long)}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public static ProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productType, and にて指定の値に一致する{@@link ProductRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productType, and の値と一致する{@@link ProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeProductType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ProductRow.TYPE,
            "institution_code=? and product_type=?",
            null,
            new Object[] { p_institutionCode, p_productType } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeProductType(String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(ProductRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeProductType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductType( p_institutionCode, p_productType ) );
    }

}
@
