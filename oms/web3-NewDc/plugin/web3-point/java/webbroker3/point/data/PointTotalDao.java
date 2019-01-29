head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.49.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointTotalDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.point.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link PointTotalDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PointTotalRow}インスタンスへ関連付けることができます。 
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
 * @@see PointTotalPK 
 * @@see PointTotalRow 
 */
public class PointTotalDao extends DataAccessObject {


  /** 
   * この{@@link PointTotalDao}に関連する型指定のRowオブジェクト 
   */
    private PointTotalRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PointTotalRow}と仮定される{@@link DataAccessObject}から新たに{@@link PointTotalDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PointTotalDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PointTotalRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PointTotalRow )
                return new PointTotalDao( (PointTotalRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PointTotalRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PointTotalRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PointTotalRow}オブジェクト 
    */
    protected PointTotalDao( PointTotalRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PointTotalRow}オブジェクトを取得します。
   */
    public PointTotalRow getPointTotalRow() {
        return row;
    }


  /** 
   * 指定の{@@link PointTotalRow}オブジェクトから{@@link PointTotalDao}オブジェクトを作成します。 
   * これは実際の{@@link PointTotalRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PointTotalDao}取得のために指定の{@@link PointTotalRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PointTotalDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PointTotalDao forRow( PointTotalRow row ) throws java.lang.IllegalArgumentException {
        return (PointTotalDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PointTotalRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PointTotalRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PointTotalPK}やデータベースレコードとして挿入される{@@link PointTotalParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PointTotalRow.TYPE );
    }


  /** 
   * {@@link PointTotalRow}を一意に特定する{@@link PointTotalPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PointTotalRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PointTotalParams}オブジェクトの主キーとして利用可能な{@@link PointTotalPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PointTotalPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PointTotalRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_period 検索対象であるp_periodフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointTotalRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointTotalRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_period ) throws DataFindException, DataQueryException, DataNetworkException {
        PointTotalPK pk = new PointTotalPK( p_institutionCode, p_branchCode, p_accountCode, p_period );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPointTotalPKオブジェクトから{@@link PointTotalRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPointTotalPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointTotalRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointTotalRow findRowByPk( PointTotalPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PointTotalRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(PointTotalRow)}を使用してください。 
   */
    public static PointTotalDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_period ) throws DataFindException, DataQueryException, DataNetworkException {
        PointTotalPK pk = new PointTotalPK( p_institutionCode, p_branchCode, p_accountCode, p_period );
        PointTotalRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PointTotalPK)}および{@@link #forRow(PointTotalRow)}を使用してください。 
   */
    public static PointTotalDao findDaoByPk( PointTotalPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PointTotalRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_period, and にて指定の値から一意の{@@link PointTotalRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_period 検索対象であるp_periodフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_period, and の値と一致する{@@link PointTotalRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PointTotalRow findRowByInstitutionCodeBranchCodeAccountCodePeriod( String p_institutionCode, String p_branchCode, String p_accountCode, String p_period ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PointTotalRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and period=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_period } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PointTotalRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PointTotalDao.findRowsByInstitutionCodeBranchCodeAccountCodePeriod(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodePeriod(String, String, String, String)}および{@@link #forRow(PointTotalRow)}を使用してください。 
   */
    public static PointTotalDao findDaoByInstitutionCodeBranchCodeAccountCodePeriod( String p_institutionCode, String p_branchCode, String p_accountCode, String p_period ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodePeriod( p_institutionCode, p_branchCode, p_accountCode, p_period ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
