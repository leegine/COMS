head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.47.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	BankSettleStartRequestDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link BankSettleStartRequestDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BankSettleStartRequestRow}インスタンスへ関連付けることができます。 
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
 * @@see BankSettleStartRequestPK 
 * @@see BankSettleStartRequestRow 
 */
public class BankSettleStartRequestDao extends DataAccessObject {


  /** 
   * この{@@link BankSettleStartRequestDao}に関連する型指定のRowオブジェクト 
   */
    private BankSettleStartRequestRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BankSettleStartRequestRow}と仮定される{@@link DataAccessObject}から新たに{@@link BankSettleStartRequestDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BankSettleStartRequestDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BankSettleStartRequestRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankSettleStartRequestRow )
                return new BankSettleStartRequestDao( (BankSettleStartRequestRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankSettleStartRequestRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankSettleStartRequestRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BankSettleStartRequestRow}オブジェクト 
    */
    protected BankSettleStartRequestDao( BankSettleStartRequestRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BankSettleStartRequestRow}オブジェクトを取得します。
   */
    public BankSettleStartRequestRow getBankSettleStartRequestRow() {
        return row;
    }


  /** 
   * 指定の{@@link BankSettleStartRequestRow}オブジェクトから{@@link BankSettleStartRequestDao}オブジェクトを作成します。 
   * これは実際の{@@link BankSettleStartRequestRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BankSettleStartRequestDao}取得のために指定の{@@link BankSettleStartRequestRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BankSettleStartRequestDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BankSettleStartRequestDao forRow( BankSettleStartRequestRow row ) throws java.lang.IllegalArgumentException {
        return (BankSettleStartRequestDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankSettleStartRequestRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BankSettleStartRequestRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BankSettleStartRequestPK}やデータベースレコードとして挿入される{@@link BankSettleStartRequestParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankSettleStartRequestRow.TYPE );
    }


  /** 
   * {@@link BankSettleStartRequestRow}を一意に特定する{@@link BankSettleStartRequestPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BankSettleStartRequestRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BankSettleStartRequestParams}オブジェクトの主キーとして利用可能な{@@link BankSettleStartRequestPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BankSettleStartRequestPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BankSettleStartRequestRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankSettleStartRequestRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankSettleStartRequestRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleStartRequestPK pk = new BankSettleStartRequestPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBankSettleStartRequestPKオブジェクトから{@@link BankSettleStartRequestRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBankSettleStartRequestPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankSettleStartRequestRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankSettleStartRequestRow findRowByPk( BankSettleStartRequestPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankSettleStartRequestRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(BankSettleStartRequestRow)}を使用してください。 
   */
    public static BankSettleStartRequestDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleStartRequestPK pk = new BankSettleStartRequestPK( p_rowid );
        BankSettleStartRequestRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BankSettleStartRequestPK)}および{@@link #forRow(BankSettleStartRequestRow)}を使用してください。 
   */
    public static BankSettleStartRequestDao findDaoByPk( BankSettleStartRequestPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleStartRequestRow row = findRowByPk( pk );
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
