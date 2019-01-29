head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MailInfoDao.java;


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
 * {@@link MailInfoDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MailInfoRow}インスタンスへ関連付けることができます。 
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
 * @@see MailInfoPK 
 * @@see MailInfoRow 
 */
public class MailInfoDao extends DataAccessObject {


  /** 
   * この{@@link MailInfoDao}に関連する型指定のRowオブジェクト 
   */
    private MailInfoRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MailInfoRow}と仮定される{@@link DataAccessObject}から新たに{@@link MailInfoDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MailInfoDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MailInfoRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MailInfoRow )
                return new MailInfoDao( (MailInfoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MailInfoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MailInfoRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MailInfoRow}オブジェクト 
    */
    protected MailInfoDao( MailInfoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MailInfoRow}オブジェクトを取得します。
   */
    public MailInfoRow getMailInfoRow() {
        return row;
    }


  /** 
   * 指定の{@@link MailInfoRow}オブジェクトから{@@link MailInfoDao}オブジェクトを作成します。 
   * これは実際の{@@link MailInfoRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MailInfoDao}取得のために指定の{@@link MailInfoRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MailInfoDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MailInfoDao forRow( MailInfoRow row ) throws java.lang.IllegalArgumentException {
        return (MailInfoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MailInfoRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MailInfoRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MailInfoPK}やデータベースレコードとして挿入される{@@link MailInfoParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MailInfoRow.TYPE );
    }


  /** 
   * {@@link MailInfoRow}を一意に特定する{@@link MailInfoPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MailInfoRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MailInfoParams}オブジェクトの主キーとして利用可能な{@@link MailInfoPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MailInfoPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MailInfoRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_discernmentId 検索対象であるp_discernmentIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailInfoRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailInfoRow findRowByPk( String p_institutionCode, String p_sendmailDiv, String p_discernmentId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailInfoPK pk = new MailInfoPK( p_institutionCode, p_sendmailDiv, p_discernmentId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMailInfoPKオブジェクトから{@@link MailInfoRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMailInfoPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailInfoRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailInfoRow findRowByPk( MailInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MailInfoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(MailInfoRow)}を使用してください。 
   */
    public static MailInfoDao findDaoByPk( String p_institutionCode, String p_sendmailDiv, String p_discernmentId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailInfoPK pk = new MailInfoPK( p_institutionCode, p_sendmailDiv, p_discernmentId );
        MailInfoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MailInfoPK)}および{@@link #forRow(MailInfoRow)}を使用してください。 
   */
    public static MailInfoDao findDaoByPk( MailInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MailInfoRow row = findRowByPk( pk );
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
   * p_institutionCode, p_sendmailDiv, p_discernmentId, and にて指定の値から一意の{@@link MailInfoRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_discernmentId 検索対象であるp_discernmentIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_sendmailDiv, p_discernmentId, and の値と一致する{@@link MailInfoRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MailInfoRow findRowByInstitutionCodeSendmailDivDiscernmentId( String p_institutionCode, String p_sendmailDiv, String p_discernmentId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MailInfoRow.TYPE,
            "institution_code=? and sendmail_div=? and discernment_id=?",
            null,
            new Object[] { p_institutionCode, p_sendmailDiv, p_discernmentId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MailInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MailInfoDao.findRowsByInstitutionCodeSendmailDivDiscernmentId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeSendmailDivDiscernmentId(String, String, String)}および{@@link #forRow(MailInfoRow)}を使用してください。 
   */
    public static MailInfoDao findDaoByInstitutionCodeSendmailDivDiscernmentId( String p_institutionCode, String p_sendmailDiv, String p_discernmentId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSendmailDivDiscernmentId( p_institutionCode, p_sendmailDiv, p_discernmentId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
