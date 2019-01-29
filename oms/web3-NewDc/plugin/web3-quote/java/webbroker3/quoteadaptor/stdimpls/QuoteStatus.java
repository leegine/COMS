head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	QuoteStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteStatusEnum�N���X(WEB3QuoteStatusEnum.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.quoteadaptor.stdimpls;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;


/**
 * �����T�[�r�X�̐ڑ���Ԃ�\��Enumerated�N���X
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public final class QuoteStatus extends Enumerated
{
    
    /**
     * WEB3QuoteStatusEnum�̐����l�̒�`�N���X
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    public static final class IntValues {

        /**
         * �ڑ���ԁF���ڑ�
         */
        public static final int CLOSED = 0;
        
        /**
         * �ڑ���ԁF�ڑ���
         */
        public static final int CONNECTING = 1;

        /**
         * �ڑ���ԁF�ڑ���
         */
        public static final int CONNECTED = 2;
        
    }
    
    /**
     * �ڑ���ԁF���ڑ�
     */
    public static final QuoteStatus CLOSED = 
        new QuoteStatus(IntValues.CLOSED, "CLOSED");
    
    /**
     * �ڑ���ԁF�ڑ���
     */
    public static final QuoteStatus CONNECTING =
        new QuoteStatus(IntValues.CONNECTING, "CONNECTING");

    /**
     * �ڑ���ԁF�ڑ���
     */
    public static final QuoteStatus CONNECTED =
        new QuoteStatus(IntValues.CONNECTED, "CONNECTED");

    /**
     * �R���X�g���N�^
     * 
     * @@param intValue �����l
     * @@param stringValue ������l
     */
    private QuoteStatus(int intValue, String stringValue)
    {
        super(intValue, stringValue);
    }

}
@
