head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityRangeBasedMapPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>AffinityRangeBasedMap</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>AffinityRangeBasedMap</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>AffinityRangeBasedMap</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityRangeBasedMapRow 
 */
public class AffinityRangeBasedMapPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "affinity_range_based_map";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: AffinityRangeBasedMapRow. 
   */
  public RowType getRowType() {
    return AffinityRangeBasedMapRow.TYPE;
  }

  /**
   * <em>key_start</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long key_start;
  /**
   * <em>key_end</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long key_end;
  /**
   * <em>range_order_no</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public int range_order_no;
  /**
   * <em>server_type</em>�J�����̒l������킷1���ȉ���int�l 
   */
  public int server_type;
  /**
   * <em>ctx_name</em>�J�����̒l������킷100���ȉ���String�l 
   */
  public String ctx_name;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public AffinityRangeBasedMapPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_keyStart <em>key_start</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_keyEnd <em>key_end</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_rangeOrderNo <em>range_order_no</em>�J�����̒l������킷6���ȉ���int�l 
   * @@param p_serverType <em>server_type</em>�J�����̒l������킷1���ȉ���int�l 
   * @@param p_ctxName <em>ctx_name</em>�J�����̒l������킷100���ȉ���String�l 
   */
  public AffinityRangeBasedMapPK( long p_keyStart, long p_keyEnd, int p_rangeOrderNo, int p_serverType, String p_ctxName ) {
      key_start = p_keyStart;
      key_end = p_keyEnd;
      range_order_no = p_rangeOrderNo;
      server_type = p_serverType;
      ctx_name = p_ctxName;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static AffinityRangeBasedMapPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AffinityRangeBasedMapPK pk = new AffinityRangeBasedMapPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.key_start = Long.valueOf(st.nextToken()).longValue();
    pk.key_end = Long.valueOf(st.nextToken()).longValue();
    pk.range_order_no = Integer.valueOf(st.nextToken()).intValue();
    pk.server_type = Integer.valueOf(st.nextToken()).intValue();
    pk.ctx_name = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(key_start) + "," + String.valueOf(key_end) + "," + String.valueOf(range_order_no) + "," + String.valueOf(server_type) + "," + ctx_name;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AffinityRangeBasedMapPK) )
      return false;
    AffinityRangeBasedMapPK o = (AffinityRangeBasedMapPK) other;
    if ( key_start != o.key_start )
      return false;
    if ( key_end != o.key_end )
      return false;
    if ( range_order_no != o.range_order_no )
      return false;
    if ( server_type != o.server_type )
      return false;
    if ( ctx_name == null ) {
      if ( o.ctx_name != null )
        return false;
    } else if ( !ctx_name.equals( o.ctx_name ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) key_start)
        + ((int) key_end)
        + ((int) range_order_no)
        + ((int) server_type)
        + (ctx_name!=null? ctx_name.hashCode(): 0) 
    ;
  }

}

@
