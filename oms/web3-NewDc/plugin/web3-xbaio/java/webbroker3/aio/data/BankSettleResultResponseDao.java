head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.42.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankSettleResultResponseDao.java;


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
 * {@@link BankSettleResultResponseDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BankSettleResultResponseRow}インスタンスへ関連付けることができます。 
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
 * @@see BankSettleResultResponsePK 
 * @@see BankSettleResultResponseRow 
 */
public class BankSettleResultResponseDao extends DataAccessObject {


  /** 
   * この{@@link BankSettleResultResponseDao}に関連する型指定のRowオブジェクト 
   */
    private BankSettleResultResponseRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BankSettleResultResponseRow}と仮定される{@@link DataAccessObject}から新たに{@@link BankSettleResultResponseDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BankSettleResultResponseDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BankSettleResultResponseRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BankSettleResultResponseRow )
                return new BankSettleResultResponseDao( (BankSettleResultResponseRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BankSettleResultResponseRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BankSettleResultResponseRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BankSettleResultResponseRow}オブジェクト 
    */
    protected BankSettleResultResponseDao( BankSettleResultResponseRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BankSettleResultResponseRow}オブジェクトを取得します。
   */
    public BankSettleResultResponseRow getBankSettleResultResponseRow() {
        return row;
    }


  /** 
   * 指定の{@@link BankSettleResultResponseRow}オブジェクトから{@@link BankSettleResultResponseDao}オブジェクトを作成します。 
   * これは実際の{@@link BankSettleResultResponseRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BankSettleResultResponseDao}取得のために指定の{@@link BankSettleResultResponseRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BankSettleResultResponseDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BankSettleResultResponseDao forRow( BankSettleResultResponseRow row ) throws java.lang.IllegalArgumentException {
        return (BankSettleResultResponseDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BankSettleResultResponseRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BankSettleResultResponseRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BankSettleResultResponsePK}やデータベースレコードとして挿入される{@@link BankSettleResultResponseParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BankSettleResultResponseRow.TYPE );
    }


  /** 
   * {@@link BankSettleResultResponseRow}を一意に特定する{@@link BankSettleResultResponsePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BankSettleResultResponseRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BankSettleResultResponseParams}オブジェクトの主キーとして利用可能な{@@link BankSettleResultResponsePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BankSettleResultResponsePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BankSettleResultResponseRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankSettleResultResponseRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankSettleResultResponseRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleResultResponsePK pk = new BankSettleResultResponsePK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBankSettleResultResponsePKオブジェクトから{@@link BankSettleResultResponseRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBankSettleResultResponsePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BankSettleResultResponseRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BankSettleResultResponseRow findRowByPk( BankSettleResultResponsePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BankSettleResultResponseRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(BankSettleResultResponseRow)}を使用してください。 
   */
    public static BankSettleResultResponseDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleResultResponsePK pk = new BankSettleResultResponsePK( p_rowid );
        BankSettleResultResponseRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BankSettleResultResponsePK)}および{@@link #forRow(BankSettleResultResponseRow)}を使用してください。 
   */
    public static BankSettleResultResponseDao findDaoByPk( BankSettleResultResponsePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BankSettleResultResponseRow row = findRowByPk( pk );
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
