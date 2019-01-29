head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FinInstitutionDao.java;


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
 * {@@link FinInstitutionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FinInstitutionRow}インスタンスへ関連付けることができます。 
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
 * @@see FinInstitutionPK 
 * @@see FinInstitutionRow 
 */
public class FinInstitutionDao extends DataAccessObject {


  /** 
   * この{@@link FinInstitutionDao}に関連する型指定のRowオブジェクト 
   */
    private FinInstitutionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FinInstitutionRow}と仮定される{@@link DataAccessObject}から新たに{@@link FinInstitutionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FinInstitutionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FinInstitutionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FinInstitutionRow )
                return new FinInstitutionDao( (FinInstitutionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FinInstitutionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FinInstitutionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FinInstitutionRow}オブジェクト 
    */
    protected FinInstitutionDao( FinInstitutionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FinInstitutionRow}オブジェクトを取得します。
   */
    public FinInstitutionRow getFinInstitutionRow() {
        return row;
    }


  /** 
   * 指定の{@@link FinInstitutionRow}オブジェクトから{@@link FinInstitutionDao}オブジェクトを作成します。 
   * これは実際の{@@link FinInstitutionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FinInstitutionDao}取得のために指定の{@@link FinInstitutionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FinInstitutionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FinInstitutionDao forRow( FinInstitutionRow row ) throws java.lang.IllegalArgumentException {
        return (FinInstitutionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FinInstitutionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FinInstitutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FinInstitutionPK}やデータベースレコードとして挿入される{@@link FinInstitutionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FinInstitutionRow.TYPE );
    }


  /** 
   * {@@link FinInstitutionRow}を一意に特定する{@@link FinInstitutionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FinInstitutionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FinInstitutionParams}オブジェクトの主キーとして利用可能な{@@link FinInstitutionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FinInstitutionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FinInstitutionRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FinInstitutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FinInstitutionRow findRowByPk( String p_institutionCode, String p_finInstitutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FinInstitutionPK pk = new FinInstitutionPK( p_institutionCode, p_finInstitutionCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFinInstitutionPKオブジェクトから{@@link FinInstitutionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFinInstitutionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FinInstitutionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FinInstitutionRow findRowByPk( FinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FinInstitutionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(FinInstitutionRow)}を使用してください。 
   */
    public static FinInstitutionDao findDaoByPk( String p_institutionCode, String p_finInstitutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FinInstitutionPK pk = new FinInstitutionPK( p_institutionCode, p_finInstitutionCode );
        FinInstitutionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FinInstitutionPK)}および{@@link #forRow(FinInstitutionRow)}を使用してください。 
   */
    public static FinInstitutionDao findDaoByPk( FinInstitutionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FinInstitutionRow row = findRowByPk( pk );
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
   * p_institutionCode, p_finInstitutionCode, and にて指定の値から一意の{@@link FinInstitutionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_finInstitutionCode 検索対象であるp_finInstitutionCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_finInstitutionCode, and の値と一致する{@@link FinInstitutionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FinInstitutionRow findRowByInstitutionCodeFinInstitutionCode( String p_institutionCode, String p_finInstitutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FinInstitutionRow.TYPE,
            "institution_code=? and fin_institution_code=?",
            null,
            new Object[] { p_institutionCode, p_finInstitutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FinInstitutionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FinInstitutionDao.findRowsByInstitutionCodeFinInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeFinInstitutionCode(String, String)}および{@@link #forRow(FinInstitutionRow)}を使用してください。 
   */
    public static FinInstitutionDao findDaoByInstitutionCodeFinInstitutionCode( String p_institutionCode, String p_finInstitutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeFinInstitutionCode( p_institutionCode, p_finInstitutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
