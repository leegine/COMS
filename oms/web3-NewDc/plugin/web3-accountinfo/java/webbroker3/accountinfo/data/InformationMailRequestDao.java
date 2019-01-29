head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	InformationMailRequestDao.java;


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
 * {@@link InformationMailRequestDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link InformationMailRequestRow}インスタンスへ関連付けることができます。 
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
 * @@see InformationMailRequestPK 
 * @@see InformationMailRequestRow 
 */
public class InformationMailRequestDao extends DataAccessObject {


  /** 
   * この{@@link InformationMailRequestDao}に関連する型指定のRowオブジェクト 
   */
    private InformationMailRequestRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link InformationMailRequestRow}と仮定される{@@link DataAccessObject}から新たに{@@link InformationMailRequestDao}を返します。 
         * @@return 指定のRowに結びつく{@@link InformationMailRequestDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link InformationMailRequestRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformationMailRequestRow )
                return new InformationMailRequestDao( (InformationMailRequestRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformationMailRequestRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformationMailRequestRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link InformationMailRequestRow}オブジェクト 
    */
    protected InformationMailRequestDao( InformationMailRequestRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link InformationMailRequestRow}オブジェクトを取得します。
   */
    public InformationMailRequestRow getInformationMailRequestRow() {
        return row;
    }


  /** 
   * 指定の{@@link InformationMailRequestRow}オブジェクトから{@@link InformationMailRequestDao}オブジェクトを作成します。 
   * これは実際の{@@link InformationMailRequestRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link InformationMailRequestDao}取得のために指定の{@@link InformationMailRequestRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link InformationMailRequestDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static InformationMailRequestDao forRow( InformationMailRequestRow row ) throws java.lang.IllegalArgumentException {
        return (InformationMailRequestDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformationMailRequestRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link InformationMailRequestRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link InformationMailRequestPK}やデータベースレコードとして挿入される{@@link InformationMailRequestParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformationMailRequestRow.TYPE );
    }


  /** 
   * {@@link InformationMailRequestRow}を一意に特定する{@@link InformationMailRequestPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link InformationMailRequestRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link InformationMailRequestParams}オブジェクトの主キーとして利用可能な{@@link InformationMailRequestPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static InformationMailRequestPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new InformationMailRequestPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link InformationMailRequestRow}オブジェクトを検索します。 
   * 
   * @@param p_informationMailRequestId 検索対象であるp_informationMailRequestIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformationMailRequestRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformationMailRequestRow findRowByPk( long p_informationMailRequestId ) throws DataFindException, DataQueryException, DataNetworkException {
        InformationMailRequestPK pk = new InformationMailRequestPK( p_informationMailRequestId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のInformationMailRequestPKオブジェクトから{@@link InformationMailRequestRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するInformationMailRequestPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformationMailRequestRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformationMailRequestRow findRowByPk( InformationMailRequestPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformationMailRequestRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(InformationMailRequestRow)}を使用してください。 
   */
    public static InformationMailRequestDao findDaoByPk( long p_informationMailRequestId ) throws DataFindException, DataQueryException, DataNetworkException {
        InformationMailRequestPK pk = new InformationMailRequestPK( p_informationMailRequestId );
        InformationMailRequestRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(InformationMailRequestPK)}および{@@link #forRow(InformationMailRequestRow)}を使用してください。 
   */
    public static InformationMailRequestDao findDaoByPk( InformationMailRequestPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformationMailRequestRow row = findRowByPk( pk );
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
   * p_informationMailRequestId, and にて指定の値から一意の{@@link InformationMailRequestRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_informationMailRequestId 検索対象であるp_informationMailRequestIdフィールドの値
   * 
   * @@return 引数指定のp_informationMailRequestId, and の値と一致する{@@link InformationMailRequestRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static InformationMailRequestRow findRowByInformationMailRequestId( long p_informationMailRequestId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InformationMailRequestRow.TYPE,
            "information_mail_request_id=?",
            null,
            new Object[] { new Long(p_informationMailRequestId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InformationMailRequestRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InformationMailRequestDao.findRowsByInformationMailRequestId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInformationMailRequestId(long)}および{@@link #forRow(InformationMailRequestRow)}を使用してください。 
   */
    public static InformationMailRequestDao findDaoByInformationMailRequestId( long p_informationMailRequestId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInformationMailRequestId( p_informationMailRequestId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_sendmailDev, p_requestTimestamp, and にて指定の値に一致する{@@link InformationMailRequestRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_sendmailDev 検索対象であるp_sendmailDevフィールドの値
   * @@param p_requestTimestamp 検索対象であるp_requestTimestampフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_sendmailDev, p_requestTimestamp, and の値と一致する{@@link InformationMailRequestRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeSendmailDevRequestTimestamp( String p_institutionCode, String p_sendmailDev, java.sql.Timestamp p_requestTimestamp ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            InformationMailRequestRow.TYPE,
            "institution_code=? and sendmail_dev=? and request_timestamp=?",
            null,
            new Object[] { p_institutionCode, p_sendmailDev, p_requestTimestamp } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeSendmailDevRequestTimestamp(String, String, java.sql.Timestamp)}および{@@link #forRow(InformationMailRequestRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeSendmailDevRequestTimestamp( String p_institutionCode, String p_sendmailDev, java.sql.Timestamp p_requestTimestamp ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeSendmailDevRequestTimestamp( p_institutionCode, p_sendmailDev, p_requestTimestamp ) );
    }

}
@
