head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	PrimaryMarketCskDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quotecheck.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.quotecheck.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link PrimaryMarketCskDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link PrimaryMarketCskRow}インスタンスへ関連付けることができます。 
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
 * @@see PrimaryMarketCskPK 
 * @@see PrimaryMarketCskRow 
 */
public class PrimaryMarketCskDao extends DataAccessObject {


  /** 
   * この{@@link PrimaryMarketCskDao}に関連する型指定のRowオブジェクト 
   */
    private PrimaryMarketCskRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link PrimaryMarketCskRow}と仮定される{@@link DataAccessObject}から新たに{@@link PrimaryMarketCskDao}を返します。 
         * @@return 指定のRowに結びつく{@@link PrimaryMarketCskDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link PrimaryMarketCskRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PrimaryMarketCskRow )
                return new PrimaryMarketCskDao( (PrimaryMarketCskRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PrimaryMarketCskRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PrimaryMarketCskRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link PrimaryMarketCskRow}オブジェクト 
    */
    protected PrimaryMarketCskDao( PrimaryMarketCskRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link PrimaryMarketCskRow}オブジェクトを取得します。
   */
    public PrimaryMarketCskRow getPrimaryMarketCskRow() {
        return row;
    }


  /** 
   * 指定の{@@link PrimaryMarketCskRow}オブジェクトから{@@link PrimaryMarketCskDao}オブジェクトを作成します。 
   * これは実際の{@@link PrimaryMarketCskRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link PrimaryMarketCskDao}取得のために指定の{@@link PrimaryMarketCskRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link PrimaryMarketCskDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static PrimaryMarketCskDao forRow( PrimaryMarketCskRow row ) throws java.lang.IllegalArgumentException {
        return (PrimaryMarketCskDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PrimaryMarketCskRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link PrimaryMarketCskRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link PrimaryMarketCskPK}やデータベースレコードとして挿入される{@@link PrimaryMarketCskParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PrimaryMarketCskRow.TYPE );
    }


  /** 
   * {@@link PrimaryMarketCskRow}を一意に特定する{@@link PrimaryMarketCskPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link PrimaryMarketCskRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link PrimaryMarketCskParams}オブジェクトの主キーとして利用可能な{@@link PrimaryMarketCskPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static PrimaryMarketCskPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link PrimaryMarketCskRow}オブジェクトを検索します。 
   * 
   * @@param p_fundCode 検索対象であるp_fundCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PrimaryMarketCskRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PrimaryMarketCskRow findRowByPk( String p_fundCode ) throws DataFindException, DataQueryException, DataNetworkException {
        PrimaryMarketCskPK pk = new PrimaryMarketCskPK( p_fundCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のPrimaryMarketCskPKオブジェクトから{@@link PrimaryMarketCskRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するPrimaryMarketCskPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link PrimaryMarketCskRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static PrimaryMarketCskRow findRowByPk( PrimaryMarketCskPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PrimaryMarketCskRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(PrimaryMarketCskRow)}を使用してください。 
   */
    public static PrimaryMarketCskDao findDaoByPk( String p_fundCode ) throws DataFindException, DataQueryException, DataNetworkException {
        PrimaryMarketCskPK pk = new PrimaryMarketCskPK( p_fundCode );
        PrimaryMarketCskRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(PrimaryMarketCskPK)}および{@@link #forRow(PrimaryMarketCskRow)}を使用してください。 
   */
    public static PrimaryMarketCskDao findDaoByPk( PrimaryMarketCskPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PrimaryMarketCskRow row = findRowByPk( pk );
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
   * p_fundCode, and にて指定の値から一意の{@@link PrimaryMarketCskRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_fundCode 検索対象であるp_fundCodeフィールドの値
   * 
   * @@return 引数指定のp_fundCode, and の値と一致する{@@link PrimaryMarketCskRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static PrimaryMarketCskRow findRowByFundCode( String p_fundCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PrimaryMarketCskRow.TYPE,
            "fund_code=?",
            null,
            new Object[] { p_fundCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PrimaryMarketCskRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PrimaryMarketCskDao.findRowsByFundCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByFundCode(String)}および{@@link #forRow(PrimaryMarketCskRow)}を使用してください。 
   */
    public static PrimaryMarketCskDao findDaoByFundCode( String p_fundCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFundCode( p_fundCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_fundCode, p_primaryMarketCode, and にて指定の値に一致する{@@link PrimaryMarketCskRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_fundCode 検索対象であるp_fundCodeフィールドの値
   * @@param p_primaryMarketCode 検索対象であるp_primaryMarketCodeフィールドの値
   * 
   * @@return 引数指定のp_fundCode, p_primaryMarketCode, and の値と一致する{@@link PrimaryMarketCskRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByFundCodePrimaryMarketCode( String p_fundCode, String p_primaryMarketCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PrimaryMarketCskRow.TYPE,
            "fund_code=? and primary_market_code=?",
            null,
            new Object[] { p_fundCode, p_primaryMarketCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByFundCodePrimaryMarketCode(String, String)}および{@@link #forRow(PrimaryMarketCskRow)}を使用してください。 
   */
    public static List findDaosByFundCodePrimaryMarketCode( String p_fundCode, String p_primaryMarketCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByFundCodePrimaryMarketCode( p_fundCode, p_primaryMarketCode ) );
    }

}
@
