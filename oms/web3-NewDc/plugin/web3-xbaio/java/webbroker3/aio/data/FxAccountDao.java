head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	FxAccountDao.java;


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
 * {@@link FxAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FxAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see FxAccountPK 
 * @@see FxAccountRow 
 */
public class FxAccountDao extends DataAccessObject {


  /** 
   * この{@@link FxAccountDao}に関連する型指定のRowオブジェクト 
   */
    private FxAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FxAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link FxAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FxAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FxAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FxAccountRow )
                return new FxAccountDao( (FxAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FxAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FxAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FxAccountRow}オブジェクト 
    */
    protected FxAccountDao( FxAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FxAccountRow}オブジェクトを取得します。
   */
    public FxAccountRow getFxAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link FxAccountRow}オブジェクトから{@@link FxAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link FxAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FxAccountDao}取得のために指定の{@@link FxAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FxAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FxAccountDao forRow( FxAccountRow row ) throws java.lang.IllegalArgumentException {
        return (FxAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FxAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FxAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FxAccountPK}やデータベースレコードとして挿入される{@@link FxAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FxAccountRow.TYPE );
    }


  /** 
   * {@@link FxAccountRow}を一意に特定する{@@link FxAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FxAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FxAccountParams}オブジェクトの主キーとして利用可能な{@@link FxAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FxAccountPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new FxAccountPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FxAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_fxAccountId 検索対象であるp_fxAccountIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FxAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FxAccountRow findRowByPk( long p_fxAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountPK pk = new FxAccountPK( p_fxAccountId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFxAccountPKオブジェクトから{@@link FxAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFxAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FxAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FxAccountRow findRowByPk( FxAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FxAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(FxAccountRow)}を使用してください。 
   */
    public static FxAccountDao findDaoByPk( long p_fxAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountPK pk = new FxAccountPK( p_fxAccountId );
        FxAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FxAccountPK)}および{@@link #forRow(FxAccountRow)}を使用してください。 
   */
    public static FxAccountDao findDaoByPk( FxAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FxAccountRow row = findRowByPk( pk );
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
   * p_fxAccountId, and にて指定の値から一意の{@@link FxAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_fxAccountId 検索対象であるp_fxAccountIdフィールドの値
   * 
   * @@return 引数指定のp_fxAccountId, and の値と一致する{@@link FxAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FxAccountRow findRowByFxAccountId( long p_fxAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxAccountRow.TYPE,
            "fx_account_id=?",
            null,
            new Object[] { new Long(p_fxAccountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxAccountDao.findRowsByFxAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFxAccountId(long)}および{@@link #forRow(FxAccountRow)}を使用してください。 
   */
    public static FxAccountDao findDaoByFxAccountId( long p_fxAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFxAccountId( p_fxAccountId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, and にて指定の値から一意の{@@link FxAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_fxSystemCode 検索対象であるp_fxSystemCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode, and の値と一致する{@@link FxAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FxAccountRow findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxAccountRow.TYPE,
            "institution_code=? and branch_code=? and fx_system_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxAccountDao.findRowsByInstitutionCodeBranchCodeFxSystemCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode(String, String, String, String)}および{@@link #forRow(FxAccountRow)}を使用してください。 
   */
    public static FxAccountDao findDaoByInstitutionCodeBranchCodeFxSystemCodeAccountCode( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode( p_institutionCode, p_branchCode, p_fxSystemCode, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
