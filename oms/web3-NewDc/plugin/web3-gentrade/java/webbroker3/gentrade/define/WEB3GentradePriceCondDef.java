head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePriceCondDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �l�i�����R�[�h��`�C���^�t�F�C�X(WEB3GentradePriceCondDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 �Г� (���u) �V�K�쐬
*/
package webbroker3.gentrade.define;

/**
 * �l�i�����R�[�h��`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3GentradePriceCondDef
{
    /**
     * 0�F�@@DEFAULT(�����w��Ȃ�)
     */
    public static final String DEFAULT = "0";
    
    /**
     * 1�F�@@���ݒl�w�l����
     */
    public static final String CURRENT_PRICE_ORDER = "1";

    /**
     * 3�F�@@�D��w�l����
     */
    public static final String EASE_CURRENT_PRICE_ORDER = "3";

    /**
     * 5�F�@@���s�c���w�l����
     */
    public static final String MARKET_PRICE_REST_LIMIT_PRICE = "5";

    /**
     * 7�F�@@���s�c���������
     */
    public static final String MARKET_PRICE_REST_CANCEL = "7";
}
@
