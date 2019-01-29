head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.34.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TraderDao.java;


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
 * {@@link TraderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TraderRow}インスタンスへ関連付けることができます。 
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
 * @@see TraderPK 
 * @@see TraderRow 
 */
public class TraderDao extends DataAccessObject {


  /** 
   * この{@@link TraderDao}に関連する型指定のRowオブジェクト 
   */
    private TraderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TraderRow}と仮定される{@@link DataAccessObject}から新たに{@@link TraderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TraderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TraderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TraderRow )
                return new TraderDao( (TraderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TraderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TraderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TraderRow}オブジェクト 
    */
    protected TraderDao( TraderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TraderRow}オブジェクトを取得します。
   */
    public TraderRow getTraderRow() {
        return row;
    }


  /** 
   * 指定の{@@link TraderRow}オブジェクトから{@@link TraderDao}オブジェクトを作成します。 
   * これは実際の{@@link TraderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TraderDao}取得のために指定の{@@link TraderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TraderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TraderDao forRow( TraderRow row ) throws java.lang.IllegalArgumentException {
        return (TraderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TraderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TraderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TraderPK}やデータベースレコードとして挿入される{@@link TraderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TraderRow.TYPE );
    }


  /** 
   * {@@link TraderRow}を一意に特定する{@@link TraderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TraderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TraderParams}オブジェクトの主キーとして利用可能な{@@link TraderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TraderPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TraderPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TraderRow}オブジェクトを検索します。 
   * 
   * @@param p_traderId 検索対象であるp_traderIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TraderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TraderRow findRowByPk( long p_traderId ) throws DataFindException, DataQueryException, DataNetworkException {
        TraderPK pk = new TraderPK( p_traderId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTraderPKオブジェクトから{@@link TraderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTraderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TraderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TraderRow findRowByPk( TraderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TraderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static TraderDao findDaoByPk( long p_traderId ) throws DataFindException, DataQueryException, DataNetworkException {
        TraderPK pk = new TraderPK( p_traderId );
        TraderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TraderPK)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static TraderDao findDaoByPk( TraderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TraderRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link TraderDao}に紐付く{@@link TraderRow}内で外部キーの関係をもつ{@@link BranchRow}を検索します。 
   * 
   * @@return {@@link TraderDao}と外部キーの関係にある{@@link BranchRow} 
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
   * @@deprecated 代わりに{@@link #fetchBranchRowViaBranchId()}および{@@link #forRow(TraderRow)}を使用してください。 
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
    // Find Rows given foreign key values for Branch
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByBranchId(BranchRow)}を使用してください。 
   */
    public static List findRowsByBranchId( BranchDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( dao.getBranchRow() );
    }


  /** 
   * {@@link BranchRow}と外部キーの関係にある{@@link TraderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link BranchRow}オブジェクト 
   * @@return 指定の{@@link BranchRow}に外部キーを持つ{@@link TraderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}と外部キーの関係にある{@@link TraderRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link BranchPK}オブジェクト 
   * @@return {@@link BranchPK}と外部キーが一致する値を持つ{@@link TraderRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link TraderRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link TraderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TraderRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchRow)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(BranchPK)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByBranchId(long)}および{@@link #forRow(TraderRow)}を使用してください。 
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
   * p_traderId, and にて指定の値から一意の{@@link TraderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_traderId 検索対象であるp_traderIdフィールドの値
   * 
   * @@return 引数指定のp_traderId, and の値と一致する{@@link TraderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TraderRow findRowByTraderId( long p_traderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TraderRow.TYPE,
            "trader_id=?",
            null,
            new Object[] { new Long(p_traderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TraderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TraderDao.findRowsByTraderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByTraderId(long)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static TraderDao findDaoByTraderId( long p_traderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTraderId( p_traderId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_traderCode, and にて指定の値から一意の{@@link TraderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_traderCode 検索対象であるp_traderCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_traderCode, and の値と一致する{@@link TraderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TraderRow findRowByInstitutionCodeBranchCodeTraderCode( String p_institutionCode, String p_branchCode, String p_traderCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TraderRow.TYPE,
            "institution_code=? and branch_code=? and trader_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_traderCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TraderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TraderDao.findRowsByInstitutionCodeBranchCodeTraderCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeTraderCode(String, String, String)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static TraderDao findDaoByInstitutionCodeBranchCodeTraderCode( String p_institutionCode, String p_branchCode, String p_traderCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeTraderCode( p_institutionCode, p_branchCode, p_traderCode ) );
    }


  /** 
   * p_loginId, and にて指定の値から一意の{@@link TraderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_loginId 検索対象であるp_loginIdフィールドの値
   * 
   * @@return 引数指定のp_loginId, and の値と一致する{@@link TraderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TraderRow findRowByLoginId( long p_loginId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TraderRow.TYPE,
            "login_id=?",
            null,
            new Object[] { new Long(p_loginId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TraderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TraderDao.findRowsByLoginId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByLoginId(long)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static TraderDao findDaoByLoginId( long p_loginId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByLoginId( p_loginId ) );
    }


  /** 
   * p_branchId, p_traderCode, and にて指定の値から一意の{@@link TraderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_traderCode 検索対象であるp_traderCodeフィールドの値
   * 
   * @@return 引数指定のp_branchId, p_traderCode, and の値と一致する{@@link TraderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TraderRow findRowByBranchIdTraderCode( long p_branchId, String p_traderCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TraderRow.TYPE,
            "branch_id=? and trader_code=?",
            null,
            new Object[] { new Long(p_branchId), p_traderCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TraderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TraderDao.findRowsByBranchIdTraderCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBranchIdTraderCode(long, String)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static TraderDao findDaoByBranchIdTraderCode( long p_branchId, String p_traderCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchIdTraderCode( p_branchId, p_traderCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_traderId, p_institutionCode, p_traderCode, and にて指定の値に一致する{@@link TraderRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_traderId 検索対象であるp_traderIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_traderCode 検索対象であるp_traderCodeフィールドの値
   * 
   * @@return 引数指定のp_traderId, p_institutionCode, p_traderCode, and の値と一致する{@@link TraderRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByTraderIdInstitutionCodeTraderCode( long p_traderId, String p_institutionCode, String p_traderCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TraderRow.TYPE,
            "trader_id=? and institution_code=? and trader_code=?",
            null,
            new Object[] { new Long(p_traderId), p_institutionCode, p_traderCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByTraderIdInstitutionCodeTraderCode(long, String, String)}および{@@link #forRow(TraderRow)}を使用してください。 
   */
    public static List findDaosByTraderIdInstitutionCodeTraderCode( long p_traderId, String p_institutionCode, String p_traderCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTraderIdInstitutionCodeTraderCode( p_traderId, p_institutionCode, p_traderCode ) );
    }

}
@
