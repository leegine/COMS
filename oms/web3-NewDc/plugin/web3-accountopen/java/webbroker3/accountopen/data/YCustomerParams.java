head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.20.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	YCustomerParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * y_customerテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link YCustomerRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link YCustomerParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link YCustomerParams}が{@@link YCustomerRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see YCustomerPK 
 * @@see YCustomerRow 
 */
public class YCustomerParams extends Params implements YCustomerRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "y_customer";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = YCustomerRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return YCustomerRow.TYPE;
  }


  /** 
   * <em>y_customer_id</em>カラムの値 
   */
  public  long  y_customer_id;

  /** 
   * <em>control_branch_code</em>カラムの値 
   */
  public  String  control_branch_code;

  /** 
   * <em>operation_div</em>カラムの値 
   */
  public  String  operation_div;

  /** 
   * <em>control_number</em>カラムの値 
   */
  public  int  control_number;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>era_born</em>カラムの値 
   */
  public  String  era_born;

  /** 
   * <em>born_date</em>カラムの値 
   */
  public  String  born_date;

  /** 
   * <em>name_kana</em>カラムの値 
   */
  public  String  name_kana;

  /** 
   * <em>name</em>カラムの値 
   */
  public  String  name;

  /** 
   * <em>account_name_kana</em>カラムの値 
   */
  public  String  account_name_kana;

  /** 
   * <em>account_name</em>カラムの値 
   */
  public  String  account_name;

  /** 
   * <em>address_kana</em>カラムの値 
   */
  public  String  address_kana;

  /** 
   * <em>address</em>カラムの値 
   */
  public  String  address;

  /** 
   * <em>zip_code</em>カラムの値 
   */
  public  String  zip_code;

  /** 
   * <em>telephone</em>カラムの値 
   */
  public  String  telephone;

  /** 
   * <em>office_kana</em>カラムの値 
   */
  public  String  office_kana;

  /** 
   * <em>office</em>カラムの値 
   */
  public  String  office;

  /** 
   * <em>post_kana</em>カラムの値 
   */
  public  String  post_kana;

  /** 
   * <em>post</em>カラムの値 
   */
  public  String  post;

  /** 
   * <em>comment_kana</em>カラムの値 
   */
  public  String  comment_kana;

  /** 
   * <em>comment_kanji</em>カラムの値 
   */
  public  String  comment_kanji;

  /** 
   * <em>sonar_created_branch</em>カラムの値 
   */
  public  String  sonar_created_branch;

  /** 
   * <em>sonar_created_date</em>カラムの値 
   */
  public  String  sonar_created_date;

  /** 
   * <em>sonar_updated_branch</em>カラムの値 
   */
  public  String  sonar_updated_branch;

  /** 
   * <em>sonar_updated_date</em>カラムの値 
   */
  public  String  sonar_updated_date;

  /** 
   * <em>disclosure_div</em>カラムの値 
   */
  public  String  disclosure_div;

  /** 
   * <em>sonar_check_div</em>カラムの値 
   */
  public  String  sonar_check_div;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean y_customer_id_is_set = false;
  private boolean y_customer_id_is_modified = false;
  private boolean control_branch_code_is_set = false;
  private boolean control_branch_code_is_modified = false;
  private boolean operation_div_is_set = false;
  private boolean operation_div_is_modified = false;
  private boolean control_number_is_set = false;
  private boolean control_number_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean era_born_is_set = false;
  private boolean era_born_is_modified = false;
  private boolean born_date_is_set = false;
  private boolean born_date_is_modified = false;
  private boolean name_kana_is_set = false;
  private boolean name_kana_is_modified = false;
  private boolean name_is_set = false;
  private boolean name_is_modified = false;
  private boolean account_name_kana_is_set = false;
  private boolean account_name_kana_is_modified = false;
  private boolean account_name_is_set = false;
  private boolean account_name_is_modified = false;
  private boolean address_kana_is_set = false;
  private boolean address_kana_is_modified = false;
  private boolean address_is_set = false;
  private boolean address_is_modified = false;
  private boolean zip_code_is_set = false;
  private boolean zip_code_is_modified = false;
  private boolean telephone_is_set = false;
  private boolean telephone_is_modified = false;
  private boolean office_kana_is_set = false;
  private boolean office_kana_is_modified = false;
  private boolean office_is_set = false;
  private boolean office_is_modified = false;
  private boolean post_kana_is_set = false;
  private boolean post_kana_is_modified = false;
  private boolean post_is_set = false;
  private boolean post_is_modified = false;
  private boolean comment_kana_is_set = false;
  private boolean comment_kana_is_modified = false;
  private boolean comment_kanji_is_set = false;
  private boolean comment_kanji_is_modified = false;
  private boolean sonar_created_branch_is_set = false;
  private boolean sonar_created_branch_is_modified = false;
  private boolean sonar_created_date_is_set = false;
  private boolean sonar_created_date_is_modified = false;
  private boolean sonar_updated_branch_is_set = false;
  private boolean sonar_updated_branch_is_modified = false;
  private boolean sonar_updated_date_is_set = false;
  private boolean sonar_updated_date_is_modified = false;
  private boolean disclosure_div_is_set = false;
  private boolean disclosure_div_is_modified = false;
  private boolean sonar_check_div_is_set = false;
  private boolean sonar_check_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "y_customer_id=" + y_customer_id
      + "," + "control_branch_code=" +control_branch_code
      + "," + "operation_div=" +operation_div
      + "," + "control_number=" +control_number
      + "," + "institution_code=" +institution_code
      + "," + "era_born=" +era_born
      + "," + "born_date=" +born_date
      + "," + "name_kana=" +name_kana
      + "," + "name=" +name
      + "," + "account_name_kana=" +account_name_kana
      + "," + "account_name=" +account_name
      + "," + "address_kana=" +address_kana
      + "," + "address=" +address
      + "," + "zip_code=" +zip_code
      + "," + "telephone=" +telephone
      + "," + "office_kana=" +office_kana
      + "," + "office=" +office
      + "," + "post_kana=" +post_kana
      + "," + "post=" +post
      + "," + "comment_kana=" +comment_kana
      + "," + "comment_kanji=" +comment_kanji
      + "," + "sonar_created_branch=" +sonar_created_branch
      + "," + "sonar_created_date=" +sonar_created_date
      + "," + "sonar_updated_branch=" +sonar_updated_branch
      + "," + "sonar_updated_date=" +sonar_updated_date
      + "," + "disclosure_div=" +disclosure_div
      + "," + "sonar_check_div=" +sonar_check_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のYCustomerParamsオブジェクトを作成します。 
   */
  public YCustomerParams() {
    y_customer_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のYCustomerRowオブジェクトの値を利用してYCustomerParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するYCustomerRowオブジェクト 
   */
  public YCustomerParams( YCustomerRow p_row )
  {
    y_customer_id = p_row.getYCustomerId();
    y_customer_id_is_set = p_row.getYCustomerIdIsSet();
    y_customer_id_is_modified = p_row.getYCustomerIdIsModified();
    control_branch_code = p_row.getControlBranchCode();
    control_branch_code_is_set = p_row.getControlBranchCodeIsSet();
    control_branch_code_is_modified = p_row.getControlBranchCodeIsModified();
    operation_div = p_row.getOperationDiv();
    operation_div_is_set = p_row.getOperationDivIsSet();
    operation_div_is_modified = p_row.getOperationDivIsModified();
    control_number = p_row.getControlNumber();
    control_number_is_set = p_row.getControlNumberIsSet();
    control_number_is_modified = p_row.getControlNumberIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    era_born = p_row.getEraBorn();
    era_born_is_set = p_row.getEraBornIsSet();
    era_born_is_modified = p_row.getEraBornIsModified();
    born_date = p_row.getBornDate();
    born_date_is_set = p_row.getBornDateIsSet();
    born_date_is_modified = p_row.getBornDateIsModified();
    name_kana = p_row.getNameKana();
    name_kana_is_set = p_row.getNameKanaIsSet();
    name_kana_is_modified = p_row.getNameKanaIsModified();
    name = p_row.getName();
    name_is_set = p_row.getNameIsSet();
    name_is_modified = p_row.getNameIsModified();
    account_name_kana = p_row.getAccountNameKana();
    account_name_kana_is_set = p_row.getAccountNameKanaIsSet();
    account_name_kana_is_modified = p_row.getAccountNameKanaIsModified();
    account_name = p_row.getAccountName();
    account_name_is_set = p_row.getAccountNameIsSet();
    account_name_is_modified = p_row.getAccountNameIsModified();
    address_kana = p_row.getAddressKana();
    address_kana_is_set = p_row.getAddressKanaIsSet();
    address_kana_is_modified = p_row.getAddressKanaIsModified();
    address = p_row.getAddress();
    address_is_set = p_row.getAddressIsSet();
    address_is_modified = p_row.getAddressIsModified();
    zip_code = p_row.getZipCode();
    zip_code_is_set = p_row.getZipCodeIsSet();
    zip_code_is_modified = p_row.getZipCodeIsModified();
    telephone = p_row.getTelephone();
    telephone_is_set = p_row.getTelephoneIsSet();
    telephone_is_modified = p_row.getTelephoneIsModified();
    office_kana = p_row.getOfficeKana();
    office_kana_is_set = p_row.getOfficeKanaIsSet();
    office_kana_is_modified = p_row.getOfficeKanaIsModified();
    office = p_row.getOffice();
    office_is_set = p_row.getOfficeIsSet();
    office_is_modified = p_row.getOfficeIsModified();
    post_kana = p_row.getPostKana();
    post_kana_is_set = p_row.getPostKanaIsSet();
    post_kana_is_modified = p_row.getPostKanaIsModified();
    post = p_row.getPost();
    post_is_set = p_row.getPostIsSet();
    post_is_modified = p_row.getPostIsModified();
    comment_kana = p_row.getCommentKana();
    comment_kana_is_set = p_row.getCommentKanaIsSet();
    comment_kana_is_modified = p_row.getCommentKanaIsModified();
    comment_kanji = p_row.getCommentKanji();
    comment_kanji_is_set = p_row.getCommentKanjiIsSet();
    comment_kanji_is_modified = p_row.getCommentKanjiIsModified();
    sonar_created_branch = p_row.getSonarCreatedBranch();
    sonar_created_branch_is_set = p_row.getSonarCreatedBranchIsSet();
    sonar_created_branch_is_modified = p_row.getSonarCreatedBranchIsModified();
    sonar_created_date = p_row.getSonarCreatedDate();
    sonar_created_date_is_set = p_row.getSonarCreatedDateIsSet();
    sonar_created_date_is_modified = p_row.getSonarCreatedDateIsModified();
    sonar_updated_branch = p_row.getSonarUpdatedBranch();
    sonar_updated_branch_is_set = p_row.getSonarUpdatedBranchIsSet();
    sonar_updated_branch_is_modified = p_row.getSonarUpdatedBranchIsModified();
    sonar_updated_date = p_row.getSonarUpdatedDate();
    sonar_updated_date_is_set = p_row.getSonarUpdatedDateIsSet();
    sonar_updated_date_is_modified = p_row.getSonarUpdatedDateIsModified();
    disclosure_div = p_row.getDisclosureDiv();
    disclosure_div_is_set = p_row.getDisclosureDivIsSet();
    disclosure_div_is_modified = p_row.getDisclosureDivIsModified();
    sonar_check_div = p_row.getSonarCheckDiv();
    sonar_check_div_is_set = p_row.getSonarCheckDivIsSet();
    sonar_check_div_is_modified = p_row.getSonarCheckDivIsModified();
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
      control_branch_code_is_set = true;
      control_branch_code_is_modified = true;
      operation_div_is_set = true;
      operation_div_is_modified = true;
      control_number_is_set = true;
      control_number_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      era_born_is_set = true;
      era_born_is_modified = true;
      born_date_is_set = true;
      born_date_is_modified = true;
      name_kana_is_set = true;
      name_kana_is_modified = true;
      name_is_set = true;
      name_is_modified = true;
      account_name_kana_is_set = true;
      account_name_kana_is_modified = true;
      account_name_is_set = true;
      account_name_is_modified = true;
      address_kana_is_set = true;
      address_kana_is_modified = true;
      address_is_set = true;
      address_is_modified = true;
      zip_code_is_set = true;
      zip_code_is_modified = true;
      telephone_is_set = true;
      telephone_is_modified = true;
      office_kana_is_set = true;
      office_kana_is_modified = true;
      office_is_set = true;
      office_is_modified = true;
      post_kana_is_set = true;
      post_kana_is_modified = true;
      post_is_set = true;
      post_is_modified = true;
      comment_kana_is_set = true;
      comment_kana_is_modified = true;
      comment_kanji_is_set = true;
      comment_kanji_is_modified = true;
      sonar_created_branch_is_set = true;
      sonar_created_branch_is_modified = true;
      sonar_created_date_is_set = true;
      sonar_created_date_is_modified = true;
      sonar_updated_branch_is_set = true;
      sonar_updated_branch_is_modified = true;
      sonar_updated_date_is_set = true;
      sonar_updated_date_is_modified = true;
      disclosure_div_is_set = true;
      disclosure_div_is_modified = true;
      sonar_check_div_is_set = true;
      sonar_check_div_is_modified = true;
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
    if ( !( other instanceof YCustomerRow ) )
       return false;
    return fieldsEqual( (YCustomerRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のYCustomerRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( YCustomerRow row )
  {
    if ( y_customer_id != row.getYCustomerId() )
      return false;
    if ( control_branch_code == null ) {
      if ( row.getControlBranchCode() != null )
        return false;
    } else if ( !control_branch_code.equals( row.getControlBranchCode() ) ) {
        return false;
    }
    if ( operation_div == null ) {
      if ( row.getOperationDiv() != null )
        return false;
    } else if ( !operation_div.equals( row.getOperationDiv() ) ) {
        return false;
    }
    if ( control_number != row.getControlNumber() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( era_born == null ) {
      if ( row.getEraBorn() != null )
        return false;
    } else if ( !era_born.equals( row.getEraBorn() ) ) {
        return false;
    }
    if ( born_date == null ) {
      if ( row.getBornDate() != null )
        return false;
    } else if ( !born_date.equals( row.getBornDate() ) ) {
        return false;
    }
    if ( name_kana == null ) {
      if ( row.getNameKana() != null )
        return false;
    } else if ( !name_kana.equals( row.getNameKana() ) ) {
        return false;
    }
    if ( name == null ) {
      if ( row.getName() != null )
        return false;
    } else if ( !name.equals( row.getName() ) ) {
        return false;
    }
    if ( account_name_kana == null ) {
      if ( row.getAccountNameKana() != null )
        return false;
    } else if ( !account_name_kana.equals( row.getAccountNameKana() ) ) {
        return false;
    }
    if ( account_name == null ) {
      if ( row.getAccountName() != null )
        return false;
    } else if ( !account_name.equals( row.getAccountName() ) ) {
        return false;
    }
    if ( address_kana == null ) {
      if ( row.getAddressKana() != null )
        return false;
    } else if ( !address_kana.equals( row.getAddressKana() ) ) {
        return false;
    }
    if ( address == null ) {
      if ( row.getAddress() != null )
        return false;
    } else if ( !address.equals( row.getAddress() ) ) {
        return false;
    }
    if ( zip_code == null ) {
      if ( row.getZipCode() != null )
        return false;
    } else if ( !zip_code.equals( row.getZipCode() ) ) {
        return false;
    }
    if ( telephone == null ) {
      if ( row.getTelephone() != null )
        return false;
    } else if ( !telephone.equals( row.getTelephone() ) ) {
        return false;
    }
    if ( office_kana == null ) {
      if ( row.getOfficeKana() != null )
        return false;
    } else if ( !office_kana.equals( row.getOfficeKana() ) ) {
        return false;
    }
    if ( office == null ) {
      if ( row.getOffice() != null )
        return false;
    } else if ( !office.equals( row.getOffice() ) ) {
        return false;
    }
    if ( post_kana == null ) {
      if ( row.getPostKana() != null )
        return false;
    } else if ( !post_kana.equals( row.getPostKana() ) ) {
        return false;
    }
    if ( post == null ) {
      if ( row.getPost() != null )
        return false;
    } else if ( !post.equals( row.getPost() ) ) {
        return false;
    }
    if ( comment_kana == null ) {
      if ( row.getCommentKana() != null )
        return false;
    } else if ( !comment_kana.equals( row.getCommentKana() ) ) {
        return false;
    }
    if ( comment_kanji == null ) {
      if ( row.getCommentKanji() != null )
        return false;
    } else if ( !comment_kanji.equals( row.getCommentKanji() ) ) {
        return false;
    }
    if ( sonar_created_branch == null ) {
      if ( row.getSonarCreatedBranch() != null )
        return false;
    } else if ( !sonar_created_branch.equals( row.getSonarCreatedBranch() ) ) {
        return false;
    }
    if ( sonar_created_date == null ) {
      if ( row.getSonarCreatedDate() != null )
        return false;
    } else if ( !sonar_created_date.equals( row.getSonarCreatedDate() ) ) {
        return false;
    }
    if ( sonar_updated_branch == null ) {
      if ( row.getSonarUpdatedBranch() != null )
        return false;
    } else if ( !sonar_updated_branch.equals( row.getSonarUpdatedBranch() ) ) {
        return false;
    }
    if ( sonar_updated_date == null ) {
      if ( row.getSonarUpdatedDate() != null )
        return false;
    } else if ( !sonar_updated_date.equals( row.getSonarUpdatedDate() ) ) {
        return false;
    }
    if ( disclosure_div == null ) {
      if ( row.getDisclosureDiv() != null )
        return false;
    } else if ( !disclosure_div.equals( row.getDisclosureDiv() ) ) {
        return false;
    }
    if ( sonar_check_div == null ) {
      if ( row.getSonarCheckDiv() != null )
        return false;
    } else if ( !sonar_check_div.equals( row.getSonarCheckDiv() ) ) {
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
      return  ((int) y_customer_id)
        + (control_branch_code!=null? control_branch_code.hashCode(): 0) 
        + (operation_div!=null? operation_div.hashCode(): 0) 
        + ((int) control_number)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (era_born!=null? era_born.hashCode(): 0) 
        + (born_date!=null? born_date.hashCode(): 0) 
        + (name_kana!=null? name_kana.hashCode(): 0) 
        + (name!=null? name.hashCode(): 0) 
        + (account_name_kana!=null? account_name_kana.hashCode(): 0) 
        + (account_name!=null? account_name.hashCode(): 0) 
        + (address_kana!=null? address_kana.hashCode(): 0) 
        + (address!=null? address.hashCode(): 0) 
        + (zip_code!=null? zip_code.hashCode(): 0) 
        + (telephone!=null? telephone.hashCode(): 0) 
        + (office_kana!=null? office_kana.hashCode(): 0) 
        + (office!=null? office.hashCode(): 0) 
        + (post_kana!=null? post_kana.hashCode(): 0) 
        + (post!=null? post.hashCode(): 0) 
        + (comment_kana!=null? comment_kana.hashCode(): 0) 
        + (comment_kanji!=null? comment_kanji.hashCode(): 0) 
        + (sonar_created_branch!=null? sonar_created_branch.hashCode(): 0) 
        + (sonar_created_date!=null? sonar_created_date.hashCode(): 0) 
        + (sonar_updated_branch!=null? sonar_updated_branch.hashCode(): 0) 
        + (sonar_updated_date!=null? sonar_updated_date.hashCode(): 0) 
        + (disclosure_div!=null? disclosure_div.hashCode(): 0) 
        + (sonar_check_div!=null? sonar_check_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !control_branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'control_branch_code' must be set before inserting.");
		if ( !operation_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'operation_div' must be set before inserting.");
		if ( !control_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'control_number' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !name_kana_is_set )
			throw new IllegalArgumentException("non-nullable field 'name_kana' must be set before inserting.");
		if ( !name_is_set )
			throw new IllegalArgumentException("non-nullable field 'name' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("y_customer_id",new Long(y_customer_id));
		map.put("control_branch_code",control_branch_code);
		map.put("operation_div",operation_div);
		map.put("control_number",new Integer(control_number));
		map.put("institution_code",institution_code);
		if ( era_born != null )
			map.put("era_born",era_born);
		if ( born_date != null )
			map.put("born_date",born_date);
		map.put("name_kana",name_kana);
		map.put("name",name);
		if ( account_name_kana != null )
			map.put("account_name_kana",account_name_kana);
		if ( account_name != null )
			map.put("account_name",account_name);
		if ( address_kana != null )
			map.put("address_kana",address_kana);
		if ( address != null )
			map.put("address",address);
		if ( zip_code != null )
			map.put("zip_code",zip_code);
		if ( telephone != null )
			map.put("telephone",telephone);
		if ( office_kana != null )
			map.put("office_kana",office_kana);
		if ( office != null )
			map.put("office",office);
		if ( post_kana != null )
			map.put("post_kana",post_kana);
		if ( post != null )
			map.put("post",post);
		if ( comment_kana != null )
			map.put("comment_kana",comment_kana);
		if ( comment_kanji != null )
			map.put("comment_kanji",comment_kanji);
		if ( sonar_created_branch != null )
			map.put("sonar_created_branch",sonar_created_branch);
		if ( sonar_created_date != null )
			map.put("sonar_created_date",sonar_created_date);
		if ( sonar_updated_branch != null )
			map.put("sonar_updated_branch",sonar_updated_branch);
		if ( sonar_updated_date != null )
			map.put("sonar_updated_date",sonar_updated_date);
		if ( disclosure_div != null )
			map.put("disclosure_div",disclosure_div);
		if ( sonar_check_div != null )
			map.put("sonar_check_div",sonar_check_div);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( control_branch_code_is_modified )
			map.put("control_branch_code",control_branch_code);
		if ( operation_div_is_modified )
			map.put("operation_div",operation_div);
		if ( control_number_is_modified )
			map.put("control_number",new Integer(control_number));
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( era_born_is_modified )
			map.put("era_born",era_born);
		if ( born_date_is_modified )
			map.put("born_date",born_date);
		if ( name_kana_is_modified )
			map.put("name_kana",name_kana);
		if ( name_is_modified )
			map.put("name",name);
		if ( account_name_kana_is_modified )
			map.put("account_name_kana",account_name_kana);
		if ( account_name_is_modified )
			map.put("account_name",account_name);
		if ( address_kana_is_modified )
			map.put("address_kana",address_kana);
		if ( address_is_modified )
			map.put("address",address);
		if ( zip_code_is_modified )
			map.put("zip_code",zip_code);
		if ( telephone_is_modified )
			map.put("telephone",telephone);
		if ( office_kana_is_modified )
			map.put("office_kana",office_kana);
		if ( office_is_modified )
			map.put("office",office);
		if ( post_kana_is_modified )
			map.put("post_kana",post_kana);
		if ( post_is_modified )
			map.put("post",post);
		if ( comment_kana_is_modified )
			map.put("comment_kana",comment_kana);
		if ( comment_kanji_is_modified )
			map.put("comment_kanji",comment_kanji);
		if ( sonar_created_branch_is_modified )
			map.put("sonar_created_branch",sonar_created_branch);
		if ( sonar_created_date_is_modified )
			map.put("sonar_created_date",sonar_created_date);
		if ( sonar_updated_branch_is_modified )
			map.put("sonar_updated_branch",sonar_updated_branch);
		if ( sonar_updated_date_is_modified )
			map.put("sonar_updated_date",sonar_updated_date);
		if ( disclosure_div_is_modified )
			map.put("disclosure_div",disclosure_div);
		if ( sonar_check_div_is_modified )
			map.put("sonar_check_div",sonar_check_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( control_branch_code_is_set )
				map.put("control_branch_code",control_branch_code);
			if ( operation_div_is_set )
				map.put("operation_div",operation_div);
			if ( control_number_is_set )
				map.put("control_number",new Integer(control_number));
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("era_born",era_born);
			map.put("born_date",born_date);
			if ( name_kana_is_set )
				map.put("name_kana",name_kana);
			if ( name_is_set )
				map.put("name",name);
			map.put("account_name_kana",account_name_kana);
			map.put("account_name",account_name);
			map.put("address_kana",address_kana);
			map.put("address",address);
			map.put("zip_code",zip_code);
			map.put("telephone",telephone);
			map.put("office_kana",office_kana);
			map.put("office",office);
			map.put("post_kana",post_kana);
			map.put("post",post);
			map.put("comment_kana",comment_kana);
			map.put("comment_kanji",comment_kanji);
			map.put("sonar_created_branch",sonar_created_branch);
			map.put("sonar_created_date",sonar_created_date);
			map.put("sonar_updated_branch",sonar_updated_branch);
			map.put("sonar_updated_date",sonar_updated_date);
			map.put("disclosure_div",disclosure_div);
			map.put("sonar_check_div",sonar_check_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>y_customer_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getYCustomerId()
  {
    return y_customer_id;
  }


  /** 
   * <em>y_customer_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYCustomerIdIsSet() {
    return y_customer_id_is_set;
  }


  /** 
   * <em>y_customer_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYCustomerIdIsModified() {
    return y_customer_id_is_modified;
  }


  /** 
   * <em>control_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getControlBranchCode()
  {
    return control_branch_code;
  }


  /** 
   * <em>control_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getControlBranchCodeIsSet() {
    return control_branch_code_is_set;
  }


  /** 
   * <em>control_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getControlBranchCodeIsModified() {
    return control_branch_code_is_modified;
  }


  /** 
   * <em>operation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperationDiv()
  {
    return operation_div;
  }


  /** 
   * <em>operation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationDivIsSet() {
    return operation_div_is_set;
  }


  /** 
   * <em>operation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationDivIsModified() {
    return operation_div_is_modified;
  }


  /** 
   * <em>control_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getControlNumber()
  {
    return control_number;
  }


  /** 
   * <em>control_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getControlNumberIsSet() {
    return control_number_is_set;
  }


  /** 
   * <em>control_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getControlNumberIsModified() {
    return control_number_is_modified;
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
   * <em>era_born</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEraBorn()
  {
    return era_born;
  }


  /** 
   * <em>era_born</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEraBornIsSet() {
    return era_born_is_set;
  }


  /** 
   * <em>era_born</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEraBornIsModified() {
    return era_born_is_modified;
  }


  /** 
   * <em>born_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBornDate()
  {
    return born_date;
  }


  /** 
   * <em>born_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBornDateIsSet() {
    return born_date_is_set;
  }


  /** 
   * <em>born_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBornDateIsModified() {
    return born_date_is_modified;
  }


  /** 
   * <em>name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNameKana()
  {
    return name_kana;
  }


  /** 
   * <em>name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameKanaIsSet() {
    return name_kana_is_set;
  }


  /** 
   * <em>name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameKanaIsModified() {
    return name_kana_is_modified;
  }


  /** 
   * <em>name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getName()
  {
    return name;
  }


  /** 
   * <em>name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameIsSet() {
    return name_is_set;
  }


  /** 
   * <em>name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameIsModified() {
    return name_is_modified;
  }


  /** 
   * <em>account_name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountNameKana()
  {
    return account_name_kana;
  }


  /** 
   * <em>account_name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameKanaIsSet() {
    return account_name_kana_is_set;
  }


  /** 
   * <em>account_name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameKanaIsModified() {
    return account_name_kana_is_modified;
  }


  /** 
   * <em>account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountName()
  {
    return account_name;
  }


  /** 
   * <em>account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsSet() {
    return account_name_is_set;
  }


  /** 
   * <em>account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsModified() {
    return account_name_is_modified;
  }


  /** 
   * <em>address_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressKana()
  {
    return address_kana;
  }


  /** 
   * <em>address_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressKanaIsSet() {
    return address_kana_is_set;
  }


  /** 
   * <em>address_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressKanaIsModified() {
    return address_kana_is_modified;
  }


  /** 
   * <em>address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddress()
  {
    return address;
  }


  /** 
   * <em>address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressIsSet() {
    return address_is_set;
  }


  /** 
   * <em>address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressIsModified() {
    return address_is_modified;
  }


  /** 
   * <em>zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getZipCode()
  {
    return zip_code;
  }


  /** 
   * <em>zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCodeIsSet() {
    return zip_code_is_set;
  }


  /** 
   * <em>zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCodeIsModified() {
    return zip_code_is_modified;
  }


  /** 
   * <em>telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTelephone()
  {
    return telephone;
  }


  /** 
   * <em>telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephoneIsSet() {
    return telephone_is_set;
  }


  /** 
   * <em>telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephoneIsModified() {
    return telephone_is_modified;
  }


  /** 
   * <em>office_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOfficeKana()
  {
    return office_kana;
  }


  /** 
   * <em>office_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeKanaIsSet() {
    return office_kana_is_set;
  }


  /** 
   * <em>office_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeKanaIsModified() {
    return office_kana_is_modified;
  }


  /** 
   * <em>office</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOffice()
  {
    return office;
  }


  /** 
   * <em>office</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeIsSet() {
    return office_is_set;
  }


  /** 
   * <em>office</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeIsModified() {
    return office_is_modified;
  }


  /** 
   * <em>post_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPostKana()
  {
    return post_kana;
  }


  /** 
   * <em>post_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostKanaIsSet() {
    return post_kana_is_set;
  }


  /** 
   * <em>post_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostKanaIsModified() {
    return post_kana_is_modified;
  }


  /** 
   * <em>post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPost()
  {
    return post;
  }


  /** 
   * <em>post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostIsSet() {
    return post_is_set;
  }


  /** 
   * <em>post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostIsModified() {
    return post_is_modified;
  }


  /** 
   * <em>comment_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommentKana()
  {
    return comment_kana;
  }


  /** 
   * <em>comment_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommentKanaIsSet() {
    return comment_kana_is_set;
  }


  /** 
   * <em>comment_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommentKanaIsModified() {
    return comment_kana_is_modified;
  }


  /** 
   * <em>comment_kanji</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommentKanji()
  {
    return comment_kanji;
  }


  /** 
   * <em>comment_kanji</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommentKanjiIsSet() {
    return comment_kanji_is_set;
  }


  /** 
   * <em>comment_kanji</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommentKanjiIsModified() {
    return comment_kanji_is_modified;
  }


  /** 
   * <em>sonar_created_branch</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarCreatedBranch()
  {
    return sonar_created_branch;
  }


  /** 
   * <em>sonar_created_branch</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCreatedBranchIsSet() {
    return sonar_created_branch_is_set;
  }


  /** 
   * <em>sonar_created_branch</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCreatedBranchIsModified() {
    return sonar_created_branch_is_modified;
  }


  /** 
   * <em>sonar_created_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarCreatedDate()
  {
    return sonar_created_date;
  }


  /** 
   * <em>sonar_created_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCreatedDateIsSet() {
    return sonar_created_date_is_set;
  }


  /** 
   * <em>sonar_created_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCreatedDateIsModified() {
    return sonar_created_date_is_modified;
  }


  /** 
   * <em>sonar_updated_branch</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarUpdatedBranch()
  {
    return sonar_updated_branch;
  }


  /** 
   * <em>sonar_updated_branch</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarUpdatedBranchIsSet() {
    return sonar_updated_branch_is_set;
  }


  /** 
   * <em>sonar_updated_branch</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarUpdatedBranchIsModified() {
    return sonar_updated_branch_is_modified;
  }


  /** 
   * <em>sonar_updated_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarUpdatedDate()
  {
    return sonar_updated_date;
  }


  /** 
   * <em>sonar_updated_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarUpdatedDateIsSet() {
    return sonar_updated_date_is_set;
  }


  /** 
   * <em>sonar_updated_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarUpdatedDateIsModified() {
    return sonar_updated_date_is_modified;
  }


  /** 
   * <em>disclosure_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDisclosureDiv()
  {
    return disclosure_div;
  }


  /** 
   * <em>disclosure_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisclosureDivIsSet() {
    return disclosure_div_is_set;
  }


  /** 
   * <em>disclosure_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisclosureDivIsModified() {
    return disclosure_div_is_modified;
  }


  /** 
   * <em>sonar_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarCheckDiv()
  {
    return sonar_check_div;
  }


  /** 
   * <em>sonar_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCheckDivIsSet() {
    return sonar_check_div_is_set;
  }


  /** 
   * <em>sonar_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCheckDivIsModified() {
    return sonar_check_div_is_modified;
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
    return new YCustomerPK(y_customer_id);
  }


  /** 
   * <em>y_customer_id</em>カラムの値を設定します。 
   *
   * @@param p_yCustomerId <em>y_customer_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setYCustomerId( long p_yCustomerId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    y_customer_id = p_yCustomerId;
    y_customer_id_is_set = true;
    y_customer_id_is_modified = true;
  }


  /** 
   * <em>control_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_controlBranchCode <em>control_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setControlBranchCode( String p_controlBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    control_branch_code = p_controlBranchCode;
    control_branch_code_is_set = true;
    control_branch_code_is_modified = true;
  }


  /** 
   * <em>operation_div</em>カラムの値を設定します。 
   *
   * @@param p_operationDiv <em>operation_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOperationDiv( String p_operationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operation_div = p_operationDiv;
    operation_div_is_set = true;
    operation_div_is_modified = true;
  }


  /** 
   * <em>control_number</em>カラムの値を設定します。 
   *
   * @@param p_controlNumber <em>control_number</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setControlNumber( int p_controlNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    control_number = p_controlNumber;
    control_number_is_set = true;
    control_number_is_modified = true;
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
   * <em>era_born</em>カラムの値を設定します。 
   *
   * @@param p_eraBorn <em>era_born</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEraBorn( String p_eraBorn )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    era_born = p_eraBorn;
    era_born_is_set = true;
    era_born_is_modified = true;
  }


  /** 
   * <em>born_date</em>カラムの値を設定します。 
   *
   * @@param p_bornDate <em>born_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setBornDate( String p_bornDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    born_date = p_bornDate;
    born_date_is_set = true;
    born_date_is_modified = true;
  }


  /** 
   * <em>name_kana</em>カラムの値を設定します。 
   *
   * @@param p_nameKana <em>name_kana</em>カラムの値をあらわす42桁以下のString値 
   */
  public final void setNameKana( String p_nameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_kana = p_nameKana;
    name_kana_is_set = true;
    name_kana_is_modified = true;
  }


  /** 
   * <em>name</em>カラムの値を設定します。 
   *
   * @@param p_name <em>name</em>カラムの値をあらわす42桁以下のString値 
   */
  public final void setName( String p_name )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name = p_name;
    name_is_set = true;
    name_is_modified = true;
  }


  /** 
   * <em>account_name_kana</em>カラムの値を設定します。 
   *
   * @@param p_accountNameKana <em>account_name_kana</em>カラムの値をあらわす42桁以下のString値 
   */
  public final void setAccountNameKana( String p_accountNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name_kana = p_accountNameKana;
    account_name_kana_is_set = true;
    account_name_kana_is_modified = true;
  }


  /** 
   * <em>account_name</em>カラムの値を設定します。 
   *
   * @@param p_accountName <em>account_name</em>カラムの値をあらわす42桁以下のString値 
   */
  public final void setAccountName( String p_accountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name = p_accountName;
    account_name_is_set = true;
    account_name_is_modified = true;
  }


  /** 
   * <em>address_kana</em>カラムの値を設定します。 
   *
   * @@param p_addressKana <em>address_kana</em>カラムの値をあらわす104桁以下のString値 
   */
  public final void setAddressKana( String p_addressKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_kana = p_addressKana;
    address_kana_is_set = true;
    address_kana_is_modified = true;
  }


  /** 
   * <em>address</em>カラムの値を設定します。 
   *
   * @@param p_address <em>address</em>カラムの値をあらわす104桁以下のString値 
   */
  public final void setAddress( String p_address )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address = p_address;
    address_is_set = true;
    address_is_modified = true;
  }


  /** 
   * <em>zip_code</em>カラムの値を設定します。 
   *
   * @@param p_zipCode <em>zip_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setZipCode( String p_zipCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    zip_code = p_zipCode;
    zip_code_is_set = true;
    zip_code_is_modified = true;
  }


  /** 
   * <em>telephone</em>カラムの値を設定します。 
   *
   * @@param p_telephone <em>telephone</em>カラムの値をあらわす14桁以下のString値 
   */
  public final void setTelephone( String p_telephone )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    telephone = p_telephone;
    telephone_is_set = true;
    telephone_is_modified = true;
  }


  /** 
   * <em>office_kana</em>カラムの値を設定します。 
   *
   * @@param p_officeKana <em>office_kana</em>カラムの値をあらわす52桁以下のString値 
   */
  public final void setOfficeKana( String p_officeKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    office_kana = p_officeKana;
    office_kana_is_set = true;
    office_kana_is_modified = true;
  }


  /** 
   * <em>office</em>カラムの値を設定します。 
   *
   * @@param p_office <em>office</em>カラムの値をあらわす52桁以下のString値 
   */
  public final void setOffice( String p_office )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    office = p_office;
    office_is_set = true;
    office_is_modified = true;
  }


  /** 
   * <em>post_kana</em>カラムの値を設定します。 
   *
   * @@param p_postKana <em>post_kana</em>カラムの値をあらわす102桁以下のString値 
   */
  public final void setPostKana( String p_postKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    post_kana = p_postKana;
    post_kana_is_set = true;
    post_kana_is_modified = true;
  }


  /** 
   * <em>post</em>カラムの値を設定します。 
   *
   * @@param p_post <em>post</em>カラムの値をあらわす102桁以下のString値 
   */
  public final void setPost( String p_post )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    post = p_post;
    post_is_set = true;
    post_is_modified = true;
  }


  /** 
   * <em>comment_kana</em>カラムの値を設定します。 
   *
   * @@param p_commentKana <em>comment_kana</em>カラムの値をあらわす52桁以下のString値 
   */
  public final void setCommentKana( String p_commentKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment_kana = p_commentKana;
    comment_kana_is_set = true;
    comment_kana_is_modified = true;
  }


  /** 
   * <em>comment_kanji</em>カラムの値を設定します。 
   *
   * @@param p_commentKanji <em>comment_kanji</em>カラムの値をあらわす52桁以下のString値 
   */
  public final void setCommentKanji( String p_commentKanji )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comment_kanji = p_commentKanji;
    comment_kanji_is_set = true;
    comment_kanji_is_modified = true;
  }


  /** 
   * <em>sonar_created_branch</em>カラムの値を設定します。 
   *
   * @@param p_sonarCreatedBranch <em>sonar_created_branch</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSonarCreatedBranch( String p_sonarCreatedBranch )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_created_branch = p_sonarCreatedBranch;
    sonar_created_branch_is_set = true;
    sonar_created_branch_is_modified = true;
  }


  /** 
   * <em>sonar_created_date</em>カラムの値を設定します。 
   *
   * @@param p_sonarCreatedDate <em>sonar_created_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setSonarCreatedDate( String p_sonarCreatedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_created_date = p_sonarCreatedDate;
    sonar_created_date_is_set = true;
    sonar_created_date_is_modified = true;
  }


  /** 
   * <em>sonar_updated_branch</em>カラムの値を設定します。 
   *
   * @@param p_sonarUpdatedBranch <em>sonar_updated_branch</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSonarUpdatedBranch( String p_sonarUpdatedBranch )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_updated_branch = p_sonarUpdatedBranch;
    sonar_updated_branch_is_set = true;
    sonar_updated_branch_is_modified = true;
  }


  /** 
   * <em>sonar_updated_date</em>カラムの値を設定します。 
   *
   * @@param p_sonarUpdatedDate <em>sonar_updated_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setSonarUpdatedDate( String p_sonarUpdatedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_updated_date = p_sonarUpdatedDate;
    sonar_updated_date_is_set = true;
    sonar_updated_date_is_modified = true;
  }


  /** 
   * <em>disclosure_div</em>カラムの値を設定します。 
   *
   * @@param p_disclosureDiv <em>disclosure_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDisclosureDiv( String p_disclosureDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    disclosure_div = p_disclosureDiv;
    disclosure_div_is_set = true;
    disclosure_div_is_modified = true;
  }


  /** 
   * <em>sonar_check_div</em>カラムの値を設定します。 
   *
   * @@param p_sonarCheckDiv <em>sonar_check_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSonarCheckDiv( String p_sonarCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_check_div = p_sonarCheckDiv;
    sonar_check_div_is_set = true;
    sonar_check_div_is_modified = true;
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
                if ( name.equals("account_name_kana") ) {
                    return this.account_name_kana;
                }
                else if ( name.equals("account_name") ) {
                    return this.account_name;
                }
                else if ( name.equals("address_kana") ) {
                    return this.address_kana;
                }
                else if ( name.equals("address") ) {
                    return this.address;
                }
                break;
            case 'b':
                if ( name.equals("born_date") ) {
                    return this.born_date;
                }
                break;
            case 'c':
                if ( name.equals("control_branch_code") ) {
                    return this.control_branch_code;
                }
                else if ( name.equals("control_number") ) {
                    return new Integer( control_number );
                }
                else if ( name.equals("comment_kana") ) {
                    return this.comment_kana;
                }
                else if ( name.equals("comment_kanji") ) {
                    return this.comment_kanji;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("disclosure_div") ) {
                    return this.disclosure_div;
                }
                break;
            case 'e':
                if ( name.equals("era_born") ) {
                    return this.era_born;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("name_kana") ) {
                    return this.name_kana;
                }
                else if ( name.equals("name") ) {
                    return this.name;
                }
                break;
            case 'o':
                if ( name.equals("operation_div") ) {
                    return this.operation_div;
                }
                else if ( name.equals("office_kana") ) {
                    return this.office_kana;
                }
                else if ( name.equals("office") ) {
                    return this.office;
                }
                break;
            case 'p':
                if ( name.equals("post_kana") ) {
                    return this.post_kana;
                }
                else if ( name.equals("post") ) {
                    return this.post;
                }
                break;
            case 's':
                if ( name.equals("sonar_created_branch") ) {
                    return this.sonar_created_branch;
                }
                else if ( name.equals("sonar_created_date") ) {
                    return this.sonar_created_date;
                }
                else if ( name.equals("sonar_updated_branch") ) {
                    return this.sonar_updated_branch;
                }
                else if ( name.equals("sonar_updated_date") ) {
                    return this.sonar_updated_date;
                }
                else if ( name.equals("sonar_check_div") ) {
                    return this.sonar_check_div;
                }
                break;
            case 't':
                if ( name.equals("telephone") ) {
                    return this.telephone;
                }
                break;
            case 'y':
                if ( name.equals("y_customer_id") ) {
                    return new Long( y_customer_id );
                }
                break;
            case 'z':
                if ( name.equals("zip_code") ) {
                    return this.zip_code;
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
                if ( name.equals("account_name_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name_kana' must be String: '"+value+"' is not." );
                    this.account_name_kana = (String) value;
                    if (this.account_name_kana_is_set)
                        this.account_name_kana_is_modified = true;
                    this.account_name_kana_is_set = true;
                    return;
                }
                else if ( name.equals("account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name' must be String: '"+value+"' is not." );
                    this.account_name = (String) value;
                    if (this.account_name_is_set)
                        this.account_name_is_modified = true;
                    this.account_name_is_set = true;
                    return;
                }
                else if ( name.equals("address_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_kana' must be String: '"+value+"' is not." );
                    this.address_kana = (String) value;
                    if (this.address_kana_is_set)
                        this.address_kana_is_modified = true;
                    this.address_kana_is_set = true;
                    return;
                }
                else if ( name.equals("address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address' must be String: '"+value+"' is not." );
                    this.address = (String) value;
                    if (this.address_is_set)
                        this.address_is_modified = true;
                    this.address_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("born_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'born_date' must be String: '"+value+"' is not." );
                    this.born_date = (String) value;
                    if (this.born_date_is_set)
                        this.born_date_is_modified = true;
                    this.born_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("control_branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'control_branch_code' must be String: '"+value+"' is not." );
                    this.control_branch_code = (String) value;
                    if (this.control_branch_code_is_set)
                        this.control_branch_code_is_modified = true;
                    this.control_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("control_number") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'control_number' must be Integer: '"+value+"' is not." );
                    this.control_number = ((Integer) value).intValue();
                    if (this.control_number_is_set)
                        this.control_number_is_modified = true;
                    this.control_number_is_set = true;
                    return;
                }
                else if ( name.equals("comment_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment_kana' must be String: '"+value+"' is not." );
                    this.comment_kana = (String) value;
                    if (this.comment_kana_is_set)
                        this.comment_kana_is_modified = true;
                    this.comment_kana_is_set = true;
                    return;
                }
                else if ( name.equals("comment_kanji") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comment_kanji' must be String: '"+value+"' is not." );
                    this.comment_kanji = (String) value;
                    if (this.comment_kanji_is_set)
                        this.comment_kanji_is_modified = true;
                    this.comment_kanji_is_set = true;
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
            case 'd':
                if ( name.equals("disclosure_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'disclosure_div' must be String: '"+value+"' is not." );
                    this.disclosure_div = (String) value;
                    if (this.disclosure_div_is_set)
                        this.disclosure_div_is_modified = true;
                    this.disclosure_div_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("era_born") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'era_born' must be String: '"+value+"' is not." );
                    this.era_born = (String) value;
                    if (this.era_born_is_set)
                        this.era_born_is_modified = true;
                    this.era_born_is_set = true;
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
                if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("name_kana") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name_kana' must be String: '"+value+"' is not." );
                    this.name_kana = (String) value;
                    if (this.name_kana_is_set)
                        this.name_kana_is_modified = true;
                    this.name_kana_is_set = true;
                    return;
                }
                else if ( name.equals("name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name' must be String: '"+value+"' is not." );
                    this.name = (String) value;
                    if (this.name_is_set)
                        this.name_is_modified = true;
                    this.name_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("operation_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operation_div' must be String: '"+value+"' is not." );
                    this.operation_div = (String) value;
                    if (this.operation_div_is_set)
                        this.operation_div_is_modified = true;
                    this.operation_div_is_set = true;
                    return;
                }
                else if ( name.equals("office_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'office_kana' must be String: '"+value+"' is not." );
                    this.office_kana = (String) value;
                    if (this.office_kana_is_set)
                        this.office_kana_is_modified = true;
                    this.office_kana_is_set = true;
                    return;
                }
                else if ( name.equals("office") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'office' must be String: '"+value+"' is not." );
                    this.office = (String) value;
                    if (this.office_is_set)
                        this.office_is_modified = true;
                    this.office_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("post_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'post_kana' must be String: '"+value+"' is not." );
                    this.post_kana = (String) value;
                    if (this.post_kana_is_set)
                        this.post_kana_is_modified = true;
                    this.post_kana_is_set = true;
                    return;
                }
                else if ( name.equals("post") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'post' must be String: '"+value+"' is not." );
                    this.post = (String) value;
                    if (this.post_is_set)
                        this.post_is_modified = true;
                    this.post_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_created_branch") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_created_branch' must be String: '"+value+"' is not." );
                    this.sonar_created_branch = (String) value;
                    if (this.sonar_created_branch_is_set)
                        this.sonar_created_branch_is_modified = true;
                    this.sonar_created_branch_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_created_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_created_date' must be String: '"+value+"' is not." );
                    this.sonar_created_date = (String) value;
                    if (this.sonar_created_date_is_set)
                        this.sonar_created_date_is_modified = true;
                    this.sonar_created_date_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_updated_branch") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_updated_branch' must be String: '"+value+"' is not." );
                    this.sonar_updated_branch = (String) value;
                    if (this.sonar_updated_branch_is_set)
                        this.sonar_updated_branch_is_modified = true;
                    this.sonar_updated_branch_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_updated_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_updated_date' must be String: '"+value+"' is not." );
                    this.sonar_updated_date = (String) value;
                    if (this.sonar_updated_date_is_set)
                        this.sonar_updated_date_is_modified = true;
                    this.sonar_updated_date_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_check_div' must be String: '"+value+"' is not." );
                    this.sonar_check_div = (String) value;
                    if (this.sonar_check_div_is_set)
                        this.sonar_check_div_is_modified = true;
                    this.sonar_check_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("telephone") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'telephone' must be String: '"+value+"' is not." );
                    this.telephone = (String) value;
                    if (this.telephone_is_set)
                        this.telephone_is_modified = true;
                    this.telephone_is_set = true;
                    return;
                }
                break;
            case 'y':
                if ( name.equals("y_customer_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'y_customer_id' must be Long: '"+value+"' is not." );
                    this.y_customer_id = ((Long) value).longValue();
                    if (this.y_customer_id_is_set)
                        this.y_customer_id_is_modified = true;
                    this.y_customer_id_is_set = true;
                    return;
                }
                break;
            case 'z':
                if ( name.equals("zip_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'zip_code' must be String: '"+value+"' is not." );
                    this.zip_code = (String) value;
                    if (this.zip_code_is_set)
                        this.zip_code_is_modified = true;
                    this.zip_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
