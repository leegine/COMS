head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AttentionInfoHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.eqtypeadmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * {@@link AttentionInfoHistoryDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AttentionInfoHistoryRow}インスタンスへ関連付けることができます。 
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
 * @@see AttentionInfoHistoryPK 
 * @@see AttentionInfoHistoryRow 
 */
public class AttentionInfoHistoryDao extends DataAccessObject {


  /** 
   * この{@@link AttentionInfoHistoryDao}に関連する型指定のRowオブジェクト 
   */
    private AttentionInfoHistoryRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AttentionInfoHistoryRow}と仮定される{@@link DataAccessObject}から新たに{@@link AttentionInfoHistoryDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AttentionInfoHistoryDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AttentionInfoHistoryRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AttentionInfoHistoryRow )
                return new AttentionInfoHistoryDao( (AttentionInfoHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AttentionInfoHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AttentionInfoHistoryRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AttentionInfoHistoryRow}オブジェクト 
    */
    protected AttentionInfoHistoryDao( AttentionInfoHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AttentionInfoHistoryRow}オブジェクトを取得します。
   */
    public AttentionInfoHistoryRow getAttentionInfoHistoryRow() {
        return row;
    }


  /** 
   * 指定の{@@link AttentionInfoHistoryRow}オブジェクトから{@@link AttentionInfoHistoryDao}オブジェクトを作成します。 
   * これは実際の{@@link AttentionInfoHistoryRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AttentionInfoHistoryDao}取得のために指定の{@@link AttentionInfoHistoryRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AttentionInfoHistoryDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AttentionInfoHistoryDao forRow( AttentionInfoHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (AttentionInfoHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AttentionInfoHistoryRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AttentionInfoHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AttentionInfoHistoryPK}やデータベースレコードとして挿入される{@@link AttentionInfoHistoryParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AttentionInfoHistoryRow.TYPE );
    }


  /** 
   * {@@link AttentionInfoHistoryRow}を一意に特定する{@@link AttentionInfoHistoryPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AttentionInfoHistoryRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AttentionInfoHistoryParams}オブジェクトの主キーとして利用可能な{@@link AttentionInfoHistoryPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AttentionInfoHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AttentionInfoHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AttentionInfoHistoryRow}オブジェクトを検索します。 
   * 
   * @@param p_attentionInfoHistoryId 検索対象であるp_attentionInfoHistoryIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AttentionInfoHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AttentionInfoHistoryRow findRowByPk( long p_attentionInfoHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        AttentionInfoHistoryPK pk = new AttentionInfoHistoryPK( p_attentionInfoHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAttentionInfoHistoryPKオブジェクトから{@@link AttentionInfoHistoryRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAttentionInfoHistoryPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AttentionInfoHistoryRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AttentionInfoHistoryRow findRowByPk( AttentionInfoHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AttentionInfoHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long)}および{@@link #forRow(AttentionInfoHistoryRow)}を使用してください。 
   */
    public static AttentionInfoHistoryDao findDaoByPk( long p_attentionInfoHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        AttentionInfoHistoryPK pk = new AttentionInfoHistoryPK( p_attentionInfoHistoryId );
        AttentionInfoHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AttentionInfoHistoryPK)}および{@@link #forRow(AttentionInfoHistoryRow)}を使用してください。 
   */
    public static AttentionInfoHistoryDao findDaoByPk( AttentionInfoHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AttentionInfoHistoryRow row = findRowByPk( pk );
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
