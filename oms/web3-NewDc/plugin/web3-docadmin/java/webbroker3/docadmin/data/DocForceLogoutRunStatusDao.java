head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocForceLogoutRunStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.docadmin.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link DocForceLogoutRunStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link DocForceLogoutRunStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see DocForceLogoutRunStatusPK 
 * @@see DocForceLogoutRunStatusRow 
 */
public class DocForceLogoutRunStatusDao extends DataAccessObject {


  /** 
   * この{@@link DocForceLogoutRunStatusDao}に関連する型指定のRowオブジェクト 
   */
    private DocForceLogoutRunStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link DocForceLogoutRunStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link DocForceLogoutRunStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link DocForceLogoutRunStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link DocForceLogoutRunStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocForceLogoutRunStatusRow )
                return new DocForceLogoutRunStatusDao( (DocForceLogoutRunStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocForceLogoutRunStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocForceLogoutRunStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link DocForceLogoutRunStatusRow}オブジェクト 
    */
    protected DocForceLogoutRunStatusDao( DocForceLogoutRunStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link DocForceLogoutRunStatusRow}オブジェクトを取得します。
   */
    public DocForceLogoutRunStatusRow getDocForceLogoutRunStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link DocForceLogoutRunStatusRow}オブジェクトから{@@link DocForceLogoutRunStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link DocForceLogoutRunStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link DocForceLogoutRunStatusDao}取得のために指定の{@@link DocForceLogoutRunStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link DocForceLogoutRunStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static DocForceLogoutRunStatusDao forRow( DocForceLogoutRunStatusRow row ) throws java.lang.IllegalArgumentException {
        return (DocForceLogoutRunStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocForceLogoutRunStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link DocForceLogoutRunStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link DocForceLogoutRunStatusPK}やデータベースレコードとして挿入される{@@link DocForceLogoutRunStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocForceLogoutRunStatusRow.TYPE );
    }


  /** 
   * {@@link DocForceLogoutRunStatusRow}を一意に特定する{@@link DocForceLogoutRunStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link DocForceLogoutRunStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link DocForceLogoutRunStatusParams}オブジェクトの主キーとして利用可能な{@@link DocForceLogoutRunStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static DocForceLogoutRunStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link DocForceLogoutRunStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accountIdFrom 検索対象であるp_accountIdFromフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocForceLogoutRunStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocForceLogoutRunStatusRow findRowByPk( String p_institutionCode, long p_accountIdFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        DocForceLogoutRunStatusPK pk = new DocForceLogoutRunStatusPK( p_institutionCode, p_accountIdFrom );
        return findRowByPk( pk );
    }


  /** 
   * 指定のDocForceLogoutRunStatusPKオブジェクトから{@@link DocForceLogoutRunStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するDocForceLogoutRunStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link DocForceLogoutRunStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static DocForceLogoutRunStatusRow findRowByPk( DocForceLogoutRunStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocForceLogoutRunStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,long)}および{@@link #forRow(DocForceLogoutRunStatusRow)}を使用してください。 
   */
    public static DocForceLogoutRunStatusDao findDaoByPk( String p_institutionCode, long p_accountIdFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        DocForceLogoutRunStatusPK pk = new DocForceLogoutRunStatusPK( p_institutionCode, p_accountIdFrom );
        DocForceLogoutRunStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(DocForceLogoutRunStatusPK)}および{@@link #forRow(DocForceLogoutRunStatusRow)}を使用してください。 
   */
    public static DocForceLogoutRunStatusDao findDaoByPk( DocForceLogoutRunStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocForceLogoutRunStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accountIdFrom, and にて指定の値から一意の{@@link DocForceLogoutRunStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_accountIdFrom 検索対象であるp_accountIdFromフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_accountIdFrom, and の値と一致する{@@link DocForceLogoutRunStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static DocForceLogoutRunStatusRow findRowByInstitutionCodeAccountIdFrom( String p_institutionCode, long p_accountIdFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocForceLogoutRunStatusRow.TYPE,
            "institution_code=? and account_id_from=?",
            null,
            new Object[] { p_institutionCode, new Long(p_accountIdFrom) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocForceLogoutRunStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocForceLogoutRunStatusDao.findRowsByInstitutionCodeAccountIdFrom(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAccountIdFrom(String, long)}および{@@link #forRow(DocForceLogoutRunStatusRow)}を使用してください。 
   */
    public static DocForceLogoutRunStatusDao findDaoByInstitutionCodeAccountIdFrom( String p_institutionCode, long p_accountIdFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccountIdFrom( p_institutionCode, p_accountIdFrom ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
