head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������� �萔��`�C���^�t�F�C�X(WEB3EquityOrderTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/27 �Ջ`�g(���u) �쐬
*/
package webbroker3.equity.define;

/**
 * ������ԁ@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �Ջ`�g
 * @@version 1.0
 */
public interface WEB3EquityOrderTypeDef {

    /**
     * "00"�i�V�K�����j
     */
    public static final String NEW_ORDER = "00";

    /**
     *  "01"�i�����j
     */
    public static final String CHANGE_ORDER = "01";
    
    /**
     *  "02"�i����j
     */
    public static final String CANCEL_ORDER = "02";
    
    /**
     * "03"�i�ꕔ���j
     */
    public static final String ONE_ORDERED = "03";

    /**
     * "04"�i�S�����j
     */
    public static final String ALL_ORDERED = "04";
    
    
    /**
     * "05"�i�ꕔ�����j
     */
    public static final String ONE_LAPSE = "05";

    /**
     * "06"�i�S�������j
     */
    public static final String ALL_LAPSE = "06";

    /**
     * "07"�i�����J�z�j
     */
    public static final String ORDER_OVER = "07";

    /**
     * "99"�i���̑��j
     */
    public static final String OTHER = "99";
    
}
@
