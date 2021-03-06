head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFund2ndProductSonarDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MutualFund2ndProductSonarDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MutualFund2ndProductSonarRow}インスタンスへ関連付けることができます。 
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
 * @@see MutualFund2ndProductSonarPK 
 * @@see MutualFund2ndProductSonarRow 
 */
public class MutualFund2ndProductSonarDao extends DataAccessObject {


  /** 
   * この{@@link MutualFund2ndProductSonarDao}に関連する型指定のRowオブジェクト 
   */
    private MutualFund2ndProductSonarRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MutualFund2ndProductSonarRow}と仮定される{@@link DataAccessObject}から新たに{@@link MutualFund2ndProductSonarDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MutualFund2ndProductSonarDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MutualFund2ndProductSonarRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFund2ndProductSonarRow )
                return new MutualFund2ndProductSonarDao( (MutualFund2ndProductSonarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFund2ndProductSonarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFund2ndProductSonarRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MutualFund2ndProductSonarRow}オブジェクト 
    */
    protected MutualFund2ndProductSonarDao( MutualFund2ndProductSonarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MutualFund2ndProductSonarRow}オブジェクトを取得します。
   */
    public MutualFund2ndProductSonarRow getMutualFund2ndProductSonarRow() {
        return row;
    }


  /** 
   * 指定の{@@link MutualFund2ndProductSonarRow}オブジェクトから{@@link MutualFund2ndProductSonarDao}オブジェクトを作成します。 
   * これは実際の{@@link MutualFund2ndProductSonarRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MutualFund2ndProductSonarDao}取得のために指定の{@@link MutualFund2ndProductSonarRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MutualFund2ndProductSonarDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MutualFund2ndProductSonarDao forRow( MutualFund2ndProductSonarRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFund2ndProductSonarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFund2ndProductSonarRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MutualFund2ndProductSonarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MutualFund2ndProductSonarPK}やデータベースレコードとして挿入される{@@link MutualFund2ndProductSonarParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFund2ndProductSonarRow.TYPE );
    }


  /** 
   * {@@link MutualFund2ndProductSonarRow}を一意に特定する{@@link MutualFund2ndProductSonarPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MutualFund2ndProductSonarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MutualFund2ndProductSonarParams}オブジェクトの主キーとして利用可能な{@@link MutualFund2ndProductSonarPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MutualFund2ndProductSonarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MutualFund2ndProductSonarRow}オブジェクトを検索します。 
   * 
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFund2ndProductSonarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFund2ndProductSonarRow findRowByPk( String p_productCode, String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFund2ndProductSonarPK pk = new MutualFund2ndProductSonarPK( p_productCode, p_institutionCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMutualFund2ndProductSonarPKオブジェクトから{@@link MutualFund2ndProductSonarRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMutualFund2ndProductSonarPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFund2ndProductSonarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFund2ndProductSonarRow findRowByPk( MutualFund2ndProductSonarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFund2ndProductSonarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(MutualFund2ndProductSonarRow)}を使用してください。 
   */
    public static MutualFund2ndProductSonarDao findDaoByPk( String p_productCode, String p_institutionCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFund2ndProductSonarPK pk = new MutualFund2ndProductSonarPK( p_productCode, p_institutionCode );
        MutualFund2ndProductSonarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MutualFund2ndProductSonarPK)}および{@@link #forRow(MutualFund2ndProductSonarRow)}を使用してください。 
   */
    public static MutualFund2ndProductSonarDao findDaoByPk( MutualFund2ndProductSonarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFund2ndProductSonarRow row = findRowByPk( pk );
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
   * p_productCode, p_institutionCode, and にて指定の値から一意の{@@link MutualFund2ndProductSonarRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * 
   * @@return 引数指定のp_productCode, p_institutionCode, and の値と一致する{@@link MutualFund2ndProductSonarRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MutualFund2ndProductSonarRow findRowByProductCodeInstitutionCode( String p_productCode, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MutualFund2ndProductSonarRow.TYPE,
            "product_code=? and institution_code=?",
            null,
            new Object[] { p_productCode, p_institutionCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MutualFund2ndProductSonarRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MutualFund2ndProductSonarDao.findRowsByProductCodeInstitutionCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByProductCodeInstitutionCode(String, String)}および{@@link #forRow(MutualFund2ndProductSonarRow)}を使用してください。 
   */
    public static MutualFund2ndProductSonarDao findDaoByProductCodeInstitutionCode( String p_productCode, String p_institutionCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByProductCodeInstitutionCode( p_productCode, p_institutionCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
