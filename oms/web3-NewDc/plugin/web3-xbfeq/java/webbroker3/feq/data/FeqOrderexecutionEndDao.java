head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqOrderexecutionEndDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FeqOrderexecutionEndDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link FeqOrderexecutionEndRow}インスタンスへ関連付けることができます。 
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
 * @@see FeqOrderexecutionEndPK 
 * @@see FeqOrderexecutionEndRow 
 */
public class FeqOrderexecutionEndDao extends DataAccessObject {


  /** 
   * この{@@link FeqOrderexecutionEndDao}に関連する型指定のRowオブジェクト 
   */
    private FeqOrderexecutionEndRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link FeqOrderexecutionEndRow}と仮定される{@@link DataAccessObject}から新たに{@@link FeqOrderexecutionEndDao}を返します。 
         * @@return 指定のRowに結びつく{@@link FeqOrderexecutionEndDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link FeqOrderexecutionEndRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqOrderexecutionEndRow )
                return new FeqOrderexecutionEndDao( (FeqOrderexecutionEndRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqOrderexecutionEndRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqOrderexecutionEndRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link FeqOrderexecutionEndRow}オブジェクト 
    */
    protected FeqOrderexecutionEndDao( FeqOrderexecutionEndRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link FeqOrderexecutionEndRow}オブジェクトを取得します。
   */
    public FeqOrderexecutionEndRow getFeqOrderexecutionEndRow() {
        return row;
    }


  /** 
   * 指定の{@@link FeqOrderexecutionEndRow}オブジェクトから{@@link FeqOrderexecutionEndDao}オブジェクトを作成します。 
   * これは実際の{@@link FeqOrderexecutionEndRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link FeqOrderexecutionEndDao}取得のために指定の{@@link FeqOrderexecutionEndRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link FeqOrderexecutionEndDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static FeqOrderexecutionEndDao forRow( FeqOrderexecutionEndRow row ) throws java.lang.IllegalArgumentException {
        return (FeqOrderexecutionEndDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqOrderexecutionEndRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link FeqOrderexecutionEndRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link FeqOrderexecutionEndPK}やデータベースレコードとして挿入される{@@link FeqOrderexecutionEndParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqOrderexecutionEndRow.TYPE );
    }


  /** 
   * {@@link FeqOrderexecutionEndRow}を一意に特定する{@@link FeqOrderexecutionEndPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link FeqOrderexecutionEndRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link FeqOrderexecutionEndParams}オブジェクトの主キーとして利用可能な{@@link FeqOrderexecutionEndPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static FeqOrderexecutionEndPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link FeqOrderexecutionEndRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqOrderexecutionEndRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqOrderexecutionEndRow findRowByPk( String p_institutionCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderexecutionEndPK pk = new FeqOrderexecutionEndPK( p_institutionCode, p_marketCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のFeqOrderexecutionEndPKオブジェクトから{@@link FeqOrderexecutionEndRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するFeqOrderexecutionEndPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link FeqOrderexecutionEndRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static FeqOrderexecutionEndRow findRowByPk( FeqOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqOrderexecutionEndRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(FeqOrderexecutionEndRow)}を使用してください。 
   */
    public static FeqOrderexecutionEndDao findDaoByPk( String p_institutionCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderexecutionEndPK pk = new FeqOrderexecutionEndPK( p_institutionCode, p_marketCode );
        FeqOrderexecutionEndRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(FeqOrderexecutionEndPK)}および{@@link #forRow(FeqOrderexecutionEndRow)}を使用してください。 
   */
    public static FeqOrderexecutionEndDao findDaoByPk( FeqOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderexecutionEndRow row = findRowByPk( pk );
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
   * p_institutionCode, p_marketCode, and にて指定の値から一意の{@@link FeqOrderexecutionEndRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_marketCode, and の値と一致する{@@link FeqOrderexecutionEndRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static FeqOrderexecutionEndRow findRowByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderexecutionEndRow.TYPE,
            "institution_code=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_marketCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderexecutionEndRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderexecutionEndDao.findRowsByInstitutionCodeMarketCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeMarketCode(String, String)}および{@@link #forRow(FeqOrderexecutionEndRow)}を使用してください。 
   */
    public static FeqOrderexecutionEndDao findDaoByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCode( p_institutionCode, p_marketCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
