head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	CustodianDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link CustodianDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CustodianRow}インスタンスへ関連付けることができます。 
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
 * @@see CustodianPK 
 * @@see CustodianRow 
 */
public class CustodianDao extends DataAccessObject {


  /** 
   * この{@@link CustodianDao}に関連する型指定のRowオブジェクト 
   */
    private CustodianRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CustodianRow}と仮定される{@@link DataAccessObject}から新たに{@@link CustodianDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CustodianDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CustodianRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CustodianRow )
                return new CustodianDao( (CustodianRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CustodianRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CustodianRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CustodianRow}オブジェクト 
    */
    protected CustodianDao( CustodianRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CustodianRow}オブジェクトを取得します。
   */
    public CustodianRow getCustodianRow() {
        return row;
    }


  /** 
   * 指定の{@@link CustodianRow}オブジェクトから{@@link CustodianDao}オブジェクトを作成します。 
   * これは実際の{@@link CustodianRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CustodianDao}取得のために指定の{@@link CustodianRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CustodianDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CustodianDao forRow( CustodianRow row ) throws java.lang.IllegalArgumentException {
        return (CustodianDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CustodianRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CustodianRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CustodianPK}やデータベースレコードとして挿入される{@@link CustodianParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CustodianRow.TYPE );
    }


  /** 
   * {@@link CustodianRow}を一意に特定する{@@link CustodianPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CustodianRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CustodianParams}オブジェクトの主キーとして利用可能な{@@link CustodianPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CustodianPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CustodianRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_custodianCode 検索対象であるp_custodianCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CustodianRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CustodianRow findRowByPk( String p_institutionCode, String p_custodianCode ) throws DataFindException, DataQueryException, DataNetworkException {
        CustodianPK pk = new CustodianPK( p_institutionCode, p_custodianCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCustodianPKオブジェクトから{@@link CustodianRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCustodianPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CustodianRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CustodianRow findRowByPk( CustodianPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CustodianRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(CustodianRow)}を使用してください。 
   */
    public static CustodianDao findDaoByPk( String p_institutionCode, String p_custodianCode ) throws DataFindException, DataQueryException, DataNetworkException {
        CustodianPK pk = new CustodianPK( p_institutionCode, p_custodianCode );
        CustodianRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CustodianPK)}および{@@link #forRow(CustodianRow)}を使用してください。 
   */
    public static CustodianDao findDaoByPk( CustodianPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CustodianRow row = findRowByPk( pk );
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
   * p_institutionCode, p_custodianCode, and にて指定の値から一意の{@@link CustodianRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_custodianCode 検索対象であるp_custodianCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_custodianCode, and の値と一致する{@@link CustodianRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CustodianRow findRowByInstitutionCodeCustodianCode( String p_institutionCode, String p_custodianCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CustodianRow.TYPE,
            "institution_code=? and custodian_code=?",
            null,
            new Object[] { p_institutionCode, p_custodianCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CustodianRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CustodianDao.findRowsByInstitutionCodeCustodianCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeCustodianCode(String, String)}および{@@link #forRow(CustodianRow)}を使用してください。 
   */
    public static CustodianDao findDaoByInstitutionCodeCustodianCode( String p_institutionCode, String p_custodianCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCustodianCode( p_institutionCode, p_custodianCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
