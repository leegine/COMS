head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqOrderChangeStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;

/** 
 * {@@link FeqOrderChangeStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqOrderChangeStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqOrderChangeStatusPK 
 * @@see FeqOrderChangeStatusRow 
 */
public class FeqOrderChangeStatusDao extends DataAccessObject {


  /** 
   * この{@@link FeqOrderChangeStatusDao}に関連する型指定のRowオブジェクト 
   */
    private FeqOrderChangeStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqOrderChangeStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqOrderChangeStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqOrderChangeStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqOrderChangeStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqOrderChangeStatusRow )
                return new FeqOrderChangeStatusDao( (FeqOrderChangeStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqOrderChangeStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqOrderChangeStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqOrderChangeStatusRow}オブジェクト 
    */
    protected FeqOrderChangeStatusDao( FeqOrderChangeStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqOrderChangeStatusRow}オブジェクトを取得します。
   */
    public FeqOrderChangeStatusRow getFeqOrderChangeStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqOrderChangeStatusRow}オブジェクトから{@@link FeqOrderChangeStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqOrderChangeStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqOrderChangeStatusDao}取得のために指定の{@@link FeqOrderChangeStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqOrderChangeStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqOrderChangeStatusDao forRow( FeqOrderChangeStatusRow row ) throws java.lang.IllegalArgumentException {
        return (FeqOrderChangeStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqOrderChangeStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqOrderChangeStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqOrderChangeStatusPK}やデータベースレコードとして挿入される{@@link FeqOrderChangeStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqOrderChangeStatusRow.TYPE );
    }


  /** 
   * {@@link FeqOrderChangeStatusRow}を一意に特定する{@@link FeqOrderChangeStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqOrderChangeStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqOrderChangeStatusParams}オブジェクトの主キーとして利用可能な{@@link FeqOrderChangeStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqOrderChangeStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqOrderChangeStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_originalOrderId 検索対象であるp_originalOrderIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqOrderChangeStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqOrderChangeStatusRow findRowByPk( long p_accountId, long p_originalOrderId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderChangeStatusPK pk = new FeqOrderChangeStatusPK( p_accountId, p_originalOrderId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqOrderChangeStatusPKオブジェクトから{@@link FeqOrderChangeStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqOrderChangeStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqOrderChangeStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqOrderChangeStatusRow findRowByPk( FeqOrderChangeStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqOrderChangeStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,long)}および{@@link #forRow(FeqOrderChangeStatusRow)}を使用してください。 
   */
    public static FeqOrderChangeStatusDao findDaoByPk( long p_accountId, long p_originalOrderId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderChangeStatusPK pk = new FeqOrderChangeStatusPK( p_accountId, p_originalOrderId );
        FeqOrderChangeStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqOrderChangeStatusPK)}および{@@link #forRow(FeqOrderChangeStatusRow)}を使用してください。 
   */
    public static FeqOrderChangeStatusDao findDaoByPk( FeqOrderChangeStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderChangeStatusRow row = findRowByPk( pk );
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
   * p_accountId, p_originalOrderId, and にて指定の値から一意の{@@link FeqOrderChangeStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_originalOrderId 検索対象であるp_originalOrderIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_originalOrderId, and の値と一致する{@@link FeqOrderChangeStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqOrderChangeStatusRow findRowByAccountIdOriginalOrderId( long p_accountId, long p_originalOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderChangeStatusRow.TYPE,
            "account_id=? and original_order_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_originalOrderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderChangeStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderChangeStatusDao.findRowsByAccountIdOriginalOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdOriginalOrderId(long, long)}および{@@link #forRow(FeqOrderChangeStatusRow)}を使用してください。 
   */
    public static FeqOrderChangeStatusDao findDaoByAccountIdOriginalOrderId( long p_accountId, long p_originalOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdOriginalOrderId( p_accountId, p_originalOrderId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_originalOrderId, and にて指定の値から一意の{@@link FeqOrderChangeStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_originalOrderId 検索対象であるp_originalOrderIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_originalOrderId, and の値と一致する{@@link FeqOrderChangeStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqOrderChangeStatusRow findRowByInstitutionCodeBranchCodeAccountCodeOriginalOrderId( String p_institutionCode, String p_branchCode, String p_accountCode, long p_originalOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderChangeStatusRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and original_order_id=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, new Long(p_originalOrderId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderChangeStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderChangeStatusDao.findRowsByInstitutionCodeBranchCodeAccountCodeOriginalOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeOriginalOrderId(String, String, String, long)}および{@@link #forRow(FeqOrderChangeStatusRow)}を使用してください。 
   */
    public static FeqOrderChangeStatusDao findDaoByInstitutionCodeBranchCodeAccountCodeOriginalOrderId( String p_institutionCode, String p_branchCode, String p_accountCode, long p_originalOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeOriginalOrderId( p_institutionCode, p_branchCode, p_accountCode, p_originalOrderId ) );
    }


  /** 
   * p_accountId, p_newOrderId, and にて指定の値から一意の{@@link FeqOrderChangeStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_newOrderId 検索対象であるp_newOrderIdフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_newOrderId, and の値と一致する{@@link FeqOrderChangeStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqOrderChangeStatusRow findRowByAccountIdNewOrderId( long p_accountId, Long p_newOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderChangeStatusRow.TYPE,
            "account_id=? and new_order_id=?",
            null,
            new Object[] { new Long(p_accountId), p_newOrderId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderChangeStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderChangeStatusDao.findRowsByAccountIdNewOrderId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccountIdNewOrderId(long, Long)}および{@@link #forRow(FeqOrderChangeStatusRow)}を使用してください。 
   */
    public static FeqOrderChangeStatusDao findDaoByAccountIdNewOrderId( long p_accountId, Long p_newOrderId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdNewOrderId( p_accountId, p_newOrderId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
