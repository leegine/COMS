head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ProductEstimationRatioDao.java;


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
 * {@@link ProductEstimationRatioDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ProductEstimationRatioRow}インスタンスへ関連付けることができます。 
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
 * @@see ProductEstimationRatioPK 
 * @@see ProductEstimationRatioRow 
 */
public class ProductEstimationRatioDao extends DataAccessObject {


  /** 
   * この{@@link ProductEstimationRatioDao}に関連する型指定のRowオブジェクト 
   */
    private ProductEstimationRatioRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ProductEstimationRatioRow}と仮定される{@@link DataAccessObject}から新たに{@@link ProductEstimationRatioDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ProductEstimationRatioDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ProductEstimationRatioRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ProductEstimationRatioRow )
                return new ProductEstimationRatioDao( (ProductEstimationRatioRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ProductEstimationRatioRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ProductEstimationRatioRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ProductEstimationRatioRow}オブジェクト 
    */
    protected ProductEstimationRatioDao( ProductEstimationRatioRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ProductEstimationRatioRow}オブジェクトを取得します。
   */
    public ProductEstimationRatioRow getProductEstimationRatioRow() {
        return row;
    }


  /** 
   * 指定の{@@link ProductEstimationRatioRow}オブジェクトから{@@link ProductEstimationRatioDao}オブジェクトを作成します。 
   * これは実際の{@@link ProductEstimationRatioRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ProductEstimationRatioDao}取得のために指定の{@@link ProductEstimationRatioRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ProductEstimationRatioDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ProductEstimationRatioDao forRow( ProductEstimationRatioRow row ) throws java.lang.IllegalArgumentException {
        return (ProductEstimationRatioDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ProductEstimationRatioRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ProductEstimationRatioRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ProductEstimationRatioPK}やデータベースレコードとして挿入される{@@link ProductEstimationRatioParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ProductEstimationRatioRow.TYPE );
    }


  /** 
   * {@@link ProductEstimationRatioRow}を一意に特定する{@@link ProductEstimationRatioPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ProductEstimationRatioRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ProductEstimationRatioParams}オブジェクトの主キーとして利用可能な{@@link ProductEstimationRatioPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ProductEstimationRatioPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ProductEstimationRatioRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ProductEstimationRatioRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ProductEstimationRatioRow findRowByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductEstimationRatioPK pk = new ProductEstimationRatioPK( p_institutionCode, p_productType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のProductEstimationRatioPKオブジェクトから{@@link ProductEstimationRatioRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するProductEstimationRatioPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ProductEstimationRatioRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ProductEstimationRatioRow findRowByPk( ProductEstimationRatioPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ProductEstimationRatioRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(ProductEstimationRatioRow)}を使用してください。 
   */
    public static ProductEstimationRatioDao findDaoByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductEstimationRatioPK pk = new ProductEstimationRatioPK( p_institutionCode, p_productType );
        ProductEstimationRatioRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ProductEstimationRatioPK)}および{@@link #forRow(ProductEstimationRatioRow)}を使用してください。 
   */
    public static ProductEstimationRatioDao findDaoByPk( ProductEstimationRatioPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ProductEstimationRatioRow row = findRowByPk( pk );
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


  /** 
   * p_institutionCode, p_productType, and にて指定の値から一意の{@@link ProductEstimationRatioRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productType, and の値と一致する{@@link ProductEstimationRatioRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ProductEstimationRatioRow findRowByInstitutionCodeProductType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ProductEstimationRatioRow.TYPE,
            "institution_code=? and product_type=?",
            null,
            new Object[] { p_institutionCode, p_productType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ProductEstimationRatioRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ProductEstimationRatioDao.findRowsByInstitutionCodeProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductType(String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(ProductEstimationRatioRow)}を使用してください。 
   */
    public static ProductEstimationRatioDao findDaoByInstitutionCodeProductType( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductType( p_institutionCode, p_productType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
