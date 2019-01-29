head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushHistoryTopDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data.qtp;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rcp.data.qtp.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link QtpRichPushHistoryTopDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QtpRichPushHistoryTopRow}インスタンスへ関連付けることができます。 
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
 * @@see QtpRichPushHistoryTopPK 
 * @@see QtpRichPushHistoryTopRow 
 */
public class QtpRichPushHistoryTopDao extends DataAccessObject {


  /** 
   * この{@@link QtpRichPushHistoryTopDao}に関連する型指定のRowオブジェクト 
   */
    private QtpRichPushHistoryTopRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QtpRichPushHistoryTopRow}と仮定される{@@link DataAccessObject}から新たに{@@link QtpRichPushHistoryTopDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QtpRichPushHistoryTopDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QtpRichPushHistoryTopRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QtpRichPushHistoryTopRow )
                return new QtpRichPushHistoryTopDao( (QtpRichPushHistoryTopRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QtpRichPushHistoryTopRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QtpRichPushHistoryTopRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QtpRichPushHistoryTopRow}オブジェクト 
    */
    protected QtpRichPushHistoryTopDao( QtpRichPushHistoryTopRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QtpRichPushHistoryTopRow}オブジェクトを取得します。
   */
    public QtpRichPushHistoryTopRow getQtpRichPushHistoryTopRow() {
        return row;
    }


  /** 
   * 指定の{@@link QtpRichPushHistoryTopRow}オブジェクトから{@@link QtpRichPushHistoryTopDao}オブジェクトを作成します。 
   * これは実際の{@@link QtpRichPushHistoryTopRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QtpRichPushHistoryTopDao}取得のために指定の{@@link QtpRichPushHistoryTopRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QtpRichPushHistoryTopDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QtpRichPushHistoryTopDao forRow( QtpRichPushHistoryTopRow row ) throws java.lang.IllegalArgumentException {
        return (QtpRichPushHistoryTopDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QtpRichPushHistoryTopRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QtpRichPushHistoryTopRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QtpRichPushHistoryTopPK}やデータベースレコードとして挿入される{@@link QtpRichPushHistoryTopParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QtpRichPushHistoryTopRow.TYPE );
    }


  /** 
   * {@@link QtpRichPushHistoryTopRow}を一意に特定する{@@link QtpRichPushHistoryTopPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QtpRichPushHistoryTopRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QtpRichPushHistoryTopParams}オブジェクトの主キーとして利用可能な{@@link QtpRichPushHistoryTopPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QtpRichPushHistoryTopPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new QtpRichPushHistoryTopPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QtpRichPushHistoryTopRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushHistoryTopRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushHistoryTopRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryTopPK pk = new QtpRichPushHistoryTopPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQtpRichPushHistoryTopPKオブジェクトから{@@link QtpRichPushHistoryTopRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQtpRichPushHistoryTopPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushHistoryTopRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushHistoryTopRow findRowByPk( QtpRichPushHistoryTopPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QtpRichPushHistoryTopRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(QtpRichPushHistoryTopRow)}を使用してください。 
   */
    public static QtpRichPushHistoryTopDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryTopPK pk = new QtpRichPushHistoryTopPK( p_serlnum );
        QtpRichPushHistoryTopRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QtpRichPushHistoryTopPK)}および{@@link #forRow(QtpRichPushHistoryTopRow)}を使用してください。 
   */
    public static QtpRichPushHistoryTopDao findDaoByPk( QtpRichPushHistoryTopPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushHistoryTopRow row = findRowByPk( pk );
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
