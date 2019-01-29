head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MfFixedBuyingDrawAccountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MfFixedBuyingDrawAccountDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MfFixedBuyingDrawAccountRow}インスタンスへ関連付けることができます。 
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
 * @@see MfFixedBuyingDrawAccountPK 
 * @@see MfFixedBuyingDrawAccountRow 
 */
public class MfFixedBuyingDrawAccountDao extends DataAccessObject {


  /** 
   * この{@@link MfFixedBuyingDrawAccountDao}に関連する型指定のRowオブジェクト 
   */
    private MfFixedBuyingDrawAccountRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MfFixedBuyingDrawAccountRow}と仮定される{@@link DataAccessObject}から新たに{@@link MfFixedBuyingDrawAccountDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MfFixedBuyingDrawAccountDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MfFixedBuyingDrawAccountRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MfFixedBuyingDrawAccountRow )
                return new MfFixedBuyingDrawAccountDao( (MfFixedBuyingDrawAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MfFixedBuyingDrawAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MfFixedBuyingDrawAccountRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MfFixedBuyingDrawAccountRow}オブジェクト 
    */
    protected MfFixedBuyingDrawAccountDao( MfFixedBuyingDrawAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MfFixedBuyingDrawAccountRow}オブジェクトを取得します。
   */
    public MfFixedBuyingDrawAccountRow getMfFixedBuyingDrawAccountRow() {
        return row;
    }


  /** 
   * 指定の{@@link MfFixedBuyingDrawAccountRow}オブジェクトから{@@link MfFixedBuyingDrawAccountDao}オブジェクトを作成します。 
   * これは実際の{@@link MfFixedBuyingDrawAccountRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MfFixedBuyingDrawAccountDao}取得のために指定の{@@link MfFixedBuyingDrawAccountRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MfFixedBuyingDrawAccountDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MfFixedBuyingDrawAccountDao forRow( MfFixedBuyingDrawAccountRow row ) throws java.lang.IllegalArgumentException {
        return (MfFixedBuyingDrawAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MfFixedBuyingDrawAccountRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MfFixedBuyingDrawAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MfFixedBuyingDrawAccountPK}やデータベースレコードとして挿入される{@@link MfFixedBuyingDrawAccountParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MfFixedBuyingDrawAccountRow.TYPE );
    }


  /** 
   * {@@link MfFixedBuyingDrawAccountRow}を一意に特定する{@@link MfFixedBuyingDrawAccountPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MfFixedBuyingDrawAccountRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MfFixedBuyingDrawAccountParams}オブジェクトの主キーとして利用可能な{@@link MfFixedBuyingDrawAccountPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MfFixedBuyingDrawAccountPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MfFixedBuyingDrawAccountRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfFixedBuyingDrawAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfFixedBuyingDrawAccountRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingDrawAccountPK pk = new MfFixedBuyingDrawAccountPK( p_institutionCode, p_branchCode, p_accountCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMfFixedBuyingDrawAccountPKオブジェクトから{@@link MfFixedBuyingDrawAccountRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMfFixedBuyingDrawAccountPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MfFixedBuyingDrawAccountRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MfFixedBuyingDrawAccountRow findRowByPk( MfFixedBuyingDrawAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MfFixedBuyingDrawAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(MfFixedBuyingDrawAccountRow)}を使用してください。 
   */
    public static MfFixedBuyingDrawAccountDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingDrawAccountPK pk = new MfFixedBuyingDrawAccountPK( p_institutionCode, p_branchCode, p_accountCode );
        MfFixedBuyingDrawAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MfFixedBuyingDrawAccountPK)}および{@@link #forRow(MfFixedBuyingDrawAccountRow)}を使用してください。 
   */
    public static MfFixedBuyingDrawAccountDao findDaoByPk( MfFixedBuyingDrawAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MfFixedBuyingDrawAccountRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, and にて指定の値から一意の{@@link MfFixedBuyingDrawAccountRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, and の値と一致する{@@link MfFixedBuyingDrawAccountRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MfFixedBuyingDrawAccountRow findRowByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MfFixedBuyingDrawAccountRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MfFixedBuyingDrawAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MfFixedBuyingDrawAccountDao.findRowsByInstitutionCodeBranchCodeAccountCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCode(String, String, String)}および{@@link #forRow(MfFixedBuyingDrawAccountRow)}を使用してください。 
   */
    public static MfFixedBuyingDrawAccountDao findDaoByInstitutionCodeBranchCodeAccountCode( String p_institutionCode, String p_branchCode, String p_accountCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCode( p_institutionCode, p_branchCode, p_accountCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
