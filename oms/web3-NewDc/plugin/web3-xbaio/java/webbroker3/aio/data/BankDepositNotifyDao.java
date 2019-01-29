head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.42.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankDepositNotifyDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link BankDepositNotifyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BankDepositNotifyRow}インスタンスへ関連付けることができます。 
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
 * @@see BankDepositNotifyPK 
 * @@see BankDepositNotifyRow 
 */
public class BankDepositNotifyDao extends DataAccessObject {


  /** 
   * この{@@link BankDepositNotifyDao}に関連する型指定のRowオブジェクト 
   */
    private BankDepositNotifyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BankDepositNotifyRow}と仮定される{@@link DataAccessObject}から新たに{@@link BankDepositNotifyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BankDepositNotifyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BankDepositNotifyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankDepositNotifyRow )
                return new BankDepositNotifyDao( (BankDepositNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankDepositNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankDepositNotifyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BankDepositNotifyRow}オブジェクト 
    */
    protected BankDepositNotifyDao( BankDepositNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BankDepositNotifyRow}オブジェクトを取得します。
   */
    public BankDepositNotifyRow getBankDepositNotifyRow() {
        return row;
    }


  /** 
   * 指定の{@@link BankDepositNotifyRow}オブジェクトから{@@link BankDepositNotifyDao}オブジェクトを作成します。 
   * これは実際の{@@link BankDepositNotifyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BankDepositNotifyDao}取得のために指定の{@@link BankDepositNotifyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BankDepositNotifyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BankDepositNotifyDao forRow( BankDepositNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (BankDepositNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankDepositNotifyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BankDepositNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BankDepositNotifyPK}やデータベースレコードとして挿入される{@@link BankDepositNotifyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankDepositNotifyRow.TYPE );
    }


  /** 
   * {@@link BankDepositNotifyRow}を一意に特定する{@@link BankDepositNotifyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BankDepositNotifyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BankDepositNotifyParams}オブジェクトの主キーとして利用可能な{@@link BankDepositNotifyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BankDepositNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BankDepositNotifyRow}オブジェクトを検索します。 
   * 
   * @@param p_bankDepositNotifyId 検索対象であるp_bankDepositNotifyIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_dataLoadDiv 検索対象であるp_dataLoadDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankDepositNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankDepositNotifyRow findRowByPk( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositNotifyPK pk = new BankDepositNotifyPK( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBankDepositNotifyPKオブジェクトから{@@link BankDepositNotifyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBankDepositNotifyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankDepositNotifyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankDepositNotifyRow findRowByPk( BankDepositNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankDepositNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,String)}および{@@link #forRow(BankDepositNotifyRow)}を使用してください。 
   */
    public static BankDepositNotifyDao findDaoByPk( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositNotifyPK pk = new BankDepositNotifyPK( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv );
        BankDepositNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BankDepositNotifyPK)}および{@@link #forRow(BankDepositNotifyRow)}を使用してください。 
   */
    public static BankDepositNotifyDao findDaoByPk( BankDepositNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankDepositNotifyRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link BankDepositNotifyDao}に関連する{@@link BankDepositNotifyRow}の外部キーがある{@@link BankDepositErrorHistoryRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link BankDepositNotifyDao}に関連する{@@link BankDepositNotifyRow}の外部キーがある{@@link BankDepositErrorHistoryRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchBankDepositErrorHistoryRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataQueryException  {
        return BankDepositErrorHistoryDao.findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBankDepositErrorHistoryRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv()}および{@@link #forRow(BankDepositNotifyRow)}を使用してください。 
   */
    public List fetchBankDepositErrorHistoryDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataQueryException  {
        return BankDepositErrorHistoryDao.findDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBankDepositErrorHistoryRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv()}および{@@link #forRow(BankDepositNotifyRow)}を使用してください。 
   */
    public List fetchBankDepositErrorHistoryDaosBankDepositNotifyIdInstitutionCodeDataLoadDiv() throws DataNetworkException, DataQueryException  {
        return fetchBankDepositErrorHistoryDaosByBankDepositNotifyIdInstitutionCodeDataLoadDiv();
    }


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, and にて指定の値から一意の{@@link BankDepositNotifyRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_bankDepositNotifyId 検索対象であるp_bankDepositNotifyIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_dataLoadDiv 検索対象であるp_dataLoadDivフィールドの値
   * 
   * @@return 引数指定のp_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv, and の値と一致する{@@link BankDepositNotifyRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BankDepositNotifyRow findRowByBankDepositNotifyIdInstitutionCodeDataLoadDiv( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankDepositNotifyRow.TYPE,
            "bank_deposit_notify_id=? and institution_code=? and data_load_div=?",
            null,
            new Object[] { new Long(p_bankDepositNotifyId), p_institutionCode, p_dataLoadDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankDepositNotifyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankDepositNotifyDao.findRowsByBankDepositNotifyIdInstitutionCodeDataLoadDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBankDepositNotifyIdInstitutionCodeDataLoadDiv(long, String, String)}および{@@link #forRow(BankDepositNotifyRow)}を使用してください。 
   */
    public static BankDepositNotifyDao findDaoByBankDepositNotifyIdInstitutionCodeDataLoadDiv( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBankDepositNotifyIdInstitutionCodeDataLoadDiv( p_bankDepositNotifyId, p_institutionCode, p_dataLoadDiv ) );
    }


  /** 
   * p_institutionCode, p_bankCode, p_bankBranchCode, p_bankAccountNo, p_depositDataReferenceNo, p_depositDataAccountDate, p_dataLoadDiv, and にて指定の値から一意の{@@link BankDepositNotifyRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_bankCode 検索対象であるp_bankCodeフィールドの値
   * @@param p_bankBranchCode 検索対象であるp_bankBranchCodeフィールドの値
   * @@param p_bankAccountNo 検索対象であるp_bankAccountNoフィールドの値
   * @@param p_depositDataReferenceNo 検索対象であるp_depositDataReferenceNoフィールドの値
   * @@param p_depositDataAccountDate 検索対象であるp_depositDataAccountDateフィールドの値
   * @@param p_dataLoadDiv 検索対象であるp_dataLoadDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_bankCode, p_bankBranchCode, p_bankAccountNo, p_depositDataReferenceNo, p_depositDataAccountDate, p_dataLoadDiv, and の値と一致する{@@link BankDepositNotifyRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BankDepositNotifyRow findRowByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv( String p_institutionCode, String p_bankCode, String p_bankBranchCode, String p_bankAccountNo, String p_depositDataReferenceNo, String p_depositDataAccountDate, String p_dataLoadDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankDepositNotifyRow.TYPE,
            "institution_code=? and bank_code=? and bank_branch_code=? and bank_account_no=? and deposit_data_reference_no=? and deposit_data_account_date=? and data_load_div=?",
            null,
            new Object[] { p_institutionCode, p_bankCode, p_bankBranchCode, p_bankAccountNo, p_depositDataReferenceNo, p_depositDataAccountDate, p_dataLoadDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankDepositNotifyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankDepositNotifyDao.findRowsByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv(String, String, String, String, String, String, String)}および{@@link #forRow(BankDepositNotifyRow)}を使用してください。 
   */
    public static BankDepositNotifyDao findDaoByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv( String p_institutionCode, String p_bankCode, String p_bankBranchCode, String p_bankAccountNo, String p_depositDataReferenceNo, String p_depositDataAccountDate, String p_dataLoadDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBankCodeBankBranchCodeBankAccountNoDepositDataReferenceNoDepositDataAccountDateDataLoadDiv( p_institutionCode, p_bankCode, p_bankBranchCode, p_bankAccountNo, p_depositDataReferenceNo, p_depositDataAccountDate, p_dataLoadDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
