head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpOtherTempRestraintDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpower.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TpOtherTempRestraintDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link TpOtherTempRestraintRow}インスタンスへ関連付けることができます。 
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
 * @@see TpOtherTempRestraintPK 
 * @@see TpOtherTempRestraintRow 
 */
public class TpOtherTempRestraintDao extends DataAccessObject {


  /** 
   * この{@@link TpOtherTempRestraintDao}に関連する型指定のRowオブジェクト 
   */
    private TpOtherTempRestraintRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link TpOtherTempRestraintRow}と仮定される{@@link DataAccessObject}から新たに{@@link TpOtherTempRestraintDao}を返します。 
         * @@return 指定のRowに結びつく{@@link TpOtherTempRestraintDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link TpOtherTempRestraintRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpOtherTempRestraintRow )
                return new TpOtherTempRestraintDao( (TpOtherTempRestraintRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpOtherTempRestraintRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpOtherTempRestraintRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link TpOtherTempRestraintRow}オブジェクト 
    */
    protected TpOtherTempRestraintDao( TpOtherTempRestraintRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link TpOtherTempRestraintRow}オブジェクトを取得します。
   */
    public TpOtherTempRestraintRow getTpOtherTempRestraintRow() {
        return row;
    }


  /** 
   * 指定の{@@link TpOtherTempRestraintRow}オブジェクトから{@@link TpOtherTempRestraintDao}オブジェクトを作成します。 
   * これは実際の{@@link TpOtherTempRestraintRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link TpOtherTempRestraintDao}取得のために指定の{@@link TpOtherTempRestraintRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link TpOtherTempRestraintDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static TpOtherTempRestraintDao forRow( TpOtherTempRestraintRow row ) throws java.lang.IllegalArgumentException {
        return (TpOtherTempRestraintDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


      // (this object has no primary key components)


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


      // (this object has no primary key components)


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
   * p_accountId, p_deleteFlag, and にて指定の値に一致する{@@link TpOtherTempRestraintRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_accountId 検索対象であるp_accountIdフィールドの値
   * @@param p_deleteFlag 検索対象であるp_deleteFlagフィールドの値
   * 
   * @@return 引数指定のp_accountId, p_deleteFlag, and の値と一致する{@@link TpOtherTempRestraintRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByAccountIdDeleteFlag( long p_accountId, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpOtherTempRestraintRow.TYPE,
            "account_id=? and delete_flag=?",
            null,
            new Object[] { new Long(p_accountId), p_deleteFlag } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByAccountIdDeleteFlag(long, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum)}および{@@link #forRow(TpOtherTempRestraintRow)}を使用してください。 
   */
    public static List findDaosByAccountIdDeleteFlag( long p_accountId, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdDeleteFlag( p_accountId, p_deleteFlag ) );
    }


  /** 
   * p_restraintDiv, p_deleteKey1, and にて指定の値に一致する{@@link TpOtherTempRestraintRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_restraintDiv 検索対象であるp_restraintDivフィールドの値
   * @@param p_deleteKey1 検索対象であるp_deleteKey1フィールドの値
   * 
   * @@return 引数指定のp_restraintDiv, p_deleteKey1, and の値と一致する{@@link TpOtherTempRestraintRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByRestraintDivDeleteKey1( String p_restraintDiv, String p_deleteKey1 ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpOtherTempRestraintRow.TYPE,
            "restraint_div=? and delete_key1=?",
            null,
            new Object[] { p_restraintDiv, p_deleteKey1 } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByRestraintDivDeleteKey1(String, String)}および{@@link #forRow(TpOtherTempRestraintRow)}を使用してください。 
   */
    public static List findDaosByRestraintDivDeleteKey1( String p_restraintDiv, String p_deleteKey1 ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRestraintDivDeleteKey1( p_restraintDiv, p_deleteKey1 ) );
    }

}
@
