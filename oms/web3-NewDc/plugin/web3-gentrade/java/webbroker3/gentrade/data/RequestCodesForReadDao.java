head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	RequestCodesForReadDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link RequestCodesForReadDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link RequestCodesForReadRow}インスタンスへ関連付けることができます。 
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
 * @@see RequestCodesForReadPK 
 * @@see RequestCodesForReadRow 
 */
public class RequestCodesForReadDao extends DataAccessObject {


  /** 
   * この{@@link RequestCodesForReadDao}に関連する型指定のRowオブジェクト 
   */
    private RequestCodesForReadRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link RequestCodesForReadRow}と仮定される{@@link DataAccessObject}から新たに{@@link RequestCodesForReadDao}を返します。 
         * @@return 指定のRowに結びつく{@@link RequestCodesForReadDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link RequestCodesForReadRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RequestCodesForReadRow )
                return new RequestCodesForReadDao( (RequestCodesForReadRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RequestCodesForReadRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RequestCodesForReadRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link RequestCodesForReadRow}オブジェクト 
    */
    protected RequestCodesForReadDao( RequestCodesForReadRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link RequestCodesForReadRow}オブジェクトを取得します。
   */
    public RequestCodesForReadRow getRequestCodesForReadRow() {
        return row;
    }


  /** 
   * 指定の{@@link RequestCodesForReadRow}オブジェクトから{@@link RequestCodesForReadDao}オブジェクトを作成します。 
   * これは実際の{@@link RequestCodesForReadRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link RequestCodesForReadDao}取得のために指定の{@@link RequestCodesForReadRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link RequestCodesForReadDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static RequestCodesForReadDao forRow( RequestCodesForReadRow row ) throws java.lang.IllegalArgumentException {
        return (RequestCodesForReadDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link RequestCodesForReadRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link RequestCodesForReadRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link RequestCodesForReadPK}やデータベースレコードとして挿入される{@@link RequestCodesForReadParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( RequestCodesForReadRow.TYPE );
    }


  /** 
   * {@@link RequestCodesForReadRow}を一意に特定する{@@link RequestCodesForReadPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link RequestCodesForReadRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link RequestCodesForReadParams}オブジェクトの主キーとして利用可能な{@@link RequestCodesForReadPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static RequestCodesForReadPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link RequestCodesForReadRow}オブジェクトを検索します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_ptype 検索対象であるp_ptypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RequestCodesForReadRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RequestCodesForReadRow findRowByPk( String p_requestCode, String p_ptype ) throws DataFindException, DataQueryException, DataNetworkException {
        RequestCodesForReadPK pk = new RequestCodesForReadPK( p_requestCode, p_ptype );
        return findRowByPk( pk );
    }


  /** 
   * 指定のRequestCodesForReadPKオブジェクトから{@@link RequestCodesForReadRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するRequestCodesForReadPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link RequestCodesForReadRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static RequestCodesForReadRow findRowByPk( RequestCodesForReadPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (RequestCodesForReadRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(RequestCodesForReadRow)}を使用してください。 
   */
    public static RequestCodesForReadDao findDaoByPk( String p_requestCode, String p_ptype ) throws DataFindException, DataQueryException, DataNetworkException {
        RequestCodesForReadPK pk = new RequestCodesForReadPK( p_requestCode, p_ptype );
        RequestCodesForReadRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(RequestCodesForReadPK)}および{@@link #forRow(RequestCodesForReadRow)}を使用してください。 
   */
    public static RequestCodesForReadDao findDaoByPk( RequestCodesForReadPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        RequestCodesForReadRow row = findRowByPk( pk );
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
   * p_requestCode, p_ptype, and にて指定の値から一意の{@@link RequestCodesForReadRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_ptype 検索対象であるp_ptypeフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_ptype, and の値と一致する{@@link RequestCodesForReadRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static RequestCodesForReadRow findRowByRequestCodePtype( String p_requestCode, String p_ptype ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            RequestCodesForReadRow.TYPE,
            "request_code=? and ptype=?",
            null,
            new Object[] { p_requestCode, p_ptype } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (RequestCodesForReadRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query RequestCodesForReadDao.findRowsByRequestCodePtype(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByRequestCodePtype(String, String)}および{@@link #forRow(RequestCodesForReadRow)}を使用してください。 
   */
    public static RequestCodesForReadDao findDaoByRequestCodePtype( String p_requestCode, String p_ptype ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByRequestCodePtype( p_requestCode, p_ptype ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
