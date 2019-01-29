head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCondSonarParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mutual_fund_inst_cond_sonar�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link MutualFundInstCondSonarRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link MutualFundInstCondSonarParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link MutualFundInstCondSonarParams}��{@@link MutualFundInstCondSonarRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundInstCondSonarPK 
 * @@see MutualFundInstCondSonarRow 
 */
public class MutualFundInstCondSonarParams extends Params implements MutualFundInstCondSonarRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "mutual_fund_inst_cond_sonar";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = MutualFundInstCondSonarRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return MutualFundInstCondSonarRow.TYPE;
  }


  /** 
   * <em>institution_code</em>�J�����̒l 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>reg_type</em>�J�����̒l 
   */
  public  String  reg_type;

  /** 
   * <em>order_accept_limit_time_usual</em>�J�����̒l 
   */
  public  String  order_accept_limit_time_usual;

  /** 
   * <em>order_accept_limit_time_half</em>�J�����̒l 
   */
  public  String  order_accept_limit_time_half;

  /** 
   * <em>buy_forbid_div</em>�J�����̒l 
   */
  public  String  buy_forbid_div;

  /** 
   * <em>buy_forbid_end_date</em>�J�����̒l 
   */
  public  String  buy_forbid_end_date;

  /** 
   * <em>sell_forbid_end_date</em>�J�����̒l 
   */
  public  String  sell_forbid_end_date;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean reg_type_is_set = false;
  private boolean reg_type_is_modified = false;
  private boolean order_accept_limit_time_usual_is_set = false;
  private boolean order_accept_limit_time_usual_is_modified = false;
  private boolean order_accept_limit_time_half_is_set = false;
  private boolean order_accept_limit_time_half_is_modified = false;
  private boolean buy_forbid_div_is_set = false;
  private boolean buy_forbid_div_is_modified = false;
  private boolean buy_forbid_end_date_is_set = false;
  private boolean buy_forbid_end_date_is_modified = false;
  private boolean sell_forbid_end_date_is_set = false;
  private boolean sell_forbid_end_date_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "product_code=" + product_code
      + "," + "reg_type=" +reg_type
      + "," + "order_accept_limit_time_usual=" +order_accept_limit_time_usual
      + "," + "order_accept_limit_time_half=" +order_accept_limit_time_half
      + "," + "buy_forbid_div=" +buy_forbid_div
      + "," + "buy_forbid_end_date=" +buy_forbid_end_date
      + "," + "sell_forbid_end_date=" +sell_forbid_end_date
      + "}";
  }


  /** 
   * �l�����ݒ��MutualFundInstCondSonarParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public MutualFundInstCondSonarParams() {
    institution_code_is_modified = true;
    product_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���MutualFundInstCondSonarRow�I�u�W�F�N�g�̒l�𗘗p����MutualFundInstCondSonarParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����MutualFundInstCondSonarRow�I�u�W�F�N�g 
   */
  public MutualFundInstCondSonarParams( MutualFundInstCondSonarRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    reg_type = p_row.getRegType();
    reg_type_is_set = p_row.getRegTypeIsSet();
    reg_type_is_modified = p_row.getRegTypeIsModified();
    order_accept_limit_time_usual = p_row.getOrderAcceptLimitTimeUsual();
    order_accept_limit_time_usual_is_set = p_row.getOrderAcceptLimitTimeUsualIsSet();
    order_accept_limit_time_usual_is_modified = p_row.getOrderAcceptLimitTimeUsualIsModified();
    order_accept_limit_time_half = p_row.getOrderAcceptLimitTimeHalf();
    order_accept_limit_time_half_is_set = p_row.getOrderAcceptLimitTimeHalfIsSet();
    order_accept_limit_time_half_is_modified = p_row.getOrderAcceptLimitTimeHalfIsModified();
    buy_forbid_div = p_row.getBuyForbidDiv();
    buy_forbid_div_is_set = p_row.getBuyForbidDivIsSet();
    buy_forbid_div_is_modified = p_row.getBuyForbidDivIsModified();
    buy_forbid_end_date = p_row.getBuyForbidEndDate();
    buy_forbid_end_date_is_set = p_row.getBuyForbidEndDateIsSet();
    buy_forbid_end_date_is_modified = p_row.getBuyForbidEndDateIsModified();
    sell_forbid_end_date = p_row.getSellForbidEndDate();
    sell_forbid_end_date_is_set = p_row.getSellForbidEndDateIsSet();
    sell_forbid_end_date_is_modified = p_row.getSellForbidEndDateIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      reg_type_is_set = true;
      reg_type_is_modified = true;
      order_accept_limit_time_usual_is_set = true;
      order_accept_limit_time_usual_is_modified = true;
      order_accept_limit_time_half_is_set = true;
      order_accept_limit_time_half_is_modified = true;
      buy_forbid_div_is_set = true;
      buy_forbid_div_is_modified = true;
      buy_forbid_end_date_is_set = true;
      buy_forbid_end_date_is_modified = true;
      sell_forbid_end_date_is_set = true;
      sell_forbid_end_date_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MutualFundInstCondSonarRow ) )
       return false;
    return fieldsEqual( (MutualFundInstCondSonarRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�MutualFundInstCondSonarRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( MutualFundInstCondSonarRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( reg_type == null ) {
      if ( row.getRegType() != null )
        return false;
    } else if ( !reg_type.equals( row.getRegType() ) ) {
        return false;
    }
    if ( order_accept_limit_time_usual == null ) {
      if ( row.getOrderAcceptLimitTimeUsual() != null )
        return false;
    } else if ( !order_accept_limit_time_usual.equals( row.getOrderAcceptLimitTimeUsual() ) ) {
        return false;
    }
    if ( order_accept_limit_time_half == null ) {
      if ( row.getOrderAcceptLimitTimeHalf() != null )
        return false;
    } else if ( !order_accept_limit_time_half.equals( row.getOrderAcceptLimitTimeHalf() ) ) {
        return false;
    }
    if ( buy_forbid_div == null ) {
      if ( row.getBuyForbidDiv() != null )
        return false;
    } else if ( !buy_forbid_div.equals( row.getBuyForbidDiv() ) ) {
        return false;
    }
    if ( buy_forbid_end_date == null ) {
      if ( row.getBuyForbidEndDate() != null )
        return false;
    } else if ( !buy_forbid_end_date.equals( row.getBuyForbidEndDate() ) ) {
        return false;
    }
    if ( sell_forbid_end_date == null ) {
      if ( row.getSellForbidEndDate() != null )
        return false;
    } else if ( !sell_forbid_end_date.equals( row.getSellForbidEndDate() ) ) {
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
        + (product_code!=null? product_code.hashCode(): 0) 
        + (reg_type!=null? reg_type.hashCode(): 0) 
        + (order_accept_limit_time_usual!=null? order_accept_limit_time_usual.hashCode(): 0) 
        + (order_accept_limit_time_half!=null? order_accept_limit_time_half.hashCode(): 0) 
        + (buy_forbid_div!=null? buy_forbid_div.hashCode(): 0) 
        + (buy_forbid_end_date!=null? buy_forbid_end_date.hashCode(): 0) 
        + (sell_forbid_end_date!=null? sell_forbid_end_date.hashCode(): 0) 
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
		map.put("product_code",product_code);
		if ( reg_type != null )
			map.put("reg_type",reg_type);
		if ( order_accept_limit_time_usual != null )
			map.put("order_accept_limit_time_usual",order_accept_limit_time_usual);
		if ( order_accept_limit_time_half != null )
			map.put("order_accept_limit_time_half",order_accept_limit_time_half);
		if ( buy_forbid_div != null )
			map.put("buy_forbid_div",buy_forbid_div);
		if ( buy_forbid_end_date != null )
			map.put("buy_forbid_end_date",buy_forbid_end_date);
		if ( sell_forbid_end_date != null )
			map.put("sell_forbid_end_date",sell_forbid_end_date);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( reg_type_is_modified )
			map.put("reg_type",reg_type);
		if ( order_accept_limit_time_usual_is_modified )
			map.put("order_accept_limit_time_usual",order_accept_limit_time_usual);
		if ( order_accept_limit_time_half_is_modified )
			map.put("order_accept_limit_time_half",order_accept_limit_time_half);
		if ( buy_forbid_div_is_modified )
			map.put("buy_forbid_div",buy_forbid_div);
		if ( buy_forbid_end_date_is_modified )
			map.put("buy_forbid_end_date",buy_forbid_end_date);
		if ( sell_forbid_end_date_is_modified )
			map.put("sell_forbid_end_date",sell_forbid_end_date);
		if (map.size() == 0) {
			map.put("reg_type",reg_type);
			map.put("order_accept_limit_time_usual",order_accept_limit_time_usual);
			map.put("order_accept_limit_time_half",order_accept_limit_time_half);
			map.put("buy_forbid_div",buy_forbid_div);
			map.put("buy_forbid_end_date",buy_forbid_end_date);
			map.put("sell_forbid_end_date",sell_forbid_end_date);
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
   * <em>reg_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getRegType()
  {
    return reg_type;
  }


  /** 
   * <em>reg_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegTypeIsSet() {
    return reg_type_is_set;
  }


  /** 
   * <em>reg_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRegTypeIsModified() {
    return reg_type_is_modified;
  }


  /** 
   * <em>order_accept_limit_time_usual</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderAcceptLimitTimeUsual()
  {
    return order_accept_limit_time_usual;
  }


  /** 
   * <em>order_accept_limit_time_usual</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderAcceptLimitTimeUsualIsSet() {
    return order_accept_limit_time_usual_is_set;
  }


  /** 
   * <em>order_accept_limit_time_usual</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderAcceptLimitTimeUsualIsModified() {
    return order_accept_limit_time_usual_is_modified;
  }


  /** 
   * <em>order_accept_limit_time_half</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderAcceptLimitTimeHalf()
  {
    return order_accept_limit_time_half;
  }


  /** 
   * <em>order_accept_limit_time_half</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderAcceptLimitTimeHalfIsSet() {
    return order_accept_limit_time_half_is_set;
  }


  /** 
   * <em>order_accept_limit_time_half</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderAcceptLimitTimeHalfIsModified() {
    return order_accept_limit_time_half_is_modified;
  }


  /** 
   * <em>buy_forbid_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBuyForbidDiv()
  {
    return buy_forbid_div;
  }


  /** 
   * <em>buy_forbid_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyForbidDivIsSet() {
    return buy_forbid_div_is_set;
  }


  /** 
   * <em>buy_forbid_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyForbidDivIsModified() {
    return buy_forbid_div_is_modified;
  }


  /** 
   * <em>buy_forbid_end_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBuyForbidEndDate()
  {
    return buy_forbid_end_date;
  }


  /** 
   * <em>buy_forbid_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyForbidEndDateIsSet() {
    return buy_forbid_end_date_is_set;
  }


  /** 
   * <em>buy_forbid_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBuyForbidEndDateIsModified() {
    return buy_forbid_end_date_is_modified;
  }


  /** 
   * <em>sell_forbid_end_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getSellForbidEndDate()
  {
    return sell_forbid_end_date;
  }


  /** 
   * <em>sell_forbid_end_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellForbidEndDateIsSet() {
    return sell_forbid_end_date_is_set;
  }


  /** 
   * <em>sell_forbid_end_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getSellForbidEndDateIsModified() {
    return sell_forbid_end_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MutualFundInstCondSonarPK(institution_code, product_code);
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
   * <em>reg_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_regType <em>reg_type</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setRegType( String p_regType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_type = p_regType;
    reg_type_is_set = true;
    reg_type_is_modified = true;
  }


  /** 
   * <em>order_accept_limit_time_usual</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderAcceptLimitTimeUsual <em>order_accept_limit_time_usual</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setOrderAcceptLimitTimeUsual( String p_orderAcceptLimitTimeUsual )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_accept_limit_time_usual = p_orderAcceptLimitTimeUsual;
    order_accept_limit_time_usual_is_set = true;
    order_accept_limit_time_usual_is_modified = true;
  }


  /** 
   * <em>order_accept_limit_time_half</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderAcceptLimitTimeHalf <em>order_accept_limit_time_half</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setOrderAcceptLimitTimeHalf( String p_orderAcceptLimitTimeHalf )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_accept_limit_time_half = p_orderAcceptLimitTimeHalf;
    order_accept_limit_time_half_is_set = true;
    order_accept_limit_time_half_is_modified = true;
  }


  /** 
   * <em>buy_forbid_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyForbidDiv <em>buy_forbid_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setBuyForbidDiv( String p_buyForbidDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_forbid_div = p_buyForbidDiv;
    buy_forbid_div_is_set = true;
    buy_forbid_div_is_modified = true;
  }


  /** 
   * <em>buy_forbid_end_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_buyForbidEndDate <em>buy_forbid_end_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setBuyForbidEndDate( String p_buyForbidEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_forbid_end_date = p_buyForbidEndDate;
    buy_forbid_end_date_is_set = true;
    buy_forbid_end_date_is_modified = true;
  }


  /** 
   * <em>sell_forbid_end_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_sellForbidEndDate <em>sell_forbid_end_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setSellForbidEndDate( String p_sellForbidEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_forbid_end_date = p_sellForbidEndDate;
    sell_forbid_end_date_is_set = true;
    sell_forbid_end_date_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("buy_forbid_div") ) {
                    return this.buy_forbid_div;
                }
                else if ( name.equals("buy_forbid_end_date") ) {
                    return this.buy_forbid_end_date;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'o':
                if ( name.equals("order_accept_limit_time_usual") ) {
                    return this.order_accept_limit_time_usual;
                }
                else if ( name.equals("order_accept_limit_time_half") ) {
                    return this.order_accept_limit_time_half;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'r':
                if ( name.equals("reg_type") ) {
                    return this.reg_type;
                }
                break;
            case 's':
                if ( name.equals("sell_forbid_end_date") ) {
                    return this.sell_forbid_end_date;
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
                if ( name.equals("buy_forbid_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_forbid_div' must be String: '"+value+"' is not." );
                    this.buy_forbid_div = (String) value;
                    if (this.buy_forbid_div_is_set)
                        this.buy_forbid_div_is_modified = true;
                    this.buy_forbid_div_is_set = true;
                    return;
                }
                else if ( name.equals("buy_forbid_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_forbid_end_date' must be String: '"+value+"' is not." );
                    this.buy_forbid_end_date = (String) value;
                    if (this.buy_forbid_end_date_is_set)
                        this.buy_forbid_end_date_is_modified = true;
                    this.buy_forbid_end_date_is_set = true;
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
            case 'o':
                if ( name.equals("order_accept_limit_time_usual") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_accept_limit_time_usual' must be String: '"+value+"' is not." );
                    this.order_accept_limit_time_usual = (String) value;
                    if (this.order_accept_limit_time_usual_is_set)
                        this.order_accept_limit_time_usual_is_modified = true;
                    this.order_accept_limit_time_usual_is_set = true;
                    return;
                }
                else if ( name.equals("order_accept_limit_time_half") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_accept_limit_time_half' must be String: '"+value+"' is not." );
                    this.order_accept_limit_time_half = (String) value;
                    if (this.order_accept_limit_time_half_is_set)
                        this.order_accept_limit_time_half_is_modified = true;
                    this.order_accept_limit_time_half_is_set = true;
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
                break;
            case 'r':
                if ( name.equals("reg_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reg_type' must be String: '"+value+"' is not." );
                    this.reg_type = (String) value;
                    if (this.reg_type_is_set)
                        this.reg_type_is_modified = true;
                    this.reg_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sell_forbid_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_forbid_end_date' must be String: '"+value+"' is not." );
                    this.sell_forbid_end_date = (String) value;
                    if (this.sell_forbid_end_date_is_set)
                        this.sell_forbid_end_date_is_modified = true;
                    this.sell_forbid_end_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
