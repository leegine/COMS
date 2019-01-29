head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ipo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IpoBookbuildingDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IpoBookbuildingRow}インスタンスへ関連付けることができます。 
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
 * @@see IpoBookbuildingPK 
 * @@see IpoBookbuildingRow 
 */
public class IpoBookbuildingDao extends DataAccessObject {


  /** 
   * この{@@link IpoBookbuildingDao}に関連する型指定のRowオブジェクト 
   */
    private IpoBookbuildingRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IpoBookbuildingRow}と仮定される{@@link DataAccessObject}から新たに{@@link IpoBookbuildingDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IpoBookbuildingDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IpoBookbuildingRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IpoBookbuildingRow )
                return new IpoBookbuildingDao( (IpoBookbuildingRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IpoBookbuildingRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IpoBookbuildingRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IpoBookbuildingRow}オブジェクト 
    */
    protected IpoBookbuildingDao( IpoBookbuildingRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IpoBookbuildingRow}オブジェクトを取得します。
   */
    public IpoBookbuildingRow getIpoBookbuildingRow() {
        return row;
    }


  /** 
   * 指定の{@@link IpoBookbuildingRow}オブジェクトから{@@link IpoBookbuildingDao}オブジェクトを作成します。 
   * これは実際の{@@link IpoBookbuildingRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IpoBookbuildingDao}取得のために指定の{@@link IpoBookbuildingRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IpoBookbuildingDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IpoBookbuildingDao forRow( IpoBookbuildingRow row ) throws java.lang.IllegalArgumentException {
        return (IpoBookbuildingDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IpoBookbuildingRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IpoBookbuildingRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IpoBookbuildingPK}やデータベースレコードとして挿入される{@@link IpoBookbuildingParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IpoBookbuildingRow.TYPE );
    }


  /** 
   * {@@link IpoBookbuildingRow}を一意に特定する{@@link IpoBookbuildingPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IpoBookbuildingRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IpoBookbuildingParams}オブジェクトの主キーとして利用可能な{@@link IpoBookbuildingPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IpoBookbuildingPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IpoBookbuildingRow}オブジェクトを検索します。 
   * 
   * @@param p_ipoProductId 検索対象であるp_ipoProductIdフィールドの値
   * @@param p_bbDiv 検索対象であるp_bbDivフィールドの値
   * @@param p_bbSeq 検索対象であるp_bbSeqフィールドの値
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IpoBookbuildingRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IpoBookbuildingRow findRowByPk( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingPK pk = new IpoBookbuildingPK( p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIpoBookbuildingPKオブジェクトから{@@link IpoBookbuildingRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIpoBookbuildingPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IpoBookbuildingRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IpoBookbuildingRow findRowByPk( IpoBookbuildingPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IpoBookbuildingRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,long,long)}および{@@link #forRow(IpoBookbuildingRow)}を使用してください。 
   */
    public static IpoBookbuildingDao findDaoByPk( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingPK pk = new IpoBookbuildingPK( p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId );
        IpoBookbuildingRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IpoBookbuildingPK)}および{@@link #forRow(IpoBookbuildingRow)}を使用してください。 
   */
    public static IpoBookbuildingDao findDaoByPk( IpoBookbuildingPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoBookbuildingRow row = findRowByPk( pk );
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
   * p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId, and にて指定の値から一意の{@@link IpoBookbuildingRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_ipoProductId 検索対象であるp_ipoProductIdフィールドの値
   * @@param p_bbDiv 検索対象であるp_bbDivフィールドの値
   * @@param p_bbSeq 検索対象であるp_bbSeqフィールドの値
   * @@param p_branchId 検索対象であるp_branchIdフィールドの値
   * 
   * @@return 引数指定のp_ipoProductId, p_bbDiv, p_bbSeq, p_branchId, and の値と一致する{@@link IpoBookbuildingRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IpoBookbuildingRow findRowByIpoProductIdBbDivBbSeqBranchId( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IpoBookbuildingRow.TYPE,
            "ipo_product_id=? and bb_div=? and bb_seq=? and branch_id=?",
            null,
            new Object[] { new Long(p_ipoProductId), p_bbDiv, new Long(p_bbSeq), new Long(p_branchId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IpoBookbuildingRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IpoBookbuildingDao.findRowsByIpoProductIdBbDivBbSeqBranchId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByIpoProductIdBbDivBbSeqBranchId(long, String, long, long)}および{@@link #forRow(IpoBookbuildingRow)}を使用してください。 
   */
    public static IpoBookbuildingDao findDaoByIpoProductIdBbDivBbSeqBranchId( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByIpoProductIdBbDivBbSeqBranchId( p_ipoProductId, p_bbDiv, p_bbSeq, p_branchId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
