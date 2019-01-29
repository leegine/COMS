head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsAccountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.rlsgateway.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link RlsAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RlsAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see RlsAccountPK 
 * @@see RlsAccountRow 
 */
public class RlsAccountDao extends DataAccessObject {


  /** 
   * この{@@link RlsAccountDao}に関連する型指定のRowオブジェクト 
   */
    private RlsAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RlsAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link RlsAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RlsAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RlsAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RlsAccountRow )
                return new RlsAccountDao( (RlsAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RlsAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RlsAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RlsAccountRow}オブジェクト 
    */
    protected RlsAccountDao( RlsAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RlsAccountRow}オブジェクトを取得します。
   */
    public RlsAccountRow getRlsAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link RlsAccountRow}オブジェクトから{@@link RlsAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link RlsAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RlsAccountDao}取得のために指定の{@@link RlsAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RlsAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RlsAccountDao forRow( RlsAccountRow row ) throws java.lang.IllegalArgumentException {
        return (RlsAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RlsAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RlsAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RlsAccountPK}やデータベースレコードとして挿入される{@@link RlsAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RlsAccountRow.TYPE );
    }


  /** 
   * {@@link RlsAccountRow}を一意に特定する{@@link RlsAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RlsAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RlsAccountParams}オブジェクトの主キーとして利用可能な{@@link RlsAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RlsAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new RlsAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RlsAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_id 検索対象であるp_idフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsAccountRow findRowByPk( long p_id ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsAccountPK pk = new RlsAccountPK( p_id );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRlsAccountPKオブジェクトから{@@link RlsAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRlsAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RlsAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RlsAccountRow findRowByPk( RlsAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RlsAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(RlsAccountRow)}を使用してください。 
   */
    public static RlsAccountDao findDaoByPk( long p_id ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsAccountPK pk = new RlsAccountPK( p_id );
        RlsAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RlsAccountPK)}および{@@link #forRow(RlsAccountRow)}を使用してください。 
   */
    public static RlsAccountDao findDaoByPk( RlsAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RlsAccountRow row = findRowByPk( pk );
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
   * p_id, and にて指定の値から一意の{@@link RlsAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_id 検索対象であるp_idフィールドの値
   * 
   * @@return 引数指定のp_id, and の値と一致する{@@link RlsAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RlsAccountRow findRowById( long p_id ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RlsAccountRow.TYPE,
            "id=?",
            null,
            new Object[] { new Long(p_id) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RlsAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RlsAccountDao.findRowsById(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowById(long)}および{@@link #forRow(RlsAccountRow)}を使用してください。 
   */
    public static RlsAccountDao findDaoById( long p_id ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowById( p_id ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
