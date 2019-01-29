head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.46.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	MqMessageIdMappingsDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mqgateway.stdimpls.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mqgateway.stdimpls.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link MqMessageIdMappingsDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MqMessageIdMappingsRow}インスタンスへ関連付けることができます。 
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
 * @@see MqMessageIdMappingsPK 
 * @@see MqMessageIdMappingsRow 
 */
public class MqMessageIdMappingsDao extends DataAccessObject {


  /** 
   * この{@@link MqMessageIdMappingsDao}に関連する型指定のRowオブジェクト 
   */
    private MqMessageIdMappingsRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MqMessageIdMappingsRow}と仮定される{@@link DataAccessObject}から新たに{@@link MqMessageIdMappingsDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MqMessageIdMappingsDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MqMessageIdMappingsRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MqMessageIdMappingsRow )
                return new MqMessageIdMappingsDao( (MqMessageIdMappingsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MqMessageIdMappingsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MqMessageIdMappingsRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MqMessageIdMappingsRow}オブジェクト 
    */
    protected MqMessageIdMappingsDao( MqMessageIdMappingsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MqMessageIdMappingsRow}オブジェクトを取得します。
   */
    public MqMessageIdMappingsRow getMqMessageIdMappingsRow() {
        return row;
    }


  /** 
   * 指定の{@@link MqMessageIdMappingsRow}オブジェクトから{@@link MqMessageIdMappingsDao}オブジェクトを作成します。 
   * これは実際の{@@link MqMessageIdMappingsRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MqMessageIdMappingsDao}取得のために指定の{@@link MqMessageIdMappingsRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MqMessageIdMappingsDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MqMessageIdMappingsDao forRow( MqMessageIdMappingsRow row ) throws java.lang.IllegalArgumentException {
        return (MqMessageIdMappingsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MqMessageIdMappingsRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MqMessageIdMappingsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MqMessageIdMappingsPK}やデータベースレコードとして挿入される{@@link MqMessageIdMappingsParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MqMessageIdMappingsRow.TYPE );
    }


  /** 
   * {@@link MqMessageIdMappingsRow}を一意に特定する{@@link MqMessageIdMappingsPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MqMessageIdMappingsRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MqMessageIdMappingsParams}オブジェクトの主キーとして利用可能な{@@link MqMessageIdMappingsPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MqMessageIdMappingsPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MqMessageIdMappingsRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_dataCode 検索対象であるp_dataCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MqMessageIdMappingsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MqMessageIdMappingsRow findRowByPk( String p_institutionCode, String p_dataCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MqMessageIdMappingsPK pk = new MqMessageIdMappingsPK( p_institutionCode, p_dataCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMqMessageIdMappingsPKオブジェクトから{@@link MqMessageIdMappingsRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMqMessageIdMappingsPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MqMessageIdMappingsRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MqMessageIdMappingsRow findRowByPk( MqMessageIdMappingsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MqMessageIdMappingsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(MqMessageIdMappingsRow)}を使用してください。 
   */
    public static MqMessageIdMappingsDao findDaoByPk( String p_institutionCode, String p_dataCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MqMessageIdMappingsPK pk = new MqMessageIdMappingsPK( p_institutionCode, p_dataCode );
        MqMessageIdMappingsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MqMessageIdMappingsPK)}および{@@link #forRow(MqMessageIdMappingsRow)}を使用してください。 
   */
    public static MqMessageIdMappingsDao findDaoByPk( MqMessageIdMappingsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MqMessageIdMappingsRow row = findRowByPk( pk );
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
   * p_institutionCode, p_dataCode, and にて指定の値から一意の{@@link MqMessageIdMappingsRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_dataCode 検索対象であるp_dataCodeフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_dataCode, and の値と一致する{@@link MqMessageIdMappingsRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static MqMessageIdMappingsRow findRowByInstitutionCodeDataCode( String p_institutionCode, String p_dataCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MqMessageIdMappingsRow.TYPE,
            "institution_code=? and data_code=?",
            null,
            new Object[] { p_institutionCode, p_dataCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MqMessageIdMappingsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MqMessageIdMappingsDao.findRowsByInstitutionCodeDataCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeDataCode(String, String)}および{@@link #forRow(MqMessageIdMappingsRow)}を使用してください。 
   */
    public static MqMessageIdMappingsDao findDaoByInstitutionCodeDataCode( String p_institutionCode, String p_dataCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeDataCode( p_institutionCode, p_dataCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
