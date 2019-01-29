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
filename	ShortSellingRestraintTimeDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link ShortSellingRestraintTimeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link ShortSellingRestraintTimeRow}インスタンスへ関連付けることができます。 
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
 * @@see ShortSellingRestraintTimePK 
 * @@see ShortSellingRestraintTimeRow 
 */
public class ShortSellingRestraintTimeDao extends DataAccessObject {


  /** 
   * この{@@link ShortSellingRestraintTimeDao}に関連する型指定のRowオブジェクト 
   */
    private ShortSellingRestraintTimeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link ShortSellingRestraintTimeRow}と仮定される{@@link DataAccessObject}から新たに{@@link ShortSellingRestraintTimeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link ShortSellingRestraintTimeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link ShortSellingRestraintTimeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ShortSellingRestraintTimeRow )
                return new ShortSellingRestraintTimeDao( (ShortSellingRestraintTimeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ShortSellingRestraintTimeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ShortSellingRestraintTimeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link ShortSellingRestraintTimeRow}オブジェクト 
    */
    protected ShortSellingRestraintTimeDao( ShortSellingRestraintTimeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link ShortSellingRestraintTimeRow}オブジェクトを取得します。
   */
    public ShortSellingRestraintTimeRow getShortSellingRestraintTimeRow() {
        return row;
    }


  /** 
   * 指定の{@@link ShortSellingRestraintTimeRow}オブジェクトから{@@link ShortSellingRestraintTimeDao}オブジェクトを作成します。 
   * これは実際の{@@link ShortSellingRestraintTimeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link ShortSellingRestraintTimeDao}取得のために指定の{@@link ShortSellingRestraintTimeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link ShortSellingRestraintTimeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static ShortSellingRestraintTimeDao forRow( ShortSellingRestraintTimeRow row ) throws java.lang.IllegalArgumentException {
        return (ShortSellingRestraintTimeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ShortSellingRestraintTimeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link ShortSellingRestraintTimeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link ShortSellingRestraintTimePK}やデータベースレコードとして挿入される{@@link ShortSellingRestraintTimeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ShortSellingRestraintTimeRow.TYPE );
    }


  /** 
   * {@@link ShortSellingRestraintTimeRow}を一意に特定する{@@link ShortSellingRestraintTimePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link ShortSellingRestraintTimeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link ShortSellingRestraintTimeParams}オブジェクトの主キーとして利用可能な{@@link ShortSellingRestraintTimePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static ShortSellingRestraintTimePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link ShortSellingRestraintTimeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_marketCode 検索対象であるp_marketCodeフィールドの値
   * @@param p_bizDateType 検索対象であるp_bizDateTypeフィールドの値
   * @@param p_startTime 検索対象であるp_startTimeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ShortSellingRestraintTimeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ShortSellingRestraintTimeRow findRowByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_bizDateType, String p_startTime ) throws DataFindException, DataQueryException, DataNetworkException {
        ShortSellingRestraintTimePK pk = new ShortSellingRestraintTimePK( p_institutionCode, p_branchCode, p_marketCode, p_bizDateType, p_startTime );
        return findRowByPk( pk );
    }


  /** 
   * 指定のShortSellingRestraintTimePKオブジェクトから{@@link ShortSellingRestraintTimeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するShortSellingRestraintTimePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link ShortSellingRestraintTimeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static ShortSellingRestraintTimeRow findRowByPk( ShortSellingRestraintTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ShortSellingRestraintTimeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,String,String)}および{@@link #forRow(ShortSellingRestraintTimeRow)}を使用してください。 
   */
    public static ShortSellingRestraintTimeDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_marketCode, String p_bizDateType, String p_startTime ) throws DataFindException, DataQueryException, DataNetworkException {
        ShortSellingRestraintTimePK pk = new ShortSellingRestraintTimePK( p_institutionCode, p_branchCode, p_marketCode, p_bizDateType, p_startTime );
        ShortSellingRestraintTimeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(ShortSellingRestraintTimePK)}および{@@link #forRow(ShortSellingRestraintTimeRow)}を使用してください。 
   */
    public static ShortSellingRestraintTimeDao findDaoByPk( ShortSellingRestraintTimePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ShortSellingRestraintTimeRow row = findRowByPk( pk );
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
