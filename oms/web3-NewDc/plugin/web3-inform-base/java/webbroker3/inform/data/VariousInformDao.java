head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	VariousInformDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.inform.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link VariousInformDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link VariousInformRow}インスタンスへ関連付けることができます。 
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
 * @@see VariousInformPK 
 * @@see VariousInformRow 
 */
public class VariousInformDao extends DataAccessObject {


  /** 
   * この{@@link VariousInformDao}に関連する型指定のRowオブジェクト 
   */
    private VariousInformRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link VariousInformRow}と仮定される{@@link DataAccessObject}から新たに{@@link VariousInformDao}を返します。 
         * @@return 指定のRowに結びつく{@@link VariousInformDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link VariousInformRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof VariousInformRow )
                return new VariousInformDao( (VariousInformRow) row );
            throw new java.lang.IllegalArgumentException( "Not a VariousInformRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link VariousInformRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link VariousInformRow}オブジェクト 
    */
    protected VariousInformDao( VariousInformRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link VariousInformRow}オブジェクトを取得します。
   */
    public VariousInformRow getVariousInformRow() {
        return row;
    }


  /** 
   * 指定の{@@link VariousInformRow}オブジェクトから{@@link VariousInformDao}オブジェクトを作成します。 
   * これは実際の{@@link VariousInformRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link VariousInformDao}取得のために指定の{@@link VariousInformRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link VariousInformDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static VariousInformDao forRow( VariousInformRow row ) throws java.lang.IllegalArgumentException {
        return (VariousInformDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link VariousInformRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link VariousInformRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link VariousInformPK}やデータベースレコードとして挿入される{@@link VariousInformParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( VariousInformRow.TYPE );
    }


  /** 
   * {@@link VariousInformRow}を一意に特定する{@@link VariousInformPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link VariousInformRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link VariousInformParams}オブジェクトの主キーとして利用可能な{@@link VariousInformPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static VariousInformPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link VariousInformRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * @@param p_requestNumber 検索対象であるp_requestNumberフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link VariousInformRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static VariousInformRow findRowByPk( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        VariousInformPK pk = new VariousInformPK( p_institutionCode, p_informDiv, p_requestNumber, p_branchCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のVariousInformPKオブジェクトから{@@link VariousInformRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するVariousInformPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link VariousInformRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static VariousInformRow findRowByPk( VariousInformPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (VariousInformRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String)}および{@@link #forRow(VariousInformRow)}を使用してください。 
   */
    public static VariousInformDao findDaoByPk( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        VariousInformPK pk = new VariousInformPK( p_institutionCode, p_informDiv, p_requestNumber, p_branchCode );
        VariousInformRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(VariousInformPK)}および{@@link #forRow(VariousInformRow)}を使用してください。 
   */
    public static VariousInformDao findDaoByPk( VariousInformPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        VariousInformRow row = findRowByPk( pk );
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
   * p_institutionCode, p_informDiv, p_requestNumber, p_branchCode, and にて指定の値から一意の{@@link VariousInformRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * @@param p_requestNumber 検索対象であるp_requestNumberフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_informDiv, p_requestNumber, p_branchCode, and の値と一致する{@@link VariousInformRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static VariousInformRow findRowByInstitutionCodeInformDivRequestNumberBranchCode( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            VariousInformRow.TYPE,
            "institution_code=? and inform_div=? and request_number=? and branch_code=?",
            null,
            new Object[] { p_institutionCode, p_informDiv, p_requestNumber, p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (VariousInformRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query VariousInformDao.findRowsByInstitutionCodeInformDivRequestNumberBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeInformDivRequestNumberBranchCode(String, String, String, String)}および{@@link #forRow(VariousInformRow)}を使用してください。 
   */
    public static VariousInformDao findDaoByInstitutionCodeInformDivRequestNumberBranchCode( String p_institutionCode, String p_informDiv, String p_requestNumber, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeInformDivRequestNumberBranchCode( p_institutionCode, p_informDiv, p_requestNumber, p_branchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
