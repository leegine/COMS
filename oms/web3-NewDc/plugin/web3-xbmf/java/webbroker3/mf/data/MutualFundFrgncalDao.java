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
filename	MutualFundFrgncalDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MutualFundFrgncalDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MutualFundFrgncalRow}インスタンスへ関連付けることができます。 
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
 * @@see MutualFundFrgncalPK 
 * @@see MutualFundFrgncalRow 
 */
public class MutualFundFrgncalDao extends DataAccessObject {


  /** 
   * この{@@link MutualFundFrgncalDao}に関連する型指定のRowオブジェクト 
   */
    private MutualFundFrgncalRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MutualFundFrgncalRow}と仮定される{@@link DataAccessObject}から新たに{@@link MutualFundFrgncalDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MutualFundFrgncalDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MutualFundFrgncalRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundFrgncalRow )
                return new MutualFundFrgncalDao( (MutualFundFrgncalRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundFrgncalRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundFrgncalRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MutualFundFrgncalRow}オブジェクト 
    */
    protected MutualFundFrgncalDao( MutualFundFrgncalRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MutualFundFrgncalRow}オブジェクトを取得します。
   */
    public MutualFundFrgncalRow getMutualFundFrgncalRow() {
        return row;
    }


  /** 
   * 指定の{@@link MutualFundFrgncalRow}オブジェクトから{@@link MutualFundFrgncalDao}オブジェクトを作成します。 
   * これは実際の{@@link MutualFundFrgncalRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MutualFundFrgncalDao}取得のために指定の{@@link MutualFundFrgncalRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MutualFundFrgncalDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MutualFundFrgncalDao forRow( MutualFundFrgncalRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundFrgncalDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFundFrgncalRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MutualFundFrgncalRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MutualFundFrgncalPK}やデータベースレコードとして挿入される{@@link MutualFundFrgncalParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFundFrgncalRow.TYPE );
    }


  /** 
   * {@@link MutualFundFrgncalRow}を一意に特定する{@@link MutualFundFrgncalPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MutualFundFrgncalRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MutualFundFrgncalParams}オブジェクトの主キーとして利用可能な{@@link MutualFundFrgncalPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MutualFundFrgncalPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MutualFundFrgncalRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_holiday 検索対象であるp_holidayフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundFrgncalRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundFrgncalRow findRowByPk( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundFrgncalPK pk = new MutualFundFrgncalPK( p_institutionCode, p_productCode, p_holiday );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMutualFundFrgncalPKオブジェクトから{@@link MutualFundFrgncalRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMutualFundFrgncalPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundFrgncalRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundFrgncalRow findRowByPk( MutualFundFrgncalPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFundFrgncalRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,java.sql.Timestamp)}および{@@link #forRow(MutualFundFrgncalRow)}を使用してください。 
   */
    public static MutualFundFrgncalDao findDaoByPk( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundFrgncalPK pk = new MutualFundFrgncalPK( p_institutionCode, p_productCode, p_holiday );
        MutualFundFrgncalRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MutualFundFrgncalPK)}および{@@link #forRow(MutualFundFrgncalRow)}を使用してください。 
   */
    public static MutualFundFrgncalDao findDaoByPk( MutualFundFrgncalPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundFrgncalRow row = findRowByPk( pk );
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
   * p_institutionCode, p_productCode, p_holiday, and にて指定の値から一意の{@@link MutualFundFrgncalRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_holiday 検索対象であるp_holidayフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productCode, p_holiday, and の値と一致する{@@link MutualFundFrgncalRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MutualFundFrgncalRow findRowByInstitutionCodeProductCodeHoliday( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MutualFundFrgncalRow.TYPE,
            "institution_code=? and product_code=? and holiday=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_holiday } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MutualFundFrgncalRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MutualFundFrgncalDao.findRowsByInstitutionCodeProductCodeHoliday(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductCodeHoliday(String, String, java.sql.Timestamp)}および{@@link #forRow(MutualFundFrgncalRow)}を使用してください。 
   */
    public static MutualFundFrgncalDao findDaoByInstitutionCodeProductCodeHoliday( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeHoliday( p_institutionCode, p_productCode, p_holiday ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productCode, and にて指定の値に一致する{@@link MutualFundFrgncalRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productCode, and の値と一致する{@@link MutualFundFrgncalRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeProductCode( String p_institutionCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MutualFundFrgncalRow.TYPE,
            "institution_code=? and product_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeProductCode(String, String)}および{@@link #forRow(MutualFundFrgncalRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeProductCode( String p_institutionCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductCode( p_institutionCode, p_productCode ) );
    }

}
@
