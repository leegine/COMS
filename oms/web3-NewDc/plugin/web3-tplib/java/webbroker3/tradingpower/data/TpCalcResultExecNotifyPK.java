head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultExecNotifyPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>TpCalcResultExecNotify</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>TpCalcResultExecNotify</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>TpCalcResultExecNotify</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultExecNotifyRow 
 */
public class TpCalcResultExecNotifyPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "tp_calc_result_exec_notify";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: TpCalcResultExecNotifyRow. 
   */
  public RowType getRowType() {
    return TpCalcResultExecNotifyRow.TYPE;
  }

  /**
   * <em>rowid</em>�J�����̒l������킷20���ȉ���String�l�ŁA���̐��x��20���܂�
   */
  public String rowid;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public TpCalcResultExecNotifyPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_rowid <em>rowid</em>�J�����̒l������킷20���ȉ���String�l�ŁA���̐��x��20���܂�
   */
  public TpCalcResultExecNotifyPK( String p_rowid ) {
      rowid = p_rowid;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static TpCalcResultExecNotifyPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new TpCalcResultExecNotifyPK( pkValueString );
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    return rowid;
  }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TpCalcResultExecNotifyPK) )
      return false;
    TpCalcResultExecNotifyPK o = (TpCalcResultExecNotifyPK) other;
    if ( rowid == null ) {
      if ( o.rowid != null )
        return false;
    } else if ( !rowid.equals( o.rowid ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return (rowid!=null? rowid.hashCode(): 0) 
    ;
  }

}

@
