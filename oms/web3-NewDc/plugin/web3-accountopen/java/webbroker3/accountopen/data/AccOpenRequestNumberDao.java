head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenRequestNumberDao.java;


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
 * {@@link AccOpenRequestNumberDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccOpenRequestNumberRow}インスタンスへ関連付けることができます。 
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
 * @@see AccOpenRequestNumberPK 
 * @@see AccOpenRequestNumberRow 
 */
public class AccOpenRequestNumberDao extends DataAccessObject {


  /** 
   * この{@@link AccOpenRequestNumberDao}に関連する型指定のRowオブジェクト 
   */
    private AccOpenRequestNumberRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccOpenRequestNumberRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccOpenRequestNumberDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccOpenRequestNumberDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccOpenRequestNumberRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccOpenRequestNumberRow )
                return new AccOpenRequestNumberDao( (AccOpenRequestNumberRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccOpenRequestNumberRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccOpenRequestNumberRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccOpenRequestNumberRow}オブジェクト 
    */
    protected AccOpenRequestNumberDao( AccOpenRequestNumberRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccOpenRequestNumberRow}オブジェクトを取得します。
   */
    public AccOpenRequestNumberRow getAccOpenRequestNumberRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccOpenRequestNumberRow}オブジェクトから{@@link AccOpenRequestNumberDao}オブジェクトを作成します。 
   * これは実際の{@@link AccOpenRequestNumberRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccOpenRequestNumberDao}取得のために指定の{@@link AccOpenRequestNumberRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccOpenRequestNumberDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccOpenRequestNumberDao forRow( AccOpenRequestNumberRow row ) throws java.lang.IllegalArgumentException {
        return (AccOpenRequestNumberDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccOpenRequestNumberRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccOpenRequestNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccOpenRequestNumberPK}やデータベースレコードとして挿入される{@@link AccOpenRequestNumberParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccOpenRequestNumberRow.TYPE );
    }


  /** 
   * {@@link AccOpenRequestNumberRow}を一意に特定する{@@link AccOpenRequestNumberPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccOpenRequestNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccOpenRequestNumberParams}オブジェクトの主キーとして利用可能な{@@link AccOpenRequestNumberPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccOpenRequestNumberPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccOpenRequestNumberRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenRequestNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenRequestNumberRow findRowByPk( String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenRequestNumberPK pk = new AccOpenRequestNumberPK( p_institutionCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccOpenRequestNumberPKオブジェクトから{@@link AccOpenRequestNumberRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccOpenRequestNumberPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccOpenRequestNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccOpenRequestNumberRow findRowByPk( AccOpenRequestNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccOpenRequestNumberRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(AccOpenRequestNumberRow)}を使用してください。 
   */
    public static AccOpenRequestNumberDao findDaoByPk( String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenRequestNumberPK pk = new AccOpenRequestNumberPK( p_institutionCode );
        AccOpenRequestNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccOpenRequestNumberPK)}および{@@link #forRow(AccOpenRequestNumberRow)}を使用してください。 
   */
    public static AccOpenRequestNumberDao findDaoByPk( AccOpenRequestNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccOpenRequestNumberRow row = findRowByPk( pk );
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
   * p_institutionCode, and にて指定の値から一意の{@@link AccOpenRequestNumberRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, and の値と一致する{@@link AccOpenRequestNumberRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccOpenRequestNumberRow findRowByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccOpenRequestNumberRow.TYPE,
            "institution_code=?",
            null,
            new Object[] { p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccOpenRequestNumberRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccOpenRequestNumberDao.findRowsByInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCode(String)}および{@@link #forRow(AccOpenRequestNumberRow)}を使用してください。 
   */
    public static AccOpenRequestNumberDao findDaoByInstitutionCode( String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCode( p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
