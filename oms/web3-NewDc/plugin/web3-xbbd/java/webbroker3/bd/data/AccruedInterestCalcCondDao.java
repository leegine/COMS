head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	AccruedInterestCalcCondDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link AccruedInterestCalcCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AccruedInterestCalcCondRow}インスタンスへ関連付けることができます。 
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
 * @@see AccruedInterestCalcCondPK 
 * @@see AccruedInterestCalcCondRow 
 */
public class AccruedInterestCalcCondDao extends DataAccessObject {


  /** 
   * この{@@link AccruedInterestCalcCondDao}に関連する型指定のRowオブジェクト 
   */
    private AccruedInterestCalcCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AccruedInterestCalcCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link AccruedInterestCalcCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AccruedInterestCalcCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AccruedInterestCalcCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AccruedInterestCalcCondRow )
                return new AccruedInterestCalcCondDao( (AccruedInterestCalcCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AccruedInterestCalcCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AccruedInterestCalcCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AccruedInterestCalcCondRow}オブジェクト 
    */
    protected AccruedInterestCalcCondDao( AccruedInterestCalcCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AccruedInterestCalcCondRow}オブジェクトを取得します。
   */
    public AccruedInterestCalcCondRow getAccruedInterestCalcCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link AccruedInterestCalcCondRow}オブジェクトから{@@link AccruedInterestCalcCondDao}オブジェクトを作成します。 
   * これは実際の{@@link AccruedInterestCalcCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AccruedInterestCalcCondDao}取得のために指定の{@@link AccruedInterestCalcCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AccruedInterestCalcCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AccruedInterestCalcCondDao forRow( AccruedInterestCalcCondRow row ) throws java.lang.IllegalArgumentException {
        return (AccruedInterestCalcCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AccruedInterestCalcCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AccruedInterestCalcCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AccruedInterestCalcCondPK}やデータベースレコードとして挿入される{@@link AccruedInterestCalcCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AccruedInterestCalcCondRow.TYPE );
    }


  /** 
   * {@@link AccruedInterestCalcCondRow}を一意に特定する{@@link AccruedInterestCalcCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AccruedInterestCalcCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AccruedInterestCalcCondParams}オブジェクトの主キーとして利用可能な{@@link AccruedInterestCalcCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AccruedInterestCalcCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AccruedInterestCalcCondRow}オブジェクトを検索します。 
   * 
   * @@param p_accruedInterestCalcType 検索対象であるp_accruedInterestCalcTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccruedInterestCalcCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccruedInterestCalcCondRow findRowByPk( String p_accruedInterestCalcType ) throws DataFindException, DataQueryException, DataNetworkException {
        AccruedInterestCalcCondPK pk = new AccruedInterestCalcCondPK( p_accruedInterestCalcType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAccruedInterestCalcCondPKオブジェクトから{@@link AccruedInterestCalcCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAccruedInterestCalcCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AccruedInterestCalcCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AccruedInterestCalcCondRow findRowByPk( AccruedInterestCalcCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccruedInterestCalcCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(AccruedInterestCalcCondRow)}を使用してください。 
   */
    public static AccruedInterestCalcCondDao findDaoByPk( String p_accruedInterestCalcType ) throws DataFindException, DataQueryException, DataNetworkException {
        AccruedInterestCalcCondPK pk = new AccruedInterestCalcCondPK( p_accruedInterestCalcType );
        AccruedInterestCalcCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AccruedInterestCalcCondPK)}および{@@link #forRow(AccruedInterestCalcCondRow)}を使用してください。 
   */
    public static AccruedInterestCalcCondDao findDaoByPk( AccruedInterestCalcCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AccruedInterestCalcCondRow row = findRowByPk( pk );
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


  /** 
   * p_accruedInterestCalcType, and にて指定の値から一意の{@@link AccruedInterestCalcCondRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_accruedInterestCalcType 検索対象であるp_accruedInterestCalcTypeフィールドの値
   * 
   * @@return 引数指定のp_accruedInterestCalcType, and の値と一致する{@@link AccruedInterestCalcCondRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AccruedInterestCalcCondRow findRowByAccruedInterestCalcType( String p_accruedInterestCalcType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AccruedInterestCalcCondRow.TYPE,
            "accrued_interest_calc_type=?",
            null,
            new Object[] { p_accruedInterestCalcType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AccruedInterestCalcCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AccruedInterestCalcCondDao.findRowsByAccruedInterestCalcType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByAccruedInterestCalcType(String)}および{@@link #forRow(AccruedInterestCalcCondRow)}を使用してください。 
   */
    public static AccruedInterestCalcCondDao findDaoByAccruedInterestCalcType( String p_accruedInterestCalcType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccruedInterestCalcType( p_accruedInterestCalcType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
