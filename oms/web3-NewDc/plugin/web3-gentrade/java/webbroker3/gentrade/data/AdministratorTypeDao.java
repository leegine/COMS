head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorTypeDao.java;


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
 * {@@link AdministratorTypeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AdministratorTypeRow}インスタンスへ関連付けることができます。 
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
 * @@see AdministratorTypePK 
 * @@see AdministratorTypeRow 
 */
public class AdministratorTypeDao extends DataAccessObject {


  /** 
   * この{@@link AdministratorTypeDao}に関連する型指定のRowオブジェクト 
   */
    private AdministratorTypeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AdministratorTypeRow}と仮定される{@@link DataAccessObject}から新たに{@@link AdministratorTypeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AdministratorTypeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AdministratorTypeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdministratorTypeRow )
                return new AdministratorTypeDao( (AdministratorTypeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdministratorTypeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdministratorTypeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AdministratorTypeRow}オブジェクト 
    */
    protected AdministratorTypeDao( AdministratorTypeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AdministratorTypeRow}オブジェクトを取得します。
   */
    public AdministratorTypeRow getAdministratorTypeRow() {
        return row;
    }


  /** 
   * 指定の{@@link AdministratorTypeRow}オブジェクトから{@@link AdministratorTypeDao}オブジェクトを作成します。 
   * これは実際の{@@link AdministratorTypeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AdministratorTypeDao}取得のために指定の{@@link AdministratorTypeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AdministratorTypeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AdministratorTypeDao forRow( AdministratorTypeRow row ) throws java.lang.IllegalArgumentException {
        return (AdministratorTypeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdministratorTypeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AdministratorTypeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AdministratorTypePK}やデータベースレコードとして挿入される{@@link AdministratorTypeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdministratorTypeRow.TYPE );
    }


  /** 
   * {@@link AdministratorTypeRow}を一意に特定する{@@link AdministratorTypePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AdministratorTypeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AdministratorTypeParams}オブジェクトの主キーとして利用可能な{@@link AdministratorTypePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AdministratorTypePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AdministratorTypeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_permissionLevel 検索対象であるp_permissionLevelフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdministratorTypeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdministratorTypeRow findRowByPk( String p_institutionCode, String p_permissionLevel ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorTypePK pk = new AdministratorTypePK( p_institutionCode, p_permissionLevel );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAdministratorTypePKオブジェクトから{@@link AdministratorTypeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAdministratorTypePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdministratorTypeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdministratorTypeRow findRowByPk( AdministratorTypePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdministratorTypeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(AdministratorTypeRow)}を使用してください。 
   */
    public static AdministratorTypeDao findDaoByPk( String p_institutionCode, String p_permissionLevel ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorTypePK pk = new AdministratorTypePK( p_institutionCode, p_permissionLevel );
        AdministratorTypeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AdministratorTypePK)}および{@@link #forRow(AdministratorTypeRow)}を使用してください。 
   */
    public static AdministratorTypeDao findDaoByPk( AdministratorTypePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorTypeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_permissionLevel, and にて指定の値から一意の{@@link AdministratorTypeRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_permissionLevel 検索対象であるp_permissionLevelフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_permissionLevel, and の値と一致する{@@link AdministratorTypeRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AdministratorTypeRow findRowByInstitutionCodePermissionLevel( String p_institutionCode, String p_permissionLevel ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorTypeRow.TYPE,
            "institution_code=? and permission_level=?",
            null,
            new Object[] { p_institutionCode, p_permissionLevel } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorTypeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorTypeDao.findRowsByInstitutionCodePermissionLevel(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodePermissionLevel(String, String)}および{@@link #forRow(AdministratorTypeRow)}を使用してください。 
   */
    public static AdministratorTypeDao findDaoByInstitutionCodePermissionLevel( String p_institutionCode, String p_permissionLevel ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodePermissionLevel( p_institutionCode, p_permissionLevel ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
