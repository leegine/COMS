head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.30.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDeliveryManagementHistPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>DocDeliveryManagementHist</b>�f�[�^�x�[�X�e�[�u���ň�ӂł���1�̃��R�[�h������킷<b>DocDeliveryManagementHist</b>�C���X�^���X����肷���L�[�I�u�W�F�N�g�ł��B 
 * <p> 
 * ����͒ʏ탁�������ɍ\�z����A<b>DocDeliveryManagementHist</b>�e�[�u���������̃��R�[�h���������邽��{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * �N�G�����\�b�h�ֈ����Ƃ��ēn����܂��B 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DocDeliveryManagementHistRow 
 */
public class DocDeliveryManagementHistPK implements PrimaryKey {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "pk";


  /** ����pks�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "doc_delivery_management_hist";


  /** 
   * PrimaryKey�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ���̎�L�[�ɕR�t���e�[�u����RowType: DocDeliveryManagementHistRow. 
   */
  public RowType getRowType() {
    return DocDeliveryManagementHistRow.TYPE;
  }

  /**
   * <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public long account_id;
  /**
   * <em>document_div</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String document_div;
  /**
   * <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public String product_code;
  /**
   * <em>delivery_date</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp delivery_date;
  /**
   * <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public java.sql.Timestamp created_timestamp;
  /**
   * <em>document_category</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public String document_category;


  /** 
   * �f�t�H���g�R���X�g���N�^ 
   */
  public DocDeliveryManagementHistPK() { 
  }


  /** 
   * �����𔺂��R���X�g���N�^ 
   *
   * @@param p_accountId <em>account_id</em>�J�����̒l������킷18���ȉ���long�l 
   * @@param p_documentDiv <em>document_div</em>�J�����̒l������킷3���ȉ���String�l 
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷10���ȉ���String�l 
   * @@param p_deliveryDate <em>delivery_date</em>�J�����̒l������킷java.sql.Timestamp�l
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   * @@param p_documentCategory <em>document_category</em>�J�����̒l������킷3���ȉ���String�l 
   */
  public DocDeliveryManagementHistPK( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) {
      account_id = p_accountId;
      document_div = p_documentDiv;
      product_code = p_productCode;
      delivery_date = p_deliveryDate;
      created_timestamp = p_createdTimestamp;
      document_category = p_documentCategory;
  }


  /** 
   * �J���}��؂�Ŏw�肵��������𗘗p����pk���\�z���܂��B 
   * 
   * @@param pkValueString ��L�[�̒l���J���}��؂�Ń��X�g���������� 
   * @@exception NumberFormatException double�A long�Aint�̃p�[�X���ɃG���[�����������ꍇ 
   */
  public static DocDeliveryManagementHistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    DocDeliveryManagementHistPK pk = new DocDeliveryManagementHistPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.document_div = st.nextToken();
    pk.product_code = st.nextToken();
    pk.delivery_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.created_timestamp = java.sql.Timestamp.valueOf(st.nextToken());
    pk.document_category = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * �R���X�g���N�^�쐬���Ɠ����ŃJ���}��؂�̎�L�[�l�̕������Ԃ��܂��B 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + document_div + "," + product_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(delivery_date) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(created_timestamp) + "," + document_category;
    return m_id;
  }

  private String m_id = null;


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof DocDeliveryManagementHistPK) )
      return false;
    DocDeliveryManagementHistPK o = (DocDeliveryManagementHistPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( document_div == null ) {
      if ( o.document_div != null )
        return false;
    } else if ( !document_div.equals( o.document_div ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( o.delivery_date != null )
        return false;
    } else if ( !delivery_date.equals( o.delivery_date ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( o.created_timestamp != null )
        return false;
    } else if ( !created_timestamp.equals( o.created_timestamp ) ) {
        return false;
    }
    if ( document_category == null ) {
      if ( o.document_category != null )
        return false;
    } else if ( !document_category.equals( o.document_category ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int 
   */
  public int hashCode() {
    return ((int) account_id)
        + (document_div!=null? document_div.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (document_category!=null? document_category.hashCode(): 0) 
    ;
  }

}

@
