head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityKeyBasedMapDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.system.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link AffinityKeyBasedMapDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AffinityKeyBasedMapRow}インスタンスへ関連付けることができます。 
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
 * @@see AffinityKeyBasedMapPK 
 * @@see AffinityKeyBasedMapRow 
 */
public class AffinityKeyBasedMapDao extends DataAccessObject {


  /** 
   * この{@@link AffinityKeyBasedMapDao}に関連する型指定のRowオブジェクト 
   */
    private AffinityKeyBasedMapRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AffinityKeyBasedMapRow}と仮定される{@@link DataAccessObject}から新たに{@@link AffinityKeyBasedMapDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AffinityKeyBasedMapDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AffinityKeyBasedMapRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AffinityKeyBasedMapRow )
                return new AffinityKeyBasedMapDao( (AffinityKeyBasedMapRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AffinityKeyBasedMapRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AffinityKeyBasedMapRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AffinityKeyBasedMapRow}オブジェクト 
    */
    protected AffinityKeyBasedMapDao( AffinityKeyBasedMapRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AffinityKeyBasedMapRow}オブジェクトを取得します。
   */
    public AffinityKeyBasedMapRow getAffinityKeyBasedMapRow() {
        return row;
    }


  /** 
   * 指定の{@@link AffinityKeyBasedMapRow}オブジェクトから{@@link AffinityKeyBasedMapDao}オブジェクトを作成します。 
   * これは実際の{@@link AffinityKeyBasedMapRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AffinityKeyBasedMapDao}取得のために指定の{@@link AffinityKeyBasedMapRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AffinityKeyBasedMapDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AffinityKeyBasedMapDao forRow( AffinityKeyBasedMapRow row ) throws java.lang.IllegalArgumentException {
        return (AffinityKeyBasedMapDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AffinityKeyBasedMapRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AffinityKeyBasedMapRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AffinityKeyBasedMapPK}やデータベースレコードとして挿入される{@@link AffinityKeyBasedMapParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AffinityKeyBasedMapRow.TYPE );
    }


  /** 
   * {@@link AffinityKeyBasedMapRow}を一意に特定する{@@link AffinityKeyBasedMapPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AffinityKeyBasedMapRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AffinityKeyBasedMapParams}オブジェクトの主キーとして利用可能な{@@link AffinityKeyBasedMapPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AffinityKeyBasedMapPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AffinityKeyBasedMapRow}オブジェクトを検索します。 
   * 
   * @@param p_appId 検索対象であるp_appIdフィールドの値
   * @@param p_dbId 検索対象であるp_dbIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AffinityKeyBasedMapRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AffinityKeyBasedMapRow findRowByPk( String p_appId, String p_dbId ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityKeyBasedMapPK pk = new AffinityKeyBasedMapPK( p_appId, p_dbId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAffinityKeyBasedMapPKオブジェクトから{@@link AffinityKeyBasedMapRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAffinityKeyBasedMapPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AffinityKeyBasedMapRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AffinityKeyBasedMapRow findRowByPk( AffinityKeyBasedMapPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AffinityKeyBasedMapRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(AffinityKeyBasedMapRow)}を使用してください。 
   */
    public static AffinityKeyBasedMapDao findDaoByPk( String p_appId, String p_dbId ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityKeyBasedMapPK pk = new AffinityKeyBasedMapPK( p_appId, p_dbId );
        AffinityKeyBasedMapRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AffinityKeyBasedMapPK)}および{@@link #forRow(AffinityKeyBasedMapRow)}を使用してください。 
   */
    public static AffinityKeyBasedMapDao findDaoByPk( AffinityKeyBasedMapPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityKeyBasedMapRow row = findRowByPk( pk );
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
