head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	BidPriceTitle.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �����������N���X(BidPriceTitle.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 �R�c�@@��i(FLJ) �V�K�쐬
 *                  : 2006/06/27 �R�c�@@��i(FTL) ���C�z�l�^�C�g��.�񂹒��Ɣ��C�z�l�^�C�g��.���ʋC�z��ǉ�
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * ���C�z�l�^�C�g����Enum�N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class BidPriceTitle extends Enumerated
{
    /**
     * ���C�z�l�^�C�g����Enum�N���X�Ŏg�p����萔<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues {
        
        /**
         * ���C�z�l�^�C�g���F����`
         */
        public static final int UNDEFINED = 0;
        
        /**
         * ���C�z�l�^�C�g���F�񂹒�
         */
        public static final int ITAYOSECHU = 3;
        
        /**
         * ���C�z�l�^�C�g���F���C�z
         */
        public static final int BID = 4;
        
        /**
         * ���C�z�l�^�C�g���F���ʋC�z
         */
        public static final int SPECIAL_QUOTATION = 5;
        
    }
    
    /**
     * ���C�z�l�^�C�g���F����`
     */
    public static final BidPriceTitle UNDEFINED =
        new BidPriceTitle(IntValues.UNDEFINED, "UNDEFINED");
    
    /**
     * ���C�z�l�^�C�g���F�񂹒�
     */
    public static final BidPriceTitle ITAYOSECHU =
        new BidPriceTitle(IntValues.ITAYOSECHU, "ITAYOSECHU");
        
    /**
     * ���C�z�l�^�C�g���F���C�z
     */
    public static final BidPriceTitle BID =
        new BidPriceTitle(IntValues.BID, "BID");
        
    /**
     * ���C�z�l�^�C�g���F���ʋC�z
     */
    public static final BidPriceTitle SPECIAL_QUOTATION =
        new BidPriceTitle(IntValues.SPECIAL_QUOTATION, "SPECIAL_QUOTATION");
        
    private BidPriceTitle(int i, String s) {
        super(i, s);
    }
}
@
