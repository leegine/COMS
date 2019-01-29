head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.49.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointPremiumMasterDao.java;


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
 * {@@link PointPremiumMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PointPremiumMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see PointPremiumMasterPK 
 * @@see PointPremiumMasterRow 
 */
public class PointPremiumMasterDao extends DataAccessObject {


  /** 
   * この{@@link PointPremiumMasterDao}に関連する型指定のRowオブジェクト 
   */
    private PointPremiumMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PointPremiumMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link PointPremiumMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PointPremiumMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PointPremiumMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PointPremiumMasterRow )
                return new PointPremiumMasterDao( (PointPremiumMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PointPremiumMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PointPremiumMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PointPremiumMasterRow}オブジェクト 
    */
    protected PointPremiumMasterDao( PointPremiumMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PointPremiumMasterRow}オブジェクトを取得します。
   */
    public PointPremiumMasterRow getPointPremiumMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link PointPremiumMasterRow}オブジェクトから{@@link PointPremiumMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link PointPremiumMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PointPremiumMasterDao}取得のために指定の{@@link PointPremiumMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PointPremiumMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PointPremiumMasterDao forRow( PointPremiumMasterRow row ) throws java.lang.IllegalArgumentException {
        return (PointPremiumMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PointPremiumMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PointPremiumMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PointPremiumMasterPK}やデータベースレコードとして挿入される{@@link PointPremiumMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PointPremiumMasterRow.TYPE );
    }


  /** 
   * {@@link PointPremiumMasterRow}を一意に特定する{@@link PointPremiumMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PointPremiumMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PointPremiumMasterParams}オブジェクトの主キーとして利用可能な{@@link PointPremiumMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PointPremiumMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PointPremiumMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_premiumNo 検索対象であるp_premiumNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointPremiumMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointPremiumMasterRow findRowByPk( String p_institutionCode, String p_premiumNo ) throws DataFindException, DataQueryException, DataNetworkException {
        PointPremiumMasterPK pk = new PointPremiumMasterPK( p_institutionCode, p_premiumNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPointPremiumMasterPKオブジェクトから{@@link PointPremiumMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPointPremiumMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointPremiumMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointPremiumMasterRow findRowByPk( PointPremiumMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PointPremiumMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(PointPremiumMasterRow)}を使用してください。 
   */
    public static PointPremiumMasterDao findDaoByPk( String p_institutionCode, String p_premiumNo ) throws DataFindException, DataQueryException, DataNetworkException {
        PointPremiumMasterPK pk = new PointPremiumMasterPK( p_institutionCode, p_premiumNo );
        PointPremiumMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PointPremiumMasterPK)}および{@@link #forRow(PointPremiumMasterRow)}を使用してください。 
   */
    public static PointPremiumMasterDao findDaoByPk( PointPremiumMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PointPremiumMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link PointPremiumMasterDao}に紐付く{@@link PointPremiumMasterRow}内で外部キーの関係をもつ{@@link PointCategoryMasterRow}を検索します。 
   * 
   * @@return {@@link PointPremiumMasterDao}と外部キーの関係にある{@@link PointCategoryMasterRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public PointCategoryMasterRow fetchPointCategoryMasterRowViaInstitutionCodeCategoryNo() throws DataNetworkException, DataFindException, DataQueryException  {
        PointCategoryMasterPK pk = new PointCategoryMasterPK( row.getInstitutionCode(), row.getCategoryNo() );
        Row row = PointCategoryMasterDao.findRowByPk( pk );
        if ( row != null && !(row instanceof PointCategoryMasterRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (PointCategoryMasterRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchPointCategoryMasterRowViaInstitutionCodeCategoryNo()}および{@@link #forRow(PointPremiumMasterRow)}を使用してください。 
   */
    public PointCategoryMasterDao fetchPointCategoryMasterDaoViaInstitutionCodeCategoryNo() throws DataNetworkException, DataFindException, DataQueryException  {
        PointCategoryMasterPK pk = new PointCategoryMasterPK( row.getInstitutionCode(), row.getCategoryNo() );
        DataAccessObject dao = PointCategoryMasterDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof PointCategoryMasterDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (PointCategoryMasterDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for PointCategoryMaster
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByInstitutionCodeCategoryNo(PointCategoryMasterRow)}を使用してください。 
   */
    public static List findRowsByInstitutionCodeCategoryNo( PointCategoryMasterDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeCategoryNo( dao.getPointCategoryMasterRow() );
    }


  /** 
   * {@@link PointCategoryMasterRow}と外部キーの関係にある{@@link PointPremiumMasterRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link PointCategoryMasterRow}オブジェクト 
   * @@return 指定の{@@link PointCategoryMasterRow}に外部キーを持つ{@@link PointPremiumMasterRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeCategoryNo( PointCategoryMasterRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeCategoryNo( row.getInstitutionCode(), row.getCategoryNo() );
    }


  /** 
   * {@@link PointCategoryMasterPK}と外部キーの関係にある{@@link PointPremiumMasterRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link PointCategoryMasterPK}オブジェクト 
   * @@return {@@link PointCategoryMasterPK}と外部キーが一致する値を持つ{@@link PointPremiumMasterRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeCategoryNo( PointCategoryMasterPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeCategoryNo( pk.institution_code, pk.category_no );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link PointPremiumMasterRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_categoryNo 検索対象であるp_categoryNoフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link PointPremiumMasterRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeCategoryNo( String p_institutionCode, int p_categoryNo  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PointPremiumMasterRow.TYPE,
            "institution_code=? and category_no=?",
            null,
            new Object[] { p_institutionCode, new Integer(p_categoryNo) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for PointCategoryMaster
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByInstitutionCodeCategoryNo(PointCategoryMasterRow)}および{@@link #forRow(PointPremiumMasterRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeCategoryNo( PointCategoryMasterDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeCategoryNo( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeCategoryNo(PointCategoryMasterRow)}および{@@link #forRow(PointPremiumMasterRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeCategoryNo( PointCategoryMasterRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeCategoryNo( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeCategoryNo(PointCategoryMasterPK)}および{@@link #forRow(PointPremiumMasterRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeCategoryNo( PointCategoryMasterPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeCategoryNo( pk.institution_code, pk.category_no ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeCategoryNo(String, int)}および{@@link #forRow(PointPremiumMasterRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeCategoryNo( String p_institutionCode, int p_categoryNo ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeCategoryNo( p_institutionCode, p_categoryNo ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_premiumNo, and にて指定の値から一意の{@@link PointPremiumMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_premiumNo 検索対象であるp_premiumNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_premiumNo, and の値と一致する{@@link PointPremiumMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PointPremiumMasterRow findRowByInstitutionCodePremiumNo( String p_institutionCode, String p_premiumNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PointPremiumMasterRow.TYPE,
            "institution_code=? and premium_no=?",
            null,
            new Object[] { p_institutionCode, p_premiumNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PointPremiumMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PointPremiumMasterDao.findRowsByInstitutionCodePremiumNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodePremiumNo(String, String)}および{@@link #forRow(PointPremiumMasterRow)}を使用してください。 
   */
    public static PointPremiumMasterDao findDaoByInstitutionCodePremiumNo( String p_institutionCode, String p_premiumNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodePremiumNo( p_institutionCode, p_premiumNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
