head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConstants.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleConstants�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 �� �V�K�쐬
 */
package webbroker3.slebase.utils;

/**
 * SLE�}�[�P�b�g�A�_�v�^�ɂĎg�p����SLE API�֘A�̒萔
 */
public class SleConstants {

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
      
      /** ���`�s�ꖼ�B */
      public final static String MARKET_CODE = "N1";
      
	  /** �u���[�J���B */
	  public final static String BROKER_NAME = "HK SMBC";
      
           
      /** ���`�s���GLID�ł��B */
      public final static String GLID        = "000800000000";


      /** ���`�s��փ��N�G�X�gID**/
      public static final String F_GL_REQ_ID = "2000SEHK";
      
      /** ��a���`�Ɋ��蓖�Ă����GL ���[�UNo */
      public static final String DIR_HK_GL_NO = "101";
      
      /**
       * �����J�e�S����SLE���b�Z�[�W�ł��B
       */
      public static class OrderCategory {

        public final static String SINGLE = "O";

        public final static String CARE   = "S";

        public final static String STOP   = "M";
      }

      /**
       * SLE��2019���X�|���X���b�Z�[�W�̔����敪�ł��B
       */
      public static class Side {

        /** ���� */
        public final static int BUY  = 0;

        /** ���� */
        public final static int SELL = 1;
      }

      /**
       * ������s�����y�ю��s�����֘A�̒萔�ł��B
       */
      public static class TradingPhase {

        /** ���s�����Ȃ� */
        public final static String UNCONDITIONAL     = " ";

        /** �s���� */
        public final static String FUNARI            = "C";

        /** �����ύX���Ɏ��s�������N���A����ۂɎg�p */
        public final static String CLEAR_TO_NULL     = "E"; // reset during modification as per GL

        // API spec for TSE.

        /** ���(TSE) */
        public final static String AT_OPEN_ONLY      = "0";

        /** ����(TSE) */
        public final static String AT_CLOSE_ONLY     = "K";

        /** ���(FEQ) */
        public final static String AT_OPEN_ONLY_FEQ  = "HB";

        /** ����(FEQ) */
        public final static String AT_CLOSE_ONLY_FEQ = "HE";
      }

      /**
       * SLE��2019���X�|���X���b�Z�[�W�̃v���C�X�^�C�v�ł��B
       */
      public static class Modality {

        /** ���s */
        public final static String ANY_PRICE              = "B";

        /** �w�l */
        public final static String LIMIT_PRICE            = "L";

        /** ���ݒl�w�l */
        public final static String LAST_PRICE             = "A";

        /** �D��w�l */
        public final static String BEST_PRICE             = "P";

        /** ���s�c���w�l */
        public final static String MARKET_REMAINING_LIMIT = "M";
      }

      /**
       * Validity of an order.
       */
      public static class Validity {

        /**
         * �{���� ���ӁF ���b�Z�[�W��MFD�t�@@�C���ɂăf�B�t�H���g�Ƃ��Đݒ肵�Ă���ב��M���œ��ɐݒ肵�Ă��Ȃ��B
         */
        public final static String ALL_DAY = "J";

        /**
         * �l�i���������s�c������̎��Ɏg�p�B
         */
        public final static String FAK     = "E";
      }

      /**
       * ���`���̎�������b�Z�[�W�^�C�v�֘A�̃G�i���ł��B
       */
      public static class ExchangeMessageType {

        /**
         * �}�[�P�b�g�X�e�[�^�X�ɕύX�����邱�Ƃ������܂��B
         */
        public final static String MARKET_STATUS_MESSAGE = "1";

        /**
         * �s��Ƀ����N�X�e�[�^�X�̕ύX�����邱�Ƃ������܂��B
         */
        public final static String LINK_STATUS_MESSAGE   = "5";
        
        /**
         * �s�����X�e�[�^�X�ύX�����邱�Ƃ������܂��B
         */
        public final static String EXCHANGE_STATUS_MESSAGE = "2";
        
        /**
         * �S�̎s��X�e�[�^�X�ύX�����邱�Ƃ������܂��B
         */
        public final static String ALL_STATUS_MESSAGE = "6";
        
        /**
         * ��������X�e�[�^�X�ύX�����邱�Ƃ������܂��B
         */
        public final static String BRAND_STATUS_MESSAGE = "8";
        
        
      }

