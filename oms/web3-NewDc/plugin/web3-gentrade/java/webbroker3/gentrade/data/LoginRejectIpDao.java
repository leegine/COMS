head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.41.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginRejectIpDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link LoginRejectIpDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link LoginRejectIpRow}インスタンスへ関連付けることができます。 
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
 * @@see LoginRejectIpPK 
 * @@see LoginRejectIpRow 
 */
public class LoginRejectIpDao extends DataAccessObject {


  /** 
   * この{@@link LoginRejectIpDao}に関連する型指定のRowオブジェクト 
   */
    private LoginRejectIpRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link LoginRejectIpRow}と仮定される{@@link DataAccessObject}から新たに{@@link LoginRejectIpDao}を返します。 
         * @@return 指定のRowに結びつく{@@link LoginRejectIpDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link LoginRejectIpRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof LoginRejectIpRow )
                return new LoginRejectIpDao( (LoginRejectIpRow) row );
            throw new java.lang.IllegalArgumentException( "Not a LoginRejectIpRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link LoginRejectIpRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link LoginRejectIpRow}オブジェクト 
    */
    protected LoginRejectIpDao( LoginRejectIpRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link LoginRejectIpRow}オブジェクトを取得します。
   */
    public LoginRejectIpRow getLoginRejectIpRow() {
        return row;
    }


  /** 
   * 指定の{@@link LoginRejectIpRow}オブジェクトから{@@link LoginRejectIpDao}オブジェクトを作成します。 
   * これは実際の{@@link LoginRejectIpRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link LoginRejectIpDao}取得のために指定の{@@link LoginRejectIpRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link LoginRejectIpDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static LoginRejectIpDao forRow( LoginRejectIpRow row ) throws java.lang.IllegalArgumentException {
        return (LoginRejectIpDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link LoginRejectIpRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link LoginRejectIpRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link LoginRejectIpPK}やデータベースレコードとして挿入される{@@link LoginRejectIpParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( LoginRejectIpRow.TYPE );
    }


  /** 
   * {@@link LoginRejectIpRow}を一意に特定する{@@link LoginRejectIpPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link LoginRejectIpRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link LoginRejectIpParams}オブジェクトの主キーとして利用可能な{@@link LoginRejectIpPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static LoginRejectIpPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new LoginRejectIpPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link LoginRejectIpRow}オブジェクトを検索します。 
   * 
   * @@param p_loginRejectId 検索対象であるp_loginRejectIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link LoginRejectIpRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static LoginRejectIpRow findRowByPk( long p_loginRejectId ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginRejectIpPK pk = new LoginRejectIpPK( p_loginRejectId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のLoginRejectIpPKオブジェクトから{@@link LoginRejectIpRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するLoginRejectIpPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link LoginRejectIpRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static LoginRejectIpRow findRowByPk( LoginRejectIpPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (LoginRejectIpRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(LoginRejectIpRow)}を使用してください。 
   */
    public static LoginRejectIpDao findDaoByPk( long p_loginRejectId ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginRejectIpPK pk = new LoginRejectIpPK( p_loginRejectId );
        LoginRejectIpRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(LoginRejectIpPK)}および{@@link #forRow(LoginRejectIpRow)}を使用してください。 
   */
    public static LoginRejectIpDao findDaoByPk( LoginRejectIpPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginRejectIpRow row = findRowByPk( pk );
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
   * p_loginRejectId, and にて指定の値から一意の{@@link LoginRejectIpRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_loginRejectId 検索対象であるp_loginRejectIdフィールドの値
   * 
   * @@return 引数指定のp_loginRejectId, and の値と一致する{@@link LoginRejectIpRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static LoginRejectIpRow findRowByLoginRejectId( long p_loginRejectId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LoginRejectIpRow.TYPE,
            "login_reject_id=?",
            null,
            new Object[] { new Long(p_loginRejectId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LoginRejectIpRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LoginRejectIpDao.findRowsByLoginRejectId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByLoginRejectId(long)}および{@@link #forRow(LoginRejectIpRow)}を使用してください。 
   */
    public static LoginRejectIpDao findDaoByLoginRejectId( long p_loginRejectId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByLoginRejectId( p_loginRejectId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_ipAddress, p_status, p_appliStartTimestamp, p_appliEndTimestamp, and にて指定の値に一致する{@@link LoginRejectIpRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_ipAddress 検索対象であるp_ipAddressフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_appliStartTimestamp 検索対象であるp_appliStartTimestampフィールドの値
   * @@param p_appliEndTimestamp 検索対象であるp_appliEndTimestampフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_ipAddress, p_status, p_appliStartTimestamp, p_appliEndTimestamp, and の値と一致する{@@link LoginRejectIpRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeIpAddressStatusAppliStartTimestampAppliEndTimestamp( String p_institutionCode, String p_ipAddress, String p_status, java.sql.Timestamp p_appliStartTimestamp, java.sql.Timestamp p_appliEndTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LoginRejectIpRow.TYPE,
            "institution_code=? and ip_address=? and status=? and appli_start_timestamp=? and appli_end_timestamp=?",
            null,
            new Object[] { p_institutionCode, p_ipAddress, p_status, p_appliStartTimestamp, p_appliEndTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeIpAddressStatusAppliStartTimestampAppliEndTimestamp(String, String, String, java.sql.Timestamp, java.sql.Timestamp)}および{@@link #forRow(LoginRejectIpRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeIpAddressStatusAppliStartTimestampAppliEndTimestamp( String p_institutionCode, String p_ipAddress, String p_status, java.sql.Timestamp p_appliStartTimestamp, java.sql.Timestamp p_appliEndTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeIpAddressStatusAppliStartTimestampAppliEndTimestamp( p_institutionCode, p_ipAddress, p_status, p_appliStartTimestamp, p_appliEndTimestamp ) );
    }


  /** 
   * p_institutionCode, p_status, p_appliEndTimestamp, and にて指定の値に一致する{@@link LoginRejectIpRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_appliEndTimestamp 検索対象であるp_appliEndTimestampフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_status, p_appliEndTimestamp, and の値と一致する{@@link LoginRejectIpRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeStatusAppliEndTimestamp( String p_institutionCode, String p_status, java.sql.Timestamp p_appliEndTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LoginRejectIpRow.TYPE,
            "institution_code=? and status=? and appli_end_timestamp=?",
            null,
            new Object[] { p_institutionCode, p_status, p_appliEndTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeStatusAppliEndTimestamp(String, String, java.sql.Timestamp)}および{@@link #forRow(LoginRejectIpRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeStatusAppliEndTimestamp( String p_institutionCode, String p_status, java.sql.Timestamp p_appliEndTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeStatusAppliEndTimestamp( p_institutionCode, p_status, p_appliEndTimestamp ) );
    }

}
@
