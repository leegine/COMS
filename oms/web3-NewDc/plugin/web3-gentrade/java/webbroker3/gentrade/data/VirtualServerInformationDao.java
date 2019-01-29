head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerInformationDao.java;


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
 * {@@link VirtualServerInformationDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link VirtualServerInformationRow}インスタンスへ関連付けることができます。 
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
 * @@see VirtualServerInformationPK 
 * @@see VirtualServerInformationRow 
 */
public class VirtualServerInformationDao extends DataAccessObject {


  /** 
   * この{@@link VirtualServerInformationDao}に関連する型指定のRowオブジェクト 
   */
    private VirtualServerInformationRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link VirtualServerInformationRow}と仮定される{@@link DataAccessObject}から新たに{@@link VirtualServerInformationDao}を返します。 
         * @@return 指定のRowに結びつく{@@link VirtualServerInformationDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link VirtualServerInformationRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof VirtualServerInformationRow )
                return new VirtualServerInformationDao( (VirtualServerInformationRow) row );
            throw new java.lang.IllegalArgumentException( "Not a VirtualServerInformationRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link VirtualServerInformationRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link VirtualServerInformationRow}オブジェクト 
    */
    protected VirtualServerInformationDao( VirtualServerInformationRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link VirtualServerInformationRow}オブジェクトを取得します。
   */
    public VirtualServerInformationRow getVirtualServerInformationRow() {
        return row;
    }


  /** 
   * 指定の{@@link VirtualServerInformationRow}オブジェクトから{@@link VirtualServerInformationDao}オブジェクトを作成します。 
   * これは実際の{@@link VirtualServerInformationRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link VirtualServerInformationDao}取得のために指定の{@@link VirtualServerInformationRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link VirtualServerInformationDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static VirtualServerInformationDao forRow( VirtualServerInformationRow row ) throws java.lang.IllegalArgumentException {
        return (VirtualServerInformationDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link VirtualServerInformationRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link VirtualServerInformationRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link VirtualServerInformationPK}やデータベースレコードとして挿入される{@@link VirtualServerInformationParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( VirtualServerInformationRow.TYPE );
    }


  /** 
   * {@@link VirtualServerInformationRow}を一意に特定する{@@link VirtualServerInformationPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link VirtualServerInformationRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link VirtualServerInformationParams}オブジェクトの主キーとして利用可能な{@@link VirtualServerInformationPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static VirtualServerInformationPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link VirtualServerInformationRow}オブジェクトを検索します。 
   * 
   * @@param p_virtualServerNumberJsoes 検索対象であるp_virtualServerNumberJsoesフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link VirtualServerInformationRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static VirtualServerInformationRow findRowByPk( String p_virtualServerNumberJsoes ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerInformationPK pk = new VirtualServerInformationPK( p_virtualServerNumberJsoes );
        return findRowByPk( pk );
    }


  /** 
   * 指定のVirtualServerInformationPKオブジェクトから{@@link VirtualServerInformationRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するVirtualServerInformationPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link VirtualServerInformationRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static VirtualServerInformationRow findRowByPk( VirtualServerInformationPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (VirtualServerInformationRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(VirtualServerInformationRow)}を使用してください。 
   */
    public static VirtualServerInformationDao findDaoByPk( String p_virtualServerNumberJsoes ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerInformationPK pk = new VirtualServerInformationPK( p_virtualServerNumberJsoes );
        VirtualServerInformationRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(VirtualServerInformationPK)}および{@@link #forRow(VirtualServerInformationRow)}を使用してください。 
   */
    public static VirtualServerInformationDao findDaoByPk( VirtualServerInformationPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        VirtualServerInformationRow row = findRowByPk( pk );
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
   * p_virtualServerNumberJsoes, and にて指定の値に一致する{@@link VirtualServerInformationRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_virtualServerNumberJsoes 検索対象であるp_virtualServerNumberJsoesフィールドの値
   * 
   * @@return 引数指定のp_virtualServerNumberJsoes, and の値と一致する{@@link VirtualServerInformationRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByVirtualServerNumberJsoes( String p_virtualServerNumberJsoes ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            VirtualServerInformationRow.TYPE,
            "virtual_server_number_jsoes=?",
            null,
            new Object[] { p_virtualServerNumberJsoes } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByVirtualServerNumberJsoes(String)}および{@@link #forRow(VirtualServerInformationRow)}を使用してください。 
   */
    public static List findDaosByVirtualServerNumberJsoes( String p_virtualServerNumberJsoes ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByVirtualServerNumberJsoes( p_virtualServerNumberJsoes ) );
    }

}
@