      /**
       * ���`���̎�������b�Z�[�W�R�[�h�֘A�̃G�i���ł��B
       */
      public static class ExchangeMessageCode {

        /**
         * �J�n�O�������܂��B
         */
        public final static String PRE_OPEN             = "P";

        /**
         * �N���[�Y�E�I���������܂��B
         */
        public final static String CLOSED               = "E";

        /**
         * �����N�����S�Ɏ���ꂽ���Ƃ������܂��B
         */
        public final static String LINK_COMPLETELY_LOST = "Z";

        /**
         * �����N�������I�Ɏ���ꂽ���Ƃ������܂��B
         */
        public final static String LINK_PARTIALLY_LOST  = "A";

        /**
         * �����N���C�����ꂽ���Ƃ������܂��B
         */
        public final static String LINK_RESTORED        = "N";
      }
      
      /**
       * Market Trading Status
       */
      public static class MarketTradingStatus {
          
          /**
           * ������
           */
          public final static String BLOCK = "FE";
          /**
           * �N���[�Y(�S��)
           */
          public final static String CLOSE_GENERAL = "HE";
          /**
           * �����
           */
          public final static String EXCHANGGING = "IB";
          /**
           * �s�ꖢ�I�[�v��
           */
          public final static String NOT_OPEN = "JB";
          /**
           * ���������
           */          
          public final static String ORDER_CANCEL = "BB";
          /**
           * ��������
           */
          public final static String ORDER_INPUT = "FB";
          /**
           * ���/�����s��
           */
          public final static String CAN_NOT_MODIFY_AND_CANCEL = "FB";
          /**
           * �s����
           */
          public final static String MARKET_INTERVENTION = "BB";
          /**
           * �N���[�Y
           */
          public final static String DAY_CLOSE = "DB";
          /**
           * ������
           */
          public final static String FREEZE = "BB";
      }

