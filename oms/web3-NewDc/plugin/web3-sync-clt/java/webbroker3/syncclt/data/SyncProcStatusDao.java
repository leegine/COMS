head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.26.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d7db4ef1a89;
filename	SyncProcStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.syncclt.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.syncclt.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link SyncProcStatusDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SyncProcStatusRow}インスタンスへ関連付けることができます。 
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
 * @@see SyncProcStatusPK 
 * @@see SyncProcStatusRow 
 */
public class SyncProcStatusDao extends DataAccessObject {


  /** 
   * この{@@link SyncProcStatusDao}に関連する型指定のRowオブジェクト 
   */
    private SyncProcStatusRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SyncProcStatusRow}と仮定される{@@link DataAccessObject}から新たに{@@link SyncProcStatusDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SyncProcStatusDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SyncProcStatusRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SyncProcStatusRow )
                return new SyncProcStatusDao( (SyncProcStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SyncProcStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SyncProcStatusRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SyncProcStatusRow}オブジェクト 
    */
    protected SyncProcStatusDao( SyncProcStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SyncProcStatusRow}オブジェクトを取得します。
   */
    public SyncProcStatusRow getSyncProcStatusRow() {
        return row;
    }


  /** 
   * 指定の{@@link SyncProcStatusRow}オブジェクトから{@@link SyncProcStatusDao}オブジェクトを作成します。 
   * これは実際の{@@link SyncProcStatusRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SyncProcStatusDao}取得のために指定の{@@link SyncProcStatusRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SyncProcStatusDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SyncProcStatusDao forRow( SyncProcStatusRow row ) throws java.lang.IllegalArgumentException {
        return (SyncProcStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SyncProcStatusRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SyncProcStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SyncProcStatusPK}やデータベースレコードとして挿入される{@@link SyncProcStatusParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SyncProcStatusRow.TYPE );
    }


  /** 
   * {@@link SyncProcStatusRow}を一意に特定する{@@link SyncProcStatusPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SyncProcStatusRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SyncProcStatusParams}オブジェクトの主キーとして利用可能な{@@link SyncProcStatusPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SyncProcStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SyncProcStatusRow}オブジェクトを検索します。 
   * 
   * @@param p_serviceName 検索対象であるp_serviceNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SyncProcStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SyncProcStatusRow findRowByPk( String p_serviceName ) throws DataFindException, DataQueryException, DataNetworkException {
        SyncProcStatusPK pk = new SyncProcStatusPK( p_serviceName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSyncProcStatusPKオブジェクトから{@@link SyncProcStatusRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSyncProcStatusPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SyncProcStatusRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SyncProcStatusRow findRowByPk( SyncProcStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SyncProcStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(SyncProcStatusRow)}を使用してください。 
   */
    public static SyncProcStatusDao findDaoByPk( String p_serviceName ) throws DataFindException, DataQueryException, DataNetworkException {
        SyncProcStatusPK pk = new SyncProcStatusPK( p_serviceName );
        SyncProcStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SyncProcStatusPK)}および{@@link #forRow(SyncProcStatusRow)}を使用してください。 
   */
    public static SyncProcStatusDao findDaoByPk( SyncProcStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SyncProcStatusRow row = findRowByPk( pk );
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
   * p_serviceName, and にて指定の値から一意の{@@link SyncProcStatusRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_serviceName 検索対象であるp_serviceNameフィールドの値
   * 
   * @@return 引数指定のp_serviceName, and の値と一致する{@@link SyncProcStatusRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SyncProcStatusRow findRowByServiceName( String p_serviceName ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SyncProcStatusRow.TYPE,
            "service_name=?",
            null,
            new Object[] { p_serviceName } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SyncProcStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SyncProcStatusDao.findRowsByServiceName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByServiceName(String)}および{@@link #forRow(SyncProcStatusRow)}を使用してください。 
   */
    public static SyncProcStatusDao findDaoByServiceName( String p_serviceName ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByServiceName( p_serviceName ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
