head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	GenCurrencyDao.java;


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
 * {@@link GenCurrencyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link GenCurrencyRow}インスタンスへ関連付けることができます。 
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
 * @@see GenCurrencyPK 
 * @@see GenCurrencyRow 
 */
public class GenCurrencyDao extends DataAccessObject {


  /** 
   * この{@@link GenCurrencyDao}に関連する型指定のRowオブジェクト 
   */
    private GenCurrencyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link GenCurrencyRow}と仮定される{@@link DataAccessObject}から新たに{@@link GenCurrencyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link GenCurrencyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link GenCurrencyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof GenCurrencyRow )
                return new GenCurrencyDao( (GenCurrencyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a GenCurrencyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link GenCurrencyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link GenCurrencyRow}オブジェクト 
    */
    protected GenCurrencyDao( GenCurrencyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link GenCurrencyRow}オブジェクトを取得します。
   */
    public GenCurrencyRow getGenCurrencyRow() {
        return row;
    }


  /** 
   * 指定の{@@link GenCurrencyRow}オブジェクトから{@@link GenCurrencyDao}オブジェクトを作成します。 
   * これは実際の{@@link GenCurrencyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link GenCurrencyDao}取得のために指定の{@@link GenCurrencyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link GenCurrencyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static GenCurrencyDao forRow( GenCurrencyRow row ) throws java.lang.IllegalArgumentException {
        return (GenCurrencyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link GenCurrencyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link GenCurrencyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link GenCurrencyPK}やデータベースレコードとして挿入される{@@link GenCurrencyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( GenCurrencyRow.TYPE );
    }


  /** 
   * {@@link GenCurrencyRow}を一意に特定する{@@link GenCurrencyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link GenCurrencyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link GenCurrencyParams}オブジェクトの主キーとして利用可能な{@@link GenCurrencyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static GenCurrencyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link GenCurrencyRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_currencyCode 検索対象であるp_currencyCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link GenCurrencyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static GenCurrencyRow findRowByPk( String p_institutionCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        GenCurrencyPK pk = new GenCurrencyPK( p_institutionCode, p_currencyCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のGenCurrencyPKオブジェクトから{@@link GenCurrencyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するGenCurrencyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link GenCurrencyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static GenCurrencyRow findRowByPk( GenCurrencyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (GenCurrencyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(GenCurrencyRow)}を使用してください。 
   */
    public static GenCurrencyDao findDaoByPk( String p_institutionCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        GenCurrencyPK pk = new GenCurrencyPK( p_institutionCode, p_currencyCode );
        GenCurrencyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(GenCurrencyPK)}および{@@link #forRow(GenCurrencyRow)}を使用してください。 
   */
    public static GenCurrencyDao findDaoByPk( GenCurrencyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        GenCurrencyRow row = findRowByPk( pk );
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
   * p_institutionCode, p_currencyCode, and にて指定の値から一意の{@@link GenCurrencyRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_currencyCode 検索対象であるp_currencyCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_currencyCode, and の値と一致する{@@link GenCurrencyRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static GenCurrencyRow findRowByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            GenCurrencyRow.TYPE,
            "institution_code=? and currency_code=?",
            null,
            new Object[] { p_institutionCode, p_currencyCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (GenCurrencyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query GenCurrencyDao.findRowsByInstitutionCodeCurrencyCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeCurrencyCode(String, String)}および{@@link #forRow(GenCurrencyRow)}を使用してください。 
   */
    public static GenCurrencyDao findDaoByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCurrencyCode( p_institutionCode, p_currencyCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
