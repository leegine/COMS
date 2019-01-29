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
filename	HostEquityCarryoverSkipDao.java;


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
 * {@@link HostEquityCarryoverSkipDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostEquityCarryoverSkipRow}インスタンスへ関連付けることができます。 
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
 * @@see HostEquityCarryoverSkipPK 
 * @@see HostEquityCarryoverSkipRow 
 */
public class HostEquityCarryoverSkipDao extends DataAccessObject {


  /** 
   * この{@@link HostEquityCarryoverSkipDao}に関連する型指定のRowオブジェクト 
   */
    private HostEquityCarryoverSkipRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostEquityCarryoverSkipRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostEquityCarryoverSkipDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostEquityCarryoverSkipDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostEquityCarryoverSkipRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEquityCarryoverSkipRow )
                return new HostEquityCarryoverSkipDao( (HostEquityCarryoverSkipRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEquityCarryoverSkipRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEquityCarryoverSkipRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostEquityCarryoverSkipRow}オブジェクト 
    */
    protected HostEquityCarryoverSkipDao( HostEquityCarryoverSkipRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostEquityCarryoverSkipRow}オブジェクトを取得します。
   */
    public HostEquityCarryoverSkipRow getHostEquityCarryoverSkipRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostEquityCarryoverSkipRow}オブジェクトから{@@link HostEquityCarryoverSkipDao}オブジェクトを作成します。 
   * これは実際の{@@link HostEquityCarryoverSkipRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostEquityCarryoverSkipDao}取得のために指定の{@@link HostEquityCarryoverSkipRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostEquityCarryoverSkipDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostEquityCarryoverSkipDao forRow( HostEquityCarryoverSkipRow row ) throws java.lang.IllegalArgumentException {
        return (HostEquityCarryoverSkipDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEquityCarryoverSkipRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostEquityCarryoverSkipRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostEquityCarryoverSkipPK}やデータベースレコードとして挿入される{@@link HostEquityCarryoverSkipParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEquityCarryoverSkipRow.TYPE );
    }


  /** 
   * {@@link HostEquityCarryoverSkipRow}を一意に特定する{@@link HostEquityCarryoverSkipPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostEquityCarryoverSkipRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostEquityCarryoverSkipParams}オブジェクトの主キーとして利用可能な{@@link HostEquityCarryoverSkipPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostEquityCarryoverSkipPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostEquityCarryoverSkipRow}オブジェクトを検索します。 
   * 
   * @@param p_rowid 検索対象であるp_rowidフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEquityCarryoverSkipRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEquityCarryoverSkipRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEquityCarryoverSkipPK pk = new HostEquityCarryoverSkipPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostEquityCarryoverSkipPKオブジェクトから{@@link HostEquityCarryoverSkipRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostEquityCarryoverSkipPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostEquityCarryoverSkipRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostEquityCarryoverSkipRow findRowByPk( HostEquityCarryoverSkipPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEquityCarryoverSkipRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(HostEquityCarryoverSkipRow)}を使用してください。 
   */
    public static HostEquityCarryoverSkipDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEquityCarryoverSkipPK pk = new HostEquityCarryoverSkipPK( p_rowid );
        HostEquityCarryoverSkipRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostEquityCarryoverSkipPK)}および{@@link #forRow(HostEquityCarryoverSkipRow)}を使用してください。 
   */
    public static HostEquityCarryoverSkipDao findDaoByPk( HostEquityCarryoverSkipPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEquityCarryoverSkipRow row = findRowByPk( pk );
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
