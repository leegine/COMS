head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostOrderexecutionEndDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link HostOrderexecutionEndDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostOrderexecutionEndRow}インスタンスへ関連付けることができます。 
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
 * @@see HostOrderexecutionEndPK 
 * @@see HostOrderexecutionEndRow 
 */
public class HostOrderexecutionEndDao extends DataAccessObject {


  /** 
   * この{@@link HostOrderexecutionEndDao}に関連する型指定のRowオブジェクト 
   */
    private HostOrderexecutionEndRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostOrderexecutionEndRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostOrderexecutionEndDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostOrderexecutionEndDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostOrderexecutionEndRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostOrderexecutionEndRow )
                return new HostOrderexecutionEndDao( (HostOrderexecutionEndRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostOrderexecutionEndRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostOrderexecutionEndRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostOrderexecutionEndRow}オブジェクト 
    */
    protected HostOrderexecutionEndDao( HostOrderexecutionEndRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostOrderexecutionEndRow}オブジェクトを取得します。
   */
    public HostOrderexecutionEndRow getHostOrderexecutionEndRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostOrderexecutionEndRow}オブジェクトから{@@link HostOrderexecutionEndDao}オブジェクトを作成します。 
   * これは実際の{@@link HostOrderexecutionEndRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostOrderexecutionEndDao}取得のために指定の{@@link HostOrderexecutionEndRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostOrderexecutionEndDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostOrderexecutionEndDao forRow( HostOrderexecutionEndRow row ) throws java.lang.IllegalArgumentException {
        return (HostOrderexecutionEndDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostOrderexecutionEndRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostOrderexecutionEndRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostOrderexecutionEndPK}やデータベースレコードとして挿入される{@@link HostOrderexecutionEndParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostOrderexecutionEndRow.TYPE );
    }


  /** 
   * {@@link HostOrderexecutionEndRow}を一意に特定する{@@link HostOrderexecutionEndPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostOrderexecutionEndRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostOrderexecutionEndParams}オブジェクトの主キーとして利用可能な{@@link HostOrderexecutionEndPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostOrderexecutionEndPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostOrderexecutionEndRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostOrderexecutionEndRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostOrderexecutionEndRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOrderexecutionEndPK pk = new HostOrderexecutionEndPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostOrderexecutionEndPKオブジェクトから{@@link HostOrderexecutionEndRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostOrderexecutionEndPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostOrderexecutionEndRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostOrderexecutionEndRow findRowByPk( HostOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostOrderexecutionEndRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostOrderexecutionEndRow)}を使用してください。 
   */
    public static HostOrderexecutionEndDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOrderexecutionEndPK pk = new HostOrderexecutionEndPK( p_rowid );
        HostOrderexecutionEndRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostOrderexecutionEndPK)}および{@@link #forRow(HostOrderexecutionEndRow)}を使用してください。 
   */
    public static HostOrderexecutionEndDao findDaoByPk( HostOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOrderexecutionEndRow row = findRowByPk( pk );
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
   * p_requestCode, p_institutionCode, p_productType, p_futureOptionDiv, and にて指定の値に一致する{@@link HostOrderexecutionEndRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_requestCode 検索対象であるp_requestCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * @@param p_futureOptionDiv 検索対象であるp_futureOptionDivフィールドの値
   * 
   * @@return 引数指定のp_requestCode, p_institutionCode, p_productType, p_futureOptionDiv, and の値と一致する{@@link HostOrderexecutionEndRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRequestCodeInstitutionCodeProductTypeFutureOptionDiv( String p_requestCode, String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostOrderexecutionEndRow.TYPE,
            "request_code=? and institution_code=? and product_type=? and future_option_div=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_productType, p_futureOptionDiv } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRequestCodeInstitutionCodeProductTypeFutureOptionDiv(String, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String)}および{@@link #forRow(HostOrderexecutionEndRow)}を使用してください。 
   */
    public static List findDaosByRequestCodeInstitutionCodeProductTypeFutureOptionDiv( String p_requestCode, String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeInstitutionCodeProductTypeFutureOptionDiv( p_requestCode, p_institutionCode, p_productType, p_futureOptionDiv ) );
    }

}
@
