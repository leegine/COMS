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
filename	BondBranchRecruitLimitDao.java;


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
 * {@@link BondBranchRecruitLimitDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BondBranchRecruitLimitRow}インスタンスへ関連付けることができます。 
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
 * @@see BondBranchRecruitLimitPK 
 * @@see BondBranchRecruitLimitRow 
 */
public class BondBranchRecruitLimitDao extends DataAccessObject {


  /** 
   * この{@@link BondBranchRecruitLimitDao}に関連する型指定のRowオブジェクト 
   */
    private BondBranchRecruitLimitRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BondBranchRecruitLimitRow}と仮定される{@@link DataAccessObject}から新たに{@@link BondBranchRecruitLimitDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BondBranchRecruitLimitDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BondBranchRecruitLimitRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondBranchRecruitLimitRow )
                return new BondBranchRecruitLimitDao( (BondBranchRecruitLimitRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondBranchRecruitLimitRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondBranchRecruitLimitRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BondBranchRecruitLimitRow}オブジェクト 
    */
    protected BondBranchRecruitLimitDao( BondBranchRecruitLimitRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BondBranchRecruitLimitRow}オブジェクトを取得します。
   */
    public BondBranchRecruitLimitRow getBondBranchRecruitLimitRow() {
        return row;
    }


  /** 
   * 指定の{@@link BondBranchRecruitLimitRow}オブジェクトから{@@link BondBranchRecruitLimitDao}オブジェクトを作成します。 
   * これは実際の{@@link BondBranchRecruitLimitRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BondBranchRecruitLimitDao}取得のために指定の{@@link BondBranchRecruitLimitRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BondBranchRecruitLimitDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BondBranchRecruitLimitDao forRow( BondBranchRecruitLimitRow row ) throws java.lang.IllegalArgumentException {
        return (BondBranchRecruitLimitDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BondBranchRecruitLimitRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BondBranchRecruitLimitRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BondBranchRecruitLimitPK}やデータベースレコードとして挿入される{@@link BondBranchRecruitLimitParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BondBranchRecruitLimitRow.TYPE );
    }


  /** 
   * {@@link BondBranchRecruitLimitRow}を一意に特定する{@@link BondBranchRecruitLimitPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BondBranchRecruitLimitRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BondBranchRecruitLimitParams}オブジェクトの主キーとして利用可能な{@@link BondBranchRecruitLimitPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BondBranchRecruitLimitPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BondBranchRecruitLimitRow}オブジェクトを検索します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondBranchRecruitLimitRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondBranchRecruitLimitRow findRowByPk( long p_productId, String p_institutionCode, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchRecruitLimitPK pk = new BondBranchRecruitLimitPK( p_productId, p_institutionCode, p_branchCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBondBranchRecruitLimitPKオブジェクトから{@@link BondBranchRecruitLimitRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBondBranchRecruitLimitPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BondBranchRecruitLimitRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BondBranchRecruitLimitRow findRowByPk( BondBranchRecruitLimitPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BondBranchRecruitLimitRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(long,String,String)}および{@@link #forRow(BondBranchRecruitLimitRow)}を使用してください。 
   */
    public static BondBranchRecruitLimitDao findDaoByPk( long p_productId, String p_institutionCode, String p_branchCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchRecruitLimitPK pk = new BondBranchRecruitLimitPK( p_productId, p_institutionCode, p_branchCode );
        BondBranchRecruitLimitRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BondBranchRecruitLimitPK)}および{@@link #forRow(BondBranchRecruitLimitRow)}を使用してください。 
   */
    public static BondBranchRecruitLimitDao findDaoByPk( BondBranchRecruitLimitPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BondBranchRecruitLimitRow row = findRowByPk( pk );
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
   * p_productId, p_institutionCode, p_branchCode, and にて指定の値から一意の{@@link BondBranchRecruitLimitRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * 
   * @@return 引数指定のp_productId, p_institutionCode, p_branchCode, and の値と一致する{@@link BondBranchRecruitLimitRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BondBranchRecruitLimitRow findRowByProductIdInstitutionCodeBranchCode( long p_productId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BondBranchRecruitLimitRow.TYPE,
            "product_id=? and institution_code=? and branch_code=?",
            null,
            new Object[] { new Long(p_productId), p_institutionCode, p_branchCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BondBranchRecruitLimitRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BondBranchRecruitLimitDao.findRowsByProductIdInstitutionCodeBranchCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductIdInstitutionCodeBranchCode(long, String, String)}および{@@link #forRow(BondBranchRecruitLimitRow)}を使用してください。 
   */
    public static BondBranchRecruitLimitDao findDaoByProductIdInstitutionCodeBranchCode( long p_productId, String p_institutionCode, String p_branchCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductIdInstitutionCodeBranchCode( p_productId, p_institutionCode, p_branchCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_productId, and にて指定の値に一致する{@@link BondBranchRecruitLimitRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のp_productId, and の値と一致する{@@link BondBranchRecruitLimitRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondBranchRecruitLimitRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(BondBranchRecruitLimitRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductId( p_productId ) );
    }

}
@
