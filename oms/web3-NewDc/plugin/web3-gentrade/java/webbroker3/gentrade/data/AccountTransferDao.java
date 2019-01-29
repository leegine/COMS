head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountTransferDao.java;


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
 * {@@link AccountTransferDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccountTransferRow}インスタンスへ関連付けることができます。 
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
 * @@see AccountTransferPK 
 * @@see AccountTransferRow 
 */
public class AccountTransferDao extends DataAccessObject {


  /** 
   * この{@@link AccountTransferDao}に関連する型指定のRowオブジェクト 
   */
    private AccountTransferRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccountTransferRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccountTransferDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccountTransferDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccountTransferRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccountTransferRow )
                return new AccountTransferDao( (AccountTransferRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccountTransferRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccountTransferRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccountTransferRow}オブジェクト 
    */
    protected AccountTransferDao( AccountTransferRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccountTransferRow}オブジェクトを取得します。
   */
    public AccountTransferRow getAccountTransferRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccountTransferRow}オブジェクトから{@@link AccountTransferDao}オブジェクトを作成します。 
   * これは実際の{@@link AccountTransferRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccountTransferDao}取得のために指定の{@@link AccountTransferRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccountTransferDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccountTransferDao forRow( AccountTransferRow row ) throws java.lang.IllegalArgumentException {
        return (AccountTransferDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccountTransferRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccountTransferRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccountTransferPK}やデータベースレコードとして挿入される{@@link AccountTransferParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccountTransferRow.TYPE );
    }


  /** 
   * {@@link AccountTransferRow}を一意に特定する{@@link AccountTransferPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccountTransferRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccountTransferParams}オブジェクトの主キーとして利用可能な{@@link AccountTransferPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccountTransferPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccountTransferRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_workDay 検索対象であるp_workDayフィールドの値
   * @@param p_branchCodeOld 検索対象であるp_branchCodeOldフィールドの値
   * @@param p_accountCodeOld 検索対象であるp_accountCodeOldフィールドの値
   * @@param p_transferTbl 検索対象であるp_transferTblフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccountTransferRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccountTransferRow findRowByPk( String p_institutionCode, java.sql.Timestamp p_workDay, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountTransferPK pk = new AccountTransferPK( p_institutionCode, p_workDay, p_branchCodeOld, p_accountCodeOld, p_transferTbl );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccountTransferPKオブジェクトから{@@link AccountTransferRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccountTransferPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccountTransferRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccountTransferRow findRowByPk( AccountTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccountTransferRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,java.sql.Timestamp,String,String,String)}および{@@link #forRow(AccountTransferRow)}を使用してください。 
   */
    public static AccountTransferDao findDaoByPk( String p_institutionCode, java.sql.Timestamp p_workDay, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountTransferPK pk = new AccountTransferPK( p_institutionCode, p_workDay, p_branchCodeOld, p_accountCodeOld, p_transferTbl );
        AccountTransferRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccountTransferPK)}および{@@link #forRow(AccountTransferRow)}を使用してください。 
   */
    public static AccountTransferDao findDaoByPk( AccountTransferPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccountTransferRow row = findRowByPk( pk );
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
   * p_workDay, p_institutionCode, p_branchCodeOld, p_accountCodeOld, p_transferTbl, and にて指定の値から一意の{@@link AccountTransferRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_workDay 検索対象であるp_workDayフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCodeOld 検索対象であるp_branchCodeOldフィールドの値
   * @@param p_accountCodeOld 検索対象であるp_accountCodeOldフィールドの値
   * @@param p_transferTbl 検索対象であるp_transferTblフィールドの値
   * 
   * @@return 引数指定のp_workDay, p_institutionCode, p_branchCodeOld, p_accountCodeOld, p_transferTbl, and の値と一致する{@@link AccountTransferRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccountTransferRow findRowByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl( java.sql.Timestamp p_workDay, String p_institutionCode, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccountTransferRow.TYPE,
            "work_day=? and institution_code=? and branch_code_old=? and account_code_old=? and transfer_tbl=?",
            null,
            new Object[] { p_workDay, p_institutionCode, p_branchCodeOld, p_accountCodeOld, p_transferTbl } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccountTransferRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccountTransferDao.findRowsByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl(java.sql.Timestamp, String, String, String, String)}および{@@link #forRow(AccountTransferRow)}を使用してください。 
   */
    public static AccountTransferDao findDaoByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl( java.sql.Timestamp p_workDay, String p_institutionCode, String p_branchCodeOld, String p_accountCodeOld, String p_transferTbl ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByWorkDayInstitutionCodeBranchCodeOldAccountCodeOldTransferTbl( p_workDay, p_institutionCode, p_branchCodeOld, p_accountCodeOld, p_transferTbl ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_proDiv, and にて指定の値に一致する{@@link AccountTransferRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_proDiv 検索対象であるp_proDivフィールドの値
   * 
   * @@return 引数指定のp_proDiv, and の値と一致する{@@link AccountTransferRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProDiv( String p_proDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            AccountTransferRow.TYPE,
            "pro_div=?",
            null,
            new Object[] { p_proDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProDiv(String)}および{@@link #forRow(AccountTransferRow)}を使用してください。 
   */
    public static List findDaosByProDiv( String p_proDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProDiv( p_proDiv ) );
    }

}
@
