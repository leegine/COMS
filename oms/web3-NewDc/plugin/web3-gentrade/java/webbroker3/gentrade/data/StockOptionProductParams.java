head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	StockOptionProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * stock_option_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link StockOptionProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link StockOptionProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link StockOptionProductParams}が{@@link StockOptionProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see StockOptionProductPK 
 * @@see StockOptionProductRow 
 */
public class StockOptionProductParams extends Params implements StockOptionProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "stock_option_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = StockOptionProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return StockOptionProductRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>work_date</em>カラムの値 
   */
  public  String  work_date;

  /** 
   * <em>created_date</em>カラムの値 
   */
  public  String  created_date;

  /** 
   * <em>last_updated_date</em>カラムの値 
   */
  public  String  last_updated_date;

  /** 
   * <em>regist_div</em>カラムの値 
   */
  public  String  regist_div;

  /** 
   * <em>product</em>カラムの値 
   */
  public  String  product;

  /** 
   * <em>acc_trans_date</em>カラムの値 
   */
  public  String  acc_trans_date;

  /** 
   * <em>before_acc_trans_br</em>カラムの値 
   */
  public  String  before_acc_trans_br;

  /** 
   * <em>before_acc_trans_cust</em>カラムの値 
   */
  public  String  before_acc_trans_cust;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean work_date_is_set = false;
  private boolean work_date_is_modified = false;
  private boolean created_date_is_set = false;
  private boolean created_date_is_modified = false;
  private boolean last_updated_date_is_set = false;
  private boolean last_updated_date_is_modified = false;
  private boolean regist_div_is_set = false;
  private boolean regist_div_is_modified = false;
  private boolean product_is_set = false;
  private boolean product_is_modified = false;
  private boolean acc_trans_date_is_set = false;
  private boolean acc_trans_date_is_modified = false;
  private boolean before_acc_trans_br_is_set = false;
  private boolean before_acc_trans_br_is_modified = false;
  private boolean before_acc_trans_cust_is_set = false;
  private boolean before_acc_trans_cust_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_id=" + branch_id
      + "," + "account_id=" + account_id
      + "," + "product_id=" + product_id
      + "," + "work_date=" +work_date
      + "," + "created_date=" +created_date
      + "," + "last_updated_date=" +last_updated_date
      + "," + "regist_div=" +regist_div
      + "," + "product=" +product
      + "," + "acc_trans_date=" +acc_trans_date
      + "," + "before_acc_trans_br=" +before_acc_trans_br
      + "," + "before_acc_trans_cust=" +before_acc_trans_cust
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のStockOptionProductParamsオブジェクトを作成します。 
   */
  public StockOptionProductParams() {
    institution_code_is_modified = true;
    branch_id_is_modified = true;
    account_id_is_modified = true;
    product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のStockOptionProductRowオブジェクトの値を利用してStockOptionProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するStockOptionProductRowオブジェクト 
   */
  public StockOptionProductParams( StockOptionProductRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    work_date = p_row.getWorkDate();
    work_date_is_set = p_row.getWorkDateIsSet();
    work_date_is_modified = p_row.getWorkDateIsModified();
    created_date = p_row.getCreatedDate();
    created_date_is_set = p_row.getCreatedDateIsSet();
    created_date_is_modified = p_row.getCreatedDateIsModified();
    last_updated_date = p_row.getLastUpdatedDate();
    last_updated_date_is_set = p_row.getLastUpdatedDateIsSet();
    last_updated_date_is_modified = p_row.getLastUpdatedDateIsModified();
    regist_div = p_row.getRegistDiv();
    regist_div_is_set = p_row.getRegistDivIsSet();
    regist_div_is_modified = p_row.getRegistDivIsModified();
    product = p_row.getProduct();
    product_is_set = p_row.getProductIsSet();
    product_is_modified = p_row.getProductIsModified();
    acc_trans_date = p_row.getAccTransDate();
    acc_trans_date_is_set = p_row.getAccTransDateIsSet();
    acc_trans_date_is_modified = p_row.getAccTransDateIsModified();
    before_acc_trans_br = p_row.getBeforeAccTransBr();
    before_acc_trans_br_is_set = p_row.getBeforeAccTransBrIsSet();
    before_acc_trans_br_is_modified = p_row.getBeforeAccTransBrIsModified();
    before_acc_trans_cust = p_row.getBeforeAccTransCust();
    before_acc_trans_cust_is_set = p_row.getBeforeAccTransCustIsSet();
    before_acc_trans_cust_is_modified = p_row.getBeforeAccTransCustIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      work_date_is_set = true;
      work_date_is_modified = true;
      created_date_is_set = true;
      created_date_is_modified = true;
      last_updated_date_is_set = true;
      last_updated_date_is_modified = true;
      regist_div_is_set = true;
      regist_div_is_modified = true;
      product_is_set = true;
      product_is_modified = true;
      acc_trans_date_is_set = true;
      acc_trans_date_is_modified = true;
      before_acc_trans_br_is_set = true;
      before_acc_trans_br_is_modified = true;
      before_acc_trans_cust_is_set = true;
      before_acc_trans_cust_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof StockOptionProductRow ) )
       return false;
    return fieldsEqual( (StockOptionProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のStockOptionProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( StockOptionProductRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_id != row.getBranchId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( work_date == null ) {
      if ( row.getWorkDate() != null )
        return false;
    } else if ( !work_date.equals( row.getWorkDate() ) ) {
        return false;
    }
    if ( created_date == null ) {
      if ( row.getCreatedDate() != null )
        return false;
    } else if ( !created_date.equals( row.getCreatedDate() ) ) {
        return false;
    }
    if ( last_updated_date == null ) {
      if ( row.getLastUpdatedDate() != null )
        return false;
    } else if ( !last_updated_date.equals( row.getLastUpdatedDate() ) ) {
        return false;
    }
    if ( regist_div == null ) {
      if ( row.getRegistDiv() != null )
        return false;
    } else if ( !regist_div.equals( row.getRegistDiv() ) ) {
        return false;
    }
    if ( product == null ) {
      if ( row.getProduct() != null )
        return false;
    } else if ( !product.equals( row.getProduct() ) ) {
        return false;
    }
    if ( acc_trans_date == null ) {
      if ( row.getAccTransDate() != null )
        return false;
    } else if ( !acc_trans_date.equals( row.getAccTransDate() ) ) {
        return false;
    }
    if ( before_acc_trans_br == null ) {
      if ( row.getBeforeAccTransBr() != null )
        return false;
    } else if ( !before_acc_trans_br.equals( row.getBeforeAccTransBr() ) ) {
        return false;
    }
    if ( before_acc_trans_cust == null ) {
      if ( row.getBeforeAccTransCust() != null )
        return false;
    } else if ( !before_acc_trans_cust.equals( row.getBeforeAccTransCust() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) branch_id)
        + ((int) account_id)
        + ((int) product_id)
        + (work_date!=null? work_date.hashCode(): 0) 
        + (created_date!=null? created_date.hashCode(): 0) 
        + (last_updated_date!=null? last_updated_date.hashCode(): 0) 
        + (regist_div!=null? regist_div.hashCode(): 0) 
        + (product!=null? product.hashCode(): 0) 
        + (acc_trans_date!=null? acc_trans_date.hashCode(): 0) 
        + (before_acc_trans_br!=null? before_acc_trans_br.hashCode(): 0) 
        + (before_acc_trans_cust!=null? before_acc_trans_cust.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_id",new Long(branch_id));
		map.put("account_id",new Long(account_id));
		map.put("product_id",new Long(product_id));
		if ( work_date != null )
			map.put("work_date",work_date);
		if ( created_date != null )
			map.put("created_date",created_date);
		if ( last_updated_date != null )
			map.put("last_updated_date",last_updated_date);
		if ( regist_div != null )
			map.put("regist_div",regist_div);
		if ( product != null )
			map.put("product",product);
		if ( acc_trans_date != null )
			map.put("acc_trans_date",acc_trans_date);
		if ( before_acc_trans_br != null )
			map.put("before_acc_trans_br",before_acc_trans_br);
		if ( before_acc_trans_cust != null )
			map.put("before_acc_trans_cust",before_acc_trans_cust);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( work_date_is_modified )
			map.put("work_date",work_date);
		if ( created_date_is_modified )
			map.put("created_date",created_date);
		if ( last_updated_date_is_modified )
			map.put("last_updated_date",last_updated_date);
		if ( regist_div_is_modified )
			map.put("regist_div",regist_div);
		if ( product_is_modified )
			map.put("product",product);
		if ( acc_trans_date_is_modified )
			map.put("acc_trans_date",acc_trans_date);
		if ( before_acc_trans_br_is_modified )
			map.put("before_acc_trans_br",before_acc_trans_br);
		if ( before_acc_trans_cust_is_modified )
			map.put("before_acc_trans_cust",before_acc_trans_cust);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("work_date",work_date);
			map.put("created_date",created_date);
			map.put("last_updated_date",last_updated_date);
			map.put("regist_div",regist_div);
			map.put("product",product);
			map.put("acc_trans_date",acc_trans_date);
			map.put("before_acc_trans_br",before_acc_trans_br);
			map.put("before_acc_trans_cust",before_acc_trans_cust);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
  }


  /** 
   * <em>work_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getWorkDate()
  {
    return work_date;
  }


  /** 
   * <em>work_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDateIsSet() {
    return work_date_is_set;
  }


  /** 
   * <em>work_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDateIsModified() {
    return work_date_is_modified;
  }


  /** 
   * <em>created_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCreatedDate()
  {
    return created_date;
  }


  /** 
   * <em>created_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedDateIsSet() {
    return created_date_is_set;
  }


  /** 
   * <em>created_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedDateIsModified() {
    return created_date_is_modified;
  }


  /** 
   * <em>last_updated_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdatedDate()
  {
    return last_updated_date;
  }


  /** 
   * <em>last_updated_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedDateIsSet() {
    return last_updated_date_is_set;
  }


  /** 
   * <em>last_updated_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedDateIsModified() {
    return last_updated_date_is_modified;
  }


  /** 
   * <em>regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegistDiv()
  {
    return regist_div;
  }


  /** 
   * <em>regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDivIsSet() {
    return regist_div_is_set;
  }


  /** 
   * <em>regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDivIsModified() {
    return regist_div_is_modified;
  }


  /** 
   * <em>product</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProduct()
  {
    return product;
  }


  /** 
   * <em>product</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIsSet() {
    return product_is_set;
  }


  /** 
   * <em>product</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIsModified() {
    return product_is_modified;
  }


  /** 
   * <em>acc_trans_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccTransDate()
  {
    return acc_trans_date;
  }


  /** 
   * <em>acc_trans_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccTransDateIsSet() {
    return acc_trans_date_is_set;
  }


  /** 
   * <em>acc_trans_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccTransDateIsModified() {
    return acc_trans_date_is_modified;
  }


  /** 
   * <em>before_acc_trans_br</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBeforeAccTransBr()
  {
    return before_acc_trans_br;
  }


  /** 
   * <em>before_acc_trans_br</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeAccTransBrIsSet() {
    return before_acc_trans_br_is_set;
  }


  /** 
   * <em>before_acc_trans_br</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeAccTransBrIsModified() {
    return before_acc_trans_br_is_modified;
  }


  /** 
   * <em>before_acc_trans_cust</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBeforeAccTransCust()
  {
    return before_acc_trans_cust;
  }


  /** 
   * <em>before_acc_trans_cust</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeAccTransCustIsSet() {
    return before_acc_trans_cust_is_set;
  }


  /** 
   * <em>before_acc_trans_cust</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeAccTransCustIsModified() {
    return before_acc_trans_cust_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new StockOptionProductPK(institution_code, branch_id, account_id, product_id);
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>work_date</em>カラムの値を設定します。 
   *
   * @@param p_workDate <em>work_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setWorkDate( String p_workDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = p_workDate;
    work_date_is_set = true;
    work_date_is_modified = true;
  }


  /** 
   * <em>created_date</em>カラムの値を設定します。 
   *
   * @@param p_createdDate <em>created_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setCreatedDate( String p_createdDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_date = p_createdDate;
    created_date_is_set = true;
    created_date_is_modified = true;
  }


  /** 
   * <em>last_updated_date</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedDate <em>last_updated_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setLastUpdatedDate( String p_lastUpdatedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_date = p_lastUpdatedDate;
    last_updated_date_is_set = true;
    last_updated_date_is_modified = true;
  }


  /** 
   * <em>regist_div</em>カラムの値を設定します。 
   *
   * @@param p_registDiv <em>regist_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegistDiv( String p_registDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_div = p_registDiv;
    regist_div_is_set = true;
    regist_div_is_modified = true;
  }


  /** 
   * <em>product</em>カラムの値を設定します。 
   *
   * @@param p_product <em>product</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setProduct( String p_product )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product = p_product;
    product_is_set = true;
    product_is_modified = true;
  }


  /** 
   * <em>acc_trans_date</em>カラムの値を設定します。 
   *
   * @@param p_accTransDate <em>acc_trans_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setAccTransDate( String p_accTransDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_trans_date = p_accTransDate;
    acc_trans_date_is_set = true;
    acc_trans_date_is_modified = true;
  }


  /** 
   * <em>before_acc_trans_br</em>カラムの値を設定します。 
   *
   * @@param p_beforeAccTransBr <em>before_acc_trans_br</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBeforeAccTransBr( String p_beforeAccTransBr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_acc_trans_br = p_beforeAccTransBr;
    before_acc_trans_br_is_set = true;
    before_acc_trans_br_is_modified = true;
  }


  /** 
   * <em>before_acc_trans_cust</em>カラムの値を設定します。 
   *
   * @@param p_beforeAccTransCust <em>before_acc_trans_cust</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setBeforeAccTransCust( String p_beforeAccTransCust )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_acc_trans_cust = p_beforeAccTransCust;
    before_acc_trans_cust_is_set = true;
    before_acc_trans_cust_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("acc_trans_date") ) {
                    return this.acc_trans_date;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("before_acc_trans_br") ) {
                    return this.before_acc_trans_br;
                }
                else if ( name.equals("before_acc_trans_cust") ) {
                    return this.before_acc_trans_cust;
                }
                break;
            case 'c':
                if ( name.equals("created_date") ) {
                    return this.created_date;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_date") ) {
                    return this.last_updated_date;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product") ) {
                    return this.product;
                }
                break;
            case 'r':
                if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    return this.work_date;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("acc_trans_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_trans_date' must be String: '"+value+"' is not." );
                    this.acc_trans_date = (String) value;
                    if (this.acc_trans_date_is_set)
                        this.acc_trans_date_is_modified = true;
                    this.acc_trans_date_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("before_acc_trans_br") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'before_acc_trans_br' must be String: '"+value+"' is not." );
                    this.before_acc_trans_br = (String) value;
                    if (this.before_acc_trans_br_is_set)
                        this.before_acc_trans_br_is_modified = true;
                    this.before_acc_trans_br_is_set = true;
                    return;
                }
                else if ( name.equals("before_acc_trans_cust") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'before_acc_trans_cust' must be String: '"+value+"' is not." );
                    this.before_acc_trans_cust = (String) value;
                    if (this.before_acc_trans_cust_is_set)
                        this.before_acc_trans_cust_is_modified = true;
                    this.before_acc_trans_cust_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'created_date' must be String: '"+value+"' is not." );
                    this.created_date = (String) value;
                    if (this.created_date_is_set)
                        this.created_date_is_modified = true;
                    this.created_date_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updated_date' must be String: '"+value+"' is not." );
                    this.last_updated_date = (String) value;
                    if (this.last_updated_date_is_set)
                        this.last_updated_date_is_modified = true;
                    this.last_updated_date_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("product") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product' must be String: '"+value+"' is not." );
                    this.product = (String) value;
                    if (this.product_is_set)
                        this.product_is_modified = true;
                    this.product_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("regist_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_div' must be String: '"+value+"' is not." );
                    this.regist_div = (String) value;
                    if (this.regist_div_is_set)
                        this.regist_div_is_modified = true;
                    this.regist_div_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'work_date' must be String: '"+value+"' is not." );
                    this.work_date = (String) value;
                    if (this.work_date_is_set)
                        this.work_date_is_modified = true;
                    this.work_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
