head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MailProcTempParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mail_proc_temp�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MailProcTempRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MailProcTempParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MailProcTempParams}��{@@link MailProcTempRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MailProcTempPK 
 * @@see MailProcTempRow 
 */
public class MailProcTempParams extends Params implements MailProcTempRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mail_proc_temp";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MailProcTempRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MailProcTempRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>�J�����̒l 
   */
  public  String  branch_code;

  /** 
   * <em>sendmail_div</em>�J�����̒l 
   */
  public  String  sendmail_div;

  /** 
   * <em>discernment_id</em>�J�����̒l 
   */
  public  String  discernment_id;

  /** 
   * <em>account_code</em>�J�����̒l 
   */
  public  String  account_code;

  /** 
   * <em>mail_id</em>�J�����̒l 
   */
  public  long  mail_id;

  /** 
   * <em>date_1</em>�J�����̒l 
   */
  public  java.sql.Timestamp  date_1;

  /** 
   * <em>date_2</em>�J�����̒l 
   */
  public  java.sql.Timestamp  date_2;

  /** 
   * <em>date_3</em>�J�����̒l 
   */
  public  java.sql.Timestamp  date_3;

  /** 
   * <em>date_4</em>�J�����̒l 
   */
  public  java.sql.Timestamp  date_4;

  /** 
   * <em>quantity</em>�J�����̒l 
   */
  public  Double  quantity;

  /** 
   * <em>amount</em>�J�����̒l 
   */
  public  Double  amount;

  /** 
   * <em>order_id</em>�J�����̒l 
   */
  public  Long  order_id;

  /** 
   * <em>division</em>�J�����̒l 
   */
  public  String  division;

  /** 
   * <em>name_1</em>�J�����̒l 
   */
  public  String  name_1;

  /** 
   * <em>name_2</em>�J�����̒l 
   */
  public  String  name_2;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>send_process_date_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  send_process_date_time;

  /** 
   * <em>error_code</em>�J�����̒l 
   */
  public  String  error_code;

  /** 
   * <em>admin_mail_div</em>�J�����̒l 
   */
  public  String  admin_mail_div;

  /** 
   * <em>resend_status</em>�J�����̒l 
   */
  public  String  resend_status;

  /** 
   * <em>resend_process_date_time</em>�J�����̒l 
   */
  public  java.sql.Timestamp  resend_process_date_time;

  /** 
   * <em>email_address</em>�J�����̒l 
   */
  public  String  email_address;

  /** 
   * <em>send_email_address</em>�J�����̒l 
   */
  public  String  send_email_address;

  /** 
   * <em>subject</em>�J�����̒l 
   */
  public  String  subject;

  /** 
   * <em>mail_text</em>�J�����̒l 
   */
  public  String  mail_text;

  /** 
   * <em>delete_flag</em>�J�����̒l 
   */
  public  Integer  delete_flag;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean sendmail_div_is_set = false;
  private boolean sendmail_div_is_modified = false;
  private boolean discernment_id_is_set = false;
  private boolean discernment_id_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean mail_id_is_set = false;
  private boolean mail_id_is_modified = false;
  private boolean date_1_is_set = false;
  private boolean date_1_is_modified = false;
  private boolean date_2_is_set = false;
  private boolean date_2_is_modified = false;
  private boolean date_3_is_set = false;
  private boolean date_3_is_modified = false;
  private boolean date_4_is_set = false;
  private boolean date_4_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean amount_is_set = false;
  private boolean amount_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean division_is_set = false;
  private boolean division_is_modified = false;
  private boolean name_1_is_set = false;
  private boolean name_1_is_modified = false;
  private boolean name_2_is_set = false;
  private boolean name_2_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean send_process_date_time_is_set = false;
  private boolean send_process_date_time_is_modified = false;
  private boolean error_code_is_set = false;
  private boolean error_code_is_modified = false;
  private boolean admin_mail_div_is_set = false;
  private boolean admin_mail_div_is_modified = false;
  private boolean resend_status_is_set = false;
  private boolean resend_status_is_modified = false;
  private boolean resend_process_date_time_is_set = false;
  private boolean resend_process_date_time_is_modified = false;
  private boolean email_address_is_set = false;
  private boolean email_address_is_modified = false;
  private boolean send_email_address_is_set = false;
  private boolean send_email_address_is_modified = false;
  private boolean subject_is_set = false;
  private boolean subject_is_modified = false;
  private boolean mail_text_is_set = false;
  private boolean mail_text_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "sendmail_div=" + sendmail_div
      + "," + "discernment_id=" + discernment_id
      + "," + "account_code=" + account_code
      + "," + "mail_id=" + mail_id
      + "," + "date_1=" +date_1
      + "," + "date_2=" +date_2
      + "," + "date_3=" +date_3
      + "," + "date_4=" +date_4
      + "," + "quantity=" +quantity
      + "," + "amount=" +amount
      + "," + "order_id=" +order_id
      + "," + "division=" +division
      + "," + "name_1=" +name_1
      + "," + "name_2=" +name_2
      + "," + "status=" +status
      + "," + "send_process_date_time=" +send_process_date_time
      + "," + "error_code=" +error_code
      + "," + "admin_mail_div=" +admin_mail_div
      + "," + "resend_status=" +resend_status
      + "," + "resend_process_date_time=" +resend_process_date_time
      + "," + "email_address=" +email_address
      + "," + "send_email_address=" +send_email_address
      + "," + "subject=" +subject
      + "," + "mail_text=" +mail_text
      + "," + "delete_flag=" +delete_flag
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��MailProcTempParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MailProcTempParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    sendmail_div_is_modified = true;
    discernment_id_is_modified = true;
    account_code_is_modified = true;
    mail_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MailProcTempRow�I�u�W�F�N�g�̒l�𗘗p����MailProcTempParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MailProcTempRow�I�u�W�F�N�g 
   */
  public MailProcTempParams( MailProcTempRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    sendmail_div = p_row.getSendmailDiv();
    sendmail_div_is_set = p_row.getSendmailDivIsSet();
    sendmail_div_is_modified = p_row.getSendmailDivIsModified();
    discernment_id = p_row.getDiscernmentId();
    discernment_id_is_set = p_row.getDiscernmentIdIsSet();
    discernment_id_is_modified = p_row.getDiscernmentIdIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    mail_id = p_row.getMailId();
    mail_id_is_set = p_row.getMailIdIsSet();
    mail_id_is_modified = p_row.getMailIdIsModified();
    date_1 = p_row.getDate1();
    date_1_is_set = p_row.getDate1IsSet();
    date_1_is_modified = p_row.getDate1IsModified();
    date_2 = p_row.getDate2();
    date_2_is_set = p_row.getDate2IsSet();
    date_2_is_modified = p_row.getDate2IsModified();
    date_3 = p_row.getDate3();
    date_3_is_set = p_row.getDate3IsSet();
    date_3_is_modified = p_row.getDate3IsModified();
    date_4 = p_row.getDate4();
    date_4_is_set = p_row.getDate4IsSet();
    date_4_is_modified = p_row.getDate4IsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getAmountIsNull() )
      amount = new Double( p_row.getAmount() );
    amount_is_set = p_row.getAmountIsSet();
    amount_is_modified = p_row.getAmountIsModified();
    if ( !p_row.getOrderIdIsNull() )
      order_id = new Long( p_row.getOrderId() );
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    division = p_row.getDivision();
    division_is_set = p_row.getDivisionIsSet();
    division_is_modified = p_row.getDivisionIsModified();
    name_1 = p_row.getName1();
    name_1_is_set = p_row.getName1IsSet();
    name_1_is_modified = p_row.getName1IsModified();
    name_2 = p_row.getName2();
    name_2_is_set = p_row.getName2IsSet();
    name_2_is_modified = p_row.getName2IsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    send_process_date_time = p_row.getSendProcessDateTime();
    send_process_date_time_is_set = p_row.getSendProcessDateTimeIsSet();
    send_process_date_time_is_modified = p_row.getSendProcessDateTimeIsModified();
    error_code = p_row.getErrorCode();
    error_code_is_set = p_row.getErrorCodeIsSet();
    error_code_is_modified = p_row.getErrorCodeIsModified();
    admin_mail_div = p_row.getAdminMailDiv();
    admin_mail_div_is_set = p_row.getAdminMailDivIsSet();
    admin_mail_div_is_modified = p_row.getAdminMailDivIsModified();
    resend_status = p_row.getResendStatus();
    resend_status_is_set = p_row.getResendStatusIsSet();
    resend_status_is_modified = p_row.getResendStatusIsModified();
    resend_process_date_time = p_row.getResendProcessDateTime();
    resend_process_date_time_is_set = p_row.getResendProcessDateTimeIsSet();
    resend_process_date_time_is_modified = p_row.getResendProcessDateTimeIsModified();
    email_address = p_row.getEmailAddress();
    email_address_is_set = p_row.getEmailAddressIsSet();
    email_address_is_modified = p_row.getEmailAddressIsModified();
    send_email_address = p_row.getSendEmailAddress();
    send_email_address_is_set = p_row.getSendEmailAddressIsSet();
    send_email_address_is_modified = p_row.getSendEmailAddressIsModified();
    subject = p_row.getSubject();
    subject_is_set = p_row.getSubjectIsSet();
    subject_is_modified = p_row.getSubjectIsModified();
    mail_text = p_row.getMailText();
    mail_text_is_set = p_row.getMailTextIsSet();
    mail_text_is_modified = p_row.getMailTextIsModified();
    if ( !p_row.getDeleteFlagIsNull() )
      delete_flag = new Integer( p_row.getDeleteFlag() );
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
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
      date_1_is_set = true;
      date_1_is_modified = true;
      date_2_is_set = true;
      date_2_is_modified = true;
      date_3_is_set = true;
      date_3_is_modified = true;
      date_4_is_set = true;
      date_4_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      amount_is_set = true;
      amount_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      division_is_set = true;
      division_is_modified = true;
      name_1_is_set = true;
      name_1_is_modified = true;
      name_2_is_set = true;
      name_2_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      send_process_date_time_is_set = true;
      send_process_date_time_is_modified = true;
      error_code_is_set = true;
      error_code_is_modified = true;
      admin_mail_div_is_set = true;
      admin_mail_div_is_modified = true;
      resend_status_is_set = true;
      resend_status_is_modified = true;
      resend_process_date_time_is_set = true;
      resend_process_date_time_is_modified = true;
      email_address_is_set = true;
      email_address_is_modified = true;
      send_email_address_is_set = true;
      send_email_address_is_modified = true;
      subject_is_set = true;
      subject_is_modified = true;
      mail_text_is_set = true;
      mail_text_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MailProcTempRow ) )
       return false;
    return fieldsEqual( (MailProcTempRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MailProcTempRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MailProcTempRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( sendmail_div == null ) {
      if ( row.getSendmailDiv() != null )
        return false;
    } else if ( !sendmail_div.equals( row.getSendmailDiv() ) ) {
        return false;
    }
    if ( discernment_id == null ) {
      if ( row.getDiscernmentId() != null )
        return false;
    } else if ( !discernment_id.equals( row.getDiscernmentId() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( mail_id != row.getMailId() )
      return false;
    if ( date_1 == null ) {
      if ( row.getDate1() != null )
        return false;
    } else if ( !date_1.equals( row.getDate1() ) ) {
        return false;
    }
    if ( date_2 == null ) {
      if ( row.getDate2() != null )
        return false;
    } else if ( !date_2.equals( row.getDate2() ) ) {
        return false;
    }
    if ( date_3 == null ) {
      if ( row.getDate3() != null )
        return false;
    } else if ( !date_3.equals( row.getDate3() ) ) {
        return false;
    }
    if ( date_4 == null ) {
      if ( row.getDate4() != null )
        return false;
    } else if ( !date_4.equals( row.getDate4() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( amount == null ) {
      if ( !row.getAmountIsNull() )
        return false;
    } else if ( row.getAmountIsNull() || ( amount.doubleValue() != row.getAmount() ) ) {
        return false;
    }
    if ( order_id == null ) {
      if ( !row.getOrderIdIsNull() )
        return false;
    } else if ( row.getOrderIdIsNull() || ( order_id.longValue() != row.getOrderId() ) ) {
        return false;
    }
    if ( division == null ) {
      if ( row.getDivision() != null )
        return false;
    } else if ( !division.equals( row.getDivision() ) ) {
        return false;
    }
    if ( name_1 == null ) {
      if ( row.getName1() != null )
        return false;
    } else if ( !name_1.equals( row.getName1() ) ) {
        return false;
    }
    if ( name_2 == null ) {
      if ( row.getName2() != null )
        return false;
    } else if ( !name_2.equals( row.getName2() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( send_process_date_time == null ) {
      if ( row.getSendProcessDateTime() != null )
        return false;
    } else if ( !send_process_date_time.equals( row.getSendProcessDateTime() ) ) {
        return false;
    }
    if ( error_code == null ) {
      if ( row.getErrorCode() != null )
        return false;
    } else if ( !error_code.equals( row.getErrorCode() ) ) {
        return false;
    }
    if ( admin_mail_div == null ) {
      if ( row.getAdminMailDiv() != null )
        return false;
    } else if ( !admin_mail_div.equals( row.getAdminMailDiv() ) ) {
        return false;
    }
    if ( resend_status == null ) {
      if ( row.getResendStatus() != null )
        return false;
    } else if ( !resend_status.equals( row.getResendStatus() ) ) {
        return false;
    }
    if ( resend_process_date_time == null ) {
      if ( row.getResendProcessDateTime() != null )
        return false;
    } else if ( !resend_process_date_time.equals( row.getResendProcessDateTime() ) ) {
        return false;
    }
    if ( email_address == null ) {
      if ( row.getEmailAddress() != null )
        return false;
    } else if ( !email_address.equals( row.getEmailAddress() ) ) {
        return false;
    }
    if ( send_email_address == null ) {
      if ( row.getSendEmailAddress() != null )
        return false;
    } else if ( !send_email_address.equals( row.getSendEmailAddress() ) ) {
        return false;
    }
    if ( subject == null ) {
      if ( row.getSubject() != null )
        return false;
    } else if ( !subject.equals( row.getSubject() ) ) {
        return false;
    }
    if ( mail_text == null ) {
      if ( row.getMailText() != null )
        return false;
    } else if ( !mail_text.equals( row.getMailText() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( !row.getDeleteFlagIsNull() )
        return false;
    } else if ( row.getDeleteFlagIsNull() || ( delete_flag.intValue() != row.getDeleteFlag() ) ) {
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
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (sendmail_div!=null? sendmail_div.hashCode(): 0) 
        + (discernment_id!=null? discernment_id.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) mail_id)
        + (date_1!=null? date_1.hashCode(): 0) 
        + (date_2!=null? date_2.hashCode(): 0) 
        + (date_3!=null? date_3.hashCode(): 0) 
        + (date_4!=null? date_4.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (amount!=null? amount.hashCode(): 0) 
        + (order_id!=null? order_id.hashCode(): 0) 
        + (division!=null? division.hashCode(): 0) 
        + (name_1!=null? name_1.hashCode(): 0) 
        + (name_2!=null? name_2.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (send_process_date_time!=null? send_process_date_time.hashCode(): 0) 
        + (error_code!=null? error_code.hashCode(): 0) 
        + (admin_mail_div!=null? admin_mail_div.hashCode(): 0) 
        + (resend_status!=null? resend_status.hashCode(): 0) 
        + (resend_process_date_time!=null? resend_process_date_time.hashCode(): 0) 
        + (email_address!=null? email_address.hashCode(): 0) 
        + (send_email_address!=null? send_email_address.hashCode(): 0) 
        + (subject!=null? subject.hashCode(): 0) 
        + (mail_text!=null? mail_text.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
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
		map.put("branch_code",branch_code);
		map.put("sendmail_div",sendmail_div);
		map.put("discernment_id",discernment_id);
		map.put("account_code",account_code);
		map.put("mail_id",new Long(mail_id));
		if ( date_1 != null )
			map.put("date_1",date_1);
		if ( date_2 != null )
			map.put("date_2",date_2);
		if ( date_3 != null )
			map.put("date_3",date_3);
		if ( date_4 != null )
			map.put("date_4",date_4);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( amount != null )
			map.put("amount",amount);
		if ( order_id != null )
			map.put("order_id",order_id);
		if ( division != null )
			map.put("division",division);
		if ( name_1 != null )
			map.put("name_1",name_1);
		if ( name_2 != null )
			map.put("name_2",name_2);
		if ( status != null )
			map.put("status",status);
		if ( send_process_date_time != null )
			map.put("send_process_date_time",send_process_date_time);
		if ( error_code != null )
			map.put("error_code",error_code);
		if ( admin_mail_div != null )
			map.put("admin_mail_div",admin_mail_div);
		if ( resend_status != null )
			map.put("resend_status",resend_status);
		if ( resend_process_date_time != null )
			map.put("resend_process_date_time",resend_process_date_time);
		if ( email_address != null )
			map.put("email_address",email_address);
		if ( send_email_address != null )
			map.put("send_email_address",send_email_address);
		if ( subject != null )
			map.put("subject",subject);
		if ( mail_text != null )
			map.put("mail_text",mail_text);
		if ( delete_flag != null )
			map.put("delete_flag",delete_flag);
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
		if ( date_1_is_modified )
			map.put("date_1",date_1);
		if ( date_2_is_modified )
			map.put("date_2",date_2);
		if ( date_3_is_modified )
			map.put("date_3",date_3);
		if ( date_4_is_modified )
			map.put("date_4",date_4);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( amount_is_modified )
			map.put("amount",amount);
		if ( order_id_is_modified )
			map.put("order_id",order_id);
		if ( division_is_modified )
			map.put("division",division);
		if ( name_1_is_modified )
			map.put("name_1",name_1);
		if ( name_2_is_modified )
			map.put("name_2",name_2);
		if ( status_is_modified )
			map.put("status",status);
		if ( send_process_date_time_is_modified )
			map.put("send_process_date_time",send_process_date_time);
		if ( error_code_is_modified )
			map.put("error_code",error_code);
		if ( admin_mail_div_is_modified )
			map.put("admin_mail_div",admin_mail_div);
		if ( resend_status_is_modified )
			map.put("resend_status",resend_status);
		if ( resend_process_date_time_is_modified )
			map.put("resend_process_date_time",resend_process_date_time);
		if ( email_address_is_modified )
			map.put("email_address",email_address);
		if ( send_email_address_is_modified )
			map.put("send_email_address",send_email_address);
		if ( subject_is_modified )
			map.put("subject",subject);
		if ( mail_text_is_modified )
			map.put("mail_text",mail_text);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("date_1",date_1);
			map.put("date_2",date_2);
			map.put("date_3",date_3);
			map.put("date_4",date_4);
			map.put("quantity",quantity);
			map.put("amount",amount);
			map.put("order_id",order_id);
			map.put("division",division);
			map.put("name_1",name_1);
			map.put("name_2",name_2);
			map.put("status",status);
			map.put("send_process_date_time",send_process_date_time);
			map.put("error_code",error_code);
			map.put("admin_mail_div",admin_mail_div);
			map.put("resend_status",resend_status);
			map.put("resend_process_date_time",resend_process_date_time);
			map.put("email_address",email_address);
			map.put("send_email_address",send_email_address);
			map.put("subject",subject);
			map.put("mail_text",mail_text);
			map.put("delete_flag",delete_flag);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>institution_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>sendmail_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSendmailDiv()
  {
    return sendmail_div;
  }


  /** 
   * <em>sendmail_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSendmailDivIsSet() {
    return sendmail_div_is_set;
  }


  /** 
   * <em>sendmail_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSendmailDivIsModified() {
    return sendmail_div_is_modified;
  }


  /** 
   * <em>discernment_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDiscernmentId()
  {
    return discernment_id;
  }


  /** 
   * <em>discernment_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDiscernmentIdIsSet() {
    return discernment_id_is_set;
  }


  /** 
   * <em>discernment_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDiscernmentIdIsModified() {
    return discernment_id_is_modified;
  }


  /** 
   * <em>account_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>mail_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getMailId()
  {
    return mail_id;
  }


  /** 
   * <em>mail_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailIdIsSet() {
    return mail_id_is_set;
  }


  /** 
   * <em>mail_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailIdIsModified() {
    return mail_id_is_modified;
  }


  /** 
   * <em>date_1</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDate1()
  {
    return date_1;
  }


  /** 
   * <em>date_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDate1IsSet() {
    return date_1_is_set;
  }


  /** 
   * <em>date_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDate1IsModified() {
    return date_1_is_modified;
  }


  /** 
   * <em>date_2</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDate2()
  {
    return date_2;
  }


  /** 
   * <em>date_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDate2IsSet() {
    return date_2_is_set;
  }


  /** 
   * <em>date_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDate2IsModified() {
    return date_2_is_modified;
  }


  /** 
   * <em>date_3</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDate3()
  {
    return date_3;
  }


  /** 
   * <em>date_3</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDate3IsSet() {
    return date_3_is_set;
  }


  /** 
   * <em>date_3</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDate3IsModified() {
    return date_3_is_modified;
  }


  /** 
   * <em>date_4</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDate4()
  {
    return date_4;
  }


  /** 
   * <em>date_4</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDate4IsSet() {
    return date_4_is_set;
  }


  /** 
   * <em>date_4</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDate4IsModified() {
    return date_4_is_modified;
  }


  /** 
   * <em>quantity</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
  }


  /** 
   * <em>quantity</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityIsSet() {
    return quantity_is_set;
  }


  /** 
   * <em>quantity</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuantityIsModified() {
    return quantity_is_modified;
  }


  /** 
   * <em>amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAmount()
  {
    return ( amount==null? ((double)0): amount.doubleValue() );
  }


  /** 
   * <em>amount</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAmountIsNull()
  {
    return amount == null;
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
   * <em>order_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getOrderId()
  {
    return ( order_id==null? ((long)0): order_id.longValue() );
  }


  /** 
   * <em>order_id</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOrderIdIsNull()
  {
    return order_id == null;
  }


  /** 
   * <em>order_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>order_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderIdIsModified() {
    return order_id_is_modified;
  }


  /** 
   * <em>division</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getDivision()
  {
    return division;
  }


  /** 
   * <em>division</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDivisionIsSet() {
    return division_is_set;
  }


  /** 
   * <em>division</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDivisionIsModified() {
    return division_is_modified;
  }


  /** 
   * <em>name_1</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getName1()
  {
    return name_1;
  }


  /** 
   * <em>name_1</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getName1IsSet() {
    return name_1_is_set;
  }


  /** 
   * <em>name_1</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getName1IsModified() {
    return name_1_is_modified;
  }


  /** 
   * <em>name_2</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getName2()
  {
    return name_2;
  }


  /** 
   * <em>name_2</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getName2IsSet() {
    return name_2_is_set;
  }


  /** 
   * <em>name_2</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getName2IsModified() {
    return name_2_is_modified;
  }


  /** 
   * <em>status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * <em>send_process_date_time</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getSendProcessDateTime()
  {
    return send_process_date_time;
  }


  /** 
   * <em>send_process_date_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSendProcessDateTimeIsSet() {
    return send_process_date_time_is_set;
  }


  /** 
   * <em>send_process_date_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSendProcessDateTimeIsModified() {
    return send_process_date_time_is_modified;
  }


  /** 
   * <em>error_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getErrorCode()
  {
    return error_code;
  }


  /** 
   * <em>error_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getErrorCodeIsSet() {
    return error_code_is_set;
  }


  /** 
   * <em>error_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getErrorCodeIsModified() {
    return error_code_is_modified;
  }


  /** 
   * <em>admin_mail_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAdminMailDiv()
  {
    return admin_mail_div;
  }


  /** 
   * <em>admin_mail_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdminMailDivIsSet() {
    return admin_mail_div_is_set;
  }


  /** 
   * <em>admin_mail_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdminMailDivIsModified() {
    return admin_mail_div_is_modified;
  }


  /** 
   * <em>resend_status</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getResendStatus()
  {
    return resend_status;
  }


  /** 
   * <em>resend_status</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getResendStatusIsSet() {
    return resend_status_is_set;
  }


  /** 
   * <em>resend_status</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getResendStatusIsModified() {
    return resend_status_is_modified;
  }


  /** 
   * <em>resend_process_date_time</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getResendProcessDateTime()
  {
    return resend_process_date_time;
  }


  /** 
   * <em>resend_process_date_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getResendProcessDateTimeIsSet() {
    return resend_process_date_time_is_set;
  }


  /** 
   * <em>resend_process_date_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getResendProcessDateTimeIsModified() {
    return resend_process_date_time_is_modified;
  }


  /** 
   * <em>email_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEmailAddress()
  {
    return email_address;
  }


  /** 
   * <em>email_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEmailAddressIsSet() {
    return email_address_is_set;
  }


  /** 
   * <em>email_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEmailAddressIsModified() {
    return email_address_is_modified;
  }


  /** 
   * <em>send_email_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSendEmailAddress()
  {
    return send_email_address;
  }


  /** 
   * <em>send_email_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSendEmailAddressIsSet() {
    return send_email_address_is_set;
  }


  /** 
   * <em>send_email_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSendEmailAddressIsModified() {
    return send_email_address_is_modified;
  }


  /** 
   * <em>subject</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSubject()
  {
    return subject;
  }


  /** 
   * <em>subject</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubjectIsSet() {
    return subject_is_set;
  }


  /** 
   * <em>subject</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubjectIsModified() {
    return subject_is_modified;
  }


  /** 
   * <em>mail_text</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMailText()
  {
    return mail_text;
  }


  /** 
   * <em>mail_text</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailTextIsSet() {
    return mail_text_is_set;
  }


  /** 
   * <em>mail_text</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailTextIsModified() {
    return mail_text_is_modified;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getDeleteFlag()
  {
    return ( delete_flag==null? ((int)0): delete_flag.intValue() );
  }


  /** 
   * <em>delete_flag</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getDeleteFlagIsNull()
  {
    return delete_flag == null;
  }


  /** 
   * <em>delete_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
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
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MailProcTempPK(institution_code, branch_code, sendmail_div, discernment_id, account_code, mail_id);
  }


  /** 
   * <em>institution_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_institutionCode <em>institution_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchCode <em>branch_code</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>sendmail_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sendmailDiv <em>sendmail_div</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setSendmailDiv( String p_sendmailDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sendmail_div = p_sendmailDiv;
    sendmail_div_is_set = true;
    sendmail_div_is_modified = true;
  }


  /** 
   * <em>discernment_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_discernmentId <em>discernment_id</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setDiscernmentId( String p_discernmentId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    discernment_id = p_discernmentId;
    discernment_id_is_set = true;
    discernment_id_is_modified = true;
  }


  /** 
   * <em>account_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountCode <em>account_code</em>�J�����̒l������킷7���ȉ���String�l 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>mail_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mailId <em>mail_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setMailId( long p_mailId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_id = p_mailId;
    mail_id_is_set = true;
    mail_id_is_modified = true;
  }


  /** 
   * <em>date_1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_date1 <em>date_1</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDate1( java.sql.Timestamp p_date1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    date_1 = p_date1;
    date_1_is_set = true;
    date_1_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>date_1</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDate1( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    date_1 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    date_1_is_set = true;
    date_1_is_modified = true;
  }


  /** 
   * <em>date_2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_date2 <em>date_2</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDate2( java.sql.Timestamp p_date2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    date_2 = p_date2;
    date_2_is_set = true;
    date_2_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>date_2</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDate2( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    date_2 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    date_2_is_set = true;
    date_2_is_modified = true;
  }


  /** 
   * <em>date_3</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_date3 <em>date_3</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDate3( java.sql.Timestamp p_date3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    date_3 = p_date3;
    date_3_is_set = true;
    date_3_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>date_3</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDate3( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    date_3 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    date_3_is_set = true;
    date_3_is_modified = true;
  }


  /** 
   * <em>date_4</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_date4 <em>date_4</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDate4( java.sql.Timestamp p_date4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    date_4 = p_date4;
    date_4_is_set = true;
    date_4_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>date_4</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDate4( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    date_4 = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    date_4_is_set = true;
    date_4_is_modified = true;
  }


  /** 
   * <em>quantity</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quantity <em>quantity</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>quantity</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_amount <em>amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAmount( double p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount = new Double(p_amount);
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>amount</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAmount( Double p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount = p_amount;
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * <em>order_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderId <em>order_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = new Long(p_orderId);
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>order_id</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOrderId( Long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>division</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_division <em>division</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setDivision( String p_division )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    division = p_division;
    division_is_set = true;
    division_is_modified = true;
  }


  /** 
   * <em>name_1</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_name1 <em>name_1</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setName1( String p_name1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_1 = p_name1;
    name_1_is_set = true;
    name_1_is_modified = true;
  }


  /** 
   * <em>name_2</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_name2 <em>name_2</em>�J�����̒l������킷50���ȉ���String�l 
   */
  public final void setName2( String p_name2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_2 = p_name2;
    name_2_is_set = true;
    name_2_is_modified = true;
  }


  /** 
   * <em>status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_status <em>status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>send_process_date_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sendProcessDateTime <em>send_process_date_time</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setSendProcessDateTime( java.sql.Timestamp p_sendProcessDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_process_date_time = p_sendProcessDateTime;
    send_process_date_time_is_set = true;
    send_process_date_time_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>send_process_date_time</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setSendProcessDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    send_process_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    send_process_date_time_is_set = true;
    send_process_date_time_is_modified = true;
  }


  /** 
   * <em>error_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_errorCode <em>error_code</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setErrorCode( String p_errorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_code = p_errorCode;
    error_code_is_set = true;
    error_code_is_modified = true;
  }


  /** 
   * <em>admin_mail_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_adminMailDiv <em>admin_mail_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setAdminMailDiv( String p_adminMailDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    admin_mail_div = p_adminMailDiv;
    admin_mail_div_is_set = true;
    admin_mail_div_is_modified = true;
  }


  /** 
   * <em>resend_status</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_resendStatus <em>resend_status</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setResendStatus( String p_resendStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    resend_status = p_resendStatus;
    resend_status_is_set = true;
    resend_status_is_modified = true;
  }


  /** 
   * <em>resend_process_date_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_resendProcessDateTime <em>resend_process_date_time</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setResendProcessDateTime( java.sql.Timestamp p_resendProcessDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    resend_process_date_time = p_resendProcessDateTime;
    resend_process_date_time_is_set = true;
    resend_process_date_time_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>resend_process_date_time</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setResendProcessDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    resend_process_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    resend_process_date_time_is_set = true;
    resend_process_date_time_is_modified = true;
  }


  /** 
   * <em>email_address</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_emailAddress <em>email_address</em>�J�����̒l������킷100���ȉ���String�l 
   */
  public final void setEmailAddress( String p_emailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_address = p_emailAddress;
    email_address_is_set = true;
    email_address_is_modified = true;
  }


  /** 
   * <em>send_email_address</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sendEmailAddress <em>send_email_address</em>�J�����̒l������킷100���ȉ���String�l 
   */
  public final void setSendEmailAddress( String p_sendEmailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_email_address = p_sendEmailAddress;
    send_email_address_is_set = true;
    send_email_address_is_modified = true;
  }


  /** 
   * <em>subject</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subject <em>subject</em>�J�����̒l������킷1000���ȉ���String�l 
   */
  public final void setSubject( String p_subject )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    subject = p_subject;
    subject_is_set = true;
    subject_is_modified = true;
  }


  /** 
   * <em>mail_text</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mailText <em>mail_text</em>�J�����̒l������킷String�l
   */
  public final void setMailText( String p_mailText )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_text = p_mailText;
    mail_text_is_set = true;
    mail_text_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deleteFlag <em>delete_flag</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setDeleteFlag( int p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = new Integer(p_deleteFlag);
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>delete_flag</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setDeleteFlag( Integer p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>created_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_updated_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("amount") ) {
                    return this.amount;
                }
                else if ( name.equals("admin_mail_div") ) {
                    return this.admin_mail_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("discernment_id") ) {
                    return this.discernment_id;
                }
                else if ( name.equals("date_1") ) {
                    return this.date_1;
                }
                else if ( name.equals("date_2") ) {
                    return this.date_2;
                }
                else if ( name.equals("date_3") ) {
                    return this.date_3;
                }
                else if ( name.equals("date_4") ) {
                    return this.date_4;
                }
                else if ( name.equals("division") ) {
                    return this.division;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'e':
                if ( name.equals("error_code") ) {
                    return this.error_code;
                }
                else if ( name.equals("email_address") ) {
                    return this.email_address;
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
            case 'm':
                if ( name.equals("mail_id") ) {
                    return new Long( mail_id );
                }
                else if ( name.equals("mail_text") ) {
                    return this.mail_text;
                }
                break;
            case 'n':
                if ( name.equals("name_1") ) {
                    return this.name_1;
                }
                else if ( name.equals("name_2") ) {
                    return this.name_2;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return this.order_id;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 'r':
                if ( name.equals("resend_status") ) {
                    return this.resend_status;
                }
                else if ( name.equals("resend_process_date_time") ) {
                    return this.resend_process_date_time;
                }
                break;
            case 's':
                if ( name.equals("sendmail_div") ) {
                    return this.sendmail_div;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("send_process_date_time") ) {
                    return this.send_process_date_time;
                }
                else if ( name.equals("send_email_address") ) {
                    return this.send_email_address;
                }
                else if ( name.equals("subject") ) {
                    return this.subject;
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
                if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'amount' must be Double: '"+value+"' is not." );
                    this.amount = (Double) value;
                    if (this.amount_is_set)
                        this.amount_is_modified = true;
                    this.amount_is_set = true;
                    return;
                }
                else if ( name.equals("admin_mail_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'admin_mail_div' must be String: '"+value+"' is not." );
                    this.admin_mail_div = (String) value;
                    if (this.admin_mail_div_is_set)
                        this.admin_mail_div_is_modified = true;
                    this.admin_mail_div_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("discernment_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'discernment_id' must be String: '"+value+"' is not." );
                    this.discernment_id = (String) value;
                    if (this.discernment_id_is_set)
                        this.discernment_id_is_modified = true;
                    this.discernment_id_is_set = true;
                    return;
                }
                else if ( name.equals("date_1") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'date_1' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.date_1 = (java.sql.Timestamp) value;
                    if (this.date_1_is_set)
                        this.date_1_is_modified = true;
                    this.date_1_is_set = true;
                    return;
                }
                else if ( name.equals("date_2") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'date_2' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.date_2 = (java.sql.Timestamp) value;
                    if (this.date_2_is_set)
                        this.date_2_is_modified = true;
                    this.date_2_is_set = true;
                    return;
                }
                else if ( name.equals("date_3") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'date_3' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.date_3 = (java.sql.Timestamp) value;
                    if (this.date_3_is_set)
                        this.date_3_is_modified = true;
                    this.date_3_is_set = true;
                    return;
                }
                else if ( name.equals("date_4") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'date_4' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.date_4 = (java.sql.Timestamp) value;
                    if (this.date_4_is_set)
                        this.date_4_is_modified = true;
                    this.date_4_is_set = true;
                    return;
                }
                else if ( name.equals("division") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'division' must be String: '"+value+"' is not." );
                    this.division = (String) value;
                    if (this.division_is_set)
                        this.division_is_modified = true;
                    this.division_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be Integer: '"+value+"' is not." );
                    this.delete_flag = (Integer) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("error_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_code' must be String: '"+value+"' is not." );
                    this.error_code = (String) value;
                    if (this.error_code_is_set)
                        this.error_code_is_modified = true;
                    this.error_code_is_set = true;
                    return;
                }
                else if ( name.equals("email_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_address' must be String: '"+value+"' is not." );
                    this.email_address = (String) value;
                    if (this.email_address_is_set)
                        this.email_address_is_modified = true;
                    this.email_address_is_set = true;
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
            case 'm':
                if ( name.equals("mail_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'mail_id' must be Long: '"+value+"' is not." );
                    this.mail_id = ((Long) value).longValue();
                    if (this.mail_id_is_set)
                        this.mail_id_is_modified = true;
                    this.mail_id_is_set = true;
                    return;
                }
                else if ( name.equals("mail_text") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mail_text' must be String: '"+value+"' is not." );
                    this.mail_text = (String) value;
                    if (this.mail_text_is_set)
                        this.mail_text_is_modified = true;
                    this.mail_text_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("name_1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name_1' must be String: '"+value+"' is not." );
                    this.name_1 = (String) value;
                    if (this.name_1_is_set)
                        this.name_1_is_modified = true;
                    this.name_1_is_set = true;
                    return;
                }
                else if ( name.equals("name_2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name_2' must be String: '"+value+"' is not." );
                    this.name_2 = (String) value;
                    if (this.name_2_is_set)
                        this.name_2_is_modified = true;
                    this.name_2_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = (Long) value;
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("resend_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'resend_status' must be String: '"+value+"' is not." );
                    this.resend_status = (String) value;
                    if (this.resend_status_is_set)
                        this.resend_status_is_modified = true;
                    this.resend_status_is_set = true;
                    return;
                }
                else if ( name.equals("resend_process_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'resend_process_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.resend_process_date_time = (java.sql.Timestamp) value;
                    if (this.resend_process_date_time_is_set)
                        this.resend_process_date_time_is_modified = true;
                    this.resend_process_date_time_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sendmail_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sendmail_div' must be String: '"+value+"' is not." );
                    this.sendmail_div = (String) value;
                    if (this.sendmail_div_is_set)
                        this.sendmail_div_is_modified = true;
                    this.sendmail_div_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("send_process_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'send_process_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.send_process_date_time = (java.sql.Timestamp) value;
                    if (this.send_process_date_time_is_set)
                        this.send_process_date_time_is_modified = true;
                    this.send_process_date_time_is_set = true;
                    return;
                }
                else if ( name.equals("send_email_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_email_address' must be String: '"+value+"' is not." );
                    this.send_email_address = (String) value;
                    if (this.send_email_address_is_set)
                        this.send_email_address_is_modified = true;
                    this.send_email_address_is_set = true;
                    return;
                }
                else if ( name.equals("subject") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'subject' must be String: '"+value+"' is not." );
                    this.subject = (String) value;
                    if (this.subject_is_set)
                        this.subject_is_modified = true;
                    this.subject_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
