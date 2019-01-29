head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.29.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushEquityChangeCancelDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rcp.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RichPushEquityChangeCancelDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RichPushEquityChangeCancelRow}インスタンスへ関連付けることができます。 
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
 * @@see RichPushEquityChangeCancelPK 
 * @@see RichPushEquityChangeCancelRow 
 */
public class RichPushEquityChangeCancelDao extends DataAccessObject {


  /** 
   * この{@@link RichPushEquityChangeCancelDao}に関連する型指定のRowオブジェクト 
   */
    private RichPushEquityChangeCancelRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RichPushEquityChangeCancelRow}と仮定される{@@link DataAccessObject}から新たに{@@link RichPushEquityChangeCancelDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RichPushEquityChangeCancelDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RichPushEquityChangeCancelRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RichPushEquityChangeCancelRow )
                return new RichPushEquityChangeCancelDao( (RichPushEquityChangeCancelRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RichPushEquityChangeCancelRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RichPushEquityChangeCancelRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RichPushEquityChangeCancelRow}オブジェクト 
    */
    protected RichPushEquityChangeCancelDao( RichPushEquityChangeCancelRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RichPushEquityChangeCancelRow}オブジェクトを取得します。
   */
    public RichPushEquityChangeCancelRow getRichPushEquityChangeCancelRow() {
        return row;
    }


  /** 
   * 指定の{@@link RichPushEquityChangeCancelRow}オブジェクトから{@@link RichPushEquityChangeCancelDao}オブジェクトを作成します。 
   * これは実際の{@@link RichPushEquityChangeCancelRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RichPushEquityChangeCancelDao}取得のために指定の{@@link RichPushEquityChangeCancelRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RichPushEquityChangeCancelDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RichPushEquityChangeCancelDao forRow( RichPushEquityChangeCancelRow row ) throws java.lang.IllegalArgumentException {
        return (RichPushEquityChangeCancelDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RichPushEquityChangeCancelRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RichPushEquityChangeCancelRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RichPushEquityChangeCancelPK}やデータベースレコードとして挿入される{@@link RichPushEquityChangeCancelParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RichPushEquityChangeCancelRow.TYPE );
    }


  /** 
   * {@@link RichPushEquityChangeCancelRow}を一意に特定する{@@link RichPushEquityChangeCancelPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RichPushEquityChangeCancelRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RichPushEquityChangeCancelParams}オブジェクトの主キーとして利用可能な{@@link RichPushEquityChangeCancelPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RichPushEquityChangeCancelPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RichPushEquityChangeCancelPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RichPushEquityChangeCancelRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RichPushEquityChangeCancelRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RichPushEquityChangeCancelRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityChangeCancelPK pk = new RichPushEquityChangeCancelPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRichPushEquityChangeCancelPKオブジェクトから{@@link RichPushEquityChangeCancelRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRichPushEquityChangeCancelPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RichPushEquityChangeCancelRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RichPushEquityChangeCancelRow findRowByPk( RichPushEquityChangeCancelPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RichPushEquityChangeCancelRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RichPushEquityChangeCancelRow)}を使用してください。 
   */
    public static RichPushEquityChangeCancelDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityChangeCancelPK pk = new RichPushEquityChangeCancelPK( p_serlnum );
        RichPushEquityChangeCancelRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RichPushEquityChangeCancelPK)}および{@@link #forRow(RichPushEquityChangeCancelRow)}を使用してください。 
   */
    public static RichPushEquityChangeCancelDao findDaoByPk( RichPushEquityChangeCancelPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityChangeCancelRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
