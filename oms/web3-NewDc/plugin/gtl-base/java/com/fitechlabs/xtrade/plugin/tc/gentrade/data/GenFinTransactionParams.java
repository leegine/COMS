head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.34.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	GenFinTransactionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * gen_fin_transaction�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link GenFinTransactionRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link GenFinTransactionParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link GenFinTransactionParams}��{@@link GenFinTransactionRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see GenFinTransactionPK 
 * @@see GenFinTransactionRow 
 */
public class GenFinTransactionParams extends Params implements GenFinTransactionRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "gen_fin_transaction";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = GenFinTransactionRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return GenFinTransactionRow.TYPE;
  }


  /** 
   * <em>transaction_id</em>�J�����̒l 
   */
  public  long  transaction_id;

  /** 
   * <em>delivery_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>transaction_description</em>�J�����̒l 
   */
  public  String  transaction_description;

  /** 
   * <em>net_amount</em>�J�����̒l 
   */
  public  double  net_amount;

  /** 
   * <em>account_id</em>�J�����̒l 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>�J�����̒l 
   */
  public  long  sub_account_id;

  /** 
   * <em>trade_module_name</em>�J�����̒l 
   */
  public  String  trade_module_name;

  /** 
   * <em>transaction_date</em>�J�����̒l 
   */
  public  java.sql.Timestamp  transaction_date;

  /** 
   * <em>transaction_type</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType  transaction_type;

  /** 
   * <em>delete_flag</em>�J�����̒l 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

  /** 
   * <em>biz_date</em>�J�����̒l 
   */
  public  String  biz_date;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean transaction_id_is_set = false;
  private boolean transaction_id_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean transaction_description_is_set = false;
  private boolean transaction_description_is_modified = false;
  private boolean net_amount_is_set = false;
  private boolean net_amount_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean trade_module_name_is_set = false;
  private boolean trade_module_name_is_modified = false;
  private boolean transaction_date_is_set = false;
  private boolean transaction_date_is_modified = false;
  private boolean transaction_type_is_set = false;
  private boolean transaction_type_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean biz_date_is_set = false;
  private boolean biz_date_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "transaction_id=" + transaction_id
      + "," + "delivery_date=" +delivery_date
      + "," + "transaction_description=" +transaction_description
      + "," + "net_amount=" +net_amount
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "trade_module_name=" +trade_module_name
      + "," + "transaction_date=" +transaction_date
      + "," + "transaction_type=" +transaction_type
      + "," + "delete_flag=" +delete_flag
      + "," + "biz_date=" +biz_date
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��GenFinTransactionParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public GenFinTransactionParams() {
    transaction_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���GenFinTransactionRow�I�u�W�F�N�g�̒l�𗘗p����GenFinTransactionParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����GenFinTransactionRow�I�u�W�F�N�g 
   */
  public GenFinTransactionParams( GenFinTransactionRow p_row )
  {
    transaction_id = p_row.getTransactionId();
    transaction_id_is_set = p_row.getTransactionIdIsSet();
    transaction_id_is_modified = p_row.getTransactionIdIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    transaction_description = p_row.getTransactionDescription();
    transaction_description_is_set = p_row.getTransactionDescriptionIsSet();
    transaction_description_is_modified = p_row.getTransactionDescriptionIsModified();
    net_amount = p_row.getNetAmount();
    net_amount_is_set = p_row.getNetAmountIsSet();
    net_amount_is_modified = p_row.getNetAmountIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    trade_module_name = p_row.getTradeModuleName();
    trade_module_name_is_set = p_row.getTradeModuleNameIsSet();
    trade_module_name_is_modified = p_row.getTradeModuleNameIsModified();
    transaction_date = p_row.getTransactionDate();
    transaction_date_is_set = p_row.getTransactionDateIsSet();
    transaction_date_is_modified = p_row.getTransactionDateIsModified();
    transaction_type = p_row.getTransactionType();
    transaction_type_is_set = p_row.getTransactionTypeIsSet();
    transaction_type_is_modified = p_row.getTransactionTypeIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    biz_date_is_modified = p_row.getBizDateIsModified();
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
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      transaction_description_is_set = true;
      transaction_description_is_modified = true;
      net_amount_is_set = true;
      net_amount_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      trade_module_name_is_set = true;
      trade_module_name_is_modified = true;
      transaction_date_is_set = true;
      transaction_date_is_modified = true;
      transaction_type_is_set = true;
      transaction_type_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      biz_date_is_set = true;
      biz_date_is_modified = true;
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
    if ( !( other instanceof GenFinTransactionRow ) )
       return false;
    return fieldsEqual( (GenFinTransactionRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�GenFinTransactionRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( GenFinTransactionRow row )
  {
    if ( transaction_id != row.getTransactionId() )
      return false;
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( transaction_description == null ) {
      if ( row.getTransactionDescription() != null )
        return false;
    } else if ( !transaction_description.equals( row.getTransactionDescription() ) ) {
        return false;
    }
    if ( net_amount != row.getNetAmount() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( trade_module_name == null ) {
      if ( row.getTradeModuleName() != null )
        return false;
    } else if ( !trade_module_name.equals( row.getTradeModuleName() ) ) {
        return false;
    }
    if ( transaction_date == null ) {
      if ( row.getTransactionDate() != null )
        return false;
    } else if ( !transaction_date.equals( row.getTransactionDate() ) ) {
        return false;
    }
    if ( transaction_type == null ) {
      if ( row.getTransactionType() != null )
        return false;
    } else if ( !transaction_type.equals( row.getTransactionType() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
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
      return  ((int) transaction_id)
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (transaction_description!=null? transaction_description.hashCode(): 0) 
        + ((int) net_amount)
        + ((int) account_id)
        + ((int) sub_account_id)
        + (trade_module_name!=null? trade_module_name.hashCode(): 0) 
        + (transaction_date!=null? transaction_date.hashCode(): 0) 
        + (transaction_type!=null? transaction_type.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
		if ( !transaction_description_is_set )
			throw new IllegalArgumentException("non-nullable field 'transaction_description' must be set before inserting.");
		if ( !net_amount_is_set )
			throw new IllegalArgumentException("non-nullable field 'net_amount' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !transaction_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'transaction_date' must be set before inserting.");
		if ( !transaction_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'transaction_type' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("transaction_id",new Long(transaction_id));
		map.put("delivery_date",delivery_date);
		map.put("transaction_description",transaction_description);
		map.put("net_amount",new Double(net_amount));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		if ( trade_module_name != null )
			map.put("trade_module_name",trade_module_name);
		map.put("transaction_date",transaction_date);
		map.put("transaction_type",transaction_type);
		if ( delete_flag_is_set )
			map.put("delete_flag",delete_flag);
		map.put("biz_date",biz_date);
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
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( transaction_description_is_modified )
			map.put("transaction_description",transaction_description);
		if ( net_amount_is_modified )
			map.put("net_amount",new Double(net_amount));
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( trade_module_name_is_modified )
			map.put("trade_module_name",trade_module_name);
		if ( transaction_date_is_modified )
			map.put("transaction_date",transaction_date);
		if ( transaction_type_is_modified )
			map.put("transaction_type",transaction_type);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( biz_date_is_modified )
			map.put("biz_date",biz_date);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			if ( transaction_description_is_set )
				map.put("transaction_description",transaction_description);
			if ( net_amount_is_set )
				map.put("net_amount",new Double(net_amount));
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			map.put("trade_module_name",trade_module_name);
			if ( transaction_date_is_set )
				map.put("transaction_date",transaction_date);
			if ( transaction_type_is_set )
				map.put("transaction_type",transaction_type);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
			if ( biz_date_is_set )
				map.put("biz_date",biz_date);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>transaction_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getTransactionId()
  {
    return transaction_id;
  }


  /** 
   * <em>transaction_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionIdIsSet() {
    return transaction_id_is_set;
  }


  /** 
   * <em>transaction_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionIdIsModified() {
    return transaction_id_is_modified;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>transaction_description</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTransactionDescription()
  {
    return transaction_description;
  }


  /** 
   * <em>transaction_description</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionDescriptionIsSet() {
    return transaction_description_is_set;
  }


  /** 
   * <em>transaction_description</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionDescriptionIsModified() {
    return transaction_description_is_modified;
  }


  /** 
   * <em>net_amount</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getNetAmount()
  {
    return net_amount;
  }


  /** 
   * <em>net_amount</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNetAmountIsSet() {
    return net_amount_is_set;
  }


  /** 
   * <em>net_amount</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getNetAmountIsModified() {
    return net_amount_is_modified;
  }


  /** 
   * <em>account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
  }


  /** 
   * <em>trade_module_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTradeModuleName()
  {
    return trade_module_name;
  }


  /** 
   * <em>trade_module_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeModuleNameIsSet() {
    return trade_module_name_is_set;
  }


  /** 
   * <em>trade_module_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTradeModuleNameIsModified() {
    return trade_module_name_is_modified;
  }


  /** 
   * <em>transaction_date</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getTransactionDate()
  {
    return transaction_date;
  }


  /** 
   * <em>transaction_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionDateIsSet() {
    return transaction_date_is_set;
  }


  /** 
   * <em>transaction_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionDateIsModified() {
    return transaction_date_is_modified;
  }


  /** 
   * <em>transaction_type</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType getTransactionType()
  {
    return transaction_type;
  }


  /** 
   * <em>transaction_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionTypeIsSet() {
    return transaction_type_is_set;
  }


  /** 
   * <em>transaction_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTransactionTypeIsModified() {
    return transaction_type_is_modified;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l���擾���܂��B
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�̒l 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
  {
    return delete_flag;
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
   * <em>biz_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBizDate()
  {
    return biz_date;
  }


  /** 
   * <em>biz_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizDateIsSet() {
    return biz_date_is_set;
  }


  /** 
   * <em>biz_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBizDateIsModified() {
    return biz_date_is_modified;
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
    return new GenFinTransactionPK(transaction_id);
  }


  /** 
   * <em>transaction_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transactionId <em>transaction_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setTransactionId( long p_transactionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_id = p_transactionId;
    transaction_id_is_set = true;
    transaction_id_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deliveryDate <em>delivery_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>delivery_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>transaction_description</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transactionDescription <em>transaction_description</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setTransactionDescription( String p_transactionDescription )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_description = p_transactionDescription;
    transaction_description_is_set = true;
    transaction_description_is_modified = true;
  }


  /** 
   * <em>net_amount</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_netAmount <em>net_amount</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setNetAmount( double p_netAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount = p_netAmount;
    net_amount_is_set = true;
    net_amount_is_modified = true;
  }


  /** 
   * <em>account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>sub_account_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subAccountId <em>sub_account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
  }


  /** 
   * <em>trade_module_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_tradeModuleName <em>trade_module_name</em>�J�����̒l������킷100���ȉ���String�l 
   */
  public final void setTradeModuleName( String p_tradeModuleName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_module_name = p_tradeModuleName;
    trade_module_name_is_set = true;
    trade_module_name_is_modified = true;
  }


  /** 
   * <em>transaction_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transactionDate <em>transaction_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setTransactionDate( java.sql.Timestamp p_transactionDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_date = p_transactionDate;
    transaction_date_is_set = true;
    transaction_date_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>transaction_date</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setTransactionDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transaction_date_is_set = true;
    transaction_date_is_modified = true;
  }


  /** 
   * <em>transaction_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_transactionType <em>transaction_type</em>�J�����̒l������킷6���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType�l 
   */
  public final void setTransactionType( com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType p_transactionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_type = p_transactionType;
    transaction_type_is_set = true;
    transaction_type_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_deleteFlag <em>delete_flag</em>�J�����̒l������킷1���ȉ���com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum�l 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
  }


  /** 
   * <em>biz_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bizDate <em>biz_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setBizDate( String p_bizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date = p_bizDate;
    biz_date_is_set = true;
    biz_date_is_modified = true;
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
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'b':
                if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("net_amount") ) {
                    return new Double( net_amount );
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                break;
            case 't':
                if ( name.equals("transaction_id") ) {
                    return new Long( transaction_id );
                }
                else if ( name.equals("transaction_description") ) {
                    return this.transaction_description;
                }
                else if ( name.equals("trade_module_name") ) {
                    return this.trade_module_name;
                }
                else if ( name.equals("transaction_date") ) {
                    return this.transaction_date;
                }
                else if ( name.equals("transaction_type") ) {
                    return this.transaction_type;
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
                break;
            case 'b':
                if ( name.equals("biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be String: '"+value+"' is not." );
                    this.biz_date = (String) value;
                    if (this.biz_date_is_set)
                        this.biz_date_is_modified = true;
                    this.biz_date_is_set = true;
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
                if ( name.equals("delivery_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
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
                if ( name.equals("net_amount") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'net_amount' must be Double: '"+value+"' is not." );
                    this.net_amount = ((Double) value).doubleValue();
                    if (this.net_amount_is_set)
                        this.net_amount_is_modified = true;
                    this.net_amount_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transaction_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'transaction_id' must be Long: '"+value+"' is not." );
                    this.transaction_id = ((Long) value).longValue();
                    if (this.transaction_id_is_set)
                        this.transaction_id_is_modified = true;
                    this.transaction_id_is_set = true;
                    return;
                }
                else if ( name.equals("transaction_description") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transaction_description' must be String: '"+value+"' is not." );
                    this.transaction_description = (String) value;
                    if (this.transaction_description_is_set)
                        this.transaction_description_is_modified = true;
                    this.transaction_description_is_set = true;
                    return;
                }
                else if ( name.equals("trade_module_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_module_name' must be String: '"+value+"' is not." );
                    this.trade_module_name = (String) value;
                    if (this.trade_module_name_is_set)
                        this.trade_module_name_is_modified = true;
                    this.trade_module_name_is_set = true;
                    return;
                }
                else if ( name.equals("transaction_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transaction_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transaction_date = (java.sql.Timestamp) value;
                    if (this.transaction_date_is_set)
                        this.transaction_date_is_modified = true;
                    this.transaction_date_is_set = true;
                    return;
                }
                else if ( name.equals("transaction_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType) )
                        throw new IllegalArgumentException( "value for 'transaction_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType: '"+value+"' is not." );
                    this.transaction_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType) value;
                    if (this.transaction_type_is_set)
                        this.transaction_type_is_modified = true;
                    this.transaction_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
