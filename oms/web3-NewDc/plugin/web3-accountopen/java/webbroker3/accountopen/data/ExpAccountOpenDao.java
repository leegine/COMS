head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ExpAccountOpenDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link ExpAccountOpenDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ExpAccountOpenRow}インスタンスへ関連付けることができます。 
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
 * @@see ExpAccountOpenPK 
 * @@see ExpAccountOpenRow 
 */
public class ExpAccountOpenDao extends DataAccessObject {


  /** 
   * この{@@link ExpAccountOpenDao}に関連する型指定のRowオブジェクト 
   */
    private ExpAccountOpenRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ExpAccountOpenRow}と仮定される{@@link DataAccessObject}から新たに{@@link ExpAccountOpenDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ExpAccountOpenDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ExpAccountOpenRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExpAccountOpenRow )
                return new ExpAccountOpenDao( (ExpAccountOpenRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExpAccountOpenRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExpAccountOpenRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ExpAccountOpenRow}オブジェクト 
    */
    protected ExpAccountOpenDao( ExpAccountOpenRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ExpAccountOpenRow}オブジェクトを取得します。
   */
    public ExpAccountOpenRow getExpAccountOpenRow() {
        return row;
    }


  /** 
   * 指定の{@@link ExpAccountOpenRow}オブジェクトから{@@link ExpAccountOpenDao}オブジェクトを作成します。 
   * これは実際の{@@link ExpAccountOpenRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ExpAccountOpenDao}取得のために指定の{@@link ExpAccountOpenRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ExpAccountOpenDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ExpAccountOpenDao forRow( ExpAccountOpenRow row ) throws java.lang.IllegalArgumentException {
        return (ExpAccountOpenDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExpAccountOpenRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ExpAccountOpenRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ExpAccountOpenPK}やデータベースレコードとして挿入される{@@link ExpAccountOpenParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExpAccountOpenRow.TYPE );
    }


  /** 
   * {@@link ExpAccountOpenRow}を一意に特定する{@@link ExpAccountOpenPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ExpAccountOpenRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ExpAccountOpenParams}オブジェクトの主キーとして利用可能な{@@link ExpAccountOpenPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ExpAccountOpenPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ExpAccountOpenRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExpAccountOpenRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExpAccountOpenRow findRowByPk( String p_institutionCode, String p_accOpenRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenPK pk = new ExpAccountOpenPK( p_institutionCode, p_accOpenRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * 指定のExpAccountOpenPKオブジェクトから{@@link ExpAccountOpenRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するExpAccountOpenPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ExpAccountOpenRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ExpAccountOpenRow findRowByPk( ExpAccountOpenPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExpAccountOpenRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static ExpAccountOpenDao findDaoByPk( String p_institutionCode, String p_accOpenRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenPK pk = new ExpAccountOpenPK( p_institutionCode, p_accOpenRequestNumber );
        ExpAccountOpenRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ExpAccountOpenPK)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static ExpAccountOpenDao findDaoByPk( ExpAccountOpenPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link ExpAccountOpenDao}に紐付く{@@link ExpAccountOpenRow}内で外部キーの関係をもつ{@@link InstitutionRow}を検索します。 
   * 
   * @@return {@@link ExpAccountOpenDao}と外部キーの関係にある{@@link InstitutionRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public InstitutionRow fetchInstitutionRowViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        Row row = InstitutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof InstitutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (InstitutionRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchInstitutionRowViaInstitutionId()}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public InstitutionDao fetchInstitutionDaoViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        DataAccessObject dao = InstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof InstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (InstitutionDao) dao;
    }


  /** 
   * この{@@link ExpAccountOpenDao}に紐付く{@@link ExpAccountOpenRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link ExpAccountOpenDao}と外部キーの関係にある{@@link BranchRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public BranchRow fetchBranchRowViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        Row row = BranchDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BranchRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BranchRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Institution
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByInstitutionId(InstitutionRow)}を使用してください。 
   */
    public static List findRowsByInstitutionId( InstitutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( dao.getInstitutionRow() );
    }


  /** 
   * {@@link InstitutionRow}と外部キーの関係にある{@@link ExpAccountOpenRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link InstitutionRow}オブジェクト 
   * @@return 指定の{@@link InstitutionRow}に外部キーを持つ{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( row.getInstitutionId() );
    }


  /** 
   * {@@link InstitutionPK}と外部キーの関係にある{@@link ExpAccountOpenRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link InstitutionPK}オブジェクト 
   * @@return {@@link InstitutionPK}と外部キーが一致する値を持つ{@@link ExpAccountOpenRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( pk.institution_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link ExpAccountOpenRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( long p_institutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Institution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionPK)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( pk.institution_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(long)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( long p_institutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( p_institutionId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Branch
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByBranchId(BranchRow)}を使用してください。 
   */
    public static List findRowsByBranchId( BranchDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( dao.getBranchRow() );
    }


  /** 
   * {@@link BranchRow}と外部キーの関係にある{@@link ExpAccountOpenRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link ExpAccountOpenRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link ExpAccountOpenRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link ExpAccountOpenRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
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
   * p_institutionCode, p_accOpenRequestNumber, and にて指定の値から一意の{@@link ExpAccountOpenRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_accOpenRequestNumber, and の値と一致する{@@link ExpAccountOpenRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static ExpAccountOpenRow findRowByInstitutionCodeAccOpenRequestNumber( String p_institutionCode, String p_accOpenRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "institution_code=? and acc_open_request_number=?",
            null,
            new Object[] { p_institutionCode, p_accOpenRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExpAccountOpenRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExpAccountOpenDao.findRowsByInstitutionCodeAccOpenRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAccOpenRequestNumber(String, String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static ExpAccountOpenDao findDaoByInstitutionCodeAccOpenRequestNumber( String p_institutionCode, String p_accOpenRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccOpenRequestNumber( p_institutionCode, p_accOpenRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }


  /** 
   * p_branchCode, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_branchCode, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchCode( String p_branchCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "branch_code=?",
            null,
            new Object[] { p_branchCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchCode(String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByBranchCode( String p_branchCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByBranchCode( p_branchCode ) );
    }


  /** 
   * p_accOpenRequestNumber, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accOpenRequestNumber 検索対象であるp_accOpenRequestNumberフィールドの値
   * 
   * @@return 引数指定のp_accOpenRequestNumber, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccOpenRequestNumber( String p_accOpenRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "acc_open_request_number=?",
            null,
            new Object[] { p_accOpenRequestNumber } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccOpenRequestNumber(String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByAccOpenRequestNumber( String p_accOpenRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccOpenRequestNumber( p_accOpenRequestNumber ) );
    }


  /** 
   * p_accountCode, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_accountCode, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountCode( String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "account_code=?",
            null,
            new Object[] { p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountCode(String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByAccountCode( String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountCode( p_accountCode ) );
    }


  /** 
   * p_accountDiv, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * 
   * @@return 引数指定のp_accountDiv, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountDiv( String p_accountDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "account_div=?",
            null,
            new Object[] { p_accountDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountDiv(String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByAccountDiv( String p_accountDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountDiv( p_accountDiv ) );
    }


  /** 
   * p_infomationClaimDatetime, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_infomationClaimDatetime 検索対象であるp_infomationClaimDatetimeフィールドの値
   * 
   * @@return 引数指定のp_infomationClaimDatetime, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInfomationClaimDatetime( java.sql.Timestamp p_infomationClaimDatetime ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "infomation_claim_datetime=?",
            null,
            new Object[] { p_infomationClaimDatetime } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInfomationClaimDatetime(java.sql.Timestamp)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByInfomationClaimDatetime( java.sql.Timestamp p_infomationClaimDatetime ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInfomationClaimDatetime( p_infomationClaimDatetime ) );
    }


  /** 
   * p_accountOpenDate, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountOpenDate 検索対象であるp_accountOpenDateフィールドの値
   * 
   * @@return 引数指定のp_accountOpenDate, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountOpenDate( java.sql.Timestamp p_accountOpenDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "account_open_date=?",
            null,
            new Object[] { p_accountOpenDate } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountOpenDate(java.sql.Timestamp)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByAccountOpenDate( java.sql.Timestamp p_accountOpenDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountOpenDate( p_accountOpenDate ) );
    }


  /** 
   * p_familyNameAlt1, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_familyNameAlt1 検索対象であるp_familyNameAlt1フィールドの値
   * 
   * @@return 引数指定のp_familyNameAlt1, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByFamilyNameAlt1( String p_familyNameAlt1 ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "family_name_alt1=?",
            null,
            new Object[] { p_familyNameAlt1 } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByFamilyNameAlt1(String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByFamilyNameAlt1( String p_familyNameAlt1 ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByFamilyNameAlt1( p_familyNameAlt1 ) );
    }


  /** 
   * p_givenNameAlt1, and にて指定の値に一致する{@@link ExpAccountOpenRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_givenNameAlt1 検索対象であるp_givenNameAlt1フィールドの値
   * 
   * @@return 引数指定のp_givenNameAlt1, and の値と一致する{@@link ExpAccountOpenRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByGivenNameAlt1( String p_givenNameAlt1 ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenRow.TYPE,
            "given_name_alt1=?",
            null,
            new Object[] { p_givenNameAlt1 } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByGivenNameAlt1(String)}および{@@link #forRow(ExpAccountOpenRow)}を使用してください。 
   */
    public static List findDaosByGivenNameAlt1( String p_givenNameAlt1 ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByGivenNameAlt1( p_givenNameAlt1 ) );
    }

}
@
