head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointConvertMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.point.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link PointConvertMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PointConvertMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see PointConvertMasterPK 
 * @@see PointConvertMasterRow 
 */
public class PointConvertMasterDao extends DataAccessObject {


  /** 
   * この{@@link PointConvertMasterDao}に関連する型指定のRowオブジェクト 
   */
    private PointConvertMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PointConvertMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link PointConvertMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PointConvertMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PointConvertMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PointConvertMasterRow )
                return new PointConvertMasterDao( (PointConvertMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PointConvertMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PointConvertMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PointConvertMasterRow}オブジェクト 
    */
    protected PointConvertMasterDao( PointConvertMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PointConvertMasterRow}オブジェクトを取得します。
   */
    public PointConvertMasterRow getPointConvertMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link PointConvertMasterRow}オブジェクトから{@@link PointConvertMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link PointConvertMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PointConvertMasterDao}取得のために指定の{@@link PointConvertMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PointConvertMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PointConvertMasterDao forRow( PointConvertMasterRow row ) throws java.lang.IllegalArgumentException {
        return (PointConvertMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PointConvertMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PointConvertMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PointConvertMasterPK}やデータベースレコードとして挿入される{@@link PointConvertMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PointConvertMasterRow.TYPE );
    }


  /** 
   * {@@link PointConvertMasterRow}を一意に特定する{@@link PointConvertMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PointConvertMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PointConvertMasterParams}オブジェクトの主キーとして利用可能な{@@link PointConvertMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PointConvertMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PointConvertMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fundType 検索対象であるp_fundTypeフィールドの値
   * @@param p_tranType 検索対象であるp_tranTypeフィールドの値
   * @@param p_buySellDiv 検索対象であるp_buySellDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointConvertMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointConvertMasterRow findRowByPk( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        PointConvertMasterPK pk = new PointConvertMasterPK( p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPointConvertMasterPKオブジェクトから{@@link PointConvertMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPointConvertMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointConvertMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointConvertMasterRow findRowByPk( PointConvertMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PointConvertMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(PointConvertMasterRow)}を使用してください。 
   */
    public static PointConvertMasterDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        PointConvertMasterPK pk = new PointConvertMasterPK( p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv );
        PointConvertMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PointConvertMasterPK)}および{@@link #forRow(PointConvertMasterRow)}を使用してください。 
   */
    public static PointConvertMasterDao findDaoByPk( PointConvertMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PointConvertMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv, and にて指定の値から一意の{@@link PointConvertMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fundType 検索対象であるp_fundTypeフィールドの値
   * @@param p_tranType 検索対象であるp_tranTypeフィールドの値
   * @@param p_buySellDiv 検索対象であるp_buySellDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv, and の値と一致する{@@link PointConvertMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PointConvertMasterRow findRowByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PointConvertMasterRow.TYPE,
            "institution_code=? and branch_code=? and fund_type=? and tran_type=? and buy_sell_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PointConvertMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PointConvertMasterDao.findRowsByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv(String, String, String, String, String)}および{@@link #forRow(PointConvertMasterRow)}を使用してください。 
   */
    public static PointConvertMasterDao findDaoByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv( String p_institutionCode, String p_branchCode, String p_fundType, String p_tranType, String p_buySellDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFundTypeTranTypeBuySellDiv( p_institutionCode, p_branchCode, p_fundType, p_tranType, p_buySellDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
