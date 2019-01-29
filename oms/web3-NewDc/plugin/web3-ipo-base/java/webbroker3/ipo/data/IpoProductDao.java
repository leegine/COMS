head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoProductDao.java;


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
 * {@@link IpoProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IpoProductRow}インスタンスへ関連付けることができます。 
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
 * @@see IpoProductPK 
 * @@see IpoProductRow 
 */
public class IpoProductDao extends DataAccessObject {


  /** 
   * この{@@link IpoProductDao}に関連する型指定のRowオブジェクト 
   */
    private IpoProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IpoProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link IpoProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IpoProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IpoProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IpoProductRow )
                return new IpoProductDao( (IpoProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IpoProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IpoProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IpoProductRow}オブジェクト 
    */
    protected IpoProductDao( IpoProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IpoProductRow}オブジェクトを取得します。
   */
    public IpoProductRow getIpoProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link IpoProductRow}オブジェクトから{@@link IpoProductDao}オブジェクトを作成します。 
   * これは実際の{@@link IpoProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IpoProductDao}取得のために指定の{@@link IpoProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IpoProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IpoProductDao forRow( IpoProductRow row ) throws java.lang.IllegalArgumentException {
        return (IpoProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IpoProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IpoProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IpoProductPK}やデータベースレコードとして挿入される{@@link IpoProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IpoProductRow.TYPE );
    }


  /** 
   * {@@link IpoProductRow}を一意に特定する{@@link IpoProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IpoProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IpoProductParams}オブジェクトの主キーとして利用可能な{@@link IpoProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IpoProductPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IpoProductPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IpoProductRow}オブジェクトを検索します。 
   * 
   * @@param p_ipoProductId 検索対象であるp_ipoProductIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IpoProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IpoProductRow findRowByPk( long p_ipoProductId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoProductPK pk = new IpoProductPK( p_ipoProductId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIpoProductPKオブジェクトから{@@link IpoProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIpoProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IpoProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IpoProductRow findRowByPk( IpoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IpoProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(IpoProductRow)}を使用してください。 
   */
    public static IpoProductDao findDaoByPk( long p_ipoProductId ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoProductPK pk = new IpoProductPK( p_ipoProductId );
        IpoProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IpoProductPK)}および{@@link #forRow(IpoProductRow)}を使用してください。 
   */
    public static IpoProductDao findDaoByPk( IpoProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IpoProductRow row = findRowByPk( pk );
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
   * p_ipoProductId, and にて指定の値から一意の{@@link IpoProductRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_ipoProductId 検索対象であるp_ipoProductIdフィールドの値
   * 
   * @@return 引数指定のp_ipoProductId, and の値と一致する{@@link IpoProductRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static IpoProductRow findRowByIpoProductId( long p_ipoProductId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IpoProductRow.TYPE,
            "ipo_product_id=?",
            null,
            new Object[] { new Long(p_ipoProductId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IpoProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IpoProductDao.findRowsByIpoProductId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByIpoProductId(long)}および{@@link #forRow(IpoProductRow)}を使用してください。 
   */
    public static IpoProductDao findDaoByIpoProductId( long p_ipoProductId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByIpoProductId( p_ipoProductId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
