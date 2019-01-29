head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketDealtCondDao.java;


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
 * {@@link BranchMarketDealtCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BranchMarketDealtCondRow}インスタンスへ関連付けることができます。 
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
 * @@see BranchMarketDealtCondPK 
 * @@see BranchMarketDealtCondRow 
 */
public class BranchMarketDealtCondDao extends DataAccessObject {


  /** 
   * この{@@link BranchMarketDealtCondDao}に関連する型指定のRowオブジェクト 
   */
    private BranchMarketDealtCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BranchMarketDealtCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link BranchMarketDealtCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BranchMarketDealtCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BranchMarketDealtCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BranchMarketDealtCondRow )
                return new BranchMarketDealtCondDao( (BranchMarketDealtCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BranchMarketDealtCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BranchMarketDealtCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BranchMarketDealtCondRow}オブジェクト 
    */
    protected BranchMarketDealtCondDao( BranchMarketDealtCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BranchMarketDealtCondRow}オブジェクトを取得します。
   */
    public BranchMarketDealtCondRow getBranchMarketDealtCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link BranchMarketDealtCondRow}オブジェクトから{@@link BranchMarketDealtCondDao}オブジェクトを作成します。 
   * これは実際の{@@link BranchMarketDealtCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BranchMarketDealtCondDao}取得のために指定の{@@link BranchMarketDealtCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BranchMarketDealtCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BranchMarketDealtCondDao forRow( BranchMarketDealtCondRow row ) throws java.lang.IllegalArgumentException {
        return (BranchMarketDealtCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BranchMarketDealtCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BranchMarketDealtCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BranchMarketDealtCondPK}やデータベースレコードとして挿入される{@@link BranchMarketDealtCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BranchMarketDealtCondRow.TYPE );
    }


  /** 
   * {@@link BranchMarketDealtCondRow}を一意に特定する{@@link BranchMarketDealtCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BranchMarketDealtCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BranchMarketDealtCondParams}オブジェクトの主キーとして利用可能な{@@link BranchMarketDealtCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BranchMarketDealtCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BranchMarketDealtCondRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BranchMarketDealtCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BranchMarketDealtCondRow findRowByPk( String p_institutionCode, String p_branchCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketDealtCondPK pk = new BranchMarketDealtCondPK( p_institutionCode, p_branchCode, p_marketCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBranchMarketDealtCondPKオブジェクトから{@@link BranchMarketDealtCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBranchMarketDealtCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BranchMarketDealtCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BranchMarketDealtCondRow findRowByPk( BranchMarketDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BranchMarketDealtCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(BranchMarketDealtCondRow)}を使用してください。 
   */
    public static BranchMarketDealtCondDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketDealtCondPK pk = new BranchMarketDealtCondPK( p_institutionCode, p_branchCode, p_marketCode );
        BranchMarketDealtCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BranchMarketDealtCondPK)}および{@@link #forRow(BranchMarketDealtCondRow)}を使用してください。 
   */
    public static BranchMarketDealtCondDao findDaoByPk( BranchMarketDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketDealtCondRow row = findRowByPk( pk );
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
