head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OffFloorOrderProductDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link OffFloorOrderProductDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link OffFloorOrderProductRow}インスタンスへ関連付けることができます。 
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
 * @@see OffFloorOrderProductPK 
 * @@see OffFloorOrderProductRow 
 */
public class OffFloorOrderProductDao extends DataAccessObject {


  /** 
   * この{@@link OffFloorOrderProductDao}に関連する型指定のRowオブジェクト 
   */
    private OffFloorOrderProductRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link OffFloorOrderProductRow}と仮定される{@@link DataAccessObject}から新たに{@@link OffFloorOrderProductDao}を返します。 
         * @@return 指定のRowに結びつく{@@link OffFloorOrderProductDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link OffFloorOrderProductRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OffFloorOrderProductRow )
                return new OffFloorOrderProductDao( (OffFloorOrderProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OffFloorOrderProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OffFloorOrderProductRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link OffFloorOrderProductRow}オブジェクト 
    */
    protected OffFloorOrderProductDao( OffFloorOrderProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link OffFloorOrderProductRow}オブジェクトを取得します。
   */
    public OffFloorOrderProductRow getOffFloorOrderProductRow() {
        return row;
    }


  /** 
   * 指定の{@@link OffFloorOrderProductRow}オブジェクトから{@@link OffFloorOrderProductDao}オブジェクトを作成します。 
   * これは実際の{@@link OffFloorOrderProductRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link OffFloorOrderProductDao}取得のために指定の{@@link OffFloorOrderProductRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link OffFloorOrderProductDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static OffFloorOrderProductDao forRow( OffFloorOrderProductRow row ) throws java.lang.IllegalArgumentException {
        return (OffFloorOrderProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OffFloorOrderProductRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link OffFloorOrderProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link OffFloorOrderProductPK}やデータベースレコードとして挿入される{@@link OffFloorOrderProductParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OffFloorOrderProductRow.TYPE );
    }


  /** 
   * {@@link OffFloorOrderProductRow}を一意に特定する{@@link OffFloorOrderProductPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link OffFloorOrderProductRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link OffFloorOrderProductParams}オブジェクトの主キーとして利用可能な{@@link OffFloorOrderProductPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static OffFloorOrderProductPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link OffFloorOrderProductRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_orderEndDatetime 検索対象であるp_orderEndDatetimeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OffFloorOrderProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OffFloorOrderProductRow findRowByPk( String p_institutionCode, String p_productCode, String p_marketCode, java.sql.Timestamp p_orderEndDatetime ) throws DataFindException, DataQueryException, DataNetworkException {
        OffFloorOrderProductPK pk = new OffFloorOrderProductPK( p_institutionCode, p_productCode, p_marketCode, p_orderEndDatetime );
        return findRowByPk( pk );
    }


  /** 
   * 指定のOffFloorOrderProductPKオブジェクトから{@@link OffFloorOrderProductRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するOffFloorOrderProductPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link OffFloorOrderProductRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static OffFloorOrderProductRow findRowByPk( OffFloorOrderProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OffFloorOrderProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,java.sql.Timestamp)}および{@@link #forRow(OffFloorOrderProductRow)}を使用してください。 
   */
    public static OffFloorOrderProductDao findDaoByPk( String p_institutionCode, String p_productCode, String p_marketCode, java.sql.Timestamp p_orderEndDatetime ) throws DataFindException, DataQueryException, DataNetworkException {
        OffFloorOrderProductPK pk = new OffFloorOrderProductPK( p_institutionCode, p_productCode, p_marketCode, p_orderEndDatetime );
        OffFloorOrderProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(OffFloorOrderProductPK)}および{@@link #forRow(OffFloorOrderProductRow)}を使用してください。 
   */
    public static OffFloorOrderProductDao findDaoByPk( OffFloorOrderProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OffFloorOrderProductRow row = findRowByPk( pk );
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
