head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.26.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushIfoChangecancelDao.java;


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
 * {@@link QtpRichPushIfoChangecancelDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QtpRichPushIfoChangecancelRow}インスタンスへ関連付けることができます。 
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
 * @@see QtpRichPushIfoChangecancelPK 
 * @@see QtpRichPushIfoChangecancelRow 
 */
public class QtpRichPushIfoChangecancelDao extends DataAccessObject {


  /** 
   * この{@@link QtpRichPushIfoChangecancelDao}に関連する型指定のRowオブジェクト 
   */
    private QtpRichPushIfoChangecancelRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QtpRichPushIfoChangecancelRow}と仮定される{@@link DataAccessObject}から新たに{@@link QtpRichPushIfoChangecancelDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QtpRichPushIfoChangecancelDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QtpRichPushIfoChangecancelRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QtpRichPushIfoChangecancelRow )
                return new QtpRichPushIfoChangecancelDao( (QtpRichPushIfoChangecancelRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QtpRichPushIfoChangecancelRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QtpRichPushIfoChangecancelRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QtpRichPushIfoChangecancelRow}オブジェクト 
    */
    protected QtpRichPushIfoChangecancelDao( QtpRichPushIfoChangecancelRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QtpRichPushIfoChangecancelRow}オブジェクトを取得します。
   */
    public QtpRichPushIfoChangecancelRow getQtpRichPushIfoChangecancelRow() {
        return row;
    }


  /** 
   * 指定の{@@link QtpRichPushIfoChangecancelRow}オブジェクトから{@@link QtpRichPushIfoChangecancelDao}オブジェクトを作成します。 
   * これは実際の{@@link QtpRichPushIfoChangecancelRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QtpRichPushIfoChangecancelDao}取得のために指定の{@@link QtpRichPushIfoChangecancelRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QtpRichPushIfoChangecancelDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QtpRichPushIfoChangecancelDao forRow( QtpRichPushIfoChangecancelRow row ) throws java.lang.IllegalArgumentException {
        return (QtpRichPushIfoChangecancelDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QtpRichPushIfoChangecancelRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QtpRichPushIfoChangecancelRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QtpRichPushIfoChangecancelPK}やデータベースレコードとして挿入される{@@link QtpRichPushIfoChangecancelParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QtpRichPushIfoChangecancelRow.TYPE );
    }


  /** 
   * {@@link QtpRichPushIfoChangecancelRow}を一意に特定する{@@link QtpRichPushIfoChangecancelPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QtpRichPushIfoChangecancelRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QtpRichPushIfoChangecancelParams}オブジェクトの主キーとして利用可能な{@@link QtpRichPushIfoChangecancelPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QtpRichPushIfoChangecancelPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new QtpRichPushIfoChangecancelPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QtpRichPushIfoChangecancelRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushIfoChangecancelRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushIfoChangecancelRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoChangecancelPK pk = new QtpRichPushIfoChangecancelPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQtpRichPushIfoChangecancelPKオブジェクトから{@@link QtpRichPushIfoChangecancelRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQtpRichPushIfoChangecancelPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushIfoChangecancelRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushIfoChangecancelRow findRowByPk( QtpRichPushIfoChangecancelPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QtpRichPushIfoChangecancelRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(QtpRichPushIfoChangecancelRow)}を使用してください。 
   */
    public static QtpRichPushIfoChangecancelDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoChangecancelPK pk = new QtpRichPushIfoChangecancelPK( p_serlnum );
        QtpRichPushIfoChangecancelRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QtpRichPushIfoChangecancelPK)}および{@@link #forRow(QtpRichPushIfoChangecancelRow)}を使用してください。 
   */
    public static QtpRichPushIfoChangecancelDao findDaoByPk( QtpRichPushIfoChangecancelPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoChangecancelRow row = findRowByPk( pk );
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
