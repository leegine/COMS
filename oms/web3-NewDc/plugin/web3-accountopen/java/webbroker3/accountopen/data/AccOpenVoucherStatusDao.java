head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AccOpenVoucherStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenVoucherStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenVoucherStatusPK 
 * @@see AccOpenVoucherStatusRow 
 */
public class AccOpenVoucherStatusDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenVoucherStatusDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenVoucherStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenVoucherStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenVoucherStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenVoucherStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenVoucherStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenVoucherStatusRow )
                return new AccOpenVoucherStatusDao( (AccOpenVoucherStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenVoucherStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenVoucherStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenVoucherStatusRow}オブジェクト 
    */
    protected AccOpenVoucherStatusDao( AccOpenVoucherStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenVoucherStatusRow}オブジェクトを取得します。
   */
    public AccOpenVoucherStatusRow getAccOpenVoucherStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenVoucherStatusRow}オブジェクトから{@@link AccOpenVoucherStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenVoucherStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenVoucherStatusDao}取得のために指定の{@@link AccOpenVoucherStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenVoucherStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenVoucherStatusDao forRow( AccOpenVoucherStatusRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenVoucherStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenVoucherStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenVoucherStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenVoucherStatusPK}やデータベースレコードとして挿入される{@@link AccOpenVoucherStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenVoucherStatusRow.TYPE );
    }


  /** 
   * {@@link AccOpenVoucherStatusRow}を一意に特定する{@@link AccOpenVoucherStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenVoucherStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenVoucherStatusParams}オブジェクトの主キーとして利用可能な{@@link AccOpenVoucherStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenVoucherStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenVoucherStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenVoucherStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenVoucherStatusRow findRowByPk( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherStatusPK pk = new AccOpenVoucherStatusPK( p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenVoucherStatusPKオブジェクトから{@@link AccOpenVoucherStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenVoucherStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenVoucherStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenVoucherStatusRow findRowByPk( AccOpenVoucherStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenVoucherStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(AccOpenVoucherStatusRow)}を使用してください。 
   */
    public static AccOpenVoucherStatusDao findDaoByPk( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherStatusPK pk = new AccOpenVoucherStatusPK( p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo );
        AccOpenVoucherStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenVoucherStatusPK)}および{@@link #forRow(AccOpenVoucherStatusRow)}を使用してください。 
   */
    public static AccOpenVoucherStatusDao findDaoByPk( AccOpenVoucherStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenVoucherStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo, and にて指定の値から一意の{@@link AccOpenVoucherStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo, and の値と一致する{@@link AccOpenVoucherStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenVoucherStatusRow findRowByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "institution_code=? and acc_open_request_number=? and request_code=? and serial_no=?",
            null,
            new Object[] { p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenVoucherStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenVoucherStatusDao.findRowsByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo(String, String, String, String)}および{@@link #forRow(AccOpenVoucherStatusRow)}を使用してください。 
   */
    public static AccOpenVoucherStatusDao findDaoByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo( p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, and にて指定の値に一致する{@@link AccOpenVoucherStatusRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, and の値と一致する{@@link AccOpenVoucherStatusRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "institution_code=?",
            null,
            new Object[] { p_institutionCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCode(String)}および{@@link #forRow(AccOpenVoucherStatusRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCode( p_institutionCode ) );
    }


  /** 
   * p_accOpenRequestNumber, and にて指定の値に一致する{@@link AccOpenVoucherStatusRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_accOpenRequestNumber, and の値と一致する{@@link AccOpenVoucherStatusRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccOpenRequestNumber( String p_accOpenRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "acc_open_request_number=?",
            null,
            new Object[] { p_accOpenRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccOpenRequestNumber(String)}および{@@link #forRow(AccOpenVoucherStatusRow)}を使用してください。 
   */
    public static List findDaosByAccOpenRequestNumber( String p_accOpenRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccOpenRequestNumber( p_accOpenRequestNumber ) );
    }


  /** 
   * p_requestCode, and にて指定の値に一致する{@@link AccOpenVoucherStatusRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * 
   * @@return 引数指定のp_requestCode, and の値と一致する{@@link AccOpenVoucherStatusRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCode( String p_requestCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "request_code=?",
            null,
            new Object[] { p_requestCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCode(String)}および{@@link #forRow(AccOpenVoucherStatusRow)}を使用してください。 
   */
    public static List findDaosByRequestCode( String p_requestCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCode( p_requestCode ) );
    }


  /** 
   * p_sendTimestamp, and にて指定の値に一致する{@@link AccOpenVoucherStatusRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_sendTimestamp 検索対象であるp_sendTimestampフィールドの値
   * 
   * @@return 引数指定のp_sendTimestamp, and の値と一致する{@@link AccOpenVoucherStatusRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsBySendTimestamp( java.sql.Timestamp p_sendTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenVoucherStatusRow.TYPE,
            "send_timestamp=?",
            null,
            new Object[] { p_sendTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsBySendTimestamp(java.sql.Timestamp)}および{@@link #forRow(AccOpenVoucherStatusRow)}を使用してください。 
   */
    public static List findDaosBySendTimestamp( java.sql.Timestamp p_sendTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsBySendTimestamp( p_sendTimestamp ) );
    }

}
@
