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
filename	MutualFundProductSonarDao.java;


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
 * {@@link MutualFundProductSonarDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link MutualFundProductSonarRow}インスタンスへ関連付けることができます。 
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
 * @@see MutualFundProductSonarPK 
 * @@see MutualFundProductSonarRow 
 */
public class MutualFundProductSonarDao extends DataAccessObject {


  /** 
   * この{@@link MutualFundProductSonarDao}に関連する型指定のRowオブジェクト 
   */
    private MutualFundProductSonarRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link MutualFundProductSonarRow}と仮定される{@@link DataAccessObject}から新たに{@@link MutualFundProductSonarDao}を返します。 
         * @@return 指定のRowに結びつく{@@link MutualFundProductSonarDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link MutualFundProductSonarRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundProductSonarRow )
                return new MutualFundProductSonarDao( (MutualFundProductSonarRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundProductSonarRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundProductSonarRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link MutualFundProductSonarRow}オブジェクト 
    */
    protected MutualFundProductSonarDao( MutualFundProductSonarRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link MutualFundProductSonarRow}オブジェクトを取得します。
   */
    public MutualFundProductSonarRow getMutualFundProductSonarRow() {
        return row;
    }


  /** 
   * 指定の{@@link MutualFundProductSonarRow}オブジェクトから{@@link MutualFundProductSonarDao}オブジェクトを作成します。 
   * これは実際の{@@link MutualFundProductSonarRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link MutualFundProductSonarDao}取得のために指定の{@@link MutualFundProductSonarRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link MutualFundProductSonarDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static MutualFundProductSonarDao forRow( MutualFundProductSonarRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundProductSonarDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFundProductSonarRow}を一意に特定するlong型の値を生成します。 
   * この値は{@@link MutualFundProductSonarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return 新しい{@@link MutualFundProductSonarPK}やデータベースレコードとして挿入される{@@link MutualFundProductSonarParams}インスタンスの主キーとして利用可能なlong型の値 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFundProductSonarRow.TYPE );
    }


  /** 
   * {@@link MutualFundProductSonarRow}を一意に特定する{@@link MutualFundProductSonarPK}オブジェクトを生成します。 
   * このオブジェクトは{@@link MutualFundProductSonarRow}のオブジェクトタイプに対応するものです。 
   * 
   * @@return データベースへ挿入する新たな{@@link MutualFundProductSonarParams}オブジェクトの主キーとして利用可能な{@@link MutualFundProductSonarPK}オブジェクト 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   * @@exception UnsupportedOperationException primary_keyに複数のカラムが含まれているかカラムのタイプがlong型でない場合 
   */
    public static MutualFundProductSonarPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * 指定の主キーの値から{@@link MutualFundProductSonarRow}オブジェクトを検索します。 
   * 
   * @@param p_productCode 検索対象であるp_productCodeフィールドの値
   * 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundProductSonarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundProductSonarRow findRowByPk( String p_productCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundProductSonarPK pk = new MutualFundProductSonarPK( p_productCode );
        return findRowByPk( pk );
    }


  /** 
   * 指定のMutualFundProductSonarPKオブジェクトから{@@link MutualFundProductSonarRow}オブジェクトを検索します。 
   * 
   * @@param pk 検索キーとして利用するMutualFundProductSonarPKオブジェクト 
   * @@return 引数指定のIDと一致する主キーを持つ{@@link MutualFundProductSonarRow} 
   * @@exception DataFindException クエリ自体は実行されたが、指定の主キーにて検索のオブジェクトが見つからなかった場合 
   * @@exception DataQueryException クエリ自体は実行されたが、検索キーが一意でないなど何らかのデータ関連の理由で検索に失敗した場合 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   */
    public static MutualFundProductSonarRow findRowByPk( MutualFundProductSonarPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFundProductSonarRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(String)}および{@@link #forRow(MutualFundProductSonarRow)}を使用してください。 
   */
    public static MutualFundProductSonarDao findDaoByPk( String p_productCode ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundProductSonarPK pk = new MutualFundProductSonarPK( p_productCode );
        MutualFundProductSonarRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowByPk(MutualFundProductSonarPK)}および{@@link #forRow(MutualFundProductSonarRow)}を使用してください。 
   */
    public static MutualFundProductSonarDao findDaoByPk( MutualFundProductSonarPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundProductSonarRow row = findRowByPk( pk );
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
