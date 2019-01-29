head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BranchDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link BranchDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BranchRow}インスタンスへ関連付けることができます。 
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
 * @@see BranchPK 
 * @@see BranchRow 
 */
public class BranchDao extends DataAccessObject {


  /** 
   * この{@@link BranchDao}に関連する型指定のRowオブジェクト 
   */
    private BranchRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BranchRow}と仮定される{@@link DataAccessObject}から新たに{@@link BranchDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BranchDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BranchRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BranchRow )
                return new BranchDao( (BranchRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BranchRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BranchRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BranchRow}オブジェクト 
    */
    protected BranchDao( BranchRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BranchRow}オブジェクトを取得します。
   */
    public BranchRow getBranchRow() {
        return row;
    }


  /** 
   * 指定の{@@link BranchRow}オブジェクトから{@@link BranchDao}オブジェクトを作成します。 
   * これは実際の{@@link BranchRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BranchDao}取得のために指定の{@@link BranchRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BranchDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BranchDao forRow( BranchRow row ) throws java.lang.IllegalArgumentException {
        return (BranchDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BranchRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BranchRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BranchPK}やデータベースレコードとして挿入される{@@link BranchParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BranchRow.TYPE );
    }


  /** 
   * {@@link BranchRow}を一意に特定する{@@link BranchPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BranchRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BranchParams}オブジェクトの主キーとして利用可能な{@@link BranchPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BranchPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BranchPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BranchRow}オブジェクトを検索します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BranchRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BranchRow findRowByPk( long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchPK pk = new BranchPK( p_branchId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBranchPKオブジェクトから{@@link BranchRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBranchPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BranchRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BranchRow findRowByPk( BranchPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BranchRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static BranchDao findDaoByPk( long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchPK pk = new BranchPK( p_branchId );
        BranchRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BranchPK)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static BranchDao findDaoByPk( BranchPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link BranchDao}に紐付く{@@link BranchRow}内で外部キーの関係をもつ{@@link InstitutionRow}を検索します。 
   * 
   * @@return {@@link BranchDao}と外部キーの関係にある{@@link InstitutionRow} 
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
   * @@deprecated 代わりに{@@link #fetchInstitutionRowViaInstitutionId()}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public InstitutionDao fetchInstitutionDaoViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        DataAccessObject dao = InstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof InstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (InstitutionDao) dao;
    }


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link BranchDao}に関連する{@@link BranchRow}の外部キーがある{@@link TraderRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link BranchDao}に関連する{@@link BranchRow}の外部キーがある{@@link TraderRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchTraderRowsByBranchId() throws DataNetworkException, DataQueryException  {
        return TraderDao.findRowsByBranchId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTraderRowsByBranchId()}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public List fetchTraderDaosByBranchId() throws DataNetworkException, DataQueryException  {
        return TraderDao.findDaosByBranchId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchTraderRowsByBranchId()}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public List fetchTraderDaosBranchId() throws DataNetworkException, DataQueryException  {
        return fetchTraderDaosByBranchId();
    }


  /** 
   * この{@@link BranchDao}に関連する{@@link BranchRow}の外部キーがある{@@link MainAccountRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link BranchDao}に関連する{@@link BranchRow}の外部キーがある{@@link MainAccountRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchMainAccountRowsByBranchId() throws DataNetworkException, DataQueryException  {
        return MainAccountDao.findRowsByBranchId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMainAccountRowsByBranchId()}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public List fetchMainAccountDaosByBranchId() throws DataNetworkException, DataQueryException  {
        return MainAccountDao.findDaosByBranchId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchMainAccountRowsByBranchId()}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public List fetchMainAccountDaosBranchId() throws DataNetworkException, DataQueryException  {
        return fetchMainAccountDaosByBranchId();
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
   * {@@link InstitutionRow}と外部キーの関係にある{@@link BranchRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link InstitutionRow}オブジェクト 
   * @@return 指定の{@@link InstitutionRow}に外部キーを持つ{@@link BranchRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( row.getInstitutionId() );
    }


  /** 
   * {@@link InstitutionPK}と外部キーの関係にある{@@link BranchRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link InstitutionPK}オブジェクト 
   * @@return {@@link InstitutionPK}と外部キーが一致する値を持つ{@@link BranchRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( pk.institution_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link BranchRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link BranchRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionId( long p_institutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BranchRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Institution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionRow)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(InstitutionPK)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( pk.institution_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionId(long)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static List findDaosByInstitutionId( long p_institutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( p_institutionId ) );
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
   * p_branchId, and にて指定の値から一意の{@@link BranchRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 引数指定のp_branchId, and の値と一致する{@@link BranchRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BranchRow findRowByBranchId( long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BranchRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BranchRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BranchDao.findRowsByBranchId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBranchId(long)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static BranchDao findDaoByBranchId( long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchId( p_branchId ) );
    }


  /** 
   * p_institutionId, p_branchCode, and にて指定の値から一意の{@@link BranchRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionId 検索対象であるp_institutionIdフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionId, p_branchCode, and の値と一致する{@@link BranchRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BranchRow findRowByInstitutionIdBranchCode( long p_institutionId, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BranchRow.TYPE,
            "institution_id=? and branch_code=?",
            null,
            new Object[] { new Long(p_institutionId), p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BranchRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BranchDao.findRowsByInstitutionIdBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionIdBranchCode(long, String)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static BranchDao findDaoByInstitutionIdBranchCode( long p_institutionId, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionIdBranchCode( p_institutionId, p_branchCode ) );
    }


  /** 
   * p_institutionCode, p_branchCode, and にて指定の値から一意の{@@link BranchRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, and の値と一致する{@@link BranchRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BranchRow findRowByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BranchRow.TYPE,
            "institution_code=? and branch_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BranchRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BranchDao.findRowsByInstitutionCodeBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCode(String, String)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static BranchDao findDaoByInstitutionCodeBranchCode( String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCode( p_institutionCode, p_branchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_branchId, p_institutionCode, p_branchCode, and にて指定の値に一致する{@@link BranchRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_branchId, p_institutionCode, p_branchCode, and の値と一致する{@@link BranchRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchIdInstitutionCodeBranchCode( long p_branchId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BranchRow.TYPE,
            "branch_id=? and institution_code=? and branch_code=?",
            null,
            new Object[] { new Long(p_branchId), p_institutionCode, p_branchCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchIdInstitutionCodeBranchCode(long, String, String)}および{@@link #forRow(BranchRow)}を使用してください。 
   */
    public static List findDaosByBranchIdInstitutionCodeBranchCode( long p_branchId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByBranchIdInstitutionCodeBranchCode( p_branchId, p_institutionCode, p_branchCode ) );
    }

}
@
