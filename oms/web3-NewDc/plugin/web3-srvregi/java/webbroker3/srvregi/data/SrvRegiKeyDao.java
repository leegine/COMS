head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiKeyDao.java;


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
 * {@@link SrvRegiKeyDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiKeyRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiKeyPK 
 * @@see SrvRegiKeyRow 
 */
public class SrvRegiKeyDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiKeyDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiKeyRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiKeyRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiKeyDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiKeyDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiKeyRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiKeyRow )
                return new SrvRegiKeyDao( (SrvRegiKeyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiKeyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiKeyRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiKeyRow}オブジェクト 
    */
    protected SrvRegiKeyDao( SrvRegiKeyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiKeyRow}オブジェクトを取得します。
   */
    public SrvRegiKeyRow getSrvRegiKeyRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiKeyRow}オブジェクトから{@@link SrvRegiKeyDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiKeyRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiKeyDao}取得のために指定の{@@link SrvRegiKeyRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiKeyDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiKeyDao forRow( SrvRegiKeyRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiKeyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiKeyRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiKeyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiKeyPK}やデータベースレコードとして挿入される{@@link SrvRegiKeyParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiKeyRow.TYPE );
    }


  /** 
   * {@@link SrvRegiKeyRow}を一意に特定する{@@link SrvRegiKeyPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiKeyRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiKeyParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiKeyPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiKeyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiKeyRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_srvUseKeyType 検索対象であるp_srvUseKeyTypeフィールドの値
   * @@param p_srvUseId 検索対象であるp_srvUseIdフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiKeyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiKeyRow findRowByPk( String p_institutionCode, String p_srvDiv, String p_srvUseKeyType, long p_srvUseId ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiKeyPK pk = new SrvRegiKeyPK( p_institutionCode, p_srvDiv, p_srvUseKeyType, p_srvUseId );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiKeyPKオブジェクトから{@@link SrvRegiKeyRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiKeyPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiKeyRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiKeyRow findRowByPk( SrvRegiKeyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiKeyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,String,long)}および{@@link #forRow(SrvRegiKeyRow)}を使用してください。 
   */
    public static SrvRegiKeyDao findDaoByPk( String p_institutionCode, String p_srvDiv, String p_srvUseKeyType, long p_srvUseId ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiKeyPK pk = new SrvRegiKeyPK( p_institutionCode, p_srvDiv, p_srvUseKeyType, p_srvUseId );
        SrvRegiKeyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiKeyPK)}および{@@link #forRow(SrvRegiKeyRow)}を使用してください。 
   */
    public static SrvRegiKeyDao findDaoByPk( SrvRegiKeyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiKeyRow row = findRowByPk( pk );
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
   * p_institutionCode, p_srvDiv, p_srvUseKeyType, p_srvUseId, and にて指定の値から一意の{@@link SrvRegiKeyRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_srvUseKeyType 検索対象であるp_srvUseKeyTypeフィールドの値
   * @@param p_srvUseId 検索対象であるp_srvUseIdフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_srvDiv, p_srvUseKeyType, p_srvUseId, and の値と一致する{@@link SrvRegiKeyRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiKeyRow findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId( String p_institutionCode, String p_srvDiv, String p_srvUseKeyType, long p_srvUseId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiKeyRow.TYPE,
            "institution_code=? and srv_div=? and srv_use_key_type=? and srv_use_id=?",
            null,
            new Object[] { p_institutionCode, p_srvDiv, p_srvUseKeyType, new Long(p_srvUseId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiKeyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiKeyDao.findRowsByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(String, String, String, long)}および{@@link #forRow(SrvRegiKeyRow)}を使用してください。 
   */
    public static SrvRegiKeyDao findDaoByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId( String p_institutionCode, String p_srvDiv, String p_srvUseKeyType, long p_srvUseId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId( p_institutionCode, p_srvDiv, p_srvUseKeyType, p_srvUseId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
