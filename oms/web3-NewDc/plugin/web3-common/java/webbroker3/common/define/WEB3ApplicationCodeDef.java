head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ApplicationCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3ApplicationCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02 jia-yuanchun(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * �K�p�R�[�h�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3ApplicationCodeDef
{
    /**
     * 10:�M�p����
     */
    public static final String MARGIN_SETTLE = "10";

    /**
     * 11:�m��z��
     */
    public static final String CONFIRM_DIVIDEND = "11";

    /**
     * 12:�a����z��
     */
    public static final String DEPOSIT_DIVIDEND = "12";

    /**
     * 13:�����󕥋�
     */
    public static final String CLAIM_PAYMENT = "13";

    /**
     * 20:�~�j������
     */
    public static final String MINI_STOCK_TRADE = "20";

    /**
     * 21:�~�j�[�����p
     */
    public static final String MINI_STOCK_SALE = "21";

    /**
     * 22:�~�j�L������
     */
    public static final String MINI_STOCK_ONEROUS_INCREASE = "22";

    /**
     * 31:�O���������p
     */
    public static final String FOREIGN_STOCK_CLAIM_SALE = "31";

    /**
     * 00:�ȊO
     */
    public static final String OTHER = "00";
}
@
