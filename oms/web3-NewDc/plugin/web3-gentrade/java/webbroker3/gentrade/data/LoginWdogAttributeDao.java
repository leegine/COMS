head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginWdogAttributeDao.java;


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
 * {@@link LoginWdogAttributeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link LoginWdogAttributeRow}インスタンスへ関連付けることができます。 
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
 * @@see LoginWdogAttributePK 
 * @@see LoginWdogAttributeRow 
 */
public class LoginWdogAttributeDao extends DataAccessObject {


  /** 
   * この{@@link LoginWdogAttributeDao}に関連する型指定のRowオブジェクト 
   */
    private LoginWdogAttributeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link LoginWdogAttributeRow}と仮定される{@@link DataAccessObject}から新たに{@@link LoginWdogAttributeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link LoginWdogAttributeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link LoginWdogAttributeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof LoginWdogAttributeRow )
                return new LoginWdogAttributeDao( (LoginWdogAttributeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a LoginWdogAttributeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link LoginWdogAttributeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link LoginWdogAttributeRow}オブジェクト 
    */
    protected LoginWdogAttributeDao( LoginWdogAttributeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link LoginWdogAttributeRow}オブジェクトを取得します。
   */
    public LoginWdogAttributeRow getLoginWdogAttributeRow() {
        return row;
    }


  /** 
   * 指定の{@@link LoginWdogAttributeRow}オブジェクトから{@@link LoginWdogAttributeDao}オブジェクトを作成します。 
   * これは実際の{@@link LoginWdogAttributeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link LoginWdogAttributeDao}取得のために指定の{@@link LoginWdogAttributeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link LoginWdogAttributeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static LoginWdogAttributeDao forRow( LoginWdogAttributeRow row ) throws java.lang.IllegalArgumentException {
        return (LoginWdogAttributeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link LoginWdogAttributeRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link LoginWdogAttributeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link LoginWdogAttributePK}やデータベースレコードとして挿入される{@@link LoginWdogAttributeParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( LoginWdogAttributeRow.TYPE );
    }


  /** 
   * {@@link LoginWdogAttributeRow}を一意に特定する{@@link LoginWdogAttributePK}オブジェクトを生成します。 
   * このオブジェクトは{@@link LoginWdogAttributeRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link LoginWdogAttributeParams}オブジェクトの主キーとして利用可能な{@@link LoginWdogAttributePK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static LoginWdogAttributePK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link LoginWdogAttributeRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_attributeName 検索対象であるp_attributeNameフィールドの値
   * @@param p_attributeNameSerialNo 検索対象であるp_attributeNameSerialNoフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link LoginWdogAttributeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static LoginWdogAttributeRow findRowByPk( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginWdogAttributePK pk = new LoginWdogAttributePK( p_institutionCode, p_attributeName, p_attributeNameSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * 指定のLoginWdogAttributePKオブジェクトから{@@link LoginWdogAttributeRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するLoginWdogAttributePKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link LoginWdogAttributeRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static LoginWdogAttributeRow findRowByPk( LoginWdogAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (LoginWdogAttributeRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,int)}および{@@link #forRow(LoginWdogAttributeRow)}を使用してください。 
   */
    public static LoginWdogAttributeDao findDaoByPk( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginWdogAttributePK pk = new LoginWdogAttributePK( p_institutionCode, p_attributeName, p_attributeNameSerialNo );
        LoginWdogAttributeRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(LoginWdogAttributePK)}および{@@link #forRow(LoginWdogAttributeRow)}を使用してください。 
   */
    public static LoginWdogAttributeDao findDaoByPk( LoginWdogAttributePK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        LoginWdogAttributeRow row = findRowByPk( pk );
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
   * p_institutionCode, p_attributeName, p_attributeNameSerialNo, and にて指定の値から一意の{@@link LoginWdogAttributeRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_attributeName 検索対象であるp_attributeNameフィールドの値
   * @@param p_attributeNameSerialNo 検索対象であるp_attributeNameSerialNoフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_attributeName, p_attributeNameSerialNo, and の値と一致する{@@link LoginWdogAttributeRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static LoginWdogAttributeRow findRowByInstitutionCodeAttributeNameAttributeNameSerialNo( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            LoginWdogAttributeRow.TYPE,
            "institution_code=? and attribute_name=? and attribute_name_serial_no=?",
            null,
            new Object[] { p_institutionCode, p_attributeName, new Integer(p_attributeNameSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (LoginWdogAttributeRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query LoginWdogAttributeDao.findRowsByInstitutionCodeAttributeNameAttributeNameSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeAttributeNameAttributeNameSerialNo(String, String, int)}および{@@link #forRow(LoginWdogAttributeRow)}を使用してください。 
   */
    public static LoginWdogAttributeDao findDaoByInstitutionCodeAttributeNameAttributeNameSerialNo( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAttributeNameAttributeNameSerialNo( p_institutionCode, p_attributeName, p_attributeNameSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
