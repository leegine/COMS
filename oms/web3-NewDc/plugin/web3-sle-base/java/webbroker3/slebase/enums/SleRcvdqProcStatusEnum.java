head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleRcvdqProcStatusEnum.java;


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
 * RCVD_Q'�����敪'�Ɋւ���G�i���ϐ����`����
 */
public class SleRcvdqProcStatusEnum extends Enumerated {
    /** �����҂���Ԃ�����킵�܂� */
    public static final SleRcvdqProcStatusEnum TODO = new SleRcvdqProcStatusEnum(IntValues.TODO,
          "TODO");

    /** �����ς�����킵�܂��B */
    public static final SleRcvdqProcStatusEnum PROCESSED = new SleRcvdqProcStatusEnum(IntValues.PROCESSED,
          "PROCESSED");
    
    /** �������������ꂽ���Ƃ�����킵�܂��B */
    public static final SleRcvdqProcStatusEnum SKIP_PROCESSING_IGNORE = new SleRcvdqProcStatusEnum(IntValues.SKIP_PROCESSING_IGNORE,
          "SKIP_PROCESSING_IGNORE");
    
    /** �G���[�̂��ߏ������ȗ����ꂽ���Ƃ�����킵�܂��B */
    public static final SleRcvdqProcStatusEnum SKIP_PROCESSING_ERROR = new SleRcvdqProcStatusEnum(IntValues.SKIP_PROCESSING_ERROR,
          "SKIP_PROCESSING_ERROR");

    /** �Ǘ���ʗp����`�t���O'2' */
    public static final SleRcvdqProcStatusEnum NO_DEFINE_2 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_2,
          "NO_DEFINE_2");
    
    /** �Ǘ���ʗp����`�t���O'3' */      
    public static final SleRcvdqProcStatusEnum NO_DEFINE_3 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_3,
          "NO_DEFINE_3");                    
    
    /** �Ǘ���ʗp����`�t���O'4' */
    public static final SleRcvdqProcStatusEnum NO_DEFINE_4 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_4,
          "NO_DEFINE_4");
    
    /** �Ǘ���ʗp����`�t���O'5' */
    public static final SleRcvdqProcStatusEnum NO_DEFINE_5 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_5,
          "NO_DEFINE_5");
          
    /** �Ǘ���ʗp����`�t���O'6' */
    public static final SleRcvdqProcStatusEnum NO_DEFINE_6 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_6,
          "NO_DEFINE_6");
    
    /** ��菈�����t���O'7' */      
    public static final SleRcvdqProcStatusEnum EXEC_PROCESSING = new SleRcvdqProcStatusEnum(IntValues.EXEC_PROCESSING,
          "EXEC_PROCESSING");                    
    

    
    /**
     * ��ʃN���X�̃R���X�g���N�^���I�[�o���C�h����K�{�̃R���X�g���N�^�ł��B 
     *
     * @@param i int�^�̒l
     * @@param s ������\��
     */
    public SleRcvdqProcStatusEnum(int i, String s) {
        super(i, s);
    }

    /**
     * �C�ӂ̃G�i���̐����l��`����������N���X�ł��B�����l���`���邱�Ƃɂ��A 
     * ���̃N���X��switch���ŗe�Ղɗ��p���邱�Ƃ��ł��܂��B
     */
    public static class IntValues {

        //~ Static fields/initializers ---------------------------------------------

        /** �����҂���Ԃ�����킵�܂��B */
        public static final int TODO = 0;

        /** �����ς�����킵�܂��B */
        public static final int PROCESSED = 1;
        
        /** �������������ꂽ���Ƃ�����킵�܂��B */
        public static final int SKIP_PROCESSING_IGNORE = 8;
        
        /** �G���[�̂��ߏ������ȗ����ꂽ���Ƃ�����킵�܂��B */
        public static final int SKIP_PROCESSING_ERROR = 9;
        
        /** �Ǘ���ʗp����`�t���O'2' */
        public static final int NO_DEFINE_2 = 2;
        
        /** �Ǘ���ʗp����`�t���O'3' */        
        public static final int NO_DEFINE_3 = 3;
        
        /** �Ǘ���ʗp����`�t���O'4' */
        public static final int NO_DEFINE_4 = 4;
        
        /** �Ǘ���ʗp����`�t���O'5' */
        public static final int NO_DEFINE_5 = 5;

        /** �Ǘ���ʗp����`�t���O'6' */
        public static final int NO_DEFINE_6 = 6;
        
        /** ��菈�����t���O'7' */
        public static final int EXEC_PROCESSING = 7;
        
    }
    

}
@
