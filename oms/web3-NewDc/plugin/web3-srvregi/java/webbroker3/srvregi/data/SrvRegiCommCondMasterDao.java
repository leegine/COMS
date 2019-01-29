head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiCommCondMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.srvregi.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SrvRegiCommCondMasterDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiCommCondMasterRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiCommCondMasterPK 
 * @@see SrvRegiCommCondMasterRow 
 */
public class SrvRegiCommCondMasterDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiCommCondMasterDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiCommCondMasterRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiCommCondMasterRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiCommCondMasterDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiCommCondMasterDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiCommCondMasterRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiCommCondMasterRow )
                return new SrvRegiCommCondMasterDao( (SrvRegiCommCondMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiCommCondMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiCommCondMasterRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiCommCondMasterRow}オブジェクト 
    */
    protected SrvRegiCommCondMasterDao( SrvRegiCommCondMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiCommCondMasterRow}オブジェクトを取得します。
   */
    public SrvRegiCommCondMasterRow getSrvRegiCommCondMasterRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiCommCondMasterRow}オブジェクトから{@@link SrvRegiCommCondMasterDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiCommCondMasterRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiCommCondMasterDao}取得のために指定の{@@link SrvRegiCommCondMasterRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiCommCondMasterDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiCommCondMasterDao forRow( SrvRegiCommCondMasterRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiCommCondMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiCommCondMasterRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiCommCondMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiCommCondMasterPK}やデータベースレコードとして挿入される{@@link SrvRegiCommCondMasterParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiCommCondMasterRow.TYPE );
    }


  /** 
   * {@@link SrvRegiCommCondMasterRow}を一意に特定する{@@link SrvRegiCommCondMasterPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiCommCondMasterRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiCommCondMasterParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiCommCondMasterPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiCommCondMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiCommCondMasterRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_orderAccProduct 検索対象であるp_orderAccProductフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiCommCondMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiCommCondMasterRow findRowByPk( String p_institutionCode, String p_orderAccProduct ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiCommCondMasterPK pk = new SrvRegiCommCondMasterPK( p_institutionCode, p_orderAccProduct );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiCommCondMasterPKオブジェクトから{@@link SrvRegiCommCondMasterRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiCommCondMasterPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiCommCondMasterRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiCommCondMasterRow findRowByPk( SrvRegiCommCondMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiCommCondMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(SrvRegiCommCondMasterRow)}を使用してください。 
   */
    public static SrvRegiCommCondMasterDao findDaoByPk( String p_institutionCode, String p_orderAccProduct ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiCommCondMasterPK pk = new SrvRegiCommCondMasterPK( p_institutionCode, p_orderAccProduct );
        SrvRegiCommCondMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiCommCondMasterPK)}および{@@link #forRow(SrvRegiCommCondMasterRow)}を使用してください。 
   */
    public static SrvRegiCommCondMasterDao findDaoByPk( SrvRegiCommCondMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiCommCondMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_orderAccProduct, and にて指定の値から一意の{@@link SrvRegiCommCondMasterRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_orderAccProduct 検索対象であるp_orderAccProductフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_orderAccProduct, and の値と一致する{@@link SrvRegiCommCondMasterRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiCommCondMasterRow findRowByInstitutionCodeOrderAccProduct( String p_institutionCode, String p_orderAccProduct ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiCommCondMasterRow.TYPE,
            "institution_code=? and order_acc_product=?",
            null,
            new Object[] { p_institutionCode, p_orderAccProduct } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiCommCondMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiCommCondMasterDao.findRowsByInstitutionCodeOrderAccProduct(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeOrderAccProduct(String, String)}および{@@link #forRow(SrvRegiCommCondMasterRow)}を使用してください。 
   */
    public static SrvRegiCommCondMasterDao findDaoByInstitutionCodeOrderAccProduct( String p_institutionCode, String p_orderAccProduct ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeOrderAccProduct( p_institutionCode, p_orderAccProduct ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
