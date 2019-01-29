head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.28.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CommCodeChgMstDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommCodeChgMstDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CommCodeChgMstRow}インスタンスへ関連付けることができます。 
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
 * @@see CommCodeChgMstPK 
 * @@see CommCodeChgMstRow 
 */
public class CommCodeChgMstDao extends DataAccessObject {


  /** 
   * この{@@link CommCodeChgMstDao}に関連する型指定のRowオブジェクト 
   */
    private CommCodeChgMstRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CommCodeChgMstRow}と仮定される{@@link DataAccessObject}から新たに{@@link CommCodeChgMstDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CommCodeChgMstDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CommCodeChgMstRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommCodeChgMstRow )
                return new CommCodeChgMstDao( (CommCodeChgMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommCodeChgMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommCodeChgMstRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CommCodeChgMstRow}オブジェクト 
    */
    protected CommCodeChgMstDao( CommCodeChgMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CommCodeChgMstRow}オブジェクトを取得します。
   */
    public CommCodeChgMstRow getCommCodeChgMstRow() {
        return row;
    }


  /** 
   * 指定の{@@link CommCodeChgMstRow}オブジェクトから{@@link CommCodeChgMstDao}オブジェクトを作成します。 
   * これは実際の{@@link CommCodeChgMstRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CommCodeChgMstDao}取得のために指定の{@@link CommCodeChgMstRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CommCodeChgMstDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CommCodeChgMstDao forRow( CommCodeChgMstRow row ) throws java.lang.IllegalArgumentException {
        return (CommCodeChgMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommCodeChgMstRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CommCodeChgMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CommCodeChgMstPK}やデータベースレコードとして挿入される{@@link CommCodeChgMstParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommCodeChgMstRow.TYPE );
    }


  /** 
   * {@@link CommCodeChgMstRow}を一意に特定する{@@link CommCodeChgMstPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CommCodeChgMstRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CommCodeChgMstParams}オブジェクトの主キーとして利用可能な{@@link CommCodeChgMstPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CommCodeChgMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CommCodeChgMstRow}オブジェクトを検索します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_commissionNo 検索対象であるp_commissionNoフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommCodeChgMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommCodeChgMstRow findRowByPk( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCodeChgMstPK pk = new CommCodeChgMstPK( p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCommCodeChgMstPKオブジェクトから{@@link CommCodeChgMstRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCommCodeChgMstPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CommCodeChgMstRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CommCodeChgMstRow findRowByPk( CommCodeChgMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommCodeChgMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,String,String)}および{@@link #forRow(CommCodeChgMstRow)}を使用してください。 
   */
    public static CommCodeChgMstDao findDaoByPk( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCodeChgMstPK pk = new CommCodeChgMstPK( p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate );
        CommCodeChgMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CommCodeChgMstPK)}および{@@link #forRow(CommCodeChgMstRow)}を使用してください。 
   */
    public static CommCodeChgMstDao findDaoByPk( CommCodeChgMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommCodeChgMstRow row = findRowByPk( pk );
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
   * p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate, and にて指定の値から一意の{@@link CommCodeChgMstRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * @@param p_commProductCode 検索対象であるp_commProductCodeフィールドの値
   * @@param p_commissionNo 検索対象であるp_commissionNoフィールドの値
   * @@param p_appliStartDate 検索対象であるp_appliStartDateフィールドの値
   * 
   * @@return 引数指定のp_branchId, p_commProductCode, p_commissionNo, p_appliStartDate, and の値と一致する{@@link CommCodeChgMstRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CommCodeChgMstRow findRowByBranchIdCommProductCodeCommissionNoAppliStartDate( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommCodeChgMstRow.TYPE,
            "branch_id=? and comm_product_code=? and commission_no=? and appli_start_date=?",
            null,
            new Object[] { new Long(p_branchId), p_commProductCode, p_commissionNo, p_appliStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommCodeChgMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommCodeChgMstDao.findRowsByBranchIdCommProductCodeCommissionNoAppliStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBranchIdCommProductCodeCommissionNoAppliStartDate(long, String, String, String)}および{@@link #forRow(CommCodeChgMstRow)}を使用してください。 
   */
    public static CommCodeChgMstDao findDaoByBranchIdCommProductCodeCommissionNoAppliStartDate( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchIdCommProductCodeCommissionNoAppliStartDate( p_branchId, p_commProductCode, p_commissionNo, p_appliStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
