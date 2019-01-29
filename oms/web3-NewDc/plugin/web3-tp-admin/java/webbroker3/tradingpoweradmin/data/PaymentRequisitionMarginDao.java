head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.57.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitionMarginDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpoweradmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link PaymentRequisitionMarginDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PaymentRequisitionMarginRow}インスタンスへ関連付けることができます。 
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
 * @@see PaymentRequisitionMarginPK 
 * @@see PaymentRequisitionMarginRow 
 */
public class PaymentRequisitionMarginDao extends DataAccessObject {


  /** 
   * この{@@link PaymentRequisitionMarginDao}に関連する型指定のRowオブジェクト 
   */
    private PaymentRequisitionMarginRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PaymentRequisitionMarginRow}と仮定される{@@link DataAccessObject}から新たに{@@link PaymentRequisitionMarginDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PaymentRequisitionMarginDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PaymentRequisitionMarginRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PaymentRequisitionMarginRow )
                return new PaymentRequisitionMarginDao( (PaymentRequisitionMarginRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PaymentRequisitionMarginRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PaymentRequisitionMarginRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PaymentRequisitionMarginRow}オブジェクト 
    */
    protected PaymentRequisitionMarginDao( PaymentRequisitionMarginRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PaymentRequisitionMarginRow}オブジェクトを取得します。
   */
    public PaymentRequisitionMarginRow getPaymentRequisitionMarginRow() {
        return row;
    }


  /** 
   * 指定の{@@link PaymentRequisitionMarginRow}オブジェクトから{@@link PaymentRequisitionMarginDao}オブジェクトを作成します。 
   * これは実際の{@@link PaymentRequisitionMarginRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PaymentRequisitionMarginDao}取得のために指定の{@@link PaymentRequisitionMarginRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PaymentRequisitionMarginDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PaymentRequisitionMarginDao forRow( PaymentRequisitionMarginRow row ) throws java.lang.IllegalArgumentException {
        return (PaymentRequisitionMarginDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PaymentRequisitionMarginRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PaymentRequisitionMarginRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PaymentRequisitionMarginPK}やデータベースレコードとして挿入される{@@link PaymentRequisitionMarginParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PaymentRequisitionMarginRow.TYPE );
    }


  /** 
   * {@@link PaymentRequisitionMarginRow}を一意に特定する{@@link PaymentRequisitionMarginPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PaymentRequisitionMarginRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PaymentRequisitionMarginParams}オブジェクトの主キーとして利用可能な{@@link PaymentRequisitionMarginPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PaymentRequisitionMarginPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PaymentRequisitionMarginRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_calcDate 検索対象であるp_calcDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PaymentRequisitionMarginRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PaymentRequisitionMarginRow findRowByPk( long p_accountId, java.sql.Timestamp p_calcDate ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitionMarginPK pk = new PaymentRequisitionMarginPK( p_accountId, p_calcDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPaymentRequisitionMarginPKオブジェクトから{@@link PaymentRequisitionMarginRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPaymentRequisitionMarginPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PaymentRequisitionMarginRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PaymentRequisitionMarginRow findRowByPk( PaymentRequisitionMarginPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PaymentRequisitionMarginRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,java.sql.Timestamp)}および{@@link #forRow(PaymentRequisitionMarginRow)}を使用してください。 
   */
    public static PaymentRequisitionMarginDao findDaoByPk( long p_accountId, java.sql.Timestamp p_calcDate ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitionMarginPK pk = new PaymentRequisitionMarginPK( p_accountId, p_calcDate );
        PaymentRequisitionMarginRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PaymentRequisitionMarginPK)}および{@@link #forRow(PaymentRequisitionMarginRow)}を使用してください。 
   */
    public static PaymentRequisitionMarginDao findDaoByPk( PaymentRequisitionMarginPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PaymentRequisitionMarginRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_calcResultMarginId, and にて指定の値に一致する{@@link PaymentRequisitionMarginRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_calcResultMarginId 検索対象であるp_calcResultMarginIdフィールドの値
   * 
   * @@return 引数指定のp_calcResultMarginId, and の値と一致する{@@link PaymentRequisitionMarginRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCalcResultMarginId( long p_calcResultMarginId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PaymentRequisitionMarginRow.TYPE,
            "calc_result_margin_id=?",
            null,
            new Object[] { new Long(p_calcResultMarginId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCalcResultMarginId(long)}および{@@link #forRow(PaymentRequisitionMarginRow)}を使用してください。 
   */
    public static List findDaosByCalcResultMarginId( long p_calcResultMarginId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCalcResultMarginId( p_calcResultMarginId ) );
    }


  /** 
   * p_calcDate, p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link PaymentRequisitionMarginRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_calcDate 検索対象であるp_calcDateフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_calcDate, p_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link PaymentRequisitionMarginRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCalcDateInstitutionCodeBranchCodeAccountCode( java.sql.Timestamp p_calcDate, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PaymentRequisitionMarginRow.TYPE,
            "calc_date=? and institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_calcDate, p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCalcDateInstitutionCodeBranchCodeAccountCode(java.sql.Timestamp, String, String, String)}および{@@link #forRow(PaymentRequisitionMarginRow)}を使用してください。 
   */
    public static List findDaosByCalcDateInstitutionCodeBranchCodeAccountCode( java.sql.Timestamp p_calcDate, String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCalcDateInstitutionCodeBranchCodeAccountCode( p_calcDate, p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
