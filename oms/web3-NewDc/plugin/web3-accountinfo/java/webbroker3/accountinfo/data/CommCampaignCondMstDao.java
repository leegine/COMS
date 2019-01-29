head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignCondMstDao.java;


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
 * {@@link CommCampaignCondMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CommCampaignCondMstRow}インスタンスへ関連付けることができます。 
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
 * @@see CommCampaignCondMstPK 
 * @@see CommCampaignCondMstRow 
 */
public class CommCampaignCondMstDao extends DataAccessObject {


  /** 
   * この{@@link CommCampaignCondMstDao}に関連する型指定のRowオブジェクト 
   */
    private CommCampaignCondMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CommCampaignCondMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link CommCampaignCondMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CommCampaignCondMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CommCampaignCondMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommCampaignCondMstRow )
                return new CommCampaignCondMstDao( (CommCampaignCondMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommCampaignCondMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommCampaignCondMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CommCampaignCondMstRow}オブジェクト 
    */
    protected CommCampaignCondMstDao( CommCampaignCondMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CommCampaignCondMstRow}オブジェクトを取得します。
   */
    public CommCampaignCondMstRow getCommCampaignCondMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link CommCampaignCondMstRow}オブジェクトから{@@link CommCampaignCondMstDao}オブジェクトを作成します。 
   * これは実際の{@@link CommCampaignCondMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CommCampaignCondMstDao}取得のために指定の{@@link CommCampaignCondMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CommCampaignCondMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CommCampaignCondMstDao forRow( CommCampaignCondMstRow row ) throws java.lang.IllegalArgumentException {
        return (CommCampaignCondMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommCampaignCondMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CommCampaignCondMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CommCampaignCondMstPK}やデータベースレコードとして挿入される{@@link CommCampaignCondMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommCampaignCondMstRow.TYPE );
    }


  /** 
   * {@@link CommCampaignCondMstRow}を一意に特定する{@@link CommCampaignCondMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CommCampaignCondMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CommCampaignCondMstParams}オブジェクトの主キーとして利用可能な{@@link CommCampaignCondMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CommCampaignCondMstPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new CommCampaignCondMstPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CommCampaignCondMstRow}オブジェクトを検索します。 
   * 
   * @@param p_campaignId 検索対象であるp_campaignIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommCampaignCondMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommCampaignCondMstRow findRowByPk( long p_campaignId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignCondMstPK pk = new CommCampaignCondMstPK( p_campaignId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCommCampaignCondMstPKオブジェクトから{@@link CommCampaignCondMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCommCampaignCondMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommCampaignCondMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommCampaignCondMstRow findRowByPk( CommCampaignCondMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommCampaignCondMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(CommCampaignCondMstRow)}を使用してください。 
   */
    public static CommCampaignCondMstDao findDaoByPk( long p_campaignId ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignCondMstPK pk = new CommCampaignCondMstPK( p_campaignId );
        CommCampaignCondMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CommCampaignCondMstPK)}および{@@link #forRow(CommCampaignCondMstRow)}を使用してください。 
   */
    public static CommCampaignCondMstDao findDaoByPk( CommCampaignCondMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCampaignCondMstRow row = findRowByPk( pk );
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
   * この{@@link CommCampaignCondMstDao}に関連する{@@link CommCampaignCondMstRow}の外部キーがある{@@link CommCampaignProductMstRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link CommCampaignCondMstDao}に関連する{@@link CommCampaignCondMstRow}の外部キーがある{@@link CommCampaignProductMstRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchCommCampaignProductMstRowsByCampaignId() throws DataNetworkException, DataQueryException  {
        return CommCampaignProductMstDao.findRowsByCampaignId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchCommCampaignProductMstRowsByCampaignId()}および{@@link #forRow(CommCampaignCondMstRow)}を使用してください。 
   */
    public List fetchCommCampaignProductMstDaosByCampaignId() throws DataNetworkException, DataQueryException  {
        return CommCampaignProductMstDao.findDaosByCampaignId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchCommCampaignProductMstRowsByCampaignId()}および{@@link #forRow(CommCampaignCondMstRow)}を使用してください。 
   */
    public List fetchCommCampaignProductMstDaosCampaignId() throws DataNetworkException, DataQueryException  {
        return fetchCommCampaignProductMstDaosByCampaignId();
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
   * p_campaignId, and にて指定の値から一意の{@@link CommCampaignCondMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_campaignId 検索対象であるp_campaignIdフィールドの値
   * 
   * @@return 引数指定のp_campaignId, and の値と一致する{@@link CommCampaignCondMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CommCampaignCondMstRow findRowByCampaignId( long p_campaignId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommCampaignCondMstRow.TYPE,
            "campaign_id=?",
            null,
            new Object[] { new Long(p_campaignId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommCampaignCondMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommCampaignCondMstDao.findRowsByCampaignId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByCampaignId(long)}および{@@link #forRow(CommCampaignCondMstRow)}を使用してください。 
   */
    public static CommCampaignCondMstDao findDaoByCampaignId( long p_campaignId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCampaignId( p_campaignId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_registType, and にて指定の値に一致する{@@link CommCampaignCondMstRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_registType 検索対象であるp_registTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_registType, and の値と一致する{@@link CommCampaignCondMstRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeRegistType( String p_institutionCode, String p_registType ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            CommCampaignCondMstRow.TYPE,
            "institution_code=? and regist_type=?",
            null,
            new Object[] { p_institutionCode, p_registType } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeRegistType(String, String)}および{@@link #forRow(CommCampaignCondMstRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeRegistType( String p_institutionCode, String p_registType ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeRegistType( p_institutionCode, p_registType ) );
    }

}
@
