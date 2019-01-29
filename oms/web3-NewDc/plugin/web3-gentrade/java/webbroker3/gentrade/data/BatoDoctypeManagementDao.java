head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.31.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BatoDoctypeManagementDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BatoDoctypeManagementDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BatoDoctypeManagementRow}インスタンスへ関連付けることができます。 
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
 * @@see BatoDoctypeManagementPK 
 * @@see BatoDoctypeManagementRow 
 */
public class BatoDoctypeManagementDao extends DataAccessObject {


  /** 
   * この{@@link BatoDoctypeManagementDao}に関連する型指定のRowオブジェクト 
   */
    private BatoDoctypeManagementRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BatoDoctypeManagementRow}と仮定される{@@link DataAccessObject}から新たに{@@link BatoDoctypeManagementDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BatoDoctypeManagementDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BatoDoctypeManagementRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BatoDoctypeManagementRow )
                return new BatoDoctypeManagementDao( (BatoDoctypeManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BatoDoctypeManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BatoDoctypeManagementRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BatoDoctypeManagementRow}オブジェクト 
    */
    protected BatoDoctypeManagementDao( BatoDoctypeManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BatoDoctypeManagementRow}オブジェクトを取得します。
   */
    public BatoDoctypeManagementRow getBatoDoctypeManagementRow() {
        return row;
    }


  /** 
   * 指定の{@@link BatoDoctypeManagementRow}オブジェクトから{@@link BatoDoctypeManagementDao}オブジェクトを作成します。 
   * これは実際の{@@link BatoDoctypeManagementRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BatoDoctypeManagementDao}取得のために指定の{@@link BatoDoctypeManagementRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BatoDoctypeManagementDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BatoDoctypeManagementDao forRow( BatoDoctypeManagementRow row ) throws java.lang.IllegalArgumentException {
        return (BatoDoctypeManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BatoDoctypeManagementRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link BatoDoctypeManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link BatoDoctypeManagementPK}やデータベースレコードとして挿入される{@@link BatoDoctypeManagementParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BatoDoctypeManagementRow.TYPE );
    }


  /** 
   * {@@link BatoDoctypeManagementRow}を一意に特定する{@@link BatoDoctypeManagementPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link BatoDoctypeManagementRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link BatoDoctypeManagementParams}オブジェクトの主キーとして利用可能な{@@link BatoDoctypeManagementPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static BatoDoctypeManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link BatoDoctypeManagementRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_typeCode 検索対象であるp_typeCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatoDoctypeManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatoDoctypeManagementRow findRowByPk( String p_institutionCode, String p_typeCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoDoctypeManagementPK pk = new BatoDoctypeManagementPK( p_institutionCode, p_typeCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のBatoDoctypeManagementPKオブジェクトから{@@link BatoDoctypeManagementRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するBatoDoctypeManagementPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link BatoDoctypeManagementRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static BatoDoctypeManagementRow findRowByPk( BatoDoctypeManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BatoDoctypeManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(BatoDoctypeManagementRow)}を使用してください。 
   */
    public static BatoDoctypeManagementDao findDaoByPk( String p_institutionCode, String p_typeCode ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoDoctypeManagementPK pk = new BatoDoctypeManagementPK( p_institutionCode, p_typeCode );
        BatoDoctypeManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(BatoDoctypeManagementPK)}および{@@link #forRow(BatoDoctypeManagementRow)}を使用してください。 
   */
    public static BatoDoctypeManagementDao findDaoByPk( BatoDoctypeManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BatoDoctypeManagementRow row = findRowByPk( pk );
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
   * p_institutionCode, p_typeCode, and にて指定の値から一意の{@@link BatoDoctypeManagementRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_typeCode 検索対象であるp_typeCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_typeCode, and の値と一致する{@@link BatoDoctypeManagementRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static BatoDoctypeManagementRow findRowByInstitutionCodeTypeCode( String p_institutionCode, String p_typeCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BatoDoctypeManagementRow.TYPE,
            "institution_code=? and type_code=?",
            null,
            new Object[] { p_institutionCode, p_typeCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BatoDoctypeManagementRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BatoDoctypeManagementDao.findRowsByInstitutionCodeTypeCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeTypeCode(String, String)}および{@@link #forRow(BatoDoctypeManagementRow)}を使用してください。 
   */
    public static BatoDoctypeManagementDao findDaoByInstitutionCodeTypeCode( String p_institutionCode, String p_typeCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeTypeCode( p_institutionCode, p_typeCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
