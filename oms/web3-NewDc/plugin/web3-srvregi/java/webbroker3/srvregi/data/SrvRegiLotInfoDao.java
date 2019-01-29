head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.42.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiLotInfoDao.java;


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
 * {@@link SrvRegiLotInfoDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link SrvRegiLotInfoRow}インスタンスへ関連付けることができます。 
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
 * @@see SrvRegiLotInfoPK 
 * @@see SrvRegiLotInfoRow 
 */
public class SrvRegiLotInfoDao extends DataAccessObject {


  /** 
   * この{@@link SrvRegiLotInfoDao}に関連する型指定のRowオブジェクト 
   */
    private SrvRegiLotInfoRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link SrvRegiLotInfoRow}と仮定される{@@link DataAccessObject}から新たに{@@link SrvRegiLotInfoDao}を返します。 
         * @@return 指定のRowに結びつく{@@link SrvRegiLotInfoDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link SrvRegiLotInfoRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SrvRegiLotInfoRow )
                return new SrvRegiLotInfoDao( (SrvRegiLotInfoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SrvRegiLotInfoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SrvRegiLotInfoRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link SrvRegiLotInfoRow}オブジェクト 
    */
    protected SrvRegiLotInfoDao( SrvRegiLotInfoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link SrvRegiLotInfoRow}オブジェクトを取得します。
   */
    public SrvRegiLotInfoRow getSrvRegiLotInfoRow() {
        return row;
    }


  /** 
   * 指定の{@@link SrvRegiLotInfoRow}オブジェクトから{@@link SrvRegiLotInfoDao}オブジェクトを作成します。 
   * これは実際の{@@link SrvRegiLotInfoRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link SrvRegiLotInfoDao}取得のために指定の{@@link SrvRegiLotInfoRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link SrvRegiLotInfoDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static SrvRegiLotInfoDao forRow( SrvRegiLotInfoRow row ) throws java.lang.IllegalArgumentException {
        return (SrvRegiLotInfoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SrvRegiLotInfoRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link SrvRegiLotInfoRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link SrvRegiLotInfoPK}やデータベースレコードとして挿入される{@@link SrvRegiLotInfoParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SrvRegiLotInfoRow.TYPE );
    }


  /** 
   * {@@link SrvRegiLotInfoRow}を一意に特定する{@@link SrvRegiLotInfoPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link SrvRegiLotInfoRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link SrvRegiLotInfoParams}オブジェクトの主キーとして利用可能な{@@link SrvRegiLotInfoPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static SrvRegiLotInfoPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link SrvRegiLotInfoRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_consecutiveNumbers 検索対象であるp_consecutiveNumbersフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiLotInfoRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiLotInfoRow findRowByPk( String p_institutionCode, String p_srvDiv, long p_consecutiveNumbers ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiLotInfoPK pk = new SrvRegiLotInfoPK( p_institutionCode, p_srvDiv, p_consecutiveNumbers );
        return findRowByPk( pk );
    }


  /** 
   * 指定のSrvRegiLotInfoPKオブジェクトから{@@link SrvRegiLotInfoRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するSrvRegiLotInfoPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link SrvRegiLotInfoRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static SrvRegiLotInfoRow findRowByPk( SrvRegiLotInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SrvRegiLotInfoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String,long)}および{@@link #forRow(SrvRegiLotInfoRow)}を使用してください。 
   */
    public static SrvRegiLotInfoDao findDaoByPk( String p_institutionCode, String p_srvDiv, long p_consecutiveNumbers ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiLotInfoPK pk = new SrvRegiLotInfoPK( p_institutionCode, p_srvDiv, p_consecutiveNumbers );
        SrvRegiLotInfoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(SrvRegiLotInfoPK)}および{@@link #forRow(SrvRegiLotInfoRow)}を使用してください。 
   */
    public static SrvRegiLotInfoDao findDaoByPk( SrvRegiLotInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SrvRegiLotInfoRow row = findRowByPk( pk );
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
   * p_institutionCode, p_srvDiv, p_consecutiveNumbers, and にて指定の値から一意の{@@link SrvRegiLotInfoRow}オブジェクトを検索します。 
   * 該当するオブジェクトがない場合はnullを返します。
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_srvDiv 検索対象であるp_srvDivフィールドの値
   * @@param p_consecutiveNumbers 検索対象であるp_consecutiveNumbersフィールドの値
   * 
   * @@return 引数指定のp_institutionCode, p_srvDiv, p_consecutiveNumbers, and の値と一致する{@@link SrvRegiLotInfoRow}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataFindException クエリ実行に成功したが実行結果を複数返した場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static SrvRegiLotInfoRow findRowByInstitutionCodeSrvDivConsecutiveNumbers( String p_institutionCode, String p_srvDiv, long p_consecutiveNumbers ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SrvRegiLotInfoRow.TYPE,
            "institution_code=? and srv_div=? and consecutive_numbers=?",
            null,
            new Object[] { p_institutionCode, p_srvDiv, new Long(p_consecutiveNumbers) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SrvRegiLotInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SrvRegiLotInfoDao.findRowsByInstitutionCodeSrvDivConsecutiveNumbers(): "+list.size() );
        }
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByInstitutionCodeSrvDivConsecutiveNumbers(String, String, long)}および{@@link #forRow(SrvRegiLotInfoRow)}を使用してください。 
   */
    public static SrvRegiLotInfoDao findDaoByInstitutionCodeSrvDivConsecutiveNumbers( String p_institutionCode, String p_srvDiv, long p_consecutiveNumbers ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeSrvDivConsecutiveNumbers( p_institutionCode, p_srvDiv, p_consecutiveNumbers ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
