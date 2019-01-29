head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MailProcDao.java;


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
 * {@@link MailProcDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MailProcRow}インスタンスへ関連付けることができます。 
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
 * @@see MailProcPK 
 * @@see MailProcRow 
 */
public class MailProcDao extends DataAccessObject {


  /** 
   * この{@@link MailProcDao}に関連する型指定のRowオブジェクト 
   */
    private MailProcRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MailProcRow}と仮定される{@@link DataAccessObject}から新たに{@@link MailProcDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MailProcDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MailProcRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MailProcRow )
                return new MailProcDao( (MailProcRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MailProcRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MailProcRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MailProcRow}オブジェクト 
    */
    protected MailProcDao( MailProcRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MailProcRow}オブジェクトを取得します。
   */
    public MailProcRow getMailProcRow() {
        return row;
    }


  /** 
   * 指定の{@@link MailProcRow}オブジェクトから{@@link MailProcDao}オブジェクトを作成します。 
   * これは実際の{@@link MailProcRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MailProcDao}取得のために指定の{@@link MailProcRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MailProcDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MailProcDao forRow( MailProcRow row ) throws java.lang.IllegalArgumentException {
        return (MailProcDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MailProcRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MailProcRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MailProcPK}やデータベースレコードとして挿入される{@@link MailProcParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MailProcRow.TYPE );
    }


  /** 
   * {@@link MailProcRow}を一意に特定する{@@link MailProcPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MailProcRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MailProcParams}オブジェクトの主キーとして利用可能な{@@link MailProcPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MailProcPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MailProcRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_discernmentId 検索対象であるp_discernmentIdフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_mailId 検索対象であるp_mailIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailProcRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailProcRow findRowByPk( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailProcPK pk = new MailProcPK( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMailProcPKオブジェクトから{@@link MailProcRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMailProcPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MailProcRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MailProcRow findRowByPk( MailProcPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MailProcRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String,long)}および{@@link #forRow(MailProcRow)}を使用してください。 
   */
    public static MailProcDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) throws DataFindException, DataQueryException, DataNetworkException {
        MailProcPK pk = new MailProcPK( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId );
        MailProcRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MailProcPK)}および{@@link #forRow(MailProcRow)}を使用してください。 
   */
    public static MailProcDao findDaoByPk( MailProcPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MailProcRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, and にて指定の値から一意の{@@link MailProcRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_discernmentId 検索対象であるp_discernmentIdフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_mailId 検索対象であるp_mailIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, and の値と一致する{@@link MailProcRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MailProcRow findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MailProcRow.TYPE,
            "institution_code=? and branch_code=? and sendmail_div=? and discernment_id=? and account_code=? and mail_id=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, new Long(p_mailId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MailProcRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MailProcDao.findRowsByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(String, String, String, String, String, long)}および{@@link #forRow(MailProcRow)}を使用してください。 
   */
    public static MailProcDao findDaoByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_status, and にて指定の値に一致する{@@link MailProcRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_status, and の値と一致する{@@link MailProcRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MailProcRow.TYPE,
            "status=?",
            null,
            new Object[] { p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatus(String)}および{@@link #forRow(MailProcRow)}を使用してください。 
   */
    public static List findDaosByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatus( p_status ) );
    }

}
@
