head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.25.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	QtpRichPushIfoLapseDao.java;


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
 * {@@link QtpRichPushIfoLapseDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QtpRichPushIfoLapseRow}インスタンスへ関連付けることができます。 
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
 * @@see QtpRichPushIfoLapsePK 
 * @@see QtpRichPushIfoLapseRow 
 */
public class QtpRichPushIfoLapseDao extends DataAccessObject {


  /** 
   * この{@@link QtpRichPushIfoLapseDao}に関連する型指定のRowオブジェクト 
   */
    private QtpRichPushIfoLapseRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QtpRichPushIfoLapseRow}と仮定される{@@link DataAccessObject}から新たに{@@link QtpRichPushIfoLapseDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QtpRichPushIfoLapseDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QtpRichPushIfoLapseRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QtpRichPushIfoLapseRow )
                return new QtpRichPushIfoLapseDao( (QtpRichPushIfoLapseRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QtpRichPushIfoLapseRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QtpRichPushIfoLapseRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QtpRichPushIfoLapseRow}オブジェクト 
    */
    protected QtpRichPushIfoLapseDao( QtpRichPushIfoLapseRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QtpRichPushIfoLapseRow}オブジェクトを取得します。
   */
    public QtpRichPushIfoLapseRow getQtpRichPushIfoLapseRow() {
        return row;
    }


  /** 
   * 指定の{@@link QtpRichPushIfoLapseRow}オブジェクトから{@@link QtpRichPushIfoLapseDao}オブジェクトを作成します。 
   * これは実際の{@@link QtpRichPushIfoLapseRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QtpRichPushIfoLapseDao}取得のために指定の{@@link QtpRichPushIfoLapseRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QtpRichPushIfoLapseDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QtpRichPushIfoLapseDao forRow( QtpRichPushIfoLapseRow row ) throws java.lang.IllegalArgumentException {
        return (QtpRichPushIfoLapseDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QtpRichPushIfoLapseRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QtpRichPushIfoLapseRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QtpRichPushIfoLapsePK}やデータベースレコードとして挿入される{@@link QtpRichPushIfoLapseParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QtpRichPushIfoLapseRow.TYPE );
    }


  /** 
   * {@@link QtpRichPushIfoLapseRow}を一意に特定する{@@link QtpRichPushIfoLapsePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QtpRichPushIfoLapseRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QtpRichPushIfoLapseParams}オブジェクトの主キーとして利用可能な{@@link QtpRichPushIfoLapsePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QtpRichPushIfoLapsePK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new QtpRichPushIfoLapsePK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QtpRichPushIfoLapseRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushIfoLapseRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushIfoLapseRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoLapsePK pk = new QtpRichPushIfoLapsePK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQtpRichPushIfoLapsePKオブジェクトから{@@link QtpRichPushIfoLapseRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQtpRichPushIfoLapsePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QtpRichPushIfoLapseRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QtpRichPushIfoLapseRow findRowByPk( QtpRichPushIfoLapsePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QtpRichPushIfoLapseRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(QtpRichPushIfoLapseRow)}を使用してください。 
   */
    public static QtpRichPushIfoLapseDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoLapsePK pk = new QtpRichPushIfoLapsePK( p_serlnum );
        QtpRichPushIfoLapseRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QtpRichPushIfoLapsePK)}および{@@link #forRow(QtpRichPushIfoLapseRow)}を使用してください。 
   */
    public static QtpRichPushIfoLapseDao findDaoByPk( QtpRichPushIfoLapsePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QtpRichPushIfoLapseRow row = findRowByPk( pk );
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
