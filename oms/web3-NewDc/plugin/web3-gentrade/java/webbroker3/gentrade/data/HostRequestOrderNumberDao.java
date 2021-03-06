head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostRequestOrderNumberDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link HostRequestOrderNumberDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link HostRequestOrderNumberRow}インスタンスへ関連付けることができます。 
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
 * @@see HostRequestOrderNumberPK 
 * @@see HostRequestOrderNumberRow 
 */
public class HostRequestOrderNumberDao extends DataAccessObject {


  /** 
   * この{@@link HostRequestOrderNumberDao}に関連する型指定のRowオブジェクト 
   */
    private HostRequestOrderNumberRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link HostRequestOrderNumberRow}と仮定される{@@link DataAccessObject}から新たに{@@link HostRequestOrderNumberDao}を返します。 
         * @@return 指定のRowに結びつく{@@link HostRequestOrderNumberDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link HostRequestOrderNumberRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostRequestOrderNumberRow )
                return new HostRequestOrderNumberDao( (HostRequestOrderNumberRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostRequestOrderNumberRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostRequestOrderNumberRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link HostRequestOrderNumberRow}オブジェクト 
    */
    protected HostRequestOrderNumberDao( HostRequestOrderNumberRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link HostRequestOrderNumberRow}オブジェクトを取得します。
   */
    public HostRequestOrderNumberRow getHostRequestOrderNumberRow() {
        return row;
    }


  /** 
   * 指定の{@@link HostRequestOrderNumberRow}オブジェクトから{@@link HostRequestOrderNumberDao}オブジェクトを作成します。 
   * これは実際の{@@link HostRequestOrderNumberRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link HostRequestOrderNumberDao}取得のために指定の{@@link HostRequestOrderNumberRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link HostRequestOrderNumberDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static HostRequestOrderNumberDao forRow( HostRequestOrderNumberRow row ) throws java.lang.IllegalArgumentException {
        return (HostRequestOrderNumberDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostRequestOrderNumberRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link HostRequestOrderNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link HostRequestOrderNumberPK}やデータベースレコードとして挿入される{@@link HostRequestOrderNumberParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostRequestOrderNumberRow.TYPE );
    }


  /** 
   * {@@link HostRequestOrderNumberRow}を一意に特定する{@@link HostRequestOrderNumberPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link HostRequestOrderNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link HostRequestOrderNumberParams}オブジェクトの主キーとして利用可能な{@@link HostRequestOrderNumberPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static HostRequestOrderNumberPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link HostRequestOrderNumberRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostRequestOrderNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostRequestOrderNumberRow findRowByPk( String p_institutionCode, String p_branchCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        HostRequestOrderNumberPK pk = new HostRequestOrderNumberPK( p_institutionCode, p_branchCode, p_productType );
        return findRowByPk( pk );
    }


  /** 
   * 指定のHostRequestOrderNumberPKオブジェクトから{@@link HostRequestOrderNumberRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するHostRequestOrderNumberPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link HostRequestOrderNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static HostRequestOrderNumberRow findRowByPk( HostRequestOrderNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostRequestOrderNumberRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(HostRequestOrderNumberRow)}を使用してください。 
   */
    public static HostRequestOrderNumberDao findDaoByPk( String p_institutionCode, String p_branchCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        HostRequestOrderNumberPK pk = new HostRequestOrderNumberPK( p_institutionCode, p_branchCode, p_productType );
        HostRequestOrderNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(HostRequestOrderNumberPK)}および{@@link #forRow(HostRequestOrderNumberRow)}を使用してください。 
   */
    public static HostRequestOrderNumberDao findDaoByPk( HostRequestOrderNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostRequestOrderNumberRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_productType, and にて指定の値から一意の{@@link HostRequestOrderNumberRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_branchCode 検索対象であるp_branchCodeフィールドの値
   * @@param p_productType 検索対象であるp_productTypeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_branchCode, p_productType, and の値と一致する{@@link HostRequestOrderNumberRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static HostRequestOrderNumberRow findRowByInstitutionCodeBranchCodeProductType( String p_institutionCode, String p_branchCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostRequestOrderNumberRow.TYPE,
            "institution_code=? and branch_code=? and product_type=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_productType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostRequestOrderNumberRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostRequestOrderNumberDao.findRowsByInstitutionCodeBranchCodeProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeBranchCodeProductType(String, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}および{@@link #forRow(HostRequestOrderNumberRow)}を使用してください。 
   */
    public static HostRequestOrderNumberDao findDaoByInstitutionCodeBranchCodeProductType( String p_institutionCode, String p_branchCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeProductType( p_institutionCode, p_branchCode, p_productType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
