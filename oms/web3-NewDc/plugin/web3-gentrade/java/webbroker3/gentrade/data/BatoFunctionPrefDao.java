head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.35.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoFunctionPrefDao.java;


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
 * {@@link BatoFunctionPrefDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BatoFunctionPrefRow}インスタンスへ関連付けることができます。 
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
 * @@see BatoFunctionPrefPK 
 * @@see BatoFunctionPrefRow 
 */
public class BatoFunctionPrefDao extends DataAccessObject {


  /** 
   * この{@@link BatoFunctionPrefDao}に関連する型指定のRowオブジェクト 
   */
    private BatoFunctionPrefRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BatoFunctionPrefRow}と仮定される{@@link DataAccessObject}から新たに{@@link BatoFunctionPrefDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BatoFunctionPrefDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BatoFunctionPrefRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatoFunctionPrefRow )
                return new BatoFunctionPrefDao( (BatoFunctionPrefRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatoFunctionPrefRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatoFunctionPrefRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BatoFunctionPrefRow}オブジェクト 
    */
    protected BatoFunctionPrefDao( BatoFunctionPrefRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BatoFunctionPrefRow}オブジェクトを取得します。
   */
    public BatoFunctionPrefRow getBatoFunctionPrefRow() {
        return row;
    }


  /** 
   * 指定の{@@link BatoFunctionPrefRow}オブジェクトから{@@link BatoFunctionPrefDao}オブジェクトを作成します。 
   * これは実際の{@@link BatoFunctionPrefRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BatoFunctionPrefDao}取得のために指定の{@@link BatoFunctionPrefRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BatoFunctionPrefDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BatoFunctionPrefDao forRow( BatoFunctionPrefRow row ) throws java.lang.IllegalArgumentException {
        return (BatoFunctionPrefDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatoFunctionPrefRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BatoFunctionPrefRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BatoFunctionPrefPK}やデータベースレコードとして挿入される{@@link BatoFunctionPrefParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatoFunctionPrefRow.TYPE );
    }


  /** 
   * {@@link BatoFunctionPrefRow}を一意に特定する{@@link BatoFunctionPrefPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BatoFunctionPrefRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BatoFunctionPrefParams}オブジェクトの主キーとして利用可能な{@@link BatoFunctionPrefPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BatoFunctionPrefPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BatoFunctionPrefRow}オブジェクトを検索します。 
   * 
   * @@param p_functionDiv 検索対象であるp_functionDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatoFunctionPrefRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatoFunctionPrefRow findRowByPk( String p_functionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoFunctionPrefPK pk = new BatoFunctionPrefPK( p_functionDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBatoFunctionPrefPKオブジェクトから{@@link BatoFunctionPrefRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBatoFunctionPrefPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatoFunctionPrefRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatoFunctionPrefRow findRowByPk( BatoFunctionPrefPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatoFunctionPrefRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(BatoFunctionPrefRow)}を使用してください。 
   */
    public static BatoFunctionPrefDao findDaoByPk( String p_functionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoFunctionPrefPK pk = new BatoFunctionPrefPK( p_functionDiv );
        BatoFunctionPrefRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BatoFunctionPrefPK)}および{@@link #forRow(BatoFunctionPrefRow)}を使用してください。 
   */
    public static BatoFunctionPrefDao findDaoByPk( BatoFunctionPrefPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoFunctionPrefRow row = findRowByPk( pk );
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
   * p_functionDiv, and にて指定の値から一意の{@@link BatoFunctionPrefRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_functionDiv 検索対象であるp_functionDivフィールドの値
   * 
   * @@return 引数指定のp_functionDiv, and の値と一致する{@@link BatoFunctionPrefRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BatoFunctionPrefRow findRowByFunctionDiv( String p_functionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatoFunctionPrefRow.TYPE,
            "function_div=?",
            null,
            new Object[] { p_functionDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatoFunctionPrefRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatoFunctionPrefDao.findRowsByFunctionDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFunctionDiv(String)}および{@@link #forRow(BatoFunctionPrefRow)}を使用してください。 
   */
    public static BatoFunctionPrefDao findDaoByFunctionDiv( String p_functionDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFunctionDiv( p_functionDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
