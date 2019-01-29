head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	QuoteMonitorProductDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.quoteadaptor.stdimpls.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link QuoteMonitorProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QuoteMonitorProductRow}インスタンスへ関連付けることができます。 
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
 * @@see QuoteMonitorProductPK 
 * @@see QuoteMonitorProductRow 
 */
public class QuoteMonitorProductDao extends DataAccessObject {


  /** 
   * この{@@link QuoteMonitorProductDao}に関連する型指定のRowオブジェクト 
   */
    private QuoteMonitorProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QuoteMonitorProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link QuoteMonitorProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QuoteMonitorProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QuoteMonitorProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QuoteMonitorProductRow )
                return new QuoteMonitorProductDao( (QuoteMonitorProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QuoteMonitorProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QuoteMonitorProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QuoteMonitorProductRow}オブジェクト 
    */
    protected QuoteMonitorProductDao( QuoteMonitorProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QuoteMonitorProductRow}オブジェクトを取得します。
   */
    public QuoteMonitorProductRow getQuoteMonitorProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link QuoteMonitorProductRow}オブジェクトから{@@link QuoteMonitorProductDao}オブジェクトを作成します。 
   * これは実際の{@@link QuoteMonitorProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QuoteMonitorProductDao}取得のために指定の{@@link QuoteMonitorProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QuoteMonitorProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QuoteMonitorProductDao forRow( QuoteMonitorProductRow row ) throws java.lang.IllegalArgumentException {
        return (QuoteMonitorProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QuoteMonitorProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QuoteMonitorProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QuoteMonitorProductPK}やデータベースレコードとして挿入される{@@link QuoteMonitorProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QuoteMonitorProductRow.TYPE );
    }


  /** 
   * {@@link QuoteMonitorProductRow}を一意に特定する{@@link QuoteMonitorProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QuoteMonitorProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QuoteMonitorProductParams}オブジェクトの主キーとして利用可能な{@@link QuoteMonitorProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QuoteMonitorProductPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QuoteMonitorProductRow}オブジェクトを検索します。 
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QuoteMonitorProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QuoteMonitorProductRow findRowByPk( String p_marketCode, String p_productCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteMonitorProductPK pk = new QuoteMonitorProductPK( p_marketCode, p_productCode, p_productType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQuoteMonitorProductPKオブジェクトから{@@link QuoteMonitorProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQuoteMonitorProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QuoteMonitorProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QuoteMonitorProductRow findRowByPk( QuoteMonitorProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QuoteMonitorProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(QuoteMonitorProductRow)}を使用してください。 
   */
    public static QuoteMonitorProductDao findDaoByPk( String p_marketCode, String p_productCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteMonitorProductPK pk = new QuoteMonitorProductPK( p_marketCode, p_productCode, p_productType );
        QuoteMonitorProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QuoteMonitorProductPK)}および{@@link #forRow(QuoteMonitorProductRow)}を使用してください。 
   */
    public static QuoteMonitorProductDao findDaoByPk( QuoteMonitorProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteMonitorProductRow row = findRowByPk( pk );
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
   * p_marketCode, p_productCode, p_productType, and にて指定の値から一意の{@@link QuoteMonitorProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_marketCode, p_productCode, p_productType, and の値と一致する{@@link QuoteMonitorProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static QuoteMonitorProductRow findRowByMarketCodeProductCodeProductType( String p_marketCode, String p_productCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QuoteMonitorProductRow.TYPE,
            "market_code=? and product_code=? and product_type=?",
            null,
            new Object[] { p_marketCode, p_productCode, p_productType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QuoteMonitorProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QuoteMonitorProductDao.findRowsByMarketCodeProductCodeProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByMarketCodeProductCodeProductType(String, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(QuoteMonitorProductRow)}を使用してください。 
   */
    public static QuoteMonitorProductDao findDaoByMarketCodeProductCodeProductType( String p_marketCode, String p_productCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketCodeProductCodeProductType( p_marketCode, p_productCode, p_productType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
