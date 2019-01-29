head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.28.09.09.29;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5644d9050285d38;
filename	RuitoProductDao.java;

1.1
date	2011.03.22.09.17.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoProductDao.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link RuitoProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RuitoProductRow}インスタンスへ関連付けることができます。 
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
 * @@see RuitoProductPK 
 * @@see RuitoProductRow 
 */
public class RuitoProductDao extends DataAccessObject {


  /** 
   * この{@@link RuitoProductDao}に関連する型指定のRowオブジェクト 
   */
    private RuitoProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RuitoProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link RuitoProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RuitoProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RuitoProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RuitoProductRow )
                return new RuitoProductDao( (RuitoProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RuitoProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RuitoProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RuitoProductRow}オブジェクト 
    */
    protected RuitoProductDao( RuitoProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RuitoProductRow}オブジェクトを取得します。
   */
    public RuitoProductRow getRuitoProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link RuitoProductRow}オブジェクトから{@@link RuitoProductDao}オブジェクトを作成します。 
   * これは実際の{@@link RuitoProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RuitoProductDao}取得のために指定の{@@link RuitoProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RuitoProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RuitoProductDao forRow( RuitoProductRow row ) throws java.lang.IllegalArgumentException {
        return (RuitoProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RuitoProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RuitoProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RuitoProductPK}やデータベースレコードとして挿入される{@@link RuitoProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RuitoProductRow.TYPE );
    }


  /** 
   * {@@link RuitoProductRow}を一意に特定する{@@link RuitoProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RuitoProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RuitoProductParams}オブジェクトの主キーとして利用可能な{@@link RuitoProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RuitoProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RuitoProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RuitoProductRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RuitoProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RuitoProductRow findRowByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoProductPK pk = new RuitoProductPK( p_productId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRuitoProductPKオブジェクトから{@@link RuitoProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRuitoProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RuitoProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RuitoProductRow findRowByPk( RuitoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RuitoProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public static RuitoProductDao findDaoByPk( long p_productId ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoProductPK pk = new RuitoProductPK( p_productId );
        RuitoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RuitoProductPK)}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public static RuitoProductDao findDaoByPk( RuitoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RuitoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link RuitoProductDao}に紐付く{@@link RuitoProductRow}内で外部キーの関係をもつ{@@link ProductRow}を検索します。 
   * 
   * @@return {@@link RuitoProductDao}と外部キーの関係にある{@@link ProductRow} 
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
   * @@deprecated 代わりに{@@link #fetchProductRowViaProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
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
   * この{@@link RuitoProductDao}に関連する{@@link RuitoProductRow}の外部キーがある{@@link RuitoTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoProductDao}に関連する{@@link RuitoProductRow}の外部キーがある{@@link RuitoTradedProductUpdqRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductUpdqDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoTradedProductUpdqRowsByProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public List fetchRuitoTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductUpdqDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoTradedProductUpdqRowsByProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public List fetchRuitoTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoTradedProductUpdqDaosByProductId();
    }


  /** 
   * この{@@link RuitoProductDao}に関連する{@@link RuitoProductRow}の外部キーがある{@@link RuitoTradedProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link RuitoProductDao}に関連する{@@link RuitoProductRow}の外部キーがある{@@link RuitoTradedProductRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchRuitoTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductDao.findRowsByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoTradedProductRowsByProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public List fetchRuitoTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductDao.findDaosByProductId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchRuitoTradedProductRowsByProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public List fetchRuitoTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoTradedProductDaosByProductId();
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
   * {@@link ProductRow}と外部キーの関係にある{@@link RuitoProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link ProductRow}オブジェクト 
   * @@return 指定の{@@link ProductRow}に外部キーを持つ{@@link RuitoProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( row.getProductId() );
    }


  /** 
   * {@@link ProductPK}と外部キーの関係にある{@@link RuitoProductRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link ProductPK}オブジェクト 
   * @@return {@@link ProductPK}と外部キーが一致する値を持つ{@@link RuitoProductRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByProductId( pk.product_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link RuitoProductRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link RuitoProductRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            RuitoProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Product
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductRow)}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(ProductPK)}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public static List findDaosByProductId( ProductPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByProductId( pk.product_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(RuitoProductRow)}を使用してください。 
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
   * p_productId, and にて指定の値から一意の{@@link RuitoProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のp_productId, and の値と一致する{@@link RuitoProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RuitoProductRow findRowByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RuitoProductRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RuitoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RuitoProductDao.findRowsByProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductId(long)}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public static RuitoProductDao findDaoByProductId( long p_productId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductId( p_productId ) );
    }


  /** 
   * p_institutionCode, p_productCode, p_productIssueCode, and にて指定の値から一意の{@@link RuitoProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_productIssueCode 検索対象であるp_productIssueCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productCode, p_productIssueCode, and の値と一致する{@@link RuitoProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RuitoProductRow findRowByInstitutionCodeProductCodeProductIssueCode( String p_institutionCode, String p_productCode, String p_productIssueCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RuitoProductRow.TYPE,
            "institution_code=? and product_code=? and product_issue_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_productIssueCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RuitoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RuitoProductDao.findRowsByInstitutionCodeProductCodeProductIssueCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductCodeProductIssueCode(String, String, String)}および{@@link #forRow(RuitoProductRow)}を使用してください。 
   */
    public static RuitoProductDao findDaoByInstitutionCodeProductCodeProductIssueCode( String p_institutionCode, String p_productCode, String p_productIssueCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeProductIssueCode( p_institutionCode, p_productCode, p_productIssueCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@


1.1
log
@*** empty log message ***
@
text
@d221 1
a221 1
   * この{@@link RuitoProductDao}に関連する{@@link RuitoProductRow}の外部キーがある{@@link RuitoTradedProductRow}オブジェクトを検索し{@@link List}として返します。 
d223 1
a223 1
   * @@return この{@@link RuitoProductDao}に関連する{@@link RuitoProductRow}の外部キーがある{@@link RuitoTradedProductRow}オブジェクトの{@@link List}
d227 2
a228 2
    public List fetchRuitoTradedProductRowsByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductDao.findRowsByProductId( row );
d233 1
a233 1
   * @@deprecated 代わりに{@@link #fetchRuitoTradedProductRowsByProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
d235 2
a236 2
    public List fetchRuitoTradedProductDaosByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductDao.findDaosByProductId( row );
d241 1
a241 1
   * @@deprecated 代わりに{@@link #fetchRuitoTradedProductRowsByProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
d243 2
a244 2
    public List fetchRuitoTradedProductDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoTradedProductDaosByProductId();
d249 1
a249 1
   * この{@@link RuitoProductDao}に関連する{@@link RuitoProductRow}の外部キーがある{@@link RuitoTradedProductUpdqRow}オブジェクトを検索し{@@link List}として返します。 
d251 1
a251 1
   * @@return この{@@link RuitoProductDao}に関連する{@@link RuitoProductRow}の外部キーがある{@@link RuitoTradedProductUpdqRow}オブジェクトの{@@link List}
d255 2
a256 2
    public List fetchRuitoTradedProductUpdqRowsByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductUpdqDao.findRowsByProductId( row );
d261 1
a261 1
   * @@deprecated 代わりに{@@link #fetchRuitoTradedProductUpdqRowsByProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
d263 2
a264 2
    public List fetchRuitoTradedProductUpdqDaosByProductId() throws DataNetworkException, DataQueryException  {
        return RuitoTradedProductUpdqDao.findDaosByProductId( row );
d269 1
a269 1
   * @@deprecated 代わりに{@@link #fetchRuitoTradedProductUpdqRowsByProductId()}および{@@link #forRow(RuitoProductRow)}を使用してください。 
d271 2
a272 2
    public List fetchRuitoTradedProductUpdqDaosProductId() throws DataNetworkException, DataQueryException  {
        return fetchRuitoTradedProductUpdqDaosByProductId();
@

