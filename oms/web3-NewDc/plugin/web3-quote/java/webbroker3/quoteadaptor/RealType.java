head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	RealType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �����f�[�^�̃��A���敪��\��Enumerated�N���X(RealType.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 �R�c�@@��i(FLJ) �V�K�쐬
                    : 2008/05/14 ���@@�@@�@@�@@��(FLJ) ���A���敪�̒l�ɒǉ��@@�@@4:�X�i�b�v�V���b�g
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * ���A���敪��Enum�N���X
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class RealType extends Enumerated
{
    
    /**
     * ���A���敪��Enum�N���X�Ŏg�p����萔<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static class IntValues {
        
        /**
         * ���A���敪�F����`
         */
        public static final int UNDEFINED = 0;
        
        /**
         * ���A���敪�F���A��
         */
        public static final int REAL = 1;
        
        /**
         * ���A���敪�F20���f�B���C
         */
        public static final int DELAY = 2;
        
        /**
         * ���A���敪�F���l
         */
        public static final int CLOSING_PRICE = 3;
        
        /**
         * ���A���敪�F�X�i�b�v�V���b�g
         */
        public static final int SNAPSHOT = 4;
    }
    
    /**
     * ���A���敪�F����`
     */
    public static final RealType UNDEFINED =
        new RealType(IntValues.UNDEFINED, "UNDEFINED");
    
    /**
     * ���A���敪�F���A��
     */
    public static final RealType REAL =
        new RealType(IntValues.REAL, "REAL");
    
    /**
     * ���A���敪�F20���f�B���C
     */
    public static final RealType DELAY =
        new RealType(IntValues.DELAY, "DELAY");
        
    /**
     * ���A���敪�F���l
     */
    public static final RealType CLOSING_PRICE =
        new RealType(IntValues.CLOSING_PRICE, "CLOSING_PRICE");
    
    /**
     * ���A���敪�F�X�i�b�v�V���b�g
     */
    public static final RealType SNAPSHOT = 
        new RealType(IntValues.SNAPSHOT, "SNAPSHOT");
    
    /**
     * �R���X�g���N�^
     */
    private RealType(int i, String s) {
        super(i, s);
    }
    
}
@
