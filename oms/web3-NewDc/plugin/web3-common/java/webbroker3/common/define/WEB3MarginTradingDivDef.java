head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginTradingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3MarginTradingDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * �M�p����敪�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3MarginTradingDivDef
{
    /**
     * 0 �F DEFAULT�i�M�p����ȊO�j
     */
    public static final String DEFAULT = "0";

    /**
     * 1 �F ���x�M�p
     */
    public static final String MARKET_MARGIN = "1";

    /**
     * 2 �F ��ʐM�p
     */
    public static final String INSTITUTION_MARGIN = "2";
    
    /**
     * 3�F���x�^��ʐM�p(����)
     */
    public static final String MARKET_MARGIN_OR_INSTITUTION_MARGIN = "3";

}
@
