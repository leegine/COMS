head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoTradeTypeDef.java;


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
package webbroker3.ifo.define;

/**
 * ����敪
 *                                                                     
 * @@author Wu Yan Fei
 * @@version 1.1
 */
public interface WEB3IfoTradeTypeDef
{
    /**
     *3 : �V�K��������
     */
    public static final String OPEN_LONG_CONTRACT = "3";

    /**
     *4 : �V�K��������
     */
    public static final String OPEN_SHORT_CONTRACT = "4";
    
    /**
     *5 : �����ԍϒ����i���ԍρj
     */
    public static final String CLOSE_SELL_CONTRACT = "5";
    
    /**
     *6 : �����ԍϒ����i���ԍρj
     */
    public static final String CLOSE_BUY_CONTRACT = "6";
}
@
