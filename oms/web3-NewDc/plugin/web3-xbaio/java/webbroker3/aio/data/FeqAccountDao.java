head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.41.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	FeqAccountDao.java;


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
 * {@@link FeqAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqAccountPK 
 * @@see FeqAccountRow 
 */
public class FeqAccountDao extends DataAccessObject {


  /** 
   * この{@@link FeqAccountDao}に関連する型指定のRowオブジェクト 
   */
    private FeqAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqAccountRow )
                return new FeqAccountDao( (FeqAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqAccountRow}オブジェクト 
    */
    protected FeqAccountDao( FeqAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqAccountRow}オブジェクトを取得します。
   */
    public FeqAccountRow getFeqAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqAccountRow}オブジェクトから{@@link FeqAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqAccountDao}取得のために指定の{@@link FeqAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqAccountDao forRow( FeqAccountRow row ) throws java.lang.IllegalArgumentException {
        return (FeqAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqAccountPK}やデータベースレコードとして挿入される{@@link FeqAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqAccountRow.TYPE );
    }


  /** 
   * {@@link FeqAccountRow}を一意に特定する{@@link FeqAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqAccountParams}オブジェクトの主キーとして利用可能な{@@link FeqAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new FeqAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_feqAccountId 検索対象であるp_feqAccountIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqAccountRow findRowByPk( long p_feqAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqAccountPK pk = new FeqAccountPK( p_feqAccountId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqAccountPKオブジェクトから{@@link FeqAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqAccountRow findRowByPk( FeqAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(FeqAccountRow)}を使用してください。 
   */
    public static FeqAccountDao findDaoByPk( long p_feqAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqAccountPK pk = new FeqAccountPK( p_feqAccountId );
        FeqAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqAccountPK)}および{@@link #forRow(FeqAccountRow)}を使用してください。 
   */
    public static FeqAccountDao findDaoByPk( FeqAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqAccountRow row = findRowByPk( pk );
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
   * p_feqAccountId, and にて指定の値から一意の{@@link FeqAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_feqAccountId 検索対象であるp_feqAccountIdフィールドの値
   * 
   * @@return 引数指定のp_feqAccountId, and の値と一致する{@@link FeqAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqAccountRow findRowByFeqAccountId( long p_feqAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqAccountRow.TYPE,
            "feq_account_id=?",
            null,
            new Object[] { new Long(p_feqAccountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqAccountDao.findRowsByFeqAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFeqAccountId(long)}および{@@link #forRow(FeqAccountRow)}を使用してください。 
   */
    public static FeqAccountDao findDaoByFeqAccountId( long p_feqAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFeqAccountId( p_feqAccountId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値から一意の{@@link FeqAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link FeqAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqAccountRow findRowByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqAccountRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqAccountDao.findRowsByInstitutionCodeBranchCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(FeqAccountRow)}を使用してください。 
   */
    public static FeqAccountDao findDaoByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_feqAccountCode, and にて指定の値から一意の{@@link FeqAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_feqAccountCode 検索対象であるp_feqAccountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_feqAccountCode, and の値と一致する{@@link FeqAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqAccountRow findRowByInstitutionCodeBranchCodeFeqAccountCode( String p_institutionCode, String p_branchCode, String p_feqAccountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqAccountRow.TYPE,
            "institution_code=? and branch_code=? and feq_account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_feqAccountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqAccountDao.findRowsByInstitutionCodeBranchCodeFeqAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeFeqAccountCode(String, String, String)}および{@@link #forRow(FeqAccountRow)}を使用してください。 
   */
    public static FeqAccountDao findDaoByInstitutionCodeBranchCodeFeqAccountCode( String p_institutionCode, String p_branchCode, String p_feqAccountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFeqAccountCode( p_institutionCode, p_branchCode, p_feqAccountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
