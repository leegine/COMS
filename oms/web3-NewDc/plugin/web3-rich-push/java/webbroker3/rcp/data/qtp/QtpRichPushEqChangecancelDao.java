head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.27.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushEqChangecancelDao.java;


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
 * {@@link QtpRichPushEqChangecancelDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QtpRichPushEqChangecancelRow}インスタンスへ関連付けることができます。 
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
 * @@see QtpRichPushEqChangecancelPK 
 * @@see QtpRichPushEqChangecancelRow 
 */
public class QtpRichPushEqChangecancelDao extends DataAccessObject {


  /** 
   * この{@@link QtpRichPushEqChangecancelDao}に関連する型指定のRowオブジェクト 
   */
    private QtpRichPushEqChangecancelRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QtpRichPushEqChangecancelRow}と仮定される{@@link DataAccessObject}から新たに{@@link QtpRichPushEqChangecancelDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QtpRichPushEqChangecancelDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QtpRichPushEqChangecancelRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QtpRichPushEqChangecancelRow )
                return new QtpRichPushEqChangecancelDao( (QtpRichPushEqChangecancelRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QtpRichPushEqChangecancelRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QtpRichPushEqChangecancelRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QtpRichPushEqChangecancelRow}オブジェクト 
    */
    protected QtpRichPushEqChangecancelDao( QtpRichPushEqChangecancelRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QtpRichPushEqChangecancelRow}オブジェクトを取得します。
   */
    public QtpRichPushEqChangecancelRow getQtpRichPushEqChangecancelRow() {
        return row;
    }


  /** 
   * 指定の{@@link QtpRichPushEqChangecancelRow}オブジェクトから{@@link QtpRichPushEqChangecancelDao}オブジェクトを作成します。 
   * これは実際の{@@link QtpRichPushEqChangecancelRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QtpRichPushEqChangecancelDao}取得のために指定の{@@link QtpRichPushEqChangecancelRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QtpRichPushEqChangecancelDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QtpRichPushEqChangecancelDao forRow( QtpRichPushEqChangecancelRow row ) throws java.lang.IllegalArgumentException {
        return (QtpRichPushEqChangecancelDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QtpRichPushEqChangecancelRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QtpRichPushEqChangecancelRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QtpRichPushEqChangecancelPK}やデータベースレコードとして挿入される{@@link QtpRichPushEqChangecancelParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QtpRichPushEqChangecancelRow.TYPE );
    }


  /** 
   * {@@link QtpRichPushEqChangecancelRow}を一意に特定する{@@link QtpRichPushEqChangecancelPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QtpRichPushEqChangecancelRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QtpRichPushEqChangecancelParams}オブジェクトの主キーとして利用可能な{@@link QtpRichPushEqChangecancelPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QtpRichPushEqChangecancelPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new QtpRichPushEqChangecancelPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QtpRichPushEqChangecancelRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushEqChangecancelRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushEqChangecancelRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushEqChangecancelPK pk = new QtpRichPushEqChangecancelPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQtpRichPushEqChangecancelPKオブジェクトから{@@link QtpRichPushEqChangecancelRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQtpRichPushEqChangecancelPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushEqChangecancelRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushEqChangecancelRow findRowByPk( QtpRichPushEqChangecancelPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QtpRichPushEqChangecancelRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(QtpRichPushEqChangecancelRow)}を使用してください。 
   */
    public static QtpRichPushEqChangecancelDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushEqChangecancelPK pk = new QtpRichPushEqChangecancelPK( p_serlnum );
        QtpRichPushEqChangecancelRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QtpRichPushEqChangecancelPK)}および{@@link #forRow(QtpRichPushEqChangecancelRow)}を使用してください。 
   */
    public static QtpRichPushEqChangecancelDao findDaoByPk( QtpRichPushEqChangecancelPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushEqChangecancelRow row = findRowByPk( pk );
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
