head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitMngDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpower.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link PaymentRequisitMngDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PaymentRequisitMngRow}インスタンスへ関連付けることができます。 
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
 * @@see PaymentRequisitMngPK 
 * @@see PaymentRequisitMngRow 
 */
public class PaymentRequisitMngDao extends DataAccessObject {


  /** 
   * この{@@link PaymentRequisitMngDao}に関連する型指定のRowオブジェクト 
   */
    private PaymentRequisitMngRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PaymentRequisitMngRow}と仮定される{@@link DataAccessObject}から新たに{@@link PaymentRequisitMngDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PaymentRequisitMngDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PaymentRequisitMngRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PaymentRequisitMngRow )
                return new PaymentRequisitMngDao( (PaymentRequisitMngRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PaymentRequisitMngRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PaymentRequisitMngRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PaymentRequisitMngRow}オブジェクト 
    */
    protected PaymentRequisitMngDao( PaymentRequisitMngRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PaymentRequisitMngRow}オブジェクトを取得します。
   */
    public PaymentRequisitMngRow getPaymentRequisitMngRow() {
        return row;
    }


  /** 
   * 指定の{@@link PaymentRequisitMngRow}オブジェクトから{@@link PaymentRequisitMngDao}オブジェクトを作成します。 
   * これは実際の{@@link PaymentRequisitMngRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PaymentRequisitMngDao}取得のために指定の{@@link PaymentRequisitMngRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PaymentRequisitMngDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PaymentRequisitMngDao forRow( PaymentRequisitMngRow row ) throws java.lang.IllegalArgumentException {
        return (PaymentRequisitMngDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PaymentRequisitMngRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PaymentRequisitMngRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PaymentRequisitMngPK}やデータベースレコードとして挿入される{@@link PaymentRequisitMngParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PaymentRequisitMngRow.TYPE );
    }


  /** 
   * {@@link PaymentRequisitMngRow}を一意に特定する{@@link PaymentRequisitMngPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PaymentRequisitMngRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PaymentRequisitMngParams}オブジェクトの主キーとして利用可能な{@@link PaymentRequisitMngPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PaymentRequisitMngPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new PaymentRequisitMngPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PaymentRequisitMngRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PaymentRequisitMngRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PaymentRequisitMngRow findRowByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitMngPK pk = new PaymentRequisitMngPK( p_accountId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPaymentRequisitMngPKオブジェクトから{@@link PaymentRequisitMngRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPaymentRequisitMngPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PaymentRequisitMngRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PaymentRequisitMngRow findRowByPk( PaymentRequisitMngPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PaymentRequisitMngRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(PaymentRequisitMngRow)}を使用してください。 
   */
    public static PaymentRequisitMngDao findDaoByPk( long p_accountId ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitMngPK pk = new PaymentRequisitMngPK( p_accountId );
        PaymentRequisitMngRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PaymentRequisitMngPK)}および{@@link #forRow(PaymentRequisitMngRow)}を使用してください。 
   */
    public static PaymentRequisitMngDao findDaoByPk( PaymentRequisitMngPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitMngRow row = findRowByPk( pk );
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
   * p_accountId, and にて指定の値から一意の{@@link PaymentRequisitMngRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, and の値と一致する{@@link PaymentRequisitMngRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PaymentRequisitMngRow findRowByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PaymentRequisitMngRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PaymentRequisitMngRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PaymentRequisitMngDao.findRowsByAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountId(long)}および{@@link #forRow(PaymentRequisitMngRow)}を使用してください。 
   */
    public static PaymentRequisitMngDao findDaoByAccountId( long p_accountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountId( p_accountId ) );
    }


  /** 
   * p_calcDate, p_institutionCode, p_branchCode, p_accountCode, and にて指定の値から一意の{@@link PaymentRequisitMngRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_calcDate 検索対象であるp_calcDateフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_calcDate, p_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link PaymentRequisitMngRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PaymentRequisitMngRow findRowByCalcDateInstitutionCodeBranchCodeAccountCode( java.sql.Timestamp p_calcDate, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PaymentRequisitMngRow.TYPE,
            "calc_date=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_calcDate, p_institutionCode, p_branchCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PaymentRequisitMngRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PaymentRequisitMngDao.findRowsByCalcDateInstitutionCodeBranchCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByCalcDateInstitutionCodeBranchCodeAccountCode(java.sql.Timestamp, String, String, String)}および{@@link #forRow(PaymentRequisitMngRow)}を使用してください。 
   */
    public static PaymentRequisitMngDao findDaoByCalcDateInstitutionCodeBranchCodeAccountCode( java.sql.Timestamp p_calcDate, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCalcDateInstitutionCodeBranchCodeAccountCode( p_calcDate, p_institutionCode, p_branchCode, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
