head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.18.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenWaitingDao.java;


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
 * {@@link AccOpenWaitingDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenWaitingRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenWaitingPK 
 * @@see AccOpenWaitingRow 
 */
public class AccOpenWaitingDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenWaitingDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenWaitingRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenWaitingRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenWaitingDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenWaitingDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenWaitingRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenWaitingRow )
                return new AccOpenWaitingDao( (AccOpenWaitingRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenWaitingRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenWaitingRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenWaitingRow}オブジェクト 
    */
    protected AccOpenWaitingDao( AccOpenWaitingRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenWaitingRow}オブジェクトを取得します。
   */
    public AccOpenWaitingRow getAccOpenWaitingRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenWaitingRow}オブジェクトから{@@link AccOpenWaitingDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenWaitingRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenWaitingDao}取得のために指定の{@@link AccOpenWaitingRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenWaitingDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenWaitingDao forRow( AccOpenWaitingRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenWaitingDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenWaitingRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenWaitingRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenWaitingPK}やデータベースレコードとして挿入される{@@link AccOpenWaitingParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenWaitingRow.TYPE );
    }


  /** 
   * {@@link AccOpenWaitingRow}を一意に特定する{@@link AccOpenWaitingPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenWaitingRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenWaitingParams}オブジェクトの主キーとして利用可能な{@@link AccOpenWaitingPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenWaitingPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenWaitingRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenWaitingRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenWaitingRow findRowByPk( String p_institutionCode, String p_accOpenRequestNumber, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenWaitingPK pk = new AccOpenWaitingPK( p_institutionCode, p_accOpenRequestNumber, p_serialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenWaitingPKオブジェクトから{@@link AccOpenWaitingRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenWaitingPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenWaitingRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenWaitingRow findRowByPk( AccOpenWaitingPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenWaitingRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(AccOpenWaitingRow)}を使用してください。 
   */
    public static AccOpenWaitingDao findDaoByPk( String p_institutionCode, String p_accOpenRequestNumber, String p_serialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenWaitingPK pk = new AccOpenWaitingPK( p_institutionCode, p_accOpenRequestNumber, p_serialNo );
        AccOpenWaitingRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenWaitingPK)}および{@@link #forRow(AccOpenWaitingRow)}を使用してください。 
   */
    public static AccOpenWaitingDao findDaoByPk( AccOpenWaitingPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenWaitingRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accOpenRequestNumber, p_serialNo, and にて指定の値から一意の{@@link AccOpenWaitingRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * @@param p_serialNo 検索対象であるp_serialNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_accOpenRequestNumber, p_serialNo, and の値と一致する{@@link AccOpenWaitingRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenWaitingRow findRowByInstitutionCodeAccOpenRequestNumberSerialNo( String p_institutionCode, String p_accOpenRequestNumber, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenWaitingRow.TYPE,
            "institution_code=? and acc_open_request_number=? and serial_no=?",
            null,
            new Object[] { p_institutionCode, p_accOpenRequestNumber, p_serialNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenWaitingRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenWaitingDao.findRowsByInstitutionCodeAccOpenRequestNumberSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAccOpenRequestNumberSerialNo(String, String, String)}および{@@link #forRow(AccOpenWaitingRow)}を使用してください。 
   */
    public static AccOpenWaitingDao findDaoByInstitutionCodeAccOpenRequestNumberSerialNo( String p_institutionCode, String p_accOpenRequestNumber, String p_serialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccOpenRequestNumberSerialNo( p_institutionCode, p_accOpenRequestNumber, p_serialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_checkDiv, and にて指定の値に一致する{@@link AccOpenWaitingRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_checkDiv 検索対象であるp_checkDivフィールドの値
   * 
   * @@return 引数指定のp_checkDiv, and の値と一致する{@@link AccOpenWaitingRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCheckDiv( String p_checkDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccOpenWaitingRow.TYPE,
            "check_div=?",
            null,
            new Object[] { p_checkDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCheckDiv(String)}および{@@link #forRow(AccOpenWaitingRow)}を使用してください。 
   */
    public static List findDaosByCheckDiv( String p_checkDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCheckDiv( p_checkDiv ) );
    }

}
@
