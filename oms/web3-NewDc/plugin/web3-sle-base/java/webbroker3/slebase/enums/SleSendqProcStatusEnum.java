head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendqProcStatusEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleBasedMarketAdapterBasePlugin�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/2 �� �V�K�쐬
 */

package webbroker3.slebase.enums;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 *  SEND_Q�@@�̏����敪�Ɋւ���G�i���萔���`���܂��B
 */
public class SleSendqProcStatusEnum extends Enumerated {

    /** �����҂���Ԃ�����킵�܂��B */
    public static final SleSendqProcStatusEnum TODO = new SleSendqProcStatusEnum(IntValues.TODO,
          "TODO");

    /** �����ς�����킵�܂��B */
    public static final SleSendqProcStatusEnum PROCESSED = new SleSendqProcStatusEnum(IntValues.PROCESSED,
          "PROCESSED");

    /** ���[�J���ύX�܂��͎���ł��邽�ߏ������ȗ����ꂽ���Ƃ�����킵�܂��B*/
    public static final SleSendqProcStatusEnum SKIP_PROCESSING_LOCAL = new SleSendqProcStatusEnum(IntValues.SKIP_PROCESSING_LOCAL,
          "SKIP_PROCESSING_LOCAL");

    /** �G���[�̂��ߏ������ȗ����ꂽ���Ƃ�����킵�܂��B */
    public static final SleSendqProcStatusEnum SKIP_PROCESSING_ERROR = new SleSendqProcStatusEnum(IntValues.SKIP_PROCESSING_ERROR,
          "SKIP_PROCESSING_ERROR");
    
    /**�����M��Ԃ�����킵�܂��B */
    public static final SleSendqProcStatusEnum NOT_PROCESSED = new SleSendqProcStatusEnum(IntValues.NOT_PROCESSED,
          "NOT_PROCESSED");
    
    /**���M������Ԃ�\���܂��B*/
    public static final SleSendqProcStatusEnum PREPARE_PROCESSED = new SleSendqProcStatusEnum(IntValues.PREPARE_PROCESSED,
          "PREPARE_PROCESSED");
          
//    /** �Ǘ���ʗp����`�t���O'2' */
//    public static final SleSendqProcStatusEnum NO_DEFINE_2 = new SleSendqProcStatusEnum(IntValues.NO_DEFINE_2,
//            "NO_DEFINE_2");
    
    /** �Ǘ���ʗp����`�t���O'3' */      
    public static final SleSendqProcStatusEnum NO_DEFINE_3 = new SleSendqProcStatusEnum(IntValues.NO_DEFINE_3,
            "NO_DEFINE_3");                    
    
    /** �Ǘ���ʗp����`�t���O'4' */
    public static final SleSendqProcStatusEnum NO_DEFINE_4 = new SleSendqProcStatusEnum(IntValues.NO_DEFINE_4,
            "NO_DEFINE_4");
    
    /** �Ǘ���ʗp����`�t���O'5' */
    public static final SleSendqProcStatusEnum NO_DEFINE_5 = new SleSendqProcStatusEnum(IntValues.NO_DEFINE_5,
            "NO_DEFINE_5");
          
    /** �o�b�`�����I���t���O'2' */
    public static final SleSendqProcStatusEnum BAT_PROCED = new SleSendqProcStatusEnum(IntValues.BAT_PROCED,
            "BAT_PROCED");    

    
    /**
     * ��ʃN���X�̃R���X�g���N�^���I�[�o���C�h����K�{�̃R���X�g���N�^�ł��B 
     *
     * @@param i int�^�̒l
     * @@param s ������\��
     */
    public SleSendqProcStatusEnum(int i, String s) {
        super(i, s);
    }

    /**
     * �C�ӂ̃G�i���̐����l��`����������N���X�ł��B�����l���`���邱�Ƃɂ��A 
     * ���̃N���X��switch���ŗe�Ղɗ��p���邱�Ƃ��ł��܂��B
     */
    public static class IntValues {

        //~ Static fields/initializers ---------------------------------------------

        /** �����҂���Ԃ�����킵�܂��B  */
        public static final int TODO = 0;

        /** �����ς�����킵�܂��B */
        public static final int PROCESSED = 1;

        /** ���[�J���ύX�܂��͎���ł��邽�ߏ������ȗ����ꂽ���Ƃ�����킵�܂��B */
        public static final int SKIP_PROCESSING_LOCAL    = 8;

        /** �G���[�̂��ߏ������ȗ����ꂽ���Ƃ�����킵�܂��B */
        public static final int SKIP_PROCESSING_ERROR = 9;
        
        /**�����M��Ԃ�����킵�܂��B */
        public static final int NOT_PROCESSED = 7;
        
        /**���M������Ԃ�\���܂��B*/
        public static final int PREPARE_PROCESSED = 6;
        
//        /** �Ǘ���ʗp����`�t���O'2' */
//        public static final int NO_DEFINE_2 = 2;
        
        /** �Ǘ���ʗp����`�t���O'3' */        
        public static final int NO_DEFINE_3 = 3;
        
        /** �Ǘ���ʗp����`�t���O'4' */
        public static final int NO_DEFINE_4 = 4;
        
        /** �Ǘ���ʗp����`�t���O'5' */
        public static final int NO_DEFINE_5 = 5;
        
        /** �o�b�`�����ς݂̏�����ԁB */
        public static final int BAT_PROCED = 2;
        
    }
}
@
