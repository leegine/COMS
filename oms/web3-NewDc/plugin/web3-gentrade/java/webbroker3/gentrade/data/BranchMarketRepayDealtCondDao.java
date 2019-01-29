head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.24.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketRepayDealtCondDao.java;


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
 * {@@link BranchMarketRepayDealtCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BranchMarketRepayDealtCondRow}インスタンスへ関連付けることができます。 
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
 * @@see BranchMarketRepayDealtCondPK 
 * @@see BranchMarketRepayDealtCondRow 
 */
public class BranchMarketRepayDealtCondDao extends DataAccessObject {


  /** 
   * この{@@link BranchMarketRepayDealtCondDao}に関連する型指定のRowオブジェクト 
   */
    private BranchMarketRepayDealtCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BranchMarketRepayDealtCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link BranchMarketRepayDealtCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BranchMarketRepayDealtCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BranchMarketRepayDealtCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BranchMarketRepayDealtCondRow )
                return new BranchMarketRepayDealtCondDao( (BranchMarketRepayDealtCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BranchMarketRepayDealtCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BranchMarketRepayDealtCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BranchMarketRepayDealtCondRow}オブジェクト 
    */
    protected BranchMarketRepayDealtCondDao( BranchMarketRepayDealtCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BranchMarketRepayDealtCondRow}オブジェクトを取得します。
   */
    public BranchMarketRepayDealtCondRow getBranchMarketRepayDealtCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link BranchMarketRepayDealtCondRow}オブジェクトから{@@link BranchMarketRepayDealtCondDao}オブジェクトを作成します。 
   * これは実際の{@@link BranchMarketRepayDealtCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BranchMarketRepayDealtCondDao}取得のために指定の{@@link BranchMarketRepayDealtCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BranchMarketRepayDealtCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BranchMarketRepayDealtCondDao forRow( BranchMarketRepayDealtCondRow row ) throws java.lang.IllegalArgumentException {
        return (BranchMarketRepayDealtCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BranchMarketRepayDealtCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BranchMarketRepayDealtCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BranchMarketRepayDealtCondPK}やデータベースレコードとして挿入される{@@link BranchMarketRepayDealtCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BranchMarketRepayDealtCondRow.TYPE );
    }


  /** 
   * {@@link BranchMarketRepayDealtCondRow}を一意に特定する{@@link BranchMarketRepayDealtCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BranchMarketRepayDealtCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BranchMarketRepayDealtCondParams}オブジェクトの主キーとして利用可能な{@@link BranchMarketRepayDealtCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BranchMarketRepayDealtCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BranchMarketRepayDealtCondRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_repaymentDiv 検索対象であるp_repaymentDivフィールドの値
   * @@param p_repaymentNum 検索対象であるp_repaymentNumフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BranchMarketRepayDealtCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BranchMarketRepayDealtCondRow findRowByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_repaymentDiv, int p_repaymentNum ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketRepayDealtCondPK pk = new BranchMarketRepayDealtCondPK( p_institutionCode, p_branchCode, p_marketCode, p_repaymentDiv, p_repaymentNum );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBranchMarketRepayDealtCondPKオブジェクトから{@@link BranchMarketRepayDealtCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBranchMarketRepayDealtCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BranchMarketRepayDealtCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BranchMarketRepayDealtCondRow findRowByPk( BranchMarketRepayDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BranchMarketRepayDealtCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,int)}および{@@link #forRow(BranchMarketRepayDealtCondRow)}を使用してください。 
   */
    public static BranchMarketRepayDealtCondDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_repaymentDiv, int p_repaymentNum ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketRepayDealtCondPK pk = new BranchMarketRepayDealtCondPK( p_institutionCode, p_branchCode, p_marketCode, p_repaymentDiv, p_repaymentNum );
        BranchMarketRepayDealtCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BranchMarketRepayDealtCondPK)}および{@@link #forRow(BranchMarketRepayDealtCondRow)}を使用してください。 
   */
    public static BranchMarketRepayDealtCondDao findDaoByPk( BranchMarketRepayDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchMarketRepayDealtCondRow row = findRowByPk( pk );
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
