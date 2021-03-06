head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeOrderHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link HostEqtypeOrderHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostEqtypeOrderHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see HostEqtypeOrderHistoryPK 
 * @@see HostEqtypeOrderHistoryRow 
 */
public class HostEqtypeOrderHistoryDao extends DataAccessObject {


  /** 
   * この{@@link HostEqtypeOrderHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private HostEqtypeOrderHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostEqtypeOrderHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostEqtypeOrderHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostEqtypeOrderHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostEqtypeOrderHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEqtypeOrderHistoryRow )
                return new HostEqtypeOrderHistoryDao( (HostEqtypeOrderHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEqtypeOrderHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEqtypeOrderHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostEqtypeOrderHistoryRow}オブジェクト 
    */
    protected HostEqtypeOrderHistoryDao( HostEqtypeOrderHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostEqtypeOrderHistoryRow}オブジェクトを取得します。
   */
    public HostEqtypeOrderHistoryRow getHostEqtypeOrderHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostEqtypeOrderHistoryRow}オブジェクトから{@@link HostEqtypeOrderHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link HostEqtypeOrderHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostEqtypeOrderHistoryDao}取得のために指定の{@@link HostEqtypeOrderHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostEqtypeOrderHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostEqtypeOrderHistoryDao forRow( HostEqtypeOrderHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (HostEqtypeOrderHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEqtypeOrderHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostEqtypeOrderHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostEqtypeOrderHistoryPK}やデータベースレコードとして挿入される{@@link HostEqtypeOrderHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEqtypeOrderHistoryRow.TYPE );
    }


  /** 
   * {@@link HostEqtypeOrderHistoryRow}を一意に特定する{@@link HostEqtypeOrderHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostEqtypeOrderHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostEqtypeOrderHistoryParams}オブジェクトの主キーとして利用可能な{@@link HostEqtypeOrderHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostEqtypeOrderHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostEqtypeOrderHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeOrderHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeOrderHistoryRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderHistoryPK pk = new HostEqtypeOrderHistoryPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostEqtypeOrderHistoryPKオブジェクトから{@@link HostEqtypeOrderHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostEqtypeOrderHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEqtypeOrderHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEqtypeOrderHistoryRow findRowByPk( HostEqtypeOrderHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEqtypeOrderHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostEqtypeOrderHistoryRow)}を使用してください。 
   */
    public static HostEqtypeOrderHistoryDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderHistoryPK pk = new HostEqtypeOrderHistoryPK( p_rowid );
        HostEqtypeOrderHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostEqtypeOrderHistoryPK)}および{@@link #forRow(HostEqtypeOrderHistoryRow)}を使用してください。 
   */
    public static HostEqtypeOrderHistoryDao findDaoByPk( HostEqtypeOrderHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderHistoryRow row = findRowByPk( pk );
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
   * p_status, p_institutionCode, and にて指定の値に一致する{@@link HostEqtypeOrderHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_status, p_institutionCode, and の値と一致する{@@link HostEqtypeOrderHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatusInstitutionCode( String p_status, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderHistoryRow.TYPE,
            "status=? and institution_code=?",
            null,
            new Object[] { p_status, p_institutionCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatusInstitutionCode(String, String)}および{@@link #forRow(HostEqtypeOrderHistoryRow)}を使用してください。 
   */
    public static List findDaosByStatusInstitutionCode( String p_status, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusInstitutionCode( p_status, p_institutionCode ) );
    }


  /** 
   * p_corpCode, and にて指定の値に一致する{@@link HostEqtypeOrderHistoryRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_corpCode 検索対象であるp_corpCodeフィールドの値
   * 
   * @@return 引数指定のp_corpCode, and の値と一致する{@@link HostEqtypeOrderHistoryRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderHistoryRow.TYPE,
            "corp_code=?",
            null,
            new Object[] { p_corpCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByCorpCode(String)}および{@@link #forRow(HostEqtypeOrderHistoryRow)}を使用してください。 
   */
    public static List findDaosByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCorpCode( p_corpCode ) );
    }

}
@
