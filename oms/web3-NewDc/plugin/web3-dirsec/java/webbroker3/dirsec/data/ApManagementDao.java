head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ApManagementDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.dirsec.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link ApManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ApManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see ApManagementPK 
 * @@see ApManagementRow 
 */
public class ApManagementDao extends DataAccessObject {


  /** 
   * この{@@link ApManagementDao}に関連する型指定のRowオブジェクト 
   */
    private ApManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ApManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link ApManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ApManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ApManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ApManagementRow )
                return new ApManagementDao( (ApManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ApManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ApManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ApManagementRow}オブジェクト 
    */
    protected ApManagementDao( ApManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ApManagementRow}オブジェクトを取得します。
   */
    public ApManagementRow getApManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link ApManagementRow}オブジェクトから{@@link ApManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link ApManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ApManagementDao}取得のために指定の{@@link ApManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ApManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ApManagementDao forRow( ApManagementRow row ) throws java.lang.IllegalArgumentException {
        return (ApManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ApManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ApManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ApManagementPK}やデータベースレコードとして挿入される{@@link ApManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ApManagementRow.TYPE );
    }


  /** 
   * {@@link ApManagementRow}を一意に特定する{@@link ApManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ApManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ApManagementParams}オブジェクトの主キーとして利用可能な{@@link ApManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ApManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ApManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_ptype 検索対象であるp_ptypeフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ApManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ApManagementRow findRowByPk( String p_ptype, String p_requestCode ) throws DataFindException, DataQueryException, DataNetworkException {
        ApManagementPK pk = new ApManagementPK( p_ptype, p_requestCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のApManagementPKオブジェクトから{@@link ApManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するApManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ApManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ApManagementRow findRowByPk( ApManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ApManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(ApManagementRow)}を使用してください。 
   */
    public static ApManagementDao findDaoByPk( String p_ptype, String p_requestCode ) throws DataFindException, DataQueryException, DataNetworkException {
        ApManagementPK pk = new ApManagementPK( p_ptype, p_requestCode );
        ApManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ApManagementPK)}および{@@link #forRow(ApManagementRow)}を使用してください。 
   */
    public static ApManagementDao findDaoByPk( ApManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ApManagementRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_ptype, p_requestCode, and にて指定の値に一致する{@@link ApManagementRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_ptype 検索対象であるp_ptypeフィールドの値
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * 
   * @@return 引数指定のp_ptype, p_requestCode, and の値と一致する{@@link ApManagementRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByPtypeRequestCode( String p_ptype, String p_requestCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ApManagementRow.TYPE,
            "ptype=? and request_code=?",
            null,
            new Object[] { p_ptype, p_requestCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByPtypeRequestCode(String, String)}および{@@link #forRow(ApManagementRow)}を使用してください。 
   */
    public static List findDaosByPtypeRequestCode( String p_ptype, String p_requestCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByPtypeRequestCode( p_ptype, p_requestCode ) );
    }

}
@
