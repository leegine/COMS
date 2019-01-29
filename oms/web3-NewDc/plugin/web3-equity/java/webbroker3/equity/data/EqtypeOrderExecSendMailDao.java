head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EqtypeOrderExecSendMailDao.java;


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
 * {@@link EqtypeOrderExecSendMailDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link EqtypeOrderExecSendMailRow}インスタンスへ関連付けることができます。 
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
 * @@see EqtypeOrderExecSendMailPK 
 * @@see EqtypeOrderExecSendMailRow 
 */
public class EqtypeOrderExecSendMailDao extends DataAccessObject {


  /** 
   * この{@@link EqtypeOrderExecSendMailDao}に関連する型指定のRowオブジェクト 
   */
    private EqtypeOrderExecSendMailRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link EqtypeOrderExecSendMailRow}と仮定される{@@link DataAccessObject}から新たに{@@link EqtypeOrderExecSendMailDao}を返します。 
         * @@return 指定のRowに結びつく{@@link EqtypeOrderExecSendMailDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link EqtypeOrderExecSendMailRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EqtypeOrderExecSendMailRow )
                return new EqtypeOrderExecSendMailDao( (EqtypeOrderExecSendMailRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EqtypeOrderExecSendMailRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EqtypeOrderExecSendMailRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link EqtypeOrderExecSendMailRow}オブジェクト 
    */
    protected EqtypeOrderExecSendMailDao( EqtypeOrderExecSendMailRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link EqtypeOrderExecSendMailRow}オブジェクトを取得します。
   */
    public EqtypeOrderExecSendMailRow getEqtypeOrderExecSendMailRow() {
        return row;
    }


  /** 
   * 指定の{@@link EqtypeOrderExecSendMailRow}オブジェクトから{@@link EqtypeOrderExecSendMailDao}オブジェクトを作成します。 
   * これは実際の{@@link EqtypeOrderExecSendMailRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link EqtypeOrderExecSendMailDao}取得のために指定の{@@link EqtypeOrderExecSendMailRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link EqtypeOrderExecSendMailDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static EqtypeOrderExecSendMailDao forRow( EqtypeOrderExecSendMailRow row ) throws java.lang.IllegalArgumentException {
        return (EqtypeOrderExecSendMailDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EqtypeOrderExecSendMailRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link EqtypeOrderExecSendMailRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link EqtypeOrderExecSendMailPK}やデータベースレコードとして挿入される{@@link EqtypeOrderExecSendMailParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EqtypeOrderExecSendMailRow.TYPE );
    }


  /** 
   * {@@link EqtypeOrderExecSendMailRow}を一意に特定する{@@link EqtypeOrderExecSendMailPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link EqtypeOrderExecSendMailRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link EqtypeOrderExecSendMailParams}オブジェクトの主キーとして利用可能な{@@link EqtypeOrderExecSendMailPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static EqtypeOrderExecSendMailPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link EqtypeOrderExecSendMailRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_accountCode 検索対象であるp_accountCodeフィールドの値
   * @@param p_orderRequestNumber 検索対象であるp_orderRequestNumberフィールドの値
   * @@param p_orderActionId 検索対象であるp_orderActionIdフィールドの値
   * @@param p_orderExecStatus 検索対象であるp_orderExecStatusフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeOrderExecSendMailRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeOrderExecSendMailRow findRowByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber, long p_orderActionId, String p_orderExecStatus ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecSendMailPK pk = new EqtypeOrderExecSendMailPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, p_orderActionId, p_orderExecStatus );
        return findRowByPk( pk );
    }


  /** 
   * 指定のEqtypeOrderExecSendMailPKオブジェクトから{@@link EqtypeOrderExecSendMailRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するEqtypeOrderExecSendMailPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link EqtypeOrderExecSendMailRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static EqtypeOrderExecSendMailRow findRowByPk( EqtypeOrderExecSendMailPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeOrderExecSendMailRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,long,String)}および{@@link #forRow(EqtypeOrderExecSendMailRow)}を使用してください。 
   */
    public static EqtypeOrderExecSendMailDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_accountCode, String p_orderRequestNumber, long p_orderActionId, String p_orderExecStatus ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecSendMailPK pk = new EqtypeOrderExecSendMailPK( p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber, p_orderActionId, p_orderExecStatus );
        EqtypeOrderExecSendMailRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(EqtypeOrderExecSendMailPK)}および{@@link #forRow(EqtypeOrderExecSendMailRow)}を使用してください。 
   */
    public static EqtypeOrderExecSendMailDao findDaoByPk( EqtypeOrderExecSendMailPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EqtypeOrderExecSendMailRow row = findRowByPk( pk );
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
   * p_status, and にて指定の値に一致する{@@link EqtypeOrderExecSendMailRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_status 検索対象であるp_statusフィールドの値
   * 
   * @@return 引数指定のp_status, and の値と一致する{@@link EqtypeOrderExecSendMailRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            EqtypeOrderExecSendMailRow.TYPE,
            "status=?",
            null,
            new Object[] { p_status } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByStatus(String)}および{@@link #forRow(EqtypeOrderExecSendMailRow)}を使用してください。 
   */
    public static List findDaosByStatus( String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatus( p_status ) );
    }

}
@
