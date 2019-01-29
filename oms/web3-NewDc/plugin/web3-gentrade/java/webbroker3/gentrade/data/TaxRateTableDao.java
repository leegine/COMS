head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TaxRateTableDao.java;


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
 * {@@link TaxRateTableDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TaxRateTableRow}インスタンスへ関連付けることができます。 
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
 * @@see TaxRateTablePK 
 * @@see TaxRateTableRow 
 */
public class TaxRateTableDao extends DataAccessObject {


  /** 
   * この{@@link TaxRateTableDao}に関連する型指定のRowオブジェクト 
   */
    private TaxRateTableRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TaxRateTableRow}と仮定される{@@link DataAccessObject}から新たに{@@link TaxRateTableDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TaxRateTableDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TaxRateTableRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TaxRateTableRow )
                return new TaxRateTableDao( (TaxRateTableRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TaxRateTableRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TaxRateTableRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TaxRateTableRow}オブジェクト 
    */
    protected TaxRateTableDao( TaxRateTableRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TaxRateTableRow}オブジェクトを取得します。
   */
    public TaxRateTableRow getTaxRateTableRow() {
        return row;
    }


  /** 
   * 指定の{@@link TaxRateTableRow}オブジェクトから{@@link TaxRateTableDao}オブジェクトを作成します。 
   * これは実際の{@@link TaxRateTableRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TaxRateTableDao}取得のために指定の{@@link TaxRateTableRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TaxRateTableDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TaxRateTableDao forRow( TaxRateTableRow row ) throws java.lang.IllegalArgumentException {
        return (TaxRateTableDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TaxRateTableRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TaxRateTableRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TaxRateTablePK}やデータベースレコードとして挿入される{@@link TaxRateTableParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TaxRateTableRow.TYPE );
    }


  /** 
   * {@@link TaxRateTableRow}を一意に特定する{@@link TaxRateTablePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TaxRateTableRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TaxRateTableParams}オブジェクトの主キーとして利用可能な{@@link TaxRateTablePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TaxRateTablePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TaxRateTableRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_taxType 検索対象であるp_taxTypeフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TaxRateTableRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TaxRateTableRow findRowByPk( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        TaxRateTablePK pk = new TaxRateTablePK( p_institutionCode, p_taxType, p_appliStartDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTaxRateTablePKオブジェクトから{@@link TaxRateTableRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTaxRateTablePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TaxRateTableRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TaxRateTableRow findRowByPk( TaxRateTablePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TaxRateTableRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,java.sql.Timestamp)}および{@@link #forRow(TaxRateTableRow)}を使用してください。 
   */
    public static TaxRateTableDao findDaoByPk( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        TaxRateTablePK pk = new TaxRateTablePK( p_institutionCode, p_taxType, p_appliStartDate );
        TaxRateTableRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TaxRateTablePK)}および{@@link #forRow(TaxRateTableRow)}を使用してください。 
   */
    public static TaxRateTableDao findDaoByPk( TaxRateTablePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TaxRateTableRow row = findRowByPk( pk );
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
   * p_institutionCode, p_taxType, p_appliStartDate, and にて指定の値から一意の{@@link TaxRateTableRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_taxType 検索対象であるp_taxTypeフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_taxType, p_appliStartDate, and の値と一致する{@@link TaxRateTableRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TaxRateTableRow findRowByInstitutionCodeTaxTypeAppliStartDate( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TaxRateTableRow.TYPE,
            "institution_code=? and tax_type=? and appli_start_date=?",
            null,
            new Object[] { p_institutionCode, p_taxType, p_appliStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TaxRateTableRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TaxRateTableDao.findRowsByInstitutionCodeTaxTypeAppliStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeTaxTypeAppliStartDate(String, String, java.sql.Timestamp)}および{@@link #forRow(TaxRateTableRow)}を使用してください。 
   */
    public static TaxRateTableDao findDaoByInstitutionCodeTaxTypeAppliStartDate( String p_institutionCode, String p_taxType, java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeTaxTypeAppliStartDate( p_institutionCode, p_taxType, p_appliStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
