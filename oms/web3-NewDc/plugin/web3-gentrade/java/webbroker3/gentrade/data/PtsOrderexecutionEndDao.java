head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	PtsOrderexecutionEndDao.java;


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
 * {@@link PtsOrderexecutionEndDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PtsOrderexecutionEndRow}インスタンスへ関連付けることができます。 
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
 * @@see PtsOrderexecutionEndPK 
 * @@see PtsOrderexecutionEndRow 
 */
public class PtsOrderexecutionEndDao extends DataAccessObject {


  /** 
   * この{@@link PtsOrderexecutionEndDao}に関連する型指定のRowオブジェクト 
   */
    private PtsOrderexecutionEndRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PtsOrderexecutionEndRow}と仮定される{@@link DataAccessObject}から新たに{@@link PtsOrderexecutionEndDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PtsOrderexecutionEndDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PtsOrderexecutionEndRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PtsOrderexecutionEndRow )
                return new PtsOrderexecutionEndDao( (PtsOrderexecutionEndRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PtsOrderexecutionEndRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PtsOrderexecutionEndRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PtsOrderexecutionEndRow}オブジェクト 
    */
    protected PtsOrderexecutionEndDao( PtsOrderexecutionEndRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PtsOrderexecutionEndRow}オブジェクトを取得します。
   */
    public PtsOrderexecutionEndRow getPtsOrderexecutionEndRow() {
        return row;
    }


  /** 
   * 指定の{@@link PtsOrderexecutionEndRow}オブジェクトから{@@link PtsOrderexecutionEndDao}オブジェクトを作成します。 
   * これは実際の{@@link PtsOrderexecutionEndRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PtsOrderexecutionEndDao}取得のために指定の{@@link PtsOrderexecutionEndRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PtsOrderexecutionEndDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PtsOrderexecutionEndDao forRow( PtsOrderexecutionEndRow row ) throws java.lang.IllegalArgumentException {
        return (PtsOrderexecutionEndDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PtsOrderexecutionEndRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PtsOrderexecutionEndRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PtsOrderexecutionEndPK}やデータベースレコードとして挿入される{@@link PtsOrderexecutionEndParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PtsOrderexecutionEndRow.TYPE );
    }


  /** 
   * {@@link PtsOrderexecutionEndRow}を一意に特定する{@@link PtsOrderexecutionEndPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PtsOrderexecutionEndRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PtsOrderexecutionEndParams}オブジェクトの主キーとして利用可能な{@@link PtsOrderexecutionEndPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PtsOrderexecutionEndPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PtsOrderexecutionEndRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PtsOrderexecutionEndRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PtsOrderexecutionEndRow findRowByPk( String p_institutionCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        PtsOrderexecutionEndPK pk = new PtsOrderexecutionEndPK( p_institutionCode, p_marketCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPtsOrderexecutionEndPKオブジェクトから{@@link PtsOrderexecutionEndRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPtsOrderexecutionEndPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PtsOrderexecutionEndRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PtsOrderexecutionEndRow findRowByPk( PtsOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PtsOrderexecutionEndRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(PtsOrderexecutionEndRow)}を使用してください。 
   */
    public static PtsOrderexecutionEndDao findDaoByPk( String p_institutionCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        PtsOrderexecutionEndPK pk = new PtsOrderexecutionEndPK( p_institutionCode, p_marketCode );
        PtsOrderexecutionEndRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PtsOrderexecutionEndPK)}および{@@link #forRow(PtsOrderexecutionEndRow)}を使用してください。 
   */
    public static PtsOrderexecutionEndDao findDaoByPk( PtsOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PtsOrderexecutionEndRow row = findRowByPk( pk );
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
   * p_institutionCode, p_marketCode, and にて指定の値から一意の{@@link PtsOrderexecutionEndRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_marketCode, and の値と一致する{@@link PtsOrderexecutionEndRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PtsOrderexecutionEndRow findRowByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PtsOrderexecutionEndRow.TYPE,
            "institution_code=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_marketCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PtsOrderexecutionEndRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PtsOrderexecutionEndDao.findRowsByInstitutionCodeMarketCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeMarketCode(String, String)}および{@@link #forRow(PtsOrderexecutionEndRow)}を使用してください。 
   */
    public static PtsOrderexecutionEndDao findDaoByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCode( p_institutionCode, p_marketCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
