head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ExclusiveUseAccountDao.java;


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
 * {@@link ExclusiveUseAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ExclusiveUseAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see ExclusiveUseAccountPK 
 * @@see ExclusiveUseAccountRow 
 */
public class ExclusiveUseAccountDao extends DataAccessObject {


  /** 
   * この{@@link ExclusiveUseAccountDao}に関連する型指定のRowオブジェクト 
   */
    private ExclusiveUseAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ExclusiveUseAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link ExclusiveUseAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ExclusiveUseAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ExclusiveUseAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExclusiveUseAccountRow )
                return new ExclusiveUseAccountDao( (ExclusiveUseAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExclusiveUseAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExclusiveUseAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ExclusiveUseAccountRow}オブジェクト 
    */
    protected ExclusiveUseAccountDao( ExclusiveUseAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ExclusiveUseAccountRow}オブジェクトを取得します。
   */
    public ExclusiveUseAccountRow getExclusiveUseAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link ExclusiveUseAccountRow}オブジェクトから{@@link ExclusiveUseAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link ExclusiveUseAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ExclusiveUseAccountDao}取得のために指定の{@@link ExclusiveUseAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ExclusiveUseAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ExclusiveUseAccountDao forRow( ExclusiveUseAccountRow row ) throws java.lang.IllegalArgumentException {
        return (ExclusiveUseAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExclusiveUseAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ExclusiveUseAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ExclusiveUseAccountPK}やデータベースレコードとして挿入される{@@link ExclusiveUseAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExclusiveUseAccountRow.TYPE );
    }


  /** 
   * {@@link ExclusiveUseAccountRow}を一意に特定する{@@link ExclusiveUseAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ExclusiveUseAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ExclusiveUseAccountParams}オブジェクトの主キーとして利用可能な{@@link ExclusiveUseAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ExclusiveUseAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new ExclusiveUseAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ExclusiveUseAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExclusiveUseAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExclusiveUseAccountRow findRowByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountPK pk = new ExclusiveUseAccountPK( p_accountId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のExclusiveUseAccountPKオブジェクトから{@@link ExclusiveUseAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するExclusiveUseAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExclusiveUseAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExclusiveUseAccountRow findRowByPk( ExclusiveUseAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExclusiveUseAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(ExclusiveUseAccountRow)}を使用してください。 
   */
    public static ExclusiveUseAccountDao findDaoByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountPK pk = new ExclusiveUseAccountPK( p_accountId );
        ExclusiveUseAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ExclusiveUseAccountPK)}および{@@link #forRow(ExclusiveUseAccountRow)}を使用してください。 
   */
    public static ExclusiveUseAccountDao findDaoByPk( ExclusiveUseAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExclusiveUseAccountRow row = findRowByPk( pk );
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
   * p_accountId, and にて指定の値から一意の{@@link ExclusiveUseAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, and の値と一致する{@@link ExclusiveUseAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ExclusiveUseAccountRow findRowByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExclusiveUseAccountRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExclusiveUseAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExclusiveUseAccountDao.findRowsByAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountId(long)}および{@@link #forRow(ExclusiveUseAccountRow)}を使用してください。 
   */
    public static ExclusiveUseAccountDao findDaoByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountId( p_accountId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link ExclusiveUseAccountRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link ExclusiveUseAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExclusiveUseAccountRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(ExclusiveUseAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }


  /** 
   * p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo, and にて指定の値に一致する{@@link ExclusiveUseAccountRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * @@param p_finBranchCode 検索対象であるp_finBranchCodeフィールドの値
   * @@param p_finAccountNo 検索対象であるp_finAccountNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo, and の値と一致する{@@link ExclusiveUseAccountRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExclusiveUseAccountRow.TYPE,
            "institution_code=? and fin_institution_code=? and fin_branch_code=? and fin_account_no=?",
            null,
            new Object[] { p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo(String, String, String, String)}および{@@link #forRow(ExclusiveUseAccountRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeFinInstitutionCodeFinBranchCodeFinAccountNo( p_institutionCode, p_finInstitutionCode, p_finBranchCode, p_finAccountNo ) );
    }

}
@
