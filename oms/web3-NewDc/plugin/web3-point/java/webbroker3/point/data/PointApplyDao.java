head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.49.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointApplyDao.java;


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
 * {@@link PointApplyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PointApplyRow}インスタンスへ関連付けることができます。 
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
 * @@see PointApplyPK 
 * @@see PointApplyRow 
 */
public class PointApplyDao extends DataAccessObject {


  /** 
   * この{@@link PointApplyDao}に関連する型指定のRowオブジェクト 
   */
    private PointApplyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PointApplyRow}と仮定される{@@link DataAccessObject}から新たに{@@link PointApplyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PointApplyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PointApplyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PointApplyRow )
                return new PointApplyDao( (PointApplyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PointApplyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PointApplyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PointApplyRow}オブジェクト 
    */
    protected PointApplyDao( PointApplyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PointApplyRow}オブジェクトを取得します。
   */
    public PointApplyRow getPointApplyRow() {
        return row;
    }


  /** 
   * 指定の{@@link PointApplyRow}オブジェクトから{@@link PointApplyDao}オブジェクトを作成します。 
   * これは実際の{@@link PointApplyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PointApplyDao}取得のために指定の{@@link PointApplyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PointApplyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PointApplyDao forRow( PointApplyRow row ) throws java.lang.IllegalArgumentException {
        return (PointApplyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PointApplyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PointApplyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PointApplyPK}やデータベースレコードとして挿入される{@@link PointApplyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PointApplyRow.TYPE );
    }


  /** 
   * {@@link PointApplyRow}を一意に特定する{@@link PointApplyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PointApplyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PointApplyParams}オブジェクトの主キーとして利用可能な{@@link PointApplyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PointApplyPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new PointApplyPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PointApplyRow}オブジェクトを検索します。 
   * 
   * @@param p_applyId 検索対象であるp_applyIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointApplyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointApplyRow findRowByPk( long p_applyId ) throws DataFindException, DataQueryException, DataNetworkException {
        PointApplyPK pk = new PointApplyPK( p_applyId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPointApplyPKオブジェクトから{@@link PointApplyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPointApplyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PointApplyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PointApplyRow findRowByPk( PointApplyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PointApplyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(PointApplyRow)}を使用してください。 
   */
    public static PointApplyDao findDaoByPk( long p_applyId ) throws DataFindException, DataQueryException, DataNetworkException {
        PointApplyPK pk = new PointApplyPK( p_applyId );
        PointApplyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PointApplyPK)}および{@@link #forRow(PointApplyRow)}を使用してください。 
   */
    public static PointApplyDao findDaoByPk( PointApplyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PointApplyRow row = findRowByPk( pk );
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
   * p_applyId, and にて指定の値から一意の{@@link PointApplyRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_applyId 検索対象であるp_applyIdフィールドの値
   * 
   * @@return 引数指定のp_applyId, and の値と一致する{@@link PointApplyRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PointApplyRow findRowByApplyId( long p_applyId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PointApplyRow.TYPE,
            "apply_id=?",
            null,
            new Object[] { new Long(p_applyId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PointApplyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PointApplyDao.findRowsByApplyId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByApplyId(long)}および{@@link #forRow(PointApplyRow)}を使用してください。 
   */
    public static PointApplyDao findDaoByApplyId( long p_applyId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByApplyId( p_applyId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値に一致する{@@link PointApplyRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link PointApplyRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PointApplyRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(PointApplyRow)}を使用してください。 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }

}
@
