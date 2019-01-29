head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCondSonarDao.java;


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
 * {@@link MutualFundInstCondSonarDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MutualFundInstCondSonarRow}インスタンスへ関連付けることができます。 
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
 * @@see MutualFundInstCondSonarPK 
 * @@see MutualFundInstCondSonarRow 
 */
public class MutualFundInstCondSonarDao extends DataAccessObject {


  /** 
   * この{@@link MutualFundInstCondSonarDao}に関連する型指定のRowオブジェクト 
   */
    private MutualFundInstCondSonarRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MutualFundInstCondSonarRow}と仮定される{@@link DataAccessObject}から新たに{@@link MutualFundInstCondSonarDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MutualFundInstCondSonarDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MutualFundInstCondSonarRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundInstCondSonarRow )
                return new MutualFundInstCondSonarDao( (MutualFundInstCondSonarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundInstCondSonarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundInstCondSonarRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MutualFundInstCondSonarRow}オブジェクト 
    */
    protected MutualFundInstCondSonarDao( MutualFundInstCondSonarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MutualFundInstCondSonarRow}オブジェクトを取得します。
   */
    public MutualFundInstCondSonarRow getMutualFundInstCondSonarRow() {
        return row;
    }


  /** 
   * 指定の{@@link MutualFundInstCondSonarRow}オブジェクトから{@@link MutualFundInstCondSonarDao}オブジェクトを作成します。 
   * これは実際の{@@link MutualFundInstCondSonarRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MutualFundInstCondSonarDao}取得のために指定の{@@link MutualFundInstCondSonarRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MutualFundInstCondSonarDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MutualFundInstCondSonarDao forRow( MutualFundInstCondSonarRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundInstCondSonarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFundInstCondSonarRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MutualFundInstCondSonarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MutualFundInstCondSonarPK}やデータベースレコードとして挿入される{@@link MutualFundInstCondSonarParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFundInstCondSonarRow.TYPE );
    }


  /** 
   * {@@link MutualFundInstCondSonarRow}を一意に特定する{@@link MutualFundInstCondSonarPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MutualFundInstCondSonarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MutualFundInstCondSonarParams}オブジェクトの主キーとして利用可能な{@@link MutualFundInstCondSonarPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MutualFundInstCondSonarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MutualFundInstCondSonarRow}オブジェクトを検索します。 
   * 
   * @@param p_institutionCode 検索対象であるp_institutionCodeフィールドの値
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundInstCondSonarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundInstCondSonarRow findRowByPk( String p_institutionCode, String p_productCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCondSonarPK pk = new MutualFundInstCondSonarPK( p_institutionCode, p_productCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMutualFundInstCondSonarPKオブジェクトから{@@link MutualFundInstCondSonarRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMutualFundInstCondSonarPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundInstCondSonarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundInstCondSonarRow findRowByPk( MutualFundInstCondSonarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFundInstCondSonarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String,String)}および{@@link #forRow(MutualFundInstCondSonarRow)}を使用してください。 
   */
    public static MutualFundInstCondSonarDao findDaoByPk( String p_institutionCode, String p_productCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCondSonarPK pk = new MutualFundInstCondSonarPK( p_institutionCode, p_productCode );
        MutualFundInstCondSonarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MutualFundInstCondSonarPK)}および{@@link #forRow(MutualFundInstCondSonarRow)}を使用してください。 
   */
    public static MutualFundInstCondSonarDao findDaoByPk( MutualFundInstCondSonarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundInstCondSonarRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
