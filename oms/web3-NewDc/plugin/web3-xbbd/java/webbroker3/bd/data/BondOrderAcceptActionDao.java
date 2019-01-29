head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondOrderAcceptActionDao.java;


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
 * {@@link BondOrderAcceptActionDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BondOrderAcceptActionRow}インスタンスへ関連付けることができます。 
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
 * @@see BondOrderAcceptActionPK 
 * @@see BondOrderAcceptActionRow 
 */
public class BondOrderAcceptActionDao extends DataAccessObject {


  /** 
   * この{@@link BondOrderAcceptActionDao}に関連する型指定のRowオブジェクト 
   */
    private BondOrderAcceptActionRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BondOrderAcceptActionRow}と仮定される{@@link DataAccessObject}から新たに{@@link BondOrderAcceptActionDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BondOrderAcceptActionDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BondOrderAcceptActionRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondOrderAcceptActionRow )
                return new BondOrderAcceptActionDao( (BondOrderAcceptActionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondOrderAcceptActionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondOrderAcceptActionRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BondOrderAcceptActionRow}オブジェクト 
    */
    protected BondOrderAcceptActionDao( BondOrderAcceptActionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BondOrderAcceptActionRow}オブジェクトを取得します。
   */
    public BondOrderAcceptActionRow getBondOrderAcceptActionRow() {
        return row;
    }


  /** 
   * 指定の{@@link BondOrderAcceptActionRow}オブジェクトから{@@link BondOrderAcceptActionDao}オブジェクトを作成します。 
   * これは実際の{@@link BondOrderAcceptActionRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BondOrderAcceptActionDao}取得のために指定の{@@link BondOrderAcceptActionRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BondOrderAcceptActionDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BondOrderAcceptActionDao forRow( BondOrderAcceptActionRow row ) throws java.lang.IllegalArgumentException {
        return (BondOrderAcceptActionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondOrderAcceptActionRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BondOrderAcceptActionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BondOrderAcceptActionPK}やデータベースレコードとして挿入される{@@link BondOrderAcceptActionParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondOrderAcceptActionRow.TYPE );
    }


  /** 
   * {@@link BondOrderAcceptActionRow}を一意に特定する{@@link BondOrderAcceptActionPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BondOrderAcceptActionRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BondOrderAcceptActionParams}オブジェクトの主キーとして利用可能な{@@link BondOrderAcceptActionPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BondOrderAcceptActionPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BondOrderAcceptActionRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderAcceptDate 検索対象であるp_orderAcceptDateフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondOrderAcceptActionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondOrderAcceptActionRow findRowByPk( long p_productId, String p_institutionCode, String p_branchCode, java.sql.Timestamp p_orderAcceptDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderAcceptActionPK pk = new BondOrderAcceptActionPK( p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBondOrderAcceptActionPKオブジェクトから{@@link BondOrderAcceptActionRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBondOrderAcceptActionPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondOrderAcceptActionRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondOrderAcceptActionRow findRowByPk( BondOrderAcceptActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondOrderAcceptActionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,String,java.sql.Timestamp)}および{@@link #forRow(BondOrderAcceptActionRow)}を使用してください。 
   */
    public static BondOrderAcceptActionDao findDaoByPk( long p_productId, String p_institutionCode, String p_branchCode, java.sql.Timestamp p_orderAcceptDate ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderAcceptActionPK pk = new BondOrderAcceptActionPK( p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate );
        BondOrderAcceptActionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BondOrderAcceptActionPK)}および{@@link #forRow(BondOrderAcceptActionRow)}を使用してください。 
   */
    public static BondOrderAcceptActionDao findDaoByPk( BondOrderAcceptActionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondOrderAcceptActionRow row = findRowByPk( pk );
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
   * p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate, and にて指定の値から一意の{@@link BondOrderAcceptActionRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_orderAcceptDate 検索対象であるp_orderAcceptDateフィールドの値
   * 
   * @@return 引数指定のp_productId, p_institutionCode, p_branchCode, p_orderAcceptDate, and の値と一致する{@@link BondOrderAcceptActionRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondOrderAcceptActionRow findRowByProductIdInstitutionCodeBranchCodeOrderAcceptDate( long p_productId, String p_institutionCode, String p_branchCode, java.sql.Timestamp p_orderAcceptDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondOrderAcceptActionRow.TYPE,
            "product_id=? and institution_code=? and branch_code=? and order_accept_date=?",
            null,
            new Object[] { new Long(p_productId), p_institutionCode, p_branchCode, p_orderAcceptDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondOrderAcceptActionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondOrderAcceptActionDao.findRowsByProductIdInstitutionCodeBranchCodeOrderAcceptDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdInstitutionCodeBranchCodeOrderAcceptDate(long, String, String, java.sql.Timestamp)}および{@@link #forRow(BondOrderAcceptActionRow)}を使用してください。 
   */
    public static BondOrderAcceptActionDao findDaoByProductIdInstitutionCodeBranchCodeOrderAcceptDate( long p_productId, String p_institutionCode, String p_branchCode, java.sql.Timestamp p_orderAcceptDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdInstitutionCodeBranchCodeOrderAcceptDate( p_productId, p_institutionCode, p_branchCode, p_orderAcceptDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_productId, p_institutionCode, p_branchCode, and にて指定の値に一致する{@@link BondOrderAcceptActionRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_productId, p_institutionCode, p_branchCode, and の値と一致する{@@link BondOrderAcceptActionRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductIdInstitutionCodeBranchCode( long p_productId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondOrderAcceptActionRow.TYPE,
            "product_id=? and institution_code=? and branch_code=?",
            null,
            new Object[] { new Long(p_productId), p_institutionCode, p_branchCode } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductIdInstitutionCodeBranchCode(long, String, String)}および{@@link #forRow(BondOrderAcceptActionRow)}を使用してください。 
   */
    public static List findDaosByProductIdInstitutionCodeBranchCode( long p_productId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductIdInstitutionCodeBranchCode( p_productId, p_institutionCode, p_branchCode ) );
    }

}
@
