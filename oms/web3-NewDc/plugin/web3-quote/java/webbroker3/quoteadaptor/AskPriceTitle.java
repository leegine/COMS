head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	AskPriceTitle.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �����������N���X(AskPriceTitle.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 �R�c�@@��i(FLJ) �V�K�쐬
 *                    2006/06/27 �R�c�@@��i(FTL) ���C�z�l�^�C�g��.���ʋC�z��ǉ�
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * ���C�z�l�^�C�g����Enum�N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class AskPriceTitle extends Enumerated
{

    /**
     * ���C�z�l�^�C�g����Enum�N���X�Ŏg�p����萔<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues
    {

        /**
         * ���C�z�l�^�C�g���F����`
         */
        public static final int UNDEFINED = 0;

        /**
         * ���C�z�l�^�C�g���F���C�z
         */
        public static final int ASK = 1;

        /**
         * ���C�z�l�^�C�g���F������~
         */
        public static final int TRADING_SUSPENDED = 2;

        /**
         * ���C�z�l�^�C�g���F�񂹒�
         */
        public static final int ITAYOSECHU = 3;
        
        /**
         * ���C�z�l�^�C�g���F���ʋC�z
         */
        public static final int SPECIAL_QUOTATION = 5;
        
    }

    /**
     * ���C�z�l�^�C�g���F����`
     */
    public static final AskPriceTitle UNDEFINED =
        new AskPriceTitle(IntValues.UNDEFINED, "UNDEFINED");

    /**
     * ���C�z�l�^�C�g���F���C�z
     */
    public static final AskPriceTitle ASK =
        new AskPriceTitle(IntValues.ASK, "ASK");

    /**
     * ���C�z�l�^�C�g���F������~
     */
    public static final AskPriceTitle TRADING_SUSPENDED =
        new AskPriceTitle(IntValues.TRADING_SUSPENDED, "TRADING_SUSPENDED");

    /**
     * ���C�z�l�^�C�g���F�񂹒�
     */
    public static final AskPriceTitle ITAYOSECHU =
        new AskPriceTitle(IntValues.ITAYOSECHU, "ITAYOSECHU");

    /**
     * ���C�z�l�^�C�g���F���ʋC�z
     */
    public static final AskPriceTitle SPECIAL_QUOTATION =
        new AskPriceTitle(IntValues.SPECIAL_QUOTATION, "SPECIAL_QUOTATION");

    /**
     * �R���X�g���N�^
     */
    private AskPriceTitle(int i, String s)
    {
        super(i, s);
    }
}
@
