head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignProductMstDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommCampaignProductMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CommCampaignProductMstRow}インスタンスへ関連付けることができます。 
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
 * @@see CommCampaignProductMstPK 
 * @@see CommCampaignProductMstRow 
 */
public class CommCampaignProductMstDao extends DataAccessObject {


  /** 
   * この{@@link CommCampaignProductMstDao}に関連する型指定のRowオブジェクト 
   */
    private CommCampaignProductMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CommCampaignProductMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link CommCampaignProductMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CommCampaignProductMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CommCampaignProductMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommCampaignProductMstRow )
                return new CommCampaignProductMstDao( (CommCampaignProductMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommCampaignProductMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommCampaignProductMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CommCampaignProductMstRow}オブジェクト 
    */
    protected CommCampaignProductMstDao( CommCampaignProductMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CommCampaignProductMstRow}オブジェクトを取得します。
   */
    public CommCampaignProductMstRow getCommCampaignProductMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link CommCampaignProductMstRow}オブジェクトから{@@link CommCampaignProductMstDao}オブジェクトを作成します。 
   * これは実際の{@@link CommCampaignProductMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CommCampaignProductMstDao}取得のために指定の{@@link CommCampaignProductMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CommCampaignProductMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CommCampaignProductMstDao forRow( CommCampaignProductMstRow row ) throws java.lang.IllegalArgumentException {
        return (CommCampaignProductMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommCampaignProductMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CommCampaignProductMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CommCampaignProductMstPK}やデータベースレコードとして挿入される{@@link CommCampaignProductMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommCampaignProductMstRow.TYPE );
    }


  /** 
   * {@@link CommCampaignProductMstRow}を一意に特定する{@@link CommCampaignProductMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CommCampaignProductMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CommCampaignProductMstParams}オブジェクトの主キーとして利用可能な{@@link CommCampaignProductMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CommCampaignProductMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CommCampaignProductMstRow}オブジェクトを検索します。 
   * 
   * @@param p_campaignId 検索対象であるp_campaignIdフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommCampaignProductMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommCampaignProductMstRow findRowByPk( long p_campaignId, String p_commProductCode ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignProductMstPK pk = new CommCampaignProductMstPK( p_campaignId, p_commProductCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCommCampaignProductMstPKオブジェクトから{@@link CommCampaignProductMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCommCampaignProductMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommCampaignProductMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommCampaignProductMstRow findRowByPk( CommCampaignProductMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommCampaignProductMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public static CommCampaignProductMstDao findDaoByPk( long p_campaignId, String p_commProductCode ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignProductMstPK pk = new CommCampaignProductMstPK( p_campaignId, p_commProductCode );
        CommCampaignProductMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CommCampaignProductMstPK)}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public static CommCampaignProductMstDao findDaoByPk( CommCampaignProductMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignProductMstRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * この{@@link CommCampaignProductMstDao}に紐付く{@@link CommCampaignProductMstRow}内で外部キーの関係をもつ{@@link CommCampaignCondMstRow}を検索します。 
   * 
   * @@return {@@link CommCampaignProductMstDao}と外部キーの関係にある{@@link CommCampaignCondMstRow} 
   * @@exception DataFindException 外部キーIDがnullでなくクエリが実行されたが、データベースに該当データの存在がなくオブジェクト検索に失敗した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public CommCampaignCondMstRow fetchCommCampaignCondMstRowViaCampaignId() throws DataNetworkException, DataFindException, DataQueryException  {
        CommCampaignCondMstPK pk = new CommCampaignCondMstPK( row.getCampaignId() );
        Row row = CommCampaignCondMstDao.findRowByPk( pk );
        if ( row != null && !(row instanceof CommCampaignCondMstRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (CommCampaignCondMstRow) row;
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchCommCampaignCondMstRowViaCampaignId()}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public CommCampaignCondMstDao fetchCommCampaignCondMstDaoViaCampaignId() throws DataNetworkException, DataFindException, DataQueryException  {
        CommCampaignCondMstPK pk = new CommCampaignCondMstPK( row.getCampaignId() );
        DataAccessObject dao = CommCampaignCondMstDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof CommCampaignCondMstDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (CommCampaignCondMstDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for CommCampaignCondMst
    //-----------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}および{@@link #findRowsByCampaignId(CommCampaignCondMstRow)}を使用してください。 
   */
    public static List findRowsByCampaignId( CommCampaignCondMstDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByCampaignId( dao.getCommCampaignCondMstRow() );
    }


  /** 
   * {@@link CommCampaignCondMstRow}と外部キーの関係にある{@@link CommCampaignProductMstRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param row 外部キーの参照をする{@@link CommCampaignCondMstRow}オブジェクト 
   * @@return 指定の{@@link CommCampaignCondMstRow}に外部キーを持つ{@@link CommCampaignProductMstRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCampaignId( CommCampaignCondMstRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByCampaignId( row.getCampaignId() );
    }


  /** 
   * {@@link CommCampaignCondMstPK}と外部キーの関係にある{@@link CommCampaignProductMstRow}オブジェクトを参照し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param pk クエリ内で利用する{@@link CommCampaignCondMstPK}オブジェクト 
   * @@return {@@link CommCampaignCondMstPK}と外部キーが一致する値を持つ{@@link CommCampaignProductMstRow}の{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCampaignId( CommCampaignCondMstPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByCampaignId( pk.campaign_id );
    }


  /** 
   * 指定の外部キーの値（場合により複数）から{@@link CommCampaignProductMstRow}オブジェクトを検索し{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_campaignId 検索対象であるp_campaignIdフィールドの値
   * 
   * @@return 指定の外部キーを持つ{@@link CommCampaignProductMstRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCampaignId( long p_campaignId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignProductMstRow.TYPE,
            "campaign_id=?",
            null,
            new Object[] { new Long(p_campaignId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for CommCampaignCondMst
    //-----------------------------------------------------------------


  /** 
   * @@deprecated 代わりに{@@link DataAccessObject#getRow()}、{@@link #findRowsByCampaignId(CommCampaignCondMstRow)}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public static List findDaosByCampaignId( CommCampaignCondMstDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCampaignId( actor ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCampaignId(CommCampaignCondMstRow)}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public static List findDaosByCampaignId( CommCampaignCondMstRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCampaignId( row ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCampaignId(CommCampaignCondMstPK)}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public static List findDaosByCampaignId( CommCampaignCondMstPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCampaignId( pk.campaign_id ) );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCampaignId(long)}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public static List findDaosByCampaignId( long p_campaignId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByCampaignId( p_campaignId ) );
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
   * p_campaignId, p_commProductCode, and にて指定の値から一意の{@@link CommCampaignProductMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_campaignId 検索対象であるp_campaignIdフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * 
   * @@return 引数指定のp_campaignId, p_commProductCode, and の値と一致する{@@link CommCampaignProductMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CommCampaignProductMstRow findRowByCampaignIdCommProductCode( long p_campaignId, String p_commProductCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommCampaignProductMstRow.TYPE,
            "campaign_id=? and comm_product_code=?",
            null,
            new Object[] { new Long(p_campaignId), p_commProductCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommCampaignProductMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommCampaignProductMstDao.findRowsByCampaignIdCommProductCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByCampaignIdCommProductCode(long, String)}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public static CommCampaignProductMstDao findDaoByCampaignIdCommProductCode( long p_campaignId, String p_commProductCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCampaignIdCommProductCode( p_campaignId, p_commProductCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_commProductCode, and にて指定の値に一致する{@@link CommCampaignProductMstRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * 
   * @@return 引数指定のp_commProductCode, and の値と一致する{@@link CommCampaignProductMstRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCommProductCode( String p_commProductCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignProductMstRow.TYPE,
            "comm_product_code=?",
            null,
            new Object[] { p_commProductCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCommProductCode(String)}および{@@link #forRow(CommCampaignProductMstRow)}を使用してください。 
   */
    public static List findDaosByCommProductCode( String p_commProductCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCommProductCode( p_commProductCode ) );
    }

}
@
