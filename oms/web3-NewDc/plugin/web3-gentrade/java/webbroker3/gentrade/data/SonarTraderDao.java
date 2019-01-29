head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.25.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SonarTraderDao.java;


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
 * {@@link SonarTraderDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SonarTraderRow}インスタンスへ関連付けることができます。 
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
 * @@see SonarTraderPK 
 * @@see SonarTraderRow 
 */
public class SonarTraderDao extends DataAccessObject {


  /** 
   * この{@@link SonarTraderDao}に関連する型指定のRowオブジェクト 
   */
    private SonarTraderRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SonarTraderRow}と仮定される{@@link DataAccessObject}から新たに{@@link SonarTraderDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SonarTraderDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SonarTraderRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SonarTraderRow )
                return new SonarTraderDao( (SonarTraderRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SonarTraderRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SonarTraderRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SonarTraderRow}オブジェクト 
    */
    protected SonarTraderDao( SonarTraderRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SonarTraderRow}オブジェクトを取得します。
   */
    public SonarTraderRow getSonarTraderRow() {
        return row;
    }


  /** 
   * 指定の{@@link SonarTraderRow}オブジェクトから{@@link SonarTraderDao}オブジェクトを作成します。 
   * これは実際の{@@link SonarTraderRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SonarTraderDao}取得のために指定の{@@link SonarTraderRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SonarTraderDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SonarTraderDao forRow( SonarTraderRow row ) throws java.lang.IllegalArgumentException {
        return (SonarTraderDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SonarTraderRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SonarTraderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SonarTraderPK}やデータベースレコードとして挿入される{@@link SonarTraderParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SonarTraderRow.TYPE );
    }


  /** 
   * {@@link SonarTraderRow}を一意に特定する{@@link SonarTraderPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SonarTraderRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SonarTraderParams}オブジェクトの主キーとして利用可能な{@@link SonarTraderPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SonarTraderPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SonarTraderRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_sonarTraderCode 検索対象であるp_sonarTraderCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SonarTraderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SonarTraderRow findRowByPk( String p_institutionCode, String p_branchCode, String p_sonarTraderCode ) throws DataFindException, DataQueryException, DataNetworkException {
        SonarTraderPK pk = new SonarTraderPK( p_institutionCode, p_branchCode, p_sonarTraderCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSonarTraderPKオブジェクトから{@@link SonarTraderRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSonarTraderPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SonarTraderRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SonarTraderRow findRowByPk( SonarTraderPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SonarTraderRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(SonarTraderRow)}を使用してください。 
   */
    public static SonarTraderDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_sonarTraderCode ) throws DataFindException, DataQueryException, DataNetworkException {
        SonarTraderPK pk = new SonarTraderPK( p_institutionCode, p_branchCode, p_sonarTraderCode );
        SonarTraderRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SonarTraderPK)}および{@@link #forRow(SonarTraderRow)}を使用してください。 
   */
    public static SonarTraderDao findDaoByPk( SonarTraderPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SonarTraderRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_sonarTraderCode, and にて指定の値から一意の{@@link SonarTraderRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_sonarTraderCode 検索対象であるp_sonarTraderCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_sonarTraderCode, and の値と一致する{@@link SonarTraderRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SonarTraderRow findRowByInstitutionCodeBranchCodeSonarTraderCode( String p_institutionCode, String p_branchCode, String p_sonarTraderCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SonarTraderRow.TYPE,
            "institution_code=? and branch_code=? and sonar_trader_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_sonarTraderCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SonarTraderRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SonarTraderDao.findRowsByInstitutionCodeBranchCodeSonarTraderCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeSonarTraderCode(String, String, String)}および{@@link #forRow(SonarTraderRow)}を使用してください。 
   */
    public static SonarTraderDao findDaoByInstitutionCodeBranchCodeSonarTraderCode( String p_institutionCode, String p_branchCode, String p_sonarTraderCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeSonarTraderCode( p_institutionCode, p_branchCode, p_sonarTraderCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
