head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.30.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushHistoryTopDao.java;


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
 * {@@link RichPushHistoryTopDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RichPushHistoryTopRow}インスタンスへ関連付けることができます。 
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
 * @@see RichPushHistoryTopPK 
 * @@see RichPushHistoryTopRow 
 */
public class RichPushHistoryTopDao extends DataAccessObject {


  /** 
   * この{@@link RichPushHistoryTopDao}に関連する型指定のRowオブジェクト 
   */
    private RichPushHistoryTopRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RichPushHistoryTopRow}と仮定される{@@link DataAccessObject}から新たに{@@link RichPushHistoryTopDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RichPushHistoryTopDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RichPushHistoryTopRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RichPushHistoryTopRow )
                return new RichPushHistoryTopDao( (RichPushHistoryTopRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RichPushHistoryTopRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RichPushHistoryTopRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RichPushHistoryTopRow}オブジェクト 
    */
    protected RichPushHistoryTopDao( RichPushHistoryTopRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RichPushHistoryTopRow}オブジェクトを取得します。
   */
    public RichPushHistoryTopRow getRichPushHistoryTopRow() {
        return row;
    }


  /** 
   * 指定の{@@link RichPushHistoryTopRow}オブジェクトから{@@link RichPushHistoryTopDao}オブジェクトを作成します。 
   * これは実際の{@@link RichPushHistoryTopRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RichPushHistoryTopDao}取得のために指定の{@@link RichPushHistoryTopRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RichPushHistoryTopDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RichPushHistoryTopDao forRow( RichPushHistoryTopRow row ) throws java.lang.IllegalArgumentException {
        return (RichPushHistoryTopDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RichPushHistoryTopRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RichPushHistoryTopRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RichPushHistoryTopPK}やデータベースレコードとして挿入される{@@link RichPushHistoryTopParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RichPushHistoryTopRow.TYPE );
    }


  /** 
   * {@@link RichPushHistoryTopRow}を一意に特定する{@@link RichPushHistoryTopPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RichPushHistoryTopRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RichPushHistoryTopParams}オブジェクトの主キーとして利用可能な{@@link RichPushHistoryTopPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RichPushHistoryTopPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RichPushHistoryTopPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RichPushHistoryTopRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RichPushHistoryTopRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RichPushHistoryTopRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryTopPK pk = new RichPushHistoryTopPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRichPushHistoryTopPKオブジェクトから{@@link RichPushHistoryTopRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRichPushHistoryTopPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RichPushHistoryTopRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RichPushHistoryTopRow findRowByPk( RichPushHistoryTopPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RichPushHistoryTopRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RichPushHistoryTopRow)}を使用してください。 
   */
    public static RichPushHistoryTopDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryTopPK pk = new RichPushHistoryTopPK( p_serlnum );
        RichPushHistoryTopRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RichPushHistoryTopPK)}および{@@link #forRow(RichPushHistoryTopRow)}を使用してください。 
   */
    public static RichPushHistoryTopDao findDaoByPk( RichPushHistoryTopPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushHistoryTopRow row = findRowByPk( pk );
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
