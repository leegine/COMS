head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfSubAssetDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MfSubAssetDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MfSubAssetRow}インスタンスへ関連付けることができます。 
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
 * @@see MfSubAssetPK 
 * @@see MfSubAssetRow 
 */
public class MfSubAssetDao extends DataAccessObject {


  /** 
   * この{@@link MfSubAssetDao}に関連する型指定のRowオブジェクト 
   */
    private MfSubAssetRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MfSubAssetRow}と仮定される{@@link DataAccessObject}から新たに{@@link MfSubAssetDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MfSubAssetDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MfSubAssetRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfSubAssetRow )
                return new MfSubAssetDao( (MfSubAssetRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfSubAssetRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfSubAssetRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MfSubAssetRow}オブジェクト 
    */
    protected MfSubAssetDao( MfSubAssetRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MfSubAssetRow}オブジェクトを取得します。
   */
    public MfSubAssetRow getMfSubAssetRow() {
        return row;
    }


  /** 
   * 指定の{@@link MfSubAssetRow}オブジェクトから{@@link MfSubAssetDao}オブジェクトを作成します。 
   * これは実際の{@@link MfSubAssetRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MfSubAssetDao}取得のために指定の{@@link MfSubAssetRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MfSubAssetDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MfSubAssetDao forRow( MfSubAssetRow row ) throws java.lang.IllegalArgumentException {
        return (MfSubAssetDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfSubAssetRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MfSubAssetRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MfSubAssetPK}やデータベースレコードとして挿入される{@@link MfSubAssetParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfSubAssetRow.TYPE );
    }


  /** 
   * {@@link MfSubAssetRow}を一意に特定する{@@link MfSubAssetPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MfSubAssetRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MfSubAssetParams}オブジェクトの主キーとして利用可能な{@@link MfSubAssetPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MfSubAssetPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new MfSubAssetPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MfSubAssetRow}オブジェクトを検索します。 
   * 
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfSubAssetRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfSubAssetRow findRowByPk( long p_assetId ) throws DataFindException, DataQueryException, DataNetworkException {
        MfSubAssetPK pk = new MfSubAssetPK( p_assetId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMfSubAssetPKオブジェクトから{@@link MfSubAssetRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMfSubAssetPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfSubAssetRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfSubAssetRow findRowByPk( MfSubAssetPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfSubAssetRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(MfSubAssetRow)}を使用してください。 
   */
    public static MfSubAssetDao findDaoByPk( long p_assetId ) throws DataFindException, DataQueryException, DataNetworkException {
        MfSubAssetPK pk = new MfSubAssetPK( p_assetId );
        MfSubAssetRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MfSubAssetPK)}および{@@link #forRow(MfSubAssetRow)}を使用してください。 
   */
    public static MfSubAssetDao findDaoByPk( MfSubAssetPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfSubAssetRow row = findRowByPk( pk );
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
   * p_assetId, and にて指定の値から一意の{@@link MfSubAssetRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_assetId 検索対象であるp_assetIdフィールドの値
   * 
   * @@return 引数指定のp_assetId, and の値と一致する{@@link MfSubAssetRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MfSubAssetRow findRowByAssetId( long p_assetId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfSubAssetRow.TYPE,
            "asset_id=?",
            null,
            new Object[] { new Long(p_assetId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfSubAssetRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfSubAssetDao.findRowsByAssetId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAssetId(long)}および{@@link #forRow(MfSubAssetRow)}を使用してください。 
   */
    public static MfSubAssetDao findDaoByAssetId( long p_assetId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAssetId( p_assetId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
