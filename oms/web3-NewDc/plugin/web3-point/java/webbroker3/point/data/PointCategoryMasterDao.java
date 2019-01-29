head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointCategoryMasterDao.java;


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
 * {@@link PointCategoryMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PointCategoryMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see PointCategoryMasterPK 
 * @@see PointCategoryMasterRow 
 */
public class PointCategoryMasterDao extends DataAccessObject {


  /** 
   * この{@@link PointCategoryMasterDao}に関連する型指定のRowオブジェクト 
   */
    private PointCategoryMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PointCategoryMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link PointCategoryMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PointCategoryMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PointCategoryMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PointCategoryMasterRow )
                return new PointCategoryMasterDao( (PointCategoryMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PointCategoryMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PointCategoryMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PointCategoryMasterRow}オブジェクト 
    */
    protected PointCategoryMasterDao( PointCategoryMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PointCategoryMasterRow}オブジェクトを取得します。
   */
    public PointCategoryMasterRow getPointCategoryMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link PointCategoryMasterRow}オブジェクトから{@@link PointCategoryMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link PointCategoryMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PointCategoryMasterDao}取得のために指定の{@@link PointCategoryMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PointCategoryMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PointCategoryMasterDao forRow( PointCategoryMasterRow row ) throws java.lang.IllegalArgumentException {
        return (PointCategoryMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PointCategoryMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PointCategoryMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PointCategoryMasterPK}やデータベースレコードとして挿入される{@@link PointCategoryMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PointCategoryMasterRow.TYPE );
    }


  /** 
   * {@@link PointCategoryMasterRow}を一意に特定する{@@link PointCategoryMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PointCategoryMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PointCategoryMasterParams}オブジェクトの主キーとして利用可能な{@@link PointCategoryMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PointCategoryMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PointCategoryMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_categoryNo 検索対象であるp_categoryNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointCategoryMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointCategoryMasterRow findRowByPk( String p_institutionCode, int p_categoryNo ) throws DataFindException, DataQueryException, DataNetworkException {
        PointCategoryMasterPK pk = new PointCategoryMasterPK( p_institutionCode, p_categoryNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPointCategoryMasterPKオブジェクトから{@@link PointCategoryMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPointCategoryMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointCategoryMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointCategoryMasterRow findRowByPk( PointCategoryMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PointCategoryMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,int)}および{@@link #forRow(PointCategoryMasterRow)}を使用してください。 
   */
    public static PointCategoryMasterDao findDaoByPk( String p_institutionCode, int p_categoryNo ) throws DataFindException, DataQueryException, DataNetworkException {
        PointCategoryMasterPK pk = new PointCategoryMasterPK( p_institutionCode, p_categoryNo );
        PointCategoryMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PointCategoryMasterPK)}および{@@link #forRow(PointCategoryMasterRow)}を使用してください。 
   */
    public static PointCategoryMasterDao findDaoByPk( PointCategoryMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PointCategoryMasterRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link PointCategoryMasterDao}に関連する{@@link PointCategoryMasterRow}の外部キーがある{@@link PointPremiumMasterRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link PointCategoryMasterDao}に関連する{@@link PointCategoryMasterRow}の外部キーがある{@@link PointPremiumMasterRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchPointPremiumMasterRowsByInstitutionCodeCategoryNo() throws DataNetworkException, DataQueryException  {
        return PointPremiumMasterDao.findRowsByInstitutionCodeCategoryNo( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchPointPremiumMasterRowsByInstitutionCodeCategoryNo()}および{@@link #forRow(PointCategoryMasterRow)}を使用してください。 
   */
    public List fetchPointPremiumMasterDaosByInstitutionCodeCategoryNo() throws DataNetworkException, DataQueryException  {
        return PointPremiumMasterDao.findDaosByInstitutionCodeCategoryNo( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchPointPremiumMasterRowsByInstitutionCodeCategoryNo()}および{@@link #forRow(PointCategoryMasterRow)}を使用してください。 
   */
    public List fetchPointPremiumMasterDaosInstitutionCodeCategoryNo() throws DataNetworkException, DataQueryException  {
        return fetchPointPremiumMasterDaosByInstitutionCodeCategoryNo();
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
   * p_institutionCode, p_categoryNo, and にて指定の値から一意の{@@link PointCategoryMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_categoryNo 検索対象であるp_categoryNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_categoryNo, and の値と一致する{@@link PointCategoryMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PointCategoryMasterRow findRowByInstitutionCodeCategoryNo( String p_institutionCode, int p_categoryNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PointCategoryMasterRow.TYPE,
            "institution_code=? and category_no=?",
            null,
            new Object[] { p_institutionCode, new Integer(p_categoryNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PointCategoryMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PointCategoryMasterDao.findRowsByInstitutionCodeCategoryNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeCategoryNo(String, int)}および{@@link #forRow(PointCategoryMasterRow)}を使用してください。 
   */
    public static PointCategoryMasterDao findDaoByInstitutionCodeCategoryNo( String p_institutionCode, int p_categoryNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCategoryNo( p_institutionCode, p_categoryNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
