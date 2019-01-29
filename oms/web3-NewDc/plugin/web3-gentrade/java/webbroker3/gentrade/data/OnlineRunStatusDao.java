head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.41.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OnlineRunStatusDao.java;


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
 * {@@link OnlineRunStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OnlineRunStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see OnlineRunStatusPK 
 * @@see OnlineRunStatusRow 
 */
public class OnlineRunStatusDao extends DataAccessObject {


  /** 
   * この{@@link OnlineRunStatusDao}に関連する型指定のRowオブジェクト 
   */
    private OnlineRunStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OnlineRunStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link OnlineRunStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OnlineRunStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OnlineRunStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OnlineRunStatusRow )
                return new OnlineRunStatusDao( (OnlineRunStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OnlineRunStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OnlineRunStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OnlineRunStatusRow}オブジェクト 
    */
    protected OnlineRunStatusDao( OnlineRunStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OnlineRunStatusRow}オブジェクトを取得します。
   */
    public OnlineRunStatusRow getOnlineRunStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link OnlineRunStatusRow}オブジェクトから{@@link OnlineRunStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link OnlineRunStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OnlineRunStatusDao}取得のために指定の{@@link OnlineRunStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OnlineRunStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OnlineRunStatusDao forRow( OnlineRunStatusRow row ) throws java.lang.IllegalArgumentException {
        return (OnlineRunStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OnlineRunStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OnlineRunStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OnlineRunStatusPK}やデータベースレコードとして挿入される{@@link OnlineRunStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OnlineRunStatusRow.TYPE );
    }


  /** 
   * {@@link OnlineRunStatusRow}を一意に特定する{@@link OnlineRunStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OnlineRunStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OnlineRunStatusParams}オブジェクトの主キーとして利用可能な{@@link OnlineRunStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OnlineRunStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OnlineRunStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * @@param p_onlineServiceDiv 検索対象であるp_onlineServiceDivフィールドの値
   * @@param p_accountIdFrom 検索対象であるp_accountIdFromフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OnlineRunStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OnlineRunStatusRow findRowByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        OnlineRunStatusPK pk = new OnlineRunStatusPK( p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOnlineRunStatusPKオブジェクトから{@@link OnlineRunStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOnlineRunStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OnlineRunStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OnlineRunStatusRow findRowByPk( OnlineRunStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OnlineRunStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum,String,String,long)}および{@@link #forRow(OnlineRunStatusRow)}を使用してください。 
   */
    public static OnlineRunStatusDao findDaoByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        OnlineRunStatusPK pk = new OnlineRunStatusPK( p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom );
        OnlineRunStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OnlineRunStatusPK)}および{@@link #forRow(OnlineRunStatusRow)}を使用してください。 
   */
    public static OnlineRunStatusDao findDaoByPk( OnlineRunStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OnlineRunStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom, and にて指定の値から一意の{@@link OnlineRunStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * @@param p_onlineServiceDiv 検索対象であるp_onlineServiceDivフィールドの値
   * @@param p_accountIdFrom 検索対象であるp_accountIdFromフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom, and の値と一致する{@@link OnlineRunStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static OnlineRunStatusRow findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OnlineRunStatusRow.TYPE,
            "institution_code=? and product_type=? and future_option_div=? and online_service_div=? and account_id_from=?",
            null,
            new Object[] { p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, new Long(p_accountIdFrom) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OnlineRunStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OnlineRunStatusDao.findRowsByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String, String, long)}および{@@link #forRow(OnlineRunStatusRow)}を使用してください。 
   */
    public static OnlineRunStatusDao findDaoByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom( p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
