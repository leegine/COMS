head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.10.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BatchBranchParams.java;


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
 * batch_branch�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link BatchBranchRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link BatchBranchParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link BatchBranchParams}��{@@link BatchBranchRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BatchBranchPK 
 * @@see BatchBranchRow 
 */
public class BatchBranchParams extends Params implements BatchBranchRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "batch_branch";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = BatchBranchRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return BatchBranchRow.TYPE;
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
   * <em>equity_order_exe_mail_flag</em>�J�����̒l 
   */
  public  int  equity_order_exe_mail_flag;

  /** 
   * <em>equity_order_unexec_mail_flag</em>�J�����̒l 
   */
  public  int  equity_order_unexec_mail_flag;

  /** 
   * <em>ifo_order_exec_mail_flag</em>�J�����̒l 
   */
  public  int  ifo_order_exec_mail_flag;

  /** 
   * <em>ifo_order_unexec_mail_flag</em>�J�����̒l 
   */
  public  int  ifo_order_unexec_mail_flag;

  /** 
   * <em>information_mail_flag</em>�J�����̒l 
   */
  public  int  information_mail_flag;

  /** 
   * <em>transfer_count</em>�J�����̒l 
   */
  public  int  transfer_count;

  /** 
   * <em>top_page_id</em>�J�����̒l 
   */
  public  String  top_page_id;

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
  private boolean equity_order_exe_mail_flag_is_set = false;
  private boolean equity_order_exe_mail_flag_is_modified = false;
  private boolean equity_order_unexec_mail_flag_is_set = false;
  private boolean equity_order_unexec_mail_flag_is_modified = false;
  private boolean ifo_order_exec_mail_flag_is_set = false;
  private boolean ifo_order_exec_mail_flag_is_modified = false;
  private boolean ifo_order_unexec_mail_flag_is_set = false;
  private boolean ifo_order_unexec_mail_flag_is_modified = false;
  private boolean information_mail_flag_is_set = false;
  private boolean information_mail_flag_is_modified = false;
  private boolean transfer_count_is_set = false;
  private boolean transfer_count_is_modified = false;
  private boolean top_page_id_is_set = false;
  private boolean top_page_id_is_modified = false;
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
      + "," + "equity_order_exe_mail_flag=" +equity_order_exe_mail_flag
      + "," + "equity_order_unexec_mail_flag=" +equity_order_unexec_mail_flag
      + "," + "ifo_order_exec_mail_flag=" +ifo_order_exec_mail_flag
      + "," + "ifo_order_unexec_mail_flag=" +ifo_order_unexec_mail_flag
      + "," + "information_mail_flag=" +information_mail_flag
      + "," + "transfer_count=" +transfer_count
      + "," + "top_page_id=" +top_page_id
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��BatchBranchParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public BatchBranchParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���BatchBranchRow�I�u�W�F�N�g�̒l�𗘗p����BatchBranchParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����BatchBranchRow�I�u�W�F�N�g 
   */
  public BatchBranchParams( BatchBranchRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    equity_order_exe_mail_flag = p_row.getEquityOrderExeMailFlag();
    equity_order_exe_mail_flag_is_set = p_row.getEquityOrderExeMailFlagIsSet();
    equity_order_exe_mail_flag_is_modified = p_row.getEquityOrderExeMailFlagIsModified();
    equity_order_unexec_mail_flag = p_row.getEquityOrderUnexecMailFlag();
    equity_order_unexec_mail_flag_is_set = p_row.getEquityOrderUnexecMailFlagIsSet();
    equity_order_unexec_mail_flag_is_modified = p_row.getEquityOrderUnexecMailFlagIsModified();
    ifo_order_exec_mail_flag = p_row.getIfoOrderExecMailFlag();
    ifo_order_exec_mail_flag_is_set = p_row.getIfoOrderExecMailFlagIsSet();
    ifo_order_exec_mail_flag_is_modified = p_row.getIfoOrderExecMailFlagIsModified();
    ifo_order_unexec_mail_flag = p_row.getIfoOrderUnexecMailFlag();
    ifo_order_unexec_mail_flag_is_set = p_row.getIfoOrderUnexecMailFlagIsSet();
    ifo_order_unexec_mail_flag_is_modified = p_row.getIfoOrderUnexecMailFlagIsModified();
    information_mail_flag = p_row.getInformationMailFlag();
    information_mail_flag_is_set = p_row.getInformationMailFlagIsSet();
    information_mail_flag_is_modified = p_row.getInformationMailFlagIsModified();
    transfer_count = p_row.getTransferCount();
    transfer_count_is_set = p_row.getTransferCountIsSet();
    transfer_count_is_modified = p_row.getTransferCountIsModified();
    top_page_id = p_row.getTopPageId();
    top_page_id_is_set = p_row.getTopPageIdIsSet();
    top_page_id_is_modified = p_row.getTopPageIdIsModified();
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
      equity_order_exe_mail_flag_is_set = true;
      equity_order_exe_mail_flag_is_modified = true;
      equity_order_unexec_mail_flag_is_set = true;
      equity_order_unexec_mail_flag_is_modified = true;
      ifo_order_exec_mail_flag_is_set = true;
      ifo_order_exec_mail_flag_is_modified = true;
      ifo_order_unexec_mail_flag_is_set = true;
      ifo_order_unexec_mail_flag_is_modified = true;
      information_mail_flag_is_set = true;
      information_mail_flag_is_modified = true;
      transfer_count_is_set = true;
      transfer_count_is_modified = true;
      top_page_id_is_set = true;
      top_page_id_is_modified = true;
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
    if ( !( other instanceof BatchBranchRow ) )
       return false;
    return fieldsEqual( (BatchBranchRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�BatchBranchRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( BatchBranchRow row )
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
    if ( equity_order_exe_mail_flag != row.getEquityOrderExeMailFlag() )
      return false;
    if ( equity_order_unexec_mail_flag != row.getEquityOrderUnexecMailFlag() )
      return false;
    if ( ifo_order_exec_mail_flag != row.getIfoOrderExecMailFlag() )
      return false;
    if ( ifo_order_unexec_mail_flag != row.getIfoOrderUnexecMailFlag() )
      return false;
    if ( information_mail_flag != row.getInformationMailFlag() )
      return false;
    if ( transfer_count != row.getTransferCount() )
      return false;
    if ( top_page_id == null ) {
      if ( row.getTopPageId() != null )
        return false;
    } else if ( !top_page_id.equals( row.getTopPageId() ) ) {
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
        + ((int) equity_order_exe_mail_flag)
        + ((int) equity_order_unexec_mail_flag)
        + ((int) ifo_order_exec_mail_flag)
        + ((int) ifo_order_unexec_mail_flag)
        + ((int) information_mail_flag)
        + ((int) transfer_count)
        + (top_page_id!=null? top_page_id.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !equity_order_exe_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'equity_order_exe_mail_flag' must be set before inserting.");
		if ( !equity_order_unexec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'equity_order_unexec_mail_flag' must be set before inserting.");
		if ( !ifo_order_exec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_order_exec_mail_flag' must be set before inserting.");
		if ( !ifo_order_unexec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_order_unexec_mail_flag' must be set before inserting.");
		if ( !information_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'information_mail_flag' must be set before inserting.");
		if ( !transfer_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_count' must be set before inserting.");
		if ( !top_page_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'top_page_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("equity_order_exe_mail_flag",new Integer(equity_order_exe_mail_flag));
		map.put("equity_order_unexec_mail_flag",new Integer(equity_order_unexec_mail_flag));
		map.put("ifo_order_exec_mail_flag",new Integer(ifo_order_exec_mail_flag));
		map.put("ifo_order_unexec_mail_flag",new Integer(ifo_order_unexec_mail_flag));
		map.put("information_mail_flag",new Integer(information_mail_flag));
		map.put("transfer_count",new Integer(transfer_count));
		map.put("top_page_id",top_page_id);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( equity_order_exe_mail_flag_is_modified )
			map.put("equity_order_exe_mail_flag",new Integer(equity_order_exe_mail_flag));
		if ( equity_order_unexec_mail_flag_is_modified )
			map.put("equity_order_unexec_mail_flag",new Integer(equity_order_unexec_mail_flag));
		if ( ifo_order_exec_mail_flag_is_modified )
			map.put("ifo_order_exec_mail_flag",new Integer(ifo_order_exec_mail_flag));
		if ( ifo_order_unexec_mail_flag_is_modified )
			map.put("ifo_order_unexec_mail_flag",new Integer(ifo_order_unexec_mail_flag));
		if ( information_mail_flag_is_modified )
			map.put("information_mail_flag",new Integer(information_mail_flag));
		if ( transfer_count_is_modified )
			map.put("transfer_count",new Integer(transfer_count));
		if ( top_page_id_is_modified )
			map.put("top_page_id",top_page_id);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( equity_order_exe_mail_flag_is_set )
				map.put("equity_order_exe_mail_flag",new Integer(equity_order_exe_mail_flag));
			if ( equity_order_unexec_mail_flag_is_set )
				map.put("equity_order_unexec_mail_flag",new Integer(equity_order_unexec_mail_flag));
			if ( ifo_order_exec_mail_flag_is_set )
				map.put("ifo_order_exec_mail_flag",new Integer(ifo_order_exec_mail_flag));
			if ( ifo_order_unexec_mail_flag_is_set )
				map.put("ifo_order_unexec_mail_flag",new Integer(ifo_order_unexec_mail_flag));
			if ( information_mail_flag_is_set )
				map.put("information_mail_flag",new Integer(information_mail_flag));
			if ( transfer_count_is_set )
				map.put("transfer_count",new Integer(transfer_count));
			if ( top_page_id_is_set )
				map.put("top_page_id",top_page_id);
			map.put("created_timestamp",created_timestamp);
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
   * <em>equity_order_exe_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getEquityOrderExeMailFlag()
  {
    return equity_order_exe_mail_flag;
  }


  /** 
   * <em>equity_order_exe_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEquityOrderExeMailFlagIsSet() {
    return equity_order_exe_mail_flag_is_set;
  }


  /** 
   * <em>equity_order_exe_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEquityOrderExeMailFlagIsModified() {
    return equity_order_exe_mail_flag_is_modified;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getEquityOrderUnexecMailFlag()
  {
    return equity_order_unexec_mail_flag;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEquityOrderUnexecMailFlagIsSet() {
    return equity_order_unexec_mail_flag_is_set;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEquityOrderUnexecMailFlagIsModified() {
    return equity_order_unexec_mail_flag_is_modified;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getIfoOrderExecMailFlag()
  {
    return ifo_order_exec_mail_flag;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoOrderExecMailFlagIsSet() {
    return ifo_order_exec_mail_flag_is_set;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoOrderExecMailFlagIsModified() {
    return ifo_order_exec_mail_flag_is_modified;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getIfoOrderUnexecMailFlag()
  {
    return ifo_order_unexec_mail_flag;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoOrderUnexecMailFlagIsSet() {
    return ifo_order_unexec_mail_flag_is_set;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIfoOrderUnexecMailFlagIsModified() {
    return ifo_order_unexec_mail_flag_is_modified;
  }


  /** 
   * <em>information_mail_flag</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getInformationMailFlag()
  {
    return information_mail_flag;
  }


  /** 
   * <em>information_mail_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInformationMailFlagIsSet() {
    return information_mail_flag_is_set;
  }


  /** 
   * <em>information_mail_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getInformationMailFlagIsModified() {
    return information_mail_flag_is_modified;
  }


  /** 
   * <em>transfer_count</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getTransferCount()
  {
    return transfer_count;
  }


  /** 
   * <em>transfer_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferCountIsSet() {
    return transfer_count_is_set;
  }


  /** 
   * <em>transfer_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransferCountIsModified() {
    return transfer_count_is_modified;
  }


  /** 
   * <em>top_page_id</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTopPageId()
  {
    return top_page_id;
  }


  /** 
   * <em>top_page_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTopPageIdIsSet() {
    return top_page_id_is_set;
  }


  /** 
   * <em>top_page_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTopPageIdIsModified() {
    return top_page_id_is_modified;
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
    return new BatchBranchPK(institution_code, branch_code);
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
   * <em>equity_order_exe_mail_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_equityOrderExeMailFlag <em>equity_order_exe_mail_flag</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setEquityOrderExeMailFlag( int p_equityOrderExeMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_order_exe_mail_flag = p_equityOrderExeMailFlag;
    equity_order_exe_mail_flag_is_set = true;
    equity_order_exe_mail_flag_is_modified = true;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_equityOrderUnexecMailFlag <em>equity_order_unexec_mail_flag</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setEquityOrderUnexecMailFlag( int p_equityOrderUnexecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_order_unexec_mail_flag = p_equityOrderUnexecMailFlag;
    equity_order_unexec_mail_flag_is_set = true;
    equity_order_unexec_mail_flag_is_modified = true;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoOrderExecMailFlag <em>ifo_order_exec_mail_flag</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setIfoOrderExecMailFlag( int p_ifoOrderExecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_order_exec_mail_flag = p_ifoOrderExecMailFlag;
    ifo_order_exec_mail_flag_is_set = true;
    ifo_order_exec_mail_flag_is_modified = true;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ifoOrderUnexecMailFlag <em>ifo_order_unexec_mail_flag</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setIfoOrderUnexecMailFlag( int p_ifoOrderUnexecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_order_unexec_mail_flag = p_ifoOrderUnexecMailFlag;
    ifo_order_unexec_mail_flag_is_set = true;
    ifo_order_unexec_mail_flag_is_modified = true;
  }


  /** 
   * <em>information_mail_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_informationMailFlag <em>information_mail_flag</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public final void setInformationMailFlag( int p_informationMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    information_mail_flag = p_informationMailFlag;
    information_mail_flag_is_set = true;
    information_mail_flag_is_modified = true;
  }


  /** 
   * <em>transfer_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transferCount <em>transfer_count</em>�J�����̒l������킷2���ȉ���int�l 
   */
  public final void setTransferCount( int p_transferCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_count = p_transferCount;
    transfer_count_is_set = true;
    transfer_count_is_modified = true;
  }


  /** 
   * <em>top_page_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_topPageId <em>top_page_id</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setTopPageId( String p_topPageId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    top_page_id = p_topPageId;
    top_page_id_is_set = true;
    top_page_id_is_modified = true;
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
            case 'e':
                if ( name.equals("equity_order_exe_mail_flag") ) {
                    return new Integer( equity_order_exe_mail_flag );
                }
                else if ( name.equals("equity_order_unexec_mail_flag") ) {
                    return new Integer( equity_order_unexec_mail_flag );
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ifo_order_exec_mail_flag") ) {
                    return new Integer( ifo_order_exec_mail_flag );
                }
                else if ( name.equals("ifo_order_unexec_mail_flag") ) {
                    return new Integer( ifo_order_unexec_mail_flag );
                }
                else if ( name.equals("information_mail_flag") ) {
                    return new Integer( information_mail_flag );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 't':
                if ( name.equals("transfer_count") ) {
                    return new Integer( transfer_count );
                }
                else if ( name.equals("top_page_id") ) {
                    return this.top_page_id;
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
                    if ( value != null )
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
                if ( name.equals("equity_order_exe_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'equity_order_exe_mail_flag' must be Integer: '"+value+"' is not." );
                    this.equity_order_exe_mail_flag = ((Integer) value).intValue();
                    if (this.equity_order_exe_mail_flag_is_set)
                        this.equity_order_exe_mail_flag_is_modified = true;
                    this.equity_order_exe_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("equity_order_unexec_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'equity_order_unexec_mail_flag' must be Integer: '"+value+"' is not." );
                    this.equity_order_unexec_mail_flag = ((Integer) value).intValue();
                    if (this.equity_order_unexec_mail_flag_is_set)
                        this.equity_order_unexec_mail_flag_is_modified = true;
                    this.equity_order_unexec_mail_flag_is_set = true;
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
                else if ( name.equals("ifo_order_exec_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ifo_order_exec_mail_flag' must be Integer: '"+value+"' is not." );
                    this.ifo_order_exec_mail_flag = ((Integer) value).intValue();
                    if (this.ifo_order_exec_mail_flag_is_set)
                        this.ifo_order_exec_mail_flag_is_modified = true;
                    this.ifo_order_exec_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_order_unexec_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ifo_order_unexec_mail_flag' must be Integer: '"+value+"' is not." );
                    this.ifo_order_unexec_mail_flag = ((Integer) value).intValue();
                    if (this.ifo_order_unexec_mail_flag_is_set)
                        this.ifo_order_unexec_mail_flag_is_modified = true;
                    this.ifo_order_unexec_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("information_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'information_mail_flag' must be Integer: '"+value+"' is not." );
                    this.information_mail_flag = ((Integer) value).intValue();
                    if (this.information_mail_flag_is_set)
                        this.information_mail_flag_is_modified = true;
                    this.information_mail_flag_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transfer_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'transfer_count' must be Integer: '"+value+"' is not." );
                    this.transfer_count = ((Integer) value).intValue();
                    if (this.transfer_count_is_set)
                        this.transfer_count_is_modified = true;
                    this.transfer_count_is_set = true;
                    return;
                }
                else if ( name.equals("top_page_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'top_page_id' must be String: '"+value+"' is not." );
                    this.top_page_id = (String) value;
                    if (this.top_page_id_is_set)
                        this.top_page_id_is_modified = true;
                    this.top_page_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
