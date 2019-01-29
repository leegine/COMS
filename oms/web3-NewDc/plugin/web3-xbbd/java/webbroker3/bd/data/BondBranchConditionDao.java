head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondBranchConditionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link BondBranchConditionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BondBranchConditionRow}インスタンスへ関連付けることができます。 
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
 * @@see BondBranchConditionPK 
 * @@see BondBranchConditionRow 
 */
public class BondBranchConditionDao extends DataAccessObject {


  /** 
   * この{@@link BondBranchConditionDao}に関連する型指定のRowオブジェクト 
   */
    private BondBranchConditionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BondBranchConditionRow}と仮定される{@@link DataAccessObject}から新たに{@@link BondBranchConditionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BondBranchConditionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BondBranchConditionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondBranchConditionRow )
                return new BondBranchConditionDao( (BondBranchConditionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondBranchConditionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondBranchConditionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BondBranchConditionRow}オブジェクト 
    */
    protected BondBranchConditionDao( BondBranchConditionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BondBranchConditionRow}オブジェクトを取得します。
   */
    public BondBranchConditionRow getBondBranchConditionRow() {
        return row;
    }


  /** 
   * 指定の{@@link BondBranchConditionRow}オブジェクトから{@@link BondBranchConditionDao}オブジェクトを作成します。 
   * これは実際の{@@link BondBranchConditionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BondBranchConditionDao}取得のために指定の{@@link BondBranchConditionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BondBranchConditionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BondBranchConditionDao forRow( BondBranchConditionRow row ) throws java.lang.IllegalArgumentException {
        return (BondBranchConditionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondBranchConditionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BondBranchConditionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BondBranchConditionPK}やデータベースレコードとして挿入される{@@link BondBranchConditionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondBranchConditionRow.TYPE );
    }


  /** 
   * {@@link BondBranchConditionRow}を一意に特定する{@@link BondBranchConditionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BondBranchConditionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BondBranchConditionParams}オブジェクトの主キーとして利用可能な{@@link BondBranchConditionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BondBranchConditionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BondBranchConditionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BondBranchConditionRow}オブジェクトを検索します。 
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondBranchConditionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondBranchConditionRow findRowByPk( long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchConditionPK pk = new BondBranchConditionPK( p_branchId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBondBranchConditionPKオブジェクトから{@@link BondBranchConditionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBondBranchConditionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondBranchConditionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondBranchConditionRow findRowByPk( BondBranchConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondBranchConditionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(BondBranchConditionRow)}を使用してください。 
   */
    public static BondBranchConditionDao findDaoByPk( long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchConditionPK pk = new BondBranchConditionPK( p_branchId );
        BondBranchConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BondBranchConditionPK)}および{@@link #forRow(BondBranchConditionRow)}を使用してください。 
   */
    public static BondBranchConditionDao findDaoByPk( BondBranchConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchConditionRow row = findRowByPk( pk );
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
   * p_branchId, and にて指定の値から一意の{@@link BondBranchConditionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 引数指定のp_branchId, and の値と一致する{@@link BondBranchConditionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondBranchConditionRow findRowByBranchId( long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondBranchConditionRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondBranchConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondBranchConditionDao.findRowsByBranchId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByBranchId(long)}および{@@link #forRow(BondBranchConditionRow)}を使用してください。 
   */
    public static BondBranchConditionDao findDaoByBranchId( long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchId( p_branchId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