      /**
       * �}�[�P�b�g�X�e�[�^�X�̕ύX�ʒm�Ɋ֘A������������̃��b�Z�[�W�̏،��R�[�h�ɐݒ肳���l�ł��B
       */
      public final static String STOCK_CODE_FOR_EXCHANGE_MESSAGES = "SZEQ";

    }
    
    /***********************************************************************************************
     * �[�Z���s��֘A�̒萔�B
     **********************************************************************************************/
    public static class  SESZ{
      
      /** �[�Z���s�ꖼ�B */
      public final static String MARKET_CODE = "N2";
           
      /** �[�Z���s���GLID�ł��B */
      public final static String GLID        = "012000000000";

      /** �[�Z���s��փ��N�G�X�gID**/
      public static final String F_GL_REQ_ID = "2000SESZ";
      
      /**
       * �����J�e�S����SLE���b�Z�[�W�ł��B
       */
      public static class OrderCategory {

        public final static String SINGLE = "O";

        public final static String CARE   = "S";

        public final static String STOP   = "M";
      }

      /**
       * SLE��2019���X�|���X���b�Z�[�W�̔����敪�ł��B
       */
      public static class Side {

        /** ���� */
        public final static int BUY  = 0;

        /** ���� */
        public final static int SELL = 1;
      }

      /**
       * ������s�����y�ю��s�����֘A�̒萔�ł��B
       */
      public static class TradingPhase {

        /** ���s�����Ȃ� */
        public final static String UNCONDITIONAL     = " ";

        /** �s���� */
        public final static String FUNARI            = "C";

        /** �����ύX���Ɏ��s�������N���A����ۂɎg�p */
        public final static String CLEAR_TO_NULL     = "E"; // reset during modification as per GL

        // API spec for TSE.

        /** ���(TSE) */
        public final static String AT_OPEN_ONLY      = "0";

        /** ����(TSE) */
        public final static String AT_CLOSE_ONLY     = "K";

        /** ���(FEQ) */
        public final static String AT_OPEN_ONLY_FEQ  = "HB";

        /** ����(FEQ) */
        public final static String AT_CLOSE_ONLY_FEQ = "HE";
      }

      /**
       * SLE��2019���X�|���X���b�Z�[�W�̃v���C�X�^�C�v�ł��B
       */
      public static class Modality {

        /** ���s */
        public final static String ANY_PRICE              = "B";

        /** �w�l */
        public final static String LIMIT_PRICE            = "L";

        /** ���ݒl�w�l */
        public final static String LAST_PRICE             = "A";

        /** �D��w�l */
        public final static String BEST_PRICE             = "P";

        /** ���s�c���w�l */
        public final static String MARKET_REMAINING_LIMIT = "M";
      }

      /**
       * Validity of an order.
       */
      public static class Validity {

        /**
         * �{���� ���ӁF ���b�Z�[�W��MFD�t�@@�C���ɂăf�B�t�H���g�Ƃ��Đݒ肵�Ă���ב��M���œ��ɐݒ肵�Ă��Ȃ��B
         */
        public final static String ALL_DAY = "J";

        /**
         * �l�i���������s�c������̎��Ɏg�p�B
         */
        public final static String FAK     = "E";
      }

      /**
       * �������̎�������b�Z�[�W�^�C�v�֘A�̃G�i���ł��B
       */
      public static class ExchangeMessageType {

        /**
         * �}�[�P�b�g�X�e�[�^�X�ɕύX�����邱�Ƃ������܂��B
         */
        public final static String MARKET_STATUS_MESSAGE = "1";

        /**
         * �s��Ƀ����N�X�e�[�^�X�̕ύX�����邱�Ƃ������܂��B
         */
        public final static String LINK_STATUS_MESSAGE   = "5";
        
        /**
         * �s�����X�e�[�^�X�ύX�����邱�Ƃ������܂��B
         */
        public final static String EXCHANGE_STATUS_MESSAGE = "2";
        
        /**
         * �S�̎s��X�e�[�^�X�ύX�����邱�Ƃ������܂��B
         */
        public final static String ALL_STATUS_MESSAGE = "6";
        
        /**
         * ��������X�e�[�^�X�ύX�����邱�Ƃ������܂��B
         */
        public final static String BRAND_STATUS_MESSAGE = "8";
        
        
      }

      /**
       * �������̎�������b�Z�[�W�R�[�h�֘A�̃G�i���ł��B
       */
      public static class ExchangeMessageCode {

        /**
         * �J�n�O�������܂��B
         */
        public final static String PRE_OPEN             = "P";

        /**
         * �N���[�Y�E�I���������܂��B
         */
        public final static String CLOSED               = "E";

        /**
         * �����N�����S�Ɏ���ꂽ���Ƃ������܂��B
         */
        public final static String LINK_COMPLETELY_LOST = "Z";

        /**
         * �����N�������I�Ɏ���ꂽ���Ƃ������܂��B
         */
        public final static String LINK_PARTIALLY_LOST  = "A";

        /**
         * �����N���C�����ꂽ���Ƃ������܂��B
         */
        public final static String LINK_RESTORED        = "N";
      }
      
      /**
       * Market Trading Status
       */
      public static class MarketTradingStatus {
          
          /**
           * ������
           */
          public final static String BLOCK = "FE";
          /**
           * �N���[�Y(�S��)
           */
          public final static String CLOSE_GENERAL = "HE";
          /**
           * �����
           */
          public final static String EXCHANGGING = "IB";
          /**
           * �s�ꖢ�I�[�v��
           */
          public final static String NOT_OPEN = "JB";
          /**
           * ���������
           */          
          public final static String ORDER_CANCEL = "BB";
          /**
           * ��������
           */
          public final static String ORDER_INPUT = "FB";
          /**
           * ���/�����s��
           */
          public final static String CAN_NOT_MODIFY_AND_CANCEL = "FB";
          /**
           * �s����
           */
          public final static String MARKET_INTERVENTION = "BB";
          /**
           * �N���[�Y
           */
          public final static String DAY_CLOSE = "DB";
          /**
           * ������
           */
          public final static String FREEZE = "BB";
      }
      
    }
    
    /***********************************************************************************************
     * ��C�s��֘A�̒萔�B
     **********************************************************************************************/
    public static class SESH extends SESZ
    {
      
      /** ��C�s�ꖼ�B */
      public final static String MARKET_CODE = "X1";
      
      /** ��C�s���GLID�ł��B */
      public final static String GLID        = "011900000000";

      /** ��C�s��փ��N�G�X�gID**/
      public static final String F_GL_REQ_ID = "2000SESH";
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

  /**
   * SLE��2019���X�|���X���b�Z�[�W�̒����X�e�[�^�X�ł��B�����X�e�[�^�X�̓t�B�[���h�ԍ�41�ɑΉ����܂��B
   */
  public static class OrderStatus41 {

    public final static String ORDER_WAITING                    = "000";

    public final static String ORDER_WAITING_PARTIALLY_EXEC     = "002";

    public final static String ORDER_ACKD                       = "060";

    public final static String ORDER_PARTIALLY_EXECD            = "062";

    public final static String ORDER_TOTALLY_EXEC               = "063";

    public final static String ORDER_CANCELLED                  = "160";

    public final static String ORDER_CANCELLED_PARTIALLY_EXECD  = "162";

    public final static String ORDER_REJECTED                   = "030";

    public final static String ORDER_REJECTED_PARTIALLY_EXECD   = "032";

    public final static String ORDER_CANCELLING                 = "660";

    public final static String ORDER_CANCELLING_PARTIALLY_EXECD = "662";

    public final static String ORDER_MODIFYING                  = "560";

    public final static String ORDER_MODIFIED_PARTIALLY_EXECD   = "462";

    public final static String ORDER_MODIFIED                   = "460";

    public final static String ORDER_MODIFYING_PARTIALLY_EXECD  = "562";

    /** �ύX�����̐ړ����ł��B */
    public final static String ORDER_MODIFYING_PREFIX           = "5";

    /** ��������̐ړ����ł��B */
    public final static String ORDER_CANCELLING_PREFIX          = "6";

  }

  /**
   * SLE���b�Z�[�W�i�V�K�����j�̃R�}���h�t�B�[���h�ł��B
   */
  public static class OrderCommand {

    /** �V�K���� */
    public final static int NEW           = 0;

    /** ��� */
    public final static int CANCEL        = 1;

    /** �ύX */
    public final static int MODIFY        = 2;

    /** �O���[�o���L�����Z���i���g�p�j */
    public final static int GLOBAL_CANCEL = 5;
  }

  /**
   * SLE2000�d�����ڂł��B
   */
  public static class Order2000MsgItem {
      
    public static final String F_USER_NUMBER          = "user_number";
    
    public static final String F_CUSTOMER_ACCOUNT     = "customer_account";
    
    public static final String F_VALIDITY             = "validity";
    
    public static final String F_SIDE                 = "side";

    public static final String F_COUNTERPART          = "counterpart";

    public static final String F_MARKET_CODE          = "market_code";

    public static final String F_MEMO                 = "memo";

    public static final String F_EXCHANGE_REFERENCE   = "exchange_reference";

    public static final String F_INTERNAL_REFERENCE   = "internal_reference";

    public static final String F_GLID                 = "glid";

    public static final String F_STOCK_CODE           = "stock_code";

    public static final String F_ORDER_CATEGORY       = "order_category";

    public static final String F_PRICE                = "price";

    public static final String F_MODALITY             = "modality";

    public static final String F_QUANTITY             = "quantity";

    public static final String F_COMMAND              = "command";

    public static final String F_ORDER_TYPE           = "order_type";

    public static final String F_REMAINING_QUANTITY   = "remaining_quantity";

    public static final String F_TRIGGER_MARKET_PHASE = "trigger_market_phase";
    
    public static final String F_TRIGGER_PARAMETER = "trigger_parameter";
    
    public static final String F_LONG_NAME = "long_name";
    
    public static final String ROUTING_REFERENCE = "routing_reference";

  }

//  /**
//   * ���`�u���[�J���
//   */
//  public static class HKBroker {
//
//    public static final String BROKER_NAME   = "HK SBMC";
//
//    public static final String BROKER_MARKET = "N1";
//  }

  /**
   * �����R�����g
   */
  public static class GLDATACOMMENT {

    //�V�K
    public static final String ORDER_NEW    = "New order request";

    //���
    public static final String ORDER_CANCEL = "Cancel order request";

    //����
    public static final String ORDER_CHG    = "Change order request";
  }

  /**
   * �s��X�e�[�^�X�Ǘ��e�[�u��
   */
  public static class SLEMARKETSTATUS {

    //1�F���������M   
    public static final int SEND_OK        = 1;

    //2�F���������M���Ȃ�
    public static final int SEND_FALSE     = 2;

    //0�F�I�t���C�����(�s��֘A)
    //1�F�I�����C�����(�s��֘A)
    //2�F�����N����(�R�l�N�^�֘A)
    //3�F�����N���S����(�R�l�N�^�֘A)
    public static final int STATUS_OFFLINE  = 0;

    public static final int STATUS_ONLINE = 1;

    public static final int STATUS_LINK    = 2;

    public static final int STATUS_NOLINK  = 3;


  }

}


@
