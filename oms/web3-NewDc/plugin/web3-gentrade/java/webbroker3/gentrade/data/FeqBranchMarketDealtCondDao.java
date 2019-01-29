head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.41.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FeqBranchMarketDealtCondDao.java;


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
 * {@@link FeqBranchMarketDealtCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqBranchMarketDealtCondRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqBranchMarketDealtCondPK 
 * @@see FeqBranchMarketDealtCondRow 
 */
public class FeqBranchMarketDealtCondDao extends DataAccessObject {


  /** 
   * この{@@link FeqBranchMarketDealtCondDao}に関連する型指定のRowオブジェクト 
   */
    private FeqBranchMarketDealtCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqBranchMarketDealtCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqBranchMarketDealtCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqBranchMarketDealtCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqBranchMarketDealtCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqBranchMarketDealtCondRow )
                return new FeqBranchMarketDealtCondDao( (FeqBranchMarketDealtCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqBranchMarketDealtCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqBranchMarketDealtCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqBranchMarketDealtCondRow}オブジェクト 
    */
    protected FeqBranchMarketDealtCondDao( FeqBranchMarketDealtCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqBranchMarketDealtCondRow}オブジェクトを取得します。
   */
    public FeqBranchMarketDealtCondRow getFeqBranchMarketDealtCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqBranchMarketDealtCondRow}オブジェクトから{@@link FeqBranchMarketDealtCondDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqBranchMarketDealtCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqBranchMarketDealtCondDao}取得のために指定の{@@link FeqBranchMarketDealtCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqBranchMarketDealtCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqBranchMarketDealtCondDao forRow( FeqBranchMarketDealtCondRow row ) throws java.lang.IllegalArgumentException {
        return (FeqBranchMarketDealtCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqBranchMarketDealtCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqBranchMarketDealtCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqBranchMarketDealtCondPK}やデータベースレコードとして挿入される{@@link FeqBranchMarketDealtCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqBranchMarketDealtCondRow.TYPE );
    }


  /** 
   * {@@link FeqBranchMarketDealtCondRow}を一意に特定する{@@link FeqBranchMarketDealtCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqBranchMarketDealtCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqBranchMarketDealtCondParams}オブジェクトの主キーとして利用可能な{@@link FeqBranchMarketDealtCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqBranchMarketDealtCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqBranchMarketDealtCondRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqBranchMarketDealtCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqBranchMarketDealtCondRow findRowByPk( String p_institutionCode, String p_branchCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqBranchMarketDealtCondPK pk = new FeqBranchMarketDealtCondPK( p_institutionCode, p_branchCode, p_marketCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqBranchMarketDealtCondPKオブジェクトから{@@link FeqBranchMarketDealtCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqBranchMarketDealtCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqBranchMarketDealtCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqBranchMarketDealtCondRow findRowByPk( FeqBranchMarketDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqBranchMarketDealtCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(FeqBranchMarketDealtCondRow)}を使用してください。 
   */
    public static FeqBranchMarketDealtCondDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqBranchMarketDealtCondPK pk = new FeqBranchMarketDealtCondPK( p_institutionCode, p_branchCode, p_marketCode );
        FeqBranchMarketDealtCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqBranchMarketDealtCondPK)}および{@@link #forRow(FeqBranchMarketDealtCondRow)}を使用してください。 
   */
    public static FeqBranchMarketDealtCondDao findDaoByPk( FeqBranchMarketDealtCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqBranchMarketDealtCondRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_marketCode, and にて指定の値から一意の{@@link FeqBranchMarketDealtCondRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_marketCode, and の値と一致する{@@link FeqBranchMarketDealtCondRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqBranchMarketDealtCondRow findRowByInstitutionCodeBranchCodeMarketCode( String p_institutionCode, String p_branchCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqBranchMarketDealtCondRow.TYPE,
            "institution_code=? and branch_code=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_marketCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqBranchMarketDealtCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqBranchMarketDealtCondDao.findRowsByInstitutionCodeBranchCodeMarketCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeMarketCode(String, String, String)}および{@@link #forRow(FeqBranchMarketDealtCondRow)}を使用してください。 
   */
    public static FeqBranchMarketDealtCondDao findDaoByInstitutionCodeBranchCodeMarketCode( String p_institutionCode, String p_branchCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeMarketCode( p_institutionCode, p_branchCode, p_marketCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
