head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	BookValueSpecDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradehistory.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BookValueSpecDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BookValueSpecRow}インスタンスへ関連付けることができます。 
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
 * @@see BookValueSpecPK 
 * @@see BookValueSpecRow 
 */
public class BookValueSpecDao extends DataAccessObject {


  /** 
   * この{@@link BookValueSpecDao}に関連する型指定のRowオブジェクト 
   */
    private BookValueSpecRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BookValueSpecRow}と仮定される{@@link DataAccessObject}から新たに{@@link BookValueSpecDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BookValueSpecDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BookValueSpecRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BookValueSpecRow )
                return new BookValueSpecDao( (BookValueSpecRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BookValueSpecRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BookValueSpecRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BookValueSpecRow}オブジェクト 
    */
    protected BookValueSpecDao( BookValueSpecRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BookValueSpecRow}オブジェクトを取得します。
   */
    public BookValueSpecRow getBookValueSpecRow() {
        return row;
    }


  /** 
   * 指定の{@@link BookValueSpecRow}オブジェクトから{@@link BookValueSpecDao}オブジェクトを作成します。 
   * これは実際の{@@link BookValueSpecRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BookValueSpecDao}取得のために指定の{@@link BookValueSpecRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BookValueSpecDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BookValueSpecDao forRow( BookValueSpecRow row ) throws java.lang.IllegalArgumentException {
        return (BookValueSpecDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BookValueSpecRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BookValueSpecRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BookValueSpecPK}やデータベースレコードとして挿入される{@@link BookValueSpecParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BookValueSpecRow.TYPE );
    }


  /** 
   * {@@link BookValueSpecRow}を一意に特定する{@@link BookValueSpecPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BookValueSpecRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BookValueSpecParams}オブジェクトの主キーとして利用可能な{@@link BookValueSpecPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BookValueSpecPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BookValueSpecPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BookValueSpecRow}オブジェクトを検索します。 
   * 
   * @@param p_bookValueSpecId 検索対象であるp_bookValueSpecIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BookValueSpecRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BookValueSpecRow findRowByPk( long p_bookValueSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        BookValueSpecPK pk = new BookValueSpecPK( p_bookValueSpecId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBookValueSpecPKオブジェクトから{@@link BookValueSpecRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBookValueSpecPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BookValueSpecRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BookValueSpecRow findRowByPk( BookValueSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BookValueSpecRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(BookValueSpecRow)}を使用してください。 
   */
    public static BookValueSpecDao findDaoByPk( long p_bookValueSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        BookValueSpecPK pk = new BookValueSpecPK( p_bookValueSpecId );
        BookValueSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BookValueSpecPK)}および{@@link #forRow(BookValueSpecRow)}を使用してください。 
   */
    public static BookValueSpecDao findDaoByPk( BookValueSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BookValueSpecRow row = findRowByPk( pk );
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
   * p_bookValueSpecId, and にて指定の値から一意の{@@link BookValueSpecRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_bookValueSpecId 検索対象であるp_bookValueSpecIdフィールドの値
   * 
   * @@return 引数指定のp_bookValueSpecId, and の値と一致する{@@link BookValueSpecRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BookValueSpecRow findRowByBookValueSpecId( long p_bookValueSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BookValueSpecRow.TYPE,
            "book_value_spec_id=?",
            null,
            new Object[] { new Long(p_bookValueSpecId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BookValueSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BookValueSpecDao.findRowsByBookValueSpecId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBookValueSpecId(long)}および{@@link #forRow(BookValueSpecRow)}を使用してください。 
   */
    public static BookValueSpecDao findDaoByBookValueSpecId( long p_bookValueSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBookValueSpecId( p_bookValueSpecId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_commodityCode, p_productCode, and にて指定の値に一致する{@@link BookValueSpecRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_commodityCode 検索対象であるp_commodityCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_commodityCode, p_productCode, and の値と一致する{@@link BookValueSpecRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeCommodityCodeProductCode( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commodityCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BookValueSpecRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and commodity_code=? and product_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_commodityCode, p_productCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeCommodityCodeProductCode(String, String, String, String, String)}および{@@link #forRow(BookValueSpecRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeCommodityCodeProductCode( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commodityCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeCommodityCodeProductCode( p_institutionCode, p_branchCode, p_accountCode, p_commodityCode, p_productCode ) );
    }

}
@
