head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	CurrentPriceFlag.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : ���ݒl�t���O��Enum�N���X(CurrentPriceFlag.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * ���ݒl�t���O��Enum�N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class CurrentPriceFlag extends Enumerated
{
    
    /**
     * ���ݒl�t���O��Enum�N���X�Ŏg�p�����萔<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues
    {
        
        /**
         * ���ݒl�t���O�F�ʏ�
         */
        public static final int NORMAL = 0;
        
        /**
         * ���ݒl�t���O�F�I�l
         */
        public static final int CLOSING_PRICE = 1;
        
    }

    /**
     * ���ݒl�t���O�F�ʏ�
     */
    public static final CurrentPriceFlag NORMAL =
        new CurrentPriceFlag(IntValues.NORMAL, "NORMAL");

    /**
     * ���ݒl�t���O�F�I�l
     */
    public static final CurrentPriceFlag CLOSING_PRICE =
        new CurrentPriceFlag(IntValues.CLOSING_PRICE, "CLOSE_PRICE");

    /**
     * �R���X�g���N�^
     */
    private CurrentPriceFlag(int i, String s)
    {
        super(i, s);
    }
}
@
