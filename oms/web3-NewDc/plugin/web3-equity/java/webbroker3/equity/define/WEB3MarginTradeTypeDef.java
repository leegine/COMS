head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginTradeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3MarginSwapAccountTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20  WuYanFei(sinocom)�@@�V�K�쐬
*/
package webbroker3.equity.define;

/**
 * ����敪
 *                                                                     
 * @@author Wu Yan Fei
 * @@version 1.1
 */
public interface WEB3MarginTradeTypeDef
{
    /**
     *3 : �V�K��������
     */
    public static final String OPEN_LONG_MARGIN = "3";

    /**
     *4 : �V�K��������
     */
    public static final String OPEN_SHORT_MARGIN = "4";
    
    /**
     *5 : �����ԍϒ����i���ԍρj
     */
    public static final String CLOSE_SELL_MARGIN = "5";
    
    /**
     *6 : �����ԍϒ����i���ԍρj
     */
    public static final String CLOSE_BUY_MARGIN = "6";
    
    /**
     *7 : ��������
     */
    public static final String  GENBIKI_ORDER= "7";
    
    /**
     *8 : ���n����
     */ 
    public static final String GENWADASI_ORDER = "8";
}
@
