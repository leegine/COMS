head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ExtMailProcDao.java;


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
 * {@@link ExtMailProcDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ExtMailProcRow}インスタンスへ関連付けることができます。 
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
 * @@see ExtMailProcPK 
 * @@see ExtMailProcRow 
 */
public class ExtMailProcDao extends DataAccessObject {


  /** 
   * この{@@link ExtMailProcDao}に関連する型指定のRowオブジェクト 
   */
    private ExtMailProcRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ExtMailProcRow}と仮定される{@@link DataAccessObject}から新たに{@@link ExtMailProcDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ExtMailProcDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ExtMailProcRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExtMailProcRow )
                return new ExtMailProcDao( (ExtMailProcRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExtMailProcRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExtMailProcRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ExtMailProcRow}オブジェクト 
    */
    protected ExtMailProcDao( ExtMailProcRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ExtMailProcRow}オブジェクトを取得します。
   */
    public ExtMailProcRow getExtMailProcRow() {
        return row;
    }


  /** 
   * 指定の{@@link ExtMailProcRow}オブジェクトから{@@link ExtMailProcDao}オブジェクトを作成します。 
   * これは実際の{@@link ExtMailProcRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ExtMailProcDao}取得のために指定の{@@link ExtMailProcRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ExtMailProcDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ExtMailProcDao forRow( ExtMailProcRow row ) throws java.lang.IllegalArgumentException {
        return (ExtMailProcDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExtMailProcRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ExtMailProcRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ExtMailProcPK}やデータベースレコードとして挿入される{@@link ExtMailProcParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExtMailProcRow.TYPE );
    }


  /** 
   * {@@link ExtMailProcRow}を一意に特定する{@@link ExtMailProcPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ExtMailProcRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ExtMailProcParams}オブジェクトの主キーとして利用可能な{@@link ExtMailProcPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ExtMailProcPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ExtMailProcRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_discernmentId 検索対象であるp_discernmentIdフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_mailId 検索対象であるp_mailIdフィールドの値
   * @@param p_itemName 検索対象であるp_itemNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExtMailProcRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExtMailProcRow findRowByPk( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId, String p_itemName ) throws DataFindException, DataQueryException, DataNetworkException {
        ExtMailProcPK pk = new ExtMailProcPK( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のExtMailProcPKオブジェクトから{@@link ExtMailProcRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するExtMailProcPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExtMailProcRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExtMailProcRow findRowByPk( ExtMailProcPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExtMailProcRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String,long,String)}および{@@link #forRow(ExtMailProcRow)}を使用してください。 
   */
    public static ExtMailProcDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId, String p_itemName ) throws DataFindException, DataQueryException, DataNetworkException {
        ExtMailProcPK pk = new ExtMailProcPK( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName );
        ExtMailProcRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ExtMailProcPK)}および{@@link #forRow(ExtMailProcRow)}を使用してください。 
   */
    public static ExtMailProcDao findDaoByPk( ExtMailProcPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExtMailProcRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName, and にて指定の値から一意の{@@link ExtMailProcRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_sendmailDiv 検索対象であるp_sendmailDivフィールドの値
   * @@param p_discernmentId 検索対象であるp_discernmentIdフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_mailId 検索対象であるp_mailIdフィールドの値
   * @@param p_itemName 検索対象であるp_itemNameフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName, and の値と一致する{@@link ExtMailProcRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ExtMailProcRow findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId, String p_itemName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExtMailProcRow.TYPE,
            "institution_code=? and branch_code=? and sendmail_div=? and discernment_id=? and account_code=? and mail_id=? and item_name=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, new Long(p_mailId), p_itemName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExtMailProcRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExtMailProcDao.findRowsByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName(String, String, String, String, String, long, String)}および{@@link #forRow(ExtMailProcRow)}を使用してください。 
   */
    public static ExtMailProcDao findDaoByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName( String p_institutionCode, String p_branchCode, String p_sendmailDiv, String p_discernmentId, String p_accountCode, long p_mailId, String p_itemName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailIdItemName( p_institutionCode, p_branchCode, p_sendmailDiv, p_discernmentId, p_accountCode, p_mailId, p_itemName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
