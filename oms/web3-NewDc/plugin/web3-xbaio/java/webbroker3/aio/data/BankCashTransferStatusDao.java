head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.45.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankCashTransferStatusDao.java;


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
 * {@@link BankCashTransferStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BankCashTransferStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see BankCashTransferStatusPK 
 * @@see BankCashTransferStatusRow 
 */
public class BankCashTransferStatusDao extends DataAccessObject {


  /** 
   * この{@@link BankCashTransferStatusDao}に関連する型指定のRowオブジェクト 
   */
    private BankCashTransferStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BankCashTransferStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link BankCashTransferStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BankCashTransferStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BankCashTransferStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankCashTransferStatusRow )
                return new BankCashTransferStatusDao( (BankCashTransferStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankCashTransferStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankCashTransferStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BankCashTransferStatusRow}オブジェクト 
    */
    protected BankCashTransferStatusDao( BankCashTransferStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BankCashTransferStatusRow}オブジェクトを取得します。
   */
    public BankCashTransferStatusRow getBankCashTransferStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link BankCashTransferStatusRow}オブジェクトから{@@link BankCashTransferStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link BankCashTransferStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BankCashTransferStatusDao}取得のために指定の{@@link BankCashTransferStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BankCashTransferStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BankCashTransferStatusDao forRow( BankCashTransferStatusRow row ) throws java.lang.IllegalArgumentException {
        return (BankCashTransferStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankCashTransferStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BankCashTransferStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BankCashTransferStatusPK}やデータベースレコードとして挿入される{@@link BankCashTransferStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankCashTransferStatusRow.TYPE );
    }


  /** 
   * {@@link BankCashTransferStatusRow}を一意に特定する{@@link BankCashTransferStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BankCashTransferStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BankCashTransferStatusParams}オブジェクトの主キーとして利用可能な{@@link BankCashTransferStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BankCashTransferStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BankCashTransferStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankCashTransferStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankCashTransferStatusRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        BankCashTransferStatusPK pk = new BankCashTransferStatusPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBankCashTransferStatusPKオブジェクトから{@@link BankCashTransferStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBankCashTransferStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankCashTransferStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankCashTransferStatusRow findRowByPk( BankCashTransferStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankCashTransferStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public static BankCashTransferStatusDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        BankCashTransferStatusPK pk = new BankCashTransferStatusPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        BankCashTransferStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BankCashTransferStatusPK)}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public static BankCashTransferStatusDao findDaoByPk( BankCashTransferStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankCashTransferStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link BankCashTransferStatusDao}に紐付く{@@link BankCashTransferStatusRow}内で外部キーの関係をもつ{@@link CooperateBankMasterRow}を検索します。 
   * 
   * @@return {@@link BankCashTransferStatusDao}と外部キーの関係にある{@@link CooperateBankMasterRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public CooperateBankMasterRow fetchCooperateBankMasterRowViaPaySchemeId() throws DataNetworkException, DataFindException, DataQueryException  {
        CooperateBankMasterPK pk = new CooperateBankMasterPK( row.getPaySchemeId() );
        Row row = CooperateBankMasterDao.findRowByPk( pk );
        if ( row != null && !(row instanceof CooperateBankMasterRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (CooperateBankMasterRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchCooperateBankMasterRowViaPaySchemeId()}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public CooperateBankMasterDao fetchCooperateBankMasterDaoViaPaySchemeId() throws DataNetworkException, DataFindException, DataQueryException  {
        CooperateBankMasterPK pk = new CooperateBankMasterPK( row.getPaySchemeId() );
        DataAccessObject dao = CooperateBankMasterDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof CooperateBankMasterDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (CooperateBankMasterDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for CooperateBankMaster
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByPaySchemeId(CooperateBankMasterRow)}を使用してください。 
   */
    public static List findRowsByPaySchemeId( CooperateBankMasterDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByPaySchemeId( dao.getCooperateBankMasterRow() );
    }


  /** 
   * {@@link CooperateBankMasterRow}と外部キーの関係にある{@@link BankCashTransferStatusRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link CooperateBankMasterRow}オブジェクト 
   * @@return 指定の{@@link CooperateBankMasterRow}に外部キーを持つ{@@link BankCashTransferStatusRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByPaySchemeId( CooperateBankMasterRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByPaySchemeId( row.getPaySchemeId() );
    }


  /** 
   * {@@link CooperateBankMasterPK}と外部キーの関係にある{@@link BankCashTransferStatusRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link CooperateBankMasterPK}オブジェクト 
   * @@return {@@link CooperateBankMasterPK}と外部キーが一致する値を持つ{@@link BankCashTransferStatusRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByPaySchemeId( CooperateBankMasterPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByPaySchemeId( pk.pay_scheme_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BankCashTransferStatusRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_paySchemeId 検索対象であるp_paySchemeIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BankCashTransferStatusRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByPaySchemeId( String p_paySchemeId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BankCashTransferStatusRow.TYPE,
            "pay_scheme_id=?",
            null,
            new Object[] { p_paySchemeId } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for CooperateBankMaster
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByPaySchemeId(CooperateBankMasterRow)}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByPaySchemeId(CooperateBankMasterRow)}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByPaySchemeId(CooperateBankMasterPK)}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public static List findDaosByPaySchemeId( CooperateBankMasterPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( pk.pay_scheme_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByPaySchemeId(String)}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public static List findDaosByPaySchemeId( String p_paySchemeId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByPaySchemeId( p_paySchemeId ) );
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
   * p_institutionCode, p_branchCode, p_orderRequestNumber, and にて指定の値から一意の{@@link BankCashTransferStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_orderRequestNumber, and の値と一致する{@@link BankCashTransferStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BankCashTransferStatusRow findRowByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BankCashTransferStatusRow.TYPE,
            "institution_code=? and branch_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BankCashTransferStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BankCashTransferStatusDao.findRowsByInstitutionCodeBranchCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeOrderRequestNumber(String, String, String)}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public static BankCashTransferStatusDao findDaoByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_paySchemeId, p_baseDate, and にて指定の値に一致する{@@link BankCashTransferStatusRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_paySchemeId 検索対象であるp_paySchemeIdフィールドの値
   * @@param p_baseDate 検索対象であるp_baseDateフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_paySchemeId, p_baseDate, and の値と一致する{@@link BankCashTransferStatusRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodePaySchemeIdBaseDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_paySchemeId, java.sql.Timestamp p_baseDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BankCashTransferStatusRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and pay_scheme_id=? and base_date=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_paySchemeId, p_baseDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCodePaySchemeIdBaseDate(String, String, String, String, java.sql.Timestamp)}および{@@link #forRow(BankCashTransferStatusRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodePaySchemeIdBaseDate( String p_institutionCode, String p_branchCode, String p_accountCode, String p_paySchemeId, java.sql.Timestamp p_baseDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodePaySchemeIdBaseDate( p_institutionCode, p_branchCode, p_accountCode, p_paySchemeId, p_baseDate ) );
    }

}
@
