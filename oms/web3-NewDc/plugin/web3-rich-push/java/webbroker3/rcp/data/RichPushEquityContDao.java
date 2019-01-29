head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	RichPushEquityContDao.java;


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
 * {@@link RichPushEquityContDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RichPushEquityContRow}インスタンスへ関連付けることができます。 
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
 * @@see RichPushEquityContPK 
 * @@see RichPushEquityContRow 
 */
public class RichPushEquityContDao extends DataAccessObject {


  /** 
   * この{@@link RichPushEquityContDao}に関連する型指定のRowオブジェクト 
   */
    private RichPushEquityContRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RichPushEquityContRow}と仮定される{@@link DataAccessObject}から新たに{@@link RichPushEquityContDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RichPushEquityContDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RichPushEquityContRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RichPushEquityContRow )
                return new RichPushEquityContDao( (RichPushEquityContRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RichPushEquityContRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RichPushEquityContRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RichPushEquityContRow}オブジェクト 
    */
    protected RichPushEquityContDao( RichPushEquityContRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RichPushEquityContRow}オブジェクトを取得します。
   */
    public RichPushEquityContRow getRichPushEquityContRow() {
        return row;
    }


  /** 
   * 指定の{@@link RichPushEquityContRow}オブジェクトから{@@link RichPushEquityContDao}オブジェクトを作成します。 
   * これは実際の{@@link RichPushEquityContRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RichPushEquityContDao}取得のために指定の{@@link RichPushEquityContRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RichPushEquityContDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RichPushEquityContDao forRow( RichPushEquityContRow row ) throws java.lang.IllegalArgumentException {
        return (RichPushEquityContDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RichPushEquityContRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RichPushEquityContRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RichPushEquityContPK}やデータベースレコードとして挿入される{@@link RichPushEquityContParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RichPushEquityContRow.TYPE );
    }


  /** 
   * {@@link RichPushEquityContRow}を一意に特定する{@@link RichPushEquityContPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RichPushEquityContRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RichPushEquityContParams}オブジェクトの主キーとして利用可能な{@@link RichPushEquityContPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RichPushEquityContPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RichPushEquityContPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RichPushEquityContRow}オブジェクトを検索します。 
   * 
   * @@param p_serlnum 検索対象であるp_serlnumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RichPushEquityContRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RichPushEquityContRow findRowByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityContPK pk = new RichPushEquityContPK( p_serlnum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRichPushEquityContPKオブジェクトから{@@link RichPushEquityContRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRichPushEquityContPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RichPushEquityContRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RichPushEquityContRow findRowByPk( RichPushEquityContPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RichPushEquityContRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RichPushEquityContRow)}を使用してください。 
   */
    public static RichPushEquityContDao findDaoByPk( long p_serlnum ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityContPK pk = new RichPushEquityContPK( p_serlnum );
        RichPushEquityContRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RichPushEquityContPK)}および{@@link #forRow(RichPushEquityContRow)}を使用してください。 
   */
    public static RichPushEquityContDao findDaoByPk( RichPushEquityContPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RichPushEquityContRow row = findRowByPk( pk );
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
