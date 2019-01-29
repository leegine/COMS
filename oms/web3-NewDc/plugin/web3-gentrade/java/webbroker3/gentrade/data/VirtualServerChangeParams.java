head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.22.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	VirtualServerChangeParams.java;


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
 * virtual_server_changeテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link VirtualServerChangeRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link VirtualServerChangeParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link VirtualServerChangeParams}が{@@link VirtualServerChangeRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see VirtualServerChangePK 
 * @@see VirtualServerChangeRow 
 */
public class VirtualServerChangeParams extends Params implements VirtualServerChangeRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "virtual_server_change";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = VirtualServerChangeRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return VirtualServerChangeRow.TYPE;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値 
   */
  public  String  virtual_server_number_market;

  /** 
   * <em>change_req_res_div</em>カラムの値 
   */
  public  String  change_req_res_div;

  /** 
   * <em>notice_type</em>カラムの値 
   */
  public  String  notice_type;

  /** 
   * <em>front_order_exchange_code</em>カラムの値 
   */
  public  String  front_order_exchange_code;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>front_order_system_code</em>カラムの値 
   */
  public  String  front_order_system_code;

  /** 
   * <em>front_order_trade_code</em>カラムの値 
   */
  public  String  front_order_trade_code;

  /** 
   * <em>corp_code</em>カラムの値 
   */
  public  String  corp_code;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>virtual_server_number_sonar</em>カラムの値 
   */
  public  String  virtual_server_number_sonar;

  /** 
   * <em>client_number</em>カラムの値 
   */
  public  String  client_number;

  /** 
   * <em>notice_file_number</em>カラムの値 
   */
  public  String  notice_file_number;

  /** 
   * <em>resend_notice_number_from</em>カラムの値 
   */
  public  Long  resend_notice_number_from;

  /** 
   * <em>error_message</em>カラムの値 
   */
  public  String  error_message;

  /** 
   * <em>action_count</em>カラムの値 
   */
  public  Integer  action_count;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>agency_accept_notice_number</em>カラムの値 
   */
  public  Long  agency_accept_notice_number;

  /** 
   * <em>agency_exec_notice_number</em>カラムの値 
   */
  public  Long  agency_exec_notice_number;

  private boolean virtual_server_number_market_is_set = false;
  private boolean virtual_server_number_market_is_modified = false;
  private boolean change_req_res_div_is_set = false;
  private boolean change_req_res_div_is_modified = false;
  private boolean notice_type_is_set = false;
  private boolean notice_type_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean front_order_exchange_code_is_set = false;
  private boolean front_order_exchange_code_is_modified = false;
  private boolean front_order_system_code_is_set = false;
  private boolean front_order_system_code_is_modified = false;
  private boolean front_order_trade_code_is_set = false;
  private boolean front_order_trade_code_is_modified = false;
  private boolean corp_code_is_set = false;
  private boolean corp_code_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean virtual_server_number_sonar_is_set = false;
  private boolean virtual_server_number_sonar_is_modified = false;
  private boolean client_number_is_set = false;
  private boolean client_number_is_modified = false;
  private boolean notice_file_number_is_set = false;
  private boolean notice_file_number_is_modified = false;
  private boolean resend_notice_number_from_is_set = false;
  private boolean resend_notice_number_from_is_modified = false;
  private boolean error_message_is_set = false;
  private boolean error_message_is_modified = false;
  private boolean action_count_is_set = false;
  private boolean action_count_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean agency_accept_notice_number_is_set = false;
  private boolean agency_accept_notice_number_is_modified = false;
  private boolean agency_exec_notice_number_is_set = false;
  private boolean agency_exec_notice_number_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "virtual_server_number_market=" + virtual_server_number_market
      + "," + "change_req_res_div=" + change_req_res_div
      + "," + "notice_type=" + notice_type
      + "," + "front_order_exchange_code=" + front_order_exchange_code
      + "," + "institution_code=" +institution_code
      + "," + "front_order_system_code=" +front_order_system_code
      + "," + "front_order_trade_code=" +front_order_trade_code
      + "," + "corp_code=" +corp_code
      + "," + "cancel_div=" +cancel_div
      + "," + "virtual_server_number_sonar=" +virtual_server_number_sonar
      + "," + "client_number=" +client_number
      + "," + "notice_file_number=" +notice_file_number
      + "," + "resend_notice_number_from=" +resend_notice_number_from
      + "," + "error_message=" +error_message
      + "," + "action_count=" +action_count
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "product_type=" +product_type
      + "," + "agency_accept_notice_number=" +agency_accept_notice_number
      + "," + "agency_exec_notice_number=" +agency_exec_notice_number
      + "}";
  }


  /** 
   * 値が未設定のVirtualServerChangeParamsオブジェクトを作成します。 
   */
  public VirtualServerChangeParams() {
    virtual_server_number_market_is_modified = true;
    change_req_res_div_is_modified = true;
    notice_type_is_modified = true;
    front_order_exchange_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のVirtualServerChangeRowオブジェクトの値を利用してVirtualServerChangeParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するVirtualServerChangeRowオブジェクト 
   */
  public VirtualServerChangeParams( VirtualServerChangeRow p_row )
  {
    virtual_server_number_market = p_row.getVirtualServerNumberMarket();
    virtual_server_number_market_is_set = p_row.getVirtualServerNumberMarketIsSet();
    virtual_server_number_market_is_modified = p_row.getVirtualServerNumberMarketIsModified();
    change_req_res_div = p_row.getChangeReqResDiv();
    change_req_res_div_is_set = p_row.getChangeReqResDivIsSet();
    change_req_res_div_is_modified = p_row.getChangeReqResDivIsModified();
    notice_type = p_row.getNoticeType();
    notice_type_is_set = p_row.getNoticeTypeIsSet();
    notice_type_is_modified = p_row.getNoticeTypeIsModified();
    front_order_exchange_code = p_row.getFrontOrderExchangeCode();
    front_order_exchange_code_is_set = p_row.getFrontOrderExchangeCodeIsSet();
    front_order_exchange_code_is_modified = p_row.getFrontOrderExchangeCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    front_order_system_code = p_row.getFrontOrderSystemCode();
    front_order_system_code_is_set = p_row.getFrontOrderSystemCodeIsSet();
    front_order_system_code_is_modified = p_row.getFrontOrderSystemCodeIsModified();
    front_order_trade_code = p_row.getFrontOrderTradeCode();
    front_order_trade_code_is_set = p_row.getFrontOrderTradeCodeIsSet();
    front_order_trade_code_is_modified = p_row.getFrontOrderTradeCodeIsModified();
    corp_code = p_row.getCorpCode();
    corp_code_is_set = p_row.getCorpCodeIsSet();
    corp_code_is_modified = p_row.getCorpCodeIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    virtual_server_number_sonar = p_row.getVirtualServerNumberSonar();
    virtual_server_number_sonar_is_set = p_row.getVirtualServerNumberSonarIsSet();
    virtual_server_number_sonar_is_modified = p_row.getVirtualServerNumberSonarIsModified();
    client_number = p_row.getClientNumber();
    client_number_is_set = p_row.getClientNumberIsSet();
    client_number_is_modified = p_row.getClientNumberIsModified();
    notice_file_number = p_row.getNoticeFileNumber();
    notice_file_number_is_set = p_row.getNoticeFileNumberIsSet();
    notice_file_number_is_modified = p_row.getNoticeFileNumberIsModified();
    if ( !p_row.getResendNoticeNumberFromIsNull() )
      resend_notice_number_from = new Long( p_row.getResendNoticeNumberFrom() );
    resend_notice_number_from_is_set = p_row.getResendNoticeNumberFromIsSet();
    resend_notice_number_from_is_modified = p_row.getResendNoticeNumberFromIsModified();
    error_message = p_row.getErrorMessage();
    error_message_is_set = p_row.getErrorMessageIsSet();
    error_message_is_modified = p_row.getErrorMessageIsModified();
    if ( !p_row.getActionCountIsNull() )
      action_count = new Integer( p_row.getActionCount() );
    action_count_is_set = p_row.getActionCountIsSet();
    action_count_is_modified = p_row.getActionCountIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    if ( !p_row.getAgencyAcceptNoticeNumberIsNull() )
      agency_accept_notice_number = new Long( p_row.getAgencyAcceptNoticeNumber() );
    agency_accept_notice_number_is_set = p_row.getAgencyAcceptNoticeNumberIsSet();
    agency_accept_notice_number_is_modified = p_row.getAgencyAcceptNoticeNumberIsModified();
    if ( !p_row.getAgencyExecNoticeNumberIsNull() )
      agency_exec_notice_number = new Long( p_row.getAgencyExecNoticeNumber() );
    agency_exec_notice_number_is_set = p_row.getAgencyExecNoticeNumberIsSet();
    agency_exec_notice_number_is_modified = p_row.getAgencyExecNoticeNumberIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      front_order_system_code_is_set = true;
      front_order_system_code_is_modified = true;
      front_order_trade_code_is_set = true;
      front_order_trade_code_is_modified = true;
      corp_code_is_set = true;
      corp_code_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      virtual_server_number_sonar_is_set = true;
      virtual_server_number_sonar_is_modified = true;
      client_number_is_set = true;
      client_number_is_modified = true;
      notice_file_number_is_set = true;
      notice_file_number_is_modified = true;
      resend_notice_number_from_is_set = true;
      resend_notice_number_from_is_modified = true;
      error_message_is_set = true;
      error_message_is_modified = true;
      action_count_is_set = true;
      action_count_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      agency_accept_notice_number_is_set = true;
      agency_accept_notice_number_is_modified = true;
      agency_exec_notice_number_is_set = true;
      agency_exec_notice_number_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof VirtualServerChangeRow ) )
       return false;
    return fieldsEqual( (VirtualServerChangeRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のVirtualServerChangeRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( VirtualServerChangeRow row )
  {
    if ( virtual_server_number_market == null ) {
      if ( row.getVirtualServerNumberMarket() != null )
        return false;
    } else if ( !virtual_server_number_market.equals( row.getVirtualServerNumberMarket() ) ) {
        return false;
    }
    if ( change_req_res_div == null ) {
      if ( row.getChangeReqResDiv() != null )
        return false;
    } else if ( !change_req_res_div.equals( row.getChangeReqResDiv() ) ) {
        return false;
    }
    if ( notice_type == null ) {
      if ( row.getNoticeType() != null )
        return false;
    } else if ( !notice_type.equals( row.getNoticeType() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( front_order_exchange_code == null ) {
      if ( row.getFrontOrderExchangeCode() != null )
        return false;
    } else if ( !front_order_exchange_code.equals( row.getFrontOrderExchangeCode() ) ) {
        return false;
    }
    if ( front_order_system_code == null ) {
      if ( row.getFrontOrderSystemCode() != null )
        return false;
    } else if ( !front_order_system_code.equals( row.getFrontOrderSystemCode() ) ) {
        return false;
    }
    if ( front_order_trade_code == null ) {
      if ( row.getFrontOrderTradeCode() != null )
        return false;
    } else if ( !front_order_trade_code.equals( row.getFrontOrderTradeCode() ) ) {
        return false;
    }
    if ( corp_code == null ) {
      if ( row.getCorpCode() != null )
        return false;
    } else if ( !corp_code.equals( row.getCorpCode() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( virtual_server_number_sonar == null ) {
      if ( row.getVirtualServerNumberSonar() != null )
        return false;
    } else if ( !virtual_server_number_sonar.equals( row.getVirtualServerNumberSonar() ) ) {
        return false;
    }
    if ( client_number == null ) {
      if ( row.getClientNumber() != null )
        return false;
    } else if ( !client_number.equals( row.getClientNumber() ) ) {
        return false;
    }
    if ( notice_file_number == null ) {
      if ( row.getNoticeFileNumber() != null )
        return false;
    } else if ( !notice_file_number.equals( row.getNoticeFileNumber() ) ) {
        return false;
    }
    if ( resend_notice_number_from == null ) {
      if ( !row.getResendNoticeNumberFromIsNull() )
        return false;
    } else if ( row.getResendNoticeNumberFromIsNull() || ( resend_notice_number_from.longValue() != row.getResendNoticeNumberFrom() ) ) {
        return false;
    }
    if ( error_message == null ) {
      if ( row.getErrorMessage() != null )
        return false;
    } else if ( !error_message.equals( row.getErrorMessage() ) ) {
        return false;
    }
    if ( action_count == null ) {
      if ( !row.getActionCountIsNull() )
        return false;
    } else if ( row.getActionCountIsNull() || ( action_count.intValue() != row.getActionCount() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( agency_accept_notice_number == null ) {
      if ( !row.getAgencyAcceptNoticeNumberIsNull() )
        return false;
    } else if ( row.getAgencyAcceptNoticeNumberIsNull() || ( agency_accept_notice_number.longValue() != row.getAgencyAcceptNoticeNumber() ) ) {
        return false;
    }
    if ( agency_exec_notice_number == null ) {
      if ( !row.getAgencyExecNoticeNumberIsNull() )
        return false;
    } else if ( row.getAgencyExecNoticeNumberIsNull() || ( agency_exec_notice_number.longValue() != row.getAgencyExecNoticeNumber() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (virtual_server_number_market!=null? virtual_server_number_market.hashCode(): 0) 
        + (change_req_res_div!=null? change_req_res_div.hashCode(): 0) 
        + (notice_type!=null? notice_type.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (front_order_exchange_code!=null? front_order_exchange_code.hashCode(): 0) 
        + (front_order_system_code!=null? front_order_system_code.hashCode(): 0) 
        + (front_order_trade_code!=null? front_order_trade_code.hashCode(): 0) 
        + (corp_code!=null? corp_code.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (virtual_server_number_sonar!=null? virtual_server_number_sonar.hashCode(): 0) 
        + (client_number!=null? client_number.hashCode(): 0) 
        + (notice_file_number!=null? notice_file_number.hashCode(): 0) 
        + (resend_notice_number_from!=null? resend_notice_number_from.hashCode(): 0) 
        + (error_message!=null? error_message.hashCode(): 0) 
        + (action_count!=null? action_count.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (agency_accept_notice_number!=null? agency_accept_notice_number.hashCode(): 0) 
        + (agency_exec_notice_number!=null? agency_exec_notice_number.hashCode(): 0) 
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
		map.put("virtual_server_number_market",virtual_server_number_market);
		map.put("change_req_res_div",change_req_res_div);
		map.put("notice_type",notice_type);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		map.put("front_order_exchange_code",front_order_exchange_code);
		if ( front_order_system_code != null )
			map.put("front_order_system_code",front_order_system_code);
		if ( front_order_trade_code != null )
			map.put("front_order_trade_code",front_order_trade_code);
		if ( corp_code != null )
			map.put("corp_code",corp_code);
		if ( cancel_div != null )
			map.put("cancel_div",cancel_div);
		if ( virtual_server_number_sonar != null )
			map.put("virtual_server_number_sonar",virtual_server_number_sonar);
		if ( client_number != null )
			map.put("client_number",client_number);
		if ( notice_file_number != null )
			map.put("notice_file_number",notice_file_number);
		if ( resend_notice_number_from != null )
			map.put("resend_notice_number_from",resend_notice_number_from);
		if ( error_message != null )
			map.put("error_message",error_message);
		if ( action_count != null )
			map.put("action_count",action_count);
		if ( status != null )
			map.put("status",status);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( product_type != null )
			map.put("product_type",product_type);
		if ( agency_accept_notice_number != null )
			map.put("agency_accept_notice_number",agency_accept_notice_number);
		if ( agency_exec_notice_number != null )
			map.put("agency_exec_notice_number",agency_exec_notice_number);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( front_order_system_code_is_modified )
			map.put("front_order_system_code",front_order_system_code);
		if ( front_order_trade_code_is_modified )
			map.put("front_order_trade_code",front_order_trade_code);
		if ( corp_code_is_modified )
			map.put("corp_code",corp_code);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( virtual_server_number_sonar_is_modified )
			map.put("virtual_server_number_sonar",virtual_server_number_sonar);
		if ( client_number_is_modified )
			map.put("client_number",client_number);
		if ( notice_file_number_is_modified )
			map.put("notice_file_number",notice_file_number);
		if ( resend_notice_number_from_is_modified )
			map.put("resend_notice_number_from",resend_notice_number_from);
		if ( error_message_is_modified )
			map.put("error_message",error_message);
		if ( action_count_is_modified )
			map.put("action_count",action_count);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( agency_accept_notice_number_is_modified )
			map.put("agency_accept_notice_number",agency_accept_notice_number);
		if ( agency_exec_notice_number_is_modified )
			map.put("agency_exec_notice_number",agency_exec_notice_number);
		if (map.size() == 0) {
			map.put("institution_code",institution_code);
			map.put("front_order_system_code",front_order_system_code);
			map.put("front_order_trade_code",front_order_trade_code);
			map.put("corp_code",corp_code);
			map.put("cancel_div",cancel_div);
			map.put("virtual_server_number_sonar",virtual_server_number_sonar);
			map.put("client_number",client_number);
			map.put("notice_file_number",notice_file_number);
			map.put("resend_notice_number_from",resend_notice_number_from);
			map.put("error_message",error_message);
			map.put("action_count",action_count);
			map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("product_type",product_type);
			map.put("agency_accept_notice_number",agency_accept_notice_number);
			map.put("agency_exec_notice_number",agency_exec_notice_number);
		}
		return map;
	}


  /** 
   * <em>virtual_server_number_market</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getVirtualServerNumberMarket()
  {
    return virtual_server_number_market;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberMarketIsSet() {
    return virtual_server_number_market_is_set;
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberMarketIsModified() {
    return virtual_server_number_market_is_modified;
  }


  /** 
   * <em>change_req_res_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChangeReqResDiv()
  {
    return change_req_res_div;
  }


  /** 
   * <em>change_req_res_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeReqResDivIsSet() {
    return change_req_res_div_is_set;
  }


  /** 
   * <em>change_req_res_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeReqResDivIsModified() {
    return change_req_res_div_is_modified;
  }


  /** 
   * <em>notice_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNoticeType()
  {
    return notice_type;
  }


  /** 
   * <em>notice_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeTypeIsSet() {
    return notice_type_is_set;
  }


  /** 
   * <em>notice_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeTypeIsModified() {
    return notice_type_is_modified;
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
   * <em>front_order_exchange_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFrontOrderExchangeCode()
  {
    return front_order_exchange_code;
  }


  /** 
   * <em>front_order_exchange_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderExchangeCodeIsSet() {
    return front_order_exchange_code_is_set;
  }


  /** 
   * <em>front_order_exchange_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderExchangeCodeIsModified() {
    return front_order_exchange_code_is_modified;
  }


  /** 
   * <em>front_order_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFrontOrderSystemCode()
  {
    return front_order_system_code;
  }


  /** 
   * <em>front_order_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderSystemCodeIsSet() {
    return front_order_system_code_is_set;
  }


  /** 
   * <em>front_order_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderSystemCodeIsModified() {
    return front_order_system_code_is_modified;
  }


  /** 
   * <em>front_order_trade_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFrontOrderTradeCode()
  {
    return front_order_trade_code;
  }


  /** 
   * <em>front_order_trade_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderTradeCodeIsSet() {
    return front_order_trade_code_is_set;
  }


  /** 
   * <em>front_order_trade_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFrontOrderTradeCodeIsModified() {
    return front_order_trade_code_is_modified;
  }


  /** 
   * <em>corp_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCorpCode()
  {
    return corp_code;
  }


  /** 
   * <em>corp_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorpCodeIsSet() {
    return corp_code_is_set;
  }


  /** 
   * <em>corp_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorpCodeIsModified() {
    return corp_code_is_modified;
  }


  /** 
   * <em>cancel_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCancelDiv()
  {
    return cancel_div;
  }


  /** 
   * <em>cancel_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelDivIsSet() {
    return cancel_div_is_set;
  }


  /** 
   * <em>cancel_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelDivIsModified() {
    return cancel_div_is_modified;
  }


  /** 
   * <em>virtual_server_number_sonar</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getVirtualServerNumberSonar()
  {
    return virtual_server_number_sonar;
  }


  /** 
   * <em>virtual_server_number_sonar</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberSonarIsSet() {
    return virtual_server_number_sonar_is_set;
  }


  /** 
   * <em>virtual_server_number_sonar</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVirtualServerNumberSonarIsModified() {
    return virtual_server_number_sonar_is_modified;
  }


  /** 
   * <em>client_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClientNumber()
  {
    return client_number;
  }


  /** 
   * <em>client_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClientNumberIsSet() {
    return client_number_is_set;
  }


  /** 
   * <em>client_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClientNumberIsModified() {
    return client_number_is_modified;
  }


  /** 
   * <em>notice_file_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNoticeFileNumber()
  {
    return notice_file_number;
  }


  /** 
   * <em>notice_file_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeFileNumberIsSet() {
    return notice_file_number_is_set;
  }


  /** 
   * <em>notice_file_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoticeFileNumberIsModified() {
    return notice_file_number_is_modified;
  }


  /** 
   * <em>resend_notice_number_from</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getResendNoticeNumberFrom()
  {
    return ( resend_notice_number_from==null? ((long)0): resend_notice_number_from.longValue() );
  }


  /** 
   * <em>resend_notice_number_from</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getResendNoticeNumberFromIsNull()
  {
    return resend_notice_number_from == null;
  }


  /** 
   * <em>resend_notice_number_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResendNoticeNumberFromIsSet() {
    return resend_notice_number_from_is_set;
  }


  /** 
   * <em>resend_notice_number_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResendNoticeNumberFromIsModified() {
    return resend_notice_number_from_is_modified;
  }


  /** 
   * <em>error_message</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorMessage()
  {
    return error_message;
  }


  /** 
   * <em>error_message</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorMessageIsSet() {
    return error_message_is_set;
  }


  /** 
   * <em>error_message</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorMessageIsModified() {
    return error_message_is_modified;
  }


  /** 
   * <em>action_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getActionCount()
  {
    return ( action_count==null? ((int)0): action_count.intValue() );
  }


  /** 
   * <em>action_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActionCountIsNull()
  {
    return action_count == null;
  }


  /** 
   * <em>action_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActionCountIsSet() {
    return action_count_is_set;
  }


  /** 
   * <em>action_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActionCountIsModified() {
    return action_count_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
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
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>agency_accept_notice_number</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAgencyAcceptNoticeNumber()
  {
    return ( agency_accept_notice_number==null? ((long)0): agency_accept_notice_number.longValue() );
  }


  /** 
   * <em>agency_accept_notice_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAgencyAcceptNoticeNumberIsNull()
  {
    return agency_accept_notice_number == null;
  }


  /** 
   * <em>agency_accept_notice_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAcceptNoticeNumberIsSet() {
    return agency_accept_notice_number_is_set;
  }


  /** 
   * <em>agency_accept_notice_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAcceptNoticeNumberIsModified() {
    return agency_accept_notice_number_is_modified;
  }


  /** 
   * <em>agency_exec_notice_number</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAgencyExecNoticeNumber()
  {
    return ( agency_exec_notice_number==null? ((long)0): agency_exec_notice_number.longValue() );
  }


  /** 
   * <em>agency_exec_notice_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAgencyExecNoticeNumberIsNull()
  {
    return agency_exec_notice_number == null;
  }


  /** 
   * <em>agency_exec_notice_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyExecNoticeNumberIsSet() {
    return agency_exec_notice_number_is_set;
  }


  /** 
   * <em>agency_exec_notice_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyExecNoticeNumberIsModified() {
    return agency_exec_notice_number_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new VirtualServerChangePK(virtual_server_number_market, change_req_res_div, notice_type, front_order_exchange_code);
  }


  /** 
   * <em>virtual_server_number_market</em>カラムの値を設定します。 
   *
   * @@param p_virtualServerNumberMarket <em>virtual_server_number_market</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setVirtualServerNumberMarket( String p_virtualServerNumberMarket )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    virtual_server_number_market = p_virtualServerNumberMarket;
    virtual_server_number_market_is_set = true;
    virtual_server_number_market_is_modified = true;
  }


  /** 
   * <em>change_req_res_div</em>カラムの値を設定します。 
   *
   * @@param p_changeReqResDiv <em>change_req_res_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setChangeReqResDiv( String p_changeReqResDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_req_res_div = p_changeReqResDiv;
    change_req_res_div_is_set = true;
    change_req_res_div_is_modified = true;
  }


  /** 
   * <em>notice_type</em>カラムの値を設定します。 
   *
   * @@param p_noticeType <em>notice_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setNoticeType( String p_noticeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    notice_type = p_noticeType;
    notice_type_is_set = true;
    notice_type_is_modified = true;
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
   * <em>front_order_exchange_code</em>カラムの値を設定します。 
   *
   * @@param p_frontOrderExchangeCode <em>front_order_exchange_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFrontOrderExchangeCode( String p_frontOrderExchangeCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    front_order_exchange_code = p_frontOrderExchangeCode;
    front_order_exchange_code_is_set = true;
    front_order_exchange_code_is_modified = true;
  }


  /** 
   * <em>front_order_system_code</em>カラムの値を設定します。 
   *
   * @@param p_frontOrderSystemCode <em>front_order_system_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFrontOrderSystemCode( String p_frontOrderSystemCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    front_order_system_code = p_frontOrderSystemCode;
    front_order_system_code_is_set = true;
    front_order_system_code_is_modified = true;
  }


  /** 
   * <em>front_order_trade_code</em>カラムの値を設定します。 
   *
   * @@param p_frontOrderTradeCode <em>front_order_trade_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFrontOrderTradeCode( String p_frontOrderTradeCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    front_order_trade_code = p_frontOrderTradeCode;
    front_order_trade_code_is_set = true;
    front_order_trade_code_is_modified = true;
  }


  /** 
   * <em>corp_code</em>カラムの値を設定します。 
   *
   * @@param p_corpCode <em>corp_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setCorpCode( String p_corpCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    corp_code = p_corpCode;
    corp_code_is_set = true;
    corp_code_is_modified = true;
  }


  /** 
   * <em>cancel_div</em>カラムの値を設定します。 
   *
   * @@param p_cancelDiv <em>cancel_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCancelDiv( String p_cancelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_div = p_cancelDiv;
    cancel_div_is_set = true;
    cancel_div_is_modified = true;
  }


  /** 
   * <em>virtual_server_number_sonar</em>カラムの値を設定します。 
   *
   * @@param p_virtualServerNumberSonar <em>virtual_server_number_sonar</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setVirtualServerNumberSonar( String p_virtualServerNumberSonar )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    virtual_server_number_sonar = p_virtualServerNumberSonar;
    virtual_server_number_sonar_is_set = true;
    virtual_server_number_sonar_is_modified = true;
  }


  /** 
   * <em>client_number</em>カラムの値を設定します。 
   *
   * @@param p_clientNumber <em>client_number</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setClientNumber( String p_clientNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    client_number = p_clientNumber;
    client_number_is_set = true;
    client_number_is_modified = true;
  }


  /** 
   * <em>notice_file_number</em>カラムの値を設定します。 
   *
   * @@param p_noticeFileNumber <em>notice_file_number</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setNoticeFileNumber( String p_noticeFileNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    notice_file_number = p_noticeFileNumber;
    notice_file_number_is_set = true;
    notice_file_number_is_modified = true;
  }


  /** 
   * <em>resend_notice_number_from</em>カラムの値を設定します。 
   *
   * @@param p_resendNoticeNumberFrom <em>resend_notice_number_from</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setResendNoticeNumberFrom( long p_resendNoticeNumberFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    resend_notice_number_from = new Long(p_resendNoticeNumberFrom);
    resend_notice_number_from_is_set = true;
    resend_notice_number_from_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>resend_notice_number_from</em>カラムに値を設定します。 
   */
  public final void setResendNoticeNumberFrom( Long p_resendNoticeNumberFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    resend_notice_number_from = p_resendNoticeNumberFrom;
    resend_notice_number_from_is_set = true;
    resend_notice_number_from_is_modified = true;
  }


  /** 
   * <em>error_message</em>カラムの値を設定します。 
   *
   * @@param p_errorMessage <em>error_message</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setErrorMessage( String p_errorMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_message = p_errorMessage;
    error_message_is_set = true;
    error_message_is_modified = true;
  }


  /** 
   * <em>action_count</em>カラムの値を設定します。 
   *
   * @@param p_actionCount <em>action_count</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setActionCount( int p_actionCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    action_count = new Integer(p_actionCount);
    action_count_is_set = true;
    action_count_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>action_count</em>カラムに値を設定します。 
   */
  public final void setActionCount( Integer p_actionCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    action_count = p_actionCount;
    action_count_is_set = true;
    action_count_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
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
   * <em>product_type</em>カラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>agency_accept_notice_number</em>カラムの値を設定します。 
   *
   * @@param p_agencyAcceptNoticeNumber <em>agency_accept_notice_number</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setAgencyAcceptNoticeNumber( long p_agencyAcceptNoticeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_accept_notice_number = new Long(p_agencyAcceptNoticeNumber);
    agency_accept_notice_number_is_set = true;
    agency_accept_notice_number_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>agency_accept_notice_number</em>カラムに値を設定します。 
   */
  public final void setAgencyAcceptNoticeNumber( Long p_agencyAcceptNoticeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    agency_accept_notice_number = p_agencyAcceptNoticeNumber;
    agency_accept_notice_number_is_set = true;
    agency_accept_notice_number_is_modified = true;
  }


  /** 
   * <em>agency_exec_notice_number</em>カラムの値を設定します。 
   *
   * @@param p_agencyExecNoticeNumber <em>agency_exec_notice_number</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setAgencyExecNoticeNumber( long p_agencyExecNoticeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_exec_notice_number = new Long(p_agencyExecNoticeNumber);
    agency_exec_notice_number_is_set = true;
    agency_exec_notice_number_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>agency_exec_notice_number</em>カラムに値を設定します。 
   */
  public final void setAgencyExecNoticeNumber( Long p_agencyExecNoticeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    agency_exec_notice_number = p_agencyExecNoticeNumber;
    agency_exec_notice_number_is_set = true;
    agency_exec_notice_number_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("action_count") ) {
                    return this.action_count;
                }
                else if ( name.equals("agency_accept_notice_number") ) {
                    return this.agency_accept_notice_number;
                }
                else if ( name.equals("agency_exec_notice_number") ) {
                    return this.agency_exec_notice_number;
                }
                break;
            case 'c':
                if ( name.equals("change_req_res_div") ) {
                    return this.change_req_res_div;
                }
                else if ( name.equals("corp_code") ) {
                    return this.corp_code;
                }
                else if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                else if ( name.equals("client_number") ) {
                    return this.client_number;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("error_message") ) {
                    return this.error_message;
                }
                break;
            case 'f':
                if ( name.equals("front_order_exchange_code") ) {
                    return this.front_order_exchange_code;
                }
                else if ( name.equals("front_order_system_code") ) {
                    return this.front_order_system_code;
                }
                else if ( name.equals("front_order_trade_code") ) {
                    return this.front_order_trade_code;
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
                if ( name.equals("notice_type") ) {
                    return this.notice_type;
                }
                else if ( name.equals("notice_file_number") ) {
                    return this.notice_file_number;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 'r':
                if ( name.equals("resend_notice_number_from") ) {
                    return this.resend_notice_number_from;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 'v':
                if ( name.equals("virtual_server_number_market") ) {
                    return this.virtual_server_number_market;
                }
                else if ( name.equals("virtual_server_number_sonar") ) {
                    return this.virtual_server_number_sonar;
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
                if ( name.equals("action_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'action_count' must be Integer: '"+value+"' is not." );
                    this.action_count = (Integer) value;
                    if (this.action_count_is_set)
                        this.action_count_is_modified = true;
                    this.action_count_is_set = true;
                    return;
                }
                else if ( name.equals("agency_accept_notice_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'agency_accept_notice_number' must be Long: '"+value+"' is not." );
                    this.agency_accept_notice_number = (Long) value;
                    if (this.agency_accept_notice_number_is_set)
                        this.agency_accept_notice_number_is_modified = true;
                    this.agency_accept_notice_number_is_set = true;
                    return;
                }
                else if ( name.equals("agency_exec_notice_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'agency_exec_notice_number' must be Long: '"+value+"' is not." );
                    this.agency_exec_notice_number = (Long) value;
                    if (this.agency_exec_notice_number_is_set)
                        this.agency_exec_notice_number_is_modified = true;
                    this.agency_exec_notice_number_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("change_req_res_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_req_res_div' must be String: '"+value+"' is not." );
                    this.change_req_res_div = (String) value;
                    if (this.change_req_res_div_is_set)
                        this.change_req_res_div_is_modified = true;
                    this.change_req_res_div_is_set = true;
                    return;
                }
                else if ( name.equals("corp_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'corp_code' must be String: '"+value+"' is not." );
                    this.corp_code = (String) value;
                    if (this.corp_code_is_set)
                        this.corp_code_is_modified = true;
                    this.corp_code_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_div' must be String: '"+value+"' is not." );
                    this.cancel_div = (String) value;
                    if (this.cancel_div_is_set)
                        this.cancel_div_is_modified = true;
                    this.cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("client_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'client_number' must be String: '"+value+"' is not." );
                    this.client_number = (String) value;
                    if (this.client_number_is_set)
                        this.client_number_is_modified = true;
                    this.client_number_is_set = true;
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
            case 'e':
                if ( name.equals("error_message") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_message' must be String: '"+value+"' is not." );
                    this.error_message = (String) value;
                    if (this.error_message_is_set)
                        this.error_message_is_modified = true;
                    this.error_message_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("front_order_exchange_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_exchange_code' must be String: '"+value+"' is not." );
                    this.front_order_exchange_code = (String) value;
                    if (this.front_order_exchange_code_is_set)
                        this.front_order_exchange_code_is_modified = true;
                    this.front_order_exchange_code_is_set = true;
                    return;
                }
                else if ( name.equals("front_order_system_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_system_code' must be String: '"+value+"' is not." );
                    this.front_order_system_code = (String) value;
                    if (this.front_order_system_code_is_set)
                        this.front_order_system_code_is_modified = true;
                    this.front_order_system_code_is_set = true;
                    return;
                }
                else if ( name.equals("front_order_trade_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'front_order_trade_code' must be String: '"+value+"' is not." );
                    this.front_order_trade_code = (String) value;
                    if (this.front_order_trade_code_is_set)
                        this.front_order_trade_code_is_modified = true;
                    this.front_order_trade_code_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( value != null )
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
                if ( name.equals("notice_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'notice_type' must be String: '"+value+"' is not." );
                    this.notice_type = (String) value;
                    if (this.notice_type_is_set)
                        this.notice_type_is_modified = true;
                    this.notice_type_is_set = true;
                    return;
                }
                else if ( name.equals("notice_file_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'notice_file_number' must be String: '"+value+"' is not." );
                    this.notice_file_number = (String) value;
                    if (this.notice_file_number_is_set)
                        this.notice_file_number_is_modified = true;
                    this.notice_file_number_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("resend_notice_number_from") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'resend_notice_number_from' must be Long: '"+value+"' is not." );
                    this.resend_notice_number_from = (Long) value;
                    if (this.resend_notice_number_from_is_set)
                        this.resend_notice_number_from_is_modified = true;
                    this.resend_notice_number_from_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("virtual_server_number_market") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'virtual_server_number_market' must be String: '"+value+"' is not." );
                    this.virtual_server_number_market = (String) value;
                    if (this.virtual_server_number_market_is_set)
                        this.virtual_server_number_market_is_modified = true;
                    this.virtual_server_number_market_is_set = true;
                    return;
                }
                else if ( name.equals("virtual_server_number_sonar") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'virtual_server_number_sonar' must be String: '"+value+"' is not." );
                    this.virtual_server_number_sonar = (String) value;
                    if (this.virtual_server_number_sonar_is_set)
                        this.virtual_server_number_sonar_is_modified = true;
                    this.virtual_server_number_sonar_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
