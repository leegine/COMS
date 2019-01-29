head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondIssueCouponTypeDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link BondIssueCouponTypeDao}は{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}のサブクラスで{@@link BondIssueCouponTypeRow}インスタンスへ関連付けることができます。 
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
 * @@see BondIssueCouponTypePK 
 * @@see BondIssueCouponTypeRow 
 */
public class BondIssueCouponTypeDao extends DataAccessObject {


  /** 
   * この{@@link BondIssueCouponTypeDao}に関連する型指定のRowオブジェクト 
   */
    private BondIssueCouponTypeRow row;


  /** 
   * Rowオブジェクトから新たにDataAccessObjectオブジェクトを作成するため利用されるファ@クトリ 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * 指定の{@@link BondIssueCouponTypeRow}と仮定される{@@link DataAccessObject}から新たに{@@link BondIssueCouponTypeDao}を返します。 
         * @@return 指定のRowに結びつく{@@link BondIssueCouponTypeDao}インスタンス 
         * @@exception java.lang.IllegalArgumentException 指定のRowオブジェクトが{@@link BondIssueCouponTypeRow}のタイプと一致しない場合 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondIssueCouponTypeRow )
                return new BondIssueCouponTypeDao( (BondIssueCouponTypeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondIssueCouponTypeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondIssueCouponTypeRow}を引数に取るコンストラクタです。これはファ@クトリおよびそのサブクラスのみにより利用されます。 
   * @@param row Daoにデータを提供する{@@link BondIssueCouponTypeRow}オブジェクト 
    */
    protected BondIssueCouponTypeDao( BondIssueCouponTypeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * このDaoに結びついている{@@link BondIssueCouponTypeRow}オブジェクトを取得します。
   */
    public BondIssueCouponTypeRow getBondIssueCouponTypeRow() {
        return row;
    }


  /** 
   * 指定の{@@link BondIssueCouponTypeRow}オブジェクトから{@@link BondIssueCouponTypeDao}オブジェクトを作成します。 
   * これは実際の{@@link BondIssueCouponTypeRow}クラスインスタンスをベースに戻り値として適切なDaoオブジェクトを 
   * ポリモルフィックに作成します。 
   * 
   * @@param row 必要な{@@link BondIssueCouponTypeDao}取得のために指定の{@@link BondIssueCouponTypeRow}オブジェクト 
   * @@return 指定のrowオブジェクトに対応する{@@link BondIssueCouponTypeDao}オブジェクト 
   * @@exception java.lang.IllegalArgumentException 指定のRowタイプに対応するDaoのタイプが存在しない場合 
   */
    public static BondIssueCouponTypeDao forRow( BondIssueCouponTypeRow row ) throws java.lang.IllegalArgumentException {
        return (BondIssueCouponTypeDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


      // (this object has no primary key components)


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


      // (this object has no primary key components)


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


  /** 
   * p_productId, and にて指定の値に一致する{@@link BondIssueCouponTypeRow}オブジェクトを{@@link List}として返します。 
   * 該当するオブジェクトがない場合は空のリストを返します。 
   * 
   * @@param p_productId 検索対象であるp_productIdフィールドの値
   * 
   * @@return 引数指定のp_productId, and の値と一致する{@@link BondIssueCouponTypeRow}オブジェクトの{@@link List} 
   * @@exception DataNetworkException ネットワークまたはその他インフラ関係の障害でクエリが実行できなかった場合 
   * @@exception DataQueryException クエリが実行されても何らかのデータ関連の理由から失敗した場合 
   */
    public static List findRowsByProductId( long p_productId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondIssueCouponTypeRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


  /** 
   * @@deprecated 代わりに{@@link #findRowsByProductId(long)}および{@@link #forRow(BondIssueCouponTypeRow)}を使用してください。 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductId( p_productId ) );
    }

}
@
