head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginHistoryPastDao.java;


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
 * {@@link LoginHistoryPastDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link LoginHistoryPastRow}インスタンスへ関連付けることができます。 
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
 * @@see LoginHistoryPastPK 
 * @@see LoginHistoryPastRow 
 */
public class LoginHistoryPastDao extends DataAccessObject {


  /** 
   * この{@@link LoginHistoryPastDao}に関連する型指定のRowオブジェクト 
   */
    private LoginHistoryPastRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link LoginHistoryPastRow}と仮定される{@@link DataAccessObject}から新たに{@@link LoginHistoryPastDao}を返します。 
         * @@return 指定のRowに結びつく{@@link LoginHistoryPastDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link LoginHistoryPastRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof LoginHistoryPastRow )
                return new LoginHistoryPastDao( (LoginHistoryPastRow) row );
            throw new java.lang.IllegalArgumentException( "Not a LoginHistoryPastRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link LoginHistoryPastRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link LoginHistoryPastRow}オブジェクト 
    */
    protected LoginHistoryPastDao( LoginHistoryPastRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link LoginHistoryPastRow}オブジェクトを取得します。
   */
    public LoginHistoryPastRow getLoginHistoryPastRow() {
        return row;
    }


  /** 
   * 指定の{@@link LoginHistoryPastRow}オブジェクトから{@@link LoginHistoryPastDao}オブジェクトを作成します。 
   * これは実際の{@@link LoginHistoryPastRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link LoginHistoryPastDao}取得のために指定の{@@link LoginHistoryPastRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link LoginHistoryPastDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static LoginHistoryPastDao forRow( LoginHistoryPastRow row ) throws java.lang.IllegalArgumentException {
        return (LoginHistoryPastDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link LoginHistoryPastRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link LoginHistoryPastRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link LoginHistoryPastPK}やデータベースレコードとして挿入される{@@link LoginHistoryPastParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( LoginHistoryPastRow.TYPE );
    }


  /** 
   * {@@link LoginHistoryPastRow}を一意に特定する{@@link LoginHistoryPastPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link LoginHistoryPastRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link LoginHistoryPastParams}オブジェクトの主キーとして利用可能な{@@link LoginHistoryPastPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static LoginHistoryPastPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new LoginHistoryPastPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link LoginHistoryPastRow}オブジェクトを検索します。 
   * 
   * @@param p_loginHistoryId 検索対象であるp_loginHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link LoginHistoryPastRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static LoginHistoryPastRow findRowByPk( long p_loginHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginHistoryPastPK pk = new LoginHistoryPastPK( p_loginHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のLoginHistoryPastPKオブジェクトから{@@link LoginHistoryPastRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するLoginHistoryPastPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link LoginHistoryPastRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static LoginHistoryPastRow findRowByPk( LoginHistoryPastPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (LoginHistoryPastRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(LoginHistoryPastRow)}を使用してください。 
   */
    public static LoginHistoryPastDao findDaoByPk( long p_loginHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginHistoryPastPK pk = new LoginHistoryPastPK( p_loginHistoryId );
        LoginHistoryPastRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(LoginHistoryPastPK)}および{@@link #forRow(LoginHistoryPastRow)}を使用してください。 
   */
    public static LoginHistoryPastDao findDaoByPk( LoginHistoryPastPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginHistoryPastRow row = findRowByPk( pk );
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
   * p_loginHistoryId, and にて指定の値から一意の{@@link LoginHistoryPastRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_loginHistoryId 検索対象であるp_loginHistoryIdフィールドの値
   * 
   * @@return 引数指定のp_loginHistoryId, and の値と一致する{@@link LoginHistoryPastRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static LoginHistoryPastRow findRowByLoginHistoryId( long p_loginHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LoginHistoryPastRow.TYPE,
            "login_history_id=?",
            null,
            new Object[] { new Long(p_loginHistoryId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LoginHistoryPastRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LoginHistoryPastDao.findRowsByLoginHistoryId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByLoginHistoryId(long)}および{@@link #forRow(LoginHistoryPastRow)}を使用してください。 
   */
    public static LoginHistoryPastDao findDaoByLoginHistoryId( long p_loginHistoryId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByLoginHistoryId( p_loginHistoryId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_loginTimestamp, p_loginFailure, p_ipAddress, and にて指定の値に一致する{@@link LoginHistoryPastRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_loginTimestamp 検索対象であるp_loginTimestampフィールドの値
   * @@param p_loginFailure 検索対象であるp_loginFailureフィールドの値
   * @@param p_ipAddress 検索対象であるp_ipAddressフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_loginTimestamp, p_loginFailure, p_ipAddress, and の値と一致する{@@link LoginHistoryPastRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeLoginTimestampLoginFailureIpAddress( String p_institutionCode, java.sql.Timestamp p_loginTimestamp, String p_loginFailure, String p_ipAddress ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            LoginHistoryPastRow.TYPE,
            "institution_code=? and login_timestamp=? and login_failure=? and ip_address=?",
            null,
            new Object[] { p_institutionCode, p_loginTimestamp, p_loginFailure, p_ipAddress } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeLoginTimestampLoginFailureIpAddress(String, java.sql.Timestamp, String, String)}および{@@link #forRow(LoginHistoryPastRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeLoginTimestampLoginFailureIpAddress( String p_institutionCode, java.sql.Timestamp p_loginTimestamp, String p_loginFailure, String p_ipAddress ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeLoginTimestampLoginFailureIpAddress( p_institutionCode, p_loginTimestamp, p_loginFailure, p_ipAddress ) );
    }

}
@
