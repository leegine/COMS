head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.37.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	FxTransferMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link FxTransferMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FxTransferMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see FxTransferMasterPK 
 * @@see FxTransferMasterRow 
 */
public class FxTransferMasterDao extends DataAccessObject {


  /** 
   * この{@@link FxTransferMasterDao}に関連する型指定のRowオブジェクト 
   */
    private FxTransferMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FxTransferMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link FxTransferMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FxTransferMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FxTransferMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FxTransferMasterRow )
                return new FxTransferMasterDao( (FxTransferMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FxTransferMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FxTransferMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FxTransferMasterRow}オブジェクト 
    */
    protected FxTransferMasterDao( FxTransferMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FxTransferMasterRow}オブジェクトを取得します。
   */
    public FxTransferMasterRow getFxTransferMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link FxTransferMasterRow}オブジェクトから{@@link FxTransferMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link FxTransferMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FxTransferMasterDao}取得のために指定の{@@link FxTransferMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FxTransferMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FxTransferMasterDao forRow( FxTransferMasterRow row ) throws java.lang.IllegalArgumentException {
        return (FxTransferMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FxTransferMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FxTransferMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FxTransferMasterPK}やデータベースレコードとして挿入される{@@link FxTransferMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FxTransferMasterRow.TYPE );
    }


  /** 
   * {@@link FxTransferMasterRow}を一意に特定する{@@link FxTransferMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FxTransferMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FxTransferMasterParams}オブジェクトの主キーとして利用可能な{@@link FxTransferMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FxTransferMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FxTransferMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_fxSystemId 検索対象であるp_fxSystemIdフィールドの値
   * @@param p_transferDiv 検索対象であるp_transferDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FxTransferMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FxTransferMasterRow findRowByPk( long p_fxSystemId, String p_transferDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        FxTransferMasterPK pk = new FxTransferMasterPK( p_fxSystemId, p_transferDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFxTransferMasterPKオブジェクトから{@@link FxTransferMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFxTransferMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FxTransferMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FxTransferMasterRow findRowByPk( FxTransferMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FxTransferMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String)}および{@@link #forRow(FxTransferMasterRow)}を使用してください。 
   */
    public static FxTransferMasterDao findDaoByPk( long p_fxSystemId, String p_transferDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        FxTransferMasterPK pk = new FxTransferMasterPK( p_fxSystemId, p_transferDiv );
        FxTransferMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FxTransferMasterPK)}および{@@link #forRow(FxTransferMasterRow)}を使用してください。 
   */
    public static FxTransferMasterDao findDaoByPk( FxTransferMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FxTransferMasterRow row = findRowByPk( pk );
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
   * p_fxSystemId, p_transferDiv, and にて指定の値から一意の{@@link FxTransferMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_fxSystemId 検索対象であるp_fxSystemIdフィールドの値
   * @@param p_transferDiv 検索対象であるp_transferDivフィールドの値
   * 
   * @@return 引数指定のp_fxSystemId, p_transferDiv, and の値と一致する{@@link FxTransferMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FxTransferMasterRow findRowByFxSystemIdTransferDiv( long p_fxSystemId, String p_transferDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxTransferMasterRow.TYPE,
            "fx_system_id=? and transfer_div=?",
            null,
            new Object[] { new Long(p_fxSystemId), p_transferDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxTransferMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxTransferMasterDao.findRowsByFxSystemIdTransferDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFxSystemIdTransferDiv(long, String)}および{@@link #forRow(FxTransferMasterRow)}を使用してください。 
   */
    public static FxTransferMasterDao findDaoByFxSystemIdTransferDiv( long p_fxSystemId, String p_transferDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFxSystemIdTransferDiv( p_fxSystemId, p_transferDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
