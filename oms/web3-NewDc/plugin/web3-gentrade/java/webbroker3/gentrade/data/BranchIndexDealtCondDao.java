head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchIndexDealtCondDao.java;


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
 * {@@link BranchIndexDealtCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BranchIndexDealtCondRow}インスタンスへ関連付けることができます。 
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
 * @@see BranchIndexDealtCondPK 
 * @@see BranchIndexDealtCondRow 
 */
public class BranchIndexDealtCondDao extends DataAccessObject {


  /** 
   * この{@@link BranchIndexDealtCondDao}に関連する型指定のRowオブジェクト 
   */
    private BranchIndexDealtCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BranchIndexDealtCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link BranchIndexDealtCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BranchIndexDealtCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BranchIndexDealtCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BranchIndexDealtCondRow )
                return new BranchIndexDealtCondDao( (BranchIndexDealtCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BranchIndexDealtCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BranchIndexDealtCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BranchIndexDealtCondRow}オブジェクト 
    */
    protected BranchIndexDealtCondDao( BranchIndexDealtCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BranchIndexDealtCondRow}オブジェクトを取得します。
   */
    public BranchIndexDealtCondRow getBranchIndexDealtCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link BranchIndexDealtCondRow}オブジェクトから{@@link BranchIndexDealtCondDao}オブジェクトを作成します。 
   * これは実際の{@@link BranchIndexDealtCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BranchIndexDealtCondDao}取得のために指定の{@@link BranchIndexDealtCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BranchIndexDealtCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BranchIndexDealtCondDao forRow( BranchIndexDealtCondRow row ) throws java.lang.IllegalArgumentException {
        return (BranchIndexDealtCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BranchIndexDealtCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BranchIndexDealtCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BranchIndexDealtCondPK}やデータベースレコードとして挿入される{@@link BranchIndexDealtCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BranchIndexDealtCondRow.TYPE );
    }


  /** 
   * {@@link BranchIndexDealtCondRow}を一意に特定する{@@link BranchIndexDealtCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BranchIndexDealtCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BranchIndexDealtCondParams}オブジェクトの主キーとして利用可能な{@@link BranchIndexDealtCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BranchIndexDealtCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BranchIndexDealtCondRow}オブジェクトを検索します。 
   * 
   * @@param p_targetProductCode 検索対象であるp_targetProductCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BranchIndexDealtCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BranchIndexDealtCondRow findRowByPk( String p_targetProductCode, String p_institutionCode, String p_branchCode, String p_marketCode, String p_futureOptionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchIndexDealtCondPK pk = new BranchIndexDealtCondPK( p_targetProductCode, p_institutionCode, p_branchCode, p_marketCode, p_futureOptionDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBranchIndexDealtCondPKオブジェクトから{@@link BranchIndexDealtCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBranchIndexDealtCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BranchIndexDealtCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BranchIndexDealtCondRow findRowByPk( BranchIndexDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BranchIndexDealtCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(BranchIndexDealtCondRow)}を使用してください。 
   */
    public static BranchIndexDealtCondDao findDaoByPk( String p_targetProductCode, String p_institutionCode, String p_branchCode, String p_marketCode, String p_futureOptionDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchIndexDealtCondPK pk = new BranchIndexDealtCondPK( p_targetProductCode, p_institutionCode, p_branchCode, p_marketCode, p_futureOptionDiv );
        BranchIndexDealtCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BranchIndexDealtCondPK)}および{@@link #forRow(BranchIndexDealtCondRow)}を使用してください。 
   */
    public static BranchIndexDealtCondDao findDaoByPk( BranchIndexDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BranchIndexDealtCondRow row = findRowByPk( pk );
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
