head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	ForeignCostDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link ForeignCostDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ForeignCostRow}インスタンスへ関連付けることができます。 
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
 * @@see ForeignCostPK 
 * @@see ForeignCostRow 
 */
public class ForeignCostDao extends DataAccessObject {


  /** 
   * この{@@link ForeignCostDao}に関連する型指定のRowオブジェクト 
   */
    private ForeignCostRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ForeignCostRow}と仮定される{@@link DataAccessObject}から新たに{@@link ForeignCostDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ForeignCostDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ForeignCostRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ForeignCostRow )
                return new ForeignCostDao( (ForeignCostRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ForeignCostRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ForeignCostRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ForeignCostRow}オブジェクト 
    */
    protected ForeignCostDao( ForeignCostRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ForeignCostRow}オブジェクトを取得します。
   */
    public ForeignCostRow getForeignCostRow() {
        return row;
    }


  /** 
   * 指定の{@@link ForeignCostRow}オブジェクトから{@@link ForeignCostDao}オブジェクトを作成します。 
   * これは実際の{@@link ForeignCostRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ForeignCostDao}取得のために指定の{@@link ForeignCostRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ForeignCostDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ForeignCostDao forRow( ForeignCostRow row ) throws java.lang.IllegalArgumentException {
        return (ForeignCostDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ForeignCostRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ForeignCostRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ForeignCostPK}やデータベースレコードとして挿入される{@@link ForeignCostParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ForeignCostRow.TYPE );
    }


  /** 
   * {@@link ForeignCostRow}を一意に特定する{@@link ForeignCostPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ForeignCostRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ForeignCostParams}オブジェクトの主キーとして利用可能な{@@link ForeignCostPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ForeignCostPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ForeignCostRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_costDiv 検索対象であるp_costDivフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * @@param p_amountFrom 検索対象であるp_amountFromフィールドの値
   * @@param p_sideDiv 検索対象であるp_sideDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ForeignCostRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ForeignCostRow findRowByPk( String p_institutionCode, String p_marketCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        ForeignCostPK pk = new ForeignCostPK( p_institutionCode, p_marketCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のForeignCostPKオブジェクトから{@@link ForeignCostRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するForeignCostPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ForeignCostRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ForeignCostRow findRowByPk( ForeignCostPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ForeignCostRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,java.sql.Timestamp,double,String)}および{@@link #forRow(ForeignCostRow)}を使用してください。 
   */
    public static ForeignCostDao findDaoByPk( String p_institutionCode, String p_marketCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        ForeignCostPK pk = new ForeignCostPK( p_institutionCode, p_marketCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv );
        ForeignCostRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ForeignCostPK)}および{@@link #forRow(ForeignCostRow)}を使用してください。 
   */
    public static ForeignCostDao findDaoByPk( ForeignCostPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ForeignCostRow row = findRowByPk( pk );
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
   * p_institutionCode, p_marketCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv, and にて指定の値から一意の{@@link ForeignCostRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_costDiv 検索対象であるp_costDivフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * @@param p_amountFrom 検索対象であるp_amountFromフィールドの値
   * @@param p_sideDiv 検索対象であるp_sideDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_marketCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv, and の値と一致する{@@link ForeignCostRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ForeignCostRow findRowByInstitutionCodeMarketCodeCostDivAppliStartDateAmountFromSideDiv( String p_institutionCode, String p_marketCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ForeignCostRow.TYPE,
            "institution_code=? and market_code=? and cost_div=? and appli_start_date=? and amount_from=? and side_div=?",
            null,
            new Object[] { p_institutionCode, p_marketCode, p_costDiv, p_appliStartDate, new Double(p_amountFrom), p_sideDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ForeignCostRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ForeignCostDao.findRowsByInstitutionCodeMarketCodeCostDivAppliStartDateAmountFromSideDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeMarketCodeCostDivAppliStartDateAmountFromSideDiv(String, String, String, java.sql.Timestamp, double, String)}および{@@link #forRow(ForeignCostRow)}を使用してください。 
   */
    public static ForeignCostDao findDaoByInstitutionCodeMarketCodeCostDivAppliStartDateAmountFromSideDiv( String p_institutionCode, String p_marketCode, String p_costDiv, java.sql.Timestamp p_appliStartDate, double p_amountFrom, String p_sideDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCodeCostDivAppliStartDateAmountFromSideDiv( p_institutionCode, p_marketCode, p_costDiv, p_appliStartDate, p_amountFrom, p_sideDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
