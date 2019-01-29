head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FinInstitutionBankDao.java;


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
 * {@@link FinInstitutionBankDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FinInstitutionBankRow}インスタンスへ関連付けることができます。 
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
 * @@see FinInstitutionBankPK 
 * @@see FinInstitutionBankRow 
 */
public class FinInstitutionBankDao extends DataAccessObject {


  /** 
   * この{@@link FinInstitutionBankDao}に関連する型指定のRowオブジェクト 
   */
    private FinInstitutionBankRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FinInstitutionBankRow}と仮定される{@@link DataAccessObject}から新たに{@@link FinInstitutionBankDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FinInstitutionBankDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FinInstitutionBankRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FinInstitutionBankRow )
                return new FinInstitutionBankDao( (FinInstitutionBankRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FinInstitutionBankRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FinInstitutionBankRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FinInstitutionBankRow}オブジェクト 
    */
    protected FinInstitutionBankDao( FinInstitutionBankRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FinInstitutionBankRow}オブジェクトを取得します。
   */
    public FinInstitutionBankRow getFinInstitutionBankRow() {
        return row;
    }


  /** 
   * 指定の{@@link FinInstitutionBankRow}オブジェクトから{@@link FinInstitutionBankDao}オブジェクトを作成します。 
   * これは実際の{@@link FinInstitutionBankRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FinInstitutionBankDao}取得のために指定の{@@link FinInstitutionBankRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FinInstitutionBankDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FinInstitutionBankDao forRow( FinInstitutionBankRow row ) throws java.lang.IllegalArgumentException {
        return (FinInstitutionBankDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FinInstitutionBankRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FinInstitutionBankRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FinInstitutionBankPK}やデータベースレコードとして挿入される{@@link FinInstitutionBankParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FinInstitutionBankRow.TYPE );
    }


  /** 
   * {@@link FinInstitutionBankRow}を一意に特定する{@@link FinInstitutionBankPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FinInstitutionBankRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FinInstitutionBankParams}オブジェクトの主キーとして利用可能な{@@link FinInstitutionBankPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FinInstitutionBankPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FinInstitutionBankRow}オブジェクトを検索します。 
   * 
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * @@param p_finBranchCode 検索対象であるp_finBranchCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FinInstitutionBankRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FinInstitutionBankRow findRowByPk( String p_finInstitutionCode, String p_finBranchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FinInstitutionBankPK pk = new FinInstitutionBankPK( p_finInstitutionCode, p_finBranchCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFinInstitutionBankPKオブジェクトから{@@link FinInstitutionBankRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFinInstitutionBankPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FinInstitutionBankRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FinInstitutionBankRow findRowByPk( FinInstitutionBankPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FinInstitutionBankRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(FinInstitutionBankRow)}を使用してください。 
   */
    public static FinInstitutionBankDao findDaoByPk( String p_finInstitutionCode, String p_finBranchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FinInstitutionBankPK pk = new FinInstitutionBankPK( p_finInstitutionCode, p_finBranchCode );
        FinInstitutionBankRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FinInstitutionBankPK)}および{@@link #forRow(FinInstitutionBankRow)}を使用してください。 
   */
    public static FinInstitutionBankDao findDaoByPk( FinInstitutionBankPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FinInstitutionBankRow row = findRowByPk( pk );
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
   * p_finInstitutionCode, p_finBranchCode, and にて指定の値から一意の{@@link FinInstitutionBankRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * @@param p_finBranchCode 検索対象であるp_finBranchCodeフィールドの値
   * 
   * @@return 引数指定のp_finInstitutionCode, p_finBranchCode, and の値と一致する{@@link FinInstitutionBankRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FinInstitutionBankRow findRowByFinInstitutionCodeFinBranchCode( String p_finInstitutionCode, String p_finBranchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FinInstitutionBankRow.TYPE,
            "fin_institution_code=? and fin_branch_code=?",
            null,
            new Object[] { p_finInstitutionCode, p_finBranchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FinInstitutionBankRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FinInstitutionBankDao.findRowsByFinInstitutionCodeFinBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFinInstitutionCodeFinBranchCode(String, String)}および{@@link #forRow(FinInstitutionBankRow)}を使用してください。 
   */
    public static FinInstitutionBankDao findDaoByFinInstitutionCodeFinBranchCode( String p_finInstitutionCode, String p_finBranchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFinInstitutionCodeFinBranchCode( p_finInstitutionCode, p_finBranchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
