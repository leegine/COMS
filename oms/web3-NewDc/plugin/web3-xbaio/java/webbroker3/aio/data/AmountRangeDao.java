head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	AmountRangeDao.java;


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
 * {@@link AmountRangeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link AmountRangeRow}インスタンスへ関連付けることができます。 
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
 * @@see AmountRangePK 
 * @@see AmountRangeRow 
 */
public class AmountRangeDao extends DataAccessObject {


  /** 
   * この{@@link AmountRangeDao}に関連する型指定のRowオブジェクト 
   */
    private AmountRangeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link AmountRangeRow}と仮定される{@@link DataAccessObject}から新たに{@@link AmountRangeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link AmountRangeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link AmountRangeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AmountRangeRow )
                return new AmountRangeDao( (AmountRangeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AmountRangeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AmountRangeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link AmountRangeRow}オブジェクト 
    */
    protected AmountRangeDao( AmountRangeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link AmountRangeRow}オブジェクトを取得します。
   */
    public AmountRangeRow getAmountRangeRow() {
        return row;
    }


  /** 
   * 指定の{@@link AmountRangeRow}オブジェクトから{@@link AmountRangeDao}オブジェクトを作成します。 
   * これは実際の{@@link AmountRangeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link AmountRangeDao}取得のために指定の{@@link AmountRangeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link AmountRangeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static AmountRangeDao forRow( AmountRangeRow row ) throws java.lang.IllegalArgumentException {
        return (AmountRangeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AmountRangeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link AmountRangeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link AmountRangePK}やデータベースレコードとして挿入される{@@link AmountRangeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AmountRangeRow.TYPE );
    }


  /** 
   * {@@link AmountRangeRow}を一意に特定する{@@link AmountRangePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link AmountRangeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link AmountRangeParams}オブジェクトの主キーとして利用可能な{@@link AmountRangePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static AmountRangePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link AmountRangeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_fundType 検索対象であるp_fundTypeフィールドの値
   * @@param p_transactionType 検索対象であるp_transactionTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AmountRangeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AmountRangeRow findRowByPk( String p_institutionCode, String p_fundType, String p_transactionType ) throws DataFindException, DataQueryException, DataNetworkException {
        AmountRangePK pk = new AmountRangePK( p_institutionCode, p_fundType, p_transactionType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のAmountRangePKオブジェクトから{@@link AmountRangeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するAmountRangePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link AmountRangeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static AmountRangeRow findRowByPk( AmountRangePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AmountRangeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(AmountRangeRow)}を使用してください。 
   */
    public static AmountRangeDao findDaoByPk( String p_institutionCode, String p_fundType, String p_transactionType ) throws DataFindException, DataQueryException, DataNetworkException {
        AmountRangePK pk = new AmountRangePK( p_institutionCode, p_fundType, p_transactionType );
        AmountRangeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(AmountRangePK)}および{@@link #forRow(AmountRangeRow)}を使用してください。 
   */
    public static AmountRangeDao findDaoByPk( AmountRangePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AmountRangeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_fundType, p_transactionType, and にて指定の値から一意の{@@link AmountRangeRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_fundType 検索対象であるp_fundTypeフィールドの値
   * @@param p_transactionType 検索対象であるp_transactionTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_fundType, p_transactionType, and の値と一致する{@@link AmountRangeRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static AmountRangeRow findRowByInstitutionCodeFundTypeTransactionType( String p_institutionCode, String p_fundType, String p_transactionType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AmountRangeRow.TYPE,
            "institution_code=? and fund_type=? and transaction_type=?",
            null,
            new Object[] { p_institutionCode, p_fundType, p_transactionType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AmountRangeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AmountRangeDao.findRowsByInstitutionCodeFundTypeTransactionType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeFundTypeTransactionType(String, String, String)}および{@@link #forRow(AmountRangeRow)}を使用してください。 
   */
    public static AmountRangeDao findDaoByInstitutionCodeFundTypeTransactionType( String p_institutionCode, String p_fundType, String p_transactionType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeFundTypeTransactionType( p_institutionCode, p_fundType, p_transactionType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
