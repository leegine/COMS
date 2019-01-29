head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	CareerShopIdDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link CareerShopIdDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CareerShopIdRow}インスタンスへ関連付けることができます。 
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
 * @@see CareerShopIdPK 
 * @@see CareerShopIdRow 
 */
public class CareerShopIdDao extends DataAccessObject {


  /** 
   * この{@@link CareerShopIdDao}に関連する型指定のRowオブジェクト 
   */
    private CareerShopIdRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CareerShopIdRow}と仮定される{@@link DataAccessObject}から新たに{@@link CareerShopIdDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CareerShopIdDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CareerShopIdRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CareerShopIdRow )
                return new CareerShopIdDao( (CareerShopIdRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CareerShopIdRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CareerShopIdRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CareerShopIdRow}オブジェクト 
    */
    protected CareerShopIdDao( CareerShopIdRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CareerShopIdRow}オブジェクトを取得します。
   */
    public CareerShopIdRow getCareerShopIdRow() {
        return row;
    }


  /** 
   * 指定の{@@link CareerShopIdRow}オブジェクトから{@@link CareerShopIdDao}オブジェクトを作成します。 
   * これは実際の{@@link CareerShopIdRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CareerShopIdDao}取得のために指定の{@@link CareerShopIdRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CareerShopIdDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CareerShopIdDao forRow( CareerShopIdRow row ) throws java.lang.IllegalArgumentException {
        return (CareerShopIdDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CareerShopIdRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CareerShopIdRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CareerShopIdPK}やデータベースレコードとして挿入される{@@link CareerShopIdParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CareerShopIdRow.TYPE );
    }


  /** 
   * {@@link CareerShopIdRow}を一意に特定する{@@link CareerShopIdPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CareerShopIdRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CareerShopIdParams}オブジェクトの主キーとして利用可能な{@@link CareerShopIdPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CareerShopIdPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CareerShopIdRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_careerDiv 検索対象であるp_careerDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CareerShopIdRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CareerShopIdRow findRowByPk( String p_institutionCode, String p_branchCode, String p_careerDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CareerShopIdPK pk = new CareerShopIdPK( p_institutionCode, p_branchCode, p_careerDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCareerShopIdPKオブジェクトから{@@link CareerShopIdRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCareerShopIdPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CareerShopIdRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CareerShopIdRow findRowByPk( CareerShopIdPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CareerShopIdRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(CareerShopIdRow)}を使用してください。 
   */
    public static CareerShopIdDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_careerDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CareerShopIdPK pk = new CareerShopIdPK( p_institutionCode, p_branchCode, p_careerDiv );
        CareerShopIdRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CareerShopIdPK)}および{@@link #forRow(CareerShopIdRow)}を使用してください。 
   */
    public static CareerShopIdDao findDaoByPk( CareerShopIdPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CareerShopIdRow row = findRowByPk( pk );
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
