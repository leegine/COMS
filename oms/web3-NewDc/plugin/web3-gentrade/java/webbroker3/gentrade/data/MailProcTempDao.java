head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MailProcTempDao.java;


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
 * {@@link MailProcTempDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MailProcTempRow}インスタンスへ関連付けることができます。 
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
 * @@see MailProcTempPK 
 * @@see MailProcTempRow 
 */
public class MailProcTempDao extends DataAccessObject {


  /** 
   * この{@@link MailProcTempDao}に関連する型指定のRowオブジェクト 
   */
    private MailProcTempRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MailProcTempRow}と仮定される{@@link DataAccessObject}から新たに{@@link MailProcTempDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MailProcTempDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MailProcTempRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MailProcTempRow )
                return new MailProcTempDao( (MailProcTempRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MailProcTempRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MailProcTempRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MailProcTempRow}オブジェクト 
    */
    protected MailProcTempDao( MailProcTempRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MailProcTempRow}オブジェクトを取得します。
   */
    public MailProcTempRow getMailProcTempRow() {
        return row;
    }


  /** 
   * 指定の{@@link MailProcTempRow}オブジェクトから{@@link MailProcTempDao}オブジェクトを作成します。 
   * これは実際の{@@link MailProcTempRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MailProcTempDao}取得のために指定の{@@link MailProcTempRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MailProcTempDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MailProcTempDao forRow( MailProcTempRow row ) throws java.lang.IllegalArgumentException {
        return (MailProcTempDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MailProcTempRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MailProcTempRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MailProcTempPK}やデータベースレコードとして挿入される{@@link MailProcTempParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MailProcTempRow.TYPE );
    }


  /** 
   * {@@link MailProcTempRow}を一意に特定する{@@link MailProcTempPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MailProcTempRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MailProcTempParams}オブジェクトの主キーとして利用可能な{@@link MailProcTempPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MailProcTempPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MailProcTempRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_discernmentId 検索対象であるp_discernmentIdフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_mailId 検索対象であるp_mailIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailProcTempRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailProcTempRow findRowByPk( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailProcTempPK pk = new MailProcTempPK( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMailProcTempPKオブジェクトから{@@link MailProcTempRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMailProcTempPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailProcTempRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailProcTempRow findRowByPk( MailProcTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MailProcTempRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String,long)}および{@@link #forRow(MailProcTempRow)}を使用してください。 
   */
    public static MailProcTempDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailProcTempPK pk = new MailProcTempPK( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId );
        MailProcTempRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MailProcTempPK)}および{@@link #forRow(MailProcTempRow)}を使用してください。 
   */
    public static MailProcTempDao findDaoByPk( MailProcTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MailProcTempRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, and にて指定の値から一意の{@@link MailProcTempRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_discernmentId 検索対象であるp_discernmentIdフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_mailId 検索対象であるp_mailIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, and の値と一致する{@@link MailProcTempRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MailProcTempRow findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MailProcTempRow.TYPE,
            "institution_code=? and branch_code=? and sendmail_div=? and discernment_id=? and account_code=? and mail_id=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, new Long(p_mailId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MailProcTempRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MailProcTempDao.findRowsByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(String, String, String, String, String, long)}および{@@link #forRow(MailProcTempRow)}を使用してください。 
   */
    public static MailProcTempDao findDaoByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_sendmailDiv, p_orderId, and にて指定の値に一致する{@@link MailProcTempRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_orderId 検索対象であるp_orderIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_sendmailDiv, p_orderId, and の値と一致する{@@link MailProcTempRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeSendmailDivOrderId( String p_institutionCode, String p_sendmailDiv, Long p_orderId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MailProcTempRow.TYPE,
            "institution_code=? and sendmail_div=? and order_id=?",
            null,
            new Object[] { p_institutionCode, p_sendmailDiv, p_orderId } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeSendmailDivOrderId(String, String, Long)}および{@@link #forRow(MailProcTempRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeSendmailDivOrderId( String p_institutionCode, String p_sendmailDiv, Long p_orderId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeSendmailDivOrderId( p_institutionCode, p_sendmailDiv, p_orderId ) );
    }

}
@
