head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorDao.java;


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
 * {@@link AdministratorDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AdministratorRow}インスタンスへ関連付けることができます。 
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
 * @@see AdministratorPK 
 * @@see AdministratorRow 
 */
public class AdministratorDao extends DataAccessObject {


  /** 
   * この{@@link AdministratorDao}に関連する型指定のRowオブジェクト 
   */
    private AdministratorRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AdministratorRow}と仮定される{@@link DataAccessObject}から新たに{@@link AdministratorDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AdministratorDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AdministratorRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdministratorRow )
                return new AdministratorDao( (AdministratorRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdministratorRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdministratorRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AdministratorRow}オブジェクト 
    */
    protected AdministratorDao( AdministratorRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AdministratorRow}オブジェクトを取得します。
   */
    public AdministratorRow getAdministratorRow() {
        return row;
    }


  /** 
   * 指定の{@@link AdministratorRow}オブジェクトから{@@link AdministratorDao}オブジェクトを作成します。 
   * これは実際の{@@link AdministratorRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AdministratorDao}取得のために指定の{@@link AdministratorRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AdministratorDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AdministratorDao forRow( AdministratorRow row ) throws java.lang.IllegalArgumentException {
        return (AdministratorDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdministratorRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AdministratorRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AdministratorPK}やデータベースレコードとして挿入される{@@link AdministratorParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdministratorRow.TYPE );
    }


  /** 
   * {@@link AdministratorRow}を一意に特定する{@@link AdministratorPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AdministratorRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AdministratorParams}オブジェクトの主キーとして利用可能な{@@link AdministratorPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AdministratorPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AdministratorPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AdministratorRow}オブジェクトを検索します。 
   * 
   * @@param p_administratorId 検索対象であるp_administratorIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdministratorRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdministratorRow findRowByPk( long p_administratorId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorPK pk = new AdministratorPK( p_administratorId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAdministratorPKオブジェクトから{@@link AdministratorRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAdministratorPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AdministratorRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AdministratorRow findRowByPk( AdministratorPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdministratorRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(AdministratorRow)}を使用してください。 
   */
    public static AdministratorDao findDaoByPk( long p_administratorId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorPK pk = new AdministratorPK( p_administratorId );
        AdministratorRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AdministratorPK)}および{@@link #forRow(AdministratorRow)}を使用してください。 
   */
    public static AdministratorDao findDaoByPk( AdministratorPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorRow row = findRowByPk( pk );
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
   * p_administratorId, and にて指定の値から一意の{@@link AdministratorRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_administratorId 検索対象であるp_administratorIdフィールドの値
   * 
   * @@return 引数指定のp_administratorId, and の値と一致する{@@link AdministratorRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AdministratorRow findRowByAdministratorId( long p_administratorId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorRow.TYPE,
            "administrator_id=?",
            null,
            new Object[] { new Long(p_administratorId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorDao.findRowsByAdministratorId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAdministratorId(long)}および{@@link #forRow(AdministratorRow)}を使用してください。 
   */
    public static AdministratorDao findDaoByAdministratorId( long p_administratorId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAdministratorId( p_administratorId ) );
    }


  /** 
   * p_institutionCode, p_administratorCode, and にて指定の値から一意の{@@link AdministratorRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_administratorCode 検索対象であるp_administratorCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_administratorCode, and の値と一致する{@@link AdministratorRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AdministratorRow findRowByInstitutionCodeAdministratorCode( String p_institutionCode, String p_administratorCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorRow.TYPE,
            "institution_code=? and administrator_code=?",
            null,
            new Object[] { p_institutionCode, p_administratorCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorDao.findRowsByInstitutionCodeAdministratorCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAdministratorCode(String, String)}および{@@link #forRow(AdministratorRow)}を使用してください。 
   */
    public static AdministratorDao findDaoByInstitutionCodeAdministratorCode( String p_institutionCode, String p_administratorCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAdministratorCode( p_institutionCode, p_administratorCode ) );
    }


  /** 
   * p_loginId, and にて指定の値から一意の{@@link AdministratorRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_loginId 検索対象であるp_loginIdフィールドの値
   * 
   * @@return 引数指定のp_loginId, and の値と一致する{@@link AdministratorRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AdministratorRow findRowByLoginId( long p_loginId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorRow.TYPE,
            "login_id=?",
            null,
            new Object[] { new Long(p_loginId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorDao.findRowsByLoginId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByLoginId(long)}および{@@link #forRow(AdministratorRow)}を使用してください。 
   */
    public static AdministratorDao findDaoByLoginId( long p_loginId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByLoginId( p_loginId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
