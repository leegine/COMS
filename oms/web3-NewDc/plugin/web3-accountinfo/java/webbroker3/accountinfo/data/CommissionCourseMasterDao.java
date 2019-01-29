head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommissionCourseMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommissionCourseMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CommissionCourseMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see CommissionCourseMasterPK 
 * @@see CommissionCourseMasterRow 
 */
public class CommissionCourseMasterDao extends DataAccessObject {


  /** 
   * この{@@link CommissionCourseMasterDao}に関連する型指定のRowオブジェクト 
   */
    private CommissionCourseMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CommissionCourseMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link CommissionCourseMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CommissionCourseMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CommissionCourseMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommissionCourseMasterRow )
                return new CommissionCourseMasterDao( (CommissionCourseMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommissionCourseMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommissionCourseMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CommissionCourseMasterRow}オブジェクト 
    */
    protected CommissionCourseMasterDao( CommissionCourseMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CommissionCourseMasterRow}オブジェクトを取得します。
   */
    public CommissionCourseMasterRow getCommissionCourseMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link CommissionCourseMasterRow}オブジェクトから{@@link CommissionCourseMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link CommissionCourseMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CommissionCourseMasterDao}取得のために指定の{@@link CommissionCourseMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CommissionCourseMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CommissionCourseMasterDao forRow( CommissionCourseMasterRow row ) throws java.lang.IllegalArgumentException {
        return (CommissionCourseMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommissionCourseMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CommissionCourseMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CommissionCourseMasterPK}やデータベースレコードとして挿入される{@@link CommissionCourseMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommissionCourseMasterRow.TYPE );
    }


  /** 
   * {@@link CommissionCourseMasterRow}を一意に特定する{@@link CommissionCourseMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CommissionCourseMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CommissionCourseMasterParams}オブジェクトの主キーとして利用可能な{@@link CommissionCourseMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CommissionCourseMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CommissionCourseMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_commissionCourseDiv 検索対象であるp_commissionCourseDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommissionCourseMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommissionCourseMasterRow findRowByPk( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseMasterPK pk = new CommissionCourseMasterPK( p_institutionCode, p_commProductCode, p_commissionCourseDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCommissionCourseMasterPKオブジェクトから{@@link CommissionCourseMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCommissionCourseMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommissionCourseMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommissionCourseMasterRow findRowByPk( CommissionCourseMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommissionCourseMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(CommissionCourseMasterRow)}を使用してください。 
   */
    public static CommissionCourseMasterDao findDaoByPk( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseMasterPK pk = new CommissionCourseMasterPK( p_institutionCode, p_commProductCode, p_commissionCourseDiv );
        CommissionCourseMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CommissionCourseMasterPK)}および{@@link #forRow(CommissionCourseMasterRow)}を使用してください。 
   */
    public static CommissionCourseMasterDao findDaoByPk( CommissionCourseMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_commProductCode, p_commissionCourseDiv, and にて指定の値から一意の{@@link CommissionCourseMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_commissionCourseDiv 検索対象であるp_commissionCourseDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_commProductCode, p_commissionCourseDiv, and の値と一致する{@@link CommissionCourseMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CommissionCourseMasterRow findRowByInstitutionCodeCommProductCodeCommissionCourseDiv( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommissionCourseMasterRow.TYPE,
            "institution_code=? and comm_product_code=? and commission_course_div=?",
            null,
            new Object[] { p_institutionCode, p_commProductCode, p_commissionCourseDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommissionCourseMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommissionCourseMasterDao.findRowsByInstitutionCodeCommProductCodeCommissionCourseDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeCommProductCodeCommissionCourseDiv(String, String, String)}および{@@link #forRow(CommissionCourseMasterRow)}を使用してください。 
   */
    public static CommissionCourseMasterDao findDaoByInstitutionCodeCommProductCodeCommissionCourseDiv( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCommProductCodeCommissionCourseDiv( p_institutionCode, p_commProductCode, p_commissionCourseDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
