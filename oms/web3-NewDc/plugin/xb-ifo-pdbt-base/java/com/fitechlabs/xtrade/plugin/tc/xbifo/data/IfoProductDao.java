head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.06.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoProductDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IfoProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoProductRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoProductPK 
 * @@see IfoProductRow 
 */
public class IfoProductDao extends DataAccessObject {


  /** 
   * この{@@link IfoProductDao}に関連する型指定のRowオブジェクト 
   */
    private IfoProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoProductRow )
                return new IfoProductDao( (IfoProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoProductRow}オブジェクト 
    */
    protected IfoProductDao( IfoProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoProductRow}オブジェクトを取得します。
   */
    public IfoProductRow getIfoProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoProductRow}オブジェクトから{@@link IfoProductDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoProductDao}取得のために指定の{@@link IfoProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoProductDao forRow( IfoProductRow row ) throws java.lang.IllegalArgumentException {
        return (IfoProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoProductPK}やデータベースレコードとして挿入される{@@link IfoProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoProductRow.TYPE );
    }


  /** 
   * {@@link IfoProductRow}を一意に特定する{@@link IfoProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoProductParams}オブジェクトの主キーとして利用可能な{@@link IfoProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoProductRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoProductPK pk = new IfoProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoProductPKオブジェクトから{@@link IfoProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoProductRow findRowByPk( IfoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static IfoProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoProductPK pk = new IfoProductPK( p_productId );
        IfoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoProductPK)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static IfoProductDao findDaoByPk( IfoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link IfoProductDao}に紐付く{@@link IfoProductRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link IfoProductDao}と外部キーの関係にある{@@link ProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(IfoProductRow)}を使用してください。 
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link IfoProductDao}に関連する{@@link IfoProductRow}の外部キーがある{@@link IfoTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link IfoProductDao}に関連する{@@link IfoProductRow}の外部キーがある{@@link IfoTradedProductUpdqRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchIfoTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return IfoTradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoTradedProductUpdqRowsByProductId()}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public List fetchIfoTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return IfoTradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoTradedProductUpdqRowsByProductId()}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public List fetchIfoTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchIfoTradedProductUpdqDaosByProductId();
    }


  /** 
   * この{@@link IfoProductDao}に関連する{@@link IfoProductRow}の外部キーがある{@@link IfoTradedProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link IfoProductDao}に関連する{@@link IfoProductRow}の外部キーがある{@@link IfoTradedProductRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchIfoTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return IfoTradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoTradedProductRowsByProductId()}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public List fetchIfoTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return IfoTradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchIfoTradedProductRowsByProductId()}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public List fetchIfoTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchIfoTradedProductDaosByProductId();
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
   * {@@link ProductRow}と外部キーの関係にある{@@link IfoProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link IfoProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link IfoProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link IfoProductRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link IfoProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link IfoProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(IfoProductRow)}を使用してください。 
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
   * p_productId, and にて指定の値から一意の{@@link IfoProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のp_productId, and の値と一致する{@@link IfoProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductId(long)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static IfoProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }


  /** 
   * p_productCode, p_institutionCode, and にて指定の値から一意の{@@link IfoProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_productCode, p_institutionCode, and の値と一致する{@@link IfoProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoProductRow findRowByProductCodeInstitutionCode( String p_productCode, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "product_code=? and institution_code=?",
            null,
            new Object[] { p_productCode, p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoProductDao.findRowsByProductCodeInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductCodeInstitutionCode(String, String)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static IfoProductDao findDaoByProductCodeInstitutionCode( String p_productCode, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductCodeInstitutionCode( p_productCode, p_institutionCode ) );
    }


  /** 
   * p_institutionCode, p_underlyingProductCode, p_derivativeType, p_strikePrice, p_monthOfDelivery, p_splitType, and にて指定の値から一意の{@@link IfoProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_underlyingProductCode 検索対象であるp_underlyingProductCodeフィールドの値
   * @@param p_derivativeType 検索対象であるp_derivativeTypeフィールドの値
   * @@param p_strikePrice 検索対象であるp_strikePriceフィールドの値
   * @@param p_monthOfDelivery 検索対象であるp_monthOfDeliveryフィールドの値
   * @@param p_splitType 検索対象であるp_splitTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_underlyingProductCode, p_derivativeType, p_strikePrice, p_monthOfDelivery, p_splitType, and の値と一致する{@@link IfoProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IfoProductRow findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType( String p_institutionCode, String p_underlyingProductCode, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType, double p_strikePrice, String p_monthOfDelivery, String p_splitType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "institution_code=? and underlying_product_code=? and derivative_type=? and strike_price=? and month_of_delivery=? and split_type=?",
            null,
            new Object[] { p_institutionCode, p_underlyingProductCode, p_derivativeType, new Double(p_strikePrice), p_monthOfDelivery, p_splitType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoProductDao.findRowsByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType(String, String, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum, double, String, String)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static IfoProductDao findDaoByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType( String p_institutionCode, String p_underlyingProductCode, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType, double p_strikePrice, String p_monthOfDelivery, String p_splitType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType( p_institutionCode, p_underlyingProductCode, p_derivativeType, p_strikePrice, p_monthOfDelivery, p_splitType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_underlyingProductCode, p_derivativeType, p_monthOfDelivery, and にて指定の値に一致する{@@link IfoProductRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_underlyingProductCode 検索対象であるp_underlyingProductCodeフィールドの値
   * @@param p_derivativeType 検索対象であるp_derivativeTypeフィールドの値
   * @@param p_monthOfDelivery 検索対象であるp_monthOfDeliveryフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_underlyingProductCode, p_derivativeType, p_monthOfDelivery, and の値と一致する{@@link IfoProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeUnderlyingProductCodeDerivativeTypeMonthOfDelivery( String p_institutionCode, String p_underlyingProductCode, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType, String p_monthOfDelivery ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoProductRow.TYPE,
            "institution_code=? and underlying_product_code=? and derivative_type=? and month_of_delivery=?",
            null,
            new Object[] { p_institutionCode, p_underlyingProductCode, p_derivativeType, p_monthOfDelivery } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeUnderlyingProductCodeDerivativeTypeMonthOfDelivery(String, String, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum, String)}および{@@link #forRow(IfoProductRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeUnderlyingProductCodeDerivativeTypeMonthOfDelivery( String p_institutionCode, String p_underlyingProductCode, com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum p_derivativeType, String p_monthOfDelivery ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeUnderlyingProductCodeDerivativeTypeMonthOfDelivery( p_institutionCode, p_underlyingProductCode, p_derivativeType, p_monthOfDelivery ) );
    }

}
@
