head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CooperateBankMasterDao.java;


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
 * {@@link CooperateBankMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link CooperateBankMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see CooperateBankMasterPK 
 * @@see CooperateBankMasterRow 
 */
public class CooperateBankMasterDao extends DataAccessObject {


  /** 
   * この{@@link CooperateBankMasterDao}に関連する型指定のRowオブジェクト 
   */
    private CooperateBankMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link CooperateBankMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link CooperateBankMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link CooperateBankMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link CooperateBankMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CooperateBankMasterRow )
                return new CooperateBankMasterDao( (CooperateBankMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CooperateBankMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CooperateBankMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link CooperateBankMasterRow}オブジェクト 
    */
    protected CooperateBankMasterDao( CooperateBankMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link CooperateBankMasterRow}オブジェクトを取得します。
   */
    public CooperateBankMasterRow getCooperateBankMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link CooperateBankMasterRow}オブジェクトから{@@link CooperateBankMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link CooperateBankMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link CooperateBankMasterDao}取得のために指定の{@@link CooperateBankMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link CooperateBankMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static CooperateBankMasterDao forRow( CooperateBankMasterRow row ) throws java.lang.IllegalArgumentException {
        return (CooperateBankMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CooperateBankMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link CooperateBankMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link CooperateBankMasterPK}やデータベースレコードとして挿入される{@@link CooperateBankMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CooperateBankMasterRow.TYPE );
    }


  /** 
   * {@@link CooperateBankMasterRow}を一意に特定する{@@link CooperateBankMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link CooperateBankMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link CooperateBankMasterParams}オブジェクトの主キーとして利用可能な{@@link CooperateBankMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static CooperateBankMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link CooperateBankMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_paySchemeId 検索対象であるp_paySchemeIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CooperateBankMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CooperateBankMasterRow findRowByPk( String p_paySchemeId ) throws DataFindException, DataQueryException, DataNetworkException {
        CooperateBankMasterPK pk = new CooperateBankMasterPK( p_paySchemeId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のCooperateBankMasterPKオブジェクトから{@@link CooperateBankMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するCooperateBankMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link CooperateBankMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static CooperateBankMasterRow findRowByPk( CooperateBankMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CooperateBankMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(CooperateBankMasterRow)}を使用してください。 
   */
    public static CooperateBankMasterDao findDaoByPk( String p_paySchemeId ) throws DataFindException, DataQueryException, DataNetworkException {
        CooperateBankMasterPK pk = new CooperateBankMasterPK( p_paySchemeId );
        CooperateBankMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(CooperateBankMasterPK)}および{@@link #forRow(CooperateBankMasterRow)}を使用してください。 
   */
    public static CooperateBankMasterDao findDaoByPk( CooperateBankMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CooperateBankMasterRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * この{@@link CooperateBankMasterDao}に関連する{@@link CooperateBankMasterRow}の外部キーがある{@@link CompBankConditionRow}オブジェクトを検索し{@@link List}として返します。 
   * 
   * @@return この{@@link CooperateBankMasterDao}に関連する{@@link CooperateBankMasterRow}の外部キーがある{@@link CompBankConditionRow}オブジェクトの{@@link List}
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public List fetchCompBankConditionRowsByPaySchemeId() throws DataNetworkException, DataQueryException  {
        return CompBankConditionDao.findRowsByPaySchemeId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchCompBankConditionRowsByPaySchemeId()}および{@@link #forRow(CooperateBankMasterRow)}を使用してください。 
   */
    public List fetchCompBankConditionDaosByPaySchemeId() throws DataNetworkException, DataQueryException  {
        return CompBankConditionDao.findDaosByPaySchemeId( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #fetchCompBankConditionRowsByPaySchemeId()}および{@@link #forRow(CooperateBankMasterRow)}を使用してください。 
   */
    public List fetchCompBankConditionDaosPaySchemeId() throws DataNetworkException, DataQueryException  {
        return fetchCompBankConditionDaosByPaySchemeId();
    }


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_paySchemeId, and にて指定の値から一意の{@@link CooperateBankMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_paySchemeId 検索対象であるp_paySchemeIdフィールドの値
   * 
   * @@return 引数指定のp_paySchemeId, and の値と一致する{@@link CooperateBankMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static CooperateBankMasterRow findRowByPaySchemeId( String p_paySchemeId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CooperateBankMasterRow.TYPE,
            "pay_scheme_id=?",
            null,
            new Object[] { p_paySchemeId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CooperateBankMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CooperateBankMasterDao.findRowsByPaySchemeId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPaySchemeId(String)}および{@@link #forRow(CooperateBankMasterRow)}を使用してください。 
   */
    public static CooperateBankMasterDao findDaoByPaySchemeId( String p_paySchemeId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByPaySchemeId( p_paySchemeId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
