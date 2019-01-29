head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.43.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiCommCondDao.java;


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
 * {@@link SrvRegiCommCondDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiCommCondRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiCommCondPK 
 * @@see SrvRegiCommCondRow 
 */
public class SrvRegiCommCondDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiCommCondDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiCommCondRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiCommCondRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiCommCondDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiCommCondDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiCommCondRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiCommCondRow )
                return new SrvRegiCommCondDao( (SrvRegiCommCondRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiCommCondRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiCommCondRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiCommCondRow}オブジェクト 
    */
    protected SrvRegiCommCondDao( SrvRegiCommCondRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiCommCondRow}オブジェクトを取得します。
   */
    public SrvRegiCommCondRow getSrvRegiCommCondRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiCommCondRow}オブジェクトから{@@link SrvRegiCommCondDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiCommCondRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiCommCondDao}取得のために指定の{@@link SrvRegiCommCondRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiCommCondDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiCommCondDao forRow( SrvRegiCommCondRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiCommCondDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiCommCondRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiCommCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiCommCondPK}やデータベースレコードとして挿入される{@@link SrvRegiCommCondParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiCommCondRow.TYPE );
    }


  /** 
   * {@@link SrvRegiCommCondRow}を一意に特定する{@@link SrvRegiCommCondPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiCommCondRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiCommCondParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiCommCondPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiCommCondPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiCommCondRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_orderAccProduct 検索対象であるp_orderAccProductフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiCommCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiCommCondRow findRowByPk( String p_institutionCode, String p_srvDiv, String p_orderAccProduct ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiCommCondPK pk = new SrvRegiCommCondPK( p_institutionCode, p_srvDiv, p_orderAccProduct );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiCommCondPKオブジェクトから{@@link SrvRegiCommCondRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiCommCondPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiCommCondRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiCommCondRow findRowByPk( SrvRegiCommCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiCommCondRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String)}および{@@link #forRow(SrvRegiCommCondRow)}を使用してください。 
   */
    public static SrvRegiCommCondDao findDaoByPk( String p_institutionCode, String p_srvDiv, String p_orderAccProduct ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiCommCondPK pk = new SrvRegiCommCondPK( p_institutionCode, p_srvDiv, p_orderAccProduct );
        SrvRegiCommCondRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiCommCondPK)}および{@@link #forRow(SrvRegiCommCondRow)}を使用してください。 
   */
    public static SrvRegiCommCondDao findDaoByPk( SrvRegiCommCondPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiCommCondRow row = findRowByPk( pk );
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
   * p_institutionCode, p_srvDiv, p_orderAccProduct, and にて指定の値から一意の{@@link SrvRegiCommCondRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_orderAccProduct 検索対象であるp_orderAccProductフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_srvDiv, p_orderAccProduct, and の値と一致する{@@link SrvRegiCommCondRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiCommCondRow findRowByInstitutionCodeSrvDivOrderAccProduct( String p_institutionCode, String p_srvDiv, String p_orderAccProduct ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiCommCondRow.TYPE,
            "institution_code=? and srv_div=? and order_acc_product=?",
            null,
            new Object[] { p_institutionCode, p_srvDiv, p_orderAccProduct } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiCommCondRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiCommCondDao.findRowsByInstitutionCodeSrvDivOrderAccProduct(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeSrvDivOrderAccProduct(String, String, String)}および{@@link #forRow(SrvRegiCommCondRow)}を使用してください。 
   */
    public static SrvRegiCommCondDao findDaoByInstitutionCodeSrvDivOrderAccProduct( String p_institutionCode, String p_srvDiv, String p_orderAccProduct ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSrvDivOrderAccProduct( p_institutionCode, p_srvDiv, p_orderAccProduct ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
