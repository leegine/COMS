head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.50.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ipo_bookbuilding�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link IpoBookbuildingRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link IpoBookbuildingParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link IpoBookbuildingParams}��{@@link IpoBookbuildingRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoBookbuildingPK 
 * @@see IpoBookbuildingRow 
 */
public class IpoBookbuildingParams extends Params implements IpoBookbuildingRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "ipo_bookbuilding";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = IpoBookbuildingRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return IpoBookbuildingRow.TYPE;
  }


  /** 
   * <em>ipo_product_id</em>�J�����̒l 
   */
  public  long  ipo_product_id;

  /** 
   * <em>bb_div</em>�J�����̒l 
   */
  public  String  bb_div;

  /** 
   * <em>bb_seq</em>�J�����̒l 
   */
  public  long  bb_seq;

  /** 
   * <em>branch_id</em>�J�����̒l 
   */
  public  long  branch_id;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>bb_quantity_all</em>�J�����̒l 
   */
  public  long  bb_quantity_all;

  /** 
   * <em>bb_quantity_loop</em>�J�����̒l 
   */
  public  long  bb_quantity_loop;

  /** 
   * <em>sub_bb_quantity_all</em>�J�����̒l 
   */
  public  Long  sub_bb_quantity_all;

  /** 
   * <em>sub_bb_quantity_loop</em>�J�����̒l 
   */
  public  Long  sub_bb_quantity_loop;

  /** 
   * <em>process</em>�J�����̒l 
   */
  public  String  process;

  /** 
   * <em>status</em>�J�����̒l 
   */
  public  String  status;

  /** 
   * <em>bb_result_quantity_sum</em>�J�����̒l 
   */
  public  Long  bb_result_quantity_sum;

  /** 
   * <em>bb_result_acc_count</em>�J�����̒l 
   */
  public  Long  bb_result_acc_count;

  /** 
   * <em>bb_result_quantity_max</em>�J�����̒l 
   */
  public  Long  bb_result_quantity_max;

  /** 
   * <em>bb_result_quantity_min</em>�J�����̒l 
   */
  public  Long  bb_result_quantity_min;

  /** 
   * <em>sub_bb_result_quantity_sum</em>�J�����̒l 
   */
  public  Long  sub_bb_result_quantity_sum;

  /** 
   * <em>sub_bb_result_acc_count</em>�J�����̒l 
   */
  public  Long  sub_bb_result_acc_count;

  /** 
   * <em>sub_bb_result_quantity_max</em>�J�����̒l 
   */
  public  Long  sub_bb_result_quantity_max;

  /** 
   * <em>sub_bb_result_quantity_min</em>�J�����̒l 
   */
  public  Long  sub_bb_result_quantity_min;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean ipo_product_id_is_set = false;
  private boolean ipo_product_id_is_modified = false;
  private boolean bb_div_is_set = false;
  private boolean bb_div_is_modified = false;
  private boolean bb_seq_is_set = false;
  private boolean bb_seq_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean bb_quantity_all_is_set = false;
  private boolean bb_quantity_all_is_modified = false;
  private boolean bb_quantity_loop_is_set = false;
  private boolean bb_quantity_loop_is_modified = false;
  private boolean sub_bb_quantity_all_is_set = false;
  private boolean sub_bb_quantity_all_is_modified = false;
  private boolean sub_bb_quantity_loop_is_set = false;
  private boolean sub_bb_quantity_loop_is_modified = false;
  private boolean process_is_set = false;
  private boolean process_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean bb_result_quantity_sum_is_set = false;
  private boolean bb_result_quantity_sum_is_modified = false;
  private boolean bb_result_acc_count_is_set = false;
  private boolean bb_result_acc_count_is_modified = false;
  private boolean bb_result_quantity_max_is_set = false;
  private boolean bb_result_quantity_max_is_modified = false;
  private boolean bb_result_quantity_min_is_set = false;
  private boolean bb_result_quantity_min_is_modified = false;
  private boolean sub_bb_result_quantity_sum_is_set = false;
  private boolean sub_bb_result_quantity_sum_is_modified = false;
  private boolean sub_bb_result_acc_count_is_set = false;
  private boolean sub_bb_result_acc_count_is_modified = false;
  private boolean sub_bb_result_quantity_max_is_set = false;
  private boolean sub_bb_result_quantity_max_is_modified = false;
  private boolean sub_bb_result_quantity_min_is_set = false;
  private boolean sub_bb_result_quantity_min_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "ipo_product_id=" + ipo_product_id
      + "," + "bb_div=" + bb_div
      + "," + "bb_seq=" + bb_seq
      + "," + "branch_id=" + branch_id
      + "," + "product_code=" +product_code
      + "," + "bb_quantity_all=" +bb_quantity_all
      + "," + "bb_quantity_loop=" +bb_quantity_loop
      + "," + "sub_bb_quantity_all=" +sub_bb_quantity_all
      + "," + "sub_bb_quantity_loop=" +sub_bb_quantity_loop
      + "," + "process=" +process
      + "," + "status=" +status
      + "," + "bb_result_quantity_sum=" +bb_result_quantity_sum
      + "," + "bb_result_acc_count=" +bb_result_acc_count
      + "," + "bb_result_quantity_max=" +bb_result_quantity_max
      + "," + "bb_result_quantity_min=" +bb_result_quantity_min
      + "," + "sub_bb_result_quantity_sum=" +sub_bb_result_quantity_sum
      + "," + "sub_bb_result_acc_count=" +sub_bb_result_acc_count
      + "," + "sub_bb_result_quantity_max=" +sub_bb_result_quantity_max
      + "," + "sub_bb_result_quantity_min=" +sub_bb_result_quantity_min
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��IpoBookbuildingParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public IpoBookbuildingParams() {
    ipo_product_id_is_modified = true;
    bb_div_is_modified = true;
    bb_seq_is_modified = true;
    branch_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���IpoBookbuildingRow�I�u�W�F�N�g�̒l�𗘗p����IpoBookbuildingParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����IpoBookbuildingRow�I�u�W�F�N�g 
   */
  public IpoBookbuildingParams( IpoBookbuildingRow p_row )
  {
    ipo_product_id = p_row.getIpoProductId();
    ipo_product_id_is_set = p_row.getIpoProductIdIsSet();
    ipo_product_id_is_modified = p_row.getIpoProductIdIsModified();
    bb_div = p_row.getBbDiv();
    bb_div_is_set = p_row.getBbDivIsSet();
    bb_div_is_modified = p_row.getBbDivIsModified();
    bb_seq = p_row.getBbSeq();
    bb_seq_is_set = p_row.getBbSeqIsSet();
    bb_seq_is_modified = p_row.getBbSeqIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    bb_quantity_all = p_row.getBbQuantityAll();
    bb_quantity_all_is_set = p_row.getBbQuantityAllIsSet();
    bb_quantity_all_is_modified = p_row.getBbQuantityAllIsModified();
    bb_quantity_loop = p_row.getBbQuantityLoop();
    bb_quantity_loop_is_set = p_row.getBbQuantityLoopIsSet();
    bb_quantity_loop_is_modified = p_row.getBbQuantityLoopIsModified();
    if ( !p_row.getSubBbQuantityAllIsNull() )
      sub_bb_quantity_all = new Long( p_row.getSubBbQuantityAll() );
    sub_bb_quantity_all_is_set = p_row.getSubBbQuantityAllIsSet();
    sub_bb_quantity_all_is_modified = p_row.getSubBbQuantityAllIsModified();
    if ( !p_row.getSubBbQuantityLoopIsNull() )
      sub_bb_quantity_loop = new Long( p_row.getSubBbQuantityLoop() );
    sub_bb_quantity_loop_is_set = p_row.getSubBbQuantityLoopIsSet();
    sub_bb_quantity_loop_is_modified = p_row.getSubBbQuantityLoopIsModified();
    process = p_row.getProcess();
    process_is_set = p_row.getProcessIsSet();
    process_is_modified = p_row.getProcessIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    if ( !p_row.getBbResultQuantitySumIsNull() )
      bb_result_quantity_sum = new Long( p_row.getBbResultQuantitySum() );
    bb_result_quantity_sum_is_set = p_row.getBbResultQuantitySumIsSet();
    bb_result_quantity_sum_is_modified = p_row.getBbResultQuantitySumIsModified();
    if ( !p_row.getBbResultAccCountIsNull() )
      bb_result_acc_count = new Long( p_row.getBbResultAccCount() );
    bb_result_acc_count_is_set = p_row.getBbResultAccCountIsSet();
    bb_result_acc_count_is_modified = p_row.getBbResultAccCountIsModified();
    if ( !p_row.getBbResultQuantityMaxIsNull() )
      bb_result_quantity_max = new Long( p_row.getBbResultQuantityMax() );
    bb_result_quantity_max_is_set = p_row.getBbResultQuantityMaxIsSet();
    bb_result_quantity_max_is_modified = p_row.getBbResultQuantityMaxIsModified();
    if ( !p_row.getBbResultQuantityMinIsNull() )
      bb_result_quantity_min = new Long( p_row.getBbResultQuantityMin() );
    bb_result_quantity_min_is_set = p_row.getBbResultQuantityMinIsSet();
    bb_result_quantity_min_is_modified = p_row.getBbResultQuantityMinIsModified();
    if ( !p_row.getSubBbResultQuantitySumIsNull() )
      sub_bb_result_quantity_sum = new Long( p_row.getSubBbResultQuantitySum() );
    sub_bb_result_quantity_sum_is_set = p_row.getSubBbResultQuantitySumIsSet();
    sub_bb_result_quantity_sum_is_modified = p_row.getSubBbResultQuantitySumIsModified();
    if ( !p_row.getSubBbResultAccCountIsNull() )
      sub_bb_result_acc_count = new Long( p_row.getSubBbResultAccCount() );
    sub_bb_result_acc_count_is_set = p_row.getSubBbResultAccCountIsSet();
    sub_bb_result_acc_count_is_modified = p_row.getSubBbResultAccCountIsModified();
    if ( !p_row.getSubBbResultQuantityMaxIsNull() )
      sub_bb_result_quantity_max = new Long( p_row.getSubBbResultQuantityMax() );
    sub_bb_result_quantity_max_is_set = p_row.getSubBbResultQuantityMaxIsSet();
    sub_bb_result_quantity_max_is_modified = p_row.getSubBbResultQuantityMaxIsModified();
    if ( !p_row.getSubBbResultQuantityMinIsNull() )
      sub_bb_result_quantity_min = new Long( p_row.getSubBbResultQuantityMin() );
    sub_bb_result_quantity_min_is_set = p_row.getSubBbResultQuantityMinIsSet();
    sub_bb_result_quantity_min_is_modified = p_row.getSubBbResultQuantityMinIsModified();
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
      product_code_is_set = true;
      product_code_is_modified = true;
      bb_quantity_all_is_set = true;
      bb_quantity_all_is_modified = true;
      bb_quantity_loop_is_set = true;
      bb_quantity_loop_is_modified = true;
      sub_bb_quantity_all_is_set = true;
      sub_bb_quantity_all_is_modified = true;
      sub_bb_quantity_loop_is_set = true;
      sub_bb_quantity_loop_is_modified = true;
      process_is_set = true;
      process_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      bb_result_quantity_sum_is_set = true;
      bb_result_quantity_sum_is_modified = true;
      bb_result_acc_count_is_set = true;
      bb_result_acc_count_is_modified = true;
      bb_result_quantity_max_is_set = true;
      bb_result_quantity_max_is_modified = true;
      bb_result_quantity_min_is_set = true;
      bb_result_quantity_min_is_modified = true;
      sub_bb_result_quantity_sum_is_set = true;
      sub_bb_result_quantity_sum_is_modified = true;
      sub_bb_result_acc_count_is_set = true;
      sub_bb_result_acc_count_is_modified = true;
      sub_bb_result_quantity_max_is_set = true;
      sub_bb_result_quantity_max_is_modified = true;
      sub_bb_result_quantity_min_is_set = true;
      sub_bb_result_quantity_min_is_modified = true;
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
    if ( !( other instanceof IpoBookbuildingRow ) )
       return false;
    return fieldsEqual( (IpoBookbuildingRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�IpoBookbuildingRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( IpoBookbuildingRow row )
  {
    if ( ipo_product_id != row.getIpoProductId() )
      return false;
    if ( bb_div == null ) {
      if ( row.getBbDiv() != null )
        return false;
    } else if ( !bb_div.equals( row.getBbDiv() ) ) {
        return false;
    }
    if ( bb_seq != row.getBbSeq() )
      return false;
    if ( branch_id != row.getBranchId() )
      return false;
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( bb_quantity_all != row.getBbQuantityAll() )
      return false;
    if ( bb_quantity_loop != row.getBbQuantityLoop() )
      return false;
    if ( sub_bb_quantity_all == null ) {
      if ( !row.getSubBbQuantityAllIsNull() )
        return false;
    } else if ( row.getSubBbQuantityAllIsNull() || ( sub_bb_quantity_all.longValue() != row.getSubBbQuantityAll() ) ) {
        return false;
    }
    if ( sub_bb_quantity_loop == null ) {
      if ( !row.getSubBbQuantityLoopIsNull() )
        return false;
    } else if ( row.getSubBbQuantityLoopIsNull() || ( sub_bb_quantity_loop.longValue() != row.getSubBbQuantityLoop() ) ) {
        return false;
    }
    if ( process == null ) {
      if ( row.getProcess() != null )
        return false;
    } else if ( !process.equals( row.getProcess() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( bb_result_quantity_sum == null ) {
      if ( !row.getBbResultQuantitySumIsNull() )
        return false;
    } else if ( row.getBbResultQuantitySumIsNull() || ( bb_result_quantity_sum.longValue() != row.getBbResultQuantitySum() ) ) {
        return false;
    }
    if ( bb_result_acc_count == null ) {
      if ( !row.getBbResultAccCountIsNull() )
        return false;
    } else if ( row.getBbResultAccCountIsNull() || ( bb_result_acc_count.longValue() != row.getBbResultAccCount() ) ) {
        return false;
    }
    if ( bb_result_quantity_max == null ) {
      if ( !row.getBbResultQuantityMaxIsNull() )
        return false;
    } else if ( row.getBbResultQuantityMaxIsNull() || ( bb_result_quantity_max.longValue() != row.getBbResultQuantityMax() ) ) {
        return false;
    }
    if ( bb_result_quantity_min == null ) {
      if ( !row.getBbResultQuantityMinIsNull() )
        return false;
    } else if ( row.getBbResultQuantityMinIsNull() || ( bb_result_quantity_min.longValue() != row.getBbResultQuantityMin() ) ) {
        return false;
    }
    if ( sub_bb_result_quantity_sum == null ) {
      if ( !row.getSubBbResultQuantitySumIsNull() )
        return false;
    } else if ( row.getSubBbResultQuantitySumIsNull() || ( sub_bb_result_quantity_sum.longValue() != row.getSubBbResultQuantitySum() ) ) {
        return false;
    }
    if ( sub_bb_result_acc_count == null ) {
      if ( !row.getSubBbResultAccCountIsNull() )
        return false;
    } else if ( row.getSubBbResultAccCountIsNull() || ( sub_bb_result_acc_count.longValue() != row.getSubBbResultAccCount() ) ) {
        return false;
    }
    if ( sub_bb_result_quantity_max == null ) {
      if ( !row.getSubBbResultQuantityMaxIsNull() )
        return false;
    } else if ( row.getSubBbResultQuantityMaxIsNull() || ( sub_bb_result_quantity_max.longValue() != row.getSubBbResultQuantityMax() ) ) {
        return false;
    }
    if ( sub_bb_result_quantity_min == null ) {
      if ( !row.getSubBbResultQuantityMinIsNull() )
        return false;
    } else if ( row.getSubBbResultQuantityMinIsNull() || ( sub_bb_result_quantity_min.longValue() != row.getSubBbResultQuantityMin() ) ) {
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
      return  ((int) ipo_product_id)
        + (bb_div!=null? bb_div.hashCode(): 0) 
        + ((int) bb_seq)
        + ((int) branch_id)
        + (product_code!=null? product_code.hashCode(): 0) 
        + ((int) bb_quantity_all)
        + ((int) bb_quantity_loop)
        + (sub_bb_quantity_all!=null? sub_bb_quantity_all.hashCode(): 0) 
        + (sub_bb_quantity_loop!=null? sub_bb_quantity_loop.hashCode(): 0) 
        + (process!=null? process.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (bb_result_quantity_sum!=null? bb_result_quantity_sum.hashCode(): 0) 
        + (bb_result_acc_count!=null? bb_result_acc_count.hashCode(): 0) 
        + (bb_result_quantity_max!=null? bb_result_quantity_max.hashCode(): 0) 
        + (bb_result_quantity_min!=null? bb_result_quantity_min.hashCode(): 0) 
        + (sub_bb_result_quantity_sum!=null? sub_bb_result_quantity_sum.hashCode(): 0) 
        + (sub_bb_result_acc_count!=null? sub_bb_result_acc_count.hashCode(): 0) 
        + (sub_bb_result_quantity_max!=null? sub_bb_result_quantity_max.hashCode(): 0) 
        + (sub_bb_result_quantity_min!=null? sub_bb_result_quantity_min.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !bb_quantity_all_is_set )
			throw new IllegalArgumentException("non-nullable field 'bb_quantity_all' must be set before inserting.");
		if ( !bb_quantity_loop_is_set )
			throw new IllegalArgumentException("non-nullable field 'bb_quantity_loop' must be set before inserting.");
		if ( !process_is_set )
			throw new IllegalArgumentException("non-nullable field 'process' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
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
		map.put("ipo_product_id",new Long(ipo_product_id));
		map.put("bb_div",bb_div);
		map.put("bb_seq",new Long(bb_seq));
		map.put("branch_id",new Long(branch_id));
		map.put("product_code",product_code);
		map.put("bb_quantity_all",new Long(bb_quantity_all));
		map.put("bb_quantity_loop",new Long(bb_quantity_loop));
		if ( sub_bb_quantity_all != null )
			map.put("sub_bb_quantity_all",sub_bb_quantity_all);
		if ( sub_bb_quantity_loop != null )
			map.put("sub_bb_quantity_loop",sub_bb_quantity_loop);
		map.put("process",process);
		map.put("status",status);
		if ( bb_result_quantity_sum != null )
			map.put("bb_result_quantity_sum",bb_result_quantity_sum);
		if ( bb_result_acc_count != null )
			map.put("bb_result_acc_count",bb_result_acc_count);
		if ( bb_result_quantity_max != null )
			map.put("bb_result_quantity_max",bb_result_quantity_max);
		if ( bb_result_quantity_min != null )
			map.put("bb_result_quantity_min",bb_result_quantity_min);
		if ( sub_bb_result_quantity_sum != null )
			map.put("sub_bb_result_quantity_sum",sub_bb_result_quantity_sum);
		if ( sub_bb_result_acc_count != null )
			map.put("sub_bb_result_acc_count",sub_bb_result_acc_count);
		if ( sub_bb_result_quantity_max != null )
			map.put("sub_bb_result_quantity_max",sub_bb_result_quantity_max);
		if ( sub_bb_result_quantity_min != null )
			map.put("sub_bb_result_quantity_min",sub_bb_result_quantity_min);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( bb_quantity_all_is_modified )
			map.put("bb_quantity_all",new Long(bb_quantity_all));
		if ( bb_quantity_loop_is_modified )
			map.put("bb_quantity_loop",new Long(bb_quantity_loop));
		if ( sub_bb_quantity_all_is_modified )
			map.put("sub_bb_quantity_all",sub_bb_quantity_all);
		if ( sub_bb_quantity_loop_is_modified )
			map.put("sub_bb_quantity_loop",sub_bb_quantity_loop);
		if ( process_is_modified )
			map.put("process",process);
		if ( status_is_modified )
			map.put("status",status);
		if ( bb_result_quantity_sum_is_modified )
			map.put("bb_result_quantity_sum",bb_result_quantity_sum);
		if ( bb_result_acc_count_is_modified )
			map.put("bb_result_acc_count",bb_result_acc_count);
		if ( bb_result_quantity_max_is_modified )
			map.put("bb_result_quantity_max",bb_result_quantity_max);
		if ( bb_result_quantity_min_is_modified )
			map.put("bb_result_quantity_min",bb_result_quantity_min);
		if ( sub_bb_result_quantity_sum_is_modified )
			map.put("sub_bb_result_quantity_sum",sub_bb_result_quantity_sum);
		if ( sub_bb_result_acc_count_is_modified )
			map.put("sub_bb_result_acc_count",sub_bb_result_acc_count);
		if ( sub_bb_result_quantity_max_is_modified )
			map.put("sub_bb_result_quantity_max",sub_bb_result_quantity_max);
		if ( sub_bb_result_quantity_min_is_modified )
			map.put("sub_bb_result_quantity_min",sub_bb_result_quantity_min);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( product_code_is_set )
				map.put("product_code",product_code);
			if ( bb_quantity_all_is_set )
				map.put("bb_quantity_all",new Long(bb_quantity_all));
			if ( bb_quantity_loop_is_set )
				map.put("bb_quantity_loop",new Long(bb_quantity_loop));
			map.put("sub_bb_quantity_all",sub_bb_quantity_all);
			map.put("sub_bb_quantity_loop",sub_bb_quantity_loop);
			if ( process_is_set )
				map.put("process",process);
			if ( status_is_set )
				map.put("status",status);
			map.put("bb_result_quantity_sum",bb_result_quantity_sum);
			map.put("bb_result_acc_count",bb_result_acc_count);
			map.put("bb_result_quantity_max",bb_result_quantity_max);
			map.put("bb_result_quantity_min",bb_result_quantity_min);
			map.put("sub_bb_result_quantity_sum",sub_bb_result_quantity_sum);
			map.put("sub_bb_result_acc_count",sub_bb_result_acc_count);
			map.put("sub_bb_result_quantity_max",sub_bb_result_quantity_max);
			map.put("sub_bb_result_quantity_min",sub_bb_result_quantity_min);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>ipo_product_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getIpoProductId()
  {
    return ipo_product_id;
  }


  /** 
   * <em>ipo_product_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoProductIdIsSet() {
    return ipo_product_id_is_set;
  }


  /** 
   * <em>ipo_product_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getIpoProductIdIsModified() {
    return ipo_product_id_is_modified;
  }


  /** 
   * <em>bb_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBbDiv()
  {
    return bb_div;
  }


  /** 
   * <em>bb_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbDivIsSet() {
    return bb_div_is_set;
  }


  /** 
   * <em>bb_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbDivIsModified() {
    return bb_div_is_modified;
  }


  /** 
   * <em>bb_seq</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBbSeq()
  {
    return bb_seq;
  }


  /** 
   * <em>bb_seq</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbSeqIsSet() {
    return bb_seq_is_set;
  }


  /** 
   * <em>bb_seq</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbSeqIsModified() {
    return bb_seq_is_modified;
  }


  /** 
   * <em>branch_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>bb_quantity_all</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBbQuantityAll()
  {
    return bb_quantity_all;
  }


  /** 
   * <em>bb_quantity_all</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbQuantityAllIsSet() {
    return bb_quantity_all_is_set;
  }


  /** 
   * <em>bb_quantity_all</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbQuantityAllIsModified() {
    return bb_quantity_all_is_modified;
  }


  /** 
   * <em>bb_quantity_loop</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBbQuantityLoop()
  {
    return bb_quantity_loop;
  }


  /** 
   * <em>bb_quantity_loop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbQuantityLoopIsSet() {
    return bb_quantity_loop_is_set;
  }


  /** 
   * <em>bb_quantity_loop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbQuantityLoopIsModified() {
    return bb_quantity_loop_is_modified;
  }


  /** 
   * <em>sub_bb_quantity_all</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubBbQuantityAll()
  {
    return ( sub_bb_quantity_all==null? ((long)0): sub_bb_quantity_all.longValue() );
  }


  /** 
   * <em>sub_bb_quantity_all</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSubBbQuantityAllIsNull()
  {
    return sub_bb_quantity_all == null;
  }


  /** 
   * <em>sub_bb_quantity_all</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbQuantityAllIsSet() {
    return sub_bb_quantity_all_is_set;
  }


  /** 
   * <em>sub_bb_quantity_all</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbQuantityAllIsModified() {
    return sub_bb_quantity_all_is_modified;
  }


  /** 
   * <em>sub_bb_quantity_loop</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubBbQuantityLoop()
  {
    return ( sub_bb_quantity_loop==null? ((long)0): sub_bb_quantity_loop.longValue() );
  }


  /** 
   * <em>sub_bb_quantity_loop</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSubBbQuantityLoopIsNull()
  {
    return sub_bb_quantity_loop == null;
  }


  /** 
   * <em>sub_bb_quantity_loop</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbQuantityLoopIsSet() {
    return sub_bb_quantity_loop_is_set;
  }


  /** 
   * <em>sub_bb_quantity_loop</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbQuantityLoopIsModified() {
    return sub_bb_quantity_loop_is_modified;
  }


  /** 
   * <em>process</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProcess()
  {
    return process;
  }


  /** 
   * <em>process</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProcessIsSet() {
    return process_is_set;
  }


  /** 
   * <em>process</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProcessIsModified() {
    return process_is_modified;
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
   * <em>bb_result_quantity_sum</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBbResultQuantitySum()
  {
    return ( bb_result_quantity_sum==null? ((long)0): bb_result_quantity_sum.longValue() );
  }


  /** 
   * <em>bb_result_quantity_sum</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBbResultQuantitySumIsNull()
  {
    return bb_result_quantity_sum == null;
  }


  /** 
   * <em>bb_result_quantity_sum</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbResultQuantitySumIsSet() {
    return bb_result_quantity_sum_is_set;
  }


  /** 
   * <em>bb_result_quantity_sum</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbResultQuantitySumIsModified() {
    return bb_result_quantity_sum_is_modified;
  }


  /** 
   * <em>bb_result_acc_count</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBbResultAccCount()
  {
    return ( bb_result_acc_count==null? ((long)0): bb_result_acc_count.longValue() );
  }


  /** 
   * <em>bb_result_acc_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBbResultAccCountIsNull()
  {
    return bb_result_acc_count == null;
  }


  /** 
   * <em>bb_result_acc_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbResultAccCountIsSet() {
    return bb_result_acc_count_is_set;
  }


  /** 
   * <em>bb_result_acc_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbResultAccCountIsModified() {
    return bb_result_acc_count_is_modified;
  }


  /** 
   * <em>bb_result_quantity_max</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBbResultQuantityMax()
  {
    return ( bb_result_quantity_max==null? ((long)0): bb_result_quantity_max.longValue() );
  }


  /** 
   * <em>bb_result_quantity_max</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBbResultQuantityMaxIsNull()
  {
    return bb_result_quantity_max == null;
  }


  /** 
   * <em>bb_result_quantity_max</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbResultQuantityMaxIsSet() {
    return bb_result_quantity_max_is_set;
  }


  /** 
   * <em>bb_result_quantity_max</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbResultQuantityMaxIsModified() {
    return bb_result_quantity_max_is_modified;
  }


  /** 
   * <em>bb_result_quantity_min</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBbResultQuantityMin()
  {
    return ( bb_result_quantity_min==null? ((long)0): bb_result_quantity_min.longValue() );
  }


  /** 
   * <em>bb_result_quantity_min</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBbResultQuantityMinIsNull()
  {
    return bb_result_quantity_min == null;
  }


  /** 
   * <em>bb_result_quantity_min</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbResultQuantityMinIsSet() {
    return bb_result_quantity_min_is_set;
  }


  /** 
   * <em>bb_result_quantity_min</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBbResultQuantityMinIsModified() {
    return bb_result_quantity_min_is_modified;
  }


  /** 
   * <em>sub_bb_result_quantity_sum</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubBbResultQuantitySum()
  {
    return ( sub_bb_result_quantity_sum==null? ((long)0): sub_bb_result_quantity_sum.longValue() );
  }


  /** 
   * <em>sub_bb_result_quantity_sum</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSubBbResultQuantitySumIsNull()
  {
    return sub_bb_result_quantity_sum == null;
  }


  /** 
   * <em>sub_bb_result_quantity_sum</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbResultQuantitySumIsSet() {
    return sub_bb_result_quantity_sum_is_set;
  }


  /** 
   * <em>sub_bb_result_quantity_sum</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbResultQuantitySumIsModified() {
    return sub_bb_result_quantity_sum_is_modified;
  }


  /** 
   * <em>sub_bb_result_acc_count</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubBbResultAccCount()
  {
    return ( sub_bb_result_acc_count==null? ((long)0): sub_bb_result_acc_count.longValue() );
  }


  /** 
   * <em>sub_bb_result_acc_count</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSubBbResultAccCountIsNull()
  {
    return sub_bb_result_acc_count == null;
  }


  /** 
   * <em>sub_bb_result_acc_count</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbResultAccCountIsSet() {
    return sub_bb_result_acc_count_is_set;
  }


  /** 
   * <em>sub_bb_result_acc_count</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbResultAccCountIsModified() {
    return sub_bb_result_acc_count_is_modified;
  }


  /** 
   * <em>sub_bb_result_quantity_max</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubBbResultQuantityMax()
  {
    return ( sub_bb_result_quantity_max==null? ((long)0): sub_bb_result_quantity_max.longValue() );
  }


  /** 
   * <em>sub_bb_result_quantity_max</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSubBbResultQuantityMaxIsNull()
  {
    return sub_bb_result_quantity_max == null;
  }


  /** 
   * <em>sub_bb_result_quantity_max</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbResultQuantityMaxIsSet() {
    return sub_bb_result_quantity_max_is_set;
  }


  /** 
   * <em>sub_bb_result_quantity_max</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbResultQuantityMaxIsModified() {
    return sub_bb_result_quantity_max_is_modified;
  }


  /** 
   * <em>sub_bb_result_quantity_min</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getSubBbResultQuantityMin()
  {
    return ( sub_bb_result_quantity_min==null? ((long)0): sub_bb_result_quantity_min.longValue() );
  }


  /** 
   * <em>sub_bb_result_quantity_min</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getSubBbResultQuantityMinIsNull()
  {
    return sub_bb_result_quantity_min == null;
  }


  /** 
   * <em>sub_bb_result_quantity_min</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbResultQuantityMinIsSet() {
    return sub_bb_result_quantity_min_is_set;
  }


  /** 
   * <em>sub_bb_result_quantity_min</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSubBbResultQuantityMinIsModified() {
    return sub_bb_result_quantity_min_is_modified;
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
    return new IpoBookbuildingPK(ipo_product_id, bb_div, bb_seq, branch_id);
  }


  /** 
   * <em>ipo_product_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_ipoProductId <em>ipo_product_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setIpoProductId( long p_ipoProductId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ipo_product_id = p_ipoProductId;
    ipo_product_id_is_set = true;
    ipo_product_id_is_modified = true;
  }


  /** 
   * <em>bb_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bbDiv <em>bb_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBbDiv( String p_bbDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bb_div = p_bbDiv;
    bb_div_is_set = true;
    bb_div_is_modified = true;
  }


  /** 
   * <em>bb_seq</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bbSeq <em>bb_seq</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBbSeq( long p_bbSeq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bb_seq = p_bbSeq;
    bb_seq_is_set = true;
    bb_seq_is_modified = true;
  }


  /** 
   * <em>branch_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>bb_quantity_all</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bbQuantityAll <em>bb_quantity_all</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBbQuantityAll( long p_bbQuantityAll )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bb_quantity_all = p_bbQuantityAll;
    bb_quantity_all_is_set = true;
    bb_quantity_all_is_modified = true;
  }


  /** 
   * <em>bb_quantity_loop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bbQuantityLoop <em>bb_quantity_loop</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBbQuantityLoop( long p_bbQuantityLoop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bb_quantity_loop = p_bbQuantityLoop;
    bb_quantity_loop_is_set = true;
    bb_quantity_loop_is_modified = true;
  }


  /** 
   * <em>sub_bb_quantity_all</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subBbQuantityAll <em>sub_bb_quantity_all</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubBbQuantityAll( long p_subBbQuantityAll )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_quantity_all = new Long(p_subBbQuantityAll);
    sub_bb_quantity_all_is_set = true;
    sub_bb_quantity_all_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sub_bb_quantity_all</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSubBbQuantityAll( Long p_subBbQuantityAll )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_quantity_all = p_subBbQuantityAll;
    sub_bb_quantity_all_is_set = true;
    sub_bb_quantity_all_is_modified = true;
  }


  /** 
   * <em>sub_bb_quantity_loop</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subBbQuantityLoop <em>sub_bb_quantity_loop</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubBbQuantityLoop( long p_subBbQuantityLoop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_quantity_loop = new Long(p_subBbQuantityLoop);
    sub_bb_quantity_loop_is_set = true;
    sub_bb_quantity_loop_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sub_bb_quantity_loop</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSubBbQuantityLoop( Long p_subBbQuantityLoop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_quantity_loop = p_subBbQuantityLoop;
    sub_bb_quantity_loop_is_set = true;
    sub_bb_quantity_loop_is_modified = true;
  }


  /** 
   * <em>process</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_process <em>process</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setProcess( String p_process )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    process = p_process;
    process_is_set = true;
    process_is_modified = true;
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
   * <em>bb_result_quantity_sum</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bbResultQuantitySum <em>bb_result_quantity_sum</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBbResultQuantitySum( long p_bbResultQuantitySum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bb_result_quantity_sum = new Long(p_bbResultQuantitySum);
    bb_result_quantity_sum_is_set = true;
    bb_result_quantity_sum_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>bb_result_quantity_sum</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBbResultQuantitySum( Long p_bbResultQuantitySum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bb_result_quantity_sum = p_bbResultQuantitySum;
    bb_result_quantity_sum_is_set = true;
    bb_result_quantity_sum_is_modified = true;
  }


  /** 
   * <em>bb_result_acc_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bbResultAccCount <em>bb_result_acc_count</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBbResultAccCount( long p_bbResultAccCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bb_result_acc_count = new Long(p_bbResultAccCount);
    bb_result_acc_count_is_set = true;
    bb_result_acc_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>bb_result_acc_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBbResultAccCount( Long p_bbResultAccCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bb_result_acc_count = p_bbResultAccCount;
    bb_result_acc_count_is_set = true;
    bb_result_acc_count_is_modified = true;
  }


  /** 
   * <em>bb_result_quantity_max</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bbResultQuantityMax <em>bb_result_quantity_max</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBbResultQuantityMax( long p_bbResultQuantityMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bb_result_quantity_max = new Long(p_bbResultQuantityMax);
    bb_result_quantity_max_is_set = true;
    bb_result_quantity_max_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>bb_result_quantity_max</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBbResultQuantityMax( Long p_bbResultQuantityMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bb_result_quantity_max = p_bbResultQuantityMax;
    bb_result_quantity_max_is_set = true;
    bb_result_quantity_max_is_modified = true;
  }


  /** 
   * <em>bb_result_quantity_min</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bbResultQuantityMin <em>bb_result_quantity_min</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBbResultQuantityMin( long p_bbResultQuantityMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bb_result_quantity_min = new Long(p_bbResultQuantityMin);
    bb_result_quantity_min_is_set = true;
    bb_result_quantity_min_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>bb_result_quantity_min</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBbResultQuantityMin( Long p_bbResultQuantityMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bb_result_quantity_min = p_bbResultQuantityMin;
    bb_result_quantity_min_is_set = true;
    bb_result_quantity_min_is_modified = true;
  }


  /** 
   * <em>sub_bb_result_quantity_sum</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subBbResultQuantitySum <em>sub_bb_result_quantity_sum</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubBbResultQuantitySum( long p_subBbResultQuantitySum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_result_quantity_sum = new Long(p_subBbResultQuantitySum);
    sub_bb_result_quantity_sum_is_set = true;
    sub_bb_result_quantity_sum_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sub_bb_result_quantity_sum</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSubBbResultQuantitySum( Long p_subBbResultQuantitySum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_result_quantity_sum = p_subBbResultQuantitySum;
    sub_bb_result_quantity_sum_is_set = true;
    sub_bb_result_quantity_sum_is_modified = true;
  }


  /** 
   * <em>sub_bb_result_acc_count</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subBbResultAccCount <em>sub_bb_result_acc_count</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubBbResultAccCount( long p_subBbResultAccCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_result_acc_count = new Long(p_subBbResultAccCount);
    sub_bb_result_acc_count_is_set = true;
    sub_bb_result_acc_count_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sub_bb_result_acc_count</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSubBbResultAccCount( Long p_subBbResultAccCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_result_acc_count = p_subBbResultAccCount;
    sub_bb_result_acc_count_is_set = true;
    sub_bb_result_acc_count_is_modified = true;
  }


  /** 
   * <em>sub_bb_result_quantity_max</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subBbResultQuantityMax <em>sub_bb_result_quantity_max</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubBbResultQuantityMax( long p_subBbResultQuantityMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_result_quantity_max = new Long(p_subBbResultQuantityMax);
    sub_bb_result_quantity_max_is_set = true;
    sub_bb_result_quantity_max_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sub_bb_result_quantity_max</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSubBbResultQuantityMax( Long p_subBbResultQuantityMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_result_quantity_max = p_subBbResultQuantityMax;
    sub_bb_result_quantity_max_is_set = true;
    sub_bb_result_quantity_max_is_modified = true;
  }


  /** 
   * <em>sub_bb_result_quantity_min</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_subBbResultQuantityMin <em>sub_bb_result_quantity_min</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setSubBbResultQuantityMin( long p_subBbResultQuantityMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_result_quantity_min = new Long(p_subBbResultQuantityMin);
    sub_bb_result_quantity_min_is_set = true;
    sub_bb_result_quantity_min_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>sub_bb_result_quantity_min</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setSubBbResultQuantityMin( Long p_subBbResultQuantityMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sub_bb_result_quantity_min = p_subBbResultQuantityMin;
    sub_bb_result_quantity_min_is_set = true;
    sub_bb_result_quantity_min_is_modified = true;
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
                if ( name.equals("bb_div") ) {
                    return this.bb_div;
                }
                else if ( name.equals("bb_seq") ) {
                    return new Long( bb_seq );
                }
                else if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("bb_quantity_all") ) {
                    return new Long( bb_quantity_all );
                }
                else if ( name.equals("bb_quantity_loop") ) {
                    return new Long( bb_quantity_loop );
                }
                else if ( name.equals("bb_result_quantity_sum") ) {
                    return this.bb_result_quantity_sum;
                }
                else if ( name.equals("bb_result_acc_count") ) {
                    return this.bb_result_acc_count;
                }
                else if ( name.equals("bb_result_quantity_max") ) {
                    return this.bb_result_quantity_max;
                }
                else if ( name.equals("bb_result_quantity_min") ) {
                    return this.bb_result_quantity_min;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("ipo_product_id") ) {
                    return new Long( ipo_product_id );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("process") ) {
                    return this.process;
                }
                break;
            case 's':
                if ( name.equals("sub_bb_quantity_all") ) {
                    return this.sub_bb_quantity_all;
                }
                else if ( name.equals("sub_bb_quantity_loop") ) {
                    return this.sub_bb_quantity_loop;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("sub_bb_result_quantity_sum") ) {
                    return this.sub_bb_result_quantity_sum;
                }
                else if ( name.equals("sub_bb_result_acc_count") ) {
                    return this.sub_bb_result_acc_count;
                }
                else if ( name.equals("sub_bb_result_quantity_max") ) {
                    return this.sub_bb_result_quantity_max;
                }
                else if ( name.equals("sub_bb_result_quantity_min") ) {
                    return this.sub_bb_result_quantity_min;
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
                if ( name.equals("bb_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bb_div' must be String: '"+value+"' is not." );
                    this.bb_div = (String) value;
                    if (this.bb_div_is_set)
                        this.bb_div_is_modified = true;
                    this.bb_div_is_set = true;
                    return;
                }
                else if ( name.equals("bb_seq") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bb_seq' must be Long: '"+value+"' is not." );
                    this.bb_seq = ((Long) value).longValue();
                    if (this.bb_seq_is_set)
                        this.bb_seq_is_modified = true;
                    this.bb_seq_is_set = true;
                    return;
                }
                else if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("bb_quantity_all") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bb_quantity_all' must be Long: '"+value+"' is not." );
                    this.bb_quantity_all = ((Long) value).longValue();
                    if (this.bb_quantity_all_is_set)
                        this.bb_quantity_all_is_modified = true;
                    this.bb_quantity_all_is_set = true;
                    return;
                }
                else if ( name.equals("bb_quantity_loop") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bb_quantity_loop' must be Long: '"+value+"' is not." );
                    this.bb_quantity_loop = ((Long) value).longValue();
                    if (this.bb_quantity_loop_is_set)
                        this.bb_quantity_loop_is_modified = true;
                    this.bb_quantity_loop_is_set = true;
                    return;
                }
                else if ( name.equals("bb_result_quantity_sum") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bb_result_quantity_sum' must be Long: '"+value+"' is not." );
                    this.bb_result_quantity_sum = (Long) value;
                    if (this.bb_result_quantity_sum_is_set)
                        this.bb_result_quantity_sum_is_modified = true;
                    this.bb_result_quantity_sum_is_set = true;
                    return;
                }
                else if ( name.equals("bb_result_acc_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bb_result_acc_count' must be Long: '"+value+"' is not." );
                    this.bb_result_acc_count = (Long) value;
                    if (this.bb_result_acc_count_is_set)
                        this.bb_result_acc_count_is_modified = true;
                    this.bb_result_acc_count_is_set = true;
                    return;
                }
                else if ( name.equals("bb_result_quantity_max") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bb_result_quantity_max' must be Long: '"+value+"' is not." );
                    this.bb_result_quantity_max = (Long) value;
                    if (this.bb_result_quantity_max_is_set)
                        this.bb_result_quantity_max_is_modified = true;
                    this.bb_result_quantity_max_is_set = true;
                    return;
                }
                else if ( name.equals("bb_result_quantity_min") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bb_result_quantity_min' must be Long: '"+value+"' is not." );
                    this.bb_result_quantity_min = (Long) value;
                    if (this.bb_result_quantity_min_is_set)
                        this.bb_result_quantity_min_is_modified = true;
                    this.bb_result_quantity_min_is_set = true;
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
            case 'i':
                if ( name.equals("ipo_product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ipo_product_id' must be Long: '"+value+"' is not." );
                    this.ipo_product_id = ((Long) value).longValue();
                    if (this.ipo_product_id_is_set)
                        this.ipo_product_id_is_modified = true;
                    this.ipo_product_id_is_set = true;
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
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("process") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'process' must be String: '"+value+"' is not." );
                    this.process = (String) value;
                    if (this.process_is_set)
                        this.process_is_modified = true;
                    this.process_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_bb_quantity_all") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_bb_quantity_all' must be Long: '"+value+"' is not." );
                    this.sub_bb_quantity_all = (Long) value;
                    if (this.sub_bb_quantity_all_is_set)
                        this.sub_bb_quantity_all_is_modified = true;
                    this.sub_bb_quantity_all_is_set = true;
                    return;
                }
                else if ( name.equals("sub_bb_quantity_loop") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_bb_quantity_loop' must be Long: '"+value+"' is not." );
                    this.sub_bb_quantity_loop = (Long) value;
                    if (this.sub_bb_quantity_loop_is_set)
                        this.sub_bb_quantity_loop_is_modified = true;
                    this.sub_bb_quantity_loop_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("sub_bb_result_quantity_sum") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_bb_result_quantity_sum' must be Long: '"+value+"' is not." );
                    this.sub_bb_result_quantity_sum = (Long) value;
                    if (this.sub_bb_result_quantity_sum_is_set)
                        this.sub_bb_result_quantity_sum_is_modified = true;
                    this.sub_bb_result_quantity_sum_is_set = true;
                    return;
                }
                else if ( name.equals("sub_bb_result_acc_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_bb_result_acc_count' must be Long: '"+value+"' is not." );
                    this.sub_bb_result_acc_count = (Long) value;
                    if (this.sub_bb_result_acc_count_is_set)
                        this.sub_bb_result_acc_count_is_modified = true;
                    this.sub_bb_result_acc_count_is_set = true;
                    return;
                }
                else if ( name.equals("sub_bb_result_quantity_max") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_bb_result_quantity_max' must be Long: '"+value+"' is not." );
                    this.sub_bb_result_quantity_max = (Long) value;
                    if (this.sub_bb_result_quantity_max_is_set)
                        this.sub_bb_result_quantity_max_is_modified = true;
                    this.sub_bb_result_quantity_max_is_set = true;
                    return;
                }
                else if ( name.equals("sub_bb_result_quantity_min") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_bb_result_quantity_min' must be Long: '"+value+"' is not." );
                    this.sub_bb_result_quantity_min = (Long) value;
                    if (this.sub_bb_result_quantity_min_is_set)
                        this.sub_bb_result_quantity_min_is_modified = true;
                    this.sub_bb_result_quantity_min_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
