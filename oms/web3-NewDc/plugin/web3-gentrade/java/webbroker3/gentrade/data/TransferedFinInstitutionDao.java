head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TransferedFinInstitutionDao.java;


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
 * {@@link TransferedFinInstitutionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TransferedFinInstitutionRow}インスタンスへ関連付けることができます。 
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
 * @@see TransferedFinInstitutionPK 
 * @@see TransferedFinInstitutionRow 
 */
public class TransferedFinInstitutionDao extends DataAccessObject {


  /** 
   * この{@@link TransferedFinInstitutionDao}に関連する型指定のRowオブジェクト 
   */
    private TransferedFinInstitutionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TransferedFinInstitutionRow}と仮定される{@@link DataAccessObject}から新たに{@@link TransferedFinInstitutionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TransferedFinInstitutionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TransferedFinInstitutionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TransferedFinInstitutionRow )
                return new TransferedFinInstitutionDao( (TransferedFinInstitutionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TransferedFinInstitutionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TransferedFinInstitutionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TransferedFinInstitutionRow}オブジェクト 
    */
    protected TransferedFinInstitutionDao( TransferedFinInstitutionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TransferedFinInstitutionRow}オブジェクトを取得します。
   */
    public TransferedFinInstitutionRow getTransferedFinInstitutionRow() {
        return row;
    }


  /** 
   * 指定の{@@link TransferedFinInstitutionRow}オブジェクトから{@@link TransferedFinInstitutionDao}オブジェクトを作成します。 
   * これは実際の{@@link TransferedFinInstitutionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TransferedFinInstitutionDao}取得のために指定の{@@link TransferedFinInstitutionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TransferedFinInstitutionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TransferedFinInstitutionDao forRow( TransferedFinInstitutionRow row ) throws java.lang.IllegalArgumentException {
        return (TransferedFinInstitutionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TransferedFinInstitutionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link TransferedFinInstitutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link TransferedFinInstitutionPK}やデータベースレコードとして挿入される{@@link TransferedFinInstitutionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TransferedFinInstitutionRow.TYPE );
    }


  /** 
   * {@@link TransferedFinInstitutionRow}を一意に特定する{@@link TransferedFinInstitutionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link TransferedFinInstitutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link TransferedFinInstitutionParams}オブジェクトの主キーとして利用可能な{@@link TransferedFinInstitutionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static TransferedFinInstitutionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link TransferedFinInstitutionRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_designateDiv 検索対象であるp_designateDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TransferedFinInstitutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TransferedFinInstitutionRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        TransferedFinInstitutionPK pk = new TransferedFinInstitutionPK( p_institutionCode, p_branchCode, p_accountCode, p_designateDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のTransferedFinInstitutionPKオブジェクトから{@@link TransferedFinInstitutionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するTransferedFinInstitutionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link TransferedFinInstitutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static TransferedFinInstitutionRow findRowByPk( TransferedFinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TransferedFinInstitutionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(TransferedFinInstitutionRow)}を使用してください。 
   */
    public static TransferedFinInstitutionDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        TransferedFinInstitutionPK pk = new TransferedFinInstitutionPK( p_institutionCode, p_branchCode, p_accountCode, p_designateDiv );
        TransferedFinInstitutionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(TransferedFinInstitutionPK)}および{@@link #forRow(TransferedFinInstitutionRow)}を使用してください。 
   */
    public static TransferedFinInstitutionDao findDaoByPk( TransferedFinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TransferedFinInstitutionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, and にて指定の値から一意の{@@link TransferedFinInstitutionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_designateDiv 検索対象であるp_designateDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_accountCode, p_designateDiv, and の値と一致する{@@link TransferedFinInstitutionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static TransferedFinInstitutionRow findRowByInstitutionCodeBranchCodeAccountCodeDesignateDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TransferedFinInstitutionRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and designate_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_designateDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TransferedFinInstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TransferedFinInstitutionDao.findRowsByInstitutionCodeBranchCodeAccountCodeDesignateDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDesignateDiv(String, String, String, String)}および{@@link #forRow(TransferedFinInstitutionRow)}を使用してください。 
   */
    public static TransferedFinInstitutionDao findDaoByInstitutionCodeBranchCodeAccountCodeDesignateDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDesignateDiv( p_institutionCode, p_branchCode, p_accountCode, p_designateDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
