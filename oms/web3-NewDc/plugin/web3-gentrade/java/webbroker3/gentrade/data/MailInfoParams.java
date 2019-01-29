head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MailInfoParams.java;


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
 * mail_info�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MailInfoRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MailInfoParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MailInfoParams}��{@@link MailInfoRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MailInfoPK 
 * @@see MailInfoRow 
 */
public class MailInfoParams extends Params implements MailInfoRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mail_info";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MailInfoRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MailInfoRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>sendmail_div</em>�J�����̒l 
   */
  public  String  sendmail_div;

  /** 
   * <em>discernment_id</em>�J�����̒l 
   */
  public  String  discernment_id;

  /** 
   * <em>mail_name</em>�J�����̒l 
   */
  public  String  mail_name;

  /** 
   * <em>mail_sender</em>�J�����̒l 
   */
  public  String  mail_sender;

  /** 
   * <em>send_address</em>�J�����̒l 
   */
  public  String  send_address;

  /** 
   * <em>subject</em>�J�����̒l 
   */
  public  String  subject;

  /** 
   * <em>mail_header</em>�J�����̒l 
   */
  public  String  mail_header;

  /** 
   * <em>mail_text</em>�J�����̒l 
   */
  public  String  mail_text;

  /** 
   * <em>mail_footer</em>�J�����̒l 
   */
  public  String  mail_footer;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

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
  private boolean sendmail_div_is_set = false;
  private boolean sendmail_div_is_modified = false;
  private boolean discernment_id_is_set = false;
  private boolean discernment_id_is_modified = false;
  private boolean mail_name_is_set = false;
  private boolean mail_name_is_modified = false;
  private boolean mail_sender_is_set = false;
  private boolean mail_sender_is_modified = false;
  private boolean send_address_is_set = false;
  private boolean send_address_is_modified = false;
  private boolean subject_is_set = false;
  private boolean subject_is_modified = false;
  private boolean mail_header_is_set = false;
  private boolean mail_header_is_modified = false;
  private boolean mail_text_is_set = false;
  private boolean mail_text_is_modified = false;
  private boolean mail_footer_is_set = false;
  private boolean mail_footer_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
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
      + "," + "sendmail_div=" + sendmail_div
      + "," + "discernment_id=" + discernment_id
      + "," + "mail_name=" +mail_name
      + "," + "mail_sender=" +mail_sender
      + "," + "send_address=" +send_address
      + "," + "subject=" +subject
      + "," + "mail_header=" +mail_header
      + "," + "mail_text=" +mail_text
      + "," + "mail_footer=" +mail_footer
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��MailInfoParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MailInfoParams() {
    institution_code_is_modified = true;
    sendmail_div_is_modified = true;
    discernment_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MailInfoRow�I�u�W�F�N�g�̒l�𗘗p����MailInfoParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MailInfoRow�I�u�W�F�N�g 
   */
  public MailInfoParams( MailInfoRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    sendmail_div = p_row.getSendmailDiv();
    sendmail_div_is_set = p_row.getSendmailDivIsSet();
    sendmail_div_is_modified = p_row.getSendmailDivIsModified();
    discernment_id = p_row.getDiscernmentId();
    discernment_id_is_set = p_row.getDiscernmentIdIsSet();
    discernment_id_is_modified = p_row.getDiscernmentIdIsModified();
    mail_name = p_row.getMailName();
    mail_name_is_set = p_row.getMailNameIsSet();
    mail_name_is_modified = p_row.getMailNameIsModified();
    mail_sender = p_row.getMailSender();
    mail_sender_is_set = p_row.getMailSenderIsSet();
    mail_sender_is_modified = p_row.getMailSenderIsModified();
    send_address = p_row.getSendAddress();
    send_address_is_set = p_row.getSendAddressIsSet();
    send_address_is_modified = p_row.getSendAddressIsModified();
    subject = p_row.getSubject();
    subject_is_set = p_row.getSubjectIsSet();
    subject_is_modified = p_row.getSubjectIsModified();
    mail_header = p_row.getMailHeader();
    mail_header_is_set = p_row.getMailHeaderIsSet();
    mail_header_is_modified = p_row.getMailHeaderIsModified();
    mail_text = p_row.getMailText();
    mail_text_is_set = p_row.getMailTextIsSet();
    mail_text_is_modified = p_row.getMailTextIsModified();
    mail_footer = p_row.getMailFooter();
    mail_footer_is_set = p_row.getMailFooterIsSet();
    mail_footer_is_modified = p_row.getMailFooterIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      mail_name_is_set = true;
      mail_name_is_modified = true;
      mail_sender_is_set = true;
      mail_sender_is_modified = true;
      send_address_is_set = true;
      send_address_is_modified = true;
      subject_is_set = true;
      subject_is_modified = true;
      mail_header_is_set = true;
      mail_header_is_modified = true;
      mail_text_is_set = true;
      mail_text_is_modified = true;
      mail_footer_is_set = true;
      mail_footer_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof MailInfoRow ) )
       return false;
    return fieldsEqual( (MailInfoRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MailInfoRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MailInfoRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
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
    if ( mail_name == null ) {
      if ( row.getMailName() != null )
        return false;
    } else if ( !mail_name.equals( row.getMailName() ) ) {
        return false;
    }
    if ( mail_sender == null ) {
      if ( row.getMailSender() != null )
        return false;
    } else if ( !mail_sender.equals( row.getMailSender() ) ) {
        return false;
    }
    if ( send_address == null ) {
      if ( row.getSendAddress() != null )
        return false;
    } else if ( !send_address.equals( row.getSendAddress() ) ) {
        return false;
    }
    if ( subject == null ) {
      if ( row.getSubject() != null )
        return false;
    } else if ( !subject.equals( row.getSubject() ) ) {
        return false;
    }
    if ( mail_header == null ) {
      if ( row.getMailHeader() != null )
        return false;
    } else if ( !mail_header.equals( row.getMailHeader() ) ) {
        return false;
    }
    if ( mail_text == null ) {
      if ( row.getMailText() != null )
        return false;
    } else if ( !mail_text.equals( row.getMailText() ) ) {
        return false;
    }
    if ( mail_footer == null ) {
      if ( row.getMailFooter() != null )
        return false;
    } else if ( !mail_footer.equals( row.getMailFooter() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
        + (sendmail_div!=null? sendmail_div.hashCode(): 0) 
        + (discernment_id!=null? discernment_id.hashCode(): 0) 
        + (mail_name!=null? mail_name.hashCode(): 0) 
        + (mail_sender!=null? mail_sender.hashCode(): 0) 
        + (send_address!=null? send_address.hashCode(): 0) 
        + (subject!=null? subject.hashCode(): 0) 
        + (mail_header!=null? mail_header.hashCode(): 0) 
        + (mail_text!=null? mail_text.hashCode(): 0) 
        + (mail_footer!=null? mail_footer.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !subject_is_set )
			throw new IllegalArgumentException("non-nullable field 'subject' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("sendmail_div",sendmail_div);
		map.put("discernment_id",discernment_id);
		if ( mail_name != null )
			map.put("mail_name",mail_name);
		if ( mail_sender != null )
			map.put("mail_sender",mail_sender);
		if ( send_address != null )
			map.put("send_address",send_address);
		map.put("subject",subject);
		if ( mail_header != null )
			map.put("mail_header",mail_header);
		if ( mail_text != null )
			map.put("mail_text",mail_text);
		if ( mail_footer != null )
			map.put("mail_footer",mail_footer);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( mail_name_is_modified )
			map.put("mail_name",mail_name);
		if ( mail_sender_is_modified )
			map.put("mail_sender",mail_sender);
		if ( send_address_is_modified )
			map.put("send_address",send_address);
		if ( subject_is_modified )
			map.put("subject",subject);
		if ( mail_header_is_modified )
			map.put("mail_header",mail_header);
		if ( mail_text_is_modified )
			map.put("mail_text",mail_text);
		if ( mail_footer_is_modified )
			map.put("mail_footer",mail_footer);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("mail_name",mail_name);
			map.put("mail_sender",mail_sender);
			map.put("send_address",send_address);
			if ( subject_is_set )
				map.put("subject",subject);
			map.put("mail_header",mail_header);
			map.put("mail_text",mail_text);
			map.put("mail_footer",mail_footer);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
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
   * <em>mail_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMailName()
  {
    return mail_name;
  }


  /** 
   * <em>mail_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailNameIsSet() {
    return mail_name_is_set;
  }


  /** 
   * <em>mail_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailNameIsModified() {
    return mail_name_is_modified;
  }


  /** 
   * <em>mail_sender</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMailSender()
  {
    return mail_sender;
  }


  /** 
   * <em>mail_sender</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailSenderIsSet() {
    return mail_sender_is_set;
  }


  /** 
   * <em>mail_sender</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailSenderIsModified() {
    return mail_sender_is_modified;
  }


  /** 
   * <em>send_address</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSendAddress()
  {
    return send_address;
  }


  /** 
   * <em>send_address</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSendAddressIsSet() {
    return send_address_is_set;
  }


  /** 
   * <em>send_address</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSendAddressIsModified() {
    return send_address_is_modified;
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
   * <em>mail_header</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMailHeader()
  {
    return mail_header;
  }


  /** 
   * <em>mail_header</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailHeaderIsSet() {
    return mail_header_is_set;
  }


  /** 
   * <em>mail_header</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailHeaderIsModified() {
    return mail_header_is_modified;
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
   * <em>mail_footer</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMailFooter()
  {
    return mail_footer;
  }


  /** 
   * <em>mail_footer</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailFooterIsSet() {
    return mail_footer_is_set;
  }


  /** 
   * <em>mail_footer</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMailFooterIsModified() {
    return mail_footer_is_modified;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
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
    return new MailInfoPK(institution_code, sendmail_div, discernment_id);
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
   * <em>mail_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mailName <em>mail_name</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setMailName( String p_mailName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_name = p_mailName;
    mail_name_is_set = true;
    mail_name_is_modified = true;
  }


  /** 
   * <em>mail_sender</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mailSender <em>mail_sender</em>�J�����̒l������킷300���ȉ���String�l 
   */
  public final void setMailSender( String p_mailSender )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_sender = p_mailSender;
    mail_sender_is_set = true;
    mail_sender_is_modified = true;
  }


  /** 
   * <em>send_address</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sendAddress <em>send_address</em>�J�����̒l������킷1020���ȉ���String�l 
   */
  public final void setSendAddress( String p_sendAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_address = p_sendAddress;
    send_address_is_set = true;
    send_address_is_modified = true;
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
   * <em>mail_header</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mailHeader <em>mail_header</em>�J�����̒l������킷4000���ȉ���String�l 
   */
  public final void setMailHeader( String p_mailHeader )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_header = p_mailHeader;
    mail_header_is_set = true;
    mail_header_is_modified = true;
  }


  /** 
   * <em>mail_text</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mailText <em>mail_text</em>�J�����̒l������킷4000���ȉ���String�l 
   */
  public final void setMailText( String p_mailText )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_text = p_mailText;
    mail_text_is_set = true;
    mail_text_is_modified = true;
  }


  /** 
   * <em>mail_footer</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_mailFooter <em>mail_footer</em>�J�����̒l������킷4000���ȉ���String�l 
   */
  public final void setMailFooter( String p_mailFooter )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mail_footer = p_mailFooter;
    mail_footer_is_set = true;
    mail_footer_is_modified = true;
  }


  /** 
   * <em>last_updater</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdater <em>last_updater</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
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
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("discernment_id") ) {
                    return this.discernment_id;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mail_name") ) {
                    return this.mail_name;
                }
                else if ( name.equals("mail_sender") ) {
                    return this.mail_sender;
                }
                else if ( name.equals("mail_header") ) {
                    return this.mail_header;
                }
                else if ( name.equals("mail_text") ) {
                    return this.mail_text;
                }
                else if ( name.equals("mail_footer") ) {
                    return this.mail_footer;
                }
                break;
            case 's':
                if ( name.equals("sendmail_div") ) {
                    return this.sendmail_div;
                }
                else if ( name.equals("send_address") ) {
                    return this.send_address;
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
                if ( name.equals("last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
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
                if ( name.equals("mail_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mail_name' must be String: '"+value+"' is not." );
                    this.mail_name = (String) value;
                    if (this.mail_name_is_set)
                        this.mail_name_is_modified = true;
                    this.mail_name_is_set = true;
                    return;
                }
                else if ( name.equals("mail_sender") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mail_sender' must be String: '"+value+"' is not." );
                    this.mail_sender = (String) value;
                    if (this.mail_sender_is_set)
                        this.mail_sender_is_modified = true;
                    this.mail_sender_is_set = true;
                    return;
                }
                else if ( name.equals("mail_header") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mail_header' must be String: '"+value+"' is not." );
                    this.mail_header = (String) value;
                    if (this.mail_header_is_set)
                        this.mail_header_is_modified = true;
                    this.mail_header_is_set = true;
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
                else if ( name.equals("mail_footer") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mail_footer' must be String: '"+value+"' is not." );
                    this.mail_footer = (String) value;
                    if (this.mail_footer_is_set)
                        this.mail_footer_is_modified = true;
                    this.mail_footer_is_set = true;
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
                else if ( name.equals("send_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_address' must be String: '"+value+"' is not." );
                    this.send_address = (String) value;
                    if (this.send_address_is_set)
                        this.send_address_is_modified = true;
                    this.send_address_is_set = true;
                    return;
                }
                else if ( name.equals("subject") ) {
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
