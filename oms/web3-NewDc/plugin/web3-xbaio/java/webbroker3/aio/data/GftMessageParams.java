head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.42.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	GftMessageParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * gft_messageテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link GftMessageRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link GftMessageParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link GftMessageParams}が{@@link GftMessageRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see GftMessagePK 
 * @@see GftMessageRow 
 */
public class GftMessageParams extends Params implements GftMessageRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "gft_message";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = GftMessageRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return GftMessageRow.TYPE;
  }


  /** 
   * <em>message_div</em>カラムの値 
   */
  public  String  message_div;

  /** 
   * <em>dir_send_time</em>カラムの値 
   */
  public  String  dir_send_time;

  /** 
   * <em>operation</em>カラムの値 
   */
  public  String  operation;

  /** 
   * <em>account</em>カラムの値 
   */
  public  String  account;

  /** 
   * <em>email</em>カラムの値 
   */
  public  String  email;

  /** 
   * <em>gft_link_1</em>カラムの値 
   */
  public  String  gft_link_1;

  /** 
   * <em>gft_link_2</em>カラムの値 
   */
  public  String  gft_link_2;

  /** 
   * <em>group_name</em>カラムの値 
   */
  public  String  group_name;

  /** 
   * <em>amount</em>カラムの値 
   */
  public  String  amount;

  /** 
   * <em>wolf_session_key</em>カラムの値 
   */
  public  String  wolf_session_key;

  /** 
   * <em>aa_aid</em>カラムの値 
   */
  public  String  aa_aid;

  /** 
   * <em>aa_rsid</em>カラムの値 
   */
  public  String  aa_rsid;

  /** 
   * <em>ssid</em>カラムの値 
   */
  public  String  ssid;

  /** 
   * <em>cpy</em>カラムの値 
   */
  public  String  cpy;

  /** 
   * <em>brn</em>カラムの値 
   */
  public  String  brn;

  /** 
   * <em>acc</em>カラムの値 
   */
  public  String  acc;

  /** 
   * <em>req</em>カラムの値 
   */
  public  String  req;

  /** 
   * <em>result_code</em>カラムの値 
   */
  public  String  result_code;

  /** 
   * <em>gft_send_time</em>カラムの値 
   */
  public  String  gft_send_time;

  /** 
   * <em>gft_ac1</em>カラムの値 
   */
  public  String  gft_ac1;

  /** 
   * <em>gft_ac2</em>カラムの値 
   */
  public  String  gft_ac2;

  /** 
   * <em>last_name</em>カラムの値 
   */
  public  String  last_name;

  /** 
   * <em>first_name</em>カラムの値 
   */
  public  String  first_name;

  /** 
   * <em>hash_key</em>カラムの値 
   */
  public  String  hash_key;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  String  delivery_date;

  /** 
   * <em>amount2</em>カラムの値 
   */
  public  String  amount2;

  /** 
   * <em>address1</em>カラムの値 
   */
  public  String  address1;

  /** 
   * <em>address2</em>カラムの値 
   */
  public  String  address2;

  /** 
   * <em>address3</em>カラムの値 
   */
  public  String  address3;

  private boolean message_div_is_set = false;
  private boolean message_div_is_modified = false;
  private boolean dir_send_time_is_set = false;
  private boolean dir_send_time_is_modified = false;
  private boolean operation_is_set = false;
  private boolean operation_is_modified = false;
  private boolean account_is_set = false;
  private boolean account_is_modified = false;
  private boolean email_is_set = false;
  private boolean email_is_modified = false;
  private boolean gft_link_1_is_set = false;
  private boolean gft_link_1_is_modified = false;
  private boolean gft_link_2_is_set = false;
  private boolean gft_link_2_is_modified = false;
  private boolean group_name_is_set = false;
  private boolean group_name_is_modified = false;
  private boolean amount_is_set = false;
  private boolean amount_is_modified = false;
  private boolean wolf_session_key_is_set = false;
  private boolean wolf_session_key_is_modified = false;
  private boolean aa_aid_is_set = false;
  private boolean aa_aid_is_modified = false;
  private boolean aa_rsid_is_set = false;
  private boolean aa_rsid_is_modified = false;
  private boolean ssid_is_set = false;
  private boolean ssid_is_modified = false;
  private boolean cpy_is_set = false;
  private boolean cpy_is_modified = false;
  private boolean brn_is_set = false;
  private boolean brn_is_modified = false;
  private boolean acc_is_set = false;
  private boolean acc_is_modified = false;
  private boolean req_is_set = false;
  private boolean req_is_modified = false;
  private boolean result_code_is_set = false;
  private boolean result_code_is_modified = false;
  private boolean gft_send_time_is_set = false;
  private boolean gft_send_time_is_modified = false;
  private boolean gft_ac1_is_set = false;
  private boolean gft_ac1_is_modified = false;
  private boolean gft_ac2_is_set = false;
  private boolean gft_ac2_is_modified = false;
  private boolean last_name_is_set = false;
  private boolean last_name_is_modified = false;
  private boolean first_name_is_set = false;
  private boolean first_name_is_modified = false;
  private boolean hash_key_is_set = false;
  private boolean hash_key_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean amount2_is_set = false;
  private boolean amount2_is_modified = false;
  private boolean address1_is_set = false;
  private boolean address1_is_modified = false;
  private boolean address2_is_set = false;
  private boolean address2_is_modified = false;
  private boolean address3_is_set = false;
  private boolean address3_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "message_div=" +message_div
      + "," + "dir_send_time=" +dir_send_time
      + "," + "operation=" +operation
      + "," + "account=" +account
      + "," + "email=" +email
      + "," + "gft_link_1=" +gft_link_1
      + "," + "gft_link_2=" +gft_link_2
      + "," + "group_name=" +group_name
      + "," + "amount=" +amount
      + "," + "wolf_session_key=" +wolf_session_key
      + "," + "aa_aid=" +aa_aid
      + "," + "aa_rsid=" +aa_rsid
      + "," + "ssid=" +ssid
      + "," + "cpy=" +cpy
      + "," + "brn=" +brn
      + "," + "acc=" +acc
      + "," + "req=" +req
      + "," + "result_code=" +result_code
      + "," + "gft_send_time=" +gft_send_time
      + "," + "gft_ac1=" +gft_ac1
      + "," + "gft_ac2=" +gft_ac2
      + "," + "last_name=" +last_name
      + "," + "first_name=" +first_name
      + "," + "hash_key=" +hash_key
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "delivery_date=" +delivery_date
      + "," + "amount2=" +amount2
      + "," + "address1=" +address1
      + "," + "address2=" +address2
      + "," + "address3=" +address3
      + "}";
  }


  /** 
   * 値が未設定のGftMessageParamsオブジェクトを作成します。 
   */
  public GftMessageParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のGftMessageRowオブジェクトの値を利用してGftMessageParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するGftMessageRowオブジェクト 
   */
  public GftMessageParams( GftMessageRow p_row )
  {
    message_div = p_row.getMessageDiv();
    message_div_is_set = p_row.getMessageDivIsSet();
    message_div_is_modified = p_row.getMessageDivIsModified();
    dir_send_time = p_row.getDirSendTime();
    dir_send_time_is_set = p_row.getDirSendTimeIsSet();
    dir_send_time_is_modified = p_row.getDirSendTimeIsModified();
    operation = p_row.getOperation();
    operation_is_set = p_row.getOperationIsSet();
    operation_is_modified = p_row.getOperationIsModified();
    account = p_row.getAccount();
    account_is_set = p_row.getAccountIsSet();
    account_is_modified = p_row.getAccountIsModified();
    email = p_row.getEmail();
    email_is_set = p_row.getEmailIsSet();
    email_is_modified = p_row.getEmailIsModified();
    gft_link_1 = p_row.getGftLink1();
    gft_link_1_is_set = p_row.getGftLink1IsSet();
    gft_link_1_is_modified = p_row.getGftLink1IsModified();
    gft_link_2 = p_row.getGftLink2();
    gft_link_2_is_set = p_row.getGftLink2IsSet();
    gft_link_2_is_modified = p_row.getGftLink2IsModified();
    group_name = p_row.getGroupName();
    group_name_is_set = p_row.getGroupNameIsSet();
    group_name_is_modified = p_row.getGroupNameIsModified();
    amount = p_row.getAmount();
    amount_is_set = p_row.getAmountIsSet();
    amount_is_modified = p_row.getAmountIsModified();
    wolf_session_key = p_row.getWolfSessionKey();
    wolf_session_key_is_set = p_row.getWolfSessionKeyIsSet();
    wolf_session_key_is_modified = p_row.getWolfSessionKeyIsModified();
    aa_aid = p_row.getAaAid();
    aa_aid_is_set = p_row.getAaAidIsSet();
    aa_aid_is_modified = p_row.getAaAidIsModified();
    aa_rsid = p_row.getAaRsid();
    aa_rsid_is_set = p_row.getAaRsidIsSet();
    aa_rsid_is_modified = p_row.getAaRsidIsModified();
    ssid = p_row.getSsid();
    ssid_is_set = p_row.getSsidIsSet();
    ssid_is_modified = p_row.getSsidIsModified();
    cpy = p_row.getCpy();
    cpy_is_set = p_row.getCpyIsSet();
    cpy_is_modified = p_row.getCpyIsModified();
    brn = p_row.getBrn();
    brn_is_set = p_row.getBrnIsSet();
    brn_is_modified = p_row.getBrnIsModified();
    acc = p_row.getAcc();
    acc_is_set = p_row.getAccIsSet();
    acc_is_modified = p_row.getAccIsModified();
    req = p_row.getReq();
    req_is_set = p_row.getReqIsSet();
    req_is_modified = p_row.getReqIsModified();
    result_code = p_row.getResultCode();
    result_code_is_set = p_row.getResultCodeIsSet();
    result_code_is_modified = p_row.getResultCodeIsModified();
    gft_send_time = p_row.getGftSendTime();
    gft_send_time_is_set = p_row.getGftSendTimeIsSet();
    gft_send_time_is_modified = p_row.getGftSendTimeIsModified();
    gft_ac1 = p_row.getGftAc1();
    gft_ac1_is_set = p_row.getGftAc1IsSet();
    gft_ac1_is_modified = p_row.getGftAc1IsModified();
    gft_ac2 = p_row.getGftAc2();
    gft_ac2_is_set = p_row.getGftAc2IsSet();
    gft_ac2_is_modified = p_row.getGftAc2IsModified();
    last_name = p_row.getLastName();
    last_name_is_set = p_row.getLastNameIsSet();
    last_name_is_modified = p_row.getLastNameIsModified();
    first_name = p_row.getFirstName();
    first_name_is_set = p_row.getFirstNameIsSet();
    first_name_is_modified = p_row.getFirstNameIsModified();
    hash_key = p_row.getHashKey();
    hash_key_is_set = p_row.getHashKeyIsSet();
    hash_key_is_modified = p_row.getHashKeyIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    amount2 = p_row.getAmount2();
    amount2_is_set = p_row.getAmount2IsSet();
    amount2_is_modified = p_row.getAmount2IsModified();
    address1 = p_row.getAddress1();
    address1_is_set = p_row.getAddress1IsSet();
    address1_is_modified = p_row.getAddress1IsModified();
    address2 = p_row.getAddress2();
    address2_is_set = p_row.getAddress2IsSet();
    address2_is_modified = p_row.getAddress2IsModified();
    address3 = p_row.getAddress3();
    address3_is_set = p_row.getAddress3IsSet();
    address3_is_modified = p_row.getAddress3IsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      message_div_is_set = true;
      message_div_is_modified = true;
      dir_send_time_is_set = true;
      dir_send_time_is_modified = true;
      operation_is_set = true;
      operation_is_modified = true;
      account_is_set = true;
      account_is_modified = true;
      email_is_set = true;
      email_is_modified = true;
      gft_link_1_is_set = true;
      gft_link_1_is_modified = true;
      gft_link_2_is_set = true;
      gft_link_2_is_modified = true;
      group_name_is_set = true;
      group_name_is_modified = true;
      amount_is_set = true;
      amount_is_modified = true;
      wolf_session_key_is_set = true;
      wolf_session_key_is_modified = true;
      aa_aid_is_set = true;
      aa_aid_is_modified = true;
      aa_rsid_is_set = true;
      aa_rsid_is_modified = true;
      ssid_is_set = true;
      ssid_is_modified = true;
      cpy_is_set = true;
      cpy_is_modified = true;
      brn_is_set = true;
      brn_is_modified = true;
      acc_is_set = true;
      acc_is_modified = true;
      req_is_set = true;
      req_is_modified = true;
      result_code_is_set = true;
      result_code_is_modified = true;
      gft_send_time_is_set = true;
      gft_send_time_is_modified = true;
      gft_ac1_is_set = true;
      gft_ac1_is_modified = true;
      gft_ac2_is_set = true;
      gft_ac2_is_modified = true;
      last_name_is_set = true;
      last_name_is_modified = true;
      first_name_is_set = true;
      first_name_is_modified = true;
      hash_key_is_set = true;
      hash_key_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      amount2_is_set = true;
      amount2_is_modified = true;
      address1_is_set = true;
      address1_is_modified = true;
      address2_is_set = true;
      address2_is_modified = true;
      address3_is_set = true;
      address3_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof GftMessageRow ) )
       return false;
    return fieldsEqual( (GftMessageRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のGftMessageRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( GftMessageRow row )
  {
    if ( message_div == null ) {
      if ( row.getMessageDiv() != null )
        return false;
    } else if ( !message_div.equals( row.getMessageDiv() ) ) {
        return false;
    }
    if ( dir_send_time == null ) {
      if ( row.getDirSendTime() != null )
        return false;
    } else if ( !dir_send_time.equals( row.getDirSendTime() ) ) {
        return false;
    }
    if ( operation == null ) {
      if ( row.getOperation() != null )
        return false;
    } else if ( !operation.equals( row.getOperation() ) ) {
        return false;
    }
    if ( account == null ) {
      if ( row.getAccount() != null )
        return false;
    } else if ( !account.equals( row.getAccount() ) ) {
        return false;
    }
    if ( email == null ) {
      if ( row.getEmail() != null )
        return false;
    } else if ( !email.equals( row.getEmail() ) ) {
        return false;
    }
    if ( gft_link_1 == null ) {
      if ( row.getGftLink1() != null )
        return false;
    } else if ( !gft_link_1.equals( row.getGftLink1() ) ) {
        return false;
    }
    if ( gft_link_2 == null ) {
      if ( row.getGftLink2() != null )
        return false;
    } else if ( !gft_link_2.equals( row.getGftLink2() ) ) {
        return false;
    }
    if ( group_name == null ) {
      if ( row.getGroupName() != null )
        return false;
    } else if ( !group_name.equals( row.getGroupName() ) ) {
        return false;
    }
    if ( amount == null ) {
      if ( row.getAmount() != null )
        return false;
    } else if ( !amount.equals( row.getAmount() ) ) {
        return false;
    }
    if ( wolf_session_key == null ) {
      if ( row.getWolfSessionKey() != null )
        return false;
    } else if ( !wolf_session_key.equals( row.getWolfSessionKey() ) ) {
        return false;
    }
    if ( aa_aid == null ) {
      if ( row.getAaAid() != null )
        return false;
    } else if ( !aa_aid.equals( row.getAaAid() ) ) {
        return false;
    }
    if ( aa_rsid == null ) {
      if ( row.getAaRsid() != null )
        return false;
    } else if ( !aa_rsid.equals( row.getAaRsid() ) ) {
        return false;
    }
    if ( ssid == null ) {
      if ( row.getSsid() != null )
        return false;
    } else if ( !ssid.equals( row.getSsid() ) ) {
        return false;
    }
    if ( cpy == null ) {
      if ( row.getCpy() != null )
        return false;
    } else if ( !cpy.equals( row.getCpy() ) ) {
        return false;
    }
    if ( brn == null ) {
      if ( row.getBrn() != null )
        return false;
    } else if ( !brn.equals( row.getBrn() ) ) {
        return false;
    }
    if ( acc == null ) {
      if ( row.getAcc() != null )
        return false;
    } else if ( !acc.equals( row.getAcc() ) ) {
        return false;
    }
    if ( req == null ) {
      if ( row.getReq() != null )
        return false;
    } else if ( !req.equals( row.getReq() ) ) {
        return false;
    }
    if ( result_code == null ) {
      if ( row.getResultCode() != null )
        return false;
    } else if ( !result_code.equals( row.getResultCode() ) ) {
        return false;
    }
    if ( gft_send_time == null ) {
      if ( row.getGftSendTime() != null )
        return false;
    } else if ( !gft_send_time.equals( row.getGftSendTime() ) ) {
        return false;
    }
    if ( gft_ac1 == null ) {
      if ( row.getGftAc1() != null )
        return false;
    } else if ( !gft_ac1.equals( row.getGftAc1() ) ) {
        return false;
    }
    if ( gft_ac2 == null ) {
      if ( row.getGftAc2() != null )
        return false;
    } else if ( !gft_ac2.equals( row.getGftAc2() ) ) {
        return false;
    }
    if ( last_name == null ) {
      if ( row.getLastName() != null )
        return false;
    } else if ( !last_name.equals( row.getLastName() ) ) {
        return false;
    }
    if ( first_name == null ) {
      if ( row.getFirstName() != null )
        return false;
    } else if ( !first_name.equals( row.getFirstName() ) ) {
        return false;
    }
    if ( hash_key == null ) {
      if ( row.getHashKey() != null )
        return false;
    } else if ( !hash_key.equals( row.getHashKey() ) ) {
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
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( amount2 == null ) {
      if ( row.getAmount2() != null )
        return false;
    } else if ( !amount2.equals( row.getAmount2() ) ) {
        return false;
    }
    if ( address1 == null ) {
      if ( row.getAddress1() != null )
        return false;
    } else if ( !address1.equals( row.getAddress1() ) ) {
        return false;
    }
    if ( address2 == null ) {
      if ( row.getAddress2() != null )
        return false;
    } else if ( !address2.equals( row.getAddress2() ) ) {
        return false;
    }
    if ( address3 == null ) {
      if ( row.getAddress3() != null )
        return false;
    } else if ( !address3.equals( row.getAddress3() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (message_div!=null? message_div.hashCode(): 0) 
        + (dir_send_time!=null? dir_send_time.hashCode(): 0) 
        + (operation!=null? operation.hashCode(): 0) 
        + (account!=null? account.hashCode(): 0) 
        + (email!=null? email.hashCode(): 0) 
        + (gft_link_1!=null? gft_link_1.hashCode(): 0) 
        + (gft_link_2!=null? gft_link_2.hashCode(): 0) 
        + (group_name!=null? group_name.hashCode(): 0) 
        + (amount!=null? amount.hashCode(): 0) 
        + (wolf_session_key!=null? wolf_session_key.hashCode(): 0) 
        + (aa_aid!=null? aa_aid.hashCode(): 0) 
        + (aa_rsid!=null? aa_rsid.hashCode(): 0) 
        + (ssid!=null? ssid.hashCode(): 0) 
        + (cpy!=null? cpy.hashCode(): 0) 
        + (brn!=null? brn.hashCode(): 0) 
        + (acc!=null? acc.hashCode(): 0) 
        + (req!=null? req.hashCode(): 0) 
        + (result_code!=null? result_code.hashCode(): 0) 
        + (gft_send_time!=null? gft_send_time.hashCode(): 0) 
        + (gft_ac1!=null? gft_ac1.hashCode(): 0) 
        + (gft_ac2!=null? gft_ac2.hashCode(): 0) 
        + (last_name!=null? last_name.hashCode(): 0) 
        + (first_name!=null? first_name.hashCode(): 0) 
        + (hash_key!=null? hash_key.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (amount2!=null? amount2.hashCode(): 0) 
        + (address1!=null? address1.hashCode(): 0) 
        + (address2!=null? address2.hashCode(): 0) 
        + (address3!=null? address3.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !message_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'message_div' must be set before inserting.");
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
		map.put("message_div",message_div);
		if ( dir_send_time != null )
			map.put("dir_send_time",dir_send_time);
		if ( operation != null )
			map.put("operation",operation);
		if ( account != null )
			map.put("account",account);
		if ( email != null )
			map.put("email",email);
		if ( gft_link_1 != null )
			map.put("gft_link_1",gft_link_1);
		if ( gft_link_2 != null )
			map.put("gft_link_2",gft_link_2);
		if ( group_name != null )
			map.put("group_name",group_name);
		if ( amount != null )
			map.put("amount",amount);
		if ( wolf_session_key != null )
			map.put("wolf_session_key",wolf_session_key);
		if ( aa_aid != null )
			map.put("aa_aid",aa_aid);
		if ( aa_rsid != null )
			map.put("aa_rsid",aa_rsid);
		if ( ssid != null )
			map.put("ssid",ssid);
		if ( cpy != null )
			map.put("cpy",cpy);
		if ( brn != null )
			map.put("brn",brn);
		if ( acc != null )
			map.put("acc",acc);
		if ( req != null )
			map.put("req",req);
		if ( result_code != null )
			map.put("result_code",result_code);
		if ( gft_send_time != null )
			map.put("gft_send_time",gft_send_time);
		if ( gft_ac1 != null )
			map.put("gft_ac1",gft_ac1);
		if ( gft_ac2 != null )
			map.put("gft_ac2",gft_ac2);
		if ( last_name != null )
			map.put("last_name",last_name);
		if ( first_name != null )
			map.put("first_name",first_name);
		if ( hash_key != null )
			map.put("hash_key",hash_key);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( amount2 != null )
			map.put("amount2",amount2);
		if ( address1 != null )
			map.put("address1",address1);
		if ( address2 != null )
			map.put("address2",address2);
		if ( address3 != null )
			map.put("address3",address3);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( message_div_is_modified )
			map.put("message_div",message_div);
		if ( dir_send_time_is_modified )
			map.put("dir_send_time",dir_send_time);
		if ( operation_is_modified )
			map.put("operation",operation);
		if ( account_is_modified )
			map.put("account",account);
		if ( email_is_modified )
			map.put("email",email);
		if ( gft_link_1_is_modified )
			map.put("gft_link_1",gft_link_1);
		if ( gft_link_2_is_modified )
			map.put("gft_link_2",gft_link_2);
		if ( group_name_is_modified )
			map.put("group_name",group_name);
		if ( amount_is_modified )
			map.put("amount",amount);
		if ( wolf_session_key_is_modified )
			map.put("wolf_session_key",wolf_session_key);
		if ( aa_aid_is_modified )
			map.put("aa_aid",aa_aid);
		if ( aa_rsid_is_modified )
			map.put("aa_rsid",aa_rsid);
		if ( ssid_is_modified )
			map.put("ssid",ssid);
		if ( cpy_is_modified )
			map.put("cpy",cpy);
		if ( brn_is_modified )
			map.put("brn",brn);
		if ( acc_is_modified )
			map.put("acc",acc);
		if ( req_is_modified )
			map.put("req",req);
		if ( result_code_is_modified )
			map.put("result_code",result_code);
		if ( gft_send_time_is_modified )
			map.put("gft_send_time",gft_send_time);
		if ( gft_ac1_is_modified )
			map.put("gft_ac1",gft_ac1);
		if ( gft_ac2_is_modified )
			map.put("gft_ac2",gft_ac2);
		if ( last_name_is_modified )
			map.put("last_name",last_name);
		if ( first_name_is_modified )
			map.put("first_name",first_name);
		if ( hash_key_is_modified )
			map.put("hash_key",hash_key);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( amount2_is_modified )
			map.put("amount2",amount2);
		if ( address1_is_modified )
			map.put("address1",address1);
		if ( address2_is_modified )
			map.put("address2",address2);
		if ( address3_is_modified )
			map.put("address3",address3);
		if (map.size() == 0) {
			if ( message_div_is_set )
				map.put("message_div",message_div);
			map.put("dir_send_time",dir_send_time);
			map.put("operation",operation);
			map.put("account",account);
			map.put("email",email);
			map.put("gft_link_1",gft_link_1);
			map.put("gft_link_2",gft_link_2);
			map.put("group_name",group_name);
			map.put("amount",amount);
			map.put("wolf_session_key",wolf_session_key);
			map.put("aa_aid",aa_aid);
			map.put("aa_rsid",aa_rsid);
			map.put("ssid",ssid);
			map.put("cpy",cpy);
			map.put("brn",brn);
			map.put("acc",acc);
			map.put("req",req);
			map.put("result_code",result_code);
			map.put("gft_send_time",gft_send_time);
			map.put("gft_ac1",gft_ac1);
			map.put("gft_ac2",gft_ac2);
			map.put("last_name",last_name);
			map.put("first_name",first_name);
			map.put("hash_key",hash_key);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("delivery_date",delivery_date);
			map.put("amount2",amount2);
			map.put("address1",address1);
			map.put("address2",address2);
			map.put("address3",address3);
		}
		return map;
	}


  /** 
   * <em>message_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMessageDiv()
  {
    return message_div;
  }


  /** 
   * <em>message_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMessageDivIsSet() {
    return message_div_is_set;
  }


  /** 
   * <em>message_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMessageDivIsModified() {
    return message_div_is_modified;
  }


  /** 
   * <em>dir_send_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirSendTime()
  {
    return dir_send_time;
  }


  /** 
   * <em>dir_send_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirSendTimeIsSet() {
    return dir_send_time_is_set;
  }


  /** 
   * <em>dir_send_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirSendTimeIsModified() {
    return dir_send_time_is_modified;
  }


  /** 
   * <em>operation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperation()
  {
    return operation;
  }


  /** 
   * <em>operation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationIsSet() {
    return operation_is_set;
  }


  /** 
   * <em>operation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationIsModified() {
    return operation_is_modified;
  }


  /** 
   * <em>account</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccount()
  {
    return account;
  }


  /** 
   * <em>account</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIsSet() {
    return account_is_set;
  }


  /** 
   * <em>account</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIsModified() {
    return account_is_modified;
  }


  /** 
   * <em>email</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmail()
  {
    return email;
  }


  /** 
   * <em>email</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailIsSet() {
    return email_is_set;
  }


  /** 
   * <em>email</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailIsModified() {
    return email_is_modified;
  }


  /** 
   * <em>gft_link_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGftLink1()
  {
    return gft_link_1;
  }


  /** 
   * <em>gft_link_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftLink1IsSet() {
    return gft_link_1_is_set;
  }


  /** 
   * <em>gft_link_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftLink1IsModified() {
    return gft_link_1_is_modified;
  }


  /** 
   * <em>gft_link_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGftLink2()
  {
    return gft_link_2;
  }


  /** 
   * <em>gft_link_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftLink2IsSet() {
    return gft_link_2_is_set;
  }


  /** 
   * <em>gft_link_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftLink2IsModified() {
    return gft_link_2_is_modified;
  }


  /** 
   * <em>group_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGroupName()
  {
    return group_name;
  }


  /** 
   * <em>group_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGroupNameIsSet() {
    return group_name_is_set;
  }


  /** 
   * <em>group_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGroupNameIsModified() {
    return group_name_is_modified;
  }


  /** 
   * <em>amount</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAmount()
  {
    return amount;
  }


  /** 
   * <em>amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountIsSet() {
    return amount_is_set;
  }


  /** 
   * <em>amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountIsModified() {
    return amount_is_modified;
  }


  /** 
   * <em>wolf_session_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getWolfSessionKey()
  {
    return wolf_session_key;
  }


  /** 
   * <em>wolf_session_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWolfSessionKeyIsSet() {
    return wolf_session_key_is_set;
  }


  /** 
   * <em>wolf_session_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWolfSessionKeyIsModified() {
    return wolf_session_key_is_modified;
  }


  /** 
   * <em>aa_aid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAaAid()
  {
    return aa_aid;
  }


  /** 
   * <em>aa_aid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAaAidIsSet() {
    return aa_aid_is_set;
  }


  /** 
   * <em>aa_aid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAaAidIsModified() {
    return aa_aid_is_modified;
  }


  /** 
   * <em>aa_rsid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAaRsid()
  {
    return aa_rsid;
  }


  /** 
   * <em>aa_rsid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAaRsidIsSet() {
    return aa_rsid_is_set;
  }


  /** 
   * <em>aa_rsid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAaRsidIsModified() {
    return aa_rsid_is_modified;
  }


  /** 
   * <em>ssid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSsid()
  {
    return ssid;
  }


  /** 
   * <em>ssid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSsidIsSet() {
    return ssid_is_set;
  }


  /** 
   * <em>ssid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSsidIsModified() {
    return ssid_is_modified;
  }


  /** 
   * <em>cpy</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCpy()
  {
    return cpy;
  }


  /** 
   * <em>cpy</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCpyIsSet() {
    return cpy_is_set;
  }


  /** 
   * <em>cpy</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCpyIsModified() {
    return cpy_is_modified;
  }


  /** 
   * <em>brn</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBrn()
  {
    return brn;
  }


  /** 
   * <em>brn</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrnIsSet() {
    return brn_is_set;
  }


  /** 
   * <em>brn</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrnIsModified() {
    return brn_is_modified;
  }


  /** 
   * <em>acc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcc()
  {
    return acc;
  }


  /** 
   * <em>acc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccIsSet() {
    return acc_is_set;
  }


  /** 
   * <em>acc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccIsModified() {
    return acc_is_modified;
  }


  /** 
   * <em>req</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReq()
  {
    return req;
  }


  /** 
   * <em>req</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReqIsSet() {
    return req_is_set;
  }


  /** 
   * <em>req</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReqIsModified() {
    return req_is_modified;
  }


  /** 
   * <em>result_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getResultCode()
  {
    return result_code;
  }


  /** 
   * <em>result_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultCodeIsSet() {
    return result_code_is_set;
  }


  /** 
   * <em>result_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResultCodeIsModified() {
    return result_code_is_modified;
  }


  /** 
   * <em>gft_send_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGftSendTime()
  {
    return gft_send_time;
  }


  /** 
   * <em>gft_send_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftSendTimeIsSet() {
    return gft_send_time_is_set;
  }


  /** 
   * <em>gft_send_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftSendTimeIsModified() {
    return gft_send_time_is_modified;
  }


  /** 
   * <em>gft_ac1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGftAc1()
  {
    return gft_ac1;
  }


  /** 
   * <em>gft_ac1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftAc1IsSet() {
    return gft_ac1_is_set;
  }


  /** 
   * <em>gft_ac1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftAc1IsModified() {
    return gft_ac1_is_modified;
  }


  /** 
   * <em>gft_ac2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGftAc2()
  {
    return gft_ac2;
  }


  /** 
   * <em>gft_ac2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftAc2IsSet() {
    return gft_ac2_is_set;
  }


  /** 
   * <em>gft_ac2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGftAc2IsModified() {
    return gft_ac2_is_modified;
  }


  /** 
   * <em>last_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastName()
  {
    return last_name;
  }


  /** 
   * <em>last_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastNameIsSet() {
    return last_name_is_set;
  }


  /** 
   * <em>last_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastNameIsModified() {
    return last_name_is_modified;
  }


  /** 
   * <em>first_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFirstName()
  {
    return first_name;
  }


  /** 
   * <em>first_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstNameIsSet() {
    return first_name_is_set;
  }


  /** 
   * <em>first_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstNameIsModified() {
    return first_name_is_modified;
  }


  /** 
   * <em>hash_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHashKey()
  {
    return hash_key;
  }


  /** 
   * <em>hash_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashKeyIsSet() {
    return hash_key_is_set;
  }


  /** 
   * <em>hash_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashKeyIsModified() {
    return hash_key_is_modified;
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
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>amount2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAmount2()
  {
    return amount2;
  }


  /** 
   * <em>amount2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmount2IsSet() {
    return amount2_is_set;
  }


  /** 
   * <em>amount2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmount2IsModified() {
    return amount2_is_modified;
  }


  /** 
   * <em>address1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddress1()
  {
    return address1;
  }


  /** 
   * <em>address1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddress1IsSet() {
    return address1_is_set;
  }


  /** 
   * <em>address1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddress1IsModified() {
    return address1_is_modified;
  }


  /** 
   * <em>address2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddress2()
  {
    return address2;
  }


  /** 
   * <em>address2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddress2IsSet() {
    return address2_is_set;
  }


  /** 
   * <em>address2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddress2IsModified() {
    return address2_is_modified;
  }


  /** 
   * <em>address3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddress3()
  {
    return address3;
  }


  /** 
   * <em>address3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddress3IsSet() {
    return address3_is_set;
  }


  /** 
   * <em>address3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddress3IsModified() {
    return address3_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    throw new UnsupportedOperationException("Table gft_message has no primary key.");
  }


  /** 
   * <em>message_div</em>カラムの値を設定します。 
   *
   * @@param p_messageDiv <em>message_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMessageDiv( String p_messageDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    message_div = p_messageDiv;
    message_div_is_set = true;
    message_div_is_modified = true;
  }


  /** 
   * <em>dir_send_time</em>カラムの値を設定します。 
   *
   * @@param p_dirSendTime <em>dir_send_time</em>カラムの値をあらわす14桁以下のString値 
   */
  public final void setDirSendTime( String p_dirSendTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dir_send_time = p_dirSendTime;
    dir_send_time_is_set = true;
    dir_send_time_is_modified = true;
  }


  /** 
   * <em>operation</em>カラムの値を設定します。 
   *
   * @@param p_operation <em>operation</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOperation( String p_operation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operation = p_operation;
    operation_is_set = true;
    operation_is_modified = true;
  }


  /** 
   * <em>account</em>カラムの値を設定します。 
   *
   * @@param p_account <em>account</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setAccount( String p_account )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account = p_account;
    account_is_set = true;
    account_is_modified = true;
  }


  /** 
   * <em>email</em>カラムの値を設定します。 
   *
   * @@param p_email <em>email</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setEmail( String p_email )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email = p_email;
    email_is_set = true;
    email_is_modified = true;
  }


  /** 
   * <em>gft_link_1</em>カラムの値を設定します。 
   *
   * @@param p_gftLink1 <em>gft_link_1</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setGftLink1( String p_gftLink1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gft_link_1 = p_gftLink1;
    gft_link_1_is_set = true;
    gft_link_1_is_modified = true;
  }


  /** 
   * <em>gft_link_2</em>カラムの値を設定します。 
   *
   * @@param p_gftLink2 <em>gft_link_2</em>カラムの値をあらわす12桁以下のString値 
   */
  public final void setGftLink2( String p_gftLink2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gft_link_2 = p_gftLink2;
    gft_link_2_is_set = true;
    gft_link_2_is_modified = true;
  }


  /** 
   * <em>group_name</em>カラムの値を設定します。 
   *
   * @@param p_groupName <em>group_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setGroupName( String p_groupName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    group_name = p_groupName;
    group_name_is_set = true;
    group_name_is_modified = true;
  }


  /** 
   * <em>amount</em>カラムの値を設定します。 
   *
   * @@param p_amount <em>amount</em>カラムの値をあらわす12桁以下のString値 
   */
  public final void setAmount( String p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount = p_amount;
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * <em>wolf_session_key</em>カラムの値を設定します。 
   *
   * @@param p_wolfSessionKey <em>wolf_session_key</em>カラムの値をあらわす80桁以下のString値 
   */
  public final void setWolfSessionKey( String p_wolfSessionKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    wolf_session_key = p_wolfSessionKey;
    wolf_session_key_is_set = true;
    wolf_session_key_is_modified = true;
  }


  /** 
   * <em>aa_aid</em>カラムの値を設定します。 
   *
   * @@param p_aaAid <em>aa_aid</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setAaAid( String p_aaAid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    aa_aid = p_aaAid;
    aa_aid_is_set = true;
    aa_aid_is_modified = true;
  }


  /** 
   * <em>aa_rsid</em>カラムの値を設定します。 
   *
   * @@param p_aaRsid <em>aa_rsid</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setAaRsid( String p_aaRsid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    aa_rsid = p_aaRsid;
    aa_rsid_is_set = true;
    aa_rsid_is_modified = true;
  }


  /** 
   * <em>ssid</em>カラムの値を設定します。 
   *
   * @@param p_ssid <em>ssid</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSsid( String p_ssid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ssid = p_ssid;
    ssid_is_set = true;
    ssid_is_modified = true;
  }


  /** 
   * <em>cpy</em>カラムの値を設定します。 
   *
   * @@param p_cpy <em>cpy</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setCpy( String p_cpy )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cpy = p_cpy;
    cpy_is_set = true;
    cpy_is_modified = true;
  }


  /** 
   * <em>brn</em>カラムの値を設定します。 
   *
   * @@param p_brn <em>brn</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBrn( String p_brn )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    brn = p_brn;
    brn_is_set = true;
    brn_is_modified = true;
  }


  /** 
   * <em>acc</em>カラムの値を設定します。 
   *
   * @@param p_acc <em>acc</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAcc( String p_acc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc = p_acc;
    acc_is_set = true;
    acc_is_modified = true;
  }


  /** 
   * <em>req</em>カラムの値を設定します。 
   *
   * @@param p_req <em>req</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setReq( String p_req )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    req = p_req;
    req_is_set = true;
    req_is_modified = true;
  }


  /** 
   * <em>result_code</em>カラムの値を設定します。 
   *
   * @@param p_resultCode <em>result_code</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setResultCode( String p_resultCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    result_code = p_resultCode;
    result_code_is_set = true;
    result_code_is_modified = true;
  }


  /** 
   * <em>gft_send_time</em>カラムの値を設定します。 
   *
   * @@param p_gftSendTime <em>gft_send_time</em>カラムの値をあらわす14桁以下のString値 
   */
  public final void setGftSendTime( String p_gftSendTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gft_send_time = p_gftSendTime;
    gft_send_time_is_set = true;
    gft_send_time_is_modified = true;
  }


  /** 
   * <em>gft_ac1</em>カラムの値を設定します。 
   *
   * @@param p_gftAc1 <em>gft_ac1</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setGftAc1( String p_gftAc1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gft_ac1 = p_gftAc1;
    gft_ac1_is_set = true;
    gft_ac1_is_modified = true;
  }


  /** 
   * <em>gft_ac2</em>カラムの値を設定します。 
   *
   * @@param p_gftAc2 <em>gft_ac2</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setGftAc2( String p_gftAc2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gft_ac2 = p_gftAc2;
    gft_ac2_is_set = true;
    gft_ac2_is_modified = true;
  }


  /** 
   * <em>last_name</em>カラムの値を設定します。 
   *
   * @@param p_lastName <em>last_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setLastName( String p_lastName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_name = p_lastName;
    last_name_is_set = true;
    last_name_is_modified = true;
  }


  /** 
   * <em>first_name</em>カラムの値を設定します。 
   *
   * @@param p_firstName <em>first_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setFirstName( String p_firstName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_name = p_firstName;
    first_name_is_set = true;
    first_name_is_modified = true;
  }


  /** 
   * <em>hash_key</em>カラムの値を設定します。 
   *
   * @@param p_hashKey <em>hash_key</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setHashKey( String p_hashKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hash_key = p_hashKey;
    hash_key_is_set = true;
    hash_key_is_modified = true;
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
   * <em>delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setDeliveryDate( String p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>amount2</em>カラムの値を設定します。 
   *
   * @@param p_amount2 <em>amount2</em>カラムの値をあらわす12桁以下のString値 
   */
  public final void setAmount2( String p_amount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount2 = p_amount2;
    amount2_is_set = true;
    amount2_is_modified = true;
  }


  /** 
   * <em>address1</em>カラムの値を設定します。 
   *
   * @@param p_address1 <em>address1</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setAddress1( String p_address1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address1 = p_address1;
    address1_is_set = true;
    address1_is_modified = true;
  }


  /** 
   * <em>address2</em>カラムの値を設定します。 
   *
   * @@param p_address2 <em>address2</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setAddress2( String p_address2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address2 = p_address2;
    address2_is_set = true;
    address2_is_modified = true;
  }


  /** 
   * <em>address3</em>カラムの値を設定します。 
   *
   * @@param p_address3 <em>address3</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setAddress3( String p_address3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address3 = p_address3;
    address3_is_set = true;
    address3_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account") ) {
                    return this.account;
                }
                else if ( name.equals("amount") ) {
                    return this.amount;
                }
                else if ( name.equals("aa_aid") ) {
                    return this.aa_aid;
                }
                else if ( name.equals("aa_rsid") ) {
                    return this.aa_rsid;
                }
                else if ( name.equals("acc") ) {
                    return this.acc;
                }
                else if ( name.equals("amount2") ) {
                    return this.amount2;
                }
                else if ( name.equals("address1") ) {
                    return this.address1;
                }
                else if ( name.equals("address2") ) {
                    return this.address2;
                }
                else if ( name.equals("address3") ) {
                    return this.address3;
                }
                break;
            case 'b':
                if ( name.equals("brn") ) {
                    return this.brn;
                }
                break;
            case 'c':
                if ( name.equals("cpy") ) {
                    return this.cpy;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("dir_send_time") ) {
                    return this.dir_send_time;
                }
                else if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'e':
                if ( name.equals("email") ) {
                    return this.email;
                }
                break;
            case 'f':
                if ( name.equals("first_name") ) {
                    return this.first_name;
                }
                break;
            case 'g':
                if ( name.equals("gft_link_1") ) {
                    return this.gft_link_1;
                }
                else if ( name.equals("gft_link_2") ) {
                    return this.gft_link_2;
                }
                else if ( name.equals("group_name") ) {
                    return this.group_name;
                }
                else if ( name.equals("gft_send_time") ) {
                    return this.gft_send_time;
                }
                else if ( name.equals("gft_ac1") ) {
                    return this.gft_ac1;
                }
                else if ( name.equals("gft_ac2") ) {
                    return this.gft_ac2;
                }
                break;
            case 'h':
                if ( name.equals("hash_key") ) {
                    return this.hash_key;
                }
                break;
            case 'l':
                if ( name.equals("last_name") ) {
                    return this.last_name;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("message_div") ) {
                    return this.message_div;
                }
                break;
            case 'o':
                if ( name.equals("operation") ) {
                    return this.operation;
                }
                break;
            case 'r':
                if ( name.equals("req") ) {
                    return this.req;
                }
                else if ( name.equals("result_code") ) {
                    return this.result_code;
                }
                break;
            case 's':
                if ( name.equals("ssid") ) {
                    return this.ssid;
                }
                break;
            case 'w':
                if ( name.equals("wolf_session_key") ) {
                    return this.wolf_session_key;
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
                if ( name.equals("account") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account' must be String: '"+value+"' is not." );
                    this.account = (String) value;
                    if (this.account_is_set)
                        this.account_is_modified = true;
                    this.account_is_set = true;
                    return;
                }
                else if ( name.equals("amount") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'amount' must be String: '"+value+"' is not." );
                    this.amount = (String) value;
                    if (this.amount_is_set)
                        this.amount_is_modified = true;
                    this.amount_is_set = true;
                    return;
                }
                else if ( name.equals("aa_aid") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'aa_aid' must be String: '"+value+"' is not." );
                    this.aa_aid = (String) value;
                    if (this.aa_aid_is_set)
                        this.aa_aid_is_modified = true;
                    this.aa_aid_is_set = true;
                    return;
                }
                else if ( name.equals("aa_rsid") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'aa_rsid' must be String: '"+value+"' is not." );
                    this.aa_rsid = (String) value;
                    if (this.aa_rsid_is_set)
                        this.aa_rsid_is_modified = true;
                    this.aa_rsid_is_set = true;
                    return;
                }
                else if ( name.equals("acc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc' must be String: '"+value+"' is not." );
                    this.acc = (String) value;
                    if (this.acc_is_set)
                        this.acc_is_modified = true;
                    this.acc_is_set = true;
                    return;
                }
                else if ( name.equals("amount2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'amount2' must be String: '"+value+"' is not." );
                    this.amount2 = (String) value;
                    if (this.amount2_is_set)
                        this.amount2_is_modified = true;
                    this.amount2_is_set = true;
                    return;
                }
                else if ( name.equals("address1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address1' must be String: '"+value+"' is not." );
                    this.address1 = (String) value;
                    if (this.address1_is_set)
                        this.address1_is_modified = true;
                    this.address1_is_set = true;
                    return;
                }
                else if ( name.equals("address2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address2' must be String: '"+value+"' is not." );
                    this.address2 = (String) value;
                    if (this.address2_is_set)
                        this.address2_is_modified = true;
                    this.address2_is_set = true;
                    return;
                }
                else if ( name.equals("address3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address3' must be String: '"+value+"' is not." );
                    this.address3 = (String) value;
                    if (this.address3_is_set)
                        this.address3_is_modified = true;
                    this.address3_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("brn") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'brn' must be String: '"+value+"' is not." );
                    this.brn = (String) value;
                    if (this.brn_is_set)
                        this.brn_is_modified = true;
                    this.brn_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("cpy") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cpy' must be String: '"+value+"' is not." );
                    this.cpy = (String) value;
                    if (this.cpy_is_set)
                        this.cpy_is_modified = true;
                    this.cpy_is_set = true;
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
                if ( name.equals("dir_send_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dir_send_time' must be String: '"+value+"' is not." );
                    this.dir_send_time = (String) value;
                    if (this.dir_send_time_is_set)
                        this.dir_send_time_is_modified = true;
                    this.dir_send_time_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be String: '"+value+"' is not." );
                    this.delivery_date = (String) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("email") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email' must be String: '"+value+"' is not." );
                    this.email = (String) value;
                    if (this.email_is_set)
                        this.email_is_modified = true;
                    this.email_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("first_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'first_name' must be String: '"+value+"' is not." );
                    this.first_name = (String) value;
                    if (this.first_name_is_set)
                        this.first_name_is_modified = true;
                    this.first_name_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("gft_link_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gft_link_1' must be String: '"+value+"' is not." );
                    this.gft_link_1 = (String) value;
                    if (this.gft_link_1_is_set)
                        this.gft_link_1_is_modified = true;
                    this.gft_link_1_is_set = true;
                    return;
                }
                else if ( name.equals("gft_link_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gft_link_2' must be String: '"+value+"' is not." );
                    this.gft_link_2 = (String) value;
                    if (this.gft_link_2_is_set)
                        this.gft_link_2_is_modified = true;
                    this.gft_link_2_is_set = true;
                    return;
                }
                else if ( name.equals("group_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'group_name' must be String: '"+value+"' is not." );
                    this.group_name = (String) value;
                    if (this.group_name_is_set)
                        this.group_name_is_modified = true;
                    this.group_name_is_set = true;
                    return;
                }
                else if ( name.equals("gft_send_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gft_send_time' must be String: '"+value+"' is not." );
                    this.gft_send_time = (String) value;
                    if (this.gft_send_time_is_set)
                        this.gft_send_time_is_modified = true;
                    this.gft_send_time_is_set = true;
                    return;
                }
                else if ( name.equals("gft_ac1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gft_ac1' must be String: '"+value+"' is not." );
                    this.gft_ac1 = (String) value;
                    if (this.gft_ac1_is_set)
                        this.gft_ac1_is_modified = true;
                    this.gft_ac1_is_set = true;
                    return;
                }
                else if ( name.equals("gft_ac2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gft_ac2' must be String: '"+value+"' is not." );
                    this.gft_ac2 = (String) value;
                    if (this.gft_ac2_is_set)
                        this.gft_ac2_is_modified = true;
                    this.gft_ac2_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("hash_key") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hash_key' must be String: '"+value+"' is not." );
                    this.hash_key = (String) value;
                    if (this.hash_key_is_set)
                        this.hash_key_is_modified = true;
                    this.hash_key_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_name' must be String: '"+value+"' is not." );
                    this.last_name = (String) value;
                    if (this.last_name_is_set)
                        this.last_name_is_modified = true;
                    this.last_name_is_set = true;
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
            case 'm':
                if ( name.equals("message_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'message_div' must be String: '"+value+"' is not." );
                    this.message_div = (String) value;
                    if (this.message_div_is_set)
                        this.message_div_is_modified = true;
                    this.message_div_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("operation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operation' must be String: '"+value+"' is not." );
                    this.operation = (String) value;
                    if (this.operation_is_set)
                        this.operation_is_modified = true;
                    this.operation_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("req") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'req' must be String: '"+value+"' is not." );
                    this.req = (String) value;
                    if (this.req_is_set)
                        this.req_is_modified = true;
                    this.req_is_set = true;
                    return;
                }
                else if ( name.equals("result_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'result_code' must be String: '"+value+"' is not." );
                    this.result_code = (String) value;
                    if (this.result_code_is_set)
                        this.result_code_is_modified = true;
                    this.result_code_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("ssid") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ssid' must be String: '"+value+"' is not." );
                    this.ssid = (String) value;
                    if (this.ssid_is_set)
                        this.ssid_is_modified = true;
                    this.ssid_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("wolf_session_key") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'wolf_session_key' must be String: '"+value+"' is not." );
                    this.wolf_session_key = (String) value;
                    if (this.wolf_session_key_is_set)
                        this.wolf_session_key_is_modified = true;
                    this.wolf_session_key_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
