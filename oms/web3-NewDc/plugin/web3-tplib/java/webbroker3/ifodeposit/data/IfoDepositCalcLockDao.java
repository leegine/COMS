head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcLockDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifodeposit.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link IfoDepositCalcLockDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoDepositCalcLockRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoDepositCalcLockPK 
 * @@see IfoDepositCalcLockRow 
 */
public class IfoDepositCalcLockDao extends DataAccessObject {


  /** 
   * この{@@link IfoDepositCalcLockDao}に関連する型指定のRowオブジェクト 
   */
    private IfoDepositCalcLockRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoDepositCalcLockRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoDepositCalcLockDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoDepositCalcLockDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoDepositCalcLockRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoDepositCalcLockRow )
                return new IfoDepositCalcLockDao( (IfoDepositCalcLockRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoDepositCalcLockRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoDepositCalcLockRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoDepositCalcLockRow}オブジェクト 
    */
    protected IfoDepositCalcLockDao( IfoDepositCalcLockRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoDepositCalcLockRow}オブジェクトを取得します。
   */
    public IfoDepositCalcLockRow getIfoDepositCalcLockRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoDepositCalcLockRow}オブジェクトから{@@link IfoDepositCalcLockDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoDepositCalcLockRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoDepositCalcLockDao}取得のために指定の{@@link IfoDepositCalcLockRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoDepositCalcLockDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoDepositCalcLockDao forRow( IfoDepositCalcLockRow row ) throws java.lang.IllegalArgumentException {
        return (IfoDepositCalcLockDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoDepositCalcLockRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoDepositCalcLockRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoDepositCalcLockPK}やデータベースレコードとして挿入される{@@link IfoDepositCalcLockParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoDepositCalcLockRow.TYPE );
    }


  /** 
   * {@@link IfoDepositCalcLockRow}を一意に特定する{@@link IfoDepositCalcLockPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoDepositCalcLockRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoDepositCalcLockParams}オブジェクトの主キーとして利用可能な{@@link IfoDepositCalcLockPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoDepositCalcLockPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoDepositCalcLockRow}オブジェクトを検索します。 
   * 
   * @@param p_serviceName 検索対象であるp_serviceNameフィールドの値
   * @@param p_threadNo 検索対象であるp_threadNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoDepositCalcLockRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoDepositCalcLockRow findRowByPk( String p_serviceName, long p_threadNo ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcLockPK pk = new IfoDepositCalcLockPK( p_serviceName, p_threadNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoDepositCalcLockPKオブジェクトから{@@link IfoDepositCalcLockRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoDepositCalcLockPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoDepositCalcLockRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoDepositCalcLockRow findRowByPk( IfoDepositCalcLockPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoDepositCalcLockRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,long)}および{@@link #forRow(IfoDepositCalcLockRow)}を使用してください。 
   */
    public static IfoDepositCalcLockDao findDaoByPk( String p_serviceName, long p_threadNo ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcLockPK pk = new IfoDepositCalcLockPK( p_serviceName, p_threadNo );
        IfoDepositCalcLockRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoDepositCalcLockPK)}および{@@link #forRow(IfoDepositCalcLockRow)}を使用してください。 
   */
    public static IfoDepositCalcLockDao findDaoByPk( IfoDepositCalcLockPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcLockRow row = findRowByPk( pk );
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
