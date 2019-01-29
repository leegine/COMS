head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.59.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformCtrlRequestNumberDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.inform.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link InformCtrlRequestNumberDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link InformCtrlRequestNumberRow}インスタンスへ関連付けることができます。 
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
 * @@see InformCtrlRequestNumberPK 
 * @@see InformCtrlRequestNumberRow 
 */
public class InformCtrlRequestNumberDao extends DataAccessObject {


  /** 
   * この{@@link InformCtrlRequestNumberDao}に関連する型指定のRowオブジェクト 
   */
    private InformCtrlRequestNumberRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link InformCtrlRequestNumberRow}と仮定される{@@link DataAccessObject}から新たに{@@link InformCtrlRequestNumberDao}を返します。 
         * @@return 指定のRowに結びつく{@@link InformCtrlRequestNumberDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link InformCtrlRequestNumberRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InformCtrlRequestNumberRow )
                return new InformCtrlRequestNumberDao( (InformCtrlRequestNumberRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InformCtrlRequestNumberRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InformCtrlRequestNumberRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link InformCtrlRequestNumberRow}オブジェクト 
    */
    protected InformCtrlRequestNumberDao( InformCtrlRequestNumberRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link InformCtrlRequestNumberRow}オブジェクトを取得します。
   */
    public InformCtrlRequestNumberRow getInformCtrlRequestNumberRow() {
        return row;
    }


  /** 
   * 指定の{@@link InformCtrlRequestNumberRow}オブジェクトから{@@link InformCtrlRequestNumberDao}オブジェクトを作成します。 
   * これは実際の{@@link InformCtrlRequestNumberRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link InformCtrlRequestNumberDao}取得のために指定の{@@link InformCtrlRequestNumberRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link InformCtrlRequestNumberDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static InformCtrlRequestNumberDao forRow( InformCtrlRequestNumberRow row ) throws java.lang.IllegalArgumentException {
        return (InformCtrlRequestNumberDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InformCtrlRequestNumberRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link InformCtrlRequestNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link InformCtrlRequestNumberPK}やデータベースレコードとして挿入される{@@link InformCtrlRequestNumberParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InformCtrlRequestNumberRow.TYPE );
    }


  /** 
   * {@@link InformCtrlRequestNumberRow}を一意に特定する{@@link InformCtrlRequestNumberPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link InformCtrlRequestNumberRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link InformCtrlRequestNumberParams}オブジェクトの主キーとして利用可能な{@@link InformCtrlRequestNumberPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static InformCtrlRequestNumberPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link InformCtrlRequestNumberRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformCtrlRequestNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformCtrlRequestNumberRow findRowByPk( String p_institutionCode, String p_informDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlRequestNumberPK pk = new InformCtrlRequestNumberPK( p_institutionCode, p_informDiv );
        return findRowByPk( pk );
    }


  /** 
   * 指定のInformCtrlRequestNumberPKオブジェクトから{@@link InformCtrlRequestNumberRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するInformCtrlRequestNumberPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link InformCtrlRequestNumberRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static InformCtrlRequestNumberRow findRowByPk( InformCtrlRequestNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InformCtrlRequestNumberRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(InformCtrlRequestNumberRow)}を使用してください。 
   */
    public static InformCtrlRequestNumberDao findDaoByPk( String p_institutionCode, String p_informDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlRequestNumberPK pk = new InformCtrlRequestNumberPK( p_institutionCode, p_informDiv );
        InformCtrlRequestNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(InformCtrlRequestNumberPK)}および{@@link #forRow(InformCtrlRequestNumberRow)}を使用してください。 
   */
    public static InformCtrlRequestNumberDao findDaoByPk( InformCtrlRequestNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InformCtrlRequestNumberRow row = findRowByPk( pk );
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
   * p_institutionCode, p_informDiv, and にて指定の値から一意の{@@link InformCtrlRequestNumberRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_informDiv 検索対象であるp_informDivフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_informDiv, and の値と一致する{@@link InformCtrlRequestNumberRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static InformCtrlRequestNumberRow findRowByInstitutionCodeInformDiv( String p_institutionCode, String p_informDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InformCtrlRequestNumberRow.TYPE,
            "institution_code=? and inform_div=?",
            null,
            new Object[] { p_institutionCode, p_informDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InformCtrlRequestNumberRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InformCtrlRequestNumberDao.findRowsByInstitutionCodeInformDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeInformDiv(String, String)}および{@@link #forRow(InformCtrlRequestNumberRow)}を使用してください。 
   */
    public static InformCtrlRequestNumberDao findDaoByInstitutionCodeInformDiv( String p_institutionCode, String p_informDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeInformDiv( p_institutionCode, p_informDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
