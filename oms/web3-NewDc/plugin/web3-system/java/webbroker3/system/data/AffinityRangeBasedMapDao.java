head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.22.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityRangeBasedMapDao.java;


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
 * {@@link AffinityRangeBasedMapDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AffinityRangeBasedMapRow}インスタンスへ関連付けることができます。 
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
 * @@see AffinityRangeBasedMapPK 
 * @@see AffinityRangeBasedMapRow 
 */
public class AffinityRangeBasedMapDao extends DataAccessObject {


  /** 
   * この{@@link AffinityRangeBasedMapDao}に関連する型指定のRowオブジェクト 
   */
    private AffinityRangeBasedMapRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AffinityRangeBasedMapRow}と仮定される{@@link DataAccessObject}から新たに{@@link AffinityRangeBasedMapDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AffinityRangeBasedMapDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AffinityRangeBasedMapRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AffinityRangeBasedMapRow )
                return new AffinityRangeBasedMapDao( (AffinityRangeBasedMapRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AffinityRangeBasedMapRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AffinityRangeBasedMapRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AffinityRangeBasedMapRow}オブジェクト 
    */
    protected AffinityRangeBasedMapDao( AffinityRangeBasedMapRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AffinityRangeBasedMapRow}オブジェクトを取得します。
   */
    public AffinityRangeBasedMapRow getAffinityRangeBasedMapRow() {
        return row;
    }


  /** 
   * 指定の{@@link AffinityRangeBasedMapRow}オブジェクトから{@@link AffinityRangeBasedMapDao}オブジェクトを作成します。 
   * これは実際の{@@link AffinityRangeBasedMapRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AffinityRangeBasedMapDao}取得のために指定の{@@link AffinityRangeBasedMapRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AffinityRangeBasedMapDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AffinityRangeBasedMapDao forRow( AffinityRangeBasedMapRow row ) throws java.lang.IllegalArgumentException {
        return (AffinityRangeBasedMapDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AffinityRangeBasedMapRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AffinityRangeBasedMapRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AffinityRangeBasedMapPK}やデータベースレコードとして挿入される{@@link AffinityRangeBasedMapParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AffinityRangeBasedMapRow.TYPE );
    }


  /** 
   * {@@link AffinityRangeBasedMapRow}を一意に特定する{@@link AffinityRangeBasedMapPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AffinityRangeBasedMapRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AffinityRangeBasedMapParams}オブジェクトの主キーとして利用可能な{@@link AffinityRangeBasedMapPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AffinityRangeBasedMapPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AffinityRangeBasedMapRow}オブジェクトを検索します。 
   * 
   * @@param p_keyStart 検索対象であるp_keyStartフィールドの値
   * @@param p_keyEnd 検索対象であるp_keyEndフィールドの値
   * @@param p_rangeOrderNo 検索対象であるp_rangeOrderNoフィールドの値
   * @@param p_serverType 検索対象であるp_serverTypeフィールドの値
   * @@param p_ctxName 検索対象であるp_ctxNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AffinityRangeBasedMapRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AffinityRangeBasedMapRow findRowByPk( long p_keyStart, long p_keyEnd, int p_rangeOrderNo, int p_serverType, String p_ctxName ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityRangeBasedMapPK pk = new AffinityRangeBasedMapPK( p_keyStart, p_keyEnd, p_rangeOrderNo, p_serverType, p_ctxName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAffinityRangeBasedMapPKオブジェクトから{@@link AffinityRangeBasedMapRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAffinityRangeBasedMapPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AffinityRangeBasedMapRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AffinityRangeBasedMapRow findRowByPk( AffinityRangeBasedMapPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AffinityRangeBasedMapRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,long,int,int,String)}および{@@link #forRow(AffinityRangeBasedMapRow)}を使用してください。 
   */
    public static AffinityRangeBasedMapDao findDaoByPk( long p_keyStart, long p_keyEnd, int p_rangeOrderNo, int p_serverType, String p_ctxName ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityRangeBasedMapPK pk = new AffinityRangeBasedMapPK( p_keyStart, p_keyEnd, p_rangeOrderNo, p_serverType, p_ctxName );
        AffinityRangeBasedMapRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AffinityRangeBasedMapPK)}および{@@link #forRow(AffinityRangeBasedMapRow)}を使用してください。 
   */
    public static AffinityRangeBasedMapDao findDaoByPk( AffinityRangeBasedMapPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityRangeBasedMapRow row = findRowByPk( pk );
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
