head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.39.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	DepositInformDao.java;


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
 * {@@link DepositInformDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DepositInformRow}インスタンスへ関連付けることができます。 
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
 * @@see DepositInformPK 
 * @@see DepositInformRow 
 */
public class DepositInformDao extends DataAccessObject {


  /** 
   * この{@@link DepositInformDao}に関連する型指定のRowオブジェクト 
   */
    private DepositInformRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DepositInformRow}と仮定される{@@link DataAccessObject}から新たに{@@link DepositInformDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DepositInformDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DepositInformRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DepositInformRow )
                return new DepositInformDao( (DepositInformRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DepositInformRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DepositInformRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DepositInformRow}オブジェクト 
    */
    protected DepositInformDao( DepositInformRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DepositInformRow}オブジェクトを取得します。
   */
    public DepositInformRow getDepositInformRow() {
        return row;
    }


  /** 
   * 指定の{@@link DepositInformRow}オブジェクトから{@@link DepositInformDao}オブジェクトを作成します。 
   * これは実際の{@@link DepositInformRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DepositInformDao}取得のために指定の{@@link DepositInformRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DepositInformDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DepositInformDao forRow( DepositInformRow row ) throws java.lang.IllegalArgumentException {
        return (DepositInformDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DepositInformRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DepositInformRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DepositInformPK}やデータベースレコードとして挿入される{@@link DepositInformParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DepositInformRow.TYPE );
    }


  /** 
   * {@@link DepositInformRow}を一意に特定する{@@link DepositInformPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DepositInformRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DepositInformParams}オブジェクトの主キーとして利用可能な{@@link DepositInformPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DepositInformPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DepositInformRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DepositInformRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DepositInformRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        DepositInformPK pk = new DepositInformPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDepositInformPKオブジェクトから{@@link DepositInformRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDepositInformPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DepositInformRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DepositInformRow findRowByPk( DepositInformPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DepositInformRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(DepositInformRow)}を使用してください。 
   */
    public static DepositInformDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        DepositInformPK pk = new DepositInformPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber );
        DepositInformRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DepositInformPK)}および{@@link #forRow(DepositInformRow)}を使用してください。 
   */
    public static DepositInformDao findDaoByPk( DepositInformPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DepositInformRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link DepositInformDao}に紐付く{@@link DepositInformRow}内で外部キーの関係をもつ{@@link FinInstitutionRow}を検索します。 
   * 
   * @@return {@@link DepositInformDao}と外部キーの関係にある{@@link FinInstitutionRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public FinInstitutionRow fetchFinInstitutionRowViaInstitutionCodeFinInstitutionCode() throws DataNetworkException, DataFindException, DataQueryException  {
        FinInstitutionPK pk = new FinInstitutionPK( row.getInstitutionCode(), row.getFinInstitutionCode() );
        Row row = FinInstitutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof FinInstitutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (FinInstitutionRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchFinInstitutionRowViaInstitutionCodeFinInstitutionCode()}および{@@link #forRow(DepositInformRow)}を使用してください。 
   */
    public FinInstitutionDao fetchFinInstitutionDaoViaInstitutionCodeFinInstitutionCode() throws DataNetworkException, DataFindException, DataQueryException  {
        FinInstitutionPK pk = new FinInstitutionPK( row.getInstitutionCode(), row.getFinInstitutionCode() );
        DataAccessObject dao = FinInstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof FinInstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (FinInstitutionDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for FinInstitution
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByInstitutionCodeFinInstitutionCode(FinInstitutionRow)}を使用してください。 
   */
    public static List findRowsByInstitutionCodeFinInstitutionCode( FinInstitutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeFinInstitutionCode( dao.getFinInstitutionRow() );
    }


  /** 
   * {@@link FinInstitutionRow}と外部キーの関係にある{@@link DepositInformRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link FinInstitutionRow}オブジェクト 
   * @@return 指定の{@@link FinInstitutionRow}に外部キーを持つ{@@link DepositInformRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeFinInstitutionCode( FinInstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeFinInstitutionCode( row.getInstitutionCode(), row.getFinInstitutionCode() );
    }


  /** 
   * {@@link FinInstitutionPK}と外部キーの関係にある{@@link DepositInformRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link FinInstitutionPK}オブジェクト 
   * @@return {@@link FinInstitutionPK}と外部キーが一致する値を持つ{@@link DepositInformRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeFinInstitutionCode( FinInstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeFinInstitutionCode( pk.institution_code, pk.fin_institution_code );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link DepositInformRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link DepositInformRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeFinInstitutionCode( String p_institutionCode, String p_finInstitutionCode  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DepositInformRow.TYPE,
            "institution_code=? and fin_institution_code=?",
            null,
            new Object[] { p_institutionCode, p_finInstitutionCode } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for FinInstitution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByInstitutionCodeFinInstitutionCode(FinInstitutionRow)}および{@@link #forRow(DepositInformRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeFinInstitutionCode( FinInstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeFinInstitutionCode( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeFinInstitutionCode(FinInstitutionRow)}および{@@link #forRow(DepositInformRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeFinInstitutionCode( FinInstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeFinInstitutionCode( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeFinInstitutionCode(FinInstitutionPK)}および{@@link #forRow(DepositInformRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeFinInstitutionCode( FinInstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeFinInstitutionCode( pk.institution_code, pk.fin_institution_code ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeFinInstitutionCode(String, String)}および{@@link #forRow(DepositInformRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeFinInstitutionCode( String p_institutionCode, String p_finInstitutionCode ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeFinInstitutionCode( p_institutionCode, p_finInstitutionCode ) );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and にて指定の値から一意の{@@link DepositInformRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, and の値と一致する{@@link DepositInformRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DepositInformRow findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DepositInformRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DepositInformRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DepositInformDao.findRowsByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(String, String, String, String)}および{@@link #forRow(DepositInformRow)}を使用してください。 
   */
    public static DepositInformDao findDaoByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
