head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundExchangeRateDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MutualFundExchangeRateDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MutualFundExchangeRateRow}インスタンスへ関連付けることができます。 
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
 * @@see MutualFundExchangeRatePK 
 * @@see MutualFundExchangeRateRow 
 */
public class MutualFundExchangeRateDao extends DataAccessObject {


  /** 
   * この{@@link MutualFundExchangeRateDao}に関連する型指定のRowオブジェクト 
   */
    private MutualFundExchangeRateRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MutualFundExchangeRateRow}と仮定される{@@link DataAccessObject}から新たに{@@link MutualFundExchangeRateDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MutualFundExchangeRateDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MutualFundExchangeRateRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundExchangeRateRow )
                return new MutualFundExchangeRateDao( (MutualFundExchangeRateRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundExchangeRateRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundExchangeRateRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MutualFundExchangeRateRow}オブジェクト 
    */
    protected MutualFundExchangeRateDao( MutualFundExchangeRateRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MutualFundExchangeRateRow}オブジェクトを取得します。
   */
    public MutualFundExchangeRateRow getMutualFundExchangeRateRow() {
        return row;
    }


  /** 
   * 指定の{@@link MutualFundExchangeRateRow}オブジェクトから{@@link MutualFundExchangeRateDao}オブジェクトを作成します。 
   * これは実際の{@@link MutualFundExchangeRateRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MutualFundExchangeRateDao}取得のために指定の{@@link MutualFundExchangeRateRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MutualFundExchangeRateDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MutualFundExchangeRateDao forRow( MutualFundExchangeRateRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundExchangeRateDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFundExchangeRateRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MutualFundExchangeRateRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MutualFundExchangeRatePK}やデータベースレコードとして挿入される{@@link MutualFundExchangeRateParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFundExchangeRateRow.TYPE );
    }


  /** 
   * {@@link MutualFundExchangeRateRow}を一意に特定する{@@link MutualFundExchangeRatePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MutualFundExchangeRateRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MutualFundExchangeRateParams}オブジェクトの主キーとして利用可能な{@@link MutualFundExchangeRatePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MutualFundExchangeRatePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MutualFundExchangeRateRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_currencyCode 検索対象であるp_currencyCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundExchangeRateRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundExchangeRateRow findRowByPk( String p_institutionCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundExchangeRatePK pk = new MutualFundExchangeRatePK( p_institutionCode, p_currencyCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMutualFundExchangeRatePKオブジェクトから{@@link MutualFundExchangeRateRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMutualFundExchangeRatePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundExchangeRateRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundExchangeRateRow findRowByPk( MutualFundExchangeRatePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFundExchangeRateRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(MutualFundExchangeRateRow)}を使用してください。 
   */
    public static MutualFundExchangeRateDao findDaoByPk( String p_institutionCode, String p_currencyCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundExchangeRatePK pk = new MutualFundExchangeRatePK( p_institutionCode, p_currencyCode );
        MutualFundExchangeRateRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MutualFundExchangeRatePK)}および{@@link #forRow(MutualFundExchangeRateRow)}を使用してください。 
   */
    public static MutualFundExchangeRateDao findDaoByPk( MutualFundExchangeRatePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundExchangeRateRow row = findRowByPk( pk );
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
   * p_institutionCode, p_currencyCode, and にて指定の値から一意の{@@link MutualFundExchangeRateRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_currencyCode 検索対象であるp_currencyCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_currencyCode, and の値と一致する{@@link MutualFundExchangeRateRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MutualFundExchangeRateRow findRowByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MutualFundExchangeRateRow.TYPE,
            "institution_code=? and currency_code=?",
            null,
            new Object[] { p_institutionCode, p_currencyCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MutualFundExchangeRateRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MutualFundExchangeRateDao.findRowsByInstitutionCodeCurrencyCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeCurrencyCode(String, String)}および{@@link #forRow(MutualFundExchangeRateRow)}を使用してください。 
   */
    public static MutualFundExchangeRateDao findDaoByInstitutionCodeCurrencyCode( String p_institutionCode, String p_currencyCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCurrencyCode( p_institutionCode, p_currencyCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
