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
filename	HostManagementDao.java;


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
 * {@@link HostManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see HostManagementPK 
 * @@see HostManagementRow 
 */
public class HostManagementDao extends DataAccessObject {


  /** 
   * この{@@link HostManagementDao}に関連する型指定のRowオブジェクト 
   */
    private HostManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostManagementRow )
                return new HostManagementDao( (HostManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostManagementRow}オブジェクト 
    */
    protected HostManagementDao( HostManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostManagementRow}オブジェクトを取得します。
   */
    public HostManagementRow getHostManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostManagementRow}オブジェクトから{@@link HostManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link HostManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostManagementDao}取得のために指定の{@@link HostManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostManagementDao forRow( HostManagementRow row ) throws java.lang.IllegalArgumentException {
        return (HostManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostManagementPK}やデータベースレコードとして挿入される{@@link HostManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostManagementRow.TYPE );
    }


  /** 
   * {@@link HostManagementRow}を一意に特定する{@@link HostManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostManagementParams}オブジェクトの主キーとして利用可能な{@@link HostManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_hostTableName 検索対象であるp_hostTableNameフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostManagementRow findRowByPk( String p_hostTableName ) throws DataFindException, DataQueryException, DataNetworkException {
        HostManagementPK pk = new HostManagementPK( p_hostTableName );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostManagementPKオブジェクトから{@@link HostManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostManagementRow findRowByPk( HostManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostManagementRow)}を使用してください。 
   */
    public static HostManagementDao findDaoByPk( String p_hostTableName ) throws DataFindException, DataQueryException, DataNetworkException {
        HostManagementPK pk = new HostManagementPK( p_hostTableName );
        HostManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostManagementPK)}および{@@link #forRow(HostManagementRow)}を使用してください。 
   */
    public static HostManagementDao findDaoByPk( HostManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostManagementRow row = findRowByPk( pk );
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

        // (none) 

}
@
