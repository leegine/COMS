head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PriceConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �l�i�����@@�萔��`�C���^�t�F�C�X(WEB3PriceConditionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 SRA���� �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �l�i�����@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author SRA����
 * @@version 1.0
 */
public interface WEB3PriceConditionDef
{
    /**
      * 0�F DEFAULT 
      */
    public static final String DEFAULT = "0";
    
    /**
      * 1�F ���ݒl�w�l����
      */
    public static final String PRESENT_VALUE_LIMIT_PRICE_ORDER = "1";
    
    /**
      * 3�F �D��w�l����
      */
    public static final String PRIORITY_LIMIT_PRICE_ORDER = "3";
    
    /**
      * 5�F ���s�c���w�l����
      */
    public static final String PARTIALLY_LIMIT_PRICE_ORDER = "5";
    
    /**
      * 7�F ���s�c���������
      */
    public static final String PARTIALLY_CANCEL_ORDER = "7";
}@
