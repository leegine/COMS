head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.43.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankSettleResultResponseParams.java;


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
 * bank_settle_result_response�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link BankSettleResultResponseRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link BankSettleResultResponseParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link BankSettleResultResponseParams}��{@@link BankSettleResultResponseRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankSettleResultResponsePK 
 * @@see BankSettleResultResponseRow 
 */
public class BankSettleResultResponseParams extends Params implements BankSettleResultResponseRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "bank_settle_result_response";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = BankSettleResultResponseRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return BankSettleResultResponseRow.TYPE;
  }


  /** 
   * <em>rowid</em>�J�����̒l 
   */
  public  String  rowid;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  String  created_timestamp;

  /** 
   * <em>protocol_version</em>�J�����̒l 
   */
  public  String  protocol_version;

  /** 
   * <em>linked_1</em>�J�����̒l 
   */
  public  String  linked_1;

  /** 
   * <em>shop_id</em>�J�����̒l 
   */
  public  String  shop_id;

  /** 
   * <em>order_date_time</em>�J�����̒l 
   */
  public  String  order_date_time;

  /** 
   * <em>center_pay_id</em>�J�����̒l 
   */
  public  String  center_pay_id;

  /** 
   * <em>comondebi_sales_slip</em>�J�����̒l 
   */
  public  String  comondebi_sales_slip;

  /** 
   * <em>comondebi_auth_date</em>�J�����̒l 
   */
  public  String  comondebi_auth_date;

  /** 
   * <em>pay_status</em>�J�����̒l 
   */
  public  String  pay_status;

  /** 
   * <em>comondebi_authres_code</em>�J�����̒l 
   */
  public  String  comondebi_authres_code;

  /** 
   * <em>comondebi_capture_date</em>�J�����̒l 
   */
  public  String  comondebi_capture_date;

  /** 
   * <em>amount</em>�J�����̒l 
   */
  public  String  amount;

  /** 
   * <em>pay_scheme_id</em>�J�����̒l 
   */
  public  String  pay_scheme_id;

  /** 
   * <em>access_key</em>�J�����̒l 
   */
  public  String  access_key;

  /** 
   * <em>description</em>�J�����̒l 
   */
  public  String  description;

  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean protocol_version_is_set = false;
  private boolean protocol_version_is_modified = false;
  private boolean linked_1_is_set = false;
  private boolean linked_1_is_modified = false;
  private boolean shop_id_is_set = false;
  private boolean shop_id_is_modified = false;
  private boolean order_date_time_is_set = false;
  private boolean order_date_time_is_modified = false;
  private boolean center_pay_id_is_set = false;
  private boolean center_pay_id_is_modified = false;
  private boolean comondebi_sales_slip_is_set = false;
  private boolean comondebi_sales_slip_is_modified = false;
  private boolean comondebi_auth_date_is_set = false;
  private boolean comondebi_auth_date_is_modified = false;
  private boolean pay_status_is_set = false;
  private boolean pay_status_is_modified = false;
  private boolean comondebi_authres_code_is_set = false;
  private boolean comondebi_authres_code_is_modified = false;
  private boolean comondebi_capture_date_is_set = false;
  private boolean comondebi_capture_date_is_modified = false;
  private boolean amount_is_set = false;
  private boolean amount_is_modified = false;
  private boolean pay_scheme_id_is_set = false;
  private boolean pay_scheme_id_is_modified = false;
  private boolean access_key_is_set = false;
  private boolean access_key_is_modified = false;
  private boolean description_is_set = false;
  private boolean description_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "created_timestamp=" +created_timestamp
      + "," + "protocol_version=" +protocol_version
      + "," + "linked_1=" +linked_1
      + "," + "shop_id=" +shop_id
      + "," + "order_date_time=" +order_date_time
      + "," + "center_pay_id=" +center_pay_id
      + "," + "comondebi_sales_slip=" +comondebi_sales_slip
      + "," + "comondebi_auth_date=" +comondebi_auth_date
      + "," + "pay_status=" +pay_status
      + "," + "comondebi_authres_code=" +comondebi_authres_code
      + "," + "comondebi_capture_date=" +comondebi_capture_date
      + "," + "amount=" +amount
      + "," + "pay_scheme_id=" +pay_scheme_id
      + "," + "access_key=" +access_key
      + "," + "description=" +description
      + "}";
  }


  /** 
   * �l�����ݒ��BankSettleResultResponseParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public BankSettleResultResponseParams() {
    rowid_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���BankSettleResultResponseRow�I�u�W�F�N�g�̒l�𗘗p����BankSettleResultResponseParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����BankSettleResultResponseRow�I�u�W�F�N�g 
   */
  public BankSettleResultResponseParams( BankSettleResultResponseRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    protocol_version = p_row.getProtocolVersion();
    protocol_version_is_set = p_row.getProtocolVersionIsSet();
    protocol_version_is_modified = p_row.getProtocolVersionIsModified();
    linked_1 = p_row.getLinked1();
    linked_1_is_set = p_row.getLinked1IsSet();
    linked_1_is_modified = p_row.getLinked1IsModified();
    shop_id = p_row.getShopId();
    shop_id_is_set = p_row.getShopIdIsSet();
    shop_id_is_modified = p_row.getShopIdIsModified();
    order_date_time = p_row.getOrderDateTime();
    order_date_time_is_set = p_row.getOrderDateTimeIsSet();
    order_date_time_is_modified = p_row.getOrderDateTimeIsModified();
    center_pay_id = p_row.getCenterPayId();
    center_pay_id_is_set = p_row.getCenterPayIdIsSet();
    center_pay_id_is_modified = p_row.getCenterPayIdIsModified();
    comondebi_sales_slip = p_row.getComondebiSalesSlip();
    comondebi_sales_slip_is_set = p_row.getComondebiSalesSlipIsSet();
    comondebi_sales_slip_is_modified = p_row.getComondebiSalesSlipIsModified();
    comondebi_auth_date = p_row.getComondebiAuthDate();
    comondebi_auth_date_is_set = p_row.getComondebiAuthDateIsSet();
    comondebi_auth_date_is_modified = p_row.getComondebiAuthDateIsModified();
    pay_status = p_row.getPayStatus();
    pay_status_is_set = p_row.getPayStatusIsSet();
    pay_status_is_modified = p_row.getPayStatusIsModified();
    comondebi_authres_code = p_row.getComondebiAuthresCode();
    comondebi_authres_code_is_set = p_row.getComondebiAuthresCodeIsSet();
    comondebi_authres_code_is_modified = p_row.getComondebiAuthresCodeIsModified();
    comondebi_capture_date = p_row.getComondebiCaptureDate();
    comondebi_capture_date_is_set = p_row.getComondebiCaptureDateIsSet();
    comondebi_capture_date_is_modified = p_row.getComondebiCaptureDateIsModified();
    amount = p_row.getAmount();
    amount_is_set = p_row.getAmountIsSet();
    amount_is_modified = p_row.getAmountIsModified();
    pay_scheme_id = p_row.getPaySchemeId();
    pay_scheme_id_is_set = p_row.getPaySchemeIdIsSet();
    pay_scheme_id_is_modified = p_row.getPaySchemeIdIsModified();
    access_key = p_row.getAccessKey();
    access_key_is_set = p_row.getAccessKeyIsSet();
    access_key_is_modified = p_row.getAccessKeyIsModified();
    description = p_row.getDescription();
    description_is_set = p_row.getDescriptionIsSet();
    description_is_modified = p_row.getDescriptionIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      protocol_version_is_set = true;
      protocol_version_is_modified = true;
      linked_1_is_set = true;
      linked_1_is_modified = true;
      shop_id_is_set = true;
      shop_id_is_modified = true;
      order_date_time_is_set = true;
      order_date_time_is_modified = true;
      center_pay_id_is_set = true;
      center_pay_id_is_modified = true;
      comondebi_sales_slip_is_set = true;
      comondebi_sales_slip_is_modified = true;
      comondebi_auth_date_is_set = true;
      comondebi_auth_date_is_modified = true;
      pay_status_is_set = true;
      pay_status_is_modified = true;
      comondebi_authres_code_is_set = true;
      comondebi_authres_code_is_modified = true;
      comondebi_capture_date_is_set = true;
      comondebi_capture_date_is_modified = true;
      amount_is_set = true;
      amount_is_modified = true;
      pay_scheme_id_is_set = true;
      pay_scheme_id_is_modified = true;
      access_key_is_set = true;
      access_key_is_modified = true;
      description_is_set = true;
      description_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BankSettleResultResponseRow ) )
       return false;
    return fieldsEqual( (BankSettleResultResponseRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�BankSettleResultResponseRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( BankSettleResultResponseRow row )
  {
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( protocol_version == null ) {
      if ( row.getProtocolVersion() != null )
        return false;
    } else if ( !protocol_version.equals( row.getProtocolVersion() ) ) {
        return false;
    }
    if ( linked_1 == null ) {
      if ( row.getLinked1() != null )
        return false;
    } else if ( !linked_1.equals( row.getLinked1() ) ) {
        return false;
    }
    if ( shop_id == null ) {
      if ( row.getShopId() != null )
        return false;
    } else if ( !shop_id.equals( row.getShopId() ) ) {
        return false;
    }
    if ( order_date_time == null ) {
      if ( row.getOrderDateTime() != null )
        return false;
    } else if ( !order_date_time.equals( row.getOrderDateTime() ) ) {
        return false;
    }
    if ( center_pay_id == null ) {
      if ( row.getCenterPayId() != null )
        return false;
    } else if ( !center_pay_id.equals( row.getCenterPayId() ) ) {
        return false;
    }
    if ( comondebi_sales_slip == null ) {
      if ( row.getComondebiSalesSlip() != null )
        return false;
    } else if ( !comondebi_sales_slip.equals( row.getComondebiSalesSlip() ) ) {
        return false;
    }
    if ( comondebi_auth_date == null ) {
      if ( row.getComondebiAuthDate() != null )
        return false;
    } else if ( !comondebi_auth_date.equals( row.getComondebiAuthDate() ) ) {
        return false;
    }
    if ( pay_status == null ) {
      if ( row.getPayStatus() != null )
        return false;
    } else if ( !pay_status.equals( row.getPayStatus() ) ) {
        return false;
    }
    if ( comondebi_authres_code == null ) {
      if ( row.getComondebiAuthresCode() != null )
        return false;
    } else if ( !comondebi_authres_code.equals( row.getComondebiAuthresCode() ) ) {
        return false;
    }
    if ( comondebi_capture_date == null ) {
      if ( row.getComondebiCaptureDate() != null )
        return false;
    } else if ( !comondebi_capture_date.equals( row.getComondebiCaptureDate() ) ) {
        return false;
    }
    if ( amount == null ) {
      if ( row.getAmount() != null )
        return false;
    } else if ( !amount.equals( row.getAmount() ) ) {
        return false;
    }
    if ( pay_scheme_id == null ) {
      if ( row.getPaySchemeId() != null )
        return false;
    } else if ( !pay_scheme_id.equals( row.getPaySchemeId() ) ) {
        return false;
    }
    if ( access_key == null ) {
      if ( row.getAccessKey() != null )
        return false;
    } else if ( !access_key.equals( row.getAccessKey() ) ) {
        return false;
    }
    if ( description == null ) {
      if ( row.getDescription() != null )
        return false;
    } else if ( !description.equals( row.getDescription() ) ) {
        return false;
    }
    if ( rowid == null ) {
      if ( row.getRowid() != null )
        return false;
    } else if ( !rowid.equals( row.getRowid() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (protocol_version!=null? protocol_version.hashCode(): 0) 
        + (linked_1!=null? linked_1.hashCode(): 0) 
        + (shop_id!=null? shop_id.hashCode(): 0) 
        + (order_date_time!=null? order_date_time.hashCode(): 0) 
        + (center_pay_id!=null? center_pay_id.hashCode(): 0) 
        + (comondebi_sales_slip!=null? comondebi_sales_slip.hashCode(): 0) 
        + (comondebi_auth_date!=null? comondebi_auth_date.hashCode(): 0) 
        + (pay_status!=null? pay_status.hashCode(): 0) 
        + (comondebi_authres_code!=null? comondebi_authres_code.hashCode(): 0) 
        + (comondebi_capture_date!=null? comondebi_capture_date.hashCode(): 0) 
        + (amount!=null? amount.hashCode(): 0) 
        + (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (access_key!=null? access_key.hashCode(): 0) 
        + (description!=null? description.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !protocol_version_is_set )
			throw new IllegalArgumentException("non-nullable field 'protocol_version' must be set before inserting.");
		if ( !linked_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'linked_1' must be set before inserting.");
		if ( !shop_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'shop_id' must be set before inserting.");
		if ( !order_date_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_date_time' must be set before inserting.");
		if ( !center_pay_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'center_pay_id' must be set before inserting.");
		if ( !pay_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'pay_status' must be set before inserting.");
		if ( !pay_scheme_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'pay_scheme_id' must be set before inserting.");
		if ( !access_key_is_set )
			throw new IllegalArgumentException("non-nullable field 'access_key' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("created_timestamp",created_timestamp);
		map.put("protocol_version",protocol_version);
		map.put("linked_1",linked_1);
		map.put("shop_id",shop_id);
		map.put("order_date_time",order_date_time);
		map.put("center_pay_id",center_pay_id);
		if ( comondebi_sales_slip != null )
			map.put("comondebi_sales_slip",comondebi_sales_slip);
		if ( comondebi_auth_date != null )
			map.put("comondebi_auth_date",comondebi_auth_date);
		map.put("pay_status",pay_status);
		if ( comondebi_authres_code != null )
			map.put("comondebi_authres_code",comondebi_authres_code);
		if ( comondebi_capture_date != null )
			map.put("comondebi_capture_date",comondebi_capture_date);
		if ( amount != null )
			map.put("amount",amount);
		map.put("pay_scheme_id",pay_scheme_id);
		map.put("access_key",access_key);
		if ( description != null )
			map.put("description",description);
		map.put("rowid",rowid);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( protocol_version_is_modified )
			map.put("protocol_version",protocol_version);
		if ( linked_1_is_modified )
			map.put("linked_1",linked_1);
		if ( shop_id_is_modified )
			map.put("shop_id",shop_id);
		if ( order_date_time_is_modified )
			map.put("order_date_time",order_date_time);
		if ( center_pay_id_is_modified )
			map.put("center_pay_id",center_pay_id);
		if ( comondebi_sales_slip_is_modified )
			map.put("comondebi_sales_slip",comondebi_sales_slip);
		if ( comondebi_auth_date_is_modified )
			map.put("comondebi_auth_date",comondebi_auth_date);
		if ( pay_status_is_modified )
			map.put("pay_status",pay_status);
		if ( comondebi_authres_code_is_modified )
			map.put("comondebi_authres_code",comondebi_authres_code);
		if ( comondebi_capture_date_is_modified )
			map.put("comondebi_capture_date",comondebi_capture_date);
		if ( amount_is_modified )
			map.put("amount",amount);
		if ( pay_scheme_id_is_modified )
			map.put("pay_scheme_id",pay_scheme_id);
		if ( access_key_is_modified )
			map.put("access_key",access_key);
		if ( description_is_modified )
			map.put("description",description);
		if (map.size() == 0) {
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( protocol_version_is_set )
				map.put("protocol_version",protocol_version);
			if ( linked_1_is_set )
				map.put("linked_1",linked_1);
			if ( shop_id_is_set )
				map.put("shop_id",shop_id);
			if ( order_date_time_is_set )
				map.put("order_date_time",order_date_time);
			if ( center_pay_id_is_set )
				map.put("center_pay_id",center_pay_id);
			map.put("comondebi_sales_slip",comondebi_sales_slip);
			map.put("comondebi_auth_date",comondebi_auth_date);
			if ( pay_status_is_set )
				map.put("pay_status",pay_status);
			map.put("comondebi_authres_code",comondebi_authres_code);
			map.put("comondebi_capture_date",comondebi_capture_date);
			map.put("amount",amount);
			if ( pay_scheme_id_is_set )
				map.put("pay_scheme_id",pay_scheme_id);
			if ( access_key_is_set )
				map.put("access_key",access_key);
			map.put("description",description);
		}
		return map;
	}


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>protocol_version</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProtocolVersion()
  {
    return protocol_version;
  }


  /** 
   * <em>protocol_version</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProtocolVersionIsSet() {
    return protocol_version_is_set;
  }


  /** 
   * <em>protocol_version</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProtocolVersionIsModified() {
    return protocol_version_is_modified;
  }


  /** 
   * <em>linked_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLinked1()
  {
    return linked_1;
  }


  /** 
   * <em>linked_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLinked1IsSet() {
    return linked_1_is_set;
  }


  /** 
   * <em>linked_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLinked1IsModified() {
    return linked_1_is_modified;
  }


  /** 
   * <em>shop_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getShopId()
  {
    return shop_id;
  }


  /** 
   * <em>shop_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getShopIdIsSet() {
    return shop_id_is_set;
  }


  /** 
   * <em>shop_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getShopIdIsModified() {
    return shop_id_is_modified;
  }


  /** 
   * <em>order_date_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderDateTime()
  {
    return order_date_time;
  }


  /** 
   * <em>order_date_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderDateTimeIsSet() {
    return order_date_time_is_set;
  }


  /** 
   * <em>order_date_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderDateTimeIsModified() {
    return order_date_time_is_modified;
  }


  /** 
   * <em>center_pay_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCenterPayId()
  {
    return center_pay_id;
  }


  /** 
   * <em>center_pay_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCenterPayIdIsSet() {
    return center_pay_id_is_set;
  }


  /** 
   * <em>center_pay_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCenterPayIdIsModified() {
    return center_pay_id_is_modified;
  }


  /** 
   * <em>comondebi_sales_slip</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getComondebiSalesSlip()
  {
    return comondebi_sales_slip;
  }


  /** 
   * <em>comondebi_sales_slip</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getComondebiSalesSlipIsSet() {
    return comondebi_sales_slip_is_set;
  }


  /** 
   * <em>comondebi_sales_slip</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getComondebiSalesSlipIsModified() {
    return comondebi_sales_slip_is_modified;
  }


  /** 
   * <em>comondebi_auth_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getComondebiAuthDate()
  {
    return comondebi_auth_date;
  }


  /** 
   * <em>comondebi_auth_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getComondebiAuthDateIsSet() {
    return comondebi_auth_date_is_set;
  }


  /** 
   * <em>comondebi_auth_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getComondebiAuthDateIsModified() {
    return comondebi_auth_date_is_modified;
  }


  /** 
   * <em>pay_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPayStatus()
  {
    return pay_status;
  }


  /** 
   * <em>pay_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPayStatusIsSet() {
    return pay_status_is_set;
  }


  /** 
   * <em>pay_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPayStatusIsModified() {
    return pay_status_is_modified;
  }


  /** 
   * <em>comondebi_authres_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getComondebiAuthresCode()
  {
    return comondebi_authres_code;
  }


  /** 
   * <em>comondebi_authres_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getComondebiAuthresCodeIsSet() {
    return comondebi_authres_code_is_set;
  }


  /** 
   * <em>comondebi_authres_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getComondebiAuthresCodeIsModified() {
    return comondebi_authres_code_is_modified;
  }


  /** 
   * <em>comondebi_capture_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getComondebiCaptureDate()
  {
    return comondebi_capture_date;
  }


  /** 
   * <em>comondebi_capture_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getComondebiCaptureDateIsSet() {
    return comondebi_capture_date_is_set;
  }


  /** 
   * <em>comondebi_capture_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getComondebiCaptureDateIsModified() {
    return comondebi_capture_date_is_modified;
  }


  /** 
   * <em>amount</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAmount()
  {
    return amount;
  }


  /** 
   * <em>amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountIsSet() {
    return amount_is_set;
  }


  /** 
   * <em>amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAmountIsModified() {
    return amount_is_modified;
  }


  /** 
   * <em>pay_scheme_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPaySchemeId()
  {
    return pay_scheme_id;
  }


  /** 
   * <em>pay_scheme_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaySchemeIdIsSet() {
    return pay_scheme_id_is_set;
  }


  /** 
   * <em>pay_scheme_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPaySchemeIdIsModified() {
    return pay_scheme_id_is_modified;
  }


  /** 
   * <em>access_key</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccessKey()
  {
    return access_key;
  }


  /** 
   * <em>access_key</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccessKeyIsSet() {
    return access_key_is_set;
  }


  /** 
   * <em>access_key</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccessKeyIsModified() {
    return access_key_is_modified;
  }


  /** 
   * <em>description</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDescription()
  {
    return description;
  }


  /** 
   * <em>description</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDescriptionIsSet() {
    return description_is_set;
  }


  /** 
   * <em>description</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDescriptionIsModified() {
    return description_is_modified;
  }


  /** 
   * <em>rowid</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRowid()
  {
    return rowid;
  }


  /** 
   * <em>rowid</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRowidIsSet() {
    return rowid_is_set;
  }


  /** 
   * <em>rowid</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRowidIsModified() {
    return rowid_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BankSettleResultResponsePK(rowid);
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷14���ȉ���String�l 
   */
  public final void setCreatedTimestamp( String p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>protocol_version</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_protocolVersion <em>protocol_version</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setProtocolVersion( String p_protocolVersion )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    protocol_version = p_protocolVersion;
    protocol_version_is_set = true;
    protocol_version_is_modified = true;
  }


  /** 
   * <em>linked_1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_linked1 <em>linked_1</em>�J�����̒l������킷80���ȉ���String�l 
   */
  public final void setLinked1( String p_linked1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    linked_1 = p_linked1;
    linked_1_is_set = true;
    linked_1_is_modified = true;
  }


  /** 
   * <em>shop_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_shopId <em>shop_id</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setShopId( String p_shopId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    shop_id = p_shopId;
    shop_id_is_set = true;
    shop_id_is_modified = true;
  }


  /** 
   * <em>order_date_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderDateTime <em>order_date_time</em>�J�����̒l������킷14���ȉ���String�l 
   */
  public final void setOrderDateTime( String p_orderDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_date_time = p_orderDateTime;
    order_date_time_is_set = true;
    order_date_time_is_modified = true;
  }


  /** 
   * <em>center_pay_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_centerPayId <em>center_pay_id</em>�J�����̒l������킷15���ȉ���String�l 
   */
  public final void setCenterPayId( String p_centerPayId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    center_pay_id = p_centerPayId;
    center_pay_id_is_set = true;
    center_pay_id_is_modified = true;
  }


  /** 
   * <em>comondebi_sales_slip</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_comondebiSalesSlip <em>comondebi_sales_slip</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setComondebiSalesSlip( String p_comondebiSalesSlip )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comondebi_sales_slip = p_comondebiSalesSlip;
    comondebi_sales_slip_is_set = true;
    comondebi_sales_slip_is_modified = true;
  }


  /** 
   * <em>comondebi_auth_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_comondebiAuthDate <em>comondebi_auth_date</em>�J�����̒l������킷14���ȉ���String�l 
   */
  public final void setComondebiAuthDate( String p_comondebiAuthDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comondebi_auth_date = p_comondebiAuthDate;
    comondebi_auth_date_is_set = true;
    comondebi_auth_date_is_modified = true;
  }


  /** 
   * <em>pay_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_payStatus <em>pay_status</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setPayStatus( String p_payStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_status = p_payStatus;
    pay_status_is_set = true;
    pay_status_is_modified = true;
  }


  /** 
   * <em>comondebi_authres_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_comondebiAuthresCode <em>comondebi_authres_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public final void setComondebiAuthresCode( String p_comondebiAuthresCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comondebi_authres_code = p_comondebiAuthresCode;
    comondebi_authres_code_is_set = true;
    comondebi_authres_code_is_modified = true;
  }


  /** 
   * <em>comondebi_capture_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_comondebiCaptureDate <em>comondebi_capture_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setComondebiCaptureDate( String p_comondebiCaptureDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comondebi_capture_date = p_comondebiCaptureDate;
    comondebi_capture_date_is_set = true;
    comondebi_capture_date_is_modified = true;
  }


  /** 
   * <em>amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_amount <em>amount</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setAmount( String p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount = p_amount;
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * <em>pay_scheme_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_paySchemeId <em>pay_scheme_id</em>�J�����̒l������킷11���ȉ���String�l 
   */
  public final void setPaySchemeId( String p_paySchemeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_scheme_id = p_paySchemeId;
    pay_scheme_id_is_set = true;
    pay_scheme_id_is_modified = true;
  }


  /** 
   * <em>access_key</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accessKey <em>access_key</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setAccessKey( String p_accessKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    access_key = p_accessKey;
    access_key_is_set = true;
    access_key_is_modified = true;
  }


  /** 
   * <em>description</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_description <em>description</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setDescription( String p_description )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    description = p_description;
    description_is_set = true;
    description_is_modified = true;
  }


  /** 
   * <em>rowid</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_rowid <em>rowid</em>�J�����̒l������킷20���ȉ���String�l�ŁA���̐��x��20���܂�
   */
  public final void setRowid( String p_rowid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rowid = p_rowid;
    rowid_is_set = true;
    rowid_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("amount") ) {
                    return this.amount;
                }
                else if ( name.equals("access_key") ) {
                    return this.access_key;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("center_pay_id") ) {
                    return this.center_pay_id;
                }
                else if ( name.equals("comondebi_sales_slip") ) {
                    return this.comondebi_sales_slip;
                }
                else if ( name.equals("comondebi_auth_date") ) {
                    return this.comondebi_auth_date;
                }
                else if ( name.equals("comondebi_authres_code") ) {
                    return this.comondebi_authres_code;
                }
                else if ( name.equals("comondebi_capture_date") ) {
                    return this.comondebi_capture_date;
                }
                break;
            case 'd':
                if ( name.equals("description") ) {
                    return this.description;
                }
                break;
            case 'l':
                if ( name.equals("linked_1") ) {
                    return this.linked_1;
                }
                break;
            case 'o':
                if ( name.equals("order_date_time") ) {
                    return this.order_date_time;
                }
                break;
            case 'p':
                if ( name.equals("protocol_version") ) {
                    return this.protocol_version;
                }
                else if ( name.equals("pay_status") ) {
                    return this.pay_status;
                }
                else if ( name.equals("pay_scheme_id") ) {
                    return this.pay_scheme_id;
                }
                break;
            case 'r':
                if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("shop_id") ) {
                    return this.shop_id;
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
                if ( name.equals("amount") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'amount' must be String: '"+value+"' is not." );
                    this.amount = (String) value;
                    if (this.amount_is_set)
                        this.amount_is_modified = true;
                    this.amount_is_set = true;
                    return;
                }
                else if ( name.equals("access_key") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'access_key' must be String: '"+value+"' is not." );
                    this.access_key = (String) value;
                    if (this.access_key_is_set)
                        this.access_key_is_modified = true;
                    this.access_key_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be String: '"+value+"' is not." );
                    this.created_timestamp = (String) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("center_pay_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'center_pay_id' must be String: '"+value+"' is not." );
                    this.center_pay_id = (String) value;
                    if (this.center_pay_id_is_set)
                        this.center_pay_id_is_modified = true;
                    this.center_pay_id_is_set = true;
                    return;
                }
                else if ( name.equals("comondebi_sales_slip") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comondebi_sales_slip' must be String: '"+value+"' is not." );
                    this.comondebi_sales_slip = (String) value;
                    if (this.comondebi_sales_slip_is_set)
                        this.comondebi_sales_slip_is_modified = true;
                    this.comondebi_sales_slip_is_set = true;
                    return;
                }
                else if ( name.equals("comondebi_auth_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comondebi_auth_date' must be String: '"+value+"' is not." );
                    this.comondebi_auth_date = (String) value;
                    if (this.comondebi_auth_date_is_set)
                        this.comondebi_auth_date_is_modified = true;
                    this.comondebi_auth_date_is_set = true;
                    return;
                }
                else if ( name.equals("comondebi_authres_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comondebi_authres_code' must be String: '"+value+"' is not." );
                    this.comondebi_authres_code = (String) value;
                    if (this.comondebi_authres_code_is_set)
                        this.comondebi_authres_code_is_modified = true;
                    this.comondebi_authres_code_is_set = true;
                    return;
                }
                else if ( name.equals("comondebi_capture_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comondebi_capture_date' must be String: '"+value+"' is not." );
                    this.comondebi_capture_date = (String) value;
                    if (this.comondebi_capture_date_is_set)
                        this.comondebi_capture_date_is_modified = true;
                    this.comondebi_capture_date_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("description") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'description' must be String: '"+value+"' is not." );
                    this.description = (String) value;
                    if (this.description_is_set)
                        this.description_is_modified = true;
                    this.description_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("linked_1") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'linked_1' must be String: '"+value+"' is not." );
                    this.linked_1 = (String) value;
                    if (this.linked_1_is_set)
                        this.linked_1_is_modified = true;
                    this.linked_1_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_date_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_date_time' must be String: '"+value+"' is not." );
                    this.order_date_time = (String) value;
                    if (this.order_date_time_is_set)
                        this.order_date_time_is_modified = true;
                    this.order_date_time_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("protocol_version") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'protocol_version' must be String: '"+value+"' is not." );
                    this.protocol_version = (String) value;
                    if (this.protocol_version_is_set)
                        this.protocol_version_is_modified = true;
                    this.protocol_version_is_set = true;
                    return;
                }
                else if ( name.equals("pay_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_status' must be String: '"+value+"' is not." );
                    this.pay_status = (String) value;
                    if (this.pay_status_is_set)
                        this.pay_status_is_modified = true;
                    this.pay_status_is_set = true;
                    return;
                }
                else if ( name.equals("pay_scheme_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_scheme_id' must be String: '"+value+"' is not." );
                    this.pay_scheme_id = (String) value;
                    if (this.pay_scheme_id_is_set)
                        this.pay_scheme_id_is_modified = true;
                    this.pay_scheme_id_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("rowid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rowid' must be String: '"+value+"' is not." );
                    this.rowid = (String) value;
                    if (this.rowid_is_set)
                        this.rowid_is_modified = true;
                    this.rowid_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("shop_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'shop_id' must be String: '"+value+"' is not." );
                    this.shop_id = (String) value;
                    if (this.shop_id_is_set)
                        this.shop_id_is_modified = true;
                    this.shop_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
