head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoOrderExecSendMailDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link IfoOrderExecSendMailDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoOrderExecSendMailRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoOrderExecSendMailPK 
 * @@see IfoOrderExecSendMailRow 
 */
public class IfoOrderExecSendMailDao extends DataAccessObject {


  /** 
   * この{@@link IfoOrderExecSendMailDao}に関連する型指定のRowオブジェクト 
   */
    private IfoOrderExecSendMailRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoOrderExecSendMailRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoOrderExecSendMailDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoOrderExecSendMailDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoOrderExecSendMailRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoOrderExecSendMailRow )
                return new IfoOrderExecSendMailDao( (IfoOrderExecSendMailRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoOrderExecSendMailRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoOrderExecSendMailRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoOrderExecSendMailRow}オブジェクト 
    */
    protected IfoOrderExecSendMailDao( IfoOrderExecSendMailRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoOrderExecSendMailRow}オブジェクトを取得します。
   */
    public IfoOrderExecSendMailRow getIfoOrderExecSendMailRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoOrderExecSendMailRow}オブジェクトから{@@link IfoOrderExecSendMailDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoOrderExecSendMailRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoOrderExecSendMailDao}取得のために指定の{@@link IfoOrderExecSendMailRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoOrderExecSendMailDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoOrderExecSendMailDao forRow( IfoOrderExecSendMailRow row ) throws java.lang.IllegalArgumentException {
        return (IfoOrderExecSendMailDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoOrderExecSendMailRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoOrderExecSendMailRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoOrderExecSendMailPK}やデータベースレコードとして挿入される{@@link IfoOrderExecSendMailParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoOrderExecSendMailRow.TYPE );
    }


  /** 
   * {@@link IfoOrderExecSendMailRow}を一意に特定する{@@link IfoOrderExecSendMailPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoOrderExecSendMailRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoOrderExecSendMailParams}オブジェクトの主キーとして利用可能な{@@link IfoOrderExecSendMailPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoOrderExecSendMailPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoOrderExecSendMailRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_orderExecStatus 検索対象であるp_orderExecStatusフィールドの値
   * @@param p_orderActionId 検索対象であるp_orderActionIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoOrderExecSendMailRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoOrderExecSendMailRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber, String p_orderExecStatus, long p_orderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderExecSendMailPK pk = new IfoOrderExecSendMailPK( p_institutionCode, p_branchCode, p_orderRequestNumber, p_orderExecStatus, p_orderActionId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoOrderExecSendMailPKオブジェクトから{@@link IfoOrderExecSendMailRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoOrderExecSendMailPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoOrderExecSendMailRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoOrderExecSendMailRow findRowByPk( IfoOrderExecSendMailPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoOrderExecSendMailRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,long)}および{@@link #forRow(IfoOrderExecSendMailRow)}を使用してください。 
   */
    public static IfoOrderExecSendMailDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber, String p_orderExecStatus, long p_orderActionId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderExecSendMailPK pk = new IfoOrderExecSendMailPK( p_institutionCode, p_branchCode, p_orderRequestNumber, p_orderExecStatus, p_orderActionId );
        IfoOrderExecSendMailRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoOrderExecSendMailPK)}および{@@link #forRow(IfoOrderExecSendMailRow)}を使用してください。 
   */
    public static IfoOrderExecSendMailDao findDaoByPk( IfoOrderExecSendMailPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderExecSendMailRow row = findRowByPk( pk );
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
   * p_status, and にて指定の値に一致する{@@link IfoOrderExecSendMailRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_status, and の値と一致する{@@link IfoOrderExecSendMailRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            IfoOrderExecSendMailRow.TYPE,
            "status=?",
            null,
            new Object[] { p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatus(String)}および{@@link #forRow(IfoOrderExecSendMailRow)}を使用してください。 
   */
    public static List findDaosByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatus( p_status ) );
    }

}
@
