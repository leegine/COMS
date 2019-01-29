head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	IfoDepositCalcResultDao.java;


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
 * {@@link IfoDepositCalcResultDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link IfoDepositCalcResultRow}インスタンスへ関連付けることができます。 
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
 * @@see IfoDepositCalcResultPK 
 * @@see IfoDepositCalcResultRow 
 */
public class IfoDepositCalcResultDao extends DataAccessObject {


  /** 
   * この{@@link IfoDepositCalcResultDao}に関連する型指定のRowオブジェクト 
   */
    private IfoDepositCalcResultRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link IfoDepositCalcResultRow}と仮定される{@@link DataAccessObject}から新たに{@@link IfoDepositCalcResultDao}を返します。 
         * @@return 指定のRowに結びつく{@@link IfoDepositCalcResultDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link IfoDepositCalcResultRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoDepositCalcResultRow )
                return new IfoDepositCalcResultDao( (IfoDepositCalcResultRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoDepositCalcResultRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoDepositCalcResultRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link IfoDepositCalcResultRow}オブジェクト 
    */
    protected IfoDepositCalcResultDao( IfoDepositCalcResultRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link IfoDepositCalcResultRow}オブジェクトを取得します。
   */
    public IfoDepositCalcResultRow getIfoDepositCalcResultRow() {
        return row;
    }


  /** 
   * 指定の{@@link IfoDepositCalcResultRow}オブジェクトから{@@link IfoDepositCalcResultDao}オブジェクトを作成します。 
   * これは実際の{@@link IfoDepositCalcResultRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link IfoDepositCalcResultDao}取得のために指定の{@@link IfoDepositCalcResultRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link IfoDepositCalcResultDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static IfoDepositCalcResultDao forRow( IfoDepositCalcResultRow row ) throws java.lang.IllegalArgumentException {
        return (IfoDepositCalcResultDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoDepositCalcResultRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link IfoDepositCalcResultRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link IfoDepositCalcResultPK}やデータベースレコードとして挿入される{@@link IfoDepositCalcResultParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoDepositCalcResultRow.TYPE );
    }


  /** 
   * {@@link IfoDepositCalcResultRow}を一意に特定する{@@link IfoDepositCalcResultPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link IfoDepositCalcResultRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link IfoDepositCalcResultParams}オブジェクトの主キーとして利用可能な{@@link IfoDepositCalcResultPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static IfoDepositCalcResultPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new IfoDepositCalcResultPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link IfoDepositCalcResultRow}オブジェクトを検索します。 
   * 
   * @@param p_depositInfoId 検索対象であるp_depositInfoIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoDepositCalcResultRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoDepositCalcResultRow findRowByPk( long p_depositInfoId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcResultPK pk = new IfoDepositCalcResultPK( p_depositInfoId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のIfoDepositCalcResultPKオブジェクトから{@@link IfoDepositCalcResultRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するIfoDepositCalcResultPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link IfoDepositCalcResultRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static IfoDepositCalcResultRow findRowByPk( IfoDepositCalcResultPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoDepositCalcResultRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(IfoDepositCalcResultRow)}を使用してください。 
   */
    public static IfoDepositCalcResultDao findDaoByPk( long p_depositInfoId ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcResultPK pk = new IfoDepositCalcResultPK( p_depositInfoId );
        IfoDepositCalcResultRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(IfoDepositCalcResultPK)}および{@@link #forRow(IfoDepositCalcResultRow)}を使用してください。 
   */
    public static IfoDepositCalcResultDao findDaoByPk( IfoDepositCalcResultPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoDepositCalcResultRow row = findRowByPk( pk );
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
