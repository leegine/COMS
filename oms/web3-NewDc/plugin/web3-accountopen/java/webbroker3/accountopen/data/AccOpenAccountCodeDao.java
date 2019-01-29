head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.14.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenAccountCodeDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AccOpenAccountCodeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenAccountCodeRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenAccountCodePK 
 * @@see AccOpenAccountCodeRow 
 */
public class AccOpenAccountCodeDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenAccountCodeDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenAccountCodeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenAccountCodeRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenAccountCodeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenAccountCodeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenAccountCodeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenAccountCodeRow )
                return new AccOpenAccountCodeDao( (AccOpenAccountCodeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenAccountCodeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenAccountCodeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenAccountCodeRow}オブジェクト 
    */
    protected AccOpenAccountCodeDao( AccOpenAccountCodeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenAccountCodeRow}オブジェクトを取得します。
   */
    public AccOpenAccountCodeRow getAccOpenAccountCodeRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenAccountCodeRow}オブジェクトから{@@link AccOpenAccountCodeDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenAccountCodeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenAccountCodeDao}取得のために指定の{@@link AccOpenAccountCodeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenAccountCodeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenAccountCodeDao forRow( AccOpenAccountCodeRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenAccountCodeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenAccountCodeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenAccountCodeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenAccountCodePK}やデータベースレコードとして挿入される{@@link AccOpenAccountCodeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenAccountCodeRow.TYPE );
    }


  /** 
   * {@@link AccOpenAccountCodeRow}を一意に特定する{@@link AccOpenAccountCodePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenAccountCodeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenAccountCodeParams}オブジェクトの主キーとして利用可能な{@@link AccOpenAccountCodePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenAccountCodePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenAccountCodeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenAccountCodeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenAccountCodeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenAccountCodePK pk = new AccOpenAccountCodePK( p_institutionCode, p_branchCode, p_accountDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenAccountCodePKオブジェクトから{@@link AccOpenAccountCodeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenAccountCodePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenAccountCodeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenAccountCodeRow findRowByPk( AccOpenAccountCodePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenAccountCodeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(AccOpenAccountCodeRow)}を使用してください。 
   */
    public static AccOpenAccountCodeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenAccountCodePK pk = new AccOpenAccountCodePK( p_institutionCode, p_branchCode, p_accountDiv );
        AccOpenAccountCodeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenAccountCodePK)}および{@@link #forRow(AccOpenAccountCodeRow)}を使用してください。 
   */
    public static AccOpenAccountCodeDao findDaoByPk( AccOpenAccountCodePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenAccountCodeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountDiv, and にて指定の値から一意の{@@link AccOpenAccountCodeRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountDiv 検索対象であるp_accountDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountDiv, and の値と一致する{@@link AccOpenAccountCodeRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenAccountCodeRow findRowByInstitutionCodeBranchCodeAccountDiv( String p_institutionCode, String p_branchCode, String p_accountDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenAccountCodeRow.TYPE,
            "institution_code=? and branch_code=? and account_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenAccountCodeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenAccountCodeDao.findRowsByInstitutionCodeBranchCodeAccountDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountDiv(String, String, String)}および{@@link #forRow(AccOpenAccountCodeRow)}を使用してください。 
   */
    public static AccOpenAccountCodeDao findDaoByInstitutionCodeBranchCodeAccountDiv( String p_institutionCode, String p_branchCode, String p_accountDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountDiv( p_institutionCode, p_branchCode, p_accountDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
