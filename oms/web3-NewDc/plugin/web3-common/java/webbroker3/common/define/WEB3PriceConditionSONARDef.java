head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PriceConditionSONARDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �l�i�����iSONAR�j�@@�萔��`�C���^�t�F�C�X(WEB3PriceConditionSONARDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 �������F(SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �l�i�����iSONAR�j�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author SRA����
 * @@version 1.0
 */
public interface WEB3PriceConditionSONARDef
{
    /**
      * �u�����N�F �w�薳�� 
      */
    public static final String DEFAULT = " ";
    
    /**
      * A�F ���ݒl�w�l����
      */
    public static final String PRESENT_VALUE_LIMIT_PRICE_ORDER = "A";
    
    /**
      * B�F �D��w�l����
      */
    public static final String PRIORITY_LIMIT_PRICE_ORDER = "B";
    
    /**
      * C�F ���s�c���w�l����
      */
    public static final String PARTIALLY_LIMIT_PRICE_ORDER = "C";
    
    /**
      * D�F ���s�c���������
      */
    public static final String PARTIALLY_CANCEL_ORDER = "D";
}
@
