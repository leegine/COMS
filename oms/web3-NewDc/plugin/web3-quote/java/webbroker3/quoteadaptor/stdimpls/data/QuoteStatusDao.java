head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	QuoteStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.quoteadaptor.stdimpls.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link QuoteStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link QuoteStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see QuoteStatusPK 
 * @@see QuoteStatusRow 
 */
public class QuoteStatusDao extends DataAccessObject {


  /** 
   * この{@@link QuoteStatusDao}に関連する型指定のRowオブジェクト 
   */
    private QuoteStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link QuoteStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link QuoteStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link QuoteStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link QuoteStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QuoteStatusRow )
                return new QuoteStatusDao( (QuoteStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QuoteStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QuoteStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link QuoteStatusRow}オブジェクト 
    */
    protected QuoteStatusDao( QuoteStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link QuoteStatusRow}オブジェクトを取得します。
   */
    public QuoteStatusRow getQuoteStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link QuoteStatusRow}オブジェクトから{@@link QuoteStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link QuoteStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link QuoteStatusDao}取得のために指定の{@@link QuoteStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link QuoteStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static QuoteStatusDao forRow( QuoteStatusRow row ) throws java.lang.IllegalArgumentException {
        return (QuoteStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QuoteStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link QuoteStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link QuoteStatusPK}やデータベースレコードとして挿入される{@@link QuoteStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QuoteStatusRow.TYPE );
    }


  /** 
   * {@@link QuoteStatusRow}を一意に特定する{@@link QuoteStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link QuoteStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link QuoteStatusParams}オブジェクトの主キーとして利用可能な{@@link QuoteStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static QuoteStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link QuoteStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_hostName 検索対象であるp_hostNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QuoteStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QuoteStatusRow findRowByPk( String p_hostName ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteStatusPK pk = new QuoteStatusPK( p_hostName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のQuoteStatusPKオブジェクトから{@@link QuoteStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するQuoteStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link QuoteStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static QuoteStatusRow findRowByPk( QuoteStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QuoteStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(QuoteStatusRow)}を使用してください。 
   */
    public static QuoteStatusDao findDaoByPk( String p_hostName ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteStatusPK pk = new QuoteStatusPK( p_hostName );
        QuoteStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(QuoteStatusPK)}および{@@link #forRow(QuoteStatusRow)}を使用してください。 
   */
    public static QuoteStatusDao findDaoByPk( QuoteStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteStatusRow row = findRowByPk( pk );
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
   * p_hostName, and にて指定の値から一意の{@@link QuoteStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_hostName 検索対象であるp_hostNameフィールドの値
   * 
   * @@return 引数指定のp_hostName, and の値と一致する{@@link QuoteStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static QuoteStatusRow findRowByHostName( String p_hostName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QuoteStatusRow.TYPE,
            "host_name=?",
            null,
            new Object[] { p_hostName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QuoteStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QuoteStatusDao.findRowsByHostName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByHostName(String)}および{@@link #forRow(QuoteStatusRow)}を使用してください。 
   */
    public static QuoteStatusDao findDaoByHostName( String p_hostName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByHostName( p_hostName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
