head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleCallbackConstantsDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3SleCallbackConstantsDef�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/10/23 ��(FTL) �V�K�쐬
 */
package webbroker3.slegateway.define;

/**
 * SLE�}�[�P�b�g�A�_�v�^�ɂĎg�p����SLE API�֘A�̒萔
 */
public class WEB3SleCallbackConstantsDef {

  /**
   * SLE�R�l�N�^�֘A�̒萔�ł��B
   */

  /**
   * �s��֘A�̒萔�ł��B
   */
  public static class Markets {

    /***********************************************************************************************
     * ���`���֘A�̒萔�ł��B
     **********************************************************************************************/
    public static class SEHK {
                 
      /** ���`�s���GLID�ł��B */
      public final static String GLID        =  "000800000000";

    }
    
    /***********************************************************************************************
     * �[�Z���s��֘A�̒萔�ł��B
     **********************************************************************************************/
    public static class SESZ {
                 
      /** �[�Z���s���GLID�ł��B */
      public final static String GLID        =  "012000000000";

    }
    
    /***********************************************************************************************
     * ��C�s��֘A�̒萔�ł��B
     **********************************************************************************************/
    public static class SESH {
                 
      /** ��C�s���GLID�ł��B 2009/1 ���_�܂Ŗ���*/
      public final static String GLID        =  "011900000000";
    }

}

  /**
   * SLE��2019���X�|���X���b�Z�[�W�̉����^�C�v�ł��B
   */
  public static class RepliesType {

    /** �V�K�A�ύX�A����̉��� */
    public final static String ACK             = "A";

    /** GL���̃��W�F�N�g */
    public final static String GL_REJECT       = "G";

    /** �s�ꂩ��̃��W�F�N�g */
    public final static String EXCHANGE_REJECT = "C";

    /** ��� */
    public final static String TRADE_EXEC      = "R";

    /** �s�ꂩ��̊Ǘ��d�� */
    public final static String EXCHANGE_MSG    = "J";
    
    /** �s�ꂩ���Alert�d�� */
    public final static String ALERT_MSG    = "L";
  }

  /**
   * Ack�^�C�v�ł��B
   */
  public static class AckType {

    /** ����ς� */
    public final static int CANCELLED        = 1;

    /** �����ς� */
    public final static int ORDER_ELIMINATED = 2;

    /** ��t�ς� */
    public final static int ORDER_ACCEPTED   = 4;
  }

  /**
   * Ack�R�}���h�̃^�C�v�ł��B
   */
  public static class AckdCommand {

    /** �V�K������Ack */
    public final static int NEW_ORDER_ACK = 0;

    /** �����Ack */
    public final static int CANCEL_ACK    = 1;

    /** �ύX��Ack */
    public final static int MODIFY_ACK    = 2;
  }

  /**
   * Reject�R�}���h�̃^�C�v�ł��B
   */
  public static class RejectedCommandType {

    /** �V�K�����̃��W�F�N�g */
    public final static int REJECT_NEW_ORDER = 0;

    /** ����̃��W�F�N�g */
    public final static int REJECT_CANCEL    = 1;

    /** �ύX�̃��W�F�N�g */
    public final static int REJECT_MODIFY    = 2;
  }
  
  /**
   * WEB3���ŊǗ�����I�y���[�^�^�C�v
   * ��elimination�d������͂���ɂ́AWEB3�̃I�y���[�^�^�C�v���Q�Ƃ���K�v������̂�
   * ��2006/11/2 �ǉ�
   */
  public static class OrderOpType {

    /** �V�K�����̃I�y���[�^�^�C�v */
    public final static int NEW_ORDER_OP = 0;

    /** ���������̃I�y���[�^�^�C�v */
    public final static int MODIFY_ORDER_OP    = 1;

    /** ��������̃I�y���[�^�^�C�v */
    public final static int CANCEL_ORDER_OP    = 2;
  }

  /**
   * SubStatus�̃^�C�v�ł��B
   */
  public static class SubStatusType {

    /** �s��ɂ����钍����� */
    public final static String BRANK = " ";

    /** SLE�ɂ����钍����� */
    public final static String STATUS_AT_SLE    = "Q";

    /** OG�L���[�ɂ����钍����� */
    public final static String STATUS_AT_OG    = "E";
  }
}


@
